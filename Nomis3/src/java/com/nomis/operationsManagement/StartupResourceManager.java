/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.Partner;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class StartupResourceManager 
{
    DaoUtility util=new DaoUtility();
    public List getLevel3OrgUnits(String level2OuId)
    {
        List level2OuList=new ArrayList();
        try
        {
            List list=util.getOrganizationUnitDaoInstance().getOrganizationUnityByParentId(level2OuId);
            if(list !=null && !list.isEmpty())
            {
                level2OuList.addAll(list);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return level2OuList;
    }
    public OrganizationUnit getOrganizationUnit(String uid)
    {
        OrganizationUnit ou=null;
        try
        {
            ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(uid);
            if(ou==null)
            {
                ou=new OrganizationUnit();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ou;
    }
    public Partner getPartner(String partnerCode)
    {
        Partner partner=null;
        try
        {
            partner=util.getPartnerDaoInstance().getPartner(partnerCode);
            if(partner==null)
            {
                partner=new Partner();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return partner;
    }
    public CommunityBasedOrganization getCommunityBasedOrganization(String cboId)
    {
        CommunityBasedOrganization cbo=null;
        try
        {
            cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboId);
            if(cbo==null)
            {
                cbo=new CommunityBasedOrganization();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return cbo;
    }
}
