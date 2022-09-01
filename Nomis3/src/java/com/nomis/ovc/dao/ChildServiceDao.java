/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.ChildService;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface ChildServiceDao 
{
    public void saveChildService(ChildService service,boolean saveBirthRegistration) throws Exception;
    public void updateChildService(ChildService service,boolean saveBirthRegistration) throws Exception;
    public void markedForDelete(ChildService service) throws Exception;
    public void markChildServicesForDelete(String ovcId) throws Exception;
    public void deleteChildService(String ovcId, Date serviceDate) throws Exception;
    public void deleteService(ChildService service) throws Exception;
    public ChildService getChildService(String ovcId, Date serviceDate) throws Exception;
    public List getAllChildServices() throws Exception;
    public int getNumberOfActiveOvcServed(String startDate, String endDate,int startAge,int endAge) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    
    public List getListOfOvcServedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    
    public int getNumberOfOvcServedByEnrollmentStatusAndServiceType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusScreenedForHiv(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusNotScreenedForHiv(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception; 
    public List getChildServicesWithReferralRecords() throws Exception;
    public int getNumberOfOvcServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType,String serviceCode) throws Exception;
    public int getNumberOfOvcServedAndRiskAssessedByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int hivRiskStatus) throws Exception;
    public List getChildServiceRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public int getNumberOfOvcNotServedInReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getListOfOvcServedByEnrollmentStatusAndServiceType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType) throws Exception;
    public List getListOfOvcNotServedInReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public List getServiceRecordsWithZeroAgeAtService() throws Exception;
    public int getNumberOfOvcServedAndRiskAssessedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int riskAssessmentStatus) throws Exception;
    public List getServicesPerChild(String ovcId) throws Exception;
    public void deleteServicesPerChild(String ovcId) throws Exception;
    public List getListOfServicesByLevel4OrganizationUnit(String level4OuId) throws Exception;
    public List getListOfOvcServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType,String serviceCode) throws Exception;
    public void changeOvcIdInService(String oldOvcId, String newOvcId) throws Exception;
    public int getNumberOfHivUnknownOvcNotAtRiskServed(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfHivUnknownOvcAtRiskServed(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfServiceRecordsPerChild(String ovcId) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusForDatim(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusForDatim(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,String enrollmentStreamCode,boolean newlyEnrolledOnly) throws Exception;
    public int getNumberOfActiveOvcServed(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception;
    public int getNumberOfActiveOvcServedForDatim(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly,int hivStatus) throws Exception;
    //public boolean isChildActiveInReportPeriod(String ovcId,String startDate) throws Exception;
    public int childServedInReportPeriod(String ovcId,String startDate,String endDate) throws Exception;
    public List getServicesByPeriodPerChild(String ovcId,String startDate,String endDate) throws Exception;
    public List getListOfServicesByDomainAndServiceTypeAndAgeLimit(int domain,String serviveCode, int ageLimit) throws Exception;
    public int resetAgeAtServiceForChildrenWithZeroAgeAtService() throws Exception;
    public int resetAgeAtServiceForServiceRecords(String level4OuId) throws Exception;
    public List getDistinctYearOfserviceList() throws Exception;
    public List getAndUpdateWashRecords() throws Exception;
    public int getNumberOfMalnourishedChildrenProvidedNutritionalServices(ReportParameterTemplate rpt,String startDate, String endDate,int startAge, int endAge,int enrollmentStatus,int currentNutritionStatus,String sex,String serviceCode) throws Exception;
    public List getListOfMalnourishedChildrenProvidedNutritionalServices(ReportParameterTemplate rpt,String startDate, String endDate,int startAge, int endAge,int enrollmentStatus,int currentNutritionStatus,String sex,String serviceCode) throws Exception;
    public List getServiceRecordsByCommunity(String communityCode) throws Exception;
    public List getServicesByOvcIdAndAdditionalServiceQueryByNumberOfServices(String ovcId,String additionalServiceQuery) throws Exception;
    public int getNumberOfServicesPerServiceRecord(ChildService service) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusAndServiceType(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment,int domain,String serviveCode) throws Exception;
    public int getNumberOfOvcServedBySubDomain(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int subDomain) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndEnrollmentStream(ReportParameterTemplate rpt,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,String enrollmentStreamCode) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusWithinReportPeriod(ReportParameterTemplate rpt,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,String enrollmentStreamCode,boolean newlyEnrolledOnly,int enrollmentSetting,int birthCertificateValue,int childInSchoolValue) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusForOVC_EDU(ReportParameterTemplate rpt,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int childInSchoolValue,int regularSchoolAttendance) throws Exception;
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusAndViralLoadCascadeForDatim(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment,boolean viralLoadEligibilityRequired,boolean viralLoadResultRequired,boolean viralLoadSuppressionRequired) throws Exception;
    public List getListOfServicesByDomainAndServiceTypeCommunityAndAgeLimit(String communityCode,int domain,String serviveCode, int ageLimit) throws Exception;
    public int getNumberOfHouseholdsServedForDatim(ReportParameterTemplate rpt,String startDate, String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,int enrollmentStatus) throws Exception;
}
