/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.hivriskassessment.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class NationalHivRiskAssessmentForm extends org.apache.struts.action.ActionForm {
    
    private int recordId;
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int hivParentQuestion;
    private int sexualAbuseQuestion;
    private int childAtRiskQuestion;
    private int chronicallyIllQuestion;
    private int sexuallyActiveQuestion;
    private int childSickQuestion;
    private int bloodTransfusionQuestion;
    private int muacbmiQuestion;
    private int childTestedQuestion;  
    private int hivStatus;
    private String dateOfAssessment;
       
    //private int hivStatusQuestion;
    
        
    private int age;
    private String ageUnit;
    private int hhSerialNo; 
    private String hhUniqueId;
    private String ovcId;
    private String surname;
    private String firstname;
    private String address;
    private String occupation;
    private String sex;
    private String phoneNumber;
    private String dateOfEnrollment;
    private String volunteerName;
    
    public NationalHivRiskAssessmentForm() {
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

    public int getBloodTransfusionQuestion() {
        return bloodTransfusionQuestion;
    }

    public void setBloodTransfusionQuestion(int bloodTransfusionQuestion) {
        this.bloodTransfusionQuestion = bloodTransfusionQuestion;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public int getChildAtRiskQuestion() {
        return childAtRiskQuestion;
    }

    public void setChildAtRiskQuestion(int childAtRiskQuestion) {
        this.childAtRiskQuestion = childAtRiskQuestion;
    }

    public int getChildSickQuestion() {
        return childSickQuestion;
    }

    public void setChildSickQuestion(int childSickQuestion) {
        this.childSickQuestion = childSickQuestion;
    }

    public int getChildTestedQuestion() {
        return childTestedQuestion;
    }

    public void setChildTestedQuestion(int childTestedQuestion) {
        this.childTestedQuestion = childTestedQuestion;
    }

    public int getChronicallyIllQuestion() {
        return chronicallyIllQuestion;
    }

    public void setChronicallyIllQuestion(int chronicallyIllQuestion) {
        this.chronicallyIllQuestion = chronicallyIllQuestion;
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

    public int getHivParentQuestion() {
        return hivParentQuestion;
    }

    public void setHivParentQuestion(int hivParentQuestion) {
        this.hivParentQuestion = hivParentQuestion;
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

    public int getMuacbmiQuestion() {
        return muacbmiQuestion;
    }

    public void setMuacbmiQuestion(int muacbmiQuestion) {
        this.muacbmiQuestion = muacbmiQuestion;
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

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
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

    public int getSexualAbuseQuestion() {
        return sexualAbuseQuestion;
    }

    public void setSexualAbuseQuestion(int sexualAbuseQuestion) {
        this.sexualAbuseQuestion = sexualAbuseQuestion;
    }

    public int getSexuallyActiveQuestion() {
        return sexuallyActiveQuestion;
    }

    public void setSexuallyActiveQuestion(int sexuallyActiveQuestion) {
        this.sexuallyActiveQuestion = sexuallyActiveQuestion;
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
