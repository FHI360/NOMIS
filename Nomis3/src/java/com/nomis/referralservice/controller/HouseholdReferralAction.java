/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.referralservice.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.EnrollmentStatusManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.OvcServiceAttributesManager;
import com.nomis.operationsManagement.QuarterlyServiceTrackerManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.HouseholdReferral;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.HivPropertiesManager;
import com.nomis.ovc.util.ReferralFacilityManager;
import com.nomis.ovc.util.UniqueIdManager;
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
public class HouseholdReferralAction extends org.apache.struts.action.Action {

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
        HouseholdReferralForm hhrform=(HouseholdReferralForm)form;
        String moduleName="Household referral service";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=hhrform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        String level2OuId=hhrform.getLevel2OuId();
        String level3OuId=hhrform.getLevel3OuId();
        String organizationUnitId=hhrform.getOrganizationUnitId();
        String cboId=hhrform.getCboId();
        int hhSerialNo=hhrform.getHhSerialNo();
        String hhUniqueId=hhrform.getHhUniqueId();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,hhrform.getCboId());
        HivPropertiesManager.setAllHivStatusList(session);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        loadfacility(session,level2OuId,null);
        AccessManager acm=new AccessManager();
        User user=appManager.getCurrentUser(session);
        requiredAction=acm.getActionName(requiredAction, user);
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
        setBeneficiaryList(session,hhUniqueId);
        loadReferringOrganization(session,cboId);
        //setButtonState(session,"false","true");
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            //beneficiaryid is set to null in the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            hhrform.reset(mapping, request);
            resetBaselineInfo(hhrform);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, hhrform.getCboId());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("householdDetails"))
        {
            hhrform.setHhSerialNo(hhSerialNo);
            hhrform.setHhUniqueId(hhUniqueId);
            setBeneficiaryList(session,hhUniqueId);
            //loadChildrenPerHousehold(session, hhUniqueId);
            hhrform=setOrganizationUnitProperties(session, hhUniqueId,hhrform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("beneficiaryDetails"))
        {
            String beneficiaryId=hhrform.getBeneficiaryId();
            hhrform.reset(mapping, request);
            hhrform.setHhSerialNo(hhSerialNo);
            hhrform.setBeneficiaryId(beneficiaryId);
            hhrform.setOrganizationUnitId(organizationUnitId);
            setBeneficiaryDetails(hhrform,session);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("serviceDetails"))
        {
            UniqueIdManager uim=new UniqueIdManager();
            //int hhSeria
            AppUtility appUtil=new AppUtility();
            String hhrformServiceDate=hhrform.getDateOfReferral();
            String beneficiaryId=hhrform.getBeneficiaryId();
            String referringOrganization=hhrform.getReferringOrganization();
            String receivingOrganization=hhrform.getReceivingOrganization();
            hhrform.reset(mapping, request);
            hhrform.setHhSerialNo(hhSerialNo);
            hhrform.setHhUniqueId(hhUniqueId);
            hhrform.setDateOfReferral(hhrformServiceDate);
            hhrform.setBeneficiaryId(beneficiaryId);
            hhrform.setReferringOrganization(referringOrganization);
            hhrform.setReceivingOrganization(receivingOrganization);
            hhrform.setOrganizationUnitId(organizationUnitId);
            Date serviceDate=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhrformServiceDate));
            HouseholdReferral service=util.getHouseholdReferralDaoInstance().getHouseholdReferral(beneficiaryId, serviceDate);
            if(service !=null)
            {
                String mbcServiceCode=OvcServiceAttributesManager.getMotherBabyCourseServices().getServiceCode();
                //hhrform.reset(mapping, request);
                hhrform.setHhSerialNo(hhSerialNo);
                hhrform.setBeneficiaryId(service.getBeneficiaryId());
                hhrform.setBeneficiaryType(service.getBeneficiaryType());
                hhrform.setReceivingOrganization(service.getReceivingOrganization());
                hhrform.setReferringOrganization(service.getReferringOrganization());
                hhrform.setHealthServices(appUtil.splitString(service.getHealthServices(), ","));
                hhrform.setSafetyServices(appUtil.splitString(service.getSafetyServices(), ","));
                hhrform.setSchoolServices(appUtil.splitString(service.getSchooledServices(), ","));
                hhrform.setStableServices(appUtil.splitString(service.getStableServices(), ","));
                hhrform.setGbvServices(appUtil.splitString(service.getGbvServices(), ","));
                hhrform.setDateOfReferral(DateManager.convertDateToString(service.getDateOfReferral(),"MM/dd/yyyy"));
                hhrform.setReferralCompleted(service.getReferralCompleted());
                hhrform.setVolunteerName(service.getCommunityWorkerId());
                //setBeneficiaryDetails(hhrform,session);
                if(service.getSafetyServices() !=null && service.getSafetyServices().indexOf(mbcServiceCode) !=-1)
                session.setAttribute("mbcdisabled", "false");
                setButtonState(session,"true","false");
                return mapping.findForward(SUCCESS);
            }
            else
            {
                
                
            }
            setBeneficiaryDetails(hhrform,session);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            QuarterlyServiceTrackerManager qstm=new QuarterlyServiceTrackerManager();
            EnrollmentStatusManager esm=new EnrollmentStatusManager();
            HouseholdReferral service=getHouseholdReferral(hhrform,userName);
            service.setAgeAtReferral(getAgeAtService(service));
            util.getHouseholdReferralDaoInstance().saveHouseholdReferral(service);
            esm.updateQuarterlyEnrollmentStatusByServiceParameters(userName,service.getBeneficiaryId(),AppConstant.OVC_TYPE_NUM,service.getDateOfReferral());
            
            //qstm.saveQuarterlyService(service.getBeneficiaryId(), service.getDateOfReferral(),AppConstant.OVC_TYPE_NUM, service.getAgeAtService(),true,userName);
            saveUserActivity(userName,moduleName,"Saved a new Household referral for child with Id "+service.getBeneficiaryId());
            hhrform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("update"))
        {
            QuarterlyServiceTrackerManager qstm=new QuarterlyServiceTrackerManager();
            EnrollmentStatusManager esm=new EnrollmentStatusManager();
            HouseholdReferral service=getHouseholdReferral(hhrform,userName);
            service.setAgeAtReferral(getAgeAtService(service));
            HouseholdReferral dupService=util.getHouseholdReferralDaoInstance().getHouseholdReferral(service.getBeneficiaryId(), service.getDateOfReferral());
            if(dupService !=null && dupService.getGbvServices() !=null)
            service.setGbvServices(dupService.getGbvServices());
            util.getHouseholdReferralDaoInstance().updateHouseholdReferral(service);
            esm.updateQuarterlyEnrollmentStatusByServiceParameters(userName,service.getBeneficiaryId(),AppConstant.OVC_TYPE_NUM,service.getDateOfReferral());
            //qstm.saveQuarterlyService(service.getBeneficiaryId(), service.getDateOfReferral(),AppConstant.OVC_TYPE_NUM, service.getAgeAtService(),true,userName);
            saveUserActivity(userName,moduleName,"Modified Household referral record with Id "+service.getBeneficiaryId());
            hhrform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("markForDelete"))
        {
            HouseholdReferral service=getHouseholdReferral(hhrform,userName);
            util.getHouseholdReferralDaoInstance().markedForDelete(service);
            saveUserActivity(userName,moduleName,"Marked Household referral with Id "+service.getBeneficiaryId()+" for delete");
            hhrform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            HouseholdReferral service=getHouseholdReferral(hhrform,userName);
            util.getHouseholdReferralDaoInstance().markedForDelete(service);
            saveUserActivity(userName,moduleName,"Requested Household referral with Id "+service.getBeneficiaryId()+" to be deleted");
            hhrform.reset(mapping, request);
        }
        hhrform.reset(mapping, request);
        return mapping.findForward(SUCCESS);
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="hhrWithdrawnMessage";
        if(beneficiaryId !=null)
        {
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
            if(beneficiary==null)
            {
                beneficiary=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
            }
            if(beneficiary !=null)
            {
                if(appUtil.getBeneficiaryWithrawnMessage(beneficiary.getCurrentEnrollmentStatus()) !=null)
                {
                    setButtonState(session,AppConstant.TRUEVALUE,AppConstant.TRUEVALUE);
                    session.setAttribute(attributeName, appUtil.getBeneficiaryWithrawnMessage(beneficiary.getCurrentEnrollmentStatus()));
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
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    /*private void loadChildrenPerHousehold(HttpSession session, String hhUniqueId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);
        if(list==null)
        list=new ArrayList();
        session.setAttribute("listOfChildrenPerHousehold", list);
    }*/
    private HouseholdReferralForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,HouseholdReferralForm form,String userName) throws Exception
    {
        DaoUtility util=new DaoUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
        System.err.println("hhUniqueId is "+hhUniqueId);
        if(hhe !=null)
        {
            System.err.println("hhe is not null "+hhe.getHhUniqueId());
            OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(hhe.getOrganizationUnit());
            if(ou !=null)
            {
                ouaManager.setOrganizationUnitAttributes(session, ou.getParentId(),userName,hhe.getCboId());
                form.setLevel3OuId(ou.getParentId());
            }
            form.setOrganizationUnitId(hhe.getOrganizationUnit());
            form.setCboId(hhe.getCboId());
        }
        return form;
    }
    private HouseholdReferral getHouseholdReferral(HouseholdReferralForm hhrform,String userName)
    {
        System.err.println("hhrform.getDateOfReferral() is "+hhrform.getDateOfReferral());
        if(hhrform.getHealthServices() !=null)
        System.err.println("hhrform.getHealthServices() is "+hhrform.getHealthServices().toString());
        AppUtility appUtil=new AppUtility();
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        HouseholdReferral service=new HouseholdReferral();
        service.setBeneficiaryId(hhrform.getBeneficiaryId());
        service.setBeneficiaryType(hhrform.getBeneficiaryType());
        service.setDateOfReferral(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhrform.getDateOfReferral())));
        service.setDateCreated(currentDate);
        service.setLastModifiedDate(currentDate);
        service.setHealthServices(appUtil.concatStr(hhrform.getHealthServices(), null));
        service.setSafetyServices(appUtil.concatStr(hhrform.getSafetyServices(), null));
        service.setStableServices(appUtil.concatStr(hhrform.getStableServices(), null));
        service.setSchooledServices(appUtil.concatStr(hhrform.getSchoolServices(), null));
        service.setReceivingOrganization(hhrform.getReceivingOrganization());
        service.setReferringOrganization(hhrform.getReferringOrganization());
        service.setReferralCompleted(hhrform.getReferralCompleted());
        service.setNumberOfServices(0);
        service.setRecordedBy(userName);
        service.setId(hhrform.getId());
        service.setCommunityWorkerId(hhrform.getVolunteerName());
        return service;
    }
    private void setBeneficiaryDetails(HouseholdReferralForm hhrform,HttpSession session)
    {
        try
        {
            System.err.println("hhrform.getBeneficiaryId() is "+hhrform.getBeneficiaryId());
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getChildEnrollmentDaoInstance().getOvc(hhrform.getBeneficiaryId());
            if(beneficiary==null)
            {
                beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(hhrform.getBeneficiaryId());
            }
            
            if(beneficiary !=null)
            {
                hhrform.setBeneficiaryId(beneficiary.getBeneficiaryId());
                hhrform.setDateOfEnrollment(DateManager.convertDateToString(beneficiary.getDateOfEnrollment(),"MM/dd/yyyy"));
                hhrform.setHivStatus(beneficiary.getCurrentHivStatus());
                hhrform.setSex(beneficiary.getSex());
                hhrform.setPhoneNumber(beneficiary.getPhoneNumber());
                //setWithdrawalStatusMessage(session,hhrform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
            setWithdrawalStatusMessage(session,hhrform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            /*else
            {
                setWithdrawalStatusMessage(session,hhrform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }*/
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private int getAgeAtService(HouseholdReferral service) throws Exception
    {
        int ageAtService=0;
        DaoUtility util=new DaoUtility();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(service.getBeneficiaryId());
        if(ovc !=null)
        {
            ageAtService=ovc.getCurrentAge();
        }
        return ageAtService;
    }
    private void resetBaselineInfo(HouseholdReferralForm hhrform)
    {
        hhrform.setHhUniqueId(null);
        hhrform.setHhName(null);
        hhrform.setDateOfEnrollment(null);
        hhrform.setEducationLevel(0);
        hhrform.setHivStatus(0);
        hhrform.setOccupation(0);
        hhrform.setBeneficiaryId(null);
        hhrform.setPhoneNumber(null);
        hhrform.setSex(null);
    }
    private void setBeneficiaryList(HttpSession session,String hhUniqueId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List beneficiaryList=new ArrayList();
        List childrenList=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);
        if(childrenList !=null)
        {
            beneficiaryList.addAll(childrenList);
           
        }
        List adultList=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMembersPerHousehold(hhUniqueId);
        if(adultList !=null)
        {
            beneficiaryList.addAll(adultList);
        }
        session.setAttribute("refBeneficiaryList", beneficiaryList);
    }
    private void loadfacility(HttpSession session,String level2OuId,String level3OuId)
    {
        try
        {
            ReferralFacilityManager rfm=new ReferralFacilityManager();
            List facilityList=rfm.loadfacility(level2OuId, level3OuId);
            session.setAttribute("refFacilityList", facilityList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void loadReferringOrganization(HttpSession session,String cboId)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List referringOrganizationList=new ArrayList();
            CommunityBasedOrganization cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboId);
            if(cbo !=null)
            referringOrganizationList.add(cbo);
            session.setAttribute("referringOrganizationList", referringOrganizationList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("referralSaveDisabled", saveDisabled);
        session.setAttribute("referralModifyDisabled", modifyDisabled);
    }
}
