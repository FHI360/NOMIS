/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smomoh
 */
public class MaritalStatusManager implements Serializable
{
    public MaritalStatus getMaritalStatus(int value)
    {
        MaritalStatus maritalStatus=new MaritalStatus();
        maritalStatus.setValue(value);
        maritalStatus.setName(" ");
        if(value==AppConstant.MARITALSTATUS_SINGLE_NUM)
        {
            maritalStatus.setName("Single");
        }
        else if(value==AppConstant.MARITALSTATUS_SEPARATED_NUM)
        {
            maritalStatus.setName("Separated");
        }
        else if(value==AppConstant.MARITALSTATUS_MARRIED_NUM)
        {
            maritalStatus.setName("Married");
        }
        else if(value==AppConstant.MARITALSTATUS_WIDOWED_NUM)
        {
            maritalStatus.setName("Widowed");
        }
        else if(value==AppConstant.MARITALSTATUS_DIVORCED_NUM)
        {
            maritalStatus.setName("Divorced");
        }
        return maritalStatus;
    }
    public MaritalStatus getMaritalStatus(String name)
    {
        MaritalStatus maritalStatus=new MaritalStatus();
        maritalStatus.setName(name);
        if(name !=null)
        {
            name=name.trim();
            if(name.equalsIgnoreCase("Single"))
            {
                maritalStatus.setValue(AppConstant.MARITALSTATUS_SINGLE_NUM);
            }
            else if(name.equalsIgnoreCase("Separated"))
            {
                maritalStatus.setValue(AppConstant.MARITALSTATUS_SEPARATED_NUM);
                
            }
            else if(name.equalsIgnoreCase("Married"))
            {
                maritalStatus.setValue(AppConstant.MARITALSTATUS_MARRIED_NUM);
            }
            else if(name.equalsIgnoreCase("Widowed") || name.equalsIgnoreCase("Widow(er)"))
            {
                maritalStatus.setValue(AppConstant.MARITALSTATUS_WIDOWED_NUM);
            }
            else if(name.equalsIgnoreCase("Divorced"))
            {
                maritalStatus.setValue(AppConstant.MARITALSTATUS_DIVORCED_NUM);
            }
        }
        return maritalStatus;
    }
    public List getListOfMaritalStatus()
    {
        List list=new ArrayList();
        list.add(getMaritalStatus(AppConstant.MARITALSTATUS_SINGLE_NUM));
        list.add(getMaritalStatus(AppConstant.MARITALSTATUS_SEPARATED_NUM));
        list.add(getMaritalStatus(AppConstant.MARITALSTATUS_MARRIED_NUM));
        list.add(getMaritalStatus(AppConstant.MARITALSTATUS_WIDOWED_NUM));
        list.add(getMaritalStatus(AppConstant.MARITALSTATUS_DIVORCED_NUM));
        return list;
    }
    public void setMaritalStatusList(HttpSession session)
    {
        session.setAttribute("maritalStatusList", getListOfMaritalStatus());
    }
}
