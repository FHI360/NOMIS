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
public class BulkDeleteOperationForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;
    private String sortOrder;
    private String beneficiaryType;
    private String[] householdsToDelete;
    
    public BulkDeleteOperationForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String[] getHouseholdsToDelete() {
        return householdsToDelete;
    }

    public void setHouseholdsToDelete(String[] householdsToDelete) {
        this.householdsToDelete = householdsToDelete;
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

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
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
