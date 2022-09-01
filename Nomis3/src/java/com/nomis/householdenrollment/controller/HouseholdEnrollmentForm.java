/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.householdenrollment.controller;

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
public class HouseholdEnrollmentForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String hhUniqueId;
    private String existingHhUniqueId;
    private int serialNo;
    private int cgAge;
    private int maritalStatus;
    private int baselineHivStatus;
    private String dateOfBaselineHivStatus;
    private int enrolledOnTreatment;
    private String dateEnrolledOnTreatment;
    private String hivTreatmentFacilityId;
    private String treatmentId;
    private int hivStatusKnown;
    private int hivPositiveLinked;
    private int hasViralLoadResult;
    private int hivPreventionKnowledge;
    private int childUndernourished;
    private int childrenHasBirthCertificate;
    private int stableAdult;
    private int violenceExperienceReported;
    private int regularSchoolAttendance;
    private int cgEngagedInEconomicActivities;
    private int childrenEnrolledInSchool;
    private int socialEmotionalSupport;
    private String volunteerName;
    private String dateOfAssessment;
    private String firstName;
    private String surname;
    private String address;
    private String cgSex;
    private String cgPhoneNumber;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
        
    public HouseholdEnrollmentForm() {
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

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String getDateOfAssessment() 
    {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) 
    {
        this.dateOfAssessment = dateOfAssessment;
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

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
    }

    public String getExistingHhUniqueId() {
        return existingHhUniqueId;
    }

    public void setExistingHhUniqueId(String existingHhUniqueId) {
        this.existingHhUniqueId = existingHhUniqueId;
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
    
    public String getCgPhoneNumber() {
        return cgPhoneNumber;
    }

    public void setCgPhoneNumber(String cgPhoneNumber) {
        this.cgPhoneNumber = cgPhoneNumber;
    }

    public String getCgSex() {
        return cgSex;
    }

    public void setCgSex(String cgSex) {
        this.cgSex = cgSex;
    }
    public int getCgAge() {
        return cgAge;
    }

    public void setCgAge(int cgAge) {
        this.cgAge = cgAge;
    }

    public int getHasViralLoadResult() {
        return hasViralLoadResult;
    }

    public void setHasViralLoadResult(int hasViralLoadResult) {
        this.hasViralLoadResult = hasViralLoadResult;
    }

    public int getHivPositiveLinked() {
        return hivPositiveLinked;
    }

    public void setHivPositiveLinked(int hivPositiveLinked) {
        this.hivPositiveLinked = hivPositiveLinked;
    }

    public int getHivPreventionKnowledge() {
        return hivPreventionKnowledge;
    }

    public void setHivPreventionKnowledge(int hivPreventionKnowledge) {
        this.hivPreventionKnowledge = hivPreventionKnowledge;
    }

    public int getHivStatusKnown() {
        return hivStatusKnown;
    }

    public void setHivStatusKnown(int hivStatusKnown) {
        this.hivStatusKnown = hivStatusKnown;
    }

    public int getRegularSchoolAttendance() {
        return regularSchoolAttendance;
    }

    public void setRegularSchoolAttendance(int regularSchoolAttendance) {
        this.regularSchoolAttendance = regularSchoolAttendance;
    }

    public int getStableAdult() {
        return stableAdult;
    }

    public void setStableAdult(int stableAdult) {
        this.stableAdult = stableAdult;
    }

    public int getChildUndernourished() {
        return childUndernourished;
    }

    public void setChildUndernourished(int childUndernourished) {
        this.childUndernourished = childUndernourished;
    }

    public int getSocialEmotionalSupport() {
        return socialEmotionalSupport;
    }

    public void setSocialEmotionalSupport(int socialEmotionalSupport) {
        this.socialEmotionalSupport = socialEmotionalSupport;
    }

    public int getCgEngagedInEconomicActivities() {
        return cgEngagedInEconomicActivities;
    }

    public void setCgEngagedInEconomicActivities(int cgEngagedInEconomicActivities) {
        this.cgEngagedInEconomicActivities = cgEngagedInEconomicActivities;
    }

    public int getChildrenEnrolledInSchool() {
        return childrenEnrolledInSchool;
    }

    public void setChildrenEnrolledInSchool(int childrenEnrolledInSchool) {
        this.childrenEnrolledInSchool = childrenEnrolledInSchool;
    }

    public int getChildrenHasBirthCertificate() {
        return childrenHasBirthCertificate;
    }

    public void setChildrenHasBirthCertificate(int childrenHasBirthCertificate) {
        this.childrenHasBirthCertificate = childrenHasBirthCertificate;
    }

    public int getViolenceExperienceReported() {
        return violenceExperienceReported;
    }

    public void setViolenceExperienceReported(int violenceExperienceReported) {
        this.violenceExperienceReported = violenceExperienceReported;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public int getBaselineHivStatus() {
        return baselineHivStatus;
    }

    public void setBaselineHivStatus(int baselineHivStatus) {
        this.baselineHivStatus = baselineHivStatus;
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

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    hhUniqueId=null;
    existingHhUniqueId=null;
    dateOfAssessment=null;
    address=null;
    serialNo=0;
    firstName=null;
    surname=null;
    cgSex=null;
    cgAge=0;
    maritalStatus=0;
       
    hivStatusKnown=0;
    hivPositiveLinked=0;
    hasViralLoadResult=0;
    hivPreventionKnowledge=0;
    childUndernourished=0;
    stableAdult=0;
    regularSchoolAttendance=0;
    socialEmotionalSupport=0;
    childrenHasBirthCertificate=0;
    violenceExperienceReported=0;
    regularSchoolAttendance=0;
    cgEngagedInEconomicActivities=0;
    childrenEnrolledInSchool=0;
    volunteerName=null;
    baselineHivStatus=0;
    dateOfBaselineHivStatus=null;
    enrolledOnTreatment=0;
    dateEnrolledOnTreatment=null;
    hivTreatmentFacilityId=null;
    treatmentId=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("loadHousehold") || getActionName().equalsIgnoreCase("hhvaDetails") || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        //Date dDateOfAssessment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateOfAssessment()));
        int currentAge=this.getCgAge();
        if(this.getDateOfAssessment() !=null && this.getDateOfAssessment().indexOf("/") !=-1)
        currentAge=AppManager.getCurrentAge(DateManager.processMthDayYearToMysqlFormat(this.getDateOfAssessment()), this.getCgAge());
        Date dateOfBirth=DateManager.getPreviousDate(DateManager.getCurrentDateInstance(), currentAge);
        Date dDateOfBaselineHivStatus=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateOfBaselineHivStatus()));
        
        Beneficiary ahm=ValidationManager.getBeneficiaryByName(getHhUniqueId(),getFirstName(), getSurname(), AppConstant.CAREGIVER_TYPE_NUM);
        if(ahm !=null && getActionName().equalsIgnoreCase("save"))
        errors.add("firstName", new ActionMessage("errors.caregiver.exists"));
        if(getCboId()==null || getCboId().trim().equalsIgnoreCase("select"))
        errors.add("cboId", new ActionMessage("errors.cboId.required"));
        else if(this.getOrganizationUnitId()==null || getOrganizationUnitId().trim().equalsIgnoreCase("select"))
        errors.add("organizationUnit", new ActionMessage("errors.organizationUnit.required"));
        
        else if(this.getDateOfAssessment()==null ||  getDateOfAssessment().indexOf("/")==-1)
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
        else if(!ValidationManager.isValidDate(getDateOfAssessment()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateOfAssessment()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.postdated"));
        //else if(dateOfBirth !=null && dDateOfAssessment !=null && dateOfBirth.after(dDateOfAssessment))
        //errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.beforeDateOfBirth"));
        
        else if(getFirstName()==null || this.getFirstName().length()<3)
        errors.add("cgFirstName", new ActionMessage("errors.firstName.required"));
        else if(getFirstName().length()>25)
        errors.add("firstName", new ActionMessage("errors.firstName.toolong"));
        else if(this.getSurname()==null || getSurname().length()<3)
        errors.add("cgSurname", new ActionMessage("errors.surname.required"));
        else if(getSurname().length()>25)
        errors.add("surname", new ActionMessage("errors.surname.toolong"));
        else if(this.getAddress()==null || getAddress().length()<3)
        errors.add("address", new ActionMessage("errors.address.required"));
        else if(this.getCgSex()==null || getCgSex().length()<1)
        errors.add("sex", new ActionMessage("errors.sex.required"));
        else if(this.getCgAge()<16)
        errors.add("cgAge", new ActionMessage("errors.ahm.underAged"));
        
        else if(getBaselineHivStatus()==0)
        errors.add("baselineHivStatus", new ActionMessage("errors.baselineHivStatus.required"));
        else if((getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM || getBaselineHivStatus()==AppConstant.HIV_NEGATIVE_NUM) && (this.getDateOfBaselineHivStatus()==null || this.getDateOfBaselineHivStatus().indexOf("/")==-1))
        errors.add("dateOfBaselineHivStatus", new ActionMessage("errors.dateOfBaselineHivStatus.required"));
        else if(dateOfBirth !=null && dDateOfBaselineHivStatus !=null && dateOfBirth.after(dDateOfBaselineHivStatus))
        errors.add("dateOfBaselineHivStatus", new ActionMessage("errors.dateOfBaselineHivStatus.beforeDateOfBirth"));
            /*else if(!ValidationManager.isDateAfterEnrollmentDate(this.getDateOfAssessment(),this.getDateOfBaselineHivStatus()))
        errors.add("dateOfBaselineHivStatus", new ActionMessage("errors.dateOfBaselineHivStatus.beforeEnrollment"));*/
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
        
        
        /*else if(DateManager.compareDates(getDateOfAssessment(),getPrCgiverDateOfBirth()))
        errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.b4dob"));*/
        else if(this.getCgEngagedInEconomicActivities()==0)
        errors.add("cgEngagedInEconomicActivities", new ActionMessage("errors.cgEngagedInEconomicActivities.required"));
        else if(this.getChildUndernourished()==0)
        errors.add("childUndernourished", new ActionMessage("errors.childUndernourished.required"));
        else if(this.getChildrenEnrolledInSchool()==0)
        errors.add("childrenEnrolledInSchool", new ActionMessage("errors.childrenEnrolledInSchool.required"));
        else if(this.getChildrenHasBirthCertificate()==0)
        errors.add("childrenHasBirthCertificate", new ActionMessage("errors.childrenHasBirthCertificate.required"));
        else if(this.getHasViralLoadResult()==0)
        errors.add("hasViralLoadResult", new ActionMessage("errors.hasViralLoadResult.required"));
        else if(this.getHivPositiveLinked()==0)
        errors.add("hivPositiveLinked", new ActionMessage("errors.hivPositiveLinked.required"));
        
        else if(this.getHivPreventionKnowledge()==0)
        errors.add("hivPreventionKnowledge", new ActionMessage("errors.hivPreventionKnowledge.required"));
        else if(this.getHivStatusKnown()==0)
        errors.add("hivStatusKnown", new ActionMessage("errors.hivStatusKnown.required"));
        else if(this.getRegularSchoolAttendance()==0)
        errors.add("regularSchoolAttendance", new ActionMessage("errors.regularSchoolAttendance.required"));
        else if(this.getSocialEmotionalSupport()==0)
        errors.add("socialEmotionalSupport", new ActionMessage("errors.socialEmotionalSupport.required"));
        
        else if(this.getStableAdult()==0)
        errors.add("stableAdult", new ActionMessage("errors.stableAdult.required"));
        else if(this.getViolenceExperienceReported()==0)
        errors.add("violenceExperienceReported", new ActionMessage("errors.violenceExperienceReported.required"));
        else if(this.getVolunteerName()==null || getVolunteerName().trim().equalsIgnoreCase("select"))
        errors.add("volunteerName", new ActionMessage("errors.volunteerName.required"));
        
        return errors;
    }
}
