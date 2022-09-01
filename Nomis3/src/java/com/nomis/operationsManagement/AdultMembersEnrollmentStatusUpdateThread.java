/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.EnrollmentStatusHistory;
import com.nomis.ovc.business.HouseholdService;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class AdultMembersEnrollmentStatusUpdateThread extends Thread
{
    String level4OuId;
    String userName;
    String serviceStartDate;
    String serviceEndDate;
    public AdultMembersEnrollmentStatusUpdateThread(String level4OuId,String serviceStartDate,String serviceEndDate,String userName)
    {
        this.level4OuId=level4OuId;
        this.userName=userName;
        this.serviceStartDate=serviceStartDate;
        this.serviceEndDate=serviceEndDate;
    }
    public void run()
    {
        try
        {
            AppUtility.enrollmentStatusUpdateThreadCounter++;
            DaoUtility util=new DaoUtility();
            if((serviceStartDate==null || serviceStartDate.indexOf("-")==-1) || (serviceEndDate==null || serviceEndDate.indexOf("-")==-1))
            {
                List list=util.getHouseholdServiceDaoInstance().getServiceRecordsByCommunity(level4OuId);
                for(Object sobj:list)
                {
                    HouseholdService service=(HouseholdService)sobj;
                    processAdultHouseholdMemberEnrollmentStatusByBeneficiaryId(service.getBeneficiaryId(),DateManager.convertDateToString(service.getServiceDate(), DateManager.DB_DATE_FORMAT), userName);
                    AppUtility.enrollmentStatusDataGeneratorCounter++;
                }
            }
            else
            {
                List adultList=util.getAdultHouseholdMemberDaoInstance().getListOfAdultHouseholdMembersByLevel4OrganizationUnit(level4OuId);
                if(adultList !=null)
                {
                    processAdultHouseholdMemberEnrollmentStatus(adultList,serviceStartDate, serviceEndDate,userName);

                }
            }
            AppUtility.enrollmentStatusUpdateThreadCounter--;
            System.err.println(AppUtility.enrollmentStatusUpdateThreadCounter+" enrollment status threads left");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
            this.finalize();
            }
            catch(Throwable tw)
            {
                tw.printStackTrace();
            }
        }
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
                   saveAdultHouseholdMemberEnrollmentStatusHistory(ahm,1,serviceStartDate, serviceEndDate,userName);
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
    public int updateQuarterlyEnrollmentStatusByServiceParameters(String userName,String beneficiaryId,int beneficiaryType,Date serviceDate)
    {
        int updated=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(beneficiaryType==AppConstant.CAREGIVER_TYPE_NUM)
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
    private AdultHouseholdMember getAdultMemberWithCurrentStatus(AdultHouseholdMember ahm, int enrollmentStatus,Date dateOfNewStatus)
    {
        if(ahm !=null && dateOfNewStatus !=null)
        {
            if(ahm.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ahm.getCurrentEnrollmentStatus()==AppConstant.INACTIVE_NUM || ahm.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
            {
                if(ahm.getDateOfCurrentEnrollmentStatus().before(dateOfNewStatus) || ahm.getDateOfCurrentEnrollmentStatus().equals(dateOfNewStatus))
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
}
