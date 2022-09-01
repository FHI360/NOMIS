/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.RevisedHouseholdVulnerabilityAssessment;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class HouseholdAssessmentManager implements Serializable
{
    public int getTotalAssessmentScore(RevisedHouseholdVulnerabilityAssessment rhva)
    {
        int totalAssessmentScore=0;
        if(rhva !=null)
        {
            if(rhva.getCgEngagedInEconomicActivities()==1)
            totalAssessmentScore++;
            if(rhva.getChildUndernourished()==1)
            totalAssessmentScore++;
            if(rhva.getChildrenEnrolledInSchool()==1)
            totalAssessmentScore++;
            if(rhva.getChildrenHasBirthCertificate()==1)
            totalAssessmentScore++;
            if(rhva.getHasViralLoadResult()==1)
            totalAssessmentScore++;
            if(rhva.getHivPositiveLinked()==1)
            totalAssessmentScore++;
            if(rhva.getHivPreventionKnowledge()==1)
            totalAssessmentScore++;
            if(rhva.getHivStatusKnown()==1)
            totalAssessmentScore++;
            if(rhva.getRegularSchoolAttendance()==1)
            totalAssessmentScore++;
            if(rhva.getSocialEmotionalSupport()==1)
            totalAssessmentScore++;
            if(rhva.getStableAdult()==1)
            totalAssessmentScore++;
            if(rhva.getViolenceExperienceReported()==1)
            totalAssessmentScore++;
            
            if(rhva.getEducationLevel()==1)
            totalAssessmentScore++;
            if(rhva.getFoodSecurityAndNutrition()==1)
            totalAssessmentScore++;
            if(rhva.getHealth()==1)
            totalAssessmentScore++;
            if(rhva.getHhHeadship()==1)
            totalAssessmentScore++;
            if(rhva.getHhIncome()==1)
            totalAssessmentScore++;
            if(rhva.getMeansOfLivelihood()==1)
            totalAssessmentScore++;
            if(rhva.getShelterAndHousing()==1)
            totalAssessmentScore++;
            
        }
        return totalAssessmentScore;
    }
}
