/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.educationalassessment.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.ChildEducationPerformanceAssessment;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.HivPropertiesManager;
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
public class ChildEducationPerformanceAssessmentAction extends org.apache.struts.action.Action {

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
        ChildEducationPerformanceAssessmentForm cepaform=(ChildEducationPerformanceAssessmentForm)form;
        String moduleName="Educational Performance assessment ";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=cepaform.getActionName();
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
        String level3OuId=cepaform.getLevel3OuId();
        int hhSerialNo=cepaform.getHhSerialNo();
        String hhUniqueId=cepaform.getHhUniqueId();
        String ovcId=cepaform.getOvcId();
        String dateOfAssessment=cepaform.getDateOfAssessment();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,cepaform.getCboId());
        session.setAttribute("hivStatusForRiskAssessment", HivPropertiesManager.getHivStatusWithoutPositive());
        setOvcPerHouseholdList(session, cepaform.getHhUniqueId());
        setButtonState(session,"false","true");
        setOvcDetails(cepaform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        //setWithdrawalStatusMessage(session,cepaform.getOvcId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
        AccessManager acm=new AccessManager();
        requiredAction=acm.getActionName(requiredAction, user);
        
        if(requiredAction==null)
        {
            //set null ovcid to the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            cepaform.reset(mapping, request);
            //setButtonState(session,"false","true");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, cepaform.getCboId());
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
            String hhName=null;//cepaform.getHhName();
            setOvcPerHouseholdList(session, hhUniqueId);
            request.setAttribute("hhName", hhName);
            cepaform.reset(mapping, request);
            cepaform.setHhUniqueId(hhUniqueId);
            cepaform.setHhSerialNo(hhSerialNo);
            //cepaform.setHhName(hhName);
            cepaform=setOrganizationUnitProperties(session, hhUniqueId,cepaform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("childDetails"))
        {
            UniqueIdManager uim=new UniqueIdManager();
            cepaform.reset(mapping, request);
            cepaform.setHhSerialNo(uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId));
            cepaform.setHhUniqueId(hhUniqueId);
            cepaform.setOvcId(ovcId);
            setOvcDetails(cepaform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("assessmentDetails"))
        {
            //UniqueIdManager uim=new UniqueIdManager();
            Date ddateOfAssessment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(dateOfAssessment));
            ChildEducationPerformanceAssessment cepa=util.getChildEducationPerformanceAssessmentDaoInstance().getChildEducationPerformanceAssessment(cepaform.getOvcId(), ddateOfAssessment);
            cepaform.reset(mapping, request);
            
            if(cepa !=null)
            {
                cepaform.setChildHasInjuriesOrMarks(cepa.getChildHasInjuriesOrMarks());
                cepaform.setChildIsSociallyWithdrawn(cepa.getChildIsSociallyWithdrawn());
                cepaform.setChildMissVocTraining(cepa.getChildMissVocTraining());
                cepaform.setChildProgressedInSchool(cepa.getChildProgressedInSchool());
                cepaform.setClassTeacherComment(cepa.getClassTeacherComment());
                cepaform.setClassTeacherName(cepa.getClassTeacherUid());
                cepaform.setDateOfAssessment(DateManager.getMthDayYearStringDateFormat(cepa.getDateOfAssessment(),0));
                cepaform.setEarlyResumptionInSchool(cepa.getEarlyResumptionInSchool());
                cepaform.setEarlyResumptionInTrainingCenter(cepa.getEarlyResumptionInTrainingCenter());
                cepaform.setGoodPerformanceInLastExam(cepa.getGoodPerformanceInLastExam());
                cepaform.setOvcId(cepa.getOvcId());
                cepaform.setRecordId(cepa.getRecordId());
                cepaform.setRegularSchoolAttendance(cepa.getRegularSchoolAttendance());
                cepaform.setSignsOfFatigueAndTiredness(cepa.getSignsOfFatigueAndTiredness());
                cepaform.setSteadyImprovementInClassWork(cepa.getSteadyImprovementInClassWork());
                cepaform.setSteadyImprovementInVocWork(cepa.getSteadyImprovementInVocWork());
                cepaform.setVolunteerName(cepa.getVolunteerName());
                setOvcDetails(cepaform,session,AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);//setButtonState(session,"true","false");
            }
            else
            {
                setOvcDetails(cepaform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
            cepaform.setHhSerialNo(hhSerialNo);
            cepaform.setHhUniqueId(hhUniqueId);
            cepaform.setOvcId(ovcId);
            cepaform.setDateOfAssessment(dateOfAssessment);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            ChildEducationPerformanceAssessment cepa=getChildEducationPerformanceAssessment(cepaform, userName);
            util.getChildEducationPerformanceAssessmentDaoInstance().saveChildEducationPerformanceAssessment(cepa);
            saveUserActivity(userName,moduleName,"Added new Child educational assessment record with child Id "+cepa.getOvcId());
            setButtonState(session,"false","true");
            cepaform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            ChildEducationPerformanceAssessment cepa=getChildEducationPerformanceAssessment(cepaform, userName);
            util.getChildEducationPerformanceAssessmentDaoInstance().updateChildEducationPerformanceAssessment(cepa);
            saveUserActivity(userName,moduleName,"Updated Child educational assessment record with child Id "+cepa.getOvcId()+" and date of assessment "+cepa.getDateOfAssessment());
            
            setButtonState(session,"false","true");
            cepaform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("markForDelete"))
        {
            ChildEducationPerformanceAssessment cepa=getChildEducationPerformanceAssessment(cepaform, userName);
            util.getChildEducationPerformanceAssessmentDaoInstance().markForDelete(cepa);
            saveUserActivity(userName,moduleName,"Marked Child educational assessment record with child Id "+cepa.getOvcId()+" and date of assessment "+cepa.getDateOfAssessment()+" for delete");
            setButtonState(session,"false","true");
            cepaform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            ChildEducationPerformanceAssessment cepa=getChildEducationPerformanceAssessment(cepaform, userName);
            util.getChildEducationPerformanceAssessmentDaoInstance().markForDelete(cepa);
            saveUserActivity(userName,moduleName,"Deleted Child educational assessment record with child Id "+cepa.getOvcId()+" and date of assessment "+cepa.getDateOfAssessment());
           setButtonState(session,"false","true");
           cepaform.reset(mapping, request);
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private ChildEducationPerformanceAssessment getChildEducationPerformanceAssessment(ChildEducationPerformanceAssessmentForm form,String userName)
    {
        ChildEducationPerformanceAssessment cepa=new ChildEducationPerformanceAssessment();
        cepa.setChildHasInjuriesOrMarks(form.getChildHasInjuriesOrMarks());
        cepa.setChildIsSociallyWithdrawn(form.getChildIsSociallyWithdrawn());
        cepa.setChildMissVocTraining(form.getChildMissVocTraining());
        cepa.setChildProgressedInSchool(form.getChildProgressedInSchool());
        cepa.setClassTeacherComment(form.getClassTeacherComment());
        cepa.setClassTeacherUid(form.getClassTeacherName());
        cepa.setDateCreated(DateManager.getCurrentDateInstance());
        cepa.setDateOfAssessment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfAssessment())));
        cepa.setEarlyResumptionInSchool(form.getEarlyResumptionInSchool());
        cepa.setEarlyResumptionInTrainingCenter(form.getEarlyResumptionInTrainingCenter());
        cepa.setGoodPerformanceInLastExam(form.getGoodPerformanceInLastExam());
        cepa.setLastModifiedDate(DateManager.getCurrentDateInstance());
        cepa.setOvcId(form.getOvcId());
        cepa.setRecordId(form.getRecordId());
        cepa.setRecordedBy(userName);
        cepa.setRegularSchoolAttendance(form.getRegularSchoolAttendance());
        cepa.setSignsOfFatigueAndTiredness(form.getSignsOfFatigueAndTiredness());
        cepa.setSteadyImprovementInClassWork(form.getSteadyImprovementInClassWork());
        cepa.setSteadyImprovementInVocWork(form.getSteadyImprovementInVocWork());
        cepa.setVolunteerName(form.getVolunteerName());
        return cepa;
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="cepaWithdrawnMessage";
        if(beneficiaryId !=null)
        {
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
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
    private ChildEducationPerformanceAssessmentForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,ChildEducationPerformanceAssessmentForm form,String userName) throws Exception
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
    private void setOvcPerHouseholdList(HttpSession session, String hhUniqueId)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);
            if(list==null)
            list=new ArrayList();
            session.setAttribute("hracOvcPerHouseholdList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private Ovc getOvc(String ovcId)
    {
        Ovc ovc=null;
        try
        {
            DaoUtility util=new DaoUtility();
            ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ovc;
    }
    private void setOvcDetails(ChildEducationPerformanceAssessmentForm cepaform,HttpSession session,String saveBtnDisabled,String modifyBtnDisabled)
    {
        try
        {
            System.err.println("cepaform.getOvcId() is "+cepaform.getOvcId());
            DaoUtility util=new DaoUtility();
            String ovcId=cepaform.getOvcId();
            Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
            if(ovc !=null)
            {
                cepaform.setOvcId(ovc.getOvcId());
                cepaform.setDateOfEnrollment(DateManager.convertDateToString(ovc.getDateOfEnrollment(),"MM/dd/yyyy"));
                cepaform.setSex(ovc.getSex());
                cepaform.setPhoneNumber(ovc.getPhoneNumber());
                cepaform.setSchoolName(ovc.getSchoolObj().getSchoolName());
                cepaform.setSchoolGrade(ovc.getSchoolGradeObj().getGradeName());
                setWithdrawalStatusMessage(session,cepaform.getOvcId(),saveBtnDisabled,modifyBtnDisabled); 
            }
            else
            {
                setWithdrawalStatusMessage(session,cepaform.getOvcId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("hracSaveDisabled", saveDisabled);
        session.setAttribute("hracModifyDisabled", modifyDisabled);
    }
}
