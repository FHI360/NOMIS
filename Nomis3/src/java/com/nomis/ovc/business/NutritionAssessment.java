/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.operationsManagement.NutritionAssessmentAttributesManager;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.SingleOptionManager;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class NutritionAssessment implements Serializable
{
    private int recordId;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private String ovcId;
    private String volunteerName;
    private Date dateOfAssessment;
    private Date dateCreated;
    private Date lastModifiedDate;
    private int assessmentNo;
    private double weight;
    private double height;
    private double lastWeight;
    private int changeInWeight;
    private Date dateOfLastWeight;
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
    private String yellowMuacServices;
    private int markedForDelete;
    private int recordStatus;
    private String recordedBy;
    private int ageAtAssessment;
    private int ageUnitAtAssessment;
    int serialNo=0;
    String rowColor=AppConstant.FIRSTREPORTROWCOLOUR;
    
    private Ovc ovc;
    private String nutritionalStatusAnswer;
    private String foodSecurityAndDietQ1Answer;
    private String foodSecurityAndDietQ2Answer;
    private String foodSecurityAndDietQ3Answer;
    private String foodSecurityAndDietQ4Answer;
    private String foodSecurityAndDietQ5Answer;
    private String foodSecurityAndDietQ6Answer;
    private String foodSecurityAndDietQ7Answer;
    private String foodSecurityAndDietQ8Answer;
    private String foodSecurityAndDietQ9Answer;
    private String hygieneQ1Answer;
    private String hygieneQ2Answer;
    private String hygieneQ3Answer;
    private String hygieneQ4Answer;
    private String ageUnitAtAssessmentName;
    private CommunityWorker communityWorker;

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(Date dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getDateOfLastWeight() {
        return dateOfLastWeight;
    }

    public void setDateOfLastWeight(Date dateOfLastWeight) {
        this.dateOfLastWeight = dateOfLastWeight;
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

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public int getMuac() {
        return muac;
    }

    public void setMuac(int muac) {
        this.muac = muac;
    }

    public String getMuacFacility() {
        return muacFacility;
    }

    public void setMuacFacility(String muacFacility) {
        this.muacFacility = muacFacility;
    }

    public int getNutritionalStatus() {
        return nutritionalStatus;
    }

    public void setNutritionalStatus(int nutritionalStatus) {
        this.nutritionalStatus = nutritionalStatus;
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

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
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

    public String getYellowMuacServices() {
        return yellowMuacServices;
    }

    public void setYellowMuacServices(String yellowMuacServices) {
        this.yellowMuacServices = yellowMuacServices;
    }

    public int getAssessmentNo() {
        return assessmentNo;
    }

    public void setAssessmentNo(int assessmentNo) {
        this.assessmentNo = assessmentNo;
    }

    public int getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(int recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public int getAgeAtAssessment() {
        return ageAtAssessment;
    }

    public void setAgeAtAssessment(int ageAtAssessment) {
        this.ageAtAssessment = ageAtAssessment;
    }

    public int getAgeUnitAtAssessment() {
        return ageUnitAtAssessment;
    }

    public void setAgeUnitAtAssessment(int ageUnitAtAssessment) {
        this.ageUnitAtAssessment = ageUnitAtAssessment;
    }

    public String getRowColor() {
        return rowColor;
    }

    public void setRowColor(String rowColor) {
        this.rowColor = rowColor;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public Ovc getOvc() {
        return ovc;
    }

    public void setOvc(Ovc ovc) {
        this.ovc = ovc;
    }

    public String getAgeUnitAtAssessmentName() {
        return ageUnitAtAssessmentName=SingleOptionManager.getAgeUnit(ageUnitAtAssessment).getName();
    }

    public String getFoodSecurityAndDietQ1Answer() {
        return foodSecurityAndDietQ1Answer=NutritionAssessmentAttributesManager.getAnswerToHouseholdMemberDidNotEatBecauseNoFood(foodSecurityAndDietQ1);
    }

    public String getFoodSecurityAndDietQ2Answer() {
        return foodSecurityAndDietQ2Answer=NutritionAssessmentAttributesManager.getAnswerToHouseholdMemberDidNotEatBecauseNoFood(foodSecurityAndDietQ2);
    }

    public String getFoodSecurityAndDietQ3Answer() {
        return foodSecurityAndDietQ3Answer=NutritionAssessmentAttributesManager.getAnswerToHouseholdMemberDidNotEatBecauseNoFood(foodSecurityAndDietQ3);
    }

    public String getFoodSecurityAndDietQ4Answer() {
        return foodSecurityAndDietQ4Answer=NutritionAssessmentAttributesManager.getAnswerToNumberOfTimesChildAteFood(foodSecurityAndDietQ4);
    }

    public String getFoodSecurityAndDietQ5Answer() {
        return foodSecurityAndDietQ5Answer=SingleOptionManager.getSingleChoiceOption(foodSecurityAndDietQ5).getName();
    }

    public String getFoodSecurityAndDietQ6Answer() {
        return foodSecurityAndDietQ6Answer=SingleOptionManager.getSingleChoiceOption(foodSecurityAndDietQ6).getName();
    }

    public String getFoodSecurityAndDietQ7Answer() {
        return foodSecurityAndDietQ7Answer=SingleOptionManager.getSingleChoiceOption(foodSecurityAndDietQ7).getName();
    }

    public String getFoodSecurityAndDietQ8Answer() {
        return foodSecurityAndDietQ8Answer=SingleOptionManager.getSingleChoiceOption(foodSecurityAndDietQ8).getName();
    }

    public String getFoodSecurityAndDietQ9Answer() {
        return foodSecurityAndDietQ9Answer=SingleOptionManager.getSingleChoiceOption(foodSecurityAndDietQ9).getName();
    }

    public String getHygieneQ1Answer() {
        return hygieneQ1Answer=SingleOptionManager.getSingleChoiceOption(hygieneQ1).getName();
    }

    public String getHygieneQ2Answer() {
        return hygieneQ2Answer=SingleOptionManager.getSingleChoiceOption(hygieneQ2).getName();
    }

    public String getHygieneQ3Answer() {
        return hygieneQ3Answer=SingleOptionManager.getSingleChoiceOption(hygieneQ3).getName();
    }

    public String getHygieneQ4Answer() {
        return hygieneQ4Answer=SingleOptionManager.getSingleChoiceOption(hygieneQ4).getName();
    }

    public String getNutritionalStatusAnswer() {
        return nutritionalStatusAnswer=NutritionAssessmentAttributesManager.getAnswerToNutritionalStatus(nutritionalStatus);
    }
    public CommunityWorker getCommunityWorker() {
        return communityWorker=BeneficiaryManager.getCommunityWorker(volunteerName);
    }
}
