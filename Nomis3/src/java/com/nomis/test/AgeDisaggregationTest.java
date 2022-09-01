/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.test;

import com.nomis.ovc.dao.SubQueryGenerator;

/**
 *
 * @author smomoh
 */
public class AgeDisaggregationTest 
{
    public static void testOvcAgeQuery()
    {
        int[] ovcAgeDisaggregation={0,0,1,4,5,9,10,14,15,17};
        SubQueryGenerator sqg=new SubQueryGenerator();
        for(int i=0; i<ovcAgeDisaggregation.length; i+=2)
        {
            System.err.println(sqg.getOvcCurrentAgeQuery(ovcAgeDisaggregation[i],ovcAgeDisaggregation[i+1]));
            //System.err.println(sqg.getAgeAtEnrollmentStatusQuery(ovcAgeDisaggregation[i]+"",ovcAgeDisaggregation[i+1]+""));
        }
    }
}
