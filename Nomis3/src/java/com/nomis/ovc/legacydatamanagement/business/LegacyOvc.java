/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.legacydatamanagement.business;

import java.io.Serializable;

/**
 *
 * @author HP USER
 */
public class LegacyOvc implements Serializable 
{
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    
    private LegacyNutritionAssessment nutritionAssessmentAsAtReport;
    private LegacyNutritionAssessment currentNutritionAssessment;
    private String state;
    private String lga;
    private String ward;
    private String dateEnrollment;
    private int monthOfEnrollment;
    private int yearOfEnrollment;
    private String hhUniqueId;
    private int serialNo;
    private String ovcId;
    private String vcEnrollmentId;
    private String newOvcId;
    private String lastName;
    private String firstName;
    private String fullName;
    private String gender;
    private int age;
    private int currentAge;
    private String currentAgeUnit;
    private String ageUnit;
    private String address;
    private String phone;
    private String eligibility;
    private String hivStatus;
    private LegacyHivStatusUpdate currentHivStatus;
    private LegacyHivStatusUpdate activeHivStatus;
    private String dateOfCurrentNutritionalStatus;
    private String dateOfCurrentHivStatus;
    private String birthCertificate;
    //private BirthRegistration birthRegistration;
    //private BirthRegistration currentBirthRegistrationStatus;
    private String activeBirthRegistrationStatus;
    private String activeSchoolStatus;
    private String schoolStatus;
    private String schoolName;
    private String currentSchoolStatus;
    private String currentClass;
    private String schoolType;
    private String orphanage;
    private String orphanageName;

    private String hhSurname;
    private String hhFirstname;
    private String hhGender;
    private int hhAge;
    private int numOfChildrenInHh;
    private int numOfOVCInHh;
    private String caregiverId;
    private String caregiverName;
    private String caregiverGender;
    private int caregiverAge;
    private String caregiverAddress;
    private String caregiverPhone;
    private String caregiverOccupation;
    private String caregiverRelationships;
    private String serviceEnrollment;
    private String completedbyName;
    private String completedbyDesignation;
    private String completedbyCbo;
    private String sourceOfInfo;
    private double weight;
    private double height;
    private String verifiedBy;
    private String dateOfEntry;
    private String recordedBy;
    private String partner;
    private String pointOfService="enrollment";
    private String enrolledInCare="No";
    private String enrolledOnART="No";
    private String enrolledOnTreatment="No";
    private String facilityId;
    private String withdrawnFromProgram="No";
    private String reasonForWithdrawal="Active";
    private String dateOfWithdrawal;
    private int baselineVulnerabilityScore=0;
    private int currentVulnerabilityScore=0;
    private String baselineVulnerabilityStatus;
    private String currentVulnerabilityStatus;
    private LegacyCaregiver cgiver;
    LegacyHouseholdEnrollment hhe;
    private int markedForDelete;
    private String lastServiceDate="1900-01-01";
    private String serviceStatusColor;
    private int currentEnrollmentStatusCode;
    private boolean schoolStatusRequired=true;
    private boolean householdInfoRequired=true;
    private boolean birthRegStatusRequired=true;
    private boolean caregiverInfoRequired=true;
    private boolean addressRequired=true;
    private boolean activeHivStatusRequired=true;
    private boolean vulnearabiltyStatusInfoRequired=true;
    private LegacyReferralDirectory facility;
    //private ChildSchoolStatus currentSchoolStatusObj=null;//=beneficiary.getCurrentChildSchoolStatus(ovcId);
//beneficiary.getHouseholdHead(hhUniqueId);

public LegacyOvc() 
{
    //hhe=beneficiary.getHouseholdHead(hhUniqueId);
}
public String getState() 
{
    state=getHhe().getStateName();
    return state;
}
public void setState(String state) 
{
this.state = state;
}

public String getLga() 
{
    lga=getHhe().getLgaName();
    return lga;
}
public void setLga(String lga) {
this.lga = lga;
}

public String getWard() 
{
    ward=getHhe().getCommunityName();
    return ward;
}
public void setWard(String ward) {
this.ward = ward;
}

public LegacyCaregiver getCgiver() 
{
    cgiver=new LegacyCaregiver();
    //if(this.isCaregiverInfoRequired())
    //cgiver=beneficiary.getCaregiver(caregiverId);
    return cgiver;
}

public void setCgiver(LegacyCaregiver cgiver) {
    this.cgiver = cgiver;
}

public String getDateEnrollment() {
return dateEnrollment;
}
public void setDateEnrollment(String dateEnrollment) {
this.dateEnrollment = dateEnrollment;
}

public String getHhUniqueId() {
    return hhUniqueId;
}

public void setHhUniqueId(String hhUniqueId) {
    this.hhUniqueId = hhUniqueId;
}

public int getSerialNo() {
    return serialNo;
}

public void setSerialNo(int serialNo) {
    this.serialNo = serialNo;
}

public String getVcEnrollmentId() {
    return vcEnrollmentId;
}

public void setVcEnrollmentId(String vcEnrollmentId) {
    this.vcEnrollmentId = vcEnrollmentId;
}

public String getOvcId() {
return ovcId;
}
public void setOvcId(String ovcId) {
this.ovcId = ovcId;
}

