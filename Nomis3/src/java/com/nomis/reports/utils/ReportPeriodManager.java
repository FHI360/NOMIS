/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

/**
 *
 * @author smomoh
 */
public class ReportPeriodManager 
{
    public static ReportPeriod getCurrentFinancialYear(int month,int year)
    {
        ReportPeriod fy=new ReportPeriod();
        if(month>0 && month <10)
        {
            fy.setStartMonth(10);
            fy.setStartYear(--year);
            fy.setEndMonth(9);
            fy.setEndYear(year);
        }
        else if(month>9)
        {
            fy.setStartMonth(10);
            fy.setStartYear(year);
            fy.setEndMonth(9);
            fy.setEndYear(year+1);
        }
        return fy;
    }
    public static ReportPeriod getStartOfFinancialYear(int month,int year)
    {
        ReportPeriod fy=new ReportPeriod();
        if(month>0 && month <10)
        {
            fy.setStartMonth(10);
            fy.setStartYear(--year);
            fy.setEndMonth(9);
            fy.setEndYear(year);
        }
        else if(month>9)
        {
            fy.setStartMonth(10);
            fy.setStartYear(year);
            fy.setEndMonth(9);
            fy.setEndYear(year+1);
        }
        return fy;
    }
}
