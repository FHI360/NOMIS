/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

/**
 *
 * @author smomoh
 */
public class CaregiverRelationshipManager 
{
    public CaregiverRelationship getCaregiverRelationship(int value)
    {
        CaregiverRelationship cgrm=new CaregiverRelationship();
        cgrm.setCode("cgr");
        cgrm.setName("Other");
        if(value==1)
        {
            cgrm.setCode("cgr");
            cgrm.setName("Father");
            cgrm.setValue(value);
        }
        else if(value==2)
        {
            cgrm.setCode("cgr");
            cgrm.setName("Mother");
            cgrm.setValue(value);
        }
        else if(value==3)
        {
            cgrm.setCode("cgr");
            cgrm.setName("Grandparents");
            cgrm.setValue(value);
        }
        else if(value==4)
        {
            cgrm.setCode("cgr");
            cgrm.setName("Aunt/Uncle");
            cgrm.setValue(value);
        }
        else if(value==5)
        {
            cgrm.setCode("cgr");
            cgrm.setName("Sister/Brother");
            cgrm.setValue(value);
        }
        else if(value==6)
        {
            cgrm.setCode("cgr");
            cgrm.setName("Guardian");
            cgrm.setValue(value);
        }
        else if(value==7)
        {
            cgrm.setCode("cgr");
            cgrm.setName("Social worker");
            cgrm.setValue(value);
        }
        else if(value==8)
        {
            cgrm.setCode("cgr");
            cgrm.setName("Caregiver");
            cgrm.setValue(value);
        }
        else if(value==9)
        {
            cgrm.setCode("spo");
            cgrm.setName("Spouse");
            cgrm.setValue(value);
        }
        else if(value==10)
        {
            cgrm.setCode("nfm");
            cgrm.setName("Nuclear family member");
            cgrm.setValue(value);
        }
        else if(value==11)
        {
            cgrm.setCode("nfm");
            cgrm.setName("Neighbour");
            cgrm.setValue(value);
        }
        else if(value==12)
        {
            cgrm.setCode("fnd");
            cgrm.setName("Friend");
            cgrm.setValue(value);
        }
        return cgrm;
    }
    public static CaregiverRelationship getCaregiverRelationship(String name)
    {
        CaregiverRelationship cgrm=new CaregiverRelationship();
        if(name !=null)
        {
            name=name.trim();
            if(name.equalsIgnoreCase("Father"))
            {
                cgrm.setCode("fth");
                cgrm.setName(name);
                cgrm.setValue(1);
            }
            else if(name.equalsIgnoreCase("Mother"))
            {
                cgrm.setCode("mth");
                cgrm.setName(name);
                cgrm.setValue(2);
            }
            else if(name.equalsIgnoreCase("Grand parent"))
            {
                cgrm.setCode("gpr");
                cgrm.setName(name);
                cgrm.setValue(3);
            }
            else if(name.equalsIgnoreCase("Aunt/Uncle"))
            {
                cgrm.setCode("aou");
                cgrm.setName(name);
                cgrm.setValue(4);
            }
            else if(name.equalsIgnoreCase("Sister/Brother"))
            {
                cgrm.setCode("sob");
                cgrm.setName(name);
                cgrm.setValue(5);
            }
            else if(name.equalsIgnoreCase("Guardian"))
            {
                cgrm.setCode("gdn");
                cgrm.setName(name);
                cgrm.setValue(6);
            }
            else if(name.equalsIgnoreCase("Social worker"))
            {
                cgrm.setCode("scw");
                cgrm.setName(name);
                cgrm.setValue(7);
            }
            else if(name.equalsIgnoreCase("Caregiver"))
            {
                cgrm.setCode("cgv");
                cgrm.setName(name);
                cgrm.setValue(8);
            }
            else if(name.equalsIgnoreCase("Spouse"))
            {
                cgrm.setCode("spo");
                cgrm.setName(name);
                cgrm.setValue(9);
            }
            else if(name.equalsIgnoreCase("Nuclear family member"))
            {
                cgrm.setCode("nfm");
                cgrm.setName(name);
                cgrm.setValue(10);
            }
            else if(name.equalsIgnoreCase("Neighbour"))
            {
                cgrm.setCode("nfm");
                cgrm.setName(name);
                cgrm.setValue(11);
            }
            else if(name.equalsIgnoreCase("Friend"))
            {
                cgrm.setCode("fnd");
                cgrm.setName(name);
                cgrm.setValue(12);
            }  
        }
        return cgrm;
    }
}