    public String getNewOvcId() {
        return newOvcId;
    }

    public void setNewOvcId(String newOvcId) {
        this.newOvcId = newOvcId;
    }

public String getLastName() {
return lastName;
}
public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getFirstName() {
return firstName;
}
public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getGender() {
return gender;
}
public void setGender(String gender) {
this.gender = gender;
}

public int getAge() {
return age;
}
public void setAge(int age) {
this.age = age;
}

public int getCurrentAge() {
    return currentAge;
}

public void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
}

public String getCurrentAgeUnit() 
{
    return currentAgeUnit;
}

public void setCurrentAgeUnit(String currentAgeUnit) 
{
    this.currentAgeUnit = currentAgeUnit;
}

public String getAgeUnit() {
return ageUnit;
}
public void setAgeUnit(String ageUnit) {
this.ageUnit = ageUnit;
}

public String getAddress() 
{
    if(address==null)
    {
        if(this.isHouseholdInfoRequired())
        {
            //if(beneficiary.getHouseholdHead(getHhUniqueId()) !=null)
            //address=beneficiary.getHouseholdHead(getHhUniqueId()).getAddress();
        }
    }
    return address;
}
public void setAddress(String address) 
{
this.address = address;
}

public String getPhone() {
return phone;
}
public void setPhone(String phone) {
this.phone = phone;
}

public String getEligibility() {
return eligibility;
}
public void setEligibility(String eligibility) {
this.eligibility = eligibility;
}
public LegacyHivStatusUpdate getActiveHivStatus() 
{
    activeHivStatus=new LegacyHivStatusUpdate();
    //if(this.isActiveHivStatusRequired())
    //activeHivStatus=beneficiary.getActiveHivStatus(ovcId,this.getDateEnrollment());
    this.setEnrolledInCare(activeHivStatus.getClientEnrolledInCare());
    this.enrolledOnART=activeHivStatus.getEnrolledOnART();
    return activeHivStatus;
}


public LegacyHivStatusUpdate getCurrentHivStatus() 
{
    LegacyHivStatus hsu=new LegacyHivStatus();
    if(this.isActiveHivStatusRequired())
    currentHivStatus=hsu.getCurrentHivStatus(ovcId,dateEnrollment);
    if(currentHivStatus==null)
    {
        currentHivStatus=getActiveHivStatus();
    }
    return currentHivStatus;
}

public String getActiveBirthRegistrationStatus() 
{
    //if(this.isBirthRegStatusRequired())
    //this.activeBirthRegistrationStatus=beneficiary.getActiveBirthRegistrationStatus(ovcId);
    return activeBirthRegistrationStatus;
}
public String getActiveSchoolStatus() 
{
    //if(this.isSchoolStatusRequired())
    //this.activeSchoolStatus=beneficiary.getActiveSchoolStatus(ovcId);
    if(activeSchoolStatus==null)
    activeSchoolStatus=schoolStatus;
    return activeSchoolStatus;
}

