/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.CareAndSupportChecklist;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.dao.SubQueryGenerator;
import com.nomis.ovc.util.AppConstant;
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
public class CareAndSupportChecklistDaoImpl implements CareAndSupportChecklistDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and casc.markedForDelete=0";
    public int removeRecordsOfNonPositiveBeneficiaries() throws Exception
    {
        int count=0;
        try
        {
            List list=new ArrayList();
            list.addAll(getRecordsOfNonPositiveOvc());
            list.addAll(getRecordsOfNonPositiveAdultHouseholdMembers());
            if(list !=null && !list.isEmpty())
            {
                CareAndSupportChecklist csc=null;
                for(Object obj:list)
                {
                    csc=(CareAndSupportChecklist)obj;
                    this.deleteCareAndSupportChecklist(csc);
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
    public List getRecordsOfNonPositiveOvc() throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String query="from Ovc ovc, CareAndSupportChecklist csc where ovc.ovcId=csc.beneficiaryId and ovc.currentHivStatus !=1 ";
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                CareAndSupportChecklist csc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    csc=(CareAndSupportChecklist)objArray[1];
                    mainList.add(csc);
                    
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
    public List getRecordsOfNonPositiveAdultHouseholdMembers() throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String query="from AdultHouseholdMember ahm, CareAndSupportChecklist csc where ahm.beneficiaryId=csc.beneficiaryId and ahm.currentHivStatus !=1 ";
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                CareAndSupportChecklist csc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    csc=(CareAndSupportChecklist)objArray[1];
                    mainList.add(csc);
                    
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
    public int getNumberOfOvcAdherenceToTreatmentRegimentInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            DateManager dm=new DateManager();
            //Calculate six months before this report for the care and support assessment
            String careAndSupportAssessmentStartDate=dm.getStartOfNextMonthAfterMonthAdd(DateManager.DB_DATE_FORMAT,endDate, -6);
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getOvcCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String currentEnrollmentQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            //ART support value is 1 if beneficiary skipped arv regimen and 2 if adhered to it
            String artSupportQuery=SubQueryGenerator.getARTAdherenceSupportQuery(2);
            String dateOfArtSupportQuery=SubQueryGenerator.getCareAndSupportDateOfAssessmentQuery(careAndSupportAssessmentStartDate,endDate);
            additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String servicePeriodQuery=sqg.getOvcServiceDateQuery(startDate, endDate);
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitCareAndSupportServiceQuarterlyStatusTrackerQuery()+additionalOrgUnitQuery+ageQuery+sqg.getOvcSexQuery(sex)+servicePeriodQuery+artSupportQuery+dateOfArtSupportQuery+currentEnrollmentQuery+markedForDeleteQuery;
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
    public int getNumberOfAdultHouseholdMembersAdherenceToTreatmentRegimentInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            DateManager dm=new DateManager();
            //Calculate six months before this report for the care and support assessment
            String careAndSupportAssessmentStartDate=dm.getStartOfNextMonthAfterMonthAdd(DateManager.DB_DATE_FORMAT,endDate, -6);
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String currentEnrollmentQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            //ART support value is 1 if beneficiary skipped arv regimen and 2 if adhered to it
            String artSupportQuery=SubQueryGenerator.getARTAdherenceSupportQuery(2);
            String dateOfArtSupportQuery=SubQueryGenerator.getCareAndSupportDateOfAssessmentQuery(careAndSupportAssessmentStartDate,endDate);
            additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String servicePeriodQuery=sqg.getHouseholdServiceDateQuery(startDate, endDate);
            String query="select count(distinct ahm.beneficiaryId) "+SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitCareAndSupportServiceQuarterlyStatusTrackerQuery()+additionalOrgUnitQuery+sqg.getAdultHouseholdMemberGenderQuery(sex)+ageQuery+servicePeriodQuery+artSupportQuery+dateOfArtSupportQuery+currentEnrollmentQuery+markedForDeleteQuery;
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
    public List getListOfAdultHouseholdMembersAdherenceToTreatmentRegimentInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String currentEnrollmentQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            String artSupportQuery=SubQueryGenerator.getARTAdherenceSupportQuery(2);
            String dateOfArtSupportQuery=SubQueryGenerator.getCareAndSupportDateOfAssessmentQuery(startDate,endDate);
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitCareAndSupportQuery()+sqg.getAdultHouseholdMemberGenderQuery(sex)+artSupportQuery+dateOfArtSupportQuery+currentEnrollmentQuery+ageQuery+additionalOrgUnitQuery+markedForDeleteQuery;
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
    public List getListOfAdultHouseholdMembersSupportedToAccessARTServicesInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String currentEnrollmentQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            String artSupportQuery=SubQueryGenerator.getARTTransportationSupportQuery(1);
            String dateOfArtSupportQuery=SubQueryGenerator.getCareAndSupportDateOfAssessmentQuery(startDate,endDate);
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitCareAndSupportQuery()+sqg.getAdultHouseholdMemberGenderQuery(sex)+artSupportQuery+dateOfArtSupportQuery+currentEnrollmentQuery+ageQuery+additionalOrgUnitQuery+markedForDeleteQuery;
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
    public int getNumberOfOvcSupportedToAccessARTServicesInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String ageQuery=sqg.getOvcCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(enrollmentStatus);
            //ART support value is 1 if given transportation support and 2 when not given
            String artSupportQuery=SubQueryGenerator.getARTTransportationSupportQuery(1);
            String dateOfArtSupportQuery=SubQueryGenerator.getCareAndSupportDateOfAssessmentQuery(startDate,endDate);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count(distinct ovc.ovcId) "+SubQueryGenerator.getHheOvcOrganizationUnitCareAndSupportQuery()+sqg.getOvcSexQuery(sex)+artSupportQuery+dateOfArtSupportQuery+currentEnrollmentQuery+ageQuery+additionalOrgUnitQuery+markedForDeleteQuery;
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
    public List getListOfOvcSupportedToAccessARTServicesInReportPeriod(ReportParameterTemplate rpt,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        List hheList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String ageQuery=sqg.getOvcCurrentAgeQuery(rpt.getStartAge(), rpt.getEndAge());
            String currentEnrollmentQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(enrollmentStatus);
            String artSupportQuery=SubQueryGenerator.getARTTransportationSupportQuery(1);
            String dateOfArtSupportQuery=SubQueryGenerator.getCareAndSupportDateOfAssessmentQuery(startDate,endDate);
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcOrganizationUnitCareAndSupportQuery()+sqg.getOvcSexQuery(sex)+artSupportQuery+dateOfArtSupportQuery+currentEnrollmentQuery+ageQuery+additionalOrgUnitQuery+markedForDeleteQuery;
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
    public int getOvcRecordsWhoAreVirallySuppressed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            DateManager dm=new DateManager();
            //Calculate 12 months before this report for the viral load test date
            String viralLoadTestStartDate=dm.getStartOfNextMonthAfterMonthAdd(DateManager.DB_DATE_FORMAT,endDate, -12);
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String enrolledOnTreatmentQeury=SubQueryGenerator.getOvcHivPositiveOnTreatmentQuery();
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge,endAge);
            String sexQuery=sqg.getOvcSexQuery(sex);
            String viralLoadPeriodQuery=sqg.getViralLoadSuppressedQuery(viralLoadTestStartDate, endDate);
            String enrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(enrollmentStatus);
            String query="select count(distinct ovc.ovcId)"+SubQueryGenerator.getHheOvcCareAndSupportOrganizationUnitQuery()+orgUnitQuery+enrollmentStatusQuery+ageQuery+sexQuery+enrolledOnTreatmentQeury+viralLoadPeriodQuery+markedForDeleteQuery;
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
    public int getAdultHouseholdMemberRecordsWhoAreVirallySuppressed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            DateManager dm=new DateManager();
            //Calculate 12 months before this report for the viral load test date
            String viralLoadTestStartDate=dm.getStartOfNextMonthAfterMonthAdd(DateManager.DB_DATE_FORMAT,endDate, -12);
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String enrolledOnTreatmentQeury=SubQueryGenerator.getAdultHouseholdMemberHivPositiveOnTreatmentQuery();
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(startAge,endAge);
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String viralLoadPeriodQuery=sqg.getViralLoadSuppressedQuery(viralLoadTestStartDate, endDate);
            String enrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            String query="select count(distinct ahm.beneficiaryId)"+SubQueryGenerator.getHheAdultHouseholdMemberCareAndSupportOrganizationUnitQuery()+orgUnitQuery+enrollmentStatusQuery+ageQuery+sexQuery+enrolledOnTreatmentQeury+viralLoadPeriodQuery+markedForDeleteQuery;
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
    public int getOvcRecordsWithViralLoadResult(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            DateManager dm=new DateManager();
            //Calculate 12 months before this report for the viral load test date
            String viralLoadTestStartDate=dm.getStartOfNextMonthAfterMonthAdd(DateManager.DB_DATE_FORMAT,endDate, -12);
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String enrolledOnTreatmentQeury=SubQueryGenerator.getOvcHivPositiveOnTreatmentQuery();
            String ageQuery=sqg.getOvcCurrentAgeQuery(startAge,endAge);
            String sexQuery=sqg.getOvcSexQuery(sex);
            String viralLoadPeriodQuery=sqg.getViralLoadTestDateQuery(viralLoadTestStartDate, endDate);
            String enrollmentStatusQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(enrollmentStatus);
            String query="select count(distinct ovc.ovcId)"+SubQueryGenerator.getHheOvcCareAndSupportOrganizationUnitQuery()+orgUnitQuery+enrollmentStatusQuery+ageQuery+sexQuery+enrolledOnTreatmentQeury+viralLoadPeriodQuery+markedForDeleteQuery;
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
    public int getAdultHouseholdMemberRecordsWithViralLoadResult(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            DateManager dm=new DateManager();
            //Calculate 12 months before this report for the viral load test date
            String viralLoadTestStartDate=dm.getStartOfNextMonthAfterMonthAdd(DateManager.DB_DATE_FORMAT,endDate, -12);
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String enrolledOnTreatmentQeury=SubQueryGenerator.getAdultHouseholdMemberHivPositiveOnTreatmentQuery();
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(startAge,endAge);
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String viralLoadPeriodQuery=sqg.getViralLoadTestDateQuery(viralLoadTestStartDate, endDate);
            String enrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            String query="select count(distinct ahm.beneficiaryId)"+SubQueryGenerator.getHheAdultHouseholdMemberCareAndSupportOrganizationUnitQuery()+orgUnitQuery+enrollmentStatusQuery+ageQuery+sexQuery+enrolledOnTreatmentQeury+viralLoadPeriodQuery+markedForDeleteQuery;
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
    public List getCareAndSupportRecordsWithNullOvcTreatmentStatus() throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String query="from Ovc ovc, CareAndSupportChecklist casc where ovc.ovcId=casc.beneficiaryId and (ovc.hivTreatmentFacilityId is null or ovc.hivTreatmentFacilityId=' ') and casc.facilityId is not null and casc.facilityId !='select'";
            //System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                Ovc ovc=null;
                CareAndSupportChecklist casc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    ovc=(Ovc)objArray[0];
                    casc=(CareAndSupportChecklist)objArray[1];
                    ovc.setHivTreatmentFacilityId(casc.getFacilityId());
                    ovc.setEnrolledOnTreatment(casc.getEnrolledOnTreatment());
                    //casc.setBeneficiary(ovc);
                    mainList.add(ovc);
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
    public List getCareAndSupportRecordsWithNullAdultHouseholdMemberTreatmentStatus() throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String query="from AdultHouseholdMember ahm, CareAndSupportChecklist casc where ahm.beneficiaryId=casc.beneficiaryId and (ahm.hivTreatmentFacilityId is null or ahm.hivTreatmentFacilityId=' ') and casc.facilityId is not null and casc.facilityId !='select' and casc.facilityId !=' '";
            //System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                AdultHouseholdMember ahm=null;
                CareAndSupportChecklist casc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    ahm=(AdultHouseholdMember)objArray[0];
                    casc=(CareAndSupportChecklist)objArray[1];
                    ahm.setHivTreatmentFacilityId(casc.getFacilityId());
                    ahm.setEnrolledOnTreatment(casc.getEnrolledOnTreatment());
                    mainList.add(ahm);
                    //casc.setBeneficiary(ovc);
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
    public List getMostRecentCareAndSupportRecords(ReportParameterTemplate rpt) throws Exception
    {
        List recentCareAndSupportList=new ArrayList();
        List cascList=new ArrayList();
        if(getOvcCareAndSupportRecords(rpt) !=null)
        cascList.addAll(getOvcCareAndSupportRecords(rpt));
        if(getAdultCareAndSupportRecords(rpt) !=null)
        cascList.addAll(getAdultCareAndSupportRecords(rpt));
        
        if(cascList !=null && !cascList.isEmpty())
        {
            List uniqueIdList=new ArrayList();
            CareAndSupportChecklist casc=null;
            for(Object obj:cascList)
            {
                casc=(CareAndSupportChecklist)obj;
                if(!uniqueIdList.contains(casc.getBeneficiaryId()))
                {
                    recentCareAndSupportList.add(casc);
                    uniqueIdList.add(casc.getBeneficiaryId());
                }
            }
        }
        
        return recentCareAndSupportList;
    }
    public List getOvcCareAndSupportRecords(ReportParameterTemplate rpt) throws Exception
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
            String query=SubQueryGenerator.getHheOvcCareAndSupportOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getCareAndSupportDateOfAssessmentQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteQuery+" order by casc.dateOfAssessment desc";
            //System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                Ovc ovc=null;
                HouseholdEnrollment hhe=null;
                CareAndSupportChecklist casc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ovc=(Ovc)objArray[1];
                    casc=(CareAndSupportChecklist)objArray[2];
                    ovc.setHhe(hhe);
                    casc.setBeneficiary(ovc);
                    mainList.add(casc);
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
    public List getAdultCareAndSupportRecords(ReportParameterTemplate rpt) throws Exception
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
            String query=SubQueryGenerator.getHheAdultHouseholdMemberCareAndSupportOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getCareAndSupportDateOfAssessmentQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteQuery+" order by casc.dateOfAssessment desc";
            //System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                AdultHouseholdMember ahm=null;
                HouseholdEnrollment hhe=null;
                CareAndSupportChecklist casc=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ahm=(AdultHouseholdMember)objArray[1];
                    casc=(CareAndSupportChecklist)objArray[2];
                    ahm.setHhe(hhe);
                    casc.setBeneficiary(ahm);
                    mainList.add(casc);
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
    public List getCareAndSupportRecords(ReportParameterTemplate rpt) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            List ovcCareAndSupportList=getOvcCareAndSupportRecords(rpt);
            List adultCareAndSupportList=getAdultCareAndSupportRecords(rpt);
            if(ovcCareAndSupportList !=null)
            mainList.addAll(ovcCareAndSupportList);
            if(adultCareAndSupportList !=null)
            mainList.addAll(adultCareAndSupportList);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getCareAndSupportRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            List ovcCareAndSupportList=getOvcCareAndSupportRecordsForExport(rpt,markedForDeleteValue);
            List adultCareAndSupportList=getAdultCareAndSupportRecordsForExport(rpt,markedForDeleteValue);
            if(ovcCareAndSupportList !=null)
            mainList.addAll(ovcCareAndSupportList);
            if(adultCareAndSupportList !=null)
            mainList.addAll(adultCareAndSupportList);
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getOvcCareAndSupportRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and casc.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcCareAndSupportOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getCareAndSupportLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
                    mainList.add(objArray[2]);
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
    public List getAdultCareAndSupportRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and casc.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberCareAndSupportOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getCareAndSupportLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
                    mainList.add(objArray[2]);
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
    public CareAndSupportChecklist getCareAndSupportChecklist(int recordId) throws Exception
    {
        CareAndSupportChecklist casc=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CareAndSupportChecklist casc where casc.recordId=:id").setInteger("id", recordId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                casc=(CareAndSupportChecklist)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return casc;
    }
    public CareAndSupportChecklist getCareAndSupportChecklist(String beneficiaryId,Date dateOfAssessment) throws Exception
    {
       CareAndSupportChecklist casc=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CareAndSupportChecklist casc where casc.beneficiaryId=:id and casc.dateOfAssessment=:doa").setString("id", beneficiaryId).setDate("doa", dateOfAssessment).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                casc=(CareAndSupportChecklist)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return casc; 
    }
    public List getCareAndSupportChecklist(String beneficiaryId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="from CareAndSupportChecklist casc where casc.beneficiaryId=:id"+markedForDeleteQuery+"  order by casc.dateOfAssessment desc";
            //System.err.println("query is "+query);
            list = session.createQuery(query).setString("id", beneficiaryId).list();
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
    public void saveCareAndSupportChecklist(CareAndSupportChecklist casc) throws Exception
    {
        try
        {
            if(casc !=null && this.getCareAndSupportChecklist(casc.getBeneficiaryId(),casc.getDateOfAssessment()) ==null)
            {
                ////System.err.println("casc with Id "+casc.getBeneficiaryId()+" about to be saved after cleanup");
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(casc);
                tx.commit();
                closeSession(session);
                //System.err.println("casc with Id "+casc.getBeneficiaryId()+" saved");
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void markForDelete(CareAndSupportChecklist casc) throws Exception
    {
        try
        {
            if(casc !=null)
            {
                 CareAndSupportChecklist casc2=getCareAndSupportChecklist(casc.getBeneficiaryId(),casc.getDateOfAssessment());
                if(casc2 !=null)
                {
                    casc2.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(casc2);
                    tx.commit();
                    closeSession(session);
                    //System.err.println("casc with Id "+casc.getBeneficiaryId()+" marked for delete");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateCareAndSupportChecklist(CareAndSupportChecklist casc) throws Exception
    {
        try
        {
            if(casc !=null)
            {
                 CareAndSupportChecklist casc2=getCareAndSupportChecklist(casc.getBeneficiaryId(),casc.getDateOfAssessment());
                if(casc2 !=null)
                {
                    casc.setRecordId(casc2.getRecordId()); 
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(casc);
                    tx.commit();
                    closeSession(session);
                    //System.err.println("casc with Id "+casc.getBeneficiaryId()+" updated");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteCareAndSupportChecklist(CareAndSupportChecklist casc) throws Exception
    {
        try
        {
            if(casc !=null)
            {
                CareAndSupportChecklist casc2=getCareAndSupportChecklist(casc.getBeneficiaryId(),casc.getDateOfAssessment());
                if(casc2 !=null)
                {
                    casc.setRecordId(casc2.getRecordId()); 
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(casc);
                    tx.commit();
                    closeSession(session);
                    //System.err.println("casc with Id "+casc.getBeneficiaryId()+" deleted");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void changeBeneficiaryId(String oldBeneficiaryId, String newBeneficiaryId) throws Exception
    {
        List list=this.getCareAndSupportChecklist(oldBeneficiaryId);
        if(list !=null)
        {
            CareAndSupportChecklist casc=null;
            for(Object obj:list)
            {
                casc=(CareAndSupportChecklist)obj;
                casc.setBeneficiaryId(newBeneficiaryId);
                if(this.getCareAndSupportChecklist(casc.getBeneficiaryId(), casc.getDateOfAssessment())==null)
                saveCareAndSupportChecklist(casc);
                else
                updateCareAndSupportChecklist(casc);
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
