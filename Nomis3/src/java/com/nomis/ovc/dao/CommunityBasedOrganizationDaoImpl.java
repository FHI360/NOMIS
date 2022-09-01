/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class CommunityBasedOrganizationDaoImpl implements CommunityBasedOrganizationDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public int updateCBOsWithWrongLevel2OuId(String wrongLevel2OuId,String correctLevel2OuId) throws Exception
    {
        int count=0;
        List list=getCommunityBasedOrganizationByLevel2OrgUnit(wrongLevel2OuId);
        if(list !=null)
        {
            CommunityBasedOrganization setup=null;
            for(int i=0; i<list.size(); i++)
            {
                setup=(CommunityBasedOrganization)list.get(i);
                setup.setLevel2OuId(correctLevel2OuId);
                this.updateCommunityBasedOrganization(setup);
                count++;
            }
        }
        return count;
    }
    public void saveCommunityBasedOrganization(CommunityBasedOrganization setup) throws Exception
    {
        try
        {
            //System.err.println("setup.getCboName() is "+setup.getUniqueId()+" and setup.getCboName()"+setup.getCboName());
            if(setup !=null && getCommunityBasedOrganizationByName(setup.getCboName())==null)
            {
                if(setup.getUniqueId()==null || setup.getUniqueId().trim().length()<11)
                setup.setUniqueId(getUniqueRecordId());
                if(this.getCommunityBasedOrganization(setup.getUniqueId())==null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(setup);
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
    public void updateCommunityBasedOrganization(CommunityBasedOrganization setup) throws Exception
    {
        try
        {
            System.err.println("setup.getCboName() is "+setup.getUniqueId()+" and setup.getCboName()"+setup.getCboName());
            if(setup !=null && this.getCommunityBasedOrganization(setup.getUniqueId()) !=null)
            {
                boolean updateAllowed=true;
                CommunityBasedOrganization setup2=getCommunityBasedOrganizationByName(setup.getCboName());
                if(setup2 !=null)
                {
                    if(!setup.getUniqueId().trim().equalsIgnoreCase(setup2.getUniqueId().trim()))
                    updateAllowed=false;
                }
                if(updateAllowed)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(setup);
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
    public void deleteCommunityBasedOrganization(CommunityBasedOrganization setup) throws Exception
    {
        try
        {
            if(setup !=null && this.getCommunityBasedOrganization(setup.getUniqueId()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(setup);
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
    public CommunityBasedOrganization getCommunityBasedOrganization(String uniqueId) throws Exception
    {
        CommunityBasedOrganization setup=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CommunityBasedOrganization setup where setup.uniqueId=:id").setString("id", uniqueId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                setup=(CommunityBasedOrganization)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return setup;
    }
    public CommunityBasedOrganization getCommunityBasedOrganizationByName(String cboName) throws Exception
    {
        CommunityBasedOrganization setup=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CommunityBasedOrganization setup where setup.cboName=:nm").setString("nm", cboName).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                setup=(CommunityBasedOrganization)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return setup;
    }
    public CommunityBasedOrganization getCommunityBasedOrganizationByLevel2OuIdAndCboName(String level2OuId,String cboName) throws Exception
    {
        CommunityBasedOrganization setup=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CommunityBasedOrganization setup where setup.level2OuId=:l2Id and setup.cboName=:nm").setString("l2Id", level2OuId).setString("nm", cboName).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                setup=(CommunityBasedOrganization)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return setup;
    }
    public List getCommunityBasedOrganizationByLevel2OrgUnit(String level2OuId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from CommunityBasedOrganization setup where setup.level2OuId=:ouId").setString("ouId", level2OuId).list();
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
    public List getCommunityBasedOrganizationByAssignedLevel3OrgUnit(String level3OuId) throws Exception
    {
        List list=null;
        try
        {//assignedLevel3OuIds
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from CommunityBasedOrganization setup where setup.assignedLevel3OuIds like '%"+level3OuId+"%'").list();
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
    public List getAllCommunityBasedOrganization() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from CommunityBasedOrganization setup").list();
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
            if(getCommunityBasedOrganization(uniqueId) !=null)
            getUniqueRecordId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public CommunityBasedOrganization getCommunityBasedOrganizationByLegacyId(String legacyId) throws Exception
    {
       CommunityBasedOrganization cbo=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CommunityBasedOrganization cbo where cbo.legacyId=:lid").setString("lid", legacyId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                cbo=(CommunityBasedOrganization)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return cbo;
    }
    public CommunityBasedOrganization createCommunityBasedOrganization(String level2OuId,String cboCode,String cboName,String userName,String legacyId) throws Exception
    {
        CommunityBasedOrganization cbo=null;
        if(level2OuId !=null && cboName !=null)
        {
            //System.err.println("Parent name is "+parentOu.getName()+" and ward name "+wardName);
                cbo=this.getCommunityBasedOrganizationByLevel2OuIdAndCboName(level2OuId,cboName);
                if(cbo==null)
                {
                    cbo=new CommunityBasedOrganization();
                    cbo.setUniqueId(getUniqueRecordId());
                    cbo.setDateCreated(DateManager.getCurrentDateInstance());
                    cbo.setLastModifiedDate(DateManager.getCurrentDateInstance());
                    cbo.setCboCode(cboCode);
                    cbo.setCboName(cboName);
                    cbo.setLevel2OuId(level2OuId); 
                    cbo.setRecordedBy(cboName);
                    cbo.setContactPersonName(AppConstant.DEFAULTUID);
                    cbo.setContactPersonEmail(AppConstant.DEFAULTUID);
                    cbo.setContactPersonPhone(AppConstant.DEFAULTUID);
                    cbo.setLegacyId(legacyId);
                    saveCommunityBasedOrganization(cbo);
                }
            
        }
        return cbo;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
