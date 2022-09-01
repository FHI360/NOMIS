/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class DatimDataGenerationForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String[] level2OuCodes;
    private String startMth;
    private int startYear;
    private String endMth;
    private int endYear;
    
    public DatimDataGenerationForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getEndMth() {
        return endMth;
    }

    public void setEndMth(String endMth) {
        this.endMth = endMth;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String[] getLevel2OuCodes() {
        return level2OuCodes;
    }

    public void setLevel2OuCodes(String[] level2OuCodes) {
        this.level2OuCodes = level2OuCodes;
    }

    public String getStartMth() {
        return startMth;
    }

    public void setStartMth(String startMth) {
        this.startMth = startMth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    level2OuCodes=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
    {
        ActionErrors errors = new ActionErrors();
        
        return errors;
    }
}
