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
public class HivPositiveDataManager implements Serializable
{
    private String beneficiaryId;
    private int currentEnrollmentStatus;
    private Date dateOfCurrentEnrollmentStatus;
    private int currentHivStatus;
    private Date dateOfCurrentHivStatus;
    private int enrolledOnTreatment;
    private Date dateEnrolledOnTreatment;
    private int daySinceEnrolledOnTreatment;
    private Date lastModifiedDate;
    private int eligibleForViralLoad;
    private int currentViralLoad;
    private Date dateOfCurrentViralLoad;

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getCurrentEnrollmentStatus() {
        return currentEnrollmentStatus;
    }

    public void setCurrentEnrollmentStatus(int currentEnrollmentStatus) {
        this.currentEnrollmentStatus = currentEnrollmentStatus;
    }

    public int getCurrentHivStatus() {
        return currentHivStatus;
    }

    public void setCurrentHivStatus(int currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
    }

    public int getCurrentViralLoad() {
        return currentViralLoad;
    }

    public void setCurrentViralLoad(int currentViralLoad) {
        this.currentViralLoad = currentViralLoad;
    }

    public Date getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(Date dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public Date getDateOfCurrentEnrollmentStatus() {
        return dateOfCurrentEnrollmentStatus;
    }

    public void setDateOfCurrentEnrollmentStatus(Date dateOfCurrentEnrollmentStatus) {
        this.dateOfCurrentEnrollmentStatus = dateOfCurrentEnrollmentStatus;
    }

    public Date getDateOfCurrentHivStatus() {
        return dateOfCurrentHivStatus;
    }

    public void setDateOfCurrentHivStatus(Date dateOfCurrentHivStatus) {
        this.dateOfCurrentHivStatus = dateOfCurrentHivStatus;
    }

    public Date getDateOfCurrentViralLoad() {
        return dateOfCurrentViralLoad;
    }

    public void setDateOfCurrentViralLoad(Date dateOfCurrentViralLoad) {
        this.dateOfCurrentViralLoad = dateOfCurrentViralLoad;
    }

    public int getDaySinceEnrolledOnTreatment() {
        return daySinceEnrolledOnTreatment;
    }

    public void setDaySinceEnrolledOnTreatment(int daySinceEnrolledOnTreatment) {
        this.daySinceEnrolledOnTreatment = daySinceEnrolledOnTreatment;
    }

    public int getEligibleForViralLoad() {
        return eligibleForViralLoad;
    }

    public void setEligibleForViralLoad(int eligibleForViralLoad) {
        this.eligibleForViralLoad = eligibleForViralLoad;
    }

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
}
