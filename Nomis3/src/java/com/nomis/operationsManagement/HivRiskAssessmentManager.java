/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;


import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class HivRiskAssessmentManager implements Serializable
{
    /*public HivRiskAssessment getHivRiskAssessmentWithAtRiskStatus(HivRiskAssessment hra)
    {//This method no longer applies to this form
        if(hra !=null)
        {
            if(hra.getChildTestedQuestion()==1)
            {
                hra.setChildAtRiskQuestion(AppConstant.CHILD_AT_LOW_RISK_NUM);
            }
            else if(hra.getChildTestedQuestion()>1 && getSumOfHivRiskFactors(hra) ==0)
            {
                hra.setChildAtRiskQuestion(AppConstant.CHILD_AT_LOW_RISK_NUM);
            }
            else
            {
                hra.setChildAtRiskQuestion(AppConstant.CHILD_AT_HIGH_RISK_NUM);
            }
        }
        return hra;
    }*/
    public static HivRiskAssessment getHivRiskAssessmentWithCleanedStatus(HivRiskAssessment hra)
    {
        hra=HivRiskAssessmentManager.getHivRiskAssessmentWithRiskStatus(hra);
        if(hra.getHivStatusAtRiskAssessment()==AppConstant.HIV_UNKNOWN_NUM || hra.getHivStatusAtRiskAssessment()==AppConstant.HIV_UNDISCLOSED_NUM)
         //set this to NO (2) because the hiv status is unknown. This means the caregiver does not know the hiv status of the child
        hra.setHivStatusQuestion(2);
        return hra;
    }
    public static HivRiskAssessment getHivRiskAssessmentWithRiskStatus(HivRiskAssessment hra)
    {
        int sumOfHivRiskFactors=HivRiskAssessmentManager.getSumOfHivRiskFactors(hra);
        if(sumOfHivRiskFactors>0)
        {
            hra.setChildAtRiskQuestion(AppConstant.CHILD_AT_RISK_NUM);
        }
        else
        {
            hra.setChildAtRiskQuestion(AppConstant.CHILD_NOT_AT_RISK_NUM);
        }
        return hra;
    }
    public static int getSumOfHivRiskFactors(HivRiskAssessment hra)
    {
        int sumOfHivRiskFactors=0;
        if(hra !=null)
        {
            if(hra.getChildTestedQuestion()==2)
            sumOfHivRiskFactors++;
            if(hra.getHivParentQuestion()==1)
            sumOfHivRiskFactors++;
            if(hra.getMotherSicknessQuestion()==1)
            sumOfHivRiskFactors++;
            if(hra.getHivSibblingQuestion()==1)
            sumOfHivRiskFactors++;
            if(hra.getSibblingSicknessQuestion()==1)
            sumOfHivRiskFactors++;
            if(hra.getChildSickQuestion()==1)
            sumOfHivRiskFactors++;   
            if(hra.getChildHasMoreThanTwoIllnessQuestion()==1)
            sumOfHivRiskFactors++;
            if(hra.getBloodTransfusionQuestion()==1)
            sumOfHivRiskFactors++;
            if(hra.getChildCircumcisedOrEarPierced()==1)
            sumOfHivRiskFactors++;
            if(hra.getSexualAbuseQuestion()==1)
            sumOfHivRiskFactors++;
            if(hra.getChildEverPregnantQuestion()==1)
            sumOfHivRiskFactors++;
        }
        return sumOfHivRiskFactors;
    }
    public int processHivRiskAssessmentOutcome()
    {
        int count=0;
        try
        {
            DaoUtility util=new DaoUtility();
            List hivRiskAssList=util.getHivRiskAssessmentDaoInstance().getAllHivRiskAssessments();
            if(hivRiskAssList !=null)
            {
                HivRiskAssessment hra=new HivRiskAssessment();
                for(int i=0; i<hivRiskAssList.size(); i++)
                {
                    hra=(HivRiskAssessment)hivRiskAssList.get(i);
                    //if(!childAtRiskOfHiv(hra))
                    //{
                        util.getHivRiskAssessmentDaoInstance().processHivRiskAssessmentOutcome(hra);
                        count++;
                        System.err.println("HIV Risk assessment outcome for "+hra.getOvcId()+" at "+count+" processed");
                    //}
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public static int getYesNoValue(String yesNoInput)
    {
        int yesNoValue=0;
        if(yesNoInput !=null)
        {
            if(yesNoInput.equalsIgnoreCase("Yes"))
            yesNoValue=AppConstant.YES_OPTION_NUM;
            else if(yesNoInput.equalsIgnoreCase("No"))
            yesNoValue=AppConstant.NO_OPTION_NUM;
        }
        return yesNoValue;
    }
    public boolean childAtRiskOfHiv(HivRiskAssessment hra)
    {
        boolean childAtRiskOfHiv=false;
        if(hra !=null)
        {
            if(hra.getChildAtRiskQuestion()==AppConstant.CHILD_AT_HIGH_RISK_NUM || hra.getChildAtRiskQuestion()==AppConstant.CHILD_AT_RISK_NUM)
            childAtRiskOfHiv=true;
        }
        return childAtRiskOfHiv;
    }
}
