/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance.controller;

import com.nomis.maintenance.DataCleanupManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
public class BulkDeleteDataUploadAction extends org.apache.struts.action.Action {

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
        BulkDeleteDataUploadForm bduform=(BulkDeleteDataUploadForm)form;
        HttpSession session=request.getSession();
        String requiredAction=bduform.getActionName();
        FormFile uploadedFile=bduform.getUploadedFile();
        
        DaoUtility util=new DaoUtility();
        String moduleName="Data import";
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        String userName=appManager.getCurrentUserName(session);
        
        if(requiredAction==null)
        {
            bduform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("upload"))
        {
            InputStream is=uploadedFile.getInputStream();
                AppUtility appUtil=new AppUtility();
                String currentDate=DateManager.getCurrentDate();
                String uploadMsg="Unable to upload file";
                String fileName=uploadedFile.getFileName();
                String addedName="_"+userName+"_"+currentDate+".zip";
                
                if(fileName !=null && fileName.endsWith(".zip"))
                {
                    String metadataImportParentDirectoryPath = appUtil.getMetadataImportFilePath();
                    appUtil.createDirectories(metadataImportParentDirectoryPath);
                    fileName=fileName.replace(".zip", addedName);
                    String metadataImportFilePath=metadataImportParentDirectoryPath+appUtil.getFileSeperator()+fileName;
                    //System.err.println("file name is "+fileName);
                    File targetFile = new File(metadataImportFilePath);
                    OutputStream outStream = new FileOutputStream(targetFile);
                    
                    byte[] buffer = new byte[8 * 1024];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1)
                    {
                        outStream.write(buffer, 0, bytesRead);
                    }
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(outStream);
                    File file = new File(metadataImportFilePath);
                    if(file.exists())
                    {
                        DataCleanupManager dcm=new DataCleanupManager();
                        int success=dcm.importAndProcessDataDeleteRequest(metadataImportFilePath,request);
                        String msg="Delete successful";
                        if(success !=1)
                        {
                            msg="Metadata import failed";
                        }
                        request.setAttribute("metadataImportMsg",msg);    
                    }
                    //saveData(request,syncRecords,hivBirthRegUpdate);
                    request.setAttribute("dbImportMsg",uploadMsg);
                    //session.setAttribute("syncRecords",syncRecords);
                } 
        }
        return mapping.findForward(SUCCESS);
    }
}
