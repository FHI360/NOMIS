/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport.controller;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.exportimport.MetadataExportManager;
import com.nomis.exportimport.XMLDataExportManager;
import com.nomis.exportimport.ZipHandler;
import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.VersionManager;
import com.nomis.reports.utils.ReportParameterTemplate;
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
public class XMLDataExportAction extends org.apache.struts.action.Action {

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
        XMLDataExportForm xmldeform=(XMLDataExportForm)form;
        HttpSession session=request.getSession();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        String moduleName="Data export";
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        //System.err.println("user.getUserroleId() is "+user.getUserroleId()+" UserPrivilegeManager.getDataExportPriviledge() is "+UserPrivilegeManager.getDataExportPriviledge().getPrivilegeId());
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
        String userName=appManager.getCurrentUserName(session);
        ouaManager.setOrganizationUnitAttributesForReports(session);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
        
        String level2OuId=xmldeform.getLevel2OuId();
        String level3OuId=xmldeform.getLevel3OuId();
        String level4OuId=xmldeform.getOrganizationUnitId();
        String cboId=xmldeform.getCboId();
        String partnerCode=xmldeform.getPartnerCode();
        String startDate=DateManager.processMthDayYearToMysqlFormat(xmldeform.getStartDate());
        String endDate=DateManager.processMthDayYearToMysqlFormat(xmldeform.getEndDate());
        int entirePeriod=xmldeform.getEntirePeriod();
        if(entirePeriod==1)
        {
            startDate=null;
            endDate=null;
        }
        ouaManager.setOrganizationUnitAttributes(session, level3OuId, userName, cboId);
        //ouaManager.setOrganizationUnitAttributesByOuId(session, level2OuId, level3OuId, level4OuId,cboId);
        
        String requiredAction=xmldeform.getActionName();
        System.err.println("requiredAction is "+requiredAction);
        
