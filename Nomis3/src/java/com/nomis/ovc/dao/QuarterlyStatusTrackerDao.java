/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.EnrollmentStatusHistory;
import com.nomis.ovc.business.QuarterlyStatusTracker;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface QuarterlyStatusTrackerDao 
{
    public void saveQuarterlyStatusTracker(QuarterlyStatusTracker esh) throws Exception;
    public void updateQuarterlyStatusTracker(QuarterlyStatusTracker esh) throws Exception;
    public void markForDelete(QuarterlyStatusTracker esh) throws Exception;
    public void deleteQuarterlyStatusTracker(QuarterlyStatusTracker esh) throws Exception;
    public QuarterlyStatusTracker getQuarterlyStatusTracker(int recordId) throws Exception;
    public QuarterlyStatusTracker getQuarterlyStatusTracker(String beneficiaryId, Date dateOfEnrollmentStatus) throws Exception;
    public List getQuarterlyStatusTracker(String beneficiaryId) throws Exception;
    public void changeBeneficiaryIdInQuarterlyStatusTracker(String oldOvcId, String newOvcId) throws Exception;
    //public void updateHivStatus(String beneficiaryId,int hivStatus,Date dateOfHivStatus,int age,String ageUnit) throws Exception;
    public List getQuarterlyStatusTracker(String beneficiaryId, Date startDate,Date endDate) throws Exception;
    public void saveEnrollmentStatusHistory(EnrollmentStatusHistory esh) throws Exception;
    public List getQuarterlyStatusTrackerAfterStatusDate(String beneficiaryId, Date startDate) throws Exception;
    public void updateQuarterlyStatusTrackerByRecordId(QuarterlyStatusTracker qst) throws Exception;
}
