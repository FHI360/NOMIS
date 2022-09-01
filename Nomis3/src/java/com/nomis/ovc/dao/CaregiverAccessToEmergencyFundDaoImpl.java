/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.CaregiverAccessToEmergencyFund;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.util.AppConstant;
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
public class CaregiverAccessToEmergencyFundDaoImpl implements CaregiverAccessToEmergencyFundDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    String markedForDeleteQuery=" and ceaf.markedForDelete=0";
    public int getNumberOfCaregiversThatHadAccessToEmergencyFundForDatim(ReportParameterTemplate rpt,int startAge, int endAge,String startDate, String endDate,int enrollmentStatus,String sex,int unexpectedExpenditureValue,int abilityToAccessMoneyToPay) throws Exception
    {
        int count=0;
        try
        {
            FinancialYearManager fym=new FinancialYearManager();
            String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
            SubQueryGenerator sqg=new SubQueryGenerator();
            String baseQuery=SubQueryGenerator.getHheAdultHouseholdMemberHouseholdServiceQuarterlyStatusTrackerCaregiverAccessToEmergencyFundOrganizationUnitQuery();
            String additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            String unexpectedExpenditureQuery=sqg.getHouseholdAbilityToPayForUnexpectedExpenditureQuery(unexpectedExpenditureValue,abilityToAccessMoneyToPay);//" and caef.unexpectedExpenditure=1 and caef.accessMoneyToPay=1";
            String caefPeriodQuery=sqg.getCaregiverAccessToEmergencyFundDateOfAssessmentQuery(startDate, endDate);
            String ageQuery=sqg.getAdultHouseholdMemberCurrentAgeQuery(startAge, endAge);
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String quarterlyStatusDateQuery=sqg.getQuarterlyStatusTrackerDateOfCurrentStatusQuery(startOfLastQuarter, endDate);
            String serviceDateQuery=sqg.getHouseholdServiceDateQuery(startOfLastQuarter, endDate);
            String currentEnrollmentStatusQuery=sqg.getEnrollmentStatusQueryByDateOfStatus(enrollmentStatus,startDate, endDate);
            //SubQueryGenerator.getEnrollmentStatusQuery(enrollmentStatus);
            
            String query="select count (distinct hhe.hhUniqueId) "+baseQuery+additionalOrgUnitQuery+ageQuery+sexQuery+currentEnrollmentStatusQuery+serviceDateQuery+quarterlyStatusDateQuery+unexpectedExpenditureQuery+caefPeriodQuery;
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
    public int getNumberOfCaregiversThatHadAccessToEmergencyFund(ReportParameterTemplate rpt,int startAge, int endAge,String startDate, String endDate,int enrollmentStatus,String sex) throws Exception
    {
        int count=0;
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String unexpectedExpenditureQuery=sqg.getHouseholdAbilityToPayForUnexpectedExpenditureQuery(1,1);//" and caef.unexpectedExpenditure=1 and caef.accessMoneyToPay=1";
            String periodQuery=sqg.getCaregiverAccessToEmergencyFundDateOfAssessmentQuery(startDate, endDate);
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String currentEnrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query="select count (distinct hhe.hhUniqueId) "+SubQueryGenerator.getHheAdultHouseholdMemberCaregiverAccessToEmergencyFundOrganizationUnitQuery()+additionalOrgUnitQuery+sexQuery+currentEnrollmentStatusQuery+unexpectedExpenditureQuery+periodQuery;
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
    public List getListOfCaregiversThatHadAccessToEmergencyFund(ReportParameterTemplate rpt,int startAge, int endAge,String startDate, String endDate,int enrollmentStatus,String sex) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            String unexpectedExpenditureQuery=" and caef.unexpectedExpenditure=1";
            String periodQuery=sqg.getCaregiverAccessToEmergencyFundDateOfAssessmentQuery(startDate, endDate);
            String sexQuery=sqg.getAdultHouseholdMemberGenderQuery(sex);
            String currentEnrollmentStatusQuery=SubQueryGenerator.getAdultHouseholdMemberCurrentEnrollmentStatusQuery(enrollmentStatus);
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberCaregiverAccessToEmergencyFundOrganizationUnitQuery()+additionalOrgUnitQuery+sexQuery+currentEnrollmentStatusQuery+unexpectedExpenditureQuery+periodQuery;
            //System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                List uniqueIdList=new ArrayList();
                HouseholdEnrollment hhe=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    if(!uniqueIdList.contains(hhe.getHhUniqueId()))
                    {
                        mainList.add(hhe);
                        uniqueIdList.add(hhe.getHhUniqueId());
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
    public List getCaregiverAccessToEmergencyFundRecords(ReportParameterTemplate rpt) throws Exception
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
            String query=SubQueryGenerator.getHheAdultHouseholdMemberCaregiverAccessToEmergencyFundOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getCaregiverAccessToEmergencyFundDateOfAssessmentQuery(rpt.getStartDate(),rpt.getEndDate());
            //System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                AdultHouseholdMember ahm=null;
                CaregiverAccessToEmergencyFund caef=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    ahm=(AdultHouseholdMember)objArray[1];
                    caef=(CaregiverAccessToEmergencyFund)objArray[2];
                    ahm.setHhe(hhe);
                    caef.setAdultHouseholdMember(ahm);
                    mainList.add(caef);
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
    public List getCaregiverAccessToEmergencyFundRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and caef.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberCaregiverAccessToEmergencyFundOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getCaregiverAccessToEmergencyFundLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
    public void saveCaregiverAccessToEmergencyFund(CaregiverAccessToEmergencyFund ceaf) throws Exception
    {
        try
        {
            if(ceaf !=null && this.getCaregiverAccessToEmergencyFund(ceaf.getBeneficiaryId(),ceaf.getDateOfAssessment()) ==null)
            {
                //System.err.println("ceaf with Id "+ceaf.getBeneficiaryId()+" about to be saved after cleanup");
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(ceaf);
                tx.commit();
                closeSession(session);
                //System.err.println("ceaf with Id "+ceaf.getBeneficiaryId()+" saved");
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void markForDelete(CaregiverAccessToEmergencyFund ceaf) throws Exception
    {
        try
        {
            if(ceaf !=null)
            {
                 CaregiverAccessToEmergencyFund ceaf2=getCaregiverAccessToEmergencyFund(ceaf.getBeneficiaryId(),ceaf.getDateOfAssessment());
                //System.err.println("ceaf with Id "+ceaf.getBeneficiaryId()+" about to be saved after cleanup");
                if(ceaf2 !=null)
                {
                    ceaf2.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(ceaf2);
                    tx.commit();
                    closeSession(session);
                    //System.err.println("ceaf with Id "+ceaf.getBeneficiaryId()+" marked for delete");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateCaregiverAccessToEmergencyFund(CaregiverAccessToEmergencyFund ceaf) throws Exception
    {
        try
        {
            if(ceaf !=null)
            {
                 CaregiverAccessToEmergencyFund ceaf2=getCaregiverAccessToEmergencyFund(ceaf.getBeneficiaryId(),ceaf.getDateOfAssessment());
                //System.err.println("ceaf with Id "+ceaf.getBeneficiaryId()+" about to be saved after cleanup");
                if(ceaf2 !=null)
                {
                    ceaf.setRecordId(ceaf2.getRecordId()); 
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(ceaf);
                    tx.commit();
                    closeSession(session);
                    //System.err.println("ceaf with Id "+ceaf.getBeneficiaryId()+" saved");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteCaregiverAccessToEmergencyFund(CaregiverAccessToEmergencyFund ceaf) throws Exception
    {
        try
        {
            if(ceaf !=null)
            {
                CaregiverAccessToEmergencyFund ceaf2=getCaregiverAccessToEmergencyFund(ceaf.getBeneficiaryId(),ceaf.getDateOfAssessment());
                if(ceaf2 !=null)
                {
                    ceaf.setRecordId(ceaf2.getRecordId()); 
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(ceaf);
                    tx.commit();
                    closeSession(session);
                    //System.err.println("ceaf with Id "+ceaf.getBeneficiaryId()+" saved");
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public CaregiverAccessToEmergencyFund getCaregiverAccessToEmergencyFund(int recordId) throws Exception
    {
        CaregiverAccessToEmergencyFund ceaf=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CaregiverAccessToEmergencyFund ceaf where ceaf.recordId=:id").setInteger("id", recordId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ceaf=(CaregiverAccessToEmergencyFund)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ceaf;
    }
    public CaregiverAccessToEmergencyFund getCaregiverAccessToEmergencyFund(String beneficiaryId,Date dateOfAssessment) throws Exception
    {
       CaregiverAccessToEmergencyFund ceaf=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from CaregiverAccessToEmergencyFund ceaf where ceaf.beneficiaryId=:id and ceaf.dateOfAssessment=:doa").setString("id", beneficiaryId).setDate("doa", dateOfAssessment).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ceaf=(CaregiverAccessToEmergencyFund)list.get(0);
            }
            
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ceaf; 
    }
    public List getCaregiverAccessToEmergencyFund(String beneficiaryId) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String query="from CaregiverAccessToEmergencyFund ceaf where ceaf.beneficiaryId=:id"+markedForDeleteQuery;;
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
    public void changeBeneficiaryId(String oldBeneficiaryId, String newBeneficiaryId) throws Exception
    {
        List list=this.getCaregiverAccessToEmergencyFund(oldBeneficiaryId);
        if(list !=null)
        {
            CaregiverAccessToEmergencyFund caes=null;
            for(Object obj:list)
            {
                caes=(CaregiverAccessToEmergencyFund)obj;
                caes.setBeneficiaryId(newBeneficiaryId);
                if(getCaregiverAccessToEmergencyFund(caes.getBeneficiaryId(), caes.getDateOfAssessment())==null)
                saveCaregiverAccessToEmergencyFund(caes);
                else
                updateCaregiverAccessToEmergencyFund(caes);
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
