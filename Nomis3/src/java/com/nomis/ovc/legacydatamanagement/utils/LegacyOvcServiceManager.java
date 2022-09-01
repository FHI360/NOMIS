/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.utils;

import com.nomis.ovc.business.Service;
import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class LegacyOvcServiceManager implements Serializable
{
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
        service.setDomainName("Psychosocial Support");
        service.setServiceName("Recreational activity");//Recreational activity (e.g kids club)
        service.setServiceValue("Recreational activity");
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
    public static Service getParentingSkills()
    {
        Service service=new Service();
        service.setDomainName("Psychosocial Support");
        service.setServiceName("Parenting skills");
        service.setServiceValue("Parenting skills");
        service.setServiceCode("5ps");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getCaregiverForum()
    {
        Service service=new Service();
        service.setDomainName("Psychosocial Support");
        service.setServiceName("Caregivers forum");
        service.setServiceValue("Caregivers forum");
        service.setServiceCode("6ps");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }//Parenting skills
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
        service.setServiceName("Food & nutritional supplements");//Food and nutritional supplements
        service.setServiceValue("Food & nutritional supplements");
        service.setServiceCode("2nu");
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
        service.setServiceName("NACS");//Nutrition assessment, counselling and support (NACS)
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
    public static Service getNutritionReferral()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Nutrition referral");
        service.setServiceValue("Nutrition referral");
        service.setServiceCode("6nu");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getFoodPreservation()
    {
        Service service=new Service();
        service.setDomainName("Nutrition");
        service.setServiceName("Food preservation");
        service.setServiceValue("Food_preservation");
        service.setServiceCode("7nu");
        service.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
        return service;
    }//
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
    public static Service getTreatmentOfMinorIllnessesService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Treatment of minor illnesses");
        service.setServiceValue("Treatment of minor illnesses");
        service.setServiceCode("2he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getDewormingService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("De-worming");
        service.setServiceValue("De-worming");
        service.setServiceCode("3he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getInsecticideTreatedBedNetService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Insecticides treated bed net");
        service.setServiceValue("Insecticides treated bed net");
        service.setServiceCode("4he");
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
    public static Service getWashService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Wash");
        service.setServiceValue("Wash");
        service.setServiceCode("6he");
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
    public static Service getHctEidHIVReferralService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("HIV services referral (HTC/EID)");
        service.setServiceValue("HIV services referral (HTC/EID)");
        service.setServiceCode("8he");
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
    public static Service getCommunityTBSymptomScreeningService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Community TB symptom screening");
        service.setServiceValue("Community TB symptom screening");
        service.setServiceCode("10he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getTBServicesReferralDiagnosisDOTS()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("TB services referral (Diagnosis-DOTS)");
        service.setServiceValue("TB services referral (Diagnosis-DOTS)");
        service.setServiceCode("11he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHealthReferralService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Health referral");
        service.setServiceValue("Health referral");
        service.setServiceCode("12he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getAdolescentHivPreventionService()
    {
        Service service=new Service();
        service.setDomainName("Health");
        service.setServiceName("Adolescent HIV prevention and sexual reproductive health services");
        service.setServiceValue("Adolescent HIV prevention and sexual reproductive health services");
        service.setServiceCode("13he");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getAdvocacyForSchoolEnrolmentService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Advocacy for school enrolment");//School enrolment/re-enrolment
        service.setServiceValue("Advocacy for school enrolment");
        service.setServiceCode("1ed");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }//Education awareness and engagement
    public static Service getWaiverOfSchoolFeeService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Advocacy for waiver of school fees");//Waiver of school fees
        service.setServiceValue("Advocacy for waiver of school fees");
        service.setServiceCode("2ed");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getProvisionOfSchoolMaterialService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Provision of school materials");//Provision of school materials/uniform
      service.setServiceValue("Provision of school materials");
      service.setServiceCode("3ed");
      service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSchoolPerformanceAssessmentService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("School performance assessment");
        service.setServiceValue("School performance assessment");
        service.setServiceCode("4ed");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getSchoolVisit()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("School visit");
        service.setServiceValue("School visit");
        service.setServiceCode("5ed");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getHolisticScholarshipService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Holistic scholarship");
        service.setServiceValue("Holistic scholarship");
        service.setServiceCode("6ed");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEducationReferralService()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Referral for educational services");//Referral for educational services
        service.setServiceValue("Referral");
        service.setServiceCode("7ed");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEducationAwarenessAndEngagement()
    {
        Service service=new Service();
        service.setDomainName("Education");
        service.setServiceName("Education awareness and engagement");
        service.setServiceValue("Education awareness and engagement");
        service.setServiceCode("8ed");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }//
    
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
    public static Service getBirthRegistrationService()
    {
        Service service=new Service();
        service.setDomainName("Child protection");
        service.setServiceName("Birth registration");
        service.setServiceValue("Birth registration");
        service.setServiceCode("3pt");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
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
        service.setServiceName("Micro-finance (savings and loans)");
        service.setServiceValue("Micro-finance (savings and loans)");
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
        //service.setServiceName("Savings and Internal Lending Community (SILC)");
        service.setServiceName("SILC");
        service.setServiceValue("SILC");
        service.setServiceCode("8es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static Service getEconomicStrengtheningReferral()
    {
        Service service=new Service();
        service.setDomainName("Economic Strengthening");
        service.setServiceName("Economic strenghtening referral");
        service.setServiceValue("Referral");
        service.setServiceCode("9es");
        service.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
        return service;
    }
    public static String getConcatenatedServiceCodes(String[] arrayOfServiceNames,boolean removeStartAndEndWildCharacters)
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
                    service=getService(codeOrName,removeStartAndEndWildCharacters);
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
    public static String getConcatenatedServiceNames(String[] arrayOfServiceCodes,boolean removeStartAndEndWildCharacters)
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
                    service=getService(codeOrName,removeStartAndEndWildCharacters);
                    serviceName=service.getServiceName();
                    if(i==0 || concatenatedServiceNames==null)
                    concatenatedServiceNames=serviceName;
                    else
                    {
                        concatenatedServiceNames+=","+serviceName;
                    }
                }
            }
        }
        return concatenatedServiceNames;
    }
    public static String getConcatenatedServiceNames(String concatenatedServiceCodes,boolean removeStartAndEndWildCharacters)
    {
        String concatenatedServiceNames=concatenatedServiceCodes;
        if(concatenatedServiceCodes !=null)
        {
            concatenatedServiceCodes=concatenatedServiceCodes.replaceAll(";", ",");
            String[] serviceCodeArray=concatenatedServiceCodes.split(",");
            concatenatedServiceNames=getConcatenatedServiceNames(serviceCodeArray,removeStartAndEndWildCharacters);
        }
        System.err.println("concatenatedServiceNames is "+concatenatedServiceNames);
        return concatenatedServiceNames;
    }
    public static Service getService(String serviceCodeOrName,boolean removeStartAndEndWildCharacters)
    {
        Service service=new Service();
        if(serviceCodeOrName !=null)
        {
            serviceCodeOrName=serviceCodeOrName.trim();
            if(serviceCodeOrName.equalsIgnoreCase("Counselling support") || serviceCodeOrName.equalsIgnoreCase("1ps"))
            service=getCounsellingSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase("Recreational activity") || serviceCodeOrName.equalsIgnoreCase("2ps"))
            service=getRecreationalService();
            else if(serviceCodeOrName.equalsIgnoreCase("Life skill support") || serviceCodeOrName.equalsIgnoreCase("3ps"))
            service=getLifeSkillSupportService();
            else if(serviceCodeOrName.equalsIgnoreCase("homevisit") || serviceCodeOrName.equalsIgnoreCase("4ps"))
            service=getHomeVisitService();
            else if(serviceCodeOrName.equalsIgnoreCase("Parenting skills") || serviceCodeOrName.equalsIgnoreCase("5ps"))
            service=getParentingSkills();
            else if(serviceCodeOrName.equalsIgnoreCase("Caregivers forum") || serviceCodeOrName.equalsIgnoreCase("6ps"))
            service=getCaregiverForum();
            else if(serviceCodeOrName.equalsIgnoreCase("Nutrition education and counselling") || serviceCodeOrName.equalsIgnoreCase("1nu"))
            service=getNutritionEducationAndCounsellingService();
            else if(serviceCodeOrName.equalsIgnoreCase("Food & nutritional supplements") || serviceCodeOrName.equalsIgnoreCase("2nu"))
            service=getFoodAndNutritionalSupplements();
            else if(serviceCodeOrName.equalsIgnoreCase("Vitamin A, Zinc and Iron suplement") || serviceCodeOrName.equalsIgnoreCase("3nu"))
            service=getVitaminAZincAndIronSuplement();
            else if(serviceCodeOrName.equalsIgnoreCase("Nutrition assessment, counselling and support (NACS)") || serviceCodeOrName.equalsIgnoreCase("NACS") || serviceCodeOrName.equalsIgnoreCase("Nutrition assessment counselling and support (NACS)") || serviceCodeOrName.equalsIgnoreCase("4nu"))
            service=getNutritionAssessmentCounsellingAndSupport();
            
            else if(serviceCodeOrName.equalsIgnoreCase("Growth monitoring") || serviceCodeOrName.equalsIgnoreCase("5nu"))
            service=getGrowthMonitoring();
            else if(serviceCodeOrName.equalsIgnoreCase("Nutrition referral") || serviceCodeOrName.equalsIgnoreCase("6nu"))
            service=getNutritionReferral();
            else if(serviceCodeOrName.equalsIgnoreCase(getFoodPreservation().getServiceValue()) || serviceCodeOrName.equalsIgnoreCase(getFoodPreservation().getServiceCode()))
            service=getFoodPreservation();
            else if(serviceCodeOrName.equalsIgnoreCase("Health education") || serviceCodeOrName.equalsIgnoreCase("1he"))
            service=getHealthEducationService();
            else if(serviceCodeOrName.equalsIgnoreCase("Treatment of minor illnesses") || serviceCodeOrName.equalsIgnoreCase("2he"))
            service=getTreatmentOfMinorIllnessesService();
            else if(serviceCodeOrName.equalsIgnoreCase("De-worming") || serviceCodeOrName.equalsIgnoreCase("3he"))
            service=getDewormingService();
            else if(serviceCodeOrName.equalsIgnoreCase("Insecticides treated bed net") || serviceCodeOrName.equalsIgnoreCase("4he"))
            service=getInsecticideTreatedBedNetService();
            else if(serviceCodeOrName.equalsIgnoreCase("Water treatment") || serviceCodeOrName.equalsIgnoreCase("5he"))
            service=getWaterTreatmentService();
            else if(serviceCodeOrName.equalsIgnoreCase("Wash") || serviceCodeOrName.equalsIgnoreCase("Water-Sanitation and Hygiene (WASH)")  || serviceCodeOrName.equalsIgnoreCase("6he"))
            service=getWashService();//
            else if(serviceCodeOrName.equalsIgnoreCase("Community HIV services (HTC/PMTCT)") || serviceCodeOrName.equalsIgnoreCase("7he"))
            service=getPmtctHctCommunityHIVService();
            else if(serviceCodeOrName.equalsIgnoreCase("HIV services referral (HTC/EID)") || serviceCodeOrName.equalsIgnoreCase("8he"))
            service=getHctEidHIVReferralService();
            else if(serviceCodeOrName.equalsIgnoreCase("Access for HIV care") || serviceCodeOrName.equalsIgnoreCase("9he"))
            service=getAccessForHIVCareService();
            else if(serviceCodeOrName.equalsIgnoreCase("Community TB symptom screening") || serviceCodeOrName.equalsIgnoreCase("10he"))
            service=getCommunityTBSymptomScreeningService();
            else if(serviceCodeOrName.equalsIgnoreCase("TB services referral (Diagnosis-DOTS)") || serviceCodeOrName.equalsIgnoreCase("11he"))
            service=getTBServicesReferralDiagnosisDOTS();
            else if(serviceCodeOrName.equalsIgnoreCase("Health referral") || serviceCodeOrName.equalsIgnoreCase("12he"))
            service=getHealthReferralService();
            
            else if(serviceCodeOrName.equalsIgnoreCase("Advocacy for school enrolment") || serviceCodeOrName.equalsIgnoreCase("Sensitization for child school enrolment/re-enrolment") || serviceCodeOrName.equalsIgnoreCase("1ed"))
            service=getAdvocacyForSchoolEnrolmentService();
            else if(serviceCodeOrName.equalsIgnoreCase("Advocacy for waiver of school fees") || serviceCodeOrName.equalsIgnoreCase("2ed"))
            service=getWaiverOfSchoolFeeService();
            else if(serviceCodeOrName.equalsIgnoreCase("Provision of school materials") || serviceCodeOrName.equalsIgnoreCase("3ed"))
            service=getProvisionOfSchoolMaterialService();
            else if(serviceCodeOrName.equalsIgnoreCase("School performance assessment") || serviceCodeOrName.equalsIgnoreCase("4ed"))
            service=getSchoolPerformanceAssessmentService();
            else if(serviceCodeOrName.equalsIgnoreCase("School visit") || serviceCodeOrName.equalsIgnoreCase("5ed"))
            service=getSchoolVisit();
            else if(serviceCodeOrName.equalsIgnoreCase("Holistic scholarship") || serviceCodeOrName.equalsIgnoreCase("6ed"))
            service=getHolisticScholarshipService();
            else if(serviceCodeOrName.equalsIgnoreCase("Referral") || serviceCodeOrName.equalsIgnoreCase("7ed"))
            service=getEducationReferralService();
            else if(serviceCodeOrName.equalsIgnoreCase(getEducationAwarenessAndEngagement().getServiceName()) || serviceCodeOrName.equalsIgnoreCase(getEducationAwarenessAndEngagement().getServiceCode()))
            service=getEducationAwarenessAndEngagement();
            else if(serviceCodeOrName.equalsIgnoreCase("Legal services") || serviceCodeOrName.equalsIgnoreCase("1pt"))
            service=getLegalServices();
            else if(serviceCodeOrName.equalsIgnoreCase("Succession planning") || serviceCodeOrName.equalsIgnoreCase("2pt"))
            service=getSuccessionPlanning();
            else if(serviceCodeOrName.equalsIgnoreCase("Birth registration") || serviceCodeOrName.equalsIgnoreCase("3pt"))
            service=getBirthRegistrationService();
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
            else if(serviceCodeOrName.equalsIgnoreCase("Micro-finance (savings and loans)") || serviceCodeOrName.equalsIgnoreCase("2es"))
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
            else if(serviceCodeOrName.equalsIgnoreCase("SILC") || serviceCodeOrName.equalsIgnoreCase("8es"))
            service=getSavingsAndInternalLendingCommunity();
            else if(serviceCodeOrName.equalsIgnoreCase("Economic strenghtening referral") || serviceCodeOrName.equalsIgnoreCase("9es"))
            service=getEconomicStrengtheningReferral();
            else
            {
                if(serviceCodeOrName.length()>0)
                {
                    if(!removeStartAndEndWildCharacters)
                    serviceCodeOrName=";"+serviceCodeOrName;
                    else
                    serviceCodeOrName=cleanServiceName(serviceCodeOrName);
                    service.setServiceCode(serviceCodeOrName);
                    service.setServiceName(serviceCodeOrName);
                }
            }
        }
        
        return service;
    }
    public static String cleanServiceName(String serviceName)
    {
        if(serviceName !=null)
        {
            serviceName=serviceName.trim();
            if(serviceName.startsWith(";") ||serviceName.startsWith(":") || serviceName.startsWith(","))
            serviceName=serviceName.substring(1);
            if(serviceName.endsWith(";") ||serviceName.endsWith(":") || serviceName.endsWith(","))
            serviceName=serviceName.substring(0, serviceName.length()-2);
            serviceName=serviceName.trim();
        }
        return serviceName;
    }
}
