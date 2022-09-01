/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smomoh
 */
public class AppManager 
{
    public User getCurrentUser(HttpSession session)
    {
        User user=(User)session.getAttribute("USER");
        try
        {
        DaoUtility util=new DaoUtility();
        if(user !=null)
        {
            user=util.getUserDaoInstance().getUser(user.getUsername());
            //System.err.println("Username is "+user.getUsername());
        }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return user;
    }
    public String getCurrentUserName(HttpSession session)
    {
        String userName="test";
        User user=(User)session.getAttribute("USER");
        if(user !=null)
        {
            userName=user.getUsername();
            //System.err.println("Username is "+userName);
        }
        return userName;
    }
    
    public int getAgeInYears(int ageInMonths)
    {
        int ageInYears=0;
        if(ageInMonths>11)
        {
            ageInYears =Math.round(ageInMonths/12);
        }
        return ageInYears;
    }
    public static int getAgeAtBaseline(Date dateOfBirth,Date dateOfEnrollment)
    {
        String strDateOfBirth=DateManager.convertDateToString(dateOfBirth, DateManager.DB_DATE_FORMAT);
        String strDateOfEnrollment=DateManager.convertDateToString(dateOfEnrollment, DateManager.DB_DATE_FORMAT);
        int ageAtBaseline=getAgeAtBaseline(strDateOfBirth, strDateOfEnrollment);
        return ageAtBaseline;    
    }
    public static int getAgeAtBaseline(String dateOfBirth,String dateOfEnrollment) 
    {
        int ageAtBaseline=getAgeInYearsFromDates(dateOfBirth, dateOfEnrollment);
        return ageAtBaseline;
    }
    public static int getBaselineAgeUnit(String dateOfBirth,String dateOfEnrollment) 
    {
        int currentAgeUnit=1;
        int monthDifference=DateManager.getDateDifferenceInMonths(dateOfBirth, dateOfEnrollment);
        if(monthDifference>11)
        currentAgeUnit=2;
        return currentAgeUnit;
    }
    public AgeObject getAgeAtService(String dateOfEnrollment,String serviceDate,int ageAtEnrollment,int ageUnitAtEnrollment) 
    {
        AgeObject ageObj=new AgeObject();
        ageObj.setAge(ageAtEnrollment);
        ageObj.setAgeUnit(ageUnitAtEnrollment);
        
        if(serviceDate !=null && serviceDate.indexOf("-") !=-1)
        {
            int ageAtService=ageAtEnrollment;
            if(ageUnitAtEnrollment==AppConstant.AGEUNIT_MONTH_NUM)
            {
                int ageDifferenceInMonths=getAgeInMonthsFromDates(dateOfEnrollment,serviceDate);
                //System.err.println("ageDifferenceInMonths is "+ageDifferenceInMonths);
                int totalAgeInMonth=ageAtEnrollment+ageDifferenceInMonths;
                //System.err.println("totalAgeInMonth is "+totalAgeInMonth);
                ageObj.setAge(totalAgeInMonth);
                ageObj.setAgeUnit(AppConstant.AGEUNIT_MONTH_NUM);
                if(totalAgeInMonth >11)
                {
                    int ageInYears=getAgeInYears(totalAgeInMonth);
                    //System.err.println("ageInYears is "+ageInYears);
                    ageAtService=(int)ageInYears;
                    ageObj.setAge(ageInYears);
                    ageObj.setAgeUnit(AppConstant.AGEUNIT_YEAR_NUM);
                }
            }
            else
            {
                int ageDifferenceInYears=getAgeInYearsFromDates(dateOfEnrollment,serviceDate);
                ageAtService=ageDifferenceInYears+ageAtEnrollment;
                ageObj.setAge(ageAtService);
                ageObj.setAgeUnit(AppConstant.AGEUNIT_YEAR_NUM);
            }
        }
        //System.err.println("ageAtService is "+ageObj.getAge()+" age unit at service is "+ageObj.getAgeUnit());
        return ageObj;
    }
    public AgeObject getAgeAtService(Date dateOfEnrollment,Date serviceDate,int ageAtEnrollment,int ageUnitAtEnrollment) 
    {
        AgeObject ageObj=new AgeObject();
        ageObj.setAge(ageAtEnrollment);
        ageObj.setAgeUnit(ageUnitAtEnrollment);
        
        if(dateOfEnrollment !=null && serviceDate !=null)
        {
            String strDateOfEnrollment=DateManager.convertDateToString(dateOfEnrollment, DateManager.DB_DATE_FORMAT);
            String strServiceDate=DateManager.convertDateToString(serviceDate, DateManager.DB_DATE_FORMAT);
            ageObj=getAgeAtService(strDateOfEnrollment,strServiceDate,ageAtEnrollment,ageUnitAtEnrollment);
        }
        return ageObj;
    }
    public AgeObject getCurrentAge(Date dateOfEnrollment,int ageAtEnrollment,int ageUnitAtEnrollment) 
    {
        AgeObject ageObj=new AgeObject();
        ageObj.setAge(ageAtEnrollment);
        ageObj.setAgeUnit(ageUnitAtEnrollment);
        Date currentDate=DateManager.getCurrentDateInstance();
        if(dateOfEnrollment !=null && currentDate !=null)
        {
            String strDateOfEnrollment=DateManager.convertDateToString(dateOfEnrollment, DateManager.DB_DATE_FORMAT);
            String strCurrentDate=DateManager.convertDateToString(currentDate, DateManager.DB_DATE_FORMAT);
            ageObj=getAgeAtService(strDateOfEnrollment,strCurrentDate,ageAtEnrollment,ageUnitAtEnrollment);
        }
        return ageObj;
    }
    public AgeObject getCurrentAge(String dateOfEnrollment,int ageAtEnrollment,int ageUnitAtEnrollment) 
    {
        AgeObject ageObj=new AgeObject();
        ageObj.setAge(ageAtEnrollment);
        ageObj.setAgeUnit(ageUnitAtEnrollment);
        String currentDate=DateManager.getCurrentDate();
        if((dateOfEnrollment !=null && dateOfEnrollment.indexOf("-") !=-1) && (currentDate !=null && currentDate.indexOf("-") !=-1))
        {
            ageObj=getAgeAtService(dateOfEnrollment,currentDate,ageAtEnrollment,ageUnitAtEnrollment);
        }
        return ageObj;
    }
    public static int getCurrentAge(String dateOfEnrollment,int ageAtEnrollment) 
    {
        int currentAge=ageAtEnrollment;
        if(dateOfEnrollment !=null && dateOfEnrollment.indexOf("-") !=-1)
        {
            String currentDate=DateManager.getCurrentDate();
            int ageDifference=getAgeInYearsFromDates(dateOfEnrollment, currentDate);
            currentAge=ageDifference+ageAtEnrollment;
        }
        //System.err.println("currentAge is "+currentAge);
        return currentAge;
    }
    public static int getCurrentAge(String dateOfBirth) 
    {
        String currentDate=DateManager.getCurrentDate();
        int currentAge=getAgeInYearsFromDates(dateOfBirth, currentDate);
        return currentAge;
    }
    public static int getCurrentAgeUnit(String dateOfBirth) 
    {
        int currentAgeUnit=1;
        String currentDate=DateManager.getCurrentDate();
        int monthDifference=DateManager.getDateDifferenceInMonths(dateOfBirth, currentDate);
        if(monthDifference>11)
        currentAgeUnit=2;
        return currentAgeUnit;
    }
    public static int getAgeInMonthsFromDates(String startDate, String endDate)
    {
        int ageInMonths=-1;
        if(startDate !=null && endDate !=null)
        {
            int monthDifference=DateManager.getDateDifferenceInMonths(startDate, endDate);
            ageInMonths=monthDifference;
        }
        return ageInMonths;
    }
    public static int getAgeInYearsFromDates(String startDate, String endDate)
    {
        int ageInYears=-1;
        if(startDate !=null && endDate !=null)
        {
            int monthDifference=DateManager.getDateDifferenceInMonths(startDate, endDate);
            double ageDifferenceInYears=Math.floor((monthDifference/12d));
            ageInYears=(int)ageDifferenceInYears;
        }
        return ageInYears;
    }
    public static int getAgeInYearsFromDates(Date startDate, Date endDate)
    {
        int age=-1;
        if(startDate !=null && endDate !=null)
        {
            String strStartDate=DateManager.convertDateToString(startDate, DateManager.DB_DATE_FORMAT);
            String strEndDate=DateManager.convertDateToString(endDate, DateManager.DB_DATE_FORMAT);
            int monthDifference=DateManager.getDateDifferenceInMonths(strStartDate, strEndDate);
            //System.err.println("strStartDate is "+strStartDate+" and strEndDate is "+strEndDate);
            //System.err.println("monthDifference is "+monthDifference);
            
            double dage=Math.floor((monthDifference/12d));
            age=(int)dage;
        }
        return age;
    }
    public static int getCurrentAgeUnitFromDates(Date startDate, Date endDate) 
    {
        int currentAgeUnit=1;
        String strStartDate=DateManager.convertDateToString(startDate, DateManager.DB_DATE_FORMAT);
        String strEndDate=DateManager.convertDateToString(endDate, DateManager.DB_DATE_FORMAT);
        int monthDifference=DateManager.getDateDifferenceInMonths(strStartDate, strEndDate);
        if(monthDifference>11)
        currentAgeUnit=2;
        return currentAgeUnit;
    }
}
