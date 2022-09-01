/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.nutritionalassessment.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class NutritionalAssessmentForm extends org.apache.struts.action.ActionForm {
    
    private int recordId;
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private int hhSerialNo; 
    private String hhUniqueId;
    private String ovcId;
    private String surname;
    private String firstname;
    private String address;
    private String occupation;
    private String sex;
    private int hivStatus;
    private String phoneNumber;
    private String dateOfEnrollment;
    private String volunteerName;
    private String dateOfAssessment;
    private double weight;
    private double height;
    private double lastWeight;
    private int changeInWeight;
    private String dateOfLastWeight;
    private int oedema;
    private int muac;
    private double bmi;
    private int nutritionalStatus;
    private int foodSecurityAndDietQ1;
    private int foodSecurityAndDietQ2;
    private int foodSecurityAndDietQ3;
    private int foodSecurityAndDietQ4;
    private int foodSecurityAndDietQ5;
    private int foodSecurityAndDietQ6;
    private int foodSecurityAndDietQ7;
    private int foodSecurityAndDietQ8;
    private int foodSecurityAndDietQ9;
    private int hygieneQ1;
    private int hygieneQ2;
    private int hygieneQ3;
    private int hygieneQ4;
    private String muacFacility;
    private String[] yellowMuacServices;
    private List referralDirectoryList=new ArrayList();

    public NutritionalAssessmentForm() {
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

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public int getChangeInWeight() {
        return changeInWeight;
    }

    public void setChangeInWeight(int changeInWeight) {
        this.changeInWeight = changeInWeight;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getDateOfLastWeight() {
        return dateOfLastWeight;
    }

    public void setDateOfLastWeight(String dateOfLastWeight) {
        this.dateOfLastWeight = dateOfLastWeight;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getFoodSecurityAndDietQ1() {
        return foodSecurityAndDietQ1;
    }

    public void setFoodSecurityAndDietQ1(int foodSecurityAndDietQ1) {
        this.foodSecurityAndDietQ1 = foodSecurityAndDietQ1;
    }

    public int getFoodSecurityAndDietQ2() {
        return foodSecurityAndDietQ2;
    }

    public void setFoodSecurityAndDietQ2(int foodSecurityAndDietQ2) {
        this.foodSecurityAndDietQ2 = foodSecurityAndDietQ2;
    }

    public int getFoodSecurityAndDietQ3() {
        return foodSecurityAndDietQ3;
    }

    public void setFoodSecurityAndDietQ3(int foodSecurityAndDietQ3) {
        this.foodSecurityAndDietQ3 = foodSecurityAndDietQ3;
    }

    public int getFoodSecurityAndDietQ4() {
        return foodSecurityAndDietQ4;
    }

    public void setFoodSecurityAndDietQ4(int foodSecurityAndDietQ4) {
        this.foodSecurityAndDietQ4 = foodSecurityAndDietQ4;
    }

    public int getFoodSecurityAndDietQ5() {
        return foodSecurityAndDietQ5;
    }

    public void setFoodSecurityAndDietQ5(int foodSecurityAndDietQ5) {
        this.foodSecurityAndDietQ5 = foodSecurityAndDietQ5;
    }

    public int getFoodSecurityAndDietQ6() {
        return foodSecurityAndDietQ6;
    }

    public void setFoodSecurityAndDietQ6(int foodSecurityAndDietQ6) {
        this.foodSecurityAndDietQ6 = foodSecurityAndDietQ6;
    }

    public int getFoodSecurityAndDietQ7() {
        return foodSecurityAndDietQ7;
    }

    public void setFoodSecurityAndDietQ7(int foodSecurityAndDietQ7) {
        this.foodSecurityAndDietQ7 = foodSecurityAndDietQ7;
    }

    public int getFoodSecurityAndDietQ8() {
        return foodSecurityAndDietQ8;
    }

    public void setFoodSecurityAndDietQ8(int foodSecurityAndDietQ8) {
        this.foodSecurityAndDietQ8 = foodSecurityAndDietQ8;
    }

    public int getFoodSecurityAndDietQ9() {
        return foodSecurityAndDietQ9;
    }

    public void setFoodSecurityAndDietQ9(int foodSecurityAndDietQ9) {
        this.foodSecurityAndDietQ9 = foodSecurityAndDietQ9;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public int getHygieneQ1() {
        return hygieneQ1;
    }

    public void setHygieneQ1(int hygieneQ1) {
        this.hygieneQ1 = hygieneQ1;
    }

    public int getHygieneQ2() {
        return hygieneQ2;
    }

    public void setHygieneQ2(int hygieneQ2) {
        this.hygieneQ2 = hygieneQ2;
    }

    public int getHygieneQ3() {
        return hygieneQ3;
    }

    public void setHygieneQ3(int hygieneQ3) {
        this.hygieneQ3 = hygieneQ3;
    }

    public int getHygieneQ4() {
        return hygieneQ4;
    }

    public void setHygieneQ4(int hygieneQ4) {
        this.hygieneQ4 = hygieneQ4;
    }

    public double getLastWeight() {
        return lastWeight;
    }

    public void setLastWeight(double lastWeight) {
        this.lastWeight = lastWeight;
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

    public int getMuac() {
        return muac;
    }

    public void setMuac(int muac) {
        this.muac = muac;
    }

    public int getNutritionalStatus() {
        return nutritionalStatus;
    }

    public void setNutritionalStatus(int nutritionalStatus) {
        this.nutritionalStatus = nutritionalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getOedema() {
        return oedema;
    }

    public void setOedema(int oedema) {
        this.oedema = oedema;
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

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
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

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getMuacFacility() {
        return muacFacility;
    }

    public void setMuacFacility(String muacFacility) {
        this.muacFacility = muacFacility;
    }

    public String[] getYellowMuacServices() {
        return yellowMuacServices;
    }

    public void setYellowMuacServices(String[] yellowMuacServices) {
        this.yellowMuacServices = yellowMuacServices;
    }

    public List getReferralDirectoryList() {
        return referralDirectoryList;
    }

    public void setReferralDirectoryList(List referralDirectoryList) {
        this.referralDirectoryList = referralDirectoryList;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    hhUniqueId=null;
    ovcId=null;
    surname=null;
    firstname=null;
    address=null;
    occupation=null;
    sex=null;
    phoneNumber=null;
    dateOfEnrollment=null;
    volunteerName=null;
    dateOfAssessment=null;
    dateOfLastWeight=null;
    muacFacility=null;
    yellowMuacServices=null;
    hhSerialNo=0; 
    weight=0.0;
    height=0.0;
    lastWeight=0.0;
    changeInWeight=0;
    hivStatus=0;
    oedema=0;
    muac=0;
    bmi=0.0;
    nutritionalStatus=0;
    foodSecurityAndDietQ1=0;
    foodSecurityAndDietQ2=0;
    foodSecurityAndDietQ3=0;
    foodSecurityAndDietQ4=0;
    foodSecurityAndDietQ5=0;
    foodSecurityAndDietQ6=0;
    foodSecurityAndDietQ7=0;
    foodSecurityAndDietQ8=0;
    foodSecurityAndDietQ9=0;
    hygieneQ1=0;
    hygieneQ2=0;
    hygieneQ3=0;
    hygieneQ4=0;
    referralDirectoryList=new ArrayList();
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        return errors;
    }
}
