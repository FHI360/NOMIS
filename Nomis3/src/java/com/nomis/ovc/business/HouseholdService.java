/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.operationsManagement.OvcServiceAttributesManager;
import com.nomis.operationsManagement.OvcServiceManager;
import com.nomis.ovc.util.AppUtility;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class HouseholdService implements Serializable
{
    private int id;
    private String serviceId;
    private String beneficiaryId;
    private Date serviceDate;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String stableServices;
    private String healthServices;
    private String safetyServices;
    private String referralServices;
    private String gbvServices;
    private String schoolServices;
    private int reasonWithdrawal;
    private String recordedBy;
    private int numberOfServices;
    private int enrollmentStatus;
    private int markedForDelete;
    private int ageAtService;
    
    private String stableServiceNames;
    private String healthServiceNames;
    private String safetyServiceNames;
    private String referralServiceNames;
    private String gbvServiceNames;
    private String schoolServiceNames;
    private String communityWorkerId;
    private Date dateOfEnrollmentStatus;
    int serialNo=0;
    String rowColor="#FFFFFF";
    AdultHouseholdMember ahm;
    AppUtility appUtil=new AppUtility();
    OvcServiceManager osm=new OvcServiceManager();
    
    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public int getReasonWithdrawal() {
        return reasonWithdrawal;
    }

    public void setReasonWithdrawal(int reasonWithdrawal) {
        this.reasonWithdrawal = reasonWithdrawal;
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

    public String getSafetyServices() {
        return safetyServices;
    }

    public void setSafetyServices(String safetyServices) {
        this.safetyServices = safetyServices;
    }

    public String getGbvServices() {
        return gbvServices;
    }

    public void setGbvServices(String gbvServices) {
        this.gbvServices = gbvServices;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getStableServices() {
        return stableServices;
    }

    public void setStableServices(String stableServices) {
        this.stableServices = stableServices;
    }

    public int getNumberOfServices() {
        return numberOfServices;
    }

    public void setNumberOfServices(int numberOfServices) {
        this.numberOfServices = numberOfServices;
    }

    public Date getDateOfEnrollmentStatus() {
        return dateOfEnrollmentStatus;
    }

    public void setDateOfEnrollmentStatus(Date dateOfEnrollmentStatus) {
        this.dateOfEnrollmentStatus = dateOfEnrollmentStatus;
    }

    public int getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(int enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getRowColor() {
        return rowColor;
    }

    public void setRowColor(String rowColor) {
        this.rowColor = rowColor;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public AdultHouseholdMember getAhm() 
    {
        if(ahm==null)
        ahm=BeneficiaryManager.getAdultHouseholdMember(beneficiaryId);
        return ahm;
    }

    public void setAhm(AdultHouseholdMember ahm) {
        this.ahm = ahm;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public int getAgeAtService() {
        return ageAtService;
    }

    public void setAgeAtService(int ageAtService) {
        this.ageAtService = ageAtService;
    }
    public String getHealthServiceNames() {
        healthServiceNames=OvcServiceAttributesManager.getConcatenatedServiceNames(appUtil.splitServices(this.getHealthServices()));
        return appUtil.cleanString(healthServiceNames,";");//return healthServiceNames;
    }

    public String getReferralServiceNames() 
    {
        referralServiceNames=OvcServiceAttributesManager.getConcatenatedServiceNames(appUtil.splitServices(this.getReferralServices()));
        return appUtil.cleanString(referralServiceNames,";");//return referralServiceNames;
    }

    public String getSafetyServiceNames() 
    {
        safetyServiceNames=OvcServiceAttributesManager.getConcatenatedServiceNames(appUtil.splitServices(this.getSafetyServices()));
        return appUtil.cleanString(safetyServiceNames,";");//return safetyServiceNames;
    }

    public String getStableServiceNames() 
    {
        stableServiceNames=OvcServiceAttributesManager.getConcatenatedServiceNames(appUtil.splitServices(this.getStableServices()));
        return appUtil.cleanString(stableServiceNames,";");
    }

    public String getGbvServiceNames() 
    {
        gbvServiceNames=OvcServiceAttributesManager.getConcatenatedServiceNames(appUtil.splitServices(this.getGbvServices()));
        return appUtil.cleanString(gbvServiceNames,";");
    }

    public String getSchoolServices() {
        return schoolServices;
    }

    public void setSchoolServices(String schoolServices) {
        this.schoolServices = schoolServices;
    }

    public String getSchoolServiceNames() 
    {
        schoolServiceNames=OvcServiceAttributesManager.getConcatenatedServiceNames(appUtil.splitServices(this.getSchoolServices()));
        return appUtil.cleanString(schoolServiceNames,";");
    }

    public String getCommunityWorkerId() {
        return communityWorkerId;
    }

    public void setCommunityWorkerId(String communityWorkerId) {
        this.communityWorkerId = communityWorkerId;
    }
    
}
