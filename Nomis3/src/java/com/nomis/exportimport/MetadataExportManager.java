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
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.helpers.AttributesImpl;
/**
 *
 * @author smomoh
 */
public class MetadataExportManager 
{
    ZipHandler zipHandler;
    DataEncryption encryptor;
    private static final String isoEncoding="ISO-8859-1";
    static String fileSeperator="\\";
    
    List mainList=null;
   public MetadataExportManager()
   {
      //appUtil=new AppUtility();
      //fileSeperator=appUtil.getFileSeperator();
      //appUtil.createExportImportDirectories();
   }
public static int exportOrganizationUnitRecordsInXml(String parentFolderPath, List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    fileSeperator=appUtil.getFileSeperator();
    DaoUtility util=new DaoUtility();
    String parentTag="OrganizationUnits";
    String fileName="";
    String parentFolder="OrganizationUnit";
        
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        OrganizationUnit ou=null;
	StringWriter sw=new StringWriter();
	StreamResult streamResult = new StreamResult(sw);
	SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

	TransformerHandler hd = tf.newTransformerHandler();
	Transformer serializer = hd.getTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING,isoEncoding);

	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
	hd.setResult(streamResult);
	AttributesImpl atts = new AttributesImpl();
                
        String[] columnNames={"uid","oucode","name","ouPath","ouPathByNames","description","parentHierarchyId","parentId","oulevel","dateCreated","lastModifiedDate","datimId"};
        atts.clear();
        if(list==null)
        list=util.getOrganizationUnitDaoInstance().getAllOrganizationUnit();
        //List ouList=util.getOrganizationUnitDaoInstance().getAllOrganizationUnit();
        if(list !=null)
        {
            hd.startDocument();
            hd.startElement("","",parentTag,atts);
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              for(int k=0; k<loopCount; k++)
              {
                    for(int j=startSize; j<list.size(); j++)
                    {
                        if(j>((k+1)*4999))
                        break;
                      ou=(OrganizationUnit)list.get(j);
                      String uid=getPropertyValue(ou.getUid());
                      String oucode=getPropertyValue(ou.getOucode());
                      String name=getPropertyValue(ou.getName());
                      String ouPath=getPropertyValue(ou.getOuPath());
                      String ouPathByNames=getPropertyValue(ou.getOuPathByNames());
                      String description=getPropertyValue(ou.getDescription());

                      String parentHierarchyId=getPropertyValue(ou.getParentHierarchyId());
                      String parentId=getPropertyValue(ou.getParentId());
                      String oulevel=getIntegerPropertyValue(ou.getOulevel()+"");
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(ou.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(ou.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                      String datimId=getPropertyValue(ou.getDatimId());
                      
                      String[] fieldValues={uid,oucode,name,ouPath,ouPathByNames,description,parentHierarchyId,parentId,oulevel,dateCreated,lastModifiedDate,datimId};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }

                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    if(k>0)
                    fileName=parentFolder+k+".xml";
                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                }
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
public static int exportCBORecordsInXml(String parentFolderPath,List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    fileSeperator=appUtil.getFileSeperator();
    DaoUtility util=new DaoUtility();
    String parentTag="CommunityBasedOrganizations";
    String fileName="";
    String parentFolder="CommunityBasedOrganization";
    
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        CommunityBasedOrganization cbo=null;
	StringWriter sw=new StringWriter();
	StreamResult streamResult = new StreamResult(sw);
	SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

	TransformerHandler hd = tf.newTransformerHandler();
	Transformer serializer = hd.getTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING,isoEncoding);

	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
	hd.setResult(streamResult);
	AttributesImpl atts = new AttributesImpl();
                
        String[] columnNames={"uniqueId","address","assignedLevel3OuIds","cbocode","cboName","contactPersonEmail","contactPersonName","contactPersonPhone","dataExchangeId","level2OuId","latitude","longitude","recordedBy","services","dateCreated","lastModifiedDate"};
        atts.clear();
        if(list==null)
        list=util.getCommunityBasedOrganizationDaoInstance().getAllCommunityBasedOrganization();
        if(list !=null)
        {
            hd.startDocument();
            hd.startElement("","",parentTag,atts);
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              for(int k=0; k<loopCount; k++)
              {
                    for(int j=startSize; j<list.size(); j++)
                    {
                        if(j>((k+1)*4999))
                        break;
                      cbo=(CommunityBasedOrganization)list.get(j);
                      String uniqueId=getPropertyValue(cbo.getUniqueId());
                      String address=getPropertyValue(cbo.getAddress());
                      String assignedLevel3OuIds=getPropertyValue(cbo.getAssignedLevel3OuIds());
                      String cbocode=getPropertyValue(cbo.getCboCode());
                      String cboName=getPropertyValue(cbo.getCboName());
                      String contactPersonEmail=getPropertyValue(cbo.getContactPersonEmail());
                      String contactPersonName=getPropertyValue(cbo.getContactPersonName());
                      String contactPersonPhone=getPropertyValue(cbo.getContactPersonPhone());

                      String dataExchangeId=getPropertyValue(cbo.getDataExchangeId());
                      String level2OuId=getPropertyValue(cbo.getLevel2OuId());
                      String recordedBy=getPropertyValue(cbo.getRecordedBy());
                      String services=getPropertyValue(cbo.getServices());
                      String latitude=getIntegerPropertyValue(cbo.getLatitude()+"");
                      String longitude=getIntegerPropertyValue(cbo.getLongitude()+"");
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(cbo.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(cbo.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));

                      String[] fieldValues={uniqueId,address,assignedLevel3OuIds,cbocode,cboName,contactPersonEmail,contactPersonName,contactPersonPhone,dataExchangeId,level2OuId,latitude,longitude,recordedBy,services,dateCreated,lastModifiedDate};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }

                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    if(k>0)
                    fileName=parentFolder+k+".xml";

                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                }
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
public static int exportCommunityWorkerRecordsInXml(String parentFolderPath,List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    fileSeperator=appUtil.getFileSeperator();
    DaoUtility util=new DaoUtility();
    String parentTag="CommunityWorkers";
    String fileName="";
    String parentFolder="CommunityWorker";
    
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        CommunityWorker cw=null;
	StringWriter sw=new StringWriter();
	StreamResult streamResult = new StreamResult(sw);
	SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

	TransformerHandler hd = tf.newTransformerHandler();
	Transformer serializer = hd.getTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING,isoEncoding);

	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
	hd.setResult(streamResult);
	AttributesImpl atts = new AttributesImpl();
                
        String[] columnNames={"communityWorkerId","designation","firstName","surname","sex","level4OuId","recordedBy","dateCreated","lastModifiedDate","enrollmentStatus"};
        atts.clear();
        if(list==null)
        list=util.getCommunityWorkerDaoInstance().getAllCommunityWorkers();
        if(list ==null)
        list=new ArrayList();
        
        if(list !=null)
        {
            hd.startDocument();
            hd.startElement("","",parentTag,atts);
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              for(int k=0; k<loopCount; k++)
              {
                    for(int j=startSize; j<list.size(); j++)
                    {
                        if(j>((k+1)*4999))
                        break;
                      cw=(CommunityWorker)list.get(j);
                      String communityWorkerId=getPropertyValue(cw.getCommunityWorkerId());
                      String designation=getPropertyValue(cw.getDesignation());
                      String firstName=getPropertyValue(cw.getFirstName());
                      String level4OuId=getPropertyValue(cw.getLevel4OuId());
                      String recordedBy=getPropertyValue(cw.getRecordedBy());
                      String sex=getPropertyValue(cw.getSex());
                      String surname=getPropertyValue(cw.getSurname());
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(cw.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(cw.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                      String enrollmentStatus=getPropertyValue(cw.getEnrollmentStatus()+"");

                      String[] fieldValues={communityWorkerId,designation,firstName,surname,sex,level4OuId,recordedBy,dateCreated,lastModifiedDate,enrollmentStatus};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }

                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    if(k>0)
                    fileName=parentFolder+k+".xml";

                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                }
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
public static int exportSchoolRecordsInXml(String parentFolderPath,List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    fileSeperator=appUtil.getFileSeperator();
    DaoUtility util=new DaoUtility();
    String parentTag="Schools";
    String fileName="";
    String parentFolder="School";
    
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        School school=null;
	StringWriter sw=new StringWriter();
	StreamResult streamResult = new StreamResult(sw);
	SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

	TransformerHandler hd = tf.newTransformerHandler();
	Transformer serializer = hd.getTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING,isoEncoding);

	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
	hd.setResult(streamResult);
	AttributesImpl atts = new AttributesImpl();
                
        String[] columnNames={"id","orgUnitId","schoolName","schoolType","recordedBy","dateCreated","lastModifiedDate","markedForDelete"};
        atts.clear();
        if(list==null)
        list=util.getSchoolDaoInstance().getAllSchools();
        
        if(list !=null)
        {
            hd.startDocument();
            hd.startElement("","",parentTag,atts);
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              for(int k=0; k<loopCount; k++)
              {
                    for(int j=startSize; j<list.size(); j++)
                    {
                        if(j>((k+1)*4999))
                        break;
                      school=(School)list.get(j);
                      String id=getPropertyValue(school.getId());
                      String orgUnitId=getPropertyValue(school.getOrgUnitId());
                      String schoolName=getPropertyValue(school.getSchoolName());
                      String recordedBy=getPropertyValue(school.getRecordedBy());
                      String schoolType=getIntegerPropertyValue(school.getSchoolType()+"");
                      String markedForDelete=getIntegerPropertyValue(school.getMarkForDelete()+"");
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(school.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(school.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                      
                      String[] fieldValues={id,orgUnitId,schoolName,schoolType,recordedBy,dateCreated,lastModifiedDate,markedForDelete};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }

                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    if(k>0)
                    fileName=parentFolder+k+".xml";

                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                }
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
public static int exportSchoolGradeRecordsInXml(String parentFolderPath,List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    DaoUtility util=new DaoUtility();
    fileSeperator=appUtil.getFileSeperator();
    
    String parentTag="SchoolGrades";
    String fileName="";
    String parentFolder="SchoolGrade";
    
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        SchoolGrade grade=null;
	StringWriter sw=new StringWriter();
	StreamResult streamResult = new StreamResult(sw);
	SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

	TransformerHandler hd = tf.newTransformerHandler();
	Transformer serializer = hd.getTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING,isoEncoding);

	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
	hd.setResult(streamResult);
	AttributesImpl atts = new AttributesImpl();
                
        String[] columnNames={"id","gradeName","gradeLevel","recordedBy","dateCreated","lastModifiedDate","markedForDelete"};
        atts.clear();
        if(list==null)
        list=util.getSchoolGradeDaoInstance().getAllSchoolGrades();
        
        if(list !=null)
        {
            hd.startDocument();
            hd.startElement("","",parentTag,atts);
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              for(int k=0; k<loopCount; k++)
              {
                    for(int j=startSize; j<list.size(); j++)
                    {
                        if(j>((k+1)*4999))
                        break;
                      grade=(SchoolGrade)list.get(j);
                      String id=getPropertyValue(grade.getId());
                      String gradeName=getPropertyValue(grade.getGradeName());
                      String recordedBy=getPropertyValue(grade.getRecordedBy());
                      String gradeLevel=getIntegerPropertyValue(grade.getGradeLevel()+"");
                      String markedForDelete=getIntegerPropertyValue(grade.getMarkForDelete()+"");
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(grade.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(grade.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                      
                      String[] fieldValues={id,gradeName,gradeLevel,recordedBy,dateCreated,lastModifiedDate,markedForDelete};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }

                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    if(k>0)
                    fileName=parentFolder+k+".xml";

                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                }
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
public static int exportPartnerRecordsInXml(String parentFolderPath,List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    DaoUtility util=new DaoUtility();
    fileSeperator=appUtil.getFileSeperator();
    String parentTag="Partners";
    String fileName="";
    String parentFolder="Partner";
    
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        Partner partner=null;
	StringWriter sw=new StringWriter();
	StreamResult streamResult = new StreamResult(sw);
	SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

	TransformerHandler hd = tf.newTransformerHandler();
	Transformer serializer = hd.getTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING,isoEncoding);

	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
	hd.setResult(streamResult);
	AttributesImpl atts = new AttributesImpl();
                
        String[] columnNames={"partnerCode","partnerName","dateCreated","lastModifiedDate","datimId"};
        if(list ==null)
        atts.clear();
        list=util.getPartnerDaoInstance().getAllPartners();
        if(list ==null)
        list=new ArrayList();
        if(list !=null)
        {
            hd.startDocument();
            hd.startElement("","",parentTag,atts);
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              for(int k=0; k<loopCount; k++)
              {
                    for(int j=startSize; j<list.size(); j++)
                    {
                        if(j>((k+1)*4999))
                        break;
                      partner=(Partner)list.get(j);
                      String partnerCode=getPropertyValue(partner.getPartnerCode());
                      String partnerName=getPropertyValue(partner.getPartnerName());
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(partner.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(partner.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                      String datimId=getPropertyValue(partner.getDatimId());
                      
                      String[] fieldValues={partnerCode,partnerName,dateCreated,lastModifiedDate,datimId};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }

                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    if(k>0)
                    fileName=parentFolder+k+".xml";

                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                }
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
public static int exportReferralFacilityRecordsInXml(String parentFolderPath,List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    DaoUtility util=new DaoUtility();
    fileSeperator=appUtil.getFileSeperator();
    
    String parentTag="ReferralFacilities";
    String fileName="";
    String parentFolder="ReferralFacility";
    
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        ReferralFacility rf=null;
	String[] columnNames={"facilityId","facilityName","typeOfFacility","address","contactEmail","contactPhone","nameOfContactPerson","organizationUnitId","latitude","longitude","dateCreated","lastModifiedDate","recordedBy","datimId"};
        if(list==null)
        list=util.getReferralFacilityDaoInstance().getAllReferralFacilities();
        
        if(list !=null)
        {
            
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              //for(int k=0; k<loopCount; k++)
              //{
                  StringWriter sw=new StringWriter();
                StreamResult streamResult = new StreamResult(sw);
                SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
                TransformerHandler hd = tf.newTransformerHandler();
                Transformer serializer = hd.getTransformer();
                serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
                serializer.setOutputProperty(OutputKeys.INDENT,"yes");
                hd.setResult(streamResult);
                AttributesImpl atts = new AttributesImpl();
                  hd.startDocument();
                  hd.startElement("","",parentTag,atts);
                    for(int j=startSize; j<list.size(); j++)
                    {
                        /*if(j>((k+1)*4999))
                        break;*/
                      rf=(ReferralFacility)list.get(j);
                      String address=getPropertyValue(rf.getAddress());
                      String contactEmail=getPropertyValue(rf.getContactEmail());
                      String contactPhone=getPropertyValue(rf.getContactPhone());
                      String facilityId=getPropertyValue(rf.getFacilityId());
                      String facilityName=getPropertyValue(rf.getFacilityName());
                      String nameOfContactPerson=getPropertyValue(rf.getNameOfContactPerson());
                      String organizationUnitId=getPropertyValue(rf.getOrganizationUnitId());
                      String datimId=getPropertyValue(rf.getDatimId());
                      String latitude=getIntegerPropertyValue(rf.getLatitude()+"");
                      String longitude=getIntegerPropertyValue(rf.getLongitude()+"");
                      String typeOfFacility=getIntegerPropertyValue(rf.getTypeOfFacility()+"");
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(rf.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(rf.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                      String recordedBy=getPropertyValue(rf.getRecordedBy());
                      
                      String[] fieldValues={facilityId,facilityName,typeOfFacility,address,contactEmail,contactPhone,nameOfContactPerson,organizationUnitId,latitude,longitude,dateCreated,lastModifiedDate,recordedBy,datimId};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }
                    //startSize=count;
                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    //if(k>0)
                    //fileName=parentFolder+k+".xml";

                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                //}
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
public static int exportVulnerabilityStatusRecordsInXml(String parentFolderPath,List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    DaoUtility util=new DaoUtility();
    fileSeperator=appUtil.getFileSeperator();
    
    String parentTag="VulnerabilityStatusRecords";
    String fileName="";
    String parentFolder="VulnerabilityStatus";
    
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        VulnerabilityStatus vs=null;
	StringWriter sw=new StringWriter();
	StreamResult streamResult = new StreamResult(sw);
	SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

	TransformerHandler hd = tf.newTransformerHandler();
	Transformer serializer = hd.getTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING,isoEncoding);

	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
	hd.setResult(streamResult);
	AttributesImpl atts = new AttributesImpl();
                
        String[] columnNames={"vulnerabilityStatusId","vulnerabilityStatusName","recordedBy","markedForDelete","vsEnabled","dateCreated","lastModifiedDate","vsCategory"};
        atts.clear();
        
        if(list==null)
        list=util.getVulnerabilityStatusDaoInstance().getAllVulnerabilityStatus();
        if(list ==null)
        list=new ArrayList();
        if(list !=null)
        {
            hd.startDocument();
            hd.startElement("","",parentTag,atts);
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              for(int k=0; k<loopCount; k++)
              {
                    for(int j=startSize; j<list.size(); j++)
                    {
                        if(j>((k+1)*4999))
                        break;
                      vs=(VulnerabilityStatus)list.get(j);
                      String vulnerabilityStatusId=getPropertyValue(vs.getVulnerabilityStatusId());
                      String vulnerabilityStatusName=getPropertyValue(vs.getVulnerabilityStatusName());
                      String recordedBy=getPropertyValue(vs.getRecordedBy());
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(vs.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(vs.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                      String markedForDelete=getIntegerPropertyValue(vs.getMarkedForDelete()+"");
                      String vsEnabled=getIntegerPropertyValue(vs.getVsEnabled()+"");
                      String vsCategory=getIntegerPropertyValue(vs.getVsCategory()+"");
                      
                      String[] fieldValues={vulnerabilityStatusId,vulnerabilityStatusName,recordedBy,markedForDelete,vsEnabled,dateCreated,lastModifiedDate,vsCategory};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }

                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    if(k>0)
                    fileName=parentFolder+k+".xml";

                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                }
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
public static int exportBeneficiaryServiceRecordsInXml(String parentFolderPath,List list) throws Exception
{
    AppUtility appUtil=new AppUtility();
    DaoUtility util=new DaoUtility();
    fileSeperator=appUtil.getFileSeperator();
    
    String parentTag="BeneficiaryServiceRecords";
    String fileName="";
    String parentFolder="BeneficiaryService";
    
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        Service bs=null;
	StringWriter sw=new StringWriter();
	StreamResult streamResult = new StreamResult(sw);
	SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

	TransformerHandler hd = tf.newTransformerHandler();
	Transformer serializer = hd.getTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING,isoEncoding);

	serializer.setOutputProperty(OutputKeys.INDENT,"yes");
	hd.setResult(streamResult);
	AttributesImpl atts = new AttributesImpl();
                
        String[] columnNames={"serviceId","serviceCode","serviceName","beneficiaryType","domainId","serviceValue","displayStatus","cascadableEndAge","cascadableStartAge","dateCreated","lastModifiedDate","recordedBy"};
        atts.clear();
        
        if(list==null)
        list=util.getBeneficiaryServiceDaoInstance().getAllBeneficiaryServices();
        
        if(list !=null)
        {
            hd.startDocument();
            hd.startElement("","",parentTag,atts);
            startSize=count;
            System.err.println("startSize is "+startSize);
            double loopCount=Math.ceil((list.size()/5000d));
              for(int k=0; k<loopCount; k++)
              {
                    for(int j=startSize; j<list.size(); j++)
                    {
                        if(j>((k+1)*4999))
                        break;
                      bs=(Service)list.get(j);
                      String serviceId=getPropertyValue(bs.getServiceId());
                      String serviceCode=getPropertyValue(bs.getServiceCode());
                      String serviceName=getPropertyValue(bs.getServiceName());
                      String beneficiaryType=getIntegerPropertyValue(bs.getBeneficiaryType()+"");
                      String domainId=getPropertyValue(bs.getDomainId());
                      String serviceValue=getPropertyValue(bs.getServiceValue());
                      String displayStatus=getIntegerPropertyValue(bs.getDisplayStatus()+"");
                      String cascadableEndAge=getIntegerPropertyValue(bs.getCascadableEndAge()+"");
                      String cascadableStartAge=getIntegerPropertyValue(bs.getCascadableStartAge()+"");
                      String recordedBy=getPropertyValue(bs.getRecordedBy());
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(bs.getDateCreated(),DateManager.DB_DATE_FORMAT));
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(bs.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                      
                      
                      String[] fieldValues={serviceId,serviceCode,serviceName,beneficiaryType,domainId,serviceValue,displayStatus,cascadableEndAge,cascadableStartAge,dateCreated,lastModifiedDate,recordedBy};
                      hd.startElement("","",parentFolder,atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                      hd.endElement("","",parentFolder);
                      count++;
                    }

                    hd.endElement("","",parentTag);
                    hd.endDocument();
                    String xmlString = sw.toString();
                    fileName=parentFolder+".xml";
                    if(k>0)
                    fileName=parentFolder+k+".xml";

                    //String exportDestination=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                    if(parentFolderPath ==null)//parentFolderPath
                    parentFolderPath=appUtil.getMetadataExportFilePath()+fileSeperator+parentFolder;
                    appUtil.createDirectories(parentFolderPath);
                    
                    String exportDestination=parentFolderPath+fileSeperator+fileName;
                    
                    File file = new File(exportDestination);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    bw.write(xmlString);
                    bw.flush();
                    bw.close();
                }
            }
        }
       catch(Exception ex)
       {
		ex.printStackTrace();
       }
      return noOfRecords;
}
private static String getPropertyValue(String propertyValue)
{
    if(propertyValue==null || propertyValue.equals(" ") || propertyValue.equals(""))
     propertyValue="none";
     propertyValue=propertyValue.trim();
    return propertyValue;
}
private static String getIntegerPropertyValue(String propertyValue)
{
    if(propertyValue==null || propertyValue.equals(" ") || propertyValue.equals(""))
     propertyValue="0";
     propertyValue=propertyValue.trim();
    return propertyValue;
}
public static String exportMetadata(String parentFolderPath,List list) throws Exception
{
    ZipHandler zipHandler=new ZipHandler();
    AppUtility appUtil=new AppUtility();
    MetadataExportManager.exportCBORecordsInXml(parentFolderPath, list);
    MetadataExportManager.exportCommunityWorkerRecordsInXml(parentFolderPath, list);
    MetadataExportManager.exportOrganizationUnitRecordsInXml(parentFolderPath,list);
    MetadataExportManager.exportPartnerRecordsInXml(parentFolderPath, list);
    MetadataExportManager.exportReferralFacilityRecordsInXml(parentFolderPath, list);
    MetadataExportManager.exportSchoolGradeRecordsInXml(parentFolderPath, list);
    MetadataExportManager.exportSchoolRecordsInXml(parentFolderPath, list);
    MetadataExportManager.exportVulnerabilityStatusRecordsInXml(parentFolderPath, list);
    MetadataExportManager.exportBeneficiaryServiceRecordsInXml(parentFolderPath, list); 
    
    
    List exportFileList=appUtil.copyFilePathsIntoList(appUtil.getMetadataExportFilePath());
    String zipFilPath=zipHandler.zipFiles(exportFileList, appUtil.getDbExportZipDirectory(), getMetaDataZipFileName());
    return zipFilPath;
}
public static String getMetaDataZipFileName()
{
    String currentDateAndTime=DateManager.getDefaultCurrentDateAndTime();
    if(currentDateAndTime !=null)
    {
        currentDateAndTime=currentDateAndTime.replace(":", "");
        currentDateAndTime=currentDateAndTime.replace(";", "");
        currentDateAndTime=currentDateAndTime.replace("\\", "");
        currentDateAndTime=currentDateAndTime.replace("/", "");
    }
    String zipFileName="metadata_"+currentDateAndTime;
    return zipFileName;
}
}
