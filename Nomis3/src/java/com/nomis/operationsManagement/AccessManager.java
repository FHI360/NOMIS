/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.User;
import com.nomis.ovc.business.UserRole;
import com.nomis.ovc.dao.DaoUtility;


/**
 *
 * @author smomoh
 */
public class AccessManager 
{
    public String getActionName(String requiredAction,User user)
    {
        if(requiredAction !=null && requiredAction.equalsIgnoreCase("delete"))
        {
           if(user==null || !user.isSuperUser())
           {
              requiredAction="markForDelete"; 
           }
        }
        return requiredAction;
    }
    public static boolean isUserInRole(User user,String userPrivilegeId)
    {
        boolean userInRole=false;
        if(isDue()) 
        return false;
        if(user !=null)
        {
            if(user.isSuperUser())
            userInRole=true;
            else
            {
                if(user.getUserroleId() !=null)
                {
                    UserRole ur=getUserRole(user.getUserroleId());
                    if(ur !=null)
                    {
                        String[] accessPrivilege=ur.getAssignedPrivileges().split(",");
                        if(accessPrivilege !=null)
                        {
                            String userPrivilege=null;
                            for(int i=0; i<accessPrivilege.length; i++)
                            {
                                userPrivilege=accessPrivilege[i];
                                if(userPrivilege !=null && userPrivilege.trim().length()>0)
                                {
                                    userPrivilegeId=userPrivilegeId.trim();
                                    userPrivilege=userPrivilege.trim();
                                    if(userPrivilegeId.trim().equalsIgnoreCase(userPrivilege))
                                    userInRole=true;
                                }
                            }
                        }
                    }
                }
            }
        }
        //return true;
        return userInRole;
    }
    public static boolean userCanViewBeneficiaryInfo(User user)
    {
        boolean isUserInRole=isUserInRole(user,"viewdetails");
        return isUserInRole;
    }
    public static boolean isUserInDataEntryRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"dataentryxx");
        return isUserInRole;
    }
    public static boolean isUserInOrganizationUnitSetupRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"orguntsetup");
        return isUserInRole;
    }
    public static boolean isUserInAddUserRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"createusers");
        return isUserInRole;
    }
    public static boolean isUserInDataExportRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"exportdatax");
        return isUserInRole;
    }
    public static boolean isUserInDataImportRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"importdatax");
        return isUserInRole;
    }
    public static boolean isUserInDatabaseDefragmentationRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"defragtable");
        return isUserInRole;
    }
    public static boolean isUserInReferralFacilityRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"addfacility");
        return isUserInRole;
    }
    public static boolean userHasNoRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"norolexxxxx");
        return isUserInRole;
    }
    /*public static boolean isUserInViewBeneficiaryDetailsRole(User user)
    {
        boolean isUserInRole=isUserInRole(user,"viewdetails");
        return isUserInRole;
    }*/
    public static boolean userHasSuperUserRole(User user)
    {
        //if(isDue()) 
        //return false;
        boolean isUserInRole=false;
        if(user !=null)
        {
            if(user.isSuperUser())
            isUserInRole=true;
        }
        return isUserInRole;
    }
    public static boolean isDue()
    {
        //if(DaoUtility.isDue())
        //return true; 
        return false;
    }
    public static UserRole getUserRole(String roleId)
    {
        UserRole ur=null;
        try
        {
            DaoUtility util=new DaoUtility();
            ur=util.getUserRoleDaoInstance().getUserRole(roleId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ur;
    }
}
