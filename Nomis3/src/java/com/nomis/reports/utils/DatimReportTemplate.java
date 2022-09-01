/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class DatimReportTemplate implements Serializable
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
    
    private int activePositiveMaleLessThan1=0;
    private int activePositiveMale1To4=0;
    private int activePositiveMale5To9=0;
    private int activePositiveMale10To14=0;
    private int activePositiveMale15To17=0;
    private int activePositiveFemaleLessThan1=0;
    private int activePositiveFemale1To4=0;
    private int activePositiveFemale5To9=0;
    private int activePositiveFemale10To14=0;
    private int activePositiveFemale15To17=0;
    
    private int activePositiveEnrolledOnARTMaleLessThan1=0;
    private int activePositiveEnrolledOnARTMale1To4=0;
    private int activePositiveEnrolledOnARTMale5To9=0;
    private int activePositiveEnrolledOnARTMale10To14=0;
    private int activePositiveEnrolledOnARTMale15To17=0;
    private int activePositiveEnrolledOnARTFemaleLessThan1=0;
    private int activePositiveEnrolledOnARTFemale1To4=0;
    private int activePositiveEnrolledOnARTFemale5To9=0;
    private int activePositiveEnrolledOnARTFemale10To14=0;
    private int activePositiveEnrolledOnARTFemale15To17=0;
    
    private int activePositiveNotEnrolledOnARTMaleLessThan1=0;
    private int activePositiveNotEnrolledOnARTMale1To4=0;
    private int activePositiveNotEnrolledOnARTMale5To9=0;
    private int activePositiveNotEnrolledOnARTMale10To14=0;
    private int activePositiveNotEnrolledOnARTMale15To17=0;
    private int activePositiveNotEnrolledOnARTFemaleLessThan1=0;
    private int activePositiveNotEnrolledOnARTFemale1To4=0;
    private int activePositiveNotEnrolledOnARTFemale5To9=0;
    private int activePositiveNotEnrolledOnARTFemale10To14=0;
    private int activePositiveNotEnrolledOnARTFemale15To17=0;
    
    private int activeNegativeMaleLessThan1=0;
    private int activeNegativeMale1To4=0;
    private int activeNegativeMale5To9=0;
    private int activeNegativeMale10To14=0;
    private int activeNegativeMale15To17=0;
    private int activeNegativeFemaleLessThan1=0;
    private int activeNegativeFemale1To4=0;
    private int activeNegativeFemale5To9=0;
    private int activeNegativeFemale10To14=0;
    private int activeNegativeFemale15To17=0;
    
    private int activeUnknownMaleLessThan1=0;
    private int activeUnknownMale1To4=0;
    private int activeUnknownMale5To9=0;
    private int activeUnknownMale10To14=0;
    private int activeUnknownMale15To17=0;
    private int activeUnknownFemaleLessThan1=0;
    private int activeUnknownFemale1To4=0;
    private int activeUnknownFemale5To9=0;
    private int activeUnknownFemale10To14=0;
    private int activeUnknownFemale15To17=0;
    
    private int activeTestNotIndicatedMaleLessThan1=0;
    private int activeTestNotIndicatedMale1To4=0;
    private int activeTestNotIndicatedMale5To9=0;
    private int activeTestNotIndicatedMale10To14=0;
    private int activeTestNotIndicatedMale15To17=0;
    private int activeTestNotIndicatedFemaleLessThan1=0;
    private int activeTestNotIndicatedFemale1To4=0;
    private int activeTestNotIndicatedFemale5To9=0;
    private int activeTestNotIndicatedFemale10To14=0;
    private int activeTestNotIndicatedFemale15To17=0;
    
    private int activeOtherReasonsMaleLessThan1=0;
    private int activeOtherReasonsMale1To4=0;
    private int activeOtherReasonsMale5To9=0;
    private int activeOtherReasonsMale10To14=0;
    private int activeOtherReasonsMale15To17=0;
    private int activeOtherReasonsFemaleLessThan1=0;
    private int activeOtherReasonsFemale1To4=0;
    private int activeOtherReasonsFemale5To9=0;
    private int activeOtherReasonsFemale10To14=0;
    private int activeOtherReasonsFemale15To17=0;
    
    private int graduatedPositiveMaleLessThan1=0;
    private int graduatedPositiveMale1To4=0;
    private int graduatedPositiveMale5To9=0;
    private int graduatedPositiveMale10To14=0;
    private int graduatedPositiveMale15To17=0;
    private int graduatedPositiveFemaleLessThan1=0;
    private int graduatedPositiveFemale1To4=0;
    private int graduatedPositiveFemale5To9=0;
    private int graduatedPositiveFemale10To14=0;
    private int graduatedPositiveFemale15To17=0;
    
    private int graduatedPositiveEnrolledOnARTMaleLessThan1=0;
    private int graduatedPositiveEnrolledOnARTMale1To4=0;
    private int graduatedPositiveEnrolledOnARTMale5To9=0;
    private int graduatedPositiveEnrolledOnARTMale10To14=0;
    private int graduatedPositiveEnrolledOnARTMale15To17=0;
    private int graduatedPositiveEnrolledOnARTFemaleLessThan1=0;
    private int graduatedPositiveEnrolledOnARTFemale1To4=0;
    private int graduatedPositiveEnrolledOnARTFemale5To9=0;
    private int graduatedPositiveEnrolledOnARTFemale10To14=0;
    private int graduatedPositiveEnrolledOnARTFemale15To17=0;
    
    private int graduatedPositiveNotEnrolledOnARTMaleLessThan1=0;
    private int graduatedPositiveNotEnrolledOnARTMale1To4=0;
    private int graduatedPositiveNotEnrolledOnARTMale5To9=0;
    private int graduatedPositiveNotEnrolledOnARTMale10To14=0;
    private int graduatedPositiveNotEnrolledOnARTMale15To17=0;
    private int graduatedPositiveNotEnrolledOnARTFemaleLessThan1=0;
    private int graduatedPositiveNotEnrolledOnARTFemale1To4=0;
    private int graduatedPositiveNotEnrolledOnARTFemale5To9=0;
    private int graduatedPositiveNotEnrolledOnARTFemale10To14=0;
    private int graduatedPositiveNotEnrolledOnARTFemale15To17=0;
    
    private int graduatedNegativeMaleLessThan1=0;
    private int graduatedNegativeMale1To4=0;
    private int graduatedNegativeMale5To9=0;
    private int graduatedNegativeMale10To14=0;
    private int graduatedNegativeMale15To17=0;
    private int graduatedNegativeFemaleLessThan1=0;
    private int graduatedNegativeFemale1To4=0;
    private int graduatedNegativeFemale5To9=0;
    private int graduatedNegativeFemale10To14=0;
    private int graduatedNegativeFemale15To17=0;
    
    private int graduatedUnknownMaleLessThan1=0;
    private int graduatedUnknownMale1To4=0;
    private int graduatedUnknownMale5To9=0;
    private int graduatedUnknownMale10To14=0;
    private int graduatedUnknownMale15To17=0;
    private int graduatedUnknownFemaleLessThan1=0;
    private int graduatedUnknownFemale1To4=0;
    private int graduatedUnknownFemale5To9=0;
    private int graduatedUnknownFemale10To14=0;
    private int graduatedUnknownFemale15To17=0;
    
    private int graduatedTestNotIndicatedMaleLessThan1=0;
    private int graduatedTestNotIndicatedMale1To4=0;
    private int graduatedTestNotIndicatedMale5To9=0;
    private int graduatedTestNotIndicatedMale10To14=0;
    private int graduatedTestNotIndicatedMale15To17=0;
    private int graduatedTestNotIndicatedFemaleLessThan1=0;
    private int graduatedTestNotIndicatedFemale1To4=0;
    private int graduatedTestNotIndicatedFemale5To9=0;
    private int graduatedTestNotIndicatedFemale10To14=0;
    private int graduatedTestNotIndicatedFemale15To17=0;
    
    private int graduatedOtherReasonsMaleLessThan1=0;
    private int graduatedOtherReasonsMale1To4=0;
    private int graduatedOtherReasonsMale5To9=0;
    private int graduatedOtherReasonsMale10To14=0;
    private int graduatedOtherReasonsMale15To17=0;
    private int graduatedOtherReasonsFemaleLessThan1=0;
    private int graduatedOtherReasonsFemale1To4=0;
    private int graduatedOtherReasonsFemale5To9=0;
    private int graduatedOtherReasonsFemale10To14=0;
    private int graduatedOtherReasonsFemale15To17=0;
    
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
    
    
    private int caregiver_servActive18to24Male;
    private int caregiver_servActive18to24Female;
    private int caregiver_servActive25AndAboveMale;
    private int caregiver_servActive25AndAboveFemale;
    
    private int caregiver_servGraduated18to24Male;
    private int caregiver_servGraduated18to24Female;
    private int caregiver_servGraduated25AndAboveMale;
    private int caregiver_servGraduated25AndAboveFemale;
    
    private int transferedToPEPFAR;
    private int transferedToNonPEPFAR;
    
    private String startMth;
    private String startYr;
    private String endMth;
    private String endYr;
    private String period;
    private String partnerName;
    private String partnerCode;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;

    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
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

    public String getLevel3Ou() {
        return level3Ou;
    }

    public void setLevel3Ou(String level3Ou) {
        this.level3Ou = level3Ou;
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

    public String getLevel2Ou() {
        return level2Ou;
    }

    public void setLevel2Ou(String level2Ou) {
        this.level2Ou = level2Ou;
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

    public String getLevel4Ou() {
        return level4Ou;
    }

    public void setLevel4Ou(String level4Ou) {
        this.level4Ou = level4Ou;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    
    
    
    
    public int getCaregiver_servActive18to24Female() {
        return caregiver_servActive18to24Female;
    }

    public void setCaregiver_servActive18to24Female(int caregiver_servActive18to24Female) {
        this.caregiver_servActive18to24Female = caregiver_servActive18to24Female;
    }

    public int getCaregiver_servActive18to24Male() {
        return caregiver_servActive18to24Male;
    }

    public void setCaregiver_servActive18to24Male(int caregiver_servActive18to24Male) {
        this.caregiver_servActive18to24Male = caregiver_servActive18to24Male;
    }

    public int getCaregiver_servActive25AndAboveFemale() {
        return caregiver_servActive25AndAboveFemale;
    }

    public void setCaregiver_servActive25AndAboveFemale(int caregiver_servActive25AndAboveFemale) {
        this.caregiver_servActive25AndAboveFemale = caregiver_servActive25AndAboveFemale;
    }

    public int getCaregiver_servActive25AndAboveMale() {
        return caregiver_servActive25AndAboveMale;
    }

    public void setCaregiver_servActive25AndAboveMale(int caregiver_servActive25AndAboveMale) {
        this.caregiver_servActive25AndAboveMale = caregiver_servActive25AndAboveMale;
    }

    public int getCaregiver_servGraduated18to24Female() {
        return caregiver_servGraduated18to24Female;
    }

    public void setCaregiver_servGraduated18to24Female(int caregiver_servGraduated18to24Female) {
        this.caregiver_servGraduated18to24Female = caregiver_servGraduated18to24Female;
    }

    public int getCaregiver_servGraduated18to24Male() {
        return caregiver_servGraduated18to24Male;
    }

    public void setCaregiver_servGraduated18to24Male(int caregiver_servGraduated18to24Male) {
        this.caregiver_servGraduated18to24Male = caregiver_servGraduated18to24Male;
    }

    public int getCaregiver_servGraduated25AndAboveFemale() {
        return caregiver_servGraduated25AndAboveFemale;
    }

    public void setCaregiver_servGraduated25AndAboveFemale(int caregiver_servGraduated25AndAboveFemale) {
        this.caregiver_servGraduated25AndAboveFemale = caregiver_servGraduated25AndAboveFemale;
    }

    public int getCaregiver_servGraduated25AndAboveMale() {
        return caregiver_servGraduated25AndAboveMale;
    }

    public void setCaregiver_servGraduated25AndAboveMale(int caregiver_servGraduated25AndAboveMale) {
        this.caregiver_servGraduated25AndAboveMale = caregiver_servGraduated25AndAboveMale;
    }

    public int getActiveNegativeFemale10To14() {
        return activeNegativeFemale10To14;
    }

    public void setActiveNegativeFemale10To14(int activeNegativeFemale10To14) {
        this.activeNegativeFemale10To14 = activeNegativeFemale10To14;
    }

    public int getActiveNegativeFemale15To17() {
        return activeNegativeFemale15To17;
    }

    public void setActiveNegativeFemale15To17(int activeNegativeFemale15To17) {
        this.activeNegativeFemale15To17 = activeNegativeFemale15To17;
    }

    public int getActiveNegativeFemale1To4() {
        return activeNegativeFemale1To4;
    }

    public void setActiveNegativeFemale1To4(int activeNegativeFemale1To4) {
        this.activeNegativeFemale1To4 = activeNegativeFemale1To4;
    }

    public int getActiveNegativeFemale5To9() {
        return activeNegativeFemale5To9;
    }

    public void setActiveNegativeFemale5To9(int activeNegativeFemale5To9) {
        this.activeNegativeFemale5To9 = activeNegativeFemale5To9;
    }

    public int getActiveNegativeFemaleLessThan1() {
        return activeNegativeFemaleLessThan1;
    }

    public void setActiveNegativeFemaleLessThan1(int activeNegativeFemaleLessThan1) {
        this.activeNegativeFemaleLessThan1 = activeNegativeFemaleLessThan1;
    }

    public int getActiveNegativeMale10To14() {
        return activeNegativeMale10To14;
    }

    public void setActiveNegativeMale10To14(int activeNegativeMale10To14) {
        this.activeNegativeMale10To14 = activeNegativeMale10To14;
    }

    public int getActiveNegativeMale15To17() {
        return activeNegativeMale15To17;
    }

    public void setActiveNegativeMale15To17(int activeNegativeMale15To17) {
        this.activeNegativeMale15To17 = activeNegativeMale15To17;
    }

    public int getActiveNegativeMale1To4() {
        return activeNegativeMale1To4;
    }

    public void setActiveNegativeMale1To4(int activeNegativeMale1To4) {
        this.activeNegativeMale1To4 = activeNegativeMale1To4;
    }

    public int getActiveNegativeMale5To9() {
        return activeNegativeMale5To9;
    }

    public void setActiveNegativeMale5To9(int activeNegativeMale5To9) {
        this.activeNegativeMale5To9 = activeNegativeMale5To9;
    }

    public int getActiveNegativeMaleLessThan1() {
        return activeNegativeMaleLessThan1;
    }

    public void setActiveNegativeMaleLessThan1(int activeNegativeMaleLessThan1) {
        this.activeNegativeMaleLessThan1 = activeNegativeMaleLessThan1;
    }

    public int getActiveOtherReasonsFemale10To14() {
        return activeOtherReasonsFemale10To14;
    }

    public void setActiveOtherReasonsFemale10To14(int activeOtherReasonsFemale10To14) {
        this.activeOtherReasonsFemale10To14 = activeOtherReasonsFemale10To14;
    }

    public int getActiveOtherReasonsFemale15To17() {
        return activeOtherReasonsFemale15To17;
    }

    public void setActiveOtherReasonsFemale15To17(int activeOtherReasonsFemale15To17) {
        this.activeOtherReasonsFemale15To17 = activeOtherReasonsFemale15To17;
    }

    public int getActiveOtherReasonsFemale1To4() {
        return activeOtherReasonsFemale1To4;
    }

    public void setActiveOtherReasonsFemale1To4(int activeOtherReasonsFemale1To4) {
        this.activeOtherReasonsFemale1To4 = activeOtherReasonsFemale1To4;
    }

    public int getActiveOtherReasonsFemale5To9() {
        return activeOtherReasonsFemale5To9;
    }

    public void setActiveOtherReasonsFemale5To9(int activeOtherReasonsFemale5To9) {
        this.activeOtherReasonsFemale5To9 = activeOtherReasonsFemale5To9;
    }

    public int getActiveOtherReasonsFemaleLessThan1() {
        return activeOtherReasonsFemaleLessThan1;
    }

    public void setActiveOtherReasonsFemaleLessThan1(int activeOtherReasonsFemaleLessThan1) {
        this.activeOtherReasonsFemaleLessThan1 = activeOtherReasonsFemaleLessThan1;
    }

    public int getActiveOtherReasonsMale10To14() {
        return activeOtherReasonsMale10To14;
    }

    public void setActiveOtherReasonsMale10To14(int activeOtherReasonsMale10To14) {
        this.activeOtherReasonsMale10To14 = activeOtherReasonsMale10To14;
    }

    public int getActiveOtherReasonsMale15To17() {
        return activeOtherReasonsMale15To17;
    }

    public void setActiveOtherReasonsMale15To17(int activeOtherReasonsMale15To17) {
        this.activeOtherReasonsMale15To17 = activeOtherReasonsMale15To17;
    }

    public int getActiveOtherReasonsMale1To4() {
        return activeOtherReasonsMale1To4;
    }

    public void setActiveOtherReasonsMale1To4(int activeOtherReasonsMale1To4) {
        this.activeOtherReasonsMale1To4 = activeOtherReasonsMale1To4;
    }

    public int getActiveOtherReasonsMale5To9() {
        return activeOtherReasonsMale5To9;
    }

    public void setActiveOtherReasonsMale5To9(int activeOtherReasonsMale5To9) {
        this.activeOtherReasonsMale5To9 = activeOtherReasonsMale5To9;
    }

    public int getActiveOtherReasonsMaleLessThan1() {
        return activeOtherReasonsMaleLessThan1;
    }

    public void setActiveOtherReasonsMaleLessThan1(int activeOtherReasonsMaleLessThan1) {
        this.activeOtherReasonsMaleLessThan1 = activeOtherReasonsMaleLessThan1;
    }

    public int getActivePositiveEnrolledOnARTFemale10To14() {
        return activePositiveEnrolledOnARTFemale10To14;
    }

    public void setActivePositiveEnrolledOnARTFemale10To14(int activePositiveEnrolledOnARTFemale10To14) {
        this.activePositiveEnrolledOnARTFemale10To14 = activePositiveEnrolledOnARTFemale10To14;
    }

    public int getActivePositiveEnrolledOnARTFemale15To17() {
        return activePositiveEnrolledOnARTFemale15To17;
    }

    public void setActivePositiveEnrolledOnARTFemale15To17(int activePositiveEnrolledOnARTFemale15To17) {
        this.activePositiveEnrolledOnARTFemale15To17 = activePositiveEnrolledOnARTFemale15To17;
    }

    public int getActivePositiveEnrolledOnARTFemale1To4() {
        return activePositiveEnrolledOnARTFemale1To4;
    }

    public void setActivePositiveEnrolledOnARTFemale1To4(int activePositiveEnrolledOnARTFemale1To4) {
        this.activePositiveEnrolledOnARTFemale1To4 = activePositiveEnrolledOnARTFemale1To4;
    }

    public int getActivePositiveEnrolledOnARTFemale5To9() {
        return activePositiveEnrolledOnARTFemale5To9;
    }

    public void setActivePositiveEnrolledOnARTFemale5To9(int activePositiveEnrolledOnARTFemale5To9) {
        this.activePositiveEnrolledOnARTFemale5To9 = activePositiveEnrolledOnARTFemale5To9;
    }

    public int getActivePositiveEnrolledOnARTFemaleLessThan1() {
        return activePositiveEnrolledOnARTFemaleLessThan1;
    }

    public void setActivePositiveEnrolledOnARTFemaleLessThan1(int activePositiveEnrolledOnARTFemaleLessThan1) {
        this.activePositiveEnrolledOnARTFemaleLessThan1 = activePositiveEnrolledOnARTFemaleLessThan1;
    }

    public int getActivePositiveEnrolledOnARTMale10To14() {
        return activePositiveEnrolledOnARTMale10To14;
    }

    public void setActivePositiveEnrolledOnARTMale10To14(int activePositiveEnrolledOnARTMale10To14) {
        this.activePositiveEnrolledOnARTMale10To14 = activePositiveEnrolledOnARTMale10To14;
    }

    public int getActivePositiveEnrolledOnARTMale15To17() {
        return activePositiveEnrolledOnARTMale15To17;
    }

    public void setActivePositiveEnrolledOnARTMale15To17(int activePositiveEnrolledOnARTMale15To17) {
        this.activePositiveEnrolledOnARTMale15To17 = activePositiveEnrolledOnARTMale15To17;
    }

    public int getActivePositiveEnrolledOnARTMale1To4() {
        return activePositiveEnrolledOnARTMale1To4;
    }

    public void setActivePositiveEnrolledOnARTMale1To4(int activePositiveEnrolledOnARTMale1To4) {
        this.activePositiveEnrolledOnARTMale1To4 = activePositiveEnrolledOnARTMale1To4;
    }

    public int getActivePositiveEnrolledOnARTMale5To9() {
        return activePositiveEnrolledOnARTMale5To9;
    }

    public void setActivePositiveEnrolledOnARTMale5To9(int activePositiveEnrolledOnARTMale5To9) {
        this.activePositiveEnrolledOnARTMale5To9 = activePositiveEnrolledOnARTMale5To9;
    }

    public int getActivePositiveEnrolledOnARTMaleLessThan1() {
        return activePositiveEnrolledOnARTMaleLessThan1;
    }

    public void setActivePositiveEnrolledOnARTMaleLessThan1(int activePositiveEnrolledOnARTMaleLessThan1) {
        this.activePositiveEnrolledOnARTMaleLessThan1 = activePositiveEnrolledOnARTMaleLessThan1;
    }

    public int getActivePositiveFemale10To14() {
        return activePositiveFemale10To14;
    }

    public void setActivePositiveFemale10To14(int activePositiveFemale10To14) {
        this.activePositiveFemale10To14 = activePositiveFemale10To14;
    }

    public int getActivePositiveFemale15To17() {
        return activePositiveFemale15To17;
    }

    public void setActivePositiveFemale15To17(int activePositiveFemale15To17) {
        this.activePositiveFemale15To17 = activePositiveFemale15To17;
    }

    public int getActivePositiveFemale1To4() {
        return activePositiveFemale1To4;
    }

    public void setActivePositiveFemale1To4(int activePositiveFemale1To4) {
        this.activePositiveFemale1To4 = activePositiveFemale1To4;
    }

    public int getActivePositiveFemale5To9() {
        return activePositiveFemale5To9;
    }

    public void setActivePositiveFemale5To9(int activePositiveFemale5To9) {
        this.activePositiveFemale5To9 = activePositiveFemale5To9;
    }

    public int getActivePositiveFemaleLessThan1() {
        return activePositiveFemaleLessThan1;
    }

    public void setActivePositiveFemaleLessThan1(int activePositiveFemaleLessThan1) {
        this.activePositiveFemaleLessThan1 = activePositiveFemaleLessThan1;
    }

    public int getActivePositiveMale10To14() {
        return activePositiveMale10To14;
    }

    public void setActivePositiveMale10To14(int activePositiveMale10To14) {
        this.activePositiveMale10To14 = activePositiveMale10To14;
    }

    public int getActivePositiveMale15To17() {
        return activePositiveMale15To17;
    }

    public void setActivePositiveMale15To17(int activePositiveMale15To17) {
        this.activePositiveMale15To17 = activePositiveMale15To17;
    }

    public int getActivePositiveMale1To4() {
        return activePositiveMale1To4;
    }

    public void setActivePositiveMale1To4(int activePositiveMale1To4) {
        this.activePositiveMale1To4 = activePositiveMale1To4;
    }

    public int getActivePositiveMale5To9() {
        return activePositiveMale5To9;
    }

    public void setActivePositiveMale5To9(int activePositiveMale5To9) {
        this.activePositiveMale5To9 = activePositiveMale5To9;
    }

    public int getActivePositiveMaleLessThan1() {
        return activePositiveMaleLessThan1;
    }

    public void setActivePositiveMaleLessThan1(int activePositiveMaleLessThan1) {
        this.activePositiveMaleLessThan1 = activePositiveMaleLessThan1;
    }

    public int getActivePositiveNotEnrolledOnARTFemale10To14() {
        return activePositiveNotEnrolledOnARTFemale10To14;
    }

    public void setActivePositiveNotEnrolledOnARTFemale10To14(int activePositiveNotEnrolledOnARTFemale10To14) {
        this.activePositiveNotEnrolledOnARTFemale10To14 = activePositiveNotEnrolledOnARTFemale10To14;
    }

    public int getActivePositiveNotEnrolledOnARTFemale15To17() {
        return activePositiveNotEnrolledOnARTFemale15To17;
    }

    public void setActivePositiveNotEnrolledOnARTFemale15To17(int activePositiveNotEnrolledOnARTFemale15To17) {
        this.activePositiveNotEnrolledOnARTFemale15To17 = activePositiveNotEnrolledOnARTFemale15To17;
    }

    public int getActivePositiveNotEnrolledOnARTFemale1To4() {
        return activePositiveNotEnrolledOnARTFemale1To4;
    }

    public void setActivePositiveNotEnrolledOnARTFemale1To4(int activePositiveNotEnrolledOnARTFemale1To4) {
        this.activePositiveNotEnrolledOnARTFemale1To4 = activePositiveNotEnrolledOnARTFemale1To4;
    }

    public int getActivePositiveNotEnrolledOnARTFemale5To9() {
        return activePositiveNotEnrolledOnARTFemale5To9;
    }

    public void setActivePositiveNotEnrolledOnARTFemale5To9(int activePositiveNotEnrolledOnARTFemale5To9) {
        this.activePositiveNotEnrolledOnARTFemale5To9 = activePositiveNotEnrolledOnARTFemale5To9;
    }

    public int getActivePositiveNotEnrolledOnARTFemaleLessThan1() {
        return activePositiveNotEnrolledOnARTFemaleLessThan1;
    }

    public void setActivePositiveNotEnrolledOnARTFemaleLessThan1(int activePositiveNotEnrolledOnARTFemaleLessThan1) {
        this.activePositiveNotEnrolledOnARTFemaleLessThan1 = activePositiveNotEnrolledOnARTFemaleLessThan1;
    }

    public int getActivePositiveNotEnrolledOnARTMale10To14() {
        return activePositiveNotEnrolledOnARTMale10To14;
    }

    public void setActivePositiveNotEnrolledOnARTMale10To14(int activePositiveNotEnrolledOnARTMale10To14) {
        this.activePositiveNotEnrolledOnARTMale10To14 = activePositiveNotEnrolledOnARTMale10To14;
    }

    public int getActivePositiveNotEnrolledOnARTMale15To17() {
        return activePositiveNotEnrolledOnARTMale15To17;
    }

    public void setActivePositiveNotEnrolledOnARTMale15To17(int activePositiveNotEnrolledOnARTMale15To17) {
        this.activePositiveNotEnrolledOnARTMale15To17 = activePositiveNotEnrolledOnARTMale15To17;
    }

    public int getActivePositiveNotEnrolledOnARTMale1To4() {
        return activePositiveNotEnrolledOnARTMale1To4;
    }

    public void setActivePositiveNotEnrolledOnARTMale1To4(int activePositiveNotEnrolledOnARTMale1To4) {
        this.activePositiveNotEnrolledOnARTMale1To4 = activePositiveNotEnrolledOnARTMale1To4;
    }

    public int getActivePositiveNotEnrolledOnARTMale5To9() {
        return activePositiveNotEnrolledOnARTMale5To9;
    }

    public void setActivePositiveNotEnrolledOnARTMale5To9(int activePositiveNotEnrolledOnARTMale5To9) {
        this.activePositiveNotEnrolledOnARTMale5To9 = activePositiveNotEnrolledOnARTMale5To9;
    }

    public int getActivePositiveNotEnrolledOnARTMaleLessThan1() {
        return activePositiveNotEnrolledOnARTMaleLessThan1;
    }

    public void setActivePositiveNotEnrolledOnARTMaleLessThan1(int activePositiveNotEnrolledOnARTMaleLessThan1) {
        this.activePositiveNotEnrolledOnARTMaleLessThan1 = activePositiveNotEnrolledOnARTMaleLessThan1;
    }

    public int getActiveTestNotIndicatedFemale10To14() {
        return activeTestNotIndicatedFemale10To14;
    }

    public void setActiveTestNotIndicatedFemale10To14(int activeTestNotIndicatedFemale10To14) {
        this.activeTestNotIndicatedFemale10To14 = activeTestNotIndicatedFemale10To14;
    }

    public int getActiveTestNotIndicatedFemale15To17() {
        return activeTestNotIndicatedFemale15To17;
    }

    public void setActiveTestNotIndicatedFemale15To17(int activeTestNotIndicatedFemale15To17) {
        this.activeTestNotIndicatedFemale15To17 = activeTestNotIndicatedFemale15To17;
    }

    public int getActiveTestNotIndicatedFemale1To4() {
        return activeTestNotIndicatedFemale1To4;
    }

    public void setActiveTestNotIndicatedFemale1To4(int activeTestNotIndicatedFemale1To4) {
        this.activeTestNotIndicatedFemale1To4 = activeTestNotIndicatedFemale1To4;
    }

    public int getActiveTestNotIndicatedFemale5To9() {
        return activeTestNotIndicatedFemale5To9;
    }

    public void setActiveTestNotIndicatedFemale5To9(int activeTestNotIndicatedFemale5To9) {
        this.activeTestNotIndicatedFemale5To9 = activeTestNotIndicatedFemale5To9;
    }

    public int getActiveTestNotIndicatedFemaleLessThan1() {
        return activeTestNotIndicatedFemaleLessThan1;
    }

    public void setActiveTestNotIndicatedFemaleLessThan1(int activeTestNotIndicatedFemaleLessThan1) {
        this.activeTestNotIndicatedFemaleLessThan1 = activeTestNotIndicatedFemaleLessThan1;
    }

    public int getActiveTestNotIndicatedMale10To14() {
        return activeTestNotIndicatedMale10To14;
    }

    public void setActiveTestNotIndicatedMale10To14(int activeTestNotIndicatedMale10To14) {
        this.activeTestNotIndicatedMale10To14 = activeTestNotIndicatedMale10To14;
    }

    public int getActiveTestNotIndicatedMale15To17() {
        return activeTestNotIndicatedMale15To17;
    }

    public void setActiveTestNotIndicatedMale15To17(int activeTestNotIndicatedMale15To17) {
        this.activeTestNotIndicatedMale15To17 = activeTestNotIndicatedMale15To17;
    }

    public int getActiveTestNotIndicatedMale1To4() {
        return activeTestNotIndicatedMale1To4;
    }

    public void setActiveTestNotIndicatedMale1To4(int activeTestNotIndicatedMale1To4) {
        this.activeTestNotIndicatedMale1To4 = activeTestNotIndicatedMale1To4;
    }

    public int getActiveTestNotIndicatedMale5To9() {
        return activeTestNotIndicatedMale5To9;
    }

    public void setActiveTestNotIndicatedMale5To9(int activeTestNotIndicatedMale5To9) {
        this.activeTestNotIndicatedMale5To9 = activeTestNotIndicatedMale5To9;
    }

    public int getActiveTestNotIndicatedMaleLessThan1() {
        return activeTestNotIndicatedMaleLessThan1;
    }

    public void setActiveTestNotIndicatedMaleLessThan1(int activeTestNotIndicatedMaleLessThan1) {
        this.activeTestNotIndicatedMaleLessThan1 = activeTestNotIndicatedMaleLessThan1;
    }

    public int getActiveUnknownFemale10To14() {
        return activeUnknownFemale10To14;
    }

    public void setActiveUnknownFemale10To14(int activeUnknownFemale10To14) {
        this.activeUnknownFemale10To14 = activeUnknownFemale10To14;
    }

    public int getActiveUnknownFemale15To17() {
        return activeUnknownFemale15To17;
    }

    public void setActiveUnknownFemale15To17(int activeUnknownFemale15To17) {
        this.activeUnknownFemale15To17 = activeUnknownFemale15To17;
    }

    public int getActiveUnknownFemale1To4() {
        return activeUnknownFemale1To4;
    }

    public void setActiveUnknownFemale1To4(int activeUnknownFemale1To4) {
        this.activeUnknownFemale1To4 = activeUnknownFemale1To4;
    }

    public int getActiveUnknownFemale5To9() {
        return activeUnknownFemale5To9;
    }

    public void setActiveUnknownFemale5To9(int activeUnknownFemale5To9) {
        this.activeUnknownFemale5To9 = activeUnknownFemale5To9;
    }

    public int getActiveUnknownFemaleLessThan1() {
        return activeUnknownFemaleLessThan1;
    }

    public void setActiveUnknownFemaleLessThan1(int activeUnknownFemaleLessThan1) {
        this.activeUnknownFemaleLessThan1 = activeUnknownFemaleLessThan1;
    }

    public int getActiveUnknownMale10To14() {
        return activeUnknownMale10To14;
    }

    public void setActiveUnknownMale10To14(int activeUnknownMale10To14) {
        this.activeUnknownMale10To14 = activeUnknownMale10To14;
    }

    public int getActiveUnknownMale15To17() {
        return activeUnknownMale15To17;
    }

    public void setActiveUnknownMale15To17(int activeUnknownMale15To17) {
        this.activeUnknownMale15To17 = activeUnknownMale15To17;
    }

    public int getActiveUnknownMale1To4() {
        return activeUnknownMale1To4;
    }

    public void setActiveUnknownMale1To4(int activeUnknownMale1To4) {
        this.activeUnknownMale1To4 = activeUnknownMale1To4;
    }

    public int getActiveUnknownMale5To9() {
        return activeUnknownMale5To9;
    }

    public void setActiveUnknownMale5To9(int activeUnknownMale5To9) {
        this.activeUnknownMale5To9 = activeUnknownMale5To9;
    }

    public int getActiveUnknownMaleLessThan1() {
        return activeUnknownMaleLessThan1;
    }

    public void setActiveUnknownMaleLessThan1(int activeUnknownMaleLessThan1) {
        this.activeUnknownMaleLessThan1 = activeUnknownMaleLessThan1;
    }

    public int getGraduatedNegativeFemale10To14() {
        return graduatedNegativeFemale10To14;
    }

    public void setGraduatedNegativeFemale10To14(int graduatedNegativeFemale10To14) {
        this.graduatedNegativeFemale10To14 = graduatedNegativeFemale10To14;
    }

    public int getGraduatedNegativeFemale15To17() {
        return graduatedNegativeFemale15To17;
    }

    public void setGraduatedNegativeFemale15To17(int graduatedNegativeFemale15To17) {
        this.graduatedNegativeFemale15To17 = graduatedNegativeFemale15To17;
    }

    public int getGraduatedNegativeFemale1To4() {
        return graduatedNegativeFemale1To4;
    }

    public void setGraduatedNegativeFemale1To4(int graduatedNegativeFemale1To4) {
        this.graduatedNegativeFemale1To4 = graduatedNegativeFemale1To4;
    }

    public int getGraduatedNegativeFemale5To9() {
        return graduatedNegativeFemale5To9;
    }

    public void setGraduatedNegativeFemale5To9(int graduatedNegativeFemale5To9) {
        this.graduatedNegativeFemale5To9 = graduatedNegativeFemale5To9;
    }

    public int getGraduatedNegativeFemaleLessThan1() {
        return graduatedNegativeFemaleLessThan1;
    }

    public void setGraduatedNegativeFemaleLessThan1(int graduatedNegativeFemaleLessThan1) {
        this.graduatedNegativeFemaleLessThan1 = graduatedNegativeFemaleLessThan1;
    }

    public int getGraduatedNegativeMale10To14() {
        return graduatedNegativeMale10To14;
    }

    public void setGraduatedNegativeMale10To14(int graduatedNegativeMale10To14) {
        this.graduatedNegativeMale10To14 = graduatedNegativeMale10To14;
    }

    public int getGraduatedNegativeMale15To17() {
        return graduatedNegativeMale15To17;
    }

    public void setGraduatedNegativeMale15To17(int graduatedNegativeMale15To17) {
        this.graduatedNegativeMale15To17 = graduatedNegativeMale15To17;
    }

    public int getGraduatedNegativeMale1To4() {
        return graduatedNegativeMale1To4;
    }

    public void setGraduatedNegativeMale1To4(int graduatedNegativeMale1To4) {
        this.graduatedNegativeMale1To4 = graduatedNegativeMale1To4;
    }

    public int getGraduatedNegativeMale5To9() {
        return graduatedNegativeMale5To9;
    }

    public void setGraduatedNegativeMale5To9(int graduatedNegativeMale5To9) {
        this.graduatedNegativeMale5To9 = graduatedNegativeMale5To9;
    }

    public int getGraduatedNegativeMaleLessThan1() {
        return graduatedNegativeMaleLessThan1;
    }

    public void setGraduatedNegativeMaleLessThan1(int graduatedNegativeMaleLessThan1) {
        this.graduatedNegativeMaleLessThan1 = graduatedNegativeMaleLessThan1;
    }

    public int getGraduatedOtherReasonsFemale10To14() {
        return graduatedOtherReasonsFemale10To14;
    }

    public void setGraduatedOtherReasonsFemale10To14(int graduatedOtherReasonsFemale10To14) {
        this.graduatedOtherReasonsFemale10To14 = graduatedOtherReasonsFemale10To14;
    }

    public int getGraduatedOtherReasonsFemale15To17() {
        return graduatedOtherReasonsFemale15To17;
    }

    public void setGraduatedOtherReasonsFemale15To17(int graduatedOtherReasonsFemale15To17) {
        this.graduatedOtherReasonsFemale15To17 = graduatedOtherReasonsFemale15To17;
    }

    public int getGraduatedOtherReasonsFemale1To4() {
        return graduatedOtherReasonsFemale1To4;
    }

    public void setGraduatedOtherReasonsFemale1To4(int graduatedOtherReasonsFemale1To4) {
        this.graduatedOtherReasonsFemale1To4 = graduatedOtherReasonsFemale1To4;
    }

    public int getGraduatedOtherReasonsFemale5To9() {
        return graduatedOtherReasonsFemale5To9;
    }

    public void setGraduatedOtherReasonsFemale5To9(int graduatedOtherReasonsFemale5To9) {
        this.graduatedOtherReasonsFemale5To9 = graduatedOtherReasonsFemale5To9;
    }

    public int getGraduatedOtherReasonsFemaleLessThan1() {
        return graduatedOtherReasonsFemaleLessThan1;
    }

    public void setGraduatedOtherReasonsFemaleLessThan1(int graduatedOtherReasonsFemaleLessThan1) {
        this.graduatedOtherReasonsFemaleLessThan1 = graduatedOtherReasonsFemaleLessThan1;
    }

    public int getGraduatedOtherReasonsMale10To14() {
        return graduatedOtherReasonsMale10To14;
    }

    public void setGraduatedOtherReasonsMale10To14(int graduatedOtherReasonsMale10To14) {
        this.graduatedOtherReasonsMale10To14 = graduatedOtherReasonsMale10To14;
    }

    public int getGraduatedOtherReasonsMale15To17() {
        return graduatedOtherReasonsMale15To17;
    }

    public void setGraduatedOtherReasonsMale15To17(int graduatedOtherReasonsMale15To17) {
        this.graduatedOtherReasonsMale15To17 = graduatedOtherReasonsMale15To17;
    }

    public int getGraduatedOtherReasonsMale1To4() {
        return graduatedOtherReasonsMale1To4;
    }

    public void setGraduatedOtherReasonsMale1To4(int graduatedOtherReasonsMale1To4) {
        this.graduatedOtherReasonsMale1To4 = graduatedOtherReasonsMale1To4;
    }

    public int getGraduatedOtherReasonsMale5To9() {
        return graduatedOtherReasonsMale5To9;
    }

    public void setGraduatedOtherReasonsMale5To9(int graduatedOtherReasonsMale5To9) {
        this.graduatedOtherReasonsMale5To9 = graduatedOtherReasonsMale5To9;
    }

    public int getGraduatedOtherReasonsMaleLessThan1() {
        return graduatedOtherReasonsMaleLessThan1;
    }

    public void setGraduatedOtherReasonsMaleLessThan1(int graduatedOtherReasonsMaleLessThan1) {
        this.graduatedOtherReasonsMaleLessThan1 = graduatedOtherReasonsMaleLessThan1;
    }

    public int getGraduatedPositiveEnrolledOnARTFemale10To14() {
        return graduatedPositiveEnrolledOnARTFemale10To14;
    }

    public void setGraduatedPositiveEnrolledOnARTFemale10To14(int graduatedPositiveEnrolledOnARTFemale10To14) {
        this.graduatedPositiveEnrolledOnARTFemale10To14 = graduatedPositiveEnrolledOnARTFemale10To14;
    }

    public int getGraduatedPositiveEnrolledOnARTFemale15To17() {
        return graduatedPositiveEnrolledOnARTFemale15To17;
    }

    public void setGraduatedPositiveEnrolledOnARTFemale15To17(int graduatedPositiveEnrolledOnARTFemale15To17) {
        this.graduatedPositiveEnrolledOnARTFemale15To17 = graduatedPositiveEnrolledOnARTFemale15To17;
    }

    public int getGraduatedPositiveEnrolledOnARTFemale1To4() {
        return graduatedPositiveEnrolledOnARTFemale1To4;
    }

    public void setGraduatedPositiveEnrolledOnARTFemale1To4(int graduatedPositiveEnrolledOnARTFemale1To4) {
        this.graduatedPositiveEnrolledOnARTFemale1To4 = graduatedPositiveEnrolledOnARTFemale1To4;
    }

    public int getGraduatedPositiveEnrolledOnARTFemale5To9() {
        return graduatedPositiveEnrolledOnARTFemale5To9;
    }

    public void setGraduatedPositiveEnrolledOnARTFemale5To9(int graduatedPositiveEnrolledOnARTFemale5To9) {
        this.graduatedPositiveEnrolledOnARTFemale5To9 = graduatedPositiveEnrolledOnARTFemale5To9;
    }

    public int getGraduatedPositiveEnrolledOnARTFemaleLessThan1() {
        return graduatedPositiveEnrolledOnARTFemaleLessThan1;
    }

    public void setGraduatedPositiveEnrolledOnARTFemaleLessThan1(int graduatedPositiveEnrolledOnARTFemaleLessThan1) {
        this.graduatedPositiveEnrolledOnARTFemaleLessThan1 = graduatedPositiveEnrolledOnARTFemaleLessThan1;
    }

    public int getGraduatedPositiveEnrolledOnARTMale10To14() {
        return graduatedPositiveEnrolledOnARTMale10To14;
    }

    public void setGraduatedPositiveEnrolledOnARTMale10To14(int graduatedPositiveEnrolledOnARTMale10To14) {
        this.graduatedPositiveEnrolledOnARTMale10To14 = graduatedPositiveEnrolledOnARTMale10To14;
    }

    public int getGraduatedPositiveEnrolledOnARTMale15To17() {
        return graduatedPositiveEnrolledOnARTMale15To17;
    }

    public void setGraduatedPositiveEnrolledOnARTMale15To17(int graduatedPositiveEnrolledOnARTMale15To17) {
        this.graduatedPositiveEnrolledOnARTMale15To17 = graduatedPositiveEnrolledOnARTMale15To17;
    }

    public int getGraduatedPositiveEnrolledOnARTMale1To4() {
        return graduatedPositiveEnrolledOnARTMale1To4;
    }

    public void setGraduatedPositiveEnrolledOnARTMale1To4(int graduatedPositiveEnrolledOnARTMale1To4) {
        this.graduatedPositiveEnrolledOnARTMale1To4 = graduatedPositiveEnrolledOnARTMale1To4;
    }

    public int getGraduatedPositiveEnrolledOnARTMale5To9() {
        return graduatedPositiveEnrolledOnARTMale5To9;
    }

    public void setGraduatedPositiveEnrolledOnARTMale5To9(int graduatedPositiveEnrolledOnARTMale5To9) {
        this.graduatedPositiveEnrolledOnARTMale5To9 = graduatedPositiveEnrolledOnARTMale5To9;
    }

    public int getGraduatedPositiveEnrolledOnARTMaleLessThan1() {
        return graduatedPositiveEnrolledOnARTMaleLessThan1;
    }

    public void setGraduatedPositiveEnrolledOnARTMaleLessThan1(int graduatedPositiveEnrolledOnARTMaleLessThan1) {
        this.graduatedPositiveEnrolledOnARTMaleLessThan1 = graduatedPositiveEnrolledOnARTMaleLessThan1;
    }

    public int getGraduatedPositiveFemale10To14() {
        return graduatedPositiveFemale10To14;
    }

    public void setGraduatedPositiveFemale10To14(int graduatedPositiveFemale10To14) {
        this.graduatedPositiveFemale10To14 = graduatedPositiveFemale10To14;
    }

    public int getGraduatedPositiveFemale15To17() {
        return graduatedPositiveFemale15To17;
    }

    public void setGraduatedPositiveFemale15To17(int graduatedPositiveFemale15To17) {
        this.graduatedPositiveFemale15To17 = graduatedPositiveFemale15To17;
    }

    public int getGraduatedPositiveFemale1To4() {
        return graduatedPositiveFemale1To4;
    }

    public void setGraduatedPositiveFemale1To4(int graduatedPositiveFemale1To4) {
        this.graduatedPositiveFemale1To4 = graduatedPositiveFemale1To4;
    }

    public int getGraduatedPositiveFemale5To9() {
        return graduatedPositiveFemale5To9;
    }

    public void setGraduatedPositiveFemale5To9(int graduatedPositiveFemale5To9) {
        this.graduatedPositiveFemale5To9 = graduatedPositiveFemale5To9;
    }

    public int getGraduatedPositiveFemaleLessThan1() {
        return graduatedPositiveFemaleLessThan1;
    }

    public void setGraduatedPositiveFemaleLessThan1(int graduatedPositiveFemaleLessThan1) {
        this.graduatedPositiveFemaleLessThan1 = graduatedPositiveFemaleLessThan1;
    }

    public int getGraduatedPositiveMale10To14() {
        return graduatedPositiveMale10To14;
    }

    public void setGraduatedPositiveMale10To14(int graduatedPositiveMale10To14) {
        this.graduatedPositiveMale10To14 = graduatedPositiveMale10To14;
    }

    public int getGraduatedPositiveMale15To17() {
        return graduatedPositiveMale15To17;
    }

    public void setGraduatedPositiveMale15To17(int graduatedPositiveMale15To17) {
        this.graduatedPositiveMale15To17 = graduatedPositiveMale15To17;
    }

    public int getGraduatedPositiveMale1To4() {
        return graduatedPositiveMale1To4;
    }

    public void setGraduatedPositiveMale1To4(int graduatedPositiveMale1To4) {
        this.graduatedPositiveMale1To4 = graduatedPositiveMale1To4;
    }

    public int getGraduatedPositiveMale5To9() {
        return graduatedPositiveMale5To9;
    }

    public void setGraduatedPositiveMale5To9(int graduatedPositiveMale5To9) {
        this.graduatedPositiveMale5To9 = graduatedPositiveMale5To9;
    }

    public int getGraduatedPositiveMaleLessThan1() {
        return graduatedPositiveMaleLessThan1;
    }

    public void setGraduatedPositiveMaleLessThan1(int graduatedPositiveMaleLessThan1) {
        this.graduatedPositiveMaleLessThan1 = graduatedPositiveMaleLessThan1;
    }

    public int getGraduatedPositiveNotEnrolledOnARTFemale10To14() {
        return graduatedPositiveNotEnrolledOnARTFemale10To14;
    }

    public void setGraduatedPositiveNotEnrolledOnARTFemale10To14(int graduatedPositiveNotEnrolledOnARTFemale10To14) {
        this.graduatedPositiveNotEnrolledOnARTFemale10To14 = graduatedPositiveNotEnrolledOnARTFemale10To14;
    }

    public int getGraduatedPositiveNotEnrolledOnARTFemale15To17() {
        return graduatedPositiveNotEnrolledOnARTFemale15To17;
    }

    public void setGraduatedPositiveNotEnrolledOnARTFemale15To17(int graduatedPositiveNotEnrolledOnARTFemale15To17) {
        this.graduatedPositiveNotEnrolledOnARTFemale15To17 = graduatedPositiveNotEnrolledOnARTFemale15To17;
    }

    public int getGraduatedPositiveNotEnrolledOnARTFemale1To4() {
        return graduatedPositiveNotEnrolledOnARTFemale1To4;
    }

    public void setGraduatedPositiveNotEnrolledOnARTFemale1To4(int graduatedPositiveNotEnrolledOnARTFemale1To4) {
        this.graduatedPositiveNotEnrolledOnARTFemale1To4 = graduatedPositiveNotEnrolledOnARTFemale1To4;
    }

    public int getGraduatedPositiveNotEnrolledOnARTFemale5To9() {
        return graduatedPositiveNotEnrolledOnARTFemale5To9;
    }

    public void setGraduatedPositiveNotEnrolledOnARTFemale5To9(int graduatedPositiveNotEnrolledOnARTFemale5To9) {
        this.graduatedPositiveNotEnrolledOnARTFemale5To9 = graduatedPositiveNotEnrolledOnARTFemale5To9;
    }

    public int getGraduatedPositiveNotEnrolledOnARTFemaleLessThan1() {
        return graduatedPositiveNotEnrolledOnARTFemaleLessThan1;
    }

    public void setGraduatedPositiveNotEnrolledOnARTFemaleLessThan1(int graduatedPositiveNotEnrolledOnARTFemaleLessThan1) {
        this.graduatedPositiveNotEnrolledOnARTFemaleLessThan1 = graduatedPositiveNotEnrolledOnARTFemaleLessThan1;
    }

    public int getGraduatedPositiveNotEnrolledOnARTMale10To14() {
        return graduatedPositiveNotEnrolledOnARTMale10To14;
    }

    public void setGraduatedPositiveNotEnrolledOnARTMale10To14(int graduatedPositiveNotEnrolledOnARTMale10To14) {
        this.graduatedPositiveNotEnrolledOnARTMale10To14 = graduatedPositiveNotEnrolledOnARTMale10To14;
    }

    public int getGraduatedPositiveNotEnrolledOnARTMale15To17() {
        return graduatedPositiveNotEnrolledOnARTMale15To17;
    }

    public void setGraduatedPositiveNotEnrolledOnARTMale15To17(int graduatedPositiveNotEnrolledOnARTMale15To17) {
        this.graduatedPositiveNotEnrolledOnARTMale15To17 = graduatedPositiveNotEnrolledOnARTMale15To17;
    }

    public int getGraduatedPositiveNotEnrolledOnARTMale1To4() {
        return graduatedPositiveNotEnrolledOnARTMale1To4;
    }

    public void setGraduatedPositiveNotEnrolledOnARTMale1To4(int graduatedPositiveNotEnrolledOnARTMale1To4) {
        this.graduatedPositiveNotEnrolledOnARTMale1To4 = graduatedPositiveNotEnrolledOnARTMale1To4;
    }

    public int getGraduatedPositiveNotEnrolledOnARTMale5To9() {
        return graduatedPositiveNotEnrolledOnARTMale5To9;
    }

    public void setGraduatedPositiveNotEnrolledOnARTMale5To9(int graduatedPositiveNotEnrolledOnARTMale5To9) {
        this.graduatedPositiveNotEnrolledOnARTMale5To9 = graduatedPositiveNotEnrolledOnARTMale5To9;
    }

    public int getGraduatedPositiveNotEnrolledOnARTMaleLessThan1() {
        return graduatedPositiveNotEnrolledOnARTMaleLessThan1;
    }

    public void setGraduatedPositiveNotEnrolledOnARTMaleLessThan1(int graduatedPositiveNotEnrolledOnARTMaleLessThan1) {
        this.graduatedPositiveNotEnrolledOnARTMaleLessThan1 = graduatedPositiveNotEnrolledOnARTMaleLessThan1;
    }

    public int getGraduatedTestNotIndicatedFemale10To14() {
        return graduatedTestNotIndicatedFemale10To14;
    }

    public void setGraduatedTestNotIndicatedFemale10To14(int graduatedTestNotIndicatedFemale10To14) {
        this.graduatedTestNotIndicatedFemale10To14 = graduatedTestNotIndicatedFemale10To14;
    }

    public int getGraduatedTestNotIndicatedFemale15To17() {
        return graduatedTestNotIndicatedFemale15To17;
    }

    public void setGraduatedTestNotIndicatedFemale15To17(int graduatedTestNotIndicatedFemale15To17) {
        this.graduatedTestNotIndicatedFemale15To17 = graduatedTestNotIndicatedFemale15To17;
    }

    public int getGraduatedTestNotIndicatedFemale1To4() {
        return graduatedTestNotIndicatedFemale1To4;
    }

    public void setGraduatedTestNotIndicatedFemale1To4(int graduatedTestNotIndicatedFemale1To4) {
        this.graduatedTestNotIndicatedFemale1To4 = graduatedTestNotIndicatedFemale1To4;
    }

    public int getGraduatedTestNotIndicatedFemale5To9() {
        return graduatedTestNotIndicatedFemale5To9;
    }

    public void setGraduatedTestNotIndicatedFemale5To9(int graduatedTestNotIndicatedFemale5To9) {
        this.graduatedTestNotIndicatedFemale5To9 = graduatedTestNotIndicatedFemale5To9;
    }

    public int getGraduatedTestNotIndicatedFemaleLessThan1() {
        return graduatedTestNotIndicatedFemaleLessThan1;
    }

    public void setGraduatedTestNotIndicatedFemaleLessThan1(int graduatedTestNotIndicatedFemaleLessThan1) {
        this.graduatedTestNotIndicatedFemaleLessThan1 = graduatedTestNotIndicatedFemaleLessThan1;
    }

    public int getGraduatedTestNotIndicatedMale10To14() {
        return graduatedTestNotIndicatedMale10To14;
    }

    public void setGraduatedTestNotIndicatedMale10To14(int graduatedTestNotIndicatedMale10To14) {
        this.graduatedTestNotIndicatedMale10To14 = graduatedTestNotIndicatedMale10To14;
    }

    public int getGraduatedTestNotIndicatedMale15To17() {
        return graduatedTestNotIndicatedMale15To17;
    }

    public void setGraduatedTestNotIndicatedMale15To17(int graduatedTestNotIndicatedMale15To17) {
        this.graduatedTestNotIndicatedMale15To17 = graduatedTestNotIndicatedMale15To17;
    }

    public int getGraduatedTestNotIndicatedMale1To4() {
        return graduatedTestNotIndicatedMale1To4;
    }

    public void setGraduatedTestNotIndicatedMale1To4(int graduatedTestNotIndicatedMale1To4) {
        this.graduatedTestNotIndicatedMale1To4 = graduatedTestNotIndicatedMale1To4;
    }

    public int getGraduatedTestNotIndicatedMale5To9() {
        return graduatedTestNotIndicatedMale5To9;
    }

    public void setGraduatedTestNotIndicatedMale5To9(int graduatedTestNotIndicatedMale5To9) {
        this.graduatedTestNotIndicatedMale5To9 = graduatedTestNotIndicatedMale5To9;
    }

    public int getGraduatedTestNotIndicatedMaleLessThan1() {
        return graduatedTestNotIndicatedMaleLessThan1;
    }

    public void setGraduatedTestNotIndicatedMaleLessThan1(int graduatedTestNotIndicatedMaleLessThan1) {
        this.graduatedTestNotIndicatedMaleLessThan1 = graduatedTestNotIndicatedMaleLessThan1;
    }

    public int getGraduatedUnknownFemale10To14() {
        return graduatedUnknownFemale10To14;
    }

    public void setGraduatedUnknownFemale10To14(int graduatedUnknownFemale10To14) {
        this.graduatedUnknownFemale10To14 = graduatedUnknownFemale10To14;
    }

    public int getGraduatedUnknownFemale15To17() {
        return graduatedUnknownFemale15To17;
    }

    public void setGraduatedUnknownFemale15To17(int graduatedUnknownFemale15To17) {
        this.graduatedUnknownFemale15To17 = graduatedUnknownFemale15To17;
    }

    public int getGraduatedUnknownFemale1To4() {
        return graduatedUnknownFemale1To4;
    }

    public void setGraduatedUnknownFemale1To4(int graduatedUnknownFemale1To4) {
        this.graduatedUnknownFemale1To4 = graduatedUnknownFemale1To4;
    }

    public int getGraduatedUnknownFemale5To9() {
        return graduatedUnknownFemale5To9;
    }

    public void setGraduatedUnknownFemale5To9(int graduatedUnknownFemale5To9) {
        this.graduatedUnknownFemale5To9 = graduatedUnknownFemale5To9;
    }

    public int getGraduatedUnknownFemaleLessThan1() {
        return graduatedUnknownFemaleLessThan1;
    }

    public void setGraduatedUnknownFemaleLessThan1(int graduatedUnknownFemaleLessThan1) {
        this.graduatedUnknownFemaleLessThan1 = graduatedUnknownFemaleLessThan1;
    }

    public int getGraduatedUnknownMale10To14() {
        return graduatedUnknownMale10To14;
    }

    public void setGraduatedUnknownMale10To14(int graduatedUnknownMale10To14) {
        this.graduatedUnknownMale10To14 = graduatedUnknownMale10To14;
    }

    public int getGraduatedUnknownMale15To17() {
        return graduatedUnknownMale15To17;
    }

    public void setGraduatedUnknownMale15To17(int graduatedUnknownMale15To17) {
        this.graduatedUnknownMale15To17 = graduatedUnknownMale15To17;
    }

    public int getGraduatedUnknownMale1To4() {
        return graduatedUnknownMale1To4;
    }

    public void setGraduatedUnknownMale1To4(int graduatedUnknownMale1To4) {
        this.graduatedUnknownMale1To4 = graduatedUnknownMale1To4;
    }

    public int getGraduatedUnknownMale5To9() {
        return graduatedUnknownMale5To9;
    }

    public void setGraduatedUnknownMale5To9(int graduatedUnknownMale5To9) {
        this.graduatedUnknownMale5To9 = graduatedUnknownMale5To9;
    }

    public int getGraduatedUnknownMaleLessThan1() {
        return graduatedUnknownMaleLessThan1;
    }

    public void setGraduatedUnknownMaleLessThan1(int graduatedUnknownMaleLessThan1) {
        this.graduatedUnknownMaleLessThan1 = graduatedUnknownMaleLessThan1;
    }
    
}
