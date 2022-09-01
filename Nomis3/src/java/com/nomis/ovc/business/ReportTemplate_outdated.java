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
public class ReportTemplate_outdated implements Serializable
{
    private int recordId;
    private Date dateCreated;
    private String state;
    private String lga;
    private String cbo;
    private String ward;
    private String user;
    private Indicator indicator;
    private String indicatorId;
    private String indicatorName;
    private String merCode;
    private String startMth;
    private String startYr;
    private String endMth;
    private String endYr;
    private String period;
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
    private int male18to24;
    private int female18to24;
    private int male25AndAbove;
    private int female25AndAbove;
    private int maleTotal;
    private int femaleTotal;
    private int grandTotal;
    private int ovc_serv;
    private String ovc_servAgeCategory;
    private String percentage;
    private String otherDisaggregation;
    

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
        
    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
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
    
    public int getFemale1to4() {
        return female1to4;
    }

    public void setFemale1to4(int female1to4) {
        this.female1to4 = female1to4;
    }

    public int getFemale18to24() {
        return female18to24;
    }

    public void setFemale18to24(int female18to24) {
        this.female18to24 = female18to24;
    }

    public int getFemale25AndAbove() {
        return female25AndAbove;
    }

    public void setFemale25AndAbove(int female25AndAbove) {
        this.female25AndAbove = female25AndAbove;
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

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
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
    
    public int getMale1to4() {
        return male1to4;
    }

    public void setMale1to4(int male1to4) {
        this.male1to4 = male1to4;
    }

    public int getMale18to24() {
        return male18to24;
    }

    public void setMale18to24(int male18to24) {
        this.male18to24 = male18to24;
    }
    
    public int getMale25AndAbove() {
        return male25AndAbove;
    }

    public void setMale25AndAbove(int male25AndAbove) {
        this.male25AndAbove = male25AndAbove;
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

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
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

    public int getMaleTotal() {
        return maleTotal;
    }

    public void setMaleTotal(int maleTotal) {
        this.maleTotal = maleTotal;
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

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOtherDisaggregation() {
        return otherDisaggregation;
    }

    public void setOtherDisaggregation(String otherDisaggregation) {
        this.otherDisaggregation = otherDisaggregation;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }
    
}
