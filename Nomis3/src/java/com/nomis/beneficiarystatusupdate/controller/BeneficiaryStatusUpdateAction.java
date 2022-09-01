/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.beneficiarystatusupdate.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.EnrollmentStatusManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.BeneficiaryStatusUpdate;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.BeneficiaryStatusManager;
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
public class BeneficiaryStatusUpdateAction extends org.apache.struts.action.Action {

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
            throws Exception {
        BeneficiaryStatusUpdateForm bsuform=(BeneficiaryStatusUpdateForm)form;
        String moduleName="Beneficiary status update";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        String requiredAction=bsuform.getActionName();
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
        String level2OuId=bsuform.getLevel2OuId();
        String level3OuId=bsuform.getLevel3OuId();
        int hhSerialNo=bsuform.getHhSerialNo();
        String hhUniqueId=bsuform.getHhUniqueId();
        String beneficiaryId=bsuform.getBeneficiaryId();
        String caregiverId=bsuform.getCaregiverId();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,bsuform.getCboId());
        String hhId=request.getParameter("hh");
        String reqParam=request.getParameter("p");
        //setEnrolledOnTreatmentControlStatus(request,bsuform.getNewHivStatus());
        if(reqParam !=null)
        {
            requiredAction=reqParam;
            hhUniqueId=hhId;
        }
        
