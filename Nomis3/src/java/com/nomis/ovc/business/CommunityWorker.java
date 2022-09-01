/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import com.nomis.operationsManagement.EnrollmentStatusObjectManager;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class CommunityWorker implements Serializable
{
    private String communityWorkerId;
    private String firstName;
    private String surname;
    private String fullName;
    private String sex;
    private String designation;
    private String level4OuId;
    private Date dateCreated;
    private Date lastModifiedDate;
    private String recordedBy;
    private int enrollmentStatus;
    private String enrollmentStatusName;
    

    public String getCommunityWorkerId() {
        return communityWorkerId;
    }

    public void setCommunityWorkerId(String communityWorkerId) {
        this.communityWorkerId = communityWorkerId;
    }
   
    
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLevel4OuId() {
        return level4OuId;
    }

    public void setLevel4OuId(String level4OuId) {
        this.level4OuId = level4OuId;
    }

    public int getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(int enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getEnrollmentStatusName() {
        return EnrollmentStatusObjectManager.getEnrollmentStatus(enrollmentStatus).getStatusName();
    }

    public String getFullName() {
        return fullName=getFirstName()+" "+getSurname();
    }
    
}
