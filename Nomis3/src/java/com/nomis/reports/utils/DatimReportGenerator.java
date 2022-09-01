/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;


import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.ovc.business.Partner;
import com.nomis.ovc.dao.CaregiverAccessToEmergencyFundDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.DatimReportDao;
import com.nomis.ovc.dao.DatimReportDaoImpl;
import com.nomis.ovc.dao.SubQueryGenerator;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class DatimReportGenerator 
{
    FinancialYearManager fym=new FinancialYearManager();
    DaoUtility util=new DaoUtility();
    public DatimReportTemplate getDatimReport(ReportParameterTemplate rpt) throws Exception
    {
        String startDate=rpt.getStartDate();
        String endDate=rpt.getEndDate();
        String partnerCode=rpt.getPartnerCode();
        Partner partner=util.getPartnerDaoInstance().getPartner(partnerCode);
        if(partner==null)
        partner=new Partner();
        String startOfFinancialYear=rpt.getFinancialYear().getStartYear()+"-"+rpt.getFinancialYear().getStartMonth()+"-01";
        DatimReportTemplate dform=new DatimReportTemplate();
        //dform.setPartnerCode(rpt.getPartnerCode());
        //Datim2017Form dform=new Datim2017Form();
        
        DatimReportTemplate activeRt=getNoOfActiveOvcServed(rpt,startDate, endDate,0,17,"All",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate gradRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,0,17,"All",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate childrenTransferedToPEPFARRt=getNoOfOvcServedAndTransfered(rpt,startOfFinancialYear, endDate,0,17,"All");
        DatimReportTemplate childrenTransferedToNonPEPFARRt=getNoOfOvcServedAndTransferedToNonPEPFAR(rpt,startOfFinancialYear, endDate,0,17,"All");
        
        DatimReportTemplate caregiversTransferedToPEPFARRt=getNoOfCaregiversServedAndTransferedToPEPFAR(rpt,startOfFinancialYear,endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"");
        DatimReportTemplate caregiversTransferedToNonPEPFARRt=getNoOfCaregiversServedAndTransferedToNonPEPFAR(rpt,startOfFinancialYear,endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"");
        
        int totalTransferedToPEPFAR=childrenTransferedToPEPFARRt.getOvc_servTransfered()+caregiversTransferedToPEPFARRt.getOvc_servTransfered();
        int totalTransferedToNonPEPFAR=childrenTransferedToNonPEPFARRt.getOvc_servTransfered()+caregiversTransferedToNonPEPFARRt.getOvc_servTransfered();
        int totalChildrenTransfered=childrenTransferedToPEPFARRt.getOvc_servTransfered()+childrenTransferedToNonPEPFARRt.getOvc_servTransfered();
        int totalCaregiversTransfered=caregiversTransferedToPEPFARRt.getOvc_servTransfered()+caregiversTransferedToNonPEPFARRt.getOvc_servTransfered();
        //ReportTemplate transferedRt=getNoOfOvcServedAndTransfered(rpt,startDate, endDate,"All");
        //ReportTemplate caregiversTransferedRt=getNoOfCaregiversServedAndTransfered(rpt,startDate, endDate,"All");
        
        DatimReportTemplate exitedWithoutGraduationRt=getNoOfOvcExitedWithoutGraduationServed(rpt,startOfFinancialYear, endDate,0,AppConstant.OLDER_OVC_ENDAGE_NUM,"All");
        DatimReportTemplate caregiversExitedWithoutGraduationRt=getNoOfCaregiversExitedWithoutGraduationServed(rpt,startOfFinancialYear, endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"All");
        
        /*DatimReportTemplate hivPosActiveRt=getNoOfActiveHivPositiveOvcServed(rpt,0,17,startDate, endDate,"All");
        DatimReportTemplate hivPosGraduatedRt=getNoOfGraduatedHivPositiveOvcServed(rpt,0,17,startOfFinancialYear, endDate,"All");
        DatimReportTemplate hivNegActiveRt=getNoOfActiveHivNegativeOvcServed(rpt,0,17,startDate, endDate,"All");
        DatimReportTemplate hivNegGraduatedRt=getNoOfGraduatedHivNegativeOvcServed(rpt,0,17,startOfFinancialYear, endDate,"All");
        DatimReportTemplate hivUnknownActiveRt=getNoOfActiveHivUnknownOvcServed(rpt,0,17,startDate, endDate,"All");
        DatimReportTemplate hivUnknownGraduatedRt=getNoOfGraduatedHivUnknownOvcServed(rpt,0,17,startOfFinancialYear, endDate,"All");
        DatimReportTemplate activeHivPositiveOnTreatmentRt=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,0,17,startDate, endDate,"All");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRt=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,0,17,startOfFinancialYear, endDate,"All");
        DatimReportTemplate activeTestNotIndicatedRt=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"All");
        DatimReportTemplate graduatedTestNotIndicatedRt=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,0,17,startOfFinancialYear, endDate,"All");
        */
        DatimReportTemplate hivPosActiveRtMaleLessThan1=getNoOfActiveHivPositiveOvcServed(rpt,0,0,startDate, endDate,"M");
        DatimReportTemplate hivPosActiveRtMale1To4=getNoOfActiveHivPositiveOvcServed(rpt,1,4,startDate, endDate,"M");
        DatimReportTemplate hivPosActiveRtMale5To9=getNoOfActiveHivPositiveOvcServed(rpt,5,9,startDate, endDate,"M");
        DatimReportTemplate hivPosActiveRtMale10To14=getNoOfActiveHivPositiveOvcServed(rpt,10,14,startDate, endDate,"M");
        DatimReportTemplate hivPosActiveRtMale15To17=getNoOfActiveHivPositiveOvcServed(rpt,15,17,startDate, endDate,"M");
        DatimReportTemplate hivPosActiveRtFemaleLessThan1=getNoOfActiveHivPositiveOvcServed(rpt,0,0,startDate, endDate,"F");
        DatimReportTemplate hivPosActiveRtFemale1To4=getNoOfActiveHivPositiveOvcServed(rpt,1,4,startDate, endDate,"F");
        DatimReportTemplate hivPosActiveRtFemale5To9=getNoOfActiveHivPositiveOvcServed(rpt,5,9,startDate, endDate,"F");
        DatimReportTemplate hivPosActiveRtFemale10To14=getNoOfActiveHivPositiveOvcServed(rpt,10,14,startDate, endDate,"F");
        DatimReportTemplate hivPosActiveRtFemale15To17=getNoOfActiveHivPositiveOvcServed(rpt,15,17,startDate, endDate,"F");
        
        DatimReportTemplate hivPosGraduatedRtMaleLessThan1=getNoOfGraduatedHivPositiveOvcServed(rpt,0,0,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivPosGraduatedRtMale1To4=getNoOfGraduatedHivPositiveOvcServed(rpt,1,4,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivPosGraduatedRtMale5To9=getNoOfGraduatedHivPositiveOvcServed(rpt,5,9,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivPosGraduatedRtMale10To14=getNoOfGraduatedHivPositiveOvcServed(rpt,10,14,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivPosGraduatedRtMale15To17=getNoOfGraduatedHivPositiveOvcServed(rpt,15,17,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivPosGraduatedRtFemaleLessThan1=getNoOfGraduatedHivPositiveOvcServed(rpt,0,0,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivPosGraduatedRtFemale1To4=getNoOfGraduatedHivPositiveOvcServed(rpt,1,4,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivPosGraduatedRtFemale5To9=getNoOfGraduatedHivPositiveOvcServed(rpt,5,9,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivPosGraduatedRtFemale10To14=getNoOfGraduatedHivPositiveOvcServed(rpt,10,14,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivPosGraduatedRtFemale15To17=getNoOfGraduatedHivPositiveOvcServed(rpt,15,17,startOfFinancialYear, endDate,"F");
        
        DatimReportTemplate hivNegActiveRtMaleLessThan1=getNoOfActiveHivNegativeOvcServed(rpt,0,0,startDate, endDate,"M");
        DatimReportTemplate hivNegActiveRtMale1To4=getNoOfActiveHivNegativeOvcServed(rpt,1,4,startDate, endDate,"M");
        DatimReportTemplate hivNegActiveRtMale5To9=getNoOfActiveHivNegativeOvcServed(rpt,5,9,startDate, endDate,"M");
        DatimReportTemplate hivNegActiveRtMale10To14=getNoOfActiveHivNegativeOvcServed(rpt,10,14,startDate, endDate,"M");
        DatimReportTemplate hivNegActiveRtMale15To17=getNoOfActiveHivNegativeOvcServed(rpt,15,17,startDate, endDate,"M");
        DatimReportTemplate hivNegActiveRtFemaleLessThan1=getNoOfActiveHivNegativeOvcServed(rpt,0,0,startDate, endDate,"F");
        DatimReportTemplate hivNegActiveRtFemale1To4=getNoOfActiveHivNegativeOvcServed(rpt,1,4,startDate, endDate,"F");
        DatimReportTemplate hivNegActiveRtFemale5To9=getNoOfActiveHivNegativeOvcServed(rpt,5,9,startDate, endDate,"F");
        DatimReportTemplate hivNegActiveRtFemale10To14=getNoOfActiveHivNegativeOvcServed(rpt,10,14,startDate, endDate,"F");
        DatimReportTemplate hivNegActiveRtFemale15To17=getNoOfActiveHivNegativeOvcServed(rpt,15,17,startDate, endDate,"F");
        
        
        DatimReportTemplate hivNegGraduatedRtMaleLessThan1=getNoOfGraduatedHivNegativeOvcServed(rpt,0,0,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivNegGraduatedRtMale1To4=getNoOfGraduatedHivNegativeOvcServed(rpt,1,4,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivNegGraduatedRtMale5To9=getNoOfGraduatedHivNegativeOvcServed(rpt,5,9,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivNegGraduatedRtMale10To14=getNoOfGraduatedHivNegativeOvcServed(rpt,10,14,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivNegGraduatedRtMale15To17=getNoOfGraduatedHivNegativeOvcServed(rpt,15,17,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivNegGraduatedRtFemaleLessThan1=getNoOfGraduatedHivNegativeOvcServed(rpt,0,0,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivNegGraduatedRtFemale1To4=getNoOfGraduatedHivNegativeOvcServed(rpt,1,4,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivNegGraduatedRtFemale5To9=getNoOfGraduatedHivNegativeOvcServed(rpt,5,9,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivNegGraduatedRtFemale10To14=getNoOfGraduatedHivNegativeOvcServed(rpt,10,14,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivNegGraduatedRtFemale15To17=getNoOfGraduatedHivNegativeOvcServed(rpt,15,17,startOfFinancialYear, endDate,"F");
        
        DatimReportTemplate hivUnknownActiveRtMaleLessThan1=getNoOfActiveHivUnknownOvcServed(rpt,0,0,startDate, endDate,"M");
        DatimReportTemplate hivUnknownActiveRtMale1To4=getNoOfActiveHivUnknownOvcServed(rpt,1,4,startDate, endDate,"M");
        DatimReportTemplate hivUnknownActiveRtMale5To9=getNoOfActiveHivUnknownOvcServed(rpt,5,9,startDate, endDate,"M");
        DatimReportTemplate hivUnknownActiveRtMale10To14=getNoOfActiveHivUnknownOvcServed(rpt,10,14,startDate, endDate,"M");
        DatimReportTemplate hivUnknownActiveRtMale15To17=getNoOfActiveHivUnknownOvcServed(rpt,15,17,startDate, endDate,"M");
        DatimReportTemplate hivUnknownActiveRtFemaleLessThan1=getNoOfActiveHivUnknownOvcServed(rpt,0,0,startDate, endDate,"F");
        DatimReportTemplate hivUnknownActiveRtFemale1To4=getNoOfActiveHivUnknownOvcServed(rpt,1,4,startDate, endDate,"F");
        DatimReportTemplate hivUnknownActiveRtFemale5To9=getNoOfActiveHivUnknownOvcServed(rpt,5,9,startDate, endDate,"F");
        DatimReportTemplate hivUnknownActiveRtFemale10To14=getNoOfActiveHivUnknownOvcServed(rpt,10,14,startDate, endDate,"F");
        DatimReportTemplate hivUnknownActiveRtFemale15To17=getNoOfActiveHivUnknownOvcServed(rpt,15,17,startDate, endDate,"F");
        
        DatimReportTemplate hivUnknownGraduatedRtMaleLessThan1=getNoOfGraduatedHivUnknownOvcServed(rpt,0,0,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivUnknownGraduatedRtMale1To4=getNoOfGraduatedHivUnknownOvcServed(rpt,1,4,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivUnknownGraduatedRtMale5To9=getNoOfGraduatedHivUnknownOvcServed(rpt,5,9,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivUnknownGraduatedRtMale10To14=getNoOfGraduatedHivUnknownOvcServed(rpt,10,14,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivUnknownGraduatedRtMale15To17=getNoOfGraduatedHivUnknownOvcServed(rpt,15,17,startOfFinancialYear, endDate,"M");
        DatimReportTemplate hivUnknownGraduatedRtFemaleLessThan1=getNoOfGraduatedHivUnknownOvcServed(rpt,0,0,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivUnknownGraduatedRtFemale1To4=getNoOfGraduatedHivUnknownOvcServed(rpt,1,4,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivUnknownGraduatedRtFemale5To9=getNoOfGraduatedHivUnknownOvcServed(rpt,5,9,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivUnknownGraduatedRtFemale10To14=getNoOfGraduatedHivUnknownOvcServed(rpt,10,14,startOfFinancialYear, endDate,"F");
        DatimReportTemplate hivUnknownGraduatedRtFemale15To17=getNoOfGraduatedHivUnknownOvcServed(rpt,15,17,startOfFinancialYear, endDate,"F");
        
        DatimReportTemplate activeHivPositiveOnTreatmentRtMaleLessThan1=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,0,0,startDate, endDate,"M");
        DatimReportTemplate activeHivPositiveOnTreatmentRtMale1To4=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,1,4,startDate, endDate,"M");
        DatimReportTemplate activeHivPositiveOnTreatmentRtMale5To9=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,5,9,startDate, endDate,"M");
        DatimReportTemplate activeHivPositiveOnTreatmentRtMale10To14=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,10,14,startDate, endDate,"M");
        DatimReportTemplate activeHivPositiveOnTreatmentRtMale15To17=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,15,17,startDate, endDate,"M");
        DatimReportTemplate activeHivPositiveOnTreatmentRtFemaleLessThan1=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,0,0,startDate, endDate,"F");
        DatimReportTemplate activeHivPositiveOnTreatmentRtFemale1To4=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,1,4,startDate, endDate,"F");
        DatimReportTemplate activeHivPositiveOnTreatmentRtFemale5To9=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,5,9,startDate, endDate,"F");
        DatimReportTemplate activeHivPositiveOnTreatmentRtFemale10To14=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,10,14,startDate, endDate,"F");
        DatimReportTemplate activeHivPositiveOnTreatmentRtFemale15To17=getNoOfActiveHivPositiveOvcOnTreatmentServed(rpt,15,17,startDate, endDate,"F");
         
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtMaleLessThan1=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,0,0,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtMale1To4=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,1,4,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtMale5To9=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,5,9,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtMale10To14=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,10,14,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtMale15To17=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,15,17,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtFemaleLessThan1=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,0,0,startOfFinancialYear, endDate,"F");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtFemale1To4=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,1,4,startOfFinancialYear, endDate,"F");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtFemale5To9=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,5,9,startOfFinancialYear, endDate,"F");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtFemale10To14=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,10,14,startOfFinancialYear, endDate,"F");
        DatimReportTemplate graduatedHivPositiveOnTreatmentRtFemale15To17=getNoOfGraduatedHivPositiveOvcOnTreatmentServed(rpt,15,17,startOfFinancialYear, endDate,"F");
        
        DatimReportTemplate activeTestNotIndicatedRtMaleLessThan1=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"M");
        DatimReportTemplate activeTestNotIndicatedRtMale1To4=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"M");
        DatimReportTemplate activeTestNotIndicatedRtMale5To9=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"M");
        DatimReportTemplate activeTestNotIndicatedRtMale10To14=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"M");
        DatimReportTemplate activeTestNotIndicatedRtMale15To17=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"M");
        DatimReportTemplate activeTestNotIndicatedRtFemaleLessThan1=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"F");
        DatimReportTemplate activeTestNotIndicatedRtFemale1To4=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"F");
        DatimReportTemplate activeTestNotIndicatedRtFemale5To9=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"F");
        DatimReportTemplate activeTestNotIndicatedRtFemale10To14=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"F");
        DatimReportTemplate activeTestNotIndicatedRtFemale15To17=getNoOfActiveOvcWithTestNotIndicatedStatusServed(rpt,0,17,startDate, endDate,"F");
        
        DatimReportTemplate graduatedTestNotIndicatedRtMaleLessThan1=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,0,0,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedTestNotIndicatedRtMale1To4=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,1,4,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedTestNotIndicatedRtMale5To9=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,5,9,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedTestNotIndicatedRtMale10To14=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,10,14,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedTestNotIndicatedRtMale15To17=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,15,17,startOfFinancialYear, endDate,"M");
        DatimReportTemplate graduatedTestNotIndicatedRtFemaleLessThan1=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,0,0,startOfFinancialYear, endDate,"F");
        DatimReportTemplate graduatedTestNotIndicatedRtFemale1To4=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,1,4,startOfFinancialYear, endDate,"F");
        DatimReportTemplate graduatedTestNotIndicatedRtFemale5To9=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,5,9,startOfFinancialYear, endDate,"F");
        DatimReportTemplate graduatedTestNotIndicatedRtFemale10To14=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,10,14,startOfFinancialYear, endDate,"F");
        DatimReportTemplate graduatedTestNotIndicatedRtFemale15To17=getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(rpt,15,17,startOfFinancialYear, endDate,"F");
        
        dform.setActivePositiveMaleLessThan1(hivPosActiveRtMaleLessThan1.getHiv_statNumerator());
        dform.setActivePositiveMale1To4(hivPosActiveRtMale1To4.getHiv_statNumerator());
        dform.setActivePositiveMale5To9(hivPosActiveRtMale5To9.getHiv_statNumerator());
        dform.setActivePositiveMale10To14(hivPosActiveRtMale10To14.getHiv_statNumerator());
        dform.setActivePositiveMale15To17(hivPosActiveRtMale15To17.getHiv_statNumerator());
        dform.setActivePositiveFemaleLessThan1(hivPosActiveRtFemaleLessThan1.getHiv_statNumerator());
        dform.setActivePositiveFemale1To4(hivPosActiveRtFemale1To4.getHiv_statNumerator());
        dform.setActivePositiveFemale5To9(hivPosActiveRtFemale5To9.getHiv_statNumerator());
        dform.setActivePositiveFemale10To14(hivPosActiveRtFemale10To14.getHiv_statNumerator());
        dform.setActivePositiveFemale15To17(hivPosActiveRtFemale15To17.getHiv_statNumerator());
        
        dform.setGraduatedPositiveMaleLessThan1(hivPosGraduatedRtMaleLessThan1.getHiv_statNumerator());
        dform.setGraduatedPositiveMale1To4(hivPosGraduatedRtMale1To4.getHiv_statNumerator());
        dform.setGraduatedPositiveMale5To9(hivPosGraduatedRtMale5To9.getHiv_statNumerator());
        dform.setGraduatedPositiveMale10To14(hivPosGraduatedRtMale10To14.getHiv_statNumerator());
        dform.setGraduatedPositiveMale15To17(hivPosGraduatedRtMale15To17.getHiv_statNumerator());
        dform.setGraduatedPositiveFemaleLessThan1(hivPosGraduatedRtFemaleLessThan1.getHiv_statNumerator());
        dform.setGraduatedPositiveFemale1To4(hivPosGraduatedRtFemale1To4.getHiv_statNumerator());
        dform.setGraduatedPositiveFemale5To9(hivPosGraduatedRtFemale5To9.getHiv_statNumerator());
        dform.setGraduatedPositiveFemale10To14(hivPosGraduatedRtFemale10To14.getHiv_statNumerator());
        dform.setGraduatedPositiveFemale15To17(hivPosGraduatedRtFemale15To17.getHiv_statNumerator());
        
        dform.setActiveNegativeMaleLessThan1(hivNegActiveRtMaleLessThan1.getHiv_statNumerator());
        dform.setActiveNegativeMale1To4(hivNegActiveRtMale1To4.getHiv_statNumerator());
        dform.setActiveNegativeMale5To9(hivNegActiveRtMale5To9.getHiv_statNumerator());
        dform.setActiveNegativeMale10To14(hivNegActiveRtMale10To14.getHiv_statNumerator());
        dform.setActiveNegativeMale15To17(hivNegActiveRtMale15To17.getHiv_statNumerator());
        dform.setActiveNegativeFemaleLessThan1(hivNegActiveRtFemaleLessThan1.getHiv_statNumerator());
        dform.setActiveNegativeFemale1To4(hivNegActiveRtFemale1To4.getHiv_statNumerator());
        dform.setActiveNegativeFemale5To9(hivNegActiveRtFemale5To9.getHiv_statNumerator());
        dform.setActiveNegativeFemale10To14(hivNegActiveRtFemale10To14.getHiv_statNumerator());
        dform.setActiveNegativeFemale15To17(hivNegActiveRtFemale15To17.getHiv_statNumerator());
        
        dform.setGraduatedNegativeMaleLessThan1(hivNegGraduatedRtMaleLessThan1.getHiv_statNumerator());
        dform.setGraduatedNegativeMale1To4(hivNegGraduatedRtMale1To4.getHiv_statNumerator());
        dform.setGraduatedNegativeMale5To9(hivNegGraduatedRtMale5To9.getHiv_statNumerator());
        dform.setGraduatedNegativeMale10To14(hivNegGraduatedRtMale10To14.getHiv_statNumerator());
        dform.setGraduatedNegativeMale15To17(hivNegGraduatedRtMale15To17.getHiv_statNumerator());
        dform.setGraduatedNegativeFemaleLessThan1(hivNegGraduatedRtFemaleLessThan1.getHiv_statNumerator());
        dform.setGraduatedNegativeFemale1To4(hivNegGraduatedRtFemale1To4.getHiv_statNumerator());
        dform.setGraduatedNegativeFemale5To9(hivNegGraduatedRtFemale5To9.getHiv_statNumerator());
        dform.setGraduatedNegativeFemale10To14(hivNegGraduatedRtFemale10To14.getHiv_statNumerator());
        dform.setGraduatedNegativeFemale15To17(hivNegGraduatedRtFemale15To17.getHiv_statNumerator());
        
        dform.setActiveUnknownMaleLessThan1(hivUnknownActiveRtMaleLessThan1.getHiv_statNumerator());
        dform.setActiveUnknownMale1To4(hivUnknownActiveRtMale1To4.getHiv_statNumerator());
        dform.setActiveUnknownMale5To9(hivUnknownActiveRtMale5To9.getHiv_statNumerator());
        dform.setActiveUnknownMale10To14(hivUnknownActiveRtMale10To14.getHiv_statNumerator());
        dform.setActiveUnknownMale15To17(hivUnknownActiveRtMale15To17.getHiv_statNumerator());
        dform.setActiveUnknownFemaleLessThan1(hivUnknownActiveRtFemaleLessThan1.getHiv_statNumerator());
        dform.setActiveUnknownFemale1To4(hivUnknownActiveRtFemale1To4.getHiv_statNumerator());
        dform.setActiveUnknownFemale5To9(hivUnknownActiveRtFemale5To9.getHiv_statNumerator());
        dform.setActiveUnknownFemale10To14(hivUnknownActiveRtFemale10To14.getHiv_statNumerator());
        dform.setActiveUnknownFemale15To17(hivUnknownActiveRtFemale15To17.getHiv_statNumerator());
        
        dform.setGraduatedUnknownMaleLessThan1(hivUnknownGraduatedRtMaleLessThan1.getHiv_statNumerator());
        dform.setGraduatedUnknownMale1To4(hivUnknownGraduatedRtMale1To4.getHiv_statNumerator());
        dform.setGraduatedUnknownMale5To9(hivUnknownGraduatedRtMale5To9.getHiv_statNumerator());
        dform.setGraduatedUnknownMale10To14(hivUnknownGraduatedRtMale10To14.getHiv_statNumerator());
        dform.setGraduatedUnknownMale15To17(hivUnknownGraduatedRtMale15To17.getHiv_statNumerator());
        dform.setGraduatedUnknownFemaleLessThan1(hivUnknownGraduatedRtFemaleLessThan1.getHiv_statNumerator());
        dform.setGraduatedUnknownFemale1To4(hivUnknownGraduatedRtFemale1To4.getHiv_statNumerator());
        dform.setGraduatedUnknownFemale5To9(hivUnknownGraduatedRtFemale5To9.getHiv_statNumerator());
        dform.setGraduatedUnknownFemale10To14(hivUnknownGraduatedRtFemale10To14.getHiv_statNumerator());
        dform.setGraduatedUnknownFemale15To17(hivUnknownGraduatedRtFemale15To17.getHiv_statNumerator());
        
        dform.setActivePositiveEnrolledOnARTMaleLessThan1(activeHivPositiveOnTreatmentRtMaleLessThan1.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTMale1To4(activeHivPositiveOnTreatmentRtMale1To4.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTMale5To9(activeHivPositiveOnTreatmentRtMale5To9.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTMale10To14(activeHivPositiveOnTreatmentRtMale10To14.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTMale15To17(activeHivPositiveOnTreatmentRtMale15To17.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTFemaleLessThan1(activeHivPositiveOnTreatmentRtFemaleLessThan1.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTFemale1To4(activeHivPositiveOnTreatmentRtFemale1To4.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTFemale5To9(activeHivPositiveOnTreatmentRtFemale5To9.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTFemale10To14(activeHivPositiveOnTreatmentRtFemale10To14.getEnrolledOnART());
        dform.setActivePositiveEnrolledOnARTFemale15To17(activeHivPositiveOnTreatmentRtFemale15To17.getEnrolledOnART());
                 
        dform.setGraduatedPositiveEnrolledOnARTMaleLessThan1(graduatedHivPositiveOnTreatmentRtMaleLessThan1.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTMale1To4(graduatedHivPositiveOnTreatmentRtMale1To4.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTMale5To9(graduatedHivPositiveOnTreatmentRtMale5To9.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTMale10To14(graduatedHivPositiveOnTreatmentRtMale10To14.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTMale15To17(graduatedHivPositiveOnTreatmentRtMale15To17.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTFemaleLessThan1(graduatedHivPositiveOnTreatmentRtFemaleLessThan1.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTFemale1To4(graduatedHivPositiveOnTreatmentRtFemale1To4.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTFemale5To9(graduatedHivPositiveOnTreatmentRtFemale5To9.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTFemale10To14(graduatedHivPositiveOnTreatmentRtFemale10To14.getEnrolledOnART());
        dform.setGraduatedPositiveEnrolledOnARTFemale15To17(graduatedHivPositiveOnTreatmentRtFemale15To17.getEnrolledOnART());
        
        
        
        dform.setActiveTestNotIndicatedMaleLessThan1(activeTestNotIndicatedRtMaleLessThan1.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedMale1To4(activeTestNotIndicatedRtMale1To4.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedMale5To9(activeTestNotIndicatedRtMale5To9.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedMale10To14(activeTestNotIndicatedRtMale10To14.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedMale15To17(activeTestNotIndicatedRtMale15To17.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedFemaleLessThan1(activeTestNotIndicatedRtFemaleLessThan1.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedFemale1To4(activeTestNotIndicatedRtFemale1To4.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedFemale5To9(activeTestNotIndicatedRtFemale5To9.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedFemale10To14(activeTestNotIndicatedRtFemale10To14.getHiv_statNumerator());
        dform.setActiveTestNotIndicatedFemale15To17(activeTestNotIndicatedRtFemale15To17.getHiv_statNumerator());
                 
        dform.setGraduatedTestNotIndicatedMaleLessThan1(graduatedTestNotIndicatedRtMaleLessThan1.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedMale1To4(graduatedTestNotIndicatedRtMale1To4.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedMale5To9(graduatedTestNotIndicatedRtMale5To9.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedMale10To14(graduatedTestNotIndicatedRtMale10To14.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedMale15To17(graduatedTestNotIndicatedRtMale15To17.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedFemaleLessThan1(graduatedTestNotIndicatedRtFemaleLessThan1.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedFemale1To4(graduatedTestNotIndicatedRtFemale1To4.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedFemale5To9(graduatedTestNotIndicatedRtFemale5To9.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedFemale10To14(graduatedTestNotIndicatedRtFemale10To14.getHiv_statNumerator());
        dform.setGraduatedTestNotIndicatedFemale15To17(graduatedTestNotIndicatedRtFemale15To17.getHiv_statNumerator());
                
        dform.setActivePositiveNotEnrolledOnARTFemaleLessThan1(dform.getActivePositiveFemaleLessThan1()-dform.getActivePositiveEnrolledOnARTFemaleLessThan1());
        dform.setActivePositiveNotEnrolledOnARTFemale1To4(dform.getActivePositiveFemale1To4()-dform.getActivePositiveEnrolledOnARTFemale1To4());
        dform.setActivePositiveNotEnrolledOnARTFemale5To9(dform.getActivePositiveFemale5To9()-dform.getActivePositiveEnrolledOnARTFemale5To9());
        dform.setActivePositiveNotEnrolledOnARTFemale10To14(dform.getActivePositiveFemale10To14()-dform.getActivePositiveEnrolledOnARTFemale10To14());
        dform.setActivePositiveNotEnrolledOnARTFemale15To17(dform.getActivePositiveFemale15To17()-dform.getActivePositiveEnrolledOnARTFemale15To17());
        dform.setActivePositiveNotEnrolledOnARTMaleLessThan1(dform.getActivePositiveMaleLessThan1()-dform.getActivePositiveEnrolledOnARTMaleLessThan1());
        dform.setActivePositiveNotEnrolledOnARTMale1To4(dform.getActivePositiveMale1To4()-dform.getActivePositiveEnrolledOnARTMale1To4());
        dform.setActivePositiveNotEnrolledOnARTMale5To9(dform.getActivePositiveMale5To9()-dform.getActivePositiveEnrolledOnARTMale5To9());
        dform.setActivePositiveNotEnrolledOnARTMale10To14(dform.getActivePositiveMale10To14()-dform.getActivePositiveEnrolledOnARTMale10To14());
        dform.setActivePositiveNotEnrolledOnARTMale15To17(dform.getActivePositiveMale15To17()-dform.getActivePositiveEnrolledOnARTMale15To17());
        
        dform.setGraduatedPositiveNotEnrolledOnARTFemaleLessThan1(dform.getGraduatedPositiveFemaleLessThan1()-dform.getGraduatedPositiveEnrolledOnARTFemaleLessThan1());
        dform.setGraduatedPositiveNotEnrolledOnARTFemale1To4(dform.getGraduatedPositiveFemale1To4()-dform.getGraduatedPositiveEnrolledOnARTFemale1To4());
        dform.setGraduatedPositiveNotEnrolledOnARTFemale5To9(dform.getGraduatedPositiveFemale5To9()-dform.getGraduatedPositiveEnrolledOnARTFemale5To9());
        dform.setGraduatedPositiveNotEnrolledOnARTFemale10To14(dform.getGraduatedPositiveFemale10To14()-dform.getGraduatedPositiveEnrolledOnARTFemale10To14());
        dform.setGraduatedPositiveNotEnrolledOnARTFemale15To17(dform.getGraduatedPositiveFemale15To17()-dform.getGraduatedPositiveEnrolledOnARTFemale15To17());
        dform.setGraduatedPositiveNotEnrolledOnARTMaleLessThan1(dform.getGraduatedPositiveMaleLessThan1()-dform.getGraduatedPositiveEnrolledOnARTMaleLessThan1());
        dform.setGraduatedPositiveNotEnrolledOnARTMale1To4(dform.getGraduatedPositiveMale1To4()-dform.getGraduatedPositiveEnrolledOnARTMale1To4());
        dform.setGraduatedPositiveNotEnrolledOnARTMale5To9(dform.getGraduatedPositiveMale5To9()-dform.getGraduatedPositiveEnrolledOnARTMale5To9());
        dform.setGraduatedPositiveNotEnrolledOnARTMale10To14(dform.getGraduatedPositiveMale10To14()-dform.getGraduatedPositiveEnrolledOnARTMale10To14());
        dform.setGraduatedPositiveNotEnrolledOnARTMale15To17(dform.getGraduatedPositiveMale15To17()-dform.getGraduatedPositiveEnrolledOnARTMale15To17());
        
        DatimReportTemplate activeLessThan1Rt=getNoOfActiveOvcServed(rpt,startDate, endDate,0,0,"All",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        
        DatimReportTemplate activeLessThan1MaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,0,0,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate activeLessThan1FemaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,0,0,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active1to4MaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,1,4,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active1to4FemaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,1,4,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active5to9MaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,5,9,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active5to9FemaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,5,9,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active1to9Rt=getNoOfActiveOvcServed(rpt,startDate, endDate,1,9,"All",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active10to14MaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,10,14,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active10to14FemaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,10,14,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active15to17MaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,15,17,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate active15to17FemaleRt=getNoOfActiveOvcServed(rpt,startDate, endDate,15,17,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        
        DatimReportTemplate activeCaregivers18AndAboveMaleRt=getNoOfActiveCaregiversServed(rpt,startDate, endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"M");
        DatimReportTemplate activeCaregivers18AndAboveFemaleRt=getNoOfActiveCaregiversServed(rpt,startDate, endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"F");
        
        DatimReportTemplate activeOvc18PlusFemaleServed=getNoOfOvc18PlusServed(rpt,AppConstant.ACTIVE_NUM,startDate, endDate,AppConstant.OLDER_OVC_STARTAGE_NUM,AppConstant.OLDER_OVC_ENDAGE_NUM,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate activeOvc18PlusMaleServed=getNoOfOvc18PlusServed(rpt,AppConstant.ACTIVE_NUM,startDate, endDate,AppConstant.OLDER_OVC_STARTAGE_NUM,AppConstant.OLDER_OVC_ENDAGE_NUM,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate graduatedOvc18PlusFemaleServed=getNoOfOvc18PlusServed(rpt,AppConstant.GRADUATED_NUM,startDate, endDate,AppConstant.OLDER_OVC_STARTAGE_NUM,AppConstant.OLDER_OVC_ENDAGE_NUM,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate graduatedOvc18PlusMaleServed=getNoOfOvc18PlusServed(rpt,AppConstant.GRADUATED_NUM,startDate, endDate,AppConstant.OLDER_OVC_STARTAGE_NUM,AppConstant.OLDER_OVC_ENDAGE_NUM,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        
        
        DatimReportTemplate gradLessThan1Rt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,0,0,"All",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        
        DatimReportTemplate gradLessThan1MaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,0,0,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate gradLessThan1FemaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,0,0,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad1to4MaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,1,4,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad1to4FemaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,1,4,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad5to9MaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,5,9,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad5to9FemaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,5,9,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad1to9Rt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,1,9,"All",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad10to14MaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,10,14,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad10to14FemaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,10,14,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad15to17MaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,15,17,"M",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        DatimReportTemplate grad15to17FemaleRt=getNoOfGraduatedOvcServed(rpt,startOfFinancialYear, endDate,15,17,"F",AppConstant.ALL_SERVICE_DOMAIN,null,null,false);
        
        DatimReportTemplate gradCaregivers18AndAboveMaleRt=getNoOfGraduatedCaregiversServed(rpt,startOfFinancialYear, endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"M");
        DatimReportTemplate gradCaregivers18AndAboveFemaleRt=getNoOfGraduatedCaregiversServed(rpt,startOfFinancialYear, endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"F");
        
        //Caregivers 18+ graduated
        int totalActiveCaregivers18AndAbove=activeCaregivers18AndAboveMaleRt.getOvc_servActive()+activeCaregivers18AndAboveFemaleRt.getOvc_servActive();
        int totalGraduatedCaregivers18AndAbove=gradCaregivers18AndAboveMaleRt.getOvc_servGraduated()+gradCaregivers18AndAboveFemaleRt.getOvc_servGraduated();
        
        DatimReportTemplate activeCaregiver18to24MaleRt=getNoOfActiveCaregiversServed(rpt,startDate, endDate,18,24,"M");
        DatimReportTemplate gradCaregiver18to24MaleRt=getNoOfGraduatedCaregiversServed(rpt,startOfFinancialYear, endDate,18,24,"M");
        DatimReportTemplate activeCaregiver18to24FemaleRt=getNoOfActiveCaregiversServed(rpt,startDate, endDate,18,24,"F");
        DatimReportTemplate gradCaregiver18to24FemaleRt=getNoOfGraduatedCaregiversServed(rpt,startOfFinancialYear, endDate,18,24,"F");
        
        DatimReportTemplate active25AndAboveMaleRt=getNoOfActiveCaregiversServed(rpt,startDate, endDate,25,AppConstant.ADULT_ENDAGE_NUM,"M");
        DatimReportTemplate grad25AndAboveMaleRt=getNoOfGraduatedCaregiversServed(rpt,startOfFinancialYear, endDate,25,AppConstant.ADULT_ENDAGE_NUM,"M");
        DatimReportTemplate active25AndAboveFemaleRt=getNoOfActiveCaregiversServed(rpt,startDate, endDate,25,AppConstant.ADULT_ENDAGE_NUM,"F");
        DatimReportTemplate grad25AndAboveFemaleRt=getNoOfGraduatedCaregiversServed(rpt,startOfFinancialYear, endDate,25,AppConstant.ADULT_ENDAGE_NUM,"F");
        
              
        /*int totalPositive=hivPosActiveRt.getHiv_statNumerator()+hivPosGraduatedRt.getHiv_statNumerator();
        int totalNegative=hivNegActiveRt.getHiv_statNumerator()+hivNegGraduatedRt.getHiv_statNumerator();
        int totalUnknown=hivUnknownActiveRt.getHiv_statNumerator()+hivUnknownGraduatedRt.getHiv_statNumerator();
        
        int totalPositiveOnTreatment=activeHivPositiveOnTreatmentRt.getEnrolledOnART()+graduatedHivPositiveOnTreatmentRt.getEnrolledOnART();
        int totalPositiveNotOnTreatment=totalPositive-totalPositiveOnTreatment;
        int totalTestNotIndicated=activeTestNotIndicatedRt.getTestNotIndicated()+graduatedTestNotIndicatedRt.getTestNotIndicated();
        //int totalUnknownWithoutTestNotIndicated=totalUnknown-totalTestNotIndicated;
        int hivUnknownOtherReasons=totalUnknown-totalTestNotIndicated;
        */
        int totalLessThan1=activeLessThan1Rt.getOvc_servActive()+gradLessThan1Rt.getOvc_servGraduated();
        int total1to9=active1to9Rt.getOvc_servActive()+grad1to9Rt.getOvc_servGraduated();
        int total10to14Male=active10to14MaleRt.getOvc_servActive()+grad10to14MaleRt.getOvc_servGraduated();
        int total10to14Female=active10to14FemaleRt.getOvc_servActive()+grad10to14FemaleRt.getOvc_servGraduated();
        int total15to17Male=active15to17MaleRt.getOvc_servActive()+grad15to17MaleRt.getOvc_servGraduated();
        int total15to17Female=active15to17FemaleRt.getOvc_servActive()+grad15to17FemaleRt.getOvc_servGraduated();
        
        int total18to24Male=activeCaregiver18to24MaleRt.getOvc_servActive()+gradCaregiver18to24MaleRt.getOvc_servGraduated();
        int total18to24Female=activeCaregiver18to24FemaleRt.getOvc_servActive()+gradCaregiver18to24FemaleRt.getOvc_servGraduated();
        int total25AndAboveMale=active25AndAboveMaleRt.getOvc_servActive()+grad25AndAboveMaleRt.getOvc_servGraduated();
        int total25AndAboveFemale=active25AndAboveFemaleRt.getOvc_servActive()+grad25AndAboveFemaleRt.getOvc_servGraduated();
        
        //calculate total active for caregivers
        int totalActiveCaregivers=activeCaregiver18to24MaleRt.getOvc_servActive()+activeCaregiver18to24FemaleRt.getOvc_servActive()+active25AndAboveMaleRt.getOvc_servActive()+active25AndAboveFemaleRt.getOvc_servActive();
        
        //calculate total caregivers graduated and served
        int totalGraduatedCaregivers=gradCaregiver18to24MaleRt.getOvc_servGraduated()+gradCaregiver18to24FemaleRt.getOvc_servGraduated()+grad25AndAboveMaleRt.getOvc_servGraduated()+grad25AndAboveFemaleRt.getOvc_servGraduated();
        
        //calculate total caregivers 18+
        int total18to24=total18to24Male+total18to24Female;
        int total25AndAbove=total25AndAboveMale+total25AndAboveFemale;
        //int caregiversTransfered=caregiversTransferedRt.getOvc_servTransfered();
        int caregiversExitedWithoutGraduation=caregiversExitedWithoutGraduationRt.getOvc_servExitedWithoutGraduation();
        dform.setLevel2Ou(rpt.getLevel2OuId());
        dform.setLevel3Ou(rpt.getLevel3OuId());
        dform.setLevel4Ou("All");
        dform.setDateCreated(DateManager.getCurrentDateInstance());
        dform.setCbo("All");
        dform.setPartnerCode(partnerCode);
        dform.setPartnerName(partner.getPartnerName());
        dform.setPeriod(startDate+"-"+endDate);
        dform.setLastModifiedDate(DateManager.getCurrentDateInstance());
        dform.setRecordedBy("");
        
        dform.setOvc_servActiveLessThan1Female(activeLessThan1FemaleRt.getOvc_servActive());
        dform.setOvc_servActiveLessThan1Male(activeLessThan1MaleRt.getOvc_servActive());
        dform.setOvc_servActive1to4Female(active1to4FemaleRt.getOvc_servActive());
        dform.setOvc_servActive1to4Male(active1to4MaleRt.getOvc_servActive());
        dform.setOvc_servActive5to9Female(active5to9FemaleRt.getOvc_servActive());
        dform.setOvc_servActive5to9Male(active5to9MaleRt.getOvc_servActive());
        dform.setOvc_servActive10to14Female(active10to14FemaleRt.getOvc_servActive());
        dform.setOvc_servActive10to14Male(active10to14MaleRt.getOvc_servActive());
        dform.setOvc_servActive15to17Female(active15to17FemaleRt.getOvc_servActive());
        dform.setOvc_servActive15to17Male(active15to17MaleRt.getOvc_servActive());
        //Caregivers 18 and above
        dform.setOvc_servActive18AndAboveFemale(activeCaregivers18AndAboveFemaleRt.getOvc_servActive());
        dform.setOvc_servActive18AndAboveMale(activeCaregivers18AndAboveMaleRt.getOvc_servActive());
        
        dform.setOvc_servGraduatedLessThan1Female(gradLessThan1FemaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduatedLessThan1Male(gradLessThan1MaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated1to4Female(grad1to4FemaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated1to4Male(grad1to4MaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated5to9Female(grad5to9FemaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated5to9Male(grad5to9MaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated10to14Female(grad10to14FemaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated10to14Male(grad10to14MaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated15to17Female(grad15to17FemaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated15to17Male(grad15to17MaleRt.getOvc_servGraduated());
        
        dform.setOvc_servGraduated18AndAboveFemale(gradCaregivers18AndAboveFemaleRt.getOvc_servGraduated());
        dform.setOvc_servGraduated18AndAboveMale(gradCaregivers18AndAboveMaleRt.getOvc_servGraduated());
        
        dform.setOvc_servFemale18To24(activeOvc18PlusFemaleServed.getOvc_servNumerator());
        dform.setOvc_servMale18To24(activeOvc18PlusMaleServed.getOvc_servNumerator());
        dform.setOvc_servGraduated18to24Female(graduatedOvc18PlusFemaleServed.getOvc_servNumerator()); 
        dform.setOvc_servGraduated18to24Male(graduatedOvc18PlusMaleServed.getOvc_servNumerator()); 
        
        int totalOvc18To24Active=activeOvc18PlusMaleServed.getOvc_servNumerator()+activeOvc18PlusFemaleServed.getOvc_servNumerator();
        int totalOvc18To24Graduated=graduatedOvc18PlusMaleServed.getOvc_servNumerator()+graduatedOvc18PlusFemaleServed.getOvc_servNumerator();
                
        dform.setOvc_servActive(activeRt.getOvc_servActive()+totalActiveCaregivers);
        dform.setOvc_servGraduated(gradRt.getOvc_servGraduated()+totalGraduatedCaregivers);
        dform.setOvc_servNumerator(activeRt.getOvc_servActive()+totalOvc18To24Active+gradRt.getOvc_servGraduated()+totalOvc18To24Graduated+totalActiveCaregivers18AndAbove+totalGraduatedCaregivers18AndAbove);
        //dform.setOvc_servNumerator(activeRt.getOvc_servActive()+gradRt.getOvc_servGraduated()+total18to24+total25AndAbove);
        dform.setOvc_servTransfered(totalChildrenTransfered+totalCaregiversTransfered);
        dform.setOvc_servExitedWithoutGraduation(exitedWithoutGraduationRt.getOvc_servExitedWithoutGraduation()+caregiversExitedWithoutGraduation);
        /*dform.setTotalPositive(totalPositive);
        dform.setTotalNegative(totalNegative);
        dform.setTotalUnknown(totalUnknown);
        //dform.setTotalUnknown(totalUnknownWithoutTestNotIndicated);
        dform.setHiv_statNumerator(totalPositive+totalNegative+totalUnknown+totalTestNotIndicated);
        dform.setEnrolledOnART(totalPositiveOnTreatment);
        dform.setNotEnrolledOnART(totalPositiveNotOnTreatment);
        dform.setTestNotIndicated(totalTestNotIndicated);
        dform.setOtherReasons(hivUnknownOtherReasons);*/
        
        
        dform.setTransferedToPEPFAR(totalTransferedToPEPFAR);
        dform.setTransferedToNonPEPFAR(totalTransferedToNonPEPFAR);
        
        
        return dform;
    }
    public ReportTemplate getNumberOfHouseholdsThatAccessFundToMeetUnexpectedExpencesForDatim(Indicator indicator,ReportParameterTemplate rpt,int enrollmentStatus,int unexpectedExpenditureValue,int abilityToAccessMoneyToPay)
    {
        ReportTemplate rt=new ReportTemplate();
        DaoUtility util=new DaoUtility();
        CaregiverAccessToEmergencyFundDao dao=util.getCaregiverAccessToEmergencyFundDaoInstance();
        
        String maleSex=AppConstant.MALESEX;
        String femaleSex=AppConstant.FEMALESEX;
        
        try
        {
            int maleCount=dao.getNumberOfCaregiversThatHadAccessToEmergencyFundForDatim(rpt, rpt.getStartAge(),rpt.getEndAge(), rpt.getStartDate(), rpt.getEndDate(), enrollmentStatus,maleSex,unexpectedExpenditureValue, abilityToAccessMoneyToPay);
            int femaleCount=dao.getNumberOfCaregiversThatHadAccessToEmergencyFundForDatim(rpt,rpt.getStartAge(),rpt.getEndAge(),rpt.getStartDate(), rpt.getEndDate(), enrollmentStatus,femaleSex,unexpectedExpenditureValue, abilityToAccessMoneyToPay);
            int total=maleCount+femaleCount;
            
            rt.setMaleTotal(maleCount);
            rt.setFemaleTotal(femaleCount);
            rt.setGrandTotal(total);
            if(indicator !=null)
            {
                rt.setIndicatorId(indicator.getIndicatorId());
                rt.setIndicatorName(indicator.getIndicatorName());
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return rt;
    }
    
    public DatimReportTemplate getNoOfActiveCaregiversServed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfActiveCaregiversServedByEnrollmentStatus(rpt,startOfLastQuarter,endDate,startAge,endAge,sex);
        //int count=getNoOfCaregiversServedByEnrollmentStatus(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,endDate,startAge,endAge,sex);
        rt.setOvc_servActive(count);
        return rt;
    }
    public DatimReportTemplate getNoOfGraduatedCaregiversServed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfCaregiversServedByEnrollmentStatus(rpt,AppConstant.GRADUATED_NUM,startDate,endDate,startAge,endAge,sex);
        rt.setOvc_servGraduated(count);
        return rt;
    }
    public DatimReportTemplate getNoOfCaregiversServedAndTransfered(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate(); //18,200
        if(!rpt.isAdultAgeDisaggregated())
        {
            startAge=AppConstant.ADULT_STARTAGE_NUM;
            endAge=AppConstant.ADULT_ENDAGE_NUM;
        }
        DatimReportTemplate pepfarRt=getNoOfCaregiversServedAndTransferedToPEPFAR(rpt,startDate,endDate,startAge,endAge,sex);
        DatimReportTemplate nonPepfarRt=getNoOfCaregiversServedAndTransferedToNonPEPFAR(rpt,startDate,endDate,startAge,endAge,sex);
        rt.setOvc_servTransfered(pepfarRt.getOvc_servTransfered()+nonPepfarRt.getOvc_servTransfered());
        return rt;
    }
    public DatimReportTemplate getNoOfCaregiversServedAndTransferedToPEPFAR(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        //,18,200
        DatimReportTemplate rt=new DatimReportTemplate();;
        int pepfarcount=getNoOfCaregiversServedByEnrollmentStatus(rpt,AppConstant.TRANSFERED_PEPFAR_NUM,startDate,endDate,startAge,endAge,sex);
        rt.setOvc_servTransfered(pepfarcount);
        return rt;
    }
    public DatimReportTemplate getNoOfCaregiversServedAndTransferedToNonPEPFAR(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        //
        DatimReportTemplate rt=new DatimReportTemplate();;
        int nonPepfarcount=getNoOfCaregiversServedByEnrollmentStatus(rpt,AppConstant.TRANSFERED_NONPEPFAR_NUM,startDate,endDate,startAge,endAge,sex);
        rt.setOvc_servTransfered(nonPepfarcount);
        return rt;
    }
    public DatimReportTemplate getNoOfCaregiversExitedWithoutGraduationServed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        try
        {
            if(!rpt.isAdultAgeDisaggregated())
            {
                startAge=AppConstant.ADULT_STARTAGE_NUM;
                endAge=AppConstant.ADULT_ENDAGE_NUM;
            }
            //System.err.println("endDate in DatimReportGenerator.getNoOfCaregiversExitedWithoutGraduationServed is "+endDate);
            String nextMonth=DateManager.getNextMonth(endDate, 0);
            //System.err.println("DateManager.getNextMonth(2017-01-31, 0) is "+DateManager.getNextMonth("2017-01-31", 0));
            //System.err.println("endDate is DatimReportGenerator.getNoOfCaregiversExitedWithoutGraduationServed is "+endDate);
            DaoUtility util=new DaoUtility();
            int count=util.getAdultHouseholdMemberDaoInstance().getNumberOfAdultHouseholdMembersExitedWithoutGraduation(rpt, startDate, endDate,startAge,endAge,sex);
                    //getNoOfCaregiversServedByEnrollmentStatus(rpt,AppConstant.EXITED_WITHOUT_GRADUATION_NUM,startDate,endDate,0,17,sex);
            rt.setOvc_servExitedWithoutGraduation(count);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return rt;
    }
    public DatimReportTemplate getNoOfOvc18PlusServed(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly)
    {
        //An active beneficiary must have been served in the last quarter, hence the start date should be the start of the last quarter
        String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
        String startOfFinancialYear=rpt.getFinancialYear().getStartYear()+"-"+rpt.getFinancialYear().getStartMonth()+"-01";
        if(enrollmentStatus==AppConstant.ACTIVE_NUM)
        startDate=startOfLastQuarter;
        else
        startDate=startOfFinancialYear;
        //
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfOvcServedByEnrollmentStatus(rpt,enrollmentStatus,startDate,endDate,startAge,endAge,sex,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly,AppConstant.HIV_ALL_STATUS_NUM);
        rt.setOvc_servNumerator(count);//.setOvc_servActive(count);
        return rt;
    }
    public DatimReportTemplate getNoOfActiveOvcServed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly)
    {
        //An active beneficiary must have been served in the last quarter, hence the start date should be the start of the last quarter
        String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
        DatimReportTemplate rt=new DatimReportTemplate();
        //System.err.println("enrollmentStreamCode in getNoOfActiveOvcServed is "+enrollmentStreamCode);
        //System.err.println("newlyEnrolledOnly in getNoOfActiveOvcServed is "+newlyEnrolledOnly);
        int count=getNoOfOvcServedByEnrollmentStatus(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,endDate,startAge,endAge,sex,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly,AppConstant.HIV_ALL_STATUS_NUM);
        rt.setOvc_servActive(count);
        return rt;
    }
    public DatimReportTemplate getNoOfGraduatedOvcServed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        //System.err.println("enrollmentStreamCode in getNoOfGraduatedOvcServed is "+enrollmentStreamCode);
        int count=getNoOfOvcServedByEnrollmentStatus(rpt,AppConstant.GRADUATED_NUM,startDate,endDate,startAge,endAge,sex,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly,AppConstant.HIV_ALL_STATUS_NUM);
        rt.setOvc_servGraduated(count);
        return rt;
    }
    public DatimReportTemplate getNoOfOvcServedAndTransfered(ReportParameterTemplate rpt,String startDate,String endDate,int startAge, int endAge, String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();;
        DatimReportTemplate pepfarRt=getNoOfOvcServedAndTransferedToPEPFAR(rpt,startDate,endDate,startAge, endAge,sex);
        DatimReportTemplate nonPepfarRt=getNoOfOvcServedAndTransferedToPEPFAR(rpt,startDate,endDate,startAge, endAge,sex);
        rt.setOvc_servTransfered(pepfarRt.getOvc_servTransfered()+nonPepfarRt.getOvc_servTransfered());
        return rt;
    }
    public DatimReportTemplate getNoOfOvcServedAndTransferedToPEPFAR(ReportParameterTemplate rpt,String startDate,String endDate,int startAge, int endAge,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();;
        int pepfarcount=getNoOfOvcServedByEnrollmentStatus(rpt,AppConstant.TRANSFERED_PEPFAR_NUM,startDate,endDate,startAge, endAge,sex,AppConstant.ALL_SERVICE_DOMAIN,null,null,false,AppConstant.HIV_ALL_STATUS_NUM);
        rt.setOvc_servTransfered(pepfarcount);
        return rt;
    }
    public DatimReportTemplate getNoOfOvcServedAndTransferedToNonPEPFAR(ReportParameterTemplate rpt,String startDate,String endDate,int startAge, int endAge,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();;
        int nonPepfarcount=getNoOfOvcServedByEnrollmentStatus(rpt,AppConstant.TRANSFERED_NONPEPFAR_NUM,startDate,endDate,startAge, endAge,sex,AppConstant.ALL_SERVICE_DOMAIN,null,null,false,AppConstant.HIV_ALL_STATUS_NUM);
        rt.setOvc_servTransfered(nonPepfarcount);
        return rt;
    }
    public DatimReportTemplate getNoOfOvcExitedWithoutGraduationServed(ReportParameterTemplate rpt,String startDate,String endDate,int startAge, int endAge,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        try
        {
            //String nextMonth=DateManager.getNextMonth(endDate, 0);
            DaoUtility util=new DaoUtility();
            int count=util.getChildEnrollmentDaoInstance().getNumberOfOvcExitedWithoutGraduation(rpt, startDate, endDate, startAge,endAge,sex);
            rt.setOvc_servExitedWithoutGraduation(count);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return rt;
    }
    public DatimReportTemplate getNoOfActiveHivPositiveOvcServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfOvcServedByEnrollmentStatus(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,endDate,startAge,endAge,sex,AppConstant.ALL_SERVICE_DOMAIN,null,null,false,AppConstant.HIV_POSITIVE_NUM);
        //getNoOfOvcServedByEnrollmentStatusAndHivStatus(rpt,AppConstant.ACTIVE_NUM,AppConstant.HIV_POSITIVE_NUM,startOfLastQuarter,endDate,startAge,endAge,sex);
        rt.setHiv_statNumerator(count);
        return rt;
    }
    public DatimReportTemplate getNoOfGraduatedHivPositiveOvcServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfOvcServedByEnrollmentStatusAndHivStatus(rpt,AppConstant.GRADUATED_NUM,AppConstant.HIV_POSITIVE_NUM,startDate,endDate,startAge,endAge,sex);
        rt.setHiv_statNumerator(count);
        return rt;
    }
    public DatimReportTemplate getNoOfActiveHivNegativeOvcServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfOvcServedByEnrollmentStatus(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,endDate,startAge,endAge,sex,AppConstant.ALL_SERVICE_DOMAIN,null,null,false,AppConstant.HIV_NEGATIVE_NUM);
        //getNoOfOvcServedByEnrollmentStatusAndHivStatus(rpt,AppConstant.ACTIVE_NUM,AppConstant.HIV_NEGATIVE_NUM,startOfLastQuarter,endDate,startAge,endAge,sex);
        rt.setHiv_statNumerator(count);
        return rt;
    }
    public DatimReportTemplate getNoOfGraduatedHivNegativeOvcServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfOvcServedByEnrollmentStatusAndHivStatus(rpt,AppConstant.GRADUATED_NUM,AppConstant.HIV_NEGATIVE_NUM,startDate,endDate,startAge,endAge,sex);
        rt.setHiv_statNumerator(count);
        return rt;
    }
    public DatimReportTemplate getNoOfActiveHivUnknownOvcServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
        DatimReportTemplate rt=new DatimReportTemplate();
        //int count=getNoOfOvcServedByEnrollmentStatusAndHivStatus(rpt,AppConstant.ACTIVE_NUM,AppConstant.HIV_UNKNOWN_NUM,startOfLastQuarter,endDate,startAge,endAge,sex);
        int count=getNoOfOvcServedByEnrollmentStatus(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,endDate,startAge,endAge,sex,AppConstant.ALL_SERVICE_DOMAIN,null,null,false,AppConstant.HIV_UNKNOWN_NUM);
                
        rt.setHiv_statNumerator(count);
        return rt;
    }
    public DatimReportTemplate getNoOfGraduatedHivUnknownOvcServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfOvcServedByEnrollmentStatusAndHivStatus(rpt,AppConstant.GRADUATED_NUM,AppConstant.HIV_UNKNOWN_NUM,startDate,endDate,startAge,endAge,sex);
        rt.setHiv_statNumerator(count);
        return rt;
    }
    public DatimReportTemplate getNoOfActiveHivPositiveOvcOnTreatmentServed(ReportParameterTemplate rpt,int stargAge, int endAge,String startDate,String endDate,String sex)
    {
        String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfHivPositiveOvcOnTreatmentServedByEnrollmentStatus(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,endDate,stargAge,endAge,sex);
        rt.setEnrolledOnART(count);
        return rt;
    }
    public DatimReportTemplate getNoOfGraduatedHivPositiveOvcOnTreatmentServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfHivPositiveOvcOnTreatmentServedByEnrollmentStatus(rpt,AppConstant.GRADUATED_NUM,startDate,endDate,startAge,endAge,sex);
        rt.setEnrolledOnART(count);
        return rt;
    }
    public DatimReportTemplate getNoOfActiveOvcWithTestNotIndicatedStatusServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        String startOfLastQuarter=fym.getStartDateOfQuarter(endDate);
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfOvcServedByEnrollmentStatus(rpt,AppConstant.ACTIVE_NUM,startDate,endDate,startAge,endAge,sex,AppConstant.ALL_SERVICE_DOMAIN,null,null,false,AppConstant.HIV_TEST_NOT_INDICATED_NUM);
        //getNumberOfOvcNotAtRiskAndServed(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,endDate,startAge,endAge,sex);
        rt.setTestNotIndicated(count);
        System.err.println("count in getNumberOfOvcNotAtRiskAndServed is "+count);
        return rt;
    }
    public DatimReportTemplate getNoOfGraduatedOvcWithTestNotIndicatedStatusServed(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNumberOfOvcNotAtRiskAndServed(rpt,AppConstant.GRADUATED_NUM,startDate,endDate,startAge,endAge,sex);
        rt.setTestNotIndicated(count);
        System.err.println("count in getNumberOfOvcNotAtRiskAndServed is "+count);
        return rt;
    }
    /*public DatimReportTemplate getNoOfActiveOvcLessThan1YearServed(ReportParameterTemplate rpt,String startDate,String endDate)
    {
        DatimReportTemplate rt=new DatimReportTemplate();
        int count=getNoOfOvcServedByEnrollmentStatus(rpt,1,startDate,endDate,0,0);
        rt.setOvc_servActive(count);
        return rt;
    }*/
    public int getNoOfActiveCaregiversServedByEnrollmentStatusAndHivStatusForDatim(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex,int hivStatus,int enrolledOnTreatment,boolean viralLoadEligibilityRequired,boolean viralLoadResultRequired,boolean viralLoadSuppressionRequired)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfCaregivers=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfCaregivers=util.getHouseholdServiceDaoInstance().getNumberOfActiveAdultMembersServedByHivStatusForDatim(orgUnitQuery, startDate, endDate, startAge, endAge, sex,hivStatus,enrolledOnTreatment, viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
            //orgUnitQuery, enrollmentStatus, startDate, endDate,startAge,endAge,sex);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfCaregivers;
    }
    public int getNoOfCaregiversServedByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge,int endAge,String sex,int hivStatus,int enrolledOnTreatment,boolean viralLoadEligibilityRequired,boolean viralLoadResultRequired,boolean viralLoadSuppressionRequired)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfCaregivers=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfCaregivers=util.getHouseholdServiceDaoInstance().getNumberOfAdultMembersServedByEnrollmentStatusAndHivStatusForDatim(orgUnitQuery, enrollmentStatus, startDate, endDate, startAge, endAge, sex,hivStatus,enrolledOnTreatment,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
            //countOfCaregivers=util.getHouseholdServiceDaoInstance().getNumberOfBeneficiariesServedByEnrollmentStatus(orgUnitQuery, enrollmentStatus, startDate, endDate, startAge, endAge, sex);//orgUnitQuery, enrollmentStatus, startDate, endDate,startAge,endAge,sex);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfCaregivers;
    }
    public int getNoOfActiveCaregiversServedByEnrollmentStatus(ReportParameterTemplate rpt,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfCaregivers=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfCaregivers=util.getHouseholdServiceDaoInstance().getNumberOfActiveAdultMembersServedForDatim(orgUnitQuery, startDate, endDate, startAge, endAge, sex);
            //orgUnitQuery, enrollmentStatus, startDate, endDate,startAge,endAge,sex);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfCaregivers;
    }
    public int getNoOfCaregiversServedByEnrollmentStatus(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfCaregivers=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfCaregivers=util.getHouseholdServiceDaoInstance().getNumberOfAdultMembersServedByEnrollmentStatusForDatim(orgUnitQuery, enrollmentStatus, startDate, endDate, startAge, endAge, sex);
            //countOfCaregivers=util.getHouseholdServiceDaoInstance().getNumberOfBeneficiariesServedByEnrollmentStatus(orgUnitQuery, enrollmentStatus, startDate, endDate, startAge, endAge, sex);//orgUnitQuery, enrollmentStatus, startDate, endDate,startAge,endAge,sex);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfCaregivers;
    }
    public int getNoOfCaregiversServedByEnrollmentStatusForReportPeriod(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge,int endAge,String sex,boolean newlyEnrolled)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfCaregivers=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfCaregivers=util.getHouseholdServiceDaoInstance().getNumberOfAdultMembersServedByEnrollmentStatusWithinReportPeriod(orgUnitQuery, enrollmentStatus, startDate, endDate, startAge, endAge, sex,newlyEnrolled);
            //countOfCaregivers=util.getHouseholdServiceDaoInstance().getNumberOfBeneficiariesServedByEnrollmentStatus(orgUnitQuery, enrollmentStatus, startDate, endDate, startAge, endAge, sex);//orgUnitQuery, enrollmentStatus, startDate, endDate,startAge,endAge,sex);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfCaregivers;
    }
    public int getNoOfOvcServedByEnrollmentStatus(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly,int hivStatus)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfOvc=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            if(enrollmentStatus==AppConstant.ACTIVE_NUM)
            countOfOvc=util.getChildServiceDaoInstance().getNumberOfActiveOvcServedForDatim(orgUnitQuery, startDate, endDate,startAge,endAge,sex,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly,hivStatus);
            else
            countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusForDatim(orgUnitQuery, enrollmentStatus, startDate, endDate,startAge,endAge,sex,enrollmentStreamCode,newlyEnrolledOnly);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfOvc;
    }
    public int getNoOfOvcServedByEnrollmentStatusForSpecifiedReportPeriod(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly,int enrollmentSetting,int birthCertificateValue,int childInSchoolValue)
    {
        //This method gets the number served within specified report period, not limiting it to the last quarter even if enrollment status is active.
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfOvc=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            //String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);//
            countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusWithinReportPeriod(rpt,enrollmentStatus, startDate, endDate,startAge,endAge,sex,enrollmentStreamCode,newlyEnrolledOnly,enrollmentSetting,birthCertificateValue,childInSchoolValue);
            //countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusForDatim(orgUnitQuery, enrollmentStatus, startDate, endDate,startAge,endAge,sex,enrollmentStreamCode,newlyEnrolledOnly);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfOvc;
    }
    public int getNoOfOvcServedByEnrollmentStatusForOVC_EDU(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge,int endAge,String sex,int serviceDomain,String serviceType,int birthCertificateValue,int childInSchoolValue,int regularSchoolAttendance)
    {
        //This method gets the number served within specified report period, not limiting it to the last quarter even if enrollment status is active.
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfOvc=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            //String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);//
            countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusForOVC_EDU(rpt,enrollmentStatus, startDate, endDate,startAge,endAge,sex,childInSchoolValue,regularSchoolAttendance);
            //countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusForDatim(orgUnitQuery, enrollmentStatus, startDate, endDate,startAge,endAge,sex,enrollmentStreamCode,newlyEnrolledOnly);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfOvc;
    }
    public int getNoOfOvcServedByEnrollmentStatusAndHivStatus(ReportParameterTemplate rpt,int enrollmentStatus,int hivStatus,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfOvc=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusAndHivStatusForDatim(orgUnitQuery, enrollmentStatus,hivStatus, startDate, endDate,startAge,endAge,sex,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfOvc;
    }//
    public int getNumberOfOvcNotAtRiskAndServed(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge,int endAge,String sex)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfOvc=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfOvc=getNoOfOvcServedByEnrollmentStatusAndHivStatus(rpt,enrollmentStatus,AppConstant.HIV_TEST_NOT_INDICATED_NUM,startDate,endDate,0,17,sex);
            //countOfOvc=util.getChildServiceDaoInstance().getNumberOfHivUnknownOvcNotAtRiskServed(orgUnitQuery, enrollmentStatus,startDate, endDate,startAge,endAge,sex);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfOvc;
    }
    public int getNoOfOvcServedByEnrollmentStatusAndHivRiskAssessmentStatus(ReportParameterTemplate rpt,int enrollmentStatus,int hivStatus,String startDate,String endDate,int startAge,int endAge,String sex,int hivRiskStatus)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfOvc=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedAndRiskAssessedByEnrollmentStatusAndHivStatus(orgUnitQuery, enrollmentStatus,hivStatus, startDate, endDate,startAge,endAge,sex,hivRiskStatus);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfOvc;
    }
    public int getNoOfHivPositiveOvcOnTreatmentServedByEnrollmentStatus(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge, int endAge,String sex)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfOvc=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusAndHivStatusForDatim(orgUnitQuery, enrollmentStatus,1, startDate, endDate,startAge,endAge,sex,AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfOvc;
    }
    public int getNoOfHivPositiveOvcOnTreatmentServedByEnrollmentStatusForViralLoadCascade(ReportParameterTemplate rpt,int enrollmentStatus,String startDate,String endDate,int startAge, int endAge,String sex,boolean viralLoadEligibilityRequired,boolean viralLoadResultRequired,boolean viralLoadSuppressionRequired)
    {
        SubQueryGenerator sqg=new SubQueryGenerator();
        int countOfOvc=0;
        DaoUtility util=new DaoUtility();           
        try
        {
            String orgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            countOfOvc=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusAndHivStatusAndViralLoadCascadeForDatim(orgUnitQuery, enrollmentStatus,1, startDate, endDate,startAge,endAge,sex,AppConstant.ENROLLED_ON_TREATMENT_YES_NUM,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return countOfOvc;
    }
    public List getDatimFormList(ReportParameterTemplate rpt)
    {
        DaoUtility util=new DaoUtility();
        List datimFormList=new ArrayList();
        try
        {
            if(rpt !=null)
            {
                List level3OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel3OuList(rpt);
                if(level3OuList !=null)
                {
                    OrganizationUnit level3Ou=null;
                    String level3OuId=null;
                    for(int i=0; i<level3OuList.size(); i++)
                    {
                        level3Ou=(OrganizationUnit)level3OuList.get(i);
                        level3OuId=level3Ou.getUid();
                        rpt.setLevel3OuId(level3OuId);
                        datimFormList.add(getDatimReport(rpt));
                        //if(i==2)
                        //break;
                    }
                }
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return datimFormList;
    }
    public List convertDatimReportTemplateToReportTemplates(DatimReportTemplate drt)
    {
        List reportTemplateList=new ArrayList();
        try
        {
            if(drt !=null)
            {
                DaoUtility util=new DaoUtility();
                OrganizationUnit level2Ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(drt.getLevel2Ou());
                OrganizationUnit level3Ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(drt.getLevel3Ou());
                String level2OuName=null;
                String partnerCode=drt.getPartnerCode();
                
                //String level3OuName=null;
                if(level2Ou !=null)
                level2OuName=level2Ou.getName();
                //if(level3Ou !=null)
                //level3OuName=level3Ou.getName();
                String ageDisaggregation="All";
                String allDisaggregation="All";
                String bothSexes="All";
                ReportTemplate rt=new ReportTemplate();
                
                /*reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"HIV Stat Numerator",bothSexes,ageDisaggregation,allDisaggregation,drt.getPeriod(),drt.getHiv_statNumerator(),DataElementCategoryOptionComboManager.getHIVSTATNumeratorDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"Reported HIV positive to IP",bothSexes,ageDisaggregation,allDisaggregation,drt.getPeriod(),drt.getTotalPositive(),DataElementCategoryOptionComboManager.getReportedHIVPositiveToIPDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"Of those positive: Currently receiving ART",bothSexes,ageDisaggregation,allDisaggregation,drt.getPeriod(),drt.getEnrolledOnART(),DataElementCategoryOptionComboManager.getReceivingARTDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"Of those positive: Not currently receiving ART",bothSexes,ageDisaggregation,allDisaggregation,drt.getPeriod(),drt.getNotEnrolledOnART(),DataElementCategoryOptionComboManager.getHIVPositiveNotOnARTDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"Reported HIV negative to IP",bothSexes,ageDisaggregation,allDisaggregation,drt.getPeriod(),drt.getTotalNegative(),DataElementCategoryOptionComboManager.getHIVNegatveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"Test not required based on risk assessment",bothSexes,ageDisaggregation,allDisaggregation,drt.getPeriod(),drt.getTestNotIndicated(),DataElementCategoryOptionComboManager.getTestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"No HIV status reported to IP (HIV status unknown)",bothSexes,ageDisaggregation,allDisaggregation,drt.getPeriod(),drt.getTotalUnknown(),DataElementCategoryOptionComboManager.getNoHIVStatusReportedDataElementCategoryOptionCombo()));
                */
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","<1",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTMaleLessThan1()+drt.getGraduatedPositiveEnrolledOnARTMaleLessThan1(),DataElementCategoryOptionComboManager.getMaleLessThan1ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","1-4",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTMale1To4()+drt.getGraduatedPositiveEnrolledOnARTMale1To4(),DataElementCategoryOptionComboManager.getMale1To4ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","5-9",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTMale5To9()+drt.getGraduatedPositiveEnrolledOnARTMale5To9(),DataElementCategoryOptionComboManager.getMale5To9ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","10-14",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTMale10To14()+drt.getGraduatedPositiveEnrolledOnARTMale10To14(),DataElementCategoryOptionComboManager.getMale10To14ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","15-17",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTMale15To17()+drt.getGraduatedPositiveEnrolledOnARTMale15To17(),DataElementCategoryOptionComboManager.getMale15To17ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","<1",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTFemaleLessThan1()+drt.getGraduatedPositiveEnrolledOnARTFemaleLessThan1(),DataElementCategoryOptionComboManager.getFemaleLessThan1ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","1-4",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTFemale1To4()+drt.getGraduatedPositiveEnrolledOnARTFemale1To4(),DataElementCategoryOptionComboManager.getFemale1To4ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","5-9",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTFemale5To9()+drt.getGraduatedPositiveEnrolledOnARTFemale5To9(),DataElementCategoryOptionComboManager.getFemale5To9ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","10-14",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTFemale10To14()+drt.getGraduatedPositiveEnrolledOnARTFemale10To14(),DataElementCategoryOptionComboManager.getFemale10To14ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","15-17",allDisaggregation,drt.getPeriod(),drt.getActivePositiveEnrolledOnARTFemale15To17()+drt.getGraduatedPositiveEnrolledOnARTFemale15To17(),DataElementCategoryOptionComboManager.getFemale15To17ReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","<1",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTMaleLessThan1()+drt.getGraduatedPositiveNotEnrolledOnARTMaleLessThan1(),DataElementCategoryOptionComboManager.getMaleLessThan1NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","1-4",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTMale1To4()+drt.getGraduatedPositiveNotEnrolledOnARTMale1To4(),DataElementCategoryOptionComboManager.getMale1To4NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","5-9",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTMale5To9()+drt.getGraduatedPositiveNotEnrolledOnARTMale5To9(),DataElementCategoryOptionComboManager.getMale5To9NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","10-14",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTMale10To14()+drt.getGraduatedPositiveNotEnrolledOnARTMale10To14(),DataElementCategoryOptionComboManager.getMale10To14NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","15-17",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTMale15To17()+drt.getGraduatedPositiveNotEnrolledOnARTMale15To17(),DataElementCategoryOptionComboManager.getMale15To17NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","<1",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTFemaleLessThan1()+drt.getGraduatedPositiveNotEnrolledOnARTFemaleLessThan1(),DataElementCategoryOptionComboManager.getFemaleLessThan1NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","1-4",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTFemale1To4()+drt.getGraduatedPositiveNotEnrolledOnARTFemale1To4(),DataElementCategoryOptionComboManager.getFemale1To4NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","5-9",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTFemale5To9()+drt.getGraduatedPositiveNotEnrolledOnARTFemale5To9(),DataElementCategoryOptionComboManager.getFemale5To9NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","10-14",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTFemale10To14()+drt.getGraduatedPositiveNotEnrolledOnARTFemale10To14(),DataElementCategoryOptionComboManager.getFemale10To14NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","15-17",allDisaggregation,drt.getPeriod(),drt.getActivePositiveNotEnrolledOnARTFemale15To17()+drt.getGraduatedPositiveNotEnrolledOnARTFemale15To17(),DataElementCategoryOptionComboManager.getFemale15To17NotReceivingARTPsoitiveDataElementCategoryOptionCombo()));
                
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","<1",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeMaleLessThan1()+drt.getGraduatedNegativeMaleLessThan1(),DataElementCategoryOptionComboManager.getMaleLessThan1NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","1-4",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeMale1To4()+drt.getGraduatedNegativeMale1To4(),DataElementCategoryOptionComboManager.getMale1To4NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","5-9",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeMale5To9()+drt.getGraduatedNegativeMale5To9(),DataElementCategoryOptionComboManager.getMale5To9NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","10-14",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeMale10To14()+drt.getGraduatedNegativeMale10To14(),DataElementCategoryOptionComboManager.getMale10To14NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","15-17",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeMale15To17()+drt.getGraduatedNegativeMale15To17(),DataElementCategoryOptionComboManager.getMale15To17NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","<1",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeFemaleLessThan1()+drt.getGraduatedNegativeFemaleLessThan1(),DataElementCategoryOptionComboManager.getFemaleLessThan1NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","1-4",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeFemale1To4()+drt.getGraduatedNegativeFemale1To4(),DataElementCategoryOptionComboManager.getFemale1To4NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","5-9",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeFemale5To9()+drt.getGraduatedNegativeFemale5To9(),DataElementCategoryOptionComboManager.getFemale5To9NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","10-14",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeFemale10To14()+drt.getGraduatedNegativeFemale10To14(),DataElementCategoryOptionComboManager.getFemale10To14NegativeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","15-17",allDisaggregation,drt.getPeriod(),drt.getActiveNegativeFemale15To17()+drt.getGraduatedNegativeFemale15To17(),DataElementCategoryOptionComboManager.getFemale15To17NegativeDataElementCategoryOptionCombo()));
                
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","<1",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownMaleLessThan1()+drt.getGraduatedUnknownMaleLessThan1(),DataElementCategoryOptionComboManager.getMaleLessThan1UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","1-4",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownMale1To4()+drt.getGraduatedUnknownMale1To4(),DataElementCategoryOptionComboManager.getMale1To4UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","5-9",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownMale5To9()+drt.getGraduatedUnknownMale5To9(),DataElementCategoryOptionComboManager.getMale5To9UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","10-14",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownMale10To14()+drt.getGraduatedUnknownMale10To14(),DataElementCategoryOptionComboManager.getMale10To14UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","15-17",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownMale15To17()+drt.getGraduatedUnknownMale15To17(),DataElementCategoryOptionComboManager.getMale15To17UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","<1",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownFemaleLessThan1()+drt.getGraduatedUnknownFemaleLessThan1(),DataElementCategoryOptionComboManager.getFemaleLessThan1UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","1-4",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownFemale1To4()+drt.getGraduatedUnknownFemale1To4(),DataElementCategoryOptionComboManager.getFemale1To4UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","5-9",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownFemale5To9()+drt.getGraduatedUnknownFemale5To9(),DataElementCategoryOptionComboManager.getFemale5To9UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","10-14",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownFemale10To14()+drt.getGraduatedUnknownFemale10To14(),DataElementCategoryOptionComboManager.getFemale10To14UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","15-17",allDisaggregation,drt.getPeriod(),drt.getActiveUnknownFemale15To17()+drt.getGraduatedUnknownFemale15To17(),DataElementCategoryOptionComboManager.getFemale15To17UndisclosedHIVStatusToIpDataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","<1",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedMaleLessThan1()+drt.getGraduatedTestNotIndicatedMaleLessThan1(),DataElementCategoryOptionComboManager.getMaleLessThan1TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","1-4",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedMale1To4()+drt.getGraduatedTestNotIndicatedMale1To4(),DataElementCategoryOptionComboManager.getMale1To4TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","5-9",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedMale5To9()+drt.getGraduatedTestNotIndicatedMale5To9(),DataElementCategoryOptionComboManager.getMale5To9TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","10-14",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedMale10To14()+drt.getGraduatedTestNotIndicatedMale10To14(),DataElementCategoryOptionComboManager.getMale10To14TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Male","15-17",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedMale15To17()+drt.getGraduatedTestNotIndicatedMale15To17(),DataElementCategoryOptionComboManager.getMale15To17TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","<1",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedFemaleLessThan1()+drt.getGraduatedTestNotIndicatedFemaleLessThan1(),DataElementCategoryOptionComboManager.getFemaleLessThan1TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","1-4",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedFemale1To4()+drt.getGraduatedTestNotIndicatedFemale1To4(),DataElementCategoryOptionComboManager.getFemale1To4TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","5-9",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedFemale5To9()+drt.getGraduatedTestNotIndicatedFemale5To9(),DataElementCategoryOptionComboManager.getFemale5To9TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","10-14",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedFemale10To14()+drt.getGraduatedTestNotIndicatedFemale10To14(),DataElementCategoryOptionComboManager.getFemale10To14TestNotRequiredDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_HIVSTAT","Female","15-17",allDisaggregation,drt.getPeriod(),drt.getActiveTestNotIndicatedFemale15To17()+drt.getGraduatedTestNotIndicatedFemale15To17(),DataElementCategoryOptionComboManager.getFemale15To17TestNotRequiredDataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV_Numerator",bothSexes,ageDisaggregation,allDisaggregation,drt.getPeriod(),drt.getOvc_servNumerator(),DataElementCategoryOptionComboManager.getOVCSERV_NumeratorDataElementCategoryOptionCombo()));
                //reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","Unknown age","Active",drt.getPeriod(),0,DataElementCategoryOptionComboManager.getMaleActiveUnknownAgeDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","<1","Active",drt.getPeriod(),drt.getOvc_servActiveLessThan1Male(),DataElementCategoryOptionComboManager.getMaleActiveLessThan1DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","1-4","Active",drt.getPeriod(),drt.getOvc_servActive1to4Male(),DataElementCategoryOptionComboManager.getMaleActive1To4DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","5-9","Active",drt.getPeriod(),drt.getOvc_servActive5to9Male(),DataElementCategoryOptionComboManager.getMaleActive5To9DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","10-14","Active",drt.getPeriod(),drt.getOvc_servActive10to14Male(),DataElementCategoryOptionComboManager.getMaleActive10To14DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","15-17","Active",drt.getPeriod(),drt.getOvc_servActive15to17Male(),DataElementCategoryOptionComboManager.getMaleActive15To17DataElementCategoryOptionCombo()));
                //Children male active 18-24 captured here
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","18-24","Active",drt.getPeriod(),drt.getOvc_servMale18To24(),DataElementCategoryOptionComboManager.getMaleActive18To20DataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","18+","Active",drt.getPeriod(),drt.getOvc_servActive18AndAboveMale(),DataElementCategoryOptionComboManager.getMaleActive18PlusDataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","<1","Active",drt.getPeriod(),drt.getOvc_servActiveLessThan1Female(),DataElementCategoryOptionComboManager.getFemaleActiveLessThan1DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","1-4","Active",drt.getPeriod(),drt.getOvc_servActive1to4Female(),DataElementCategoryOptionComboManager.getFemaleActive1To4DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","5-9","Active",drt.getPeriod(),drt.getOvc_servActive5to9Female(),DataElementCategoryOptionComboManager.getFemaleActive5To9DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","10-14","Active",drt.getPeriod(),drt.getOvc_servActive10to14Female(),DataElementCategoryOptionComboManager.getFemaleActive10To14DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","15-17","Active",drt.getPeriod(),drt.getOvc_servActive15to17Female(),DataElementCategoryOptionComboManager.getFemaleActive15To17DataElementCategoryOptionCombo()));
                //Children female active 18-24 captured here
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","18-24","Active",drt.getPeriod(),drt.getOvc_servFemale18To24(),DataElementCategoryOptionComboManager.getFemaleActive18To20DataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","18+","Active",drt.getPeriod(),drt.getOvc_servActive18AndAboveFemale(),DataElementCategoryOptionComboManager.getFemaleActive18PlusDataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","<1","Graduated",drt.getPeriod(),drt.getOvc_servGraduatedLessThan1Male(),DataElementCategoryOptionComboManager.getMaleGraduatedLessThan1DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","1-4","Graduated",drt.getPeriod(),drt.getOvc_servGraduated1to4Male(),DataElementCategoryOptionComboManager.getMaleGraduated1To4DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","5-9","Graduated",drt.getPeriod(),drt.getOvc_servGraduated5to9Male(),DataElementCategoryOptionComboManager.getMaleGraduated5To9DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","10-14","Graduated",drt.getPeriod(),drt.getOvc_servGraduated10to14Male(),DataElementCategoryOptionComboManager.getMaleGraduated10To14DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","15-17","Graduated",drt.getPeriod(),drt.getOvc_servGraduated15to17Male(),DataElementCategoryOptionComboManager.getMaleGraduated15To17DataElementCategoryOptionCombo()));
                //Children male graduated 18-24 captured here
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","18-24","Graduated",drt.getPeriod(),drt.getOvc_servGraduated18to24Male(),DataElementCategoryOptionComboManager.getMaleGraduated18To20DataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Male","18+","Graduated",drt.getPeriod(),drt.getOvc_servGraduated18AndAboveMale(),DataElementCategoryOptionComboManager.getMaleGraduated18PlusDataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","<1","Graduated",drt.getPeriod(),drt.getOvc_servGraduatedLessThan1Female(),DataElementCategoryOptionComboManager.getFemaleGraduatedLessThan1DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","1-4","Graduated",drt.getPeriod(),drt.getOvc_servGraduated1to4Female(),DataElementCategoryOptionComboManager.getFemaleGraduated1To4DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","5-9","Graduated",drt.getPeriod(),drt.getOvc_servGraduated5to9Female(),DataElementCategoryOptionComboManager.getFemaleGraduated5To9DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","10-14","Graduated",drt.getPeriod(),drt.getOvc_servGraduated10to14Female(),DataElementCategoryOptionComboManager.getFemaleGraduated10To14DataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","15-17","Graduated",drt.getPeriod(),drt.getOvc_servGraduated15to17Female(),DataElementCategoryOptionComboManager.getFemaleGraduated15To17DataElementCategoryOptionCombo()));
                //Children female graduated 18-24 captured here
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","18-24","Graduated",drt.getPeriod(),drt.getOvc_servGraduated18to24Female(),DataElementCategoryOptionComboManager.getFemaleGraduated18To20DataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV","Female","18+","Graduated",drt.getPeriod(),drt.getOvc_servGraduated18AndAboveFemale(),DataElementCategoryOptionComboManager.getFemaleGraduated18PlusDataElementCategoryOptionCombo()));
                
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV",bothSexes,ageDisaggregation,"Transfered to PEPFAR Supported program",drt.getPeriod(),drt.getTransferedToPEPFAR(),DataElementCategoryOptionComboManager.getPEPFARSupportedParterTransferDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV",bothSexes,ageDisaggregation,"Transfered to Non-PEPFAR Supported program",drt.getPeriod(),drt.getTransferedToNonPEPFAR(),DataElementCategoryOptionComboManager.getNonPEPFARSupportedParterTransferDataElementCategoryOptionCombo()));
                reportTemplateList.add(getReportTemplate(level2OuName,level3Ou,partnerCode,"OVC_SERV",bothSexes,ageDisaggregation,"Exited without graduation",drt.getPeriod(),drt.getOvc_servExitedWithoutGraduation(),DataElementCategoryOptionComboManager.getExitedWithoutGraduationDataElementCategoryOptionCombo()));
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex); 
        }
        return reportTemplateList;
    }
    private ReportTemplate getReportTemplate(String level2OuName,OrganizationUnit level3Ou,String partnerCode,String indicatorName,String sex,String ageDisaggregation,String otherDisaggregation,String period,int value,DataElementCategoryOptionCombo decc)
    {
        ReportTemplate rt=new ReportTemplate();
        String level3OuName=null;
        if(level3Ou !=null)
        level3OuName=level3Ou.getName();
        rt.setLevel2OuName(level2OuName);
        rt.setLevel3OuName(level3OuName);
        rt.setOu(level3Ou);
        rt.setPartnerCode(partnerCode);
        rt.setIndicatorName(indicatorName);
        rt.setValue(value);
        rt.setPeriod(period);
        rt.setSex(sex);
        rt.setAgeDisaggregation(ageDisaggregation);
        rt.setOtherDisaggregation(otherDisaggregation);
        rt.setDataElementCategoryOptionCombo(decc);
        return rt;
    }
    public void saveDatimReportData(List datimReportTemplateList,String userName)
    {
        DatimReportDao drtdao=new DatimReportDaoImpl();
        try
        {
            if(datimReportTemplateList !=null)
            {
                for(Object obj:datimReportTemplateList)
                {
                    DatimReportTemplate dform=(DatimReportTemplate)obj;
                    dform.setDateCreated(DateManager.getNewDateInstance());
                    dform.setLastModifiedDate(DateManager.getNewDateInstance());
                    dform.setRecordedBy(userName);
                    DatimReportTemplate dupDrt=drtdao.getDatimReportTemplate(dform);
                    if(dupDrt==null)
                    drtdao.saveDatimReportTemplate(dform);
                    else
                    {
                        dform.setRecordId(dupDrt.getRecordId());
                        drtdao.updateDatimReportTemplate(dform);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
