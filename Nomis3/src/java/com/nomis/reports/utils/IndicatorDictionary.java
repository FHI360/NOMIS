/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;
import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class IndicatorDictionary implements Serializable
{
    //
    public Indicator getIndicatorForOVC_ART_OFFER()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mervcfacoff");
        indicator.setIndicatorName("Number of children and adolescents on ART in PEPFAR clinical settings offered enrollment into the OVC program");
        indicator.setDescription("This indicator counts the number of children and adolescents on ART in PEPFAR clinical settings offered enrollment into the OVC program");
        indicator.setMerCode("OVC_OFFER");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForOVC_ART_ENROLL()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcartenroll");
        indicator.setIndicatorName("Number of HIV positive children and adolescents on ART at a PEPFAR clinical setting who are enrolled in the OVC comprehensive program after having been offered enrollment");
        indicator.setDescription("This indicator counts the Number of HIV positive children and adolescents on ART at a PEPFAR clinical setting who are enrolled in the OVC comprehensive program after having been offered enrollment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_ART_ENROLL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTestAndDidNotReceiveTest()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrsrefntst");
        indicator.setIndicatorName("OVC referred for testing and not tested (OVC_HTSLINK)");
        indicator.setDescription("This indicator counts the number of OVC with Unknown or Negative HIV status who were assessed for HIV risk, referred for HIV test but did not receive test. Those not assessed for HIV risk are excluded");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HTSLINK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTestAndCompletedReferral()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrskcomref");
        indicator.setIndicatorName("Number of OVC with Unknown or Negative HIV status referred for testing  who got tested and received result (OVC_HTSLINK)");
        indicator.setDescription("This indicator counts the number of OVC with Unknown or Negative HIV status who were assessed for HIV risk, referred for HIV test who got tested and received result. Those not assessed for HIV risk are excluded");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HTSLINK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWithKnownHIVStatusMER()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivstamer");
        indicator.setMerCode("OVC_HIVSTAT");
        indicator.setIndicatorName("Number of orphans and vulnerable children (<18 years old) with HIV status reported, disaggregated by HIV status");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Number of orphans and vulnerable children (<18 years old) with HIV status reported, disaggregated by HIV status");
        indicator.setSource("Child and caregiver forms");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        indicator.setDatimId("BoiTgHlqBmn");
        indicator.setDatimName("OVC_HIVSTAT (N, DSD, ReportedStatus): OVC Disclosed Known HIV Status");
        
        return indicator;
    }
    public Indicator getIndicatorForProportionOfOvcWithKnownHIVStatusMER()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivstprop");
        indicator.setMerCode("OVC_HIVSTAT");
        indicator.setIndicatorName("Percentage of orphans and vulnerable children (<18 years old) with HIV status reported to implementing partner");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Percentage of orphans and vulnerable children (<18 years old) with HIV status reported to implementing partner");
        indicator.setSource("Child and caregiver forms");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveBeneficiariesServedMER()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnservrpmer");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of active beneficiaries served by PEPFAR OVC programs for children and families affected by HIV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Number of active beneficiaries served by PEPFAR OVC programs for children and families affected by HIV");
        indicator.setSource("Child and caregiver forms");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfGraduatedBeneficiariesServedMER()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bngrdsermer");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of graduated beneficiaries served by PEPFAR OVC programs for children and families affected by HIV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Number of graduated beneficiaries served by PEPFAR OVC programs for children and families affected by HIV");
        indicator.setSource("Child and caregiver forms");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcDocumetedAsReferredForHivTestOnRiskAssessmentForm()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefhivtst");
        indicator.setIndicatorName("Number of OVC (<18 years old) at risk of HIV and referred for HIV test documented on the HIV Risk assessment form");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of OVC (<18 years old) at risk of HIV and referred for HIV test and documented on the HIV Risk assessment form)");
        indicator.setSource("HIV Risk assessment register");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("OVC_RISKASSESSED");
        return indicator;
    }  
    public Indicator getIndicatorForNumberOfOvcEligibleForHIVRiskAssessment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcelghivrsk");
        indicator.setIndicatorName("Number of Unknown/Undisclosed or HIV-negative OVC eligible for HIV Risk assessment (<18 years old)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of active HIV Unknown/Undisclosed OVC and HIV-negative OVC in the program at the time of reporting. This excludes HIV negative OVC that were assessed for HIV Risk in less than 6 months from date of reporting (<18 years old)");
        indicator.setSource("OVC Enrollment register");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("OVC_RISKASSESSED");
        return indicator;
    }
    public Indicator getNoOfOvcAssessedForHIVRiskWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivrskass");
        indicator.setIndicatorName("Number of OVC (<18 years old) determined of Unknown status or HIV-Negative, risk-assessed using a HIV risk assessment tool");
        indicator.setDescription("This indicator counts the number of OVC (<18 years old) determined of Unknown status or HIV-Negative, risk-assessed using a HIV risk assessment tool");
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForProportionOfOvcAssessedForHIVRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcperrskass");
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorName("Percentage of OVC (<18 years old) determined of unknown status or HIV-negative, who were assessed to be at risk for HIV using a risk assessment tool");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Percentage of OVC (<18 years old) determined of unknown status or HIV-negative, who were assessed to be at risk for HIV using a risk assessment tool");
        indicator.setSource("OVC Risk assessment form");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForProportionOfBeneficiariesWhoAreVirallySuppresedInPast12Months()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnpervldsup");
        indicator.setMerCode("OVC_VLS");
        indicator.setIndicatorName("Percentage of HIV positive OVC (required) and caregiver (suggested), active and graduated, who are served by an OVC comprehensive program who are on ART and are virally suppressed (<1000 copies/ml)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Percentage of HIV positive OVC (required) and caregiver (suggested), active and graduated, who are served by an OVC comprehensive program who are on ART and are virally suppressed (<1000 copies/ml)");
        indicator.setSource("Care and Support Checklist");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesWhoAreVirallySuppresedInPast12Months()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnvloaddsup");
        indicator.setMerCode("OVC_VLS");
        indicator.setIndicatorName("Number of HIV positive OVC and caregivers (active and graduated) who are served by an OVC comprehensive program who are on ART and are virally suppressed (<1000 copies/ml)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Number of HIV positive OVC and caregivers (active and graduated) who are served by an OVC comprehensive program who are on ART and are virally suppressed (<1000 copies/ml)");
        indicator.setSource("Care and Support Checklist");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForProportionOfBeneficiariesWithViralLoadResultInPast12Months()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnpervloadr");
        indicator.setMerCode("OVC_VLR");
        indicator.setIndicatorName("% of HIV positive OVC (required) and caregiver (suggested), active and graduated, who are served by an OVC comprehensive program who are on ART with a known documented viral load test result within the previous 12 months");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the % of HIV positive OVC (required) and caregiver (suggested), active and graduated, who are served by an OVC comprehensive program who are on ART with a known documented viral load test result within the previous 12 months");
        indicator.setSource("Care and Support Checklist");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesWithViralLoadResultInPast12Months()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnvloadresl");
        indicator.setMerCode("OVC_VLR");
        indicator.setIndicatorName("Number of HIV positive OVC and caregivers (active and graduated) who are served by an OVC comprehensive program who are on ART with a known documented viral load test result within the previous 12 months");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Number of HIV positive OVC and caregivers (active and graduated) who are served by an OVC comprehensive program who are on ART with a known documented viral load test result within the previous 12 months");
        indicator.setSource("Care and Support Checklist");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForProportionOfBeneficiariesEligibleForViralLoad()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnpervlelig");
        indicator.setMerCode("OVC_VL_ELIGIBLE");
        indicator.setIndicatorName("% of HIV positive children (required) and caregivers (suggested), active or graduated, who are served by an OVC comprehensive program on ART, who are eligible for viral load testing");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the percentage of HIV positive children (required) and caregivers (suggested), active or graduated, who are served by an OVC comprehensive program on ART, who are eligible for viral load testing.");
        indicator.setSource("Care and Support Checklist");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesEligibleForViralLoad()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnvloadelig");
        indicator.setMerCode("OVC_VL_ELIGIBLE");
        indicator.setIndicatorName("Number of HIV positive children and caregivers (active or graduated) who are served by an OVC comprehensive program  on ART who are eligible to have a VL test");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of HIV positive children and caregivers (active or graduated) who are served by an OVC comprehensive program  on ART who are eligible to have a VL test. This indicator counts only those that are eligible for testing but yet to be tested.");
        indicator.setSource("Care and Support Checklist");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveAndGraduatedHivPositiveBeneficiariesEnrolledOnTreatment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnartactgrd");
        indicator.setIndicatorName("Number of HIV positive children and caregivers (active or graduated) who are served by an OVC comprehensive program on ART");
        indicator.setDescription("This indicator counts the number of active and graduated HIV positive beneficiaries enrolled on treatment");
        indicator.setSource("Children and Adult household members register");
        indicator.setOtherInformation("This indicator counts just active and graduated beneficiaries");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("OVC_VL_ELIGIBLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveOvcServedWithinTheReportPeriodForDatim()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcactserved");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of active beneficiaries (children) served");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children that are served within the report period ("+indicator.getMerCode()+")");
        indicator.setSource("Child service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period ("+indicator.getMerCode()+")");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfGraduatedOvcServedWithinTheReportPeriodForDatim()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcgrtserved");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of graduated beneficiaries (children) served");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children that are served and graduated within the report period ("+indicator.getMerCode()+")");
        indicator.setSource("Child service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period ("+indicator.getMerCode()+")");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveCaregivers18T024ServedWithinTheReportPeriodForDatim()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cg18actserv");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of active beneficiaries (Adults/Caregivers) 18-24 years served");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of adults/caregivers  18-24 years  that are served within the report period ("+indicator.getMerCode()+")");
        indicator.setSource("Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period ("+indicator.getMerCode()+")");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveCaregivers25PlusServedWithinTheReportPeriodForDatim()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cg25actserv");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of active beneficiaries (Adults/Caregivers) 25+ years served");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of adults/caregivers  25+ years  that are served within the report period ("+indicator.getMerCode()+")");
        indicator.setSource("Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period ("+indicator.getMerCode()+")");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfGraduatedCaregivers18To24ServedWithinTheReportPeriodForDatim()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cg18grdserv");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of graduated beneficiaries (Adults/Caregivers) 18-24 served");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of adults/caregivers 18-24 that are served and graduated within the report period ("+indicator.getMerCode()+")");
        indicator.setSource("Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period ("+indicator.getMerCode()+")");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfGraduatedCaregivers25PlusServedWithinTheReportPeriodForDatim()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cg25grdserv");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of graduated beneficiaries (Adults/Caregivers) 25+ served");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of adults/caregivers 25+ that are served and graduated within the report period ("+indicator.getMerCode()+")");
        indicator.setSource("Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period ("+indicator.getMerCode()+")");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodForDatim()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgactserved");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of active beneficiaries (Adults/Caregivers) served");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of adults/caregivers that are served within the report period ("+indicator.getMerCode()+")");
        indicator.setSource("Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period ("+indicator.getMerCode()+")");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfGraduatedCaregiversServedWithinTheReportPeriodForDatim()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cggrdserved");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorName("Number of graduated beneficiaries (Adults/Caregivers) served");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of adults/caregivers that are served and graduated within the report period ("+indicator.getMerCode()+")");
        indicator.setSource("Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period ("+indicator.getMerCode()+")");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnservedrpe");
        indicator.setIndicatorName("Number of beneficiaries who received services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of beneficiaries that are served within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeBeneficiariesCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnhivnegenr");
        indicator.setIndicatorName("Number of HIV negative beneficiaries currently enrolled");
        indicator.setDescription("This indicator counts the number of beneficiaries currently enrolled (active or re-enrolled) whose current HIV status is negative");
        indicator.setSource("Children and Adult household members register");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownBeneficiariesCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnhivuknenr");
        indicator.setIndicatorName("Number of HIV unknown beneficiaries currently enrolled");
        indicator.setDescription("This indicator counts the number of beneficiaries currently enrolled (active or re-enrolled) whose current HIV status is unknown");
        indicator.setSource("Children and Adult household members register");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period.");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownAndHivNegativeOvc()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunkandneg");
        indicator.setMerCode("OVC_HIVSTAT");
        indicator.setIndicatorName("Number of Unknown/Undisclosed or HIV-negative OVC (<18 years old)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of Number of Unknown/Undisclosed or HIV-negative OVC (<18 years old)");
        indicator.setSource("OVC Enrollment register");
        indicator.setOtherInformation("");
        indicator.setDisaggregations("Age, Sex");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcWithTestNotIndicated()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctnicurenr");
        indicator.setIndicatorName("Number of OVC Screened and HIV test NOT required");
        indicator.setDescription("This indicator counts the number of beneficiaries currently enrolled (active or re-enrolled) whose current HIV status is Test not indicated based on HIV Risk Assessment");
        indicator.setSource("Children register");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period.");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcAtRiskOfHivInfection()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivrskenr");
        indicator.setIndicatorName("Number of OVC Screened and HIV test required");
        indicator.setDescription("This indicator counts the number of beneficiaries currently enrolled (active or re-enrolled) whose current HIV status is unknown based on HIV Risk assessment and an HIV test is required");
        indicator.setSource("Children register");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period");
        indicator.setAlternateName("At Risk of HIV infection");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVRISK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcNotScreenedForHivRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnotscrenr");
        indicator.setIndicatorName("Number of OVC Not screened for HIV");
        indicator.setAlternateName("Number of OVC not screened for HIV");
        indicator.setDescription("This indicator counts the number of beneficiaries currently enrolled (active or re-enrolled) whose current HIV status is unknown but have not been screened for HIV");
        indicator.setSource("Children register");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVRISK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveNotOnTreatment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnonartenr");
        indicator.setIndicatorName("Number of HIV Positive beneficiaries  currently enrolled and Not enrolled on treatment");
        indicator.setDescription("This indicator counts the number of beneficiaries currently enrolled (active or re-enrolled) whose current HIV status is positive but are not enrolled on treatment");
        indicator.setSource("Children and Adult household members register");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOnTreatment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivartenr");
        indicator.setIndicatorName("Number of HIV Positive children currently enrolled and enrolled on treatment");
        indicator.setDescription("This indicator counts the number of children currently enrolled (active or re-enrolled) whose current HIV status is positive and are enrolled on treatment");
        indicator.setSource("Children and Adult household members register");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveEnrolledOnTreatmentInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivartrpr");
        indicator.setIndicatorName("Number of HIV Positive children currently enrolled and enrolled on treatment within the report period");
        indicator.setDescription("This indicator counts the number of beneficiaries currently enrolled (active or re-enrolled) whose current HIV status is positive and are enrolled on treatment within the report period");
        indicator.setSource("Children and Adult household members register");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveAdultMembersServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcactvahmrp");
        indicator.setIndicatorName("Number of adult members served within the report period that are currently active");
        indicator.setAlternateName("Number of adult members served within the report period that are currently active");
        indicator.setDescription("This indicator counts the number of adult members provided services at least once with the specified report period that are still active in the program");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveOvcServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcactvserrp");
        indicator.setIndicatorName("Number of OVC served within the report period that are currently active");
        indicator.setAlternateName("Number of OVC served within the report period that are currently active");
        indicator.setDescription("This indicator counts the number of children provided services at least once with the specified report period that are still active in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenParticipatingInSupportGroup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcinsuppgrp");
        indicator.setIndicatorName("Number of children participating in support groups");
        indicator.setAlternateName("Number of children participating in support groups");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenProvidedStationarySupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcstatnysup");
        indicator.setIndicatorName("Number of children receiving stationery support");
        indicator.setAlternateName("Number of children receiving stationery support");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("SCHOOL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenProvidedSchoolFeeSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcschfeesup");
        indicator.setIndicatorName("Number of children receiving school fees");
        indicator.setAlternateName("Number of children receiving school fees");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("SCHOOL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenProvidedEmergencyShelterSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcemgshlter");
        indicator.setIndicatorName("Number of children receiving emergency shelter support");
        indicator.setAlternateName("Number of children receiving emergency shelter support");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenProvidedLegalAssistanceRelatedToMaltreatmentAndGBV()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vclegalmgbv");
        indicator.setIndicatorName("Number of children receiving legal assistance related to maltreatment, GBV");
        indicator.setAlternateName("Number of children receiving legal assistance related to maltreatment, GBV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenAssistedWithBirthRegistration()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcbirthregn");
        indicator.setIndicatorName("Number of children assisted with Birth registration");
        indicator.setAlternateName("Number of children assisted with Birth registration");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenProvidedPostViolenceTraumaCounsessling()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcpostviocn");
        indicator.setIndicatorName("Number of children receiving post violence trauma informed counselling from a trained provider");
        indicator.setAlternateName("Number of children receiving post violence trauma informed counselling from a trained provider");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenParticipatingInChildRightsSession()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcchldright");
        indicator.setIndicatorName("Number of children participating in child rights sessions");
        indicator.setAlternateName("Number of children participating in child rights sessions");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenTrackedDevMilestoneInHiv()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcdevmilhiv");
        indicator.setIndicatorName("Number of children regularly tracked developmental milestones in HIV infected, HEU and infants");
        indicator.setAlternateName("Number of children regularly tracked developmental milestones in HIV infected, HEU and infants");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenProvidedAgeAppropriateHIVTreatmentLiteracyServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivtrtlit");
        indicator.setIndicatorName("Number of children receiving age appropriate HIV treatment literacy (CHLHIV)");
        indicator.setAlternateName("Number of children receiving age appropriate HIV treatment literacy (CHLHIV)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenProvidedAgeAppropriateCounsellingAndHIVDisclosureSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivdiscrp");
        indicator.setIndicatorName("Number of children receiving age appropriate counselling and HIV disclosure support");
        indicator.setAlternateName("Number of children receiving age appropriate counselling and HIV disclosure support");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    
    
    public Indicator getIndicatorForNumberOfCaregiversParticipatingInBetterParentingPlus()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgbetparent");
        indicator.setIndicatorName("Number of caregivers participating in Better Parenting Plus");
        indicator.setAlternateName("Number of caregivers participating in Better Parenting Plus");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setMerCode("SAFE");
        indicator.setDescription("This is derived from caregivers provided  evidence based parenting services");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversParticipatingInSinovoTeens()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsinovotns");
        indicator.setIndicatorName("Number of caregivers participating in Sinovuyo teens");
        indicator.setAlternateName("Number of caregivers participating in Sinovuyo teens");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedAgeAppropriateCounsellingAndHIVDisclosureSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivdiscrp");
        indicator.setIndicatorName("Number of caregivers who received age appropriate counselling and HIV disclosure support");
        indicator.setAlternateName("Number of caregivers who received age appropriate counselling and HIV disclosure support");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedFinancialLiteracyTraining()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgfinlitrcy");
        indicator.setIndicatorName("Number of Caregivers participating in Financial literacy training");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Caregivers participating in Financial literacy training");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedSmallBusinessSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsmbussupp");
        indicator.setIndicatorName("Number of Caregivers participating in small business support (business planning, market linkages)");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This counts caregivers/adult menbers linked to markets");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfCaregiversProvidedEmergencyShelterSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgemgshlter");
        indicator.setIndicatorName("Number of Caregivers receiving emergency shelter care/facility");
        indicator.setAlternateName("Number of Caregivers receiving emergency shelter care/facility");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedSoftSkillsTraining()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsoftskill");
        indicator.setIndicatorName("Caregivers participating in soft skills training (job readiness, borrower training, career planning)");
        indicator.setAlternateName("Caregivers participating in soft skills training (job readiness, borrower training, career planning)");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This counts caregivers provided business skills training");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedWASHServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgwashmsgrp");
        indicator.setIndicatorName("Number of caregivers receiving WASH messaging");
        //indicator.setIndicatorName("Number of Adult members provided Household hygiene counseling and WASH messaging");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of caregivers receiving WASH messaging");
        indicator.setMerCode("HEALTH");
        return indicator;
    }  
    public Indicator getIndicatorForNumberOfAdultMembersProvidedLinkagesToFinancialInstitutionsAndPrivateSector()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cglinktofin");
        indicator.setIndicatorName("Number of Adult members Linked to financial institutions & private sector");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members Linked to financial institutions & private sector");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersProvidedSavingsAndInternalLendingCommunity()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cglinkdsilc");
        indicator.setIndicatorName("Number of Adult members linked to Savings groups (SILC)");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members linked to Savings groups (SILC)");
        indicator.setMerCode("STABLE");
        return indicator;
    }

