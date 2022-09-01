/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.user.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
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
public class AdminUserAtion extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String CREATEUSER = "createuser";
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
        AdminUserForm usf=(AdminUserForm)form;
        String moduleName="User account setup";
        HttpSession session=request.getSession();
        AppUtility appUtil=new AppUtility();
        DaoUtility util=new DaoUtility();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        String tomcatRootLocation="";
        String dbsource=getServlet().getServletContext().getRealPath("/Resources/dbs");
        if(dbsource !=null)
        {
            if(dbsource.indexOf("/") !=-1)
            tomcatRootLocation=dbsource.substring(0, dbsource.indexOf("/"));
            else if(dbsource.indexOf("\\") !=-1)
            tomcatRootLocation=dbsource.substring(0, dbsource.indexOf("\\"));
        }
        
        String confSourceDirectory=getServlet().getServletContext().getRealPath("/Resources/conf");
        String facilitySourceDirectory=getServlet().getServletContext().getRealPath("/Resources/Resources");
        /*if(AccessManager.isUserInAddUserRole(user))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }*/
        String userName=appManager.getCurrentUserName(session);
        //OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();
        String requiredAction=usf.getActionName();
        String param=request.getParameter("p");
        if(param !=null)
        requiredAction=param;
        List ouList=new ArrayList();//ouam.getOrganizationUnitsWithParentNames(4);
        session.setAttribute("ouWithPathNamesForUserAccount", ouList);
        //setButtonState(session,"false","true");
        setUserList(session);
        if(requiredAction==null || requiredAction.equalsIgnoreCase("login"))
        {
            usf.reset(mapping, request);
            usf.setTomcatRootLocation(tomcatRootLocation);
            appUtil.setResourceDirectory(tomcatRootLocation);
            usf.setResourceLocation(appUtil.getDbRootDirectory());
            usf.setDbUrl(appUtil.getDatabaseURL());
            return mapping.findForward(CREATEUSER);
        }
        
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            User nomisUser=getUser(usf,userName);
            util.getUserDaoInstance().saveUser(nomisUser);
            if(nomisUser !=null)
            {
                session.setAttribute("USER", nomisUser);
            }
            saveUserActivity(userName,moduleName,"Saved new user record with name "+nomisUser.getUsername());
            setUserList(session);
            usf.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        
        return mapping.findForward(CREATEUSER);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private User getUser(AdminUserForm usf,String userName)
    {
        AppUtility appUtil=new AppUtility();
        User user=new User();
        user.setAccessPrivileges(AppConstant.DEFAULTUID);
        user.setDataEntryOu(AppConstant.DEFAULTUID);
        user.setReportOu(AppConstant.DEFAULTUID);
        user.setAccountStatus(usf.getAccountStatus());
        user.setAddress(usf.getAddress());
        user.setAssignedGroupId(usf.getAssignedGroup());
        user.setChangePwd(usf.getChangePwd());
        user.setDateCreated(DateManager.getCurrentDateInstance());
        user.setEmail(usf.getEmail());
        user.setFirstName(usf.getFirstname());
        user.setLastModifiedDate(DateManager.getCurrentDateInstance());
        user.setLastName(usf.getSurname());
        user.setOrgUnitGroupId(usf.getOrgunitGroup());
        user.setPassword(usf.getPassword());
        user.setPhone(usf.getPhone());
        user.setUserGroupId(usf.getUsergroup());
        user.setUsername(usf.getUsername());
        user.setUserroleId(usf.getUserrole());
        user.setViewClientDetails(user.getViewClientDetails());
        user.setRecordedBy(userName);
        return user;
    }
    private AdminUserForm getUserAccountForm(AdminUserForm usf,User user)
    {
        AppUtility appUtil=new AppUtility();
        usf.setAccountStatus(user.getAccountStatus());
        usf.setAddress(user.getAddress());
        usf.setAssignedGroup(user.getAssignedGroupId());
        usf.setChangePwd(user.getChangePwd());
        usf.setEmail(user.getEmail());
        usf.setFirstname(user.getFirstName());
        usf.setSurname(user.getLastName());
        usf.setOrgunitGroup(user.getOrgUnitGroupId());
        usf.setPassword(user.getPassword());
        usf.setConfirmPwd(user.getPassword());
        usf.setPhone(user.getPhone());
        usf.setUsergroup(user.getUserGroupId());
        usf.setUsername(user.getUsername());
        usf.setUserrole(user.getUserroleId());
        usf.setViewClientDetails(user.getViewClientDetails());
        
        return usf;
    }
    private void setButtonState(HttpSession session,String saveDisabled)
    {
        session.setAttribute("adminUserSaveDisabled", saveDisabled);
    }
    private void setUserList(HttpSession session)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getUserDaoInstance().getAllUsers();
            if(list==null)
            list=new ArrayList();
            session.setAttribute("userList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
