/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;


import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.business.CareAndSupportChecklist;
import com.nomis.ovc.business.CommunityWorker;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.NutritionStatus;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.ReferralFacility;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author smomoh
 */
public class BeneficiaryManager 
{
    public static String getEnrollmentSettingName(int enrollmentSetting)
    {
        if(enrollmentSetting==AppConstant.ENROLLMENTSETTING_FACILITY_NUM)
        return AppConstant.FACILITY_NAME;
        else
        return AppConstant.COMMUNITY_NAME;
    }
    public static NutritionStatus getNutritionStatus(Ovc ovc)
    {
        NutritionStatus ns=null;
        DaoUtility util=new DaoUtility();
        try
        {
            if(ovc !=null)
            ns=util.getNutritionStatusDaoInstance().getNutritionStatus(ovc.getOvcId());
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        if(ns==null)
        ns=new NutritionStatus();
        if(ns.getHeight()<1)
        ns.setHeight(ovc.getHeight());
        if(ns.getWeight()<1)
        ns.setWeight(ovc.getWeight());
        return ns;
    }
    public static int getViralLoad(String beneficiaryId,int currentHivStatusCode,int enrolledOnTreatment)
    {
        int viralLoad=0;
        try
        {
            if(currentHivStatusCode==AppConstant.HIV_POSITIVE_NUM && enrolledOnTreatment==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            {
                DaoUtility util=new DaoUtility();
                CareAndSupportChecklist casc=null;
                List list=util.getCareAndSupportChecklistDaoInstance().getCareAndSupportChecklist(beneficiaryId);
                if(list !=null && !list.isEmpty())
                {
                    casc=(CareAndSupportChecklist)list.get(0);
                    viralLoad=casc.getViralLoadResult();
                }
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex); 
        }
        return viralLoad;
    }
    public static String getTreatmentId(String treatmentId,int currentHivStatusCode,int enrolledOnTreatment)
    {
        try
        {
            if(currentHivStatusCode !=AppConstant.HIV_POSITIVE_NUM || enrolledOnTreatment!=AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
            {
                treatmentId=null;
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex); 
        }
        return treatmentId;
    }
    public static CommunityWorker getCommunityWorker(String communityWorkerId)
    {
        CommunityWorker communityWorker=null;
        try
        {
            DaoUtility util=new DaoUtility();
            communityWorker=util.getCommunityWorkerDaoInstance().getCommunityWorker(communityWorkerId);
            if(communityWorker==null)
            communityWorker=new CommunityWorker();
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return communityWorker;
    }
    
    /*public static School getSchool(String schoolId)
    {
        School school=null;
        try
        {
            System.err.println("schoolId is "+schoolId);
          DaoUtility util=new DaoUtility();
          school=util.getSchoolDaoInstance().getSchool(schoolId);
          if(school ==null)
          school=new School(); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return school;
    }*/
    public static ReferralFacility getReferralFacility(String facilityId)
    {
        ReferralFacility facility=null;
        try
        {
            System.err.println("facilityId is "+facilityId);
          DaoUtility util=new DaoUtility();
          facility=util.getReferralFacilityDaoInstance().getReferralFacility(facilityId);
          if(facility ==null)
          facility=new ReferralFacility(); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return facility;
    }
    public static List getHouseholdMembers(String hhUniqueId)
    {
        List list=new ArrayList();
        try
        {
          DaoUtility util=new DaoUtility();
          List ahmList=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMembersPerHousehold(hhUniqueId);
          if(ahmList !=null)
          list.addAll(ahmList);
          List ovcList=util.getChildEnrollmentDaoInstance().getOvcPerHousehold(hhUniqueId);
          if(ovcList !=null)
          list.addAll(ovcList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    /*public static GenderBasedViolence getGenderBasedViolence(String beneficiaryId)
    {
        GenderBasedViolence gbv=null;
        try
        {
          DaoUtility util=new DaoUtility();  
          gbv=util.getGenderBasedViolenceDaoInstance().getGenderBasedViolence(beneficiaryId);
          if(gbv==null)
          gbv=new GenderBasedViolence();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return gbv;
    }*/
    public static Beneficiary getBeneficiary(String beneficiaryId)
    {
        Beneficiary beneficiary=null;
        try
        {
          DaoUtility util=new DaoUtility();  
          beneficiary=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
          if(beneficiary==null)
          {
              beneficiary=util.getChildEnrollmentDaoInstance().getOvc(beneficiaryId);
          }
          if(beneficiary==null)
          beneficiary=new AdultHouseholdMember();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return beneficiary;
    }
    public static AdultHouseholdMember getAdultHouseholdMember(String beneficiaryId)
    {
        AdultHouseholdMember ahm=null;
        try
        {
          DaoUtility util=new DaoUtility();  
          ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(beneficiaryId);
          if(ahm==null)
          ahm=new AdultHouseholdMember();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ahm;
    }
    public static HouseholdEnrollment getHouseholdEnrollment(String hhUniqueId)
    {
        HouseholdEnrollment hhe=null;
        try
        {
          DaoUtility util=new DaoUtility();  
          hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
          if(hhe==null)
          hhe=new HouseholdEnrollment();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return hhe;
    }
    
    public static Ovc getOvc(String ovcId)
    {
        Ovc ovc=null;
        try
        {
          DaoUtility util=new DaoUtility();  
          ovc=util.getChildEnrollmentDaoInstance().getOvc(ovcId);
          if(ovc==null)
          ovc=new Ovc();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ovc;
    }
    public static boolean isCurrentlyEnrolled(int currentEnrollmentStatus)
    {
        if(currentEnrollmentStatus==AppConstant.ACTIVE_NUM || currentEnrollmentStatus==AppConstant.REENROLLED_NUM)
        return true;
        else
        return false;
    }
    public void displayBeneficiaryBiodata(HttpServletRequest request, User user)
    {
        if(user.getViewClientDetails() !=null && user.getViewClientDetails().equalsIgnoreCase("yes"))
        {
            request.setAttribute("viewDetails", user);
        }
    }
}
