/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.CaregiverAccessToEmergencyFund;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface CaregiverAccessToEmergencyFundDao 
{
    public void saveCaregiverAccessToEmergencyFund(CaregiverAccessToEmergencyFund ceaf) throws Exception;
    public void updateCaregiverAccessToEmergencyFund(CaregiverAccessToEmergencyFund ceaf) throws Exception;
    public void markForDelete(CaregiverAccessToEmergencyFund ceaf) throws Exception;
    public void deleteCaregiverAccessToEmergencyFund(CaregiverAccessToEmergencyFund ceaf) throws Exception;
    public CaregiverAccessToEmergencyFund getCaregiverAccessToEmergencyFund(int recordId) throws Exception;
    public CaregiverAccessToEmergencyFund getCaregiverAccessToEmergencyFund(String beneficiaryId,Date dateOfAssessment) throws Exception;
    public List getCaregiverAccessToEmergencyFund(String beneficiaryId) throws Exception;
    public List getCaregiverAccessToEmergencyFundRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public List getCaregiverAccessToEmergencyFundRecords(ReportParameterTemplate rpt) throws Exception;
    public int getNumberOfCaregiversThatHadAccessToEmergencyFund(ReportParameterTemplate rpt,int startAge, int endAge,String startDate, String endDate,int enrollmentStatus,String sex) throws Exception;
    public List getListOfCaregiversThatHadAccessToEmergencyFund(ReportParameterTemplate rpt,int startAge, int endAge,String startDate, String endDate,int enrollmentStatus,String sex) throws Exception;
    public void changeBeneficiaryId(String oldBeneficiaryId, String newBeneficiaryId) throws Exception;
    public int getNumberOfCaregiversThatHadAccessToEmergencyFundForDatim(ReportParameterTemplate rpt,int startAge, int endAge,String startDate, String endDate,int enrollmentStatus,String sex,int unexpectedExpenditureValue,int abilityToAccessMoneyToPay) throws Exception;
}
