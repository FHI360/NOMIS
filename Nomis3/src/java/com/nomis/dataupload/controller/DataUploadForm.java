/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.dataupload.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author smomoh
 */
public class DataUploadForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String uploadType;
    private FormFile uploadedFile;
    
    public DataUploadForm() {
        super();
        // TODO Auto-generated constructor stub
    }
public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public FormFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(FormFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    uploadType=null;
    uploadedFile=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        return errors;
    }
}
