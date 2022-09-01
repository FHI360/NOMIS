/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.ProcessMonitor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class ProcessMonitorDaoImpl implements ProcessMonitorDao
{
    Session session;
    Transaction tx;
    List list;
    
    public void saveProcessMonitor(ProcessMonitor pm) throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.save(pm);
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void updateProcessMonitor(ProcessMonitor pm) throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.update(pm);
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void deleteProcessMonitor(ProcessMonitor pm) throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            session.delete(pm);
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public ProcessMonitor getProcessMonitorByProcessId(String processId) throws Exception
    {
        ProcessMonitor pm=null;
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery("from ProcessMonitor pm where pm.processId= :pid").setString("pid", processId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                pm=(ProcessMonitor)list.get(0);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
        return pm;
    }
    public ProcessMonitor getProcessMonitorByProcessName(String processName) throws Exception
    {
        ProcessMonitor pm=null;
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery("from ProcessMonitor pm where pm.processName= :pname").setString("pname", processName).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                pm=(ProcessMonitor)list.get(0);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
        return pm;
    }
    public List getAllProcessMonitors() throws Exception
    {
        List list=null;
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery("from ProcessMonitor pm").list();
            tx.commit();
            closeSession(session);  
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
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
