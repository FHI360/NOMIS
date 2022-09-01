/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.utils;

import com.nomis.ovc.util.AppManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smomoh
 */
public class LegacyAppUtility 
{
    private static String seperator="\\";
    private static String dbRootDirectory="C:"+seperator+"Nomis";
    public static String resourceString;
    private static String resourceLocation=dbRootDirectory;
    private static String dbBackupDirectory="C:"+seperator+"Nomis_backup";
    String[] validLetters={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int[] validNumbers={0,1,2,3,4,5,6,7,8,9};
    String[] specialCharacters={"@","#","$","%","^","&","*","!","~","`","+","=","\\","/","?","'","\"",".","[","]","{","}",","};
    private String metadataDirName="Metadata";
    private final static String environmentFileName="StartupConfig.txt";
    public static boolean CANWRITETODATABASE=false;
    public static boolean importHivOnly=false;//setCurrentImportFileName
    public static String currentImportFileName=null;
    public static String currentImportRecordName=null;
    public static HttpServletRequest request=null;
    
    public static String getResourceLocation()
    {
        return resourceLocation;
    }
    public String getZipExtractFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"ZipExtract";
    }
    public String getExportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Export";
    }
    public String getDxExportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"DxExport";
    }
    public void setDbRootDirectory(String newDirectory)
    {
        dbRootDirectory=newDirectory+seperator+"Nomis";
    }
    public String getDbRootDirectory()
    {
        return getResourceLocation();
    }
    public void setResourceDirectory(String newDirectory)
    {
        resourceLocation=newDirectory+seperator+"Nomis";
    }
    
    public String getDatabaseDirectory()
    {
        return getResourceLocation()+seperator+"dbs";
    }
    public String getDatabaseBackupDirectory()
    {
        createDirectories(dbBackupDirectory);
        return dbBackupDirectory;
    }
    public String getFileSeperator()
    {
        return seperator;
    }
    public void setFileSeperator(String seperator)
    {
        this.seperator=seperator;
    }
    public void createDirectories(String directoryPath)
    {
        try
        {
            boolean success = (new File(directoryPath)).mkdirs();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
    }

public void createDirectory(String directoryPath)
{
    try
    {
        boolean success = (new File(directoryPath)).mkdir();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
}
public void createZipExtractDirectories()
    {
        createDirectories(getZipExtractFilePath());
    }
    public void createExportImportDirectories()
    {
      createDirectories(getExportFilePath()+seperator+"Enrollment");
      createDirectories(getExportFilePath()+seperator+"CsiScore");
      createDirectories(getExportFilePath()+seperator+"OvcService");
      createDirectories(getExportFilePath()+seperator+"OrganizationRecords");
      createDirectories(getExportFilePath()+seperator+"Wards");
      createDirectories(getExportFilePath()+seperator+"OrganizationalAssessment");
      createDirectories(getExportFilePath()+seperator+"FollowupSurveyRecords");
      createDirectories(getExportFilePath()+seperator+"OvcReferral");
      createDirectories(getExportFilePath()+seperator+"OvcSchool");
      createDirectories(getExportFilePath()+seperator+"HouseholdVulnerabilityIndex");
      createDirectories(getExportFilePath()+seperator+"HouseholdService");
      createDirectories(getExportFilePath()+seperator+"HouseholdEnrollment");
      createDirectories(getExportFilePath()+seperator+"HouseholdVulnerabilityAssessment");
      createDirectories(getExportFilePath()+seperator+"Caregiver");
      createDirectories(getExportFilePath()+seperator+"NutritionAssessment");
      createDirectories(getExportFilePath()+seperator+"HouseholdFollowupAssessment");
      createDirectories(getExportFilePath()+seperator+"OvcWithdrawal");
      createDirectories(getExportFilePath()+seperator+"DeletedRecord");
      createDirectories(getExportFilePath()+seperator+"OvcTBHIVScreeningChecklist");
      createDirectories(getExportFilePath()+seperator+"CaregiverTBHIVScreeningChecklist");
      createDirectories(getExportFilePath()+seperator+"GraduationCheckList");
      createDirectories(getExportFilePath()+seperator+"ReferralDirectory");
      createDirectories(getExportFilePath()+seperator+"HivRiskAssessmentChecklist");
      createDirectories(getExportFilePath()+seperator+"CareplanAchievement");
      createDirectories(getExportFilePath()+seperator+"CareAndSupportChecklist");
      createDirectories(getExportFilePath()+seperator+"CaregiverExpenditureAndSchoolAttendance");
      createDirectories(getExportFilePath()+seperator+"DataExportSummary");
      //DataTransfer
      createDirectories(getImportFilePath());
      //createDirectories(getDxImportFilePath());
      //createDirectories(getDxExportFilePath());
      createDirectories(getResourceLocation()+seperator+"Transfer"+seperator+"Zips");
      createDirectories(getConfigurationDirectory());
      //createDirectories(getTrainingDataXmlFolderPathFile());
      //createDirectories(getTrainingMetadataXmlFolderPathFile());
      //createMetadataExportImportDirectories();
      System.err.println("ExportImportDirectories created");
    }
    public String getImportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Import";
    }
    public String getImportDoneDirectoryPath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Done";
    }
    public String getLoginConfigFile()
    {
        return getConfigurationDirectory()+seperator+"loginconfig.txt";
    }
    public static String getConfigurationDirectory()
    {
        return getResourceLocation()+seperator+"conf";
    }
    public String getCurrentUser(HttpSession session)
    {
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        return userName;
    }
    public String[] listFiles(String directoryPath)
    {
        File file=new File(directoryPath);
        String[] files=null;
        StringBuilder s=new StringBuilder();

        if(file.isDirectory())
        {
            files=file.list();
        }
        //if(files==null)
        
        return files;
    }
    public void deleteFiles(String directoryPath)
    {
        File file=new File(directoryPath);
        String[] files=null;
        if(file.isDirectory())
        {
         files=file.list();
         File file2=null;
         String filePath=null;
         for(int i=0; i<files.length; i++)
         {
             filePath=directoryPath+"\\"+files[i];
             file2=new File(filePath);      
             if(file2.isDirectory())
             deleteFiles(file2.getPath());
             file2.delete();
         }
        }
        else
        file.delete();
    }
    public boolean copyAndPasteFiles(String source,String destination)
    {
        String seperator="\\";
        if(source !=null && source.indexOf("/") !=-1)
        seperator="/";
        String[] files=null;
        File sourceDirectory=new File(source);
        File destinationFile=new File(destination);
        if(!destinationFile.exists())
        destinationFile.mkdirs();
        File sourceFile;
        files=sourceDirectory.list();
        for(int i=0; i<files.length; i++)
        {
           sourceFile =new File(source+seperator+files[i]);
           if(sourceFile.isDirectory())
           {
               destinationFile=new File(destination+seperator+files[i]);
               destinationFile.mkdirs();
               copyAndPasteFiles(sourceFile.getAbsolutePath(),destinationFile.getAbsolutePath());
           }
           else
           {
               String parent=sourceFile.getParent();
               String immediateParentFolder=parent.substring(parent.lastIndexOf(seperator)+1, parent.length());
               System.err.println("destinationFile is "+destinationFile);
               System.err.println("immediateParentFolder is "+immediateParentFolder);
               String destPath=destinationFile.getAbsolutePath().substring(0,destinationFile.getAbsolutePath().indexOf(immediateParentFolder));
               System.err.println("destPath is "+destPath);
               destPath=destPath+immediateParentFolder+seperator+files[i];
               System.err.println("destPath2 is "+destPath);
               writeFilesAsStream(sourceFile.getPath(), destPath);
           }
        }
        return true;
    }
    public void writeFilesAsStream(String sourceFileName,String destinationFileName)
    {
        FileOutputStream fos=null;
        try
        {
            File file=new File(sourceFileName);
            byte[] outputByte=new byte[(int)file.length()];
            FileInputStream fileInputStream=new FileInputStream(file);
            fileInputStream.read(outputByte);
            fileInputStream.close();

            fos=new FileOutputStream(destinationFileName);
            fos.write(outputByte);
            fos.close();
        }
        catch(FileNotFoundException fnfe)
        {
            try
            {
                fnfe.printStackTrace();
                fos.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Other exception occured "+ex.getMessage());
        }
    }
}
