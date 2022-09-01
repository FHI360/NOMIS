/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.DatimReportDao;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.Datim2017Form;
import com.nomis.reports.utils.DatimReportGenerator;
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
public class DatimDataGenerationAction extends org.apache.struts.action.Action {

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
            throws Exception 
    {
        DatimDataGenerationForm ddgform=(DatimDataGenerationForm)form;
        AppUtility appUtil=new AppUtility();
        DaoUtility util=new DaoUtility();
        DateManager dm=new DateManager();
        //ddgform
        HttpSession session=request.getSession();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        ouaManager.getLevel2OrganizationUnitForReports(session);
        ReportParameterTemplate rpt=new ReportParameterTemplate();
        
        
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        //User user=appUtil.getCurrentUser(session);
        String userName=null;
        if(user !=null)
        userName=user.getUsername();
        //if(!acm.isUserInRole("cirb"))
        //session.setAttribute("datimGenButtonDisabled", "true");
        //else
        session.setAttribute("datimGenButtonDisabled", "false");
        
        //acm.setLevel2OuListForReports(session);
        
        int startMth=appUtil.getMonthAsNumber(ddgform.getStartMth());
        int startYear=ddgform.getStartYear();
        int endMth=appUtil.getMonthAsNumber(ddgform.getEndMth());
        int endYear=ddgform.getEndYear();
                
        rpt.setStartMth(startMth);
        rpt.setStartYear(startYear);
        rpt.setEndMth(endMth);
        rpt.setEndYear(endYear);
        rpt.setStartDate(dm.getStartDate(startMth, startYear));
        rpt.setEndDate(dm.getEndDate(endMth, endYear));
        String[] selectedLevel2Ous=ddgform.getLevel2OuCodes();
        
        
        String requiredAction=ddgform.getActionName();
        List listOfMonths=dm.generateMonths(1);
        List listOfYears=DateManager.generateYears();
        session.setAttribute("generatedYears", listOfYears);
        session.setAttribute("generatedMonths", listOfMonths);
        //String currentUser=appUtil.getCurrentUser(session);
        
        //AppManager appManager=new AppManager();
        //User user=appManager.getCurrentUser(session);
                
        /*List partnerList=acm.getPartnerListForReports(session);
        session.setAttribute("partnerList", partnerList);*/
        System.err.println("requiredAction is "+requiredAction);
        
        if(requiredAction==null)
        {
            ddgform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level2OuList"))
        {
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("deleteData"))
        {
            String level2OuId=null;
            int count=0;
            String level2OuName=null;
            DatimReportDao drtdao=util.getDatimReportDaoInstance();
            for(int j=0; j<selectedLevel2Ous.length; j++)
            {
               level2OuId=selectedLevel2Ous[j];
               OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level2OuId);
               if(ou !=null)
               level2OuName=ou.getName();
               count+=drtdao.deleteAllDatimReportTemplates(level2OuName);
            }
            ddgform.reset(mapping, request);
            System.err.println(count+" deleted");
        }
        else if(requiredAction.equalsIgnoreCase("generateData"))
        {
            try  
            {
                rpt.setAdultAgeDisaggregated(false);
                DatimReportDao drtdao=util.getDatimReportDaoInstance();
                String level2OuId=null;
                DatimReportGenerator drg=new DatimReportGenerator();
                
                if(selectedLevel2Ous !=null && selectedLevel2Ous.length>0)
                {
                        List datimFormList=new ArrayList();
                    for(int j=0; j<selectedLevel2Ous.length; j++)
                    {
                       level2OuId=selectedLevel2Ous[j];
                        //set the individual level2OuId and get the datim forms
                       rpt.setLevel2OuId(level2OuId);
                       datimFormList=drg.getDatimFormList(rpt);
                       drtdao.saveDatimReportData(datimFormList,userName);
                    }
                }
             }
               catch(Exception ex)
                {
                    NomisLogManager.logStackTrace(ex);
                    //ex.printStackTrace();
                }
                ddgform.reset(mapping, request);
                return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
