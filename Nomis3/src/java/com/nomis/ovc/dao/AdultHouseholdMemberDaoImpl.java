/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.operationsManagement.HivOperationsManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import com.nomis.ovc.util.AppManager;
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
public class AdultHouseholdMemberDaoImpl implements AdultHouseholdMemberDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    HivOperationsManager hom=new HivOperationsManager();
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and ahm.markedForDelete=0";
    public int getNoOfAdultHouseholdMembersNewlyEnrolledAndServed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        String enrollmentDate=sqg.getAdultHouseholdMemberEnrollmentDateQuery(rpt.getStartDate(),rpt.getEndDate());
        String enrollmentStatusDateQuery=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate,endDate);
        String enrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
        //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
        String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(startAge, endAge);
        String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
        String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
        try
        {
            //"+getHheAdultHouseholdMemberOvcOrganizationUnitQuery()
            String query="select count(distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitHouseholdServiceQuarterlyStatusTrackerQuery()+orgUnitQuery+enrollmentStatusQuery+enrollmentStatusDateQuery+enrollmentDate+ageQuery+sexQuery+markedForDeleteQuery;
            System.err.println("query in getNoOfAdultHouseholdMembersNewlyEnrolledAndServed is "+query);
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
    public int getNumberOfAdultsEligibleForViralLoad(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex,boolean newEligibilityOnly) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String viralLoadEligibilityQuery=SubQueryGenerator.getNewViralLoadEligibilityQuery(newEligibilityOnly);
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            
            String enrolledOnTreatmentQuery=SubQueryGenerator.getAdultHouseholdMemberHivPositiveOnTreatmentQuery();
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberHivPositiveDataManagerOrganizationUnitQuery()+sexQuery+currentEnrollmentQuery+ageQuery+enrolledOnTreatmentQuery+additionalOrgUnitQuery+viralLoadEligibilityQuery+markedForDeleteQuery;
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
    public int getNumberOfAdultHouseholdMembersEnrolledOnTreatmentInReportPeriodByEnrollmentStatus(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            //String hivStatusQuery=SubQueryGenerator.getAdultHouseholdMemberHivStatusQuery(hivStatus);
            //String dateOfHivStatusQuery=SubQueryGenerator.getAdultDateOfCurrentHivStatusQuery(startDate, endDate);
            String enrolledOnTreatmentQuery=SubQueryGenerator.getAdultHouseholdMemberHivPositiveOnTreatmentQuery(startDate,endDate);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+sexQuery+currentEnrollmentQuery+ageQuery+enrolledOnTreatmentQuery+additionalOrgUnitQuery+markedForDeleteQuery;
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
    public List getListOfAdultHouseholdMembersEnrolledOnTreatmentInReportPeriodByEnrollmentStatus(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            //String hivStatusQuery=SubQueryGenerator.getAdultHouseholdMemberHivStatusQuery(hivStatus);
            //String dateOfHivStatusQuery=SubQueryGenerator.getAdultDateOfCurrentHivStatusQuery(startDate, endDate);
            String enrolledOnTreatmentQuery=SubQueryGenerator.getAdultHouseholdMemberHivPositiveOnTreatmentQuery(startDate,endDate);
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+sexQuery+currentEnrollmentQuery+ageQuery+enrolledOnTreatmentQuery+additionalOrgUnitQuery+markedForDeleteQuery;
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
    public List getRecordsWithKnownBaselineHivStatusButUnknownCurrentHivStatus() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="from AdultHouseholdMember ahm where ahm.baselineHivStatus>0 and (ahm.currentHivStatus=0 or ahm.currentHivStatus="+AppConstant.HIV_UNKNOWN_NUM+" or ahm.currentHivStatus ="+AppConstant.HIV_UNDISCLOSED_NUM+")";
            System.err.println("query is "+query);
            list = session.createQuery(query).list();
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
    public List getRecordsWithPositiveHivStatusAtBaselineButOtherStatusCurrently() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="from AdultHouseholdMember ahm where ahm.baselineHivStatus="+AppConstant.HIV_POSITIVE_NUM+" and ahm.currentHivStatus !="+AppConstant.HIV_POSITIVE_NUM;
            System.err.println("query is "+query);
            list = session.createQuery(query).list();
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
    public int getNumberOfAdultHouseholdMembersTestedAndRecievedResultInReportPeriodByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,int enrolledOnTreatmentValue,int hivStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            String hivStatusQuery=SubQueryGenerator.getAdultHouseholdMemberHivStatusQuery(hivStatus);
            String dateOfHivStatusQuery=SubQueryGenerator.getAdultDateOfCurrentHivStatusQuery(startDate, endDate);
            String enrolledOnTreatmentQuery="";
            if(enrolledOnTreatmentValue==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            enrolledOnTreatmentQuery=SubQueryGenerator.getAdultHouseholdMemberHivPositiveOnTreatmentQuery(startDate,endDate);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+sexQuery+hivStatusQuery+dateOfHivStatusQuery+currentEnrollmentQuery+ageQuery+additionalOrgUnitQuery+markedForDeleteQuery;
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
    public List getListOfAdultHouseholdMembersTestedAndRecievedResultInReportPeriodByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,int enrolledOnTreatmentValue,int hivStatus,String sex) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String currentEnrollmentQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            String hivStatusQuery=SubQueryGenerator.getAdultHouseholdMemberHivStatusQuery(hivStatus);
            String dateOfHivStatusQuery=SubQueryGenerator.getAdultDateOfCurrentHivStatusQuery(startDate, endDate);
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+sexQuery+hivStatusQuery+dateOfHivStatusQuery+currentEnrollmentQuery+ageQuery+additionalOrgUnitQuery+markedForDeleteQuery;
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
    public int getNoOfAdultHouseholdMembersByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator rqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            //String organizationUnitQueryCriteria=rqg.getOrganizationUnitQuery(rpt);
            String ageQuery=rqg.getAdultHouseholdMemberCurrentAgeQuery(startAge,endAge);
            String dateQuery=rqg.getAdultHouseholdMemberEnrollmentDateQuery(startDate, endDate);
            String queryPart= additionalOrgUnitQuery+ageQuery+sqg.getAdultHouseholdMemberGenderQuery(sex)+dateQuery;
            
            String hivTreatmentQuery="";
            if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            hivTreatmentQuery=SubQueryGenerator.getAdultHouseholdMemberHivPositiveOnTreatmentQuery();
            else if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM)
            hivTreatmentQuery=SubQueryGenerator.getAdultHouseholdMemberHivPositiveNotOnTreatmentQuery();
            
            String query="select count(distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+queryPart+SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(currentEnrollmentStatus)+SubQueryGenerator.getAdultHouseholdMemberHivStatusQuery(hivStatus)+hivTreatmentQuery+markedForDeleteQuery;
            System.err.println("query in getNoOfAdultHouseholdMembersByEnrollmentStatusAndHivStatus is "+query);
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
    public List getListOfAdultHouseholdMembersByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            SubQueryGenerator rqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            //String organizationUnitQueryCriteria=rqg.getOrganizationUnitQuery(rpt);
            String ageQuery=rqg.getAdultHouseholdMemberCurrentAgeQuery(startAge,endAge);
            String dateQuery=rqg.getAdultHouseholdMemberEnrollmentDateQuery(startDate, endDate);
            String queryPart= additionalOrgUnitQuery+ageQuery+sqg.getAdultHouseholdMemberGenderQuery(sex)+dateQuery;
            
            String hivTreatmentQuery="";
            if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            hivTreatmentQuery=SubQueryGenerator.getAdultHouseholdMemberHivPositiveOnTreatmentQuery();
            else if(hivStatus==AppConstant.HIV_POSITIVE_NUM && onTreatment==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM)
            hivTreatmentQuery=SubQueryGenerator.getAdultHouseholdMemberHivPositiveNotOnTreatmentQuery();
            
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+queryPart+SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(currentEnrollmentStatus)+SubQueryGenerator.getAdultHouseholdMemberHivStatusQuery(hivStatus)+hivTreatmentQuery+markedForDeleteQuery;
            System.err.println("query in getListOfAdultHouseholdMembersByEnrollmentStatusAndHivStatus is "+query);
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
    public int updateAdultHouseholdMemberBaselineAge() throws Exception
    {
        int count=0;
        try
        {
            List mainList=getAdultHouseholdMembersWithBaselineAgeLessThan18();
            if(mainList !=null)
            {
                AdultHouseholdMember ahm=null;
                for(Object obj:mainList)
                {
                    ahm=(AdultHouseholdMember)obj;
                    ahm=getAdultHouseholdMemberWithCalculatedAge(ahm);
                    updateAdultHouseholdMember(ahm);
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
    public List getAdultHouseholdMembersWithBaselineAgeLessThan18() throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String ageQuery=sqg.getAdultHouseholdAgeBelow18Query();
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+ageQuery+markedForDeleteQuery+" order by ahm.firstName";
            System.err.println("query in getAdultHouseholdMembers is "+query);
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
    public int getNumberOfAdultHouseholdMembersExitedWithoutGraduation(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(AppConstant.EXITED_WITHOUT_GRADUATION_NUM,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(AppConstant.EXITED_WITHOUT_GRADUATION_NUM);
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            //String query="select count(distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+sqg.getAdultHouseholdMemberGenderQuery(sex)+additionalOrgUnitQuery+sqg.getAdultHouseholdMemberEnrollmentDateOfCurrentStatusQuery(startDate,endDate)+currentEnrollmentStatusQuery+markedForDeleteQuery;
            String query="select count(distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuarterlyStatusTrackerQuery()+sqg.getAdultHouseholdMemberGenderQuery(sex)+additionalOrgUnitQuery+sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startDate,endDate)+currentEnrollmentStatusQuery+markedForDeleteQuery;
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
    public List getListOfAdultHouseholdMembersByLevel4OrganizationUnit(String level4OuId) throws Exception
    {
        List mainList=new ArrayList();
        try
        {     
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+" and ou.ouPath like '%"+level4OuId+"%'"+markedForDeleteQuery+" order by hhe.dateOfAssessment";
            //System.err.println("query is "+query);
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
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
    public int updateAdultHouseholdMembersAge() throws Exception
    {
        int count=0;
        List list=getListOfAdultHouseholdMembersWithZeroAge();
        if(list !=null)
        {
            AdultHouseholdMember ahm=null;
            for(int i=0; i<list.size(); i++)
            {
                ahm=(AdultHouseholdMember)list.get(i);
                updateAdultHouseholdMember(ahm);
                count++;
                //System.err.println("Adult household member "+count+" with age: "+ahm.getAgeAtBaseline()+" and current age: "+ahm.getCurrentAge()+" updated");
            }
        }
        return count;
    }
    public List getListOfAdultHouseholdMembersWithZeroAge() throws Exception
    {
        List list=new ArrayList();
        try
        {
            String query="From AdultHouseholdMember ahm where (ahm.ageAtBaseline=0 or ahm.currentAge=0)";
            System.err.println("query is "+query);
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
    public List searchHouseholdProfileByPartOfName(ReportParameterTemplate rpt,String partOfName) throws Exception
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
                if(partOfName.indexOf("'") !=-1)
                partOfName=partOfName.substring(0, partOfName.indexOf("'"));
                if(partOfName.length() <1)
                {
                    hheList=new ArrayList();
                }
                else
                {
                    partOfName=partOfName.toUpperCase();
                    String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+additionalOrgUnitQuery+" and (UPPER(ahm.firstName) like '"+partOfName+"%' or UPPER(ahm.surname) like '"+partOfName+"%')"+markedForDeleteQuery+" order by ahm.firstName";
                    
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
                            AdultHouseholdMember ahm=(AdultHouseholdMember)objArray[1];
                            hheList.add(ahm);
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
    public void saveAdultHouseholdMember(AdultHouseholdMember ahm) throws Exception
    {
        try
        {
            //System.err.println("Inside saveAdultHouseholdMember(AdultHouseholdMember)");
            if(ahm !=null && ahm.getDateOfEnrollment() !=null)
            {
                if(ahm.getBeneficiaryId() ==null || ahm.getBeneficiaryId().trim().length()==0)
                {
                    ahm.setBeneficiaryId(getUniqueRecordId());
                    ahm.setEnrollmentId(ahm.getBeneficiaryId());
                }
                if(this.getAdultHouseholdMember(ahm.getBeneficiaryId())==null)
                {
                    ahm=getPreparedAdultHouseholdMember(ahm);
                    if(ahm.getCurrentHivStatus()==0 || ahm.getDateOfCurrentHivStatus()==null)
                    {
                        ahm.setCurrentHivStatus(ahm.getBaselineHivStatus());
                        ahm.setDateOfCurrentHivStatus(ahm.getDateOfEnrollment());
                    }
                    ahm=(AdultHouseholdMember)hom.processBeneficiaryHivStatus(ahm);
                    ahm=getAdultHouseholdMemberWithCurrentAge(ahm);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(ahm);
                    tx.commit();
                    closeSession(session);
                    System.err.println("New Adult member saved");
                    //saveOvcHivStatus(ahm);
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateAdultHouseholdMember(AdultHouseholdMember ahm) throws Exception
    {
        try
        {
            //System.err.println("ahm.getEnrolledOnTreatment() in  updateAdultHouseholdMember is "+ahm.getEnrolledOnTreatment()+" and ahm.getDateEnrolledOnTreatment() is "+ahm.getDateEnrolledOnTreatment());
            if(ahm !=null && ahm.getDateOfEnrollment() !=null && !AppUtility.isNull(ahm.getBeneficiaryId()))
            {
                //System.err.println("ahm.getEnrolledOnTreatment() in  updateAdultHouseholdMember is "+ahm.getEnrolledOnTreatment()+" and ahm.getDateEnrolledOnTreatment() is "+ahm.getDateEnrolledOnTreatment());
                    AdultHouseholdMember ahm2=getAdultHouseholdMember(ahm.getBeneficiaryId());
                    if(ahm2 !=null)
                    {
                        DateManager dm=new DateManager();
                        
                        String dateOfCurrentHivStatus=dm.formatDate(ahm.getDateOfCurrentHivStatus(),DateManager.DB_DATE_FORMAT);
                        String dateOfCurrentHivStatus2=dm.formatDate(ahm2.getDateOfCurrentHivStatus(),DateManager.DB_DATE_FORMAT);
                        //System.err.println("ahm2.getLastModifiedDate() is "+ahm2.getLastModifiedDate()+" and ahm.getLastModifiedDate() is "+ahm.getLastModifiedDate());
                        if(ahm2.getLastModifiedDate().before(ahm.getLastModifiedDate()) || ahm2.getLastModifiedDate().equals(ahm.getLastModifiedDate()))
                        {
                            ahm.setDateCreated(ahm2.getDateCreated());
                            ahm=getPreparedAdultHouseholdMember(ahm);
                            ahm=getAdultHouseholdMemberWithCurrentParameters(ahm,ahm2);
                            //System.err.println("ahm.getEnrolledOnTreatment()3 in updateAdultHouseholdMember is "+ahm.getEnrolledOnTreatment()+" and ahm.getDateEnrolledOnTreatment() is "+ahm.getDateEnrolledOnTreatment());
                            if(ahm.getEnrollmentId()==null)
                            ahm.setEnrollmentId(ahm.getBeneficiaryId());
                            /*if(!ahm.isForUpdateHivStatus() && (ahm.getDateOfCurrentHivStatus().before(ahm2.getDateOfCurrentHivStatus()) || dateOfCurrentHivStatus.equals(dateOfCurrentHivStatus2)))
                            {
                                //System.err.println("ahm.getEnrolledOnTreatment()4 in updateAdultHouseholdMember is "+ahm.getEnrolledOnTreatment()+" and ahm.getDateEnrolledOnTreatment() is "+ahm.getDateEnrolledOnTreatment());
                                ahm.setCurrentHivStatus(ahm2.getCurrentHivStatus());
                                ahm.setDateOfCurrentHivStatus(ahm2.getDateOfCurrentHivStatus());
                                if(ahm.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM && ahm.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM)
                                {
                                    ahm.setEnrolledOnTreatment(ahm2.getEnrolledOnTreatment());
                                    ahm.setHivTreatmentFacilityId(ahm2.getHivTreatmentFacilityId());
                                    ahm.setDateEnrolledOnTreatment(ahm2.getDateEnrolledOnTreatment());
                                    //System.err.println("ahm.getEnrolledOnTreatment()5 in updateAdultHouseholdMember is "+ahm.getEnrolledOnTreatment()+" and ahm.getDateEnrolledOnTreatment() is "+ahm.getDateEnrolledOnTreatment());
                                }
                            }
                            if((ahm.getCurrentHivStatus()==0 && ahm2.getCurrentHivStatus()>0))
                            {
                                //System.err.println("ahm.getEnrolledOnTreatment()4 in updateAdultHouseholdMember is "+ahm.getEnrolledOnTreatment()+" and ahm.getDateEnrolledOnTreatment() is "+ahm.getDateEnrolledOnTreatment());
                                ahm.setCurrentHivStatus(ahm2.getCurrentHivStatus());
                                ahm.setDateOfCurrentHivStatus(ahm2.getDateOfCurrentHivStatus());
                                if(ahm.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM && ahm.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM)
                                {
                                    ahm.setEnrolledOnTreatment(ahm2.getEnrolledOnTreatment());
                                    ahm.setHivTreatmentFacilityId(ahm2.getHivTreatmentFacilityId());
                                    ahm.setDateEnrolledOnTreatment(ahm2.getDateEnrolledOnTreatment());
                                    //System.err.println("ahm.getEnrolledOnTreatment()5 in updateAdultHouseholdMember is "+ahm.getEnrolledOnTreatment()+" and ahm.getDateEnrolledOnTreatment() is "+ahm.getDateEnrolledOnTreatment());
                                }
                            }*/
                            
                            //System.err.println("ahm.getCurrentHivStatus() is "+ahm.getCurrentHivStatus()+" ahm.getEnrolledOnTreatment() is "+ahm.getEnrolledOnTreatment()+" ahm.getHivTreatmentFacilityId() is "+ahm.getHivTreatmentFacilityId());
                            ahm=(AdultHouseholdMember)hom.processBeneficiaryHivStatus(ahm);
                            ahm=getAdultHouseholdMemberWithCurrentAge(ahm);
                            //System.err.println("ahm.getEnrolledOnTreatment()6 in updateAdultHouseholdMember is "+ahm.getEnrolledOnTreatment()+" and ahm.getDateEnrolledOnTreatment() is "+ahm.getDateEnrolledOnTreatment());
                            session = HibernateUtil.getSession();
                            tx = session.beginTransaction();
                            session.update(ahm);
                            tx.commit();
                            closeSession(session);
                            System.err.println("Adult Household member record updated");
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
    public void updateAdultHouseholdMemberWithoutDuplicateCheck(AdultHouseholdMember ahm,AdultHouseholdMember existingAhm) throws Exception
    {
        try
        {
            //System.err.println("ahm.getDateOfBirth() "+ahm.getDateOfBirth()+" ahm.getDateOfEnrollment() "+ahm.getDateOfEnrollment()+" ahm.getBeneficiaryId() "+ahm.getBeneficiaryId());
            if(ahm !=null && ahm.getDateOfBirth() !=null && ahm.getDateOfEnrollment() !=null && !AppUtility.isNull(ahm.getBeneficiaryId()))
            {
                if(dateOfBirthBeforeDateOfEnrollment(ahm))
                {
                    ahm=getPreparedAdultHouseholdMember(ahm);
                    ahm=getAdultHouseholdMemberWithCurrentParameters(ahm,existingAhm);
                    ahm=getAdultHouseholdMemberWithCurrentAge(ahm);
                    ahm=(AdultHouseholdMember)hom.processBeneficiaryHivStatus(ahm);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(ahm);
                    tx.commit();
                    closeSession(session);
                    //saveOvcHivStatus(ahm);
                    //System.err.println("Adult household member with age: "+ahm.getAgeAtBaseline()+" and current age: "+ahm.getCurrentAge()+" updated");
                }
            }
            
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    /*public void saveOvcHivStatus(AdultHouseholdMember ahm) throws Exception
    {
        HivStatusManager hsm=new HivStatusManager();
        if(ahm !=null)
        {
            hsm.setBeneficiaryId(ahm.getBeneficiaryId());
            hsm.setBeneficiaryTypeValue(ahm.getBeneficiaryType());
            hsm.setDateCreated(ahm.getDateCreated());
            hsm.setDateOfNewStatus(ahm.getDateOfEnrollment());
            //hsm.setEnrolledOnTreatment(ahm..getChildEnrolledOnTreatment());
            //hsm.setFacilityId(ahm.getFacilityId());
            hsm.setLastModifiedDate(ahm.getLastModifiedDate());
            hsm.setNewHivStatus(ahm.getHivStatusCode());
            hsm.setPointOfUpdateValue(ahm.getPointOfUpdate());
            hsm.setRecordedBy(ahm.getRecordedBy());
            HivStatusManagerDao hsmdao=new HivStatusManagerDaoImpl();
            hsmdao.saveHivStatusManager(hsm,true);
        }
    }*/
    public void markForDelete(AdultHouseholdMember ahm) throws Exception
    {
        try
        {
            if(ahm !=null)
            {
                AdultHouseholdMember ahm2=getAdultHouseholdMember(ahm.getBeneficiaryId());
                if(ahm2 !=null)
                {
                    ahm2.setMarkedForDelete(1);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(ahm2);
                    tx.commit();
                    closeSession(session);
                    markBeneficiaryServicesForDelete(ahm2.getBeneficiaryId());
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteAdultHouseholdMember(AdultHouseholdMember ahm) throws Exception
    {
        try
        {
            if(ahm !=null && !AppUtility.isNull(ahm.getBeneficiaryId()))
            {
                AdultHouseholdMember ahm2=getAdultHouseholdMember(ahm.getBeneficiaryId());
                if(ahm2 !=null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(ahm);
                    tx.commit();
                    closeSession(session);
                    deleteBeneficiaryServices(ahm.getBeneficiaryId());
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public int getNumberOfAdultHouseholdMembersEnrolled(String additionalQuery,int currentEnrollmentStatus,int startAge,int endAge,String sex) throws Exception
    {
        int count=0;
        
        try
        {
            
            String currentEnrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            }
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(startAge,endAge);
            String query="select count (distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+markedForDeleteQuery;
            System.err.println("query is "+query);
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
    public List getListOfAdultHouseholdMembersEnrolled(String additionalQuery,int currentEnrollmentStatus,int startAge,int endAge,String sex) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            
            String currentEnrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            }
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(startAge,endAge);
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+markedForDeleteQuery;
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
    public int getNumberOfAdultHouseholdMembersEnrolledWithinReportPeriod(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception
    {
        int count=0;
        
        try
        {
            
            String currentEnrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            }
            String ageQuery=sqg.getAdultHouseholdAgeAtBaselineQuery(startAge,endAge);
            String query="select count (distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getAdultHouseholdMemberEnrollmentDateQuery(startDate, endDate)+markedForDeleteQuery;
            System.err.println("query is "+query);
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
    public List getListOfAdultHouseholdMembersEnrolledWithinReportPeriod(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,int onTreatment) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            
            String currentEnrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(currentEnrollmentStatus);
            String sexQuery="";
            if(sex !=null)
            {
                sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            }
            String ageQuery=sqg.getAdultHouseholdAgeAtBaselineQuery(startAge,endAge);
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getAdultHouseholdMemberEnrollmentDateQuery(startDate, endDate)+markedForDeleteQuery;
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
    public List getAllAdultHouseholdMembers(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception
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
            //sqg.getAdultHouseholdAgeBelow18Query();// and ahm.ageAtBaseline<18 
            String ageQuery=sqg.getAdultHouseholdAgeAtBaselineQuery(startAge,endAge);
            String query=SubQueryGenerator.getHheAllAdultHouseholdMemberOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getAdultHouseholdMemberEnrollmentDateQuery(startDate, endDate)+markedForDeleteQuery+" order by ahm.firstName";
            System.err.println("query in getAllAdultHouseholdMembers is "+query);
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
    public List getAdultHouseholdMembers(String additionalQuery,int currentEnrollmentStatus,int hivStatus,String startDate, String endDate,int startAge,int endAge,String sex,boolean onTreatment) throws Exception
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
            //sqg.getAdultHouseholdAgeBelow18Query();// and ahm.ageAtBaseline<18 
            String ageQuery=sqg.getAdultHouseholdAgeAtBaselineQuery(startAge,endAge);
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitQuery()+additionalQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+sqg.getAdultHouseholdMemberEnrollmentDateQuery(startDate, endDate)+markedForDeleteQuery+" order by ahm.firstName";
            System.err.println("query in getAdultHouseholdMembers is "+query);
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
    public List getAdultHouseholdMemberRecords(ReportParameterTemplate rpt) throws Exception
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
            String query=SubQueryGenerator.getHheAllAdultHouseholdMemberOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getAdultHouseholdMemberLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteQuery+" order by ahm.firstName, ahm.surname";
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                AdultHouseholdMember ahm=null;
                HouseholdEnrollment hhe=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ahm=(AdultHouseholdMember)objArray[1];
                    ahm.setHhe(hhe);
                    mainList.add(ahm);
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
    public List getAdultHouseholdMemberRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and ahm.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAllAdultHouseholdMemberOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getAdultHouseholdMemberLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
    public List getAdultHouseholdMemberRecordsMarkedForDelete(ReportParameterTemplate rpt) throws Exception
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
            String query=SubQueryGenerator.getHheAllAdultHouseholdMemberOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getAdultHouseholdMemberLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+" and ahm.markedForDelete=1";
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
    public List getAllAdultHouseholdMembers() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from AdultHouseholdMember ahm where ahm.beneficiaryId is not null "+markedForDeleteQuery).list();
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
    public void updateAllAdultHouseholdMembers() throws Exception
    {
        List list=getAllAdultHouseholdMembers();
        if(list !=null)
        {
            int count=0;
            for(Object obj:list)
            {
                AdultHouseholdMember ahm=(AdultHouseholdMember)obj;
                updateAdultHouseholdMember(ahm);
                count++;
                System.err.println(count+" AdultHouseholdMember updated");
            }
        }
    }
    public List getCaregiversPerHousehold(String hhUniqueId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction(); //and ahm.beneficiaryType !=1
            list = session.createQuery("from AdultHouseholdMember ahm where ahm.hhUniqueId=:id "+markedForDeleteQuery).setString("id", hhUniqueId).list();
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
    public List getAdultHouseholdMembersPerHousehold(String hhUniqueId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from AdultHouseholdMember ahm where ahm.hhUniqueId=:id"+markedForDeleteQuery).setString("id", hhUniqueId).list();
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
    public AdultHouseholdMember getAdultHouseholdMember(String beneficiaryId) throws Exception
    {
        AdultHouseholdMember ahm=null;
        try
        {
            //System.err.println("beneficiaryId: "+beneficiaryId);
            if(beneficiaryId !=null)
            beneficiaryId=beneficiaryId.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from AdultHouseholdMember ahm where ahm.beneficiaryId=:id").setString("id", beneficiaryId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ahm=(AdultHouseholdMember)list.get(0);
                //System.err.println("ahm.getFirstName() is "+ahm.getFirstName());
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return ahm;
    }
    public AdultHouseholdMember getAdultHouseholdMemberByTreatmentId(String beneficiaryId) throws Exception
    {
        AdultHouseholdMember ahm=null;
        try
        {
            //System.err.println("beneficiaryId: "+beneficiaryId);
            if(beneficiaryId !=null)
            beneficiaryId=beneficiaryId.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from AdultHouseholdMember ahm where ahm.treatmentId=:id").setString("id", beneficiaryId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ahm=(AdultHouseholdMember)list.get(0);
                //System.err.println("ahm.getFirstName() is "+ahm.getFirstName());
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return ahm;
    }
    public AdultHouseholdMember getAdultHouseholdMemberByName(String hhUniqueId,String firstName,String surname) throws Exception
    {
        AdultHouseholdMember ahm=null;
        try
        {
            System.err.println("firstName is "+firstName+" and surname is "+surname);
            if(hhUniqueId !=null)
            hhUniqueId=hhUniqueId.trim();
            if(firstName !=null)
            firstName=firstName.trim().toUpperCase();
            if(surname !=null)
            surname=surname.trim().toUpperCase();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from AdultHouseholdMember ahm where ahm.hhUniqueId=:id and ((UPPER(ahm.firstName)=:fn and UPPER(ahm.surname)=:sn) or (UPPER(ahm.firstName)=:sn and UPPER(ahm.surname)=:fn))").setString("id", hhUniqueId).setString("fn", firstName).setString("sn", surname).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ahm=(AdultHouseholdMember)list.get(0);
                System.err.println("ahm.getFirstName() is "+ahm.getFirstName());
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return ahm;
    }
    public AdultHouseholdMember getHeadOfHousehold(String hhUniqueId) throws Exception
    {
        AdultHouseholdMember ahm=null;
        try
        {
            //System.err.println("beneficiaryId: "+beneficiaryId);
            if(hhUniqueId !=null)
            hhUniqueId=hhUniqueId.trim();
            session = HibernateUtil.getSession();
            tx = session.beginTransaction(); //and (ahm.beneficiaryType=1 or ahm.beneficiaryType=4)
            List list = session.createQuery("from AdultHouseholdMember ahm where ahm.hhUniqueId=:id").setString("id", hhUniqueId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                //if only one caregiver, assume that is the household head, return as household head
                if(list.size()==1)
                ahm=(AdultHouseholdMember)list.get(0);
                else
                {
                    //Here, loop over and check the adult household member designated as household head and return
                    int count=0;
                    for(Object obj:list)
                    {
                        count++;
                        ahm=(AdultHouseholdMember)obj;
                        if(ahm.getBeneficiaryType()==AppConstant.HOUSEHOLD_TYPE_NUM)
                        break;
                        else
                        {
                            //If none is marked as household head, return the first in the list
                            if(count==list.size()-1)
                            ahm=(AdultHouseholdMember)list.get(0);
                        }
                    }
                }
                //System.err.println("ahm.getFirstName() is "+ahm.getFirstName());
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        //System.err.println("ahm is "+ahm);
        return ahm;
    }
    public AdultHouseholdMember getPreparedAdultHouseholdMember(AdultHouseholdMember ahm)
    {
        if(ahm !=null && ahm.getDateOfEnrollment() !=null)
        {
            AppUtility appUtil=new AppUtility();
            ahm=getAdultHouseholdMemberWithCalculatedAge(ahm);
            ahm.setFirstName(appUtil.changeFirstLetterToUpperCase(ahm.getFirstName()));
            ahm.setSurname(appUtil.changeFirstLetterToUpperCase(ahm.getSurname()));
            
            if(ahm.getCurrentEnrollmentStatus()==0)
            {
                ahm.setCurrentEnrollmentStatus(AppConstant.ACTIVE_NUM);
                ahm.setDateOfCurrentEnrollmentStatus(ahm.getDateOfEnrollment());
            }
        }
        return ahm;
    }
    private AdultHouseholdMember getAdultHouseholdMemberWithCalculatedAge(AdultHouseholdMember ahm)
    {
        if(ahm.getDateOfEnrollment() !=null)
        {
            int currentAge=AppManager.getCurrentAge(DateManager.convertDateToString(ahm.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT), ahm.getAgeAtBaseline());
            Date ddateOfBirth=DateManager.getPreviousDate(ahm.getDateOfEnrollment(), ahm.getAgeAtBaseline());
            ahm.setCurrentAge(currentAge);
            ahm.setDateOfBirth(ddateOfBirth);
       }
        return ahm;
    }
    private AdultHouseholdMember getAdultHouseholdMemberWithCurrentParameters(AdultHouseholdMember ahm,AdultHouseholdMember ahm2)
    {
        if(ahm.getBaselineHivStatus()==0)
        {
            ahm.setBaselineHivStatus(ahm2.getBaselineHivStatus());
        }
        if(ahm.getCurrentHivStatus()==0 || ahm.getDateOfCurrentHivStatus()==null)
        {
            ahm.setCurrentHivStatus(ahm2.getCurrentHivStatus());
            ahm.setDateOfCurrentHivStatus(ahm2.getDateOfEnrollment());
        }
        /*if((ahm.getDateOfCurrentHivStatus().before(ahm2.getDateOfCurrentHivStatus())))
        {
            ahm.setCurrentHivStatus(ahm2.getCurrentHivStatus());
            ahm.setDateOfCurrentHivStatus(ahm2.getDateOfCurrentHivStatus());
        }*/
        if(ahm.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
        {
            if(ahm.getEnrolledOnTreatment()==0)
            {
                ahm.setEnrolledOnTreatment(ahm2.getEnrolledOnTreatment());
                ahm.setHivTreatmentFacilityId(ahm2.getHivTreatmentFacilityId());
            }
        }
        /*if(ahm.getDateOfCurrentHivStatus() !=null && ahm.getDateEnrolledOnTreatment() !=null)
        {
            if(ahm.getDateEnrolledOnTreatment().before(ahm.getDateOfCurrentHivStatus()) || ahm.getDateEnrolledOnTreatment().equals(DateManager.getDateInstance(DateManager.DEFAULT_DATE)))
            {
                ahm.setEnrolledOnTreatment(0);
                ahm.setDateEnrolledOnTreatment(DateManager.getDateInstance(DateManager.DEFAULT_DATE));
            }
        }*/
        if(ahm.getCurrentEnrollmentStatus()==0)
        ahm.setCurrentEnrollmentStatus(ahm2.getCurrentEnrollmentStatus());
        if(ahm.getCurrentAge()==0)
        ahm.setCurrentAge(ahm2.getCurrentAge());
        //if(ahm.getComfortableToDiscloseMemberHivStatus()==0)
        //ahm.setComfortableToDiscloseMemberHivStatus(ahm2.getComfortableToDiscloseMemberHivStatus());
        if(ahm.getAgeAtBaseline()==0)
        ahm.setAgeAtBaseline(ahm2.getAgeAtBaseline());
        //if(ahm.getEnrolledOnTreatment()==0 && ahm.getCurrentHivStatusCode()==1)
        //ahm.setEnrolledOnTreatment(ahm2.getEnrolledOnTreatment());
        return ahm;
    }
    private AdultHouseholdMember getAdultHouseholdMemberWithCurrentAge(AdultHouseholdMember ahm) throws Exception
    {
        if(ahm !=null)
        {
            AppManager appManager=new AppManager();
            AgeObject ageObj=appManager.getCurrentAge(ahm.getDateOfEnrollment(),ahm.getAgeAtBaseline(), ahm.getAgeUnitAtBaseline());
            ahm.setCurrentAge(ageObj.getAge());
            ahm.setCurrentAgeUnit(AppConstant.AGEUNIT_YEAR_NUM);
        }
        return ahm;
    }
    public void markBeneficiaryServicesForDelete(String beneficiaryId) throws Exception
    {
        HouseholdServiceDao hhsdao=new HouseholdServiceDaoImpl();
        hhsdao.markHouseholdServicesForDelete(beneficiaryId);
        //deleteBeneficiaryHivRecords(beneficiaryId);
        this.markReferralRecordsForDelete(beneficiaryId);
    }
    public void deleteBeneficiaryServices(String beneficiaryId) throws Exception
    {
        HouseholdServiceDao hhsdao=new HouseholdServiceDaoImpl();
        hhsdao.deleteHouseholdServicesByBeneficiaryId(beneficiaryId);
        deleteBeneficiaryHivRecords(beneficiaryId);
        deleteBeneficiaryReferralRecords(beneficiaryId);
    }
    /*public void changeAdultHouseholdMemberBeneficiaryId(String oldHhUniqueId, String newHhUniqueId) throws Exception
    {
        List list=getAdultHouseholdMembersPerHousehold(oldHhUniqueId);
        if(list !=null)
        {
            GenderBasedViolenceDao gbvdao=new GenderBasedViolenceDaoImpl();
            HouseholdServiceDao hssdao=new HouseholdServiceDaoImpl();
            ReferralServiceDao rsdao=new ReferralServiceDaoImpl();
            for(Object obj:list)
            {
                AdultHouseholdMember ahm=(AdultHouseholdMember)obj;
                if(ahm.getBeneficiaryId().equalsIgnoreCase(oldHhUniqueId))
                {
                    //if this is the primary caregiver, change the beneficiary Id for all the services it has received and delete it
                    hssdao.changeCaregiverIdInHouseholdService(oldHhUniqueId, newHhUniqueId);
                    rsdao.changeBeneficiaryIdInReferralService(oldHhUniqueId, newHhUniqueId);
                    gbvdao.changeBeneficiaryIdInGBVEnrollment(oldHhUniqueId, newHhUniqueId);
                    this.deleteAdultHouseholdMember(ahm);
                    System.err.println("Adult Household member with Id "+ahm.getBeneficiaryId()+" deleted");
                }
                else
                {
                    //if you get here, then this is a secondary caregiver, just change the hhUniqueId and update
                   ahm.setHhUniqueId(newHhUniqueId);
                   updateAdultHouseholdMember(ahm); 
                   System.err.println("Adult Household member with Id "+ahm.getBeneficiaryId()+" updated");
                }
            }
        }
    }*/
    private void deleteBeneficiaryHivRecords(String beneficiaryId) throws Exception
    {
        HivStatusManagerDao hsmdao=new HivStatusManagerDaoImpl();
        hsmdao.deleteHivStatusManager(beneficiaryId);
    }
    private void markReferralRecordsForDelete(String beneficiaryId) throws Exception
    {
        HouseholdReferralDao rdao=new HouseholdReferralDaoImpl();
        rdao.markServicesForDelete(beneficiaryId);
    }
    private void deleteBeneficiaryReferralRecords(String beneficiaryId) throws Exception
    {
        HouseholdReferralDao rdao=new HouseholdReferralDaoImpl();
        rdao.deleteServicesPerBeneficiary(beneficiaryId);
    }
    public String getUniqueRecordId()
    {
        AppUtility appUtil=new AppUtility();
        String uniqueId=appUtil.generateUniqueId();
        try
        {
            if(this.getAdultHouseholdMember(uniqueId) !=null)
            getUniqueRecordId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public void setAdultHouseholdMemberNewEnrollmentStatus(String hhUniqueId,int enrollmentStatus,Date dateOfNewEnrollmentStatus) throws Exception
    {
        List list=this.getAdultHouseholdMembersPerHousehold(hhUniqueId);
        if(list !=null)
        {
            AdultHouseholdMember ahm=null;
            for(Object obj:list)
            {
                ahm=(AdultHouseholdMember)obj;
                if(ahm.getCurrentEnrollmentStatus()!=AppConstant.DIED_NUM)
                {
                    if(ahm !=null && (ahm.getDateOfCurrentEnrollmentStatus().before(dateOfNewEnrollmentStatus) || ahm.getDateOfCurrentEnrollmentStatus().equals(dateOfNewEnrollmentStatus)))
                    {
                        ahm.setCurrentEnrollmentStatus(enrollmentStatus);
                        ahm.setDateOfCurrentEnrollmentStatus(dateOfNewEnrollmentStatus);
                        updateAdultHouseholdMember(ahm);
                    }
                }
            }
        }
    }
    public void mergeCaregivers(String caregiverIdToDelete,String caregiverIdToKeep) throws Exception
    {
        
        if(caregiverIdToKeep==null)
        return;
        try
        {
            AdultHouseholdMember ahmToDelete=this.getAdultHouseholdMember(caregiverIdToDelete);
            AdultHouseholdMember ahmToKeep=this.getAdultHouseholdMember(caregiverIdToKeep);
            
            if(ahmToKeep !=null)
            {
                HouseholdServiceDao hhsdao=new HouseholdServiceDaoImpl();
                HouseholdReferralDao hhrdao=new HouseholdReferralDaoImpl();
                ChildEnrollmentDao dao=new ChildEnrollmentDaoImpl();
                CareAndSupportChecklistDao cscdao=new CareAndSupportChecklistDaoImpl();
                CaregiverAccessToEmergencyFundDao caesdao=new CaregiverAccessToEmergencyFundDaoImpl();
                BeneficiaryStatusUpdateDao bsudao=new BeneficiaryStatusUpdateDaoImpl();
                
                //Remove this Caregiver from all the OVC assigned
                dao.changeCaregiverId(caregiverIdToDelete, caregiverIdToKeep);
                hhsdao.changeCaregiverIdInHouseholdService(caregiverIdToDelete,caregiverIdToKeep);
                hhrdao.changeBeneficiaryIdInHouseholdReferral(caregiverIdToDelete, caregiverIdToKeep);
                cscdao.changeBeneficiaryId(caregiverIdToDelete, caregiverIdToKeep);
                caesdao.changeBeneficiaryId(caregiverIdToDelete, caregiverIdToKeep);
                bsudao.changeBeneficiaryId(caregiverIdToDelete, caregiverIdToKeep);
                
                markForDelete(ahmToDelete);
            }
        }
        catch (Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        } 
    }
    public void changeCaregiverId(String oldId,String newId) throws Exception
    {
        
        if(newId==null)
        return;
        try
        {
            AdultHouseholdMember ahm=this.getAdultHouseholdMember(oldId);
            String newCaregiverId=newId;
            if(ahm !=null)
            {
                HouseholdServiceDao hhsdao=new HouseholdServiceDaoImpl();
                ChildEnrollmentDao dao=new ChildEnrollmentDaoImpl();
                if(newId.indexOf("/") !=-1)
                {
                    String[] newIdArray=newId.split("/");
                    String hhUniqueId=newId.substring(0, newId.lastIndexOf("/"));
                    if(newIdArray !=null)
                    {
                        if(newIdArray.length<5)
                        hhUniqueId=newId.substring(0, newId.length()-1);
                    }
                }
                //System.err.println("hhUniqueId in cgiverdao.changeCaregiverId is "+hhUniqueId);
                AdultHouseholdMember newAhm=this.getAdultHouseholdMember(newId);
                
                ahm.setBeneficiaryId(newCaregiverId);
                saveAdultHouseholdMember(ahm);
                ahm.setBeneficiaryId(oldId);
                markForDelete(ahm);
                hhsdao.changeCaregiverIdInHouseholdService(oldId,newCaregiverId);
                //Remove this Caregiver from all the OVC assigned
                dao.changeCaregiverId(oldId, newCaregiverId);
            }
        }
        catch (Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        } 
    }
    private boolean dateOfBirthBeforeDateOfEnrollment(AdultHouseholdMember ahm)
    {
        boolean dateOfBirthBeforeDateOfEnrollment=true;
        if(!ahm.getDateOfBirth().before(ahm.getDateOfEnrollment()))
        {
            System.err.println("Date of birth or date of enrollment wrong. Cannot commit this record");
            dateOfBirthBeforeDateOfEnrollment=false;
        }
        return dateOfBirthBeforeDateOfEnrollment;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
