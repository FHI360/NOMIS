/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author smomoh
 */
public class MetadataImportForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private FormFile uploadedFile;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public FormFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(FormFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    /**
     *
     */
    public MetadataImportForm() {
        super();
        // TODO Auto-generated constructor stub
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
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
        if(this.getActionName()==null)
        return errors;
        else if(this.getUploadedFile()==null)
        errors.add("uploadedFile", new ActionMessage("error.uploadedFile.required"));
        return errors;
    }
}
