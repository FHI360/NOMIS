/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.communityworker.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.CommunityWorker;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.AppManager;
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
public class CommunityWorkerAction extends org.apache.struts.action.Action {

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
        CommunityWorkerForm cwf=(CommunityWorkerForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        String userName=appManager.getCurrentUserName(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getAdminSetupUtilityPriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false","true"); 
        }
        else
        {
            setButtonState(session,"true","true");
            cwf.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        String requiredAction=cwf.getActionName();
        setEnumeratorsRegistrationList(session);
        String param=request.getParameter("p");
        if(param !=null)
        requiredAction=param;
        setButtonState(session,"false","true");
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
           cwf.reset(mapping, request); 
           return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("details"))
        {
            String communityWorkerId=request.getParameter("id");
            cwf.reset(mapping, request);
            CommunityWorker er=util.getCommunityWorkerDaoInstance().getCommunityWorker(communityWorkerId);
            if(er !=null)
            {
                cwf.setFirstName(er.getFirstName());
                cwf.setDesignation(er.getDesignation());
                cwf.setCommunityWorkerId(communityWorkerId);
                cwf.setLevel4OuId(er.getLevel4OuId());
                cwf.setSex(er.getSex());
                cwf.setSurname(er.getSurname());
                cwf.setEnrollmentStatus(er.getEnrollmentStatus());
                setButtonState(session,"true","false");
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
           CommunityWorker er=getEnumeratorsRegistration(cwf,userName);
           util.getCommunityWorkerDaoInstance().saveCommunityWorker(er);
           setEnumeratorsRegistrationList(session);
           cwf.reset(mapping, request); 
           return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
           CommunityWorker er=getEnumeratorsRegistration(cwf,userName);
           util.getCommunityWorkerDaoInstance().updateCommunityWorker(er);
           setEnumeratorsRegistrationList(session);
           cwf.reset(mapping, request);
           return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
           //EnumeratorsRegistration er=getEnumeratorsRegistration(cwf,userName);
            setEnumeratorsRegistrationList(session);
            cwf.reset(mapping, request);
           return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
    private void setEnumeratorsRegistrationList(HttpSession session) throws Exception
    {
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        DaoUtility util=new DaoUtility();
        session.removeAttribute("allCommunityWorkerList");
        List list=util.getCommunityWorkerDaoInstance().getAllCommunityWorkers();
        if(session !=null && !list.isEmpty())
        {
            session.setAttribute("allCommunityWorkerList", list);
        }
    }
    private CommunityWorker getEnumeratorsRegistration(CommunityWorkerForm cwf,String userName)
    {
        CommunityWorker er=new CommunityWorker();
        er.setDateCreated(DateManager.getCurrentDateInstance());
        er.setCommunityWorkerId(cwf.getCommunityWorkerId());
        er.setDesignation(cwf.getDesignation());
        er.setFirstName(cwf.getFirstName());
        er.setLastModifiedDate(DateManager.getCurrentDateInstance());
        er.setLevel4OuId(cwf.getLevel4OuId());
        er.setRecordedBy(userName);
        er.setSex(cwf.getSex());
        er.setSurname(cwf.getSurname());
        er.setEnrollmentStatus(cwf.getEnrollmentStatus()); 
        return er;
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("enumeratorSavedisabled", saveDisabled);
        session.setAttribute("enumeratorModifyDisabled", modifyDisabled);
    }
}