public Indicator getIndicatorForNumberOfAdultMembersProvidedHIVAdherenceSupportGroupService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivadhsup");
        indicator.setIndicatorName("Number of Adult members provided with HIV Adherence support");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members provided with HIV Adherence support");
        indicator.setMerCode("HEALTH");
        return indicator;
    }

public Indicator getIndicatorForNumberOfAdultMembersProvidedStructuredPLHASupportGroupService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgplhasupgp");
        indicator.setIndicatorName("Number of Adult members enrolled in structured PLHA Support group");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members enrolled in structured PLHA Support group ");
        indicator.setMerCode("HEALTH");
        return indicator;
    }

public Indicator getIndicatorForNumberOfAdultMembersProvidedEarlyChildStimulationService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgstimulatn");
        indicator.setIndicatorName("Number of Adult members provided early Childhood Stimulation (ECS)");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members provided early Childhood Stimulation (ECS)");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
public Indicator getIndicatorForNumberOfAdultMembersProvidedEmergencyTransportAssistanceService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgemergtran");
        indicator.setIndicatorName("Number of Adult members provided emergency transport assistance");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members provided emergency transport assistance");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
public Indicator getIndicatorForNumberOfAdultMembersProvidedMotherBabyCourseServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgmbbcourse");
        indicator.setIndicatorName("Number of Adult members who participated in Mother Baby Courses(MBC)");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members who participated in Mother Baby Courses(MBC)");
        indicator.setMerCode("HEALTH");
        return indicator;
    }



public Indicator getIndicatorForNumberOfAdultMembersProvidedEvidenceBasedParentingService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgevidbpart");
        indicator.setIndicatorName("Evidence based parenting service");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members provided Evidence based parenting service within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("SAFE");
        return indicator;
    }

