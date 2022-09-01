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
public class OrganizationUnitHierarchy implements Serializable
{
    private String uid;
    private String name;
    private int oulevel;
    private String description;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;

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

    public int getOulevel() {
        return oulevel;
    }

    public void setOulevel(int oulevel) {
        this.oulevel = oulevel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }
    
}
