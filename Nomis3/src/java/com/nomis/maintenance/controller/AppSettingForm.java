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
public class AppSettingForm extends org.apache.struts.action.ActionForm {
    
    private String actionName;
    private String optionId;
    private String name;
    private String value;
    private String hhvaversion;
    private String hracversion;
    private String xmlDbImport;
    private String displayClientRecordInHtml;
    private String downloadClientRecordInExcel;
    private String excelDownload;
    
    public AppSettingForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDisplayClientRecordInHtml() {
        return displayClientRecordInHtml;
    }

    public void setDisplayClientRecordInHtml(String displayClientRecordInHtml) {
        this.displayClientRecordInHtml = displayClientRecordInHtml;
    }

    public String getDownloadClientRecordInExcel() {
        return downloadClientRecordInExcel;
    }

    public void setDownloadClientRecordInExcel(String downloadClientRecordInExcel) {
        this.downloadClientRecordInExcel = downloadClientRecordInExcel;
    }

    public String getExcelDownload() {
        return excelDownload;
    }

    public void setExcelDownload(String excelDownload) {
        this.excelDownload = excelDownload;
    }

    public String getHhvaversion() {
        return hhvaversion;
    }

    public void setHhvaversion(String hhvaversion) {
        this.hhvaversion = hhvaversion;
    }

    public String getHracversion() {
        return hracversion;
    }

    public void setHracversion(String hracversion) {
        this.hracversion = hracversion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getXmlDbImport() {
        return xmlDbImport;
    }

    public void setXmlDbImport(String xmlDbImport) {
        this.xmlDbImport = xmlDbImport;
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
