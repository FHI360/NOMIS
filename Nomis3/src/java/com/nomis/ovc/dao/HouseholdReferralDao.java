/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.HouseholdReferral;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface HouseholdReferralDao 
{
  public void saveHouseholdReferral(HouseholdReferral service) throws Exception;
    public void updateHouseholdReferral(HouseholdReferral service) throws Exception;
    public void markedForDelete(HouseholdReferral service) throws Exception;
    public void markServicesForDelete(String beneficiaryId) throws Exception;
    public void deleteHouseholdReferral(String ovcId, Date serviceDate) throws Exception;
    public void deleteService(HouseholdReferral service) throws Exception;
    public HouseholdReferral getHouseholdReferral(String ovcId, Date serviceDate) throws Exception;
    public List getAllHouseholdReferrals() throws Exception;
    public List getHouseholdReferrals(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception;
    public void changeBeneficiaryIdInHouseholdReferral(String oldBeneficiaryId, String newBeneficiaryId) throws Exception;
    public List getHouseholdReferralsWithReferralRecords() throws Exception;
    public List getHouseholdReferralRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public int resetAgeAtServiceForChildrenWithZeroAgeAtService() throws Exception;
    public int resetAgeAtServiceForServiceRecords(String level4OuId) throws Exception;
    public int getNumberOfServiceRecordsPerChild(String ovcId) throws Exception;
    public List getListOfServicesByDomainAndServiceTypeAndAgeLimit(int domain,String serviveCode, int ageLimit) throws Exception;
    public void deleteServicesPerBeneficiary(String ovcId) throws Exception;
    public int getNumberOfOvcReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit(ReportParameterTemplate rpt,int domain,String serviveCode, String startDate,String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getListOfOvcReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit(ReportParameterTemplate rpt,int domain,String serviveCode, String startDate,String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfAdultHouseholdMembersReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit(ReportParameterTemplate rpt,int domain,String serviveCode, String startDate,String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getListOfAdultHouseholdMembersReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit(ReportParameterTemplate rpt,int domain,String serviveCode, String startDate,String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getServicesPerBeneficiary(String beneficiaryId) throws Exception;
    /*public int getNumberOfActiveOvcServed(String startDate, String endDate,int startAge,int endAge) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    
    public int getNumberOfOvcServedByEnrollmentStatusAndServiceType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusScreenedForHiv(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusNotScreenedForHiv(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception; 
    
    public int getNumberOfOvcServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType,String serviceCode) throws Exception;
    public int getNumberOfOvcServedAndRiskAssessedByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int hivRiskStatus) throws Exception;
    
    public int getNumberOfOvcNotServedInReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getListOfOvcServedByEnrollmentStatusAndServiceType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType) throws Exception;
    public List getListOfOvcNotServedInReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getServiceRecordsWithZeroAgeAtService() throws Exception;
    public int getNumberOfOvcServedAndRiskAssessedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int riskAssessmentStatus) throws Exception;
    public List getServicesPerChild(String ovcId) throws Exception;
    
    public List getListOfServicesByLevel4OrganizationUnit(String level4OuId) throws Exception;
    public List getListOfOvcServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType,String serviceCode) throws Exception;
    
    public int getNumberOfHivUnknownOvcNotAtRiskServed(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfHivUnknownOvcAtRiskServed(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusForDatim(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusForDatim(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfActiveOvcServed(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfActiveOvcServedForDatim(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    //public boolean isChildActiveInReportPeriod(String ovcId,String startDate) throws Exception;
    public int childServedInReportPeriod(String ovcId,String startDate,String endDate) throws Exception;
    public List getServicesByPeriodPerChild(String ovcId,String startDate,String endDate) throws Exception;
    
    */
    public List getDistinctYearOfserviceList() throws Exception;
    public void saveOrUpdateHouseholdReferral(HouseholdReferral referral) throws Exception;
}
