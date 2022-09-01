/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.BeneficiaryStatusUpdate;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface BeneficiaryStatusUpdateDao 
{
    public void saveBeneficiaryStatusUpdate(BeneficiaryStatusUpdate hsm,boolean updateBeneficiaryHivStatus) throws Exception;
    public void updateBeneficiaryStatusUpdate(BeneficiaryStatusUpdate hsm,boolean updateBeneficiaryHivStatus) throws Exception;
    public void markForDelete(BeneficiaryStatusUpdate hsm) throws Exception;
    public void deleteBeneficiaryStatusUpdate(BeneficiaryStatusUpdate hsm) throws Exception;
    public void deleteBeneficiaryStatusUpdate(String beneficiarId) throws Exception;
    public  BeneficiaryStatusUpdate getBeneficiaryStatusUpdate(String beneficiaryId) throws Exception;
    public  List getAllBeneficiaryStatusUpdateRecords() throws Exception;
    public List getAdultHouseholdMemberStatusUpdateRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public List getOvcStatusUpdateRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public List getBeneficiaryStatusUpdateRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public void changeBeneficiaryIdInBeneficiaryStatusUpdate(String oldOvcId, String newOvcId) throws Exception;
    public void deleteHivStatusRecordsOfChildrenAtRiskOfHivBeforeOctober2018() throws Exception;
    public  List getBeneficiaryStatusUpdateRecords(String beneficiaryId) throws Exception;
    public void changeBeneficiaryId(String oldBeneficiaryId, String newBeneficiaryId) throws Exception;
}
