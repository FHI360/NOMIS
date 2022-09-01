/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.HivStatusHistory;
import com.nomis.ovc.util.AppConstant;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class HivStatusHistoryDaoImpl implements HivStatusHistoryDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    String markedForDeleteQuery=" and hsh.markedForDelete=0";
    public void deleteHivStatusRecordsOfChildrenAtRiskOfHivBeforeOctober2018() throws Exception
    {
        String query="delete from APP.HIVSTATUSHISTORY where newhivstatus="+AppConstant.HIV_TEST_REQUIRED_NUM+" and dateofnewstatus <'2018-10-01'";
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        session.createSQLQuery(query).executeUpdate();
        tx.commit();
        closeSession(session);
    }
    public void saveHivStatusHistory(HivStatusHistory hsh) throws Exception
    {
        try
        {
            if(hsh !=null && getHivStatusHistory(hsh.getBeneficiaryId(), hsh.getDateOfNewStatus()) ==null)
            {
                //System.err.println("hsh.getBeneficiaryId in saveHivStatusManager is "+hsh.getBeneficiaryId());
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(hsh);
                tx.commit();
                closeSession(session);
            }
            else
            {
                updateHivStatusHistory(hsh);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateHivStatusHistory(HivStatusHistory hsh) throws Exception
    {
        //System.err.println("Inside updateHivStatusManager ");
            if(hsh !=null)
            {
                HivStatusHistory hsh2=getHivStatusHistory(hsh.getBeneficiaryId(), hsh.getDateOfNewStatus());
                if(hsh2 !=null)
                {
                    hsh.setRecordId(hsh2.getRecordId()); 
                    hsh.setDateCreated(hsh2.getDateCreated());
                    if(hsh.getNewHivStatus()==AppConstant.HIV_POSITIVE_NUM && hsh.getEnrolledOnTreatment()==0)
                    {
                        hsh.setEnrolledOnTreatment(hsh2.getEnrolledOnTreatment());
                        hsh.setFacilityId(hsh2.getFacilityId());
                    }
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(hsh);
                    tx.commit();
                    closeSession(session);
                    System.err.println(hsh.getNewHivStatus()+" HIV status history updated ");
                }
                else
                {
                    saveHivStatusHistory(hsh);
                }
            }
    }
    public void deleteHivStatusHistory(HivStatusHistory hsh) throws Exception
    {
        
    }
    public HivStatusHistory getHivStatusHistory(int recordId) throws Exception
    {
        HivStatusHistory hsh=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from HivStatusHistory hsh where hsh.recordId=:id").setInteger("id", recordId).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            hsh=(HivStatusHistory)list.get(0);
        }
        return hsh;
    }
    public HivStatusHistory getHivStatusHistory(String beneficiaryId, Date dateOfNewStatus) throws Exception
    {
        HivStatusHistory hsh=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from HivStatusHistory hsh where hsh.beneficiaryId=:id and hsh.dateOfNewStatus=:dt").setString("id", beneficiaryId).setDate("dt", dateOfNewStatus).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            hsh=(HivStatusHistory)list.get(0);
        }
        return hsh;
    }
    public List getHivStatusHistory(String beneficiaryId) throws Exception
    {
        List list=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        list = session.createQuery("from HivStatusHistory hsh where hsh.beneficiaryId=:id").setString("id", beneficiaryId).list();
        tx.commit();
        closeSession(session);
        return list;
    }
    public void changeBeneficiaryIdInHivStatusHistory(String oldOvcId, String newOvcId) throws Exception
    {
        List list=getHivStatusHistory(oldOvcId);
        if(list !=null)
        {
            HivStatusHistory hsh=null;
            for(Object obj:list)
            {
                hsh=(HivStatusHistory)obj;
                hsh.setBeneficiaryId(newOvcId);
                if(getHivStatusHistory(hsh.getBeneficiaryId(), hsh.getDateOfNewStatus()) ==null)
                saveHivStatusHistory(hsh);
                else
                updateHivStatusHistory(hsh);

                hsh.setBeneficiaryId(oldOvcId);
                this.deleteHivStatusHistory(hsh);
                System.err.println("OVC Id in HivSatusHistory changed from "+oldOvcId+" to "+newOvcId);
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
