/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.AppSetting;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class AppSettingDaoImpl implements AppSettingDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public AppSetting getAppSetting(String recordId) throws Exception
    {
        AppSetting opm=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from AppSetting opm where opm.recordId =:oid").setString("oid", recordId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                opm=(AppSetting)list.get(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            closeSession(session);
        }
        return opm;
    }
    public void saveAppSetting(AppSetting opm) throws Exception
    {
        try
        {
            if(opm !=null && opm.getRecordId() !=null && opm.getValue() !=null)
            {
                if(getAppSetting(opm.getRecordId()) ==null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(opm);
                    tx.commit();
                    closeSession(session);
                }
                else
                updateAppSetting(opm);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void updateAppSetting(AppSetting opm) throws Exception
    {
        try
        {
            if(opm !=null && opm.getRecordId() !=null && opm.getValue() !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(opm);
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
    public void deleteAppSetting(AppSetting opm) throws Exception
    {
        try
        {
            if(opm !=null && opm.getRecordId() !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(opm);
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