public Indicator getIndicatorForNumberOfAdultMembersProvidedEntrepreneurshipandBusinessManagementTraining()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgenterptrg");
        indicator.setIndicatorName("Number of Adult members provided entrepreneurship and business management training");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of Adult members provided entrepreneurship and business management training");
        indicator.setMerCode("STABLE");
        return indicator;
    }


    public Indicator getIndicatorForNumberOfAdultMembersReferredForHivSupportGroup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgrefhgrprp");
        indicator.setIndicatorName("Number of adult household members referred for HIV support group within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of adult household members referred for HIV support group within the report period");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersReferredForPMTCT()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgrefpmtcrp");
        indicator.setIndicatorName("Number of adult household members referred for PMTCT within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of adult household members referred for PMTCT within the report period");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersReferredForPEP()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgrefpeprpe");
        indicator.setIndicatorName("Number of adult members referred for PEP within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of adult members referred for PEP within the report period");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedAgeAppropriateHIVTreatmentLiteracyService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivtrmlit");
        indicator.setIndicatorName("Number of children provided with age appropriate HIV treatment literacy (CLHIV) ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of children provided with age appropriate HIV treatment literacy (CLHIV)");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedHIVAdherenceSupportGroupService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivadhsup");
        indicator.setIndicatorName("Number of children provided with HIV Adherence support");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of children provided with HIV Adherence support");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedStructuredPLHASupportGroupService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcplhasupgp");
        indicator.setIndicatorName("Number of children enrolled in structured PLHA Support group");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of children enrolled in structured PLHA Support group ");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedAgeAppropriateCounsellingAndHIVDisclosureSupportService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccdisclose");
        indicator.setIndicatorName("Number of children provided with age appropriate counseling and HIV disclosure support service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of children provided with age appropriate counseling and HIV disclosure support service");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedReferralForHIVRelatedTestingService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefhivtst");
        indicator.setMerCode("OVC_HTSLINK");
        indicator.setIndicatorName("Number of children who completed a referral for or was facilitated to obtain HIV related testing ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of children who completed a referral for or was facilitated to obtain HIV related testing ("+indicator.getMerCode()+")");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAssessedForHivRiskReferralForHIVRelatedTestingWhoTestedPositive()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrskrefhiv");
        indicator.setMerCode("OVC_HTSLINK");
        indicator.setIndicatorName("Number of OVC assessed for HIV Risk, referred for HIV related testing and tested positive ("+indicator.getMerCode()+")");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This counts the number of children assessed for HIV risk, referred for HIV testing and tested positive within the report period. This is a subset of OVC_HTSLINK that tested Positive");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersProvidedReferralForHIVRelatedTestingService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgrefhivtst");
        indicator.setMerCode("OVC_HTSLINK");
        indicator.setIndicatorName("Number of caregivers/adults who completed a referral for or was facilitated to obtain HIV related testing ("+indicator.getMerCode()+")");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of caregivers/adults who completed a referral for or was facilitated to obtain HIV related testing ("+indicator.getMerCode()+")");
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedReferralForHIVOrOrpotunisticInfectionService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcoppinfref");
        indicator.setIndicatorName("Number of children who completed a referral or was facilitated to obtain HIV (or related opportunistic infections) treatment and care");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of children who completed a referral or was facilitated to obtain HIV (or related opportunistic infections) treatment and care");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForPEP()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefpeprpe");
        indicator.setIndicatorName("Number of OVC referred for PEP within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC referred for PEP within the report period");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
       
    public Indicator getIndicatorForTrackedDevelopmentalMilestoneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcdevmilest");
        indicator.setIndicatorName("Number of HIV infected, HEU & infected infants and young children regularly tracked for development milestones");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of HIV infected, HEU & infected infants and young children regularly tracked for development milestones");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForHIVPreventionSupportService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivprevrp");
        indicator.setIndicatorName("Number of OVC who completed a referral or was facilitated to obtain age appropriate HIV prevention support");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC who completed a referral or was facilitated to obtain age appropriate HIV prevention support");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedSubstanceAbuseSupportService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcsubsabuse");
        indicator.setIndicatorName("Number of OVC who completed a referral or was facilitated to obtain substance abuse support by a trained provider");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC who completed a referral or was facilitated to obtain substance abuse support by a trained provider");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedAgeAppropriateWomenHealthService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcwomenhlth");
        indicator.setIndicatorName("Number of OVC who completed a referral or was facilitated to obtain age appropriate women’s health");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC who completed a referral or was facilitated to obtain age appropriate women’s health");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedPerinatalCareService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcperinatal");
        indicator.setIndicatorName("Number of OVC who completed a referral or was facilitated to obtain perinatal care including PMTCT");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC who completed a referral or was facilitated to obtain perinatal care including PMTCT");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEmergencyTransportAssistanceService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcemergtran");
        indicator.setIndicatorName("Number of OVC provided emergency transport assistance");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided emergency transport assistance");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEarlyChildStimulationService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcstimulatn");
        indicator.setIndicatorName("Number of OVC provided early Childhood Stimulation (ECS)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided early Childhood Stimulation (ECS)");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcProvidedMotherBabyCourseServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmbbcourse");
        indicator.setIndicatorName("Number of beneficiaries who participated in Mother Baby Courses(MBC)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of beneficiaries who participated in Mother Baby Courses(MBC) within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedWASHServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcwashmsgrp");
        indicator.setIndicatorName("Number of OVC provided Household hygiene counseling and WASH messaging");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided Household hygiene counseling and WASH messaging");
        indicator.setMerCode("HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcFacilitatedToParticipateInChildRightsSession()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcchildrits");
        indicator.setIndicatorName("Number of OVC facilitated to participate in Child rights Sessions");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC facilitated to participate in Child rights Sessions");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedFamilyConflictMitigationService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcfamconfmg");
        indicator.setIndicatorName("Number of OVC provided Structured psycho-social support related to family conflict mitigation and family relationship");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided Structured psycho-social support related to family conflict mitigation and family relationship");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedCognitiveBehaviorTherapyService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccogbehave");
        indicator.setIndicatorName("Number of OVC provided Cognitive behavior therapy service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided Cognitive behavior therapy service");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcProvidedFamilyGroupConferencingService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcfamgrpcon");
        indicator.setIndicatorName("Number of OVC provided Structured family group conferencing to prevent occurrence/reoccurrence of child abuse, exploitation or neglect");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided Structured family group conferencing to prevent occurrence/reoccurrence of child abuse, exploitation or neglect");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedLegalAssistanceRelatedToMaltreatmentAndGBV()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmgbvlegal");
        indicator.setIndicatorName("Number of OVC provided Legal assistance related to maltreatment, GBV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided Legal assistance related to maltreatment, GBV");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEmergencyShelterAndCareFacilityService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcemergshlt");
        indicator.setIndicatorName("Number of OVC provided Emergency shelter/care facility or kinship care placement and monitoring for children");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided Emergency shelter/care facility or kinship care placement and monitoring for children");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEvidenceBasedParentingService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcevidbpart");
        indicator.setIndicatorName("Number of OVC provided Evidence based parenting service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided Evidence based parenting service");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedBirthRegistrationAcquisitionService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcbirthregn");
        indicator.setIndicatorName("Number of OVC assisted to acquire birth registration");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC assisted to acquire birth registration");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedGBVCommunityDialoguesService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcgbvdialog");
        indicator.setIndicatorName("Number of OVC provided assisted to engage in GBV Community dialogues");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided assisted to engage in GBV Community dialogues");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcSessionsOnChildProtectionSupportService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcprotsessn");
        indicator.setIndicatorName("Number of OVC that had sessions with child protection officer, police or another local child protection authority");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC that had sessions with child protection officer, police or another local child protection authority");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedPostViolenceTraumaSupportService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcpstviocou");
        indicator.setIndicatorName("Number of OVC that had Post violence trauma counselling from a trained provider");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC that had Post violence trauma counselling from a trained provider");
        indicator.setMerCode("SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedSchoolEducationalAssistance()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcschassist");
        indicator.setIndicatorName("Number of OVC provided school educational assistance");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided school educational assistance");
        indicator.setMerCode("SCHOOL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedExamFeeSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcfeesuport");
        indicator.setIndicatorName("Number of OVC provided exam fee support (school & exam fees)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided exam fee support (school & exam fees)");
        indicator.setMerCode("SCHOOL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEducationalSubsidiesPTCE()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcedusubsdy");
        indicator.setIndicatorName("Number of OVC provided education subsidies (PTCE)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided education subsidies (PTCE)");
        indicator.setMerCode("SCHOOL");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcProvidedMentorshipServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmentorser");
        indicator.setIndicatorName("Number of OVC provided mentorship services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided mentorship services");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEntrepreneurshipandBusinessManagementTraining()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcenterptrg");
        indicator.setIndicatorName("Number of OVC provided entrepreneurship and business management training");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided entrepreneurship and business management training");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedFinancialLiteracyTraining()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcfinlitrcy");
        indicator.setIndicatorName("Number of OVC provided financial literacy training");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided financial literacy training");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedVocationalTraining()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcvoctraing");
        indicator.setIndicatorName("Number of OVC provided vocational training services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided vocational training services");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedMarketLinkages()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vclinkedmkt");
        indicator.setIndicatorName("Number of OVC provided market linkages");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided market linkages");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedBusinessSkillsTraining()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcebuskitrg");
        indicator.setIndicatorName("Number of OVC provided business skills training");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided business skills training");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedPassportToSuccess()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcpasstosuc");
        indicator.setIndicatorName("Number of OVC provided passport to Success (PTS)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC provided passport to Success (PTS)");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedSavingsAndInternalLendingCommunity()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vclinkdsilc");
        indicator.setIndicatorName("Number of OVC linked to Savings groups (SILC)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC linked to Savings groups (SILC)");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedLinkagesToFinancialInstitutionsAndPrivateSector()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vclinktofin");
        indicator.setIndicatorName("Number of OVC Linked to financial institutions & private sector");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC Linked to financial institutions & private sector");
        indicator.setMerCode("STABLE");
        return indicator;
    }
    
    
    
    
    public Indicator getIndicatorForNumberOfOvcReferredForMUAC()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefmuacrp");
        indicator.setIndicatorName("Number of OVC referred for MUAC within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC referred for MUAC within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForImmunization()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefimzrpe");
        indicator.setIndicatorName("Number of OVC referred for Immunization within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC referred for Immunization within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForEID()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefeidrpe");
        indicator.setIndicatorName("Number of OVC referred for EID within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC referred for EID within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersReferredForRoutineHealthCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgrefrhcrpe");
        indicator.setIndicatorName("Number of adult members referred for Routine Health Care within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of adult members referred for Routine Health Care within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForRoutineHealthCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefrhcrpe");
        indicator.setIndicatorName("Number of OVC referred for Routine Health Care within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC referred for Routine Health Care within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersReferredForSTITreatment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgrefstirpe");
        indicator.setIndicatorName("Number of adult members referred for STI Treatment within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of adult members referred for STI Treatment within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForSTITreatment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefstirpe");
        indicator.setIndicatorName("Number of OVC referred for STI Treatment within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC referred for STI Treatment within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersReferredForHivTreatmentAndCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgrefhivtrc");
        indicator.setIndicatorName("Number of adult members referred for HIV treatment and care within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of adult members referred for HIV treatment and care within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForHivTreatmentAndCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrefhivtrc");
        indicator.setIndicatorName("Number of OVC referred for HIV treatment and care within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC referred for HIV treatment and care within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersReferred()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgreferedrp");
        indicator.setIndicatorName("Number of adult members referred within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of adult members referred within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferred()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcreferedrp");
        indicator.setIndicatorName("Number of OVC referred within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of OVC referred within the report period");
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHouseholdsWithCasePlan()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhcasepland");
        indicator.setIndicatorName("Number of households with caseplan");
        indicator.setDescription("This indicator counts the Number of households whose caseplan was developed within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Case plan register");
        indicator.setOtherInformation("This indicator is dependent on report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("Number of households with caseplan");
        indicator.setMerCode("Household");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenWithCasePlan()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccasepland");
        indicator.setIndicatorName("Number of children with caseplan");
        
        indicator.setDescription("This indicator counts the Number of children whose caseplan was developed within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Case plan register");
        indicator.setOtherInformation("This indicator is dependent on report period");
        indicator.setDisaggregations("Age, Sex");
        
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("Number of children with caseplan");
        indicator.setMerCode("Household");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesWithCasePlan()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bncasepland");
        indicator.setIndicatorName("Number of beneficiaries with caseplan");
        indicator.setDescription("This indicator counts the Number of children and households with caseplan at the time of report(includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Case plan register");
        indicator.setOtherInformation("This indicator is NOT dependent on report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        indicator.setMerCode("Household");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsWithoutAddress()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhnoaddress");
        indicator.setIndicatorName("Number of households without address");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of households with missing address");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    
    //Vulnerability status
    public Indicator getIndicatorForNumberOfChildrenOrphanedByAIDSEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcorphnaids");
        indicator.setIndicatorName("Number of children orphaned by AIDS enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Number of children orphaned by AIDS enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenAtHeightenedRiskOfHIVInfectionEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcatriskhiv");
        indicator.setIndicatorName("Number of  children at heightened risk of HIV infection enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children at heightened risk of HIV infection enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdolescentFemalesAtRiskOfTransactionalSexEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctranscsex");
        indicator.setIndicatorName("Number of adolescent females at risk of transactional sex enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Number of adolescent females at risk of transactional sex enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenAtRiskOfOrHaveExperiencedSexualViolenceEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcatrisksex");
        indicator.setIndicatorName("Number of  children at risk of or have experienced sexual violence enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the Number of  children at risk of or have experienced sexual violence enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfTeenMothersEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcteenmthen");
        indicator.setIndicatorName("Number of Teen mothers enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of Teen mothers enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdolescentGirlsEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcadolscenr");
        indicator.setIndicatorName("Number of Adolescent girls at risk including teen mothers and out of school AGYW enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of Adolescent girls at risk including teen mothers and out of school AGYW enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfStuntedOrMalnourishedChildrenEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmalnouenr");
        indicator.setIndicatorName("Number of stunted or malnourished children enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of stunted or malnourished enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenEnrolledFromUtraPoorHouseholds()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcultrapohh");
        indicator.setIndicatorName("Number of children enrolled from ultra poor households");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children enrolled from ultra poor households. Source is child enrollment form-Sub population");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenOfHIVPositiveCaregiversEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivposcge");
        indicator.setIndicatorName("Number of children of HIV Positive caregivers enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of HIV Positive caregivers enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenWhoseSubPopulationStatusIsHivPositive()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcvulhivpos");
        indicator.setIndicatorName("Number of children whose sub-population status is Child is HIV+");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children living with HIV enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenLivingWithHIVEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcplhivenrl");
        indicator.setIndicatorName("Number of children living with HIV enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children living with HIV enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVExposedChildrenEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivexpenr");
        indicator.setIndicatorName("Number of HIV exposed children enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of HIV exposed children enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenLivingWithDisabilityEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcdisablden");
        indicator.setIndicatorName("Number of children living with disability enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children living with disability enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAbusedChildrenEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcabusedenr");
        indicator.setIndicatorName("Number of abused children enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of abused children enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfStreetChildrenEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcstreetenr");
        indicator.setIndicatorName("Number of street children enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children living and working on the street enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenOfKeyPopEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vckeypopenr");
        indicator.setIndicatorName("Number of children of key population enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of children of key population enrolled in the program");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoWereEnrolledAsDoubleOrphans()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcdouborpen");
        indicator.setIndicatorName("Number of Double orphans enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of OVC who were enrolled as double orphans");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoWereEnrolledAsPaternalOrphans()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcpatorphen");
        indicator.setIndicatorName("Number of paternal orphans enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of OVC who were enrolled as paternal orphans");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoWereEnrolledAsMaternalOrphans()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmatorphen");
        indicator.setIndicatorName("Number of maternal orphans enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of OVC who were enrolled as maternal orphans");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoWereEnrolledAsOrphans()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcorphanenr");
        indicator.setIndicatorName("Number of Orphans enrolled in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of OVC who were enrolled as orphans");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("ahmcurenrol");
        indicator.setIndicatorName("Number of Caregivers currently enrolled in the program");
        
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of Adult members currently enrolled in the program (excludes those graduated, lost to follow-up, migrated and other losses");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersEnrolledWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("ahmenrollrp");
        indicator.setIndicatorName("Number of new Caregivers enrolled within the report period (OVC_ENROLLED)");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of Adult members enrolled within the report period (includes those graduated, lost to follow-up, migrated and other losses");
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("ahmservedrp");
        indicator.setIndicatorName("Number of Caregivers served within the report period");
        //indicator.setAlternateName("Number of OVC served within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of OVC that are served within the report period (includes those graduated, lost to follow-up, migrated and other losses");
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersWithoutServiceRecords()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("ahmnotserve");
        indicator.setIndicatorName("Number of Caregivers currently enrolled but not served within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfPhysicalAndEmotionalViolenceGBVCases()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("gbvphemovio");
        indicator.setIndicatorName("Physical and/or emotional violence");
        indicator.setDescription("This indicator counts the number of GBV beneficiaries that experienced Physical and/or emotional violence within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("GBV enrollment register");
        indicator.setOtherInformation("This indicator is dependent on report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("GBV_ENROLL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfSexualViolenceGBVCases()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("gbvsexuavio");
        indicator.setIndicatorName("Sexual violence");
        indicator.setDescription("This indicator counts the number of GBV beneficiaries that experienced Sexual violence within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("GBV register");
        indicator.setOtherInformation("This indicator is dependent on report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("GBV_ENROLL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfGBVBeneficiariesProvidedPEP()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("begbvpepser");
        indicator.setIndicatorName("Number of beneficiaries provided Post Exposure Profilaxis (PEP)");
        indicator.setDescription("This indicator counts number of beneficiaries provided Post Exposure Profilaxis (PEP) within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("GBV service registers");
        indicator.setOtherInformation("This indicator is dependent on GBV service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("GBV_ENROLL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesBenefitedFromMotherBabyCourses()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnmotbabycs");
        indicator.setIndicatorName("Mother/Baby courses");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesProvidedParentingOrCaregiverSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnparcgsupp");
        indicator.setIndicatorName("Caregiver support");
        
        indicator.setDescription("This indicator counts the number of beneficiaries that are provided caregiver support service within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesProvidedPartTimeContinuingEducationServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnptcontedu");
        indicator.setIndicatorName("Part time continuing education");
        indicator.setDescription("This indicator counts the number of beneficiaries provided Part time continuing education service (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Child and Caregiver service register");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesProvidedEducationSubsidyServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnedusubsdy");
        indicator.setIndicatorName("Education subsidy");
        indicator.setDescription("This indicator counts the number of beneficiaries provided Education subsidy service (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Child and Caregiver service register");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesProvidedWorkReadinessServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnworkreadn");
        indicator.setIndicatorName("Work readiness");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesProvidedSocialAssetBuildingServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnsocassetb");
        indicator.setIndicatorName("Social asset building");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesReferredFromCBOToFacility()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnrefcbofac");
        indicator.setIndicatorName("Referrals from OVC partners to Health facilities");
        indicator.setDescription("This indicator counts the number of beneficiaries referred from OVC Partners to Health facilities (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Referral register");
        indicator.setOtherInformation("This indicator is dependent on referral service and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesReferredFromFacilityToCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnreffaccbo");
        indicator.setIndicatorName("Referrals from Health facilities to OVC partners");
        indicator.setDescription("This indicator counts the number of beneficiaries referred from Health facilities to OVC Partners (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Referral register");
        indicator.setOtherInformation("This indicator is dependent on referral service and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_REFERRAL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveNotOnTreatmentServed()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnonartser");
        indicator.setIndicatorName("HIV Positive Not on ART and served in report period");
        //indicator.setIndicatorName("Number of HIV unknown OVC (Test not indicated) provided with at least one service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOnTreatmentServed()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivartser");
        indicator.setIndicatorName("HIV Positive on ART and served in report period");
        //indicator.setIndicatorName("Number of HIV unknown OVC (Test not indicated) provided with at least one service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcWithTestNotIndicatedServed()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctniserved");
        indicator.setIndicatorName("Number of OVC Screened and HIV test NOT required and served in report period");
        //indicator.setIndicatorName("Number of HIV unknown OVC (Test not indicated) provided with at least one service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcAtRiskOfHivInfectionServed()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivrskser");
        indicator.setIndicatorName("Number of OVC Screened and HIV test required and served in report period");
        //indicator.setIndicatorName("Number of HIV unknown OVC at Risk of HIV infection provided with at least one service");
        indicator.setAlternateName("At Risk of HIV infection");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVRISK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcNotScreenedForHivRiskServed()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnotscrser");
        indicator.setIndicatorName("Number of OVC Not screened but served within the report period");
        //indicator.setIndicatorName("Number of HIV unknown OVC at Risk of HIV infection provided with at least one service");
        indicator.setAlternateName("Number of OVC not screened for HIV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVRISK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcTestedButDidNotReceivedResultServed()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivrnrser");
        indicator.setIndicatorName("Tested but did not receive result");
        
        indicator.setDescription("This indicator counts the number of beneficiaries tested for HIV but did not receive result (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household member enrollment registers");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWithUndisclosedHivStatusServed()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivundser");
        indicator.setIndicatorName("Caregivers not willing to disclose HIV status");
        indicator.setDescription("This indicator counts the number of beneficiaries with undisclosed HIV status (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household member enrollment registers");
        indicator.setOtherInformation("This indicator is NOT dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedStableServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservstbrp");
        indicator.setIndicatorName("Stable services");
        indicator.setAlternateName("Stable services");
        indicator.setDescription("This indicator counts the number of beneficiaries that are provided any of the services in the STABLE domain within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_STABLE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedSafetyServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservsafrp");
        indicator.setIndicatorName("Safety services");
        indicator.setAlternateName("Safety services");
        indicator.setDescription("This indicator counts the number of beneficiaries that are provided any of the services in the SAFE domain within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SAFE");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedReferralServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservrefrp");
        indicator.setIndicatorName("Referral services");
        indicator.setAlternateName("Referral services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_REF");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedHealthServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservhthrp");
        indicator.setIndicatorName("Health services");
        indicator.setAlternateName("Health services");
        indicator.setDescription("This indicator counts the number of beneficiaries that are provided any of the services in the HEALTH domain within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HEALTH");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedSchoolServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservschrp");
        indicator.setIndicatorName("School services");
        indicator.setAlternateName("School services");
        indicator.setDescription("This indicator counts the number of beneficiaries that are provided any of the services in the SCHOOL domain within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SCHOOL");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdolescentsProvidedHIVPreventionServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivprevrp");
        indicator.setIndicatorName("Number of OVC (aged 9-17) that received adolescent HIV prevention and sexual reproductive health services");
        indicator.setAlternateName("Number of OVC (aged 9-17) that received adolescent HIV prevention and sexual reproductive health services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVPREV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOVC_HIVSTATPOSITIVE()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivstapos");
        indicator.setIndicatorName("OVC_HIVSTAT POSITIVE");
        indicator.setAlternateName("OVC (<18) positive (including known +ve)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOVC_HIVSTATNEGATIVE()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivstaneg");
        indicator.setIndicatorName("OVC_HIVSTAT NEGATIVE");
        indicator.setAlternateName("Reported HIV Negative");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOVC_HIVSTATUNKNOWN()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivstaunk");
        indicator.setIndicatorName("OVC_HIVSTAT UNKNOWN");
        indicator.setAlternateName("No Reported HIV Status");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOVC_HIVTestNotIndicated()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctstnotrqd");
        indicator.setIndicatorName("OVC_HIVSTAT Test not required");
        indicator.setAlternateName("HIV test not required due to Risk assessment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcExitedWithoutGraduation()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcexitnogra");
        indicator.setIndicatorName("Number of OVC exited without graduation");
        indicator.setAlternateName("Exited without Graduation");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcRegularlyAttendingSchool()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcschattend");
        indicator.setIndicatorName("Number of school aged children enrolled in the OVC program who are regularly (defined as not missing more than 2 days in a month of uninterrupted academic or vocational training session) attending school or vocational training within the report period (OVC_EDU)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setAlternateName("OVC school aged (5-17 years) regularly attending school/vocational training");
        indicator.setMerCode("OVC_EDU");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcSelfReportingAdherenceToTreatment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcadherence");
        indicator.setIndicatorName("Number of HIV positive OVC on treatment self-reporting adherence to treatment within the report period (OVC_ART_SUPP)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setAlternateName("OVC reporting adherence to treatment");
        indicator.setMerCode("OVC_ART_SUPP");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcNewlyTestedPositiveAndLinkedToTreatment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnewposart");
        indicator.setMerCode("OVC_TXLINK");
        indicator.setIndicatorName("OVC Newly tested Positive (<18) and linked to treatment in Report Period ("+indicator.getMerCode()+")");
        indicator.setAlternateName("Number of Newly tested HIV positive OVC successfully linked to treatment within the report period (OVC_TXLINK)");
        
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcTestedAndReceivedResult()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctstresult");
        indicator.setMerCode("OVC_HTSLINK");
        indicator.setIndicatorName("Number of OVC 0-17 years who got tested and received result within the report period ("+indicator.getMerCode()+")");
        indicator.setAlternateName("Tested and received result within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcThatShowedAtleastOneScoreImprovement()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vc1scorimpr");
        indicator.setIndicatorName("Number of OVC that showed at least one score improvement in any VC service area");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
   
    public Indicator getIndicatorForNumberOfOvcWithdrawn()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcwithdrawn");
        indicator.setIndicatorName("Number of <b>OVC Withdrawn</b> from the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcCurrentlyInSchool()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccurrinsch");
        indicator.setIndicatorName("Number of OVC aged (5-17 years) currently in the program who are in school");
        indicator.setAlternateName("OVC school aged (5-17 years) in the program");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_EDU");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcOutOfSchool()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnotschool");
        indicator.setIndicatorName("Number of OVC aged (5-17 years) currently in the program who are out of school");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_EDU");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcProvidedThreeOrMoreServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("gt3services");
        indicator.setIndicatorName("Number of OVC that received three or more services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedLessThanThreeServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("lt3services");
        indicator.setIndicatorName("Number of OVC that received less than three services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcFollowedUp");
        indicator.setIndicatorName("Number of OVC followed up");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcNotFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcNotFollowedUp");
        indicator.setIndicatorName("Number of OVC not followed up");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcAtFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivPositiveOvcFollowedUp");
        indicator.setIndicatorName("Number of HIV positive OVC followed up");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegativeOvcAtFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivNegativeOvcFollowedUp");
        indicator.setIndicatorName("Number of HIV negative OVC followed up");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcAtFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivUnknownOvcFollowedUp");
        indicator.setIndicatorName("Number of HIV unknown OVC followed up");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAssessedForHivRiskAndDeterminedToBeAtRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivatrisk");
        indicator.setIndicatorName("Number of children assessed for HIV risk and determined to be at Risk of HIV (OVC_RISKASSESSED)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_RISKASSESSED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoHadAChangeInRiskProfileSinceLastHivRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivrskprf");
        indicator.setIndicatorName("Number of children who had a change in risk profile since last HIV Risk Assessment(OVC_RISKASSESSED)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_RISKASSESSED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTest()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcatriskref");
        indicator.setIndicatorName("Number of OVC at risk of HIV and referred for HIV test(OVC_HTSLINK)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HTSLINK");
        return indicator;
    }
    /*public Indicator getIndicatorForNumberOfOvcAssessedForHivRiskAndReferredForTesting()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivrskref");
        indicator.setIndicatorName("Numbers of children assessed for HIV risk and referred for testing");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }*/
    public Indicator getIndicatorForNumberOfOvcNeverAssessedForHivRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnashivrsk");
        indicator.setIndicatorName("Numbers of children Never assessed for HIV risk");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    /*public Indicator getIndicatorForNumberOfOvcAssessedForHivRiskWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcasshivrsk");
        indicator.setIndicatorName("Numbers of children assessed for HIV risk within the report period");
        return indicator;
    }*/
    public Indicator getIndicatorForNumberOfCaregiversExitedWithoutGraduation()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgexitnogra");
        indicator.setIndicatorName("Number of Caregivers exited without graduation");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_EDU");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoseCaregiversRefusedToDiscloseTheirHivStatusWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccgrdclhiv");
        indicator.setIndicatorName("Number of OVC whose caregiver refused to Disclose children HIV status within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoseCaregiversKnowTheirHivStatusWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccgknhivst");
        indicator.setIndicatorName("Number of OVC whose caregiver know their HIV status within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcThatParticipatedInRecreationalActivityWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrecactyrp");
        indicator.setIndicatorName("Number of OVC that participated in Recreational activity within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcEnrolledInKidClubWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vckidsclbrp");
        indicator.setIndicatorName("Number of OVC enrolled in Kids club within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcLinkedToGovtForPostViolenceServicesWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vclgovptvrp");
        indicator.setIndicatorName("Number of OVC <18 that have received a completed referral to services[of recognized NGO/Government Entity] for demonstrated and/or documented cases of violence, abuse, exploitation or neglect");
        //indicator.setIndicatorName("Number of OVC with a demonstrated and/or documented case of violence, exploitation or neglect who have been successfully referred to Government of Nigeria Social Welfare and other post-violence and child protection services");
        indicator.setMerCode("OVC_PROTECT");
        indicator.setAlternateName("Number of OVC <18 abused/neglected/exploited successfully linked to a GON Social Welfare and other post-violence and child protection services");
        //indicator.setIndicatorName("Number of OVC linked to Government for Post-Violence Services within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcGraduated()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcgraduated");
        indicator.setIndicatorName("Number of OVC graduated from the program within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWithdrawnDueToKnownDeath()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcknowndeat");
        indicator.setIndicatorName("Number of OVC known to have died within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWithdrawnDueToAgingOut()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("ovcagingout");
        indicator.setIndicatorName("Number of OVC withdrawn from the program (Aging out) within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWithdrawnDueToMigration()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("ovcmigrated");
        indicator.setIndicatorName("Number of OVC migrated within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWithdrawnDueToLostToFollowup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vclosttofup");
        indicator.setIndicatorName("Number of OVC lost to follow up within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcWithdrawnDueToTransfer()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctransferd");
        indicator.setIndicatorName("Number of OVC transfered from the program within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcTransferedToPEPFAR()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctranspepf");
        indicator.setIndicatorName("Number of OVC transfered to PEPFAR programs within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcTransferedToNonPEPFAR()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctranonpep");
        indicator.setIndicatorName("Number of OVC transfered to NON-PEPFAR programs within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcDeclaredInactive()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcinactivrp");
        // and removed from the program within the reporting period
        indicator.setIndicatorName("Number of OVC declared inactive");
        indicator.setDescription("Number of OVC declared inactive within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcVoluntarilyWithdrawnFromTheProgramReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcvolleftrp");
        indicator.setIndicatorName("Number of OVC who voluntarily withdrew from the program within the report period ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of OVC that voluntarily withdrew from the program within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReenrolledIntoTheProgram()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcreenrolrp");
        indicator.setIndicatorName("Number of OVC re-enrolled into the program within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForOVC_ACCPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivaccess");
        indicator.setIndicatorName("Number of active OVC beneficiaries receiving support from PEPFAR OVC programs to access HIV services (OVC_ACC)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForProportionOfHivPosOvcCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivpprpce");
        indicator.setIndicatorName("Proportion of currently enrolled OVC who are HIV positive");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForProportionOfHivNegOvcCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivnprpce");
        indicator.setIndicatorName("Proportion of currently enrolled OVC who are HIV negative");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForProportionOfHivUnkOvcCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivuprpce");
        indicator.setIndicatorName("Proportion of currently enrolled OVC whose HIV status is unknown");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversEnrolledInCaregiverForumWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cginforumrp");
        indicator.setIndicatorName("Number of Caregivers enrolled in Caregiver forum within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversEnrolledInSILCWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgenrsilcrp");
        indicator.setIndicatorName("Number of Caregivers enrolled in SILC within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolledInCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivcencar");
        indicator.setIndicatorName("Number of HIV positive caregivers currently enrolled in care");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolledOnART()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivcenart");
        indicator.setIndicatorName("Number of HIV positive caregivers currently enrolled on ART");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfCaregiversNewlyTestedPositive()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgnewhivpos");
        indicator.setMerCode("OVC_HTSLINK");
        indicator.setIndicatorName("Number of caregivers Newly tested Positive in Reporting Period ("+indicator.getMerCode()+")");
        
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveCaregiversNewlyEnrolledOnARTWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivposart");
        indicator.setMerCode("OVC_TXLINK");
        indicator.setIndicatorName("Number of HIV positive caregivers enrolled on treatment in Reporting Period ("+indicator.getMerCode()+")");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHIVUnknownCaregiversEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivunkeen");
        indicator.setIndicatorName("Number of HIV unknown caregivers ever enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeCaregiversEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivnegeen");
        indicator.setIndicatorName("Number of HIV negative caregivers ever enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveCaregiversEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivposeen");
        indicator.setIndicatorName("Number of HIV positive caregivers ever enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownCaregiversCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivunkcen");
        indicator.setIndicatorName("Number of HIV unknown caregivers currently enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeCaregiversCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivnegcen");
        indicator.setIndicatorName("Number of HIV negative caregivers currently enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cghivposcen");
        indicator.setIndicatorName("Number of HIV positive caregivers currently enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcCurrentlyEnrolledThatHaveBeenAssessedonHIVRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccehivrisk");
        indicator.setIndicatorName("Number of OVC currently enrolled that has HIV Risk assessment done");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcEverEnrolledThatHaveBeenAssessedonHIVRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vceehivrisk");
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorName("Number of OVC ever enrolled that has HIV Risk assessment done ("+indicator.getMerCode()+")");
        
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getNoOfHivNegativeOvcAssessedForHIVRiskAndServedWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegrskser");
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorName("Number of HIV Negative OVC assessed for HIV and served within the report period("+indicator.getMerCode()+")");
        indicator.setAlternateName("Number of OVC <18 with negative HIV status  risk assessed ("+indicator.getMerCode()+")");
        
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getNoOfHivUnknownOvcAssessedForHIVRiskAndServedWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorId("vcunkrskser");
        indicator.setIndicatorName("Number of HIV unknown OVC assessed for HIV risk and served within the report period("+indicator.getMerCode()+")");
        indicator.setAlternateName("Number of OVC <18 with unknown HIV status  risk assessed("+indicator.getMerCode()+")");
        
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getNoOfOvcAssessedForHIVRiskAndServedWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivrskser");
        indicator.setIndicatorName("Number of OVC assessed for HIV risk and served within the report period");
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getNoOfHivNegativeOvcAssessedForHIVRiskWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegrskass");
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorName("Number of HIV Negative OVC assessed for HIV risk within the report period("+indicator.getMerCode()+")");
        indicator.setAlternateName("Number of OVC <18 with negative HIV status  risk assessed ("+indicator.getMerCode()+")");
        
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getNoOfHivUnknownOvcAssessedForHIVRiskWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorId("vcunkrskass");
        indicator.setIndicatorName("Number of HIV unknown OVC assessed for HIV risk within the report period("+indicator.getMerCode()+")");
        indicator.setAlternateName("Number of OVC <18 with unknown HIV status  risk assessed("+indicator.getMerCode()+")");
        
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
         
    public Indicator getIndicatorForNumberOfHivUnknownOvcEverEnrolledThatHaveBeenAssessedonHIVRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vceeunkrass");
        indicator.setIndicatorName("Number of HIV Unknown OVC ever enrolled assessed for HIV risk");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_RISKASSESSED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegativeOvcEverEnrolledThatHaveBeenAssessedonHIVRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vceenegrass");
        indicator.setIndicatorName("Number of Hiv Negative OVC ever enrolled assessed for HIV risk");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_RISKASSESSED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcAssessedonHIVRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunkrassed");
        indicator.setIndicatorName("Number of HIV Unknown OVC assessed for HIV risk (OVC_RISKASSESSED_UNKNOWN)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_RISKASSESSED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegativeOvcAssessedonHIVRisk()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegrassed");
        indicator.setIndicatorName("Number of Hiv Negative OVC assessed for HIV risk (OVC_RISKASSESSED_NEGATIVE)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_RISKASSESSED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcCurrentlyEnrolledThatHasNoHIVRiskAssessmentRecord()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccenohivra");
        indicator.setIndicatorName("Number of OVC currently enrolled that has no HIV Risk assessment record");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcCurrentlyEnrolledInHouseholdsWithBaselineAssessment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccehhblass");
        indicator.setIndicatorName("Number of Ovc enrolled within the report period and currently in the program whose Households has Baseline Assessment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromVulnerableHouseholdsAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposcevulb");
        indicator.setIndicatorName("Number of HIV positive Ovc currently enrolled whose households were vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromMoreVulnerableHouseholdsAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposcemrvb");
        indicator.setIndicatorName("Number of HIV positive OVC currently enrolled whose households were more vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromMostVulnerableHouseholdsAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposcemovb");
        indicator.setIndicatorName("Number of HIV Positive OVC currently enrolled whose households were most vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromVulnerableHouseholdsAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgposcevulb");
        indicator.setIndicatorName("Number of HIV positive caregivers currently enrolled whose households were vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromMoreVulnerableHouseholdsAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgposcemrvb");
        indicator.setIndicatorName("Number of HIV positive caregivers currently enrolled whose households were more vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromMostVulnerableHouseholdsAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgposcemovb");
        indicator.setIndicatorName("Number of HIV positive caregivers currently enrolled whose households were most vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreVulnerableAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhcevulbase");
        indicator.setIndicatorName("Number of households currently enrolled that are vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreMoreVulnerableAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhcemrvulba");
        indicator.setIndicatorName("Number of households currently enrolled that are more vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreMostVulnerableAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhcemtvulba");
        indicator.setIndicatorName("Number of households currently enrolled that are most vulnerable at baseline");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsProvidedHES()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhdserhesrp");
        indicator.setIndicatorName("Number of households provided economic strengthening services within the reporting period");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsServed()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhdservedrp");
        indicator.setIndicatorName("Number of households served within the reporting period");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvc0to19CurrentlyEnrolledWhoseHouseholdsProvidedEconomicStrengthening()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcce019hhes");
        indicator.setIndicatorName("Number of OVC (0 - 19 years) currently enrolled in the Program whose families have been empowered with Household Economic Strengthening (HES)  Interventions (excludes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvc0to19WhoseHouseholdsProvidedEconomicStrengthening()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcee019hhes");
        indicator.setIndicatorName("Number of OVC (0 - 19 years) enrolled whose families have been empowered with Household Economic Strengthening (HES)  Interventions (includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdolescentsWhoseHouseholdsProvidedEconomicStrengthening()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcadolshhes");
        indicator.setIndicatorName("Number of Adolescents (10 - 19 years) enrolled whose families have been empowered with Household Economic Strengthening (HES)  Interventions (includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdolescentsCurrentlyEnrolledWhoseHouseholdsProvidedEconomicStrengthening()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcceadohhes");
        indicator.setIndicatorName("Number of Adolescents (10 - 19 years) currently enrolled in the Program whose families have been empowered with Household Economic Strengthening (HES)  Interventions (excludes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcEnrolledInCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivincare");
        indicator.setIndicatorName("Number of OVC enrolled in HIV care");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForHIVCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivreferr");
        indicator.setIndicatorName("Number of OVC referred for HIV care");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcEnrolledFromHouseholdsWithChronicallyIllMembers()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivillmem");
        indicator.setIndicatorName("Number of OVC enrolled from households with chronically ill or HIV positive members");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcTestedForHIV()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctestedhiv");
        indicator.setIndicatorName("Number of OVC tested for HIV");
        indicator.setAlternateName("OVC Newly tested in Reporting Period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HTSLINK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcScreenedForTB()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcscrnfortb");
        indicator.setIndicatorName("Number of OVC screened for TB");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcWhoAreOverweightAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcbmi25to29");
        indicator.setIndicatorName("Number of OVC that are over weight (BMI 25 - 29.9) at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcThatAreObesseAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcbmi30to40");
        indicator.setIndicatorName("Number of OVC that are obesse (BMI 30 - 40) at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcThatAreMorbidityObesseAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcwtbmigt40");
        indicator.setIndicatorName("Number of OVC that are morbidity obesse (BMI > 40) at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfSeverelyMalnourishedOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmuaclth11");
        indicator.setIndicatorName("Number of OVC that are severely malnourished (MUAC <11.5cm) at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfModeratelyNourishedOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmua11to12");
        indicator.setIndicatorName("Number of OVC that are moderately nourished (MUAC 11.5cm - 12.5cm) at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfWellNourishedOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmuacgth12");
        indicator.setIndicatorName("Number of OVC that are well nourished (MUAC > 12.5cm) at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfSeverelyMalnourishedOvcCurrently()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccmuaclt11");
        indicator.setMerCode("OVC_NUTRITION");
        indicator.setIndicatorName("Number of OVC that are severely malnourished (MUAC <11.5cm) currently ("+indicator.getMerCode()+")");
        indicator.setAlternateName("Malnourished OVC (<18) ("+indicator.getMerCode()+")");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfSeverelyMalnourishedOvcServedNutritonalServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcsmalnsern");
        indicator.setMerCode("OVC_NUTRITION");
        indicator.setIndicatorName("Number of severely malnourished OVC provided nutritional services ("+indicator.getMerCode()+")");
        indicator.setAlternateName("Malnourished OVC (<18) linked to Nutrition ("+indicator.getMerCode()+")");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfModeratelyNourishedOvcCurrently()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccmuac1112");
        indicator.setIndicatorName("Number of OVC that are moderately nourished (MUAC 11.5cm - 12.5cm) currently");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfWellNourishedOvcCurrently()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccmuacgt12");
        indicator.setIndicatorName("Number of OVC that are well nourished (MUAC > 12.5cm) currently");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcWhoAreMostVulnerableAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mostVulnerableOvc");
        indicator.setIndicatorName("Number of OVC who are most vulnerable at baseline (total CSI below 13)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoAreMoreVulnerableAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("moreVulnerableOvc");
        indicator.setIndicatorName("Number of OVC who are more vulnerable at baseline  (total CSI between 13 and 24)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWhoAreVulnerableAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vulnerableOvc");
        indicator.setIndicatorName("Number of OVC who are vulnerable at baseline  (total CSI above 24)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcVulnerableToHIV_AIDS()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcVulnerableToHiv");
        indicator.setIndicatorName("Number of OVC vulnerable to HIV/AIDS and other adversities");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcSickWithLimitedAccessToHealthCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcSickWithLtdAccessToHealthCare");
        indicator.setIndicatorName("Number of OVC frequently sick status and limited access to health care");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcSickWithNoAccessToHealthCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcSickWithNoAccessToHealthCare");
        indicator.setIndicatorName("Number of OVC frequently sick with no access to health care");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcInChildHeadedHouseholds()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcInChildHeadedHouseholds");
        indicator.setIndicatorName("Number of OVC who are in child-headed households");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    
    public Indicator getIndicatorForNumberOfOvcNewlyEnrolledWithBirthCertificateAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnebslbcrt");
        indicator.setIndicatorName("Number of Ovc newly enrolled within the report period who had birth certificate at baseline(includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcCurrentlyEnrolledWithBirthCertAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccebslbcrt");
        indicator.setIndicatorName("Total Number of Ovc currently enrolled in the program who had birth certificate at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcEverEnrolledWithBirthCertificateAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vceebslbcrt");
        indicator.setIndicatorName("Total number of Ovc ever enrolled in the program who had birth certificate at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedBirthCertWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcserbtctrp");
        indicator.setIndicatorName("Number of Ovc provided birth certificate within the report period(includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getNoOfOvcServedWithinTheReportPeriodThatHasBirthCert()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcceserbcer");
        indicator.setIndicatorName("Number of Ovc currently enrolled served within the report period that has brith certificate");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_BIRTHCERT");
        //% OVC (<18) with a birth certificate
        indicator.setAlternateName("OVC (<18) with Birth Certificate");
        return indicator;
    }
    public Indicator getIndicatorForProportionOfOvcServedWithinTheReportPeriodThatHasBirthCert()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcpropbcert");
        indicator.setIndicatorName("Proportion of OVC (<18) with a birth certificate");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_BIRTHCERT");
        indicator.setAlternateName("Proportion of OVC (<18) with a birth certificate");
        indicator.setNumerator("Number OVC (<18) with a birth certificate");
        indicator.setDenominator("Number of OVC (<18) SERVED in the program");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcCurrentlyEnrolledWithBirthCertificate()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccebthcert");
        indicator.setIndicatorName("Number of Ovc currently enrolled that has birth certificate");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcLessThan18CurrentlyEnrolledWithBirthCertificate()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccelt18cer");
        indicator.setIndicatorName("Number of OVC less than 18 years currently enrolled that has birth certificate");
        indicator.setMerCode("OVC_BIRTHCERT");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcEverEnrolledWithBirthCertificate()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vceebthcert");
        indicator.setIndicatorName("Number of Ovc ever enrolled that has birth certificate");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedBirthCertificateServicesAfterEnrollment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcWithBirthCertAfterEnrollmentPerMth");
        indicator.setIndicatorName("Number of Ovc provided birth registration services after enrollment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcWithoutBirthCertificateAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcwithnobir");
        indicator.setIndicatorName("Number of OVC without birth certificate at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcWithoutBirthCertificateCurrently()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcwithnobcc");
        indicator.setIndicatorName("Number of OVC less than 18 years currently enrolled without birth certificate");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    
    public Indicator getIndicatorForNumberOfOvcProvidedLegalServicesAfterEnrollment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcProvidedLegalServicesPerMth");
        indicator.setIndicatorName("Number of Ovc provided legal services after enrollment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWithKnownHIVStatusAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcWithKnownHivStatusAtEnrolledmentPerMth");
        indicator.setIndicatorName("Number of Ovc with known HIV status at enrollment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvc()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccurhivpos");
        indicator.setIndicatorName("Number of HIV positive OVC enrolled within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcIdentifiedAndServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivposser");
        //indicator.setIndicatorName("Number of HIV positive OVC identified and served within the report period");
        indicator.setIndicatorName("Number of OVC Newly tested Positive and served in Reporting Period");
        indicator.setAlternateName("OVC Newly tested Positive (<18) and served in Reporting Period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HTSLINK");
        indicator.setDescription("This counts number of OVC newly tested positive and served within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcNewlyTestedPositiveWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnewhivpos");
        indicator.setMerCode("OVC_HTSLINK");
        //indicator.setIndicatorName("Number of HIV positive OVC identified and served within the report period");
        indicator.setIndicatorName("Number of OVC Newly tested Positive in Reporting Period");
        indicator.setAlternateName("OVC Newly tested Positive (<18) in Reporting Period");
        indicator.setDescription("This counts number of OVC newly tested positive within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAssessedForHivRiskReferredForHivTestAndTestedPositiveWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrskhivpos");
        indicator.setMerCode("OVC_TXLINK");
        //indicator.setIndicatorName("Number of HIV positive OVC identified and served within the report period");
        indicator.setIndicatorName("Number of OVC Newly tested Positive in Reporting Period ("+indicator.getMerCode()+")");
        indicator.setAlternateName("OVC Risk assessed, referred for hiv test and tested Positive (<18) in Reporting Period");
        indicator.setDescription("This counts number of OVC who were risk assessed, referred for HIV test and tested positive within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcEnrolledOnARTWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivposart");
        indicator.setMerCode("OVC_TXLINK");
        //indicator.setIndicatorName("Number of HIV positive OVC identified and served within the report period");
        indicator.setIndicatorName("Number of HIV positive OVC enrolled on treatment in Reporting Period");
        indicator.setAlternateName("OVC HIV Positive OVC (<18) enrolled on treatment in Reporting Period");
        indicator.setDescription("This indicator counts the number of HIV Positive OVC enrolled on treatment within the reporting Period. An OVC is counted even if the child is exited the program after been enrolled on treatment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAssessedForHivRiskReferredForHivTestTestedPositiveAndEnrolledOnARTWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcrskposart");
        indicator.setMerCode("OVC_TXLINK");
        indicator.setIndicatorName("Number of HIV positive OVC enrolled on treatment in Reporting Period ("+indicator.getMerCode()+")");
        indicator.setAlternateName("OVC HIV Positive OVC (<18) enrolled on treatment in Reporting Period");
        indicator.setDescription("This indicator counts the number of OVC assessed for HIV risk, referred for test, tested Positive and enrolled on treatment within the reporting Period. An OVC is counted even if the child is exited from the program after been enrolled on treatment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcIdentifiedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivposide");
        indicator.setIndicatorName("Number of HIV positive OVC identified within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_TXLINK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivPosbas");
        indicator.setIndicatorName("Number of HIV positive OVC identified at enrollment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegativeOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivNegbas");
        indicator.setIndicatorName("Number of HIV negative OVC identified at enrollment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivUnkbas");
        indicator.setIndicatorName("Number of HIV unknown OVC identified at enrollment");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcOutOfSchoolAtEnrollmentPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcOutOfSchoolAtEnrollment");
        indicator.setIndicatorName("Number of Ovc out of school at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcEverEnrolledAndInSchoolAtEnrollmentPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcinschaten");
        indicator.setIndicatorName("Number of Ovc in school at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcCurrentlyAndInSchoolAtEnrollmentPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcceinscenr");
        indicator.setIndicatorName("Number of active Ovc in school at baseline");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcThatDroppedOutOfSchoolPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcThatDroppedOutOfSchool");
        indicator.setIndicatorName("Number of Ovc who dropped out of school");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcServedPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedPerMth");
        indicator.setIndicatorName("Number of Ovc provided atleast one service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeOvcServedPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivNegOvcServedPerMth");
        indicator.setIndicatorName("Number of HIV negative Ovc provided atleast one service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcServedPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivPosOvcServedPerMth");
        indicator.setIndicatorName("Number of HIV positive Ovc provided atleast one service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownOvcServedPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivUnknownOvcServedPerMth");
        indicator.setIndicatorName("Number of HIV unknown Ovc provided atleast one service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcProvidedHIVServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedHivCarePerMth");
        indicator.setIndicatorName("Number of Ovc provided HIV care services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedPsychosocialSupportServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedPsychoPerMth");
        indicator.setIndicatorName("Number of Ovc provided Psychosocial support services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    /*public Indicator getIndicatorForNumberOfOvcProvidedNutritionalServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedNutritionPerMth");
        indicator.setIndicatorName("Number of Ovc provided nutritional services");
        return indicator;
    }*/
    public Indicator getIndicatorForNumberOfOvcProvidedHealthServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedHealthPerMth");
        indicator.setIndicatorName("Number of Ovc provided Health services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEducationalServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedEducPerMth");
        indicator.setIndicatorName("Number of Ovc provided Educational services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedProtectionServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedProtPerMth");
        indicator.setIndicatorName("Number of Ovc provided Protection services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedShelterServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedShelterPerMth");
        indicator.setIndicatorName("Number of Ovc provided Shelter services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEconomicStrengtheningServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcServedEconStrgthPerMth");
        indicator.setIndicatorName("Number of Ovc provided Economic strengthening services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOVCWithUpdatedHIVStatusAtFollowup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcWithUpdatedHivAtFollowup");
        indicator.setIndicatorName("Number of Ovc with updated HIV status at followup");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForHIVPositiveOVCIdentifiedAtAtFollowup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hivPosVcIdentifiedAtFollowup");
        indicator.setIndicatorName("Number of HIV positive Ovc identified at followup");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getNoOfOVCWithUpdatedBirthRegistrationAtFollowup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcWithUpdatedBirthRegAtFollowup");
        indicator.setIndicatorName("Number of Ovc provided birth registeration at followup");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getNumberOfOVCEnrolledInSchoolAtFollowup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcevrinscflp");
        indicator.setIndicatorName("Number of Ovc enrolled in school at followup since inception");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getNumberOfActiveOVCEnrolledInSchoolAtFollowup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccurinscflp");
        indicator.setIndicatorName("Number of active Ovc enrolled in school at followup");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcReferredForServicesPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfOvcReferredPerMth");
        indicator.setIndicatorName("Number of Ovc referred for services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsEverEnrolledWithBaselineAssessment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhdbasasses");
        indicator.setIndicatorName("Number of households ever enrolled with baseline assessment");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithBaselineAssessment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhdcebasass");
        indicator.setIndicatorName("Number of households currently enrolled with baseline assessment");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsWithBaselineAssessmentWithinTheReprtPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhbassesper");
        indicator.setIndicatorName("Number of households with baseline assessment within the report period");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithBaselineAssessmentWithinTheReprtPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhcebassper");
        indicator.setIndicatorName("Number of households currently enrolled with baseline assessment within the report period");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithFollowupAssessment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhcefupasse");
        indicator.setIndicatorName("Number of households currently enrolled with follow up assessment");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsEverEnrolledWithFollowupAssessment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hheefupasse");
        indicator.setIndicatorName("Number of households ever enrolled with follow up assessment");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhdEnrolled");
        indicator.setIndicatorName("Number of households enrolled");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsNewlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhnenrolled");
        indicator.setIndicatorName("Number of Households newly enrolled");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhcenrolled");
        indicator.setIndicatorName("Number of households currently enrolled");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hheenrolled");
        indicator.setIndicatorName("Number of Households ever enrolled");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsEnrolledPerCohort()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHouseholdsEnrolledPerCohort");
        indicator.setIndicatorName("Number of households enrolled in this cohort");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsFollowedupPerCohort()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHouseholdsFollowedUpPerCohort");
        indicator.setIndicatorName("Number of households followed up");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsNotFollowedupPerCohort()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHouseholdsNotFollowedUpPerCohort");
        indicator.setIndicatorName("Number of households not followed up");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversNewlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgnenrolled");
        indicator.setIndicatorName("Number of Caregivers newly enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgcenrolled");
        indicator.setIndicatorName("Number of Caregivers currently enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgeenrolled");
        indicator.setIndicatorName("Number of Caregivers ever enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversGraduated()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cggraduated");
        indicator.setIndicatorName("Number of Caregivers graduated from the program");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversWithdrawnDueToLostToFollowup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cglosttofup");
        indicator.setIndicatorName("Number of Caregivers lost to followup");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversWithdrawnDueToMigration()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgmigrated1");
        indicator.setIndicatorName("Number of Caregivers migrated");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversWithdrawnDueToTransfer()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgtransferd");
        indicator.setIndicatorName("Number of Caregivers transfered to other program");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversWithdrawnDueToKnownDeath()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgknowndeat");
        indicator.setIndicatorName("Number of Caregivers known to have died");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersExitedWithoutGraduation()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgexitnogra");
        indicator.setIndicatorName("Number of Adult members exited without graduation");
        indicator.setAlternateName("Exited without Graduation");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersReenrolledIntoTheProgram()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgreenrolrp");
        indicator.setIndicatorName("Number of Adult members re-enrolled into the program within the reporting period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversEnrolledPerCohort()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfCaregiverEnrolledPerCohort");
        indicator.setIndicatorName("Number of caregivers enrolled");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfCaregiversFollowedUp");
        indicator.setIndicatorName("Number of caregivers followed up");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveCaregiversAtFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivPositiveCaregiversFollowedUp");
        indicator.setIndicatorName("Number of HIV positive Caregivers followed up");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegativeCaregiversAtFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivNegativeCaregiversFollowedUp");
        indicator.setIndicatorName("Number of HIV negative Caregivers followed up");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownCaregiversAtFollowedup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noOfHivUnknownCaregiversFollowedUp");
        indicator.setIndicatorName("Number of HIV unknown Caregivers followed up");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversScreenedForTB()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgscrnfortb");
        indicator.setIndicatorName("Number of Caregivers screened for TB");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversTestedForHiv()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgtesforhiv");
        indicator.setIndicatorName("Number of Caregivers tested for HIV");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedHES()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgservedhes");
        indicator.setIndicatorName("Number of Caregivers provided Economic strengthening services");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    /*public Indicator getIndicatorForNumberOfOvcEnrolledPerMonthByCBO()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcEnrolledPerMth");
        indicator.setIndicatorName("Number of OVC enrolled");
        return indicator;
    }*/
    public Indicator getIndicatorForNumberOfOvcEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("ovcEnrolled");
        indicator.setIndicatorName("Number of OVC enrolled in the cohort");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewOvcEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnewlyEnro");
        indicator.setIndicatorName("Number of new OVC enrolled within the report period");
        indicator.setMerCode("OVC_ENROLLED");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewEnrolledChildrenLivingWithHiv()
    {
        Indicator indicator=Indicator.getInstance();
        //vcclhivnewt database generated id is used here
        indicator.setAutoGeneratedId("CtSjUaWsR6f");
        indicator.setIndicatorId("CtSjUaWsR6f");
        indicator.setIndicatorName("Children Living with HIV (TOTAL)");
        //indicator.setIndicatorName("Number of newly enrolled beneficiaries (Children Living with HIV (TOTAL)) who are receiving services within the reporting period in PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_NEW");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewEnrolledChildrenLivingWithPLHIV()
    {
        Indicator indicator=Indicator.getInstance();
        //vcplhivnewt
        indicator.setAutoGeneratedId("YJ9M2OgimWh");
        indicator.setIndicatorId("YJ9M2OgimWh");
        indicator.setIndicatorName("Children Living with PLHIV (TOTAL)");
        //indicator.setIndicatorName("Number of newly enrolled beneficiaries (Children Living with PLHIV (TOTAL)) who are receiving services within the reporting period in PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_NEW");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewEnrolledChildrenOfKeyPop()
    {
        Indicator indicator=Indicator.getInstance();
        //vckeypopnew
        indicator.setAutoGeneratedId("oedL40sUppl");
        indicator.setIndicatorId("oedL40sUppl");
        indicator.setIndicatorName("Children of Key Populations (TOTAL)");
        //indicator.setIndicatorName("Number of newly enrolled beneficiaries (Children of Key Populations (TOTAL)) who are receiving services within the reporting period in PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_NEW");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewEnrolledHIVExposedInfants()
    {
        Indicator indicator=Indicator.getInstance();
        //vcheinewser
        indicator.setAutoGeneratedId("7GsA4HTMfKd");
        indicator.setIndicatorId("7GsA4HTMfKd");
        indicator.setIndicatorName("HIV Exposed Infant (TOTAL)");
        //indicator.setIndicatorName("Number of newly enrolled beneficiaries (HIV Exposed Infant (TOTAL)) who are receiving services within the reporting period in PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_NEW");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewEnrolledSurvivorsOfViolenceAgainstChildren()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setAutoGeneratedId("Vua0Mu0iPhc");
        indicator.setIndicatorId("Vua0Mu0iPhc");
        indicator.setIndicatorName("Survivors of Violence Against Children (TOTAL) ");
        //indicator.setIndicatorName("Number of newly enrolled beneficiaries (Survivors of Violence Against Children (TOTAL)) who are receiving services within the reporting period in PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_NEW");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewEnrolledAdolescentsAtHighRiskOfHIV()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setAutoGeneratedId("mmgEnWjxA6u");
        indicator.setIndicatorId("vcathivrisk");
        indicator.setIndicatorName("Adolescents at High Risk of HIV (including KP minors, OVC with previous STI, Teen Moms, etc.) (TOTAL)");
        //indicator.setIndicatorName("Number of newly enrolled beneficiaries (Adolescents at High Risk of HIV (including KP minors, OVC with previous STI, Teen Moms, etc.) (TOTAL)) who are receiving services within the reporting period in PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_NEW");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewEnrolledBeneficiaries()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("bnnewlyEnro");
        indicator.setIndicatorName("Number of newly enrolled beneficiaries (OVC, caregivers, or parents) who are receiving services within the reporting period in PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_NEW");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    
    public Indicator getIndicatorForNumberOfChildrenLivingWithHivServedMer()
    {
        Indicator indicator=Indicator.getInstance();
        //vcclhivnewt database generated id is used here
        indicator.setAutoGeneratedId("CtSjUaWsR6f");
        indicator.setIndicatorId("vcclhivsmer");
        indicator.setIndicatorName("Children Living with HIV (TOTAL)");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenLivingWithPLHIVServedMer()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setAutoGeneratedId("YJ9M2OgimWh");
        indicator.setIndicatorId("vcplhivsmer");
        indicator.setIndicatorName("Children Living with PLHIV (TOTAL)");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfChildrenOfKeyPopServedMer()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setAutoGeneratedId("oedL40sUppl");
        indicator.setIndicatorId("vckeypopmer");
        indicator.setIndicatorName("Children of Key Populations (TOTAL)");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVExposedInfantsServedMer()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setAutoGeneratedId("7GsA4HTMfKd");
        indicator.setIndicatorId("vcheisermer");
        indicator.setIndicatorName("HIV Exposed Infant (TOTAL)");
        //indicator.setIndicatorName("Number of beneficiaries (HIV Exposed Infant (TOTAL)) served by PEPFAR OVC programs for children and families affected by HIV");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfSurvivorsOfViolenceAgainstChildrenServedMer()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setAutoGeneratedId("Vua0Mu0iPhc");//L99PhFTdk6J, pyRkx3Sr59I
        indicator.setIndicatorId("vcviosurmer");
        indicator.setIndicatorName("Survivors of Violence Against Children (TOTAL) ");
        //indicator.setIndicatorName("Number of beneficiaries (Survivors of Violence Against Children (TOTAL)) served by PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdolescentsAtHighRiskOfHIVServedMER()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setAutoGeneratedId("mmgEnWjxA6u"); //mmgEnWjxA6u,Kjd0GFDW0Na,uX8awocLeWY
        indicator.setIndicatorId("vchivrskmer");
        indicator.setIndicatorName("Adolescents at High Risk of HIV  (including KP minors, OVC with previous STI, Teen Moms, etc.) (TOTAL)) ");
        //indicator.setIndicatorName("Number of beneficiaries (including KP minors, OVC with previous STI, Teen Moms, etc.) (TOTAL)) served by PEPFAR OVC programs for children and families affected by HIV ");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
      
    public Indicator getIndicatorForNumberOfOvcCurrentlyEnrolled()
    {//currEnro031
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccenrolled");
        indicator.setIndicatorName("Number of OVC currently enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcevenroled");
        indicator.setIndicatorName("Number of OVC ever enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hivPosbasel");
        indicator.setIndicatorName("Number of OVC newly enrolled whose baseline HIV status is positive (includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hivNegbasel");
        indicator.setIndicatorName("Number of OVC newly enrolled whose baseline HIV status is negative (includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownOvcAtBaseline()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hivUnkbasel");
        indicator.setIndicatorName("Number of OVC newly enrolled whose baseline HIV status is unknown (includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHIVPositiveOvcIdentifiedWithinTheReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivPosIdf");
        indicator.setIndicatorName("Number of HIV positive OVC identified within the reporting period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeOvcIdentifiedWithinTheReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivNegIdf");
        indicator.setIndicatorName("Number of HIV Negative OVC identified within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownOvcIdentifiedWithinTheReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivunkIdf");
        indicator.setIndicatorName("Number of HIV unknown OVC identified within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hivPosEnrol");
        indicator.setIndicatorName("Number of HIV positive OVC currently enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeOvcCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hivNegEnrol");
        indicator.setIndicatorName("Number of HIV negative OVC currently enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownOvcCurrentlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hivUnkEnrol");
        indicator.setIndicatorName("Number of HIV unknown OVC currently enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivposeen");
        indicator.setIndicatorName("Number of HIV positive OVC ever enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeOvcEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivnegeen");
        indicator.setIndicatorName("Number of HIV negative OVC ever enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownOvcEverEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivunkeen");
        indicator.setIndicatorName("Number of HIV unknown OVC ever enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHIVPositiveOvcNewlyEnrolledInCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnewInCare");
        indicator.setIndicatorName("Number of HIV positive OVC newly enrolled in care");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolledInCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccurInCare");
        indicator.setIndicatorName("Number of HIV positive OVC currently enrolled in care");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_TXLINK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcEverEnrolledInCare()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vceveInCare");
        indicator.setIndicatorName("Number of HIV positive OVC ever enrolled that are enrolled in care");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_TXLINK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolledOnART()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccureOnArt");
        indicator.setIndicatorName("Number of HIV positive OVC currently enrolled that are enrolled on ART within the report period");
        indicator.setAlternateName("Active OVC Currently receiving ART");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcEverEnrolledOnART()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vceverOnArt");
        indicator.setIndicatorName("Number of HIV positive OVC ever enrolled that are enrolled on ART within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_TXLINK");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVPositiveOvcEnrolledAndAreStillInProgram()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivPosinp");
        indicator.setIndicatorName("Number of OVC enrolled within the report period whose current HIV status is positive and are still in the program (excludes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeOvcEnrolledAndAreStillInProgram()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivNeginp");
        indicator.setIndicatorName("Number of OVC enrolled within the report period whose current HIV status is negative and are still in the program (excludes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownOvcEnrolledAndAreStillInProgram()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivUnkinp");
        indicator.setIndicatorName("Number of OVC enrolled within the report period whose current HIV status is unknown and are still in the program (excludes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHIVPositiveOvcNewlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivPosnEn");
        indicator.setIndicatorName("Number of OVC newly enrolled whose current HIV status is positive (includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVNegativeOvcNewlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivNegnEn");
        indicator.setIndicatorName("Number of OVC newly enrolled whose current HIV status is negative (includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHIVUnknownOvcNewlyEnrolled()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vchivUnknEn");
        indicator.setIndicatorName("Number of OVC newly enrolled whose current HIV status is unknown (includes those out of the program)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOVCCurrentlyEnrolledAndServedHTCWhoseCurrentHIVStatusIsUnknown()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("nhivunkhtc");
        indicator.setIndicatorName("Number of OVC provided HTC services whose current HIV status is unknown");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservedrpe");
        indicator.setIndicatorName("Number of OVC served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of beneficiaries that are served within the report period (includes active, graduated,re-enrolled, lost to follow-up, migrated and other losses)");
        indicator.setSource("Children and Adult household members service registers");
        indicator.setOtherInformation("This indicator is dependent on service provision and report period");
        indicator.setDisaggregations("Age, Sex");
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfBeneficiariesNewlyEnrolledWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccgnenserv");
        indicator.setIndicatorName("Number of beneficiaries newly enrolled and served within the report period (OVC_ENROLLED)");
        indicator.setAlternateName("New beneficiaries enrolled");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_ENROLLED");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewOvcEnrolledAndServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnenservrp");
        indicator.setIndicatorName("Number of OVC newly enrolled and served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcCurrentlyEnrolledAndServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vccurenserv");
        //indicator.setIndicatorName("Number of OVC currently enrolled and served within the report period ");
        indicator.setIndicatorName("OVC_SERV(Active)");
        indicator.setAlternateName("OVC_SERV(Active)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setDescription("This indicator counts the number of OVC that are currently in the program and are served within the report period (excludes those graduated, lost to follow-up, migrated and other losses)");
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcGraduatedButServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcgradserve");
        indicator.setIndicatorName("OVC_SERV(Graduated)");
        //indicator.setIndicatorName("Number of OVC graduated but served within the report period ");
        indicator.setAlternateName("OVC_SERV(Graduated)");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        indicator.setDescription("This indicator counts the number of OVC that are graduated out of the program and but served within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcLostToFollowupButServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcltfuserve");
        indicator.setIndicatorName("Number of OVC Lost to follow-up but served within the report period ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        indicator.setDescription("This indicator counts the number of OVC that were declared lost to follow-up but served within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcMigratedButServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcmigrserve");
        indicator.setIndicatorName("Number of OVC migrated but served within the report period ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        indicator.setDescription("This indicator counts the number of OVC that are recorded to have migrated out of the program but served within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAgedoutButServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcageoserve");
        indicator.setIndicatorName("Number of OVC Aged out but served within the report period ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        indicator.setDescription("This indicator counts the number of OVC that are withdrawn from the program due to age above 17 years but were served within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcTransferedOutButServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctranserve");
        indicator.setIndicatorName("Number of OVC transfered out but served within the report period ");
        indicator.setAlternateName("Transfered");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        indicator.setDescription("This indicator counts the number of OVC that are transfered out of the program but served within the report period");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcDiedButServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcdeadserve");
        indicator.setIndicatorName("Number of OVC known to  have died but served within the report period ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        indicator.setDescription("This indicator counts the number of OVC that are graduated out of the program and but served within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcInactiveButServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcinactserv");
        indicator.setIndicatorName("Number of Inactive OVC served within the report period ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        indicator.setDescription("This indicator counts the number of OVC that are declared inactive but served within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcVoluntarilyWithdrawnButServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcvolwserve");
        indicator.setIndicatorName("Number of OVC who voluntarily withdrew from the program but served within the report period ");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_SERV");
        indicator.setDescription("This indicator counts the number of OVC that voluntarily withdrew from the program but served within the report period");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHivPositiveOvcProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("posvcserved");
        indicator.setIndicatorName("Number of HIV positive OVC provided with at least one service");
        indicator.setMerCode("OVC_HIVSTAT");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcEnrolledOnARTProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcartserved");
        indicator.setIndicatorName("Number of OVC enrolled on ART and provided with at least one service within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }//
    public Indicator getIndicatorForNumberOfHivNegativeOvcProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("negvcserved");
        indicator.setIndicatorName("Number of HIV negative OVC provided with at least one service");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("unkvcserved");
        indicator.setIndicatorName("HIV status unknown served");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHivPositiveOvcCurrentlyEnrolledProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposceserv");
        indicator.setIndicatorName("Number of HIV positive OVC currently enrolled and served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegativeOvcCurrentlyEnrolledProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegceserv");
        indicator.setIndicatorName("HIV negative OVC served");
        //indicator.setIndicatorName("Number of HIV negative OVC currently enrolled and served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcTNIGraduatedAndServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctnigrdser");
        indicator.setIndicatorName("Number of HIV unknown OVC (Test not indicated) graduated and served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcTNICurrentlyEnrolledAndServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vctniceserv");
        indicator.setIndicatorName("Number of HIV unknown OVC (Test not indicated) currently enrolled and served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcCurrentlyEnrolledProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunkceserv");
        indicator.setIndicatorName("Number of HIV unknown OVC currently enrolled and served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcServedButGraduatedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcpossergrd");
        indicator.setIndicatorName("Number of HIV Positive OVC served and graduated within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegativeOvcServedButGraduatedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegsergrd");
        indicator.setIndicatorName("Number of HIV Negative OVC served and graduated within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOvcServedButGraduatedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunksergrd");
        indicator.setIndicatorName("Number of HIV unknown OVC served and graduated within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcOnARTCurrentlyEnrolledProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcartceserv");
        //indicator.setIndicatorName("Number of HIV positive OVC currently enrolled who are on treatment and served within the report period");
        indicator.setIndicatorName("HIV positive OVC on treatment");
        indicator.setAlternateName("Active  HIV+ OVC currently receiving ART");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcNotOnARTCurrentlyEnrolledProvidedWithAtleastOneService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnoartserv");
        //indicator.setIndicatorName("Number of HIV positive OVC currently enrolled who are on treatment and served within the report period");
        indicator.setIndicatorName("HIV positive OVC not on Treatment");
        indicator.setAlternateName("Active  HIV+ OVC currently receiving ART");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPositiveOvcOnARTServedButGraduatedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcartsergrd");
        indicator.setIndicatorName("Number of HIV Positive OVC who are on Treatment but served and graduated within the report period");
        indicator.setAlternateName("Graduated but currently receiving ART");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcServedThatWereHivPositiveAtTheEndOfTheReportReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposrpserv");
        indicator.setIndicatorName("Number of OVC provided with at least one service that were  HIV positive at the end of the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcServedThatWereHivNegativeAtTheEndOfTheReportReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegrpserv");
        indicator.setIndicatorName("Number of OVC provided with at least one service that were  HIV negative at the end of the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcServedThatWereHivUnknownAtTheEndOfTheReportReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcuknrpserv");
        indicator.setIndicatorName("Number of OVC provided with at least one service that were  HIV unknown at the end of the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcProvidedPsychosocialSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservedpsy");
        indicator.setIndicatorName("Number of OVC provided Psychosocial services");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedNutritionalSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservednut");
        indicator.setIndicatorName("Number of OVC provided Nutritional services");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedHealthService()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservedhlt");
        indicator.setIndicatorName("Number of OVC provided Health services");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedEducationalSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservededu");
        indicator.setIndicatorName("Number of OVC provided Educational services");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedProtectionServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservedpro");
        indicator.setIndicatorName("Number of OVC provided Protection services");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcProvidedShelterAndCareServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcservedshe");
        indicator.setIndicatorName("Number of OVC provided Shelter and care services");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    
    
    public Indicator getIndicatorForNumberOfHivPosOvcProvidedNutritionalSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcpossernut");
        indicator.setIndicatorName("Number of HIV Positive OVC provided Nutritional services");
        indicator.setMerCode("OVC_HIVSTAT");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegOvcProvidedNutritionalSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegsernut");
        indicator.setIndicatorName("Number of HIV Negative OVC provided Nutritional services");
        indicator.setMerCode("OVC_HIVSTAT");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknOvcProvidedNutritionalSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunksernut");
        indicator.setIndicatorName("Number of HIV unknown OVC provided Nutritional services");
        indicator.setMerCode("OVC_HIVSTAT");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPosOvcProvidedPsychosocialServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposserpsy");
        indicator.setIndicatorName("Number of HIV Positive OVC provided Psychosocial services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegOvcProvidedPsychosocialServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegserpsy");
        indicator.setIndicatorName("Number of HIV Negative OVC provided Psychosocial services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknOvcProvidedPsychosocialServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunkserpsy");
        indicator.setIndicatorName("Number of HIV unknown OVC provided Psychosocial services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivPosOvcProvidedHealthServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposserhlt");
        indicator.setIndicatorName("Number of HIV Positive OVC provided Health services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegOvcProvidedHealthServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegserhlt");
        indicator.setIndicatorName("Number of HIV Negative OVC provided Health services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknOvcProvidedHealthServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunkserhlt");
        indicator.setIndicatorName("Number of HIV unknown OVC provided Health services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHivPosOvcProvidedEducationalServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposseredu");
        indicator.setIndicatorName("Number of HIV Positive OVC provided Educational services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegOvcProvidedEducationalServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegseredu");
        indicator.setIndicatorName("Number of HIV Negative OVC provided Educational services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknOvcProvidedEducationalServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunkseredu");
        indicator.setIndicatorName("Number of HIV unknown OVC provided Educational services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
       
    public Indicator getIndicatorForNumberOfHivPosOvcProvidedProtectionServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposserpro");
        indicator.setIndicatorName("Number of HIV Positive OVC provided Protection services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegOvcProvidedProtectionServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegserpro");
        indicator.setIndicatorName("Number of HIV Negative OVC provided Protection services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknOvcProvidedProtectionServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunkserpro");
        indicator.setIndicatorName("Number of HIV unknown OVC provided Protection services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHivPosOvcProvidedShelterAndCareServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcpossersht");
        indicator.setIndicatorName("Number of HIV Positive OVC provided Shelter and care services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegOvcProvidedShelterAndCareServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegsersht");
        indicator.setIndicatorName("Number of HIV Negative OVC provided Shelter and care services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknOvcProvidedShelterAndCareServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunksersht");
        indicator.setIndicatorName("Number of HIV unknown OVC provided Shelter and care services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHivPosOvcProvidedEconomicStrengtheningServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcposserecs");
        indicator.setIndicatorName("Number of HIV Positive OVC provided Economic Strengthening services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivNegOvcProvidedEconomicStrengtheningServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnegserecs");
        indicator.setIndicatorName("Number of HIV Negative OVC provided Economic Strengthening services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknOvcProvidedEconomicStrengtheningServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunkserecs");
        indicator.setIndicatorName("Number of HIV unknown OVC provided Economic Strengthening services");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfOvcWithoutServiceRecords()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcnotserved");
        indicator.setIndicatorName("Number of OVC currently enrolled but not served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfNewOvcWithoutServiceRecords()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("newnotserve");
        indicator.setIndicatorName("Number of OVC newly enrolled without service records within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversWithoutServiceRecords()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgnotserved");
        indicator.setIndicatorName("Number of Caregivers currently enrolled without service records within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedSILCSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgservesilc");
        indicator.setIndicatorName("Number of Caregivers provided Savings and Internal Lending Community (SILC) support");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedMicrofinanceSupport()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgservemfin");
        indicator.setIndicatorName("Number of Caregivers provided microfinace support");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversProvidedEconomicStrenghteningServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgserveecon");
        indicator.setIndicatorName("Number of Caregivers provided economic strengthening services");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgiverserve");
        indicator.setIndicatorName("Number of Caregivers served within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }//cgnenrserrp
    public Indicator getIndicatorForNumberOfNewlyEnrolledCaregiversServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgnenrserrp");
        indicator.setIndicatorName("Number of Caregivers newly enrolled and served within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodByAge()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgactsrpage");
        indicator.setIndicatorName("Number of Caregivers currently enrolled and served within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveCaregiversServedWithinDatimReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgactiserdp");
        indicator.setIndicatorName("Number of active Caregivers served within the report period (DATIM)");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndGraduatedWithinDatimReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsergraddp");
        indicator.setIndicatorName("Number of Caregivers served but graduated within the report period (DATIM)");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndTransferedWithinDatimReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsertrandp");
        indicator.setIndicatorName("Number of Caregivers served and transfered within the report period (DATIM)");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndExitedWithinDatimReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgserexitdp");
        indicator.setIndicatorName("Number of Caregivers served and exited within the report period (DATIM)");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgactiserrp");
        indicator.setIndicatorName("Number of Caregivers currently enrolled and served within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndGraduatedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsergradrp");
        indicator.setIndicatorName("Number of Caregivers served but graduated within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndGraduatedWithinTheReportPeriodByAge()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsgrdrpage");
        indicator.setIndicatorName("Number of Caregivers served but graduated within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfInactiveCaregiversServedInReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cginactserv");
        indicator.setIndicatorName("Number of Inactive Caregivers served within the report period ");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setDescription("This indicator counts the number of Caregivers that are declared inactive but served within the report period");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndLostToFollowupWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgserltfurp");
        indicator.setIndicatorName("Number of Caregivers served but lost to follow up within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndDiedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgserdiedrp");
        indicator.setIndicatorName("Number of Caregivers served but died within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndMigratedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsermigrrp");
        indicator.setIndicatorName("Number of Caregivers served but migrated within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndTransferedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgsertranrp");
        indicator.setIndicatorName("Number of Caregivers served but transfered within the report period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversSupportedToAccessHIVServices()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgivehivacc");
        indicator.setIndicatorName("Number of caregivers/household heads supported to access HIV services");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversDeclaredInactive()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cginactivrp");
        indicator.setIndicatorName("Number of Caregivers declared inactive and removed from the program within the reporting period");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfGraduatedHouseholdsWhoseOvcWereServedWithinReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhgdvcserrp");
        indicator.setIndicatorName("Number of Graduated Households whose OVC where served within the report period");
        indicator.setAlternateName("Number of Households Graduated");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        indicator.setMerCode("OVC_HHGRAD");
        return indicator;
    }
    public Indicator getIndicatorNumberOfHouseholdsWhoseOvcWereServedWithinReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhvcserverp");
        indicator.setIndicatorName("Number of Households whose OVC where served within the report period");
        indicator.setAlternateName("Number of Households served");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        indicator.setMerCode("OVC_HHGRAD");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfGraduatedHouseholdsWhoseBeneficiariesWereServedWithinReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhgdbnserrp");
        indicator.setIndicatorName("Number of Graduated Households whose Beneficiaries where served within the report period");
        indicator.setAlternateName("Number of Households Graduated");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        indicator.setMerCode("OVC_HHGRAD");
        return indicator;
    }
    public Indicator getIndicatorNumberOfHouseholdsWhoseBeneficiariesWereServedWithinReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhbnserverp");
        indicator.setIndicatorName("Number of Households whose beneficiaries where served within the report period");
        indicator.setAlternateName("Number of Households served");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        indicator.setMerCode("OVC_HHGRAD");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsThatCanSolveEmergencyNeedsWithinReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhemergneed");
        indicator.setMerCode("OVC_ECONS");
        indicator.setIndicatorName("Number of active HHs who have access to at least one source of income (asset generating income, autonomous income, remittances, etc) and able to meet all households needs/expenses ("+indicator.getMerCode()+")");
        indicator.setAlternateName("Number of active HHs who have access to at least one source of income (asset generating income, autonomous income, remittances, etc) and able to meet all households needs/expenses ("+indicator.getMerCode()+")");
        
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsServedAndAssessedOnAbilityToMeetEmergencyNeedsWithinReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhassemergn");
        indicator.setMerCode("OVC_ECONS");
        indicator.setIndicatorName("Total number of active HHs served and assessed on household`s ability to meet important expenses (household’s economic resilience) within the reporting period ("+indicator.getMerCode()+")");
        indicator.setAlternateName("Total number of active HHs served and assessed on household`s ability to meet important expenses (household’s economic resilience) within the reporting period ("+indicator.getMerCode()+")");
        
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForTotalNumberOfHouseholdsWithdrawnForTheReportingPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhwithdrawn");
        indicator.setIndicatorName("Number of households withdrawn from the program");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsWithdrawnDueToGraduation()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhgraduated");
        indicator.setIndicatorName("Number of households graduated from the program");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    
    public Indicator getIndicatorForNumberOfHouseholdsWithdrawnDueToMigration()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhmigrated1");
        indicator.setIndicatorName("Number of households migrated");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsWithdrawnDueToLostToFollowup()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhlosttofup");
        indicator.setIndicatorName("Number of households lost to follow up");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsWithdrawnDueToTransfer()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhtransferd");
        indicator.setIndicatorName("Number of households transfered from the program (transfer)");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHouseholdsDeclaredInactive()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("hhinactivrp");
        indicator.setIndicatorName("Number of Households declared inactive and removed from the program within the reporting period");
        indicator.setIndicatorType(AppConstant.HOUSEHOLD_TYPE);
        return indicator;
    }
     //DQA indicators
    public Indicator getIndicatorForNumberOfOvcWithNoDocumentedBirthRegistrationStatus()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("nobthregdoc");
        indicator.setIndicatorName("Number of OVC with no documented birth registration status");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcWithNoDocumentedSchoolStatus()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("noschstadoc");
        indicator.setIndicatorName("Number of OVC with no documented school status");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfAdultMembersSelfReportingAdherenceToTreatment()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mrcgartsupp");
        indicator.setIndicatorName("Number of HIV positive Caregivers on treatment self-reporting adherence to treatment within the report period (OVC_ARTSUPP)");
        indicator.setMerCode("OVC_ARTSUPP");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getOvc_NUTRITIONIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mrovnutritn");
        indicator.setIndicatorName("Number of malnourished children provided nutrition services (OVC_NUTRITION)");
        indicator.setMerCode("OVC_NUTRITION");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfOvcAbusedWithinReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcabusedrpe");
        indicator.setIndicatorName("Number of OVC <18 years with a demonstrated and/or documented case of violence, exploitation or neglect within the report period");
        indicator.setMerCode("OVC_PROTECT");
        indicator.setAlternateName("Number of OVC <18 abused/neglected/exploited within the report period");
        //indicator.setIndicatorName("Number of OVC linked to Government for Post-Violence Services within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfHivUnknownOrNegativeOvcServedWithinTheReportPeriod()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("vcunknegser");
        indicator.setIndicatorName("Number of HIV unknown or HIV negative OVC served within the report period");
        indicator.setAlternateName("Number of HIV unknown or HIV negative OVC served within the report period");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        indicator.setMerCode("OVC_HIVSTAT");
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversServedAndTransferedWithinTheReportPeriodByAge()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgstrarpage");
        indicator.setIndicatorName("Number of Caregivers served but transfered within the report period (by age)");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        return indicator;
    }
    public Indicator getIndicatorForNumberOfCaregiversExitedWithoutGraduationByAge()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("cgexnograge");
        indicator.setIndicatorName("Number of Caregivers exited without graduation (by age)");
        indicator.setIndicatorType(AppConstant.CAREGIVER_TYPE);
        indicator.setMerCode("OVC_SERV");
        return indicator;
    }
    public Indicator getOVC_ART_OFFERIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mervcartoff");
        indicator.setIndicatorName("OVC_ART_OFFER");
        indicator.setDescription("This indicator counts the number of children and adolescents on ART in PEPFAR clinical settings offered enrollment into the OVC program");
        indicator.setMerCode("OVC_ART_OFFER");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getOVC_ART_ENROLLIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mervcartenr");
        indicator.setIndicatorName("OVC_ART_ENROLL");
        indicator.setMerCode("OVC_ART_ENROLL");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getOvc_EDUIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("merovceduin");
        indicator.setIndicatorName("Number of OVC assessed for educational performance (OVC_EDU)");
        indicator.setMerCode("OVC_EDU");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    //Custom indicators definition
    public Indicator getOvc_NEWIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("merovcnewrp");
        indicator.setIndicatorName("OVC_NEW");
        indicator.setMerCode("OVC_NEW");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getOvc_ENROLLEDIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("merovcenrol");
        indicator.setIndicatorName("OVC_ENROLLED");
        indicator.setMerCode("OVC_ENROLLED");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_SERVIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("merovcservn");
        indicator.setIndicatorName("OVC_SERV");
        indicator.setMerCode("OVC_SERV");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_HIVSTATIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("merovchivst");
        indicator.setIndicatorName("OVC_HIVSTAT");
        indicator.setMerCode("OVC_HIVSTAT");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_BIRTHCERTIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("movcbircert");
        indicator.setIndicatorName("OVC_BIRTHCERT");
        indicator.setMerCode("OVC_BIRTHCERT");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getOVC_HIVRISKASSESSEDIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("movchivrisk");
        indicator.setIndicatorName("OVC_RISKASSESSED");
        indicator.setMerCode("OVC_RISKASSESSED");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_HTSLINKIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("movchtslink");
        indicator.setIndicatorName("OVC_HTSLINK");
        indicator.setMerCode("OVC_HTSLINK");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_TXLINKIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("merovctxlink");
        indicator.setIndicatorName("OVC_TXLINK");
        indicator.setMerCode("OVC_TXLINK");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_ARTSUPPIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mrovcartsup");
        indicator.setIndicatorName("OVC_ARTSUPP");
        indicator.setMerCode("OVC_ARTSUPP");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getOVC_VL_ELIGIBLEIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mervcvlelig");
        indicator.setIndicatorName("OVC_VL_ELIGIBLE");
        indicator.setMerCode("OVC_VL_ELIGIBLE");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getOVC_VLRIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mervcvlrest");
        indicator.setIndicatorName("OVC_VLR");
        indicator.setMerCode("OVC_VLR");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    //
    public Indicator getOVC_VLSIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mervcvlsupp");
        indicator.setIndicatorName("OVC_VLS");
        indicator.setMerCode("OVC_VLS");
        indicator.setIndicatorType(AppConstant.OVC_TYPE);
        return indicator;
    }
    public Indicator getOvc_ECONSIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("merovcecons");
        indicator.setIndicatorName("OVC_ECONS");
        indicator.setMerCode("OVC_ECONS");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_PROTECTIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("movcprotect");
        indicator.setIndicatorName("OVC_PROTECT");
        indicator.setMerCode("OVC_PROTECT");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_HIVPREVIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("movchivprev");
        indicator.setIndicatorName("OVC_HIVPREV");
        indicator.setMerCode("OVC_HIVPREV");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    public Indicator getOvc_HHGRADIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mrovchhgrad");
        indicator.setIndicatorName("OVC_HHGRAD");
        indicator.setMerCode("OVC_HHGRAD");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }
    
    /*public Indicator getHIVStatNumeratorIndicator()
    {
        Indicator indicator=Indicator.getInstance();
        indicator.setIndicatorId("mrovchhgrad");
        indicator.setIndicatorName("OVC_HHGRAD");
        indicator.setMerCode("OVC_HHGRAD");
        indicator.setIndicatorType(AppConstant.MAIN_INDICATOR_TYPE);
        return indicator;
    }*/
    
}
