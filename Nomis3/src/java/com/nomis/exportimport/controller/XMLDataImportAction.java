/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport.controller;

import com.nomis.exportimport.DataImportFileUploadManager;
import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.Partner;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author smomoh
 */
public class XMLDataImportAction extends org.apache.struts.action.Action {

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
        XMLDataImportForm importForm=(XMLDataImportForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        String moduleName="Data import";
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getDataImportPriviledge().getPrivilegeId()))
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
        
        String requiredAction=importForm.getActionName();
        FormFile uploadedFile=importForm.getUploadedFile();
        //User user1=appManager.getCurrentUser(session);
        //util.getUserDaoInstance().getUser(userName);
        setPartnerList(user,session);
        if(requiredAction==null)
        {
            importForm.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("upload"))
        {
            int ovcOption=importForm.getOvcOption();
            int hheOption=importForm.getHheOption();
            int metadataOption=importForm.getMetadataOption();
            int householdServiceOption=importForm.getHouseholdServiceOption();
            int riskAssessmentOption=importForm.getRiskAssessmentOption();
            int childServiceOption=importForm.getChildServiceOption();
            int quarterlyEnrollmentStatusOption=importForm.getQuarterlyEnrollmentStatusOption();
            AppUtility appUtil=new AppUtility();
            //DaoUtility util=new DaoUtility();
            String currentDate=DateManager.getCurrentDate();
            String uploadMsg="Unable to upload file";
            if(uploadedFile !=null)
            {
               appUtil.createExportImportDirectories();
               String filePath = appUtil.getImportFilePath();
                File folder = new File(filePath);
                if(!folder.exists())
                {
                    folder.mkdir();
                }
                InputStream is=uploadedFile.getInputStream();
                String fileName=uploadedFile.getFileName();
                String addedName="_"+userName+"_"+currentDate+".zip";
                
                if(fileName !=null && fileName.endsWith(".zip"))
                {
                    fileName=fileName.replace(".zip", addedName);
                    //System.err.println("file name is "+fileName);
                    File targetFile = new File(appUtil.getImportFilePath()+"\\"+fileName);
                    OutputStream outStream = new FileOutputStream(targetFile);
                    String partnerCode=importForm.getPartnerCode();
                    byte[] buffer = new byte[8 * 1024];
                    int bytesRead;
                    //Write file to data import folder
                    while ((bytesRead = is.read(buffer)) != -1)
                    {
                        outStream.write(buffer, 0, bytesRead);
                    }
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(outStream);
                    File file = new File(appUtil.getImportFilePath()+appUtil.getFileSeperator()+fileName);
                    if(file.exists())
                    {
                        //save a record in the database for the uploaded file
                        DataImportFileUploadManager difum=new DataImportFileUploadManager();
                        difum.setDateImportCompleted(DateManager.getCurrentDateInstance());
                        difum.setDateOfUpload(DateManager.getDateInstance(currentDate));
                        difum.setImportFileName(fileName);
                        difum.setImportStatus(0);
                        difum.setPartnerCode("xxxxxxxxxxx");
                        if(partnerCode !=null)
                        difum.setPartnerCode(partnerCode);
                        difum.setTimeImportCompleted(DateManager.getDefaultCurrentDateAndTime());
                        difum.setUserName(userName);
                        difum.setImportFilePath(filePath);
                        difum.setSelectedTableCodes(null);
                        difum.setImportOptions(hheOption+","+ovcOption+","+metadataOption+","+householdServiceOption+","+childServiceOption+","+riskAssessmentOption+","+quarterlyEnrollmentStatusOption);
                        util.getDataImportUploadManagerDaoInstance().saveDataImportFileUploadManager(difum);
                        uploadMsg="Your file has been uploaded. Processing import, please wait....";
                        //AppUtility.setCurrentImportFileName(fileName);
                        saveUserActivity(userName,moduleName,"Uploaded file named "+fileName+" for import");
                            
                    }
                    //saveData(request,syncRecords,hivBirthRegUpdate);
                    request.setAttribute("dbImportMsg",uploadMsg);
                    //session.setAttribute("syncRecords",syncRecords);
             
               }  
                //Return to jsp page which will then call an ajax function to commence the import process
                return mapping.findForward(SUCCESS);
            }
        }
        return mapping.findForward(SUCCESS);
    }
    private void setPartnerList(User user,HttpSession session)
    {
        String partnerCodes=null;
        DaoUtility util=new DaoUtility();
        try
        {
            if(user !=null)
            {
                List partnerList=new ArrayList();
                //partnerList=util.getPartnerDaoInstance().getAllPartners(); 
                if(user.isSuperUser())
                {
                   partnerList=util.getPartnerDaoInstance().getAllPartners(); 
                }
                else
                {
                    partnerCodes=user.getPartnerCodes();
                    if(partnerCodes !=null)
                    {
                        String[] partnerCodeArray=partnerCodes.split(",");
                        if(partnerCodeArray !=null)
                        {
                            //List list=new ArrayList();
                            Partner partner=null;
                            for(int i=0; i<partnerCodeArray.length; i++)
                            {
                                partner=util.getPartnerDaoInstance().getPartner(partnerCodeArray[i]);
                                partnerList.add(partner);
                            }
                            //session.setAttribute("userAssignedPartners", list);
                        }
                    }
                }
                session.setAttribute("userAssignedPartners", partnerList);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("xmlDataImportButtonDisabled", saveDisabled);
        //session.setAttribute("userModifyDisabled", modifyDisabled);
    }
}
