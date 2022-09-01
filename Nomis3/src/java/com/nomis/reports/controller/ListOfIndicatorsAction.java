/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatabaseUtilities;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.Indicator;
import com.nomis.reports.utils.IndicatorWarehouse;
import com.nomis.reports.utils.ListOfIndicatorsReportGenerator;
import com.nomis.reports.utils.ReportIndicatorsManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class ListOfIndicatorsAction extends org.apache.struts.action.Action {

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
            throws Exception 
    {
        ListOfIndicatorsForm lform=(ListOfIndicatorsForm)form;
        HttpSession session=request.getSession();
        AppUtility appUtil=new AppUtility();
        DaoUtility util=new DaoUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        
        String userName=appManager.getCurrentUserName(session);
        User user=appManager.getCurrentUser(session);
        if(user !=null)
        userName=user.getUsername();
        else
        System.err.println("user is null. user name is "+userName);
        String level2OuId=lform.getLevel2OuId();
        String level3OuId=lform.getLevel3OuId();
        String level4OuId=lform.getOrganizationUnitId();
        String cboId=lform.getCboId();
        String partnerCode=lform.getPartnerCode();
        
               
        //run update on current records in childenrollment table
        DatabaseUtilities dbUtil=new DatabaseUtilities();
        dbUtil.updateBaselineAndCurrentAgeUnitsInChildEnrollment();
        
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
        //ouaManager.setOrganizationUnitAttributesForReports(session, level2OuId,level3OuId,level4OuId,cboId,partnerCode);
        //ouaManager.getLevel2OrganizationUnitForReports(session);
        ouaManager.setOrganizationUnitAttributesByOuId(request, level2OuId, level3OuId, level4OuId,cboId);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
        
        /*ouaManager.setOrganizationUnitAttributesForReports(session);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
        ouaManager.setOrganizationUnitAttributesByOuId(request, level2OuId, level3OuId, level4OuId,cboId);*/
        String requiredAction=lform.getActionName();
        String startAge=lform.getStartAge();
        String endAge=lform.getEndAge();
        
        String[] reqParamArray=null;
        String reqParam=request.getParameter("reqParam");
        if(reqParam !=null && reqParam.indexOf(":") !=-1)
        {
            reqParamArray=reqParam.split(":");
            requiredAction=reqParamArray[2];
            //System.err.println("reqParam is "+reqParam);
        }
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
        
        ReportIndicatorsManager ssrm=new ReportIndicatorsManager();
        List list=ssrm.getSummaryStatisticsIndicators();
        request.setAttribute("sunstatIndictors", list);
        System.err.println("requiredAction is "+requiredAction);
        List ageList=new ArrayList();
        if(requiredAction==null)
        {
            lform.reset(mapping, request);
            ageList.clear();
            ageList=appUtil.getAgeList(0,25);
            session.setAttribute("ageList", ageList);
            List endAgeList=new ArrayList();
            endAgeList.addAll(ageList);
            endAgeList.add("24+");
            session.setAttribute("endAgeList", endAgeList);
            if(user !=null)
            {
                //System.err.println("User is not null");
                if(user.getReportGenerationDisabled() !=AppConstant.REPORTGENERATIONDISABLED_NUM)
                {
                    ouaManager.setOrganizationUnitAttributesForReports(session,user.getReportLevel2OuId(),level3OuId,level4OuId,cboId,user.getReportPartner());
                }
            }
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equals("endAge"))
        {
            session.removeAttribute("summaryStatistics");
            List endAgeList=new ArrayList();
            if(startAge !=null)
            {
                if(startAge.equalsIgnoreCase("All") || startAge.indexOf("+") !=-1)
                ageList.add("All");
                else if(startAge.equalsIgnoreCase("&lt1"))
                {
                    endAgeList.clear();
                    endAgeList.add(0);
                }
                else
                {
                    int startAgeInNumber=Integer.parseInt(startAge);
                    endAgeList=appUtil.getAgeListAbove18(startAgeInNumber);
                }
                session.setAttribute("endAgeList", endAgeList);
            }
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equalsIgnoreCase("cboList"))
        {
            //ouaManager.getLevel3OrganizationUnitForReports(session,level2OuId);
            //ouaManager.getLevel4OrganizationUnitForReports(session,null);
            //ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            //if(user !=null && user.getReportGenerationDisabled() !=1)
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            //if(user !=null && user.getReportGenerationDisabled() !=1)
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            //ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            //ouaManager.setAssignedLevel3Ou(session, lform.getCboId());
            //ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            //if(user !=null && user.getReportGenerationDisabled() !=1)
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            //ouaManager.getLevel4OrganizationUnitForReports(session, level3OuId);
            return mapping.findForward(PARAMPAGE);
        }
        else if(requiredAction.equals("summstatreport"))
        {
            DateManager dm=new DateManager();
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setReportType(AppConstant.LISTOFINDICATORS_REPORTTYPE);
            //lform.setStartAge(0);
            //lform.setEndAge(17);
            rpt.setCboId(cboId);
            rpt.setLevel2OuId(level2OuId);
            rpt.setLevel3OuId(level3OuId);
            rpt.setLevel4OuId(level4OuId);
            if(lform.getStartAge() ==null || lform.getStartAge().equalsIgnoreCase("All"))
            rpt.setStartAge(0);
            else if(lform.getStartAge().equalsIgnoreCase("&lt1"))
            {
                rpt.setStartAge(0);
                rpt.setEndAge(0);
            }
            else
            rpt.setStartAge(Integer.parseInt(lform.getStartAge()));
            if((lform.getEndAge() ==null || lform.getEndAge().indexOf("+") !=-1) && !lform.getStartAge().equalsIgnoreCase("&lt1"))
            rpt.setEndAge(25);
            else
            rpt.setEndAge(Integer.parseInt(lform.getEndAge()));
            rpt.setStartDate(DateManager.processMthDayYearToMysqlFormat(lform.getStartDate()));
            rpt.setEndDate(DateManager.processMthDayYearToMysqlFormat(lform.getEndDate()));
            rpt.setStartMth(lform.getStartMth());
            rpt.setStartYear(lform.getStartYear());
            rpt.setEndMth(lform.getEndMth());
            rpt.setEndYear(lform.getEndYear());
            
            String[] indicators=lform.getIndicatorIndexes();
            if(indicators==null)
            {
                request.setAttribute("sumstatmsg", "You must select at least one indicator");
                return mapping.findForward("paramPage");
            }
            ListOfIndicatorsReportGenerator lrg=new ListOfIndicatorsReportGenerator();
            List resultList=lrg.getOvcEnrolledSummStatistics(rpt, indicators);
            
            String[] dateParams={rpt.getStartDate(),rpt.getEndDate()};
            List dateList=dm.getDateLabels(dateParams);
            String reportPeriod=dateList.get(0).toString()+" to "+dateList.get(1).toString();
            String ageLabel=rpt.getStartAge()+" to "+rpt.getEndAge();
            if(rpt.getStartAge()==0 && rpt.getEndAge()==0)
            ageLabel=" <1 ";
            session.setAttribute("listOfIndicatorsResult", resultList);
            session.setAttribute("reportParam", rpt);
            request.setAttribute("reportPeriod", reportPeriod);
            request.setAttribute("ageLabel", ageLabel);
            lform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equals("summstatList"))
        {
            List resultList=new ArrayList();
            DateManager dm=new DateManager();
            ReportParameterTemplate rpt=(ReportParameterTemplate)session.getAttribute("reportParam");
            String indicatorName="";
            String target="childRegister";
            if(reqParamArray !=null)
            {
                //reqParam=${rt.indicatorCode}:Female:summstatList"
                String indicatorCode=reqParamArray[0];
                IndicatorWarehouse indwh=new IndicatorWarehouse();
                Indicator indicator=indwh.getIndicatorById(indicatorCode);
                if(indicator !=null)
                {
                    indicatorName=indicator.getIndicatorName();
                    System.err.println("indicator.getIndicatorType() is "+indicator.getIndicatorType());
                    if(indicator.getIndicatorType() !=null && indicator.getIndicatorType().equalsIgnoreCase(AppConstant.HOUSEHOLD_TYPE))
                    target="householdRegister";
                    if(indicator.getIndicatorType() !=null && indicator.getIndicatorType().equalsIgnoreCase(AppConstant.CAREGIVER_TYPE))
                    target="cgiverRegister";
                }
                String sex=reqParamArray[1];
                ListOfIndicatorsReportGenerator lrg=new ListOfIndicatorsReportGenerator();
                resultList=lrg.getOvcList(rpt, sex,indicatorCode);
                
            }
            String[] dateParams={rpt.getStartDate(),rpt.getEndDate()};
            List dateList=dm.getDateLabels(dateParams);
            String reportPeriod=dateList.get(0).toString()+" to "+dateList.get(1).toString();
            System.err.println("target is "+target);
            if(target.equalsIgnoreCase("childRegister"))
            {
                session.setAttribute("ovcListForRegister", resultList);
                if(indicatorName !=null && indicatorName.trim().length()>0)
                request.setAttribute("indicatorName", indicatorName);
                request.setAttribute("reportPeriod", reportPeriod);
            }
            else if(target.equalsIgnoreCase("cgiverRegister"))
            {
                request.setAttribute("reportType", "Caregiver Register");
                session.setAttribute("cgiverListForRegister", resultList);
                request.setAttribute("indicatorName", indicatorName);
            }
            else if(target.equalsIgnoreCase("householdRegister"))
            {
                request.setAttribute("reportType", "Household Register");
                session.setAttribute("hheListForRegister", resultList);
                request.setAttribute("indicatorName", indicatorName);
            }
            return mapping.findForward(target);
        }
        return mapping.findForward(PARAMPAGE);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("licSaveDisabled", saveDisabled);
    }
}
