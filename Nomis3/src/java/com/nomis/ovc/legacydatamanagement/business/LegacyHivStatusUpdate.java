/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class LegacyHivStatusUpdate implements Serializable 
{
    LegacyBeneficiaryResourceManager beneficiary=new LegacyBeneficiaryResourceManager();
    private String recordId;
    private String clientId;
    private String hivStatus;
    private String recordStatus;
    private String dateOfCurrentStatus;
    private String lastHivTrackingDate;
    private String dateModified;
    private String clientType;
    private String clientEnrolledInCare;
    private String enrolledOnART;
    private String organizationClientIsReferred;
    private String pointOfUpdate;
    private String recordedBy;
    private int markedForDelete;
    private String treatmentId;
    private Date dateEnrolledOnTreatment;
    private LegacyReferralDirectory facility;

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDateOfCurrentStatus() {
        return dateOfCurrentStatus;
    }

    public void setDateOfCurrentStatus(String dateOfCurrentStatus) {
        this.dateOfCurrentStatus = dateOfCurrentStatus;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(String hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    
    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientEnrolledInCare() {
        return clientEnrolledInCare;
    }

    public void setClientEnrolledInCare(String clientEnrolledInCare) {
        this.clientEnrolledInCare = clientEnrolledInCare;
    }

    public String getOrganizationClientIsReferred() {
        return organizationClientIsReferred;
    }

    public void setOrganizationClientIsReferred(String organizationClientIsReferred) {
        this.organizationClientIsReferred = organizationClientIsReferred;
    }

    public String getPointOfUpdate() {
        return pointOfUpdate;
    }

    public void setPointOfUpdate(String pointOfUpdate) {
        this.pointOfUpdate = pointOfUpdate;
    }

    public String getEnrolledOnART() {
        return enrolledOnART;
    }

    public void setEnrolledOnART(String enrolledOnART) {
        this.enrolledOnART = enrolledOnART;
    }

    public String getLastHivTrackingDate() {
        return lastHivTrackingDate;
    }

    public void setLastHivTrackingDate(String lastHivTrackingDate) {
        this.lastHivTrackingDate = lastHivTrackingDate;
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

    public Date getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(Date dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public LegacyReferralDirectory getFacility() {
        if(facility==null)
        {
            //facility=beneficiary.getReferralDirectory(organizationClientIsReferred);
        }
        return facility;
    }

    public void setFacility(LegacyReferralDirectory facility) {
        this.facility = facility;
    }
        
}
