/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;


import com.nomis.ovc.business.CustomIndicatorsReport;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface CustomIndicatorsReportDao 
{
    public void saveCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception;
    public void saveOrUpdateCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception;
    public void updateCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception;
    public void deleteCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception;
    public List getAllCustomIndicatorsReports() throws Exception;
    public List getAllCustomIndicatorsReportByState(String state) throws Exception;
    public List getAllCustomIndicatorsReportByLga(String state,String lga) throws Exception;
    public List getAllCustomIndicatorsReportByCbo(String state,String lga,String cbo) throws Exception;
    public CustomIndicatorsReport getCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception;
    public CustomIndicatorsReport getCustomIndicatorsReport(String state,String lga,String cbo,String indicatorName,String otherDisaggregation,String period) throws Exception;
    public List getDistinctPeriods() throws Exception;
    public List getDistinctLevel2Ous() throws Exception;
    public List getDistinctLevel2OusByPeriod(String period) throws Exception;
    public CustomIndicatorsReport getCustomIndicatorsReport(String lga,String cbo,String partnerCode,String indicatorName, String merCode,String otherDisaggregation,String period) throws Exception;
    public int deleteAllCustomIndicatorsReports(String state) throws Exception;
    public List getDistinctLevel2OusByPartner(String partnerCode) throws Exception;
    public List getDistinctLevel2OusByPeriodAndPartner(String partnerCode,String period) throws Exception;
    public List getCustomIndicatorsReportsByLevel2OuPartnerAndPeriod(String state,String partnerCode,String period) throws Exception;
}
