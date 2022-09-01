/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.UserActivityTracker;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class UserActivityTrackerDaoImpl implements UserActivityTrackerDao
{
    Session session;
   Transaction tx;
   SessionFactory sessions;
   public List getDistinctUserActions() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct uat.userAction from UserActivityTracker uat").list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list;
    }
   public List getDistinctUserNames() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct uat.userName from UserActivityTracker uat").list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list;
    }
   public UserActivityTracker getUserActivityTracker(int recordId) throws Exception
   {
       return null;
   }
    public UserActivityTracker getUserActivityTracker(String userName, String dateAndTime) throws Exception
    {
        return null;
    }
    public List getUserActivityTrackerByUserName(String userName) throws Exception
    {
        return null;
    }
    public List getUserActivityTrackerByUserAction(String userAction) throws Exception
    {
        return null;
    }
    public List getUserActivityTrackerByUserNameAndAction(String userName,String userAction) throws Exception
    {
        List list=null;
        try
        {
            String additionalQuery=SubQueryGenerator.getUserActivitySubQuery(userName, userAction);
            String query="from UserActivityTracker uat where uat.userName is not null "+additionalQuery;
            System.err.println("Query in getUserActivityTrackerByUserNameAndAction is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list;
    }
    public List getUserActivityTrackerByDate(Date dateCreated) throws Exception
    {
        return null;
    }
    public List getAllUserActivityTrackers() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from UserActivityTracker uat").list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list;
    }
   public void saveUserActivityTracker(UserActivityTracker uat) throws Exception
   {
       try
        {
            if(uat !=null && uat.getUserAction() !=null && uat.getUserName() !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(uat);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
   }
   public void updateUserActivityTracker(UserActivityTracker uat) throws Exception
   {
       try
        {
            if(uat !=null && uat.getUserAction() !=null && uat.getUserName() !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(uat);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
   }
   public void markForDelete(UserActivityTracker uat) throws Exception
   {
       try
        {
            if(uat !=null && uat.getUserAction() !=null && uat.getUserName() !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(uat);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
   }
   public void deleteUserActivityTracker(UserActivityTracker uat) throws Exception
   {
       try
        {
            if(uat !=null && uat.getUserAction() !=null && uat.getUserName() !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(uat);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
   }
   public void closeSession(Session session)
   {
       if(session !=null && session.isOpen())
       {
           session.close();
       }
   }
}
