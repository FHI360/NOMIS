/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.CareAndSupportChecklist;
import com.nomis.ovc.dao.CareAndSupportChecklistDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class HivOperationsManager 
{
    
    public int markCareAndSupportCheckRecordsForDeleteForBeneficiariesWithChangedHivStatus(String beneficiaryId)
    {
        int count=0;
        try
        {
            DaoUtility util=new DaoUtility();
            Beneficiary beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
            if(beneficiary==null)
            beneficiary=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
            if(beneficiary !=null)
            {
                if(beneficiary.getCurrentHivStatus() !=AppConstant.HIV_POSITIVE_NUM)
                {
                    CareAndSupportChecklistDao dao=util.getCareAndSupportChecklistDaoInstance();
                    List list=dao.getCareAndSupportChecklist(beneficiaryId);
                    if(list !=null)
                    {
                        for(Object obj:list)
                        {
                            CareAndSupportChecklist csc=(CareAndSupportChecklist)obj;
                            dao.markForDelete(csc);
                            count++;
                        }
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
    public Beneficiary processBeneficiaryHivStatus(Beneficiary beneficiary)
    {
        if(beneficiary.getCurrentHivStatus()>beneficiary.getBaselineHivStatus() && beneficiary.getBaselineHivStatus()>0)
        {
            beneficiary.setBaselineHivStatus(beneficiary.getCurrentHivStatus());
        }
        if(beneficiary.getDateOfBaselineHivStatus()==null)
        beneficiary.setDateOfBaselineHivStatus(beneficiary.getDateOfEnrollment());
        if(beneficiary.getDateOfCurrentHivStatus()==null)
        beneficiary.setDateOfCurrentHivStatus(beneficiary.getDateOfEnrollment());
        
        if(beneficiary.getDateOfCurrentHivStatus().before(beneficiary.getDateOfBaselineHivStatus()))
        beneficiary.setDateOfBaselineHivStatus(beneficiary.getDateOfCurrentHivStatus());
        if(beneficiary.getHivTreatmentFacilityId() !=null && beneficiary.getHivTreatmentFacilityId().equalsIgnoreCase("select"))
            beneficiary.setHivTreatmentFacilityId(null);
            //ovc=getPreparedOvc(ovc);
            if(beneficiary.getDateOfCurrentHivStatus()==null)
            {
                beneficiary.setCurrentHivStatus(beneficiary.getBaselineHivStatus());
                beneficiary.setDateOfCurrentHivStatus(beneficiary.getDateOfEnrollment());
            }
        //if current hiv status is not positive, beneficiary cannot be on treatment. Set facility as null
        if(beneficiary.getCurrentHivStatus() !=AppConstant.HIV_POSITIVE_NUM)
        {
            beneficiary.setEnrolledOnTreatment(0);
            beneficiary.setHivTreatmentFacilityId(null);
            beneficiary.setDateEnrolledOnTreatment(DateManager.getDefaultStartDateInstance());
        }
        else
        {
            //if current hiv status is positive but beneficiary is not on treatment, set facility as null 
            if(beneficiary.getEnrolledOnTreatment() !=AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            {
                beneficiary.setHivTreatmentFacilityId(null);
                beneficiary.setDateEnrolledOnTreatment(DateManager.getDefaultStartDateInstance());
            }
            else
            {
                if(beneficiary.getDateEnrolledOnTreatment()==null)
                beneficiary.setDateEnrolledOnTreatment(beneficiary.getDateOfCurrentHivStatus());
            }
        }
        /*
         * if(!ovc.isForUpdateHivStatus() && ovc.getDateOfCurrentHivStatus().before(ovc2.getDateOfCurrentHivStatus()))
                    {
                        ovc.setCurrentHivStatus(ovc2.getCurrentHivStatus());
                        ovc.setDateOfCurrentHivStatus(ovc2.getDateOfCurrentHivStatus());
                        ovc.setEnrolledOnTreatment(ovc2.getEnrolledOnTreatment());
                        ovc.setHivTreatmentFacilityId(ovc2.getHivTreatmentFacilityId());
                        
                    }
         */
        return beneficiary;
    }
    public boolean isHivStatusKnownAtBaseline(Beneficiary beneficiary)
    {
        boolean hivStatusKnownAtBaseline=true;
        if((beneficiary.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM || beneficiary.getBaselineHivStatus()==AppConstant.HIV_NEGATIVE_NUM) && beneficiary.getCurrentHivStatus() ==AppConstant.HIV_UNKNOWN_NUM)
        {
            hivStatusKnownAtBaseline=false;
        }
        return hivStatusKnownAtBaseline;
    }
    public boolean isBaselineHivStatusPositive(Beneficiary beneficiary)
    {
        boolean baselineHivStatusPositive=true;
        if((beneficiary.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM) && beneficiary.getCurrentHivStatus() !=AppConstant.HIV_POSITIVE_NUM)
        {
            baselineHivStatusPositive=false;
        }
        return baselineHivStatusPositive;
    }
}
