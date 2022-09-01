/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnitHierachyAttribute;
import com.nomis.ovc.metadata.OrganizationUnitHierarchy;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smomoh
 */
public class OrganizationUnitHierarchyManager implements Serializable
{
    DaoUtility util=new DaoUtility();
    public static OrganizationUnitHierachyAttribute orgUnitHierachyAttribute=null;
    public static OrganizationUnitHierarchy validOuHierarchyName(String ouHierarchyName)
    {
        DaoUtility util=new DaoUtility();
        OrganizationUnitHierarchy ouh=null;
        try
        {
            ouh=util.getOrganizationUnitHierarchyDaoInstance().getOrganizationUnitHierarchyByName(ouHierarchyName);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ouh;
    }

    public static OrganizationUnitHierachyAttribute getOrgUnitHierachyAttribute() 
    {
        if(orgUnitHierachyAttribute==null)
        orgUnitHierachyAttribute=new OrganizationUnitHierachyAttribute();
        return orgUnitHierachyAttribute;
    }
    
    public static void setOrganizationUnitHierachyAttributes(HttpSession session)
    {
        DaoUtility util=new DaoUtility();
        try
        {//OrganizationUnitHierachyAttributeManager
            OrganizationUnitHierachyAttribute ouha=new OrganizationUnitHierachyAttribute();
            OrganizationUnitHierarchy ouh=null;
            List list=util.getOrganizationUnitHierarchyDaoInstance().getAllOrganizationUnitHierarchyRecords();
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    ouh=(OrganizationUnitHierarchy)obj;
                    if(ouh.getOulevel()==1)
                    ouha.setLevel1HierachyName(ouh.getName());
                    if(ouh.getOulevel()==2)
                    ouha.setLevel2HierachyName(ouh.getName());
                    if(ouh.getOulevel()==3)
                    ouha.setLevel3HierachyName(ouh.getName());
                    if(ouh.getOulevel()==4)
                    ouha.setLevel4HierachyName(ouh.getName());
                    if(ouh.getOulevel()==5)
                    ouha.setLevel5HierachyName(ouh.getName());
                    if(ouh.getOulevel()==6)
                    ouha.setLevel6HierachyName(ouh.getName());
                    if(ouh.getOulevel()==7)
                    ouha.setLevel7HierachyName(ouh.getName());
                    if(ouh.getOulevel()==8)
                    ouha.setLevel8HierachyName(ouh.getName());
                    if(ouh.getOulevel()==9)
                    ouha.setLevel9HierachyName(ouh.getName());
                    if(ouh.getOulevel()==10)
                    ouha.setLevel10HierachyName(ouh.getName());
                    orgUnitHierachyAttribute=ouha;
                }
                session.setAttribute("ouHierachyAttribute", ouha);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
