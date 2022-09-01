/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.CareplanAchievementChecklist;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface CareplanAchievementChecklistDao 
{
    public void saveCareplanAchievementChecklist(CareplanAchievementChecklist cpa) throws Exception;
    public void updateCareplanAchievementChecklist(CareplanAchievementChecklist cpa) throws Exception;
    public void markForDelete(CareplanAchievementChecklist cpa) throws Exception;
    public void deleteCareplanAchievementChecklist(CareplanAchievementChecklist cpa) throws Exception;
    public List getAllCareplanAchievementChecklists() throws Exception;
    public CareplanAchievementChecklist getCareplanAchievementChecklist(int id) throws Exception;
    public List getCareplanAchievementChecklistsPerHousehold(String hhUniqueId) throws Exception;
    public CareplanAchievementChecklist getCareplanAchievementChecklist(String hhUniqueId,Date dateOfAssessment) throws Exception;
    public List getCareplanAchievementChecklists(String additionalQueryCriteria) throws Exception;
    public int getGraduationScore(CareplanAchievementChecklist cpa) throws Exception;
    public void saveCareplanAchievementChecklistForImport(CareplanAchievementChecklist cpa) throws Exception;
    public void updateCareplanAchievementChecklistForImport(CareplanAchievementChecklist cpa) throws Exception;
    public List getCareplanAchievementChecklistsNotGraduated(String additionalQueryCriteria) throws Exception;
    public List getCareplanAchievementChecklistRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception;
    public List getCareplanAchievementChecklistRecords(ReportParameterTemplate rpt) throws Exception;
}
