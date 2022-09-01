/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;


import com.nomis.ovc.business.ReferralFacility;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
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
public class ReferralFacilityDaoImpl implements ReferralFacilityDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public void saveReferralFacility(ReferralFacility rf) throws Exception
    {
        try
        {
            if(rf !=null && isValidFacilityName(rf))
            {
                if(rf.getFacilityId()==null || rf.getFacilityId().trim().length()==0)
                rf.setFacilityId(getUniqueRecordId());
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(rf);
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
    public void updateReferralFacility(ReferralFacility rf) throws Exception
    {
        try
        {
            if(rf !=null && getReferralFacility(rf.getFacilityId()) !=null  && isValidFacilityName(rf))
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(rf);
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
    public void markForDelete(ReferralFacility rf) throws Exception
    {
        try
        {
            if(rf !=null && getReferralFacility(rf.getFacilityId()) !=null  && isValidFacilityName(rf))
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                
                session.update(rf);
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
    public void deleteReferralFacility(ReferralFacility rf) throws Exception
    {
        try
        {
            if(getReferralFacility(rf.getFacilityId()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(rf);
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
    public List getReferralFacilitiesByOrgUnit(ReportParameterTemplate rpt) throws Exception
    {
        List facilityList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String query="from ReferralFacility rf order by rf.facilityName";
            String additionalOrgUnitQuery="";
            /*if(rpt !=null && rpt.getLevel2OuId() !=null && (rpt.getLevel2OuId().equalsIgnoreCase("select") || rpt.getLevel2OuId().equalsIgnoreCase("All")))
            {
                facilityList=getAllReferralFacilities();
            }*/
            
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQueryWithoutCBO(rpt);
            }
            query=SubQueryGenerator.getReferralFacilityOrganizationUnitQuery()+additionalOrgUnitQuery+" order by rf.facilityName";
            System.err.println(query);
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            List list=session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    facilityList.add(objArray[0]);
                }
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            throw new Exception(ex);
        }
        return facilityList;
    }
    public ReferralFacility getReferralFacility(String facilityId) throws Exception
    {
        ReferralFacility rf=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from ReferralFacility rf where rf.facilityId=:id").setString("id", facilityId).list();
            tx.commit();
            closeSession(session); 
            if(list !=null && !list.isEmpty())
            {
                rf=(ReferralFacility)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return rf;
    }
    public ReferralFacility getReferralFacilityByName(String facilityName) throws Exception
    {
        ReferralFacility rf=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from ReferralFacility rf where rf.facilityName=:nm").setString("nm", facilityName).list();
            tx.commit();
            closeSession(session); 
            if(list !=null && !list.isEmpty())
            {
                rf=(ReferralFacility)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return rf;
    }
    public List getReferralFacilitiesByName(String facilityName) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ReferralFacility rf where rf.facilityName=:nm").setString("nm", facilityName).list();
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
    public List getAllReferralFacilities() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ReferralFacility rf order by rf.facilityName").list();
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
    public List getReferralFacilitiesWithDefaultOrgUnit() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ReferralFacility rf where (rf.organizationUnitId is null or rf.organizationUnitId='XXX')").list();
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
            if(getReferralFacility(uniqueId) !=null)
            getUniqueRecordId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public ReferralFacility createReferralFacility(String name,String address,String contactPhone,String contactEmail,String contactPerson,double latitude,double longitude,String orgUnitId,String userName,int type) throws Exception
    {
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        
        ReferralFacility rf=this.getReferralFacilityByName(name);
        if(rf==null)
        {
            rf=new ReferralFacility();
            rf.setAddress(address);
            rf.setContactEmail(contactEmail);
            rf.setContactPhone(contactPhone);
            rf.setNameOfContactPerson(contactPerson);
            rf.setFacilityName(name);
            rf.setTypeOfFacility(type);
            rf.setDateCreated(currentDate);
            rf.setLastModifiedDate(currentDate);
            rf.setOrganizationUnitId(orgUnitId);
            rf.setRecordedBy(userName);
            rf.setFacilityId(getUniqueRecordId());
            saveReferralFacility(rf);
        }
        return rf;
    }
    private boolean isValidFacilityName(ReferralFacility rf)
    {
        boolean isFacilityNameValid=false;
        if(rf !=null)
        {
            String facilityName=rf.getFacilityName();
            if(facilityName !=null && facilityName.trim().length()>1 && !facilityName.equalsIgnoreCase("N/A") && !facilityName.equalsIgnoreCase("NA/"))
            {
                isFacilityNameValid=true;
            }
        }
        return isFacilityNameValid;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
