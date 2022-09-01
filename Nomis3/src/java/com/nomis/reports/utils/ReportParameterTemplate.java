/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author smomoh
 */
public class ReportParameterTemplate 
{
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    private String cboId;
    private String partnerCode;
    private String startDate;
    private String endDate;
    private int startMth;
    private int startYear;
    private int endMth;
    private int endYear;
    private int startAge;
    private int endAge;
    private int birthCertificateValue;
    private Indicator indicator;
    private String sex;
    //private String shortSexName;
    private String longSexName;
    private int enrollmentStatus;
    private int schoolStatus;
    private ReportPeriod financialYear;
    private int reportType;
    private boolean childrenAgeDisaggregated=true;
    private boolean adultAgeDisaggregated=true;
    private int enrollmentSetting;
    private String startOfFinancialYear;
    private List level2OuList=new ArrayList();
    private List level3OuList=new ArrayList();
    private List level4OuList=new ArrayList();
    private List cboList=new ArrayList();
    
    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public int getEndAge() {
        return endAge;
    }

    public void setEndAge(int endAge) {
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

    public int getStartAge() {
        return startAge;
    }

    public void setStartAge(int startAge) {
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getStartDate() 
    {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getBirthCertificateValue() {
        return birthCertificateValue;
    }

    public void setBirthCertificateValue(int birthCertificateValue) {
        this.birthCertificateValue = birthCertificateValue;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }
    
    public int getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(int enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public int getSchoolStatus() {
        return schoolStatus;
    }

    public void setSchoolStatus(int schoolStatus) {
        this.schoolStatus = schoolStatus;
    }

    public ReportPeriod getFinancialYear() 
    {
        if(financialYear==null)
        financialYear=ReportPeriodManager.getStartOfFinancialYear(getStartMth(), getStartYear());
        return financialYear;
    }

    public void setFinancialYear(ReportPeriod financialYear) {
        this.financialYear = financialYear;
    }

    public String getLongSexName() {
        return longSexName;
    }

    public void setLongSexName(String longSexName) {
        this.longSexName = longSexName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public boolean isAdultAgeDisaggregated() {
        return adultAgeDisaggregated;
    }

    public void setAdultAgeDisaggregated(boolean adultAgeDisaggregated) {
        this.adultAgeDisaggregated = adultAgeDisaggregated;
    }

    public boolean isChildrenAgeDisaggregated() {
        return childrenAgeDisaggregated;
    }

    public void setChildrenAgeDisaggregated(boolean childrenAgeDisaggregated) {
        this.childrenAgeDisaggregated = childrenAgeDisaggregated;
    }

    public int getEnrollmentSetting() {
        return enrollmentSetting;
    }

    public void setEnrollmentSetting(int enrollmentSetting) {
        this.enrollmentSetting = enrollmentSetting;
    }

    public String getStartOfFinancialYear() 
    {
        startOfFinancialYear=getFinancialYear().getStartYear()+"-"+getFinancialYear().getStartMonth()+"-01";
        return startOfFinancialYear;
    }

    public List getCboList() {
        return cboList;
    }

    public void setCboList(List cboList) {
        this.cboList = cboList;
    }

    public List getLevel2OuList() {
        return level2OuList;
    }

    public void setLevel2OuList(List level2OuList) {
        this.level2OuList = level2OuList;
    }

    public List getLevel3OuList() {
        return level3OuList;
    }

    public void setLevel3OuList(List level3OuList) {
        this.level3OuList = level3OuList;
    }

    public List getLevel4OuList() {
        return level4OuList;
    }

    public void setLevel4OuList(List level4OuList) {
        this.level4OuList = level4OuList;
    }
    
}
