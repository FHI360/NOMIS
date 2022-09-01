/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.EnrollmentStatusManager;
import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.util.AppManager;
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
public class EnrollmentStatusManagementAction extends org.apache.struts.action.Action {

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
        EnrollmentStatusManagementForm esmf=(EnrollmentStatusManagementForm)form;
        HttpSession session=request.getSession();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        if(AccessManager.userHasSuperUserRole(user))
        {
            setButtonState(session,"false");
        }
        else
        {
            setButtonState(session,"true");
            //return mapping.findForward(PARAMPAGE);
        }
        
        setButtonState(session,"false");
        String userName=appManager.getCurrentUserName(session);
        String requiredAction=esmf.getActionName();
        String reportPeriod=esmf.getReportPeriod();
        setDateParametersForEnrollmentStatus(session);
        
        if(requiredAction==null)
        {
            esmf.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("updateEnrollmentStatusHistory"))
        {
            String[] periods=reportPeriod.split(":");
            EnrollmentStatusManager esm=new EnrollmentStatusManager();
            //esm.updateLastDateOfCurrentEnrollmentStatus();
            String message=""+esm.updateBeneficiaryEnrollmentStatusHistory(periods[0],periods[1],userName)+" Enrollment status records updated to history store";
            request.setAttribute("enrStatusMsg", message);
        }
        return mapping.findForward(SUCCESS);
    }
    private void setDateParametersForEnrollmentStatus(HttpSession session)
    {
        FinancialYearManager fym=new FinancialYearManager();
        List list=fym.getListOfDateParameterTemplatesForSemiAnnualReport();
        if(list==null)
        list=new ArrayList();
        session.setAttribute("dateParameterListForEnrollmentStatus", list);
        //fym.getListOfDateParameterTemplatesForSemiAnnualReport();
    }
    public void setButtonState(HttpSession session,String disabled)
    {
        session.setAttribute("enrollmentStatusBtnDisabled", disabled);
    }
}
