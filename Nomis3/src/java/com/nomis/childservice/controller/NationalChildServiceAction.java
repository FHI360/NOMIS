/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.childservice.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.EnrollmentStatusManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.OvcServiceAttributesManager;
import com.nomis.operationsManagement.QuarterlyServiceTrackerManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.business.DatasetSetting;
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
public class NationalChildServiceAction extends org.apache.struts.action.Action {

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
        NationalChildServiceForm csform=(NationalChildServiceForm)form;
        String moduleName="Child service";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=csform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        String level3OuId=csform.getLevel3OuId();
        String organizationUnitId=csform.getOrganizationUnitId();
        String hhUniqueId=csform.getHhUniqueId();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,csform.getCboId());
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        DatasetSetting dsts=util.getDatasetSettingDaoInstance().getDatasetSettingByModuleId(DatasetManager.getChildServiceModuleId());
        if(dsts !=null && dsts.getDatasetId().equalsIgnoreCase(DatasetManager.getRevChildServiceDatasetId()))
        {
            return mapping.findForward("RevisedChildServiceForm");
        }
        AccessManager acm=new AccessManager();
        User user=appManager.getCurrentUser(session);
        requiredAction=acm.getActionName(requiredAction, user);
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
        setOvcPerHouseholdList(session, csform.getHhUniqueId());
        loadAdditionalServices(session);
        //setButtonState(session,"false","true");
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            csform.reset(mapping, request);
            resetBaselineInfo(csform);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, csform.getCboId());
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
            int hhSerialNo=csform.getHhSerialNo();
            csform.setHhSerialNo(hhSerialNo);
            csform.setHhUniqueId(hhUniqueId);
            loadChildrenPerHousehold(session, hhUniqueId);
            csform=setOrganizationUnitProperties(session, hhUniqueId,csform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("childDetails"))
        {
            String ovcId=csform.getOvcId();
            csform.reset(mapping, request);
            csform.setOvcId(ovcId);
            csform.setOrganizationUnitId(organizationUnitId);
            setOvcDetails(csform,session);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("serviceDetails"))
        {
            AppUtility appUtil=new AppUtility();
            String csformServiceDate=csform.getServiceDate();
            String ovcId=csform.getOvcId();
            Date serviceDate=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(csformServiceDate));
            ChildService service=util.getChildServiceDaoInstance().getChildService(csform.getOvcId(), serviceDate);
            if(service !=null)
            {
                String mbcServiceCode=OvcServiceAttributesManager.getMotherBabyCourseServices().getServiceCode();
                csform.reset(mapping, request);
                csform.setOvcId(service.getOvcId());
                csform.setReferralServices(appUtil.splitString(service.getReferralServices(), ","));
                csform.setHealthServices(appUtil.splitString(service.getHealthServices(), ","));
                csform.setSafetyServices(appUtil.splitString(service.getSafetyServices(), ","));
                csform.setSchoolServices(appUtil.splitString(service.getSchooledServices(), ","));
                csform.setStableServices(appUtil.splitString(service.getStableServices(), ","));
                csform.setGbvServices(appUtil.splitString(service.getGbvServices(), ","));
                csform.setServiceDate(DateManager.convertDateToString(service.getServiceDate(),"MM/dd/yyyy"));
                csform.setVolunteerName(service.getCommunityWorkerId());
                setOvcDetails(csform,session);
                if(service.getSafetyServices() !=null && service.getSafetyServices().indexOf(mbcServiceCode) !=-1)
                session.setAttribute("mbcdisabled", "false");
                setButtonState(session,"true","false");
            }
            else
            {
                
                csform.reset(mapping, request);
                csform.setServiceDate(csformServiceDate);
                csform.setOvcId(ovcId);
            }
            csform.setOrganizationUnitId(organizationUnitId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("ovcList"))
        {
            String hhName=csform.getHhName();
            hhUniqueId=UniqueIdManager.cleanUniqueId(hhUniqueId);
            if(csform.getBeneficiaryType()==3)
            {
                String ovcId=hhUniqueId;
                Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
                if(ovc !=null)
                {
                    csform.setOvcId(ovcId);
                    hhUniqueId=ovc.getHhUniqueId();
                    setOvcDetails(csform,session);
                }
            }
            setOvcPerHouseholdList(session, hhUniqueId);
            request.setAttribute("hhName", hhName);
            csform.reset(mapping, request);
            csform.setHhUniqueId(hhUniqueId);
            csform.setHhName(hhName);
            csform=setOrganizationUnitProperties(session, hhUniqueId,csform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            QuarterlyServiceTrackerManager qstm=new QuarterlyServiceTrackerManager();
            EnrollmentStatusManager esm=new EnrollmentStatusManager();
            ChildService service=getChildService(csform,userName);
            service.setAgeAtService(getAgeAtService(service));
            util.getChildServiceDaoInstance().saveChildService(service, true);
            esm.updateQuarterlyEnrollmentStatusByServiceParameters(userName,service.getOvcId(),AppConstant.OVC_TYPE_NUM,service.getServiceDate());
            
            //qstm.saveQuarterlyService(service.getOvcId(), service.getServiceDate(),AppConstant.OVC_TYPE_NUM, service.getAgeAtService(),true,userName);
            saveUserActivity(userName,moduleName,"Saved a new service for child with Id "+service.getOvcId());
            csform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            QuarterlyServiceTrackerManager qstm=new QuarterlyServiceTrackerManager();
            EnrollmentStatusManager esm=new EnrollmentStatusManager();
            ChildService service=getChildService(csform,userName);
            service.setAgeAtService(getAgeAtService(service));
            ChildService dupService=util.getChildServiceDaoInstance().getChildService(service.getOvcId(), service.getServiceDate());
            if(dupService !=null && dupService.getGbvServices() !=null)
            service.setGbvServices(dupService.getGbvServices());
            util.getChildServiceDaoInstance().updateChildService(service, true);
            esm.updateQuarterlyEnrollmentStatusByServiceParameters(userName,service.getOvcId(),AppConstant.OVC_TYPE_NUM,service.getServiceDate());
            //qstm.saveQuarterlyService(service.getOvcId(), service.getServiceDate(),AppConstant.OVC_TYPE_NUM, service.getAgeAtService(),true,userName);
            saveUserActivity(userName,moduleName,"Modified child service record with Id "+service.getOvcId());
            csform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("markForDelete"))
        {
            ChildService service=getChildService(csform,userName);
            util.getChildServiceDaoInstance().markedForDelete(service);
            saveUserActivity(userName,moduleName,"Marked child service with Id "+service.getOvcId()+" for delete");
            csform.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            ChildService service=getChildService(csform,userName);
            util.getChildServiceDaoInstance().markedForDelete(service);
            saveUserActivity(userName,moduleName,"Requested child service with Id "+service.getOvcId()+" to be deleted");
            csform.reset(mapping, request);
        }
        csform.reset(mapping, request);
        return mapping.findForward(SUCCESS);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    private void loadAdditionalServices(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List healthList=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType("HLT", 1, AppConstant.OVC_TYPE_NUM);
        List stableList=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType("STB", 1, AppConstant.OVC_TYPE_NUM);
        List safetyList=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType("SFT", 1, AppConstant.OVC_TYPE_NUM);
        List schoolList=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServicesByDomainDisplayStatusAndBeneficiaryType("SCH", 1, AppConstant.OVC_TYPE_NUM);
        if(healthList==null)
        healthList=new ArrayList();
        if(stableList==null)
        stableList=new ArrayList();
        if(safetyList==null)
        safetyList=new ArrayList();
        if(schoolList==null)
        schoolList=new ArrayList();
        session.setAttribute("otherHealthList", healthList);
        session.setAttribute("otherStableList", stableList);
        session.setAttribute("otherSafetyList", safetyList);
        session.setAttribute("otherSchoolList", schoolList);
    }
    private void loadChildrenPerHousehold(HttpSession session, String hhUniqueId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);
        if(list==null)
        list=new ArrayList();
        session.setAttribute("listOfChildrenPerHousehold", list);
    }
    private NationalChildServiceForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,NationalChildServiceForm form,String userName) throws Exception
    {
        DaoUtility util=new DaoUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
        System.err.println("hhUniqueId is "+hhUniqueId);
        if(hhe !=null)
        {
            System.err.println("hhe is not null "+hhe.getHhUniqueId());
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
    private ChildService getChildService(NationalChildServiceForm csform,String userName)
    {
        System.err.println("csform.getServiceDate() is "+csform.getServiceDate());
        if(csform.getHealthServices() !=null)
        System.err.println("csform.getHealthServices() is "+csform.getHealthServices().toString());
        AppUtility appUtil=new AppUtility();
        Date currentDate=DateManager.getDateInstance(DateManager.getCurrentDate());
        ChildService service=new ChildService();
        service.setOvcId(csform.getOvcId());
        service.setServiceDate(DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(csform.getServiceDate())));
        service.setDateCreated(currentDate);
        service.setLastModifiedDate(currentDate);
        service.setHealthServices(appUtil.concatStr(csform.getHealthServices(), null));
        service.setSafetyServices(appUtil.concatStr(csform.getSafetyServices(), null));
        service.setStableServices(appUtil.concatStr(csform.getStableServices(), null));
        service.setSchooledServices(appUtil.concatStr(csform.getSchoolServices(), null));
        service.setReferralServices(appUtil.concatStr(csform.getReferralServices(),null));
        service.setNumberOfServices(0);
        service.setRecordedBy(userName);
        service.setId(csform.getId());
        service.setCommunityWorkerId(csform.getVolunteerName());
        return service;
    }
    private void setOvcPerHouseholdList(HttpSession session, String hhUniqueId)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);//.getAdultHouseholdMemberInstance().getAdultHouseholdMembersPerHousehold(hhUniqueId);
            if(list==null)
            list=new ArrayList();
            session.setAttribute("serviceOvcPerHouseholdList", list);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void setOvcDetails(NationalChildServiceForm csform,HttpSession session)
    {
        try
        {
            System.err.println("csform.getOvcId() is "+csform.getOvcId());
            DaoUtility util=new DaoUtility();
            Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(csform.getOvcId());
            if(ovc !=null)
            {
                csform.setOvcId(ovc.getOvcId());
                csform.setDateOfEnrollment(DateManager.convertDateToString(ovc.getDateOfEnrollment(),"MM/dd/yyyy"));
                csform.setHivStatus(ovc.getCurrentHivStatus());
                csform.setSex(ovc.getSex());
                csform.setPhoneNumber(ovc.getPhoneNumber());
                if(ovc.getCurrentBirthRegistrationStatus()==AppConstant.CHILD_HAS_BIRTHCERTIFICATE)
                session.setAttribute("ovchasbirthcert", "true");
                else
                session.removeAttribute("ovchasbirthcert");
                if(ovc.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                {
                    session.setAttribute("hivPosRelatedServices", "false");
                }
                else
                {
                    session.setAttribute("hivPosRelatedServices", "true");
                    session.setAttribute("nonPosRelatedServices", "false");
                }
                if((ovc.getCurrentAgeUnit()==AppConstant.AGEUNIT_MONTH_NUM || (ovc.getCurrentAge()<3 && ovc.getCurrentAgeUnit()==AppConstant.AGEUNIT_YEAR_NUM)))        
                {
                    session.setAttribute("mbcdisabled", "false");
                    if(ovc.getPrimaryCaregiver() !=null && ovc.getPrimaryCaregiver().getCurrentHivStatus() ==AppConstant.HIV_POSITIVE_NUM)
                    {
                        session.setAttribute("eidRelatedServices", "false");
                    }
                    else
                    {
                        session.setAttribute("eidRelatedServices", "true");
                    }
                }
                else
                {
                    session.setAttribute("mbcdisabled", "true");
                    session.setAttribute("eidRelatedServices", "false");
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private int getAgeAtService(ChildService service) throws Exception
    {
        int ageAtService=0;
        DaoUtility util=new DaoUtility();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(service.getOvcId());
        if(ovc !=null)
        {
            ageAtService=ovc.getCurrentAge();
        }
        return ageAtService;
    }
    private void resetBaselineInfo(NationalChildServiceForm csform)
    {
        csform.setHhUniqueId(null);
        csform.setHhName(null);
        csform.setDateOfBirth(null);
        csform.setDateOfEnrollment(null);
        csform.setEducationLevel(0);
        csform.setHivStatus(0);
        csform.setOccupation(0);
        csform.setOvcId(null);
        csform.setPhoneNumber(null);
        csform.setSex(null);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("serviceSaveDisabled", saveDisabled);
        session.setAttribute("serviceModifyDisabled", modifyDisabled);
    }
}
