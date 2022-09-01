/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.householdcareplan.controller;

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
public class HouseholdCareplanForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private int recordId;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int hivStatus;
    
    private int hhSerialNo; 
    private String hhUniqueId;
    private String beneficiaryId;
    private String surname;
    private String firstname;
    private String address;
    private String occupation;
    private String sex;
    private int age;
    private String phoneNumber;
    private String dateOfEnrollment;
    private String volunteerName;
    
    private String careplanDate;
    private String identifiedHealthIssues;
    private String householdHealthGoals;
    private String priorityHealthAction;
    private String healthServicesToBeProvided;
    private String timeFrameForHealthServices;
    private String followupForHealthServices;
    
    private String identifiedSafetyIssues;
    private String householdSafetyGoals;
    private String prioritySafetyAction;
    private String safetyServicesToBeProvided;
    private String timeFrameForSafetyServices;
    private String followupForSafetyServices;
    private String identifiedSchooledIssues;
    private String householdSchooledGoals;
    private String prioritySchooledAction;
    private String schooledServicesToBeProvided;
    private String timeFrameForSchooledServices;
    private String followupForSchooledServices;
    private String identifiedStableIssues;
    private String householdStableGoals;
    private String priorityStableAction;
    private String stableServicesToBeProvided;
    private String timeFrameForStableServices;
    private String followupForStableServices;

    
    public HouseholdCareplanForm() {
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

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getCareplanDate() {
        return careplanDate;
    }

    public void setCareplanDate(String careplanDate) {
        this.careplanDate = careplanDate;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
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

    public String getFollowupForHealthServices() {
        return followupForHealthServices;
    }

    public void setFollowupForHealthServices(String followupForHealthServices) {
        this.followupForHealthServices = followupForHealthServices;
    }

    public String getFollowupForSafetyServices() {
        return followupForSafetyServices;
    }

    public void setFollowupForSafetyServices(String followupForSafetyServices) {
        this.followupForSafetyServices = followupForSafetyServices;
    }

    public String getFollowupForSchooledServices() {
        return followupForSchooledServices;
    }

    public void setFollowupForSchooledServices(String followupForSchooledServices) {
        this.followupForSchooledServices = followupForSchooledServices;
    }

    public String getFollowupForStableServices() {
        return followupForStableServices;
    }

    public void setFollowupForStableServices(String followupForStableServices) {
        this.followupForStableServices = followupForStableServices;
    }

    public String getHealthServicesToBeProvided() {
        return healthServicesToBeProvided;
    }

    public void setHealthServicesToBeProvided(String healthServicesToBeProvided) {
        this.healthServicesToBeProvided = healthServicesToBeProvided;
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

    public String getHouseholdHealthGoals() {
        return householdHealthGoals;
    }

    public void setHouseholdHealthGoals(String householdHealthGoals) {
        this.householdHealthGoals = householdHealthGoals;
    }

    public String getHouseholdSafetyGoals() {
        return householdSafetyGoals;
    }

    public void setHouseholdSafetyGoals(String householdSafetyGoals) {
        this.householdSafetyGoals = householdSafetyGoals;
    }

    public String getHouseholdSchooledGoals() {
        return householdSchooledGoals;
    }

    public void setHouseholdSchooledGoals(String householdSchooledGoals) {
        this.householdSchooledGoals = householdSchooledGoals;
    }

    public String getHouseholdStableGoals() {
        return householdStableGoals;
    }

    public void setHouseholdStableGoals(String householdStableGoals) {
        this.householdStableGoals = householdStableGoals;
    }

    public String getIdentifiedHealthIssues() {
        return identifiedHealthIssues;
    }

    public void setIdentifiedHealthIssues(String identifiedHealthIssues) {
        this.identifiedHealthIssues = identifiedHealthIssues;
    }

    public String getIdentifiedSafetyIssues() {
        return identifiedSafetyIssues;
    }

    public void setIdentifiedSafetyIssues(String identifiedSafetyIssues) {
        this.identifiedSafetyIssues = identifiedSafetyIssues;
    }

    public String getIdentifiedSchooledIssues() {
        return identifiedSchooledIssues;
    }

    public void setIdentifiedSchooledIssues(String identifiedSchooledIssues) {
        this.identifiedSchooledIssues = identifiedSchooledIssues;
    }

    public String getIdentifiedStableIssues() {
        return identifiedStableIssues;
    }

    public void setIdentifiedStableIssues(String identifiedStableIssues) {
        this.identifiedStableIssues = identifiedStableIssues;
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

    public String getPriorityHealthAction() {
        return priorityHealthAction;
    }

    public void setPriorityHealthAction(String priorityHealthAction) {
        this.priorityHealthAction = priorityHealthAction;
    }

    public String getPrioritySafetyAction() {
        return prioritySafetyAction;
    }

    public void setPrioritySafetyAction(String prioritySafetyAction) {
        this.prioritySafetyAction = prioritySafetyAction;
    }

    public String getPrioritySchooledAction() {
        return prioritySchooledAction;
    }

    public void setPrioritySchooledAction(String prioritySchooledAction) {
        this.prioritySchooledAction = prioritySchooledAction;
    }

    public String getPriorityStableAction() {
        return priorityStableAction;
    }

    public void setPriorityStableAction(String priorityStableAction) {
        this.priorityStableAction = priorityStableAction;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getSafetyServicesToBeProvided() {
        return safetyServicesToBeProvided;
    }

    public void setSafetyServicesToBeProvided(String safetyServicesToBeProvided) {
        this.safetyServicesToBeProvided = safetyServicesToBeProvided;
    }

    public String getSchooledServicesToBeProvided() {
        return schooledServicesToBeProvided;
    }

    public void setSchooledServicesToBeProvided(String schooledServicesToBeProvided) {
        this.schooledServicesToBeProvided = schooledServicesToBeProvided;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStableServicesToBeProvided() {
        return stableServicesToBeProvided;
    }

    public void setStableServicesToBeProvided(String stableServicesToBeProvided) {
        this.stableServicesToBeProvided = stableServicesToBeProvided;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTimeFrameForHealthServices() {
        return timeFrameForHealthServices;
    }

    public void setTimeFrameForHealthServices(String timeFrameForHealthServices) {
        this.timeFrameForHealthServices = timeFrameForHealthServices;
    }

    public String getTimeFrameForSafetyServices() {
        return timeFrameForSafetyServices;
    }

    public void setTimeFrameForSafetyServices(String timeFrameForSafetyServices) {
        this.timeFrameForSafetyServices = timeFrameForSafetyServices;
    }

    public String getTimeFrameForSchooledServices() {
        return timeFrameForSchooledServices;
    }

    public void setTimeFrameForSchooledServices(String timeFrameForSchooledServices) {
        this.timeFrameForSchooledServices = timeFrameForSchooledServices;
    }

    public String getTimeFrameForStableServices() {
        return timeFrameForStableServices;
    }

    public void setTimeFrameForStableServices(String timeFrameForStableServices) {
        this.timeFrameForStableServices = timeFrameForStableServices;
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
    //hhSerialNo=0; 
    //hhUniqueId=null;
    //beneficiaryId=null;
    surname=null;
    firstname=null;
    address=null;
    occupation=null;
    sex=null;
    age=0;
    phoneNumber=null;
    dateOfEnrollment=null;
    volunteerName=null;
    
    careplanDate=null;
    identifiedHealthIssues=null;
    householdHealthGoals=null;
    priorityHealthAction=null;
    healthServicesToBeProvided=null;
    timeFrameForHealthServices=null;
    followupForHealthServices=null;
    
    identifiedSafetyIssues=null;
    householdSafetyGoals=null;
    prioritySafetyAction=null;
    safetyServicesToBeProvided=null;
    timeFrameForSafetyServices=null;
    followupForSafetyServices=null;
    identifiedSchooledIssues=null;
    householdSchooledGoals=null;
    prioritySchooledAction=null;
    schooledServicesToBeProvided=null;
    timeFrameForSchooledServices=null;
    followupForSchooledServices=null;
    identifiedStableIssues=null;
    householdStableGoals=null;
    priorityStableAction=null;
    stableServicesToBeProvided=null;
    timeFrameForStableServices=null;
    followupForStableServices=null;
}
private boolean isCareplanValid()
{
    boolean valid=true;
    if((this.getIdentifiedHealthIssues()==null || this.getIdentifiedHealthIssues().trim().length()==0)
        && (this.getIdentifiedSafetyIssues()==null || this.getIdentifiedSafetyIssues().trim().length()==0)
       && (this.getIdentifiedSchooledIssues()==null || this.getIdentifiedSchooledIssues().trim().length()==0)
       && (this.getIdentifiedStableIssues()==null || this.getIdentifiedStableIssues().trim().length()==0))
     valid=false;
    /*else if((this.getFollowupForHealthServices()==null || this.getFollowupForHealthServices().trim().length()==0)
       || (this.getFollowupForSafetyServices()==null || this.getFollowupForSafetyServices().trim().length()==0)
       || (this.getFollowupForSchooledServices()==null || this.getFollowupForSchooledServices().trim().length()==0)
       || (this.getFollowupForStableServices()==null || this.getFollowupForStableServices().trim().length()==0)
      )
        valid=false;*/
    
    return valid;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("beneficiaryList") || getActionName().equalsIgnoreCase("beneficiaryDetails") || getActionName().equalsIgnoreCase("careplanDetails") || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(this.getCareplanDate()==null || this.getCareplanDate().indexOf("/")==-1)
        errors.add("CareplanDate", new ActionMessage("errors.careplanDate.required"));
        else if(!ValidationManager.isValidDate(getCareplanDate()))
        errors.add("CareplanDate", new ActionMessage("errors.careplanDate.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getCareplanDate()))
        errors.add("CareplanDate", new ActionMessage("errors.careplanDate.postdated"));
        else if(!ValidationManager.dateAfterOrEqualEnrollmentDate(this.getBeneficiaryId(),getCareplanDate()))
        errors.add("CareplanDate", new ActionMessage("errors.careplanDate.beforeEnrollment"));
        
        else if(this.getBeneficiaryId()==null || getBeneficiaryId().equalsIgnoreCase("select"))
        errors.add("beneficiaryId", new ActionMessage("errors.beneficiaryId.required"));
        else if((this.getIdentifiedHealthIssues()==null || this.getIdentifiedHealthIssues().trim().length()==0)
        && (this.getIdentifiedSafetyIssues()==null || this.getIdentifiedSafetyIssues().trim().length()==0)
        && (this.getIdentifiedSchooledIssues()==null || this.getIdentifiedSchooledIssues().trim().length()==0)
        && (this.getIdentifiedStableIssues()==null || this.getIdentifiedStableIssues().trim().length()==0))
            errors.add("identifiedHealthIssues", new ActionMessage("errors.identifiedIssues.required"));
        else if((this.getIdentifiedHealthIssues()!=null && this.getIdentifiedHealthIssues().trim().length()>0)
             && (this.getHouseholdHealthGoals()==null || this.getHouseholdHealthGoals().trim().length()==0))
        errors.add("householdHealthGoals", new ActionMessage("errors.householdHealthGoals.required"));
        else if((this.getIdentifiedSafetyIssues()!=null && this.getIdentifiedSafetyIssues().trim().length()>0)
             && (this.getHouseholdSafetyGoals()==null || this.getHouseholdSafetyGoals().trim().length()==0))
        errors.add("householdSafetyGoals", new ActionMessage("errors.householdSafetyGoals.required"));
        else if((this.getIdentifiedSchooledIssues()!=null && this.getIdentifiedSchooledIssues().trim().length()>0)
             && (this.getHouseholdSchooledGoals()==null || this.getHouseholdSchooledGoals().trim().length()==0))
        errors.add("householdSchooledGoals", new ActionMessage("errors.householdSchooledGoals.required"));
        else if((this.getIdentifiedStableIssues()!=null && this.getIdentifiedStableIssues().trim().length()>0)
             && (this.getHouseholdStableGoals()==null || this.getHouseholdStableGoals().trim().length()==0))
        errors.add("householdStableGoals", new ActionMessage("errors.householdStableGoals.required"));
        
        else if((this.getIdentifiedHealthIssues()!=null && this.getIdentifiedHealthIssues().trim().length()>0)
             && (this.getPriorityHealthAction()==null || this.getPriorityHealthAction().trim().length()==0))
        errors.add("priorityHealthAction", new ActionMessage("errors.priorityHealthAction.required"));
        else if((this.getIdentifiedSafetyIssues()!=null && this.getIdentifiedSafetyIssues().trim().length()>0)
             && (this.getPrioritySafetyAction()==null || this.getPrioritySafetyAction().trim().length()==0))
        errors.add("prioritySafetyAction", new ActionMessage("errors.prioritySafetyAction.required"));
        else if((this.getIdentifiedSchooledIssues()!=null && this.getIdentifiedSchooledIssues().trim().length()>0)
             && (this.getPrioritySchooledAction()==null || this.getPrioritySchooledAction().trim().length()==0))
        errors.add("prioritySchooledAction", new ActionMessage("errors.prioritySchooledAction.required"));
        else if((this.getIdentifiedStableIssues()!=null && this.getIdentifiedStableIssues().trim().length()>0)
             && (this.getPriorityStableAction()==null || this.getPriorityStableAction().trim().length()==0))
        errors.add("priorityStableAction", new ActionMessage("errors.priorityStableAction.required"));
        
        else if((this.getIdentifiedHealthIssues()!=null && this.getIdentifiedHealthIssues().trim().length()>0)
             && (this.getHealthServicesToBeProvided()==null || this.getHealthServicesToBeProvided().trim().length()==0))
        errors.add("healthServicesToBeProvided", new ActionMessage("errors.healthServicesToBeProvided.required"));
        else if((this.getIdentifiedSafetyIssues()!=null && this.getIdentifiedSafetyIssues().trim().length()>0)
             && (this.getSafetyServicesToBeProvided()==null || this.getSafetyServicesToBeProvided().trim().length()==0))
        errors.add("safetyServicesToBeProvided", new ActionMessage("errors.safetyServicesToBeProvided.required"));
        else if((this.getIdentifiedSchooledIssues()!=null && this.getIdentifiedSchooledIssues().trim().length()>0)
             && (this.getSchooledServicesToBeProvided()==null || this.getSchooledServicesToBeProvided().trim().length()==0))
        errors.add("schooledServicesToBeProvided", new ActionMessage("errors.schooledServicesToBeProvided.required"));
        else if((this.getIdentifiedStableIssues()!=null && this.getIdentifiedStableIssues().trim().length()>0)
             && (this.getStableServicesToBeProvided()==null || this.getStableServicesToBeProvided().trim().length()==0))
        errors.add("stableServicesToBeProvided", new ActionMessage("errors.stableServicesToBeProvided.required"));
        else if((this.getIdentifiedHealthIssues()!=null && this.getIdentifiedHealthIssues().trim().length()>0)
             && (this.getTimeFrameForHealthServices()==null || this.getTimeFrameForHealthServices().trim().length()==0))
        errors.add("timeFrameForHealthServices", new ActionMessage("errors.timeFrameForHealthServices.required"));
        else if((this.getIdentifiedSafetyIssues()!=null && this.getIdentifiedSafetyIssues().trim().length()>0)
             && (this.getTimeFrameForSafetyServices()==null || this.getTimeFrameForSafetyServices().trim().length()==0))
        errors.add("timeFrameForSafetyServices", new ActionMessage("errors.timeFrameForSafetyServices.required"));
        else if((this.getIdentifiedSchooledIssues()!=null && this.getIdentifiedSchooledIssues().trim().length()>0)
             && (this.getTimeFrameForSchooledServices()==null || this.getTimeFrameForSchooledServices().trim().length()==0))
        errors.add("timeFrameForSchooledServices", new ActionMessage("errors.timeFrameForSchooledServices.required"));
        else if((this.getIdentifiedStableIssues()!=null && this.getIdentifiedStableIssues().trim().length()>0)
             && (this.getTimeFrameForStableServices()==null || this.getTimeFrameForStableServices().trim().length()==0))
        errors.add("timeFrameForStableServices", new ActionMessage("errors.timeFrameForStableServices.required"));
        else if(this.getVolunteerName()==null || this.getVolunteerName().trim().equalsIgnoreCase("select"))
        errors.add("volunteerName", new ActionMessage("errors.volunteerName.required"));
        return errors;
    }
}
