/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.operationsManagement.BeneficiaryType;
import com.nomis.operationsManagement.BeneficiaryTypeManager;
import com.nomis.operationsManagement.EnrollmentStatusObjectManager;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.BeneficiaryAttributeManager;
import com.nomis.ovc.util.EnrolledOnTreatment;
import com.nomis.ovc.util.EnrolledOnTreatmentManager;
import com.nomis.ovc.util.EnrollmentStatus;
import com.nomis.ovc.util.HivPropertiesManager;
import com.nomis.ovc.util.HivStatus;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class AdultHouseholdMember implements Serializable,Beneficiary
{
    private String hhUniqueId;
    private Date dateOfBirth;
    private Date dateOfEnrollment;
    private String enrollmentId;
    private String legacyId;
    //private String nationalId;
    private String beneficiaryId;
    private String firstName;
    private String surname;
    private String address;
    private String sex;
    private String phoneNumber;
    private String cboId;
    private String level3OuId;
    private String organizationUnit;
    private int occupation;
    private int numberOfChildren;
    private int baselineHivStatus;
    private Date dateOfBaselineHivStatus;
    private int educationLevel;
    private int isCaregiver;
    private int beneficiaryType;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    private int markedForDelete;
    private int viralLoad;
    private String fullName;
    //private int beneficiaryType=AppConstant.CAREGIVER_TYPE_NUM;
    private int pointOfUpdate=2;
    private int currentEnrollmentStatus;
    private Date dateOfCurrentEnrollmentStatus;
    private int ageAtBaseline;
    private int ageUnitAtBaseline=AppConstant.AGEUNIT_YEAR_NUM;
    private int currentAge;
    private int currentAgeUnit;
    private int maritalStatus;
    private int comfortableToDiscloseMemberHivStatus;
    private int disabled;
    private int currentHivStatus;
    private int enrolledOnTreatment;
    private Date dateOfCurrentHivStatus;
    private Date dateEnrolledOnTreatment;
    private String hivTreatmentFacilityId;
    private String treatmentId;
    private EnrolledOnTreatment enrolledOnTreatmentObject;
    private ReferralFacility referralFacility;
    private boolean forUpdateHivStatus;
    int serialNo=0;
    String rowColor=AppConstant.FIRSTREPORTROWCOLOUR;
    private HouseholdEnrollment hhe;
    private HivStatus baselineHivStatusObject;
    private HivStatus currentHivStatusObject;
    private EnrollmentStatus currentEnrollmentStatusObj;
    private BeneficiaryType beneficiaryTypeObject;
    private CommunityWorker communityWorker=null;
    private BeneficiaryAttributeManager bcam=new BeneficiaryAttributeManager();
    private String ageUnitName="Year";
    private String beneficiaryTypeName=AppConstant.CAREGIVER_TYPE;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = getHhe().getAddress();
    }

    public int getBaselineHivStatus() {
        return baselineHivStatus;
    }

    public void setBaselineHivStatus(int baselineHivStatus) {
        this.baselineHivStatus = baselineHivStatus;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }
    public BeneficiaryType getBeneficiaryTypeObject() {
        return beneficiaryTypeObject=BeneficiaryTypeManager.getBeneficiaryType(getBeneficiaryType());
    }
    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public Date getDateOfBaselineHivStatus() {
        return dateOfBaselineHivStatus;
    }

    public void setDateOfBaselineHivStatus(Date dateOfBaselineHivStatus) {
        this.dateOfBaselineHivStatus = dateOfBaselineHivStatus;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }
    
    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getLevel3OuId() {
        return level3OuId;
    }

    public void setLevel3OuId(String level3OuId) {
        this.level3OuId = level3OuId;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getOccupation() {
        return occupation;
    }

    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getIsCaregiver() {
        return isCaregiver;
    }

    public void setIsCaregiver(int isCaregiver) {
        this.isCaregiver = isCaregiver;
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

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    
    public HouseholdEnrollment getHhe() {
        if(hhe==null)
        hhe=BeneficiaryManager.getHouseholdEnrollment(hhUniqueId);
        return hhe;
    }
    public void setHhe(HouseholdEnrollment hhe) {
        this.hhe = hhe;
    }

    public HivStatus getBaselineHivStatusObject() {
        baselineHivStatusObject=HivPropertiesManager.getHivStatus(baselineHivStatus);
        return baselineHivStatusObject;
    }

    public HivStatus getCurrentHivStatusObject() {
        currentHivStatusObject=HivPropertiesManager.getHivStatus(currentHivStatus);
        return currentHivStatusObject;
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

    public String getFullName() 
    {
        fullName=this.getFirstName()+" "+this.getSurname();
        return fullName;
    }

    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public void setBeneficiaryType(int beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public int getBeneficiaryType() {
        if(beneficiaryType==0)
        beneficiaryType=AppConstant.CAREGIVER_TYPE_NUM;
        return beneficiaryType;
    }

    public int getPointOfUpdate() {
        return pointOfUpdate;
    }

    public void setPointOfUpdate(int pointOfUpdate) {
        this.pointOfUpdate = pointOfUpdate;
    }

    public int getCurrentEnrollmentStatus() {
        return currentEnrollmentStatus;
    }

    public void setCurrentEnrollmentStatus(int currentEnrollmentStatus) {
        this.currentEnrollmentStatus = currentEnrollmentStatus;
    }

    public Date getDateOfCurrentEnrollmentStatus() {
        return dateOfCurrentEnrollmentStatus;
    }

    public void setDateOfCurrentEnrollmentStatus(Date dateOfCurrentEnrollmentStatus) {
        this.dateOfCurrentEnrollmentStatus = dateOfCurrentEnrollmentStatus;
    }

    public int getAgeAtBaseline() {
        return ageAtBaseline;
    }

    public void setAgeAtBaseline(int ageAtBaseline) {
        this.ageAtBaseline = ageAtBaseline;
    }

    public int getAgeUnitAtBaseline() {
        return ageUnitAtBaseline;
    }

    public void setAgeUnitAtBaseline(int ageUnitAtBaseline) {
        this.ageUnitAtBaseline = ageUnitAtBaseline;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public int getCurrentAgeUnit() {
        return currentAgeUnit;
    }

    public void setCurrentAgeUnit(int currentAgeUnit) {
        this.currentAgeUnit = currentAgeUnit;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getComfortableToDiscloseMemberHivStatus() {
        return comfortableToDiscloseMemberHivStatus;
    }

    public void setComfortableToDiscloseMemberHivStatus(int comfortableToDiscloseMemberHivStatus) {
        this.comfortableToDiscloseMemberHivStatus = comfortableToDiscloseMemberHivStatus;
    }

    public Date getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(Date dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public int getCurrentHivStatus() {
        return currentHivStatus;
    }

    public void setCurrentHivStatus(int currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
    }

    public Date getDateOfCurrentHivStatus() {
        return dateOfCurrentHivStatus;
    }

    public void setDateOfCurrentHivStatus(Date dateOfCurrentHivStatus) {
        this.dateOfCurrentHivStatus = dateOfCurrentHivStatus;
    }

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public String getHivTreatmentFacilityId() {
        return hivTreatmentFacilityId;
    }

    public void setHivTreatmentFacilityId(String hivTreatmentFacilityId) {
        this.hivTreatmentFacilityId = hivTreatmentFacilityId;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }
    
    public EnrolledOnTreatment getEnrolledOnTreatmentObject() {
        enrolledOnTreatmentObject=EnrolledOnTreatmentManager.getEnrolledOnTreatment(enrolledOnTreatment);
        return enrolledOnTreatmentObject;
    }
    public ReferralFacility getReferralFacility() 
    {
        referralFacility=bcam.getReferralFacility(hivTreatmentFacilityId);
        return referralFacility;
    }
    public EnrollmentStatus getCurrentEnrollmentStatusObj() 
    {
        currentEnrollmentStatusObj=EnrollmentStatusObjectManager.getEnrollmentStatus(getCurrentEnrollmentStatus());
        return currentEnrollmentStatusObj;
    }
    public CommunityWorker getCommunityWorker() {
        return communityWorker=BeneficiaryManager.getCommunityWorker("");
    }
    public String getBaselineAgeUnitName() 
    {
        return ageUnitName;
    }

    public String getCurrentAgeUnitName() 
    {
        return ageUnitName;
    }
    public int getViralLoad() {
        if(viralLoad==0)
        viralLoad=BeneficiaryManager.getViralLoad(beneficiaryId, currentHivStatus, enrolledOnTreatment);
        return viralLoad;
    }

    public void setViralLoad(int viralLoad) {
        this.viralLoad = viralLoad;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }
    public String getBeneficiaryTypeName()
    {
        return beneficiaryTypeName;
    }

    public boolean isForUpdateHivStatus() {
        return forUpdateHivStatus;
    }

    public void setForUpdateHivStatus(boolean forUpdateHivStatus) {
        this.forUpdateHivStatus = forUpdateHivStatus;
    }
    public boolean isCurrentlyEnrolled()
    {
        return BeneficiaryManager.isCurrentlyEnrolled(getCurrentEnrollmentStatus());
    }
}
