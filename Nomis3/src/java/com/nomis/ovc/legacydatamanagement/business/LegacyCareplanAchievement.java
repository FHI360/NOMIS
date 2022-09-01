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
public class LegacyCareplanAchievement implements Serializable
{
    private String hhUniqueId;
    private String stateCode;
    private String lgaCode;
    private String orgCode;
    private String community;
    private String hhSurname;
    private String hhFirstname;
    private String hhGender;
    private String address;
    private String phone;
    private String occupation;
    private String followupRequired;
    private String graduated;
    private int hhAge;
    private String dateOfEnrollment;
    private String dateOfAssessment;
    private String lastModifiedDate;
    private String recordedBy;
    private int id;
    private String clientId;
    private int score;
    private int hhSerialNo;
    private String volunteerId;
    private String designation;
    private String serviceProviderPhone;
    private int assessmentNo;
    
    private String hth_hivknowledge;
    private String hth_vchivrisk;
    private String hth_vcreftested;
    private String hth_hivOnArt;
    private String hth_hivdisclosed;
    private String hth_vcInneedOfHthServices;
    private String stb_hungryNoFood;
    private String stb_resiliency;
    private String sft_vcsad;
    private String stb_cgPartEconServ;
    private String stb_socEmotSupport;
    private String sft_vcreferredForCps;
    private String sft_vcSafeFromAbuse;
    private String sft_birthCert;
    private String sft_cgcompletedTwoModules;
    private String sft_childHeadedLinkedToServices;
    private String sch_schAttendance;
    private String sch_vcEnrolledInSecondarySch;
    private String sch_adolInVoctraining;
    private String sch_earlyChildCare;
     
    private String hth_hivknowledgeComment;
    private String hth_vchivriskComment;
    private String hth_vcreftestedComment;
    private String hth_hivOnArtComment;
    private String hth_hivdisclosedComment;
    private String hth_vcInneedOfHthServicesComment;
    private String stb_hungryNoFoodComment;
    private String stb_resiliencyComment;
    private String sft_vcsadComment;
    private String stb_cgPartEconServComment;
    private String stb_socEmotSupportComment;
    private String sft_vcreferredForCpsComment;
    private String sft_vcSafeFromAbuseComment;
    private String sft_birthCertComment;
    private String sft_cgcompletedTwoModulesComment;
    private String sft_childHeadedLinkedToServicesComment;
    private String sch_schAttendanceComment;
    private String sch_vcEnrolledInSecondarySchComment;
    private String sch_adolInVoctrainingComment;
    private String sch_earlyChildCareComment;
    private String comment;
    private int markedForDelete;
    
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    private LegacyHouseholdEnrollment hhe;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAssessmentNo() {
        return assessmentNo;
    }

    public void setAssessmentNo(int assessmentNo) {
        this.assessmentNo = assessmentNo;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFollowupRequired() {
        return followupRequired;
    }

    public void setFollowupRequired(String followupRequired) {
        this.followupRequired = followupRequired;
    }

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }

    public int getHhAge() {
        return hhAge;
    }

    public void setHhAge(int hhAge) {
        this.hhAge = hhAge;
    }

    public String getHhFirstname() {
        return hhFirstname;
    }

    public void setHhFirstname(String hhFirstname) {
        this.hhFirstname = hhFirstname;
    }

    public String getHhGender() {
        return hhGender;
    }

    public void setHhGender(String hhGender) {
        this.hhGender = hhGender;
    }

    public int getHhSerialNo() {
        return hhSerialNo;
    }

    public void setHhSerialNo(int hhSerialNo) {
        this.hhSerialNo = hhSerialNo;
    }

    public String getHhSurname() {
        return hhSurname;
    }

