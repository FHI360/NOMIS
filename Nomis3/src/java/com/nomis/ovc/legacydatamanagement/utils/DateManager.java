/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.utils;

//import com.fhi.kidmap.dao.DaoUtil;
import com.nomis.ovc.util.Month;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author smomoh
 */
public class DateManager implements Serializable
{
    public static String DB_DATE_FORMAT="yyyy-MM-dd";
    public static String DD_MM_YYYY_SLASH="dd/MM/yyyy";
    public static String MM_DD_YYYY_SLASH="MM/dd/yyyy";
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
    /*public List generatDate(int startYear)
    {
        List listOfDates=new ArrayList();
        for(int i=startYear; i<=2020; i++)
        {
            listOfDates.add(i);
        }
        return listOfDates;
    }*/
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
    public static Date getNewDateInstance()
    {
        Date date=new Date();
        return date;
    }
    public static String getDefaultCurrentDateAndTime() 
    {
        //
        String dateAndTime=null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateAndTime=dateFormat.format(date);
        return dateAndTime; 
    }
    public static String getCurrentDateAndTime(String dateAndTimeFormat) 
    {
        //"yyyy-MM-dd HH:mm:ss"
        String dateAndTime=null;
        DateFormat dateFormat = new SimpleDateFormat(dateAndTimeFormat);
        Date date = new Date();
        dateAndTime=dateFormat.format(date);
        return dateAndTime; 
    }
    /*public static int getDateDifference(int mth, int year)
    {
        Calendar calendar = Calendar.getInstance();
        int currentYear=calendar.get(Calendar.YEAR);
        int currentMonth=calendar.get(Calendar.MONTH)+1;
        //System.err.println("currentMonth is "+currentMonth);
        int yearDiff=currentYear-year;
        int mthDiff=0;
        if(yearDiff ==0 && (currentMonth >= mth))
        mthDiff=currentMonth-mth;
        else if(yearDiff==1)
        mthDiff=currentMonth+(12-mth);
        else if(yearDiff>1)
        mthDiff=(currentMonth+(12-mth))+((yearDiff-1)*12);
        return mthDiff;
    }*/
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
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    public static Date getDateInstance(String datestr)
    {
        Date date=new Date();
        try
        {
            date=new SimpleDateFormat("yyyy-MM-dd").parse((datestr));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return date;
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
    public static String getCurrentDate()
    {
        String dateFormat="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
        String currentDate=sdf.format(new java.util.Date());
        return currentDate;
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
                mysqlDate=getMySqlDate(dateArray[1],dateArray[0],dateArray[2]);
            }
        }
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
        int endYear=getYear(DateManager.getCurrentDateInstance());
        for(int i=startYear; i<=endYear; i++)
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
    public List generateMonths()
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
    public List generateYears(int firstYr,int lastYr)
    {
         List yearList=new ArrayList();
         for(int i=firstYr; i<lastYr+1; i++)
         {
             yearList.add(i);
         }
         return yearList;
    }
public static boolean compareDates(String firstDate, String secondDate)
{
    String[] firstDateArray;
    String[] secondDateArray;
    boolean dateFlag=false;
    if(firstDate !=null && secondDate !=null)
    {
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
           dateList.add(cal.getDisplayName(cal.MONTH, cal.SHORT, Locale.ENGLISH)+" "+dateArray[0]);
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
    /*public List getPeriodList(String periodType)
    {
        List periodList=new ArrayList();
        
        try
        {
            if(periodType !=null)
            {
                LegacyDaoUtil util=new LegacyDaoUtil();
                List list=util.getListOfYearsFromHhe();
                if(list !=null)
                {
                    int year=0;
                    //NomisDate date=new DateManager();
                    if(periodType.equalsIgnoreCase("monthly"))
                    {
                        for(int i=0; i<list.size(); i++)
                        {
                            year=Integer.parseInt(list.get(i).toString());
                            periodList.addAll(generatetMonthlyDates(year));
                        }
                    }
                    else if(periodType.equalsIgnoreCase("quarterly"))
                    {
                        for(int i=0; i<list.size(); i++)
                        {
                            year=Integer.parseInt(list.get(i).toString());
                            periodList.addAll(generateQuarterlyDates(year));
                        }
                    }
                    else if(periodType.equalsIgnoreCase("sixmonthly"))
                    {
                        for(int i=0; i<list.size(); i++)
                        {
                            year=Integer.parseInt(list.get(i).toString());
                            periodList.addAll(generateSemiAnnualDates(year));
                        }
                    }
                    else if(periodType.equalsIgnoreCase("annually"))
                    {
                        int startYear=Integer.parseInt(list.get(0).toString());
                        int endYear=Integer.parseInt(list.get(list.size()-1).toString());
                        for(int i=0; i<list.size(); i++)
                        {
                            year=Integer.parseInt(list.get(i).toString());
                            periodList.addAll(generateAnnualDates(startYear, endYear));
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return periodList;
    }*/
}
