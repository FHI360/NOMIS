/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.FacilityOvcOffer;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.dao.DaoUtility;
import java.util.Date;



/**
 *
 * @author smomoh
 */
public class ValidationManager 
{
    DaoUtility util=new DaoUtility();
  public FacilityOvcOffer getFacilityOvcOfferByTreatmentId(String treatmentId)
  {
      FacilityOvcOffer foo=null;
      try
      {
          foo=util.getFacilityOvcOfferDaoInstance().getFacilityOvcOfferByTreatmentId(treatmentId);
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return foo;
  }
  public boolean isTreatmentIdAvailable(String treatmentId,String clientUniqueId)
  {
      boolean treatmentIdAvailable=false;
      DaoUtility util=new DaoUtility();
      try
      {
          FacilityOvcOffer foo=util.getFacilityOvcOfferDaoInstance().getFacilityOvcOfferByTreatmentId(treatmentId);
          if(foo==null)
          treatmentIdAvailable=true;
          else 
          {
              if(clientUniqueId==null)
              treatmentIdAvailable=false;
              else if(clientUniqueId.equalsIgnoreCase(foo.getClientUniqueId()))
              treatmentIdAvailable=true;
              else
              treatmentIdAvailable=false;
          }
      }
      catch(Exception ex)
      {
         ex.printStackTrace(); 
      }
      return treatmentIdAvailable;
  }
  public boolean isOvcOfferDateBeforeDateOfEnrollment(Date ovcOfferDate,Date enrollmentDate)
  {
      boolean ovcOfferDateBeforeDateOfEnrollment=true;
      try
      {
          if(ovcOfferDate ==null || enrollmentDate ==null || ovcOfferDate.after(enrollmentDate))
          ovcOfferDateBeforeDateOfEnrollment=false;
      }
      catch(Exception ex)
      {
         ex.printStackTrace(); 
      }
      return ovcOfferDateBeforeDateOfEnrollment;
  }
  public AgeObject getAgeObject(String beneficiaryId,String dateOfAssessment,int beneficiaryType)
  {
      AgeObject ageObj=new AgeObject();
      try
      {
          if(dateOfAssessment !=null)
          {
              if(dateOfAssessment.indexOf("/") !=-1)
              dateOfAssessment=DateManager.processMthDayYearToMysqlFormat(dateOfAssessment);
              DaoUtility util=new DaoUtility();
              if(beneficiaryType==AppConstant.OVC_TYPE_NUM)
              {
                  AppManager appManager=new AppManager();
                    Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
                    if(ovc !=null)
                    {
                        ageObj=appManager.getAgeAtService(DateManager.convertDateToString(ovc.getDateOfEnrollment(),DateManager.DB_DATE_FORMAT), dateOfAssessment,ovc.getAgeAtBaseline(), ovc.getAgeUnitAtBaseline());
                    }
              }
              else
              {

              }
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return ageObj;
  }
  public static int getAgeAtEnrollment(String dateOfBirth,String dateOfEnrollment)
  {
      int ageAtBaseline=0;          
    int age=AppManager.getAgeAtBaseline(dateOfBirth, dateOfEnrollment);
    int ageUnitAtBaseline=AppManager.getBaselineAgeUnit(dateOfBirth, dateOfEnrollment);
    ageAtBaseline=age;
    //an ageunit of 1 implies the age is less than 1 year, that is in months
      if(ageUnitAtBaseline==1)
      {
          ageAtBaseline=0;
      }
      return ageAtBaseline;
  }
  public static boolean dateBeforeOrEqualToCurrentDate(String mthDayYear)
  {
      Date userDate=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(mthDayYear));
      return DateManager.isFirstDateBeforeOrEqualToSecondDate(userDate, DateManager.getCurrentDateInstance());
  }
  public static boolean isDateEnrolledOnTreatmentBeforeDateOfHivStatus(String dateOfHivStatus,String dateEnrolledOnTreatment)
  {
      try
      {
          if(dateOfHivStatus !=null && dateOfHivStatus.indexOf("/") !=-1)
          {
            Date dDateOfHivStatus=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(dateOfHivStatus));
            Date dDateEnrolledOnTreatment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(dateEnrolledOnTreatment));
            return DateManager.compareDates(dDateOfHivStatus, dDateEnrolledOnTreatment);//.compareDates(DateManager.convertDateToString(dDateOfHivStatus, DateManager.DB_DATE_FORMAT),DateManager.processMthDayYearToMysqlFormat(dateEnrolledOnTreatment));
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return false;//DateManager.compareDates(DateManager.processMthDayYearToMysqlFormat(mthDayYear), DateManager.getCurrentDate());
  }
  public static boolean isDateAfterEnrollmentDate(String dateOfEnrollment,String mthDayYear)
  {
      try
      {
          if(dateOfEnrollment !=null && dateOfEnrollment.indexOf("/") !=-1)
          {
              Date dDateOfEnrollment=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(dateOfEnrollment));
              Date dSecondDate=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(mthDayYear));
            return DateManager.isFirstDateBeforeOrEqualToSecondDate(dDateOfEnrollment,dSecondDate);
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return false;//DateManager.compareDates(DateManager.processMthDayYearToMysqlFormat(mthDayYear), DateManager.getCurrentDate());
  }
  public static Beneficiary getBeneficiary(String beneficiaryId, int beneficiaryType)
  {
      Beneficiary beneficiary=null;
      try
      {
          DaoUtility util=new DaoUtility();
          if(beneficiaryType==AppConstant.OVC_TYPE_NUM)
          beneficiary=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
          else
          beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return beneficiary;
  }
  public Beneficiary getBeneficiaryByTreatmentId(String treatmentId)
  {
      Beneficiary beneficiary=null;
      try
      {
          DaoUtility util=new DaoUtility();
          beneficiary=util.getChildEnrollmentDaoInstance().getOvcByTreatmentId(treatmentId);
          if(beneficiary==null)
          beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMemberByTreatmentId(treatmentId);
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return beneficiary;
  }
  public static Beneficiary getBeneficiaryByName(String hhUniqueId, String firstName, String surname,int beneficiaryType)
  {
      Beneficiary beneficiary=null;
      try
      {
          DaoUtility util=new DaoUtility();
          if(beneficiaryType==AppConstant.OVC_TYPE_NUM)
          beneficiary=util.getChildEnrollmentDaoInstance().getOvcByName(hhUniqueId, firstName, surname);
          else
          {
            beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMemberByName(hhUniqueId, firstName, surname);
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return beneficiary;
  }
  public static boolean dateAfterEnrollmentDate(String beneficiaryId,String mthDayYear,int beneficiaryType)
  {
      try
      {
          DaoUtility util=new DaoUtility();
          if(beneficiaryType==AppConstant.HOUSEHOLD_TYPE_NUM)
          {
              HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(beneficiaryId);
              if(hhe !=null)
              {
                  return DateManager.compareDates(DateManager.convertDateToString(hhe.getDateOfAssessment(), DateManager.DB_DATE_FORMAT),DateManager.processMthDayYearToMysqlFormat(mthDayYear));
              }
          }
          if(beneficiaryType==AppConstant.CAREGIVER_TYPE_NUM)
          {
              AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
              if(ahm !=null)
              {
                  return DateManager.compareDates(DateManager.convertDateToString(ahm.getDateOfEnrollment(), DateManager.DB_DATE_FORMAT),DateManager.processMthDayYearToMysqlFormat(mthDayYear));
              }
          }
          else if(beneficiaryType==AppConstant.OVC_TYPE_NUM)
          {
              Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
              if(ovc !=null)
              {
                  return DateManager.compareDates(DateManager.convertDateToString(ovc.getDateOfEnrollment(), DateManager.DB_DATE_FORMAT),DateManager.processMthDayYearToMysqlFormat(mthDayYear));
              }
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return false;//DateManager.compareDates(DateManager.processMthDayYearToMysqlFormat(mthDayYear), DateManager.getCurrentDate());
  }
  public static boolean dateAfterOrEqualEnrollmentDate(String beneficiaryId,String mthDayYear)
  {
      try
      {
          DaoUtility util=new DaoUtility();
          
          HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(beneficiaryId);
          Date dateObj=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(mthDayYear));
          if(hhe !=null)
          {
              return DateManager.isFirstDateBeforeOrEqualToSecondDate(hhe.getDateOfAssessment(),dateObj);
          }
          else
          {
              AdultHouseholdMember ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
              if(ahm !=null)
              {
                  return DateManager.isFirstDateBeforeOrEqualToSecondDate(ahm.getDateOfEnrollment(),dateObj);
              }
              else
              {
                  Ovc ovc=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
                  if(ovc !=null)
                  {
                      return DateManager.isFirstDateBeforeOrEqualToSecondDate(ovc.getDateOfEnrollment(),dateObj);
                  }
              }
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return false;//DateManager.compareDates(DateManager.processMthDayYearToMysqlFormat(mthDayYear), DateManager.getCurrentDate());
  }
  
 public static boolean isValidDate(String date)
 {
     boolean isvalid=false;
     if(date !=null)
     {
         if(date.indexOf("/") !=-1)
         {
            String[] dateArray=date.split("/");
            if(dateArray.length == 3)
            isvalid=true;
          }
     }
     return isvalid;
 }
}
