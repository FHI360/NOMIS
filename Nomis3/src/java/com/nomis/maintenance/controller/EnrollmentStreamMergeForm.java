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
public class EnrollmentStreamMergeForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String[] nonStdEnrollmentStreamIds;
    private String stdEnrollmentStreamId;

    
    public EnrollmentStreamMergeForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String[] getNonStdEnrollmentStreamIds() {
        return nonStdEnrollmentStreamIds;
    }

    public void setNonStdEnrollmentStreamIds(String[] nonStdEnrollmentStreamIds) {
        this.nonStdEnrollmentStreamIds = nonStdEnrollmentStreamIds;
    }
    
    public String getStdEnrollmentStreamId() {
        return stdEnrollmentStreamId;
    }

    public void setStdEnrollmentStreamId(String stdEnrollmentStreamId) {
        this.stdEnrollmentStreamId = stdEnrollmentStreamId;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    nonStdEnrollmentStreamIds=null;
    stdEnrollmentStreamId=null;
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
