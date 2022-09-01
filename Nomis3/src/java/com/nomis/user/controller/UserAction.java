/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.user.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
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
public class UserAction extends org.apache.struts.action.Action {

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
        UserForm usf=(UserForm)form;
        String moduleName="User account setup";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        ouaManager.setOrganizationUnitHirachyAttributes(session);
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getUserSetupPriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        String userName=appManager.getCurrentUserName(session);
        //OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();
        String dataCaptureLevelOu2Id=usf.getDataCaptureLevel2OuId();
        String dataCaptureCboId=usf.getDataCaptureCboId();
        String dataCaptureLevel3OuId=usf.getDataCaptureLevel3OuId();
        String reportLevel2OuId=usf.getReportLevel2OuId();
        String reportCboId=usf.getReportCboId();
        String reportLevel3OuId=usf.getReportLevel3OuId();
        if(dataCaptureCboId==null || dataCaptureCboId.trim().equalsIgnoreCase("select"))
        dataCaptureLevel3OuId=null;
        if(reportCboId==null || reportCboId.trim().equalsIgnoreCase("select"))
        reportLevel3OuId=null;
        setLevel2OuList(session);
        setCboList(session,dataCaptureLevelOu2Id,"dataCapture");
        setLevel3OuListByCboId(session,dataCaptureCboId,"dataCapture");
        setLevel4OuList(session,dataCaptureLevel3OuId,"dataCapture");
        
        setCboList(session,reportLevel2OuId,"report");
        setLevel3OuListByCboId(session,reportCboId,"report");
        setLevel4OuList(session,reportLevel3OuId,"report");
        setPartnerList(session);
               
        String requiredAction=usf.getActionName();
        String param=request.getParameter("p");
        if(param !=null)
        requiredAction=param;
        List ouList=new ArrayList();//ouam.getOrganizationUnitsWithParentNames(4);
        session.setAttribute("ouWithPathNamesForUserAccount", ouList);
        //setButtonState(session,"false","true");
        setUserList(session);
        setUserRoleList(session);
        if(requiredAction==null)
        {
            usf.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("dataCaptureCboList"))
        {
            //setCboList(session,dataCaptureLevelOu2Id,"dataCapture");
            setLevel3OuListByCboId(session,null,"dataCapture");
            setLevel4OuList(session,null,"dataCapture");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("dataCaptureLevel3OuList"))
        {
            //setLevel3OuListByCboId(session,dataCaptureCboId,"dataCapture");
            setLevel4OuList(session,null,"dataCapture");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("dataCaptureLevel4OuList"))
        {
            //setLevel4OuList(session,dataCaptureLevel3OuId,"dataCapture");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("reportCboList"))
        {
            //setCboList(session,reportLevel2OuId,"report");
            setLevel3OuListByCboId(session,null,"report");
            setLevel4OuList(session,null,"report");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("reportLevel3OuList"))
        {
            //setLevel3OuListByCboId(session,reportCboId,"report");
            setLevel4OuList(session,null,"report");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("reportLevel4OuList"))
        {
            //setLevel4OuList(session,reportLevel3OuId,"report");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("edit"))
        {
            String username=request.getParameter("id");
            User user1=util.getUserDaoInstance().getUser(username);
            if(user1 !=null)
            {
                usf=getUserAccountForm(session,usf,user1);
                setButtonState(session,"true","false");
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            User nomisUser=getUser(usf,userName);
            if(util.getUserDaoInstance().getUser(userName) !=null)
            {
                util.getUserDaoInstance().updateUser(nomisUser);
                saveUserActivity(userName,moduleName,"Modified user record with name "+nomisUser.getUsername());
            }
            else
            {
                util.getUserDaoInstance().saveUser(getUser(usf,userName));
                saveUserActivity(userName,moduleName,"Saved new user record with name "+nomisUser.getUsername());
            }
            setUserList(session);
            usf.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("update"))
        {
            User nomisUser=getUser(usf,userName);
            util.getUserDaoInstance().updateUser(getUser(usf,userName));
            saveUserActivity(userName,moduleName,"Modified user record with name "+nomisUser.getUsername());
            setUserList(session);
            usf.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            User nomisUser=getUser(usf,userName);
            util.getUserDaoInstance().deleteUser(getUser(usf,userName));
            saveUserActivity(userName,moduleName,"Requested user record with name "+nomisUser.getUsername());
            setUserList(session);
            usf.reset(mapping, request);
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void setLevel2OuList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List level2orgUnitList=util.getOrganizationUnitDaoInstance().getOrganizationUnitsByOuLevel(2);
        if(level2orgUnitList==null)
        level2orgUnitList=new ArrayList();
        session.setAttribute("level2orgUnitList", level2orgUnitList);
    }
    private void setLevel3OuListByCboId(HttpSession session,String cboId,String dataCaptureOrReportFlag) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List level3OuList=new ArrayList();
        CommunityBasedOrganization cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboId);
        if(cbo !=null)
        {
            if(cbo.getAssignedLevel3OuIds() !=null)
            {
                OrganizationUnit ou=null;
                String ouId=null;
                String[] assignedLevel3OuIds=cbo.getAssignedLevel3OuIds().split(",");
                for(int i=0; i<assignedLevel3OuIds.length; i++)
                {
                    ouId=assignedLevel3OuIds[i].replace(",", "");
                    ouId=ouId.trim();
                    ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(ouId);
                    if(ou !=null)
                    level3OuList.add(ou);
                }
            }
        }
        if(dataCaptureOrReportFlag.equalsIgnoreCase("dataCapture"))
        {
            session.setAttribute("dataCaptureLevel3OuList", level3OuList);
            session.setAttribute("dataCaptureLevel4OuList", new ArrayList());
        }
        else
        {
            session.setAttribute("reportLevel3OuList", level3OuList);
            session.setAttribute("reportLevel4OuList", new ArrayList());
        }
    }
    private void setLevel4OuList(HttpSession session,String Level3OuId,String dataCaptureOrReportFlag) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List level4OuList=util.getOrganizationUnitDaoInstance().getOrganizationUnityByParentId(Level3OuId);
        if(level4OuList==null)
        level4OuList=new ArrayList();
        if(dataCaptureOrReportFlag.equalsIgnoreCase("dataCapture"))
        session.setAttribute("dataCaptureLevel4OuList", level4OuList);
        else
        session.setAttribute("reportLevel4OuList", level4OuList);
    }
    private void setPartnerList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List partnerList=util.getPartnerDaoInstance().getAllPartners();
        if(partnerList==null)
        partnerList=new ArrayList();
        
