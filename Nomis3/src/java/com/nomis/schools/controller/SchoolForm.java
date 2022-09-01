/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.schools.controller;

import com.nomis.ovc.util.AppUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class SchoolForm extends org.apache.struts.action.ActionForm {
    
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    //private String organizationUnitId;
    private String actionName;
    private String schoolName;
    private int schoolType;
    private String id;
    
    public SchoolForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(int schoolType) {
        this.schoolType = schoolType;
    }
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    schoolName=null;
    schoolType=0;
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
        AppUtility appUtil=new AppUtility();
        
        if(this.getActionName()==null || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("ward") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(this.getLevel3OuId()==null || getLevel3OuId().trim().equalsIgnoreCase("select"))
        errors.add("level3OuId", new ActionMessage("errors.district.required"));
        else if(this.getLevel4OuId()==null || !appUtil.isString(this.getLevel4OuId())  || getLevel4OuId().equalsIgnoreCase("select"))
        errors.add("level4OuId", new ActionMessage("errors.organizationUnitId.required"));
        else if(getSchoolName()==null || getSchoolName().trim().length() <1)
        errors.add("schoolName", new ActionMessage("errors.schoolName.required"));
        else if(getSchoolName().trim().length() >100)
        errors.add("schoolName", new ActionMessage("errors.schoolName.toolong"));
        else if(this.getSchoolType()==0)
        errors.add("schoolType", new ActionMessage("errors.schoolType.required"));
        return errors;
    }
}
