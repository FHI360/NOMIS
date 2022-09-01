/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.validation;

import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;

/**
 *
 * @author smomoh
 */
public class OrganizationUnitValidator 
{
    public int validateOuCode(String ouCode,int ouLevel,String ouName)
    {
        int validFlag=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(ouCode !=null && ouCode.trim().length()>0)
            {
                ouCode=ouCode.trim();
                if(ouCode.length() !=3)
                validFlag=2;
                else
                {
                    OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByOuCodeAndOuLevel(ouCode, ouLevel);
                    if(ou !=null && ouName !=null)
                    {
                        System.err.println("ou.getName() is "+ou.getName()+" and ouName is "+ouName);
                        if(ou.getName().equalsIgnoreCase(ouName))
                        validFlag=1;
                        else
                        validFlag=2;
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return validFlag;
    }
}
