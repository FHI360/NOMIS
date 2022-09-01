/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.ReferralFacilityManager;
import com.nomis.ovc.util.SingleOptionManager;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class CareAndSupportChecklist implements Serializable
{
    private int recordId;
    private String beneficiaryId;
    private int coughSymptom;
    private int childLossinWeight;
    private int childHasFever;
    private int childHasNightSweat;
    private int childHasSwelling;
    private Date dateOfAssessment;
    private Date dateCreated;
    private Date lastModifiedDate;
    private int enrolledOnTreatment;
    private String facilityId;
    private int pickedUpMedication;
    private int missedARVsRecently;
    private String reasonsPeopleSkipARV;
    private int viralLoadTestDone;
    
    private Date dateOfViralLoadSampleCollection;
    private int viralLoadResultKnown;
    private int viralLoadResult;
    private String reasonViralLoadNotDone;
    private int receivedTransportationSupport;
    private int monthsOfTransportationSupport;
    private int soresRashPainExperience;
    private Date dateOfNextAppointment;
    private int markedForDelete;
    private String volunteerName;
    private String recordedBy;
    private Date dateOfLastDrugPickup;
    private int numberOfDaysOfRefill;
    private Date dateEnrolledOnTreatment;
        
    private String coughSymptomOptionName;
    private String childLossinWeightOptionName;
    private String childHasFeverOptionName;
    private String childHasNightSweatOptionName;
    private String childHasSwellingOptionName;
    private String enrolledOnTreatmentOptionName;
    private String facilityName;
    private String pickedUpMedicationOptionName;
    private String missedARVsRecentlyOptionName;
    private String reasonsPeopleSkipARVOptionName;
    private String viralLoadTestDoneOptionName;
    private String viralLoadResultKnownOptionName;
    private String viralLoadResultOptionName;
    private String reasonViralLoadNotDoneOptionName;
    private String receivedTransportationSupportOptionName;
    private String monthsOfTransportationSupportOptionName;
    private String soresRashPainExperienceOptionName;
    private Date dateOfViralLoadResult;
        
    private int serialNo=0;
    String rowColor=AppConstant.FIRSTREPORTROWCOLOUR;
    private Beneficiary beneficiary;

    public Beneficiary getBeneficiary() 
    {
        if(beneficiary==null)
        beneficiary=BeneficiaryManager.getBeneficiary(beneficiaryId);
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public int getChildHasFever() {
        return childHasFever;
    }

    public void setChildHasFever(int childHasFever) {
        this.childHasFever = childHasFever;
    }

    public int getChildHasNightSweat() {
        return childHasNightSweat;
    }

    public void setChildHasNightSweat(int childHasNightSweat) {
        this.childHasNightSweat = childHasNightSweat;
    }

    public int getChildHasSwelling() {
        return childHasSwelling;
    }

    public void setChildHasSwelling(int childHasSwelling) {
        this.childHasSwelling = childHasSwelling;
    }

    public int getChildLossinWeight() {
        return childLossinWeight;
    }

    public void setChildLossinWeight(int childLossinWeight) {
        this.childLossinWeight = childLossinWeight;
    }

    public int getCoughSymptom() {
        return coughSymptom;
    }

    public void setCoughSymptom(int coughSymptom) {
        this.coughSymptom = coughSymptom;
    }

    public Date getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(Date dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfNextAppointment() {
        return dateOfNextAppointment;
    }

    public void setDateOfNextAppointment(Date dateOfNextAppointment) {
        this.dateOfNextAppointment = dateOfNextAppointment;
    }

    public Date getDateOfViralLoadSampleCollection() {
        return dateOfViralLoadSampleCollection;
    }

    public void setDateOfViralLoadSampleCollection(Date dateOfViralLoadSampleCollection) {
        this.dateOfViralLoadSampleCollection = dateOfViralLoadSampleCollection;
    }

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
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

    public int getMissedARVsRecently() {
        return missedARVsRecently;
    }

    public void setMissedARVsRecently(int missedARVsRecently) {
        this.missedARVsRecently = missedARVsRecently;
    }

    public int getMonthsOfTransportationSupport() {
        return monthsOfTransportationSupport;
    }

    public void setMonthsOfTransportationSupport(int monthsOfTransportationSupport) {
        this.monthsOfTransportationSupport = monthsOfTransportationSupport;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getPickedUpMedication() {
        return pickedUpMedication;
    }

    public void setPickedUpMedication(int pickedUpMedication) {
        this.pickedUpMedication = pickedUpMedication;
    }

    public String getReasonViralLoadNotDone() {
        return reasonViralLoadNotDone;
    }

    public void setReasonViralLoadNotDone(String reasonViralLoadNotDone) {
        this.reasonViralLoadNotDone = reasonViralLoadNotDone;
    }

    public String getReasonsPeopleSkipARV() {
        return reasonsPeopleSkipARV;
    }

    public void setReasonsPeopleSkipARV(String reasonsPeopleSkipARV) {
        this.reasonsPeopleSkipARV = reasonsPeopleSkipARV;
    }

    public int getReceivedTransportationSupport() {
        return receivedTransportationSupport;
    }

    public void setReceivedTransportationSupport(int receivedTransportationSupport) {
        this.receivedTransportationSupport = receivedTransportationSupport;
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

    public int getSoresRashPainExperience() {
        return soresRashPainExperience;
    }

    public void setSoresRashPainExperience(int soresRashPainExperience) {
        this.soresRashPainExperience = soresRashPainExperience;
    }

    public int getViralLoadResult() {
        return viralLoadResult;
    }

    public void setViralLoadResult(int viralLoadResult) {
        this.viralLoadResult = viralLoadResult;
    }

    public int getViralLoadResultKnown() {
        return viralLoadResultKnown;
    }

    public void setViralLoadResultKnown(int viralLoadResultKnown) {
        this.viralLoadResultKnown = viralLoadResultKnown;
    }

    public int getViralLoadTestDone() {
        return viralLoadTestDone;
    }

    public void setViralLoadTestDone(int viralLoadTestDone) {
        this.viralLoadTestDone = viralLoadTestDone;
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

    public String getChildHasFeverOptionName() {
        return childHasFeverOptionName=SingleOptionManager.getSingleChoiceOption(childHasFever).getName();
    }

    public String getChildHasNightSweatOptionName() {
        return childHasNightSweatOptionName=SingleOptionManager.getSingleChoiceOption(childHasNightSweat).getName();
    }

    public String getChildHasSwellingOptionName() {
        return childHasSwellingOptionName=SingleOptionManager.getSingleChoiceOption(childHasSwelling).getName();
    }

    public String getChildLossinWeightOptionName() {
        return childLossinWeightOptionName=SingleOptionManager.getSingleChoiceOption(childLossinWeight).getName();
    }

    public String getCoughSymptomOptionName() {
        return coughSymptomOptionName=SingleOptionManager.getSingleChoiceOption(coughSymptom).getName();
    }

    public String getEnrolledOnTreatmentOptionName() {
        return enrolledOnTreatmentOptionName=SingleOptionManager.getSingleChoiceOption(enrolledOnTreatment).getName();
    }

    public String getFacilityName() {
        return facilityName=ReferralFacilityManager.getReferralFacilityById(facilityId).getFacilityName();
    }

    public String getMissedARVsRecentlyOptionName() {
        return missedARVsRecentlyOptionName=SingleOptionManager.getSingleChoiceOption(missedARVsRecently).getName();
    }

    public String getMonthsOfTransportationSupportOptionName() {
        return monthsOfTransportationSupportOptionName=SingleOptionManager.getSingleChoiceOption(monthsOfTransportationSupport).getName();
    }

    public String getPickedUpMedicationOptionName() {
        return pickedUpMedicationOptionName=SingleOptionManager.getSingleChoiceOption(pickedUpMedication).getName();
    }

    public String getReasonViralLoadNotDoneOptionName() {
        return reasonViralLoadNotDoneOptionName;
    }

    public String getReasonsPeopleSkipARVOptionName() {
        return reasonsPeopleSkipARVOptionName;
    }

    public String getReceivedTransportationSupportOptionName() {
        return receivedTransportationSupportOptionName=SingleOptionManager.getSingleChoiceOption(receivedTransportationSupport).getName();
    }

    public String getSoresRashPainExperienceOptionName() {
        return soresRashPainExperienceOptionName=SingleOptionManager.getSingleChoiceOption(soresRashPainExperience).getName();
    }

    public String getViralLoadResultKnownOptionName() {
        return viralLoadResultKnownOptionName=SingleOptionManager.getSingleChoiceOption(viralLoadResultKnown).getName();
    }

    public String getViralLoadResultOptionName() {
        return viralLoadResultOptionName=SingleOptionManager.getSingleChoiceOption(viralLoadResult).getName();
    }

    public String getViralLoadTestDoneOptionName() {
        return viralLoadTestDoneOptionName=SingleOptionManager.getSingleChoiceOption(viralLoadTestDone).getName();
    }

    public Date getDateOfLastDrugPickup() {
        return dateOfLastDrugPickup;
    }

    public void setDateOfLastDrugPickup(Date dateOfLastDrugPickup) {
        this.dateOfLastDrugPickup = dateOfLastDrugPickup;
    }

    public int getNumberOfDaysOfRefill() {
        return numberOfDaysOfRefill;
    }

    public void setNumberOfDaysOfRefill(int numberOfDaysOfRefill) {
        this.numberOfDaysOfRefill = numberOfDaysOfRefill;
    }

    public Date getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(Date dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public Date getDateOfViralLoadResult() {
        return dateOfViralLoadResult;
    }

    public void setDateOfViralLoadResult(Date dateOfViralLoadResult) {
        this.dateOfViralLoadResult = dateOfViralLoadResult;
    }

}
