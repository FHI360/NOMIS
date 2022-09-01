/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.controller;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.business.Partner;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.CustomIndicatorsReportDao;
import com.nomis.ovc.dao.CustomIndicatorsReportDaoImpl;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.CustomIndicatorsReportManagerThread;
import com.nomis.reports.utils.IndicatorDictionary;
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
public class CustomIndicatorsDataGenerationAction extends org.apache.struts.action.Action {

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
        CustomIndicatorsDataGenerationForm cidgform=(CustomIndicatorsDataGenerationForm)form;
        AppUtility appUtil=new AppUtility();
        DaoUtility util=new DaoUtility();
        DateManager dm=new DateManager();
        //cidgform
        HttpSession session=request.getSession();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        ouaManager.getLevel2OrganizationUnitForReports(session);
        ReportParameterTemplate rpt=new ReportParameterTemplate();
        AccessManager acm=new AccessManager();
        //OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();
        //String partnerCode=cidgform.getPartnerCode();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        //User user=appUtil.getCurrentUser(session);
        String userName=null;
        if(user !=null)
        userName=user.getUsername();
        //if(!acm.isUserInRole("cirb"))
        session.setAttribute("cirbGenButtonDisabled", "true");
        //else
        //session.setAttribute("cirbGenButtonDisabled", "false");
        
        //acm.setStateListForReports(session);
        
        int startMth=appUtil.getMonthAsNumber(cidgform.getStartMth());
        int startYear=cidgform.getStartYear();
        int endMth=appUtil.getMonthAsNumber(cidgform.getEndMth());
        int endYear=cidgform.getEndYear();
        //String mainReportPeriod=dm.getMonthAsString(startMth)+" "+startYear+"-"+dm.getMonthAsString(endMth)+" "+endYear;
        String selectedPartner=cidgform.getPartnerCode();
        
        rpt.setStartMth(startMth);
        rpt.setStartYear(startYear);
        rpt.setEndMth(endMth);
        rpt.setEndYear(endYear);
        String startDate=dm.getStartDate(startMth, startYear);
        String endDate=dm.getEndDate(endMth, endYear);
        rpt.setStartDate(startDate);
        rpt.setEndDate(endDate);
        rpt.setPartnerCode(selectedPartner);
        rpt.setReportType(AppConstant.CUSTOMINDICATORS_REPORTTYPE);
        //String[] dateParams={startMth+"",startYear+"",endMth+"",endYear+""};
        //String startDate=util.getStartDate(dateParams);
        //String endDate=util.getEndDate(dateParams);
        String[] selectedLevel2Ous=cidgform.getLevel2OuCodes();
        String[] selectedIndicators=cidgform.getIndicators();
        
        String requiredAction=cidgform.getActionName();
        List listOfMonths=dm.generateMonths(1);
        List listOfYears=DateManager.generateYears();
        session.setAttribute("generatedYears", listOfYears);
        session.setAttribute("generatedMonths", listOfMonths);
        //String currentUser=appUtil.getCurrentUser(session);
        
        List partnerList=ouaManager.getPartnerListForReports(rpt);
        session.setAttribute("cidgPartnerList", partnerList);
        //if(currentUser==null)
        //return mapping.findForward("login");
        
        setPartnerList(user,session);
        List indicatorList=CustomIndicatorsReportManagerThread.getCustomIndicators();
        session.setAttribute("customIndicatorList", indicatorList);
        
        setButtonState(session,"false", "true");
        if(user ==null || user.getReportGenerationDisabled() ==AppConstant.REPORTGENERATIONDISABLED_NUM)
        {
            List emptyList=new ArrayList();
            session.setAttribute("cidgPartnerList",emptyList);
            session.setAttribute("customIndicatorList", emptyList);
            setButtonState(session,"true", "true");
            requiredAction=null;
         }
        
        System.err.println("requiredAction is "+requiredAction);
        
