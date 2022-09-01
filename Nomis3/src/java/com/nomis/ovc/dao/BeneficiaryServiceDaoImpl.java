/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.Service;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class BeneficiaryServiceDaoImpl implements BeneficiaryServiceDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and bservice.markedForDelete=0";
    public void saveBeneficiaryService(Service bservice) throws Exception
    {
        try
        {
            System.err.println("Inside saveBeneficiaryService");
            if(bservice !=null)
            {
                if(this.getBeneficiaryServiceByName(bservice.getServiceName().trim()) ==null)
                {
                    if(bservice.getServiceId()==null || bservice.getServiceId().trim().length()<11)
                    bservice.setServiceId(getUniqueRecordId());
                    bservice.setServiceName(bservice.getServiceName().trim());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(bservice);
                    tx.commit();
                    closeSession(session);
                    System.err.println("New BeneficiaryService saved");  
                }
                else
                {
                    this.updateBeneficiaryService(bservice);
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateBeneficiaryService(Service bservice) throws Exception
    {
        try
        {
            System.err.println("Inside updateBeneficiaryService");
            if(bservice !=null)
            {
                //System.err.println("bservice is not null");
                Service bs2=this.getBeneficiaryService(bservice.getServiceId()); 
                if(bs2 !=null)
                {
                    //System.err.println("bs2 is not null");
                    if(this.getBeneficiaryServiceByName(bservice.getServiceName().trim()) !=null)
                    {
                        //System.err.println("bs2 is not null");
                        //if a service with the same name already exists, then do not update if their service ids are different
                        if(!bservice.getServiceId().equalsIgnoreCase(bs2.getServiceId()))
                        {
                            System.err.println("bservice.getServiceId() "+bservice.getServiceId()+" bs2.getServiceId() "+bs2.getServiceId());
                            return;
                        }
                    }
                    bservice.setServiceName(bservice.getServiceName().trim());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(bservice);
                    tx.commit();
                    closeSession(session);
                    System.err.println("BeneficiaryService updated");  
                }  
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void markForDelete(Service bservice) throws Exception
    {
        try
        {
            System.err.println("Inside markForDelete");
            if(bservice !=null)
            {
                Service bs2=this.getBeneficiaryService(bservice.getServiceId()); 
                if(bs2 !=null)
                {
                    bs2.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(bservice);
                    tx.commit();
                    closeSession(session);
                    System.err.println("BeneficiaryService marked for delete");  
                }   
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteBeneficiaryService(Service bservice) throws Exception
    {
        try
        {
            System.err.println("Inside markForDelete");
            if(bservice !=null)
            {
                Service bs2=this.getBeneficiaryService(bservice.getServiceId()); 
                if(bs2 !=null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(bservice);
                    tx.commit();
                    closeSession(session);
                    System.err.println("BeneficiaryService deleted");  
                } 
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public Service getBeneficiaryService(String serviceId) throws Exception
    {
        Service bservice=null;
        try
        {
            if(serviceId !=null)
            serviceId=serviceId.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Service bservice where bservice.serviceId=:id").setString("id", serviceId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                bservice=(Service)list.get(0);
                //System.err.println("ahm.getFirstName() is "+ahm.getFirstName());
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return bservice;
    }
    public Service getBeneficiaryServiceByServiceCode(String serviceCode) throws Exception
    {
        Service bservice=null;
        try
        {
            if(serviceCode !=null)
            serviceCode=serviceCode.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Service bservice where bservice.serviceCode=:sc").setString("sc", serviceCode).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                bservice=(Service)list.get(0);
                //System.err.println("ahm.getFirstName() is "+ahm.getFirstName());
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return bservice;
    }
    public Service getBeneficiaryServiceByName(String serviceName) throws Exception
    {
        Service bservice=null;
        try
        {
            //System.err.println("beneficiaryId: "+beneficiaryId);
            if(serviceName !=null)
            serviceName=serviceName.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Service bservice where bservice.serviceName=:sn").setString("sn", serviceName).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                bservice=(Service)list.get(0);
                //System.err.println("ahm.getFirstName() is "+ahm.getFirstName());
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return bservice;
    }
    public List getBeneficiaryServicesByDomain(String domainId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from Service bservice where bservice.domainId=:id").setString("id", domainId).list();
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
    public List getBeneficiaryServicesByDomainAndDisplayStatus(String domainId,int displayStatus) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from Service bservice where bservice.domainId=:id and bservice.displayStatus=:ds").setString("id", domainId).setInteger("ds", displayStatus).list();
            tx.commit();
            closeSession(session);
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return list;
    }
    public List getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType(String domainId,int displayStatus,int beneficiaryType) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from Service bservice where bservice.domainId=:id and bservice.displayStatus=:ds and bservice.beneficiaryType=:bt").setString("id", domainId).setInteger("ds", displayStatus).setInteger("bt", beneficiaryType).list();
            tx.commit();
            closeSession(session);
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return list;
    }
    public List getAllBeneficiaryServices() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from Service bservice ").list();
            tx.commit();
            closeSession(session); 
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return list;
    }
    public String getUniqueRecordId()
    {
        AppUtility appUtil=new AppUtility();
        String uniqueId=appUtil.generateUniqueId();
        try
        {
            if(this.getBeneficiaryService(uniqueId) !=null)
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
