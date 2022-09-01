/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

/**
 *
 * @author smomoh
 */
public class DisabilityManager 
{
    public Disability getDisability(int code)
    {
        Disability disabled=new Disability();
        if(code==1)
        {
            disabled.setCode(code);
            disabled.setName("Disabled");
            disabled.setValue("Yes");
        }
        else if(code==2)
        {
            disabled.setCode(code);
            disabled.setName("Not disabled");
            disabled.setValue("No");
        }
        return disabled;
    }
    public static Disability getDisability(String value)
    {
        Disability disabled=new Disability();
        if(value!=null)
        {
            value=value.trim();
            if(value.equalsIgnoreCase("Yes"))
            {
                disabled.setCode(1);
                disabled.setName("Disabled");
                disabled.setValue(value);
            }
            else if(value.equalsIgnoreCase("No"))
            {
                disabled.setCode(1);
                disabled.setName("Not disabled");
                disabled.setValue(value);
            }
        }
        return disabled;
    }
}
