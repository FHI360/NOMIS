/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.ovc.business.EnrollmentStatusHistory;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class EnrollmentStatusHistoryDaoImpl implements EnrollmentStatusHistoryDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and esh.markedForDelete=0";
    public void saveEnrollmentStatusHistory(EnrollmentStatusHistory esh) throws Exception
    {
        try
        {
            if(esh !=null && getEnrollmentStatusHistory(esh.getBeneficiaryId(), esh.getDateOfEnrollmentStatus()) ==null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(esh);
                tx.commit();
                closeSession(session);
                QuarterlyStatusTrackerDao qstdao=new QuarterlyStatusTrackerDaoImpl();
                qstdao.saveEnrollmentStatusHistory(esh); 
            }
            else
            {
                updateEnrollmentStatusHistory(esh);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateEnrollmentStatusHistory(EnrollmentStatusHistory esh) throws Exception
    {
        try
        {
            if(esh !=null)
            {
                EnrollmentStatusHistory esh2=getEnrollmentStatusHistory(esh.getBeneficiaryId(), esh.getDateOfEnrollmentStatus());
                if(esh2 !=null)
                {
                    esh.setRecordId(esh2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(esh);
                    tx.commit();
                    closeSession(session);
                    QuarterlyStatusTrackerDao qstdao=new QuarterlyStatusTrackerDaoImpl();
                    qstdao.saveEnrollmentStatusHistory(esh);
                }
                else
                {
                    saveEnrollmentStatusHistory(esh);
                }
            }
            
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateEnrollmentStatusHistoryByRecordId(EnrollmentStatusHistory esh) throws Exception
    {
        try
        {
            if(esh !=null)
            {
                EnrollmentStatusHistory esh2=getEnrollmentStatusHistory(esh.getRecordId());
                if(esh2 !=null)
                {
                    //esh.setRecordId(esh2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(esh);
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
    public void updateHivStatus(String beneficiaryId,int hivStatus,Date dateOfHivStatus,int age,int ageUnit) throws Exception
    {
        try
        {
                FinancialYearManager fym=new FinancialYearManager();
                Date startOfQuarter=fym.getStartDateOfQuarter(dateOfHivStatus);
                Date endOfQuarter=fym.getEndDateOfQuarter(dateOfHivStatus);
                List list=getEnrollmentStatusHistory(beneficiaryId, startOfQuarter,endOfQuarter);
                if(list !=null && !list.isEmpty())
                {
                    EnrollmentStatusHistory esh=(EnrollmentStatusHistory)list.get(0);
                    if(esh.getDateOfHivStatus().before(dateOfHivStatus))
                    {
                        esh.setCurrentAgeUnit(ageUnit);
                        esh.setHivStatus(hivStatus);
                        esh.setDateOfHivStatus(dateOfHivStatus);
                        session = HibernateUtil.getSession();
                        tx = session.beginTransaction();
                        session.update(esh);
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
    public void markForDelete(EnrollmentStatusHistory esh) throws Exception
    {
        try
        {
            if(esh !=null)
            {
                EnrollmentStatusHistory esh2=getEnrollmentStatusHistory(esh.getBeneficiaryId(), esh.getDateOfEnrollmentStatus());
                if(esh2 !=null)
                {
                    esh2.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(esh);
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
    public void deleteEnrollmentStatusHistory(EnrollmentStatusHistory esh) throws Exception
    {
        try
        {
            if(esh !=null)
            {
                EnrollmentStatusHistory esh2=getEnrollmentStatusHistory(esh.getBeneficiaryId(), esh.getDateOfEnrollmentStatus());
                if(esh2 !=null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(esh2);
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
    public EnrollmentStatusHistory getEnrollmentStatusHistory(int recordId) throws Exception
    {
        EnrollmentStatusHistory esh=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from EnrollmentStatusHistory esh where esh.recordId=:id").setInteger("id", recordId).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            esh=(EnrollmentStatusHistory)list.get(0);
        }
        return esh;
    }
    public List getEnrollmentStatusHistory(String beneficiaryId, Date startDate,Date endDate) throws Exception
    {
        List list=null;
        try
        {
            String strStartDate=DateManager.convertDateToString(startDate, DateManager.DB_DATE_FORMAT);
            String strEndDate=DateManager.convertDateToString(endDate, DateManager.DB_DATE_FORMAT);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from EnrollmentStatusHistory esh where esh.beneficiaryId=:id"+sqg.getEnrollmentStatusHistoryDateOfCurrentStatusQuery(strStartDate,strEndDate)+" order by esh.dateOfEnrollmentStatus desc").setString("id", beneficiaryId).list();
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
    public EnrollmentStatusHistory getEnrollmentStatusHistory(String beneficiaryId, Date dateOfEnrollmentStatus) throws Exception
    {
        EnrollmentStatusHistory esh=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from EnrollmentStatusHistory esh where esh.beneficiaryId=:id and esh.dateOfEnrollmentStatus=:dt").setString("id", beneficiaryId).setDate("dt", dateOfEnrollmentStatus).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            esh=(EnrollmentStatusHistory)list.get(0);
        }
        return esh;
    }
    public List getEnrollmentStatusHistory(String beneficiaryId) throws Exception
    {
        List list=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        list = session.createQuery("from EnrollmentStatusHistory esh where esh.beneficiaryId=:id").setString("id", beneficiaryId).list();
        tx.commit();
        closeSession(session);
        return list;
    }
    public void changeBeneficiaryIdInEnrollmentStatusHistory(String oldOvcId, String newOvcId) throws Exception
    {
        List list=getEnrollmentStatusHistory(oldOvcId);
        if(list !=null)
        {
            EnrollmentStatusHistory esh=null;
            for(Object obj:list)
            {
                esh=(EnrollmentStatusHistory)obj;
                esh.setBeneficiaryId(newOvcId);
                if(getEnrollmentStatusHistory(esh.getBeneficiaryId(), esh.getDateOfEnrollmentStatus()) ==null)
                saveEnrollmentStatusHistory(esh);
                else
                updateEnrollmentStatusHistory(esh);

                esh.setBeneficiaryId(oldOvcId);
                this.deleteEnrollmentStatusHistory(esh);
                System.err.println("OVC Id in EnrollmentStatusHistory changed from "+oldOvcId+" to "+newOvcId);
            }
            
        }
    }
    /*public void changeBeneficiaryIdInEnrollmentStatusHistory(String oldBeneficiaryId, String newBeneficiaryId) throws Exception
    {
        List list=this.getEnrollmentStatusHistory(oldBeneficiaryId);
        if(list !=null)
        {
            for(Object obj:list)
            {
                EnrollmentStatusHistory ess=(EnrollmentStatusHistory)obj;
                ess.setBeneficiaryId(newBeneficiaryId);
                if(this.getEnrollmentStatusHistory(ess.getBeneficiaryId(), ess.getDateOfEnrollmentStatus())==null)
                saveEnrollmentStatusHistory(ess);
                else
                updateEnrollmentStatusHistory(ess);
                System.err.println("Ovc Id in OvcServiceDaoImpl changed from "+oldBeneficiaryId+" to "+newBeneficiaryId);
            }
        }
    }*/
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
