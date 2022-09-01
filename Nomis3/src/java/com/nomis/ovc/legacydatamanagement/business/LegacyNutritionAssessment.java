/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.legacydatamanagement.business;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class LegacyNutritionAssessment implements Serializable
{
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    private String ovcId;
    private String ovcFname;
    private String ovcSurname;
    private String ovcGender;
    private int ovcAge;
    private String ageUnit;
    private double weight;
    private int id;
    private double lastWeight;
    private double height;
    private String  dateOfAssessment;
    private int monthOfAssessment;
    private int yearOfAssessment;
    private String  dateOfLastWeight;
    private String changeInWeight;
    private int assessmentNo;
    private String oedema;
    private String muac;
    private String bmi;
    private double bodyMassIndex;
    private String nutritionalStatus;
    private String foodSecurityAndDietQ1;
    private String foodSecurityAndDietQ2;
    private String foodSecurityAndDietQ3;
    private String foodSecurityAndDietQ4;
    private String foodSecurityAndDietQ5;
    private String foodSecurityAndDietQ6;
    private String foodSecurityAndDietQ7;
    private String foodSecurityAndDietQ8;
    private String foodSecurityAndDietQ9;
    private String hygieneQ1;
    private String hygieneQ2;
    private String hygieneQ3;
    private String hygieneQ4;
    private String medicalEvaluationHIV;
    private String medicalEvaluationDiarrhea;
    private String medicalEvaluationNausea;
    private String medicalEvaluationPainfulChewing;
    private String medicalEvaluationVomiting;
    private String medicalEvaluationPoorApetite;
    private String medicalEvaluationMouthSoars;
    private String dateModified;
    private String recordedBy;
    private int naRecordStatus;
    private String muacFacility;
    private String yellowMuacServices;
    private int markedForDelete;

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(double bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }
    
    public String getChangeInWeight() {
        return changeInWeight;
    }

    public void setChangeInWeight(String changeInWeight) {
        this.changeInWeight = changeInWeight;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getDateOfLastWeight() {
        return dateOfLastWeight;
    }

    public void setDateOfLastWeight(String dateOfLastWeight) {
        this.dateOfLastWeight = dateOfLastWeight;
    }

    public String getFoodSecurityAndDietQ1() {
        return foodSecurityAndDietQ1;
    }

    public void setFoodSecurityAndDietQ1(String foodSecurityAndDietQ1) {
        this.foodSecurityAndDietQ1 = foodSecurityAndDietQ1;
    }

    public String getFoodSecurityAndDietQ2() {
        return foodSecurityAndDietQ2;
    }

    public void setFoodSecurityAndDietQ2(String foodSecurityAndDietQ2) {
        this.foodSecurityAndDietQ2 = foodSecurityAndDietQ2;
    }

    public String getFoodSecurityAndDietQ3() {
        return foodSecurityAndDietQ3;
    }

    public void setFoodSecurityAndDietQ3(String foodSecurityAndDietQ3) {
        this.foodSecurityAndDietQ3 = foodSecurityAndDietQ3;
    }

    public String getFoodSecurityAndDietQ4() {
        return foodSecurityAndDietQ4;
    }

    public void setFoodSecurityAndDietQ4(String foodSecurityAndDietQ4) {
        this.foodSecurityAndDietQ4 = foodSecurityAndDietQ4;
    }

    public String getFoodSecurityAndDietQ5() {
        return foodSecurityAndDietQ5;
    }

    public void setFoodSecurityAndDietQ5(String foodSecurityAndDietQ5) {
        this.foodSecurityAndDietQ5 = foodSecurityAndDietQ5;
    }

    public String getFoodSecurityAndDietQ6() {
        return foodSecurityAndDietQ6;
    }

    public void setFoodSecurityAndDietQ6(String foodSecurityAndDietQ6) {
        this.foodSecurityAndDietQ6 = foodSecurityAndDietQ6;
    }

    public String getFoodSecurityAndDietQ7() {
        return foodSecurityAndDietQ7;
    }

    public void setFoodSecurityAndDietQ7(String foodSecurityAndDietQ7) {
        this.foodSecurityAndDietQ7 = foodSecurityAndDietQ7;
    }

    public String getFoodSecurityAndDietQ8() {
        return foodSecurityAndDietQ8;
    }

    public void setFoodSecurityAndDietQ8(String foodSecurityAndDietQ8) {
        this.foodSecurityAndDietQ8 = foodSecurityAndDietQ8;
    }

    public String getFoodSecurityAndDietQ9() {
        return foodSecurityAndDietQ9;
    }

    public void setFoodSecurityAndDietQ9(String foodSecurityAndDietQ9) {
        this.foodSecurityAndDietQ9 = foodSecurityAndDietQ9;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHygieneQ1() {
        return hygieneQ1;
    }

    public void setHygieneQ1(String hygieneQ1) {
        this.hygieneQ1 = hygieneQ1;
    }

    public String getHygieneQ2() {
        return hygieneQ2;
    }

    public void setHygieneQ2(String hygieneQ2) {
        this.hygieneQ2 = hygieneQ2;
    }

    public String getHygieneQ3() {
        return hygieneQ3;
    }

    public void setHygieneQ3(String hygieneQ3) {
        this.hygieneQ3 = hygieneQ3;
    }

    public String getHygieneQ4() {
        return hygieneQ4;
    }

    public void setHygieneQ4(String hygieneQ4) {
        this.hygieneQ4 = hygieneQ4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLastWeight() {
        return lastWeight;
    }

    public void setLastWeight(double lastWeight) {
        this.lastWeight = lastWeight;
    }

    public String getMedicalEvaluationDiarrhea() {
        return medicalEvaluationDiarrhea;
    }

    public void setMedicalEvaluationDiarrhea(String medicalEvaluationDiarrhea) {
        this.medicalEvaluationDiarrhea = medicalEvaluationDiarrhea;
    }

    public String getMedicalEvaluationHIV() {
        return medicalEvaluationHIV;
    }

    public void setMedicalEvaluationHIV(String medicalEvaluationHIV) {
        this.medicalEvaluationHIV = medicalEvaluationHIV;
    }

    public String getMedicalEvaluationMouthSoars() {
        return medicalEvaluationMouthSoars;
    }

    public void setMedicalEvaluationMouthSoars(String medicalEvaluationMouthSoars) {
        this.medicalEvaluationMouthSoars = medicalEvaluationMouthSoars;
    }

    public String getMedicalEvaluationNausea() {
        return medicalEvaluationNausea;
    }

    public void setMedicalEvaluationNausea(String medicalEvaluationNausea) {
        this.medicalEvaluationNausea = medicalEvaluationNausea;
    }

    public String getMedicalEvaluationPainfulChewing() {
        return medicalEvaluationPainfulChewing;
    }

    public void setMedicalEvaluationPainfulChewing(String medicalEvaluationPainfulChewing) {
        this.medicalEvaluationPainfulChewing = medicalEvaluationPainfulChewing;
    }

    public String getMedicalEvaluationPoorApetite() {
        return medicalEvaluationPoorApetite;
    }

    public void setMedicalEvaluationPoorApetite(String medicalEvaluationPoorApetite) {
        this.medicalEvaluationPoorApetite = medicalEvaluationPoorApetite;
    }

    public String getMedicalEvaluationVomiting() {
        return medicalEvaluationVomiting;
    }

    public void setMedicalEvaluationVomiting(String medicalEvaluationVomiting) {
        this.medicalEvaluationVomiting = medicalEvaluationVomiting;
    }

    public String getMuac() {
        return muac;
    }

    public void setMuac(String muac) {
        this.muac = muac;
    }

    public String getNutritionalStatus() {
        return nutritionalStatus;
    }

    public void setNutritionalStatus(String nutritionalStatus) {
        this.nutritionalStatus = nutritionalStatus;
    }

    public String getOedema() {
        return oedema;
    }

    public void setOedema(String oedema) {
        this.oedema = oedema;
    }

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public int getOvcAge() {
        return ovcAge;
    }

    public void setOvcAge(int ovcAge) {
        this.ovcAge = ovcAge;
    }

    public String getOvcFname() {
        return ovcFname;
    }

    public void setOvcFname(String ovcFname) {
        this.ovcFname = ovcFname;
    }

    public String getOvcGender() {
        return ovcGender;
    }

    public void setOvcGender(String ovcGender) {
        this.ovcGender = ovcGender;
    }

    public String getOvcSurname() {
        return ovcSurname;
    }

    public void setOvcSurname(String ovcSurname) {
        this.ovcSurname = ovcSurname;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAssessmentNo() {
        return assessmentNo;
    }

    public void setAssessmentNo(int assessmentNo) {
        this.assessmentNo = assessmentNo;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public int getNaRecordStatus() {
        return naRecordStatus;
    }

    public void setNaRecordStatus(int naRecordStatus) {
        this.naRecordStatus = naRecordStatus;
    }

    public int getMonthOfAssessment() 
    {
        if(dateOfAssessment !=null)
        {
            //monthOfAssessment=beneficiary.getMonthFromDate(dateOfAssessment);;
        }
        return monthOfAssessment;
    }

    public void setMonthOfAssessment(int monthOfAssessment) {
        this.monthOfAssessment = monthOfAssessment;
    }

    public int getYearOfAssessment() 
    {
        if(dateOfAssessment !=null)
        {
            //yearOfAssessment=beneficiary.getYearFromDate(dateOfAssessment);;
        }
        return yearOfAssessment;
    }

    public void setYearOfAssessment(int yearOfAssessment) {
        this.yearOfAssessment = yearOfAssessment;
    }

    public String getMuacFacility() {
        return muacFacility;
    }

    public void setMuacFacility(String muacFacility) {
        this.muacFacility = muacFacility;
    }

    public String getYellowMuacServices() {
        return yellowMuacServices;
    }

    public void setYellowMuacServices(String yellowMuacServices) {
        this.yellowMuacServices = yellowMuacServices;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

}
