/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.enrollmentstatus.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.CommunityWorkerRecordsManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.BeneficiaryStatusManager;
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
public class EnrollmentStatusManagementAction extends org.apache.struts.action.Action {

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
        EnrollmentStatusManagementForm esmform=(EnrollmentStatusManagementForm)form;
        
        String moduleName="Enrollment status management";
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=esmform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getAdhocOperationsUtilityPriviledge().getPrivilegeId()))
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
        String level2OuId=esmform.getLevel2OuId();
        String level3OuId=esmform.getLevel3OuId();
        int hhSerialNo=esmform.getHhSerialNo();
        String hhUniqueId=esmform.getHhUniqueId();
        String beneficiaryId=esmform.getBeneficiaryId();
        String dateOfNewEnrollmentStatus=esmform.getDateOfNewEnrollmentStatus();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,esmform.getCboId());
        BeneficiaryStatusManager bsm=new BeneficiaryStatusManager();
        session.setAttribute("currentBeneficiaryStatusList", bsm.getAllBeneficiaryStatus());
        setBeneficiaryStatusList(session,beneficiaryId);        
        setButtonState(session,"false","true");
        setBeneficiaryDetails(esmform,session);
        CommunityWorkerRecordsManager.setEnumeratorsRegistrationList(session);
        HivPropertiesManager.setHivStatusList(session, HivPropertiesManager.getThreeMainHivStatus());
        AccessManager acm=new AccessManager();
        requiredAction=acm.getActionName(requiredAction, user);
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            esmform.reset(mapping, request);
            setButtonState(session,"false","true");
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, esmform.getCboId());
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
            esmform.reset(mapping, request);
            esmform.setHhUniqueId(hhUniqueId);
            esmform.setHhSerialNo(hhSerialNo);
            esmform=setOrganizationUnitProperties(session, hhUniqueId,esmform,userName);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("beneficiaryDetails"))
        {
            UniqueIdManager uim=new UniqueIdManager();
            esmform.reset(mapping, request);
            esmform.setHhSerialNo(uim.extractHouseholdSerialNumberFromHhUniqueId(hhUniqueId));
            esmform.setHhUniqueId(hhUniqueId);
            esmform.setBeneficiaryId(beneficiaryId);
            setBeneficiaryDetails(esmform,session);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            updateBeneficiaryCurrentEnrollmentStatus(esmform); 
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
           updateBeneficiaryCurrentEnrollmentStatus(esmform); 
        }
        return mapping.findForward(SUCCESS);
    }
    private void updateBeneficiaryCurrentEnrollmentStatus(EnrollmentStatusManagementForm form)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            
            if(form.getDateOfNewEnrollmentStatus() !=null && form.getDateOfNewEnrollmentStatus().indexOf("/") !=-1)
            {
                Date dateOfNewEnrollmentStatus=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(form.getDateOfNewEnrollmentStatus()));
                if(form.getApplyToEntireHousehold()==1)
                {
                    HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(form.getBeneficiaryId());
                    if(hhe !=null)
                    {
                        hhe.setCurrentEnrollmentStatus(form.getCurrentEnrollmentStatus());
                        hhe.setDateOfCurrentStatus(dateOfNewEnrollmentStatus);
                        util.getHouseholdEnrollmentDaoInstance().updateHouseholdEnrollment(hhe);
                        util.getChildEnrollmentDaoInstance().setOvcNewEnrollmentStatus(hhe.getHhUniqueId(), form.getCurrentEnrollmentStatus(), dateOfNewEnrollmentStatus);
                        util.getAdultHouseholdMemberDaoInstance().setAdultHouseholdMemberNewEnrollmentStatus(hhe.getHhUniqueId(), form.getCurrentEnrollmentStatus(), dateOfNewEnrollmentStatus);
                    }
                }
                else
                {
                    Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(form.getBeneficiaryId());
                    if(ovc !=null && (ovc.getDateOfCurrentEnrollmentStatus().before(dateOfNewEnrollmentStatus) || ovc.getDateOfCurrentEnrollmentStatus().equals(dateOfNewEnrollmentStatus)))
                    {
                        setOvcEnrollmentStatus(ovc,form.getCurrentEnrollmentStatus(),dateOfNewEnrollmentStatus);
                        /*ovc.setCurrentEnrollmentStatus(form.getCurrentEnrollmentStatus());
                        ovc.setDateOfCurrentEnrollmentStatus(dateOfNewEnrollmentStatus);
                        util.getChildEnrollmentDaoInstance().updateOvc(ovc, false, false);*/
                    }
                    else
                    {
                        AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(form.getBeneficiaryId());
                        if(ahm !=null)
                        {
                           setAdultHouseholdMemberEnrollmentStatus(ahm,form.getCurrentEnrollmentStatus(),dateOfNewEnrollmentStatus);
                           /*ahm.setCurrentEnrollmentStatus(form.getCurrentEnrollmentStatus());
                           ahm.setDateOfCurrentEnrollmentStatus(dateOfNewEnrollmentStatus); 
                           util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);*/
                        }
                    }
                }   
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void setOvcEnrollmentStatus(Ovc ovc,int enrollmentStatus,Date dateOfNewEnrollmentStatus) throws Exception
    {
        DaoUtility util=new DaoUtility();
        if(ovc !=null && (ovc.getDateOfCurrentEnrollmentStatus().before(dateOfNewEnrollmentStatus) || ovc.getDateOfCurrentEnrollmentStatus().equals(dateOfNewEnrollmentStatus)))
        {
            ovc.setCurrentEnrollmentStatus(enrollmentStatus);
            ovc.setDateOfCurrentEnrollmentStatus(dateOfNewEnrollmentStatus);
            util.getChildEnrollmentDaoInstance().updateOvc(ovc, false, false);
        }
    }
    private void setAdultHouseholdMemberEnrollmentStatus(AdultHouseholdMember ahm,int enrollmentStatus,Date dateOfNewEnrollmentStatus) throws Exception
    {
        DaoUtility util=new DaoUtility();
        if(ahm !=null && (ahm.getDateOfCurrentEnrollmentStatus().before(dateOfNewEnrollmentStatus) || ahm.getDateOfCurrentEnrollmentStatus().equals(dateOfNewEnrollmentStatus)))
        {
            ahm.setCurrentEnrollmentStatus(enrollmentStatus);
            ahm.setDateOfCurrentEnrollmentStatus(dateOfNewEnrollmentStatus);
            util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
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
                beneficiaryList.add(ahm);
            }
        }
        session.setAttribute("esmBeneficiaryList", beneficiaryList);
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("esmSaveDisabled", saveDisabled);
        session.setAttribute("esmModifyDisabled", modifyDisabled);
    }
    private void setBeneficiaryDetails(EnrollmentStatusManagementForm form,HttpSession session)
    {
        try
        {
            //System.err.println("form.getBeneficiaryId() is "+form.getBeneficiaryId());
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getChildEnrollmentDaoInstance().getOvc(form.getBeneficiaryId());
            if(beneficiary==null)
            {
                beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(form.getBeneficiaryId());
            }
            
            if(beneficiary !=null)
            {
                form.setBeneficiaryId(beneficiary.getBeneficiaryId());
                form.setDateOfEnrollment(DateManager.convertDateToString(beneficiary.getDateOfEnrollment(),"MM/dd/yyyy"));
                form.setHivStatus(beneficiary.getCurrentHivStatus());
                form.setSex(beneficiary.getSex());
                form.setPhoneNumber(beneficiary.getPhoneNumber());
                form.setCurrentEnrollmentStatus(beneficiary.getCurrentEnrollmentStatus());
                form.setDateOfCurrentEnrollmentStatus(DateManager.convertDateToString(beneficiary.getDateOfCurrentEnrollmentStatus(),DateManager.MM_DD_YYYY_SLASH)); 
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private EnrollmentStatusManagementForm setOrganizationUnitProperties(HttpSession session, String hhUniqueId,EnrollmentStatusManagementForm form,String userName) throws Exception
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
    private void setBeneficiaryStatusList(HttpSession session,String beneficiaryId) throws Exception
    {
        DaoUtility util=new DaoUtility();
        BeneficiaryStatusManager bsm=new BeneficiaryStatusManager();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
        if(ovc !=null)
        session.setAttribute("beneficiaryStatusList", bsm.getBeneficiaryStatusListForChildren());
        else
        session.setAttribute("beneficiaryStatusList", bsm.getBeneficiaryStatusListForAdults());
    }
}
