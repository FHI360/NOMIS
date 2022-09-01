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
public class DatabaseDefragmentationForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String[] tableNames;
    private int sequence;

    public DatabaseDefragmentationForm() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }
    @Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    tableNames=null;
    sequence=0;
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
