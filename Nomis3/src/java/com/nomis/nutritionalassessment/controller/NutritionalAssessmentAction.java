/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.nutritionalassessment.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.NutritionAssessment;
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
public class NutritionalAssessmentAction extends org.apache.struts.action.Action {

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
        NutritionalAssessmentForm naform=(NutritionalAssessmentForm)form;
        String moduleName="Nutrition assessment";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=naform.getActionName();
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
        String level3OuId=naform.getLevel3OuId();
        int hhSerialNo=naform.getHhSerialNo();
        String hhUniqueId=naform.getHhUniqueId();
        String ovcId=naform.getOvcId();
        String formDateOfAssessment=naform.getDateOfAssessment();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,naform.getCboId());
        session.setAttribute("hivStatusForNutritionAssessment", HivPropertiesManager.getHivStatusWithoutPositive());
        setOvcPerHouseholdList(session, naform.getHhUniqueId());
        setButtonState(session,"false","true");
        setOvcDetails(naform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        AccessManager acm=new AccessManager();
        requiredAction=acm.getActionName(requiredAction, user);
        
        if(requiredAction==null)
        {
            //beneficiaryid is set to null in the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            naform.reset(mapping, request);
            setButtonState(session,"false","true");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, naform.getCboId());
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
            String hhName=null;//naform.getHhName();
            
            setOvcPerHouseholdList(session, hhUniqueId);
            request.setAttribute("hhName", hhName);
            naform.reset(mapping, request);
            naform.setHhUniqueId(hhUniqueId);
            naform.setHhSerialNo(hhSerialNo);
            //naform.setHhName(hhName);
            naform=setOrganizationUnitProperties(session, hhUniqueId,naform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("childDetails"))
        {
            UniqueIdManager uim=new UniqueIdManager();
            naform.reset(mapping, request);
            naform.setHhSerialNo(uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId));
            naform.setHhUniqueId(hhUniqueId);
            naform.setOvcId(ovcId);
            setOvcDetails(naform,session,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("assessmentDetails"))
        {
            naform.reset(mapping, request);
            naform.setHhSerialNo(hhSerialNo);
            naform.setHhUniqueId(hhUniqueId);
            naform.setOvcId(ovcId);
            naform.setDateOfAssessment(formDateOfAssessment);
            naform=getNutritionAssessmentForm(session,naform);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            NutritionAssessment na=getNutritionAssessment(naform,userName);
            util.getNutritionalAssessmentDaoInstance().saveNutritionAssessment(na);
            form.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            NutritionAssessment na=getNutritionAssessment(naform,userName);
            na.setRecordId(naform.getRecordId());
            util.getNutritionalAssessmentDaoInstance().updateNutritionAssessment(na);
            util.getNutritionalAssessmentDaoInstance().setNutritionAssessmentsWithDateOfLastWeight(na);
            form.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            NutritionAssessment na=getNutritionAssessment(naform,userName);
            na.setRecordId(naform.getRecordId());
            util.getNutritionalAssessmentDaoInstance().markedForDelete(na);
            form.reset(mapping, request);
        }
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private NutritionalAssessmentForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,NutritionalAssessmentForm form,String userName) throws Exception
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
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="naWithdrawnMessage";
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
    private void setOvcDetails(NutritionalAssessmentForm naform,HttpSession session,String saveBtnDisabled,String modifyBtnDisabled)
    {
        try
        {
            System.err.println("naform.getOvcId() is "+naform.getOvcId());
            DaoUtility util=new DaoUtility();
            String ovcId=naform.getOvcId();
            Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
            if(ovc !=null)
            {
                naform.setOvcId(ovc.getOvcId());
                naform.setDateOfEnrollment(DateManager.convertDateToString(ovc.getDateOfEnrollment(),"MM/dd/yyyy"));
                naform.setHivStatus(ovc.getCurrentHivStatus());
                naform.setSex(ovc.getSex());
                naform.setPhoneNumber(ovc.getPhoneNumber());
                //disableAdolescentControls(session,ovcId);
                setWithdrawalStatusMessage(session,naform.getOvcId(),saveBtnDisabled,modifyBtnDisabled);
            }
            else
            {
                setWithdrawalStatusMessage(session,naform.getOvcId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private NutritionAssessment getNutritionAssessment(NutritionalAssessmentForm naf,String userName) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        NutritionAssessment na=new NutritionAssessment();
        double height=naf.getHeight();
        double heightInMetres=height/100;
        double weight=naf.getWeight();
        double bmi=0;
        if(heightInMetres >0)
        bmi=(weight/(heightInMetres*heightInMetres));
        bmi=Math.round(bmi);
        System.err.println("double bmi= is "+bmi);
        
        String assessmentDate=naf.getDateOfAssessment();
        String dateOfLastWeight=naf.getDateOfLastWeight();
        if(dateOfLastWeight==null || dateOfLastWeight.indexOf("/")==-1)
        {
            dateOfLastWeight=assessmentDate;
        }
        na.setBmi(bmi);
        //naf.getChangeInWeight()
        na.setChangeInWeight(getChangeInWeight(naf.getWeight(),naf.getLastWeight()));
        na.setDateCreated(DateManager.getCurrentDateInstance());
        na.setLastModifiedDate(DateManager.getCurrentDateInstance());
        String mySqlDateOfAssessment=DateManager.processMthDayYearToMysqlFormat(assessmentDate);
        String mySqlDateOfLastWeight=DateManager.processMthDayYearToMysqlFormat(dateOfLastWeight);
        //na.setAssessmentNo(naf.getAssessmentNo());
        na.setDateOfAssessment(DateManager.getDateInstance(mySqlDateOfAssessment));
        na.setDateOfLastWeight(DateManager.getDateInstance(mySqlDateOfLastWeight));
        na.setFoodSecurityAndDietQ1(naf.getFoodSecurityAndDietQ1());
        na.setFoodSecurityAndDietQ2(naf.getFoodSecurityAndDietQ2());
        na.setFoodSecurityAndDietQ3(naf.getFoodSecurityAndDietQ3());
        na.setFoodSecurityAndDietQ4(naf.getFoodSecurityAndDietQ4());
        na.setFoodSecurityAndDietQ5(naf.getFoodSecurityAndDietQ5());
        na.setFoodSecurityAndDietQ6(naf.getFoodSecurityAndDietQ6());
        na.setFoodSecurityAndDietQ7(naf.getFoodSecurityAndDietQ7());
        na.setFoodSecurityAndDietQ8(naf.getFoodSecurityAndDietQ8());
        na.setFoodSecurityAndDietQ9(naf.getFoodSecurityAndDietQ9());

        na.setHeight(height);
        na.setHygieneQ1(naf.getHygieneQ1());
        na.setHygieneQ2(naf.getHygieneQ2());
        na.setHygieneQ3(naf.getHygieneQ3());
        na.setHygieneQ4(naf.getHygieneQ4());
        na.setLastWeight(naf.getLastWeight());
        na.setMuacFacility(naf.getMuacFacility());
        na.setYellowMuacServices(appUtil.concatStr(naf.getYellowMuacServices(), null));
        
        na.setMuac(naf.getMuac());
        na.setNutritionalStatus(naf.getNutritionalStatus());
        na.setOedema(naf.getOedema());
        na.setOvcId(naf.getOvcId());
        na.setRecordedBy(userName);
        na.setWeight(weight);
        na.setVolunteerName(naf.getVolunteerName());
        return na;
    }
    private NutritionalAssessmentForm getNutritionAssessmentForm(HttpSession session,NutritionalAssessmentForm naf) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        DaoUtility util=new DaoUtility();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(naf.getOvcId());
        if(ovc !=null)
        {
        NutritionAssessment na=util.getNutritionalAssessmentDaoInstance().getNutritionAssessment(naf.getOvcId(), DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(naf.getDateOfAssessment())));
        if(na !=null)
        {
            System.err.println("double bmi= is "+na.getBmi());
            naf.setBmi(na.getBmi());
            naf.setChangeInWeight(na.getChangeInWeight());
            //naf.setAssessmentNo(na.getAssessmentNo());
            naf.setDateOfAssessment(DateManager.convertDateToString(na.getDateOfAssessment(),DateManager.MM_DD_YYYY_SLASH));
            naf.setDateOfLastWeight(DateManager.convertDateToString(na.getDateOfLastWeight(),DateManager.MM_DD_YYYY_SLASH));
            naf.setFoodSecurityAndDietQ1(na.getFoodSecurityAndDietQ1());
            naf.setFoodSecurityAndDietQ2(na.getFoodSecurityAndDietQ2());
            naf.setFoodSecurityAndDietQ3(na.getFoodSecurityAndDietQ3());
            naf.setFoodSecurityAndDietQ4(na.getFoodSecurityAndDietQ4());
            naf.setFoodSecurityAndDietQ5(na.getFoodSecurityAndDietQ5());
            naf.setFoodSecurityAndDietQ6(na.getFoodSecurityAndDietQ6());
            naf.setFoodSecurityAndDietQ7(na.getFoodSecurityAndDietQ7());
            naf.setFoodSecurityAndDietQ8(na.getFoodSecurityAndDietQ8());
            naf.setFoodSecurityAndDietQ9(na.getFoodSecurityAndDietQ9());

            naf.setHeight(na.getHeight());
            naf.setHygieneQ1(na.getHygieneQ1());
            naf.setHygieneQ2(na.getHygieneQ2());
            naf.setHygieneQ3(na.getHygieneQ3());
            naf.setHygieneQ4(na.getHygieneQ4());
            naf.setLastWeight(na.getLastWeight());
            naf.setMuacFacility(na.getMuacFacility());
            naf.setYellowMuacServices(appUtil.splitServices(na.getYellowMuacServices()));

            naf.setMuac(na.getMuac());
            naf.setNutritionalStatus(na.getNutritionalStatus());
            naf.setOedema(na.getOedema());
            naf.setOvcId(na.getOvcId());
            naf.setWeight(na.getWeight());
            naf.setVolunteerName(na.getVolunteerName());
            naf.setSex(ovc.getSex());
            naf.setHivStatus(ovc.getCurrentHivStatus());
            
            setButtonState(session,"true","false");
        }
        }
        return naf;
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("hracSaveDisabled", saveDisabled);
        session.setAttribute("hracModifyDisabled", modifyDisabled);
    }
    private int getChangeInWeight(double weight,double lastWeight)
    {
        int changeInWeight=0;
        double weightDifference=weight-lastWeight;
        if(weightDifference==0)
        changeInWeight=AppConstant.NUTRITION_WEIGHTSTATIONARY;
        else if(weightDifference>0)
        changeInWeight=AppConstant.NUTRITION_WEIGHTGAIN;
        else if(weightDifference<0)
        changeInWeight=AppConstant.NUTRITION_WEIGHTLOSS;
        return changeInWeight;
    }
}
