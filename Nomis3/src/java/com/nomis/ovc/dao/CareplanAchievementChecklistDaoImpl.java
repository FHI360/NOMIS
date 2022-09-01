/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.operationsManagement.BeneficiaryExitManager;
import com.nomis.operationsManagement.GraduationManager;
import com.nomis.ovc.business.CareplanAchievementChecklist;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.RevisedHouseholdVulnerabilityAssessment;
import com.nomis.ovc.util.AppConstant;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class CareplanAchievementChecklistDaoImpl implements CareplanAchievementChecklistDao
{
    Session session;
    Transaction tx;
    String markedForDeleteQuery=" and cpa.markedForDelete=0";
    public List getCareplanAchievementChecklistRecords(ReportParameterTemplate rpt) throws Exception
    {
        RevisedHouseholdVulnerabilityAssessmentDao rhvadao=new RevisedHouseholdVulnerabilityAssessmentDaoImpl();
        List mainList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheCareplanAchievementChecklistOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getCareplanAchievementChecklistLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate());
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                CareplanAchievementChecklist cpa=null;
                RevisedHouseholdVulnerabilityAssessment rhva=null;
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    hhe=(HouseholdEnrollment)objArray[0];
                    rhva=rhvadao.getHouseholdVulnerabilityAssessment(hhe.getHhUniqueId(), hhe.getDateOfAssessment());
                    if(rhva !=null)
                    hhe.setRhva(rhva);
                    cpa=(CareplanAchievementChecklist)objArray[1];
                    cpa.setHhe(hhe);
                    mainList.add(cpa);
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
    public List getCareplanAchievementChecklistRecordsForExport(ReportParameterTemplate rpt,int markedForDeleteValue) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            String markedForDeleteRecordsOnlyQuery="";
            if(markedForDeleteValue==1)
            markedForDeleteRecordsOnlyQuery=" and cpa.markedForDelete=1";
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheCareplanAchievementChecklistOrganizationUnitQuery()+additionalOrgUnitQuery+sqg.getCareplanAchievementChecklistLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate())+markedForDeleteRecordsOnlyQuery;
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
    public List getCareplanAchievementChecklistsNotGraduated(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        List cpaList=new ArrayList();
        try
        {
            String query="";
            System.err.println("query in getCareplanAchievementChecklists is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list=session.createQuery(query).list();
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
        if(list !=null)
        {
            for(Object obj:list)
            {
                Object[] objArray=(Object[])obj;
                cpaList.add(objArray[1]);
            }
        }
        return cpaList;
    }
    public List getCareplanAchievementChecklists(String additionalQueryCriteria) throws Exception
    {
        List list=null;
        List cpaList=new ArrayList();
        try
        {
            String query="";
            System.err.println("query in getCareplanAchievementChecklists is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list=session.createQuery(query).list();
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
        if(list !=null)
        {
            for(Object obj:list)
            {
                Object[] objArray=(Object[])obj;
                cpaList.add(objArray[1]);
            }
        }
        return cpaList;
    }
    public List getAllCareplanAchievementChecklists() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list=session.createQuery("from CareplanAchievementChecklist cpa").list();
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
        return list;
    }
    public CareplanAchievementChecklist getCareplanAchievementChecklist(int id) throws Exception
    {
        CareplanAchievementChecklist cpa=null;
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list=session.createQuery("from CareplanAchievementChecklist cpa where cpa.id=:uid").setInteger("uid", id).list();
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
        if(list !=null && !list.isEmpty())
        {
           cpa=(CareplanAchievementChecklist)list.get(0); 
        }
        return cpa;
    }
    public CareplanAchievementChecklist getCareplanAchievementChecklist(String hhUniqueId,Date dateOfAssessment) throws Exception
    {
        CareplanAchievementChecklist cpa=null;
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list=session.createQuery("from CareplanAchievementChecklist cpa where cpa.hhUniqueId=:hhid and cpa.dateOfAssessment=:dt").setString("hhid", hhUniqueId).setDate("dt", dateOfAssessment).list();
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
        if(list !=null && !list.isEmpty())
        {
           cpa=(CareplanAchievementChecklist)list.get(0); 
        }
        return cpa;
    }
    public List getCareplanAchievementChecklistsPerHousehold(String hhUniqueId) throws Exception
    {
       List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list=session.createQuery("from CareplanAchievementChecklist cpa where cpa.hhUniqueId=:hhid order by cpa.dateOfAssessment desc").setString("hhid", hhUniqueId).list();
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            closeSession(session);
        }
        return list;
    }
    public int getGraduationScore(CareplanAchievementChecklist cpa) throws Exception
    {
        int cpascore=0;
        if(cpa !=null)
        {
            //*String enrolledInVocationalTraining=cpa.getSch_adolInVoctraining();
            cpascore+=GraduationManager.getGraduationScore(cpa.getAllChildrenHaveBirthCert());
            cpascore+=GraduationManager.getGraduationScore(cpa.getCgiversEconomicActivity());
            cpascore+=GraduationManager.getGraduationScore(cpa.getChildrenEnrolledInSchool());
            cpascore+=GraduationManager.getGraduationScore(cpa.getChildrenHivStatusknown());
            cpascore+=GraduationManager.getGraduationScore(cpa.getChildrenNotUndernourished());
            cpascore+=GraduationManager.getGraduationScore(cpa.getDocumentedViralLoadResult());
            cpascore+=GraduationManager.getGraduationScore(cpa.getEmotionalSupportTeamIdentification());
            cpascore+=GraduationManager.getGraduationScore(cpa.getHivPosAdolscentsLinked());
            cpascore+=GraduationManager.getGraduationScore(cpa.getHivPreventionKnowledge());
            cpascore+=GraduationManager.getGraduationScore(cpa.getRegularSchoolAttendance());
            cpascore+=GraduationManager.getGraduationScore(cpa.getStableAdultInHousehold());
            cpascore+=GraduationManager.getGraduationScore(cpa.getViolenceIncidenceReport());
            
        }
        return cpascore;
    }
    public void saveCareplanAchievementChecklistForImport(CareplanAchievementChecklist cpa) throws Exception
    {
        try
        {
            if(cpa !=null)
            {
                cpa.setScore(getGraduationScore(cpa));
                session=HibernateUtil.getSession();
                tx=session.beginTransaction();
                session.save(cpa);
                tx.commit();
                session.close();
            }
            else
            {
                System.err.println("cpa is null in saveCareplanAchievementChecklist");
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void updateCareplanAchievementChecklistForImport(CareplanAchievementChecklist cpa) throws Exception
    {
        try
        {
            if(cpa !=null)
            {
                CareplanAchievementChecklist cpa2=this.getCareplanAchievementChecklist(cpa.getHhUniqueId(), cpa.getDateOfAssessment());
                if(cpa2 !=null)
                {
                    cpa.setRecordId(cpa2.getRecordId());
                    cpa.setScore(getGraduationScore(cpa));
                    session=HibernateUtil.getSession();
                    tx=session.beginTransaction();
                    session.update(cpa);
                    tx.commit();
                    session.close();
                }
            }
            else
            {
                System.err.println("cpa is null in updateCareplanAchievementChecklist");
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void saveCareplanAchievementChecklist(CareplanAchievementChecklist cpa) throws Exception
    {
        try
        {
            if(cpa !=null)
            {
                cpa.setScore(getGraduationScore(cpa));
                session=HibernateUtil.getSession();
                tx=session.beginTransaction();
                session.save(cpa);
                tx.commit();
                session.close();
                if(cpa.getHhGraduated() ==AppConstant.YES_OPTION_NUM && cpa.getScore()>=AppConstant.GRADUATION_ASSESSMENT_SCORE)
                withdrawHousehold(cpa.getHhUniqueId(),AppConstant.GRADUATED_NUM,cpa.getDateOfAssessment());
            }
            else
            {
                System.err.println("cpa is null in saveCareplanAchievementChecklist");
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void updateCareplanAchievementChecklist(CareplanAchievementChecklist cpa) throws Exception
    {
        try
        {
            if(cpa !=null)
            {
                CareplanAchievementChecklist cpa2=this.getCareplanAchievementChecklist(cpa.getHhUniqueId(), cpa.getDateOfAssessment());
                if(cpa2 !=null)
                {
                    cpa.setRecordId(cpa2.getRecordId());
                    cpa.setScore(getGraduationScore(cpa));
                    session=HibernateUtil.getSession();
                    tx=session.beginTransaction();
                    session.update(cpa);
                    tx.commit();
                    session.close();
                    if(cpa.getHhGraduated() ==AppConstant.YES_OPTION_NUM && cpa.getScore()>=AppConstant.GRADUATION_ASSESSMENT_SCORE)
                    withdrawHousehold(cpa.getHhUniqueId(),AppConstant.GRADUATED_NUM,cpa.getDateOfAssessment());
                }
            }
            else
            {
                System.err.println("cpa is null in updateCareplanAchievementChecklist");
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void markForDelete(CareplanAchievementChecklist cpa) throws Exception
    {
        try
        {
            if(cpa !=null)
            {
                CareplanAchievementChecklist cpa2=this.getCareplanAchievementChecklist(cpa.getHhUniqueId(), cpa.getDateOfAssessment());
                if(cpa2 !=null)
                {
                    cpa.setRecordId(cpa2.getRecordId());
                    cpa.setScore(getGraduationScore(cpa));
                    cpa.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session=HibernateUtil.getSession();
                    tx=session.beginTransaction();
                    session.update(cpa);
                    tx.commit();
                    session.close();
                }
            }
            else
            {
                System.err.println("cpa is null in updateCareplanAchievementChecklist");
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void deleteCareplanAchievementChecklist(CareplanAchievementChecklist cpa) throws Exception
    {
        try
        {
            if(cpa !=null)
            {
                CareplanAchievementChecklist cpa2=this.getCareplanAchievementChecklist(cpa.getHhUniqueId(), cpa.getDateOfAssessment());
                if(cpa2 !=null)
                {
                    cpa.setRecordId(cpa2.getRecordId());
                    session=HibernateUtil.getSession();
                    tx=session.beginTransaction();
                    session.delete(cpa);
                    tx.commit();
                    session.close();
                }
            }
            else
            {
                System.err.println("cpa is null in deleteCareplanAchievementChecklist");
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
    }
    public void withdrawHousehold(String hhUniqueId,int reasonForWithdrawal,Date dateOfWithdrawal) throws Exception
    {
        BeneficiaryExitManager bem=new BeneficiaryExitManager();
        bem.withdrawHousehold(hhUniqueId, reasonForWithdrawal, dateOfWithdrawal);
    }
    private void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        session.close();
    }
    
}
