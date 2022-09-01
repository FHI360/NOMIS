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
public class DatimReportForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    private String organizationUnitId;
    private String cboId;
    private String periodChkBox;
    private String startDate;
    private String endDate;
    private String reportType;
    private String reportPeriod;
    
    public DatimReportForm() {
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

    public String getPeriodChkBox() {
        return periodChkBox;
    }

    public void setPeriodChkBox(String periodChkBox) {
        this.periodChkBox = periodChkBox;
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

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    @Override
public void reset(ActionMapping mapping,HttpServletRequest request)
{
    actionName=null;
    periodChkBox=null;
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
