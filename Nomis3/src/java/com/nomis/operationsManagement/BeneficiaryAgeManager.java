/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.util.AppConstant;

/**
 *
 * @author smomoh
 */
public class BeneficiaryAgeManager 
{
    public int getAgeUnit(String ageUnitInStringFormat)
    {
        int ageUnit=0;
        if(ageUnitInStringFormat !=null && ageUnitInStringFormat.trim().length()>0)
        {
            if(ageUnitInStringFormat.equalsIgnoreCase("Year"))
                ageUnit=AppConstant.AGEUNIT_YEAR_NUM;
            else if(ageUnitInStringFormat.equalsIgnoreCase("Month"))
            {
                ageUnit=AppConstant.AGEUNIT_MONTH_NUM;
            }
        }
        return ageUnit;
    }
}
