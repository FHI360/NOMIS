/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.reports.utils.HivRiskAssessmentReport;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface HivRiskAssessmentDao 
{
    public void saveHivRiskAssessment(HivRiskAssessment hra) throws Exception;
    public void updateHivRiskAssessment(HivRiskAssessment hra) throws Exception;
    public void deleteHivRiskAssessment(HivRiskAssessment hra) throws Exception;
    public void markForDelete(HivRiskAssessment hra) throws Exception;
    public void markRiskAssessmentRecordsForDelete(String ovcId) throws Exception;
    public HivRiskAssessment getHivRiskAssessment(int recordId) throws Exception;
    public List getAllHivRiskAssessments() throws Exception;
    public HivRiskAssessment getHivRiskAssessment(String ovcId,Date dateOfAssessment) throws Exception;
    public List getHivRiskAssessmentRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public List getHivRiskAssessmentRecords(ReportParameterTemplate rpt) throws Exception;
    public List getRecordsWithZeroAgeAtAssessment() throws Exception;
    public List getRiskAssessmentRecordsByOvcId(String ovcId) throws Exception;
    public void deleteRiskAssessmentRecordsPerChild(String ovcId) throws Exception;
    public List getHivRiskAssessmentRecordsByLevel4OuId(String level4OuId) throws Exception;
    public void changeOvcIdInHivRiskAssessmentRecords(String oldOvcId, String newOvcId) throws Exception;
    public void processHivRiskAssessmentOutcome(HivRiskAssessment hra) throws Exception;
    public List getListOfOvcRiskAssessedByHivStatusAndEnrollmentStatus(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,int hivStatus,String sex) throws Exception;
    public int getNumberOfOvcRiskAssessedByHivStatusAndEnrollmentStatus(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,int hivStatus,String sex) throws Exception;
    
    //analytics report methods
    public void saveHivRiskAssessmentReport(HivRiskAssessmentReport hra) throws Exception;
    public void updateHivRiskAssessmentReport(HivRiskAssessmentReport hra) throws Exception;
    public void deleteHivRiskAssessmentReport(HivRiskAssessmentReport hra) throws Exception;
    public HivRiskAssessmentReport getHivRiskAssessmentReport(String beneficiaryId) throws Exception;
    public List getHivRiskAssessmentReportByLevel4OrganizationUnit(String level4OuId) throws Exception;
    public int getNoOfChildrenForHivRiskAssessmentCascade(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int enrollmentStatus,int eligibilityValue,int riskAssessmentValue,int riskAssessmentOutcome) throws Exception;
    public int getNumberOfChildrenEligibleForRiskAssessmentThatWereRiskAssessed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue) throws Exception;
    public int getNumberOfChildrenEligibleForRiskAssessmentRiskAssessedAndAtRiskOfHiv(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue,int riskAssessmentOutcome) throws Exception;
    public int getNumberOfChildrenWithChangeInRiskProfile(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue) throws Exception;
    public int getNumberOfChildrenRiskAssessedAndReferredForHivTestByHivStatusAndTreatmentStatus(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int riskAssessmentOutcome,boolean referralComplete,int hivStatus,int enrolledOntreatment) throws Exception;
    public int getNoOfOvcDocumentedOnHivRiskassessmentFormAsReferredForHIVTest(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue,int riskAssessmentOutcome) throws Exception;
    public int getNumberOfChildrenAssessedForHivRiskAndReferredForHivTestForTreatmentCascade(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int riskAssessmentOutcome,int referralComplete,int hivStatus,int enrolledOntreatment,int enrollmentStatus) throws Exception;
}
