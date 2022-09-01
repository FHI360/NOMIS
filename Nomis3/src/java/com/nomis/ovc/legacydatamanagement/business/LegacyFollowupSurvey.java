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
public class LegacyFollowupSurvey implements Serializable {

    private int id;
    private String ovcId;
    private String dateOfSurvey;
    private String updatedAddress;
    private int updatedAge;
    private String updatedAgeUnit;
    private String updatedHivStatus;
    private String updatedBirthCertStatus;
    private String currentBirthCertStatus;
    private String updatedSchoolStatus;
    private String updatedSchoolName;
    private String updatedClass;
    private String caregiverId;
    private String updatedCaregiverName;
    private String updatedCaregiverGender;
    private int updatedCaregiverAge;
    private String updatedCaregiverAddress;
    private String updatedCaregiverPhone;
    private String updatedCaregiverOccupation;
    private String updatedCaregiverRelationship;
    private String cgiverHivStatus;
    private String completedbyName;
    private String completedbyDesignation;
    private String dateOfEntry;
    private String recordedBy;
    private String pointOfService="followup";
    private String enrolledInCare="No";
    private String enrolledOnART="No";
    private String facilityId;
    private String cgiverEnrolledInCare="No";
    private String cgiverEnrolledOnART="No";
    private String cgiverFacilityId;
    private LegacyChildStatusIndex childStatusIndex;
    private int markedForDelete;
    
    private LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();

    public String getDateOfSurvey() {
        return dateOfSurvey;
    }

    public void setDateOfSurvey(String dateOfSurvey) {
        this.dateOfSurvey = dateOfSurvey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
    }

    public String getUpdatedAddress() {
        return updatedAddress;
    }

    public void setUpdatedAddress(String updatedAddress) {
        this.updatedAddress = updatedAddress;
    }

    public int getUpdatedAge() {
        return updatedAge;
    }

    public void setUpdatedAge(int updatedAge) {
        this.updatedAge = updatedAge;
    }

    public String getUpdatedAgeUnit() {
        return updatedAgeUnit;
    }

    public void setUpdatedAgeUnit(String updatedAgeUnit) {
        this.updatedAgeUnit = updatedAgeUnit;
    } 

    public String getUpdatedBirthCertStatus() 
    {
        if(updatedBirthCertStatus==null)
        updatedBirthCertStatus=getCurrentBirthCertStatus();
        return updatedBirthCertStatus;
    }

    public void setUpdatedBirthCertStatus(String updatedBirthCertStatus) {
        this.updatedBirthCertStatus = updatedBirthCertStatus;
    }

    public String getCurrentBirthCertStatus() 
    {
        //currentBirthCertStatus=beneficiary.getCurrentBirthRegistrationStatus(ovcId, dateOfSurvey, pointOfService);
        return currentBirthCertStatus;
    }

    /*public void setCurrentBirthCertStatus(String currentBirthCertStatus) {
        this.currentBirthCertStatus = currentBirthCertStatus;
    }*/

    public String getUpdatedCaregiverAddress() {
        return updatedCaregiverAddress;
    }

    public void setUpdatedCaregiverAddress(String updatedCaregiverAddress) {
        this.updatedCaregiverAddress = updatedCaregiverAddress;
    }

    public int getUpdatedCaregiverAge() {
        return updatedCaregiverAge;
    }

    public void setUpdatedCaregiverAge(int updatedCaregiverAge) {
        this.updatedCaregiverAge = updatedCaregiverAge;
    }

    public String getUpdatedCaregiverGender() {
        return updatedCaregiverGender;
    }

    public void setUpdatedCaregiverGender(String updatedCaregiverGender) {
        this.updatedCaregiverGender = updatedCaregiverGender;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }
    
    public String getUpdatedCaregiverName() {
        return updatedCaregiverName;
    }

    public void setUpdatedCaregiverName(String updatedCaregiverName) {
        this.updatedCaregiverName = updatedCaregiverName;
    }

