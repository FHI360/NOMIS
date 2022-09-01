/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.reports.utils.Indicator;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class CustomIndicatorsReport implements Serializable
{
    private int recordId;
    private Date dateCreated;
    private String level2OuId;
    private String level3OuId;
    private String cboId;
    private String level4OuId;
    private String userName;
    private Indicator indicator;
    private String indicatorId;
    private String indicatorName;
    private String merCode;
    private String startMth;
    private String startYr;
    private String endMth;
    private String endYr;
    private String reportPeriod;
    private String partnerCode;
    private String partnerName;
    private int maleLessThan1;
    private int femaleLessThan1;
    private int male1to4;
    private int female1to4;
    private int male5to9;
    private int female5to9;
    private int male10to14;
    private int female10to14;
    private int male15to17;
    private int female15to17;
    private int male18to20;
    private int female18to20;
    private int male18to24;
    private int female18to24;
    private int male25Plus;
    private int female25Plus;
    private int maleTotal;
    private int femaleTotal;
    private int grandTotal;
    private int ovc_serv;
    private String ovc_servAgeCategory;
    private String percentage;
    private String otherDisaggregation;
    
    private int male18PlusChildren;
    private int female18PlusChildren;

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEndMth() {
        return endMth;
    }

    public void setEndMth(String endMth) {
        this.endMth = endMth;
    }

    public String getEndYr() {
        return endYr;
    }

    public void setEndYr(String endYr) {
        this.endYr = endYr;
    }

    public int getFemale10to14() {
        return female10to14;
    }

    public void setFemale10to14(int female10to14) {
        this.female10to14 = female10to14;
    }

    public int getFemale15to17() {
        return female15to17;
    }

    public void setFemale15to17(int female15to17) {
        this.female15to17 = female15to17;
    }

    public int getFemale18to24() {
        return female18to24;
    }

    public void setFemale18to24(int female18to24) {
        this.female18to24 = female18to24;
    }

    public int getFemale1to4() {
        return female1to4;
    }

    public void setFemale1to4(int female1to4) {
        this.female1to4 = female1to4;
    }

    public int getFemale25Plus() {
        return female25Plus;
    }

    public void setFemale25Plus(int female25Plus) {
        this.female25Plus = female25Plus;
    }

    public int getFemale5to9() {
        return female5to9;
    }

    public void setFemale5to9(int female5to9) {
        this.female5to9 = female5to9;
    }

    public int getFemaleLessThan1() {
        return femaleLessThan1;
    }

    public void setFemaleLessThan1(int femaleLessThan1) {
        this.femaleLessThan1 = femaleLessThan1;
    }

    public int getFemaleTotal() {
        return femaleTotal;
    }

    public void setFemaleTotal(int femaleTotal) {
        this.femaleTotal = femaleTotal;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
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

    public int getMale10to14() {
        return male10to14;
    }

    public void setMale10to14(int male10to14) {
        this.male10to14 = male10to14;
    }

    public int getMale15to17() {
        return male15to17;
    }

    public void setMale15to17(int male15to17) {
        this.male15to17 = male15to17;
    }

    public int getMale18to24() {
        return male18to24;
    }

    public void setMale18to24(int male18to24) {
        this.male18to24 = male18to24;
    }

    public int getMale1to4() {
        return male1to4;
    }

    public void setMale1to4(int male1to4) {
        this.male1to4 = male1to4;
    }

    public int getMale25Plus() {
        return male25Plus;
    }

    public void setMale25Plus(int male25Plus) {
        this.male25Plus = male25Plus;
    }

    public int getMale5to9() {
        return male5to9;
    }

    public void setMale5to9(int male5to9) {
        this.male5to9 = male5to9;
    }

    public int getMaleLessThan1() {
        return maleLessThan1;
    }

    public void setMaleLessThan1(int maleLessThan1) {
        this.maleLessThan1 = maleLessThan1;
    }

    public int getMaleTotal() {
        return maleTotal;
    }

    public void setMaleTotal(int maleTotal) {
        this.maleTotal = maleTotal;
    }

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getOtherDisaggregation() {
        return otherDisaggregation;
    }

    public void setOtherDisaggregation(String otherDisaggregation) {
        this.otherDisaggregation = otherDisaggregation;
    }

    public int getOvc_serv() {
        return ovc_serv;
    }

    public void setOvc_serv(int ovc_serv) {
        this.ovc_serv = ovc_serv;
    }

    public String getOvc_servAgeCategory() {
        return ovc_servAgeCategory;
    }

    public void setOvc_servAgeCategory(String ovc_servAgeCategory) {
        this.ovc_servAgeCategory = ovc_servAgeCategory;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getStartMth() {
        return startMth;
    }

    public void setStartMth(String startMth) {
        this.startMth = startMth;
    }

    public String getStartYr() {
        return startYr;
    }

    public void setStartYr(String startYr) {
        this.startYr = startYr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFemale18PlusChildren() {
        return female18PlusChildren;
    }

    public void setFemale18PlusChildren(int female18PlusChildren) {
        this.female18PlusChildren = female18PlusChildren;
    }

    public int getMale18PlusChildren() {
        return male18PlusChildren;
    }

    public void setMale18PlusChildren(int male18PlusChildren) {
        this.male18PlusChildren = male18PlusChildren;
    }
    
}
