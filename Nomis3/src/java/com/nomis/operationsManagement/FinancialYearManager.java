/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;


import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.DateParamenterTemplate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class FinancialYearManager 
{
    public Date getDATIMStartDate(Date date)
    {
        Date startDate=null;
        String strDate=DateManager.convertDateToString(date, DateManager.DB_DATE_FORMAT);
        if(strDate !=null && strDate.indexOf("-") !=-1)
        {
            String[] dateArray=strDate.split("-");
            if(dateArray !=null && dateArray.length>2)
            {
                int month=Integer.parseInt(dateArray[1]);
                int year=Integer.parseInt(dateArray[0]);
                int quarter=getQuarter(month);
                
                if(quarter==1)
                {
                    startDate=DateManager.getDateInstance(year+"-"+10+"-01");
                }
                else if(quarter==2)
                {
                    year=year-1;
                    startDate=DateManager.getDateInstance(year+"-"+10+"-01");
                }
                else if(quarter==3 || quarter==4)
                {
                    startDate=DateManager.getDateInstance(year+"-"+"04"+"-01");
                }  
            }
        }
        return startDate;
    }
    public Date getDATIMEndDate(Date date)
    {
        Date startDate=null;
        String strDate=DateManager.convertDateToString(date, DateManager.DB_DATE_FORMAT);
        if(strDate !=null && strDate.indexOf("-") !=-1)
        {
            String[] dateArray=strDate.split("-");
            if(dateArray !=null && dateArray.length>2)
            {
                int month=Integer.parseInt(dateArray[1]);
                int year=Integer.parseInt(dateArray[0]);
                int quarter=getQuarter(month);
                
                if(quarter==1)
                {
                    year=year+1;
                    startDate=DateManager.getDateInstance(year+"-03-31");
                }
                else if(quarter==2)
                {
                    startDate=DateManager.getDateInstance(year+"-03-31");
                }
                else if(quarter==3 || quarter==4)
                {
                    startDate=DateManager.getDateInstance(year+"-"+"09"+"-30");
                }  
            }
        }
        return startDate;
    }
    public String getStartDateOfPreviousQuarter(String date)
    {
        String startDateOfPreviousQuarter=null;
        if(date !=null)
        {
            String strDate=date;
            if(strDate.indexOf("-") ==-1)
            strDate=DateManager.convertDateToString(DateManager.getDateInstance(strDate), DateManager.DB_DATE_FORMAT);
            if(strDate.indexOf("-") !=-1)
            {
                String[] dateArray=strDate.split("-");
                if(dateArray.length>1)
                {
                    int month=Integer.parseInt(dateArray[1]);
                    int previousQuarter=getPreviousQuarter(month);
                    int year=Integer.parseInt(dateArray[0]);
                    startDateOfPreviousQuarter=getStartDateOfQuarter(previousQuarter,year);
                }
            }
        }
        return startDateOfPreviousQuarter;
    }
    public String getEndDateOfPreviousQuarter(String date)
    {
        String startDateOfPreviousQuarter=null;
        if(date !=null)
        {
            String strDate=date;
            if(strDate.indexOf("-") ==-1)
            strDate=DateManager.convertDateToString(DateManager.getDateInstance(strDate), DateManager.DB_DATE_FORMAT);
            if(strDate.indexOf("-") !=-1)
            {
                String[] dateArray=strDate.split("-");
                if(dateArray.length>1)
                {
                    int month=Integer.parseInt(dateArray[1]);
                    int previousQuarter=getPreviousQuarter(month);
                    int year=Integer.parseInt(dateArray[0]);
                    startDateOfPreviousQuarter=getEndDateOfQuarter(previousQuarter,year);
                }
            }
        }
        return startDateOfPreviousQuarter;
    }
    public String getStartDateOfQuarter(int quarter,int year)
    {
        String startDate=null;
        if(quarter>0)
        {
            
                //int quarter=getQuarter(month);
                if(quarter==1)
                {
                    year=year-1;
                    startDate=year+"-"+10+"-01";
                }
                else if(quarter==2)
                {
                    startDate=year+"-"+"01"+"-01";
                }
                else if(quarter==3)
                {
                    startDate=year+"-"+"04"+"-01";
                }
                else if(quarter==4)
                {
                    startDate=year+"-"+"07"+"-01";
                }
        }
        return startDate;
    }
    public String getEndDateOfQuarter(int quarter,int year)
    {
        String endDate=null;
        if(quarter>0)
        {
            
                //int quarter=getQuarter(month);
                if(quarter==1)
                {
                    year=year-1;
                    endDate=year+"-"+12+"-31";
                }
                else if(quarter==2)
                {
                    endDate=year+"-"+"03"+"-31";
                }
                else if(quarter==3)
                {
                    endDate=year+"-"+"06"+"-30";
                }
                else if(quarter==4)
                {
                    endDate=year+"-"+"09"+"-30";
                }
        }
        return endDate;
    }
    public String getStartDateOfQuarter(String date)
    {
        String startDate=null;
        if(date !=null && date.indexOf("-") !=-1)
        {
            String[] dateArray=date.split("-");
            if(dateArray !=null && dateArray.length>2)
            {
                int month=Integer.parseInt(dateArray[1]);
                int quarter=getQuarter(month);
                if(quarter==1)
                {
                    startDate=dateArray[0]+"-"+10+"-01";
                }
                else if(quarter==2)
                {
                    startDate=dateArray[0]+"-"+"01"+"-01";
                }
                else if(quarter==3)
                {
                    startDate=dateArray[0]+"-"+"04"+"-01";
                }
                else if(quarter==4)
                {
                    startDate=dateArray[0]+"-"+"07"+"-01";
                }
                
            }
        }
        return startDate;
    }
    public String getEndDateOfQuarter(String date)
    {
        String startDate=null;
        if(date !=null && date.indexOf("-") !=-1)
        {
            String[] dateArray=date.split("-");
            if(dateArray !=null && dateArray.length>2)
            {
                int month=Integer.parseInt(dateArray[1]);
                int quarter=getQuarter(month);
                if(quarter==1)
                {
                    startDate=dateArray[0]+"-"+12+"-31";
                }
                else if(quarter==2)
                {
                    startDate=dateArray[0]+"-"+"03"+"-31";
                }
                else if(quarter==3)
                {
                    startDate=dateArray[0]+"-"+"06"+"-30";
                }
                else if(quarter==4)
                {
                    startDate=dateArray[0]+"-"+"09"+"-30";
                }
                
            }
        }
        return startDate;
    }
    public Date getStartDateOfQuarter(Date date)
    {
        Date startDate=null;
        String strDate=DateManager.convertDateToString(date, DateManager.DB_DATE_FORMAT);
        if(strDate !=null && strDate.indexOf("-") !=-1)
        {
            String[] dateArray=strDate.split("-");
            if(dateArray !=null && dateArray.length>2)
            {
                int month=Integer.parseInt(dateArray[1]);
                int quarter=getQuarter(month);
                if(quarter==1)
                {
                    startDate=DateManager.getDateInstance(dateArray[0]+"-"+10+"-01");
                }
                else if(quarter==2)
                {
                    startDate=DateManager.getDateInstance(dateArray[0]+"-"+"01"+"-01");
                }
                else if(quarter==3)
                {
                    startDate=DateManager.getDateInstance(dateArray[0]+"-"+"04"+"-01");
                }
                else if(quarter==4)
                {
                    startDate=DateManager.getDateInstance(dateArray[0]+"-"+"07"+"-01");
                }  
            }
        }
        return startDate;
    }
    public Date getEndDateOfQuarter(Date date)
    {
        String strDate=DateManager.convertDateToString(date, DateManager.DB_DATE_FORMAT);
        Date endDate=null;
        if(strDate !=null && strDate.indexOf("-") !=-1)
        {
            String[] dateArray=strDate.split("-");
            if(dateArray !=null && dateArray.length>2)
            {
                int month=Integer.parseInt(dateArray[1]);
                int quarter=getQuarter(month);
                if(quarter==1)
                {
                    endDate=DateManager.getDateInstance(dateArray[0]+"-"+12+"-31");
                }
                else if(quarter==2)
                {
                    endDate=DateManager.getDateInstance(dateArray[0]+"-"+"03"+"-31");
                }
                else if(quarter==3)
                {
                    endDate=DateManager.getDateInstance(dateArray[0]+"-"+"06"+"-30");
                }
                else if(quarter==4)
                {
                    endDate=DateManager.getDateInstance(dateArray[0]+"-"+"09"+"-30");
                }  
            }
        }
        return endDate;
    }
    public String getReportQuarter(Date date)
    {
        String reportQuarter=null;
        if(date !=null)
        {
            String strdate=DateManager.convertDateToString(date, DateManager.DB_DATE_FORMAT);
            if(strdate !=null && strdate.indexOf("-") !=-1)
            {
                String[] strdateArray=strdate.split("-");
                if(strdateArray.length>2)
                {
                    int year=Integer.parseInt(strdateArray[0]);
                    int month=Integer.parseInt(strdateArray[1]);
                    int quarter=getQuarter(month);
                    reportQuarter=year+"Q"+quarter;
                }
            }
        }
        return reportQuarter;
    }
    public int getQuarter(int month)
    {
        int quarter=0;
        if(month>9 && month<13)
        quarter=1;
        else if(month>0 && month<4)
        quarter=2;
        else if(month>3 && month<7)
        quarter=3;
        else if(month>6 && month<10)
        quarter=4;
        return quarter;
    }
    public int getPreviousQuarter(int month)
    {
        int previousQuarter=0;
        if(month>9 && month<13)
        previousQuarter=4;
        else if(month>0 && month<4)
        previousQuarter=1;
        else if(month>3 && month<7)
        previousQuarter=2;
        else if(month>6 && month<10)
        previousQuarter=3;
        return previousQuarter;
    }
    public List getListOfDateParameterTemplatesForQuarterlyReport()
    {
        List list=new ArrayList();
        try
        {
            DaoUtility util=new DaoUtility();
            List yearList=util.getHouseholdEnrollmentDaoInstance().getDistinctYearOfAssessment();
            if(yearList !=null && !yearList.isEmpty())
            {
                String startOfQuarter1=null;
                String startOfQuarter2=null;
                String startOfQuarter3=null;
                String startOfQuarter4=null;
                String endOfQuarter1=null;
                String endOfQuarter2=null;
                String endOfQuarter3=null;
                String endOfQuarter4=null;
                String quarter1Display=null;
                String quarter2Display=null;
                String quarter3Display=null;
                String quarter4Display=null;
                
                int firstYearOfAssessment=Integer.parseInt(yearList.get(0).toString());
                String currentDate=DateManager.getCurrentDate();
                int currentYear=Integer.parseInt(currentDate.substring(0, 4));
                if(firstYearOfAssessment <= currentYear)
                {
                    DateParamenterTemplate dpt=new DateParamenterTemplate();
                    for(int i=currentYear; i>=firstYearOfAssessment; i--)
                    {
                        startOfQuarter2=i+"-01"+"-01";
                        startOfQuarter3=i+"-04"+"-01";
                        startOfQuarter4=i+"-07"+"-01";
                        startOfQuarter1=i+"-10"+"-01";
                        
                        endOfQuarter2=DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(startOfQuarter2)),DateManager.DB_DATE_FORMAT);
                        endOfQuarter3=DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(startOfQuarter3)),DateManager.DB_DATE_FORMAT);
                        endOfQuarter4=DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(startOfQuarter4)),DateManager.DB_DATE_FORMAT);
                        endOfQuarter1=DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(startOfQuarter1)),DateManager.DB_DATE_FORMAT);
                        
                        quarter1Display=getReportQuarter(DateManager.getDateInstance(startOfQuarter1));
                        quarter2Display=getReportQuarter(DateManager.getDateInstance(startOfQuarter2));
                        quarter3Display=getReportQuarter(DateManager.getDateInstance(startOfQuarter3));
                        quarter4Display=getReportQuarter(DateManager.getDateInstance(startOfQuarter4));
                        
                        dpt=new DateParamenterTemplate();
                        dpt.setDisplayName(quarter4Display);
                        dpt.setValue(startOfQuarter4+":"+endOfQuarter4);
                        list.add(dpt);
                        
                        dpt=new DateParamenterTemplate();
                        dpt.setDisplayName(quarter3Display);
                        dpt.setValue(startOfQuarter3+":"+endOfQuarter3);
                        list.add(dpt);
                        
                        dpt=new DateParamenterTemplate();
                        dpt.setDisplayName(quarter2Display);
                        dpt.setValue(startOfQuarter2+":"+endOfQuarter2);
                        list.add(dpt);
                        
                        dpt=new DateParamenterTemplate();
                        dpt.setDisplayName(quarter1Display);
                        dpt.setValue(startOfQuarter1+":"+endOfQuarter1);
                        list.add(dpt);
                        
                        System.err.println("Display value is "+dpt.getDisplayName()+" Value is "+dpt.getValue());
                    }
                }
            }
            /*System.err.println("date is "+date);
            System.err.println("getReportQuarter(DateManager.getDateInstance(date)) is "+getReportQuarter(DateManager.getDateInstance(date)));
            System.err.println("getStartDateOfQuarter(DateManager.getDateInstance(date)) is "+DateManager.convertDateToString(getStartDateOfQuarter(DateManager.getDateInstance(date)),DateManager.DB_DATE_FORMAT));
            System.err.println("getEndDateOfQuarter(DateManager.getDateInstance(date)) is "+DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(date)),DateManager.DB_DATE_FORMAT));
            */
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    public List getListOfDateParameterTemplatesForSemiAnnualReport()
    {
        List list=new ArrayList();
        try
        {
            DaoUtility util=new DaoUtility();
            List yearList=util.getHouseholdEnrollmentDaoInstance().getDistinctYearOfAssessment();
            if(yearList !=null && !yearList.isEmpty())
            {
                String startOfQuarter1=null;
                String startOfQuarter2=null;
                String startOfQuarter3=null;
                String startOfQuarter4=null;
                String endOfQuarter1=null;
                String endOfQuarter2=null;
                String endOfQuarter3=null;
                String endOfQuarter4=null;
                String quarter1Display=null;
                String quarter2Display=null;
                String quarter3Display=null;
                String quarter4Display=null;
                
                int firstYearOfAssessment=Integer.parseInt(yearList.get(0).toString());
                String currentDate=DateManager.getCurrentDate();
                int currentYear=Integer.parseInt(currentDate.substring(0, 4));
                if(firstYearOfAssessment <= currentYear)
                {
                    DateParamenterTemplate dpt=new DateParamenterTemplate();
                    for(int i=currentYear; i>=firstYearOfAssessment; i--)
                    {
                        startOfQuarter2=i+"-01"+"-01";
                        startOfQuarter3=i+"-04"+"-01";
                        startOfQuarter4=i+"-07"+"-01";
                        startOfQuarter1=(i-1)+"-10"+"-01";
                        
                        endOfQuarter2=DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(startOfQuarter2)),DateManager.DB_DATE_FORMAT);
                        endOfQuarter3=DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(startOfQuarter3)),DateManager.DB_DATE_FORMAT);
                        endOfQuarter4=DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(startOfQuarter4)),DateManager.DB_DATE_FORMAT);
                        endOfQuarter1=DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(startOfQuarter1)),DateManager.DB_DATE_FORMAT);
                        
                        quarter1Display=getReportQuarter(DateManager.getDateInstance(startOfQuarter1));
                        quarter2Display=getReportQuarter(DateManager.getDateInstance(startOfQuarter2));
                        quarter3Display=getReportQuarter(DateManager.getDateInstance(startOfQuarter3));
                        quarter4Display=getReportQuarter(DateManager.getDateInstance(startOfQuarter4));
                        
                        dpt=new DateParamenterTemplate();
                        dpt.setDisplayName("APR"+i);
                        dpt.setValue(startOfQuarter3+":"+endOfQuarter4);
                        list.add(dpt);
                        //System.err.println("Display value is "+dpt.getDisplayName()+" Value is "+dpt.getValue());
                                                
                        dpt=new DateParamenterTemplate();
                        dpt.setDisplayName("SAPR"+i);
                        dpt.setValue(startOfQuarter1+":"+endOfQuarter2);
                        list.add(dpt);
                        
                                                
                        //System.err.println("Display value is "+dpt.getDisplayName()+" Value is "+dpt.getValue());
                    }
                }
            }
            /*System.err.println("date is "+date);
            System.err.println("getReportQuarter(DateManager.getDateInstance(date)) is "+getReportQuarter(DateManager.getDateInstance(date)));
            System.err.println("getStartDateOfQuarter(DateManager.getDateInstance(date)) is "+DateManager.convertDateToString(getStartDateOfQuarter(DateManager.getDateInstance(date)),DateManager.DB_DATE_FORMAT));
            System.err.println("getEndDateOfQuarter(DateManager.getDateInstance(date)) is "+DateManager.convertDateToString(getEndDateOfQuarter(DateManager.getDateInstance(date)),DateManager.DB_DATE_FORMAT));
            */
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
}
