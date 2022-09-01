/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.login.controller;

import com.nomis.operationsManagement.OrganizationUnitHierarchyManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatabaseUtilities;
import java.io.File;
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
public class LoginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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
        LoginForm lform=(LoginForm)form;
        String moduleName="User login";
        HttpSession session=request.getSession();
        String requiredAction=lform.getActionName();
        String userName=lform.getUserName();
        String password=lform.getPassword();
        
        AppUtility appUtil=new AppUtility();
        System.err.println("requiredAction is "+requiredAction);
        loadDatabase();
        
        if(requiredAction==null)
        {
            lform.reset(mapping, request);
            return mapping.findForward(FAILURE);
        }
        else if(requiredAction.equalsIgnoreCase("login"))
        {
            String dbsource=getServlet().getServletContext().getRealPath("/Resources/dbs");
            String confSourceDirectory=getServlet().getServletContext().getRealPath("/Resources/conf");
            String facilitySourceDirectory=getServlet().getServletContext().getRealPath("/Resources/Resources");
            String seperator="/";
            if(dbsource !=null && dbsource.indexOf("\\") !=-1)
            seperator="\\";
            appUtil.setFileSeperator(seperator);
            //set default database directory
            String defaultDbLocationPath="C:";

            File defaultDbLocation=new File(defaultDbLocationPath);
            //check if it is not a Windows computer
            if(!defaultDbLocation.exists())
            {
                //get the root directory for the context path
                defaultDbLocationPath=dbsource.substring(0,dbsource.indexOf(seperator));
            }
            File rootFolder=new File(defaultDbLocationPath);
            if(!rootFolder.canWrite())
            {
                if(appUtil.getUserHomeDirectory() !=null)
                defaultDbLocationPath=appUtil.getUserHomeDirectory();
            }
            System.err.println("defaultDbLocationPath is "+defaultDbLocationPath);
            
            //set the default location for the Nomis folder
        appUtil.setResourceDirectory(defaultDbLocationPath);
        //check for the hibernate config file
        //AppUtility.getResourceLocation()+seperator+
        File hibernateFile=new File(AppUtility.getHibernateFile());
        String destination=appUtil.getDatabaseDirectory();
        String confDestinationDirectory=AppUtility.getConfigurationDirectory();
        String facilitiesDestinationDirectory=AppUtility.getResourcesDirectory();
        if(!hibernateFile.exists())
        {
            appUtil.createDatabase(dbsource, destination);
            if(!appUtil.loginConfigFileExists())
            {
                appUtil.copyAndPasteDbFiles(confSourceDirectory, confDestinationDirectory);
                appUtil.copyAndPasteDbFiles(facilitySourceDirectory, facilitiesDestinationDirectory);
            }
            //DatabaseUtilities dbUtils=new DatabaseUtilities();
            int created=appUtil.createHibernateFile();
            if(created==2)
            {
                //List list=AppUtility.getHibernateConnectionDetails();
                //DatabaseUtilities.setConnectionParameters(list);
                //dbUtils.runDatabaseUpdateForVersion21();
                System.err.println("Hibernate file created");
            }
            else if(created==1)
            {
                System.err.println("Hibernate file not created but directory created");
            }
            if(created==0)
            {
                System.err.println("Hibernate directory not created");
            }
        }
        else
        {
            //List list=AppUtility.getHibernateConnectionDetails();
            //DatabaseUtilities.setConnectionParameters(list);
            //dbUtils.runDatabaseUpdateForVersion21();
        }
        AppUtility.initializeDatabaseConnectionManager();
        OrganizationUnitHierarchyManager.setOrganizationUnitHierachyAttributes(session);

        AppUtility.request=request;
        appUtil.createExportImportDirectories();
        appUtil.createLogDirectory();
        DatabaseUtilities dbutils=new DatabaseUtilities();
        dbutils.executeDatabaseUpdate();
            User user=null;
            String accountDisabledMessage="Account is disbaled. Contact Administrator";
            if(userName !=null && password !=null && (userName.trim().equalsIgnoreCase("admin") && password.trim().equalsIgnoreCase("admin")))
            {
                //if use of default account is allowed as indicated in the loginconfig file, then proceed to ask the user to create new account
                if(appUtil.isDefaultAccountEnabled())
                {
                    user=new User();
                    user.setUsername(userName);
                    user.setPassword(password);
                    user.setAccountStatus(AppConstant.ENABLEDACCOUNTSTATUS);
                    user.setAccessPrivileges("createusers,norolexxxxx");
                    appUtil.disableDefaultAccount();
                    //redirect to account creation page to create a new user account
                    return (mapping.findForward("createuser"));
                }
                else
                {
                    request.setAttribute("failedLogin", accountDisabledMessage);
                   return mapping.findForward(FAILURE);
                }
            }
            DaoUtility util=new DaoUtility();
            if(user==null)
            user=util.getUserDaoInstance().getUser(userName, password);
            if(user !=null)
            {
                if(user.getAccountStatus() !=null && user.getAccountStatus().equalsIgnoreCase(AppConstant.ENABLEDACCOUNTSTATUS))
                {
                    session.setAttribute("USER", user);
                    lform.reset(mapping, request);
                }
                else
                {
                   request.setAttribute("failedLogin", accountDisabledMessage);
                   return mapping.findForward(FAILURE); 
                }
                return mapping.findForward(SUCCESS);
            }
            else
            {
                request.setAttribute("failedLogin", "Invalid username or password");
                return mapping.findForward(FAILURE);
            }
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void loadDatabase()
    {
        AppUtility appUtil=new AppUtility();
        String dbsource=getServlet().getServletContext().getRealPath("/Resources/dbs");
        String confSourceDirectory=getServlet().getServletContext().getRealPath("/Resources/conf");
        //System.err.println("dbsource is "+dbsource);
        //System.err.println("confSourceDirectory is "+confSourceDirectory);
        String seperator="/";
        if(dbsource !=null && dbsource.indexOf("\\") !=-1)
        seperator="\\";
        appUtil.setFileSeperator(seperator);
        //set default database directory
        String defaultDbLocationPath="C:";
        File defaultDbLocation=new File(defaultDbLocationPath);
        //check if it is not a Windows computer
        if(!defaultDbLocation.exists())
        {   //get the root directory for the context path
            defaultDbLocationPath=dbsource.substring(0,dbsource.indexOf(seperator));
        }
        File rootFolder=new File(defaultDbLocationPath);
        if(!rootFolder.canWrite())
        {
            if(appUtil.getUserHomeDirectory() !=null)
            defaultDbLocationPath=appUtil.getUserHomeDirectory();
        }
        System.err.println("defaultDbLocationPath is "+defaultDbLocationPath);
        //set the default location for the Nomis folder
        appUtil.setResourceDirectory(defaultDbLocationPath);
        //AppUtility.getResourceLocation()+seperator+
        File hibernateFile=new File(AppUtility.getHibernateFile());
        System.err.println("hibernateFile path is "+hibernateFile.getPath());
        String destination=appUtil.getDatabaseDirectory();
        String confDestinationDirectory=AppUtility.getConfigurationDirectory();
        if(!hibernateFile.exists())
        {
            System.err.println("destination is "+destination);
            appUtil.createDatabase(dbsource, destination);
            if(!appUtil.loginConfigFileExists())
            appUtil.copyAndPasteDbFiles(confSourceDirectory, confDestinationDirectory);

            int created=appUtil.createHibernateFile();
            if(created==2)
            {
                List list=AppUtility.getHibernateConnectionDetails();
                DatabaseUtilities.setConnectionParameters(list);
                //dbUtils.runDatabaseUpdateForVersion21();
                System.err.println("Hibernate file created");
            }
            if(created==1)
            {
                System.err.println("Hibernate file not created but directory created");
            }
            if(created==0)
            {
                System.err.println("Hibernate directory not created");
            }
        }
        else
        {
            List list=AppUtility.getHibernateConnectionDetails();
            DatabaseUtilities.setConnectionParameters(list);
        }
        
    }
}
