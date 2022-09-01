/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.schools.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.SchoolGrade;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
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
public class SchoolGradeAction extends org.apache.struts.action.Action {

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
        SchoolGradeForm gradeform=(SchoolGradeForm)form;
        String moduleName="School grade setup";
        HttpSession session=request.getSession();
        DaoUtility daoutil=new DaoUtility();
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
        
        
        generateSchoolGradeList(session);
        //setButtonState(session,"false","true");
        String requiredAction=gradeform.getActionName();
        String id=request.getParameter("id");
        String param=request.getParameter("p");
        if(param !=null)
        requiredAction=param;
        
        if(requiredAction==null)
        {
            gradeform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            SchoolGrade grade=getSchoolGrade(userName,gradeform);
            daoutil.getSchoolGradeDaoInstance().saveSchoolGrade(getSchoolGrade(userName,gradeform));
            saveUserActivity(userName,moduleName,"Saved new SchoolGrade record with name "+grade.getGradeName());
            generateSchoolGradeList(session);
            gradeform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            SchoolGrade grade=getSchoolGrade(userName,gradeform);
            grade.setId(gradeform.getId());
            daoutil.getSchoolGradeDaoInstance().updateSchoolGrade(grade);
            saveUserActivity(userName,moduleName,"Modified SchoolGrade record with name "+grade.getGradeName());
            generateSchoolGradeList(session);
            gradeform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("de"))
        {
            SchoolGrade grade=getSchoolGrade(userName,gradeform);
            grade.setId(gradeform.getId());
            daoutil.getSchoolGradeDaoInstance().deleteSchoolGrade(grade);
            saveUserActivity(userName,moduleName,"Requested SchoolGrade record with name "+grade.getGradeName()+" be deleted");
            gradeform.reset(mapping, request);
            generateSchoolGradeList(session);
            gradeform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ed"))
        {
            SchoolGrade grade=daoutil.getSchoolGradeDaoInstance().getSchoolGrade(id);
            if(grade !=null)
            {
                gradeform.setId(grade.getId());
                gradeform.setGradeName(grade.getGradeName());
                gradeform.setGradeLevel(grade.getGradeLevel());
                setButtonState(session,"true","false");
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            daoutil.getSchoolGradeDaoInstance().deleteSchoolGrade(getSchoolGrade(userName,gradeform));
            gradeform.reset(mapping, request);
            //generateSiteSetupList(session);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private SchoolGrade getSchoolGrade(String userName,SchoolGradeForm gradeform)
    {
        SchoolGrade grade=new SchoolGrade();
        grade.setRecordedBy(userName);
        grade.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        grade.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        grade.setGradeName(gradeform.getGradeName());
        grade.setGradeLevel(gradeform.getGradeLevel());
        return grade;
    }
    private void generateSchoolGradeList(HttpSession session)
    {
        try
        {
            DaoUtility daoutil=new DaoUtility();
            List list=daoutil.getSchoolGradeDaoInstance().getAllSchoolGrades();
            if(list==null)
            list=new ArrayList();
            session.setAttribute("schoolGradeList", list);
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
            session.setAttribute("schoolGradeSaveDisabled", saveDisabled);
            session.setAttribute("schoolGradeModifyDisabled", modifyDisabled);
        }
        else
        {
            session.setAttribute("schoolGradeSaveDisabled", "true");
            session.setAttribute("schoolGradeModifyDisabled", modifyDisabled);
        }
    }
}
