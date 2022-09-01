/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatabaseUtilities;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class AnalyticsDataGenerator implements Serializable
{

public static String generateHivRiskAssessmentData()
{
        
    String message=null;
    try
    {
        if(AppUtility.hivRiskAssessmentDataGeneratorThreadCounter==0)
        {
        DatabaseUtilities dbUtil=new DatabaseUtilities();
        DaoUtility util=new DaoUtility();
        String level4OuId=null;
        List list=null;
        List hraList=null;
        HivRiskAssessment hra=null;
        HivRiskAssessmentReport hrar=null;
        dbUtil.createHivRiskAssessmentReportTable(true);
        List level4OuIdList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
        int count=0;
        Thread t1=null;
        for(Object obj:level4OuIdList)
        {
            level4OuId=(String)obj;
            t1=new Thread(new HivRiskAssessmentDataGenerationThread(level4OuId));
            t1.start();
            
            /*list=util.getHivRiskAssessmentDaoInstance().getHivRiskAssessmentReportByLevel4OrganizationUnit(level4OuId);
            if(list !=null && !list.isEmpty())
            {
                for(Object hraObj:list)
                {
                    hrar=(HivRiskAssessmentReport)hraObj;
                    hraList=util.getHivRiskAssessmentDaoInstance().getRiskAssessmentRecordsByOvcId(hrar.getBeneficiaryId());
                    if(hraList !=null && !hraList.isEmpty())
                    {
                        hra=(HivRiskAssessment)hraList.get(0);
                        hrar.setDateOfLastRiskAssessment(hra.getDateOfAssessment());
                        hrar.setOutcomeOfLastHivRiskAssessment(hra.getChildAtRiskQuestion());
                        hrar.setChildReferredForHIVTest(hra.getChildReferredForHIVTest());
                        if(hraList.size()>1)
                        {
                            hra=(HivRiskAssessment)hraList.get(1);
                            hrar.setDateOfPreviousRiskAssessment(hra.getDateOfAssessment());
                            hrar.setOutcomeOfPreviousRiskAssessment(hra.getChildAtRiskQuestion());
                        }
                        util.getHivRiskAssessmentDaoInstance().updateHivRiskAssessmentReport(hrar);
                        count++;
                        message=count+" HIV Risk assessment data generated for analytics";
                        System.err.println(message);
                    }
                    //System.err.println("level4OuId OU Id is "+level4OuId+" and ovc.getVulnerabilityStatusCode() at "+count+" is "+ovc.getVulnerabilityStatusCode());
                    
                }
                
            }*/
        }
        updateHivRiskAssessmentReport();
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return message;
}
public static int updateHivRiskAssessmentReport()
{
    int success=0;
    try
    {
        if(AppUtility.hivRiskAssessmentDataGeneratorThreadCounter==0)
        {
            DaoUtility util=new DaoUtility();
            success=util.executeSQLupdate("UPDATE APP.HIVRISKASSESSMENTREPORT SET DAYSSINCELASTHIVRISKASSESSMENT = {fn timestampdiff(SQL_TSI_DAY, DATE(DATEOFLASTRISKASSESSMENT), DATE(CURRENT_DATE))} where DATEOFLASTRISKASSESSMENT>'1900-01-01'");
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return success;
}
public static String generateDataForCareAndSupportReport(boolean dropBeforeCreate)
{
    DatabaseUtilities dbutils=new DatabaseUtilities();
    String message=dbutils.createHivPositiveDataTable(dropBeforeCreate);
    return message;   
}
public static String generateDataForBeneficiaryEnrollmentReport(boolean dropBeforeCreate)
{
    DatabaseUtilities dbutils=new DatabaseUtilities();
    String message=dbutils.createBeneficiaryEnrollmentTable(dropBeforeCreate);
    return message;   
}
public static String generateDataForBeneficiaryServiceReport(boolean dropBeforeCreate)
{
    DatabaseUtilities dbutils=new DatabaseUtilities();
    String message=dbutils.createBeneficiaryServiceTable(dropBeforeCreate);
    return message;   
}
}
