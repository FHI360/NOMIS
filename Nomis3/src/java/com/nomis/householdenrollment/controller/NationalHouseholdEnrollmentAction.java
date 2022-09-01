/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.householdenrollment.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.DatasetSetting;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.HouseholdVulnerabilityAssessment;
import com.nomis.ovc.business.SiteSetup;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.AdultHouseholdMemberDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.HouseholdEnrollmentDao;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatasetManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.HivPropertiesManager;
import com.nomis.ovc.util.ReferralFacilityManager;
import com.nomis.reports.utils.ReportParameterTemplate;
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
public class NationalHouseholdEnrollmentAction extends org.apache.struts.action.Action {

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
        NationalHouseholdEnrollmentForm hheform=(NationalHouseholdEnrollmentForm)form;
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
        if(dsts !=null && dsts.getDatasetId().equalsIgnoreCase("revhhvaform"))
        {
            moduleName="RevisedHouseholdAssessment";
            return mapping.findForward(moduleName);
        }
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getBeneficiaryRecordsUpdatePriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        String userName=appManager.getCurrentUserName(session);
        String partnerCode=null;
        SiteSetup setup=ouaManager.getSiteSetup(userName);
        if(setup !=null)
        partnerCode=setup.getPartnerCode();
        String level2OuId=hheform.getLevel2OuId();
        String level3OuId=hheform.getLevel3OuId();
        String level4OuId=hheform.getOrganizationUnitId();
        
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,hheform.getCboId());
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        if(requiredAction!=null && !requiredAction.equalsIgnoreCase("level3OuList") && !requiredAction.equalsIgnoreCase("level4OuList"))
        setButtonState(session,"false","true");
        generateSchoolList(session,hheform);
        loadfacility(session,level2OuId,level3OuId);
        
        List dayList=DateManager.generateDays(1);
        List monthList=DateManager.generateSingleValueMonths();
        List yearList=DateManager.generateYears();
        session.setAttribute("hhpDayList", dayList);
        session.setAttribute("hhpMonthList", monthList);
        session.setAttribute("hhpYearList", yearList);
        loadCommunityWorkers(level4OuId,request);
        
        if(requiredAction==null)
        {
            //updateHouseholdEnrollmentWithLegacyRecords(userName);
            hheform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("hhvaDetails"))
        {
            int hhSerialNo=hheform.getSerialNo();
            String hhUniqueId=hheform.getHhUniqueId();
            if(hhUniqueId !=null)
            hhUniqueId=hhUniqueId.replace("_", "'");
            //updateRevisedHouseholdAssessmentRecords();
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
                    
                
                AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getHeadOfHousehold(hhUniqueId);
                if(ahm !=null)
                {
                    System.err.println("ahm.getHhUniqueId() is "+ahm.getHhUniqueId()+" Baseline Hiv status is "+ahm.getBaselineHivStatus()+" Enrolled on treatment is "+ahm.getEnrolledOnTreatment());               
                    hhe.setPrCaregiver(ahm); 
                    hheform.setCgAge(ahm.getAgeAtBaseline());
                    hheform.setCgFirstName(ahm.getFirstName());
                    hheform.setCgPhoneNumber(ahm.getPhoneNumber());
                    hheform.setCgSex(ahm.getSex());
                    //hheform.setMaritalStatus(ahm.getMaritalStatus());
                    hheform.setCgSurname(ahm.getSurname());
                    hheform.setBaselineHivStatus(ahm.getBaselineHivStatus());
                    //hheform.setDateOfBaselineHivStatus(DateManager.convertDateToString(ahm.getDateOfBaselineHivStatus(), DateManager.MM_DD_YYYY_SLASH));
                    hheform.setDateOfBaselineHivStatus(DateManager.getMthDayYearStringDateFormat(ahm.getDateOfBaselineHivStatus(), 0));
                    hheform.setEnrolledOnTreatment(ahm.getEnrolledOnTreatment());
                    //hheform.setDateEnrolledOnTreatment(DateManager.convertDateToString(ahm.getDateEnrolledOnTreatment(),DateManager.MM_DD_YYYY_SLASH));
                    hheform.setDateEnrolledOnTreatment(DateManager.getMthDayYearStringDateFormat(ahm.getDateEnrolledOnTreatment(),0));
                    hheform.setHivTreatmentFacilityId(ahm.getHivTreatmentFacilityId());
                    hheform.setTreatmentId(ahm.getTreatmentId());
                    System.err.println("hheform.getCboId() is "+hheform.getCboId());               
                    setButtonState(session,"true","false");
                }
                System.err.println("hheform.getHhUniqueId() is "+hheform.getHhUniqueId()+" and hheform.getDateOfAssessment() is "+hheform.getDateOfAssessment());
                    HouseholdVulnerabilityAssessment hva=util.getHouseholdVulnerabilityAssessmentDaoInstance().getHouseholdVulnerabilityAssessment(hhe.getHhUniqueId(), hhe.getDateOfAssessment());
                    if(hva !=null)
                    {
                        System.err.println("hva is not null");
                        hheform.setFoodSecurityAndNutrition(hva.getFoodSecurityAndNutrition());
                        hheform.setEducationLevel(hva.getEducationLevel());
                        hheform.setHealth(hva.getHealth());
                        hheform.setHhHeadship(hva.getHhHeadship());
                        hheform.setHhIncome(hva.getHhIncome());
                        hheform.setMeansOfLivelihood(hva.getMeansOfLivelihood());
                        hheform.setShelterAndHousing(hva.getShelterAndHousing());
                    }
            }
            else
            {
                hheform.setSerialNo(hhSerialNo);
                hheform.setHhUniqueId(hhUniqueId);
            }
            return mapping.findForward(SUCCESS);
        }//hhvaDetails
        /*else if(requiredAction.equalsIgnoreCase("hhvaDetails"))
        {
            int hhSerialNo=hheform.getSerialNo();
            String hhUniqueId=hheform.getHhUniqueId();
            if(hhUniqueId !=null)
            hhUniqueId=hhUniqueId.replace("_", "'");
            HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
            hheform.reset(mapping, request);
            if(hhe !=null)
            {
                AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getHeadOfHousehold(hhUniqueId);
                if(ahm !=null)
                {
                    OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(hhe.getOrganizationUnit());
                    if(ou !=null)
                    {
                        ouaManager.setOrganizationUnitAttributes(session, ou.getParentId(),userName,hhe.getCboId());
                        hheform.setLevel3OuId(ou.getParentId());
                    }
                    
                    System.err.println("hhe.getCboId() is "+hhe.getCboId());
                    hhe.setPrCaregiver(ahm); 
                    hheform.setSerialNo(hhSerialNo);
                    hheform.setOrganizationUnitId(hhe.getOrganizationUnit());
                    hheform.setCboId(hhe.getCboId());
                    
                    hheform.setHhUniqueId(hhUniqueId);
                    
                    //hheform.set
                    hheform.setExistingHhUniqueId(hhUniqueId);
                    hheform.setAddress(hhe.getAddress());
                    
                    hheform.setVolunteerName(hhe.getVolunteerName());
                    hheform.setCgAge(ahm.getAgeAtBaseline());
                    hheform.setCgFirstName(ahm.getFirstName());
                    hheform.setCgPhoneNumber(ahm.getPhoneNumber());
                    hheform.setCgSex(ahm.getSex());
                    hheform.setCgSurname(ahm.getSurname());
                    hheform.setBaselineHivStatus(ahm.getBaselineHivStatus());
                    hheform.setDateOfBaselineHivStatus(DateManager.convertDateToString(ahm.getDateOfBaselineHivStatus(), DateManager.MM_DD_YYYY_SLASH));
                    hheform.setEnrolledOnTreatment(ahm.getEnrolledOnTreatment());
                    hheform.setDateEnrolledOnTreatment(DateManager.convertDateToString(ahm.getDateEnrolledOnTreatment(),DateManager.MM_DD_YYYY_SLASH));
                    hheform.setHivTreatmentFacilityId(ahm.getHivTreatmentFacilityId());
                    hheform.setDateOfAssessment(DateManager.convertDateToString(hhe.getDateOfAssessment(),DateManager.MM_DD_YYYY_SLASH));
                    System.err.println("hheform.getCboId() is "+hheform.getCboId());
                                        
                    setButtonState(session,"true","false");
                }
            }
            else
            {
                hheform.setSerialNo(hhSerialNo);
                hheform.setHhUniqueId(hhUniqueId);
            }
            return mapping.findForward(SUCCESS);
        }*/
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
        else if(requiredAction.equalsIgnoreCase("hhvaDetails"))
        {
            int hhSerialNo=hheform.getSerialNo();
            String hhUniqueId=hheform.getHhUniqueId();
            //if(hhUniqueId !=null)
            //hhUniqueId=hhUniqueId.replace("_", "'");
            HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
            hheform.reset(mapping, request);
            if(hhe !=null)
            {
                AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getHeadOfHousehold(hhUniqueId);
                if(ahm !=null)
                {
                    OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(hhe.getOrganizationUnit());
                    if(ou !=null)
                    {
                        ouaManager.setOrganizationUnitAttributes(session, ou.getParentId(),userName,hhe.getCboId());
                        hheform.setLevel3OuId(ou.getParentId());
                    }
                    
                    System.err.println("hhe.getCboId() is "+hhe.getCboId());
                    hhe.setPrCaregiver(ahm); 
                    hheform.setSerialNo(hhSerialNo);
                    hheform.setOrganizationUnitId(hhe.getOrganizationUnit());
                    hheform.setCboId(hhe.getCboId());
                    hheform.setHhUniqueId(hhUniqueId);
                    hheform.setVolunteerName(hhe.getVolunteerName());
                    hheform.setCgAge(ahm.getAgeAtBaseline());
                    hheform.setCgFirstName(ahm.getFirstName());
                    hheform.setCgPhoneNumber(ahm.getPhoneNumber());
                    hheform.setCgSex(ahm.getSex());
                    hheform.setCgSurname(ahm.getSurname());
                    hheform.setBaselineHivStatus(ahm.getBaselineHivStatus());
                    hheform.setDateOfBaselineHivStatus(DateManager.convertDateToString(ahm.getDateOfBaselineHivStatus(), DateManager.MM_DD_YYYY_SLASH));
                    hheform.setEnrolledOnTreatment(ahm.getEnrolledOnTreatment());
                    hheform.setDateEnrolledOnTreatment(DateManager.convertDateToString(ahm.getDateEnrolledOnTreatment(),DateManager.MM_DD_YYYY_SLASH));
                    hheform.setHivTreatmentFacilityId(ahm.getHivTreatmentFacilityId());
                    hheform.setDateOfAssessment(DateManager.convertDateToString(hhe.getDateOfAssessment(),DateManager.MM_DD_YYYY_SLASH));
                    System.err.println("hheform.getHhUniqueId() is "+hheform.getHhUniqueId()+" and hheform.getDateOfAssessment() is "+hheform.getDateOfAssessment());
                    HouseholdVulnerabilityAssessment hva=util.getHouseholdVulnerabilityAssessmentDaoInstance().getHouseholdVulnerabilityAssessment(hhe.getHhUniqueId(), hhe.getDateOfAssessment());
                    if(hva !=null)
                    {
                        System.err.println("hva is not null");
                        hheform.setFoodSecurityAndNutrition(hva.getFoodSecurityAndNutrition());
                        hheform.setEducationLevel(hva.getEducationLevel());
                        hheform.setHealth(hva.getHealth());
                        hheform.setHhHeadship(hva.getHhHeadship());
                        hheform.setHhIncome(hva.getHhIncome());
                        hheform.setMeansOfLivelihood(hva.getMeansOfLivelihood());
                        hheform.setShelterAndHousing(hva.getShelterAndHousing());
                    }
                    //hheform.set
                    hheform.setExistingHhUniqueId(hhUniqueId);
                    hheform.setAddress(hhe.getAddress());
                    System.err.println("hheform.getCboId() is "+hheform.getCboId());
                                        
                    setButtonState(session,"true","false");
                }
            }
            else
            {
                hheform.setSerialNo(hhSerialNo);
                hheform.setHhUniqueId(hhUniqueId);
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            HouseholdEnrollment hhe=getHouseholdEnrollment(hheform,userName,partnerCode);
            hhedao.saveHouseholdEnrollment(hhe);
            HouseholdVulnerabilityAssessment hva= getHouseholdAssessment(hheform,userName);
            util.getHouseholdVulnerabilityAssessmentDaoInstance().saveOrUpdateHouseholdVulnerabilityAssessment(hva);
            saveUserActivity(userName,moduleName,"Saved new Household profile record with Id "+hhe.getHhUniqueId());
            //Save primary and secondary caregiver information
            AdultHouseholdMember primaryCaregiver=this.getPrimaryCaregiver(hheform, userName);
            ahmdao.saveAdultHouseholdMember(primaryCaregiver);
                       
            //System.err.println("primaryCaregiver.getHhUniqueId() in save is "+primaryCaregiver.getHhUniqueId()+" and primaryCaregiver.getBeneficiaryId() is "+primaryCaregiver.getBeneficiaryId()+" and primaryCaregiver.getDateOfEnrollment() is "+primaryCaregiver.getDateOfEnrollment());
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
            HouseholdVulnerabilityAssessment hva= getHouseholdAssessment(hheform,userName);
            util.getHouseholdVulnerabilityAssessmentDaoInstance().saveOrUpdateHouseholdVulnerabilityAssessment(hva);
            
            AdultHouseholdMember primaryCaregiver=getPrimaryCaregiver(hheform, userName);
            AdultHouseholdMember householdHead=util.getAdultHouseholdMemberDaoInstance().getHeadOfHousehold(hhe.getHhUniqueId());
            if(householdHead !=null)
            {
                primaryCaregiver.setHhUniqueId(hhe.getHhUniqueId());
                primaryCaregiver.setBeneficiaryId(householdHead.getBeneficiaryId());
                primaryCaregiver.setEnrollmentId(householdHead.getEnrollmentId());
                ahmdao.updateAdultHouseholdMember(primaryCaregiver);
            }
            saveUserActivity(userName,moduleName,"Modified Household profile record with Id "+hhe.getHhUniqueId());
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
            saveUserActivity(userName,moduleName,"Requested Household profile record with Id "+hhe.getHhUniqueId()+" be deleted");
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
    private void loadCommunityWorkers(String level4OuId,HttpServletRequest request) throws Exception
    {
        CommunityWorkerRecordsManager cwrm=new CommunityWorkerRecordsManager();
        List communityWorkerList=cwrm.getActiveCommunityWorkerList();
        if(communityWorkerList==null)
        communityWorkerList=new ArrayList();
        request.setAttribute("communityWorkerList", communityWorkerList);
    }//communityWorkerList
    private HouseholdEnrollment getHouseholdEnrollment(NationalHouseholdEnrollmentForm hhform,String userName,String partnerCode)
    {
        HouseholdEnrollment hhe=new HouseholdEnrollment();
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        Date dateOfAssessment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfAssessment()));
        hhe.setHhUniqueId(hhform.getHhUniqueId());
        hhe.setEnrollmentId(hhform.getHhUniqueId());
        hhe.setAddress(hhform.getAddress());
        hhe.setOrganizationUnit(hhform.getOrganizationUnitId());
        hhe.setCboId(hhform.getCboId());
        hhe.setCurrentEnrollmentStatus(AppConstant.ACTIVE_NUM);
        hhe.setPartnerCode(partnerCode);
        hhe.setDateCreated(currentDate);
        hhe.setLastModifiedDate(currentDate);
        hhe.setDateOfCurrentStatus(dateOfAssessment);
        hhe.setDateOfAssessment(dateOfAssessment);
        hhe.setRecordedBy(userName);
        hhe.setEnrollmentId(hhform.getHhUniqueId());
        hhe.setHhUniqueId(hhform.getHhUniqueId());
        hhe.setOrganizationUnit(hhform.getOrganizationUnitId());
        hhe.setRecordedBy(userName);
        hhe.setVolunteerName(hhform.getVolunteerName());
        //System.err.println("hhe.getPrCaregiver().getFirstName() is "+hhe.getPrCaregiver().getFirstName());
        
      return hhe;          
    }
    private HouseholdVulnerabilityAssessment getHouseholdAssessment(NationalHouseholdEnrollmentForm hhform,String userName)
    {
        
        HouseholdVulnerabilityAssessment hva=new HouseholdVulnerabilityAssessment();
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        Date dateOfAssessment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfAssessment()));
        hva.setHhUniqueId(hhform.getHhUniqueId());
        
        hva.setDateCreated(currentDate);
        hva.setLastModifiedDate(currentDate);
        hva.setDateOfAssessment(dateOfAssessment);
        hva.setRecordedBy(userName);
        hva.setNameOfAssessor(hhform.getVolunteerName());
        
        hva.setEducationLevel(hhform.getEducationLevel());
        hva.setFoodSecurityAndNutrition(hhform.getFoodSecurityAndNutrition());
        hva.setHealth(hhform.getHealth());
        hva.setHhHeadship(hhform.getHhHeadship());
        hva.setHhIncome(hhform.getHhIncome());
        hva.setMeansOfLivelihood(hhform.getMeansOfLivelihood());
        hva.setShelterAndHousing(hhform.getShelterAndHousing());
        
        //System.err.println("hhe.getPrCaregiver().getFirstName() is "+hhe.getPrCaregiver().getFirstName());
        
      return hva;          
    }
    private AdultHouseholdMember getPrimaryCaregiver(NationalHouseholdEnrollmentForm hhform,String userName)
    {
        //UniqueIdManager uig=new UniqueIdManager();
        AdultHouseholdMember ahm=new AdultHouseholdMember();
        String uniqueId=null;//uig.generateHouseholdUniqueId(hhform.getPrCgiverFirstName(), hhform.getPrCgiverSurname(), hhform.getLevel3OuId(), hhform.getPrCgiverDateOfBirth(), hhform.getPrCgiverSex());
        ahm.setHhUniqueId(hhform.getHhUniqueId());
        ahm.setEnrollmentId(uniqueId);
        ahm.setFirstName(hhform.getCgFirstName());
        ahm.setSurname(hhform.getCgSurname());
        ahm.setSex(hhform.getCgSex());
        ahm.setPhoneNumber(hhform.getCgPhoneNumber());
        ahm.setAgeAtBaseline(hhform.getCgAge());
        ahm.setSex(hhform.getCgSex());
        ahm.setPhoneNumber(hhform.getCgPhoneNumber());
        //ahm.setBaselineHivStatus(hhform.geth);
        ahm.setCurrentAge(ahm.getAgeAtBaseline());
        ahm.setCurrentHivStatus(ahm.getBaselineHivStatus());
        ahm.setDateEnrolledOnTreatment(DateManager.getDateInstance(DateManager.DEFAULT_DATE));
        ahm.setDateOfBaselineHivStatus(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfBaselineHivStatus())));
        ahm.setDateOfCurrentHivStatus(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateOfBaselineHivStatus())));
        ahm.setDateEnrolledOnTreatment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateEnrolledOnTreatment())));
        if(ahm.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
        {
            if(hhform.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            {
                if(hhform.getDateEnrolledOnTreatment() !=null)
                ahm.setDateEnrolledOnTreatment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhform.getDateEnrolledOnTreatment())));
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
    private void generateSchoolList(HttpSession session,NationalHouseholdEnrollmentForm hheform)
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
    /*private void loadfacility(HttpSession session,HouseholdEnrollmentForm hheform)
    {
        try
        {
            List facilityList=new ArrayList();
            DaoUtility util=new DaoUtility();
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setLevel2OuId(hheform.getLevel2OuId());
            rpt.setLevel3OuId(hheform.getLevel3OuId());
            //if(rpt.getLevel3OuId() !=null &&! rpt.getLevel3OuId().trim().equalsIgnoreCase("select"))
            facilityList=util.getReferralFacilityDaoInstance().getReferralFacilitiesByOrgUnit(rpt);
            if(facilityList ==null)
            facilityList=new ArrayList();
            session.setAttribute("ovcfacilityList", facilityList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }*/
    private void updateHouseholdEnrollmentWithLegacyRecords(String userName,String partnerCode)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getHouseholdEnrollmentDaoInstance().getAllHouseholdEnrollmentRecords();
            if(list !=null)
            {
                HouseholdEnrollment hhe=null;
                HouseholdEnrollment hhp=null;
                HouseholdEnrollment duphhp=null;
                Date currentDate=DateManager.getCurrentDateInstance();
                for(int i=0; i<list.size(); i++)
                {
                    hhe=(HouseholdEnrollment)list.get(i);
                    hhp=new HouseholdEnrollment();
                    hhp.setAddress(hhe.getAddress());
                    hhp.setCboId(hhe.getCboId());
                    hhp.setEnrollmentId(hhe.getHhUniqueId());
                    hhp.setDateOfCurrentStatus(hhe.getDateOfAssessment());//.getDateOfAssessment());
                    hhp.setHhUniqueId(hhe.getHhUniqueId());
                    hhp.setOrganizationUnit(hhe.getOrganizationUnit());
                    hhp.setDateCreated(currentDate);
                    hhp.setLastModifiedDate(currentDate);
                    hhp.setRecordedBy(userName);
                    hhp.setPartnerCode(partnerCode);
                    
                    duphhp=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhp.getHhUniqueId());
                    if(duphhp==null)
                    {
                        util.getHouseholdEnrollmentDaoInstance().saveHouseholdEnrollment(hhp);
                    }
                    else
                    {
                        util.getHouseholdEnrollmentDaoInstance().updateHouseholdEnrollment(hhp);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
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
