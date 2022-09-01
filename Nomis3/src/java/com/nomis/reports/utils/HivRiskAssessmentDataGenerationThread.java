/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppUtility;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class HivRiskAssessmentDataGenerationThread extends Thread
{
    String level4OuId;
    public HivRiskAssessmentDataGenerationThread(String level4OuId)
    {
       this.level4OuId=level4OuId; 
    }
    public void run()
    {
         try
         {
            DaoUtility util=new DaoUtility();
            List hraList=null;
            HivRiskAssessment hra=null;
            HivRiskAssessmentReport hrar=null; 
            int count=0;
            String message="";
            AppUtility.hivRiskAssessmentDataGeneratorThreadCounter++;
            List list=util.getHivRiskAssessmentDaoInstance().getHivRiskAssessmentReportByLevel4OrganizationUnit(level4OuId);
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
            }
            --AppUtility.hivRiskAssessmentDataGeneratorThreadCounter;
            if(AppUtility.hivRiskAssessmentDataGeneratorThreadCounter==0)
            updateHivRiskAssessmentReport();
            message=" AppUtility.hivRiskAssessmentDataGeneratorThreadCounter is "+AppUtility.hivRiskAssessmentDataGeneratorThreadCounter;
            System.err.println(message);
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
    }
    public int updateHivRiskAssessmentReport()
    {
        int success=0;
        try
        {
            DaoUtility util=new DaoUtility();
            success=util.executeSQLupdate("UPDATE APP.HIVRISKASSESSMENTREPORT SET DAYSSINCELASTHIVRISKASSESSMENT = {fn timestampdiff(SQL_TSI_DAY, DATE(DATEOFLASTRISKASSESSMENT), DATE(CURRENT_DATE))} where DATEOFLASTRISKASSESSMENT>'1900-01-01'");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return success;
    }
}
