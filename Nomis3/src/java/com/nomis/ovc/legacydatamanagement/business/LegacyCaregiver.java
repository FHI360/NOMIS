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
public class LegacyCaregiver implements Serializable
{
    private LegacyHouseholdEnrollment hhe;
    private String caregiverId;
    private String cgEnrollmentId;
    private String newCaregiverId;
    private String hhUniqueId;
    private String caregiverFirstname;
    private String caregiverLastName;
    private String caregiverFullName;
    private String caregiverGender;
    private LegacyHivStatusUpdate currentHivStatus;
    private String dateOfCurrentHivStatus;
    private LegacyHivStatusUpdate activeHivStatus;
    private int caregiverAge;
    private int currentAge;
    private String caregiverPhone;
    private String caregiverAddress;
    private String caregiverOccupation;
    private String caregiverMaritalStatus;
    private String cgiverHivStatus;
    private String dateOfEnrollment;
    private String dateModified;
    private String enrolledInCare="No";
    private String enrolledOnART="No";
    private String enrolledOnTreatment="No";
    private String facilityId;
    private String pointOfService="enrollment";
    private String householdhead;
    private boolean active=false;
    private String withdrawnFromProgram="No";
    private String reasonForWithdrawal="Active";
    //private String currentCaregiverStatus="Active";
    //private String dateOfCurrentCaregiverStatus;
    private String dateOfWithdrawal;
    private int markedForDelete;
    private LegacyReferralDirectory facility;
    //private HouseholdService hhs=new HouseholdService();
    
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    public String getCaregiverAddress() 
    {
        if(caregiverAddress ==null)
        {
            //if(beneficiary.getHouseholdHead(hhUniqueId) !=null)
            //caregiverAddress=beneficiary.getHouseholdHead(hhUniqueId).getAddress();
        }
        return caregiverAddress;
    }

    public String getCgEnrollmentId() {
        return cgEnrollmentId;
    }

    public void setCgEnrollmentId(String cgEnrollmentId) {
        this.cgEnrollmentId = cgEnrollmentId;
    }

    public boolean isActive() 
    {
        return active;
    }

    public void setCaregiverAddress(String caregiverAddress) {
        this.caregiverAddress = caregiverAddress;
    }

    public int getCaregiverAge() {
        return caregiverAge;
    }

