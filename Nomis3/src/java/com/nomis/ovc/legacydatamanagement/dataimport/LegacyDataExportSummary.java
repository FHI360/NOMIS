/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.dataimport;

/**
 *
 * @author smomoh
 */
public class LegacyDataExportSummary 
{
    private String exportId;
    private int hhEverEnrolledInDatabase;
    private int hhCurrentlyEnrolledInDatabase;
    private int vcEverEnrolledInDatabase;
    private int vcCurrentlyEnrolledInDatabase;
    private int totalServiceRecords;
    private int noOfHHEnrollmentRecordsExported;
    private int noOfCaregiverEnrollmentRecordsExported;
    private int noOfVCEnrollmentRecordsExported;
    private int noOfHHServiceRecordsExported;
    private int noOfVCServiceRecordsExported;
    private int noOfVCFollowupAssessmentRecordsExported;
    private int noOfHHFollowupAssessmentRecordsExported;
    private int noOfHIVRiskAssessmentRecordsExported;
    private int careAndSupportRecordsExported;
    private int caregiverExpenditureRecordsExported;
    private int careplanAchievementRecordsExported;
    private int noOfCSIRecordsExported;
    private int nutritionalAssessmentRecordsExported;
    private int noOfReferralRecordsExported;
    private int noOfWithdrawalRecordsExported;
    private int noOfSchoolRecordsExported;
    private int noOfTrainingRecordsExported;
    private int noOfVCTBHIVRecordsExported;
    private int noOfCgiverTBHIVRecordsExported;
    private String systemTime;
    private String exportPeriod;
    private String level2Ou;
    private String level3Ou;
    private String level4Ou;
    private String cbo;
    
    private String level2OuName;
    private String level3OuName;
    private String level4OuName;
    private String cboName;

    public int getCareAndSupportRecordsExported() {
        return careAndSupportRecordsExported;
    }

    public void setCareAndSupportRecordsExported(int careAndSupportRecordsExported) {
        this.careAndSupportRecordsExported = careAndSupportRecordsExported;
    }

    public int getCaregiverExpenditureRecordsExported() {
        return caregiverExpenditureRecordsExported;
    }

    public void setCaregiverExpenditureRecordsExported(int caregiverExpenditureRecordsExported) {
        this.caregiverExpenditureRecordsExported = caregiverExpenditureRecordsExported;
    }

    public int getCareplanAchievementRecordsExported() {
        return careplanAchievementRecordsExported;
    }

    public void setCareplanAchievementRecordsExported(int careplanAchievementRecordsExported) {
        this.careplanAchievementRecordsExported = careplanAchievementRecordsExported;
    }

    public int getHhCurrentlyEnrolledInDatabase() {
        return hhCurrentlyEnrolledInDatabase;
    }

    public void setHhCurrentlyEnrolledInDatabase(int hhCurrentlyEnrolledInDatabase) {
        this.hhCurrentlyEnrolledInDatabase = hhCurrentlyEnrolledInDatabase;
    }

    public int getHhEverEnrolledInDatabase() {
        return hhEverEnrolledInDatabase;
    }

    public void setHhEverEnrolledInDatabase(int hhEverEnrolledInDatabase) {
        this.hhEverEnrolledInDatabase = hhEverEnrolledInDatabase;
    }

    public int getVcCurrentlyEnrolledInDatabase() {
        return vcCurrentlyEnrolledInDatabase;
    }

    public void setVcCurrentlyEnrolledInDatabase(int vcCurrentlyEnrolledInDatabase) {
        this.vcCurrentlyEnrolledInDatabase = vcCurrentlyEnrolledInDatabase;
    }

    public int getVcEverEnrolledInDatabase() {
        return vcEverEnrolledInDatabase;
    }

    public void setVcEverEnrolledInDatabase(int vcEverEnrolledInDatabase) {
        this.vcEverEnrolledInDatabase = vcEverEnrolledInDatabase;
    }

    public String getExportId() {
        return exportId;
    }

    public void setExportId(String exportId) {
        this.exportId = exportId;
    }

    public int getNoOfCSIRecordsExported() {
        return noOfCSIRecordsExported;
    }

    public void setNoOfCSIRecordsExported(int noOfCSIRecordsExported) {
        this.noOfCSIRecordsExported = noOfCSIRecordsExported;
    }

    public int getNoOfCaregiverEnrollmentRecordsExported() {
        return noOfCaregiverEnrollmentRecordsExported;
    }

    public void setNoOfCaregiverEnrollmentRecordsExported(int noOfCaregiverEnrollmentRecordsExported) {
        this.noOfCaregiverEnrollmentRecordsExported = noOfCaregiverEnrollmentRecordsExported;
    }

    public int getNoOfCgiverTBHIVRecordsExported() {
        return noOfCgiverTBHIVRecordsExported;
    }

    public void setNoOfCgiverTBHIVRecordsExported(int noOfCgiverTBHIVRecordsExported) {
        this.noOfCgiverTBHIVRecordsExported = noOfCgiverTBHIVRecordsExported;
    }

    public int getNoOfHHEnrollmentRecordsExported() {
        return noOfHHEnrollmentRecordsExported;
    }

    public void setNoOfHHEnrollmentRecordsExported(int noOfHHEnrollmentRecordsExported) {
        this.noOfHHEnrollmentRecordsExported = noOfHHEnrollmentRecordsExported;
    }

