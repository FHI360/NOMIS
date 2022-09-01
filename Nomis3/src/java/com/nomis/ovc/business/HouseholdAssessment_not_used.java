/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class HouseholdAssessment_not_used implements Serializable
{
    private String hhUniqueId;
    private Date dateOfAssessment;
    private int hivStatusKnown;
    private int hivPositiveLinked;
    private int hasViralLoadResult;
    private int hivPreventionKnowledge;
    private int childUndernourished;
    private int childrenHasBirthCertificate;
    private int stableAdult;
    private int violenceExperienceReported;
    private int childrenEnrolledInSchool;
    private int regularSchoolAttendance;
    private int cgEngagedInEconomicActivities;
    private int socialEmotionalSupport;
    private String volunteerName;
    private int markedForDelete;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;

    public int getCgEngagedInEconomicActivities() {
        return cgEngagedInEconomicActivities;
    }

    public void setCgEngagedInEconomicActivities(int cgEngagedInEconomicActivities) {
        this.cgEngagedInEconomicActivities = cgEngagedInEconomicActivities;
    }

    public int getChildUndernourished() {
        return childUndernourished;
    }

    public void setChildUndernourished(int childUndernourished) {
        this.childUndernourished = childUndernourished;
    }

    public int getChildrenEnrolledInSchool() {
        return childrenEnrolledInSchool;
    }

    public void setChildrenEnrolledInSchool(int childrenEnrolledInSchool) {
        this.childrenEnrolledInSchool = childrenEnrolledInSchool;
    }

    public int getChildrenHasBirthCertificate() {
        return childrenHasBirthCertificate;
    }

    public void setChildrenHasBirthCertificate(int childrenHasBirthCertificate) {
        this.childrenHasBirthCertificate = childrenHasBirthCertificate;
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

    public int getHasViralLoadResult() {
        return hasViralLoadResult;
    }

    public void setHasViralLoadResult(int hasViralLoadResult) {
        this.hasViralLoadResult = hasViralLoadResult;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public int getHivPositiveLinked() {
        return hivPositiveLinked;
    }

    public void setHivPositiveLinked(int hivPositiveLinked) {
        this.hivPositiveLinked = hivPositiveLinked;
    }

    public int getHivPreventionKnowledge() {
        return hivPreventionKnowledge;
    }

    public void setHivPreventionKnowledge(int hivPreventionKnowledge) {
        this.hivPreventionKnowledge = hivPreventionKnowledge;
    }

    public int getHivStatusKnown() {
        return hivStatusKnown;
    }

    public void setHivStatusKnown(int hivStatusKnown) {
        this.hivStatusKnown = hivStatusKnown;
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

    public int getSocialEmotionalSupport() {
        return socialEmotionalSupport;
    }

    public void setSocialEmotionalSupport(int socialEmotionalSupport) {
        this.socialEmotionalSupport = socialEmotionalSupport;
    }

    public int getStableAdult() {
        return stableAdult;
    }

    public void setStableAdult(int stableAdult) {
        this.stableAdult = stableAdult;
    }

    public int getViolenceExperienceReported() {
        return violenceExperienceReported;
    }

    public void setViolenceExperienceReported(int violenceExperienceReported) {
        this.violenceExperienceReported = violenceExperienceReported;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }
    
}