    public void setCaregiverAge(int caregiverAge) {
        this.caregiverAge = caregiverAge;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public String getCaregiverFirstname() {
        return caregiverFirstname;
    }

    public void setCaregiverFirstname(String caregiverFirstname) {
        this.caregiverFirstname = caregiverFirstname;
    }

    public void setCaregiverFullName(String caregiverFullName) {
        this.caregiverFullName = caregiverFullName;
    }

    public String getCaregiverFullName() 
    {
        if(caregiverFullName==null)
        caregiverFullName=caregiverFirstname+" "+caregiverLastName;
        return caregiverFullName;
    }

    public String getCgiverHivStatus() {
        return cgiverHivStatus;
    }

    public void setCgiverHivStatus(String cgiverHivStatus) {
        this.cgiverHivStatus = cgiverHivStatus;
    }
    
    
    public String getCaregiverGender() {
        return caregiverGender;
    }

    public void setCaregiverGender(String caregiverGender) {
        this.caregiverGender = caregiverGender;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public String getNewCaregiverId() {
        return newCaregiverId;
    }

    public void setNewCaregiverId(String newCaregiverId) {
        this.newCaregiverId = newCaregiverId;
    }

    public String getCaregiverLastName() {
        return caregiverLastName;
    }

    public void setCaregiverLastName(String caregiverLastName) {
        this.caregiverLastName = caregiverLastName;
    }

    public String getCaregiverOccupation() {
        return caregiverOccupation;
    }

    public void setCaregiverOccupation(String caregiverOccupation) {
        this.caregiverOccupation = caregiverOccupation;
    }

    public String getCaregiverMaritalStatus() {
        return caregiverMaritalStatus;
    }

    public void setCaregiverMaritalStatus(String caregiverMaritalStatus) {
        this.caregiverMaritalStatus = caregiverMaritalStatus;
    }

    public String getCaregiverPhone() {
        return caregiverPhone;
    }

    public void setCaregiverPhone(String caregiverPhone) {
        this.caregiverPhone = caregiverPhone;
    }

    public LegacyHivStatusUpdate getActiveHivStatus() {
        //HivStatus hsu=new HivStatus();
        //activeHivStatus=beneficiary.getActiveHivStatus(caregiverId, dateOfEnrollment);
        
        this.enrolledInCare=activeHivStatus.getClientEnrolledInCare();
        this.setEnrolledOnART(activeHivStatus.getEnrolledOnART());
        return activeHivStatus;
    }

        
public LegacyHivStatusUpdate getCurrentHivStatus() 
{
    LegacyHivStatus hsu=new LegacyHivStatus();
    currentHivStatus=hsu.getCurrentHivStatus(caregiverId,dateOfEnrollment);
    if(currentHivStatus==null)
    {
        currentHivStatus=getActiveHivStatus();
    }
    return currentHivStatus;
}

    
    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }
    
    public String getDateModified()
    {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getHouseholdhead() {
        return householdhead;
    }

    public void setHouseholdhead(String householdhead) {
        this.householdhead = householdhead;
    }
    
    /*public boolean isHouseholdhead() {
        return householdhead;
    }

    public void setHouseholdhead(boolean householdhead) {
        this.householdhead = householdhead;
    }*/

    public String getEnrolledInCare() {
        return enrolledInCare;
    }

    public void setEnrolledInCare(String enrolledInCare) {
        this.enrolledInCare = enrolledInCare;
    }

    public String getPointOfService() {
        return pointOfService;
    }

    public void setPointOfService(String pointOfService) {
        this.pointOfService = pointOfService;
    }

    public String getDateOfCurrentHivStatus() {
        return dateOfCurrentHivStatus;
    }

    public void setDateOfCurrentHivStatus(String dateOfCurrentHivStatus) {
        this.dateOfCurrentHivStatus = dateOfCurrentHivStatus;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getDateOfWithdrawal() 
    {
        LegacyOvcWithdrawal withdrawal=null;
        //withdrawal=beneficiary.getWithdrawalRecord(caregiverId,getWithdrawnFromProgram());
        //if(withdrawal !=null)
        //dateOfWithdrawal=withdrawal.getDateOfWithdrawal();
        return dateOfWithdrawal;
    }

    public void setDateOfWithdrawal(String dateOfWithdrawal) {
        this.dateOfWithdrawal = dateOfWithdrawal;
    }

    public String getReasonForWithdrawal() 
    {
        LegacyOvcWithdrawal withdrawal=null;
        //withdrawal=beneficiary.getWithdrawalRecord(caregiverId,getWithdrawnFromProgram());
        if(withdrawal !=null)
        reasonForWithdrawal=withdrawal.getReasonWithdrawal();
        return reasonForWithdrawal;
    }

    public void setReasonForWithdrawal(String reasonForWithdrawal) {
        this.reasonForWithdrawal = reasonForWithdrawal;
    }

    public String getWithdrawnFromProgram() {
        return withdrawnFromProgram;
    }

    public void setWithdrawnFromProgram(String withdrawnFromProgram) {
        this.withdrawnFromProgram = withdrawnFromProgram;
    }

    public String getEnrolledOnART() {
        return enrolledOnART;
    }

    public void setEnrolledOnART(String enrolledOnART) {
        this.enrolledOnART = enrolledOnART;
    }

    public String getEnrolledOnTreatment() {
        if((getEnrolledOnART() !=null && getEnrolledOnART().equalsIgnoreCase("Yes")) || (getEnrolledInCare() !=null && getEnrolledInCare().equalsIgnoreCase("Yes")))
        enrolledOnTreatment="Yes";
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(String enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public LegacyHouseholdEnrollment getHhe() 
    {
        //if(hhe==null)
        //hhe=beneficiary.getHouseholdHead(hhUniqueId);
        return hhe;
    }

    /*public String getCurrentCaregiverStatus() 
    {
        String cgWithdrawnValue=getReasonForWithdrawal();
        if(cgWithdrawnValue !=null)
            currentCaregiverStatus=cgWithdrawnValue;
        return currentCaregiverStatus;
    }

    public void setCurrentCaregiverStatus(String currentCaregiverStatus) {
        this.currentCaregiverStatus = currentCaregiverStatus;
    }

    public String getDateOfCurrentCaregiverStatus() 
    {
        String dateWithdrawn=getDateOfWithdrawal();
        if(dateWithdrawn !=null)
        dateOfCurrentCaregiverStatus=dateWithdrawn;
        return dateOfCurrentCaregiverStatus;
    }

    public void setDateOfCurrentCaregiverStatus(String dateOfCurrentCaregiverStatus) {
        this.dateOfCurrentCaregiverStatus = dateOfCurrentCaregiverStatus;
    }*/

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public LegacyReferralDirectory getFacility() {
        if(facility==null)
        {
            //facility=beneficiary.getReferralDirectory(facilityId);
        }
        return facility;
    }

    public void setFacility(LegacyReferralDirectory facility) {
        this.facility = facility;
    }

    
}
