/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import com.nomis.ovc.business.School;
import com.nomis.ovc.business.SchoolGrade;
import com.nomis.ovc.dao.DaoUtility;


/**
 *
 * @author smomoh
 */
public class SchoolManager 
{
    public School getSchool(String schoolId)
    {
        System.err.println("School id in School manager is "+schoolId);
        DaoUtility util=new DaoUtility();
        School school=null;
        try
        {
           school=util.getSchoolDaoInstance().getSchool(schoolId);
           if(school==null)
           {
               school=new School();
               school.setId(schoolId);
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return school;
    }
    public SchoolGrade getSchoolGrade(String gradeId)
    {
        DaoUtility util=new DaoUtility();
        SchoolGrade grade=null;
        try
        {
           grade=util.getSchoolGradeDaoInstance().getSchoolGrade(gradeId);
           if(grade==null)
           {
               grade=new SchoolGrade();
               grade.setId(gradeId);
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return grade;
    }
}
