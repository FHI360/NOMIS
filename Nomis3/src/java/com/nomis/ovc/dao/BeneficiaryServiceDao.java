/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.Service;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface BeneficiaryServiceDao 
{
    public void saveBeneficiaryService(Service bservice) throws Exception;
    public void updateBeneficiaryService(Service bservice) throws Exception;
    public void markForDelete(Service bservice) throws Exception;
    public void deleteBeneficiaryService(Service bservice) throws Exception;
    public Service getBeneficiaryService(String serviceId) throws Exception;
    public Service getBeneficiaryServiceByName(String serviceName) throws Exception;
    public List getBeneficiaryServicesByDomain(String domainId) throws Exception;
    public List getBeneficiaryServicesByDomainAndDisplayStatus(String domainId,int displayStatus) throws Exception;
    public List getAllBeneficiaryServices() throws Exception;
    public List getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType(String domainId,int displayStatus,int beneficiaryType) throws Exception;
    public Service getBeneficiaryServiceByServiceCode(String serviceCode) throws Exception;
}
