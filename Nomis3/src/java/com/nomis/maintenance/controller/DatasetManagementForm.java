/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class DatasetManagementForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String hhEnrollmentFormId;
    private String childEnrollmentFormId;
    private String childServiceFormId;
    private String hhServiceFormId;
    private String hivRiskAssessmentFormId;
    
    public DatasetManagementForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getChildEnrollmentFormId() {
        return childEnrollmentFormId;
    }

    public void setChildEnrollmentFormId(String childEnrollmentFormId) {
        this.childEnrollmentFormId = childEnrollmentFormId;
    }

    public String getChildServiceFormId() {
        return childServiceFormId;
    }

    public void setChildServiceFormId(String childServiceFormId) {
        this.childServiceFormId = childServiceFormId;
    }

    public String getHhEnrollmentFormId() {
        return hhEnrollmentFormId;
    }

    public void setHhEnrollmentFormId(String hhEnrollmentFormId) {
        this.hhEnrollmentFormId = hhEnrollmentFormId;
    }

    public String getHhServiceFormId() {
        return hhServiceFormId;
    }

    public void setHhServiceFormId(String hhServiceFormId) {
        this.hhServiceFormId = hhServiceFormId;
    }

    public String getHivRiskAssessmentFormId() {
        return hivRiskAssessmentFormId;
    }

    public void setHivRiskAssessmentFormId(String hivRiskAssessmentFormId) {
        this.hivRiskAssessmentFormId = hivRiskAssessmentFormId;
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