public String getFullName() 
{
    if(firstName==null && lastName==null)
    return fullName;
    return firstName+" "+lastName;
}

public void setFullName(String fullName) {
    this.fullName = fullName;
}

public String getHivStatus() {
return hivStatus;//getActiveHivStatus().getHivStatus();
}
public void setHivStatus(String hivStatus) {
this.hivStatus = hivStatus;
}

public String getBirthCertificate() {
return birthCertificate;
}
public void setBirthCertificate(String birthCertificate) {
this.birthCertificate = birthCertificate;
}

/*public BirthRegistration getBirthRegistration() 
{
    if(this.isBirthRegStatusRequired())
    birthRegistration=beneficiary.getActiveBirthRegistration(ovcId);
    if(birthRegistration==null)
    birthRegistration=new BirthRegistration();
    return birthRegistration;
}*/

    /*public BirthRegistration getCurrentBirthRegistrationStatus() 
    {
        if(this.isBirthRegStatusRequired())
        currentBirthRegistrationStatus=beneficiary.getCurrentBirthRegistration(ovcId, dateEnrollment, pointOfService);
    if(currentBirthRegistrationStatus==null)
    currentBirthRegistrationStatus=new BirthRegistration();
    
        return currentBirthRegistrationStatus;
    }*/

public String getSchoolStatus() {
return schoolStatus;
}
public void setSchoolStatus(String schoolStatus) {
this.schoolStatus = schoolStatus;
}

public String getSchoolName() {
return schoolName;
}
public void setSchoolName(String schoolName) {
this.schoolName = schoolName;
}

public String getCurrentClass() 
{
    //if(currentClass==null)
    //currentClass=getCurrentSchoolStatusObj().getCurrentClass();//beneficiary.getOvcCurrentSchoolStatus(ovcId);
    return currentClass;
}
public void setCurrentClass(String currentClass) {
this.currentClass = currentClass;
}

public String getSchoolType() {
return schoolType;
}
public void setSchoolType(String schoolType) {
this.schoolType = schoolType;
}

public String getOrphanage() {
return orphanage;
}
public void setOrphanage(String orphanage) {
this.orphanage = orphanage;
}

