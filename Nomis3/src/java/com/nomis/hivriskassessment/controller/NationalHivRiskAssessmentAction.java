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
import com.nomis.ovc.business.DatasetSetting;
import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
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
public class NationalHivRiskAssessmentAction extends org.apache.struts.action.Action {

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
        NationalHivRiskAssessmentForm hracform=(NationalHivRiskAssessmentForm)form;  
        String moduleName="National HIV Risk assessment";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=hracform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        DatasetSetting dsts=util.getDatasetSettingDaoInstance().getDatasetSettingByModuleId(DatasetManager.getHivRiskAsmtModuleId());
        if(dsts !=null && dsts.getDatasetId().equalsIgnoreCase(DatasetManager.getRevHivRiskAsmtDatasetId()))
        {
            return mapping.findForward("RevisedHivRiskAssessmentForm");
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
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,hracform.getCboId());
        session.setAttribute("hivStatusForRiskAssessment", HivPropertiesManager.getHivStatusWithoutPositive());
        setOvcPerHouseholdList(session, hracform.getHhUniqueId());
        setButtonState(session,"false","true");
        setOvcDetails(hracform,session);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        AccessManager acm=new AccessManager();
        requiredAction=acm.getActionName(requiredAction, user);
        
        if(requiredAction==null)
        {
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
            String ovcId=hracform.getOvcId();
            hracform.reset(mapping, request);
            hracform.setHhSerialNo(uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId));
            hracform.setHhUniqueId(hhUniqueId);
            hracform.setOvcId(ovcId);
            setOvcDetails(hracform,session);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("childTestedQuestion"))
        {
            
            String ovcId=hracform.getOvcId();
            disableAdolescentControls(session,ovcId);
            if(hracform.getChildTestedQuestion()==1)
            {
                //setButtonState(session,"false","true");
                disableFieldset1Controls(session,"true");
                disableChildAtRiskControl(session, "false");
                disableHivParentControl(session,"true");
                hracform.setChildAtRiskQuestion(AppConstant.CHILD_NOT_AT_RISK_NUM);
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
            
            String ovcId=hracform.getOvcId();
            disableAdolescentControls(session,ovcId);
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
            String ovcId=hracform.getOvcId();
            int hhSerialNo=uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId);
            Date serviceDate=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hracformDateOfAssessment));
            HivRiskAssessment hrac=util.getHivRiskAssessmentDaoInstance().getHivRiskAssessment(ovcId, serviceDate);
            hracform.reset(mapping, request);
            hracform.setHhSerialNo(hhSerialNo);
            hracform.setHhUniqueId(hhUniqueId);
            if(hrac !=null)
            {
                hracform.setOvcId(ovcId);
                hracform=getFilledNationalHivRiskAssessmentForm(hrac,hracform);
                if(hrac.getChildTestedQuestion()==1 || hrac.getHivParentQuestion()==1)
                {
                    disableChildTestedControl(session,"false");
                    disableFieldset1Controls(session,"true");
                    disableAdolescentControls(session,ovcId);
                }
                else
                {
                    //Enable data entry controls to allow modification
                    disableChildTestedControl(session,"false");
                    disableFieldset1Controls(session,"false");
                }
                setButtonState(session,"true","false");
            }
            else
            {
                hracform.setOvcId(ovcId);
                hracform.setHhUniqueId(hhUniqueId);
                setOvcDetails(hracform,session);
                hracform.setDateOfAssessment(hracformDateOfAssessment);
                disableChildTestedControl(session,"false");
                disableAdolescentControls(session,ovcId);
                disableFieldset1Controls(session,"false");
                setButtonState(session,"false","true");
            }
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            //HivRiskAssessmentManager hram=new HivRiskAssessmentManager();
            HivRiskAssessment hra=getHivRiskAssessment(hracform,userName);
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
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private NationalHivRiskAssessmentForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,NationalHivRiskAssessmentForm form,String userName) throws Exception
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
    private void setOvcDetails(NationalHivRiskAssessmentForm hracform,HttpSession session)
    {
        try
        {
            System.err.println("hracform.getOvcId() is "+hracform.getOvcId());
            DaoUtility util=new DaoUtility();
            String ovcId=hracform.getOvcId();
            Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
            if(ovc !=null)
            {
                hracform.setOvcId(ovc.getOvcId());
                hracform.setDateOfEnrollment(DateManager.convertDateToString(ovc.getDateOfEnrollment(),"MM/dd/yyyy"));
                hracform.setHivStatus(ovc.getCurrentHivStatus());
                hracform.setSex(ovc.getSex());
                hracform.setPhoneNumber(ovc.getPhoneNumber());
                disableAdolescentControls(session,ovcId);
                
                if(ovc.getCurrentHivStatus()==1)
                {
                    setButtonState(session,"true","true");
                    disableChildTestedControl(session,"true");
                    disableFieldset1Controls(session,"true");
                    
                    //hracform.setHivStatusQuestion(1);
                }
                else if(ovc.getCurrentHivStatus()==2)
                {
                    //hracform.setHivStatusQuestion(1);
                    disableChildTestedControl(session,"false");
                    //disableAdolescentControls(session,"false");
                    disableFieldset1Controls(session,"false");
                }
                else if(ovc.getCurrentHivStatus()==3 || ovc.getCurrentHivStatus()==4)
                {
                    //hracform.setHivStatusQuestion(2);
                    hracform.setChildTestedQuestion(3);
                    disableChildTestedControl(session,"true");
                    //disableAdolescentControls(session,"false");
                    disableFieldset1Controls(session,"false");
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private HivRiskAssessment getHivRiskAssessment(NationalHivRiskAssessmentForm form,String userName)
    {
        HivRiskAssessment hrac=new HivRiskAssessment();
        if(form.getChildTestedQuestion()==1)
        {
            hrac.setChildAtRiskQuestion(AppConstant.CHILD_NOT_AT_RISK_NUM);
            
        }
        if(form.getHivParentQuestion()==1)
        {
            hrac.setChildAtRiskQuestion(AppConstant.CHILD_AT_RISK_NUM);
        }
        String ovcId=form.getOvcId();
        Ovc ovc=getOvc(ovcId);
        if(ovc !=null)
        {
            hrac.setAgeAtRiskAssessment(ovc.getCurrentAge());
            hrac.setAgeUnitAtRiskAssessment(ovc.getCurrentAgeUnit());
        }
        hrac.setBloodTransfusionQuestion(form.getBloodTransfusionQuestion());
        hrac.setChildAtRiskQuestion(form.getChildAtRiskQuestion());
        hrac.setChildSickQuestion(form.getChildSickQuestion());
        hrac.setChildTestedQuestion(form.getChildTestedQuestion());
        hrac.setDateOfAssessment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfAssessment())));     
        hrac.setSexualAbuseQuestion(form.getSexualAbuseQuestion());
        
        //hrac.setChildCircumcisedOrEarPierced(form.getChildCircumcisedOrEarPierced());
        //hrac.setChildEverPregnantQuestion(form.getChildEverPregnantQuestion());
        //hrac.setChildHasMoreThanTwoIllnessQuestion(form.getChildHasMoreThanTwoIllnessQuestion());
        hrac.setDateCreated(DateManager.getCurrentDateInstance());
        hrac.setDateOfAssessment(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfAssessment())));
        hrac.setHivParentQuestion(form.getHivParentQuestion());
        hrac.setHivStatusAtRiskAssessment(form.getHivStatus());
        //hrac.setHivStatusQuestion(form.getHivStatusQuestion());
        //hrac.setHivSibblingQuestion(form.getHivSibblingQuestion());
        //hrac.setMotherSicknessQuestion(form.getMotherSicknessQuestion());
        hrac.setNameOfAssessor(form.getVolunteerName());
        hrac.setSexualAbuseQuestion(form.getSexualAbuseQuestion());
        //hrac.setSibblingSicknessQuestion(form.getSibblingSicknessQuestion());
        if(hrac.getChildTestedQuestion()==2)
        {
            hrac.setChildAtRiskQuestion(AppConstant.CHILD_AT_RISK_NUM);
        }
                
        hrac.setLastModifiedDate(DateManager.getCurrentDateInstance());
        hrac.setOvcId(ovcId);
        hrac.setRecordId(form.getRecordId());
        hrac.setRecordedBy(userName);
        
                
        return hrac;
    }
    private NationalHivRiskAssessmentForm getFilledNationalHivRiskAssessmentForm(HivRiskAssessment hrac,NationalHivRiskAssessmentForm form) throws Exception
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
        }
        form.setBloodTransfusionQuestion(hrac.getBloodTransfusionQuestion());
        form.setChildAtRiskQuestion(hrac.getChildAtRiskQuestion());
        form.setChildSickQuestion(hrac.getChildSickQuestion());
        form.setChildTestedQuestion(hrac.getChildTestedQuestion());
        form.setDateOfAssessment(DateManager.convertDateToString(hrac.getDateOfAssessment(),DateManager.MM_DD_YYYY_SLASH));
        //form.setChildCircumcisedOrEarPierced(hrac.getChildCircumcisedOrEarPierced());
        //form.setChildEverPregnantQuestion(hrac.getChildEverPregnantQuestion());
        //form.setChildHasMoreThanTwoIllnessQuestion(hrac.getChildHasMoreThanTwoIllnessQuestion());
        //form.setHhUniqueId(SUCCESS);
        //form.setHivSibblingQuestion(hrac.getHivSibblingQuestion());      
        form.setHivParentQuestion(hrac.getHivParentQuestion());
        form.setHivStatus(hrac.getHivStatusAtRiskAssessment());
        //form.setHivStatusQuestion(hrac.getHivStatusQuestion());
        //form.setMotherSicknessQuestion(hrac.getMotherSicknessQuestion());
        form.setVolunteerName(hrac.getNameOfAssessor());
        form.setOvcId(hrac.getOvcId());
        form.setSexualAbuseQuestion(hrac.getSexualAbuseQuestion());
        //form.setSibblingSicknessQuestion(hrac.getSibblingSicknessQuestion());
        
        
        form.setOvcId(hrac.getOvcId());
                
        form.setRecordId(hrac.getRecordId());
            
        return form;
    }
    
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("nathracSaveDisabled", saveDisabled);
        session.setAttribute("nathracModifyDisabled", modifyDisabled);
    }
    public void disableFieldset1Controls(HttpSession session,String disabled)
    {
        session.setAttribute("hracfieldset1disabled", disabled);
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
    public void disableAdolescentControls(HttpSession session,String ovcId)
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
                    if(ovc.getCurrentAge()<12)
                    {
                       disableAdolescentFields(session,"true");//session.setAttribute("hraadolfiledsdisabled", "true"); 
                    }
                    else
                    disableAdolescentFields(session,"false");//session.setAttribute("hraadolfiledsdisabled", "false");
                }
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
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
