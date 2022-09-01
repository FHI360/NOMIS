/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.HouseholdCareplan;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface HouseholdCareplanDao 
{
    public void saveHouseholdCareplan(HouseholdCareplan hcp) throws Exception;
    public void updateHouseholdCareplan(HouseholdCareplan hcp) throws Exception;
    public void markForDelete(HouseholdCareplan hcp) throws Exception;
    public void deleteHouseholdCareplan(HouseholdCareplan hcp) throws Exception;
    public HouseholdCareplan getHouseholdCareplan(int recordId) throws Exception;
    public HouseholdCareplan getHouseholdCareplan(String beneficiaryId,Date careplanDate) throws Exception;
    public List getHouseholdCareplan(String ovcId) throws Exception;
    public List getHouseholdCareplanRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public HouseholdCareplan getAllRecordIdsWithZeroValuesFromHouseholdCareplan() throws Exception;
    public int getSumOfRecordIdsFromHouseholdCareplan() throws Exception;
}
