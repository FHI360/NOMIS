/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.BeneficiaryDetailsManager;
import com.nomis.operationsManagement.BeneficiaryManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.CareAndSupportChecklist;
import com.nomis.ovc.business.CaregiverAccessToEmergencyFund;
import com.nomis.ovc.business.CareplanAchievementChecklist;
import com.nomis.ovc.business.ChildEducationPerformanceAssessment;
import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.business.FacilityOvcOffer;
import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.business.HouseholdCareplan;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.HouseholdReferral;
import com.nomis.ovc.business.HouseholdService;
import com.nomis.ovc.business.NutritionAssessment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.SubQueryGenerator;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ExcelWriter;
import com.nomis.reports.utils.MonthlySummaryFormReport;
import com.nomis.reports.utils.ReportParameterManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.write.WritableWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class OvcRegisterAction extends org.apache.struts.action.Action {

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
        OvcRegisterForm hhrf=(OvcRegisterForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        ReportParameterManager rpm=new ReportParameterManager();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        String requiredAction=hhrf.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        //User user=appManager.getCurrentUser(session);
        String userName=appManager.getCurrentUserName(session);
        User user=appManager.getCurrentUser(session);
        if(user !=null)
        userName=user.getUsername();
        else
        System.err.println("user is null. user name is "+userName);
        
        String level2OuId=hhrf.getLevel2OuId();
        String level3OuId=hhrf.getLevel3OuId();
        String level4OuId=hhrf.getOrganizationUnitId();
        String cboId=hhrf.getCboId();
        String partnerCode=hhrf.getPartnerCode();
        
        String startDate=DateManager.processMthDayYearToMysqlFormat(hhrf.getStartDate());
        String endDate=DateManager.processMthDayYearToMysqlFormat(hhrf.getEndDate());
        
        setButtonState(session,"false", "true");
        if(user ==null || user.getReportGenerationDisabled() ==AppConstant.REPORTGENERATIONDISABLED_NUM)
        {
            level2OuId=null;
            level3OuId=null;
            level4OuId=null;
            cboId=null;
            partnerCode=null;
            ouaManager.emptyReportAttributes(request);
            setButtonState(session,"true", "true");
            requiredAction=null;
         }
        
        
        ReportParameterTemplate rpt=new ReportParameterTemplate();
        rpt.setCboId(cboId);
        rpt.setLevel2OuId(level2OuId);
        rpt.setLevel3OuId(level3OuId);
        rpt.setLevel4OuId(level4OuId);
        rpt.setStartDate(startDate);
        rpt.setEndDate(endDate);
        
        SubQueryGenerator sqg=new SubQueryGenerator();
        String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
        
        if(AccessManager.isDue())
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.LICENCE_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        else
        {
            setButtonState(session,"false","true");
        }
        String registerType=hhrf.getRegisterType();
        //ouaManager.getLevel2OrganizationUnitForReports(session);
        ouaManager.setOrganizationUnitAttributesByOuId(request, level2OuId, level3OuId, level4OuId,cboId);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
                
        String reportPeriod=DateManager.getDateParameter(startDate, endDate);
        request.setAttribute("reportPeriod", reportPeriod);
        
        System.err.println("requiredAction is "+requiredAction);
        System.err.println("registerType is "+registerType);
        String id=request.getParameter("id");
        String ovcId=null;
        String cgiverId=null;
        String hhUniqueId=null;
        if(id !=null )
        {
            requiredAction=id;
            if(id.indexOf(":") !=-1)
            {
                String[] paramArray=id.split(":");
                requiredAction=paramArray[0];
                if(paramArray.length>1)
                {
                    if(requiredAction.equalsIgnoreCase("childHistory"))
                    ovcId=paramArray[1];
                    else if(requiredAction.equalsIgnoreCase("cgHistory"))
                    cgiverId=paramArray[1];
                    else
                    hhUniqueId=paramArray[1];
                }
            }
            System.err.println("requiredAction is "+requiredAction);
        }
        if(requiredAction==null)
        {
            //DateManager dm=new DateManager();
            //dm.getDifferenceInDays("2020-10-01", DateManager.getCurrentDate(),DateManager.DB_DATE_FORMAT);
            hhrf.reset(mapping, request);
            if(user !=null)
            {
                //System.err.println("User is not null");
                if(user.getReportGenerationDisabled() !=AppConstant.REPORTGENERATIONDISABLED_NUM)
                {
                    System.err.println("user.getReportLevel2OuId() is "+user.getReportLevel2OuId());
                    ouaManager.setOrganizationUnitAttributesForReports(session,user.getReportLevel2OuId(),level3OuId,level4OuId,cboId,user.getReportPartner());
                    setButtonState(session,"false","false");
                }
            }
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("cboList"))
        {
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            //ouaManager.getLevel3OrganizationUnitForReports(session,level2OuId);
            //ouaManager.getLevel4OrganizationUnitForReports(session,null);
            //ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            
            session.setAttribute("assignedLevel3OuList", new ArrayList());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            //ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            //ouaManager.setAssignedLevel3Ou(session, hhrf.getCboId());
            //session.setAttribute("assignedLevel3OuList", new ArrayList());
            //ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            //ouaManager.getLevel4OrganizationUnitForReports(session,level3OuId);
            //ouaManager.setLevel4OuList(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("register"))
        {
            if(registerType.equalsIgnoreCase("monthlySummaryForm"))
            {
                MonthlySummaryFormReport msr=new MonthlySummaryFormReport();
                //DateManager dm=new DateManager();
                int ageSegType=0;
                //OvcReportManager orm=new OvcReportManager();
                List paramList=new ArrayList();

                rpt.setLevel2OuId(level2OuId);
                rpt.setLevel3OuId(level3OuId);
                rpt.setLevel4OuId(level4OuId);
                rpt.setCboId(cboId);
                rpt.setStartDate(startDate); 
                rpt.setEndDate(endDate);
                rpt.setPartnerCode(null);

                paramList.add(level2OuId);
                paramList.add(level3OuId);
                paramList.add(cboId);
                paramList.add(level4OuId);
                /*paramList.add(startMth);
                paramList.add(startYear);
                paramList.add(endMth);
                paramList.add(endYear);
                paramList.add(partnerCode);*/
                List list=msr.getOvcMthlySummaryData(rpt,ageSegType+"");
                //orm.getOvcMthlySummaryData(session,rpt,paramList,ageSegType+"");
                session.setAttribute("mthSummaryReportTemplateList", list);
                //if(ageSegType==1)
                return mapping.findForward("monthlySummaryForm");
            }
            else if(registerType.equalsIgnoreCase("facilityOvcOfferRegister"))
            {
                BeneficiaryManager bfm=new BeneficiaryManager();
                bfm.displayBeneficiaryBiodata(request, user);
                DaoUtility daoutil=new DaoUtility();
                List reportList=new ArrayList();
                List list=daoutil.getFacilityOvcOfferDaoInstance().getFacilityOvcOfferedRecords(rpt);
                if(list==null)
                list=new ArrayList();
                FacilityOvcOffer foo=null;
                for(int i=0; i<list.size(); i++)
                {
                    foo=(FacilityOvcOffer)list.get(i);
                    //ovc=BeneficiaryDetailsManager.getPreparedOvc(ovc, user);
                    foo.setSerialNo(i+1);
                    if(i%2>0)
                    foo.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    reportList.add(foo);
                }
                session.setAttribute("facilityOvcOfferListForRegister", reportList);
                request.setAttribute("reportType", "Facility OVC Offer Register");
                hhrf.reset(mapping, request);
                return mapping.findForward("facilityovcofferregister");
            }
            else if(registerType.equalsIgnoreCase("childRegister"))
            {
                BeneficiaryManager bfm=new BeneficiaryManager();
                bfm.displayBeneficiaryBiodata(request, user);
                DaoUtility daoutil=new DaoUtility();
                List hheList=new ArrayList();
                List list=daoutil.getChildEnrollmentDaoInstance().getOvcRecords(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, AppConstant.HIV_ALL_STATUS_NUM, startDate, endDate, 0, 25, null, false);
                if(list==null)
                list=new ArrayList();
                Ovc ovc=null;
                for(int i=0; i<list.size(); i++)
                {
                    ovc=(Ovc)list.get(i);
                    ovc=BeneficiaryDetailsManager.getPreparedOvc(ovc, user);
                    ovc.setSerialNo(i+1);
                    if(i%2>0)
                    ovc.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    hheList.add(ovc);
                }
                session.setAttribute("ovcListForRegister", hheList);
                request.setAttribute("reportType", "Child Register");
                hhrf.reset(mapping, request);
                return mapping.findForward("childregister");
            }
            else if(registerType.equalsIgnoreCase("caregiverRegister"))
            {
                DaoUtility daoutil=new DaoUtility();
                List beneficiaryList=new ArrayList();
                List idList=new ArrayList();
                List list=daoutil.getAdultHouseholdMemberDaoInstance().getAllAdultHouseholdMembers(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 200, null, false);
                //.getAllAdultHouseholdMembers();
                if(list==null)
                list=new ArrayList();
                AdultHouseholdMember ahm=null;
                for(int i=0; i<list.size(); i++)
                {
                    ahm=(AdultHouseholdMember)list.get(i);
                    if(!idList.contains(ahm.getBeneficiaryId()))
                    {
                        ahm=BeneficiaryDetailsManager.getPreparedAdultHouseholdMember(ahm, user);
                        ahm.setSerialNo(i+1);
                        if(i%2>0)
                        ahm.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                        beneficiaryList.add(ahm);
                        idList.add(ahm.getBeneficiaryId());
                    }
                }
                request.setAttribute("reportType", "Caregiver Register");
                session.setAttribute("cgiverListForRegister", beneficiaryList);
                hhrf.reset(mapping, request);
                return mapping.findForward("cgiverRegister");
            }
            else if(registerType.equalsIgnoreCase("householdRegister"))
            {
                DaoUtility daoutil=new DaoUtility();
                List hheList=new ArrayList();
                List list=daoutil.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollmentAndRevisedAssessmentRecords(rpt);
                
                if(list==null)
                list=new ArrayList();
                HouseholdEnrollment hhp=null;
                //HouseholdEnrollment hhe=null;
                for(int i=0; i<list.size(); i++)
                {
                    hhp=(HouseholdEnrollment)list.get(i);
                    hhp.setSerialNo(i+1);
                    if(i%2>0)
                    hhp.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    hheList.add(hhp);
                }
                System.err.println("list.size() is "+list.size());
                System.err.println("hheList.size() is "+hheList.size());
                request.setAttribute("reportType", "Household Register");
                session.setAttribute("hheListForRegister", hheList);
                hhrf.reset(mapping, request);
                return mapping.findForward("householdRegister");
            }
            else if(registerType.equalsIgnoreCase("ovcServiceRegister"))
            {
                DaoUtility daoutil=new DaoUtility();
                List serviceList=new ArrayList();
                List list=daoutil.getChildServiceDaoInstance().getListOfOvcServedByEnrollmentStatusAndHivStatus(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 24, null, AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
                if(list==null)
                list=new ArrayList();
                ChildService service=null;
                for(int i=0; i<list.size(); i++)
                {
                    service=(ChildService)list.get(i);
                    service.setSerialNo(i+1);
                    if(i%2>0)
                    service.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    serviceList.add(service);
                }
                request.setAttribute("reportType", "OVC Service Register");
                session.setAttribute("ovcServiceListForRegister", serviceList);
                hhrf.reset(mapping, request);
                return mapping.findForward("ovcServiceRegister");
            }
            else if(registerType.equalsIgnoreCase("referralRegister"))
            {
                DaoUtility daoutil=new DaoUtility();
                List serviceList=new ArrayList();
                List list=daoutil.getHouseholdReferralDaoInstance().getHouseholdReferrals(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 24, null, false);
                if(list==null)
                list=new ArrayList();
                HouseholdReferral referral=null;
                for(int i=0; i<list.size(); i++)
                {
                    referral=(HouseholdReferral)list.get(i);
                    referral.setSerialNo(i+1);
                    if(i%2>0)
                    referral.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    serviceList.add(referral);
                }
                request.setAttribute("reportType", "Household Referral Register");
                session.setAttribute("ReferralServiceListForRegister", serviceList);
                hhrf.reset(mapping, request);
                return mapping.findForward("referralRegister");
            }
            else if(registerType.equalsIgnoreCase("householdServiceRegister"))
            {
                DaoUtility daoutil=new DaoUtility();
                List serviceList=new ArrayList();
                List list=daoutil.getHouseholdServiceDaoInstance().getHouseholdServices(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 200, null, false);
                if(list==null)
                list=new ArrayList();
                HouseholdService service=null;
                for(int i=0; i<list.size(); i++)
                {
                    service=(HouseholdService)list.get(i);
                    service.setSerialNo(i+1);
                    if(i%2>0)
                    service.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    serviceList.add(service);
                }
                request.setAttribute("reportType", "Household Service Register");
                session.setAttribute("householdServiceListForRegister", serviceList);
                hhrf.reset(mapping, request);
                return mapping.findForward("householdServiceRegister");
            }
            else if(registerType.equalsIgnoreCase("hivRiskAssessmentRegister"))
            {
                System.err.println("registerType in the inner block is "+registerType);
                DaoUtility daoutil=new DaoUtility();
                List hraList=new ArrayList();
                List list=daoutil.getHivRiskAssessmentDaoInstance().getHivRiskAssessmentRecords(rpt);
                //.getHouseholdServiceDaoInstance().getHouseholdServices(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 200, null, false);
                if(list==null)
                list=new ArrayList();
                HivRiskAssessment hra=null;
                for(int i=0; i<list.size(); i++)
                {
                    hra=(HivRiskAssessment)list.get(i);
                    hra.setSerialNo(i+1);
                    if(i%2>0)
                    hra.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    hraList.add(hra);
                }
                request.setAttribute("reportType", "HIV Risk assessment Register");
                session.setAttribute("hraForRegister", hraList);
                hhrf.reset(mapping, request);
                System.err.println("hraRegister is hraRegister");
                return mapping.findForward("hraRegister");
            }
            else if(registerType.equalsIgnoreCase("careAndSupportRegister"))
            {
                System.err.println("registerType in the inner block is "+registerType);
                DaoUtility daoutil=new DaoUtility();
                List hraList=new ArrayList();
                List list=daoutil.getCareAndSupportChecklistDaoInstance().getCareAndSupportRecords(rpt);
                //.getHouseholdServiceDaoInstance().getHouseholdServices(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 200, null, false);
                if(list==null)
                list=new ArrayList();
                CareAndSupportChecklist casc=null;
                for(int i=0; i<list.size(); i++)
                {
                    casc=(CareAndSupportChecklist)list.get(i);
                    casc.setSerialNo(i+1);
                    if(i%2>0)
                    casc.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    hraList.add(casc);
                }
                request.setAttribute("reportType", "Care and Support Register");
                session.setAttribute("careAndSupportListForRegister", hraList);
                hhrf.reset(mapping, request);
                //System.err.println("hraRegister is hraRegister");
                return mapping.findForward("careAndSupportRegister");
            }
            else if(registerType.equalsIgnoreCase("careAndSupportExcelTemplate"))
            {
                //DaoUtility util=new DaoUtility();
                //int count=util.getCareAndSupportChecklistDaoInstance().removeRecordsOfNonPositiveBeneficiaries();
                List list=util.getCareAndSupportChecklistDaoInstance().getMostRecentCareAndSupportRecords(rpt);
                //System.err.println(count+" records whose HIV status changed from positive deleted");
                if(list !=null)
                {
                    try
                    {
                        String fileName="CareAndSupportReport"+DateManager.getDefaultCurrentDateAndTime();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
                        OutputStream os=response.getOutputStream();
                        ExcelWriter ew=new ExcelWriter();
                        WritableWorkbook wworkbook=ew.writeCareAndSupportRegisterToExcel(os, list) ;
                        if(wworkbook !=null)
                        {
                            wworkbook.write();
                            wworkbook.close();
                        }
                        os.close();
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    hhrf.reset(mapping, request);
                }
                return null;
            }
            else if(registerType.equalsIgnoreCase("nutritionAssessment"))
            {
                DaoUtility daoutil=new DaoUtility();
                List naList=new ArrayList();
                List list=daoutil.getNutritionalAssessmentDaoInstance().getNutritionAssessmentRecords(rpt);
                
                if(list==null)
                list=new ArrayList();
                NutritionAssessment na=null;
                for(int i=0; i<list.size(); i++)
                {
                    na=(NutritionAssessment)list.get(i);
                    na.setSerialNo(i+1);
                    if(i%2>0)
                    na.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    naList.add(na);
                }
                request.setAttribute("reportType", "Nutrition assessment Register");
                session.setAttribute("nutritionAssessmentListForRegister", naList);
                hhrf.reset(mapping, request);
                return mapping.findForward("nutritionAssessmentRegister");
                
            }
            else if(registerType.equalsIgnoreCase("caregiverAccessToEmergencyFund"))
            {
                DaoUtility daoutil=new DaoUtility();
                List caefList=new ArrayList();
                List list=daoutil.getCaregiverAccessToEmergencyFundDaoInstance().getCaregiverAccessToEmergencyFundRecords(rpt);
                
                if(list==null)
                list=new ArrayList();
                CaregiverAccessToEmergencyFund caef=null;
                for(int i=0; i<list.size(); i++)
                {
                    caef=(CaregiverAccessToEmergencyFund)list.get(i);
                    caef.setSerialNo(i+1);
                    if(i%2>0)
                    caef.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    caefList.add(caef);
                }
                request.setAttribute("reportType", "Caregiver access to emergency fund Register");
                session.setAttribute("caregiverAccessToEmergencyFundListForRegister", caefList);
                hhrf.reset(mapping, request);
                return mapping.findForward("caregiverAccessToEmergencyFundRegister");
                
            }
            else if(registerType.equalsIgnoreCase("careplanAchievementChecklist"))
            {
                DaoUtility daoutil=new DaoUtility();
                List cpaList=new ArrayList();
                List list=daoutil.getCareplanAchievementChecklistDaoInstance().getCareplanAchievementChecklistRecords(rpt);
                
                if(list==null)
                list=new ArrayList();
                CareplanAchievementChecklist cpa=null;
                for(int i=0; i<list.size(); i++)
                {
                    cpa=(CareplanAchievementChecklist)list.get(i);
                    cpa.setSerialNo(i+1);
                    if(i%2>0)
                    cpa.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    cpaList.add(cpa);
                }
                request.setAttribute("reportType", "Careplan achievement Register");
                session.setAttribute("careplanAchievementListForRegister", cpaList);
                hhrf.reset(mapping, request);
                return mapping.findForward("careplanAchievementRegister"); 
            }
            else if(registerType.equalsIgnoreCase("householdCarePlanRegister"))
            {
                DaoUtility daoutil=new DaoUtility();
                List cpaList=new ArrayList();
                List list=daoutil.getHouseholdCareplanDaoInstance().getHouseholdCareplanRecordsForExport(rpt,0);
                
                if(list==null)
                list=new ArrayList();
                HouseholdCareplan hcp=null;
                for(int i=0; i<list.size(); i++)
                {
                    hcp=(HouseholdCareplan)list.get(i);
                    hcp.setSerialNo(i+1);
                    if(i%2>0)
                    hcp.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    cpaList.add(hcp);
                }
                request.setAttribute("reportType", "Household care plan Register");
                session.setAttribute("householdCareplanListForRegister", cpaList);
                hhrf.reset(mapping, request);
                return mapping.findForward("householdCareplanRegister"); 
            }
            else if(registerType.equalsIgnoreCase("educationalAssessmentRegister"))
            {
                System.err.println("registerType in the inner block is "+registerType);
                DaoUtility daoutil=new DaoUtility();
                List cepaList=new ArrayList();
                List list=daoutil.getChildEducationPerformanceAssessmentDaoInstance().getChildEducationPerformanceAssessmentRecords(rpt);
                //.getHouseholdServiceDaoInstance().getHouseholdServices(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 200, null, false);
                if(list==null)
                list=new ArrayList();
                ChildEducationPerformanceAssessment cepa=null;
                for(int i=0; i<list.size(); i++)
                {
                    cepa=(ChildEducationPerformanceAssessment)list.get(i);
                    cepa.setSerialNo(i+1);
                    if(i%2>0)
                    cepa.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    cepaList.add(cepa);
                }
                request.setAttribute("reportType", "Educational Performance assessment Register");
                session.setAttribute("cepaListForRegister", cepaList);
                hhrf.reset(mapping, request);
                //System.err.println("hraRegister is hraRegister");
                return mapping.findForward("cepaRegister");
            }
            /*else if(registerType.equalsIgnoreCase("graduationBenchmark"))
            {
                System.err.println("registerType in the inner block is "+registerType);
                DaoUtility daoutil=new DaoUtility();
                List gbmList=new ArrayList();
                List list=daoutil.getGraduationBenchmarkAchievementDaoInstance().getGraduationBenchmarkAchievementRecords();
                //.getHouseholdServiceDaoInstance().getHouseholdServices(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 200, null, false);
                if(list==null)
                list=new ArrayList();
                GraduationBenchmarkAchievement gbm=null;
                for(int i=0; i<list.size(); i++)
                {
                    gbm=(GraduationBenchmarkAchievement)list.get(i);
                    gbm.setSerialNo(i+1);
                    if(i%2>0)
                    gbm.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    gbmList.add(gbm);
                }
                request.setAttribute("reportType", "Graduation Benchmark Register");
                session.setAttribute("gbmForRegister", gbmList);
                hhrf.reset(mapping, request);
                //System.err.println("hraRegister is hraRegister");
                return mapping.findForward("gbmRegister");
            }
            else if(registerType.equalsIgnoreCase("trainingRegister"))
            {
                System.err.println("registerType in the inner block is "+registerType);
                DaoUtility daoutil=new DaoUtility();
                List trnList=new ArrayList();
                List list=daoutil.getTrainingDaoInstance().getTrainings();
                //.getHouseholdServiceDaoInstance().getHouseholdServices(orgUnitQuery, AppConstant.EVER_ENROLLED_NUM, 0, startDate, endDate, 0, 200, null, false);
                if(list==null)
                list=new ArrayList();
                Training trn=null;
                for(int i=0; i<list.size(); i++)
                {
                    trn=(Training)list.get(i);
                    trn.setSerialNo(i+1);
                    if(i%2>0)
                    trn.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    trnList.add(trn);
                }
                request.setAttribute("reportType", "Training Register");
                session.setAttribute("trainingListForRegister", trnList);
                hhrf.reset(mapping, request);
                //System.err.println("hraRegister is hraRegister");
                return mapping.findForward("trainingRegister");
            }
        }*/
        }
        return mapping.findForward(SUCCESS);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("regSaveDisabled", saveDisabled);
    }
}
