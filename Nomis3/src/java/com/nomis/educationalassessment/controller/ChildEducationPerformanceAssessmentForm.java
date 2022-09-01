/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.educationalassessment.controller;

import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.ValidationManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class ChildEducationPerformanceAssessmentForm extends org.apache.struts.action.ActionForm 
{
    private int recordId;
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private String dateOfAssessment;
    
    private int childHasInjuriesOrMarks;
    private int childIsSociallyWithdrawn;
    private int signsOfFatigueAndTiredness;
    
    private int regularSchoolAttendance;
    private int steadyImprovementInClassWork;
    private int earlyResumptionInSchool;
    private int goodPerformanceInLastExam;
    
    private int childProgressedInSchool;
    
    private int childMissVocTraining;
    private int earlyResumptionInTrainingCenter;
    private int steadyImprovementInVocWork;
    private String classTeacherComment;
    private String classTeacherName;
    
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
    private String schoolName;
    private String schoolGrade;
    private String volunteerName;
    
    public ChildEducationPerformanceAssessmentForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getChildHasInjuriesOrMarks() {
        return childHasInjuriesOrMarks;
    }

    public void setChildHasInjuriesOrMarks(int childHasInjuriesOrMarks) {
        this.childHasInjuriesOrMarks = childHasInjuriesOrMarks;
    }

    public int getChildIsSociallyWithdrawn() {
        return childIsSociallyWithdrawn;
    }

    public void setChildIsSociallyWithdrawn(int childIsSociallyWithdrawn) {
        this.childIsSociallyWithdrawn = childIsSociallyWithdrawn;
    }

    public int getChildMissVocTraining() {
        return childMissVocTraining;
    }

    public void setChildMissVocTraining(int childMissVocTraining) {
        this.childMissVocTraining = childMissVocTraining;
    }

    public int getChildProgressedInSchool() {
        return childProgressedInSchool;
    }

    public void setChildProgressedInSchool(int childProgressedInSchool) {
        this.childProgressedInSchool = childProgressedInSchool;
    }

    public int getEarlyResumptionInSchool() {
        return earlyResumptionInSchool;
    }

    public void setEarlyResumptionInSchool(int earlyResumptionInSchool) {
        this.earlyResumptionInSchool = earlyResumptionInSchool;
    }

    public int getEarlyResumptionInTrainingCenter() {
        return earlyResumptionInTrainingCenter;
    }

    public void setEarlyResumptionInTrainingCenter(int earlyResumptionInTrainingCenter) {
        this.earlyResumptionInTrainingCenter = earlyResumptionInTrainingCenter;
    }

    public int getGoodPerformanceInLastExam() {
        return goodPerformanceInLastExam;
    }

    public void setGoodPerformanceInLastExam(int goodPerformanceInLastExam) {
        this.goodPerformanceInLastExam = goodPerformanceInLastExam;
    }

    public int getRegularSchoolAttendance() {
        return regularSchoolAttendance;
    }

    public void setRegularSchoolAttendance(int regularSchoolAttendance) {
        this.regularSchoolAttendance = regularSchoolAttendance;
    }

    public int getSignsOfFatigueAndTiredness() {
        return signsOfFatigueAndTiredness;
    }

    public void setSignsOfFatigueAndTiredness(int signsOfFatigueAndTiredness) {
        this.signsOfFatigueAndTiredness = signsOfFatigueAndTiredness;
    }

    public int getSteadyImprovementInClassWork() {
        return steadyImprovementInClassWork;
    }

    public void setSteadyImprovementInClassWork(int steadyImprovementInClassWork) {
        this.steadyImprovementInClassWork = steadyImprovementInClassWork;
    }

    public int getSteadyImprovementInVocWork() {
        return steadyImprovementInVocWork;
    }

    public void setSteadyImprovementInVocWork(int steadyImprovementInVocWork) {
        this.steadyImprovementInVocWork = steadyImprovementInVocWork;
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

    public String getClassTeacherComment() {
        return classTeacherComment;
    }

    public void setClassTeacherComment(String classTeacherComment) {
        this.classTeacherComment = classTeacherComment;
    }

    public String getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(String classTeacherName) {
        this.classTeacherName = classTeacherName;
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

    public String getSchoolGrade() {
        return schoolGrade;
    }

    public void setSchoolGrade(String schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
     actionName=null;
     childHasInjuriesOrMarks=0;
     childIsSociallyWithdrawn=0;
     signsOfFatigueAndTiredness=0;
     regularSchoolAttendance=0;
     steadyImprovementInClassWork=0;
     earlyResumptionInSchool=0;
     goodPerformanceInLastExam=0;
     childProgressedInSchool=0;
     childMissVocTraining=0;
     earlyResumptionInTrainingCenter=0;
     steadyImprovementInVocWork=0;
    classTeacherComment=null;
    classTeacherName=null;
    dateOfAssessment=null;
     /*age=0;
    ageUnit=null;
     hhSerialNo=0; 
    hhUniqueId=null;
    ovcId=null;
    surname=null;
    firstname=null;
    address=null;
    occupation=null;
    sex=null;
    phoneNumber=null;
    dateOfEnrollment=null;*/
    schoolName=null;
    schoolGrade=null;
    volunteerName=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("householdDetails") || getActionName().equalsIgnoreCase("childDetails") || getActionName().equalsIgnoreCase("assessmentDetails") || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(this.getDateOfAssessment()==null || this.getDateOfAssessment().indexOf("/")==-1)
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
        else if(!ValidationManager.isValidDate(getDateOfAssessment()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateOfAssessment()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.postdated"));
        else if(!ValidationManager.dateAfterEnrollmentDate(this.getOvcId(),getDateOfAssessment(),AppConstant.OVC_TYPE_NUM))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.beforeEnrollment"));
        
        else if(this.getOvcId()==null || getOvcId().equalsIgnoreCase("select"))
        errors.add("ovcId", new ActionMessage("errors.ovcIdIneduassess.required"));
        else if(getChildHasInjuriesOrMarks()==0)
        errors.add("childHasInjuriesOrMarks", new ActionMessage("errors.childHasInjuriesOrMarks.required"));
        else if(this.getChildIsSociallyWithdrawn()==0)
        errors.add("childIsSociallyWithdrawn", new ActionMessage("errors.childIsSociallyWithdrawn.required"));
        else if(this.getSignsOfFatigueAndTiredness()==0)
        errors.add("signsOfFatigueAndTiredness", new ActionMessage("errors.signsOfFatigueAndTiredness.required"));
        else if(this.getRegularSchoolAttendance()==0)
        errors.add("regularSchoolAttendance", new ActionMessage("errors.regularSchoolAttendance.required"));
        else if(this.getSteadyImprovementInClassWork()==0)
        errors.add("steadyImprovementInClassWork", new ActionMessage("errors.steadyImprovementInClassWork.required"));
        else if(this.getEarlyResumptionInSchool()==0)
        errors.add("earlyResumptionInSchool", new ActionMessage("errors.earlyResumptionInSchool.required"));
        else if(this.getGoodPerformanceInLastExam()==0)
        errors.add("goodPerformanceInLastExam", new ActionMessage("errors.goodPerformanceInLastExam.required"));
        else if(this.getChildProgressedInSchool()==0)
        errors.add("childProgressedInSchool", new ActionMessage("errors.childProgressedInSchool.required"));
        
        else if(this.getChildMissVocTraining()>0 && getChildMissVocTraining()!=3)
        {
            //errors.add("childMissVocTraining", new ActionMessage("errors.childMissVocTraining.required"));
            if(this.getEarlyResumptionInTrainingCenter()==0)
            errors.add("earlyResumptionInTrainingCenter", new ActionMessage("errors.earlyResumptionInTrainingCenter.required"));
            else if(this.getSteadyImprovementInVocWork()==0)
            errors.add("steadyImprovementInVocWork", new ActionMessage("errors.steadyImprovementInVocWork.required"));
        }
        else if(this.getClassTeacherName()==null || getClassTeacherName().trim().equalsIgnoreCase("select"))
        errors.add("classTeacherName", new ActionMessage("errors.classTeacherName.required"));
        
        /*else if(this.getSchoolName()==null || getSchoolName().equalsIgnoreCase("select"))
        errors.add("schoolName", new ActionMessage("errors.schoolName.required"));
        else if(this.getSchoolGrade()==null || getSchoolGrade().equalsIgnoreCase("select"))
        errors.add("schoolGrade", new ActionMessage("errors.schoolGrade.required"));*/
        return errors;
    }
}
