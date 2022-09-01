/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class ServiceDomain implements Serializable
{
    private String serviceDomainId;
    private String serviceDomainName;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceDomainId() {
        return serviceDomainId;
    }

    public void setServiceDomainId(String serviceDomainId) {
        this.serviceDomainId = serviceDomainId;
    }

    public String getServiceDomainName() {
        return serviceDomainName;
    }

    public void setServiceDomainName(String serviceDomainName) {
        this.serviceDomainName = serviceDomainName;
    }
    
}
