/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface AdultHouseholdMemberDao 
{
    public void saveAdultHouseholdMember(AdultHouseholdMember ahm) throws Exception;
    public void updateAdultHouseholdMember(AdultHouseholdMember ahm) throws Exception;
    public void markForDelete(AdultHouseholdMember ahm) throws Exception;
    public void deleteAdultHouseholdMember(AdultHouseholdMember ahm) throws Exception;
    public List getAllAdultHouseholdMembers() throws Exception;
    public List getAdultHouseholdMembersPerHousehold(String hhUniqueId) throws Exception;
    public AdultHouseholdMember getAdultHouseholdMember(String beneficiaryId) throws Exception;
    public AdultHouseholdMember getHeadOfHousehold(String hhUniqueId) throws Exception;
    public void updateAllAdultHouseholdMembers() throws Exception;
    public List getAdultHouseholdMembers(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception;
    public List searchHouseholdProfileByPartOfName(ReportParameterTemplate rpt,String partOfName) throws Exception;
    public List getAdultHouseholdMemberRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public int getNumberOfAdultHouseholdMembersEnrolledWithinReportPeriod(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    public int getNumberOfAdultHouseholdMembersEnrolled(String additionalQuery,int currentEnrollmentStatus,int startAge,int endAge,String sex) throws Exception;
    public List getListOfAdultHouseholdMembersEnrolled(String additionalQuery,int currentEnrollmentStatus,int startAge,int endAge,String sex) throws Exception;
    public List getListOfAdultHouseholdMembersEnrolledWithinReportPeriod(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    public List getListOfAdultHouseholdMembersWithZeroAge() throws Exception;
    public int updateAdultHouseholdMembersAge() throws Exception;
    public List getListOfAdultHouseholdMembersByLevel4OrganizationUnit(String level4OuId) throws Exception;
    public int getNumberOfAdultHouseholdMembersExitedWithoutGraduation(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex) throws Exception;
    //public void changeAdultHouseholdMemberBeneficiaryId(String oldHhUniqueId, String newHhUniqueId) throws Exception;
    public void updateAdultHouseholdMemberWithoutDuplicateCheck(AdultHouseholdMember ahm,AdultHouseholdMember existingAhm) throws Exception;
    public List getAdultHouseholdMembersWithBaselineAgeLessThan18() throws Exception;
    public int updateAdultHouseholdMemberBaselineAge() throws Exception;
    public List getAllAdultHouseholdMembers(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception;
    public int getNoOfAdultHouseholdMembersByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    public List getListOfAdultHouseholdMembersByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    public List getCaregiversPerHousehold(String hhUniqueId) throws Exception;
    public AdultHouseholdMember getAdultHouseholdMemberByName(String hhUniqueId,String firstName,String surname) throws Exception;
    public List getAdultHouseholdMemberRecordsMarkedForDelete(ReportParameterTemplate rpt) throws Exception;
    public int getNumberOfAdultHouseholdMembersTestedAndRecievedResultInReportPeriodByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,int enrolledOnTreatmentValue,int hivStatus,String sex) throws Exception;
    public List getListOfAdultHouseholdMembersTestedAndRecievedResultInReportPeriodByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,int enrolledOnTreatmentValue,int hivStatus,String sex) throws Exception;
    //public int getNumberOfAdultHouseholdMembersSupportedToAccessARTServicesInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    //public List getListOfAdultHouseholdMembersSupportedToAccessARTServicesInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public void setAdultHouseholdMemberNewEnrollmentStatus(String hhUniqueId,int enrollmentStatus,Date dateOfNewEnrollmentStatus) throws Exception;
    public List getRecordsWithPositiveHivStatusAtBaselineButOtherStatusCurrently() throws Exception;
    public List getRecordsWithKnownBaselineHivStatusButUnknownCurrentHivStatus() throws Exception;
    public int getNumberOfAdultHouseholdMembersEnrolledOnTreatmentInReportPeriodByEnrollmentStatus(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public List getListOfAdultHouseholdMembersEnrolledOnTreatmentInReportPeriodByEnrollmentStatus(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception;
    public void mergeCaregivers(String caregiverIdToDelete,String caregiverIdToKeep) throws Exception;
    public List getAdultHouseholdMemberRecords(ReportParameterTemplate rpt) throws Exception;
    public int getNumberOfAdultsEligibleForViralLoad(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex,boolean newEligibilityOnly) throws Exception;
    public int getNoOfAdultHouseholdMembersNewlyEnrolledAndServed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,int enrollmentStatus,String sex) throws Exception;
    public AdultHouseholdMember getAdultHouseholdMemberByTreatmentId(String beneficiaryId) throws Exception;
}
