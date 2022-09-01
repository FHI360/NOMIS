/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.childenrollment.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.HivPropertiesManager;
import com.nomis.ovc.util.MaritalStatusManager;
import com.nomis.ovc.util.OccupationManager;
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
public class AdultHouseholdMemberAction extends org.apache.struts.action.Action {

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
        AdultHouseholdMemberForm ahmform=(AdultHouseholdMemberForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        MaritalStatusManager msm=new MaritalStatusManager();
        OccupationManager om=new OccupationManager();
        String moduleName="Adult household member enrollment";
        String requiredAction=ahmform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
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
        String userName=appManager.getCurrentUserName(session);
        String level2OuId=ahmform.getLevel2OuId();
        String level3OuId=ahmform.getLevel3OuId();
        String hhUniqueId=ahmform.getHhUniqueId();
        
        msm.setMaritalStatusList(session);
        om.setOccupationList(session);
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,ahmform.getCboId());
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        //session.setAttribute("mainHivStatus", HivPropertiesManager.getThreeMainHivStatus());
        setHouseholdMemberListPerHousehold(session, ahmform.getHhUniqueId());
        loadfacility(session,level2OuId,null);
        //setWithdrawalStatusMessage(session,ahmform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.TRUEVALUE);
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            //set null beneficiaryid to the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            ahmform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, ahmform.getCboId());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }//
        else if(requiredAction.equalsIgnoreCase("ahmList"))
        {
            String hhName=ahmform.getHhName();
            int hhSerialNo=ahmform.getHhSerialNo();
            setHouseholdMemberListPerHousehold(session, ahmform.getHhUniqueId());
            request.setAttribute("hhName", hhName);
            ahmform.reset(mapping, request);
            ahmform.setHhSerialNo(hhSerialNo);
            ahmform.setHhUniqueId(hhUniqueId);
            ahmform.setHhName(hhName);
            ahmform=setOrganizationUnitProperties(session, hhUniqueId,ahmform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ahmDetails"))
        {
            AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(ahmform.getEnrolledBeneficiaryId());
            if(ahm !=null)
            {
                String hhName=ahmform.getHhName();
                ahmform.setAgeAtBaseline(ahm.getAgeAtBaseline());
                ahmform.setEnrolledOnTreatment(ahm.getEnrolledOnTreatment());
                ahmform.setHivTreatmentFacilityId(ahm.getHivTreatmentFacilityId());
                ahmform.setAddress(ahm.getAddress());
                ahmform.setBeneficiaryId(ahm.getBeneficiaryId());
                ahmform.setEducationLevel(ahm.getEducationLevel());
                ahmform.setFirstName(ahm.getFirstName());
                ahmform.setSurname(ahm.getSurname());
                ahmform.setHhUniqueId(ahm.getHhUniqueId());
                ahmform.setBaselineHivStatus(ahm.getBaselineHivStatus());
                ahmform.setOccupation(ahm.getOccupation());
                ahmform.setMaritalStatus(ahm.getMaritalStatus());
                ahmform.setPhoneNumber(ahm.getPhoneNumber());
                ahmform.setSex(ahm.getSex());
                ahmform.setEnrolledOnTreatment(ahm.getEnrolledOnTreatment());
                ahmform.setHivTreatmentFacilityId(ahm.getHivTreatmentFacilityId());
                ahmform.setTreatmentId(ahm.getTreatmentId());
                ahmform.setBeneficiaryType(ahm.getBeneficiaryType());
                ahmform.setDateEnrolledOnTreatment(DateManager.convertDateToString(ahm.getDateEnrolledOnTreatment(),DateManager.MM_DD_YYYY_SLASH));
                ahmform.setDateOfBaselineHivStatus(DateManager.convertDateToString(ahm.getDateOfBaselineHivStatus(),DateManager.MM_DD_YYYY_SLASH));
                if(ahm.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                setHIVStatusProperties(session,AppConstant.FALSEVALUE);
                else
                {
                    if(ahm.getBaselineHivStatus()==AppConstant.HIV_UNKNOWN_NUM)
                    setUnknownHIVStatusProperties(session,AppConstant.TRUEVALUE);
                    else
                    setUnknownHIVStatusProperties(session,AppConstant.FALSEVALUE);
                    setHIVStatusProperties(session,AppConstant.TRUEVALUE);
                }
                if(ahmform.getEnrolledOnTreatment()==0)
                {
                    ahmform.setDateEnrolledOnTreatment(null);
                    if(ahmform.getDateOfBaselineHivStatus() !=null && ahmform.getDateOfBaselineHivStatus().equalsIgnoreCase(DateManager.DEFAULT_DATE))
                    {
                        ahmform.setDateOfBaselineHivStatus(null);
                    }
                }
                ahmform.setDateOfEnrollment(DateManager.convertDateToString(ahm.getDateOfEnrollment(),DateManager.MM_DD_YYYY_SLASH));
                request.setAttribute("hhName", hhName);
                setWithdrawalStatusMessage(session,ahmform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
            }
            else
            {
                setWithdrawalStatusMessage(session,ahmform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            AdultHouseholdMember ahm=getAdultHouseholdMember(ahmform,userName);
            util.getAdultHouseholdMemberDaoInstance().saveAdultHouseholdMember(ahm);
            setHouseholdMemberListPerHousehold(session, ahmform.getHhUniqueId());
            saveUserActivity(userName,moduleName,"Saved AHM beneficiary "+ahm.getBeneficiaryId());
            ahmform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            AdultHouseholdMember ahm=getAdultHouseholdMember(ahmform,userName);
            ahm.setBeneficiaryId(ahmform.getEnrolledBeneficiaryId());
            util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
            setHouseholdMemberListPerHousehold(session, ahmform.getHhUniqueId());
            saveUserActivity(userName,moduleName,"modified AHM beneficiary "+ahm.getBeneficiaryId());
            //System.err.println("ahmform.getSelectedBeneficiaryId() is "+ahmform.getSelectedBeneficiaryId());
            ahmform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            AdultHouseholdMember ahm=getAdultHouseholdMember(ahmform,userName);
            util.getAdultHouseholdMemberDaoInstance().markForDelete(ahm);
            ahm.setBeneficiaryId(ahmform.getEnrolledBeneficiaryId());
            //hhedao.deleteHouseholdEnrollment(getHouseholdEnrollment(hheform));
            setHouseholdMemberListPerHousehold(session, ahmform.getHhUniqueId());
            saveUserActivity(userName,moduleName,"Deleted AHM "+ahm.getBeneficiaryId());
            ahmform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        
        return mapping.findForward(SUCCESS);
    }
    private void setHIVStatusProperties(HttpSession session,String disabled)
    {
        session.setAttribute("ahmHivDisabled", disabled);
    }
    private void setUnknownHIVStatusProperties(HttpSession session,String disabled)
    {
        session.setAttribute("ahmUnkHivDisabled", disabled);
    }
    private AdultHouseholdMember getAdultHouseholdMember(AdultHouseholdMemberForm ahmform,String userName)
    {
        UniqueIdManager uig=new UniqueIdManager();
        AdultHouseholdMember ahm=new AdultHouseholdMember();
        Date dateOfEnrollment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(ahmform.getDateOfEnrollment()));
        Date dateOfBaselineHivStatus=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(ahmform.getDateOfBaselineHivStatus()));
        
        ahm.setAddress(ahmform.getAddress());
        ahm.setAgeAtBaseline(ahmform.getAgeAtBaseline());
        ahm.setCurrentAge(ahmform.getAgeAtBaseline());
        ahm.setCurrentEnrollmentStatus(AppConstant.ACTIVE_NUM);
        ahm.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        ahm.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        ahm.setDateOfEnrollment(dateOfEnrollment);
        ahm.setDateOfBaselineHivStatus(dateOfBaselineHivStatus);
        ahm.setDateEnrolledOnTreatment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(ahmform.getDateEnrolledOnTreatment())));
        ahm.setDateOfCurrentEnrollmentStatus(dateOfEnrollment);
        ahm.setDateOfCurrentHivStatus(dateOfBaselineHivStatus);
        ahm.setEnrolledOnTreatment(ahmform.getEnrolledOnTreatment());
        ahm.setHivTreatmentFacilityId(ahmform.getHivTreatmentFacilityId());
        ahm.setTreatmentId(ahmform.getTreatmentId());
        ahm.setSex(ahmform.getSex());
        ahm.setIsCaregiver(1);
        
        ahm.setEducationLevel(ahmform.getEducationLevel());
        ahm.setMaritalStatus(ahmform.getMaritalStatus());
        //ahm.setEnrollmentId(ahmform.getBeneficiaryId());
        ahm.setFirstName(ahmform.getFirstName());
        ahm.setHhUniqueId(ahmform.getHhUniqueId());
        
        ahm.setBaselineHivStatus(ahmform.getBaselineHivStatus());
        ahm.setCurrentHivStatus(ahmform.getBaselineHivStatus());
        ahm.setIsCaregiver(0);
        ahm.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        ahm.setOccupation(ahmform.getOccupation());
        ahm.setPhoneNumber(ahmform.getPhoneNumber());
        ahm.setRecordedBy(userName);
        ahm.setSex(ahmform.getSex());
        ahm.setSurname(ahmform.getSurname());
        if(ahmform.getBeneficiaryType()>0)
        {
            ahm.setBeneficiaryType(ahmform.getBeneficiaryType());
        }
        else
        ahm.setBeneficiaryType(ahmform.getBeneficiaryType());    
        //String uniqueId=uig.generateHouseholdUniqueId(ahmform.getFirstName(), ahmform.getSurname(),ahmform.getLevel3OuId(), ahmform.getDateOfBirth(), ahmform.getSex());
        ahm.setBeneficiaryId(ahmform.getBeneficiaryId());
        ahm.setEnrollmentId(ahmform.getBeneficiaryId());
        ahm.setRecordedBy(userName);
        return ahm;
    }
    private AdultHouseholdMemberForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,AdultHouseholdMemberForm ahmform,String userName) throws Exception
    {
        DaoUtility util=new DaoUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
        if(hhe !=null)
        {
            OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(hhe.getOrganizationUnit());
            if(ou !=null)
            {
                ouaManager.setOrganizationUnitAttributes(session, ou.getParentId(),userName,hhe.getCboId());
                ahmform.setLevel3OuId(ou.getParentId());
            }
            ahmform.setOrganizationUnitId(hhe.getOrganizationUnit());
            ahmform.setCboId(hhe.getCboId());
        }
        return ahmform;
    }
    private void setHouseholdMemberListPerHousehold(HttpSession session, String hhUniqueId)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            hhUniqueId=UniqueIdManager.cleanUniqueId(hhUniqueId);
            List list=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMembersPerHousehold(hhUniqueId);
            if(list==null)
            list=new ArrayList();
            session.setAttribute("ahmList", list);
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
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("ahmSaveDisabled", saveDisabled);
        session.setAttribute("ahmModifyDisabled", modifyDisabled);
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="ahmWithdrawnMessage";
        if(beneficiaryId !=null)
        {
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
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
}
