/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import com.nomis.reports.utils.ReportUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class ListOfIndicatorsForm extends org.apache.struts.action.ActionForm {
    
    
    private String actionName;
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    private String organizationUnitId;
    private String cboId;
    private int startMth;
    private int startYear;
    private int endMth;
    private int endYear;
    private String startAge;
    private String endAge;
    private String startDate;
    private String endDate;
    private String partnerCode;
    private String[] indicatorIndexes;
    
    public ListOfIndicatorsForm() {
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

    public String getEndAge() {
        return endAge;
    }

    public void setEndAge(String endAge) {
        this.endAge = endAge;
    }
    
    public int getEndMth() {
        return endMth;
    }

    public void setEndMth(int endMth) {
        this.endMth = endMth;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getStartAge() {
        return startAge;
    }

    public void setStartAge(String startAge) {
        this.startAge = startAge;
    }

    public int getStartMth() {
        return startMth;
    }

    public void setStartMth(int startMth) {
        this.startMth = startMth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public String[] getIndicatorIndexes() {
        return indicatorIndexes;
    }

    public void setIndicatorIndexes(String[] indicatorIndexes) {
        this.indicatorIndexes = indicatorIndexes;
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

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
@Override    
public void reset(ActionMapping mapping, HttpServletRequest request)
{
    actionName=null;
    indicatorIndexes=null;
    startAge="0";
    endAge="24+";
    startDate=ReportUtility.getDefaultStartDateForReports();
    endDate=ReportUtility.getDefaultEndDateForReports();
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
