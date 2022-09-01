/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.operationsManagement.OvcServiceAttributesManager;
import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
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
public class ChildServiceDaoImpl implements ChildServiceDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    Ovc ovc=null;
    String markedForDeleteQuery=" and service.markedForDelete=0";
    String ovcMarkedForDeleteQuery=" and ovc.markedForDelete=0";
    public int getNumberOfHouseholdsServedForDatim(ReportParameterTemplate rpt,String startDate, String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,int enrollmentStatus) throws Exception
    {
        int count=0;
        try
        {
            FinancialYearManager fym=new FinancialYearManager();
            String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
            //String dateOfEnrollmentStatus=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startOfLastQuarter, endDate);
            if(enrollmentStatus==AppConstant.ACTIVE_NUM)
            startDate=startOfLastQuarter;
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            //String serviceTypeQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(serviceDomain, serviceType);
            //String enrollmentStreamQuery=SubQueryGenerator.getVulnerabilityStatusQuery(enrollmentStreamCode,AppConstant.MAINVULNERABILITY_NUM);
            String enrollmentDateQuery="";
            //if(newlyEnrolledOnly)
            //enrollmentDateQuery=sqg.getOvcEnrollmentDateQuery(startDate, endDate);
            //.getEnrollmentStatusHistoryStatusQuery(currentEnrollmentStatus); ,int serviceDomain,String serviceType
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getBeneficiarySexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");
            //String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct hhe.hhUniqueId) "+SubQueryGenerator.getHheBeneficiaryOrganizationUnitServiceQuarterlyStatusTrackerQuery()+orgUnitQuery+enrollmentDateQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startOfLastQuarter, endDate)+markedForDeleteQuery;
            //String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceEnrollmentStatusHistoryQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getEnrollmentStatusHistoryDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public List getServiceRecordsByCommunity(String communityCode) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+" and hhe.organizationUnit='"+communityCode+"'").list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ChildService service=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    service=(ChildService)objArray[3];
                    mainList.add(service);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mainList;
    }
    public int getNumberOfMalnourishedChildrenProvidedNutritionalServices(ReportParameterTemplate rpt,String startDate, String endDate,int startAge, int endAge,int enrollmentStatus,int currentNutritionStatus,String sex,String serviceCode) throws Exception
    {
        int count=0;
        try
        {
            String serviceDateQuery=sqg.getOvcServiceDateQuery(startDate, endDate);
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String enrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            String serviceTypeQuery="";
            if(serviceCode !=null)
            serviceTypeQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(AppConstant.HEALTH_DOMAIN,serviceCode);
            String query="select count(distinct service.ovcId)"+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerNutritionStatusQuery()+sexQuery+ageQuery+enrollmentStatusQuery+SubQueryGenerator.getCurrentNutritionStatusQuery(currentNutritionStatus)+serviceTypeQuery+serviceDateQuery+markedForDeleteQuery;
            System.err.println("query in ChildService.getNumberOfMalnourishedChildrenProvidedNutritionalServices is "+query);
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
    public List getListOfMalnourishedChildrenProvidedNutritionalServices(ReportParameterTemplate rpt,String startDate, String endDate,int startAge, int endAge,int enrollmentStatus,int currentNutritionStatus,String sex,String serviceCode) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String serviceDateQuery=sqg.getOvcServiceDateQuery(startDate, endDate);
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            String enrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            String serviceTypeQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(AppConstant.HEALTH_DOMAIN,serviceCode);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerNutritionStatusQuery()+ovcMarkedForDeleteQuery+sexQuery+ageQuery+enrollmentStatusQuery+SubQueryGenerator.getCurrentNutritionStatusQuery(currentNutritionStatus)+serviceTypeQuery+serviceDateQuery+markedForDeleteQuery).list();
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
                        uniqueIdList.add(ovc.getOvcId());
                        mainList.add(ovc);
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
    public List getAndUpdateWashRecords() throws Exception
    {
        List list=null;
        try
        {
            String query="from ChildService service where service.healthServices like '%33h%'";
            //System.err.println("query in getAndUpdateWashRecords() is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ChildService service=null;
                String healthServices=null;
                for(Object obj:list)
                {
                    service=(ChildService)obj;
                    healthServices=service.getHealthServices();
                    healthServices=healthServices.replaceAll("33h", "24h");
                    service.setHealthServices(healthServices);
                    updateChildService(service, false);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    public List getDistinctYearOfserviceList() throws Exception
    {
        List list=null;
        try
        {
            String query="select distinct YEAR(service.serviceDate) from ChildService service order by YEAR(service.serviceDate) desc";
            //System.err.println("query in getDistinctYearOfserviceList() is "+query);
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
                ChildService service=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    service=(ChildService)objArray[3];
                    updateChildService(service, false); 
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
            String query="from ChildService service where (service.ageAtService=0 or service.ageUnitAtService="+AppConstant.AGEUNIT_MONTH_NUM+") order by service.serviceDate desc";
                        
            //System.err.println("query in resetAgeAtServiceForChildrenWithZeroAgeAtService() is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ChildService service=null;
                for(Object obj:list)
                {
                    service=(ChildService)obj;
                    //service=getChildServiceWithAgeAtService(service);
                    this.updateChildService(service, false);
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
    public List getListOfServicesByDomainAndServiceTypeCommunityAndAgeLimit(String communityCode,int domain,String serviveCode, int ageLimit) throws Exception
    {
        List list=null;
        try
        {
            String orgUnitQuery=" and hhe.organizationUnit=:ouId";
            String serviceQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(domain,serviveCode);
            String query=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+orgUnitQuery+serviceQuery+" and service.ageAtService>"+ageLimit+markedForDeleteQuery+" order by service.serviceDate desc";
            
            //System.err.println("query in getListOfServicesByDomainAndServiceTypeAndAgeLimit is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).setString("ouId", communityCode).list();
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
    public List getListOfServicesByDomainAndServiceTypeAndAgeLimit(int domain,String serviveCode, int ageLimit) throws Exception
    {
        List list=null;
        try
        {
            String serviceQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(domain,serviveCode);
            String query="from ChildService service where service.ovcId is not null"+serviceQuery+" and service.ageAtService>"+ageLimit+markedForDeleteQuery+" order by service.serviceDate desc";
            
            //System.err.println("query in getListOfServicesByDomainAndServiceTypeAndAgeLimit is "+query);
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
    public List getServicesByPeriodPerChild(String ovcId,String startDate,String endDate) throws Exception
    {
        List list=null;
        try
        {
            String serviceDateQuery=sqg.getOvcServiceDateQuery(startDate, endDate);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ChildService service where service.ovcId=:sid"+serviceDateQuery+markedForDeleteQuery+" order by service.serviceDate desc").setString("sid",ovcId).list();
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
    public int childServedInReportPeriod(String ovcId,String startDate,String endDate) throws Exception
    {
        int servedInReportPeriod=0;
        try
        {
            List list=getServicesByPeriodPerChild(ovcId,startDate,endDate);
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
    public int getNumberOfServiceRecordsPerChild(String ovcId) throws Exception
    {
        int count=0;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct service.ovcId) from Ovc ovc,ChildService service where ovc.ovcId=service.ovcId and ovc.ovcId=:id"+markedForDeleteQuery;
            //System.err.println("query is "+query);
            List list = session.createQuery(query).setString("id", ovcId).list();
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
            String query="update childservice s set s.ageAtService=(select e.currentage from childenrollment e where s.ovcid=e.ovcid)";
            //System.err.println("query is "+query);
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
            String query=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+" and ou.ouPath like '%"+level4OuId+"%'"+markedForDeleteQuery+" order by service.serviceDate";
            //System.err.println("query is "+query);
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
            String query="from ChildService service where service.ageAtService=0";
            //System.err.println("query is "+query);
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
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitQuery()+ageQuery+additionalQuery+currentEnrollmentStatusQuery+sexQuery+sqg.getOvcEnrollmentEndDateQuery(endDate)+ovcMarkedForDeleteQuery+" and ovc.ovcId not in (select distinct service.ovcId from ChildService service where service.ovcId is not null "+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery+")";
            //System.err.println("query in getNumberOfOvcNotServedInReportPeriod is "+query);
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
            String query=SubQueryGenerator.getHheOvcOrganizationUnitQuery()+ageQuery+additionalQuery+currentEnrollmentStatusQuery+sexQuery+sqg.getOvcEnrollmentEndDateQuery(endDate)+ovcMarkedForDeleteQuery+" and ovc.ovcId not in (select distinct service.ovcId from ChildService service where service.ovcId is not null "+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery+") order by hhe.organizationUnit, ovc.currentAge";
            //SubQueryGenerator.getHheOvcOrganizationUnitQuery()+ageQuery+additionalQuery+currentEnrollmentStatusQuery+sexQuery+" and ovc.ovcId not in (select distinct service.ovcId from ChildService service where service.ovcId is not null "+sqg.getOvcServiceDateQuery(startDate, endDate)+")"+" order by hhe.organizationUnit, ovc.currentAge";
            //System.err.println("query is "+query);
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
                    ovc.setHhe((HouseholdEnrollment)objArray[0]); 
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+hivRiskStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public int getNumberOfOvcServedByServiceDomainAndSubType(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceCode) throws Exception
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
                String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+sqg.getOvcServiceQueryByServiceDomainAndSubType(serviceDomain,serviceCode)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
                //System.err.println("query is "+query);
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
    public int getNumberOfOvcServedBySubDomain(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int subDomain) throws Exception
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
                String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+sqg.getOvcServiceQueryBySubDomain(subDomain)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
                //System.err.println("query is "+query);
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
                String query=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+sqg.getOvcServiceQueryByServiceDomainAndSubType(serviceType,serviceCode)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
                //System.err.println("query is "+query);
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
    public List getChildServicesWithReferralRecords() throws Exception
    {
        List list =null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ChildService service where service.referralServices is not null and LENGTH(TRIM(service.referralServices))>0"+markedForDeleteQuery).list();
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+SubQueryGenerator.getHivUnknownOvcNotScreenedForHivRiskAssessmentQuery(startDate, endDate)+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+screenedForHivQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+ovcMarkedForDeleteQuery+SubQueryGenerator.getHivRiskAssessmentDateQuery(startDate,endDate)+additionalQuery+currentEnrollmentStatusQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+screenedForHivQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
            String query=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+sqg.getOvcServiceQueryByServiceDomain(serviceType)+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+statusPeriodQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+sqg.getOvcServiceQueryByServiceDomain(serviceType)+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+statusPeriodQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;//+" and service.serviceDate between '"+startDate+"' and '"+endDate+"'";
            //System.err.println("query is "+query);
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+testNotIndicatedQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+ageQuery+sexQuery+testNotIndicatedQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceHivRiskAssessmentQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+riskAssessmentStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusAndServiceType(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment,int domain,String serviveCode) throws Exception
    {
        int count=0;
        try
        {
            String serviceQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(domain,serviveCode);
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtOvcServiceQuery(startAge,endAge);
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+statusPeriodQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+serviceQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            
            //System.err.println("query is "+query);
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
    //int serviceType
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+statusPeriodQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            
            //System.err.println("query is "+query);
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
    public List getListOfOvcServedByEnrollmentStatusAndHivStatus(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception
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
            String statusPeriodQuery="";
            if(currentEnrollmentStatus==AppConstant.ACTIVE_NUM)
            statusPeriodQuery=sqg.getOvcStartDateOfCurrentStatusQuery(startDate);
            String hivTreatmentQuery="";
            if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            hivTreatmentQuery=SubQueryGenerator.getOvcHivPositiveOnTreatmentQuery();
            else if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM)
            hivTreatmentQuery=SubQueryGenerator.getOvcHivPositiveNotOnTreatmentQuery();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //String query=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            
            String query=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+statusPeriodQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getOvcStartDateOfCurrentStatusQuery(startOfLastQuarter)+sqg.getOvcServiceDateQuery(startOfLastQuarter, endDate)+markedForDeleteQuery;
            //String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceEnrollmentStatusHistoryQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getEnrollmentStatusHistoryDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+ovcMarkedForDeleteQuery+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public int getNumberOfActiveOvcServedForDatim(String additionalQuery,String startDate, String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly,int hivStatus) throws Exception
    {
        int count=0;
        try
        {
            FinancialYearManager fym=new FinancialYearManager();
            String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
            String hivStatusQuery=SubQueryGenerator.getEnrollmentStatusHivStatusQuery(hivStatus); 
            //String dateOfEnrollmentStatus=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startOfLastQuarter, endDate);
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(AppConstant.ACTIVE_NUM,startOfLastQuarter, endDate);
            //+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate)
            //SubQueryGenerator.getEnrollmentStatusQuery(AppConstant.ACTIVE_NUM);
            String serviceTypeQuery=sqg.getOvcServiceQueryByServiceDomainAndSubType(serviceDomain, serviceType);
            String enrollmentStreamQuery=SubQueryGenerator.getVulnerabilityStatusQuery(enrollmentStreamCode,AppConstant.MAINVULNERABILITY_NUM);
            String enrollmentDateQuery="";
            if(newlyEnrolledOnly)
            enrollmentDateQuery=sqg.getOvcEnrollmentDateQuery(startDate, endDate);
            //.getEnrollmentStatusHistoryStatusQuery(currentEnrollmentStatus); ,int serviceDomain,String serviceType
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");//sqg.getOvcCurrentAgeQuery(startAge,endAge);
            
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery()+enrollmentStreamQuery+ovcMarkedForDeleteQuery+additionalQuery+enrollmentDateQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+hivStatusQuery+serviceTypeQuery+sqg.getOvcServiceDateQuery(startOfLastQuarter, endDate)+markedForDeleteQuery;
            //String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceEnrollmentStatusHistoryQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getEnrollmentStatusHistoryDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public int getNumberOfOvcServedByEnrollmentStatusForDatim(String additionalQuery,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,String enrollmentStreamCode,boolean newlyEnrolledOnly) throws Exception
    {
        int count=0;
        try
        {
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(currentEnrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(currentEnrollmentStatus);//.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");
            String enrollmentStreamQuery=SubQueryGenerator.getVulnerabilityStatusQuery(enrollmentStreamCode,AppConstant.MAINVULNERABILITY_NUM);
            String enrollmentDateQuery="";
            if(newlyEnrolledOnly)
            enrollmentDateQuery=sqg.getOvcEnrollmentDateQuery(startDate, endDate);    
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery()+ovcMarkedForDeleteQuery+additionalQuery+enrollmentStreamQuery+enrollmentDateQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public int getNumberOfOvcServedByEnrollmentStatusWithinReportPeriod(ReportParameterTemplate rpt,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,String enrollmentStreamCode,boolean newlyEnrolledOnly,int enrollmentSetting,int birthCertificateValue,int childInSchoolValue) throws Exception
    {
        int count=0;
        try
        {
            String baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery();
            if(enrollmentSetting>0)
            baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitServiceFacilityOvcOfferQuery();
            String currentEnrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);//.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String enrollmentSettingQuery=SubQueryGenerator.getEnrollmentSettingQuery(enrollmentSetting);
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge,endAge);
            String enrollmentStreamQuery=SubQueryGenerator.getVulnerabilityStatusQuery(enrollmentStreamCode,AppConstant.MAINVULNERABILITY_NUM);
            String birthRegistrationQuery=SubQueryGenerator.getOvcCurrentBirthCertificateQuery(birthCertificateValue);
            String childInSchoolQuery=SubQueryGenerator.getOvcCurrentSchoolStatusQuery(childInSchoolValue);
            String enrollmentDateQuery="";
            if(newlyEnrolledOnly)
            enrollmentDateQuery=sqg.getOvcEnrollmentDateQuery(startDate, endDate);    
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct service.ovcId) "+baseQuery+enrollmentSettingQuery+birthRegistrationQuery+childInSchoolQuery+ovcMarkedForDeleteQuery+orgUnitQuery+enrollmentStreamQuery+enrollmentDateQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public int getNumberOfOvcServedByEnrollmentStatusForOVC_EDU(ReportParameterTemplate rpt,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,int childInSchoolValue,int regularSchoolAttendance) throws Exception
    {
        int count=0;
        try
        {
            FinancialYearManager fym=new FinancialYearManager();
            String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
            String baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery();
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String childInSchoolQuery=SubQueryGenerator.getOvcCurrentSchoolStatusQuery(childInSchoolValue);
            String regularSchoolAttendanceQuery="";
            if(regularSchoolAttendance>0)
            {
                baseQuery=SubQueryGenerator.getHheOvcServiceChildEducationPerformanceAssessmentQuarterlyStatusTrackerOrganizationUnitQuery();
                regularSchoolAttendanceQuery=SubQueryGenerator.getRegularSchoolAttendanceQuery(regularSchoolAttendance);
            }
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(AppConstant.ACTIVE_NUM,startOfLastQuarter, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(AppConstant.ACTIVE_NUM);
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge,endAge);
                        
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct service.ovcId) "+baseQuery+ovcMarkedForDeleteQuery+childInSchoolQuery+regularSchoolAttendanceQuery+orgUnitQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startOfLastQuarter, endDate)+markedForDeleteQuery;
            //String query="select count(distinct service.ovcId) "+baseQuery+ovcMarkedForDeleteQuery+orgUnitQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+childInSchoolQuery+regularSchoolAttendanceQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public int getNumberOfOvcServedByEnrollmentStatusAndEnrollmentStream(ReportParameterTemplate rpt,int currentEnrollmentStatus,String startDate, String endDate,int startAge,int endAge,String sex,String enrollmentStreamCode) throws Exception
    {
        int count=0;
        try
        {
            String additionalQuery=sqg.getOrganizationUnitQuery(rpt);
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(AppConstant.ACTIVE_NUM,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(currentEnrollmentStatus);//.getOvcCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");
            String enrollmentStreamQuery=SubQueryGenerator.getVulnerabilityStatusQuery(enrollmentStreamCode,AppConstant.MAINVULNERABILITY_NUM);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery()+enrollmentStreamQuery+ovcMarkedForDeleteQuery+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate)+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public int getNumberOfOvcServedByEnrollmentStatusAndHivStatusAndViralLoadCascadeForDatim(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment,boolean viralLoadEligibilityRequired,boolean viralLoadResultRequired,boolean viralLoadSuppressionRequired) throws Exception
    {
        int count=0;
        try
        {
            DateManager dm=new DateManager();
            //Calculate 12 months before this report for the viral load test date
            String viralLoadTestStartDate=dm.getStartOfNextMonthAfterMonthAdd(DateManager.DB_DATE_FORMAT,endDate, -12);
            String baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery();
            String viralLoadEligibilityQuery="";
            if(viralLoadEligibilityRequired)
            {
                baseQuery=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerHivPositiveDataManagerQuery();
                viralLoadEligibilityQuery=SubQueryGenerator.getNewViralLoadEligibilityQuery(false);
            }
            String viralLoadResultQuery="";
            if(viralLoadResultRequired)
            viralLoadResultQuery=SubQueryGenerator.getViralLoadTestDateQuery(viralLoadTestStartDate,endDate);
            String viralLoadSuppresedQuery="";
            if(viralLoadSuppressionRequired)
            viralLoadSuppresedQuery=SubQueryGenerator.getViralLoadSuppressedQuery(viralLoadTestStartDate,endDate);
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(currentEnrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(currentEnrollmentStatus);
            String ageQuery=sqg.getAgeAtEnrollmentStatusQuery(startAge+"",endAge+"");
            String hivStatusQuery=SubQueryGenerator.getOvcHivStatusQuery(hivStatus); //SubQueryGenerator.getEnrollmentStatusHivStatusQuery(hivStatus)
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=SubQueryGenerator.getOvcSexQuery(sex);
            }
            String hivTreatmentQuery="";
            if(hivStatus==1 && onTreatment==1)
            hivTreatmentQuery=SubQueryGenerator.getEnrollmentStatusHivPositiveOnTreatmentQuery();
            else if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM)
            hivTreatmentQuery=SubQueryGenerator.getEnrollmentStatusHivPositiveNotOnTreatmentQuery();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalQuery+currentEnrollmentStatusQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            String query="select count(distinct service.ovcId) "+baseQuery+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate) +hivStatusQuery+ageQuery+sexQuery+hivTreatmentQuery+viralLoadEligibilityQuery+viralLoadResultQuery+viralLoadSuppresedQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
            String hivStatusQuery=SubQueryGenerator.getOvcHivStatusQuery(hivStatus); //SubQueryGenerator.getEnrollmentStatusHivStatusQuery(hivStatus)
            String hivTreatmentQuery="";
            if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            hivTreatmentQuery=SubQueryGenerator.getEnrollmentStatusHivPositiveOnTreatmentQuery();
            else if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM)
            hivTreatmentQuery=SubQueryGenerator.getEnrollmentStatusHivPositiveNotOnTreatmentQuery();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalQuery+currentEnrollmentStatusQuery+SubQueryGenerator.getOvcHivStatusQuery(hivStatus)+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            String query="select count(distinct service.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuarterlyStatusTrackerQuery()+ovcMarkedForDeleteQuery+additionalQuery+currentEnrollmentStatusQuery+hivStatusQuery+ageQuery+sexQuery+hivTreatmentQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
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
            String query="select count (distinct service.ovcId)"+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+" and ovc.currentEnrollmentStatus=1 "+ovcMarkedForDeleteQuery+ageQuery+sqg.getOvcServiceDateQuery(startDate, endDate)+markedForDeleteQuery;
            //System.err.println("query is "+query);
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
    public List getChildServiceRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and service.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalOrgUnitQuery+sqg.getOvcServiceLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
    public List getAllChildServices() throws Exception
    {
        List list =null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ChildService service"+markedForDeleteQuery).list();
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
    
    public ChildService getChildService(String ovcId, Date serviceDate) throws Exception
    {
        ChildService service=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from ChildService service where service.ovcId=:sid and service.serviceDate=:sdate").setString("sid",ovcId).setDate("sdate", serviceDate).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                service=(ChildService)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return service;
    }
    public List getServicesPerChild(String ovcId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ChildService service where service.ovcId=:sid").setString("sid",ovcId).list();
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
    public void saveChildService(ChildService service,boolean saveBirthRegistration) throws Exception     
    {
        try
        {
            if(service !=null && service.getServiceDate() !=null)
            {
                if(this.getChildService(service.getOvcId(), service.getServiceDate())==null)
                {
                    service=getChildServiceWithAgeAtService(service);
                    
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(service);
                    tx.commit();
                    closeSession(session);
                    //System.err.println("service with "+service.getOvcId()+" saved");
                    if(saveBirthRegistration)
                    {
                        updateOvcCurrentBirthRegAndSchoolStatus(service);
                        updateOvcCurrentEnrollmentStatus(service);
                        updateOvc(ovc);
                    }
                }
                
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateChildService(ChildService service,boolean saveBirthRegistration) throws Exception
    {
       try
        {
            ////System.err.println("service.getOvcId() in updateChildService is "+service.getOvcId());
            if(service !=null && service.getServiceDate() !=null)
            {
                ////System.err.println("service in updateChildService is not null ");
                ChildService service2=getChildService(service.getOvcId(), service.getServiceDate());
                if(service2 !=null)
                {
                    ////System.err.println("service2 in updateChildService is not null ");
                    service.setId(service2.getId());
                    service=getChildServiceWithAgeAtService(service);
                    
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(service);
                    tx.commit();
                    closeSession(session);
                    //System.err.println("service with "+service.getOvcId()+" updated");
                    if(saveBirthRegistration)
                    {
                        updateOvcCurrentBirthRegAndSchoolStatus(service);
                        updateOvcCurrentEnrollmentStatus(service);
                        updateOvc(ovc);
                    }
                }
                
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        } 
    }
    public void markedForDelete(ChildService service) throws Exception
    {
        try
        {
            if(service !=null && service.getServiceDate() !=null)
            {
                ChildService service2=getChildService(service.getOvcId(), service.getServiceDate());
                if(service2 !=null)
                {
                    service.setId(service2.getId());
                    service.setMarkedForDelete(1);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(service);
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
    public void deleteChildService(String ovcId, Date serviceDate) throws Exception
    {
        
    }
    public void markChildServicesForDelete(String ovcId) throws Exception
    {
        List serviceList=getServicesPerChild(ovcId);
        if(serviceList !=null && !serviceList.isEmpty())
        {
            ChildService service=null;
            for(Object obj:serviceList)
            {
                service=(ChildService)obj;
                this.markedForDelete(service);
            }
        }
    }
    public void deleteServicesPerChild(String ovcId) throws Exception
    {
        List serviceList=getServicesPerChild(ovcId);
        if(serviceList !=null && !serviceList.isEmpty())
        {
            ChildService service=null;
            for(Object obj:serviceList)
            {
                service=(ChildService)obj;
                deleteService(service);
            }
        }
    }
    public void deleteService(ChildService service) throws Exception
    {
        try
        {
            if(service !=null && service.getServiceDate() !=null)
            {
                ChildService service2=getChildService(service.getOvcId(), service.getServiceDate());
                if(service2 !=null)
                {
                    service.setId(service2.getId());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(service);
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
public List getServicesByOvcIdAndAdditionalServiceQueryByNumberOfServices(String ovcId,String additionalServiceQuery) throws Exception
{
    List list=new ArrayList();

    try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ChildService service where service.ovcId=:id "+additionalServiceQuery+" order by service.numberOfServices desc").setString("id", ovcId).list();

        tx.commit();
        session.close();
        } 
    catch (HibernateException he) {
            session.close();
            throw new Exception(he);
        }
    return list;
}
public int getNumberOfServicesPerServiceRecord(ChildService service) throws Exception
{
    int numberOfServices=0;
    if(service !=null)
    {
        if(service.getHealthServices() !=null && !service.getHealthServices().equals("") && !service.getHealthServices().equals(" "))
        numberOfServices++;
        if(service.getReferralServices() !=null && !service.getReferralServices().equals("") && !service.getReferralServices().equals(" "))
        numberOfServices++;
        if(service.getGbvServices() !=null && !service.getGbvServices().equals("") && !service.getGbvServices().equals(" "))
        numberOfServices++;
        if(service.getSafetyServices() !=null && !service.getSafetyServices().equals("") && !service.getSafetyServices().equals(" "))
        numberOfServices++;
        if(service.getSchooledServices() !=null && !service.getSchooledServices().equals("") && !service.getSchooledServices().equals(" "))
        numberOfServices++;
        if(service.getStableServices() !=null && !service.getStableServices().equals("") && !service.getStableServices().equals(" "))
        numberOfServices++;
        
    }
    return numberOfServices;
}
    private void updateOvcCurrentEnrollmentStatus(ChildService service) throws Exception
    {
        if(service !=null)
        {
            if(service.getEnrollmentStatus()>0)
            {
                DaoUtility util=new DaoUtility();
                if(ovc==null)
                ovc=util.getChildEnrollmentDaoInstance().getOvc(service.getOvcId());
                if(ovc !=null)
                {
                    if(DateManager.compareDates(ovc.getDateOfCurrentEnrollmentStatus(), service.getDateOfEnrollmentStatus()))
                    {
                        ovc.setCurrentEnrollmentStatus(service.getEnrollmentStatus());
                        ovc.setDateOfCurrentEnrollmentStatus(service.getDateOfEnrollmentStatus());
                        //util.getChildEnrollmentDaoInstance().updateOvc(ovc, false, false);
                    }
                }
            }
        }
    }
    public ChildService getChildServiceWithAgeAtService(ChildService service) throws Exception
    {
        Ovc ovc=getOvc(service.getOvcId());
        if(ovc !=null)
        {
            AppManager appManager=new AppManager();
            AgeObject ageObj=appManager.getAgeAtService(DateManager.convertDateToString(ovc.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT), DateManager.convertDateToString(service.getServiceDate(),DateManager.DB_DATE_FORMAT),ovc.getAgeAtBaseline(), ovc.getAgeUnitAtBaseline());
            int ageAtService=ageObj.getAge();
            int ageUnitAtService=ageObj.getAgeUnit();
            service.setAgeAtService(ageAtService);
            service.setAgeUnitAtService(ageUnitAtService);
            ////System.err.println("Ovc Service updated with age at service "+service.getAgeAtService());
        }
        return service;
    }
    public void updateOvcCurrentBirthRegAndSchoolStatus(ChildService service) throws Exception
    {
        if(service.getSafetyServices() !=null && service.getSafetyServices().trim().length()>0)
        {
            String[] safetyServices=service.getSafetyServices().split(",");
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
                            ovc=getOvc(service.getOvcId());
                            if(ovc !=null && DateManager.compareDates(ovc.getDateOfCurrentBirthRegStatus(),service.getServiceDate()))
                            {
                                ovc.setCurrentBirthRegistrationStatus(AppConstant.CHILD_HAS_BIRTHCERTIFICATE);
                                ovc.setDateOfCurrentBirthRegStatus(service.getServiceDate());
                            }
                        }
                        //check for school service code
                        else if(serviceCode.equalsIgnoreCase(eduPTCEService) || serviceCode.equalsIgnoreCase(holisticScholarship) || serviceCode.equalsIgnoreCase(eduReferralService) || serviceCode.equalsIgnoreCase(schollEduAssistance) || serviceCode.equalsIgnoreCase(schoolPerfAssessment) || serviceCode.equalsIgnoreCase(schoolVisitService))
                        {
                            if(ovc==null)
                            ovc=getOvc(service.getOvcId());
                            if(ovc !=null && DateManager.compareDates(ovc.getDateOfCurrentBirthRegStatus(),service.getServiceDate()))
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
    private Ovc getOvc(String ovcId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
        return ovc;
    }
    public void changeOvcIdInService(String oldOvcId, String newOvcId) throws Exception
    {
        List list=this.getServicesPerChild(oldOvcId);
        if(list !=null)
        {
            for(Object obj:list)
            {
                ChildService service=(ChildService)obj;
                service.setOvcId(newOvcId);
                if(getChildService(service.getOvcId(), service.getServiceDate())==null)
                saveChildService(service, false);
                else
                updateChildService(service, false);
                //System.err.println("Ovc Id in ChildServiceDaoImpl changed from "+oldOvcId+" to "+newOvcId);
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
