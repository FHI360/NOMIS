/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;


import com.nomis.ovc.business.HivStatusManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface HivStatusManagerDao 
{
    public void saveHivStatusManager(HivStatusManager hsm,boolean updateBeneficiaryHivStatus) throws Exception;
    public void updateHivStatusManager(HivStatusManager hsm,boolean updateBeneficiaryHivStatus) throws Exception;
    public void markForDelete(HivStatusManager hsm) throws Exception;
    public void deleteHivStatusManager(HivStatusManager hsm) throws Exception;
    public void deleteHivStatusManager(String beneficiarId) throws Exception;
    public  HivStatusManager getHivStatusManager(String beneficiaryId) throws Exception;
    public  List getAllHivStatusRecords() throws Exception;
    public List getAdultHouseholdMemberHivRecordsForExport(ReportParameterTemplate rpt) throws Exception;
    public List getOvcHivRecordsForExport(ReportParameterTemplate rpt) throws Exception;
    public List getHivRecordsForExport(ReportParameterTemplate rpt) throws Exception;
    public void changeBeneficiaryIdInHivStatusManager(String oldOvcId, String newOvcId) throws Exception;
    public void deleteHivStatusRecordsOfChildrenAtRiskOfHivBeforeOctober2018() throws Exception;
}
