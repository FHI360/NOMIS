/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

/**
 *
 * @author smomoh
 */
public class Datim2017Form 
{
    private String state;
    private String lga;
    private String cbo;
    private String ward;
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
    
    private int ovc_servActiveLessThan1Male;
    private int ovc_servActiveLessThan1Female;
    private int ovc_servActive1to4Male;
    private int ovc_servActive1to4Female;
    private int ovc_servActive5to9Male;
    private int ovc_servActive5to9Female;
    private int ovc_servActive10to14Male;
    private int ovc_servActive10to14Female;
    private int ovc_servActive15to17Male;
    private int ovc_servActive15to17Female;
    private int ovc_servActive18to24Male;
    private int ovc_servActive18to24Female;
    private int ovc_servActive18AndAboveMale;
    private int ovc_servActive18AndAboveFemale;
    private int ovc_servActive25AndAboveMale;
    private int ovc_servActive25AndAboveFemale;
    
    private int ovc_servGraduatedLessThan1Male;
    private int ovc_servGraduatedLessThan1Female;
    private int ovc_servGraduated1to4Male;
    private int ovc_servGraduated1to4Female;
    private int ovc_servGraduated5to9Male;
    private int ovc_servGraduated5to9Female;
    private int ovc_servGraduated10to14Male;
    private int ovc_servGraduated10to14Female;
    private int ovc_servGraduated15to17Male;
    private int ovc_servGraduated15to17Female;
    private int ovc_servGraduated18to24Male;
    private int ovc_servGraduated18to24Female;
    private int ovc_servGraduated18AndAboveMale;
    private int ovc_servGraduated18AndAboveFemale;
    private int ovc_servGraduated25AndAboveMale;
    private int ovc_servGraduated25AndAboveFemale;
    
    private int transferedToPEPFAR;
    private int transferedToNonPEPFAR;
    
    private String startMth;
    private String startYr;
    private String endMth;
    private String endYr;
    private String period;
    private String partnerName;

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

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
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

    public int getOvc_serv1To9() {
        return ovc_serv1To9;
    }

