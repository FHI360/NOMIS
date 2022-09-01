/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.NutritionStatus;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.util.AppConstant;
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
public class NutritionStatusDaoImpl implements NutritionStatusDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    String markedForDeleteQuery=" and ns.markedForDelete=0";
    public List getNutritionStatusWithoutAgeAtAssessment() throws Exception
    {
        NutritionStatus ns=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from NutritionStatus ns where ns.ageUnitAtAssessment=0").list();
        tx.commit();
        closeSession(session);
        return list;
    }
    public int getNumberOfOvcAssessedByNutritionStatus(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,int enrollmentStatus,int nutritionStatus,String sex) throws Exception
    {
        int count=0;
        SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery=sqg.getAdditionalOrgUnitQuery(rpt);
            String ageQuery=SubQueryGenerator.getAgeAtNutritionAssessmentQuery(startAge,endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String nutritionStatusQuery=SubQueryGenerator.getCurrentNutritionStatusQuery(nutritionStatus);
            String enrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            String query="select count (distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcNutritionAssessmentNutritionStatusQuarterlyStatusTrackerOrganizationUnitQuery()+additionalOrgUnitQuery+ageQuery+sexQuery+enrollmentStatusQuery+nutritionStatusQuery+sqg.getDateOfCurrentNutritionStatusQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteQuery;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                count=Integer.parseInt(list.get(0).toString());
            }
        return count;
    }
    public List getListOfOvcAssessedByNutritionStatus(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,int enrollmentStatus,int nutritionStatus,String sex) throws Exception
    {
        List mainList=new ArrayList();
        SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery=sqg.getAdditionalOrgUnitQuery(rpt);
            String ageQuery=SubQueryGenerator.getAgeAtNutritionAssessmentQuery(startAge,endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String nutritionStatusQuery=SubQueryGenerator.getCurrentNutritionStatusQuery(nutritionStatus);
            String enrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            String query=SubQueryGenerator.getHheOvcNutritionAssessmentNutritionStatusQuarterlyStatusTrackerOrganizationUnitQuery()+additionalOrgUnitQuery+ageQuery+sexQuery+enrollmentStatusQuery+nutritionStatusQuery+sqg.getDateOfCurrentNutritionStatusQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteQuery;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                List uniqueIdList=new ArrayList();
                Ovc ovc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    ovc=(Ovc)objArray[1];
                    if(!uniqueIdList.contains(ovc.getOvcId()))
                    {
                        mainList.add(ovc);
                        uniqueIdList.add(ovc.getOvcId());
                    }
                }
            }
        return mainList;
    }
    public NutritionStatus getNutritionStatus(String ovcId) throws Exception
    {
        NutritionStatus ns=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from NutritionStatus ns where ns.ovcId=:id").setString("id", ovcId).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            ns=(NutritionStatus)list.get(0);
        }
        return ns;
    }
    public List getNutritionStatusRecordsForExport(ReportParameterTemplate rpt) throws Exception
    {
        List mainList=new ArrayList();
        SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery=sqg.getAdditionalOrgUnitQuery(rpt);
            String query=SubQueryGenerator.getHheOvcNutritionStatusOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getNutritionStatusLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate());
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[2]);
                }
            }
        return mainList;
    }
    private void saveNutritionStatus(NutritionStatus ns) throws Exception
    {
        //if(getNutritionStatus(ns.getOvcId()) ==null)
        //{
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(ns);
            tx.commit();
            closeSession(session);
        //}
    }
    public void saveOrUpdateNutritionStatus(NutritionStatus ns) throws Exception
    {
        if(getNutritionStatus(ns.getOvcId()) ==null)
        {
            saveNutritionStatus(ns);
        }
        else
        {
            updateNutritionStatus(ns);
        }
    }
    private void updateNutritionStatus(NutritionStatus ns) throws Exception
    {
        //if(getNutritionStatus(ns.getOvcId()) !=null)
        //{
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(ns);
            tx.commit();
            closeSession(session);
        //}
    }
    public void markForDelete(NutritionStatus ns) throws Exception
    {
        if(getNutritionStatus(ns.getOvcId()) !=null)
        {
            ns.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(ns);
            tx.commit();
            closeSession(session);
        }
    }
    public void deleteNutritionStatus(NutritionStatus ns) throws Exception
    {
        if(getNutritionStatus(ns.getOvcId()) !=null)
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(ns);
            tx.commit();
            closeSession(session);
        }
    }
    private void closeSession(Session session)
    {
        try
        {
            if(session!=null && session.isOpen())
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
