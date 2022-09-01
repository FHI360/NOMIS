/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.business;

import java.io.Serializable;

/**
 *
 * @author Siaka
 */
public class LegacyHouseholdReferral implements Serializable
{
    private int recordId;
    private String hhUniqueId;
    private String referringOrganization;
    private String dateOfReferral;
    private String psychosocialServices;
    private String nutritionalServices;
    private String healthServices;
    private String educationalServices;
    private String protectionServices;
    private String shelterServices;
    private String economicServices;
    private String nameOfPersonReferring;
    private String designationOfReferrer;
    private String referrerPhoneNo;
    private String referrerEmail;
    private String nameOfOrganizationChildIsReferred;
    private String referralCompleted;
    private String type;
    private String surname;
    private String firstName;
    private String gender;
    private int    age;
    private int markedForDelete;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getReferringOrganization() {
        return referringOrganization;
    }

    public void setReferringOrganization(String referringOrganization) {
        this.referringOrganization = referringOrganization;
    }

    public String getDateOfReferral() {
        return dateOfReferral;
    }

    public void setDateOfReferral(String dateOfReferral) {
        this.dateOfReferral = dateOfReferral;
    }

    public String getPsychosocialServices() {
        return psychosocialServices;
    }

    public void setPsychosocialServices(String psychosocialServices) {
        this.psychosocialServices = psychosocialServices;
    }

    public String getNutritionalServices() {
        return nutritionalServices;
    }

    public void setNutritionalServices(String nutritionalServices) {
        this.nutritionalServices = nutritionalServices;
    }

    public String getHealthServices() {
        return healthServices;
    }

    public void setHealthServices(String healthServices) {
        this.healthServices = healthServices;
    }

    public String getEducationalServices() {
        return educationalServices;
    }

    public void setEducationalServices(String educationalServices) {
        this.educationalServices = educationalServices;
    }

    public String getProtectionServices() {
        return protectionServices;
    }

    public void setProtectionServices(String protectionServices) {
        this.protectionServices = protectionServices;
    }

    public String getShelterServices() {
        return shelterServices;
    }

    public void setShelterServices(String shelterServices) {
        this.shelterServices = shelterServices;
    }

    public String getEconomicServices() {
        return economicServices;
    }

    public void setEconomicServices(String economicServices) {
        this.economicServices = economicServices;
    }

    public String getNameOfPersonReferring() {
        return nameOfPersonReferring;
    }

    public void setNameOfPersonReferring(String nameOfPersonReferring) {
        this.nameOfPersonReferring = nameOfPersonReferring;
    }

    public String getDesignationOfReferrer() {
        return designationOfReferrer;
    }

    public void setDesignationOfReferrer(String designationOfReferrer) {
        this.designationOfReferrer = designationOfReferrer;
    }

    public String getReferrerPhoneNo() {
        return referrerPhoneNo;
    }

    public void setReferrerPhoneNo(String referrerPhoneNo) {
        this.referrerPhoneNo = referrerPhoneNo;
    }

    public String getReferrerEmail() {
        return referrerEmail;
    }

    public void setReferrerEmail(String referrerEmail) {
        this.referrerEmail = referrerEmail;
    }

    public String getNameOfOrganizationChildIsReferred() {
        return nameOfOrganizationChildIsReferred;
    }

    public void setNameOfOrganizationChildIsReferred(String nameOfOrganizationChildIsReferred) {
        this.nameOfOrganizationChildIsReferred = nameOfOrganizationChildIsReferred;
    }

    public String getReferralCompleted() {
        return referralCompleted;
    }

    public void setReferralCompleted(String referralCompleted) {
        this.referralCompleted = referralCompleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
    
}
