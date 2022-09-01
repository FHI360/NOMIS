/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class HivRiskAssessmentReport implements Serializable
{
    private String beneficiaryId;
    private int outcomeOfLastHivRiskAssessment;
    private Date dateOfLastRiskAssessment;
    private int currentHivStatus;
    private Date dateOfCurrentHivStatus;
    private int currentEnrollmentStatus;
    private Date dateOfCurrentEnrollmentStatus;
    private int outcomeOfPreviousRiskAssessment;
    private Date dateOfPreviousRiskAssessment;
    //private int currentRiskAssessmentOutcome;
    
    private int daysSinceLastRiskAssessment;
    private Date lastUpdatedDate;
    private int childReferredForHIVTest;

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

    public Date getDateOfLastRiskAssessment() {
        return dateOfLastRiskAssessment;
    }

    public void setDateOfLastRiskAssessment(Date dateOfLastRiskAssessment) {
        this.dateOfLastRiskAssessment = dateOfLastRiskAssessment;
    }

    public Date getDateOfPreviousRiskAssessment() {
        return dateOfPreviousRiskAssessment;
    }

    public void setDateOfPreviousRiskAssessment(Date dateOfPreviousRiskAssessment) {
        this.dateOfPreviousRiskAssessment = dateOfPreviousRiskAssessment;
    }

    public int getDaysSinceLastRiskAssessment() {
        return daysSinceLastRiskAssessment;
    }

    public void setDaysSinceLastRiskAssessment(int daysSinceLastRiskAssessment) {
        this.daysSinceLastRiskAssessment = daysSinceLastRiskAssessment;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getOutcomeOfLastHivRiskAssessment() {
        return outcomeOfLastHivRiskAssessment;
    }

    public void setOutcomeOfLastHivRiskAssessment(int outcomeOfLastHivRiskAssessment) {
        this.outcomeOfLastHivRiskAssessment = outcomeOfLastHivRiskAssessment;
    }

    public int getOutcomeOfPreviousRiskAssessment() {
        return outcomeOfPreviousRiskAssessment;
    }

    public void setOutcomeOfPreviousRiskAssessment(int outcomeOfPreviousRiskAssessment) {
        this.outcomeOfPreviousRiskAssessment = outcomeOfPreviousRiskAssessment;
    }

    public int getChildReferredForHIVTest() {
        return childReferredForHIVTest;
    }

    public void setChildReferredForHIVTest(int childReferredForHIVTest) {
        this.childReferredForHIVTest = childReferredForHIVTest;
    }

}
