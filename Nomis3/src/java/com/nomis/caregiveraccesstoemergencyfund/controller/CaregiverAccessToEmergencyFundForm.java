/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.caregiveraccesstoemergencyfund.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class CaregiverAccessToEmergencyFundForm extends org.apache.struts.action.ActionForm {
    
    private int recordId;
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private String dateOfAssessment;
    
    private int age;
    private int hhSerialNo; 
    private String hhUniqueId;
    private String surname;
    private String firstname;
    private String address;
    private String occupation;
    private String sex;
    private String phoneNumber;
    private String dateOfEnrollment;
    private String volunteerName;
    
    private String beneficiaryId;
    private int unexpectedExpenditure;
    private int accessMoneyToPay;
    private String[] sourceOfMoney;
    private String[] urgentHhNeeds;
    
    
    public CaregiverAccessToEmergencyFundForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getAccessMoneyToPay() {
        return accessMoneyToPay;
    }

    public void setAccessMoneyToPay(int accessMoneyToPay) {
        this.accessMoneyToPay = accessMoneyToPay;
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

    public String[] getSourceOfMoney() {
        return sourceOfMoney;
    }

    public void setSourceOfMoney(String[] sourceOfMoney) {
        this.sourceOfMoney = sourceOfMoney;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getUnexpectedExpenditure() {
        return unexpectedExpenditure;
    }

    public void setUnexpectedExpenditure(int unexpectedExpenditure) {
        this.unexpectedExpenditure = unexpectedExpenditure;
    }

    public String[] getUrgentHhNeeds() {
        return urgentHhNeeds;
    }

    public void setUrgentHhNeeds(String[] urgentHhNeeds) {
        this.urgentHhNeeds = urgentHhNeeds;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    recordId=0;
    actionName=null;
    dateOfAssessment=null;
    age=0;
    hhSerialNo=0; 
    hhUniqueId=null;
    surname=null;
    firstname=null;
    address=null;
    occupation=null;
    sex=null;
    phoneNumber=null;
    dateOfEnrollment=null;
    volunteerName=null;
    beneficiaryId=null;
    unexpectedExpenditure=0;
    accessMoneyToPay=0;
    sourceOfMoney=null;
    urgentHhNeeds=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        return errors;
    }
}
