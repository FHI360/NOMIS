/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.sitesetup.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.SiteSetup;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.OrganizationUnitDao;
import com.nomis.ovc.dao.OrganizationUnitHierarchyDao;
import com.nomis.ovc.dao.PartnerDao;
import com.nomis.ovc.dao.PartnerDaoImpl;
import com.nomis.ovc.dao.SiteSetupDao;
import com.nomis.ovc.dao.SiteSetupDaoImpl;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.metadata.OrganizationUnitHierarchy;
import com.nomis.ovc.util.AppConstant;
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
public class SiteSetupAction extends org.apache.struts.action.Action {

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
        SiteSetupForm ssform=(SiteSetupForm)form;
        String moduleName="Site setup";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        OrganizationUnitHierarchyDao ouhdao=util.getOrganizationUnitHierarchyDaoInstance();
        OrganizationUnitDao oudao=util.getOrganizationUnitDaoInstance();
        PartnerDao pdao=new PartnerDaoImpl();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        String ouhl2Name="State";
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        ouaManager.setOrganizationUnitHirachyAttributes(session);
        OrganizationUnitHierarchy level2orgunitHierarchy=ouhdao.getOrganizationUnitHierarchyByLevel(2);
        if(level2orgunitHierarchy !=null)
        ouhl2Name=level2orgunitHierarchy.getName();
        
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
        List level2orgUnitList=oudao.getOrganizationUnitsByOuLevel(2);
        if(level2orgUnitList==null)
        level2orgUnitList=new ArrayList();
        
        String levelOu2Id=ssform.getLevel2OuId();
        String cboId=ssform.getCboId();
        String levelOu3Id=ssform.getLevel3OuId();
        if(cboId==null || cboId.trim().equalsIgnoreCase("select"))
        levelOu3Id=null;
        setCboList(session,levelOu2Id);
        setLevel3OuListByCboId(session,cboId);
        setLevel4OuList(session,levelOu3Id);
        List partnerList=pdao.getAllPartners();
        if(partnerList==null)
        partnerList=new ArrayList();
        
        session.setAttribute("ouhLevel2", ouhl2Name);
        session.setAttribute("level2orgUnitList", level2orgUnitList);
        session.setAttribute("partnerList", partnerList);
        generateSiteSetupList(session);
        setUserList(session);
        
        String requiredAction=ssform.getActionName();
        String param=request.getParameter("p");
        if(param !=null)
        requiredAction=param;
        
        if(requiredAction==null)
        {
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("cboList"))
        {
            setLevel3OuListByCboId(session,null);
            setLevel4OuList(session,null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            setLevel4OuList(session,null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            SiteSetup setup=getSiteSetup(ssform);
            if(util.getSiteSetupDaoInstance().getSiteSetup(setup.getUserName()) !=null)
            {
                util.getSiteSetupDaoInstance().updateSiteSetup(setup);
                saveUserActivity(userName,moduleName,"Modified Site setup record for user "+setup.getUserName());
            }
            else
            {
                util.getSiteSetupDaoInstance().saveSiteSetup(setup);
                saveUserActivity(userName,moduleName,"Saved new Site setup record for user "+setup.getUserName());
            }
            generateSiteSetupList(session);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            SiteSetup setup=getSiteSetup(ssform);
            util.getSiteSetupDaoInstance().updateSiteSetup(setup);
            saveUserActivity(userName,moduleName,"Modified Site setup record for user "+setup.getUserName());
            generateSiteSetupList(session);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("de"))
        {
            SiteSetup setup=getSiteSetup(ssform);
            util.getSiteSetupDaoInstance().deleteSiteSetup(setup);
            saveUserActivity(userName,moduleName,"Requested Site setup record for user "+setup.getUserName()+" be deleted");
            generateSiteSetupList(session);
            //return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ed"))
        {
            String userId=request.getParameter("id");
            SiteSetup setup=util.getSiteSetupDaoInstance().getSiteSetup(userId);
            if(setup !=null)
            {
                ssform.setPartner(setup.getPartnerCode());
                ssform.setLevel2OuId(setup.getLevel2OuId());
                ssform.setUserName(userId);
                setCboList(session,setup.getLevel2OuId());
                setLevel3OuListByCboId(session,setup.getCboId());
                setLevel4OuList(session,setup.getLevel3OuId());
                ssform.setCboId(setup.getCboId());
                ssform.setLevel3OuId(setup.getLevel3OuId());
                ssform.setLevel4OuId(setup.getLevel4OuId());
                setButtonState(session,"true","false");
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            SiteSetup setup=getSiteSetup(ssform);
            util.getSiteSetupDaoInstance().deleteSiteSetup(setup);
            saveUserActivity(userName,moduleName,"Requested Site setup record for user "+setup.getUserName()+" be deleted");
            generateSiteSetupList(session);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void setLevel3OuListByCboId(HttpSession session,String cboId) throws Exception
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
        session.setAttribute("siteSetupLevel3OuList", level3OuList);
        session.setAttribute("siteSetupLevel4OuList", new ArrayList());
    }
    private void setLevel4OuList(HttpSession session,String levelOu3Id) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List level4OuList=util.getOrganizationUnitDaoInstance().getOrganizationUnityByParentId(levelOu3Id);
        if(level4OuList==null)
        level4OuList=new ArrayList();
        session.setAttribute("siteSetupLevel4OuList", level4OuList);
    }
    private void setCboList(HttpSession session,String levelOu2Id) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List cboList=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganizationByLevel2OrgUnit(levelOu2Id);
        if(cboList ==null)
        cboList=new ArrayList();
        session.setAttribute("siteSetupCboList", cboList);
    }//
    private SiteSetup getSiteSetup(SiteSetupForm ssform)
    {
        SiteSetup setup=new SiteSetup();
        setup.setCreatedBy(ssform.getUserName());
        setup.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        setup.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        setup.setCboId(ssform.getCboId());
        setup.setLevel2OuId(ssform.getLevel2OuId());
        setup.setLevel3OuId(ssform.getLevel3OuId());
        setup.setLevel4OuId(ssform.getLevel4OuId());
        setup.setPartnerCode(ssform.getPartner());
        setup.setUserName(ssform.getUserName());
        
        return setup;
    }
    private void generateSiteSetupList(HttpSession session)
    {
        try
        {
            SiteSetupDao ssdao=new SiteSetupDaoImpl();
            List list=ssdao.getAllSiteSetups();
            if(list==null)
            list=new ArrayList();
            session.setAttribute("siteSetupList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("ssSaveDisabled", saveDisabled);
        session.setAttribute("ssModifyDisabled", modifyDisabled);
    }
    private void setUserList(HttpSession session)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List userList=util.getUserDaoInstance().getAllUsers();
            if(userList==null)
            userList=new ArrayList();
            session.setAttribute("siteSetupUserList", userList);
            //siteSetupUserList
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
