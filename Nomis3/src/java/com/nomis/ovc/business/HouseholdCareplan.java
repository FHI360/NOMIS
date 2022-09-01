/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryManager;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class HouseholdCareplan implements Serializable
{
    private int recordId;
    private String beneficiaryId;
    private Date dateCreated;
    private Date lastModifiedDate;
    private Date careplanDate;
    private String identifiedHealthIssues;
    private String householdHealthGoals;
    private String priorityHealthGoals;
    private String healthServicesToBeProvided;
    private String timeFrameForHealthServices;
    private String followupForHealthServices;
    
    private String identifiedSafetyIssues;
    private String householdSafetyGoals;
    private String prioritySafetyGoals;
    private String safetyServicesToBeProvided;
    private String timeFrameForSafetyServices;
    private String followupForSafetyServices;
    
    private String identifiedSchooledIssues;
    private String householdSchooledGoals;
    private String prioritySchooledGoals;
    private String schooledServicesToBeProvided;
    private String timeFrameForSchooledServices;
    private String followupForSchooledServices;
    
    private String identifiedStableIssues;
    private String householdStableGoals;
    private String priorityStableGoals;
    private String stableServicesToBeProvided;
    private String timeFrameForStableServices;
    private String followupForStableServices;
    
    private int markedForDelete;
    private String volunteerName;
    private String recordedBy;
    private HouseholdEnrollment hhe;
    private Beneficiary beneficiary;
    private CommunityWorker communityWorker=null;
    
    int serialNo=0;
    String rowColor="#FFFFFF";

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public Date getCareplanDate() {
        return careplanDate;
    }

    public void setCareplanDate(Date careplanDate) {
        this.careplanDate = careplanDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFollowupForHealthServices() {
        return followupForHealthServices;
    }

    public void setFollowupForHealthServices(String followupForHealthServices) {
        this.followupForHealthServices = followupForHealthServices;
    }

    public String getFollowupForSafetyServices() {
        return followupForSafetyServices;
    }

    public void setFollowupForSafetyServices(String followupForSafetyServices) {
        this.followupForSafetyServices = followupForSafetyServices;
    }

    public String getFollowupForSchooledServices() {
        return followupForSchooledServices;
    }

    public void setFollowupForSchooledServices(String followupForSchooledServices) {
        this.followupForSchooledServices = followupForSchooledServices;
    }

    public String getFollowupForStableServices() {
        return followupForStableServices;
    }

    public void setFollowupForStableServices(String followupForStableServices) {
        this.followupForStableServices = followupForStableServices;
    }

    public String getHealthServicesToBeProvided() {
        return healthServicesToBeProvided;
    }

    public void setHealthServicesToBeProvided(String healthServicesToBeProvided) {
        this.healthServicesToBeProvided = healthServicesToBeProvided;
    }

    public String getHouseholdHealthGoals() {
        return householdHealthGoals;
    }

    public void setHouseholdHealthGoals(String householdHealthGoals) {
        this.householdHealthGoals = householdHealthGoals;
    }

    public String getHouseholdSafetyGoals() {
        return householdSafetyGoals;
    }

    public void setHouseholdSafetyGoals(String householdSafetyGoals) {
        this.householdSafetyGoals = householdSafetyGoals;
    }

    public String getHouseholdSchooledGoals() {
        return householdSchooledGoals;
    }

    public void setHouseholdSchooledGoals(String householdSchooledGoals) {
        this.householdSchooledGoals = householdSchooledGoals;
    }

    public String getHouseholdStableGoals() {
        return householdStableGoals;
    }

    public void setHouseholdStableGoals(String householdStableGoals) {
        this.householdStableGoals = householdStableGoals;
    }

    public String getIdentifiedHealthIssues() {
        return identifiedHealthIssues;
    }

    public void setIdentifiedHealthIssues(String identifiedHealthIssues) {
        this.identifiedHealthIssues = identifiedHealthIssues;
    }

    public String getIdentifiedSafetyIssues() {
        return identifiedSafetyIssues;
    }

    public void setIdentifiedSafetyIssues(String identifiedSafetyIssues) {
        this.identifiedSafetyIssues = identifiedSafetyIssues;
    }

    public String getIdentifiedSchooledIssues() {
        return identifiedSchooledIssues;
    }

    public void setIdentifiedSchooledIssues(String identifiedSchooledIssues) {
        this.identifiedSchooledIssues = identifiedSchooledIssues;
    }

    public String getIdentifiedStableIssues() {
        return identifiedStableIssues;
    }

    public void setIdentifiedStableIssues(String identifiedStableIssues) {
        this.identifiedStableIssues = identifiedStableIssues;
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

    public String getPriorityHealthGoals() {
        return priorityHealthGoals;
    }

    public void setPriorityHealthGoals(String priorityHealthGoals) {
        this.priorityHealthGoals = priorityHealthGoals;
    }

    public String getPrioritySafetyGoals() {
        return prioritySafetyGoals;
    }

    public void setPrioritySafetyGoals(String prioritySafetyGoals) {
        this.prioritySafetyGoals = prioritySafetyGoals;
    }

    public String getPrioritySchooledGoals() {
        return prioritySchooledGoals;
    }

    public void setPrioritySchooledGoals(String prioritySchooledGoals) {
        this.prioritySchooledGoals = prioritySchooledGoals;
    }

    public String getPriorityStableGoals() {
        return priorityStableGoals;
    }

    public void setPriorityStableGoals(String priorityStableGoals) {
        this.priorityStableGoals = priorityStableGoals;
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

    public String getSafetyServicesToBeProvided() {
        return safetyServicesToBeProvided;
    }

    public void setSafetyServicesToBeProvided(String safetyServicesToBeProvided) {
        this.safetyServicesToBeProvided = safetyServicesToBeProvided;
    }

    public String getSchooledServicesToBeProvided() {
        return schooledServicesToBeProvided;
    }

    public void setSchooledServicesToBeProvided(String schooledServicesToBeProvided) {
        this.schooledServicesToBeProvided = schooledServicesToBeProvided;
    }

    public String getStableServicesToBeProvided() {
        return stableServicesToBeProvided;
    }

    public void setStableServicesToBeProvided(String stableServicesToBeProvided) {
        this.stableServicesToBeProvided = stableServicesToBeProvided;
    }

    public String getTimeFrameForHealthServices() {
        return timeFrameForHealthServices;
    }

    public void setTimeFrameForHealthServices(String timeFrameForHealthServices) {
        this.timeFrameForHealthServices = timeFrameForHealthServices;
    }

    public String getTimeFrameForSafetyServices() {
        return timeFrameForSafetyServices;
    }

    public void setTimeFrameForSafetyServices(String timeFrameForSafetyServices) {
        this.timeFrameForSafetyServices = timeFrameForSafetyServices;
    }

    public String getTimeFrameForSchooledServices() {
        return timeFrameForSchooledServices;
    }

    public void setTimeFrameForSchooledServices(String timeFrameForSchooledServices) {
        this.timeFrameForSchooledServices = timeFrameForSchooledServices;
    }

    public String getTimeFrameForStableServices() {
        return timeFrameForStableServices;
    }

    public void setTimeFrameForStableServices(String timeFrameForStableServices) {
        this.timeFrameForStableServices = timeFrameForStableServices;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
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

    public HouseholdEnrollment getHhe() {
        return hhe;
    }

    public void setHhe(HouseholdEnrollment hhe) {
        this.hhe = hhe;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }
     public CommunityWorker getCommunityWorker() {
        return communityWorker=BeneficiaryManager.getCommunityWorker(volunteerName);
    }
}
