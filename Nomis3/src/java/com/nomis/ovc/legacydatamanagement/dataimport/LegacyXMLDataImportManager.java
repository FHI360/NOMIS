/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.dataimport;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class LegacyXMLDataImportManager implements Runnable
{
   String parentFolderPath;
   String tableCode;
   String partnerCode;
   int dataUploadId=0;
   boolean hivUpdate;
   boolean birthRegUpdate;
   public LegacyXMLDataImportManager(int dataUploadId,String parentFolderPath,String tableCode,String partnerCode,boolean hivUpdate,boolean birthRegUpdate)
   {
       this.parentFolderPath=parentFolderPath;
       this.tableCode=tableCode;
       this.partnerCode=partnerCode;
       this.dataUploadId=dataUploadId;
       this.hivUpdate=hivUpdate;
       this.birthRegUpdate=birthRegUpdate;
   }
    public void run()
    {
        extractAndImportDataFromXML(dataUploadId,parentFolderPath,tableCode,partnerCode,hivUpdate,birthRegUpdate);
    }
    public List extractAndImportDataFromXML(int dataUploadId,String parentFolderPath,String tableCode,String partnerCode,boolean hivUpdate,boolean birthRegUpdate)
    {
        //System.err.println("parentFolderPath is "+parentFolderPath+" tableCode is "+tableCode);
        LegacyXMLDataImportReader mdb=new LegacyXMLDataImportReader();
        List resultList=new ArrayList();
        if(tableCode.equalsIgnoreCase("wdr"))
        resultList.addAll(mdb.readOvcWithdrawalFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("vce"))
        resultList.addAll(mdb.readOvcRecordsFromXml(dataUploadId,parentFolderPath,hivUpdate,birthRegUpdate));
        
        else if(tableCode.equalsIgnoreCase("csi"))
        resultList.addAll(mdb.readCsiScoreFromXml(dataUploadId,parentFolderPath));
        //else if(tableCode.equalsIgnoreCase("hhe"))
        //resultList.addAll(mdb.readHouseholdEnrollmentFromXml(dataUploadId,parentFolderPath,partnerCode));
        else if(tableCode.equalsIgnoreCase("hhs"))
        resultList.addAll(mdb.readHouseholdServiceFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("vcfu"))
        resultList.addAll(mdb.readFollowupSurveyRecordsFromXml(dataUploadId,parentFolderPath,hivUpdate,birthRegUpdate));
        else if(tableCode.equalsIgnoreCase("hhfu"))
        resultList.addAll(mdb.readHouseholdFollowupAssessmentFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("hva"))
        resultList.addAll(mdb.readHouseholdVulnebilityAssessmentFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("cgi"))
        resultList.addAll(mdb.readCaregiverRecordsFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("hsu"))
        resultList.addAll(mdb.readHivStatusUpdateFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("hrac"))
        resultList.addAll(mdb.importHivRiskAssessmentChecklist(dataUploadId,parentFolderPath));
        
        else if(tableCode.equalsIgnoreCase("cef"))
        mdb.importCaregiverExpenditureAndSchoolAttendance(dataUploadId,parentFolderPath);
        
        /*else if(tableCode.equalsIgnoreCase("trn") || tableCode.equalsIgnoreCase("trnp"))
        {
            TrainingExportImportManager teim=new TrainingExportImportManager();
            resultList.addAll(teim.importTrainingData(dataUploadId,parentFolderPath));
            resultList.addAll(teim.importTrainingParticipant(dataUploadId,parentFolderPath));
        }
        else if(tableCode.equalsIgnoreCase("nass"))
        resultList.addAll(NutritionAssessmentExportImportManager.readNutritionAssessmentFromXml(dataUploadId,parentFolderPath));
        */
        else if(tableCode.equalsIgnoreCase("cgtbhiv"))
        resultList.addAll(mdb.readCaregiverTBHIVScreeningChecklistFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("ovctbhiv"))
        resultList.addAll(mdb.readOvcTBHIVScreeningChecklistFromXml(dataUploadId,parentFolderPath));
        //else if(tableCode.equalsIgnoreCase("grdchk"))
        //resultList.addAll(mdb.importGraduationCheckList(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("refdir"))
        resultList.addAll(mdb.importReferralDirectory(dataUploadId,parentFolderPath));
         else if(tableCode.equalsIgnoreCase("cpa"))
        resultList.addAll(mdb.importCareplanAchievementChecklist(dataUploadId,parentFolderPath));
        
        else if(tableCode.equalsIgnoreCase("vcs"))
        resultList.addAll(mdb.readServiceRecordsFromXml(dataUploadId,parentFolderPath,hivUpdate,birthRegUpdate));
        else if(tableCode.equalsIgnoreCase("casc"))
        mdb.importCareAndSupportChecklist(dataUploadId,parentFolderPath);
        else if(tableCode.equalsIgnoreCase("ref"))
        resultList.addAll(mdb.readReferralRecordsFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("sch"))
        resultList.addAll(mdb.readSchoolRecordsFromXml(dataUploadId,parentFolderPath));
        //else if(tableCode.equalsIgnoreCase("dlr"))
        //mdb.processDeletedRecords(dataUploadId,parentFolderPath);
        else if(tableCode.equalsIgnoreCase("cbo"))
        mdb.readOrganizationRecordsFromXml(dataUploadId,parentFolderPath);
        else if(tableCode.equalsIgnoreCase("wrd"))
        mdb.readWardsFromXml(dataUploadId,parentFolderPath);
        else if(tableCode.equalsIgnoreCase("orc"))
        mdb.readOrganizationRecordsFromXml(dataUploadId,parentFolderPath);
        //else if(tableCode.equalsIgnoreCase("oca"))
        //resultList.addAll(mdb.readOrganizationalAssessmentFromXml(dataUploadId,parentFolderPath));
        else if(tableCode.equalsIgnoreCase("rfd"))
        resultList.addAll(mdb.importReferralDirectory(dataUploadId,parentFolderPath));
        else
        {
            
        }
        return resultList;
    }
}
