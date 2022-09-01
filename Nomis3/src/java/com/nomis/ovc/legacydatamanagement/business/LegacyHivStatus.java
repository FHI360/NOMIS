/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.business;


import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class LegacyHivStatus implements Serializable
{
    private String hivStatusCode;
    private String hivStatusName;
    private LegacyHivStatusUpdate activeHivStatus;

    public String getHivStatusCode() {
        return hivStatusCode;
    }

    public void setHivStatusCode(String hivStatusCode) {
        this.hivStatusCode = hivStatusCode;
    }

    public String getHivStatusName() {
        return hivStatusName;
    }

    public void setHivStatusName(String hivStatusName) {
        this.hivStatusName = hivStatusName;
    }

    public LegacyHivStatusUpdate getActiveHivStatus(String clientId) 
    {
        //HivStatusUpdateDao hsudao=new HivStatusUpdateDaoImpl();
        LegacyHivStatusUpdate hsu=null;
        try
        {
            //hsu=hsudao.getActiveHivStatusUpdatesByClientId(clientId);
            if(hsu !=null)
            activeHivStatus=hsu;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return hsu;
    }
    public void setActiveHivStatus(LegacyHivStatusUpdate activeHivStatus) 
    {
        this.activeHivStatus = activeHivStatus;
    }
    public LegacyHivStatusUpdate getNewHivStatus(String clientId,String enrollmentDate,String pointOfService)
    {
        LegacyHivStatusUpdate newHivStatus=new LegacyHivStatusUpdate();
        newHivStatus.setClientEnrolledInCare("No");
        newHivStatus.setEnrolledOnART("No");
        newHivStatus.setPointOfUpdate(pointOfService);
        newHivStatus.setDateOfCurrentStatus(enrollmentDate);
        newHivStatus.setHivStatus(AppConstant.HIV_UNKNOWN);    
        return newHivStatus;
    }
    public LegacyHivStatusUpdate getDefaultHivStatus(String clientId,String enrollmentDate,String pointOfService)
    {
        LegacyHivStatusUpdate defaultHivStatus=new LegacyHivStatusUpdate();
        defaultHivStatus=getActiveHivStatus(clientId);
        if(defaultHivStatus==null)
        defaultHivStatus=getNewHivStatus(clientId, enrollmentDate, pointOfService);
        return defaultHivStatus;
    }
    public LegacyHivStatusUpdate getCurrentHivStatus(String clientId,String dateOfStatus) 
    {
        //HivStatusUpdateDao hsudao=new HivStatusUpdateDaoImpl();
        LegacyHivStatusUpdate hsu=null;
        try
        {
            //hsu=hsudao.getHivStatusUpdatesByClientIdAndDateOfStatus(clientId, dateOfStatus);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return hsu;
    }
    public static int getStatusWeight(String status)
    {
        int statusCode=0;
        if(status !=null)
        {
            if(status.equalsIgnoreCase(AppConstant.HIV_UNKNOWN) || status.equalsIgnoreCase(AppConstant.HIV_TEST_NOT_INDICATED) || status.equalsIgnoreCase(AppConstant.HIV_TEST_NOT_DONE) || status.equalsIgnoreCase(AppConstant.HIV_RESULT_NOT_DISCLOSED))
            statusCode=1;
            else if(status.equalsIgnoreCase(AppConstant.HIV_NEGATIVE))
            statusCode=2;
            else if(status.equalsIgnoreCase(AppConstant.HIV_POSITIVE))
            statusCode=3;
        }
        return statusCode;
    }
}
