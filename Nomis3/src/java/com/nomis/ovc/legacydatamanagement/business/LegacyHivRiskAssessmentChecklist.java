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
public class LegacyHivRiskAssessmentChecklist implements Serializable
{
    //Beneficiary beneficiary=new BeneficiaryResourceManager();
    private LegacyOvc ovc;
    private int recordId;
    private String hhUniqueId;
    private String ovcId;
    private String surname;
    private String firstname;
    private String gender;
    private String address;
    private String phone;
    private String occupation;
    private String dateOfAssessment;
    private String lastModifiedDate;
    private int hhSerialNo;    
    private String childAgeQuestion;
    private String childAgeComment;
    private String childTestedQuestion;
    private String childTestedComment;
    private String hivParentQuestion;
    private String hivParentComment;
    private String childSickQuestion;
    private String childSickComment;
    private String skinProblemQuestion;
    private String skinProblemComment;
    private String parentDeceasedQuestion;
    private String parentDeceasedComment;
    private String chronicallyIllQuestion;
    private String chronicallyIllComment;
    private String schoolGradeQuestion;
    private String schoolGradeComment;
    private String sexualAbuseQuestion;
    private String sexualAbuseComment;
    private String sexuallyActiveQuestion;
    private String sexuallyActiveComment;
    private String genitalDischargeQuestion;
    private String genitalDischargeComment;
    private String childAtRiskQuestion;
    private String bloodTransfusionQuestion;
    private String bloodTransfusionComment;
    private String muacbmiQuestion;
    private String muacbmiComment;
    private String hivStatusQuestion;
    private String hivStatus;
    private String serviceProviderName;
    private String designation;
    private String serviceProviderPhone;
    private int markedForDelete;
    
    private String parentKnowHivStatusQuestion;
    private String parentKnowHivStatusComment;
    private String tbOrNutritionQuestion;
    private String tbOrNutritionComment;
    private String childHeadedHhQuestion;
    private String childHeadedHhComment;
    private String teenMotherQuestion;
    private String teenMotherComment;
    private String diverseSexQuestion;
    private String diverseSexComment;
    private String outOfSchoolQuestion;
    private String outOfSchoolComment;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChildAgeComment() {
        return childAgeComment;
    }

    public void setChildAgeComment(String childAgeComment) {
        this.childAgeComment = childAgeComment;
    }

    public String getChildAgeQuestion() {
        return childAgeQuestion;
    }

    public void setChildAgeQuestion(String childAgeQuestion) {
        this.childAgeQuestion = childAgeQuestion;
    }

    public String getChildAtRiskQuestion() {
        return childAtRiskQuestion;
    }

    public void setChildAtRiskQuestion(String childAtRiskQuestion) {
        this.childAtRiskQuestion = childAtRiskQuestion;
    }

    public String getChildSickComment() {
        return childSickComment;
    }

    public void setChildSickComment(String childSickComment) {
        this.childSickComment = childSickComment;
    }

    public String getChildSickQuestion() {
        return childSickQuestion;
    }

    public void setChildSickQuestion(String childSickQuestion) {
        this.childSickQuestion = childSickQuestion;
    }

    public String getChildTestedComment() {
        return childTestedComment;
    }

    public void setChildTestedComment(String childTestedComment) {
        this.childTestedComment = childTestedComment;
    }

    public String getChildTestedQuestion() {
        return childTestedQuestion;
    }

    public void setChildTestedQuestion(String childTestedQuestion) {
        this.childTestedQuestion = childTestedQuestion;
    }

    public String getChronicallyIllComment() {
        return chronicallyIllComment;
    }

    public void setChronicallyIllComment(String chronicallyIllComment) {
        this.chronicallyIllComment = chronicallyIllComment;
    }

    public String getChronicallyIllQuestion() {
        return chronicallyIllQuestion;
    }

    public void setChronicallyIllQuestion(String chronicallyIllQuestion) {
        this.chronicallyIllQuestion = chronicallyIllQuestion;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = getOvc().getFirstName();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = getOvc().getGender();
    }

