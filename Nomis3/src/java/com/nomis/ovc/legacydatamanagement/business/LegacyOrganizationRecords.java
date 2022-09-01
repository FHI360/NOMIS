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
public class LegacyOrganizationRecords implements Serializable
{
    private String orgCode;
    private String state;
    private String lga;
    private String orgName;
    private String orgType;
    private String orgSubtype;
    private String contactName3;
    private String address;

    private String contactName1;
    private String contactPhone1;
    private String contactEmail1;
    private String contactName2;
    private String contactPhone2;
    private String contactEmail2;
    
    private String contactPhone3;
    private String contactEmail3;
    private String services;
    private double latitude;
    private double longitude;
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactEmail1() {
        return contactEmail1;
    }

    public void setContactEmail1(String contactEmail1) {
        this.contactEmail1 = contactEmail1;
    }

    public String getContactEmail2() {
        return contactEmail2;
    }

    public void setContactEmail2(String contactEmail2) {
        this.contactEmail2 = contactEmail2;
    }

    public String getContactEmail3() {
        return contactEmail3;
    }

    public void setContactEmail3(String contactEmail3) {
        this.contactEmail3 = contactEmail3;
    }

    public String getContactName1() {
        return contactName1;
    }

    public void setContactName1(String contactName1) {
        this.contactName1 = contactName1;
    }

    public String getContactName2() {
        return contactName2;
    }

    public void setContactName2(String contactName2) {
        this.contactName2 = contactName2;
    }

    public String getContactName3() {
        return contactName3;
    }

    public void setContactName3(String contactName3) {
        this.contactName3 = contactName3;
    }

    /*public String getContactName3() {
        return contactName3;
    }

    public void setContactName3(String contactName3) {
        this.contactName3 = contactName3;
    }*/

    public String getContactPhone1() {
        return contactPhone1;
    }

    public void setContactPhone1(String contactPhone1) {
        this.contactPhone1 = contactPhone1;
    }

    public String getContactPhone2() {
        return contactPhone2;
    }

    public void setContactPhone2(String contactPhone2) {
        this.contactPhone2 = contactPhone2;
    }

    public String getContactPhone3() {
        return contactPhone3;
    }

    public void setContactPhone3(String contactPhone3) {
        this.contactPhone3 = contactPhone3;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgSubtype() {
        return orgSubtype;
    }

    public void setOrgSubtype(String orgSubtype) {
        this.orgSubtype = orgSubtype;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    

}
