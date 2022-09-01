/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.HouseholdService;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface HouseholdServiceDao 
{
    public void saveHouseholdService(HouseholdService hhs,boolean calculateAgeAtService) throws Exception;
    public void updateHouseholdService(HouseholdService hhs,boolean calculateAgeAtService) throws Exception;
    public void deleteHouseholdService(HouseholdService hhs) throws Exception;
    public void markForDelete(HouseholdService hhs) throws Exception;
    public void markHouseholdServicesForDelete(String beneficiaryId) throws Exception;
    public void deleteHouseholdServicesByBeneficiaryId(String beneficiaryId) throws Exception;
    public HouseholdService getHouseholdService(int id) throws Exception;
    public HouseholdService getHouseholdService(String beneficiaryId,Date serviceDate) throws Exception;
    public List getHouseholdServicePerBeneficiary(String beneficiaryId) throws Exception;
    public List getHouseholdServiceByServiceDate(Date serviceDate) throws Exception;
    public int getNumberOfBeneficiariesServedByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getHouseholdServices(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception;
    public List getOvcServicesWithReferralRecords() throws Exception;
    public int getNumberOfHouseholdsServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType,String serviceCode) throws Exception;
    public List getHouseholdServiceRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public int getNumberOfAdultMembersNotServedInReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getListOfAdultMembersNotServedInReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getListOfAdultMembersServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType,String serviceCode) throws Exception;
    public List getServiceRecordsWithZeroAgeAtService() throws Exception;
    public List getListOfServicesByLevel4OrganizationUnit(String level4OuId) throws Exception;
    public void changeCaregiverIdInHouseholdService(String oldBeneficiaryId, String newBeneficiaryId) throws Exception;
    public int getNumberOfAdultHouseholdMembersServedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    public List getMentorshipServices() throws Exception;
    public int getNumberOfActiveAdultMembersServedForDatim(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfAdultMembersServedByEnrollmentStatusForDatim(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getServicesByPeriodPerBeneficiary(String beneficiaryId,String startDate,String endDate) throws Exception;
    public List getServiceRecordsByCommunity(String communityCode) throws Exception;
    public void saveOrUpdateHouseholdService(HouseholdService hhs,boolean calculateAgeAtService) throws Exception;
    public int getNumberOfAdultMembersServedByEnrollmentStatusWithinReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean newlyEnrolled) throws Exception;
    public int getNumberOfActiveAdultMembersServedByHivStatusForDatim(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex,int hivStatus,int enrolledOnTreatment,boolean viralLoadEligibilityRequired,boolean viralLoadResultRequired,boolean viralLoadSuppressionRequired) throws Exception;
    public int getNumberOfAdultMembersServedByEnrollmentStatusAndHivStatusForDatim(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int hivStatus,int enrolledOnTreatment,boolean viralLoadEligibilityRequired,boolean viralLoadResultRequired,boolean viralLoadSuppressionRequired) throws Exception;
}