    public String getGenitalDischargeComment() {
        return genitalDischargeComment;
    }

    public void setGenitalDischargeComment(String genitalDischargeComment) {
        this.genitalDischargeComment = genitalDischargeComment;
    }

    public String getGenitalDischargeQuestion() {
        return genitalDischargeQuestion;
    }

    public void setGenitalDischargeQuestion(String genitalDischargeQuestion) {
        this.genitalDischargeQuestion = genitalDischargeQuestion;
    }

    public int getHhSerialNo() {
        return hhSerialNo;
    }

    public void setHhSerialNo(int hhSerialNo) {
        this.hhSerialNo = hhSerialNo;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = getOvc().getHhUniqueId();
    }

    public String getHivParentComment() {
        return hivParentComment;
    }

    public void setHivParentComment(String hivParentComment) {
        this.hivParentComment = hivParentComment;
    }

    public String getHivParentQuestion() {
        return hivParentQuestion;
    }

    public void setHivParentQuestion(String hivParentQuestion) {
        this.hivParentQuestion = hivParentQuestion;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
    }

    public String getParentDeceasedComment() {
        return parentDeceasedComment;
    }

    public void setParentDeceasedComment(String parentDeceasedComment) {
        this.parentDeceasedComment = parentDeceasedComment;
    }

    public String getParentDeceasedQuestion() {
        return parentDeceasedQuestion;
    }

    public void setParentDeceasedQuestion(String parentDeceasedQuestion) {
        this.parentDeceasedQuestion = parentDeceasedQuestion;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = getOvc().getPhone();
    }

    public String getSchoolGradeComment() {
        return schoolGradeComment;
    }

    public void setSchoolGradeComment(String schoolGradeComment) {
        this.schoolGradeComment = schoolGradeComment;
    }

    public String getSchoolGradeQuestion() {
        return schoolGradeQuestion;
    }

    public void setSchoolGradeQuestion(String schoolGradeQuestion) {
        this.schoolGradeQuestion = schoolGradeQuestion;
    }

