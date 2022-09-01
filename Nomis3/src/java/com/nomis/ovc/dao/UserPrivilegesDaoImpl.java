/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.UserPrivilege;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class UserPrivilegesDaoImpl implements UserPrivilegesDao
{
        Session session;
    Transaction tx;
    
    public List getAllUserPrivileges() throws Exception
    {
        List list=null;
        try
        {
        session=HibernateUtil.getSession();
        tx=session.beginTransaction();
        list=session.createQuery("from UserPrivilege ur").list();
        tx.commit();
        session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
        return list;
    }
    public void saveUserPrivileges(UserPrivilege ur) throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.save(ur);
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
    }
    public void updateUserPrivileges(UserPrivilege ur) throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.update(ur);
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
    }
    public void deleteUserPrivileges(UserPrivilege ur) throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.delete(ur);
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
    }
}
