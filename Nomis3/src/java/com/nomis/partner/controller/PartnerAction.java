/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.partner.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.Partner;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.PartnerDao;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
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
public class PartnerAction extends org.apache.struts.action.Action {

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
        PartnerForm psform=(PartnerForm)form;
        String moduleName="Partner setup";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getAdminSetupUtilityPriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        String requiredAction=psform.getActionName();
        String param=request.getParameter("p");
        if(param !=null)
        requiredAction=param;
        
        PartnerDao pdao=util.getPartnerDaoInstance();
        getAllPartners(session);
        //setButtonState(session,"false","true");
        if(requiredAction==null)
        {
            form.reset(mapping, request);

            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
           Partner partner= getPartner(psform);
            pdao.savePartner(partner);
            saveUserActivity(userName,moduleName,"Saved new Partner record with Id "+partner.getPartnerName());
            getAllPartners(session);
            psform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            Partner partner= getPartner(psform);
            pdao.updatePartner(getPartner(psform));
            saveUserActivity(userName,moduleName,"Modified Partner record with Id "+partner.getPartnerName());
            getAllPartners(session);
            psform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("de"))
        {
            Partner partner= getPartner(psform);
            String partnerCode=request.getParameter("id");
            psform.setPartnerCode(partnerCode);
            pdao.deletePartner(partner);
            saveUserActivity(userName,moduleName,"Requested Partner record with Id "+partner.getPartnerName()+" be deleted");
            getAllPartners(session);
            psform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("ed"))
        {
            String partnerCode=request.getParameter("id");
            Partner partner=pdao.getPartner(partnerCode);
            if(partner !=null)
            {
                psform.setPartnerCode(partnerCode);
                psform.setPartnerName(partner.getPartnerName());
                psform.setDatimId(partner.getDatimId());
                setButtonState(session,"true","false");
            }
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private Partner getPartner(PartnerForm psform)
    {
        Partner partner=new Partner();
        partner.setPartnerCode(psform.getPartnerCode());
        partner.setPartnerName(psform.getPartnerName());
        partner.setDatimId(psform.getDatimId());
        partner.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        partner.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        return partner;
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        if(AppUtility.isMetadataAccessEnabled())
        {
            session.setAttribute("partnerSaveDisabled", saveDisabled);
            session.setAttribute("partnerModifyDisabled", modifyDisabled);
        }
        else
        {
            session.setAttribute("partnerSaveDisabled", "true");
            session.setAttribute("partnerModifyDisabled", "true");
        }
    }
    private List getAllPartners(HttpSession session)
    {
        List list=null;
        try
        {
            DaoUtility util=new DaoUtility();
            list=util.getPartnerDaoInstance().getAllPartners();
            if(list==null)
            list=new ArrayList();
            session.setAttribute("partnerList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
}
