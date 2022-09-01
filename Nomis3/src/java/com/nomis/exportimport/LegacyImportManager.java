/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport;

/**
 *
 * @author smomoh
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.nomis.operationsManagement.BeneficiaryAgeManager;
import com.nomis.operationsManagement.EnrollmentStatusManager;
import com.nomis.operationsManagement.HivRiskAssessmentManager;
import com.nomis.operationsManagement.NutritionAssessmentAttributesManager;
import com.nomis.operationsManagement.OvcServiceAttributesManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.CareAndSupportChecklist;
import com.nomis.ovc.business.CaregiverAccessToEmergencyFund;
import com.nomis.ovc.business.CareplanAchievementChecklist;
import com.nomis.ovc.business.ChildEducationPerformanceAssessment;
import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.business.ChildStatusIndex;
import com.nomis.ovc.business.CommunityBasedOrganization;
import com.nomis.ovc.business.CommunityWorker;
import com.nomis.ovc.business.HivRiskAssessment;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.HouseholdReferral;
import com.nomis.ovc.business.HouseholdService;
import com.nomis.ovc.business.NutritionAssessment;
import com.nomis.ovc.business.Occupation;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.ReferralFacility;
import com.nomis.ovc.business.RevisedHouseholdVulnerabilityAssessment;
import com.nomis.ovc.business.School;
import com.nomis.ovc.business.SchoolGrade;
import com.nomis.ovc.business.Service;
import com.nomis.ovc.business.VulnerabilityStatus;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.legacydatamanagement.business.LegacyCareAndSupportChecklist;
import com.nomis.ovc.legacydatamanagement.business.LegacyCaregiver;
import com.nomis.ovc.legacydatamanagement.business.LegacyCaregiverExpenditureAndSchoolAttendance;
import com.nomis.ovc.legacydatamanagement.business.LegacyCareplanAchievement;
import com.nomis.ovc.legacydatamanagement.business.LegacyChildStatusIndex;
import com.nomis.ovc.legacydatamanagement.business.LegacyHivRiskAssessmentChecklist;
import com.nomis.ovc.legacydatamanagement.business.LegacyHivStatusUpdate;
import com.nomis.ovc.legacydatamanagement.business.LegacyHouseholdEnrollment;
import com.nomis.ovc.legacydatamanagement.business.LegacyHouseholdService;
import com.nomis.ovc.legacydatamanagement.business.LegacyHouseholdVulnerabilityAssessment;
import com.nomis.ovc.legacydatamanagement.business.LegacyNutritionAssessment;
import com.nomis.ovc.legacydatamanagement.business.LegacyOrganizationRecords;
import com.nomis.ovc.legacydatamanagement.business.LegacyOvc;
import com.nomis.ovc.legacydatamanagement.business.LegacyOvcReferral;
import com.nomis.ovc.legacydatamanagement.business.LegacyOvcService;
import com.nomis.ovc.legacydatamanagement.business.LegacyOvcWithdrawal;
import com.nomis.ovc.legacydatamanagement.business.LegacyWards;
import com.nomis.ovc.legacydatamanagement.dataimport.LegacyXMLDataImportReader;
import com.nomis.ovc.legacydatamanagement.utils.LegacyOvcServiceManager;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.BirthCertificateManager;
import com.nomis.ovc.util.CaregiverRelationshipManager;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.HivPropertiesManager;
import com.nomis.ovc.util.MaritalStatusManager;
import com.nomis.ovc.util.SingleOptionManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.*;

/**
 *
 * @author smomoh
 */
