/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.organizationunit.controller;

import com.nomis.ovc.util.AppUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class ReferralFacilityForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String facilityId;
    private String facilityName;
    private int typeOfFacility;
    private String address;
    private String contactPhoneNumber;
    private String contactEmail;
    private String nameOfContactPerson;
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    private double latitude;
    private double longitude;
    private String datimId;
    
    public ReferralFacilityForm() {
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNameOfContactPerson() {
        return nameOfContactPerson;
    }

    public void setNameOfContactPerson(String nameOfContactPerson) {
        this.nameOfContactPerson = nameOfContactPerson;
    }

    public int getTypeOfFacility() {
        return typeOfFacility;
    }

    public void setTypeOfFacility(int typeOfFacility) {
        this.typeOfFacility = typeOfFacility;
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
    facilityId=null;
    facilityName=null;
    typeOfFacility=0;
    address=null;
    contactPhoneNumber=null;
    contactEmail=null;
    nameOfContactPerson=null;
    level2OuId=null;
    level3OuId=null;
    level4OuId=null;
    latitude=0.0;
    longitude=0.0;
    datimId=null;
}
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
    {
        AppUtility appUtil=new AppUtility();
        String[] exceptions={"."};
        String[] nameExceptions={".",","};
        String strLatitude=strLatitude=new Double(getLatitude()).toString();
        String strLongitude=strLongitude=new Double(getLongitude()).toString();
        
        ActionErrors errors = new ActionErrors();
        if(getActionName()==null || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("ward") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        else if(this.getLevel4OuId()==null || !appUtil.isString(this.getLevel4OuId())  || getLevel4OuId().equalsIgnoreCase("select"))
        errors.add("level4OuId", new ActionMessage("refdir.level4Ou.required"));
        else if(this.getFacilityName()==null || !appUtil.isString(getFacilityName()))
        errors.add("facilityName", new ActionMessage("refdir.facilityName.required"));
        else if(appUtil.hasSpecialCharacters(getFacilityName(),nameExceptions))
        errors.add("facilityName", new ActionMessage("refdir.facilityName.invalid"));
        else if(getTypeOfFacility()==0)
        errors.add("typeOfFacility", new ActionMessage("refdir.typeOfFacility.required"));
        else if(getNameOfContactPerson() !=null && appUtil.hasSpecialCharacters(getNameOfContactPerson(),exceptions))
        errors.add("nameOfContactPerson", new ActionMessage("refdir.nameOfContactPerson.invalid"));
        else if(appUtil.hasSpecialCharacters(strLatitude,exceptions))
        errors.add("latitude", new ActionMessage("refdir.latitude.invalid"));
        else if(appUtil.hasSpecialCharacters(strLongitude,exceptions))
        errors.add("longitude", new ActionMessage("refdir.longitude.invalid"));
        return errors;
    }
}
