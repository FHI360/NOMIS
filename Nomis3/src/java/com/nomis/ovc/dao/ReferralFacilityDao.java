/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;


import com.nomis.ovc.business.ReferralFacility;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface ReferralFacilityDao 
{
    public void saveReferralFacility(ReferralFacility rf) throws Exception;
    public void updateReferralFacility(ReferralFacility rf) throws Exception;
    public void deleteReferralFacility(ReferralFacility rf) throws Exception;
    public ReferralFacility getReferralFacility(String facilityId) throws Exception;
    public ReferralFacility getReferralFacilityByName(String facilityName) throws Exception;
    public List getReferralFacilitiesByName(String facilityName) throws Exception;
    public List getAllReferralFacilities() throws Exception;
    public ReferralFacility createReferralFacility(String name,String address, String contactPhone,String contactEmail,String contactPerson,double latitude,double longitude,String orgUnitId,String userName,int type) throws Exception;
    public List getReferralFacilitiesByOrgUnit(ReportParameterTemplate rpt) throws Exception;
    public List getReferralFacilitiesWithDefaultOrgUnit() throws Exception;
}
