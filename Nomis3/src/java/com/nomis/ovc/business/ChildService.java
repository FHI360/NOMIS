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
public class ChildService implements Serializable
{
    private int id;
    //private String legacyMemberId;
    private String serviceId;
    private String ovcId;
    private Date serviceDate;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String stableServices;
    private String healthServices;
    private String safetyServices;
    private String schooledServices;
    private String referralServices;
    private String gbvServices;
    
    private String stableServiceNames;
    private String healthServiceNames;
    private String safetyServiceNames;
    private String schooledServiceNames;
    private String referralServiceNames;
    private String gbvServiceNames;
    
    private String recordedBy;
    private String communityWorkerId;
    
    private int numberOfServices;
    private int enrollmentStatus;
    private Date dateOfEnrollmentStatus;
    private int markedForDelete;
    private int ageAtService;
    private int ageUnitAtService;
    
    private int childMissedSchool;
    private int childAbused;
    private int abusedChildLinkedToGovt;
    int serialNo=0;
    String rowColor="#FFFFFF";
    private Ovc ovc;
    private CommunityWorker communityWorker=null;
    AppUtility appUtil=new AppUtility();
    OvcServiceManager osm=new OvcServiceManager();

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
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

    public Ovc getOvc() 
    {
        ovc=BeneficiaryManager.getOvc(ovcId);
        return ovc;
    }

    public int getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(int enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public Date getDateOfEnrollmentStatus() {
        return dateOfEnrollmentStatus;
    }

    public void setDateOfEnrollmentStatus(Date dateOfEnrollmentStatus) {
        this.dateOfEnrollmentStatus = dateOfEnrollmentStatus;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
    public String getGbvServices() {
        return gbvServices;
    }

    public void setGbvServices(String gbvServices) {
        this.gbvServices = gbvServices;
    }
    public String getHealthServiceNames() {
        healthServiceNames=OvcServiceAttributesManager.getConcatenatedServiceNames(appUtil.splitServices(this.getHealthServices()));
        return healthServiceNames;//OvcServiceAttributesManager
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

    public String getSchooledServiceNames() 
    {
        schooledServiceNames=OvcServiceAttributesManager.getConcatenatedServiceNames(appUtil.splitServices(this.getSchooledServices()));
        return appUtil.cleanString(schooledServiceNames,";");
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

    public String getCommunityWorkerId() {
        return communityWorkerId;
    }

    public void setCommunityWorkerId(String communityWorkerId) {
        this.communityWorkerId = communityWorkerId;
    }

    
    public int getChildAbused() {
        return childAbused;
    }

    public void setChildAbused(int childAbused) {
        this.childAbused = childAbused;
    }

    public int getAbusedChildLinkedToGovt() {
        return abusedChildLinkedToGovt;
    }

    public void setAbusedChildLinkedToGovt(int abusedChildLinkedToGovt) {
        this.abusedChildLinkedToGovt = abusedChildLinkedToGovt;
    }

    public int getChildMissedSchool() {
        return childMissedSchool;
    }

    public void setChildMissedSchool(int childMissedSchool) {
        this.childMissedSchool = childMissedSchool;
    }

    public CommunityWorker getCommunityWorker() {
        communityWorker=BeneficiaryManager.getCommunityWorker(communityWorkerId);
        return communityWorker;
    }

}
