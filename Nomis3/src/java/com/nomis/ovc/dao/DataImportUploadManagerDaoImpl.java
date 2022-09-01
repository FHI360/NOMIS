/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.exportimport.DataImportFileUploadManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class DataImportUploadManagerDaoImpl implements DataImportUploadManagerDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public void saveDataImportFileUploadManager(DataImportFileUploadManager difum) throws Exception
    {
        if(difum !=null)
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(difum);
            tx.commit();
            closeSession(session);
        }
    }
    public void updateDataImportFileUploadManager(DataImportFileUploadManager difum) throws Exception
    {
        if(difum !=null)
        {
            if(getDataImportFileUploadManager(difum.getRecordId()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(difum);
                tx.commit();
                closeSession(session);
            }
        }
    }
    public void deleteDataImportFileUploadManager(DataImportFileUploadManager difum) throws Exception
    {
        if(getDataImportFileUploadManager(difum.getRecordId()) !=null)
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(difum);
            tx.commit();
            closeSession(session);
        }
    }
    public DataImportFileUploadManager getDataImportFileUploadManager(int recordId) throws Exception
    {
        DataImportFileUploadManager difum=null;
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from DataImportFileUploadManager difum where difum.recordId=:id").setInteger("id", recordId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                difum=(DataImportFileUploadManager)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        
        return difum;
    }
    public List getAllDataImportFileUploadManager() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from DataImportFileUploadManager difum ").list();
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
    public List getAllDataImportFileUploadManager(int uploadStatus) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from DataImportFileUploadManager difum where difum.importStatus=:id").setInteger("id", uploadStatus).list();
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