        if(requiredAction==null)
        {
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList") || requiredAction.equalsIgnoreCase("cboList"))
        {
            ouaManager.getLevel3OrganizationUnitForReports(session,level2OuId);
            ouaManager.getLevel4OrganizationUnitForReports(session,null);
            ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            session.setAttribute("assignedLevel3OuList", new ArrayList());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.getLevel4OrganizationUnitForReports(session,level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("dbexport"))
        {
            
            AppUtility appUtil=new AppUtility();
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setCboId(cboId);
            rpt.setLevel2OuId(level2OuId);
            rpt.setLevel3OuId(level3OuId);
            rpt.setLevel4OuId(level4OuId);
            rpt.setStartDate(startDate);
            rpt.setEndDate(endDate);
            rpt.setPartnerCode(partnerCode);
            if(entirePeriod==1)
            {
                rpt.setStartDate(null);
                rpt.setEndDate(null);
            }
            
            appUtil.deleteFiles(appUtil.getExportFilePath());
            appUtil.createExportImportDirectories();
            XMLDataExportManager xmlde=new XMLDataExportManager();
            List metadataList=new ArrayList();
            int numberOfHousehold=xmlde.exportHouseholdEnrollmentRecordsInXml(null, rpt,0);
            int numberOfAdults=xmlde.exportAdultHouseholdMemberRecordsInXml(null, rpt,0);
            int numberOfHouseholdAssessments=xmlde.exportHouseholdVulnerabilityAssessmentRecordsInXml(null, rpt,0);
            int numberOfRevisedHouseholdAssessments=xmlde.exportRevisedHouseholdVulnerabilityAssessmentRecordsInXml(null, rpt,0);
            int numberOfOvcRecords=xmlde.exportOvcRecordsInXml(null, rpt,0);
            int numberOfChildService=xmlde.exportOvcServiceRecordsInXml(null, rpt,0);
            int numberOfHouseholdService=xmlde.exportHouseholdServiceRecordsInXml(null, rpt,0);
            int numberOfHivRiskAssessment=xmlde.exportHivRiskAssessmentRecordsInXml(null, rpt,0);
            int numberOfHouseholdReferralRecords=xmlde.exportReferralRecordsInXml(null, rpt,0);
            int numberOfCaregiverEmergencyFund=xmlde.exportCaregiverAccessToEmergencyFundRecordsInXml(null, rpt,0);
            int numberOfCareplanAchievementChecklist=xmlde.exportCareplanAchievementChecklistRecordsInXml(null, rpt,0);
            int numberOfChildEducationAssessment=xmlde.exportChildEducationPerformanceAssessmentRecordsInXml(null, rpt,0);
            int numberOfHivCareAndSupport=xmlde.exportHivCareAreSupportRecordsInXml(null, rpt,0);
            int numberOfBeneficiaryStatusUpdate=xmlde.exportBeneficiaryStatusUpdateRecordsInXml(null, rpt,0);
            int numberOfNutritionAssessments=xmlde.exportNutritionAssessmentRecordsInXml(null, rpt,0);
            int numberOfHouseholdCareplanRecords=xmlde.exportHouseholdCareplanRecordsInXml(null, rpt,0);
            int numberOfFacilityOvcOfferRecords=xmlde.exportFacilityOvcOfferRecordsInXml(null, rpt,0);
            
            String zipFileName=getZipFileName(level2OuId,level3OuId,level4OuId,cboId,startDate,endDate);
            
            
            metadataList.add("State");
            metadataList.add(rpt.getLevel2OuId());
            metadataList.add("LGA");
            metadataList.add(rpt.getLevel3OuId());
            metadataList.add("Ward");
            metadataList.add(rpt.getLevel4OuId());
            metadataList.add("cbo");
            metadataList.add(rpt.getCboId());
            metadataList.add("StartDate");
            metadataList.add(rpt.getStartDate());
            metadataList.add("EndDate");
            metadataList.add(rpt.getEndDate());
            metadataList.add("ZipFileName");
            metadataList.add(zipFileName);
            metadataList.add("Version");
            metadataList.add(VersionManager.getVersionNumber());
            metadataList.add("DateAndTimeOfExport");
            metadataList.add(DateManager.getDefaultCurrentDateAndTime());
            
            metadataList.add("numberOfHousehold");
            metadataList.add(numberOfHousehold);
            metadataList.add("numberOfAdults");
            metadataList.add(numberOfAdults);
            metadataList.add("numberOfHouseholdAssessments");
            metadataList.add(numberOfHouseholdAssessments);
            metadataList.add("numberOfRevisedHouseholdAssessments");
            metadataList.add(numberOfRevisedHouseholdAssessments);
            metadataList.add("numberOfOvcRecords");
            metadataList.add(numberOfOvcRecords);
            metadataList.add("numberOfChildService");
            metadataList.add(numberOfChildService);
            metadataList.add("numberOfHouseholdService");
            metadataList.add(numberOfHouseholdService);
            metadataList.add("numberOfHivRiskAssessment");
            metadataList.add(numberOfHivRiskAssessment);
            metadataList.add("numberOfHouseholdReferralRecords");
            metadataList.add(numberOfHouseholdReferralRecords);
            metadataList.add("numberOfCaregiverEmergencyFund");
            metadataList.add(numberOfCaregiverEmergencyFund);
            metadataList.add("numberOfCareplanAchievementChecklist");
            metadataList.add(numberOfCareplanAchievementChecklist);
            metadataList.add("numberOfChildEducationAssessment");
            metadataList.add(numberOfChildEducationAssessment);
            metadataList.add("numberOfHivCareAndSupport");
            metadataList.add(numberOfHivCareAndSupport);
            metadataList.add("numberOfBeneficiaryStatusUpdate");
            metadataList.add(numberOfBeneficiaryStatusUpdate);
            metadataList.add("numberOfNutritionAssessment");
            metadataList.add(numberOfNutritionAssessments);
            metadataList.add("numberOfHouseholdCareplanRecords");
            metadataList.add(numberOfHouseholdCareplanRecords);
            metadataList.add("numberOfFacilityOvcOfferRecords");
            metadataList.add(numberOfFacilityOvcOfferRecords);
            metadataList.add("Createdby");
            metadataList.add(userName);
            //metadataList.add("DateOfExport");
            //metadataList.add(DateManager.getCurrentDate());
            
            int numberOfExportSummaryRecords=xmlde.writeExportSummaryInXml(null, metadataList);
            //export all meta data to default export location
            String metadataZipFilePath=MetadataExportManager.exportMetadata(null, null);
            
            zipFiles(metadataZipFilePath,zipFileName);
            saveUserActivity(userName,moduleName,"Exported file named "+zipFileName);
            request.setAttribute("dbexportmsg", "Data exported to "+appUtil.getDbExportZipDirectory());
        }
        return mapping.findForward(SUCCESS);
    }
private void zipFiles(String metadataZipFilePath,String fileName)
{
    ZipHandler zipHandler=new ZipHandler();
    AppUtility appUtil=new AppUtility();
    List listOfFilesToBeZipped=appUtil.copyFilePathsIntoList(appUtil.getExportFilePath());
    if(metadataZipFilePath !=null && metadataZipFilePath.endsWith(".zip"))
    listOfFilesToBeZipped.add(metadataZipFilePath);
    zipHandler.zipFile(listOfFilesToBeZipped, appUtil.getDbExportZipDirectory(), fileName);
}
private void saveUserActivity(String userName,String userAction,String description)
{
    UserActivityManager uam=new UserActivityManager();
    uam.saveUserActivity(userName, userAction,description);
}
private String getZipFileName(String level2OuId,String level3OuId,String level4OuId,String cboId,String startDate,String endDate)
{
    String zipFileName="";
    try
    {
        String level2HirarchyName="State";
        String level3HirarchyName="LGA";
        String level4HirarchyName="Ward";
        String period=startDate+"-"+endDate;
        if(startDate ==null || endDate ==null)
        {
            period="All";
        }
        DaoUtility util=new DaoUtility();
        String provinceName="All";
        String districtName="All";
        String wardName="All";
        String cboName="All";
        OrganizationUnit ou=null;
        ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level2OuId);
        if(ou !=null)
        provinceName=ou.getName();
        ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level3OuId);
        if(ou !=null)
        districtName=ou.getName();
        ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level4OuId);
        if(ou !=null)
        wardName=ou.getName();
        CommunityBasedOrganization cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboId);
        if(cbo !=null)
        cboName=cbo.getCboName();
        zipFileName=level2HirarchyName+"-"+provinceName+"-"+level3HirarchyName+"-"+districtName+"-"+level4HirarchyName+"-"+wardName+"-LIP-"+cboName+"-Period-"+period;
            
    }
    catch(Exception ex)
    {
        NomisLogManager.logStackTrace(ex);
    }
    return zipFileName;
}
private void setButtonState(HttpSession session,String saveDisabled)
{
    session.setAttribute("xmlDataExportButtonDisabled", saveDisabled);
    //session.setAttribute("userModifyDisabled", modifyDisabled);
}
}
