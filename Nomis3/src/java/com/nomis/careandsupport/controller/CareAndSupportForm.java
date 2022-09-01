/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.careandsupport.controller;

import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ValidationManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class CareAndSupportForm extends org.apache.struts.action.ActionForm {
    
    
    private int recordId;
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int hivStatus;
        
    private int age;
    private String ageUnit;
    private int hhSerialNo; 
    private String hhUniqueId;
    private String beneficiaryId;
    private String surname;
    private String firstname;
    private String address;
    private String occupation;
    private String sex;
    private String phoneNumber;
    private String dateOfEnrollment;
    private String volunteerName;
    
    private int coughSymptom;
    private int childLossinWeight;
    private int childHasFever;
    private int childHasNightSweat;
    private int childHasSwelling;
    private int enrolledOnTreatment;
    private String facilityId;
    private int pickedUpMedication;
    private int missedARVsRecently;
    private String[] reasonsPeopleSkipARV;
    private int viralLoadTestDone;
    private String dateOfViralLoadSampleCollection;
    private int viralLoadResultKnown;
    private int viralLoadResult;
    private String reasonViralLoadNotDone;
    private int receivedTransportationSupport;
    private int monthsOfTransportationSupport;
    private int soresRashPainExperience;
    private String dateOfNextAppointment;
    private String dateOfAssessment;
    private String dateOfLastPickup;
    private int numberOfDaysOfRefill;
    private String dateEnrolledOnTreatment;
    private String dateOfViralLoadResult;
    
    
    
    public CareAndSupportForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
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

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getDateOfNextAppointment() {
        return dateOfNextAppointment;
    }

    public void setDateOfNextAppointment(String dateOfNextAppointment) {
        this.dateOfNextAppointment = dateOfNextAppointment;
    }

    public String getDateOfLastPickup() {
        return dateOfLastPickup;
    }

    public void setDateOfLastPickup(String dateOfLastPickup) {
        this.dateOfLastPickup = dateOfLastPickup;
    }

    public String getDateOfViralLoadSampleCollection() {
        return dateOfViralLoadSampleCollection;
    }

    public void setDateOfViralLoadSampleCollection(String dateOfViralLoadSampleCollection) {
        this.dateOfViralLoadSampleCollection = dateOfViralLoadSampleCollection;
    }

    public int getNumberOfDaysOfRefill() {
        return numberOfDaysOfRefill;
    }

    public void setNumberOfDaysOfRefill(int numberOfDaysOfRefill) {
        this.numberOfDaysOfRefill = numberOfDaysOfRefill;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getHhSerialNo() {
        return hhSerialNo;
    }

    public void setHhSerialNo(int hhSerialNo) {
        this.hhSerialNo = hhSerialNo;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public int getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(int hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
    }

    public String getLevel3OuId() {
        return level3OuId;
    }

    public void setLevel3OuId(String level3OuId) {
        this.level3OuId = level3OuId;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String[] getReasonsPeopleSkipARV() {
        return reasonsPeopleSkipARV;
    }

    public void setReasonsPeopleSkipARV(String[] reasonsPeopleSkipARV) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSoresRashPainExperience() {
        return soresRashPainExperience;
    }

    public void setSoresRashPainExperience(int soresRashPainExperience) {
        this.soresRashPainExperience = soresRashPainExperience;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(String dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public String getDateOfViralLoadResult() {
        return dateOfViralLoadResult;
    }

    public void setDateOfViralLoadResult(String dateOfViralLoadResult) {
        this.dateOfViralLoadResult = dateOfViralLoadResult;
    }

    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    
    //hhSerialNo=0; 
    //hhUniqueId=null;
    //beneficiaryId=null;
    surname=null;
    firstname=null;
    address=null;
    occupation=null;
    sex=null;
    phoneNumber=null;
    dateOfEnrollment=null;
    volunteerName=null;
    facilityId=null;
    reasonsPeopleSkipARV=null;
    dateOfLastPickup=null;
    numberOfDaysOfRefill=0;
    dateOfViralLoadSampleCollection=null;
    reasonViralLoadNotDone=null;
    dateOfNextAppointment=null;
    dateOfAssessment=null;
    recordId=0;
    hivStatus=0;
    age=0;
    ageUnit=null;
    coughSymptom=0;
    childLossinWeight=0;
    childHasFever=0;
    childHasNightSweat=0;
    childHasSwelling=0;
    enrolledOnTreatment=0;
    pickedUpMedication=0;
    missedARVsRecently=0;
    viralLoadTestDone=0;
    viralLoadResultKnown=0;
    viralLoadResult=0;
    receivedTransportationSupport=0;
    monthsOfTransportationSupport=0;
    soresRashPainExperience=0;
    dateEnrolledOnTreatment=null;
    dateOfViralLoadResult=null;
    
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("householdDetails") || getActionName().equalsIgnoreCase("beneficiaryDetails") || getActionName().equalsIgnoreCase("assessmentDetails") || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        DateManager dm=new DateManager();
        Beneficiary beneficiary=BeneficiaryManager.getBeneficiary(beneficiaryId);
        if(this.getDateOfAssessment()==null || this.getDateOfAssessment().indexOf("/")==-1)
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
        else if(!ValidationManager.isValidDate(getDateOfAssessment()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateOfAssessment()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.postdated"));
        
        else if(this.getBeneficiaryId()==null || this.getBeneficiaryId().equalsIgnoreCase("select"))
        errors.add("beneficiaryId", new ActionMessage("errors.beneficiaryId.required"));
        //if beneficiary is a child, validate child specific fields
        else if(beneficiary !=null && beneficiary.getBeneficiaryType()==AppConstant.OVC_TYPE_NUM)
        {
            if(!ValidationManager.dateAfterEnrollmentDate(this.getBeneficiaryId(),getDateOfAssessment(),AppConstant.OVC_TYPE_NUM))
            errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.beforeEnrollment"));
            else if(this.getCoughSymptom()==0)
            errors.add("coughSymptom", new ActionMessage("errors.coughSymptom.required"));
            else if(this.getChildLossinWeight()==0)
            errors.add("childLossinWeight", new ActionMessage("errors.childLossinWeight.required"));
            else if(this.getChildHasFever()==0)
            errors.add("childHasFever", new ActionMessage("errors.childHasFever.required"));
            else if(this.getChildHasNightSweat()==0)
            errors.add("childHasNightSweat", new ActionMessage("errors.childHasNightSweat.required"));
            else if(this.getChildHasSwelling()==0)
            errors.add("childHasSwelling", new ActionMessage("errors.childHasSwelling.required"));
        }
        else if(beneficiary !=null && beneficiary.getBeneficiaryType()==AppConstant.CAREGIVER_TYPE_NUM)
        {
            if(!ValidationManager.dateAfterEnrollmentDate(this.getBeneficiaryId(),getDateOfAssessment(),AppConstant.CAREGIVER_TYPE_NUM))
            errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.beforeEnrollment"));
        }
        //Check if beneficiary is enrolled on treatment. If yes, validate the treatment related fields
        else if(this.getEnrolledOnTreatment()==0)
        errors.add("enrolledOnTreatment", new ActionMessage("errors.enrolledOnTreatment.required"));
        else if(this.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
        {
            String dateOfCurrentHivStatus=DateManager.convertDateToString(beneficiary.getDateOfCurrentHivStatus(),DateManager.MM_DD_YYYY_SLASH);
            
            System.err.println("dateOfCurrentHivStatus is "+dateOfCurrentHivStatus);
            System.err.println("getDateEnrolledOnTreatment() is "+getDateEnrolledOnTreatment());
            
            if(this.getDateEnrolledOnTreatment()==null || this.getDateEnrolledOnTreatment().indexOf("/")==-1)
            errors.add("dateEnrolledOnTreatment", new ActionMessage("errors.dateEnrolledOnTreatment.required"));
            else if(!ValidationManager.isDateEnrolledOnTreatmentBeforeDateOfHivStatus(dateOfCurrentHivStatus,getDateEnrolledOnTreatment()))
            errors.add("dateEnrolledOnTreatment", new ActionMessage("errors.dateEnrolledOnTreatment.beforeDateOfCurrentHivStatus"));
            
            if((this.getFacilityId()==null || this.getFacilityId().trim().equalsIgnoreCase("select")))
            errors.add("facilityId", new ActionMessage("errors.hivTreatmentFacilityId.required"));
            else if(this.getPickedUpMedication()==0)
            errors.add("pickedUpMedication", new ActionMessage("errors.pickedUpMedication.required"));
            else if(this.getPickedUpMedication()==1)
            {
                if(this.getMissedARVsRecently()==0)
                errors.add("missedARVsRecently", new ActionMessage("errors.missedARVsRecently.required"));
                if(this.getMissedARVsRecently()==1)
                {
                    if(this.getReasonsPeopleSkipARV()==null)
                    errors.add("reasonsPeopleSkipARV", new ActionMessage("errors.reasonsPeopleSkipARV.required"));
                }
            }
            else if(this.getViralLoadTestDone()==0)
            errors.add("viralLoadTestDone", new ActionMessage("errors.viralLoadTestDone.required"));
            else if(this.getViralLoadTestDone()==AppConstant.YES_OPTION_NUM)
            {
                if(this.getDateOfViralLoadSampleCollection()==null || this.getDateOfViralLoadSampleCollection().indexOf("/") ==-1 
                || DateManager.getDateInstanceFromMthDayYearFormat(getDateOfViralLoadSampleCollection()).equals(DateManager.getDateInstance(DateManager.DEFAULT_DATE)))
                errors.add("dateOfViralLoadSampleCollection", new ActionMessage("errors.dateOfViralLoadSampleCollection.required"));
                /*else if(this.getDateEnrolledOnTreatment() !=null && this.getDateEnrolledOnTreatment().equals(DateManager.getDateInstance(DateManager.DEFAULT_DATE)))
                errors.add("dateOfViralLoadSampleCollection", new ActionMessage("errors.dateOfViralLoadSampleCollection.incorrect"));*/
                else if(beneficiary !=null && beneficiary.getDateEnrolledOnTreatment() !=null && beneficiary.getDateEnrolledOnTreatment().after(DateManager.getDateInstanceFromMthDayYearFormat(this.getDateOfViralLoadSampleCollection())))
                errors.add("dateOfViralLoadSampleCollection", new ActionMessage("errors.dateOfViralLoadSampleCollection.beforeDateEnrolledOnTreatment"));
                else if(dm.getDifferenceInDays(getDateEnrolledOnTreatment(), getDateOfViralLoadSampleCollection(), DateManager.MM_DD_YYYY_SLASH) <180)
                errors.add("dateOfViralLoadSampleCollection", new ActionMessage("errors.beneficiary.NotDueForViralLoadTest"));
                else if(this.getViralLoadResultKnown()==0)
                errors.add("viralLoadResultKnown", new ActionMessage("errors.viralLoadResultKnown.required"));
            }
            else if(this.getViralLoadResult()==AppConstant.YES_OPTION_NUM)
            {
                if(this.getDateOfViralLoadResult()==null || getDateOfViralLoadResult().indexOf("/") ==-1)
                errors.add("dateOfViralLoadResult", new ActionMessage("errors.dateOfViralLoadResult.required"));
                else if(beneficiary !=null && beneficiary.getDateEnrolledOnTreatment() !=null && beneficiary.getDateEnrolledOnTreatment().after(DateManager.getDateInstanceFromMthDayYearFormat(this.getDateOfViralLoadResult())))
                errors.add("dateOfViralLoadResult", new ActionMessage("errors.dateOfViralLoadResult.beforeDateEnrolledOnTreatment"));
                else if(DateManager.getDateInstanceFromMthDayYearFormat(this.getDateOfViralLoadResult()).before(DateManager.getDateInstanceFromMthDayYearFormat(this.getDateOfViralLoadSampleCollection())))
                errors.add("dateOfViralLoadResult", new ActionMessage("errors.dateOfViralLoadResult.beforeDateOfSampleCollection"));
            }
            else if(this.getReceivedTransportationSupport()==0)
            errors.add("receivedTransportationSupport", new ActionMessage("errors.receivedTransportationSupport.required"));
            else if(this.getReceivedTransportationSupport()==1 && this.getMonthsOfTransportationSupport()==0)
            errors.add("monthsOfTransportationSupport", new ActionMessage("errors.monthsOfTransportationSupport.required"));
            
        }
        else if(this.getSoresRashPainExperience()==0)
        errors.add("soresRashPainExperience", new ActionMessage("errors.soresRashPainExperience.required"));
        else if(this.getDateOfNextAppointment() !=null && this.getDateOfNextAppointment().indexOf("/") !=-1)
        {
            //check if date of next appointment is before date of assessment
            if(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateOfNextAppointment())).before(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateOfAssessment()))))
             errors.add("dateOfNextAppointment", new ActionMessage("errors.dateOfNextAppointment.beforeDateOfAssessment"));
        }
        
        return errors;
        
    }
}
