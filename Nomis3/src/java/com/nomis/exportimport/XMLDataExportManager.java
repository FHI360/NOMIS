/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.BeneficiaryStatusUpdate;
import com.nomis.ovc.business.CareAndSupportChecklist;
import com.nomis.ovc.business.CaregiverAccessToEmergencyFund;
import com.nomis.ovc.business.CareplanAchievementChecklist;
import com.nomis.ovc.business.ChildEducationPerformanceAssessment;
import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.business.FacilityOvcOffer;
import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.business.HouseholdCareplan;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.HouseholdReferral;
import com.nomis.ovc.business.HouseholdService;
import com.nomis.ovc.business.HouseholdVulnerabilityAssessment;
import com.nomis.ovc.business.NutritionAssessment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.RevisedHouseholdVulnerabilityAssessment;
import com.nomis.ovc.dao.AdultHouseholdMemberDao;
import com.nomis.ovc.dao.BeneficiaryStatusUpdateDao;
import com.nomis.ovc.dao.CareAndSupportChecklistDao;
import com.nomis.ovc.dao.CaregiverAccessToEmergencyFundDao;
import com.nomis.ovc.dao.CareplanAchievementChecklistDao;
import com.nomis.ovc.dao.ChildEducationPerformanceAssessmentDao;
import com.nomis.ovc.dao.ChildEnrollmentDao;
import com.nomis.ovc.dao.ChildServiceDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.FacilityOvcOfferDao;
import com.nomis.ovc.dao.HivRiskAssessmentDao;
import com.nomis.ovc.dao.HouseholdCareplanDao;
import com.nomis.ovc.dao.HouseholdEnrollmentDao;
import com.nomis.ovc.dao.HouseholdReferralDao;
import com.nomis.ovc.dao.HouseholdServiceDao;
import com.nomis.ovc.dao.HouseholdVulnerabilityAssessmentDao;
import com.nomis.ovc.dao.NutritionAssessmentDao;
import com.nomis.ovc.dao.RevisedHouseholdVulnerabilityAssessmentDao;
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
public class XMLDataExportManager 
{
    ZipHandler zipHandler;
    DataEncryption encryptor;
    AppUtility appUtil;
    String fileSeperator="\\";
    DaoUtility util=new DaoUtility();
    List mainList=null;
   public XMLDataExportManager()
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
private String getExportDirectory(String parentFolderPath,String parentFolder)
{
    String exportDestination=parentFolderPath;
    if(parentFolderPath==null)
    exportDestination=appUtil.getExportFilePath()+fileSeperator+parentFolder;
    else
    exportDestination=parentFolderPath+fileSeperator+parentFolder;
    appUtil.createDirectories(exportDestination);
    return exportDestination; 
}
public int exportHouseholdEnrollmentRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="HouseholdEnrollments";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        HouseholdEnrollmentDao dao=util.getHouseholdEnrollmentDaoInstance();
        List list=dao.getHouseholdEnrollmentRecordsForExport(rpt,markedForDeleteValue);
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
                    HouseholdEnrollment hhe=(HouseholdEnrollment)list.get(j);
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
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
                
