/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.communityworker.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class CommunityWorkerForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String communityWorkerId;
    private String level3OuId;
    private String level4OuId;
    private String firstName;
    private String surname;
    private String sex;
    private String designation;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    private int enrollmentStatus;
    /**
     *
     */
    public CommunityWorkerForm() {
        super();
        // TODO Auto-generated constructor stub
    }
public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getCommunityWorkerId() {
        return communityWorkerId;
    }

    public void setCommunityWorkerId(String communityWorkerId) {
        this.communityWorkerId = communityWorkerId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public int getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(int enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    communityWorkerId=null;
    firstName=null;
    surname=null;
    sex=null;
    designation=null;
    enrollmentStatus=1;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()==null)
        return errors;
        else if(this.getFirstName()==null || this.getFirstName().trim().length()==0)
        errors.add("firstName", new ActionMessage("errors.firstName.required"));
        else if(this.getSurname()==null || this.getSurname().trim().length()==0)
        errors.add("surname", new ActionMessage("errors.surname.required"));
        else if(this.getSex()==null || getSex().trim().length()==0)
        errors.add("sex", new ActionMessage("errors.sex.required"));
        else if(this.getDesignation()==null || getDesignation().trim().length()==0)
        errors.add("designation", new ActionMessage("errors.designation.required"));
        return errors;
    }
}
