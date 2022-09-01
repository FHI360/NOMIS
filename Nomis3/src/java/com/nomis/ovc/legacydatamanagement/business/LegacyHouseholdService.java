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
public class LegacyHouseholdService implements Serializable
{
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    private LegacyHouseholdEnrollment householdHead;
    private LegacyCaregiver cgiver;
    /*private String caregiverCommunity;
    private String caregiverHivStatus;
     private int caregiverAge;
    private String caregiverGender;
    private String caregiverFirstname;
    private String caregiverSurname;
     */
    private String hhUniqueId;
    private String caregiverId;
    private String serviceRecipientType;
    
    private String serviceDate;
    private String economicStrengtheningServices;
    private String educationalServices;
    private String healthServices;
    private int id;
    private String nutritionalServices;
    private String orgCode;  
    private String protectionServices;
    private String psychosocialSupportServices;
    private int serviceNo;
    private int numberOfServices;
    private String shelterAndCareServices;
    private String volunteerDesignation;
    private String volunteerName;
    private String recordedBy;
    private String dateOfEntry;
    private String currentHivStatus;
    private String pointOfService="householdservice";
    private String enrolledInCare="No";
    private String enrolledOnART="No";
    private String facilityId;
    private int markedForDelete;
    
    private String psychosocialServicesByName;
    private String healthServicesByName;
    private String nutritionalServicesByName;
    private String educationalServicesByName;
    private String protectionServicesByName;
    private String shelterAndCareServicesByName;
    private String economicStrenghteningServicesByName;
    boolean removeStartAndEndWildCharacters=true;

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public String getServiceRecipientType() {
        return serviceRecipientType;
    }

    public void setServiceRecipientType(String serviceRecipientType) {
        this.serviceRecipientType = serviceRecipientType;
    }

    
    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getEconomicStrengtheningServices() {
        return economicStrengtheningServices;
    }

    public void setEconomicStrengtheningServices(String economicStrengtheningServices) {
        this.economicStrengtheningServices = economicStrengtheningServices;
    }

    public String getEducationalServices() {
        return educationalServices;
    }

    public void setEducationalServices(String educationalServices) {
        this.educationalServices = educationalServices;
    }

    public String getHealthServices() {
        return healthServices;
    }

    public void setHealthServices(String healthServices) {
        this.healthServices = healthServices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNutritionalServices() {
        return nutritionalServices;
    }

    public void setNutritionalServices(String nutritionalServices) {
        this.nutritionalServices = nutritionalServices;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getProtectionServices() {
        return protectionServices;
    }

    public void setProtectionServices(String protectionServices) {
        this.protectionServices = protectionServices;
    }

    public String getPsychosocialSupportServices() {
        return psychosocialSupportServices;
    }

    public void setPsychosocialSupportServices(String psychosocialSupportServices) {
        this.psychosocialSupportServices = psychosocialSupportServices;
    }

    public int getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(int serviceNo) {
        this.serviceNo = serviceNo;
    }

    public int getNumberOfServices() {
        return numberOfServices;
    }

    public void setNumberOfServices(int numberOfServices) {
        this.numberOfServices = numberOfServices;
    }

    public String getShelterAndCareServices() {
        return shelterAndCareServices;
    }

    public void setShelterAndCareServices(String shelterAndCareServices) {
        this.shelterAndCareServices = shelterAndCareServices;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getVolunteerDesignation() {
        return volunteerDesignation;
    }

    public void setVolunteerDesignation(String volunteerDesignation) {
        this.volunteerDesignation = volunteerDesignation;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getCurrentHivStatus() {
        return currentHivStatus;
    }

    public void setCurrentHivStatus(String currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
    }

    public String getEnrolledInCare() {
        return enrolledInCare;
    }

    public void setEnrolledInCare(String enrolledInCare) {
        this.enrolledInCare = enrolledInCare;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }
    
    public String getPointOfService() {
        return pointOfService;
    }

    public void setPointOfService(String pointOfService) {
        this.pointOfService = pointOfService;
    }

    public String getEnrolledOnART() {
        return enrolledOnART;
    }

    public void setEnrolledOnART(String enrolledOnART) {
        this.enrolledOnART = enrolledOnART;
    }
    public LegacyCaregiver getCgiver() {
        return cgiver;
    }

    public void setCgiver(LegacyCaregiver cgiver) {
        this.cgiver = cgiver;
    }
    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
public String getEconomicStrenghteningServicesByName() 
    {
        //economicStrenghteningServicesByName=OvcServiceManager.getConcatenatedServiceNames(economicStrengtheningServices,removeStartAndEndWildCharacters);
        return economicStrenghteningServicesByName;
    }

    public String getEducationalServicesByName() 
    {
        //educationalServicesByName=OvcServiceManager.getConcatenatedServiceNames(educationalServices,removeStartAndEndWildCharacters);
        return educationalServicesByName;
    }

    public String getHealthServicesByName() 
    {
        //healthServicesByName=OvcServiceManager.getConcatenatedServiceNames(healthServices,removeStartAndEndWildCharacters);
        return healthServicesByName;
    }

    public String getNutritionalServicesByName() 
    {
        //nutritionalServicesByName=OvcServiceManager.getConcatenatedServiceNames(nutritionalServices,removeStartAndEndWildCharacters);
        return nutritionalServicesByName;
    }

    public String getProtectionServicesByName() 
    {
        //protectionServicesByName=OvcServiceManager.getConcatenatedServiceNames(protectionServices,removeStartAndEndWildCharacters);
        return protectionServicesByName;
    }

    public String getPsychosocialServicesByName() 
    {
        //psychosocialServicesByName=OvcServiceManager.getConcatenatedServiceNames(psychosocialSupportServices,removeStartAndEndWildCharacters);
        return psychosocialServicesByName;
    }

    public String getShelterAndCareServicesByName() 
    {
        //shelterAndCareServicesByName=OvcServiceManager.getConcatenatedServiceNames(shelterAndCareServices,removeStartAndEndWildCharacters);
        return shelterAndCareServicesByName;
    }
    

}