                //appUtil.getExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                //if(parentFolderPath !=null)//parentFolderPath
                //exportDestination=parentFolderPath+fileSeperator+fileName;
                
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
public int exportRevisedHouseholdVulnerabilityAssessmentRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    //String parentFolderDirectory=parentFolderPath;
    String parentFolder="RevisedHouseholdVulnerabilityAssessments";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        RevisedHouseholdVulnerabilityAssessment rhva=null;
        RevisedHouseholdVulnerabilityAssessmentDao dao=util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance();
        List list=dao.getRevisedHouseholdEnrollmentRecordsForExport(rpt,markedForDeleteValue);
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
                String[] columnNames={"id","hhUniqueId","dateOfAssessment","hivStatusKnown","hivPositiveLinked","hasViralLoadResult","hivPreventionKnowledge","childUndernourished","childrenHasBirthCertificate","stableAdult","violenceExperienceReported","childrenEnrolledInSchool","regularSchoolAttendance","cgEngagedInEconomicActivities","socialEmotionalSupport","markedForDelete","volunteerName","dateCreated","lastModifiedDate","recordedBy"};
                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                  rhva=(RevisedHouseholdVulnerabilityAssessment)list.get(j);
                  String id=getIntegerPropertyValue(rhva.getId()+"");
                  String hhUniqueId=getPropertyValue(rhva.getHhUniqueId());
                  String hivStatusKnown=getPropertyValue(rhva.getHivStatusKnown()+"");
                  String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(rhva.getDateOfAssessment(),DateManager.DB_DATE_FORMAT));
                  String hivPositiveLinked=getPropertyValue(rhva.getHivPositiveLinked()+"");
                  String hasViralLoadResult=getPropertyValue(rhva.getHasViralLoadResult()+"");
                  String hivPreventionKnowledge=getPropertyValue(rhva.getHivPreventionKnowledge()+"");
                  String childUndernourished=getPropertyValue(rhva.getChildUndernourished()+"");
                  String markedForDelete=getPropertyValue(rhva.getMarkedForDelete()+"");
                  String childrenHasBirthCertificate=getPropertyValue(rhva.getChildrenHasBirthCertificate()+"");
                  String stableAdult=getPropertyValue(rhva.getStableAdult()+"");
                  String violenceExperienceReported=getPropertyValue(rhva.getViolenceExperienceReported()+"");
                  String childrenEnrolledInSchool=getPropertyValue(rhva.getChildrenEnrolledInSchool()+"");
                  String regularSchoolAttendance=getPropertyValue(rhva.getRegularSchoolAttendance()+"");
                  String cgEngagedInEconomicActivities=getPropertyValue(rhva.getCgEngagedInEconomicActivities()+"");
                  String socialEmotionalSupport=getPropertyValue(rhva.getSocialEmotionalSupport()+"");
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(rhva.getDateCreated(),DateManager.DB_DATE_FORMAT));
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(rhva.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                  String volunteerName=getPropertyValue(rhva.getVolunteerName());
                  String recordedBy=getPropertyValue(rhva.getRecordedBy());

                  String[] fieldValues={id,hhUniqueId,dateOfAssessment,hivStatusKnown,hivPositiveLinked,hasViralLoadResult,hivPreventionKnowledge,childUndernourished,childrenHasBirthCertificate,stableAdult,violenceExperienceReported,childrenEnrolledInSchool,regularSchoolAttendance,cgEngagedInEconomicActivities,socialEmotionalSupport,markedForDelete,volunteerName,dateCreated,lastModifiedDate,recordedBy};
                  hd.startElement("","","HouseholdAssessment",atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                  hd.endElement("","","HouseholdAssessment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderDirectory=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderDirectory=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderDirectory);
                String exportDestination=parentFolderDirectory+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
                
                /*String exportDestination=appUtil.getExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                if(parentFolderPath !=null)//parentFolderPath
                exportDestination=parentFolderPath+fileSeperator+fileName;
                appUtil.createDirectories(parentFolderPath);*/
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
public int exportHouseholdVulnerabilityAssessmentRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    //String parentFolderDirectory=parentFolderPath;
    String parentFolder="HouseholdVulnerabilityAssessments";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        HouseholdVulnerabilityAssessment hva=null;
        HouseholdVulnerabilityAssessmentDao dao=util.getHouseholdVulnerabilityAssessmentDaoInstance();
        List list=dao.getHouseholdEnrollmentRecordsForExport(rpt,markedForDeleteValue);
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
                String[] columnNames={"id","hhUniqueId","dateOfAssessment","hhHeadship","health","educationLevel","shelterAndHousing","foodSecurityAndNutrition","meansOfLivelihood","hhIncome","vulnerabilityScore","assessmentNo","markedForDelete","nameOfAssessor","dateCreated","lastModifiedDate","recordedBy"};
                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                  hva=(HouseholdVulnerabilityAssessment)list.get(j);
                  String id=getIntegerPropertyValue(hva.getId()+"");
                  String hhUniqueId=getPropertyValue(hva.getHhUniqueId());
                  String hhHeadship=getPropertyValue(hva.getHhHeadship()+"");
                  String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(hva.getDateOfAssessment(),DateManager.DB_DATE_FORMAT));
                  String health=getPropertyValue(hva.getHealth()+"");
                  String educationLevel=getPropertyValue(hva.getEducationLevel()+"");
                  String shelterAndHousing=getPropertyValue(hva.getShelterAndHousing()+"");
                  String foodSecurityAndNutrition=getPropertyValue(hva.getFoodSecurityAndNutrition()+"");
                  String markedForDelete=getPropertyValue(hva.getMarkedForDelete()+"");
                  String meansOfLivelihood=getPropertyValue(hva.getMeansOfLivelihood()+"");
                  String hhIncome=getPropertyValue(hva.getHhIncome()+"");
                  String vulnerabilityScore=getPropertyValue(hva.getVulnerabilityScore()+"");
                  String assessmentNo=getPropertyValue(hva.getAssessmentNo()+"");
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(hva.getDateCreated(),DateManager.DB_DATE_FORMAT));
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(hva.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                  String nameOfAssessor=getPropertyValue(hva.getNameOfAssessor());
                  String recordedBy=getPropertyValue(hva.getRecordedBy());

                  String[] fieldValues={id,hhUniqueId,dateOfAssessment,hhHeadship,health,educationLevel,shelterAndHousing,foodSecurityAndNutrition,meansOfLivelihood,hhIncome,vulnerabilityScore,assessmentNo,markedForDelete,nameOfAssessor,dateCreated,lastModifiedDate,recordedBy};
                  hd.startElement("","","HouseholdAssessment",atts);
                        for (int i=0;i<columnNames.length;i++)
                        {
                            hd.startElement("","",columnNames[i],atts);
                            hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                            hd.endElement("","",columnNames[i]);
                        }
                  hd.endElement("","","HouseholdAssessment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderDirectory=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderDirectory=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderDirectory);
                String exportDestination=parentFolderDirectory+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportAdultHouseholdMemberRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolderDirectory=parentFolderPath;
    String parentFolder="AdultHouseholdMembers";
    //appUtil.createExportImportDirectories();
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        AdultHouseholdMember ahm=null;
        AdultHouseholdMemberDao ahmdao=util.getAdultHouseholdMemberDaoInstance();
        List list=ahmdao.getAdultHouseholdMemberRecordsForExport(rpt,markedForDeleteValue);
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
            String[] columnNames={"hhUniqueId","enrollmentId","beneficiaryId","dateOfEnrollment","firstName","surname","sex","phoneNumber","occupation","baselineHivStatus","dateOfBaselineHivStatus","enrolledOnTreatment","dateEnrolledOnTreatment","treatmentId","hivTreatmentFacility","educationLevel","isCaregiver","beneficiaryType","maritalStatus","currentEnrollmentStatus","dateOfCurrentEnrollmentStatus","currentHivStatus","dateOfCurrentHivStatus","ageAtBaseline","ageUnitAtBaseline","currentAge","currentAgeUnit","dateCreated","lastModifiedDate","recordedBy","markedForDelete"};

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
                  String beneficiaryType=getIntegerPropertyValue(ahm.getBeneficiaryType()+"");
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

                  String[] fieldValues={hhUniqueId,enrollmentId,beneficiaryId,dateOfEnrollment,firstName,surname,sex,phoneNumber,occupation,baselineHivStatus,dateOfBaselineHivStatus,enrolledOnTreatment,dateEnrolledOnTreatment,treatmentId,hivTreatmentFacility,educationLevel,isCaregiver,beneficiaryType,maritalStatus,currentEnrollmentStatus,dateOfCurrentEnrollmentStatus,currentHivStatus,dateOfCurrentHivStatus,ageAtBaseline,ageUnitAtBaseline,currentAge,currentAgeUnit,dateCreated,lastModifiedDate,recordedBy,markedForDelete};
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
                /*if(parentFolderPath==null)
                parentFolderDirectory=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderDirectory=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderDirectory);
                String exportDestination=parentFolderDirectory+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
                
                /*String exportDestination=appUtil.getExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                if(parentFolderPath !=null)//parentFolderPath
                exportDestination=parentFolderPath+fileSeperator+fileName;
                appUtil.createDirectories(exportDestination);*/
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
public int exportFacilityOvcOfferRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    //String parentFolderDirectory=parentFolderPath;
    String parentFolder="FacilityOvcOffer";
    //appUtil.createExportImportDirectories();
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        FacilityOvcOffer foo=null;
        FacilityOvcOfferDao dao=util.getFacilityOvcOfferDaoInstance();
        List list=dao.getFacilityOvcOfferedRecordsForExport(rpt);
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
            String[] columnNames={"clientUniqueId","level4OuId","treatmentId","clientFirstName","clientSurname","dateEnrolledOnTreatment","hivTreatmentFacilityId","nameOfPersonToShareContactWith"
                    ,"shareContactQuestion","caregiverFirstName","caregiverSurname","dateCaregiverSigned","facilityStaffFirstName","facilityStaffSurname",
                    "dateFacilityStaffSigned","cboId","partnerCode","lastModifiedDate","markedForDelete","age","ageUnit","sex","userName"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    foo=(FacilityOvcOffer)list.get(j);
                  String clientUniqueId=getPropertyValue(foo.getClientUniqueId());
                  String level4OuId=getPropertyValue(foo.getLevel4OuId());
                  String treatmentId=getPropertyValue(foo.getTreatmentId());
                  String clientFirstName=getPropertyValue(foo.getClientFirstName());
                  String clientSurname=getPropertyValue(foo.getClientSurname());
                  String dateEnrolledOnTreatment=getPropertyValue(DateManager.convertDateToString(foo.getDateEnrolledOnTreatment(),DateManager.DB_DATE_FORMAT));
                  String hivTreatmentFacilityId=getPropertyValue(foo.getHivTreatmentFacilityId());
                  String nameOfPersonToShareContactWith=getPropertyValue(foo.getNameOfPersonToShareContactWith());
                  String shareContactQuestion=getIntegerPropertyValue(foo.getShareContactQuestion()+"");
                  String caregiverFirstName=getPropertyValue(foo.getCaregiverFirstName());
                  String caregiverSurname=getPropertyValue(foo.getCaregiverSurname());
                  String dateCaregiverSigned=getPropertyValue(DateManager.convertDateToString(foo.getDateCaregiverSigned(), DateManager.DB_DATE_FORMAT));
                  String facilityStaffFirstName=getPropertyValue(foo.getFacilityStaffFirstName());
                  String facilityStaffSurname=getPropertyValue(foo.getFacilityStaffSurname());
                  String cboId=getPropertyValue(foo.getCboId());
                  String dateFacilityStaffSigned=getPropertyValue(DateManager.convertDateToString(foo.getDateFacilityStaffSigned(),DateManager.DB_DATE_FORMAT));
                  String partnerCode=getPropertyValue(foo.getPartnerCode());
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(foo.getLastModifiedDate(),DateManager.DB_DATE_FORMAT));
                  String age=getIntegerPropertyValue(foo.getAge()+"");
                  String ageUnit=getIntegerPropertyValue(foo.getAgeUnit()+"");
                  String sex=getPropertyValue(foo.getSex());
                  String userName=getPropertyValue(foo.getUserName());
                  String markedForDelete=getIntegerPropertyValue(foo.getMarkedForDelete()+"");
                  
                  String[] fieldValues={clientUniqueId,level4OuId,treatmentId,clientFirstName,clientSurname,dateEnrolledOnTreatment,hivTreatmentFacilityId,nameOfPersonToShareContactWith
                    ,shareContactQuestion,caregiverFirstName,caregiverSurname,dateCaregiverSigned,facilityStaffFirstName,facilityStaffSurname,
                    dateFacilityStaffSigned,cboId,partnerCode,lastModifiedDate,markedForDelete,age,ageUnit,sex,userName};
                  hd.startElement("","","facilityEnrollment",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","facilityEnrollment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderDirectory=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderDirectory=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderDirectory);
                String exportDestination=parentFolderDirectory+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
                
                /*String exportDestination=appUtil.getExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                if(parentFolderPath !=null)//parentFolderPath
                exportDestination=parentFolderPath+fileSeperator+fileName;
                appUtil.createDirectories(exportDestination);*/
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
public int exportOvcRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    //String parentFolderDirectory=parentFolderPath;
    String parentFolder="ChildEnrollment";
    //appUtil.createExportImportDirectories();
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        Ovc ovc=null;
        ChildEnrollmentDao dao=util.getChildEnrollmentDaoInstance();
        List list=dao.getOvcRecordsForExport(rpt,markedForDeleteValue);
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
            String[] columnNames={"hhUniqueId","ovcId","enrollmentId","caregiverId","caregiverRelationship","firstName","surname","sex","phoneNumber","dateOfEnrollment","ageAtBaseline","ageUnitAtBaseline","currentAge","currentAgeUnit","baselineHivStatus","dateOfBaselineHivStatus","currentHivStatus","dateOfCurrentHivStatus","enrolledOnTreatment","dateEnrolledOnTreatment","hivTreatmentFacilityId","treatmentId","mainVulnerabilityStatusCode","vulnerabilityStatusCode","baselineBirthRegistrationStatus","currentBirthRegistrationStatus","dateOfCurrentBirthRegStatus","baselineSchoolStatus","currentSchoolStatus","dateOfCurrentSchoolStatus","schoolName","schoolGrade","weight","height","sourceOfInfo","currentEnrollmentStatus","dateOfCurrentEnrollmentStatus","communityWorkerName","dateCreated","lastModifiedDate","recordedBy","markedForDelete","childHasCasePlan","dateCasePlanDeveloped","enrollmentSetting"};

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
                  String enrollmentSetting=getIntegerPropertyValue(ovc.getEnrollmentSetting()+"");
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
                  String mainVulnerabilityStatusCode=getPropertyValue(ovc.getMainVulnerabilityStatusCode());
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
                  
                  String[] fieldValues={hhUniqueId,ovcId,enrollmentId,caregiverId,caregiverRelationship,firstName,surname,sex,phoneNumber,dateOfEnrollment,ageAtBaseline,ageUnitAtBaseline,currentAge,currentAgeUnit,baselineHivStatus,dateOfBaselineHivStatus,currentHivStatus,dateOfCurrentHivStatus,enrolledOnTreatment,dateEnrolledOnTreatment,hivTreatmentFacilityId,treatmentId,mainVulnerabilityStatusCode,vulnerabilityStatusCode,baselineBirthRegistrationStatus,currentBirthRegistrationStatus,dateOfCurrentBirthRegStatus,baselineSchoolStatus,currentSchoolStatus,dateOfCurrentSchoolStatus,schoolName,schoolGrade,weight,height,sourceOfInfo,currentEnrollmentStatus,dateOfCurrentEnrollmentStatus,communityWorkerName,dateCreated,lastModifiedDate,recordedBy,markedForDelete,childHasCasePlan,dateCasePlanDeveloped,enrollmentSetting};
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
                /*if(parentFolderPath==null)
                parentFolderDirectory=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderDirectory=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderDirectory);
                String exportDestination=parentFolderDirectory+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName; 
                
                /*String exportDestination=appUtil.getExportFilePath()+fileSeperator+parentFolder+fileSeperator+fileName;
                if(parentFolderPath !=null)//parentFolderPath
                exportDestination=parentFolderPath+fileSeperator+fileName;
                appUtil.createDirectories(exportDestination);*/
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
public int exportOvcServiceRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    //String parentFolderDirectory=parentFolderPath;
    String parentFolder="ChildServices";
    //appUtil.createExportImportDirectories();
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        ChildService service=null;
        ChildServiceDao dao=util.getChildServiceDaoInstance();
        List list=dao.getChildServiceRecordsForExport(rpt,markedForDeleteValue);
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
            serializer.setParameter("createdDate", DateManager.getCurrentDate());
            hd.setResult(streamResult);
            AttributesImpl atts = new AttributesImpl();
            if(list==null)
            list=new ArrayList();
            String[] columnNames={"ovcId","serviceDate","stableServices","healthServices","safetyServices","schooledServices","referralServices","numberOfServices","dateCreated","lastModifiedDate","recordedBy","markedForDelete","ageAtService","ageUnitAtService","communityWorkerId"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    service=(ChildService)list.get(j);
                  String ovcId=getPropertyValue(service.getOvcId());
                  String serviceDate=getPropertyValue(DateManager.convertDateToString(service.getServiceDate(), DateManager.DB_DATE_FORMAT));
                  String stableServices=getPropertyValue(service.getStableServices());
                  String healthServices=getPropertyValue(service.getHealthServices());
                  String safetyServices=getPropertyValue(service.getSafetyServices());
                  String schooledServices=getPropertyValue(service.getSchooledServices());
                  String referralServices=getPropertyValue(service.getReferralServices());
                  String numberOfServices=getIntegerPropertyValue(service.getNumberOfServices()+"");
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(service.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(service.getDateCreated(), DateManager.DB_DATE_FORMAT));
                  String recordedBy=getPropertyValue(service.getRecordedBy());
                  String markedForDelete=getIntegerPropertyValue(service.getMarkedForDelete()+"");
                  String ageAtService=getIntegerPropertyValue(service.getAgeAtService()+"");
                  String ageUnitAtService=getIntegerPropertyValue(service.getAgeUnitAtService()+"");
                  String communityWorkerId=getPropertyValue(service.getCommunityWorkerId());

                  String[] fieldValues={ovcId,serviceDate,stableServices,healthServices,safetyServices,schooledServices,referralServices,numberOfServices,dateCreated,lastModifiedDate,recordedBy,markedForDelete,ageAtService,ageUnitAtService,communityWorkerId};
                  hd.startElement("","","ChildService",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","ChildService");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderDirectory=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderDirectory=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderDirectory);
                String exportDestination=parentFolderDirectory+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
                
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
public int exportHouseholdServiceRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="HouseholdServices";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        HouseholdService hhs=null;
        HouseholdServiceDao dao=util.getHouseholdServiceDaoInstance();
        List list=dao.getHouseholdServiceRecordsForExport(rpt,markedForDeleteValue);
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
            serializer.setParameter("createdDate", DateManager.getCurrentDate());
            hd.setResult(streamResult);
            AttributesImpl atts = new AttributesImpl();
            if(list==null)
            list=new ArrayList();
            String[] columnNames={"beneficiaryId","serviceDate","stableServices","healthServices","safetyServices","referralServices","numberOfServices","dateCreated","lastModifiedDate","recordedBy","markedForDelete","ageAtService"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                  hhs=(HouseholdService)list.get(j);
                  String beneficiaryId=getPropertyValue(hhs.getBeneficiaryId());
                  String serviceDate=getPropertyValue(DateManager.convertDateToString(hhs.getServiceDate(), DateManager.DB_DATE_FORMAT));
                  String stableServices=getPropertyValue(hhs.getStableServices());
                  String healthServices=getPropertyValue(hhs.getHealthServices());
                  String safetyServices=getPropertyValue(hhs.getSafetyServices());
                  String referralServices=getPropertyValue(hhs.getReferralServices());
                  String numberOfServices=getIntegerPropertyValue(hhs.getNumberOfServices()+"");
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(hhs.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(hhs.getDateCreated(), DateManager.DB_DATE_FORMAT));
                  String recordedBy=getPropertyValue(hhs.getRecordedBy());
                  String markedForDelete=getIntegerPropertyValue(hhs.getMarkedForDelete()+"");
                  String ageAtService=getIntegerPropertyValue(hhs.getAgeAtService()+"");

                  String[] fieldValues={beneficiaryId,serviceDate,stableServices,healthServices,safetyServices,referralServices,numberOfServices,dateCreated,lastModifiedDate,recordedBy,markedForDelete,ageAtService};
                  hd.startElement("","","HouseholdService",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","HouseholdService");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
                
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
public int exportHivRiskAssessmentRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="HivRiskAssessments";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        HivRiskAssessment hra=null;
        HivRiskAssessmentDao dao=util.getHivRiskAssessmentDaoInstance();
        List list=dao.getHivRiskAssessmentRecordsForExport(rpt,markedForDeleteValue);
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
            String[] columnNames={"recordId","ovcId","dateOfAssessment","hivParentQuestion","motherSicknessQuestion","hivSibblingQuestion","sibblingSicknessQuestion","childSickQuestion","childHasMoreThanTwoIllnessQuestion","bloodTransfusionQuestion","childCircumcisedOrEarPierced","childEverPregnantQuestion","sexualAbuseQuestion","childAtRiskQuestion","childAgeQuestion","childTestedQuestion","hivStatusQuestion","hivStatusAtRiskAssessment","chronicallyIllQuestion","sexuallyActiveQuestion","drugInjectionQuestion","genitalDischargeQuestion","sexualActivityQuestion","muacbmiQuestion","ageAtRiskAssessment","ageUnitAtRiskAssessment","markedForDelete","nameOfAssessor","dateCreated","lastModifiedDate","recordedBy","childReferredForHIVTest"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    hra=(HivRiskAssessment)list.get(j);
                    String recordId=getIntegerPropertyValue(hra.getRecordId()+"");
                  String ovcId=getPropertyValue(hra.getOvcId());
                  String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(hra.getDateOfAssessment(), DateManager.DB_DATE_FORMAT));
                  
                  String hivSibblingQuestion=getIntegerPropertyValue(hra.getHivSibblingQuestion()+"");
                  String sibblingSicknessQuestion=getIntegerPropertyValue(hra.getSibblingSicknessQuestion()+"");
                  String hivStatusAtRiskAssessment=getIntegerPropertyValue(hra.getHivStatusAtRiskAssessment()+"");
                  String bloodTransfusionQuestion=getIntegerPropertyValue(hra.getBloodTransfusionQuestion()+"");
                  String childSickQuestion=getIntegerPropertyValue(hra.getChildSickQuestion()+"");
                  String motherSicknessQuestion=getIntegerPropertyValue(hra.getMotherSicknessQuestion()+"");
                  String childAtRiskQuestion=getIntegerPropertyValue(hra.getChildAtRiskQuestion()+"");
                  String childTestedQuestion=getIntegerPropertyValue(hra.getChildTestedQuestion()+"");
                  String chronicallyIllQuestion=getIntegerPropertyValue(hra.getChronicallyIllQuestion()+"");
                  String hivParentQuestion=getIntegerPropertyValue(hra.getHivParentQuestion()+"");
                  String hivStatusQuestion=getIntegerPropertyValue(hra.getHivStatusQuestion()+"");
                  String nameOfAssessor=getPropertyValue(hra.getNameOfAssessor());
                  String sexualAbuseQuestion=getIntegerPropertyValue(hra.getSexualAbuseQuestion()+"");
                  String sexuallyActiveQuestion=getIntegerPropertyValue(hra.getSexuallyActiveQuestion()+"");
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(hra.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(hra.getDateCreated(), DateManager.DB_DATE_FORMAT));
                  String recordedBy=getPropertyValue(hra.getRecordedBy());
                  String markedForDelete=getIntegerPropertyValue(hra.getMarkedForDelete()+"");
                  String ageAtRiskAssessment=getIntegerPropertyValue(hra.getAgeAtRiskAssessment()+"");
                  String ageUnitAtRiskAssessment=getIntegerPropertyValue(hra.getAgeUnitAtRiskAssessment()+"");
                  String childHasMoreThanTwoIllnessQuestion=getIntegerPropertyValue(hra.getChildHasMoreThanTwoIllnessQuestion()+"");
                  String childCircumcisedOrEarPierced=getIntegerPropertyValue(hra.getChildCircumcisedOrEarPierced()+"");
                  String childEverPregnantQuestion=getIntegerPropertyValue(hra.getChildEverPregnantQuestion()+"");
                  String childAgeQuestion=getIntegerPropertyValue(hra.getChildAgeQuestion()+"");
                  String muacbmiQuestion=getIntegerPropertyValue(hra.getMuacbmiQuestion()+"");
                  
                  String drugInjectionQuestion=getIntegerPropertyValue(hra.getDrugInjectionQuestion()+"");
                  String genitalDischargeQuestion=getIntegerPropertyValue(hra.getGenitalDischargeQuestion()+"");
                  String sexualActivityQuestion=getIntegerPropertyValue(hra.getSexualActivityQuestion()+"");
                  String childReferredForHIVTest=getIntegerPropertyValue(hra.getChildReferredForHIVTest()+"");

                  String[] fieldValues={recordId,ovcId,dateOfAssessment,hivParentQuestion,motherSicknessQuestion,hivSibblingQuestion,sibblingSicknessQuestion,childSickQuestion,childHasMoreThanTwoIllnessQuestion,bloodTransfusionQuestion,childCircumcisedOrEarPierced,childEverPregnantQuestion,sexualAbuseQuestion,childAtRiskQuestion,childAgeQuestion,childTestedQuestion,hivStatusQuestion,hivStatusAtRiskAssessment,chronicallyIllQuestion,sexuallyActiveQuestion,drugInjectionQuestion,genitalDischargeQuestion,sexualActivityQuestion,muacbmiQuestion,ageAtRiskAssessment,ageUnitAtRiskAssessment,markedForDelete,nameOfAssessor,dateCreated,lastModifiedDate,recordedBy,childReferredForHIVTest};
                  hd.startElement("","","HivRiskAssessment",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","HivRiskAssessment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportHivCareAreSupportRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="CareAndSupportChecklist";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        CareAndSupportChecklist casc=null;
        CareAndSupportChecklistDao dao=util.getCareAndSupportChecklistDaoInstance();
        List list=dao.getCareAndSupportRecordsForExport(rpt,markedForDeleteValue);
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
            String[] columnNames={"recordId","beneficiaryId","dateOfAssessment","coughSymptom","childLossinWeight",
                "childHasFever","childHasNightSweat","childHasSwelling","enrolledOnTreatment","facilityId",
                "pickedUpMedication","missedARVsRecently","reasonsPeopleSkipARV","viralLoadTestDone","dateOfViralLoadTest",
                "viralLoadResultKnown","viralLoadResult","dateOfViralLoadResult","reasonViralLoadNotDone","receivedTransportationSupport",
                "monthsOfTransportationSupport","soresRashPainExperience","dateOfNextAppointment","markedForDelete",
                "volunteerName","dateCreated","lastModifiedDate","recordedBy","dateOfLastDrugPickup",
                "numberOfDaysOfRefill"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    casc=(CareAndSupportChecklist)list.get(j);
                  String recordId=getIntegerPropertyValue(casc.getRecordId()+"");
                  String beneficiaryId=getPropertyValue(casc.getBeneficiaryId());
                  String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(casc.getDateOfAssessment(), DateManager.DB_DATE_FORMAT));
                  String coughSymptom=getIntegerPropertyValue(casc.getCoughSymptom()+"");
                  String childLossinWeight=getIntegerPropertyValue(casc.getChildLossinWeight()+"");
                  String childHasFever=getIntegerPropertyValue(casc.getChildHasFever()+"");
                  String childHasNightSweat=getIntegerPropertyValue(casc.getChildHasNightSweat()+"");
                  String childHasSwelling=getIntegerPropertyValue(casc.getChildHasSwelling()+"");
                  String enrolledOnTreatment=getIntegerPropertyValue(casc.getEnrolledOnTreatment()+"");
                  String facilityId=getPropertyValue(casc.getFacilityId());
                  String pickedUpMedication=getIntegerPropertyValue(casc.getPickedUpMedication()+"");
                  String missedARVsRecently=getIntegerPropertyValue(casc.getMissedARVsRecently()+"");
                  String reasonsPeopleSkipARV=getPropertyValue(casc.getReasonsPeopleSkipARV());
                  String viralLoadTestDone=getIntegerPropertyValue(casc.getViralLoadTestDone()+"");
                  String dateOfViralLoadTest=getPropertyValue(DateManager.convertDateToString(casc.getDateOfViralLoadSampleCollection(), DateManager.DB_DATE_FORMAT));
                  String viralLoadResultKnown=getIntegerPropertyValue(casc.getViralLoadResultKnown()+"");
                  String viralLoadResult=getIntegerPropertyValue(casc.getViralLoadResult()+"");
                  String reasonViralLoadNotDone=getPropertyValue(casc.getReasonViralLoadNotDone());
                  String receivedTransportationSupport=getIntegerPropertyValue(casc.getReceivedTransportationSupport()+"");
                  String monthsOfTransportationSupport=getIntegerPropertyValue(casc.getMonthsOfTransportationSupport()+"");
                  String soresRashPainExperience=getIntegerPropertyValue(casc.getSoresRashPainExperience()+"");
                  String dateOfNextAppointment=getPropertyValue(DateManager.convertDateToString(casc.getDateOfNextAppointment(), DateManager.DB_DATE_FORMAT));
                  
                  String dateOfLastDrugPickup=getPropertyValue(DateManager.convertDateToString(casc.getDateOfLastDrugPickup(), DateManager.DB_DATE_FORMAT));
                  String numberOfDaysOfRefill=getIntegerPropertyValue(casc.getNumberOfDaysOfRefill()+"");
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(casc.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(casc.getDateCreated(), DateManager.DB_DATE_FORMAT));
                  String volunteerName=getPropertyValue(casc.getVolunteerName());
                  String recordedBy=getPropertyValue(casc.getRecordedBy());
                  String markedForDelete=getIntegerPropertyValue(casc.getMarkedForDelete()+"");
                  String dateOfViralLoadResult=getPropertyValue(DateManager.convertDateToString(casc.getDateOfViralLoadResult(), DateManager.DB_DATE_FORMAT));
                  
                  String[] fieldValues={recordId,beneficiaryId,dateOfAssessment,coughSymptom,childLossinWeight,
                childHasFever,childHasNightSweat,childHasSwelling,enrolledOnTreatment,facilityId,
                pickedUpMedication,missedARVsRecently,reasonsPeopleSkipARV,viralLoadTestDone,dateOfViralLoadTest,
                viralLoadResultKnown,viralLoadResult,dateOfViralLoadResult,reasonViralLoadNotDone,receivedTransportationSupport,
                monthsOfTransportationSupport,soresRashPainExperience,dateOfNextAppointment,markedForDelete,
                volunteerName,dateCreated,lastModifiedDate,recordedBy,dateOfLastDrugPickup,
                numberOfDaysOfRefill};
                  //{recordId,beneficiaryId,dateOfAssessment,coughSymptom,childLossinWeight,childHasFever,childHasNightSweat,childHasSwelling,enrolledOnTreatment,facilityId,pickedUpMedication,missedARVsRecently,reasonsPeopleSkipARV,viralLoadTestDone,dateOfViralLoadTest,viralLoadResultKnown,viralLoadResult,dateOfViralLoadResult,reasonViralLoadNotDone,receivedTransportationSupport,monthsOfTransportationSupport,soresRashPainExperience,dateOfNextAppointment,markedForDelete,volunteerName,dateCreated,lastModifiedDate,recordedBy,dateOfLastDrugPickup,numberOfDaysOfRefill};
                  hd.startElement("","","CareAndSupportAssessment",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","CareAndSupportAssessment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportReferralRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolderDirectory=parentFolderPath;
    String parentFolder="HouseholdReferral";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        HouseholdReferral referral=null;
        HouseholdReferralDao dao=util.getHouseholdReferralDaoInstance();
        List list=dao.getHouseholdReferralRecordsForExport(rpt,markedForDeleteValue);
        if(list !=null)
        {
            noOfRecords=list.size();
            System.err.println("noOfRecords in exportReferralRecordsInXml is "+noOfRecords);
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
            String[] columnNames={"id","beneficiaryId","beneficiaryType","dateOfReferral","stableServices","healthServices","safetyServices","schooledServices","referringOrganization","receivingOrganization","referralCompleted","numberOfServices","dateCreated","lastModifiedDate","recordedBy","communityWorker","markedForDelete","ageAtReferral","ageUnitAtReferral"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    referral=(HouseholdReferral)list.get(j);
                  String id=getIntegerPropertyValue(referral.getId()+"");
                  String beneficiaryId=getPropertyValue(referral.getBeneficiaryId());
                  String beneficiaryType=getIntegerPropertyValue(referral.getBeneficiaryType()+"");
                  String dateOfReferral=getPropertyValue(DateManager.convertDateToString(referral.getDateOfReferral(), DateManager.DB_DATE_FORMAT));
                  String numberOfServices=getIntegerPropertyValue(referral.getNumberOfServices()+"");
                  
                  String stableServices=getPropertyValue(referral.getStableServices());
                  String healthServices=getPropertyValue(referral.getHealthServices());
                  String safetyServices=getPropertyValue(referral.getSafetyServices());
                  String schooledServices=getPropertyValue(referral.getSchooledServices());
                  
                  String referringOrganization=getPropertyValue(referral.getReferringOrganization());
                  String receivingOrganization=getPropertyValue(referral.getReceivingOrganization());
                  
                  String communityWorker=getPropertyValue(referral.getCommunityWorkerId());
                  String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(referral.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                  String dateCreated=getPropertyValue(DateManager.convertDateToString(referral.getDateCreated(), DateManager.DB_DATE_FORMAT));
                  String recordedBy=getPropertyValue(referral.getRecordedBy());
                  String markedForDelete=getIntegerPropertyValue(referral.getMarkedForDelete()+"");
                  String ageAtReferral=getIntegerPropertyValue(referral.getAgeAtReferral()+"");
                  String ageUnitAtReferral=getIntegerPropertyValue(referral.getAgeUnitAtReferral()+"");
                  String referralComplete=getIntegerPropertyValue(referral.getReferralCompleted()+"");

                  String[] fieldValues={id,beneficiaryId,beneficiaryType,dateOfReferral,stableServices,healthServices,safetyServices,schooledServices,referringOrganization,receivingOrganization,referralComplete,numberOfServices,dateCreated,lastModifiedDate,recordedBy,communityWorker,markedForDelete,ageAtReferral,ageUnitAtReferral};
                  hd.startElement("","","ReferralService",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","ReferralService");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderDirectory=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderDirectory=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderDirectory);
                String exportDestination=parentFolderDirectory+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportCaregiverAccessToEmergencyFundRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="CaregiverAccessToEmergencyFund";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        CaregiverAccessToEmergencyFund caef=null;
        CaregiverAccessToEmergencyFundDao dao=util.getCaregiverAccessToEmergencyFundDaoInstance();
        List list=dao.getCaregiverAccessToEmergencyFundRecordsForExport(rpt,markedForDeleteValue);
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
            String[] columnNames={"recordId","beneficiaryId","dateOfAssessment","unexpectedExpenditure","accessMoneyToPay","sourceOfMoney","urgentHhNeeds","dateCreated","lastModifiedDate","recordedBy","volunteerName","markedForDelete"};

            atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    caef=(CaregiverAccessToEmergencyFund)list.get(j);
                      String recordId=getIntegerPropertyValue(caef.getRecordId()+"");
                      String beneficiaryId=getPropertyValue(caef.getBeneficiaryId());
                      String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(caef.getDateOfAssessment(), DateManager.DB_DATE_FORMAT));
                      String unexpectedExpenditure=getIntegerPropertyValue(caef.getUnexpectedExpenditure()+"");
                      String accessMoneyToPay=getIntegerPropertyValue(caef.getAccessMoneyToPay()+"");
                      String sourceOfMoney=getPropertyValue(caef.getSourceOfMoney());
                      String urgentHhNeeds=getPropertyValue(caef.getUrgentHhNeeds());
                      String volunteerName=getPropertyValue(caef.getVolunteerName());
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(caef.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(caef.getDateCreated(), DateManager.DB_DATE_FORMAT));
                      String recordedBy=getPropertyValue(caef.getRecordedBy());
                      String markedForDelete=getIntegerPropertyValue(caef.getMarkedForDelete()+"");
                  
                  String[] fieldValues={recordId,beneficiaryId,dateOfAssessment,unexpectedExpenditure,accessMoneyToPay,sourceOfMoney,urgentHhNeeds,dateCreated,lastModifiedDate,recordedBy,volunteerName,markedForDelete};
                  hd.startElement("","","Assessment",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","Assessment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportCareplanAchievementChecklistRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="CareplanAchievementChecklist";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        CareplanAchievementChecklist cpa=null;
        CareplanAchievementChecklistDao dao=util.getCareplanAchievementChecklistDaoInstance();
        List list=dao.getCareplanAchievementChecklistRecordsForExport(rpt,markedForDeleteValue);
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
                String[] columnNames={"recordId","hhUniqueId","dateOfAssessment","childrenHivStatusknown","hivPosAdolscentsLinked","documentedViralLoadResult","hivPreventionKnowledge","childrenNotUndernourished","allChildrenHaveBirthCert","stableAdultInHousehold","violenceIncidenceReport","childrenEnrolledInSchool","regularSchoolAttendance","cgiversEconomicActivity","emotionalSupportTeamIdentification","score","dateCreated","lastModifiedDate","recordedBy","volunteerName","markedForDelete"};

                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    cpa=(CareplanAchievementChecklist)list.get(j);
                      String recordId=getIntegerPropertyValue(cpa.getRecordId()+"");
                      String hhUniqueId=getPropertyValue(cpa.getHhUniqueId());
                      String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(cpa.getDateOfAssessment(), DateManager.DB_DATE_FORMAT));
                      String childrenHivStatusknown=getIntegerPropertyValue(cpa.getChildrenHivStatusknown()+"");
                      String hivPosAdolscentsLinked=getIntegerPropertyValue(cpa.getHivPosAdolscentsLinked()+"");
                      String childrenNotUndernourished=getIntegerPropertyValue(cpa.getChildrenNotUndernourished()+"");
                      String allChildrenHaveBirthCert=getIntegerPropertyValue(cpa.getAllChildrenHaveBirthCert()+"");
                      String stableAdultInHousehold=getIntegerPropertyValue(cpa.getStableAdultInHousehold()+"");
                      String documentedViralLoadResult=getIntegerPropertyValue(cpa.getDocumentedViralLoadResult()+"");
                      String hivPreventionKnowledge=getIntegerPropertyValue(cpa.getHivPreventionKnowledge()+"");
                      String violenceIncidenceReport=getIntegerPropertyValue(cpa.getViolenceIncidenceReport()+"");
                      String childrenEnrolledInSchool=getIntegerPropertyValue(cpa.getChildrenEnrolledInSchool()+"");
                      String regularSchoolAttendance=getIntegerPropertyValue(cpa.getRegularSchoolAttendance()+"");
                      String cgiversEconomicActivity=getIntegerPropertyValue(cpa.getCgiversEconomicActivity()+"");
                      String emotionalSupportTeamIdentification=getIntegerPropertyValue(cpa.getEmotionalSupportTeamIdentification()+"");
                      String score=getIntegerPropertyValue(cpa.getScore()+"");
                      String volunteerName=getPropertyValue(cpa.getVolunteerName());
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(cpa.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(cpa.getDateCreated(), DateManager.DB_DATE_FORMAT));
                      String recordedBy=getPropertyValue(cpa.getRecordedBy());
                      String markedForDelete=getIntegerPropertyValue(cpa.getMarkedForDelete()+"");
                  
                  String[] fieldValues={recordId,hhUniqueId,dateOfAssessment,childrenHivStatusknown,hivPosAdolscentsLinked,documentedViralLoadResult,hivPreventionKnowledge,childrenNotUndernourished,allChildrenHaveBirthCert,stableAdultInHousehold,violenceIncidenceReport,childrenEnrolledInSchool,regularSchoolAttendance,cgiversEconomicActivity,emotionalSupportTeamIdentification,score,dateCreated,lastModifiedDate,recordedBy,volunteerName,markedForDelete};
                  hd.startElement("","","Assessment",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","Assessment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportChildEducationPerformanceAssessmentRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="ChildEducationPerformanceAssessment";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        ChildEducationPerformanceAssessment cepa=null;
        ChildEducationPerformanceAssessmentDao dao=util.getChildEducationPerformanceAssessmentDaoInstance();
        List list=dao.getChildEducationPerformanceAssessmentRecordsForExport(rpt,markedForDeleteValue);
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
                String[] columnNames={"recordId","ovcId","dateOfAssessment","childHasInjuriesOrMarks","childIsSociallyWithdrawn","signsOfFatigueAndTiredness","regularSchoolAttendance","steadyImprovementInClassWork","earlyResumptionInSchool","goodPerformanceInLastExam","childProgressedInSchool","childMissVocTraining","earlyResumptionInTrainingCenter","steadyImprovementInVocWork","classTeacherComment","classTeacherName","dateCreated","lastModifiedDate","recordedBy","volunteerName","markedForDelete","reasonsChildMissedSchoolOrVocTraining"};

                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    cepa=(ChildEducationPerformanceAssessment)list.get(j);
                      String recordId=getIntegerPropertyValue(cepa.getRecordId()+"");
                      String ovcId=getPropertyValue(cepa.getOvcId());
                      String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(cepa.getDateOfAssessment(), DateManager.DB_DATE_FORMAT));
                      String childHasInjuriesOrMarks=getIntegerPropertyValue(cepa.getChildHasInjuriesOrMarks()+"");
                      String childIsSociallyWithdrawn=getIntegerPropertyValue(cepa.getChildIsSociallyWithdrawn()+"");
                      String signsOfFatigueAndTiredness=getIntegerPropertyValue(cepa.getSignsOfFatigueAndTiredness()+"");
                      String regularSchoolAttendance=getIntegerPropertyValue(cepa.getRegularSchoolAttendance()+"");
                      String steadyImprovementInClassWork=getIntegerPropertyValue(cepa.getSteadyImprovementInClassWork()+"");
                      String earlyResumptionInSchool=getIntegerPropertyValue(cepa.getEarlyResumptionInSchool()+"");
                      String goodPerformanceInLastExam=getIntegerPropertyValue(cepa.getGoodPerformanceInLastExam()+"");
                      String childProgressedInSchool=getIntegerPropertyValue(cepa.getChildProgressedInSchool()+"");
                      String childMissVocTraining=getIntegerPropertyValue(cepa.getChildMissVocTraining()+"");
                      String earlyResumptionInTrainingCenter=getIntegerPropertyValue(cepa.getEarlyResumptionInTrainingCenter()+"");
                      String steadyImprovementInVocWork=getIntegerPropertyValue(cepa.getSteadyImprovementInVocWork()+"");
                      String classTeacherComment=getPropertyValue(cepa.getClassTeacherComment()+"");
                      String classTeacherName=getPropertyValue(cepa.getClassTeacherUid());
                      String volunteerName=getPropertyValue(cepa.getVolunteerName());
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(cepa.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(cepa.getDateCreated(), DateManager.DB_DATE_FORMAT));
                      String recordedBy=getPropertyValue(cepa.getRecordedBy());
                      String markedForDelete=getIntegerPropertyValue(cepa.getMarkedForDelete()+"");
                      String reasonsChildMissedSchoolOrVocTraining=getPropertyValue(cepa.getReasonsChildMissedSchoolOrVocTraining());
                  String[] fieldValues={recordId,ovcId,dateOfAssessment,childHasInjuriesOrMarks,childIsSociallyWithdrawn,signsOfFatigueAndTiredness,regularSchoolAttendance,steadyImprovementInClassWork,earlyResumptionInSchool,goodPerformanceInLastExam,childProgressedInSchool,childMissVocTraining,earlyResumptionInTrainingCenter,steadyImprovementInVocWork,classTeacherComment,classTeacherName,dateCreated,lastModifiedDate,recordedBy,volunteerName,markedForDelete,reasonsChildMissedSchoolOrVocTraining};
                  hd.startElement("","","Assessment",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","Assessment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportBeneficiaryStatusUpdateRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="BeneficiaryStatusUpdates";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        BeneficiaryStatusUpdate bsu=null;
        BeneficiaryStatusUpdateDao dao=util.getBeneficiaryStatusUpdateDaoInstance();
        List list=dao.getBeneficiaryStatusUpdateRecordsForExport(rpt,markedForDeleteValue);
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
                String[] columnNames={"beneficiaryId","beneficiaryType","newHivStatus","dateOfNewStatus","enrolledOnTreatment","dateEnrolledOnTreatment","facilityId","pointOfUpdateValue","birthCertificate","schoolStatus","schoolName","grade","enrolledInVocationalTraining","nameOfVocationalTraining","updateCaregiverHivStatus","updateChildBirthRegStatus","updateChildHivStatus","dateCreated","lastModifiedDate","recordedBy","childExitedFromProgram","childExitStatus","caregiverExitStatus","caregiverExitedFromProgram","dateChildExitedFromProgram","dateCaregiverExitedFromProgram"};

                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    bsu=(BeneficiaryStatusUpdate)list.get(j);
                      String beneficiaryId=getPropertyValue(bsu.getBeneficiaryId());
                      String beneficiaryType=getIntegerPropertyValue(bsu.getBeneficiaryType()+"");
                      String newHivStatus=getIntegerPropertyValue(bsu.getNewHivStatus()+"");
                      String dateOfNewStatus=getPropertyValue(DateManager.convertDateToString(bsu.getDateOfNewStatus(), DateManager.DB_DATE_FORMAT));
                      String enrolledOnTreatment=getIntegerPropertyValue(bsu.getEnrolledOnTreatment()+"");
                      String dateEnrolledOnTreatment=getPropertyValue(DateManager.convertDateToString(bsu.getDateEnrolledOnTreatment(), DateManager.DB_DATE_FORMAT));
                      String facilityId=getPropertyValue(bsu.getFacilityId());
                      String pointOfUpdateValue=getIntegerPropertyValue(bsu.getPointOfUpdateValue()+"");
                      String birthCertificate=getIntegerPropertyValue(bsu.getBirthCertificate()+"");
                      String schoolStatus=getIntegerPropertyValue(bsu.getSchoolStatus()+"");
                      String schoolName=getPropertyValue(bsu.getSchoolName());
                      String grade=getPropertyValue(bsu.getGrade());
                      String enrolledInVocationalTraining=getIntegerPropertyValue(bsu.getEnrolledInVocationalTraining()+"");
                      String nameOfVocationalTraining=getPropertyValue(bsu.getNameOfVocationalTraining());
                      String updateCaregiverHivStatus=getIntegerPropertyValue(bsu.getUpdateCaregiverHivStatus()+"");
                      String updateChildBirthRegStatus=getIntegerPropertyValue(bsu.getUpdateChildBirthRegStatus()+"");
                      String updateChildHivStatus=getIntegerPropertyValue(bsu.getUpdateChildHivStatus()+"");
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(bsu.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(bsu.getDateCreated(), DateManager.DB_DATE_FORMAT));
                      String recordedBy=getPropertyValue(bsu.getRecordedBy());
                      String childExitedFromProgram=getIntegerPropertyValue(bsu.getChildExitedFromProgram()+"");
                      String childExitStatus=getIntegerPropertyValue(bsu.getChildExitStatus()+"");
                      String caregiverExitStatus=getIntegerPropertyValue(bsu.getCaregiverExitStatus()+"");
                      String caregiverExitedFromProgram=getIntegerPropertyValue(bsu.getCaregiverExitedFromProgram()+"");
                      String dateChildExitedFromProgram=getPropertyValue(DateManager.convertDateToString(bsu.getDateChildExitedFromProgram(), DateManager.DB_DATE_FORMAT));
                      String dateCaregiverExitedFromProgram=getPropertyValue(DateManager.convertDateToString(bsu.getDateCaregiverExitedFromProgram(), DateManager.DB_DATE_FORMAT));
                      //childExitedFromProgram","childExitStatus","caregiverExitStatus","caregiverExitedFromProgram",
                      //"dateChildExitedFromProgram","dateCaregiverExitedFromProgram
                  
                  String[] fieldValues={beneficiaryId,beneficiaryType,newHivStatus,dateOfNewStatus,enrolledOnTreatment,
                      dateEnrolledOnTreatment,facilityId,pointOfUpdateValue,birthCertificate,schoolStatus,schoolName,
                      grade,enrolledInVocationalTraining,nameOfVocationalTraining,updateCaregiverHivStatus,
                      updateChildBirthRegStatus,updateChildHivStatus,dateCreated,lastModifiedDate,recordedBy,childExitedFromProgram,
                      childExitStatus,caregiverExitStatus,caregiverExitedFromProgram,dateChildExitedFromProgram,dateCaregiverExitedFromProgram};
                  hd.startElement("","","BeneficiaryStatusUpdate",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","BeneficiaryStatusUpdate");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportNutritionAssessmentRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="NutritionAssessments";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        NutritionAssessment na=null;
        NutritionAssessmentDao dao=util.getNutritionalAssessmentDaoInstance();
        List list=dao.getNutritionAssessmentRecordsForExport(rpt,markedForDeleteValue);
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
                String[] columnNames={"recordId","ovcId","dateOfAssessment","assessmentNo","bmi","changeInWeight","lastWeight","dateOfLastWeight","foodSecurityAndDietQ1","foodSecurityAndDietQ2","foodSecurityAndDietQ3","foodSecurityAndDietQ4","foodSecurityAndDietQ5","foodSecurityAndDietQ6","foodSecurityAndDietQ7","foodSecurityAndDietQ8","foodSecurityAndDietQ9","height","hygieneQ1","hygieneQ2","hygieneQ3","hygieneQ4","muac","muacFacility","nutritionalStatus","oedema","recordStatus","weight","yellowMuacServices","markedForDelete","dateCreated","lastModifiedDate","recordedBy","volunteerName"};

                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    na=(NutritionAssessment)list.get(j);
                    String recordId=getIntegerPropertyValue(na.getRecordId()+"");
                      String ovcId=getPropertyValue(na.getOvcId());
                      String assessmentNo=getIntegerPropertyValue(na.getAssessmentNo()+"");
                      String dateOfAssessment=getPropertyValue(DateManager.convertDateToString(na.getDateOfAssessment(), DateManager.DB_DATE_FORMAT));
                      
                      String bmi=getIntegerPropertyValue(na.getBmi()+"");
                      String changeInWeight=getIntegerPropertyValue(na.getChangeInWeight()+"");
                      String lastWeight=getIntegerPropertyValue(na.getLastWeight()+"");
                      String dateOfLastWeight=getPropertyValue(DateManager.convertDateToString(na.getDateOfLastWeight(), DateManager.DB_DATE_FORMAT));
                      
                      String foodSecurityAndDietQ1=getIntegerPropertyValue(na.getFoodSecurityAndDietQ1()+"");
                      String foodSecurityAndDietQ2=getIntegerPropertyValue(na.getFoodSecurityAndDietQ2()+"");
                      String foodSecurityAndDietQ3=getIntegerPropertyValue(na.getFoodSecurityAndDietQ3()+"");
                      String foodSecurityAndDietQ4=getIntegerPropertyValue(na.getFoodSecurityAndDietQ4()+"");
                      String foodSecurityAndDietQ5=getIntegerPropertyValue(na.getFoodSecurityAndDietQ5()+"");
                      String foodSecurityAndDietQ6=getIntegerPropertyValue(na.getFoodSecurityAndDietQ6()+"");
                      String foodSecurityAndDietQ7=getIntegerPropertyValue(na.getFoodSecurityAndDietQ7()+"");
                      String foodSecurityAndDietQ8=getIntegerPropertyValue(na.getFoodSecurityAndDietQ8()+"");
                      String foodSecurityAndDietQ9=getIntegerPropertyValue(na.getFoodSecurityAndDietQ9()+"");
                      
                      String height=getIntegerPropertyValue(na.getHeight()+"");
                      String hygieneQ1=getIntegerPropertyValue(na.getHygieneQ1()+"");
                      String hygieneQ2=getIntegerPropertyValue(na.getHygieneQ2()+"");
                      String hygieneQ3=getIntegerPropertyValue(na.getHygieneQ3()+"");
                      String hygieneQ4=getIntegerPropertyValue(na.getHygieneQ4()+"");
                      String muac=getIntegerPropertyValue(na.getMuac()+"");
                      String muacFacility=getPropertyValue(na.getMuacFacility());
                      String nutritionalStatus=getIntegerPropertyValue(na.getNutritionalStatus()+"");
                      String oedema=getIntegerPropertyValue(na.getOedema()+"");
                      String recordStatus=getIntegerPropertyValue(na.getRecordStatus()+"");
                      String weight=getIntegerPropertyValue(na.getWeight()+"");
                      String yellowMuacServices=getPropertyValue(na.getYellowMuacServices());
                      String markedForDelete=getIntegerPropertyValue(na.getMarkedForDelete()+"");
                      String volunteerName=getPropertyValue(na.getVolunteerName());
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(na.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(na.getDateCreated(), DateManager.DB_DATE_FORMAT));
                      String recordedBy=getPropertyValue(na.getRecordedBy());
                      
                  
                  String[] fieldValues={recordId,ovcId,dateOfAssessment,assessmentNo,bmi,changeInWeight,lastWeight,dateOfLastWeight,foodSecurityAndDietQ1,foodSecurityAndDietQ2,foodSecurityAndDietQ3,foodSecurityAndDietQ4,foodSecurityAndDietQ5,foodSecurityAndDietQ6,foodSecurityAndDietQ7,foodSecurityAndDietQ8,foodSecurityAndDietQ9,height,hygieneQ1,hygieneQ2,hygieneQ3,hygieneQ4,muac,muacFacility,nutritionalStatus,oedema,recordStatus,weight,yellowMuacServices,markedForDelete,dateCreated,lastModifiedDate,recordedBy,volunteerName};
                  hd.startElement("","","NutritionAssessment",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","NutritionAssessment");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int exportHouseholdCareplanRecordsInXml(String parentFolderPath,ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
{
    String fileName="";
    String parentFolder="HouseholdCareplan";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        HouseholdCareplan hcp=null;
        HouseholdCareplanDao dao=util.getHouseholdCareplanDaoInstance();
        List list=dao.getHouseholdCareplanRecordsForExport(rpt,markedForDeleteValue);
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
                String[] columnNames={"recordId","beneficiaryId","careplanDate","followupForHealthServices","followupForSafetyServices","followupForSchooledServices","followupForStableServices","healthServicesToBeProvided","householdHealthGoals","householdSafetyGoals","householdSchooledGoals","householdStableGoals","identifiedHealthIssues","identifiedSafetyIssues","identifiedSchooledIssues","identifiedStableIssues","priorityHealthGoals","prioritySafetyGoals","prioritySchooledGoals","priorityStableGoals","safetyServicesToBeProvided","schooledServicesToBeProvided","stableServicesToBeProvided","timeFrameForHealthServices","timeFrameForSafetyServices","timeFrameForSchooledServices","timeFrameForStableServices","markedForDelete","dateCreated","lastModifiedDate","recordedBy","volunteerName"};

                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                for(int j=startSize; j<list.size(); j++)
                {
                    if(j>((k+1)*4999))
                    break;
                    hcp=(HouseholdCareplan)list.get(j);
                      String recordId=getIntegerPropertyValue(hcp.getRecordId()+"");
                      String beneficiaryId=getPropertyValue(hcp.getBeneficiaryId());
                      String followupForHealthServices=getPropertyValue(hcp.getFollowupForHealthServices());
                      String careplanDate=getPropertyValue(DateManager.convertDateToString(hcp.getCareplanDate(), DateManager.DB_DATE_FORMAT));
                      String followupForSafetyServices=getPropertyValue(hcp.getFollowupForSafetyServices());
                      String followupForSchooledServices=getPropertyValue(hcp.getFollowupForSchooledServices());
                      String followupForStableServices=getPropertyValue(hcp.getFollowupForStableServices());
                      String healthServicesToBeProvided=getPropertyValue(hcp.getHealthServicesToBeProvided());
                      String safetyServicesToBeProvided=getPropertyValue(hcp.getSafetyServicesToBeProvided());
                      String schooledServicesToBeProvided=getPropertyValue(hcp.getSchooledServicesToBeProvided());
                      String stableServicesToBeProvided=getPropertyValue(hcp.getStableServicesToBeProvided());
                      String householdHealthGoals=getPropertyValue(hcp.getHouseholdHealthGoals());
                      String householdSafetyGoals=getPropertyValue(hcp.getHouseholdSafetyGoals());
                      String householdSchooledGoals=getPropertyValue(hcp.getHouseholdSchooledGoals());
                      String householdStableGoals=getPropertyValue(hcp.getHouseholdStableGoals());
                      String identifiedHealthIssues=getPropertyValue(hcp.getIdentifiedHealthIssues());
                      String identifiedSafetyIssues=getPropertyValue(hcp.getIdentifiedSafetyIssues());
                      String identifiedSchooledIssues=getPropertyValue(hcp.getIdentifiedSchooledIssues());
                      String identifiedStableIssues=getPropertyValue(hcp.getIdentifiedStableIssues());
                      String priorityHealthGoals=getPropertyValue(hcp.getPriorityHealthGoals());
                      String prioritySafetyGoals=getPropertyValue(hcp.getPrioritySafetyGoals());
                      String prioritySchooledGoals=getPropertyValue(hcp.getPrioritySchooledGoals());
                      String priorityStableGoals=getPropertyValue(hcp.getPriorityStableGoals());
                      String timeFrameForHealthServices=getPropertyValue(hcp.getTimeFrameForHealthServices());
                      String timeFrameForSafetyServices=getPropertyValue(hcp.getTimeFrameForSafetyServices());
                      String timeFrameForSchooledServices=getPropertyValue(hcp.getTimeFrameForSchooledServices());
                      String timeFrameForStableServices=getPropertyValue(hcp.getTimeFrameForStableServices());
                      String markedForDelete=getIntegerPropertyValue(hcp.getMarkedForDelete()+"");
                      String volunteerName=getPropertyValue(hcp.getVolunteerName());
                      String lastModifiedDate=getPropertyValue(DateManager.convertDateToString(hcp.getLastModifiedDate(), DateManager.DB_DATE_FORMAT));
                      String dateCreated=getPropertyValue(DateManager.convertDateToString(hcp.getDateCreated(), DateManager.DB_DATE_FORMAT));
                      String recordedBy=getPropertyValue(hcp.getRecordedBy());
                      
                  
                  String[] fieldValues={recordId,beneficiaryId,careplanDate,followupForHealthServices,followupForSafetyServices,followupForSchooledServices,followupForStableServices,healthServicesToBeProvided,householdHealthGoals,householdSafetyGoals,householdSchooledGoals,householdStableGoals,identifiedHealthIssues,identifiedSafetyIssues,identifiedSchooledIssues,identifiedStableIssues,priorityHealthGoals,prioritySafetyGoals,prioritySchooledGoals,priorityStableGoals,safetyServicesToBeProvided,schooledServicesToBeProvided,stableServicesToBeProvided,timeFrameForHealthServices,timeFrameForSafetyServices,timeFrameForSchooledServices,timeFrameForStableServices,markedForDelete,dateCreated,lastModifiedDate,recordedBy,volunteerName};
                  hd.startElement("","","careplan",atts);
                    for (int i=0;i<columnNames.length;i++)
                    {
                        hd.startElement("","",columnNames[i],atts);
                        hd.characters(fieldValues[i].toCharArray(),0,fieldValues[i].length());
                        hd.endElement("","",columnNames[i]);
                    }
                  hd.endElement("","","careplan");
                  count++;
                }

                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                if(k>0)
                fileName=parentFolder+k+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
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
public int writeExportSummaryInXml(String parentFolderPath,List list) throws Exception
{
    String fileName="";
    String parentFolder="DataExportSummary";
    String fileSeperator=appUtil.getFileSeperator();
    int noOfRecords=0;
      try
      {
          int startSize=0;
        int count=0;
        
        if(list !=null)
        {
            noOfRecords=list.size()/2;
        
            
                StringWriter sw=new StringWriter();
                StreamResult streamResult = new StreamResult(sw);
                SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
                TransformerHandler hd = tf.newTransformerHandler();
                Transformer serializer = hd.getTransformer();
                serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
                serializer.setOutputProperty(OutputKeys.INDENT,"yes");
                hd.setResult(streamResult);
                AttributesImpl atts = new AttributesImpl();
                String elementName=null;
                String elementValue=null;
                
                atts.clear();

                hd.startDocument();
                hd.startElement("","",parentFolder,atts);
                startSize=count;
                System.err.println("startSize is "+startSize);
                hd.startElement("","","ExportSummary",atts);
                for(int j=startSize; j<list.size(); j+=2)
                {
                    elementName=list.get(j).toString();
                    if(list.get(j+1)!=null)
                    elementValue=list.get(j+1).toString();
                    else
                    elementValue="0";
                   hd.startElement("","",elementName,atts);
                   hd.characters(elementValue.toCharArray(),0,elementValue.length());
                   hd.endElement("","",list.get(j).toString());
                   count++;
                }
                hd.endElement("","","ExportSummary");
                hd.endElement("","",parentFolder);
                hd.endDocument();
                String xmlString = sw.toString();
                fileName=parentFolder+".xml";
                /*if(parentFolderPath==null)
                parentFolderPath=appUtil.getExportFilePath()+fileSeperator+parentFolder;
                else
                parentFolderPath=parentFolderPath+fileSeperator+parentFolder;
                appUtil.createDirectories(parentFolderPath);
                String exportDestination=parentFolderPath+fileSeperator+fileName;*/
                String exportDestination=getExportDirectory(parentFolderPath,parentFolder)+fileSeperator+fileName;
                
                File file = new File(exportDestination);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                bw.write(xmlString);
                bw.flush();
                bw.close();
           
        }
      }
       catch(Exception ex)
       {
           NomisLogManager.logStackTrace(ex);
            ex.printStackTrace();
       }
      return noOfRecords;
}
}
