/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.DatimReportTemplate;
import com.nomis.reports.utils.DatimReportTemplateTest;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class DatimReportDaoImpl implements DatimReportDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    DaoUtility util=new DaoUtility();
    public void saveDatimReportTemplateTest(DatimReportTemplateTest rt) throws Exception
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
    public void saveDatimReportTemplate(DatimReportTemplate rt) throws Exception
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
    public void saveOrUpdateDatimReportTemplate(DatimReportTemplate rt) throws Exception
    {
        try
        {
            if(rt !=null)
            {
                if(getDatimReportTemplate(rt)==null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(rt);
                    tx.commit();
                    closeSession(session);
                }
                else
                updateDatimReportTemplate(rt);
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
    }
    public void updateDatimReportTemplate(DatimReportTemplate rt) throws Exception
    {
        try
        {
            if(rt !=null)
            {
                DatimReportTemplate drt2=getDatimReportTemplate(rt);
                if(drt2 !=null)
                {
                    rt.setRecordId(drt2.getRecordId());
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
    public void deleteDatimReportTemplate(DatimReportTemplate rt) throws Exception
    {
        try
        {
            if(rt !=null)
            {
                DatimReportTemplate drt2=getDatimReportTemplate(rt);
                if(drt2 !=null)
                {
                    rt.setRecordId(drt2.getRecordId());
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
    public int deleteAllDatimReportTemplates(String Level2Ou) throws Exception
    {
        int count=0;
        try
        {
            List list=this.getAllDatimReportTemplateByLevel2Ou(Level2Ou);
            if(list !=null)
            {
                for(int i=0; i<list.size(); i++)
                {
                    DatimReportTemplate rt=(DatimReportTemplate)list.get(i);
                    deleteDatimReportTemplate(rt);
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
            list = session.createQuery("select distinct rt.period from DatimReportTemplate rt").list();
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
            list = session.createQuery("select distinct rt.level2Ou from DatimReportTemplate rt").list();
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
    public List getDistinctLevel3Ous(String Level2OuName) throws Exception
    {
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct rt.level3Ou from DatimReportTemplate rt  where rt.level2Ou=:st").setString("st", Level2OuName).list();
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
            list = session.createQuery("select distinct rt.level2Ou from DatimReportTemplate rt where rt.partnerCode=:pc").setString("pc", partnerCode).list();
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
    public DatimReportTemplate getDatimReportTemplate(String Level3Ou,String cbo,String partnerCode,String period) throws Exception
    {
        DatimReportTemplate rt = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from DatimReportTemplate rt where rt.level3Ou=:lg and rt.cbo=:cb and rt.partnerCode=:pc and rt.period=:prd")
                    .setString("lg", Level3Ou).setString("cb", cbo).setString("pc", partnerCode).setString("prd", period).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                rt=(DatimReportTemplate)list.get(0);
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
            list = session.createQuery("select distinct rt.level2Ou from DatimReportTemplate rt where rt.period=:prd").setString("prd", period).list();
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
            list = session.createQuery("select distinct rt.level2Ou from DatimReportTemplate rt where rt.partnerCode=:pc and rt.period=:prd").setString("pc", partnerCode).setString("prd", period).list();
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
    public List getAllDatimReportTemplates() throws Exception
    {
        List list = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from DatimReportTemplate rt").list();
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
    public List getAllDatimReportTemplateByLevel2Ou(String Level2Ou) throws Exception
    {
        List list = null;
        if(Level2Ou !=null)
        Level2Ou=Level2Ou.trim();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from DatimReportTemplate rt where rt.level2Ou=:st").setString("st", Level2Ou).list();
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
    public DatimReportTemplate getDatimReportTemplate(DatimReportTemplate rt) throws Exception
    {
        DatimReportTemplate drt2=null;
        try
        {
            if(rt !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from DatimReportTemplate rt where rt.level2Ou=:st and rt.level3Ou=:lg and rt.cbo=:cb and rt.partnerCode=:ptc and rt.period=:prd").setString("st", rt.getLevel2Ou()).setString("lg", rt.getLevel3Ou()).setString("cb", rt.getCbo()).setString("ptc", rt.getPartnerName()).setString("prd", rt.getPeriod()).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    drt2=(DatimReportTemplate)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return drt2;
    }
    public DatimReportTemplate getDatimReportTemplate(String Level2Ou,String Level3Ou,String period) throws Exception
    {
        DatimReportTemplate rt=null;
        if(Level2Ou !=null)
        Level2Ou=Level2Ou.trim();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from DatimReportTemplate rt where rt.level2Ou=:st and rt.level3Ou=:lg  and rt.period=:prd").setString("st", Level2Ou).setString("lg", Level3Ou).setString("prd", period).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                rt=(DatimReportTemplate)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return rt;
    }
    public DatimReportTemplate getDatimReportTemplate(String level2Ou,String level3Ou,String cbo, String partnerCode,String period) throws Exception
    {
        DatimReportTemplate rt=null;
        if(level2Ou !=null)
        level2Ou=level2Ou.trim();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from DatimReportTemplate rt where rt.level2Ou=:st and rt.level3Ou=:lg and rt.cbo=:cb and rt.partnerCode=:ptc and rt.period=:prd").setString("st", level2Ou).setString("lg", level3Ou).setString("cb", cbo).setString("ptc", partnerCode).setString("prd", period).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                rt=(DatimReportTemplate)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            NomisLogManager.logStackTrace(ex);
         }
        return rt;
    }
    public List getAllDatimReportTemplateByLevel3Ou(String Level2Ou,String Level3Ou) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from DatimReportTemplate rt where rt.level2Ou=:st and rt.level3Ou=:lg").setString("st", Level2Ou).setString("lg", Level3Ou).list();
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
    public List getAllDatimReportTemplateByCbo(String Level2Ou,String Level3Ou,String cbo) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from DatimReportTemplate rt where rt.level2Ou=:st and rt.level3Ou=:lg and and rt.cbo=:cb").setString("st", Level2Ou).setString("lg", Level3Ou).setString("cb", cbo).list();
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
    public void saveDatimReportData(List datimReportTemplateList,String userName) throws Exception
    {
        DatimReportDao drtdao=new DatimReportDaoImpl();
        try
        {
            if(datimReportTemplateList !=null)
            {
                DatimReportTemplateTest drtt=null;
                for(Object obj:datimReportTemplateList)
                {
                    DatimReportTemplate dform=(DatimReportTemplate)obj;
                    drtt=new DatimReportTemplateTest();
                    drtt.setCbo(dform.getCbo());
                    drtt.setLevel2Ou(dform.getLevel2Ou());
                    drtt.setLevel3Ou(dform.getLevel3Ou());
                    drtt.setOvc_serv1To4(dform.getOvc_serv1To4());
                    drtt.setOvc_serv1To9(dform.getOvc_serv1To9());
                    drtt.setOvc_serv5To9(dform.getOvc_serv5To9());
                    drtt.setOvc_servFemale10To14(dform.getOvc_servActive10to14Female());
                    drtt.setOvc_servFemale15To17(dform.getOvc_servActive15to17Female());
                    drtt.setOvc_servFemale18To24(dform.getOvc_servActive18to24Female());
                    drtt.setOvc_servFemale25AndAbove(dform.getOvc_servActive18to24Female());
                    drtt.setOvc_servMale10To14(dform.getOvc_servActive10to14Male());
                    drtt.setOvc_servMale15To17(dform.getOvc_servActive15to17Male());
                    drtt.setOvc_servMale18To24(dform.getOvc_servActive18to24Male());
                    drtt.setOvc_servMale25AndAbove(dform.getOvc_servActive18to24Male());
                    drtt.setOvc_servLessThan1(dform.getOvc_servActiveLessThan1Female());
                    this.saveDatimReportTemplateTest(drtt);
                    /*dform.setDateCreated(DateManager.getNewDateInstance());
                    dform.setLastModifiedDate(DateManager.getNewDateInstance());
                    dform.setRecordedBy(userName);
                    DatimReportTemplate dupDrt=drtdao.getDatimReportTemplate(dform);
                    if(dupDrt==null)
                    drtdao.saveDatimReportTemplate(dform);
                    else
                    {
                        dform.setRecordId(dupDrt.getRecordId());
                        drtdao.updateDatimReportTemplate(dform);
                    }*/
                }
            }
        }
        catch(Exception ex)
        {
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
