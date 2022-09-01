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
public class XMLDataImportForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private FormFile uploadedFile;
    private String partnerCode;
    private int ovcOption;
    private int hheOption;
    private int metadataOption;
    private int householdServiceOption;
    private int childServiceOption;
    private int riskAssessmentOption;
    private int quarterlyEnrollmentStatusOption;
    
    public XMLDataImportForm() {
        super();
        // TODO Auto-generated constructor stub
    }

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

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public int getHheOption() {
        return hheOption;
    }

    public void setHheOption(int hheOption) {
        this.hheOption = hheOption;
    }

    public int getOvcOption() {
        return ovcOption;
    }

    public void setOvcOption(int ovcOption) {
        this.ovcOption = ovcOption;
    }

    public int getMetadataOption() {
        return metadataOption;
    }

    public void setMetadataOption(int metadataOption) {
        this.metadataOption = metadataOption;
    }

    public int getChildServiceOption() {
        return childServiceOption;
    }

    public void setChildServiceOption(int childServiceOption) {
        this.childServiceOption = childServiceOption;
    }

    public int getHouseholdServiceOption() {
        return householdServiceOption;
    }

    public void setHouseholdServiceOption(int householdServiceOption) {
        this.householdServiceOption = householdServiceOption;
    }

    public int getRiskAssessmentOption() {
        return riskAssessmentOption;
    }

    public void setRiskAssessmentOption(int riskAssessmentOption) {
        this.riskAssessmentOption = riskAssessmentOption;
    }

    public int getQuarterlyEnrollmentStatusOption() {
        return quarterlyEnrollmentStatusOption;
    }

    public void setQuarterlyEnrollmentStatusOption(int quarterlyEnrollmentStatusOption) {
        this.quarterlyEnrollmentStatusOption = quarterlyEnrollmentStatusOption;
    }

    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    uploadedFile=null;
    partnerCode=null;
    ovcOption=3;
    hheOption=3;
    metadataOption=4;
    childServiceOption=3;
    householdServiceOption=3;
    riskAssessmentOption=3;
    quarterlyEnrollmentStatusOption=2;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()!=null && this.getActionName().equalsIgnoreCase("upload"))
        {
            if(this.getPartnerCode()==null || this.getPartnerCode().trim().length()<3 || this.getPartnerCode().trim().equalsIgnoreCase("select"))
            errors.add("partnerCode", new ActionMessage("errors.partnerCode.requiredForImport"));
            else if(this.getUploadedFile()==null)
            errors.add("uploadedFile", new ActionMessage("errors.file.required"));
        }
        return errors;
    }
}
