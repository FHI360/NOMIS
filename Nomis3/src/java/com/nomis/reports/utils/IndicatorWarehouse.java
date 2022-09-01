/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;


import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class IndicatorWarehouse implements Serializable
{
    
    
    public Indicator getIndicatorById(String indicatorId)
    {
        //System.err.println("indicatorId is "+indicatorId);
        Indicator indicator=new Indicator();
        if(indicatorId !=null)
        {
            //
            IndicatorDictionary ind=new IndicatorDictionary();
            if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfGraduatedHouseholdsWhoseBeneficiariesWereServedWithinReportingPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfGraduatedHouseholdsWhoseBeneficiariesWereServedWithinReportingPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorNumberOfHouseholdsWhoseBeneficiariesWereServedWithinReportingPeriod().getIndicatorId()))
            indicator=ind.getIndicatorNumberOfHouseholdsWhoseBeneficiariesWereServedWithinReportingPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForOVC_ART_OFFER().getIndicatorId()))
            indicator=ind.getIndicatorForOVC_ART_OFFER();
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_ART_OFFERIndicator().getIndicatorId()))
            indicator=ind.getOVC_ART_OFFERIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHouseholdsServedAndAssessedOnAbilityToMeetEmergencyNeedsWithinReportingPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHouseholdsServedAndAssessedOnAbilityToMeetEmergencyNeedsWithinReportingPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_ART_ENROLLIndicator().getIndicatorId()))
            indicator=ind.getOVC_ART_ENROLLIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForOVC_ART_ENROLL().getIndicatorId()))
            indicator=ind.getIndicatorForOVC_ART_ENROLL();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTestAndDidNotReceiveTest().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTestAndDidNotReceiveTest();
            if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTestAndCompletedReferral().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTestAndCompletedReferral();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTest().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcAtRiskOfHivAndReferredForHivTest();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcWhoHadAChangeInRiskProfileSinceLastHivRisk().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcWhoHadAChangeInRiskProfileSinceLastHivRisk();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcEligibleForHIVRiskAssessment().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcEligibleForHIVRiskAssessment();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOVC_HIVTestNotIndicated().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOVC_HIVTestNotIndicated();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForProportionOfOvcServedWithinTheReportPeriodThatHasBirthCert().getIndicatorId()))
            indicator=ind.getIndicatorForProportionOfOvcServedWithinTheReportPeriodThatHasBirthCert();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForProportionOfOvcWithKnownHIVStatusMER().getIndicatorId()))
            indicator=ind.getIndicatorForProportionOfOvcWithKnownHIVStatusMER();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcWithKnownHIVStatusMER().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcWithKnownHIVStatusMER();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfChildrenOfKeyPopServedMer().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfChildrenOfKeyPopServedMer();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHIVExposedInfantsServedMer().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHIVExposedInfantsServedMer();
            if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfChildrenLivingWithPLHIVServedMer().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfChildrenLivingWithPLHIVServedMer();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfChildrenLivingWithHivServedMer().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfChildrenLivingWithHivServedMer();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfSurvivorsOfViolenceAgainstChildrenServedMer().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfSurvivorsOfViolenceAgainstChildrenServedMer();
            if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfAdolescentsAtHighRiskOfHIVServedMER().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfAdolescentsAtHighRiskOfHIVServedMER();
            
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveBeneficiariesServedMER().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfActiveBeneficiariesServedMER();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfGraduatedBeneficiariesServedMER().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfGraduatedBeneficiariesServedMER();
            
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfNewEnrolledBeneficiaries().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfNewEnrolledBeneficiaries();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithHiv().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithHiv();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithPLHIV().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfNewEnrolledChildrenLivingWithPLHIV();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfNewEnrolledChildrenOfKeyPop().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfNewEnrolledChildrenOfKeyPop();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfNewEnrolledHIVExposedInfants().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfNewEnrolledHIVExposedInfants();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfNewEnrolledSurvivorsOfViolenceAgainstChildren().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfNewEnrolledSurvivorsOfViolenceAgainstChildren();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfNewEnrolledAdolescentsAtHighRiskOfHIV().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfNewEnrolledAdolescentsAtHighRiskOfHIV();
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_NEWIndicator().getIndicatorId()))
            indicator=ind.getOvc_NEWIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHIVUnknownAndHivNegativeOvc().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHIVUnknownAndHivNegativeOvc();
            else if(indicatorId.equalsIgnoreCase(ind.getNoOfOvcAssessedForHIVRiskWithinReportPeriod().getIndicatorId()))
            indicator=ind.getNoOfOvcAssessedForHIVRiskWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForProportionOfOvcAssessedForHIVRisk().getIndicatorId()))
            indicator=ind.getIndicatorForProportionOfOvcAssessedForHIVRisk();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfBeneficiariesWhoAreVirallySuppresedInPast12Months().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfBeneficiariesWhoAreVirallySuppresedInPast12Months();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForProportionOfBeneficiariesWhoAreVirallySuppresedInPast12Months().getIndicatorId()))
            indicator=ind.getIndicatorForProportionOfBeneficiariesWhoAreVirallySuppresedInPast12Months();
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_VLRIndicator().getIndicatorId()))
            indicator=ind.getOVC_VLRIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForProportionOfBeneficiariesWithViralLoadResultInPast12Months().getIndicatorId()))
            indicator=ind.getIndicatorForProportionOfBeneficiariesWithViralLoadResultInPast12Months();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfBeneficiariesWithViralLoadResultInPast12Months().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfBeneficiariesWithViralLoadResultInPast12Months();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveAndGraduatedHivPositiveBeneficiariesEnrolledOnTreatment().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfActiveAndGraduatedHivPositiveBeneficiariesEnrolledOnTreatment();
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_VL_ELIGIBLEIndicator().getIndicatorId()))
            indicator=ind.getOVC_VL_ELIGIBLEIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfBeneficiariesEligibleForViralLoad().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfBeneficiariesEligibleForViralLoad();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForProportionOfBeneficiariesEligibleForViralLoad().getIndicatorId()))
            indicator=ind.getIndicatorForProportionOfBeneficiariesEligibleForViralLoad();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcAssessedForHivRiskReferredForHivTestTestedPositiveAndEnrolledOnARTWithinTheReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcAssessedForHivRiskReferredForHivTestTestedPositiveAndEnrolledOnARTWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcAssessedForHivRiskReferredForHivTestAndTestedPositiveWithinTheReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcAssessedForHivRiskReferredForHivTestAndTestedPositiveWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveCaregivers18T024ServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfActiveCaregivers18T024ServedWithinTheReportPeriodForDatim();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveCaregivers25PlusServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfActiveCaregivers25PlusServedWithinTheReportPeriodForDatim();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfGraduatedCaregivers18To24ServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfGraduatedCaregivers18To24ServedWithinTheReportPeriodForDatim();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfGraduatedCaregivers25PlusServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfGraduatedCaregivers25PlusServedWithinTheReportPeriodForDatim();
            
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcAbusedWithinReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcAbusedWithinReportPeriod();//
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveOvcServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfActiveOvcServedWithinTheReportPeriodForDatim();
            
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriodForDatim();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfGraduatedOvcServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfGraduatedOvcServedWithinTheReportPeriodForDatim();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfGraduatedCaregiversServedWithinTheReportPeriodForDatim().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfGraduatedCaregiversServedWithinTheReportPeriodForDatim();
            
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivUnknownOrNegativeOvcServedWithinTheReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivUnknownOrNegativeOvcServedWithinTheReportPeriod();
            
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcProvidedReferralForHIVRelatedTestingService().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcProvidedReferralForHIVRelatedTestingService();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfAdultMembersProvidedReferralForHIVRelatedTestingService().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedReferralForHIVRelatedTestingService();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivNegativeOvcAssessedonHIVRisk().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcAssessedonHIVRisk();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivUnknownOvcAssessedonHIVRisk().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcAssessedonHIVRisk();
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_NUTRITIONIndicator().getIndicatorId()))
            indicator=ind.getOvc_NUTRITIONIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_EDUIndicator().getIndicatorId()))
            indicator=ind.getOvc_EDUIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getOVC_HIVRISKASSESSEDIndicator().getIndicatorId()))
            indicator=ind.getOVC_HIVRISKASSESSEDIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_BIRTHCERTIndicator().getIndicatorId()))
            indicator=ind.getOvc_BIRTHCERTIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfAdultMembersSelfReportingAdherenceToTreatment().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfAdultMembersSelfReportingAdherenceToTreatment();
            else if(indicatorId.equalsIgnoreCase(ind.getOvc_ARTSUPPIndicator().getIndicatorId()))
            indicator=ind.getOvc_ARTSUPPIndicator();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivPositiveOvcEnrolledOnARTWithinTheReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcEnrolledOnARTWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcNewlyTestedPositiveWithinTheReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcNewlyTestedPositiveWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfCaregiversNewlyTestedPositive().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfCaregiversNewlyTestedPositive();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHIVPositiveCaregiversNewlyEnrolledOnARTWithinTheReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversNewlyEnrolledOnARTWithinTheReportPeriod();
            //
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivPositiveEnrolledOnTreatmentInReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivPositiveEnrolledOnTreatmentInReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfChildrenOrphanedByAIDSEnrolled().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfChildrenOrphanedByAIDSEnrolled();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfAdolescentFemalesAtRiskOfTransactionalSexEnrolled().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfAdolescentFemalesAtRiskOfTransactionalSexEnrolled();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfChildrenAtRiskOfOrHaveExperiencedSexualViolenceEnrolled().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfChildrenAtRiskOfOrHaveExperiencedSexualViolenceEnrolled();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfChildrenAtHeightenedRiskOfHIVInfectionEnrolled().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfChildrenAtHeightenedRiskOfHIVInfectionEnrolled();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfBeneficiariesServedWithinTheReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfBeneficiariesServedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHIVUnknownBeneficiariesCurrentlyEnrolled().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHIVUnknownBeneficiariesCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHIVNegativeBeneficiariesCurrentlyEnrolled().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHIVNegativeBeneficiariesCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivUnknownOvcWithTestNotIndicated().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcWithTestNotIndicated();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivUnknownOvcAtRiskOfHivInfection().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcAtRiskOfHivInfection();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcNotScreenedForHivRisk().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcNotScreenedForHivRisk();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivPositiveNotOnTreatment().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivPositiveNotOnTreatment();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHivPositiveOnTreatment().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHivPositiveOnTreatment();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveAdultMembersServedInReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfActiveAdultMembersServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfActiveOvcServedInReportPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfActiveOvcServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcinsuppgrp"))
            indicator=ind.getIndicatorForNumberOfChildrenParticipatingInSupportGroup();
            else if(indicatorId.equalsIgnoreCase("vcstatnysup"))
            indicator=ind.getIndicatorForNumberOfChildrenProvidedStationarySupport();
            else if(indicatorId.equalsIgnoreCase("vcschfeesup"))
            indicator=ind.getIndicatorForNumberOfChildrenProvidedSchoolFeeSupport();
            else if(indicatorId.equalsIgnoreCase("vcemgshlter"))
            indicator=ind.getIndicatorForNumberOfChildrenProvidedEmergencyShelterSupport();
            else if(indicatorId.equalsIgnoreCase("vclegalmgbv"))
            indicator=ind.getIndicatorForNumberOfChildrenProvidedLegalAssistanceRelatedToMaltreatmentAndGBV();
            else if(indicatorId.equalsIgnoreCase("vcbirthregn"))
            indicator=ind.getIndicatorForNumberOfChildrenAssistedWithBirthRegistration();
            else if(indicatorId.equalsIgnoreCase("vcpostviocn"))
            indicator=ind.getIndicatorForNumberOfChildrenProvidedPostViolenceTraumaCounsessling();
            else if(indicatorId.equalsIgnoreCase("vcchldright"))
            indicator=ind.getIndicatorForNumberOfChildrenParticipatingInChildRightsSession();
            else if(indicatorId.equalsIgnoreCase("vcdevmilhiv"))
            indicator=ind.getIndicatorForNumberOfChildrenTrackedDevMilestoneInHiv();
            else if(indicatorId.equalsIgnoreCase("vchivtrtlit"))
            indicator=ind.getIndicatorForNumberOfChildrenProvidedAgeAppropriateHIVTreatmentLiteracyServices();
            else if(indicatorId.equalsIgnoreCase("vchivdiscrp"))
            indicator=ind.getIndicatorForNumberOfChildrenProvidedAgeAppropriateCounsellingAndHIVDisclosureSupport();
            
            
            
            else if(indicatorId.equalsIgnoreCase("cgbetparent"))
            indicator=ind.getIndicatorForNumberOfCaregiversParticipatingInBetterParentingPlus();
            else if(indicatorId.equalsIgnoreCase("cgsinovotns"))
            indicator=ind.getIndicatorForNumberOfCaregiversParticipatingInSinovoTeens();
            else if(indicatorId.equalsIgnoreCase("cghivdiscrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedAgeAppropriateCounsellingAndHIVDisclosureSupport();
            else if(indicatorId.equalsIgnoreCase("cgfinlitrcy"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedFinancialLiteracyTraining();
            else if(indicatorId.equalsIgnoreCase("cgsmbussupp"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedSmallBusinessSupport();
            else if(indicatorId.equalsIgnoreCase("cgemgshlter"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedEmergencyShelterSupport();
            else if(indicatorId.equalsIgnoreCase("cgsoftskill"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedSoftSkillsTraining();
            
            
            else if(indicatorId.equalsIgnoreCase("vcultrapohh"))
            indicator=ind.getIndicatorForNumberOfChildrenEnrolledFromUtraPoorHouseholds();
            else if(indicatorId.equalsIgnoreCase("cghivadhsup"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedHIVAdherenceSupportGroupService();
            else if(indicatorId.equalsIgnoreCase("cgplhasupgp"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedStructuredPLHASupportGroupService();
            else if(indicatorId.equalsIgnoreCase("cgstimulatn"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedEarlyChildStimulationService();
            else if(indicatorId.equalsIgnoreCase("cgemergtran"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedEmergencyTransportAssistanceService();
            else if(indicatorId.equalsIgnoreCase("cgmbbcourse"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedMotherBabyCourseServices();
            else if(indicatorId.equalsIgnoreCase("cgwashmsgrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedWASHServices();
            
            else if(indicatorId.equalsIgnoreCase("cgevidbpart"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedEvidenceBasedParentingService();
            else if(indicatorId.equalsIgnoreCase("cgenterptrg"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedEntrepreneurshipandBusinessManagementTraining();
            else if(indicatorId.equalsIgnoreCase("cglinkdsilc"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedSavingsAndInternalLendingCommunity();
            else if(indicatorId.equalsIgnoreCase("cglinktofin"))
            indicator=ind.getIndicatorForNumberOfAdultMembersProvidedLinkagesToFinancialInstitutionsAndPrivateSector();
            
            else if(indicatorId.equalsIgnoreCase("vchivadhsup"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedHIVAdherenceSupportGroupService();
            else if(indicatorId.equalsIgnoreCase("vcplhasupgp"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedStructuredPLHASupportGroupService();
            else if(indicatorId.equalsIgnoreCase("vcstimulatn"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedEarlyChildStimulationService();
            else if(indicatorId.equalsIgnoreCase("vcemergtran"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedEmergencyTransportAssistanceService();
            else if(indicatorId.equalsIgnoreCase("vcmbbcourse"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedMotherBabyCourseServices();
            else if(indicatorId.equalsIgnoreCase("vcwashmsgrp"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedWASHServices();
            else if(indicatorId.equalsIgnoreCase("vcevidbpart"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedEvidenceBasedParentingService();
            else if(indicatorId.equalsIgnoreCase("vcenterptrg"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedEntrepreneurshipandBusinessManagementTraining();
            else if(indicatorId.equalsIgnoreCase("vclinkdsilc"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedSavingsAndInternalLendingCommunity();
            else if(indicatorId.equalsIgnoreCase("vclinktofin"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedLinkagesToFinancialInstitutionsAndPrivateSector();
            
            else if(indicatorId.equalsIgnoreCase("cgrefhgrprp"))
            indicator=ind.getIndicatorForNumberOfAdultMembersReferredForHivSupportGroup();
            else if(indicatorId.equalsIgnoreCase("bncasepland"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesWithCasePlan();
            else if(indicatorId.equalsIgnoreCase("vccasepland"))
            indicator=ind.getIndicatorForNumberOfChildrenWithCasePlan();
            else if(indicatorId.equalsIgnoreCase("hhcasepland"))
            indicator=ind.getIndicatorForNumberOfHouseholdsWithCasePlan();
            else if(indicatorId.equalsIgnoreCase("cgrefpmtcrp"))
            indicator=ind.getIndicatorForNumberOfAdultMembersReferredForPMTCT();
            else if(indicatorId.equalsIgnoreCase("vcrefpeprpe"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForPEP();
            //else if(indicatorId.equalsIgnoreCase("cgrefpeprpe"))
            //indicator=ind.getIndicatorForNumberOfOvcReferredForImmunization();
            
            else if(indicatorId.equalsIgnoreCase("vcrefmuacrp"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForMUAC();
            else if(indicatorId.equalsIgnoreCase("vcrefimzrpe"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForImmunization();
            
            else if(indicatorId.equalsIgnoreCase("vcrefeidrpe"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForEID();
            else if(indicatorId.equalsIgnoreCase("cgrefrhcrpe"))
            indicator=ind.getIndicatorForNumberOfAdultMembersReferredForRoutineHealthCare();
            else if(indicatorId.equalsIgnoreCase("vcrefrhcrpe"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForRoutineHealthCare();
            
            else if(indicatorId.equalsIgnoreCase("vcrefstirpe"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForSTITreatment();
            else if(indicatorId.equalsIgnoreCase("cgrefstirpe"))
            indicator=ind.getIndicatorForNumberOfAdultMembersReferredForSTITreatment();
            else if(indicatorId.equalsIgnoreCase("vcrefhivtrc"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForHivTreatmentAndCare();
            else if(indicatorId.equalsIgnoreCase("cgrefhivtrc"))
            indicator=ind.getIndicatorForNumberOfAdultMembersReferredForHivTreatmentAndCare();
            else if(indicatorId.equalsIgnoreCase("cgreferedrp"))
            indicator=ind.getIndicatorForNumberOfAdultMembersReferred();
            else if(indicatorId.equalsIgnoreCase("vcreferedrp"))
            indicator=ind.getIndicatorForNumberOfOvcReferred();
            else if(indicatorId.equalsIgnoreCase("hhnoaddress"))
            indicator=ind.getIndicatorForNumberOfHouseholdsWithoutAddress();
            else if(indicatorId.equalsIgnoreCase("nobthregdoc"))
            indicator=ind.getIndicatorForNumberOfOvcWithNoDocumentedBirthRegistrationStatus();
            else if(indicatorId.equalsIgnoreCase("noschstadoc"))
            indicator=ind.getIndicatorForNumberOfOvcWithNoDocumentedSchoolStatus();
            else if(indicatorId.equalsIgnoreCase("vcteenmthen"))
            indicator=ind.getIndicatorForNumberOfTeenMothersEnrolled();
            else if(indicatorId.equalsIgnoreCase("vcadolscenr"))
            indicator=ind.getIndicatorForNumberOfAdolescentGirlsEnrolled();
            else if(indicatorId.equalsIgnoreCase("vcmalnouenr"))
            indicator=ind.getIndicatorForNumberOfStuntedOrMalnourishedChildrenEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivposcge"))
            indicator=ind.getIndicatorForNumberOfChildrenOfHIVPositiveCaregiversEnrolled();
            else if(indicatorId.equalsIgnoreCase("vcplhivenrl"))
            indicator=ind.getIndicatorForNumberOfChildrenLivingWithHIVEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivexpenr"))
            indicator=ind.getIndicatorForNumberOfHIVExposedChildrenEnrolled();
            else if(indicatorId.equalsIgnoreCase("vcdisablden"))
            indicator=ind.getIndicatorForNumberOfChildrenLivingWithDisabilityEnrolled();
            else if(indicatorId.equalsIgnoreCase("vcabusedenr"))
            indicator=ind.getIndicatorForNumberOfAbusedChildrenEnrolled() ;
            else if(indicatorId.equalsIgnoreCase("vcstreetenr"))
            indicator=ind.getIndicatorForNumberOfStreetChildrenEnrolled();
            else if(indicatorId.equalsIgnoreCase("vckeypopenr"))
            indicator=ind.getIndicatorForNumberOfChildrenOfKeyPopEnrolled();
            else if(indicatorId.equalsIgnoreCase("vcdouborpen"))
            indicator=ind.getIndicatorForNumberOfOvcWhoWereEnrolledAsDoubleOrphans();
            else if(indicatorId.equalsIgnoreCase("vcpatorphen"))
            indicator=ind.getIndicatorForNumberOfOvcWhoWereEnrolledAsPaternalOrphans();
            else if(indicatorId.equalsIgnoreCase("vcmatorphen"))
            indicator=ind.getIndicatorForNumberOfOvcWhoWereEnrolledAsMaternalOrphans();
            else if(indicatorId.equalsIgnoreCase("vcorphanenr"))
            indicator=ind.getIndicatorForNumberOfOvcWhoWereEnrolledAsOrphans();
            else if(indicatorId.equalsIgnoreCase("ahmcurenrol"))
            indicator=ind.getIndicatorForNumberOfAdultMembersCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("ahmenrollrp"))
            indicator=ind.getIndicatorForNumberOfAdultMembersEnrolledWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("ahmnotserve"))
            indicator=ind.getIndicatorForNumberOfAdultMembersWithoutServiceRecords();
            else if(indicatorId.equalsIgnoreCase("vcservedrpe"))
            indicator=ind.getIndicatorForNumberOfOvcServedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfBeneficiariesBenefitedFromMotherBabyCourses().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfBeneficiariesBenefitedFromMotherBabyCourses();
            else if(indicatorId.equalsIgnoreCase("gbvphemovio"))
            indicator=ind.getIndicatorForNumberOfPhysicalAndEmotionalViolenceGBVCases();
            else if(indicatorId.equalsIgnoreCase("gbvsexuavio"))
            indicator=ind.getIndicatorForNumberOfSexualViolenceGBVCases();
            else if(indicatorId.equalsIgnoreCase("begbvpepser"))
            indicator=ind.getIndicatorForNumberOfGBVBeneficiariesProvidedPEP();
            else if(indicatorId.equalsIgnoreCase("bnparcgsupp"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesProvidedParentingOrCaregiverSupport();
            else if(indicatorId.equalsIgnoreCase("bnptcontedu"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesProvidedPartTimeContinuingEducationServices();
            else if(indicatorId.equalsIgnoreCase("bnedusubsdy"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesProvidedEducationSubsidyServices();
            else if(indicatorId.equalsIgnoreCase("bnworkreadn"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesProvidedWorkReadinessServices();
            else if(indicatorId.equalsIgnoreCase("bnsocassetb"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesProvidedSocialAssetBuildingServices();
            else if(indicatorId.equalsIgnoreCase("bnreffaccbo"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesReferredFromFacilityToCBO();
            else if(indicatorId.equalsIgnoreCase("bnrefcbofac"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesReferredFromCBOToFacility();
            else if(indicatorId.equalsIgnoreCase("vcnonartser"))
            indicator=ind.getIndicatorForNumberOfHivPositiveNotOnTreatmentServed();
            else if(indicatorId.equalsIgnoreCase("vchivartser"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOnTreatmentServed();
            else if(indicatorId.equalsIgnoreCase("vcnotscrser"))
            indicator=ind.getIndicatorForNumberOfOvcNotScreenedForHivRiskServed();
            else if(indicatorId.equalsIgnoreCase("vchivrnrser"))
            indicator=ind.getIndicatorForNumberOfOvcTestedButDidNotReceivedResultServed();
            else if(indicatorId.equalsIgnoreCase("vchivundser"))
            indicator=ind.getIndicatorForNumberOfOvcWithUndisclosedHivStatusServed();
            else if(indicatorId.equalsIgnoreCase("vcnoartserv"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcNotOnARTCurrentlyEnrolledProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("vchivrskser"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcAtRiskOfHivInfectionServed();
            else if(indicatorId.equalsIgnoreCase("vcservstbrp"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedStableServices();
            else if(indicatorId.equalsIgnoreCase("vcservsafrp"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedSafetyServices();
            else if(indicatorId.equalsIgnoreCase("vcservrefrp"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedReferralServices();
            else if(indicatorId.equalsIgnoreCase("vcservhthrp"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedHealthServices();
            else if(indicatorId.equalsIgnoreCase("vcservschrp"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedSchoolServices();
            
            else if(indicatorId.equalsIgnoreCase("vctranspepf"))
            indicator=ind.getIndicatorForNumberOfOvcTransferedToPEPFAR();
            else if(indicatorId.equalsIgnoreCase("vctranonpep"))
            indicator=ind.getIndicatorForNumberOfOvcTransferedToNonPEPFAR();
            else if(indicatorId.equalsIgnoreCase("vchivprevrp"))
            indicator=ind.getIndicatorForNumberOfAdolescentsProvidedHIVPreventionServices();
            else if(indicatorId.equalsIgnoreCase("vclgovptvrp"))
            indicator=ind.getIndicatorForNumberOfOvcLinkedToGovtForPostViolenceServicesWithinReportPeriod();
            //getNoOfHivUnknownOvcAssessedForHIVRiskWithinReportPeriod()
            else if(indicatorId.equalsIgnoreCase(ind.getNoOfHivNegativeOvcAssessedForHIVRiskWithinReportPeriod().getIndicatorId()))
            indicator=ind.getNoOfHivNegativeOvcAssessedForHIVRiskWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getNoOfHivUnknownOvcAssessedForHIVRiskWithinReportPeriod().getIndicatorId()))
            indicator=ind.getNoOfHivUnknownOvcAssessedForHIVRiskWithinReportPeriod();
            
            else if(indicatorId.equalsIgnoreCase("vcnegrskser"))
            indicator=ind.getNoOfHivNegativeOvcAssessedForHIVRiskAndServedWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcunkrskser"))
            indicator=ind.getNoOfHivUnknownOvcAssessedForHIVRiskAndServedWithinReportPeriod();
            
            else if(indicatorId.equalsIgnoreCase("vchivrskser"))
            indicator=ind.getNoOfOvcAssessedForHIVRiskAndServedWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOVC_HIVSTATPOSITIVE().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOVC_HIVSTATPOSITIVE();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOVC_HIVSTATNEGATIVE().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOVC_HIVSTATNEGATIVE();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOVC_HIVSTATUNKNOWN().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOVC_HIVSTATUNKNOWN();
            else if(indicatorId.equalsIgnoreCase("vchivposser"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcIdentifiedAndServedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vceeunkrass"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcEverEnrolledThatHaveBeenAssessedonHIVRisk();
            else if(indicatorId.equalsIgnoreCase("vceenegrass"))
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcEverEnrolledThatHaveBeenAssessedonHIVRisk();
            else if(indicatorId.equalsIgnoreCase("vcexitnogra"))
            indicator=ind.getIndicatorForNumberOfOvcExitedWithoutGraduation();
            else if(indicatorId.equalsIgnoreCase("vcsmalnsern"))
            indicator=ind.getIndicatorForNumberOfSeverelyMalnourishedOvcServedNutritonalServices();
            else if(indicatorId.equalsIgnoreCase("vcceserbcer"))
            indicator=ind.getNoOfOvcServedWithinTheReportPeriodThatHasBirthCert();
            else if(indicatorId.equalsIgnoreCase("vchivposide"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcIdentifiedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vccgnenserv"))
            indicator=ind.getIndicatorForNumberOfBeneficiariesNewlyEnrolledWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vccelt18cer"))
            indicator=ind.getIndicatorForNumberOfOvcLessThan18CurrentlyEnrolledWithBirthCertificate();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcAssessedForHivRiskAndDeterminedToBeAtRisk().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcAssessedForHivRiskAndDeterminedToBeAtRisk();
            else if(indicatorId.equalsIgnoreCase("vcschattend"))
            indicator=ind.getIndicatorForNumberOfOvcRegularlyAttendingSchool();
            else if(indicatorId.equalsIgnoreCase("vcadherence"))
            indicator=ind.getIndicatorForNumberOfOvcSelfReportingAdherenceToTreatment();
            else if(indicatorId.equalsIgnoreCase("vcnewposart"))
            indicator=ind.getIndicatorForNumberOfOvcNewlyTestedPositiveAndLinkedToTreatment();
            else if(indicatorId.equalsIgnoreCase("vctstresult"))
            indicator=ind.getIndicatorForNumberOfOvcTestedAndReceivedResult();
            else if(indicatorId.equalsIgnoreCase("vccurinscflp"))
            indicator=ind.getNumberOfActiveOVCEnrolledInSchoolAtFollowup();
            else if(indicatorId.equalsIgnoreCase("vcnewlyEnro"))
            indicator=ind.getIndicatorForNumberOfNewOvcEnrolled();
            else if(indicatorId.equalsIgnoreCase("vccenrolled"))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("vcevenroled"))
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("vcgraduated"))
            indicator=ind.getIndicatorForNumberOfOvcGraduated();
            else if(indicatorId.equalsIgnoreCase("vclosttofup"))
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToLostToFollowup();
            else if(indicatorId.equalsIgnoreCase("ovcmigrated"))
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToMigration();
            else if(indicatorId.equalsIgnoreCase("ovcagingout"))
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToAgingOut();
            else if(indicatorId.equalsIgnoreCase("vcknowndeat"))
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToKnownDeath();
            else if(indicatorId.equalsIgnoreCase("vctransferd"))
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToTransfer();
            else if(indicatorId.equalsIgnoreCase("vcinactivrp"))
            indicator=ind.getIndicatorForNumberOfOvcDeclaredInactive();
            else if(indicatorId.equalsIgnoreCase("vcreenrolrp"))
            indicator=ind.getIndicatorForNumberOfOvcReenrolledIntoTheProgram();
            
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcCurrentlyInSchool().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyInSchool();
            else if(indicatorId.equalsIgnoreCase("vcnotschool"))
            indicator=ind.getIndicatorForNumberOfOvcOutOfSchool();
            else if(indicatorId.equalsIgnoreCase("vcnebslbcrt"))
            indicator=ind.getIndicatorForNumberOfOvcNewlyEnrolledWithBirthCertificateAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vccebslbcrt"))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledWithBirthCertAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vceebslbcrt"))
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolledWithBirthCertificateAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vccebthcert"))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledWithBirthCertificate();
            else if(indicatorId.equalsIgnoreCase("vceebthcert"))
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolledWithBirthCertificate();
            else if(indicatorId.equalsIgnoreCase("vcserbtctrp"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedBirthCertWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcwithnobcc"))
            indicator=ind.getIndicatorForNumberOfOvcWithoutBirthCertificateCurrently();
            else if(indicatorId.equalsIgnoreCase("hivPosEnrol"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("hivNegEnrol"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("hivUnkEnrol"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivPosinp"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcEnrolledAndAreStillInProgram();
            else if(indicatorId.equalsIgnoreCase("vchivNeginp"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcEnrolledAndAreStillInProgram();
            else if(indicatorId.equalsIgnoreCase("vchivUnkinp"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcEnrolledAndAreStillInProgram();
            else if(indicatorId.equalsIgnoreCase("vchivPosnEn"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcNewlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivNegnEn"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcNewlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivUnknEn"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcNewlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("hivPosbasel"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("hivNegbasel"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("hhivUnkbasel"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vctestedhiv"))
            indicator=ind.getIndicatorForNumberOfOvcTestedForHIV();
            else if(indicatorId.equalsIgnoreCase("vccurhivpos"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvc();
            else if(indicatorId.equalsIgnoreCase("vchivreferr"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForHIVCare();
            else if(indicatorId.equalsIgnoreCase("vcnewInCare"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcNewlyEnrolledInCare();
            else if(indicatorId.equalsIgnoreCase("vccurInCare"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolledInCare();
            else if(indicatorId.equalsIgnoreCase("vceveInCare"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcEverEnrolledInCare();
            else if(indicatorId.equalsIgnoreCase("vccureOnArt"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolledOnART();
            else if(indicatorId.equalsIgnoreCase("vceverOnArt"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcEverEnrolledOnART();
            else if(indicatorId.equalsIgnoreCase("vccehivrisk"))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledThatHaveBeenAssessedonHIVRisk();
            else if(indicatorId.equalsIgnoreCase("vceehivrisk"))
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolledThatHaveBeenAssessedonHIVRisk();
            else if(indicatorId.equalsIgnoreCase("vccenohivra"))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledThatHasNoHIVRiskAssessmentRecord();
            else if(indicatorId.equalsIgnoreCase("vcnashivrsk"))
            indicator=ind.getIndicatorForNumberOfOvcNeverAssessedForHivRisk();
            else if(indicatorId.equalsIgnoreCase("vccgknhivst"))
            indicator=ind.getIndicatorForNumberOfOvcWhoseCaregiversKnowTheirHivStatusWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vccgrdclhiv"))
            indicator=ind.getIndicatorForNumberOfOvcWhoseCaregiversRefusedToDiscloseTheirHivStatusWithinTheReportPeriod();
            //else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfOvcAssessedForHivRiskAndReferredForTesting().getIndicatorId()))
            //indicator=ind.getIndicatorForNumberOfOvcAssessedForHivRiskAndReferredForTesting();
            else if(indicatorId.equalsIgnoreCase("vcmuaclth11"))
            indicator=ind.getIndicatorForNumberOfSeverelyMalnourishedOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcmua11to12"))
            indicator=ind.getIndicatorForNumberOfModeratelyNourishedOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcmuacgth12"))
            indicator=ind.getIndicatorForNumberOfWellNourishedOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vccmuaclt11"))
            indicator=ind.getIndicatorForNumberOfSeverelyMalnourishedOvcCurrently();
            else if(indicatorId.equalsIgnoreCase("vccmuac1112"))
            indicator=ind.getIndicatorForNumberOfModeratelyNourishedOvcCurrently();
            else if(indicatorId.equalsIgnoreCase("vccmuacgt12"))
            indicator=ind.getIndicatorForNumberOfWellNourishedOvcCurrently();
            else if(indicatorId.equalsIgnoreCase("vchivillmem"))
            indicator=ind.getIndicatorForNumberOfOvcEnrolledFromHouseholdsWithChronicallyIllMembers();
            else if(indicatorId.equalsIgnoreCase("vccehhblass"))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledInHouseholdsWithBaselineAssessment();
            else if(indicatorId.equalsIgnoreCase("vcposcevulb"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromVulnerableHouseholdsAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcposcemrvb"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromMoreVulnerableHouseholdsAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcposcemovb"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromMostVulnerableHouseholdsAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vc1scorimpr"))
            indicator=ind.getIndicatorForNumberOfOvcThatShowedAtleastOneScoreImprovement();
            else if(indicatorId.equalsIgnoreCase("vcscrnfortb"))
            indicator=ind.getIndicatorForNumberOfOvcScreenedForTB();
            else if(indicatorId.equalsIgnoreCase("vcnenservrp"))
            indicator=ind.getIndicatorForNumberOfNewOvcEnrolledAndServedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vccurenserv"))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledAndServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcnotserved"))
            indicator=ind.getIndicatorForNumberOfOvcWithoutServiceRecords();
            else if(indicatorId.equalsIgnoreCase("vcgradserve"))
            indicator=ind.getIndicatorForNumberOfOvcGraduatedButServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcltfuserve"))
            indicator=ind.getIndicatorForNumberOfOvcLostToFollowupButServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcmigrserve"))
            indicator=ind.getIndicatorForNumberOfOvcMigratedButServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcageoserve"))
            indicator=ind.getIndicatorForNumberOfOvcAgedoutButServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vctranserve"))
            indicator=ind.getIndicatorForNumberOfOvcTransferedOutButServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcdeadserve"))
            indicator=ind.getIndicatorForNumberOfOvcDiedButServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcvolwserve"))
            indicator=ind.getIndicatorForNumberOfOvcVoluntarilyWithdrawnButServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcinactserv"))
            indicator=ind.getIndicatorForNumberOfOvcInactiveButServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("gt3services"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedThreeOrMoreServices();
            else if(indicatorId.equalsIgnoreCase("lt3services"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedLessThanThreeServices();
            else if(indicatorId.equalsIgnoreCase("vcrecactyrp"))
            indicator=ind.getIndicatorForNumberOfOvcThatParticipatedInRecreationalActivityWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcservedpsy"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedPsychosocialSupport();
            else if(indicatorId.equalsIgnoreCase("vcservednut"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedNutritionalSupport();
            else if(indicatorId.equalsIgnoreCase("vcservedhlt"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedHealthService();
            else if(indicatorId.equalsIgnoreCase("vcservededu"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedEducationalSupport();
            else if(indicatorId.equalsIgnoreCase("vcservedpro"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedProtectionServices();
            else if(indicatorId.equalsIgnoreCase("vcservedshe"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedShelterAndCareServices();
            else if(indicatorId.equalsIgnoreCase("vchivaccess"))
            indicator=ind.getIndicatorForOVC_ACCPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("posvcserved"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("negvcserved"))
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("unkvcserved"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("vcposceserv"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcCurrentlyEnrolledProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("vcnegceserv"))
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcCurrentlyEnrolledProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("vcunkceserv"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcCurrentlyEnrolledProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("vcpossernut"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedNutritionalSupport();
            else if(indicatorId.equalsIgnoreCase("vcnegsernut"))
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedNutritionalSupport();
            else if(indicatorId.equalsIgnoreCase("vcunksernut"))
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedNutritionalSupport();
            else if(indicatorId.equalsIgnoreCase("vcposserhlt"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedHealthServices();
            else if(indicatorId.equalsIgnoreCase("vcnegserhlt"))
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedHealthServices();
            else if(indicatorId.equalsIgnoreCase("vcunkserhlt"))
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedHealthServices();
            else if(indicatorId.equalsIgnoreCase("vcposseredu"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedEducationalServices();
            else if(indicatorId.equalsIgnoreCase("vcnegseredu"))
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedEducationalServices();
            else if(indicatorId.equalsIgnoreCase("vcunkseredu"))
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedEducationalServices();
            else if(indicatorId.equalsIgnoreCase("vcposserpsy"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedPsychosocialServices();
            else if(indicatorId.equalsIgnoreCase("vcnegserpsy"))
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedPsychosocialServices();
            else if(indicatorId.equalsIgnoreCase("vcunkserpsy"))
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedPsychosocialServices();
            else if(indicatorId.equalsIgnoreCase("vcposserpro"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedProtectionServices();
            else if(indicatorId.equalsIgnoreCase("vcnegserpro"))
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedProtectionServices();
            else if(indicatorId.equalsIgnoreCase("vcunkserpro"))
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedProtectionServices();
            else if(indicatorId.equalsIgnoreCase("vcpossersht"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedShelterAndCareServices();
            else if(indicatorId.equalsIgnoreCase("vcnegsersht"))
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedShelterAndCareServices();
            else if(indicatorId.equalsIgnoreCase("vcunksersht"))
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedShelterAndCareServices();
            else if(indicatorId.equalsIgnoreCase("vcposserecs"))
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedEconomicStrengtheningServices();
            else if(indicatorId.equalsIgnoreCase("vcnegserecs"))
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedEconomicStrengtheningServices();
            else if(indicatorId.equalsIgnoreCase("vcunkserecs"))
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedEconomicStrengtheningServices();
            
            else if(indicatorId.equalsIgnoreCase("vcceadohhes"))
            indicator=ind.getIndicatorForNumberOfAdolescentsCurrentlyEnrolledWhoseHouseholdsProvidedEconomicStrengthening();
            else if(indicatorId.equalsIgnoreCase("vcadolshhes"))
            indicator=ind.getIndicatorForNumberOfAdolescentsWhoseHouseholdsProvidedEconomicStrengthening();
            else if(indicatorId.equalsIgnoreCase("vcce019hhes"))
            indicator=ind.getIndicatorForNumberOfOvc0to19CurrentlyEnrolledWhoseHouseholdsProvidedEconomicStrengthening();
            else if(indicatorId.equalsIgnoreCase("vcee019hhes"))
            indicator=ind.getIndicatorForNumberOfOvc0to19WhoseHouseholdsProvidedEconomicStrengthening();
            
            //
            else if(indicatorId.equalsIgnoreCase("cgexitnogra"))
            indicator=ind.getIndicatorForNumberOfCaregiversExitedWithoutGraduation();
            else if(indicatorId.equalsIgnoreCase("cgnenrserrp"))
            indicator=ind.getIndicatorForNumberOfNewlyEnrolledCaregiversServedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cgnenrolled"))
            indicator=ind.getIndicatorForNumberOfCaregiversNewlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("cgcenrolled"))
            indicator=ind.getIndicatorForNumberOfCaregiversCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("cgeenrolled"))
            indicator=ind.getIndicatorForNumberOfCaregiversEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("cggraduated"))
            indicator=ind.getIndicatorForNumberOfCaregiversGraduated();
            else if(indicatorId.equalsIgnoreCase("cgmigrated1"))
            indicator=ind.getIndicatorForNumberOfCaregiversWithdrawnDueToMigration();
            else if(indicatorId.equalsIgnoreCase("cglosttofup"))
            indicator=ind.getIndicatorForNumberOfCaregiversWithdrawnDueToLostToFollowup();
            else if(indicatorId.equalsIgnoreCase("cgknowndeat"))
            indicator=ind.getIndicatorForNumberOfCaregiversWithdrawnDueToKnownDeath();
            else if(indicatorId.equalsIgnoreCase("cgtransferd"))
            indicator=ind.getIndicatorForNumberOfCaregiversWithdrawnDueToTransfer();
            //
            else if(indicatorId.equalsIgnoreCase("cginactivrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversDeclaredInactive();
            else if(indicatorId.equalsIgnoreCase("cgexitnogra"))
            indicator=ind.getIndicatorForNumberOfAdultMembersExitedWithoutGraduation();
            else if(indicatorId.equalsIgnoreCase("cgreenrolrp"))
            indicator=ind.getIndicatorForNumberOfAdultMembersReenrolledIntoTheProgram();
            else if(indicatorId.equalsIgnoreCase("cgscrnfortb"))
            indicator=ind.getIndicatorForNumberOfCaregiversScreenedForTB();
            else if(indicatorId.equalsIgnoreCase("cghivposcen"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("cghivnegcen"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeCaregiversCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("cghivunkcen"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownCaregiversCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("cghivposeen"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("cghivnegeen"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeCaregiversEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("cghivunkeen"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownCaregiversEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("cghivcencar"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolledInCare();
            else if(indicatorId.equalsIgnoreCase("cghivcenart"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolledOnART();
            else if(indicatorId.equalsIgnoreCase("cgivehivacc"))
            indicator=ind.getIndicatorForNumberOfCaregiversSupportedToAccessHIVServices();
            else if(indicatorId.equalsIgnoreCase("cgposcevulb"))
            indicator=ind.getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromVulnerableHouseholdsAtBaseline();
            else if(indicatorId.equalsIgnoreCase("cgposcemrvb"))
            indicator=ind.getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromMoreVulnerableHouseholdsAtBaseline();
            else if(indicatorId.equalsIgnoreCase("cgposcemovb"))
            indicator=ind.getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromMostVulnerableHouseholdsAtBaseline();
            else if(indicatorId.equalsIgnoreCase("cgtesforhiv"))
            indicator=ind.getIndicatorForNumberOfCaregiversTestedForHiv();
            else if(indicatorId.equalsIgnoreCase("cgiverserve"))
            indicator=ind.getIndicatorForNumberOfCaregiversServedWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cgactiserrp"))
            indicator=ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cgnotserved"))
            indicator=ind.getIndicatorForNumberOfCaregiversWithoutServiceRecords();
            else if(indicatorId.equalsIgnoreCase("cgsergradrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndGraduatedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cgserltfurp"))
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndLostToFollowupWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cgserdiedrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndDiedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cgsermigrrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndMigratedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cgsertranrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndTransferedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cginactserv"))
            indicator=ind.getIndicatorForNumberOfInactiveCaregiversServedInReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cgservedhes"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedHES();
            else if(indicatorId.equalsIgnoreCase("cgenrsilcrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversEnrolledInSILCWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase("cginforumrp"))
            indicator=ind.getIndicatorForNumberOfCaregiversEnrolledInCaregiverForumWithinReportPeriod();
            
            else if(indicatorId.equalsIgnoreCase("hhgdvcserrp"))
            indicator=ind.getIndicatorForNumberOfGraduatedHouseholdsWhoseOvcWereServedWithinReportingPeriod();
            else if(indicatorId.equalsIgnoreCase("hhvcserverp"))
            indicator=ind.getIndicatorNumberOfHouseholdsWhoseOvcWereServedWithinReportingPeriod();
            else if(indicatorId.equalsIgnoreCase(ind.getIndicatorForNumberOfHouseholdsThatCanSolveEmergencyNeedsWithinReportingPeriod().getIndicatorId()))
            indicator=ind.getIndicatorForNumberOfHouseholdsThatCanSolveEmergencyNeedsWithinReportingPeriod();
            else if(indicatorId.equalsIgnoreCase("hhnenrolled"))
            indicator=ind.getIndicatorForNumberOfHouseholdsNewlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("hhcenrolled"))
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("hheenrolled"))
            indicator=ind.getIndicatorForNumberOfHouseholdsEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("hhgraduated"))
            indicator=ind.getIndicatorForNumberOfHouseholdsWithdrawnDueToGraduation();
            else if(indicatorId.equalsIgnoreCase("hhmigrated1"))
            indicator=ind.getIndicatorForNumberOfHouseholdsWithdrawnDueToMigration();
            else if(indicatorId.equalsIgnoreCase("hhlosttofup"))
            indicator=ind.getIndicatorForNumberOfHouseholdsWithdrawnDueToLostToFollowup();
            else if(indicatorId.equalsIgnoreCase("hhtransferd"))
            indicator=ind.getIndicatorForNumberOfHouseholdsWithdrawnDueToTransfer();
            else if(indicatorId.equalsIgnoreCase("hhinactivrp"))
            indicator=ind.getIndicatorForNumberOfHouseholdsDeclaredInactive();
            else if(indicatorId.equalsIgnoreCase("hhdservedrp"))
            indicator=ind.getIndicatorForNumberOfHouseholdsServed();
            else if(indicatorId.equalsIgnoreCase("hhdserhesrp"))
            indicator=ind.getIndicatorForNumberOfHouseholdsProvidedHES();
            else if(indicatorId.equalsIgnoreCase("hhcevulbase"))
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreVulnerableAtBaseline();
            else if(indicatorId.equalsIgnoreCase("hhcemrvulba"))
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreMoreVulnerableAtBaseline();
            else if(indicatorId.equalsIgnoreCase("hhcemtvulba"))
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreMostVulnerableAtBaseline();
            else if(indicatorId.equalsIgnoreCase("hhdbasasses"))
            indicator=ind.getIndicatorForNumberOfHouseholdsEverEnrolledWithBaselineAssessment();
            else if(indicatorId.equalsIgnoreCase("hhdcebasass"))
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithBaselineAssessment();
            else if(indicatorId.equalsIgnoreCase("hhbassesper"))
            indicator=ind.getIndicatorForNumberOfHouseholdsWithBaselineAssessmentWithinTheReprtPeriod();
            else if(indicatorId.equalsIgnoreCase("hhcebassper"))
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithBaselineAssessmentWithinTheReprtPeriod();
            else if(indicatorId.equalsIgnoreCase("hhcefupasse"))
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithFollowupAssessment();
            else if(indicatorId.equalsIgnoreCase("hheefupasse"))
            indicator=ind.getIndicatorForNumberOfHouseholdsEverEnrolledWithFollowupAssessment();
            else if(indicatorId.equalsIgnoreCase("vcwithdrawn"))
            indicator=ind.getIndicatorForNumberOfOvcWithdrawn();
            else if(indicatorId.equalsIgnoreCase("noOfOvcFollowedUp"))
            indicator=ind.getIndicatorForNumberOfOvcFollowedup();
            else if(indicatorId.equalsIgnoreCase("noOfOvcNotFollowedUp"))
            indicator=ind.getIndicatorForNumberOfOvcNotFollowedup();
            else if(indicatorId.equalsIgnoreCase("noOfHivPositiveOvcFollowedUp"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcAtFollowedup();
            else if(indicatorId.equalsIgnoreCase("noOfHivNegativeOvcFollowedUp"))
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcAtFollowedup();
            else if(indicatorId.equalsIgnoreCase("noOfHivUnknownOvcFollowedUp"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcAtFollowedup();
            else if(indicatorId.equalsIgnoreCase("vckidsclbrp"))
            indicator=ind.getIndicatorForNumberOfOvcEnrolledInKidClubWithinReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcvolleftrp"))
            indicator=ind.getIndicatorForNumberOfOvcVoluntarilyWithdrawnFromTheProgramReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vchivpprpce"))
            indicator=ind.getIndicatorForProportionOfHivPosOvcCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivnprpce"))
            indicator=ind.getIndicatorForProportionOfHivNegOvcCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivuprpce"))
            indicator=ind.getIndicatorForProportionOfHivUnkOvcCurrentlyEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivincare"))
            indicator=ind.getIndicatorForNumberOfOvcEnrolledInCare();
            else if(indicatorId.equalsIgnoreCase("vcbmi25to29"))
            indicator=ind.getIndicatorForNumberOfOvcWhoAreOverweightAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcbmi30to40"))
            indicator=ind.getIndicatorForNumberOfOvcThatAreObesseAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcwtbmigt40"))
            indicator=ind.getIndicatorForNumberOfOvcThatAreMorbidityObesseAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcwithnobir"))
            indicator=ind.getIndicatorForNumberOfOvcWithoutBirthCertificateAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcWithKnownHivStatusAtEnrolledmentPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcWithKnownHIVStatusAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vchivPosbas"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vchivNegbas"))
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vchivUnkbas"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcAtBaseline();
            else if(indicatorId.equalsIgnoreCase("noOfOvcOutOfSchoolAtEnrollment"))
            indicator=ind.getIndicatorForNumberOfOvcOutOfSchoolAtEnrollmentPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("vcinschaten"))
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolledAndInSchoolAtEnrollmentPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("vcceinscenr"))
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyAndInSchoolAtEnrollmentPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfOvcServedPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcServedPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfHivNegOvcServedPerMth"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcServedPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfHivPosOvcServedPerMth"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcServedPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfHivUnknownOvcServedPerMth"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcServedPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfOvcServedHivCarePerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedHIVServicesPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfOvcServedPsychoPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedPsychosocialSupportServicesPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfOvcServedHealthPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedHealthServicesPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfOvcServedEducPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedEducationalServicesPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfOvcServedProtPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedProtectionServicesPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfOvcServedShelterPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedShelterServicesPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("noOfOvcServedEconStrgthPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedEconomicStrengtheningServicesPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("vcWithUpdatedHivAtFollowup"))
            indicator=ind.getIndicatorForNumberOfOVCWithUpdatedHIVStatusAtFollowup();
            else if(indicatorId.equalsIgnoreCase("hivPosVcIdentifiedAtFollowup"))
            indicator=ind.getIndicatorForHIVPositiveOVCIdentifiedAtAtFollowup();
            else if(indicatorId.equalsIgnoreCase("vcevrinscflp"))
            indicator=ind.getNumberOfOVCEnrolledInSchoolAtFollowup();
            else if(indicatorId.equalsIgnoreCase("noOfOvcReferredPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcReferredForServicesPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("hhdEnrolled"))
            indicator=ind.getIndicatorForNumberOfHouseholdsEnrolled();
            else if(indicatorId.equalsIgnoreCase("noOfHouseholdsEnrolledPerCohort"))
            indicator=ind.getIndicatorForNumberOfHouseholdsEnrolledPerCohort();
            else if(indicatorId.equalsIgnoreCase("noOfHouseholdsFollowedUpPerCohort"))
            indicator=ind.getIndicatorForNumberOfHouseholdsFollowedupPerCohort();
            else if(indicatorId.equalsIgnoreCase("noOfHouseholdsNotFollowedUpPerCohort"))
            indicator=ind.getIndicatorForNumberOfHouseholdsNotFollowedupPerCohort();
            else if(indicatorId.equalsIgnoreCase("noOfCaregiverEnrolledPerCohort"))
            indicator=ind.getIndicatorForNumberOfCaregiversEnrolledPerCohort();
            else if(indicatorId.equalsIgnoreCase("noOfCaregiversFollowedUp"))
            indicator=ind.getIndicatorForNumberOfCaregiversFollowedup();
            else if(indicatorId.equalsIgnoreCase("noOfHivPositiveCaregiversFollowedUp"))
            indicator=ind.getIndicatorForNumberOfHivPositiveCaregiversAtFollowedup();
            else if(indicatorId.equalsIgnoreCase("noOfHivNegativeCaregiversFollowedUp"))
            indicator=ind.getIndicatorForNumberOfHivNegativeCaregiversAtFollowedup();
            else if(indicatorId.equalsIgnoreCase("noOfHivUnknownCaregiversFollowedUp"))
            indicator=ind.getIndicatorForNumberOfHivUnknownCaregiversAtFollowedup();
            else if(indicatorId.equalsIgnoreCase("ovcEnrolled"))
            indicator=ind.getIndicatorForNumberOfOvcEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivPosIdf"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcIdentifiedWithinTheReportingPeriod();
            else if(indicatorId.equalsIgnoreCase("vchivNegIdf"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcIdentifiedWithinTheReportingPeriod();
            else if(indicatorId.equalsIgnoreCase("vchivunkIdf"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcIdentifiedWithinTheReportingPeriod();
            else if(indicatorId.equalsIgnoreCase("vchivposeen"))
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivnegeen"))
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("vchivunkeen"))
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcEverEnrolled();
            else if(indicatorId.equalsIgnoreCase("nhivunkhtc"))
            indicator=ind.getIndicatorForNumberOfOVCCurrentlyEnrolledAndServedHTCWhoseCurrentHIVStatusIsUnknown();
            else if(indicatorId.equalsIgnoreCase("vcartserved"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcEnrolledOnARTProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("vctniserved"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcWithTestNotIndicatedServed();
            else if(indicatorId.equalsIgnoreCase("vcposrpserv"))
            indicator=ind.getIndicatorForNumberOfOvcServedThatWereHivPositiveAtTheEndOfTheReportReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcnegrpserv"))
            indicator=ind.getIndicatorForNumberOfOvcServedThatWereHivNegativeAtTheEndOfTheReportReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcuknrpserv"))
            indicator=ind.getIndicatorForNumberOfOvcServedThatWereHivUnknownAtTheEndOfTheReportReportPeriod();
            else if(indicatorId.equalsIgnoreCase("newnotserve"))
            indicator=ind.getIndicatorForNumberOfNewOvcWithoutServiceRecords();
            else if(indicatorId.equalsIgnoreCase("cgservesilc"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedSILCSupport();
            else if(indicatorId.equalsIgnoreCase("cgservemfin"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedMicrofinanceSupport();
            else if(indicatorId.equalsIgnoreCase("cgserveecon"))
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedEconomicStrenghteningServices();
            else if(indicatorId.equalsIgnoreCase("hhwithdrawn"))
            indicator=ind.getIndicatorForTotalNumberOfHouseholdsWithdrawnForTheReportingPeriod();
            else if(indicatorId.equalsIgnoreCase("vcWithUpdatedBirthRegAtFollowup"))
            indicator=ind.getNoOfOVCWithUpdatedBirthRegistrationAtFollowup();
            else if(indicatorId.equalsIgnoreCase("noOfOvcThatDroppedOutOfSchool"))
            indicator=ind.getIndicatorForNumberOfOvcThatDroppedOutOfSchoolPerMonthByCBO();
            else if(indicatorId.equalsIgnoreCase("vcVulnerableToHiv"))
            indicator=ind.getIndicatorForNumberOfOvcVulnerableToHIV_AIDS();
            else if(indicatorId.equalsIgnoreCase("vcInChildHeadedHouseholds"))
            indicator=ind.getIndicatorForNumberOfOvcInChildHeadedHouseholds();
            else if(indicatorId.equalsIgnoreCase("vcSickWithLtdAccessToHealthCare"))
            indicator=ind.getIndicatorForNumberOfOvcSickWithLimitedAccessToHealthCare();
            else if(indicatorId.equalsIgnoreCase("vcSickWithNoAccessToHealthCare"))
            indicator=ind.getIndicatorForNumberOfOvcSickWithNoAccessToHealthCare();
            else if(indicatorId.equalsIgnoreCase("mostVulnerableOvc"))
            indicator=ind.getIndicatorForNumberOfOvcWhoAreMostVulnerableAtBaseline();
            else if(indicatorId.equalsIgnoreCase("moreVulnerableOvc"))
            indicator=ind.getIndicatorForNumberOfOvcWhoAreMoreVulnerableAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vulnerableOvc"))
            indicator=ind.getIndicatorForNumberOfOvcWhoAreVulnerableAtBaseline();
            else if(indicatorId.equalsIgnoreCase("vcWithBirthCertAfterEnrollmentPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedBirthCertificateServicesAfterEnrollment();
            else if(indicatorId.equalsIgnoreCase("vcProvidedLegalServicesPerMth"))
            indicator=ind.getIndicatorForNumberOfOvcProvidedLegalServicesAfterEnrollment();
            
            else if(indicatorId.equalsIgnoreCase("vctnigrdser"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcTNIGraduatedAndServedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vctniceserv"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcTNICurrentlyEnrolledAndServedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcartsergrd"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcOnARTServedButGraduatedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcartceserv"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcOnARTCurrentlyEnrolledProvidedWithAtleastOneService();
            else if(indicatorId.equalsIgnoreCase("vcpossergrd"))
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcServedButGraduatedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcnegsergrd"))
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcServedButGraduatedWithinTheReportPeriod();
            else if(indicatorId.equalsIgnoreCase("vcunksergrd"))
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcServedButGraduatedWithinTheReportPeriod();
          
        }
        return indicator;
    }
    
    public String getIndicatorName(String indicatorCode)
    {
        String indicatorName=indicatorCode;
        Indicator ind=this.getIndicatorById(indicatorCode);
        if(ind !=null)
        indicatorName=ind.getIndicatorName();
        return indicatorName;
    }
    public Indicator showIndicatorDetails(String indicatorId)
    {
        Indicator indicator=null;
        if(indicatorId !=null)
        {
            IndicatorDictionary ind=new IndicatorDictionary();
            
            indicator=ind.getNoOfOVCWithUpdatedBirthRegistrationAtFollowup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getNumberOfActiveOVCEnrolledInSchoolAtFollowup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcThatDroppedOutOfSchoolPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcVulnerableToHIV_AIDS();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcInChildHeadedHouseholds();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcSickWithLimitedAccessToHealthCare();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcSickWithNoAccessToHealthCare();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWhoAreMostVulnerableAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWhoAreMoreVulnerableAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWhoAreVulnerableAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedBirthCertificateServicesAfterEnrollment();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedLegalServicesAfterEnrollment();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfNewOvcEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcGraduated();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToLostToFollowup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToMigration();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToAgingOut();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToKnownDeath();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithdrawnDueToTransfer();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcDeclaredInactive();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyInSchool();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcOutOfSchool();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcNewlyEnrolledWithBirthCertificateAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledWithBirthCertAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolledWithBirthCertificateAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledWithBirthCertificate();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolledWithBirthCertificate();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedBirthCertWithinReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithoutBirthCertificateCurrently();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcEnrolledAndAreStillInProgram();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcEnrolledAndAreStillInProgram();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcEnrolledAndAreStillInProgram();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcNewlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcNewlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcNewlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcTestedForHIV();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPositiveOvc();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcReferredForHIVCare();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcNewlyEnrolledInCare();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolledInCare();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcEverEnrolledInCare();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcCurrentlyEnrolledOnART();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcEverEnrolledOnART();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledThatHaveBeenAssessedonHIVRisk();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolledThatHaveBeenAssessedonHIVRisk();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledThatHasNoHIVRiskAssessmentRecord();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcNeverAssessedForHivRisk();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWhoseCaregiversKnowTheirHivStatusWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWhoseCaregiversRefusedToDiscloseTheirHivStatusWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            //indicator=ind.getIndicatorForNumberOfOvcAssessedForHivRiskAndReferredForTesting();
            //System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfSeverelyMalnourishedOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfModeratelyNourishedOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfWellNourishedOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfSeverelyMalnourishedOvcCurrently();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfModeratelyNourishedOvcCurrently();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfWellNourishedOvcCurrently();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEnrolledFromHouseholdsWithChronicallyIllMembers();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledInHouseholdsWithBaselineAssessment();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromVulnerableHouseholdsAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromMoreVulnerableHouseholdsAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcCurrentlyEnrolledThatAreFromMostVulnerableHouseholdsAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcThatShowedAtleastOneScoreImprovement();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcScreenedForTB();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcServedWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfNewOvcEnrolledAndServedWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyEnrolledAndServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithoutServiceRecords();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcGraduatedButServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcLostToFollowupButServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcMigratedButServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcAgedoutButServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcTransferedOutButServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcDiedButServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcVoluntarilyWithdrawnButServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcInactiveButServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedThreeOrMoreServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedLessThanThreeServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcThatParticipatedInRecreationalActivityWithinReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedPsychosocialSupport();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedNutritionalSupport();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedHealthService();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedEducationalSupport();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedProtectionServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedShelterAndCareServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForOVC_ACCPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcProvidedWithAtleastOneService();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcProvidedWithAtleastOneService();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcProvidedWithAtleastOneService();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcCurrentlyEnrolledProvidedWithAtleastOneService();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcCurrentlyEnrolledProvidedWithAtleastOneService();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcCurrentlyEnrolledProvidedWithAtleastOneService();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedNutritionalSupport();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedNutritionalSupport();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedNutritionalSupport();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedHealthServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedHealthServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedHealthServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedEducationalServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedEducationalServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedEducationalServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedPsychosocialServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedPsychosocialServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedPsychosocialServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedProtectionServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedProtectionServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedProtectionServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedShelterAndCareServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedShelterAndCareServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedShelterAndCareServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosOvcProvidedEconomicStrengtheningServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegOvcProvidedEconomicStrengtheningServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknOvcProvidedEconomicStrengtheningServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfAdolescentsCurrentlyEnrolledWhoseHouseholdsProvidedEconomicStrengthening();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfAdolescentsWhoseHouseholdsProvidedEconomicStrengthening();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvc0to19CurrentlyEnrolledWhoseHouseholdsProvidedEconomicStrengthening();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvc0to19WhoseHouseholdsProvidedEconomicStrengthening();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversNewlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversGraduated();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversWithdrawnDueToMigration();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversWithdrawnDueToLostToFollowup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversWithdrawnDueToKnownDeath();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversWithdrawnDueToTransfer();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversDeclaredInactive();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversScreenedForTB();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeCaregiversCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownCaregiversCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeCaregiversEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownCaregiversEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolledInCare();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveCaregiversCurrentlyEnrolledOnART();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversSupportedToAccessHIVServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromVulnerableHouseholdsAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromMoreVulnerableHouseholdsAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPosCaregiversCurrentlyEnrolledThatAreFromMostVulnerableHouseholdsAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversTestedForHiv();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversServedWithinReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfActiveCaregiversServedWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversWithoutServiceRecords();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndGraduatedWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndLostToFollowupWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndDiedWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndMigratedWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversServedAndTransferedWithinTheReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfInactiveCaregiversServedInReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedHES();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversEnrolledInSILCWithinReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversEnrolledInCaregiverForumWithinReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsNewlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsWithdrawnDueToGraduation();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsWithdrawnDueToMigration();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsWithdrawnDueToLostToFollowup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsWithdrawnDueToTransfer();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsDeclaredInactive();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsServed();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsProvidedHES();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreVulnerableAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreMoreVulnerableAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledThatAreMostVulnerableAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsEverEnrolledWithBaselineAssessment();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithBaselineAssessment();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsWithBaselineAssessmentWithinTheReprtPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithBaselineAssessmentWithinTheReprtPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsCurrentlyEnrolledWithFollowupAssessment();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsEverEnrolledWithFollowupAssessment();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithdrawn();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcNotFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcAtFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcAtFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcAtFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEnrolledInKidClubWithinReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcVoluntarilyWithdrawnFromTheProgramReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForProportionOfHivPosOvcCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForProportionOfHivNegOvcCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForProportionOfHivUnkOvcCurrentlyEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEnrolledInCare();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWhoAreOverweightAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcThatAreObesseAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcThatAreMorbidityObesseAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithoutBirthCertificateAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcWithKnownHIVStatusAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegativeOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcAtBaseline();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcOutOfSchoolAtEnrollmentPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEverEnrolledAndInSchoolAtEnrollmentPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcCurrentlyAndInSchoolAtEnrollmentPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcServedPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcServedPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcServedPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcServedPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedHIVServicesPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedPsychosocialSupportServicesPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedHealthServicesPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedEducationalServicesPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedProtectionServicesPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedShelterServicesPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcProvidedEconomicStrengtheningServicesPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOVCWithUpdatedHIVStatusAtFollowup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForHIVPositiveOVCIdentifiedAtAtFollowup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getNumberOfOVCEnrolledInSchoolAtFollowup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcReferredForServicesPerMonthByCBO();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsEnrolledPerCohort();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsFollowedupPerCohort();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHouseholdsNotFollowedupPerCohort();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversEnrolledPerCohort();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPositiveCaregiversAtFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivNegativeCaregiversAtFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknownCaregiversAtFollowedup();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcIdentifiedWithinTheReportingPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcIdentifiedWithinTheReportingPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcIdentifiedWithinTheReportingPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVPositiveOvcEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVNegativeOvcEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHIVUnknownOvcEverEnrolled();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOVCCurrentlyEnrolledAndServedHTCWhoseCurrentHIVStatusIsUnknown();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivPositiveOvcEnrolledOnARTProvidedWithAtleastOneService();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfHivUnknownOvcWithTestNotIndicatedServed();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcServedThatWereHivPositiveAtTheEndOfTheReportReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcServedThatWereHivNegativeAtTheEndOfTheReportReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfOvcServedThatWereHivUnknownAtTheEndOfTheReportReportPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfNewOvcWithoutServiceRecords();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedSILCSupport();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedMicrofinanceSupport();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForNumberOfCaregiversProvidedEconomicStrenghteningServices();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());
            indicator=ind.getIndicatorForTotalNumberOfHouseholdsWithdrawnForTheReportingPeriod();
            System.err.println(indicator.getIndicatorId()+"----------- "+indicator.getIndicatorName());

        }
        return indicator;
    }
}
