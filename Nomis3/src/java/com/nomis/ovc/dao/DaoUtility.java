/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class DaoUtility 
{
    Session session;
    Transaction tx;
    public List execSqlQuery(String sql)
    {
        List list=new ArrayList();
        session=HibernateUtil.getSession();
        tx=session.beginTransaction();
        list=session.createSQLQuery(sql).list();
        tx.commit();
        session.close();
        return list;
    }
    public List execReportQuery(String sql)
    {
        List list=new ArrayList();
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery(sql).list();
            tx.commit();
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            session.close();
        }
        return list;
    }
    public int executeUpdate(String query) throws Exception
    {
        int count=0;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            count=session.createQuery(query).executeUpdate();
            tx.commit();
            session.close();
            
        }
        catch(Exception ex)
        {
            session.close();
            throw new Exception(ex);
        }
        return count;
    }
    public int executeSQLupdate(String query) throws Exception
    {
        int response=0;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            tx.commit();
            session.close();
            response=1;
        }
        catch(Exception ex)
        {
            response=2;
            session.close();
            //ex.printStackTrace();
            throw new Exception(ex);
        }
        return response;
    }
    public OrganizationUnitDao getOrganizationUnitDaoInstance()
    {
        return new OrganizationUnitDaoImpl();
    }
    public OrganizationUnitHierarchyDao getOrganizationUnitHierarchyDaoInstance()
    {
        return new OrganizationUnitHierarchyDaoImpl();
    }
    public UserActivityTrackerDao getUserActivityTrackerDaoInstance()
    {
        return new UserActivityTrackerDaoImpl();
    }
    public UserDao getUserDaoInstance()
    {
        return new UserDaoImpl();
    }
    public CommunityBasedOrganizationDao getCommunityBasedOrganizationDaoInstance()
    {
        return new CommunityBasedOrganizationDaoImpl();
    }
    public ServiceDomainDao getServiceDomainDaoInstance()
    {
        return new ServiceDomainDaoImpl();
    }
    public PartnerDao getPartnerDaoInstance()
    {
        return new PartnerDaoImpl();
    }
    public SiteSetupDao getSiteSetupDaoInstance()
    {
        return new SiteSetupDaoImpl();
    }
    public ReportOrganizationUnitDao getReportOrganizationUnitDaoInstance()
    {
        return new ReportOrganizationUnitDaoImpl();
    }
    public HouseholdEnrollmentDao getHouseholdEnrollmentDaoInstance()
    {
        return new HouseholdEnrollmentDaoImpl();
    }
    public AdultHouseholdMemberDao getAdultHouseholdMemberDaoInstance()
    {
        return new AdultHouseholdMemberDaoImpl();
    }
    public SchoolDao getSchoolDaoInstance()
    {
        return new SchoolDaoImpl();
    }
    public SchoolGradeDao getSchoolGradeDaoInstance()
    {
        return new SchoolGradeDaoImpl();
    }
    public CommunityWorkerDao getCommunityWorkerDaoInstance()
    {
        return new CommunityWorkerDaoImpl();
    }
    public VulnerabilityStatusDao getVulnerabilityStatusDaoInstance()
    {
        return new VulnerabilityStatusDaoImpl();
    }
    public ChildEnrollmentDao getChildEnrollmentDaoInstance()
    {
        return new ChildEnrollmentDaoImpl();
    }
    public ChildServiceDao getChildServiceDaoInstance()
    {
        return new ChildServiceDaoImpl();
    }
    public HouseholdServiceDao getHouseholdServiceDaoInstance()
    {
        return new HouseholdServiceDaoImpl();
    }
    public ProcessMonitorDao getProcessMonitorDaoInstance()
    {
        return new ProcessMonitorDaoImpl();
    }
    public HivStatusManagerDao getHivStatusManagerDaoInstance()
    {
        return new HivStatusManagerDaoImpl();
    }
    public HivRiskAssessmentDao getHivRiskAssessmentDaoInstance()
    {
        return new HivRiskAssessmentDaoImpl();
    }
    public CareplanAchievementChecklistDao getCareplanAchievementChecklistDaoInstance()
    {
        return new CareplanAchievementChecklistDaoImpl();
    }
    public HouseholdReferralDao getHouseholdReferralDaoInstance()
    {
        return new HouseholdReferralDaoImpl();
    }
    public NutritionAssessmentDao getNutritionalAssessmentDaoInstance()
    {
        return new NutritionAssessmentDaoImpl();
    }
    public BeneficiaryServiceDao getBeneficiaryServiceDaoInstance()
    {
        return new BeneficiaryServiceDaoImpl();
    }
    public ReferralFacilityDao getReferralFacilityDaoInstance()
    {
        return new ReferralFacilityDaoImpl();
    }
    public BeneficiaryStatusUpdateDao getBeneficiaryStatusUpdateDaoInstance()
    {
        return new BeneficiaryStatusUpdateDaoImpl();
    }
    public ChildEducationPerformanceAssessmentDao getChildEducationPerformanceAssessmentDaoInstance()
    {
        return new ChildEducationPerformanceAssessmentDaoImpl();
    }
    public CareAndSupportChecklistDao getCareAndSupportChecklistDaoInstance()
    {
        return new CareAndSupportChecklistDaoImpl();
    }
    public CaregiverAccessToEmergencyFundDao getCaregiverAccessToEmergencyFundDaoInstance()
    {
        return new CaregiverAccessToEmergencyFundDaoImpl();
    }
    public HouseholdCareplanDao getHouseholdCareplanDaoInstance()
    {
        return new HouseholdCareplanDaoImpl();
    }
    public HouseholdVulnerabilityAssessmentDao getHouseholdVulnerabilityAssessmentDaoInstance()
    {
        return new HouseholdVulnerabilityAssessmentDaoImpl();
    }
    public RevisedHouseholdVulnerabilityAssessmentDao getRevisedHouseholdVulnerabilityAssessmentDaoInstance()
    {
        return new RevisedHouseholdVulnerabilityAssessmentDaoImpl();
    }
    public DatasetSettingDao getDatasetSettingDaoInstance()
    {
        return new DatasetSettingDaoImpl();
    }
    public DataImportUploadManagerDao getDataImportUploadManagerDaoInstance()
    {
        return new DataImportUploadManagerDaoImpl();
    }
    public HivStatusHistoryDao getHivStatusHistoryDaoInstance()
    {
        return new HivStatusHistoryDaoImpl();
    }
    public EnrollmentStatusHistoryDao getEnrollmentStatusHistoryDaoInstance()
    {
        return new EnrollmentStatusHistoryDaoImpl();
    }
    public QuarterlyStatusTrackerDao getQuarterlyStatusTrackerDaoInstance()
    {
        return new QuarterlyStatusTrackerDaoImpl();
    }
    public ChildStatusDao getChildStatusDaoInstance()
    {
        return new ChildStatusDaoImpl();
    }
    public DatimReportDao getDatimReportDaoInstance()
    {
        return new DatimReportDaoImpl();
    }
    public NutritionStatusDao getNutritionStatusDaoInstance()
    {
        return new NutritionStatusDaoImpl();
    }
    public OccupationDao getOccupationDaoInstance()
    {
        return new OccupationDaoImpl();
    }
    public UserRoleDao getUserRoleDaoInstance()
    {
        return new UserRoleDaoImpl();
    }
    public UserPrivilegesDao getUserPrivilegesDaoInstance()
    {
        return new UserPrivilegesDaoImpl();
    }
    public MonthlySummaryFormReportDao getMonthlySummaryFormReportDaoInstance()
    {
        return new MonthlySummaryFormReportDaoImpl();
    }
    public FacilityOvcOfferDao getFacilityOvcOfferDaoInstance()
    {
        return new FacilityOvcOfferDaoImpl();
    }
}
