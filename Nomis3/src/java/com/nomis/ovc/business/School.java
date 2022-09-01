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
public class School implements Serializable
{
    private String schoolName;
    private int schoolType;
    private String id;
    private String orgUnitId;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    private int markForDelete;
    private OrganizationUnit level2Ou;
    private OrganizationUnit level3Ou;
    private OrganizationUnit level4Ou;
    OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(String orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(int schoolType) {
        this.schoolType = schoolType;
    }

    public int getMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(int markForDelete) {
        this.markForDelete = markForDelete;
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
        level4Ou=ouam.getOrganizationUnitForReports(getOrgUnitId());
        return level4Ou;
    }

    public void setLevel4Ou(OrganizationUnit level4Ou) {
        this.level4Ou = level4Ou;
    }
}
