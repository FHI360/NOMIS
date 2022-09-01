/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.HouseholdCareplan;
import com.nomis.ovc.dao.DaoUtility;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class DatabaseUtilities 
{
    private static List connectionParameters; 
    String successTag="<label style='color: green; font-size: 14px; font-weight: bold'>";
    String failureTag="<label style='color: red; font-size: 14px; font-weight: bold'>";
    public boolean addFieldsToNomisUserTable()
    {
        boolean executed=false;
        String tableName="NOMISUSER";
        String dataCaptureLevel2OuId="DATACAPTURELEVEL2OUID";
        String dataCaptureCboId="DATACAPTURECBOID";
        String dataCaptureLevel3OuId="DATACAPTURELEVEL3OUID";
        String dataCaptureLevel4OuId="DATACAPTURELEVEL4OUID";
        String dataCapturePartnerCode="DATACAPTUREPARTNERCODE";
        
        String reportLevel2OuId="REPORTLEVEL2OUID";
        String reportCboId="REPORTCBOID";
        String reportLevel3OuId="REPORTLEVEL3OUID";
        String reportLevel4OuId="REPORTLEVEL4OUID";
        String reportPartnerCode="REPORTPARTNERCODE";
        String disableReportGeneration="REPORTGENERATIONDISABLED";
        
        String columnName=dataCaptureLevel2OuId;
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                //columnName=dataCaptureLevel2OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=dataCaptureCboId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=dataCaptureLevel3OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=dataCaptureLevel4OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(200)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=dataCapturePartnerCode;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                
                columnName=reportLevel2OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(5000)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=reportCboId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(5000)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=reportLevel3OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(5000)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=reportLevel4OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(5000)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=reportPartnerCode;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(1200)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=disableReportGeneration;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query); 
                }//
            }            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
    public boolean addFieldsToSiteSetupTable()
    {
        boolean executed=false;
        String tableName="SITESETUP";
        String level2OuId="LEVEL2OUID";
        String cboId="CBOID";
        String level3OuId="LEVEL3OUID";
        String level4OuId="LEVEL4OUID";
        String columnName=level2OuId;
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                columnName=level2OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=cboId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=level3OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                columnName=level4OuId;
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
                if(columnExists(tableName,"ORGANIZATIONUNIT") && columnExists(tableName,"LEVEL2OUID"))
                {
                    query="UPDATE APP."+tableName+" SET LEVEL2OUID=ORGANIZATIONUNIT WHERE ORGANIZATIONUNIT IS NOT NULL";
                    updateSuccess=util.executeSQLupdate(query); 
                    query="ALTER TABLE APP."+tableName+" DROP COLUMN ORGANIZATIONUNIT";
                    updateSuccess=util.executeSQLupdate(query); 
                }
            }            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
    public boolean addBeneficiaryTypeToQuarterlyStatusTrackerTables()
    {
        boolean executed=false;
        String tableName="QUARTERLYSTATUSTRACKER";
        String columnName="BENEFICIARYTYPE";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query); 
                }
            }            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
    public boolean addDatimIdToPartnerTables()
    {
        boolean executed=false;
        String tableName="PARTNER";
        String columnName="DATIMID";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
            }            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean addDatimIdToOrganizationUnitTables()
    {
        boolean executed=false;
        String tableName="ORGANIZATIONUNIT";
        String columnName="DATIMID";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
            }            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public static void setConnectionParameters(List connectionParameters)
   {
       DatabaseUtilities.connectionParameters=connectionParameters;
   }
   public static List getConnectionParameters()
   {
       return connectionParameters;
   }
   public String createFacilityOvcOfferTable()
    {
        String tableName="FACILITYOVCOFFER";
        String schemaAndTableName="APP."+tableName;
        String message="";
        
        try
        {
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                + "CLIENTUNIQUEID VARCHAR(11) NOT NULL PRIMARY KEY, ORGANIZATIONUNITID VARCHAR(11) NOT NULL,CLIENTFIRSTNAME VARCHAR(25) NOT NULL,CLIENTSURNAME VARCHAR(25) NOT NULL, "
                + "AGE NUMERIC(2) NOT NULL DEFAULT 0,AGEUNIT NUMERIC(1) NOT NULL DEFAULT 0,SEX CHAR(1) NOT NULL,DATEENROLLEDONTREATMENT DATE NOT NULL,"
                + "TREATMENTID VARCHAR(50) NOT NULL, HIVTREATMENTFACILITYID VARCHAR(11)  NOT NULL, CBOID VARCHAR(11) NOT NULL,PARTNERCODE VARCHAR(11) NOT NULL,"
                + "NAMEOFPERSONTOSHARECONTACTWITH VARCHAR(100) NOT NULL,SHARECONTACTQUESTION NUMERIC(1) DEFAULT 0  NOT NULL,CAREGIVERFIRSTNAME VARCHAR(25) NOT NULL,"
                + " CAREGIVERSURNAME VARCHAR(25) NOT NULL, DATECAREGIVERSIGNED DATE NOT NULL,FACILITYSTAFFFIRSTNAME VARCHAR(25) NOT NULL,"
                + "FACILITYSTAFFSURNAME VARCHAR(25) NOT NULL,DATEFACILITYSTAFFSIGNED DATE NOT NULL,MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0,RECORDEDBY VARCHAR(25) NOT NULL,LASTMODIFIEDDATE DATE NOT NULL)";
                                                                                     
                int response=updateDatabase(query);
                if(response==1)
                {
                    message=createIndexQuery(schemaAndTableName,"ORGANIZATIONUNITID","idx_ouid_fovc");
                    message=createIndexQuery(schemaAndTableName,"TREATMENTID","idx_trtmtid_fovc");
                    message=createIndexQuery(schemaAndTableName,"HIVTREATMENTFACILITYID","idx_facilityid_fovc");
                    message=createIndexQuery(schemaAndTableName,"DATECAREGIVERSIGNED","idx_datecgsigned_fovc");
                    message=createIndexQuery(schemaAndTableName,"DATEFACILITYSTAFFSIGNED","idx_datefacstaffsigned_fovc");
                    message=createIndexQuery(schemaAndTableName,"SHARECONTACTQUESTION","idx_sharedcontquest_fovc");
                    message=createIndexQuery(schemaAndTableName,"PARTNERCODE","idx_partnercode_fovc");
                    message=createIndexQuery(schemaAndTableName,"AGE","idx_age_fovc");
                    message=createIndexQuery(schemaAndTableName,"AGEUNIT","idx_ageunit_fovc");
                    message=createIndexQuery(schemaAndTableName,"SEX","idx_sex_fovc");
                    message=createIndexQuery(schemaAndTableName,"MARKEDFORDELETE","idx_markedfordelete_fovc");
                } 
                message=getMessage(tableName,response);
                System.err.println(message);
            }            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    } 
   public boolean createIndexOnBeneficiaryEnrollmentTable()
   {
        boolean executed=false;
        String tableName="BENEFICIARYENROLLMENT";
        String schemaAndTableName="APP."+tableName;
        
        String message="";
        try
        {
            if(tableExists(tableName))
            {   
                message=createIndexQuery(schemaAndTableName,"HHUNIQUEID","idx_hhuid_benr");
                message=createIndexQuery(schemaAndTableName,"SEX","idx_sex_benr"); 
                message=createIndexQuery(schemaAndTableName,"AGEATBASELINE","idx_bage_benr"); 
                message=createIndexQuery(schemaAndTableName,"AGEUNITATBASELINE","idx_bageunit_benr");
                message=createIndexQuery(schemaAndTableName,"CURRENTAGE","idx_curage_benr"); 
                message=createIndexQuery(schemaAndTableName,"CURRENTAGEUNIT","idx_cageunit_benr");
                message=createIndexQuery(schemaAndTableName,"DATEOFENROLLMENT","idx_denr_benr"); 
                message=createIndexQuery(schemaAndTableName,"BENEFICIARYTYPE","idx_btype_benr"); 
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating one or more indexes on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean createIndexOnHouseholdReferralTable()
   {
        boolean executed=false;
        String tableName="HOUSEHOLDREFERRAL";
        String schemaAndTableName="APP."+tableName;
        
        String message="";
        try
        {
            if(tableExists(tableName))
            {   
                message=createIndexQuery(schemaAndTableName,"STABLESERVICES","idx_stable_hhr");
                message=createIndexQuery(schemaAndTableName,"HEALTHSERVICES","idx_health_hhr"); 
                message=createIndexQuery(schemaAndTableName,"SAFETYSERVICES","idx_safety_hhr"); 
                message=createIndexQuery(schemaAndTableName,"SCHOOLEDSERVICES","idx_school_hhr"); 
                message=createIndexQuery(schemaAndTableName,"GBVSERVICES","idx_gbvs_hhr"); 
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating one or more indexes on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean createIndexOnCareAndSupportTable()
   {
        boolean executed=false;
        String tableName="CAREANDSUPPORTCHECKLIST";
        String schemaAndTableName="APP."+tableName;
        
        String message="";
        try
        {
            if(tableExists(tableName))
            {   
                message=createIndexQuery(schemaAndTableName,"MISSEDARVRECENTLY","idx_marv_cas");
                message=createIndexQuery(schemaAndTableName,"MARKEDFORDELETE","idx_mkdfordel_cas"); 
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating one or more indexes on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean createIndexOnBeneficiaryServiceTable()
   {
        boolean executed=false;
        String tableName="BENEFICIARYSERVICE";
        String schemaAndTableName="APP."+tableName;
        
        String message="";
        try
        {
            if(tableExists(tableName))
            {   
                message=createIndexQuery(schemaAndTableName,"BENEFICIARYID","idx_bnid_bns");
                message=createIndexQuery(schemaAndTableName,"STABLESERVICES","idx_stable_bns");
                message=createIndexQuery(schemaAndTableName,"HEALTHSERVICES","idx_health_bns"); 
                message=createIndexQuery(schemaAndTableName,"SAFETYSERVICES","idx_safety_bns"); 
                message=createIndexQuery(schemaAndTableName,"SCHOOLEDSERVICES","idx_school_bns"); 
                message=createIndexQuery(schemaAndTableName,"GBVSERVICES","idx_gbvs_bns"); 
                message=createIndexQuery(schemaAndTableName,"REFERRALSERVICES","idx_referral_bns"); 
                message=createIndexQuery(schemaAndTableName,"MARKEDFORDELETE","idx_mkdfordel_bns"); 
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating one or more indexes on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean createIndexOnHouseholdServiceTable()
   {
        boolean executed=false;
        String tableName="HOUSEHOLDSERVICE";
        String schemaAndTableName="APP."+tableName;
        
        String message="";
        try
        {
            if(tableExists(tableName))
            {   
                message=createIndexQuery(schemaAndTableName,"STABLESERVICES","idx_stable_hhs");
                message=createIndexQuery(schemaAndTableName,"HEALTHSERVICES","idx_health_hhs"); 
                message=createIndexQuery(schemaAndTableName,"SAFETYSERVICES","idx_safety_hhs"); 
                message=createIndexQuery(schemaAndTableName,"SCHOOLEDSERVICES","idx_school_hhs"); 
                message=createIndexQuery(schemaAndTableName,"GBVSERVICES","idx_gbvs_hhs"); 
                message=createIndexQuery(schemaAndTableName,"REFERRALSERVICES","idx_referral_hhs"); 
                message=createIndexQuery(schemaAndTableName,"MARKEDFORDELETE","idx_mkdfordel_hhs"); 
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating one or more indexes on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean createIndexOnChildEnrollmentTable()
   {
        boolean executed=false;
        String tableName="CHILDENROLLMENT";
        String schemaAndTableName="APP."+tableName;
        
        String message="";
        try
        {
            if(tableExists(tableName))
            {   
                message=createIndexQuery(schemaAndTableName,"AGEATBASELINE","idx_basage_ovc");
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating one or more indexes on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
  public boolean createIndexOnAdultHouseholdMemberTable()
   {
        boolean executed=false;
        String tableName="ADULTHOUSEHOLDMEMBER";
        String schemaAndTableName="APP."+tableName;
        
        String message="";
        try
        {
            if(tableExists(tableName))
            {   
                message=createIndexQuery(schemaAndTableName,"AGEATBASELINE","idx_basage_ahm");
                message=createIndexQuery(schemaAndTableName,"CURRENTAGE","idx_curage_ahm"); 
                message=createIndexQuery(schemaAndTableName,"BASELINEHIVSTATUS","idx_bashiv_ahm"); 
                message=createIndexQuery(schemaAndTableName,"DATEOFBASELINEHIVSTATUS","idx_dbashiv_ahm"); 
                message=createIndexQuery(schemaAndTableName,"ENROLLEDONTREATMENT","idx_enrart_ahm"); 
                message=createIndexQuery(schemaAndTableName,"DATEENROLLEDONTREATMENT","idx_denrart_ahm"); 
                message=createIndexQuery(schemaAndTableName,"MARKEDFORDELETE","idx_mkdfordel_ahm"); 
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating one or more indexes on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public String createBeneficiaryEnrollmentTable(boolean dropBeforeCreate)
    {
        String tableName="BENEFICIARYENROLLMENT";
        String schemaAndTableName="APP."+tableName;
        String message="";
        if(dropBeforeCreate)
        dropTable(tableName);
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                + "BENEFICIARYID VARCHAR(50) NOT NULL PRIMARY KEY, HHUNIQUEID VARCHAR(50) NOT NULL, ENROLLMENTID VARCHAR(50) NOT NULL, SEX VARCHAR(1) NOT NULL,"
                + "AGEATBASELINE NUMERIC(3) DEFAULT 0  NOT NULL, AGEUNITATBASELINE NUMERIC(1) DEFAULT 0  NOT NULL, CURRENTAGE NUMERIC(3) DEFAULT 0  NOT NULL,"
                + " CURRENTAGEUNIT NUMERIC(1) DEFAULT 0  NOT NULL, DATEOFENROLLMENT DATE NOT NULL,BENEFICIARYTYPE NUMERIC(1) DEFAULT 0  NOT NULL)";
                                                                                                     
                int response=updateDatabase(query);
                if(response==1)
                {
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (BENEFICIARYID,HHUNIQUEID, ENROLLMENTID,SEX,AGEATBASELINE,AGEUNITATBASELINE,CURRENTAGE,CURRENTAGEUNIT,DATEOFENROLLMENT,BENEFICIARYTYPE) SELECT OVCID,HHUNIQUEID, ENROLLMENTID,SEX,AGEATBASELINE,AGEUNITATBASELINE,CURRENTAGE,CURRENTAGEUNIT,DATEOFENROLLMENT,3 FROM APP.CHILDENROLLMENT");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (BENEFICIARYID,HHUNIQUEID, ENROLLMENTID,SEX,AGEATBASELINE,AGEUNITATBASELINE,CURRENTAGE,CURRENTAGEUNIT,DATEOFENROLLMENT,BENEFICIARYTYPE) SELECT BENEFICIARYID,HHUNIQUEID, ENROLLMENTID,SEX,AGEATBASELINE,2,CURRENTAGE,2,DATEOFENROLLMENT,2 FROM APP.ADULTHOUSEHOLDMEMBER");
                } 
                message=getMessage(tableName,response);
                System.err.println(message);
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    } 
  public String createBeneficiaryServiceTable(boolean dropBeforeCreate)
    {
        String tableName="BENEFICIARYSERVICE";
        String schemaAndTableName="APP."+tableName;
        String message="";
        if(dropBeforeCreate)
        dropTable(tableName);
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                + "ID INTEGER GENERATED ALWAYS AS IDENTITY (start WITH 1, increment BY 1)  NOT NULL PRIMARY KEY, "
                + "BENEFICIARYID VARCHAR(25) NOT NULL, SERVICEDATE DATE NOT NULL, STABLESERVICES VARCHAR(1000), HEALTHSERVICES VARCHAR(1000), "
                + "SAFETYSERVICES VARCHAR(1000), SCHOOLEDSERVICES VARCHAR(1000), GBVSERVICES VARCHAR(1000), REFERRALSERVICES VARCHAR(1000), "
                + "NUMBEROFSERVICES NUMERIC(3) DEFAULT 0  NOT NULL, AGEATSERVICE NUMERIC(3) DEFAULT 0  NOT NULL, "
                + "AGEUNITATSERVICE NUMERIC(1) DEFAULT 0  NOT NULL, RECORDEDBY VARCHAR(25) NOT NULL, DATECREATED DATE NOT NULL, "
                + "LASTMODIFIEDDATE DATE NOT NULL, MARKEDFORDELETE NUMERIC(1) DEFAULT 0  NOT NULL, COMMUNITYWORKER VARCHAR(11))";
                                                                                     
                int response=updateDatabase(query);
                if(response==1)
                {
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (BENEFICIARYID, SERVICEDATE, STABLESERVICES, HEALTHSERVICES, SAFETYSERVICES, SCHOOLEDSERVICES, GBVSERVICES, REFERRALSERVICES, NUMBEROFSERVICES, AGEATSERVICE, AGEUNITATSERVICE, RECORDEDBY, DATECREATED, LASTMODIFIEDDATE, MARKEDFORDELETE, COMMUNITYWORKER)  SELECT OVCID, SERVICEDATE, STABLESERVICES, HEALTHSERVICES, SAFETYSERVICES, SCHOOLEDSERVICES, GBVSERVICES, REFERRALSERVICES, NUMBEROFSERVICES, AGEATSERVICE, AGEUNITATSERVICE, RECORDEDBY, DATECREATED, LASTMODIFIEDDATE, MARKEDFORDELETE, COMMUNITYWORKER FROM APP.CHILDSERVICE");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (BENEFICIARYID, SERVICEDATE, STABLESERVICES, HEALTHSERVICES, SAFETYSERVICES, SCHOOLEDSERVICES, GBVSERVICES, REFERRALSERVICES, NUMBEROFSERVICES, AGEATSERVICE, AGEUNITATSERVICE, RECORDEDBY, DATECREATED, LASTMODIFIEDDATE, MARKEDFORDELETE, COMMUNITYWORKER)  SELECT BENEFICIARYID, SERVICEDATE, STABLESERVICES, HEALTHSERVICES, SAFETYSERVICES, SCHOOLEDSERVICES, GBVSERVICES, REFERRALSERVICES, NUMBEROFSERVICES, AGEATSERVICE, 2, RECORDEDBY, DATECREATED, LASTMODIFIEDDATE, MARKEDFORDELETE, COMMUNITYWORKER FROM APP.HOUSEHOLDSERVICE");
                } 
                message=getMessage(tableName,response);
                System.err.println(message);
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    } 
   public boolean addLastServiceDateToBeneficiaryTables()
    {
        boolean executed=false;
        String tableName="CHILDENROLLMENT";
        String adultTableName="ADULTHOUSEHOLDMEMBER";
        String columnName="DATEOFLASTSERVICE";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                }
            }
            if(tableExists(adultTableName))
            {   
                if(!columnExists(adultTableName,columnName))
                {
                    query="ALTER TABLE APP."+adultTableName+" ADD COLUMN "+columnName+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table or "+adultTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public static void updateCareAndSupportChecklist()
   {
        try
        {
            DaoUtility util=new DaoUtility();
            //util.executeSQLupdate("UPDATE APP.CAREANDSUPPORTCHECKLIST SET VIRALLOADTESTDONE=2 where (VIRALLOADTESTDONE=0 and (ENROLLEDONTREATMENT=0 OR ENROLLEDONTREATMENT=2)");
            util.executeSQLupdate("UPDATE APP.CAREANDSUPPORTCHECKLIST SET DATEOFVIRALLOADTEST = '1900-01-01', VIRALLOADRESULT=-1 where (VIRALLOADTESTDONE=0 or VIRALLOADTESTDONE=2)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
   }
   public boolean addEnrollmentSettingToChildEnrollmentTable()
    {
        boolean executed=false;
        String tableName="CHILDENROLLMENT";
        String columnName="ENROLLMENTSETTING";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 1";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean addChildReferredForHIVTestFieldToHivRiskAssessmentReportTable()
    {
        boolean executed=false;
        String tableName="HIVRISKASSESSMENTREPORT";
        String columnName="CHILDREFERREDFORHIVTEST";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean addChildReferredForHIVTestFieldToHivRiskAssessmentTable()
    {
        boolean executed=false;
        String tableName="HIVRISKASSESSMENT";
        String columnName="CHILDREFERREDFORHIVTEST";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public String createHivRiskAssessmentReportTable(boolean dropBeforeCreate)
    {
        String tableName="HIVRISKASSESSMENTREPORT";
        String schemaAndTableName="APP."+tableName;
        String message="";
        if(dropBeforeCreate)
        dropTable(tableName);
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists(tableName))
            {//update t1 set a = ( select t2.a from t1, t2 where t1.b = t2.b );
                String query="CREATE TABLE "+schemaAndTableName+"("
                + "BENEFICIARYID VARCHAR(25) NOT NULL PRIMARY KEY, CURRENTENROLLMENTSTATUS NUMERIC(2) DEFAULT 0  NOT NULL, DATEOFCURRENTENROLLMENTSTATUS DATE NOT NULL,CURRENTHIVSTATUS NUMERIC(1) DEFAULT 0  NOT NULL,DATEOFCURRENTHIVSTATUS DATE NOT NULL, "
                        + "OUTCOMEOFPREVIOUSRISKASSESSMENT NUMERIC(1) DEFAULT 0  NOT NULL,DATEOFPREVIOUSRISKASSESSMENT DATE NOT NULL,OUTCOMEOFLASTRISKASSESSMENT NUMERIC(1) DEFAULT 0  NOT NULL,DATEOFLASTRISKASSESSMENT DATE NOT NULL,"
                        + "DAYSSINCELASTHIVRISKASSESSMENT NUMERIC(11) DEFAULT 0  NOT NULL,ELIGIBLEFORHIVRISKASSESSMENT NUMERIC(1) DEFAULT 0  NOT NULL,CHILDREFERREDFORHIVTEST NUMERIC(1) DEFAULT 0  NOT NULL,LASTMODIFIEDDATE DATE NOT NULL)";
                                                                                     
                int response=updateDatabase(query);
                if(response==1)
                {
                    util.executeSQLupdate("insert into "+schemaAndTableName+" SELECT OVCID,CURRENTENROLLMENTSTATUS, DATEOFCURRENTSTATUS,CURRENTHIVSTATUS,DATEOFCURRENTHIVSTATUS,0,'1900-01-01',0,'1900-01-01',0,0,0,CURRENT_DATE FROM APP.CHILDENROLLMENT WHERE CURRENTHIVSTATUS !=1");
                    updateHivRiskAssessmentReport();
                } 
                message=getMessage(tableName,response);
                System.err.println(message);
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
public static void updateHivRiskAssessmentReport()
{
    try
    {
        DaoUtility util=new DaoUtility();
        util.executeSQLupdate("UPDATE APP.HIVRISKASSESSMENTREPORT SET DAYSSINCELASTHIVRISKASSESSMENT = {fn timestampdiff(SQL_TSI_DAY, DATE(DATEOFLASTRISKASSESSMENT), DATE(CURRENT_DATE))} where DATEOFLASTRISKASSESSMENT>'1900-01-01'");
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
}
   public void dropTable(String tableName)
   {
       try
       {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {
                util.executeSQLupdate("drop table APP."+tableName);
            }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
   }
   public String createHivPositiveDataTable(boolean dropBeforeCreate)
   {
        String tableName="HIVPOSITIVEDATA";
        String schemaAndTableName="APP."+tableName;
        String message="";
        if(dropBeforeCreate)
        dropTable(tableName);
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists(tableName))
            {//update t1 set a = ( select t2.a from t1, t2 where t1.b = t2.b );
                String query="CREATE TABLE "+schemaAndTableName+"("
                + "BENEFICIARYID VARCHAR(25) NOT NULL PRIMARY KEY, CURRENTENROLLMENTSTATUS NUMERIC(2) DEFAULT 0  NOT NULL, DATEOFCURRENTENROLLMENTSTATUS DATE NOT NULL,DATEOFCURRENTHIVSTATUS DATE NOT NULL,ENROLLEDONTREATMENT NUMERIC(1) DEFAULT 0  NOT NULL,DATEENROLLEDONTREATMENT DATE NOT NULL, DAYSSINCEENROLLEDONTREATMENT NUMERIC(11) DEFAULT 0  NOT NULL,ELIGIBLEFORVIRALLOAD NUMERIC(1) DEFAULT 0  NOT NULL,CURRENTVIRALLOAD NUMERIC(11) DEFAULT 0  NOT NULL,DATEOFCURRENTVIRALLOAD DATE NOT NULL,LASTMODIFIEDDATE DATE NOT NULL)";
                                                                                     
                int response=updateDatabase(query);
                if(response==1)
                {
                    util.executeSQLupdate("insert into "+schemaAndTableName+" SELECT OVCID,CURRENTENROLLMENTSTATUS, DATEOFCURRENTSTATUS,DATEOFCURRENTHIVSTATUS,CHILDENROLLEDONTREATMENT, DATEENROLLEDONTREATMENT,0,0,0,'1900-01-01',CURRENT_DATE FROM APP.CHILDENROLLMENT WHERE CURRENTHIVSTATUS=1");
                    util.executeSQLupdate("insert into "+schemaAndTableName+" SELECT BENEFICIARYID,CURRENTENROLLMENTSTATUS, DATEOFCURRENTENROLLMENTSTATUS,DATEOFCURRENTHIVSTATUS,ENROLLEDONTREATMENT, DATEENROLLEDONTREATMENT,0,0,0,'1900-01-01',CURRENT_DATE FROM APP.ADULTHOUSEHOLDMEMBER WHERE CURRENTHIVSTATUS=1");
                    util.executeSQLupdate("UPDATE "+schemaAndTableName+" SET DAYSSINCEENROLLEDONTREATMENT = {fn timestampdiff(SQL_TSI_DAY, DATE(DATEENROLLEDONTREATMENT), DATE(CURRENT_DATE))} WHERE ENROLLEDONTREATMENT=1");
                    //util.executeSQLupdate("UPDATE "+schemaAndTableName+" SET DATEOFCURRENTVIRALLOAD = (SELECT MAX(cas.DATEOFVIRALLOADTEST) FROM APP.CAREANDSUPPORTCHECKLIST cas,APP.HIVPOSITIVEDATA hd where cas.beneficiaryId=hd.beneficiaryId AND cas.VIRALLOADRESULT>0 and cas.DATEOFVIRALLOADTEST IS NOT NULL)");
                }  //UPDATE APP.HIVPOSITIVEDATA SET CURRENTVIRALLOAD = (SELECT cas.VIRALLOADRESULT FROM APP.CAREANDSUPPORTCHECKLIST cas,APP.HIVPOSITIVEDATA hd where cas.beneficiaryId=hd.beneficiaryId AND cas.DATEOFVIRALLOADTEST=hd.DATEOFCURRENTVIRALLOAD and hd.ENROLLEDONTREATMENT=1)
                message=getMessage(tableName,response);
                System.err.println(message);
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
    public boolean addMarkedForDeleteFieldToBeneficiaryStatusTable()
    {
        boolean executed=false;
        String tableName="BENEFICIARYSTATUSUPDATE";
        String columnName="MARKEDFORDELETE";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean updateDateOfCurrentEnrollmentStatusInBeneficiaryTables()
   {
       
       boolean executed=false;
        String tableName="HOUSEHOLDENROLLMENT";
        String columnName="DATEOFCURRENTSTATUS";
        String columnName1="DATEOFCURRENTENROLLMENTSTATUS";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                query="UPDATE APP.HOUSEHOLDENROLLMENT SET DATEOFCURRENTSTATUS =DATEOFASSESSMENT WHERE DATEOFCURRENTSTATUS='1900-01-01'";
                updateSuccess=util.executeSQLupdate(query);

                query="UPDATE APP.CHILDENROLLMENT SET DATEOFCURRENTSTATUS =DATEOFENROLLMENT WHERE DATEOFCURRENTSTATUS='1900-01-01'";
                updateSuccess=util.executeSQLupdate(query);

                query="UPDATE APP.ADULTHOUSEHOLDMEMBER SET DATEOFCURRENTENROLLMENTSTATUS =DATEOFENROLLMENT WHERE DATEOFCURRENTENROLLMENTSTATUS='1900-01-01'";
                updateSuccess=util.executeSQLupdate(query); 
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error updating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
    public boolean addFieldsToCommunityWorkerTable()
    {
        boolean executed=false;
        String tableName="COMMUNITYWORKER";
        String columnName="ENROLLMENTSTATUS";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(2) NOT NULL DEFAULT 1";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean createIndexOnHivRiskAssessmentTable()
   {
        boolean executed=false;
        String tableName="HIVRISKASSESSMENT";
        String schemaAndTableName="APP."+tableName;
        String columnName="OVCID";
        String columnName1="DATEOFASSESSMENT";
        String columnName2="HIVSTATUSATRISKASSESSMENT";
        String columnName3="CHILDATRISKQUESTION";
        String columnName4="AGEATRISKASSESSMENT";
        String columnName5="AGEUNITATRISKASSESSMENT";
        String columnName6="MARKEDFORDELETE";
        //
        String message="";
        
        try
        {
            if(tableExists(tableName))
            {   
                if(columnExists(tableName,columnName))
                message=createIndexQuery(schemaAndTableName,columnName,"idx_ovcid_hra");
                if(columnExists(tableName,columnName1))
                message=createIndexQuery(schemaAndTableName,columnName1,"idx_dateoass_hra"); 
                if(columnExists(tableName,columnName2))
                message=createIndexQuery(schemaAndTableName,columnName2,"idx_hivstatass_hra"); 
                if(columnExists(tableName,columnName2))
                message=createIndexQuery(schemaAndTableName,columnName3,"idx_childatrsk_hra"); 
                if(columnExists(tableName,columnName2))
                message=createIndexQuery(schemaAndTableName,columnName4,"idx_ageatass_hra"); 
                if(columnExists(tableName,columnName2))
                message=createIndexQuery(schemaAndTableName,columnName5,"idx_ageunitatass_hra"); 
                if(columnExists(tableName,columnName2))
                message=createIndexQuery(schemaAndTableName,columnName6,"idx_mkdfordel_hra"); 
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean addFieldsToHivRiskAssessmentTable()
    {
        boolean executed=false;
        String tableName="HIVRISKASSESSMENT";
        String columnName="DRUGINJECTIONQUESTION";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    columnName="GENITALDISCHARGEQUESTION";
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    columnName="SEXUALACTIVITYQUESTION";
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   public boolean addIndexToCustomReportTable()
   {
       boolean executed=false;
       String schemaAndTableName="App.CUSTOMREPORT";
       String message=message=createIndexQuery(schemaAndTableName,"BENEFICIARYID","idx_benid_hcp");
       message=createIndexQuery(schemaAndTableName,"CAREPLANDATE","idx_hcpdate_hcp");
       return executed;
   }
   public String createIndexesOnOrganizationUnitTable()
   {
       String message=createIndexQuery("APP.ORGANIZATIONUNIT","OUCODE","idx_ou_code");
       message=createIndexQuery("APP.ORGANIZATIONUNIT","OULEVEL","idx_ou_lev");
       message=createIndexQuery("APP.ORGANIZATIONUNIT","OUNAME","idx_ou_name");
       message=createIndexQuery("APP.ORGANIZATIONUNIT","OUPATH","idx_ou_path");
       message=createIndexQuery("APP.ORGANIZATIONUNIT","PARENTID","idx_ou_pid");
       return message;
   }
   public boolean createIndexOnHouseholServiceTable()
    {
        boolean executed=false;
        String tableName="HOUSEHOLDSERVICE";
        String schemaAndTableName="APP."+tableName;
        String columnName="BENEFICIARYID";
        String columnName1="SERVICEDATE";
        String message="";
        
        try
        {
            if(tableExists(tableName))
            {   
                if(columnExists(tableName,columnName))
                message=createIndexQuery(schemaAndTableName,columnName,"idx_benid_hhs");
                if(columnExists(tableName,columnName1))
                message=createIndexQuery(schemaAndTableName,columnName1,"idx_sdate_hhs"); 
                System.err.println(message);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addChildren18PlusToCustomIdicatorsReportTable()
    {
        boolean executed=false;
        String tableName="CUSTOMINDICATORSREPORT";
        String columnName="MALE18PLUSCHILDREN";
        String columnName1="FEMALE18PLUSCHILDREN";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(11) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    columnName=columnName1;                   
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName1+" NUMERIC(11) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    
                }
                util.executeSQLupdate("ALTER TABLE APP.CUSTOMINDICATORSREPORT ALTER COLUMN INDICATORNAME SET DATA TYPE VARCHAR(500)");
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean recreateHouseholdCarePlanTable()
    {
        boolean executed=false;
        String tableName="HOUSEHOLDCAREPLAN";
        String schemaAndTableName="APP.HOUSEHOLDCAREPLAN";
        String columnName="RECORDID";
        String columnName1="UPDATEFLAG";
        String message="";
        
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(columnExists(tableName,columnName))
                {
                    int sumOfRecordIds=util.getHouseholdCareplanDaoInstance().getSumOfRecordIdsFromHouseholdCareplan();
                    System.err.println("sumOfRecordIds is "+sumOfRecordIds);
                    //HouseholdCareplan hcp=util.getHouseholdCareplanDaoInstance().getAllRecordIdsWithZeroValuesFromHouseholdCareplan();
                    //sum of records =0 means there is no recordid value greater than zero. Either the table is empty or the field is not auto increment
                    if(sumOfRecordIds ==0 && !columnExists(schemaAndTableName,columnName1))
                    {
                        String query="";
                        int response=0;
                        if(tableExists(tableName))
                        {
                            query="DROP TABLE "+schemaAndTableName;
                            response=updateDatabase(query);
                        }
                        
                        query="CREATE TABLE "+schemaAndTableName+"("
                        + "RECORDID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY,"
                        + "BENEFICIARYID VARCHAR(25) NOT NULL, CAREPLANDATE DATE NOT NULL, IDENTIFIEDHEALTHISSUES VARCHAR(2000),"
                        + "HOUSEHOLDHEALTHGOALS VARCHAR(2000) DEFAULT '0' , PRIORITYHEALTHGOALS VARCHAR(2000) DEFAULT '0' , "
                        + "HEALTHSERVICESTOBEPROVIDED VARCHAR(2000), TIMEFRAMEFORHEALTHSERVICES VARCHAR(500), FOLLOWUPFORHEALTHSERVICES VARCHAR(2000), "
                        + "IDENTIFIEDSAFETYISSUES VARCHAR(2000), HOUSEHOLDSAFETYGOALS VARCHAR(2000), PRIORITYSAFETYGOALS VARCHAR(2000), "
                        + "SAFETYSERVICESTOBEPROVIDED VARCHAR(2000), TIMEFRAMEFORSAFETYSERVICES LONG VARCHAR, FOLLOWUPFORSAFETYSERVICES VARCHAR(2000), "
                        + "IDENTIFIEDSCHOOLEDISSUES VARCHAR(2000), HOUSEHOLDSCHOOLEDGOALS VARCHAR(2000), PRIORITYSCHOOLEDGOALS VARCHAR(2000), "
                        + "SCHOOLEDSERVICESTOBEPROVIDED VARCHAR(2000), TIMEFRAMEFORSCHOOLEDSERVICES VARCHAR(2000), "
                        + "FOLLOWUPFORSCHOOLEDSERVICES VARCHAR(2000), IDENTIFIEDSTABLEISSUES VARCHAR(2000), HOUSEHOLDSTABLEGOALS VARCHAR(2000), "
                        + "PRIORITYSTABLEGOALS VARCHAR(2000), STABLESERVICESTOBEPROVIDED VARCHAR(2000), TIMEFRAMEFORSTABLESERVICES VARCHAR(2000), "
                        + "FOLLOWUPFORSTABLESERVICES VARCHAR(2000), MARKEDFORDELETE NUMERIC(1) DEFAULT 0  NOT NULL, "
                        + "VOLUNTEERNAME VARCHAR(25) DEFAULT ''  NOT NULL, RECORDEDBY VARCHAR(25) DEFAULT ''  NOT NULL, "
                        + "DATECREATED DATE DEFAULT '1900-01-01'  NOT NULL, LASTMODIFIEDDATE DATE DEFAULT '1900-01-01'  NOT NULL,"
                        + "UPDATEFLAG NUMERIC(2) NOT NULL DEFAULT 0)";
                                                                           
                        response=updateDatabase(query);
                        message=getMessage(schemaAndTableName,response);
                        System.err.println(message);
                        message=createIndexQuery(schemaAndTableName,"BENEFICIARYID","idx_benid_hcp");
                        message=createIndexQuery(schemaAndTableName,"CAREPLANDATE","idx_hcpdate_hcp");
                        /*query="ALTER TABLE APP."+tableName+" DROP COLUMN "+columnName;
                        updateSuccess=util.executeSQLupdate(query); 
                        query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY";
                        updateSuccess=util.executeSQLupdate(query); 
                        query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName1+" NUMERIC (2) NOT NULL DEFAULT 1";
                        updateSuccess=util.executeSQLupdate(query); 
                        System.err.println("record Id field changed to auto increment");*/
                    }
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+schemaAndTableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addDateOfViralTestResultToCareAndSupportTable()
    {
        boolean executed=false;
        String tableName="CAREANDSUPPORTCHECKLIST";
        String columnName="DATEOFVIRALLOADRESULT";
        
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query); 
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean changeSizeOfScoreFieldInCarePlanAchievementTable()
   {
       
       boolean executed=false;
        String tableName="CAREPLANACHIEVEMENTCHECKLIST";
        String columnName="SCORECONTROL";
        String columnName1="SCORE";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(3) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="UPDATE APP."+tableName+" SET "+columnName+"="+columnName1;
                    updateSuccess=util.executeSQLupdate(query);
                    
                    query="ALTER TABLE APP."+tableName+" DROP COLUMN "+columnName1;
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName1+" NUMERIC(3) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="UPDATE APP."+tableName+" SET "+columnName1+"="+columnName;
                    updateSuccess=util.executeSQLupdate(query);
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
   //ALTER TABLE [table] ALTER COLUMN [column] SET DATA TYPE [type];
   public boolean updateCurrentHivStatusForChildAndAdultHouseholdMemberTables()
    {
        boolean executed=false;
        String tableName="CHILDENROLLMENT";
        String tableName1="ADULTHOUSEHOLDMEMBER";
        String columnName="MONTHSOFTRANSPORTATIONSUPPORTCONTROL";
        String columnName1="MONTHSOFTRANSPORTATIONSUPPORT";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {                   
                query="UPDATE APP."+tableName+" SET CURRENTHIVSTATUS=BASELINEHIVSTATUS,DATEOFCURRENTHIVSTATUS=DATEOFBASELINEHIVSTATUS WHERE CURRENTHIVSTATUS=0 AND BASELINEHIVSTATUS>0";
                updateSuccess=util.executeSQLupdate(query);
                query="UPDATE APP."+tableName+" SET CURRENTHIVSTATUS=BASELINEHIVSTATUS,DATEOFCURRENTHIVSTATUS=DATEOFBASELINEHIVSTATUS WHERE (CURRENTHIVSTATUS=3 OR CURRENTHIVSTATUS=4) AND (BASELINEHIVSTATUS=1 OR BASELINEHIVSTATUS=2)";
                updateSuccess=util.executeSQLupdate(query);
            }
            if(tableExists(tableName1))
            {                   
                query="UPDATE APP."+tableName1+" SET CURRENTHIVSTATUS=BASELINEHIVSTATUS,DATEOFCURRENTHIVSTATUS=DATEOFBASELINEHIVSTATUS WHERE CURRENTHIVSTATUS=0 AND BASELINEHIVSTATUS>0";
                updateSuccess=util.executeSQLupdate(query);
                query="UPDATE APP."+tableName1+" SET CURRENTHIVSTATUS=BASELINEHIVSTATUS,DATEOFCURRENTHIVSTATUS=DATEOFBASELINEHIVSTATUS WHERE (CURRENTHIVSTATUS=3 OR CURRENTHIVSTATUS=4) AND (BASELINEHIVSTATUS=1 OR BASELINEHIVSTATUS=2)";
                updateSuccess=util.executeSQLupdate(query);
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error updating "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean alterSizeOfMonthsOfTransportationSupportInCareAndSupportTable()
    {
        boolean executed=false;
        String tableName="CAREANDSUPPORTCHECKLIST";
        String columnName="MONTHSOFTRANSPORTATIONSUPPORTCONTROL";
        String columnName1="MONTHSOFTRANSPORTATIONSUPPORT";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(2) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="UPDATE APP."+tableName+" SET "+columnName+"="+columnName1;
                    updateSuccess=util.executeSQLupdate(query);
                    
                    query="ALTER TABLE APP."+tableName+" DROP COLUMN "+columnName1;
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName1+" NUMERIC(2) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="UPDATE APP."+tableName+" SET "+columnName1+"="+columnName;
                    updateSuccess=util.executeSQLupdate(query);
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addMainVulnerabilityStatusCodeTable()
    {
        boolean executed=false;
        String tableName="CHILDENROLLMENT";
        String columnName="MAINVULNERABILITYSTATUSCODE";
        
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(25)";
                    updateSuccess=util.executeSQLupdate(query); 
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addLastDrugPickupAndNumberOfDaysOfRefillToCareAndSupportTable()
    {
        boolean executed=false;
        String tableName="CAREANDSUPPORTCHECKLIST";
        String columnName="DATEOFLASTDRUGPICKUP";
        String columnName1="NUMBEROFDAYSOFREFILL";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query);   
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName1+" NUMERIC(3) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addVsCategpryToVulnerabilityStatusTable()
    {
        boolean executed=false;
        String tableName="VULNERABILITYSTATUS";
        String columnName="VSCATEGORY";
        
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="UPDATE APP."+tableName+" SET VSCATEGORY=1 WHERE VSENABLED =1";
                    updateSuccess=util.executeSQLupdate(query);
                    query="UPDATE APP."+tableName+" SET VSCATEGORY=2 WHERE VSENABLED =3";
                    updateSuccess=util.executeSQLupdate(query);
                    query="UPDATE APP."+tableName+" SET VSENABLED=1 WHERE VSENABLED =3";
                    updateSuccess=util.executeSQLupdate(query);
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addReasonsChildMissedSchoolToEduPerfAssessmentTable()
    {
        boolean executed=false;
        String tableName="CHILDEDUCATIONPERFORMANCEASSESSMENT";
        String columnName="REASONSCHILDMISSEDSCHOOLORVOCTRAINING";
        
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(500)";
                    updateSuccess=util.executeSQLupdate(query);   
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addFieldsFromNomis2ToCareplanAchievementChecklistTable()
    {
        boolean executed=false;
        String tableName="CAREPLANACHIEVEMENTCHECKLIST";
        String columnName="ADOLINVOCTRAINING";
        String columnName2="CHILDRENENROLLEDINEARLYCHILDCARE";
        String columnName3="CAREGIVERDISCLOSEDHIVSTATUS";
        String columnName4="CHILDRENINNEEDOFHLTPROVIDEDHLTSERVICES";
        String columnName5="HHDEMONSTRATEDABILITYTOMEETGOALS";
        String columnName6="VCATRISKREFERREDFORCHILDPROTECTION";
        String columnName7="CHILDRENINHHHAVEADEQUATEHOUSINGANDSPACE";
        String columnName8="CAREGIVERSCOMPLETEDPARENTINGCOURSE";
        String columnName9="CHILDHEADEDHHLINKEDTOSERVICES";
        String columnName10="HHGRADUATED";
        String columnName11="CHILDRENREFERREDFORHIVTESTING";
        String columnName12="CHILDRENREFERREDRECEIVEDTESTINGSERVICES";
        String columnName13="CHILDWITHDRAWNORSAD";
        String columnName14="SCORE";
        String query="";
        int updateSuccess=0;
        
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName2+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName3+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName4+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName5+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName6+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName7+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName8+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName9+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName10+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName11+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName12+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName13+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    //query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName14+" NUMERIC(1) NOT NULL DEFAULT 0";
                    //updateSuccess=util.executeSQLupdate(query);
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addCustomIndicatorsFieldsToChildServiceTable()
    {
        boolean executed=false;
        String tableName="CHILDSERVICE";
        String columnName="CHILDABUSED";
        String columnName2="ABUSEDCHILDLINKEDTOGOVT";
        String columnName3="CHILDMISSEDSCHOOL";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName2+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName3+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);   
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   /*CREATE TABLE USERROLES (ROLEID VARCHAR(11) NOT NULL, ROLENAME VARCHAR(50) NOT NULL, ASSIGNEDRIGHTS VARCHAR(1000) NOT NULL, PRIMARY KEY (ROLEID));
    
    */
   public String createUserRoleTable()
    {
        String tableName="USERROLE";
        String schemaAndTableName="APP."+tableName;
        String message="";
        
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                + "ROLEID VARCHAR(11) NOT NULL, ROLENAME VARCHAR(50) NOT NULL, "
                + "USERPRIVILEGES VARCHAR(1000) NOT NULL)";
                                                                                     
                int response=updateDatabase(query);
                if(response==1)
                {
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (ROLEID,ROLENAME,USERPRIVILEGES) VALUES ('101','Super user','100')");
                    /*util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_INFORMALLYEMPLOYED_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_INFORMALLYEMPLOYED_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_RETIREDNONPENSIONER_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_RETIREDNONPENSIONER_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_RETIREDPENSIONER_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_RETIREDPENSIONER_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_SELFEMPLOYED_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_SELFEMPLOYED_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_UNEMPLOYED_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_UNEMPLOYED_NUM).getName()+"')");
                    */
                }   
                message=getMessage(tableName,response);
                System.err.println(message);
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
   public String createOccupationTable()
    {
        String tableName="OCCUPATION";
        String schemaAndTableName="APP."+tableName;
        String message="";
        
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists(tableName))
            {
                OccupationManager om=new OccupationManager();
                String query="CREATE TABLE "+schemaAndTableName+"("
                + "RECORDID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1) NOT NULL PRIMARY KEY,"
                + "OCCUPATIONNAME VARCHAR(200) NOT NULL,"
                + "OCCUPATIONVALUE NUMERIC(2) NOT NULL DEFAULT 0)";
                                                     
                int response=updateDatabase(query);
                if(response==1)
                {
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_FORMALLYEMPLOYED_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_FORMALLYEMPLOYED_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_INFORMALLYEMPLOYED_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_INFORMALLYEMPLOYED_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_RETIREDNONPENSIONER_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_RETIREDNONPENSIONER_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_RETIREDPENSIONER_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_RETIREDPENSIONER_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_SELFEMPLOYED_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_SELFEMPLOYED_NUM).getName()+"')");
                    util.executeSQLupdate("INSERT INTO "+schemaAndTableName+" (OCCUPATIONNAME,OCCUPATIONVALUE) VALUES ("+om.getOccupation(AppConstant.OCCUPATION_UNEMPLOYED_NUM).getValue()+",'"+om.getOccupation(AppConstant.OCCUPATION_UNEMPLOYED_NUM).getName()+"')");
                }   
                message=getMessage(tableName,response);
                System.err.println(message);
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
   public boolean addDatimIdToReferralDirectoryTable()
    {
        boolean executed=false;
        String tableName="REFERRALDIRECTORY";
        String columnName="DATIMID";
        
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query);  
                    if(updateSuccess==1)
                    executed=true;
                }
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addLegacyIdToChildEnrollmentTable()
    {
        boolean executed=false;
        String tableName="CHILDENROLLMENT";
        String columnName="LEGACYID";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(50)";
                    updateSuccess=util.executeSQLupdate(query);  
                    createIndexQuery("APP."+tableName,columnName,"idx_ovc_lid");                    
                }
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addLegacyIdToAdultHouseholdMemberTable()
    {
        boolean executed=false;
        String tableName="ADULTHOUSEHOLDMEMBER";
        String columnName="LEGACYID";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(50)";
                    updateSuccess=util.executeSQLupdate(query);  
                    createIndexQuery("APP."+tableName,columnName,"idx_ahm_lid");                    
                }   
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addLegacyIdToHouseholdEnrollmentTable()
    {       
        boolean executed=false;
        String tableName="HOUSEHOLDENROLLMENT";
        String columnName="LEGACYID";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(50)";
                    updateSuccess=util.executeSQLupdate(query);  
                    createIndexQuery("APP."+tableName,columnName,"idx_hhe_lid");                    
                }
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addLegacyIdToCommunityBasedOrganizationTable()
    {
        boolean executed=false;
        String tableName="COMMUNITYBASEDORGANIZATION";
        String columnName="LEGACYID";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(50)";
                    updateSuccess=util.executeSQLupdate(query);  
                    createIndexQuery("APP."+tableName,columnName,"idx_cbo_lid");                    
                }  
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addLegacyIdToOrganizationUnitTable()
    {
        boolean executed=false;
        String tableName="ORGANIZATIONUNIT";
        String columnName="LEGACYID";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(50)";
                    updateSuccess=util.executeSQLupdate(query);  
                    createIndexQuery("APP."+tableName,columnName,"idx_ou_lid");                    
                }  
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addNumberOfChildrenAndPeopleFieldsToHouseholdEnrollmentTable()
    {
        boolean executed=false;
        String tableName="HOUSEHOLDENROLLMENT";
        String columnName="NUMBEROFCHILDRENINHOUSEHOLD";
        String columnName2="NUMBEROFPEOPLEINHOUSEHOLD";
        String columnName3="LEGACYID";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(3) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName2+" NUMERIC(3) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName3+" VARCHAR(50)";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                }
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addLegacyAssessmentFieldsToRevisedHouseholdAssessmentTable()
    {
        boolean executed=false;
        String tableName="REVISEDHOUSEHOLDASSESSMENT";
        String columnName="HHHEADSHIP";
        String columnName2="HEALTH";
        String columnName3="EDUCATIONLEVEL";
        String columnName4="SHELTERANDHOUSING";
        String columnName5="FOODSECURITYANDNUTRITION";
        String columnName6="MEANSOFLIVELIHOOD";
        String columnName7="HHINCOME";
        String columnName8="VULNERABILITYSCORE";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName2+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName3+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName4+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName5+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName6+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName7+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName8+" NUMERIC(2) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    
                    //createIndexQuery("APP."+tableName,columnName,"idx_na_age");
                    //createIndexQuery("APP."+tableName,columnName2,"idx_na_ageu");
                }
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addEnrolmentStatusFieldsToBeneficiaryStatusUpdateTable()
    {
        boolean executed=false;
        String tableName="BENEFICIARYSTATUSUPDATE";
        String columnName="CHILDEXITEDFROMPROGRAM";
        String columnName2="CHILDEXITSTATUS";
        String columnName3="DATECHILDEXITEDFROMPROGRAM";
        String columnName4="CAREGIVEREXITEDFROMPROGRAM";
        String columnName5="CAREGIVEREXITSTATUS";
        String columnName6="DATECAREGIVEREXITEDFROMPROGRAM";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName2+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName3+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName4+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName5+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName6+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query);  
                    
                    //createIndexQuery("APP."+tableName,columnName,"idx_na_age");
                    //createIndexQuery("APP."+tableName,columnName2,"idx_na_ageu");
                }
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean revertHivUnknownDueToRiskAssessmentToBaselineHivStatusInChildEnrollment()
    {
        boolean executed=false;
        try
        {
            String tableName="CHILDENROLLMENT";
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                String updateScript="UPDATE APP.CHILDENROLLMENT SET CURRENTHIVSTATUS=BASELINEHIVSTATUS,DATEOFCURRENTHIVSTATUS=DATEOFBASELINEHIVSTATUS WHERE CURRENTHIVSTATUS="+AppConstant.HIV_TEST_REQUIRED_NUM;
                int updateSuccess=util.executeSQLupdate(updateScript); 
                
                if(updateSuccess==1)
                executed=true;
                    
            }
            
        }       
        catch(Exception ex)
        {
            System.err.println("Error: Unable to update CHILDENROLLMENT table with HIV information. "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addAgeAtAssessmentToNutritionalAssessmentTable()
    {
        boolean executed=false;
        String tableName="NUTRITIONASSESSMENT";
        String columnName="AGEATASSESSMENT";
        String columnName2="AGEUNITATASSESSMENT";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(2) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName2+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    createIndexQuery("APP."+tableName,columnName,"idx_na_age");
                    createIndexQuery("APP."+tableName,columnName2,"idx_na_ageu");
                }
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addReferralCompletedToHouseholdReferral()
    {
        boolean executed=false;
        String tableName="HOUSEHOLDREFERRAL";
        String columnName="REFERRALCOMPLETED";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    createIndexQuery("APP."+tableName,columnName,"idx_refs_rcom");
                }
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean addDateOfStatusToChildEnrollment()
    {
        boolean executed=false;
        String tableName="BENEFICIARYSTATUSUPDATE";
        String columnName="DATEOFSTATUS";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query);  
                }
                columnName="DateOfCaregiverHivStatus";
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query);  
                }
                columnName="caregiverEnrolledOnTreatment";
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                }
                columnName="DateCaregiverEnrolledOnTreatment";
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" DATE NOT NULL DEFAULT '1900-01-01'";
                    updateSuccess=util.executeSQLupdate(query);  
                }
                columnName="facilityCaregiverEnrolled";
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(11)";
                    updateSuccess=util.executeSQLupdate(query);  
                }
                columnName="childTreatmentId";
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(25)";
                    updateSuccess=util.executeSQLupdate(query);  
                }
                columnName="caregiverTreatmentId";
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(25)";
                    updateSuccess=util.executeSQLupdate(query);  
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public String createAccessPrivilegeTable()
    {
        String tableName="ACCESSPRIVILEGE";
        String schemaAndTableName="APP."+tableName;
        String message="";
        
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                + "PRIVILEGEID VARCHAR(11) NOT NULL PRIMARY KEY,"
                + "PRIVILEGENAME VARCHAR(150) NOT NULL, PRIVILEGETYPE VARCHAR(25))";
                                                     
                int response=updateDatabase(query);
                if(response==1)
                {
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('101', 'Data entry', 'dataEntry')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('102', 'Add user role', 'userrole')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('103', 'Add User', 'user')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('104', 'Generate reports', 'generateReport')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('105', 'Export data', 'dataExport')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('106', 'Import Data', 'dataImport')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('107', 'Add/Update School', 'schoolSetup')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('108', 'Setup sites', 'siteSetup')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('109', 'Add Vulnerability Status', 'vulnerabilityStatus')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('110', 'Add/Update Organization unit', 'ousetup')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('111', 'Add Beneficiary services', 'beneficiaryservices')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('112', 'Add Community Based Organizations', 'cbo')");
                    util.executeSQLupdate("INSERT INTO APP.ACCESSPRIVILEGE (PRIVILEGEID, PRIVILEGENAME, PRIVILEGETYPE) VALUES ('113', 'Add Partners', 'partners')");
                }
                message=getMessage(tableName,response);
                System.err.println(message);
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
   public String createIndexesOnCaregiverExpenditureTable()
   {
       String message="";
       message=createIndexQuery("APP.CHILDEDUCATIONPERFORMANCEASSESSMENT","DATEOFASSESSMENT","idx_cepa_dta");
       message=createIndexQuery("APP.CHILDEDUCATIONPERFORMANCEASSESSMENT","OVCID","idx_cepa_cid");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","MARKEDFORDELETE","idx_hhe_mfd");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","CBOID","idx_hhe_cbo");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","PARTNERCODE","idx_hhe_ptc");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","ENROLLMENTID","idx_hhe_enid");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","CURRENTENROLLMENTSTATUS","idx_hhe_cens");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","DATEOFCURRENTSTATUS","idx_hhe_cend");
        return message;
   }
   public String createIndexesOnNutritionAssessmentTable()
   {
       String message="";
       message=createIndexQuery("APP.NUTRITIONASSESSMENT","DATEOFASSESSMENT","idx_na_dta");
       message=createIndexQuery("APP.NUTRITIONASSESSMENT","OVCID","idx_na_vcid");
       message=createIndexQuery("APP.NUTRITIONASSESSMENT","MARKEDFORDELETE","idx_na_mfd");
       return message;
   }
   public String createIndexesOnSchoolTable()
   {
       String message="";
       message=createIndexQuery("APP.SCHOOL","SCHOOLNAME","idx_sch_dta");
       message=createIndexQuery("APP.SCHOOL","MARKEDFORDELETE","idx_sch_mfd");
       return message;
   }
   public String createIndexesOnHouseholdReferralTable()
   {
       String message="";
       message=createIndexQuery("APP.HOUSEHOLDREFERRAL","DATEOFREFERRAL","idx_hhr_dtr");
       message=createIndexQuery("APP.HOUSEHOLDREFERRAL","BENEFICIARYID","idx_hhr_bid");
       message=createIndexQuery("APP.HOUSEHOLDREFERRAL","MARKEDFORDELETE","idx_hhr_mfd");
       message=createIndexQuery("APP.HOUSEHOLDREFERRAL","BENEFICIARYTYPE","idx_hhr_bnt");
       return message;
   }
   public String createIndexesOnCareAndSupportTable()
   {
       String message="";
       message=createIndexQuery("APP.CAREANDSUPPORTCHECKLIST","DATEOFASSESSMENT","idx_cas_dta");
       message=createIndexQuery("APP.CAREANDSUPPORTCHECKLIST","BENEFICIARYID","idx_cas_bid");
       message=createIndexQuery("APP.CAREANDSUPPORTCHECKLIST","MARKEDFORDELETE","idx_cas_mfd");
       return message;
   }
   public String createIndexesOnCommunityBasedOrganizationTable()
   {
       String message="";
       message=createIndexQuery("APP.COMMUNITYBASEDORGANIZATION","CBOCODE","idx_cbo_code");
       message=createIndexQuery("APP.COMMUNITYBASEDORGANIZATION","CBONAME","idx_cbo_name");
       return message;
   }
   public String createIndexesOnBeneficiaryStatusUpdateTable()
   {
       String message="";
       message=createIndexQuery("APP.BENEFICIARYSTATUSUPDATE","NEWHIVSTATUS","idx_bsu_nhs");
       message=createIndexQuery("APP.BENEFICIARYSTATUSUPDATE","DATEOFNEWSTATUS","idx_bsu_dnhs");
       return message;
   }
   public String createIndexesOnCaregiverAccessToEmergencyFundTable()
   {
       String message="";
       message=createIndexQuery("APP.CAREGIVERACCESSTOEMERGENCYFUND","DATEOFASSESSMENT","idx_caef_dta");
       message=createIndexQuery("APP.CAREGIVERACCESSTOEMERGENCYFUND","BENEFICIARYID","idx_caef_bid");
       message=createIndexQuery("APP.CAREGIVERACCESSTOEMERGENCYFUND","MARKEDFORDELETE","idx_caef_mfd");
       return message;
   }
   public String createIndexesOnChildEducationalPerformanceTable()
   {
       String message="";
       message=createIndexQuery("APP.CHILDEDUCATIONPERFORMANCEASSESSMENT","DATEOFASSESSMENT","idx_cepa_dta");
       message=createIndexQuery("APP.CHILDEDUCATIONPERFORMANCEASSESSMENT","OVCID","idx_cepa_cid");
       message=createIndexQuery("APP.CHILDEDUCATIONPERFORMANCEASSESSMENT","MARKEDFORDELETE","idx_cepa_mfd");
       return message;
   }
   public String createIndexesOnCareplanAchievementChecklistTable()
   {
       String message="";
       message=createIndexQuery("APP.CAREPLANACHIEVEMENTCHECKLIST","DATEOFASSESSMENT","idx_cpa_dta");
       message=createIndexQuery("APP.CAREPLANACHIEVEMENTCHECKLIST","HHUNIQUEID","idx_cpa_bid");
       message=createIndexQuery("APP.CAREPLANACHIEVEMENTCHECKLIST","MARKEDFORDELETE","idx_cpa_mfd");
       return message;
   }
   public String createIndexesOnHhVulnerabilityAssessmentTable()
   {
       String message="";
       message=createIndexQuery("APP.HHVULNERABILITYASSESSMENT","DATEOFASSESSMENT","idx_hhva_dta");
       message=createIndexQuery("APP.HHVULNERABILITYASSESSMENT","HHUNIQUEID","idx_hhva_hid");
       return message;
   }
   public String createIndexesOnHouseholdEnrollmentTable()
   {
       String message="";
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","DATEOFASSESSMENT","idx_hhe_dta");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","ORGANIZATIONUNIT","idx_hhe_ou");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","MARKEDFORDELETE","idx_hhe_mfd");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","CBOID","idx_hhe_cbo");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","PARTNERCODE","idx_hhe_ptc");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","ENROLLMENTID","idx_hhe_enid");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","CURRENTENROLLMENTSTATUS","idx_hhe_cens");
       message=createIndexQuery("APP.HOUSEHOLDENROLLMENT","DATEOFCURRENTSTATUS","idx_hhe_cend");
        return message;
   }
   public String createIndexesOnChildEnrollmentTable()
   {
       String message="";
       message=createIndexQuery("APP.CHILDENROLLMENT","DATEOFENROLLMENT","idx_che_dte");
       message=createIndexQuery("APP.CHILDENROLLMENT","SEX","idx_che_sex");
       message=createIndexQuery("APP.CHILDENROLLMENT","MARKEDFORDELETE","idx_che_mfd");
       message=createIndexQuery("APP.CHILDENROLLMENT","BASELINEHIVSTATUS","idx_che_bhiv");
       message=createIndexQuery("APP.CHILDENROLLMENT","DATEOFBASELINEHIVSTATUS","idx_che_bhvd");
       message=createIndexQuery("APP.CHILDENROLLMENT","VULNERABILITYSTATUS","idx_che_vul");
       message=createIndexQuery("APP.CHILDENROLLMENT","BASELINESCHOOLSTATUS","idx_che_bsch");
       message=createIndexQuery("APP.CHILDENROLLMENT","CURRENTSCHOOLSTATUS","idx_che_csch");
       message=createIndexQuery("APP.CHILDENROLLMENT","DATEOFCURRENTSCHOOLSTATUS","idx_che_cscd");
        return message;
   }
   public String createIndexesOnChildServiceTable()
   {
       String message="";
       message=createIndexQuery("APP.CHILDSERVICE","OVCID","idx_chs_vcid");
       message=createIndexQuery("APP.CHILDSERVICE","SERVICEDATE","idx_chs_sdt");
       message=createIndexQuery("APP.CHILDSERVICE","MARKEDFORDELETE","idx_chs_mfd");
       message=createIndexQuery("APP.CHILDSERVICE","STABLESERVICES","idx_chs_stb");
       message=createIndexQuery("APP.CHILDSERVICE","HEALTHSERVICES","idx_chs_hlt");
       message=createIndexQuery("APP.CHILDSERVICE","SAFETYSERVICES","idx_chs_sft");
       message=createIndexQuery("APP.CHILDSERVICE","SCHOOLEDSERVICES","idx_chs_sch");
       message=createIndexQuery("APP.CHILDSERVICE","AGEATSERVICE","idx_chs_age");
                
        return message;
   }
   public String createNutritionalStatusTable()
    {
        String tableName="NUTRITIONSTATUS";
        String schemaAndTableName="APP."+tableName;
        String message="";
        
        try
        {
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                        + "OVCID VARCHAR(25)  NOT NULL PRIMARY KEY, "
                        + "CURRENTNUTRITIONSTATUS NUMERIC(1) NOT NULL DEFAULT 0, "
                        + "DATEOFCURRENTNUTRITIONSTATUS DATE NOT NULL,"
                        + "LASTMODIFIEDDATE DATE NOT NULL,"
                        + "MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0)";
                                                                             
                int response=updateDatabase(query);
                message=getMessage(tableName,response);
                System.err.println(message);
                message=createIndexQuery(schemaAndTableName,"CURRENTNUTRITIONSTATUS","idx_nut_cns");
                message=createIndexQuery(schemaAndTableName,"DATEOFCURRENTNUTRITIONSTATUS","idx_nut_dtns");
                
                System.err.println(message);
                //createIndexOnHivStatusTables();
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
   public boolean addFieldsToNutritionStatusTable()
    {
        boolean executed=false;
        String tableName="NUTRITIONSTATUS";
        String columnName="AGEATASSESSMENT";
        String query="";
        int updateSuccess=0;
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(2) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);  
                    columnName="AGEUNITATASSESSMENT";
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" NUMERIC(1) NOT NULL DEFAULT 0";
                    updateSuccess=util.executeSQLupdate(query);
                    columnName="HEIGHT";
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" DOUBLE NOT NULL DEFAULT 0.0";
                    updateSuccess=util.executeSQLupdate(query);
                    columnName="WEIGHT";
                    query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" DOUBLE NOT NULL DEFAULT 0.0";
                    updateSuccess=util.executeSQLupdate(query);
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
   }
    public String createDatimReportTable()
    {
        String tableName="DATIMREPORT";
        String schemaAndTableName="APP."+tableName;
        String message="";
        
        try
        {
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                        + "RECORDID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1)  NOT NULL PRIMARY KEY, "
                        + "LEVEL2OU VARCHAR(100) NOT NULL, LEVEL3OU VARCHAR(100) NOT NULL, CBO VARCHAR(100) NOT NULL, "
                        + "DPERIOD VARCHAR(50) NOT NULL, OVC_SERVLESSTHAN1 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OVC_SERV1TO4 NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERV5TO9 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OVC_SERV1TO9 NUMERIC(11) DEFAULT 0  NOT NULL, M10TO14 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "M15TO17 NUMERIC(11) DEFAULT 0  NOT NULL, M18TO24 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "M25PLUS NUMERIC(11) DEFAULT 0  NOT NULL, F1TO4 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "F5TO9 NUMERIC(11) DEFAULT 0  NOT NULL, F10TO14 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "F15TO17 NUMERIC(11) DEFAULT 0  NOT NULL, F18TO24 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "F25PLUS NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERV NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "MALETOTAL NUMERIC(11) DEFAULT 0  NOT NULL, FEMALETOTAL NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "GRANDTOTAL NUMERIC(11) DEFAULT 0  NOT NULL, HIV_STATNUMERATOR NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "TOTALPOSITIVE NUMERIC(11) DEFAULT 0  NOT NULL, ENROLLEDONART NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "NOTENROLLEDONART NUMERIC(11) DEFAULT 0  NOT NULL, TOTALNEGATIVE NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "TOTALUNKNOWN NUMERIC(11) DEFAULT 0  NOT NULL, TESTNOTINDICATED NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OTHERREASONS NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERVNUMERATOR NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OVC_SERVACTIVE NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERVGRADUATED NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OVC_SERVTRANSFERED NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERVEXITEDWITHOUTGRADUATION NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "DATECREATED DATE DEFAULT '1900-01-01'  NOT NULL, LASTMODIFIEDDATE DATE DEFAULT '1900-01-01'  NOT NULL, "
                        + "USERNAME VARCHAR(100) NOT NULL, PARTNERCODE VARCHAR(25), PARTNERNAME VARCHAR(200))";
                                                     
                int response=updateDatabase(query);
                message=getMessage(tableName,response);
                System.err.println(message);
                message=createIndexQuery(schemaAndTableName,"LEVEL2OU","idx_dtm_l2ou");
                message=createIndexQuery(schemaAndTableName,"LEVEL3OU","idx_dtm_l3ou");
                message+=createIndexQuery(schemaAndTableName,"CBO","idx_dtm_cbo");
                message+=createIndexQuery(schemaAndTableName,"DPERIOD","idx_dtm_prd");
                
                System.err.println(message);
                //createIndexOnHivStatusTables();
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
    public String createChildStatusIndexTable()
    {
        String tableName="CHILDSTATUSINDEX";
        String schemaAndTableName="APP."+tableName;
        String message="";
        
        try
        {
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                        + "ID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1)  NOT NULL PRIMARY KEY, "
                        + "OVCID VARCHAR(25) NOT NULL, CSIDATE DATE, emotionalHealth NUMERIC(1) DEFAULT 0  NOT NULL, "
                        + "socialBehaviour NUMERIC(1) DEFAULT 0  NOT NULL, foodSecurity NUMERIC(1) DEFAULT 0  NOT NULL, "
                        + "nutritionAndGrowth NUMERIC(1) DEFAULT 0  NOT NULL, wellness NUMERIC(1) DEFAULT 0  NOT NULL, "
                        + "healthCareServices NUMERIC(1) DEFAULT 0  NOT NULL, developmentAndPerformance NUMERIC(1) DEFAULT 0 , "
                        + "educationAndWork NUMERIC(1) DEFAULT 0  NOT NULL, abuseAndExploitation NUMERIC(1) DEFAULT 0  NOT NULL, "
                        + "legalProtection NUMERIC(1) DEFAULT 0  NOT NULL, shelter NUMERIC(1) DEFAULT 0  NOT NULL, "
                        + "care NUMERIC(1) DEFAULT 0  NOT NULL, ASSESSMENTNUMBER NUMERIC(2) DEFAULT 0  NOT NULL, "
                        + "LASTMODIFIEDDATE DATE, TOTALCSISCORE NUMERIC(3) DEFAULT 0  NOT NULL,MARKEDFORDELETE NUMERIC(1) DEFAULT 0  NOT NULL)";
                                                     
                int response=updateDatabase(query);
                message=getMessage(tableName,response);
                System.err.println(message);
                message=createIndexQuery(schemaAndTableName,"OVCID","idx_csi_ovcid");
                message=createIndexQuery(schemaAndTableName,"CSIDATE","idx_csi_csidt");
                message+=createIndexQuery(schemaAndTableName,"emotionalHealth","idx_csi_emh");
                message+=createIndexQuery(schemaAndTableName,"socialBehaviour","idx_csi_sbh");
                message+=createIndexQuery(schemaAndTableName,"foodSecurity","idx_csi_fsec");
                message+=createIndexQuery(schemaAndTableName,"nutritionAndGrowth","idx_csi_ngrt");
                message+=createIndexQuery(schemaAndTableName,"wellness","idx_csi_wel");
                message+=createIndexQuery(schemaAndTableName,"healthCareServices","idx_csi_hlt");
                
                message+=createIndexQuery(schemaAndTableName,"developmentAndPerformance","idx_csi_dev");
                message+=createIndexQuery(schemaAndTableName,"educationAndWork","idx_csi_eaw");
                message+=createIndexQuery(schemaAndTableName,"abuseAndExploitation","idx_csi_abu");
                message+=createIndexQuery(schemaAndTableName,"legalProtection","idx_csi_leg");
                message+=createIndexQuery(schemaAndTableName,"shelter","idx_csi_shlt");
                message+=createIndexQuery(schemaAndTableName,"care","idx_csi_care");
                System.err.println(message);
                //createIndexOnHivStatusTables();
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }//CREATE TABLE ChildStatusIndex (ID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1)  NOT NULL PRIMARY KEY, OVC_ID VARCHAR(25) DEFAULT ' '  NOT NULL, CSI_DATE DATE, CSI_FACTOR1 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR2 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR3 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR4 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR5 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR6 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR7 INTEGER DEFAULT 0 , CSI_FACTOR8 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR9 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR10 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR11 INTEGER DEFAULT 0  NOT NULL, CSI_FACTOR12 INTEGER DEFAULT 0  NOT NULL, SURVEY_NUMBER INTEGER DEFAULT 0  NOT NULL, DATEOFENTRY DATE, TOTALCSISCORE NUMERIC(3) DEFAULT 0  NOT NULL);
    public String createCustomIndicatorsReportTable()
    {
        String tableName="CUSTOMINDICATORSREPORT";
        String schemaAndTableName="APP."+tableName;
        String message="";
        
        try
        {
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                +"RECORDID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1)  NOT NULL PRIMARY KEY,"
                +"LEVEL2OUID VARCHAR(11) NOT NULL, LEVEL3OUID VARCHAR(11) NOT NULL, LEVEL4OUID VARCHAR(11) NOT NULL,"
                +" CBOID VARCHAR(11) NOT NULL, PARTNERCODE VARCHAR(11), MERCODE VARCHAR(20) NOT NULL, "
                +"INDICATORNAME VARCHAR(500) NOT NULL, OTHERDISAGGREGATION VARCHAR(50) NOT NULL, "
                +"REPORTPERIOD VARCHAR(20) NOT NULL, MALELESSTHAN1 NUMERIC(11) DEFAULT 0  NOT NULL, "
                + "MALE1TO4 NUMERIC(11) DEFAULT 0  NOT NULL, MALE5TO9 NUMERIC(11) DEFAULT 0  NOT NULL, "
                +"MALE10TO14 NUMERIC(11) DEFAULT 0  NOT NULL, MALE15TO17 NUMERIC(11) DEFAULT 0  NOT NULL,"
                +"MALE18TO24 NUMERIC(11) DEFAULT 0  NOT NULL, MALE25PLUS NUMERIC(11) DEFAULT 0  NOT NULL, "
                +"FEMALELESSTHAN1 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE1TO4 NUMERIC(11) DEFAULT 0  NOT NULL, "
                +"FEMALE5TO9 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE10TO14 NUMERIC(11) DEFAULT 0  NOT NULL, "
                +"FEMALE15TO17 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE18TO24 NUMERIC(11) DEFAULT 0  NOT NULL, "
                +"FEMALE25PLUS NUMERIC(11) DEFAULT 0  NOT NULL, MALETOTAL NUMERIC(11) DEFAULT 0  NOT NULL, "
                +"FEMALETOTAL NUMERIC(11) DEFAULT 0  NOT NULL, GRANDTOTAL NUMERIC(11) DEFAULT 0  NOT NULL, "
                +"DATECREATED DATE DEFAULT '1900-01-01'  NOT NULL, USERNAME VARCHAR(100) NOT NULL)";
                
                                                      
                int response=updateDatabase(query);
                message=getMessage(tableName,response);
                System.err.println(message);
                message=createIndexQuery(schemaAndTableName,"LEVEL2OUID","idx_cir_l2ou");
                message+=createIndexQuery(schemaAndTableName,"LEVEL3OUID","idx_cir_l3ou");
                message+=createIndexQuery(schemaAndTableName,"CBOID","idx_cir_cbo");
                message+=createIndexQuery(schemaAndTableName,"PARTNERCODE","idx_cir_pcd");
                message+=createIndexQuery(schemaAndTableName,"MERCODE","idx_cir_mer");
                message+=createIndexQuery(schemaAndTableName,"INDICATORNAME","idx_cir_ind");
                message+=createIndexQuery(schemaAndTableName,"REPORTPERIOD","idx_cir_prd");
                System.err.println(message);
                //createIndexOnHivStatusTables();
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }//CREATE TABLE APP.CUSTOMINDICATORSREPORT2 (RECORDID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment  by 1)  NOT NULL PRIMARY KEY, LEVEL2OUID VARCHAR(11) NOT NULL, LEVEL3OUID VARCHAR(11) NOT NULL, LEVEL4OUID VARCHAR(11) NOT NULL, CBOID VARCHAR(11) NOT NULL, PARTNERCODE VARCHAR(11), MERCODE VARCHAR(20) NOT NULL, INDICATORNAME VARCHAR(200) NOT NULL, OTHERDISAGGREGATION VARCHAR(50) NOT NULL, "PERIOD" VARCHAR(20) NOT NULL, MALELESSTHAN1 NUMERIC(11) DEFAULT 0  NOT NULL, MALE1TO4 NUMERIC(11) DEFAULT 0  NOT NULL, MALE5TO9 NUMERIC(11) DEFAULT 0  NOT NULL, MALE10TO14 NUMERIC(11) DEFAULT 0  NOT NULL, MALE15TO17 NUMERIC(11) DEFAULT 0  NOT NULL, MALE18TO24 NUMERIC(11) DEFAULT 0  NOT NULL, MALE25PLUS NUMERIC(11) DEFAULT 0  NOT NULL, FEMALELESSTHAN1 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE1TO4 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE5TO9 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE10TO14 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE15TO17 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE18TO24 NUMERIC(11) DEFAULT 0  NOT NULL, FEMALE25PLUS NUMERIC(11) DEFAULT 0  NOT NULL, MALETOTAL NUMERIC(11) DEFAULT 0  NOT NULL, FEMALETOTAL NUMERIC(11) DEFAULT 0  NOT NULL, GRANDTOTAL NUMERIC(11) DEFAULT 0  NOT NULL, DATECREATED DATE DEFAULT '1900-01-01'  NOT NULL, USERNAME VARCHAR(100) NOT NULL);
    public String updateBaselineAndCurrentAgeUnitsInChildEnrollment()
    {
        String query="update APP.CHILDENROLLMENT SET AGEUNITATBASELINE=2, CURRENTAGEUNIT=2 WHERE AGEUNITATBASELINE=0";
        int result=updateDatabase(query);
        String message=result+"Child age units updated. ";
        System.err.println(message);
        return message;
    }
    public String changeAgeUnitTableInEnrollmentStatusHistory()
    {
        int result=0;
        String message="";
        if(!columnExists("ENROLLMENTSTATUSHISTORY","CURRENTAGEUNIT_FLAG"))
        {
            String query="ALTER TABLE APP.ENROLLMENTSTATUSHISTORY ADD COLUMN CURRENTAGEUNIT_FLAG NUMERIC(1) DEFAULT 0";
            result=updateDatabase(query);
            query="update APP.ENROLLMENTSTATUSHISTORY SET CURRENTAGEUNIT_FLAG=1 where CURRENTAGEUNIT='1'";
            result=updateDatabase(query);
            query="update APP.ENROLLMENTSTATUSHISTORY SET CURRENTAGEUNIT_FLAG=2 where CURRENTAGEUNIT='2'";
            result=updateDatabase(query);
            query="ALTER TABLE APP.ENROLLMENTSTATUSHISTORY DROP COLUMN CURRENTAGEUNIT";
            result=updateDatabase(query);

            query="ALTER TABLE APP.ENROLLMENTSTATUSHISTORY ADD COLUMN CURRENTAGEUNIT NUMERIC(1) NOT NULL DEFAULT 0";
            result=updateDatabase(query);
            query="update APP.ENROLLMENTSTATUSHISTORY SET CURRENTAGEUNIT=CURRENTAGEUNIT_FLAG";
            result=updateDatabase(query);

            createIndexQuery("APP.ENROLLMENTSTATUSHISTORY","CURRENTAGEUNIT","idx_esh_ageu");
            message=result+"Current age unit updated in enrollment status history. ";
            System.err.println(message);
        }
        return message;
    }
    public String updateEnrollmentStatusHistory()
    {
        String query="update APP.ENROLLMENTSTATUSHISTORY SET CURRENTAGEUNIT=2 WHERE CURRENTAGEUNIT=0";
        int result=updateDatabase(query);
        String message=result+"Current age unit updated in enrollment status history. ";
        System.err.println(message);
        return message;
    }
    public String updateCurrentEnrollmentStatus()
    {
        String query="update APP.CHILDENROLLMENT SET CURRENTENROLLMENTSTATUS=1 WHERE CURRENTENROLLMENTSTATUS=0";
        int result=updateDatabase(query);
        query="update APP.ADULTHOUSEHOLDMEMBER SET CURRENTENROLLMENTSTATUS=1 WHERE CURRENTENROLLMENTSTATUS=0";
        int adultResult=result=updateDatabase(query);
        String message=result+"Child current enrollment status updated; "+adultResult+"Adult current enrollment status updated; ";
        System.err.println(message);
        return message;
    }
    public String createHivStatusHistoryTable()
    {
        String tableName="HIVSTATUSHISTORY";
        String schemaAndTableName="APP."+tableName;
        String message="";
        String processId="hivhistytbl";
        String processName="Hiv Status History table created";
        try
        {
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                +"RECORDID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1)  NOT NULL PRIMARY KEY,"
                +"BENEFICIARYID VARCHAR(25) NOT NULL, NEWHIVSTATUS NUMERIC(1) DEFAULT 0  NOT NULL, DATEOFNEWSTATUS DATE NOT NULL, "
                +"ENROLLEDONTREATMENT NUMERIC(1) DEFAULT 0  NOT NULL, FACILITYID VARCHAR(11), DATECREATED DATE NOT NULL,"
                +"LASTMODIFIEDDATE DATE NOT NULL, RECORDEDBY VARCHAR(25) NOT NULL, BENEFICIARYTYPE NUMERIC(1) DEFAULT 0  NOT NULL,"
                +"POINTOFUPDATE NUMERIC(1) DEFAULT 0  NOT NULL)";
                                                      
                int response=updateDatabase(query);
                message=getMessage(tableName,response);
                System.err.println(message);
                message=createIndexQuery(schemaAndTableName,"BENEFICIARYID","idx_hsh_bid");
                message+=createIndexQuery(schemaAndTableName,"NEWHIVSTATUS","idx_hsh_nhs");
                message+=createIndexQuery(schemaAndTableName,"DATEOFNEWSTATUS","idx_hsh_dns");
                message+=createIndexQuery(schemaAndTableName,"ENROLLEDONTREATMENT","idx_hsh_trmt");
                System.err.println(message);
                //createIndexOnHivStatusTables();
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
    public String createEnrollmentStatusHistoryTable()
    {
        String tableName="ENROLLMENTSTATUSHISTORY";
        String schemaAndTableName="APP."+tableName;
        String message="";
        String processId="enrhistytbl";
        String processName="Enrollment Status History table created";
        try
        {
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                +"RECORDID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1)  NOT NULL PRIMARY KEY,"
                +"BENEFICIARYID VARCHAR(25) NOT NULL, ENROLLMENTSTATUS NUMERIC(2) DEFAULT 0  NOT NULL, DATEOFENROLLMENTSTATUS DATE NOT NULL, "
                +"HIVSTATUS NUMERIC(1) DEFAULT 0  NOT NULL, DATEOFHIVSTATUS DATE NOT NULL, ENROLLEDONTREATMENT NUMERIC(1) DEFAULT 0  NOT NULL, FACILITYID VARCHAR(11), DATECREATED DATE NOT NULL,"
                +"CURRENTAGE NUMERIC(3) DEFAULT 0  NOT NULL,CURRENTAGEUNIT NUMERIC(1) NOT NULL DEFAULT 0, LASTMODIFIEDDATE DATE NOT NULL, RECORDEDBY VARCHAR(25) NOT NULL, BENEFICIARYTYPE NUMERIC(1) DEFAULT 0  NOT NULL,"
                +"POINTOFUPDATE NUMERIC(1) DEFAULT 0  NOT NULL)";

                int response=updateDatabase(query);
                message=getMessage(tableName,response);
                System.err.println(message);
                message=createIndexQuery(schemaAndTableName,"BENEFICIARYID","idx_esh_bid");
                message+=createIndexQuery(schemaAndTableName,"ENROLLMENTSTATUS","idx_esh_ens");
                message+=createIndexQuery(schemaAndTableName,"DATEOFENROLLMENTSTATUS","idx_esh_des");
                message+=createIndexQuery(schemaAndTableName,"ENROLLEDONTREATMENT","idx_esh_trmt");
                message+=createIndexQuery(schemaAndTableName,"HIVSTATUS","idx_esh_hivs");
                message+=createIndexQuery(schemaAndTableName,"DATEOFHIVSTATUS","idx_esh_dhiv");
                message+=createIndexQuery(schemaAndTableName,"CURRENTAGE","idx_esh_cage");
                message+=createIndexQuery(schemaAndTableName,"CURRENTAGEUNIT","idx_esh_ageu");
                System.err.println(message);
                //createIndexOnHivStatusTables();
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
    public String createQuarterlyStatusTrackerTable()
    {
        String tableName="QUARTERLYSTATUSTRACKER";
        String schemaAndTableName="APP."+tableName;
        String message="";
        String processId="qstrackrtbl";
        String processName="Quarterly Status tracker table created";
        try
        {
            if(!tableExists(tableName))
            {
                String query="CREATE TABLE "+schemaAndTableName+"("
                +"RECORDID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1)  NOT NULL PRIMARY KEY,"
                +"BENEFICIARYID VARCHAR(25) NOT NULL, ENROLLMENTSTATUS NUMERIC(2) DEFAULT 0  NOT NULL, DATEOFENROLLMENTSTATUS DATE NOT NULL, "
                +"HIVSTATUS NUMERIC(1) DEFAULT 0  NOT NULL, DATEOFHIVSTATUS DATE NOT NULL, ENROLLEDONTREATMENT NUMERIC(1) DEFAULT 0  NOT NULL, FACILITYID VARCHAR(11), DATECREATED DATE NOT NULL,"
                +"CURRENTAGE NUMERIC(3) DEFAULT 0  NOT NULL,CURRENTAGEUNIT NUMERIC(1) NOT NULL DEFAULT 0, LASTMODIFIEDDATE DATE NOT NULL, RECORDEDBY VARCHAR(25) NOT NULL, BENEFICIARYTYPE NUMERIC(1) DEFAULT 0  NOT NULL,"
                +"POINTOFUPDATE NUMERIC(1) DEFAULT 0  NOT NULL,REPORTQUARTER VARCHAR(6) NOT NULL,AUTOGEN NUMERIC(1) DEFAULT 0  NOT NULL)";

                int response=updateDatabase(query);
                message=getMessage(tableName,response);
                System.err.println(message);
                message=createIndexQuery(schemaAndTableName,"BENEFICIARYID","idx_qst_bnid");
                message+=createIndexQuery(schemaAndTableName,"ENROLLMENTSTATUS","idx_qst_ens");
                message+=createIndexQuery(schemaAndTableName,"DATEOFENROLLMENTSTATUS","idx_qst_des");
                message+=createIndexQuery(schemaAndTableName,"ENROLLEDONTREATMENT","idx_qst_trmt");
                message+=createIndexQuery(schemaAndTableName,"HIVSTATUS","idx_qst_hivs");
                message+=createIndexQuery(schemaAndTableName,"DATEOFHIVSTATUS","idx_qst_dhiv");
                message+=createIndexQuery(schemaAndTableName,"CURRENTAGE","idx_qst_cage");
                message+=createIndexQuery(schemaAndTableName,"CURRENTAGEUNIT","idx_qst_ageu");
                message+=createIndexQuery(schemaAndTableName,"REPORTQUARTER","idx_qst_rqt");
                System.err.println(message);
            }  
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            //return false;
        }
        return message;
    }
    public boolean addTreatmentIdToChildEnrollment()
    {
        boolean executed=false;
        String tableName="CHILDENROLLMENT";
        String columnName="TREATMENTID";
        try
        {
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    String query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(25)";
                    int updateSuccess=util.executeSQLupdate(query);  
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
    public boolean addTreatmentIdToAdultHouseholdMember()
    {
        boolean executed=false;
        String tableName="ADULTHOUSEHOLDMEMBER";
        String columnName="TREATMENTID";
        try
        {
            
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,columnName))
                {
                    String query="ALTER TABLE APP."+tableName+" ADD COLUMN "+columnName+" VARCHAR(25)";
                    int updateSuccess=util.executeSQLupdate(query);  
                }
            }
            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+columnName+" on "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }//hivTreatmentId
   public boolean addDateOfCurrentSchoolStatusToChildEnrollment()
    {
        boolean executed=false;
        try
        {
            String tableName="CHILDENROLLMENT";
            DaoUtility util=new DaoUtility();
            if(tableExists(tableName))
            {   
                if(!columnExists(tableName,"DATEOFCURRENTSCHOOLSTATUS"))
                {
                    String query="ALTER TABLE APP."+tableName+" ADD COLUMN DATEOFCURRENTSCHOOLSTATUS DATE";
                    int updateSuccess=util.executeSQLupdate(query); 
                    if(updateSuccess==1)
                    {
                        String updateScript="UPDATE APP.CHILDENROLLMENT SET DATEOFCURRENTSCHOOLSTATUS=DATEOFENROLLMENT";
                        util.executeSQLupdate(updateScript); 
                        if(updateSuccess==1)
                        executed=true;
                    }
                }
            }
            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DATASETSETTING table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }//
   public boolean createDataImportFileUploadTable()
    {
        boolean executed=false;
        String tableName="DATAIMPORTFILEUPLOAD";
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists(tableName))
            {   
                
                String query="CREATE TABLE APP.DATAIMPORTFILEUPLOAD "
                        + "(RECORDID INTEGER GENERATED ALWAYS AS IDENTITY (Start with 1,increment by 1) NOT NULL PRIMARY KEY, "
                        + "IMPORTFILENAME VARCHAR(1000), IMPORTDIRECTORYPATH VARCHAR(1000), USERNAME VARCHAR(25) NOT NULL, "
                        + "PARTNERCODE VARCHAR(11) NOT NULL, DATEOFUPLOAD DATE NOT NULL, DATEIMPORTCOMPLETED DATE NOT NULL, "
                        + "TIMEIMPORTCOMPLETED VARCHAR(25) DEFAULT '00-00-00'  NOT NULL, "
                        + "IMPORTSTATUS NUMERIC(2) DEFAULT 0  NOT NULL, SELECTEDTABLECODES VARCHAR(1000), "
                        + "LASTPROCESSEDTABLEINDEX NUMERIC(3) DEFAULT 0  NOT NULL, IMPORTOPTIONS VARCHAR(1000))";
                int updateSuccess=util.executeSQLupdate(query); 
                if(updateSuccess==1)
                executed=true;
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating "+tableName+" table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean createDatasetSettingTable()
    {
        boolean executed=false;
        try
        {
            DaoUtility util=new DaoUtility();
            if(!tableExists("DATASETSETTING"))
            {   
                
                String query="CREATE TABLE APP.DATASETSETTING (MODULEID VARCHAR(11) NOT NULL  PRIMARY KEY, DATASETID VARCHAR(11) NOT NULL, LASTMODIFIEDDATE DATE NOT NULL, RECORDEDBY VARCHAR(25) NOT NULL)";
                int updateSuccess=util.executeSQLupdate(query); 
                if(updateSuccess==1)
                executed=true;
            }
            else if(!columnExists("DATASETSETTING","MODULEID"))
            {
                util.executeSQLupdate("drop table APP.DATASETSETTING"); 
                createDatasetSettingTable();
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DATASETSETTING table "+ex.getMessage());
            NomisLogManager.logStackTrace(ex);
            return false;
        }
        return executed;
    }
   public boolean tableExists(String tableName)
    {
        DaoUtility util=new DaoUtility();
        boolean tableExists=false;
        if(tableName !=null)
        {
            tableName=tableName.toUpperCase();
            if(tableName.indexOf("APP.") !=-1)
            tableName=tableName.substring(tableName.indexOf("APP."));
            
            String query="SELECT TABLENAME FROM SYS.SYSTABLES WHERE UPPER(TABLENAME)='"+tableName+"'";
            List list=util.execSqlQuery(query);
            if(list !=null && !list.isEmpty())
            {
                tableExists=true;
            }
        }
        return tableExists;
    }
    public boolean columnExists(String tableName,String desiredColumn)
     {
         DaoUtility util=new DaoUtility();
         boolean columnExists=false;
         String columnName=null;
         if(tableName !=null)
        {
             if(tableName.indexOf("APP.") !=-1)
             tableName=tableName.substring(4,tableName.length());
             System.err.println("tableName is "+tableName);
             String query="select c.COLUMNNAME from SYS.SYSTABLES t, SYS.SYSCOLUMNS c WHERE t.TABLEID=c.REFERENCEID AND t.TABLENAME='"+tableName+"' and c.COLUMNNAME='"+desiredColumn+"'";
             List list=util.execSqlQuery(query);
             if(list !=null && !list.isEmpty())
             {
                 columnName=(String)list.get(0);
                 if(columnName !=null)
                 columnExists=true;
                 //System.err.println("columnName is "+columnName);
             }
        }
         return columnExists;
     }
    public String createIndexQuery(String tableName,String columnName,String indexName)
   {
       AppUtility appUtil=new AppUtility();
        String message="Index created successfully";
        try
        {
            //String processName="index "+indexName+" created on "+tableName+" for "+columnName;
            String query="create index "+indexName+" on "+tableName+"("+columnName+")";
            //System.err.println(query);
            if(indexName.length()>11)
            indexName=indexName.replace("_", "");
            if(indexName.length()>11)
            indexName=appUtil.generateUniqueId(11);
            int response=0;
            if(columnExists(tableName,columnName))
            {
                response=updateDatabase(query);
                System.err.println(columnName+" exist on table "+tableName);
            }
            if(response==0)
            message=failureTag+"Failed to perform action</label>";
            else
            message="Index created successfully on "+tableName;
            System.err.println(message);
            /*else if(response==1)
            message=successTag+" Successfully created index "+indexName+" on "+columnName+" </label>";
            if(response==2)
            message=failureTag+"An error occured. Unable to create index "+indexName+" on column "+columnName+" </label>";
            System.err.println(message);*/
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return message;
   }
    private int updateDatabase(String query)
   {
       int response=0;
       try
       {
           DaoUtility util=new DaoUtility();
           response=util.executeSQLupdate(query);
       }
       catch(Exception ex)
       {
           response=2;
           ex.printStackTrace();
       }
       return response;
   }
    private String getMessage(String tableName,int returnValue)
   {
        String message="";
        if(returnValue==0)
        message=failureTag+"Failed to perform action</label>";
        else if(returnValue==1)
        message=successTag+tableName+" table created successfully</label>";
        if(returnValue==2)
        message=failureTag+"An error occured. Unable to create table"+tableName+" </label>";
       return message;
   }
    public boolean executeDatabaseUpdate()
    {
        addFieldsToSiteSetupTable();
        addFieldsToNomisUserTable();
        changeAgeUnitTableInEnrollmentStatusHistory();
        boolean executed=createDatasetSettingTable();
        addDateOfCurrentSchoolStatusToChildEnrollment();
        addTreatmentIdToChildEnrollment();
        addTreatmentIdToAdultHouseholdMember();
        createDataImportFileUploadTable();
        createQuarterlyStatusTrackerTable();
        createEnrollmentStatusHistoryTable();
        createHivStatusHistoryTable();
        updateCurrentEnrollmentStatus();
        createCustomIndicatorsReportTable();
        updateBaselineAndCurrentAgeUnitsInChildEnrollment();
        createChildStatusIndexTable();
        createDatimReportTable();
        createIndexesOnHouseholdEnrollmentTable();
        createIndexesOnChildEnrollmentTable();
        createIndexesOnChildServiceTable();
        createNutritionalStatusTable();
        addAgeAtAssessmentToNutritionalAssessmentTable();
        addReferralCompletedToHouseholdReferral();
        addEnrolmentStatusFieldsToBeneficiaryStatusUpdateTable();
        addDatimIdToReferralDirectoryTable();
        addLegacyAssessmentFieldsToRevisedHouseholdAssessmentTable();
        addNumberOfChildrenAndPeopleFieldsToHouseholdEnrollmentTable();
        createOccupationTable();
        addLegacyIdToHouseholdEnrollmentTable();
        addLegacyIdToAdultHouseholdMemberTable();
        addLegacyIdToChildEnrollmentTable();
        addLegacyIdToCommunityBasedOrganizationTable();
        addLegacyIdToOrganizationUnitTable();
        addCustomIndicatorsFieldsToChildServiceTable();
        addFieldsFromNomis2ToCareplanAchievementChecklistTable();
        addReasonsChildMissedSchoolToEduPerfAssessmentTable();
        addLastDrugPickupAndNumberOfDaysOfRefillToCareAndSupportTable();
        alterSizeOfMonthsOfTransportationSupportInCareAndSupportTable();
        updateCurrentHivStatusForChildAndAdultHouseholdMemberTables();
        addVsCategpryToVulnerabilityStatusTable();
        createIndexesOnBeneficiaryStatusUpdateTable();
        createIndexesOnCaregiverAccessToEmergencyFundTable();
        createIndexesOnChildEducationalPerformanceTable();
        createIndexesOnCareplanAchievementChecklistTable();
        createIndexesOnHhVulnerabilityAssessmentTable();
        createIndexesOnCommunityBasedOrganizationTable();
        createIndexesOnCareAndSupportTable();
        createIndexesOnHouseholdReferralTable();
        createIndexesOnNutritionAssessmentTable();
        createIndexesOnSchoolTable();
        //addDateOfStatusToChildEnrollment();
        createAccessPrivilegeTable();
        createUserRoleTable();
        addMainVulnerabilityStatusCodeTable();
        changeSizeOfScoreFieldInCarePlanAchievementTable();
        addDateOfViralTestResultToCareAndSupportTable();
        recreateHouseholdCarePlanTable();
        addChildren18PlusToCustomIdicatorsReportTable();
        createIndexOnHouseholServiceTable();
        createIndexesOnOrganizationUnitTable();
        addFieldsToHivRiskAssessmentTable();
        createIndexOnHivRiskAssessmentTable();
        addFieldsToCommunityWorkerTable();
        addFieldsToNutritionStatusTable();
        updateDateOfCurrentEnrollmentStatusInBeneficiaryTables();
        addMarkedForDeleteFieldToBeneficiaryStatusTable();
        createHivPositiveDataTable(false);
        createHivRiskAssessmentReportTable(false);
        addChildReferredForHIVTestFieldToHivRiskAssessmentTable();
        addChildReferredForHIVTestFieldToHivRiskAssessmentReportTable();
        addEnrollmentSettingToChildEnrollmentTable();
        updateCareAndSupportChecklist();
        createBeneficiaryServiceTable(false);
        createIndexOnBeneficiaryServiceTable();
        createBeneficiaryEnrollmentTable(false);
        createIndexOnBeneficiaryEnrollmentTable();
        createIndexOnHouseholdReferralTable();
        createIndexOnHouseholdServiceTable();
        createIndexOnChildEnrollmentTable();
        createIndexOnAdultHouseholdMemberTable();
        
        createIndexOnCareAndSupportTable();
        createFacilityOvcOfferTable();
        addDatimIdToOrganizationUnitTables();
        addDatimIdToPartnerTables();
        addBeneficiaryTypeToQuarterlyStatusTrackerTables();
        //createHivRiskAssessmentReportStage1Table(false);
        return executed;
    }
}
