/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;


import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.operationsManagement.FinancialYearManager;
import com.nomis.operationsManagement.OvcServiceAttributesManager;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.CustomIndicatorsReport;
import com.nomis.ovc.dao.CustomIndicatorsReportDao;
import com.nomis.ovc.dao.CustomIndicatorsReportDaoImpl;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class CustomIndicatorsReportManagerThread extends Thread
{
    List parameterList=null;
    ReportPeriod fy=null;
    String mainReportPeriod="";
    IndicatorDictionary ind=new IndicatorDictionary();
    int[] ovcAgeDisaggregation={0,0,1,4,5,9,10,14,15,17};
    int[] hivPrevAgeDisaggregation={9,9,10,14,15,17};
    int[] ovcAgeDisaggregation_5to17={5,9,10,14,15,17};
    int[] adultAgeDisaggregation={18,24,25,200};
    int[] hhgradAgeDisaggregation={0,24,25,200};
    int[] ovcAndCaregiverAgeDisaggregation={0,0,1,4,5,9,10,14,15,17,18,24,25,AppConstant.ADULT_ENDAGE_NUM};
    String beneficiariesNewlyEnrolled=ind.getIndicatorForNumberOfNewOvcEnrolled().getIndicatorId();
    String ovcActiveAndServeId=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledAndServedInReportPeriod().getIndicatorId();
    String ovcGraduatedAndServeId=ind.getIndicatorForNumberOfOvcGraduatedButServedInReportPeriod().getIndicatorId();
    String ovcServeAndTransferedId=ind.getIndicatorForNumberOfOvcTransferedOutButServedInReportPeriod().getIndicatorId();
    String ovcExitedWithoutGraduationId=ind.getIndicatorForNumberOfOvcExitedWithoutGraduation().getIndicatorId();
    
    String cgActiveAndServeId=ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriod().getIndicatorId();
    String cgServedAndTransferedId=ind.getIndicatorForNumberOfCaregiversServedAndTransferedWithinTheReportPeriod().getIndicatorId();
    String cgServedAndGraduatedId=ind.getIndicatorForNumberOfCaregiversServedAndGraduatedWithinTheReportPeriod().getIndicatorId();
    String cgExitedWithoutGraduationId=ind.getIndicatorForNumberOfCaregiversExitedWithoutGraduation().getIndicatorId();
    String userName="auto";
    String startOfFinancialYear=null;
    String partnerCode=null;
    ReportParameterTemplate rpt=null;
    ReportParameterTemplate rptForExited=null;
    String indicatorId=null;
    //CustomIndicatorsReport denominatorCirt=null;
    public CustomIndicatorsReportManagerThread(ReportParameterTemplate rpt,String indicatorId,int startMth, int startYear,int endMth,int endYear,String currentUser)
    {
        DateManager dm=new DateManager();
        startOfFinancialYear=rpt.getFinancialYear().getStartYear()+"-"+rpt.getFinancialYear().getStartMonth()+"-01";
        this.rpt=rpt;
        rptForExited=rpt;
        fy=ReportPeriodManager.getStartOfFinancialYear(rpt.getStartMth(), rpt.getStartYear());
        rptForExited.setStartMth(fy.getStartMonth());
        rptForExited.setStartYear(fy.getStartYear());
        //parameterList=paramList;
        if(currentUser !=null)
        userName=currentUser;
        partnerCode=rpt.getPartnerCode();
        mainReportPeriod=dm.getMonthAsString(startMth)+" "+startYear+"-"+dm.getMonthAsString(endMth)+" "+endYear;
        this.indicatorId=indicatorId;
    }
    public void run()
    {
        
        //DateManager dm=new DateManager();
        //startOfFinancialYear=rpt.getFinancialYear().getStartYear()+"-"+rpt.getFinancialYear().getStartMonth()+"-01";
        //String initialReportPeriod=dm.getMonthAsString(rpt.getStartMth())+" "+rpt.getStartYear()+"-"+dm.getMonthAsString(rpt.getEndMth())+" "+rpt.getEndYear();
        //String initialReportPeriod2=dm.getMonthAsString(startMth)+" "+startYear+"-"+dm.getMonthAsString(endMth)+" "+endYear;
        //System.err.println("initialReportPeriod is "+initialReportPeriod);
        //System.err.println("initialReportPeriod2 is "+initialReportPeriod2);
        /*if(selectedIndicators==null || selectedIndicators.isEmpty())
        selectedIndicators=CustomIndicatorsReportManagerThread.getCustomIndicators();
        fy=ReportPeriodManager.getStartOfFinancialYear(rpt.getStartMth(), rpt.getStartYear());
        rpt.setStartAge(1);
        rpt.setEndAge(17);
        //Create a report template for exited beneficiaries. Their start period is from the begining of the FY
        ReportParameterTemplate rptForExited=rpt;
        rptForExited.setStartMth(fy.getStartMonth());
        rptForExited.setStartYear(fy.getStartYear());*/
        //parameterList=paramList;
        //if(currentUser !=null)
        //userName=currentUser;
        
        //String partnerCode=rpt.getPartnerCode();
        //String[] params=getQueryParam(paramList);
        //String[] queryParam={stateCode,lgaCode,cboCode,wardCode,startMonth,startYear,endMonth,endYear,null,null,null,null,null,null,partnerName,partnerCode,null,null,partnerCode};
        //String[] reportParams={rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),rpt.getLevel4OuId(),"All","All","All",rpt.getStartMth()+"",rpt.getStartYear()+"",rpt.getEndMth()+"",rpt.getEndYear()+"","All","All","All","All","0","17","All",rpt.getPartnerCode(),rpt.getLevel4OuId()};
        //String[] reportParamsForExited={rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),rpt.getLevel4OuId(),"All","All","All",fy.getStartMonth()+"",fy.getStartYear()+"",rpt.getEndMth()+"",rpt.getEndYear()+"","All","All","All","All","0","17","All",rpt.getPartnerCode(),rpt.getLevel4OuId()};
        //mainReportPeriod=dm.getMonthAsString(startMth)+" "+startYear+"-"+dm.getMonthAsString(endMth)+" "+endYear;
        //dm.getMonthAsString(rpt.getStartMth())+" "+rpt.getStartYear()+"-"+dm.getMonthAsString(rpt.getEndMth())+" "+rpt.getEndYear();
        
        IndicatorDictionary ind=new IndicatorDictionary();
        //Indicators ind=null;
        
        //for(int i=0; i<selectedIndicators.size(); i++)
        //{
            //indicatorId=selectedIndicators.get(i).toString();
            if(indicatorId.equalsIgnoreCase(ind.getOvc_NEWIndicator().getIndicatorId()))
            {//OVC_ENROLLED
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCNewEnrolledAndServed(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_SERVIndicator().getIndicatorId()))
            {//OVC_SERV
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCCurrentlyEnrolledAndServed(rpt,partnerCode,AppConstant.ALL_SERVICE_DOMAIN,null); 
                processsOVCActiveAndGraduatedEnrolledAndServedByEnrollmentStream(rpt,partnerCode,AppConstant.ALL_SERVICE_DOMAIN,null);
                processsOVCGraduatedAndServed(rptForExited,partnerCode,AppConstant.ALL_SERVICE_DOMAIN,null);
                processsOVCTransferedAndServed(rptForExited,partnerCode,AppConstant.ALL_SERVICE_DOMAIN,null);
                processsOVCExitedWithoutGraduationAndServed(rptForExited,partnerCode,AppConstant.ALL_SERVICE_DOMAIN,null);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_HIVSTATIndicator().getIndicatorId()))
            {//OVC_HIVSTAT
                AppUtility.customIndicatorsThreadCounter++;
                CustomIndicatorsReport pcir=processsOVC_HIVSTATPositive(rpt,partnerCode);
                CustomIndicatorsReport ncir=processsOVC_HIVSTATNegative(rpt,partnerCode);
                CustomIndicatorsReport ucir=processsOVC_HIVSTATUnknown(rpt,partnerCode);
                processsOVC_HIVTestNotRequired(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
                /*CustomIndicatorsReport numeratorCirt=new CustomIndicatorsReport();
                
                //set values for numerator report object by adding active and graduated for each age/sex category
                numeratorCirt.setMaleLessThan1(pcir.getMaleLessThan1()+ncir.getMaleLessThan1());
                numeratorCirt.setMale1to4(pcir.getMale1to4()+ncir.getMale1to4());
                numeratorCirt.setMale5to9(pcir.getMale5to9()+ncir.getMale5to9());
                numeratorCirt.setMale10to14(pcir.getMale10to14()+ncir.getMale10to14());
                numeratorCirt.setMale15to17(pcir.getMale15to17()+ncir.getMale15to17());
                numeratorCirt.setMaleTotal(pcir.getMaleTotal()+ncir.getMaleTotal());
                numeratorCirt.setFemaleLessThan1(pcir.getFemaleLessThan1()+ncir.getFemaleLessThan1());
                numeratorCirt.setFemale1to4(pcir.getFemale1to4()+ncir.getFemale1to4());
                numeratorCirt.setFemale5to9(pcir.getFemale5to9()+ncir.getFemale5to9());
                numeratorCirt.setFemale10to14(pcir.getFemale10to14()+ncir.getFemale10to14());
                numeratorCirt.setFemale15to17(pcir.getFemale15to17()+ncir.getFemale15to17());
                numeratorCirt.setFemaleTotal(pcir.getFemaleTotal()+ncir.getFemaleTotal());*/
                //get active and graduated OVC served to use as denominator
                //CustomIndicatorsReport activeCir=processsOVCCurrentlyEnrolledAndServed(rpt,partnerCode);
                //CustomIndicatorsReport graduatedCir=processsOVCGraduatedAndServed(rpt,partnerCode);
                
                //denominator report object to hold data for both active and graduated
                //CustomIndicatorsReport denominatorCir=processsOVCCurrentlyEnrolledActiveAndGraduatedServed(rpt,partnerCode,AppConstant.ALL_SERVICE_DOMAIN,null);//new CustomIndicatorsReport();
                //set values for denominator report object by adding active and graduated for each age/sex category
                                
                //String indicatorCode=ind.getIndicatorForProportionOfOvcWithKnownHIVStatusMER().getIndicatorId();
                //CustomIndicatorsReport proportionCirt=getProportion(rpt,indicatorCode,partnerCode,denominatorCir,numeratorCirt);
                //saveReportTemplate(proportionCirt);
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_TXLINKIndicator().getIndicatorId()))
            {//OVC_TXLINK
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCAssessedForHivRiskReferredForHivTestAndTestedPositive(rpt,partnerCode);
                processsOVCAssessedForHivRiskReferredForHivTestTestedPositiveAndEnrolledOnTreatment(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
                //processsEnrolledOnTreatment(reportParams,partnerCode);
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_ARTSUPPIndicator().getIndicatorId()))
            {//OVC_ARTSUPP
                //processsOVCAssessedForHivRiskReferredForHivTestAndTestedPositive(rpt,partnerCode);
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCAdherenceToTreatment(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_HIVRISKASSESSEDIndicator().getIndicatorId()))
            {//OVC_HIVRISKASS
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCAssessedForHIVRisk(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
                //processsOVCWithUnknownHivAssessedForHIVRisk(rpt,partnerCode);
                //processsOVCWithNegativeHivAssessedForHIVRisk(rpt,partnerCode);
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_BIRTHCERTIndicator().getIndicatorId()))
            {//OVC_BIRTHCERT getBirthRegistrationAcquisitionService()
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCWithBirthCertificateServed(rpt,partnerCode,AppConstant.ALL_SERVICE_DOMAIN,OvcServiceAttributesManager.getBirthRegistrationAcquisitionService().getServiceCode());
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_PROTECTIndicator().getIndicatorId()))
            {//OVC_PROTECT
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCAbusedAndExploited(rpt,partnerCode);
                processsOVCPostViolenceLinkedToGovt(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_HTSLINKIndicator().getIndicatorId()))
            {//OVC_HTSLINK
                //processsHivUnknownOrNegativeOVCServedInReportPeriod(rpt,partnerCode);
                //processsOVCReferredForTestingOnly(reportParams,partnerCode);
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCReferredForTestingAndTestedAndObtainedResult(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_NUTRITIONIndicator().getIndicatorId()))
            {//OVC_NUTRITION
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCSeverelyMalnourished(rpt,partnerCode);
                processsOVCSeverelyMalnourishedAndServedNutrition(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_EDUIndicator().getIndicatorId()))
            {//OVC_EDU
                AppUtility.customIndicatorsThreadCounter++;
                processsOVCCurrentlyInSchool(rpt,partnerCode);
                processsOVCRegularlyAttendingSchool(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_HIVPREVIndicator().getIndicatorId()))
            {//OVC_HIVPREV
                AppUtility.customIndicatorsThreadCounter++;
                processsNumberOfAdolescentsProvidedHIVPreventionServices(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_HHGRADIndicator().getIndicatorId()))
            {//OVC_HHGRAD
                AppUtility.customIndicatorsThreadCounter++;
                //processsNumberOfActiveHouseholdsWhoseOvcWereServed(rpt,partnerCode);
                processsNumberOfGraduatedHouseholdsWhoseBeneficiariesWereServed(rptForExited,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_ECONSIndicator().getIndicatorId()))
            {//OVC_ECONS
                AppUtility.customIndicatorsThreadCounter++;
                processsNumberOfHouseholdsThatReceivedEmergencyNeeds(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_VL_ELIGIBLEIndicator().getIndicatorId()))
            {//OVC_VL_ELIGIBLE
                AppUtility.customIndicatorsThreadCounter++;
                processViralLoadEligibilityIndicators(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_VLRIndicator().getIndicatorId()))
            {//OVC_VLR
                AppUtility.customIndicatorsThreadCounter++;
                processOVC_VLRIndicators(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_VLSIndicator().getIndicatorId()))
            {//OVC_VLS
                AppUtility.customIndicatorsThreadCounter++;
                processOVC_VLSIndicators(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_ART_ENROLLIndicator().getIndicatorId()))
            {//OVC_ART_ENROLL
                AppUtility.customIndicatorsThreadCounter++;
                processgetOVC_ART_ENROLLIndicators(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
                
            }
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_ART_OFFERIndicator().getIndicatorId()))
            {//OVC_ART_ENROLL
                AppUtility.customIndicatorsThreadCounter++;
                processgetOVC_ART_OFFERIndicators(rpt,partnerCode);
                --AppUtility.customIndicatorsThreadCounter;
                
            }
           try
           {
               this.finalize();
           }
           catch(Throwable tw)
           {
               tw.printStackTrace();
           }
        //}
        /*processsCaregiversCurrentlyEnrolledAndServed(reportParams,partnerCode);
        processsCaregiversGraduatedAndServed(reportParams,partnerCode);
        processsCaregiversTransferedAndServed(reportParams,partnerCode);
        processsCaregiversExitedWithoutGraduationAndServed(reportParams,partnerCode);*/
    }
    public CustomIndicatorsReport processsOVCCurrentlyEnrolledAndServed(ReportParameterTemplate rpt,String partnerCode,int serviceDomain,String serviceType)
    {
        String indicatorCode=ind.getIndicatorForNumberOfActiveBeneficiariesServedMER().getIndicatorId();
        //getIndicatorForNumberOfOvcCurrentlyEnrolledAndServedInReportPeriod().getIndicatorId();
        CustomIndicatorsReport cir=getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,serviceDomain,serviceType,null,false);   
        return cir;
    }
    public CustomIndicatorsReport processsOVCCurrentlyEnrolledActiveAndGraduatedServed(ReportParameterTemplate rpt,String partnerCode,int serviceDomain,String serviceType)
    {
        CustomIndicatorsReport activeCir=processsOVCCurrentlyEnrolledAndServed(rpt,partnerCode,serviceDomain,serviceType);
        CustomIndicatorsReport graduatedCir=processsOVCGraduatedAndServed(rpt,partnerCode,serviceDomain,serviceType);
        
        ///denominator report object to hold data for both active and graduated
        CustomIndicatorsReport totalCir=new CustomIndicatorsReport();
        //set values for denominator report object by adding active and graduated for each age/sex category
        totalCir.setMaleLessThan1(activeCir.getMaleLessThan1()+graduatedCir.getMaleLessThan1());
        totalCir.setMale1to4(activeCir.getMale1to4()+graduatedCir.getMale1to4());
        totalCir.setMale5to9(activeCir.getMale5to9()+graduatedCir.getMale5to9());
        totalCir.setMale10to14(activeCir.getMale10to14()+graduatedCir.getMale10to14());
        totalCir.setMale15to17(activeCir.getMale15to17()+graduatedCir.getMale15to17());
        totalCir.setMale18to24(activeCir.getMale18to24()+graduatedCir.getMale18to24());
        totalCir.setMale25Plus(activeCir.getMale25Plus()+graduatedCir.getMale25Plus());
        totalCir.setMaleTotal(activeCir.getMaleTotal()+graduatedCir.getMaleTotal());
        
        totalCir.setFemaleLessThan1(activeCir.getFemaleLessThan1()+graduatedCir.getFemaleLessThan1());
        totalCir.setFemale1to4(activeCir.getFemale1to4()+graduatedCir.getFemale1to4());
        totalCir.setFemale5to9(activeCir.getFemale5to9()+graduatedCir.getFemale5to9());
        totalCir.setFemale10to14(activeCir.getFemale10to14()+graduatedCir.getFemale10to14());
        totalCir.setFemale15to17(activeCir.getFemale15to17()+graduatedCir.getFemale15to17());
        totalCir.setFemale18to24(activeCir.getFemale18to24()+graduatedCir.getFemale18to24());
        totalCir.setFemale25Plus(activeCir.getFemale25Plus()+graduatedCir.getFemale25Plus());
        totalCir.setFemaleTotal(activeCir.getFemaleTotal()+graduatedCir.getFemaleTotal());
        
        totalCir.setGrandTotal(activeCir.getGrandTotal()+graduatedCir.getGrandTotal());
        
        return totalCir;
    }
    public void processsOVCActiveAndGraduatedEnrolledAndServedByEnrollmentStream(ReportParameterTemplate rpt,String partnerCode,int serviceDomain,String serviceType)
    {
        String indicatorCode=ind.getIndicatorForNumberOfChildrenLivingWithHivServedMer().getIndicatorId();
        getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,serviceDomain,serviceType,ind.getIndicatorForNumberOfChildrenLivingWithHivServedMer().getAutoGeneratedId(),false);
        indicatorCode=ind.getIndicatorForNumberOfChildrenLivingWithPLHIVServedMer().getIndicatorId();
        getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,serviceDomain,serviceType,ind.getIndicatorForNumberOfChildrenLivingWithPLHIVServedMer().getAutoGeneratedId(),false);
        indicatorCode=ind.getIndicatorForNumberOfChildrenOfKeyPopServedMer().getIndicatorId();
        getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,serviceDomain,serviceType,ind.getIndicatorForNumberOfChildrenOfKeyPopServedMer().getAutoGeneratedId(),false);
        indicatorCode=ind.getIndicatorForNumberOfHIVExposedInfantsServedMer().getIndicatorId();
        getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,serviceDomain,serviceType,ind.getIndicatorForNumberOfHIVExposedInfantsServedMer().getAutoGeneratedId(),false);
        indicatorCode=ind.getIndicatorForNumberOfSurvivorsOfViolenceAgainstChildrenServedMer().getIndicatorId();
        getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,serviceDomain,serviceType,ind.getIndicatorForNumberOfSurvivorsOfViolenceAgainstChildrenServedMer().getAutoGeneratedId(),false);
        indicatorCode=ind.getIndicatorForNumberOfAdolescentsAtHighRiskOfHIVServedMER().getIndicatorId();
        getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,serviceDomain,serviceType,ind.getIndicatorForNumberOfAdolescentsAtHighRiskOfHIVServedMER().getAutoGeneratedId(),false);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public CustomIndicatorsReport processsOVCGraduatedAndServed(ReportParameterTemplate rpt,String partnerCode,int serviceDomain,String serviceType)
    {
        //getIndicatorForNumberOfGraduatedBeneficiariesServedMER()
        String indicatorCode=ind.getIndicatorForNumberOfOvcGraduatedButServedInReportPeriod().getIndicatorId();
        CustomIndicatorsReport cir=getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.GRADUATED_NUM,serviceDomain,serviceType,null,false);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
        return cir;
    }
    public CustomIndicatorsReport processsOVCTransferedAndServed(ReportParameterTemplate rpt,String partnerCode,int serviceDomain,String serviceType)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOvcTransferedOutButServedInReportPeriod().getIndicatorId();
        CustomIndicatorsReport cir=getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.TRANSFERED_NUM,serviceDomain,serviceType,null,false);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
        return cir;
    }
    public CustomIndicatorsReport processsOVCExitedWithoutGraduationAndServed(ReportParameterTemplate rpt,String partnerCode,int serviceDomain,String serviceType)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOvcExitedWithoutGraduation().getIndicatorId();
        CustomIndicatorsReport cir=getDatimEquivalentData(rpt,indicatorCode,partnerCode,AppConstant.EXITED_WITHOUT_GRADUATION_NUM,serviceDomain,serviceType,null,false);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
        return cir;
    }
    public void processsOVCNewEnrolledAndServed(ReportParameterTemplate rpt,String partnerCode)
    {
        int enrollmentSetting=0;
        String indicatorCode=ind.getIndicatorForNumberOfNewEnrolledBeneficiaries().getIndicatorId();
        getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,null,true,enrollmentSetting,0,0);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithHiv().getIndicatorId();
        getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithHiv().getAutoGeneratedId(),true,enrollmentSetting,0,0);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithPLHIV().getIndicatorId();
        getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithPLHIV().getAutoGeneratedId(),true,enrollmentSetting,0,0);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledChildrenOfKeyPop().getIndicatorId();
        getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,ind.getIndicatorForNumberOfNewEnrolledChildrenOfKeyPop().getAutoGeneratedId(),true,enrollmentSetting,0,0);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledHIVExposedInfants().getIndicatorId();
        getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,ind.getIndicatorForNumberOfNewEnrolledHIVExposedInfants().getAutoGeneratedId(),true,enrollmentSetting,0,0);
        //The two indicators below are not part of NOMIS but created for custom indicators reporting. 
        //They come two or more NOMIS enrollment streams, hence uses the auto generated Ids of those NOMIS indicators to access the data
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledSurvivorsOfViolenceAgainstChildren().getIndicatorId();
        getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,ind.getIndicatorForNumberOfNewEnrolledSurvivorsOfViolenceAgainstChildren().getAutoGeneratedId(),true,enrollmentSetting,0,0);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledAdolescentsAtHighRiskOfHIV().getIndicatorId();
        getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,ind.getIndicatorForNumberOfNewEnrolledAdolescentsAtHighRiskOfHIV().getAutoGeneratedId(),true,enrollmentSetting,0,0);
        /*String indicatorCode=ind.getIndicatorForNumberOfNewEnrolledBeneficiaries().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
        rpt.setReportType(AppConstant.CUSTOMINDICATORS_REPORTTYPE);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithHiv().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithPLHIV().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledChildrenOfKeyPop().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledHIVExposedInfants().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
        //The two indicators below are not part of NOMIS but created for custom indicators reporting. 
        //They come two or more NOMIS enrollment streams, hence uses the auto generated Ids of those NOMIS indicators to access the data
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledSurvivorsOfViolenceAgainstChildren().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfNewEnrolledAdolescentsAtHighRiskOfHIV().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);*/
        
    }
    
    public CustomIndicatorsReport processsOVC_HIVSTATPositive(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOVC_HIVSTATPOSITIVE().getIndicatorId();
        CustomIndicatorsReport cir=processDataByCBO(rpt,indicatorCode,partnerCode);
        return cir;
    }
    public CustomIndicatorsReport processsOVC_HIVSTATNegative(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOVC_HIVSTATNEGATIVE().getIndicatorId();
        CustomIndicatorsReport cir=processDataByCBO(rpt,indicatorCode,partnerCode);
        return cir;
    }
    public CustomIndicatorsReport processsOVC_HIVSTATUnknown(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOVC_HIVSTATUNKNOWN().getIndicatorId();
        CustomIndicatorsReport cir=processDataByCBO(rpt,indicatorCode,partnerCode);
        return cir;
    }
    public CustomIndicatorsReport processsOVC_HIVTestNotRequired(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOVC_HIVTestNotIndicated().getIndicatorId();
        CustomIndicatorsReport cir=processDataByCBO(rpt,indicatorCode,partnerCode);
        return cir;
    }
    /*public void processsEnrolledOnTreatment(ReportParameterTemplate rpt,String partnerCode)
    {
        //getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolledOnART()
        String indicatorCode=ind.getIndicatorForNumberOfOvcAssessedForHivRiskReferredForHivTestTestedPositiveAndEnrolledOnARTWithinTheReportPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_HTSLINK(rpt,indicatorCode,partnerCode,AppConstant.CHILD_AT_RISK_NUM,AppConstant.REFERRALCOMPLETED_YES_NUM,AppConstant.HIV_POSITIVE_NUM,AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
        processDataByCBO(rpt,indicatorCode,partnerCode);
    }*/
    public void processsOVCAssessedForHivRiskReferredForHivTestAndTestedPositive(ReportParameterTemplate rpt,String partnerCode)
    {
        //String indicatorCode=ind.getIndicatorForNumberOfOvcNewlyTestedPositiveWithinTheReportPeriod().getIndicatorId();
        String indicatorCode=ind.getIndicatorForNumberOfOvcAssessedForHivRiskReferredForHivTestAndTestedPositiveWithinTheReportPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_HTSLINK(rpt,indicatorCode,partnerCode,AppConstant.CHILD_AT_RISK_NUM,AppConstant.REFERRALCOMPLETED_YES_NUM,AppConstant.HIV_POSITIVE_NUM,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    
    public void processsOVCAssessedForHivRiskReferredForHivTestTestedPositiveAndEnrolledOnTreatment(ReportParameterTemplate rpt,String partnerCode)
    {
        //String indicatorCode=ind.getIndicatorForNumberOfHivPositiveOvcEnrolledOnARTWithinTheReportPeriod().getIndicatorId();
        String indicatorCode=ind.getIndicatorForNumberOfOvcNewlyTestedPositiveAndLinkedToTreatment().getIndicatorId();
        getDatimEquivalentDataForOVC_HTSLINK(rpt,indicatorCode,partnerCode,AppConstant.CHILD_AT_RISK_NUM,AppConstant.REFERRALCOMPLETED_YES_NUM,AppConstant.HIV_POSITIVE_NUM,AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsOVCAdherenceToTreatment(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOvcSelfReportingAdherenceToTreatment().getIndicatorId();
        getDatimEquivalentDataForViralLoadCascade(rpt,indicatorCode,partnerCode,false,false,false);
        getDatimEquivalentDataForOVC_ARTSUPP(rpt,indicatorCode,partnerCode);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsOVCAssessedForHIVRisk(ReportParameterTemplate rpt,String partnerCode)
    {
        //getIndicatorForNumberOfHIVUnknownAndHivNegativeOvc()
        String indicatorCode=ind.getIndicatorForNumberOfOvcEligibleForHIVRiskAssessment().getIndicatorId();
        getDatimEquivalentDataForOVC_RISKASSESSED(rpt,indicatorCode, partnerCode,1,0,0);
        //CustomIndicatorsReport denominatorCirt=processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getNoOfOvcAssessedForHIVRiskWithinReportPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_RISKASSESSED(rpt,indicatorCode, partnerCode,0,1,0);
        //CustomIndicatorsReport numeratorCirt=processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfOvcAssessedForHivRiskAndDeterminedToBeAtRisk().getIndicatorId();
        getDatimEquivalentDataForOVC_RISKASSESSED(rpt,indicatorCode, partnerCode,0,1,1);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTest().getIndicatorId();
        getDatimEquivalentDataForOVC_HTSLINK(rpt,indicatorCode,partnerCode,AppConstant.CHILD_AT_RISK_NUM,AppConstant.REFERRALCOMPLETED_NOT_APPLICABLE_NUM,AppConstant.HIV_ALL_STATUS_NUM,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfOvcWhoHadAChangeInRiskProfileSinceLastHivRisk().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfOvcDocumetedAsReferredForHivTestOnRiskAssessmentForm().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
        
        //indicatorCode=ind.getIndicatorForProportionOfOvcAssessedForHIVRisk().getIndicatorId();
        //CustomIndicatorsReport proportionCirt=getProportion(rpt,indicatorCode,partnerCode,denominatorCirt,numeratorCirt);
        //saveReportTemplate(proportionCirt);
    }
    public void processsOVCWithUnknownHivAssessedForHIVRisk(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getNoOfHivUnknownOvcAssessedForHIVRiskAndServedWithinReportPeriod().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsOVCWithNegativeHivAssessedForHIVRisk(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getNoOfHivNegativeOvcAssessedForHIVRiskAndServedWithinReportPeriod().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsOVCWithBirthCertificateServed(ReportParameterTemplate rpt,String partnerCode,int serviceDomain,String serviceType)
    {
        //getOvc_BIRTHCERTIndicator()
        String indicatorCode=ind.getNoOfOvcServedWithinTheReportPeriodThatHasBirthCert().getIndicatorId();
        CustomIndicatorsReport numeratorCir=getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,null,false,0,AppConstant.CHILD_HAS_BIRTHCERTIFICATE,0);
                //processDataByCBO(rpt,indicatorCode,partnerCode);
        //CustomIndicatorsReport denominatorCir=processsOVCCurrentlyEnrolledActiveAndGraduatedServed(rpt,partnerCode,serviceDomain,serviceType);
        
        //indicatorCode=ind.getIndicatorForProportionOfOvcServedWithinTheReportPeriodThatHasBirthCert().getIndicatorId();
        //CustomIndicatorsReport proportionCirt=getProportion(rpt,indicatorCode,partnerCode,denominatorCir,numeratorCir);
        //saveReportTemplate(proportionCirt);
    }
    //
    public void processsOVCAbusedAndExploited(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOvcAbusedWithinReportPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_PROTECT(rpt,indicatorCode,partnerCode,AppConstant.SAFETY_DOMAIN,OvcServiceAttributesManager.getPostViolenceTraumaSupportService().getServiceCode(),null,false);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsOVCPostViolenceLinkedToGovt(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOvcLinkedToGovtForPostViolenceServicesWithinReportPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_PROTECT(rpt,indicatorCode,partnerCode,AppConstant.SAFETY_DOMAIN,OvcServiceAttributesManager.getChilfAbuseCaseReport().getServiceCode(),null,false);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    
    public void processsOVCReferredForTestingAndTestedAndObtainedResult(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTest().getIndicatorId();
        getDatimEquivalentDataForOVC_HTSLINK(rpt,indicatorCode,partnerCode,AppConstant.CHILD_AT_RISK_NUM,AppConstant.REFERRALCOMPLETED_NOT_APPLICABLE_NUM,AppConstant.HIV_ALL_STATUS_NUM,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
        //getIndicatorForNumberOfOvcProvidedReferralForHIVRelatedTestingService()
        indicatorCode=ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTestAndCompletedReferral().getIndicatorId();
        getDatimEquivalentDataForOVC_HTSLINK(rpt,indicatorCode,partnerCode,AppConstant.CHILD_AT_RISK_NUM,AppConstant.REFERRALCOMPLETED_YES_NUM,AppConstant.HIV_ALL_STATUS_NUM,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTestAndDidNotReceiveTest().getIndicatorId();
        getDatimEquivalentDataForOVC_HTSLINK(rpt,indicatorCode,partnerCode,AppConstant.CHILD_AT_RISK_NUM,AppConstant.REFERRALCOMPLETED_NO_NUM,AppConstant.HIV_ALL_STATUS_NUM,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
    }
    
    public void processsOVCSeverelyMalnourished(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfSeverelyMalnourishedOvcCurrently().getIndicatorId();
        getDatimEquivalentDataForOVC_NUTRITION(rpt,indicatorCode, partnerCode,null);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsOVCSeverelyMalnourishedAndServedNutrition(ReportParameterTemplate rpt,String partnerCode)
    {//OvcServiceAttributesManager.getSevereAcuteMalnutrition().getServiceCode();
        String indicatorCode=ind.getIndicatorForNumberOfSeverelyMalnourishedOvcServedNutritonalServices().getIndicatorId();
        getDatimEquivalentDataForOVC_NUTRITION(rpt,indicatorCode, partnerCode,OvcServiceAttributesManager.getFoodPackagesOrNutritionSupplements().getServiceCode());
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsOVCRegularlyAttendingSchool(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOvcRegularlyAttendingSchool().getIndicatorId();
        getDatimEquivalentDataForOVC_EDU(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,0,AppConstant.CHILD_IN_SCHOOL,AppConstant.REGULAR_SCHOOL_ATTENDANCE_YES_NUM);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsOVCCurrentlyInSchool(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfOvcCurrentlyInSchool().getIndicatorId();
        getDatimEquivalentDataForOVC_EDU(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,0,AppConstant.CHILD_IN_SCHOOL,AppConstant.REGULAR_SCHOOL_ATTENDANCE_NOTAPPLICABLE_NUM);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processsNumberOfAdolescentsProvidedHIVPreventionServices(ReportParameterTemplate rpt,String partnerCode)
    {//getAdolescentHivPreventionServices()
        String indicatorCode=ind.getIndicatorForNumberOfAdolescentsProvidedHIVPreventionServices().getIndicatorId();
        getDatimEquivalentDataHIVPrevention(rpt,indicatorCode,partnerCode,AppConstant.HEALTH_DOMAIN,OvcServiceAttributesManager.getAdolescentHivPreventionServices().getServiceCode(),null,false);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    
    //Household indicators starts here
    /*public void processsNumberOfActiveHouseholdsWhoseOvcWereServed(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorNumberOfHouseholdsWhoseOvcWereServedWithinReportingPeriod().getIndicatorId();
        processDataByCBO(rpt,indicatorCode,partnerCode);
    }*/
    public void processsNumberOfGraduatedHouseholdsWhoseBeneficiariesWereServed(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfGraduatedHouseholdsWhoseBeneficiariesWereServedWithinReportingPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_HHGRAD(rpt,indicatorCode,partnerCode,false); 
        indicatorCode=ind.getIndicatorNumberOfHouseholdsWhoseBeneficiariesWereServedWithinReportingPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_HHGRAD(rpt,indicatorCode,partnerCode,true);
    }
    public void processsNumberOfHouseholdsThatReceivedEmergencyNeeds(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfHouseholdsServedAndAssessedOnAbilityToMeetEmergencyNeedsWithinReportingPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_ECONS(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,0,0);
        indicatorCode=ind.getIndicatorForNumberOfHouseholdsThatCanSolveEmergencyNeedsWithinReportingPeriod().getIndicatorId();
        getDatimEquivalentDataForOVC_ECONS(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,1,1);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
    }
    public void processViralLoadEligibilityIndicators(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfActiveAndGraduatedHivPositiveBeneficiariesEnrolledOnTreatment().getIndicatorId();
        getDatimEquivalentDataForViralLoadCascade(rpt,indicatorCode,partnerCode,false,false,false);
        //CustomIndicatorsReport denominatorCirt=processDataByCBO(rpt,indicatorCode,partnerCode);
        indicatorCode=ind.getIndicatorForNumberOfBeneficiariesEligibleForViralLoad().getIndicatorId();
        getDatimEquivalentDataForViralLoadCascade(rpt,indicatorCode,partnerCode,true,false,false);
        //CustomIndicatorsReport numeratorCirt=processDataByCBO(rpt,indicatorCode,partnerCode);
        //indicatorCode=ind.getIndicatorForProportionOfBeneficiariesEligibleForViralLoad().getIndicatorId();
        //CustomIndicatorsReport proportionCirt=getProportion(rpt,indicatorCode,partnerCode,denominatorCirt,numeratorCirt);
        //saveReportTemplate(proportionCirt);
    }
    public void processOVC_VLRIndicators(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfActiveAndGraduatedHivPositiveBeneficiariesEnrolledOnTreatment().getIndicatorId();
        //getDatimEquivalentDataForViralLoadCascade(rpt,indicatorCode,partnerCode,false,false,false);
        //CustomIndicatorsReport denominatorCirt=processDataByCBO(rpt,indicatorCode,partnerCode);
        
        indicatorCode=ind.getIndicatorForNumberOfBeneficiariesWithViralLoadResultInPast12Months().getIndicatorId();
        getDatimEquivalentDataForViralLoadCascade(rpt,indicatorCode,partnerCode,true,true,false);
        //CustomIndicatorsReport numeratorCirt=processDataByCBO(rpt,indicatorCode,partnerCode);
        
        //indicatorCode=ind.getIndicatorForProportionOfBeneficiariesWithViralLoadResultInPast12Months().getIndicatorId();
        //CustomIndicatorsReport proportionCirt=getProportion(rpt,indicatorCode,partnerCode,denominatorCirt,numeratorCirt);
        //saveReportTemplate(proportionCirt);
    }
    public void processOVC_VLSIndicators(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForNumberOfBeneficiariesWithViralLoadResultInPast12Months().getIndicatorId();
        getDatimEquivalentDataForViralLoadCascade(rpt,indicatorCode,partnerCode,true,true,false);
        //CustomIndicatorsReport denominatorCirt=processDataByCBO(rpt,indicatorCode,partnerCode);
        
        indicatorCode=ind.getIndicatorForNumberOfBeneficiariesWhoAreVirallySuppresedInPast12Months().getIndicatorId();
        getDatimEquivalentDataForViralLoadCascade(rpt,indicatorCode,partnerCode,true,true,true);
        //CustomIndicatorsReport numeratorCirt=processDataByCBO(rpt,indicatorCode,partnerCode);
                      
        //indicatorCode=ind.getIndicatorForProportionOfBeneficiariesWhoAreVirallySuppresedInPast12Months().getIndicatorId();
        //CustomIndicatorsReport proportionCirt=getProportion(rpt,indicatorCode,partnerCode,denominatorCirt,numeratorCirt);
       // saveReportTemplate(proportionCirt);
    }
     public void processgetOVC_ART_OFFERIndicators(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForOVC_ART_OFFER().getIndicatorId();
        CustomIndicatorsReport denominatorCirt=getReportForOVC_OFFER(rpt,indicatorCode,partnerCode);
        //processDataByCBO(rpt,indicatorCode,partnerCode);         
    }
    public void processgetOVC_ART_ENROLLIndicators(ReportParameterTemplate rpt,String partnerCode)
    {
        String indicatorCode=ind.getIndicatorForOVC_ART_ENROLL().getIndicatorId();
        CustomIndicatorsReport denominatorCirt=getDatimEquivalentDataForActiveForExactReportPeriod(rpt,indicatorCode,partnerCode,AppConstant.ACTIVE_NUM,AppConstant.ALL_SERVICE_DOMAIN,null,null,true,AppConstant.ENROLLMENTSETTING_FACILITY_NUM,0,0);
        //processDataByCBO(rpt,indicatorCode,partnerCode);
                
    }
    private CustomIndicatorsReport getProportion(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,CustomIndicatorsReport denominatorCirt,CustomIndicatorsReport numeratorCirt)
    {
        CustomIndicatorsReport proportion=new CustomIndicatorsReport();
        IndicatorWarehouse indw=new IndicatorWarehouse();
        Indicator ind=indw.getIndicatorById(indicatorCode);
        if(ind ==null)
        return new CustomIndicatorsReport();
        
        if(ind.getAlternateName()==null)
        ind.setAlternateName(ind.getIndicatorName());
        proportion.setMaleLessThan1(getProportion(numeratorCirt.getMaleLessThan1(), denominatorCirt.getMaleLessThan1())); 
        proportion.setMale1to4(getProportion(numeratorCirt.getMale1to4(), denominatorCirt.getMale1to4())); 
        proportion.setMale5to9(getProportion(numeratorCirt.getMale5to9(), denominatorCirt.getMale5to9())); 
        proportion.setMale10to14(getProportion(numeratorCirt.getMale10to14(), denominatorCirt.getMale10to14())); 
        proportion.setMale15to17(getProportion(numeratorCirt.getMale15to17(), denominatorCirt.getMale15to17())); 
        proportion.setMale18to24(getProportion(numeratorCirt.getMale18to24(), denominatorCirt.getMale18to24())); 
        proportion.setMale25Plus(getProportion(numeratorCirt.getMale25Plus(), denominatorCirt.getMale25Plus())); 
        proportion.setMaleTotal(getProportion(numeratorCirt.getMaleTotal(), denominatorCirt.getMaleTotal()));
        
        proportion.setFemaleLessThan1(getProportion(numeratorCirt.getFemaleLessThan1(), denominatorCirt.getFemaleLessThan1())); 
        proportion.setFemale1to4(getProportion(numeratorCirt.getFemale1to4(), denominatorCirt.getFemale1to4())); 
        proportion.setFemale5to9(getProportion(numeratorCirt.getFemale5to9(), denominatorCirt.getFemale5to9())); 
        proportion.setFemale10to14(getProportion(numeratorCirt.getFemale10to14(), denominatorCirt.getFemale10to14())); 
        proportion.setFemale15to17(getProportion(numeratorCirt.getFemale15to17(), denominatorCirt.getFemale15to17())); 
        proportion.setFemale18to24(getProportion(numeratorCirt.getFemale18to24(), denominatorCirt.getFemale18to24())); 
        proportion.setFemale25Plus(getProportion(numeratorCirt.getFemale25Plus(), denominatorCirt.getFemale25Plus())); 
        proportion.setFemaleTotal(getProportion(numeratorCirt.getFemaleTotal(), denominatorCirt.getFemaleTotal()));
        proportion.setGrandTotal(getProportion(numeratorCirt.getGrandTotal(), denominatorCirt.getGrandTotal()));
        
        proportion.setLevel2OuId(rpt.getLevel2OuId());
        proportion.setLevel3OuId(rpt.getLevel3OuId());
        proportion.setLevel4OuId("xxxxxxxxxxx");
        proportion.setCboId(rpt.getCboId());
        proportion.setIndicator(ind);
        proportion.setIndicatorId(indicatorCode);
        proportion.setIndicatorName(indicatorCode);
        proportion.setReportPeriod(mainReportPeriod);
        proportion.setPartnerCode(partnerCode);
        
        if(ind !=null)
        {
            proportion.setIndicatorName(ind.getAlternateName());
            proportion.setMerCode(ind.getMerCode());
            proportion.setOtherDisaggregation("other");
            proportion.setUserName(userName);
            proportion.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        }
        
        return proportion;
    }
    private int getProportion(int numerator, int denominator)
    {
        int proportion=0;
        if(numerator>0 && denominator>0)
        proportion=(numerator*100)/denominator;
        //System.err.println("numerator is "+numerator+" denominator is "+denominator+" proportion is "+proportion);
        return proportion;
    }
    public CustomIndicatorsReport getActiveCaregiverData(String startDate, String endDate)
    {
        DatimReportGenerator drg=new DatimReportGenerator();
        DatimReportTemplate activeCaregivers18AndAboveMaleRt=drg.getNoOfActiveCaregiversServed(rpt,startDate, endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"M");
        DatimReportTemplate activeCaregivers18AndAboveFemaleRt=drg.getNoOfActiveCaregiversServed(rpt,startDate, endDate,AppConstant.ADULT_STARTAGE_NUM,AppConstant.ADULT_ENDAGE_NUM,"F");
        System.err.println("activeCaregivers18AndAboveMaleRt.getOvc_servActive()1 is "+activeCaregivers18AndAboveMaleRt.getOvc_servActive());
        System.err.println("activeCaregivers18AndAboveFemaleRt.getOvc_servActive()1 is "+activeCaregivers18AndAboveFemaleRt.getOvc_servActive());
        
        DatimReportTemplate activeCaregivers18AndAboveMaleRt18_24=drg.getNoOfActiveCaregiversServed(rpt,startDate, endDate,18,24,"M");
        DatimReportTemplate activeCaregivers18AndAboveFemaleRt18_24=drg.getNoOfActiveCaregiversServed(rpt,startDate, endDate,18,24,"F");
        
        DatimReportTemplate activeCaregivers18AndAboveMaleRt25_200=drg.getNoOfActiveCaregiversServed(rpt,startDate, endDate,25,200,"M");
        DatimReportTemplate activeCaregivers18AndAboveFemaleRt25_200=drg.getNoOfActiveCaregiversServed(rpt,startDate, endDate,25,200,"F");
        
        System.err.println("activeCaregivers18AndAboveMaleRt18_24.getOvc_servActive()2 is "+activeCaregivers18AndAboveMaleRt18_24.getOvc_servActive());
        System.err.println("activeCaregivers18AndAboveFemaleRt18_24.getOvc_servActive()2 is "+activeCaregivers18AndAboveFemaleRt18_24.getOvc_servActive());
        
        System.err.println("activeCaregivers18AndAboveMaleRt25_200.getOvc_servActive()3 is "+activeCaregivers18AndAboveMaleRt25_200.getOvc_servActive());
        System.err.println("activeCaregivers18AndAboveFemaleRt25_200.getOvc_servActive()3 is "+activeCaregivers18AndAboveFemaleRt25_200.getOvc_servActive());
        
        System.err.println("activeCaregivers18AndAboveMaleRt18_24.getOvc_servActive()+activeCaregivers18AndAboveMaleRt25_200.getOvc_servActive()4 is "+activeCaregivers18AndAboveMaleRt18_24.getOvc_servActive()+activeCaregivers18AndAboveMaleRt25_200.getOvc_servActive());
        System.err.println("activeCaregivers18AndAboveFemaleRt18_24.getOvc_servActive()+activeCaregivers18AndAboveFemaleRt25_200.getOvc_servActive()4 is "+activeCaregivers18AndAboveFemaleRt18_24.getOvc_servActive()+activeCaregivers18AndAboveFemaleRt25_200.getOvc_servActive());
        return null;
    }
    private CustomIndicatorsReport getDatimEquivalentData(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,int enrollmentStatus,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly)
    {
        CustomIndicatorsReport cir=null;
        DatimReportGenerator drg=new DatimReportGenerator();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            
            int[] ageDisaggregationWith18To20={0,0,1,4,5,9,10,14,15,17,18,20,18,24,25,200};
            
            int[] ageDisaggregation=ageDisaggregationWith18To20;
            //int[] ageDisaggregation=ovcAndCaregiverAgeDisaggregation; //ovcAgeDisaggregation;
            if(indicatorCode.equalsIgnoreCase(beneficiariesNewlyEnrolled))
            ageDisaggregation=ovcAndCaregiverAgeDisaggregation;
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfAdolescentsProvidedHIVPreventionServices().getIndicatorId()))
            ageDisaggregation=hivPrevAgeDisaggregation;
            else if(enrollmentStreamCode != null)
            {
                ageDisaggregation=ovcAgeDisaggregation;
                
            }
            //System.err.println("enrollmentStreamCode in getDatimEquivalentData is "+enrollmentStreamCode);
            //List valueList=new ArrayList();
            String cboId="All";
            rpt.setCboId(cboId);
            int maleCount=0;
            int femaleCount=0;
            //int activeGraduatedMaleCount=0;
            //int activeGraduatedFemaleCount=0;
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                System.err.println("About to pull Datim data for "+ageDisaggregation[j]+" to "+ageDisaggregation[j+1]);
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18 || rpt.getEndAge()==20)
                {
                    if(rpt.getEndAge()==20)
                    rpt.setEndAge(24);
                    if(enrollmentStatus==AppConstant.ACTIVE_NUM)
                    {
                        //DatimReportTemplate drt=drg.getNoOfActiveCaregiversServed(rpt, rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                         maleCount=drg.getNoOfActiveOvcServed(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servActive();
                         femaleCount=drg.getNoOfActiveOvcServed(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servActive();
                       
                    }
                    else
                    {
                        if(enrollmentStatus==AppConstant.GRADUATED_NUM)
                        {
                            maleCount=drg.getNoOfGraduatedOvcServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servGraduated();
                            femaleCount=drg.getNoOfGraduatedOvcServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servGraduated();
                        }
                        else if(enrollmentStatus==AppConstant.ACTIVE_GRADUATED_NUM)
                        {
                            //DatimReportTemplate drt=drg.getNoOfActiveCaregiversServed(rpt, rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                             maleCount=drg.getNoOfActiveOvcServed(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servActive();
                             femaleCount=drg.getNoOfActiveOvcServed(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servActive();
                             
                             maleCount+=drg.getNoOfGraduatedOvcServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servGraduated();
                             femaleCount+=drg.getNoOfGraduatedOvcServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servGraduated();
                       
                        }
                        else if(enrollmentStatus==AppConstant.EXITED_WITHOUT_GRADUATION_NUM)
                        {
                            maleCount=drg.getNoOfOvcExitedWithoutGraduationServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male).getOvc_servExitedWithoutGraduation();
                            femaleCount=drg.getNoOfOvcExitedWithoutGraduationServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female).getOvc_servExitedWithoutGraduation();
                        }
                        else if(enrollmentStatus==AppConstant.TRANSFERED_NUM)
                        {
                            maleCount=drg.getNoOfOvcServedAndTransfered(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male).getOvc_servTransfered();
                            femaleCount=drg.getNoOfOvcServedAndTransfered(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female).getOvc_servTransfered();
                            
                        }
                        else
                        {
                             maleCount=drg.getNoOfCaregiversServedByEnrollmentStatus(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                             femaleCount=drg.getNoOfCaregiversServedByEnrollmentStatus(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female); 
                        }
                         
                    }
                     //System.err.println("Pulled Datim data for "+ageDisaggregation[j]+" to "+ageDisaggregation[j+1]);
                }
                else
                {
                    //Enrollment stream indicators are only for children less than 18
                        if(enrollmentStatus==AppConstant.ACTIVE_NUM)
                        {//drg.getNoOfActiveCaregiversServed(
                            DatimReportTemplate maleDrt=drg.getNoOfActiveCaregiversServed(rpt, rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                            DatimReportTemplate femaleDrt=drg.getNoOfActiveCaregiversServed(rpt, rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female);
                             maleCount=maleDrt.getOvc_servActive();//drg.getNoOfCaregiversServedByEnrollmentStatus(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                             femaleCount=femaleDrt.getOvc_servActive();//drg.getNoOfCaregiversServedByEnrollmentStatus(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female);

                        }
                        else
                        {
                            if(enrollmentStatus==AppConstant.GRADUATED_NUM)
                            {
                                maleCount=drg.getNoOfGraduatedCaregiversServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male).getOvc_servGraduated();
                                femaleCount=drg.getNoOfGraduatedCaregiversServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female).getOvc_servGraduated();
                            }
                            else if(enrollmentStatus==AppConstant.EXITED_WITHOUT_GRADUATION_NUM)
                            {
                                maleCount=drg.getNoOfCaregiversExitedWithoutGraduationServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male).getOvc_servExitedWithoutGraduation();
                                femaleCount=drg.getNoOfCaregiversExitedWithoutGraduationServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female).getOvc_servExitedWithoutGraduation();

                            }
                            else if(enrollmentStatus==AppConstant.TRANSFERED_NUM)
                            {
                                int transferedToPEPFARMaleCount=drg.getNoOfCaregiversServedAndTransferedToPEPFAR(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male).getOvc_servTransfered();
                                int transferedToPEPFARFemaleCount=drg.getNoOfCaregiversServedAndTransferedToPEPFAR(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female).getOvc_servTransfered();
                                int transferedToNonPEPFARMaleCount=drg.getNoOfCaregiversServedAndTransferedToNonPEPFAR(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male).getOvc_servTransfered();
                                int transferedToNonPEPFARFemaleCount=drg.getNoOfCaregiversServedAndTransferedToNonPEPFAR(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female).getOvc_servTransfered();
                                maleCount=transferedToPEPFARMaleCount+transferedToNonPEPFARMaleCount;
                                femaleCount=transferedToPEPFARFemaleCount+transferedToNonPEPFARFemaleCount;
                            }
                            else
                            {
                                DatimReportTemplate maleDrt=drg.getNoOfActiveCaregiversServed(rpt, rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                                DatimReportTemplate femaleDrt=drg.getNoOfActiveCaregiversServed(rpt, rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female);
                                maleCount=maleDrt.getOvc_servActive();
                                femaleCount=femaleDrt.getOvc_servActive();
                                 //maleCount=drg.getNoOfCaregiversServedByEnrollmentStatus(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                                 //femaleCount=drg.getNoOfCaregiversServedByEnrollmentStatus(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female); 
                            }   
                        }
                    
                }
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForOVC_RISKASSESSED(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,int eligibilityValue,int riskAssessmentValue,int riskAssessmentOutcome)
    {
        CustomIndicatorsReport cir=null;
        //DatimReportGenerator drg=new DatimReportGenerator();
        OvcReportManager orm=new OvcReportManager();
        try
        {
            //String male=AppConstant.MALESEX;
            //String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            //ReportTemplate activeRt=new ReportTemplate();
            int[] ageDisaggregation=ovcAgeDisaggregation;
            
             
            
            String cboId="All";
            rpt.setCboId(cboId);
            int activeMaleCount=0;
            int activeFemaleCount=0;
            int graduateMaleCount=0;
            int graduatedFemaleCount=0;
            int maleCount=0;
            int femaleCount=0;
            FinancialYearManager fy=new FinancialYearManager();
            String startDateOfQuarter=fy.getStartDateOfQuarter(rpt.getEndDate());
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                     ReportTemplate activeRt=orm.getNoOfChildrenForHivRiskAssessmentCascade(rpt,rpt.getStartDate(),rpt.getEndDate(),AppConstant.ACTIVE_NUM,eligibilityValue,riskAssessmentValue, riskAssessmentOutcome);
                     ReportTemplate graduatedRt=orm.getNoOfChildrenForHivRiskAssessmentCascade(rpt,startOfFinancialYear,rpt.getEndDate(),AppConstant.GRADUATED_NUM,eligibilityValue,riskAssessmentValue, riskAssessmentOutcome);
                     activeMaleCount=activeRt.getMaleTotal();
                     activeFemaleCount=activeRt.getFemaleTotal();
                     graduateMaleCount=graduatedRt.getMaleTotal();
                     graduatedFemaleCount=graduatedRt.getFemaleTotal();
                     
                     maleCount=activeMaleCount+graduateMaleCount;
                     femaleCount=activeFemaleCount+graduatedFemaleCount;        
                }
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForOVC_HTSLINK(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,int childAtRisk,int referralComplete,int hivStatus,int enrolledOnTreatment)
    {
        CustomIndicatorsReport cir=null;
        DatimReportGenerator drg=new DatimReportGenerator();
        OvcReportManager orm=new OvcReportManager();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            //ReportTemplate activeRt=new ReportTemplate();
            int[] ageDisaggregation=ovcAgeDisaggregation;
            
             
            
            String cboId="All";
            rpt.setCboId(cboId);
            int activeMaleCount=0;
            int activeFemaleCount=0;
            int graduateMaleCount=0;
            int graduatedFemaleCount=0;
            int maleCount=0;
            int femaleCount=0;
            FinancialYearManager fy=new FinancialYearManager();
            String startDateOfQuarter=fy.getStartDateOfQuarter(rpt.getEndDate());
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                     ReportTemplate activeRt=orm.getNumberOfChildrenAssessedForHivRiskAndReferredForHivTestForTreatmentCascade(rpt,rpt.getStartDate(),rpt.getEndDate(),childAtRisk,referralComplete,hivStatus,enrolledOnTreatment,AppConstant.ACTIVE_NUM);
                     ReportTemplate graduatedRt=orm.getNumberOfChildrenAssessedForHivRiskAndReferredForHivTestForTreatmentCascade(rpt,startOfFinancialYear,rpt.getEndDate(),childAtRisk,referralComplete,hivStatus,enrolledOnTreatment,AppConstant.GRADUATED_NUM);
                     activeMaleCount=activeRt.getMaleTotal();
                     activeFemaleCount=activeRt.getFemaleTotal();
                     graduateMaleCount=graduatedRt.getMaleTotal();
                     graduatedFemaleCount=graduatedRt.getFemaleTotal();
                     
                     maleCount=activeMaleCount+graduateMaleCount;
                     femaleCount=activeFemaleCount+graduatedFemaleCount;        
                }
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForOVC_HHGRAD(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,boolean servedOnly)
    {
        CustomIndicatorsReport cir=null;
        OvcReportManager orm=new OvcReportManager();
        IndicatorWarehouse indw=new IndicatorWarehouse();
        Indicator indicator=indw.getIndicatorById(indicatorCode);
        
        try
        {
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            ReportPeriodManager rpm=new ReportPeriodManager();
            FinancialYearManager fym=new FinancialYearManager();
                                   
            String cboId="All";
            rpt.setCboId(cboId);
                        
            rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             if(servedOnly)
             rt=orm.getOVC_HHGRADForDatim(rpt,rpt.getStartDate(),rpt.getEndDate(),AppConstant.ACTIVE_GRADUATED_NUM,servedOnly);
             else
             rt=orm.getOVC_HHGRADForDatim(rpt,rpt.getStartOfFinancialYear(),rpt.getEndDate(),AppConstant.GRADUATED_NUM,servedOnly);
             maleList.add(rt);
             femaleList.add(rt);
                                   
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getReportForOVC_OFFER(ReportParameterTemplate rpt,String indicatorCode,String partnerCode)
    {
        CustomIndicatorsReport cir=null;
        OvcReportManager orm=new OvcReportManager();
        IndicatorWarehouse indw=new IndicatorWarehouse();
        Indicator indicator=indw.getIndicatorById(indicatorCode);
        System.err.println("indicatorCode is "+indicatorCode);
        try
        {
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
                               
            int[] ageDisaggregation=ovcAgeDisaggregation;
                       
            String cboId="All";
            rpt.setCboId(cboId);
            int maleCount=0;
            int femaleCount=0;
                    
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                
                rt=orm.getNumberOfClientsOfferedFacilityOvcEnrollment(indicator,rpt);
                maleCount=rt.getMaleTotal();
                femaleCount=rt.getFemaleTotal();
                rt=new ReportTemplate();
                rt.setMaleTotal(maleCount);
                rt.setFemaleTotal(femaleCount);
                maleList.add(rt);
                femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForOVC_ECONS(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,int enrollmentStatus,int unexpectedExpenditureValue,int abilityToAccessMoneyToPay)
    {
        CustomIndicatorsReport cir=null;
        OvcReportManager orm=new OvcReportManager();
        IndicatorWarehouse indw=new IndicatorWarehouse();
        Indicator indicator=indw.getIndicatorById(indicatorCode);
        
        try
        {
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            FinancialYearManager fym=new FinancialYearManager();
                        
            int[] ageDisaggregation=adultAgeDisaggregation; //ovcAgeDisaggregation;
                       
            String cboId="All";
            rpt.setCboId(cboId);
            int maleCount=0;
            int femaleCount=0;
            
            rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             
                        
            String startOfLastQuarter=fym.getStartDateOfQuarter(rpt.getEndDate());
           
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                
                rt=orm.getNumberOfHouseholdsThatAccessFundToMeetUnexpectedExpencesForDatim(indicator,rpt,enrollmentStatus,unexpectedExpenditureValue,abilityToAccessMoneyToPay);
                maleCount=rt.getMaleTotal();
                femaleCount=rt.getFemaleTotal();
                rt=new ReportTemplate();
                rt.setMaleTotal(maleCount);
                rt.setFemaleTotal(femaleCount);
                maleList.add(rt);
                femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataHIVPrevention(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly)
    {
        CustomIndicatorsReport cir=null;
        DatimReportGenerator drg=new DatimReportGenerator();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            
            int[] ageDisaggregation=hivPrevAgeDisaggregation;
            
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
            
            String cboId="All";
            rpt.setCboId(cboId);
            int activeMaleCount=0;
            int activeFemaleCount=0;
            int graduateMaleCount=0;
            int graduatedFemaleCount=0;
            int maleCount=0;
            int femaleCount=0;
            
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                     activeMaleCount=drg.getNoOfActiveOvcServed(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servActive();
                     activeFemaleCount=drg.getNoOfActiveOvcServed(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servActive();
                     graduateMaleCount=drg.getNoOfGraduatedOvcServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servGraduated();
                     graduatedFemaleCount=drg.getNoOfGraduatedOvcServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servGraduated();
                     maleCount=activeMaleCount+graduateMaleCount;
                     femaleCount=activeFemaleCount+graduatedFemaleCount;        
                }
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForOVC_PROTECT(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly)
    {
        CustomIndicatorsReport cir=null;
        DatimReportGenerator drg=new DatimReportGenerator();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            
            int[] ageDisaggregation=ovcAgeDisaggregation;
            
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
            
            String cboId="All";
            rpt.setCboId(cboId);
            int activeMaleCount=0;
            int activeFemaleCount=0;
            int graduateMaleCount=0;
            int graduatedFemaleCount=0;
            int maleCount=0;
            int femaleCount=0;
            
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                     activeMaleCount=drg.getNoOfActiveOvcServed(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servActive();
                     activeFemaleCount=drg.getNoOfActiveOvcServed(rpt,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servActive();
                     graduateMaleCount=drg.getNoOfGraduatedOvcServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servGraduated();
                     graduatedFemaleCount=drg.getNoOfGraduatedOvcServed(rpt,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly).getOvc_servGraduated();
                     maleCount=activeMaleCount+graduateMaleCount;
                     femaleCount=activeFemaleCount+graduatedFemaleCount;        
                }
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForOVC_NUTRITION(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,String serviceCode)
    {
        CustomIndicatorsReport cir=null;
        OvcReportManager orm=new OvcReportManager();
        IndicatorWarehouse indw=new IndicatorWarehouse();
        Indicator indicator=indw.getIndicatorById(indicatorCode);
        DatimReportGenerator drg=new DatimReportGenerator();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            FinancialYearManager fym=new FinancialYearManager();
                        
            int[] ageDisaggregation0To5={0,0,1,4,5,5};//int[] ageDisaggregation=ovcAgeDisaggregation;
            
            String cboId="All";
            rpt.setCboId(cboId);
            int maleCount=0;
            int femaleCount=0;
            
            int activeMaleCount=0;
            int activeFemaleCount=0;
            int graduateMaleCount=0;
            int graduatedFemaleCount=0;
            String startOfLastQuarter=fym.getStartDateOfQuarter(rpt.getEndDate());
           
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation0To5.length; j+=2)
            {
                System.err.println("About to pull Datim data for "+ageDisaggregation0To5[j]+" to "+ageDisaggregation0To5[j+1]);
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation0To5[j]);
                rpt.setEndAge(ageDisaggregation0To5[j+1]);
                if(rpt.getStartAge()<18)
                {//getNumberOfOvcAssessedByNutritionStatus
                    if(serviceCode ==null)
                    {
                        rt=orm.getNumberOfMalnourishedChildren(indicator,rpt,startOfLastQuarter,rpt.getEndDate(),AppConstant.ACTIVE_NUM);
                        activeMaleCount=rt.getMaleTotal();
                        activeFemaleCount=rt.getFemaleTotal();
                        rt=orm.getNumberOfMalnourishedChildren(indicator,rpt,startOfFinancialYear,rpt.getEndDate(),AppConstant.GRADUATED_NUM);
                        graduateMaleCount=rt.getMaleTotal();
                        graduatedFemaleCount=rt.getFemaleTotal();

                        maleCount=activeMaleCount+graduateMaleCount;
                        femaleCount=activeFemaleCount+graduatedFemaleCount;
                    }
                    else
                    {
                        rt=orm.getNumberOfMalnourishedChildrenProvidedNutritionalServices(indicator,rpt,startOfLastQuarter,rpt.getEndDate(),AppConstant.ACTIVE_NUM,serviceCode);
                        activeMaleCount=rt.getMaleTotal();
                        activeFemaleCount=rt.getFemaleTotal();
                        rt=orm.getNumberOfMalnourishedChildrenProvidedNutritionalServices(indicator,rpt,startOfFinancialYear,rpt.getEndDate(),AppConstant.GRADUATED_NUM,serviceCode);
                        graduateMaleCount=rt.getMaleTotal();
                        graduatedFemaleCount=rt.getFemaleTotal();

                        maleCount=activeMaleCount+graduateMaleCount;
                        femaleCount=activeFemaleCount+graduatedFemaleCount; 
                    }
                }
                
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForOVC_ARTSUPP(ReportParameterTemplate rpt,String indicatorCode,String partnerCode)
    {
        CustomIndicatorsReport cir=null;
        OvcReportManager orm=new OvcReportManager();
        IndicatorWarehouse indw=new IndicatorWarehouse();
        Indicator indicator=indw.getIndicatorById(indicatorCode);
        DatimReportGenerator drg=new DatimReportGenerator();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            FinancialYearManager fym=new FinancialYearManager();
                        
            int[] ageDisaggregation=ovcAgeDisaggregation; //ovcAgeDisaggregation;
            //if(indicatorCode.equalsIgnoreCase(beneficiariesNewlyEnrolled))
            //ageDisaggregation=ovcAndCaregiverAgeDisaggregation;
            
            String cboId="All";
            rpt.setCboId(cboId);
            int maleCount=0;
            int femaleCount=0;
            
            int activeMaleCount=0;
            int activeFemaleCount=0;
            int graduateMaleCount=0;
            int graduatedFemaleCount=0;
            String startOfLastQuarter=fym.getStartDateOfQuarter(rpt.getEndDate());
           
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                System.err.println("About to pull Datim data for "+ageDisaggregation[j]+" to "+ageDisaggregation[j+1]);
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                    rt=orm.getOVC_ARTSUPPIndicator(indicator,rpt,startOfLastQuarter,rpt.getEndDate(),AppConstant.ACTIVE_NUM);
                    activeMaleCount=rt.getMaleTotal();
                    activeFemaleCount=rt.getFemaleTotal();
                    rt=orm.getOVC_ARTSUPPIndicator(indicator,rpt,startOfFinancialYear,rpt.getEndDate(),AppConstant.GRADUATED_NUM);
                    graduateMaleCount=rt.getMaleTotal();
                    graduatedFemaleCount=rt.getFemaleTotal();
                    maleCount=activeMaleCount+graduateMaleCount;
                    femaleCount=activeFemaleCount+graduatedFemaleCount;
                }
                else
                {
                    rt=orm.getAdult_ARTSUPPIndicator(indicator,rpt,startOfLastQuarter,rpt.getEndDate(),AppConstant.ACTIVE_NUM);
                    activeMaleCount=rt.getMaleTotal();
                    activeFemaleCount=rt.getFemaleTotal();
                    rt=orm.getAdult_ARTSUPPIndicator(indicator,rpt,startOfFinancialYear,rpt.getEndDate(),AppConstant.GRADUATED_NUM);
                    graduateMaleCount=rt.getMaleTotal();
                    graduatedFemaleCount=rt.getFemaleTotal();
                    maleCount=activeMaleCount+graduateMaleCount;
                    femaleCount=activeFemaleCount+graduatedFemaleCount;
                }
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForViralLoadCascade(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,boolean viralLoadEligibilityRequired,boolean viralLoadResultRequired,boolean viralLoadSuppressionRequired)
    {
        CustomIndicatorsReport cir=null;
        DatimReportGenerator drg=new DatimReportGenerator();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            FinancialYearManager fym=new FinancialYearManager();
                        
            int[] ageDisaggregation=ovcAndCaregiverAgeDisaggregation; //ovcAgeDisaggregation;
            if(indicatorCode.equalsIgnoreCase(beneficiariesNewlyEnrolled))
            ageDisaggregation=ovcAndCaregiverAgeDisaggregation;
            
            String cboId="All";
            rpt.setCboId(cboId);
            int maleCount=0;
            int femaleCount=0;
            
            int activeMaleCount=0;
            int activeFemaleCount=0;
            int graduateMaleCount=0;
            int graduatedFemaleCount=0;
            String startOfLastQuarter=fym.getStartDateOfQuarter(rpt.getEndDate());
           
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                System.err.println("About to pull Datim data for "+ageDisaggregation[j]+" to "+ageDisaggregation[j+1]);
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                    activeMaleCount=drg.getNoOfHivPositiveOvcOnTreatmentServedByEnrollmentStatusForViralLoadCascade(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
                    activeFemaleCount=drg.getNoOfHivPositiveOvcOnTreatmentServedByEnrollmentStatusForViralLoadCascade(rpt,AppConstant.ACTIVE_NUM,startOfLastQuarter,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
                    graduateMaleCount=drg.getNoOfHivPositiveOvcOnTreatmentServedByEnrollmentStatusForViralLoadCascade(rpt,AppConstant.GRADUATED_NUM,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
                    graduatedFemaleCount=drg.getNoOfHivPositiveOvcOnTreatmentServedByEnrollmentStatusForViralLoadCascade(rpt,AppConstant.GRADUATED_NUM,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
                    maleCount=activeMaleCount+graduateMaleCount;
                    femaleCount=activeFemaleCount+graduatedFemaleCount;
                }
                else
                {
                    activeMaleCount=drg.getNoOfActiveCaregiversServedByEnrollmentStatusAndHivStatusForDatim(rpt,startOfLastQuarter,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,AppConstant.HIV_POSITIVE_NUM,AppConstant.ENROLLED_ON_TREATMENT_YES_NUM,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
                    activeFemaleCount=drg.getNoOfActiveCaregiversServedByEnrollmentStatusAndHivStatusForDatim(rpt,startOfLastQuarter,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,AppConstant.HIV_POSITIVE_NUM,AppConstant.ENROLLED_ON_TREATMENT_YES_NUM,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
                    graduateMaleCount=drg.getNoOfCaregiversServedByEnrollmentStatusAndHivStatus(rpt,AppConstant.GRADUATED_NUM,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,AppConstant.HIV_POSITIVE_NUM,AppConstant.ENROLLED_ON_TREATMENT_YES_NUM,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
                    graduatedFemaleCount=drg.getNoOfCaregiversServedByEnrollmentStatusAndHivStatus(rpt,AppConstant.GRADUATED_NUM,startOfFinancialYear,rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,AppConstant.HIV_POSITIVE_NUM,AppConstant.ENROLLED_ON_TREATMENT_YES_NUM,viralLoadEligibilityRequired,viralLoadResultRequired,viralLoadSuppressionRequired);
                    maleCount=activeMaleCount+graduateMaleCount;
                    femaleCount=activeFemaleCount+graduatedFemaleCount;                    
                }
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForActiveForExactReportPeriod(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,int enrollmentStatus,int serviceDomain,String serviceType,String enrollmentStreamCode,boolean newlyEnrolledOnly,int enrollmentSetting,int birthCertificateValue,int childInSchoolValue)
    {
        CustomIndicatorsReport cir=null;
        
        //Indicator indicator=VulnerabilityStatusManager.getVulnerabilityStatusIndicator(indicatorCode);
        DatimReportGenerator drg=new DatimReportGenerator();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            
            int[] ageDisaggregation=ovcAndCaregiverAgeDisaggregation; //ovcAgeDisaggregation;
            if(indicatorCode.equalsIgnoreCase(beneficiariesNewlyEnrolled))
            ageDisaggregation=ovcAndCaregiverAgeDisaggregation;
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForOVC_ART_ENROLL().getIndicatorId()))
            ageDisaggregation=ovcAgeDisaggregation;
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcCurrentlyInSchool().getIndicatorId()))
            {
                 rt=new ReportTemplate();
                 maleList.add(rt);
                 femaleList.add(rt);
                 rt=new ReportTemplate();
                 maleList.add(rt);
                 femaleList.add(rt);
                ageDisaggregation=ovcAgeDisaggregation_5to17;
            }
            else if(enrollmentStreamCode != null || indicatorCode.equalsIgnoreCase(ind.getNoOfOvcServedWithinTheReportPeriodThatHasBirthCert().getIndicatorId()))
            {
                ageDisaggregation=ovcAgeDisaggregation;
                
            }
            
            String cboId="All";
            rpt.setCboId(cboId);
            int maleCount=0;
            int femaleCount=0;
            
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                System.err.println("About to pull Datim data for "+ageDisaggregation[j]+" to "+ageDisaggregation[j+1]);
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                    if(enrollmentStatus==AppConstant.ACTIVE_NUM)
                    {
                        //DatimReportTemplate drt=drg.getNoOfActiveCaregiversServed(rpt, rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                         maleCount=drg.getNoOfOvcServedByEnrollmentStatusForSpecifiedReportPeriod(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly,enrollmentSetting,birthCertificateValue, childInSchoolValue);
                         femaleCount=drg.getNoOfOvcServedByEnrollmentStatusForSpecifiedReportPeriod(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,enrollmentStreamCode,newlyEnrolledOnly,enrollmentSetting,birthCertificateValue,childInSchoolValue);
                       
                    }                    
                }
                else
                {
                    //Enrollment stream indicators are only for children less than 18
                        if(enrollmentStatus==AppConstant.ACTIVE_NUM)
                        {
                                                     //DatimReportTemplate drt=drg.getNoOfActiveCaregiversServed(rpt, rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male);
                             maleCount=drg.getNoOfCaregiversServedByEnrollmentStatusForReportPeriod(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,newlyEnrolledOnly);
                             femaleCount=drg.getNoOfCaregiversServedByEnrollmentStatusForReportPeriod(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,newlyEnrolledOnly);

                        }
                                            
                }
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport getDatimEquivalentDataForOVC_EDU(ReportParameterTemplate rpt,String indicatorCode,String partnerCode,int enrollmentStatus,int serviceDomain,String serviceType,int birthCertificateValue,int childInSchoolValue,int regularSchoolAttendance)
    {
        CustomIndicatorsReport cir=null;
        
        //Indicator indicator=VulnerabilityStatusManager.getVulnerabilityStatusIndicator(indicatorCode);
        DatimReportGenerator drg=new DatimReportGenerator();
        try
        {
            String male=AppConstant.MALESEX;
            String female=AppConstant.FEMALESEX;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            ReportTemplate rt=null;
            
            int[] ageDisaggregation=ovcAgeDisaggregation_5to17;//ageDisaggregation=ovcAgeDisaggregation;
            rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
             rt=new ReportTemplate();
             maleList.add(rt);
             femaleList.add(rt);
            /*if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcCurrentlyInSchool().getIndicatorId()))
            {
                 rt=new ReportTemplate();
                 maleList.add(rt);
                 femaleList.add(rt);
                 rt=new ReportTemplate();
                 maleList.add(rt);
                 femaleList.add(rt);
                ageDisaggregation=ovcAgeDisaggregation_5to17;
            }*/
                        
            String cboId="All";
            rpt.setCboId(cboId);
            int maleCount=0;
            int femaleCount=0;
            
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                System.err.println("About to pull Datim data for "+ageDisaggregation[j]+" to "+ageDisaggregation[j+1]);
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                    if(enrollmentStatus==AppConstant.ACTIVE_NUM)
                    {
                        maleCount=drg.getNoOfOvcServedByEnrollmentStatusForOVC_EDU(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),male,serviceDomain,serviceType,birthCertificateValue, childInSchoolValue,regularSchoolAttendance);
                        femaleCount=drg.getNoOfOvcServedByEnrollmentStatusForOVC_EDU(rpt,enrollmentStatus,rpt.getStartDate(),rpt.getEndDate(),rpt.getStartAge(),rpt.getEndAge(),female,serviceDomain,serviceType,birthCertificateValue,childInSchoolValue,regularSchoolAttendance);
                       
                    }                    
                }
                
                 rt=new ReportTemplate();
                 rt.setMaleTotal(maleCount);
                 rt.setFemaleTotal(femaleCount);
                 maleList.add(rt);
                 femaleList.add(rt);
            }
            
           cir=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cir);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cir;
    }
    private CustomIndicatorsReport processDataByCBO(ReportParameterTemplate rpt,String indicatorCode,String partnerCode)
    {
        CustomIndicatorsReport cirt=null;
        ListOfIndicatorsReportGenerator lirg=new ListOfIndicatorsReportGenerator();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        ReportTemplate rt=null;
        
        int[] ageDisaggregation=ovcAndCaregiverAgeDisaggregation;
        
        try
        {
            String[] indicatorArray={indicatorCode};
            List list=null;
            //List valueList=new ArrayList();
            String cboId="All";
            rpt.setCboId(cboId);
            //Get the data by age disaggregation
            for(int j=0; j<ageDisaggregation.length; j+=2)
            {
                System.err.println("About to pull for "+ageDisaggregation[j]+" to "+ageDisaggregation[j+1]);
                //set the start age and end age
                rpt.setStartAge(ageDisaggregation[j]);
                rpt.setEndAge(ageDisaggregation[j+1]);
                if(rpt.getStartAge()<18)
                {
                    list=lirg.getOvcEnrolledSummStatistics(rpt, indicatorArray);                   
                }
                else
                {
                    list=processDataFor18AndAboveByCBO(rpt,indicatorCode);
                }
                if(list !=null && !list.isEmpty())
                {
                    rt=(ReportTemplate)list.get(0);
                    maleList.add(rt);
                    femaleList.add(rt);
                    //System.err.println("Pulled data for "+ageDisaggregation[j]+" to "+ageDisaggregation[j+1]);
                }
            }
            
           cirt=getReportTemplate(rpt.getLevel2OuId(),rpt.getLevel3OuId(),rpt.getCboId(),partnerCode,indicatorCode,maleList,femaleList,mainReportPeriod);
           saveReportTemplate(cirt);      
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cirt;
    }
    
    private List processDataFor18AndAboveByCBO(ReportParameterTemplate rpt,String indicatorCode)
    {
        //List paramList This is a method arguments
        List mainList=new ArrayList();
        try
        {
            ListOfIndicatorsReportGenerator lirg=new ListOfIndicatorsReportGenerator();
            int executed=1;
            
            ReportTemplate rt=new ReportTemplate();
            
            String[] indicatorArray={indicatorCode};
            //
            if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfBeneficiariesWhoAreVirallySuppresedInPast12Months().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfBeneficiariesWhoAreVirallySuppresedInPast12Months().getIndicatorId();
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfBeneficiariesWithViralLoadResultInPast12Months().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfBeneficiariesWithViralLoadResultInPast12Months().getIndicatorId();
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfBeneficiariesEligibleForViralLoad().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfBeneficiariesEligibleForViralLoad().getIndicatorId();
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveAndGraduatedHivPositiveBeneficiariesEnrolledOnTreatment().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfActiveAndGraduatedHivPositiveBeneficiariesEnrolledOnTreatment().getIndicatorId();
            
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfNewOvcEnrolled().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfAdultMembersEnrolledWithinTheReportPeriod().getIndicatorId();
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveBeneficiariesServedMER().getIndicatorId()))
                    //getIndicatorForNumberOfOvcCurrentlyEnrolledAndServedInReportPeriod().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodForDatim().getIndicatorId();
            //getIndicatorForNumberOfActiveCaregiversServedWithinDatimReportPeriod().getIndicatorId();
            
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcGraduatedButServedInReportPeriod().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfGraduatedCaregiversServedWithinTheReportPeriodForDatim().getIndicatorId();
                    //getIndicatorForNumberOfCaregiversServedAndGraduatedWithinDatimReportPeriod().getIndicatorId();
            
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcTransferedOutButServedInReportPeriod().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfCaregiversServedAndTransferedWithinDatimReportPeriod().getIndicatorId();
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcExitedWithoutGraduation().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfCaregiversServedAndExitedWithinDatimReportPeriod().getIndicatorId();
            
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcProvidedReferralForHIVRelatedTestingService().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfAdultMembersProvidedReferralForHIVRelatedTestingService().getIndicatorId();
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcNewlyTestedPositiveWithinTheReportPeriod().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfCaregiversNewlyTestedPositive().getIndicatorId();
            
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfHivPositiveOvcEnrolledOnARTWithinTheReportPeriod().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfHIVPositiveCaregiversNewlyEnrolledOnARTWithinTheReportPeriod().getIndicatorId();
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcSelfReportingAdherenceToTreatment().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfAdultMembersSelfReportingAdherenceToTreatment().getIndicatorId();
            
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfNewEnrolledBeneficiaries().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfNewEnrolledBeneficiaries().getIndicatorId();
            
            /*else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodForDatim().getIndicatorId();
            else if(indicatorCode.equalsIgnoreCase(ind.getIndicatorForNumberOfGraduatedCaregiversServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicatorArray[0]=ind.getIndicatorForNumberOfGraduatedCaregiversServedWithinTheReportPeriodForDatim().getIndicatorId();*/
            else
            executed=0;
            if(executed==1)
            {
                mainList=lirg.getOvcEnrolledSummStatistics(rpt, indicatorArray);
                if(mainList==null || mainList.isEmpty())
                mainList.add(rt);
            }
            else
            {
                mainList.add(rt);
                
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mainList;
    }
    
    public CustomIndicatorsReport getReportTemplate(String level2OuId,String level3OuId,String cboName,String partnerCode,String indicatorId,List maleList,List femaleList,String period)
    {
        ReportTemplate maleRt=null;
        ReportTemplate femaleRt=null;
        IndicatorWarehouse indw=new IndicatorWarehouse();
        Indicator ind=indw.getIndicatorById(indicatorId);
        if(ind ==null)
        return new CustomIndicatorsReport();
        
        if(ind.getAlternateName()==null)
        ind.setAlternateName(ind.getIndicatorName());
        //ReportTemplate rt
        //String hhgradOvcServedId=ind.getIndicatorId();
        //String ovcEconsId=ind.getIndicatorId();
        CustomIndicatorsReport cirt=new CustomIndicatorsReport();
        cirt.setLevel2OuId(level2OuId);
        cirt.setLevel3OuId(level3OuId);
        cirt.setLevel4OuId("xxxxxxxxxxx");
        cirt.setCboId(cboName);
        cirt.setIndicator(ind);
        cirt.setIndicatorId(indicatorId);
        cirt.setIndicatorName(indicatorId);
        cirt.setReportPeriod(period);
        cirt.setPartnerCode(partnerCode);
        //rt.setp
        
        if(ind !=null)
        {
            cirt.setIndicatorName(ind.getIndicatorName());
            cirt.setMerCode(ind.getMerCode());
            cirt.setOtherDisaggregation("other");
            cirt.setUserName(userName);
            cirt.setDateCreated(DateManager.getDateInstance(DateManager.getCurrentDate()));
        }        
        
        int maleValue=0;
        int femaleValue=0;
        int maleTotal=0;
        int femaleTotal=0;
        if(maleList !=null && !maleList.isEmpty() && femaleList !=null && !femaleList.isEmpty())
        {
            maleRt=(ReportTemplate)maleList.get(0);
            femaleRt=(ReportTemplate)femaleList.get(0);
            
            /*if(ind !=null && ind.getIndicatorType() !=null && ind.getIndicatorType().equalsIgnoreCase(AppConstant.HOUSEHOLD_TYPE))
            {
                if(!maleList.isEmpty())
                maleValue=maleRt.getMaleTotal();//Integer.parseInt(maleList.get(0).toString());
                cirt.setGrandTotal(maleValue);
            }
            else
            {*/
                for(int i=0; i<maleList.size(); i++)
                {
                    maleRt=(ReportTemplate)maleList.get(i);
                    femaleRt=(ReportTemplate)femaleList.get(i);
                    maleValue=maleRt.getMaleTotal();
                    femaleValue=femaleRt.getFemaleTotal();
                    
                    maleTotal+=maleValue;
                    femaleTotal+=femaleValue;
                    if(i==0)
                    {
                        cirt.setMaleLessThan1(maleValue);
                        cirt.setFemaleLessThan1(femaleValue);
                    }
                    else if(i==1)
                    {
                        cirt.setMale1to4(maleValue);
                        cirt.setFemale1to4(femaleValue);
                    }
                    else if(i==2)
                    {
                        cirt.setMale5to9(maleValue);
                        cirt.setFemale5to9(femaleValue);
                    }
                    else if(i==3)
                    {
                        cirt.setMale10to14(maleValue);
                        cirt.setFemale10to14(femaleValue);
                    }
                    else if(i==4)
                    {
                        cirt.setMale15to17(maleValue);
                        cirt.setFemale15to17(femaleValue);
                    }
                    else if(i==5)
                    {
                        if(maleList.size()>7)
                        {
                            cirt.setMale18PlusChildren(maleValue);
                            cirt.setFemale18PlusChildren(femaleValue);
                        }
                        else
                        {
                           cirt.setMale18to24(maleValue);
                           cirt.setFemale18to24(femaleValue); 
                        }
                    }
                    else if(i==6)
                    {
                        if(maleList.size()>7)
                        {
                            cirt.setMale18to24(maleValue);
                            cirt.setFemale18to24(femaleValue); 
                        }
                        else
                        {
                           cirt.setMale25Plus(maleValue);
                           cirt.setFemale25Plus(femaleValue);
                        }
                        
                    }
                    else if(i==7)
                    {
                        cirt.setMale25Plus(maleValue);
                        cirt.setFemale25Plus(femaleValue);
                    }
                }
                int grandTotal=maleTotal+femaleTotal;
                cirt.setMaleTotal(maleTotal);
                cirt.setFemaleTotal(femaleTotal);
                cirt.setGrandTotal(grandTotal);
                
            }
            
        //}
        return cirt;
    }
    
    /*private List getMaleList(List valueList)
    {
        ReportTemplate rt=null;
        List maleList=new ArrayList();
        if(valueList==null) //|| valueList.size()<2)
        {
            maleList.add(0);
        }
        else
        {
            rt=(ReportTemplate)valueList.get(0);
            //valueList.get(1)
            maleList.add(rt.getMaleTotal());
        }
        return maleList;
    }
    private List getFemaleList(List valueList)
    {
        ReportTemplate rt=null;
        List femaleList=new ArrayList();
        if(valueList==null || valueList.size()<3)
        {
            femaleList.add(0);
        }
        else
        {
            rt=(ReportTemplate)valueList.get(0);
            //valueList.get(2)
            femaleList.add(rt.getFemaleTotal());
        }
        return femaleList;
    }*/
    public static void processCustomIndicatorsReport(List list)
    {
        try
        {
        //CustomIndicatorsReportDao cirbdao=new CustomIndicatorsReportDaoImpl();
        if(list !=null)
        {
            CustomIndicatorsReport rt=null;
            for(Object obj:list)
            {
                rt = (CustomIndicatorsReport)obj; 
                saveReportTemplate(rt);
                //cirbdao.saveOrUpdateCustomIndicatorsReport(rt);
            }
        }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
    }
    public static void saveReportTemplate(CustomIndicatorsReport cirt)
    {
        try
        {
            if(cirt !=null)
            {
                CustomIndicatorsReportDao cirbdao=new CustomIndicatorsReportDaoImpl();
                cirbdao.saveOrUpdateCustomIndicatorsReport(cirt);
                System.err.println(cirt.getIndicatorName()+" saved for LGA "+cirt.getLevel3OuId());
                System.err.println("Number of threads left is "+AppUtility.customIndicatorsThreadCounter);
                
            }        
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
    }
    public List getListOfCBOs(ReportParameterTemplate rpt)
    {
        //String lgaCode
        List cboList=new ArrayList();
        try
        {
            DaoUtility util=new DaoUtility();
            List cboCodeList=util.getHouseholdEnrollmentDaoInstance().getDistinctCommunityBasedOrganizationIds(rpt);
            if(cboCodeList !=null)
            { 
                CommunityBasedOrganization cbo=null;
                String cboId=null;
                for(int i=0; i<cboCodeList.size(); i++)
                {
                    if(cboCodeList.get(i) !=null)
                    {
                        cboId=cboCodeList.get(i).toString();
                        cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboId);
                        if(cbo==null)
                        {
                            cbo=new CommunityBasedOrganization();
                            cbo.setCboCode(null);
                            cbo.setUniqueId(cboId);
                            cbo.setCboName(cboId);
                            cbo.setAssignedLevel3OuIds(rpt.getLevel3OuId());
                            cbo.setLevel2OuId(rpt.getLevel2OuId());
                        }
                        cboList.add(cbo);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return cboList;
    }
    public static List getCustomIndicators()
    {
        List list=new ArrayList();
        IndicatorDictionary ind=new IndicatorDictionary();
        list.add(ind.getOvc_ARTSUPPIndicator());
        list.add(ind.getOvc_BIRTHCERTIndicator());
        list.add(ind.getOvc_ECONSIndicator());
        list.add(ind.getOvc_NEWIndicator());
        list.add(ind.getOvc_EDUIndicator());
        list.add(ind.getOvc_HHGRADIndicator());
        list.add(ind.getOvc_HIVPREVIndicator());
        list.add(ind.getOVC_HIVRISKASSESSEDIndicator());
        list.add(ind.getOvc_HIVSTATIndicator());
        list.add(ind.getOvc_HTSLINKIndicator());
        list.add(ind.getOvc_PROTECTIndicator());
        list.add(ind.getOvc_SERVIndicator());
        list.add(ind.getOvc_TXLINKIndicator());
        list.add(ind.getOvc_NUTRITIONIndicator());
        list.add(ind.getOVC_VL_ELIGIBLEIndicator());
        list.add(ind.getOVC_VLRIndicator());
        list.add(ind.getOVC_VLSIndicator());
        list.add(ind.getOVC_ART_OFFERIndicator());
        list.add(ind.getOVC_ART_ENROLLIndicator());
        return list;
    }
    /*public String[] getQueryParam(List paramList)
    {
        OrganizationUnitAttributesManager ouam=new OrganizationUnitAttributesManager();
        String stateCode=(String)paramList.get(0);
        String lgaCode=(String)paramList.get(1);
        String cboCode=(String)paramList.get(2);
        String wardCode=(String)paramList.get(3);
        String startMonth=paramList.get(4).toString();
        String startYear=paramList.get(5).toString();
        String endMonth=paramList.get(6).toString();
        String endYear=paramList.get(7).toString();
        String partnerCode="All";
        if(paramList.size()>7)
        partnerCode=(String)paramList.get(8);
        
        System.err.println("partnerCode in rutil.getQueryParam(List paramList) is "+partnerCode);
        String partnerName=ouam.getPartnerName(partnerCode);
        String[] queryParam={stateCode,lgaCode,cboCode,wardCode,startMonth,startYear,endMonth,endYear,null,null,null,null,null,null,partnerName,partnerCode,null,null,partnerCode};
        return queryParam;
    }*/
}
