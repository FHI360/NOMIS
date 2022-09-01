/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.Occupation;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class OccupationDaoImpl implements OccupationDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    String markedForDeleteQuery=" and occupation.markedForDelete=0";
    public Occupation getOccupationWithHighestValue() throws Exception
    {
        Occupation occupation=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Occupation occupation order by occupation.value desc").list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                occupation=(Occupation)list.get(0);
            }
         }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return occupation;
    }
    public void saveOccupation(Occupation occupation) throws Exception
    {
        if(occupation !=null && occupation.getName() !=null && occupation.getValue()>0)
        {
            Occupation occupation2=this.getOccupation(occupation.getName());
            if(occupation2==null)
            {
                Occupation occupation3=this.getOccupation(occupation.getValue());
                if(occupation3==null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(occupation);
                    tx.commit();
                    closeSession(session);
                }
                System.err.println("occupation.getName() "+occupation.getName()+" saved");
            }

        }
    }
    public void updateOccupation(Occupation occupation) throws Exception
    {
       if(occupation !=null && occupation.getName() !=null && occupation.getValue()>0)
        {
            Occupation occupation2=this.getOccupation(occupation.getName());
            Occupation occupation3=this.getOccupation(occupation.getValue());
            if(occupation2!=null || occupation3 !=null)
            {
                if(occupation2!=null)
                occupation.setRecordId(occupation2.getRecordId());
                else
                occupation.setRecordId(occupation3.getRecordId()); 
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(occupation);
                tx.commit();
                closeSession(session);
                System.err.println("occupation.getName() "+occupation.getName()+" updated");
            }

        }
    }  
    
    public void deleteOccupation(Occupation occupation) throws Exception
    {
        if(occupation !=null && occupation.getName() !=null)
        {
            Occupation occupation2=this.getOccupation(occupation.getName());
            Occupation occupation3=this.getOccupation(occupation.getValue());
            if(occupation2!=null || occupation3 !=null)
            {
                if(occupation2!=null)
                occupation.setRecordId(occupation2.getRecordId());
                else
                occupation.setRecordId(occupation3.getRecordId()); 
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(occupation);
                tx.commit();
                closeSession(session);
                System.err.println("occupation.getName() "+occupation.getName()+" updated");
            }

        }
    }
    public Occupation getOccupation(String occupationName) throws Exception
    {
        Occupation occupation=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Occupation occupation where occupation.name =:oc").setString("oc", occupationName).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                occupation=(Occupation)list.get(0);
            }
         }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return occupation;
    }
    public Occupation getOccupation(int occupationValue) throws Exception
    {
        Occupation occupation=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Occupation occupation where occupation.value =:val").setInteger("val", occupationValue).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                occupation=(Occupation)list.get(0);
            }
         }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return occupation;
    }
    public Occupation getOccupationByRecordId(int recordId) throws Exception
    {
        Occupation occupation=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Occupation occupation where occupation.recordId =:rid").setInteger("rid", recordId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                occupation=(Occupation)list.get(0);
            }
         }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return occupation;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
