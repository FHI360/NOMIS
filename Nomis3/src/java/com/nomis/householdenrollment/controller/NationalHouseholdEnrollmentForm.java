/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.householdenrollment.controller;

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
public class NationalHouseholdEnrollmentForm extends org.apache.struts.action.ActionForm 
{
   private String actionName;
    private String hhUniqueId;
    private String existingHhUniqueId;
    private int serialNo;
    private int cgAge;
    private int baselineHivStatus;
    private String dateOfBaselineHivStatus;
    private int enrolledOnTreatment;
    private String dateEnrolledOnTreatment;
    private String hivTreatmentFacilityId;
    private String treatmentId;
    /*private int hivStatusKnown;
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
    private int socialEmotionalSupport;*/
    private String volunteerName;
    private String dateOfAssessment;
    private String cgFirstName;
    private String cgSurname;
    private String address;
    private String cgSex;
    private String cgPhoneNumber;
    private int numberOfPeopleInHh;
    private int numberOfChildrenInHh;
    private int occupation;
    private int educationalLevel;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    
    private int hhHeadship;
    private int health;
    private int educationLevel;
    private int hhEducationLevel;
    private int childEducationLevel;
    private int shelterAndHousing;
    private int foodSecurityAndNutrition;
    private int protection;
    private int meansOfLivelihood;
    private int hhIncome;
        
    public NationalHouseholdEnrollmentForm() {
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

    
    public String getCgFirstName() {
        return cgFirstName;
    }

    public void setCgFirstName(String cgFirstName) {
        this.cgFirstName = cgFirstName;
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

    public String getCgSurname() {
        return cgSurname;
    }

    public void setCgSurname(String cgSurname) {
        this.cgSurname = cgSurname;
    }
    
    public int getCgAge() {
        return cgAge;
    }

    public void setCgAge(int cgAge) {
        this.cgAge = cgAge;
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

    public int getChildEducationLevel() {
        return childEducationLevel;
    }

    public void setChildEducationLevel(int childEducationLevel) {
        this.childEducationLevel = childEducationLevel;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getFoodSecurityAndNutrition() {
        return foodSecurityAndNutrition;
    }

    public void setFoodSecurityAndNutrition(int foodSecurityAndNutrition) {
        this.foodSecurityAndNutrition = foodSecurityAndNutrition;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHhEducationLevel() {
        return hhEducationLevel;
    }

    public void setHhEducationLevel(int hhEducationLevel) {
        this.hhEducationLevel = hhEducationLevel;
    }

    public int getHhHeadship() {
        return hhHeadship;
    }

    public void setHhHeadship(int hhHeadship) {
        this.hhHeadship = hhHeadship;
    }

    public int getHhIncome() {
        return hhIncome;
    }

    public void setHhIncome(int hhIncome) {
        this.hhIncome = hhIncome;
    }

    public int getMeansOfLivelihood() {
        return meansOfLivelihood;
    }

    public void setMeansOfLivelihood(int meansOfLivelihood) {
        this.meansOfLivelihood = meansOfLivelihood;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getShelterAndHousing() {
        return shelterAndHousing;
    }

    public void setShelterAndHousing(int shelterAndHousing) {
        this.shelterAndHousing = shelterAndHousing;
    }

    public int getNumberOfChildrenInHh() {
        return numberOfChildrenInHh;
    }

    public void setNumberOfChildrenInHh(int numberOfChildrenInHh) {
        this.numberOfChildrenInHh = numberOfChildrenInHh;
    }

    public int getNumberOfPeopleInHh() {
        return numberOfPeopleInHh;
    }

    public void setNumberOfPeopleInHh(int numberOfPeopleInHh) {
        this.numberOfPeopleInHh = numberOfPeopleInHh;
    }

    public int getEducationalLevel() {
        return educationalLevel;
    }

    public void setEducationalLevel(int educationalLevel) {
        this.educationalLevel = educationalLevel;
    }

    public int getOccupation() {
        return occupation;
    }

    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }
    
   private boolean isHouseholdAccessed()
    {
        if((getEducationLevel()==0 && this.getChildEducationLevel()==0) || getFoodSecurityAndNutrition()==0
         || getHealth()==0 || getMeansOfLivelihood()==0 || getShelterAndHousing()==0
         || getHhHeadship()==0 || getHhIncome()==0)
        return false;
        return true;
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
    cgFirstName=null;
    cgSurname=null;
    cgSex=null;
    cgAge=0;
    numberOfChildrenInHh=0;   
    numberOfPeopleInHh=0;
    occupation=0;
    educationalLevel=0;
    volunteerName=null;
    baselineHivStatus=0;
    dateOfBaselineHivStatus=null;
    enrolledOnTreatment=0;
    dateEnrolledOnTreatment=null;
    hivTreatmentFacilityId=null;
    treatmentId=null;
    
    hhHeadship=0;
    health=0;
    educationLevel=0;
    hhEducationLevel=0;
    childEducationLevel=0;
    shelterAndHousing=0;
    foodSecurityAndNutrition=0;
    protection=0;
    meansOfLivelihood=0;
    hhIncome=0;
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
        
        else if(getCgFirstName()==null || this.getCgFirstName().length()<3)
        errors.add("cgFirstName", new ActionMessage("errors.firstName.required"));
        else if(getCgFirstName().length()>25)
        errors.add("firstName", new ActionMessage("errors.firstName.toolong"));
        else if(this.getCgSurname()==null || getCgSurname().length()<3)
        errors.add("cgSurname", new ActionMessage("errors.surname.required"));
        else if(getCgSurname().length()>25)
        errors.add("surname", new ActionMessage("errors.surname.toolong"));
        else if(this.getAddress()==null || getAddress().length()<3)
        errors.add("address", new ActionMessage("errors.address.required"));
        else if(this.getCgSex()==null || getCgSex().length()<1)
        errors.add("sex", new ActionMessage("errors.sex.required"));
        else if(getCgAge()<10)
        errors.add("hhAge", new ActionMessage("errors.hhAge.wrong"));
        
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
        else if(this.getVolunteerName()==null || getVolunteerName().trim().equalsIgnoreCase("select"))
        errors.add("volunteerName", new ActionMessage("errors.volunteerName.required"));
        if(errors.isEmpty())
        {           
            if(!isHouseholdAccessed())
            errors.add("thematicAreas", new ActionMessage("errors.thematicAreas.required")); 
            else if(getHhHeadship()==4 && getCgAge()>21)
            errors.add("hhHeadship", new ActionMessage("errors.childHeadedHousehold.wrong"));
            else if(getHhHeadship()!=4 && getCgAge()<18)
            errors.add("hhHeadship", new ActionMessage("errors.childHeadedHousehold.NotSelected"));
        } 
        return errors;
    }
}
