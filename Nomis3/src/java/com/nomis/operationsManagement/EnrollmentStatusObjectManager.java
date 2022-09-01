/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.EnrollmentStatus;



/**
 *
 * @author smomoh
 */
public class EnrollmentStatusObjectManager 
{
    public static EnrollmentStatus getEnrollmentStatus(int statusCode)
    {
        EnrollmentStatus es=new EnrollmentStatus();
        es.setStatusCode(statusCode);
        if(statusCode==AppConstant.ACTIVE_NUM)
        {
            es.setStatusName(AppConstant.ACTIVE);
        }
        else if(statusCode==AppConstant.AGED_OUT_NUM)
        es.setStatusName(AppConstant.AGED_OUT);
        else if(statusCode==AppConstant.DIED_NUM)
        es.setStatusName(AppConstant.DIED);
        else if(statusCode==AppConstant.MIGRATED_NUM)
        es.setStatusName(AppConstant.MIGRATED);
        else if(statusCode==AppConstant.GRADUATED_NUM)
        es.setStatusName(AppConstant.GRADUATED);
        else if(statusCode==AppConstant.LOST_TO_FOLLOWUP_NUM)
        es.setStatusName(AppConstant.LOST_TO_FOLLOWUP);
        else if(statusCode==AppConstant.VOLUNTARILY_WITHDRAWN_NUM)
        es.setStatusName(AppConstant.VOLUNTARILY_WITHDRAWN);
        else if(statusCode==AppConstant.INACTIVE_NUM)
        es.setStatusName(AppConstant.INACTIVE);
        else if(statusCode==AppConstant.EXITED_WITHOUT_GRADUATION_NUM)
        es.setStatusName(AppConstant.EXITED_WITHOUT_GRADUATION);
        else if(statusCode==AppConstant.REENROLLED_NUM)
        es.setStatusName(AppConstant.REENROLLED_STATUS);
        else if(statusCode==AppConstant.TRANSFERED_PEPFAR_NUM)
        es.setStatusName(AppConstant.TRANSFERED_TO_PEPFAR);
        else if(statusCode==AppConstant.TRANSFERED_NONPEPFAR_NUM)
        es.setStatusName(AppConstant.TRANSFERED_TO_NONPEPFAR);
        return es;
    }
}
