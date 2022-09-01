/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.SiteSetup;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class SiteSetupDaoImpl implements SiteSetupDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public void saveSiteSetup(SiteSetup setup) throws Exception
    {
        try
        {
            if(getSiteSetup(setup.getUserName()) ==null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(setup);
                tx.commit();
                closeSession(session);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
    }
    public void updateSiteSetup(SiteSetup setup) throws Exception
    {
        try
        {
            if(getSiteSetup(setup.getUserName()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(setup);
                tx.commit();
                closeSession(session);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
    }
    public void deleteSiteSetup(SiteSetup setup) throws Exception
    {
         try
         {
            if(getSiteSetup(setup.getUserName()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(setup);
                tx.commit();
                closeSession(session);
            }
         }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
    }
    public SiteSetup getSiteSetup(String userName) throws Exception
    {
        SiteSetup setup=null;
        try
        {
            if(userName !=null)
            {
                userName=userName.trim().toUpperCase();
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from SiteSetup setup where UPPER(setup.userName)=:un").setString("un", userName).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    setup=(SiteSetup)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return setup;
    }
    public List getAllSiteSetups() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from SiteSetup setup").list();
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
