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
public class LegacyCaregiverTBHIVScreeningChecklist implements Serializable
{
    private String orgCode;
    private String communityCode;
    private String hhUniqueId;
    private String caregiverId;
    private String gender;
    private int age;
    private int id;
    private int assessmentNo;
    private String dateOfAssessment;
    private String caregiverCoughing;
    private String caregiverLossindWeight;
    private String caregiverHavingFever;
    private String caregiverHavingNightSweat;
    private String familyMemberHadTB;
    private String dosesOfARVMissedByCaregiver;
    private String caregiverEnrolledInPMTCT;
    private String caregiverOnSeptrin;
    private String hivStatusDisclosedToPartner;
    private String hivStatusOfPartnerKnown;
    private String dosesOfSeptrinMissedByCaregiver;
    private String childAboveSixWksAndStartedOnSeptrin;
    private String eidTestingDoneForChild;
    private String caregiverSleepInTreatedNet;
    private String beneficiariesHasSoresOrBleeding;
    private String numberOfClubsAttndedInThreeMths;
    private String caregiverSwellingOnNeck;
    private String volunteerName;
    private String designation;
    private String dateModified;
    private int markedForDelete;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getChildAboveSixWksAndStartedOnSeptrin() {
        return childAboveSixWksAndStartedOnSeptrin;
    }

    public void setChildAboveSixWksAndStartedOnSeptrin(String childAboveSixWksAndStartedOnSeptrin) {
        this.childAboveSixWksAndStartedOnSeptrin = childAboveSixWksAndStartedOnSeptrin;
    }
    
    public String getCaregiverCoughing() {
        return caregiverCoughing;
    }

    public void setCaregiverCoughing(String caregiverCoughing) {
        this.caregiverCoughing = caregiverCoughing;
    }

    public String getCaregiverEnrolledInPMTCT() {
        return caregiverEnrolledInPMTCT;
    }

    public void setCaregiverEnrolledInPMTCT(String caregiverEnrolledInPMTCT) {
        this.caregiverEnrolledInPMTCT = caregiverEnrolledInPMTCT;
    }

    public String getCaregiverHavingFever() {
        return caregiverHavingFever;
    }

    public void setCaregiverHavingFever(String caregiverHavingFever) {
        this.caregiverHavingFever = caregiverHavingFever;
    }

    public String getCaregiverHavingNightSweat() {
        return caregiverHavingNightSweat;
    }

    public void setCaregiverHavingNightSweat(String caregiverHavingNightSweat) {
        this.caregiverHavingNightSweat = caregiverHavingNightSweat;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public String getCaregiverLossindWeight() {
        return caregiverLossindWeight;
    }

    public void setCaregiverLossindWeight(String caregiverLossindWeight) {
        this.caregiverLossindWeight = caregiverLossindWeight;
    }

    public String getCaregiverOnSeptrin() {
        return caregiverOnSeptrin;
    }

    public void setCaregiverOnSeptrin(String caregiverOnSeptrin) {
        this.caregiverOnSeptrin = caregiverOnSeptrin;
    }

    public String getCaregiverSleepInTreatedNet() {
        return caregiverSleepInTreatedNet;
    }

    public void setCaregiverSleepInTreatedNet(String caregiverSleepInTreatedNet) {
        this.caregiverSleepInTreatedNet = caregiverSleepInTreatedNet;
    }

    public String getCaregiverSwellingOnNeck() {
        return caregiverSwellingOnNeck;
    }

    public void setCaregiverSwellingOnNeck(String caregiverSwellingOnNeck) {
        this.caregiverSwellingOnNeck = caregiverSwellingOnNeck;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDosesOfARVMissedByCaregiver() {
        return dosesOfARVMissedByCaregiver;
    }

    public void setDosesOfARVMissedByCaregiver(String dosesOfARVMissedByCaregiver) {
        this.dosesOfARVMissedByCaregiver = dosesOfARVMissedByCaregiver;
    }

    public String getDosesOfSeptrinMissedByCaregiver() {
        return dosesOfSeptrinMissedByCaregiver;
    }

    public void setDosesOfSeptrinMissedByCaregiver(String dosesOfSeptrinMissedByCaregiver) {
        this.dosesOfSeptrinMissedByCaregiver = dosesOfSeptrinMissedByCaregiver;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
    
}
