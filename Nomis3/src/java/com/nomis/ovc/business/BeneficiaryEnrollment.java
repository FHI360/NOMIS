/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class BeneficiaryEnrollment implements Serializable
{
    private String hhUniqueId;
    private String beneficiaryId;
    private String legacyId;
    private int beneficiaryType;
    private String enrollmentId;
    private Date dateOfEnrollment;
    private Date dateOfBirth;
    private String firstName;
    private String surname;
    private String sex;
    private int ageAtBaseline;
    private int ageUnitAtBaseline;
    private int currentAge;
    private int currentAgeUnit;

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
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(int beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
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

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
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
    
}
