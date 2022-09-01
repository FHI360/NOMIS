/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;


import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.business.EnrollmentStatusHistory;
import com.nomis.ovc.business.HouseholdService;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.ovc.databasemanagement.DatabaseMaintenance;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class EnrollmentStatusManager implements Serializable
{
    DateManager dm=new DateManager();
    private void defragmentEssentialTables()
    {
        List tableCodeList=new ArrayList();
        tableCodeList.add("esh");
        tableCodeList.add("hsh");
        tableCodeList.add("qst");
        tableCodeList.add("vce");
        tableCodeList.add("hhp");
        tableCodeList.add("vcs");
        tableCodeList.add("hhs");
        DatabaseMaintenance dbm=new DatabaseMaintenance(); 
        dbm.defragmentTable(tableCodeList, 0); 
    }
    public int deleteRecords(int deleteRecordsValue,String startDate,String endDate)
    {
        int count=0;
        try
        {
            if(deleteRecordsValue>0)
            {
                DaoUtility util=new DaoUtility();
                String query=null;
                if(deleteRecordsValue==1)
                query="delete from QuarterlyStatusTracker qst"; //where qst.enrollmentStatus=1";
                if(startDate !=null && endDate !=null)
                {
                    if(deleteRecordsValue==2)
                    query="delete from QuarterlyStatusTracker qst where qst.enrollmentStatus=1 and qst.dateOfEnrollmentStatus not between '"+startDate+"' and '"+endDate+"'";
                    else if(deleteRecordsValue==3)
                    query="delete from QuarterlyStatusTracker qst where qst.enrollmentStatus=1 and qst.dateOfEnrollmentStatus between '"+startDate+"' and '"+endDate+"'";
                }
                if(query !=null)
                count=util.executeUpdate(query);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int updateChildrenEnrollmentStatusForAllPeriods(String userName)
    {
        int count=0;
        try
        {
           DaoUtility util=new DaoUtility();
           List communityList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
           if(communityList !=null)
           {
               String communityCode=null;
               for(Object obj:communityList)
               {
                   communityCode=(String)obj;
                   Thread childThread=new Thread(new ChildrenEnrollmentStatusUpdateThread(communityCode,"All","All",userName));
                   childThread.start();
                   /*List list=util.getChildServiceDaoInstance().getServiceRecordsByCommunity(communityCode);
                   for(Object sobj:list)
                   {
                       ChildService service=(ChildService)sobj;
                       processChildrenEnrollmentStatusByChildId(service.getOvcId(),DateManager.convertDateToString(service.getServiceDate(), DateManager.DB_DATE_FORMAT), userName);
                   }*/
               }
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int updateAdultMembersEnrollmentStatusForAllPeriods(String userName)
    {
        int count=0;
        try
        {
           DaoUtility util=new DaoUtility();
           List communityList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
           if(communityList !=null)
           {
               String communityCode=null;
               for(Object obj:communityList)
               {
                   communityCode=(String)obj;
                   Thread adultThread=new Thread(new AdultMembersEnrollmentStatusUpdateThread(communityCode,"All","All",userName));
                   adultThread.start();
                   /*List list=util.getHouseholdServiceDaoInstance().getServiceRecordsByCommunity(communityCode);
                   for(Object sobj:list)
                   {
                       HouseholdService service=(HouseholdService)sobj;
                       processAdultHouseholdMemberEnrollmentStatusByBeneficiaryId(service.getBeneficiaryId(),DateManager.convertDateToString(service.getServiceDate(), DateManager.DB_DATE_FORMAT), userName);
                   }*/
               }
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int updateBeneficiaryEnrollmentStatusForAllPeriods(String userName)
    {
        int count=0;
        try
        {
            AppUtility.enrollmentStatusDataGeneratorCounter=0;
            count=updateChildrenEnrollmentStatusForAllPeriods(userName);
           count+=updateAdultMembersEnrollmentStatusForAllPeriods(userName);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int updateBeneficiaryEnrollmentStatusHistory(String serviceStartDate, String serviceEndDate,String userName)
    {
        int count=0;
        //defragmentEssentialTables();
        count+=updateChildrenEnrollmentStatusHistory(serviceStartDate,serviceEndDate,userName);
        count+=updateAdultHouseholdMemberEnrollmentStatusHistory(serviceStartDate,serviceEndDate,userName);
        return count;
    }
    public int updateChildrenEnrollmentStatusHistory(String serviceStartDate, String serviceEndDate,String userName)
    {
        int count=0;
        try
        {
            DaoUtility util=new DaoUtility();
            List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
            if(level4OuList !=null)
            {
                List childrenList=null;
                String level4OuId=null;
                //Ovc ovc=null;
                for(Object obj:level4OuList)
                {
                    level4OuId=(String)obj;
                    childrenList=util.getChildEnrollmentDaoInstance().getListOfOvcByLevel4OrganizationUnit(level4OuId);
                    count+=processChildrenEnrollmentStatus(childrenList,serviceStartDate,serviceEndDate,userName);
                    
                }
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int processChildrenEnrollmentStatus(List childrenList,String serviceStartDate, String serviceEndDate,String userName)
    {
        int count=0;
        try
        {
            if(childrenList !=null)
            {
                //updateLastDateOfCurrentEnrollmentStatus();
                Ovc ovc=null;
                for(Object ovcObj:childrenList)
                {
                   ovc=(Ovc)ovcObj; 
                   //saveOvcEnrollmentStatusHistory(ovc,1,serviceStartDate,serviceEndDate,userName);
                   saveOvcEnrollmentStatusHistory(ovc,2,serviceStartDate,serviceEndDate,userName);
                   saveOvcEnrollmentStatusHistory(ovc,3,serviceStartDate,serviceEndDate,userName);
                   count++;
                   if(count==10000)
                   {
                       //defragmentEssentialTables();
                   }
                   System.err.println(count+" Children enrollment status saved to history");
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int processChildrenEnrollmentStatusByChildId(String beneficiaryId,String serviceDate, String userName)
    {
        int count=0;
        try
        {
            FinancialYearManager fym=new FinancialYearManager();
            String serviceStartDate=fym.getStartDateOfQuarter(serviceDate);
            String serviceEndDate=fym.getEndDateOfQuarter(serviceDate);
               DaoUtility util=new DaoUtility();
               Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
               if(ovc !=null)
               {
                   saveOvcEnrollmentStatusHistory(ovc,1,serviceStartDate,serviceEndDate,userName);
                   saveOvcEnrollmentStatusHistory(ovc,2,serviceStartDate,serviceEndDate,userName);
                   saveOvcEnrollmentStatusHistory(ovc,3,serviceStartDate,serviceEndDate,userName);
                   count++;
                   System.err.println(" Child with "+beneficiaryId+" saved to enrollment status history");
               }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public  int processAdultHouseholdMemberEnrollmentStatus(List adultList,String serviceStartDate, String serviceEndDate,String userName)
    {
        int count=0;
        try
        {
            if(adultList !=null)
            {
                //updateLastDateOfCurrentEnrollmentStatus();
                AdultHouseholdMember ahm=null;
                for(Object ovcObj:adultList)
                {
                   ahm=(AdultHouseholdMember)ovcObj; 
                   //saveAdultHouseholdMemberEnrollmentStatusHistory(ahm,1,serviceStartDate, serviceEndDate,userName);
                   saveAdultHouseholdMemberEnrollmentStatusHistory(ahm,2,serviceStartDate, serviceEndDate,userName);
                   saveAdultHouseholdMemberEnrollmentStatusHistory(ahm,3,serviceStartDate, serviceEndDate,userName);
                   count++;
                   if(count==10000)
                   {
                       //defragmentEssentialTables();
                   }
                   System.err.println(count+" Adult members enrollment status saved to history");
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public  int processAdultHouseholdMemberEnrollmentStatusByBeneficiaryId(String beneficiaryId,String serviceDate, String userName)
    {
        int count=0;
        try
        {
            FinancialYearManager fym=new FinancialYearManager();
            String serviceStartDate=fym.getStartDateOfQuarter(serviceDate);
            String serviceEndDate=fym.getEndDateOfQuarter(serviceDate);
                DaoUtility util=new DaoUtility();
                AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
                if(ahm !=null)
                {
                   saveAdultHouseholdMemberEnrollmentStatusHistory(ahm,1,serviceStartDate, serviceEndDate,userName);
                   saveAdultHouseholdMemberEnrollmentStatusHistory(ahm,2,serviceStartDate, serviceEndDate,userName);
                   saveAdultHouseholdMemberEnrollmentStatusHistory(ahm,3,serviceStartDate, serviceEndDate,userName);
                   count++;
                   System.err.println(" Adult member with beneficiaryId "+beneficiaryId+" saved to enrollment status history");
                }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int updateAdultHouseholdMemberEnrollmentStatusHistory(String serviceStartDate, String serviceEndDate,String userName)
    {
        int count=0;
        try
        {
            DaoUtility util=new DaoUtility();
            List level4OuList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
            if(level4OuList !=null)
            {
                List adultList=null;
                String level4OuId=null;
                //AdultHouseholdMember ahm=null;
                for(Object obj:level4OuList)
                {
                    level4OuId=(String)obj;
                    adultList=util.getAdultHouseholdMemberDaoInstance().getListOfAdultHouseholdMembersByLevel4OrganizationUnit(level4OuId);
                    if(adultList !=null)
                    {
                        count+=processAdultHouseholdMemberEnrollmentStatus(adultList,serviceStartDate, serviceEndDate,userName);
                        
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
    public void saveOvcEnrollmentStatusHistory(Ovc ovc,int statusIndicator,String serviceStartDate,String serviceEndDate,String userName) throws Exception
    {
        EnrollmentStatusHistory esh=new EnrollmentStatusHistory();
        Date dServiceStartDate=DateManager.getDateInstance(serviceStartDate);
        Date dServiceEndDate=DateManager.getDateInstance(serviceEndDate);
        if(ovc !=null)
        {
            DaoUtility util=new DaoUtility();
            esh.setBeneficiaryId(ovc.getOvcId());
            esh.setBeneficiaryType(ovc.getBeneficiaryType());
            esh.setDateCreated(DateManager.getCurrentDateInstance());
            
            //esh.setDateOfNewStatus(ovc.getDateOfEnrollment());
            esh.setEnrolledOnTreatment(ovc.getEnrolledOnTreatment());
            esh.setCurrentAge(ovc.getCurrentAge());
            esh.setCurrentAgeUnit(ovc.getCurrentAgeUnit());
            esh.setFacilityId(ovc.getHivTreatmentFacilityId());
            esh.setLastModifiedDate(DateManager.getCurrentDateInstance());
            if(statusIndicator==1)
            {
                //Baseline enrollment status. If child enrolled less than 32 days within the report period, consider the child as active
                String dateOfEnrollemnt=DateManager.convertDateToString(ovc.getDateOfEnrollment(), DateManager.DB_DATE_FORMAT);
                long dayDifference=dm.getDifferenceInDays(dateOfEnrollemnt, serviceEndDate,DateManager.DB_DATE_FORMAT);
                if(dayDifference>=0 && dayDifference<32)
                {
                    //If child already has an enrollment status update within same period, do no process the baseline record again.
                    List list=util.getQuarterlyStatusTrackerDaoInstance().getQuarterlyStatusTracker(ovc.getOvcId(), dServiceStartDate, dServiceEndDate);
                    if(list==null || list.isEmpty())
                    {
                        esh.setEnrollmentStatus(AppConstant.ACTIVE_NUM);
                        esh.setCurrentAge(ovc.getAgeAtBaseline());
                        esh.setDateOfEnrollmentStatus(ovc.getDateOfEnrollment());
                        esh.setHivStatus(ovc.getBaselineHivStatus());
                        esh.setDateOfHivStatus(ovc.getDateOfEnrollment());
                        esh.setRecordedBy(userName);
                        esh=trackOvcService(ovc.getOvcId(),esh,serviceStartDate,serviceEndDate,userName);
                        util.getEnrollmentStatusHistoryDaoInstance().saveEnrollmentStatusHistory(esh);
                    }
                }
                return;
            }
            else if(statusIndicator==2)
            {
                if(ovc.getCurrentEnrollmentStatus()!=AppConstant.REENROLLED_NUM)
                return;
                esh.setEnrollmentStatus(AppConstant.INACTIVE_NUM);
                esh.setDateOfEnrollmentStatus(ovc.getDateOfCurrentEnrollmentStatus());
                //esh.setDateOfEnrollmentStatus(DateManager.getDateInstance("2019-03-31"));
                esh.setHivStatus(ovc.getCurrentHivStatus());
                esh.setDateOfHivStatus(ovc.getDateOfCurrentHivStatus());
            }
            else if(statusIndicator==3)
            {
                esh.setEnrollmentStatus(ovc.getCurrentEnrollmentStatus());
                esh.setDateOfEnrollmentStatus(ovc.getDateOfCurrentEnrollmentStatus());
                esh.setHivStatus(ovc.getCurrentHivStatus());
                esh.setDateOfHivStatus(ovc.getDateOfCurrentHivStatus());
            }
            //esh.setPointOfUpdateValue(ovc.getPointOfUpdate());
            esh.setRecordedBy(userName);
            esh=trackOvcService(ovc.getOvcId(),esh,serviceStartDate,serviceEndDate,userName);
            util.getEnrollmentStatusHistoryDaoInstance().saveEnrollmentStatusHistory(esh);
            
        }
    }
    public void saveNewOvcEnrollmentStatusInstance(Ovc ovc,int enrollmentstatus,String userName) throws Exception
    {
        EnrollmentStatusHistory esh=new EnrollmentStatusHistory();
        if(ovc !=null)
        {
            DaoUtility util=new DaoUtility();
            esh.setBeneficiaryId(ovc.getOvcId());
            esh.setBeneficiaryType(ovc.getBeneficiaryType());
            esh.setDateCreated(DateManager.getCurrentDateInstance());
            
            //esh.setDateOfNewStatus(ovc.getDateOfEnrollment());
            esh.setEnrolledOnTreatment(ovc.getEnrolledOnTreatment());
            esh.setCurrentAge(ovc.getCurrentAge());
            esh.setCurrentAgeUnit(ovc.getCurrentAgeUnit());
            esh.setFacilityId(ovc.getHivTreatmentFacilityId());
            esh.setLastModifiedDate(DateManager.getCurrentDateInstance());
            esh.setEnrollmentStatus(enrollmentstatus);
            esh.setDateOfEnrollmentStatus(ovc.getDateOfCurrentEnrollmentStatus());
            esh.setHivStatus(ovc.getCurrentHivStatus());
            esh.setDateOfHivStatus(ovc.getDateOfCurrentHivStatus());
            
            esh.setRecordedBy(userName);
            //util.getEnrollmentStatusHistoryDaoInstance().saveEnrollmentStatusHistory(esh);
            util.getChildEnrollmentDaoInstance().updateOvc(ovc, false, false);
        }
    }
    public void saveNewAdultMemberEnrollmentStatusInstance(AdultHouseholdMember ahm,int enrollmentstatus,String userName) throws Exception
    {
        EnrollmentStatusHistory esh=new EnrollmentStatusHistory();
        if(ahm !=null)
        {
            DaoUtility util=new DaoUtility();
            esh.setBeneficiaryId(ahm.getBeneficiaryId());
            esh.setBeneficiaryType(ahm.getBeneficiaryType());
            esh.setDateCreated(DateManager.getCurrentDateInstance());
            
            //esh.setDateOfNewStatus(ovc.getDateOfEnrollment());
            esh.setEnrolledOnTreatment(ahm.getEnrolledOnTreatment());
            esh.setCurrentAge(ahm.getCurrentAge());
            esh.setCurrentAgeUnit(AppConstant.AGEUNIT_YEAR_NUM);
            esh.setFacilityId(ahm.getHivTreatmentFacilityId());
            esh.setLastModifiedDate(DateManager.getCurrentDateInstance());
            esh.setEnrollmentStatus(enrollmentstatus);
            esh.setDateOfEnrollmentStatus(ahm.getDateOfCurrentEnrollmentStatus());
            esh.setHivStatus(ahm.getCurrentHivStatus());
            esh.setDateOfHivStatus(ahm.getDateOfCurrentHivStatus());
            
            esh.setRecordedBy(userName);
            //util.getEnrollmentStatusHistoryDaoInstance().saveEnrollmentStatusHistory(esh);
            util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
        }
    }
    public void saveAdultHouseholdMemberEnrollmentStatusHistory(AdultHouseholdMember ahm,int statusIndicator,String serviceStartDate, String serviceEndDate,String userName) throws Exception
    {
        EnrollmentStatusHistory esh=new EnrollmentStatusHistory();
        if(ahm !=null)
        {
            DaoUtility util=new DaoUtility();
            esh.setBeneficiaryId(ahm.getBeneficiaryId());
            esh.setBeneficiaryType(ahm.getBeneficiaryType());
            esh.setDateCreated(DateManager.getCurrentDateInstance());
            //esh.setDateOfNewStatus(ovc.getDateOfEnrollment());
            esh.setEnrolledOnTreatment(ahm.getEnrolledOnTreatment());
            esh.setFacilityId(ahm.getHivTreatmentFacilityId());
            esh.setLastModifiedDate(DateManager.getCurrentDateInstance());
            esh.setCurrentAge(ahm.getCurrentAge());
            esh.setCurrentAgeUnit(AppConstant.AGEUNIT_YEAR_NUM);
            if(statusIndicator==1)
            {
                esh.setEnrollmentStatus(AppConstant.ACTIVE_NUM);
                esh.setDateOfEnrollmentStatus(ahm.getDateOfEnrollment());
                esh.setHivStatus(ahm.getBaselineHivStatus());
                esh.setDateOfHivStatus(ahm.getDateOfEnrollment());
                esh.setCurrentAge(ahm.getAgeAtBaseline());
            }
            else if(statusIndicator==2)
            {
                if(ahm.getCurrentEnrollmentStatus()!=AppConstant.REENROLLED_NUM)
                return;
                esh.setEnrollmentStatus(AppConstant.INACTIVE_NUM);
                esh.setDateOfEnrollmentStatus(ahm.getDateOfCurrentHivStatus());
                esh.setHivStatus(ahm.getCurrentHivStatus());
                esh.setDateOfHivStatus(ahm.getDateOfCurrentHivStatus());
            }
            else if(statusIndicator==3)
            {
                esh.setEnrollmentStatus(ahm.getCurrentEnrollmentStatus());
                esh.setDateOfEnrollmentStatus(ahm.getDateOfCurrentEnrollmentStatus());
                esh.setHivStatus(ahm.getCurrentHivStatus());
                esh.setDateOfHivStatus(ahm.getDateOfCurrentHivStatus());
            }
            esh.setRecordedBy(userName);
            esh=trackAdultHouseholdMemberService(ahm.getBeneficiaryId(),esh,serviceStartDate, serviceEndDate,userName);
            util.getEnrollmentStatusHistoryDaoInstance().saveEnrollmentStatusHistory(esh);
        }
    }
    /*public void updateLastDateOfCurrentEnrollmentStatus()
    {
        try
        {
            DaoUtility util=new DaoUtility();
            String childQuery="update CHILDENROLLMENT set DATEOFCURRENTSTATUS='2019-03-31' where DATEOFCURRENTSTATUS < '2019-03-31'";
            String adultQuery="update ADULTHOUSEHOLDMEMBER set DATEOFCURRENTSTATUS='2019-03-31' where DATEOFCURRENTSTATUS < '2019-03-31'";
            util.updateDatabase(childQuery);
            util.updateDatabase(adultQuery);
            System.err.println("Date of current enrollment status updated");
        }
        catch(Exception ex)
        {
            System.err.println("Error: Unable to update Date of current enrollment status");
            ex.printStackTrace();
        }
    }*/
    public int updateQuarterlyEnrollmentStatusByServiceParameters(String userName,String beneficiaryId,int beneficiaryType,Date serviceDate)
    {
        int updated=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(beneficiaryType==AppConstant.OVC_TYPE_NUM)
            {
                Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
                if(ovc !=null)
                {
                    updateOvcQuarterlyEnrollmentStatus(userName,ovc,serviceDate);
                }
            }
            else if(beneficiaryType==AppConstant.CAREGIVER_TYPE_NUM)
            {
                AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
                if(ahm !=null)
                {
                    updateAdultMembersQuarterlyEnrollmentStatus(userName,ahm,serviceDate);
                }
            }
        }
        catch(Exception ex)
        {
            updated=-1;
            ex.printStackTrace();
        }
        return updated;   
    }
    public int updateOvcQuarterlyEnrollmentStatus(String userName,Ovc ovc,Date serviceDate)
    {
        int count=0;
        try
        {
            //defragmentEssentialTables();
            DaoUtility util=new DaoUtility();
            List reportQuarterServiceList=null;
            List precedingQuarterServiceList=null;
            int reportQuarterService=0;
            int precedingQuarterService=0;
            int totalQuarterlyServiceCount=0;
            int enrollmentStatus=0;
            Date currentDate=DateManager.getCurrentDateInstance();
            //Date serviceDate=service.getServiceDate();
            String startDate=DateManager.convertDateToString(serviceDate, DateManager.DB_DATE_FORMAT);
            FinancialYearManager fym=new FinancialYearManager();
            
            //get the start date and end date of selected report quarter
            String startDateOfReportQuarter=fym.getStartDateOfQuarter(startDate);
            String endDateOfReportQuarter=fym.getEndDateOfQuarter(startDate);
            Date dtEndDateOfReportQuarter=DateManager.getDateInstance(endDateOfReportQuarter);
            //get the start date and end date of preceding quarter
            String startDateOfPrecedingQuater=fym.getStartDateOfPreviousQuarter(startDate);
            String endDateOfPrecedingQuater=fym.getEndDateOfQuarter(startDateOfPrecedingQuater);
            //Ovc ovc=(Ovc)util.getChildEnrollmentDaoInstance().getOvc(service.getOvcId());
            //Check if child received service in the report quarter and the quarter before that.
            ChildService service=null;                              
            if(ovc !=null)
            {
                reportQuarterServiceList=util.getChildServiceDaoInstance().getServicesByPeriodPerChild(ovc.getOvcId(),startDateOfReportQuarter,endDateOfReportQuarter);
                precedingQuarterServiceList=util.getChildServiceDaoInstance().getServicesByPeriodPerChild(ovc.getOvcId(),startDateOfPrecedingQuater,endDateOfPrecedingQuater);
                if(reportQuarterServiceList !=null && !reportQuarterServiceList.isEmpty())
                {
                    //Child was served in the second of the two quarters that make up the report period
                    service=(ChildService)reportQuarterServiceList.get(0);
                    reportQuarterService=1;
                }
                if(precedingQuarterServiceList !=null && !precedingQuarterServiceList.isEmpty())
                {
                    //Child was served in the first of the two quarters that make up the report period
                    precedingQuarterService=1;
                    if(service==null)
                    service=(ChildService)precedingQuarterServiceList.get(0);
                }

                totalQuarterlyServiceCount=reportQuarterService+precedingQuarterService;
                if(reportQuarterService==1 && precedingQuarterService==1)
                {
                    enrollmentStatus=AppConstant.ACTIVE_NUM;
                    ovc=getOvcWithCurrentStatus(ovc, enrollmentStatus,service.getServiceDate());
                    //ovc.setCurrentEnrollmentStatus(enrollmentStatus);
                    //ovc.setDateOfCurrentEnrollmentStatus(service.getServiceDate());
                }
                else if(reportQuarterService==1 && precedingQuarterService==0)
                {
                    //here, beneficiary was served in the report quarter but not served in the previous quarter, check date of enrollment to determine if the beneficiary has spent 2 quarters since enrollment
                    //then return status accordingly
                    if(ovc.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ovc.getCurrentEnrollmentStatus()==AppConstant.INACTIVE_NUM || ovc.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
                    {
                        enrollmentStatus=getEnrollmentStatusForBeneficiaryServedInSecondQuarterOnly(ovc.getDateOfEnrollment(),DateManager.getDateInstance(endDateOfReportQuarter),currentDate,ovc.getCurrentEnrollmentStatus());
                    }
                }
                else if(reportQuarterService==0 && precedingQuarterService==1 && currentDate.after(dtEndDateOfReportQuarter))
                {
                    //here, beneficiary was served in the previous quarter but not served in the report quarter, beneficiary has become inactive
                    if(ovc.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ovc.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
                    {
                        enrollmentStatus=AppConstant.INACTIVE_NUM;
                    }
                }
                else if(totalQuarterlyServiceCount==0 && currentDate.after(dtEndDateOfReportQuarter))
                {
                    String strDateOfEnrollment=DateManager.convertDateToString(ovc.getDateOfEnrollment(), DateManager.DB_DATE_FORMAT);
                    String strReportEndDate=DateManager.convertDateToString(dtEndDateOfReportQuarter, DateManager.DB_DATE_FORMAT);
                    int monthDifference=DateManager.getDateDifferenceInMonths(strDateOfEnrollment, strReportEndDate);
                    //Change the status only if the beneficiary is active or re-enrolled and was enrolled more than 3 months before the end of the report period
                    if(ovc.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ovc.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
                    {
                        if(monthDifference>2)
                        enrollmentStatus=AppConstant.INACTIVE_NUM;
                    }
                }
                if(service !=null)
                {
                    Date firstDatimReportQuarterStatusDate=DateManager.getDateInstance("2019-03-31");
                    //service.getServiceDate().after(ovc.getDateOfCurrentEnrollmentStatus())  && 
                    if(enrollmentStatus>0)
                    {
                        //set the current enrollment status for this beneficiary
                        ovc=getOvcWithCurrentStatus(ovc, enrollmentStatus,service.getServiceDate());
                        
                    }
                    //The first status report was at the end FY 19 SAPR i.e March 2019, so all status that was set before this date should default to this date
                    if(ovc.getDateOfCurrentEnrollmentStatus().before(firstDatimReportQuarterStatusDate))
                    ovc.setDateOfCurrentEnrollmentStatus(firstDatimReportQuarterStatusDate);
                }
                if(enrollmentStatus==0)
                enrollmentStatus=ovc.getCurrentEnrollmentStatus();
                saveNewOvcEnrollmentStatusInstance(ovc,enrollmentStatus,userName);
                count++;
                //System.err.println("OVC with Id "+ovc.getOvcId()+" at "+count+" processed");
            }                       
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int updateAdultMembersQuarterlyEnrollmentStatus(String userName,AdultHouseholdMember ahm,Date serviceDate)
    {
        int count=0;
        try
        {
            //defragmentEssentialTables();
            DaoUtility util=new DaoUtility();
            List reportQuarterServiceList=null;
            List precedingQuarterServiceList=null;
            int reportQuarterService=0;
            int precedingQuarterService=0;
            int totalQuarterlyServiceCount=0;
            int enrollmentStatus=0;
            Date currentDate=DateManager.getCurrentDateInstance();
            //Date serviceDate=service.getServiceDate();
            String startDate=DateManager.convertDateToString(serviceDate, DateManager.DB_DATE_FORMAT);
            FinancialYearManager fym=new FinancialYearManager();
            
            //get the start date and end date of selected report quarter
            String startDateOfReportQuarter=fym.getStartDateOfQuarter(startDate);
            String endDateOfReportQuarter=fym.getEndDateOfQuarter(startDate);
            Date dtEndDateOfReportQuarter=DateManager.getDateInstance(endDateOfReportQuarter);
            //get the start date and end date of preceding quarter
            String startDateOfPrecedingQuater=fym.getStartDateOfPreviousQuarter(startDate);
            String endDateOfPrecedingQuater=fym.getEndDateOfQuarter(startDateOfPrecedingQuater);
            
            //Check if child received service in the report quarter and the quarter before that.
            HouseholdService hhs=null;                              
            if(ahm !=null)
            {
                reportQuarterServiceList=util.getHouseholdServiceDaoInstance().getServicesByPeriodPerBeneficiary(ahm.getBeneficiaryId(),startDateOfReportQuarter,endDateOfReportQuarter);
                precedingQuarterServiceList=util.getHouseholdServiceDaoInstance().getServicesByPeriodPerBeneficiary(ahm.getBeneficiaryId(),startDateOfPrecedingQuater,endDateOfPrecedingQuater);
                if(reportQuarterServiceList !=null && !reportQuarterServiceList.isEmpty())
                {
                    hhs=(HouseholdService)reportQuarterServiceList.get(0);
                    reportQuarterService=1;
                }
                if(precedingQuarterServiceList !=null && !precedingQuarterServiceList.isEmpty())
                {
                    precedingQuarterService=1;
                    if(hhs==null)
                    hhs=(HouseholdService)precedingQuarterServiceList.get(0);
                }
                totalQuarterlyServiceCount=reportQuarterService+precedingQuarterService;
                if(reportQuarterService==1 && precedingQuarterService==1)
                {
                    enrollmentStatus=AppConstant.ACTIVE_NUM;
                    ahm=getAdultMemberWithCurrentStatus(ahm, enrollmentStatus,hhs.getServiceDate());
                    //ahm.setCurrentEnrollmentStatus(enrollmentStatus);
                    //ahm.setDateOfCurrentEnrollmentStatus(hhs.getServiceDate());
                }
                else if(reportQuarterService==1 && precedingQuarterService==0)
                {
                    //here, beneficiary was served in the report quarter but not served in the previous quarter, check date of enrollment to determine if the beneficiary has spent 2 quarters since enrollment
                    //then return status accordingly
                    if(ahm.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ahm.getCurrentEnrollmentStatus()==AppConstant.INACTIVE_NUM || ahm.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
                    {
                        enrollmentStatus=getEnrollmentStatusForBeneficiaryServedInSecondQuarterOnly(ahm.getDateOfEnrollment(),DateManager.getDateInstance(endDateOfReportQuarter),currentDate,ahm.getCurrentEnrollmentStatus());
                    }
                }
                else if(reportQuarterService==0 && precedingQuarterService==1 && currentDate.after(dtEndDateOfReportQuarter))
                {
                    //here, beneficiary was served in the previous quarter but not served in the report quarter, beneficiary has become inactive
                    if(ahm.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ahm.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
                    {
                        enrollmentStatus=AppConstant.INACTIVE_NUM;
                    }
                }
                else if(totalQuarterlyServiceCount==0 && currentDate.after(dtEndDateOfReportQuarter))
                {
                    String strDateOfEnrollment=DateManager.convertDateToString(ahm.getDateOfEnrollment(), DateManager.DB_DATE_FORMAT);
                    String strReportEndDate=DateManager.convertDateToString(dtEndDateOfReportQuarter, DateManager.DB_DATE_FORMAT);
                    int monthDifference=DateManager.getDateDifferenceInMonths(strDateOfEnrollment, strReportEndDate);
                    //Change the status only if the beneficiary is active or re-enrolled and was enrolled more than 3 months before the end of the report period
                    if(ahm.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ahm.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
                    {
                        if(monthDifference>2)
                        {
                            enrollmentStatus=AppConstant.INACTIVE_NUM;
                            ahm.setDateOfCurrentEnrollmentStatus(dtEndDateOfReportQuarter);
                        }
                    }
                }
                if(hhs !=null)
                {
                    //hhs.getServiceDate().after(ahm.getDateOfCurrentEnrollmentStatus()) && 
                    if(enrollmentStatus>0)
                    {
                        //set the current enrollment status for this beneficiary
                        ahm=getAdultMemberWithCurrentStatus(ahm, enrollmentStatus,hhs.getServiceDate());
                        //ahm.setCurrentEnrollmentStatus(enrollmentStatus);
                        //ahm.setDateOfCurrentEnrollmentStatus(hhs.getServiceDate());
                    }
                    //The first status report was at the end FY 19 SAPR i.e March 2019, so all status that was set before this date should default to this date
                    Date firstDatimReportQuarterStatusDate=DateManager.getDateInstance("2019-03-31");
                    if(ahm.getDateOfCurrentEnrollmentStatus().before(firstDatimReportQuarterStatusDate))
                    ahm.setDateOfCurrentEnrollmentStatus(firstDatimReportQuarterStatusDate);
                }
                count++;
                if(enrollmentStatus==0)
                {
                    /*String strDateOfEnrollment=DateManager.convertDateToString(ahm.getDateOfEnrollment(), DateManager.DB_DATE_FORMAT);
                    String strReportEndDate=DateManager.convertDateToString(dtEndDateOfReportQuarter, DateManager.DB_DATE_FORMAT);
                    int monthDifference=DateManager.getDateDifferenceInMonths(strDateOfEnrollment, strReportEndDate);
                    */
                     enrollmentStatus=ahm.getCurrentEnrollmentStatus();
                }
                saveNewAdultMemberEnrollmentStatusInstance(ahm,enrollmentStatus,userName);
                //System.err.println("Adult member with Id "+ahm.getBeneficiaryId()+" at "+count+" processed");
            }                       
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
    public int updateOvcQuarterlyEnrollmentStatus(String userName,String startDate, String endDate)
    {
        int count=0;
        try
        {
            //defragmentEssentialTables();
            DaoUtility util=new DaoUtility();
            List level4OuIdList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
            if(level4OuIdList !=null)
            {
                String level4OuId=null;
                List childrenList=null;
                Ovc ovc=null;
                for(Object strObj:level4OuIdList)
                {
                    level4OuId=(String)strObj;
                    childrenList=util.getChildEnrollmentDaoInstance().getListOfOvcByLevel4OrganizationUnitByEnrollmentStatus(level4OuId,0);
                    if(childrenList !=null)
                    {
                        //Check if child received service in the report quarter and the quarter before that.
                        for(Object ovcObj:childrenList)
                        {
                            ovc=(Ovc)ovcObj;
                            updateOvcQuarterlyEnrollmentStatus(userName,ovc,DateManager.getDateInstance(startDate));
                            count++;
                            if(count==10000)
                            {
                                //defragmentEssentialTables();
                            }
                            System.err.println("OVC with id "+ovc.getOvcId()+" at "+count+" processed");
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
    public int updateAdultMembersQuarterlyEnrollmentStatus(String userName,String startDate, String endDate)
    {
        int count=0;
        try
        {
            //defragmentEssentialTables();
            DaoUtility util=new DaoUtility();
            List level4OuIdList=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel4OrganizationUnit();
            if(level4OuIdList !=null)
            {
                String level4OuId=null;
                List ahmList=null;
                AdultHouseholdMember ahm=null;
                for(Object strObj:level4OuIdList)
                {
                    level4OuId=(String)strObj;
                    ahmList=util.getAdultHouseholdMemberDaoInstance().getListOfAdultHouseholdMembersByLevel4OrganizationUnit(level4OuId);
                    if(ahmList !=null)
                    {
                        //Check if child received service in the report quarter and the quarter before that.
                        for(Object ovcObj:ahmList)
                        {
                            ahm=(AdultHouseholdMember)ovcObj;
                            updateAdultMembersQuarterlyEnrollmentStatus(userName,ahm,DateManager.getDateInstance(startDate)); 
                            count++;
                            System.err.println("Adult member with id "+ahm.getBeneficiaryId()+" at "+count+" processed");
                            /*if(count==10000)
                            {
                                defragmentEssentialTables();
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
    private Ovc getOvcWithCurrentStatus(Ovc ovc, int enrollmentStatus,Date dateOfNewStatus)
    {
        if(ovc !=null && dateOfNewStatus !=null)
        {
            if(ovc.getDateOfCurrentEnrollmentStatus().before(dateOfNewStatus) || ovc.getDateOfCurrentEnrollmentStatus().equals(dateOfNewStatus))
            {
                //Enrollment status of beneficiaries that are already out of the program are not updated here
                if(ovc.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ovc.getCurrentEnrollmentStatus()==AppConstant.INACTIVE_NUM || ovc.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
                {
                    ovc.setCurrentEnrollmentStatus(enrollmentStatus);
                    ovc.setDateOfCurrentEnrollmentStatus(dateOfNewStatus);
                }
            }
        }
        return ovc;
    }
    private AdultHouseholdMember getAdultMemberWithCurrentStatus(AdultHouseholdMember ahm, int enrollmentStatus,Date dateOfNewStatus)
    {
        if(ahm !=null && dateOfNewStatus !=null)
        {
            if(ahm.getDateOfCurrentEnrollmentStatus().before(dateOfNewStatus) || ahm.getDateOfCurrentEnrollmentStatus().equals(dateOfNewStatus))
            {
                //Enrollment status of beneficiaries that are already out of the program are not updated here
                if(ahm.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ahm.getCurrentEnrollmentStatus()==AppConstant.INACTIVE_NUM || ahm.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
                {
                    ahm.setCurrentEnrollmentStatus(enrollmentStatus);
                    ahm.setDateOfCurrentEnrollmentStatus(dateOfNewStatus);
                }
            }
        }
        return ahm;
    }
    
    public int getEnrollmentStatusForBeneficiaryServedInSecondQuarterOnly(Date dateOfEnrollment,Date reportEndDate, Date currentDate,int currentEnrollmentStatus)
    {
        int enrollmentStatus=currentEnrollmentStatus;
        String strDateOfEnrollment=DateManager.convertDateToString(dateOfEnrollment, DateManager.DB_DATE_FORMAT);
        String strReportEndDate=DateManager.convertDateToString(reportEndDate, DateManager.DB_DATE_FORMAT);
        int monthDifference=DateManager.getDateDifferenceInMonths(strDateOfEnrollment, strReportEndDate);
        if(monthDifference<5)
        {
            enrollmentStatus=AppConstant.ACTIVE_NUM;
        }
        else
        {
            if(currentDate.after(reportEndDate))
            enrollmentStatus=AppConstant.INACTIVE_NUM;
        }
        return enrollmentStatus;
    }
    public EnrollmentStatusHistory trackOvcService(String beneficiaryId,EnrollmentStatusHistory esh,String startServiceDate,String endServiceDate,String userName)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List serviceList=util.getChildServiceDaoInstance().getServicesByPeriodPerChild(beneficiaryId, startServiceDate, endServiceDate);
            //.childServedInReportPeriod(ovc.getOvcId(), startServiceDate, endServiceDate);
            if(serviceList !=null && !serviceList.isEmpty())
            {
               //The result is ordered desc, hence the first service is the last in the list
                //ChildService firstService=(ChildService)serviceList.get(serviceList.size()-1);
                ChildService firstService=(ChildService)serviceList.get(0);
                esh.setDateOfEnrollmentStatus(firstService.getServiceDate());
                updateQuarterlyEnrollmentStatusByServiceParameters(userName,beneficiaryId,AppConstant.OVC_TYPE_NUM,firstService.getServiceDate());
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return esh;
    }
    public EnrollmentStatusHistory trackAdultHouseholdMemberService(String beneficiaryId,EnrollmentStatusHistory esh,String startServiceDate,String endServiceDate,String userName)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            List serviceList=util.getHouseholdServiceDaoInstance().getServicesByPeriodPerBeneficiary(beneficiaryId, startServiceDate, endServiceDate);
            //.childServedInReportPeriod(ovc.getOvcId(), startServiceDate, endServiceDate);
            if(serviceList !=null && !serviceList.isEmpty())
            {
               //The result is ordered desc, hence the first service is the last in the list
                //HouseholdService firstService=(HouseholdService)serviceList.get(serviceList.size()-1);
                HouseholdService firstService=(HouseholdService)serviceList.get(0);
                esh.setDateOfEnrollmentStatus(firstService.getServiceDate());
                updateQuarterlyEnrollmentStatusByServiceParameters(userName,beneficiaryId,AppConstant.CAREGIVER_TYPE_NUM,firstService.getServiceDate());
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return esh;
    }
    public boolean isActiveInReportPeriod(String beneficiaryId,Date dateOfStatus,int beneficiartType)
    {
        boolean beneficiaryIsActive=false;
        FinancialYearManager fym=new FinancialYearManager();
        String strDate=DateManager.convertDateToString(dateOfStatus, DateManager.DB_DATE_FORMAT);
        try
        {
            String startDateOfPreviousQuarter=fym.getStartDateOfPreviousQuarter(strDate);
            String endDateOfPreviousQuarter=fym.getEndDateOfPreviousQuarter(strDate);
            String startDateOfCurrentQuarter=fym.getStartDateOfQuarter(strDate);
            String endDateOfCurrentQuarter=fym.getEndDateOfQuarter(strDate);

            DaoUtility util=new DaoUtility();
            if(beneficiartType==AppConstant.OVC_TYPE_NUM)
            {
                List previousQtrServiceList=util.getChildServiceDaoInstance().getServicesByPeriodPerChild(beneficiaryId, startDateOfPreviousQuarter, endDateOfPreviousQuarter);
                List currentQtrServiceList=util.getChildServiceDaoInstance().getServicesByPeriodPerChild(beneficiaryId, startDateOfCurrentQuarter, endDateOfCurrentQuarter);

                if(previousQtrServiceList !=null && !previousQtrServiceList.isEmpty() && currentQtrServiceList !=null && !currentQtrServiceList.isEmpty())
                beneficiaryIsActive=true;
                else
                {
                    Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
                    if(ovc !=null)
                    {
                        Date dStartDateOfCurrentQuarter=DateManager.getDateInstance(startDateOfCurrentQuarter);
                        //check if beneficiary was enrolled in current quarter
                        if(ovc.getDateOfEnrollment().equals(dStartDateOfCurrentQuarter) || ovc.getDateOfEnrollment().after(dStartDateOfCurrentQuarter))
                        beneficiaryIsActive=true;
                    }
                }
            }
            else if(beneficiartType==AppConstant.CAREGIVER_TYPE_NUM)
            {
                List previousQtrServiceList=util.getHouseholdServiceDaoInstance().getServicesByPeriodPerBeneficiary(beneficiaryId, startDateOfPreviousQuarter, endDateOfPreviousQuarter);
                List currentQtrServiceList=util.getHouseholdServiceDaoInstance().getServicesByPeriodPerBeneficiary(beneficiaryId, startDateOfCurrentQuarter, endDateOfCurrentQuarter);

                if(previousQtrServiceList !=null && !previousQtrServiceList.isEmpty() && currentQtrServiceList !=null && !currentQtrServiceList.isEmpty())
                beneficiaryIsActive=true;
                else
                {
                    AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
                    if(ahm !=null)
                    {
                        Date dStartDateOfCurrentQuarter=DateManager.getDateInstance(startDateOfCurrentQuarter);
                        //check if beneficiary was enrolled in current quarter
                        if(ahm.getDateOfEnrollment().equals(dStartDateOfCurrentQuarter) || ahm.getDateOfEnrollment().after(dStartDateOfCurrentQuarter))
                        beneficiaryIsActive=true;
                    }
                }
            }
            //System.err.println("startDateOfPreviousQuarter is "+startDateOfPreviousQuarter);
            //System.err.println("endDateOfPreviousQuarter is "+endDateOfPreviousQuarter);
            //System.err.println("startDateOfCurrentQuarter is "+startDateOfCurrentQuarter);
            //System.err.println("endDateOfCurrentQuarter is "+endDateOfCurrentQuarter);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return beneficiaryIsActive;
    }
}