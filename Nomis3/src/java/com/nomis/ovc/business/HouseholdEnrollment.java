/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.ovc.util.EnrollmentStatus;
import com.nomis.operationsManagement.EnrollmentStatusObjectManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.SingleOptionManager;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class HouseholdEnrollment implements Serializable
{
    private String hhUniqueId;
    private String enrollmentId;
    private String legacyId;
    private String partnerCode;
    private String cboId;
    private String organizationUnit;
    private String address;
    
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
    private int numberOfChildrenInHousehold;
    private int numberOfPeopleInHousehold;
    //private Date dateOfEnrollment;
    
    private int markedForDelete;
    int serialNo=0;
    String rowColor="#FFFFFF";
    
    //private int pointOfUpdate=1;
    private int currentEnrollmentStatus;
    private Date dateOfCurrentStatus;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    
    
    private int hhHasCasePlan;
    private Date dateCasePlanDeveloped;
    private String casePlanAnswer;
    
    private OrganizationUnit level2Ou;
    private OrganizationUnit level3Ou;
    private OrganizationUnit level4Ou;
    private CommunityBasedOrganization cbo;
    private RevisedHouseholdVulnerabilityAssessment rhva;
    
    OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();
    private AdultHouseholdMember prCaregiver;
    private CommunityWorker communityWorker=null;
    private EnrollmentStatus currentEnrollmentStatusObj;
    
    private SingleOptionManager som=new SingleOptionManager();
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }
    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public CommunityBasedOrganization getCbo() 
    {
        cbo=ouam.getCBOForReports(getCboId());
        return cbo;
    }

    public OrganizationUnit getLevel2Ou() 
    {
        level2Ou=ouam.getLevel2OrganizationUnitFromOuPart(getLevel4Ou().getOuPath());
        return level2Ou;
    }

    public void setLevel2Ou(OrganizationUnit level2Ou) {
        this.level2Ou = level2Ou;
    }

    public OrganizationUnit getLevel3Ou() 
    {
        level3Ou=ouam.getLevel3OrganizationUnitFromOuPart(getLevel4Ou().getOuPath());
        return level3Ou;
    }

    public void setLevel3Ou(OrganizationUnit level3Ou) {
        this.level3Ou = level3Ou;
    }

    public OrganizationUnit getLevel4Ou() 
    {
        level4Ou=ouam.getOrganizationUnitForReports(getOrganizationUnit());
        return level4Ou;
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

    public OrganizationUnitAttributesManager getOuam() {
        return ouam;
    }

    public void setOuam(OrganizationUnitAttributesManager ouam) {
        this.ouam = ouam;
    }
    
    public int getCurrentEnrollmentStatus() {
        return currentEnrollmentStatus;
    }

    public void setCurrentEnrollmentStatus(int currentEnrollmentStatus) {
        this.currentEnrollmentStatus = currentEnrollmentStatus;
    }

    public Date getDateOfCurrentStatus() {
        return dateOfCurrentStatus;
    }

    public void setDateOfCurrentStatus(Date dateOfCurrentStatus) {
        this.dateOfCurrentStatus = dateOfCurrentStatus;
    }

    public AdultHouseholdMember getPrCaregiver() 
    {
        return prCaregiver;
    }

    public void setPrCaregiver(AdultHouseholdMember prCaregiver) {
        this.prCaregiver = prCaregiver;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
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

    /*public ReferralFacility getNearestFacilityObject() 
    {
        nearestFacilityObject=BeneficiaryManager.getReferralFacility(nearestFacility);
        return nearestFacilityObject;
    }
    public School getNearestSchoolObject() 
    {
        nearestSchoolObject=BeneficiaryManager.getSchool(nearestSchool);
        return nearestSchoolObject;
    }*/

    public Date getDateCasePlanDeveloped() {
        return dateCasePlanDeveloped;
    }

    public void setDateCasePlanDeveloped(Date dateCasePlanDeveloped) {
        this.dateCasePlanDeveloped = dateCasePlanDeveloped;
    }

    public int getHhHasCasePlan() {
        return hhHasCasePlan;
    }

    public void setHhHasCasePlan(int hhHasCasePlan) {
        this.hhHasCasePlan = hhHasCasePlan;
    }

    public String getCasePlanAnswer() 
    {
        casePlanAnswer=som.getSingleChoiceOption(hhHasCasePlan).getName();
        return casePlanAnswer;
    }

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

    public RevisedHouseholdVulnerabilityAssessment getRhva() {
        return rhva;
    }

    public void setRhva(RevisedHouseholdVulnerabilityAssessment rhva) {
        this.rhva = rhva;
    }
    public CommunityWorker getCommunityWorker() {
        return communityWorker=BeneficiaryManager.getCommunityWorker(volunteerName);
    }

    public int getNumberOfChildrenInHousehold() {
        return numberOfChildrenInHousehold;
    }

    public void setNumberOfChildrenInHousehold(int numberOfChildrenInHousehold) {
        this.numberOfChildrenInHousehold = numberOfChildrenInHousehold;
    }

    public int getNumberOfPeopleInHousehold() {
        return numberOfPeopleInHousehold;
    }

    public void setNumberOfPeopleInHousehold(int numberOfPeopleInHousehold) {
        this.numberOfPeopleInHousehold = numberOfPeopleInHousehold;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }
    public boolean isCurrentlyEnrolled()
    {
        return BeneficiaryManager.isCurrentlyEnrolled(getCurrentEnrollmentStatus());
    }
    public EnrollmentStatus getCurrentEnrollmentStatusObj() 
    {
        currentEnrollmentStatusObj=EnrollmentStatusObjectManager.getEnrollmentStatus(getCurrentEnrollmentStatus());
        return currentEnrollmentStatusObj;
    }
}
