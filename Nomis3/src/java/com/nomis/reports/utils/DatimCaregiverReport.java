/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import java.util.List;

/**
 *
 * @author smomoh
 */
public class DatimCaregiverReport 
{
    ReportParameterTemplate rt=null;
    public DatimReportTemplate getDatimCaregiverNewlyEnrolledReport(ReportParameterTemplate rt,int startAge,int endAge)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForActive={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getStartMth()+"",rt.getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",startAge+"",endAge+"","All",rt.getPartnerCode(),rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversNewlyEnrolledId=ind.getIndicatorForNumberOfBeneficiariesNewlyEnrolledWithinTheReportPeriod().getIndicatorId();
        String[] selectedIndicatorArray={caregiversNewlyEnrolledId};
        List list=getSummaryStatisticsReport(sex,reportParamsForActive,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            
            if(subList.size()>1)
            {
                if(endAge<25)
                {
                    dform.setOvc_servMale18To24(Integer.parseInt(subList.get(1).toString()));
                    dform.setOvc_servFemale18To24(Integer.parseInt(subList.get(2).toString()));
                }
                else
                {
                    dform.setOvc_servMale25AndAbove(Integer.parseInt(subList.get(1).toString()));
                    dform.setOvc_servFemale25AndAbove(Integer.parseInt(subList.get(2).toString()));
                }

                //System.err.println("dform active values are dform.getOvc_servMale18To24():"+dform.getOvc_servMale18To24());
                //System.err.println("dform active values are dform.getOvc_servFemale18To24():"+dform.getOvc_servFemale18To24());
            }
        }
        return dform;
    }
    public DatimReportTemplate getDatimCaregiverActiveAndServedReportFor18To24(ReportParameterTemplate rt)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForActive={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getStartMth()+"",rt.getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",0+"",24+"","All","Partner name",rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversServedAndActive=ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodByAge().getIndicatorId();
        String[] selectedIndicatorArray={caregiversServedAndActive};
        List list=getSummaryStatisticsReport(sex,reportParamsForActive,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            
            if(subList.size()>1)
            {
                dform.setOvc_servMale18To24(Integer.parseInt(subList.get(1).toString()));
                dform.setOvc_servFemale18To24(Integer.parseInt(subList.get(2).toString()));

                //System.err.println("dform active values are dform.getOvc_servMale18To24():"+dform.getOvc_servMale18To24());
                //System.err.println("dform active values are dform.getOvc_servFemale18To24():"+dform.getOvc_servFemale18To24());
            }
        }
        return dform;
    }
    public DatimReportTemplate getDatimCaregiverActiveAndServedReportFor25AndAbove(ReportParameterTemplate rt)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForActive={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getStartMth()+"",rt.getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",25+"",5000+"","All","Partner name",rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversServedAndActive=ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodByAge().getIndicatorId();
        String[] selectedIndicatorArray={caregiversServedAndActive};
        List list=getSummaryStatisticsReport(sex,reportParamsForActive,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            if(subList.size()>1)
            {
                dform.setOvc_servMale25AndAbove(Integer.parseInt(subList.get(1).toString()));
                dform.setOvc_servFemale25AndAbove(Integer.parseInt(subList.get(2).toString()));

                //System.err.println("dform graduated values are dform.getOvc_servMale18To24():"+dform.getOvc_servMale18To24());
                //System.err.println("dform graduated values are dform.getOvc_servFemale18To24():"+dform.getOvc_servFemale18To24());
            }
        }
        return dform;
    }
    public DatimReportTemplate getDatimCaregiverGraduatedAndServedReportFor18To24(ReportParameterTemplate rt)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForExited={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getFinancialYear().getStartMonth()+"",rt.getFinancialYear().getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",0+"",24+"","All","Partner name",rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversServedAndGraduated=ind.getIndicatorForNumberOfCaregiversServedAndGraduatedWithinTheReportPeriodByAge().getIndicatorId();
        String[] selectedIndicatorArray={caregiversServedAndGraduated};
        List list=getSummaryStatisticsReport(sex,reportParamsForExited,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            if(subList.size()>1)
            {
                dform.setOvc_servMale18To24(Integer.parseInt(subList.get(1).toString()));
                dform.setOvc_servFemale18To24(Integer.parseInt(subList.get(2).toString()));

                //System.err.println("dform active values are dform.getOvc_servMale25AndAbove():"+dform.getOvc_servMale25AndAbove());
                //System.err.println("dform active values dform.getOvc_servMale25AndAbove():"+dform.getOvc_servFemale25AndAbove());
            }
        }
        return dform;
    }
    public DatimReportTemplate getDatimCaregiverGraduatedAndServedReportFor25AndAbove(ReportParameterTemplate rt)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForExited={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getFinancialYear().getStartMonth()+"",rt.getFinancialYear().getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",25+"",5000+"","All","Partner name",rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversServedAndGraduated=ind.getIndicatorForNumberOfCaregiversServedAndGraduatedWithinTheReportPeriodByAge().getIndicatorId();
        String[] selectedIndicatorArray={caregiversServedAndGraduated};
        List list=getSummaryStatisticsReport(sex,reportParamsForExited,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            if(subList.size()>1)
            {
                dform.setOvc_servMale25AndAbove(Integer.parseInt(subList.get(1).toString()));
                dform.setOvc_servFemale25AndAbove(Integer.parseInt(subList.get(2).toString()));
            }
        }
        return dform;
    }
    public DatimReportTemplate getDatimCaregiverTransferedAndServedReportFor18To24(ReportParameterTemplate rt)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForExited={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getFinancialYear().getStartMonth()+"",rt.getFinancialYear().getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",0+"",24+"","All","Partner name",rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversServedAndGraduated=ind.getIndicatorForNumberOfCaregiversServedAndTransferedWithinTheReportPeriodByAge().getIndicatorId();
        String[] selectedIndicatorArray={caregiversServedAndGraduated};
        List list=getSummaryStatisticsReport(sex,reportParamsForExited,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            if(subList.size()>1)
            {
                dform.setOvc_servMale18To24(Integer.parseInt(subList.get(1).toString()));
                dform.setOvc_servFemale18To24(Integer.parseInt(subList.get(2).toString()));
            }
        }
        return dform;
    }
    public DatimReportTemplate getDatimCaregiverTransferedAndServedReportFor25AndAbove(ReportParameterTemplate rt)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForExited={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getFinancialYear().getStartMonth()+"",rt.getFinancialYear().getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",25+"",5000+"","All","Partner name",rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversServedAndGraduated=ind.getIndicatorForNumberOfCaregiversServedAndTransferedWithinTheReportPeriodByAge().getIndicatorId();
        String[] selectedIndicatorArray={caregiversServedAndGraduated};
        List list=getSummaryStatisticsReport(sex,reportParamsForExited,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            if(subList.size()>1)
            {
                dform.setOvc_servMale25AndAbove(Integer.parseInt(subList.get(1).toString()));
                dform.setOvc_servFemale25AndAbove(Integer.parseInt(subList.get(2).toString()));
            }
        }
        return dform;
    }
    public DatimReportTemplate getDatimCaregiverServedAndExitedWithoutGraduationReportFor18To24(ReportParameterTemplate rt)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForExited={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getFinancialYear().getStartMonth()+"",rt.getFinancialYear().getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",0+"",24+"","All","Partner name",rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversServedAndGraduated=ind.getIndicatorForNumberOfCaregiversExitedWithoutGraduationByAge().getIndicatorId();
        String[] selectedIndicatorArray={caregiversServedAndGraduated};
        List list=getSummaryStatisticsReport(sex,reportParamsForExited,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            if(subList.size()>1)
            {
                dform.setOvc_servMale18To24(Integer.parseInt(subList.get(1).toString()));
                dform.setOvc_servFemale18To24(Integer.parseInt(subList.get(2).toString()));
            }
        }
        return dform;
    }
    public DatimReportTemplate getDatimCaregiverServedAndExitedWithoutGraduationReportFor25AndAbove(ReportParameterTemplate rt)
    {
        DatimReportTemplate dform=new DatimReportTemplate();
        String sex="Male";
        String[] reportParamsForExited={rt.getLevel2OuId(),rt.getLevel3OuId(),rt.getCboId(),rt.getLevel4OuId(),"All","All","All",rt.getFinancialYear().getStartMonth()+"",rt.getFinancialYear().getStartYear()+"",rt.getEndMth()+"",rt.getEndYear()+"","All","All","All","All",25+"",5000+"","All","Partner name",rt.getPartnerCode()};
        IndicatorDictionary ind=new IndicatorDictionary();
        String caregiversServedAndGraduated=ind.getIndicatorForNumberOfCaregiversExitedWithoutGraduationByAge().getIndicatorId();
        String[] selectedIndicatorArray={caregiversServedAndGraduated};
        List list=getSummaryStatisticsReport(sex,reportParamsForExited,selectedIndicatorArray);
        if(list !=null && list.size()>0)
        {
            List subList=(List)list.get(0);
            if(subList.size()>1)
            {
                dform.setOvc_servMale25AndAbove(Integer.parseInt(subList.get(1).toString()));
                dform.setOvc_servFemale25AndAbove(Integer.parseInt(subList.get(2).toString()));
            }
        }
        return dform;
    }
    public List getSummaryStatisticsReport(String sex,String[] reportParamsForActive,String[] selectedIndicatorArray)
    {
        List list=null;
        try
        {
            //SummaryStatisticsReportGenerator ssrg=new SummaryStatisticsReportGenerator();
            //list=ssrg.getOvcEnrolledSummStatistics(sex,reportParamsForActive,selectedIndicatorArray);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
}
