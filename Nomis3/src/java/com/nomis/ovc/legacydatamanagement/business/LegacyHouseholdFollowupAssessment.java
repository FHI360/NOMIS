/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.legacydatamanagement.business;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class LegacyHouseholdFollowupAssessment implements Serializable
{
    private String recordId;
    private String hhUniqueId;
    private String hhSurname;
    private String hhFirstname;
    private String updatedAddress;
    private String phone;
    private String maritalStatus;
    private String hhGender;
    private String occupation;
    private String dateOfAssessment;
    private int noOfChildrenInhh;
    private int noOfPeopleInhh;
    private int hhAge;
    private String reasonWithdrawal;
    private String stateCode;
    private String lgaCode;
    private String orgCode;
    private String communityCode;
    private String partnerCode;
    private String recordedBy;
    private String dateModified;
    private int hhHeadship;
    private int health;
    private int educationLevel;
    private int childEducationLevel;
    private int shelterAndHousing;
    private int foodSecurityAndNutrition;
    private int hhEducationLevel;
    private int meansOfLivelihood;
    private int hhIncome;
    private int protection;
    private int vulnerabilityScore;
    private String vulnerabilityStatus;
    private int assessmentNo;
    private String nameOfAssessor;
    private String designation;
    private String hivStatus;
    private String enrolledInCare="No";
    private String enrolledOnART="No";
    private String facilityId;
    private String pointOfService="hhfollowup";
    private LegacyHouseholdVulnerabilityAssessment hva;
    private int hhvaId;
    private int markedForDelete;
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getUpdatedAddress() {
        return updatedAddress;
    }

    public void setUpdatedAddress(String updatedAddress) {
        this.updatedAddress = updatedAddress;
    }

    
    public int getHhAge() {
        return hhAge;
    }

    public void setHhAge(int hhAge) {
        this.hhAge = hhAge;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getHhFirstname() {
        return hhFirstname;
    }

    public void setHhFirstname(String hhFirstname) {
        this.hhFirstname = hhFirstname;
    }

    public String getHhGender() {
        return hhGender;
    }

    public void setHhGender(String hhGender) {
        this.hhGender = hhGender;
    }

    public String getHhSurname() {
        return hhSurname;
    }

    public void setHhSurname(String hhSurname) {
        this.hhSurname = hhSurname;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getLgaCode() {
        return lgaCode;
    }

    public void setLgaCode(String lgaCode) {
        this.lgaCode = lgaCode;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReasonWithdrawal() {
        return reasonWithdrawal;
    }

    public void setReasonWithdrawal(String reasonWithdrawal) {
        this.reasonWithdrawal = reasonWithdrawal;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public int getHhHeadship() {
        return hhHeadship;
    }

    public void setHhHeadship(int hhHeadship) {
        this.hhHeadship = hhHeadship;
    }

    public int getHhEducationLevel() {
        return hhEducationLevel;
    }

    public void setHhEducationLevel(int hhEducationLevel) {
        this.hhEducationLevel = hhEducationLevel;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getShelterAndHousing() {
        return shelterAndHousing;
    }

    public void setShelterAndHousing(int shelterAndHousing) {
        this.shelterAndHousing = shelterAndHousing;
    }

    public int getFoodSecurityAndNutrition() {
        return foodSecurityAndNutrition;
    }

    public void setFoodSecurityAndNutrition(int foodSecurityAndNutrition) {
        this.foodSecurityAndNutrition = foodSecurityAndNutrition;
    }

    public int getMeansOfLivelihood() {
        return meansOfLivelihood;
    }

    public void setMeansOfLivelihood(int meansOfLivelihood) {
        this.meansOfLivelihood = meansOfLivelihood;
    }

    public int getHhIncome() {
        return hhIncome;
    }

    public void setHhIncome(int hhIncome) {
        this.hhIncome = hhIncome;
    }

    public int getVulnerabilityScore() {
        return vulnerabilityScore;
    }

    public void setVulnerabilityScore(int vulnerabilityScore) {
        this.vulnerabilityScore = vulnerabilityScore;
    }

    public String getVulnerabilityStatus() {
        //vulnerabilityStatus=beneficiary.getHouseholdCurrentVulnerabilityStatus(getVulnerabilityScore());
        return vulnerabilityStatus;
    }

    public void setVulnerabilityStatus(String vulnerabilityStatus) {
        this.vulnerabilityStatus = vulnerabilityStatus;
    }

    public int getAssessmentNo() {
        return assessmentNo;
    }

    public void setAssessmentNo(int assessmentNo) {
        this.assessmentNo = assessmentNo;
    }

    public String getNameOfAssessor() {
        return nameOfAssessor;
    }

    public void setNameOfAssessor(String nameOfAssessor) {
        this.nameOfAssessor = nameOfAssessor;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public String getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(String hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getPointOfService() {
        return pointOfService;
    }

    public void setPointOfService(String pointOfService) {
        this.pointOfService = pointOfService;
    }

    public LegacyHouseholdVulnerabilityAssessment getHva() 
    {
        if(hva==null)
        hva=new LegacyHouseholdVulnerabilityAssessment();
        return hva;
    }

    public void setHva(LegacyHouseholdVulnerabilityAssessment hva) {
        this.hva = hva;
    }

    public int getHhvaId() {
        return hhvaId;
    }

    public void setHhvaId(int hhvaId) 
    {
        this.hhvaId = hhvaId;
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
    
}
