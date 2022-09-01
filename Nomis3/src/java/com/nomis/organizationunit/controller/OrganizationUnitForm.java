/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.organizationunit.controller;

import com.nomis.validation.OrganizationUnitValidator;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class OrganizationUnitForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String uid;
    private String name;
    private String description;
    private String parentId;
    private int oulevel;
    private String oucode;
    private String rootOrganizationUnit;
    private int filterByParent;
    private String datimId;
    
    public OrganizationUnitForm() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
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
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOucode() {
        return oucode;
    }

    public void setOucode(String oucode) {
        this.oucode = oucode;
    }

    public int getOulevel() {
        return oulevel;
    }

    public void setOulevel(int oulevel) {
        this.oulevel = oulevel;
    }

    public String getRootOrganizationUnit() {
        return rootOrganizationUnit;
    }

    public void setRootOrganizationUnit(String rootOrganizationUnit) {
        this.rootOrganizationUnit = rootOrganizationUnit;
    }

    public int getFilterByParent() {
        return filterByParent;
    }

    public void setFilterByParent(int filterByParent) {
        this.filterByParent = filterByParent;
    }

    public String getDatimId() {
        return datimId;
    }

    public void setDatimId(String datimId) {
        this.datimId = datimId;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    name=null;
    description=null;
    uid=null;
    parentId=null;
    oucode=null;
    filterByParent=0;
    datimId=null;
}
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        OrganizationUnitValidator ouValidator=new OrganizationUnitValidator();
        System.err.println("getActionName() is "+getActionName());
        if(getActionName()==null || getActionName().equalsIgnoreCase("parentList") || getActionName().equalsIgnoreCase("filterByParent") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
        }
        else if (getOucode() == null || getOucode().trim().length() != 3) {
            errors.add("oucode", new ActionMessage("error.oucode.required"));
        }
        else if(ouValidator.validateOuCode(this.getOucode(), getOulevel(), this.getName()) ==2)
        errors.add("oucode", new ActionMessage("error.oucode.unavailable"));
            // TODO: add 'error.name.required' key to your resources
        
        return errors;
    }
}
