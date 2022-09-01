/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;


import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.metadata.OrganizationUnit;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class ReferralFacility implements Serializable
{
    private String facilityId;
    private String facilityName;
    private int typeOfFacility;
    private String address;
    private String contactPhone;
    private String contactEmail;
    private String nameOfContactPerson;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String organizationUnitId;
    private double latitude;
    private double longitude;
    private String recordedBy;
    private String datimId;
    private OrganizationUnit level2Ou;
    private OrganizationUnit level3Ou;
    private OrganizationUnit level4Ou;
    OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getNameOfContactPerson() {
        return nameOfContactPerson;
    }

    public void setNameOfContactPerson(String nameOfContactPerson) {
        this.nameOfContactPerson = nameOfContactPerson;
    }

    public int getTypeOfFacility() {
        return typeOfFacility;
    }

    public void setTypeOfFacility(int typeOfFacility) {
        this.typeOfFacility = typeOfFacility;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }
   
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public OrganizationUnit getLevel2Ou() {
        level2Ou=ouam.getLevel2OrganizationUnitFromOuPart(getLevel4Ou().getOuPath());
        return level2Ou;
    }

    public void setLevel2Ou(OrganizationUnit level2Ou) {
        this.level2Ou = level2Ou;
    }

    public OrganizationUnit getLevel3Ou() {
        level3Ou=ouam.getLevel3OrganizationUnitFromOuPart(getLevel4Ou().getOuPath());
        return level3Ou;
    }

    public void setLevel3Ou(OrganizationUnit level3Ou) {
        this.level3Ou = level3Ou;
    }

    public OrganizationUnit getLevel4Ou() {
        level4Ou=ouam.getOrganizationUnitForReports(getOrganizationUnitId());
        return level4Ou;
    }

    public void setLevel4Ou(OrganizationUnit level4Ou) {
        this.level4Ou = level4Ou;
    }

    public String getDatimId() {
        return datimId;
    }

    public void setDatimId(String datimId) {
        this.datimId = datimId;
    }

}
