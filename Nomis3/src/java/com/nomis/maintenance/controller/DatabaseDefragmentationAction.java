/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.TaskManager;
import com.ovc.databasemanagement.DatabaseMaintenance;
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
public class DatabaseDefragmentationAction extends org.apache.struts.action.Action {

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
        DatabaseDefragmentationForm ddf=(DatabaseDefragmentationForm)form;
        String moduleName="Disk defragmentation";
        String requiredAction=ddf.getActionName();
        HttpSession session=request.getSession();
        //AccessManager acm=new AccessManager();
        AppUtility appUtil=new AppUtility();
        
        String[] tableCodes=ddf.getTableNames();
        
        int sequence=ddf.getSequence();
        //DatabaseUtilities dbUtils=new DatabaseUtilities();
        DatabaseMaintenance dbm=new DatabaseMaintenance();
        request.setAttribute("tableList", dbm.getTableList());    
        String msg=" ";
        request.setAttribute("dbdefragmentDisabled", "false");
        request.setAttribute("dbdefragmentmsg", msg);
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        String userName=appManager.getCurrentUserName(session);
        if(AccessManager.isUserInDatabaseDefragmentationRole(user))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        if(TaskManager.isDatabaseLocked())
        {
            msg="System busy. Kindly try again later";
            return mapping.findForward(SUCCESS);
        }
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("defragment"))
        {
            msg="<label style='color: red'>No table processed</label>";
            TaskManager.setDatabaseLocked(true);
            List tableList=new ArrayList();
            if(tableCodes !=null)
            {
                for(int i=0; i<tableCodes.length; i++)
                {
                    tableList.add(tableCodes[i]);
                }
                tableList.add("dlr");
                tableList.add("brg");
            }
            System.err.println("tableList.size() is "+tableList.size());
            msg=dbm.defragmentTable(tableList, sequence);  
            saveUserActivity(userName,moduleName,"Defragmented tables ");
            TaskManager.setDatabaseLocked(false);
            request.setAttribute("dbdefragmentmsg", msg);
        }
        TaskManager.setDatabaseLocked(false);
        ddf.reset(mapping, request);
        request.setAttribute("dbdefragmentmsg", msg);
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("dbdefragSaveDisabled", saveDisabled);
        session.setAttribute("dbdefragModifyDisabled", modifyDisabled);
    }
}
