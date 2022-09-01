/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class CaregiverAccessToEmergencyFund implements Serializable
{
    private int recordId;
    private Date dateOfAssessment;
    private Date dateCreated;
    private Date lastModifiedDate;   
    private String beneficiaryId;
    private int unexpectedExpenditure;
    private int accessMoneyToPay;
    private String sourceOfMoney;
    private String urgentHhNeeds;
    private int markedForDelete;
    private String volunteerName;
    private String recordedBy;
    private CommunityWorker communityWorker;
    int serialNo=0;
    String rowColor=AppConstant.FIRSTREPORTROWCOLOUR;
    private AdultHouseholdMember adultHouseholdMember; 

    public int getAccessMoneyToPay() {
        return accessMoneyToPay;
    }

    public void setAccessMoneyToPay(int accessMoneyToPay) {
        this.accessMoneyToPay = accessMoneyToPay;
    }

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

    public Date getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(Date dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
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

    public String getSourceOfMoney() {
        return sourceOfMoney;
    }

    public void setSourceOfMoney(String sourceOfMoney) {
        this.sourceOfMoney = sourceOfMoney;
    }

    public int getUnexpectedExpenditure() {
        return unexpectedExpenditure;
    }

    public void setUnexpectedExpenditure(int unexpectedExpenditure) {
        this.unexpectedExpenditure = unexpectedExpenditure;
    }

    public String getUrgentHhNeeds() {
        return urgentHhNeeds;
    }

    public void setUrgentHhNeeds(String urgentHhNeeds) {
        this.urgentHhNeeds = urgentHhNeeds;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }
    public CommunityWorker getCommunityWorker() {
        return communityWorker=BeneficiaryManager.getCommunityWorker(volunteerName);
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

    public AdultHouseholdMember getAdultHouseholdMember() {
        return adultHouseholdMember;
    }

    public void setAdultHouseholdMember(AdultHouseholdMember adultHouseholdMember) {
        this.adultHouseholdMember = adultHouseholdMember;
    }
}
