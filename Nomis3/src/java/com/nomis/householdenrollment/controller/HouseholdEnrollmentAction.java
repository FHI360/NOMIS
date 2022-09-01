/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.householdenrollment.controller;

import com.nomis.ajax.HtmlContentProvider;
import com.nomis.maintenance.DataCleanupManager;
import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.DatasetSetting;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.RevisedHouseholdVulnerabilityAssessment;
import com.nomis.ovc.business.SiteSetup;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.AdultHouseholdMemberDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.HouseholdEnrollmentDao;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ReferralFacilityManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DatasetManager;
import com.nomis.ovc.util.HivPropertiesManager;
import com.nomis.test.AgeDisaggregationTest;
import com.nomis.test.TestClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class HouseholdEnrollmentAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
        HouseholdEnrollmentForm hheform=(HouseholdEnrollmentForm)form;
        String moduleName="Household assessment";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        AdultHouseholdMemberDao ahmdao=util.getAdultHouseholdMemberDaoInstance();
        String requiredAction=hheform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        DatasetSetting dsts=util.getDatasetSettingDaoInstance().getDatasetSettingByModuleId(DatasetManager.getHhEnrolmentModuleId());
        if(dsts !=null && dsts.getDatasetId().equalsIgnoreCase("nathhvaform"))
        {
            moduleName="NationalHouseholdAssessment";
            return mapping.findForward(moduleName);
        }
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getBeneficiaryRecordsUpdatePriviledge().getPrivilegeId()))
        {
            setButtonState(session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
        }
        else
        {
            setButtonState(session,AppConstant.TRUEVALUE,AppConstant.TRUEVALUE);
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        
        //DatasetManager
        String userName=appManager.getCurrentUserName(session);
        String partnerCode=null;
        SiteSetup setup=ouaManager.getSiteSetup(userName);
        if(setup !=null)
        partnerCode=setup.getPartnerCode();
        String level2OuId=hheform.getLevel2OuId();
        String level3OuId=hheform.getLevel3OuId();
        String level4OuId=hheform.getOrganizationUnitId();
        String cboId=hheform.getCboId();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,cboId);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        if(requiredAction!=null && !requiredAction.equalsIgnoreCase("level3OuList") && !requiredAction.equalsIgnoreCase("level4OuList"))
        setButtonState(session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
        generateSchoolList(session,hheform);
        loadfacility(session,level2OuId,null);
        
        List dayList=DateManager.generateDays(1);
        List monthList=DateManager.generateSingleValueMonths();
        List yearList=DateManager.generateYears();
        session.setAttribute("hhpDayList", dayList);
        session.setAttribute("hhpMonthList", monthList);
        session.setAttribute("hhpYearList", yearList);
        loadCommunityWorkers(level4OuId,session);
        //setWithdrawalStatusMessage(session,hheform.getHhUniqueId(),AppConstant.TRUEVALUE,AppConstant.TRUEVALUE);
        if(requiredAction==null)
        {
            //AgeDisaggregationTest.testOvcAgeQuery();
            TestClass.testDatimReports();
            //set null hhUniqueid to the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            hheform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }//hhvaDetails
        else if(requiredAction.equalsIgnoreCase("hhvaDetails"))
        {
            int hhSerialNo=hheform.getSerialNo();
            String hhUniqueId=hheform.getHhUniqueId();
            if(hhUniqueId !=null)
            hhUniqueId=hhUniqueId.replace("_", "'");
            updateRevisedHouseholdAssessmentRecords();
            HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
            hheform.reset(mapping, request);
            if(hhe !=null)
            {
                OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(hhe.getOrganizationUnit());
                if(ou !=null)
                {
                    ouaManager.setOrganizationUnitAttributes(session, ou.getParentId(),userName,hhe.getCboId());
                    hheform.setLevel3OuId(ou.getParentId());
                }
                    
                hheform.setSerialNo(hhSerialNo);
                hheform.setOrganizationUnitId(hhe.getOrganizationUnit());
                hheform.setCboId(hhe.getCboId());
                hheform.setHhUniqueId(hhUniqueId);
                hheform.setExistingHhUniqueId(hhUniqueId);
                hheform.setAddress(hhe.getAddress());
                hheform.setOrganizationUnitId(hhe.getOrganizationUnit());
                hheform.setVolunteerName(hhe.getVolunteerName());
                hheform.setDateOfAssessment(DateManager.convertDateToString(hhe.getDateOfAssessment(),DateManager.MM_DD_YYYY_SLASH));
                    
                RevisedHouseholdVulnerabilityAssessment rhva=util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().getHouseholdVulnerabilityAssessment(hhUniqueId, hhe.getDateOfAssessment());
                if(rhva !=null)
                {
                    hheform.setCgEngagedInEconomicActivities(rhva.getCgEngagedInEconomicActivities());
                    hheform.setChildUndernourished(rhva.getChildUndernourished());
                    hheform.setChildrenEnrolledInSchool(rhva.getChildrenEnrolledInSchool());
                    hheform.setChildrenHasBirthCertificate(rhva.getChildrenHasBirthCertificate());
                    hheform.setHasViralLoadResult(rhva.getHasViralLoadResult());
                    hheform.setHivPositiveLinked(rhva.getHivPositiveLinked());
                    hheform.setHivPreventionKnowledge(rhva.getHivPreventionKnowledge());
                    hheform.setHivStatusKnown(rhva.getHivStatusKnown());
                    hheform.setRegularSchoolAttendance(rhva.getRegularSchoolAttendance());
                    hheform.setSocialEmotionalSupport(rhva.getSocialEmotionalSupport());
                    hheform.setStableAdult(rhva.getStableAdult());
                    hheform.setViolenceExperienceReported(rhva.getViolenceExperienceReported());
                }
                else
                {
                    hheform.setCgEngagedInEconomicActivities(hhe.getCgEngagedInEconomicActivities());
                    hheform.setChildUndernourished(hhe.getChildUndernourished());
                    hheform.setChildrenEnrolledInSchool(hhe.getChildrenEnrolledInSchool());
                    hheform.setChildrenHasBirthCertificate(hhe.getChildrenHasBirthCertificate());
                    hheform.setHasViralLoadResult(hhe.getHasViralLoadResult());
                    hheform.setHivPositiveLinked(hhe.getHivPositiveLinked());
                    hheform.setHivPreventionKnowledge(hhe.getHivPreventionKnowledge());
                    hheform.setHivStatusKnown(hhe.getHivStatusKnown());
                    hheform.setRegularSchoolAttendance(hhe.getRegularSchoolAttendance());
                    hheform.setSocialEmotionalSupport(hhe.getSocialEmotionalSupport());
                    hheform.setStableAdult(hhe.getStableAdult());
                    hheform.setViolenceExperienceReported(hhe.getViolenceExperienceReported());
                }
                AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getHeadOfHousehold(hhUniqueId);
                if(ahm !=null)
                {
                    System.err.println("ahm.getHhUniqueId() is "+ahm.getHhUniqueId()+" Baseline Hiv status is "+ahm.getBaselineHivStatus()+" Enrolled on treatment is "+ahm.getEnrolledOnTreatment());               
                    hhe.setPrCaregiver(ahm); 
                    hheform.setCgAge(ahm.getAgeAtBaseline());
                    hheform.setFirstName(ahm.getFirstName());
                    hheform.setCgPhoneNumber(ahm.getPhoneNumber());
                    hheform.setCgSex(ahm.getSex());
                    hheform.setMaritalStatus(ahm.getMaritalStatus());
                    hheform.setSurname(ahm.getSurname());
                    hheform.setBaselineHivStatus(ahm.getBaselineHivStatus());
                    if(ahm.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                    setHIVStatusProperties(session,AppConstant.FALSEVALUE);
                    else
                    setHIVStatusProperties(session,AppConstant.TRUEVALUE);
                    //hheform.setDateOfBaselineHivStatus(DateManager.convertDateToString(ahm.getDateOfBaselineHivStatus(), DateManager.MM_DD_YYYY_SLASH));
                    hheform.setDateOfBaselineHivStatus(DateManager.getMthDayYearStringDateFormat(ahm.getDateOfBaselineHivStatus(), 0));
                    hheform.setEnrolledOnTreatment(ahm.getEnrolledOnTreatment());
                    //hheform.setDateEnrolledOnTreatment(DateManager.convertDateToString(ahm.getDateEnrolledOnTreatment(),DateManager.MM_DD_YYYY_SLASH));
                    hheform.setDateEnrolledOnTreatment(DateManager.getMthDayYearStringDateFormat(ahm.getDateEnrolledOnTreatment(),0));
                    hheform.setHivTreatmentFacilityId(ahm.getHivTreatmentFacilityId());
                    hheform.setTreatmentId(ahm.getTreatmentId());
                    //System.err.println("hheform.getCboId() is "+hheform.getCboId());               
                    setButtonState(session,AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
                }
                setWithdrawalStatusMessage(session,hheform.getHhUniqueId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE); 
            }
            else
            {
                setWithdrawalStatusMessage(session,hheform.getHhUniqueId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
                hheform.setSerialNo(hhSerialNo);
                hheform.setHhUniqueId(hhUniqueId);
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, hheform.getCboId());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            ouaManager.setCBOListByAssignedLevel3Ou(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            HouseholdEnrollment hhe=getHouseholdEnrollment(hheform,userName,partnerCode);
            hhedao.saveHouseholdEnrollment(hhe);
            
            saveUserActivity(userName,moduleName,"Saved new Household enrollment record with Id "+hhe.getHhUniqueId());
            //Save primary and secondary caregiver information
            AdultHouseholdMember primaryCaregiver=this.getPrimaryCaregiver(hheform, userName);
            ahmdao.saveAdultHouseholdMember(primaryCaregiver);
            
            RevisedHouseholdVulnerabilityAssessment rhva= getHouseholdAssessment(hheform,userName);
            util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().saveOrUpdateRevisedHouseholdVulnerabilityAssessment(rhva);
            System.err.println("primaryCaregiver.getHhUniqueId() in save is "+primaryCaregiver.getHhUniqueId()+" and primaryCaregiver.getBeneficiaryId() is "+primaryCaregiver.getBeneficiaryId()+" and primaryCaregiver.getDateOfEnrollment() is "+primaryCaregiver.getDateOfEnrollment());
            hheform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            String existingHhUniqueId=hheform.getExistingHhUniqueId();
            HouseholdEnrollment hhe=getHouseholdEnrollment(hheform,userName,partnerCode);
            if(existingHhUniqueId !=null && existingHhUniqueId.trim().length()>0)
            hhe.setHhUniqueId(existingHhUniqueId);
            hhedao.updateHouseholdEnrollment(hhe);
            
            AdultHouseholdMember primaryCaregiver=getPrimaryCaregiver(hheform, userName);
            AdultHouseholdMember householdHead=util.getAdultHouseholdMemberDaoInstance().getHeadOfHousehold(hhe.getHhUniqueId());
            if(householdHead !=null)
            {
                //System.err.println("householdHead in HouseholdEnrollmentAction is not null");
                primaryCaregiver.setHhUniqueId(hhe.getHhUniqueId());
                primaryCaregiver.setBeneficiaryId(householdHead.getBeneficiaryId());
                primaryCaregiver.setEnrollmentId(householdHead.getEnrollmentId());
                primaryCaregiver.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
                //primaryCaregiver.set
                ahmdao.updateAdultHouseholdMember(primaryCaregiver);
            }
            RevisedHouseholdVulnerabilityAssessment rhva= getHouseholdAssessment(hheform,userName);
            util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().saveOrUpdateRevisedHouseholdVulnerabilityAssessment(rhva);
            saveUserActivity(userName,moduleName,"Modified Household enrollment record with Id "+hhe.getHhUniqueId());
            hheform.reset(mapping, request);
            //System.err.println("primaryCaregiver.getEducationLevel() in modify is "+primaryCaregiver.getEducationLevel());
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            String existingHhUniqueId=hheform.getExistingHhUniqueId();
            HouseholdEnrollment hhe=getHouseholdEnrollment(hheform,userName,partnerCode);
            hhe.setHhUniqueId(existingHhUniqueId);
            hhedao.markedForDelete(hhe);
            RevisedHouseholdVulnerabilityAssessment rhva= getHouseholdAssessment(hheform,userName);
            //util.getHouseholdVulnerabilityAssessmentDaoInstance().deleteHouseholdVulnerabilityAssessment(rhva);
            saveUserActivity(userName,moduleName,"Requested Household enrollment record with Id "+hhe.getHhUniqueId()+" be deleted");
            hheform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void setHIVStatusProperties(HttpSession session,String disabled)
    {
        session.setAttribute("hhHivDisabled", disabled);
    }
    private void loadCommunityWorkers(String level4OuId,HttpSession session) throws Exception
    {
        CommunityWorkerRecordsManager cwrm=new CommunityWorkerRecordsManager();
        List communityWorkerList=cwrm.getActiveCommunityWorkerList();
        if(communityWorkerList==null)
        communityWorkerList=new ArrayList();
        session.setAttribute("communityWorkerList", communityWorkerList);
    }
    private HouseholdEnrollment getHouseholdEnrollment(HouseholdEnrollmentForm hhform,String userName,String partnerCode)
    {
        String uniqueId=hhform.getHhUniqueId();
                  
        HouseholdEnrollment hhe=new HouseholdEnrollment();
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        Date dateOfAssessment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfAssessment()));
        
        hhe.setHhUniqueId(uniqueId);
        hhe.setEnrollmentId(uniqueId);
        hhe.setAddress(hhform.getAddress());
        hhe.setOrganizationUnit(hhform.getOrganizationUnitId());
        hhe.setCboId(hhform.getCboId());
        //System.err.println("hhe.getCboId() is "+hhe.getCboId());
        hhe.setCurrentEnrollmentStatus(AppConstant.ACTIVE_NUM);
        hhe.setPartnerCode(partnerCode);
        hhe.setDateCreated(currentDate);
        hhe.setLastModifiedDate(currentDate);
        hhe.setDateOfCurrentStatus(dateOfAssessment);
        hhe.setDateOfAssessment(dateOfAssessment);
        hhe.setRecordedBy(userName);
        
        hhe.setCgEngagedInEconomicActivities(hhform.getCgEngagedInEconomicActivities());
        hhe.setChildUndernourished(hhform.getChildUndernourished());
        hhe.setChildrenEnrolledInSchool(hhform.getChildrenEnrolledInSchool());
        hhe.setChildrenHasBirthCertificate(hhform.getChildrenHasBirthCertificate());
        hhe.setHasViralLoadResult(hhform.getHasViralLoadResult());
        hhe.setHivPositiveLinked(hhform.getHivPositiveLinked());
        hhe.setHivPreventionKnowledge(hhform.getHivPreventionKnowledge());
        hhe.setHivStatusKnown(hhform.getHivStatusKnown());
        hhe.setOrganizationUnit(hhform.getOrganizationUnitId());
        hhe.setRecordedBy(userName);
        hhe.setRegularSchoolAttendance(hhform.getRegularSchoolAttendance());
        hhe.setSocialEmotionalSupport(hhform.getSocialEmotionalSupport());
        hhe.setStableAdult(hhform.getStableAdult());
        hhe.setViolenceExperienceReported(hhform.getViolenceExperienceReported());
        hhe.setVolunteerName(hhform.getVolunteerName());
        //System.err.println("hhe.getPrCaregiver().getFirstName() is "+hhe.getPrCaregiver().getFirstName());
        
      return hhe;          
    }
    private RevisedHouseholdVulnerabilityAssessment getHouseholdAssessment(HouseholdEnrollmentForm hhform,String userName)
    {
        
        RevisedHouseholdVulnerabilityAssessment hhe=new RevisedHouseholdVulnerabilityAssessment();
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        Date dateOfAssessment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfAssessment()));
        hhe.setHhUniqueId(hhform.getHhUniqueId());
        
        hhe.setDateCreated(currentDate);
        hhe.setLastModifiedDate(currentDate);
        hhe.setDateOfAssessment(dateOfAssessment);
        hhe.setRecordedBy(userName);
        
        hhe.setCgEngagedInEconomicActivities(hhform.getCgEngagedInEconomicActivities());
        hhe.setChildUndernourished(hhform.getChildUndernourished());
        hhe.setChildrenEnrolledInSchool(hhform.getChildrenEnrolledInSchool());
        hhe.setChildrenHasBirthCertificate(hhform.getChildrenHasBirthCertificate());
        hhe.setHasViralLoadResult(hhform.getHasViralLoadResult());
        hhe.setHivPositiveLinked(hhform.getHivPositiveLinked());
        hhe.setHivPreventionKnowledge(hhform.getHivPreventionKnowledge());
        hhe.setHivStatusKnown(hhform.getHivStatusKnown());
        hhe.setRecordedBy(userName);
        hhe.setRegularSchoolAttendance(hhform.getRegularSchoolAttendance());
        hhe.setSocialEmotionalSupport(hhform.getSocialEmotionalSupport());
        hhe.setStableAdult(hhform.getStableAdult());
        hhe.setViolenceExperienceReported(hhform.getViolenceExperienceReported());
        hhe.setVolunteerName(hhform.getVolunteerName());
        //System.err.println("hhe.getPrCaregiver().getFirstName() is "+hhe.getPrCaregiver().getFirstName());
        
      return hhe;          
    }
    private AdultHouseholdMember getPrimaryCaregiver(HouseholdEnrollmentForm hhform,String userName)
    {
        //UniqueIdManager uig=new UniqueIdManager();
        AdultHouseholdMember ahm=new AdultHouseholdMember();
        String uniqueId=null;//uig.generateHouseholdUniqueId(hhform.getPrCgiverFirstName(), hhform.getPrCgiverSurname(), hhform.getLevel3OuId(), hhform.getPrCgiverDateOfBirth(), hhform.getPrCgiverSex());
        Date dateOfBaselineHivStatus=DateManager.getDefaultStartDateInstance();
        String formDateOfHivStatus=hhform.getDateOfBaselineHivStatus();
        if(formDateOfHivStatus !=null && formDateOfHivStatus.trim().length()>0)
        dateOfBaselineHivStatus=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfBaselineHivStatus()));
        ahm.setHhUniqueId(hhform.getHhUniqueId());
        ahm.setEnrollmentId(uniqueId);
        ahm.setFirstName(hhform.getFirstName());
        ahm.setSurname(hhform.getSurname());
        ahm.setSex(hhform.getCgSex());
        ahm.setPhoneNumber(hhform.getCgPhoneNumber());
        ahm.setAgeAtBaseline(hhform.getCgAge());
        ahm.setSex(hhform.getCgSex());
        ahm.setMaritalStatus(hhform.getMaritalStatus());
        ahm.setPhoneNumber(hhform.getCgPhoneNumber());
        ahm.setBaselineHivStatus(hhform.getBaselineHivStatus());
        ahm.setCurrentAge(ahm.getAgeAtBaseline());
        ahm.setCurrentHivStatus(ahm.getBaselineHivStatus());
        ahm.setDateEnrolledOnTreatment(DateManager.getDateInstance(DateManager.DEFAULT_DATE));
        ahm.setDateOfBaselineHivStatus(dateOfBaselineHivStatus);
        ahm.setDateOfCurrentHivStatus(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfBaselineHivStatus())));
        ahm.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_NO_NUM);
        ahm.setTreatmentId(null);
        ahm.setHivTreatmentFacilityId(null);
        //ahm.setDateEnrolledOnTreatment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateEnrolledOnTreatment())));
        if(ahm.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
        {
            System.err.println("hhform.getEnrolledOnTreatment() is "+hhform.getEnrolledOnTreatment());
            if(hhform.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            {
                if(hhform.getDateEnrolledOnTreatment() !=null)
                ahm.setDateEnrolledOnTreatment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateEnrolledOnTreatment())));
                ahm.setEnrolledOnTreatment(hhform.getEnrolledOnTreatment());
                ahm.setTreatmentId(hhform.getTreatmentId());
                ahm.setHivTreatmentFacilityId(hhform.getHivTreatmentFacilityId());
            }
            else
            ahm.setHivTreatmentFacilityId(null);
        }
        
        ahm.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        ahm.setDateOfEnrollment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfAssessment())));
        ahm.setDateOfCurrentEnrollmentStatus(ahm.getDateOfEnrollment());
        ahm.setIsCaregiver(1);
        ahm.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        ahm.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        ahm.setRecordedBy(userName);
        System.err.println("ahm.getFirstName() in getPrimaryCaregiver is "+ahm.getFirstName());
        
      return ahm;          
    }
    private void generateSchoolList(HttpSession session,HouseholdEnrollmentForm hheform)
    {
        try
        {
            List list=new ArrayList();
            DaoUtility daoutil=new DaoUtility();
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setLevel2OuId(hheform.getLevel2OuId());
            rpt.setLevel3OuId(hheform.getLevel3OuId());
            //if(rpt.getLevel3OuId() !=null &&! rpt.getLevel3OuId().trim().equalsIgnoreCase("select"))
            list=null;//daoutil.getSchoolDaoInstance().getSchoolsByOrgUnit(rpt);
            if(list==null)
            list=new ArrayList();
            session.setAttribute("schoolListByLevel2Ou", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void loadfacility(HttpSession session,String level2OuId,String level3OuId)
    {
        try
        {
            ReferralFacilityManager rfm=new ReferralFacilityManager();
            List facilityList=rfm.loadfacility(level2OuId, level3OuId);
            session.setAttribute("ovcfacilityList", facilityList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void updateRevisedHouseholdAssessmentRecords() throws Exception
    {
        DaoUtility util=new DaoUtility();
        int numberOfAssessments=util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().getUniqueCountOfHouseholdVulnerabilityAssessment();
        if(numberOfAssessments==0)
        {
            List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
            if(level4OuList !=null && !level4OuList.isEmpty())
            {
                String level4Ou=null;
                List hhEnrollmentList=null;
                RevisedHouseholdVulnerabilityAssessment rhva=null;
                for(Object obj:level4OuList)
                {
                    level4Ou=(String)obj;
                    hhEnrollmentList=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollmentByOrgUnit(level4Ou);
                    if(hhEnrollmentList !=null)
                    {
                        for(Object hheObj:hhEnrollmentList)
                        {
                            HouseholdEnrollment hhe=(HouseholdEnrollment)hheObj;
                            rhva=new RevisedHouseholdVulnerabilityAssessment();
                            rhva.setCgEngagedInEconomicActivities(hhe.getCgEngagedInEconomicActivities());
                            rhva.setChildUndernourished(hhe.getChildUndernourished());
                            rhva.setChildrenEnrolledInSchool(hhe.getChildrenEnrolledInSchool());
                            rhva.setChildrenHasBirthCertificate(hhe.getChildrenHasBirthCertificate());
                            rhva.setDateCreated(hhe.getDateCreated());
                            rhva.setDateOfAssessment(hhe.getDateOfAssessment());
                            rhva.setHasViralLoadResult(hhe.getHasViralLoadResult());
                            rhva.setHhUniqueId(hhe.getHhUniqueId());
                            rhva.setHivPositiveLinked(hhe.getHivPositiveLinked());
                            rhva.setHivPreventionKnowledge(hhe.getHivPreventionKnowledge());
                            rhva.setHivStatusKnown(hhe.getHivStatusKnown());
                            rhva.setLastModifiedDate(hhe.getLastModifiedDate());
                            rhva.setRecordedBy(hhe.getRecordedBy());
                            rhva.setRegularSchoolAttendance(hhe.getRegularSchoolAttendance());
                            rhva.setSocialEmotionalSupport(hhe.getSocialEmotionalSupport());
                            rhva.setStableAdult(hhe.getStableAdult());
                            rhva.setViolenceExperienceReported(hhe.getViolenceExperienceReported());
                            rhva.setVolunteerName(hhe.getVolunteerName());
                            if(util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().getTotalAssessmentScore(rhva)>0)
                            util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().saveOrUpdateRevisedHouseholdVulnerabilityAssessment(rhva);
                        }
                    }
                }
            }
        }
        
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="hhWithdrawnMessage";
        if(beneficiaryId !=null)
        {
            DaoUtility util=new DaoUtility();
            HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(beneficiaryId);
            if(hhe !=null)
            {
                if(appUtil.getBeneficiaryWithrawnMessage(hhe.getCurrentEnrollmentStatus()) !=null)
                {
                    setButtonState(session,AppConstant.TRUEVALUE,AppConstant.TRUEVALUE);
                    session.setAttribute(attributeName, appUtil.getBeneficiaryWithrawnMessage(hhe.getCurrentEnrollmentStatus()));
                }
                else
                {
                    session.removeAttribute(attributeName);
                    setButtonState(session,saveBtnDisabledValue,modifyBtnDisabledValue);
                }
            }
            else
            {
                session.removeAttribute(attributeName);
                setButtonState(session,saveBtnDisabledValue,modifyBtnDisabledValue);
            }
        }
        else
        {
            session.removeAttribute(attributeName);
            setButtonState(session,saveBtnDisabledValue,modifyBtnDisabledValue);
        }
    }
    private boolean isNull(String str)
    {
        if(!AppUtility.isNull(str))
        return false;
        return true;
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("hheSaveDisabled", saveDisabled);
        session.setAttribute("hheModifyDisabled", modifyDisabled);
    }
}
