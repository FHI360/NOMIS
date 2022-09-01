/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.exportimport.DataEncryption;
import com.nomis.exportimport.XMLDataExportManager;
import com.nomis.exportimport.ZipHandler;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.dao.AdultHouseholdMemberDao;
import com.nomis.ovc.dao.ChildEnrollmentDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.HouseholdEnrollmentDao;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
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
public class DataCleanupScriptGenerator 
{
ZipHandler zipHandler;
    DataEncryption encryptor;
    AppUtility appUtil;
    String fileSeperator="\\";
    DaoUtility util=new DaoUtility();
    List mainList=null;
    XMLDataExportManager xdem=new XMLDataExportManager();
   public DataCleanupScriptGenerator()
   {
      appUtil=new AppUtility();
      fileSeperator=appUtil.getFileSeperator();
      appUtil.createExportImportDirectories();
   }
   private String getPropertyValue(String propertyValue)
{
     if(propertyValue==null || propertyValue.equals(" ") || propertyValue.equals(""))
     propertyValue="none";
     propertyValue=propertyValue.trim();
    return propertyValue;
}
private String getIntegerPropertyValue(String propertyValue)
{
    if(propertyValue==null || propertyValue.equals(" ") || propertyValue.equals(""))
     propertyValue="0";
     propertyValue=propertyValue.trim();
    return propertyValue;
}
public void createDeleteRequestScript(String parentFolderPath,ReportParameterTemplate rpt) throws Exception
{
    xdem.exportAdultHouseholdMemberRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportBeneficiaryStatusUpdateRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportCaregiverAccessToEmergencyFundRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportCareplanAchievementChecklistRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportChildEducationPerformanceAssessmentRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportHivCareAreSupportRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportHivRiskAssessmentRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportHouseholdCareplanRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportHouseholdEnrollmentRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportHouseholdServiceRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportHouseholdVulnerabilityAssessmentRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportNutritionAssessmentRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportOvcRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportOvcServiceRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportReferralRecordsInXml(parentFolderPath, rpt,1);
    xdem.exportRevisedHouseholdVulnerabilityAssessmentRecordsInXml(parentFolderPath, rpt,1);
}
public int createXmlScriptForHouseholdEnrollmentForDelete(String parentFolderPath,ReportParameterTemplate rpt) throws Exception
{
    
    String fileName="";
    String parentFolder="HouseholdRecordsForDelete";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        HouseholdEnrollment hhe=null;
        HouseholdEnrollmentDao dao=util.getHouseholdEnrollmentDaoInstance();
        List list=dao.getHouseholdEnrollmentRecordsMarkedForDelete(rpt);
        if(list !=null)
        {
            noOfRecords=list.size();
        
            double loopCount=Math.ceil((list.size()/5000d));
            for(int k=0; k<loopCount; k++)
            {
                StringWriter sw=new StringWriter();
                StreamResult streamResult = new StreamResult(sw);
                SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
                TransformerHandler hd = tf.newTransformerHandler();
                Transformer serializer = hd.getTransformer();
                serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
                serializer.setOutputProperty(OutputKeys.INDENT,"yes");
                hd.setResult(streamResult);
                AttributesImpl atts = new AttributesImpl();
                
                if(list==null)
                list=new ArrayList();
                String[] columnNames={"hhUniqueId","enrollmentId","dateOfAssessment","partnerCode","cboId","organizationUnit","address","markedForDelete","currentEnrollmentStatus","dateOfCurrentStatus","hhHasCasePlan","dateCasePlanDeveloped","volunteerName","dateCreated","lastModifiedDate","recordedBy"};
                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                  hhe=(HouseholdEnrollment)list.get(j);
                  String hhUniqueId=getPropertyValue(hhe.getHhUniqueId());
                  String enrollmentId=getPropertyValue(hhe.getEnrollmentId());
                  String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(hhe.getDateOfAssessment(),DateManager.DB_DATE_FORMAT));
                  String partnerCode=getPropertyValue(hhe.getPartnerCode());
                  String cboId=getPropertyValue(hhe.getCboId());
                  String organizationUnit=getPropertyValue(hhe.getOrganizationUnit());
                  String address=getPropertyValue(hhe.getAddress());
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(hhe.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                  String markedForDelete=getIntegerPropertyValue(hhe.getMarkedForDelete()+"");
                  String currentEnrollmentStatus=getIntegerPropertyValue(hhe.getCurrentEnrollmentStatus()+"");
                  String dateOfCurrentStatus=getPropertyValue(DateManager.convertDateToString(hhe.getDateOfCurrentStatus(),DateManager.DB_DATE_FORMAT));
                  String hhHasCasePlan=getIntegerPropertyValue(hhe.getHhHasCasePlan()+"");
                  String dateCasePlanDeveloped=getPropertyValue(DateManager.convertDateToString(hhe.getDateCasePlanDeveloped(),DateManager.DB_DATE_FORMAT));
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(hhe.getDateCreated(),DateManager.DB_DATE_FORMAT));
                  String volunteerName=getPropertyValue(hhe.getVolunteerName());
                  String recordedBy=getPropertyValue(hhe.getRecordedBy());

                  String[] fieldValues={hhUniqueId,enrollmentId,dateOfAssessment,partnerCode,cboId,organizationUnit,address,markedForDelete,currentEnrollmentStatus,dateOfCurrentStatus,hhHasCasePlan,dateCasePlanDeveloped,volunteerName,dateCreated,lastModifiedDate,recordedBy};
                  hd.startElement("","","Household",atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                  hd.endElement("","","Household");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                if(parentFolderPath==null)
                parentFolderPath=appUtil.getDeleteScriptsFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
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
           NomisLogManager.logStackTrace(ex);
            ex.printStackTrace();
       }
      return noOfRecords;
}
public int createXmlScriptForAdultHouseholdMemberRecordsForDelete(String parentFolderPath,ReportParameterTemplate rpt) throws Exception
{
    String fileName="";
    String parentFolder="AdultHouseholdMemberRecordsForDelete";
    //appUtil.createExportImportDirectories();
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        AdultHouseholdMember ahm=null;
        AdultHouseholdMemberDao ahmdao=util.getAdultHouseholdMemberDaoInstance();
        List list=ahmdao.getAdultHouseholdMemberRecordsMarkedForDelete(rpt);
        if(list !=null)
        {
            noOfRecords=list.size();
        
            double loopCount=Math.ceil((list.size()/5000d));
            for(int k=0; k<loopCount; k++)
            {
            StringWriter sw=new StringWriter();
            StreamResult streamResult = new StreamResult(sw);
            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

            TransformerHandler hd = tf.newTransformerHandler();
            Transformer serializer = hd.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");

            serializer.setOutputProperty(OutputKeys.INDENT,"yes");
            hd.setResult(streamResult);

            AttributesImpl atts = new AttributesImpl();


            if(list==null)
            list=new ArrayList();
            String[] columnNames={"hhUniqueId","enrollmentId","beneficiaryId","dateOfEnrollment","firstName","surname","sex","phoneNumber","occupation","baselineHivStatus","dateOfBaselineHivStatus","enrolledOnTreatment","dateEnrolledOnTreatment","treatmentId","hivTreatmentFacility","educationLevel","isCaregiver","maritalStatus","currentEnrollmentStatus","dateOfCurrentEnrollmentStatus","currentHivStatus","dateOfCurrentHivStatus","ageAtBaseline","ageUnitAtBaseline","currentAge","currentAgeUnit","dateCreated","lastModifiedDate","recordedBy","markedForDelete"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    ahm=(AdultHouseholdMember)list.get(j);
                  String hhUniqueId=getPropertyValue(ahm.getHhUniqueId());
                  String enrollmentId=getPropertyValue(ahm.getEnrollmentId());
                  String beneficiaryId=getPropertyValue(ahm.getBeneficiaryId());
                  //String nationalId=getPropertyValue(ahm.getNationalId());
                  String dateOfEnrollment=getPropertyValue(DateManager.convertDateToString(ahm.getDateOfEnrollment(), DateManager.DB_DATE_FORMAT));
                  String dateOfBirth=getPropertyValue(DateManager.convertDateToString(ahm.getDateOfBirth(), DateManager.DB_DATE_FORMAT));
                  String firstName=getPropertyValue(ahm.getFirstName());
                  String surname=getPropertyValue(ahm.getSurname());
                  String occupation=getIntegerPropertyValue(ahm.getOccupation()+"");
                  String baselineHivStatus=getIntegerPropertyValue(ahm.getBaselineHivStatus()+"");
                  String treatmentId=getPropertyValue(ahm.getTreatmentId());
                  String hivTreatmentFacility=getPropertyValue(ahm.getHivTreatmentFacilityId());
                  String educationLevel=getIntegerPropertyValue(ahm.getEducationLevel()+"");
                  String isCaregiver=getIntegerPropertyValue(ahm.getIsCaregiver()+"");
                  String maritalStatus=getIntegerPropertyValue(ahm.getMaritalStatus()+"");
                  //String comfortableToDiscloseMemberHivStatus=getIntegerPropertyValue(ahm.getComfortableToDiscloseMemberHivStatus()+"");
                  String currentEnrollmentStatus=getIntegerPropertyValue(ahm.getCurrentEnrollmentStatus()+"");

                  String sex=getPropertyValue(ahm.getSex()); 
                  String phoneNumber=getPropertyValue(ahm.getPhoneNumber());
                  String enrolledOnTreatment=getPropertyValue(ahm.getEnrolledOnTreatment()+"");
                  String dateEnrolledOnTreatment=getPropertyValue(DateManager.convertDateToString(ahm.getDateEnrolledOnTreatment(), DateManager.DB_DATE_FORMAT));
                  String dateOfBaselineHivStatus=getPropertyValue(DateManager.convertDateToString(ahm.getDateOfBaselineHivStatus(), DateManager.DB_DATE_FORMAT));
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(ahm.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                  String dateOfCurrentEnrollmentStatus=getPropertyValue(DateManager.convertDateToString(ahm.getDateOfCurrentEnrollmentStatus(), DateManager.DB_DATE_FORMAT));
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(ahm.getDateCreated(), DateManager.DB_DATE_FORMAT));
                  String recordedBy=getPropertyValue(ahm.getRecordedBy());
                  String markedForDelete=getIntegerPropertyValue(ahm.getMarkedForDelete()+"");
                  String currentHivStatus=getIntegerPropertyValue(ahm.getCurrentHivStatus()+"");
                  String dateOfCurrentHivStatus=getPropertyValue(DateManager.convertDateToString(ahm.getDateOfCurrentHivStatus(),DateManager.DB_DATE_FORMAT));
                  String ageAtBaseline=getIntegerPropertyValue(ahm.getAgeAtBaseline()+"");
                  String ageUnitAtBaseline=getIntegerPropertyValue(ahm.getAgeUnitAtBaseline()+"");
                  String currentAge=getIntegerPropertyValue(ahm.getCurrentAge()+"");
                  String currentAgeUnit=getIntegerPropertyValue(ahm.getCurrentAgeUnit()+"");
                  //String disabled=getIntegerPropertyValue(ahm.getDisabled()+"");

                  String[] fieldValues={hhUniqueId,enrollmentId,beneficiaryId,dateOfEnrollment,firstName,surname,sex,phoneNumber,occupation,baselineHivStatus,dateOfBaselineHivStatus,enrolledOnTreatment,dateEnrolledOnTreatment,treatmentId,hivTreatmentFacility,educationLevel,isCaregiver,maritalStatus,currentEnrollmentStatus,dateOfCurrentEnrollmentStatus,currentHivStatus,dateOfCurrentHivStatus,ageAtBaseline,ageUnitAtBaseline,currentAge,currentAgeUnit,dateCreated,lastModifiedDate,recordedBy,markedForDelete};
                  hd.startElement("","","AdultHouseholdMember",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","AdultHouseholdMember");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                if(parentFolderPath==null)
                parentFolderPath=appUtil.getDeleteScriptsFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
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
public int createXmlScriptForOvcRecordsForDelete(String parentFolderPath,ReportParameterTemplate rpt) throws Exception
{
    String fileName="";
    String parentFolder="OvcRecordsForDelete";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        Ovc ovc=null;
        ChildEnrollmentDao dao=util.getChildEnrollmentDaoInstance();
        List list=dao.getOvcRecordsMarkedForDelete(rpt);
        if(list !=null)
        {
            noOfRecords=list.size();
        
            double loopCount=Math.ceil((list.size()/5000d));
            for(int k=0; k<loopCount; k++)
            {
            StringWriter sw=new StringWriter();
            StreamResult streamResult = new StreamResult(sw);
            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

            TransformerHandler hd = tf.newTransformerHandler();
            Transformer serializer = hd.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");

            serializer.setOutputProperty(OutputKeys.INDENT,"yes");
            hd.setResult(streamResult);

            AttributesImpl atts = new AttributesImpl();


            if(list==null)
            list=new ArrayList();
            String[] columnNames={"hhUniqueId","ovcId","enrollmentId","caregiverId","caregiverRelationship","firstName","surname","sex","phoneNumber","dateOfEnrollment","ageAtBaseline","ageUnitAtBaseline","currentAge","currentAgeUnit","baselineHivStatus","dateOfBaselineHivStatus","currentHivStatus","dateOfCurrentHivStatus","enrolledOnTreatment","dateEnrolledOnTreatment","hivTreatmentFacilityId","treatmentId","vulnerabilityStatusCode","baselineBirthRegistrationStatus","currentBirthRegistrationStatus","dateOfCurrentBirthRegStatus","baselineSchoolStatus","currentSchoolStatus","dateOfCurrentSchoolStatus","schoolName","schoolGrade","weight","height","sourceOfInfo","currentEnrollmentStatus","dateOfCurrentEnrollmentStatus","communityWorkerName","dateCreated","lastModifiedDate","recordedBy","markedForDelete","childHasCasePlan","dateCasePlanDeveloped"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    ovc=(Ovc)list.get(j);
                  String hhUniqueId=getPropertyValue(ovc.getHhUniqueId());
                  String ovcId=getPropertyValue(ovc.getOvcId());
                  String enrollmentId=getPropertyValue(ovc.getEnrollmentId());
                  String caregiverId=getPropertyValue(ovc.getCaregiverId());
                  String caregiverRelationship=getIntegerPropertyValue(ovc.getCaregiverRelationship()+"");
                  String firstName=getPropertyValue(ovc.getFirstName());
                  String surname=getPropertyValue(ovc.getSurname());
                  String dateOfEnrollment=getPropertyValue(DateManager.convertDateToString(ovc.getDateOfEnrollment(), DateManager.DB_DATE_FORMAT));
                  String baselineHivStatus=getIntegerPropertyValue(ovc.getBaselineHivStatus()+"");
                  String dateOfBaselineHivStatus=getPropertyValue(DateManager.convertDateToString(ovc.getDateOfBaselineHivStatus(), DateManager.DB_DATE_FORMAT));
                  String currentHivStatus=getIntegerPropertyValue(ovc.getCurrentHivStatus()+"");
                  String dateOfCurrentHivStatus=getPropertyValue(DateManager.convertDateToString(ovc.getDateOfCurrentHivStatus(), DateManager.DB_DATE_FORMAT));
                  String enrolledOnTreatment=getIntegerPropertyValue(ovc.getEnrolledOnTreatment()+"");
                  String dateEnrolledOnTreatment=getPropertyValue(DateManager.convertDateToString(ovc.getDateEnrolledOnTreatment(), DateManager.DB_DATE_FORMAT));
                  String ageAtBaseline=getIntegerPropertyValue(ovc.getAgeAtBaseline()+"");
                  String ageUnitAtBaseline=getPropertyValue(ovc.getAgeUnitAtBaseline()+"");
                  String currentAge=getIntegerPropertyValue(ovc.getCurrentAge()+"");
                  String currentAgeUnit=getPropertyValue(ovc.getCurrentAgeUnit()+"");
                  String vulnerabilityStatusCode=getPropertyValue(ovc.getVulnerabilityStatusCode());
                  String baselineBirthRegistrationStatus=getIntegerPropertyValue(ovc.getBaselineBirthRegistrationStatus()+"");
                  String currentBirthRegistrationStatus=getIntegerPropertyValue(ovc.getCurrentBirthRegistrationStatus()+"");
                  String baselineSchoolStatus=getIntegerPropertyValue(ovc.getBaselineSchoolStatus()+"");
                  String currentSchoolStatus=getIntegerPropertyValue(ovc.getCurrentSchoolStatus()+"");
                  String dateOfCurrentSchoolStatus=getPropertyValue(DateManager.convertDateToString(ovc.getDateOfCurrentSchoolStatus(), DateManager.DB_DATE_FORMAT));
                  String schoolName=getPropertyValue(ovc.getSchoolName());
                  String schoolGrade=getPropertyValue(ovc.getSchoolGrade());
                  String weight=getIntegerPropertyValue(ovc.getWeight()+"");
                  String height=getIntegerPropertyValue(ovc.getHeight()+"");
                  
                  String sourceOfInfo=getIntegerPropertyValue(ovc.getSourceOfInfo()+"");
                  String hivTreatmentFacilityId=getPropertyValue(ovc.getHivTreatmentFacilityId());
                  String treatmentId=getPropertyValue(ovc.getTreatmentId());
                  String communityWorkerName=getPropertyValue(ovc.getCommunityWorkerName());
                  String sex=getPropertyValue(ovc.getSex()); 
                  String phoneNumber=getPropertyValue(ovc.getPhoneNumber());
                  String currentEnrollmentStatus=getIntegerPropertyValue(ovc.getCurrentEnrollmentStatus()+"");
                  String dateOfCurrentEnrollmentStatus=getPropertyValue(DateManager.convertDateToString(ovc.getDateOfCurrentEnrollmentStatus(), DateManager.DB_DATE_FORMAT));
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(ovc.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(ovc.getDateCreated(), DateManager.DB_DATE_FORMAT));
                  String recordedBy=getPropertyValue(ovc.getRecordedBy());
                  //String currentBirthRegistrationStatus=getIntegerPropertyValue(ovc.getCurrentBirthRegistrationStatus()+"");
                  String dateOfCurrentBirthRegStatus=getPropertyValue(DateManager.convertDateToString(ovc.getDateOfCurrentBirthRegStatus(), DateManager.DB_DATE_FORMAT));
                  String childHasCasePlan=getIntegerPropertyValue(ovc.getChildHasCasePlan()+"");
                  String dateCasePlanDeveloped=getPropertyValue(DateManager.convertDateToString(ovc.getDateCasePlanDeveloped(), DateManager.DB_DATE_FORMAT));;
                  String markedForDelete=getIntegerPropertyValue(ovc.getMarkedForDelete()+"");
                  
                  String[] fieldValues={hhUniqueId,ovcId,enrollmentId,caregiverId,caregiverRelationship,firstName,surname,sex,phoneNumber,dateOfEnrollment,ageAtBaseline,ageUnitAtBaseline,currentAge,currentAgeUnit,baselineHivStatus,dateOfBaselineHivStatus,currentHivStatus,dateOfCurrentHivStatus,enrolledOnTreatment,dateEnrolledOnTreatment,hivTreatmentFacilityId,treatmentId,vulnerabilityStatusCode,baselineBirthRegistrationStatus,currentBirthRegistrationStatus,dateOfCurrentBirthRegStatus,baselineSchoolStatus,currentSchoolStatus,dateOfCurrentSchoolStatus,schoolName,schoolGrade,weight,height,sourceOfInfo,currentEnrollmentStatus,dateOfCurrentEnrollmentStatus,communityWorkerName,dateCreated,lastModifiedDate,recordedBy,markedForDelete,childHasCasePlan,dateCasePlanDeveloped};
                  hd.startElement("","","ovc",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","ovc");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                if(parentFolderPath==null)
                parentFolderPath=appUtil.getDeleteScriptsFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
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
}