public String getOrphanageName() {
return orphanageName;
}
public void setOrphanageName(String orphanageName) {
this.orphanageName = orphanageName;
}

    public int getHhAge() {
        return hhAge;
    }

    public void setHhAge(int hhAge) {
        this.hhAge = hhAge;
    }

    public String getHhFirstname() 
    {
        if(this.isHouseholdInfoRequired())
        {
            if(hhFirstname==null)
            {
                //if(beneficiary.getHouseholdHead(getHhUniqueId()) !=null)
                //hhFirstname=beneficiary.getHouseholdHead(getHhUniqueId()).getHhFirstname();
            }
        }
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

    public String getHhSurname() 
    {
        if(this.isHouseholdInfoRequired())
        {
            if(hhSurname==null)
            {
                //if(beneficiary.getHouseholdHead(getHhUniqueId()) !=null)
                //hhSurname=beneficiary.getHouseholdHead(getHhUniqueId()).getHhSurname();
            }
        }
        return hhSurname;
    }

    public void setHhSurname(String hhSurname) {
        this.hhSurname = hhSurname;
    }

    public int getNumOfChildrenInHh() {
        return numOfChildrenInHh;
    }

    public void setNumOfChildrenInHh(int numOfChildrenInHh) {
        this.numOfChildrenInHh = numOfChildrenInHh;
    }

    public int getNumOfOVCInHh() {
        return numOfOVCInHh;
    }

    public void setNumOfOVCInHh(int numOfOVCInHh) {
        this.numOfOVCInHh = numOfOVCInHh;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

public String getCaregiverName() 
{
    return getCgiver().getCaregiverFullName();
}
public void setCaregiverName(String caregiverName) 
{
    this.caregiverName=caregiverName;
}

public String getCaregiverGender() 
{
    return getCgiver().getCaregiverGender();
}
public void setCaregiverGender(String caregiverGender) 
{
this.caregiverGender = caregiverGender;
}

public int getCaregiverAge() 
{
   return getCgiver().getCaregiverAge();
}
public void setCaregiverAge(int caregiverAge) {
this.caregiverAge = caregiverAge;
}

public String getCaregiverAddress() 
{
    return getCgiver().getCaregiverAddress();
}
public void setCaregiverAddress(String caregiverAddress) {
this.caregiverAddress = caregiverAddress;
}

public String getCaregiverPhone() 
{
    return getCgiver().getCaregiverPhone();
}
public void setCaregiverPhone(String caregiverPhone) {
this.caregiverPhone = caregiverPhone;
}

public String getCaregiverOccupation() 
{
    /*if(caregiverOccupation ==null)
    {
        if(beneficiary.getCaregiver(caregiverId) !=null)
        caregiverOccupation=beneficiary.getCaregiver(caregiverId).getCaregiverOccupation();
    }*/
    return getCgiver().getCaregiverOccupation();
}
public void setCaregiverOccupation(String caregiverOccupation) {
this.caregiverOccupation = caregiverOccupation;
}

public String getCaregiverRelationships() {
return caregiverRelationships;
}
public void setCaregiverRelationships(String caregiverRelationships) {
this.caregiverRelationships = caregiverRelationships;
}

public String getServiceEnrollment() {
return serviceEnrollment;
}
public void setServiceEnrollment(String serviceEnrollment) {
this.serviceEnrollment = serviceEnrollment;
}

public String getCompletedbyName() {
return completedbyName;
}
public void setCompletedbyName(String completedbyName) {
this.completedbyName = completedbyName;
}

public String getCompletedbyDesignation() {
return completedbyDesignation;
}
public void setCompletedbyDesignation(String completedbyDesignation) {
this.completedbyDesignation = completedbyDesignation;
}

public String getCompletedbyCbo() 
{
    completedbyCbo=getHhe().getOrgName();
    
    return completedbyCbo;
}
public void setCompletedbyCbo(String completedbyCbo) {
this.completedbyCbo = completedbyCbo;
}

 public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


     public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


     public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getSourceOfInfo() {
        return sourceOfInfo;
    }

    public void setSourceOfInfo(String sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getEnrolledInCare() {
        return enrolledInCare;
    }

    public void setEnrolledInCare(String enrolledInCare) {
        this.enrolledInCare = enrolledInCare;
    }

    public String getPointOfService() {
        return pointOfService;
    }

    public void setPointOfService(String pointOfService) {
        this.pointOfService = pointOfService;
    }

    public String getDateOfCurrentHivStatus() {
        return dateOfCurrentHivStatus;
    }

    public void setDateOfCurrentHivStatus(String dateOfCurrentHivStatus) {
        this.dateOfCurrentHivStatus = dateOfCurrentHivStatus;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getDateOfWithdrawal() 
    {
        LegacyOvcWithdrawal withdrawal=null;
        //withdrawal=beneficiary.getWithdrawalRecord(ovcId,getWithdrawnFromProgram());
        if(withdrawal !=null)
        dateOfWithdrawal=withdrawal.getDateOfWithdrawal();
        return dateOfWithdrawal;
    }

    public void setDateOfWithdrawal(String dateOfWithdrawal) {
        this.dateOfWithdrawal = dateOfWithdrawal;
    }

    public String getReasonForWithdrawal() 
    {
        LegacyOvcWithdrawal withdrawal=null;
        //withdrawal=beneficiary.getWithdrawalRecord(ovcId,getWithdrawnFromProgram());
        if(withdrawal !=null)
        reasonForWithdrawal=withdrawal.getReasonWithdrawal();
        return reasonForWithdrawal;
    }

    public void setReasonForWithdrawal(String reasonForWithdrawal) {
        this.reasonForWithdrawal = reasonForWithdrawal;
    }

    public String getWithdrawnFromProgram() {
        return withdrawnFromProgram;
    }

    public void setWithdrawnFromProgram(String withdrawnFromProgram) {
        this.withdrawnFromProgram = withdrawnFromProgram;
    }

    public String getEnrolledOnART() 
    {
        return enrolledOnART;
    }

    public void setEnrolledOnART(String enrolledOnART) {
        this.enrolledOnART = enrolledOnART;
    }

    public String getEnrolledOnTreatment() {
        if((getEnrolledOnART() !=null && getEnrolledOnART().equalsIgnoreCase("Yes")) || (getEnrolledInCare() !=null && getEnrolledInCare().equalsIgnoreCase("Yes")))
        enrolledOnTreatment="Yes";
        else
        enrolledOnTreatment="No";
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(String enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public String getDateOfCurrentNutritionalStatus() 
    {
        return dateOfCurrentNutritionalStatus;
    }

    public void setDateOfCurrentNutritionalStatus(String dateOfCurrentNutritionalStatus) {
        this.dateOfCurrentNutritionalStatus = dateOfCurrentNutritionalStatus;
    }

    
    public LegacyNutritionAssessment getCurrentNutritionAssessment() 
    {
        if(currentNutritionAssessment==null)
        currentNutritionAssessment=new LegacyNutritionAssessment();
        return currentNutritionAssessment;
    }

    public void setCurrentNutritionAssessment(LegacyNutritionAssessment currentNutritionAssessment) {
        this.currentNutritionAssessment = currentNutritionAssessment;
    }

    public LegacyNutritionAssessment getNutritionAssessmentAsAtReport() {
        if(nutritionAssessmentAsAtReport==null)
        nutritionAssessmentAsAtReport=new LegacyNutritionAssessment();
        return nutritionAssessmentAsAtReport;
    }

    public void setNutritionAssessmentAsAtReport(LegacyNutritionAssessment nutritionAssessmentAsAtReport) {
        this.nutritionAssessmentAsAtReport = nutritionAssessmentAsAtReport;
    }

    public int getMonthOfEnrollment() 
    {
        if(dateEnrollment !=null)
        {
            //monthOfEnrollment=beneficiary.getMonthFromDate(dateEnrollment);;
        }
        return monthOfEnrollment;
    }

    public void setMonthOfEnrollment(int monthOfEnrollment) {
        this.monthOfEnrollment = monthOfEnrollment;
    }

    public int getYearOfEnrollment() 
    {
        if(dateEnrollment !=null)
        {
            //yearOfEnrollment=beneficiary.getYearFromDate(dateEnrollment);;
        }
        return yearOfEnrollment;
    }

    public void setYearOfEnrollment(int yearOfEnrollment) {
        this.yearOfEnrollment = yearOfEnrollment;
    }

    public LegacyHouseholdEnrollment getHhe() 
    {
        //if(this.isHouseholdInfoRequired())
        //hhe=beneficiary.getHouseholdHead(hhUniqueId);
        //else
        //hhe=new LegacyHouseholdEnrollment();
        return hhe;
    }

    public int getBaselineVulnerabilityScore() 
    {
        //if(this.isVulnearabiltyStatusInfoRequired())
        //baselineVulnerabilityScore=beneficiary.getOvcBaselineVulnerabilityScore(ovcId, dateEnrollment);
        return baselineVulnerabilityScore;
    }

    public void setBaselineVulnerabilityScore(int baselineVulnerabilityScore) 
    {
        this.baselineVulnerabilityScore = baselineVulnerabilityScore;
    }

    public String getBaselineVulnerabilityStatus() 
    {
        //if(this.isVulnearabiltyStatusInfoRequired())
        //baselineVulnerabilityStatus=beneficiary.getOvcBaselineVulnerabilityStatus(ovcId, dateEnrollment);
        return baselineVulnerabilityStatus;
    }

    public void setBaselineVulnerabilityStatus(String baselineVulnerabilityStatus) 
    {
        this.baselineVulnerabilityStatus = baselineVulnerabilityStatus;
    }

    public int getCurrentVulnerabilityScore() 
    {
        //if(this.isVulnearabiltyStatusInfoRequired())
        //currentVulnerabilityScore=beneficiary.getOvcCurrentVulnerabilityScore(ovcId);
        return currentVulnerabilityScore;
    }

    public void setCurrentVulnerabilityScore(int currentVulnerabilityScore) 
    {
        this.currentVulnerabilityScore = currentVulnerabilityScore;
    }

    public String getCurrentVulnerabilityStatus() 
    {
        //if(this.isVulnearabiltyStatusInfoRequired())
        //currentVulnerabilityStatus=beneficiary.getOvcCurrentVulnerabilityStatus(ovcId);
        return currentVulnerabilityStatus;
    }

    public void setCurrentVulnerabilityStatus(String currentVulnerabilityStatus) 
    {
        this.currentVulnerabilityStatus = currentVulnerabilityStatus;
    }

    public String getCurrentSchoolStatus() 
    {
        //currentSchoolStatus=getCurrentSchoolStatusObj().getOvcInSchool();//this.getSchoolStatus();
        /*if(isSchoolStatusRequired())
        {
            currentSchoolStatus=beneficiary.getOvcCurrentSchoolStatus(ovcId);
            if(currentSchoolStatus==null)
            currentSchoolStatus=this.getSchoolStatus();
        }*/
        return currentSchoolStatus;
    }

    public void setCurrentSchoolStatus(String currentSchoolStatus) {
        this.currentSchoolStatus = currentSchoolStatus;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(String lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public String getServiceStatusColor() {
        return serviceStatusColor;
    }

    public void setServiceStatusColor(String serviceStatusColor) {
        this.serviceStatusColor = serviceStatusColor;
    }

    public int getCurrentEnrollmentStatusCode() {
        return currentEnrollmentStatusCode;
    }

    public void setCurrentEnrollmentStatusCode(int currentEnrollmentStatusCode) {
        this.currentEnrollmentStatusCode = currentEnrollmentStatusCode;
    }

    public boolean isActiveHivStatusRequired() {
        return activeHivStatusRequired;
    }

    public void setActiveHivStatusRequired(boolean activeHivStatusRequired) {
        this.activeHivStatusRequired = activeHivStatusRequired;
    }

    public boolean isAddressRequired() {
        return addressRequired;
    }

    public void setAddressRequired(boolean addressRequired) {
        this.addressRequired = addressRequired;
    }

    public LegacyBeneficiaryResourceManager getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(LegacyBeneficiaryResourceManager beneficiary) {
        this.beneficiary = beneficiary;
    }

    public boolean isBirthRegStatusRequired() {
        return birthRegStatusRequired;
    }

    public void setBirthRegStatusRequired(boolean birthRegStatusRequired) {
        this.birthRegStatusRequired = birthRegStatusRequired;
    }

    public boolean isCaregiverInfoRequired() {
        return caregiverInfoRequired;
    }

    public void setCaregiverInfoRequired(boolean caregiverInfoRequired) {
        this.caregiverInfoRequired = caregiverInfoRequired;
    }

    public boolean isHouseholdInfoRequired() {
        return householdInfoRequired;
    }

    public void setHouseholdInfoRequired(boolean householdInfoRequired) {
        this.householdInfoRequired = householdInfoRequired;
    }

    public boolean isSchoolStatusRequired() {
        return schoolStatusRequired;
    }

    public void setSchoolStatusRequired(boolean schoolStatusRequired) {
        this.schoolStatusRequired = schoolStatusRequired;
    }

    public boolean isVulnearabiltyStatusInfoRequired() {
        return vulnearabiltyStatusInfoRequired;
    }

    public void setVulnearabiltyStatusInfoRequired(boolean vulnearabiltyStatusInfoRequired) {
        this.vulnearabiltyStatusInfoRequired = vulnearabiltyStatusInfoRequired;
    }
    public LegacyReferralDirectory getFacility() 
    {
        if(facility==null)
        {
            //facility=beneficiary.getReferralDirectory(facilityId);
        }
        return facility;
    }

    public void setFacility(LegacyReferralDirectory facility) {
        this.facility = facility;
    }

    /*public ChildSchoolStatus getCurrentSchoolStatusObj() 
    {
        if(currentSchoolStatusObj==null)
        currentSchoolStatusObj=beneficiary.getCurrentChildSchoolStatus(ovcId);
        return currentSchoolStatusObj;
    }*/

}
