/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.careplanachievement.controller;

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
public class CareplanAchievementForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int recordId;
    private int hhSerialNo;
    private String hhUniqueId;
    private String firstName;
    private String surname;
    private String dateOfEnrollment;
    private String dateOfAssessment;
    private String sex;
    private String phoneNumber;
    private int childrenHivStatusknown;
    private int hivPosAdolscentsLinked;
    private int documentedViralLoadResult;
    private int hivPreventionKnowledge;
    private int childrenNotUndernourished;
    private int allChildrenHaveBirthCert;
    private int stableAdultInHousehold;
    private int violenceIncidenceReport;
    private int childrenEnrolledInSchool;
    private int regularSchoolAttendance;
    private int cgiversEconomicActivity;
    private int emotionalSupportTeamIdentification;
    private String volunteerName;
    private int householdGraduated;
    
    public CareplanAchievementForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getAllChildrenHaveBirthCert() {
        return allChildrenHaveBirthCert;
    }

    public void setAllChildrenHaveBirthCert(int allChildrenHaveBirthCert) {
        this.allChildrenHaveBirthCert = allChildrenHaveBirthCert;
    }

    public int getCgiversEconomicActivity() {
        return cgiversEconomicActivity;
    }

    public void setCgiversEconomicActivity(int cgiversEconomicActivity) {
        this.cgiversEconomicActivity = cgiversEconomicActivity;
    }

    public int getChildrenEnrolledInSchool() {
        return childrenEnrolledInSchool;
    }

    public void setChildrenEnrolledInSchool(int childrenEnrolledInSchool) {
        this.childrenEnrolledInSchool = childrenEnrolledInSchool;
    }

    public int getChildrenHivStatusknown() {
        return childrenHivStatusknown;
    }

    public void setChildrenHivStatusknown(int childrenHivStatusknown) {
        this.childrenHivStatusknown = childrenHivStatusknown;
    }

    public int getChildrenNotUndernourished() {
        return childrenNotUndernourished;
    }

    public void setChildrenNotUndernourished(int childrenNotUndernourished) {
        this.childrenNotUndernourished = childrenNotUndernourished;
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

    public int getDocumentedViralLoadResult() {
        return documentedViralLoadResult;
    }

    public void setDocumentedViralLoadResult(int documentedViralLoadResult) {
        this.documentedViralLoadResult = documentedViralLoadResult;
    }

    public int getEmotionalSupportTeamIdentification() {
        return emotionalSupportTeamIdentification;
    }

    public void setEmotionalSupportTeamIdentification(int emotionalSupportTeamIdentification) {
        this.emotionalSupportTeamIdentification = emotionalSupportTeamIdentification;
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

    public int getHivPosAdolscentsLinked() {
        return hivPosAdolscentsLinked;
    }

    public void setHivPosAdolscentsLinked(int hivPosAdolscentsLinked) {
        this.hivPosAdolscentsLinked = hivPosAdolscentsLinked;
    }

    public int getHivPreventionKnowledge() {
        return hivPreventionKnowledge;
    }

    public void setHivPreventionKnowledge(int hivPreventionKnowledge) {
        this.hivPreventionKnowledge = hivPreventionKnowledge;
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

    public int getRegularSchoolAttendance() {
        return regularSchoolAttendance;
    }

    public void setRegularSchoolAttendance(int regularSchoolAttendance) {
        this.regularSchoolAttendance = regularSchoolAttendance;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getStableAdultInHousehold() {
        return stableAdultInHousehold;
    }

    public void setStableAdultInHousehold(int stableAdultInHousehold) {
        this.stableAdultInHousehold = stableAdultInHousehold;
    }

    public int getViolenceIncidenceReport() {
        return violenceIncidenceReport;
    }

    public void setViolenceIncidenceReport(int violenceIncidenceReport) {
        this.violenceIncidenceReport = violenceIncidenceReport;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
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

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public void setVolunteerName(String volunteerName) 
    {
        this.volunteerName = volunteerName;
    }

    public int getHouseholdGraduated() {
        return householdGraduated;
    }

    public void setHouseholdGraduated(int householdGraduated) {
        this.householdGraduated = householdGraduated;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    recordId=0;
    hhSerialNo=0;
    hhUniqueId=null;
    dateOfEnrollment=null;
    dateOfAssessment=null;
    sex=null;
    phoneNumber=null;
    childrenHivStatusknown=0;
    hivPosAdolscentsLinked=0;
    documentedViralLoadResult=0;
    hivPreventionKnowledge=0;
    childrenNotUndernourished=0;
    allChildrenHaveBirthCert=0;
    stableAdultInHousehold=0;
    violenceIncidenceReport=0;
    childrenEnrolledInSchool=0;
    regularSchoolAttendance=0;
    cgiversEconomicActivity=0;
    emotionalSupportTeamIdentification=0;
    volunteerName=null;
    firstName=null;
    surname=null;
    householdGraduated=2;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();//
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("householdDetails") || getActionName().equalsIgnoreCase("assessmentDetails") || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(this.getDateOfAssessment()==null || this.getDateOfAssessment().indexOf("/")==-1)
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
        else if(!ValidationManager.isValidDate(getDateOfAssessment()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateOfAssessment()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.postdated"));
        else if(!ValidationManager.dateAfterEnrollmentDate(this.getHhUniqueId(),getDateOfAssessment(),AppConstant.HOUSEHOLD_TYPE_NUM))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.beforeEnrollment"));
        else if(this.getAllChildrenHaveBirthCert()==0)
        errors.add("allChildrenHaveBirthCert", new ActionMessage("errors.allChildrenHaveBirthCert.required"));
        else if(this.getAllChildrenHaveBirthCert()==0)
        errors.add("allChildrenHaveBirthCert", new ActionMessage("errors.allChildrenHaveBirthCert.required"));
        else if(this.getCgiversEconomicActivity()==0)
        errors.add("cgiversEconomicActivity", new ActionMessage("errors.cgiversEconomicActivity.required"));
        else if(this.getChildrenEnrolledInSchool()==0)
        errors.add("childrenEnrolledInSchool", new ActionMessage("errors.childrenEnrolledInSchool.required"));
        else if(this.getChildrenHivStatusknown()==0)
        errors.add("childrenHivStatusknown", new ActionMessage("errors.childrenHivStatusknown.required"));
        else if(this.getChildrenNotUndernourished()==0)
        errors.add("childrenNotUndernourished", new ActionMessage("errors.childrenNotUndernourished.required"));
        else if(this.getDocumentedViralLoadResult()==0)
        errors.add("documentedViralLoadResult", new ActionMessage("errors.documentedViralLoadResult.required"));
        else if(this.getEmotionalSupportTeamIdentification()==0)
        errors.add("emotionalSupportTeamIdentification", new ActionMessage("errors.emotionalSupportTeamIdentification.required"));
        else if(this.getHivPosAdolscentsLinked()==0)
        errors.add("hivPosAdolscentsLinked", new ActionMessage("errors.hivPosAdolscentsLinked.required"));
        else if(this.getHivPreventionKnowledge()==0)
        errors.add("hivPreventionKnowledge", new ActionMessage("errors.hivPreventionKnowledge.required"));
        else if(this.getRegularSchoolAttendance()==0)
        errors.add("regularSchoolAttendance", new ActionMessage("errors.regularSchoolAttendance.required"));
        else if(this.getStableAdultInHousehold()==0)
        errors.add("stableAdultInHousehold", new ActionMessage("errors.stableAdultInHousehold.required"));
        else if(this.getViolenceIncidenceReport()==0)
        errors.add("violenceIncidenceReport", new ActionMessage("errors.violenceIncidenceReport.required"));
        
        return errors;
    }
}
