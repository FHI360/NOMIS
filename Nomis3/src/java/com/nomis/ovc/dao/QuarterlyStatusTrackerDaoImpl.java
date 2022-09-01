/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.ovc.business.EnrollmentStatusHistory;
import com.nomis.ovc.business.QuarterlyStatusTracker;
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
public class QuarterlyStatusTrackerDaoImpl implements QuarterlyStatusTrackerDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and qst.markedForDelete=0";
    FinancialYearManager fym=new FinancialYearManager();
    
    public void saveEnrollmentStatusHistory(EnrollmentStatusHistory esh) throws Exception
    {
        try
        {
            if(esh !=null)
            {
                QuarterlyStatusTracker qst=new QuarterlyStatusTracker();
                qst.setBeneficiaryId(esh.getBeneficiaryId());
                qst.setBeneficiaryType(esh.getBeneficiaryType());
                qst.setCurrentAge(esh.getCurrentAge());
                qst.setCurrentAgeUnit(esh.getCurrentAgeUnit());
                qst.setDateCreated(esh.getDateCreated());
                qst.setDateOfEnrollmentStatus(esh.getDateOfEnrollmentStatus());
                qst.setDateOfHivStatus(esh.getDateOfHivStatus());
                qst.setEnrolledOnTreatment(esh.getEnrolledOnTreatment());
                qst.setEnrollmentStatus(esh.getEnrollmentStatus());
                qst.setFacilityId(esh.getFacilityId());
                qst.setHivStatus(esh.getHivStatus());
                qst.setLastModifiedDate(esh.getLastModifiedDate());
                qst.setMarkedForDelete(esh.getMarkedForDelete());
                qst.setPointOfUpdate(esh.getPointOfUpdate());
                qst.setRecordedBy(esh.getRecordedBy());
                qst.setReportQuarter(fym.getReportQuarter(esh.getDateOfEnrollmentStatus()));
                if(qst.getReportQuarter() !=null)
                saveQuarterlyStatusTracker(qst);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void saveQuarterlyStatusTracker(QuarterlyStatusTracker qst) throws Exception
    {
        try
        {
            if(qst !=null)
            {
                
                clearExistingRecordForTheReportPeriod(qst.getBeneficiaryId(),qst.getDateOfEnrollmentStatus());
                if(getQuarterlyStatusTracker(qst.getBeneficiaryId(), qst.getDateOfEnrollmentStatus()) ==null)
                {
                    Date startOfQuarter=fym.getStartDateOfQuarter(qst.getDateOfEnrollmentStatus());
                    Date endOfQuarter=fym.getEndDateOfQuarter(qst.getDateOfEnrollmentStatus());
                    List list=getQuarterlyStatusTracker(qst.getBeneficiaryId(), startOfQuarter, endOfQuarter);
                    if(list==null || list.isEmpty())
                    {
                        session = HibernateUtil.getSession();
                        tx = session.beginTransaction();
                        session.save(qst);
                        tx.commit();
                        closeSession(session);
                    }
                    else
                    {
                        QuarterlyStatusTracker qst2=(QuarterlyStatusTracker)list.get(0);
                        if(qst2.getDateOfEnrollmentStatus().before(qst.getDateOfEnrollmentStatus()))
                        qst.setRecordId(qst2.getRecordId());
                        updateQuarterlyStatusTrackerByRecordId(qst);
                    }
                }
            }
            else
            {
                updateQuarterlyStatusTracker(qst);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    /*public void updateQuarterlyStatusTrackerForBeneficiaryByPeriod(QuarterlyStatusTracker qst) throws Exception
    {
        try
        {
            if(qst !=null)
            {
                QuarterlyStatusTracker qst2=getQuarterlyStatusTracker(qst.getBeneficiaryId(), qst.getDateOfEnrollmentStatus());
                if(qst2 !=null)
                {
                    qst.setRecordId(qst2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(qst);
                    tx.commit();
                    closeSession(session);
                }
                else
                {
                    saveQuarterlyStatusTracker(qst);
                }
            }
            
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }*/
    public void updateQuarterlyStatusTracker(QuarterlyStatusTracker qst) throws Exception
    {
        try
        {
            if(qst !=null)
            {
                QuarterlyStatusTracker qst2=getQuarterlyStatusTracker(qst.getBeneficiaryId(), qst.getDateOfEnrollmentStatus());
                if(qst2 !=null)
                {
                    qst.setRecordId(qst2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(qst);
                    tx.commit();
                    closeSession(session);
                }
                else
                {
                    saveQuarterlyStatusTracker(qst);
                }
            }
            
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateQuarterlyStatusTrackerByRecordId(QuarterlyStatusTracker qst) throws Exception
    {
        try
        {
            if(qst !=null)
            {
                QuarterlyStatusTracker qst2=getQuarterlyStatusTracker(qst.getRecordId());
                if(qst2 !=null)
                {
                    //qst.setRecordId(qst2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(qst);
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
    private void clearExistingRecordForTheReportPeriod(String beneficiaryId, Date dateOfEnrollmentStatus) throws Exception
    {
        try
        {
            Date datimReportStartDate=fym.getDATIMStartDate(dateOfEnrollmentStatus);
            Date datimReportEndDate=fym.getDATIMEndDate(dateOfEnrollmentStatus);
            String dateOfCurrentStatusQuery=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(DateManager.convertDateToString(datimReportStartDate, DateManager.DB_DATE_FORMAT),DateManager.convertDateToString(datimReportEndDate,DateManager.DB_DATE_FORMAT));
            QuarterlyStatusTracker qst=null;
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from QuarterlyStatusTracker qst where qst.beneficiaryId=:id"+dateOfCurrentStatusQuery).setString("id", beneficiaryId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    qst=(QuarterlyStatusTracker)obj;
                    deleteQuarterlyStatusTracker(qst);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    /*public void updateHivStatus(String beneficiaryId,int hivStatus,Date dateOfHivStatus,int age,String ageUnit) throws Exception
    {
        try
        {
                FinancialYearManager fym=new FinancialYearManager();
                Date startOfQuarter=fym.getStartDateOfQuarter(dateOfHivStatus);
                Date endOfQuarter=fym.getEndDateOfQuarter(dateOfHivStatus);
                List list=getQuarterlyStatusTracker(beneficiaryId, startOfQuarter,endOfQuarter);
                if(list !=null && !list.isEmpty())
                {
                    QuarterlyStatusTracker qst=(QuarterlyStatusTracker)list.get(0);
                    if(qst.getDateOfHivStatus().before(dateOfHivStatus))
                    {
                        qst.setHivStatus(hivStatus);
                        qst.setDateOfHivStatus(dateOfHivStatus);
                        session = HibernateUtil.getSession();
                        tx = session.beginTransaction();
                        session.update(qst);
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
    }*/
    public void markForDelete(QuarterlyStatusTracker qst) throws Exception
    {
        try
        {
            if(qst !=null)
            {
                QuarterlyStatusTracker qst2=getQuarterlyStatusTracker(qst.getBeneficiaryId(), qst.getDateOfEnrollmentStatus());
                if(qst2 !=null)
                {
                    qst2.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(qst);
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
    public void deleteQuarterlyStatusTracker(QuarterlyStatusTracker qst) throws Exception
    {
        try
        {
            if(qst !=null)
            {
                QuarterlyStatusTracker qst2=getQuarterlyStatusTracker(qst.getBeneficiaryId(), qst.getDateOfEnrollmentStatus());
                if(qst2 !=null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(qst2);
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
    public QuarterlyStatusTracker getQuarterlyStatusTracker(int recordId) throws Exception
    {
        QuarterlyStatusTracker qst=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from QuarterlyStatusTracker qst where qst.recordId=:id").setInteger("id", recordId).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            qst=(QuarterlyStatusTracker)list.get(0);
        }
        return qst;
    }
    public List getQuarterlyStatusTrackerAfterStatusDate(String beneficiaryId, Date startDate) throws Exception
    {
        List list=null;
        try
        {
            String strStartDate=DateManager.convertDateToString(startDate, DateManager.DB_DATE_FORMAT);
            
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from QuarterlyStatusTracker qst where qst.beneficiaryId=:id and qst.dateOfEnrollmentStatus>='"+strStartDate+"' order by qst.dateOfEnrollmentStatus desc").setString("id", beneficiaryId).list();
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
    public List getQuarterlyStatusTracker(String beneficiaryId, Date startDate,Date endDate) throws Exception
    {
        List list=null;
        try
        {
            String strStartDate=DateManager.convertDateToString(startDate, DateManager.DB_DATE_FORMAT);
            String strEndDate=DateManager.convertDateToString(endDate, DateManager.DB_DATE_FORMAT);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from QuarterlyStatusTracker qst where qst.beneficiaryId=:id"+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(strStartDate,strEndDate)+" order by qst.dateOfEnrollmentStatus desc").setString("id", beneficiaryId).list();
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
    public QuarterlyStatusTracker getQuarterlyStatusTracker(String beneficiaryId, Date dateOfEnrollmentStatus) throws Exception
    {
        QuarterlyStatusTracker qst=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from QuarterlyStatusTracker qst where qst.beneficiaryId=:id and qst.dateOfEnrollmentStatus=:dt").setString("id", beneficiaryId).setDate("dt", dateOfEnrollmentStatus).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            qst=(QuarterlyStatusTracker)list.get(0);
        }
        return qst;
    }
    public List getQuarterlyStatusTracker(String beneficiaryId) throws Exception
    {
        List list=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        list = session.createQuery("from QuarterlyStatusTracker qst where qst.beneficiaryId=:id").setString("id", beneficiaryId).list();
        tx.commit();
        closeSession(session);
        return list;
    }
    public void changeBeneficiaryIdInQuarterlyStatusTracker(String oldOvcId, String newOvcId) throws Exception
    {
        List list=getQuarterlyStatusTracker(oldOvcId);
        if(list !=null)
        {
            QuarterlyStatusTracker qst=null;
            for(Object obj:list)
            {
                qst=(QuarterlyStatusTracker)obj;
                qst.setBeneficiaryId(newOvcId);
                if(getQuarterlyStatusTracker(qst.getBeneficiaryId(), qst.getDateOfEnrollmentStatus()) ==null)
                saveQuarterlyStatusTracker(qst);
                else
                updateQuarterlyStatusTracker(qst);

                qst.setBeneficiaryId(oldOvcId);
                this.deleteQuarterlyStatusTracker(qst);
                System.err.println("OVC Id in changeBeneficiaryIdInQuarterlyStatusTracker changed from "+oldOvcId+" to "+newOvcId);
            }
            
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
