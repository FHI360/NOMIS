/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

/**
 *
 * @author smomoh
 */
public class SourceOfInformationManager 
{
    public SourceOfInformation getSourceOfInformation(int value)
    {
        SourceOfInformation soi=new SourceOfInformation();
        if(value==1)
        {
            soi.setCode("soi");
            soi.setName("Parent");
            soi.setValue(value);
        }
        else if(value==2)
        {
            soi.setCode("soi");
            soi.setName("Relation");
            soi.setValue(value);
        }
        else if(value==3)
        {
            soi.setCode("soi");
            soi.setName("Neighbour");
            soi.setValue(value);
        }
        else if(value==4)
        {
            soi.setCode("soi");
            soi.setName("Guardian");
            soi.setValue(value);
        }
        else if(value==5)
        {
            soi.setCode("soi");
            soi.setName("");
            soi.setValue(value);
        }
        else if(value==6)
        {
            soi.setCode("soi");
            soi.setName("");
            soi.setValue(value);
        }
        else if(value==7)
        {
            soi.setCode("soi");
            soi.setName("Social worker");
            soi.setValue(value);
        }
        return soi;
    }
    private static SourceOfInformation getSourceOfInformation(String name)
    {
        SourceOfInformation soi=new SourceOfInformation();
        if(name !=null)
        {
            if(name.equalsIgnoreCase("Parent"))
            {
                soi.setCode("soi");
                soi.setName(name);
                soi.setValue(1);
            }
            else if(name.equalsIgnoreCase("Relation"))
            {
                soi.setCode("soi");
                soi.setName(name);
                soi.setValue(2);
            }
            else if(name.equalsIgnoreCase("Neighbour"))
            {
                soi.setCode("soi");
                soi.setName(name);
                soi.setValue(3);
            }
            else if(name.equalsIgnoreCase("Guardian"))
            {
                soi.setCode("soi");
                soi.setName(name);
                soi.setValue(4);
            }
            else if(name.equalsIgnoreCase(""))
            {
                soi.setCode("soi");
                soi.setName(name);
                soi.setValue(5);
            }
            else if(name.equalsIgnoreCase(""))
            {
                soi.setCode("soi");
                soi.setName(name);
                soi.setValue(6);
            }
            else if(name.equalsIgnoreCase("Social worker"))
            {
                soi.setCode("soi");
                soi.setName(name);
                soi.setValue(7);
            }
        }
        return soi;
    }
}
