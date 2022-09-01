/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.careandsupport.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.ObjectInteractionManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.CareAndSupportChecklist;
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
public class CareAndSupportAction extends org.apache.struts.action.Action {

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
        CareAndSupportForm casform=(CareAndSupportForm)form;
        String moduleName="HIV Care and Support";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=casform.getActionName();
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
        String level2OuId=casform.getLevel2OuId();
        String level3OuId=casform.getLevel3OuId();
        int hhSerialNo=casform.getHhSerialNo();
        String hhUniqueId=casform.getHhUniqueId();
        String cboId=casform.getCboId();
        String beneficiaryId=casform.getBeneficiaryId();
        String dateOfAssessment=casform.getDateOfAssessment();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,cboId);
        //session.setAttribute("hivStatusForRiskAssessment", HivPropertiesManager.getHivStatusWithoutPositive());
        loadfacility(session,level2OuId,null);
        setOvcPerHouseholdList(session, casform.getHhUniqueId());
        int beneficiaryType=getBeneficiaryType(beneficiaryId);
        if(beneficiaryType==0 || beneficiaryType==3)
        disableChildSpecificQuestions(session,"false");
        else
        disableChildSpecificQuestions(session,"true");
        
        setButtonState(session,"false","true");
        setBeneficiaryDetails(casform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        AccessManager acm=new AccessManager();
        //setWithdrawalStatusMessage(session,casform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.TRUEVALUE,requiredAction);
        requiredAction=acm.getActionName(requiredAction, user);
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            //CipherAESExample.showEncryptedResult("SiakaMomoh Mathematician Abejukolo");
            //CipherSample.testCipher("Dan Asabe");
            //set null beneficiaryid to the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            casform.reset(mapping, request);
            setButtonState(session,"false","true");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, casform.getCboId());
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
            String hhName=null;
            setBeneficiaryList(session, hhUniqueId);
            //setOvcPerHouseholdList(session, hhUniqueId);
            request.setAttribute("hhName", hhName);
            casform.reset(mapping, request);
            casform.setHhUniqueId(hhUniqueId);
            casform.setHhSerialNo(hhSerialNo);
            casform=setOrganizationUnitProperties(session, hhUniqueId,casform,userName);
            casform.setCboId(cboId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("beneficiaryDetails"))
        {
            UniqueIdManager uim=new UniqueIdManager();
            casform.reset(mapping, request);
            casform.setHhSerialNo(uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId));
            casform.setHhUniqueId(hhUniqueId);
            casform.setCboId(cboId);
            casform.setBeneficiaryId(beneficiaryId);
            setBeneficiaryDetails(casform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            //setWithdrawalStatusMessage(session,casform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.TRUEVALUE,requiredAction);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("assessmentDetails"))
        {
            AppUtility appUtil=new AppUtility();//UniqueIdManager uim=new UniqueIdManager();
            Date ddateOfAssessment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(dateOfAssessment));
            CareAndSupportChecklist casc=util.getCareAndSupportChecklistDaoInstance()
            .getCareAndSupportChecklist(casform.getBeneficiaryId(), ddateOfAssessment);
            casform.reset(mapping, request);
            
            if(casc !=null)
            {
                beneficiaryId=casc.getBeneficiaryId();
                Beneficiary beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
                if(beneficiary==null)
                beneficiary=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
                if(beneficiary !=null)
                {
                    casform.setDateEnrolledOnTreatment(DateManager.convertDateToString(beneficiary.getDateEnrolledOnTreatment(), DateManager.DD_MM_YYYY_SLASH));
                }
                casform.setChildHasFever(casc.getChildHasFever());
                casform.setChildHasNightSweat(casc.getChildHasNightSweat());
                casform.setChildHasSwelling(casc.getChildHasSwelling());
                casform.setChildLossinWeight(casc.getChildLossinWeight());
                casform.setCoughSymptom(casc.getCoughSymptom());
                casform.setDateOfAssessment(dateOfAssessment);

                casform.setDateOfNextAppointment(DateManager.getMthDayYearStringDateFormat(casc.getDateOfNextAppointment(),1));
                casform.setDateOfViralLoadSampleCollection(DateManager.getMthDayYearStringDateFormat(casc.getDateOfViralLoadSampleCollection(),1));

                casform.setEnrolledOnTreatment(casc.getEnrolledOnTreatment());
                casform.setFacilityId(casc.getFacilityId());
                casform.setMissedARVsRecently(casc.getMissedARVsRecently());
                casform.setMonthsOfTransportationSupport(casc.getMonthsOfTransportationSupport());
                casform.setBeneficiaryId(casc.getBeneficiaryId());
                casform.setPickedUpMedication(casc.getPickedUpMedication());
                casform.setDateOfLastPickup(DateManager.convertDateToString(casc.getDateOfLastDrugPickup(),DateManager.MM_DD_YYYY_SLASH));
                casform.setNumberOfDaysOfRefill(casc.getNumberOfDaysOfRefill());
                casform.setReasonViralLoadNotDone(casc.getReasonViralLoadNotDone());
                casform.setReasonsPeopleSkipARV(appUtil.splitString(casc.getReasonsPeopleSkipARV(),","));
                casform.setReceivedTransportationSupport(casc.getReceivedTransportationSupport());
                casform.setRecordId(casc.getRecordId());
                casform.setSoresRashPainExperience(casc.getSoresRashPainExperience());
                casform.setViralLoadResult(casc.getViralLoadResult());
                casform.setViralLoadResultKnown(casc.getViralLoadResultKnown());
                casform.setDateOfViralLoadResult(DateManager.convertDateToString(casc.getDateOfViralLoadResult(),DateManager.MM_DD_YYYY_SLASH));
                casform.setViralLoadTestDone(casc.getViralLoadTestDone());
                casform.setVolunteerName(casc.getVolunteerName());
                setButtonState(session,"true","false");
                setBeneficiaryDetails(casform,session,AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
            }
            else
            {
                setBeneficiaryDetails(casform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
            casform.setHhSerialNo(hhSerialNo);
            casform.setHhUniqueId(hhUniqueId);
            casform.setBeneficiaryId(beneficiaryId);
            casform.setDateOfAssessment(dateOfAssessment);
            casform.setCboId(cboId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            CareAndSupportChecklist casc=getCareAndSupportChecklist(casform, userName);
            util.getCareAndSupportChecklistDaoInstance().saveCareAndSupportChecklist(casc);
            ObjectInteractionManager oim=new ObjectInteractionManager();
            oim.updateBeneficiaryCareAndSupportInformation(casc);
            saveUserActivity(userName,moduleName,"Added new Care and Support record with beneficiary Id "+casc.getBeneficiaryId());
            setButtonState(session,"false","true");
            casform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            CareAndSupportChecklist casc=getCareAndSupportChecklist(casform, userName);
            util.getCareAndSupportChecklistDaoInstance().updateCareAndSupportChecklist(casc);
            saveUserActivity(userName,moduleName,"Updated Care and Support with beneficiary Id "
                    +casc.getBeneficiaryId()+" and date of assessment "+casc.getDateOfAssessment());
            setButtonState(session,"false","true");
            ObjectInteractionManager oim=new ObjectInteractionManager();
            oim.updateBeneficiaryCareAndSupportInformation(casc);
            casform.reset(mapping, request);
        }
        
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            CareAndSupportChecklist casc=getCareAndSupportChecklist(casform, userName);
            util.getCareAndSupportChecklistDaoInstance().markForDelete(casc);
            saveUserActivity(userName,moduleName,"Marked Care and Support record with beneficiary Id "
                    +casc.getBeneficiaryId()+" and date of assessment "+casc.getDateOfAssessment());
           setButtonState(session,"false","true");
           casform.reset(mapping, request);
        }
        return mapping.findForward(SUCCESS);
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="casWithdrawnMessage";
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
    private CareAndSupportChecklist getCareAndSupportChecklist(CareAndSupportForm form,String userName)
    {
        AppUtility appUtil=new AppUtility();
        CareAndSupportChecklist casc=new CareAndSupportChecklist();
        //casc.set
        casc.setChildHasFever(form.getChildHasFever());
        casc.setChildHasNightSweat(form.getChildHasNightSweat());
        casc.setChildHasSwelling(form.getChildHasSwelling());
        casc.setChildLossinWeight(form.getChildLossinWeight());
        casc.setCoughSymptom(form.getCoughSymptom());
        casc.setDateCreated(DateManager.getCurrentDateInstance());
        casc.setDateOfAssessment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfAssessment())));
        casc.setLastModifiedDate(DateManager.getCurrentDateInstance());
        casc.setDateOfNextAppointment(DateManager.getDefaultStartDateInstance());
        if(form.getDateOfNextAppointment() !=null && form.getDateOfNextAppointment().indexOf("/") !=-1)
        casc.setDateOfNextAppointment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfNextAppointment())));
        casc.setDateOfViralLoadSampleCollection(DateManager.getDefaultStartDateInstance());
        if(form.getDateOfViralLoadSampleCollection() !=null && form.getDateOfViralLoadSampleCollection().indexOf("/") !=-1)
        casc.setDateOfViralLoadSampleCollection(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfViralLoadSampleCollection())));
        
        casc.setDateOfViralLoadResult(DateManager.getDefaultStartDateInstance());
        if(form.getDateOfViralLoadResult() !=null && form.getDateOfViralLoadResult().indexOf("/") !=-1)
        casc.setDateOfViralLoadResult(DateManager.getDateInstanceFromMthDayYearFormat(form.getDateOfViralLoadResult()));
        
        casc.setDateOfLastDrugPickup(DateManager.getDefaultStartDateInstance());
        if(form.getDateOfLastPickup() !=null && form.getDateOfLastPickup().indexOf("/") !=-1)
        casc.setDateOfLastDrugPickup(DateManager.getDateInstanceFromMthDayYearFormat(form.getDateOfLastPickup()));
        casc.setDateEnrolledOnTreatment(DateManager.getDateInstanceFromMthDayYearFormat(form.getDateEnrolledOnTreatment()));
        casc.setEnrolledOnTreatment(form.getEnrolledOnTreatment());
        casc.setFacilityId(form.getFacilityId());
        casc.setMissedARVsRecently(form.getMissedARVsRecently());
        casc.setNumberOfDaysOfRefill(form.getNumberOfDaysOfRefill());
        casc.setMonthsOfTransportationSupport(form.getMonthsOfTransportationSupport());
        casc.setBeneficiaryId(form.getBeneficiaryId());
        casc.setPickedUpMedication(form.getPickedUpMedication());
        casc.setReasonViralLoadNotDone(form.getReasonViralLoadNotDone());
        casc.setReasonsPeopleSkipARV(appUtil.concatStr(form.getReasonsPeopleSkipARV(),null));
        casc.setReceivedTransportationSupport(form.getReceivedTransportationSupport());
        casc.setRecordId(form.getRecordId());
        casc.setRecordedBy(userName);
        casc.setSoresRashPainExperience(form.getSoresRashPainExperience());
        casc.setViralLoadResult(form.getViralLoadResult());
        casc.setViralLoadResultKnown(form.getViralLoadResultKnown());
        casc.setViralLoadTestDone(form.getViralLoadTestDone());
        casc.setVolunteerName(form.getVolunteerName());
        return casc;
    }
    private CareAndSupportForm getCareAndSupportForm(CareAndSupportForm form,String userName)
    {
        AppUtility appUtil=new AppUtility();
        CareAndSupportChecklist casc=new CareAndSupportChecklist();
        form.setChildHasFever(casc.getChildHasFever());
        form.setChildHasNightSweat(casc.getChildHasNightSweat());
        form.setChildHasSwelling(casc.getChildHasSwelling());
        form.setChildLossinWeight(casc.getChildLossinWeight());
        form.setCoughSymptom(casc.getCoughSymptom());
        form.setDateOfAssessment(DateManager.getMthDayYearStringDateFormat(casc.getDateOfAssessment(),1));
        form.setDateOfNextAppointment(DateManager.getMthDayYearStringDateFormat(casc.getDateOfNextAppointment(),0));
        form.setDateOfViralLoadSampleCollection(DateManager.getMthDayYearStringDateFormat(casc.getDateOfViralLoadSampleCollection(),0));
        //form.setDateEnrolledOnTreatment(DateManager.convertDateToString(casc.getDateEnrolledOnTreatment(), DateManager.MM_DD_YYYY_SLASH));
        form.setEnrolledOnTreatment(casc.getEnrolledOnTreatment());
        form.setFacilityId(casc.getFacilityId());
        form.setMissedARVsRecently(casc.getMissedARVsRecently());
        form.setNumberOfDaysOfRefill(casc.getNumberOfDaysOfRefill());
        form.setMonthsOfTransportationSupport(casc.getMonthsOfTransportationSupport());
        form.setBeneficiaryId(casc.getBeneficiaryId());
        form.setPickedUpMedication(casc.getPickedUpMedication());
        form.setDateOfLastPickup(DateManager.convertDateToString(casc.getDateOfLastDrugPickup(),DateManager.MM_DD_YYYY_SLASH));
        form.setReasonViralLoadNotDone(casc.getReasonViralLoadNotDone());
        form.setReasonsPeopleSkipARV(appUtil.splitString(casc.getReasonsPeopleSkipARV(),","));
        form.setReceivedTransportationSupport(casc.getReceivedTransportationSupport());
        form.setRecordId(casc.getRecordId());
        form.setSoresRashPainExperience(casc.getSoresRashPainExperience());
        form.setViralLoadResult(casc.getViralLoadResult());
        form.setViralLoadResultKnown(casc.getViralLoadResultKnown());
        form.setViralLoadTestDone(casc.getViralLoadTestDone());
        form.setVolunteerName(casc.getVolunteerName());
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
            session.setAttribute("cascOvcPerHouseholdList", list);
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
            Ovc ovc=null;
            for(Object obj:childrenList)
            {
                ovc=(Ovc)obj;
                if(ovc.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                beneficiaryList.add(ovc);
            }
           
        }
        List adultList=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMembersPerHousehold(hhUniqueId);
        if(adultList !=null)
        {
            AdultHouseholdMember ahm=null;
            for(Object obj:adultList)
            {
                ahm=(AdultHouseholdMember)obj;
                if(ahm.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                beneficiaryList.add(ahm);
            }
        }
        session.setAttribute("cascBeneficiaryList", beneficiaryList);
    }
    private void setBeneficiaryDetails(CareAndSupportForm cscform,HttpSession session,String saveBtnDisabled,String modifyBtnDisabled)
    {
        try
        {
            System.err.println("cscform.getBeneficiaryId() is "+cscform.getBeneficiaryId());
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getChildEnrollmentDaoInstance().getOvc(cscform.getBeneficiaryId());
            if(beneficiary==null)
            {
                beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(cscform.getBeneficiaryId());
            }
            
            if(beneficiary !=null)
            {
                cscform.setBeneficiaryId(beneficiary.getBeneficiaryId());
                cscform.setDateOfEnrollment(DateManager.convertDateToString(beneficiary.getDateOfEnrollment(),DateManager.MM_DD_YYYY_SLASH));
                cscform.setHivStatus(beneficiary.getCurrentHivStatus());
                cscform.setSex(beneficiary.getSex());
                cscform.setPhoneNumber(beneficiary.getPhoneNumber());
                
                if(beneficiary.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
                {
                    cscform.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                    cscform.setDateEnrolledOnTreatment(DateManager.convertDateToString(beneficiary.getDateEnrolledOnTreatment(), DateManager.MM_DD_YYYY_SLASH));
                    session.setAttribute("casHealthFacilityDisabled", "false");
                    session.setAttribute("casOnTreatmentQuestionsDisabled", "false");
                    session.setAttribute("casDateEnrolledOnTreatmentDisabled", "false");
                }
                else
                {
                    session.setAttribute("casHealthFacilityDisabled", "true");
                    session.setAttribute("casOnTreatmentQuestionsDisabled", "true");
                    session.setAttribute("casDateEnrolledOnTreatmentDisabled", "true");
                }
                setWithdrawalStatusMessage(session,cscform.getBeneficiaryId(),saveBtnDisabled,modifyBtnDisabled);
                //setButtonState(session,"true","false");
            }
            else
            {
                setWithdrawalStatusMessage(session,cscform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private int getBeneficiaryType(String beneficiaryId)
    {
        int beneficiaryType=0;
        try
        {
            if(beneficiaryId !=null)
            {
                DaoUtility util=new DaoUtility();
                Beneficiary beneficiary=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
                if(beneficiary !=null)
                beneficiaryType=3;
                else
                {
                    beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
                    if(beneficiary !=null)
                    beneficiaryType=2;
                } 
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return beneficiaryType;
    }
    private void setOvcDetails(CareAndSupportForm casform,HttpSession session)
    {
        try
        {
            System.err.println("casform.getBeneficiaryId() is "+casform.getBeneficiaryId());
            DaoUtility util=new DaoUtility();
            String ovcId=casform.getBeneficiaryId();
            Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
            if(ovc !=null)
            {
                casform.setBeneficiaryId(ovc.getBeneficiaryId());
                casform.setDateOfEnrollment(DateManager.convertDateToString(ovc.getDateOfEnrollment(),"MM/dd/yyyy"));
                casform.setHivStatus(ovc.getCurrentHivStatus());
                casform.setSex(ovc.getSex());
                casform.setPhoneNumber(ovc.getPhoneNumber());
                
                if(ovc.getCurrentHivStatus()==1)
                {
                    setButtonState(session,"true","true");
                    
                }
                else if(ovc.getCurrentHivStatus()==2)
                {
                                        
                }
                else if(ovc.getCurrentHivStatus()==3 || ovc.getCurrentHivStatus()==4)
                {
                    
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private CareAndSupportForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,CareAndSupportForm form,String userName) throws Exception
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
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("cascSaveDisabled", saveDisabled);
        session.setAttribute("cascModifyDisabled", modifyDisabled);
    }
    public void disableChildSpecificQuestions(HttpSession session,String disabled)
    {
        session.setAttribute("childSpecificQuestionDisabled", disabled);
    }
}