        if(requiredAction==null)
        {
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level2OuList"))
        {
            //ouaManager.setOrganizationUnitAttributesForReports(session,level2OuId,level3OuId,level4OuId,cboId,partnerCode);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("deleteData"))
        {
            String level2OuId=null;
            int count=0;
            String level2OuName=null;
            CustomIndicatorsReportDao cirbdao=new CustomIndicatorsReportDaoImpl();
            for(int j=0; j<selectedLevel2Ous.length; j++)
            {
               level2OuId=selectedLevel2Ous[j];
               //level2OuName=ouaManager.getOrganizationUnitName(level2OuId);
               count+=cirbdao.deleteAllCustomIndicatorsReports(level2OuId);
            }
            System.err.println(count+" deleted");
        }
        else if(requiredAction.equalsIgnoreCase("generateData"))
        {
            try
                {
                    //DatabaseUtilities dbUtils=new DatabaseUtilities();
                    //dbUtils.createHivPositiveDataTable(true);
                    //dbUtils.createHivRiskAssessmentReportTable(true);
                    //CustomIndicatorsReportManager cirm=new CustomIndicatorsReportManagerThread();
                    //ReportParameterTemplate rpt=new ReportParameterTemplate();
                    List mainList=new ArrayList();
                    String level2OuId=null;
                    String level3OuId="All";
                    String cboId="All";
                    String partnerCode=selectedPartner;
                    rpt.setCboId(cboId);
                    String indicatorId=null;
                    Thread indicatorThread=null;
                    //The indicators are collected into array from user request, the data is moved into a list for further processing
                    List listOfSelectedIndicatorsFromArray=getListFromArray(selectedIndicators);
                    //Check to be sure atleast one level 2 Ou was selected for the report
                    if(selectedLevel2Ous !=null && selectedLevel2Ous.length>0)
                    {
                        //List paramList=getParamList(level2OuId,level3OuId,cboId,startMth,startYear,endMth,endYear,partnerCode);
                        //OvcQuarterlyReport quarterlyReport=new OvcQuarterlyReport();
                        List level3OuList=null;
                        List level4OuList=null;
                        List partnerCodeList=new ArrayList();
                        List list=null;
                        //if only one level 2 Ou was selected for the report, add the name to the file name
                        if(selectedLevel2Ous.length==1)
                        {
                            level2OuId=selectedLevel2Ous[0];
                            rpt.setLevel2OuId(level2OuId);
                            //paramList.set(0, level2OuId);
                            String level2OuName=ouaManager.getOrganizationUnitName(level2OuId);
                            if(level2OuName !=null)
                            level2OuName=level2OuName.replaceAll(" ", "_");
                            if(selectedPartner==null || selectedPartner.equalsIgnoreCase("All") || selectedPartner.equalsIgnoreCase("select") || selectedPartner.trim().length()==0)
                            partnerCodeList=util.getHouseholdEnrollmentDaoInstance().getDistinctPartnerCodes(rpt);
                            else
                            partnerCodeList.add(selectedPartner);
                            if(partnerCodeList !=null)
                            {
                                OrganizationUnit ou=null;
                                
                                for(Object s:partnerCodeList)
                                {
                                    partnerCode=(String)s;
                                    rpt.setPartnerCode(partnerCode);
                                    level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit(rpt);
                                    level3OuList=util.getOrganizationUnitDaoInstance().getParentOuList(level4OuList);
                                    for(int i=0; i<level3OuList.size(); i++)
                                    {
                                      ou=(OrganizationUnit)level3OuList.get(i);
                                      level3OuId=ou.getUid();
                                      //rpt.setLevel3OuId(level3OuId);
                                      for(int j=0; j<listOfSelectedIndicatorsFromArray.size(); j++)
                                      {
                                          indicatorId=listOfSelectedIndicatorsFromArray.get(j).toString();
                                          //System.err.println("indicatorId1 is "+indicatorId);
                                          rpt=getNewReportParameterTemplate(partnerCode,level2OuId,level3OuId,cboId,startMth, startYear,endMth,endYear);
                                          createDataGenerationThreads(rpt,indicatorId,startMth,startYear,endMth,endYear,userName);
                                      }
                                      //System.err.println("rpt.getLevel3OuId() is "+rpt.getLevel3OuId());
                                      //cirm.processCustomIndicatorsByLevel2Ou(rpt,listOfSelectedIndicatorsFromArray,startMth,startYear,endMth,endYear,userName);
                                      //mainList.add(quarterlyReport.getQuarterlyReport(paramList, level2OuId)); 
                                    }
                                }
                            }
                        }
                        else
                        {
                            //Here more than one level2 OU is selected for the report, loop and generate the data for the selected OUs
                            for(int j=0; j<selectedLevel2Ous.length; j++)
                            {
                               level2OuId=selectedLevel2Ous[j];
                               rpt.setLevel2OuId(level2OuId);
                               //if no partner was selected by the user, generate data for all the implementing partners
                               if(selectedPartner==null || selectedPartner.equalsIgnoreCase("All"))
                                partnerCodeList=util.getHouseholdEnrollmentDaoInstance().getDistinctPartnerCodes(rpt);
                                else
                                partnerCodeList.add(selectedPartner);
                               
                                if(partnerCodeList !=null)
                                {
                                    OrganizationUnit parentOu=null;
                                    for(Object s:partnerCodeList)
                                    {
                                        partnerCode=(String)s;
                                        rpt.setPartnerCode(partnerCode);
                                        level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit(rpt);
                                        level3OuList=util.getOrganizationUnitDaoInstance().getParentOuList(level4OuList);
                                        for(int i=0; i<level3OuList.size(); i++)
                                        {
                                          parentOu=(OrganizationUnit)level3OuList.get(i);
                                          level3OuId=parentOu.getUid();
                                          //rpt.setLevel3OuId(level3OuId);
                                          for(int k=0; k<listOfSelectedIndicatorsFromArray.size(); k++)
                                          {
                                              indicatorId=listOfSelectedIndicatorsFromArray.get(j).toString();
                                              //System.err.println("indicatorId2 is "+indicatorId);
                                              rpt=getNewReportParameterTemplate(partnerCode,level2OuId,level3OuId,cboId,startMth, startYear,endMth,endYear);
                                              createDataGenerationThreads(rpt,indicatorId,startMth,startYear,endMth,endYear,userName);
                                              //indicatorThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                                              //indicatorThread.start();
                                          }
                                          //System.err.println("rpt.getLevel3OuId() is "+rpt.getLevel3OuId());
                                          //cirm.processCustomIndicatorsByLevel2Ou(rpt,listOfSelectedIndicatorsFromArray,startMth,startYear,endMth,endYear,userName);
                                          //mainList.add(quarterlyReport.getQuarterlyReport(paramList, level2OuId)); 
                                        }
                                       System.err.println("Quaterly_Report size is "+mainList.size());
                                    }
                                }
                            }
                        }
                    }
                }
                catch(Exception ex)
                {
                    NomisLogManager.logStackTrace(ex);
                    //ex.printStackTrace();
                }
                cidgform.reset(mapping, request);
                return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
    private ReportParameterTemplate getNewReportParameterTemplate(String partnerCode,String level2OuId,String level3OuId,String cboId,int startMth,int startYear,int endMth,int endYear)
    {
        DateManager dm=new DateManager();
        ReportParameterTemplate newRpt=new ReportParameterTemplate();
        newRpt.setStartMth(startMth);
        newRpt.setStartYear(startYear);
        newRpt.setEndMth(endMth);
        newRpt.setEndYear(endYear);
        String startDate=dm.getStartDate(startMth, startYear);
        String endDate=dm.getEndDate(endMth, endYear);
        newRpt.setStartDate(startDate);
        newRpt.setEndDate(endDate);
        newRpt.setReportType(AppConstant.CUSTOMINDICATORS_REPORTTYPE);
        newRpt.setPartnerCode(partnerCode);
        newRpt.setLevel3OuId(level3OuId);
        newRpt.setLevel2OuId(level2OuId);
        newRpt.setCboId(cboId);
        return newRpt;
    }
    private void createDataGenerationThreads(ReportParameterTemplate rpt,String indicatorId,int startMth,int startYear,int endMth,int endYear,String userName)
    {
        IndicatorDictionary ind=new IndicatorDictionary();
        //String startDate="2021-04-01";
        //String endDate="2021-09-30";
        //new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName).getActiveCaregiverData(startDate, endDate);
            if(indicatorId.equalsIgnoreCase(ind.getOvc_NEWIndicator().getIndicatorId()))
            {//OVC_ENROLLED
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_SERVIndicator().getIndicatorId()))
            {//OVC_SERV
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_HIVSTATIndicator().getIndicatorId()))
            {//OVC_HIVSTAT
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_TXLINKIndicator().getIndicatorId()))
            {//OVC_TXLINK
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_ARTSUPPIndicator().getIndicatorId()))
            {//OVC_ARTSUPP
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_HIVRISKASSESSEDIndicator().getIndicatorId()))
            {//OVC_HIVRISKASS
               Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
               ovcEnrThread.start(); 
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_BIRTHCERTIndicator().getIndicatorId()))
            {//OVC_BIRTHCERT
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_PROTECTIndicator().getIndicatorId()))
            {//OVC_PROTECT
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_HTSLINKIndicator().getIndicatorId()))
            {//OVC_HTSLINK
               Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
               ovcEnrThread.start(); 
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_NUTRITIONIndicator().getIndicatorId()))
            {//OVC_NUTRITION
               Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
               ovcEnrThread.start(); 
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_EDUIndicator().getIndicatorId()))
            {//OVC_EDU
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_HIVPREVIndicator().getIndicatorId()))
            {//OVC_HHGRAD
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_HHGRADIndicator().getIndicatorId()))
            {//OVC_HHGRAD
                Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_ECONSIndicator().getIndicatorId()))
            {//OVC_ECONS
               Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start(); 
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_VL_ELIGIBLEIndicator().getIndicatorId()))
            {//OVC_VL_ELIGIBLE
              Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start();  
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_VLRIndicator().getIndicatorId()))
            {//OVC_VLR
               Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start(); 
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_VLSIndicator().getIndicatorId()))
            {//OVC_VLS
               Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start(); 
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_ART_OFFERIndicator().getIndicatorId()))
            {//OVC_ART_ENROLL
               Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start(); 
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_ART_ENROLLIndicator().getIndicatorId()))
            {//OVC_ART_ENROLL
               Thread ovcEnrThread =new Thread(new CustomIndicatorsReportManagerThread(rpt,indicatorId,startMth,startYear,endMth,endYear,userName));
                ovcEnrThread.start(); 
            }
    }
   /* private List getParamList(String level2OuId,String level3OuId,String cboId,int startMth,int startYr,int endMth,int endYr,String partnerCode)
    {
        List paramList=new ArrayList();
        
        paramList.add(level2OuId);
        paramList.add(level3OuId);
        paramList.add(cboId);
        paramList.add("All");
        paramList.add(startMth);
        paramList.add(startYr);
        paramList.add(endMth);
        paramList.add(endYr);
        paramList.add(partnerCode);
        return paramList;
    }*/
    private void setPartnerList(User user,HttpSession session)
    {
        String partnerCodes=null;
        DaoUtility util=new DaoUtility();
        try
        {
            if(user !=null)
            {
                List partnerList=new ArrayList();
                if(user.isSuperUser())
                {
                   partnerList=util.getPartnerDaoInstance().getAllPartners(); 
                }
                else
                {
                    partnerCodes=user.getPartnerCodes();
                    if(partnerCodes !=null)
                    {
                        String[] partnerCodeArray=partnerCodes.split(",");
                        if(partnerCodeArray !=null)
                        {
                            Partner partner=null;
                            for(int i=0; i<partnerCodeArray.length; i++)
                            {
                                partner=util.getPartnerDaoInstance().getPartner(partnerCodeArray[i]);
                                partnerList.add(partner);
                            }
                        }
                    }
                }
                session.setAttribute("userAssignedPartners", partnerList);
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
    }
    private List getListFromArray(String[] selectedIndicators)
    {
        List list=new ArrayList();
        if(selectedIndicators !=null)
        {
            for(int i=0; i<selectedIndicators.length; i++)
            {
                list.add(selectedIndicators[i]);
            }
        }
        return list;
    }
    private List getLevel3OuList(List level4OuList)
    {
        List level3OuList=new ArrayList();
        try
        {
        DaoUtility util=new DaoUtility();
        OrganizationUnit level3Ou=null;
        OrganizationUnit level4Ou=null;
        if(level4OuList !=null)
        {
            String level4OuId=null;
            for(Object obj:level4OuList)
            {
                level4OuId=(String)obj;
                level4Ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level4OuId);
                if(level4Ou !=null)
                {
                    level3Ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level4Ou.getParentId());
                    if(level3Ou !=null)
                    {
                        level3OuList.add(level3Ou);
                    }
                }
            }
        }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return level3OuList;
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("cirgSaveDisabled", saveDisabled);
    }
}
