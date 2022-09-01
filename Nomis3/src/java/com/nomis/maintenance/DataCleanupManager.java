/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.exportimport.ZipHandler;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.QuarterlyStatusTracker;
import com.nomis.ovc.business.VulnerabilityStatus;
import com.nomis.ovc.dao.AdultHouseholdMemberDao;
import com.nomis.ovc.dao.ChildEnrollmentDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.HouseholdEnrollmentDao;
import com.nomis.ovc.dao.QuarterlyStatusTrackerDao;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.TaskManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author smomoh
 */
public class DataCleanupManager 
{
    DaoUtility util=new DaoUtility();
    AppUtility appUtil=new AppUtility();
    
    String userName=null;
public DataCleanupManager()
{
    ZipHandler zipHandler;
    appUtil=new AppUtility();
    AppUtility.getResourceLocation();
    String destinationDirectory=appUtil.getExportFilePath();
    
}//Adolescent HIV prevention and sexual reproductive health services
public int updateOvcEnrollmentStatusWithHouseholdEnrollmentStatus()
{
    int count=0;
    try
    {
        QuarterlyStatusTrackerDao qstdao=util.getQuarterlyStatusTrackerDaoInstance();
        List communityList=util.getHouseholdEnrollmentDaoInstance().getDistinctCommunityCodesFromHouseholdsExitedFromProgram(AppConstant.OVC_TYPE_NUM);
        if(communityList !=null && !communityList.isEmpty())
        {
            List list=null;
            
            for(int i=0; i<communityList.size(); i++)
            {
                list=util.getHouseholdEnrollmentDaoInstance().getOvcWhoseHouseholdsAreExitedFromTheProgram(communityList.get(i).toString());
                List qstList=null;
                if(list !=null && !list.isEmpty())
                {
                    ChildEnrollmentDao dao=util.getChildEnrollmentDaoInstance();
                    HouseholdEnrollment hhe=null;
                    Ovc ovc=null;
                    for(Object obj:list)
                    {
                        Object[] objArray=(Object[])obj;
                        hhe=(HouseholdEnrollment)objArray[0];
                        ovc=(Ovc)objArray[1];
                        ovc.setCurrentEnrollmentStatus(hhe.getCurrentEnrollmentStatus());
                        ovc.setDateOfCurrentEnrollmentStatus(hhe.getDateOfCurrentStatus());
                        dao.updateOvcOnly(ovc);
                        qstList=qstdao.getQuarterlyStatusTrackerAfterStatusDate(ovc.getOvcId(), ovc.getDateOfCurrentEnrollmentStatus());
                        if(qstList !=null && !qstList.isEmpty())
                        {
                            for(Object qstObj:qstList)
                            {
                                QuarterlyStatusTracker qst=(QuarterlyStatusTracker)qstObj;
                                qst.setEnrollmentStatus(ovc.getCurrentEnrollmentStatus());
                                qstdao.updateQuarterlyStatusTrackerByRecordId(qst);
                            }
                        }
                        count++;
                    }
                }
            }
        }
        communityList=util.getHouseholdEnrollmentDaoInstance().getDistinctCommunityCodesFromHouseholdsExitedFromProgram(AppConstant.CAREGIVER_TYPE_NUM);
        if(communityList !=null && !communityList.isEmpty())
        {
            List qstList=null;
            List list=util.getHouseholdEnrollmentDaoInstance().getAdultHouseholdMembersWhoseHouseholdsAreExitedFromTheProgram(userName);
            if(list !=null && !list.isEmpty())
            {
                AdultHouseholdMemberDao ahmdao=util.getAdultHouseholdMemberDaoInstance();
                HouseholdEnrollment hhe=null;
                AdultHouseholdMember ahm=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ahm=(AdultHouseholdMember)objArray[1];
                    ahm.setCurrentEnrollmentStatus(hhe.getCurrentEnrollmentStatus());
                    ahm.setDateOfCurrentEnrollmentStatus(hhe.getDateOfCurrentStatus());
                    ahmdao.updateAdultHouseholdMember(ahm);
                    qstList=qstdao.getQuarterlyStatusTrackerAfterStatusDate(ahm.getBeneficiaryId(), ahm.getDateOfCurrentEnrollmentStatus());
                    if(qstList !=null && !qstList.isEmpty())
                    {
                        for(Object qstObj:qstList)
                        {
                            QuarterlyStatusTracker qst=(QuarterlyStatusTracker)qstObj;
                            qst.setEnrollmentStatus(ahm.getCurrentEnrollmentStatus());
                            qstdao.updateQuarterlyStatusTrackerByRecordId(qst);
                        }
                    }
                    count++;
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return count;
}
public static String correctServiceCodeForAdolescentHIVPrevention()
{
    String message="";
    try
    {
        DaoUtility util=new DaoUtility();
        int count=0;
        List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
        List filteredLevel4OuInDataList=getLevel4OuInDataButNotInOrganizationUnitTable(level4OuList);
        if(filteredLevel4OuInDataList !=null)
        {
            String level4OuId=null;
            List list=null;
            for(Object obj:filteredLevel4OuInDataList)
            {
                level4OuId=(String)obj;
                list=util.getChildServiceDaoInstance().getListOfServicesByDomainAndServiceTypeCommunityAndAgeLimit(level4OuId,AppConstant.HEALTH_DOMAIN,"Adolescent HIV prevention and sexual reproductive health services", 100);
                count++;
                
            }
            //TaskManager.setOrganizationUnitProcessActive(false);
        }
        message=count+" new organization units created";
    }
    catch(Exception ex)
    {
        TaskManager.setOrganizationUnitProcessActive(false);
        ex.printStackTrace();
    }
    return message;
}
public static String updateChildEnrollmentRecordsForOVCOfferedClients()
{
    String message="";
    if(TaskManager.isFacilityOvcOfferProcessActive())
    {
        return message="Unable to process facility OVC offer requests. Similar process is active";
    }
            TaskManager.setFacilityOvcOfferProcessActive(true);
    try
    {
        DaoUtility util=new DaoUtility();
        int count=util.getFacilityOvcOfferDaoInstance().updateChildEnrollmentRecordsForOVCOfferedClients();
        message=count+" OVC records updated with OVC offer information";
    }
    catch(Exception ex)
    {
        TaskManager.setFacilityOvcOfferProcessActive(false);
        ex.printStackTrace();
    }
    return message;
}
public static String correctOrganizationUnitsInDataButNotInOuMetadata()
{
    String message="";
    try
    {
        DaoUtility util=new DaoUtility();
        int count=0;
        List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
        List filteredLevel4OuInDataList=getLevel4OuInDataButNotInOrganizationUnitTable(level4OuList);
        if(filteredLevel4OuInDataList !=null)
        {
            if(TaskManager.isOrganizationUnitProcessActive())
            {
                return message="Unable to create temporary organization units. Similar process is active";
            }
            TaskManager.setOrganizationUnitProcessActive(true);
            String level4OuId=null;
            OrganizationUnit level4Ou=null;
            
            for(Object obj:filteredLevel4OuInDataList)
            {
                level4OuId=(String)obj;
                level4Ou=util.getOrganizationUnitDaoInstance().createTemporaryLevel4OrganizationUnit(level4OuId,null);
                count++;
                if(level4Ou !=null)
                System.err.println("level4Ou name at "+count+" is "+level4Ou.getName()+" OuCode is "+level4Ou.getOucode()+" ouLevel is "+level4Ou.getOulevel()+" OuPath is "+level4Ou.getOuPath());
                
            }
            TaskManager.setOrganizationUnitProcessActive(false);
        }
        message=count+" new organization units created";
    }
    catch(Exception ex)
    {
        TaskManager.setOrganizationUnitProcessActive(false);
        ex.printStackTrace();
    }
    return message;
}

private static List getLevel4OuInDataButNotInOrganizationUnitTable(List level4OuInDataList)
{
    List mainList=new ArrayList();
    try
    {
        DaoUtility util=new DaoUtility();
        List list=util.getOrganizationUnitDaoInstance().getDistinctOrganizationUnitsByOuLevel(4);
        if(list !=null && !list.isEmpty() && level4OuInDataList !=null && !level4OuInDataList.isEmpty())
        {
            String dataLevel4OuId=null;
            for(Object obj:level4OuInDataList)
            {
                dataLevel4OuId=(String)obj;
                if(!list.contains(dataLevel4OuId))
                {
                    mainList.add(dataLevel4OuId);
                    //System.err.println(dataLevel4OuId+" does not exist");
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return mainList;
}
public static String updateMainEnrollmentStreams()
{
    String message=null;
    try
    {
        DaoUtility util=new DaoUtility();
        String level4OuId=null;
        List ovcList=null;
        Ovc ovc=null;
        String mainEnrollmentStream=null;
        List level4OuIdList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
        int count=0;
        for(Object obj:level4OuIdList)
        {
            level4OuId=(String)obj;
            ovcList=util.getChildEnrollmentDaoInstance().getListOfOvcWithoutMainEnrollmentStreamByLevel4OrganizationUnit(level4OuId);
            if(ovcList !=null && !ovcList.isEmpty())
            {
                for(Object ovcObj:ovcList)
                {
                    ovc=(Ovc)ovcObj;
                    //System.err.println("level4OuId OU Id is "+level4OuId+" and ovc.getVulnerabilityStatusCode() at "+count+" is "+ovc.getVulnerabilityStatusCode());
                    //count++;
                    mainEnrollmentStream=getMainEnrollmentStream(ovc.getVulnerabilityStatusCode());
                    
                    if(mainEnrollmentStream !=null)
                    {
                        ovc.setMainVulnerabilityStatusCode(mainEnrollmentStream);
                        util.getChildEnrollmentDaoInstance().updateOvcOnly(ovc);
                        count++;
                        System.err.println(count+" mainEnrollmentStreams with id "+mainEnrollmentStream+" and ovc.getMainVulnerabilityStatusCode() "+ovc.getMainVulnerabilityStatusCode()+" updated");
                    }
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return message;
}
public static String getMainEnrollmentStream(String enrollmentStreams)
{
    String mainEnrollmentStream=null;
    if(enrollmentStreams !=null)
    {
        String[] mainEnrollmentStreamIds=getMainVulnerabilityStatusIds();
        for(int i=0; i<mainEnrollmentStreamIds.length; i++)
        {
            if(enrollmentStreams.indexOf(mainEnrollmentStreamIds[i]) !=-1)
            {
                mainEnrollmentStream=mainEnrollmentStreamIds[i];
                break;
            } 
             if(i==mainEnrollmentStreamIds.length-1)
             {
                if(mainEnrollmentStream==null || mainEnrollmentStream.trim().length()==0)
                {
                    if(enrollmentStreams.indexOf(";") !=-1)
                    enrollmentStreams=enrollmentStreams.replace(";", "_");
                    if(enrollmentStreams.indexOf(",") !=-1)
                    enrollmentStreams=enrollmentStreams.replace(",", "_");
                    if(enrollmentStreams.indexOf(":") !=-1)
                    enrollmentStreams=enrollmentStreams.replace(":", "_");
                    if(enrollmentStreams.indexOf("_") !=-1)
                    {
                        mainEnrollmentStream=enrollmentStreams.substring(0, enrollmentStreams.indexOf("_"));
                    }
                    else
                    mainEnrollmentStream=enrollmentStreams;
                }
             }
        } 
    }
    return mainEnrollmentStream;
}
public static String[] getMainVulnerabilityStatusIds()
{
   //List list=new ArrayList();
   String[] vsArray={"CtSjUaWsR6f","7GsA4HTMfKd","YJ9M2OgimWh","oedL40sUppl","9sUkWiHAwxJ","mmgEnWjxA6u","Vua0Mu0iPhc","uX8awocLeWY","Kjd0GFDW0Na","pyRkx3Sr59I"};
   return vsArray;
}
public static String[] getMainVulnerabilityStatusNames()
{
   //List list=new ArrayList();
   String[] vsArray=getMainVulnerabilityStatusIds();
   try
   {
       DaoUtility util=new DaoUtility();
       if(vsArray !=null && vsArray.length>0)
       {
           VulnerabilityStatus vs=null;
           for(int i=0; i<vsArray.length; i++)
           {
               vs=(VulnerabilityStatus)util.getVulnerabilityStatusDaoInstance().getVulnerabilityStatus(vsArray[i]);
               if(vs !=null)
               System.err.println("VS ID is "+vsArray[i]+" and name is "+vs.getVulnerabilityStatusName());
           }
       }
   }
   catch(Exception ex)
   {
       ex.printStackTrace();
   }
   return vsArray;
}//
/*public OrganizationUnit generateOuName(OrganizationUnit ou)
{
    try
    {
        DaoUtility util=new DaoUtility();
        if(ou !=null)
        {
            OrganizationUnit ouByName=null;
            String ouName=ou.getName();
            for(int i=0; i<1000; i++)
            {
                if(i==0)
                {
                    ouByName=util.getOrganizationUnitDaoInstance().getOrganizationUnitByName(ouName);
                }
                else
                {
                    ouByName=util.getOrganizationUnitDaoInstance().getOrganizationUnitByName(ouName+i);
                }
                if(ouByName !=null)
                {
                    continue;
                }
                else
                {
                    if(i==0)
                    ou.setName(ouName);
                    else
                    ou.setName(ouName+i);
                    break;
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return ou;
}*/
public static String updateNutritionStatusWithAgeHeightAndWeight()
{
    String message=null;
    try
    {
        DaoUtility util=new DaoUtility();
        int count=util.getNutritionalAssessmentDaoInstance().updateNutritionStatusWithAgeHeightAndWeight();
        message=count+" Nutrition status records updated";
        System.err.println(message);
    }
    catch(Exception ex)
    {
        NomisLogManager.logStackTrace(ex); 
    }
    return message;
}
public static String updateBeneficiaryARTInformationFromCareAndSupport()
{
    int count=0;
    String message=null;
    try
    {
        DaoUtility util=new DaoUtility();
        List ovcList=util.getCareAndSupportChecklistDaoInstance().getCareAndSupportRecordsWithNullOvcTreatmentStatus();
        if(ovcList !=null && !ovcList.isEmpty())
        {
            Ovc ovc=null;
            for(Object obj:ovcList)
            {
                ovc=(Ovc)obj;
                util.getChildEnrollmentDaoInstance().updateOvcOnly(ovc);
                count++;
            }
        }
        List adultList=util.getCareAndSupportChecklistDaoInstance().getCareAndSupportRecordsWithNullAdultHouseholdMemberTreatmentStatus();
        if(adultList !=null && !adultList.isEmpty())
        {
            AdultHouseholdMember ahm=null;
            for(Object obj:adultList)
            {
                ahm=(AdultHouseholdMember)obj;
                util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                count++;
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return count+" beneficiary HIV treatment information updated";
}
public List importHouseholdEnrollmentRecordsFromXML(String destinationDirectory)
{
    AppUtility appUtil=new AppUtility();
    List list=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="HouseholdRecordsForDelete";
    AppUtility.setCurrentImportRecordName("Household enrollment");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new Household enrollment records saved");
    duplicateRecordsList.add("Number of Household enrollment records saved as updates");
    
    int numberSaved=0;
    int numberUpdated=0;
    int count=0;//destinationDirectory
    //String filePath=appUtil.getExportFilePath()+fileSeperator+exportFileName+fileSeperator;
    String filePath=destinationDirectory+fileSeperator+exportFileName+fileSeperator;
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
                    HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
                    HouseholdEnrollment hhe=null;
                    HouseholdEnrollment existinghhe=null;
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
                        NodeList listOfObjects = doc.getElementsByTagName("Household");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            hhe=new HouseholdEnrollment();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {
                                String hhUniqueId=getNodeValue("hhUniqueId",s,listOfObjects);
                                //markedForDelete value of 9 means this has been approved for delete
                                int markedForDelete=Integer.parseInt(getNodeValue("markedForDelete",s,listOfObjects));
                                count++;
                                System.err.println("markedForDelete at "+count+" is "+markedForDelete);
                                if(markedForDelete==9)
                                {                                                                
                                    existinghhe=hhedao.getHouseholdEnrollment(hhUniqueId);
                                    if(existinghhe !=null)
                                    {
                                        if(existinghhe.getMarkedForDelete()==1)
                                        {
                                            hhedao.deleteHouseholdEnrollment(existinghhe);
                                            count++;
                                            System.err.println("Household record "+count+" deleted");
                                        }
                                    }
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
public List importAdultHouseholdMembersRecordsFromXML(String destinationDirectory)
{
    AppUtility appUtil=new AppUtility();
    List list=new ArrayList();
    List householdList=new ArrayList();
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="AdultHouseholdMemberRecordsForDelete";
    AppUtility.setCurrentImportRecordName("Adult Household Members");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new Adult Household Members records saved");
    duplicateRecordsList.add("Number of Adult Household Members records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    int count=0;
    
    String filePath=destinationDirectory+fileSeperator+exportFileName+fileSeperator;
    System.err.println("filePath is "+filePath);
    //String filePath=appUtil.getExportFilePath()+fileSeperator+exportFileName+fileSeperator;
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
                    AdultHouseholdMemberDao ahmdao=util.getAdultHouseholdMemberDaoInstance();
                    AdultHouseholdMember ahm=null;
                    AdultHouseholdMember existingAhm=null;
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
                        NodeList listOfObjects = doc.getElementsByTagName("AdultHouseholdMember");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            //ahm=new AdultHouseholdMember();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {
                                String beneficiaryId=getNodeValue("beneficiaryId",s,listOfObjects);
                                //markedForDelete value of 9 means this has been approved for delete
                                int markedForDelete=Integer.parseInt(getNodeValue("markedForDelete",s,listOfObjects));
                                count++;
                                System.err.println("markedForDelete at "+count+" is "+markedForDelete);
                                if(markedForDelete==9)
                                {
                                    existingAhm=ahmdao.getAdultHouseholdMember(beneficiaryId);
                                    if(existingAhm !=null)
                                    {
                                        if(existingAhm.getMarkedForDelete()==1)
                                        {
                                            ahmdao.deleteAdultHouseholdMember(existingAhm);
                                            count++;
                                            System.err.println("Adult Household member record "+count+" deleted");
                                        }
                                    }
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
public List importOvcRecordsFromXML(String destinationDirectory)
{
    AppUtility appUtil=new AppUtility();
    List list=new ArrayList();
    
    String fileSeperator=appUtil.getFileSeperator();
    String exportFileName="OvcRecordsForDelete";
    AppUtility.setCurrentImportRecordName("Children");
    List newRecordsList=new ArrayList();
    List duplicateRecordsList=new ArrayList();
    newRecordsList.add("Number of new OVC records saved");
    duplicateRecordsList.add("Number of OVC records saved as updates");
    int numberSaved=0;
    int numberUpdated=0;
    int count=0;
    
    String filePath=destinationDirectory+fileSeperator+exportFileName+fileSeperator;
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
                    ChildEnrollmentDao ovcdao=util.getChildEnrollmentDaoInstance();
                    Ovc ovc=null;
                    Ovc existingOvc=null;
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
                        NodeList listOfObjects = doc.getElementsByTagName("ovc");

                        for(int s=0; s<listOfObjects.getLength() ; s++)
                        {
                            ovc=new Ovc();
                            Node firstNode = listOfObjects.item(s);
                             if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                             {
                                String ovcId=getNodeValue("ovcId",s,listOfObjects);
                                //markedForDelete value of 9 means this has been approved for delete
                                int markedForDelete=Integer.parseInt(getNodeValue("markedForDelete",s,listOfObjects));
                                count++;
                                System.err.println("markedForDelete at "+count+" is "+markedForDelete);
                                if(markedForDelete==9)
                                {
                                    existingOvc=ovcdao.getOvc(ovcId);
                                    if(existingOvc !=null)
                                    {
                                        if(existingOvc.getMarkedForDelete()==1)
                                        {
                                            ovcdao.deleteOvc(existingOvc);
                                            count++;
                                            System.err.println("OVC record "+count+" deleted");
                                        }
                                    }
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
public int importAndProcessDataDeleteRequest(String metadataImportFilePath,HttpServletRequest request)
{
    int success=0;
    //String currentUserName=appUtil.getCurrentUser(request.getSession());
    if(!TaskManager.isDatabaseLocked())
    {  
        try
        {
           TaskManager.setDatabaseLocked(true);
           String destinationDirectory=appUtil.getZipExtractsFilePath(); 
           
           if(metadataImportFilePath !=null)
           {
               File importFile=new File(metadataImportFilePath);
               if(importFile.exists())
               {
                   createDestinationFolderAndUnzipMetadataFile(destinationDirectory,metadataImportFilePath);
                   importHouseholdEnrollmentRecordsFromXML(destinationDirectory);
                   importAdultHouseholdMembersRecordsFromXML(destinationDirectory);
                   importOvcRecordsFromXML(destinationDirectory);
                   
                   success=1;
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
        
    }
    return success;
}
public void createDestinationFolderAndUnzipMetadataFile(String destinationDirectory,String filePath)
{
    appUtil=new AppUtility();
    appUtil.createZipExtractDirectories();
    appUtil.createDirectories(destinationDirectory);
    ZipHandler zipHandler=new ZipHandler();
    zipHandler.unZipFile(filePath,destinationDirectory);
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
    String elementValue="";
    if(getNodeName(value,s,listOfObjects) !=null)
    {
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
private List getFiles(String filePath)
{
    AppUtility appUtil=new AppUtility();
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
public static String resetCurrentHivStatusForPositiveBaselineStatus()
{
    String message="No records found";
    int count=0;
    int riskAssessmentCount=0;
    try
    {
        DaoUtility util=new DaoUtility();
        List list=new ArrayList();
        List positiveList=util.getChildEnrollmentDaoInstance().getRecordsWithPositiveHivStatusAtBaselineButOtherStatusCurrently();
        List negativeList=util.getChildEnrollmentDaoInstance().getRecordsWithKnownBaselineHivStatusButUnknownCurrentHivStatus();
        if(positiveList !=null)
        list.addAll(positiveList);
        if(negativeList !=null)
        list.addAll(negativeList);
        //get records with positive baseline hiv status whose current hiv status is not positive
        list=util.getChildEnrollmentDaoInstance().getRecordsWithPositiveHivStatusAtBaselineButOtherStatusCurrently();
        if(list !=null)
        {
            
            Ovc ovc=null;
            for(Object obj:list)
            {
               ovc=(Ovc)obj; 
               if(ovc.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM && (ovc.getCurrentHivStatus()==AppConstant.HIV_TEST_NOT_INDICATED_NUM || ovc.getCurrentHivStatus()==AppConstant.HIV_TEST_REQUIRED_NUM))
               {
                   //This child was erroneously risk assessed, delete all risk assessment records
                   util.getHivRiskAssessmentDaoInstance().markRiskAssessmentRecordsForDelete(ovc.getOvcId());
                   riskAssessmentCount++;
               }
               ovc.setCurrentHivStatus(ovc.getBaselineHivStatus());
               ovc.setDateOfCurrentHivStatus(ovc.getDateOfBaselineHivStatus());
               ovc.setForUpdateHivStatus(true);
               util.getChildEnrollmentDaoInstance().updateOvcOnly(ovc);
               count++;
               
            }
           message=count+" Children current HIV records corrected and "+riskAssessmentCount+" records with HIV Risk assessment records marked for delete";
           System.err.println(message); 
        }
        List ahmPositiveList=util.getAdultHouseholdMemberDaoInstance().getRecordsWithPositiveHivStatusAtBaselineButOtherStatusCurrently();
        List ahmNegativeList=util.getAdultHouseholdMemberDaoInstance().getRecordsWithKnownBaselineHivStatusButUnknownCurrentHivStatus();
        //get records with positive baseline hiv status whose current hiv status is not positive
        List ahmList=new ArrayList();
        if(ahmPositiveList !=null)
        ahmList.addAll(ahmPositiveList);
        if(ahmNegativeList !=null)
        ahmList.addAll(ahmNegativeList);
        if(ahmList !=null)
        {
            count=0;
            AdultHouseholdMember ahm=null;
            for(Object obj:ahmList)
            {
               ahm=(AdultHouseholdMember)obj; 
               ahm.setCurrentHivStatus(ahm.getBaselineHivStatus());
               ahm.setDateOfCurrentHivStatus(ahm.getDateOfBaselineHivStatus());
               ahm.setForUpdateHivStatus(true);
               util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
               count++;
            }
            message+=" "+count+" Adult member Current HIV records corrected ";
            System.err.println(message);
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return message;
}
public String updateBeneficiaryCurrentAge() throws Exception
{
    String message="";
    int ovcCount=0;
    int adultCount=0;
    DaoUtility util=new DaoUtility();
    List communityList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
    if(communityList !=null && !communityList.isEmpty())
    {
        String communityId=null;
        
        for(Object obj:communityList)
        {
            communityId=(String)obj;
            ovcCount += updateOvcCurrentAge(communityId);
            adultCount += updateAdultCurrentAge(communityId);
        }
    }
    return message=ovcCount+" children current age updated and "+adultCount+" adult curent age updated";
}
public int updateOvcCurrentAge(String communityId) throws Exception
{
    int count=0;
    DaoUtility util=new DaoUtility();
    AppManager appManager=new AppManager();

    List childrenList=null;
    Ovc ovc=null;
    AgeObject ageObj=null;

    childrenList=util.getChildEnrollmentDaoInstance().getListOfOvcByLevel4OrganizationUnit(communityId);
    if(childrenList !=null)
    {
        for(Object childObj:childrenList)
        {
            ovc=(Ovc)childObj;
            ageObj=appManager.getCurrentAge(ovc.getDateOfEnrollment(), ovc.getAgeAtBaseline(), ovc.getAgeUnitAtBaseline());
            if(ageObj.getAge()>ovc.getCurrentAge() || (ageObj.getAgeUnit() !=ovc.getCurrentAgeUnit() && ageObj.getAgeUnit()==AppConstant.AGEUNIT_YEAR_NUM))
            {
                ovc.setCurrentAge(ageObj.getAge());
                ovc.setCurrentAgeUnit(ageObj.getAgeUnit());
                util.getChildEnrollmentDaoInstance().updateOvcOnly(ovc);
                count++;
                System.err.println(count+" children current age updated");
            }
        }
    } 
    return count;
}
public int updateAdultCurrentAge(String communityId) throws Exception
{
    int count=0;
    DaoUtility util=new DaoUtility();
    AppManager appManager=new AppManager();

    List adultList=null;
    AdultHouseholdMember ahm=null;
    AgeObject ageObj=null;

    adultList=util.getAdultHouseholdMemberDaoInstance().getListOfAdultHouseholdMembersByLevel4OrganizationUnit(communityId);
    if(adultList !=null)
    {
        for(Object obj:adultList)
        {
            ahm=(AdultHouseholdMember)obj;
            ageObj=appManager.getCurrentAge(ahm.getDateOfEnrollment(), ahm.getAgeAtBaseline(), ahm.getAgeUnitAtBaseline());
            if(ageObj.getAge()>ahm.getCurrentAge() || (ageObj.getAgeUnit() !=ahm.getCurrentAgeUnit() && ageObj.getAgeUnit()==AppConstant.AGEUNIT_YEAR_NUM))
            {
                ahm.setCurrentAge(ageObj.getAge());
                ahm.setCurrentAgeUnit(ageObj.getAgeUnit());
                util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                count++;
                System.err.println(count+" adult current age updated");
            }
        }
    } 
    return count;
}
}
