/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.ServiceDomain;
import com.nomis.ovc.util.AppUtility;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class ServiceDomainDaoImpl implements ServiceDomainDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    
    public void saveServiceDomain(ServiceDomain domain) throws Exception
    {
        try
        {
            if(domain !=null)
            {
                domain.setServiceDomainId(getUniqueRecordId());
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(domain);
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
     public void updateServiceDomain(ServiceDomain domain) throws Exception
     {
         try
        {
            if(domain !=null)
            {
                domain.setServiceDomainId(getUniqueRecordId());
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(domain);
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
    public void deleteServiceDomain(ServiceDomain domain) throws Exception
    {
        try
        {
            if(domain !=null)
            {
                
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(domain);
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
    public ServiceDomain getServiceDomain(String serviceDomainId) throws Exception
    {
        ServiceDomain serviceDomain=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from ServiceDomain domain where domain.serviveDomainId=:id").setString("id", serviceDomainId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                serviceDomain=(ServiceDomain)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return serviceDomain;
    }
    public List getAllServiceDomains() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ServiceDomain serviceDomain ").list();
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
    public String getUniqueRecordId()
    {
        AppUtility appUtil=new AppUtility();
        String uniqueId=appUtil.generateUniqueId();
        try
        {
            if(getServiceDomain(uniqueId) !=null)
            getUniqueRecordId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
