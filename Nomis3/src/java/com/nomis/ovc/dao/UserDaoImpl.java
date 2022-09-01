/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class UserDaoImpl implements UserDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    
   public void saveUser(User user) throws Exception
   {
       try
        {
            System.err.println("Inside saveUser(User)");
            if(user !=null)
            {
                if(getUser(user.getUsername())==null)
                {
                    System.err.println("About to save user with username "+user.getUsername());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(user);
                    tx.commit();
                    closeSession(session);
                    System.err.println("user with username "+user.getUsername()+" saved");
                }
                else
                {
                    updateUser(user);
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
   }
    public void updateUser(User user) throws Exception
    {
        try
        {
            if(user !=null && getUser(user.getUsername())!=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(user);
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
    public void deleteUser(User user) throws Exception
    {
        try
        {
            if(user !=null && getUser(user.getUsername())!=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(user);
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
    public User getUser(String username, String password) throws Exception
    {
        User user=null;
        try
        {
            if(username !=null)
            username=username.trim();
            if(password !=null)
            password=password.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from User user where TRIM(user.username)=:usn and TRIM(user.password)=:pwd").setString("usn", username).setString("pwd", password).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                user=(User)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return user;
    }
    public User getUser(String username) throws Exception
    {
        User user=null;
        try
        {
            if(username !=null)
            username=username.trim();
            
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from User user where TRIM(user.username)=:usn").setString("usn", username).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                user=(User)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return user;
    }
    public List getAllUsers() throws Exception
    {
        List list =null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from User user").list();
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
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
