/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.FacilityOvcOffer;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class FacilityOvcOfferDaoImpl implements FacilityOvcOfferDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and foo.markedForDelete=0";
    public int updateChildEnrollmentRecordsForOVCOfferedClients() throws Exception
    {
        int count=0;
        try
        {
            String query="from FacilityOvcOffer foo, Ovc ovc where foo.treatmentId=ovc.treatmentId and ovc.enrollmentSetting="+AppConstant.ENROLLMENTSETTING_COMMUNITY_NUM;
            System.err.println("query in updateChildEnrollmentRecordsForOVCOfferedClients() is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                System.err.println("list.size() in updateChildEnrollmentRecordsForOVCOfferedClients() is "+list.size());
                ChildEnrollmentDao dao=new ChildEnrollmentDaoImpl();
                FacilityOvcOffer foo=null;
                Ovc ovc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    foo=(FacilityOvcOffer)objArray[0];
                    ovc=(Ovc)objArray[1];
                    ovc=getOvcFromFacilityOvcOfferInformation(ovc,foo);
                    dao.updateOvcOnly(ovc);
                    count++;
                }
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return count;
    }
    public Ovc getOvcFromFacilityOvcOfferInformation(Ovc ovc,FacilityOvcOffer foo) throws Exception
    {
        if(ovc !=null && foo !=null)
        {
            ovc.setHivTreatmentFacilityId(foo.getHivTreatmentFacilityId());
            ovc.setSex(foo.getSex());
            ovc.setAgeAtBaseline(foo.getAge());
            ovc.setAgeUnitAtBaseline(foo.getAgeUnit());
            ovc.setEnrollmentSetting(AppConstant.ENROLLMENTSETTING_FACILITY_NUM);
            ovc.setLastModifiedDate(DateManager.getCurrentDateInstance());
        }
        return ovc;
    }
    public List getFacilityOvcOfferedRecordsForExport(ReportParameterTemplate rpt) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String baseQuery=SubQueryGenerator.getFacilityOvcOfferOrganizationUnitQuery();
            String orgUnitQuery=sqg.getOrganizationUnitQueryForFacilityOffer(rpt);
            //orgUnitQuery=orgUnitQuery.replace("hhe.partnerCode", "foo.partnerCode");
            
            String query=baseQuery+orgUnitQuery;
            //System.err.println("query in getNumberOfClientsOfferedOvcEnrollmentFromFacility is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[0]);
                }
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getFacilityOvcOfferedRecords(ReportParameterTemplate rpt) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String baseQuery=SubQueryGenerator.getFacilityOvcOfferOrganizationUnitQuery();
            String orgUnitQuery=sqg.getOrganizationUnitQueryForFacilityOffer(rpt);
            //orgUnitQuery=orgUnitQuery.replace("hhe.partnerCode", "foo.partnerCode");
            
            String query=baseQuery+orgUnitQuery+markedForDeleteQuery;
            //System.err.println("query in getNumberOfClientsOfferedOvcEnrollmentFromFacility is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[0]);
                }
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public int getNumberOfClientsOfferedOvcEnrollmentFromFacility(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex) throws Exception
    {
        int count=0;
        try
        {
            String baseQuery=SubQueryGenerator.getFacilityOvcOfferOrganizationUnitQuery();
            String orgUnitQuery=sqg.getOrganizationUnitQueryForFacilityOffer(rpt);
            //orgUnitQuery=orgUnitQuery.replace("hhe.partnerCode", "foo.partnerCode");
            String dateCaregiverSignedQuery=sqg.getFacilityOvcOfferDateQuery(startDate,endDate);
            String ageQuery=sqg.getFacilityOvcEnrollmentAgeQuery(startAge,endAge);
            String sexQuery=sqg.getFacilityOvcEnrollmentSexQuery(sex);
            String query="select count(distinct foo.clientUniqueId) "+baseQuery+orgUnitQuery+ageQuery+sexQuery+dateCaregiverSignedQuery+markedForDeleteQuery;
            //System.err.println("query in getNumberOfClientsOfferedOvcEnrollmentFromFacility is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                count=Integer.parseInt(list.get(0).toString());
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return count;
    }
    public FacilityOvcOffer getFacilityOvcOffer(String clientUniqueId) throws Exception
    {
        FacilityOvcOffer foo=null;
        try
        {
            if(clientUniqueId !=null)
            clientUniqueId=clientUniqueId.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from FacilityOvcOffer foo where foo.clientUniqueId=:id").setString("id", clientUniqueId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                foo=(FacilityOvcOffer)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return foo;
    }
    public FacilityOvcOffer getFacilityOvcOfferByTreatmentId(String treatmentId) throws Exception
    {
        FacilityOvcOffer foo=null;
        try
        {
            if(treatmentId !=null)
            treatmentId=treatmentId.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from FacilityOvcOffer foo where foo.treatmentId=:id").setString("id", treatmentId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                foo=(FacilityOvcOffer)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return foo;
    }
    public void saveFacilityOvcOffer(FacilityOvcOffer foo) throws Exception
    {
        try
        {
            if(foo !=null && foo.getDateEnrolledOnTreatment() !=null)
            {
                if(foo.getClientUniqueId() ==null || foo.getClientUniqueId().trim().length()==0)
                {
                    foo.setClientUniqueId(getUniqueRecordId());
                }
                if(this.getFacilityOvcOffer(foo.getClientUniqueId())==null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(foo);
                    tx.commit();
                    closeSession(session);
                    System.err.println("New FacilityOvcOffer saved");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateFacilityOvcOffer(FacilityOvcOffer foo) throws Exception
    {
        try
        {
            if(foo !=null && foo.getDateEnrolledOnTreatment() !=null)
            {
                if(this.getFacilityOvcOffer(foo.getClientUniqueId())!=null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(foo);
                    tx.commit();
                    closeSession(session);
                    System.err.println("FacilityOvcOffer updated");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteFacilityOvcOffer(FacilityOvcOffer foo) throws Exception
    {
        try
        {
            if(foo !=null && foo.getDateEnrolledOnTreatment() !=null)
            {
                if(this.getFacilityOvcOffer(foo.getClientUniqueId())!=null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(foo);
                    tx.commit();
                    closeSession(session);
                    System.err.println("FacilityOvcOffer deleted");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void markForDelete(FacilityOvcOffer foo) throws Exception
    {
        try
        {
            if(foo !=null && foo.getDateEnrolledOnTreatment() !=null)
            {
                if(this.getFacilityOvcOffer(foo.getClientUniqueId())!=null)
                {
                    foo.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(foo);
                    tx.commit();
                    closeSession(session);
                    System.err.println("FacilityOvcOffer marked for delete");
                }
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
    public String getUniqueRecordId()
    {
        AppUtility appUtil=new AppUtility();
        String uniqueId=appUtil.generateUniqueId();
        try
        {
            if(getFacilityOvcOffer(uniqueId) !=null)
            getUniqueRecordId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
}