    public String getUpdatedCaregiverOccupation() {
        return updatedCaregiverOccupation;
    }

    public void setUpdatedCaregiverOccupation(String updatedCaregiverOccupation) {
        this.updatedCaregiverOccupation = updatedCaregiverOccupation;
    }

    public String getUpdatedCaregiverPhone() {
        return updatedCaregiverPhone;
    }

    public void setUpdatedCaregiverPhone(String updatedCaregiverPhone) {
        this.updatedCaregiverPhone = updatedCaregiverPhone;
    }

    public String getUpdatedCaregiverRelationship() {
        return updatedCaregiverRelationship;
    }

    public void setUpdatedCaregiverRelationship(String updatedCaregiverRelationship) {
        this.updatedCaregiverRelationship = updatedCaregiverRelationship;
    }

    public String getCgiverHivStatus() {
        return cgiverHivStatus;
    }

    public void setCgiverHivStatus(String cgiverHivStatus) {
        this.cgiverHivStatus = cgiverHivStatus;
    }

    public String getUpdatedClass() {
        return updatedClass;
    }

    public void setUpdatedClass(String updatedClass) {
        this.updatedClass = updatedClass;
    }

    public String getUpdatedHivStatus() {
        return updatedHivStatus;
    }

    public void setUpdatedHivStatus(String updatedHivStatus) {
        this.updatedHivStatus = updatedHivStatus;
    }

    public String getUpdatedSchoolName() {
        return updatedSchoolName;
    }

    public void setUpdatedSchoolName(String updatedSchoolName) {
        this.updatedSchoolName = updatedSchoolName;
    }

    public String getUpdatedSchoolStatus() {
        return updatedSchoolStatus;
    }

    public void setUpdatedSchoolStatus(String updatedSchoolStatus) {
        this.updatedSchoolStatus = updatedSchoolStatus;
    }

    public String getCompletedbyDesignation() {
        return completedbyDesignation;
    }

    public void setCompletedbyDesignation(String completedbyDesignation) {
        this.completedbyDesignation = completedbyDesignation;
    }

    public String getCompletedbyName() {
        return completedbyName;
    }

    public void setCompletedbyName(String completedbyName) {
        this.completedbyName = completedbyName;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getEnrolledInCare() {
        return enrolledInCare;
    }

    public void setEnrolledInCare(String enrolledInCare) {
        this.enrolledInCare = enrolledInCare;
    }

    public String getPointOfService() {
        return pointOfService;
    }

    public void setPointOfService(String pointOfService) {
        this.pointOfService = pointOfService;
    }

    /*public BeneficiaryResourceManager getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryResourceManager beneficiary) {
        this.beneficiary = beneficiary;
    }*/

    public String getEnrolledOnART() 
    {
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

    public String getCgiverEnrolledInCare() {
        return cgiverEnrolledInCare;
    }

    public void setCgiverEnrolledInCare(String cgiverEnrolledInCare) {
        this.cgiverEnrolledInCare = cgiverEnrolledInCare;
    }

    public String getCgiverEnrolledOnART() {
        return cgiverEnrolledOnART;
    }

    public void setCgiverEnrolledOnART(String cgiverEnrolledOnART) {
        this.cgiverEnrolledOnART = cgiverEnrolledOnART;
    }

    public String getCgiverFacilityId() {
        return cgiverFacilityId;
    }

    public void setCgiverFacilityId(String cgiverFacilityId) {
        this.cgiverFacilityId = cgiverFacilityId;
    }

    public LegacyChildStatusIndex getChildStatusIndex() 
    {
        if(childStatusIndex==null)
        childStatusIndex=new LegacyChildStatusIndex();
        return childStatusIndex;
    }

    public void setChildStatusIndex(LegacyChildStatusIndex childStatusIndex) {
        this.childStatusIndex = childStatusIndex;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }


}
