/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport.controller;

import com.nomis.exportimport.MetadataImportManager;
import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.User;
import com.nomis.ovc.util.AppConstant;
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
public class MetadataImportAction extends org.apache.struts.action.Action {

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
        MetadataImportForm mdif=(MetadataImportForm)form;
        HttpSession session=request.getSession();
        AppManager appManager=new AppManager();
        
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getDataImportPriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false");
        }
        else
        {
            setButtonState(session,"true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        String userName=appManager.getCurrentUserName(session);
        String requiredAction=mdif.getActionName();
        System.err.println("requiredAction is "+requiredAction);        
        if(requiredAction==null)
        {
            mdif.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("upload"))
        {
            String msg="Meta data import successful";
           FormFile uploadedFile=mdif.getUploadedFile(); 
           if(uploadedFile !=null)
           {
               /*InputStream is=uploadedFile.getInputStream();
               ExcelReader er=new ExcelReader();
               er.uploadFacilityList(is, userName);*/
               AppUtility appUtil=new AppUtility();
               //String currentUser=userName;//appUtil.getCurrentUser(session);
                String currentDate=DateManager.getCurrentDate();
               String metadataImportParentDirectoryPath = appUtil.getMetadataImportFilePath();
               appUtil.createDirectories(metadataImportParentDirectoryPath);
               InputStream is=uploadedFile.getInputStream();
               String metadataImportFilePath=null;
                String fileName=uploadedFile.getFileName();
                String addedName="_"+userName+"_"+currentDate+".zip";
                System.err.println("fileName is "+fileName);
                
                if(fileName !=null && fileName.endsWith(".zip"))
                {
                    fileName=fileName.replace(".zip", addedName);
                    metadataImportFilePath=metadataImportParentDirectoryPath+appUtil.getFileSeperator()+fileName;
                    
                    System.err.println("metadataImportFilePath is "+metadataImportFilePath);
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
                    
                    MetadataImportManager mdim=new MetadataImportManager();
                    int success=mdim.importMetadata(metadataImportFilePath,request);
                    if(success !=1)
                    msg="Metadata import failed";
                    request.setAttribute("metadataImportMsg",msg);
                }  
                //Return to jsp page which will then call an ajax function to commence the import process
                return mapping.findForward(SUCCESS);//return mapping.findForward("dbImportSuccessMsg");
            }
           
        }
        return mapping.findForward(SUCCESS);
    }
private void setButtonState(HttpSession session,String saveDisabled)
{
    session.setAttribute("metaDataImportButtonDisabled", saveDisabled);
    //session.setAttribute("userModifyDisabled", modifyDisabled);
}
}
