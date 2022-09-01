/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;


import com.nomis.ovc.business.AgeObject;
import com.nomis.ovc.util.AppManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class OvcServiceManager implements Serializable
{
    
    public String removeDuplicateServices(String concatenatedService)
    {
        String concatenatedUniqueServices=concatenatedService;
        if(concatenatedService !=null && concatenatedService.indexOf(",") !=-1)
        {
            concatenatedUniqueServices="";
            String[] concatenatedServices=concatenatedService.split(",");
            if(concatenatedServices !=null)
            {
                List uniqueItemList=new ArrayList();
                String serviceCode=null;
                for(int i=0; i<concatenatedServices.length; i++)
                {
                    serviceCode=concatenatedServices[i];
                    if(serviceCode !=null)
                    {
                        serviceCode=serviceCode.replace(",", "");
                        if(!uniqueItemList.contains(serviceCode))
                        {
                            if(i==0)
                            concatenatedUniqueServices=serviceCode;
                            else
                            concatenatedUniqueServices=","+serviceCode; 
                            uniqueItemList.add(serviceCode);
                        }
                    }
                }
            }
        }
        if(concatenatedUniqueServices !=null)
        {
            concatenatedUniqueServices=concatenatedUniqueServices.trim();
            if(concatenatedUniqueServices.startsWith(","))
            concatenatedUniqueServices=concatenatedUniqueServices.substring(1);
            if(concatenatedUniqueServices.endsWith(","))
            concatenatedUniqueServices=concatenatedUniqueServices.substring(0,concatenatedUniqueServices.length()-1); 
        }
        return concatenatedUniqueServices;
    }
    public AgeObject getAgeAtService(Date dateOfEnrollment,Date serviceDate,int ageAtEnrollment,int ageUnitAtEnrollment) throws Exception
    {
        AppManager appManager=new AppManager();
        AgeObject ageObj=appManager.getAgeAtService(dateOfEnrollment,serviceDate,ageAtEnrollment,ageUnitAtEnrollment);
        return ageObj;
    }
    /*public int getAgeAtService(Date dateOfBirth, Date serviceDate) throws Exception
    {
        int ageAtService=AppManager.getAgeInYearsFromDates(dateOfBirth, serviceDate);
        return ageAtService;
    }*/
    /*public void deleteGBVServiceForOvcBeneficiaries(GBVService gbvs)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            ChildService service=util.getOvcServiceDaoInstance().getOvcService(gbvs.getBeneficiaryId(), gbvs.getServiceDate());
            if(service !=null)
            deleteGBVServiceInOvcService(service);
            else
            {
                CaregiverService hhs=util.getHouseholdServiceDaoInstance().getHouseholdService(gbvs.getBeneficiaryId(), gbvs.getServiceDate());
                if(hhs !=null)
                deleteGBVServiceInHouseholdService(hhs);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void deleteGBVServiceInOvcService(OvcService service) throws Exception
    {
            if(service !=null)
            {
                DaoUtility util=new DaoUtility();
                if(service.getGbvServices() !=null)
                service.setGbvServices(null);
                if((service.getHealthServices()==null || service.getHealthServices().trim().length()==0)
                && (service.getSafetyServices()==null || service.getSafetyServices().trim().length()==0)
                && (service.getSchooledServices()==null || service.getSchooledServices().trim().length()==0)
                && (service.getStableServices()==null || service.getStableServices().trim().length()==0)
                && (service.getReferralServices()==null || service.getReferralServices().trim().length()==0)
                )
                util.getOvcServiceDaoInstance().deleteService(service);
                else
                util.getOvcServiceDaoInstance().updateOvcService(service, false);
            }
        
    }
    public void deleteGBVServiceInHouseholdService(HouseholdService service) throws Exception
    {
            if(service !=null)
            {
                DaoUtility util=new DaoUtility();
                if(service.getGbvServices() !=null)
                service.setGbvServices(null);
                if((service.getHealthServices()==null || service.getHealthServices().trim().length()==0)
                && (service.getSafetyServices()==null || service.getSafetyServices().trim().length()==0)
                && (service.getStableServices()==null || service.getStableServices().trim().length()==0)
                && (service.getReferralServices()==null || service.getReferralServices().trim().length()==0)
                )
                util.getHouseholdServiceDaoInstance().deleteHouseholdService(service);
                else
                util.getHouseholdServiceDaoInstance().updateHouseholdService(service, false);
            }
        
    }
    public GBVService getGBVService(String beneficiaryId,Date date)
    {
        GBVService gbvs=null;
        try
        {
            DaoUtility util=new DaoUtility();
            gbvs=util.getGBVServiceDaoInstance().getGBVService(beneficiaryId, date);
            if(gbvs==null)
            gbvs=new GBVService();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return gbvs;
    }*/
}
