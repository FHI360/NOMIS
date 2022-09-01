/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.cbo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class CommunityBasedOrganizationForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String hiddenUniqueId;
    private String cboCode;
    private String cboName;
    private String address;
    private String contactPersonName;
    private String contactPersonPhone;
    private String contactPersonEmail;
    private double latitude;
    private double longitude;
    private String dataExchangeId;
    private String[] level3OuId;
    private String[] services;
    private String cboToKeep;
    private String[] selectedCbosToDelete;
    private String uniqueId;
    
    public CommunityBasedOrganizationForm() {
        super();
        // TODO Auto-generated constructor stub
    }
public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCboCode() {
        return cboCode;
    }

    public void setCboCode(String cboCode) {
        this.cboCode = cboCode;
    }

    public String getCboName() {
        return cboName;
    }

    public void setCboName(String cboName) {
        this.cboName = cboName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getDataExchangeId() {
        return dataExchangeId;
    }

    public void setDataExchangeId(String dataExchangeId) {
        this.dataExchangeId = dataExchangeId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String[] getLevel3OuId() {
        return level3OuId;
    }

    public void setLevel3OuId(String[] level3OuId) {
        this.level3OuId = level3OuId;
    }
    
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    public String getCboToKeep() {
        return cboToKeep;
    }

    public void setCboToKeep(String cboToKeep) {
        this.cboToKeep = cboToKeep;
    }

    public String[] getSelectedCbosToDelete() {
        return selectedCbosToDelete;
    }

    public void setSelectedCbosToDelete(String[] selectedCbosToDelete) {
        this.selectedCbosToDelete = selectedCbosToDelete;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getHiddenUniqueId() {
        return hiddenUniqueId;
    }

    public void setHiddenUniqueId(String hiddenUniqueId) {
        this.hiddenUniqueId = hiddenUniqueId;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    hiddenUniqueId=null;
    cboCode=null;
    cboName=null;
    address=null;
    contactPersonName=null;
    contactPersonPhone=null;
    contactPersonEmail=null;
    latitude=0.0;
    longitude=0.0;
    dataExchangeId=null;
    level3OuId=null;
    services=null;
    cboToKeep=null;
    selectedCbosToDelete=null;
    uniqueId=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.getActionName()==null || this.getActionName().equalsIgnoreCase("delete") || this.getActionName().equalsIgnoreCase("mergeCBORecords"))
            return errors;
        else if(this.getCboName()==null || this.getCboName().trim().length()<1)
        errors.add("cboName", new ActionMessage("error.cboName.required"));
        else if(this.getLevel3OuId() ==null || getLevel3OuId().length==0)
        errors.add("level3OuId", new ActionMessage("error.level3OuId.required"));
        return errors;
    }
}
