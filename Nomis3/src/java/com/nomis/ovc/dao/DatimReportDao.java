/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.reports.utils.DatimReportTemplate;
import com.nomis.reports.utils.DatimReportTemplateTest;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface DatimReportDao 
{
    public void saveDatimReportTemplate(DatimReportTemplate drt) throws Exception;
    public void saveDatimReportTemplateTest(DatimReportTemplateTest rt) throws Exception;
    public void saveOrUpdateDatimReportTemplate(DatimReportTemplate drt) throws Exception;
    public void saveDatimReportData(List datimReportTemplateList,String userName) throws Exception; 
    public void updateDatimReportTemplate(DatimReportTemplate drt) throws Exception;
    public void deleteDatimReportTemplate(DatimReportTemplate drt) throws Exception;
    public List getAllDatimReportTemplates() throws Exception;
    public List getAllDatimReportTemplateByLevel2Ou(String Level2Ou) throws Exception;
    public List getAllDatimReportTemplateByLevel3Ou(String Level2Ou,String Level3Ou) throws Exception;
    public List getAllDatimReportTemplateByCbo(String Level2Ou,String Level3Ou,String cbo) throws Exception;
    public DatimReportTemplate getDatimReportTemplate(DatimReportTemplate drt) throws Exception;
    public DatimReportTemplate getDatimReportTemplate(String Level2Ou,String Level3Ou,String cbo,String partnerCode,String period) throws Exception;
    public List getDistinctPeriods() throws Exception;
    public List getDistinctLevel2Ous() throws Exception;
    public List getDistinctLevel2OusByPeriod(String period) throws Exception;
    public DatimReportTemplate getDatimReportTemplate(String Level3Ou,String cbo,String partnerCode, String period) throws Exception;
    public int deleteAllDatimReportTemplates(String Level2Ou) throws Exception;
    public List getDistinctLevel2OusByPartner(String partnerCode) throws Exception;
    public List getDistinctLevel2OusByPeriodAndPartner(String partnerCode,String period) throws Exception;
    public DatimReportTemplate getDatimReportTemplate(String Level2Ou,String Level3Ou,String period) throws Exception;
    public List getDistinctLevel3Ous(String Level2OuName) throws Exception;
}
