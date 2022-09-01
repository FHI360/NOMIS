/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class QuarterlyServiceTracker implements Serializable
{
    private int recordId;
    private String beneficiaryId;
    private int beneficiaryType;
    private int fYear;
    private int prevQ4Service;
    private int ageAtPrevQ4;
    private int q1Service;
    private int ageAtQ1;
    private int q2Service;
    private int ageAtQ2;
    private int q3Service;
    private int ageAtQ3;
    private int q4Service;
    private int ageAtQ4;
    private Date serviceDate;
    private Date dateCreated;
    private Date lastModifiedDate;
    private int markedForDelete;
    private int currentQuarter;
    private String recordedBy;
    
    public int getAgeAtQ1() {
        return ageAtQ1;
    }

    public void setAgeAtQ1(int ageAtQ1) {
        this.ageAtQ1 = ageAtQ1;
    }

    public int getAgeAtQ2() {
        return ageAtQ2;
    }

    public void setAgeAtQ2(int ageAtQ2) {
        this.ageAtQ2 = ageAtQ2;
    }

    public int getAgeAtQ3() {
        return ageAtQ3;
    }

    public void setAgeAtQ3(int ageAtQ3) {
        this.ageAtQ3 = ageAtQ3;
    }

    public int getAgeAtQ4() {
        return ageAtQ4;
    }

    public void setAgeAtQ4(int ageAtQ4) {
        this.ageAtQ4 = ageAtQ4;
    }

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getQ1Service() {
        return q1Service;
    }

    public void setQ1Service(int q1Service) {
        this.q1Service = q1Service;
    }

    public int getQ2Service() {
        return q2Service;
    }

    public void setQ2Service(int q2Service) {
        this.q2Service = q2Service;
    }

    public int getQ3Service() {
        return q3Service;
    }

    public void setQ3Service(int q3Service) {
        this.q3Service = q3Service;
    }

    public int getQ4Service() {
        return q4Service;
    }

    public void setQ4Service(int q4Service) {
        this.q4Service = q4Service;
    }

    public int getfYear() {
        return fYear;
    }

    public void setfYear(int fYear) {
        this.fYear = fYear;
    }
    
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getCurrentQuarter() {
        return currentQuarter;
    }

    public void setCurrentQuarter(int currentQuarter) {
        this.currentQuarter = currentQuarter;
    }

    public int getAgeAtPrevQ4() {
        return ageAtPrevQ4;
    }

    public void setAgeAtPrevQ4(int ageAtPrevQ4) {
        this.ageAtPrevQ4 = ageAtPrevQ4;
    }

    public int getPrevQ4Service() {
        return prevQ4Service;
    }

    public void setPrevQ4Service(int prevQ4Service) {
        this.prevQ4Service = prevQ4Service;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }
}
