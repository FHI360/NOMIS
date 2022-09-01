/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.RevisedHouseholdVulnerabilityAssessment;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface HouseholdEnrollmentDao 
{
    public void saveHouseholdEnrollment(HouseholdEnrollment hhe) throws Exception;
    public void updateHouseholdEnrollment(HouseholdEnrollment hhe) throws Exception;
    public void deleteHouseholdEnrollment(HouseholdEnrollment hhe) throws Exception;
    public HouseholdEnrollment getHouseholdEnrollment(String hhUniqueId) throws Exception;
    public HouseholdEnrollment getHouseholdEnrollmentByEnrollmentId(String enrollmentId) throws Exception;
    public List getHouseholdEnrollmentByOrgUnit(String organizationUnitId) throws Exception;
    public void markedForDelete(HouseholdEnrollment hhe) throws Exception;
    public List searchHouseholdEnrollmentByPartOfName(ReportParameterTemplate rpt,String partOfName) throws Exception;
    public List getAllHouseholdEnrollmentRecords() throws Exception;
    public List getDistinctDateOfEnrollmentAscending() throws Exception;
    public List getHouseholdEnrollmentRecords(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception;
    public List getHouseholdEnrollmentRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public List getHouseholdEnrollmentWithoutAddress() throws Exception;
    public int getNumberOfHouseholdsWithoutAddress(ReportParameterTemplate rpt) throws Exception;
    public List getListOfHouseholdsWithoutAddress(ReportParameterTemplate rpt) throws Exception;
    public List getListOfHouseholdsWithNearestFacilityRecords() throws Exception;
    public List getHouseholdEnrollmentRecords(ReportParameterTemplate rpt) throws Exception;
    public int getNumberOfHouseholdsWithCasePlan(ReportParameterTemplate rpt,String startDate,String endDate,int casePlanValue,String sex) throws Exception;
    public List getListOfHouseholdsWithCasePlan(ReportParameterTemplate rpt,String startDate,String endDate,int casePlanValue,String sex) throws Exception;
    public List getDistinctLevel4OrganizationUnit() throws Exception;
    public List getListOfHouseholdsWithZeroCasePlanRecords() throws Exception;
    public int updateHouseholdEnrollmentWithZeroCasePlanRecords() throws Exception;
    public List getListOfHouseholdsWithCasePlanRecords() throws Exception;
    public int changeCboId(String oldCboId,String newCboId) throws Exception;
    public List getHouseholdEnrollmentRecordsMarkedForDelete(ReportParameterTemplate rpt) throws Exception;
    public List getDistinctPartnerCodes(ReportParameterTemplate rpt) throws Exception;
    public List getDistinctLevel4OrganizationUnit(ReportParameterTemplate rpt) throws Exception;
    public List getDistinctCommunityBasedOrganizationIds(ReportParameterTemplate rpt) throws Exception;
    public List getDistinctLevel3OuList(ReportParameterTemplate rpt) throws Exception;
    public List getHouseholdEnrollmentAndRevisedAssessmentRecords(ReportParameterTemplate rpt) throws Exception;
    public int getNumberOfHouseholdsEnrolled(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus) throws Exception;
    public List getListOfHouseholdsEnrolled(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus) throws Exception;
    public List getDistinctYearOfAssessment() throws Exception;
    public int getDistinctCommunityBasedOrganizationIdsWithServedBeneficiaries(ReportParameterTemplate rpt) throws Exception;
    public int getDistinctLevel2OuIdsWithServedBeneficiaries(ReportParameterTemplate rpt) throws Exception;
    public int getDistinctLevel3OuIdsWithServedBeneficiaries(ReportParameterTemplate rpt) throws Exception;
    public int getNumberOfHouseholdsGraduatedForOVC_HHGRAD(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String beneficiarySex) throws Exception;
    public List getOvcWhoseHouseholdsAreExitedFromTheProgram(String level4OuId) throws Exception;
    public List getDistinctCommunityCodesFromHouseholdsExitedFromProgram(int beneficiaryType) throws Exception;
    public List getAdultHouseholdMembersWhoseHouseholdsAreExitedFromTheProgram(String level4OuId) throws Exception;
    public int getNumberOfHouseholdsServedForOVC_HHGRAD(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String beneficiarySex) throws Exception;
    public List getDistinctLevel2OuList() throws Exception;
}
