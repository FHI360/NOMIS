/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.facilityovcconsent.controller;

import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.FacilityOvcOffer;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.SiteSetup;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ReferralFacilityManager;
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
public class FacilityOvcConsentAction extends org.apache.struts.action.Action {

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
        FacilityOvcConsentForm focf=(FacilityOvcConsentForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        //User user=appManager.getCurrentUser(session);
        String userName=appManager.getCurrentUserName(session);
        String level2OuId=focf.getLevel2OuId();
        String level3OuId=focf.getLevel3OuId();
        String cboId=focf.getCboId();
        String artNoOfClient=focf.getArtNoOfClient();
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,cboId);
        
        String partnerCode=null;
        SiteSetup setup=ouaManager.getSiteSetup(userName);
        if(setup !=null)
        partnerCode=setup.getPartnerCode();
        String requiredAction=focf.getActionName();
        setButtonState(session,"false","true");
        loadfacility(session,level2OuId,null);
        String clientUniqueId=focf.getClientUniqueId();
        if(requiredAction==null)
        {
            focf.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, cboId);
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            ouaManager.setCBOListByAssignedLevel3Ou(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("clientDetails"))
        {
           FacilityOvcOffer foo= util.getFacilityOvcOfferDaoInstance().getFacilityOvcOfferByTreatmentId(focf.getArtNoOfClient());
           if(foo !=null)
           {
               focf=getFacilityOvcConsentForm(focf, foo);
               setButtonState(session,"true","false");
               return mapping.findForward(SUCCESS);
           }
           else
           {
               Ovc ovc=util.getChildEnrollmentDaoInstance().getOvcByTreatmentId(focf.getArtNoOfClient());
               if(ovc !=null)
               {
                    AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(ovc.getCaregiverId());
                    OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(ovc.getHhe().getOrganizationUnit());
                    focf.reset(mapping, request);
                    focf.setArtNoOfClient(artNoOfClient);
                    focf.setClientAge(ovc.getAgeAtBaseline());
                    focf.setClientAgeUnit(ovc.getAgeUnitAtBaseline());
                    if(ahm !=null)
                    {
                        focf.setCaregiverFirstName(ahm.getFirstName());
                        focf.setCaregiverSurname(ahm.getSurname());
                    }
                    if(ou !=null)
                    {
                        focf.setLevel3OuId(ou.getParentId());
                        ouaManager.setOrganizationUnitAttributes(session, ou.getParentId(),userName,ovc.getHhe().getCboId());
                    }
                    focf.setCboId(ovc.getHhe().getCboId());
                    focf.setClientFirstName(ovc.getFirstName());
                    focf.setClientSurname(ovc.getSurname());
                    //focf.setDateCaregiverSigned(DateManager.getMthDayYearStringDateFormat(foo.getDateCaregiverSigned(),1));
                    focf.setDateEnrolledOnTreatment(DateManager.getMthDayYearStringDateFormat(ovc.getDateEnrolledOnTreatment(),1));
                    //focf.setDateFacilityStaffSigned(DateManager.getMthDayYearStringDateFormat(foo.getDateFacilityStaffSigned(),1));
                    focf.setHivTreatmentFacilityId(ovc.getHivTreatmentFacilityId());
                    focf.setClientSex(ovc.getSex());
                    focf.setArtNoOfClient(ovc.getTreatmentId());
                    focf.setOrganizationUnitId(ovc.getHhe().getOrganizationUnit());
               }
               else
               {
                   focf.reset(mapping, request);
                   focf.setArtNoOfClient(artNoOfClient);
                   focf.setClientUniqueId(clientUniqueId);
               }
               return mapping.findForward(SUCCESS);
           }
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            FacilityOvcOffer foo=getFacilityOvcOffer(focf,partnerCode,userName);
            util.getFacilityOvcOfferDaoInstance().saveFacilityOvcOffer(foo);
            updateBeneficiaryEnrollmentInformation(foo);
            //DataCleanupManager.updateChildEnrollmentRecordsForOVCOfferedClients();
        }
        else if(requiredAction.equalsIgnoreCase("modify"))
        {
            FacilityOvcOffer foo=getFacilityOvcOffer(focf,partnerCode,userName);
            util.getFacilityOvcOfferDaoInstance().updateFacilityOvcOffer(foo);
            updateBeneficiaryEnrollmentInformation(foo);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            util.getFacilityOvcOfferDaoInstance().markForDelete(getFacilityOvcOffer(focf,partnerCode,userName));
        }
        focf.reset(mapping, request);
        return mapping.findForward(SUCCESS);
    }
    private FacilityOvcConsentForm getFacilityOvcConsentForm(FacilityOvcConsentForm form,FacilityOvcOffer foo)
    {
        if(foo !=null)
        {
            form.setClientUniqueId(foo.getClientUniqueId());
            form.setClientAge(foo.getAge());
            form.setClientAgeUnit(foo.getAgeUnit());
            form.setCaregiverFirstName(foo.getCaregiverFirstName());
            form.setCaregiverSurname(foo.getCaregiverSurname());
            form.setCboId(foo.getCboId());
            form.setClientFirstName(foo.getClientFirstName());
            form.setClientSurname(foo.getClientSurname());
            form.setDateCaregiverSigned(DateManager.getMthDayYearStringDateFormat(foo.getDateCaregiverSigned(),1));
            form.setDateEnrolledOnTreatment(DateManager.getMthDayYearStringDateFormat(foo.getDateEnrolledOnTreatment(),1));
            form.setDateFacilityStaffSigned(DateManager.getMthDayYearStringDateFormat(foo.getDateFacilityStaffSigned(),1));
            form.setFacilityStaffFirstName(foo.getFacilityStaffFirstName());
            form.setFacilityStaffSurname(foo.getFacilityStaffSurname());
            form.setHivTreatmentFacilityId(foo.getHivTreatmentFacilityId());
            form.setNameOfPersonToShareContactWith(foo.getNameOfPersonToShareContactWith());

            form.setClientSex(foo.getSex());
            form.setShareContactQuestion(foo.getShareContactQuestion());
            form.setArtNoOfClient(foo.getTreatmentId());
            form.setOrganizationUnitId(foo.getLevel4OuId());
        }
        return form;
    }
    private FacilityOvcOffer getFacilityOvcOffer(FacilityOvcConsentForm form,String partnerCode,String userName)
    {
        FacilityOvcOffer foo=new FacilityOvcOffer();
        foo.setAge(form.getClientAge());
        foo.setAgeUnit(form.getClientAgeUnit());
        foo.setCaregiverFirstName(form.getCaregiverFirstName());
        foo.setCaregiverSurname(form.getCaregiverSurname());
        foo.setCboId(form.getCboId());
        foo.setClientUniqueId(form.getClientUniqueId());
        foo.setClientFirstName(form.getClientFirstName());
        foo.setClientSurname(form.getClientSurname());
        foo.setDateCaregiverSigned(DateManager.getDateInstanceFromMthDayYearFormat(form.getDateCaregiverSigned()));
        foo.setDateEnrolledOnTreatment(DateManager.getDateInstanceFromMthDayYearFormat(form.getDateEnrolledOnTreatment()));
        foo.setDateFacilityStaffSigned(DateManager.getDateInstanceFromMthDayYearFormat(form.getDateFacilityStaffSigned()));
        foo.setFacilityStaffFirstName(form.getFacilityStaffFirstName());
        foo.setFacilityStaffSurname(form.getFacilityStaffSurname());
        foo.setHivTreatmentFacilityId(form.getHivTreatmentFacilityId());
        foo.setLastModifiedDate(DateManager.getCurrentDateInstance());
        foo.setNameOfPersonToShareContactWith(form.getNameOfPersonToShareContactWith());
        foo.setPartnerCode(partnerCode);
        foo.setLevel4OuId(form.getOrganizationUnitId());
        foo.setSex(form.getClientSex());
        foo.setShareContactQuestion(form.getShareContactQuestion());
        foo.setTreatmentId(form.getArtNoOfClient());
        foo.setUserName(userName);
        return foo;
    }
    private void updateBeneficiaryEnrollmentInformation(FacilityOvcOffer foo) throws Exception
    {
        DaoUtility util=new DaoUtility();
        Ovc ovc=util.getChildEnrollmentDaoInstance().getOvcByTreatmentId(foo.getTreatmentId());
           if(ovc !=null)
           {
                ovc.setFirstName(foo.getClientFirstName());
                ovc.setSurname(foo.getClientSurname());
                ovc.setDateEnrolledOnTreatment(foo.getDateEnrolledOnTreatment());
                ovc.setHivTreatmentFacilityId(foo.getHivTreatmentFacilityId());
                ovc.setSex(foo.getSex());
                ovc.setLastModifiedDate(DateManager.getCurrentDateInstance());
                ovc.setEnrollmentSetting(AppConstant.ENROLLMENTSETTING_FACILITY_NUM);
                ovc.setRecordedBy(foo.getUserName());
                util.getChildEnrollmentDaoInstance().updateOvcOnly(ovc);
           }
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("fooSaveDisabled", saveDisabled);
        session.setAttribute("fooModifyDisabled", modifyDisabled);
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
}
