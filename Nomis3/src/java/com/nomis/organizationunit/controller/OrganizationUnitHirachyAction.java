/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.organizationunit.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitHierarchyManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.OrganizationUnitHierarchyDao;
import com.nomis.ovc.dao.OrganizationUnitHierarchyDaoImpl;
import com.nomis.ovc.metadata.OrganizationUnitHierarchy;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
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
public class OrganizationUnitHirachyAction extends org.apache.struts.action.Action {

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
        OrganizationUnitHirachyForm ouhform=(OrganizationUnitHirachyForm)form;
        OrganizationUnitHierarchyDao ouhdao=new OrganizationUnitHierarchyDaoImpl();
        HttpSession session=request.getSession();
        
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getOrganizationUnitUpdatePriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        
        String requiredAction=ouhform.getActionName();
        String reqParam=request.getParameter("p");
        if(reqParam !=null)
        requiredAction=reqParam;
        setButtonState(session,"false","true");
        setOuHierarchyRecords(session);
        OrganizationUnitHierarchyManager.setOrganizationUnitHierachyAttributes(session);
        if(requiredAction==null)
        {
            ouhform.reset(mapping, request);
            ouhform=getOuForm(ouhform);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("details"))
        {
           String uid=request.getParameter("id");
            OrganizationUnitHierarchy ouh=ouhdao.getOrganizationUnitHierarchy(uid);
            if(ouh !=null)
            {
                ouhform.reset(mapping, request);
                ouhform.setOulevel(ouh.getOulevel());
                ouhform.setDescription(ouh.getDescription());
                ouhform.setOuname(ouh.getName());
                ouhform.setUid(ouh.getUid());
                setButtonState(session,"true","false");
            }
            return mapping.findForward(SUCCESS);
           
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            ouhdao.saveOrganizationUnitHierarchy(getFilledOrganizationUnitHierarchy(ouhform));
        }
        else if(requiredAction.equalsIgnoreCase("update"))
        {
            ouhdao.updateOrganizationUnitHierarchy(getFilledOrganizationUnitHierarchy(ouhform));
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            ouhdao.deleteOrganizationUnitHierarchy(getFilledOrganizationUnitHierarchy(ouhform));
        }
        OrganizationUnitHierarchyManager.setOrganizationUnitHierachyAttributes(session);
        ouhform.reset(mapping, request);
        ouhform=getOuForm(ouhform);
        setOuHierarchyRecords(session);
        return mapping.findForward(SUCCESS);
    }
    private OrganizationUnitHierarchy getFilledOrganizationUnitHierarchy(OrganizationUnitHirachyForm ouhform)
    {
        OrganizationUnitHierarchy ouh=new OrganizationUnitHierarchy();
        ouh.setDateCreated(DateManager.getCurrentDateInstance());
        ouh.setDescription(ouhform.getDescription());
        ouh.setLastModifiedDate(DateManager.getCurrentDateInstance());
        ouh.setName(ouhform.getOuname());
        ouh.setUid(ouhform.getUid());
        ouh.setOulevel(ouhform.getOulevel());
        ouh.setRecordedBy("siaka");
        return ouh;
    }
    private OrganizationUnitHirachyForm getOuForm(OrganizationUnitHirachyForm ouhform) throws Exception
    {
        OrganizationUnitHierarchyDao ouhdao=new OrganizationUnitHierarchyDaoImpl();
        int ouLevel=ouhdao.getMaximumOuLevel();
        ouLevel=ouLevel+1;
        ouhform.setOulevel(ouLevel);
        return ouhform;
    }
    private void setOuHierarchyRecords(HttpSession session) throws Exception
    {
        OrganizationUnitHierarchyDao ouhdao=new OrganizationUnitHierarchyDaoImpl();
        List list=ouhdao.getAllOrganizationUnitHierarchyRecords();
        if(list==null)
        list=new ArrayList();
        session.setAttribute("ouhList", list);
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        if(AppUtility.isMetadataAccessEnabled())
        {
            session.setAttribute("ouhBtnSaveDisabled", saveDisabled);
            session.setAttribute("ouhBtnModifiedDisabled", modifyDisabled);
        }
        else
        {
            session.setAttribute("ouhBtnSaveDisabled", "true");
            session.setAttribute("ouhBtnModifiedDisabled", "true");
        }
    }
}
