/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import com.fhi.nomis.logs.NomisLogManager;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author smomoh
 */
public class DateManager implements Serializable
{
    public static String DB_DATE_FORMAT="yyyy-MM-dd";
    public static String DD_MM_YYYY_SLASH="dd/MM/yyyy";
    public static String MM_DD_YYYY_SLASH="MM/dd/yyyy";
    public static final String DEFAULT_DATE="1900-01-01";
    public static boolean isDueDate() 
    {
        boolean dueDate=false;
        Date currentDate=DateManager.getCurrentDateInstance();
        //Date testCurrentDate=DateManager.getDateInstance("2019-04-01");
        Date monitorDate=DateManager.getDateInstance("2019-09-30");
        if(currentDate.after(monitorDate))
        dueDate=true;
        return dueDate;
    }
    public String formatDate(Date date,String format)
    {
        String formatedDate=null;
        if(date !=null)
        {
            formatedDate=new SimpleDateFormat(format).format(date);
        }
        return formatedDate;
    }
    public static Date getDateInstanceFromMthDayYearFormat(String mthDayYear)
    {
        Date yyyymmdd=null;
        try
        {
            if(mthDayYear !=null && mthDayYear.indexOf("/") !=-1)
            {
                String[] mthDayYearArray=mthDayYear.split("/");
                if(mthDayYearArray !=null && mthDayYearArray.length==3)
                {
                    int mth=Integer.parseInt(mthDayYearArray[0]);
                    if(mth <13)
                    yyyymmdd=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(mthDayYear));
                }
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex); 
        }
        return yyyymmdd;
    }
    public static String getMthDayYearStringDateFormat(Date date,int showDefaultDate)
    {
        String mthDayYear=null;
        if(date !=null)
        mthDayYear=convertDateToString(date,DateManager.DB_DATE_FORMAT);
        if(mthDayYear !=null && mthDayYear.equalsIgnoreCase(DateManager.DEFAULT_DATE) && showDefaultDate==0)
        mthDayYear=null;
        else
        mthDayYear=DateManager.getMthDayYearFromMySqlDate(mthDayYear);
        return mthDayYear;
    }
    public int getDayFromDate(Date date) 
    {
        int day=0;
        if(date !=null)
        {
            String strDate=DateManager.convertDateToString(date,DateManager.DB_DATE_FORMAT);
            String[] dateArray=strDate.split("-");
           if(dateArray.length>2)
           {
              day=Integer.parseInt(dateArray[2]);
           }
        }
        return day;
    }

    public int getMonthFromDate(Date date) 
    {
        int month=0;
        if(date !=null)
        {
            String strDate=DateManager.convertDateToString(date,DateManager.DB_DATE_FORMAT);
            String[] dateArray=strDate.split("-");
           if(dateArray.length>2)
           {
              month=Integer.parseInt(dateArray[1]);
           }
        }
        return month;
    }

    public int getYearFromDate(Date date) 
    {
        int year=0;
        if(date !=null)
        {
            String strDate=DateManager.convertDateToString(date,DateManager.DB_DATE_FORMAT);
            String[] dateArray=strDate.split("-");
           if(dateArray.length>2)
           {
              year=Integer.parseInt(dateArray[0]);
           }
        }
        return year;
    }
    public DateObject getDateObject(Date date) 
    {
        String strdate=DateManager.convertDateToString(date, DB_DATE_FORMAT);
        DateObject dateObj=new DateObject();
        String[] dateArray=null;
        if(strdate !=null)
        {
            if(strdate.indexOf("-") !=-1)
            {
                dateArray=strdate.split("-");
                if(dateArray.length==3)
                {
                    dateObj.setDay(Integer.parseInt(dateArray[2]));
                    dateObj.setMonth(Integer.parseInt(dateArray[1]));
                    dateObj.setYear(Integer.parseInt(dateArray[0]));
                }
            }
        }
        return dateObj;
    }
    public static String getDatabaseDate(int day,int month, int year) 
    {
        String date=null;
        String strYear=year+"";
        if(day >0 && (month>0 && month<13) && strYear.trim().length()==4)
        {
            String strDay=day+"";
            String strMth=month+"";
            
            if(strDay.trim().length()==1)
            strDay="0"+day;
            if(strMth.trim().length()==1)
            strMth="0"+month;
            if(strYear.trim().length()==2)
            strYear="00"+year;
            date=strYear+"-"+strMth+"-"+strDay;
        }
        return date; 
    }
    public static String getDefaultCurrentDateAndTime() 
    {
        String dateAndTime=null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateAndTime=dateFormat.format(date);
        return dateAndTime; 
    }
    public static int getDateDifference(int basemth, int baseyear, int currentMonth, int currentYear)
    {
        Calendar calendar = Calendar.getInstance();
        if(currentMonth==0 || currentYear==0)
        {
            currentYear=calendar.get(Calendar.YEAR);
            currentMonth=calendar.get(Calendar.MONTH)+1;
        }
        //System.err.println("currentMonth:currentYear "+currentMonth+":"+currentYear);
        int yearDiff=currentYear-baseyear;
        int mthDiff=0;
        if(yearDiff ==0 && (currentMonth >= basemth))
        mthDiff=currentMonth-basemth;
        else if(yearDiff==1)
        mthDiff=currentMonth+(12-basemth);
        else if(yearDiff>1)
        mthDiff=(currentMonth+(12-basemth))+((yearDiff-1)*12);
        return mthDiff;
    }
    public static int getDateDifferenceInMonths(String startDate,String endDate)
    {
        int monthDiff=0;
        if(startDate !=null && endDate !=null)
        {
            if(startDate.indexOf("-") !=-1 && endDate.indexOf("-") !=-1)
            {
                String[] startDateArray=startDate.split("-");
                int startMth=Integer.parseInt(startDateArray[1]);
                int startYr=Integer.parseInt(startDateArray[0]);
                
                String[] endDateArray=endDate.split("-");
                int endMth=Integer.parseInt(endDateArray[1]);
                int endYr=Integer.parseInt(endDateArray[0]);
                monthDiff=getDateDifference(startMth, startYr, endMth, endYr);
                //System.err.println("monthDiff is "+monthDiff);
            }
            else
            monthDiff=-1;
        }
        else
        monthDiff=-1;
        return monthDiff;
    }
    public static Date getNewDateInstance()
    {
        Date date=new Date();
        return date;
    }
    
    public boolean isValidDate(String mysqlDate)
    {
        boolean validDate=false;
        if(mysqlDate !=null && mysqlDate.indexOf("-") !=-1)
        {
            String[] dateArray=mysqlDate.split("-");
            if(dateArray.length==3)
            {
                if(dateArray[0].length()==4 && isNumberGreaterThanZero(dateArray[0]))
                {
                    if(dateArray[1].length()<3 && dateArray[2].length()<3)
                    {
                        if(isNumberGreaterThanZero(dateArray[1]) && isNumberGreaterThanZero(dateArray[2]))
                        {
                            int day=Integer.parseInt(dateArray[2]);
                            int mth=Integer.parseInt(dateArray[1]);
                            if(mth<13)
                            {
                                if(mth==2 && day<30)
                                {
                                    validDate=true;
                                }
                                else if(mth==4 || mth==6 || mth==9 || mth==11)
                                {
                                    if(day <31)
                                    validDate=true;
                                }
                                else if(mth==1 || mth==3 || mth==5 || mth==7 || mth==8 || mth==10 || mth==12)
                                {
                                    if(day <32)
                                    validDate=true;
                                }
                            }
                        }
                    }
                }
            }
            
        }
        return validDate;
    }
    public static String convertDateToString(Date date,String format)
    {
        String formattedDate=null;
        if(date !=null)
        {
            if(format ==null)
            format=DB_DATE_FORMAT;
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            formattedDate=sdf.format(date);
        }
        return formattedDate;
    }
    public static Date getDateInstance(String datestr)
    {
        Date date=new Date();
        try
        {
            if(datestr !=null && datestr.indexOf("-") !=-1)
            date=new SimpleDateFormat("yyyy-MM-dd").parse((datestr));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return date;
    }
    public static Date getDefaultStartDateInstance()
    {
        Date date=new Date();
        try
        {
            date=new SimpleDateFormat("yyyy-MM-dd").parse((DEFAULT_DATE));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return date;
    }
    public static String getEmptyStringValueForDefaultStartDateInstance(Date date)
    {
        String emptyDateStringValue=" ";
        if(date !=null)
        {
            try
            {
                if(date.equals(getDefaultStartDateInstance()))
                emptyDateStringValue=" ";
                else
                {
                   emptyDateStringValue=date.toString();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return emptyDateStringValue;
    }
    public static Date getNullDateValueForDefaultStartDateInstance(Date date)
    {
        Date nullDateValue=null;
        if(date !=null)
        {
            try
            {
                if(!date.equals(getDefaultStartDateInstance()))
                nullDateValue=date;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return nullDateValue;
    }
    public boolean isNumberGreaterThanZero(String input)
    {
        boolean numberGreaterThanZero=false;
        String strNum=removeCharactersFromNumbers(input);
        try
        {
            //System.err.println("Serial number is "+strNum);
            if(strNum !=null)
            {
                int number=Integer.parseInt(strNum);
                //System.err.println(" number is "+number);
                if(number > 0)
                numberGreaterThanZero=true;
            }
        }
        catch(NumberFormatException nex)
        {
            System.err.println("A NumberFormatException has occured: "+nex.getMessage());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return numberGreaterThanZero;
    }
    public String removeCharactersFromNumbers(String input)
    {
        //System.err.println("input is "+input);
        String strValue=new String();
        if(input !=null)
        {
            for(int i=0; i<input.length(); i++)
            {
                
                if(!Character.isDigit(input.charAt(i)))
                {
                    //System.err.println(input.charAt(i)+" is not a number ");
                    continue;
                }
                strValue+=input.charAt(i);
            }
        }
        //System.err.println("strValue is "+strValue.trim());
        if(strValue.length()<1)
        return null;
        return strValue.trim();
    }
    public static Date getCurrentDateInstance()
    {
        Date date=new Date();
        try
        {
           date=new SimpleDateFormat("yyyy-MM-dd").parse(getCurrentDate()); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return date;
    }
    public static String getCurrentDate()
    {
        String dateFormat="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
        String currentDate=sdf.format(new java.util.Date());
        return currentDate;
    }
    public static String getNextMonth(Date date,int diff)
    {
        String strdate=DateManager.convertDateToString(date, DB_DATE_FORMAT);
        return getNextMonth(strdate,diff);       
    }
    public static String getNextMonth(String date,int diff)
    {
        System.err.println("date is "+date);
        String strdate=null;
        if(date !=null && date.indexOf("-") !=-1)
        {
            String[] strDateArray=date.split("-");
            if(strDateArray !=null && strDateArray.length>2)
            {
                int year=Integer.parseInt(strDateArray[0]);
                int mth=Integer.parseInt(strDateArray[1]);
                int day=Integer.parseInt(strDateArray[2]);
                strdate=getNextMonth(year,mth,day,diff);   
            }
        }
        return strdate;
    }
    private static int getDayToCalculateNextMoth(int currentMonth)
    {
        int day=0;
        if(currentMonth==1)
        day=28;
        else if(currentMonth==2)
        day=31;
        else if(currentMonth==3)
        day=30;
        else if(currentMonth==4)
        day=31;
        else if(currentMonth==5)
        day=30;
        else if(currentMonth==6)
        day=31;
        else if(currentMonth==7)
        day=31;
        else if(currentMonth==8)
        day=30;
        else if(currentMonth==9)
        day=31;
        else if(currentMonth==10)
        day=30;
        else if(currentMonth==11)
        day=31;
        else if(currentMonth==12)
        day=31;
        return day;
    }
    public static String getNextMonth(int year,int mth,int day,int diff)
    {
        day=getDayToCalculateNextMoth(mth);
        String dateFormat="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, mth);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.add(mth, diff);
        return sdf.format(c.getTime());
        
    }
    /*public static String getNextMonth(int year,int mth,int day,int diff)
    {
        String dateFormat="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, mth);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.add(mth, diff);
        return sdf.format(c.getTime());
        
    }*/
    public String addDaysToDate(String dateFormat,String initialDate,int numberOfDaysToAdd)
    {
        String newDate=initialDate;
        try
        {
            if(dateFormat==null)
            dateFormat=DateManager.DB_DATE_FORMAT;
            SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
            Calendar c=Calendar.getInstance();
            c.setTime(sdf.parse(initialDate));
            c.add(Calendar.DAY_OF_MONTH,numberOfDaysToAdd);
            newDate=sdf.format(c.getTime());
            //System.err.println("initialDate is "+initialDate+" and newDate is "+newDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return newDate;
    }
    public String addMonthToDate(String dateFormat,String initialDate,int numberOfMonthsToAdd)
    {
        String newDate=initialDate;
        try
        {
            if(dateFormat==null)
            dateFormat=DateManager.DB_DATE_FORMAT;
            SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
            Calendar c=Calendar.getInstance();
            c.setTime(sdf.parse(initialDate));
            c.add(Calendar.MONTH,numberOfMonthsToAdd);
            newDate=sdf.format(c.getTime());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return newDate;
    }
    public String addYearToDate(String dateFormat,String initialDate,int numberOfYearsToAdd)
    {
        String newDate=initialDate;
        try
        {
            if(dateFormat==null)
            dateFormat=DateManager.DB_DATE_FORMAT;
            SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
            Calendar c=Calendar.getInstance();
            c.setTime(sdf.parse(initialDate));
            c.add(Calendar.YEAR,numberOfYearsToAdd);
            newDate=sdf.format(c.getTime());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return newDate;
    }
    public String getStartOfNextMonthAfterMonthAdd(String dateFormat,String initialDate,int numberOfMonthsToAdd)
    {
        String newDate=initialDate;
        try
        {
            newDate=addMonthToDate(dateFormat,initialDate,numberOfMonthsToAdd);
            newDate=addDaysToDate(dateFormat,newDate,1);
            //System.err.println("initialDate is "+initialDate+" and newDate + 1 day after adding month(s) is "+newDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return newDate;
    }
    public String getPreviousDate(int year,int mth,int day,int diff)
    {
        String dateFormat="yyyy/MM/dd  hh:mm:ss";
      SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
	Calendar c=new GregorianCalendar(year,mth,day);
	c.add(Calendar.MONTH,diff);
        String previousMonth=sdf.format(c.getTime());
        return previousMonth;
    }
    public static Date getPreviousDate(Date date,int yearDiff)
    {
        Date newDate=null;
        yearDiff=-yearDiff;
        if(date !=null)
        {
            String strdate=convertDateToString(date, DB_DATE_FORMAT);
            if(strdate !=null)
            {
                String[] dateArray=strdate.split("-");
                if(dateArray !=null && dateArray.length>2)
                {
                    int day=Integer.parseInt(dateArray[2]);
                    int mth=Integer.parseInt(dateArray[1]);
                    mth=mth-1;
                    int year=Integer.parseInt(dateArray[0]);
                    //String dateFormat=DEFAULT_DATE;
                    SimpleDateFormat sdf=new SimpleDateFormat(DB_DATE_FORMAT);
                    Calendar c=new GregorianCalendar(year,mth,day);
                    c.add(Calendar.YEAR,yearDiff);
                    String strNewDate=sdf.format(c.getTime());
                    //System.err.println("strNewDate is "+strNewDate);
                    newDate=DateManager.getDateInstance(strNewDate);
                }
            }
        }
        return newDate;
    }
    public String getCurrentMthAndDate()
    {
        String currentDate=getCurrentDate();
        String[] dateArray=currentDate.split("-");
        String month=getFullMonthAsString(Integer.parseInt(dateArray[1]));
        String today=month+" "+dateArray[2]+", "+dateArray[0];
        return today;
    }
    public static String getMthDayYearFromMySqlDate(String mysqlDate)
    {
        String displayDate=null;
        if(mysqlDate !=null && mysqlDate.indexOf("-") !=-1)
        {
            String[] dateArray=mysqlDate.split("-");
            displayDate=dateArray[1]+"/"+dateArray[2]+"/"+dateArray[0];
        }
        return displayDate;
    }
    public String monthDayYear(String mysqlDate)
    {
        String displayDate=null;
        if(mysqlDate !=null && mysqlDate.indexOf("-") !=-1)
        {
            String[] dateArray=mysqlDate.split("-");
            displayDate=dateArray[1]+"/"+dateArray[2]+"/"+dateArray[0];
        }
        return displayDate;
    }
    public String convertMysqlDateTomonthDayYear(String mysqlDate)
    {
        String displayDate=null;
        if(mysqlDate !=null && mysqlDate.indexOf("-") !=-1)
        {
            String[] dateArray=mysqlDate.split("-");
            displayDate=dateArray[1]+"/"+dateArray[2]+"/"+dateArray[0];
        }
        return displayDate;
    }
    public static String processMthDayYearToMysqlFormat(String date)
    {
        String[] dateArray=null;
        String mysqlDate=date;
        if(date !=null && !date.equalsIgnoreCase("All"))
        {
            if(date.indexOf("/") !=-1)
            {
                dateArray=date.split("/");
                if(dateArray !=null && dateArray.length>2)
                {
                    String day=dateArray[1];
                    String mth=dateArray[0];
                    if(day.length()==1)
                    day="0"+day;
                    if(mth.length()==1)
                    mth="0"+mth;
                    mysqlDate=getMySqlDate(dateArray[1],dateArray[0],dateArray[2]);
                }
            }
        }
        if(mysqlDate !=null && (mysqlDate.indexOf("-") ==-1))
        mysqlDate=null;
        return mysqlDate;
    }
    public long monthsBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long monthsBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.MONTH, 1);
            monthsBetween++;
        }
        return monthsBetween;
    }
    public static String getMySqlDate(String day,String month,String year)
    {
        if(day.length()==1)
        day="0"+day;
        if(month.length()==1)
        month="0"+month;
        String mysqlDate=year+"-"+month+"-"+day;
        return mysqlDate;
    }
    public String getServiceDate(String mysqlDate)
    {
        String[] dateParts=mysqlDate.split("-");
        String month=getMonthAsString(Integer.parseInt(dateParts[1]));
        String displayDate=month+" "+dateParts[0];
        return displayDate;
    }
    public String getDayMthYearFromMysqlDate(String mysqlDate)
    {
        String displayDate=null;
        if(mysqlDate !=null && mysqlDate.indexOf("-") !=-1)
        {
            String[] dateParts=mysqlDate.split("-");
            displayDate=dateParts[2]+"/"+dateParts[1]+"/"+dateParts[0];
        }
        return displayDate;
    }
    public String getFullMonthAsString(int mth)
    {
        String month=null;
        if(mth==1 || mth==01)
        month="January";
        else if(mth==2 || mth==02)
        month="February";
        else if(mth==3 || mth==03)
        month="March";
        else if(mth==4 || mth==04)
        month="April";
        else if(mth==5 || mth==05)
        month="May";
        else if(mth==6 || mth==06)
        month="June";
        else if(mth==7 || mth==07)
        month="July";
        else if(mth==8)
        month="August";
        else if(mth==9)
        month="September";
        else if(mth==10)
        month="October";
        else if(mth==11)
        month="November";
        else if(mth==12)
        month="December";
        return month;
    }
    public String getMonthAsStringFromMMddYYYY(String MMddYYYYDateFormat)
    {
        String mth="";
        if(MMddYYYYDateFormat !=null && MMddYYYYDateFormat.indexOf("/") !=-1)
        {
            String[] dateArray=MMddYYYYDateFormat.split("/");
            if(dateArray.length>0)
            mth=getMonthAsString(Integer.parseInt(dateArray[0]));
        }
        return mth;
    }
    public String getMonthAsStringFromddMMYYYY(String ddMMYYYYDateFormat)
    {
        String mth="";
        if(ddMMYYYYDateFormat !=null && ddMMYYYYDateFormat.indexOf("-") !=-1)
        {
            String[] dateArray=ddMMYYYYDateFormat.split("/");
            if(dateArray.length>0)
            mth=getMonthAsString(Integer.parseInt(dateArray[1]));
        }
        return mth;
    }
    public String getMonthAsString(int mth)
    {
        String month=null;
        if(mth==1 || mth==01)
        month="Jan";
        else if(mth==2 || mth==02)
        month="Feb";
        else if(mth==3 || mth==03)
        month="Mar";
        else if(mth==4 || mth==04)
        month="Apr";
        else if(mth==5 || mth==05)
        month="May";
        else if(mth==6 || mth==06)
        month="Jun";
        else if(mth==7 || mth==07)
        month="Jul";
        else if(mth==8)
        month="Aug";
        else if(mth==9)
        month="Sep";
        else if(mth==10)
        month="Oct";
        else if(mth==11)
        month="Nov";
        else if(mth==12)
        month="Dec";
        return month;
    }
    public String getMonthAsNumber(String mth)
    {
        String month=null;
        if(mth !=null)
        {
            if(mth.equalsIgnoreCase("Jan") || mth.equalsIgnoreCase("January"))
            month="01";
            else if(mth.equalsIgnoreCase("Feb") || mth.equalsIgnoreCase("February"))
            month="02";
            else if(mth.equalsIgnoreCase("Mar") || mth.equalsIgnoreCase("March"))
            month="03";
            else if(mth.equalsIgnoreCase("Apr") || mth.equalsIgnoreCase("April"))
            month="04";
            else if(mth.equalsIgnoreCase("May"))
            month="05";
            else if(mth.equalsIgnoreCase("Jun") || mth.equalsIgnoreCase("June"))
            month="06";
            else if(mth.equalsIgnoreCase("Jul") || mth.equalsIgnoreCase("July"))
            month="07";
            else if(mth.equalsIgnoreCase("Aug") || mth.equalsIgnoreCase("August"))
            month="08";
            else if(mth.equalsIgnoreCase("Sep") || mth.equalsIgnoreCase("September"))
            month="09";
            else if(mth.equalsIgnoreCase("Oct") || mth.equalsIgnoreCase("October"))
            month="10";
            else if(mth.equalsIgnoreCase("Nov") || mth.equalsIgnoreCase("November"))
            month="11";
            else if(mth.equalsIgnoreCase("Dec") || mth.equalsIgnoreCase("December"))
            month="12";
        }
        return month;
    }
    public List generatDate(int startYear)
    {
        List listOfDates=new ArrayList();
        for(int i=startYear; i<=2030; i++)
        {
            listOfDates.add(i);
        }
        return listOfDates;
    }
    public List generateMonths(int startMonth)
    {
        List listOfMonths=new ArrayList();
        String[] months={"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(int i=startMonth-1; i<months.length; i++)
        {
            listOfMonths.add(months[i]);
        }
        return listOfMonths;
    }
    public static List generateDays(int month)
    {
        int limit=31;
        if(month==2)
        limit=29;
        else if(month==4 || month==6 || month==9 || month==11)
        limit=30;
        else
        limit=32;
        int[] dayArray={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};
        List dayList=new ArrayList();
        for(int i=1; i<(limit+1); i++)
        {
            if(i==dayArray.length)
            break;
            dayList.add(dayArray[i-1]);
        }
        return dayList;
    }
    public static List generateSingleValueMonths()
    {
        Month month=null;
        String[] monthArray={"1","January","2","February","3","March","4","April","5","May","6","June","7","July","8","August","9","September","10","October","11","November","12","December"};
        List monthList=new ArrayList();
        for(int i=0; i<monthArray.length; i+=2)
        {
            month=new Month();
            month.setValue(monthArray[i]);
            month.setName(monthArray[i+1]);
            monthList.add(month);
        }
        return monthList;
    }
    public static List generateMonths()
    {
        Month month=null;
        String[] monthArray={"01","January","02","February","03","March","04","April","05","May","06","June","07","July","08","August","09","September","10","October","11","November","12","December"};
        List monthList=new ArrayList();
        for(int i=0; i<monthArray.length; i+=2)
        {
            month=new Month();
            month.setValue(monthArray[i]);
            month.setName(monthArray[i+1]);
            monthList.add(month);
        }
        return monthList;
    }
    public static int getYear(Date date)
    {
        int year=0;
        if(date !=null)
        {
            String currentDate=DateManager.convertDateToString(date, DB_DATE_FORMAT);
            if(currentDate.indexOf("-") !=-1)
            {
                String[] currentDateArray=currentDate.split("-");
                year=Integer.parseInt(currentDateArray[0]);
            }
        }
        return year;
    }
    public static int getMonth(Date date)
    {
        int month=0;
        if(date !=null)
        {
            String currentDate=DateManager.convertDateToString(date, DB_DATE_FORMAT);
            if(currentDate.indexOf("-") !=-1)
            {
                String[] currentDateArray=currentDate.split("-");
                month=Integer.parseInt(currentDateArray[1]);
            }
        }
        return month;
    }
    public static List generateYears()
    {
         List yearList=new ArrayList();
         int startYear=1900;
         int endYear=getYear(DateManager.getCurrentDateInstance());
         for(int i=endYear; i>startYear-1; i--)
         {
             yearList.add(i);
         }
         return yearList;
    }
    /*public static List generateYears(int firstYr,int lastYr)
    {
         List yearList=new ArrayList();
         for(int i=lastYr; i>firstYr-1; i--)
         {
             yearList.add(i);
         }
         return yearList;
    }*/
/*public static boolean firstDateBeforeSecondDate(Date firstDate, Date secondDate)
{
    //System.err.println("First date is "+firstDate+" and second date "+secondDate);
    boolean firstDateBeforeSecondDate=false;
    if(firstDate !=null && secondDate !=null)
    {
        //System.err.println("First and second dates are not null");
        if(firstDate.before(secondDate) || firstDate.equals(secondDate))
        {
            firstDateBeforeSecondDate=true;
            //System.err.println("First date before second date");
        }
    }
    return firstDateBeforeSecondDate;
}*/
public static boolean isFirstDateBeforeOrEqualToSecondDate(Date firstDate, Date secondDate)
{
    //System.err.println("First date is "+firstDate+" and second date "+secondDate);
    boolean firstDateBeforeSecondDate=false;
    if(firstDate !=null && secondDate !=null)
    {
        //System.err.println("First and second dates are not null");
        if(firstDate.before(secondDate) || firstDate.equals(secondDate))
        {
            firstDateBeforeSecondDate=true;
            //System.err.println("First date before second date");
        }
    }
    return firstDateBeforeSecondDate;
}
public static boolean compareDates(Date firstDate, Date secondDate)
{
    String strfirstDate=DateManager.convertDateToString(firstDate, DB_DATE_FORMAT);
    String strsecondDate=DateManager.convertDateToString(secondDate, DB_DATE_FORMAT);
    String[] firstDateArray;
    String[] secondDateArray;
    boolean dateFlag=false;
    if(firstDate !=null && secondDate !=null)
    {
        if(strfirstDate.indexOf("/") !=-1)
        strfirstDate=DateManager.processMthDayYearToMysqlFormat(strfirstDate);
        if(strsecondDate.indexOf("/") !=-1)
        strsecondDate=DateManager.processMthDayYearToMysqlFormat(strsecondDate);
        firstDateArray=strfirstDate.split("-");
        secondDateArray=strsecondDate.split("-");
        if(firstDateArray.length !=3 || secondDateArray.length !=3)
        return false;
        if(strfirstDate.equalsIgnoreCase(strsecondDate))
        dateFlag=true;
        else
        {
            int year1=Integer.parseInt(firstDateArray[0]);
            int mth1=Integer.parseInt(firstDateArray[1]);
            int day1=Integer.parseInt(firstDateArray[2]);

            int year2=Integer.parseInt(secondDateArray[0]);
            int mth2=Integer.parseInt(secondDateArray[1]);
            int day2=Integer.parseInt(secondDateArray[2]);
            dateFlag=compareDate(year1, mth1, day1,year2, mth2, day2);
        }
    }
    return dateFlag;
}
public static boolean compareDates(String firstDate, String secondDate)
{
    String[] firstDateArray;
    String[] secondDateArray;
    boolean dateFlag=false;
    if(firstDate !=null && secondDate !=null)
    {
        if(firstDate.indexOf("/") !=-1)
        firstDate=DateManager.processMthDayYearToMysqlFormat(firstDate);
        if(secondDate.indexOf("/") !=-1)
        secondDate=DateManager.processMthDayYearToMysqlFormat(secondDate);
        firstDateArray=firstDate.split("-");
        secondDateArray=secondDate.split("-");
        if(firstDateArray.length !=3 || secondDateArray.length !=3)
        return false;
        if(firstDate.equalsIgnoreCase(secondDate))
        dateFlag=true;
        else
        {
            int year1=Integer.parseInt(firstDateArray[0]);
            int mth1=Integer.parseInt(firstDateArray[1]);
            int day1=Integer.parseInt(firstDateArray[2]);

            int year2=Integer.parseInt(secondDateArray[0]);
            int mth2=Integer.parseInt(secondDateArray[1]);
            int day2=Integer.parseInt(secondDateArray[2]);
            dateFlag=compareDate(year1, mth1, day1,year2, mth2, day2);
        }
    }
    return dateFlag;
}
    public static boolean compareDate(int year1,int mth1,int day1,int year2,int mth2,int day2)
    {
        Calendar c1=new GregorianCalendar(year1,mth1,day1);
        Calendar c2=new GregorianCalendar(year2,mth2,day2);
        if(c1.before(c2))
        return true;
        return false;
    }
    public String getLastDayOfMonth(String mth)
    {
        String lastDay="31";
        if(mth.equalsIgnoreCase("02"))
        lastDay="28";
        else if(mth.equalsIgnoreCase("04") || mth.equalsIgnoreCase("06") || mth.equalsIgnoreCase("09") || mth.equalsIgnoreCase("11"))
        lastDay="30";
        return lastDay;
    }
    public List getDateLabels(String[] dateParams)
    {
        List dateList=new ArrayList();
        Calendar cal=Calendar.getInstance();
        for(int i=0; i<dateParams.length; i++)
        {
           String[] dateArray=dateParams[i].split("-");
           cal.set(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[2]));
           dateList.add(cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)+" "+dateArray[0]);
        }
        return dateList;
    }
    public String getStartDate(int startMth,int startYear)
    {
        String startDate=startYear+"-"+startMth+"-"+"01";
        return startDate;
    }
    public String getEndDate(int endMth,int endYear)
    {
        Calendar cal=Calendar.getInstance();
        cal.set(endYear, endMth-1,1);
        int lastDayOfMth=cal.getActualMaximum(Calendar.DATE);
        String endDate=endYear+"-"+endMth+"-"+lastDayOfMth;
        return endDate;
    }
    public String getStartDate(String[] params)
    {
        String startDate=null;
        if(params !=null && params.length >1)
        {
            if(!validateDateParamenters(params))
            return null;
            startDate=params[1]+"-"+params[0]+"-"+"01";
        }
        return startDate;
    }
    public String getEndDate(String[] params)
    {
        String endDate=null;
        if(params !=null && params.length >3)
        {
            if(!validateDateParamenters(params))
            return null;
            Calendar cal=Calendar.getInstance();
            cal.set(Integer.parseInt(params[3]), Integer.parseInt(params[2])-1,1);
            int lastDayOfMth=cal.getActualMaximum(Calendar.DATE);
            endDate=params[3]+"-"+params[2]+"-"+lastDayOfMth;
        }
        return endDate;
    }
    public boolean validateDateParamenters(String[] params)
    {
        boolean gooddateParams=true;
        for(int i=0; i<4; i++)
        {
            if(params[i]==null || params[i].equalsIgnoreCase("All"))
            gooddateParams=false;
        }
        return gooddateParams;
    }
    public List generatetMonthlyDates(int year)
    {
        List list=new ArrayList();
        for(int i=1; i<13; i++)
        {
            if(i==1)
            list.add("Jan-"+year);
            else if(i==2)
            list.add("Feb-"+year);
            if(i==3)
            list.add("Mar-"+year);
            else if(i==4)
            list.add("Apr-"+year);
            if(i==5)
            list.add("May-"+year);
            else if(i==6)
            list.add("Jun-"+year);
            if(i==7)
            list.add("Jul-"+year);
            else if(i==8)
            list.add("Aug-"+year);
            if(i==9)
            list.add("Sep-"+year);
            else if(i==10)
            list.add("Oct-"+year);
            if(i==11)
            list.add("Nov-"+year);
            else if(i==2)
            list.add("Dec-"+year);
        }
        return list;
    }
    public List generateQuarterlyDates(int year)
    {
        List list=new ArrayList();
        for(int i=1; i<13; i+=3)
        {
            if(i==1)
            list.add("Jan-Mar "+year);
            else if(i==4)
            list.add("Apr-Jun "+year);
            else if(i==7)
            list.add("Jul-Sep "+year);
            else if(i==10)
            list.add("Oct-Dec "+year);
        }
        return list;
    }
    public List generateSemiAnnualDates(int year)
    {
        List list=new ArrayList();
        for(int i=1; i<13; i+=6)
        {
            if(i==1)
            list.add("Jan-Jun "+year);
            else if(i==7)
            list.add("Jul-Dec "+year);
        }
        return list;
    }
    public List generateAnnualDates(int startyear, int endYear)
    {
        List list=new ArrayList();
        for(int i=startyear; i<endYear+1; i++)
        {
            list.add(i);
        }
        return list;
    }
    public static String getDateParameter(int startMth, int startYear, int endMth,int endYear)
    {
        String reportPrd="";
        DateManager dm=new DateManager();
        String startMonth=dm.getFullMonthAsString(startMth);
        String endMonth=dm.getFullMonthAsString(endMth);
        reportPrd=startMonth+" "+startYear+" to "+endMonth+" "+endYear;
        return reportPrd;
    }
    public static String getDateParameter(String startDate,String endDate)
    {
        String reportPrd="";
        reportPrd=startDate+" to "+endDate;
        return reportPrd;
    }
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
      //System.err.println("difference_In_Time is "+difference_In_Time);
      return difference_In_Time;
  }
  public long getDifferenceInYears(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Years
                = TimeUnit
                      .MILLISECONDS
                      .toDays(getDifferenceInTime(startDate,endDate,dateFormat))
                  / 365;
      //System.err.println("difference_In_Years is "+difference_In_Years);
      return difference_In_Years;
  }
public long getDifferenceInDays(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Days
                = TimeUnit
                      .MILLISECONDS
                      .toDays(getDifferenceInTime(startDate,endDate,dateFormat))
                  % 365;
      //System.err.println("difference_In_Days is "+difference_In_Days);          
      return difference_In_Days;
  }
  public long getDifferenceInHours(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Hours
                = TimeUnit
                      .MILLISECONDS
                      .toHours(getDifferenceInTime(startDate,endDate,dateFormat))
                  % 24;
      //System.err.println("difference_In_Hours is "+difference_In_Hours);
      return difference_In_Hours;
  }
  public long getDifferenceInMinutes(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Minutes
                = TimeUnit
                      .MILLISECONDS
                      .toMinutes(getDifferenceInTime(startDate,endDate,dateFormat))
                  % 24;
      //System.err.println("difference_In_Minutes is "+difference_In_Minutes);
      return difference_In_Minutes;
  }
  public long getDifferenceInSeconds(String startDate,String endDate,String dateFormat)
  {
      long difference_In_Seconds
                = TimeUnit
                      .MILLISECONDS
                      .toSeconds(getDifferenceInTime(startDate,endDate,dateFormat))
                  % 24;
      //System.err.println("difference_In_Seconds is "+difference_In_Seconds);
      return difference_In_Seconds;
  }
 }
