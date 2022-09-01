/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

/**
 *
 * @author smomoh
 */
public class BirthCertificateManager 
{
    public static BirthCertificate getBirthCertificate(int value)
    {
        BirthCertificate bct=new BirthCertificate();
        if(value==0)
        {
            bct.setValue(value);
            bct.setCode("");
            bct.setName("None");
        }
        else if(value==1)
        {
            bct.setValue(value);
            bct.setCode("");
            bct.setName("Yes");
        }
        else if(value==2)
        {
            bct.setValue(value);
            bct.setCode("");
            bct.setName("No");
        }
        return bct;
    }
    public static BirthCertificate getBirthCertificate(String value)
    {
        BirthCertificate bct=new BirthCertificate();
        if(value !=null)
        {
            if(value.equalsIgnoreCase("Yes"))
            {
                bct.setValue(1);
                bct.setCode("");
                bct.setName(value);
            }
            else if(value.equalsIgnoreCase("No"))
            {
                bct.setValue(2);
                bct.setCode("");
                bct.setName(value);
            }
            else if(value.equalsIgnoreCase("N/A"))
            {
                bct.setValue(0);
                bct.setCode("");
                bct.setName(value);
            }
        }
        return bct;
    }
}
