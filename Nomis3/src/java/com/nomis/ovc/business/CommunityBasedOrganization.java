/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class CommunityBasedOrganization implements Serializable
{
    private String uniqueId;
    private String cboCode;
    private String cboName;
    private String address;
    private String contactPersonName;
    private String contactPersonPhone;
    private String contactPersonEmail;
    private double latitude;
    private double longitude;
    private String dataExchangeId;
    private String level2OuId;
    private String assignedLevel3OuIds;
    private String services;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    private String legacyId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAssignedLevel3OuIds() {
        return assignedLevel3OuIds;
    }

    public void setAssignedLevel3OuIds(String assignedLevel3OuIds) {
        this.assignedLevel3OuIds = assignedLevel3OuIds;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    
    public String getCboName() {
        return cboName;
    }

    public void setCboName(String cboName) {
        this.cboName = cboName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getDataExchangeId() {
        return dataExchangeId;
    }

    public void setDataExchangeId(String dataExchangeId) {
        this.dataExchangeId = dataExchangeId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
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

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getCboCode() {
        return cboCode;
    }

    public void setCboCode(String cboCode) {
        this.cboCode = cboCode;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }
    
}
