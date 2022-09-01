/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.caregiverservice.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.EnrollmentStatusManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.OvcServiceManager;
import com.nomis.operationsManagement.QuarterlyServiceTrackerManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.DatasetSetting;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.HouseholdService;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatasetManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.HivPropertiesManager;
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
public class CaregiverServiceAction extends org.apache.struts.action.Action {

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
        CaregiverServiceForm hhsform=(CaregiverServiceForm)form;
        String moduleName="Household service";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        QuarterlyServiceTrackerManager qstm=new QuarterlyServiceTrackerManager();
        String requiredAction=hhsform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        DatasetSetting dsts=util.getDatasetSettingDaoInstance().getDatasetSettingByModuleId(DatasetManager.getHouseholdServiceModuleId());
        if(dsts !=null && dsts.getDatasetId().equalsIgnoreCase(DatasetManager.getNatHouseholdServiceDatasetId()))
        {
            System.err.println("dsts.getDatasetId() is "+dsts.getDatasetId());
            return mapping.findForward("NationalHouseholdServiceForm");
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
        loadAdditionalServices(session);
        String userName=appManager.getCurrentUserName(session);
        String level3OuId=hhsform.getLevel3OuId();
        int hhSerialNo=hhsform.getHhSerialNo();
        String hhUniqueId=hhsform.getHhUniqueId();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,hhsform.getCboId());
        session.setAttribute("mainHivStatus", HivPropertiesManager.getThreeMainHivStatus());
        System.err.println("requiredAction is "+requiredAction);
        AccessManager acm=new AccessManager();
        requiredAction=acm.getActionName(requiredAction, user);
        //System.err.println("requiredAction after acm.getActionName() is "+requiredAction);
        setHouseholdMemberListPerHousehold(session, hhsform.getHhUniqueId());
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        //setButtonState(session,"false","true");
        //setWithdrawalStatusMessage(session,hhsform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.TRUEVALUE);
        
