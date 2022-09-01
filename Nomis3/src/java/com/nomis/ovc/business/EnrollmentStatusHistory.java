/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class EnrollmentStatusHistory implements Serializable
{
    private int recordId;
    private String beneficiaryId;
    private int enrollmentStatus;
    private Date dateOfEnrollmentStatus;
    private int hivStatus;
    private Date dateOfHivStatus;
    private int enrolledOnTreatment;
    private String facilityId;
    private int currentAge;
    private int currentAgeUnit;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    private int beneficiaryType;
    private int pointOfUpdate;
    private int markedForDelete;

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(int beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public int getCurrentAgeUnit() {
        return currentAgeUnit;
    }

    public void setCurrentAgeUnit(int currentAgeUnit) {
        this.currentAgeUnit = currentAgeUnit;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfEnrollmentStatus() {
        return dateOfEnrollmentStatus;
    }

    public void setDateOfEnrollmentStatus(Date dateOfEnrollmentStatus) {
        this.dateOfEnrollmentStatus = dateOfEnrollmentStatus;
    }

    public Date getDateOfHivStatus() {
        return dateOfHivStatus;
    }

    public void setDateOfHivStatus(Date dateOfHivStatus) {
        this.dateOfHivStatus = dateOfHivStatus;
    }

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public int getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(int enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public int getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(int hivStatus) {
        this.hivStatus = hivStatus;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public int getPointOfUpdate() {
        return pointOfUpdate;
    }

    public void setPointOfUpdate(int pointOfUpdate) {
        this.pointOfUpdate = pointOfUpdate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }
    
}
