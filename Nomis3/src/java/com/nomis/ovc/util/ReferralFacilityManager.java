/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;


import com.nomis.ovc.business.ReferralFacility;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class ReferralFacilityManager implements Serializable 
{
    public static ReferralFacility getReferralFacilityById(String facilityId)
    {
        ReferralFacility rf=new ReferralFacility();
        try
        {
            if(facilityId !=null)
            {
                DaoUtility daoutil=new DaoUtility();
                rf=daoutil.getReferralFacilityDaoInstance().getReferralFacility(facilityId);
                if(rf==null)
                {
                    rf=new ReferralFacility();
                    rf.setFacilityId(facilityId);
                    rf.setFacilityName("");
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return rf;
    }
    public List loadfacility(String level2OuId,String level3OuId)
    {
        List facilityList=null;
        try
        {
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setLevel2OuId(level2OuId);
            rpt.setLevel3OuId(level3OuId);
            DaoUtility util=new DaoUtility();
            facilityList=util.getReferralFacilityDaoInstance().getReferralFacilitiesByOrgUnit(rpt);//.getAllReferralFacilities();
            if(facilityList==null)
            facilityList=new ArrayList();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return facilityList;
    }
}
