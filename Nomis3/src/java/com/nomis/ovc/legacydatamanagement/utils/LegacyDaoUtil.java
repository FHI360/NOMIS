/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.utils;

import com.nomis.ovc.dao.DataImportUploadManagerDao;
import com.nomis.ovc.dao.DataImportUploadManagerDaoImpl;
import com.nomis.ovc.legacydatamanagement.business.LegacyOvcWithdrawal;

/**
 *
 * @author smomoh
 */
public class LegacyDaoUtil 
{
    public LegacyOvcWithdrawal getWithdrawalWithCleanedValues(LegacyOvcWithdrawal withdrawal)
 {
     try
     {
         String reasonWithdrawal=withdrawal.getReasonWithdrawal();
         if(withdrawal !=null && reasonWithdrawal !=null)
         {
            if(withdrawal.getType() !=null && withdrawal.getType().equalsIgnoreCase(NomisConstant.HOUSEHOLD_TYPE))
            { 
                if(reasonWithdrawal.equalsIgnoreCase(NomisConstant.DIED))
                withdrawal.setType("caregiver");
            }
            else if(reasonWithdrawal.equalsIgnoreCase("ageabove18") || reasonWithdrawal.equalsIgnoreCase("Age > 18") || reasonWithdrawal.equalsIgnoreCase("Age &gt; 18"))
            withdrawal.setReasonWithdrawal(NomisConstant.AGED_OUT);//
            else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase("Graduated") || withdrawal.getReasonWithdrawal().equalsIgnoreCase("Graduated from program")  || withdrawal.getReasonWithdrawal().equalsIgnoreCase("") || withdrawal.getReasonWithdrawal().equalsIgnoreCase(" ") || withdrawal.getReasonWithdrawal().equalsIgnoreCase("  ") || withdrawal.getReasonWithdrawal().equalsIgnoreCase("   "))
            withdrawal.setReasonWithdrawal(NomisConstant.GRADUATED);
            else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase("ageabove17") || withdrawal.getReasonWithdrawal().equalsIgnoreCase("ageabove18")  || withdrawal.getReasonWithdrawal().indexOf("AGE") !=-1)
            {
                //Ovc ovc=this.getOvcDaoInstance().getOvc(withdrawal.getOvcId());
                //if(ovc !=null && ovc.getCurrentAge()<18)
                withdrawal.setReasonWithdrawal(NomisConstant.GRADUATED);
            }
            if(withdrawal.getType()==null || (!withdrawal.getType().equalsIgnoreCase(NomisConstant.OVC_TYPE) && !withdrawal.getType().equalsIgnoreCase(NomisConstant.Caregiver_TYPE) && !withdrawal.getType().equalsIgnoreCase(NomisConstant.HOUSEHOLD_TYPE)))
            {
                withdrawal=getWithdrawal(withdrawal);
            }
         }
     }
     catch(Exception ex)
     {
         ex.printStackTrace();
     }
     return withdrawal;
 }
 public LegacyOvcWithdrawal getWithdrawal(LegacyOvcWithdrawal withdrawal)
{
    String uniqueId=withdrawal.getOvcId();
    if(uniqueId !=null)
    {
        if(uniqueId.trim().length() == 17)
        withdrawal.setType(NomisConstant.HOUSEHOLD_TYPE);
        else if(uniqueId.trim().length() > 17 && (uniqueId.trim().length() < 20))
        withdrawal.setType(NomisConstant.Caregiver_TYPE);
        else if(uniqueId.trim().length() > 19)
        withdrawal.setType(NomisConstant.OVC_TYPE);

        //System.err.println("withdrawal with id "+withdrawal.getOvcId()+" updated with type: "+withdrawal.getType());
    }
    return withdrawal;
}
 public DataImportUploadManagerDao getDataImportUploadManagerDaoInstance()
{
    return new DataImportUploadManagerDaoImpl();
}
}
