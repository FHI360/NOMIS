/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class CareplanAchievementChecklist implements Serializable
{
    private int recordId;
    private String hhUniqueId;
    private Date dateOfAssessment;
    private Date dateCreated;
    private Date lastModifiedDate;
    private int childrenHivStatusknown;
    private int hivPosAdolscentsLinked;
    private int documentedViralLoadResult;
    private int hivPreventionKnowledge;
    private int childrenNotUndernourished;
    private int allChildrenHaveBirthCert;
    private int stableAdultInHousehold;
    private int violenceIncidenceReport;
    private int childrenEnrolledInSchool;
    private int regularSchoolAttendance;
    private int cgiversEconomicActivity;
    private int emotionalSupportTeamIdentification;
    private int markedForDelete;
    private String recordedBy;
    private String volunteerName;
    private int score;
    
    private int adolescentInVoctraining;
    private int childrenEnrolledInEarlyChildCare;
    private int caregiverDisclosedHivStatus;
    private int childrenInNeeedOfHltProvidedHltServices;
    private int hhDemonstratedAbilityToMeetGoals;
    private int vcAtRiskReferredForChildProtection;
    private int childrenInHhHaveAdequateHousingAndSpace;
    private int cgiversCompletedParentingCourse;
    private int childHeadedHhLinkedToServices;
    private int hhGraduated;
    private int childrenReferredForHivTesting;
    private int childrenReferredReceivedTestingServices;
    private int childWithdrawnOrSad;
    private CommunityWorker communityWorker;
    int serialNo=0;
    String rowColor=AppConstant.FIRSTREPORTROWCOLOUR;
    private HouseholdEnrollment hhe;

    public int getAllChildrenHaveBirthCert() {
        return allChildrenHaveBirthCert;
    }

    public void setAllChildrenHaveBirthCert(int allChildrenHaveBirthCert) {
        this.allChildrenHaveBirthCert = allChildrenHaveBirthCert;
    }

    public int getCgiversEconomicActivity() {
        return cgiversEconomicActivity;
    }

    public void setCgiversEconomicActivity(int cgiversEconomicActivity) {
        this.cgiversEconomicActivity = cgiversEconomicActivity;
    }

    public int getChildrenEnrolledInSchool() {
        return childrenEnrolledInSchool;
    }

    public void setChildrenEnrolledInSchool(int childrenEnrolledInSchool) {
        this.childrenEnrolledInSchool = childrenEnrolledInSchool;
    }

    public int getChildrenHivStatusknown() {
        return childrenHivStatusknown;
    }

    public void setChildrenHivStatusknown(int childrenHivStatusknown) {
        this.childrenHivStatusknown = childrenHivStatusknown;
    }

    public int getChildrenNotUndernourished() {
        return childrenNotUndernourished;
    }

    public void setChildrenNotUndernourished(int childrenNotUndernourished) {
        this.childrenNotUndernourished = childrenNotUndernourished;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(Date dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public int getDocumentedViralLoadResult() {
        return documentedViralLoadResult;
    }

    public void setDocumentedViralLoadResult(int documentedViralLoadResult) {
        this.documentedViralLoadResult = documentedViralLoadResult;
    }

    public int getEmotionalSupportTeamIdentification() {
        return emotionalSupportTeamIdentification;
    }

    public void setEmotionalSupportTeamIdentification(int emotionalSupportTeamIdentification) {
        this.emotionalSupportTeamIdentification = emotionalSupportTeamIdentification;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public int getHivPosAdolscentsLinked() {
        return hivPosAdolscentsLinked;
    }

    public void setHivPosAdolscentsLinked(int hivPosAdolscentsLinked) {
        this.hivPosAdolscentsLinked = hivPosAdolscentsLinked;
    }

    public int getHivPreventionKnowledge() {
        return hivPreventionKnowledge;
    }

    public void setHivPreventionKnowledge(int hivPreventionKnowledge) {
        this.hivPreventionKnowledge = hivPreventionKnowledge;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public int getRegularSchoolAttendance() {
        return regularSchoolAttendance;
    }

    public void setRegularSchoolAttendance(int regularSchoolAttendance) {
        this.regularSchoolAttendance = regularSchoolAttendance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStableAdultInHousehold() {
        return stableAdultInHousehold;
    }

    public void setStableAdultInHousehold(int stableAdultInHousehold) {
        this.stableAdultInHousehold = stableAdultInHousehold;
    }

    public int getViolenceIncidenceReport() {
        return violenceIncidenceReport;
    }

    public void setViolenceIncidenceReport(int violenceIncidenceReport) {
        this.violenceIncidenceReport = violenceIncidenceReport;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getAdolescentInVoctraining() {
        return adolescentInVoctraining;
    }

    public void setAdolescentInVoctraining(int adolescentInVoctraining) {
        this.adolescentInVoctraining = adolescentInVoctraining;
    }

    public int getCaregiverDisclosedHivStatus() {
        return caregiverDisclosedHivStatus;
    }

    public void setCaregiverDisclosedHivStatus(int caregiverDisclosedHivStatus) {
        this.caregiverDisclosedHivStatus = caregiverDisclosedHivStatus;
    }

    public int getCgiversCompletedParentingCourse() {
        return cgiversCompletedParentingCourse;
    }

    public void setCgiversCompletedParentingCourse(int cgiversCompletedParentingCourse) {
        this.cgiversCompletedParentingCourse = cgiversCompletedParentingCourse;
    }

    public int getChildHeadedHhLinkedToServices() {
        return childHeadedHhLinkedToServices;
    }

    public void setChildHeadedHhLinkedToServices(int childHeadedHhLinkedToServices) {
        this.childHeadedHhLinkedToServices = childHeadedHhLinkedToServices;
    }

    public int getChildrenInHhHaveAdequateHousingAndSpace() {
        return childrenInHhHaveAdequateHousingAndSpace;
    }

    public void setChildrenInHhHaveAdequateHousingAndSpace(int childrenInHhHaveAdequateHousingAndSpace) {
        this.childrenInHhHaveAdequateHousingAndSpace = childrenInHhHaveAdequateHousingAndSpace;
    }

    public int getChildrenInNeeedOfHltProvidedHltServices() {
        return childrenInNeeedOfHltProvidedHltServices;
    }

    public void setChildrenInNeeedOfHltProvidedHltServices(int childrenInNeeedOfHltProvidedHltServices) {
        this.childrenInNeeedOfHltProvidedHltServices = childrenInNeeedOfHltProvidedHltServices;
    }

    public int getChildrenEnrolledInEarlyChildCare() {
        return childrenEnrolledInEarlyChildCare;
    }

    public void setChildrenEnrolledInEarlyChildCare(int childrenEnrolledInEarlyChildCare) {
        this.childrenEnrolledInEarlyChildCare = childrenEnrolledInEarlyChildCare;
    }

    public int getHhGraduated() {
        return hhGraduated;
    }

    public void setHhGraduated(int hhGraduated) {
        this.hhGraduated = hhGraduated;
    }
    
    public int getHhDemonstratedAbilityToMeetGoals() {
        return hhDemonstratedAbilityToMeetGoals;
    }

    public void setHhDemonstratedAbilityToMeetGoals(int hhDemonstratedAbilityToMeetGoals) {
        this.hhDemonstratedAbilityToMeetGoals = hhDemonstratedAbilityToMeetGoals;
    }

    public int getVcAtRiskReferredForChildProtection() {
        return vcAtRiskReferredForChildProtection;
    }

    public void setVcAtRiskReferredForChildProtection(int vcAtRiskReferredForChildProtection) {
        this.vcAtRiskReferredForChildProtection = vcAtRiskReferredForChildProtection;
    }

    public int gchildrenReferredForHivTestingetChildrenReferredForHivTesting() {
        return childrenReferredForHivTesting;
    }

    public void setChildrenReferredForHivTesting(int childrenReferredForHivTesting) {
        this.childrenReferredForHivTesting = childrenReferredForHivTesting;
    }

    public int getChildrenReferredReceivedTestingServices() {
        return childrenReferredReceivedTestingServices;
    }

    public void setChildrenReferredReceivedTestingServices(int childrenReferredReceivedTestingServices) {
        this.childrenReferredReceivedTestingServices = childrenReferredReceivedTestingServices;
    }

    public int getChildWithdrawnOrSad() {
        return childWithdrawnOrSad;
    }

    public void setChildWithdrawnOrSad(int childWithdrawnOrSad) {
        this.childWithdrawnOrSad = childWithdrawnOrSad;
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

    public CommunityWorker getCommunityWorker() {
        return communityWorker;
    }

    public HouseholdEnrollment getHhe() {
        return hhe;
    }

    public void setHhe(HouseholdEnrollment hhe) {
        this.hhe = hhe;
    }
}
