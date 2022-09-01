/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;


import com.nomis.ovc.business.School;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface SchoolDao 
{
    public void saveSchool(School school) throws Exception;
    public void updateSchool(School school) throws Exception;
    public void markForDelete(School school) throws Exception;
    public void deleteSchool(School school) throws Exception;
    public List getAllSchools() throws Exception;
    public School getSchool(String schoolId) throws Exception;
    public List getSchoolsByOrgUnit(String orgunitId) throws Exception;
    public School createSchool(String name,String orgUnitId,String userName,int type) throws Exception;
    public School getSchoolsBySchoolName(String schoolName) throws Exception;
    public List getSchoolsByOrgUnit(ReportParameterTemplate rpt) throws Exception;
}
