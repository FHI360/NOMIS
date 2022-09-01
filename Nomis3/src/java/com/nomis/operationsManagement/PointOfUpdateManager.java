/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

/**
 *
 * @author smomoh
 */
public class PointOfUpdateManager 
{
    public static PointOfUpdate getPointOfUpdate(int value)
    {
        PointOfUpdate bt=new PointOfUpdate();
        if(value==1)
        {
            bt.setCode("HH");
            bt.setName("HH enrollment");
            bt.setValue(value);
        }
        else if(value==2)
        {
            bt.setCode("AHM");
            bt.setName("AHM enrollment");
            bt.setValue(value);
        }
        else if(value==3)
        {
            bt.setCode("OVC");
            bt.setName("Child enrollment");
            bt.setValue(value);
        }
        else if(value==4)
        {
            bt.setCode("HSM");
            bt.setName("HIV status manager");
            bt.setValue(value);
        }
        return bt;
    }
    public static PointOfUpdate getPointOfUpdate(String name)
    {
        PointOfUpdate bt=new PointOfUpdate();
        if(name !=null)
        {
            if(name.equalsIgnoreCase("Household"))
            {
                bt.setCode("HH");
                bt.setName(name);
                bt.setValue(1);
            }
            else if(name.equalsIgnoreCase("Adult household member enrollment"))
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
