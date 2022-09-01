/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.childservice.controller;

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
public class NationalChildServiceForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private int hhSerialNo;
    private String hhUniqueId;
    private String dateOfBirth;
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
    private String ovcId;
    private int id;
    private String serviceDate;
    private String[] stableServices;
    private String[] healthServices;
    private String[] schoolServices;
    private String[] safetyServices;
    private String[] referralServices;
    private String[] gbvServices;
    private String reasonWithdrawal;
    private String hhName;
    private int beneficiaryType;
    private String volunteerName;
    
    private String[] psychoServices;
private String[] nutritionalServices;
//private String[] healthServices;
private String[] educationalServices;
private String[] protectionServices;
private String[] shelterServices;
private String[] economicServices;
//private String[] referralServices;
private double currentWeight;
private double currentHeight;
private String psychosocialOther;
private String nutritionOther;
private String healthOther;
private String educationOther;
private String protectionOther;
private String shelterOther;
private String economicOther;
private String providerName;
private String combinedNames;
private String currentHivStatus;
private String newHivStatus;
private String enrolledInCare;
private String enrolledOnART;
private String organizationClientIsReferred;
private int childAbused;
private String childMissedSchool;
private int childLinkedToGovt;
    
    public NationalChildServiceForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
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

    public String getReasonWithdrawal() {
        return reasonWithdrawal;
    }

    public void setReasonWithdrawal(String reasonWithdrawal) {
        this.reasonWithdrawal = reasonWithdrawal;
    }

    public String[] getReferralServices() {
        return referralServices;
    }

    public void setReferralServices(String[] referralServices) {
        this.referralServices = referralServices;
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

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
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

    public int getHhSerialNo() {
        return hhSerialNo;
    }

    public void setHhSerialNo(int hhSerialNo) {
        this.hhSerialNo = hhSerialNo;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getChildAbused() {
        return childAbused;
    }

    public void setChildAbused(int childAbused) {
        this.childAbused = childAbused;
    }

    public int getChildLinkedToGovt() {
        return childLinkedToGovt;
    }

    public void setChildLinkedToGovt(int childLinkedToGovt) {
        this.childLinkedToGovt = childLinkedToGovt;
    }

    public String getChildMissedSchool() {
        return childMissedSchool;
    }

    public void setChildMissedSchool(String childMissedSchool) {
        this.childMissedSchool = childMissedSchool;
    }

    public String getCombinedNames() {
        return combinedNames;
    }

    public void setCombinedNames(String combinedNames) {
        this.combinedNames = combinedNames;
    }

    public String getCurrentHivStatus() {
        return currentHivStatus;
    }

    public void setCurrentHivStatus(String currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
    }

    public String getEconomicOther() {
        return economicOther;
    }

    public void setEconomicOther(String economicOther) {
        this.economicOther = economicOther;
    }

    public String[] getEconomicServices() {
        return economicServices;
    }

    public void setEconomicServices(String[] economicServices) {
        this.economicServices = economicServices;
    }

    public String getEducationOther() {
        return educationOther;
    }

    public void setEducationOther(String educationOther) {
        this.educationOther = educationOther;
    }

    public String[] getEducationalServices() {
        return educationalServices;
    }

    public void setEducationalServices(String[] educationalServices) {
        this.educationalServices = educationalServices;
    }

    public String getEnrolledInCare() {
        return enrolledInCare;
    }

    public void setEnrolledInCare(String enrolledInCare) {
        this.enrolledInCare = enrolledInCare;
    }

    public String getEnrolledOnART() {
        return enrolledOnART;
    }

    public void setEnrolledOnART(String enrolledOnART) {
        this.enrolledOnART = enrolledOnART;
    }

    public String getHealthOther() {
        return healthOther;
    }

    public void setHealthOther(String healthOther) {
        this.healthOther = healthOther;
    }

    public String getNewHivStatus() {
        return newHivStatus;
    }

    public void setNewHivStatus(String newHivStatus) {
        this.newHivStatus = newHivStatus;
    }

    public String getNutritionOther() {
        return nutritionOther;
    }

    public void setNutritionOther(String nutritionOther) {
        this.nutritionOther = nutritionOther;
    }

    public String[] getNutritionalServices() {
        return nutritionalServices;
    }

    public void setNutritionalServices(String[] nutritionalServices) {
        this.nutritionalServices = nutritionalServices;
    }

    public String getOrganizationClientIsReferred() {
        return organizationClientIsReferred;
    }

    public void setOrganizationClientIsReferred(String organizationClientIsReferred) {
        this.organizationClientIsReferred = organizationClientIsReferred;
    }

    public String getProtectionOther() {
        return protectionOther;
    }

    public void setProtectionOther(String protectionOther) {
        this.protectionOther = protectionOther;
    }

    public String[] getProtectionServices() {
        return protectionServices;
    }

    public void setProtectionServices(String[] protectionServices) {
        this.protectionServices = protectionServices;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String[] getPsychoServices() {
        return psychoServices;
    }

    public void setPsychoServices(String[] psychoServices) {
        this.psychoServices = psychoServices;
    }

    public String getPsychosocialOther() {
        return psychosocialOther;
    }

    public void setPsychosocialOther(String psychosocialOther) {
        this.psychosocialOther = psychosocialOther;
    }

    public String getShelterOther() {
        return shelterOther;
    }

    public void setShelterOther(String shelterOther) {
        this.shelterOther = shelterOther;
    }

    public String[] getShelterServices() {
        return shelterServices;
    }

    public void setShelterServices(String[] shelterServices) {
        this.shelterServices = shelterServices;
    }

    public double getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(double currentHeight) {
        this.currentHeight = currentHeight;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    hhSerialNo=0;
    serviceDate=null;
    stableServices=null;
    healthServices=null;
    schoolServices=null;
    safetyServices=null;
    referralServices=null;
    reasonWithdrawal=null;
    gbvServices=null;
    organizationUnitId=null;
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
        if(getActionName()==null || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("ovcList") || getActionName().equalsIgnoreCase("childDetails") || getActionName().equalsIgnoreCase("serviceDetails") || getActionName().equalsIgnoreCase("householdDetails") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(getOvcId()==null || getOvcId().trim().equalsIgnoreCase("select") || getOvcId().trim().length()==0)
        errors.add("ovcId", new ActionMessage("errors.ovcId.required"));
               
        else if(this.getServiceDate()==null || this.getServiceDate().indexOf("/")==-1)
        errors.add("serviceDate", new ActionMessage("errors.serviceDate.required"));
        else if(!ValidationManager.isValidDate(getServiceDate()))
        errors.add("serviceDate", new ActionMessage("errors.serviceDate.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getServiceDate()))
        errors.add("serviceDate", new ActionMessage("errors.serviceDate.postdated"));
        else if(!ValidationManager.dateAfterEnrollmentDate(this.getOvcId(),getServiceDate(),AppConstant.OVC_TYPE_NUM))
        errors.add("serviceDate", new ActionMessage("errors.serviceDate.beforeEnrollment"));
        
        else if((getHealthServices()==null || getHealthServices().length==0) && (getSafetyServices()==null || getSafetyServices().length==0) && (getSchoolServices()==null || getSchoolServices().length==0) && (getStableServices()==null || getStableServices().length==0))
        errors.add("services", new ActionMessage("errors.services.required"));
        else if((this.getVolunteerName()==null || this.getVolunteerName().trim().equalsIgnoreCase("select")))
        errors.add("volunteerName", new ActionMessage("errors.volunteerName.required"));
        return errors;
    }
}
