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
public class BeneficiaryService implements Serializable
{
    private int id;
    private String beneficiaryId;
    private Date serviceDate;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String stableServices;
    private String healthServices;
    private String safetyServices;
    private String schooledServices;
    private String referralServices;
    private String gbvServices;
    private String recordedBy;
    private String communityWorkerId;
    private int numberOfServices;
    private int enrollmentStatus;
    //private Date dateOfEnrollmentStatus;
    private int markedForDelete;
    private int ageAtService;
    private int ageUnitAtService;
    
    
    
    int serialNo=0;
    String rowColor="#FFFFFF";
    //private CommunityWorker communityWorker=null;

    public int getAgeAtService() {
        return ageAtService;
    }

    public void setAgeAtService(int ageAtService) {
        this.ageAtService = ageAtService;
    }

    public int getAgeUnitAtService() {
        return ageUnitAtService;
    }

    public void setAgeUnitAtService(int ageUnitAtService) {
        this.ageUnitAtService = ageUnitAtService;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getCommunityWorkerId() {
        return communityWorkerId;
    }

    public void setCommunityWorkerId(String communityWorkerId) {
        this.communityWorkerId = communityWorkerId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(int enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getGbvServices() {
        return gbvServices;
    }

    public void setGbvServices(String gbvServices) {
        this.gbvServices = gbvServices;
    }

    public String getHealthServices() {
        return healthServices;
    }

    public void setHealthServices(String healthServices) {
        this.healthServices = healthServices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumberOfServices() {
        return numberOfServices;
    }

    public void setNumberOfServices(int numberOfServices) {
        this.numberOfServices = numberOfServices;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getReferralServices() {
        return referralServices;
    }

    public void setReferralServices(String referralServices) {
        this.referralServices = referralServices;
    }

    public String getRowColor() {
        return rowColor;
    }

    public void setRowColor(String rowColor) {
        this.rowColor = rowColor;
    }

    public String getSafetyServices() {
        return safetyServices;
    }

    public void setSafetyServices(String safetyServices) {
        this.safetyServices = safetyServices;
    }

    public String getSchooledServices() {
        return schooledServices;
    }

    public void setSchooledServices(String schooledServices) {
        this.schooledServices = schooledServices;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getStableServices() {
        return stableServices;
    }

    public void setStableServices(String stableServices) {
        this.stableServices = stableServices;
    }
    
    
}
