/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.enrollmentstatus.controller;

import com.nomis.ovc.util.DateManager;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class EnrollmentStatusManagementForm extends org.apache.struts.action.ActionForm {
    
    private int recordId;
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int hivStatus;
    private int applyToEntireHousehold;
    private int currentEnrollmentStatus;
    private String dateOfCurrentEnrollmentStatus;
    private int newEnrollmentStatus;
    private String dateOfNewEnrollmentStatus;    
    private int currentAge;
    private String currentAgeUnit;
    
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
    
    
    public EnrollmentStatusManagementForm() {
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

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public String getCurrentAgeUnit() {
        return currentAgeUnit;
    }

    public void setCurrentAgeUnit(String currentAgeUnit) {
        this.currentAgeUnit = currentAgeUnit;
    }

    public int getCurrentEnrollmentStatus() {
        return currentEnrollmentStatus;
    }

    public void setCurrentEnrollmentStatus(int currentEnrollmentStatus) {
        this.currentEnrollmentStatus = currentEnrollmentStatus;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getDateOfCurrentEnrollmentStatus() {
        return dateOfCurrentEnrollmentStatus;
    }

    public void setDateOfCurrentEnrollmentStatus(String dateOfCurrentEnrollmentStatus) {
        this.dateOfCurrentEnrollmentStatus = dateOfCurrentEnrollmentStatus;
    }

    public String getDateOfNewEnrollmentStatus() {
        return dateOfNewEnrollmentStatus;
    }

    public void setDateOfNewEnrollmentStatus(String dateOfNewEnrollmentStatus) {
        this.dateOfNewEnrollmentStatus = dateOfNewEnrollmentStatus;
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

    public int getNewEnrollmentStatus() {
        return newEnrollmentStatus;
    }

    public void setNewEnrollmentStatus(int newEnrollmentStatus) {
        this.newEnrollmentStatus = newEnrollmentStatus;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getApplyToEntireHousehold() {
        return applyToEntireHousehold;
    }

    public void setApplyToEntireHousehold(int applyToEntireHousehold) {
        this.applyToEntireHousehold = applyToEntireHousehold;
    }
    
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
    dateOfCurrentEnrollmentStatus=null;
    dateOfNewEnrollmentStatus=null;
    recordId=0;
    hivStatus=0;
    currentAge=0;
    currentAgeUnit=null;
    applyToEntireHousehold=0;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(getActionName()==null || getActionName().equalsIgnoreCase("householdDetails") || getActionName().equalsIgnoreCase("beneficiaryDetails"))
        return errors;
        else if(getApplyToEntireHousehold()==0 && (getBeneficiaryId()==null || getBeneficiaryId().equalsIgnoreCase("select")))
            errors.add("beneficiaryId", new ActionMessage("errors.beneficiaryId.required"));
        else if(this.getNewEnrollmentStatus()==0)
        errors.add("newEnrollmentStatus", new ActionMessage("errors.newEnrollmentStatus.required"));
        else if(this.getDateOfNewEnrollmentStatus()==null || getDateOfNewEnrollmentStatus().indexOf("/") ==-1)
        errors.add("dateOfNewEnrollmentStatus", new ActionMessage("error.dateOfNewEnrollmentStatus.required"));
        else
        {
            Date dateOfEnrollment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateOfEnrollment()));
            Date dateOfNewStatus=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateOfNewEnrollmentStatus()));
            if(dateOfEnrollment.before(dateOfNewStatus))
            errors.add("dateOfNewEnrollmentStatus", new ActionMessage("error.dateOfNewEnrollmentStatus.b4dateOfEnrollment"));
            if(DateManager.getCurrentDateInstance().before(dateOfNewStatus))
            errors.add("dateOfNewEnrollmentStatus", new ActionMessage("error.dateOfNewEnrollmentStatus.afterCurrentDate"));
        }
        return errors;
    }
}
