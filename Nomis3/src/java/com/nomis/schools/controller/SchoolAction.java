/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.schools.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.School;
import com.nomis.ovc.business.SiteSetup;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
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
public class SchoolAction extends org.apache.struts.action.Action {

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
        SchoolForm schoolform=(SchoolForm)form;
        String moduleName="School setup";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        AppManager appManager=new AppManager();
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
        String userName=appManager.getCurrentUserName(session);
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        String level2OuId=schoolform.getLevel2OuId();
        String level3OuId=schoolform.getLevel3OuId();
        String level4OuId=schoolform.getLevel4OuId();
        
        ouaManager.setOrganizationUnitAttributesForReports(session);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
        ouaManager.setOrganizationUnitAttributesByOuIdForReferralFacility(session, level2OuId, level3OuId, level4OuId);
        //ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,schoolform.getCboId());
        ReportParameterTemplate rpt=new ReportParameterTemplate();
        rpt.setLevel2OuId(level2OuId);
        rpt.setLevel3OuId(level3OuId);
        rpt.setLevel4OuId(level4OuId);
        generateSchoolList(session,rpt);
        //setButtonState(session,"false","true");
        String requiredAction=schoolform.getActionName();
        String param=request.getParameter("p");
        if(param !=null)
        requiredAction=param;
        System.err.println("requiredAction is "+requiredAction);
        
        if(requiredAction==null)
        {
            schoolform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            List level3OuIdList=util.getOrganizationUnitDaoInstance().getOrganizationUnityByParentId(level2OuId);
            session.setAttribute("level3OuIdList", level3OuIdList);
            ouaManager.setLevel4OuList(session, null);
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }//
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            School school=getSchoolSetup(userName,schoolform);
            if(util.getSchoolDaoInstance().getSchoolsBySchoolName(school.getSchoolName())==null)
            {
                util.getSchoolDaoInstance().saveSchool(school);
                saveUserActivity(userName,moduleName,"Saved new School record with name "+school.getSchoolName());
                generateSchoolList(session,rpt);
                schoolform.reset(mapping, request);
            }
            else
            {
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_MESSAGE,
                new ActionMessage("errors.schoolName.exists"));
                // Report any errors we have discovered back
                // to the original form
                if (!errors.isEmpty()) 
                {
                    saveErrors(request, errors);
                }
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            School school=getSchoolSetup(userName,schoolform);
            util.getSchoolDaoInstance().updateSchool(school);
            saveUserActivity(userName,moduleName,"Modified School record with name "+school.getSchoolName());
            generateSchoolList(session,rpt);
            schoolform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("de"))
        {
            School school=getSchoolSetup(userName,schoolform);
            util.getSchoolDaoInstance().deleteSchool(school);
            saveUserActivity(userName,moduleName,"Requested School record with name "+school.getSchoolName()+" be deleted");
            generateSchoolList(session,rpt);
            schoolform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ed"))
        {
            String schoolId=request.getParameter("id");
            School school=util.getSchoolDaoInstance().getSchool(schoolId);
            if(school !=null)
            {
                schoolform.setSchoolName(school.getSchoolName());
                schoolform.setId(school.getId());
                schoolform.setLevel4OuId(school.getOrgUnitId());
                setButtonState(session,"true","false");
            }
            /*SiteSetup setup=util.getSiteSetupDaoInstance().getSiteSetup(userName);
            if(setup !=null)
            {
                schoolform.setPartner(setup.getPartnerCode());
                schoolform.setLevel4OuId(setup.getOrgUnitId());
                //schoolform.setUserName(userName);
                setButtonState(session,"true","false");
            }*/
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            School school=getSchoolSetup(userName,schoolform);
            util.getSchoolDaoInstance().deleteSchool(getSchoolSetup(userName,schoolform));
            saveUserActivity(userName,moduleName,"Requested School record with name "+school.getSchoolName()+" be deleted");
            schoolform.reset(mapping, request);
            //generateSiteSetupList(session);
            return mapping.findForward(SUCCESS);
        }
        schoolform.reset(mapping, request);
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private School getSchoolSetup(String userName,SchoolForm schoolform)
    {
        School school=new School();
        school.setRecordedBy(userName);
        school.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        school.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        school.setOrgUnitId(schoolform.getLevel4OuId());
        school.setSchoolName(schoolform.getSchoolName());
        school.setSchoolType(schoolform.getSchoolType());
        return school;
    }
    private void generateSchoolList(HttpSession session,ReportParameterTemplate rpt)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getSchoolDaoInstance().getSchoolsByOrgUnit(rpt);//.getAllSchools();
            if(list==null)
            list=new ArrayList();
            session.setAttribute("schoolList", list);
            System.err.println("school list size is "+list.size());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        if(AppUtility.isMetadataAccessEnabled())
        {
            session.setAttribute("schoolSaveDisabled", saveDisabled);
            session.setAttribute("schoolModifyDisabled", modifyDisabled);
        }
        else
        {
            session.setAttribute("schoolSaveDisabled", "true");
            session.setAttribute("schoolModifyDisabled", "true");
        }
    }
}