        if(requiredAction==null)
        {
            //set null beneficiaryid to the setWithdrawalStatusMessage method to reset the session and button to initial values
            setWithdrawalStatusMessage(session,null,AppConstant.FALSEVALUE,AppConstant.TRUEVALUE);
            hhsform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, hhsform.getCboId());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }//
        else if(requiredAction.equalsIgnoreCase("householdDetails"))
        {
            String hhName=hhsform.getHhName();
            setHouseholdMemberListPerHousehold(session, hhsform.getHhUniqueId());
            request.setAttribute("hhName", hhName);
            hhsform.reset(mapping, request);
            hhsform.setHhSerialNo(hhSerialNo);
            hhsform.setHhUniqueId(hhUniqueId);
            hhsform.setHhName(hhName);
            hhsform=setOrganizationUnitProperties(session, hhUniqueId,hhsform,userName);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ahmDetails"))
        {
            session.removeAttribute("hhsahm");
            AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(hhsform.getBeneficiaryId());
            if(ahm !=null)
            {
                hhsform=getAdultHouseholdMemberForm(request,hhsform);
                setWithdrawalStatusMessage(session,hhsform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE); 
                return mapping.findForward(SUCCESS);
            }
            else
            {
                //setWithdrawalStatusMessage(session,hhsform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE);
            }
            hhsform.reset(mapping, request);
            hhsform.setHhSerialNo(hhSerialNo);
            hhsform.setHhUniqueId(hhUniqueId);
            setWithdrawalStatusMessage(session,hhsform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE); 
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("serviceDetails"))
        {
            AppUtility appUtil=new AppUtility();
            String beneficiaryId=hhsform.getBeneficiaryId();
            String hhsformServiceDate=hhsform.getServiceDate();
            Date serviceDate=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhsformServiceDate));
            HouseholdService service=util.getHouseholdServiceDaoInstance().getHouseholdService(hhsform.getBeneficiaryId(), serviceDate);
            if(service !=null)
            {
                System.err.println("service.getGbvServices() is "+service.getGbvServices());
                hhsform.reset(mapping, request);
                hhsform.setHhSerialNo(hhSerialNo);
                hhsform.setHhUniqueId(hhUniqueId);
                hhsform.setServiceDate(hhsformServiceDate);
                hhsform.setBeneficiaryId(service.getBeneficiaryId());
                hhsform.setReferralServices(appUtil.splitString(service.getReferralServices(), ","));
                hhsform.setHealthServices(appUtil.splitString(service.getHealthServices(), ","));
                hhsform.setSafetyServices(appUtil.splitString(service.getSafetyServices(), ","));
                hhsform.setStableServices(appUtil.splitString(service.getStableServices(), ","));
                hhsform.setGbvServices(appUtil.splitString(service.getGbvServices(), ","));
                hhsform.setSchoolServices(appUtil.splitString(service.getSchoolServices(), ","));
                hhsform.setServiceDate(DateManager.convertDateToString(service.getServiceDate(),DateManager.MM_DD_YYYY_SLASH));
                hhsform.setVolunteerName(service.getCommunityWorkerId());
                hhsform=getAdultHouseholdMemberForm(request,hhsform);
                setButtonState(session,"true","false");
                setWithdrawalStatusMessage(session,hhsform.getBeneficiaryId(),AppConstant.TRUEVALUE,AppConstant.FALSEVALUE); 
            }
            else
            {
                hhsform.reset(mapping, request);
                hhsform.setHhSerialNo(hhSerialNo);
                hhsform.setHhUniqueId(hhUniqueId);
                hhsform.setBeneficiaryId(beneficiaryId);
                hhsform.setServiceDate(hhsformServiceDate);
                setWithdrawalStatusMessage(session,hhsform.getBeneficiaryId(),AppConstant.FALSEVALUE,AppConstant.TRUEVALUE); 
            }
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            //ServiceCascadeManager scm=new ServiceCascadeManager();
            EnrollmentStatusManager esm=new EnrollmentStatusManager();
            HouseholdService hhs=getHouseholdService(hhsform, userName);
            hhs.setAgeAtService(getAdultHouseholdMemberAgeAtService(hhs.getBeneficiaryId(),hhs.getServiceDate()).getAge());
            util.getHouseholdServiceDaoInstance().saveHouseholdService(hhs,false);
            esm.processAdultHouseholdMemberEnrollmentStatusByBeneficiaryId(hhs.getBeneficiaryId(),DateManager.convertDateToString(hhs.getServiceDate(),DateManager.DB_DATE_FORMAT), userName);
            //esm.updateQuarterlyEnrollmentStatusByServiceParameters(userName,hhs.getBeneficiaryId(),AppConstant.CAREGIVER_TYPE_NUM,hhs.getServiceDate());
            saveUserActivity(userName,moduleName,"Saved new Household service record with Id "+hhs.getBeneficiaryId());
            //qstm.saveQuarterlyService(hhs.getBeneficiaryId(), hhs.getServiceDate(),AppConstant.CAREGIVER_TYPE_NUM, hhs.getAgeAtService(),true,userName);
            //scm.cascadeServicesToOvc(hhs, userName);
            //cascadableServicesToOvc(hhsform,userName);
            hhsform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            EnrollmentStatusManager esm=new EnrollmentStatusManager();
            //ServiceCascadeManager scm=new ServiceCascadeManager();
            HouseholdService hhs=getHouseholdService(hhsform, userName);
            hhs.setAgeAtService(getAdultHouseholdMemberAgeAtService(hhs.getBeneficiaryId(),hhs.getServiceDate()).getAge());
            HouseholdService dupService=util.getHouseholdServiceDaoInstance().getHouseholdService(hhs.getBeneficiaryId(), hhs.getServiceDate());
            if(dupService !=null && dupService.getGbvServices() !=null)
            hhs.setGbvServices(dupService.getGbvServices());
            util.getHouseholdServiceDaoInstance().updateHouseholdService(hhs,false);
            esm.processAdultHouseholdMemberEnrollmentStatusByBeneficiaryId(hhs.getBeneficiaryId(),DateManager.convertDateToString(hhs.getServiceDate(),DateManager.DB_DATE_FORMAT), userName);
            //esm.updateQuarterlyEnrollmentStatusByServiceParameters(userName,hhs.getBeneficiaryId(),AppConstant.CAREGIVER_TYPE_NUM,hhs.getServiceDate());
            saveUserActivity(userName,moduleName,"Modified Household service record with Id "+hhs.getBeneficiaryId());
            //qstm.saveQuarterlyService(hhs.getBeneficiaryId(), hhs.getServiceDate(),AppConstant.CAREGIVER_TYPE_NUM, hhs.getAgeAtService(),true,userName);
            //scm.cascadeServicesToOvc(hhs, userName);
            //cascadableServicesToOvc(hhsform,userName);
            hhsform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("markForDelete"))
        {
            HouseholdService hhs=getHouseholdService(hhsform, userName);
            util.getHouseholdServiceDaoInstance().markForDelete(hhs);
            saveUserActivity(userName,moduleName,"Marked Household service record with Id "+hhs.getBeneficiaryId()+" for delete");
            hhsform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            HouseholdService hhs=getHouseholdService(hhsform, userName);
            util.getHouseholdServiceDaoInstance().markForDelete(hhs);
            saveUserActivity(userName,moduleName,"Requested Household service record with Id "+hhs.getBeneficiaryId()+" be deleted");
            hhsform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        
        return mapping.findForward(SUCCESS);
    }
    private void setWithdrawalStatusMessage(HttpSession session,String beneficiaryId,String saveBtnDisabledValue,String modifyBtnDisabledValue) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String attributeName="hhsWithdrawnMessage";
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

    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void loadAdditionalServices(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List healthList=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType("HLT", 1, AppConstant.CAREGIVER_TYPE_NUM);
        List stableList=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType("STB", 1, AppConstant.CAREGIVER_TYPE_NUM);
        List safetyList=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType("SFT", 1, AppConstant.CAREGIVER_TYPE_NUM);
        List schoolList=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType("SCH", 1, AppConstant.CAREGIVER_TYPE_NUM);
        if(healthList==null)
        healthList=new ArrayList();
        if(stableList==null)
        stableList=new ArrayList();
        if(safetyList==null)
        safetyList=new ArrayList();
        if(schoolList==null)
        schoolList=new ArrayList();
        session.setAttribute("otherCaregiverHealthList", healthList);
        session.setAttribute("otherCaregiverStableList", stableList);
        session.setAttribute("otherCaregiverSafetyList", safetyList);
        session.setAttribute("otherCaregiverSchoolList", schoolList);
    }
    private HouseholdService getHouseholdService(CaregiverServiceForm hhsform,String userName)
    {
        AppUtility appUtil=new AppUtility();
        //UniqueIdManager uim=new UniqueIdManager();
        HouseholdService hhs=new HouseholdService();
        //hhs.setBeneficiaryId(uim.generateHouseholdServiceUniqueId(hhsform.getBeneficiaryId(), hhsform.getServiceDate()));
        hhs.setBeneficiaryId(hhsform.getBeneficiaryId());
        hhs.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        hhs.setHealthServices(appUtil.concatStr(hhsform.getHealthServices(), null));
        hhs.setLastModifiedDate(DateManager.getDateInstance(DateManager.getCurrentDate()));
        hhs.setRecordedBy(userName);
        hhs.setReferralServices(appUtil.concatStr(hhsform.getReferralServices(),null));
        hhs.setSafetyServices(appUtil.concatStr(hhsform.getSafetyServices(), null));
        hhs.setStableServices(appUtil.concatStr(hhsform.getStableServices(), null));
        hhs.setSchoolServices(appUtil.concatStr(hhsform.getSchoolServices(), null));
        hhs.setServiceDate(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(hhsform.getServiceDate())));
        hhs.setCommunityWorkerId(hhsform.getVolunteerName());
        return hhs;
    }
    private CaregiverServiceForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,CaregiverServiceForm form,String userName) throws Exception
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
    private AgeObject getAdultHouseholdMemberAgeAtService(String beneficaryId, Date serviceDate) throws Exception
    {
        AgeObject ageObj=new AgeObject();
        DaoUtility util=new DaoUtility();
        AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficaryId);
        if(ahm !=null)
        {
            OvcServiceManager osm=new OvcServiceManager();
            ageObj=osm.getAgeAtService(ahm.getDateOfEnrollment(), serviceDate,ahm.getAgeAtBaseline(),ahm.getAgeUnitAtBaseline());
        }
        return ageObj;
    }
    /*private AgeObject getAgeAtService(Date dateOfEnrollment, Date serviceDate,int ageAtEnrollment,int ageUnitAtEnrollment) throws Exception
    {
        OvcServiceManager osm=new OvcServiceManager();
        AgeObject ageObj=osm.getAgeAtService(dateOfEnrollment, serviceDate, ageAtEnrollment,ageUnitAtEnrollment);
        
        return ageObj;
    }*/
    private void setHouseholdMemberListPerHousehold(HttpSession session, String hhUniqueId)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMembersPerHousehold(hhUniqueId);
            if(list==null)
            list=new ArrayList();
            session.setAttribute("ahmList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private CaregiverServiceForm getAdultHouseholdMemberForm(HttpServletRequest request,CaregiverServiceForm hhsform)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(hhsform.getBeneficiaryId());
            if(ahm !=null)
            {
                HttpSession session=request.getSession();
                String hhName=hhsform.getHhName();
                
                hhsform.setBeneficiaryId(ahm.getBeneficiaryId());
                hhsform.setDateOfBirth(DateManager.convertDateToString(ahm.getDateOfBirth(),"MM/dd/yyyy"));
                hhsform.setDateOfEnrollment(DateManager.convertDateToString(ahm.getDateOfEnrollment(),"MM/dd/yyyy"));
                hhsform.setEducationLevel(ahm.getEducationLevel());
                hhsform.setHhUniqueId(ahm.getHhUniqueId());
                hhsform.setHivStatus(ahm.getCurrentHivStatus());
                hhsform.setOccupation(ahm.getOccupation());
                hhsform.setOrganizationUnitId(ahm.getOrganizationUnit());
                hhsform.setPhoneNumber(ahm.getPhoneNumber());
                hhsform.setSex(ahm.getSex());
                
                if(ahm.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                {
                    setHivPosRelatedServicesState(session, "false");
                    if(ahm.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
                    {
                        setArtRelatedServicesState(session, "false");
                    }
                    else
                    setArtRelatedServicesState(session, "true");
                }
                else
                {
                    setHivPosRelatedServicesState(session, "true");
                    setArtRelatedServicesState(session, "true");
                    setHivPosRelatedServicesState(session, "false");
                }
                request.setAttribute("hhName", hhName);
                session.setAttribute("hhsahm", ahm);
                //setButtonState(session,"true","false");   
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return hhsform;
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("hhsSaveDisabled", saveDisabled);
        session.setAttribute("hhsModifyDisabled", modifyDisabled);
    }
    public void setHivPosRelatedServicesState(HttpSession session,String state)
    {
        session.setAttribute("ahmHivPosRelatedServices", state);
    }
    public void setArtRelatedServicesState(HttpSession session,String state)
    {
        session.setAttribute("ahmArtRelatedServices", state);
    }
}
