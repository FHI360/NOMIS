/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

/**
 *
 * @author smomoh
 */
public class SchoolStatusManager 
{
    public static SchoolStatus getSchoolStatus(int code)
    {
        SchoolStatus schoolStatus=new SchoolStatus();
        if(code==1)
        {
            schoolStatus.setCode("Yes");
            schoolStatus.setName("In school");
            schoolStatus.setValue(code);
        }
        else if(code==2)
        {
            schoolStatus.setCode("No");
            schoolStatus.setName("Not in school");
            schoolStatus.setValue(code);
        }
        else if(code==3)
        {
            schoolStatus.setCode("N/A");
            schoolStatus.setName("Not applicable");
            schoolStatus.setValue(code);
        }
        return schoolStatus;
    }
    public static SchoolStatus getSchoolStatus(String code)
    {
        SchoolStatus schoolStatus=new SchoolStatus();
        if(code.equalsIgnoreCase("Yes"))
        {
            schoolStatus.setCode(code);
            schoolStatus.setName("In school");
            schoolStatus.setValue(1);
        }
        else if(code.equalsIgnoreCase("No"))
        {
            schoolStatus.setCode(code);
            schoolStatus.setName("Not in school");
            schoolStatus.setValue(2);
        }
        else if(code.equalsIgnoreCase("N/A"))
        {
            schoolStatus.setCode(code);
            schoolStatus.setName("Not applicable");
            schoolStatus.setValue(3);
        }
        return schoolStatus;
    }
}
