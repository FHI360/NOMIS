/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.Ovc;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface ChildEnrollmentDao 
{
    public void saveOvc(Ovc ovc,boolean saveHiv,boolean saveBirthRegistration) throws Exception;
    public void updateOvc(Ovc ovc,boolean saveHiv,boolean saveBirthRegistration) throws Exception;
    public void markForDelete(Ovc ovc) throws Exception;
    public void deleteOvc(Ovc ovc) throws Exception;
    public Ovc getOvc(String ovcId) throws Exception;
    public List getOvcPerHousehold(String hhUniqueId) throws Exception;
    public List getAllOvcRecords() throws Exception;
    public int getNoOfOvcByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus) throws Exception;
    public List getListOfOvcByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus) throws Exception;
    public int getNoOfOvcByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus) throws Exception;
    public List getListOfOvcByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus) throws Exception;
    public List getOvcRecords(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception;
    public List getOvcRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public List getListOfOvcWithDifferentPrimCaregiverId() throws Exception;
    public int updatePrimaryCaregiverIdWithHhUniqueId() throws Exception;
    public int getOvcBirthCertificateData(ReportParameterTemplate rpt,int enrollmentStatus,String sex) throws Exception;
    public List getOvcBirthCertificateList(ReportParameterTemplate rpt,int enrollmentStatus,String sex) throws Exception;
    public int getOvcSchoolEnrollmentData(ReportParameterTemplate rpt,String sex) throws Exception;
    public List getOvcSchoolEnrollmentList(ReportParameterTemplate rpt,String sex) throws Exception;
    public int getNumberOfOvcEnrollmentByOtherVulnerabilityTypes(ReportParameterTemplate rpt,String sex,String vulnerabilityStatusCodeOrName,int enrollmentStatus) throws Exception;
    public List getListOfOvcEnrollmentByOtherVulnerabilityTypes(ReportParameterTemplate rpt,String sex,String vulnerabilityStatusCodeOrName,int enrollmentStatus) throws Exception;
    public List getListOfOvcByFacilityId(String facilityId) throws Exception;
    public void deleteServices(String ovcId) throws Exception;
    public int getNumberOfOvcWithCasePlan(ReportParameterTemplate rpt,String startDate,String endDate,int casePlanValue,String sex) throws Exception;
    public List getListOfOvcWithCasePlan(ReportParameterTemplate rpt,String startDate,String endDate,int casePlanValue,String sex) throws Exception;
    public List getListOfOvcByLevel4OrganizationUnit(String level4OuId) throws Exception;
    public int getNumberOfOvcExitedWithoutGraduation(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex) throws Exception;
    public void changeOvcHhUniqueId(String oldHhUniqueId, String newHhUniqueId) throws Exception;
    public void mergeOvc(String ovcIdToDelete, String ovcIdToKeep) throws Exception;
    public List getListOfOvcWithZeroCasePlanRecords() throws Exception;
    public int updateOvcWithZeroCasePlanRecords() throws Exception;
    public List getListOfOvcWithCasePlanRecords() throws Exception;
    public List searchOvcByPartOfName(ReportParameterTemplate rpt,String partOfName) throws Exception;
    public int getNoOfOvcByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception;
    public List getListOfOvcByLevel4OrganizationUnitByEnrollmentStatus(String level4OuId,int enrollmentStatus) throws Exception;
    public void changeOvcId(String oldOvcId, String newOvcId) throws Exception;
    public List getRecordsWithPositiveHivStatusAtBaselineButOtherStatusCurrently() throws Exception;
    public int updateOvcOnly(Ovc ovc) throws Exception;
    public void saveOvcCurrentHivStatus(Ovc ovc) throws Exception;
    public Ovc getOvcByName(String hhUniqueId,String firstName,String surname) throws Exception;
    public int getNumberOfOvcPerCaregiver(String caregiverId) throws Exception;
    public List getOvcRecordsMarkedForDelete(ReportParameterTemplate rpt) throws Exception;
    public int getNumberOfOvcEnrolledOnTreatment(ReportParameterTemplate rpt,String startDate,String endDate,int enrolledOnTreatmentValue,int enrollmentStatus,String sex) throws Exception;
    public List getListOfOvcEnrolledOnTreatment(ReportParameterTemplate rpt,String startDate,String endDate,int enrolledOnTreatmentValue,int enrollmentStatus,String sex) throws Exception;
    public List getListOfOvcTestedAndRecievedResultInReportPeriodByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,String startDate,String endDate,int enrolledOnTreatmentValue,int enrollmentStatus,int hivStatus,String sex) throws Exception;
    public int getNumberOfOvcTestedAndRecievedResultInReportPeriodByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,String startDate,String endDate,int enrolledOnTreatmentValue,int enrollmentStatus,int hivStatus,String sex) throws Exception;
    public Ovc getOvcWithCurrentAge(Ovc ovc) throws Exception;
    public void setOvcNewEnrollmentStatus(String hhUniqueId,int enrollmentStatus,Date dateOfNewEnrollmentStatus) throws Exception;
    public List getRecordsWithKnownBaselineHivStatusButUnknownCurrentHivStatus() throws Exception;
    public List getRecordsByVulnerabilityStatusId(String vulnerabilityStatusId) throws Exception;
    public List getDistinctLevel4OrganizationUnitForRecordsWithoutMainVulnerabilityStatus() throws Exception;
    public List getOvcRecordsWithoutMainVulnerabilityStatusByOrgUnit(String organizationUnitId) throws Exception;
    public void changeCaregiverId(String oldCaregiverId, String newCaregiverId) throws Exception;
    public int getNumberOfOvcAssessedForHivRiskTestedAndRecievedResultInReportPeriodByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,int startAge, int endAge,String startDate,String endDate,int enrolledOnTreatmentValue,int enrollmentStatus,int hivStatus,String sex,int enrolledOnTreatment) throws Exception;
    public int getNumberOfOvcEligibleForViralLoad(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex,boolean newEligibilityOnly) throws Exception;
    public int getNumberOfOvcEnrollmentByMainVulnerabilityType(ReportParameterTemplate rpt,String sex,String vulnerabilityStatusCodeOrName,int enrollmentStatus) throws Exception;
    public List getListOfOvcEnrollmentByMainVulnerabilityType(ReportParameterTemplate rpt,String sex,String vulnerabilityStatusCodeOrName,int enrollmentStatus) throws Exception;
    public List getListOfOvcWithoutMainEnrollmentStreamByLevel4OrganizationUnit(String level4OuId) throws Exception;
    public Ovc getOvcByTreatmentId(String treatmentId) throws Exception;
}
