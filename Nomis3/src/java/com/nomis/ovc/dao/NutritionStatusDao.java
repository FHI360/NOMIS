/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.NutritionStatus;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface NutritionStatusDao 
{
    //public void saveNutritionStatus(NutritionStatus ns) throws Exception;
    //public void updateNutritionStatus(NutritionStatus ns) throws Exception;
    public void saveOrUpdateNutritionStatus(NutritionStatus ns) throws Exception;
    public void markForDelete(NutritionStatus ns) throws Exception;
    public void deleteNutritionStatus(NutritionStatus ns) throws Exception;
    public NutritionStatus getNutritionStatus(String ovcId) throws Exception;
    public List getNutritionStatusRecordsForExport(ReportParameterTemplate rpt) throws Exception;
    public int getNumberOfOvcAssessedByNutritionStatus(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,int enrollmentStatus,int nutritionStatus,String sex) throws Exception;
    public List getListOfOvcAssessedByNutritionStatus(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,int enrollmentStatus,int nutritionStatus,String sex) throws Exception;
    public List getNutritionStatusWithoutAgeAtAssessment() throws Exception;
}