    public void setOvc_serv1To9(int ovc_serv1To9) {
        this.ovc_serv1To9 = ovc_serv1To9;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public int getOvc_serv1To4() {
        return ovc_serv1To4;
    }

    public void setOvc_serv1To4(int ovc_serv1To4) {
        this.ovc_serv1To4 = ovc_serv1To4;
    }

    public int getOvc_serv5To9() {
        return ovc_serv5To9;
    }

    public void setOvc_serv5To9(int ovc_serv5To9) {
        this.ovc_serv5To9 = ovc_serv5To9;
    }

    public int getOvc_servActive10to14Female() {
        return ovc_servActive10to14Female;
    }

    public void setOvc_servActive10to14Female(int ovc_servActive10to14Female) {
        this.ovc_servActive10to14Female = ovc_servActive10to14Female;
    }

    public int getOvc_servActive10to14Male() {
        return ovc_servActive10to14Male;
    }

    public void setOvc_servActive10to14Male(int ovc_servActive10to14Male) {
        this.ovc_servActive10to14Male = ovc_servActive10to14Male;
    }

    public int getOvc_servActive15to17Female() {
        return ovc_servActive15to17Female;
    }

    public void setOvc_servActive15to17Female(int ovc_servActive15to17Female) {
        this.ovc_servActive15to17Female = ovc_servActive15to17Female;
    }

    public int getOvc_servActive15to17Male() {
        return ovc_servActive15to17Male;
    }

    public void setOvc_servActive15to17Male(int ovc_servActive15to17Male) {
        this.ovc_servActive15to17Male = ovc_servActive15to17Male;
    }

    public int getOvc_servActive18AndAboveFemale() {
        return ovc_servActive18AndAboveFemale;
    }

    public void setOvc_servActive18AndAboveFemale(int ovc_servActive18AndAboveFemale) {
        this.ovc_servActive18AndAboveFemale = ovc_servActive18AndAboveFemale;
    }

    public int getOvc_servActive18AndAboveMale() {
        return ovc_servActive18AndAboveMale;
    }

    public void setOvc_servActive18AndAboveMale(int ovc_servActive18AndAboveMale) {
        this.ovc_servActive18AndAboveMale = ovc_servActive18AndAboveMale;
    }

    public int getOvc_servActive18to24Female() {
        return ovc_servActive18to24Female;
    }

    public void setOvc_servActive18to24Female(int ovc_servActive18to24Female) {
        this.ovc_servActive18to24Female = ovc_servActive18to24Female;
    }

    public int getOvc_servActive18to24Male() {
        return ovc_servActive18to24Male;
    }

    public void setOvc_servActive18to24Male(int ovc_servActive18to24Male) {
        this.ovc_servActive18to24Male = ovc_servActive18to24Male;
    }

    public int getOvc_servActive1to4Female() {
        return ovc_servActive1to4Female;
    }

    public void setOvc_servActive1to4Female(int ovc_servActive1to4Female) {
        this.ovc_servActive1to4Female = ovc_servActive1to4Female;
    }

    public int getOvc_servActive1to4Male() {
        return ovc_servActive1to4Male;
    }

    public void setOvc_servActive1to4Male(int ovc_servActive1to4Male) {
        this.ovc_servActive1to4Male = ovc_servActive1to4Male;
    }

    public int getOvc_servActive25AndAboveFemale() {
        return ovc_servActive25AndAboveFemale;
    }

    public void setOvc_servActive25AndAboveFemale(int ovc_servActive25AndAboveFemale) {
        this.ovc_servActive25AndAboveFemale = ovc_servActive25AndAboveFemale;
    }

    public int getOvc_servActive25AndAboveMale() {
        return ovc_servActive25AndAboveMale;
    }

    public void setOvc_servActive25AndAboveMale(int ovc_servActive25AndAboveMale) {
        this.ovc_servActive25AndAboveMale = ovc_servActive25AndAboveMale;
    }

    public int getOvc_servActive5to9Female() {
        return ovc_servActive5to9Female;
    }

    public void setOvc_servActive5to9Female(int ovc_servActive5to9Female) {
        this.ovc_servActive5to9Female = ovc_servActive5to9Female;
    }

    public int getOvc_servActive5to9Male() {
        return ovc_servActive5to9Male;
    }

    public void setOvc_servActive5to9Male(int ovc_servActive5to9Male) {
        this.ovc_servActive5to9Male = ovc_servActive5to9Male;
    }

    public int getOvc_servActiveLessThan1Female() {
        return ovc_servActiveLessThan1Female;
    }

    public void setOvc_servActiveLessThan1Female(int ovc_servActiveLessThan1Female) {
        this.ovc_servActiveLessThan1Female = ovc_servActiveLessThan1Female;
    }

    public int getOvc_servActiveLessThan1Male() {
        return ovc_servActiveLessThan1Male;
    }

    public void setOvc_servActiveLessThan1Male(int ovc_servActiveLessThan1Male) {
        this.ovc_servActiveLessThan1Male = ovc_servActiveLessThan1Male;
    }

    public int getOvc_servGraduated10to14Female() {
        return ovc_servGraduated10to14Female;
    }

    public void setOvc_servGraduated10to14Female(int ovc_servGraduated10to14Female) {
        this.ovc_servGraduated10to14Female = ovc_servGraduated10to14Female;
    }

    public int getOvc_servGraduated10to14Male() {
        return ovc_servGraduated10to14Male;
    }

    public void setOvc_servGraduated10to14Male(int ovc_servGraduated10to14Male) {
        this.ovc_servGraduated10to14Male = ovc_servGraduated10to14Male;
    }

    public int getOvc_servGraduated15to17Female() {
        return ovc_servGraduated15to17Female;
    }

    public void setOvc_servGraduated15to17Female(int ovc_servGraduated15to17Female) {
        this.ovc_servGraduated15to17Female = ovc_servGraduated15to17Female;
    }

    public int getOvc_servGraduated15to17Male() {
        return ovc_servGraduated15to17Male;
    }

    public void setOvc_servGraduated15to17Male(int ovc_servGraduated15to17Male) {
        this.ovc_servGraduated15to17Male = ovc_servGraduated15to17Male;
    }

    public int getOvc_servGraduated18AndAboveFemale() {
        return ovc_servGraduated18AndAboveFemale;
    }

    public void setOvc_servGraduated18AndAboveFemale(int ovc_servGraduated18AndAboveFemale) {
        this.ovc_servGraduated18AndAboveFemale = ovc_servGraduated18AndAboveFemale;
    }

    public int getOvc_servGraduated18AndAboveMale() {
        return ovc_servGraduated18AndAboveMale;
    }

    public void setOvc_servGraduated18AndAboveMale(int ovc_servGraduated18AndAboveMale) {
        this.ovc_servGraduated18AndAboveMale = ovc_servGraduated18AndAboveMale;
    }

    public int getOvc_servGraduated18to24Female() {
        return ovc_servGraduated18to24Female;
    }

    public void setOvc_servGraduated18to24Female(int ovc_servGraduated18to24Female) {
        this.ovc_servGraduated18to24Female = ovc_servGraduated18to24Female;
    }

    public int getOvc_servGraduated18to24Male() {
        return ovc_servGraduated18to24Male;
    }

    public void setOvc_servGraduated18to24Male(int ovc_servGraduated18to24Male) {
        this.ovc_servGraduated18to24Male = ovc_servGraduated18to24Male;
    }

    public int getOvc_servGraduated1to4Female() {
        return ovc_servGraduated1to4Female;
    }

    public void setOvc_servGraduated1to4Female(int ovc_servGraduated1to4Female) {
        this.ovc_servGraduated1to4Female = ovc_servGraduated1to4Female;
    }

    public int getOvc_servGraduated1to4Male() {
        return ovc_servGraduated1to4Male;
    }

    public void setOvc_servGraduated1to4Male(int ovc_servGraduated1to4Male) {
        this.ovc_servGraduated1to4Male = ovc_servGraduated1to4Male;
    }

    public int getOvc_servGraduated25AndAboveFemale() {
        return ovc_servGraduated25AndAboveFemale;
    }

    public void setOvc_servGraduated25AndAboveFemale(int ovc_servGraduated25AndAboveFemale) {
        this.ovc_servGraduated25AndAboveFemale = ovc_servGraduated25AndAboveFemale;
    }

    public int getOvc_servGraduated25AndAboveMale() {
        return ovc_servGraduated25AndAboveMale;
    }

    public void setOvc_servGraduated25AndAboveMale(int ovc_servGraduated25AndAboveMale) {
        this.ovc_servGraduated25AndAboveMale = ovc_servGraduated25AndAboveMale;
    }

    public int getOvc_servGraduated5to9Female() {
        return ovc_servGraduated5to9Female;
    }

    public void setOvc_servGraduated5to9Female(int ovc_servGraduated5to9Female) {
        this.ovc_servGraduated5to9Female = ovc_servGraduated5to9Female;
    }

    public int getOvc_servGraduated5to9Male() {
        return ovc_servGraduated5to9Male;
    }

    public void setOvc_servGraduated5to9Male(int ovc_servGraduated5to9Male) {
        this.ovc_servGraduated5to9Male = ovc_servGraduated5to9Male;
    }

    public int getOvc_servGraduatedLessThan1Female() {
        return ovc_servGraduatedLessThan1Female;
    }

    public void setOvc_servGraduatedLessThan1Female(int ovc_servGraduatedLessThan1Female) {
        this.ovc_servGraduatedLessThan1Female = ovc_servGraduatedLessThan1Female;
    }

    public int getOvc_servGraduatedLessThan1Male() {
        return ovc_servGraduatedLessThan1Male;
    }

    public void setOvc_servGraduatedLessThan1Male(int ovc_servGraduatedLessThan1Male) {
        this.ovc_servGraduatedLessThan1Male = ovc_servGraduatedLessThan1Male;
    }

    public int getTransferedToNonPEPFAR() {
        return transferedToNonPEPFAR;
    }

    public void setTransferedToNonPEPFAR(int transferedToNonPEPFAR) {
        this.transferedToNonPEPFAR = transferedToNonPEPFAR;
    }

    public int getTransferedToPEPFAR() {
        return transferedToPEPFAR;
    }

    public void setTransferedToPEPFAR(int transferedToPEPFAR) {
        this.transferedToPEPFAR = transferedToPEPFAR;
    }
    
}
