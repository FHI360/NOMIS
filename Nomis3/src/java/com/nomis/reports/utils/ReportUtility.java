/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;


import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class ReportUtility 
{
    private int[] finerAgeDisaggregation={0,0,1,4,5,9,10,14,15,17,18,24,25,200};
    private int[] zimAgeDisaggregation={0,0,1,4,5,9,10,14,15,17,18,19,20,24,25,200};
    String householdQueryPart="from HouseholdEnrollment hhe, ";
    public int[] getFinerAgeDisaggregation() {
        return finerAgeDisaggregation;
    }

    public void setFinerAgeDisaggregation(int[] finerAgeDisaggregation) {
        this.finerAgeDisaggregation = finerAgeDisaggregation;
    }

    public int[] getZimAgeDisaggregation() {
        return zimAgeDisaggregation;
    }

    public void setZimAgeDisaggregation(int[] zimAgeDisaggregation) {
        this.zimAgeDisaggregation = zimAgeDisaggregation;
    }
    
    public static String getDefaultStartDateForReports()
    {
        String startDate="10/01/2019";
        try
        {
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return startDate;
    }
    public static String getDefaultEndDateForReports()
    {
        String endDate=DateManager.getMthDayYearFromMySqlDate(DateManager.getCurrentDate());
        try
        {
            if(DateManager.compareDates(endDate, getDefaultStartDateForReports()))
            endDate=getDefaultStartDateForReports();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return endDate;
    }
    public int[] get0_17AgeSegregation()
    {
        int[] ageSegregation={0,0,1,4,5,9,10,14,15,17};
        return ageSegregation;
    }
    public int[] getNewAgeSegregation()
    {
        int[] ageSegregation={0,0,1,4,5,9,10,14,15,17,18,100};
        return ageSegregation;
    }
    public int[] getNewFinerAgeSegregation()
    {
        int[] ageSegregation={0,0,1,4,5,9,10,14,15,17,18,24,25,100};
        return ageSegregation;
    }
    public int[] getAgeSegregation()
    {
        int[] ageSegregation={0,5,6,12,13,17};
        return ageSegregation;
    }
    public int[] getHouseholdAgeSegregation()
    {
        int[] householdAgeSegregation={0,17,18,59,60,200};
        return householdAgeSegregation;
    }
    public int[] getHouseholdAgeSegregation(int[] ageSegregation)
    {
        return ageSegregation;
    }
    public int[] getSecondHouseholdAgeSegregation()
    {
        int[] ageSegregation={0,17,18,24,25,200};
        return ageSegregation;
    }
    public String getHIVServicesReportQuery()
    {
        String hivServicesQuery=" (UPPER(service.serviceAccessed3) like '%HIV%' or UPPER(service.serviceAccessed3) like '%HTC%' or UPPER(service.serviceAccessed3) like '%HCT%') ";
        return hivServicesQuery;
    }
    public String getHhOvcQueryPart()
    {
        return householdQueryPart+"Ovc ovc where hhe.hhUniqueId=ovc.hhUniqueId and ";
    }
    public String getOvcCountQueryPart()
    {
        return "select count(distinct ovc.ovcId)"+householdQueryPart+"Ovc ovc where hhe.hhUniqueId=ovc.hhUniqueId and ";
    }
    public String getServiceCountQueryPart()
    {
        return "select count(distinct service.ovcId)"+householdQueryPart+"Ovc ovc, OvcService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId) and " ;
    }
    public String getHhOvcAndServiceQueryPart()
    {
        return householdQueryPart+"Ovc ovc,OvcService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId) ";
    }
    public String getHhOvcAndServiceByOvcIdQueryPart()
    {
        return "select distinct service.ovcId "+householdQueryPart+"Ovc ovc,OvcService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId) ";
    }
    public String getHheCaregiverHhsQuery()
    {
        return "select count(distinct hhs.caregiverId) from HouseholdService hhs, HouseholdEnrollment hhe,Caregiver cgiver where (hhe.hhUniqueId=cgiver.hhUniqueId and cgiver.hhUniqueId=hhs.hhUniqueId)";
    }
    public String getHheServiceCountQuery()
    {
        return "select count(distinct hhs.hhUniqueId) from HouseholdService hhs, HouseholdEnrollment hhe where (hhe.hhUniqueId=hhs.hhUniqueId)";
    }
    public String getServiceReportName()
    {
        return " ServiceReport";
    }
    public String getOvcCurrentlyEnrolledQuery()
    {
        return "ovc.withdrawnFromProgram='No'";
    }
    public String getHhCurrentlyEnrolledQuery()
    {
        return "hhe.withdrawnFromProgram='No'";
    }
    public String getCgiverCurrentlyEnrolledQuery()
    {
        return "cgiver.withdrawnFromProgram='No'";
    }
    public String getOvcWithdrawnQuery(String reasonWithdrawal)
    {
        if(reasonWithdrawal==null)
        return "ovc.withdrawnFromProgram='Yes'";
        else
        return "ovc.withdrawnFromProgram='Yes' and UPPER(withdrawal.reasonWithdrawal)='"+reasonWithdrawal.toUpperCase()+"'";
    }
    public String getHhWithdrawnQuery(String reasonWithdrawal)
    {
        if(reasonWithdrawal==null)
        return "hhe.withdrawnFromProgram='Yes'";
        else
        return "hhe.withdrawnFromProgram='Yes' and UPPER(withdrawal.reasonWithdrawal)='"+reasonWithdrawal.toUpperCase()+"'";
    }
    public String getCgiverWithdrawnQuery(String reasonWithdrawal)
    {
        if(reasonWithdrawal==null)
        return "cgiver.withdrawnFromProgram='Yes'";
        else
        return "cgiver.withdrawnFromProgram='Yes' and UPPER(withdrawal.reasonWithdrawal)='"+reasonWithdrawal.toUpperCase()+"'";
    }
    public String getHouseholdHIVOvcServiceReportQueryPart(String tableName)
    {
        return "from HouseholdEnrollment hhe, Ovc ovc, "+tableName+" service, HivStatusUpdate hsu where hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId and ovc.ovcId=hsu.clientId";
    }
    public String getGraduatedParameter()
    {
        return "";
    }
    public String getAdditionalEnrollmentQuery(String additionalEnrollmentQuery)
    {
        AppUtility appUtil=new AppUtility();
        if(additionalEnrollmentQuery !=null && !additionalEnrollmentQuery.equals(" ") && appUtil.isString(additionalEnrollmentQuery))
        {
            if(!additionalEnrollmentQuery.startsWith("and"))
            additionalEnrollmentQuery=" and "+additionalEnrollmentQuery;
            
            if(additionalEnrollmentQuery !=null)
            additionalEnrollmentQuery=additionalEnrollmentQuery.replace("and  and", "and");
            //additionalEnrollmentQuery=additionalEnrollmentQuery.replaceAll("and and", "and");
        }
        return additionalEnrollmentQuery;
    }
    public String getAdditionalServiceQuery(String additionalServiceQuery)
    {
        if(!additionalServiceQuery.equals(" "))
            additionalServiceQuery=" and "+additionalServiceQuery;
        return additionalServiceQuery;
    }
    public String[] getLabelParam(List paramList)
    {
        String stateCode=(String)paramList.get(0);
        String lgaCode=(String)paramList.get(1);
        String cboCode=(String)paramList.get(2);
        String wardCode=(String)paramList.get(3);
        String startMonth=paramList.get(4).toString();
        String startYear=paramList.get(5).toString();
        String endMonth=paramList.get(6).toString();
        String endYear=paramList.get(7).toString();
        String partnerCode=paramList.get(8).toString();
        String stateName=stateCode;
        String lgaName=lgaCode;
        String cboName=cboCode;
        String partnerName=partnerCode;
        
        String[] labelParam={stateCode,lgaCode,cboCode,startMonth,startYear,endMonth,endYear,stateName,lgaName,cboName,null,null,null,partnerName,partnerCode};
        return labelParam;
    }
}
