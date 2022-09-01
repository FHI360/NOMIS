/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.sitesetup.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class SiteSetupForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String userName;
    private String partner;
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    private String cboId;
    
    public SiteSetupForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String getLevel3OuId() {
        return level3OuId;
    }

    public void setLevel3OuId(String level3OuId) {
        this.level3OuId = level3OuId;
    }

    public String getLevel4OuId() {
        return level4OuId;
    }

    public void setLevel4OuId(String level4OuId) {
        this.level4OuId = level4OuId;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    userName=null;
    partner=null;
    level2OuId=null;
    level3OuId=null;
    level4OuId=null;
    cboId=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("cboList") || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList"))
        return errors;
        else if(getUserName()==null || getUserName().trim().equalsIgnoreCase("select"))
        errors.add("userName", new ActionMessage("errors.userName.required"));
        else if(this.getLevel2OuId()==null || this.getLevel2OuId().trim().equalsIgnoreCase("select"))
        errors.add("level2OuId", new ActionMessage("errors.level2Ou.required"));
        return errors;
    }
}
