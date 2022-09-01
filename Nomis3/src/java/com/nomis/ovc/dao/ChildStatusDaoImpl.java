/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.ChildStatusIndex;
import com.nomis.ovc.util.AppConstant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class ChildStatusDaoImpl implements ChildStatusDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public ChildStatusIndex getChildStatusIndex(String ovcId, Date csiDate) throws Exception
    {
        ChildStatusIndex csi=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from ChildStatusIndex csi where csi.ovcId=:id and csi.csiDate=:dt").setString("id", ovcId).setDate("dt", csiDate).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                csi=(ChildStatusIndex)list.get(0);
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
            ex.printStackTrace();
        }
       return csi;
    }
    public List getCsiAsList(String ovcId, Date csiDate) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ChildStatusIndex csi where csi.ovcId=:id and csi.csiDate=:dt").setString("id", ovcId).setDate("dt", csiDate).list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
            ex.printStackTrace();
        }
        return list;
    }
    public void saveChildStatusIndex(ChildStatusIndex csi) throws Exception
    {
        try
        {
            if(csi !=null && getChildStatusIndex(csi.getOvcId(), csi.getCsiDate()) ==null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(csi);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          NomisLogManager.logStackTrace(ex);
        }
    }
    public void updateChildStatusIndex(ChildStatusIndex csi) throws Exception
    {
        try
        {
            if(csi !=null)
            {
                ChildStatusIndex csi2=getChildStatusIndex(csi.getOvcId(), csi.getCsiDate());
                if(csi2 !=null)
                {
                    csi.setId(csi2.getId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(csi);
                    tx.commit();
                    closeSession(session);
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          NomisLogManager.logStackTrace(ex);
        }
    }
    public void saveOrUpdateChildStatusIndex(ChildStatusIndex csi) throws Exception
    {
        if(csi !=null)
        {
            if(getChildStatusIndex(csi.getOvcId(), csi.getCsiDate()) !=null)
            updateChildStatusIndex(csi);
            else
            saveChildStatusIndex(csi);
        }
    }
    public void deleteChildStatusIndex(ChildStatusIndex csi) throws Exception
    {
        try
        {
            if(csi !=null)
            {
                ChildStatusIndex csi2=getChildStatusIndex(csi.getOvcId(), csi.getCsiDate());
                if(csi2 !=null)
                {
                    csi.setId(csi2.getId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(csi);
                    tx.commit();
                    closeSession(session);
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          NomisLogManager.logStackTrace(ex);
        }
    }
    public void markForDelete(ChildStatusIndex csi) throws Exception
    {
        try
        {
            if(csi !=null)
            {
                ChildStatusIndex csi2=getChildStatusIndex(csi.getOvcId(), csi.getCsiDate());
                if(csi2 !=null)
                {
                    csi.setId(csi2.getId());
                    csi.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(csi);
                    tx.commit();
                    closeSession(session);
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          NomisLogManager.logStackTrace(ex);
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
