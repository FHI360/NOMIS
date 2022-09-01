/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;


import com.nomis.operationsManagement.BeneficiaryType;
import com.nomis.operationsManagement.PointOfUpdate;
import com.nomis.operationsManagement.PointOfUpdateManager;
import com.nomis.ovc.util.BeneficiaryAttributeManager;
import com.nomis.ovc.util.EnrolledOnTreatment;
import com.nomis.ovc.util.HivStatus;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class HivStatusManager implements Serializable
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
    private int enrolledOnTreatment;
    private int newHivStatus;
    private Date dateOfNewStatus;
    private Date dateEnrolledOnTreatment;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    private int beneficiaryType;
    
    private int pointOfUpdateValue=4;
    private int markedForDelete;
    int serialNo=0;
    String rowColor="#FFFFFF";
    private HivStatus baselineHivStatus;
    private HivStatus hivStatus;
    private BeneficiaryType beneficiaryTypeObject;
    private PointOfUpdate pointOfUpdate;
    private EnrolledOnTreatment enrolledOnTreatmentObj;
    //private ReferralFacility hivTreatmentFacility;
    //private BeneficiaryAttributeManager bcam=new BeneficiaryAttributeManager();

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

    /*public ReferralFacility getHivTreatmentFacility() {
        hivTreatmentFacility=bcam.getReferralFacility(facilityId);
        return hivTreatmentFacility;
    }*/
}
