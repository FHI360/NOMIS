/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.EnrollmentStatusHistory;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface EnrollmentStatusHistoryDao 
{
    public void saveEnrollmentStatusHistory(EnrollmentStatusHistory esh) throws Exception;
    public void updateEnrollmentStatusHistory(EnrollmentStatusHistory esh) throws Exception;
    public void markForDelete(EnrollmentStatusHistory esh) throws Exception;
    public void deleteEnrollmentStatusHistory(EnrollmentStatusHistory esh) throws Exception;
    public EnrollmentStatusHistory getEnrollmentStatusHistory(int recordId) throws Exception;
    public EnrollmentStatusHistory getEnrollmentStatusHistory(String beneficiaryId, Date dateOfEnrollmentStatus) throws Exception;
    public List getEnrollmentStatusHistory(String beneficiaryId) throws Exception;
    public void changeBeneficiaryIdInEnrollmentStatusHistory(String oldOvcId, String newOvcId) throws Exception;
    public void updateHivStatus(String beneficiaryId,int hivStatus,Date dateOfHivStatus,int age,int ageUnit) throws Exception;
    public List getEnrollmentStatusHistory(String beneficiaryId, Date startDate,Date endDate) throws Exception;
}
