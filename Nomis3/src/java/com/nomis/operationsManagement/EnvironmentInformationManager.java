/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.EnvironmentInformationProvider;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author smomoh
 */
public class EnvironmentInformationManager implements Serializable
{
    public static void loadEnvironmentInfo(HttpServletRequest request)
    {
        EnvironmentInformationProvider environmentInformationProvider=new EnvironmentInformationProvider();
        String seperator="\\";
        AppUtility appUtil=new AppUtility();
        String dbsource=getTomcatInstallationBaseDirectory(request);
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
            environmentInformationProvider.getTomcatBaseDirectory().setTaskName("Resource directory");
            environmentInformationProvider.getTomcatBaseDirectory().setProgressStatus(defaultDbLocationPath); 
            environmentInformationProvider.getCurrentTime().setTaskName("ServerTime");//getCurrentTime()
            environmentInformationProvider.getCurrentTime().setProgressStatus(getCurrentTime());
            
            environmentInformationProvider.getJavaVersion().setTaskName("Java version");
            environmentInformationProvider.getJavaVersion().setProgressStatus(getJavaVersion());
            environmentInformationProvider.getTomcatVersion().setTaskName("Tomcat version");
            environmentInformationProvider.getTomcatVersion().setProgressStatus(getTomcatVersion(request));
            environmentInformationProvider.getServerName().setTaskName("Server name");
            environmentInformationProvider.getServerName().setProgressStatus(getHostName());
            AppUtility.setEnvironmentInformationProvider(environmentInformationProvider);
    }
    public static String getTomcatInstallationBaseDirectory(HttpServletRequest request)
    {
        String tomcatInstallationDirectory=getContextPathDirectory(request,"webapps");
        if(tomcatInstallationDirectory==null)
        tomcatInstallationDirectory=getContextPathDirectory(request,"/Resources/dbs");
        return tomcatInstallationDirectory;
    }
    public static String getContextPathDirectory(HttpServletRequest request,String path)
    {
        String contextPath=request.getServletContext().getRealPath(path);
        return contextPath;
    }
    public static String getConfigurationSourceDirectory(HttpServletRequest request,String path)
    {
        String contextPath=request.getServletContext().getRealPath("/Resources/conf");
        return contextPath;
    }
    public static String getFacilitySourceDirectory(HttpServletRequest request,String path)
    {
        String contextPath=request.getServletContext().getRealPath("/Resources/Resources");
        return contextPath;
    }
    public static String getCurrentTime()
    {
        String serverTime=DateManager.getDefaultCurrentDateAndTime();
        return serverTime;
    }
    public static String getJavaVersion()
    {
        String javaVersion=System.getProperty("java.version");
        return javaVersion;
    }
    public static String getTomcatVersion(HttpServletRequest request)
    {
        String tomcatVersion=request.getServletContext().getServerInfo();
        return tomcatVersion;
    }
    public static String getHostName()
    {
        String tomcatVersion=null;
        try
        {
            tomcatVersion=InetAddress.getLocalHost().getHostName();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return tomcatVersion;
    }
}