        //System.err.println("hhUniqueId before required action is "+hhUniqueId);
        //setBeneficiaryListPerHousehold(session, hhUniqueId);
        //setButtonState(session,"false","true");
        setBeneficiaryList(session,hhUniqueId);
        session.setAttribute("lastHivDisabled", "true");
        session.setAttribute("newHivDisabled", "false");
        session.setAttribute("allHivStatus", HivPropertiesManager.getAllHivStatus());
        session.setAttribute("posNegUnkHivStatus", HivPropertiesManager.getThreeMainHivStatus());
        setEnrolledOnTreatmentControlStatus(session,AppConstant.ENROLLED_ON_TREATMENT_NO_NUM);
        generateSchoolList(session,bsuform);
        generateSchoolGradeList(session);
        loadfacility(session,level2OuId,null);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        //setWithdrawalStatusMessage(session,bsuform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.TRUEVALUE,requiredAction);
        System.err.println("requiredAction is "+requiredAction);
        BeneficiaryStatusManager bsm=new BeneficiaryStatusManager();
        session.setAttribute("beneficiaryStatusList", bsm.getBeneficiaryStatusListForChildren());
        session.setAttribute("beneficiaryStatusListForAdults", bsm.getBeneficiaryStatusListForAdults());
        session.setAttribute("allBeneficiaryStatusList", bsm.getAllBeneficiaryStatus());
        EnrollmentStatusManager esm=new EnrollmentStatusManager();
        //esm.isActiveInReportPeriod("NAS/NAS/CES/00001/00001",DateManager.getDateInstance("2021-03-28"));
        if(requiredAction==null)
        {
            //beneficiaryid is set to null in the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            bsuform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, bsuform.getCboId());
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
            String hhName=bsuform.getHhName();
            loadCaregiversPerHousehold(session, hhUniqueId);
            loadChildrenPerHousehold(session, hhUniqueId);
            
            bsuform.reset(mapping, request);
            //setBeneficiaryList(session,hhUniqueId);
            //setBeneficiaryListPerHousehold(session, hhUniqueId);
            request.setAttribute("hhName", hhName);
            bsuform.setHhSerialNo(hhSerialNo);
            bsuform.setHhUniqueId(hhUniqueId);
            bsuform.setHhName(hhName);
            bsuform=setOrganizationUnitProperties(session, hhUniqueId,bsuform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("childDetails"))
        {
            //String beneficiaryId=bsuform.getBeneficiaryId();
            //bsuform.reset(mapping, request);
            bsuform.setHhSerialNo(hhSerialNo);
            bsuform.setHhUniqueId(hhUniqueId);
            bsuform.setBeneficiaryId(beneficiaryId);
            setChildDetails(session,bsuform);
            setAdultMemberDetails(session,bsuform);
            loadCaregiversPerHousehold(session, hhUniqueId);
            setButtonState(session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            loadChildrenPerHousehold(session, hhUniqueId);
            //setWithdrawalStatusMessage(session,bsuform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("adultMemberDetails"))
        {
            //String caregiverId=bsuform.getCaregiverId();
            //bsuform.reset(mapping, request);
            bsuform.setCaregiverId(caregiverId);
            bsuform=setAdultMemberDetails(session,bsuform);
            setChildDetails(session,bsuform);
            //setBeneficiaryDetails(session,bsuform);
            loadCaregiversPerHousehold(session, hhUniqueId);
            loadChildrenPerHousehold(session, hhUniqueId);
            setButtonState(session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            //setWithdrawalStatusMessage(session,bsuform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            BeneficiaryStatusUpdate hsm=getBeneficiaryStatusUpdate(bsuform,userName);
            util.getBeneficiaryStatusUpdateDaoInstance().saveBeneficiaryStatusUpdate(hsm,true);
            updateBeneficiaryCurrentEnrollmentStatus(bsuform);
            saveUserActivity(userName,moduleName,"Saved new HIV status record for beneficiary with Id "+hsm.getBeneficiaryId());
            //setBeneficiaryList(session,hhUniqueId);
            //setBeneficiaryListPerHousehold(session, hhUniqueId);
            
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            BeneficiaryStatusUpdate hsm=getBeneficiaryStatusUpdate(bsuform,userName);
            util.getBeneficiaryStatusUpdateDaoInstance().updateBeneficiaryStatusUpdate(hsm,true);
            updateBeneficiaryCurrentEnrollmentStatus(bsuform);
            saveUserActivity(userName,moduleName,"Modified HIV status record for beneficiary with Id "+hsm.getBeneficiaryId());
            setBeneficiaryList(session,hhUniqueId);
            //setBeneficiaryListPerHousehold(session, hhUniqueId);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            BeneficiaryStatusUpdate hsm=getBeneficiaryStatusUpdate(bsuform,userName);
            util.getBeneficiaryStatusUpdateDaoInstance().markForDelete(hsm);
            saveUserActivity(userName,moduleName,"Requested HIV status record for beneficiary with Id "+hsm.getBeneficiaryId()+" be deleted");
            setBeneficiaryList(session,hhUniqueId);
            //setBeneficiaryListPerHousehold(session, hhUniqueId);
        }
        return mapping.findForward(SUCCESS);
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="bsuWithdrawnMessage";
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
    private BeneficiaryStatusUpdateForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,BeneficiaryStatusUpdateForm form,String userName) throws Exception
    {
        DaoUtility util=new DaoUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
        if(hhe !=null)
        {
            System.err.println("hhe.getHhUniqueId() is "+hhe.getHhUniqueId());
            OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(hhe.getOrganizationUnit());
            if(ou !=null)
            {
                System.err.println("ou.getName() is "+ou.getName());
                ouaManager.setOrganizationUnitAttributes(session, ou.getParentId(),userName,hhe.getCboId());
                form.setLevel3OuId(ou.getParentId());
            }
            form.setOrganizationUnitId(hhe.getOrganizationUnit());
            form.setCboId(hhe.getCboId());
        }
        return form;
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void setChildDetails(HttpSession session, BeneficiaryStatusUpdateForm bsuform)
    {
        try
        {
            System.err.println("bsuform.getBeneficiaryId() is "+bsuform.getBeneficiaryId());
            DaoUtility util=new DaoUtility();
            if(bsuform.getBeneficiaryId() !=null && !bsuform.getBeneficiaryId().trim().equalsIgnoreCase("select"))
            {
                Ovc beneficiary=util.getChildEnrollmentDaoInstance().getOvc(bsuform.getBeneficiaryId());
                if(beneficiary !=null)
                {
                    bsuform.setDateOfEnrollment(DateManager.convertDateToString(beneficiary.getDateOfEnrollment(),DateManager.MM_DD_YYYY_SLASH));
                    bsuform.setLastHivStatus(beneficiary.getCurrentHivStatus());

                    bsuform.setSex(beneficiary.getSex());

                    bsuform.setChildNewHivStatus(beneficiary.getCurrentHivStatus());
                    bsuform.setDateOfNewHivStatus(DateManager.getMthDayYearStringDateFormat(beneficiary.getDateOfCurrentHivStatus(), 0));
                    //if(bsuform.getNewHivStatus()==AppConstant.HIV_UNKNOWN_NUM || dateOfNewStatus==null || dateOfNewStatus.equalsIgnoreCase(DateManager.DEFAULT_DATE))
                    //bsuform.setDateOfNewHivStatus(null);
                    bsuform.setEnrolledOnTreatment(beneficiary.getEnrolledOnTreatment());
                    bsuform.setDateEnrolledOnTreatment(DateManager.getMthDayYearStringDateFormat(beneficiary.getDateEnrolledOnTreatment(), 0));
                    //if(bsuform.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM || dateOfEnrolledOnTreatment==null || dateOfEnrolledOnTreatment.equalsIgnoreCase(DateManager.DEFAULT_DATE))
                    //bsuform.setDateEnrolledOnTreatment(null);

                    bsuform.setBirthCertificate(beneficiary.getCurrentBirthRegistrationStatus());
                    bsuform.setSchoolStatus(beneficiary.getCurrentSchoolStatus());
                    bsuform.setSchoolName(beneficiary.getSchoolName());
                    bsuform.setGrade(beneficiary.getSchoolGrade());
                    bsuform.setEnrolledInVocationalTraining(0);
                    bsuform.setNameOfVocationalTraining(null);
                    bsuform.setChildCurrentEnrollmentStatus(beneficiary.getCurrentEnrollmentStatus());
                    bsuform.setChildDateOfCurrentStatus(DateManager.convertDateToString(beneficiary.getDateOfCurrentEnrollmentStatus(),DateManager.MM_DD_YYYY_SLASH));
                    
                    if(beneficiary.getCurrentEnrollmentStatus() !=AppConstant.ACTIVE_NUM && beneficiary.getCurrentEnrollmentStatus() !=AppConstant.REENROLLED_NUM)
                    bsuform.setChildExitedFromProgram(AppConstant.YES_OPTION_NUM);
                    else
                    bsuform.setChildExitedFromProgram(AppConstant.NO_OPTION_NUM);
                    
                    bsuform.setChildExitStatus(beneficiary.getCurrentEnrollmentStatus());
                    bsuform.setDateChildExitedFromProgram(DateManager.convertDateToString(beneficiary.getDateOfCurrentEnrollmentStatus(),DateManager.MM_DD_YYYY_SLASH));
                    //bsuform.setBeneficiaryName(beneficiary.getFirstName()+" "+beneficiary.getSurname());
                    if(beneficiary.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                    {
                        System.err.println("beneficiary.getCurrentHivStatus() is positive "+beneficiary.getCurrentHivStatus());
                        bsuform.setHivTreatmentFacilityId(beneficiary.getHivTreatmentFacilityId());
                        bsuform.setChildTreatmentId(beneficiary.getTreatmentId());
                        setHIVStatusProperties(session,"false");
                        setEnrolledOnTreatmentControlStatus(session,beneficiary.getEnrolledOnTreatment());
                    }
                    else
                    {
                        System.err.println("beneficiary.getCurrentHivStatus() is not positive "+beneficiary.getCurrentHivStatus());
                        setHIVStatusProperties(session,"true");
                    }
                    //bsuform.setPhoneNumber(beneficiary.getPhoneNumber());
                    setWithdrawalStatusMessage(session,bsuform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
                }
                else
                {
                    setWithdrawalStatusMessage(session,bsuform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
                    bsuform=resetChildInfo(bsuform);
                }
            }
            else
            bsuform=resetChildInfo(bsuform);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private BeneficiaryStatusUpdateForm setAdultMemberDetails(HttpSession session, BeneficiaryStatusUpdateForm bsuform)
    {
        try
        {
            System.err.println("bsuform.getCaregiverId() is "+bsuform.getCaregiverId());
            DaoUtility util=new DaoUtility();
            AdultHouseholdMember beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(bsuform.getCaregiverId());
                        
                if(beneficiary !=null)
                {
                    bsuform.setDateOfEnrollment(DateManager.convertDateToString(beneficiary.getDateOfEnrollment(),DateManager.MM_DD_YYYY_SLASH));
                    bsuform.setCaregiverHivStatus(beneficiary.getCurrentHivStatus());
                    bsuform.setCaregiverAge(beneficiary.getCurrentAge());
                    bsuform.setCaregiverSex(beneficiary.getSex());
                    bsuform.setCaregiverPhone(beneficiary.getPhoneNumber());
                    bsuform.setCaregiverLastHivStatus(beneficiary.getCurrentHivStatus());
                    bsuform.setDateOfCaregiverLastHivStatus(DateManager.getMthDayYearStringDateFormat(beneficiary.getDateOfCurrentHivStatus(), 0));
                    bsuform.setCaregiverHivStatus(beneficiary.getCurrentHivStatus());
                    bsuform.setDateOfCaregiverHivStatus(DateManager.getMthDayYearStringDateFormat(beneficiary.getDateOfCurrentHivStatus(), 0));
                    //bsuform.setCaregiverEnrolledOnTreatment(beneficiary.getEnrolledOnTreatment());
                    bsuform.setCgiverCurrentEnrollmentStatus(beneficiary.getCurrentEnrollmentStatus());
                    bsuform.setCgiverDateOfCurrentStatus(DateManager.convertDateToString(beneficiary.getDateOfCurrentEnrollmentStatus(),DateManager.MM_DD_YYYY_SLASH));
                    if(beneficiary.getCurrentEnrollmentStatus() !=AppConstant.ACTIVE_NUM && beneficiary.getCurrentEnrollmentStatus() !=AppConstant.REENROLLED_NUM)
                    bsuform.setCaregiverExitedFromProgram(AppConstant.YES_OPTION_NUM);
                    else
                    bsuform.setCaregiverExitedFromProgram(AppConstant.NO_OPTION_NUM);
                    
                    bsuform.setCaregiverExitStatus(beneficiary.getCurrentEnrollmentStatus());
                    bsuform.setDateCaregiverExitedFromProgram(DateManager.convertDateToString(beneficiary.getDateOfCurrentEnrollmentStatus(),DateManager.MM_DD_YYYY_SLASH));
                    //bsuform.setBeneficiaryName(beneficiary.getFirstName()+" "+beneficiary.getSurname());
                    if(beneficiary.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                    {
                        bsuform.setCaregiverEnrolledOnTreatment(beneficiary.getEnrolledOnTreatment());
                        bsuform.setDateCaregiverEnrolledOnTreatment(DateManager.getMthDayYearStringDateFormat(beneficiary.getDateEnrolledOnTreatment(), 0));
                        bsuform.setFacilityCaregiverEnrolled(beneficiary.getHivTreatmentFacilityId());
                        bsuform.setCaregiverTreatmentId(beneficiary.getTreatmentId());
                        //System.err.println("beneficiary.getCurrentHivStatus() is positive "+beneficiary.getCurrentHivStatus());
                        //System.err.println("bsuform.getCaregiverEnrolledOnTreatment() is "+bsuform.getCaregiverEnrolledOnTreatment()+" and bsuform.getDateCaregiverEnrolledOnTreatment() is "+bsuform.getDateCaregiverEnrolledOnTreatment()+" and bsuform.getFacilityCaregiverEnrolled()"+bsuform.getFacilityCaregiverEnrolled());
                        setHIVStatusProperties(session,"false");
                        setEnrolledOnTreatmentControlStatus(session,beneficiary.getEnrolledOnTreatment());
                    }
                    else
                    {
                        //System.err.println("beneficiary.getCurrentHivStatus() is not positive "+beneficiary.getCurrentHivStatus());
                        setHIVStatusProperties(session,"true");
                    }
                    //bsuform.setPhoneNumber(beneficiary.getPhoneNumber());
                }
                else
                {
                    bsuform=resetCaregiverInfo(bsuform);
                }
            
            //else
            //bsuform=resetCaregiverInfo(bsuform);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return bsuform;
    }
    private void updateBeneficiaryCurrentEnrollmentStatus(BeneficiaryStatusUpdateForm bsuform)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            if(bsuform.getUpdateChildEnrollmentStatus()==1 && bsuform.getChildExitStatus()>0)
            {
                if(bsuform.getDateChildExitedFromProgram() !=null && bsuform.getDateChildExitedFromProgram().indexOf("/") !=-1)
                {
                    Date dateChildExitedFromProgram=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(bsuform.getDateChildExitedFromProgram()));
                    Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(bsuform.getBeneficiaryId());
                    if(ovc !=null && (ovc.getDateOfCurrentEnrollmentStatus().before(dateChildExitedFromProgram) || ovc.getDateOfCurrentEnrollmentStatus().equals(dateChildExitedFromProgram)))
                    {
                        int newEnrollmentStatus=bsuform.getChildExitStatus();
                        if(newEnrollmentStatus ==AppConstant.REENROLLED_NUM)
                        {
                            EnrollmentStatusManager esm=new EnrollmentStatusManager();
                            if(esm.isActiveInReportPeriod(ovc.getOvcId(), dateChildExitedFromProgram,AppConstant.OVC_TYPE_NUM))
                            newEnrollmentStatus=AppConstant.ACTIVE_NUM;
                        }
                        ovc.setCurrentEnrollmentStatus(newEnrollmentStatus);
                        ovc.setDateOfCurrentEnrollmentStatus(dateChildExitedFromProgram);
                        util.getChildEnrollmentDaoInstance().updateOvc(ovc, false, false);
                    }
                }
            }
            if(bsuform.getUpdateCaregiverEnrollmentStatus()==1 && bsuform.getCaregiverExitStatus()>0)
            {
                if(bsuform.getDateCaregiverExitedFromProgram() !=null && bsuform.getDateCaregiverExitedFromProgram().indexOf("/") !=-1)
                {
                    Date dateCaregiverExitedFromProgram=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(bsuform.getDateCaregiverExitedFromProgram()));
                    AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(bsuform.getCaregiverId());
                    if(ahm !=null && (ahm.getDateOfCurrentEnrollmentStatus().before(dateCaregiverExitedFromProgram) || ahm.getDateOfCurrentEnrollmentStatus().equals(dateCaregiverExitedFromProgram)))
                    {
                        int newEnrollmentStatus=bsuform.getCaregiverExitStatus();
                        if(newEnrollmentStatus ==AppConstant.REENROLLED_NUM)
                        {
                            EnrollmentStatusManager esm=new EnrollmentStatusManager();
                            if(esm.isActiveInReportPeriod(ahm.getBeneficiaryId(), dateCaregiverExitedFromProgram,AppConstant.CAREGIVER_TYPE_NUM))
                            newEnrollmentStatus=AppConstant.ACTIVE_NUM;
                        }
                        ahm.setCurrentEnrollmentStatus(newEnrollmentStatus);
                        ahm.setDateOfCurrentEnrollmentStatus(dateCaregiverExitedFromProgram);
                        util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                    }
                }
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private BeneficiaryStatusUpdateForm resetChildInfo(BeneficiaryStatusUpdateForm bsuform)
    {
        bsuform.setEnrolledOnTreatment(0);
        bsuform.setChildNewHivStatus(0);
        bsuform.setBirthCertificate(0);
        bsuform.setLastHivStatus(0);
        bsuform.setDateOfNewHivStatus(null);
        bsuform.setDateEnrolledOnTreatment(null);
        bsuform.setSex(null);
        bsuform.setHivTreatmentFacilityId("select");
        bsuform.setChildTreatmentId(null);
        bsuform.setSchoolStatus(0);
        bsuform.setSchoolName("select");
        bsuform.setEnrolledInVocationalTraining(0);
        bsuform.setNameOfVocationalTraining("select");
        bsuform.setChildExitStatus(0);
        bsuform.setChildExitedFromProgram(0);
        bsuform.setDateChildExitedFromProgram(null);
        
        return bsuform;
    }
    private BeneficiaryStatusUpdateForm resetCaregiverInfo(BeneficiaryStatusUpdateForm bsuform)
    {
        bsuform.setCaregiverEnrolledOnTreatment(0);
        bsuform.setCaregiverHivStatus(0);
        bsuform.setCaregiverAge(0);
        bsuform.setCaregiverLastHivStatus(0);
        bsuform.setCaregiverPhone(null);
        bsuform.setCaregiverSex(null);
        bsuform.setDateCaregiverEnrolledOnTreatment(null);
        bsuform.setDateOfCaregiverHivStatus(null);
        bsuform.setDateOfCaregiverLastHivStatus(null);
        bsuform.setFacilityCaregiverEnrolled("select");
        bsuform.setCaregiverTreatmentId(null);
        bsuform.setCaregiverExitedFromProgram(0);
        bsuform.setCaregiverExitStatus(0);
        bsuform.setDateCaregiverExitedFromProgram(null);
        return bsuform;
    }
    private void setHIVStatusProperties(HttpSession session,String disabled)
    {
        session.setAttribute("enrhivDisabled", disabled);
    }
    private BeneficiaryStatusUpdate getBeneficiaryStatusUpdate(BeneficiaryStatusUpdateForm bsuform,String userName)
    {
       Date dateChildEnrolledOnTreatment=DateManager.getDefaultStartDateInstance();
       
       if(bsuform.getDateEnrolledOnTreatment() !=null && bsuform.getDateEnrolledOnTreatment().indexOf("/") !=-1)
       dateChildEnrolledOnTreatment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(bsuform.getDateEnrolledOnTreatment()));
       
       BeneficiaryStatusUpdate hsm=new BeneficiaryStatusUpdate();
       hsm.setBeneficiaryId(getBeneficiaryId(bsuform.getBeneficiaryId()));
       hsm.setBeneficiaryType(bsuform.getBeneficiaryType());
       hsm.setDateCreated(DateManager.getCurrentDateInstance());
       hsm.setDateOfNewStatus(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(bsuform.getDateOfNewHivStatus())));
       hsm.setDateEnrolledOnTreatment(dateChildEnrolledOnTreatment);
       hsm.setEnrolledOnTreatment(bsuform.getEnrolledOnTreatment());
       if(bsuform.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
       hsm.setFacilityId(bsuform.getHivTreatmentFacilityId());
       else
       hsm.setFacilityId(null);
       hsm.setLastModifiedDate(DateManager.getCurrentDateInstance());
       hsm.setNewHivStatus(bsuform.getChildNewHivStatus());
       hsm.setBirthCertificate(bsuform.getBirthCertificate());
       hsm.setSchoolStatus(bsuform.getSchoolStatus());
       hsm.setSchoolName(bsuform.getSchoolName());
       hsm.setGrade(bsuform.getGrade());
       hsm.setEnrolledInVocationalTraining(bsuform.getEnrolledInVocationalTraining());
       hsm.setNameOfVocationalTraining(bsuform.getNameOfVocationalTraining());
       hsm.setChildTreatmentId(bsuform.getChildTreatmentId());
       hsm.setUpdateChildHivStatus(bsuform.getUpdateChildHivStatus());
       hsm.setUpdateChildBirthRegStatus(bsuform.getUpdateChildBirthRegAndSchoolStatus());
       hsm.setUpdateSchoolStatus(bsuform.getUpdateSchoolStatus());
       //Caregiver information starts here
       Date dateCaregiverEnrolledOnTreatment=DateManager.getDefaultStartDateInstance();
       
       
       hsm.setCaregiverId(bsuform.getCaregiverId());
       hsm.setCaregiverHivStatus(bsuform.getCaregiverHivStatus());
       hsm.setDateOfCaregiverHivStatus(DateManager.getDefaultStartDateInstance());
       if(bsuform.getDateOfCaregiverHivStatus() !=null)
       {
           hsm.setDateOfCaregiverHivStatus(DateManager.getDateInstanceFromMthDayYearFormat(bsuform.getDateOfCaregiverHivStatus()));
           System.err.println("hsm.getDateOfCaregiverHivStatus() is "+hsm.getDateOfCaregiverHivStatus());
       }
       hsm.setCaregiverEnrolledOnTreatment(bsuform.getCaregiverEnrolledOnTreatment());
       
       //if caregiver is HIV positive, set treatment related information
       if(hsm.getCaregiverHivStatus()==AppConstant.HIV_POSITIVE_NUM)
       {
           hsm.setCaregiverEnrolledOnTreatment(bsuform.getCaregiverEnrolledOnTreatment());
           if(hsm.getCaregiverEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
           {
               if(bsuform.getDateCaregiverEnrolledOnTreatment() !=null && bsuform.getDateCaregiverEnrolledOnTreatment().indexOf("/") !=-1)
               dateCaregiverEnrolledOnTreatment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(bsuform.getDateCaregiverEnrolledOnTreatment()));
               hsm.setDateCaregiverEnrolledOnTreatment(dateCaregiverEnrolledOnTreatment);
               hsm.setCaregiverTreatmentId(bsuform.getCaregiverTreatmentId());
               hsm.setFacilityCaregiverEnrolled(bsuform.getFacilityCaregiverEnrolled());
           }
       }
       else
       {
           //Caregiver is not HIV positive, reset treatment related parameters to default values
           hsm.setCaregiverEnrolledOnTreatment(0);
           hsm.setCaregiverTreatmentId(null);
           hsm.setFacilityCaregiverEnrolled(null);
       }
       
       /*hsm.setDateCaregiverEnrolledOnTreatment(dateCaregiverEnrolledOnTreatment);
       hsm.setFacilityCaregiverEnrolled(bsuform.getFacilityCaregiverEnrolled());
       hsm.setCaregiverTreatmentId(bsuform.getCaregiverTreatmentId());*/
       //This is not a hiv status but an indicator for the process to know if the hiv 
       //status of beneficiary is to be updated or not in this transaction
       hsm.setUpdateCaregiverHivStatus(bsuform.getUpdateCaregiverHivStatus());
       
       
       hsm.setChildExitStatus(bsuform.getChildExitStatus());
       hsm.setChildExitedFromProgram(bsuform.getChildExitedFromProgram());
       hsm.setDateChildExitedFromProgram(DateManager.getDateInstance(DateManager.DEFAULT_DATE));
       if(bsuform.getChildExitedFromProgram()==1 && bsuform.getChildExitStatus()>0)
        {
            if(bsuform.getDateChildExitedFromProgram() !=null && bsuform.getDateChildExitedFromProgram().indexOf("/") !=-1)
            {
                hsm.setDateChildExitedFromProgram(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(bsuform.getDateChildExitedFromProgram())));
            }
        }
       hsm.setCaregiverExitStatus(bsuform.getCaregiverExitStatus());
       hsm.setCaregiverExitedFromProgram(bsuform.getCaregiverExitedFromProgram());
       hsm.setDateCaregiverExitedFromProgram(DateManager.getDateInstance(DateManager.DEFAULT_DATE));
       
       if(bsuform.getCaregiverExitedFromProgram()==1 && bsuform.getCaregiverExitStatus()>0)
        {
            if(bsuform.getDateCaregiverExitedFromProgram() !=null && bsuform.getDateCaregiverExitedFromProgram().indexOf("/") !=-1)
            {
                hsm.setDateCaregiverExitedFromProgram(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(bsuform.getDateCaregiverExitedFromProgram())));
            }
        }
       hsm.setRecordedBy(userName);
       //System.err.println("hsm.getCaregiverEnrolledOnTreatment() in BeneficiaryStatusUpdateAction is "+hsm.getCaregiverEnrolledOnTreatment());
       //System.err.println("hsm.getDateCaregiverEnrolledOnTreatment() in BeneficiaryStatusUpdateAction is "+hsm.getDateCaregiverEnrolledOnTreatment());
       return hsm;
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("serviceSaveDisabled", saveDisabled);
        session.setAttribute("serviceModifyDisabled", modifyDisabled);
    }
    private void setEnrolledOnTreatmentControlStatus(HttpSession session,int enrolledOnTreatment)
    {
        if(enrolledOnTreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
        session.setAttribute("enrOnTreatmentDisabled", "false");
        else
        session.setAttribute("enrOnTreatmentDisabled", "true");
    }
    private String getBeneficiaryId(String beneficiaryFormId)
    {
        String beneficiaryId=beneficiaryFormId;
        if(beneficiaryFormId !=null && beneficiaryFormId.indexOf("_") !=-1)
        {
            String[] idArray=beneficiaryFormId.split("_");
            beneficiaryId=idArray[0];
        }
        return beneficiaryId;
    }
    private void generateSchoolList(HttpSession session,BeneficiaryStatusUpdateForm bsuform)
    {
        try
        {
            List list=new ArrayList();
            DaoUtility daoutil=new DaoUtility();
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setLevel2OuId(bsuform.getLevel2OuId());
            rpt.setLevel3OuId(bsuform.getLevel3OuId());
            list=daoutil.getSchoolDaoInstance().getSchoolsByOrgUnit(rpt);
            if(list==null)
            list=new ArrayList();
            session.setAttribute("schoolListByLevel2Ou", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void generateSchoolGradeList(HttpSession session)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getSchoolGradeDaoInstance().getAllSchoolGrades();
            if(list==null)
            list=new ArrayList();
            session.setAttribute("schoolGradeList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void loadCaregiversPerHousehold(HttpSession session, String hhUniqueId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getAdultHouseholdMemberDaoInstance().getCaregiversPerHousehold(hhUniqueId);
        if(list==null)
        list=new ArrayList();
        session.setAttribute("listOfAdultMembersPerHouseholdInOSU", list);
    }
    private void loadChildrenPerHousehold(HttpSession session, String hhUniqueId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);
        if(list==null)
        list=new ArrayList();
        session.setAttribute("listOfChildrenPerHouseholdInOSU", list);
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
        session.setAttribute("hsmBeneficiaryList", beneficiaryList);
    }
}
