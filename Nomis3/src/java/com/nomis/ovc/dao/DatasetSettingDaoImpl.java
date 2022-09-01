/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.DatasetSetting;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class DatasetSettingDaoImpl implements DatasetSettingDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public DatasetSetting getDatasetSetting(String datasetId) throws Exception
    {
        DatasetSetting dts=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from DatasetSetting dts where dts.datasetId =:did").setString("did", datasetId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                dts=(DatasetSetting)list.get(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            closeSession(session);
        }
        return dts;
    }
    public DatasetSetting getDatasetSettingByModuleId(String moduleId) throws Exception
    {
        DatasetSetting dts=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from DatasetSetting dts where dts.moduleId =:mid").setString("mid", moduleId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                dts=(DatasetSetting)list.get(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            closeSession(session);
        }
        return dts;
    }
    public List getDatasetSettings() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from DatasetSetting dts ").list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            closeSession(session);
        }
        return list;
    }
    public void saveDatasetSetting(DatasetSetting dts) throws Exception
    {
        try
        {
            if(dts !=null && dts.getDatasetId() !=null)
            {
                if(this.getDatasetSettingByModuleId(dts.getModuleId()) ==null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(dts);
                    tx.commit();
                    closeSession(session);
                }
                else
                updateDatasetSetting(dts);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void updateDatasetSetting(DatasetSetting dts) throws Exception
    {
        try
        {
            if(dts !=null && dts.getDatasetId() !=null)
            {
                if(this.getDatasetSettingByModuleId(dts.getModuleId()) !=null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(dts);
                    tx.commit();
                    closeSession(session); 
                }
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void deleteDatasetSetting(DatasetSetting dts) throws Exception
    {
        try
        {
            if(dts !=null && dts.getModuleId() !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(dts);
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
