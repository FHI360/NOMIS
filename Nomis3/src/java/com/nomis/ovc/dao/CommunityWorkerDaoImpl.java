/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.CommunityWorker;
import com.nomis.ovc.util.AppUtility;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class CommunityWorkerDaoImpl implements CommunityWorkerDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    public void saveCommunityWorker(CommunityWorker cw) throws Exception
    {
        if(cw !=null && cw.getLevel4OuId() !=null && cw.getFirstName() !=null && cw.getSurname() !=null && cw.getSex() !=null)
        {
            if(cw.getCommunityWorkerId()==null || cw.getCommunityWorkerId().trim().length()<11)
            cw.setCommunityWorkerId(getUniqueRecordId());
            else
            {
               CommunityWorker existingCw=this.getCommunityWorker(cw.getCommunityWorkerId()); 
               if(existingCw !=null)
               {
                   updateCommunityWorker(cw);
                   return;
               }
            }
            System.err.println("cw.getcommunityWorkerId() is "+cw.getCommunityWorkerId());
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(cw);
            tx.commit();
            closeSession(session);
        }
    }
    public void updateCommunityWorker(CommunityWorker cw) throws Exception
    {
        if(cw !=null  && cw.getLevel4OuId() !=null && cw.getFirstName() !=null && cw.getSurname() !=null && cw.getSex() !=null 
                && this.getCommunityWorker(cw.getCommunityWorkerId()) !=null)
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(cw);
            tx.commit();
            closeSession(session);
        }
    }
    public void deleteCommunityWorker(CommunityWorker cw) throws Exception
    {
        if(cw !=null && this.getCommunityWorker(cw.getCommunityWorkerId()) !=null)
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(cw);
            tx.commit();
            closeSession(session);
        }
    }
    public CommunityWorker getCommunityWorker(String communityWorkerId) throws Exception
    {
        CommunityWorker cw=null;
        List list=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        list = session.createQuery("from CommunityWorker cw where cw.communityWorkerId=:id").setString("id", communityWorkerId).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            cw=(CommunityWorker)list.get(0);
        }
        return cw;
    }
    public CommunityWorker getCommunityWorkerByName(String firstName,String surname) throws Exception
    {
        CommunityWorker cw=null;
        List list=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        list = session.createQuery("from CommunityWorker cw where ((cw.firstName=:fn and cw.surname=:sn) or (cw.firstName=:sfn and cw.surname=:fsn))").setString("fn",firstName)
                .setString("sn",surname).setString("sfn",surname).setString("fsn",firstName).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            cw=(CommunityWorker)list.get(0);
        }
        return cw;
    }
    public List getAllCommunityWorkers() throws Exception
    {
        List list=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        list = session.createQuery("from CommunityWorker cw order by cw.firstName").list();
        tx.commit();
        closeSession(session);
        
        return list;
    }
    public List getActiveCommunityWorkers() throws Exception
    {
        List list=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        list = session.createQuery("from CommunityWorker cw where cw.enrollmentStatus=1 order by cw.firstName").list();
        tx.commit();
        closeSession(session);
        
        return list;
    }
    public String getUniqueRecordId()
    {
        AppUtility appUtil=new AppUtility();
        String uniqueId=appUtil.generateUniqueId();
        try
        {
            if(getCommunityWorker(uniqueId) !=null)
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
