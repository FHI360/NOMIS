/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.NutritionAssessment;
import com.nomis.ovc.business.NutritionStatus;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class NutritionAssessmentDaoImpl implements NutritionAssessmentDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public List getNutritionAssessmentRecords(ReportParameterTemplate rpt) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcNutritionAssessmentOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getNutritionAssessmentDateQuery(rpt.getStartDate(),rpt.getEndDate());
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession();
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                Ovc ovc=null;
                NutritionAssessment na=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    na=(NutritionAssessment)objArray[2];
                    ovc=(Ovc)objArray[1];
                    ovc.setHhe(hhe);
                    na.setOvc(ovc);
                    mainList.add(na);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession();
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getNutritionAssessmentRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and na.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcNutritionAssessmentOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getNutritionAssessmentLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession();
            if(list !=null)
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[2]);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession();
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getListofOvcWithNutrionalAssessmentByCohort(String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List listOfOvc=new ArrayList();
        try
        {
            List ovcList=null;//getListofOvcByPeriod(additionalQueryCriteria,startDate,endDate);
            if(ovcList !=null)
            {
                List naList=null;
                for(int i=0; i<ovcList.size(); i++)
                {
                    Ovc ovc=(Ovc)ovcList.get(i);
                    naList=getListOfNutrionalAssessmentByOvcId("",ovc.getOvcId());
                    if(naList !=null)
                    {
                        NutritionAssessment na=null;
                        for(int j=0; j<naList.size(); j++)
                        {
                            na=(NutritionAssessment)naList.get(j);
                            Ovc naOvc=ovc;
                            //naOvc.setCurrentNutritionAssessment(na);
                            listOfOvc.add(naOvc);
                        }
                        
                    }
                }
            }
            
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return listOfOvc;
    }
    public List getListOfNutrionalAssessmentByOvcId(String additionalQueryCriteria,String ovcId) throws Exception
    {
        List list=new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from NutritionAssessment na where na.ovcId=:id "+additionalQueryCriteria).setString("id", ovcId).list();
            tx.commit();
            session.close();
            if(list==null)
            list=new ArrayList();
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return list;
    }
    /*public List getListofOvcByPeriod(String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List listOfOvc=new ArrayList();
        OvcDao dao=new OvcDaoImpl();
        try
        {
            List ovcList=dao.getListOfOvcEnrolledWithinTheReportPeriod(additionalQueryCriteria, startDate, endDate);
            
           if(ovcList !=null && !ovcList.isEmpty())
            {
                for(Object obj:ovcList)
                {
                    Ovc ovc=(Ovc)obj;
                    listOfOvc.add(ovc);
                }
            }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return listOfOvc;
    }*/
    public int getNumberOfOvcWhoAreOverweight(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        int numberOfOvc=0;
       try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select count(distinct na.ovcId)").list();//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.bmi between 25 and 29.9"+additionalQueryCriteria).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
           {
               numberOfOvc=Integer.parseInt(list.get(0).toString());
           }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return numberOfOvc;
    }
    public int getNumberOfOvcThatAreObesse(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        int numberOfOvc=0;
       try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select count(distinct na.ovcId)").list();//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.bmi between 30 and 40"+additionalQueryCriteria).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
           {
               numberOfOvc=Integer.parseInt(list.get(0).toString());
           }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return numberOfOvc;
    }
    public int getNumberOfOvcThatAreMorbidityObesse(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        int numberOfOvc=0;
       try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select count(distinct na.ovcId) ").list();//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.bmi > 40"+additionalQueryCriteria).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
           {
               numberOfOvc=Integer.parseInt(list.get(0).toString());
           }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return numberOfOvc;
    }
    public int getNumberofSeverelyMalnourishedOvc(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        int numberOfOvc=0;
       try
        {
            String query="select count(distinct na.ovcId)";//;//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.muac='red'"+additionalQueryCriteria;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
           {
               numberOfOvc=Integer.parseInt(list.get(0).toString());
           }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return numberOfOvc;
    }
    public int getNumberofSeverelyMalnourishedOvcIdentifiedAndServedWithinTheReportPeriod(String additionalQueryCriteria,String startDate,String endDate,boolean curretlyEnrolled) throws Exception
    {
        List list=null;
        int numberOfOvc=0;
        String serviceStartDateQuery=" ";
        if(startDate !=null && startDate.indexOf("-") !=-1)
        {
            serviceStartDateQuery=" and service.servicedate>='"+startDate+"'";
        }
       try
        {
            String query="select count(distinct na.ovcId)";//;//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na,OvcService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId and na.ovcId=service.ovcId) and na.muac='red'"+additionalQueryCriteria;//+util.getNutritionalAssessmentDateQuery(startDate, endDate);//+util.getOvcNutritionalSupportQuery(null)+serviceStartDateQuery;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
           {
               numberOfOvc=Integer.parseInt(list.get(0).toString());
           }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return numberOfOvc;
    }
    public List getListofSeverelyMalnourishedOvcIdentifiedAndServedWithinTheReportPeriod(String additionalQueryCriteria,String startDate,String endDate,boolean curretlyEnrolled) throws Exception
    {
        List mainList=new ArrayList();
        String serviceStartDateQuery=" ";
        if(startDate !=null && startDate.indexOf("-") !=-1)
        {
            serviceStartDateQuery=" and service.servicedate>='"+startDate+"'";
        }
        try
        {
            String query="";//util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na,OvcService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId and na.ovcId=service.ovcId) and na.muac='red'"+additionalQueryCriteria;//+util.getNutritionalAssessmentDateQuery(startDate, endDate);//+util.getOvcNutritionalSupportQuery(null)+serviceStartDateQuery;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
           {
               List idList=new ArrayList();
               for(Object obj:list)
               {
                   Object[] objArray=(Object[])obj;
                   Ovc ovc=(Ovc)objArray[1];
                   if(!idList.contains(ovc.getOvcId()))
                   {
                       mainList.add(ovc);
                       idList.add(ovc.getOvcId());
                   }
               }
           }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return mainList;
    }
    public int getNumberofModeratelyMalnourishedOvc(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        int numberOfOvc=0;
       try
        {
            String query="select count(distinct na.ovcId)";//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.muac='yellow'"+additionalQueryCriteria;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
            {
               numberOfOvc=Integer.parseInt(list.get(0).toString());
            }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return numberOfOvc;
    }
    public int getNumberofWellNourishedOvc(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        int numberOfOvc=0;
       try
        {
            String query="select count(distinct na.ovcId)";//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.muac='green'"+additionalQueryCriteria;
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
            {
               numberOfOvc=Integer.parseInt(list.get(0).toString());
            }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return numberOfOvc;
    }
    public List getListofSeverelyMalnourishedOvc(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        List listOfOvc=new ArrayList();
        List idList=new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("").list();//(util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.muac='red'"+additionalQueryCriteria).list();
            tx.commit();
            session.close();
           if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    Ovc ovc=(Ovc)objArray[1];
                    if(!idList.contains(ovc.getOvcId()))
                    {
                        idList.add(ovc.getOvcId());
                        listOfOvc.add(ovc);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return listOfOvc;
    }
    public List getListofModeratelyMalnourishedOvc(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        List listOfOvc=new ArrayList();
        List idList=new ArrayList();
       try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("").list();//util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.muac='yellow'"+additionalQueryCriteria).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    Ovc ovc=(Ovc)objArray[1];
                    if(!idList.contains(ovc.getOvcId()))
                    {
                        idList.add(ovc.getOvcId());
                        listOfOvc.add(ovc);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return listOfOvc;
    }
    public List getListofWellNourishedOvc(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        List listOfOvc=new ArrayList();
        List idList=new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("").list();//util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) and na.muac='green'"+additionalQueryCriteria).list();
            tx.commit();
            session.close();
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    Ovc ovc=(Ovc)objArray[1];
                    if(!idList.contains(ovc.getOvcId()))
                    {
                        idList.add(ovc.getOvcId());
                        listOfOvc.add(ovc);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return listOfOvc;
    }
    public List getNutritionalAssessmentByCommunityCode(String communityCode) throws Exception
    {
        List list = new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("").list();//util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId and hhe.communityCode=:ccode").setString("ccode", communityCode).list();
            tx.commit();
            session.close();
        }
         catch (HibernateException he)
         {
             session.close();
            throw new Exception(he);
         }
        return list;
    }
    public List getDistinctOvcIdByCommunityCode(String communityCode) throws Exception
    {
        List list = new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct na.ovcId ").list();//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId and hhe.communityCode=:ccode").setString("ccode", communityCode).list();
            tx.commit();
            session.close();
        }
         catch (HibernateException he)
         {
             session.close();
            throw new Exception(he);
         }
        return list;
    }
    public List getDistinctOvcIdFromNutritionAssessmentRecord() throws Exception
    {
        List list = new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct na.ovcId from NutritionAssessment na").list();
            tx.commit();
            session.close();
        }
         catch (HibernateException he)
         {
             session.close();
            throw new Exception(he);
         }
        return list;
    }
    public List getDistinctOvcIdAndDateOfAssessmentByCommunityCode(String communityCode) throws Exception
    {
        List list = new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct na.ovcId,na.dateOfAssessment ").list();//+util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId and hhe.communityCode=:ccode").setString("ccode", communityCode).list();
            tx.commit();
            session.close();
        }
         catch (HibernateException he)
         {
             session.close();
            throw new Exception(he);
         }
        return list;
    }
    public List getDistinctAssessmentNo() throws Exception
    {
         List list = new ArrayList();

            try
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("select distinct na.assessmentNo from NutritionAssessment na").list();
                tx.commit();
                session.close();
            }
            catch (HibernateException he)
            {
                closeSession();
                he.printStackTrace();
            }
            catch(Exception ex)
            {
                closeSession();
            }
           return list;
    }
    public void saveNutritionAssessment(NutritionAssessment na) throws Exception
    {
        try
        {
            if(na.getDateOfAssessment()==null)
            return;
            if(getNutritionAssessment(na.getOvcId(),na.getDateOfAssessment()) ==null)
            {
                na.setAssessmentNo(getAssessmentNumber(na));
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(na);
                tx.commit();
                session.close();
                assignAssessmentNumber(na);
                setNutritionAssessmentsWithDateOfLastWeight(na);
                saveOrUpdateNutritionStatus(na);
            }
        }
        catch (HibernateException he)
        {
            closeSession();
            he.printStackTrace();
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
    }
    public void updateNutritionAssessment(NutritionAssessment na) throws Exception
    {
        try
        {
            
            if(na.getDateOfAssessment()==null)
            return;
            NutritionAssessment na2=getNutritionAssessment(na.getOvcId(), na.getDateOfAssessment());
            if(na2 !=null)
            {
                AppUtility appUtil=new AppUtility();
               if(DateManager.compareDates(na2.getLastModifiedDate(), na.getLastModifiedDate())) 
               {
                    na.setRecordId(na2.getRecordId()); 
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(na);
                    tx.commit();
                    session.close();
                    saveOrUpdateNutritionStatus(na);
               }
            }
        }
        catch (HibernateException he)
        {
            closeSession();
            he.printStackTrace();
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
    }
    private void saveOrUpdateNutritionStatus(NutritionAssessment na) throws Exception
    {
        if(na !=null)
        {
            NutritionStatusDao nsdao=new NutritionStatusDaoImpl();
            NutritionStatus ns=new NutritionStatus();
            ns.setCurrentNutritionalStatus(na.getNutritionalStatus());
            ns.setDateOfCurrentNutritionalStatus(na.getDateOfAssessment());
            ns.setLastModifiedDate(na.getLastModifiedDate());
            ns.setMarkedForDelete(na.getMarkedForDelete());
            ns.setOvcId(na.getOvcId());
            ns.setAgeAtAssessment(na.getAgeAtAssessment());
            ns.setAgeUnitAtAssessment(na.getAgeUnitAtAssessment());
            ns.setHeight(na.getHeight());
            ns.setWeight(na.getWeight());
            nsdao.saveOrUpdateNutritionStatus(ns);
        }
    }
    public int updateNutritionStatusWithAgeHeightAndWeight() throws Exception
    {
        int count=0;
        NutritionStatusDao nsdao=new NutritionStatusDaoImpl();
        List list=nsdao.getNutritionStatusWithoutAgeAtAssessment();
        if(list !=null && !list.isEmpty())
        {
            NutritionStatus ns=null;
            for(Object obj:list)
            {
                ns=(NutritionStatus)obj;
                NutritionAssessment na=this.getNutritionAssessment(ns.getOvcId(), ns.getDateOfCurrentNutritionalStatus());
                if(na !=null)
                {
                    ns.setAgeAtAssessment(na.getAgeAtAssessment());
                    ns.setAgeUnitAtAssessment(na.getAgeUnitAtAssessment());
                    ns.setHeight(na.getHeight());
                    ns.setWeight(na.getWeight());
                    nsdao.saveOrUpdateNutritionStatus(ns);
                    count++;
                }
            }
        }
        return count;
    }
    public void markedForDelete(NutritionAssessment na) throws Exception
    {
        try
        {
            na.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
            updateNutritionAssessment(na);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void deleteNutritionAssessment(NutritionAssessment na) throws Exception
    {
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(na);
            tx.commit();
            session.close();
            assignAssessmentNumber(na);
            setNutritionAssessmentsWithDateOfLastWeight(na);
            //util.saveDeletedRecord(na.getOvcId(),null, "nutritionAssessment", na.getDateOfAssessment());
        }
        catch (HibernateException he)
        {
            closeSession();
            he.printStackTrace();
        }
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
    }
    private void closeSession()
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
    public NutritionAssessment getNutritionAssessment(int id) throws Exception
    {
        NutritionAssessment na=null;
        List list = new ArrayList();

            try
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("from NutritionAssessment na where na.id =:uid").setInteger("uid", id).list();
                tx.commit();
                session.close();
            }
            catch (HibernateException he)
            {
                closeSession();
                he.printStackTrace();
            }
            catch(Exception ex)
            {
                closeSession();
            }
           if(list !=null && !list.isEmpty())
           {
              na=(NutritionAssessment)list.get(0);
           }
           return na;
    }
    public List getNutritionAssessmentByDateOfAssessment(String ovcId, Date dateOfAssessment) throws Exception
    {
           List list = new ArrayList();
            try
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("from NutritionAssessment na where na.ovcId =:id and na.dateOfAssessment=:dassessment").setString("id", ovcId).setDate("dassessment", dateOfAssessment).list();
                tx.commit();
                session.close();
            }
            catch (HibernateException he)
            {
                closeSession();
                he.printStackTrace();
            }
            catch(Exception ex)
            {
                closeSession();
            }

        return list;
    }
    public NutritionAssessment getNutritionAssessment(String ovcId, Date dateOfAssessment) throws Exception
    {
        NutritionAssessment na=null;
        List list = new ArrayList();
            try
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("from NutritionAssessment na where na.ovcId =:id and na.dateOfAssessment=:dassessment").setString("id", ovcId).setDate("dassessment", dateOfAssessment).list();
                tx.commit();
                session.close();
            }
            catch (HibernateException he)
            {
                closeSession();
                he.printStackTrace();
            }
            catch(Exception ex)
            {
                closeSession();
            }
           if(list !=null && !list.isEmpty())
           {
              na=(NutritionAssessment)list.get(0);
           }
        return na;
    }
    public int getAssessmentNumber(NutritionAssessment na) throws Exception
    {
        int assessmentNumber=1;
        List list=getNutritionAssessmentsByOvcId(na.getOvcId());
        if(list !=null && !list.isEmpty())
        {
            assessmentNumber=list.size()+1;
        }
        return assessmentNumber;
    }
    public void assignAssessmentNumber(NutritionAssessment na) throws Exception
    {
        int assessmentNumber=0;
        List list=getNutritionAssessmentsByOvcId(na.getOvcId());
        if(list !=null && !list.isEmpty())
        {
            NutritionAssessment nAssessment=null;
            if(list.size()>1)
            {
                for(Object s:list)
                {
                    nAssessment=(NutritionAssessment)s;
                    assessmentNumber++;
                    nAssessment.setAssessmentNo(assessmentNumber);
                    updateNutritionAssessment(nAssessment);
                }
            }
        }
    }
    public void setNutritionAssessmentsWithDateOfLastWeight(NutritionAssessment na) throws Exception
    {
        Date dateOfLastWeight=null;
        double lastWeight=0;
        if(na !=null)
        {
            try
            {
                List list=getNutritionAssessmentsByOvcId(na.getOvcId());
                if(list !=null && !list.isEmpty())
                {
                    NutritionAssessment nAssessment=null;
                    for(int i=0; i<list.size(); i++)
                    {
                        nAssessment=(NutritionAssessment)list.get((i));
                        if(nAssessment !=null)
                        {
                            if(i==0)
                            {
                                lastWeight=nAssessment.getWeight();
                                dateOfLastWeight=nAssessment.getDateOfAssessment();
                                nAssessment.setLastWeight(lastWeight);
                                nAssessment.setDateOfLastWeight(dateOfLastWeight);
                            }
                            else
                            {
                               nAssessment.setLastWeight(lastWeight);
                               nAssessment.setDateOfLastWeight(dateOfLastWeight);
                               lastWeight=nAssessment.getWeight();
                               dateOfLastWeight=nAssessment.getDateOfAssessment();
                            }
                            updateNutritionAssessment(nAssessment);
                        }
                    }
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            na.setDateOfLastWeight(dateOfLastWeight);
        }
    }
    
    public List getNutritionAssessmentsByOvcId(String ovcId) throws Exception
    {
        List list=null;
            try
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("from NutritionAssessment na where na.ovcId =:id order by na.dateOfAssessment").setString("id", ovcId).list();
                tx.commit();
                session.close();
            }
            catch (HibernateException he)
            {
                closeSession();
                he.printStackTrace();
            }
            catch(Exception ex)
            {
                closeSession();
                ex.printStackTrace();
            }

        return list;
    }
    public List getAllNutritionAssessments() throws Exception
    {
        List list=null;
        try
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("from NutritionAssessment na").list();
                tx.commit();
                session.close();
            }
            catch (HibernateException he)
            {
                closeSession();
                he.printStackTrace();
            }
            catch(Exception ex)
            {
                closeSession();
            }

        return list;
    }
    public List getNutritionAssessmentsWithOvcDetails(String additionalQueryCriteria) throws Exception
    {
        List list=null;
       try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("").list();//util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) "+additionalQueryCriteria).list();
            tx.commit();
            session.close();
        }
        catch (HibernateException he)
        {
            closeSession();
            he.printStackTrace();
        }
        catch(Exception ex)
        {
            closeSession();
        }
        return list;
    }
    public List getNutritionAssessmentsForExport(String additionalQueryCriteria,int markedForDeleteValue) throws Exception
    {
        List list=null;
        List naList=new ArrayList();
            try
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("").list();//util.getHouseholdQueryPart()+"Ovc ovc,NutritionAssessment na where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=na.ovcId) "+additionalQueryCriteria).list();
                tx.commit();
                session.close();
            }
            catch (HibernateException he)
            {
                closeSession();
                he.printStackTrace();
            }
            catch(Exception ex)
            {
                closeSession();
                ex.printStackTrace();
            }
        if(list !=null && !list.isEmpty())
        {
            NutritionAssessment na=null;
            for(Object s:list)
            {
                Object[] obj=(Object[])s;
                na=(NutritionAssessment)obj[2];
                naList.add(na);
            }
        }
        return naList;
    }
    public List getNutritionAssessmentsByassessmentNumber(int assessmentNumber) throws Exception
    {
        List list=null;
        
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from NutritionAssessment na where na.assessmentNo="+assessmentNumber).list();
            tx.commit();
            session.close();
        }
        
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return list;
    }
    public List getNutritionAssessmentsForBMIReset(int assessmentNumber) throws Exception
    {
        List list=null;
        
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from NutritionAssessment na where na.naRecordStatus=0 and na.assessmentNo="+assessmentNumber).list();
            tx.commit();
            session.close();
        }
        
        catch(Exception ex)
        {
            closeSession();
            ex.printStackTrace();
        }
        return list;
    }
    public int cleanupBMI() throws Exception
    {
        List assNoList=getDistinctAssessmentNo();
        int totalNoOfRecords=0;
        if(assNoList !=null)
        {
            for(int i=0; i<assNoList.size(); i++)
            {
                int assessmentNumber=Integer.parseInt(assNoList.get(i).toString());
                System.err.println("assessmentNumber is "+assessmentNumber);
                List naRecordsList=getNutritionAssessmentsForBMIReset(assessmentNumber);
                totalNoOfRecords+=naRecordsList.size();
                cleanupNutritionAssessment(naRecordsList);
            }
        }
        System.err.println(totalNoOfRecords+" records cleaned");
        return totalNoOfRecords;
    }
    public void cleanupNutritionAssessment(List naRecordsList) throws Exception
    {
        /*if(naRecordsList !=null)
        {
            NutritionAssessment na=null;
            
                int index=0;
                for(int j=0; j<naRecordsList.size(); j++)
                {
                    try
                    {
                        index=0;
                        na=(NutritionAssessment)naRecordsList.get(j);
                        if(na.getRecordStatus()==0)
                        na.setRecordStatus(1);
                        double character=na.getBmi();
                        System.err.println("character is "+character);
                        if(character ==null || character.equalsIgnoreCase("") || character.equalsIgnoreCase(" ") || character.equalsIgnoreCase("  ") || character.contains(" ") || character.trim().equalsIgnoreCase(","))
                        {
                            na.setBodyMassIndex(0);
                            updateNutritionAssessment(na);
                        }
                        else
                        {
                            if(character.length()>1 && character.contains(","))
                            {
                                character=character.replace(",", ".");
                            }
                            for(int i=0; i<character.length(); i++)
                            {
                                //System.err.println("character at "+i+" is "+character.charAt(i));
                                if(Character.isLetter(character.charAt(i)))
                                {
                                    index++;
                                }
                            }
                            if(index>0)
                            {
                                //System.err.println("index  is "+index);
                                na.setBmi(0+"");
                                updateNutritionAssessment(na);
                            }
                            else
                            {
                                na.setBodyMassIndex(Double.parseDouble(character));
                                updateNutritionAssessment(na);
                            }
                        }
                }
                catch(NumberFormatException nfe)
                {
                    System.err.println("Number format exception for input string "+na.getBmi());
                    na.setBodyMassIndex(0);
                    updateNutritionAssessment(na);
                    //System.err.println("New bmi is "+na.getBmi());
                    continue;
                    //nfe.printStackTrace();
                }
                catch(Exception ex)
                {
                    na.setBodyMassIndex(0);
                    updateNutritionAssessment(na);
                    continue;
                    //ex.printStackTrace();
                }
          } 
        }*/
        
    }
    public void setActiveAssessmentRecordPerOvc(String ovcId)
    {
        setActiveNutritionAssessmentRecord(ovcId);
    }
    public void setActiveAssessmentRecordPerCommunity(String communityCode) throws Exception
    {
        try
        {
            List list=this.getDistinctOvcIdByCommunityCode(communityCode);
            if(list !=null)
            {
                    for(int i=0; i<list.size(); i++)
                    {
                        if(list.get(i) !=null)
                        {
                            setActiveNutritionAssessmentRecord(list.get(i).toString());
                        }
                    }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setActiveNutritionAssessmentRecord(String ovcId)
    {
        try
        {
            List naList=getNutritionAssessmentsByOvcId(ovcId);
            if(naList !=null)
            {
                for(int j=0; j<naList.size(); j++)
                {
                    NutritionAssessment na=(NutritionAssessment)naList.get(j);
                    /*set the latest record as the active record with value of 2*/
                    if(j==(naList.size()-1))
                    {
                        na.setRecordStatus(2);
                        updateNutritionAssessment(na);
                        System.err.println("latest record was on "+na.getDateOfAssessment()+" "+na.getRecordStatus());
                    }
                    else
                    {
                        System.err.println("This record is on "+na.getDateOfAssessment());
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void changeOvcId(String oldId,String newId) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        List list=new ArrayList();
        try 
        {
           list=getNutritionAssessmentsByOvcId(oldId);
                
        } 
        catch (Exception ex) 
        {
                ex.printStackTrace();
        }
        if(list !=null && !list.isEmpty())
        {
            NutritionAssessment na=null;
            for(Object obj:list)
            {
                na=(NutritionAssessment)obj;
                na.setLastModifiedDate(DateManager.getCurrentDateInstance());
                /*Delete existing record and save a new one if a record with the new Id does not already exist*/
                this.deleteNutritionAssessment(na);
                if(this.getNutritionAssessment(newId, na.getDateOfAssessment())==null)
                {
                    na.setOvcId(newId);
                    saveNutritionAssessment(na);
                }
            }
        }
    }
}
