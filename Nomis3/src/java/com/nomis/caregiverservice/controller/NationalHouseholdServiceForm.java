/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.caregiverservice.controller;

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
public class NationalHouseholdServiceForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String beneficiaryId;
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
    
    public NationalHouseholdServiceForm() {
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
    beneficiaryId=null;
    hhSerialNo=0;
    serviceDate=null;
    stableServices=null;
    healthServices=null;
    schoolServices=null;
    safetyServices=null;
    referralServices=null;
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
        if(getActionName()==null || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("householdDetails") || getActionName().equalsIgnoreCase("ahmDetails") || getActionName().equalsIgnoreCase("serviceDetails") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(this.getBeneficiaryId()==null || getBeneficiaryId().trim().equalsIgnoreCase("select") || getBeneficiaryId().trim().length()==0)
        errors.add("beneficiaryId", new ActionMessage("errors.beneficiaryId.required"));
               
        else if(this.getServiceDate()==null || this.getServiceDate().indexOf("/")==-1)
        errors.add("serviceDate", new ActionMessage("errors.serviceDate.required"));
        else if(!ValidationManager.isValidDate(getServiceDate()))
        errors.add("serviceDate", new ActionMessage("errors.serviceDate.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getServiceDate()))
        errors.add("serviceDate", new ActionMessage("errors.serviceDate.postdated"));
        else if(!ValidationManager.dateAfterEnrollmentDate(getBeneficiaryId(),getServiceDate(),AppConstant.CAREGIVER_TYPE_NUM))
        errors.add("serviceDate", new ActionMessage("errors.serviceDate.beforeEnrollment"));
        
        else if((getHealthServices()==null || getHealthServices().length==0) && (getSafetyServices()==null || getSafetyServices().length==0) && (getSchoolServices()==null || getSchoolServices().length==0) && (getStableServices()==null || getStableServices().length==0))
        errors.add("services", new ActionMessage("errors.services.required"));
        else if((this.getVolunteerName()==null || this.getVolunteerName().trim().equalsIgnoreCase("select")))
        errors.add("volunteerName", new ActionMessage("errors.volunteerName.required"));
        return errors;
    }
}
    /*private int id;
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int hhSerialNo;
    private String hhUniqueId;
    private String beneficiaryId;
    private int caregiverAge;
    private String careiverGender;
    private String currentHivStatus;
    private String serviceDate;
    private String[] stableServices;
    private String[] healthServices;
    private String[] schoolServices;
    private String[] safetyServices;
    private String[] referralServices;
    private String[] gbvServices;
    private String reasonWithdrawal;
    private int serviceNo;
    //private String volunteerDesignation;
    private String volunteerName;
    //private String stateCode;
    //private String lgaCode;
    //private String orgCode;
    //private String ward;
    
    //private String newHivStatus;
    //private String enrolledInCare;
    //private String enrolledOnART;
    //private String organizationClientIsReferred;
    //private List hivStatusList=new ArrayList();
    //private List referralDirectoryList=new ArrayList();
    
    
    public NationalHouseholdServiceForm() {
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

    public int getCaregiverAge() {
        return caregiverAge;
    }

    public void setCaregiverAge(int caregiverAge) {
        this.caregiverAge = caregiverAge;
    }

    public String getCareiverGender() {
        return careiverGender;
    }

    public void setCareiverGender(String careiverGender) {
        this.careiverGender = careiverGender;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String getCurrentHivStatus() {
        return currentHivStatus;
    }

    public void setCurrentHivStatus(String currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
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

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
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

    public int getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(int serviceNo) {
        this.serviceNo = serviceNo;
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
    }*/

    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    /*public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        return errors;
    }*/
//}
