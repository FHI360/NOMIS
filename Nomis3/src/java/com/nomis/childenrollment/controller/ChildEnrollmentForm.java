/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.childenrollment.controller;

import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.FacilityOvcOffer;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.AppManager;
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
public class ChildEnrollmentForm extends org.apache.struts.action.ActionForm {
    
private String actionName;
private String cboId;
private String level2OuId;
private String level3OuId;
private String organizationUnitId;
private String facilityOvcOfferTreatmentId;
private int hhSerialNo;
private int childSerialNo;
private String hhUniqueId;
private String ovcId;
private String enrolledChildId;
private String hiddenBeneficiaryId;

private String hhName;
private String dateOfEnrollment;
private String firstName;
private String surname;
private String address;
private String sex;
private String phoneNumber;
private int baselineHivStatus;
private String dateOfBaselineHivStatus;
private int ageAtBaseline;
private int ageUnitAtBaseline;
private String mainVulnerabilityStatus;
private String[] otherVulnerabilityStatus;
private int birthCertificate;
private int schoolStatus;
private String schoolName;
private String grade;
private String caregiverId;
private int caregiverRelationship;
private String caregiverSex;
private int caregiverAge;
private String caregiverPhone;
private String caregiverHivStatus;
private String dateOfCaregiverHivStatus;
private String caregiverEnrolledOnTreatment;
private String dateCaregiverEnrolledOnTreatment;
private String facilityCaregiverEnrolled;
private double weight;
private double height;
private String verifiedBy;
private int enrolledOnTreatment;
private String dateEnrolledOnTreatment;
private String treatmentId;
private String hivTreatmentFacilityId;
//private String selectedOvcId;
private int sourceOfInfo;
private String volunteerName;

private int foodSecurity;
private int wellness;
private int nutritionAndGrowth;
private int healthCareServices;

private int shelter;
private int emotionalHealth;
private int care;
private int socialBehaviour;
private int abuseAndExploitation;
private int developmentAndPerformance;
private int legalProtection;
private int educationAndWork;

private int beneficiaryType;
private int enrollmentSetting;
//private int orphanStatus;
    
