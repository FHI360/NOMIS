/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import com.nomis.ovc.metadata.OrganizationUnit;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class ReportTemplate implements Serializable
{
    private String level2OuId;
    private String level3OuId;
    private String cboId;
    private String level4OuId;
    private String level2OuName;
    private String level3OuName;
    private String cboName;
    private String level4OuName;
    private String userName;
    private Indicator indicator;
    private String indicatorId;
    private String indicatorName;
    private int value;
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
    private int male25Plus;
    private int female25Plus;
    private int maleTotal;
    private int femaleTotal;
    private int grandTotal;
    private int childrenMale18Plus;
    private int childrenFemale18Plus;
    private int ovc_serv;
    
    private int male0to5;
    private int female0to5;
    private int male6to12;
    private int female6to12;
    private int male13to17;
    private int female13to17;
    private DataElementCategoryOptionCombo dataElementCategoryOptionCombo;
    
    
    private String ovc_servAgeCategory;
    private String percentage;
    private String ageDisaggregation;
    private String otherDisaggregation;
    private String sex;
    
    private String rowColor="#FFFFFF";
    int serialNo;
    private OrganizationUnit ou;

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
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

    public int getChildrenFemale18Plus() {
        return childrenFemale18Plus;
    }

    public void setChildrenFemale18Plus(int childrenFemale18Plus) {
        this.childrenFemale18Plus = childrenFemale18Plus;
    }

    public int getChildrenMale18Plus() {
        return childrenMale18Plus;
    }

    public void setChildrenMale18Plus(int childrenMale18Plus) {
        this.childrenMale18Plus = childrenMale18Plus;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRowColor() {
        return rowColor;
    }

    public void setRowColor(String rowColor) {
        this.rowColor = rowColor;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getCboName() {
        return cboName;
    }

    public void setCboName(String cboName) {
        this.cboName = cboName;
    }

    public String getLevel2OuName() {
        return level2OuName;
    }

    public void setLevel2OuName(String level2OuName) {
        this.level2OuName = level2OuName;
    }

    public String getLevel3OuName() {
        return level3OuName;
    }

    public void setLevel3OuName(String level3OuName) {
        this.level3OuName = level3OuName;
    }

    public String getLevel4OuName() {
        return level4OuName;
    }

    public void setLevel4OuName(String level4OuName) {
        this.level4OuName = level4OuName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getAgeDisaggregation() {
        return ageDisaggregation;
    }

    public void setAgeDisaggregation(String ageDisaggregation) {
        this.ageDisaggregation = ageDisaggregation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getFemale0to5() {
        return female0to5;
    }

    public void setFemale0to5(int female0to5) {
        this.female0to5 = female0to5;
    }

    public int getFemale13to17() {
        return female13to17;
    }

    public void setFemale13to17(int female13to17) {
        this.female13to17 = female13to17;
    }

    public int getFemale6to12() {
        return female6to12;
    }

    public void setFemale6to12(int female6to12) {
        this.female6to12 = female6to12;
    }

    public int getMale0to5() {
        return male0to5;
    }

    public void setMale0to5(int male0to5) {
        this.male0to5 = male0to5;
    }

    public int getMale13to17() {
        return male13to17;
    }

    public void setMale13to17(int male13to17) {
        this.male13to17 = male13to17;
    }

    public int getMale6to12() {
        return male6to12;
    }

    public void setMale6to12(int male6to12) {
        this.male6to12 = male6to12;
    }

    public DataElementCategoryOptionCombo getDataElementCategoryOptionCombo() {
        return dataElementCategoryOptionCombo;
    }

    public void setDataElementCategoryOptionCombo(DataElementCategoryOptionCombo dataElementCategoryOptionCombo) {
        this.dataElementCategoryOptionCombo = dataElementCategoryOptionCombo;
    }

    public OrganizationUnit getOu() {
        if(ou==null)
        ou=new OrganizationUnit();
        return ou;
    }

    public void setOu(OrganizationUnit ou) {
        this.ou = ou;
    }
        
}
