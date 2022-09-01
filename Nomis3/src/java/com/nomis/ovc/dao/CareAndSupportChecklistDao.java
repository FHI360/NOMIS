/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.CareAndSupportChecklist;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface CareAndSupportChecklistDao 
{
    public void saveCareAndSupportChecklist(CareAndSupportChecklist casc) throws Exception;
    public void updateCareAndSupportChecklist(CareAndSupportChecklist casc) throws Exception;
    public void markForDelete(CareAndSupportChecklist casc) throws Exception;
    public void deleteCareAndSupportChecklist(CareAndSupportChecklist casc) throws Exception;
    public CareAndSupportChecklist getCareAndSupportChecklist(int recordId) throws Exception;
    public CareAndSupportChecklist getCareAndSupportChecklist(String beneficiaryId,Date dateOfAssessment) throws Exception;
    public List getCareAndSupportChecklist(String beneficiaryId) throws Exception;
    public List getCareAndSupportRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public List getOvcCareAndSupportRecords(ReportParameterTemplate rpt) throws Exception;
    public List getAdultCareAndSupportRecords(ReportParameterTemplate rpt) throws Exception;
    public List getMostRecentCareAndSupportRecords(ReportParameterTemplate rpt) throws Exception;
    public List getCareAndSupportRecordsWithNullOvcTreatmentStatus() throws Exception;
    public List getCareAndSupportRecordsWithNullAdultHouseholdMemberTreatmentStatus() throws Exception;
    public void changeBeneficiaryId(String oldBeneficiaryId, String newBeneficiaryId) throws Exception;
    public int getAdultHouseholdMemberRecordsWithViralLoadResult(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public int getOvcRecordsWithViralLoadResult(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public int getAdultHouseholdMemberRecordsWhoAreVirallySuppressed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public int getOvcRecordsWhoAreVirallySuppressed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public int getNumberOfOvcSupportedToAccessARTServicesInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public List getListOfOvcSupportedToAccessARTServicesInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public int getNumberOfAdultHouseholdMembersAdherenceToTreatmentRegimentInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public int getNumberOfOvcAdherenceToTreatmentRegimentInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public List getListOfAdultHouseholdMembersAdherenceToTreatmentRegimentInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public List getCareAndSupportRecords(ReportParameterTemplate rpt) throws Exception;
    public int removeRecordsOfNonPositiveBeneficiaries() throws Exception;
}   
