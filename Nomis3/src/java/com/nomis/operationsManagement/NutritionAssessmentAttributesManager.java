/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

/**
 *
 * @author smomoh
 */
public class NutritionAssessmentAttributesManager 
{
    public static String getAnswerToNutritionalStatus(int numValue)
    {
        String answer="";
        if(numValue>0)
        {
            if(numValue==1)
            answer="Obesse";
            else if(numValue==2)
            answer="Over weight";
            else if(numValue==3)
            answer="Normal";
            else if(numValue==4)
            answer="Mild malnutrition";
            else if(numValue==5)
            answer="Moderate malnutrition";
            else if(numValue==6)
            answer="Severe malnutrition";
            else 
            answer=numValue+"";
        }
        return answer;
    }
    public static String getAnswerToNumberOfTimesChildAteFood(int numValue)
    {
        String answer="";
        if(numValue>0)
        {
            if(numValue==4)
            answer="4 or more";
            else 
            answer=numValue+"";
        }
        return answer;
    }
    public static String getAnswerToHouseholdMemberDidNotEatBecauseNoFood(int numValue)
    {
        String answer="";
        if(numValue>0)
        {
            if(numValue==2)
            answer="No";
            else if(numValue==5)
            answer="Rarely";
            else if(numValue==6)
            answer="Sometimes";
            else if(numValue==7)
            answer="Often";
            else 
            answer=numValue+"";
        }
        return answer;
    }
    public static int getNutritionAssessmentAttributeValue(String attributeName)
    {
        int attributeValue=0;
        if(attributeName !=null)
        {
            if(attributeName.equalsIgnoreCase("Yes"))
            attributeValue=1;
            else if(attributeName.equalsIgnoreCase("No"))
            attributeValue=2;
            else if(attributeName.equalsIgnoreCase("Gain"))
            attributeValue=1;
            else if(attributeName.equalsIgnoreCase("Stationary"))
            attributeValue=2;
            else if(attributeName.equalsIgnoreCase("Loss"))
            attributeValue=3;
            else if(attributeName.equalsIgnoreCase("Red"))
            attributeValue=1;
            else if(attributeName.equalsIgnoreCase("Yellow"))
            attributeValue=2;
            else if(attributeName.equalsIgnoreCase("Green"))
            attributeValue=3;
            
            else if(attributeName.equalsIgnoreCase("Obesse"))
            attributeValue=1;
            else if(attributeName.equalsIgnoreCase("Over Weight"))
            attributeValue=2;
            else if(attributeName.equalsIgnoreCase("Normal"))
            attributeValue=3;
            else if(attributeName.equalsIgnoreCase("Mild malnutrition"))
            attributeValue=4;
            else if(attributeName.equalsIgnoreCase("Moderate malnutrition"))
            attributeValue=5;
            else if(attributeName.equalsIgnoreCase("Severe malnutrition"))
            attributeValue=6;
            
            else if(attributeName.equalsIgnoreCase("Rarely"))
            attributeValue=5;
            else if(attributeName.equalsIgnoreCase("Sometimes"))
            attributeValue=6;
            else if(attributeName.equalsIgnoreCase("Often"))
            attributeValue=7;
            else if(attributeName.equalsIgnoreCase("4 or more"))
            attributeValue=4; 
            else if(attributeName.equalsIgnoreCase("1"))
            attributeValue=1;
            else if(attributeName.equalsIgnoreCase("2"))
            attributeValue=2;
            else if(attributeName.equalsIgnoreCase("3"))
            attributeValue=3;
        }
        return attributeValue;
    }
}
