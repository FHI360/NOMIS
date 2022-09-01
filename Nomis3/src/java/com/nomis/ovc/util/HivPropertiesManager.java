/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smomoh
 */
public class HivPropertiesManager 
{
    public static HivStatus getHivPositive()
    {
        HivStatus hivStatus=new HivStatus();
        hivStatus.setShortName("pos");
        hivStatus.setName("Positive");
        hivStatus.setCode(AppConstant.HIV_POSITIVE_NUM);
        return hivStatus;
    }
    public static HivStatus getHivNegative()
    {
        HivStatus hivStatus=new HivStatus();
        hivStatus.setShortName("neg");
        hivStatus.setName("Negative");
        hivStatus.setCode(AppConstant.HIV_NEGATIVE_NUM);
        return hivStatus;
    }
    public static HivStatus getHivUnknown()
    {
        HivStatus hivStatus=new HivStatus();
        hivStatus.setShortName("unk");
        hivStatus.setName("Unknown");
        hivStatus.setCode(AppConstant.HIV_UNKNOWN_NUM);
        return hivStatus;
    }
    public static HivStatus getHivUndisclosed()
    {
        HivStatus hivStatus=new HivStatus();
        hivStatus.setShortName("und");
        hivStatus.setName("Undisclosed");
        hivStatus.setCode(AppConstant.HIV_UNDISCLOSED_NUM);
        return hivStatus;
    }
    public static HivStatus getTestNotIndicatedHivStatus()
    {
        HivStatus hivStatus=new HivStatus();
        hivStatus.setShortName("tni");
        hivStatus.setName("Test not required");
        hivStatus.setCode(AppConstant.HIV_TEST_NOT_INDICATED_NUM);
        return hivStatus;
    }
    public static HivStatus getTestRequiredHivStatus()
    {
        HivStatus hivStatus=new HivStatus();
        hivStatus.setShortName("htr");
        hivStatus.setName("Unknown (Due to Risk assessment)");
        hivStatus.setCode(AppConstant.HIV_TEST_REQUIRED_NUM);
        return hivStatus;
    }
    public static HivStatus getEmptyHivStatus()
    {
        HivStatus hivStatus=new HivStatus();
        hivStatus.setShortName("emp");
        hivStatus.setName("select");
        hivStatus.setCode(0);
        return hivStatus;
    }
    public static HivStatus getHivStatus(int value)
    {
        if(value==AppConstant.HIV_POSITIVE_NUM)
        return getHivPositive();
        else if(value==AppConstant.HIV_NEGATIVE_NUM)
        return getHivNegative();
        else if(value==0 || value==AppConstant.HIV_UNKNOWN_NUM)
        return getHivUnknown();
        else if(value==AppConstant.HIV_UNDISCLOSED_NUM)
        return getHivUndisclosed();
        else if(value==AppConstant.HIV_TEST_NOT_INDICATED_NUM)
        return getTestNotIndicatedHivStatus();
        else if(value==AppConstant.HIV_TEST_REQUIRED_NUM)
        return getTestRequiredHivStatus();
        else
        return getEmptyHivStatus();
    }
    public static HivStatus getHivStatus(String value)
    {
        if(value !=null)
        {
            if(value.equalsIgnoreCase("Positive"))
            return getHivPositive();
            else if(value.equalsIgnoreCase("Negative"))
            return getHivNegative();
            else if(value.equalsIgnoreCase("Unknown"))
            return getHivUnknown();
            else if(value.equalsIgnoreCase("Undisclosed"))
            return getHivUndisclosed();
        }
        return getEmptyHivStatus();
    }
    public static List getThreeMainHivStatus()
    {
        List hivStatusList=new ArrayList();
        hivStatusList.add(getEmptyHivStatus());
        hivStatusList.add(getHivUnknown());
        hivStatusList.add(getHivNegative());
        hivStatusList.add(getHivPositive());
        return hivStatusList;
    }
    public static List getThreeMainHivStatusWithTestNotIndicated()
    {
        List hivStatusList=new ArrayList();
        hivStatusList.add(getEmptyHivStatus());
        hivStatusList.add(getHivUnknown());
        hivStatusList.add(getHivNegative());
        hivStatusList.add(getHivPositive());
        hivStatusList.add(getTestNotIndicatedHivStatus());
        return hivStatusList;
    }
    public static List getThreeMainHivStatusWithUndisclosed()
    {
        List hivStatusList=new ArrayList();
        hivStatusList.add(getEmptyHivStatus());
        hivStatusList.add(getHivUnknown());
        hivStatusList.add(getHivNegative());
        hivStatusList.add(getHivPositive());
        hivStatusList.add(getHivUndisclosed());
        return hivStatusList;
    }
    public static List getHivStatusWithoutPositive()
    {
        List hivStatusList=new ArrayList();
        hivStatusList.add(getEmptyHivStatus());
        hivStatusList.add(getHivUnknown());
        hivStatusList.add(getHivNegative());
        hivStatusList.add(getHivPositive());
        return hivStatusList;
    }
    public static List getPositiveAndNegativeHivStatus()
    {
        List hivStatusList=new ArrayList();
        hivStatusList.add(getEmptyHivStatus());
        hivStatusList.add(getHivNegative());
        hivStatusList.add(getHivPositive());
        return hivStatusList;
    }
    public static List getAllHivStatus()
    {
        List hivStatusList=new ArrayList();
        hivStatusList.add(getEmptyHivStatus());
        hivStatusList.add(getHivUnknown());
        hivStatusList.add(getHivNegative());
        hivStatusList.add(getHivPositive());
        hivStatusList.add(getHivUndisclosed());
        hivStatusList.add(getTestNotIndicatedHivStatus());
        hivStatusList.add(getTestRequiredHivStatus());
        return hivStatusList;
    }
    public static List getAllHivStatusExceptPositive()
    {
        List hivStatusList=new ArrayList();
        hivStatusList.add(getEmptyHivStatus());
        hivStatusList.add(getHivUnknown());
        hivStatusList.add(getHivNegative());
        hivStatusList.add(getHivUndisclosed());
        hivStatusList.add(getTestNotIndicatedHivStatus());
        hivStatusList.add(getTestRequiredHivStatus());
        return hivStatusList;
    }
    public static void setHivStatusList(HttpSession session,List mainHivStatusList)
    {
        session.setAttribute("mainHivStatus", mainHivStatusList);
    }
    public static void setAllHivStatusList(HttpSession session)
    {
        session.setAttribute("allHivStatus", getAllHivStatus());
    }
    /*public static HivStatusManager getHivStatusManager(String beneficiaryId)
    {
        HivStatusManager hsm=null;
        try
        {
            DaoUtility util=new DaoUtility();
            hsm=util.getHivStatusManagerDaoInstance().getHivStatusManager(beneficiaryId);
            if(hsm==null)
            hsm=new HivStatusManager();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return hsm;
    }*/
}
