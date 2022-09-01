/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.organizationunit.controller;

import com.nomis.operationsManagement.OrganizationUnitHierarchyManager;
import com.nomis.ovc.metadata.OrganizationUnitHierarchy;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class OrganizationUnitHirachyForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String uid;
    private String ouname;
    private int oulevel;
    private String description;
    
    public OrganizationUnitHirachyForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOuname() {
        return ouname;
    }

    public void setOuname(String ouname) {
        this.ouname = ouname;
    }

    public int getOulevel() {
        return oulevel;
    }

    public void setOulevel(int oulevel) {
        this.oulevel = oulevel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    uid=null;
    ouname=null;
    oulevel=0;
    description=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(getActionName()==null || getActionName().equalsIgnoreCase("delete"))
        return errors;
        OrganizationUnitHierarchy ouh=OrganizationUnitHierarchyManager.validOuHierarchyName(this.getOuname());
        if (this.getOuname() == null || this.getOuname().length() < 1) 
        errors.add("ouname", new ActionMessage("error.ouhname.required"));
        else if (getActionName().equalsIgnoreCase("save") && ouh != null) 
        errors.add("ouname", new ActionMessage("error.ouhname.exist"));
        else if (getActionName().equalsIgnoreCase("update") && ouh != null && getUid() !=null && !ouh.getUid().equalsIgnoreCase(getUid())) 
        errors.add("ouname", new ActionMessage("error.ouhname.exist"));
        else if(getOulevel()==0)
        errors.add("oulevel", new ActionMessage("error.oulevel.required"));
            // TODO: add 'error.name.required' key to your resources
        return errors;
    }
}
