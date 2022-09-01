/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport;

import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatabaseConstant;

/**
 *
 * @author smomoh
 */
public class XMLDataImportThreadManager implements Runnable
{
    String userName;
    String moduleCode;
    String destinationDirectory;
    String partnerCode;
    int fileOption;
    XMLDataImportManager xdim=null;
    public XMLDataImportThreadManager(XMLDataImportManager xdim,String userName,String moduleCode,String destinationDirectory,int fileOption,String partnerCode)
    {
        this.xdim=xdim;
        this.partnerCode=partnerCode;
        this.userName=userName;
        this.moduleCode=moduleCode;
        this.destinationDirectory=destinationDirectory;
        this.fileOption=fileOption;
    }
    public void run()
    {
        if(xdim==null)
        xdim=new XMLDataImportManager();
        //XMLDataImportManager xdim=new XMLDataImportManager();
        xdim.setUserName(userName);
        
        if(moduleCode !=null)
        {
            if(xdim.isNomis3File(destinationDirectory))
            {
                if(moduleCode.equalsIgnoreCase(DatabaseConstant.householdEnrollmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importHouseholdEnrollmentRecordsFromXML(destinationDirectory,fileOption);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.adultHouseholdMemberCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importAdultHouseholdMembersRecordsFromXML(destinationDirectory,fileOption);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.childEnrollmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importOvcRecordsFromXML(destinationDirectory,fileOption);
                    AppUtility.xmlDataImportThreadCounter--;
                }

                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.householdServiceCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importHouseholdServiceRecordsFromXML(destinationDirectory,fileOption);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.childServiceCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importOvcServiceRecordsFromXML(destinationDirectory,fileOption);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.hivRiskAssessmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importHivRiskAssessmentRecordsFromXML(destinationDirectory,fileOption);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.householdReferralCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importReferralRecordsFromXML(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.careAndSupportChecklistCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importCareAndSupportRecordsFromXML(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.cgEmergencyFundCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importCaregiverAccessToEmergencyFundRecordsFromXML(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.householdVulnerabilityAssessmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importHouseholdVulnerabilityAssessmentRecordsFromXML(destinationDirectory,fileOption);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.revisedHvaCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importRevisedHouseholdVulnerabilityAssessmentRecordsFromXML(destinationDirectory,fileOption);
                    AppUtility.xmlDataImportThreadCounter--;
                }

                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.careplanAchievementCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importCareplanAchievementChecklistRecordsFromXML(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.childEducationalPerformanceCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importChildEducationPerformanceAssessmentRecordsFromXML(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.beneficiaryStatusUpdateCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importBeneficiaryStatusUpdateRecordsFromXML(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.hhCarePlanCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importHouseholdCareplanRecordsFromXML(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.nutritionAssessmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importNutritionAssessmentFromXml(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.facilityOvcOfferCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    xdim.importFacilityOvcOfferRecordsFromXML(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
            }
            else
            {
                LegacyImportManager ldim=new LegacyImportManager(userName);
                if(moduleCode.equalsIgnoreCase(DatabaseConstant.cboCode))
                {
                    ldim.processCBORecords(destinationDirectory);
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.wardCode))
                {
                    ldim.processWardRecords(destinationDirectory);
                }
                if(moduleCode.equalsIgnoreCase(DatabaseConstant.householdEnrollmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processHouseholdEnrollment(destinationDirectory, partnerCode);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.adultHouseholdMemberCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processCaregiverRecords(destinationDirectory, partnerCode);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.childEnrollmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processOvcRecords(destinationDirectory, partnerCode);
                    AppUtility.xmlDataImportThreadCounter--;
                }

                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.householdServiceCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processHouseholdServiceRecords(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.childServiceCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processOvcServiceRecords(destinationDirectory, partnerCode);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.hivRiskAssessmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processHivRiskAssessmentRecords(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.householdReferralCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processReferralRecordsRecords(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.careAndSupportChecklistCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processCareAndSupportRecords(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.cgEmergencyFundCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processCaregiverExpenditureAndSchoolAttendanceRecords(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.householdVulnerabilityAssessmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processHouseholdVulnerabilityAssessment(destinationDirectory, partnerCode);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.careplanAchievementCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processCareplanAchievementRecords(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.childStatusIndex))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processChildStatusIndexRecords(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                else if(moduleCode.equalsIgnoreCase(DatabaseConstant.nutritionAssessmentCode))
                {
                    AppUtility.xmlDataImportThreadCounter++;
                    ldim.processNutritionAssessmentRecords(destinationDirectory);
                    AppUtility.xmlDataImportThreadCounter--;
                }
                
            }
        }
    }
}
