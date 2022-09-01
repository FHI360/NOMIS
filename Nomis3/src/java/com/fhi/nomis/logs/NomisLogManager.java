/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fhi.nomis.logs;

import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//import java.util.logging.LogManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author smomoh
 */
public class NomisLogManager 
{
    
    final static Logger logger = Logger.getLogger("com.fhi.nomis.logs.NomisLogManager");
    static FileWriter fw=null;
    public static void write(String data) 
    {
        try 
        {
            logger.info(data);
            
            AppUtility appUtil=new AppUtility();
            File logDir=new File(AppUtility.getLogDirectoryPath());
            String logFilePath=AppUtility.getLogDirectoryPath()+appUtil.getFileSeperator()+DateManager.getCurrentDate();
            if(!logDir.exists())
            appUtil.createLogDirectory();
            File logFile=new File(logFilePath);
            if(!logFile.exists())
            logFile.createNewFile();

            List fileContents=AppUtility.readFiles(logFile.getPath());
            if(fileContents==null)
            fileContents=new ArrayList();
            fileContents.add(DateManager.getNewDateInstance().toString());
            fileContents.add(data);
            appUtil.writeFile(logFilePath, fileContents);
            
            //fw = new FileWriter(new File(AppUtility.getLogDirectoryPath()+appUtil.getFileSeperator()+DateManager.getCurrentDate()));
            //fw.append(data);
            //fw.close();
        }
        catch (IOException e) 
        {
                logger.severe("Unable to open file.");
        }
        catch(Exception ex) 
        {
            logger.severe(ex.getMessage());
        }
    }
    public static void logStackTrace(Exception ex) 
    {
        try 
        {
            if(ex !=null)
            {
                //logger.log(logger.getLevel(), "Test log method", ex);
                List stackTraceElements=getStackTraceElement(ex);
                AppUtility appUtil=new AppUtility();
                File logDir=new File(AppUtility.getLogDirectoryPath());
                String logFilePath=AppUtility.getLogDirectoryPath()+appUtil.getFileSeperator()+DateManager.getCurrentDate();
                if(!logDir.exists())
                appUtil.createLogDirectory();
                File logFile=new File(logFilePath);
                if(!logFile.exists())
                logFile.createNewFile();

                List fileContents=AppUtility.readFiles(logFile.getPath());
                if(fileContents==null)
                fileContents=new ArrayList();
                fileContents.add("---------------------------------------------");
                fileContents.add(DateManager.getNewDateInstance().toString());
                fileContents.addAll(stackTraceElements);
                appUtil.writeFile(logFilePath, fileContents);
            }
            //fw = new FileWriter(new File(AppUtility.getLogDirectoryPath()+appUtil.getFileSeperator()+DateManager.getCurrentDate()));
            //fw.append(data);
            //fw.close();
        }
        catch (IOException e) 
        {
                logger.severe("Unable to open file.");
        }
        catch(Exception exp) 
        {
            logger.severe(ex.getMessage());
        }
    }
    private static List getStackTraceElement(Exception ex)
    {
        List list=new ArrayList();
        if(ex !=null)
        {
            
            list.add("Info: "+ex.getMessage());
            list.add("Stacktrace: ");
            StackTraceElement[] stackTrace=ex.getStackTrace();
            for(int i=0; i<stackTrace.length; i++)
            {
                list.add(stackTrace[i].toString());
            }
        }
        return list;
    }
}