        session.setAttribute("partnerList", partnerList);
    }
    private void setCboList(HttpSession session,String levelOu2Id,String dataCaptureOrReportFlag) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List cboList=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganizationByLevel2OrgUnit(levelOu2Id);
        if(cboList ==null)
        cboList=new ArrayList();
        if(dataCaptureOrReportFlag.equalsIgnoreCase("dataCapture"))
        session.setAttribute("dataCaptureCboList", cboList);
        else
        session.setAttribute("reportCboList", cboList);
    }
    private User getUser(UserForm usf,String userName)
    {
        AppUtility appUtil=new AppUtility();
        User user=new User();
        user.setAccessPrivileges(appUtil.concatStr(usf.getPrivileges(), null));
        user.setDataEntryOu(appUtil.concatStr(usf.getDataEntryOu(), null));
        user.setReportOu(appUtil.concatStr(usf.getReportOu(), null));
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
        user.setViewClientDetails(usf.getViewClientDetails());
        user.setDataCaptureCboId(usf.getDataCaptureCboId());
        user.setDataCaptureLevel2OuId(usf.getDataCaptureLevel2OuId());
        user.setDataCaptureLevel3OuId(usf.getDataCaptureLevel3OuId());
        user.setDataCaptureLevel4OuId(usf.getDataCaptureLevel4OuId());
        user.setDataCapturePartner(usf.getDataCapturePartner());
        user.setReportCboId(usf.getReportCboId());
        user.setReportLevel2OuId(usf.getReportLevel2OuId());
        user.setReportLevel3OuId(usf.getReportLevel3OuId());
        user.setReportLevel4OuId(usf.getReportLevel4OuId());
        user.setReportPartner(usf.getReportPartner());
        user.setReportGenerationDisabled(usf.getReportGenerationDisabled());
        user.setRecordedBy(userName);
        return user;
    }
    private UserForm getUserAccountForm(HttpSession session,UserForm usf,User user) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        if(user !=null)
        {
            setCboList(session,user.getDataCaptureLevel2OuId(),"dataCapture");
            setLevel3OuListByCboId(session,user.getDataCaptureCboId(),"dataCapture");
            setLevel4OuList(session,user.getDataCaptureLevel3OuId(),"dataCapture");

            setCboList(session,user.getReportLevel2OuId(),"report");
            setLevel3OuListByCboId(session,user.getReportCboId(),"report");
            setLevel4OuList(session,user.getReportLevel3OuId(),"report");
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
            usf.setPrivileges(appUtil.splitString(user.getAccessPrivileges(),","));
            usf.setDataEntryOu(appUtil.splitString(user.getDataEntryOu(),","));
            usf.setReportOu(appUtil.splitString(user.getReportOu(),","));

            usf.setDataCaptureCboId(user.getDataCaptureCboId());
            usf.setDataCaptureLevel2OuId(user.getDataCaptureLevel2OuId());
            usf.setDataCaptureLevel3OuId(user.getDataCaptureLevel3OuId());
            usf.setDataCaptureLevel4OuId(user.getDataCaptureLevel4OuId());
            usf.setDataCapturePartner(user.getDataCapturePartner());
            usf.setReportCboId(user.getReportCboId());
            usf.setReportLevel2OuId(user.getReportLevel2OuId());
            usf.setReportLevel3OuId(user.getReportLevel3OuId());
            usf.setReportLevel4OuId(user.getReportLevel4OuId());
            usf.setReportPartner(user.getReportPartner());
            usf.setReportGenerationDisabled(user.getReportGenerationDisabled());
        }
        return usf;
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("userSaveDisabled", saveDisabled);
        session.setAttribute("userModifyDisabled", modifyDisabled);
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
    private void setUserRoleList(HttpSession session)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getUserRoleDaoInstance().getAllUserRoles();
            if(list==null)
            list=new ArrayList();
            session.setAttribute("userRoleList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
