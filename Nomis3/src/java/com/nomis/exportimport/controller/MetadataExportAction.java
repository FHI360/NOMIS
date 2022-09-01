/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport.controller;

import com.nomis.exportimport.MetadataExportManager;
import com.nomis.exportimport.ZipHandler;
import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.CommunityWorker;
import com.nomis.ovc.business.Partner;
import com.nomis.ovc.business.ReferralFacility;
import com.nomis.ovc.business.School;
import com.nomis.ovc.business.SchoolGrade;
import com.nomis.ovc.business.Service;
import com.nomis.ovc.business.User;
import com.nomis.ovc.business.VulnerabilityStatus;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
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
public class MetadataExportAction extends org.apache.struts.action.Action {

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
        MetadataExportForm mdeform=(MetadataExportForm)form;
        HttpSession session=request.getSession();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getDataExportPriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false");
        }
        else
        {
            setButtonState(session,"true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        
        String requiredAction=mdeform.getActionName();
        
        int organizationUnit=mdeform.getOrganizationUnit();
        System.err.println("organizationUnit is "+organizationUnit);
        
        setCBOList(session);
        setSchoolList(session);
        setSchoolGradeList(session);
        setReferralFacilityList(session);
        setVulnerabilityStatusList(session);
        setImplementingPartnerList(session);
        setBeneficiaryServiceList(session);
        setCommunityWorkersList(session);
        setOrganizationList(session);
        
        if(requiredAction==null)
        {
            mdeform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        if(requiredAction.equalsIgnoreCase("export"))
        {
            ZipHandler zipHandler=new ZipHandler();
            AppUtility appUtil=new AppUtility();
            /*String currentDateAndTime=DateManager.getDefaultCurrentDateAndTime();
            if(currentDateAndTime !=null)
            {
                currentDateAndTime=currentDateAndTime.replace(":", "");
                currentDateAndTime=currentDateAndTime.replace(";", "");
                currentDateAndTime=currentDateAndTime.replace("\\", "");
                currentDateAndTime=currentDateAndTime.replace("/", "");
            }*/
            String zipFileName=MetadataExportManager.getMetaDataZipFileName();//"metadata_"+currentDateAndTime;//.getCurrentDate();
            String[] organizationUnits=mdeform.getOrganizationUnits();
            String[] cbos=mdeform.getCbos();
            String[] communityWorkers=mdeform.getCommunityWorkers();
            String[] grades=mdeform.getGrades();
            String[] implementingPartners=mdeform.getImplementingPartners();
            String[] referralFacilities=mdeform.getReferralFacilities();
            String[] schools=mdeform.getSchools();
            String[] vulnerabilityStatus=mdeform.getVulnerabilityStatus();
            String[] beneficiaryServices=mdeform.getBeneficiaryServices();
            DaoUtility util=new DaoUtility();
            if(organizationUnits !=null && organizationUnits.length>0)
            {
                List list=new ArrayList();
                for(int i=0; i<organizationUnits.length; i++)
                {
                    OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(organizationUnits[i]);
                    if(ou !=null)
                    list.add(ou);
                }
                MetadataExportManager.exportOrganizationUnitRecordsInXml(null,list);
            }
            if(cbos !=null && cbos.length>0)
            {
                List cboList=new ArrayList();
                for(int i=0; i<cbos.length; i++)
                {
                    CommunityBasedOrganization cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cbos[i]);
                    if(cbo !=null)
                    cboList.add(cbo);
                }
                MetadataExportManager.exportCBORecordsInXml(null, cboList);
            }
            if(communityWorkers !=null && communityWorkers.length>0)
            {
                List cwList=new ArrayList();
                for(int i=0; i<communityWorkers.length; i++)
                {
                    CommunityWorker cw=util.getCommunityWorkerDaoInstance().getCommunityWorker(communityWorkers[i]);
                    if(cw !=null)
                    cwList.add(cw);
                }
                MetadataExportManager.exportCommunityWorkerRecordsInXml(null, cwList);
            }
            if(schools !=null && schools.length>0)
            {
                List list=new ArrayList();
                for(int i=0; i<schools.length; i++)
                {
                    School school=util.getSchoolDaoInstance().getSchool(schools[i]);
                    if(school !=null)
                    list.add(school);
                }
                MetadataExportManager.exportSchoolRecordsInXml(null, list);
            }
            if(grades !=null && grades.length>0)
            {
                List list=new ArrayList();
                for(int i=0; i<grades.length; i++)
                {
                    SchoolGrade sg=util.getSchoolGradeDaoInstance().getSchoolGrade(grades[i]);
                    if(sg !=null)
                    list.add(sg);
                }
                MetadataExportManager.exportSchoolGradeRecordsInXml(null, list);
            }
            if(implementingPartners !=null && implementingPartners.length>0)
            {
                List list=new ArrayList();
                for(int i=0; i<implementingPartners.length; i++)
                {
                    Partner ip=util.getPartnerDaoInstance().getPartner(implementingPartners[i]);
                    if(ip !=null)
                    list.add(ip);
                }
                MetadataExportManager.exportPartnerRecordsInXml(null, list);
            }
            if(referralFacilities !=null && referralFacilities.length>0)
            {
                List list=new ArrayList();
                for(int i=0; i<referralFacilities.length; i++)
                {
                    ReferralFacility rf=util.getReferralFacilityDaoInstance().getReferralFacility(referralFacilities[i]);
                    if(rf !=null)
                    list.add(rf);
                }
                MetadataExportManager.exportReferralFacilityRecordsInXml(null, list);
            }
            if(vulnerabilityStatus !=null && vulnerabilityStatus.length>0)
            {
                List list=new ArrayList();
                for(int i=0; i<vulnerabilityStatus.length; i++)
                {
                    VulnerabilityStatus vs=util.getVulnerabilityStatusDaoInstance().getVulnerabilityStatus(vulnerabilityStatus[i]);
                    if(vs !=null)
                    list.add(vs);
                }
                MetadataExportManager.exportVulnerabilityStatusRecordsInXml(null, list);
            }
            if(beneficiaryServices !=null && beneficiaryServices.length>0)
            {
                List list=new ArrayList();
                for(int i=0; i<beneficiaryServices.length; i++)
                {
                    Service service=util.getBeneficiaryServiceDaoInstance().getBeneficiaryService(beneficiaryServices[i]);
                    if(service !=null)
                    list.add(service);
                }
                MetadataExportManager.exportBeneficiaryServiceRecordsInXml(null, list);
            }//
            List list=appUtil.copyFilePathsIntoList(appUtil.getMetadataExportFilePath());
            zipHandler.zipFiles(list, appUtil.getDbExportZipDirectory(), zipFileName);
            mdeform.reset(mapping, request);
            request.setAttribute("metadataExportMsg", "Metadata file "+zipFileName+" exported to "+appUtil.getDbExportZipDirectory());
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
    private void setOrganizationList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getOrganizationUnitDaoInstance().getAllOrganizationUnit();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("organizationListForExport", list);
    }
    private void setCBOList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getCommunityBasedOrganizationDaoInstance().getAllCommunityBasedOrganization();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("CBOListForExport", list);
    }
    private void setSchoolList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getSchoolDaoInstance().getAllSchools();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("schoolListForExport", list);
    }
    private void setSchoolGradeList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getSchoolGradeDaoInstance().getAllSchoolGrades();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("schoolGradeListForExport", list);
    }
    private void setReferralFacilityList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getReferralFacilityDaoInstance().getAllReferralFacilities();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("referralFacilityListForExport", list);
    }
    private void setVulnerabilityStatusList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getVulnerabilityStatusDaoInstance().getAllVulnerabilityStatus();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("vulnerabilityStatusListForExport", list);
    }
    private void setImplementingPartnerList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getPartnerDaoInstance().getAllPartners();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("implementingPartnerListForExport", list);
    }
    private void setBeneficiaryServiceList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getBeneficiaryServiceDaoInstance().getAllBeneficiaryServices();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("beneficiaryServiceListForExport", list);
    }
    private void setCommunityWorkersList(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getCommunityWorkerDaoInstance().getAllCommunityWorkers();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("communityWorkersListForExport", list);
    }
    private void setButtonState(HttpSession session,String saveDisabled)
    {
        session.setAttribute("metaDataExportButtonDisabled", saveDisabled);
    }
}
