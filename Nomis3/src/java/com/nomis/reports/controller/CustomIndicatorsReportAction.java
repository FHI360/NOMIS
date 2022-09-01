/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.CustomIndicatorsReportDao;
import com.nomis.ovc.dao.CustomIndicatorsReportDaoImpl;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ExcelWriter;
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
public class CustomIndicatorsReportAction extends org.apache.struts.action.Action {

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
        CustomIndicatorReportForm cirform=(CustomIndicatorReportForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        CustomIndicatorsReportDao cirdao=new CustomIndicatorsReportDaoImpl();
        AppUtility appUtil=new AppUtility();
        
        AccessManager acm=new AccessManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        //User user=appUtil.getCurrentUser(session);
        String userName=null;
        if(user !=null)
        userName=user.getUsername();
        
        //if(currentUser==null)
        //return mapping.findForward("paramPage");
        //return mapping.findForward("login");
        //User user=appUtil.getCurrentUser(session);
        //util.getUserDaoInstance().getUser(currentUser);
        setPartnerList(user,session);
        
        String requiredAction=cirform.getActionName();
        String period=cirform.getPeriod();
        String[] selectedLevel2Ous=cirform.getLevel2Ous();
        
        List periodList=cirdao.getDistinctPeriods();
        if(periodList==null)
        periodList=new ArrayList();
        session.setAttribute("cirperiodList", periodList);
        
        String partnerCode=cirform.getPartnerCode();
        List level2OuList=new ArrayList();
        List level2OuIdList=cirdao.getDistinctLevel2OusByPartner(partnerCode);//getDistinctStates();
        if(period !=null && !period.equalsIgnoreCase("select"))
        level2OuIdList=cirdao.getDistinctLevel2OusByPeriodAndPartner(partnerCode,period);
        if(level2OuIdList !=null)
        {
            String uid=null;
            for(Object obj:level2OuIdList)
            {
                uid=(String)obj;
                OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(uid);
                if(ou !=null)
                level2OuList.add(ou);
            }
        }
        session.setAttribute("cirLevel2OuList",level2OuList);
        if(user !=null)
        System.err.println("user nsme is "+user.getFirstName()+" "+user.getLastName());
        setButtonState(session,"false", "true");
        if(user ==null || user.getReportGenerationDisabled() ==AppConstant.REPORTGENERATIONDISABLED_NUM)
        {
            System.err.println("user is NULL");
            List emptyList=new ArrayList();
            session.setAttribute("cirLevel2OuList",emptyList);
            session.setAttribute("userAssignedPartners", emptyList);
            setButtonState(session,"true", "true");
            requiredAction=null;
         }
        List yearList=DateManager.generateYears();
        session.setAttribute("yearList", yearList);
        
        
        if(requiredAction==null)
        {
            return mapping.findForward("paramPage");
        }
        else if(requiredAction.equalsIgnoreCase("level2OuList"))
        {
            return mapping.findForward("paramPage");
        }
        else if(requiredAction.equalsIgnoreCase("downloadReport"))
        {
            try
                {
                    String fileName="Custom_Indicators_multiple"+DateManager.getCurrentDate();
                    
                    List mainList=new ArrayList();
                    if(selectedLevel2Ous !=null && selectedLevel2Ous.length>0)
                    {
                        String level2OuName=null;
                        String level2OuId=null;
                        List list=null;
                        if(selectedLevel2Ous.length==1)
                        {
                            level2OuId=selectedLevel2Ous[0];
                            OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level2OuId);
                            if(ou !=null)
                            {
                                level2OuName=ou.getName();
                                level2OuName=level2OuName.replaceAll(" ", "_");
                                fileName="Custom_Indicators_"+level2OuName+DateManager.getCurrentDate();
                            }
                        }
                        
                        for(int j=0; j<selectedLevel2Ous.length; j++)
                        {
                           level2OuId=selectedLevel2Ous[j];
                           list=cirdao.getCustomIndicatorsReportsByLevel2OuPartnerAndPeriod(level2OuId,partnerCode,period);
                           if(list !=null)
                           mainList.add(list);
                           System.err.println("Custom_Indicators size is "+mainList.size()); 
                        }
                        
                    }  
                    
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
                    OutputStream os=response.getOutputStream();
                    ExcelWriter ew=new ExcelWriter();
                    WritableWorkbook wworkbook=ew.writeQuarterlyReportToExcel(os, mainList) ;
                    //WritableWorkbook wworkbook=ew.writeRevisedQuarterlyReportTemplateToExcel(os, mainList) ;
                    
                    if(wworkbook !=null)
                    {
                        wworkbook.write();
                        wworkbook.close();
                    }
                    os.close();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            return null;//mapping.findForward("paramPage");
        }
        return mapping.findForward("paramPage");
    }
    private void setPartnerList(User user,HttpSession session)
    {
        String partnerCodes=null;
        DaoUtility util=new DaoUtility();
        try
        {
            List partnerList=new ArrayList();
            partnerList=util.getPartnerDaoInstance().getAllPartners(); 
            session.setAttribute("userAssignedPartners", partnerList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("cirSaveDisabled", saveDisabled);
    }
        //return mapping.findForward(SUCCESS);
    //}
}
