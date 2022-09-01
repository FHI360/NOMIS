/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

/**
 *
 * @author smomoh
 */
public class MonthlySummaryFormReportDaoImpl implements MonthlySummaryFormReportDao
{
    String householdQueryPart="from HouseholdEnrollment hhe, ";
    
    public String getHIVServicesReportQuery() throws Exception
    {
        String hivServicesQuery=" (UPPER(service.serviceAccessed3) like '%HIV%' or UPPER(service.serviceAccessed3) like '%HTC%' or UPPER(service.serviceAccessed3) like '%HCT%') ";
        return hivServicesQuery;
    }
    public String getHhOvcQueryPart() throws Exception
    {
        return householdQueryPart+"Ovc ovc where hhe.hhUniqueId=ovc.hhUniqueId and ";
    }
    public String getOvcCountQueryPart() throws Exception
    {
        return "select count(distinct ovc.ovcId)"+householdQueryPart+"Ovc ovc where hhe.hhUniqueId=ovc.hhUniqueId and ";
    }
    public String getServiceCountQueryPart() throws Exception
    {
        return "select count(distinct service.ovcId)"+householdQueryPart+"Ovc ovc, ChildService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId) and " ;
    }
    public String getHhOvcAndServiceQueryPart() throws Exception
    {
        return householdQueryPart+"Ovc ovc,ChildService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId) ";
    }
    public String getHhOvcAndServiceByOvcIdQueryPart() throws Exception
    {
        return "select distinct service.ovcId "+householdQueryPart+"Ovc ovc,ChildService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId) ";
    }
    public String getHheAdultHouseholdMemberHhsQuery() throws Exception
    {
        return "select count(distinct hhs.beneficiaryId) from HouseholdService hhs, HouseholdEnrollment hhe,AdultHouseholdMember ahm where (hhe.hhUniqueId=ahm.hhUniqueId and ahm.hhUniqueId=hhs.beneficiaryId)";
    }
    public String getHheServiceCountQuery() throws Exception
    {
        return "select count(distinct hhs.beneficiaryId) from HouseholdService hhs, HouseholdEnrollment hhe where (hhe.hhUniqueId=hhs.beneficiaryId)";
    }
    public String getServiceReportName() throws Exception
    {
        return " ServiceReport";
    }
    public String getOvcCurrentlyEnrolledQuery() throws Exception
    {
        return "ovc.withdrawnFromProgram='No'";
    }
    public String getHhCurrentlyEnrolledQuery() throws Exception
    {
        return "hhe.withdrawnFromProgram='No'";
    }
    public String getahmCurrentlyEnrolledQuery() throws Exception
    {
        return "ahm.withdrawnFromProgram='No'";
    }
    public String getOvcWithdrawnQuery(String reasonWithdrawal) throws Exception
    {
        if(reasonWithdrawal==null)
        return "ovc.withdrawnFromProgram='Yes'";
        else
        return "ovc.withdrawnFromProgram='Yes' and UPPER(withdrawal.reasonWithdrawal)='"+reasonWithdrawal.toUpperCase()+"'";
    }
    public String getHhWithdrawnQuery(String reasonWithdrawal) throws Exception
    {
        if(reasonWithdrawal==null)
        return "hhe.withdrawnFromProgram='Yes'";
        else
        return "hhe.withdrawnFromProgram='Yes' and UPPER(withdrawal.reasonWithdrawal)='"+reasonWithdrawal.toUpperCase()+"'";
    }
    public String getahmWithdrawnQuery(String reasonWithdrawal) throws Exception
    {
        if(reasonWithdrawal==null)
        return "ahm.withdrawnFromProgram='Yes'";
        else
        return "ahm.withdrawnFromProgram='Yes' and UPPER(withdrawal.reasonWithdrawal)='"+reasonWithdrawal.toUpperCase()+"'";
    }
    public String getGraduatedParameter() throws Exception
    {
        return "";
    }
}
