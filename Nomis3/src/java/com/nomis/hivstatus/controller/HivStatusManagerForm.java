/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.hivstatus.controller;

import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ValidationManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class HivStatusManagerForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private int hhSerialNo;
    private String hhUniqueId;
    private String beneficiaryId;
    private String dateOfBirth;
    private String dateOfEnrollment;
    private String sex;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int lastHivStatus;
    private int educationLevel;
    private String hhName;
    private String facilityId;
    private int enrolledOnTreatment;
    private String dateEnrolledOnTreatment;
    private String dateOfNewStatus;
    private int newHivStatus;
    private String beneficiaryName;
    private int beneficiaryType;
    public HivStatusManagerForm() {
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

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(String dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfNewStatus() {
        return dateOfNewStatus;
    }

    public void setDateOfNewStatus(String dateOfNewStatus) {
        this.dateOfNewStatus = dateOfNewStatus;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public int getNewHivStatus() {
        return newHivStatus;
    }

    public void setNewHivStatus(int newHivStatus) {
        this.newHivStatus = newHivStatus;
    }

    public int getLastHivStatus() {
        return lastHivStatus;
    }

    public void setLastHivStatus(int lastHivStatus) {
        this.lastHivStatus = lastHivStatus;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
    }

    public int getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(int beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    hhSerialNo=0;
    hhUniqueId=null;
    dateOfBirth=null;
    dateOfEnrollment=null;
    sex=null;
    lastHivStatus=0;
    educationLevel=0;
    beneficiaryId=null;
    hhName=null;
    facilityId=null;
    enrolledOnTreatment=0;
    dateEnrolledOnTreatment=null;
    dateOfNewStatus=null;
    newHivStatus=0;
    beneficiaryName=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(getActionName()==null || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("beneficiaryList") || getActionName().equalsIgnoreCase("childDetails") || getActionName().equalsIgnoreCase("level4OuList"))
        return errors;
        else
        {
            System.err.println("getDateOfNewStatus() is "+getDateOfNewStatus());
            if(getActionName().equalsIgnoreCase("modify"))
            {
                if(getLastHivStatus()==0)
                errors.add("lastHivStatus", new ActionMessage("errors.lastHivStatus.required"));
                else if(getDateOfNewStatus()==null || !ValidationManager.isValidDate(getDateOfNewStatus()))
                errors.add("dateOfNewStatus", new ActionMessage("errors.dateOfNewStatus.required"));
                else if(!DateManager.compareDates(getDateOfNewStatus(),DateManager.getCurrentDate()))
                errors.add("dateOfNewStatus", new ActionMessage("errors.dateOfNewStatus.infuture"));
            }
            else if(getActionName().equalsIgnoreCase("save"))
            {
                if(getNewHivStatus()==0)
                errors.add("newHivStatus", new ActionMessage("errors.newHivStatus.required"));
                else if(getDateOfNewStatus()==null || !ValidationManager.isValidDate(getDateOfNewStatus()))
                errors.add("dateOfNewStatus", new ActionMessage("errors.dateOfNewStatus.required"));
                else if(!DateManager.compareDates(getDateOfNewStatus(),DateManager.getCurrentDate()))
                errors.add("dateOfNewStatus", new ActionMessage("errors.dateOfNewStatus.infuture"));
            }
        }
        return errors;
    }
}
