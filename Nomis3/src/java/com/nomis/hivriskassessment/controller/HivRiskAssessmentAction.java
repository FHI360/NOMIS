/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.hivriskassessment.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.DatasetSetting;
import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatasetManager;
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
public class HivRiskAssessmentAction extends org.apache.struts.action.Action {

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
        HivRiskAssessmentForm hracform=(HivRiskAssessmentForm)form;  
        String moduleName="HIV Risk assessment";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=hracform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        DatasetSetting dsts=util.getDatasetSettingDaoInstance().getDatasetSettingByModuleId(DatasetManager.getHivRiskAsmtModuleId());
        if(dsts !=null && dsts.getDatasetId().equalsIgnoreCase(DatasetManager.getNatHivRiskAsmtDatasetId()))
        {
            return mapping.findForward("NationalHivRiskAssessmentForm");
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
        String level3OuId=hracform.getLevel3OuId();
        String hhUniqueId=hracform.getHhUniqueId();
        String ovcId=hracform.getOvcId();
        String formDateOfAssessment=hracform.getDateOfAssessment();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,hracform.getCboId());
        session.setAttribute("hivStatusForRiskAssessment", HivPropertiesManager.getHivStatusWithoutPositive());
        session.setAttribute("riskAssessmentCurrentHivStatus", HivPropertiesManager.getAllHivStatusExceptPositive());
        session.setAttribute("allHivStatus", HivPropertiesManager.getAllHivStatus());
        setOvcPerHouseholdList(session, hracform.getHhUniqueId());
        setButtonState(session,"false","true");
        setOvcDetails(hracform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        AccessManager acm=new AccessManager();
        requiredAction=acm.getActionName(requiredAction, user);
        
        setAdolescentFieldStatus(session, ovcId, DateManager.processMthDayYearToMysqlFormat(formDateOfAssessment));
                
        if(requiredAction==null)
        {
            //beneficiaryid is set to null in the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            hracform.reset(mapping, request);
            setButtonState(session,"false","true");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, hracform.getCboId());
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
            String hhName=null;//hracform.getHhName();
            int hhSerialNo=hracform.getHhSerialNo();
            setOvcPerHouseholdList(session, hhUniqueId);
            request.setAttribute("hhName", hhName);
            hracform.reset(mapping, request);
            hracform.setHhUniqueId(hhUniqueId);
            hracform.setHhSerialNo(hhSerialNo);
            //hracform.setHhName(hhName);
            hracform=setOrganizationUnitProperties(session, hhUniqueId,hracform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("childDetails"))
        {
            UniqueIdManager uim=new UniqueIdManager();
            //String ovcId=hracform.getOvcId();
            hracform.reset(mapping, request);
            hracform.setHhSerialNo(uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId));
            hracform.setHhUniqueId(hhUniqueId);
            hracform.setOvcId(ovcId);
            setOvcDetails(hracform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("childTestedQuestion"))
        {
            
            //disableAdolescentControls(session,ovcId);
            if(hracform.getChildTestedQuestion()==1)
            {
                //setButtonState(session,"false","true");
                disableFieldset1Controls(session,"true");
                disableChildAtRiskControl(session, "false");
                disableHivParentControl(session,"true");
                //hracform.setChildAtRiskQuestion(AppConstant.CHILD_NOT_AT_RISK_NUM);
                //disableAdolescentFields(session,"true");
                
                //form=getFormWithDefaultValues(hrac,form,session);
            }
            else
            {
                //setButtonState(session,"false","true");
                disableFieldset1Controls(session,"false");
                disableHivParentControl(session,"false");
                hracform.setChildAtRiskQuestion(AppConstant.CHILD_AT_RISK_NUM);
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("hivParentQuestion"))
        {
            
            //disableAdolescentControls(session,ovcId);
            if(hracform.getHivParentQuestion()==1)
            {
                //setButtonState(session,"false","true");
                //disableFieldset1Controls(session,"true");
                disableHivParentControl(session,"false");
                disableChildAtRiskControl(session, "false");
                hracform.setChildAtRiskQuestion(AppConstant.CHILD_AT_RISK_NUM);
                
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("assessmentDetails"))
        {
            UniqueIdManager uim=new UniqueIdManager();
            String hracformDateOfAssessment=hracform.getDateOfAssessment();
            String strDateOfAssessment=DateManager.processMthDayYearToMysqlFormat(hracformDateOfAssessment);
            Date dateOfAssessment=DateManager.getDateInstance(strDateOfAssessment);
            ovcId=hracform.getOvcId();
            
            int hhSerialNo=uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId);
            AgeObject ageObj=getAgeObjectWithAgeAtAssessment(ovcId,strDateOfAssessment);
            HivRiskAssessment hrac=util.getHivRiskAssessmentDaoInstance().getHivRiskAssessment(ovcId, dateOfAssessment);
            hracform.reset(mapping, request);
            hracform.setHhSerialNo(hhSerialNo);
            hracform.setHhUniqueId(hhUniqueId);
            hracform.setAgeAtAssessment(ageObj.getAge());
            
            if(hrac !=null)
            {
                hracform.setOvcId(ovcId);
                hracform=getFilledHivRiskAssessmentForm(hrac,hracform);
                if(hrac.getChildTestedQuestion()==1 || hrac.getHivParentQuestion()==1)
                {
                    disableChildTestedControl(session,"false");
                    disableFieldset1Controls(session,"true");
                    //disableAdolescentControls(session,ovcId);
                }
                else
                {
                    //Enable data entry controls to allow modification
                    disableChildTestedControl(session,"false");
                    disableFieldset1Controls(session,"false");
                }
                setOvcDetails(hracform,session,AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
                //setButtonState(session,"true","false");
            }
            else
            {
                hracform.setOvcId(ovcId);
                hracform.setHhUniqueId(hhUniqueId);
                Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
                if(ovc !=null)
                {
                    //if OVC is positive currently, check if this assessment was done before the child became positive
                    if(ovc.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                    {
                        if(ovc.getDateOfCurrentHivStatus().before(dateOfAssessment))
                        //setOvcDetails(hracform,session);
                        hracform.setDateOfAssessment(hracformDateOfAssessment);
                        disableChildTestedControl(session,"true");
                        //disableAdolescentControls(session,ovcId);
                        disableFieldset1Controls(session,"true");
                        setButtonState(session,"true","true");
                    }
                    else
                    {
                        //setOvcDetails(hracform,session);
                        hracform.setDateOfAssessment(hracformDateOfAssessment);
                        disableChildTestedControl(session,"false");
                        //disableAdolescentControls(session,ovcId);
                        disableFieldset1Controls(session,"false");
                        setButtonState(session,"false","true");
                    }
                }
                setOvcDetails(hracform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            //HivRiskAssessmentManager hram=new HivRiskAssessmentManager();
            HivRiskAssessment hra=getHivRiskAssessment(hracform,userName);
            //System.err.println("hra.getChildAtRiskQuestion() in saveHivRiskAssessment(HivRiskAssessment hra) is "+hra.getChildAtRiskQuestion());
            //hra=hram.getHivRiskAssessmentWithAtRiskStatus(hra);
            util.getHivRiskAssessmentDaoInstance().saveHivRiskAssessment(hra);
            saveUserActivity(userName,moduleName,"Saved new HIV Risk assessment record for child with Id "+hra.getOvcId());
            hracform.reset(mapping, request);
            setButtonState(session,"false","true");
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            //HivRiskAssessmentManager hram=new HivRiskAssessmentManager();
            HivRiskAssessment hra=getHivRiskAssessment(hracform,userName);
            //hra=hram.getHivRiskAssessmentWithAtRiskStatus(hra);
            util.getHivRiskAssessmentDaoInstance().updateHivRiskAssessment(getHivRiskAssessment(hracform,userName));
            saveUserActivity(userName,moduleName,"Modified Risk assessment record for child with Id "+hra.getOvcId());
            hracform.reset(mapping, request);
            setButtonState(session,"false","true");
        }
        else if(requiredAction.equalsIgnoreCase("markForDelete"))
        {
            HivRiskAssessment hra=getHivRiskAssessment(hracform,userName);
            util.getHivRiskAssessmentDaoInstance().markForDelete(getHivRiskAssessment(hracform,userName));
            saveUserActivity(userName,moduleName,"Marked Risk assessment record for child with Id "+hra.getOvcId()+" for delete");
            hracform.reset(mapping, request);
            setButtonState(session,"false","true");
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            HivRiskAssessment hra=getHivRiskAssessment(hracform,userName);
            util.getHivRiskAssessmentDaoInstance().markForDelete(getHivRiskAssessment(hracform,userName));
            saveUserActivity(userName,moduleName,"Requested Risk assessment record for child with Id "+hra.getOvcId()+" be deleted");
            hracform.reset(mapping, request);
            setButtonState(session,"false","true");
        }
        return mapping.findForward(SUCCESS);
    }
    private void setAdolescentFieldStatus(HttpSession session, String ovcId, String strDateOfAssessment) throws Exception
    {
        disableAdolescentFields(session,"false");
        if(strDateOfAssessment !=null && strDateOfAssessment.indexOf("-") !=-1)
        {
            AgeObject ageObj=getAgeObjectWithAgeAtAssessment(ovcId,strDateOfAssessment);
            if(ageObj.getAgeUnit()>0)
            {
                if(ageObj.getAge() <15)
                {
                    disableAdolescentFields(session,"true");
                }
            }
            System.err.println("ageObj.getAge() is "+ageObj.getAge()+" and age unit is "+ageObj.getAgeUnit());
        }
    }
    public AgeObject getAgeObjectWithAgeAtAssessment(String beneficiaryId,String dateOfAssessment) throws Exception
    {
        AgeObject ageObj=new AgeObject();
        DaoUtility util=new DaoUtility();
        AppManager appManager=new AppManager();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
        if(ovc !=null)
        {
            ageObj=appManager.getAgeAtService(DateManager.convertDateToString(ovc.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT), dateOfAssessment,ovc.getAgeAtBaseline(), ovc.getAgeUnitAtBaseline());
        }
        return ageObj;
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="hraWithdrawnMessage";
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
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private HivRiskAssessmentForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,HivRiskAssessmentForm form,String userName) throws Exception
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
            List ovcListForRiskAssessment=new ArrayList();
            Ovc ovc=null;
            DaoUtility util=new DaoUtility();
            List list=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);
            if(list !=null)
            {
                for(Object obj:list)
                {
                    ovc=(Ovc)obj;
                    if(ovc.getBaselineHivStatus()!=AppConstant.HIV_POSITIVE_NUM)
                    ovcListForRiskAssessment.add(ovc);
                }
            }
            
            session.setAttribute("hracOvcPerHouseholdList", ovcListForRiskAssessment);
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
    private void setOvcDetails(HivRiskAssessmentForm hracform,HttpSession session,String saveBtnDisabled,String modifyBtnDisabled)
    {
        try
        {
            System.err.println("hracform.getOvcId() is "+hracform.getOvcId());
            DaoUtility util=new DaoUtility();
            String ovcId=hracform.getOvcId();
            Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
            if(ovc !=null)
            {
                //System.err.println("ovc.getBaselineHivStatus() is "+ovc.getBaselineHivStatus());
                //System.err.println("ovc.getCurrentHivStatus() is "+ovc.getCurrentHivStatus());
                hracform.setOvcId(ovc.getOvcId());
                hracform.setDateOfEnrollment(DateManager.convertDateToString(ovc.getDateOfEnrollment(),"MM/dd/yyyy"));
                hracform.setBaselineHivStatus(ovc.getBaselineHivStatus());
                hracform.setCurrentHivStatus(ovc.getCurrentHivStatus());
                hracform.setSex(ovc.getSex());
                hracform.setPhoneNumber(ovc.getPhoneNumber());
                hracform.setCurrentAge(AppManager.getCurrentAge(DateManager.convertDateToString(ovc.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT), ovc.getAgeAtBaseline()));//ovc.getCurrentAge());
                //disableAdolescentControls(session,ovcId);
                if(hracform.getHivStatusQuestion()==0)
                {
                    if(ovc.getCurrentHivStatus()==1)
                    {
                        setButtonState(session,"true","true");
                        disableChildTestedControl(session,"true");
                        //disableAdolescentControls(session,"true");
                        disableFieldset1Controls(session,"true");

                        hracform.setHivStatusQuestion(1);
                    }
                    else if(ovc.getCurrentHivStatus()==2)
                    {
                        hracform.setHivStatusQuestion(1);
                        disableChildTestedControl(session,"false");
                        //disableAdolescentControls(session,"false");
                        disableFieldset1Controls(session,"false");
                    }
                    else if(ovc.getCurrentHivStatus()==3 || ovc.getCurrentHivStatus()==4)
                    {
                        hracform.setHivStatusQuestion(2);
                        hracform.setChildTestedQuestion(3);
                        disableChildTestedControl(session,"true");
                        //disableAdolescentControls(session,"false");
                        disableFieldset1Controls(session,"false");
                    }
                }
                setWithdrawalStatusMessage(session,hracform.getOvcId(),saveBtnDisabled,modifyBtnDisabled);
                //setButtonState(session,"true","false");
            }
            else
            {
                setWithdrawalStatusMessage(session,hracform.getOvcId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private HivRiskAssessment getHivRiskAssessment(HivRiskAssessmentForm form,String userName)
    {
        HivRiskAssessment hrac=new HivRiskAssessment();
        /*if(form.getChildTestedQuestion()==1)
        {
            hrac.setChildAtRiskQuestion(AppConstant.CHILD_NOT_AT_RISK_NUM);
            
        }*/
        
        String ovcId=form.getOvcId();
        Ovc ovc=getOvc(ovcId);
        if(ovc !=null)
        {
            hrac.setAgeAtRiskAssessment(ovc.getCurrentAge());
            hrac.setAgeUnitAtRiskAssessment(ovc.getCurrentAgeUnit());
        }
        hrac.setBloodTransfusionQuestion(form.getBloodTransfusionQuestion());
        hrac.setChildAtRiskQuestion(form.getChildAtRiskQuestion());
        /*if(form.getHivParentQuestion()==1)
        {
            hrac.setChildAtRiskQuestion(AppConstant.CHILD_AT_RISK_NUM);
        }*/
        hrac.setChildReferredForHIVTest(form.getChildReferredForHIVTest());
        hrac.setChildSickQuestion(form.getChildSickQuestion());
        hrac.setChildTestedQuestion(form.getChildTestedQuestion());
        hrac.setDateOfAssessment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfAssessment())));     
        hrac.setSexualAbuseQuestion(form.getSexualAbuseQuestion());
        
        hrac.setChildCircumcisedOrEarPierced(form.getChildCircumcisedOrEarPierced());
        hrac.setChildEverPregnantQuestion(form.getChildEverPregnantQuestion());
        hrac.setChildHasMoreThanTwoIllnessQuestion(form.getChildHasMoreThanTwoIllnessQuestion());
        hrac.setDateCreated(DateManager.getCurrentDateInstance());
        hrac.setDateOfAssessment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfAssessment())));
        hrac.setHivParentQuestion(form.getHivParentQuestion());
        hrac.setHivStatusAtRiskAssessment(form.getHivStatusAtRiskAssessment());
        hrac.setHivStatusQuestion(form.getHivStatusQuestion());
        hrac.setHivSibblingQuestion(form.getHivSibblingQuestion());
        hrac.setMotherSicknessQuestion(form.getMotherSicknessQuestion());
        hrac.setNameOfAssessor(form.getVolunteerName());
        hrac.setSexualAbuseQuestion(form.getSexualAbuseQuestion());
        hrac.setSibblingSicknessQuestion(form.getSibblingSicknessQuestion());
        hrac.setSexualActivityQuestion(form.getSexualActivityQuestion());
        hrac.setDrugInjectionQuestion(form.getDrugInjectionQuestion());
        hrac.setGenitalDischargeQuestion(form.getGenitalDischargeQuestion());
        /*if(hrac.getChildTestedQuestion()==2)
        {
            hrac.setChildAtRiskQuestion(AppConstant.CHILD_AT_RISK_NUM);
        }*/
                
        hrac.setLastModifiedDate(DateManager.getCurrentDateInstance());
        hrac.setOvcId(ovcId);
        hrac.setRecordId(form.getRecordId());
        hrac.setRecordedBy(userName);
        
                
        return hrac;
    }
    private HivRiskAssessmentForm getFilledHivRiskAssessmentForm(HivRiskAssessment hrac,HivRiskAssessmentForm form) throws Exception
    {
        DaoUtility util=new DaoUtility();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(hrac.getOvcId());
        if(ovc !=null)
        {
            UniqueIdManager uim=new UniqueIdManager();
            String hhUniqueId=ovc.getHhUniqueId();
            form.setSex(ovc.getSex());
            form.setHhUniqueId(ovc.getHhUniqueId());
            int hhSerialNo=uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId);
            form.setDateOfEnrollment(DateManager.convertDateToString(ovc.getDateOfEnrollment(), DateManager.MM_DD_YYYY_SLASH));
            form.setPhoneNumber(ovc.getPhoneNumber());
            form.setHhSerialNo(hhSerialNo);
            form.setBaselineHivStatus(ovc.getBaselineHivStatus());
            form.setCurrentHivStatus(ovc.getCurrentHivStatus()); 
        }
        form.setBloodTransfusionQuestion(hrac.getBloodTransfusionQuestion());
        form.setChildAtRiskQuestion(hrac.getChildAtRiskQuestion());
        form.setChildReferredForHIVTest(hrac.getChildReferredForHIVTest());
        form.setChildSickQuestion(hrac.getChildSickQuestion());
        form.setChildTestedQuestion(hrac.getChildTestedQuestion());
        form.setDateOfAssessment(DateManager.convertDateToString(hrac.getDateOfAssessment(),DateManager.MM_DD_YYYY_SLASH));
        form.setChildCircumcisedOrEarPierced(hrac.getChildCircumcisedOrEarPierced());
        form.setChildEverPregnantQuestion(hrac.getChildEverPregnantQuestion());
        form.setChildHasMoreThanTwoIllnessQuestion(hrac.getChildHasMoreThanTwoIllnessQuestion());
        //form.setHhUniqueId(SUCCESS);
        form.setHivSibblingQuestion(hrac.getHivSibblingQuestion());      
        form.setHivParentQuestion(hrac.getHivParentQuestion());
        form.setHivStatusAtRiskAssessment(hrac.getHivStatusAtRiskAssessment());
        form.setHivStatusQuestion(hrac.getHivStatusQuestion());
        form.setMotherSicknessQuestion(hrac.getMotherSicknessQuestion());
        form.setOvcId(hrac.getOvcId());
        form.setSexualAbuseQuestion(hrac.getSexualAbuseQuestion());
        form.setSibblingSicknessQuestion(hrac.getSibblingSicknessQuestion());
        form.setSexualActivityQuestion(hrac.getSexualActivityQuestion());
        form.setDrugInjectionQuestion(hrac.getDrugInjectionQuestion());
        form.setGenitalDischargeQuestion(hrac.getGenitalDischargeQuestion());
        form.setVolunteerName(hrac.getNameOfAssessor());
        form.setRecordId(hrac.getRecordId());
            
        return form;
    }
    
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("hracSaveDisabled", saveDisabled);
        session.setAttribute("hracModifyDisabled", modifyDisabled);
    }
    public void disableFieldset1Controls(HttpSession session,String disabled)
    {
        session.setAttribute("hracfieldset1disabled", "false");
        //session.setAttribute("hracfieldset1disabled", disabled);
    }
    public void disableChildTestedControl(HttpSession session,String disabled)
    {
        session.setAttribute("hracchildtesteddisabled", disabled);
    }
    public void disableChildAtRiskControl(HttpSession session,String disabled)
    {
        session.setAttribute("hracchildatriskdisabled", disabled);
    }
    public void disableHivParentControl(HttpSession session,String disabled)
    {
        session.setAttribute("hrachivParentdisabled", disabled);
    }
    public void disableChildRrequireHivTestControl(HttpSession session,String disabled)
    {
        session.setAttribute("hracchildrequirehivTestdisabled", disabled);
    }
    public void disableAdolescentFields(HttpSession session,String disabled)
    {
        session.setAttribute("hraadolfiledsdisabled", disabled);
    }//
    /*public void disableAdolescentControls(HttpSession session,String ovcId)
    {
        try
        {
            Ovc ovc=getOvc(ovcId);
            if(ovc !=null)
            {
                //HIV positive children are not assessed
                if(ovc.getCurrentHivStatus()==1)
                disableAdolescentFields(session,"true");
                //session.setAttribute("hraadolfiledsdisabled", "true"); 
                else
                {
                    if(ovc.getCurrentAge()<15)
                    {
                       disableAdolescentFields(session,"true");
                    }
                    else
                    disableAdolescentFields(session,"false");
                }
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }*/
    public void disableAdolescentControlsForExistingRecords(HttpSession session,int ageAtAssessment)
    {
        //this method is for already entered records that has pulled for ammendment
        try
        {
            if(ageAtAssessment >-1)
            {
                if(ageAtAssessment<12)
                {
                   disableAdolescentFields(session,"true");//session.setAttribute("hraadolfiledsdisabled", "true"); 
                }
                else
                disableAdolescentFields(session,"false");//session.setAttribute("hraadolfiledsdisabled", "false");
            }        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
