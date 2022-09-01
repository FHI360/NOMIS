/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class BeneficiaryDetailsManager implements Serializable
{
    public static boolean userCanViewBeneficiaryInfo(User user)
    {
        boolean userCanViewBeneficiaryInfo=false;
        if(AccessManager.userCanViewBeneficiaryInfo(user) || AccessManager.isUserInDataEntryRole(user))
        {
            userCanViewBeneficiaryInfo=true;
        }
        return userCanViewBeneficiaryInfo;
    }
    public static Ovc getPreparedOvc(Ovc ovc,User user)
    {
        if(!userCanViewBeneficiaryInfo(user))
        {
           ovc.setFirstName("XXXXXX");
           ovc.setSurname("XXXXXX");
           ovc.setAddress("XXXXXX");
           if(ovc.getHhe() !=null)
           {
                //ovc.setNationalId("");
                ovc.getHhe().setAddress("");
                if(ovc.getHhe().getPrCaregiver() !=null)
                ovc.getHhe().getPrCaregiver().setPhoneNumber("");
           }           
        }
        return ovc;
    }
    public static AdultHouseholdMember getPreparedAdultHouseholdMember(AdultHouseholdMember ahm,User user)
    {
        if(!userCanViewBeneficiaryInfo(user))
        {
           ahm.setFirstName("XXXXXX");
           ahm.setSurname("XXXXXX");
           ahm.setAddress("XXXXXX");
           if(ahm.getHhe() !=null)
           {
                ahm.getHhe().setAddress("XXXXXX");
                ahm.setPhoneNumber("XXXXXX");
                //ahm.setNationalId("XXXXXX");
           }   
        }
        return ahm;
    }
}