public class LegacyImportManager implements Serializable
{
    List deletedWithdrawalStatusList=new ArrayList();
    List uniqueDeletedList=new ArrayList();
    ZipHandler zipHandler;
    DataEncryption encryptor;
    AppUtility appUtil;
    DaoUtility util=new DaoUtility();
    boolean compatibilityFlag=false;
    boolean hheCompatibilityFlag=false;
    boolean hvaCompatibilityFlag=false;
    boolean cgiverCompatibilityFlag=false;
    String currentUserName=null;
    List hivList=new ArrayList();
    List hivOnTreatmentList=new ArrayList();
    LegacyXMLDataImportReader lxmlr=new LegacyXMLDataImportReader();
    String userName=null;
public LegacyImportManager(String userName)
{
    this.userName=userName;
    appUtil=new AppUtility();
    AppUtility.setCurrentImportRecordName("Extracting data from zip files");
    //appUtil.createExportImportDirectories();
}
public void createDestinationFolderAndUnzipFile(String destinationDirectory,String fileName)
{
    appUtil=new AppUtility();
    appUtil.createZipExtractDirectories();
    //String destinationDirectory=appUtil.getExportFilePath()+appUtil.getFileSeperator()+fileName;
    appUtil.createDirectories(destinationDirectory);
    zipHandler=new ZipHandler();
    zipHandler.unZipFile(appUtil.getImportFilePath()+appUtil.getFileSeperator()+fileName,destinationDirectory);
    zipHandler=null;
}
public List processHouseholdEnrollment(String parentFolderPath,String partnerCode)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processHouseholdEnrollment");
    try
    {
        List list=lxmlr.readHouseholdEnrollmentFromXml(parentFolderPath, partnerCode);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            HouseholdEnrollment hhe=null;
            RevisedHouseholdVulnerabilityAssessment rhva=null;
            RevisedHouseholdVulnerabilityAssessment dupRhva=null;
            AdultHouseholdMember ahm=null;
            AdultHouseholdMember dupAhm=null;
            MaritalStatusManager msm=new MaritalStatusManager();
            OrganizationUnit ou=null;
            //OrganizationUnit level2Ou=null;
            //OrganizationUnit level3Ou=null;
            
            CommunityBasedOrganization cbo=null;
            String cboId=null;
            String level4OuId=null;
                       
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            Date defaultDate=DateManager.getDefaultStartDateInstance();
            int vulnerabilityScore=0;
            String hhUniqueId=null;
            for(Object obj:list)
            {
                LegacyHouseholdEnrollment lhhe=(LegacyHouseholdEnrollment)obj;
                hhUniqueId=cleanLegacyId(lhhe.getHhUniqueId());
                level4OuId=AppConstant.DEFAULTUID;
                cboId=AppConstant.DEFAULTUID;
                
                ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByLegacyId(lhhe.getCommunityCode());
                if(ou !=null)
                {
                    level4OuId=ou.getUid();
                }
                else
                {
                   OrganizationUnit level4Ou=getNewLevel4OrganizationUnit(hhUniqueId); 
                   if(level4Ou !=null)
                   {
                       level4OuId=level4Ou.getUid();
                   } 
                }
                cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganizationByLegacyId(lhhe.getOrgCode());
                if(cbo !=null)
                cboId=cbo.getUniqueId();
                hhe=new HouseholdEnrollment();
                hhe.setHhUniqueId(hhUniqueId);
                hhe.setEnrollmentId(hhUniqueId);
                hhe.setLegacyId(hhUniqueId);
                //System.err.println("hhe.getHhUniqueId() is "+hhe.getHhUniqueId());
                 hhe.setAddress(lhhe.getAddress());
                 dateOfAssessment=DateManager.getDateInstance(lhhe.getDateOfAssessment());
                 hhe.setDateOfAssessment(dateOfAssessment);
                 hhe.setOrganizationUnit(level4OuId);
                 hhe.setCboId(cboId);
                 hhe.setPartnerCode(partnerCode);
                
                 hhe.setNumberOfChildrenInHousehold(lhhe.getNoOfChildrenInhh());
                 hhe.setNumberOfPeopleInHousehold(lhhe.getNoOfPeopleInhh());
                 hhe.setDateCasePlanDeveloped(defaultDate);
                 hhe.setCurrentEnrollmentStatus(AppConstant.ACTIVE_NUM);
                 hhe.setDateOfCurrentStatus(defaultDate);
                 hhe.setVolunteerName(lhhe.getRecordedBy());
                 hhe.setRecordedBy(lhhe.getRecordedBy());
                 lastModifiedDate=DateManager.getDateInstance(lhhe.getDateOfEntry());
                 hhe.setDateCreated(lastModifiedDate);
                 hhe.setLastModifiedDate(lastModifiedDate);
                 if(util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId)==null)
                 util.getHouseholdEnrollmentDaoInstance().saveHouseholdEnrollment(hhe);
                 else
                 util.getHouseholdEnrollmentDaoInstance().updateHouseholdEnrollment(hhe);

                 rhva=new RevisedHouseholdVulnerabilityAssessment();
                 rhva.setHhUniqueId(hhUniqueId);
                 rhva.setDateOfAssessment(dateOfAssessment); 
                 rhva.setDateCreated(lastModifiedDate);
                 rhva.setLastModifiedDate(lastModifiedDate);
                 String volunteerId=getCommunityWorkerIdFromName(lhhe.getNameOfAssessor(),userName,hhe.getOrganizationUnit());
                 if(volunteerId==null)
                 volunteerId="xxxxxxxxxxx";
                 rhva.setVolunteerName(volunteerId);
                 
                rhva.setHhHeadship(lhhe.getHhHeadship());
                rhva.setHealth(lhhe.getHealth());
                rhva.setEducationLevel(lhhe.getEducationLevel());
                rhva.setShelterAndHousing(lhhe.getShelterAndHousing());
                rhva.setFoodSecurityAndNutrition(lhhe.getFoodSecurityAndNutrition());
                rhva.setMeansOfLivelihood(lhhe.getMeansOfLivelihood());
                rhva.setHhIncome(lhhe.getHhIncome());
                vulnerabilityScore=rhva.getHhHeadship()+rhva.getHealth()+rhva.getEducationLevel()+rhva.getShelterAndHousing()+
                rhva.getFoodSecurityAndNutrition()+rhva.getMeansOfLivelihood()+rhva.getHhIncome();
                rhva.setVulnerabilityScore(vulnerabilityScore);
                
                dupRhva=util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().getHouseholdVulnerabilityAssessment(rhva.getHhUniqueId(), dateOfAssessment);
                if(dupRhva==null)
                util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().saveOrUpdateRevisedHouseholdVulnerabilityAssessment(rhva);
                else
                {
                    rhva.setId(dupRhva.getId());
                    util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().updateHouseholdVulnerabilityAssessment(rhva);
                }
                
                ahm=new AdultHouseholdMember();
                ahm.setHhUniqueId(hhUniqueId);
                ahm.setBeneficiaryId(hhUniqueId);
                ahm.setEnrollmentId(hhUniqueId);
                ahm.setSurname(lhhe.getHhSurname());
                ahm.setFirstName(lhhe.getHhFirstname());
                ahm.setAgeAtBaseline(lhhe.getHhAge());
                ahm.setAgeUnitAtBaseline(AppConstant.AGEUNIT_YEAR_NUM);
                //ahm.setCurrentAge(appUtil.);
                ahm.setDateEnrolledOnTreatment(defaultDate);
                ahm.setDateOfBaselineHivStatus(dateOfAssessment);
                ahm.setDateOfBirth(defaultDate);
                ahm.setDateOfCurrentEnrollmentStatus(defaultDate);
                ahm.setDateOfCurrentHivStatus(defaultDate);
                ahm.setDateOfEnrollment(dateOfAssessment);
                ahm.setPhoneNumber(lhhe.getPhone());         
                ahm.setMaritalStatus(msm.getMaritalStatus(lhhe.getMaritalStatus()).getValue());
                ahm.setIsCaregiver(AppConstant.HOUSEHOLDHEAD_CAREGIVER_NUM);

                if(lhhe.getHhGender() !=null)
                ahm.setSex(lhhe.getHhGender());
                if(ahm.getSex() !=null && ahm.getSex().trim().length()>0)
                ahm.setSex(ahm.getSex().substring(0,1));
                ahm.setOccupation(getOccupation(lhhe.getOccupation()));
                ahm.setDateCreated(lastModifiedDate);
                ahm.setLastModifiedDate(lastModifiedDate);
                ahm.setRecordedBy(lhhe.getRecordedBy());
                dupAhm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMemberByName(ahm.getHhUniqueId(), ahm.getFirstName(), ahm.getSurname());
                if(dupAhm !=null)
                {
                    ahm.setBeneficiaryId(dupAhm.getBeneficiaryId());
                    util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                }
                else
                util.getAdultHouseholdMemberDaoInstance().saveAdultHouseholdMember(ahm);
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
private OrganizationUnit getNewLevel4OrganizationUnit(String hhUniqueId) throws Exception
{
    OrganizationUnit level2Ou=null;
    OrganizationUnit level3Ou=null;
    OrganizationUnit level4Ou=null;
    String level2OuCode=null;
    String level3OuCode=null;
    
    if(hhUniqueId !=null)
    {
        String[] hhUniqueIdArray=hhUniqueId.split("/");
        if(hhUniqueIdArray !=null && hhUniqueIdArray.length>1)
        {
            //extract the level2 and level3 ou codes (state and lga codes)
            level2OuCode=hhUniqueIdArray[0];
            level3OuCode=hhUniqueIdArray[1];
            //get the level 2 ou
            level2Ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByOuCodeAndOuLevel(level2OuCode,2);
            level3Ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByOuCodeAndOuLevel(level3OuCode,3);
            if(level3Ou !=null)
            {
                level4Ou=util.getOrganizationUnitDaoInstance().createWard(level3Ou,level3Ou.getName()+" unknown Community", level3OuCode,AppConstant.DEFAULTUID+level3OuCode);
            }
            else
            {
                //Here, level3OU does not exist in this database. It was probably setup on the field. Create an unknown level3ou
                if(level2Ou !=null)
                {
                    //if the LGA code is not present, i.e LGA was setup on the field by the end user, create an unknown LGA
                    level3Ou=util.getOrganizationUnitDaoInstance().createWard(level2Ou,level2Ou.getName()+" unknown LGA", level3OuCode,AppConstant.DEFAULTUID+level3OuCode);
                    if(level3Ou !=null)
                    {
                        //create the level4 organization unit
                        level4Ou=util.getOrganizationUnitDaoInstance().createWard(level3Ou,level3Ou.getName()+" unknown Community", level3OuCode,AppConstant.DEFAULTUID+level3OuCode);
                    }
                }
            }       
        }
    }
    return level4Ou;
}
public List processCaregiverRecords(String parentFolderPath,String partnerCode)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processCaregiverRecords");
    try
    {
        List list=lxmlr.readCaregiverRecordsFromXml(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyCaregiver lcgiver=null;
            HouseholdEnrollment hhe=null;
            
            AdultHouseholdMember ahm=null;
            AdultHouseholdMember dupAhm=null;
            MaritalStatusManager msm=new MaritalStatusManager();
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            Date defaultDate=DateManager.getDefaultStartDateInstance();
            String hhUniqueId=null;
            for(Object obj:list)
            {
                lcgiver=(LegacyCaregiver)obj;
                hhUniqueId=cleanLegacyId(lcgiver.getHhUniqueId());
                hhe=new HouseholdEnrollment();
                hhe.setHhUniqueId(lcgiver.getHhUniqueId());
                hhe.setEnrollmentId(hhUniqueId);
                System.err.println("lcgiver.getHhUniqueId() is "+lcgiver.getHhUniqueId());
                 
                 dateOfAssessment=DateManager.getDateInstance(lcgiver.getDateOfEnrollment());
                 lastModifiedDate=DateManager.getDateInstance(lcgiver.getDateModified());
                                  
                ahm=new AdultHouseholdMember();
                ahm.setHhUniqueId(hhUniqueId);
                ahm.setEnrollmentId(hhUniqueId);
                ahm.setBeneficiaryId(lcgiver.getCaregiverId());
                ahm.setLegacyId(lcgiver.getCaregiverId());
                ahm.setSurname(lcgiver.getCaregiverLastName());
                ahm.setFirstName(lcgiver.getCaregiverFirstname());
                ahm.setAgeAtBaseline(lcgiver.getCaregiverAge());
                ahm.setAgeUnitAtBaseline(AppConstant.AGEUNIT_YEAR_NUM);
                //ahm.setCurrentAge(appUtil.);
                if((ahm.getFirstName()==null || ahm.getFirstName().trim().length()<2) && (ahm.getSurname()==null || ahm.getSurname().trim().length()<2))
                continue;
                ahm.setDateEnrolledOnTreatment(defaultDate);
                ahm.setBaselineHivStatus(HivPropertiesManager.getHivStatus(lcgiver.getCgiverHivStatus()).getCode());
                ahm.setDateOfBaselineHivStatus(dateOfAssessment);
                ahm.setDateOfBirth(defaultDate);
                ahm.setDateOfCurrentEnrollmentStatus(defaultDate);
                ahm.setDateOfCurrentHivStatus(defaultDate);
                ahm.setDateOfEnrollment(dateOfAssessment);
                ahm.setPhoneNumber(lcgiver.getCaregiverPhone());         
                ahm.setMaritalStatus(msm.getMaritalStatus(lcgiver.getCaregiverMaritalStatus()).getValue());
                ahm.setIsCaregiver(AppConstant.HOUSEHOLDHEAD_CAREGIVER_NUM);

                if(lcgiver.getCaregiverGender() !=null)
                ahm.setSex(lcgiver.getCaregiverGender());
                if(ahm.getSex() !=null && ahm.getSex().trim().length()>0)
                ahm.setSex(ahm.getSex().substring(0,1));
                ahm.setOccupation(getOccupation(lcgiver.getCaregiverOccupation()));
                ahm.setDateCreated(lastModifiedDate);
                ahm.setLastModifiedDate(lastModifiedDate);
                ahm.setRecordedBy(hhe.getRecordedBy());
                if(ahm.getRecordedBy()==null)
                ahm.setRecordedBy("xxxxxxxxxxx");
                if(ahm.getFirstName()==null || ahm.getFirstName().trim().length()<2)
                ahm.setFirstName("Unknown");
                if(ahm.getSurname()==null || ahm.getSurname().trim().length()<2)
                ahm.setSurname("Unknown");
                
                dupAhm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMemberByName(ahm.getHhUniqueId(), ahm.getFirstName(), ahm.getSurname());
                if(dupAhm !=null)
                {
                    //if beneficiary Id is same as hh unique Id, then 
                    if(dupAhm.getBeneficiaryId().equalsIgnoreCase(dupAhm.getHhUniqueId()))
                    {
                        //save this as the correct record and remove the earlier one
                        util.getAdultHouseholdMemberDaoInstance().saveAdultHouseholdMember(ahm);
                        if(util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(ahm.getBeneficiaryId()) !=null)
                        {
                            util.getAdultHouseholdMemberDaoInstance().deleteAdultHouseholdMember(dupAhm);
                        }
                        
                    }
                    else if(dupAhm.getBeneficiaryId().equalsIgnoreCase(ahm.getBeneficiaryId()))
                    {
                        //ahm.setBeneficiaryId(dupAhm.getBeneficiaryId());
                        //ahm.setEnrollmentId(dupAhm.getBeneficiaryId());
                        util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                    }
                }
                else
                util.getAdultHouseholdMemberDaoInstance().saveAdultHouseholdMember(ahm);
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processHouseholdVulnerabilityAssessment(String parentFolderPath,String partnerCode)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processHouseholdVulnerabilityAssessment");
    try
    {
        List list=lxmlr.readHouseholdVulnebilityAssessmentFromXml(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            HouseholdEnrollment hhe=null;
            RevisedHouseholdVulnerabilityAssessment rhva=null;
            RevisedHouseholdVulnerabilityAssessment dupRhva=null;
            LegacyHouseholdVulnerabilityAssessment lhva=null;
            String hhUniqueId=null;
            
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            
            int vulnerabilityScore=0;
            for(Object obj:list)
            {
                lhva=(LegacyHouseholdVulnerabilityAssessment)obj;
                hhUniqueId=cleanLegacyId(lhva.getHhUniqueId());
                hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(hhUniqueId);
                
                dateOfAssessment=DateManager.getDateInstance(lhva.getDateOfAssessment());
                lastModifiedDate=DateManager.getDateInstance(lhva.getDateOfEntry());
                 rhva=new RevisedHouseholdVulnerabilityAssessment();
                 rhva.setHhUniqueId(hhUniqueId);
                 rhva.setDateOfAssessment(dateOfAssessment); 
                 rhva.setDateCreated(lastModifiedDate);
                 rhva.setLastModifiedDate(lastModifiedDate);
                 String volunteerId=AppConstant.DEFAULTUID;//getCommunityWorkerIdFromName(lhva.getNameOfAssessor(),userName,hhe.getOrganizationUnit());
                 if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lhva.getNameOfAssessor(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId=AppConstant.DEFAULTUID;//hhe.getVolunteerName();
                 }
                 rhva.setVolunteerName(volunteerId);
                 
                rhva.setHhHeadship(lhva.getHhHeadship());
                rhva.setHealth(lhva.getHealth());
                rhva.setEducationLevel(lhva.getEducationLevel());
                rhva.setShelterAndHousing(lhva.getShelterAndHousing());
                rhva.setFoodSecurityAndNutrition(lhva.getFoodSecurityAndNutrition());
                rhva.setMeansOfLivelihood(lhva.getMeansOfLivelihood());
                rhva.setHhIncome(lhva.getHhIncome());
                vulnerabilityScore=rhva.getHhHeadship()+rhva.getHealth()+rhva.getEducationLevel()+rhva.getShelterAndHousing()+
                rhva.getFoodSecurityAndNutrition()+rhva.getMeansOfLivelihood()+rhva.getHhIncome();
                rhva.setVulnerabilityScore(vulnerabilityScore);
                
                dupRhva=util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().getHouseholdVulnerabilityAssessment(rhva.getHhUniqueId(), dateOfAssessment);
                if(dupRhva==null)
                util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().saveOrUpdateRevisedHouseholdVulnerabilityAssessment(rhva);
                else
                {
                    rhva.setId(dupRhva.getId());
                    util.getRevisedHouseholdVulnerabilityAssessmentDaoInstance().updateHouseholdVulnerabilityAssessment(rhva);
                }    
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processOvcRecords(String parentFolderPath,String partnerCode)
{
    List resultList=new ArrayList();
    System.err.println("Inside processOvcRecords");
    try
    {
        BeneficiaryAgeManager bam=new BeneficiaryAgeManager();
        List list=lxmlr.readOvcRecordsFromXml(1,parentFolderPath,false,false);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyOvc lovc=null;
            HouseholdEnrollment hhe=null;
            
            Ovc ovc=null;
            Ovc dupOvc=null;
            MaritalStatusManager msm=new MaritalStatusManager();
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            Date defaultDate=DateManager.getDefaultStartDateInstance();
            int vulnerabilityScore=0;
            for(Object obj:list)
            {
                lovc=(LegacyOvc)obj;
                
                dateOfAssessment=DateManager.getDateInstance(lovc.getDateEnrollment());
                lastModifiedDate=DateManager.getDateInstance(lovc.getDateOfEntry());
                                  
                ovc=new Ovc();
                ovc.setOvcId(cleanLegacyId(lovc.getOvcId()));
                ovc.setHhUniqueId(cleanLegacyId(lovc.getHhUniqueId()));
                ovc.setEnrollmentId(lovc.getOvcId());
                ovc.setLegacyId(cleanLegacyId(lovc.getOvcId()));
                ovc.setSurname(lovc.getLastName());
                ovc.setFirstName(lovc.getFirstName());
                ovc.setAgeAtBaseline(lovc.getAge());
                ovc.setAgeUnitAtBaseline(bam.getAgeUnit(lovc.getAgeUnit()));
                ovc.setBaselineBirthRegistrationStatus(BirthCertificateManager.getBirthCertificate(lovc.getBirthCertificate()).getValue());
                ovc.setDateOfCurrentBirthRegStatus(dateOfAssessment);
                ovc.setBaselineSchoolStatus(SingleOptionManager.getSingleChoiceOption(lovc.getActiveSchoolStatus()).getValue());
                ovc.setVulnerabilityStatusCode(convertLegacyVulnerabilityStatusToIds(lovc.getEligibility()));
                if(ovc.getVulnerabilityStatusCode()==null)
                ovc.setVulnerabilityStatusCode(AppConstant.DEFAULTUID);
                ovc.setCaregiverId(lovc.getCaregiverId());
                ovc.setCaregiverRelationship(CaregiverRelationshipManager.getCaregiverRelationship(lovc.getCaregiverRelationships()).getValue());
                
                if((ovc.getFirstName()==null || ovc.getFirstName().trim().length()<2) && (ovc.getSurname()==null || ovc.getSurname().trim().length()<2))
                continue;
                
                hhe=ovc.getHhe();
                                
                ovc.setDateOfCurrentSchoolStatus(dateOfAssessment);
                ovc.setDateCasePlanDeveloped(defaultDate);
                ovc.setDateEnrolledOnTreatment(defaultDate);
                ovc.setBaselineHivStatus(HivPropertiesManager.getHivStatus(lovc.getHivStatus()).getCode());
                ovc.setDateOfBaselineHivStatus(dateOfAssessment);
                ovc.setDateOfBirth(defaultDate);
                ovc.setDateOfCurrentEnrollmentStatus(defaultDate);
                ovc.setDateOfCurrentHivStatus(defaultDate);
                ovc.setDateOfEnrollment(dateOfAssessment);
                ovc.setPhoneNumber(lovc.getPhone());  
                ovc.setWeight(lovc.getWeight());
                ovc.setHeight(lovc.getHeight());
                ovc.setBaselineSchoolStatus(getSchoolStatus(lovc.getSchoolStatus()));
                ovc.setSchoolName(getSchoolIdFromSchoolName(lovc.getSchoolName(),hhe.getOrganizationUnit()));
                ovc.setSchoolGrade(getSchoolGradeIdFromSchoolGradeName(lovc.getCurrentClass()));                 
                if(lovc.getGender() !=null)
                ovc.setSex(lovc.getGender());
                if(ovc.getSex() !=null && ovc.getSex().trim().length()>0)
                ovc.setSex(ovc.getSex().substring(0,1));
                String volunteerId=AppConstant.DEFAULTUID;
                
                 if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lovc.getCompletedbyName(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId=AppConstant.DEFAULTUID;
                 }
                ovc.setCommunityWorkerName(volunteerId);
                ovc.setDateCreated(lastModifiedDate);
                ovc.setLastModifiedDate(lastModifiedDate);
                ovc.setRecordedBy(hhe.getRecordedBy());
                if(ovc.getRecordedBy()==null)
                ovc.setRecordedBy(AppConstant.DEFAULTUID);
                if(ovc.getFirstName()==null || ovc.getFirstName().trim().length()<2)
                ovc.setFirstName("Unknown");
                if(ovc.getSurname()==null || ovc.getSurname().trim().length()<2)
                ovc.setSurname("Unknown");
                ovc=util.getChildEnrollmentDaoInstance().getOvcWithCurrentAge(ovc);
                dupOvc=util.getChildEnrollmentDaoInstance().getOvc(ovc.getOvcId());
                if(dupOvc ==null)
                {
                    util.getChildEnrollmentDaoInstance().saveOvc(ovc, false, false);
                }
                else
                util.getChildEnrollmentDaoInstance().updateOvc(ovc, false, false);
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processChildStatusIndexRecords(String parentFolderPath)
{
    List resultList=new ArrayList();
    System.err.println("Inside processChildStatusIndexRecords");
    try
    {
        //System.err.println("Inside processNutritionAssessmentRecords try block");
        List list=lxmlr.readCsiScoreFromXml(1,parentFolderPath);
        if(list==null || list.isEmpty())
         System.err.println("list is null or lis is empty");   
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyChildStatusIndex lcsi=null;
            HouseholdEnrollment hhe=null;
            int count=0;
            ChildStatusIndex csi=null;
            ChildStatusIndex dupCsi=null;
            Ovc ovc=null;
            
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            
            Date defaultDate=DateManager.getDefaultStartDateInstance();
            String volunteerId=AppConstant.DEFAULTUID;
            for(Object obj:list)
            {
                lcsi=(LegacyChildStatusIndex)obj;
                ovc=util.getChildEnrollmentDaoInstance().getOvc(lcsi.getOvcId());
                if(ovc !=null)
                hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ovc.getHhUniqueId());
                
                dateOfAssessment=DateManager.getDateInstance(lcsi.getCsiDate());
                lastModifiedDate=DateManager.getDateInstance(lcsi.getDateOfEntry());
                                
                csi=new ChildStatusIndex();
                
                csi.setOvcId(lcsi.getOvcId());
                csi.setEmotionalHealth(lcsi.getCsiFactor1());
                csi.setSocialBehaviour(lcsi.getCsiFactor2());
                csi.setFoodSecurity(lcsi.getCsiFactor3());
                csi.setNutritionAndGrowth(lcsi.getCsiFactor4());
                csi.setWellness(lcsi.getCsiFactor5());
                csi.setHealthCareServices(lcsi.getCsiFactor6());
                csi.setDevelopmentAndPerformance(lcsi.getCsiFactor7());
                csi.setEducationAndWork(lcsi.getCsiFactor8());
                csi.setAbuseAndExploitation(lcsi.getCsiFactor9());
                csi.setLegalProtection(lcsi.getCsiFactor10());
                csi.setShelter(lcsi.getCsiFactor11());
                csi.setCare(lcsi.getCsiFactor12());
                csi.setAssessmentNumber(lcsi.getSurveyNumber());
                csi.setCsiDate(dateOfAssessment);
                csi.setLastModifiedDate(lastModifiedDate);
                csi.setTotalCsiScore(lcsi.getTotalCsiScore());
                csi.setTotalScore(lcsi.getTotalCsiScore());
                
                dupCsi=util.getChildStatusDaoInstance().getChildStatusIndex(csi.getOvcId(), csi.getCsiDate());
                if(dupCsi==null)
                {
                    util.getChildStatusDaoInstance().saveChildStatusIndex(csi);
                    count++;
                    System.err.println("Child Status index "+count+" saved");
                }
                else
                {
                    csi.setId(dupCsi.getId());
                    util.getChildStatusDaoInstance().updateChildStatusIndex(csi);
                    count++;
                    System.err.println("ChildStatus index "+count+" updated");
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processOvcServiceRecords(String parentFolderPath,String partnerCode)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processOvcServiceRecords");
    try
    {
        BeneficiaryAgeManager bam=new BeneficiaryAgeManager();
        EnrollmentStatusManager esm=new EnrollmentStatusManager();
        List list=lxmlr.readServiceRecordsFromXml(1,parentFolderPath,false,false);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyOvc lovc=null;
            HouseholdEnrollment hhe=null;
            
            Ovc ovc=null;
            ChildService service=null;
            ChildService dupService=null;
            Date serviceDate=null;
            Date lastModifiedDate=null;
            //Date defaultDate=DateManager.getDefaultStartDateInstance();
            String concatenatedHealthyServices=null;
            String concatenatedSafetyServices=null;
            String concatenatedSchoolServices=null;
            String concatenatedStableServices=null;
            String concatenatedReferralServices=null;
            
            String[] psychoServices=null;
            String[] nutritionalServices=null;
            String[] healthServices=null;
            String[] educationalServices=null;
            String[] protectionServices=null;
            String[] shelterServices=null;
            String[] economicServices=null;
            String[] referralServices=null;
            
            String legacyPsychoServices=null;
            String legacyNutritionalServices=null;
            String legacyHealthServices=null;
            String legacyEducationalServices=null;
            String legacyProtectionServices=null;
            String legacyShelterServices=null;
            String legacyEconomicServices=null;
            String legacyReferralServices=null;
            LegacyOvcService lservice=null;
            
            for(Object obj:list)
            {
                concatenatedHealthyServices=null;
                concatenatedSafetyServices=null;
                concatenatedSchoolServices=null;
                concatenatedStableServices=null;
            
                lservice=(LegacyOvcService)obj;
                legacyPsychoServices=lservice.getServiceAccessed1();
                legacyNutritionalServices=lservice.getServiceAccessed2();
                legacyHealthServices=lservice.getServiceAccessed3();
                legacyEducationalServices=lservice.getServiceAccessed4();
                legacyProtectionServices=lservice.getServiceAccessed5();
                legacyShelterServices=lservice.getServiceAccessed6();
                legacyEconomicServices=lservice.getServiceAccessed7();
                
                psychoServices=getLegacyOvcServices(legacyPsychoServices);
                nutritionalServices=getLegacyOvcServices(legacyNutritionalServices);
                healthServices=getLegacyOvcServices(legacyHealthServices);
                educationalServices=getLegacyOvcServices(legacyEducationalServices);
                protectionServices=getLegacyOvcServices(legacyProtectionServices);
                shelterServices=getLegacyOvcServices(legacyShelterServices);
                economicServices=getLegacyOvcServices(legacyEconomicServices);
                                
                 serviceDate=DateManager.getDateInstance(lservice.getServicedate());
                 lastModifiedDate=DateManager.getDateInstance(lservice.getDateOfEntry());
                                  
                service=new ChildService();
                service.setOvcId(lservice.getOvcId());
                service.setServiceDate(serviceDate);
                service.setAgeAtService(0);
                service.setAgeUnitAtService(0);
                service.setServiceDate(serviceDate);
                service.setDateCreated(lastModifiedDate);
                service.setLastModifiedDate(lastModifiedDate);
                service.setRecordedBy(userName);
                String volunteerId="xxxxxxxxxxx";
                ovc=util.getChildEnrollmentDaoInstance().getOvc(lservice.getOvcId());
                if(ovc !=null)
                {
                    hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ovc.getHhUniqueId());
                }
                 if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lservice.getProviderName(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId="xxxxxxxxxxx";//hhe.getVolunteerName();
                 }
                service.setCommunityWorkerId(volunteerId); 
                concatenatedHealthyServices=getConcatenatedServiceCodes(healthServices,AppConstant.OVC_TYPE_NUM,AppConstant.HEALTHDOMAINCODE);
                if(legacyNutritionalServices !=null && legacyNutritionalServices.trim().length()>0)
                {
                    if(concatenatedHealthyServices !=null)
                    concatenatedHealthyServices+=","+getConcatenatedServiceCodes(nutritionalServices,AppConstant.OVC_TYPE_NUM,AppConstant.HEALTHDOMAINCODE);
                    else
                    concatenatedHealthyServices=getConcatenatedServiceCodes(nutritionalServices,AppConstant.OVC_TYPE_NUM,AppConstant.HEALTHDOMAINCODE);
                    
                }
                service.setHealthServices(LegacyOvcServiceManager.cleanServiceName(concatenatedHealthyServices));
                
                concatenatedSafetyServices=getConcatenatedServiceCodes(psychoServices,AppConstant.OVC_TYPE_NUM,AppConstant.SAFEDOMAINCODE);
                if(legacyShelterServices !=null && legacyShelterServices.trim().length()>0)
                {
                    if(concatenatedSafetyServices !=null)
                    concatenatedSafetyServices+=","+getConcatenatedServiceCodes(shelterServices,AppConstant.OVC_TYPE_NUM,AppConstant.SAFEDOMAINCODE);
                    else
                    concatenatedSafetyServices=getConcatenatedServiceCodes(shelterServices,AppConstant.OVC_TYPE_NUM,AppConstant.SAFEDOMAINCODE);
                }
                service.setSafetyServices(LegacyOvcServiceManager.cleanServiceName(concatenatedSafetyServices));
                
                concatenatedSchoolServices=getConcatenatedServiceCodes(educationalServices,AppConstant.OVC_TYPE_NUM,AppConstant.SCHDOMAINCODE);
                service.setSchooledServices(LegacyOvcServiceManager.cleanServiceName(concatenatedSchoolServices));
                
                concatenatedStableServices=getConcatenatedServiceCodes(protectionServices,AppConstant.OVC_TYPE_NUM,AppConstant.STABLEDOMAINCODE);
                if(legacyEconomicServices !=null && legacyEconomicServices.trim().length()>0)
                {
                    if(concatenatedStableServices !=null)
                    concatenatedStableServices+=","+getConcatenatedServiceCodes(economicServices,AppConstant.OVC_TYPE_NUM,AppConstant.STABLEDOMAINCODE);
                    else
                    concatenatedStableServices=getConcatenatedServiceCodes(economicServices,AppConstant.OVC_TYPE_NUM,AppConstant.STABLEDOMAINCODE);
                } 
                service.setStableServices(LegacyOvcServiceManager.cleanServiceName(concatenatedStableServices));
                service.setChildAbused(lservice.getChildAbused());
                service.setAbusedChildLinkedToGovt(lservice.getChildLinkedToGovt());
                if(lservice.getChildMissedSchool() !=null)
                {
                    if(lservice.getChildMissedSchool().trim().equalsIgnoreCase("Yes"))
                    service.setChildMissedSchool(AppConstant.YES_OPTION_NUM);
                    else if(lservice.getChildMissedSchool().trim().equalsIgnoreCase("No"))
                    service.setChildMissedSchool(AppConstant.NO_OPTION_NUM);
                }
                
                dupService=util.getChildServiceDaoInstance().getChildService(service.getOvcId(), serviceDate);
                if(dupService ==null)
                {
                    util.getChildServiceDaoInstance().saveChildService(service, true);
                }
                else
                util.getChildServiceDaoInstance().updateChildService(service, true);
                esm.processChildrenEnrollmentStatusByChildId(service.getOvcId(),DateManager.convertDateToString(service.getServiceDate(),DateManager.DB_DATE_FORMAT), userName);
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processHouseholdServiceRecords(String parentFolderPath)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processHouseholdServiceRecords");
    try
    {
        BeneficiaryAgeManager bam=new BeneficiaryAgeManager();
        EnrollmentStatusManager esm=new EnrollmentStatusManager();
        List list=lxmlr.readHouseholdServiceFromXml(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyOvc lovc=null;
            HouseholdEnrollment hhe=null;
            
            AdultHouseholdMember ahm=null;
            HouseholdService service=null;
            HouseholdService dupService=null;
            Date serviceDate=null;
            Date lastModifiedDate=null;
            //Date defaultDate=DateManager.getDefaultStartDateInstance();
            String concatenatedHealthyServices=null;
            String concatenatedSafetyServices=null;
            String concatenatedSchoolServices=null;
            String concatenatedStableServices=null;
            String concatenatedReferralServices=null;
            
            String[] psychoServices=null;
            String[] nutritionalServices=null;
            String[] healthServices=null;
            String[] educationalServices=null;
            String[] protectionServices=null;
            String[] shelterServices=null;
            String[] economicServices=null;
            String[] referralServices=null;
            
            String legacyPsychoServices=null;
            String legacyNutritionalServices=null;
            String legacyHealthServices=null;
            String legacyEducationalServices=null;
            String legacyProtectionServices=null;
            String legacyShelterServices=null;
            String legacyEconomicServices=null;
            String legacyReferralServices=null;
            LegacyHouseholdService lservice=null;
            
            for(Object obj:list)
            {
                concatenatedHealthyServices=null;
                concatenatedSafetyServices=null;
                concatenatedSchoolServices=null;
                concatenatedStableServices=null;
            
                lservice=(LegacyHouseholdService)obj;
                legacyPsychoServices=lservice.getPsychosocialSupportServices();
                legacyNutritionalServices=lservice.getNutritionalServices();
                legacyHealthServices=lservice.getHealthServices();
                legacyEducationalServices=lservice.getEducationalServices();
                legacyProtectionServices=lservice.getProtectionServices();
                legacyShelterServices=lservice.getShelterAndCareServices();
                legacyEconomicServices=lservice.getEconomicStrengtheningServices();
                
                psychoServices=getLegacyOvcServices(legacyPsychoServices);
                nutritionalServices=getLegacyOvcServices(legacyNutritionalServices);
                healthServices=getLegacyOvcServices(legacyHealthServices);
                educationalServices=getLegacyOvcServices(legacyEducationalServices);
                protectionServices=getLegacyOvcServices(legacyProtectionServices);
                shelterServices=getLegacyOvcServices(legacyShelterServices);
                economicServices=getLegacyOvcServices(legacyEconomicServices);
                                
                 serviceDate=DateManager.getDateInstance(lservice.getServiceDate());
                 lastModifiedDate=DateManager.getDateInstance(lservice.getDateOfEntry());
                                  
                service=new HouseholdService();
                service.setBeneficiaryId(lservice.getCaregiverId());
                service.setServiceDate(serviceDate);
                service.setAgeAtService(0);
                //service.setAgeUnitAtService(0);
                service.setServiceDate(serviceDate);
                service.setDateCreated(lastModifiedDate);
                service.setLastModifiedDate(lastModifiedDate);
                service.setRecordedBy(userName);
                String volunteerId="xxxxxxxxxxx";
                ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(lservice.getCaregiverId());
                if(ahm !=null)
                {
                    hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ahm.getHhUniqueId());
                }
                 if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lservice.getVolunteerName(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId="xxxxxxxxxxx";//hhe.getVolunteerName();
                 }
                //service.setCommunityWorker(volunteerId); 
                concatenatedHealthyServices=getConcatenatedServiceCodes(healthServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.HEALTHDOMAINCODE);
                if(legacyNutritionalServices !=null && legacyNutritionalServices.trim().length()>0)
                {
                    if(concatenatedHealthyServices !=null)
                    concatenatedHealthyServices+=","+getConcatenatedServiceCodes(nutritionalServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.HEALTHDOMAINCODE);
                    else
                    concatenatedHealthyServices=getConcatenatedServiceCodes(nutritionalServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.HEALTHDOMAINCODE);
                    
                }
                service.setHealthServices(LegacyOvcServiceManager.cleanServiceName(concatenatedHealthyServices));
                
                concatenatedSafetyServices=getConcatenatedServiceCodes(psychoServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.SAFEDOMAINCODE);
                if(legacyShelterServices !=null && legacyShelterServices.trim().length()>0)
                {
                    if(concatenatedSafetyServices !=null)
                    concatenatedSafetyServices+=","+getConcatenatedServiceCodes(shelterServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.SAFEDOMAINCODE);
                    else
                    concatenatedSafetyServices=getConcatenatedServiceCodes(shelterServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.SAFEDOMAINCODE);
                }
                service.setSafetyServices(LegacyOvcServiceManager.cleanServiceName(concatenatedSafetyServices));
                
                concatenatedSchoolServices=getConcatenatedServiceCodes(educationalServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.SCHDOMAINCODE);
                service.setSchoolServices(LegacyOvcServiceManager.cleanServiceName(concatenatedSchoolServices));
                
                concatenatedStableServices=getConcatenatedServiceCodes(protectionServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.STABLEDOMAINCODE);
                if(legacyEconomicServices !=null && legacyEconomicServices.trim().length()>0)
                {
                    if(concatenatedStableServices !=null)
                    concatenatedStableServices+=","+getConcatenatedServiceCodes(economicServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.STABLEDOMAINCODE);
                    else
                    concatenatedStableServices=getConcatenatedServiceCodes(economicServices,AppConstant.CAREGIVER_TYPE_NUM,AppConstant.STABLEDOMAINCODE);
                } 
                service.setStableServices(LegacyOvcServiceManager.cleanServiceName(concatenatedStableServices));
               
                dupService=util.getHouseholdServiceDaoInstance().getHouseholdService(service.getBeneficiaryId(), serviceDate);
                if(dupService ==null)
                {
                    util.getHouseholdServiceDaoInstance().saveHouseholdService(service, true);
                }
                else
                util.getHouseholdServiceDaoInstance().updateHouseholdService(service, true);
                esm.processAdultHouseholdMemberEnrollmentStatusByBeneficiaryId(service.getBeneficiaryId(),DateManager.convertDateToString(service.getServiceDate(),DateManager.DB_DATE_FORMAT), userName);
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}

public List processHivStatusUpdateRecords(String parentFolderPath)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processHivStatusUpdateRecords");
    try
    {
        //BeneficiaryAgeManager bam=new BeneficiaryAgeManager();
        List list=lxmlr.readHivStatusUpdateFromXml(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            
            Ovc ovc=null;
            AdultHouseholdMember ahm=null;
            LegacyHivStatusUpdate lhsu=null;
            
            Date dateOfCurrentStatus=null;
            
            for(Object obj:list)
            {
                lhsu=(LegacyHivStatusUpdate)obj;
                
                dateOfCurrentStatus=DateManager.getDateInstance(lhsu.getDateOfCurrentStatus());
                //lastModifiedDate=DateManager.getDateInstance(lhsu.getDateModified());
                if(lhsu.getClientType() !=null && lhsu.getClientType().equalsIgnoreCase("ovc"))
                {
                    ovc=util.getChildEnrollmentDaoInstance().getOvc(lhsu.getClientId());
                    if(ovc !=null)
                    {
                        if(lhsu.getPointOfUpdate() !=null && lhsu.getPointOfUpdate().equalsIgnoreCase("enrollment"))
                        {
                            ovc.setBaselineHivStatus(HivPropertiesManager.getHivStatus(lhsu.getHivStatus()).getCode());
                            ovc.setDateOfBaselineHivStatus(dateOfCurrentStatus);
                            if(ovc.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                            {
                                ovc.setCurrentHivStatus(HivPropertiesManager.getHivStatus(lhsu.getHivStatus()).getCode());
                                ovc.setDateOfCurrentHivStatus(dateOfCurrentStatus);
                                if(lhsu.getClientEnrolledInCare() !=null && lhsu.getClientEnrolledInCare().equalsIgnoreCase("Yes"))
                                ovc.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                                else if(lhsu.getEnrolledOnART() !=null && lhsu.getEnrolledOnART().equalsIgnoreCase("Yes"))
                                ovc.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                                else
                                ovc.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_NO_NUM);
                                if(ovc.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
                                {
                                    ovc.setDateEnrolledOnTreatment(dateOfCurrentStatus);
                                    if(lhsu.getOrganizationClientIsReferred() !=null && !lhsu.getOrganizationClientIsReferred().equalsIgnoreCase("none") && !lhsu.getOrganizationClientIsReferred().equalsIgnoreCase("select"))
                                    ovc.setHivTreatmentFacilityId(lhsu.getOrganizationClientIsReferred());
                                }
                            }
                        }
                        if(lhsu.getRecordStatus() !=null && lhsu.getRecordStatus().equalsIgnoreCase("active"))
                        {
                            if(ovc.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                            {
                                ovc.setCurrentHivStatus(ovc.getBaselineHivStatus());
                                ovc.setDateOfCurrentHivStatus(ovc.getDateOfBaselineHivStatus());
                            }
                            else
                            {
                                ovc.setCurrentHivStatus(HivPropertiesManager.getHivStatus(lhsu.getHivStatus()).getCode());
                                ovc.setDateOfCurrentHivStatus(dateOfCurrentStatus);
                            }
                            if(ovc.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                            {
                                if(lhsu.getClientEnrolledInCare() !=null && lhsu.getClientEnrolledInCare().equalsIgnoreCase("Yes"))
                                ovc.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                                else if(lhsu.getEnrolledOnART() !=null && lhsu.getEnrolledOnART().equalsIgnoreCase("Yes"))
                                ovc.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                                else
                                ovc.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_NO_NUM);
                                if(ovc.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
                                {
                                    ovc.setDateEnrolledOnTreatment(dateOfCurrentStatus);
                                    if(lhsu.getOrganizationClientIsReferred() !=null && !lhsu.getOrganizationClientIsReferred().equalsIgnoreCase("none") && !lhsu.getOrganizationClientIsReferred().equalsIgnoreCase("select"))
                                    ovc.setHivTreatmentFacilityId(lhsu.getOrganizationClientIsReferred());
                                }
                            }
                        }
                        if(ovc.getBaselineHivStatus()<ovc.getCurrentHivStatus())
                        {
                            ovc.setCurrentHivStatus(ovc.getBaselineHivStatus());
                            ovc.setDateOfCurrentHivStatus(ovc.getDateOfBaselineHivStatus());
                        }
                        util.getChildEnrollmentDaoInstance().updateOvc(ovc, true, false);
                    }
                }
                else
                {
                    ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(lhsu.getClientId());
                    if(ahm !=null)
                    {
                        if(lhsu.getPointOfUpdate() !=null && lhsu.getPointOfUpdate().equalsIgnoreCase("enrollment"))
                        {
                            ahm.setBaselineHivStatus(HivPropertiesManager.getHivStatus(lhsu.getHivStatus()).getCode());
                            ahm.setDateOfBaselineHivStatus(dateOfCurrentStatus);
                            if(ahm.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                            {
                                ahm.setCurrentHivStatus(HivPropertiesManager.getHivStatus(lhsu.getHivStatus()).getCode());
                                ahm.setDateOfCurrentHivStatus(dateOfCurrentStatus);
                                if(lhsu.getClientEnrolledInCare() !=null && lhsu.getClientEnrolledInCare().equalsIgnoreCase("Yes"))
                                ahm.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                                else if(lhsu.getEnrolledOnART() !=null && lhsu.getEnrolledOnART().equalsIgnoreCase("Yes"))
                                ahm.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                                else
                                ahm.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_NO_NUM);
                                if(ahm.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
                                {
                                    ahm.setDateEnrolledOnTreatment(dateOfCurrentStatus);
                                    if(lhsu.getOrganizationClientIsReferred() !=null && !lhsu.getOrganizationClientIsReferred().equalsIgnoreCase("none") && !lhsu.getOrganizationClientIsReferred().equalsIgnoreCase("select"))
                                    ahm.setHivTreatmentFacilityId(lhsu.getOrganizationClientIsReferred());
                                }
                            }
                        }
                        if(lhsu.getRecordStatus() !=null && lhsu.getRecordStatus().equalsIgnoreCase("active"))
                        {
                            if(ahm.getBaselineHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                            {
                                ahm.setCurrentHivStatus(ahm.getBaselineHivStatus());
                                ahm.setDateOfCurrentHivStatus(ahm.getDateOfBaselineHivStatus());
                            }
                            else
                            {
                                ahm.setCurrentHivStatus(HivPropertiesManager.getHivStatus(lhsu.getHivStatus()).getCode());
                                ahm.setDateOfCurrentHivStatus(dateOfCurrentStatus);
                            }
                            if(ahm.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                            {
                                if(lhsu.getClientEnrolledInCare() !=null && lhsu.getClientEnrolledInCare().equalsIgnoreCase("Yes"))
                                ahm.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                                else if(lhsu.getEnrolledOnART() !=null && lhsu.getEnrolledOnART().equalsIgnoreCase("Yes"))
                                ahm.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_YES_NUM);
                                else
                                ahm.setEnrolledOnTreatment(AppConstant.ENROLLED_ON_TREATMENT_NO_NUM);
                                if(ahm.getEnrolledOnTreatment()==AppConstant.ENROLLED_ON_TREATMENT_YES_NUM)
                                {
                                    ahm.setDateEnrolledOnTreatment(dateOfCurrentStatus);
                                    if(lhsu.getOrganizationClientIsReferred() !=null && !lhsu.getOrganizationClientIsReferred().equalsIgnoreCase("none") && !lhsu.getOrganizationClientIsReferred().equalsIgnoreCase("select"))
                                    ahm.setHivTreatmentFacilityId(lhsu.getOrganizationClientIsReferred());
                                }
                            }
                        }
                        if(ahm.getBaselineHivStatus()<ahm.getCurrentHivStatus())
                        {
                            ahm.setCurrentHivStatus(ahm.getBaselineHivStatus());
                            ahm.setDateOfCurrentHivStatus(ahm.getDateOfBaselineHivStatus());
                        }
                        util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                        
                    }
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processWithdrawalStatusRecords(String parentFolderPath)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processWithdrawalStatusRecords");
    try
    {
        //BeneficiaryAgeManager bam=new BeneficiaryAgeManager();
        List list=lxmlr.readOvcWithdrawalFromXml(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            
            Ovc ovc=null;
            AdultHouseholdMember ahm=null;
            HouseholdEnrollment hhe=null;
            LegacyOvcWithdrawal withdrawal=null;
            int enrollmentStatus=0;
            
            Date dateOfCurrentStatus=null;
            
            for(Object obj:list)
            {
                enrollmentStatus=0;
                withdrawal=(LegacyOvcWithdrawal)obj;
                
                if(withdrawal.getReasonWithdrawal() !=null)
                {
                     if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.GRADUATED))
                     {
                        enrollmentStatus=AppConstant.GRADUATED_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.LOST_TO_FOLLOWUP))
                     {
                        enrollmentStatus=AppConstant.LOST_TO_FOLLOWUP_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.DIED))
                     {
                        enrollmentStatus=AppConstant.DIED_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.MIGRATED))
                     {
                        enrollmentStatus=AppConstant.MIGRATED_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.TRANSFERED))
                     {
                        enrollmentStatus=AppConstant.TRANSFERED_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.VOLUNTARILY_WITHDRAWN))
                     {
                        enrollmentStatus=AppConstant.VOLUNTARILY_WITHDRAWN_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.INACTIVE))
                     {
                        enrollmentStatus=AppConstant.INACTIVE_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.REENROLLED_STATUS))
                     {
                        enrollmentStatus=AppConstant.REENROLLED_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.AGED_OUT))
                     {
                        enrollmentStatus=AppConstant.AGED_OUT_NUM;
                     }
                     else if(withdrawal.getReasonWithdrawal().equalsIgnoreCase(AppConstant.MIGRATED))
                     {
                        enrollmentStatus=AppConstant.MIGRATED_NUM;
                     }
                }
                dateOfCurrentStatus=DateManager.getDateInstance(withdrawal.getDateOfWithdrawal());
                
                ovc=util.getChildEnrollmentDaoInstance().getOvc(withdrawal.getOvcId());
                if(ovc !=null)
                {
                    if(enrollmentStatus>1)
                    {
                        ovc.setCurrentEnrollmentStatus(enrollmentStatus);
                        ovc.setDateOfCurrentEnrollmentStatus(dateOfCurrentStatus);
                        util.getChildEnrollmentDaoInstance().updateOvc(ovc,false,false);
                    }
                }
                else
                {
                    ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(withdrawal.getOvcId());
                    if(ahm !=null)
                    {
                        if(enrollmentStatus>1)
                        {
                            ahm.setCurrentEnrollmentStatus(enrollmentStatus);
                            ahm.setDateOfCurrentEnrollmentStatus(dateOfCurrentStatus);
                            util.getAdultHouseholdMemberDaoInstance().updateAdultHouseholdMember(ahm);
                        }
                        if(withdrawal.getType() !=null && withdrawal.getType().equalsIgnoreCase("household"))
                        {
                            hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(withdrawal.getOvcId());
                            if(hhe !=null)
                            {
                                if(enrollmentStatus>1)
                                {
                                    hhe.setCurrentEnrollmentStatus(enrollmentStatus);
                                    hhe.setDateOfCurrentStatus(dateOfCurrentStatus);
                                    util.getHouseholdEnrollmentDaoInstance().updateHouseholdEnrollment(hhe);
                                }
                            }
                        }
                    }
                    else
                    {
                        hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(withdrawal.getOvcId());
                        if(hhe !=null)
                        {
                            if(enrollmentStatus>1)
                            {
                                hhe.setCurrentEnrollmentStatus(enrollmentStatus);
                                hhe.setDateOfCurrentStatus(dateOfCurrentStatus);
                                util.getHouseholdEnrollmentDaoInstance().updateHouseholdEnrollment(hhe);
                            }
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
    return resultList;
}
public List processHivRiskAssessmentRecords(String parentFolderPath)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processHivRiskAssessmentRecords");
    try
    {
        BeneficiaryAgeManager bam=new BeneficiaryAgeManager();
        List list=lxmlr.importHivRiskAssessmentChecklist(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            
            HouseholdEnrollment hhe=null;
            Ovc ovc=null;
            LegacyHivRiskAssessmentChecklist lhrac=null;
            HivRiskAssessment hrac=null;
            HivRiskAssessment dupHrac=null;
            
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            String volunteerId="xxxxxxxxxxx";
            for(Object obj:list)
            {
                lhrac=(LegacyHivRiskAssessmentChecklist)obj;
                ovc=util.getChildEnrollmentDaoInstance().getOvc(lhrac.getOvcId());
                if(ovc !=null)
                hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ovc.getHhUniqueId());
                dateOfAssessment=DateManager.getDateInstance(lhrac.getDateOfAssessment());
                lastModifiedDate=DateManager.getDateInstance(lhrac.getLastModifiedDate());
                                  
                hrac=new HivRiskAssessment();
                
                //hrac.setAgeAtRiskAssessment(0);
                //hrac.setAgeUnitAtRiskAssessment(0);
                hrac.setBloodTransfusionQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getBloodTransfusionQuestion()));
                hrac.setChildAgeQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getChildAgeQuestion()));
                hrac.setChildAtRiskQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getChildAtRiskQuestion()));
                hrac.setChildSickQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getChildSickQuestion()));
                hrac.setChildTestedQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getChildTestedQuestion()));
                hrac.setChronicallyIllQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getChronicallyIllQuestion()));
                hrac.setDateCreated(lastModifiedDate);
                hrac.setDateOfAssessment(dateOfAssessment);
                hrac.setHivParentQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getHivParentQuestion()));
                hrac.setHivStatusAtRiskAssessment(HivPropertiesManager.getHivStatus(lhrac.getHivStatus()).getCode());
                hrac.setHivStatusQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getHivStatusQuestion()));
                hrac.setMuacbmiQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getMuacbmiQuestion()));
                hrac.setSexualAbuseQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getSexualAbuseQuestion()));
                hrac.setSexuallyActiveQuestion(HivRiskAssessmentManager.getYesNoValue(lhrac.getSexuallyActiveQuestion()));
                hrac.setLastModifiedDate(lastModifiedDate);
                hrac.setRecordedBy(userName);
                
                 if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lhrac.getServiceProviderName(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId="xxxxxxxxxxx";
                 }
                hrac.setNameOfAssessor(volunteerId);
                
                hrac.setOvcId(lhrac.getOvcId());
                hrac.setRecordedBy(userName);
                //System.err.println("hrac.getOvcId() is "+hrac.getOvcId());
                dupHrac=util.getHivRiskAssessmentDaoInstance().getHivRiskAssessment(hrac.getOvcId(), dateOfAssessment);
                hrac=HivRiskAssessmentManager.getHivRiskAssessmentWithRiskStatus(hrac);
                if(dupHrac ==null)
                {
                    util.getHivRiskAssessmentDaoInstance().saveHivRiskAssessment(hrac);
                }
                else
                util.getHivRiskAssessmentDaoInstance().updateHivRiskAssessment(hrac);
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processReferralRecordsRecords(String parentFolderPath)
{
    //int dataUploadId,
    List resultList=new ArrayList();
    System.err.println("Inside processReferralRecordsRecords");
    try
    {
        List list=lxmlr.readReferralRecordsFromXml(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            HouseholdEnrollment hhe=null;
            AdultHouseholdMember ahm=null;
            Ovc ovc=null;
            LegacyOvcReferral lreferral=null;
            HouseholdReferral referral=null;
            HouseholdReferral dupReferral=null;
            ReferralFacility rf=null;
            
            Date dateOfReferral=null;
            Date lastModifiedDate=null;
            String volunteerId="xxxxxxxxxxx";
            String level4OuId=null;
            String referringOrganizationId=null;
            String receivingOrganizationId=null;
            int beneficiaryType=0;
            //Date defaultDate=DateManager.getDefaultStartDateInstance();
            String concatenatedHealthyServices=null;
            String concatenatedSafetyServices=null;
            String concatenatedSchoolServices=null;
            String concatenatedStableServices=null;
            //String concatenatedReferralServices=null;
            
            String[] psychoServices=null;
            String[] nutritionalServices=null;
            String[] healthServices=null;
            String[] educationalServices=null;
            String[] protectionServices=null;
            String[] shelterServices=null;
            String[] economicServices=null;
            //String[] referralServices=null;
            
            String legacyPsychoServices=null;
            String legacyNutritionalServices=null;
            String legacyHealthServices=null;
            String legacyEducationalServices=null;
            String legacyProtectionServices=null;
            String legacyShelterServices=null;
            String legacyEconomicServices=null;
            //String legacyReferralServices=null;
                        
            for(Object obj:list)
            {
                rf=null;
                concatenatedHealthyServices=null;
                concatenatedSafetyServices=null;
                concatenatedSchoolServices=null;
                concatenatedStableServices=null;
                referringOrganizationId=AppConstant.DEFAULTUID;
                receivingOrganizationId=AppConstant.DEFAULTUID;
                hhe=null;
                lreferral=(LegacyOvcReferral)obj;
                ovc=util.getChildEnrollmentDaoInstance().getOvc(lreferral.getOvcId());
                if(ovc !=null)
                {
                    beneficiaryType=AppConstant.OVC_TYPE_NUM;
                    hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ovc.getHhUniqueId());
                }
                else
                {
                    ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(lreferral.getOvcId());
                    if(ahm !=null)
                    {
                        beneficiaryType=AppConstant.CAREGIVER_TYPE_NUM;
                        hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ahm.getHhUniqueId());
                    }
                    else
                    {
                        beneficiaryType=AppConstant.CAREGIVER_TYPE_NUM;
                        hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(lreferral.getOvcId());
                    }
                }
                dateOfReferral=DateManager.getDateInstance(lreferral.getDateOfReferral());
                lastModifiedDate=DateManager.getDateInstance(DateManager.getCurrentDate());
                if(hhe !=null)
                {
                    level4OuId=hhe.getOrganizationUnit();
                    //The CBO usually refers, so, referringOrganizationId is CBOId
                    referringOrganizationId=hhe.getCboId();
                }
                if(lreferral.getNameOfOrganizationChildIsreferred() !=null && lreferral.getNameOfOrganizationChildIsreferred().trim().length()>2)
                rf=util.getReferralFacilityDaoInstance().createReferralFacility(lreferral.getNameOfOrganizationChildIsreferred(), "", null, null, lreferral.getNameOfPersonReferring(),0.0,0.0, level4OuId, userName, 1);
                if(rf !=null)
                receivingOrganizationId=rf.getFacilityId();
                
                referral=new HouseholdReferral();
                
                referral.setBeneficiaryId(lreferral.getOvcId());
                if(ovc !=null)
                referral.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
                else if(ahm !=null)
                referral.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
                else if(hhe !=null)
                referral.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
                else
                {
                    if(lreferral.getType() !=null)
                    {
                        if(lreferral.getType().equalsIgnoreCase("ovc") || lreferral.getType().equalsIgnoreCase("vc"))
                        referral.setBeneficiaryType(AppConstant.OVC_TYPE_NUM);
                        else if((lreferral.getType().equalsIgnoreCase("caregiver") || lreferral.getType().equalsIgnoreCase("cgiver")))
                        referral.setBeneficiaryType(AppConstant.CAREGIVER_TYPE_NUM);
                        else if((lreferral.getType().equalsIgnoreCase("household") || lreferral.getType().equalsIgnoreCase("hh")))
                        referral.setBeneficiaryType(AppConstant.HOUSEHOLD_TYPE_NUM);
                    }
                }
                 if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lreferral.getNameOfPersonReferring(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId="xxxxxxxxxxx";
                 }
                referral.setCommunityWorkerId(volunteerId);
                referral.setDateCreated(lastModifiedDate);
                referral.setDateOfReferral(dateOfReferral);
                referral.setLastModifiedDate(lastModifiedDate);
                referral.setRecordedBy(userName);
                referral.setReferringOrganization(referringOrganizationId);
                referral.setReceivingOrganization(receivingOrganizationId);
                referral.setReferralCompleted(SingleOptionManager.getSingleChoiceOption(lreferral.getReferralCompleted()).getValue());
                
                legacyPsychoServices=lreferral.getProtectionServices();
                legacyNutritionalServices=lreferral.getNutritionalServices();
                legacyHealthServices=lreferral.getHealthServices();
                legacyEducationalServices=lreferral.getEducationalServices();
                legacyProtectionServices=lreferral.getProtectionServices();
                legacyShelterServices=lreferral.getShelterServices();
                legacyEconomicServices=lreferral.getEconomicServices();
                
                psychoServices=getLegacyOvcServices(legacyPsychoServices);
                nutritionalServices=getLegacyOvcServices(legacyNutritionalServices);
                healthServices=getLegacyOvcServices(legacyHealthServices);
                educationalServices=getLegacyOvcServices(legacyEducationalServices);
                protectionServices=getLegacyOvcServices(legacyProtectionServices);
                shelterServices=getLegacyOvcServices(legacyShelterServices);
                economicServices=getLegacyOvcServices(legacyEconomicServices);
                                
                concatenatedHealthyServices=getConcatenatedServiceCodes(healthServices,beneficiaryType,AppConstant.HEALTHDOMAINCODE);
                if(legacyNutritionalServices !=null && legacyNutritionalServices.trim().length()>0)
                {
                    if(concatenatedHealthyServices !=null)
                    concatenatedHealthyServices+=","+getConcatenatedServiceCodes(nutritionalServices,beneficiaryType,AppConstant.HEALTHDOMAINCODE);
                    else
                    concatenatedHealthyServices=getConcatenatedServiceCodes(nutritionalServices,beneficiaryType,AppConstant.HEALTHDOMAINCODE);
                    
                }
                referral.setHealthServices(LegacyOvcServiceManager.cleanServiceName(concatenatedHealthyServices));
                
                concatenatedSafetyServices=getConcatenatedServiceCodes(psychoServices,beneficiaryType,AppConstant.SAFEDOMAINCODE);
                if(legacyShelterServices !=null && legacyShelterServices.trim().length()>0)
                {
                    if(concatenatedSafetyServices !=null)
                    concatenatedSafetyServices+=","+getConcatenatedServiceCodes(shelterServices,beneficiaryType,AppConstant.SAFEDOMAINCODE);
                    else
                    concatenatedSafetyServices=getConcatenatedServiceCodes(shelterServices,beneficiaryType,AppConstant.SAFEDOMAINCODE);
                }
                referral.setSafetyServices(LegacyOvcServiceManager.cleanServiceName(concatenatedSafetyServices));
                
                concatenatedSchoolServices=getConcatenatedServiceCodes(educationalServices,beneficiaryType,AppConstant.SCHDOMAINCODE);
                referral.setSchooledServices(LegacyOvcServiceManager.cleanServiceName(concatenatedSchoolServices));
                
                concatenatedStableServices=getConcatenatedServiceCodes(protectionServices,beneficiaryType,AppConstant.STABLEDOMAINCODE);
                if(legacyEconomicServices !=null && legacyEconomicServices.trim().length()>0)
                {
                    if(concatenatedStableServices !=null)
                    concatenatedStableServices+=","+getConcatenatedServiceCodes(economicServices,beneficiaryType,AppConstant.STABLEDOMAINCODE);
                    else
                    concatenatedStableServices=getConcatenatedServiceCodes(economicServices,beneficiaryType,AppConstant.STABLEDOMAINCODE);
                } 
                referral.setStableServices(LegacyOvcServiceManager.cleanServiceName(concatenatedStableServices));
               
                
                //System.err.println("hrac.getOvcId() is "+hrac.getOvcId());
                dupReferral=util.getHouseholdReferralDaoInstance().getHouseholdReferral(referral.getBeneficiaryId(),referral.getDateOfReferral());
                if(dupReferral ==null)
                {
                    util.getHouseholdReferralDaoInstance().saveHouseholdReferral(referral);
                }
                else
                {
                    util.getHouseholdReferralDaoInstance().updateHouseholdReferral(referral);
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processCareAndSupportRecords(String parentFolderPath)
{
    List resultList=new ArrayList();
    System.err.println("Inside processCareAndSupportRecords");
    try
    {
        List list=lxmlr.importCareAndSupportChecklist(1,parentFolderPath);
        int count=0;
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyCareAndSupportChecklist lcsc=null;
            HouseholdEnrollment hhe=null;
            AdultHouseholdMember ahm=null;
            CareAndSupportChecklist csc=null;
            CareAndSupportChecklist dupCsc=null;
            Ovc ovc=null;
            
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            Date defaultDate=DateManager.getDefaultStartDateInstance();
            String volunteerId=AppConstant.DEFAULTUID;
            for(Object obj:list)
            {
                lcsc=(LegacyCareAndSupportChecklist)obj;
                ovc=util.getChildEnrollmentDaoInstance().getOvc(lcsc.getClientId());
                if(ovc !=null)
                {
                    hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ovc.getHhUniqueId());
                }
                else
                {
                    ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(lcsc.getClientId());
                    if(ahm !=null)
                    {
                        hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ahm.getHhUniqueId());
                    }
                }
                dateOfAssessment=DateManager.getDateInstance(lcsc.getDateOfAssessment());
                lastModifiedDate=DateManager.getDateInstance(lcsc.getLastModifiedDate());
                                  
                csc=new CareAndSupportChecklist();
                csc.setBeneficiaryId(lcsc.getClientId());
                csc.setChildHasFever(0);
                csc.setChildHasNightSweat(0);
                csc.setChildHasSwelling(0);
                csc.setChildLossinWeight(0);
                csc.setCoughSymptom(0);
                csc.setDateCreated(lastModifiedDate);
                csc.setDateOfAssessment(dateOfAssessment);
                csc.setDateOfNextAppointment(defaultDate);
                csc.setLastModifiedDate(lastModifiedDate);
                csc.setDateOfViralLoadSampleCollection(DateManager.getDateInstance(lcsc.getDateOfViralLoadTest()));
                csc.setEnrolledOnTreatment(SingleOptionManager.getSingleChoiceOption(lcsc.getEnrolledOnTreatment()).getValue());
                csc.setFacilityId(lcsc.getTreatmentFacility());
                csc.setMissedARVsRecently(SingleOptionManager.getSingleChoiceOption(lcsc.getSkippedARV()).getValue());
                csc.setMonthsOfTransportationSupport(0);
                csc.setPickedUpMedication(SingleOptionManager.getSingleChoiceOption(lcsc.getMedicationPickedUp()).getValue());
                csc.setReasonViralLoadNotDone(null);
                csc.setReasonsPeopleSkipARV(lcsc.getReasonsPeopleSkipARV());
                csc.setRecordedBy(userName);
                csc.setReceivedTransportationSupport(SingleOptionManager.getSingleChoiceOption(lcsc.getTransportationSupport()).getValue());
                csc.setSoresRashPainExperience(0);
                csc.setViralLoadTestDone(SingleOptionManager.getSingleChoiceOption(lcsc.getViralLoadTestDone()).getValue());
                csc.setViralLoadResult(0);
                csc.setViralLoadResultKnown(SingleOptionManager.getSingleChoiceOption(lcsc.getReceivedTestResult()).getValue());
                csc.setDateOfLastDrugPickup(DateManager.getDefaultStartDateInstance());
                csc.setDateOfViralLoadResult(DateManager.getDefaultStartDateInstance());
                //hhe=ovc.getHhe();
                 if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lcsc.getVolunteerName(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId=AppConstant.DEFAULTUID;
                 }
                csc.setVolunteerName(volunteerId);
                
                dupCsc=util.getCareAndSupportChecklistDaoInstance().getCareAndSupportChecklist(csc.getBeneficiaryId(), dateOfAssessment);
                if(dupCsc==null)
                {
                    util.getCareAndSupportChecklistDaoInstance().saveCareAndSupportChecklist(csc);
                    count++;
                    //System.err.println(count+" Care and support record saved");
                }
                else
                {
                    csc.setRecordId(dupCsc.getRecordId());
                    util.getCareAndSupportChecklistDaoInstance().updateCareAndSupportChecklist(csc);
                    count++;
                    //System.err.println(count+" Care and support record updated");
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processCareplanAchievementRecords(String parentFolderPath)
{
    List resultList=new ArrayList();
    System.err.println("Inside processCareplanAchievementRecords");
    try
    {
        List list=lxmlr.importCareplanAchievementChecklist(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyCareplanAchievement lcpa=null;
            HouseholdEnrollment hhe=null;
            AdultHouseholdMember ahm=null;
            CareplanAchievementChecklist cpa=null;
            CareplanAchievementChecklist dupCpa=null;
            Ovc ovc=null;
            
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            Date defaultDate=DateManager.getDefaultStartDateInstance();
            String volunteerId=AppConstant.DEFAULTUID;
            for(Object obj:list)
            {
                lcpa=(LegacyCareplanAchievement)obj;
                hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(lcpa.getHhUniqueId());
                
                dateOfAssessment=DateManager.getDateInstance(lcpa.getDateOfAssessment());
                lastModifiedDate=DateManager.getDateInstance(lcpa.getLastModifiedDate());
                                  
                cpa=new CareplanAchievementChecklist();
                cpa.setHhUniqueId(lcpa.getClientId());
                cpa.setAllChildrenHaveBirthCert(SingleOptionManager.getSingleChoiceOption(lcpa.getSft_birthCert()).getValue());
                cpa.setCgiversEconomicActivity(SingleOptionManager.getSingleChoiceOption(lcpa.getStb_cgPartEconServ()).getValue());
                cpa.setChildrenEnrolledInSchool(SingleOptionManager.getSingleChoiceOption(lcpa.getSch_vcEnrolledInSecondarySch()).getValue());
                cpa.setChildrenHivStatusknown(SingleOptionManager.getSingleChoiceOption(lcpa.getHth_hivknowledge()).getValue());
                cpa.setChildrenNotUndernourished(SingleOptionManager.getSingleChoiceOption(lcpa.getStb_hungryNoFood()).getValue());
                cpa.setDateCreated(lastModifiedDate);
                cpa.setLastModifiedDate(lastModifiedDate);
                cpa.setDateOfAssessment(dateOfAssessment);
                cpa.setDocumentedViralLoadResult(0);
                cpa.setEmotionalSupportTeamIdentification(SingleOptionManager.getSingleChoiceOption(lcpa.getStb_socEmotSupport()).getValue());
                cpa.setHivPosAdolscentsLinked(SingleOptionManager.getSingleChoiceOption(lcpa.getHth_hivOnArt()).getValue());
                cpa.setHivPreventionKnowledge(SingleOptionManager.getSingleChoiceOption(lcpa.getHth_hivknowledge()).getValue());
                cpa.setRecordedBy(userName);
                cpa.setRegularSchoolAttendance(SingleOptionManager.getSingleChoiceOption(lcpa.getSch_schAttendance()).getValue());
                cpa.setStableAdultInHousehold(SingleOptionManager.getSingleChoiceOption(lcpa.getStb_resiliency()).getValue());
                cpa.setViolenceIncidenceReport(0);
                cpa.setScore(lcpa.getScore());
                cpa.setAdolescentInVoctraining(SingleOptionManager.getSingleChoiceOption(lcpa.getSch_adolInVoctraining()).getValue());
                cpa.setCaregiverDisclosedHivStatus(SingleOptionManager.getSingleChoiceOption(lcpa.getHth_hivdisclosed()).getValue());
                cpa.setCgiversCompletedParentingCourse(SingleOptionManager.getSingleChoiceOption(lcpa.getSft_cgcompletedTwoModules()).getValue());
                cpa.setChildHeadedHhLinkedToServices(SingleOptionManager.getSingleChoiceOption(lcpa.getHth_vcInneedOfHthServices()).getValue());
                cpa.setChildrenInHhHaveAdequateHousingAndSpace(SingleOptionManager.getSingleChoiceOption(lcpa.getSft_vcSafeFromAbuse()).getValue());
                cpa.setHhGraduated(SingleOptionManager.getSingleChoiceOption(lcpa.getGraduated()).getValue());
                cpa.setChildrenEnrolledInEarlyChildCare(SingleOptionManager.getSingleChoiceOption(lcpa.getSch_earlyChildCare()).getValue());
                cpa.setHhDemonstratedAbilityToMeetGoals(SingleOptionManager.getSingleChoiceOption(lcpa.getStb_resiliency()).getValue());
                cpa.setVcAtRiskReferredForChildProtection(SingleOptionManager.getSingleChoiceOption(lcpa.getSft_vcreferredForCps()).getValue());
                cpa.setChildrenReferredForHivTesting(SingleOptionManager.getSingleChoiceOption(lcpa.getHth_vchivrisk()).getValue());
                cpa.setChildrenReferredReceivedTestingServices(SingleOptionManager.getSingleChoiceOption(lcpa.getHth_vcreftested()).getValue());
                cpa.setChildWithdrawnOrSad(SingleOptionManager.getSingleChoiceOption(lcpa.getSft_vcsad()).getValue());
                
                //hhe=ovc.getHhe();
                 if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lcpa.getVolunteerId(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId=AppConstant.DEFAULTUID;
                 }
                cpa.setVolunteerName(volunteerId);
                
                dupCpa=util.getCareplanAchievementChecklistDaoInstance().getCareplanAchievementChecklist(cpa.getHhUniqueId(), dateOfAssessment);
                if(dupCpa==null)
                {
                    util.getCareplanAchievementChecklistDaoInstance().saveCareplanAchievementChecklist(cpa);
                }
                else
                {
                    cpa.setRecordId(dupCpa.getRecordId());
                    util.getCareplanAchievementChecklistDaoInstance().updateCareplanAchievementChecklist(cpa);
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processNutritionAssessmentRecords(String parentFolderPath)
{
    List resultList=new ArrayList();
    System.err.println("Inside processNutritionAssessmentRecords");
    try
    {
        System.err.println("Inside processNutritionAssessmentRecords try block");
        List list=lxmlr.readNutritionAssessmentFromXml(1,parentFolderPath);
        if(list==null || list.isEmpty())
         System.err.println("list is null or lis is empty");   
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyNutritionAssessment lna=null;
            HouseholdEnrollment hhe=null;
            int count=0;
            NutritionAssessment na=null;
            NutritionAssessment dupNa=null;
            Ovc ovc=null;
            
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            Date dateOfLastWeight=null;
            Date defaultDate=DateManager.getDefaultStartDateInstance();
            String volunteerId=AppConstant.DEFAULTUID;
            for(Object obj:list)
            {
                lna=(LegacyNutritionAssessment)obj;
                ovc=util.getChildEnrollmentDaoInstance().getOvc(lna.getOvcId());
                if(ovc !=null)
                hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ovc.getHhUniqueId());
                
                dateOfAssessment=DateManager.getDateInstance(lna.getDateOfAssessment());
                lastModifiedDate=DateManager.getDateInstance(lna.getDateModified());
                dateOfLastWeight=DateManager.getDateInstance(lna.getDateOfLastWeight());
                
                na=new NutritionAssessment();
                na.setAgeAtAssessment(0);
                na.setOvcId(lna.getOvcId());
                na.setAssessmentNo(lna.getAssessmentNo());
                na.setBmi(lna.getBodyMassIndex());
                na.setChangeInWeight(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getChangeInWeight()));
                na.setDateCreated(lastModifiedDate);
                na.setDateOfAssessment(dateOfAssessment);
                na.setDateOfLastWeight(dateOfLastWeight);
                na.setLastModifiedDate(lastModifiedDate);
                na.setFoodSecurityAndDietQ1(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ1()));
                na.setFoodSecurityAndDietQ2(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ2()));
                na.setFoodSecurityAndDietQ3(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ3()));
                na.setFoodSecurityAndDietQ4(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ4()));
                na.setFoodSecurityAndDietQ5(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ5()));
                na.setFoodSecurityAndDietQ6(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ6()));
                na.setFoodSecurityAndDietQ7(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ7()));
                na.setFoodSecurityAndDietQ8(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ8()));
                na.setFoodSecurityAndDietQ9(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getFoodSecurityAndDietQ9()));
                na.setHeight(lna.getHeight());
                na.setHygieneQ1(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getHygieneQ1()));
                na.setHygieneQ2(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getHygieneQ2()));
                na.setHygieneQ3(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getHygieneQ3()));
                na.setHygieneQ4(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getHygieneQ4()));
                na.setLastWeight(lna.getLastWeight());
                na.setMuac(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getMuac()));
                na.setMuacFacility(lna.getMuacFacility());
                na.setNutritionalStatus(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getNutritionalStatus()));
                na.setOedema(NutritionAssessmentAttributesManager.getNutritionAssessmentAttributeValue(lna.getOedema()));
                na.setRecordStatus(lna.getNaRecordStatus());
                na.setRecordedBy(userName);
                //na.setVolunteerName(volunteerId);
                na.setWeight(lna.getWeight());
                na.setYellowMuacServices(lna.getYellowMuacServices());              
                na.setVolunteerName(volunteerId);
                
                dupNa=util.getNutritionalAssessmentDaoInstance().getNutritionAssessment(na.getOvcId(), na.getDateOfAssessment());
                if(dupNa==null)
                {
                    util.getNutritionalAssessmentDaoInstance().saveNutritionAssessment(na);
                    count++;
                    System.err.println("Nutrition assessment "+count+" saved");
                }
                else
                {
                    na.setRecordId(dupNa.getRecordId());
                    util.getNutritionalAssessmentDaoInstance().updateNutritionAssessment(na);
                    count++;
                    System.err.println("Nutrition assessment "+count+" updated");
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processCaregiverExpenditureAndSchoolAttendanceRecords(String parentFolderPath)
{
    List resultList=new ArrayList();
    System.err.println("Inside processCaregiverExpenditureAndSchoolAttendanceRecords");
    try
    {
        List list=lxmlr.importCaregiverExpenditureAndSchoolAttendance(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyCaregiverExpenditureAndSchoolAttendance lceasa=null;
            HouseholdEnrollment hhe=null;
            AdultHouseholdMember ahm=null;
            CaregiverAccessToEmergencyFund caef=null;
            CaregiverAccessToEmergencyFund dupCaef=null;
            ChildEducationPerformanceAssessment cepa=null;
            ChildEducationPerformanceAssessment dupCepa=null;
            
            Date dateOfAssessment=null;
            Date lastModifiedDate=null;
            Date defaultDate=DateManager.getDefaultStartDateInstance();
            String volunteerId=AppConstant.DEFAULTUID;
            String ovcId=null;
            for(Object obj:list)
            {
                lceasa=(LegacyCaregiverExpenditureAndSchoolAttendance)obj;
                ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(lceasa.getCaregiverId());
                if(ahm !=null)
                hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(ahm.getHhUniqueId());
                
                dateOfAssessment=DateManager.getDateInstance(lceasa.getDateOfAssessment());
                lastModifiedDate=DateManager.getDateInstance(lceasa.getLastModifiedDate());
                                 
                cepa=new ChildEducationPerformanceAssessment();
                caef=new CaregiverAccessToEmergencyFund();
                caef.setAccessMoneyToPay(SingleOptionManager.getSingleChoiceOption(lceasa.getAccessMoney()).getValue());
                caef.setBeneficiaryId(lceasa.getCaregiverId());
                caef.setDateCreated(DateManager.getCurrentDateInstance());
                caef.setDateOfAssessment(dateOfAssessment);
                caef.setLastModifiedDate(lastModifiedDate);
                caef.setRecordedBy(userName);
                caef.setSourceOfMoney(lceasa.getSourceOfMoney());
                caef.setUnexpectedExpenditure(SingleOptionManager.getSingleChoiceOption(lceasa.getUnexpectedExpenditure()).getValue());
                caef.setUrgentHhNeeds(lceasa.getUrgentHhNeeds());
                if(hhe !=null)
                 {
                    volunteerId=getCommunityWorkerIdFromName(lceasa.getVolunteerName(),userName,hhe.getOrganizationUnit());
                    if(volunteerId==null)
                    volunteerId=AppConstant.DEFAULTUID;
                 }
                caef.setVolunteerName(volunteerId);
                               
                dupCaef=util.getCaregiverAccessToEmergencyFundDaoInstance().getCaregiverAccessToEmergencyFund(caef.getBeneficiaryId(), dateOfAssessment);
                if(dupCaef==null)
                {
                    util.getCaregiverAccessToEmergencyFundDaoInstance().saveCaregiverAccessToEmergencyFund(caef);
                }
                else
                {
                    caef.setRecordId(dupCaef.getRecordId());
                    util.getCaregiverAccessToEmergencyFundDaoInstance().updateCaregiverAccessToEmergencyFund(caef);
                }
                
                ovcId=lceasa.getOvcId();
                if(ovcId !=null && ovcId.trim().length()>0)
                {
                    String[] ovcIdArray=null;
                    if(ovcId.indexOf(",") !=-1)
                    {
                        ovcIdArray=ovcId.split(",");
                        for(int i=0; i<ovcIdArray.length; i++)
                        {
                            cepa.setOvcId(ovcIdArray[i]);
                            cepa.setRegularSchoolAttendance(SingleOptionManager.getSingleChoiceOption(lceasa.getSchAttendance()).getValue());
                            cepa.setReasonsChildMissedSchoolOrVocTraining(lceasa.getReasonsForMissingSch());
                            cepa.setClassTeacherUid("NOMIS2");
                            cepa.setDateCreated(DateManager.getCurrentDateInstance());
                            cepa.setDateOfAssessment(dateOfAssessment);
                            cepa.setLastModifiedDate(lastModifiedDate);
                            cepa.setVolunteerName(volunteerId);
                            cepa.setRecordedBy(userName); 
                            dupCepa=util.getChildEducationPerformanceAssessmentDaoInstance().getChildEducationPerformanceAssessment(cepa.getOvcId(), cepa.getDateOfAssessment());
                            if(dupCepa==null)
                            {
                                util.getChildEducationPerformanceAssessmentDaoInstance().saveChildEducationPerformanceAssessment(cepa);
                            }
                            else
                            {
                                cepa.setRecordId(dupCepa.getRecordId());
                                util.getChildEducationPerformanceAssessmentDaoInstance().updateChildEducationPerformanceAssessment(cepa);
                            }
                        }
                    }
                    else
                    {
                        cepa.setOvcId(ovcId);
                        cepa.setRegularSchoolAttendance(SingleOptionManager.getSingleChoiceOption(lceasa.getSchAttendance()).getValue());
                        cepa.setReasonsChildMissedSchoolOrVocTraining(lceasa.getReasonsForMissingSch());
                        cepa.setClassTeacherUid("NOMIS2");
                        cepa.setDateCreated(DateManager.getCurrentDateInstance());
                        cepa.setDateOfAssessment(dateOfAssessment);
                        cepa.setLastModifiedDate(lastModifiedDate);
                        cepa.setVolunteerName(volunteerId);
                        cepa.setRecordedBy(userName);
                        if(dupCepa==null)
                        {
                            util.getChildEducationPerformanceAssessmentDaoInstance().saveChildEducationPerformanceAssessment(cepa);
                        }
                        else
                        {
                            cepa.setRecordId(dupCepa.getRecordId());
                            util.getChildEducationPerformanceAssessmentDaoInstance().updateChildEducationPerformanceAssessment(cepa);
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
    return resultList;
}
public List processCBORecords(String parentFolderPath)
{
    List resultList=new ArrayList();
    System.err.println("Inside processCBORecords");
    try
    {
        List list=lxmlr.readOrganizationRecordsFromXml(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            System.err.println("list.size() is "+list.size());
            LegacyOrganizationRecords lCbo=null;
            HouseholdEnrollment hhe=null;
            AdultHouseholdMember ahm=null;
            CommunityBasedOrganization cbo=null;
            CommunityBasedOrganization dupCbo=null;
            Ovc ovc=null;
            
            String cboCode=null;
            String volunteerId=AppConstant.DEFAULTUID;
            for(Object obj:list)
            {
                lCbo=(LegacyOrganizationRecords)obj;
                
                cboCode=getCBOCodeFromLegacyId(lCbo.getOrgCode());
                if(cboCode ==null)
                continue;
                cbo=new CommunityBasedOrganization();
                cbo.setAddress(lCbo.getAddress());
                cbo.setAssignedLevel3OuIds(getConcatenatedLevelOuIdsFromOuCodes(lCbo.getLga(),3));
                
                cbo.setCboCode(cboCode);
                cbo.setCboName(lCbo.getOrgName());
                cbo.setContactPersonEmail(lCbo.getContactEmail1());
                cbo.setContactPersonName(lCbo.getContactName1());
                cbo.setContactPersonPhone(lCbo.getContactPhone1());
                cbo.setDateCreated(DateManager.getCurrentDateInstance());
                cbo.setLastModifiedDate(DateManager.getCurrentDateInstance());
                cbo.setLatitude(lCbo.getLatitude());
                cbo.setLongitude(lCbo.getLongitude());
                cbo.setLegacyId(cleanLegacyId(lCbo.getOrgCode()));
                cbo.setLevel2OuId(getConcatenatedLevelOuIdsFromOuCodes(lCbo.getState(),2));
                cbo.setRecordedBy(userName);
                cbo.setServices(lCbo.getServices());
                //cbo.setUniqueId(lCbo.);
                               
                dupCbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganizationByLegacyId(cbo.getLegacyId());
                if(dupCbo==null)
                {
                    util.getCommunityBasedOrganizationDaoInstance().saveCommunityBasedOrganization(cbo);
                }
                else
                {
                    cbo.setUniqueId(dupCbo.getUniqueId());
                    util.getCommunityBasedOrganizationDaoInstance().updateCommunityBasedOrganization(cbo);
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
public List processWardRecords(String parentFolderPath)
{
    List resultList=new ArrayList();
    System.err.println("Inside processCBORecords");
    try
    {
        AppUtility.setCurrentImportRecordName("Ward/Community records");
        List list=lxmlr.readWardsFromXml(1,parentFolderPath);
        if(list !=null && !list.isEmpty())
        {
            int count=0;
            System.err.println("list.size() is "+list.size());
            LegacyWards lward=null;
            
            OrganizationUnit ou=null;
            OrganizationUnit level3Ou=null;
            OrganizationUnit dupOu=null;
            String level3Ouid=null;
            String wardCode=null;
            String volunteerId=AppConstant.DEFAULTUID;
            for(Object obj:list)
            {
                lward=(LegacyWards)obj;
                
                wardCode=getWardCodeFromLegacyId(lward.getWard_code());
                level3Ou=getLevel3OrganizationUnitFromLegacyId(lward.getWard_code(),3);
                //System.err.println("Ward code is "+wardCode);
                
                if(wardCode ==null || level3Ou==null)
                continue;
                ou=new OrganizationUnit();
                ou.setDateCreated(DateManager.getCurrentDateInstance());
                ou.setDescription("Ward from NOMIS2");
                ou.setLastModifiedDate(DateManager.getCurrentDateInstance());
                ou.setName(lward.getWard_name());
                ou.setOucode(wardCode);
                ou.setOulevel(4);
                ou.setParentId(level3Ou.getUid());
                ou.setLegacyId(cleanLegacyId(lward.getWard_code()));
                                                               
                dupOu=util.getOrganizationUnitDaoInstance().getOrganizationUnitByLegacyId(ou.getLegacyId());
                if(dupOu==null)
                {
                    util.getOrganizationUnitDaoInstance().saveOrganizationUnit(ou);
                    count++;
                    AppUtility.setCurrentImportRecordName("Ward records: "+count+" saved");
                    System.err.println("Ward at "+count+" with name "+ou.getName()+" and code "+ou.getOucode()+" saved");
                }
                else
                {
                    ou.setUid(dupOu.getUid());
                    util.getOrganizationUnitDaoInstance().updateOrganizationUnit(ou);
                    count++;
                    AppUtility.setCurrentImportRecordName("Ward records: "+count+" saved");
                    System.err.println("Ward at "+count+" with name "+ou.getName()+" and code "+ou.getOucode()+" updated");
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return resultList;
}
private String cleanLegacyId(String legacyId)
{
    String cleanedLegacyId=null;
    if(legacyId !=null && legacyId.indexOf("/") !=-1)
    {
        String ouCode=null;
        String[] codeArray=legacyId.split("/");
        for(int i=0; i<codeArray.length; i++)
        {
        
            ouCode=codeArray[i];
            if(ouCode !=null)
            {
                ouCode=ouCode.trim();
                if(ouCode.length()>0)
                {
                    if(i==0)
                    {
                        cleanedLegacyId=ouCode;
                    }
                    else
                    cleanedLegacyId+="/"+ouCode;
                }
            }
        }
    
    }
    return cleanedLegacyId;
}
private String getWardCodeFromLegacyId(String legacyId)
{
    String wardCode=null;
    if(legacyId !=null && legacyId.indexOf("/") !=-1)
    {
        String[] codeArray=legacyId.split("/");
        if(codeArray.length>3)
        {
            wardCode=codeArray[3];
            if(wardCode !=null)
            wardCode=wardCode.trim();
        }
    }
    return wardCode;
}
private OrganizationUnit getLevel3OrganizationUnitFromLegacyId(String legacyId,int ouLevel)
{
    OrganizationUnit ou=null;
    
    try
    {
        DaoUtility util=new DaoUtility();
        if(legacyId !=null && legacyId.indexOf("/") !=-1)
        {
            String lgaCode=null;
            String[] codeArray=legacyId.split("/");
            if(codeArray.length>2)
            {
                lgaCode=codeArray[1];
                if(lgaCode !=null)
                lgaCode=lgaCode.trim();
                ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByOuCodeAndOuLevel(lgaCode, ouLevel);
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return ou;
}
private String getCBOCodeFromLegacyId(String legacyId)
{
    String cboCode=null;
    if(legacyId !=null && legacyId.indexOf("/") !=-1)
    {
        String[] codeArray=legacyId.split("/");
        if(codeArray.length>2)
        {
            cboCode=codeArray[2];
            if(cboCode !=null)
            cboCode=cboCode.trim();
        }
    }
    return cboCode;
}
private String getConcatenatedLevelOuIdsFromOuCodes(String commaSeperatedOuCodes,int ouLevel)
{
    String concatenatedOuIds=null;
    try
    {
    if(commaSeperatedOuCodes !=null && commaSeperatedOuCodes.trim().length()>0)
    {
        String ouUid=null;
        OrganizationUnit ou=null;
        if(commaSeperatedOuCodes.indexOf(",") !=-1)
        {
            String[] ouCodesArray=commaSeperatedOuCodes.split(",");
            String oucode=null;
            if(ouCodesArray !=null && ouCodesArray.length>0)
            {
                for(int i=0; i<ouCodesArray.length; i++)
                {
                    oucode=ouCodesArray[i];
                    if(oucode !=null && oucode.trim().length()>0)
                    {
                        oucode=oucode.trim();
                        ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByOuCodeAndOuLevel(oucode, ouLevel);
                        if(ou !=null)
                        {
                            ouUid=ou.getUid();
                            if(i==0)
                            concatenatedOuIds=ouUid;
                            else
                            concatenatedOuIds+=","+ouUid;
                        }
                    }
                }
            }
        }
        else
        {
            commaSeperatedOuCodes=commaSeperatedOuCodes.trim();
            ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByOuCodeAndOuLevel(commaSeperatedOuCodes, ouLevel);
            if(ou !=null)
            {
                ouUid=ou.getUid();
                concatenatedOuIds=ouUid;
            }
        }
    }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return concatenatedOuIds;
}
private String getConcatenatedServiceCodes(String[] serviceNames,int beneficiaryType,String domainCode)
{
    String concatenatedServiceCodes=null;
    String serviceName=null;
    try
    {
        DaoUtility util=new DaoUtility();
        if(serviceNames !=null && serviceNames.length>0)
        {
            Service service=null;
            for(int i=0; i<serviceNames.length; i++)
            {
                serviceName=serviceNames[i];
                if(serviceName !=null && serviceName.trim().length()>0)
                {
                    serviceName=serviceName.replace(";", "");
                    serviceName=serviceName.trim();
                    service=LegacyOvcServiceManager.getService(serviceName, true);
                                      
                    if(service==null)
                    {
                        //check if the service exist in the database
                        service=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServiceByName(serviceName);
                        if(service==null)
                        {
                            //here, it is not available in the database, so create one
                            service=new Service();
                            service.setServiceName(serviceName);
                            service.setServiceCode(appUtil.generateUniqueId(5));
                            service.setDomainId(domainCode);
                            service.setBeneficiaryType(beneficiaryType);
                            service.setDisplayStatus(AppConstant.FALSE_OPTION_NUM);
                            service.setDateCreated(DateManager.getCurrentDateInstance());
                            service.setLastModifiedDate(DateManager.getCurrentDateInstance());
                            service.setRecordedBy(userName); 
                            util.getBeneficiaryServiceDaoInstance().saveBeneficiaryService(service);
                            service=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServiceByName(serviceName);
                        }
                    }
                    
                    if(service !=null)
                    {
                        if(concatenatedServiceCodes==null)
                        concatenatedServiceCodes=service.getServiceCode();
                        else
                        concatenatedServiceCodes=","+service.getServiceCode();    
                    }
                }
            }
        }   
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    if(concatenatedServiceCodes !=null)
    {
        concatenatedServiceCodes=LegacyOvcServiceManager.cleanServiceName(concatenatedServiceCodes);
        //.replace(";", "");
        //Replace old birth registration code with new one
        concatenatedServiceCodes.replace(LegacyOvcServiceManager.getBirthRegistrationService().getServiceCode(), OvcServiceAttributesManager.getBirthRegistrationAcquisitionService().getServiceCode());
    }
    return concatenatedServiceCodes;
}
private String[] getLegacyOvcServices(String serviceCodeOrName)
{
    String[] splittedServiceNames=null;
    if(serviceCodeOrName !=null)
    {
        serviceCodeOrName.replaceAll(";", ",");
        serviceCodeOrName.replaceAll(",,", ",");
        boolean removeStartAndEndWildCharacters=true;
        splittedServiceNames=appUtil.splitServices(LegacyOvcServiceManager.getConcatenatedServiceNames(appUtil.splitServices(serviceCodeOrName),removeStartAndEndWildCharacters));
    }
    return splittedServiceNames;
}
private String getSchoolGradeIdFromSchoolGradeName(String schoolGradeName)
{
    String schoolGradeId=null;
    try
    {
        if(schoolGradeName !=null && schoolGradeName.trim().length()>0)
        {
            DaoUtility util=new DaoUtility();
            SchoolGrade schoolGrade=util.getSchoolGradeDaoInstance().getSchoolGradeByName(schoolGradeName);
            if(schoolGrade ==null)
            {
                schoolGrade=util.getSchoolGradeDaoInstance().createSchoolGrade(schoolGradeName,userName);
            }
            if(schoolGrade !=null)
            schoolGradeId=schoolGrade.getId();
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return schoolGradeId;
}
private String getSchoolIdFromSchoolName(String schoolName,String orgUnitId)
{
    String schoolId=null;
    try
    {
        if(schoolName !=null && schoolName.trim().length()>0)
        {
            DaoUtility util=new DaoUtility();
            School school=util.getSchoolDaoInstance().getSchoolsBySchoolName(schoolName);
            if(school ==null)
            {
                school=util.getSchoolDaoInstance().createSchool(schoolName, orgUnitId, userName, 0);
            }
            if(school !=null)
            schoolId=school.getId();
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return schoolId;
}
private int getSchoolStatus(String yes_no_unknown_response)
{
    int childSchoolStatus=2;
    if(yes_no_unknown_response !=null)
    {
        if(yes_no_unknown_response.trim().equalsIgnoreCase("Yes"))
        childSchoolStatus=1;
        //else
        //if(yes_no_unknown_response.)
    }
    return childSchoolStatus;
}
private String convertLegacyVulnerabilityStatusToIds(String commaSeparatedVulnerabilityStatus)
{
    String concatenatedVulnerabilityStatusId=null;
    try
    {
        DaoUtility util=new DaoUtility();
        if(commaSeparatedVulnerabilityStatus !=null && commaSeparatedVulnerabilityStatus.trim().length()>0)
        {
            List list=new ArrayList();
            concatenatedVulnerabilityStatusId="";
            VulnerabilityStatus vs=null;
            String vulnerabilityStatusName=null;
            if(commaSeparatedVulnerabilityStatus.indexOf(",") ==-1)
            {
                vulnerabilityStatusName=commaSeparatedVulnerabilityStatus.trim();
                list.add(vulnerabilityStatusName);
            }
            else
            {
                String[] commaSeparatedVulnerabilityStatusArray=commaSeparatedVulnerabilityStatus.split(",");
                if(commaSeparatedVulnerabilityStatusArray !=null && commaSeparatedVulnerabilityStatusArray.length>0)
                {
                    for(int i=0; i<commaSeparatedVulnerabilityStatusArray.length; i++)
                    {
                        vulnerabilityStatusName=commaSeparatedVulnerabilityStatusArray[i];
                        if(vulnerabilityStatusName !=null && vulnerabilityStatusName.trim().length()>0)
                        list.add(vulnerabilityStatusName.trim());
                    }
                }
            }
            for(int i=0; i<list.size(); i++)
            {
                vulnerabilityStatusName=list.get(i).toString();
                vs=util.getVulnerabilityStatusDaoInstance().getVulnerabilityStatusByName(vulnerabilityStatusName);
                if(vs==null)
                {
                    vs=util.getVulnerabilityStatusDaoInstance().createVulnerabilityStatus(vulnerabilityStatusName, userName,3);
                    if(vs !=null)
                    {
                        concatenatedVulnerabilityStatusId+=vs.getVulnerabilityStatusId()+",";
                    }
                }
                else
                {
                   concatenatedVulnerabilityStatusId=vs.getVulnerabilityStatusId(); 
                }
            }
            if(concatenatedVulnerabilityStatusId !=null && concatenatedVulnerabilityStatusId.endsWith(","))
            concatenatedVulnerabilityStatusId=concatenatedVulnerabilityStatusId.substring(0, concatenatedVulnerabilityStatusId.lastIndexOf(","));
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return concatenatedVulnerabilityStatusId;
}
private int getOccupation(String occupationName)
{
    int value=0;
    try
    {
        if(occupationName !=null && occupationName.trim().length()>0)
        {
            //here check if occupation with same name already exist
            DaoUtility util=new DaoUtility();
            Occupation occupation=util.getOccupationDaoInstance().getOccupation(occupationName);
            if(occupation==null)
            {
                //here, occupation does not exist, save it as new occupation and return the value if the save is successful
                Occupation occupationWithMaxValue=util.getOccupationDaoInstance().getOccupationWithHighestValue();
                if(occupationWithMaxValue !=null)
                {
                    int maxValue=occupationWithMaxValue.getValue();
                    occupation.setValue(maxValue+1);
                    occupation.setName(occupationName);
                    util.getOccupationDaoInstance().saveOccupation(occupation);
                    value=maxValue;
                }
                
            }
            else
            {
                value=occupation.getValue();
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return value;
}
private String getCommunityWorkerIdFromName(String communityWorkerName,String userName,String level4OuId)
{
    String communityWorkerId=null;
    try
    {
        if(communityWorkerName !=null && communityWorkerName.trim().length()>0)
        {
            String firstName=null;
            String surname=null;
            DaoUtility util=new DaoUtility();
            communityWorkerName=communityWorkerName.trim();
            if(communityWorkerName.indexOf(" ") !=-1)
            {
                String[] communityWorkerNameArray=communityWorkerName.split(" ");
                if(communityWorkerNameArray.length>1)
                {
                    firstName=communityWorkerNameArray[0];
                    surname=communityWorkerNameArray[1];
                }
                else
                {
                    firstName=communityWorkerName;
                    surname="Unknown";
                }
            }
            else
            {
                firstName=communityWorkerName;
                surname="Unknown";
            }
            CommunityWorker cw=util.getCommunityWorkerDaoInstance().getCommunityWorkerByName(firstName,surname);
            if(cw !=null)
            {
                communityWorkerId=cw.getCommunityWorkerId();
            }
            else
            {
                //create new Community worker and return the Id;
                cw=new CommunityWorker();
                cw.setFirstName(firstName);
                cw.setSurname(surname);
                cw.setSex("M");
                cw.setDesignation("comvolunteer");
                cw.setRecordedBy(userName);
                cw.setLevel4OuId(level4OuId);
                cw.setDateCreated(DateManager.getCurrentDateInstance());
                cw.setLastModifiedDate(DateManager.getCurrentDateInstance());
                util.getCommunityWorkerDaoInstance().saveCommunityWorker(cw);
                //If this save was successful, return the Id for this requesting object's use
                cw=util.getCommunityWorkerDaoInstance().getCommunityWorkerByName(firstName, surname);
                if(cw !=null)
                communityWorkerId=cw.getCommunityWorkerId();
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return communityWorkerId;
}
private String getLevel4IdIdFromWardName(String wardName,String ouCode,String level3OuCode,String legacyId)
{
    String level4OuId=null;
    try
    {
        if(wardName !=null && wardName.trim().length()>0)
        {
            DaoUtility util=new DaoUtility();
            OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByLegacyId(legacyId);
            if(ou !=null)
            {
                level4OuId=ou.getUid();
            }
            else
            {
                //LGA is level 3
                int ouLevel=3;
                wardName=wardName.trim();
                ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByOuCodeAndOuLevel(level3OuCode, ouLevel);
                if(ou !=null)
                {
                    OrganizationUnit level4Ou=util.getOrganizationUnitDaoInstance().createWard(ou, wardName,ouCode,legacyId);
                    if(level4Ou !=null)
                    {
                        level4OuId=level4Ou.getUid();
                    }
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return level4OuId;
}
private String getCboIdFromCboName(String cboCode,String cboName,String level2OuCode,String userName,String legacyId)
{
    String cboUniqueId=null;
    try
    {
        if(cboName !=null && cboName.trim().length()>0)
        {
            DaoUtility util=new DaoUtility();
            CommunityBasedOrganization cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganizationByLegacyId(legacyId);
            if(cbo !=null)
            {
                cboUniqueId=cbo.getUniqueId();
            }
            else
            {
                //State is level 2
                int ouLevel=2;
                cboName=cboName.trim();
                cboName=cboName.replace(" ", "");
                OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnitByOuCodeAndOuLevel(level2OuCode, ouLevel);
                if(ou !=null)
                {
                    cbo=util.getCommunityBasedOrganizationDaoInstance().getCommunityBasedOrganizationByLevel2OuIdAndCboName(ou.getUid(), cboName);
                    if(cbo==null)
                    {
                        cbo=util.getCommunityBasedOrganizationDaoInstance().createCommunityBasedOrganization(level2OuCode,cboCode, cboName,userName,legacyId);
                    }
                    if(cbo !=null)
                    cboUniqueId=cbo.getUniqueId();
                    else if(cboName.length()<12)
                    cboUniqueId=cboName;
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return cboUniqueId;
}
private String getAppropriateStringLength(String str,int length)
{
    if(str !=null)
    {
        str=str.trim();
        if(str.length()>length-1)
        str=str.substring(0, length-2);
    }
    return str;
}
private static String getNodeName(String value,int s,NodeList listOfObjects)
{
    String elementName=null;
    Node firstPersonNode = listOfObjects.item(s);
    Element firstPersonElement = (Element)firstPersonNode;
    if(firstPersonElement !=null)
    {
        NodeList firstNameList = firstPersonElement.getElementsByTagName(value);
        Element firstNameElement = (Element)firstNameList.item(0);

        try
        {
            if(firstNameElement !=null)
            elementName=firstNameElement.getNodeName();
        }
        catch(NullPointerException npe)
        {
            elementName=null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    return elementName;
}
private static String getNodeValue(String value,int s,NodeList listOfObjects)
{
    String elementValue=" ";
    Node firstPersonNode = listOfObjects.item(s);
    Element firstPersonElement = (Element)firstPersonNode;
    if(firstPersonElement !=null)
    {
        NodeList firstNameList = firstPersonElement.getElementsByTagName(value);
        Element firstNameElement = (Element)firstNameList.item(0);

        NodeList textFNList =null;
        if(firstNameElement !=null)
        {
            textFNList =firstNameElement.getChildNodes();
        }
        try
        {
            if((Node)textFNList==null)
            elementValue="";
            else if((Node)textFNList.item(0)==null)
            elementValue="";
            else if(((Node)textFNList.item(0)).getNodeValue()==null || (((Node)textFNList.item(0)).getNodeValue()).equals("") || (((Node)textFNList.item(0)).getNodeValue()).equals(" ") || (((Node)textFNList.item(0)).getNodeValue()).equals("none"))
            elementValue="";
            else
            elementValue=((Node)textFNList.item(0)).getNodeValue();
        }
        catch(NullPointerException npe)
        {
            elementValue="";
        }
    }
    return elementValue;
}
private List getFiles(String filePath)
{
    String[] files=appUtil.listFiles(filePath);
    List fileList=new ArrayList();
    if(files !=null)
    {
        for(int i=0; i<files.length; i++)
        {
            if(files[i].indexOf(".xml") ==-1)
            continue;
            fileList.add(files[i]);
        }
    }
    return fileList;
}
}

