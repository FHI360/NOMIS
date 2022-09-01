/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HP USER
 */
public class ChildStatusIndex implements Serializable {

    public ChildStatusIndex() {

    }

    private int id;
    private int totalCsiScore;
    private String ovcId;
    private Date csiDate;
    private Date lastModifiedDate;
    private int emotionalHealth;
    private int socialBehaviour;
    private int foodSecurity;
    private int nutritionAndGrowth;
    private int wellness;
    private int healthCareServices;
    private int developmentAndPerformance;
    private int educationAndWork;
    private int abuseAndExploitation;
    private int legalProtection;
    private int shelter;
    private int care;
    private int markedForDelete;
    private int assessmentNumber;
    private int totalScore;

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

    public Date getCsiDate() {
        return csiDate;
    }

    public void setCsiDate(Date csiDate) {
        this.csiDate = csiDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setId(int id) {
    this.id = id;
    }

    public int getId() {
    return id;
    }

    public void setOvcId(String ovcId) {
    this.ovcId = ovcId;
    }

    public String getOvcId() {
    return ovcId;
    }

    public int getTotalCsiScore() {
        return totalCsiScore;
    }

    public void setTotalCsiScore(int totalCsiScore) {
        this.totalCsiScore = totalCsiScore;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public int getAssessmentNumber() {
        return assessmentNumber;
    }

    public void setAssessmentNumber(int assessmentNumber) {
        this.assessmentNumber = assessmentNumber;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
    
}
