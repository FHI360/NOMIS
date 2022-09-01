/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.childenrollment.controller;

import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ValidationManager;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class AdultHouseholdMemberForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private int hhSerialNo;
    private String hhUniqueId;
    private String hhName;
    private String dateOfEnrollment;
    private String firstName;
    private String surname;
    private String address;
    private String sex;
    private String phoneNumber;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int occupation;
    private int baselineHivStatus;
    private String dateOfBaselineHivStatus;
    private int enrolledOnTreatment;
    private String dateEnrolledOnTreatment;
    private String hivTreatmentFacilityId;
    private String treatmentId;
    private int ageAtBaseline;
    private int educationLevel;
    private String beneficiaryId;
    private String enrolledBeneficiaryId;
    private int maritalStatus;
    
    private int beneficiaryType;
       
    public AdultHouseholdMemberForm() {
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

    public int getBaselineHivStatus() {
        return baselineHivStatus;
    }

    public void setBaselineHivStatus(int baselineHivStatus) {
        this.baselineHivStatus = baselineHivStatus;
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

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEnrolledBeneficiaryId() {
        return enrolledBeneficiaryId;
    }

    public void setEnrolledBeneficiaryId(String enrolledBeneficiaryId) {
        this.enrolledBeneficiaryId = enrolledBeneficiaryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public int getAgeAtBaseline() {
        return ageAtBaseline;
    }

    public void setAgeAtBaseline(int ageAtBaseline) {
        this.ageAtBaseline = ageAtBaseline;
    }

    public String getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(String dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public String getDateOfBaselineHivStatus() {
        return dateOfBaselineHivStatus;
    }

    public void setDateOfBaselineHivStatus(String dateOfBaselineHivStatus) {
        this.dateOfBaselineHivStatus = dateOfBaselineHivStatus;
    }

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public String getHivTreatmentFacilityId() {
        return hivTreatmentFacilityId;
    }

    public void setHivTreatmentFacilityId(String hivTreatmentFacilityId) {
        this.hivTreatmentFacilityId = hivTreatmentFacilityId;
    }

    public int getHhSerialNo() {
        return hhSerialNo;
    }

    public void setHhSerialNo(int hhSerialNo) {
        this.hhSerialNo = hhSerialNo;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    

    @Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    hhUniqueId=null;
    dateOfEnrollment=null;
    firstName=null;
    surname=null;
    address=null;
    sex=null;
    phoneNumber=null;
    occupation=0;
    baselineHivStatus=0;
    educationLevel=0;
    beneficiaryId=null;
    hhName=null;
    maritalStatus=0;
    ageAtBaseline=0;
    dateOfBaselineHivStatus=null;
    enrolledOnTreatment=0;
    dateEnrolledOnTreatment=null;
    hivTreatmentFacilityId=null;
    treatmentId=null;
    hhSerialNo=0;
    beneficiaryType=0;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        String dbDateOfEnrollment=DateManager.processMthDayYearToMysqlFormat(this.getDateOfEnrollment());
                
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("delete") || getActionName().equalsIgnoreCase("ahmList") || getActionName().equalsIgnoreCase("ahmDetails"))
        return errors;
        else if(dbDateOfEnrollment !=null)
        {
            int currentAge=this.getAgeAtBaseline();
            if(this.getDateOfEnrollment() !=null && this.getDateOfEnrollment().indexOf("/") !=-1)
            currentAge=AppManager.getCurrentAge(DateManager.processMthDayYearToMysqlFormat(this.getDateOfEnrollment()), this.getAgeAtBaseline());
            Date dateOfBirth=DateManager.getPreviousDate(DateManager.getCurrentDateInstance(), currentAge);
            Date dDateOfBaselineHivStatus=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateOfBaselineHivStatus()));
            
            Beneficiary ahm=ValidationManager.getBeneficiaryByName(getHhUniqueId(),getFirstName(), getSurname(), AppConstant.CAREGIVER_TYPE_NUM);
            if(ahm !=null && getActionName().equalsIgnoreCase("save"))
            errors.add("firstName", new ActionMessage("errors.caregiver.exists"));
            /*else if(ahm !=null && getActionName().equalsIgnoreCase("modify"))
            {
                errors.add("firstName", new ActionMessage("errors.caregiver.exists"));
            }*/
            else if(this.getAgeAtBaseline()<18)
            errors.add("ageAtBaseline", new ActionMessage("errors.ahm.underAged"));
            else if(getAgeAtBaseline()>200)
            errors.add("ageAtBaseline", new ActionMessage("errors.ahm.wrongAge"));
            
            else if(getCboId()==null || getCboId().trim().equalsIgnoreCase("select"))
            errors.add("cboId", new ActionMessage("errors.cboId.required"));
            else if(this.getOrganizationUnitId()==null || getOrganizationUnitId().trim().equalsIgnoreCase("select"))
            errors.add("organizationUnit", new ActionMessage("errors.organizationUnit.required"));
            else if(this.getSurname()==null || getSurname().length()<3)
            errors.add("surname", new ActionMessage("errors.surname.required"));
            else if(getSurname().length()>25)
            errors.add("surname", new ActionMessage("errors.surname.toolong"));
            else if(getFirstName()==null || this.getFirstName().length()<3)
            errors.add("firstName", new ActionMessage("errors.firstName.required"));
            else if(getFirstName().length()>25)
            errors.add("firstName", new ActionMessage("errors.firstName.toolong"));
            else if(getDateOfEnrollment()==null ||  getDateOfEnrollment().indexOf("/")==-1)
            errors.add("dateOfEnrollment", new ActionMessage("errors.dateOfEnrollment.required"));
            else if(!ValidationManager.isValidDate(getDateOfEnrollment()))
            errors.add("dateOfEnrollment", new ActionMessage("errors.dateOfEnrollment.required"));
            else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateOfEnrollment()))
            errors.add("dateOfEnrollment", new ActionMessage("errors.dateOfEnrollment.postdated"));
            
            else if(this.getSex()==null || getSex().equalsIgnoreCase("select"))
            errors.add("sex", new ActionMessage("errors.sex.required"));
            
            else if(getBaselineHivStatus()==0)
            errors.add("baselineHivStatus", new ActionMessage("errors.baselineHivStatus.required"));
            else if((getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM || getBaselineHivStatus()==AppConstant.HIV_NEGATIVE_NUM) && (this.getDateOfBaselineHivStatus()==null || this.getDateOfBaselineHivStatus().indexOf("/")==-1))
            errors.add("dateOfBaselineHivStatus", new ActionMessage("errors.dateOfBaselineHivStatus.required"));
            else if(dateOfBirth !=null && dDateOfBaselineHivStatus !=null && dateOfBirth.after(dDateOfBaselineHivStatus))
            errors.add("dateOfBaselineHivStatus", new ActionMessage("errors.dateOfBaselineHivStatus.beforeDateOfBirth"));
            else if(getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
            {
                if(this.getEnrolledOnTreatment()==0)
                errors.add("enrolledOnTreatment", new ActionMessage("errors.enrolledOnTreatment.required"));
                else if(this.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM )
                {
                    if(this.getDateEnrolledOnTreatment()==null || this.getDateEnrolledOnTreatment().indexOf("/")==-1)
                    errors.add("dateEnrolledOnTreatment", new ActionMessage("errors.dateEnrolledOnTreatment.required"));
                    else if(!ValidationManager.isDateEnrolledOnTreatmentBeforeDateOfHivStatus(this.getDateOfBaselineHivStatus(),this.getDateEnrolledOnTreatment()))
                    errors.add("dateEnrolledOnTreatment", new ActionMessage("errors.dateEnrolledOnTreatment.beforeDateOfBaselineHivStatus"));
                    else if((this.getHivTreatmentFacilityId()==null || this.getHivTreatmentFacilityId().trim().equalsIgnoreCase("select")))
                    errors.add("hivTreatmentFacilityId", new ActionMessage("errors.hivTreatmentFacilityId.required"));
                    else if((this.getTreatmentId()==null || this.getTreatmentId().trim().length()==0))
                    errors.add("treatmentId", new ActionMessage("errors.treatmentId.required"));
                    else if((this.getTreatmentId() !=null && this.getTreatmentId().trim().length()>25))
                    errors.add("treatmentId", new ActionMessage("errors.treatmentId.toolong"));
                }
            }
            //else if(this.getBaselineHivStatus()==0)
            //errors.add("hivStatus", new ActionMessage("errors.hivStatus.required"));
            //else if(this.getOccupation()==0)
            //errors.add("occupation", new ActionMessage("errors.occupation.required"));
            //else if(this.getEducationLevel()==0)
            //errors.add("educationLevel", new ActionMessage("errors.educationLevel.required"));
            //else if(getBeneficiaryId()==null)
            //errors.add("beneficiaryId", new ActionMessage("error.clientId.invalid"));    
            //else if(getBeneficiaryId().indexOf("-") !=-1)
            //errors.add("beneficiaryId", new ActionMessage("error.clientId.invalid"));
        }
        return errors;
    }
}
