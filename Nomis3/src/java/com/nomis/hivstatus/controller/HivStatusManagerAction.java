/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.hivstatus.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.HivStatusManager;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.HivPropertiesManager;
import com.nomis.ovc.util.ReferralFacilityManager;
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
public class HivStatusManagerAction extends org.apache.struts.action.Action {

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
        HivStatusManagerForm hsmform=(HivStatusManagerForm)form;
        String moduleName="HIV Status manager";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        String requiredAction=hsmform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInDataEntryRole(user))
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
        String level2OuId=hsmform.getLevel2OuId();
        String level3OuId=hsmform.getLevel3OuId();
        String hhUniqueId=hsmform.getHhUniqueId();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,hsmform.getCboId());
        String hhId=request.getParameter("hh");
        String reqParam=request.getParameter("p");
        //setEnrolledOnTreatmentControlStatus(request,hsmform.getNewHivStatus());
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
        loadfacility(session,level2OuId,level3OuId);
        System.err.println("requiredAction is "+requiredAction);
        
        if(requiredAction==null)
        {
            hsmform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, hsmform.getCboId());
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
            String hhName=hsmform.getHhName();
            int hhSerialNo=hsmform.getHhSerialNo();
            hhUniqueId=hsmform.getHhUniqueId();
            setBeneficiaryList(session,hhUniqueId);
            //setBeneficiaryListPerHousehold(session, hhUniqueId);
            request.setAttribute("hhName", hhName);
            hsmform.setHhUniqueId(hhUniqueId);
            hsmform.setHhSerialNo(hhSerialNo);
            hsmform.setHhName(hhName);
            hsmform=setOrganizationUnitProperties(session, hhUniqueId,hsmform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("beneficiaryDetails"))
        {
            String beneficiaryId=hsmform.getBeneficiaryId();
            //hsmform.reset(mapping, request);
            hsmform.setBeneficiaryId(beneficiaryId);
            setBeneficiaryDetails(session,hsmform);
            setBeneficiaryList(session,hhUniqueId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ed"))
        {
            String id=request.getParameter("id");
            hsmform.setBeneficiaryId(id);
            setBeneficiaryList(session,hhUniqueId);
            setBeneficiaryDetails(session,hsmform);
            //setBeneficiaryDetails(hsmform,request);
            session.setAttribute("lastHivDisabled", "false");
            session.setAttribute("newHivDisabled", "true");
            setButtonState(session,"true","false");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("new"))
        {
            String id=request.getParameter("id");
            hsmform.setBeneficiaryId(id);
            setBeneficiaryList(session,hhUniqueId);
            setBeneficiaryDetails(session,hsmform);
            //setBeneficiaryDetails(hsmform,request);
            //session.setAttribute("lastHivDisabled", "true");
            session.setAttribute("newHivDisabled", "false");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            HivStatusManager hsm=getHivStatusManager(hsmform,userName);
            util.getHivStatusManagerDaoInstance().saveHivStatusManager(hsm,true);
            saveUserActivity(userName,moduleName,"Saved new HIV status record for beneficiary with Id "+hsm.getBeneficiaryId());
            setBeneficiaryList(session,hhUniqueId);
            //setBeneficiaryListPerHousehold(session, hhUniqueId);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            HivStatusManager hsm=getHivStatusManager(hsmform,userName);
            util.getHivStatusManagerDaoInstance().updateHivStatusManager(hsm,true);
            saveUserActivity(userName,moduleName,"Modified HIV status record for beneficiary with Id "+hsm.getBeneficiaryId());
            setBeneficiaryList(session,hhUniqueId);
            //setBeneficiaryListPerHousehold(session, hhUniqueId);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            HivStatusManager hsm=getHivStatusManager(hsmform,userName);
            util.getHivStatusManagerDaoInstance().markForDelete(hsm);
            saveUserActivity(userName,moduleName,"Requested HIV status record for beneficiary with Id "+hsm.getBeneficiaryId()+" be deleted");
            setBeneficiaryList(session,hhUniqueId);
            //setBeneficiaryListPerHousehold(session, hhUniqueId);
        }
        return mapping.findForward(SUCCESS);
    }
    private HivStatusManagerForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,HivStatusManagerForm form,String userName) throws Exception
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
    private void setBeneficiaryDetails(HttpSession session, HivStatusManagerForm hsmform)
    {
        try
        {
            System.err.println("hsmform.getBeneficiaryId() is "+hsmform.getBeneficiaryId());
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getChildEnrollmentDaoInstance().getOvc(hsmform.getBeneficiaryId());
            if(beneficiary==null)
            {
                beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(hsmform.getBeneficiaryId());
            }
            
            if(beneficiary !=null)
            {
                String dateOfNewStatus=DateManager.convertDateToString(beneficiary.getDateOfCurrentHivStatus(),DateManager.MM_DD_YYYY_SLASH);
                String dateOfEnrolledOnTreatment=DateManager.convertDateToString(beneficiary.getDateEnrolledOnTreatment(),DateManager.MM_DD_YYYY_SLASH);
                hsmform.setBeneficiaryId(beneficiary.getBeneficiaryId());
                hsmform.setDateOfEnrollment(DateManager.convertDateToString(beneficiary.getDateOfEnrollment(),DateManager.MM_DD_YYYY_SLASH));
                hsmform.setLastHivStatus(beneficiary.getCurrentHivStatus());
                hsmform.setSex(beneficiary.getSex());
                
                hsmform.setNewHivStatus(beneficiary.getCurrentHivStatus());
                hsmform.setDateOfNewStatus(DateManager.convertDateToString(beneficiary.getDateOfCurrentHivStatus(),DateManager.MM_DD_YYYY_SLASH));
                if(hsmform.getNewHivStatus()==AppConstant.HIV_UNKNOWN_NUM || dateOfNewStatus==null || dateOfNewStatus.equalsIgnoreCase(DateManager.DEFAULT_DATE))
                hsmform.setDateOfNewStatus(null);
                hsmform.setEnrolledOnTreatment(beneficiary.getEnrolledOnTreatment());
                hsmform.setDateEnrolledOnTreatment(dateOfEnrolledOnTreatment);
                if(hsmform.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_NO_NUM || dateOfEnrolledOnTreatment==null || dateOfEnrolledOnTreatment.equalsIgnoreCase(DateManager.DEFAULT_DATE))
                hsmform.setDateEnrolledOnTreatment(null);
                hsmform.setFacilityId(beneficiary.getHivTreatmentFacilityId());
                hsmform.setBeneficiaryName(beneficiary.getFirstName()+" "+beneficiary.getSurname());
                if(beneficiary.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                {
                    System.err.println("beneficiary.getCurrentHivStatus() is positive "+beneficiary.getCurrentHivStatus());
                    setHIVStatusProperties(session,"false");
                    setEnrolledOnTreatmentControlStatus(session,beneficiary.getEnrolledOnTreatment());
                }
                else
                {
                    System.err.println("beneficiary.getCurrentHivStatus() is not positive "+beneficiary.getCurrentHivStatus());
                    setHIVStatusProperties(session,"true");
                }
                //hsmform.setPhoneNumber(beneficiary.getPhoneNumber());
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void setHIVStatusProperties(HttpSession session,String disabled)
    {
        session.setAttribute("enrhivDisabled", disabled);
    }
    private HivStatusManager getHivStatusManager(HivStatusManagerForm hsmform,String userName)
    {
        Date dateEnrolledOnTreatment=DateManager.getDefaultStartDateInstance();
        if(hsmform.getDateEnrolledOnTreatment() !=null && hsmform.getDateEnrolledOnTreatment().indexOf("/") !=-1)
        dateEnrolledOnTreatment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hsmform.getDateEnrolledOnTreatment()));
       HivStatusManager hsm=new HivStatusManager();
       hsm.setBeneficiaryId(getBeneficiaryId(hsmform.getBeneficiaryId()));
       hsm.setBeneficiaryType(hsmform.getBeneficiaryType());
       hsm.setDateCreated(DateManager.getCurrentDateInstance());
       hsm.setDateOfNewStatus(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hsmform.getDateOfNewStatus())));
       hsm.setDateEnrolledOnTreatment(dateEnrolledOnTreatment);
       hsm.setEnrolledOnTreatment(hsmform.getEnrolledOnTreatment());
       if(hsmform.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
       hsm.setFacilityId(hsmform.getFacilityId());
       else
       hsm.setFacilityId(null);
       hsm.setLastModifiedDate(DateManager.getCurrentDateInstance());
       hsm.setNewHivStatus(hsmform.getNewHivStatus());
       hsm.setRecordedBy(userName);
       System.err.println("hsm.getBeneficiaryId in HIVStatusManager is "+hsm.getBeneficiaryId());
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
    private String getBeneficiaryType(String beneficiaryFormId)
    {
        String beneficiaryType="HH";
        if(beneficiaryFormId !=null && beneficiaryFormId.indexOf("_") !=-1)
        {
            String[] idArray=beneficiaryFormId.split("_");
            beneficiaryType=idArray[1];
        }
        return beneficiaryType;
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
