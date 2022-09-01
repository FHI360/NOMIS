/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;



/**
 *
 * @author smomoh
 */
public class UniqueIdManager 
{
    public String generateHouseholdServiceUniqueId(String beneficiaryId,String serviceDate)
    {
        String uniqueId=null;
        try
        {
            if(beneficiaryId !=null && serviceDate !=null)
            {
                if(serviceDate.trim().length()>9)
                {
                    if(serviceDate.indexOf("/") !=-1)
                    {
                        String[] serviceDateArray=serviceDate.split("/");
                        serviceDate=serviceDateArray[1]+serviceDateArray[0]+serviceDateArray[2];
                    }
                    else if(serviceDate.indexOf("-") !=-1)
                    {
                        String[] serviceDateArray=serviceDate.split("-");
                        serviceDate=serviceDateArray[2]+serviceDateArray[1]+serviceDateArray[0];
                    }
                    serviceDate=serviceDate.trim();
                    uniqueId=beneficiaryId+serviceDate;
                }
            }
            if(uniqueId !=null)
            uniqueId=uniqueId.toUpperCase();
            System.err.println("serviceId is "+uniqueId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public String generateHouseholdUniqueIdByDistrictName(String firstName,String surname,String districtName,String dateOfBirth,String sex)
    {
        String uniqueId="";
        try
        {
            String firstNamePart=null;
            String surnamePart=null;
            String organizationUnitPart=null;
            String dobPart=null;
            String sexPart=null;
            AppUtility util=new AppUtility();
            //String organizationUnitName=null;
            //DaoUtility daoUtil=new DaoUtility();
            
            if(firstName !=null && firstName.trim().length()>1)
            {
                firstName=cleanStr(firstName);
                firstNamePart=firstName.substring(firstName.trim().length()-2, firstName.trim().length());
            }
            if(surname !=null && surname.trim().length()>1)
            {
                surname=firstName=cleanStr(surname);
                surnamePart=surname.trim().substring(surname.trim().length()-2, surname.trim().length());
            }
            if(districtName !=null && districtName.trim().length()>1)
            {
                districtName=districtName.replaceAll(" ", "");
                districtName=cleanStr(districtName);
                organizationUnitPart=districtName.trim().substring(districtName.trim().length()-2, districtName.trim().length());
            }
            dobPart=getDateOfBirthPart(dateOfBirth);
            if(sex !=null && !sex.equalsIgnoreCase("select")) 
            {
                if(sex.trim().length()>1)
                sexPart=sex.trim().substring(0, 1);
                else
                sexPart=sex.trim().substring(0);
            }
            if(firstNamePart !=null)
            uniqueId=firstNamePart;
            if(surnamePart !=null)
            uniqueId=uniqueId+surnamePart;
            if(organizationUnitPart !=null)
            uniqueId=uniqueId+organizationUnitPart;
            if(dobPart !=null)
            uniqueId=uniqueId+dobPart;
            if(sexPart !=null)
            uniqueId=uniqueId+sexPart;
            if(uniqueId !=null)
            {
                uniqueId=uniqueId.toUpperCase();
                uniqueId=uniqueId.trim();
            }
            System.err.println("uniqueId is "+uniqueId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public String generateHouseholdUniqueId(String firstName,String surname,String organizationUnitId,String dateOfBirth,String sex)
    {
        String uniqueId="";
        try
        {
            String firstNamePart=null;
            String surnamePart=null;
            String organizationUnitPart=null;
            String dobPart=null;
            String sexPart=null;
            String organizationUnitName=null;
            DaoUtility daoUtil=new DaoUtility();
            if(organizationUnitId !=null)
            {
                OrganizationUnit ou=daoUtil.getOrganizationUnitDaoInstance().getOrganizationUnit(organizationUnitId);
                if(ou !=null)
                organizationUnitName=ou.getName();
            }
            if(firstName !=null && firstName.trim().length()>1)
            {
                firstName=cleanStr(firstName);
                //System.err.println("firstName is "+firstName);
                firstNamePart=firstName.substring(firstName.trim().length()-2, firstName.trim().length());
            }
            if(surname !=null && surname.trim().length()>1)
            {
                surname=cleanStr(surname);
                surnamePart=surname.trim().substring(surname.trim().length()-2, surname.trim().length());
            }
            if(organizationUnitName !=null && organizationUnitName.trim().length()>1)
            {
                organizationUnitName=organizationUnitName.replaceAll(" ", "");
                organizationUnitName=cleanStr(organizationUnitName);
                //System.err.println("organizationUnitName is "+organizationUnitName);
                organizationUnitPart=organizationUnitName.trim().substring(organizationUnitName.trim().length()-2, organizationUnitName.trim().length());
            }
            dobPart=getDateOfBirthPart(dateOfBirth);
            //System.err.println("dobPart is "+dobPart);
            if(sex !=null && !sex.equalsIgnoreCase("select")) 
            {
                if(sex.trim().length()>1)
                sexPart=sex.trim().substring(0, 1);
                else
                sexPart=sex.trim().substring(0);
            }
            if(firstNamePart !=null)
            uniqueId=uniqueId+firstNamePart;
            if(surnamePart !=null)
            uniqueId=uniqueId+surnamePart;
            if(organizationUnitPart !=null)
            uniqueId=uniqueId+organizationUnitPart;
            if(dobPart !=null)
            uniqueId=uniqueId+dobPart;
            if(sexPart !=null)
            uniqueId=uniqueId+sexPart;
            if(uniqueId !=null)
            uniqueId=uniqueId.toUpperCase();
            System.err.println("uniqueId is "+uniqueId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public String generateOvcUniqueId(String firstName,String surname,String motherFirstName,String organizationUnitId,String dateOfBirth,String sex)
    {
        String uniqueId="";
        try
        {
            String surnamePart=null;
            String motherFirstNamePart=null;
            String organizationUnitPart=null;
            String dobPart=null;
            String sexPart=null;
            String organizationUnitName=null;
            DaoUtility daoUtil=new DaoUtility();
            if(organizationUnitId !=null)
            {
                OrganizationUnit ou=daoUtil.getOrganizationUnitDaoInstance().getOrganizationUnit(organizationUnitId);
                if(ou !=null)
                organizationUnitName=ou.getName();
            }
            if(motherFirstName !=null && motherFirstName.trim().length()>1)
            {
                motherFirstName=cleanStr(motherFirstName);
                motherFirstNamePart=motherFirstName.trim().substring(motherFirstName.trim().length()-2, motherFirstName.trim().length());
            }
            if(surname !=null && surname.trim().length()>1)
            {
                surname=cleanStr(surname);
                surnamePart=surname.trim().substring(surname.trim().length()-2, surname.trim().length());
            }
            if(organizationUnitName !=null && organizationUnitName.trim().length()>1)
            {
                organizationUnitName=organizationUnitName.trim().replaceAll(" ", "");
                organizationUnitName=cleanStr(organizationUnitName);
                organizationUnitPart=organizationUnitName.trim().substring(organizationUnitName.trim().length()-2, organizationUnitName.trim().length());
                organizationUnitPart=organizationUnitPart.trim();
            }
            dobPart=getDateOfBirthPart(dateOfBirth);
            
            if(sex !=null && !sex.equalsIgnoreCase("select")) 
            {
                if(sex.trim().length()>1)
                sexPart=sex.trim().substring(0, 1);
                else
                sexPart=sex.trim().substring(0);
            }
            if(motherFirstNamePart !=null)
            uniqueId=uniqueId+motherFirstNamePart;
            if(surnamePart !=null)
            uniqueId=uniqueId+surnamePart;
            if(organizationUnitPart !=null)
            uniqueId=uniqueId+organizationUnitPart;
            if(dobPart !=null)
            uniqueId=uniqueId+dobPart;
            if(sexPart !=null)
            uniqueId=uniqueId+sexPart;
            if(uniqueId !=null)
            uniqueId=uniqueId.toUpperCase().replaceAll(" ", "");
            System.err.println("uniqueId is "+uniqueId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public String generateOvcUniqueIdByOrgUnitName(String firstName,String surname,String motherFirstName,String organizationUnitName,String dateOfBirth,String sex)
    {
        String uniqueId="";
        try
        {
            String surnamePart=null;
            String motherFirstNamePart=null;
            String organizationUnitPart=null;
            String dobPart=null;
            String sexPart=null;
            
            if(motherFirstName !=null && motherFirstName.trim().length()>1)
            {
                motherFirstName=cleanStr(motherFirstName);
                motherFirstNamePart=motherFirstName.trim().substring(motherFirstName.trim().length()-2, motherFirstName.trim().length());
            }
            if(surname !=null && surname.trim().length()>1)
            {
                surname=cleanStr(surname);
                surnamePart=surname.trim().substring(surname.trim().length()-2, surname.trim().length());
            }
            //if(organizationUnitName !=null && organizationUnitName.trim().length()>1)
            if(organizationUnitName !=null && organizationUnitName.trim().length()>1)
            {
                organizationUnitName=organizationUnitName.trim().replaceAll(" ", "");
                organizationUnitName=cleanStr(organizationUnitName);
                
                organizationUnitPart=organizationUnitName.trim().substring(organizationUnitName.trim().length()-2, organizationUnitName.trim().length());
                organizationUnitPart=organizationUnitPart.trim();
            }
            
            dobPart=getDateOfBirthPart(dateOfBirth);
            
            if(sex !=null && !sex.equalsIgnoreCase("select")) 
            {
                if(sex.trim().length()>1)
                sexPart=sex.trim().substring(0, 1);
                else
                sexPart=sex.trim().substring(0);
            }
            if(motherFirstNamePart !=null)
            uniqueId=uniqueId+motherFirstNamePart;
            if(surnamePart !=null)
            uniqueId=uniqueId+surnamePart;
            if(organizationUnitPart !=null)
            uniqueId=uniqueId+organizationUnitPart;
            if(dobPart !=null)
            uniqueId=uniqueId+dobPart;
            if(sexPart !=null)
            uniqueId=uniqueId+sexPart;
            if(uniqueId !=null)
            uniqueId=uniqueId.toUpperCase().replaceAll(" ", "");
            System.err.println("uniqueId is "+uniqueId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public String generateUniqueIdByOrganizationUnitShortCodes(String level2OuId,String level3OuId,String cboUniqueId,int serialNumber)
    {
        String uniqueId=null;
        try
        {
            DaoUtility util=new DaoUtility();
            OrganizationUnit level2Ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level2OuId);
            OrganizationUnit level3Ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(level3OuId);
            CommunityBasedOrganization cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganization(cboUniqueId);
            if(level2Ou !=null && level2Ou.getOucode() !=null && level3Ou !=null && level3Ou.getOucode() !=null && cbo !=null && cbo.getCboCode() !=null && serialNumber>0)
            {
                uniqueId=level2Ou.getOucode()+"/"+level3Ou.getOucode()+"/"+cbo.getCboCode()+"/"+getPaddedSerialNumber(serialNumber);
                /*if(level3Ou !=null)
                uniqueId+=level3Ou.getOucode()+"/";
                if(cbo !=null)
                uniqueId+=cbo.getCboCode()+"/";
                uniqueId+=getPaddedSerialNumber(serialNumber);*/
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return uniqueId;
    }
    public String getPaddedSerialNumber(int serialNumber)
    {
        String paddedSerialNumber=null;
        if(serialNumber>0)
        {
            String strSerialNumber=serialNumber+"";
            if(strSerialNumber.length()==1)
            paddedSerialNumber="0000"+strSerialNumber;
            else if(strSerialNumber.length()==2)
            paddedSerialNumber="000"+strSerialNumber;
            else if(strSerialNumber.length()==3)
            paddedSerialNumber="00"+strSerialNumber;
            else if(strSerialNumber.length()==4)
            paddedSerialNumber="0"+strSerialNumber;
            else if(strSerialNumber.length()==5)
            paddedSerialNumber=strSerialNumber;
            paddedSerialNumber=paddedSerialNumber.trim();
        }
        return paddedSerialNumber;
    }
    public int extractHouseholdSerialNumberFromHhUniqueId(String hhUniqueId)
    {
        int hhSerialNo=0;
        if(hhUniqueId !=null)
        {
            String[] hhIdArray=hhUniqueId.split("/");
            if(hhIdArray !=null && hhIdArray.length>1)
            {
                hhSerialNo=Integer.parseInt(hhIdArray[hhIdArray.length-1]);
            }
        }
        return hhSerialNo;
    }
    public int extractChildSerialNumberFromHhUniqueId(String ovcId)
    {
        int childSerialNo=0;
        if(ovcId !=null)
        {
            String[] ovcIdArray=ovcId.split("/");
            if(ovcIdArray !=null && ovcIdArray.length>1)
            {
                childSerialNo=Integer.parseInt(ovcIdArray[ovcIdArray.length-1]); 
            }
        }
        return childSerialNo;
    }
    private String getDateOfBirthPart(String dateOfBirth)
    {
        String dobPart=null;
        if(dateOfBirth !=null && dateOfBirth.trim().length()>1)
        {
            if(dateOfBirth.indexOf("/") !=-1)
            dateOfBirth=DateManager.processMthDayYearToMysqlFormat(dateOfBirth);
            //dateOfBirth=dateOfBirth.replace("/", "");
            if(dateOfBirth.indexOf("-") !=-1)
            dateOfBirth=dateOfBirth.replace("-", "");
            dobPart=dateOfBirth.trim();
        }
        return dobPart;
    }
    public String cleanStr(String strValue)
    {
        if(strValue !=null)
        {
            AppUtility appUtil=new AppUtility();
            strValue=appUtil.removeSpacesFromString(strValue);
            //System.err.println("strValue 1 is "+strValue);
            strValue=appUtil.removeSpecialCharacters(strValue, null);
            //System.err.println("strValue 2 is "+strValue);
        }
        return strValue;
    }
    public static String cleanUniqueId(String uniqueId)
    {
        if(uniqueId !=null)
        uniqueId=uniqueId.replace("_", "'");
        return uniqueId;
    }
}
