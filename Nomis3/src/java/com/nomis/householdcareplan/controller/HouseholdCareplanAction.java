/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.householdcareplan.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.HouseholdCareplan;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
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
public class HouseholdCareplanAction extends org.apache.struts.action.Action {

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
        HouseholdCareplanForm hcpform=(HouseholdCareplanForm)form;
        String moduleName="Household care plan";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=hcpform.getActionName();
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
        String level3OuId=hcpform.getLevel3OuId();
        int hhSerialNo=hcpform.getHhSerialNo();
        String hhUniqueId=hcpform.getHhUniqueId();
        String beneficiaryId=hcpform.getBeneficiaryId();
        String caseplanDate=hcpform.getCareplanDate();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,hcpform.getCboId());
        session.setAttribute("hivStatusForRiskAssessment", HivPropertiesManager.getHivStatusWithoutPositive());
        
        setButtonState(session,"false","true");
        
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        AccessManager acm=new AccessManager();
        requiredAction=acm.getActionName(requiredAction, user);
        
        if(requiredAction==null)
        {
            hcpform.reset(mapping, request);
            setButtonState(session,"false","true");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, hcpform.getCboId());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("beneficiaryList"))
        {
            hhUniqueId=hcpform.getHhUniqueId();
            hcpform.reset(mapping, request);
            setBeneficiaryList(session,hhUniqueId);
            hcpform.setHhUniqueId(hhUniqueId);
            hcpform.setHhSerialNo(hhSerialNo);
            hcpform=setOrganizationUnitProperties(session, hhUniqueId,hcpform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("beneficiaryDetails"))
        {
            hcpform.reset(mapping, request);
            hcpform.setHhSerialNo(hhSerialNo);
            hcpform.setHhUniqueId(hhUniqueId);
            hcpform.setBeneficiaryId(beneficiaryId);
            setBeneficiaryDetails(hcpform);
            setBeneficiaryList(session,hhUniqueId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("careplanDetails"))
        {
                HouseholdCareplan hcp=util.getHouseholdCareplanDaoInstance().getHouseholdCareplan(beneficiaryId, DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(caseplanDate))); 
                hcpform.setBeneficiaryId(beneficiaryId);
                hcpform.reset(mapping, request);
                if(hcp !=null)
                {
                    hcpform.setBeneficiaryId(hcp.getBeneficiaryId());
                       hcpform.setCareplanDate(DateManager.getMthDayYearStringDateFormat(hcp.getCareplanDate(), 1));
                       hcpform.setFollowupForHealthServices(hcp.getFollowupForHealthServices());
                       hcpform.setFollowupForSafetyServices(hcp.getFollowupForSafetyServices());
                       hcpform.setFollowupForSchooledServices(hcp.getFollowupForSchooledServices());
                       hcpform.setFollowupForStableServices(hcp.getFollowupForStableServices());
                       hcpform.setHealthServicesToBeProvided(hcp.getHealthServicesToBeProvided());
                       hcpform.setHouseholdHealthGoals(hcp.getHouseholdHealthGoals());
                       hcpform.setHouseholdSafetyGoals(hcp.getHouseholdSafetyGoals());
                       hcpform.setHouseholdSchooledGoals(hcp.getHouseholdSchooledGoals());
                       hcpform.setHouseholdStableGoals(hcp.getHouseholdStableGoals());
                       hcpform.setIdentifiedHealthIssues(hcp.getIdentifiedHealthIssues());
                       hcpform.setIdentifiedSafetyIssues(hcp.getIdentifiedSafetyIssues());
                       hcpform.setIdentifiedSchooledIssues(hcp.getIdentifiedSchooledIssues());
                       hcpform.setIdentifiedStableIssues(hcp.getIdentifiedStableIssues());
                       hcpform.setPriorityHealthAction(hcp.getPriorityHealthGoals());
                       hcpform.setPrioritySafetyAction(hcp.getPrioritySafetyGoals());
                       hcpform.setPrioritySchooledAction(hcp.getPrioritySchooledGoals());
                       hcpform.setPriorityStableAction(hcp.getPriorityStableGoals());
                       hcpform.setRecordId(hcp.getRecordId());
                       hcpform.setSafetyServicesToBeProvided(hcp.getSafetyServicesToBeProvided());
                       hcpform.setSchooledServicesToBeProvided(hcp.getSchooledServicesToBeProvided());
                       hcpform.setStableServicesToBeProvided(hcp.getStableServicesToBeProvided());
                       hcpform.setTimeFrameForHealthServices(hcp.getTimeFrameForHealthServices());
                       hcpform.setTimeFrameForSafetyServices(hcp.getTimeFrameForSafetyServices());
                       hcpform.setTimeFrameForSchooledServices(hcp.getTimeFrameForSchooledServices());
                       hcpform.setTimeFrameForStableServices(hcp.getTimeFrameForStableServices());
                       hcpform.setVolunteerName(hcp.getVolunteerName());
                       setButtonState(session,"true","false");
                }
            hcpform.setHhSerialNo(hhSerialNo);
            hcpform.setHhUniqueId(hhUniqueId);
            hcpform.setBeneficiaryId(beneficiaryId);
            hcpform.setCareplanDate(caseplanDate);
            setBeneficiaryDetails(hcpform);
            setBeneficiaryList(session,hhUniqueId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            HouseholdCareplan hcp=getHouseholdCareplan(hcpform, userName);
            util.getHouseholdCareplanDaoInstance().saveHouseholdCareplan(hcp);
            saveUserActivity(userName,moduleName,"Added new HouseholdCareplan record with beneficiary Id "+hcp.getBeneficiaryId());
            setButtonState(session,"false","true");
            hcpform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            HouseholdCareplan hcp=getHouseholdCareplan(hcpform, userName);
            util.getHouseholdCareplanDaoInstance().updateHouseholdCareplan(hcp);
            saveUserActivity(userName,moduleName,"Updated HouseholdCareplan with beneficiary Id "+hcp.getBeneficiaryId()+" and careplan date "+hcp.getCareplanDate());
            setButtonState(session,"false","true");
            hcpform.reset(mapping, request);
        }
        
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            HouseholdCareplan hcp=getHouseholdCareplan(hcpform, userName);
            util.getHouseholdCareplanDaoInstance().markForDelete(hcp);
            saveUserActivity(userName,moduleName,"Marked Household care plan record with beneficiary Id "+hcp.getBeneficiaryId()+" and careplan date "+hcp.getCareplanDate());
           setButtonState(session,"false","true");
           hcpform.reset(mapping, request);
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void setBeneficiaryDetails(HouseholdCareplanForm form)
    {
        try
        {
            System.err.println("form.getBeneficiaryId() is "+form.getBeneficiaryId());
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getChildEnrollmentDaoInstance().getOvc(form.getBeneficiaryId());
            if(beneficiary==null)
            {
                beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(form.getBeneficiaryId());
            }
            if(beneficiary !=null)
            {
                form.setAddress(null);
                form.setFirstname(beneficiary.getFirstName());
                form.setSurname(beneficiary.getSurname());
                form.setAge(beneficiary.getCurrentAge());
                form.setSex(beneficiary.getSex());
                form.setDateOfEnrollment(DateManager.getMthDayYearStringDateFormat(beneficiary.getDateOfEnrollment(), 1));
                form.setBeneficiaryId(beneficiary.getBeneficiaryId());
                
                //form.setDateOfEnrollment(DateManager.convertDateToString(beneficiary.getDateOfEnrollment(),DateManager.MM_DD_YYYY_SLASH));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private HouseholdCareplan getHouseholdCareplan(HouseholdCareplanForm form,String userName) throws Exception
    {
       //DaoUtility util=new DaoUtility();
       HouseholdCareplan hcp=new HouseholdCareplan();
       hcp.setBeneficiaryId(form.getBeneficiaryId());
       hcp.setCareplanDate(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getCareplanDate())));
       hcp.setDateCreated(DateManager.getCurrentDateInstance());
       hcp.setFollowupForHealthServices(form.getFollowupForHealthServices());
       hcp.setFollowupForSafetyServices(form.getFollowupForSafetyServices());
       hcp.setFollowupForSchooledServices(form.getFollowupForSchooledServices());
       hcp.setFollowupForStableServices(form.getFollowupForStableServices());
       hcp.setHealthServicesToBeProvided(form.getHealthServicesToBeProvided());
       hcp.setHouseholdHealthGoals(form.getHouseholdHealthGoals());
       hcp.setHouseholdSafetyGoals(form.getHouseholdSafetyGoals());
       hcp.setHouseholdSchooledGoals(form.getHouseholdSchooledGoals());
       hcp.setHouseholdStableGoals(form.getHouseholdStableGoals());
       hcp.setIdentifiedHealthIssues(form.getIdentifiedHealthIssues());
       hcp.setIdentifiedSafetyIssues(form.getIdentifiedSafetyIssues());
       hcp.setIdentifiedSchooledIssues(form.getIdentifiedSchooledIssues());
       hcp.setIdentifiedStableIssues(form.getIdentifiedStableIssues());
       hcp.setLastModifiedDate(DateManager.getCurrentDateInstance());
       hcp.setPriorityHealthGoals(form.getPriorityHealthAction());
       hcp.setPrioritySafetyGoals(form.getPrioritySafetyAction());
       hcp.setPrioritySchooledGoals(form.getPrioritySchooledAction());
       hcp.setPriorityStableGoals(form.getPriorityStableAction());
       hcp.setRecordId(form.getRecordId());
       hcp.setRecordedBy(userName);
       hcp.setSafetyServicesToBeProvided(form.getSafetyServicesToBeProvided());
       hcp.setSchooledServicesToBeProvided(form.getSchooledServicesToBeProvided());
       hcp.setStableServicesToBeProvided(form.getStableServicesToBeProvided());
       hcp.setTimeFrameForHealthServices(form.getTimeFrameForHealthServices());
       hcp.setTimeFrameForSafetyServices(form.getTimeFrameForSafetyServices());
       hcp.setTimeFrameForSchooledServices(form.getTimeFrameForSchooledServices());
       hcp.setTimeFrameForStableServices(form.getTimeFrameForStableServices());
       hcp.setVolunteerName(form.getVolunteerName());
       
       return hcp;
    }
    private HouseholdCareplanForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,HouseholdCareplanForm form,String userName) throws Exception
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
        session.setAttribute("hcpBeneficiaryList", beneficiaryList);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("hcpSaveDisabled", saveDisabled);
        session.setAttribute("hcpModifyDisabled", modifyDisabled);
    }
}
