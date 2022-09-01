/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;
/**
 *
 * @author smomoh
 */
public class DateTest 
{
  
  public long getDifferenceInTime(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Time=0;
      try
      {
          SimpleDateFormat sdf
                = new SimpleDateFormat(
                    dateFormat);
          Date d1 = sdf.parse(startDate);
                Date d2 = sdf.parse(endDate);

                // Calucalte time difference
                // in milliseconds
                difference_In_Time= d2.getTime() - d1.getTime();
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return difference_In_Time;
  }
  public long getDifferenceInYears(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Years
                = TimeUnit
                      .MILLISECONDS
                      .toDays(getDifferenceInTime(startDate,endDate,dateFormat))
                  / 365;
      return difference_In_Years;
  }
public long getDifferenceInDays(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Days
                = TimeUnit
                      .MILLISECONDS
                      .toDays(getDifferenceInTime(startDate,endDate,dateFormat))
                  % 365;
                
      return difference_In_Days;
  }
  public long getDifferenceInHours(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Hours
                = TimeUnit
                      .MILLISECONDS
                      .toHours(getDifferenceInTime(startDate,endDate,dateFormat))
                  % 24;
      return difference_In_Hours;
  }
  public long getDifferenceInMinutes(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Minutes
                = TimeUnit
                      .MILLISECONDS
                      .toMinutes(getDifferenceInTime(startDate,endDate,dateFormat))
                  % 24;
      return difference_In_Minutes;
  }
  public long getDifferenceInSeconds(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Seconds
                = TimeUnit
                      .MILLISECONDS
                      .toSeconds(getDifferenceInTime(startDate,endDate,dateFormat))
                  % 24;
      return difference_In_Seconds;
  }
}
