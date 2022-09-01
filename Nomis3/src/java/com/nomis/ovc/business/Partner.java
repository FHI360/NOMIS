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
public class Partner implements Serializable
{
    private String partnerCode;
    private String partnerName;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String datimId;
    
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

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getDatimId() {
        return datimId;
    }

    public void setDatimId(String datimId) {
        this.datimId = datimId;
    }
    
}
