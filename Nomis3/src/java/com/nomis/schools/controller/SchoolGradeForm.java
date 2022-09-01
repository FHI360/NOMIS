/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.schools.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class SchoolGradeForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String gradeName;
    private int gradeLevel;
    private String id;
    
    public SchoolGradeForm() {
        super();
        // TODO Auto-generated constructor stub
    }
public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    gradeName=null;
    gradeLevel=0;
    id=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()==null || this.getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(getGradeName()==null || getGradeName().trim().length() <1)
        errors.add("gradeName", new ActionMessage("errors.gradeName.required"));
        else if(getGradeName().trim().length() >25)
        errors.add("gradeName", new ActionMessage("errors.gradeName.toolong"));
        else if(getGradeLevel()==0)
        errors.add("gradeLevel", new ActionMessage("errors.gradeLevel.required"));
        return errors;
    }
}
