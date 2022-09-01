/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.BeneficiaryType;
import com.nomis.ovc.util.EnrollmentStatus;
import com.nomis.ovc.util.EnrolledOnTreatment;
import com.nomis.ovc.util.HivStatus;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public interface Beneficiary extends Serializable
{
    public void setTreatmentId(String treatmentId);
    public String getTreatmentId();
    public int getAgeAtBaseline();
    public void setAgeAtBaseline(int ageAtBaseline);
    public int getBaselineHivStatus();
    public void setBaselineHivStatus(int baselineHivStatus);
    public int getCurrentHivStatus();
    public void setCurrentHivStatus(int currentHivStatus);
    public void setDateOfCurrentHivStatus(Date dateOfCurrentHivStatus);
    public Date getDateOfBaselineHivStatus();
    public void setDateOfBaselineHivStatus(Date dateOfBaselineHivStatus);
    public Date getDateOfCurrentHivStatus();
    public int getEnrolledOnTreatment();
    public void setEnrolledOnTreatment(int enrolledOnTreatment);
    public Date getDateEnrolledOnTreatment();
    public void setDateEnrolledOnTreatment(Date dateEnrolledOnTreatment);
    public String getHivTreatmentFacilityId();
    public void setHivTreatmentFacilityId(String hivTreatmentFacilityId);
    public int getAgeUnitAtBaseline();
    public void setAgeUnitAtBaseline(int ageUnitAtBaseline);
    public String getBeneficiaryId();
    public void setBeneficiaryId(String beneficiaryId);
    public int getBeneficiaryType();
    public BeneficiaryType getBeneficiaryTypeObject();
    public int getCurrentAge();
    public void setCurrentAge(int currentAge);
    public int getCurrentAgeUnit();
    public void setCurrentAgeUnit(int currentAgeUnit);
    public Date getDateOfBirth();
    public void setDateOfBirth(Date dateOfBirth);
    public Date getDateOfEnrollment();
    public void setDateOfEnrollment(Date dateOfEnrollment);
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getPhoneNumber();
    public void setPhoneNumber(String phoneNumber);
    public String getSex();
    public void setSex(String sex);
    public String getSurname();
    public void setSurname(String surname);
    public int getViralLoad();
    public void setViralLoad(int viralLoad);
    public HouseholdEnrollment getHhe();
    public void setHhe(HouseholdEnrollment hhe);
    public CommunityWorker getCommunityWorker();   
    public int getCurrentEnrollmentStatus();
    public void setCurrentEnrollmentStatus(int currentEnrollmentStatus); 
    public Date getDateOfCurrentEnrollmentStatus();
    public void setDateOfCurrentEnrollmentStatus(Date dateOfCurrentEnrollmentStatus);
    public ReferralFacility getReferralFacility();
    public EnrolledOnTreatment getEnrolledOnTreatmentObject();
    public EnrollmentStatus getCurrentEnrollmentStatusObj();
    public HivStatus getCurrentHivStatusObject();
    public HivStatus getBaselineHivStatusObject();
    public String getBaselineAgeUnitName();
    public String getCurrentAgeUnitName();
    public String getBeneficiaryTypeName();
    public boolean isCurrentlyEnrolled();
}
