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
public class LegacyOvcReferral implements Serializable{

private String referringOrganization;
private String dateOfReferral;
private String ovcId;
private int    recordId;
private String surname;
private String firstName;
private String gender;
private int    age;
private String psychoServices;
private String nutritionalServices;
private String healthServices;
private String educationalServices;
private String protectionServices;
private String shelterServices;
private String economicServices;
private String psychosocialOther;
private String nutritionOther;
private String healthOther;
private String educationOther;
private String protectionOther;
private String shelterOther;
private String economicOther;
private String nameOfOrganizationChildIsreferred;
private String referralCompleted;
private String nameOfPersonReferring;
private String designationOfReferrer;
private String referrerEmail;
private String referrerPhoneNo;
private String type;
private int markedForDelete;

    public String getDateOfReferral() {
        return dateOfReferral;
    }

    public void setDateOfReferral(String dateOfReferral) {
        this.dateOfReferral = dateOfReferral;
    }

    public String getDesignationOfReferrer() {
        return designationOfReferrer;
    }

    public void setDesignationOfReferrer(String designationOfReferrer) {
        this.designationOfReferrer = designationOfReferrer;
    }

    public String getEconomicOther() {
        return economicOther;
    }

    public void setEconomicOther(String economicOther) {
        this.economicOther = economicOther;
    }

    public String getEconomicServices() {
        return economicServices;
    }

    public void setEconomicServices(String economicServices) {
        this.economicServices = economicServices;
    }

    public String getEducationOther() {
        return educationOther;
    }

    public void setEducationOther(String educationOther) {
        this.educationOther = educationOther;
    }

    public String getEducationalServices() {
        return educationalServices;
    }

    public void setEducationalServices(String educationalServices) {
        this.educationalServices = educationalServices;
    }

    public String getHealthOther() {
        return healthOther;
    }

    public void setHealthOther(String healthOther) {
        this.healthOther = healthOther;
    }

    public String getHealthServices() {
        return healthServices;
    }

    public void setHealthServices(String healthServices) {
        this.healthServices = healthServices;
    }

    public String getNameOfPersonReferring() {
        return nameOfPersonReferring;
    }

    public void setNameOfPersonReferring(String nameOfPersonReferring) {
        this.nameOfPersonReferring = nameOfPersonReferring;
    }

    public String getNutritionOther() {
        return nutritionOther;
    }

    public void setNutritionOther(String nutritionOther) {
        this.nutritionOther = nutritionOther;
    }

    public String getNutritionalServices() {
        return nutritionalServices;
    }

    public void setNutritionalServices(String nutritionalServices) {
        this.nutritionalServices = nutritionalServices;
    }

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getNameOfOrganizationChildIsreferred() {
        return nameOfOrganizationChildIsreferred;
    }

    public void setNameOfOrganizationChildIsreferred(String nameOfOrganizationChildIsreferred) {
        this.nameOfOrganizationChildIsreferred = nameOfOrganizationChildIsreferred;
    }

    public String getReferralCompleted() {
        return referralCompleted;
    }

    public void setReferralCompleted(String referralCompleted) {
        this.referralCompleted = referralCompleted;
    }

    

    public String getProtectionOther() {
        return protectionOther;
    }

    public void setProtectionOther(String protectionOther) {
        this.protectionOther = protectionOther;
    }

    public String getProtectionServices() {
        return protectionServices;
    }

    public void setProtectionServices(String protectionServices) {
        this.protectionServices = protectionServices;
    }

    public String getPsychoServices() {
        return psychoServices;
    }

    public void setPsychoServices(String psychoServices) {
        this.psychoServices = psychoServices;
    }

    public String getPsychosocialOther() {
        return psychosocialOther;
    }

    public void setPsychosocialOther(String psychosocialOther) {
        this.psychosocialOther = psychosocialOther;
    }

    public String getReferrerEmail() {
        return referrerEmail;
    }

    public void setReferrerEmail(String referrerEmail) {
        this.referrerEmail = referrerEmail;
    }

    public String getReferrerPhoneNo() {
        return referrerPhoneNo;
    }

    public void setReferrerPhoneNo(String referrerPhoneNo) {
        this.referrerPhoneNo = referrerPhoneNo;
    }

    public String getReferringOrganization() {
        return referringOrganization;
    }

    public void setReferringOrganization(String referringOrganization) {
        this.referringOrganization = referringOrganization;
    }

    public String getShelterOther() {
        return shelterOther;
    }

    public void setShelterOther(String shelterOther) {
        this.shelterOther = shelterOther;
    }

    public String getShelterServices() {
        return shelterServices;
    }

    public void setShelterServices(String shelterServices) {
        this.shelterServices = shelterServices;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
    
}
