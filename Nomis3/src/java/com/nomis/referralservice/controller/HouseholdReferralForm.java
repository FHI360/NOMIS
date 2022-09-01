/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.referralservice.controller;

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
public class HouseholdReferralForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private int hhSerialNo;
    private String hhUniqueId;
    private String dateOfEnrollment;
    private String sex;
    private String phoneNumber;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int occupation;
    private int hivStatus;
    private int educationLevel;
    private String beneficiaryId;
    private int id;
    private String dateOfReferral;
    private String[] stableServices;
    private String[] healthServices;
    private String[] schoolServices;
    private String[] safetyServices;
    private String[] gbvServices;
    private String hhName;
    private int beneficiaryType;
    private String volunteerName;
    private String referringOrganization;
    private String receivingOrganization;
    private int referralCompleted;
    
    public HouseholdReferralForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(int beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
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

    public String getDateOfReferral() {
        return dateOfReferral;
    }

    public void setDateOfReferral(String dateOfReferral) {
        this.dateOfReferral = dateOfReferral;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String[] getGbvServices() {
        return gbvServices;
    }

    public void setGbvServices(String[] gbvServices) {
        this.gbvServices = gbvServices;
    }

    public String[] getHealthServices() {
        return healthServices;
    }

    public void setHealthServices(String[] healthServices) {
        this.healthServices = healthServices;
    }

    public String getHhName() {
        return hhName;
    }

    public void setHhName(String hhName) {
        this.hhName = hhName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOccupation() {
        return occupation;
    }

    public void setOccupation(int occupation) {
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
    public String[] getSafetyServices() {
        return safetyServices;
    }

    public void setSafetyServices(String[] safetyServices) {
        this.safetyServices = safetyServices;
    }

    public String[] getSchoolServices() {
        return schoolServices;
    }

    public void setSchoolServices(String[] schoolServices) {
        this.schoolServices = schoolServices;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getStableServices() {
        return stableServices;
    }

    public void setStableServices(String[] stableServices) {
        this.stableServices = stableServices;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getReceivingOrganization() {
        return receivingOrganization;
    }

    public void setReceivingOrganization(String receivingOrganization) {
        this.receivingOrganization = receivingOrganization;
    }

    public String getReferringOrganization() {
        return referringOrganization;
    }

    public void setReferringOrganization(String referringOrganization) {
        this.referringOrganization = referringOrganization;
    }

    public int getReferralCompleted() {
        return referralCompleted;
    }

    public void setReferralCompleted(int referralCompleted) {
        this.referralCompleted = referralCompleted;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    hhSerialNo=0;
    dateOfReferral=null;
    stableServices=null;
    healthServices=null;
    schoolServices=null;
    safetyServices=null;
    gbvServices=null;
    organizationUnitId=null;
    volunteerName=null;
    referringOrganization=null;
    receivingOrganization=null;
    referralCompleted=0;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(getActionName()==null || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("householdDetails") || getActionName().equalsIgnoreCase("beneficiaryDetails") || getActionName().equalsIgnoreCase("serviceDetails") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(this.getBeneficiaryId()==null || getBeneficiaryId().trim().equalsIgnoreCase("select") || getBeneficiaryId().trim().length()==0)
        errors.add("ovcId", new ActionMessage("errors.ovcId.required"));
        else if(getReferringOrganization()==null || this.getReferringOrganization().trim().equalsIgnoreCase("select"))
        errors.add("referringOrganization", new ActionMessage("errors.referringOrganization.required"));
        else if(this.getReceivingOrganization()==null || getReceivingOrganization().trim().equalsIgnoreCase("select"))
        errors.add("receivingOrganization", new ActionMessage("errors.receivingOrganization.required"));
        else if(this.getDateOfReferral()==null || this.getDateOfReferral().indexOf("/")==-1)
        errors.add("dateOfReferral", new ActionMessage("errors.dateOfReferral.required"));
        else if(!ValidationManager.isValidDate(getDateOfReferral()))
        errors.add("dateOfReferral", new ActionMessage("errors.dateOfReferral.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateOfReferral()))
        errors.add("dateOfReferral", new ActionMessage("errors.dateOfReferral.postdated"));
        else if(this.getBeneficiaryType()==AppConstant.OVC_TYPE_NUM)
        {
            if(!ValidationManager.dateAfterEnrollmentDate(getBeneficiaryId(),getDateOfReferral(),AppConstant.OVC_TYPE_NUM))
            errors.add("dateOfReferral", new ActionMessage("errors.dateOfReferral.beforeEnrollment"));
        }
        else if(this.getBeneficiaryType()==AppConstant.CAREGIVER_TYPE_NUM)
        {
            if(!ValidationManager.dateAfterEnrollmentDate(getBeneficiaryId(),getDateOfReferral(),AppConstant.CAREGIVER_TYPE_NUM))
            errors.add("dateOfReferral", new ActionMessage("errors.dateOfReferral.beforeEnrollment"));
        }
        else if((getHealthServices()==null || getHealthServices().length==0) && (getSafetyServices()==null || getSafetyServices().length==0) && (getSchoolServices()==null || getSchoolServices().length==0) && (getStableServices()==null || getStableServices().length==0))
        errors.add("services", new ActionMessage("errors.services.required"));
        else if(getReferralCompleted()==0 )
        errors.add("referralCompleted", new ActionMessage("errors.referralCompleted.required"));
        else if((this.getVolunteerName()==null || this.getVolunteerName().trim().equalsIgnoreCase("select")))
        errors.add("volunteerName", new ActionMessage("errors.volunteerName.required"));
        return errors;
    }
}
