/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.StartupResourceManager;
import com.nomis.ovc.metadata.OrganizationUnit;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class SiteSetup implements Serializable
{
    private String userName;
    private String partnerCode;
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    private String cboId;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String createdBy;
    private Partner partner;
    private CommunityBasedOrganization cbo;
    private OrganizationUnit level2Ou;
    private OrganizationUnit level3Ou;
    private OrganizationUnit level4Ou;
    StartupResourceManager objmanager=new StartupResourceManager();

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Partner getPartner() {
        partner=objmanager.getPartner(getPartnerCode());
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
   
    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String getLevel3OuId() {
        return level3OuId;
    }

    public void setLevel3OuId(String level3OuId) {
        this.level3OuId = level3OuId;
    }

    public String getLevel4OuId() {
        return level4OuId;
    }

    public void setLevel4OuId(String level4OuId) {
        this.level4OuId = level4OuId;
    }

    public CommunityBasedOrganization getCbo() {
        return cbo=objmanager.getCommunityBasedOrganization(this.getCboId());
    }

    public OrganizationUnit getLevel2Ou() {
        return level2Ou=objmanager.getOrganizationUnit(getLevel2OuId());
    }

    public OrganizationUnit getLevel3Ou() {
        return level3Ou=objmanager.getOrganizationUnit(getLevel3OuId());
    }

    public OrganizationUnit getLevel4Ou() {
        return level4Ou=objmanager.getOrganizationUnit(getLevel4OuId());
    }
    
}
