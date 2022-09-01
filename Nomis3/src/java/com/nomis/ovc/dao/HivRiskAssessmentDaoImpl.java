/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.operationsManagement.HivRiskAssessmentManager;
import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.business.HivStatusManager;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.HivRiskAssessmentReport;
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
public class HivRiskAssessmentDaoImpl implements HivRiskAssessmentDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    String markedForDeleteQuery=" and hra.markedForDelete=0";
    SubQueryGenerator sqg=new SubQueryGenerator();
    
    public int getNumberOfChildrenWithChangeInRiskProfile(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue) throws Exception
    {
        int count=0;
        try
        {    
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.ACTIVE_GRADUATED_NUM);
            String dateQuery=SubQueryGenerator.getHivRiskAssessmentReportDateQuery(startDate, endDate);
            String changeInRiskProfileQuery=SubQueryGenerator.getChangeInRiskProfileInHivAssessmentReportQuery();
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentReportQuery()+orgUnitQuery+ageQuery+sexQuery+currentEnrollmentQuery+dateQuery+SubQueryGenerator.getHivStatusEligibleForRiskAssessmentQuery(eligibilityValue)+changeInRiskProfileQuery;//+" and ou.ouPath like '%"+level4OuId+"%'";
            System.err.println("query is "+query);
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
        return count;
    }
    public int getNoOfOvcDocumentedOnHivRiskassessmentFormAsReferredForHIVTest(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue,int riskAssessmentOutcome) throws Exception
    {
        int count=0;
        try
        {    
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.ACTIVE_GRADUATED_NUM);
            String dateQuery=SubQueryGenerator.getHivRiskAssessmentReportDateQuery(startDate, endDate);
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            //String hivRiskQuery=SubQueryGenerator.getOvcAtRiskInHivAssessmentReportQuery(riskAssessmentOutcome);
            String referredForHivTestQuery=SubQueryGenerator.getOvcAtRiskDocumentedOnRiskAssessmentFormAsReferredForHIVTestQuery(riskAssessmentOutcome);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentReportQuery()+orgUnitQuery+ageQuery+sexQuery+currentEnrollmentQuery+dateQuery+SubQueryGenerator.getHivStatusEligibleForRiskAssessmentQuery(eligibilityValue)+referredForHivTestQuery;//+" and ou.ouPath like '%"+level4OuId+"%'";
            System.err.println("query is "+query);
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
        return count;
    }
    public int getNumberOfChildrenEligibleForRiskAssessmentRiskAssessedAndAtRiskOfHiv(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue,int riskAssessmentOutcome) throws Exception
    {
        int count=0;
        try
        {    
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.ACTIVE_GRADUATED_NUM);
            String dateQuery=SubQueryGenerator.getHivRiskAssessmentReportDateQuery(startDate, endDate);
            String hivRiskQuery=SubQueryGenerator.getOvcAtRiskInHivAssessmentReportQuery(riskAssessmentOutcome);
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentReportQuery()+orgUnitQuery+ageQuery+sexQuery+currentEnrollmentQuery+dateQuery+SubQueryGenerator.getHivStatusEligibleForRiskAssessmentQuery(eligibilityValue)+hivRiskQuery;//+" and ou.ouPath like '%"+level4OuId+"%'";
            System.err.println("query is "+query);
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
        return count;
    }
    /*public int getNumberOfChildrenRiskAssessedAndReferredForHivTest(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue,int riskAssessmentOutcome,boolean referralComplete) throws Exception
    {
        int count=0;
        try
        {    
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.ACTIVE_GRADUATED_NUM);
            String riskAssessmentDateQuery=SubQueryGenerator.getHivRiskAssessmentReportDateQuery(startDate, endDate);
            String referralDateQuery=SubQueryGenerator.getReferralServiceDateQuery(startDate, endDate);
            String hivRiskQuery=SubQueryGenerator.getOvcAtRiskInHivAssessmentReportQuery(riskAssessmentOutcome);
            String hivServiceReferralQuery=sqg.getHivRelatedTestingReferralQuery();
            String referralCompleteQuery="";
            if(referralComplete)
            referralCompleteQuery=sqg.getReferralCompletedQuery(AppConstant.REFERRALCOMPLETED_YES_NUM);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentReportHouseholdReferralQuery()+ageQuery+sexQuery+currentEnrollmentQuery+riskAssessmentDateQuery+referralDateQuery+SubQueryGenerator.getHivStatusEligibleForRiskAssessmentQuery(eligibilityValue)+hivRiskQuery+hivServiceReferralQuery+referralCompleteQuery;//+" and ou.ouPath like '%"+level4OuId+"%'";
            ////System.err.println("query is "+query);
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
        return count;
    }*/
    public int getNumberOfChildrenAssessedForHivRiskAndReferredForHivTestForTreatmentCascade(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int riskAssessmentOutcome,int referralComplete,int hivStatus,int enrolledOntreatment,int enrollmentStatus) throws Exception
    {
        int count=0;
        try
        {   
            FinancialYearManager fy=new FinancialYearManager();
            String startDateOfQuarter=fy.getStartDateOfQuarter(rpt.getEndDate());
            String baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentChildServiceQuarterlyStatusTrackerQuery();
            String referralDateQuery="";
            String hivServiceReferralQuery="";
            if(referralComplete==AppConstant.REFERRALCOMPLETED_NO_NUM || referralComplete==AppConstant.REFERRALCOMPLETED_YES_NUM)
            {
                hivServiceReferralQuery=sqg.getHivRelatedTestingReferralQuery();
                referralDateQuery=SubQueryGenerator.getReferralServiceDateQuery(startDate, endDate);
                baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentChildServiceQuarterlyStatusTrackerReferralQuery();
            }
            
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String serviceQuery=sqg.getOvcServiceDateQuery(startDate, endDate);
            String currentEnrollmentQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            String dateOfCurrentEnrollmentStatusQuery=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate);
            if(enrollmentStatus==AppConstant.ACTIVE_NUM)
            {
                dateOfCurrentEnrollmentStatusQuery=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDateOfQuarter, endDate);
                serviceQuery=sqg.getOvcServiceDateQuery(startDateOfQuarter, endDate);
            }
            //String currentEnrollmentQuery=SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            //String dateOfCurrentEnrollmentStatusQuery=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate);
            String riskAssessmentDateQuery=SubQueryGenerator.getHivRiskAssessmentDateQuery(startDate, endDate);
            String hivRiskQuery=SubQueryGenerator.getOvcAtRiskQuery(riskAssessmentOutcome);
            String dateOfCurrentHivStatusQuery=SubQueryGenerator.getOvcDateOfCurrentHivStatusQuery(startDate, endDate);
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            //This returns empty query string if no hiv status is specified
            String hivStatusQuery=SubQueryGenerator.getOvcHivStatusQuery(hivStatus);
            if(hivStatus==AppConstant.HIV_POSITIVE_NUM || hivStatus==AppConstant.HIV_NEGATIVE_NUM)
            {
                //This returns hiv status query and add date of hiv test to it
                hivStatusQuery=hivStatusQuery+dateOfCurrentHivStatusQuery;
                //This adds the enrolled on treatment query to the hiv status query
                if(enrolledOntreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
                hivStatusQuery=SubQueryGenerator.getOvcHivPositiveOnTreatmentQuery(startDate, endDate)+dateOfCurrentHivStatusQuery;
            }
            
            String referralCompleteQuery="";
            referralCompleteQuery=sqg.getReferralCompletedQuery(referralComplete);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.ovcId) "+baseQuery+orgUnitQuery+ageQuery+sexQuery+serviceQuery+currentEnrollmentQuery+dateOfCurrentEnrollmentStatusQuery+riskAssessmentDateQuery+referralDateQuery+hivRiskQuery+hivServiceReferralQuery+referralCompleteQuery+hivStatusQuery;//+" and ou.ouPath like '%"+level4OuId+"%'";
            System.err.println("query is "+query);
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
        return count;
    }
    public int getNumberOfChildrenRiskAssessedAndReferredForHivTestByHivStatusAndTreatmentStatus(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int riskAssessmentOutcome,boolean referralComplete,int hivStatus,int enrolledOntreatment) throws Exception
    {
        int count=0;
        try
        {    
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.ACTIVE_GRADUATED_NUM);
            String riskAssessmentDateQuery=SubQueryGenerator.getHivRiskAssessmentDateQuery(startDate, endDate);
            String referralDateQuery=SubQueryGenerator.getReferralServiceDateQuery(startDate, endDate);
            String hivRiskQuery=SubQueryGenerator.getOvcAtRiskQuery(riskAssessmentOutcome);
            String hivServiceReferralQuery=sqg.getHivRelatedTestingReferralQuery();
            String hivStatusQuery=SubQueryGenerator.getOvcHivStatusQuery(hivStatus);
            if(enrolledOntreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            hivStatusQuery=SubQueryGenerator.getOvcHivPositiveOnTreatmentQuery(startDate, endDate);
            //SubQueryGenerator.getHivStatusEligibleForRiskAssessmentQuery()+
            String referralCompleteQuery="";
            if(referralComplete)
            referralCompleteQuery=sqg.getReferralCompletedQuery(AppConstant.REFERRALCOMPLETED_YES_NUM);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentReferralQuery()+ageQuery+sexQuery+currentEnrollmentQuery+riskAssessmentDateQuery+referralDateQuery+hivRiskQuery+hivServiceReferralQuery+referralCompleteQuery;//+" and ou.ouPath like '%"+level4OuId+"%'";
            System.err.println("query in getNumberOfChildrenRiskAssessedAndReferredForHivTestByHivStatusAndTreatmentStatus is "+query);
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
        return count;
    }
    public int getNumberOfChildrenEligibleForRiskAssessmentThatWereRiskAssessed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int eligibilityValue) throws Exception
    {
        int count=0;
        try
        {    
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.ACTIVE_GRADUATED_NUM);
            String dateQuery=SubQueryGenerator.getHivRiskAssessmentReportDateQuery(startDate, endDate);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentReportQuery()+ageQuery+sexQuery+currentEnrollmentQuery+dateQuery+SubQueryGenerator.getHivStatusEligibleForRiskAssessmentQuery(eligibilityValue);//+" and ou.ouPath like '%"+level4OuId+"%'";
            ////System.err.println("query is "+query);
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
        return count;
    }
    public int getNoOfChildrenForHivRiskAssessmentCascade(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int enrollmentStatus,int eligibilityValue,int riskAssessmentValue,int riskAssessmentOutcome) throws Exception
    {
        int count=0;
        try
        {   FinancialYearManager fy=new FinancialYearManager();
            String startDateOfQuarter=fy.getStartDateOfQuarter(rpt.getEndDate());
            String baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentReportChildServiceQuarterlyStatusTrackerQuery();
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String eligibilityForHivRiskAssessmentQuery="";
            String riskAssessmentDateQuery="";
            String childAtRiskQuery="";
            if(eligibilityValue==1)
            {
                eligibilityForHivRiskAssessmentQuery=SubQueryGenerator.getHivStatusEligibleForRiskAssessmentQuery(eligibilityValue);
            }
            if(riskAssessmentValue==1)
            {
                eligibilityForHivRiskAssessmentQuery="";
                baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentChildServiceQuarterlyStatusTrackerQuery();
                childAtRiskQuery=SubQueryGenerator.getOvcAtRiskQuery(riskAssessmentOutcome);
                riskAssessmentDateQuery=SubQueryGenerator.getHivRiskAssessmentDateQuery(startDate, endDate);
            }
            
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String serviceQuery=sqg.getOvcServiceDateQuery(startDate, endDate);
            String currentEnrollmentQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            String dateOfCurrentEnrollmentStatusQuery=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate);
            if(enrollmentStatus==AppConstant.ACTIVE_NUM)
            {
                dateOfCurrentEnrollmentStatusQuery=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDateOfQuarter, endDate);
                serviceQuery=sqg.getOvcServiceDateQuery(startDateOfQuarter, endDate);
            }
            
            //String hivRiskQuery=SubQueryGenerator.getOvcAtRiskQuery(riskAssessmentOutcome);
            //String dateOfCurrentHivStatusQuery=SubQueryGenerator.getOvcDateOfCurrentHivStatusQuery(startDate, endDate);
            
            /*String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.ACTIVE_GRADUATED_NUM);*/
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.ovcId) "+baseQuery+orgUnitQuery+ageQuery+sexQuery+serviceQuery+currentEnrollmentQuery+dateOfCurrentEnrollmentStatusQuery+eligibilityForHivRiskAssessmentQuery+childAtRiskQuery+riskAssessmentDateQuery;//+" and ou.ouPath like '%"+level4OuId+"%'";
            ////System.err.println("query is "+query);
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
        return count;
    }
    public int getNumberOfOvcRiskAssessedByHivStatusAndEnrollmentStatus(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,int hivStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getAgeAtHivRiskAssessmentQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String hivStatusAtRiskAssessment=SubQueryGenerator.getHivStatusAtHivRiskAssessmentQuery(hivStatus);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct hra.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentQuery()+additionalOrgUnitQuery+ageQuery+sexQuery+hivStatusAtRiskAssessment+SubQueryGenerator.getHivRiskAssessmentDateQuery(startDate,endDate)+markedForDeleteQuery;
            //System.err.println(query);
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
        return count;
    }
    public List getListOfOvcRiskAssessedByHivStatusAndEnrollmentStatus(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,int hivStatus,String sex) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getAgeAtHivRiskAssessmentQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String hivStatusAtRiskAssessment=SubQueryGenerator.getHivStatusAtHivRiskAssessmentQuery(hivStatus);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentQuery()+additionalOrgUnitQuery+ageQuery+sexQuery+hivStatusAtRiskAssessment+SubQueryGenerator.getHivRiskAssessmentDateQuery(startDate,endDate)+markedForDeleteQuery;
            //System.err.println(query);
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
                        uniqueIdList.add(ovc.getOvcId());
                        mainList.add(objArray[1]);
                    }
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
    public List getHivRiskAssessmentRecordsByLevel4OuId(String level4OuId) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String query=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentQuery()+" and ou.ouPath like '%"+level4OuId+"%'"+markedForDeleteQuery+" order by hra.dateOfAssessment";
            //System.err.println(query);
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
                    mainList.add(objArray[3]);
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
    public List getRecordsWithZeroAgeAtAssessment() throws Exception
    {
        List list=new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="from HivRiskAssessment hra where hra.ageAtAssessment=0";
            ////System.err.println("query is "+query);
            list = session.createQuery(query).list();
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
    public List getRiskAssessmentRecordsByOvcId(String ovcId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="from HivRiskAssessment hra where hra.ovcId=:uid order by hra.dateOfAssessment desc";
            list = session.createQuery(query).setString("uid", ovcId).list();
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
    public void saveHivRiskAssessment(HivRiskAssessment hra) throws Exception
    {
        try
        {
            ////System.err.println("hra.getChildAtRiskQuestion() in saveHivRiskAssessment(HivRiskAssessment hra) is "+hra.getChildAtRiskQuestion());
            if(hra !=null && hra.getDateOfAssessment() !=null && hra.getOvcId() !=null && hra.getChildAtRiskQuestion()>0)
            {
                HivRiskAssessment hra2=this.getHivRiskAssessment(hra.getOvcId(), hra.getDateOfAssessment());
                if(hra2==null)
                {
                    hra=HivRiskAssessmentManager.getHivRiskAssessmentWithCleanedStatus(hra);
                    hra=getHivRiskAssessmentWithAgeAtAssessment(hra);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(hra);
                    tx.commit();
                    closeSession(session);
                    
                    updateOvcHivStatusWithTestNotIndicated(hra);
                    //System.err.println("hra.getOvcId() "+hra.getOvcId()+" saved");
                }
                
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateHivRiskAssessment(HivRiskAssessment hra) throws Exception
    {
        ////System.err.println("hra.getChildAtRiskQuestion() in updateHivRiskAssessment(HivRiskAssessment hra) is "+hra.getChildAtRiskQuestion());
        try
        {
            if(hra !=null && hra.getDateOfAssessment() !=null && hra.getOvcId() !=null && hra.getChildAtRiskQuestion()>0)
            {
                HivRiskAssessment hra2=this.getHivRiskAssessment(hra.getOvcId(), hra.getDateOfAssessment());
                if(hra2 !=null)
                {
                    hra.setRecordId(hra2.getRecordId());
                    hra=HivRiskAssessmentManager.getHivRiskAssessmentWithCleanedStatus(hra);
                    hra=getHivRiskAssessmentWithAgeAtAssessment(hra);
                    hra.setDateCreated(hra2.getDateCreated());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(hra);
                    tx.commit();
                    closeSession(session);
                    
                    updateOvcHivStatusWithTestNotIndicated(hra);
                    ////System.err.println("hra.getOvcId() "+hra.getOvcId()+" updated");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteHivRiskAssessment(HivRiskAssessment hra) throws Exception
    {
        try
        {
            if(hra !=null && hra.getDateOfAssessment() !=null && hra.getOvcId() !=null)
            {
                HivRiskAssessment hra2=this.getHivRiskAssessment(hra.getOvcId(), hra.getDateOfAssessment());
                if(hra2 !=null)
                {
                    hra.setRecordId(hra2.getRecordId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(hra);
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
    public void markRiskAssessmentRecordsForDelete(String ovcId) throws Exception
    {
        List list=this.getRiskAssessmentRecordsByOvcId(ovcId);
        if(list !=null && !list.isEmpty())
        {
            for(Object obj:list)
            {
                HivRiskAssessment hra=(HivRiskAssessment)obj;
                markForDelete(hra);
            }
        }
    }
    public void deleteRiskAssessmentRecordsPerChild(String ovcId) throws Exception
    {
        List list=this.getRiskAssessmentRecordsByOvcId(ovcId);
        if(list !=null && !list.isEmpty())
        {
            for(Object obj:list)
            {
                HivRiskAssessment hra=(HivRiskAssessment)obj;
                deleteHivRiskAssessment(hra);
            }
        }
    }
    public void markForDelete(HivRiskAssessment hra) throws Exception
    {
        try
        {
            if(hra !=null && hra.getDateOfAssessment() !=null && hra.getOvcId() !=null)
            {
                HivRiskAssessment hra2=this.getHivRiskAssessment(hra.getOvcId(), hra.getDateOfAssessment());
                if(hra2 !=null)
                {
                    hra.setRecordId(hra2.getRecordId());
                    hra2.setMarkedForDelete(1);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(hra2);
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
    public HivRiskAssessment getHivRiskAssessment(int recordId) throws Exception
    {
       HivRiskAssessment hra=null;
       try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HivRiskAssessment hra where hra.recordId=:id").setInteger("id", recordId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                hra=(HivRiskAssessment)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
       return hra;
    }
    public HivRiskAssessment getHivRiskAssessment(String ovcId,Date dateOfAssessment) throws Exception
    {
       HivRiskAssessment hra=null;
       try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HivRiskAssessment hra where hra.ovcId=:id and hra.dateOfAssessment=:doa").setString("id", ovcId).setDate("doa", dateOfAssessment).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                hra=(HivRiskAssessment)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
       return hra;
    }
    public void updateOvcHivStatusWithTestNotIndicated(HivRiskAssessment hra) throws Exception
    {
        if(hra.getChildAtRiskQuestion()==AppConstant.CHILD_AT_LOW_RISK_NUM || hra.getChildAtRiskQuestion()==AppConstant.CHILD_NOT_AT_RISK_NUM)
        {
            ChildEnrollmentDao dao=new ChildEnrollmentDaoImpl();
            Ovc ovc=dao.getOvc(hra.getOvcId());
            if(ovc !=null && (ovc.getCurrentHivStatus()==AppConstant.HIV_UNKNOWN_NUM || ovc.getCurrentHivStatus()==AppConstant.HIV_UNDISCLOSED_NUM || ovc.getCurrentHivStatus()==AppConstant.HIV_RESULT_NOT_RECEIVED_NUM))
            {
                //Test not indicated is for HIV unknown beneficiaries only.
                ovc.setCurrentHivStatus(AppConstant.HIV_TEST_NOT_INDICATED_NUM);
                ovc.setDateOfCurrentHivStatus(hra.getDateOfAssessment());
                dao.updateOvcOnly(ovc);
            }
        }
    }
    public void processHivRiskAssessmentOutcome(HivRiskAssessment hra) throws Exception
    {
        /*if(hra.getChildAtRiskQuestion()==AppConstant.CHILD_AT_LOW_RISK_NUM || hra.getChildAtRiskQuestion()==AppConstant.CHILD_NOT_AT_RISK_NUM)
        {
            ChildEnrollmentDao dao=new ChildEnrollmentDaoImpl();
            Ovc ovc=dao.getOvc(hra.getOvcId());
            if(ovc !=null && (ovc.getCurrentHivStatus()==AppConstant.HIV_UNKNOWN_NUM || ovc.getCurrentHivStatus()==AppConstant.HIV_UNDISCLOSED_NUM || ovc.getCurrentHivStatus()==AppConstant.HIV_RESULT_NOT_RECEIVED_NUM))
            {
                //Test not indicated is for HIV unknown beneficiaries only.
                ovc.setCurrentHivStatus(AppConstant.HIV_TEST_NOT_INDICATED_NUM);
                ovc.setDateOfCurrentHivStatus(hra.getDateOfAssessment());
                dao.updateOvcOnly(ovc);
            }
        }*/
    }
    public List getHivRiskAssessmentRecords(ReportParameterTemplate rpt) throws Exception
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
            String query=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentQuery()+additionalOrgUnitQuery+sqg.getHivRiskAssessmentDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteQuery;
            //System.err.println(query);
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
                    mainList.add(objArray[3]);
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
    public List getHivRiskAssessmentRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and hra.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentQuery()+additionalOrgUnitQuery+sqg.getHivRiskAssessmentLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
            //System.err.println(query);
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
                    mainList.add(objArray[3]);
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
    public List getAllHivRiskAssessments() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HivRiskAssessment hra where hra.ovcId is not null"+markedForDeleteQuery).list();
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
    public HivRiskAssessment getHivRiskAssessmentWithAgeAtAssessment(HivRiskAssessment hra) throws Exception
    {
        DaoUtility util=new DaoUtility();
        AppManager appManager=new AppManager();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(hra.getOvcId());
        if(ovc !=null)
        {
            AgeObject ageObj=appManager.getAgeAtService(DateManager.convertDateToString(ovc.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT), DateManager.convertDateToString(hra.getDateOfAssessment(),DateManager.DB_DATE_FORMAT),ovc.getAgeAtBaseline(), ovc.getAgeUnitAtBaseline());
            hra.setAgeAtRiskAssessment(ageObj.getAge());
            hra.setAgeUnitAtRiskAssessment(ageObj.getAgeUnit());
            
            //System.err.println(" HivRiskAssessment record updated with age at assessment "+hra.getAgeAtRiskAssessment()+" and age unit ("+hra.getAgeUnitAtRiskAssessment()+")");
        }
        return hra;
    }
    public void changeOvcIdInHivRiskAssessmentRecords(String oldOvcId, String newOvcId) throws Exception
    {
        List list=getRiskAssessmentRecordsByOvcId(oldOvcId);
        if(list !=null)
        {
            for(Object obj:list)
            {
                HivRiskAssessment hra=(HivRiskAssessment)obj;
                hra.setOvcId(newOvcId);
                if(getHivRiskAssessment(hra.getOvcId(), hra.getDateOfAssessment())==null)
                saveHivRiskAssessment(hra);
                else
                updateHivRiskAssessment(hra);
                //System.err.println("OVC Id in HivRiskAssessment changed from "+oldOvcId+" to "+newOvcId);
            }
        }
    }
    
    
    public void saveHivRiskAssessmentReport(HivRiskAssessmentReport hrar) throws Exception
    {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        session.save(hrar);
        tx.commit();
        closeSession(session);
    }
    public void updateHivRiskAssessmentReport(HivRiskAssessmentReport hrar) throws Exception
    {
        HivRiskAssessmentReport hra2=this.getHivRiskAssessmentReport(hrar.getBeneficiaryId());
        if(hra2 !=null)
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(hrar);
            tx.commit();
            closeSession(session);
        }
    }
    public void deleteHivRiskAssessmentReport(HivRiskAssessmentReport hrar) throws Exception
    {
        HivRiskAssessmentReport hra2=this.getHivRiskAssessmentReport(hrar.getBeneficiaryId());
        if(hra2 !=null)
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(hrar);
            tx.commit();
            closeSession(session);
        }
    }
    public HivRiskAssessmentReport getHivRiskAssessmentReport(String beneficiaryId) throws Exception
    {
       HivRiskAssessmentReport hra=null;
       try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HivRiskAssessmentReport hrar where hrar.beneficiaryId=:id").setString("id", beneficiaryId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                hra=(HivRiskAssessmentReport)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
       return hra;
    }
    public List getHivRiskAssessmentReportByLevel4OrganizationUnit(String level4OuId) throws Exception
    {
        List mainList=new ArrayList();
        try
        {     
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query=SubQueryGenerator.getHheOvcOrganizationUnitHivRiskAssessmentReportQuery()+" and ou.ouPath like '%"+level4OuId+"%'";
            ////System.err.println("query is "+query);
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[3]); 
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
    
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
