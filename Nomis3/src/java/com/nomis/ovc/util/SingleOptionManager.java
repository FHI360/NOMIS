/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class SingleOptionManager implements Serializable
{
    public static SingleChoiceOption getAgeUnit(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==0)
        {
           scopt.setName(" ");
           scopt.setValue(value);
        }
        else if(value==1)
        {
           scopt.setName("Month");
           scopt.setValue(value);
        }
        else if(value==2)
        {
           scopt.setName("Year");
           scopt.setValue(value);
        }
        return scopt;
    }
    public static SingleChoiceOption getSingleChoiceOption(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==0)
        {
           scopt.setName(" ");
           scopt.setValue(value);
        }
        else if(value==1)
        {
           scopt.setName("Yes");
           scopt.setValue(value);
        }
        else if(value==2)
        {
           scopt.setName("No");
           scopt.setValue(value);
        }
        else if(value==3)
        {
           scopt.setName("N/A");
           scopt.setValue(value);
        }
        return scopt;
    }
    public static SingleChoiceOption getSingleChoiceOption(String name)
    {//InStringFormat
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(name !=null)
        {
            if(name.equalsIgnoreCase("Yes"))
            {
               scopt.setName(name);
               scopt.setValue(AppConstant.YES_OPTION_NUM);
            }
            else if(name.equalsIgnoreCase("No"))
            {
               scopt.setName(name);
               scopt.setValue(AppConstant.NO_OPTION_NUM);
            }
        }
        return scopt;
    }
    public SingleChoiceOption getChildAtRiskOption(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==0)
        {
           scopt.setName(" ");
           scopt.setValue(value);
        }
        else if(value==1)
        {
           scopt.setName("Yes");
           scopt.setValue(value);
        }
        else if(value==2)
        {
           scopt.setName("No");
           scopt.setValue(value);
        }
        else if(value==4)
        {
           scopt.setName("Low Risk");
           scopt.setValue(value);
        }
        else if(value==5)
        {
           scopt.setName("High Risk");
           scopt.setValue(value);
        }
        return scopt;
    }
    public SingleChoiceOption getHivRiskChildTestedAnswerOption(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==0)
        {
           scopt.setName(" ");
           scopt.setValue(value);
        }
        else if(value==1)
        {
           scopt.setName("Yes, the test was done less than 6 months ago");
           scopt.setValue(value);
        }
        else if(value==2)
        {
           scopt.setName("No, the test was more than 6 months ago");
           scopt.setValue(value);
        }
        else if(value==4)
        {
           scopt.setName("No, the test was done more than 6 months ago and child is 12 years and below");
           scopt.setValue(value);
        }
        else if(value==5)
        {
           scopt.setName("No, the test was done more than 6 months ago and child is above 12 years");
           scopt.setValue(value);
        }
        return scopt;
    }
    public SingleChoiceOption getPhysicallyOrSexuallyAbusedAnswerOption(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==0)
        {
           scopt.setName(" ");
           scopt.setValue(value);
        }
        else if(value==1 || value==5)
        {
           scopt.setName("Sexual abuse");
           scopt.setValue(value);
        }
        else if(value==2)
        {
           scopt.setName("No");
           scopt.setValue(value);
        }
        else if(value==2 || value==3 || value==4)
        {
           scopt.setName("Physical abuse");
           scopt.setValue(value);
        }
        /*else if(value==5)
        {
           scopt.setName("Sexual abuse");
           scopt.setValue(value);
        }
        else if(value==6)
        {
           scopt.setName("Both physical and sexual abuse");
           scopt.setValue(value);
        }*/
        return scopt;
    }
    public SingleChoiceOption getParentLivingWithHivAnswerOption(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==0)
        {
           scopt.setName(" ");
           scopt.setValue(value);
        }
        else if(value==1)
        {
           scopt.setName("Yes");
           scopt.setValue(value);
        }
        else if(value==2)
        {
           scopt.setName("No");
           scopt.setValue(value);
        }
        else if(value==4)
        {
           scopt.setName("No, child &gt 12 years");
           scopt.setValue(value);
        }
        else if(value==5)
        {
           scopt.setName("Unknown or &lt 12 years");
           scopt.setValue(value);
        }
        return scopt;
    }
    public SingleChoiceOption getCasePlanOption(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==1)
        {
           scopt.setName("Yes");
           scopt.setValue(value);
        }
        else if(value==0 || value==2)
        {
           scopt.setName("No");
           scopt.setValue(value);
        }
        return scopt;
    }
    public SingleChoiceOption getEducationLevelOption(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==0)
        {
           scopt.setName(" ");
           scopt.setValue(value);
        }
        if(value==1)
        {
           scopt.setName("No formal education");
           scopt.setValue(value);
        }
        else if(value==2)
        {
           scopt.setName("Some primary education");
           scopt.setValue(value);
        }
        else if(value==3)
        {
           scopt.setName("Completed primary education");
           scopt.setValue(value);
        }
        else if(value==4)
        {
           scopt.setName("Some secondary/high school");
           scopt.setValue(value);
        }
        else if(value==5)
        {
           scopt.setName("Completed secondary or high school");
           scopt.setValue(value);
        }
        else if(value==6)
        {
           scopt.setName("Completed tertiary education");
           scopt.setValue(value);
        }
        else if(value==7)
        {
           scopt.setName("Prefer not to answer");
           scopt.setValue(value);
        }
        return scopt;
    }
    public SingleChoiceOption getOccupationOption(int value)
    {
        SingleChoiceOption scopt=new SingleChoiceOption();
        if(value==0)
        {
           scopt.setName(" ");
           scopt.setValue(value);
        }
        if(value==1)
        {
           scopt.setName("Formally employed");
           scopt.setValue(value);
        }
        else if(value==2)
        {
           scopt.setName("Informally employed");
           scopt.setValue(value);
        }
        else if(value==3)
        {
           scopt.setName("Self employed");
           scopt.setValue(value);
        }
        else if(value==4)
        {
           scopt.setName("Retired pensioner");
           scopt.setValue(value);
        }
        else if(value==5)
        {
           scopt.setName("Retired non-pensioner");
           scopt.setValue(value);
        }
        else if(value==6)
        {
           scopt.setName("Unemployed");
           scopt.setValue(value);
        }
        return scopt;
    }
}
