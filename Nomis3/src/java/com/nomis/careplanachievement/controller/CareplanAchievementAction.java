/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.careplanachievement.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.CareplanAchievementChecklist;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.UniqueIdManager;
import java.util.Date;
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
public class CareplanAchievementAction extends org.apache.struts.action.Action {

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
        CareplanAchievementForm cpaform=(CareplanAchievementForm)form;
        HttpSession session=request.getSession();
                
        String moduleName="Care plan achievement checklist";
        DaoUtility util=new DaoUtility();
        
        String requiredAction=cpaform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        String level2OuId=cpaform.getLevel2OuId();
        String level3OuId=cpaform.getLevel3OuId();
        int hhSerialNo=cpaform.getHhSerialNo();
        String hhUniqueId=cpaform.getHhUniqueId();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId, userName, cpaform.getCboId());
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
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        //setWithdrawalStatusMessage(session,cpaform.getHhUniqueId(),AppConstant.TRUEVALUE,AppConstant.TRUEVALUE);
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            cpaform.reset(mapping, request);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("householdDetails"))
        {
            //System.err.println("inside householdDetails ");
            AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getHeadOfHousehold(hhUniqueId);
            cpaform.reset(mapping, request);
            cpaform.setHhSerialNo(hhSerialNo);
            cpaform.setHhUniqueId(hhUniqueId);
            if(ahm !=null)
            {
                //System.err.println("ahm is not null");
                cpaform.setFirstName(ahm.getFirstName());
                cpaform.setSurname(ahm.getSurname());
                cpaform.setSex(ahm.getSex());
                cpaform.setPhoneNumber(ahm.getPhoneNumber());
                cpaform.setDateOfEnrollment(DateManager.convertDateToString(ahm.getDateOfEnrollment(), DateManager.MM_DD_YYYY_SLASH));
                setWithdrawalStatusMessage(session,cpaform.getHhUniqueId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
            }
            else
            {
                setWithdrawalStatusMessage(session,cpaform.getHhUniqueId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
        }
        else if(requiredAction.equalsIgnoreCase("assessmentDetails"))
        {
            UniqueIdManager uim=new UniqueIdManager();
            String cpaYearMonthDayDateOfAssessment=DateManager.processMthDayYearToMysqlFormat(cpaform.getDateOfAssessment());
            Date cpaDateOfAssessment=DateManager.getDateInstance(cpaYearMonthDayDateOfAssessment);
            CareplanAchievementChecklist cpa=util.getCareplanAchievementChecklistDaoInstance().getCareplanAchievementChecklist(hhUniqueId, cpaDateOfAssessment);
            AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getHeadOfHousehold(hhUniqueId);
            if(ahm !=null)
            {
                cpaform.setFirstName(ahm.getFirstName());
                cpaform.setSurname(ahm.getSurname());
                cpaform.setSex(ahm.getSex());
                cpaform.setPhoneNumber(ahm.getPhoneNumber());
                cpaform.setDateOfEnrollment(DateManager.convertDateToString(ahm.getDateOfEnrollment(),DateManager.MM_DD_YYYY_SLASH));
            }
            if(cpa !=null)
            {
                cpaform.setAllChildrenHaveBirthCert(cpa.getAllChildrenHaveBirthCert());
                cpaform.setCgiversEconomicActivity(cpa.getCgiversEconomicActivity());
                cpaform.setChildrenEnrolledInSchool(cpa.getChildrenEnrolledInSchool());
                cpaform.setChildrenHivStatusknown(cpa.getChildrenHivStatusknown());
                cpaform.setChildrenNotUndernourished(cpa.getChildrenNotUndernourished());
                cpaform.setDateOfAssessment(DateManager.convertDateToString(cpa.getDateOfAssessment(), DateManager.MM_DD_YYYY_SLASH));
                cpaform.setDocumentedViralLoadResult(cpa.getDocumentedViralLoadResult());
                cpaform.setEmotionalSupportTeamIdentification(cpa.getEmotionalSupportTeamIdentification());
                cpaform.setHhUniqueId(cpa.getHhUniqueId());
                cpaform.setHivPosAdolscentsLinked(cpa.getHivPosAdolscentsLinked());
                cpaform.setHivPreventionKnowledge(cpa.getHivPreventionKnowledge());
                cpaform.setRecordId(cpa.getRecordId());
                cpaform.setRegularSchoolAttendance(cpa.getRegularSchoolAttendance());
                cpaform.setStableAdultInHousehold(cpa.getStableAdultInHousehold());
                cpaform.setViolenceIncidenceReport(cpa.getViolenceIncidenceReport());
                cpaform.setVolunteerName(cpa.getVolunteerName());
                cpaform.setHouseholdGraduated(cpa.getHhGraduated());
                //cpaform.setHouseholdGraduated(cpa.getHouseholdGraduated());
                setButtonState(session,"true","false");
            }
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            CareplanAchievementChecklist cpa=getCareplanAchievementChecklist(cpaform,userName);
            util.getCareplanAchievementChecklistDaoInstance().saveCareplanAchievementChecklist(cpa);
            saveUserActivity(userName,moduleName,"Saved new Care plan achievement record for household with Id "+cpa.getHhUniqueId());
            cpaform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("update"))
        {
           CareplanAchievementChecklist cpa=getCareplanAchievementChecklist(cpaform,userName);
            util.getCareplanAchievementChecklistDaoInstance().updateCareplanAchievementChecklist(cpa); 
            saveUserActivity(userName,moduleName,"Updated Care plan achievement record for household with Id "+cpa.getHhUniqueId());
            cpaform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            CareplanAchievementChecklist cpa=getCareplanAchievementChecklist(cpaform,userName);
            util.getCareplanAchievementChecklistDaoInstance().markForDelete(cpa);
            saveUserActivity(userName,moduleName,"Marked Care plan achievement record for household with Id "+cpa.getHhUniqueId()+" for delete");
            cpaform.reset(mapping, request);
        }
        return mapping.findForward(SUCCESS);
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="cpaWithdrawnMessage";
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
    private CareplanAchievementChecklist getCareplanAchievementChecklist(CareplanAchievementForm cpaform,String userName)
    {
        CareplanAchievementChecklist cpa=new CareplanAchievementChecklist();
        cpa.setAllChildrenHaveBirthCert(cpaform.getAllChildrenHaveBirthCert());
        cpa.setCgiversEconomicActivity(cpaform.getCgiversEconomicActivity());
        cpa.setChildrenEnrolledInSchool(cpaform.getChildrenEnrolledInSchool());
        cpa.setChildrenHivStatusknown(cpaform.getChildrenHivStatusknown());
        cpa.setChildrenNotUndernourished(cpaform.getChildrenNotUndernourished());
        cpa.setDateOfAssessment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(cpaform.getDateOfAssessment())));
        cpa.setDocumentedViralLoadResult(cpaform.getDocumentedViralLoadResult());
        cpa.setEmotionalSupportTeamIdentification(cpaform.getEmotionalSupportTeamIdentification());
        cpa.setHhUniqueId(cpaform.getHhUniqueId());
        cpa.setHivPosAdolscentsLinked(cpaform.getHivPosAdolscentsLinked());
        cpa.setHivPreventionKnowledge(cpaform.getHivPreventionKnowledge());
        cpa.setRecordId(cpaform.getRecordId());
        cpa.setRegularSchoolAttendance(cpaform.getRegularSchoolAttendance());
        cpa.setStableAdultInHousehold(cpaform.getStableAdultInHousehold());
        cpa.setViolenceIncidenceReport(cpaform.getViolenceIncidenceReport());
        cpa.setVolunteerName(cpaform.getVolunteerName());
        cpa.setDateCreated(DateManager.getCurrentDateInstance());
        cpa.setLastModifiedDate(DateManager.getCurrentDateInstance());
        cpa.setVolunteerName(cpaform.getVolunteerName());
        cpa.setHhGraduated(cpaform.getHouseholdGraduated());
        cpa.setRecordedBy(userName);
        //cpa.setScore(cpa.getScore());
        //cpa.setHouseholdGraduated(cpaform.getHouseholdGraduated());
        return cpa;
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("cpaSaveDisabled", saveDisabled);
        session.setAttribute("cpaModifyDisabled", modifyDisabled);
    }
}
