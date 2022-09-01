/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.CareAndSupportChecklist;
import com.nomis.ovc.business.NutritionAssessment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.dao.DaoUtility;

/**
 *
 * @author smomoh
 */
public class ObjectInteractionManager 
{
    public void updateBeneficiaryCareAndSupportInformation(CareAndSupportChecklist csc)
    {
        try
        {
            if(csc !=null)
            {
                String beneficiaryId=csc.getBeneficiaryId();
                DaoUtility util=new DaoUtility();
                Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
                if(ovc !=null)
                {
                    if(ovc.getDateEnrolledOnTreatment().before(csc.getDateEnrolledOnTreatment()))
                    {
                        if(ovc.getEnrolledOnTreatment() !=csc.getEnrolledOnTreatment())
                        {
                            ovc.setEnrolledOnTreatment(csc.getEnrolledOnTreatment());
                            ovc.setDateEnrolledOnTreatment(csc.getDateEnrolledOnTreatment());
                        }
                        
                        ovc.setHivTreatmentFacilityId(csc.getFacilityId());
                        util.getChildEnrollmentDaoInstance().updateOvcOnly(ovc);

                    }
                }
                else
                {
                    AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
                    if(ahm !=null)
                    {
                        if(ahm.getDateEnrolledOnTreatment().before(csc.getDateEnrolledOnTreatment()))
                        {
                            if(ahm.getEnrolledOnTreatment() !=csc.getEnrolledOnTreatment())
                            {
                                ahm.setEnrolledOnTreatment(csc.getEnrolledOnTreatment());
                                ahm.setDateEnrolledOnTreatment(csc.getDateEnrolledOnTreatment());
                            }
                            //if(ahm.getHivTreatmentFacilityId()==null)
                            ahm.setHivTreatmentFacilityId(csc.getFacilityId());
                            util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                            
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateOvcNutritionAttributes(NutritionAssessment na) throws Exception
    {
        if(na !=null)
        {
            
        }
    }
}
