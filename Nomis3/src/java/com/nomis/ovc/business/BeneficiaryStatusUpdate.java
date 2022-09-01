/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryType;
import com.nomis.operationsManagement.PointOfUpdate;
import com.nomis.operationsManagement.PointOfUpdateManager;
import com.nomis.ovc.util.EnrolledOnTreatment;
import com.nomis.ovc.util.HivStatus;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class BeneficiaryStatusUpdate implements Serializable
{
    private String beneficiaryId;
    private String hhUniqueId;
    private String firstName;
    private String surname;
    private String fullName=firstName+" "+surname;
    private String sex;
    private Date dateOfLastHivStatus;
    private int lastHivStatus;
    private String facilityId;
    private String childTreatmentId;
    private String caregiverTreatmentId;
    private int enrolledOnTreatment;
    private int newHivStatus;
    private Date dateOfNewStatus;
    private Date dateEnrolledOnTreatment;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    private int beneficiaryType;
    private int markedForDelete;
    
    private int birthCertificate;
    private int schoolStatus;
    private String schoolName;
    private String grade;
    private String caregiverId;
    private String caregiverSex;
    private int caregiverAge;
    private String caregiverPhone;
    private int caregiverLastHivStatus;
    private Date dateOfCaregiverLastHivStatus;
    
    private int caregiverHivStatus;
    private Date dateOfCaregiverHivStatus;
    private int caregiverEnrolledOnTreatment;
    private Date dateCaregiverEnrolledOnTreatment;
    private String facilityCaregiverEnrolled;
        
    private int enrolledInVocationalTraining;
    private String nameOfVocationalTraining;
    private String volunteerName;
    private int pointOfUpdateValue=4;
    private int updateChildBirthRegStatus;
    private int updateChildHivStatus;
    private int updateCaregiverHivStatus;
    
    private int childExitedFromProgram;
    private int childExitStatus;
    private int caregiverExitedFromProgram;
    private int caregiverExitStatus;
    private Date dateChildExitedFromProgram;
    private Date dateCaregiverExitedFromProgram;
    private int updateSchoolStatus;
    
    int serialNo=0;
    String rowColor="#FFFFFF";
    private HivStatus baselineHivStatus;
    private HivStatus hivStatus;
    private BeneficiaryType beneficiaryTypeObject;
    private PointOfUpdate pointOfUpdate;
    private EnrolledOnTreatment enrolledOnTreatmentObj;
    
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfNewStatus() {
        return dateOfNewStatus;
    }

    public void setDateOfNewStatus(Date dateOfNewStatus) {
        this.dateOfNewStatus = dateOfNewStatus;
    }

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public int getLastHivStatus() {
        return lastHivStatus;
    }

    public void setLastHivStatus(int lastHivStatus) {
        this.lastHivStatus = lastHivStatus;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getNewHivStatus() {
        return newHivStatus;
    }

    public void setNewHivStatus(int newHivStatus) {
        this.newHivStatus = newHivStatus;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public int getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(int beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public BeneficiaryType getBeneficiaryTypeObject() {
        return beneficiaryTypeObject;
    }

    public void setBeneficiaryTypeObject(BeneficiaryType beneficiaryTypeObject) {
        this.beneficiaryTypeObject = beneficiaryTypeObject;
    }

    public Date getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(Date dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public Date getDateOfLastHivStatus() {
        return dateOfLastHivStatus;
    }

    public void setDateOfLastHivStatus(Date dateOfLastHivStatus) {
        this.dateOfLastHivStatus = dateOfLastHivStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PointOfUpdate getPointOfUpdate() 
    {
        pointOfUpdate=PointOfUpdateManager.getPointOfUpdate(getPointOfUpdateValue());
        return pointOfUpdate;
    }

    public void setPointOfUpdate(PointOfUpdate pointOfUpdate) {
        this.pointOfUpdate = pointOfUpdate;
    }

    public int getPointOfUpdateValue() {
        return pointOfUpdateValue;
    }

    public void setPointOfUpdateValue(int pointOfUpdateValue) {
        this.pointOfUpdateValue = pointOfUpdateValue;
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

    public HivStatus getBaselineHivStatus() {
        return baselineHivStatus;
    }

    public void setBaselineHivStatus(HivStatus baselineHivStatus) {
        this.baselineHivStatus = baselineHivStatus;
    }
    
    public HivStatus getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(HivStatus hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public EnrolledOnTreatment getEnrolledOnTreatmentObj() {
        return enrolledOnTreatmentObj;
    }

    public void setEnrolledOnTreatmentObj(EnrolledOnTreatment enrolledOnTreatmentObj) {
        this.enrolledOnTreatmentObj = enrolledOnTreatmentObj;
    }

    public int getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(int birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public int getCaregiverAge() {
        return caregiverAge;
    }

    public void setCaregiverAge(int caregiverAge) {
        this.caregiverAge = caregiverAge;
    }

    public int getCaregiverEnrolledOnTreatment() {
        return caregiverEnrolledOnTreatment;
    }

    public void setCaregiverEnrolledOnTreatment(int caregiverEnrolledOnTreatment) {
        this.caregiverEnrolledOnTreatment = caregiverEnrolledOnTreatment;
    }

    public int getCaregiverHivStatus() {
        return caregiverHivStatus;
    }

    public void setCaregiverHivStatus(int caregiverHivStatus) {
        this.caregiverHivStatus = caregiverHivStatus;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public int getCaregiverLastHivStatus() {
        return caregiverLastHivStatus;
    }

    public void setCaregiverLastHivStatus(int caregiverLastHivStatus) {
        this.caregiverLastHivStatus = caregiverLastHivStatus;
    }

    public String getCaregiverPhone() {
        return caregiverPhone;
    }

    public void setCaregiverPhone(String caregiverPhone) {
        this.caregiverPhone = caregiverPhone;
    }

    public String getCaregiverSex() {
        return caregiverSex;
    }

    public void setCaregiverSex(String caregiverSex) {
        this.caregiverSex = caregiverSex;
    }

    public Date getDateCaregiverEnrolledOnTreatment() {
        return dateCaregiverEnrolledOnTreatment;
    }

    public void setDateCaregiverEnrolledOnTreatment(Date dateCaregiverEnrolledOnTreatment) {
        this.dateCaregiverEnrolledOnTreatment = dateCaregiverEnrolledOnTreatment;
    }

    public Date getDateOfCaregiverHivStatus() {
        return dateOfCaregiverHivStatus;
    }

    public void setDateOfCaregiverHivStatus(Date dateOfCaregiverHivStatus) {
        this.dateOfCaregiverHivStatus = dateOfCaregiverHivStatus;
    }
    
    public Date getDateOfCaregiverLastHivStatus() {
        return dateOfCaregiverLastHivStatus;
    }

    public void setDateOfCaregiverLastHivStatus(Date dateOfCaregiverLastHivStatus) {
        this.dateOfCaregiverLastHivStatus = dateOfCaregiverLastHivStatus;
    }
    
    public String getFacilityCaregiverEnrolled() {
        return facilityCaregiverEnrolled;
    }

    public void setFacilityCaregiverEnrolled(String facilityCaregiverEnrolled) {
        this.facilityCaregiverEnrolled = facilityCaregiverEnrolled;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolStatus() {
        return schoolStatus;
    }

    public void setSchoolStatus(int schoolStatus) {
        this.schoolStatus = schoolStatus;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getEnrolledInVocationalTraining() {
        return enrolledInVocationalTraining;
    }

    public void setEnrolledInVocationalTraining(int enrolledInVocationalTraining) {
        this.enrolledInVocationalTraining = enrolledInVocationalTraining;
    }

    public String getNameOfVocationalTraining() {
        return nameOfVocationalTraining;
    }

    public void setNameOfVocationalTraining(String nameOfVocationalTraining) {
        this.nameOfVocationalTraining = nameOfVocationalTraining;
    }

    public String getCaregiverTreatmentId() {
        return caregiverTreatmentId;
    }

    public void setCaregiverTreatmentId(String caregiverTreatmentId) {
        this.caregiverTreatmentId = caregiverTreatmentId;
    }

    public String getChildTreatmentId() {
        return childTreatmentId;
    }

    public void setChildTreatmentId(String childTreatmentId) {
        this.childTreatmentId = childTreatmentId;
    }

    public int getUpdateCaregiverHivStatus() {
        return updateCaregiverHivStatus;
    }

    public void setUpdateCaregiverHivStatus(int updateCaregiverHivStatus) {
        this.updateCaregiverHivStatus = updateCaregiverHivStatus;
    }

    public int getUpdateChildBirthRegStatus() {
        return updateChildBirthRegStatus;
    }

    public void setUpdateChildBirthRegStatus(int updateChildBirthRegStatus) {
        this.updateChildBirthRegStatus = updateChildBirthRegStatus;
    }

    public int getUpdateChildHivStatus() {
        return updateChildHivStatus;
    }

    public void setUpdateChildHivStatus(int updateChildHivStatus) {
        this.updateChildHivStatus = updateChildHivStatus;
    }

    public int getCaregiverExitStatus() {
        return caregiverExitStatus;
    }

    public void setCaregiverExitStatus(int caregiverExitStatus) {
        this.caregiverExitStatus = caregiverExitStatus;
    }

    public int getCaregiverExitedFromProgram() {
        return caregiverExitedFromProgram;
    }

    public void setCaregiverExitedFromProgram(int caregiverExitedFromProgram) {
        this.caregiverExitedFromProgram = caregiverExitedFromProgram;
    }

    public int getChildExitStatus() {
        return childExitStatus;
    }

    public void setChildExitStatus(int childExitStatus) {
        this.childExitStatus = childExitStatus;
    }

    public int getChildExitedFromProgram() {
        return childExitedFromProgram;
    }

    public void setChildExitedFromProgram(int childExitedFromProgram) {
        this.childExitedFromProgram = childExitedFromProgram;
    }

    public Date getDateCaregiverExitedFromProgram() {
        return dateCaregiverExitedFromProgram;
    }

    public void setDateCaregiverExitedFromProgram(Date dateCaregiverExitedFromProgram) {
        this.dateCaregiverExitedFromProgram = dateCaregiverExitedFromProgram;
    }

    public Date getDateChildExitedFromProgram() {
        return dateChildExitedFromProgram;
    }

    public void setDateChildExitedFromProgram(Date dateChildExitedFromProgram) {
        this.dateChildExitedFromProgram = dateChildExitedFromProgram;
    }

    public int getUpdateSchoolStatus() {
        return updateSchoolStatus;
    }

    public void setUpdateSchoolStatus(int updateSchoolStatus) {
        this.updateSchoolStatus = updateSchoolStatus;
    }

}
