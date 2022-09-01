/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.HouseholdCareplan;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.util.AppConstant;
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
public class HouseholdCareplanDaoImpl implements HouseholdCareplanDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and hcp.markedForDelete=0";
    public int getSumOfRecordIdsFromHouseholdCareplan() throws Exception
    {
        int sum=0;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("select sum(hcp.recordId) from HouseholdCareplan hcp").list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
               sum=Integer.parseInt(list.get(0).toString());
            }
            
        }
         catch (Exception ex)
         {
            closeSession(session);
            throw new Exception(ex);
         }
        return sum;
    }
    public HouseholdCareplan getAllRecordIdsWithZeroValuesFromHouseholdCareplan() throws Exception
    {
        HouseholdCareplan hcp=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HouseholdCareplan hcp where hcp.recordId=0").list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
               hcp=(HouseholdCareplan)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hcp;
    }
    public List getHouseholdCareplanRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            List ovcList=getOvcHouseholdCareplanRecordsForExport(rpt,markedForDeleteValue);
            List adultList=getAdultHouseholdCareplanRecordsForExport(rpt,markedForDeleteValue);
            if(ovcList !=null)
            mainList.addAll(ovcList);
            if(adultList !=null)
            mainList.addAll(adultList);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getOvcHouseholdCareplanRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and hcp.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcHouseholdCareplanOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getHouseholdCareplanLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                Ovc ovc=null;
                HouseholdCareplan hcp=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ovc=(Ovc)objArray[1];
                    hcp=(HouseholdCareplan)objArray[2];
                    hcp.setBeneficiary(ovc);
                    hcp.setHhe(hhe);
                    mainList.add(hcp);
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
    public List getAdultHouseholdCareplanRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and hcp.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberHouseholdCareplanOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getHouseholdCareplanLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                AdultHouseholdMember ahm;
                HouseholdCareplan hcp=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ahm=(AdultHouseholdMember)objArray[1];
                    hcp=(HouseholdCareplan)objArray[2];
                    hcp.setHhe(hhe);
                    hcp.setBeneficiary(ahm);
                    mainList.add(hcp);
                }
            }
            /*if(list !=null)
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[2]);
                }
            }*/
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public void saveHouseholdCareplan(HouseholdCareplan hcp) throws Exception
    {
        try
        {
                if(hcp !=null && this.getHouseholdCareplan(hcp.getBeneficiaryId(),hcp.getCareplanDate()) ==null)
            {
                System.err.println("hcp with Id "+hcp.getBeneficiaryId()+" about to be saved after cleanup");
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(hcp);
                tx.commit();
                closeSession(session);
                System.err.println("hcp with Id "+hcp.getBeneficiaryId()+" saved");
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void markForDelete(HouseholdCareplan hcp) throws Exception
    {
        try
        {
            if(hcp !=null)
            {
                 HouseholdCareplan hcp2=getHouseholdCareplan(hcp.getBeneficiaryId(),hcp.getCareplanDate());
                System.err.println("hcp with Id "+hcp.getBeneficiaryId()+" about to be saved after cleanup");
                if(hcp2 !=null)
                {
                    hcp2.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(hcp2);
                    tx.commit();
                    closeSession(session);
                    System.err.println("hcp with Id "+hcp.getBeneficiaryId()+" marked for delete");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateHouseholdCareplan(HouseholdCareplan hcp) throws Exception
    {
        try
        {
            if(hcp !=null)
            {
                 HouseholdCareplan hcp2=getHouseholdCareplan(hcp.getBeneficiaryId(),hcp.getCareplanDate());
                System.err.println("hcp with Id "+hcp.getBeneficiaryId()+" about to be saved after cleanup");
                if(hcp2 !=null)
                {
                    hcp.setRecordId(hcp2.getRecordId()); 
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(hcp);
                    tx.commit();
                    closeSession(session);
                    System.err.println("hcp with Id "+hcp.getBeneficiaryId()+" saved");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteHouseholdCareplan(HouseholdCareplan hcp) throws Exception
    {
        try
        {
            if(hcp !=null)
            {
                HouseholdCareplan hcp2=getHouseholdCareplan(hcp.getBeneficiaryId(),hcp.getCareplanDate());
                if(hcp2 !=null)
                {
                    hcp.setRecordId(hcp2.getRecordId()); 
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(hcp);
                    tx.commit();
                    closeSession(session);
                    System.err.println("hcp with Id "+hcp.getBeneficiaryId()+" saved");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public HouseholdCareplan getHouseholdCareplan(int recordId) throws Exception
    {
        HouseholdCareplan hcp=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HouseholdCareplan hcp where hcp.recordId=:id").setInteger("id", recordId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                hcp=(HouseholdCareplan)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hcp;
    }
    public HouseholdCareplan getHouseholdCareplan(String beneficiaryId,Date careplanDate) throws Exception
    {
       HouseholdCareplan hcp=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HouseholdCareplan hcp where hcp.beneficiaryId=:id and hcp.careplanDate=:doa").setString("id", beneficiaryId).setDate("doa", careplanDate).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                hcp=(HouseholdCareplan)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hcp; 
    }
    public List getHouseholdCareplan(String beneficiaryId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="from HouseholdCareplan hcp where hcp.beneficiaryId=:id"+markedForDeleteQuery;;
            System.err.println("query is "+query);
            list = session.createQuery(query).setString("id", beneficiaryId).list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
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
