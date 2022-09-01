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
public class CustomIndicatorReportForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String period;
    private String[] level2Ous;
    private String partnerCode;
    private String[] indicators;
    
    public CustomIndicatorReportForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String[] getIndicators() {
        return indicators;
    }

    public void setIndicators(String[] indicators) {
        this.indicators = indicators;
    }

    public String[] getLevel2Ous() {
        return level2Ous;
    }

    public void setLevel2Ous(String[] level2Ous) {
        this.level2Ous = level2Ous;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    period="select";
    level2Ous=null;
    indicators=null;
    partnerCode=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(getActionName()==null || getActionName().equalsIgnoreCase("level2OuList"))
        return errors;
        else if(getPeriod()==null || this.getPeriod().trim().equalsIgnoreCase("select"))
        errors.add("period", new ActionMessage("error.cirperiod.required"));
        else if(this.getLevel2Ous()==null || this.getLevel2Ous().length<1)
        errors.add("level2Ous", new ActionMessage("error.cirLevel2Ou.required"));
        return errors;
    }
}
