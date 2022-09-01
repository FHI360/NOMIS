/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.business.EnrollmentStatusHistory;
import com.nomis.ovc.business.Ovc;
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
public class ChildrenEnrollmentStatusUpdateThread extends Thread
{
    String level4OuId;
    String userName;
    String serviceStartDate;
    String serviceEndDate;
    public ChildrenEnrollmentStatusUpdateThread(String level4OuId,String serviceStartDate,String serviceEndDate,String userName)
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
                List list=util.getChildServiceDaoInstance().getServiceRecordsByCommunity(level4OuId);
                for(Object sobj:list)
                {
                    ChildService service=(ChildService)sobj;
                    processChildrenEnrollmentStatusByChildId(service.getOvcId(),DateManager.convertDateToString(service.getServiceDate(), DateManager.DB_DATE_FORMAT), userName);
                    AppUtility.enrollmentStatusDataGeneratorCounter++;
                }
            }
            else
            {
                List childrenList=util.getChildEnrollmentDaoInstance().getListOfOvcByLevel4OrganizationUnit(level4OuId);
                processChildrenEnrollmentStatus(childrenList,serviceStartDate,serviceEndDate,userName);
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
                   saveOvcEnrollmentStatusHistory(ovc,1,serviceStartDate,serviceEndDate,userName);
                   saveOvcEnrollmentStatusHistory(ovc,2,serviceStartDate,serviceEndDate,userName);
                   saveOvcEnrollmentStatusHistory(ovc,3,serviceStartDate,serviceEndDate,userName);
                   count++;
                   if(count==10000)
                   {
                       //defragmentEssentialTables();
                   }
                   //System.err.println(count+" Children enrollment status saved to history");
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
    public void saveOvcEnrollmentStatusHistory(Ovc ovc,int statusIndicator,String serviceStartDate,String serviceEndDate,String userName) throws Exception
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
            if(statusIndicator==1)
            {
                //Baseline enrollment status
                esh.setEnrollmentStatus(AppConstant.ACTIVE_NUM);
                esh.setCurrentAge(ovc.getAgeAtBaseline());
                esh.setDateOfEnrollmentStatus(ovc.getDateOfEnrollment());
                esh.setHivStatus(ovc.getBaselineHivStatus());
                esh.setDateOfHivStatus(ovc.getDateOfEnrollment());
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
    private Ovc getOvcWithCurrentStatus(Ovc ovc, int enrollmentStatus,Date dateOfNewStatus)
    {
        if(ovc !=null && dateOfNewStatus !=null)
        {
            if(ovc.getCurrentEnrollmentStatus()==AppConstant.ACTIVE_NUM || ovc.getCurrentEnrollmentStatus()==AppConstant.INACTIVE_NUM || ovc.getCurrentEnrollmentStatus()==AppConstant.REENROLLED_NUM)
            {
                if(ovc.getDateOfCurrentEnrollmentStatus().before(dateOfNewStatus) || ovc.getDateOfCurrentEnrollmentStatus().equals(dateOfNewStatus))
                {
                    ovc.setCurrentEnrollmentStatus(enrollmentStatus);
                    ovc.setDateOfCurrentEnrollmentStatus(dateOfNewStatus);
                }
            }
        }
        return ovc;
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
