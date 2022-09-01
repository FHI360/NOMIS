/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class BeneficiaryTypeManager implements Serializable 
{
    public static BeneficiaryType getBeneficiaryType(int value)
    {
        BeneficiaryType bt=new BeneficiaryType();
        if(value==AppConstant.HOUSEHOLD_TYPE_NUM)
        {
            bt.setCode("HH");
            bt.setName("Household head");
            bt.setValue(value);
        }
        else if(value==AppConstant.HOUSEHOLDHEAD_CAREGIVER_NUM)
        {
            bt.setCode("HC");
            bt.setName("Household head/Caregiver");
            bt.setValue(value);
        }
        else if(value==AppConstant.CAREGIVER_TYPE_NUM)
        {
            bt.setCode("AHM");
            bt.setName("Adult/Caregiver");
            bt.setValue(value);
        }
        else if(value==AppConstant.OVC_TYPE_NUM)
        {
            bt.setCode("OVC");
            bt.setName("Child");
            bt.setValue(value);
        }
        return bt;
    }
    public static BeneficiaryType getBeneficiaryType(String name)
    {
        BeneficiaryType bt=new BeneficiaryType();
        if(name !=null)
        {
            if(name.equalsIgnoreCase("Household head"))
            {
                bt.setCode("HH");
                bt.setName(name);
                bt.setValue(1);
            }
            else if(name.equalsIgnoreCase("Adult"))
            {
                bt.setCode("AHM");
                bt.setName(name);
                bt.setValue(2);
            }
            else if(name.equalsIgnoreCase("Child"))
            {
                bt.setCode("OVC");
                bt.setName(name);
                bt.setValue(3);
            }
        }
        return bt;
    }
}
