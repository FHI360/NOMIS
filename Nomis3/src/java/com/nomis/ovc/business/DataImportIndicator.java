/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.ovc.util.FileManager;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class DataImportIndicator implements Serializable
{
    private ProgressIndicator adultHouseholdMembersEnrollmentIndicator = new ProgressIndicator();
    private ProgressIndicator careAndSupportIndicator = new ProgressIndicator();
    private ProgressIndicator caregiverAccessToEmergencyFundIndicator = new ProgressIndicator();
    private ProgressIndicator childEducationalAssessmentIndicator = new ProgressIndicator();
    private ProgressIndicator childEnrollmentIndicator = new ProgressIndicator();
    private ProgressIndicator childServiceIndicator = new ProgressIndicator();
    private ProgressIndicator householdEnrollmentIndicator = new ProgressIndicator();
    private ProgressIndicator householdAssessmentIndicator = new ProgressIndicator();
    private ProgressIndicator householdServiceIndicator = new ProgressIndicator();
    private ProgressIndicator hivRiskAssessmentIndicator = new ProgressIndicator();
    private ProgressIndicator statusUpdateIndicatorIndicator = new ProgressIndicator();
    private ProgressIndicator householdReferralIndicator = new ProgressIndicator();
    private ProgressIndicator careplanAchievementIndicator = new ProgressIndicator();
    private ProgressIndicator householdCareplanIndicator = new ProgressIndicator();
    private ProgressIndicator nutritionAssessmentIndicator = new ProgressIndicator();
    private ProgressIndicator revisedHouseholdAssessmentIndicator = new ProgressIndicator();
    private ProgressIndicator facilityOvcOfferIndicator = new ProgressIndicator();
    private int importStartFlag;
    private int importEndFlag;
    private File importFile;
    private String importFileName;
    private long importFileSize;

    public ProgressIndicator getAdultHouseholdMembersEnrollmentIndicator() {
        return adultHouseholdMembersEnrollmentIndicator;
    }

    public void setAdultHouseholdMembersEnrollmentIndicator(ProgressIndicator adultHouseholdMembersEnrollmentIndicator) {
        this.adultHouseholdMembersEnrollmentIndicator = adultHouseholdMembersEnrollmentIndicator;
    }

    public ProgressIndicator getCareAndSupportIndicator() {
        return careAndSupportIndicator;
    }

    public void setCareAndSupportIndicator(ProgressIndicator careAndSupportIndicator) {
        this.careAndSupportIndicator = careAndSupportIndicator;
    }

    public ProgressIndicator getCaregiverAccessToEmergencyFundIndicator() {
        return caregiverAccessToEmergencyFundIndicator;
    }

    public void setCaregiverAccessToEmergencyFundIndicator(ProgressIndicator caregiverAccessToEmergencyFundIndicator) {
        this.caregiverAccessToEmergencyFundIndicator = caregiverAccessToEmergencyFundIndicator;
    }

    public ProgressIndicator getCareplanAchievementIndicator() {
        return careplanAchievementIndicator;
    }

    public void setCareplanAchievementIndicator(ProgressIndicator careplanAchievementIndicator) {
        this.careplanAchievementIndicator = careplanAchievementIndicator;
    }

    public ProgressIndicator getChildEducationalAssessmentIndicator() {
        return childEducationalAssessmentIndicator;
    }

    public void setChildEducationalAssessmentIndicator(ProgressIndicator childEducationalAssessmentIndicator) {
        this.childEducationalAssessmentIndicator = childEducationalAssessmentIndicator;
    }

    public ProgressIndicator getChildEnrollmentIndicator() {
        return childEnrollmentIndicator;
    }

    public void setChildEnrollmentIndicator(ProgressIndicator childEnrollmentIndicator) {
        this.childEnrollmentIndicator = childEnrollmentIndicator;
    }

    public ProgressIndicator getChildServiceIndicator() {
        return childServiceIndicator;
    }

    public void setChildServiceIndicator(ProgressIndicator childServiceIndicator) {
        this.childServiceIndicator = childServiceIndicator;
    }

    public ProgressIndicator getHivRiskAssessmentIndicator() {
        return hivRiskAssessmentIndicator;
    }

    public void setHivRiskAssessmentIndicator(ProgressIndicator hivRiskAssessmentIndicator) {
        this.hivRiskAssessmentIndicator = hivRiskAssessmentIndicator;
    }

    public ProgressIndicator getHouseholdAssessmentIndicator() {
        return householdAssessmentIndicator;
    }

    public void setHouseholdAssessmentIndicator(ProgressIndicator householdAssessmentIndicator) {
        this.householdAssessmentIndicator = householdAssessmentIndicator;
    }

    public ProgressIndicator getHouseholdCareplanIndicator() {
        return householdCareplanIndicator;
    }

    public void setHouseholdCareplanIndicator(ProgressIndicator householdCareplanIndicator) {
        this.householdCareplanIndicator = householdCareplanIndicator;
    }

    public ProgressIndicator getHouseholdEnrollmentIndicator() {
        return householdEnrollmentIndicator;
    }

    public void setHouseholdEnrollmentIndicator(ProgressIndicator householdEnrollmentIndicator) {
        this.householdEnrollmentIndicator = householdEnrollmentIndicator;
    }

    public ProgressIndicator getHouseholdReferralIndicator() {
        return householdReferralIndicator;
    }

    public void setHouseholdReferralIndicator(ProgressIndicator householdReferralIndicator) {
        this.householdReferralIndicator = householdReferralIndicator;
    }

    public ProgressIndicator getHouseholdServiceIndicator() {
        return householdServiceIndicator;
    }

    public void setHouseholdServiceIndicator(ProgressIndicator householdServiceIndicator) {
        this.householdServiceIndicator = householdServiceIndicator;
    }

    public ProgressIndicator getNutritionAssessmentIndicator() {
        return nutritionAssessmentIndicator;
    }

    public void setNutritionAssessmentIndicator(ProgressIndicator nutritionAssessmentIndicator) {
        this.nutritionAssessmentIndicator = nutritionAssessmentIndicator;
    }

    public ProgressIndicator getRevisedHouseholdAssessmentIndicator() {
        return revisedHouseholdAssessmentIndicator;
    }

    public void setRevisedHouseholdAssessmentIndicator(ProgressIndicator revisedHouseholdAssessmentIndicator) {
        this.revisedHouseholdAssessmentIndicator = revisedHouseholdAssessmentIndicator;
    }

    public ProgressIndicator getStatusUpdateIndicatorIndicator() {
        return statusUpdateIndicatorIndicator;
    }

    public void setStatusUpdateIndicatorIndicator(ProgressIndicator statusUpdateIndicatorIndicator) {
        this.statusUpdateIndicatorIndicator = statusUpdateIndicatorIndicator;
    }

    public int getImportStartFlag() {
        return importStartFlag=getAdultHouseholdMembersEnrollmentIndicator().getProcessStartFlag()+getCareAndSupportIndicator().getProcessStartFlag()+getCaregiverAccessToEmergencyFundIndicator().getProcessStartFlag()
        +getCareplanAchievementIndicator().getProcessStartFlag()+getChildEducationalAssessmentIndicator().getProcessStartFlag()+getChildEnrollmentIndicator().getProcessStartFlag()
        +getChildServiceIndicator().getProcessStartFlag()+getHivRiskAssessmentIndicator().getProcessStartFlag()+getHouseholdAssessmentIndicator().getProcessStartFlag()
        +getHouseholdCareplanIndicator().getProcessStartFlag()+getHouseholdEnrollmentIndicator().getProcessStartFlag()+getHouseholdReferralIndicator().getProcessStartFlag()
        +getHouseholdServiceIndicator().getProcessStartFlag()+getNutritionAssessmentIndicator().getProcessStartFlag()+getRevisedHouseholdAssessmentIndicator().getProcessStartFlag()
        +getStatusUpdateIndicatorIndicator().getProcessStartFlag()+this.getFacilityOvcOfferIndicator().getProcessStartFlag();
    }

    public int getImportEndFlag() {
        return importEndFlag=getAdultHouseholdMembersEnrollmentIndicator().getProcessEndFlag()+getCareAndSupportIndicator().getProcessEndFlag()+getCaregiverAccessToEmergencyFundIndicator().getProcessEndFlag()
        +getCareplanAchievementIndicator().getProcessEndFlag()+getChildEducationalAssessmentIndicator().getProcessEndFlag()+getChildEnrollmentIndicator().getProcessEndFlag()
        +getChildServiceIndicator().getProcessEndFlag()+getHivRiskAssessmentIndicator().getProcessEndFlag()+getHouseholdAssessmentIndicator().getProcessEndFlag()
        +getHouseholdCareplanIndicator().getProcessEndFlag()+getHouseholdEnrollmentIndicator().getProcessEndFlag()+getHouseholdReferralIndicator().getProcessEndFlag()
        +getHouseholdServiceIndicator().getProcessEndFlag()+getNutritionAssessmentIndicator().getProcessEndFlag()+getRevisedHouseholdAssessmentIndicator().getProcessEndFlag()
        +getStatusUpdateIndicatorIndicator().getProcessEndFlag()+this.getFacilityOvcOfferIndicator().getProcessEndFlag();
    }

    public File getImportFile() {
        return importFile;
    }

    public void setImportFile(File importFile) 
    {
        this.importFile = importFile;
    }

    public String getImportFileName() 
    {
        if(importFile !=null)
        importFileName=importFile.getName();
        return importFileName;
    }

    public long getImportFileSize() 
    {
        if(importFile !=null)
        {
            FileManager f=new FileManager();
            importFileSize=importFile.length();//f.getFileSize(importFile.getPath());
        }
        return importFileSize;
    }

    public ProgressIndicator getFacilityOvcOfferIndicator() {
        return facilityOvcOfferIndicator;
    }

    public void setFacilityOvcOfferIndicator(ProgressIndicator facilityOvcOfferIndicator) {
        this.facilityOvcOfferIndicator = facilityOvcOfferIndicator;
    }
    
}
