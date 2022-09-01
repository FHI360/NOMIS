/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;


import com.nomis.ovc.business.SchoolGrade;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class SchoolGradeDaoImpl implements SchoolGradeDao
{
    Session session;
    Transaction tx;
    List list;
    public List getAllSchoolGrades() throws Exception
    {
        List list=null;
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery("from SchoolGrade grade order by grade.gradeName").list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
        return list;
    }
    public SchoolGrade getSchoolGradeByName(String gradeName) throws Exception
    {
        SchoolGrade grade=null;
        try
        {
            if(gradeName !=null)
            gradeName=gradeName.trim();
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery("from SchoolGrade grade where TRIM(grade.gradeName)= :gdn").setString("gdn", gradeName).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                grade=(SchoolGrade)list.get(0);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
        return grade;
    }
    public SchoolGrade getSchoolGrade(String id) throws Exception
    {
        SchoolGrade grade=null;
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery("from SchoolGrade grade where grade.id= :sid").setString("sid", id).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                grade=(SchoolGrade)list.get(0);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
        return grade;
    }
    public void saveSchoolGrade(SchoolGrade grade) throws Exception
    {
        try
        {
            grade.setId(getUniqueRecordId());
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.save(grade);
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
    }
    public void updateSchoolGrade(SchoolGrade grade) throws Exception
    {
        try
        {
            if(grade !=null && grade.getId() !=null)
            {
                session=HibernateUtil.getSession();
                tx=session.beginTransaction();
                session.update(grade);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
    }
    public void markForDelete(SchoolGrade grade) throws Exception
    {
        try
        {
            if(grade !=null && grade.getId() !=null)
            {
                grade.setMarkForDelete(1);
                session=HibernateUtil.getSession();
                tx=session.beginTransaction();
                session.update(grade);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
    }
    public void deleteSchoolGrade(SchoolGrade grade) throws Exception
    {
        try
        {
            if(grade !=null && grade.getId() !=null)
            {
                session=HibernateUtil.getSession();
                tx=session.beginTransaction();
                session.delete(grade);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
    }
    public String getUniqueRecordId()
    {
        AppUtility appUtil=new AppUtility();
        String uniqueId=appUtil.generateUniqueId();
        try
        {
            if(getSchoolGrade(uniqueId) !=null)
            getUniqueRecordId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public SchoolGrade getDefaultSchoolGrade(String userName) throws Exception
    {
        SchoolGrade grade=getSchoolGradeByName("N/A");
        if(grade==null)
        {
            grade=createDefaultSchoolGrade(userName);  
        }
        return grade;
    }
    public SchoolGrade createDefaultSchoolGrade(String userName) throws Exception
    {
        SchoolGrade grade=new SchoolGrade();
        grade.setGradeLevel(0);
        grade.setGradeName("N/A");
        grade.setId("xxxxxxxxxxx");
        grade.setDateCreated(DateManager.getCurrentDateInstance());
        grade.setLastModifiedDate(DateManager.getCurrentDateInstance());
        grade.setRecordedBy(userName);
        this.saveSchoolGrade(grade);
        return grade;
    }
    public SchoolGrade createSchoolGrade(String name,String userName) throws Exception
    {
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        //SchoolGrade grade=null;
        if(name==null || name.trim().length()==0)
        {
            name="N/A";
        }
            SchoolGrade grade=getSchoolGradeByName(name);
            if(grade==null)
            {
                grade=new SchoolGrade();
                grade.setGradeName(name);
                grade.setGradeLevel(0);
                grade.setDateCreated(currentDate);
                grade.setLastModifiedDate(currentDate);
                grade.setRecordedBy(userName);
                grade.setId(getUniqueRecordId());
                saveSchoolGrade(grade);
            
            }
        return grade;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
