/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.UserRole;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class UserRoleDaoImpl implements UserRoleDao
{
    Session session;
    Transaction tx;
    public List getAllUserRoles() throws Exception
    {
        List list=null;
        try
        {
        session=HibernateUtil.getSession();
        tx=session.beginTransaction();
        list=session.createQuery("from UserRole ur").list();
        tx.commit();
        session.close();
        }
        catch(Exception ex)
        {
            session.close();
        }
        return list;
    }
    public UserRole getUserRole(String id) throws Exception
    {
        List list=null;
        UserRole ur=null;
        try
        {
        session=HibernateUtil.getSession();
        tx=session.beginTransaction();
        list=session.createQuery("from UserRole ur where ur.roleId=:rId").setString("rId", id).list();
        tx.commit();
        session.close();
        }
        catch(Exception ex)
        {
            session.close();
        }
        if(list !=null && !list.isEmpty())
        {
            ur=(UserRole)list.get(0);
        }
        return ur;
    }
    public UserRole getUserRoleByRoleName(String roleName) throws Exception
    {
        List list=null;
        UserRole ur=null;
        try
        {
        session=HibernateUtil.getSession();
        tx=session.beginTransaction();
        list=session.createQuery("from UserRole ur where ur.roleName=:rName").setString("rName", roleName).list();
        tx.commit();
        session.close();
        }
        catch(Exception ex)
        {
            session.close();
        }
        if(list !=null && !list.isEmpty())
        {
            ur=(UserRole)list.get(0);
        }
        return ur;
    }
    public int getNoOfUserRoles() throws Exception
    {
        int count=0;
        List list=null;
        try
        {
        session=HibernateUtil.getSession();
        tx=session.beginTransaction();
        list=session.createQuery("select count(ur.roleId) from UserRole ur").list();
        tx.commit();
        session.close();
        }
        catch(Exception ex)
        {
            session.close();
        }
        if(list !=null && !list.isEmpty())
        {
            count=Integer.parseInt(list.get(0).toString());
        }
        return count;
    }
    public void saveUserRole(UserRole ur) throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.save(ur);
            tx.commit();
            session.close();
            //System.err.println("UserRole name in saveUserRole is "+ur.getRoleName());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
    }
    public void updateUserRole(UserRole ur) throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.update(ur);
            tx.commit();
            session.close();
            //System.err.println("UserRole name in updateUserRole is "+ur.getRoleName());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
    }
    public void deleteUserRole(UserRole ur) throws Exception
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
