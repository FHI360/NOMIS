/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.metadata.OrganizationUnitHierarchy;
import com.nomis.ovc.util.AppUtility;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class OrganizationUnitHierarchyDaoImpl implements OrganizationUnitHierarchyDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public OrganizationUnitHierarchy getOrganizationUnitHierarchyByLevel(int oulevel) throws Exception
    {
        OrganizationUnitHierarchy ouh=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from OrganizationUnitHierarchy ouh where ouh.oulevel=:oul").setInteger("oul", oulevel).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ouh=(OrganizationUnitHierarchy)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ouh;
    }
    public OrganizationUnitHierarchy getOrganizationUnitHierarchyByName(String hierarchyName) throws Exception
    {
        OrganizationUnitHierarchy ouh=null;
        try
        {
            if(hierarchyName !=null)
            {
                hierarchyName=hierarchyName.trim().toUpperCase();
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from OrganizationUnitHierarchy ouh where UPPER(ouh.name)=:hname").setString("hname", hierarchyName).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    ouh=(OrganizationUnitHierarchy)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ouh;
    }
    public List getAllOrganizationUnitHierarchyRecords() throws Exception
    {
        List list =null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from OrganizationUnitHierarchy ouh order by ouh.oulevel asc").list();
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
    public int getMaximumOuLevel() throws Exception
    {
        int ouLevel=0;
        
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("select distinct ouh.oulevel from OrganizationUnitHierarchy ouh order by ouh.oulevel desc").list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ouLevel=Integer.parseInt(list.get(0).toString());
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        
        return ouLevel;
    }
    public OrganizationUnitHierarchy getOrganizationUnitHierarchy(String uid) throws Exception
    {
        OrganizationUnitHierarchy ouh=null;
        
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from OrganizationUnitHierarchy ouh where ouh.uid=:id").setString("id", uid).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ouh=(OrganizationUnitHierarchy)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        
        return ouh;
    }
    public void saveOrganizationUnitHierarchy(OrganizationUnitHierarchy ouh) throws Exception
    {
        try
        {
            if(ouh !=null)
            {
                if(ouh.getUid()==null || ouh.getUid().trim().length()<11)
                ouh.setUid(getUniqueRecordId());
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(ouh);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            throw new Exception(ex);
        }
    }
    public void updateOrganizationUnitHierarchy(OrganizationUnitHierarchy ouh) throws Exception
    {
        try
        {
            if(ouh !=null && getOrganizationUnitHierarchy(ouh.getUid()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(ouh);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            throw new Exception(ex);
        }
    }
    public void deleteOrganizationUnitHierarchy(OrganizationUnitHierarchy ouh) throws Exception
    {
        try
        {
            if(ouh !=null && getOrganizationUnitHierarchy(ouh.getUid()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(ouh);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            throw new Exception(ex);
        }
    }
    public String getUniqueRecordId()
    {
        AppUtility appUtil=new AppUtility();
        String recordId=appUtil.generateUniqueId(11);
        try
        {
            if(getOrganizationUnitHierarchy(recordId) !=null)
            getUniqueRecordId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return recordId;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
