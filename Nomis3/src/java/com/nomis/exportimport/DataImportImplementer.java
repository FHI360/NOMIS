/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.DataImportIndicator;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatabaseConstant;
import com.nomis.ovc.util.FileManager;
import com.nomis.ovc.util.TaskManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class DataImportImplementer 
{
public List importData(String currentUserName,int hheOption,int ovcOption,int metadataOption,int householdServiceOption,int childServiceOption,int riskAssessmentOption, boolean updateQuarterlyEnrollmentStatus)
{
    System.err.println("inside NOMIS3 processImportFiles-----");
    //String userName=currentUserName;
    List mainList=new ArrayList();
    String currentFile=null;
    DaoUtility util=new DaoUtility();
    AppUtility appUtil=new AppUtility();
    if(!TaskManager.isDatabaseLocked())
    {
        //XMLDataImportManager xdim=new XMLDataImportManager();
        System.err.println("NOMIS3 Database is not locked-----");
        int fileHheOption=hheOption;
        int fileOvcOption=ovcOption;
        int fileMetadataOption=metadataOption;
        int fileHouseholdServiceOption=householdServiceOption;
        int fileChildServiceOption=childServiceOption;
        int fileRiskAssessmentOption=riskAssessmentOption;
        int quarterlyEnrollmentStatusOption=1;
        DataImportFileUploadManager difum=null;
        XMLDataImportManager xdim=new XMLDataImportManager();
        try
        {
            //get list of uploaded files from the database
            List uploadedFileList=util.getDataImportUploadManagerDaoInstance().getAllDataImportFileUploadManager(0);
            //String[] uploadedFileList=appUtil.listFiles(appUtil.getImportFilePath());
            if(uploadedFileList !=null && !uploadedFileList.isEmpty())
            {
                System.err.println(uploadedFileList.size()+" available for processing");
                AppUtility.setCurrentImportFileName("About to import uploaded files...");
                String importFilePath=null;
                String destinationDirectory=null;
                String fileName=null;
                String partnerCode=null;
                for(int i=0; i<uploadedFileList.size(); i++)
                {
                    difum=(DataImportFileUploadManager)uploadedFileList.get(i);
                    fileName=difum.getImportFileName();
                    partnerCode=difum.getPartnerCode();
                    currentFile=fileName;
                    if(fileName !=null)
                    {
                        
                        importFilePath=appUtil.getImportFilePath()+appUtil.getFileSeperator()+fileName;
                        AppUtility.setCurrentImportFileName(fileName);
                        System.err.println("importFilePath in XMLDataImportManager is "+importFilePath);
                        File file=new File(importFilePath);
                        if(!file.exists())
                        {
                            //File was initially uploaded to the folder but has been removed. Set record status to 2
                            difum.setImportStatus(2);
                            updateDataImportFileUploadManager(difum);
                            continue;
                        }
                        else
                        xdim.dataImportIndicator.setImportFile(file);
                        //AppUtility.setCurrentImportFileName(fileName);
                        TaskManager.setDatabaseLocked(true);
                        if(fileName.indexOf(".zip") !=-1)
                        fileName=fileName.substring(0, fileName.indexOf(".zip"));
                        destinationDirectory=appUtil.getZipExtractsFilePath()+appUtil.getFileSeperator()+fileName;
                        unzipFile(importFilePath,destinationDirectory);
                        file=null;
                        String options=difum.getImportOptions();
                        System.err.println("options is "+options);
                        if(options !=null && options.indexOf(",") !=-1)
                        {
                            String[] optionsArray=options.split(",");
                            if(optionsArray !=null && optionsArray.length>2)
                            {
                                fileHheOption=Integer.parseInt(optionsArray[0]);
                                fileOvcOption=Integer.parseInt(optionsArray[1]);
                                fileMetadataOption=Integer.parseInt(optionsArray[2]);
                                if(optionsArray.length>3)
                                {
                                    fileHouseholdServiceOption=Integer.parseInt(optionsArray[3]);
                                    fileChildServiceOption=Integer.parseInt(optionsArray[4]);
                                    fileRiskAssessmentOption=Integer.parseInt(optionsArray[5]);
                                }
                                if(optionsArray.length>6)
                                {
                                    quarterlyEnrollmentStatusOption=Integer.parseInt(optionsArray[6]);
                                    if(quarterlyEnrollmentStatusOption==1)
                                    {
                                        updateQuarterlyEnrollmentStatus=true;
                                    }
                                    else
                                    updateQuarterlyEnrollmentStatus=false;
                                }
                            }
                            else
                            {
                                fileHheOption=hheOption;;
                                fileOvcOption=ovcOption;
                                fileMetadataOption=metadataOption;
                                fileHouseholdServiceOption=householdServiceOption;
                                fileChildServiceOption=childServiceOption;
                                fileRiskAssessmentOption=riskAssessmentOption;
                            }
                        }
                        else
                        {
                            fileHheOption=hheOption;
                            fileOvcOption=ovcOption;
                            fileMetadataOption=metadataOption;
                            fileHouseholdServiceOption=householdServiceOption;
                            fileChildServiceOption=childServiceOption;
                            fileRiskAssessmentOption=riskAssessmentOption;
                        }
                        //if(isNomis3File(destinationDirectory))
                        //{
                            //The file is a Nomis3 export
                            Thread cboThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.cboCode,destinationDirectory,fileHheOption,partnerCode));
                            cboThread.start();
                            Thread wardThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.wardCode,destinationDirectory,fileHheOption,partnerCode));
                            wardThread.start();
                            Thread hheThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.householdEnrollmentCode,destinationDirectory,fileHheOption,partnerCode));
                            hheThread.start();
                            Thread ahmThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.adultHouseholdMemberCode,destinationDirectory,fileHheOption,partnerCode));
                            ahmThread.start();
                            Thread ovcThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.childEnrollmentCode,destinationDirectory,fileHheOption,partnerCode));
                            ovcThread.start();
                            Thread hhsThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.householdServiceCode,destinationDirectory,fileHheOption,partnerCode));
                            hhsThread.start();
                            Thread childServiceThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.childServiceCode,destinationDirectory,fileHheOption,partnerCode));
                            childServiceThread.start();
                            Thread hivRiskAssessmentThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.hivRiskAssessmentCode,destinationDirectory,fileHheOption,partnerCode));
                            hivRiskAssessmentThread.start();
                            Thread hhReferralThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.householdReferralCode,destinationDirectory,fileHheOption,partnerCode));
                            hhReferralThread.start();
                            Thread careAndSupportThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.careAndSupportChecklistCode,destinationDirectory,fileHheOption,partnerCode));
                            careAndSupportThread.start();
                            Thread cgEmergencyFundThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.cgEmergencyFundCode,destinationDirectory,fileHheOption,partnerCode));
                            cgEmergencyFundThread.start();
                            Thread householdVulnerabilityAssessmentThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.householdVulnerabilityAssessmentCode,destinationDirectory,fileHheOption,partnerCode));
                            householdVulnerabilityAssessmentThread.start();
                            Thread rhvaThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.revisedHvaCode,destinationDirectory,fileHheOption,partnerCode));
                            rhvaThread.start();
                            Thread carePlanAchievementThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.careplanAchievementCode,destinationDirectory,fileHheOption,partnerCode));
                            carePlanAchievementThread.start();
                            Thread childEduPerformanceThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.childEducationalPerformanceCode,destinationDirectory,fileHheOption,partnerCode));
                            childEduPerformanceThread.start();
                            Thread benStatusUpdateThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.beneficiaryStatusUpdateCode,destinationDirectory,fileHheOption,partnerCode));
                            benStatusUpdateThread.start();
                            Thread hhcpThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.hhCarePlanCode,destinationDirectory,fileHheOption,partnerCode));
                            hhcpThread.start();
                            Thread nutritionAssessmentThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.nutritionAssessmentCode,destinationDirectory,fileHheOption,partnerCode));
                            nutritionAssessmentThread.start();
                            Thread facilityOvcOfferThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.facilityOvcOfferCode,destinationDirectory,fileHheOption,partnerCode));
                            facilityOvcOfferThread.start();
                            Thread csiThread=new Thread(new XMLDataImportThreadManager(xdim,currentUserName,DatabaseConstant.childStatusIndex,destinationDirectory,fileHheOption,partnerCode));
                            csiThread.start();
                            
                            
                            /*mainList.addAll(xdim.importHouseholdEnrollmentRecordsFromXML(destinationDirectory,fileHheOption));
                            mainList.addAll(xdim.importAdultHouseholdMembersRecordsFromXML(destinationDirectory,fileHheOption));
                            mainList.addAll(xdim.importOvcRecordsFromXML(destinationDirectory,fileOvcOption));
                            mainList.addAll(xdim.importHouseholdServiceRecordsFromXML(destinationDirectory,fileHouseholdServiceOption));
                            mainList.addAll(xdim.importOvcServiceRecordsFromXML(destinationDirectory,fileChildServiceOption));
                            mainList.addAll(xdim.importHivRiskAssessmentRecordsFromXML(destinationDirectory,fileRiskAssessmentOption));
                            mainList.addAll(xdim.importReferralRecordsFromXML(destinationDirectory));
                            mainList.addAll(xdim.importCareAndSupportRecordsFromXML(destinationDirectory));
                            mainList.addAll(xdim.importCaregiverAccessToEmergencyFundRecordsFromXML(destinationDirectory));
                            mainList.addAll(xdim.importHouseholdVulnerabilityAssessmentRecordsFromXML(destinationDirectory,fileHheOption));
                            mainList.addAll(xdim.importRevisedHouseholdVulnerabilityAssessmentRecordsFromXML(destinationDirectory,fileHheOption));
                            mainList.addAll(xdim.importCareplanAchievementChecklistRecordsFromXML(destinationDirectory));
                            mainList.addAll(xdim.importChildEducationPerformanceAssessmentRecordsFromXML(destinationDirectory));
                            mainList.addAll(xdim.importBeneficiaryStatusUpdateRecordsFromXML(destinationDirectory));
                            mainList.addAll(xdim.importHouseholdCareplanRecordsFromXML(destinationDirectory));
                            System.err.println("fileMetadataOption is "+fileMetadataOption);*/
                            /*difum.setImportStatus(1);
                            updateDataImportFileUploadManager(difum);
                            FileManager.moveFileToAnotherDirectory(importFilePath, appUtil.getImportDoneDirectoryPath());*/
                        //}
                        //else
                        //{
                            //The file is a Nomis2 or earlier export
                            //System.err.println("File is NOMIS2 file");
                            /*LegacyImportManager ldim=new LegacyImportManager(userName);
                            ldim.processCBORecords(destinationDirectory);
                            ldim.processWardRecords(destinationDirectory);
                            ldim.processHouseholdEnrollment(destinationDirectory, partnerCode);
                            ldim.processHouseholdVulnerabilityAssessment(destinationDirectory, partnerCode);
                            ldim.processCaregiverRecords(destinationDirectory, partnerCode);
                            ldim.processOvcRecords(destinationDirectory, partnerCode);
                            ldim.processOvcServiceRecords(destinationDirectory, partnerCode);
                            ldim.processHouseholdServiceRecords(destinationDirectory);
                            ldim.processHivStatusUpdateRecords(destinationDirectory);
                            ldim.processWithdrawalStatusRecords(destinationDirectory);
                            ldim.processHivRiskAssessmentRecords(destinationDirectory);
                            ldim.processReferralRecordsRecords(destinationDirectory);
                            ldim.processCareplanAchievementRecords(destinationDirectory);
                            ldim.processCaregiverExpenditureAndSchoolAttendanceRecords(destinationDirectory);
                            ldim.processNutritionAssessmentRecords(destinationDirectory);
                            ldim.processChildStatusIndexRecords(destinationDirectory);
                            ldim.processCareAndSupportRecords(destinationDirectory);*/
                       // }
                        difum.setImportStatus(1);
                        updateDataImportFileUploadManager(difum);
                        FileManager.moveFileToAnotherDirectory(importFilePath, appUtil.getImportDoneDirectoryPath());
                    }
                    
                }
                TaskManager.setDatabaseLocked(false);
                importData(currentUserName,hheOption,ovcOption,metadataOption,fileHouseholdServiceOption,fileChildServiceOption,fileRiskAssessmentOption,updateQuarterlyEnrollmentStatus); 
            }
            else
            {
                
                    //AppUtility.setCurrentImportFileName(null); 
                    TaskManager.setDatabaseLocked(false);
                
            }
            TaskManager.setDatabaseLocked(false);
            //uploadedFileList=util.getDataImportUploadManagerDaoInstance().getAllDataImportFileUploadManager(0);
        }
        catch(Exception ex)
        {
            difum.setImportStatus(3);
            updateDataImportFileUploadManager(difum);
            NomisLogManager.logStackTrace(ex);
            ex.printStackTrace();
            AppUtility.setCurrentImportFileName("An error occured on file "+currentFile+"...."); 
        }
        TaskManager.setDatabaseLocked(false);
        //AppUtility.setCurrentImportFileName(null);
        //AppUtility.setCurrentImportRecordName("All files imported");
    }
    if(updateQuarterlyEnrollmentStatus)
    {
        //processQuarterlyEnrollmentStatus(childServiceList,AppConstant.OVC_TYPE_NUM,userName);
        //processQuarterlyEnrollmentStatus(householdServiceList,AppConstant.CAREGIVER_TYPE_NUM,userName);
    }
    /*DataImportIndicator dataImportIndicator=AppUtility.getDataImportIndicator();
    if(dataImportIndicator==null || (dataImportIndicator.getImportStartFlag()<=dataImportIndicator.getImportEndFlag()))
    {
        AppUtility.setCurrentImportFileName(null); 
        TaskManager.setDatabaseLocked(false);
    }*/
    //AppUtility.setCurrentImportFileName(null);
    //setEnrollmentManagerForChildrenList(mainChildList,userName);
    //setEnrollmentManagerForAdultList(mainHouseholdList,userName);
    //setInitialEnrollmentStatusManager(childList,userName,AppConstant.OVC_TYPE_NUM);
    //setInitialEnrollmentStatusManager(householdList,userName,AppConstant.CAREGIVER_TYPE_NUM);
    return mainList;
} 
public boolean isNomis3File(String destinationDirectory)
{
    boolean isNomis3=false;
    AppUtility appUtil=new AppUtility();
    String fileSeperator=appUtil.getFileSeperator();
    String nomis2ExportFileName="HouseholdEnrollment";
    String nomis3ExportFileName="HouseholdEnrollments";    
    String nomis2FilePath=destinationDirectory+fileSeperator+nomis2ExportFileName+fileSeperator+nomis2ExportFileName+".xml";
    String nomis3FilePath=destinationDirectory+fileSeperator+nomis3ExportFileName+fileSeperator+nomis3ExportFileName+".xml";
    //System.err.println("nomis2FilePath is "+nomis2FilePath);
    //System.err.println("nomis3FilePath is "+nomis3FilePath);
    try
	{
                File file=new File(nomis2FilePath);
                if(file.exists())
                {
                    System.err.println("Export is NOMIS2");
                }
                else
                {
                    file=new File(nomis3FilePath);
                    if(file.exists())
                    {
                        isNomis3=true;
                        System.err.println("Export is NOMIS3"); 
                    }
                }
	}
	catch (Exception ex)
	{
            ex.printStackTrace ();
	}
    return isNomis3;
}
public void unzipFile(String fileName,String destinationDirectory)
{
    AppUtility appUtil=new AppUtility();
    AppUtility.setCurrentImportRecordName("Extracting data from zip files...");
    appUtil.createDirectories(destinationDirectory);
    appUtil.createExportImportDirectories();
    ZipHandler zipHandler=new ZipHandler();
    zipHandler.unZipFile(fileName,destinationDirectory);
}
private void updateDataImportFileUploadManager(DataImportFileUploadManager difum)
{
    try
    {
        DaoUtility util=new DaoUtility();
        util.getDataImportUploadManagerDaoInstance().updateDataImportFileUploadManager(difum);
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
        //difum.setImportStatus(3);
        //util.getDataImportUploadManagerDaoInstance().updateDataImportFileUploadManager(difum);
    }
}
}
