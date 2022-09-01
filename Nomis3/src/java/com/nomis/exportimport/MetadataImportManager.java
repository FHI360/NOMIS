/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.CommunityWorker;
import com.nomis.ovc.business.Partner;
import com.nomis.ovc.business.ReferralFacility;
import com.nomis.ovc.business.School;
import com.nomis.ovc.business.SchoolGrade;
import com.nomis.ovc.business.Service;
import com.nomis.ovc.business.VulnerabilityStatus;
import com.nomis.ovc.dao.BeneficiaryServiceDao;
import com.nomis.ovc.dao.CommunityBasedOrganizationDao;
import com.nomis.ovc.dao.CommunityWorkerDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.OrganizationUnitDao;
import com.nomis.ovc.dao.PartnerDao;
import com.nomis.ovc.dao.ReferralFacilityDao;
import com.nomis.ovc.dao.SchoolDao;
import com.nomis.ovc.dao.SchoolGradeDao;
import com.nomis.ovc.dao.VulnerabilityStatusDao;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.TaskManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 *
 * @author smomoh
 */
public class MetadataImportManager 
{
    ZipHandler zipHandler;
    DataEncryption encryptor;
    AppUtility appUtil=new AppUtility();
    DaoUtility util=new DaoUtility();
public List importOrganizationUnitRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="OrganizationUnit";
    AppUtility.setCurrentImportRecordName("State");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new Organization unit records saved");
    duplicateRecordsList.add("Number of Organization unit records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    OrganizationUnitDao oudao=util.getOrganizationUnitDaoInstance();
                    OrganizationUnit ou=null;
                    OrganizationUnit existingOu=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        //System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName("OrganizationUnit");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            ou=new OrganizationUnit();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {                              
                                ou.setUid(getNodeValue("uid",s,listOfObjects));
                                ou.setOucode(getNodeValue("oucode",s,listOfObjects));
                                ou.setName(getNodeValue("name",s,listOfObjects));
                                ou.setOuPath(getNodeValue("ouPath",s,listOfObjects));
                                ou.setOuPathByNames(getNodeValue("ouPathByNames",s,listOfObjects));
                                ou.setDescription(getNodeValue("description",s,listOfObjects));
                                ou.setParentHierarchyId(getNodeValue("parentHierarchyId",s,listOfObjects));
                                ou.setParentId(getNodeValue("parentId",s,listOfObjects));
                                ou.setOulevel(Integer.parseInt(getNodeValue("oulevel",s,listOfObjects)));
                                ou.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                ou.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                if(getNodeName("datimId",s,listOfObjects) !=null)
                                {
                                    ou.setDatimId(getNodeValue("datimId",s,listOfObjects));
                                }
                                if(ou.getUid() !=null && ou.getOuPath() !=null && ou.getParentId() !=null && ou.getName() !=null)
                                {
                                    existingOu=oudao.getOrganizationUnit(ou.getUid());
                                    if(existingOu==null)
                                    {
                                        existingOu=oudao.getOrganizationUnitByName(ou.getName());
                                        //If ou with same name exists, get a new name and save.
                                        if(existingOu !=null)
                                        {
                                            ou=oudao.generateOuName(ou);//oudao.createTemporaryLevel4OrganizationUnit(ou.getUid(), ou.getName());
                                        }
                                        //else
                                        oudao.saveOrganizationUnit(ou);
                                        numberSaved++;
                                    }
                                    else
                                    {
                                        if(ou.getDatimId()==null && existingOu.getDatimId() !=null)
                                        ou.setDatimId(existingOu.getDatimId());
                                        oudao.updateOrganizationUnit(ou);
                                        numberUpdated++;
                                    }
                                    System.err.println(exportFileName+" at "+numberSaved+" saved and "+numberUpdated+" updated");
                                }
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}

	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public List importCommunityBasedOrganizationRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="CommunityBasedOrganization";
    AppUtility.setCurrentImportRecordName("CommunityBasedOrganization");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new Community Based Organization records saved");
    duplicateRecordsList.add("Number of Community Based Organization records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    CommunityBasedOrganizationDao cbodao=util.getCommunityBasedOrganizationDaoInstance();
                    CommunityBasedOrganization cbo=null;
                    CommunityBasedOrganization existingCbo=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        //System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName("CommunityBasedOrganization");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            cbo=new CommunityBasedOrganization();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {  
                                cbo.setUniqueId(getNodeValue("uniqueId",s,listOfObjects));
                                cbo.setAddress(getNodeValue("address",s,listOfObjects));
                                cbo.setAssignedLevel3OuIds(getNodeValue("assignedLevel3OuIds",s,listOfObjects));
                                cbo.setCboCode(getNodeValue("cbocode",s,listOfObjects));
                                cbo.setCboName(getNodeValue("cboName",s,listOfObjects));
                                cbo.setContactPersonEmail(getNodeValue("contactPersonEmail",s,listOfObjects));
                                cbo.setContactPersonName(getNodeValue("contactPersonName",s,listOfObjects));
                                cbo.setContactPersonPhone(getNodeValue("contactPersonPhone",s,listOfObjects));
                                cbo.setDataExchangeId(getNodeValue("dataExchangeId",s,listOfObjects));
                                cbo.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                cbo.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                cbo.setLatitude(Double.parseDouble(getNodeValue("latitude",s,listOfObjects)));
                                cbo.setLevel2OuId(getNodeValue("level2OuId",s,listOfObjects));
                                cbo.setLongitude(Double.parseDouble(getNodeValue("longitude",s,listOfObjects)));
                                cbo.setRecordedBy(getNodeValue("recordedBy",s,listOfObjects));
                                cbo.setServices(getNodeValue("services",s,listOfObjects));
                                                                                              
                                existingCbo=cbodao.getCommunityBasedOrganization(cbo.getUniqueId());
                                if(existingCbo==null)
                                {
                                    existingCbo=cbodao.getCommunityBasedOrganizationByName(cbo.getCboName());
                                    if(existingCbo !=null)
                                    cbo.setCboName(cbo.getCboName()+"-"+cbo.getUniqueId());
                                    cbodao.saveCommunityBasedOrganization(cbo);
                                    numberSaved++;
                                }
                                else
                                {
                                    cbodao.updateCommunityBasedOrganization(cbo);
                                    numberUpdated++;
                                }
                                System.err.println(exportFileName+" at "+numberSaved+" saved and "+numberUpdated+" updated");
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}

	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public List importCommunityWorkerRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="CommunityWorker";
    AppUtility.setCurrentImportRecordName("CommunityWorker");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new Community Workers saved");
    duplicateRecordsList.add("Number of Community Workers saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    CommunityWorkerDao cwdao=util.getCommunityWorkerDaoInstance();
                    CommunityWorker cw=null;
                    CommunityWorker existingCw=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        //System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName("CommunityWorker");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            cw=new CommunityWorker();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {  
                                cw.setCommunityWorkerId(getNodeValue("communityWorkerId",s,listOfObjects));
                                cw.setDesignation(getNodeValue("designation",s,listOfObjects));
                                cw.setFirstName(getNodeValue("firstName",s,listOfObjects));
                                cw.setLevel4OuId(getNodeValue("level4OuId",s,listOfObjects));
                                cw.setSex(getNodeValue("sex",s,listOfObjects));
                                cw.setSurname(getNodeValue("surname",s,listOfObjects));
                                cw.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                cw.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                cw.setRecordedBy(getNodeValue("recordedBy",s,listOfObjects));
                                if(getNodeName("enrollmentStatus",s,listOfObjects) !=null)
                                cw.setEnrollmentStatus(getIntegerNodeValue(getNodeValue("enrollmentStatus",s,listOfObjects)));
                                
                                                                                                                              
                                existingCw=cwdao.getCommunityWorker(cw.getCommunityWorkerId());
                                if(existingCw==null)
                                {
                                    cwdao.saveCommunityWorker(cw);
                                    numberSaved++;
                                }
                                else
                                {
                                    cwdao.updateCommunityWorker(cw);
                                    numberUpdated++;
                                }
                                System.err.println(exportFileName+" at "+numberSaved+" saved and "+numberUpdated+" updated");
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}

	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public List importSchoolRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="School";
    AppUtility.setCurrentImportRecordName("School");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new School records saved");
    duplicateRecordsList.add("Number of School records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    SchoolDao schooldao=util.getSchoolDaoInstance();
                    School school=null;
                    School existingSchool=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        //System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName("School");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            school=new School();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {  
                                school.setId(getNodeValue("id",s,listOfObjects));
                                school.setMarkForDelete(Integer.parseInt(getNodeValue("markedForDelete",s,listOfObjects)));
                                school.setOrgUnitId(getNodeValue("orgUnitId",s,listOfObjects));
                                school.setSchoolName(getNodeValue("schoolName",s,listOfObjects));
                                school.setSchoolType(Integer.parseInt(getNodeValue("schoolType",s,listOfObjects)));
                                school.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                school.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                school.setRecordedBy(getNodeValue("recordedBy",s,listOfObjects));
                                                                                                                              
                                existingSchool=schooldao.getSchool(school.getId());
                                if(existingSchool==null)
                                {
                                    schooldao.saveSchool(school);
                                    numberSaved++;
                                }
                                else
                                {
                                    schooldao.updateSchool(school);
                                    numberUpdated++;
                                }
                                System.err.println(exportFileName+" at "+numberSaved+" saved and "+numberUpdated+" updated");
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}

	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public List importSchoolGradeRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="SchoolGrade";
    AppUtility.setCurrentImportRecordName("SchoolGrade");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new SchoolGrade records saved");
    duplicateRecordsList.add("Number of SchoolGrade records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    SchoolGradeDao sgdao=util.getSchoolGradeDaoInstance();
                    SchoolGrade grade=null;
                    SchoolGrade existingGrade=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        //System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName("SchoolGrade");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            grade=new SchoolGrade();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {  
                                grade.setId(getNodeValue("id",s,listOfObjects));
                                grade.setGradeLevel(Integer.parseInt(getNodeValue("gradeLevel",s,listOfObjects)));
                                grade.setGradeName(getNodeValue("gradeName",s,listOfObjects));
                                grade.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                grade.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                grade.setMarkForDelete(Integer.parseInt(getNodeValue("markedForDelete",s,listOfObjects)));
                                grade.setRecordedBy(getNodeValue("recordedBy",s,listOfObjects));
                                                                                                                              
                                existingGrade=sgdao.getSchoolGrade(grade.getId());
                                if(existingGrade==null)
                                {
                                    sgdao.saveSchoolGrade(grade);
                                    numberSaved++;
                                }
                                else
                                {
                                    sgdao.updateSchoolGrade(grade);
                                    numberUpdated++;
                                }
                                System.err.println(exportFileName+" at "+numberSaved+" saved and "+numberUpdated+" updated");
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}

	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public List importPartnerRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="Partner";
    AppUtility.setCurrentImportRecordName("Partner");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new Partner records saved");
    duplicateRecordsList.add("Number of Partner records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    PartnerDao pdao=util.getPartnerDaoInstance();
                    Partner partner=null;
                    Partner existingPartner=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        //System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName("Partner");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            partner=new Partner();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {  
                                partner.setPartnerCode(getNodeValue("partnerCode",s,listOfObjects));
                                partner.setPartnerName(getNodeValue("partnerName",s,listOfObjects));
                                partner.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                partner.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                if(getNodeName("datimId",s,listOfObjects) !=null)
                                {
                                    partner.setDatimId(getNodeValue("datimId",s,listOfObjects));
                                }
                                                                                                                                                              
                                existingPartner=pdao.getPartner(partner.getPartnerCode());
                                if(existingPartner==null)
                                {
                                    pdao.savePartner(partner);
                                    numberSaved++;
                                }
                                else
                                {
                                    if(partner.getDatimId()==null && existingPartner.getDatimId() !=null)
                                    partner.setDatimId(existingPartner.getDatimId());
                                    pdao.updatePartner(partner);
                                    numberUpdated++;
                                }
                                System.err.println(exportFileName+" at "+numberSaved+" saved and "+numberUpdated+" updated");
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}
	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public List importReferralFacilityRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="ReferralFacility";
    AppUtility.setCurrentImportRecordName("ReferralFacility");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new ReferralFacility records saved");
    duplicateRecordsList.add("Number of ReferralFacility records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    ReferralFacilityDao rfdao=util.getReferralFacilityDaoInstance();
                    ReferralFacility rf=null;
                    ReferralFacility existingFacility=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        //System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName("ReferralFacility");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            rf=new ReferralFacility();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {  
                                rf.setAddress(getNodeValue("address",s,listOfObjects));
                                rf.setContactEmail(getNodeValue("contactEmail",s,listOfObjects));
                                rf.setContactPhone(getNodeValue("contactPhone",s,listOfObjects));
                                rf.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                rf.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                rf.setFacilityId(getNodeValue("facilityId",s,listOfObjects));
                                rf.setFacilityName(getNodeValue("facilityName",s,listOfObjects));
                                rf.setLatitude(Double.parseDouble(getNodeValue("latitude",s,listOfObjects)));
                                rf.setLongitude(Double.parseDouble(getNodeValue("longitude",s,listOfObjects)));
                                rf.setNameOfContactPerson(getNodeValue("nameOfContactPerson",s,listOfObjects));
                                rf.setOrganizationUnitId(getNodeValue("organizationUnitId",s,listOfObjects));
                                rf.setRecordedBy(getNodeValue("recordedBy",s,listOfObjects));
                                rf.setTypeOfFacility(Integer.parseInt(getNodeValue("typeOfFacility",s,listOfObjects)));
                                if(getNodeName("datimId",s,listOfObjects) !=null)
                                {
                                    rf.setDatimId(getNodeValue("datimId",s,listOfObjects));
                                }
                                                                                                                                                              
                                existingFacility=rfdao.getReferralFacility(rf.getFacilityId());
                                if(existingFacility==null)
                                {
                                    rfdao.saveReferralFacility(rf);
                                    numberSaved++;
                                }
                                else
                                {
                                    if(rf.getDatimId()==null && existingFacility.getDatimId() !=null)
                                    rf.setDatimId(existingFacility.getDatimId());
                                    rfdao.updateReferralFacility(rf);
                                    numberUpdated++;
                                }
                                System.err.println(exportFileName+" at "+numberSaved+" saved and "+numberUpdated+" updated");
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}
	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public List importVulnerabilityStatusRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="VulnerabilityStatus";
    AppUtility.setCurrentImportRecordName("VulnerabilityStatus");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new VulnerabilityStatus records saved");
    duplicateRecordsList.add("Number of VulnerabilityStatus records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    //appUtil.getZipExtractFilePath()
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    System.err.println("filePath is "+filePath);
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    VulnerabilityStatusDao vsdao=util.getVulnerabilityStatusDaoInstance();
                    VulnerabilityStatus vs=null;
                    VulnerabilityStatus existingVs=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName("VulnerabilityStatus");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            vs=new VulnerabilityStatus();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {  
                                vs.setVsEnabled(Integer.parseInt(getNodeValue("vsEnabled",s,listOfObjects)));
                                vs.setVulnerabilityStatusId(getNodeValue("vulnerabilityStatusId",s,listOfObjects));
                                vs.setVulnerabilityStatusName(getNodeValue("vulnerabilityStatusName",s,listOfObjects));
                                vs.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                vs.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                vs.setRecordedBy(getNodeValue("recordedBy",s,listOfObjects));
                                if(getNodeName("vsCategory",s,listOfObjects) !=null)
                                vs.setVsCategory(Integer.parseInt(getNodeValue("vsCategory",s,listOfObjects)));
                                existingVs=vsdao.getVulnerabilityStatus(vs.getVulnerabilityStatusId());
                                if(existingVs==null)
                                {
                                    vsdao.saveVulnerabilityStatus(vs);
                                    numberSaved++;
                                }
                                else
                                {
                                    vsdao.updateVulnerabilityStatus(vs);
                                    numberUpdated++;
                                }
                                System.err.println("VulnerabilityStatus at "+numberSaved+" saved and "+numberUpdated+" updated");
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}
	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public List importBeneficiaryServiceRecordsFromXml(String xmlFileLocation)
{
    if(xmlFileLocation==null)
    xmlFileLocation=appUtil.getZipExtractsFilePath();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="BeneficiaryService";
    AppUtility.setCurrentImportRecordName("BeneficiaryService");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new Beneficiary Service records saved");
    duplicateRecordsList.add("Number of Beneficiary Service records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    //appUtil.getZipExtractFilePath()
    String filePath=xmlFileLocation+fileSeperator+exportFileName+fileSeperator;
    System.err.println("filePath is "+filePath);
    try
	{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
		String fileName=filePath+exportFileName+".xml";
                List files=getFiles(filePath);
                if(files !=null)
                {
                    BeneficiaryServiceDao bsdao=util.getBeneficiaryServiceDaoInstance();
                    Service bs=null;
                    Service existingBs=null;
                    for(int i=0; i<files.size(); i++)
                    {
                        System.err.println(filePath+files.get(i).toString());
                        fileName=filePath+files.get(i).toString();
                         file=new File(fileName);
                        if(!file.exists())
                        {
                            continue;
                        }
                        doc = docBuilder.parse (file);
                        // normalize text representation
                        doc.getDocumentElement().normalize();
                        NodeList listOfObjects = doc.getElementsByTagName(exportFileName);

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            bs=new Service();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {  
                                 //{serviceId,serviceCode,serviceName,beneficiaryType,domainId,serviceValue,displayStatus,cascadableEndAge,cascadableStartAge,dateCreated,lastModifiedDate,recordedBy};
                                bs.setBeneficiaryType(Integer.parseInt(getNodeValue("beneficiaryType",s,listOfObjects)));
                                bs.setCascadableEndAge(Integer.parseInt(getNodeValue("cascadableEndAge",s,listOfObjects)));
                                bs.setCascadableStartAge(Integer.parseInt(getNodeValue("cascadableStartAge",s,listOfObjects)));
                                bs.setDateCreated(DateManager.getDateInstance(getNodeValue("dateCreated",s,listOfObjects)));
                                bs.setLastModifiedDate(DateManager.getDateInstance(getNodeValue("lastModifiedDate",s,listOfObjects)));
                                bs.setRecordedBy(getNodeValue("recordedBy",s,listOfObjects));
                                bs.setDisplayStatus(Integer.parseInt(getNodeValue("displayStatus",s,listOfObjects)));
                                bs.setServiceId(getNodeValue("serviceId",s,listOfObjects));
                                bs.setServiceCode(getNodeValue("serviceCode",s,listOfObjects));
                                bs.setServiceName(getNodeValue("serviceName",s,listOfObjects));
                                bs.setServiceValue(getNodeValue("serviceValue",s,listOfObjects));
                                bs.setDomainId(getNodeValue("domainId",s,listOfObjects));
                                                                
                                existingBs=bsdao.getBeneficiaryService(bs.getServiceId());
                                if(existingBs==null)
                                {
                                    bsdao.saveBeneficiaryService(bs);
                                    numberSaved++;
                                }
                                else
                                {
                                    bsdao.updateBeneficiaryService(bs);
                                    numberUpdated++;
                                }
                                System.err.println("BeneficiaryService at "+numberSaved+" saved and "+numberUpdated+" updated");
                             }
                        }
                    }
	        }
             newRecordsList.add(numberSaved);
            duplicateRecordsList.add(numberUpdated);       
	}
	catch (SAXParseException err)
	{
		err.printStackTrace();
        }
        catch (SAXException e)
        {
		Exception x = e.getException ();
		((x == null) ? e : x).printStackTrace ();
	}
	catch (Exception ex)
	{
		ex.printStackTrace ();
	}
    list.add(newRecordsList);
    list.add(duplicateRecordsList);
    return list;
}
public int importMetadata(String metadataImportFilePath)
{
    int success=0;
    
        try
        {
           TaskManager.setDatabaseLocked(true);
           String destinationDirectory=appUtil.getZipExtractsFilePath(); 
           String fileNameWithoutExtension=null;
           if(metadataImportFilePath !=null)
           {
               File importFile=new File(metadataImportFilePath);
               if(importFile.exists())
               {
                   fileNameWithoutExtension=importFile.getName();
                   if(fileNameWithoutExtension !=null && fileNameWithoutExtension.endsWith(".zip"))
                   {
                       fileNameWithoutExtension=fileNameWithoutExtension.substring(0, fileNameWithoutExtension.lastIndexOf(".zip"));
                       destinationDirectory=destinationDirectory+appUtil.getFileSeperator()+fileNameWithoutExtension;
                       createDestinationFolderAndUnzipMetadataFile(destinationDirectory,metadataImportFilePath);
                       importVulnerabilityStatusRecordsFromXml(destinationDirectory);
                       importReferralFacilityRecordsFromXml(destinationDirectory);
                       importPartnerRecordsFromXml(destinationDirectory);
                       importSchoolGradeRecordsFromXml(destinationDirectory);
                       importSchoolRecordsFromXml(destinationDirectory);
                       importCommunityWorkerRecordsFromXml(destinationDirectory);
                       importCommunityBasedOrganizationRecordsFromXml(destinationDirectory);
                       importOrganizationUnitRecordsFromXml(destinationDirectory);
                       importBeneficiaryServiceRecordsFromXml(destinationDirectory);
                       success=1;
                   }
               }
           }
           TaskManager.setDatabaseLocked(false);
        }
        catch(Exception ex)
        {
            TaskManager.setDatabaseLocked(false);
            success=0;
            ex.printStackTrace();
        }
    return success;
}
public int importMetadata(String metadataImportFilePath,HttpServletRequest request)
{
    int success=importMetadata(metadataImportFilePath);
    return success;
}
public void createDestinationFolderAndUnzipMetadataFile(String destinationDirectory,String filePath)
{
    appUtil=new AppUtility();
    appUtil.createZipExtractDirectories();
    //String destinationDirectory=appUtil.getExportFilePath()+appUtil.getFileSeperator()+fileName;
    appUtil.createDirectories(destinationDirectory);
    zipHandler=new ZipHandler();
    zipHandler.unZipFile(filePath,destinationDirectory);
    //zipHandler.unZipFile(appUtil.getMetadataImportFilePath()+appUtil.getFileSeperator()+fileName,destinationDirectory);
    zipHandler=null;
}
private static String getNodeName(String value,int s,NodeList listOfObjects)
{
    String elementName=null;
    Node firstPersonNode = listOfObjects.item(s);
    Element firstPersonElement = (Element)firstPersonNode;
    if(firstPersonElement !=null)
    {
        NodeList firstNameList = firstPersonElement.getElementsByTagName(value);
        Element firstNameElement = (Element)firstNameList.item(0);

        try
        {
            if(firstNameElement !=null)
            elementName=firstNameElement.getNodeName();
        }
        catch(NullPointerException npe)
        {
            elementName=null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    return elementName;
}
private static String getNodeValue(String value,int s,NodeList listOfObjects)
{
    String elementValue=" ";
    Node firstPersonNode = listOfObjects.item(s);
    Element firstPersonElement = (Element)firstPersonNode;
    if(firstPersonElement !=null)
    {
        NodeList firstNameList = firstPersonElement.getElementsByTagName(value);
        Element firstNameElement = (Element)firstNameList.item(0);

        NodeList textFNList =null;
        if(firstNameElement !=null)
        {
            textFNList =firstNameElement.getChildNodes();
        }
        try
        {
            if((Node)textFNList==null)
            elementValue="";
            else if((Node)textFNList.item(0)==null)
            elementValue="";
            else if(((Node)textFNList.item(0)).getNodeValue()==null || (((Node)textFNList.item(0)).getNodeValue()).equals("") || (((Node)textFNList.item(0)).getNodeValue()).equals(" ") || (((Node)textFNList.item(0)).getNodeValue()).equals("none"))
            elementValue="";
            else
            elementValue=((Node)textFNList.item(0)).getNodeValue();
        }
        catch(NullPointerException npe)
        {
            elementValue="";
        }
    }
    return elementValue;
}
private int getIntegerNodeValue(String value)
{
    int intValue=0;
    try
    {
        if(value !=null && value.trim().length()>0)
        intValue=Integer.parseInt(value);
    }
    catch(NumberFormatException nfe)
    {
        intValue=0;
    }
    
    return intValue;
}
private List getZippedFiles(String filePath)
{
    String[] files=appUtil.listFiles(filePath);
    List fileList=new ArrayList();
    if(files !=null)
    {
        for(int i=0; i<files.length; i++)
        {
            if(files[i].indexOf(".zip") ==-1)
            continue;
            fileList.add(files[i]);
        }
    }
    return fileList;
}
private List getFiles(String filePath)
{
    String[] files=appUtil.listFiles(filePath);
    List fileList=new ArrayList();
    if(files !=null)
    {
        for(int i=0; i<files.length; i++)
        {
            if(files[i].indexOf(".xml") ==-1)
            continue;
            fileList.add(files[i]);
        }
    }
    return fileList;
}
}
