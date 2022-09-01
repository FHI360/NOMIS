/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.business;


import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author Siaka
 */
public class LegacyHouseholdEnrollment implements Serializable
{
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    private String hhUniqueId;
    private String hhEnrollmentId;
    private String newHhUniqueId;
    private String hhSurname;
    private String hhFirstname;
    private String address;
    private String phone;
    private String maritalStatus;
    private String hhGender;
    private String occupation;
    private String dateOfAssessment;
    private int noOfChildrenInhh;
    private int noOfPeopleInhh;
    private int noOfChildrenCurrentlyEnrolled;
    private int noOfChildrenEverEnrolled;
    private double latitude;
    private double longitude;
    private int hhAge;
    private int currentAge;
    private String stateCode;
    private String lgaCode;
    private String orgCode;
    private String communityCode;
    private String stateName;
    private String lgaName;
    private String orgName;
    private String communityName;
    private String partnerCode;
    private String eligibleForEnrollment;
    private String nameOfAssessor;
    private String designationOfAssessor;
    private String recordedBy;
    private String dateOfEntry;
    private LegacyHivStatusUpdate activeHivStatus;
    //private int vulnerabilityScore;
    //private String vulnerabilityStatus;
    private String withdrawnFromProgram="No";
    private String reasonForWithdrawal="active";
    private String dateOfWithdrawal;
    
    private String dateOfCurrentAssessment;
    private String baselineHivStatus;
    private LegacyHivStatusUpdate currentHivStatus;
    private String enrolledInCare="No";
    private String enrolledOnART="No";
    private String facilityId;
    private String pointOfService="hhenrollment";
    //private String hhFollowupVulnerabilityStatus;
    //private HouseholdVulnerabilityAssessment hhva;
    private LegacyHouseholdFollowupAssessment lastFollowupAssessment;
    private int baselineAssessmentScore;
    private int currentAssessmentScore;
    public int baselineVulnerabilityScore=0;
    public int currentVulnerabilityScore=0;
    public String baselineVulnerabilityStatus;
    public String currentVulnerabilityStatus;
    public String followedUp="Yes";
    
    private int hhHeadship;
    private int health;
    private int educationLevel;
    private int hhEducationLevel;
    private int childEducationLevel;
    private int shelterAndHousing;
    private int foodSecurityAndNutrition;
    private int meansOfLivelihood;
    private int hhIncome;
    private int hhvaId;
    private int protection;
    private int markedForDelete;
    private LegacyReferralDirectory facility;

    public String getHhEnrollmentId() {
        return hhEnrollmentId;
    }

    public void setHhEnrollmentId(String hhEnrollmentId) {
        this.hhEnrollmentId = hhEnrollmentId;
    }
    
    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getNewHhUniqueId() {
        return newHhUniqueId;
    }

    public void setNewHhUniqueId(String newHhUniqueId) {
        this.newHhUniqueId = newHhUniqueId;
    }

    public String getHhSurname() {
        return hhSurname;
    }

    public void setHhSurname(String hhSurname) {
        this.hhSurname = hhSurname;
    }

    public String getHhFirstname() {
        return hhFirstname;
    }

