/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.CustomIndicatorsReport;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class CustomIndicatorsReportDaoImpl implements CustomIndicatorsReportDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    DaoUtility util=new DaoUtility();
    
    public void saveCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception
    {
        try
        {
            if(rt !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(rt);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
    }
    public void saveOrUpdateCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception
    {
        try
        {
            if(rt !=null)
            {
                CustomIndicatorsReport cirb2=getCustomIndicatorsReport(rt);
                if(cirb2==null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(rt);
                    tx.commit();
                    closeSession(session);
                }
                else
                {
                    rt.setRecordId(cirb2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(rt);
                    tx.commit();
                    closeSession(session);
                    //updateCustomIndicatorsReport(rt);
                }
                
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
    }
    public void updateCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception
    {
        try
        {
            if(rt !=null)
            {
                CustomIndicatorsReport cirb2=getCustomIndicatorsReport(rt);
                if(cirb2 !=null)
                {
                    rt.setRecordId(cirb2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(rt);
                    tx.commit();
                    closeSession(session);
                }
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
    }
    public void deleteCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception
    {
        try
        {
            if(rt !=null)
            {
                CustomIndicatorsReport cirb2=getCustomIndicatorsReport(rt);
                if(cirb2 !=null)
                {
                    rt.setRecordId(cirb2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(rt);
                    tx.commit();
                    closeSession(session);
                }
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
    }
    public int deleteAllCustomIndicatorsReports(String state) throws Exception
    {
        int count=0;
        try
        {
            List list=this.getAllCustomIndicatorsReportByState(state);
            if(list !=null)
            {
                for(int i=0; i<list.size(); i++)
                {
                    CustomIndicatorsReport rt=(CustomIndicatorsReport)list.get(i);
                    deleteCustomIndicatorsReport(rt);
                    count++;
                }
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return count;
    }
    public List getDistinctPeriods() throws Exception
    {
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct rt.reportPeriod from CustomIndicatorsReport rt").list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return list;
    }
    public List getDistinctLevel2Ous() throws Exception
    {
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct rt.level2OuId from CustomIndicatorsReport rt").list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return list;
    }
    public List getDistinctLevel2OusByPartner(String partnerCode) throws Exception
    {
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct rt.level2OuId from CustomIndicatorsReport rt where rt.partnerCode=:pc").setString("pc", partnerCode).list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return list;
    }
    public CustomIndicatorsReport getCustomIndicatorsReport(String lga,String cbo,String partnerCode,String indicatorName, String merCode,String otherDisaggregation,String period) throws Exception
    {
        CustomIndicatorsReport rt = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CustomIndicatorsReport rt where rt.level3OuId=:lg and rt.cboId=:cb and rt.partnerCode=:pc and rt.indicatorName=:indn and rt.merCode=:mer and rt.otherDisaggregation=:od and rt.reportPeriod=:prd")
                    .setString("lg", lga).setString("cb", cbo).setString("pc", partnerCode).setString("indn", indicatorName).setString("mer", merCode).setString("od", otherDisaggregation).setString("prd", period).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                rt=(CustomIndicatorsReport)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return rt;
    }
    public List getDistinctLevel2OusByPeriod(String period) throws Exception
    {
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct rt.level2OuId from CustomIndicatorsReport rt where rt.reportPeriod=:prd").setString("prd", period).list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return list;
    }
    public List getDistinctLevel2OusByPeriodAndPartner(String partnerCode,String period) throws Exception
    {
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct rt.level2OuId from CustomIndicatorsReport rt where rt.partnerCode=:pc and rt.reportPeriod=:prd").setString("pc", partnerCode).setString("prd", period).list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return list;
    }
    public List getAllCustomIndicatorsReports() throws Exception
    {
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from CustomIndicatorsReport rt").list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return list;
    }
    public List getAllCustomIndicatorsReportByState(String state) throws Exception
    {
        List list = null;
        if(state !=null)
        state=state.trim();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from CustomIndicatorsReport rt where rt.level2OuId=:st").setString("st", state).list();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);//throw new Exception(ex);
         }
        return list;
    }
    public CustomIndicatorsReport getCustomIndicatorsReport(CustomIndicatorsReport rt) throws Exception
    {
        CustomIndicatorsReport cirb2=null;
        try
        {
            if(rt !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from CustomIndicatorsReport rt where rt.level2OuId=:st and rt.level3OuId=:lg and rt.cboId=:cb and rt.partnerCode=:pt and rt.indicatorName=:ind and rt.merCode=:mer and rt.otherDisaggregation=:disag and rt.reportPeriod=:prd").setString("st", rt.getLevel2OuId()).setString("lg", rt.getLevel3OuId()).setString("cb", rt.getCboId()).setString("pt", rt.getPartnerCode()).setString("ind", rt.getIndicatorName()).setString("mer", rt.getMerCode()).setString("disag", rt.getOtherDisaggregation()).setString("prd", rt.getReportPeriod()).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    cirb2=(CustomIndicatorsReport)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return cirb2;
    }
    public CustomIndicatorsReport getCustomIndicatorsReport(String state,String lga,String cbo,String indicator, String otherDisaggregation,String period) throws Exception
    {
        CustomIndicatorsReport rt=null;
        if(state !=null)
        state=state.trim();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CustomIndicatorsReport rt where rt.level2OuId=:st and rt.lga=:lg and rt.cbo=:cb and rt.indicator=:ind and rt.sex=:sx and rt.ageCategory=:ac and rt.otherDisaggregation=:disag and rt.reportPeriod=:prd").setString("st", state).setString("lg", lga).setString("cb", cbo).setString("ind", indicator).setString("disag", otherDisaggregation).setString("prd", period).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                rt=(CustomIndicatorsReport)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return rt;
    }
    public List getCustomIndicatorsReportsByLevel2OuPartnerAndPeriod(String state,String partnerCode,String period) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            if(partnerCode !=null && !partnerCode.equalsIgnoreCase("All"))
            list = session.createQuery("from CustomIndicatorsReport rt where rt.level2OuId=:st and rt.partnerCode=:ptc and rt.reportPeriod=:prd").setString("st", state).setString("ptc", partnerCode).setString("prd", period).list();
            else
            list = session.createQuery("from CustomIndicatorsReport rt where rt.level2OuId=:st and rt.reportPeriod=:prd").setString("st", state).setString("prd", period).list();
            tx.commit();
            closeSession(session);   
        }
         catch (Exception ex)
         {
            closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return list;
    }
    public List getAllCustomIndicatorsReportByLga(String state,String lga) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from CustomIndicatorsReport rt where rt.level2OuId=:st and rt.lga=:lg").setString("st", state).setString("lg", lga).list();
            tx.commit();
            closeSession(session);   
        }
         catch (Exception ex)
         {
            closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return list;
    }
    public List getAllCustomIndicatorsReportByCbo(String state,String lga,String cbo) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from CustomIndicatorsReport rt where rt.level2OuId=:st and rt.lga=:lg and and rt.cbo=:cb").setString("st", state).setString("lg", lga).setString("cb", cbo).list();
            tx.commit();
            closeSession(session);   
        }
         catch (Exception ex)
         {
            closeSession(session);
            NomisLogManager.logStackTrace(ex);
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
