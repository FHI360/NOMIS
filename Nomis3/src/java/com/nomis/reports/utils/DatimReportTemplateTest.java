/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class DatimReportTemplateTest implements Serializable
{
    private int recordId;
    private String level2Ou;
    private String level3Ou;
    private String cbo;
    private String level4Ou;
    
    private String mainDataElementName;
    private String subDataElementName;
    private int hiv_statNumerator=0;
    private int totalPositive=0;
    private int enrolledOnART=0;
    private int notEnrolledOnART=0;
    private int totalNegative=0;
    private int totalUnknown=0;
    private int testNotIndicated=0;
    private int otherReasons=0;
    private int ovc_servNumerator=0;
    private int ovc_servActive=0;
    private int ovc_servGraduated=0;
    private int ovc_servTransfered=0;
    private int ovc_servExitedWithoutGraduation=0;
    private int ovc_servLessThan1=0;
    private int ovc_serv1To4=0;
    private int ovc_serv5To9=0;
    private int ovc_serv1To9=0;
    private int ovc_servMale10To14=0;
    private int ovc_servFemale10To14=0;
    private int ovc_servMale15To17=0;
    private int ovc_servFemale15To17=0;
    private int ovc_servMale18To24=0;
    private int ovc_servFemale18To24=0;
    private int ovc_servMale25AndAbove=0;
    private int ovc_servFemale25AndAbove=0;

    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }

    public int getEnrolledOnART() {
        return enrolledOnART;
    }

    public void setEnrolledOnART(int enrolledOnART) {
        this.enrolledOnART = enrolledOnART;
    }

    public int getHiv_statNumerator() {
        return hiv_statNumerator;
    }

    public void setHiv_statNumerator(int hiv_statNumerator) {
        this.hiv_statNumerator = hiv_statNumerator;
    }

    public String getLevel2Ou() {
        return level2Ou;
    }

    public void setLevel2Ou(String level2Ou) {
        this.level2Ou = level2Ou;
    }

    public String getLevel3Ou() {
        return level3Ou;
    }

    public void setLevel3Ou(String level3Ou) {
        this.level3Ou = level3Ou;
    }

    public String getLevel4Ou() {
        return level4Ou;
    }

    public void setLevel4Ou(String level4Ou) {
        this.level4Ou = level4Ou;
    }

    public String getMainDataElementName() {
        return mainDataElementName;
    }

    public void setMainDataElementName(String mainDataElementName) {
        this.mainDataElementName = mainDataElementName;
    }

    public int getNotEnrolledOnART() {
        return notEnrolledOnART;
    }

    public void setNotEnrolledOnART(int notEnrolledOnART) {
        this.notEnrolledOnART = notEnrolledOnART;
    }

    public int getOtherReasons() {
        return otherReasons;
    }

    public void setOtherReasons(int otherReasons) {
        this.otherReasons = otherReasons;
    }

    public int getOvc_serv1To4() {
        return ovc_serv1To4;
    }

    public void setOvc_serv1To4(int ovc_serv1To4) {
        this.ovc_serv1To4 = ovc_serv1To4;
    }

    public int getOvc_serv1To9() {
        return ovc_serv1To9;
    }

    public void setOvc_serv1To9(int ovc_serv1To9) {
        this.ovc_serv1To9 = ovc_serv1To9;
    }

    public int getOvc_serv5To9() {
        return ovc_serv5To9;
    }

    public void setOvc_serv5To9(int ovc_serv5To9) {
        this.ovc_serv5To9 = ovc_serv5To9;
    }

    public int getOvc_servActive() {
        return ovc_servActive;
    }

    public void setOvc_servActive(int ovc_servActive) {
        this.ovc_servActive = ovc_servActive;
    }

    public int getOvc_servExitedWithoutGraduation() {
        return ovc_servExitedWithoutGraduation;
    }

    public void setOvc_servExitedWithoutGraduation(int ovc_servExitedWithoutGraduation) {
        this.ovc_servExitedWithoutGraduation = ovc_servExitedWithoutGraduation;
    }

    public int getOvc_servFemale10To14() {
        return ovc_servFemale10To14;
    }

    public void setOvc_servFemale10To14(int ovc_servFemale10To14) {
        this.ovc_servFemale10To14 = ovc_servFemale10To14;
    }

    public int getOvc_servFemale15To17() {
        return ovc_servFemale15To17;
    }

    public void setOvc_servFemale15To17(int ovc_servFemale15To17) {
        this.ovc_servFemale15To17 = ovc_servFemale15To17;
    }

    public int getOvc_servFemale18To24() {
        return ovc_servFemale18To24;
    }

    public void setOvc_servFemale18To24(int ovc_servFemale18To24) {
        this.ovc_servFemale18To24 = ovc_servFemale18To24;
    }

    public int getOvc_servFemale25AndAbove() {
        return ovc_servFemale25AndAbove;
    }

    public void setOvc_servFemale25AndAbove(int ovc_servFemale25AndAbove) {
        this.ovc_servFemale25AndAbove = ovc_servFemale25AndAbove;
    }

    public int getOvc_servGraduated() {
        return ovc_servGraduated;
    }

    public void setOvc_servGraduated(int ovc_servGraduated) {
        this.ovc_servGraduated = ovc_servGraduated;
    }

    public int getOvc_servLessThan1() {
        return ovc_servLessThan1;
    }

    public void setOvc_servLessThan1(int ovc_servLessThan1) {
        this.ovc_servLessThan1 = ovc_servLessThan1;
    }

    public int getOvc_servMale10To14() {
        return ovc_servMale10To14;
    }

    public void setOvc_servMale10To14(int ovc_servMale10To14) {
        this.ovc_servMale10To14 = ovc_servMale10To14;
    }

    public int getOvc_servMale15To17() {
        return ovc_servMale15To17;
    }

    public void setOvc_servMale15To17(int ovc_servMale15To17) {
        this.ovc_servMale15To17 = ovc_servMale15To17;
    }

    public int getOvc_servMale18To24() {
        return ovc_servMale18To24;
    }

    public void setOvc_servMale18To24(int ovc_servMale18To24) {
        this.ovc_servMale18To24 = ovc_servMale18To24;
    }

    public int getOvc_servMale25AndAbove() {
        return ovc_servMale25AndAbove;
    }

    public void setOvc_servMale25AndAbove(int ovc_servMale25AndAbove) {
        this.ovc_servMale25AndAbove = ovc_servMale25AndAbove;
    }

    public int getOvc_servNumerator() {
        return ovc_servNumerator;
    }

    public void setOvc_servNumerator(int ovc_servNumerator) {
        this.ovc_servNumerator = ovc_servNumerator;
    }

    public int getOvc_servTransfered() {
        return ovc_servTransfered;
    }

    public void setOvc_servTransfered(int ovc_servTransfered) {
        this.ovc_servTransfered = ovc_servTransfered;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getSubDataElementName() {
        return subDataElementName;
    }

    public void setSubDataElementName(String subDataElementName) {
        this.subDataElementName = subDataElementName;
    }

    public int getTestNotIndicated() {
        return testNotIndicated;
    }

    public void setTestNotIndicated(int testNotIndicated) {
        this.testNotIndicated = testNotIndicated;
    }

    public int getTotalNegative() {
        return totalNegative;
    }

    public void setTotalNegative(int totalNegative) {
        this.totalNegative = totalNegative;
    }

    public int getTotalPositive() {
        return totalPositive;
    }

    public void setTotalPositive(int totalPositive) {
        this.totalPositive = totalPositive;
    }

    public int getTotalUnknown() {
        return totalUnknown;
    }

    public void setTotalUnknown(int totalUnknown) {
        this.totalUnknown = totalUnknown;
    }
    
}
