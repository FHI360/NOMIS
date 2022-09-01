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
public class LegacyCareAndSupportChecklist implements Serializable
{
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    private LegacyOvc ovc;
    private int recordId;
    private String hhUniqueId;
    private String dateOfAssessment;
    private String dateOfHivTest;
    private String receivedTestResult;
    private String hivStatus;
    private String enrolledOnTreatment;
    private String dateEnrolledOnART;
    private String lastModifiedDate;
    private String treatmentFacility;
    private String viralLoadTestDone;
    private String dateOfViralLoadTest;
    private String medicationPickedUp;
    private String skippedARV;
    private String reasonsPeopleSkipARV;
    private String transportationSupport;
    private String experiencedSoresOrRash;
    private String hhSerialNo;
    private String clientId; 
    private String volunteerName;
    private String designation;
    private String providerPhone;
    private int approvalLevel;
    private int recordDeleted;
    private int markedForDelete;
    private LegacyReferralDirectory facility;
    
    public String getDateEnrolledOnART() {
        return dateEnrolledOnART;
    }

    public void setDateEnrolledOnART(String dateEnrolledOnART) {
        this.dateEnrolledOnART = dateEnrolledOnART;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getDateOfHivTest() {
        return dateOfHivTest;
    }

    public void setDateOfHivTest(String dateOfHivTest) {
        this.dateOfHivTest = dateOfHivTest;
    }

    public String getDateOfViralLoadTest() {
        return dateOfViralLoadTest;
    }

    public void setDateOfViralLoadTest(String dateOfViralLoadTest) {
        this.dateOfViralLoadTest = dateOfViralLoadTest;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(String enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public String getExperiencedSoresOrRash() {
        return experiencedSoresOrRash;
    }

    public void setExperiencedSoresOrRash(String experiencedSoresOrRash) {
        this.experiencedSoresOrRash = experiencedSoresOrRash;
    }

    public String getHhSerialNo() {
        return hhSerialNo;
    }

    public void setHhSerialNo(String hhSerialNo) {
        this.hhSerialNo = hhSerialNo;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(String hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getMedicationPickedUp() {
        return medicationPickedUp;
    }

    public void setMedicationPickedUp(String medicationPickedUp) {
        this.medicationPickedUp = medicationPickedUp;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getReasonsPeopleSkipARV() {
        return reasonsPeopleSkipARV;
    }

    public void setReasonsPeopleSkipARV(String reasonsPeopleSkipARV) {
        this.reasonsPeopleSkipARV = reasonsPeopleSkipARV;
    }

    public String getReceivedTestResult() {
        return receivedTestResult;
    }

    public void setReceivedTestResult(String receivedTestResult) {
        this.receivedTestResult = receivedTestResult;
    }

    public String getSkippedARV() {
        return skippedARV;
    }

    public void setSkippedARV(String skippedARV) {
        this.skippedARV = skippedARV;
    }

    public String getTransportationSupport() {
        return transportationSupport;
    }

    public void setTransportationSupport(String transportationSupport) {
        this.transportationSupport = transportationSupport;
    }

    public String getTreatmentFacility() {
        return treatmentFacility;
    }

    public void setTreatmentFacility(String treatmentFacility) {
        this.treatmentFacility = treatmentFacility;
    }

    public String getViralLoadTestDone() {
        return viralLoadTestDone;
    }

    public void setViralLoadTestDone(String viralLoadTestDone) {
        this.viralLoadTestDone = viralLoadTestDone;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public LegacyOvc getOvc() 
    {
        //ovc=LegacyBeneficiaryResourceManager.getOvc(clientId);
        return ovc;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public int getRecordDeleted() {
        return recordDeleted;
    }

    public void setRecordDeleted(int recordDeleted) {
        this.recordDeleted = recordDeleted;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public LegacyReferralDirectory getFacility() 
    {
        if(facility==null)
        {
            //facility=beneficiary.getReferralDirectory(treatmentFacility);
        }
        return facility;
    }

    public void setFacility(LegacyReferralDirectory facility) {
        this.facility = facility;
    }
}
