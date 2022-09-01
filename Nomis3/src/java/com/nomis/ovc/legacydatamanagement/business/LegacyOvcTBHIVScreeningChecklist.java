/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.business;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class LegacyOvcTBHIVScreeningChecklist implements Serializable
{
    private String orgCode;
    private String communityCode;
    private String hhUniqueId;
    private String ovcId;
    private String ovcGender;
    private int ovcAge;
    private String ageUnit;
    private int id;
    private int assessmentNo;
    private String dateOfAssessment;
    private String childCoughing;
    private String childLossindWeight;
    private String childHavingFever;
    private String childHavingNightSweat;
    private String familyMemberHadTB;
    private String dosesOfARVMissedByChild;
    private String caregiverEnrolledInPMTCT;
    private String eidTestingDoneForChild;
    private String childOnSeptrin;
    private String hivStatusDisclosedToPartner;
    private String hivStatusOfPartnerKnown;
    private String dosesOfSeptrinMissedByChild;
    private String childAboveSixWksAndStartedOnSeptrin;
    private String childSleepInTreatedNet;
    private String beneficiariesHasSoresOrBleeding;
    private String numberOfClubsAttndedInThreeMths;
    private String childSwellingOnNeck;
    private String volunteerName;
    private String designation;
    private String dateModified;
    private int markedForDelete;

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public int getAssessmentNo() {
        return assessmentNo;
    }

    public void setAssessmentNo(int assessmentNo) {
        this.assessmentNo = assessmentNo;
    }

    public String getBeneficiariesHasSoresOrBleeding() {
        return beneficiariesHasSoresOrBleeding;
    }

    public void setBeneficiariesHasSoresOrBleeding(String beneficiariesHasSoresOrBleeding) {
        this.beneficiariesHasSoresOrBleeding = beneficiariesHasSoresOrBleeding;
    }

    public String getCaregiverEnrolledInPMTCT() {
        return caregiverEnrolledInPMTCT;
    }

    public void setCaregiverEnrolledInPMTCT(String caregiverEnrolledInPMTCT) {
        this.caregiverEnrolledInPMTCT = caregiverEnrolledInPMTCT;
    }

    public String getChildAboveSixWksAndStartedOnSeptrin() {
        return childAboveSixWksAndStartedOnSeptrin;
    }

    public void setChildAboveSixWksAndStartedOnSeptrin(String childAboveSixWksAndStartedOnSeptrin) {
        this.childAboveSixWksAndStartedOnSeptrin = childAboveSixWksAndStartedOnSeptrin;
    }

    public String getChildCoughing() {
        return childCoughing;
    }

    public void setChildCoughing(String childCoughing) {
        this.childCoughing = childCoughing;
    }

    public String getChildHavingFever() {
        return childHavingFever;
    }

    public void setChildHavingFever(String childHavingFever) {
        this.childHavingFever = childHavingFever;
    }

    public String getChildHavingNightSweat() {
        return childHavingNightSweat;
    }

    public void setChildHavingNightSweat(String childHavingNightSweat) {
        this.childHavingNightSweat = childHavingNightSweat;
    }

    public String getChildLossindWeight() {
        return childLossindWeight;
    }

    public void setChildLossindWeight(String childLossindWeight) {
        this.childLossindWeight = childLossindWeight;
    }

    public String getChildOnSeptrin() {
        return childOnSeptrin;
    }

    public void setChildOnSeptrin(String childOnSeptrin) {
        this.childOnSeptrin = childOnSeptrin;
    }

    public String getChildSleepInTreatedNet() {
        return childSleepInTreatedNet;
    }

    public void setChildSleepInTreatedNet(String childSleepInTreatedNet) {
        this.childSleepInTreatedNet = childSleepInTreatedNet;
    }

    public String getChildSwellingOnNeck() {
        return childSwellingOnNeck;
    }

    public void setChildSwellingOnNeck(String childSwellingOnNeck) {
        this.childSwellingOnNeck = childSwellingOnNeck;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getDosesOfARVMissedByChild() {
        return dosesOfARVMissedByChild;
    }

    public void setDosesOfARVMissedByChild(String dosesOfARVMissedByChild) {
        this.dosesOfARVMissedByChild = dosesOfARVMissedByChild;
    }

    public String getDosesOfSeptrinMissedByChild() {
        return dosesOfSeptrinMissedByChild;
    }

    public void setDosesOfSeptrinMissedByChild(String dosesOfSeptrinMissedByChild) {
        this.dosesOfSeptrinMissedByChild = dosesOfSeptrinMissedByChild;
    }

    public String getEidTestingDoneForChild() {
        return eidTestingDoneForChild;
    }

    public void setEidTestingDoneForChild(String eidTestingDoneForChild) {
        this.eidTestingDoneForChild = eidTestingDoneForChild;
    }

    public String getFamilyMemberHadTB() {
        return familyMemberHadTB;
    }

    public void setFamilyMemberHadTB(String familyMemberHadTB) {
        this.familyMemberHadTB = familyMemberHadTB;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getHivStatusDisclosedToPartner() {
        return hivStatusDisclosedToPartner;
    }

    public void setHivStatusDisclosedToPartner(String hivStatusDisclosedToPartner) {
        this.hivStatusDisclosedToPartner = hivStatusDisclosedToPartner;
    }

    public String getHivStatusOfPartnerKnown() {
        return hivStatusOfPartnerKnown;
    }

    public void setHivStatusOfPartnerKnown(String hivStatusOfPartnerKnown) {
        this.hivStatusOfPartnerKnown = hivStatusOfPartnerKnown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberOfClubsAttndedInThreeMths() {
        return numberOfClubsAttndedInThreeMths;
    }

    public void setNumberOfClubsAttndedInThreeMths(String numberOfClubsAttndedInThreeMths) {
        this.numberOfClubsAttndedInThreeMths = numberOfClubsAttndedInThreeMths;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public int getOvcAge() {
        return ovcAge;
    }

    public void setOvcAge(int ovcAge) {
        this.ovcAge = ovcAge;
    }

    public String getOvcGender() {
        return ovcGender;
    }

    public void setOvcGender(String ovcGender) {
        this.ovcGender = ovcGender;
    }

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
    
}
