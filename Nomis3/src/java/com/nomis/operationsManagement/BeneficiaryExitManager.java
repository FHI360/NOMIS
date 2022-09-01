/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.dao.DaoUtility;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class BeneficiaryExitManager 
{
    public void withdrawHousehold(String hhUniqueId, int exitType,Date dateOfExit)
    {
        try
        {
            System.err.println("exitType is "+exitType);
            DaoUtility util=new DaoUtility();
            HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
            if(hhe !=null)
            {
                withdrawHousehold(hhe, exitType,dateOfExit);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void withdrawHousehold(HouseholdEnrollment hhe, int exitType,Date dateOfExit)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            if(hhe !=null && hhe.isCurrentlyEnrolled())
            {
                String hhUniqueId=hhe.getHhUniqueId();
                hhe.setCurrentEnrollmentStatus(exitType);
                hhe.setDateOfCurrentStatus(dateOfExit);
                util.getHouseholdEnrollmentDaoInstance().updateHouseholdEnrollment(hhe);
                withdrawAllAdultHouseholdMembers(hhUniqueId,exitType,dateOfExit);
                withdrawAllChildreInHousehold(hhUniqueId, exitType,dateOfExit);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public int withdrawAllAdultHouseholdMembers(String hhUniqueId, int exitType,Date dateOfExit)
    {
        int count=0;
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMembersPerHousehold(hhUniqueId);
            if(list !=null && !list.isEmpty())
            {
                AdultHouseholdMember ahm=null;
                for(Object obj:list)
                {
                    ahm=(AdultHouseholdMember)obj;
                    if(ahm.isCurrentlyEnrolled())
                    {
                        withdrawAdultHouseholdMember(ahm, exitType,dateOfExit);
                        count++;
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public void withdrawAdultHouseholdMember(String beneficiaryId, int exitType,Date dateOfExit)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
            if(ahm !=null)
            {
                //call the overloaded form of this method to withdraw the beneficiary.
                withdrawAdultHouseholdMember(ahm, exitType,dateOfExit);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void withdrawAdultHouseholdMember(AdultHouseholdMember ahm, int exitType,Date dateOfExit)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            if(ahm !=null)
            {
                ahm.setCurrentEnrollmentStatus(exitType);
                ahm.setDateOfCurrentEnrollmentStatus(dateOfExit);
                util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void withdrawChild(String beneficiaryId, int exitType,Date dateOfExit)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
            if(ovc !=null)
            {
                withdrawChild(ovc, exitType,dateOfExit);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void withdrawChild(Ovc ovc, int exitType,Date dateOfExit)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            if(ovc !=null)
            {
                ovc.setCurrentEnrollmentStatus(exitType);
                ovc.setDateOfCurrentEnrollmentStatus(dateOfExit);
                util.getChildEnrollmentDaoInstance().updateOvcOnly(ovc);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public int withdrawAllChildreInHousehold(String hhUniqueId, int exitType,Date dateOfExit)
    {
        int count=0;
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);
            if(list !=null && !list.isEmpty())
            {
                for(Object obj:list)
                {
                    Ovc ovc=(Ovc)obj;
                    if(ovc.isCurrentlyEnrolled())
                    {
                        withdrawChild(ovc, exitType,dateOfExit);
                        count++;
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
}
