/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class MetadataExportForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private int organizationUnit;
    private String[] cbos;
    private String[] schools;
    private String[] grades;
    private String[] vulnerabilityStatus;
    private String[] referralFacilities;
    private String[] implementingPartners;
    private String[] beneficiaryServices;
    private String[] communityWorkers;
    private String[] organizationUnits;
    
    public MetadataExportForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(int organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String[] getCbos() {
        return cbos;
    }

    public void setCbos(String[] cbos) {
        this.cbos = cbos;
    }

    public String[] getGrades() {
        return grades;
    }

    public void setGrades(String[] grades) {
        this.grades = grades;
    }

    public String[] getSchools() {
        return schools;
    }

    public void setSchools(String[] schools) {
        this.schools = schools;
    }

    public String[] getReferralFacilities() {
        return referralFacilities;
    }

    public void setReferralFacilities(String[] referralFacilities) {
        this.referralFacilities = referralFacilities;
    }

    public String[] getVulnerabilityStatus() {
        return vulnerabilityStatus;
    }

    public void setVulnerabilityStatus(String[] vulnerabilityStatus) {
        this.vulnerabilityStatus = vulnerabilityStatus;
    }

    public String[] getBeneficiaryServices() {
        return beneficiaryServices;
    }

    public void setBeneficiaryServices(String[] beneficiaryServices) {
        this.beneficiaryServices = beneficiaryServices;
    }

    public String[] getCommunityWorkers() {
        return communityWorkers;
    }

    public void setCommunityWorkers(String[] communityWorkers) {
        this.communityWorkers = communityWorkers;
    }

    public String[] getImplementingPartners() {
        return implementingPartners;
    }

    public void setImplementingPartners(String[] implementingPartners) {
        this.implementingPartners = implementingPartners;
    }

    public String[] getOrganizationUnits() {
        return organizationUnits;
    }

    public void setOrganizationUnits(String[] organizationUnits) {
        this.organizationUnits = organizationUnits;
    }
    
@Override
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    organizationUnit=0;
    cbos=null;
    schools=null;
    grades=null;
    vulnerabilityStatus=null;
    referralFacilities=null;
    implementingPartners=null;
    beneficiaryServices=null;
    communityWorkers=null;
    organizationUnits=null;
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
