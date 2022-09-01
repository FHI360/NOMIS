/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.caregiveraccesstoemergencyfund.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.CaregiverAccessToEmergencyFund;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.HivPropertiesManager;
import java.util.ArrayList;
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
public class CaregiverAccessToEmergencyFundAction extends org.apache.struts.action.Action {

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
        CaregiverAccessToEmergencyFundForm caefform=(CaregiverAccessToEmergencyFundForm)form;
        String moduleName="Caregiver access to emergency funds";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=caefform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
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
        String level3OuId=caefform.getLevel3OuId();
        String cboId=caefform.getCboId();
        int hhSerialNo=caefform.getHhSerialNo();
        String hhUniqueId=caefform.getHhUniqueId();
        String beneficiaryId=caefform.getBeneficiaryId();
        String dateOfAssessment=caefform.getDateOfAssessment();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,cboId);
        session.setAttribute("hivStatusForRiskAssessment", HivPropertiesManager.getHivStatusWithoutPositive());
        setHouseholdMemberListPerHousehold(session, caefform.getHhUniqueId());
        //setButtonState(session,"false","true");
        getAdultHouseholdMemberFormWithDetails(session,caefform);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        //setWithdrawalStatusMessage(session,caefform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.TRUEVALUE);
        AccessManager acm=new AccessManager();
        requiredAction=acm.getActionName(requiredAction, user);
        
        if(requiredAction==null)
        {
            //set null beneficiaryid to the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            caefform.reset(mapping, request);
            //setButtonState(session,"false","true");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, caefform.getCboId());
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
            caefform.reset(mapping, request);
            String hhName=null;
            setHouseholdMemberListPerHousehold(session, hhUniqueId);
            request.setAttribute("hhName", hhName);
            caefform.setHhUniqueId(hhUniqueId);
            caefform.setHhSerialNo(hhSerialNo);
            caefform.setCboId(cboId);
            caefform=setOrganizationUnitProperties(session, hhUniqueId,caefform,userName);
            //setWithdrawalStatusMessage(session,caefform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("assessmentDetails"))
        {
            CaregiverAccessToEmergencyFund caef=util.getCaregiverAccessToEmergencyFundDaoInstance().getCaregiverAccessToEmergencyFund(caefform.getBeneficiaryId(), DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(dateOfAssessment)));
            caefform.reset(mapping, request);
            caefform.setCboId(cboId);
            caefform.setHhSerialNo(hhSerialNo);
            caefform.setHhUniqueId(hhUniqueId);
            caefform.setBeneficiaryId(beneficiaryId);
            caefform.setDateOfAssessment(dateOfAssessment);
            getAdultHouseholdMemberFormWithDetails(session,caefform);
            if(caef !=null)
            {
                AppUtility appUtil=new AppUtility();
                caefform.setAccessMoneyToPay(caef.getAccessMoneyToPay());
                caefform.setRecordId(caef.getRecordId());
                caefform.setUnexpectedExpenditure(caef.getUnexpectedExpenditure());
                caefform.setSourceOfMoney(appUtil.splitString(caef.getSourceOfMoney(), ","));
                caefform.setUrgentHhNeeds(appUtil.splitString(caef.getUrgentHhNeeds(), ","));
                caefform.setVolunteerName(caef.getVolunteerName());
                setWithdrawalStatusMessage(session,caefform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
                //setButtonState(session,"true","false");
            }
            else
            {
                setWithdrawalStatusMessage(session,caefform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            CaregiverAccessToEmergencyFund caef=getCaregiverAccessToEmergencyFund(caefform, userName);
            util.getCaregiverAccessToEmergencyFundDaoInstance().saveCaregiverAccessToEmergencyFund(caef);
            saveUserActivity(userName,moduleName,"Added new Caregiver Access To Emergency Fund record with Id "+caef.getBeneficiaryId());
            setButtonState(session,"false","true");
            caefform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            CaregiverAccessToEmergencyFund caef=getCaregiverAccessToEmergencyFund(caefform, userName);
            util.getCaregiverAccessToEmergencyFundDaoInstance().updateCaregiverAccessToEmergencyFund(caef);
            saveUserActivity(userName,moduleName,"Updated Caregiver Access To Emergency Fund record with Id "+caef.getBeneficiaryId()+" and date of assessment "+caef.getDateOfAssessment());
            setButtonState(session,"false","true");
            caefform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            CaregiverAccessToEmergencyFund caef=getCaregiverAccessToEmergencyFund(caefform, userName);
            util.getCaregiverAccessToEmergencyFundDaoInstance().markForDelete(caef);
            saveUserActivity(userName,moduleName,"Marked Caregiver Access To Emergency Fund record with Id "+caef.getBeneficiaryId()+" and date of assessment "+caef.getDateOfAssessment()+" for delete");
            setButtonState(session,"false","true");
            caefform.reset(mapping, request);
        }
        
        return mapping.findForward(SUCCESS);
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="caefWithdrawnMessage";
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
    private CaregiverAccessToEmergencyFund getCaregiverAccessToEmergencyFund(CaregiverAccessToEmergencyFundForm form, String userName)
    {
        AppUtility appUtil=new AppUtility();
        CaregiverAccessToEmergencyFund caef=new CaregiverAccessToEmergencyFund();
        caef.setAccessMoneyToPay(form.getAccessMoneyToPay());
        caef.setBeneficiaryId(form.getBeneficiaryId());
        caef.setDateCreated(DateManager.getCurrentDateInstance());
        caef.setDateOfAssessment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfAssessment())));
        caef.setLastModifiedDate(DateManager.getCurrentDateInstance());
        caef.setRecordId(form.getRecordId());
        caef.setRecordedBy(userName);
        caef.setSourceOfMoney(appUtil.concatStr(form.getSourceOfMoney(),null));
        caef.setUnexpectedExpenditure(form.getUnexpectedExpenditure());
        caef.setUrgentHhNeeds(appUtil.concatStr(form.getUrgentHhNeeds(), null));
        caef.setVolunteerName(form.getVolunteerName());
        return caef;
    }
    private void setHouseholdMemberListPerHousehold(HttpSession session, String hhUniqueId)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMembersPerHousehold(hhUniqueId);
            if(list==null)
            list=new ArrayList();
            session.setAttribute("caefahmList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private CaregiverAccessToEmergencyFundForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,CaregiverAccessToEmergencyFundForm form,String userName) throws Exception
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
                form.setLevel3OuId(ou.getParentId());
            }
            form.setOrganizationUnitId(hhe.getOrganizationUnit());
            form.setCboId(hhe.getCboId());
        }
        return form;
    }
    private CaregiverAccessToEmergencyFundForm getAdultHouseholdMemberFormWithDetails(HttpSession session,CaregiverAccessToEmergencyFundForm form)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(form.getBeneficiaryId());
            if(ahm !=null)
            {                               
                form.setBeneficiaryId(ahm.getBeneficiaryId());
                form.setDateOfEnrollment(DateManager.convertDateToString(ahm.getDateOfEnrollment(),"MM/dd/yyyy"));
                form.setHhUniqueId(ahm.getHhUniqueId());
                form.setOrganizationUnitId(ahm.getOrganizationUnit());
                form.setPhoneNumber(ahm.getPhoneNumber());
                form.setSex(ahm.getSex());
                session.setAttribute("caefahm", ahm);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return form;
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("caefSaveDisabled", saveDisabled);
        session.setAttribute("caefModifyDisabled", modifyDisabled);
    }
}
