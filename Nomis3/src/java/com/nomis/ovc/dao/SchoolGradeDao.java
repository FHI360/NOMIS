/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.SchoolGrade;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface SchoolGradeDao 
{
    public void saveSchoolGrade(SchoolGrade grade) throws Exception;
    public void updateSchoolGrade(SchoolGrade grade) throws Exception;
    public void markForDelete(SchoolGrade grade) throws Exception;
    public void deleteSchoolGrade(SchoolGrade grade) throws Exception;
    public List getAllSchoolGrades() throws Exception;
    public SchoolGrade getSchoolGrade(String id) throws Exception;
    public SchoolGrade getSchoolGradeByName(String gradeName) throws Exception;
    public SchoolGrade createSchoolGrade(String name,String userName) throws Exception;
}
