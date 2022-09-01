/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.organizationunit.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.ReferralFacility;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.ArrayList;
import java.util.Date;
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
public class ReferralFacilityAction extends org.apache.struts.action.Action {

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
     ReferralFacilityForm rff=(ReferralFacilityForm)form;
        String moduleName="Referral facility";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //AppUtility appUtil=new AppUtility();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getHealthFacilityUpdatePriviledge().getPrivilegeId()))
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
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        
        String level2OuId=rff.getLevel2OuId();
        String level3OuId=rff.getLevel3OuId();
        String level4OuId=rff.getLevel4OuId();
        
        ouaManager.setOrganizationUnitAttributesForReports(session);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
        ouaManager.setOrganizationUnitAttributesByOuIdForReferralFacility(session, level2OuId, level3OuId, level4OuId);
        
        String requiredAction=rff.getActionName();
        String requestParam=request.getParameter("p");
        if(requestParam !=null)
        requiredAction=requestParam;
        String facilityId=rff.getFacilityId();
        ReportParameterTemplate rpt=new ReportParameterTemplate();
        rpt.setLevel2OuId(level2OuId);
        rpt.setLevel3OuId(level3OuId);
        rpt.setLevel4OuId(level4OuId);
        //setButtonState(session,"false","true");
        loadfacility(session,rpt);
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            List level3OuIdList=util.getOrganizationUnitDaoInstance().getOrganizationUnityByParentId(level2OuId);
            session.setAttribute("level3OuIdList", level3OuIdList);
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ed"))
        {
            facilityId=request.getParameter("id");
            ReferralFacility rf=util.getReferralFacilityDaoInstance().getReferralFacility(facilityId);
            rff.reset(mapping, request);
            if(rf !=null)
            {
                //System.err.println("rf.getFacilityId() is "+rf.getFacilityId());
                rff.setAddress(rf.getAddress());
                //rff.setCommunity(rf.getCommunity());
                rff.setContactEmail(rf.getContactEmail());
                rff.setContactPhoneNumber(rf.getContactPhone());
                rff.setFacilityName(rf.getFacilityName());
                rff.setFacilityId(rf.getFacilityId());
                System.err.println("rff.getFacilityId() is "+rff.getFacilityId());
                rff.setLatitude(rf.getLatitude());
                rff.setLongitude(rf.getLongitude());
                rff.setDatimId(rf.getDatimId());
                rff.setNameOfContactPerson(rf.getNameOfContactPerson());
                rff.setTypeOfFacility(rf.getTypeOfFacility());
                rff.setLevel4OuId(rf.getOrganizationUnitId());
                session.setAttribute("rfSaveDisabled", "true");
                session.setAttribute("rfModifyDisabled", "false");
                rff=setOrganizationUnitProperties(session, rf.getFacilityId(),rff,userName);
                return mapping.findForward(SUCCESS);
            }
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
           ReferralFacility rf= getReferralFacility(rff,userName);
            util.getReferralFacilityDaoInstance().saveReferralFacility(rf);
            saveUserActivity(userName,moduleName,"Saved new Partner record with name "+rf.getFacilityName());
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            ReferralFacility rf= getReferralFacility(rff,userName);
            util.getReferralFacilityDaoInstance().updateReferralFacility(rf);
            saveUserActivity(userName,moduleName,"Modified Partner record with name "+rf.getFacilityName());
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            ReferralFacility rf= getReferralFacility(rff,userName);
           util.getReferralFacilityDaoInstance().deleteReferralFacility(rf); 
           saveUserActivity(userName,moduleName,"Requested Partner record with Id "+rf.getFacilityName()+" be deleted");
        }
        loadfacility(session,rpt);
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        if(AppUtility.isMetadataAccessEnabled())
        {
            session.setAttribute("rfSaveDisabled", saveDisabled);
            session.setAttribute("rfModifyDisabled", modifyDisabled);
        }
        else
        {
            session.setAttribute("rfSaveDisabled", "true");
            session.setAttribute("rfModifyDisabled","true");
        }
    }
    private void loadfacility(HttpSession session,ReportParameterTemplate rpt)
    {
        try
        {//.getReferralFacilitiesByOrgUnit(rpt);
            DaoUtility util=new DaoUtility();
            List facilityList=util.getReferralFacilityDaoInstance().getAllReferralFacilities();
            if(facilityList ==null)
            facilityList=new ArrayList();
            session.setAttribute("facilityList", facilityList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private ReferralFacilityForm setOrganizationUnitProperties(HttpSession session, String facilityId,ReferralFacilityForm form,String userName) throws Exception
    {
        DaoUtility util=new DaoUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        ReferralFacility rf=util.getReferralFacilityDaoInstance().getReferralFacility(facilityId);
        if(rf !=null)
        {
            OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(rf.getOrganizationUnitId());
            if(ou !=null)
            {
                ouaManager.setOrganizationUnitAttributes(session, ou.getParentId(),userName,null);
                form.setLevel3OuId(ou.getParentId());
            }
            form.setLevel4OuId(rf.getOrganizationUnitId());
            //form.setCboId(hhe.getCboId());
        }
        return form;
    }
    private ReferralFacility getReferralFacility(ReferralFacilityForm rff,String userName)
    {
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        ReferralFacility rf=new ReferralFacility();
        rf.setFacilityId(rff.getFacilityId());
        rf.setFacilityName(rff.getFacilityName());
        System.err.println("rf.getFacilityId() 2 is "+rf.getFacilityId());
        System.err.println("rf.getFacilityName() is "+rf.getFacilityName());
        
        rf.setTypeOfFacility(rff.getTypeOfFacility());
        rf.setAddress(rff.getAddress());
        rf.setContactEmail(rff.getContactEmail());
        rf.setContactPhone(rff.getContactPhoneNumber());
        rf.setNameOfContactPerson(rff.getNameOfContactPerson());
        rf.setLatitude(rff.getLatitude());
        rf.setLongitude(rff.getLongitude());
        rf.setDatimId(rff.getDatimId());
        rf.setDateCreated(currentDate);
        rf.setLastModifiedDate(currentDate);
        rf.setOrganizationUnitId(rff.getLevel4OuId());
        rf.setRecordedBy(userName);
        return rf;
    }
}
