/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.metadata;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class OrganizationUnit implements Serializable
{
    private String uid;
    private String oucode;
    private String name;
    private String ouPath;
    private String ouPathByNames;
    private String description;
    private String parentHierarchyId;
    private String parentId;
    private int oulevel;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String legacyId;
    private String datimId;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOucode() {
        return oucode;
    }

    public void setOucode(String oucode) {
        this.oucode = oucode;
    }

    public String getOuPath() {
        return ouPath;
    }

    public void setOuPath(String ouPath) {
        this.ouPath = ouPath;
    }

    public String getOuPathByNames() {
        return ouPathByNames;
    }

    public void setOuPathByNames(String ouPathByNames) {
        this.ouPathByNames = ouPathByNames;
    }

    public int getOulevel() {
        return oulevel;
    }

    public void setOulevel(int oulevel) {
        this.oulevel = oulevel;
    }

    public String getParentHierarchyId() {
        return parentHierarchyId;
    }

    public void setParentHierarchyId(String parentHierarchyId) {
        this.parentHierarchyId = parentHierarchyId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }

    public String getDatimId() {
        return datimId;
    }

    public void setDatimId(String datimId) {
        this.datimId = datimId;
    }
    
}
