/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;


import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class ReportParameterManager 
{
    public ReportParameterTemplate getPopulatedReportParameterTemplate(List paramList)
    {
        ReportParameterTemplate rpt=new ReportParameterTemplate();
        if(paramList !=null)
        {
            rpt.setLevel2OuId(paramList.get(0).toString());
            rpt.setLevel3OuId(paramList.get(1).toString());
            rpt.setCboId(paramList.get(2).toString());
            rpt.setLevel4OuId(paramList.get(3).toString());
            rpt.setStartMth(Integer.parseInt(paramList.get(4).toString()));
            rpt.setStartYear(Integer.parseInt(paramList.get(5).toString()));
            rpt.setEndMth(Integer.parseInt(paramList.get(6).toString()));
            rpt.setEndYear(Integer.parseInt(paramList.get(7).toString()));
            if(paramList.size()>7)
            rpt.setPartnerCode(paramList.get(8).toString());
        }
        return rpt;
    }
    public ReportHeaderTemplate getReportHeaderTemplate(List paramList)
    {
        ReportHeaderTemplate rht=new ReportHeaderTemplate();
        OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();
        
        String level2OuName=ouam.getOrganizationUnitName(paramList.get(0).toString());
        String level3OuName=ouam.getOrganizationUnitName(paramList.get(1).toString());
        String cboName=ouam.getCommunityBasedOrganizationName(paramList.get(2).toString());
        String startMth=paramList.get(4).toString();
        String startYr=paramList.get(5).toString();
        String endMth=paramList.get(6).toString();
        String endYr=paramList.get(7).toString();
        String partnerName=ouam.getPartnerName(paramList.get(8).toString());
        
        String[] dateParam={paramList.get(4).toString(),paramList.get(5).toString(),paramList.get(6).toString(),paramList.get(7).toString()};
        String period=null;            
        //if(util.validateDateParamenters(dateParam))
       // period="01 "+util.getMonthAsString(Integer.parseInt(paramList.get(4).toString()))+" "+ paramList.get(5).toString()+" to   end of "+util.getMonthAsString(Integer.parseInt(paramList.get(6).toString()))+" "+paramList.get(7).toString();
        
        rht.setCboId(cboName);
        rht.setEndMth(endMth);
        rht.setEndYr(endYr);
        rht.setLevel3OuId(level3OuName);
        rht.setPartnerName(partnerName);
        rht.setStartMth(startMth);
        rht.setStartYr(startYr);
        rht.setLevel2OuId(level2OuName);
        rht.setPeriod(period);
        
        return rht;
    }
}
