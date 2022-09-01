/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.dataadministration.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class HouseholdMergeOperationForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    private String organizationUnitId;
    private String cboId;
    private String keep;
    private String[] merge;
    public HouseholdMergeOperationForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
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

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }

    public String getKeep() {
        return keep;
    }

    public void setKeep(String keep) {
        this.keep = keep;
    }

    public String[] getMerge() {
        return merge;
    }

    public void setMerge(String[] merge) {
        this.merge = merge;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    level2OuId=null;
    level3OuId=null;
    level4OuId=null;
    organizationUnitId=null;
    cboId=null;
    keep=null;
    merge=null;
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
