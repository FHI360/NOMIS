/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

/**
 *
 * @author smomoh
 */
public class EnrolledOnTreatmentManager 
{
    public static EnrolledOnTreatment getEnrolledOnTreatment(int value)
    {
        EnrolledOnTreatment eot=new EnrolledOnTreatment();
        if(value==0)
        {
            eot.setValue(value);
            eot.setCode(" ");
            eot.setName(" ");
        }
        else if(value==1)
        {
            eot.setValue(value);
            eot.setCode("Yes");
            eot.setName("Enrolled on treatment");
        }
        else if(value==2)
        {
            eot.setValue(value);
            eot.setCode("No");
            eot.setName("Not Enrolled on treatment");
        }
        return eot;
    }
    public static EnrolledOnTreatment getEnrolledOnTreatment(String code)
    {
        EnrolledOnTreatment eot=new EnrolledOnTreatment();
        if(code !=null)
        {
            if(code.equalsIgnoreCase("Yes"))
            {
                eot.setValue(1);
                eot.setCode(code);
                eot.setName("Enrolled on treatment");
            }
            else if(code.equalsIgnoreCase("No"))
            {
                eot.setValue(2);
                eot.setCode(code);
                eot.setName("Not Enrolled on treatment");
            }
        }
        return eot;
    }
}