    public void setHhSurname(String hhSurname) {
        this.hhSurname = hhSurname;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getHth_hivOnArt() {
        return hth_hivOnArt;
    }

    public void setHth_hivOnArt(String hth_hivOnArt) {
        this.hth_hivOnArt = hth_hivOnArt;
    }

    public String getHth_hivdisclosed() {
        return hth_hivdisclosed;
    }

    public void setHth_hivdisclosed(String hth_hivdisclosed) {
        this.hth_hivdisclosed = hth_hivdisclosed;
    }

    public String getHth_hivknowledge() {
        return hth_hivknowledge;
    }

    public void setHth_hivknowledge(String hth_hivknowledge) {
        this.hth_hivknowledge = hth_hivknowledge;
    }

    public String getHth_vcInneedOfHthServices() {
        return hth_vcInneedOfHthServices;
    }

    public void setHth_vcInneedOfHthServices(String hth_vcInneedOfHthServices) {
        this.hth_vcInneedOfHthServices = hth_vcInneedOfHthServices;
    }

    public String getHth_vchivrisk() {
        return hth_vchivrisk;
    }

    public void setHth_vchivrisk(String hth_vchivrisk) {
        this.hth_vchivrisk = hth_vchivrisk;
    }

    public String getHth_vcreftested() {
        return hth_vcreftested;
    }

    public void setHth_vcreftested(String hth_vcreftested) {
        this.hth_vcreftested = hth_vcreftested;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLgaCode() {
        return lgaCode;
    }

    public void setLgaCode(String lgaCode) {
        this.lgaCode = lgaCode;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSch_adolInVoctraining() {
        return sch_adolInVoctraining;
    }

    public void setSch_adolInVoctraining(String sch_adolInVoctraining) {
        this.sch_adolInVoctraining = sch_adolInVoctraining;
    }

    public String getSch_earlyChildCare() {
        return sch_earlyChildCare;
    }

    public void setSch_earlyChildCare(String sch_earlyChildCare) {
        this.sch_earlyChildCare = sch_earlyChildCare;
    }

    public String getSch_schAttendance() {
        return sch_schAttendance;
    }

    public void setSch_schAttendance(String sch_schAttendance) {
        this.sch_schAttendance = sch_schAttendance;
    }

    public String getSch_vcEnrolledInSecondarySch() {
        return sch_vcEnrolledInSecondarySch;
    }

    public void setSch_vcEnrolledInSecondarySch(String sch_vcEnrolledInSecondarySch) {
        this.sch_vcEnrolledInSecondarySch = sch_vcEnrolledInSecondarySch;
    }

    public String getSft_birthCert() {
        return sft_birthCert;
    }

    public void setSft_birthCert(String sft_birthCert) {
        this.sft_birthCert = sft_birthCert;
    }

    public String getSft_cgcompletedTwoModules() {
        return sft_cgcompletedTwoModules;
    }

    public void setSft_cgcompletedTwoModules(String sft_cgcompletedTwoModules) {
        this.sft_cgcompletedTwoModules = sft_cgcompletedTwoModules;
    }

    public String getSft_childHeadedLinkedToServices() {
        return sft_childHeadedLinkedToServices;
    }

    public void setSft_childHeadedLinkedToServices(String sft_childHeadedLinkedToServices) {
        this.sft_childHeadedLinkedToServices = sft_childHeadedLinkedToServices;
    }

    public String getSft_vcSafeFromAbuse() {
        return sft_vcSafeFromAbuse;
    }

    public void setSft_vcSafeFromAbuse(String sft_vcSafeFromAbuse) {
        this.sft_vcSafeFromAbuse = sft_vcSafeFromAbuse;
    }

    public String getSft_vcreferredForCps() {
        return sft_vcreferredForCps;
    }

    public void setSft_vcreferredForCps(String sft_vcreferredForCps) {
        this.sft_vcreferredForCps = sft_vcreferredForCps;
    }

    public String getSft_vcsad() {
        return sft_vcsad;
    }

    public void setSft_vcsad(String sft_vcsad) {
        this.sft_vcsad = sft_vcsad;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStb_cgPartEconServ() {
        return stb_cgPartEconServ;
    }

    public void setStb_cgPartEconServ(String stb_cgPartEconServ) {
        this.stb_cgPartEconServ = stb_cgPartEconServ;
    }

    public String getStb_hungryNoFood() {
        return stb_hungryNoFood;
    }

    public void setStb_hungryNoFood(String stb_hungryNoFood) {
        this.stb_hungryNoFood = stb_hungryNoFood;
    }

    public String getStb_resiliency() {
        return stb_resiliency;
    }

    public void setStb_resiliency(String stb_resiliency) {
        this.stb_resiliency = stb_resiliency;
    }

    public String getStb_socEmotSupport() {
        return stb_socEmotSupport;
    }

    public void setStb_socEmotSupport(String stb_socEmotSupport) {
        this.stb_socEmotSupport = stb_socEmotSupport;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getHth_hivOnArtComment() {
        return hth_hivOnArtComment;
    }

    public void setHth_hivOnArtComment(String hth_hivOnArtComment) {
        this.hth_hivOnArtComment = hth_hivOnArtComment;
    }

    public String getHth_hivdisclosedComment() {
        return hth_hivdisclosedComment;
    }

    public void setHth_hivdisclosedComment(String hth_hivdisclosedComment) {
        this.hth_hivdisclosedComment = hth_hivdisclosedComment;
    }

    public String getHth_hivknowledgeComment() {
        return hth_hivknowledgeComment;
    }

    public void setHth_hivknowledgeComment(String hth_hivknowledgeComment) {
        this.hth_hivknowledgeComment = hth_hivknowledgeComment;
    }

    public String getHth_vcInneedOfHthServicesComment() {
        return hth_vcInneedOfHthServicesComment;
    }

    public void setHth_vcInneedOfHthServicesComment(String hth_vcInneedOfHthServicesComment) {
        this.hth_vcInneedOfHthServicesComment = hth_vcInneedOfHthServicesComment;
    }

    public String getHth_vchivriskComment() {
        return hth_vchivriskComment;
    }

    public void setHth_vchivriskComment(String hth_vchivriskComment) {
        this.hth_vchivriskComment = hth_vchivriskComment;
    }

    public String getHth_vcreftestedComment() {
        return hth_vcreftestedComment;
    }

    public void setHth_vcreftestedComment(String hth_vcreftestedComment) {
        this.hth_vcreftestedComment = hth_vcreftestedComment;
    }

    public String getSch_adolInVoctrainingComment() {
        return sch_adolInVoctrainingComment;
    }

    public void setSch_adolInVoctrainingComment(String sch_adolInVoctrainingComment) {
        this.sch_adolInVoctrainingComment = sch_adolInVoctrainingComment;
    }

    public String getSch_earlyChildCareComment() {
        return sch_earlyChildCareComment;
    }

    public void setSch_earlyChildCareComment(String sch_earlyChildCareComment) {
        this.sch_earlyChildCareComment = sch_earlyChildCareComment;
    }

    public String getSch_schAttendanceComment() {
        return sch_schAttendanceComment;
    }

    public void setSch_schAttendanceComment(String sch_schAttendanceComment) {
        this.sch_schAttendanceComment = sch_schAttendanceComment;
    }

    public String getSch_vcEnrolledInSecondarySchComment() {
        return sch_vcEnrolledInSecondarySchComment;
    }

    public void setSch_vcEnrolledInSecondarySchComment(String sch_vcEnrolledInSecondarySchComment) {
        this.sch_vcEnrolledInSecondarySchComment = sch_vcEnrolledInSecondarySchComment;
    }

    public String getSft_birthCertComment() {
        return sft_birthCertComment;
    }

    public void setSft_birthCertComment(String sft_birthCertComment) {
        this.sft_birthCertComment = sft_birthCertComment;
    }

    public String getSft_cgcompletedTwoModulesComment() {
        return sft_cgcompletedTwoModulesComment;
    }

    public void setSft_cgcompletedTwoModulesComment(String sft_cgcompletedTwoModulesComment) {
        this.sft_cgcompletedTwoModulesComment = sft_cgcompletedTwoModulesComment;
    }

    public String getSft_childHeadedLinkedToServicesComment() {
        return sft_childHeadedLinkedToServicesComment;
    }

    public void setSft_childHeadedLinkedToServicesComment(String sft_childHeadedLinkedToServicesComment) {
        this.sft_childHeadedLinkedToServicesComment = sft_childHeadedLinkedToServicesComment;
    }

    public String getSft_vcSafeFromAbuseComment() {
        return sft_vcSafeFromAbuseComment;
    }

    public void setSft_vcSafeFromAbuseComment(String sft_vcSafeFromAbuseComment) {
        this.sft_vcSafeFromAbuseComment = sft_vcSafeFromAbuseComment;
    }

    public String getSft_vcreferredForCpsComment() {
        return sft_vcreferredForCpsComment;
    }

    public void setSft_vcreferredForCpsComment(String sft_vcreferredForCpsComment) {
        this.sft_vcreferredForCpsComment = sft_vcreferredForCpsComment;
    }

    public String getSft_vcsadComment() {
        return sft_vcsadComment;
    }

    public void setSft_vcsadComment(String sft_vcsadComment) {
        this.sft_vcsadComment = sft_vcsadComment;
    }

    public String getStb_cgPartEconServComment() {
        return stb_cgPartEconServComment;
    }

    public void setStb_cgPartEconServComment(String stb_cgPartEconServComment) {
        this.stb_cgPartEconServComment = stb_cgPartEconServComment;
    }

    public String getStb_hungryNoFoodComment() {
        return stb_hungryNoFoodComment;
    }

    public void setStb_hungryNoFoodComment(String stb_hungryNoFoodComment) {
        this.stb_hungryNoFoodComment = stb_hungryNoFoodComment;
    }

    public String getStb_resiliencyComment() {
        return stb_resiliencyComment;
    }

    public void setStb_resiliencyComment(String stb_resiliencyComment) {
        this.stb_resiliencyComment = stb_resiliencyComment;
    }

    public String getStb_socEmotSupportComment() {
        return stb_socEmotSupportComment;
    }

    public void setStb_socEmotSupportComment(String stb_socEmotSupportComment) {
        this.stb_socEmotSupportComment = stb_socEmotSupportComment;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public LegacyHouseholdEnrollment getHhe() 
    {
        //if(hhe==null)
        //hhe=beneficiary.getHouseholdHead(clientId);
        return hhe;
    }

    public void setHhe(LegacyHouseholdEnrollment hhe) {
        
        this.hhe = hhe;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getServiceProviderPhone() {
        return serviceProviderPhone;
    }

    public void setServiceProviderPhone(String serviceProviderPhone) {
        this.serviceProviderPhone = serviceProviderPhone;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
        
}
