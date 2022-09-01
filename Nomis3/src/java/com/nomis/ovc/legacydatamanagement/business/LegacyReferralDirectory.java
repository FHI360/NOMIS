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
public class LegacyReferralDirectory implements Serializable
{
    private String facilityId;
    private String facilityName;
    private String typeOfOrganization;
    private String sourceOfFunding;
    private String address;
    private String contactNumber;
    private String contactEmail;
    private String nameOfContact;
    private String typeOfServices;
    private String serviceComponents;
    private String targetGroups;
    private String cost;
    private String businessHours;
    private String latitude;
    private String longitude;
    private String dateModified;
    private String partnerCode;
    private String state;
    private String lga;
    private String ward;
    private String community;
    private int markedForDelete;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
      
    public String getNameOfContact() {
        return nameOfContact;
    }

    public void setNameOfContact(String nameOfContact) {
        this.nameOfContact = nameOfContact;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getServiceComponents() {
        return serviceComponents;
    }

    public void setServiceComponents(String serviceComponents) {
        this.serviceComponents = serviceComponents;
    }

    public String getSourceOfFunding() {
        return sourceOfFunding;
    }

    public void setSourceOfFunding(String sourceOfFunding) {
        this.sourceOfFunding = sourceOfFunding;
    }

    public String getTargetGroups() {
        return targetGroups;
    }

    public void setTargetGroups(String targetGroups) {
        this.targetGroups = targetGroups;
    }

    public String getTypeOfOrganization() {
        return typeOfOrganization;
    }

    public void setTypeOfOrganization(String typeOfOrganization) {
        this.typeOfOrganization = typeOfOrganization;
    }

    public String getTypeOfServices() {
        return typeOfServices;
    }

    public void setTypeOfServices(String typeOfServices) {
        this.typeOfServices = typeOfServices;
    }
    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

}
