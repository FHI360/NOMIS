/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import com.nomis.operationsManagement.OvcServiceAttributesManager;


/**
 *
 * @author smomoh
 */
public class ReferralReportManager 
{
    public static String getReferralServiceCode(String indicatorId)
    {
        String serviceCode=null;
        if(indicatorId !=null)
        {
            if(indicatorId.equalsIgnoreCase("vcinsuppgrp"))
            serviceCode=OvcServiceAttributesManager.getStructuredPLHIVSupportGroupService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcstatnysup"))
            serviceCode=OvcServiceAttributesManager.getSchoolUniformsAndBooksAssistance().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcschfeesup"))
            serviceCode=OvcServiceAttributesManager.getExamFeeSupport().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcemgshlter"))
            serviceCode=OvcServiceAttributesManager.getEmergencyShelterAndCareFacilityService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vclegalmgbv"))
            serviceCode=OvcServiceAttributesManager.getLegalAssistanceRelatedToMaltreatmentAndGBV().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcbirthregn")) 
            serviceCode=OvcServiceAttributesManager.getBirthRegistrationAcquisitionService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcpostviocn"))
            serviceCode=OvcServiceAttributesManager.getPostViolenceTraumaSupportService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcdevmilhiv"))
            serviceCode=OvcServiceAttributesManager.getTrackedDevelopmentalMilestoneService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcchldright"))
            serviceCode=OvcServiceAttributesManager.getChildRightsSession().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vchivtrtlit"))
            serviceCode=OvcServiceAttributesManager.getAgeAppropriateHIVTreatmentLiteracyService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vchivdiscrp"))
            serviceCode=OvcServiceAttributesManager.getAgeAppropriateCounsellingAndHIVDisclosureSupportService().getServiceCode();
                       
            
            if(indicatorId.equalsIgnoreCase("cgrefrhcrpe"))
            serviceCode=OvcServiceAttributesManager.getReferralForRoutineHealthCareService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcrefrhcrpe"))
            serviceCode=OvcServiceAttributesManager.getReferralForRoutineHealthCareService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("cgrefstirpe"))
            serviceCode=OvcServiceAttributesManager.getHivCounsellingReferral().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcrefstirpe"))
            serviceCode=OvcServiceAttributesManager.getHivCounsellingReferral().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcrefhivtrc"))
            serviceCode="HIV";
            else if(indicatorId.equalsIgnoreCase("cgrefhivtrc")) 
            serviceCode="HIV";
            else if(indicatorId.equalsIgnoreCase("vcrefeidrpe"))
            serviceCode=OvcServiceAttributesManager.getReferralForEIDService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcrefimzrpe"))
            serviceCode=OvcServiceAttributesManager.getReferralForImmunizationService().getServiceCode();
            
            
            else if(indicatorId.equalsIgnoreCase("cgbetparent"))
            serviceCode=OvcServiceAttributesManager.getEvidenceBasedParentingService().getServiceCode();
            //else if(indicatorId.equalsIgnoreCase("cgsinovotns"))
            //serviceCode=OvcServiceAttributesManager.getReferralForImmunizationService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("cghivdiscrp"))
            serviceCode=OvcServiceAttributesManager.getAgeAppropriateCounsellingAndHIVDisclosureSupportService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("cgfinlitrcy"))
            serviceCode=OvcServiceAttributesManager.getFinancialLiteracyTraining().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("cgsmbussupp"))
            serviceCode=OvcServiceAttributesManager.getMarketLinkages().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("cgemgshlter"))
            serviceCode=OvcServiceAttributesManager.getEmergencyShelterAndCareFacilityService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("cgsoftskill"))
            serviceCode=OvcServiceAttributesManager.getBusinessSkillsTraining().getServiceCode();
                       
            
            
            
            if(indicatorId.equalsIgnoreCase("vchivadhsup") || indicatorId.equalsIgnoreCase("cghivadhsup"))
            serviceCode=OvcServiceAttributesManager.getHIVAdherenceSupportGroupService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcplhasupgp") || indicatorId.equalsIgnoreCase("cgplhasupgp"))
            serviceCode=OvcServiceAttributesManager.getStructuredPLHIVSupportGroupService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcstimulatn") || indicatorId.equalsIgnoreCase("cgstimulatn"))
            serviceCode=OvcServiceAttributesManager.getEarlyChildStimulationService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcemergtran") || indicatorId.equalsIgnoreCase("cgemergtran"))
            serviceCode=OvcServiceAttributesManager.getEmergencyTransportAssistanceService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcmbbcourse") || indicatorId.equalsIgnoreCase("cgmbbcourse"))
            serviceCode=OvcServiceAttributesManager.getMotherBabyCourseServices().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcwashmsgrp") || indicatorId.equalsIgnoreCase("cgwashmsgrp"))
            serviceCode=OvcServiceAttributesManager.getWASHServices().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcevidbpart") || indicatorId.equalsIgnoreCase("cgevidbpart"))
            serviceCode=OvcServiceAttributesManager.getEvidenceBasedParentingService().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vcenterptrg") || indicatorId.equalsIgnoreCase("cgenterptrg"))
            serviceCode=OvcServiceAttributesManager.getEntrepreneurshipandBusinessManagementTraining().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vclinkdsilc") || indicatorId.equalsIgnoreCase("cglinkdsilc"))
            serviceCode=OvcServiceAttributesManager.getSavingsAndInternalLendingCommunity().getServiceCode();
            else if(indicatorId.equalsIgnoreCase("vclinktofin") || indicatorId.equalsIgnoreCase("cglinktofin"))
            serviceCode=OvcServiceAttributesManager.getLinkagesToFinancialInstitutionsAndPrivateSector().getServiceCode();
            
        }
        return serviceCode;
    }
}
