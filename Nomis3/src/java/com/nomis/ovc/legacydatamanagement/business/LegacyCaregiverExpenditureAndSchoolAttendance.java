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
public class LegacyCaregiverExpenditureAndSchoolAttendance implements Serializable
{
    private int recordId;
    private LegacyCaregiver cgiver;
    private String caregiverId;
    private String dateOfAssessment;
    private String unexpectedExpenditure;
    private String accessMoney;
    private String sourceOfMoney;
    private String urgentHhNeeds;
    private String schAttendance;
    private String ovcId;
    private String ovcNames;
    private String reasonsForMissingSch;
    private String volunteerName;
    private String volunteerPhone;
    private String lastModifiedDate;
    private String recordedBy;
    private int markedForDelete;
    private LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();

    public String getAccessMoney() {
        return accessMoney;
    }

    public void setAccessMoney(String accessMoney) {
        this.accessMoney = accessMoney;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public LegacyCaregiver getCgiver() 
    {
        //cgiver=beneficiary.getCaregiver(caregiverId);
        return cgiver;
    }

    public void setCgiver(LegacyCaregiver cgiver) {
        this.cgiver = cgiver;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
    
    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
    }

    public String getOvcNames() {
        return ovcNames;
    }

    public void setOvcNames(String ovcNames) {
        this.ovcNames = ovcNames;
    }

    public String getReasonsForMissingSch() {
        return reasonsForMissingSch;
    }

    public void setReasonsForMissingSch(String reasonsForMissingSch) {
        this.reasonsForMissingSch = reasonsForMissingSch;
    }

    public String getSchAttendance() {
        return schAttendance;
    }

    public void setSchAttendance(String schAttendance) {
        this.schAttendance = schAttendance;
    }

    public String getSourceOfMoney() {
        return sourceOfMoney;
    }

    public void setSourceOfMoney(String sourceOfMoney) {
        this.sourceOfMoney = sourceOfMoney;
    }

    public String getUnexpectedExpenditure() {
        return unexpectedExpenditure;
    }

    public void setUnexpectedExpenditure(String unexpectedExpenditure) {
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

    public String getVolunteerPhone() {
        return volunteerPhone;
    }

    public void setVolunteerPhone(String volunteerPhone) {
        this.volunteerPhone = volunteerPhone;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
       
}