    public int getNoOfHHFollowupAssessmentRecordsExported() {
        return noOfHHFollowupAssessmentRecordsExported;
    }

    public void setNoOfHHFollowupAssessmentRecordsExported(int noOfHHFollowupAssessmentRecordsExported) {
        this.noOfHHFollowupAssessmentRecordsExported = noOfHHFollowupAssessmentRecordsExported;
    }

    public int getNoOfHHServiceRecordsExported() {
        return noOfHHServiceRecordsExported;
    }

    public void setNoOfHHServiceRecordsExported(int noOfHHServiceRecordsExported) {
        this.noOfHHServiceRecordsExported = noOfHHServiceRecordsExported;
    }

    public int getNoOfHIVRiskAssessmentRecordsExported() {
        return noOfHIVRiskAssessmentRecordsExported;
    }

    public void setNoOfHIVRiskAssessmentRecordsExported(int noOfHIVRiskAssessmentRecordsExported) {
        this.noOfHIVRiskAssessmentRecordsExported = noOfHIVRiskAssessmentRecordsExported;
    }

    public int getNoOfReferralRecordsExported() {
        return noOfReferralRecordsExported;
    }

    public void setNoOfReferralRecordsExported(int noOfReferralRecordsExported) {
        this.noOfReferralRecordsExported = noOfReferralRecordsExported;
    }

    public int getNoOfSchoolRecordsExported() {
        return noOfSchoolRecordsExported;
    }

    public void setNoOfSchoolRecordsExported(int noOfSchoolRecordsExported) {
        this.noOfSchoolRecordsExported = noOfSchoolRecordsExported;
    }

    public int getNoOfTrainingRecordsExported() {
        return noOfTrainingRecordsExported;
    }

    public void setNoOfTrainingRecordsExported(int noOfTrainingRecordsExported) {
        this.noOfTrainingRecordsExported = noOfTrainingRecordsExported;
    }

    public int getNoOfVCEnrollmentRecordsExported() {
        return noOfVCEnrollmentRecordsExported;
    }

    public void setNoOfVCEnrollmentRecordsExported(int noOfVCEnrollmentRecordsExported) {
        this.noOfVCEnrollmentRecordsExported = noOfVCEnrollmentRecordsExported;
    }

    public int getNoOfVCFollowupAssessmentRecordsExported() {
        return noOfVCFollowupAssessmentRecordsExported;
    }

    public void setNoOfVCFollowupAssessmentRecordsExported(int noOfVCFollowupAssessmentRecordsExported) {
        this.noOfVCFollowupAssessmentRecordsExported = noOfVCFollowupAssessmentRecordsExported;
    }

    public int getNoOfVCServiceRecordsExported() {
        return noOfVCServiceRecordsExported;
    }

    public void setNoOfVCServiceRecordsExported(int noOfVCServiceRecordsExported) {
        this.noOfVCServiceRecordsExported = noOfVCServiceRecordsExported;
    }

    public int getNoOfVCTBHIVRecordsExported() {
        return noOfVCTBHIVRecordsExported;
    }

    public void setNoOfVCTBHIVRecordsExported(int noOfVCTBHIVRecordsExported) {
        this.noOfVCTBHIVRecordsExported = noOfVCTBHIVRecordsExported;
    }

    public int getNoOfWithdrawalRecordsExported() {
        return noOfWithdrawalRecordsExported;
    }

    public void setNoOfWithdrawalRecordsExported(int noOfWithdrawalRecordsExported) {
        this.noOfWithdrawalRecordsExported = noOfWithdrawalRecordsExported;
    }

    public int getNutritionalAssessmentRecordsExported() {
        return nutritionalAssessmentRecordsExported;
    }

    public void setNutritionalAssessmentRecordsExported(int nutritionalAssessmentRecordsExported) {
        this.nutritionalAssessmentRecordsExported = nutritionalAssessmentRecordsExported;
    }

    public int getTotalServiceRecords() {
        return totalServiceRecords;
    }

    public void setTotalServiceRecords(int totalServiceRecords) {
        this.totalServiceRecords = totalServiceRecords;
    }

    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }

    public String getExportPeriod() {
        return exportPeriod;
    }

    public void setExportPeriod(String exportPeriod) {
        this.exportPeriod = exportPeriod;
    }

    public String getLevel2Ou() {
        return level2Ou;
    }

    public void setLevel2Ou(String level2Ou) {
        this.level2Ou = level2Ou;
    }

    public String getLevel3Ou() {
        return level3Ou;
    }

    public void setLevel3Ou(String level3Ou) {
        this.level3Ou = level3Ou;
    }

    public String getLevel4Ou() {
        return level4Ou;
    }

    public void setLevel4Ou(String level4Ou) {
        this.level4Ou = level4Ou;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public String getCboName() {
        return cboName;
    }

    public void setCboName(String cboName) {
        this.cboName = cboName;
    }

    public String getLevel2OuName() {
        return level2OuName;
    }

    public void setLevel2OuName(String level2OuName) {
        this.level2OuName = level2OuName;
    }

    public String getLevel3OuName() {
        return level3OuName;
    }

    public void setLevel3OuName(String level3OuName) {
        this.level3OuName = level3OuName;
    }

    public String getLevel4OuName() {
        return level4OuName;
    }

    public void setLevel4OuName(String level4OuName) {
        this.level4OuName = level4OuName;
    }
        
}
