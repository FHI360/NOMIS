/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.ServiceDomain;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface ServiceDomainDao 
{
    public void saveServiceDomain(ServiceDomain domain) throws Exception;
    public void updateServiceDomain(ServiceDomain domain) throws Exception;
    public void deleteServiceDomain(ServiceDomain domain) throws Exception;
    public ServiceDomain getServiceDomain(String serviceDomainId) throws Exception;
    public List getAllServiceDomains() throws Exception;
}
