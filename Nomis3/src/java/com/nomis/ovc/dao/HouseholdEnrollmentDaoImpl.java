/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.RevisedHouseholdVulnerabilityAssessment;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
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
public class HouseholdEnrollmentDaoImpl implements HouseholdEnrollmentDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and hhe.markedForDelete=0";
    public List getDistinctCommunityCodesFromHouseholdsExitedFromProgram(int beneficiaryType) throws Exception
    {
        List list=null;
        try
        {
            String query="select distinct hhe.organizationUnit from HouseholdEnrollment hhe, Ovc ovc where hhe.hhUniqueId=ovc.hhUniqueId and hhe.currentEnrollmentStatus !="+AppConstant.ACTIVE_NUM+" and (ovc.currentEnrollmentStatus="+AppConstant.ACTIVE_NUM+" or ovc.currentEnrollmentStatus="+AppConstant.REENROLLED_NUM+")"+markedForDeleteQuery;
            if(beneficiaryType==AppConstant.CAREGIVER_TYPE_NUM)
            query="select distinct hhe.organizationUnit from HouseholdEnrollment hhe, AdultHouseholdMember ahm where hhe.hhUniqueId=ahm.hhUniqueId and hhe.currentEnrollmentStatus !="+AppConstant.ACTIVE_NUM+" and (ahm.currentEnrollmentStatus="+AppConstant.ACTIVE_NUM+" or ahm.currentEnrollmentStatus="+AppConstant.REENROLLED_NUM+")"+markedForDeleteQuery;
            System.err.println("query in getDistinctCommunityCodesFromHouseholdsExitedFromProgram is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list= session.createQuery(query).list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
            NomisLogManager.logStackTrace(ex);
        }
        return list;
    }
    public List getOvcWhoseHouseholdsAreExitedFromTheProgram(String level4OuId) throws Exception
    {
        List list=null;
        try
        {
            String query="from HouseholdEnrollment hhe, Ovc ovc where hhe.hhUniqueId=ovc.hhUniqueId and hhe.organizationUnit=:id and hhe.currentEnrollmentStatus !="+AppConstant.ACTIVE_NUM+" and (ovc.currentEnrollmentStatus="+AppConstant.ACTIVE_NUM+" or ovc.currentEnrollmentStatus="+AppConstant.REENROLLED_NUM+")"+markedForDeleteQuery;
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list= session.createQuery(query).setString("id", level4OuId).list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
            NomisLogManager.logStackTrace(ex);
        }
        return list;
    }
    public List getAdultHouseholdMembersWhoseHouseholdsAreExitedFromTheProgram(String level4OuId) throws Exception
    {
        List list=null;
        try
        {
            String query="from HouseholdEnrollment hhe, AdultHouseholdMember ahm where hhe.hhUniqueId=ahm.hhUniqueId and hhe.organizationUnit=:id and hhe.currentEnrollmentStatus !="+AppConstant.ACTIVE_NUM+" and (ahm.currentEnrollmentStatus="+AppConstant.ACTIVE_NUM+" or ahm.currentEnrollmentStatus="+AppConstant.REENROLLED_NUM+")"+markedForDeleteQuery;
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list= session.createQuery(query).setString("id", level4OuId).list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
            NomisLogManager.logStackTrace(ex);
        }
        return list;
    }
    public int getNumberOfHouseholdsServedForOVC_HHGRAD(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String beneficiarySex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String sexQuery=sqg.getBeneficiarySexQuery(beneficiarySex);
            String qstDateOfEnrollmentStatus=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate);
            String hhDateOfCurrentEnrollmentStatus=sqg.getHouseholdDateOfCurrentEnrollmentStatusQuery(startDate, endDate);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String enrollmentStatusQuery=SubQueryGenerator.getHouseholdEnrollmentStatusQuery(enrollmentStatus);
            
            String query="select count(distinct hhe.hhUniqueId) "+SubQueryGenerator.getHheBeneficiaryOrganizationUnitServiceQuarterlyStatusTrackerQuery()+additionalOrgUnitQuery+sexQuery+hhDateOfCurrentEnrollmentStatus+enrollmentStatusQuery+qstDateOfEnrollmentStatus;
            System.err.println("query in getNumberOfHouseholdsServedForOVC_HHGRAD is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list= session.createQuery(query).list();
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
    public int getNumberOfHouseholdsGraduatedForOVC_HHGRAD(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String beneficiarySex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String sexQuery=sqg.getBeneficiarySexQuery(beneficiarySex);
            String qstDateOfEnrollmentStatus=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate, endDate);
            String hhDateOfCurrentEnrollmentStatus=sqg.getHouseholdDateOfCurrentEnrollmentStatusQuery(startDate, endDate);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String enrollmentStatusQuery=SubQueryGenerator.getHouseholdEnrollmentStatusQuery(enrollmentStatus);
            
            String query="select count(distinct hhe.hhUniqueId) "+SubQueryGenerator.getHheBeneficiaryOrganizationUnitServiceQuarterlyStatusTrackerQuery()+additionalOrgUnitQuery+sexQuery+hhDateOfCurrentEnrollmentStatus+enrollmentStatusQuery+qstDateOfEnrollmentStatus;
            System.err.println("query in getNumberOfHouseholdsGraduatedForOVC_HHGRAD is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list= session.createQuery(query).list();
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
    public List getDistinctYearOfAssessment() throws Exception
    {
        List list=new ArrayList();
        try
        {
            
            String query="select distinct YEAR(hhe.dateOfAssessment) from HouseholdEnrollment hhe order by YEAR(hhe.dateOfAssessment) asc";
            System.err.println("query in getDistinctYearOfEnrollment() is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                int i=0;
                for(Object obj:list)
                {
                    System.err.println("Year at index "+i+" is "+obj);
                    i++;
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list;
    }
    public int getNumberOfHouseholdsEnrolled(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String enrollmentStatusQuery=SubQueryGenerator.getHouseholdEnrollmentStatusQuery(enrollmentStatus);
            
            String query="select count(distinct hhe.hhUniqueId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+enrollmentStatusQuery+additionalOrgUnitQuery+SubQueryGenerator.getHheAssessmentDateQuery(startDate,endDate);
            System.err.println("query in getNumberOfHouseholdsEnrolled is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list= session.createQuery(query).list();
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
    public List getListOfHouseholdsEnrolled(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus) throws Exception
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
            String enrollmentStatusQuery=SubQueryGenerator.getHouseholdEnrollmentStatusQuery(enrollmentStatus);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list= session.createQuery(SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+enrollmentStatusQuery+additionalOrgUnitQuery+SubQueryGenerator.getHheAssessmentDateQuery(startDate,endDate)).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                HouseholdEnrollment hhe=null;
                AdultHouseholdMember ahm=null;
                List idList=new ArrayList();
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    if(!idList.contains(hhe.getHhUniqueId()))
                    {
                        ahm=(AdultHouseholdMember)objArray[1];
                        hhe.setPrCaregiver(ahm);
                        mainList.add(hhe);
                        idList.add(hhe.getHhUniqueId());
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
    public List getDistinctPartnerCodes(ReportParameterTemplate rpt) throws Exception
    {
        List list=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            
            /*if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }*/
            String query="select distinct hhe.partnerCode "+SubQueryGenerator.getHheOrganizationUnitQuery()+additionalOrgUnitQuery;
            System.err.println("query in getHouseholdEnrollmentRecords is "+query);
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
    public int changeCboId(String oldCboId,String newCboId) throws Exception
    {
        int result=0;
        try
        {
            String query="update HouseholdEnrollment set CBOID='"+newCboId+"' where cboId='"+oldCboId+"'";
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            result = session.createSQLQuery(query).executeUpdate();
            tx.commit();
            closeSession(session);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return result;
    }
    public List getListOfHouseholdsWithCasePlanRecords() throws Exception
    {
        List list=null;
        try
        {
            String query="from HouseholdEnrollment hhe, HouseholdCasePlan hcp where hhe.hhUniqueId=hcp.hhUniqueId"+markedForDeleteQuery;
            System.err.println(query);
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
    public List getListOfHouseholdsWithZeroCasePlanRecords() throws Exception
    {
        List list=null;
        try
        {
            String query="from HouseholdEnrollment hhe, HouseholdCasePlan hcp where hhe.hhUniqueId=hcp.hhUniqueId and hhe.hhHasCasePlan=0"+markedForDeleteQuery;
            System.err.println(query);
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
    public int updateHouseholdEnrollmentWithZeroCasePlanRecords() throws Exception
    {
        int count=0;
        try
        {
            List list=getListOfHouseholdsWithCasePlanRecords();
            if(list !=null)
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    HouseholdEnrollment hhe=(HouseholdEnrollment)objArray[0];
                    //HouseholdCasePlan hcp=(HouseholdCasePlan)objArray[1];
                    //hhe.setHhHasCasePlan(hcp.getHhHasCasePlan());
                    //hhe.setDateCasePlanDeveloped(hcp.getDateCasePlanDeveloped());
                    hhe.setLastModifiedDate(DateManager.getCurrentDateInstance());
                    updateHouseholdEnrollment(hhe);
                    count++;
                    System.err.println(count+" Household case plan records updated");
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
    public List getDistinctLevel4OrganizationUnit() throws Exception
    {
        List list=null;
        try
        {
            String query="select distinct hhe.organizationUnit from HouseholdEnrollment hhe";
            System.err.println(query);
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
    
    public List getDistinctCommunityBasedOrganizationIds(ReportParameterTemplate rpt) throws Exception
    {
        List list=null;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select distinct hhe.cboId "+SubQueryGenerator.getHheOrganizationUnitQuery()+additionalOrgUnitQuery;
            System.err.println(query);
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
    public int getDistinctCommunityBasedOrganizationIdsWithServedBeneficiaries(ReportParameterTemplate rpt) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct hhe.cboId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalOrgUnitQuery;
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
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return count;
    }
    public int getDistinctLevel2OuIdsWithServedBeneficiaries(ReportParameterTemplate rpt) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct ou.uid) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalOrgUnitQuery+" and ou.ouLevel=2";
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
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return count;
    }
    public int getDistinctLevel3OuIdsWithServedBeneficiaries(ReportParameterTemplate rpt) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct ou.uid) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+additionalOrgUnitQuery+" and ou.ouLevel=3";
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
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return count;
    }
    public List getDistinctLevel4OrganizationUnit(ReportParameterTemplate rpt) throws Exception
    {
        List list=null;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            
            String query="select distinct hhe.organizationUnit "+SubQueryGenerator.getHheOrganizationUnitQuery()+additionalOrgUnitQuery;
            System.err.println(query);
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
    public List getDistinctLevel3OuList(ReportParameterTemplate rpt) throws Exception
    {
        List level3OuList=new ArrayList();
        List uniqueLevel3OuIdList=new ArrayList();
        List level4OuIdList=getDistinctLevel4OrganizationUnit(rpt);
        if(level4OuIdList !=null)
        {
            String level4OuId=null;
            OrganizationUnit ou=null;
            OrganizationUnit parentOu=null;
            OrganizationUnitDao oudao=new OrganizationUnitDaoImpl();
            for(Object obj:level4OuIdList)
            {
                level4OuId=(String)obj;
                ou=oudao.getOrganizationUnit(level4OuId);
                if(ou !=null)
                {
                    if(!uniqueLevel3OuIdList.contains(ou.getParentId()))
                    {
                        parentOu=oudao.getOrganizationUnit(ou.getParentId());
                        if(parentOu !=null)
                        {
                            uniqueLevel3OuIdList.add(parentOu.getUid());
                            level3OuList.add(parentOu);
                        }
                    }
                }
            }
        }
        System.err.println("level3OuList size in HouseholdEnrollmentDaoImpl.getDistinctLevel3OuList(ReportParameterTemplate) is "+level3OuList.size());
        return level3OuList;
    }
    public List getDistinctLevel2OuList() throws Exception
    {
        List level2OuList=new ArrayList();
        List uniqueLevel2OuIdList=new ArrayList();
        List uniqueLevel3OuIdList=new ArrayList();
        List level4OuIdList=getDistinctLevel4OrganizationUnit();
        if(level4OuIdList !=null)
        {
            String level4OuId=null;
            OrganizationUnit ou=null;
            OrganizationUnit parentOu=null;
            OrganizationUnit level2Ou=null;
            OrganizationUnitDao oudao=new OrganizationUnitDaoImpl();
            for(Object obj:level4OuIdList)
            {
                level4OuId=(String)obj;
                ou=oudao.getOrganizationUnit(level4OuId);
                if(ou !=null)
                {
                    if(!uniqueLevel3OuIdList.contains(ou.getParentId()))
                    {
                        parentOu=oudao.getOrganizationUnit(ou.getParentId());
                        if(parentOu !=null)
                        {
                            uniqueLevel3OuIdList.add(parentOu.getUid());
                            level2Ou=oudao.getOrganizationUnit(parentOu.getParentId());
                            if(level2Ou !=null && !uniqueLevel2OuIdList.contains(level2Ou.getUid()))
                            {
                                uniqueLevel2OuIdList.add(level2Ou.getUid());
                                level2OuList.add(level2Ou);
                            }
                        }
                    }
                }
            }
        }
        System.err.println("level2OuList size in HouseholdEnrollmentDaoImpl.getDistinctLevel2OuList(ReportParameterTemplate) is "+level2OuList.size());
        return level2OuList;
    }
    public int getNumberOfHouseholdsWithCasePlan(ReportParameterTemplate rpt,String startDate,String endDate,int casePlanValue,String sex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct hhe.hhUniqueId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+sqg.getHouseholdHeadQuery()+ageQuery+sqg.getAdultHouseholdMemberGenderQuery(sex) +sqg.getHouseholdsWithCasePlanQuery(casePlanValue)+additionalOrgUnitQuery+sqg.getHouseholdCasePlanDevelopmentDateQuery(startDate,startDate)+markedForDeleteQuery;
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
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return count;
    }
    public List getListOfHouseholdsWithCasePlan(ReportParameterTemplate rpt,String startDate,String endDate,int casePlanValue,String sex) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+sqg.getHouseholdHeadQuery()+sqg.getAdultHouseholdMemberGenderQuery(sex)+ageQuery+sqg.getHouseholdsWithCasePlanQuery(casePlanValue)+additionalOrgUnitQuery+sqg.getHouseholdCasePlanDevelopmentDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteQuery;
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
                    hheList.add(objArray[1]);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hheList;
    }
    public int getNumberOfHouseholdsWithoutAddress(ReportParameterTemplate rpt) throws Exception
    {
        int count=0;
        try
        {
            //SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list= session.createQuery("select count(distinct hhe.hhUniqueId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+" and (hhe.address is null or LENGTH(TRIM(hhe.address))=0 or TRIM(hhe.address)='N/A')"+additionalOrgUnitQuery).list();
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
    public List getListOfHouseholdsWithNearestFacilityRecords() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list= session.createQuery("from HouseholdEnrollment hhe where hhe.nearestFacility is not null").list();
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
    public List getListOfHouseholdsWithoutAddress(ReportParameterTemplate rpt) throws Exception
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
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list= session.createQuery(SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+" and (hhe.address is null or LENGTH(TRIM(hhe.address))=0 or TRIM(hhe.address)='N/A')"+additionalOrgUnitQuery).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[1]);
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
    public List getHouseholdEnrollmentWithoutAddress() throws Exception
    {
       List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HouseholdEnrollment hhe where (hhe.address is null or LENGTH(TRIM(hhe.address))=0)").list();
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
    public List getDistinctDateOfEnrollmentAscending() throws Exception
    {
       List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct hhe.dateOfAssessment from HouseholdEnrollment hhe order by hhe.dateOfAssessment asc").list();
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
    public List searchHouseholdEnrollmentByPartOfName(ReportParameterTemplate rpt,String partOfName) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            if(partOfName !=null)
            {
                SubQueryGenerator sqg=new SubQueryGenerator();
                String additionalOrgUnitQuery="";
                if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
                {
                    additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
                }
                
                partOfName=partOfName.trim();
                if(partOfName.length() <1)
                {
                    hheList=new ArrayList();
                }
                else
                {
                    partOfName=partOfName.toUpperCase();
                    String query=SubQueryGenerator.getHheOrganizationUnitQuery()+additionalOrgUnitQuery+" and (UPPER(hhe.firstName) like '"+partOfName+"%' or UPPER(hhe.surname) like '"+partOfName+"%')"+markedForDeleteQuery+" order by hhe.firstName";
                    
                    System.err.println("query is "+query);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    List list = session.createQuery(query).list();
                    tx.commit();
                    closeSession(session);
                    if(list !=null && !list.isEmpty())
                    {
                        for(Object obj:list)
                        {
                            Object[] objArray=(Object[])obj;
                            HouseholdEnrollment hhe=(HouseholdEnrollment)objArray[0];
                            hheList.add(hhe);
                        }
                    }
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
             ex.printStackTrace();
            //throw new Exception(ex);
         }
        return hheList;
    }
    public void saveHouseholdEnrollment(HouseholdEnrollment hhe) throws Exception
    {
        try
        {
            //System.err.println("hhe.getHhUniqueId(): "+hhe.getHhUniqueId()+" hhe.getDateOfEnrollment(): "+DateManager.convertDateToString(hhe.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT));
            if(hhe !=null && hhe.getDateOfAssessment() !=null && hhe.getHhUniqueId() !=null)
            {
                if(this.getHouseholdEnrollment(hhe.getHhUniqueId())==null)
                {
                    if(hhe.getDateCasePlanDeveloped()==null)
                    hhe.setDateCasePlanDeveloped(hhe.getDateOfAssessment());
                    //hhe=getPreparedHouseholdEnrollment(hhe);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(hhe);
                    tx.commit();
                    closeSession(session);
                    System.err.println("Household enrollment with id "+hhe.getHhUniqueId()+" saved");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateHouseholdEnrollment(HouseholdEnrollment hhe) throws Exception
    {
        try
        {
            if(hhe !=null && hhe.getDateOfAssessment() !=null && hhe.getHhUniqueId() !=null)
            {
                HouseholdEnrollment hhe2=getHouseholdEnrollment(hhe.getHhUniqueId());
                if(hhe2 !=null)
                {
                    if(hhe2.getLastModifiedDate().before(hhe.getLastModifiedDate()) || hhe2.getLastModifiedDate().equals(hhe.getLastModifiedDate()))
                    {
                        if(hhe.getDateCasePlanDeveloped()==null)
                        hhe.setDateCasePlanDeveloped(hhe.getDateOfAssessment());
                        hhe=updateHouseholdEnrollmentInformation(hhe,hhe2);
                        session = HibernateUtil.getSession();
                        tx = session.beginTransaction();
                        session.update(hhe);
                        tx.commit();
                        closeSession(session);
                        System.err.println("Household profile with id "+hhe.getHhUniqueId()+" updated");
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
    public void markedForDelete(HouseholdEnrollment hhe) throws Exception
    {
        try
        {
            if(hhe !=null)
            {
                HouseholdEnrollment hhe2=getHouseholdEnrollment(hhe.getHhUniqueId());
                if(hhe2 !=null)
                {
                    hhe2.setMarkedForDelete(1);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(hhe2);
                    tx.commit();
                    closeSession(session);
                    markDependentRecordForDelete(hhe2.getHhUniqueId());
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteHouseholdEnrollment(HouseholdEnrollment hhe) throws Exception
    {
        try
        {
            if(hhe !=null && this.getHouseholdEnrollment(hhe.getHhUniqueId()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(hhe);
                tx.commit();
                closeSession(session);
                deleteDependents(hhe.getHhUniqueId());
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    
    private void markDependentRecordForDelete(String hhUniqueId) throws Exception
    {
        AdultHouseholdMemberDao ahmdao=new AdultHouseholdMemberDaoImpl();
        ChildEnrollmentDao dao=new ChildEnrollmentDaoImpl();
        List ahmList=ahmdao.getAdultHouseholdMembersPerHousehold(hhUniqueId);
        if(ahmList !=null && !ahmList.isEmpty())
        {
            AdultHouseholdMember ahm=null;
            for(Object obj:ahmList)
            {
                ahm=(AdultHouseholdMember)obj;
                ahmdao.markForDelete(ahm);
            }
        }
        List ovcList=dao.getOvcPerHousehold(hhUniqueId);
        if(ovcList !=null && !ovcList.isEmpty())
        {
            Ovc ovc=null;
            for(Object obj:ovcList)
            {
                ovc=(Ovc)obj;
                dao.markForDelete(ovc);
            }
        }
    }
    private void deleteDependents(String hhUniqueId) throws Exception
    {
        AdultHouseholdMemberDao ahmdao=new AdultHouseholdMemberDaoImpl();
        ChildEnrollmentDao dao=new ChildEnrollmentDaoImpl();
        List ahmList=ahmdao.getAdultHouseholdMembersPerHousehold(hhUniqueId);
        if(ahmList !=null && !ahmList.isEmpty())
        {
            AdultHouseholdMember ahm=null;
            for(Object obj:ahmList)
            {
                ahm=(AdultHouseholdMember)obj;
                ahmdao.deleteAdultHouseholdMember(ahm);
            }
        }
        List ovcList=dao.getOvcPerHousehold(hhUniqueId);
        if(ovcList !=null && !ovcList.isEmpty())
        {
            Ovc ovc=null;
            for(Object obj:ovcList)
            {
                ovc=(Ovc)obj;
                dao.deleteOvc(ovc);
            }
        }
    }
    public HouseholdEnrollment getHouseholdEnrollment(String hhUniqueId) throws Exception
    {
       HouseholdEnrollment hhe=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HouseholdEnrollment hhe where hhe.hhUniqueId=:hhId"+markedForDeleteQuery).setString("hhId", hhUniqueId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                hhe=(HouseholdEnrollment)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hhe;
    }
    public HouseholdEnrollment getHouseholdEnrollmentByEnrollmentId(String enrollmentId) throws Exception
    {
        HouseholdEnrollment hhe=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from HouseholdEnrollment hhe where hhe.enrollmentId=:enId").setString("enId", enrollmentId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                hhe=(HouseholdEnrollment)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hhe;
    }
    public List getHouseholdEnrollmentByOrgUnit(String organizationUnitId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HouseholdEnrollment hhe where hhe.organizationUnit=:ouId"+markedForDeleteQuery).setString("ouId", organizationUnitId).list();
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
    public List getHouseholdEnrollmentRecords(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            
            String currentEnrollmentStatusQuery="";
            String sexQuery="";
            if(sex !=null)
            {
                //sexQuery=sqg.getOvcSexQuery(sex);
            }
            String ageQuery=sqg.getHouseholdHeadBaselineAgeQuery(startAge,endAge);
            String query=SubQueryGenerator.getHheOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getHheAssessmentDateQuery(startDate, endDate)+markedForDeleteQuery;
            System.err.println("query is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[0]);
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
    /*public List getHouseholdEnrollmentRecordsForRegister(ReportParameterTemplate rpt) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getHheAssessmentDateQuery(rpt.getStartDate(),rpt.getEndDate())+" order by ahm.firstName,ahm.surname";
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                AdultHouseholdMember ahm=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ahm=(AdultHouseholdMember)objArray[1];
                    hhe.setPrCaregiver(ahm);
                    hheList.add(hhe);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hheList;
    }*/
    public List getHouseholdEnrollmentAndRevisedAssessmentRecords(ReportParameterTemplate rpt) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getAdultHouseholdAgeAtBaselineQuery(rpt.getStartAge(),rpt.getEndAge());
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberRevisedHouseholdAssessmentOrganizationUnitQuery()+additionalOrgUnitQuery+ageQuery+sqg.getHheAssessmentDateQuery(rpt.getStartDate(),rpt.getEndDate())+" and hhe.dateOfAssessment=rhva.dateOfAssessment"+markedForDeleteQuery+" order by ahm.firstName,ahm.surname";
            System.err.println("query in getHouseholdEnrollmentRecords is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                AdultHouseholdMember ahm=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ahm=(AdultHouseholdMember)objArray[1];
                    hhe.setPrCaregiver(ahm);
                    hhe.setRhva((RevisedHouseholdVulnerabilityAssessment)objArray[2]);
                    hheList.add(hhe);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hheList;
    }
    public List getHouseholdEnrollmentRecords(ReportParameterTemplate rpt) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getAdultHouseholdAgeAtBaselineQuery(rpt.getStartAge(),rpt.getEndAge());
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+additionalOrgUnitQuery+ageQuery+sqg.getHheAssessmentDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteQuery+" order by ahm.firstName,ahm.surname";
            System.err.println("query in getHouseholdEnrollmentRecords is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                AdultHouseholdMember ahm=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ahm=(AdultHouseholdMember)objArray[1];
                    hhe.setPrCaregiver(ahm);
                    hheList.add(hhe);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hheList;
    }
    public List getHouseholdEnrollmentRecordsMarkedForDelete(ReportParameterTemplate rpt) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getHheLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+" and hhe.markedForDelete=1";
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
                    hheList.add(objArray[0]);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hheList;
    }
    public List getHouseholdEnrollmentRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and hhe.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getHheLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
                    hheList.add(objArray[0]);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return hheList;
    }
    public List getAllHouseholdEnrollmentRecords() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HouseholdEnrollment hhe where hhe.hhUniqueId is not null "+markedForDeleteQuery).list();
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
    
    public HouseholdEnrollment updateHouseholdEnrollmentInformation(HouseholdEnrollment newhhe,HouseholdEnrollment existinghhe) throws Exception
    {
        if(newhhe !=null)
        {
            /*if(newhhe.getCurrentEnrollmentStatus()==0)
            newhhe.setCurrentEnrollmentStatus(existinghhe.getCurrentEnrollmentStatus());
            if(newhhe.getHhHasCasePlan()==0)
            newhhe.setHhHasCasePlan(existinghhe.getHhHasCasePlan());
            if(newhhe.getNearestFacility()==null)
            newhhe.setNearestFacility(existinghhe.getNearestFacility());
            if(newhhe.getNearestSchool()==null)
            newhhe.setNearestSchool(existinghhe.getNearestSchool());*/
        }
        return newhhe;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
