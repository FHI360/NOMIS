/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import com.nomis.ovc.business.Occupation;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smomoh
 */
public class OccupationManager 
{
    public Occupation getOccupation(int value)
    {
        Occupation Occupation=new Occupation();
        Occupation.setValue(value);
        Occupation.setName(" ");
        if(value==AppConstant.OCCUPATION_FORMALLYEMPLOYED_NUM)
        {
            Occupation.setName("Formally employed");
        }
        else if(value==AppConstant.OCCUPATION_INFORMALLYEMPLOYED_NUM)
        {
            Occupation.setName("Informally employed");
        }
        else if(value==AppConstant.OCCUPATION_SELFEMPLOYED_NUM)
        {
            Occupation.setName("Self employed");
        }
        else if(value==AppConstant.OCCUPATION_RETIREDPENSIONER_NUM)
        {
            Occupation.setName("Retired pensioner");
        }
        else if(value==AppConstant.OCCUPATION_RETIREDNONPENSIONER_NUM)
        {
            Occupation.setName("Retired non-pensioner");
        }
        else if(value==AppConstant.OCCUPATION_UNEMPLOYED_NUM)
        {
            Occupation.setName("Unemployed");
        }
        return Occupation;
    }
    public Occupation getOccupation(String name)
    {
        Occupation Occupation=new Occupation();
        Occupation.setName(name);
        if(name !=null)
        {
            name=name.trim();
            if(name.equalsIgnoreCase("Formally employed"))
            {
                Occupation.setValue(AppConstant.OCCUPATION_FORMALLYEMPLOYED_NUM);
            }
            else if(name.equalsIgnoreCase("Informally employed"))
            {
                Occupation.setValue(AppConstant.OCCUPATION_INFORMALLYEMPLOYED_NUM);
                
            }
            else if(name.equalsIgnoreCase("Self employed"))
            {
                Occupation.setValue(AppConstant.OCCUPATION_SELFEMPLOYED_NUM);
            }
            else if(name.equalsIgnoreCase("Retired pensioner"))
            {
                Occupation.setValue(AppConstant.OCCUPATION_RETIREDPENSIONER_NUM);
            }
            else if(name.equalsIgnoreCase("Retired non-pensioner"))
            {
                Occupation.setValue(AppConstant.OCCUPATION_RETIREDNONPENSIONER_NUM);
            }
            else if(name.equalsIgnoreCase("Unemployed"))
            {
                Occupation.setValue(AppConstant.OCCUPATION_UNEMPLOYED_NUM);
            }
        }
        return Occupation;
    }
    public List getListOfOccupation()
    {
        List list=new ArrayList();
        list.add(getOccupation(AppConstant.OCCUPATION_FORMALLYEMPLOYED_NUM));
        list.add(getOccupation(AppConstant.OCCUPATION_INFORMALLYEMPLOYED_NUM));
        list.add(getOccupation(AppConstant.OCCUPATION_SELFEMPLOYED_NUM));
        list.add(getOccupation(AppConstant.OCCUPATION_RETIREDPENSIONER_NUM));
        list.add(getOccupation(AppConstant.OCCUPATION_RETIREDNONPENSIONER_NUM));
        list.add(getOccupation(AppConstant.OCCUPATION_UNEMPLOYED_NUM));
        return list;
    }
    public void setOccupationList(HttpSession session)
    {
        session.setAttribute("occupationList", getListOfOccupation());
    }
}
