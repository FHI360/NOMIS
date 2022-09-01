/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

/**
 *
 * @author smomoh
 */
public class GraduationManager 
{
    public static int getGraduationScore(int value)
    {
       int score=0;
       //A yes(1) answer or N/A(3) has a score of 1
       if(value ==1 || value ==3)
       {
           score=1;
       }
       else if(value ==2)
       {
           score=0;
       }
       return score;
    }
}
