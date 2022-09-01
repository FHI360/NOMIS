/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.ReferralFacilityManager;
import com.nomis.ovc.util.SingleOptionManager;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class FacilityOvcOffer implements Serializable
{
    private String clientUniqueId;
    private String treatmentId;
    private Date dateEnrolledOnTreatment;
    private String clientFirstName;
    private String clientSurname;
    private String hivTreatmentFacilityId;
    private String nameOfPersonToShareContactWith;
    private int shareContactQuestion;
    private String shareContactAnswer;
    private String caregiverFirstName;
    private String caregiverSurname;
    private Date dateCaregiverSigned;
    private String facilityStaffFirstName;
    private String facilityStaffSurname;
    private Date dateFacilityStaffSigned;
    private String cboId;
    private String partnerCode;
    private String partnerName;
    private Date lastModifiedDate;
    private int markedForDelete;
    private int age;
    private int ageUnit;
    private String ageUnitName;
    private String sex;
    private String userName;
    
    private String level2OuId;
    private String level3OuId;
    private String level4OuId;
    int serialNo=0;
    String rowColor="#FFFFFF";
    private OrganizationUnit level2Ou;
    private OrganizationUnit level3Ou;
    private OrganizationUnit level4Ou;
    private CommunityBasedOrganization cbo;
    private ReferralFacility referralFacility;
    OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();

    public String getCaregiverFirstName() {
        return caregiverFirstName;
    }

    public void setCaregiverFirstName(String caregiverFirstName) {
        this.caregiverFirstName = caregiverFirstName;
    }

    public String getCaregiverSurname() {
        return caregiverSurname;
    }

    public void setCaregiverSurname(String caregiverSurname) {
        this.caregiverSurname = caregiverSurname;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }
    
    public Date getDateCaregiverSigned() {
        return dateCaregiverSigned;
    }

    public void setDateCaregiverSigned(Date dateCaregiverSigned) {
        this.dateCaregiverSigned = dateCaregiverSigned;
    }

    public Date getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(Date dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public Date getDateFacilityStaffSigned() {
        return dateFacilityStaffSigned;
    }

    public void setDateFacilityStaffSigned(Date dateFacilityStaffSigned) {
        this.dateFacilityStaffSigned = dateFacilityStaffSigned;
    }

    public String getFacilityStaffFirstName() {
        return facilityStaffFirstName;
    }

    public void setFacilityStaffFirstName(String facilityStaffFirstName) {
        this.facilityStaffFirstName = facilityStaffFirstName;
    }

    public String getFacilityStaffSurname() {
        return facilityStaffSurname;
    }

    public void setFacilityStaffSurname(String facilityStaffSurname) {
        this.facilityStaffSurname = facilityStaffSurname;
    }

    public String getHivTreatmentFacilityId() {
        return hivTreatmentFacilityId;
    }

    public void setHivTreatmentFacilityId(String hivTreatmentFacilityId) {
        this.hivTreatmentFacilityId = hivTreatmentFacilityId;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getNameOfPersonToShareContactWith() {
        return nameOfPersonToShareContactWith;
    }

    public void setNameOfPersonToShareContactWith(String nameOfPersonToShareContactWith) {
        this.nameOfPersonToShareContactWith = nameOfPersonToShareContactWith;
    }

    public int getShareContactQuestion() {
        return shareContactQuestion;
    }

    public void setShareContactQuestion(int shareContactQuestion) {
        this.shareContactQuestion = shareContactQuestion;
    }

    public String getShareContactAnswer() {
        return shareContactAnswer=SingleOptionManager.getSingleChoiceOption(shareContactQuestion).getName();
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getClientUniqueId() {
        return clientUniqueId;
    }

    public void setClientUniqueId(String clientUniqueId) {
        this.clientUniqueId = clientUniqueId;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(int ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
    }

    public String getLevel3OuId() {
        return level3OuId;
    }

    public void setLevel3OuId(String level3OuId) {
        this.level3OuId = level3OuId;
    }

    public String getLevel4OuId() {
        return level4OuId;
    }

    public void setLevel4OuId(String level4OuId) {
        this.level4OuId = level4OuId;
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
        level4Ou=ouam.getOrganizationUnitForReports(getLevel4OuId());
        return level4Ou;
    }
    public String getAgeUnitName() 
    {
        ageUnitName=SingleOptionManager.getAgeUnit(ageUnit).getName();
        return ageUnitName;
    }
    public ReferralFacility getReferralFacility() 
    {
        referralFacility=ReferralFacilityManager.getReferralFacilityById(hivTreatmentFacilityId);
        return referralFacility;
    }

    public String getPartnerName() 
    {
        partnerName=ouam.getPartnerName(partnerCode);
        return partnerName;
    }
    
}
