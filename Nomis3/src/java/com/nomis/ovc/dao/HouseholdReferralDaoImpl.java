/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.operationsManagement.OvcServiceAttributesManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.HouseholdReferral;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DateManager;
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
public class HouseholdReferralDaoImpl implements HouseholdReferralDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    Ovc ovc=null;
    String markedForDeleteQuery=" and referral.markedForDelete=0";
    public int getNumberOfOvcReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit(ReportParameterTemplate rpt,int domain,String serviveCode, String startDate,String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            String additionalOrgUnitQuery=sqg.getAdditionalOrgUnitQuery(rpt);
            String completedReferralQuery=sqg.getReferralCompletedQuery(AppConstant.REFERRALCOMPLETED_YES_NUM);
            String query="select count (distinct referral.beneficiaryId)"+SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+additionalOrgUnitQuery+SubQueryGenerator.getOvcSexQuery(sex) +SubQueryGenerator.getReferralServiceDateQuery(startDate, endDate) +completedReferralQuery+sqg.getAgeAtReferralQuery(startAge,endAge)+markedForDeleteQuery;
            
            System.err.println("query in getNumberOfOvcReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit is "+query);
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
    public List getListOfOvcReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit(ReportParameterTemplate rpt,int domain,String serviveCode, String startDate,String endDate,int startAge,int endAge,String sex) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String additionalOrgUnitQuery=sqg.getAdditionalOrgUnitQuery(rpt);
            String completedReferralQuery=sqg.getReferralCompletedQuery(AppConstant.REFERRALCOMPLETED_YES_NUM);
            String query=SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+additionalOrgUnitQuery+SubQueryGenerator.getOvcSexQuery(sex)+SubQueryGenerator.getReferralServiceDateQuery(startDate, endDate) +completedReferralQuery+sqg.getAgeAtReferralQuery(startAge,endAge)+markedForDeleteQuery;
            
            System.err.println("query in getListOfOvcReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                List idList=new ArrayList();
                Ovc ovc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    ovc=(Ovc)objArray[1];
                    if(!idList.contains(ovc.getOvcId()))
                    {
                        mainList.add(ovc);
                        idList.add(ovc.getOvcId());
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
    public int getNumberOfAdultHouseholdMembersReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit(ReportParameterTemplate rpt,int domain,String serviveCode, String startDate,String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            String additionalOrgUnitQuery=sqg.getAdditionalOrgUnitQuery(rpt);
            String completedReferralQuery=sqg.getReferralCompletedQuery(AppConstant.REFERRALCOMPLETED_YES_NUM);
            String query="select count (distinct referral.beneficiaryId)"+SubQueryGenerator.getHheAdultHouseholdMemberHouseholdReferralOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getAdultHouseholdMemberGenderQuery(sex) +SubQueryGenerator.getReferralServiceDateQuery(startDate, endDate) +completedReferralQuery+sqg.getAgeAtReferralQuery(startAge,endAge)+markedForDeleteQuery;
            
            System.err.println("query in getNumberOfAdultHouseholdMembersReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit is "+query);
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
    public List getListOfAdultHouseholdMembersReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit(ReportParameterTemplate rpt,int domain,String serviveCode, String startDate,String endDate,int startAge,int endAge,String sex) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String additionalOrgUnitQuery=sqg.getAdditionalOrgUnitQuery(rpt);
            String completedReferralQuery=sqg.getReferralCompletedQuery(AppConstant.REFERRALCOMPLETED_YES_NUM);
            String query=SubQueryGenerator.getHheAdultHouseholdMemberHouseholdReferralOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getAdultHouseholdMemberGenderQuery(sex)+SubQueryGenerator.getReferralServiceDateQuery(startDate, endDate) +completedReferralQuery+sqg.getAgeAtReferralQuery(startAge,endAge)+markedForDeleteQuery;
            
            System.err.println("query in getListOfAdultHouseholdMembersReferredForServiceWithCompletedReferralsByDomainAndServiceTypeAndAgeLimit is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                List idList=new ArrayList();
                AdultHouseholdMember ahm=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    ahm=(AdultHouseholdMember)objArray[1];
                    if(!idList.contains(ahm.getBeneficiaryId()))
                    {
                        mainList.add(ahm);
                        idList.add(ahm.getBeneficiaryId());
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
    public List getDistinctYearOfserviceList() throws Exception
    {
        List list=null;
        try
        {
            String query="select distinct YEAR(referral.dateOfReferral) from HouseholdReferral referral order by YEAR(referral.dateOfReferral) desc";
            System.err.println("query in getDistinctYearOfserviceList() is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    public int resetAgeAtServiceForServiceRecords(String level4OuId) throws Exception
    {
        int count=0;
        try
        {
            List list=getListOfServicesByLevel4OrganizationUnit(level4OuId);
            
            if(list !=null && !list.isEmpty())
            {
                HouseholdReferral referral=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    referral=(HouseholdReferral)objArray[3];
                    updateHouseholdReferral(referral); 
                    count++;
                    
                }
                
            }    
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return count;
    }
    public int resetAgeAtServiceForChildrenWithZeroAgeAtService() throws Exception
    {
        int count=0;
        try
        {
            //String serviceQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(domain,serviveCode);
            String query="from HouseholdReferral referral where (referral.ageAtReferral=0 or referral.ageUnitAtReferral="+AppConstant.AGEUNIT_MONTH_NUM+") order by referral.dateOfReferral desc";
                        
            System.err.println("query in resetAgeAtServiceForChildrenWithZeroAgeAtService() is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                HouseholdReferral referral=null;
                for(Object obj:list)
                {
                    referral=(HouseholdReferral)obj;
                    //referral=getHouseholdReferralWithAgeAtService(referral);
                    this.updateHouseholdReferral(referral);
                    count++;
                }
            }    
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return count;
    }
    public List getListOfServicesByDomainAndServiceTypeAndAgeLimit(int domain,String serviveCode, int ageLimit) throws Exception
    {
        List list=null;
        try
        {
            String serviceQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(domain,serviveCode);
            String query="from HouseholdReferral referral where referral.beneficiaryId is not null"+serviceQuery+" and referral.ageAtService>"+ageLimit+markedForDeleteQuery+" order by referral.dateOfReferral desc";
            
            System.err.println("query in getListOfServicesByDomainAndServiceTypeAndAgeLimit is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
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
    public List getServicesByPeriodPerChild(String beneficiaryId,String startDate,String endDate) throws Exception
    {
        List list=null;
        try
        {
            String serviceDateQuery=sqg.getOvcServiceDateQuery(startDate, endDate);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HouseholdReferral referral where referral.beneficiaryId=:sid"+serviceDateQuery+markedForDeleteQuery+" order by referral.dateOfReferral desc").setString("sid",beneficiaryId).list();
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
    public int childServedInReportPeriod(String beneficiaryId,String startDate,String endDate) throws Exception
    {
        int servedInReportPeriod=0;
        
        
        try
        {
            List list=getServicesByPeriodPerChild(beneficiaryId,startDate,endDate);
           if(list !=null && !list.isEmpty()) 
           servedInReportPeriod=1;
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return servedInReportPeriod;
    }
    public int getNumberOfServiceRecordsPerChild(String beneficiaryId) throws Exception
    {
        int count=0;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) from Ovc ovc,HouseholdReferral referral where ovc.beneficiaryId=referral.beneficiaryId and ovc.beneficiaryId=:id"+markedForDeleteQuery;
            System.err.println("query is "+query);
            List list = session.createQuery(query).setString("id", beneficiaryId).list();
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
    public int updateAgeAtServiceWithCurrentAge() throws Exception
    {
        int returnValue=0;
        try
        {     
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="update HouseholdReferral s set s.ageAtReferral=(select e.currentage from childenrollment e where s.beneficiaryId=e.beneficiaryId)";
            System.err.println("query is "+query);
            returnValue=session.createSQLQuery(query).executeUpdate();
            tx.commit();
            closeSession(session);
            /*if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[3]);
                    
                }
            }*/
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return returnValue;
    }
    public List getListOfServicesByLevel4OrganizationUnit(String level4OuId) throws Exception
    {
        List mainList=new ArrayList();
        try
        {     
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query=SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+" and ou.ouPath like '%"+level4OuId+"%'"+markedForDeleteQuery+" order by referral.dateOfReferral";
            System.err.println("query is "+query);
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                mainList.addAll(list);
                /*for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[3]);
                    
                }*/
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getServiceRecordsWithZeroAgeAtService() throws Exception
    {
        List list=new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="from HouseholdReferral referral where referral.ageAtReferral=0";
            System.err.println("query is "+query);
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
    public int getNumberOfOvcNotServedInReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String sexQuery="";
            
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct ovc.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitQuery()+ageQuery+additionalQuery+currentEnrollmentStatusQuery+sexQuery+sqg.getOvcEnrollmentEndDateQuery(endDate)+" and ovc.beneficiaryId not in (select distinct referral.beneficiaryId from HouseholdReferral referral where referral.beneficiaryId is not null "+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery+")";
            System.err.println("query in getNumberOfOvcNotServedInReportPeriod is "+query);
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
    public List getListOfOvcNotServedInReportPeriod(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query=SubQueryGenerator.getHheOvcOrganizationUnitQuery()+ageQuery+additionalQuery+currentEnrollmentStatusQuery+sexQuery+sqg.getOvcEnrollmentEndDateQuery(endDate)+" and ovc.beneficiaryId not in (select distinct referral.beneficiaryId from HouseholdReferral referral where referral.beneficiaryId is not null "+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery+") order by hhe.organizationUnit, ovc.currentAge";
            //SubQueryGenerator.getHheOvcOrganizationUnitQuery()+ageQuery+additionalQuery+currentEnrollmentStatusQuery+sexQuery+" and ovc.beneficiaryId not in (select distinct referral.beneficiaryId from HouseholdReferral referral where referral.beneficiaryId is not null "+sqg.getOvcServiceDateQuery(startDate, endDate)+")"+" order by hhe.organizationUnit, ovc.currentAge";
            System.err.println("query is "+query);
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                List uniqueIdList=new ArrayList();
                Ovc ovc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    ovc=(Ovc)objArray[2];
                    if(!uniqueIdList.contains(ovc.getOvcId()))
                    {
                        mainList.add(ovc);
                        uniqueIdList.add(ovc.getOvcId());
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
    public int getNumberOfOvcServedAndRiskAssessedByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int hivRiskStatus) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            String hivRiskStatusQuery=SubQueryGenerator.getHivUnknownOvcHivRiskStatusQuery(hivRiskStatus,startDate, endDate);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+hivRiskStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfOvcServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType,String serviceCode) throws Exception
    {
        int count=0;
        try
        {
                String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
                String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
                String sexQuery="";
                if(sex !=null)
                {
                    sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
                }
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+sqg.getOvcServiceQueryByServiceDomainAndSubType(serviceType,serviceCode)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public List getListOfOvcServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType,String serviceCode) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
                String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
                String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
                String sexQuery="";
                if(sex !=null)
                {
                    sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
                }
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                String query=SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+sqg.getOvcServiceQueryByServiceDomainAndSubType(serviceType,serviceCode)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
                System.err.println("query is "+query);
                List list = session.createQuery(query).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    List idList=new ArrayList();
                    Ovc ovc=null;
                    for(Object obj:list)
                    {
                        Object[] objArray=(Object[])obj;
                        ovc=(Ovc)objArray[1];
                        if(!idList.contains(ovc.getOvcId()))
                        {
                            mainList.add(objArray[1]);
                            idList.add(ovc.getOvcId());
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
    public List getHouseholdReferralsWithReferralRecords() throws Exception
    {
        List list =null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HouseholdReferral referral where referral.referralServices is not null and LENGTH(TRIM(referral.referralServices))>0"+markedForDeleteQuery).list();
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
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusNotScreenedForHiv(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            String screenedForHivQuery="";
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+SubQueryGenerator.getHivUnknownOvcNotScreenedForHivRiskAssessmentQuery(startDate, endDate)+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+screenedForHivQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusScreenedForHiv(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            String screenedForHivQuery="";
            
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+SubQueryGenerator.getHivRiskAssessmentDateQuery(startDate,endDate)+additionalQuery+currentEnrollmentStatusQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+screenedForHivQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public List getListOfOvcServedByEnrollmentStatusAndServiceType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            String statusPeriodQuery="";
            if(currentEnrollmentStatus==AppConstant.ACTIVE_NUM)
            statusPeriodQuery=sqg.getOvcStartDateOfCurrentStatusQuery(startDate);
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query=SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+sqg.getOvcServiceQueryByServiceDomain(serviceType)+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+statusPeriodQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            System.err.println("query is "+query);
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
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
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public int getNumberOfOvcServedByEnrollmentStatusAndServiceType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceType) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            String statusPeriodQuery="";
            if(currentEnrollmentStatus==AppConstant.ACTIVE_NUM)
            statusPeriodQuery=sqg.getOvcStartDateOfCurrentStatusQuery(startDate);
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+sqg.getOvcServiceQueryByServiceDomain(serviceType)+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+statusPeriodQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;//+" and referral.dateOfReferral between '"+startDate+"' and '"+endDate+"'";
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
    public int getNumberOfHivUnknownOvcNotAtRiskServed(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            String testNotIndicatedQuery=SubQueryGenerator.getTestNotIndicatedQuery(startDate, endDate);
            
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+testNotIndicatedQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfHivUnknownOvcAtRiskServed(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            String testNotIndicatedQuery=SubQueryGenerator.getHivUnknownOvcAtRiskQuery(startDate, endDate);
            
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+testNotIndicatedQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfOvcServedAndRiskAssessedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int riskAssessmentStatus) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }//getOvcAtRiskQuery(AppConstant.CHILD_AT_HIGH_RISK_NUM)
            String riskAssessmentStatusQuery=SubQueryGenerator.getOvcHivRiskStatusQuery(riskAssessmentStatus,startDate, endDate);
            
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+additionalQuery+currentEnrollmentStatusQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+riskAssessmentStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception
    {
        int count=0;
        try
        {
            
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            //String currentEnrollmentStatusQuery=SubQueryGenerator.getEnrollmentStatusHistoryStatusQuery(currentEnrollmentStatus);
            //String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");//getAgeAtEnrollmentStatusQuery(String firstAge,String secondAge);
             String statusPeriodQuery="";
            if(currentEnrollmentStatus==AppConstant.ACTIVE_NUM)
            statusPeriodQuery=sqg.getOvcStartDateOfCurrentStatusQuery(startDate);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            String hivTreatmentQuery="";
            if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            hivTreatmentQuery=SubQueryGenerator.getOvcHivPositiveOnTreatmentQuery();
            else if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM)
            hivTreatmentQuery=SubQueryGenerator.getOvcHivPositiveNotOnTreatmentQuery();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalQuery+currentEnrollmentStatusQuery+statusPeriodQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceEnrollmentStatusHistoryQuery()+additionalQuery+currentEnrollmentStatusQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    
    public int getNumberOfActiveOvcServed(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            FinancialYearManager fym=new FinancialYearManager();
            String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.ACTIVE_NUM);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getOvcStartDateOfCurrentStatusQuery(startOfLastQuarter)+sqg.getOvcServiceDateQuery(startOfLastQuarter, endDate)+markedForDeleteQuery;
            //String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceEnrollmentStatusHistoryQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getEnrollmentStatusHistoryDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfOvcServedByEnrollmentStatus(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfActiveOvcServedForDatim(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            FinancialYearManager fym=new FinancialYearManager();
            String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(AppConstant.ACTIVE_NUM,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(AppConstant.ACTIVE_NUM);
            
            //.getEnrollmentStatusHistoryStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");
            //String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startOfLastQuarter, endDate)+markedForDeleteQuery;
            //String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceEnrollmentStatusHistoryQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getEnrollmentStatusHistoryDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfOvcServedByEnrollmentStatusForDatim(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            /*String statusPeriodQuery="";
            if(currentEnrollmentStatus==AppConstant.ACTIVE_NUM)
            statusPeriodQuery=sqg.getEnrollmentStatusHistoryDateOfCurrentStatusQuery(startDate, endDate);*/
            //.getOvcStartDateOfCurrentStatusQuery(startDate);
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(currentEnrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(currentEnrollmentStatus);//.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");
            //String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusForDatim(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception
    {
        int count=0;
        try
        {
            
            //String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            //String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(currentEnrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");//getAgeAtEnrollmentStatusQuery(String firstAge,String secondAge);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            String hivTreatmentQuery="";
            if(hivStatus==1 && onTreatment==1)
            hivTreatmentQuery=SubQueryGenerator.getEnrollmentStatusHivPositiveOnTreatmentQuery();
            else if(hivStatus==1 && onTreatment==2)
            hivTreatmentQuery=SubQueryGenerator.getEnrollmentStatusHivPositiveNotOnTreatmentQuery();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalQuery+currentEnrollmentStatusQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            String query="select count(distinct referral.beneficiaryId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery()+additionalQuery+currentEnrollmentStatusQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate) +SubQueryGenerator.getEnrollmentStatusHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfActiveOvcServed(String startDate, String endDate,int startAge,int endAge) throws Exception
    {
        int count=0;
        try
        {
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count (distinct referral.beneficiaryId) from Ovc ovc, HouseholdReferral referral where ovc.beneficiaryId=referral.beneficiaryId and ovc.currentEnrollmentStatus=1 "+ageQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public List getHouseholdReferralRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            List ovcReferralList=getOvcReferralRecordsForExport(rpt,markedForDeleteValue);
            List adultReferralList=getAduldHouseholdMemberReferralRecordsForExport(rpt,markedForDeleteValue);
            if(ovcReferralList !=null)
            mainList.addAll(ovcReferralList);
            if(adultReferralList !=null)
            mainList.addAll(adultReferralList);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getAduldHouseholdMemberReferralRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and hhr.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberHouseholdReferralOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getHouseholdReferralLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
    public List getOvcReferralRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and hhr.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getHouseholdReferralLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
    public List getAllHouseholdReferrals() throws Exception
    {
        List list =null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HouseholdReferral referral"+markedForDeleteQuery).list();
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
    public List getHouseholdReferrals(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception
    {
        List mainList =new ArrayList();
        try
        {
            List ovcReferralList=getOvcReferrals(additionalQuery,currentEnrollmentStatus,hivStatus,startDate, endDate,startAge,endAge,sex,onTreatment);
            List adultReferralList=getAdultMembersReferrals(additionalQuery,currentEnrollmentStatus,hivStatus,startDate, endDate,startAge,endAge,sex,onTreatment);
            if(ovcReferralList !=null)
            mainList.addAll(ovcReferralList);
            if(adultReferralList !=null)
            mainList.addAll(adultReferralList);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getOvcReferrals(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception
    {
        List mainList =new ArrayList();
        try
        {
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();//getHheOvcHouseholdReferralOrganizationUnitQuery()
            String query=SubQueryGenerator.getHheOvcHouseholdReferralOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getHouseholdReferralServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //" and referral.dateOfReferral between '"+startDate+"' and '"+endDate+"'";
            System.err.println("query is "+query);
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                HouseholdReferral referral=null;
                Beneficiary beneficiary=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    referral=(HouseholdReferral)objArray[3];
                    beneficiary=(Beneficiary)objArray[1];
                    referral.setBeneficiary(beneficiary);
                    mainList.add(referral);
                }
            }
            /*if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[3]);
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
    public List getAdultMembersReferrals(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception
    {
        List mainList =new ArrayList();
        try
        {
            if(startAge<18)
            {
                startAge=AppConstant.ADULT_STARTAGE_NUM;
                endAge=AppConstant.ADULT_ENDAGE_NUM;
            }
            String currentEnrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            }
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();//getHheOvcHouseholdReferralOrganizationUnitQuery()
            String query=SubQueryGenerator.getHheAdultHouseholdMemberHouseholdReferralOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getHouseholdReferralServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            
            System.err.println("query is "+query);
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                HouseholdReferral referral=null;
                Beneficiary beneficiary=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    referral=(HouseholdReferral)objArray[3];
                    beneficiary=(Beneficiary)objArray[1];
                    referral.setBeneficiary(beneficiary);
                    mainList.add(referral);
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
    public HouseholdReferral getHouseholdReferral(String beneficiaryId, Date serviceDate) throws Exception
    {
        HouseholdReferral referral=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HouseholdReferral referral where referral.beneficiaryId=:sid and referral.dateOfReferral=:sdate").setString("sid",beneficiaryId).setDate("sdate", serviceDate).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                referral=(HouseholdReferral)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return referral;
    }
    public List getServicesPerBeneficiary(String beneficiaryId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HouseholdReferral referral where referral.beneficiaryId=:sid").setString("sid",beneficiaryId).list();
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
    public void saveHouseholdReferral(HouseholdReferral referral) throws Exception     
    {
        try
        {
            if(referral !=null && referral.getDateOfReferral() !=null)
            {
                if(this.getHouseholdReferral(referral.getBeneficiaryId(), referral.getDateOfReferral())==null)
                {
                    referral=getHouseholdReferralWithAgeAtService(referral);
                    
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(referral);
                    tx.commit();
                    closeSession(session);
                    
                    updateOvcCurrentBirthRegAndSchoolStatus(referral);
                    updateOvc(ovc);   
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateHouseholdReferral(HouseholdReferral referral) throws Exception
    {
       try
        {
            //System.err.println("referral.getBeneficiaryId() in updateHouseholdReferral is "+referral.getBeneficiaryId());
            if(referral !=null && referral.getDateOfReferral() !=null)
            {
                //System.err.println("service in updateHouseholdReferral is not null ");
                HouseholdReferral referral2=getHouseholdReferral(referral.getBeneficiaryId(), referral.getDateOfReferral());
                if(referral2 !=null)
                {
                    //System.err.println("service2 in updateHouseholdReferral is not null ");
                    referral.setId(referral2.getId());
                    referral=getHouseholdReferralWithAgeAtService(referral);
                    
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(referral);
                    tx.commit();
                    closeSession(session);
                    System.err.println("service with "+referral.getBeneficiaryId()+" updated");
                    
                        updateOvcCurrentBirthRegAndSchoolStatus(referral);
                        //updateOvcCurrentEnrollmentStatus(referral);
                        updateOvc(ovc);
                    
                }
                /*else
                {
                    saveHouseholdReferral(referral,saveBirthRegistration);
                }*/
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        } 
    }
    public void saveOrUpdateHouseholdReferral(HouseholdReferral referral) throws Exception
    {
        try
        {
            if(referral !=null && referral.getDateOfReferral() !=null)
            {
                HouseholdReferral referral2=getHouseholdReferral(referral.getBeneficiaryId(), referral.getDateOfReferral());
                if(referral2 !=null)
                {
                    referral=getHouseholdReferralWithAgeAtService(referral);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(referral);
                    tx.commit();
                    closeSession(session);
                    System.err.println("Referral service in saveOrUpdateHouseholdService with "+referral.getBeneficiaryId()+" updated");
                    updateOvcCurrentBirthRegAndSchoolStatus(referral);
                    updateOvc(ovc);
                }
                else
                {
                    referral=getHouseholdReferralWithAgeAtService(referral);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(referral);
                    tx.commit();
                    closeSession(session);
                    System.err.println("Referral service in saveOrUpdateHouseholdService with "+referral.getBeneficiaryId()+" saved");
                    updateOvcCurrentBirthRegAndSchoolStatus(referral);
                    updateOvc(ovc);
                }
            }
        }
        catch (Exception ex)
        {
            closeSession(session);
        }
    }
    public void markedForDelete(HouseholdReferral referral) throws Exception
    {
        try
        {
            if(referral !=null && referral.getDateOfReferral() !=null)
            {
                HouseholdReferral referral2=getHouseholdReferral(referral.getBeneficiaryId(), referral.getDateOfReferral());
                if(referral2 !=null)
                {
                    referral.setId(referral2.getId());
                    referral.setMarkedForDelete(1);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(referral);
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
    public void deleteHouseholdReferral(String beneficiaryId, Date serviceDate) throws Exception
    {
        
    }
    public void markServicesForDelete(String beneficiaryId) throws Exception
    {
        List referralList=getServicesPerBeneficiary(beneficiaryId);
        if(referralList !=null && !referralList.isEmpty())
        {
            HouseholdReferral referral=null;
            for(Object obj:referralList)
            {
                referral=(HouseholdReferral)obj;
                this.markedForDelete(referral);
            }
        }
    }
    public void deleteServicesPerBeneficiary(String beneficiaryId) throws Exception
    {
        List referralList=getServicesPerBeneficiary(beneficiaryId);
        if(referralList !=null && !referralList.isEmpty())
        {
            HouseholdReferral referral=null;
            for(Object obj:referralList)
            {
                referral=(HouseholdReferral)obj;
                deleteService(referral);
            }
        }
    }
    public void deleteService(HouseholdReferral referral) throws Exception
    {
        try
        {
            if(referral !=null && referral.getDateOfReferral() !=null)
            {
                HouseholdReferral referral2=getHouseholdReferral(referral.getBeneficiaryId(), referral.getDateOfReferral());
                if(referral2 !=null)
                {
                    referral.setId(referral2.getId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(referral);
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
    /*private void updateOvcCurrentEnrollmentStatus(HouseholdReferral referral) throws Exception
    {
        if(referral !=null)
        {
            if(referral.getEnrollmentStatus()>0)
            {
                DaoUtility util=new DaoUtility();
                if(ovc==null)
                ovc=util.getChildEnrollmentDaoInstance().getOvc(referral.getBeneficiaryId());
                if(ovc !=null)
                {
                    if(DateManager.compareDates(ovc.getDateOfCurrentStatus(), referral.getDateOfEnrollmentStatus()))
                    {
                        ovc.setCurrentEnrollmentStatus(referral.getEnrollmentStatus());
                        ovc.setDateOfCurrentStatus(referral.getDateOfEnrollmentStatus());
                        //util.getChildEnrollmentDaoInstance().updateOvc(ovc, false, false);
                    }
                }
            }
        }
    }*/
    public HouseholdReferral getHouseholdReferralWithAgeAtService(HouseholdReferral referral) throws Exception
    {
        Ovc ovc=getOvc(referral.getBeneficiaryId());
        if(ovc !=null)
        {
            AppManager appManager=new AppManager();
            AgeObject ageObj=appManager.getAgeAtService(DateManager.convertDateToString(ovc.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT), DateManager.convertDateToString(referral.getDateOfReferral(),DateManager.DB_DATE_FORMAT),ovc.getAgeAtBaseline(), ovc.getAgeUnitAtBaseline());
            int ageAtReferral=ageObj.getAge();
            int ageUnitAtReferral=ageObj.getAgeUnit();
            referral.setAgeAtReferral(ageAtReferral);
            referral.setAgeUnitAtReferral(ageUnitAtReferral);
            System.err.println("Household Referral updated with age at referral "+referral.getAgeAtReferral());
        }
        else
        {
            AdultHouseholdMember ahm=getAdultHouseholdMember(referral.getBeneficiaryId());
            if(ahm !=null)
            {
                AppManager appManager=new AppManager();
                AgeObject ageObj=appManager.getAgeAtService(DateManager.convertDateToString(ahm.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT), DateManager.convertDateToString(referral.getDateOfReferral(),DateManager.DB_DATE_FORMAT),ahm.getAgeAtBaseline(), ahm.getAgeUnitAtBaseline());
                int ageAtReferral=ageObj.getAge();
                int ageUnitAtReferral=ageObj.getAgeUnit();
                referral.setAgeAtReferral(ageAtReferral);
                referral.setAgeUnitAtReferral(ageUnitAtReferral);
                System.err.println("Household Referral updated with age at referral "+referral.getAgeAtReferral()); 
            }
        }
        return referral;
    }
    public void updateOvcCurrentBirthRegAndSchoolStatus(HouseholdReferral referral) throws Exception
    {
        if(referral.getSafetyServices() !=null && referral.getSafetyServices().trim().length()>0)
        {
            String[] safetyServices=referral.getSafetyServices().split(",");
            if(safetyServices !=null && safetyServices.length>0)
            {
                String serviceCode=null;
                
                String eduPTCEService=OvcServiceAttributesManager.getEducationalSubsidiesPTCE().getServiceCode();
                String holisticScholarship=OvcServiceAttributesManager.getHolisticScholarshipService().getServiceCode();
                String eduReferralService=OvcServiceAttributesManager.getEducationReferralService().getServiceCode();
                String schollEduAssistance=OvcServiceAttributesManager.getSchoolEducationalAssistance().getServiceCode();
                String schoolPerfAssessment=OvcServiceAttributesManager.getSchoolPerformanceAssessmentService().getServiceCode();
                String schoolVisitService=OvcServiceAttributesManager.getSchoolVisit().getServiceCode();
                for(int i=0; i<safetyServices.length; i++)
                {
                    serviceCode=safetyServices[i];
                    if(serviceCode !=null && serviceCode.trim().length()>0)
                    {
                        serviceCode=serviceCode.trim();
                        //check for birth registration service code
                        if(serviceCode.equalsIgnoreCase(OvcServiceAttributesManager.getBirthRegistrationAcquisitionService().getServiceCode()))
                        {
                            if(ovc==null)
                            ovc=getOvc(referral.getBeneficiaryId());
                            if(ovc !=null && DateManager.compareDates(ovc.getDateOfCurrentBirthRegStatus(),referral.getDateOfReferral()))
                            {
                                ovc.setCurrentBirthRegistrationStatus(AppConstant.CHILD_HAS_BIRTHCERTIFICATE);
                                ovc.setDateOfCurrentBirthRegStatus(referral.getDateOfReferral());
                            }
                        }
                        //check for school service code
                        else if(serviceCode.equalsIgnoreCase(eduPTCEService) || serviceCode.equalsIgnoreCase(holisticScholarship) || serviceCode.equalsIgnoreCase(eduReferralService) || serviceCode.equalsIgnoreCase(schollEduAssistance) || serviceCode.equalsIgnoreCase(schoolPerfAssessment) || serviceCode.equalsIgnoreCase(schoolVisitService))
                        {
                            if(ovc==null)
                            ovc=getOvc(referral.getBeneficiaryId());
                            if(ovc !=null && DateManager.compareDates(ovc.getDateOfCurrentBirthRegStatus(),referral.getDateOfReferral()))
                            {
                                ovc.setCurrentSchoolStatus(AppConstant.CHILD_IN_SCHOOL);
                                
                            }
                        }
                        
                    }
                }
            }
        }
    }
    private void updateOvc(Ovc ovc) throws Exception
    {
        DaoUtility util=new DaoUtility();
        if(ovc !=null)
        util.getChildEnrollmentDaoInstance().updateOvc(ovc, false, true);
    }
    private Ovc getOvc(String beneficiaryId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
        return ovc;
    }
    private AdultHouseholdMember getAdultHouseholdMember(String beneficiaryId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
        return ahm;
    }
    public void changeBeneficiaryIdInHouseholdReferral(String oldBeneficiaryId, String newBeneficiaryId) throws Exception
    {
        List list=this.getServicesPerBeneficiary(oldBeneficiaryId);
        if(list !=null)
        {
            for(Object obj:list)
            {
                HouseholdReferral referral=(HouseholdReferral)obj;
                referral.setBeneficiaryId(newBeneficiaryId);
                if(getHouseholdReferral(referral.getBeneficiaryId(), referral.getDateOfReferral())==null)
                saveHouseholdReferral(referral);
                else
                updateHouseholdReferral(referral);
                System.err.println("Ovc Id in HouseholdReferralDaoImpl changed from "+oldBeneficiaryId+" to "+newBeneficiaryId);
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
