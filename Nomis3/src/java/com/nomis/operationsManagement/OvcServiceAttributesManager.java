/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;


import com.nomis.ovc.business.Service;
import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class OvcServiceAttributesManager implements Serializable
{//Adolescent HIV Prevention and SRH services
    public static Service getCounsellingSupportService()
    {
        Service service=new Service();
        service.setDomainName("Psychosocial Support");
        service.setServiceName("Counselling support");
        service.setServiceValue("Counselling support");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        service.setServiceCode("1ps");
        return service;
    }
    public static Service getRecreationalService()
    {
        Service service=new Service();
        service.setDomainName("Safe");
        service.setServiceName("Recreational activity (e.g., kids and youth clubs)");
        service.setServiceValue("Recreational activity (e.g., kids and youth clubs)");
        service.setServiceCode("2ps");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getLifeSkillSupportService()
    {
        Service service=new Service();
        service.setDomainName("Psychosocial Support");
        service.setServiceName("Life skill support");
        service.setServiceValue("Life skill support");
        service.setServiceCode("3ps");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHomeVisitService()
    {
        Service service=new Service();
        service.setDomainName("Psychosocial Support");
        service.setServiceName("Home Visit");
        service.setServiceValue("homevisit");
        service.setServiceCode("4ps");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getNutritionEducationAndCounsellingService()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Nutrition education and counselling");
        service.setServiceValue("Nutrition education and counselling");
        service.setServiceCode("1nu");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFoodAndNutritionalSupplements()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Food and nutritional supplements");
        service.setServiceValue("Food & nutritional supplements");
        service.setServiceCode("2nu");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSevereAcuteMalnutrition()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Severe Acute Malnutrition (SAM)");
        service.setServiceValue("Severe Acute Malnutrition (SAM)");
        service.setServiceCode("15r");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getWastingOrEdema()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Wasting/edema");
        service.setServiceValue("Wasting/edema");
        service.setServiceCode("16r");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getVitaminAZincAndIronSuplement()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Vitamin A-Zinc and Iron suplement");
        service.setServiceValue("Vitamin A, Zinc and Iron suplement");
        service.setServiceCode("3nu");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getNutritionAssessmentCounsellingAndSupport()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Nutrition assessment, counselling and support (NACS)");
        service.setServiceValue("Nutrition assessment, counselling and support (NACS)");
        service.setServiceCode("4nu");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getGrowthMonitoring()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Growth monitoring");
        service.setServiceValue("Growth monitoring");
        service.setServiceCode("5nu");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getAgeAppropriateHIVTreatmentLiteracyService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Age appropriate HIV treatment literacy (CLHIV)");
        service.setServiceValue("Age appropriate HIV treatment literacy (CLHIV)");
        service.setServiceCode("1h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHIVAdherenceSupportGroupService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("HIV adherence support (including transportation support)");
        service.setServiceValue("HIV adherence support (including transportation support)");
        service.setServiceCode("2h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPLHAAdherenceSupportGroupService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Structured PLHA support group");
        service.setServiceValue("Structured PLHA support group");
        service.setServiceCode("27h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getCommunityTBSymptomScreeningService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Community TB symptom screening");
        service.setServiceValue("Community TB symptom screening");
        service.setServiceCode("28h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFoodPackagesOrNutritionSupplements()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Food package(s)/Nutritional supplements");
        service.setServiceValue("Food package(s)/Nutritional supplements");
        service.setServiceCode("29h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getStructuredPLHIVSupportGroupService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Structured PLHIV Support group");
        service.setServiceValue("Structured PLHIV Support group");
        service.setServiceCode("3h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getAgeAppropriateCounsellingAndHIVDisclosureSupportService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Age appropriate counseling and HIV disclosure support");
        service.setServiceValue("Age appropriate counseling and HIV disclosure support");
        service.setServiceCode("4h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getReferralForHIVRelatedTestingService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral for or was facilitated to obtain HIV related testing");
        service.setServiceValue("Completed a referral for or was facilitated to obtain HIV related testing");
        service.setServiceCode("5h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getReferralForHIVOrOrpotunisticInfectionService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral or was facilitated to obtain HIV (or related opportunistic infections) treatment and care");
        service.setServiceValue("Completed a referral or was facilitated to obtain HIV (or related opportunistic infections) treatment and care");
        service.setServiceCode("6h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getReferralForSTITreatmentService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral or was facilitated to obtain STI treatment");
        service.setServiceValue("Completed a referral or was facilitated to obtain STI treatment");
        service.setServiceCode("7h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getReferralForRoutineHealthCareService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed referral or was facilitated to obtain routine health care");
        service.setServiceValue("Completed referral or was facilitated to obtain routine health care");
        service.setServiceCode("8h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
 
    public static Service getReferralForEIDService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral or was facilitated to obtain EID");
        service.setServiceValue("Completed a referral or was facilitated to obtain EID");
        service.setAlternateName("Early Infant Diagnosis (EID)");
        service.setServiceCode("9h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getReferralForImmunizationService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral was facilitated to obtain immunization appropriate to age based national protocol");
        service.setServiceValue("Completed a referral was facilitated to obtain immunization appropriate to age based national protocol");
        service.setServiceCode("10h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getTrackedDevelopmentalMilestoneService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Regularly tracked development milestones in HIV infected, HEU & infected infants and young children");
        service.setServiceValue("Regularly tracked development milestones in HIV infected, HEU & infected infants and young children");
        service.setServiceCode("11h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHIVPreventionSupportService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral or was facilitated to obtain age appropriate HIV prevention support");
        service.setServiceValue("Completed a referral or was facilitated to obtain age appropriate HIV prevention support");
        service.setAlternateName("Prevention support (PrEP/condoms and/or VMMC)");
        service.setServiceCode("12h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSubstanceAbuseSupportService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral was facilitated to obtain substance abuse support by a trained provider");
        service.setServiceValue("Completed a referral was facilitated to obtain substance abuse support by a trained provider");
        service.setServiceCode("13h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getAgeAppropriateWomenHealthService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral was facilitated to obtain age appropriate women’s health");
        service.setServiceValue("Completed a referral was facilitated to obtain age appropriate women’s health");
        service.setServiceCode("14h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPerinatalCareService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral was facilitated to obtain perinatal care including PMTCT");
        service.setServiceValue("Completed a referral was facilitated to obtain perinatal care including PMTCT");
        service.setServiceCode("15h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEmergencyTransportAssistanceService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Emergency transport assistance");
        service.setServiceValue("Emergency transport assistance");
        service.setServiceCode("16h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getEarlyChildStimulationService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Early Childhood Stimulation (ECS)");
        service.setServiceValue("Early Childhood Stimulation (ECS)");
        service.setServiceCode("17h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getMotherBabyCourseServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Participated in Mother Baby Courses(MBC)");
        service.setServiceValue("Mother baby course");
        service.setServiceCode("18h");
        service.setCascadableStartAge(0);
        service.setCascadableEndAge(2);
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getWASHServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Household hygiene counseling and WASH messaging");
        service.setServiceValue("WASH");
        service.setServiceCode("19h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getReferralForTestingServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Completed a referral for or was facilitated to obtain HIV related testing (HTS, EID, TB, CD4 Count VL)");
        service.setServiceValue("Completed a referral for or was facilitated to obtain HIV related testing (HTS, EID, TB, CD4 Count VL)");
        service.setServiceCode("20h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getReferralForHivRelatedTesting_HTSPMTCT()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("HIV-related testing (HTS, PMTCT)");
        service.setServiceValue("HIV-related testing (HTS, PMTCT)");
        service.setServiceCode("1r");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getSexualReproductiveHealthReferral()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Sexual/Reproductive health");
        service.setServiceValue("Sexual/Reproductive health");
        service.setServiceCode("8r");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getEmergencyHealthCare()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Emergency health care");
        service.setServiceValue("Emergency health care");
        service.setServiceCode("10r");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getReferralForCD4VL()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("CD4 VL");
        service.setServiceValue("CD4 VL");
        service.setServiceCode("4r");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }//Early Infant Diagnosis (EID)
    public static Service getAgeAppropriateDisclosureServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Age appropriate counselling and HIV Disclosure support");
        service.setServiceValue("Age appropriate counselling and HIV Disclosure support");
        service.setServiceCode("21h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getHealthEducationServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Health education");
        service.setServiceValue("Health education");
        service.setServiceCode("22h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getHouseholdHealthInsuranceServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Household health insurance coverage");
        service.setServiceValue("Household health insurance coverage");
        service.setServiceCode("23h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getWashMessagingService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Water, Sanitation and Hygiene (WASH) messaging");
        service.setServiceValue("Water, Sanitation and Hygiene (WASH) messaging");
        service.setServiceCode("24h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }//
    /*public static Service getWashSanitationHygieneMessaging()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Water, Sanitation and Hygiene (WASH) messaging");
        service.setServiceValue("Water, Sanitation and Hygiene (WASH) messaging");
        service.setServiceCode("24h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }*/
    public static Service getCommunityHIVServices_HTS()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Community HIV services - HTS");
        service.setServiceValue("Community HIV services - HTS");
        service.setServiceCode("25h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getHIVServicesReferral()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("HIV services referral - HTS/EID/ART/PMTCT/VL/TB/STIs");
        service.setServiceValue("HIV services referral - HTS/EID/ART/PMTCT/VL/TB/STIs");
        service.setServiceCode("26h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getHealthReferralService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Health services referral");
        service.setServiceValue("Health services referral");
        service.setServiceCode("30h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getIYCFSupportGroupServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("IYCF support group");
        service.setServiceValue("IYCF support group");
        service.setServiceCode("31h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getHealthyFoodDemonstrationService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Healthy food demonstration");
        service.setServiceValue("Healthy food demonstration");
        service.setServiceCode("32h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
        
    public static Service getInseticideTreatedBedNetServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Insecticide treated bed net (LLINs)");
        service.setServiceValue("Insecticide treated bed net (LLINs)");
        service.setServiceCode("34h");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getAdolescentHivPreventionServices()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Adolescent HIV Prevention and SRH services");
        service.setServiceValue("Adolescent HIV Prevention and SRH services");
        service.setServiceCode("35h");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHealthEducationService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Health education");
        service.setServiceValue("Health education");
        service.setServiceCode("1he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getWaterTreatmentService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Water treatment");
        service.setServiceValue("Water treatment");
        service.setServiceCode("5he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPmtctHctCommunityHIVService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Community HIV services (HTC/PMTCT)");
        service.setServiceValue("Community HIV services (HTC/PMTCT)");
        service.setServiceCode("7he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getAccessForHIVCareService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Access for HIV care");//HIV care and support
        service.setServiceValue("Access for HIV care");
        service.setServiceCode("9he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
   //
    public static Service getChildRightsSession()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Child rights Sessions");
        service.setServiceValue("Child rights Sessions");
        service.setServiceCode("1s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSafetyPlan()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Safety Plan");
        service.setServiceValue("Safety Plan");
        service.setServiceCode("2s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getCognitiveBehaviorTherapy()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Cognitive behavior therapy");
        service.setServiceValue("Cognitive behavior therapy");
        service.setServiceCode("3s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFamilyGroupConferencing()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Structured family group conferencing to prevent occurrence/reoccurrence of child abuse, exploitation or neglect");
        service.setServiceValue("Structured family group conferencing to prevent occurrence/reoccurrence of child abuse, exploitation or neglect");
        service.setServiceCode("4s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getChilfAbuseCaseReport()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Child abuse case report to police/local authority");
        service.setServiceValue("Child abuse case report to police/local authority");
        service.setServiceCode("17s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getReferralForSafetyServices()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Referral for Safety services");
        service.setServiceValue("Referral for Safety services");
        service.setServiceCode("18s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }//
    public static Service getLegalAssistanceRelatedToMaltreatmentAndGBV()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Legal assistance related to maltreatment, GBV");
        service.setServiceValue("Legal assistance related to maltreatment, GBV");
        service.setServiceCode("5s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEmergencyShelterAndCareFacilityService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Emergency shelter/care facility");
        service.setServiceValue("Emergency shelter /care facility");
        service.setServiceCode("6s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEvidenceBasedParentingService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Parenting skills");
        service.setServiceValue("Parenting skills");
        service.setServiceCode("7s");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getBirthRegistrationAcquisitionService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Birth registration acquisition");
        service.setServiceValue("Birth registration acquisition");
        service.setServiceCode("8s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    
    public static Service getGBVCommunityDialoguesService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("GBV Community dialogues");
        service.setServiceValue("GBV Community dialogues");
        service.setServiceCode("9s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFamilyConflictMitigationService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        //service.setServiceName("Structured psycho-social support related to family conflict mitigation and family relationship");
        service.setServiceName("Structured PSS related to family conflict mitigation");
        service.setServiceValue("Structured PSS related to family conflict mitigation");
        service.setServiceCode("10s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSessionOnChildProtectionSupportService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Session with child protection officer, police or another local child protection authority");
        service.setServiceValue("Session with child protection officer, police or another local child protection authority");
        service.setServiceCode("11s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPostViolenceTraumaSupportService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Post-violence trauma counselling from a trained provider");
        service.setServiceValue("Post-violence trauma counselling from a trained provider");
        service.setServiceCode("12s");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getPostViolenceMedicalService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Post-violence medical services");
        service.setServiceValue("Post-violence medical services");
        service.setServiceCode("23r");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }//
    public static Service getSpiritualSupportService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Spiritual support");
        service.setServiceValue("Spiritual support");
        service.setServiceCode("18r");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getCommunitySupportGroup()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Community support group (positive living group, caregiver support group, etc.)");
        service.setServiceValue("Community support group (positive living group, caregiver support group, etc.)");
        service.setServiceCode("19r");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getLifeBuildingSkills()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Life building skills");
        service.setServiceValue("Life building skills");
        service.setServiceCode("20r");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }//Life building skills
    public static Service getClothingSupportService()
    {
        Service service=new Service();
        service.setDomainName("Safe");
        service.setServiceName("Clothing support");
        service.setServiceValue("Clothing support");
        service.setServiceCode("13s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getStructuredSafeSpacesInterventionService()
    {
        Service service=new Service();
        service.setDomainName("Safe");
        service.setServiceName("Structured safe spaces intervention");
        service.setServiceValue("Structured safe spaces intervention");
        service.setServiceCode("14s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getAwarenessOnGenderIssues()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Awareness on gender issues/Gender norms");
        service.setServiceValue("Awareness on gender issues/Gender norms");
        service.setServiceCode("15s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPostGbvCare()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Post GBV Care");
        service.setServiceValue("Post GBV Care");
        service.setServiceCode("16s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getBirthRegistrationAwarenessService()
    {
        Service service=new Service();
        service.setDomainName("Safety");
        service.setServiceName("Birth registration awareness");
        service.setServiceValue("Birth registration awareness");
        service.setServiceCode("17s");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    
    
    public static Service getSchoolEducationalAssistance()
    {
        Service service=new Service();
        service.setDomainName("Schooled");
        service.setServiceName("Assistance/support with homework");
        service.setServiceValue("Assistance/support with homework");
        service.setServiceCode("1e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getExamFeeSupport()
    {
        Service service=new Service();
        service.setDomainName("Schooled");
        service.setServiceName("Received bursary, tuition, school fees or fee exemption");
        service.setServiceValue("Received bursary, tuition, school fees or fee exemption");
        service.setServiceCode("2e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEducationalSubsidiesPTCE()
    {
        Service service=new Service();
        service.setDomainName("Schooled");
        service.setServiceName("Received assistance for re-enrollment (for drop outs or teen mothers)");
        service.setServiceValue("Received assistance for re-enrollment (for drop outs or teen mothers)");
        service.setServiceCode("3e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSchoolUniformsAndBooksAssistance()
    {
        Service service=new Service();
        service.setDomainName("Schooled");
        service.setServiceName("Received school uniforms, books or other materials");
        service.setServiceValue("Received school uniforms, books or other materials");
        service.setServiceCode("4e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSchoolEnrolmentAndReenrollmentService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("School enrolment/re-enrolment");
        service.setServiceValue("School enrolment/re-enrolment");
        service.setServiceCode("5e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getWaiverOfSchoolFeeService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Waiver of school fees");
        service.setServiceValue("Waiver of school fees");
        service.setServiceCode("6e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getProvisionOfSchoolMaterialService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Provision of school materials/uniform");
      service.setServiceValue("Provision of school materials/uniform");
      service.setServiceCode("7e");
      service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSchoolPerformanceAssessmentService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("School performance assessment");
        service.setServiceValue("School performance assessment");
        service.setServiceCode("8e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
          
    public static Service getSchoolVisit()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("School visit");
        service.setServiceValue("School visit");
        service.setServiceCode("9e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHolisticScholarshipService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Holistic scholarship");
        service.setServiceValue("Holistic scholarship");
        service.setServiceCode("10e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEducationReferralService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Referral for educational services");
        service.setServiceValue("Referral");
        service.setServiceCode("11e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSensitizationForChildSchoolEnrollment()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Sensitization for child school enrolment/re-enrolment");
        service.setServiceValue("Sensitization for child school enrolment/re-enrolment");
        service.setServiceCode("12e");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    
    public static Service getMentorshipServices()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Mentorship services");
        service.setServiceValue("Mentorship services");
        service.setServiceCode("1t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getEntrepreneurshipandBusinessManagementTraining()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Entrepreneurship training");
        service.setServiceValue("Entrepreneurship training");
        service.setServiceCode("2t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getFinancialLiteracyTraining()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Financial literacy training");
        service.setServiceValue("Financial literacy training");
        service.setServiceCode("3t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getVocationalTraining()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Vocational training");
        service.setServiceValue("Vocational training");
        service.setServiceCode("4t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getMarketLinkages()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Market linkages");
        service.setServiceValue("Market linkages");
        service.setServiceCode("5t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getBusinessSkillsTraining()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Business skills training");
        service.setServiceValue("Business skills training");
        service.setAlternateName("Private and public sector skill acquisition schemes");
        service.setServiceCode("6t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getIncomeGeneratingActiviries()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Income Generating Activities");
        service.setServiceValue("Income Generating Activities");
        service.setAlternateName("Income Generating Activities");
        service.setServiceCode("26r");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }//
    public static Service getPassportToSuccess()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Passport to Success (PTS)");
        service.setServiceValue("Passport to Success (PTS)");
        service.setServiceCode("7t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getLinkagesToFinancialInstitutionsAndPrivateSector()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Linkages to financial institutions & private sector");
        service.setServiceValue("Linkages to financial institutions & private sector");
        service.setServiceCode("8t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getProvisionOfAgriculturalInput()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Agricultural inputs/agricultural value chain");
        service.setServiceValue("Agricultural inputs/agricultural value chain");
        service.setServiceCode("9t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getShortTermEmergencyCashSupport()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Short-term emergency cash support");
        service.setServiceValue("Short-term emergency cash support");
        service.setServiceCode("10t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getSafeShelterRelatedRepair()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Safe shelter-related repair or construction");
        service.setServiceValue("Safe shelter-related repair or construction");
        service.setServiceCode("11t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getReferralForStableServices()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Referral for stable services");
        service.setServiceValue("Referral for stable services");
        service.setServiceCode("13t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getHomeGardeningSupportServices()
    {
        Service service=new Service();
        service.setDomainName("Stable");
        service.setServiceName("Home gardening support");
        service.setServiceValue("Home gardening support");
        service.setServiceCode("12t");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    
    
    
    
    public static Service getLegalServices()
    {
        Service service=new Service();
        service.setDomainName("Child protection");
        service.setServiceName("Legal services");
        service.setServiceValue("Legal services");
        service.setServiceCode("1pt");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSuccessionPlanning()
    {
        Service service=new Service();
        service.setDomainName("Child protection");
        service.setServiceName("Succession planning");
        service.setServiceValue("Succession planning");
        service.setServiceCode("2pt");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    /*public static Service getBirthRegistrationService()
    {
        Service service=new Service();
        service.setDomainName("Child protection");
        service.setServiceName("Birth registration");
        service.setServiceValue("Birth registration");
        service.setServiceCode("3pt");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }*/
    public static Service getReferralForProtectionServices()
    {
        Service service=new Service();
        service.setDomainName("Child protection");
        service.setServiceName("Referral for protection services");
        service.setServiceValue("Referral");
        service.setServiceCode("4pt");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    
    
    public static Service getReIntegrationIntoFamily()
    {
        Service service=new Service();
        service.setDomainName("Shelter and care");
        service.setServiceName("Re-integration into Family");
        service.setServiceValue("Re-integration into Family");
        service.setServiceCode("1sh");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getProvisionAndRepairOfAccommodation()
    {
        Service service=new Service();
        service.setDomainName("Shelter and care");
        service.setServiceName("Provision/repair of accommodation");
        service.setServiceValue("Provision/repair of accommodation");
        service.setServiceCode("2sh");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFosterParenting()
    {
        Service service=new Service();
        service.setDomainName("Shelter and care");
        service.setServiceName("Foster parenting");
        service.setServiceValue("Foster parenting");
        service.setServiceCode("3sh");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getClothingSupport()
    {
        Service service=new Service();
        service.setDomainName("Shelter and care");
        service.setServiceName("Clothing support");
        service.setServiceValue("Clothing support");
        service.setServiceCode("4sh");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getReferralForShelterAndCareServices()
    {
        Service service=new Service();
        service.setDomainName("Shelter and care");
        service.setServiceName("Referral for shelter and care services");
        service.setServiceValue("Referral");
        service.setServiceCode("5sh");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFinancialEducation()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Financial Education");
        service.setServiceValue("Financial Education");
        service.setServiceCode("1es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSavingsAndLoans()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Access to Micro-finance");
        service.setServiceValue("Access to Micro-finance");
        service.setServiceCode("2es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getVocationalAndApprenticeshipTraining()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Vocational/apprenticeship training");
        service.setServiceValue("Vocational/apprenticeship training");
        service.setServiceCode("3es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getLivelihoodOpportunity()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Livelihood opportunity");
        service.setServiceValue("Livelihood opportunity");
        service.setServiceCode("4es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getBusinessGrant()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Business grant");
        service.setServiceValue("Business grant");
        service.setServiceCode("5es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getLinkageToCashTransferScheme()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Linkage to cash transfer scheme");
        service.setServiceValue("Linkage to cash transfer scheme");
        service.setServiceCode("6es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPrivateSectorLinkages()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Private sector linkage(s)");
        service.setServiceValue("Private sector linkage(s)");
        service.setServiceCode("7es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSavingsAndInternalLendingCommunity()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Savings group (SILC, VSLA etc.)");
        service.setServiceValue("SILC");
        service.setServiceCode("8es");
        service.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
        return service;
    }
    public static Service getEconomicStrengtheningReferral()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Referral");
        service.setServiceValue("Economic strenghtening referral");
        service.setServiceCode("9es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHivCounsellingReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("HIV/STI Prevention");
        service.setServiceName("HIV Counselling (Pre-ART, Nutrition and Adherence)");
        service.setServiceValue("HIV Counselling (Pre-ART, Nutrition and Adherence)");
        service.setServiceCode("hcr");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPEPReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("HIV/STI Prevention");
        service.setServiceName("Post exposure prophylaxis");
        service.setServiceValue("Post exposure prophylaxis");
        service.setServiceCode("pep");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPrEPReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("HIV/STI Prevention");
        service.setServiceName("PrEP");
        service.setServiceValue("PrEP");
        service.setServiceCode("prep");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSTIScreeningReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("HIV/STI Prevention");
        service.setServiceName("STI screening/treatment");
        service.setServiceValue("STI screening/treatment");
        service.setServiceCode("sti");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getVMMCReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("HIV/STI Prevention");
        service.setServiceName("VMMC");
        service.setServiceValue("VMMC");
        service.setServiceCode("vmc");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHivRapidTestReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Laboratory/Diagnosis");
        service.setServiceName("HIV Rapid test");
        service.setServiceValue("HIV Rapid test");
        service.setServiceCode("hrt");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getDBSPCRHivTestingReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Laboratory/Diagnosis");
        service.setServiceName("DBS-PCR HIV Testing");
        service.setServiceValue("DBS-PCR HIV Testing");
        service.setServiceCode("dbs");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getCD4Referral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Laboratory/Diagnosis");
        service.setServiceName("CD4 count");
        service.setServiceValue("CD4 count");
        service.setServiceCode("cd4");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getViralLoadTestReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Laboratory/Diagnosis");
        service.setServiceName("Viral load test");
        service.setServiceValue("Viral load test");
        service.setServiceCode("vlt");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFBCReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Laboratory/Diagnosis");
        service.setServiceName("FBC");
        service.setServiceValue("FBC");
        service.setServiceCode("fbc");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getLFTReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Laboratory/Diagnosis");
        service.setServiceName("LFT");
        service.setServiceValue("LFT");
        service.setServiceCode("lft");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getUandESReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Laboratory/Diagnosis");
        service.setServiceName("U&ES");
        service.setServiceValue("U&ES");
        service.setServiceCode("ues");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getCreatinineReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Laboratory/Diagnosis");
        service.setServiceName("Creatinine");
        service.setServiceValue("Creatinine");
        service.setServiceCode("ctn");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPreARTRegistrationReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("Pre-ART registration");
        service.setServiceValue("Pre-ART registration");
        service.setServiceCode("par");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getARTInitiationReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("ART initiation");
        service.setServiceValue("ART initiation");
        service.setAlternateName("ART Serviices");
        service.setServiceCode("arti");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getARTRefillReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("ART refill (Defaulters)");
        service.setServiceValue("ART refill (Defaulters)");
        service.setServiceCode("aref");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getARTDecentralizationReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("ART decentralization");
        service.setServiceValue("ART decentralization");
        service.setServiceCode("artd");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getPMTCTOptionBPlusReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("PMTCT/Option B+");
        service.setServiceValue("PMTCT/Option B+");
        service.setServiceCode("pobp");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getCTXOIManagementReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("CTX/OI management");
        service.setServiceValue("CTX/OI management");
        service.setServiceCode("ctom");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getCARGEnrollmentOrTransferReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("CARG enrollment/transfer");
        service.setServiceValue("CARG enrollment/transfer");
        service.setServiceCode("ceot");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSupportGroupsServiceReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("Support groups e.g CATS");
        service.setServiceValue("Support groups e.g CATS");
        service.setServiceCode("sugp");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getARTOfficialTransferReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("ART official transfer");
        service.setServiceValue("ART official transfer");
        service.setServiceCode("aoft");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getARTReInitiationReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("OI/ART Services");
        service.setServiceName("ART re-initiation (LTFU or stopped Tx)");
        service.setServiceValue("ART re-initiation (LTFU or stopped Tx)");
        service.setServiceCode("arin");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getTBScreeningReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("TB Services");
        service.setServiceName("TB screening");
        service.setServiceValue("TB screening");
        service.setServiceCode("tbs");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getTBDiagnosisReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("TB Services");
        service.setServiceName("TB Diagnosis/Sputum collection");
        service.setServiceValue("TB Diagnosis/Sputum collection");
        service.setServiceCode("tbds");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getTBTreatmentOrManagementReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("TB Services");
        service.setServiceName("TB treatment/management");
        service.setServiceValue("TB treatment/management");
        service.setServiceCode("tbtm");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getTBReinitiationReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("TB Services");
        service.setServiceName("TB re-initiation after LTFU");
        service.setServiceValue("TB re-initiation after LTFU");
        service.setServiceCode("tbri");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getANCReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("FCH Services");
        service.setServiceName("ANC");
        service.setServiceValue("ANC");
        service.setServiceCode("anc");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEmergencyContraceptionReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("FCH Services");
        service.setServiceName("Emergency contraception");
        service.setServiceValue("Emergency contraception");
        service.setServiceCode("emc");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFamilyPlanningReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("FCH Services");
        service.setServiceName("Family planning");
        service.setServiceValue("Family planning");
        service.setServiceCode("fmp");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getCancerOfCervixScreeningReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("FCH Services");
        service.setServiceName("Cancer of Cervix screening");
        service.setServiceValue("Cancer of Cervix screening");
        service.setServiceCode("ccs");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getBreastCancerScreeningReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("FCH Services");
        service.setServiceName("Breast Cancer screening");
        service.setServiceValue("Breast Cancer screening");
        service.setServiceCode("bcs");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getPsychosocialSupportReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Psychosocial and economic support");
        service.setServiceName("Psychosocial support");
        service.setServiceValue("Psychosocial support");
        service.setServiceCode("pss");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getEducationalSupportReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Psychosocial and economic support");
        service.setServiceName("Educational support");
        service.setServiceValue("Educational support");
        service.setServiceCode("eds");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getEmergencyShelterReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Psychosocial and economic support");
        service.setServiceName("Emergency shelter");
        service.setServiceValue("Emergency shelter");
        service.setServiceCode("esh");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getNutritionReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Psychosocial and economic support");
        service.setServiceName("Nutrition referral");
        service.setServiceValue("Nutrition referral");
        service.setServiceCode("6nu");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getLegalCounsellingReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Legal services");
        service.setServiceName("Legal counsel");
        service.setServiceValue("Legal counsel");
        service.setServiceCode("lgc");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getVictimFriendlyServicesReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Legal services");
        service.setServiceName("Victim friendly services (Police, Courts)");
        service.setServiceValue("Victim friendly services (Police, Courts)");
        service.setServiceCode("vfs");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static Service getVitalRegistrationServicesReferral()
    {
        Service service=new Service();
        service.setDomainName("Referral");
        service.setSubDomainName("Legal services");
        service.setServiceName("Vital registration (births/deaths)");
        service.setServiceValue("Vital registration (births/deaths)");
        service.setServiceCode("vrs");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }
    public static String getConcatenatedServiceCodes(String[] arrayOfServiceNames)
    {
        String concatenatedServiceCodes=null;
        if(arrayOfServiceNames !=null)
        {
            Service service=null;
            String serviceCode=null;
            String codeOrName=null;
            for(int i=0; i<arrayOfServiceNames.length; i++)
            {
                codeOrName=arrayOfServiceNames[i];
                if(codeOrName !=null && codeOrName.trim().length()>0)
                {
                    codeOrName=codeOrName.replace(";", "");
                    codeOrName=codeOrName.replace(",", "");
                    codeOrName=codeOrName.trim();
                    service=getService(codeOrName);
                    serviceCode=service.getServiceCode();
                    if(i==0)
                    concatenatedServiceCodes=serviceCode;
                    else
                    {
                        concatenatedServiceCodes+=","+serviceCode;
                    }
                    //System.err.println("codeOrName is "+codeOrName+" service.getServiceCode() is "+service.getServiceCode()+" serviceCode is "+serviceCode+" concatenatedServiceCodes is "+concatenatedServiceCodes);
                }
            }
        }
        return concatenatedServiceCodes;
    }
    public static String getConcatenatedServiceNames(String[] arrayOfServiceCodes)
    {
        String concatenatedServiceNames=null;
        if(arrayOfServiceCodes !=null)
        {
            Service service=null;
            String codeOrName=null;
            String serviceName=null;
            for(int i=0; i<arrayOfServiceCodes.length; i++)
            {
                codeOrName=arrayOfServiceCodes[i];
                if(codeOrName !=null && codeOrName.trim().length()>0)
                {
                    codeOrName=codeOrName.replace(";", "");
                    codeOrName=codeOrName.replace(",", "");
                    codeOrName=codeOrName.trim();
                    service=getService(codeOrName);
                    serviceName=service.getServiceName();
                    if(serviceName !=null)
                    {
                        serviceName=serviceName.trim();
                        if(i==0)
                        concatenatedServiceNames=serviceName;
                        else
                        {
                            concatenatedServiceNames+=","+serviceName;
                        }
                    }
                }
            }
        }
        return concatenatedServiceNames;
    }
    public static Service getService(String serviceCodeOrName)
    {
        Service service=new Service();
        if(serviceCodeOrName !=null)
        {
            serviceCodeOrName=serviceCodeOrName.replace(";", "");
            serviceCodeOrName=serviceCodeOrName.trim();
            if(serviceCodeOrName.equalsIgnoreCase("Counselling support") || serviceCodeOrName.equalsIgnoreCase("1ps"))
            service=getCounsellingSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase("Recreational activity") || serviceCodeOrName.equalsIgnoreCase("2ps"))
            service=getRecreationalService();
            else if(serviceCodeOrName.equalsIgnoreCase("Life skill support") || serviceCodeOrName.equalsIgnoreCase("3ps"))
            service=getLifeSkillSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase("homevisit") || serviceCodeOrName.equalsIgnoreCase("4ps"))
            service=getHomeVisitService();
            else if(serviceCodeOrName.equalsIgnoreCase("Nutrition education and counselling") || serviceCodeOrName.equalsIgnoreCase("1nu"))
            service=getNutritionEducationAndCounsellingService();
            else if(serviceCodeOrName.equalsIgnoreCase("Food & nutritional supplements") || serviceCodeOrName.equalsIgnoreCase("2nu"))
            service=getFoodAndNutritionalSupplements();
            else if(serviceCodeOrName.equalsIgnoreCase("Vitamin A, Zinc and Iron suplement") || serviceCodeOrName.equalsIgnoreCase("3nu"))
            service=getVitaminAZincAndIronSuplement();
            else if(serviceCodeOrName.equalsIgnoreCase("Nutrition assessment, counselling and support (NACS)") || serviceCodeOrName.equalsIgnoreCase("4nu"))
            service=getNutritionAssessmentCounsellingAndSupport();
            
            else if(serviceCodeOrName.equalsIgnoreCase("Growth monitoring") || serviceCodeOrName.equalsIgnoreCase("5nu"))
            service=getGrowthMonitoring();
            else if(serviceCodeOrName.equalsIgnoreCase("37") || serviceCodeOrName.equalsIgnoreCase("Nutrition referral") || serviceCodeOrName.equalsIgnoreCase("6nu"))
            service=getNutritionReferral();
            
            else if(serviceCodeOrName.equalsIgnoreCase("Age appropriate HIV treatment literacy (CLHIV)") || serviceCodeOrName.equalsIgnoreCase("1h"))
            service=getAgeAppropriateHIVTreatmentLiteracyService();
            else if(serviceCodeOrName.equalsIgnoreCase("HIV Adherence support") || serviceCodeOrName.equalsIgnoreCase("2h"))
            service=getHIVAdherenceSupportGroupService();
            else if(serviceCodeOrName.equalsIgnoreCase("Structured PLHIV Support group") || serviceCodeOrName.equalsIgnoreCase("3h"))
            service=getStructuredPLHIVSupportGroupService();
            else if(serviceCodeOrName.equalsIgnoreCase("Age appropriate counseling and HIV disclosure support") || serviceCodeOrName.equalsIgnoreCase("4h") || serviceCodeOrName.equalsIgnoreCase("21h"))
            service=getAgeAppropriateCounsellingAndHIVDisclosureSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase(getHealthEducationServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHealthEducationServices().getServiceCode()))
            service=getHealthEducationServices();
            else if(serviceCodeOrName.equalsIgnoreCase(getHouseholdHealthInsuranceServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHouseholdHealthInsuranceServices().getServiceCode()))
            service=getHouseholdHealthInsuranceServices();
            else if(serviceCodeOrName.equalsIgnoreCase(getCommunityHIVServices_HTS().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getCommunityHIVServices_HTS().getServiceCode()))
            service=getCommunityHIVServices_HTS();
            else if(serviceCodeOrName.equalsIgnoreCase(getHIVServicesReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHIVServicesReferral().getServiceCode()))
            service=getHIVServicesReferral();
            else if(serviceCodeOrName.equalsIgnoreCase(getFoodPackagesOrNutritionSupplements().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getFoodPackagesOrNutritionSupplements().getServiceCode()))
            service=getFoodPackagesOrNutritionSupplements();//
            else if(serviceCodeOrName.equalsIgnoreCase(getHealthyFoodDemonstrationService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHealthyFoodDemonstrationService().getServiceCode()))
            service=getHealthyFoodDemonstrationService();
            else if(serviceCodeOrName.equalsIgnoreCase(getIYCFSupportGroupServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getIYCFSupportGroupServices().getServiceCode()))
            service=getIYCFSupportGroupServices();
            else if(serviceCodeOrName.equalsIgnoreCase(getHealthReferralService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHealthReferralService().getServiceCode()))
            service=getHealthReferralService();
            else if(serviceCodeOrName.equalsIgnoreCase(getWashMessagingService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getWashMessagingService().getServiceCode()))
            service=getWashMessagingService();
            else if(serviceCodeOrName.equalsIgnoreCase(getHealthEducationService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHealthEducationService().getServiceCode()))
            service=getHealthEducationService();
            else if(serviceCodeOrName.equalsIgnoreCase(getPmtctHctCommunityHIVService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPmtctHctCommunityHIVService().getServiceCode()))
            service=getPmtctHctCommunityHIVService();
            else if(serviceCodeOrName.equalsIgnoreCase(getAccessForHIVCareService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getAccessForHIVCareService().getServiceCode()))
            service=getAccessForHIVCareService();
            else if(serviceCodeOrName.equalsIgnoreCase(getWaterTreatmentService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getWaterTreatmentService().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("13r"))
            service=getWaterTreatmentService();
            else if(serviceCodeOrName.equalsIgnoreCase(getFoodAndNutritionalSupplements().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getFoodAndNutritionalSupplements().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("14r"))
            service=getFoodAndNutritionalSupplements();
            else if(serviceCodeOrName.equalsIgnoreCase(getSevereAcuteMalnutrition().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSevereAcuteMalnutrition().getServiceCode()))
            service=getSevereAcuteMalnutrition();
            else if(serviceCodeOrName.equalsIgnoreCase(getWastingOrEdema().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getWastingOrEdema().getServiceCode()))
            service=getWastingOrEdema();
            //
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral for or was facilitated to obtain HIV related testing") || serviceCodeOrName.equalsIgnoreCase("5h") || serviceCodeOrName.equalsIgnoreCase("20h"))
            service=getReferralForHIVRelatedTestingService();
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral or was facilitated to obtain HIV (or related opportunistic infections) treatment and care") || serviceCodeOrName.equalsIgnoreCase("6h"))
            service=getReferralForHIVOrOrpotunisticInfectionService();
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral or was facilitated to obtain STI treatment") || serviceCodeOrName.equalsIgnoreCase("7h") || serviceCodeOrName.equalsIgnoreCase("6r"))
            service=getReferralForSTITreatmentService();
            else if(serviceCodeOrName.equalsIgnoreCase(getReferralForRoutineHealthCareService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getReferralForRoutineHealthCareService().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("9r"))
            service=getReferralForRoutineHealthCareService();
            else if(serviceCodeOrName.equalsIgnoreCase(getEmergencyHealthCare().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getEmergencyHealthCare().getServiceCode()))
            service=getEmergencyHealthCare();
            else if(serviceCodeOrName.equalsIgnoreCase(getVitaminAZincAndIronSuplement().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getVitaminAZincAndIronSuplement().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("11r"))
            service=getVitaminAZincAndIronSuplement();
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral or was facilitated to obtain EID") || serviceCodeOrName.equalsIgnoreCase("9h")  || serviceCodeOrName.equalsIgnoreCase("2r"))
            service=getReferralForEIDService();
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral was facilitated to obtain immunization appropriate to age based national protocol") || serviceCodeOrName.equalsIgnoreCase("10h"))
            service=getReferralForImmunizationService();
            else if(serviceCodeOrName.equalsIgnoreCase("Regularly tracked development milestones in HIV infected, HEU & infected infants and young children") || serviceCodeOrName.equalsIgnoreCase("11h"))
            service=getTrackedDevelopmentalMilestoneService();
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral or was facilitated to obtain age appropriate HIV prevention support") || serviceCodeOrName.equalsIgnoreCase("12h") || serviceCodeOrName.equalsIgnoreCase("7r"))
            service=getHIVPreventionSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase(getSexualReproductiveHealthReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSexualReproductiveHealthReferral().getServiceCode()))
            service=getSexualReproductiveHealthReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral was facilitated to obtain substance abuse support by a trained provider") || serviceCodeOrName.equalsIgnoreCase("13h"))
            service=getSubstanceAbuseSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral was facilitated to obtain age appropriate women’s health") || serviceCodeOrName.equalsIgnoreCase("14h"))
            service=getAgeAppropriateWomenHealthService();
            else if(serviceCodeOrName.equalsIgnoreCase("Completed a referral was facilitated to obtain perinatal care including PMTCT") || serviceCodeOrName.equalsIgnoreCase("15h"))
            service=getPerinatalCareService();
            else if(serviceCodeOrName.equalsIgnoreCase("Emergency transport assistance") || serviceCodeOrName.equalsIgnoreCase("16h"))
            service=getEmergencyTransportAssistanceService();
            else if(serviceCodeOrName.equalsIgnoreCase("Early Childhood Stimulation (ECS)") || serviceCodeOrName.equalsIgnoreCase("17h"))
            service=getEarlyChildStimulationService();
            else if(serviceCodeOrName.equalsIgnoreCase("Participated in Mother Baby Courses(MBC)") || serviceCodeOrName.equalsIgnoreCase("18h"))
            service=getMotherBabyCourseServices();
            else if(serviceCodeOrName.equalsIgnoreCase("Household hygiene counseling and WASH messaging") || serviceCodeOrName.equalsIgnoreCase("19h"))
            service=getWASHServices();
            else if(serviceCodeOrName.equalsIgnoreCase(getReferralForTestingServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getReferralForTestingServices().getServiceName()))
            service=getReferralForTestingServices();
            else if(serviceCodeOrName.equalsIgnoreCase(getReferralForHivRelatedTesting_HTSPMTCT().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getReferralForHivRelatedTesting_HTSPMTCT().getServiceCode()))
            service=getReferralForHivRelatedTesting_HTSPMTCT();
            /*else if(serviceCodeOrName.equalsIgnoreCase(getAgeAppropriateDisclosureServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getAgeAppropriateDisclosureServices().getServiceName()))
            service=getAgeAppropriateDisclosureServices();)*/
            
            
            //else if(serviceCodeOrName.equalsIgnoreCase("Advocacy for school enrolment") || serviceCodeOrName.equalsIgnoreCase("1ed"))
            //service=getAdvocacyForSchoolEnrolmentService();
            else if(serviceCodeOrName.equalsIgnoreCase(getSchoolEnrolmentAndReenrollmentService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSchoolEnrolmentAndReenrollmentService().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("5er"))
            service=getSchoolEnrolmentAndReenrollmentService();//
            else if(serviceCodeOrName.equalsIgnoreCase("Advocacy for waiver of school fees") || serviceCodeOrName.equalsIgnoreCase(getWaiverOfSchoolFeeService().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("2ed")  || serviceCodeOrName.equalsIgnoreCase("6er"))
            service=getWaiverOfSchoolFeeService();
            else if(serviceCodeOrName.equalsIgnoreCase("Provision of school materials") || serviceCodeOrName.equalsIgnoreCase(getProvisionOfSchoolMaterialService().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("3ed")  || serviceCodeOrName.equalsIgnoreCase("7er"))
            service=getProvisionOfSchoolMaterialService();
            else if(serviceCodeOrName.equalsIgnoreCase("School performance assessment") || getSchoolPerformanceAssessmentService().getServiceCode().equalsIgnoreCase(serviceCodeOrName) || serviceCodeOrName.equalsIgnoreCase("4ed") || serviceCodeOrName.equalsIgnoreCase("8er"))
            service=getSchoolPerformanceAssessmentService();
            else if(serviceCodeOrName.equalsIgnoreCase("School visit") || serviceCodeOrName.equalsIgnoreCase("5ed"))
            service=getSchoolVisit();
            else if(serviceCodeOrName.equalsIgnoreCase("Holistic scholarship") || serviceCodeOrName.equalsIgnoreCase("6ed"))
            service=getHolisticScholarshipService();
            else if(serviceCodeOrName.equalsIgnoreCase("Referral") || serviceCodeOrName.equalsIgnoreCase("7ed"))
            service=getEducationReferralService();
            
            //
            else if(serviceCodeOrName.equalsIgnoreCase(getChildRightsSession().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getChildRightsSession().getServiceCode()))
            service=getChildRightsSession();
            else if(serviceCodeOrName.equalsIgnoreCase( getSafetyPlan().getServiceName()) || serviceCodeOrName.equalsIgnoreCase( getSafetyPlan().getServiceCode()))
            service= getSafetyPlan();
            else if(serviceCodeOrName.equalsIgnoreCase(getCognitiveBehaviorTherapy().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getCognitiveBehaviorTherapy().getServiceCode()))
            service=getCognitiveBehaviorTherapy();
            else if(serviceCodeOrName.equalsIgnoreCase(getFamilyGroupConferencing().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getFamilyGroupConferencing().getServiceCode()))
            service=getFamilyGroupConferencing();
            else if(serviceCodeOrName.equalsIgnoreCase(getLegalAssistanceRelatedToMaltreatmentAndGBV().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getLegalAssistanceRelatedToMaltreatmentAndGBV().getServiceCode()))
            service=getLegalAssistanceRelatedToMaltreatmentAndGBV();
            else if(serviceCodeOrName.equalsIgnoreCase(getEmergencyShelterAndCareFacilityService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getEmergencyShelterAndCareFacilityService().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("24r"))
            service=getEmergencyShelterAndCareFacilityService();
            else if(serviceCodeOrName.equalsIgnoreCase(getEvidenceBasedParentingService().getServiceName()) || serviceCodeOrName.trim().equalsIgnoreCase(getEvidenceBasedParentingService().getServiceCode()))
            service=getEvidenceBasedParentingService();
            else if(serviceCodeOrName.equalsIgnoreCase(getBirthRegistrationAcquisitionService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getBirthRegistrationAcquisitionService().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("22r"))
            service=getBirthRegistrationAcquisitionService();
            else if(serviceCodeOrName.equalsIgnoreCase(getPostViolenceMedicalService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPostViolenceMedicalService().getServiceCode()))
            service=getPostViolenceMedicalService();//
            else if(serviceCodeOrName.equalsIgnoreCase(getGBVCommunityDialoguesService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getGBVCommunityDialoguesService().getServiceCode()))
            service=getGBVCommunityDialoguesService();
            else if(serviceCodeOrName.equalsIgnoreCase(getFamilyConflictMitigationService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getFamilyConflictMitigationService().getServiceCode()))
            service=getFamilyConflictMitigationService();
            else if(serviceCodeOrName.equalsIgnoreCase(getSessionOnChildProtectionSupportService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSessionOnChildProtectionSupportService().getServiceCode()))
            service=getSessionOnChildProtectionSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase(getPostViolenceTraumaSupportService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPostViolenceTraumaSupportService().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("17r"))
            service=getPostViolenceTraumaSupportService();
            
            else if(serviceCodeOrName.equalsIgnoreCase(getSpiritualSupportService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSpiritualSupportService().getServiceCode()))
            service=getSpiritualSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase(getCommunitySupportGroup().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getCommunitySupportGroup().getServiceCode()))
            service=getCommunitySupportGroup();
            else if(serviceCodeOrName.equalsIgnoreCase(getLifeBuildingSkills().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getLifeBuildingSkills().getServiceCode()))
            service=getLifeBuildingSkills();
            //
            else if(serviceCodeOrName.equalsIgnoreCase(getClothingSupportService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getClothingSupportService().getServiceCode()))
            service=getClothingSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase(getStructuredSafeSpacesInterventionService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getStructuredSafeSpacesInterventionService().getServiceCode()))
            service=getStructuredSafeSpacesInterventionService();
            else if(serviceCodeOrName.equalsIgnoreCase(getAwarenessOnGenderIssues().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getAwarenessOnGenderIssues().getServiceCode()))
            service=getAwarenessOnGenderIssues();
            else if(serviceCodeOrName.equalsIgnoreCase(getPostGbvCare().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPostGbvCare().getServiceCode()))
            service=getPostGbvCare();
            else if(serviceCodeOrName.equalsIgnoreCase(getBirthRegistrationAwarenessService().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getBirthRegistrationAwarenessService().getServiceCode()))
            service=getBirthRegistrationAwarenessService();
            //
            
            else if(serviceCodeOrName.equalsIgnoreCase(getSchoolEducationalAssistance().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSchoolEducationalAssistance().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("1er"))
            service=getSchoolEducationalAssistance();
            else if(serviceCodeOrName.equalsIgnoreCase(getExamFeeSupport().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getExamFeeSupport().getServiceCode()))
            service=getExamFeeSupport();
            else if(serviceCodeOrName.equalsIgnoreCase(getEducationalSubsidiesPTCE().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getEducationalSubsidiesPTCE().getServiceCode()))
            service=getEducationalSubsidiesPTCE();
            else if(serviceCodeOrName.equalsIgnoreCase(getSchoolUniformsAndBooksAssistance().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSchoolUniformsAndBooksAssistance().getServiceCode()))
            service=getSchoolUniformsAndBooksAssistance();
            else if(serviceCodeOrName.equalsIgnoreCase(getMentorshipServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getMentorshipServices().getServiceCode()))
            service=getMentorshipServices();
            else if(serviceCodeOrName.equalsIgnoreCase(getEntrepreneurshipandBusinessManagementTraining().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getEntrepreneurshipandBusinessManagementTraining().getServiceCode()))
            service=getEntrepreneurshipandBusinessManagementTraining();
            else if(serviceCodeOrName.equalsIgnoreCase(getFinancialLiteracyTraining().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getFinancialLiteracyTraining().getServiceCode()))
            service=getFinancialLiteracyTraining();
            else if(serviceCodeOrName.equalsIgnoreCase(getVocationalTraining().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getVocationalTraining().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("3esr"))
            service=getVocationalTraining();
            else if(serviceCodeOrName.equalsIgnoreCase(getMarketLinkages().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getMarketLinkages().getServiceCode()))
            service=getMarketLinkages();
            else if(serviceCodeOrName.equalsIgnoreCase(getBusinessSkillsTraining().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getBusinessSkillsTraining().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("25r") )
            service=getBusinessSkillsTraining();
            else if(serviceCodeOrName.equalsIgnoreCase(getIncomeGeneratingActiviries().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getIncomeGeneratingActiviries().getServiceCode()) )
            service=getIncomeGeneratingActiviries();
            //
            else if(serviceCodeOrName.equalsIgnoreCase(getPassportToSuccess().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPassportToSuccess().getServiceCode()))
            service=getPassportToSuccess();
            else if(serviceCodeOrName.equalsIgnoreCase(getLinkagesToFinancialInstitutionsAndPrivateSector().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getLinkagesToFinancialInstitutionsAndPrivateSector().getServiceCode()))
            service=getLinkagesToFinancialInstitutionsAndPrivateSector();
            else if(serviceCodeOrName.equalsIgnoreCase(getProvisionOfAgriculturalInput().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getProvisionOfAgriculturalInput().getServiceCode()))
            service=getProvisionOfAgriculturalInput();
            else if(serviceCodeOrName.equalsIgnoreCase(getShortTermEmergencyCashSupport().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getShortTermEmergencyCashSupport().getServiceCode()))
            service=getShortTermEmergencyCashSupport();
            else if(serviceCodeOrName.equalsIgnoreCase(getHomeGardeningSupportServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHomeGardeningSupportServices().getServiceCode()))
            service=getHomeGardeningSupportServices();
            else if(serviceCodeOrName.equalsIgnoreCase(getSafeShelterRelatedRepair().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSafeShelterRelatedRepair().getServiceCode()))
            service=getSafeShelterRelatedRepair();
            //getSafeShelterRelatedRepair()
            else if(serviceCodeOrName.equalsIgnoreCase(getInseticideTreatedBedNetServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getInseticideTreatedBedNetServices().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("12r"))
            service=getInseticideTreatedBedNetServices();
            else if(serviceCodeOrName.equalsIgnoreCase(getAdolescentHivPreventionServices().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getAdolescentHivPreventionServices().getServiceCode()))
            service=getAdolescentHivPreventionServices();
            
            else if(serviceCodeOrName.equalsIgnoreCase("Legal services") || serviceCodeOrName.equalsIgnoreCase(getLegalServices().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("21r"))
            service=getLegalServices();
            else if(serviceCodeOrName.equalsIgnoreCase("Succession planning") || serviceCodeOrName.equalsIgnoreCase("2pt"))
            service=getSuccessionPlanning();
            //else if(serviceCodeOrName.equalsIgnoreCase("Birth registration") || serviceCodeOrName.equalsIgnoreCase("3pt"))
            //service=getBirthRegistrationService();
            else if(serviceCodeOrName.equalsIgnoreCase("Referral") || serviceCodeOrName.equalsIgnoreCase("4pt"))
            service=getReferralForProtectionServices();
            
            else if(serviceCodeOrName.equalsIgnoreCase("Re-integration into Family") || serviceCodeOrName.equalsIgnoreCase("1sh"))
            service=getReIntegrationIntoFamily();
            else if(serviceCodeOrName.equalsIgnoreCase("Provision/repair of accommodation") || serviceCodeOrName.equalsIgnoreCase("2sh"))
            service=getProvisionAndRepairOfAccommodation();
            else if(serviceCodeOrName.equalsIgnoreCase("Foster parenting") || serviceCodeOrName.equalsIgnoreCase("3sh"))
            service=getFosterParenting();
            else if(serviceCodeOrName.equalsIgnoreCase("Clothing support") || serviceCodeOrName.equalsIgnoreCase("4sh"))
            service=getClothingSupport();
            else if(serviceCodeOrName.equalsIgnoreCase("Referral") || serviceCodeOrName.equalsIgnoreCase("5sh"))
            service=getReferralForShelterAndCareServices();
            
            else if(serviceCodeOrName.equalsIgnoreCase("Financial Education") || serviceCodeOrName.equalsIgnoreCase("1es"))
            service=getFinancialEducation();
            else if(serviceCodeOrName.equalsIgnoreCase("Micro-finance (savings and loans)") || serviceCodeOrName.equalsIgnoreCase("2es") || serviceCodeOrName.equalsIgnoreCase("2esr"))
            service=getSavingsAndLoans();
            else if(serviceCodeOrName.equalsIgnoreCase("Vocational/apprenticeship training") || serviceCodeOrName.equalsIgnoreCase("3es"))
            service=getVocationalAndApprenticeshipTraining();
            else if(serviceCodeOrName.equalsIgnoreCase("Livelihood opportunity") || serviceCodeOrName.equalsIgnoreCase("4es"))
            service=getLivelihoodOpportunity();
            else if(serviceCodeOrName.equalsIgnoreCase("Business grant") || serviceCodeOrName.equalsIgnoreCase("5es"))
            service=getBusinessGrant();
            else if(serviceCodeOrName.equalsIgnoreCase("Linkage to cash transfer scheme") || serviceCodeOrName.equalsIgnoreCase("6es"))
            service=getLinkageToCashTransferScheme();
            
            else if(serviceCodeOrName.equalsIgnoreCase("Private sector linkage(s)") || serviceCodeOrName.equalsIgnoreCase("7es"))
            service=getPrivateSectorLinkages();
            else if(serviceCodeOrName.equalsIgnoreCase("SILC") || serviceCodeOrName.equalsIgnoreCase("Engaged in SILC or ISAL") || serviceCodeOrName.equalsIgnoreCase("8es"))
            service=getSavingsAndInternalLendingCommunity();
            else if(serviceCodeOrName.equalsIgnoreCase("34") || serviceCodeOrName.equalsIgnoreCase("Economic strenghtening referral") || serviceCodeOrName.equalsIgnoreCase("9es"))
            service=getEconomicStrengtheningReferral();
            
            //Referral services
            else if(serviceCodeOrName.equalsIgnoreCase("1") || serviceCodeOrName.equalsIgnoreCase(getHivCounsellingReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHivCounsellingReferral().getServiceCode()))
            service=getHivCounsellingReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("2") || serviceCodeOrName.equalsIgnoreCase(getPEPReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPEPReferral().getServiceCode()))
            service=getPEPReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("3") || serviceCodeOrName.equalsIgnoreCase(getPrEPReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPrEPReferral().getServiceCode()))
            service=getPrEPReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("4") || serviceCodeOrName.equalsIgnoreCase(getSTIScreeningReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSTIScreeningReferral().getServiceCode()))
            service=getSTIScreeningReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("5") || serviceCodeOrName.equalsIgnoreCase(getVMMCReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getVMMCReferral().getServiceCode()))
            service=getVMMCReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("6") || serviceCodeOrName.equalsIgnoreCase(getHivRapidTestReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getHivRapidTestReferral().getServiceCode()))
            service=getHivRapidTestReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("7") || serviceCodeOrName.equalsIgnoreCase(getDBSPCRHivTestingReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getDBSPCRHivTestingReferral().getServiceCode()))
            service=getDBSPCRHivTestingReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("8") || serviceCodeOrName.equalsIgnoreCase(getCD4Referral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getCD4Referral().getServiceCode()))
            service=getCD4Referral();
            else if(serviceCodeOrName.equalsIgnoreCase("9") || serviceCodeOrName.equalsIgnoreCase(getViralLoadTestReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getViralLoadTestReferral().getServiceCode()))
            service=getViralLoadTestReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("10") || serviceCodeOrName.equalsIgnoreCase(getFBCReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getFBCReferral().getServiceCode()))
            service=getFBCReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("11") || serviceCodeOrName.equalsIgnoreCase(getLFTReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getLFTReferral().getServiceCode()))
            service=getLFTReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("12") || serviceCodeOrName.equalsIgnoreCase(getUandESReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getUandESReferral().getServiceCode()))
            service=getUandESReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("13") || serviceCodeOrName.equalsIgnoreCase(getCreatinineReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getCreatinineReferral().getServiceCode()))
            service=getCreatinineReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("14") || serviceCodeOrName.equalsIgnoreCase(getPreARTRegistrationReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPreARTRegistrationReferral().getServiceCode()))
            service=getPreARTRegistrationReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("15") || serviceCodeOrName.equalsIgnoreCase(getARTInitiationReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getARTInitiationReferral().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("3r"))
            service=getARTInitiationReferral();
            else if(serviceCodeOrName.equalsIgnoreCase(getReferralForCD4VL().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getReferralForCD4VL().getServiceCode()) || serviceCodeOrName.equalsIgnoreCase("4r"))
            service=getReferralForCD4VL();
            //
            else if(serviceCodeOrName.equalsIgnoreCase("16") || serviceCodeOrName.equalsIgnoreCase(getARTRefillReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getARTRefillReferral().getServiceCode()))
            service=getARTRefillReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("17") || serviceCodeOrName.equalsIgnoreCase(getARTDecentralizationReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getARTDecentralizationReferral().getServiceCode()))
            service=getARTDecentralizationReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("18") || serviceCodeOrName.equalsIgnoreCase(getPMTCTOptionBPlusReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPMTCTOptionBPlusReferral().getServiceCode()))
            service=getPMTCTOptionBPlusReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("19") || serviceCodeOrName.equalsIgnoreCase(getCTXOIManagementReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getCTXOIManagementReferral().getServiceCode()))
            service=getCTXOIManagementReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("20") || serviceCodeOrName.equalsIgnoreCase(getCARGEnrollmentOrTransferReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getCARGEnrollmentOrTransferReferral().getServiceCode()))
            service=getCARGEnrollmentOrTransferReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("21") || serviceCodeOrName.equalsIgnoreCase(getSupportGroupsServiceReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getSupportGroupsServiceReferral().getServiceCode()))
            service=getSupportGroupsServiceReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("22") || serviceCodeOrName.equalsIgnoreCase(getARTOfficialTransferReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getARTOfficialTransferReferral().getServiceCode()))
            service=getARTOfficialTransferReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("23") || serviceCodeOrName.equalsIgnoreCase(getARTReInitiationReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getARTReInitiationReferral().getServiceCode()))
            service=getARTReInitiationReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("24") || serviceCodeOrName.equalsIgnoreCase(getTBScreeningReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getTBScreeningReferral().getServiceCode()))
            service=getTBScreeningReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("25") || serviceCodeOrName.equalsIgnoreCase("5r") || serviceCodeOrName.equalsIgnoreCase(getTBDiagnosisReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getTBDiagnosisReferral().getServiceCode()))
            service=getTBDiagnosisReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("26") || serviceCodeOrName.equalsIgnoreCase(getTBTreatmentOrManagementReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getTBTreatmentOrManagementReferral().getServiceCode()))
            service=getTBTreatmentOrManagementReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("27") || serviceCodeOrName.equalsIgnoreCase(getTBReinitiationReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getTBReinitiationReferral().getServiceCode()))
            service=getTBReinitiationReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("28") || serviceCodeOrName.equalsIgnoreCase(getANCReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getANCReferral().getServiceCode()))
            service=getANCReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("29") || serviceCodeOrName.equalsIgnoreCase(getEmergencyContraceptionReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getEmergencyContraceptionReferral().getServiceCode()))
            service=getEmergencyContraceptionReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("30") || serviceCodeOrName.equalsIgnoreCase(getFamilyPlanningReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getFamilyPlanningReferral().getServiceCode()))
            service=getFamilyPlanningReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("31") || serviceCodeOrName.equalsIgnoreCase(getCancerOfCervixScreeningReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getCancerOfCervixScreeningReferral().getServiceCode()))
            service=getCancerOfCervixScreeningReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("32") || serviceCodeOrName.equalsIgnoreCase(getBreastCancerScreeningReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getBreastCancerScreeningReferral().getServiceCode()))
            service=getBreastCancerScreeningReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("33") || serviceCodeOrName.equalsIgnoreCase(getPsychosocialSupportReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getPsychosocialSupportReferral().getServiceCode()))
            service=getPsychosocialSupportReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("35") || serviceCodeOrName.equalsIgnoreCase(getEducationalSupportReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getEducationalSupportReferral().getServiceCode()))
            service=getEducationalSupportReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("36") || serviceCodeOrName.equalsIgnoreCase(getEmergencyShelterReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getEmergencyShelterReferral().getServiceCode()))
            service=getEmergencyShelterReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("38") || serviceCodeOrName.equalsIgnoreCase(getLegalCounsellingReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getLegalCounsellingReferral().getServiceCode()))
            service=getLegalCounsellingReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("39") || serviceCodeOrName.equalsIgnoreCase(getVictimFriendlyServicesReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getVictimFriendlyServicesReferral().getServiceCode()))
            service=getVictimFriendlyServicesReferral();
            else if(serviceCodeOrName.equalsIgnoreCase("40") || serviceCodeOrName.equalsIgnoreCase(getVitalRegistrationServicesReferral().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getVitalRegistrationServicesReferral().getServiceCode()))
            service=getVitalRegistrationServicesReferral();
            
            else
            {
                if(serviceCodeOrName.length()>0)
                {
                    service.setServiceCode(";"+serviceCodeOrName);
                    service.setServiceName(";"+serviceCodeOrName);
                    //System.err.println("service.getServiceName() is "+service.getServiceName());
                }
            }
        }
        
        return service;
    }
}
