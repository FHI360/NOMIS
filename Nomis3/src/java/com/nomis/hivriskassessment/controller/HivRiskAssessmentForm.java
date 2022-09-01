/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.hivriskassessment.controller;

import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.util.AppConstant;
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
public class HivRiskAssessmentForm extends org.apache.struts.action.ActionForm {
    
    private int recordId;
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int hivParentQuestion;
    private int motherSicknessQuestion;
    private int hivSibblingQuestion;
    private int sibblingSicknessQuestion;
    private int childSickQuestion;
    private int childHasMoreThanTwoIllnessQuestion;
    private int bloodTransfusionQuestion;
    private int childCircumcisedOrEarPierced;
    private int childEverPregnantQuestion;
    private int sexualAbuseQuestion;
    private int childAtRiskQuestion;
    private int childReferredForHIVTest;
    private String dateOfAssessment;
    private int sexualActivityQuestion;
    private int genitalDischargeQuestion=0;
    private int drugInjectionQuestion;
       
    private int childAgeQuestion;
    private int childTestedQuestion;
    private int hivStatusQuestion;
    private int currentHivStatus;
    private int baselineHivStatus;
    private int hivStatusAtRiskAssessment;
            
    private int age;
    private String ageUnit;
    private int hhSerialNo; 
    private String hhUniqueId;
    private String ovcId;
    private String surname;
    private String firstname;
    private String address;
    private String occupation;
    private String sex;
    private String phoneNumber;
    private String dateOfEnrollment;
    private String volunteerName;
    private int ageAtAssessment=0;
    private int currentAge=0;
    /**
     *
     */
    public HivRiskAssessmentForm() {
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

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public int getBloodTransfusionQuestion() {
        return bloodTransfusionQuestion;
    }

    public void setBloodTransfusionQuestion(int bloodTransfusionQuestion) {
        this.bloodTransfusionQuestion = bloodTransfusionQuestion;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public int getChildAgeQuestion() {
        return childAgeQuestion;
    }

    public void setChildAgeQuestion(int childAgeQuestion) {
        this.childAgeQuestion = childAgeQuestion;
    }

    public int getChildAtRiskQuestion() {
        return childAtRiskQuestion;
    }

    public void setChildAtRiskQuestion(int childAtRiskQuestion) {
        this.childAtRiskQuestion = childAtRiskQuestion;
    }

    public int getChildCircumcisedOrEarPierced() {
        return childCircumcisedOrEarPierced;
    }

    public void setChildCircumcisedOrEarPierced(int childCircumcisedOrEarPierced) {
        this.childCircumcisedOrEarPierced = childCircumcisedOrEarPierced;
    }

    public int getChildEverPregnantQuestion() {
        return childEverPregnantQuestion;
    }

    public void setChildEverPregnantQuestion(int childEverPregnantQuestion) {
        this.childEverPregnantQuestion = childEverPregnantQuestion;
    }

    public int getChildHasMoreThanTwoIllnessQuestion() {
        return childHasMoreThanTwoIllnessQuestion;
    }

    public void setChildHasMoreThanTwoIllnessQuestion(int childHasMoreThanTwoIllnessQuestion) {
        this.childHasMoreThanTwoIllnessQuestion = childHasMoreThanTwoIllnessQuestion;
    }

    public int getChildSickQuestion() {
        return childSickQuestion;
    }

    public void setChildSickQuestion(int childSickQuestion) {
        this.childSickQuestion = childSickQuestion;
    }

    public int getChildTestedQuestion() {
        return childTestedQuestion;
    }

    public void setChildTestedQuestion(int childTestedQuestion) {
        this.childTestedQuestion = childTestedQuestion;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public int getHivParentQuestion() {
        return hivParentQuestion;
    }

    public void setHivParentQuestion(int hivParentQuestion) {
        this.hivParentQuestion = hivParentQuestion;
    }

    public int getHivSibblingQuestion() {
        return hivSibblingQuestion;
    }

    public void setHivSibblingQuestion(int hivSibblingQuestion) {
        this.hivSibblingQuestion = hivSibblingQuestion;
    }

    public int getCurrentHivStatus() {
        return currentHivStatus;
    }

    public void setCurrentHivStatus(int currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
    }

    public int getHivStatusQuestion() {
        return hivStatusQuestion;
    }

    public void setHivStatusQuestion(int hivStatusQuestion) {
        this.hivStatusQuestion = hivStatusQuestion;
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

    public int getMotherSicknessQuestion() {
        return motherSicknessQuestion;
    }

    public void setMotherSicknessQuestion(int motherSicknessQuestion) {
        this.motherSicknessQuestion = motherSicknessQuestion;
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

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getSexualAbuseQuestion() {
        return sexualAbuseQuestion;
    }

    public void setSexualAbuseQuestion(int sexualAbuseQuestion) {
        this.sexualAbuseQuestion = sexualAbuseQuestion;
    }

    public int getSibblingSicknessQuestion() {
        return sibblingSicknessQuestion;
    }

    public void setSibblingSicknessQuestion(int sibblingSicknessQuestion) {
        this.sibblingSicknessQuestion = sibblingSicknessQuestion;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getHivStatusAtRiskAssessment() {
        return hivStatusAtRiskAssessment;
    }

    public void setHivStatusAtRiskAssessment(int hivStatusAtRiskAssessment) {
        this.hivStatusAtRiskAssessment = hivStatusAtRiskAssessment;
    }

    public int getBaselineHivStatus() {
        return baselineHivStatus;
    }

    public void setBaselineHivStatus(int baselineHivStatus) {
        this.baselineHivStatus = baselineHivStatus;
    }

    public int getDrugInjectionQuestion() {
        return drugInjectionQuestion;
    }

    public void setDrugInjectionQuestion(int drugInjectionQuestion) {
        this.drugInjectionQuestion = drugInjectionQuestion;
    }

    public int getGenitalDischargeQuestion() {
        return genitalDischargeQuestion;
    }

    public void setGenitalDischargeQuestion(int genitalDischargeQuestion) {
        this.genitalDischargeQuestion = genitalDischargeQuestion;
    }

    public int getSexualActivityQuestion() {
        return sexualActivityQuestion;
    }

    public void setSexualActivityQuestion(int sexualActivityQuestion) {
        this.sexualActivityQuestion = sexualActivityQuestion;
    }

    public int getAgeAtAssessment() {
        return ageAtAssessment;
    }

    public void setAgeAtAssessment(int ageAtAssessment) {
        this.ageAtAssessment = ageAtAssessment;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public int getChildReferredForHIVTest() {
        return childReferredForHIVTest;
    }

    public void setChildReferredForHIVTest(int childReferredForHIVTest) {
        this.childReferredForHIVTest = childReferredForHIVTest;
    }

   
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    age=0;
    ageUnit=null;
    //hhSerialNo=0;    
    //hhUniqueId=null;
    ovcId=null;
    surname=null;
    firstname=null;
    sex=null;
    phoneNumber=null;
    address=null;
    dateOfEnrollment=null;
    occupation=null;
    dateOfAssessment=null;
    childTestedQuestion=0;
    hivParentQuestion=0;
    childSickQuestion=0;
    sexualAbuseQuestion=0;
    bloodTransfusionQuestion=0;
    hivStatusQuestion=0;
    baselineHivStatus=0;
    currentHivStatus=0;
    motherSicknessQuestion=0;
    hivSibblingQuestion=0;
    sibblingSicknessQuestion=0;
    childSickQuestion=0;
    childHasMoreThanTwoIllnessQuestion=0;
    bloodTransfusionQuestion=0;
    childCircumcisedOrEarPierced=0;
    childEverPregnantQuestion=0;
    childAtRiskQuestion=0;
    volunteerName=null;
    hivStatusAtRiskAssessment=0;
    sexualActivityQuestion=0;
    drugInjectionQuestion=0;
    genitalDischargeQuestion=0;
    ageAtAssessment=0;
    currentAge=0;
    childReferredForHIVTest=0;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        ValidationManager vm=new ValidationManager();
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("householdDetails") || getActionName().equalsIgnoreCase("childDetails") || getActionName().equalsIgnoreCase("assessmentDetails") || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        if(getActionName().equalsIgnoreCase("save") || getActionName().equalsIgnoreCase("modify"))
        {
            AgeObject ageObj=vm.getAgeObject(this.getOvcId(), this.getDateOfAssessment(), AppConstant.OVC_TYPE_NUM);
            if(this.getOvcId()==null || this.getOvcId().trim().equalsIgnoreCase("select"))
            errors.add("ovcId", new ActionMessage("errors.ovcId.required"));
            else if(this.getDateOfAssessment()==null || this.getDateOfAssessment().indexOf("/")==-1)
            errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
            else if(!ValidationManager.isValidDate(getDateOfAssessment()))
            errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.required"));
            else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateOfAssessment()))
            errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.postdated"));
            else if(!ValidationManager.dateAfterEnrollmentDate(this.getOvcId(),getDateOfAssessment(),AppConstant.OVC_TYPE_NUM))
            errors.add("dateOfAssessment", new ActionMessage("errors.dateOfAssessment.beforeEnrollment"));
            if(this.getHivStatusAtRiskAssessment()==AppConstant.HIV_UNKNOWN_NUM || getHivStatusAtRiskAssessment()==AppConstant.HIV_UNDISCLOSED_NUM)
            {
                Beneficiary ovc=ValidationManager.getBeneficiary(this.getOvcId(),AppConstant.OVC_TYPE_NUM);
                if(ovc !=null)
                {
                    if(ovc.getBaselineHivStatus() ==AppConstant.HIV_NEGATIVE_NUM || ovc.getBaselineHivStatus() ==AppConstant.HIV_POSITIVE_NUM)
                    errors.add("hivStatusAtRiskAssessment", new ActionMessage("error.hivstatus.knownAtRiskAssessment"));
                    else if((ovc.getCurrentHivStatus() ==AppConstant.HIV_NEGATIVE_NUM || ovc.getCurrentHivStatus() ==AppConstant.HIV_POSITIVE_NUM) && ovc.getDateOfCurrentHivStatus().before(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateOfAssessment()))))
                    errors.add("hivStatusAtRiskAssessment", new ActionMessage("error.hivstatus.knownAtRiskAssessment"));
                }
            }
            
            
            else if(this.getChildTestedQuestion()==0)
            errors.add("childTestedQuestion", new ActionMessage("errors.childTestedQuestion.required"));
            else if(this.getHivParentQuestion()==0)
            errors.add("hivParentQuestion", new ActionMessage("errors.hivParentQuestion.required"));
            else if(this.getMotherSicknessQuestion()==0)
            errors.add("motherSicknessQuestion", new ActionMessage("errors.motherSicknessQuestion.required"));
            else if(this.getBloodTransfusionQuestion()==0)
            errors.add("bloodTransfusionQuestion", new ActionMessage("errors.bloodTransfusionQuestion.required"));
            else if(this.getChildAtRiskQuestion()==0)
            errors.add("childAtRiskQuestion", new ActionMessage("errors.childAtRiskQuestion.required"));
            else if(getChildAtRiskQuestion()!=1 && this.getChildReferredForHIVTest()>0 )
            this.setChildReferredForHIVTest(0);
            else if(this.getChildAtRiskQuestion()==0 && this.getChildReferredForHIVTest()>0 )
            errors.add("childAtRiskQuestion", new ActionMessage("errors.childAtRiskQuestion.required"));//private int childReferredForHIVTest;
            else if(this.getChildCircumcisedOrEarPierced()==0)
            errors.add("childCircumcisedOrEarPierced", new ActionMessage("errors.childCircumcisedOrEarPierced.required"));
            else if(this.getChildEverPregnantQuestion()==0)
            errors.add("childEverPregnantQuestion", new ActionMessage("errors.childEverPregnantQuestion.required"));
            else if(this.getChildHasMoreThanTwoIllnessQuestion()==0)
            errors.add("childHasMoreThanTwoIllnessQuestion", new ActionMessage("errors.childHasMoreThanTwoIllnessQuestion.required"));
            else if(this.getChildSickQuestion()==0)
            errors.add("childSickQuestion", new ActionMessage("errors.childSickQuestion.required"));

            else if(this.getHivSibblingQuestion()==0)
            errors.add("hivSibblingQuestion", new ActionMessage("errors.hivSibblingQuestion.required"));

            else if(this.getHivStatusAtRiskAssessment()==0)
            errors.add("hivStatus", new ActionMessage("errors.hivStatus.required"));
            else if(this.getHivStatusQuestion()==0)
            errors.add("hivStatusQuestion", new ActionMessage("errors.hivStatusQuestion.required"));

            else if(this.getSexualAbuseQuestion()==0)
            errors.add("sexualAbuseQuestion", new ActionMessage("errors.sexualAbuseQuestion.required"));
            else if(this.getSibblingSicknessQuestion()==0)
            errors.add("sibblingSicknessQuestion", new ActionMessage("errors.sibblingSicknessQuestion.required"));
            else if(ageObj.getAge()<15 && (this.getDrugInjectionQuestion()>0 || this.getGenitalDischargeQuestion()>0 || this.getSexualActivityQuestion()>0))
            errors.add("drugInjectionQuestion", new ActionMessage("errors.drugInjectionQuestion.notrequired"));
            else if(ageObj.getAge()>14 && ageObj.getAgeUnit()==AppConstant.AGEUNIT_YEAR_NUM)
            {
                if(getSexualActivityQuestion()==0)
                errors.add("sexualActivityQuestion", new ActionMessage("errors.sexualActivityQuestion.required"));
                else if(getGenitalDischargeQuestion()==0)
                errors.add("genitalDischargeQuestion", new ActionMessage("errors.genitalDischargeQuestion.required"));
                else if(this.getDrugInjectionQuestion()==0)
                errors.add("drugInjectionQuestion", new ActionMessage("errors.drugInjectionQuestion.required"));
                
                
            }
            else if(this.getVolunteerName()==null || getVolunteerName().trim().equalsIgnoreCase("select"))
            errors.add("volunteerName", new ActionMessage("errors.volunteerName.required"));
        }
        
        return errors;
    }
}
