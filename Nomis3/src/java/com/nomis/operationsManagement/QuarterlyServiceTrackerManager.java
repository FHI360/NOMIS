/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;


import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.business.HouseholdService;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class QuarterlyServiceTrackerManager 
{
    FinancialYearManager fym=new FinancialYearManager();
    DateManager dm=new DateManager();
   /*public int saveQuarterlyService(String beneficiaryId,Date serviceDate,int beneficiaryType,int ageAtService,boolean isService,String userName)
   {
       int result=0;
       try
       {
           DaoUtility util=new DaoUtility();
           //FinancialYearManager fym=new FinancialYearManager();
           String strServiceDate=DateManager.convertDateToString(serviceDate, DateManager.DB_DATE_FORMAT);
           if(strServiceDate !=null)
           {
               boolean exists=true;
               String[] strServiceDateArray=strServiceDate.split("-");
               if(strServiceDateArray !=null && strServiceDateArray.length>2)
               {
                   int year=Integer.parseInt(strServiceDateArray[0]);
                   int month=Integer.parseInt(strServiceDateArray[1]);
                   //int day=Integer.parseInt(strServiceDateArray[2]);
                   int quarter=fym.getQuarter(month);
                   QuarterlyServiceTracker qst=util.getQuarterlyServiceTrackerDaoInstance().getQuarterlyServiceTracker(beneficiaryId, year);
                   if(qst ==null)
                   {
                       exists=false;
                       qst=new QuarterlyServiceTracker();
                       qst.setfYear(year);
                       qst.setDateCreated(DateManager.getCurrentDateInstance());
                       qst.setBeneficiaryId(beneficiaryId);
                       qst.setServiceDate(serviceDate);
                   }
                   //if this is a service request, set the service for the appropriate quarter, if not, this the baseline entry, save the default values of 0 for the quarter
                   if(isService)
                   {
                       if(quarter==1)
                       {
                           qst.setQ1Service(1);
                           qst.setAgeAtQ1(ageAtService);

                       }
                       else if(quarter==2)
                       {
                           qst.setQ2Service(1);
                           qst.setAgeAtQ2(ageAtService);
                       }
                       if(quarter==3)
                       {
                           qst.setQ3Service(1);
                           qst.setAgeAtQ3(ageAtService);
                       }
                       if(quarter==4)
                       {
                           qst.setQ4Service(1);
                           qst.setAgeAtQ4(ageAtService);
                       }
                   }
                   qst.setCurrentQuarter(quarter);
                   qst.setServiceDate(serviceDate);
                   qst.setBeneficiaryType(beneficiaryType);
                   qst.setLastModifiedDate(DateManager.getCurrentDateInstance());
                   if(exists)
                   {
                       util.getQuarterlyServiceTrackerDaoInstance().updateQuarterlyServiceTracker(qst);
                       if(qst.getServiceDate().before(serviceDate))
                       qst.setServiceDate(serviceDate);
                   }
                   else
                   {
                       util.getQuarterlyServiceTrackerDaoInstance().saveQuarterlyServiceTracker(qst);
                   }
                   if(qst.getQ4Service()==1)
                   {
                       setPrevQuarterlyServiceOfNextFY(beneficiaryId,serviceDate,beneficiaryType,ageAtService);
                   }
                   setBeneficiaryStatus(qst,userName);
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return result;
   }
   public void setPrevQuarterlyServiceOfNextFY(String beneficiaryId,Date serviceDate,int beneficiaryType,int ageAtService)
   {
       DaoUtility util=new DaoUtility();
       String strServiceDate=DateManager.convertDateToString(serviceDate, DateManager.DB_DATE_FORMAT);
       try
       {
           if(strServiceDate !=null)
           {
               boolean exists=true;
               String[] strServiceDateArray=strServiceDate.split("-");
               if(strServiceDateArray !=null && strServiceDateArray.length>2)
               {
                   //set year as current year since this service is for previous quarter
                   int year=Integer.parseInt(strServiceDateArray[0])+1;
                   int month=Integer.parseInt(strServiceDateArray[1]);
                   //int day=Integer.parseInt(strServiceDateArray[2]);
                   int quarter=fym.getQuarter(month);
                   QuarterlyServiceTracker qst=util.getQuarterlyServiceTrackerDaoInstance().getQuarterlyServiceTracker(beneficiaryId, year);
                   if(qst ==null)
                   {
                       exists=false;
                       qst=new QuarterlyServiceTracker();
                       qst.setfYear(year);
                       qst.setDateCreated(DateManager.getCurrentDateInstance());
                       qst.setBeneficiaryId(beneficiaryId);
                       //qst.setServiceDate(serviceDate);
                   }
                   //if this is a service request, set the service for the appropriate quarter, if not, this the baseline entry, save the default values of 0 for the quarter
                   qst.setPrevQ4Service(1);
                   qst.setAgeAtPrevQ4(ageAtService);
                   //qst.setCurrentQuarter(quarter);
                   qst.setServiceDate(serviceDate);
                   qst.setBeneficiaryType(beneficiaryType);
                   qst.setLastModifiedDate(DateManager.getCurrentDateInstance());
                   if(exists)
                   {
                       util.getQuarterlyServiceTrackerDaoInstance().updateQuarterlyServiceTracker(qst);
                       if(qst.getServiceDate().before(serviceDate))
                       qst.setServiceDate(serviceDate);
                   }
                   else
                   {
                       util.getQuarterlyServiceTrackerDaoInstance().saveQuarterlyServiceTracker(qst);
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
   }*/
   /*private int setBeneficiaryStatus(QuarterlyServiceTracker qst,String userName)
   {
       int result=0;
       try
       {
           if(qst !=null)
           {
               EnrollmentStatusManager esm=new EnrollmentStatusManager();
               DaoUtility util=new DaoUtility();
               if(qst.getBeneficiaryType()==AppConstant.OVC_TYPE_NUM)
               {
                   Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(qst.getBeneficiaryId());
                   if(ovc !=null)
                   {
                       
                           //get the new status and set it
                           int newStatus=getNewBeneficiaryStatus(qst,ovc.getDateOfEnrollment());
                           if(newStatus>0)
                           {
                               ovc.setCurrentEnrollmentStatus(newStatus);
                               //
                               if(ovc.getDateOfCurrentStatus().before(qst.getServiceDate()))
                               {//Update current status only if this is more recent than the current enrollment status of the child
                                   ovc.setDateOfCurrentEnrollmentStatus(qst.getServiceDate());
                                   util.getChildEnrollmentDaoInstance().updateOvc(ovc,false,false);
                               }
                               ovc.setDateOfCurrentEnrollmentStatus(qst.getServiceDate());
                               esm.saveOvcEnrollmentStatusHistory(ovc, 3,userName);
                           }
                       //}
                   }
               }
               else if(qst.getBeneficiaryType()==AppConstant.CAREGIVER_TYPE_NUM)
               {
                   AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(qst.getBeneficiaryId());
                   if(ahm !=null)
                   {
                       
                           //get the new status and set it
                           int newStatus=getNewBeneficiaryStatus(qst,ahm.getDateOfEnrollment());
                           if(newStatus>0)
                           {
                               ahm.setCurrentEnrollmentStatus(newStatus);
                               
                               if(ahm.getDateOfCurrentEnrollmentStatus().before(qst.getServiceDate()))
                               {
                                   ahm.setDateOfCurrentEnrollmentStatus(qst.getServiceDate());
                                   util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                               }
                               ahm.setDateOfCurrentEnrollmentStatus(qst.getServiceDate());
                               esm.saveAdultHouseholdMemberEnrollmentStatusHistory(ahm, 3,userName);
                           }
                       //}
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return result;
   }*/
   private int getNewBeneficiaryStatus(QuarterlyServiceTracker qst,Date dateOfEnrollment)
   {
       int status=0;
       try
       {
           if(qst !=null)
           {
               int quarterSum=0;
               if(qst.getCurrentQuarter()==1)
               {
                   //check to see if this beneficiary was served last fy q4
                   DaoUtility util=new DaoUtility();
                   int previousYear=qst.getfYear()-1;
                   QuarterlyServiceTracker previousQst=null;//util.getQuarterlyServiceTrackerDaoInstance().getQuarterlyServiceTracker(qst.getBeneficiaryId(),previousYear);
                   if(previousQst !=null)
                   quarterSum=qst.getQ1Service()+previousQst.getQ4Service();
                   else
                   quarterSum=qst.getQ1Service();
               }
               else if(qst.getCurrentQuarter()==2)
               {
                   quarterSum=qst.getQ1Service()+qst.getQ2Service();
               }
               else if(qst.getCurrentQuarter()==3)
               {
                   quarterSum=qst.getQ2Service()+qst.getQ3Service();
               }
               if(qst.getCurrentQuarter()==4)
               {
                   quarterSum=qst.getQ3Service()+qst.getQ4Service();
               }
               if(quarterSum==1)
               {
                   //This beneficiary has not been served in two consecutive quarters. Check the enrollment date and new status
                   String strDateOfEnrollment=DateManager.convertDateToString(dateOfEnrollment,DateManager.DB_DATE_FORMAT);
                   
                   if(strDateOfEnrollment !=null && strDateOfEnrollment.indexOf("-") !=-1)
                   {
                       String[] strDateOfEnrollmentArray=strDateOfEnrollment.split("-");
                       
                       if(strDateOfEnrollmentArray !=null)
                       {
                           //get the year of enrollment and compare with the year of service
                           int enrYear=Integer.parseInt(strDateOfEnrollmentArray[0]);
                           int enrMonth=Integer.parseInt(strDateOfEnrollmentArray[1]);
                           //if year of enrollment is before year of service or the month services provided is more than four months after enrollment, then set status to re-enrolled
                           if(enrYear<qst.getfYear() || (dm.getMonthFromDate(qst.getServiceDate()) -enrMonth) > 4)
                           status=AppConstant.REENROLLED_NUM;
                           else
                           status=AppConstant.ACTIVE_NUM;
                       }
                   }
               }
               else
               //A number of 2 means the beneficiary was served in two consecutive quarters.
               status=AppConstant.ACTIVE_NUM;
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return status;
   }
   public int processDefaultQuarterlyServiceByOrgUnit(String userName)
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
           if(level4OuList !=null)
           {
               int ovcCount=createDefaultQuarterlyChildService(level4OuList,userName);
               count+=ovcCount;
               System.err.println("Total Ovc service processed is "+ovcCount);
               int ahmCount=createDefaultQuarterlyHouseholdService(level4OuList,userName);
               count+=ahmCount;
               System.err.println("Total Household service processed is "+ahmCount);
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }
   public int createDefaultQuarterlyChildService(List level4OuList,String userName)
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           if(level4OuList !=null)
           {
               String level4OuId=null;
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List ovcList=util.getChildEnrollmentDaoInstance().getListOfOvcByLevel4OrganizationUnit(level4OuId);
                   if(ovcList !=null)
                   {
                       Ovc ovc=null;
                       for(int j=0; j<ovcList.size(); j++)
                       {
                           ovc=(Ovc)ovcList.get(j);
                           //saveQuarterlyService(ovc.getOvcId(), ovc.getDateOfEnrollment(), AppConstant.OVC_TYPE_NUM, ovc.getAgeAtBaseline(),false,userName);
                           count++;
                       }
                       System.err.println(" count of OVC service is "+count);
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }
   public int createDefaultQuarterlyHouseholdService(List level4OuList,String userName)
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           if(level4OuList !=null)
           {
               String level4OuId=null;
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List ahmList=util.getAdultHouseholdMemberDaoInstance().getListOfAdultHouseholdMembersByLevel4OrganizationUnit(level4OuId);
                   if(ahmList !=null)
                   {
                       AdultHouseholdMember ahm=null;
                       for(int j=0; j<ahmList.size(); j++)
                       {
                           ahm=(AdultHouseholdMember)ahmList.get(j);
                           //saveQuarterlyService(ahm.getBeneficiaryId(), ahm.getDateOfEnrollment(), AppConstant.CAREGIVER_TYPE_NUM, ahm.getAgeAtBaseline(),false,userName);
                           count++;
                       }
                       System.err.println(" count of Household service is "+count);
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }
   public int processQuarterlyServiceByOrgUnit(String userName)
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
           if(level4OuList !=null)
           {
               int ChildServiceCount=trackQuarterlyChildService(level4OuList,userName);
               count+=ChildServiceCount;
               System.err.println("Total Ovc service processed is "+ChildServiceCount);
               int hhsCount=trackQuarterlyHouseholdService(level4OuList,userName);
               count+=hhsCount;
               System.err.println("Total Household service processed is "+hhsCount);
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }
   public int trackQuarterlyChildService(List level4OuList,String userName)
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           if(level4OuList !=null)
           {
               String level4OuId=null;
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List serviceList=util.getChildServiceDaoInstance().getListOfServicesByLevel4OrganizationUnit(level4OuId);
                   if(serviceList !=null)
                   {
                       ChildService service=null;
                       for(int j=0; j<serviceList.size(); j++)
                       {
                           service=(ChildService)serviceList.get(j);
                           //saveQuarterlyService(service.getOvcId(), service.getServiceDate(), AppConstant.OVC_TYPE_NUM, service.getAgeAtService(),true,userName);
                           count++;
                       }
                       System.err.println(" count of OVC service is "+count);
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }
   public int trackQuarterlyHouseholdService(List level4OuList,String userName)
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           if(level4OuList !=null)
           {
               String level4OuId=null;
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List serviceList=util.getHouseholdServiceDaoInstance().getListOfServicesByLevel4OrganizationUnit(level4OuId);
                   if(serviceList !=null)
                   {
                       HouseholdService service=null;
                       for(int j=0; j<serviceList.size(); j++)
                       {
                           service=(HouseholdService)serviceList.get(j);
                           //saveQuarterlyService(service.getBeneficiaryId(), service.getServiceDate(), AppConstant.CAREGIVER_TYPE_NUM, service.getAgeAtService(),true,userName);
                           count++;
                       }
                       System.err.println(" count of Household service is "+count);
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }
   public int updateBeneficiaryStatus(String currentDate)
   {
       int numberOfRecords=0;
       try
       {
           if(currentDate !=null && currentDate.indexOf("-") !=-1)
           {
               DaoUtility util=new DaoUtility();
               String[] currentDateArray=currentDate.split("-");
               if(currentDateArray !=null && currentDateArray.length>1)
               {
                   //int totalScore=0;
                   int currentYear=Integer.parseInt(currentDateArray[0]);
                   int month=Integer.parseInt(currentDateArray[1]);
                   int previousQuarter=fym.getPreviousQuarter(month);
                   if(previousQuarter>0)
                   {
                       String ovcSql=null;
                       String ahmSql=null;
                       if(previousQuarter==1)
                       {
                           ahmSql="from AdultHouseholdMembers ahm, QuarterlyServiceTracker qst where ahm.beneficiaryId=qst.beneficiaryId and ahm.currentEnrollmentStatus=1 and qst.prevQ4Service=0 and qst.q1Service=0";
                           ovcSql="from Ovc ovc, QuarterlyServiceTracker qst where ovc.ovcId=qst.beneficiaryId and ovc.currentEnrollmentStatus=1 and qst.prevQ4Service=0 and qst.q1Service=0";
                       }
                       else if(previousQuarter==2)
                       {
                            ahmSql="from AdultHouseholdMembers ahm, QuarterlyServiceTracker qst where ahm.beneficiaryId=qst.beneficiaryId and ahm.currentEnrollmentStatus=1 and qst.q1Service=0 and qst.q2Service=0";
                            ovcSql="from Ovc ovc, QuarterlyServiceTracker qst where ovc.ovcId=qst.beneficiaryId and ovc.currentEnrollmentStatus=1 and qst.q1Service=0 and qst.q2Service=0";
                       }
                       else if(previousQuarter==3)
                       {
                            ahmSql="from AdultHouseholdMembers ahm, QuarterlyServiceTracker qst where ahm.beneficiaryId=qst.beneficiaryId and ahm.currentEnrollmentStatus=1 and qst.q2Service=0 and qst.q3Service=0";
                            ovcSql="from Ovc ovc, QuarterlyServiceTracker qst where ovc.ovcId=qst.beneficiaryId and ovc.currentEnrollmentStatus=1 and qst.q2Service=0 and qst.q3Service=0";
                       }
                       else if(previousQuarter==4)
                       {
                           ahmSql="from AdultHouseholdMembers ahm, QuarterlyServiceTracker qst where ahm.beneficiaryId=qst.beneficiaryId and ahm.currentEnrollmentStatus=1 and qst.q3Service=0 and qst.q4Service=0";
                           ovcSql="from Ovc ovc, QuarterlyServiceTracker qst where ovc.ovcId=qst.beneficiaryId and ovc.currentEnrollmentStatus=1 and qst.q3Service=0 and qst.q4Service=0";
                       }
                       if(ovcSql !=null)
                       {
                           List list= null;//util.getQuarterlyServiceTrackerDaoInstance().getDefaultingQuarterly1OvcRecords();
                           setOvcCurrentStatus(list,previousQuarter,currentYear);
                       }
                       if(ahmSql !=null)
                       {
                           List list=null;//util.getQuarterlyServiceTrackerDaoInstance().getDefaultingQuarterly1AdultMembersRecords();
                           setAdultHouseholdMembersCurrentStatus(list,previousQuarter,currentYear);
                       }
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return numberOfRecords;
   }
   private void setOvcCurrentStatus(List list,int previousQuarter,int currentYear)
   {
       try
       {
           if(list !=null)
           {
               DaoUtility util=new DaoUtility();
               int totalScore=0;
               int yearOfEnrollment=0;
               int monthOfEnrollment=0;
               Ovc ovc=null;
               QuarterlyServiceTracker qst=null;
               for(Object obj:list)
               {
                   Object[] objArray=(Object[])obj;
                   ovc=(Ovc)objArray[0];
                   qst=(QuarterlyServiceTracker)objArray[1];
                   totalScore=getTotalQuarterlyServiceScore(qst,previousQuarter);
                   ovc.setCurrentEnrollmentStatus(AppConstant.INACTIVE_NUM);
                   ovc.setDateOfCurrentEnrollmentStatus(DateManager.getCurrentDateInstance());
                   if(totalScore==1)
                   {
                       yearOfEnrollment=DateManager.getYear(ovc.getDateOfEnrollment());
                       monthOfEnrollment=DateManager.getMonth(ovc.getDateOfEnrollment());
                       if(yearOfEnrollment==currentYear && fym.getPreviousQuarter(monthOfEnrollment)==previousQuarter)
                       {
                           ovc.setCurrentEnrollmentStatus(AppConstant.ACTIVE_NUM);
                       }
                   }
                   util.getChildEnrollmentDaoInstance().updateOvc(ovc, false,false);
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
   }
   private void setAdultHouseholdMembersCurrentStatus(List list,int previousQuarter,int currentYear)
   {
       try
       {
           if(list !=null)
           {
               DaoUtility util=new DaoUtility();
               int totalScore=0;
               int yearOfEnrollment=0;
               int monthOfEnrollment=0;
               AdultHouseholdMember ahm=null;
               QuarterlyServiceTracker qst=null;
               for(Object obj:list)
               {
                   Object[] objArray=(Object[])obj;
                   ahm=(AdultHouseholdMember)objArray[0];
                   qst=(QuarterlyServiceTracker)objArray[1];
                   totalScore=getTotalQuarterlyServiceScore(qst,previousQuarter);
                   ahm.setCurrentEnrollmentStatus(AppConstant.INACTIVE_NUM);
                   ahm.setDateOfCurrentEnrollmentStatus(DateManager.getCurrentDateInstance());
                   if(totalScore==1)
                   {
                       yearOfEnrollment=DateManager.getYear(ahm.getDateOfEnrollment());
                       monthOfEnrollment=DateManager.getMonth(ahm.getDateOfEnrollment());
                       if(yearOfEnrollment==currentYear && fym.getPreviousQuarter(monthOfEnrollment)==previousQuarter)
                       {
                           ahm.setCurrentEnrollmentStatus(AppConstant.ACTIVE_NUM);
                       }
                   }
                   util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
   }
   private int getTotalQuarterlyServiceScore(QuarterlyServiceTracker qst,int previousQuarter)
   {
       int totalScore=0;
       if(previousQuarter==1)
       {
          totalScore=qst.getPrevQ4Service()+qst.getQ1Service(); 
       }
       else if(previousQuarter==2)
       {
          totalScore=qst.getQ1Service()+qst.getQ2Service(); 
       }
       else if(previousQuarter==3)
       {
          totalScore=qst.getQ2Service()+qst.getQ3Service(); 
       }
       else if(previousQuarter==4)
       {
          totalScore=qst.getQ3Service()+qst.getQ4Service(); 
       }
       return totalScore;
   }
   /*public void updateOvcCurrentEnrollmentStatus(String startOfFirstQuarter,String endOfFirstQuarter,String startOfSecondQuarter,String endOfSecondQuarter)
   {
       String sql="update APP.CHILDENROLLMENT set currentenrollmentstatus="+AppConstant.INACTIVE_NUM+" where currentenrollmentstatus=1 and dateofenrollment <='"+endOfFirstQuarter+"' and  ovcId not in (select distinct ovcId from childservice where servicedate between '"+startOfSecondQuarter+"' and '"+endOfSecondQuarter+"')";
   }
   public void updateAdultHouseholdmembersCurrentEnrollmentStatus(String startOfFirstQuarter,String endOfFirstQuarter,String startOfSecondQuarter,String endOfSecondQuarter)
   {
       String sql="update APP.ADULTHOUSEHOLDMEMBER set currentenrollmentstatus="+AppConstant.INACTIVE_NUM+" where currentenrollmentstatus=1 and dateofenrollment <='"+endOfFirstQuarter+"' and  ovcId not in (select distinct ovcId from householdservice where servicedate between '"+startOfSecondQuarter+"' and '"+endOfSecondQuarter+"')";
       //update APP.ADULTHOUSEHOLDMEMBER set currentenrollmentstatus=10 where currentenrollmentstatus=1 and clientId not in (select distinct beneficiaryId from householdservice where servicedate between '2019-01-01' and '2019-03-31')
       //update APP.CHILDENROLLMENT set currentenrollmentstatus=10 where dateofenrollment <='2018-12-31' and  ovcId not in (select distinct ovcId from childservice where servicedate between '2019-01-01' and '2019-03-31')
   }*/
   public int updateOvcAgeAtServiceWithCurrentAge()
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
           if(level4OuList !=null)
           {
               String level4OuId=null;
               ChildService service=null;
               String ovcId=null;
               Ovc ovc=null;
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List serviceList=util.getChildServiceDaoInstance().getListOfServicesByLevel4OrganizationUnit(level4OuId);
                   if(serviceList !=null)
                   {
                       for(int j=0; j<serviceList.size(); j++)
                       {
                           Object[] objArray=(Object[])serviceList.get(j);
                            service=(ChildService)objArray[3];
                            ovc=(Ovc)objArray[1];
                            if(ovc !=null)
                            {
                                service.setAgeAtService(ovc.getCurrentAge());
                                service.setAgeUnitAtService(ovc.getCurrentAgeUnit());
                                util.getChildServiceDaoInstance().updateChildService(service, false);
                                count++;
                                System.err.println("Ovc service with Id "+service.getId()+" at "+count+" updated with current age");
                            }
                          /*service=(ChildService)serviceList.get(j);
                          ovc=(Ovc)util.getChildEnrollmentDaoInstance().getOvc(service.getOvcId());
                          if(ovc !=null)
                          {
                              service.setAgeAtService(ovc.getCurrentAge());
                              service.setAgeUnitAtService(ovc.getCurrentAgeUnitCode());
                              util.getChildServiceDaoInstance().updateChildService(service, false);
                              count++;
                              System.err.println("Ovc service with Id "+service.getId()+" at "+count+" updated with current age");
                          }*/
                       }
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }
   public int updateHouseholdServiceAgeAtServiceWithCurrentAge()
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
           if(level4OuList !=null)
           {
               String level4OuId=null;
               HouseholdService service=null;
               AdultHouseholdMember ahm=null;
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List serviceList=util.getHouseholdServiceDaoInstance().getListOfServicesByLevel4OrganizationUnit(level4OuId);
                   if(serviceList !=null)
                   {
                       for(int j=0; j<serviceList.size(); j++)
                       {
                           Object[] objArray=(Object[])serviceList.get(j);
                            service=(HouseholdService)objArray[3];
                            ahm=(AdultHouseholdMember)objArray[2];
                            if(ahm !=null)
                            {
                                service.setAgeAtService(ahm.getCurrentAge());
                                util.getHouseholdServiceDaoInstance().updateHouseholdService(service, false);
                                count++;
                                System.err.println("Household service with Id "+service.getBeneficiaryId()+" at "+count+" updated with current age");
                            }
                          /*service=(HouseholdService)serviceList.get(j);
                          ahm=(AdultHouseholdMember)util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(service.getBeneficiaryId());
                          if(ahm !=null)
                          {
                              service.setAgeAtService(ahm.getCurrentAge());
                              util.getHouseholdServiceDaoInstance().updateHouseholdService(service, false);
                              count++;
                              System.err.println("Household service with Id "+service.getBeneficiaryId()+" at "+count+" updated with current age");
                          }*/
                       }
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }
   /*public int updateAgeAtRiskAssessmentWithCurrentAge()
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
           if(level4OuList !=null)
           {
               String level4OuId=null;
               HivRiskAssessment hra=null;
               String ovcId=null;
               Ovc ovc=null;
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List serviceList=util.getHivRiskAssessmentDaoInstance().getHivRiskAssessmentRecordsByLevel4OuId(level4OuId);
                   if(serviceList !=null)
                   {
                       for(int j=0; j<serviceList.size(); j++)
                       {   
                            Object[] objArray=(Object[])serviceList.get(j);
                            hra=(HivRiskAssessment)objArray[3];
                            ovc=(Ovc)objArray[1];
                            if(ovc !=null)
                            {
                                hra.setAgeAtAssessment(ovc.getCurrentAge());
                                hra.setAgeUnitAtAssessment(ovc.getCurrentAgeUnitCode());
                                util.getHivRiskAssessmentDaoInstance().updateHivRiskAssessment(hra);
                                count++;
                                System.err.println("HIV Risk assessment with Id "+hra.getOvcId()+" at "+count+" updated with current age");
                            }
                       }
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }*/
   /*public int updateAgeAtReferralWithCurrentAge()
   {
       int count=0;
       try
       {
           DaoUtility util=new DaoUtility();
           List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
           if(level4OuList !=null)
           {
               String level4OuId=null;
               ReferralService referral=null;
               String ovcId=null;
               Ovc ovc=null;
               AdultHouseholdMember ahm=null;
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List serviceList=util.getOvcReferralDaoInstance().getOvcReferralServiceRecordsByLevel4OuId(level4OuId);
                   if(serviceList !=null)
                   {
                       for(int j=0; j<serviceList.size(); j++)
                       {   
                            Object[] objArray=(Object[])serviceList.get(j);
                            referral=(ReferralService)objArray[3];
                            ovc=(Ovc)objArray[1];
                            if(ovc !=null)
                            {
                                referral.setAgeAtService(ovc.getCurrentAge());
                                referral.setAgeUnitAtService(ovc.getCurrentAgeUnitCode());
                                util.getOvcReferralDaoInstance().updateReferralService(referral,false);
                                count++;
                                System.err.println("OVC Referral record with Id "+referral.getBeneficiaryId()+" at "+count+" updated with current age");
                            }
                       }
                   }
               }
               for(int i=0; i<level4OuList.size(); i++)
               {
                   level4OuId=(String)level4OuList.get(i);
                   List serviceList=util.getOvcReferralDaoInstance().getAdultHouseholdMembersReferralServiceRecordsByLevel4OuId(level4OuId);
                   if(serviceList !=null)
                   {
                       for(int j=0; j<serviceList.size(); j++)
                       {   
                            Object[] objArray=(Object[])serviceList.get(j);
                            referral=(ReferralService)objArray[3];
                            ahm=(AdultHouseholdMember)objArray[1];
                            if(ahm !=null)
                            {
                                referral.setAgeAtService(ahm.getCurrentAge());
                                referral.setAgeUnitAtService(2);
                                util.getOvcReferralDaoInstance().updateReferralService(referral,false);
                                count++;
                                System.err.println("Adult Household Member Referral record with Id "+referral.getBeneficiaryId()+" at "+count+" updated with current age");
                            }
                       }
                   }
               }
           }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       return count;
   }*/
}