    public String getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(String hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getHivStatusQuestion() {
        return hivStatusQuestion;
    }

    public void setHivStatusQuestion(String hivStatusQuestion) {
        this.hivStatusQuestion = hivStatusQuestion;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    public String getServiceProviderPhone() {
        return serviceProviderPhone;
    }

    public void setServiceProviderPhone(String serviceProviderPhone) {
        this.serviceProviderPhone = serviceProviderPhone;
    }

    public String getSexualAbuseComment() {
        return sexualAbuseComment;
    }

    public void setSexualAbuseComment(String sexualAbuseComment) {
        this.sexualAbuseComment = sexualAbuseComment;
    }

    public String getSexualAbuseQuestion() {
        return sexualAbuseQuestion;
    }

    public void setSexualAbuseQuestion(String sexualAbuseQuestion) {
        this.sexualAbuseQuestion = sexualAbuseQuestion;
    }

    public String getSexuallyActiveComment() {
        return sexuallyActiveComment;
    }

    public void setSexuallyActiveComment(String sexuallyActiveComment) {
        this.sexuallyActiveComment = sexuallyActiveComment;
    }

    public String getSexuallyActiveQuestion() {
        return sexuallyActiveQuestion;
    }

    public void setSexuallyActiveQuestion(String sexuallyActiveQuestion) {
        this.sexuallyActiveQuestion = sexuallyActiveQuestion;
    }

    public String getSkinProblemComment() {
        return skinProblemComment;
    }

    public void setSkinProblemComment(String skinProblemComment) {
        this.skinProblemComment = skinProblemComment;
    }

    public String getSkinProblemQuestion() {
        return skinProblemQuestion;
    }

    public void setSkinProblemQuestion(String skinProblemQuestion) {
        this.skinProblemQuestion = skinProblemQuestion;
    }

    public String getBloodTransfusionQuestion() {
        return bloodTransfusionQuestion;
    }

    public void setBloodTransfusionQuestion(String bloodTransfusionQuestion) {
        this.bloodTransfusionQuestion = bloodTransfusionQuestion;
    }

    public String getBloodTransfusionComment() {
        return bloodTransfusionComment;
    }

    public void setBloodTransfusionComment(String bloodTransfusionComment) {
        this.bloodTransfusionComment = bloodTransfusionComment;
    }

    public String getMuacbmiQuestion() {
        return muacbmiQuestion;
    }

    public void setMuacbmiQuestion(String muacbmiQuestion) {
        this.muacbmiQuestion = muacbmiQuestion;
    }

    public String getMuacbmiComment() {
        return muacbmiComment;
    }

    public void setMuacbmiComment(String muacbmiComment) {
        this.muacbmiComment = muacbmiComment;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) 
    {
        this.surname = getOvc().getLastName();
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public LegacyOvc getOvc() 
    {
        //ovc=BeneficiaryResourceManager.getOvc(ovcId);
        if(ovc ==null)
        ovc=new LegacyOvc();
        return ovc;
    }

    public void setOvc(LegacyOvc ovc) {
        this.ovc = ovc;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public String getChildHeadedHhComment() {
        return childHeadedHhComment;
    }

    public void setChildHeadedHhComment(String childHeadedHhComment) {
        this.childHeadedHhComment = childHeadedHhComment;
    }

    public String getChildHeadedHhQuestion() {
        return childHeadedHhQuestion;
    }

    public void setChildHeadedHhQuestion(String childHeadedHhQuestion) {
        this.childHeadedHhQuestion = childHeadedHhQuestion;
    }

    public String getDiverseSexComment() {
        return diverseSexComment;
    }

    public void setDiverseSexComment(String diverseSexComment) {
        this.diverseSexComment = diverseSexComment;
    }

    public String getDiverseSexQuestion() {
        return diverseSexQuestion;
    }

    public void setDiverseSexQuestion(String diverseSexQuestion) {
        this.diverseSexQuestion = diverseSexQuestion;
    }

    public String getOutOfSchoolComment() {
        return outOfSchoolComment;
    }

    public void setOutOfSchoolComment(String outOfSchoolComment) {
        this.outOfSchoolComment = outOfSchoolComment;
    }

    public String getOutOfSchoolQuestion() {
        return outOfSchoolQuestion;
    }

    public void setOutOfSchoolQuestion(String outOfSchoolQuestion) {
        this.outOfSchoolQuestion = outOfSchoolQuestion;
    }

    public String getParentKnowHivStatusComment() {
        return parentKnowHivStatusComment;
    }

    public void setParentKnowHivStatusComment(String parentKnowHivStatusComment) {
        this.parentKnowHivStatusComment = parentKnowHivStatusComment;
    }

    public String getParentKnowHivStatusQuestion() {
        return parentKnowHivStatusQuestion;
    }

    public void setParentKnowHivStatusQuestion(String parentKnowHivStatusQuestion) {
        this.parentKnowHivStatusQuestion = parentKnowHivStatusQuestion;
    }

    public String getTbOrNutritionComment() {
        return tbOrNutritionComment;
    }

    public void setTbOrNutritionComment(String tbOrNutritionComment) {
        this.tbOrNutritionComment = tbOrNutritionComment;
    }

    public String getTbOrNutritionQuestion() {
        return tbOrNutritionQuestion;
    }

    public void setTbOrNutritionQuestion(String tbOrNutritionQuestion) {
        this.tbOrNutritionQuestion = tbOrNutritionQuestion;
    }

    public String getTeenMotherComment() {
        return teenMotherComment;
    }

    public void setTeenMotherComment(String teenMotherComment) {
        this.teenMotherComment = teenMotherComment;
    }

    public String getTeenMotherQuestion() {
        return teenMotherQuestion;
    }

    public void setTeenMotherQuestion(String teenMotherQuestion) {
        this.teenMotherQuestion = teenMotherQuestion;
    }
    
}
