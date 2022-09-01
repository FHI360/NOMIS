/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DatabaseUtilities;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ExcelWriter;
import com.nomis.reports.utils.DatimReportGenerator;
import com.nomis.reports.utils.DatimReportTemplate;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.write.WritableWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class DatimReportAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String PARAMPAGE="paramPage";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DatimReportForm drf=(DatimReportForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        
        User user=appManager.getCurrentUser(session);
        if(user !=null)
        userName=user.getUsername();
        String level2OuId=drf.getLevel2OuId();
        String level3OuId=drf.getLevel3OuId();
        String level4OuId=drf.getOrganizationUnitId();
        String cboId=drf.getCboId();
        String partnerCode=null;
        String reportPeriod=drf.getReportPeriod();
        String startDate=drf.getStartDate();
        String endDate=drf.getEndDate();
        
        ouaManager.getLevel2OrganizationUnitForReports(session);
        //ouaManager.setOrganizationUnitAttributesByOuId(request, level2OuId, level3OuId, level4OuId,cboId);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
        
        System.err.println("reportPeriod is "+reportPeriod);
        if(reportPeriod !=null && reportPeriod.indexOf(":") !=-1)
        {
            String[] reportPeriodArray=reportPeriod.split(":");
            startDate=reportPeriodArray[0];
            endDate=reportPeriodArray[1];
        }
        
        if(AccessManager.isDue())
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.LICENCE_ACCESS_MSG);
            return mapping.findForward(PARAMPAGE);
        }
        else
        {
            setButtonState(session,"false","true");
        }
        String requiredAction=drf.getActionName();
        setButtonState(session,"false", "true");
        if(user ==null || user.getReportGenerationDisabled() ==AppConstant.REPORTGENERATIONDISABLED_NUM)
        {
            level2OuId=null;
            level3OuId=null;
            level4OuId=null;
            cboId=null;
            partnerCode=null;
            ouaManager.emptyReportAttributes(request);
            setButtonState(session,"true", "true");
            requiredAction=null;
        }
        //run update on current records in enrollmentstatushistory table
        DatabaseUtilities dbUtil=new DatabaseUtilities();
        dbUtil.updateEnrollmentStatusHistory();
                
        /*if(user ==null)
        System.err.println("User is null");
        else
        System.err.println("User is not null"+user.getFirstName()+" "+user.getLastName());
        */
        String reportType=drf.getReportType();
        setDateParametersForEnrollmentStatus(session);        
        if(requiredAction==null)
        {
            //DateManager.getNextMonth(2019, 03, 23,0);
            drf.reset(mapping, request);
            if(user !=null)
            {
                System.err.println("User is not null");
                if(user.getReportGenerationDisabled() !=AppConstant.REPORTGENERATIONDISABLED_NUM)
                {
                    ouaManager.setOrganizationUnitAttributesForReports(session,user.getReportLevel2OuId(),level3OuId,level4OuId,cboId,user.getReportPartner());
                }
            }
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equalsIgnoreCase("cboList"))
        {
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            /*ouaManager.getLevel3OrganizationUnitForReports(session,level2OuId);
            ouaManager.getLevel4OrganizationUnitForReports(session,null);
            ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            */
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            /*ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            ouaManager.setAssignedLevel3Ou(session, drf.getCboId());
            ouaManager.setLevel4OuList(session, null);*/
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            //ouaManager.setLevel4OuList(session, level3OuId);
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equals("report"))
        {
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setCboId(cboId);
            rpt.setLevel2OuId(level2OuId);
            rpt.setLevel3OuId(level3OuId);
            rpt.setLevel4OuId(level4OuId);
            if(startDate !=null && startDate.indexOf("-") !=-1)
            {
                String[] startDateArray=startDate.split("-");
                String[] endDateArray=startDate.split("-");
                int startMth=Integer.parseInt(startDateArray[1]);
                int startYear=Integer.parseInt(startDateArray[0]);
                int endMth=Integer.parseInt(endDateArray[1]);
                int endYear=Integer.parseInt(endDateArray[0]);
                rpt.setStartMth(startMth);
                rpt.setStartYear(startYear);
                rpt.setEndMth(endMth);
                rpt.setEndYear(endYear);
            }
            //startDate=DateManager.processMthDayYearToMysqlFormat(startDate);
            //endDate=DateManager.processMthDayYearToMysqlFormat(endDate);
                
            rpt.setStartDate(startDate);
            rpt.setEndDate(endDate);
            String period=getPeriodLabel(startDate,endDate);
            request.setAttribute("datimReportPeriod", period);
            request.removeAttribute("reportPeriod");
            session.removeAttribute("reportPeriod");
            if(reportType !=null && reportType.equalsIgnoreCase("datim2017"))
            {
                DatimReportGenerator drg=new DatimReportGenerator();
                
                DatimReportTemplate dform=drg.getDatimReport(rpt);
                //Datim2017Form dform=drg.getDatimReport(rpt);
                //ReportTemplate rt=drg.getDatimReport(rpt,startDate, endDate);
                
                /*dform.setOvc_servActive(rt.getOvc_servActive());
                dform.setOvc_servGraduated(rt.getOvc_servGraduated());
                dform.setOvc_servTransfered(rt.getOvc_servTransfered());
                dform.setOvc_servExitedWithoutGraduation(rt.getOvc_servExitedWithoutGraduation());
                dform.setOvc_servNumerator(rt.getOvc_servNumerator());
                //dform.setOvc_servGraduated(rt.getOvc_servGraduated());*/
                
                session.setAttribute("datimResultForm",dform);
                return mapping.findForward(SUCCESS);
            }
            else if(reportType.equalsIgnoreCase("datimExcelDownload"))
            {
                ExcelWriter ew=new ExcelWriter();
                String fileName="Datim_report";
                List datimExcelList=new ArrayList();
                DatimReportGenerator drg=new DatimReportGenerator();
                List datimFormList=drg.getDatimFormList(rpt);
                if(datimFormList !=null && !datimFormList.isEmpty())
                {
                    DatimReportTemplate drt=null;
                    for(Object obj:datimFormList)
                    {
                        drt=(DatimReportTemplate)obj;
                        datimExcelList.addAll(drg.convertDatimReportTemplateToReportTemplates(drt));
                    }
                }
                
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
                OutputStream os=response.getOutputStream();
                WritableWorkbook wworkbook=ew.writeDatim2017ReportToExcel(os, datimExcelList);
                                
                if(wworkbook !=null)
                {
                    wworkbook.write();
                    wworkbook.close();
                }
                os.close();
                return null;
            }
            else if(reportType.equalsIgnoreCase("datimExcelFlatFileDownload"))
            {
                ExcelWriter ew=new ExcelWriter();
                String fileName="Datim_Flat_file";
                List datimExcelList=new ArrayList();
                DatimReportGenerator drg=new DatimReportGenerator();
                List partnerList=util.getHouseholdEnrollmentDaoInstance().getDistinctPartnerCodes(rpt);
                if(partnerList !=null)
                {
                    partnerCode=null;
                    for(int i=0; i<partnerList.size(); i++)
                    {
                        partnerCode=partnerList.get(i).toString();
                        rpt.setPartnerCode(partnerCode);
                        List datimFormList=drg.getDatimFormList(rpt);
                        if(datimFormList !=null && !datimFormList.isEmpty())
                        {
                            DatimReportTemplate drt=null;
                            for(Object obj:datimFormList)
                            {
                                drt=(DatimReportTemplate)obj;
                                datimExcelList.addAll(drg.convertDatimReportTemplateToReportTemplates(drt));
                            }
                        }
                    }
                }
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
                OutputStream os=response.getOutputStream();
                WritableWorkbook wworkbook=ew.writeDatimFlatFileToExcel(os, datimExcelList);
                                
                if(wworkbook !=null)
                {
                    wworkbook.write();
                    wworkbook.close();
                }
                os.close();
                return null;
            }
        }
        return mapping.findForward(PARAMPAGE);
    }
    private String getPeriodLabel(String startDate,String endDate)
    {
        String periodLabel="";
        if(startDate !=null && endDate !=null)
        {
            
            String[] startdateArray=startDate.split("-");
            String[] enddateArray=endDate.split("-");
            if((startdateArray !=null && startdateArray.length>2) && (enddateArray !=null && enddateArray.length>2))
            {
                DateManager dm=new DateManager();
                String startYear=startdateArray[0];
                String startMth=startdateArray[1];
                String startDay=startdateArray[2];
                String endYear=enddateArray[0];
                String endMth=enddateArray[1];
                String endDay=enddateArray[2];
                periodLabel=startDay+" "+dm.getMonthAsString(Integer.parseInt(startMth))+" "+startYear+" to "+endDay+" "+dm.getMonthAsString(Integer.parseInt(endMth))+" "+endYear;
            }
        }
        return periodLabel;
    }
    private void setDateParametersForEnrollmentStatus(HttpSession session)
    {
        FinancialYearManager fym=new FinancialYearManager();
        List list=fym.getListOfDateParameterTemplatesForSemiAnnualReport();
        if(list==null)
        list=new ArrayList();
        session.setAttribute("dateParameterListForDatim", list);
        //fym.getListOfDateParameterTemplatesForSemiAnnualReport();
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("datimSaveDisabled", saveDisabled);
    }
}
