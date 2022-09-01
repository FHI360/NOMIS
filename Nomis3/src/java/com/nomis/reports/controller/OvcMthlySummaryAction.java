/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.legacydatamanagement.utils.DateManager;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.reports.utils.MonthlySummaryFormReport;
import com.nomis.reports.utils.OvcReportManager;
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
public class OvcMthlySummaryAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

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
        OvcMthlySummaryForm reportForm=(OvcMthlySummaryForm)form;
        HttpSession session=request.getSession();
        AppUtility appUtil=new AppUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        String level2OuId=reportForm.getLevel2OuId();
        String level3OuId=reportForm.getLevel3OuId();
        String level4OuId=reportForm.getOrganizationUnitId();
        String cboId=reportForm.getCboId();
        String partnerCode=reportForm.getPartnerCode();
        int startMth=reportForm.getStartMth();
        int startYear=reportForm.getStartYear();
        int endMth=reportForm.getEndMth();
        int endYear=reportForm.getEndYear();
        int ageSegType=reportForm.getReportType();
        
        ouaManager.getLevel2OrganizationUnitForReports(session);
        ouaManager.setOrganizationUnitAttributesByOuId(request, level2OuId, level3OuId, level4OuId,cboId);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
        
        String requiredAction=reportForm.getActionName();
        
        if(requiredAction==null)
        {
            reportForm.reset(mapping, request);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("cboList"))
        {
            ouaManager.getLevel3OrganizationUnitForReports(session,level2OuId);
            ouaManager.getLevel4OrganizationUnitForReports(session,null);
            ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            ouaManager.setAssignedLevel3Ou(session, reportForm.getCboId());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.getLevel4OrganizationUnitForReports(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equals("monthlysummary"))
        {
            MonthlySummaryFormReport msr=new MonthlySummaryFormReport();
            DateManager dm=new DateManager();
            OvcReportManager orm=new OvcReportManager();
            List paramList=new ArrayList();
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setLevel2OuId(level2OuId);
            rpt.setLevel3OuId(level3OuId);
            rpt.setLevel4OuId(level4OuId);
            rpt.setCboId(cboId);
            rpt.setStartDate(dm.getStartDate(startMth, startYear)); 
            rpt.setEndDate(dm.getEndDate(endMth, endYear));
            rpt.setPartnerCode(partnerCode);
            
            paramList.add(level2OuId);
            paramList.add(level3OuId);
            paramList.add(cboId);
            paramList.add(level4OuId);
            paramList.add(startMth);
            paramList.add(startYear);
            paramList.add(endMth);
            paramList.add(endYear);
            paramList.add(partnerCode);
            List list=msr.getOvcMthlySummaryData(rpt,ageSegType+"");
            //orm.getOvcMthlySummaryData(session,rpt,paramList,ageSegType+"");
            session.setAttribute("mthSummaryReportTemplateList", list);
            if(ageSegType==1)
            return mapping.findForward("newReport");    
        }
        return mapping.findForward(SUCCESS);
    }
    private void generateLgaList(String stateCode,HttpServletRequest request)
    {
        AppUtility appUtil=new AppUtility();
        /*AccessManager acm=new AccessManager();
        User user=appUtil.getUser(request.getSession());
        List emptyList=new ArrayList();
        HttpSession session=request.getSession();
        List lgaList=new ArrayList();
        if(user !=null)
        lgaList =acm.getListOfLgasAssignedToUser(user,stateCode);*/
        HttpSession session=request.getSession();
        List emptyList=new ArrayList();
        List lgaList=new ArrayList();
        session.setAttribute("lgaList", lgaList);
        session.setAttribute("MthlySummOrgList", emptyList);
    }
}
