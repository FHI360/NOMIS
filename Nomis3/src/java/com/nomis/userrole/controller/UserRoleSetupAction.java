/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.userrole.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.business.UserRole;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.UserPrivilegesDao;
import com.nomis.ovc.dao.UserRoleDao;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class UserRoleSetupAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
//Privileges
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
        UserRoleSetupForm urForm=(UserRoleSetupForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        
        AccessManager acm=new AccessManager();
        
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getUserRoleSetupPriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        UserRoleDao roleDao=util.getUserRoleDaoInstance();
        UserPrivilegesDao urDao=util.getUserPrivilegesDaoInstance();
        UserRole ur=new UserRole();
        AppUtility appUtil=new AppUtility();
        String roleId=urForm.getRoleId();
        String roleName=urForm.getRoleName();
        String[] assignedPrivilegesArray=urForm.getAssignedPrivileges();
        
        ur.setRoleId(roleId);
        ur.setRoleName(roleName);
        String assignedPrivileges=appUtil.concatStr(assignedPrivilegesArray, null);
        ur.setAssignedPrivileges(assignedPrivileges);
        String requiredAction=urForm.getActionName();
        
        /*boolean userInRole=acm.isUserInRole("002",appUtil.getCurrentUser(session));
        if(!userInRole)
        {
            request.setAttribute("errorMsg", appUtil.getAccessDeniedMessage());
            return mapping.findForward("accessDenied");
        }*/
        session.setAttribute("userRoleSaveDisabled", "false");
        session.setAttribute("userRoleModifyDisabled", "true");
        
        System.err.println("Required action is "+requiredAction);
        if(requiredAction==null)
        {
           
        }
        else if(requiredAction.equalsIgnoreCase("roledetails"))
        {
             ur=roleDao.getUserRole(urForm.getUserRoleList());
             urForm.reset(mapping, request);
            if(ur !=null)
            {
               
                urForm.setRoleId(ur.getRoleId());
                urForm.setRoleName(ur.getRoleName());
                urForm.setAssignedPrivileges(appUtil.splitString(ur.getAssignedPrivileges(), ","));
                session.setAttribute("userRoleSaveDisabled", "true");
                session.setAttribute("userRoleModifyDisabled", "false");
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            //String duplicateRoleName=null;
            UserRole duplicateRole=null;
            try
            {
                duplicateRole=roleDao.getUserRoleByRoleName(ur.getRoleName());
                if(duplicateRole !=null)
                {
                    ActionErrors errors=new ActionErrors();
                    errors.add("roleName", new ActionMessage("roleNameExist"));
                    saveErrors(request, errors);
                    return mapping.findForward(SUCCESS);
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            int noOfRoles=roleDao.getNoOfUserRoles();
            noOfRoles++;
            roleId=getRoleId(roleDao,noOfRoles);
            ur.setRoleId(roleId);
            //System.err.println("userRole name is "+ur.getRoleName());
            //System.err.println("userRole id is "+ur.getRoleId());
            roleDao.saveUserRole(ur);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            roleDao.updateUserRole(ur);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            roleDao.deleteUserRole(ur);
        }
        List list=UserPrivilegeManager.getUserPrivileges();//urDao.getAllUserPrivileges();
        if(list==null)
         list=new ArrayList();
        //list.addAll(UserPrivilegeManager.getUserPrivileges());
           List roleList=roleDao.getAllUserRoles();
           if(roleList !=null)
           System.err.println("roleList size is "+roleList.size());
           session.setAttribute("avalaiblePrivileges", list);
           session.setAttribute("existingRoles", roleList);
        urForm.reset(mapping, request);
        return mapping.findForward(SUCCESS);
    }
    private String getRoleId(UserRoleDao urDao,int noOfRoles)
    {
        String roleId="roleId"+noOfRoles;
        try
        {
            UserRole ur=urDao.getUserRole(roleId);
            if(ur !=null)
            {
                //System.err.println("roleId in getRoleId is "+ur.getRoleId());
                noOfRoles++;
                roleId=getRoleId(urDao,noOfRoles);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        //System.err.println("roleId is "+roleId);
        return roleId;
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("userRoleSaveDisabled", saveDisabled);
        session.setAttribute("userRoleModifyDisabled", modifyDisabled);
    }
}
