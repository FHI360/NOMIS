/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.cbo.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.ServiceDomain;
import com.nomis.ovc.business.SiteSetup;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.CommunityBasedOrganizationDao;
import com.nomis.ovc.dao.CommunityBasedOrganizationDaoImpl;
import com.nomis.ovc.dao.DaoUtility;
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
public class CommunityBasedOrganizationAction extends org.apache.struts.action.Action {

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
        CommunityBasedOrganizationForm cboForm=(CommunityBasedOrganizationForm)form;
        String moduleName="Community Based Organizatiom action";
        CommunityBasedOrganizationDao cbodao=new CommunityBasedOrganizationDaoImpl();
        HttpSession session=request.getSession();
        AppUtility appUtil=new AppUtility();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        
        
        String level2OuId=null;
        if(ouaManager.getSiteSetup(userName) !=null)
        level2OuId=ouaManager.getSiteSetup(userName).getLevel2OuId();
        User user=appManager.getCurrentUser(session);
        if(user ==null || !user.isSuperUser())
        session.setAttribute("cbomergeBtnDisabled", "true");
        else
        session.setAttribute("cbomergeBtnDisabled", "false");    
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getAdminSetupUtilityPriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false","true"); 
        }
        else
        {
            setButtonState(session,"true","true");
            cboForm.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        DaoUtility daoutil=new DaoUtility();
       
        String requiredAction=cboForm.getActionName();
        String param=request.getParameter("p");
        if(param !=null)
        requiredAction=param;
        getCBOListByLevel2Ou(session,userName);
        ouaManager.setLevel2Ou(session, userName);
        ouaManager.setLevel2OuHierarchy(session);
        ouaManager.setLevel3OuHierarchy(session);
        loadServiceDomains(session);
        //setButtonState(session,"false","true");
        if(requiredAction==null)
        {
            saveSericeDomains();
            cboForm.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ed"))
        {
            cboForm.reset(mapping, request);
            String cboUniqueId=request.getParameter("id");
            //System.err.println("cboId is "+cboId);
            CommunityBasedOrganization cbo=daoutil.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboUniqueId);
            if(cbo !=null)
            {
                //System.err.println("cbo.getCboName() is "+cbo.getCboName());
               cboForm.setHiddenUniqueId(cboUniqueId);
               cboForm.setCboCode(cbo.getCboCode());
               cboForm.setCboName(cbo.getCboName());
               cboForm.setUniqueId(cbo.getUniqueId());
               cboForm.setAddress(cbo.getAddress());
               cboForm.setContactPersonEmail(cbo.getContactPersonEmail());
               cboForm.setContactPersonName(cbo.getContactPersonName());
               cboForm.setContactPersonPhone(cbo.getContactPersonPhone());
               cboForm.setDataExchangeId(cbo.getDataExchangeId());
               cboForm.setLatitude(cbo.getLatitude());
               cboForm.setLongitude(cbo.getLongitude());
               cboForm.setLevel3OuId(appUtil.splitString(cbo.getAssignedLevel3OuIds(), ","));
               cboForm.setServices(appUtil.splitString(cbo.getServices(), ","));
               setButtonState(session,"true","false");
            }
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            CommunityBasedOrganization setup=getCommunityBasedOrganization(cboForm,level2OuId);
            if(cboForm.getUniqueId() !=null && cboForm.getUniqueId().trim().length()==11)
            setup.setUniqueId(cboForm.getUniqueId());
            cbodao.saveCommunityBasedOrganization(setup);
            cboForm.reset(mapping, request);
            getCBOListByLevel2Ou(session,userName);
            saveUserActivity(userName,moduleName,"Saved CBO setup "+setup.getCboName());
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            CommunityBasedOrganization setup=getCommunityBasedOrganization(cboForm,level2OuId);
            setup.setUniqueId(cboForm.getHiddenUniqueId());
            cbodao.updateCommunityBasedOrganization(setup);
            cboForm.reset(mapping, request);
            getCBOListByLevel2Ou(session,userName);
            saveUserActivity(userName,moduleName,"Modified CBO setup "+setup.getCboName());
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("de"))
        {
            CommunityBasedOrganization setup=getCommunityBasedOrganization(cboForm,level2OuId);
            String uniqueId=request.getParameter("id");
            setup.setUniqueId(uniqueId);
            cbodao.deleteCommunityBasedOrganization(setup);
            saveUserActivity(userName,moduleName,"Deleted CBO setup "+setup.getCboName());
            cboForm.reset(mapping, request);
            getCBOListByLevel2Ou(session,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("mergeCBORecords"))
        {
            DaoUtility util=new DaoUtility();
            String cboToKeep=cboForm.getCboToKeep();
            String[] selectedCbosToDelete=cboForm.getSelectedCbosToDelete();
            user=util.getUserDaoInstance().getUser(userName);
            if(user !=null && user.isSuperUser())
            {
                if(cboToKeep !=null && selectedCbosToDelete !=null)
                {
                    CommunityBasedOrganization setup=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboToKeep);
                    if(setup !=null)
                    {
                        String cboToDeleteId=null;
                        for(int i=0; i<selectedCbosToDelete.length; i++)
                        {
                            cboToDeleteId=selectedCbosToDelete[i];
                            if(cboToKeep.equalsIgnoreCase(cboToDeleteId))
                            continue;
                            CommunityBasedOrganization cboToDelete=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboToDeleteId);
                            //util.getHouseholdProfileDaoInstance().changeCboId(cboToDeleteId, cboToKeep);
                            util.getCommunityBasedOrganizationDaoInstance().deleteCommunityBasedOrganization(cboToDelete);
                        }
                    }
                }
            }
        }
        getCBOListByLevel2Ou(session,userName);
        cboForm.reset(mapping, request);
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private CommunityBasedOrganization getCommunityBasedOrganization(CommunityBasedOrganizationForm cboform,String level2ouId)
    {
        AppUtility appUtil=new AppUtility();
        CommunityBasedOrganization setup=new CommunityBasedOrganization();
        setup.setCboCode(cboform.getCboCode());
        setup.setUniqueId(cboform.getUniqueId());
        setup.setAddress(cboform.getAddress());
        setup.setCboName(cboform.getCboName());
        setup.setContactPersonEmail(cboform.getContactPersonEmail());
        setup.setContactPersonName(cboform.getContactPersonName());
        setup.setContactPersonPhone(cboform.getContactPersonPhone());
        setup.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        setup.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        setup.setDataExchangeId(cboform.getDataExchangeId());
        setup.setLatitude(cboform.getLatitude());
        setup.setLongitude(cboform.getLongitude());
        setup.setLevel2OuId(level2ouId);
        setup.setAssignedLevel3OuIds(appUtil.concatStr(cboform.getLevel3OuId(), null));
        setup.setServices(appUtil.concatStr(cboform.getServices(), null));
        return setup;
    }
    private void getCBOListByLevel2Ou(HttpSession session, String userName)
    {
        try
        {
            String level2OuId=null;
            DaoUtility daoutil=new DaoUtility();
            SiteSetup siteSetup=daoutil.getSiteSetupDaoInstance().getSiteSetup(userName);
            if(siteSetup !=null)
            {
                level2OuId=siteSetup.getLevel2OuId();
            }
            
            List cboList=daoutil.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganizationByLevel2OrgUnit(level2OuId);
            if(cboList==null)
            cboList=new ArrayList();
            session.setAttribute("cboList", cboList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void saveSericeDomains()
    {
        String[] serviceDomains={"Psychosocial support","Nutritional services","Health services","Educational services","Child protection","Shelter and care","Economic strengthening"};
        try
        {
            DaoUtility daoutil=new DaoUtility();
           List list=daoutil.getServiceDomainDaoInstance().getAllServiceDomains();
           if(list==null || list.isEmpty())
           {
               ServiceDomain sd=null;
               for(int i=0; i<serviceDomains.length; i++)
               {
                   sd=new ServiceDomain();
                   sd.setServiceDomainName(serviceDomains[i]);
                   daoutil.getServiceDomainDaoInstance().saveServiceDomain(sd);
               }
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void loadServiceDomains(HttpSession session)
    {
         try
        {
            DaoUtility daoutil=new DaoUtility();
           List list=daoutil.getServiceDomainDaoInstance().getAllServiceDomains();
           if(list!=null && !list.isEmpty())
           {
               session.setAttribute("serviceDomainList", list);
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
   
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        if(AppUtility.isMetadataAccessEnabled())
        {
            session.setAttribute("cboSaveDisabled", saveDisabled);
            session.setAttribute("cboModifyDisabled", modifyDisabled);
        }
        else
        {
            session.setAttribute("cboSaveDisabled", "true");
            session.setAttribute("cboModifyDisabled", "true");
        }
    }
}
