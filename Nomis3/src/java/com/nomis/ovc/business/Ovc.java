/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.operationsManagement.BeneficiaryType;
import com.nomis.operationsManagement.BeneficiaryTypeManager;
import com.nomis.operationsManagement.EnrollmentStatusObjectManager;
import com.nomis.operationsManagement.VulnerabilityStatusManager;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.BeneficiaryAttributeManager;
import com.nomis.ovc.util.BirthCertificate;
import com.nomis.ovc.util.BirthCertificateManager;
import com.nomis.ovc.util.CaregiverRelationship;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.Disability;
import com.nomis.ovc.util.EnrolledOnTreatment;
import com.nomis.ovc.util.EnrolledOnTreatmentManager;
import com.nomis.ovc.util.EnrollmentStatus;
import com.nomis.ovc.util.HivPropertiesManager;
import com.nomis.ovc.util.HivStatus;
import com.nomis.ovc.util.ReferralFacilityManager;
import com.nomis.ovc.util.SchoolStatus;
import com.nomis.ovc.util.SchoolStatusManager;
import com.nomis.ovc.util.SingleOptionManager;
import com.nomis.ovc.util.SourceOfInformation;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class Ovc implements Serializable,Beneficiary
{
    private String cboId;
    private String organizationUnitId;
    private String hhUniqueId;
    private String ovcId;
    private String beneficiaryId;
    private String legacyId;
    private int beneficiaryType;
    private String enrollmentId;
    private Date dateOfEnrollment;
    private String displayDateOfEnrollment;
    private Date dateOfBirth;
    private String firstName;
    private String surname;
    private String address;
    private String sex;
    private int ageAtBaseline;
    private int ageUnitAtBaseline;
    //private int beneficiaryType;
    private int currentAge;
    private int currentAgeUnit;
    private int currentNutritionalStatus;
    
    private String phoneNumber;
    private int baselineHivStatus;
    private Date dateOfBaselineHivStatus;
    private int currentHivStatus;
    private Date dateOfCurrentHivStatus;
    
    private String mainVulnerabilityStatusCode;
    private String vulnerabilityStatusCode;
    private String mainVulnerabilityStatusName;
    private String selectedVulnerabilityNames;
    private int baselineBirthRegistrationStatus;
    private int currentBirthRegistrationStatus;
    private Date dateOfCurrentBirthRegStatus;
    private int baselineSchoolStatus;
    private int currentSchoolStatus;
    private Date dateOfCurrentSchoolStatus;
    private String schoolName;
    private String schoolGrade;
    private String caregiverId;
    private int caregiverRelationship;
    private double weight;
    private double height;
    private int enrolledOnTreatment;
    private Date dateEnrolledOnTreatment;
    private String hivTreatmentFacilityId;
    private String treatmentId;
    private boolean forUpdateHivStatus;
        
    private int currentEnrollmentStatus;
    private Date dateOfCurrentEnrollmentStatus;
    //private Date dateOfCurrentStatus;
    private String fullName;
    private int childHasCasePlan;
    private Date dateCasePlanDeveloped;
    private int markedForDelete;
    private int sourceOfInfo;
    private String communityWorkerName;
    private String recordedBy;
    private Date dateCreated;
    private Date lastModifiedDate;
    
    private int foodSecurity;
    private int wellness;
    private int nutritionAndGrowth;
    private int healthCareServices;
    private int shelter;
    private int emotionalHealth;
    private int care;
    private int socialBehaviour;
    private int abuseAndExploitation;
    private int developmentAndPerformance;
    private int legalProtection;
    private int educationAndWork;
        
    private int numberOfServiceRecords;
    private int numberOfHivRiskAssessmentRecords;
    private int numberOfReferralServiceRecords;
    private EnrolledOnTreatment enrolledOnTreatmentObject;
    private String baselineAgeUnitName;
    private String currentAgeUnitName;
    private int viralLoad;
    private int enrollmentSetting;
    private String enrollmentSettingName;
    //private ReferralFacility facilityEnrolledOnHivTreatment;
    private ReferralFacility referralFacility;
    private BirthCertificate baselineBirthCertificateObject;
    private School schoolObj;
    private SchoolGrade schoolGradeObj;
    private SchoolStatus schoolStatusObj;
    private CaregiverRelationship caregiverRelationshipObj;
    private SourceOfInformation sourceOfInformationObj;
    private Disability disabilityObj;
    private BeneficiaryAttributeManager bcam=new BeneficiaryAttributeManager();
    private HouseholdEnrollment hhe;
    private AdultHouseholdMember primaryCaregiver;
    private HivStatus hivStatus;
    private HivStatus baselineHivStatusObject;
    //private BirthCertificate baselineBirthCertificateObject;
    private BirthCertificate currentBirthCertificateObject;
    private SchoolStatus schoolStatusObject;
    private EnrollmentStatus currentEnrollmentStatusObj;
    private BeneficiaryType beneficiaryTypeObject;
    //private HivStatusManager hivStatusManager;
    private String casePlanAnswer;
    private int pointOfUpdate=3;
    private int serialNo=0;
    String rowColor="#FFFFFF";
    private HivStatus currentHivStatusObject;
    private SingleOptionManager som=new SingleOptionManager();
    private CommunityWorker communityWorker=null;
    private String beneficiaryTypeName=AppConstant.OVC_TYPE;
    private NutritionStatus currentNutritionStatus;
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;//getHhe().getAddress();
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

    public String getBeneficiaryId() {
        return beneficiaryId=ovcId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public BeneficiaryType getBeneficiaryTypeObject() {
        return beneficiaryTypeObject=BeneficiaryTypeManager.getBeneficiaryType(getBeneficiaryType());
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public int getCaregiverRelationship() {
        return caregiverRelationship;
    }

    public void setCaregiverRelationship(int caregiverRelationship) {
        this.caregiverRelationship = caregiverRelationship;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public int getEnrolledOnTreatment() {
        return enrolledOnTreatment;
    }

    public void setEnrolledOnTreatment(int enrolledOnTreatment) {
        this.enrolledOnTreatment = enrolledOnTreatment;
    }

    public EnrolledOnTreatment getEnrolledOnTreatmentObject() 
    {
        enrolledOnTreatmentObject=EnrolledOnTreatmentManager.getEnrolledOnTreatment(enrolledOnTreatment);
        return enrolledOnTreatmentObject;
    }

    public void setEnrolledOnTreatmentObject(EnrolledOnTreatment enrolledOnTreatmentObject) {
        this.enrolledOnTreatmentObject = enrolledOnTreatmentObject;
    }

    public String getMainVulnerabilityStatusCode() {
        return mainVulnerabilityStatusCode;
    }

    public void setMainVulnerabilityStatusCode(String mainVulnerabilityStatusCode) {
        this.mainVulnerabilityStatusCode = mainVulnerabilityStatusCode;
    }

@Override
    public int getCurrentAge() {
        return currentAge;
    }
@Override
    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }
@Override
    public int getCurrentAgeUnit() {
        return currentAgeUnit;
    }
@Override
    public void setCurrentAgeUnit(int currentAgeUnit) {
        this.currentAgeUnit = currentAgeUnit;
    }
@Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
@Override
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfCurrentSchoolStatus() {
        return dateOfCurrentSchoolStatus;
    }

    public void setDateOfCurrentSchoolStatus(Date dateOfCurrentSchoolStatus) {
        this.dateOfCurrentSchoolStatus = dateOfCurrentSchoolStatus;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getHivTreatmentFacilityId() {
        return hivTreatmentFacilityId;
    }

    public void setHivTreatmentFacilityId(String hivTreatmentFacilityId) {
        this.hivTreatmentFacilityId = hivTreatmentFacilityId;
    }

    public String getTreatmentId() {
        treatmentId=BeneficiaryManager.getTreatmentId(treatmentId, currentHivStatus, enrolledOnTreatment);
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHhUniqueId() {
        return hhUniqueId;
    }

    public void setHhUniqueId(String hhUniqueId) {
        this.hhUniqueId = hhUniqueId;
    }

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }

    public String getOvcId() {
        return ovcId;
    }

    public void setOvcId(String ovcId) {
        this.ovcId = ovcId;
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

    public int getSourceOfInfo() {
        return sourceOfInfo;
    }

    public void setSourceOfInfo(int sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMainVulnerabilityStatusName() 
    {
        mainVulnerabilityStatusName=VulnerabilityStatusManager.getVulnerabilityStatusName(mainVulnerabilityStatusCode);
        return mainVulnerabilityStatusName;
    }

    public String getVulnerabilityStatusCode() {
        return vulnerabilityStatusCode;
    }

    public void setVulnerabilityStatusCode(String vulnerabilityStatusCode) {
        this.vulnerabilityStatusCode = vulnerabilityStatusCode;
    }

    public double getWeight() {
        return weight;
    }

    public String getSelectedVulnerabilityNames() 
    {
        selectedVulnerabilityNames=VulnerabilityStatusManager.getOtherVulnerabilityStatusNames(vulnerabilityStatusCode);
        return selectedVulnerabilityNames;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getCurrentEnrollmentStatus() {
        return currentEnrollmentStatus;
    }

    public void setCurrentEnrollmentStatus(int currentEnrollmentStatus) {
        this.currentEnrollmentStatus = currentEnrollmentStatus;
    }

    /*public Date getDateOfCurrentStatus() {
        return dateOfCurrentStatus;
    }

    public void setDateOfCurrentStatus(Date dateOfCurrentStatus) {
        this.dateOfCurrentStatus = dateOfCurrentStatus;
    }*/

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

    public HouseholdEnrollment getHhe() 
    {
        if(hhe==null)
        hhe=BeneficiaryManager.getHouseholdEnrollment(hhUniqueId);
        return hhe;
    }

    public void setHhe(HouseholdEnrollment hhe) {
        this.hhe = hhe;
    }

    public AdultHouseholdMember getPrimaryCaregiver() 
    {
        if(primaryCaregiver==null)
        primaryCaregiver=BeneficiaryManager.getAdultHouseholdMember(caregiverId);
        return primaryCaregiver;
    }

    public void setPrimaryCaregiver(AdultHouseholdMember primaryCaregiver) {
        this.primaryCaregiver = primaryCaregiver;
    }

    public int getBeneficiaryType() 
    {
        if(beneficiaryType==0)
        beneficiaryType=AppConstant.OVC_TYPE_NUM;
        return beneficiaryType;
    }

    public void setBeneficiaryType(int beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }
    
    public String getFullName() 
    {
        fullName=this.getFirstName()+" "+this.getSurname();
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPointOfUpdate() {
        return pointOfUpdate;
    }

    public void setPointOfUpdate(int pointOfUpdate) {
        this.pointOfUpdate = pointOfUpdate;
    }

    public BirthCertificate getBaselineBirthCertificateObject() {
        baselineBirthCertificateObject=BirthCertificateManager.getBirthCertificate(baselineBirthRegistrationStatus);
        return baselineBirthCertificateObject;
    }

    public BirthCertificate getCurrentBirthCertificateObject() 
    {
        currentBirthCertificateObject=BirthCertificateManager.getBirthCertificate(currentBirthRegistrationStatus);
        return currentBirthCertificateObject;
    }

    public SchoolStatus getSchoolStatusObject() 
    {
        schoolStatusObject=SchoolStatusManager.getSchoolStatus(currentSchoolStatus);
        return schoolStatusObject;
    }

    public void setSchoolStatusObject(SchoolStatus schoolStatusObject) {
        this.schoolStatusObject = schoolStatusObject;
    }

    public HivStatus getBaselineHivStatusObject() {
        baselineHivStatusObject=HivPropertiesManager.getHivStatus(baselineHivStatus);
        return baselineHivStatusObject;
    }

    public HivStatus getCurrentHivStatusObject() {
        currentHivStatusObject=HivPropertiesManager.getHivStatus(currentHivStatus);
        return currentHivStatusObject;
    }
    public Date getDateOfCurrentHivStatus() {
        return dateOfCurrentHivStatus;
    }

    public void setDateOfCurrentHivStatus(Date dateOfCurrentHivStatus) {
        this.dateOfCurrentHivStatus = dateOfCurrentHivStatus;
    }
        
    public ReferralFacility getReferralFacility() 
    {
        referralFacility=ReferralFacilityManager.getReferralFacilityById(hivTreatmentFacilityId);
        return referralFacility;
    }

    
    public SchoolGrade getSchoolGradeObj() 
    {
        schoolGradeObj=bcam.getSchoolGrade(getSchoolGrade());
        return schoolGradeObj;
    }

    public School getSchoolObj() 
    {
        schoolObj=bcam.getSchool(getSchoolName());
        return schoolObj;
    }

    public SchoolStatus getSchoolStatusObj() 
    {
        schoolStatusObj=bcam.getSchoolStatus(currentSchoolStatus);
        return schoolStatusObj;
    }

    public CaregiverRelationship getCaregiverRelationshipObj() 
    {
        caregiverRelationshipObj=bcam.getCaregiverRelationship(caregiverRelationship);
        return caregiverRelationshipObj;
    }

    public SourceOfInformation getSourceOfInformationObj() 
    {
        sourceOfInformationObj=bcam.getSourceOfInformation(sourceOfInfo);
        return sourceOfInformationObj;
    }

    public Disability getDisabilityObj() {
        return disabilityObj;
    }

    public Date getDateOfCurrentBirthRegStatus() {
        return dateOfCurrentBirthRegStatus;
    }

    public void setDateOfCurrentBirthRegStatus(Date dateOfCurrentBirthRegStatus) {
        this.dateOfCurrentBirthRegStatus = dateOfCurrentBirthRegStatus;
    }

    public int getCurrentSchoolStatus() {
        return currentSchoolStatus;
    }

    public void setCurrentSchoolStatus(int currentSchoolStatus) {
        this.currentSchoolStatus = currentSchoolStatus;
    }

    public int getChildHasCasePlan() {
        return childHasCasePlan;
    }

    public void setChildHasCasePlan(int childHasCasePlan) {
        this.childHasCasePlan = childHasCasePlan;
    }

    public Date getDateCasePlanDeveloped() 
    {
        if(this.getChildHasCasePlan()==0 || this.getChildHasCasePlan()==2)
        dateCasePlanDeveloped=DateManager.getDateInstance("1900-01-01");
        return dateCasePlanDeveloped;
    }

    public void setDateCasePlanDeveloped(Date dateCasePlanDeveloped) {
        this.dateCasePlanDeveloped = dateCasePlanDeveloped;
    }

    public String getCasePlanAnswer() 
    {
        casePlanAnswer=som.getCasePlanOption(childHasCasePlan).getName();
        return casePlanAnswer;
    }

    public int getNumberOfHivRiskAssessmentRecords() {
        return numberOfHivRiskAssessmentRecords;
    }

    public void setNumberOfHivRiskAssessmentRecords(int numberOfHivRiskAssessmentRecords) {
        this.numberOfHivRiskAssessmentRecords = numberOfHivRiskAssessmentRecords;
    }

    public int getNumberOfReferralServiceRecords() {
        return numberOfReferralServiceRecords;
    }

    public void setNumberOfReferralServiceRecords(int numberOfReferralServiceRecords) {
        this.numberOfReferralServiceRecords = numberOfReferralServiceRecords;
    }

    public int getNumberOfServiceRecords() {
        return numberOfServiceRecords;
    }

    public void setNumberOfServiceRecords(int numberOfServiceRecords) {
        this.numberOfServiceRecords = numberOfServiceRecords;
    }

    public EnrollmentStatus getCurrentEnrollmentStatusObj() 
    {
        currentEnrollmentStatusObj=EnrollmentStatusObjectManager.getEnrollmentStatus(this.getCurrentEnrollmentStatus());
        return currentEnrollmentStatusObj;
    }

    public int getBaselineBirthRegistrationStatus() {
        return baselineBirthRegistrationStatus;
    }

    public void setBaselineBirthRegistrationStatus(int baselineBirthRegistrationStatus) {
        this.baselineBirthRegistrationStatus = baselineBirthRegistrationStatus;
    }

    public int getBaselineHivStatus() {
        return baselineHivStatus;
    }

    public void setBaselineHivStatus(int baselineHivStatus) {
        this.baselineHivStatus = baselineHivStatus;
    }

    public int getBaselineSchoolStatus() {
        return baselineSchoolStatus;
    }

    public void setBaselineSchoolStatus(int baselineSchoolStatus) {
        this.baselineSchoolStatus = baselineSchoolStatus;
    }

    public BeneficiaryAttributeManager getBcam() {
        return bcam;
    }

    public void setBcam(BeneficiaryAttributeManager bcam) {
        this.bcam = bcam;
    }

    public String getCommunityWorkerName() {
        return communityWorkerName;
    }

    public void setCommunityWorkerName(String communityWorkerName) {
        this.communityWorkerName = communityWorkerName;
    }

    public int getCurrentBirthRegistrationStatus() {
        return currentBirthRegistrationStatus;
    }

    public void setCurrentBirthRegistrationStatus(int currentBirthRegistrationStatus) {
        this.currentBirthRegistrationStatus = currentBirthRegistrationStatus;
    }

    public int getCurrentHivStatus() {
        return currentHivStatus;
    }

    public void setCurrentHivStatus(int currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
    }

    public Date getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(Date dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public Date getDateOfBaselineHivStatus() {
        return dateOfBaselineHivStatus;
    }

    public void setDateOfBaselineHivStatus(Date dateOfBaselineHivStatus) {
        this.dateOfBaselineHivStatus = dateOfBaselineHivStatus;
    }

    public HivStatus getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(HivStatus hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getSchoolGrade() {
        return schoolGrade;
    }

    public void setSchoolGrade(String schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getAbuseAndExploitation() {
        return abuseAndExploitation;
    }

    public void setAbuseAndExploitation(int abuseAndExploitation) {
        this.abuseAndExploitation = abuseAndExploitation;
    }

    public int getCare() {
        return care;
    }

    public void setCare(int care) {
        this.care = care;
    }

    public int getDevelopmentAndPerformance() {
        return developmentAndPerformance;
    }

    public void setDevelopmentAndPerformance(int developmentAndPerformance) {
        this.developmentAndPerformance = developmentAndPerformance;
    }

    public int getEducationAndWork() {
        return educationAndWork;
    }

    public void setEducationAndWork(int educationAndWork) {
        this.educationAndWork = educationAndWork;
    }

    public int getEmotionalHealth() {
        return emotionalHealth;
    }

    public void setEmotionalHealth(int emotionalHealth) {
        this.emotionalHealth = emotionalHealth;
    }

    public int getFoodSecurity() {
        return foodSecurity;
    }

    public void setFoodSecurity(int foodSecurity) {
        this.foodSecurity = foodSecurity;
    }

    public int getHealthCareServices() {
        return healthCareServices;
    }

    public void setHealthCareServices(int healthCareServices) {
        this.healthCareServices = healthCareServices;
    }

    public int getLegalProtection() {
        return legalProtection;
    }

    public void setLegalProtection(int legalProtection) {
        this.legalProtection = legalProtection;
    }

    public int getNutritionAndGrowth() {
        return nutritionAndGrowth;
    }

    public void setNutritionAndGrowth(int nutritionAndGrowth) {
        this.nutritionAndGrowth = nutritionAndGrowth;
    }

    public int getShelter() {
        return shelter;
    }

    public void setShelter(int shelter) {
        this.shelter = shelter;
    }

    public int getSocialBehaviour() {
        return socialBehaviour;
    }

    public void setSocialBehaviour(int socialBehaviour) {
        this.socialBehaviour = socialBehaviour;
    }

    public int getWellness() {
        return wellness;
    }

    public void setWellness(int wellness) {
        this.wellness = wellness;
    }

    public String getBaselineAgeUnitName() 
    {
        baselineAgeUnitName=SingleOptionManager.getAgeUnit(ageUnitAtBaseline).getName();
        return baselineAgeUnitName;
    }

    public String getCurrentAgeUnitName() 
    {
        currentAgeUnitName=SingleOptionManager.getAgeUnit(currentAgeUnit).getName();
        return currentAgeUnitName;
    }

    public void setDisplayDateOfEnrollment(String displayDateOfEnrollment) {
        this.displayDateOfEnrollment = displayDateOfEnrollment;
    }

    public String getDisplayDateOfEnrollment() 
    {
        if(displayDateOfEnrollment==null && this.getDateOfEnrollment() !=null)
        {
            displayDateOfEnrollment=DateManager.getMthDayYearFromMySqlDate(DateManager.convertDateToString(getDateOfEnrollment(), ovcId));
        }
        return displayDateOfEnrollment;
    }

    public CommunityWorker getCommunityWorker() {
        return communityWorker=BeneficiaryManager.getCommunityWorker(communityWorkerName);
    }

    public int getViralLoad() 
    {
        if(viralLoad==0)
        viralLoad=BeneficiaryManager.getViralLoad(beneficiaryId, currentHivStatus, enrolledOnTreatment);
        return viralLoad;
    }

    public void setViralLoad(int viralLoad) {
        this.viralLoad = viralLoad;
    }

    public int getCurrentNutritionalStatus() {
        return currentNutritionalStatus;
    }

    public void setCurrentNutritionalStatus(int currentNutritionalStatus) {
        this.currentNutritionalStatus = currentNutritionalStatus;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }

    public Date getDateOfCurrentEnrollmentStatus() {
        return dateOfCurrentEnrollmentStatus;
    }

    public void setDateOfCurrentEnrollmentStatus(Date dateOfCurrentEnrollmentStatus) {
        this.dateOfCurrentEnrollmentStatus = dateOfCurrentEnrollmentStatus;
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

    public NutritionStatus getCurrentNutritionStatus() {
        return currentNutritionStatus=BeneficiaryManager.getNutritionStatus(this);
    }

    public int getEnrollmentSetting() {
        return enrollmentSetting;
    }

    public void setEnrollmentSetting(int enrollmentSetting) {
        this.enrollmentSetting = enrollmentSetting;
    }

    public String getEnrollmentSettingName() {
        return enrollmentSettingName=BeneficiaryManager.getEnrollmentSettingName(enrollmentSetting);
    }
    
}