    public ChildEnrollmentForm() {
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

    public int getAgeAtBaseline() {
        return ageAtBaseline;
    }

    public void setAgeAtBaseline(int ageAtBaseline) {
        this.ageAtBaseline = ageAtBaseline;
    }

    public int getAgeUnitAtBaseline() {
        return ageUnitAtBaseline;
    }

    public void setAgeUnitAtBaseline(int ageUnitAtBaseline) {
        this.ageUnitAtBaseline = ageUnitAtBaseline;
    }
   
    public int getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(int birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
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

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDateOfEnrollment() 
    {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public int getBaselineHivStatus() {
        return baselineHivStatus;
    }

    public void setBaselineHivStatus(int baselineHivStatus) {
        this.baselineHivStatus = baselineHivStatus;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolStatus() {
        return schoolStatus;
    }

    public void setSchoolStatus(int schoolStatus) {
        this.schoolStatus = schoolStatus;
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

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getMainVulnerabilityStatus() {
        return mainVulnerabilityStatus;
    }

    public void setMainVulnerabilityStatus(String mainVulnerabilityStatus) {
        this.mainVulnerabilityStatus = mainVulnerabilityStatus;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCaregiverRelationship() {
        return caregiverRelationship;
    }

    public void setCaregiverRelationship(int caregiverRelationship) {
        this.caregiverRelationship = caregiverRelationship;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getSourceOfInfo() {
        return sourceOfInfo;
    }

    public void setSourceOfInfo(int sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
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

    public String getHiddenBeneficiaryId() {
        return hiddenBeneficiaryId;
    }

    public void setHiddenBeneficiaryId(String hiddenBeneficiaryId) {
        this.hiddenBeneficiaryId = hiddenBeneficiaryId;
    }

    public String getHivTreatmentFacilityId() {
        return hivTreatmentFacilityId;
    }

    public void setHivTreatmentFacilityId(String hivTreatmentFacilityId) {
        this.hivTreatmentFacilityId = hivTreatmentFacilityId;
    }

    public int getCaregiverAge() {
        return caregiverAge;
    }

    public void setCaregiverAge(int caregiverAge) {
        this.caregiverAge = caregiverAge;
    }

    public String getCaregiverSex() {
        return caregiverSex;
    }

    public void setCaregiverSex(String caregiverSex) {
        this.caregiverSex = caregiverSex;
    }

    public String getCaregiverPhone() {
        return caregiverPhone;
    }

    public void setCaregiverPhone(String caregiverPhone) {
        this.caregiverPhone = caregiverPhone;
    }

    public int getAbuseAndExploitation() {
        return abuseAndExploitation;
    }

    public void setAbuseAndExploitation(int abuseAndExploitation) {
        this.abuseAndExploitation = abuseAndExploitation;
    }

    public int getCare() {
        return care;
    }

    public void setCare(int care) {
        this.care = care;
    }

    public int getDevelopmentAndPerformance() {
        return developmentAndPerformance;
    }

    public void setDevelopmentAndPerformance(int developmentAndPerformance) {
        this.developmentAndPerformance = developmentAndPerformance;
    }

    public int getEducationAndWork() {
        return educationAndWork;
    }

    public void setEducationAndWork(int educationAndWork) {
        this.educationAndWork = educationAndWork;
    }

    public int getEmotionalHealth() {
        return emotionalHealth;
    }

    public void setEmotionalHealth(int emotionalHealth) {
        this.emotionalHealth = emotionalHealth;
    }

    public int getFoodSecurity() {
        return foodSecurity;
    }

    public void setFoodSecurity(int foodSecurity) {
        this.foodSecurity = foodSecurity;
    }

    public int getHealthCareServices() {
        return healthCareServices;
    }

    public void setHealthCareServices(int healthCareServices) {
        this.healthCareServices = healthCareServices;
    }

    public int getLegalProtection() {
        return legalProtection;
    }

    public void setLegalProtection(int legalProtection) {
        this.legalProtection = legalProtection;
    }

    public int getNutritionAndGrowth() {
        return nutritionAndGrowth;
    }

    public void setNutritionAndGrowth(int nutritionAndGrowth) {
        this.nutritionAndGrowth = nutritionAndGrowth;
    }

    public int getShelter() {
        return shelter;
    }

    public void setShelter(int shelter) {
        this.shelter = shelter;
    }

    public int getSocialBehaviour() {
        return socialBehaviour;
    }

    public void setSocialBehaviour(int socialBehaviour) {
        this.socialBehaviour = socialBehaviour;
    }

    public int getWellness() {
        return wellness;
    }

    public void setWellness(int wellness) {
        this.wellness = wellness;
    }

    public String getDateOfBaselineHivStatus() {
        return dateOfBaselineHivStatus;
    }

    public void setDateOfBaselineHivStatus(String dateOfBaselineHivStatus) {
        this.dateOfBaselineHivStatus = dateOfBaselineHivStatus;
    }

    public int getChildSerialNo() {
        return childSerialNo;
    }

    public void setChildSerialNo(int childSerialNo) {
        this.childSerialNo = childSerialNo;
    }

    public int getHhSerialNo() {
        return hhSerialNo;
    }

    public void setHhSerialNo(int hhSerialNo) {
        this.hhSerialNo = hhSerialNo;
    }

    public String getEnrolledChildId() {
        return enrolledChildId;
    }

    public void setEnrolledChildId(String enrolledChildId) {
        this.enrolledChildId = enrolledChildId;
    }

    public String getCaregiverEnrolledOnTreatment() {
        return caregiverEnrolledOnTreatment;
    }

    public void setCaregiverEnrolledOnTreatment(String caregiverEnrolledOnTreatment) {
        this.caregiverEnrolledOnTreatment = caregiverEnrolledOnTreatment;
    }

    public String getCaregiverHivStatus() {
        return caregiverHivStatus;
    }

    public void setCaregiverHivStatus(String caregiverHivStatus) {
        this.caregiverHivStatus = caregiverHivStatus;
    }

    public String getDateOfCaregiverHivStatus() {
        return dateOfCaregiverHivStatus;
    }

    public void setDateOfCaregiverHivStatus(String dateOfCaregiverHivStatus) {
        this.dateOfCaregiverHivStatus = dateOfCaregiverHivStatus;
    }

    public String getFacilityCaregiverEnrolled() {
        return facilityCaregiverEnrolled;
    }

    public void setFacilityCaregiverEnrolled(String facilityCaregiverEnrolled) {
        this.facilityCaregiverEnrolled = facilityCaregiverEnrolled;
    }

    public String getDateCaregiverEnrolledOnTreatment() {
        return dateCaregiverEnrolledOnTreatment;
    }

    public void setDateCaregiverEnrolledOnTreatment(String dateCaregiverEnrolledOnTreatment) {
        this.dateCaregiverEnrolledOnTreatment = dateCaregiverEnrolledOnTreatment;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String[] getOtherVulnerabilityStatus() {
        return otherVulnerabilityStatus;
    }

    public void setOtherVulnerabilityStatus(String[] otherVulnerabilityStatus) {
        this.otherVulnerabilityStatus = otherVulnerabilityStatus;
    }

    public int getEnrollmentSetting() {
        return enrollmentSetting;
    }

    public void setEnrollmentSetting(int enrollmentSetting) {
        this.enrollmentSetting = enrollmentSetting;
    }

    public String getFacilityOvcOfferTreatmentId() {
        return facilityOvcOfferTreatmentId;
    }

    public void setFacilityOvcOfferTreatmentId(String facilityOvcOfferTreatmentId) {
        this.facilityOvcOfferTreatmentId = facilityOvcOfferTreatmentId;
    }


@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    //cboId=null;
    //level3OuId=null;
    //organizationUnitId=null;
    //hhUniqueId=null;
    ovcId=null;
    hhName=null;
    
    dateOfEnrollment=null;
    firstName=null;
    surname=null;
    address=null;
    sex=null;
    phoneNumber=null;
    baselineHivStatus=0;
    ageAtBaseline=0;
    ageUnitAtBaseline=0;
    mainVulnerabilityStatus=null;
    otherVulnerabilityStatus=null;
    birthCertificate=0;
    schoolStatus=0;
    schoolName=null;
    grade=null;
    caregiverId=null;
    caregiverRelationship=0;
    weight=0.0;
    height=0.0;
    verifiedBy=null;
    enrolledOnTreatment=0;
    sourceOfInfo=0;
    volunteerName=null;
    hivTreatmentFacilityId=null;
    caregiverAge=0;
    caregiverSex=null;
    hiddenBeneficiaryId=null;
    caregiverPhone=null;
    dateOfBaselineHivStatus=null;
    dateEnrolledOnTreatment=null;
    //hhSerialNo=0;
    childSerialNo=0;
    enrolledChildId=null;
    caregiverHivStatus=null;
    dateOfCaregiverHivStatus=null;
    caregiverEnrolledOnTreatment=null;
    dateCaregiverEnrolledOnTreatment=null;
    facilityCaregiverEnrolled=null;
    treatmentId=null;
    enrollmentSetting=0;
    facilityOvcOfferTreatmentId=null;
}

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        //convert date from the mm/dd/yyyy formats to yyyy-mm-dd format
        ValidationManager vm=new ValidationManager();
        String dbDateOfEnrollment=DateManager.processMthDayYearToMysqlFormat(this.getDateOfEnrollment());
        //Date ddbDateOfEnrollment=null;
        //System.err.println("getActionName() is "+getActionName());
        if(getActionName()==null || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("householdDetails") 
        || getActionName().equalsIgnoreCase("childDetails") || getActionName().equalsIgnoreCase("childDetailsBySerialNo") 
        || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("delete") 
        || getActionName().equalsIgnoreCase("adultMemberDetails") || getActionName().equalsIgnoreCase("otherVulnerabilityStatus") || getActionName().equalsIgnoreCase("facilityOvcOfferDetails"))
        return errors;
        else if(dbDateOfEnrollment !=null )
        {
            Beneficiary ovc=ValidationManager.getBeneficiaryByName(getHhUniqueId(),getFirstName(), getSurname(), AppConstant.OVC_TYPE_NUM);
            if(this.getEnrollmentSetting()==AppConstant.ENROLLMENTSETTING_FACILITY_NUM)
            {
                FacilityOvcOffer foo=vm.getFacilityOvcOfferByTreatmentId(this.getFacilityOvcOfferTreatmentId());
                if(foo==null)
                errors.add("enrollmentSetting", new ActionMessage("errors.enrollmentSetting.invalid"));
                else if(DateManager.getDateInstance(dbDateOfEnrollment).before(foo.getDateEnrolledOnTreatment()))
                errors.add("dateOfEnrollment", new ActionMessage("errors.dateOfEnrollmentForOvcOffer.invalid"));
            }
            else if(ovc !=null)
            {
                if(this.getActionName().equalsIgnoreCase("save"))
                errors.add("firstName", new ActionMessage("errors.childName.exists"));
            }
            else
            {
                String formDateOfHivStatus=this.getDateOfBaselineHivStatus();
                if(formDateOfHivStatus==null || formDateOfHivStatus.indexOf("/")==-1)
                formDateOfHivStatus=DateManager.DEFAULT_DATE;
                int currentAge=this.getAgeAtBaseline();
                if(this.getDateOfEnrollment() !=null && this.getDateOfEnrollment().indexOf("/") !=-1)
                currentAge=AppManager.getCurrentAge(DateManager.processMthDayYearToMysqlFormat(this.getDateOfEnrollment()), this.getAgeAtBaseline());
                Date dateOfBirth=DateManager.getPreviousDate(DateManager.getCurrentDateInstance(), currentAge);
                Date dDateOfBaselineHivStatus=null;
                if(formDateOfHivStatus !=null && formDateOfHivStatus.indexOf("/")!=-1)
                dDateOfBaselineHivStatus=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(formDateOfHivStatus));
                //System.err.println("this.getDateOfEnrollment() is "+dbDateOfEnrollment+" and this.getDateOfBirth() is "+dbDateOfBirth);
                if(getCboId()==null || this.getCboId().trim().equalsIgnoreCase("select"))
                errors.add("cboId", new ActionMessage("errors.cboId.required"));
                else if(getOrganizationUnitId()==null || this.getOrganizationUnitId().equalsIgnoreCase("select"))
                errors.add("organizationUnitId", new ActionMessage("errors.organizationUnitId.required"));

                else if(this.getEnrollmentSetting()==0)
                errors.add("enrollmentSetting", new ActionMessage("errors.enrollmentSetting.required"));

                else if(this.getHhUniqueId()==null || this.getHhUniqueId().trim().length()<17)
                errors.add("hhUniqueId", new ActionMessage("errors.hhUniqueId.required"));
                else if(this.getOvcId()==null || this.getOvcId().trim().length()==0)
                errors.add("ovcId", new ActionMessage("errors.ovcId.required"));
                else if(this.getSurname()==null || this.getSurname().trim().length()==0)
                errors.add("surname", new ActionMessage("errors.surname.required"));
                else if(getSurname().trim().length()>25)
                errors.add("surname", new ActionMessage("errors.surname.toolong"));
                else if(this.getFirstName()==null || this.getFirstName().trim().length()==0)
                errors.add("firstName", new ActionMessage("errors.firstName.required"));
                else if(this.getFirstName().trim().length()>25)
                errors.add("firstName", new ActionMessage("errors.firstName.toolong"));

                else if(getDateOfEnrollment()==null ||  getDateOfEnrollment().indexOf("/")==-1)
                errors.add("dateOfEnrollment", new ActionMessage("errors.dateOfEnrollment.required"));
                else if(!ValidationManager.isValidDate(getDateOfEnrollment()))
                errors.add("dateOfEnrollment", new ActionMessage("errors.dateOfEnrollment.required"));
                else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateOfEnrollment()))
                errors.add("dateOfEnrollment", new ActionMessage("errors.dateOfEnrollment.postdated"));

                else if(this.getSex()==null || this.getSex().trim().equalsIgnoreCase("select"))
                errors.add("sex", new ActionMessage("errors.sex.required"));
                else if(getAgeUnitAtBaseline()==0)
                errors.add("ageUnitAtBaseline", new ActionMessage("errors.ageUnitAtBaseline.required"));
                else if(this.getAgeAtBaseline()==0)
                errors.add("ageAtBaseline", new ActionMessage("errors.ageAtBaseline.required"));
                else if(this.getAgeAtBaseline()>11 && this.getAgeUnitAtBaseline()==AppConstant.AGEUNIT_MONTH_NUM)
                errors.add("ageAtBaseline", new ActionMessage("errors.ageUnitAtBaseline.mismatch"));
                /*else if(this.getMainVulnerabilityStatus()==null || this.getMainVulnerabilityStatus().trim().equalsIgnoreCase("select"))
                errors.add("vulnerabilityStatus", new ActionMessage("errors.vulnerabilityStatus.required"));
                */
                 else if(this.getMainVulnerabilityStatus()==null || this.getMainVulnerabilityStatus().trim().equalsIgnoreCase("select"))
                errors.add("mainVulnerabilityStatus", new ActionMessage("errors.mainvulnerabilityStatus.required"));

                else if(this.getBirthCertificate()==0)
                errors.add("birthCertificate", new ActionMessage("errors.birthCertificate.required"));  
                else if(this.getEnrollmentSetting()==2 )
                errors.add("baselineHivStatus", new ActionMessage("errors.baselineHivStatus.required"));

                else if(this.getEnrollmentSetting()==AppConstant.ENROLLMENTSETTING_FACILITY_NUM && getBaselineHivStatus() !=AppConstant.HIV_POSITIVE_NUM)
                errors.add("baselineHivStatus", new ActionMessage("errors.baselineHivStatus.positive.required"));

                else if((getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM || getBaselineHivStatus()==AppConstant.HIV_NEGATIVE_NUM) && (this.getDateOfBaselineHivStatus()==null || this.getDateOfBaselineHivStatus().indexOf("/")==-1))
                errors.add("dateOfBaselineHivStatus", new ActionMessage("errors.dateOfBaselineHivStatus.required"));
                else if(dateOfBirth !=null && dDateOfBaselineHivStatus !=null && dateOfBirth.after(dDateOfBaselineHivStatus))
                errors.add("dateOfBaselineHivStatus", new ActionMessage("errors.dateOfBaselineHivStatus.beforeDateOfBirth"));
                else if(getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                {
                    if(this.getEnrolledOnTreatment()==0)
                    errors.add("enrolledOnTreatment", new ActionMessage("errors.enrolledOnTreatment.required"));
                    else if(getEnrollmentSetting()==AppConstant.ENROLLMENTSETTING_FACILITY_NUM && this.getEnrolledOnTreatment() !=AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
                    errors.add("enrollmentSetting", new ActionMessage("errors.enrollmentSetting.enrolledOnTreatment.required"));
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

                else if(this.getCaregiverId()==null || this.getCaregiverId().trim().equalsIgnoreCase("select") || this.getCaregiverId().trim().length()==0)
                errors.add("caregiverId", new ActionMessage("errors.caregiverId.required"));
                else if(this.getCaregiverRelationship()==0)
                errors.add("caregiverRelationship", new ActionMessage("errors.caregiverRelationship.required"));
                else if(this.getVolunteerName()==null || this.getVolunteerName().trim().equalsIgnoreCase("select"))
                errors.add("volunteerName", new ActionMessage("errors.volunteerName.required"));
                else if(this.getSchoolStatus()==0)
                errors.add("schoolStatus", new ActionMessage("errors.schoolStatus.required"));
                else if(this.getSchoolStatus()==1)
                {
                    if(this.getSchoolName()==null || this.getSchoolName().trim().equalsIgnoreCase("select"))
                    errors.add("schoolName", new ActionMessage("errors.schoolName.required"));
                    else if(this.getGrade()==null || this.getGrade().trim().equalsIgnoreCase("select"))
                    errors.add("grade", new ActionMessage("errors.grade.required"));
                }
            }
            /*else if(this.getSourceOfInfo()==0)
            {
                errors.add("sourceOfInfo", new ActionMessage("errors.sourceOfInfo.required"));
            }*/
        }
        else
        errors.add("validDate", new ActionMessage("errors.validDate.invalid"));
        return errors;
    }
}