    public void setHhFirstname(String hhFirstname) {
        this.hhFirstname = hhFirstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getHhGender() {
        return hhGender;
    }

    public void setHhGender(String hhGender) {
        this.hhGender = hhGender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public int getNoOfChildrenInhh() {
        return noOfChildrenInhh;
    }

    public void setNoOfChildrenInhh(int noOfChildrenInhh) {
        this.noOfChildrenInhh = noOfChildrenInhh;
    }

    public int getNoOfPeopleInhh() {
        return noOfPeopleInhh;
    }

    public void setNoOfPeopleInhh(int noOfPeopleInhh) {
        this.noOfPeopleInhh = noOfPeopleInhh;
    }

    public int getHhAge() {
        return hhAge;
    }

    public void setHhAge(int hhAge) {
        this.hhAge = hhAge;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getLgaCode() {
        return lgaCode;
    }

    public void setLgaCode(String lgaCode) {
        this.lgaCode = lgaCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getEligibleForEnrollment() {
        return eligibleForEnrollment;
    }

    public void setEligibleForEnrollment(String eligibleForEnrollment) {
        this.eligibleForEnrollment = eligibleForEnrollment;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public LegacyHouseholdFollowupAssessment getLastFollowupAssessment() {
        //lastFollowupAssessment=beneficiary.getMostRecentHouseholdFollowupAssessmentForHhe(hhUniqueId);
        return lastFollowupAssessment;
    }

    public void setLastFollowupAssessment(LegacyHouseholdFollowupAssessment lastFollowupAssessment) {
        this.lastFollowupAssessment = lastFollowupAssessment;
    }

    /*public HouseholdVulnerabilityAssessment getHhva() 
    {
        hhva=beneficiary.getHouseholdVulnerabilityAssessment(hhUniqueId, dateOfAssessment);
        return hhva;
    }

    public void setHhva(HouseholdVulnerabilityAssessment hhva) {
        this.hhva = hhva;
    }*/

    /*public int getVulnerabilityScore() 
    {
        vulnerabilityScore=getHhva().getVulnerabilityScore();
        return vulnerabilityScore;
    }

    public void setVulnerabilityScore(int vulnerabilityScore) {
        this.vulnerabilityScore = vulnerabilityScore;
    }*/

    /*public String getVulnerabilityStatus() 
    {
        vulnerabilityStatus=beneficiary.getHouseholdVulnerabilityStatus(getHhva().getVulnerabilityScore());
        return vulnerabilityStatus;
    }

    public void setVulnerabilityStatus(String vulnerabilityStatus) {
        this.vulnerabilityStatus = vulnerabilityStatus;
    }*/

    public String getDateOfWithdrawal() 
    {
        LegacyOvcWithdrawal withdrawal=null;
        //withdrawal=beneficiary.getWithdrawalRecord(hhUniqueId,getWithdrawnFromProgram());
        if(withdrawal !=null)
        dateOfWithdrawal=withdrawal.getDateOfWithdrawal();
        return dateOfWithdrawal;
    }

    public void setDateOfWithdrawal(String dateOfWithdrawal) {
        this.dateOfWithdrawal = dateOfWithdrawal;
    }

    public String getReasonForWithdrawal() 
    {
        LegacyOvcWithdrawal withdrawal=null;
        //withdrawal=beneficiary.getWithdrawalRecord(hhUniqueId,getWithdrawnFromProgram());
        if(withdrawal !=null)
        reasonForWithdrawal=withdrawal.getReasonWithdrawal();
        return reasonForWithdrawal;
    }

    public void setReasonForWithdrawal(String reasonForWithdrawal) {
        this.reasonForWithdrawal = reasonForWithdrawal;
    }

    public String getWithdrawnFromProgram() 
    {
        return withdrawnFromProgram;
    }

    public void setWithdrawnFromProgram(String withdrawnFromProgram) {
        this.withdrawnFromProgram = withdrawnFromProgram;
    }

    public int getBaselineAssessmentScore() {
        return baselineAssessmentScore;
    }

    public void setBaselineAssessmentScore(int baselineAssessmentScore) {
        this.baselineAssessmentScore = baselineAssessmentScore;
    }

    public int getCurrentAssessmentScore() {
        return currentAssessmentScore;
    }

    public void setCurrentAssessmentScore(int currentAssessmentScore) {
        this.currentAssessmentScore = currentAssessmentScore;
    }

    public String getDateOfCurrentAssessment() {
        return dateOfCurrentAssessment;
    }

    public void setDateOfCurrentAssessment(String dateOfCurrentAssessment) {
        this.dateOfCurrentAssessment = dateOfCurrentAssessment;
    }

    public String getBaselineHivStatus() {
        return baselineHivStatus;
    }

    public void setBaselineHivStatus(String baselineHivStatus) {
        this.baselineHivStatus = baselineHivStatus;
    }

    public LegacyBeneficiaryResourceManager getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(LegacyBeneficiaryResourceManager beneficiary) {
        this.beneficiary = beneficiary;
    }

    public LegacyHivStatusUpdate getCurrentHivStatus() 
    {
        //currentHivStatus=beneficiary.getActiveHivStatus(hhUniqueId,dateOfAssessment);
        return currentHivStatus;
    }

    public void setCurrentHivStatus(LegacyHivStatusUpdate currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
    }

    public String getEnrolledInCare() {
        return enrolledInCare;
    }

    public void setEnrolledInCare(String enrolledInCare) {
        this.enrolledInCare = enrolledInCare;
    }

    public String getEnrolledOnART() {
        return enrolledOnART;
    }

    public void setEnrolledOnART(String enrolledOnART) {
        this.enrolledOnART = enrolledOnART;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    
    public String getPointOfService() {
        return pointOfService;
    }

    public void setPointOfService(String pointOfService) {
        this.pointOfService = pointOfService;
    }

    public String getCommunityName() 
    {
        //communityName=beneficiary.getOrganizationUnitName(hhUniqueId, "ward");
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getLgaName() {
        //lgaName=beneficiary.getOrganizationUnitName(hhUniqueId, "lga");
        return lgaName;
    }

    public String getOrgName() {
        //orgName=beneficiary.getOrganizationUnitName(hhUniqueId, "cbo");
        return orgName;
    }

    public String getStateName() {
        //stateName=beneficiary.getOrganizationUnitName(hhUniqueId, "state");
        return stateName;
    }

    public LegacyHivStatusUpdate getActiveHivStatus() 
    {
        //activeHivStatus=beneficiary.getActiveHivStatus(hhUniqueId, dateOfAssessment);
        this.enrolledInCare=activeHivStatus.getClientEnrolledInCare();
        this.setEnrolledOnART(activeHivStatus.getEnrolledOnART());
        return activeHivStatus;
    }

    public void setActiveHivStatus(LegacyHivStatusUpdate activeHivStatus) {
        this.activeHivStatus = activeHivStatus;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getBaselineVulnerabilityScore() 
    {
        //baselineVulnerabilityScore=beneficiary.getHouseholdBaselineVulnerabilityScore(hhUniqueId, dateOfAssessment);
        return getBaselineAssessmentScore();
    }

    public void setBaselineVulnerabilityScore(int baselineVulnerabilityScore) {
        this.baselineVulnerabilityScore = baselineVulnerabilityScore;
    }

    public String getBaselineVulnerabilityStatus() 
    {
        //baselineVulnerabilityStatus=beneficiary.getHouseholdVulnerabilityStatus(getBaselineAssessmentScore());//.getHouseholdBaselineVulnerabilityStatus(hhUniqueId,dateOfAssessment);
        return baselineVulnerabilityStatus;
    }

    public void setBaselineVulnerabilityStatus(String baselineVulnerabilityStatus) {
        this.baselineVulnerabilityStatus = baselineVulnerabilityStatus;
    }

    public int getCurrentVulnerabilityScore() 
    {
        //currentVulnerabilityScore=beneficiary.getHouseholdCurrentVulnerabilityScore(hhUniqueId,dateOfAssessment);
        if(currentVulnerabilityScore<AppConstant.VULNERABLE_STARTVALUE)
        currentVulnerabilityScore=getBaselineVulnerabilityScore();
        return currentVulnerabilityScore;
    }

    public void setCurrentVulnerabilityScore(int currentVulnerabilityScore) 
    {
        this.currentVulnerabilityScore = currentVulnerabilityScore;
    }

    public String getCurrentVulnerabilityStatus() 
    {
        //currentVulnerabilityStatus=beneficiary.getHouseholdCurrentVulnerabilityStatus(getCurrentVulnerabilityScore());
        return currentVulnerabilityStatus;
    }

    public void setCurrentVulnerabilityStatus(String currentVulnerabilityStatus) {
        this.currentVulnerabilityStatus = currentVulnerabilityStatus;
    }

    public String getFollowedUp() 
    {
        if(getLastFollowupAssessment().getVulnerabilityScore() < AppConstant.VULNERABLE_STARTVALUE)
        followedUp="No";
        return followedUp;
    }

    public void setFollowedUp(String followedUp) {
        this.followedUp = followedUp;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getFoodSecurityAndNutrition() {
        return foodSecurityAndNutrition;
    }

    public void setFoodSecurityAndNutrition(int foodSecurityAndNutrition) {
        this.foodSecurityAndNutrition = foodSecurityAndNutrition;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHhHeadship() {
        return hhHeadship;
    }

    public void setHhHeadship(int hhHeadship) {
        this.hhHeadship = hhHeadship;
    }

    public int getHhIncome() {
        return hhIncome;
    }

    public void setHhIncome(int hhIncome) {
        this.hhIncome = hhIncome;
    }

    public int getMeansOfLivelihood() {
        return meansOfLivelihood;
    }

    public void setMeansOfLivelihood(int meansOfLivelihood) {
        this.meansOfLivelihood = meansOfLivelihood;
    }

    public int getShelterAndHousing() {
        return shelterAndHousing;
    }

    public void setShelterAndHousing(int shelterAndHousing) {
        this.shelterAndHousing = shelterAndHousing;
    }

    public String getNameOfAssessor() {
        return nameOfAssessor;
    }

    public void setNameOfAssessor(String nameOfAssessor) {
        this.nameOfAssessor = nameOfAssessor;
    }

    public String getDesignationOfAssessor() {
        return designationOfAssessor;
    }

    public void setDesignationOfAssessor(String designationOfAssessor) {
        this.designationOfAssessor = designationOfAssessor;
    }

    public int getHhvaId() {
        return hhvaId;
    }

    public void setHhvaId(int hhvaId) {
        this.hhvaId = hhvaId;
    }

    public int getNoOfChildrenCurrentlyEnrolled() {
        return noOfChildrenCurrentlyEnrolled;
    }

    public void setNoOfChildrenCurrentlyEnrolled(int noOfChildrenCurrentlyEnrolled) {
        this.noOfChildrenCurrentlyEnrolled = noOfChildrenCurrentlyEnrolled;
    }

    public int getNoOfChildrenEverEnrolled() {
        return noOfChildrenEverEnrolled;
    }

    public void setNoOfChildrenEverEnrolled(int noOfChildrenEverEnrolled) {
        this.noOfChildrenEverEnrolled = noOfChildrenEverEnrolled;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public int getChildEducationLevel() {
        return childEducationLevel;
    }

    public void setChildEducationLevel(int childEducationLevel) {
        this.childEducationLevel = childEducationLevel;
    }

    public int getHhEducationLevel() {
        return hhEducationLevel;
    }

    public void setHhEducationLevel(int hhEducationLevel) {
        this.hhEducationLevel = hhEducationLevel;
    }

    public LegacyReferralDirectory getFacility() 
    {
        if(facility==null)
        {
            //facility=beneficiary.getReferralDirectory(facilityId);
        }
        return facility;
    }

    public void setFacility(LegacyReferralDirectory facility) {
        this.facility = facility;
    }

}
