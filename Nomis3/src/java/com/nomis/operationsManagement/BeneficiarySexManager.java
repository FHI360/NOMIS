/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

/**
 *
 * @author smomoh
 */
public class BeneficiarySexManager 
{
    
    private String getShortSexName(int code)
    {
        String shortName="Unknown";
        if(code==1)
        shortName="M";
        else if(code==2)
        shortName="F";
        return shortName;
    }
    private String getFullSexName(int code)
    {
        String shortName="Unknown";
        if(code==1)
        shortName="Male";
        else if(code==2)
        shortName="Female";
        return shortName;
    }
    
}
