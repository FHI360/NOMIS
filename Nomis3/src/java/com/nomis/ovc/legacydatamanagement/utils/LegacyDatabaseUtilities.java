/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.legacydatamanagement.utils;



import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.UserDao;
import com.nomis.ovc.dao.UserDaoImpl;
import com.nomis.ovc.util.AppUtility;
import com.ovc.databasemanagement.BackgroundProcessManager;
import com.ovc.databasemanagement.DatabaseTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author smomoh
 */
public class LegacyDatabaseUtilities implements Serializable
{
    DaoUtility util=new DaoUtility();
    LegacyAppUtility appUtil=new LegacyAppUtility();
    private static List connectionParameters; 
    public void updateAssessment()
    {
        
    }
    public boolean tableExists(String tableName)
    {
        tableName=tableName.toUpperCase();
        boolean tableExists=false;
        String query="SELECT TABLENAME FROM SYS.SYSTABLES WHERE UPPER(TABLENAME)='"+tableName+"'";
        List list=util.execSqlQuery(query);
        if(list !=null && !list.isEmpty())
        {
            //tableExists=true;
        }
        return tableExists;
    }
    public boolean tableTruelyExists(String tableName)
    {
        tableName=tableName.toUpperCase();
        boolean tableExists=false;
        String query="SELECT TABLENAME FROM SYS.SYSTABLES WHERE UPPER(TABLENAME)='"+tableName+"'";
        List list=util.execSqlQuery(query);
        if(list !=null && !list.isEmpty())
        {
            tableExists=true;
        }
        return tableExists;
    }
    public boolean columnExists(String tableName,String desiredColumn)
     {
         boolean columnExists=false;
         String columnName=null;
         String query="select c.COLUMNNAME from SYS.SYSTABLES t, SYS.SYSCOLUMNS c WHERE t.TABLEID=c.REFERENCEID AND t.TABLENAME='"+tableName+"' and c.COLUMNNAME='"+desiredColumn+"'";
         List list=util.execSqlQuery(query);
         if(list !=null && !list.isEmpty())
         {
             columnName=(String)list.get(0);
             if(columnName !=null)
             columnExists=true;
             System.err.println("columnName is "+columnName);
         }
         return columnExists;
     }
    /*public boolean columnExists(String columnName)
    {
        if(columnName !=null)
        columnName=columnName.toUpperCase();
        boolean columnExists=false;
        String query="SELECT COLUMNNAME FROM SYS.SYSCOLUMNS WHERE COLUMNNAME='"+columnName+"'";
        List list=util.execSqlQuery(query);
        if(list !=null && !list.isEmpty())
        {
            columnExists=true;
        }
        return columnExists;
    }*/
   public static List getConnectionParameters()
   {
       return connectionParameters;
   }
   public static void setConnectionParameters(List connectionParameters)
   {
       LegacyDatabaseUtilities.connectionParameters=connectionParameters;
   }
    public String createBackup()
    {
        String msg="";
        String dbBackupDirectory=appUtil.getDatabaseBackupDirectory();
        String currentDbDirectory=appUtil.getDatabaseDirectory();
        appUtil.copyAndPasteFiles(currentDbDirectory, dbBackupDirectory);
        return msg;
    }
    public String defragmentTable(List tableNames,int sequence)
    {
        try
        {
            if(tableNames==null)
            return "<label style='color: red'>No table specified</label>";
            String sql=null;
            String tableName=null;
            String code=null;
            for(int i=0; i<tableNames.size(); i++)
            {
                code=tableNames.get(i).toString();
                tableName=getRealTableName(code);
                if(tableName==null)
                continue;
                sql="CALL SYSCS_UTIL.SYSCS_COMPRESS_TABLE('APP','"+tableName+"',"+sequence+")";
                //System.err.println("tableName is "+sql);
                util.executeSQLupdate(sql);
            }
        }
        catch(Exception ex)
        {
            return "<label style='color: red'>Exception: "+ex.getMessage()+"</label>";
        }
        return "<label style='color: green'>Database defragmentation complete</label>";
    }
    public void createIndexesOnOrganizationUnitGroupAssignmentTable()
    {
        try
        {
            util.executeSQLupdate("create index index_ouga_ouid on ORGANIZATIONUNITGROUPASSIGNMENT(ORGUNITID)");
            util.executeSQLupdate("create index index_ouga_ougid on ORGANIZATIONUNITGROUPASSIGNMENT(ORGUNITGROUPID)");
            util.executeSQLupdate("create index index_ouga_pougid on ORGANIZATIONUNITGROUPASSIGNMENT(PARENTORGUNITGROUPID)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void createIndexesOnSchoolTable()
    {
        try
        {
            util.executeSQLupdate("create index index_sch_lga on SCHOOL(LGA)");
            util.executeSQLupdate("create index index_sch_state on SCHOOL(STATE)");
            util.executeSQLupdate("create index index_sch_name on SCHOOL(NAME)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void createIndexesOnOVCReferralTable()
    {
        try
        {
            util.executeSQLupdate("create index index_ref_ovcid on OVCREFERRAL(OVCID)");
            util.executeSQLupdate("create index index_ref_dof on OVCREFERRAL(DATEOFREFERRAL)");
            util.executeSQLupdate("create index index_ref_type on OVCREFERRAL(TYPE)");
            util.executeSQLupdate("create index index_ref_psy on OVCREFERRAL(PSYCHOSERVICES)");
            util.executeSQLupdate("create index index_ref_nut on OVCREFERRAL(NUTRITIONALSERVICES)");
            util.executeSQLupdate("create index index_ref_health on OVCREFERRAL(HEALTHSERVICES)");
            util.executeSQLupdate("create index index_ref_edu on OVCREFERRAL(EDUCATIONALSERVICES)");
            util.executeSQLupdate("create index index_ref_prot on OVCREFERRAL(PROTECTIONSERVICES)");
            util.executeSQLupdate("create index index_ref_shelter on OVCREFERRAL(SHELTERSERVICES)");
            util.executeSQLupdate("create index index_ref_econ on OVCREFERRAL(ECONOMICSERVICES)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void createIndexesOnSurveyTable()
    {
        try
        {
            util.executeSQLupdate("create index index_fs_ovcId on SURVEY(OVC_ID)");
            util.executeSQLupdate("create index index_fs_dos on SURVEY(DATE_OF_SURVEY)");
            util.executeSQLupdate("create index index_fs_doe on SURVEY(DATEOFENTRY)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public List getTableList()
    {
        List tableList=new ArrayList();
        DatabaseTable dbtable=null;
        
        dbtable=new DatabaseTable();
        dbtable.setCode("vce");
        dbtable.setName("Ovc Enrolment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("csi");
        dbtable.setName("CSI");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("vcs");
        dbtable.setName("OVC Services");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("ref");
        dbtable.setName("Referral Services");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("vcfu");
        dbtable.setName("OVC follow up");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hhe");
        dbtable.setName("HH Enrollment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hva");
        dbtable.setName("HH vulnerability index");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hhs");
        dbtable.setName("HH Service");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hhfu");
        dbtable.setName("HH follow up");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("cgi");
        dbtable.setName("Caregiver");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hsu");
        dbtable.setName("HIV Status update");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("brg");
        dbtable.setName("Birth Registration");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("wdr");
        dbtable.setName("Beneficiary Withdrawal");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hrac");
        dbtable.setName("HIV Rapid assessment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("cpa");
        dbtable.setName("Care plan achievement");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("css");
        dbtable.setName("School status");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("sat");
        dbtable.setName("School attendance");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("cef");
        dbtable.setName("Caregiver expenditure tracker");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("trn");
        dbtable.setName("Training manager");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("trnp");
        dbtable.setName("Training participants manager");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("nass");
        dbtable.setName("Nutrition assessment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("grdchk");
        dbtable.setName("Graduation checklist");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("casc");
        dbtable.setName("Care and Support checklist");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("ovctbhiv");
        dbtable.setName("OVC TB/HIV Checklist");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("cgtbhiv");
        dbtable.setName("Caregiver TB/HIV Checklist");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("sch");
        dbtable.setName("School records");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("oca");
        dbtable.setName("Organization Capacity Assessment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("wrd");
        dbtable.setName("Community");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("orc");
        dbtable.setName("Organization records");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("rfd");
        dbtable.setName("Referral facilities");
        tableList.add(dbtable);
        return tableList;
    }
    private String getRealTableName(String code)
    {
        String tableName=null;
        if(code.equalsIgnoreCase("vce"))
        tableName="ENROLLMENT";
        else if(code.equalsIgnoreCase("vcs"))
        tableName="SERVICE";
        else if(code.equalsIgnoreCase("csi"))
        tableName="ASSESSMENT";
        else if(code.equalsIgnoreCase("hhe"))
        tableName="HOUSEHOLDENROLLMENT";
        else if(code.equalsIgnoreCase("hhs"))
        tableName="HOUSEHOLDSERVICE";
        else if(code.equalsIgnoreCase("vcfu"))
        tableName="SURVEY";
        else if(code.equalsIgnoreCase("hhfu"))
        tableName="HOUSEHOLDFOLLOWUPASSESSMENT";
        else if(code.equalsIgnoreCase("hva"))
        tableName="HHVULNERABILITYASSESSMENT";
        else if(code.equalsIgnoreCase("cgi"))
        tableName="CAREGIVERINFO";
        else if(code.equalsIgnoreCase("hsu"))
        tableName="HIVSTATUSUPDATE";
        else if(code.equalsIgnoreCase("dlr"))
        tableName="DELETEDRECORD";
        else if(code.equalsIgnoreCase("brg"))
        tableName="BIRTHREGISTRATION";
        else if(code.equalsIgnoreCase("wdr"))
        tableName="WITHDRAWAL";
        else if(code.equalsIgnoreCase("hrac"))
        tableName="HIVRISKASSESSMENTCHECKLIST";
        else if(code.equalsIgnoreCase("cpa"))
        tableName="CAREPLANACHIEVEMENT";
        else if(code.equalsIgnoreCase("css"))
        tableName="CHILDSCHOOLSTATUS";
        else if(code.equalsIgnoreCase("sat"))
        tableName="SCHOOLATTENDANCETRACKER";
        else if(code.equalsIgnoreCase("cef"))
        tableName="CAREGIVEREXPENDITUREANDSCHATTENDANCE";
        else if(code.equalsIgnoreCase("ref"))
        tableName="OVCREFERRAL";
        else if(code.equalsIgnoreCase("trn"))
        tableName="TRAINING";
        else if(code.equalsIgnoreCase("trnp"))
        tableName="TRAININGPARTICIPANT";
        else if(code.equalsIgnoreCase("nass"))
        tableName="NUTRITIONASSESSMENT";
        else if(code.equalsIgnoreCase("refdir"))
        tableName="REFERRALDIRECTORY";
        else if(code.equalsIgnoreCase("grdchk"))
        tableName="GRADUATIONCHECKLIST";
        else if(code.equalsIgnoreCase("casc"))
        tableName="CAREANDSUPPORTCHECKLIST";
        else if(code.equalsIgnoreCase("ovctbhiv"))
        tableName="OVCTBHIVSCREENINGCHECKLIST";
        else if(code.equalsIgnoreCase("cgtbhiv"))
        tableName="CAREGIVERTBHIVSCREENINGCHECKLIST";
        else if(code.equalsIgnoreCase("sch"))
        tableName="SCHOOL";
        else if(code.equalsIgnoreCase("oca"))
        tableName="ORGCAPACITYASSESSMENT";
        else if(code.equalsIgnoreCase("wrd"))
        tableName="WARD";
        else if(code.equalsIgnoreCase("orc"))
        tableName="ORGRECORDS";
        else if(code.equalsIgnoreCase("rfd"))
        tableName="REFERRALDIRECTORY";
        return tableName;
    }
    public boolean createNomisConfigurationFile(String tagName,int tagValue,String query)
    {
        NomisConfiguration nconfig=new NomisConfiguration();
        List tagNames=nconfig.getTagNames();
        List tagValues=nconfig.getTagValues();
        BackgroundProcessManager bpm=new BackgroundProcessManager();
        if(!bpm.backgroundProcessExecuted(tagName,tagValue))
        {
            try
            {
                util.executeSQLupdate(query);
                if(tableTruelyExists("ProcessMonitor"))
                bpm.markBackgroundProcessAsExecuted(tagName,tagValue);
            }
            catch(Exception ex)
            {
                bpm.markBackgroundProcessAsExecuted(tagName,tagValue);
                String msg=ex.getMessage();
                System.err.println("Exception message in createNomisConfigurationFile is "+msg);
                //if(msg.indexOf("exists") ==-1)
                return false;
            }
        }
        /*if(tagNames.isEmpty() || !tagNames.contains(tagName))
        {
            tagNames.add(tagName);
            tagValues.add(tagValue);
            try
            {
                util.executeSQLupdate(query);
            }
            catch(Exception ex)
            {
                String msg=ex.getMessage();
                //System.err.println("Exception message in createNomisConfigurationFile is "+msg);
                //if(msg.indexOf("exists") ==-1)
                return false;
            }
            nconfig.createXmlConfigurationFile(tagNames,tagValues);
            return true;
        }*/
        return false;
    }
    public boolean addFieldsToHivStatusUpdateTableTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HIVSTATUSUPDATE add column TREATMENTID VARCHAR(25)";
            executed=createNomisConfigurationFile("treatmentIdAddedToHivStatusUpdate",0,query);
            query="alter table HIVSTATUSUPDATE add column DATEENROLLEDONTREATMENT DATE NOT NULL DEFAULT '1900-01-01'";
            executed=createNomisConfigurationFile("dateEnrolledOnTreatmentAddedToHivStatusUpdate",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addPartnerCodeToNomisUserTable()
    {
        boolean executed=false;
        try
        {
            if(!columnExists("NOMISUSER","PARTNERCODE"))
            {
                String query="alter table NOMISUSER add column PARTNERCODE VARCHAR(1000)";
                executed=createNomisConfigurationFile("partnerCodeAddedToNomisUser",0,query);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addCurrentStatusCodeToEnrollmentTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table Enrollment add column CURRENTENROLLMENTSTATUSCODE NUMERIC(2) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("ovcCurrentStatusCode",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addLastServiceDateToEnrollmentTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table Enrollment add column LASTSERVICEDATE DATE NOT NULL DEFAULT '1900-01-01'";
            executed=createNomisConfigurationFile("ovcLastServiceDate",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addMarkedForDeleteColumnToTables()
    {
        boolean executed=false;
        try
        {
            String query="alter table HouseholdEnrollment add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhRmarkedForDelete",0,query);
            query="alter table CaregiverInfo add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgRmarkedForDelete",0,query);
            query="alter table Enrollment add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("vcenrRmarkedForDelete",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhfuRmarkedForDelete",0,query);
            query="alter table HOUSEHOLDSERVICE add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhsRmarkedForDelete",0,query);
            query="alter table SERVICE add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("serviceRmarkedForDelete",0,query);
            query="alter table SURVEY add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("surveyRmarkedForDelete",0,query);
            
            query="alter table ASSESSMENT add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("csiRmarkedForDelete",0,query);
            query="alter table BIRTHREGISTRATION add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("bregRmarkedForDelete",0,query);
            query="alter table CAREANDSUPPORTCHECKLIST add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cscRmarkedForDelete",0,query);
            query="alter table CAREGIVEREXPENDITUREANDSCHATTENDANCE add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgexpRmarkedForDelete",0,query);
            query="alter table CAREGIVERTBHIVSCREENINGCHECKLIST add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgtbhivRmarkedForDelete",0,query);
            query="alter table CAREPLANACHIEVEMENT add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cpaRmarkedForDelete",0,query);
            query="alter table CBOSETUP add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cbosetupRmarkedForDelete",0,query);
            query="alter table DATABASEIMPORTTRACKER add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("dbitrkRmarkedForDelete",0,query);
            query="alter table ELIGIBILITY add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("eligstatusRmarkedForDelete",0,query);
            query="alter table GENDERNORMCOHORTATTENDANCE add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("gndnormchtRmarkedForDelete",0,query);
            query="alter table GRADUATIONCHECKLIST add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("grdchklistRmarkedForDelete",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hracRmarkedForDelete",0,query);
            query="alter table NUTRITIONASSESSMENT add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("naRmarkedForDelete",0,query);
            query="alter table ORGCAPACITYASSESSMENT add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("ocaRmarkedForDelete",0,query);
            query="alter table OVCREFERRAL add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("refRmarkedForDelete",0,query);
            query="alter table REFERRALDIRECTORY add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("refdirRmarkedForDelete",0,query);
            //query="alter table OVCSCHOOLATTENDANCE add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            //executed=createNomisConfigurationFile("osaRmarkedForDelete",0,query);
            query="alter table OVCTBHIVSCREENINGCHECKLIST add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("vctbhivRmarkedForDelete",0,query);
            query="alter table TRAINING add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngRRmarkedForDelete",0,query);
            query="alter table TRAININGCATEGORY add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngcatRmarkedForDelete",0,query);
            query="alter table TRAININGDESIGNATION add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngdesRmarkedForDelete",0,query);
            query="alter table TRAININGINFORMATIONSETUP add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trnginfoRmarkedForDelete",0,query);
            query="alter table TRAININGPARTICIPANT add column RMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trnpatRmarkedForDelete",0,query);
            query="alter table TRAININGSTATUSSETUP add column RRRMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngstatRmarkedForDelete",0,query);
            query="alter table WITHDRAWAL add column RRRMARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("withdrawalRmarkedForDelete",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    /*public boolean addMarkedForDeleteColumnToTables()
    {
        boolean executed=false;
        try
        {
            String query="alter table HouseholdEnrollment add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhmarkedForDelete",0,query);
            query="alter table CaregiverInfo add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgmarkedForDelete",0,query);
            query="alter table Enrollment add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("vcenrmarkedForDelete",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhfumarkedForDelete",0,query);
            query="alter table HOUSEHOLDSERVICE add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhsmarkedForDelete",0,query);
            query="alter table SERVICE add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("servicemarkedForDelete",0,query);
            query="alter table SURVEY add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("surveymarkedForDelete",0,query);
            
            query="alter table ASSESSMENT add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("csimarkedForDelete",0,query);
            query="alter table BIRTHREGISTRATION add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("bregmarkedForDelete",0,query);
            query="alter table CAREANDSUPPORTCHECKLIST add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cscmarkedForDelete",0,query);
            query="alter table CAREGIVEREXPENDITUREANDSCHATTENDANCE add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgexpmarkedForDelete",0,query);
            query="alter table CAREGIVERTBHIVSCREENINGCHECKLIST add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgtbhivmarkedForDelete",0,query);
            query="alter table CAREPLANACHIEVEMENT add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cpamarkedForDelete",0,query);
            query="alter table CBOSETUP add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cbosetupmarkedForDelete",0,query);
            query="alter table DATABASEIMPORTTRACKER add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("dbitrkmarkedForDelete",0,query);
            query="alter table ELIGIBILITY add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("eligstatusmarkedForDelete",0,query);
            query="alter table GENDERNORMCOHORTATTENDANCE add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("gndnormchtmarkedForDelete",0,query);
            query="alter table GRADUATIONCHECKLIST add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("grdchklistmarkedForDelete",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hracmarkedForDelete",0,query);
            query="alter table NUTRITIONASSESSMENT add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("namarkedForDelete",0,query);
            query="alter table ORGCAPACITYASSESSMENT add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("ocamarkedForDelete",0,query);
            query="alter table OVCREFERRAL add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("refmarkedForDelete",0,query);
            query="alter table REFERRALDIRECTORY add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("refdirmarkedForDelete",0,query);
            //query="alter table OVCSCHOOLATTENDANCE add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            //executed=createNomisConfigurationFile("osamarkedForDelete",0,query);
            query="alter table OVCTBHIVSCREENINGCHECKLIST add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("vctbhivmarkedForDelete",0,query);
            query="alter table TRAINING add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngmarkedForDelete",0,query);
            query="alter table TRAININGCATEGORY add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngcatmarkedForDelete",0,query);
            query="alter table TRAININGDESIGNATION add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngdesmarkedForDelete",0,query);
            query="alter table TRAININGINFORMATIONSETUP add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trnginfomarkedForDelete",0,query);
            query="alter table TRAININGPARTICIPANT add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trnpatmarkedForDelete",0,query);
            query="alter table TRAININGSTATUSSETUP add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngstatmarkedForDelete",0,query);
            query="alter table WITHDRAWAL add column MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("withdrawalmarkedForDelete",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }*/
    /*public boolean modifyMarkedForDeleteColumns()
    {
        boolean executed=false;
        try
        {
            String query="alter table HouseholdEnrollment alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhmarkedForDelete",0,query);
            query="alter table CaregiverInfo alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgmarkedForDelete",0,query);
            query="alter table Enrollment alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("vcenrmarkedForDelete",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhfumarkedForDelete",0,query);
            query="alter table HOUSEHOLDSERVICE alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhsmarkedForDelete",0,query);
            query="alter table SERVICE alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("servicemarkedForDelete",0,query);
            query="alter table SURVEY alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("surveymarkedForDelete",0,query);
            
            query="alter table ASSESSMENT alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("csimarkedForDelete",0,query);
            query="alter table BIRTHREGISTRATION alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("bregmarkedForDelete",0,query);
            query="alter table CAREANDSUPPORTCHECKLIST alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cscmarkedForDelete",0,query);
            query="alter table CAREGIVEREXPENDITUREANDSCHATTENDANCE alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgexpmarkedForDelete",0,query);
            query="alter table CAREGIVERTBHIVSCREENINGCHECKLIST alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cgtbhivmarkedForDelete",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cpamarkedForDelete",0,query);
            query="alter table CBOSETUP alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("cbosetupmarkedForDelete",0,query);
            query="alter table DATABASEIMPORTTRACKER alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("dbitrkmarkedForDelete",0,query);
            query="alter table ELIGIBILITY alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("eligstatusmarkedForDelete",0,query);
            query="alter table GENDERNORMCOHORTATTENDANCE alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("gndnormchtmarkedForDelete",0,query);
            query="alter table GRADUATIONCHECKLIST alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("grdchklistmarkedForDelete",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hracmarkedForDelete",0,query);
            query="alter table NUTRITIONASSESSMENT alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("namarkedForDelete",0,query);
            query="alter table ORGCAPACITYASSESSMENT alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("ocamarkedForDelete",0,query);
            query="alter table OVCREFERRAL alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("refmarkedForDelete",0,query);
            query="alter table REFERRALDIRECTORY alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("refdirmarkedForDelete",0,query);
            //query="alter table OVCSCHOOLATTENDANCE alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            //executed=createNomisConfigurationFile("osamarkedForDelete",0,query);
            query="alter table OVCTBHIVSCREENINGCHECKLIST alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("vctbhivmarkedForDelete",0,query);
            query="alter table TRAINING alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngmarkedForDelete",0,query);
            query="alter table TRAININGCATEGORY alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngcatmarkedForDelete",0,query);
            query="alter table TRAININGDESIGNATION alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngdesmarkedForDelete",0,query);
            query="alter table TRAININGINFORMATIONSETUP alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trnginfomarkedForDelete",0,query);
            query="alter table TRAININGPARTICIPANT alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trnpatmarkedForDelete",0,query);
            query="alter table TRAININGSTATUSSETUP alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("trngstatmarkedForDelete",0,query);
            query="alter table WITHDRAWAL alter COLUMN MARKEDFORDELETE set DATA TYPE  NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("withdrawalmarkedForDelete",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }*/
    public boolean changeHivStatusFieldSizeInHouseholdEnrollmentTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HOUSEHOLDENROLLMENT alter COLUMN HIVSTATUS set DATA TYPE VARCHAR(8) DEFAULT 'unknown'";
            executed=createNomisConfigurationFile("hivStatusChangedInHheTable",0,query);
            //System.err.println("HIV status changed in changeHivStatusFieldSizeInHouseholdEnrollmentTable()");                
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean createDatimReportTable()
    {
        boolean executed=false;
        try
        {
            if(!tableTruelyExists("DATIMREPORT"))
            {
                String query="CREATE TABLE DATIMREPORT (RECORDID INTEGER GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1)  NOT NULL PRIMARY KEY, "
                        + "STATEOU VARCHAR(100) NOT NULL, LGA VARCHAR(100) NOT NULL, CBO VARCHAR(100) NOT NULL, DPERIOD VARCHAR(50) NOT NULL,"
                        + " OVC_SERVLESSTHAN1 NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERV1TO4 NUMERIC(11) DEFAULT 0  NOT NULL,"
                        + " OVC_SERV5TO9 NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERV1TO9 NUMERIC(11) DEFAULT 0  NOT NULL,"
                        + " M10TO14 NUMERIC(11) DEFAULT 0  NOT NULL, M15TO17 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "M18TO24 NUMERIC(11) DEFAULT 0  NOT NULL, M25PLUS NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "F1TO4 NUMERIC(11) DEFAULT 0  NOT NULL, F5TO9 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "F10TO14 NUMERIC(11) DEFAULT 0  NOT NULL, F15TO17 NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "F18TO24 NUMERIC(11) DEFAULT 0  NOT NULL, F25PLUS NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OVC_SERV NUMERIC(11) DEFAULT 0  NOT NULL, MALETOTAL NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "FEMALETOTAL NUMERIC(11) DEFAULT 0  NOT NULL, GRANDTOTAL NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "HIV_STATNUMERATOR NUMERIC(11) DEFAULT 0  NOT NULL, TOTALPOSITIVE NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "ENROLLEDONART NUMERIC(11) DEFAULT 0  NOT NULL, NOTENROLLEDONART NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "TOTALNEGATIVE NUMERIC(11) DEFAULT 0  NOT NULL, TOTALUNKNOWN NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "TESTNOTINDICATED NUMERIC(11) DEFAULT 0  NOT NULL, OTHERREASONS NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OVC_SERVNUMERATOR NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERVACTIVE NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OVC_SERVGRADUATED NUMERIC(11) DEFAULT 0  NOT NULL, OVC_SERVTRANSFERED NUMERIC(11) DEFAULT 0  NOT NULL, "
                        + "OVC_SERVEXITEDWITHOUTGRADUATION NUMERIC(11) DEFAULT 0  NOT NULL, DATECREATED DATE DEFAULT '1900-01-01'  NOT NULL, "
                        + "LASTMODIFIEDDATE DATE DEFAULT '1900-01-01'  NOT NULL, USERNAME VARCHAR(100) NOT NULL, "
                        + "PARTNERCODE VARCHAR(25), PARTNERNAME VARCHAR(200))";
                        //util.executeSQLupdate(query);
                        String processName="datimReportTable";
                        executed=createNomisConfigurationFile(processName,0,query);
                        if(executed)
                        {
                           System.err.println("DATIMREPORT Table created ");
                        }
            }
                    createNomisConfigurationFile("idx_stateoudatrep",0,"create index idx_stateoudatrep on DATIMREPORT(STATEOU)");
                    createNomisConfigurationFile("idx_lgadatrep",0,"create index idx_lgadatrep on DATIMREPORT(LGA)");
                    createNomisConfigurationFile("idx_cbodatrep",0,"create index idx_cbodatrep on DATIMREPORT(CBO)");
                    createNomisConfigurationFile("idx_perioddatrep",0,"create index idx_perioddatrep on DATIMREPORT(DPERIOD)");
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DATIMREPORT table "+ex.getMessage());
            return false;
        }
        return executed;
    }
    public boolean createImportFileUploadTable()
    {
        boolean executed=false;
        try
        {
            if(!tableTruelyExists("DATAIMPORTFILEUPLOAD"))
            {
                String query="CREATE TABLE DATAIMPORTFILEUPLOAD (RECORDID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY, IMPORTFILENAME VARCHAR(1000), "
                        + "IMPORTDIRECTORYPATH VARCHAR(1000),USERNAME VARCHAR(25) NOT NULL, PARTNERCODE VARCHAR(11) NOT NULL,DATEOFUPLOAD DATE NOT NULL,DATEIMPORTCOMPLETED DATE NOT NULL DEFAULT '1900-01-01',"
                        + "TIMEIMPORTCOMPLETED VARCHAR(25) NOT NULL DEFAULT '00-00-00',IMPORTSTATUS NUMERIC(2) NOT NULL DEFAULT 0)";
                        //util.executeSQLupdate(query);
                        String processName="importFileUploadTable";
                        executed=createNomisConfigurationFile(processName,0,query);
                        util.executeSQLupdate("create index idx_ifuuser on DATAIMPORTFILEUPLOAD(USERNAME)");
                        util.executeSQLupdate("create index idx_ifudou on DATAIMPORTFILEUPLOAD(DATEOFUPLOAD)");
                        //String processName="importFileUploadTable";
                        BackgroundProcessManager bpm=new BackgroundProcessManager();
                        bpm.markBackgroundProcessAsExecuted(processName,0);
                        System.err.println("DATAIMPORTFILEUPLOAD created ");
            }
            addFieldsToDataImportFileUploadTableTable();
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DATAIMPORTFILEUPLOAD table "+ex.getMessage());
            return false;
        }
        return executed;
    }
    public boolean addFieldsToDataImportFileUploadTableTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table DATAIMPORTFILEUPLOAD add COLUMN SELECTEDTABLECODES VARCHAR(1000)";
            executed=createNomisConfigurationFile("selectedTBCodesAddedToDIFileUploadTable",0,query);
            query="alter table DATAIMPORTFILEUPLOAD add COLUMN LASTPROCESSEDTABLEINDEX NUMERIC(3) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("lastProcesssedIndexAddedToDIFileUploadTable",0,query);
            query="alter table DATAIMPORTFILEUPLOAD add COLUMN IMPORTOPTIONS VARCHAR(1000)";
            executed=createNomisConfigurationFile("importOptionsAddedToDIFileUploadTable",0,query);
            //System.err.println("HIV status changed in changeHivStatusFieldSizeInHouseholdEnrollmentTable()");                
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean createCustomIndicatorsReportTable()
    {
        boolean executed=false;
        try
        {
            if(!tableTruelyExists("CUSTOMINDICATORSREPORT"))
            {
                String query="CREATE TABLE CUSTOMINDICATORSREPORT (RECORDID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1) NOT NULL PRIMARY KEY, STATE VARCHAR(100), "
                        + "LGA VARCHAR(100) NOT NULL, CBO VARCHAR(100) NOT NULL,INDICATORNAME VARCHAR(200) NOT NULL,MERCODE VARCHAR(20) NOT NULL,"
                        + "OTHERDISAGGREGATION VARCHAR(50) NOT NULL,PERIOD VARCHAR(20) NOT NULL,"
                        + "MLESSTHANONE NUMERIC(11) NOT NULL DEFAULT 0,M1TO4 NUMERIC(11) NOT NULL DEFAULT 0,M5TO9 NUMERIC(11) NOT NULL DEFAULT 0,M10TO14 NUMERIC(11) NOT NULL DEFAULT 0,"
                        + "M15TO17 NUMERIC(11) NOT NULL DEFAULT 0,M18TO24 NUMERIC(11) NOT NULL DEFAULT 0,M25PLUS NUMERIC(11) NOT NULL DEFAULT 0,"
                        + "FLESSTHANONE NUMERIC(11) NOT NULL DEFAULT 0,F1TO4 NUMERIC(11) NOT NULL DEFAULT 0,F5TO9 NUMERIC(11) NOT NULL DEFAULT 0,F10TO14 NUMERIC(11) NOT NULL DEFAULT 0,"
                        + "F15TO17 NUMERIC(11) NOT NULL DEFAULT 0,F18TO24 NUMERIC(11) NOT NULL DEFAULT 0,F25PLUS NUMERIC(11) NOT NULL DEFAULT 0,OVC_SERV NUMERIC(11) NOT NULL DEFAULT 0,"
                        + "DATECREATED DATE NOT NULL DEFAULT '1900-01-01',"
                        + "USERNAME VARCHAR(100) NOT NULL)";
                        
                        String processName="customIndicatorReportTable";
                        executed=createNomisConfigurationFile(processName,0,query);
                        BackgroundProcessManager bpm=new BackgroundProcessManager();
                        bpm.markBackgroundProcessAsExecuted(processName,0);
                        
                        util.executeSQLupdate("create index idx_cindstate on CUSTOMINDICATORSREPORT(STATE)");
                        util.executeSQLupdate("create index idx_cindlga on CUSTOMINDICATORSREPORT(LGA)");
                        util.executeSQLupdate("create index idx_cindcbo on CUSTOMINDICATORSREPORT(CBO)");
                        util.executeSQLupdate("create index idx_cindname on CUSTOMINDICATORSREPORT(INDICATORNAME)");
                        util.executeSQLupdate("create index idx_cindothdis on CUSTOMINDICATORSREPORT(OTHERDISAGGREGATION)");
                        util.executeSQLupdate("create index idx_cindprd on CUSTOMINDICATORSREPORT(PERIOD)");
                        
                                    
            }
            String query="alter table CUSTOMINDICATORSREPORT add COLUMN partnerCode VARCHAR(25)";
            executed=createNomisConfigurationFile("partnerCodeAddedToCustomIndicatorReportTable",0,query);
            query="alter table CUSTOMINDICATORSREPORT add COLUMN MALETOTAL NUMERIC(11) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("mtotalAddedToCustomIndicatorReportTable",0,query);
            query="alter table CUSTOMINDICATORSREPORT add COLUMN FEMALETOTAL NUMERIC(11) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("ftotalAddedToCustomIndicatorReportTable",0,query);
            query="alter table CUSTOMINDICATORSREPORT add COLUMN GRANDTOTAL NUMERIC(11) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("gtotalAddedToCustomIndicatorReportTable",0,query);
            createNomisConfigurationFile("index idx_cindptcode",0,"create index idx_cindptcode on CUSTOMINDICATORSREPORT(PARTNERCODE)");
            createNomisConfigurationFile("index idx_cindmercode",0,"create index idx_cindmercode on CUSTOMINDICATORSREPORT(MERCODE)");
            //else
            //bpm.markBackgroundProcessAsExecuted("processMonitorTable");
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating CUSTOMINDICATORSREPORT table "+ex.getMessage());
            return false;
        }
        return executed;
    }
    /*public boolean createCustomIndicatorsReportTable()
    {
        boolean executed=false;
        try
        {
            if(!tableTruelyExists("CUSTOMINDICATORSREPORT"))
            {
                String query="CREATE TABLE CUSTOMINDICATORSREPORT (RECORDID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1) NOT NULL PRIMARY KEY, STATE VARCHAR(100), "
                        + "LGA VARCHAR(100) NOT NULL, CBO VARCHAR(100) NOT NULL,INDICATORNAME VARCHAR(200) NOT NULL,SEX VARCHAR(6) NOT NULL,AGECATEGORY VARCHAR(10) NOT NULL,"
                        + "OTHERDISAGGREGATION VARCHAR(50) NOT NULL,PERIOD VARCHAR(20) NOT NULL,"
                        + "DVALUE NUMERIC(11) NOT NULL DEFAULT 0,DATECREATED DATE NOT NULL DEFAULT '1900-01-01',USERNAME VARCHAR(100) NOT NULL)";
                        util.executeSQLupdate(query);
                        util.executeSQLupdate("create index idx_cindstate on CUSTOMINDICATORSREPORT(STATE)");
                        util.executeSQLupdate("create index idx_cindlga on CUSTOMINDICATORSREPORT(LGA)");
                        util.executeSQLupdate("create index idx_cindcbo on CUSTOMINDICATORSREPORT(CBO)");
                        util.executeSQLupdate("create index idx_cindname on CUSTOMINDICATORSREPORT(INDICATORNAME)");
                        util.executeSQLupdate("create index idx_cindsex on CUSTOMINDICATORSREPORT(SEX)");
                        util.executeSQLupdate("create index idx_cindagecat on CUSTOMINDICATORSREPORT(AGECATEGORY)");
                        util.executeSQLupdate("create index idx_cindothdis on CUSTOMINDICATORSREPORT(OTHERDISAGGREGATION)");
                        util.executeSQLupdate("create index idx_cindprd on CUSTOMINDICATORSREPORT(PERIOD)");
                        String processName="customIndicatorReportTable";
                        BackgroundProcessManager bpm=new BackgroundProcessManager();
                        bpm.markBackgroundProcessAsExecuted(processName);
                                    
            }
            //else
            //bpm.markBackgroundProcessAsExecuted("processMonitorTable");
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating CUSTOMINDICATORSREPORT table "+ex.getMessage());
            return false;
        }
        return executed;
    }*/
    public boolean createProcessMonitorTable()
    {
        boolean executed=false;
        try
        {
            if(!tableTruelyExists("PROCESSMONITOR"))
            {
                String query="CREATE TABLE PROCESSMONITOR (PROCESSID VARCHAR(11) NOT NULL PRIMARY KEY, PROCESSSNAME VARCHAR(50), "
                        + "PROCESSSTATUS NUMERIC(11) NOT NULL DEFAULT 0, AUTOGENERATED NUMERIC(1) NOT NULL DEFAULT 0,REPEATACTION NUMERIC(1) NOT NULL DEFAULT 0,LASTMODIFIED DATE NOT NULL DEFAULT '1900-01-01')";
                        util.executeSQLupdate(query);
                        util.executeSQLupdate("create index idx_procName on PROCESSMONITOR(PROCESSSNAME)");
                        String processName="processMonitorTable";
                        BackgroundProcessManager bpm=new BackgroundProcessManager();
                        //if(!bpm.backgroundProcessExecuted(processName))
                        bpm.markBackgroundProcessAsExecuted(processName,0);
                    //executed=createNomisConfigurationFile("processMonitorTable",0,query);   
                
            }
            //else
            //bpm.markBackgroundProcessAsExecuted("processMonitorTable");
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating PROCESSMONITOR table "+ex.getMessage());
            return false;
        }
        return executed;
    }
    public boolean addRevisedFieldsToHouseholdFollowupTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column PROTECTION NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("protectionAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column CHILDEDUCATIONLEVEL NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("childEducationLevelAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add COLUMN HIVSTATUS VARCHAR(8) NOT NULL DEFAULT 'unknown'";
            executed=createNomisConfigurationFile("hivStatusAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add COLUMN ENROLLEDONART VARCHAR(3)";
            executed=createNomisConfigurationFile("onARTAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add COLUMN FACILITYID VARCHAR(12)";
            executed=createNomisConfigurationFile("facilityIdAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column HHEDUCATIONLEVEL NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhEducationLevelAddedToHhfaTable",0,query);
            util.executeSQLupdate("create index idx_hhfahivstatus on HOUSEHOLDFOLLOWUPASSESSMENT(HIVSTATUS)");
            util.executeSQLupdate("create index idx_hhfaonart on HOUSEHOLDFOLLOWUPASSESSMENT(ENROLLEDONART)");
            util.executeSQLupdate("create index idx_hhfafacid on HOUSEHOLDFOLLOWUPASSESSMENT(FACILITYID)");
            util.executeSQLupdate("create index idx_hhfaprot on HOUSEHOLDFOLLOWUPASSESSMENT(PROTECTION)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addRevisedFieldsToHheTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HouseholdEnrollment add column PROTECTION NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("protectionAddedToHheTable",0,query);
            query="alter table HouseholdEnrollment add column CHILDEDUCATIONLEVEL NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("childEducationLevelAddedToHheTable",0,query);
            query="alter table HouseholdEnrollment add column HHEDUCATIONLEVEL NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhEducationLevelAddedToHheTable",0,query);
            util.executeSQLupdate("create index idx_hhehivstatus on HouseholdEnrollment(HIVSTATUS)");
            util.executeSQLupdate("create index idx_hhefacid on HouseholdEnrollment(FACILITYID)");
            util.executeSQLupdate("create index idx_hheprot on HouseholdEnrollment(PROTECTION)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addEnrollmentIdsToBeneficiariesTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HouseholdEnrollment add column hhEnrollmentId VARCHAR(25)";
            executed=createNomisConfigurationFile("hhEnrollmentIdAddedToHheTable",0,query);
            query="alter table CaregiverInfo add column cgEnrollmentId VARCHAR(25)";
            executed=createNomisConfigurationFile("cgEnrollmentIdAddedToCgiverTable",0,query);
            query="alter table Enrollment add column vcEnrollmentId VARCHAR(25)";
            executed=createNomisConfigurationFile("vcEnrollmentIdAddedToEnrollmentTable",0,query);
            util.executeSQLupdate("create index idx_hheid on HouseholdEnrollment(hhEnrollmentId)");
            util.executeSQLupdate("create index idx_cgeid on CaregiverInfo(cgEnrollmentId)");
            util.executeSQLupdate("create index idx_vceid on Enrollment(vcEnrollmentId)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addAutogeneratedToLgaTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table LGA add column AUTOGENERATED VARCHAR(5) NOT NULL DEFAULT 'false'";
            executed=createNomisConfigurationFile("autogeneratedAddedToLGATable",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addVulnerabilityAssessmentFieldsToHouseholdFollowupTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column HHHEADSHIP NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhdAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column HEALTH NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("healthAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column EDUCATIONLEVEL NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("eduAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column SHELTERANDHOUSING NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("sheltAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column FOODSECURITYANDNUTRITION NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("fsecAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column MEANSOFLIVELIHOOD NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("livelihoodAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column HHINCOME NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhIncomeAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column VULNERABILITYSCORE NUMERIC(3) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("vulscoreAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column HHVAID INTEGER NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhvaIdAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column NAMEOFASSESSOR VARCHAR(50) ";
            executed=createNomisConfigurationFile("assessorAddedToHhfaTable",0,query);
            query="alter table HOUSEHOLDFOLLOWUPASSESSMENT add column DESIGNATIONOFASSESSOR VARCHAR(50) ";
            executed=createNomisConfigurationFile("desigAddedToHhfaTable",0,query);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addVulnerabilityAssessmentFieldsToHheTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HOUSEHOLDENROLLMENT add column HHHEADSHIP NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhdAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column HEALTH NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("healthAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column EDUCATIONLEVEL NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("eduAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column SHELTERANDHOUSING NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("sheltAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column FOODSECURITYANDNUTRITION NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("fsecAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column MEANSOFLIVELIHOOD NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("livelihoodAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column HHINCOME NUMERIC(1) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhIncomeAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column VULNERABILITYSCORE NUMERIC(3) NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("vulscoreAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column HHVAID INTEGER NOT NULL DEFAULT 0";
            executed=createNomisConfigurationFile("hhvaIdAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column NAMEOFASSESSOR VARCHAR(50) ";
            executed=createNomisConfigurationFile("assessorAddedToHHETable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column DESIGNATIONOFASSESSOR VARCHAR(50) ";
            executed=createNomisConfigurationFile("desigAddedToHHETable",0,query);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addProviderPhoneToCareAndSupportChecklistTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table CAREANDSUPPORTCHECKLIST add column PROVIDERPHONE VARCHAR(25)";
            executed=createNomisConfigurationFile("providerPhoneAddedToCandSTable",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }//CREATE TABLE OPTIONSMANAGER (OPTIONID VARCHAR(11) NOT NULL, "VALUE" VARCHAR(200), LASTMODIFIED DATE NOT NULL, PRIMARY KEY (OPTIONID));
    public boolean createOptionsManagerTable()
    {
        try
        {
            if(!tableExists("OPTIONSMANAGER"))
            {
                String query="CREATE TABLE OPTIONSMANAGER (OPTIONID VARCHAR(11) NOT NULL PRIMARY KEY, DVALUE VARCHAR(200), "
                        + "LASTMODIFIED DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("optionsManagerTable",0,query);   
            }   
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating OPTIONSMANAGER table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createOvcSchoolStatusTable()
    {
        try
        {
            if(!tableTruelyExists("CHILDSCHOOLSTATUS"))
            {
                String query="CREATE TABLE CHILDSCHOOLSTATUS (OVCID VARCHAR(25) NOT NULL PRIMARY KEY, OVCINSCHOOL VARCHAR(3) NOT NULL, CURRENTSCHOOLNAME VARCHAR(100),CURRENTCLASS VARCHAR(25),"
                        + "LASTPOINTOFUPDATE VARCHAR(1) NOT NULL,DATEOFASSESSMENT DATE NOT NULL, LASTMODIFIEDDATE DATE NOT NULL, MARKEDFORDELETE NUMERIC(1) NOT NULL DEFAULT 0,RECORDEDBY VARCHAR(50))";
                boolean executed=createNomisConfigurationFile("childSchoolStatusTable",0,query);
                //if(executed)
                //{
                    //createNomisConfigurationFile("idx_ovcinsch",0,"create index idx_ovcinsch on CHILDSCHOOLSTATUS(OVCINSCHOOL)");
                    //createNomisConfigurationFile("idx_sadoa",0,"create index idx_cinscdoa on CHILDSCHOOLSTATUS(DATEOFASSESSMENT)");
                    //createNomisConfigurationFile("idx_salmd",0,"create index idx_cinsclmd on CHILDSCHOOLSTATUS(LASTMODIFIEDDATE)");
                    //util.executeSQLupdate("create index idx_ovcinsch on CHILDSCHOOLSTATUS(OVCINSCHOOL)");
                    //util.executeSQLupdate("create index idx_sadoa on CHILDSCHOOLSTATUS(DATEOFASSESSMENT)");
                    //util.executeSQLupdate("create index idx_salmd on CHILDSCHOOLSTATUS(LASTMODIFIEDDATE)");
                //}
            }
            
                createNomisConfigurationFile("idx_ovcinsch",0,"create index idx_ovcinsch on CHILDSCHOOLSTATUS(OVCINSCHOOL)");
                createNomisConfigurationFile("idx_cinsccdoa",0,"create index idx_cinsccdoa on CHILDSCHOOLSTATUS(DATEOFASSESSMENT)");
                createNomisConfigurationFile("idx_cinsclmd",0,"create index idx_cinsclmd on CHILDSCHOOLSTATUS(LASTMODIFIEDDATE)");
       
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating CHILDSCHOOLSTATUS table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createSchAttendanceTrackerTable()
    {
        try
        {
            if(!tableTruelyExists("SCHOOLATTENDANCETRACKER"))
            {
                String query="CREATE TABLE SCHOOLATTENDANCETRACKER (RECORDID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1) NOT NULL PRIMARY KEY, "
                        + "OVCID VARCHAR(25) NOT NULL, CHILDMISSSCHOOL VARCHAR(3) NOT NULL, REASONSFORMISSINGSCHOOL LONG VARCHAR, "
                        + "DATEOFASSESSMENT DATE NOT NULL, LASTMODIFIEDDATE DATE NOT NULL, RECORDEDBY VARCHAR(50))";
                boolean executed=createNomisConfigurationFile("schAttendTableTracker",0,query);
                //if(executed)
                //{
                    //util.executeSQLupdate("create index idx_saovdid on SCHOOLATTENDANCETRACKER(OVCID)");
                    //util.executeSQLupdate("create index idx_sacms on SCHOOLATTENDANCETRACKER(CHILDMISSSCHOOL)");
                    //util.executeSQLupdate("create index idx_sadoa on SCHOOLATTENDANCETRACKER(DATEOFASSESSMENT)");
                    //util.executeSQLupdate("create index idx_salmd on SCHOOLATTENDANCETRACKER(LASTMODIFIEDDATE)");
               //}
                //SchoolAttendanceManager.moveSchoolAttendanceTrackerRecordsFromCaregiverExpenditureRecord();
            }
            
                createNomisConfigurationFile("idx_saovcid",0,"create index idx_saovcid on SCHOOLATTENDANCETRACKER(OVCID)");
                createNomisConfigurationFile("idx_sacms",0,"create index idx_sacms on SCHOOLATTENDANCETRACKER(CHILDMISSSCHOOL)");
                createNomisConfigurationFile("idx_sadoa",0,"create index idx_sadoa on SCHOOLATTENDANCETRACKER(DATEOFASSESSMENT)");
                createNomisConfigurationFile("idx_salmd",0,"create index idx_salmd on SCHOOLATTENDANCETRACKER(LASTMODIFIEDDATE)");
                        
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating SCHOOLATTENDANCETRACKER table "+ex.getMessage());
            return false;
        }
        return true;
    }
    /*public boolean createOvcSchAttendanceTable()
    {
        try
        {
            if(!tableExists("OVCSCHOOLATTENDANCE"))
            {
                String query="CREATE TABLE OVCSCHOOLATTENDANCE (OVCID VARCHAR(25) NOT NULL PRIMARY KEY, OVCINSCHOOL VARCHAR(3) NOT NULL, CURRENTSCHOOLNAME VARCHAR(100),CURRENTCLASS VARCHAR(25),"
                        + "LASTPOINTOFUPDATE VARCHAR(1) NOT NULL,DATEOFASSESSMENT DATE NOT NULL, LASTMODIFIEDDATE DATE NOT NULL, RECORDEDBY VARCHAR(50))";
                boolean executed=createNomisConfigurationFile("ovcSchAttendTable",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index idx_ovcinsch on OVCSCHOOLATTENDANCE(OVCINSCHOOL)");
                    util.executeSQLupdate("create index idx_sadoa on OVCSCHOOLATTENDANCE(DATEOFASSESSMENT)");
                    util.executeSQLupdate("create index idx_salmd on OVCSCHOOLATTENDANCE(LASTMODIFIEDDATE)");
                }
                //SchoolAttendanceManager.moveSchoolAttendanceRecordsFromCaregiverExpenditureRecord();
            }
            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating OVCSCHOOLATTENDANCE table "+ex.getMessage());
            return false;
        }
        return true;
    }*/
    public boolean createCaregiverExpenditureAndSchAttendanceTable()
    {
        try
        {
            if(!tableTruelyExists("CAREGIVEREXPENDITUREANDSCHATTENDANCE"))
            {
                String query="CREATE TABLE CAREGIVEREXPENDITUREANDSCHATTENDANCE (RECORDID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key,"
                        + "CAREGIVERID VARCHAR(25) NOT NULL, DATEOFASSESSMENT DATE DEFAULT '1900-01-01'  NOT NULL, UNEXPECTEDEXPENDITURE VARCHAR(3), ACCESSMONEY VARCHAR(3), "
                        + "SOURCEOFMONEY VARCHAR(500), URGENTHHNEEDS VARCHAR(500), SCHATTENDANCE VARCHAR(3) DEFAULT 'N/A'  NOT NULL, OVCID VARCHAR(1000), "
                        + " REASONSFORMISSINGSCHOOL VARCHAR(1000),VOLUNTEERNAME VARCHAR(50) NOT NULL, VOLUNTEERPHONE VARCHAR(25), LASTMODIFIEDDATE DATE DEFAULT '1900-01-01'  NOT NULL, "
                        + "RECORDEDBY VARCHAR(50) NOT NULL)";
                boolean executed=createNomisConfigurationFile("ceasaTable",0,query);
                
            }
            createNomisConfigurationFile("idx_ceasaovcid",0,"create index idx_ceasaovcid on CAREGIVEREXPENDITUREANDSCHATTENDANCE(OVCID)");
            createNomisConfigurationFile("idx_ceasadoa",0,"create index idx_ceasadoa on CAREGIVEREXPENDITUREANDSCHATTENDANCE(DATEOFASSESSMENT)");
            createNomisConfigurationFile("idx_ceasacgid",0,"create index idx_ceasacgid on CAREGIVEREXPENDITUREANDSCHATTENDANCE(CAREGIVERID)");
            createNomisConfigurationFile("idx_ceasalmd",0,"create index idx_ceasalmd on CAREGIVEREXPENDITUREANDSCHATTENDANCE(LASTMODIFIEDDATE)");
            createNomisConfigurationFile("idx_ceasalmd",0,"create index idx_ceasaschatt on CAREGIVEREXPENDITUREANDSCHATTENDANCE(SCHATTENDANCE)");
            createNomisConfigurationFile("idx_ceasaunnexpectedexp",0,"create index idx_ceasaunnexpectedexp on CAREGIVEREXPENDITUREANDSCHATTENDANCE(UNEXPECTEDEXPENDITURE)");
            createNomisConfigurationFile("idx_ceasaaccessmoney",0,"create index idx_ceasaaccessmoney on CAREGIVEREXPENDITUREANDSCHATTENDANCE(ACCESSMONEY)");
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating CAREGIVEREXPENDITUREANDSCHATTENDANCE table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createCareAndSupportTable()
    {
        try
        {
            if(!tableExists("CAREANDSUPPORTCHECKLIST"))
            {
                String query="CREATE TABLE CAREANDSUPPORTCHECKLIST (RECORDID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key,"
                        + " DATEOFASSESSMENT DATE NOT NULL, DATEOFHIVTEST DATE, RECEIVEDTESTRESULT VARCHAR(3), "
                        + "HIVSTATUS VARCHAR(9) NOT NULL, ENROLLEDONTREATMENT VARCHAR(3) NOT NULL DEFAULT 'N/A', DATEENROLLEDONART DATE, LASTMODIFIEDDATE DATE NOT NULL, "
                        + "TREATMENTFACILITY VARCHAR(15), VIRALLOADTESTDONE VARCHAR(3) DEFAULT 'N/A'  NOT NULL, DATEOFVIRALLOADTEST DATE, MEDICATIONPICKEDUP VARCHAR(3), "
                        + "SKIPPEDARV VARCHAR(3) DEFAULT 'N/A'  NOT NULL, REASONSPEOPLESKIPARV VARCHAR(500), TRANSPORTATIONSUPPORT VARCHAR(3) DEFAULT 'N/A'  NOT NULL, "
                        + "EXPERIENCEDSORESORRASH VARCHAR(3) DEFAULT 'N/A'  NOT NULL, UNIQUEID VARCHAR(25) NOT NULL, VOLUNTEERNAME VARCHAR(50), "
                        + "DESIGNATION VARCHAR(25), APPROVALLEVEL NUMERIC(1) NOT NULL DEFAULT 0, DELETED NUMERIC(1) NOT NULL DEFAULT 0)";
                boolean executed=createNomisConfigurationFile("candsTable",0,query);
                
                    
                
            }
            addProviderPhoneToCareAndSupportChecklistTable();
            createNomisConfigurationFile("idx_candsdob",0,"create index idx_candsdob on CAREANDSUPPORTCHECKLIST(DATEOFASSESSMENT)");
            createNomisConfigurationFile("idx_candstreatmt",0,"create index idx_candstreatmt on CAREANDSUPPORTCHECKLIST(ENROLLEDONTREATMENT)");
            createNomisConfigurationFile("idx_candslmd",0,"create index idx_candslmd on CAREANDSUPPORTCHECKLIST(LASTMODIFIEDDATE)");
            createNomisConfigurationFile("idx_candsuid",0,"create index idx_candsuid on CAREANDSUPPORTCHECKLIST(UNIQUEID)");
            createNomisConfigurationFile("idx_candshiv",0,"create index idx_candshiv on CAREANDSUPPORTCHECKLIST(HIVSTATUS)");
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating CAREANDSUPPORTCHECKLIST table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createGenderNormCohortAttendanceTable()
    {
        try
        {
            if(!tableExists("GENDERNORMCOHORTATTENDANCE"))
            {
                String query="CREATE TABLE GENDERNORMCOHORTATTENDANCE (RECORDID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key,"
                        + " CLIENTID VARCHAR(25) NOT NULL, DATEOFATTENDANCE DATE NOT NULL, LASTMODIFIEDDATE DATE NOT NULL, "
                        + "LASTMODIFIEDBY VARCHAR(50), ENDORSEDBY VARCHAR(50), SESSIONNUMBER NUMERIC(2) DEFAULT 0  NOT NULL,"
                        + " NUMBEROFSESSIONS NUMERIC(2) DEFAULT 0,TARGETGROUP VARCHAR(10))";
                boolean executed=createNomisConfigurationFile("genderNormTable",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index idx_gnclientId on GENDERNORMCOHORTATTENDANCE(CLIENTID)");
                    util.executeSQLupdate("create index idx_gndoa on GENDERNORMCOHORTATTENDANCE(DATEOFATTENDANCE)");
                    util.executeSQLupdate("create index idx_gnlmd on GENDERNORMCOHORTATTENDANCE(LASTMODIFIEDDATE)");
                    util.executeSQLupdate("create index idx_gnlmby on GENDERNORMCOHORTATTENDANCE(LASTMODIFIEDBY)");
                    util.executeSQLupdate("create index idx_tgtgrp on GENDERNORMCOHORTATTENDANCE(TARGETGROUP)");
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating GENDERNORMCOHORTATTENDANCE table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createCarePlanAchievementTable()
    {
        try
        {
            if(!tableExists("CAREPLANACHIEVEMENT"))
            {
                System.err.println("About to create CarePlanAchievementTable----");
                String query="CREATE TABLE CAREPLANACHIEVEMENT (ID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key,"
                        +"CLIENTID VARCHAR(50) NOT NULL, DATEOFASSESSMENT DATE NOT NULL, HTH_HIVKNOWLEDGE VARCHAR(3) NOT NULL,"
                        +"HTH_VCHIVRISK VARCHAR(3) NOT NULL, HTH_VCREFTESTED VARCHAR(3) NOT NULL, HTH_HIVONART VARCHAR(3) NOT NULL,"
                        +"HTH_HIVDISCLOSED VARCHAR(3) NOT NULL, HTH_VCINNEEDOFHTHSERVICES VARCHAR(3) NOT NULL, STB_HUNGRYNOFOOD VARCHAR(3) NOT NULL, "
                        +"STB_RESILIENCY VARCHAR(3) NOT NULL, SFT_VCSAD VARCHAR(3) NOT NULL, STB_CGPARTECONSERV VARCHAR(3) NOT NULL,"
                        +"STB_SOCEMOTSUPPORT VARCHAR(3) NOT NULL, SFT_VCREFERREDFORCPS VARCHAR(3) NOT NULL, "
                        + "SFT_VCSAFEFROMABUSE VARCHAR(3) NOT NULL, SFT_BIRTHCERT VARCHAR(3) NOT NULL, SFT_CGCOMPLETEDTWOMODULES VARCHAR(3) NOT NULL, "
                        + "SFT_CHILDHEADEDLINKEDTOSERVICES VARCHAR(3) NOT NULL, SCH_SCHATTENDANCE VARCHAR(3) NOT NULL, "
                        + "SCH_VCENROLLEDINSECONDARYSCH VARCHAR(3) NOT NULL, SCH_ADOLINVOCTRAINING VARCHAR(3) NOT NULL, "
                        + "SCH_EARLYCHILDCARE VARCHAR(3) NOT NULL, HTH_HIVKNOWLEDGECOMMENT VARCHAR(3), "
                        + "HTH_VCHIVRISKCOMMENT VARCHAR(3), HTH_VCREFTESTEDCOMMENT VARCHAR(3), "
                        + "HTH_HIVONARTCOMMENT VARCHAR(3), HTH_HIVDISCLOSEDCOMMENT VARCHAR(3), "
                        + "HTH_VCINNEEDOFHTHSERVICESCOMMENT VARCHAR(3), STB_HUNGRYNOFOODCOMMENT VARCHAR(3), "
                        + "STB_RESILIENCYCOMMENT VARCHAR(3), SFT_VCSADCOMMENT VARCHAR(3), STB_CGPARTECONSERVCOMMENT VARCHAR(3),"
                        + " STB_SOCEMOTSUPPORTCOMMENT VARCHAR(3), SFT_VCREFERREDFORCPSCOMMENT VARCHAR(3), "
                        + "SFT_VCSAFEFROMABUSECOMMENT VARCHAR(3), SFT_BIRTHCERTCOMMENT VARCHAR(3), SFT_CGCOMPLETEDTWOMODULESCOMMENT VARCHAR(3), "
                        + "SFT_CHILDHEADEDLINKEDTOSERVICESCOMMENT VARCHAR(3) NOT NULL, SCH_SCHATTENDANCECOMMENT VARCHAR(3), "
                        + "SCH_VCENROLLEDINSECONDARYSCHCOMMENT VARCHAR(3), SCH_ADOLINVOCTRAININGCOMMENT VARCHAR(3), "
                        + "SCH_EARLYCHILDCARECOMMENT VARCHAR(200), SCORE NUMERIC(5) DEFAULT 0  NOT NULL, GRADUATED VARCHAR(3) NOT NULL, VOLUNTEERID VARCHAR(50), "
                        + "DESIGNATION VARCHAR(50), RECORDEDBY VARCHAR(50),LASTMODIFIED DATE NOT NULL, ASSESSMENTNO NUMERIC(3) DEFAULT 0)";
                        
                boolean executed=createNomisConfigurationFile("careplanAchievementTable",0,query);
                if(executed)
                {
                    
                }
            }
            createNomisConfigurationFile("idx_clientId",0,"create index idx_clientId on CAREPLANACHIEVEMENT(CLIENTID)");
            createNomisConfigurationFile("idx_cpadateass",0,"create index idx_cpadateass on CAREPLANACHIEVEMENT(DATEOFASSESSMENT)");
            addFollowupToCarePlanAchievementTable();
            changeFieldSizeOnCarePlanAchievementTable();
            changeFieldsToNullOnCarePlanAchievementTable();
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating CAREPLANACHIEVEMENT table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean changeFieldSizeOnCarePlanAchievementTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_HIVKNOWLEDGECOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("hivCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_HIVKNOWLEDGECOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("hivCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_VCHIVRISKCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("hcriskCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_VCREFTESTEDCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("vcretestCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_HIVONARTCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("onArtCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_HIVDISCLOSEDCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("hivDiscCmtSizeCarePlanAchievementTable",0,query);
            
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_VCINNEEDOFHTHSERVICESCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("vcInnEdCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN STB_HUNGRYNOFOODCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("stbHungCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN STB_RESILIENCYCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("stbResCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_VCSADCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("sftVCSadCmtSizeCarePlanAchievementTable",0,query);
            
            query="alter table CAREPLANACHIEVEMENT alter COLUMN STB_CGPARTECONSERVCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("stbCGPCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN STB_SOCEMOTSUPPORTCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("stbSocCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_VCREFERREDFORCPSCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("sftVCRCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_VCSAFEFROMABUSECOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("sftVCSafeCmtSizeCarePlanAchievementTable",0,query);
            
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_BIRTHCERTCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("sftBIRCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_CGCOMPLETEDTWOMODULESCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("sftCGCCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_CHILDHEADEDLINKEDTOSERVICESCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("sftCHICmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SCH_SCHATTENDANCECOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("schafeCmtSizeCarePlanAchievementTable",0,query);
            
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SCH_VCENROLLEDINSECONDARYSCHCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("schVCECmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SCH_ADOLINVOCTRAININGCOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("schADOCmtSizeCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SCH_EARLYCHILDCARECOMMENT set DATA TYPE VARCHAR(200)";
            executed=createNomisConfigurationFile("schEarlyCmtSizeCarePlanAchievementTable",0,query);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    
    public boolean changeFieldsToNullOnCarePlanAchievementTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_HIVKNOWLEDGECOMMENT NULL";
            executed=createNomisConfigurationFile("hivCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_HIVKNOWLEDGECOMMENT NULL";
            executed=createNomisConfigurationFile("hivCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_VCHIVRISKCOMMENT NULL";
            executed=createNomisConfigurationFile("hcriskCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_VCREFTESTEDCOMMENT NULL";
            executed=createNomisConfigurationFile("vcretestCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_HIVONARTCOMMENT NULL";
            executed=createNomisConfigurationFile("onArtCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_HIVDISCLOSEDCOMMENT NULL";
            executed=createNomisConfigurationFile("hivDiscCmtNullCarePlanAchievementTable",0,query);
            
            query="alter table CAREPLANACHIEVEMENT alter COLUMN HTH_VCINNEEDOFHTHSERVICESCOMMENT NULL";
            executed=createNomisConfigurationFile("vcInnEdCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN STB_HUNGRYNOFOODCOMMENT NULL";
            executed=createNomisConfigurationFile("stbHungCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN STB_RESILIENCYCOMMENT NULL";
            executed=createNomisConfigurationFile("stbResCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_VCSADCOMMENT NULL";
            executed=createNomisConfigurationFile("sftVCSadCmtNullCarePlanAchievementTable",0,query);
            
            query="alter table CAREPLANACHIEVEMENT alter COLUMN STB_CGPARTECONSERVCOMMENT NULL";
            executed=createNomisConfigurationFile("stbCGPCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN STB_SOCEMOTSUPPORTCOMMENT NULL";
            executed=createNomisConfigurationFile("stbSocCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_VCREFERREDFORCPSCOMMENT NULL";
            executed=createNomisConfigurationFile("sftVCRCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_VCSAFEFROMABUSECOMMENT NULL";
            executed=createNomisConfigurationFile("sftVCSafeCmtNullCarePlanAchievementTable",0,query);
            
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_BIRTHCERTCOMMENT NULL";
            executed=createNomisConfigurationFile("sftBIRCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_CGCOMPLETEDTWOMODULESCOMMENT NULL";
            executed=createNomisConfigurationFile("sftCGCCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SFT_CHILDHEADEDLINKEDTOSERVICESCOMMENT NULL";
            executed=createNomisConfigurationFile("sftCHICmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SCH_SCHATTENDANCECOMMENT NULL";
            executed=createNomisConfigurationFile("schafeCmtNullCarePlanAchievementTable",0,query);
            
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SCH_VCENROLLEDINSECONDARYSCHCOMMENT NULL";
            executed=createNomisConfigurationFile("schVCECmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SCH_ADOLINVOCTRAININGCOMMENT NULL";
            executed=createNomisConfigurationFile("schADOCmtNullCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT alter COLUMN SCH_EARLYCHILDCARECOMMENT NULL";
            executed=createNomisConfigurationFile("schEarlyCmtNullCarePlanAchievementTable",0,query);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addFollowupToCarePlanAchievementTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table CAREPLANACHIEVEMENT add column FOLLOWUP VARCHAR(3)";
            executed=createNomisConfigurationFile("followupAddedToCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT add column COMMENT VARCHAR(500)";
            executed=createNomisConfigurationFile("commentAddedToCarePlanAchievementTable",0,query);
            query="alter table CAREPLANACHIEVEMENT add column SERVICEPROVIDERPHONE VARCHAR(15)";
            executed=createNomisConfigurationFile("servprovPhoneAddedToCarePlanAchievementTable",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean createDashboardItemTable()
    {
        try
        {
            if(!tableExists("DASHBOARDITEM"))
            {
                String query="CREATE TABLE DASHBOARDITEM (RECORDID VARCHAR(11) NOT NULL PRIMARY KEY,"
                        +"OBJECTID VARCHAR(11) NOT NULL, OBJECTTYPE VARCHAR(15) NOT NULL,"
                        +"DASHBOARDID VARCHAR(11) NOT NULL, DATECREATED DATE NOT NULL, LASTMODIFIEDDATE DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("dashboardItemTable",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index idx_dbItemId on DASHBOARDITEM(DASHBOARDID)");  
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DASHBOARDID table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createDashboardTable()
    {
        try
        {
            if(!tableExists("DASHBOARD"))
            {
                String query="CREATE TABLE DASHBOARD (DASHBOARDID VARCHAR(11) NOT NULL PRIMARY KEY, DASHBOARDNAME VARCHAR(100) NOT NULL UNIQUE,"
                        +"DASHBOARDTYPE VARCHAR(15) NOT NULL, CREATEDBY VARCHAR(25) NOT NULL, LASTMODIFIEDDATE DATE NOT NULL,"
                        +"DATECREATED DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("dashboardTable",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index idx_dashbtype on DASHBOARD(DASHBOARDTYPE)");  
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DASHBOARD table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean addCategoryToChartTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table CHART add column CATEGORY VARCHAR(25)";
            executed=createNomisConfigurationFile("categoryAddedToChartTable",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean createChartTable()
    {
        try
        {
            if(!tableExists("CHART"))
            {
                String query="CREATE TABLE CHART (CHARTID VARCHAR(11) NOT NULL PRIMARY KEY, "
                        +"CHARTNAME VARCHAR(500) NOT NULL UNIQUE, STATECODE VARCHAR(4),LGACODE VARCHAR(4),"
                        +"CBOCODE VARCHAR(4),COMMUNITYCODE VARCHAR(4),ORGUNITCODE VARCHAR(50), ORGUNITLEVEL NUMERIC(2) DEFAULT 0  NOT NULL,"
                        +"CHARTTYPE VARCHAR(15) NOT NULL, SERIES VARCHAR(25) NOT NULL, FILTER VARCHAR(25) NOT NULL, "
                        +"SELECTEDORGUNITS VARCHAR(500) NOT NULL, SELECTEDINDICATORS VARCHAR(500) NOT NULL, "
                        +"SELECTEDPERIODS VARCHAR(500) NOT NULL, PERIODTYPE VARCHAR(25) NOT NULL, STARTDATE DATE NOT NULL, "
                        +"ENDDATE DATE NOT NULL, LABELVISIBLE VARCHAR(7) NOT NULL, DATECREATED DATE NOT NULL, LASTMODIFIED DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("CHART",0,query);
                if(executed)
                {
                    addCategoryToChartTable();
                    util.executeSQLupdate("create index idx_charttype on CHART(CHARTTYPE)");  
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating CHART table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createHivRiskAssessmentChecklistTable()
    {//bloodTransfusionQuestion
        try
        {
            if(!tableTruelyExists("HIVRISKASSESSMENTCHECKLIST"))
            {
                String query="CREATE TABLE HIVRISKASSESSMENTCHECKLIST"
                +"(RECORDID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key,"
                +"OVCID VARCHAR(25) NOT NULL, DATEOFASSESSMENT DATE NOT NULL, CHILDAGEQUESTION VARCHAR(3) NOT NULL,"
                +"CHILDAGECOMMENT VARCHAR(200), CHILDTESTEDQUESTION VARCHAR(3) NOT NULL,CHILDTESTEDCOMMENT VARCHAR(200),"
                +"HIVPARENTQUESTION VARCHAR(3) NOT NULL, HIVPARENTCOMMENT VARCHAR(200), CHILDSICKQUESTION VARCHAR(3) NOT NULL,"
                +"CHILDSICKCOMMENT VARCHAR(200), SKINPROBLEMQUESTION VARCHAR(3) NOT NULL,SKINPROBLEMCOMMENT VARCHAR(200),"
                +"PARENTDECEASEDQUESTION VARCHAR(3) NOT NULL, PARENTDECEASEDCOMMENT VARCHAR(200),"
                +"CHRONICALLYILLQUESTION VARCHAR(3) NOT NULL, CHRONICALLYILLCOMMENT VARCHAR(200),SCHOOLGRADEQUESTION VARCHAR(3) NOT NULL,"
                +"SCHOOLGRADECOMMENT VARCHAR(200), SEXUALABUSEQUESTION VARCHAR(3) NOT NULL, SEXUALABUSECOMMENT VARCHAR(200),"
                +"SEXUALLYACTIVEQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A', SEXUALLYACTIVECOMMENT VARCHAR(200),"
                +"GENITALDISCHARGEQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A',GENITALDISCHARGECOMMENT VARCHAR(200),"
                +"BLOODTRANSFUSIONQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A', BLOODTRANSFUSIONCOMMENT VARCHAR(200),"
                +"MUACBMIQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A',MUACBMICOMMENT VARCHAR(200),"
                +"HIVSTATUSQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A',HIVSTATUS VARCHAR(8),"
                +"CHILDATRISKQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A', SERVICEPROVIDERNAME VARCHAR(50),"
                +"DESIGNATION VARCHAR(25), SERVICEPROVIDERPHONE VARCHAR(15), LASTMODIFIEDDATE DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("HIVRISKASSESSMENTCHECKLIST",0,query);
                
                
                
            }
            else
            {
                addFieldsToHivRiskAssessmentChecklistTable();
            }
            createNomisConfigurationFile("idx_hivclovcid",0,"create index idx_hivclovcid on HIVRISKASSESSMENTCHECKLIST(OVCID)");
            createNomisConfigurationFile("idx_hivcldoa",0,"create index idx_hivcldoa on HIVRISKASSESSMENTCHECKLIST(DATEOFASSESSMENT)");
            createNomisConfigurationFile("idx_hrachivstatus",0,"create index idx_hrachivstatus on HIVRISKASSESSMENTCHECKLIST(HIVSTATUS)");
        }      
        catch(Exception ex)
        {
            System.err.println("Error creating HIVRISKASSESSMENTCHECKLIST table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean addFieldsToHivRiskAssessmentChecklistTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HIVRISKASSESSMENTCHECKLIST add column BLOODTRANSFUSIONQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A'";
            executed=createNomisConfigurationFile("btfQstAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column BLOODTRANSFUSIONCOMMENT VARCHAR(200)";
            executed=createNomisConfigurationFile("btfcmntAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column MUACBMIQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A'";
            executed=createNomisConfigurationFile("muacbmiQstAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column MUACBMICOMMENT VARCHAR(200)";
            executed=createNomisConfigurationFile("muacbmicmtAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column HIVSTATUSQUESTION VARCHAR(3) NOT NULL DEFAULT 'N/A'";
            executed=createNomisConfigurationFile("hivStatusQstAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column HIVSTATUS VARCHAR(8)";
            executed=createNomisConfigurationFile("hivStatusAddedTohracTable",0,query);
            
            query="alter table HIVRISKASSESSMENTCHECKLIST add column tbOrNutritionQuestion VARCHAR(3)";
            executed=createNomisConfigurationFile("tbnutQstAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column tbOrNutritionComment VARCHAR(200)";
            executed=createNomisConfigurationFile("tbnutcmntAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column parentKnowHivStatusQuestion VARCHAR(3)";
            executed=createNomisConfigurationFile("parknowstatusQstAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column parentKnowHivStatusComment VARCHAR(200)";
            executed=createNomisConfigurationFile("parknowstatuscmtAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column childHeadedHhQuestion VARCHAR(3)";
            executed=createNomisConfigurationFile("chdheadhhQstAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column childHeadedHhComment VARCHAR(8)";
            executed=createNomisConfigurationFile("chdheadhhcmtAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column teenMotherQuestion VARCHAR(3)";
            executed=createNomisConfigurationFile("teenmthQstAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column teenMotherComment VARCHAR(8)";
            executed=createNomisConfigurationFile("teenmthcmtAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column diverseSexQuestion VARCHAR(3)";
            executed=createNomisConfigurationFile("diverseSexQstAddedTohracTable",0,query);
            query="alter table HIVRISKASSESSMENTCHECKLIST add column diverseSexComment VARCHAR(8)";
            executed=createNomisConfigurationFile("diverseSexcmtAddedTohracTable",0,query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean createDxCategoryCombinationTable()
    {
        try
        {
            if(!tableTruelyExists("DxCATEGORYCOMBINATION"))
            {
                String query="CREATE TABLE DxCATEGORYCOMBINATION"
                + "(CATCOMBOID VARCHAR(12) NOT NULL PRIMARY KEY, CATCOMBONAME VARCHAR(1000) NOT NULL, INSTANCENAME VARCHAR(15) NOT NULL, LASTMODIFIEDDATE DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("DxCATEGORYCOMBINATION",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index idx_dxccname on DxCATEGORYCOMBINATION(CATCOMBONAME)");
                    util.executeSQLupdate("create index idx_dxccinstname on DxCATEGORYCOMBINATION(INSTANCENAME)");
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DxCATEGORYCOMBINATION table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createDxDataElementTable()
    {
        try
        {
            if(!tableTruelyExists("DxDATAELEMENT"))
            {
                String query="CREATE TABLE DxDATAELEMENT"
                + "(DATAELEMENTID VARCHAR(12) NOT NULL PRIMARY KEY, DATAELEMENTNAME VARCHAR(1000) NOT NULL, INSTANCENAME VARCHAR(12) NOT NULL, LASTMODIFIEDDATE DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("DxDATAELEMENT",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index idx_dxdename on DxDATAELEMENT(DATAELEMENTNAME)");
                    util.executeSQLupdate("create index idx_dxdeinstname on DxDATAELEMENT(INSTANCENAME)");
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DxDATAELEMENT table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createDxOrganizationUnitTable()
    {
        try
        {
            if(!tableTruelyExists("DxORGANIZATIONUNIT"))
            {
                String query="CREATE TABLE DxORGANIZATIONUNIT"
                + "(ORGUNITID VARCHAR(12) NOT NULL PRIMARY KEY, PARENTID VARCHAR(12), ORGUNITNAME VARCHAR(100) NOT NULL, INSTANCENAME VARCHAR(12) NOT NULL, OULEVEL DECIMAL(2) DEFAULT 0  NOT NULL, LASTMODIFIEDDATE DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("DxORGANIZATIONUNIT",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index idx_ouinstname on DxORGANIZATIONUNIT(INSTANCENAME)");
                    util.executeSQLupdate("create index idx_dxouname on DxORGANIZATIONUNIT(ORGUNITNAME)");
                    util.executeSQLupdate("create index idx_dxoupid on DxORGANIZATIONUNIT(PARENTID)");
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DxORGANIZATIONUNIT table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createDhisInstanceTable()
    {
        try
        {
            if(!tableTruelyExists("DHISINSTANCE"))
            {
                String query="CREATE TABLE DHISINSTANCE"
                + "(ID VARCHAR(12) NOT NULL PRIMARY KEY, INSTANCENAME VARCHAR(25) NOT NULL, LASTMODIFIED DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("DHISINSTANCE",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index idx_instname on DHISINSTANCE(INSTANCENAME)");
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DHISINSTANCE table "+ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean addFieldsToNomisUserTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table NOMISUSER add column USERGROUPID VARCHAR(100)";
            executed=createNomisConfigurationFile("usergrpIdAddedToNomisUserTable",0,query);
            if(executed)
            util.executeSQLupdate("create index idx_usergrpid on NOMISUSER(USERGROUPID)");
            query="alter table NOMISUSER add column PARTNERCODE VARCHAR(1000)";
            executed=createNomisConfigurationFile("partnerCodeAddedToNomisUser",0,query);
            query="alter table NOMISUSER add column ORGUNITGROUPID VARCHAR(5000)";
            executed=createNomisConfigurationFile("ougrpIdAddedToNomisUserTable",0,query);
            if(executed)
            util.executeSQLupdate("create index idx_ougrpid on NOMISUSER(ORGUNITGROUPID)");   
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
        
    public boolean createUserGroupTable()
    {
        try
        {
            if(!tableTruelyExists("USERGROUP"))
            {
                String query="CREATE TABLE USERGROUP"
                + "(GROUPID VARCHAR(12) NOT NULL PRIMARY KEY, GROUPNAME VARCHAR(100) NOT NULL,DESCRIPTION VARCHAR(300),"
                + " ORGUNITGROUP VARCHAR(500) NOT NULL,DATECREATED DATE NOT NULL,DATEMODIFIED DATE NOT NULL)";
               
                boolean executed=createNomisConfigurationFile("USERGROUP",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index index_usg_gname on USERGROUP(GROUPNAME)");
                    util.executeSQLupdate("create index index_usg_ougrp on USERGROUP(ORGUNITGROUP)");
                }
            }
            addGroupTypeToUserGroupTable();
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating USERGROUP table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean addGroupTypeToUserGroupTable()
    {
        try
        {
            if(tableTruelyExists("USERGROUP"))
            {
                String query="ALTER TABLE USERGROUP ADD COLUMN GROUPTYPE VARCHAR(15) NOT NULL DEFAULT 'cbo' ";
               
                boolean executed=createNomisConfigurationFile("USERGROUPTYPE",0,query);
                
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error adding groupType to USERGROUP table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createReferralDirectoryTable()
    {
        try
        {
            if(!tableTruelyExists("REFERRALDIRECTORY"))
            {
                String query="CREATE TABLE REFERRALDIRECTORY"
                + "(FACILITYID VARCHAR(12) NOT NULL PRIMARY KEY, FACILITYNAME VARCHAR(50) NOT NULL,"
                + " ADDRESS VARCHAR(1000),TYPEOFORGANIZATION VARCHAR(50), NAMEOFCONTACT VARCHAR(50),"
                + " CONTACTNUMBER VARCHAR(15),CONTACTEMAIL VARCHAR(50), DATEMODIFIED DATE NOT NULL,WARD VARCHAR(25), COMMUNITY VARCHAR(15),"
                + " LATITUDE VARCHAR(15),LONGITUDE VARCHAR(15))";
               
                boolean executed=createNomisConfigurationFile("REFERRALDIRECTORY",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index index_rd_facname on REFERRALDIRECTORY(FACILITYNAME)");
                    util.executeSQLupdate("create index index_rd_factype on REFERRALDIRECTORY(TYPEOFORGANIZATION)");
                    util.executeSQLupdate("create index index_rd_dm on REFERRALDIRECTORY(DATEMODIFIED)");
                }
            }
            addContactEmailToReferralDirrectory();
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating REFERRALDIRECTORY table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean addContactEmailToReferralDirrectory()
    {
        boolean executed=false;
        if(tableExists("REFERRALDIRECTORY"))
        {
            String query="alter table REFERRALDIRECTORY add column CONTACTEMAIL VARCHAR(50)";
            executed=createNomisConfigurationFile("contactEmailAddedToRefDirectoryTable",0,query);
        }
        return executed;
    }
    public boolean createOrganizationUnitHierarchyTable()
    {
        try
        {
            if(!tableTruelyExists("ORGANIZATIONUNITHIERARCHY"))
            {
                String query="CREATE TABLE ORGANIZATIONUNITHIERARCHY"
                + "(HIERARCHYID VARCHAR(12) NOT NULL PRIMARY KEY, HIERARCHYNAME VARCHAR(50) NOT NULL,"
                + " DATEMODIFIED DATE NOT NULL, ORGANIZATIONUNITLEVEL NUMERIC(2) NOT NULL)";
               
                boolean executed=createNomisConfigurationFile("ORGANIZATIONUNITHIERARCHY",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index index_ouh_hname on ORGANIZATIONUNITHIERARCHY(HIERARCHYNAME)");
                    util.executeSQLupdate("create index index_ouh_level on ORGANIZATIONUNITHIERARCHY(ORGANIZATIONUNITLEVEL)");
                    util.executeSQLupdate("create index index_ouh_dm on ORGANIZATIONUNITHIERARCHY(DATEMODIFIED)");
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating ORGANIZATIONUNITHIERARCHY table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createSiteTransitionTable()
    {
        try
        {
            if(!tableTruelyExists("SITETRANSITION"))
            {
                String query="CREATE TABLE SITETRANSITION"
                + "(SITECODE VARCHAR(25) NOT NULL PRIMARY KEY, DATEOFTRANSITION DATE NOT NULL,"
                + " DATEMODIFIED DATE NOT NULL, ORGANIZATIONSITETRANSITIONEDTO VARCHAR(100) NOT NULL,"
                + " RECORDEDBY VARCHAR(50))";
                boolean executed=createNomisConfigurationFile("SITETRANSITION",0,query);
                if(executed)
                {
                    util.executeSQLupdate("create index index_st_dot on SITETRANSITION(DATEOFTRANSITION)");
                    util.executeSQLupdate("create index index_st_dm on SITETRANSITION(DATEMODIFIED)");
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating SITETRANSITION table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createBirthRegistrationTable()
    {
        try
        {
            if(!tableTruelyExists("BIRTHREGISTRATION"))
            {
                /*String query="CREATE TABLE BIRTHREGISTRATION "
                        + "(RECORDID VARCHAR(12) NOT NULL PRIMARY KEY, CLIENTID VARCHAR(25) NOT NULL,"
                        + " BIRTHREGISTRATIONSTATUS VARCHAR(15) NOT NULL, RECORDSTATUS VARCHAR(10) NOT NULL,"
                        + " DATEOFREGISTRATION DATE, DATEMODIFIED DATE, CLIENTTYPE VARCHAR(12) NOT NULL,"
                        + " POINTOFUPDATE VARCHAR(25))";*/
                boolean executed=createNomisConfigurationFile("BIRTHREGISTRATION",0,getBirthRegistrationTableQuery());
                getBirthRegistrationTableIndexes();
                if(executed)
                {
                    
                    /*util.executeSQLupdate("create index index_br_clienttype on BIRTHREGISTRATION(clienttype)");
                    util.executeSQLupdate("create index index_br_dateofreg on BIRTHREGISTRATION(DATEOFREGISTRATION)");
                    util.executeSQLupdate("create index index_br_datemodified on BIRTHREGISTRATION(DATEMODIFIED)");
                    util.executeSQLupdate("create index index_br_clientid on BIRTHREGISTRATION(CLIENTID)");
                    util.executeSQLupdate("create index index_br_birthregstatus on BIRTHREGISTRATION(BIRTHREGISTRATIONSTATUS)");
                    util.executeSQLupdate("create index index_br_recordstatus on BIRTHREGISTRATION(RECORDSTATUS)");
                    util.executeSQLupdate("create index index_br_pointofupdate on BIRTHREGISTRATION(POINTOFUPDATE)");*/
                }
            }
            getBirthRegistrationTableIndexes();
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating BIRTHREGISTRATION table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public void dropAndCreateBirthRegistrationTable()
    {
        try
        {
            if(tableTruelyExists("BIRTHREGISTRATION"))
            {
                util.executeSQLupdate("drop table BIRTHREGISTRATION");
                util.executeSQLupdate(getBirthRegistrationTableQuery());
                getBirthRegistrationTableIndexes();
                System.err.println("BIRTHREGISTRATION table dropped and recreated in DatabaseUtilities"); 
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating BIRTHREGISTRATION table "+ex.getMessage()); 
        }
    }
    private String getBirthRegistrationTableQuery()
    {
        String query="CREATE TABLE BIRTHREGISTRATION "
                        + "(RECORDID VARCHAR(12) NOT NULL PRIMARY KEY, CLIENTID VARCHAR(25) NOT NULL,"
                        + " BIRTHREGISTRATIONSTATUS VARCHAR(15) NOT NULL, RECORDSTATUS VARCHAR(10) NOT NULL,"
                        + " DATEOFREGISTRATION DATE NOT NULL, DATEMODIFIED DATE NOT NULL, CLIENTTYPE VARCHAR(12) NOT NULL,"
                        + " POINTOFUPDATE VARCHAR(25) NOT NULL)";
        return query;
    }
    private void getBirthRegistrationTableIndexes()
    {
        try
        {
            util.executeSQLupdate("create index index_br_clienttype on BIRTHREGISTRATION(clienttype)");
            util.executeSQLupdate("create index index_br_dateofreg on BIRTHREGISTRATION(DATEOFREGISTRATION)");
            util.executeSQLupdate("create index index_br_datemodified on BIRTHREGISTRATION(DATEMODIFIED)");
            util.executeSQLupdate("create index index_br_clientid on BIRTHREGISTRATION(CLIENTID)");
            util.executeSQLupdate("create index index_br_birthregstatus on BIRTHREGISTRATION(BIRTHREGISTRATIONSTATUS)");
            util.executeSQLupdate("create index index_br_recordstatus on BIRTHREGISTRATION(RECORDSTATUS)");
            util.executeSQLupdate("create index index_br_pointofupdate on BIRTHREGISTRATION(POINTOFUPDATE)");
            
            /*createNomisConfigurationFile("index_br_clienttype",0,"create index index_br_clienttype on BIRTHREGISTRATION(clienttype)");
            createNomisConfigurationFile("index_br_dateofreg",0,"create index index_br_dateofreg on BIRTHREGISTRATION(DATEOFREGISTRATION)");
            createNomisConfigurationFile("index_br_datemodified",0,"create index index_br_datemodified on BIRTHREGISTRATION(DATEMODIFIED)");
            createNomisConfigurationFile("index_br_clientid",0,"create index index_br_clientid on BIRTHREGISTRATION(CLIENTID)");
            createNomisConfigurationFile("index_br_birthregstatus",0,"create index index_br_birthregstatus on BIRTHREGISTRATION(BIRTHREGISTRATIONSTATUS)");
            createNomisConfigurationFile("index_br_recordstatus",0,"create index index_br_recordstatus on BIRTHREGISTRATION(RECORDSTATUS)");
            createNomisConfigurationFile("index_br_pointofupdate",0,"create index index_br_pointofupdate on BIRTHREGISTRATION(POINTOFUPDATE)");*/
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public boolean createGraduationChecklistTable()
    {
        try
        {
            if(!tableTruelyExists("GRADUATIONCHECKLIST"))
            {
                String query="CREATE TABLE GRADUATIONCHECKLIST"
                + "(ID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key,"
                + " clientId VARCHAR(25) NOT NULL, dateOfAssessment DATE NOT NULL, DATEMODIFIED DATE NOT NULL, CLIENTTYPE VARCHAR(12) NOT NULL,"
                + " health VARCHAR(4) NOT NULL,safety VARCHAR(4) NOT NULL,school VARCHAR(4) NOT NULL,"
                +"stability VARCHAR(4) NOT NULL,vulnerability VARCHAR(4) NOT NULL,graduated VARCHAR(25),volunteerId VARCHAR(25),"
                +"recordedby VARCHAR(50) NOT NULL, gclscore NUMERIC(2) not null default 0)";
                boolean executed=createNomisConfigurationFile("GRADUATIONCHECKLIST",0,query);
                //if(executed)
                //{
                    
                //}
            }
            createNomisConfigurationFile("index_gcl_dateOfAssessment",0,"create index index_gcl_dateOfAssessment on GRADUATIONCHECKLIST(dateOfAssessment)");
                    createNomisConfigurationFile("index_gcl_clienttype",0,"create index index_gcl_clienttype on GRADUATIONCHECKLIST(clienttype)");
                    createNomisConfigurationFile("index_gcl_health",0,"create index index_gcl_health on GRADUATIONCHECKLIST(health)");
                    createNomisConfigurationFile("index_gcl_safety",0,"create index index_gcl_safety on GRADUATIONCHECKLIST(safety)");
                    createNomisConfigurationFile("index_gcl_clientid",0,"create index index_gcl_clientid on GRADUATIONCHECKLIST(CLIENTID)");
                    createNomisConfigurationFile("index_gcl_school",0,"create index index_gcl_school on GRADUATIONCHECKLIST(school)");
                    createNomisConfigurationFile("index_gcl_stability",0,"create index index_gcl_stability on GRADUATIONCHECKLIST(stability)");
                    createNomisConfigurationFile("index_gcl_vulnerability",0,"create index index_gcl_vulnerability on GRADUATIONCHECKLIST(vulnerability)");
                    createNomisConfigurationFile("index_gcl_graduated",0,"create index index_gcl_graduated on GRADUATIONCHECKLIST(graduated)");
                    createNomisConfigurationFile("index_gcl_volunteerId",0,"create index index_gcl_volunteerId on GRADUATIONCHECKLIST(volunteerId)");
                    createNomisConfigurationFile("index_gcl_gclscore",0,"create index index_gcl_gclscore on GRADUATIONCHECKLIST(gclscore)");
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating GRADUATIONCHECKLIST table "+ex.getMessage());
            return false;
        }
        return true;
    }
    private String getHivStatusUpdateTableQuery()
    {
        //String query="alter table HIVSTATUSUPDATE add column lastHivTrackingDate DATE NOT NULL DEFAULT '1900-01-01'";
        String query="CREATE TABLE HIVSTATUSUPDATE "
                        + "(RECORDID VARCHAR(12) NOT NULL PRIMARY KEY, CLIENTID VARCHAR(25) NOT NULL,"
                        + " HIVSTATUS VARCHAR(15) NOT NULL, RECORDSTATUS VARCHAR(10) NOT NULL,"
                        + " DATEOFCURRENTSTATUS DATE, DATEMODIFIED DATE, CLIENTTYPE VARCHAR(12) NOT NULL,"
                        + " CLIENTENROLLEDINCARE VARCHAR(4), ORGANIZATIONCLIENTISREFERRED VARCHAR(25),POINTOFUPDATE VARCHAR(25),CLIENTENROLLEDONART VARCHAR (4),"
                        + "TREATMENTID VARCHAR(25),DATEENROLLEDONTREATMENT DATE NOT NULL DEFAULT '1900-01-01',LASTHIVTRACKINGDATE DATE NOT NULL DEFAULT '1900-01-01',RECORDEDBY VARCHAR(25))";
        return query;
    }
    public void dropAndCreateHivStatusUpdateTable()
    {
        try
        {
            if(tableTruelyExists("HIVSTATUSUPDATE"))
            {
                util.executeSQLupdate("drop table HIVSTATUSUPDATE");
                util.executeSQLupdate(getHivStatusUpdateTableQuery());
                
                util.executeSQLupdate("create index index_hsu_clienttype on HIVSTATUSUPDATE(clienttype)");
                util.executeSQLupdate("create index index_hsu_enrolledincare on HIVSTATUSUPDATE(CLIENTENROLLEDINCARE)");
                util.executeSQLupdate("create index index_hsu_dateofcurstatus on HIVSTATUSUPDATE(DATEOFCURRENTSTATUS)");
                util.executeSQLupdate("create index index_hsu_clientid on HIVSTATUSUPDATE(CLIENTID)");
                util.executeSQLupdate("create index index_hsu_hivstatus on HIVSTATUSUPDATE(HIVSTATUS)");
                util.executeSQLupdate("create index index_hsu_recordstatus on HIVSTATUSUPDATE(RECORDSTATUS)");
                util.executeSQLupdate("create index index_hsu_reforganization on HIVSTATUSUPDATE(ORGANIZATIONCLIENTISREFERRED)");
                util.executeSQLupdate("create index index_hsu_pointofupdate on HIVSTATUSUPDATE(POINTOFUPDATE)");
                util.executeSQLupdate("create index index_onART on HivStatusUpdate(CLIENTENROLLEDONART)");
                System.err.println("HIVSTATUSUPDATE table recreated in DatabaseUtilities"); 
            }
            
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating HIVSTATUSUPDATE table "+ex.getMessage()); 
        }
    }
    public boolean createHivStatusUpdateTable()
    {
        try
        {
            if(!tableTruelyExists("HIVSTATUSUPDATE"))
            {
                boolean executed=createNomisConfigurationFile("HIVSTATUSUPDATE",0,getHivStatusUpdateTableQuery());
                util.executeSQLupdate("create index index_hsu_clienttype on HIVSTATUSUPDATE(clienttype)");
                util.executeSQLupdate("create index index_hsu_enrolledincare on HIVSTATUSUPDATE(CLIENTENROLLEDINCARE)");
                util.executeSQLupdate("create index index_hsu_dateofcurstatus on HIVSTATUSUPDATE(DATEOFCURRENTSTATUS)");
                util.executeSQLupdate("create index index_hsu_clientid on HIVSTATUSUPDATE(CLIENTID)");
                util.executeSQLupdate("create index index_hsu_hivstatus on HIVSTATUSUPDATE(HIVSTATUS)");
                util.executeSQLupdate("create index index_hsu_recordstatus on HIVSTATUSUPDATE(RECORDSTATUS)");
                util.executeSQLupdate("create index index_hsu_reforganization on HIVSTATUSUPDATE(ORGANIZATIONCLIENTISREFERRED)");
                util.executeSQLupdate("create index index_hsu_pointofupdate on HIVSTATUSUPDATE(POINTOFUPDATE)");
            }
            addColumnToHivStatusUpdateTable();
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating HIVSTATUSUPDATE table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public void addColumnToHivStatusUpdateTable()
    {
        try
        {
            String query="alter table HIVSTATUSUPDATE add column lastHivTrackingDate DATE NOT NULL DEFAULT '1900-01-01'";
            boolean executed=createNomisConfigurationFile("trackingDateAddedToHivStatusTable",0,query);
            if(executed)
            {
                util.executeSQLupdate("create index ide_hsuTD on HIVSTATUSUPDATE(lastHivTrackingDate)");
            }
            query="alter table HIVSTATUSUPDATE add column recordedBy VARCHAR(25)";
            executed=createNomisConfigurationFile("rcordedAddedToHivStatusTable",0,query);
            }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public boolean createOvcTbHivScreeningChecklistTable()
    {
        try
        {
            if(!tableTruelyExists("OVCTBHIVSCREENINGCHECKLIST"))
            {
                String query="CREATE TABLE OVCTBHIVSCREENINGCHECKLIST "
                + "(ID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key, OVCID VARCHAR(25) NOT NULL,"
                + " DATEOFASSESSMENT DATE NOT NULL, CHILDCOUGHING VARCHAR(3), CHILDLOSSINGWEIGHT VARCHAR(3),"
                +"CHILDHAVINGFEVER VARCHAR(3), CHILDHAVINGNIGHTSWEAT VARCHAR(3), FAMILYMEMBERHADTB VARCHAR(3),"
                +"DOSESOFARVMISSEDBYCHILD VARCHAR(3), CAREGIVERENROLLEDINPMTCT VARCHAR(3), EIDTESTINGDONEFORCHILD VARCHAR(3),"
                +"CHILDONSEPTRIN VARCHAR(3), HIVSTATUSDISCLOSEDTOPARTNER VARCHAR(3), HIVSTATUSOFPARTNERKNOWN VARCHAR(3),"
                +"DOSESOFSEPTRINMISSEDBYCHILD VARCHAR(3), CHILDABOVESIXWKSANDSTARTEDONSEPTRIN VARCHAR(3),"
                +"CHILDSLEEPINTREATEDNET VARCHAR(3), BENEFICIARIESHASSORESORBLEEDING VARCHAR(3),"
                +"NUMBEROFCLUBSATTNDEDINTHREEMTHS VARCHAR(3), CHILDSWELLINGONNECK VARCHAR(3),ASSESSMENTNO NUMERIC(3) DEFAULT 0 NOT NULL,"
                +"VOLUNTEERNAME VARCHAR(50), DESIGNATION VARCHAR(25), DATEMODIFIED DATE DEFAULT CURRENT_DATE  NOT NULL)";
                        
                boolean executed=createNomisConfigurationFile("OVCTBHIVSCREENINGCHECKLIST",0,query);
                util.executeSQLupdate("create index index_tbhiv_ovcId on OVCTBHIVSCREENINGCHECKLIST(OVCID)");
                util.executeSQLupdate("create index index_tbhiv_dateofassessment on OVCTBHIVSCREENINGCHECKLIST(DATEOFASSESSMENT)");
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating OVCTBHIVSCREENINGCHECKLIST table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createCaregiverTbHivScreeningChecklistTable()
    {
        try
        {
            if(!tableTruelyExists("CAREGIVERTBHIVSCREENINGCHECKLIST"))
            {
                String query="CREATE TABLE CAREGIVERTBHIVSCREENINGCHECKLIST "
                + "(ID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key, CAREGIVERID VARCHAR(25) NOT NULL,"
                + " DATEOFASSESSMENT DATE NOT NULL, CAREGIVERCOUGHING VARCHAR(3), CAREGIVERLOSSINGWEIGHT VARCHAR(3),"
                +"CAREGIVERHAVINGFEVER VARCHAR(3), CAREGIVERHAVINGNIGHTSWEAT VARCHAR(3), FAMILYMEMBERHADTB VARCHAR(3),"
                +"DOSESOFARVMISSEDBYCAREGIVER VARCHAR(3), CAREGIVERENROLLEDINPMTCT VARCHAR(3), EIDTESTINGDONEFORCHILD VARCHAR(3),"
                +"CAREGIVERONSEPTRIN VARCHAR(3), HIVSTATUSDISCLOSEDTOPARTNER VARCHAR(3), HIVSTATUSOFPARTNERKNOWN VARCHAR(3),"
                +"DOSESOFSEPTRINMISSEDBYCAREGIVER VARCHAR(3), CHILDABOVESIXWKSANDSTARTEDONSEPTRIN VARCHAR(3),"
                +"CAREGIVERSLEEPINTREATEDNET VARCHAR(3), BENEFICIARIESHASSORESORBLEEDING VARCHAR(3),"
                +"NUMBEROFCLUBSATTNDEDINTHREEMTHS VARCHAR(3), CAREGIVERSWELLINGONNECK VARCHAR(3),ASSESSMENTNO NUMERIC(3) DEFAULT 0 NOT NULL,"
                +"VOLUNTEERNAME VARCHAR(50), DESIGNATION VARCHAR(25), DATEMODIFIED DATE DEFAULT CURRENT_DATE  NOT NULL)";
                        
                boolean executed=createNomisConfigurationFile("CAREGIVERTBHIVSCREENINGCHECKLIST",0,query);
                util.executeSQLupdate("create index index_tbhiv_caregiverId on CAREGIVERTBHIVSCREENINGCHECKLIST(CAREGIVERID)");
                util.executeSQLupdate("create index index_cgtbhiv_dateofassessment on CAREGIVERTBHIVSCREENINGCHECKLIST(DATEOFASSESSMENT)");
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating CAREGIVERTBHIVSCREENINGCHECKLIST table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean addNewUniqueIdColumnToDeletedRecordTable()
    {
        boolean executed=false;
        try
        {
            if(tableTruelyExists("DELETEDRECORD"))
            {
                String query="alter table DELETEDRECORD add column newUniqueId VARCHAR (25)";
                executed=createNomisConfigurationFile("newUniqueIdaddedToDeletedRecordTable",0,query);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean createDeletedRecordTable()
    {
        try
        {
            if(!tableTruelyExists("DELETEDRECORD"))
            {
                String query="CREATE TABLE DELETEDRECORD "
                + "(ID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key, RECORDID VARCHAR(25) NOT NULL,"
                + " DATERECORDCREATED DATE, DATERECORDDELETED DATE, TYPEOFRECORD VARCHAR(25) NOT NULL)";
                        
                boolean executed=createNomisConfigurationFile("DeletedRecord",0,query);
                if(executed)
                {
                    addNewUniqueIdColumnToDeletedRecordTable();
                    util.executeSQLupdate("create index index_dobj_recordid on DELETEDRECORD(RECORDID)");
                    util.executeSQLupdate("create index index_dobj_recordtype on DELETEDRECORD(TYPEOFRECORD)");
                    util.executeSQLupdate("create index index_dobj_deleteddate on DELETEDRECORD(DATERECORDDELETED)");
                    util.executeSQLupdate("create index index_dobj_recordcreationdate on DELETEDRECORD(DATERECORDCREATED)");
                }
            }
        }       
        catch(Exception ex)
        {
            System.err.println("Error creating DELETEDRECORD table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createDatabaseImportTrackerTable()
    {
        try
        {
                String query="CREATE TABLE DATABASEIMPORTTRACKER"
                +"(RECORDID INTEGER generated always as identity(start with 0,increment by 1) NOT NULL primary key,"
                +"DATEOFIMPORT DATE NOT NULL,"
                +"USERNAME VARCHAR(50) NOT NULL,"
                +"SUMMARY VARCHAR(2000))";
                boolean executed=createNomisConfigurationFile("DATABASEIMPORTTRACKER",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating DataValue table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean addColumnsToDatabaseImportTrackerTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table DATABASEIMPORTTRACKER add column XMLFILENAME VARCHAR (500)";
            executed=createNomisConfigurationFile("xmlFileNameAddedToDatabaseImportTrackerTable",0,query);
            query="alter table DATABASEIMPORTTRACKER add column RESPONSESENT VARCHAR (3)";
            executed=createNomisConfigurationFile("responseSentAddedToDatabaseImportTrackerTable",0,query);
            query="alter table DATABASEIMPORTTRACKER add column DATEANDTIME VARCHAR (25)";
            executed=createNomisConfigurationFile("dateAndTimeAddedToDatabaseImportTrackerTable",0,query);
            /*if(executed)
            {
                util.moveAllImportFilesToDoneDirectory();
            }*/
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean createDataValueTable()
    {
        try
        {
            String query="create table DataValue"
            +"(recordId INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 0, increment BY 1) not null primary key," +
            "ORGUNITID VARCHAR(15) NOT NULL," +
            "INDICATORID VARCHAR(15) NOT NULL," +
            "CATEGORYCOMBOID VARCHAR(15) NOT NULL," +
            "STARTDATE DATE NOT NULL," +
            "VALUE NUMERIC(7) NOT NULL)";
            boolean executed=createNomisConfigurationFile("DataValue",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating DataValue table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createReportQueryManagerTable()
    {
        try
        {
                String query="create table ReportQueryManager"
                +"(UNIQUEID VARCHAR(15) NOT NULL PRIMARY KEY," +
                "INDICATORID VARCHAR(15) NOT NULL," +
                "CATEGORYCOMBOID VARCHAR(15) NOT NULL," +
                "NAME VARCHAR(500)," +
                "DESCRIPTION VARCHAR(1000)," +
                "PROGRAMAREA VARCHAR(50) DEFAULT 'none'  NOT NULL," +
                "TYPE VARCHAR(50) DEFAULT 'none'  NOT NULL)";
                boolean executed=createNomisConfigurationFile("ReportQueryManager",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating DataValue table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createIndicatorTable()
    {
        try
        {
            String query="create table INDICATORTABLE"
            +"(INDICATORID VARCHAR(15) NOT NULL PRIMARY KEY," +
            "INDICATORNAME VARCHAR(500) NOT NULL," +
            "DESCRIPTION VARCHAR(1000)," +
            "PROGRAMAREA VARCHAR(50) DEFAULT 'none'  NOT NULL," +
            "TYPE VARCHAR(50) DEFAULT 'none'  NOT NULL," +
            "QUERY VARCHAR(1000))";
            boolean executed=createNomisConfigurationFile("INDICATORTABLE",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating INDICATORTABLE table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createOrganizationUnitGroupTable()
    {
        try
        {
            String query="create table OrganizationUnitGroup"
            +"(orgUnitGroupId VARCHAR(15) NOT NULL PRIMARY KEY," +
            "orgUnitGroupName VARCHAR(500) NOT NULL,"+
             "description VARCHAR(1000))";
            boolean executed=createNomisConfigurationFile("OrganizationUnitGroup",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating OrganizationUnitGroup table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createOrganizationUnitGroupAssignmentTable()
    {
        try
        {
            String query="create table OrganizationUnitGroupAssignment"
            +"(recordId INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 0, increment BY 1) not null primary key," +
            "orgunitid VARCHAR(15) NOT NULL," +
            "parentOrgUnitGroupId VARCHAR(15),"+
            "orgUnitGroupId VARCHAR(15) NOT NULL)";
            boolean executed=createNomisConfigurationFile("OrganizationUnitGroupAssignment",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating OrganizationUnitGroupAssignment table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean changeOrgunitIdSizeInOrganizationUnitGroupAssignmentTable()
    {
        String query="alter table OrganizationUnitGroupAssignment alter column orgunitid set data type varchar(25)";
        boolean executed=createNomisConfigurationFile("orgunitidSizeChangedInOugA",0,query);
        return executed;
    }
    public boolean createUmbrellaOrganizationTable()
    {
        try
        {
                String query="create table UmbrellaOrganization"
                +"(recordId VARCHAR(15) NOT NULL primary key," +
                "umbrellaOrganizationCode VARCHAR(15) NOT NULL," +
                "subOrganizationCodes VARCHAR(300)," +
                "DATEMODIFIED DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("UmbrellaOrganizationTable",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating UmbrellaOrganization table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createHouseholdFollowupAssessmentTable()
    {
        try
        {
                String query="create table HouseholdFollowupAssessment"
                +"(recordId VARCHAR(15) NOT NULL primary key," +
                "hhUniqueId VARCHAR(25) NOT NULL," +
                "UPDATEDADDRESS VARCHAR(1000)," +
                "hhAge numeric(3) NOT NULL," +
                "NOOFCHILDRENINHH numeric(3) NOT NULL," +
                "NOOFPEOPLEINHH numeric(3) NOT NULL," +
                "OCCUPATION VARCHAR(200)," +
                "PHONE VARCHAR(20)," +
                "PARTNERCODE VARCHAR(15)," +
                "RECORDEDBY VARCHAR(50)," +
                "dateOfAssessment DATE NOT NULL," +
                "DATEMODIFIED DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("HouseholdFollowupAssessment",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating HouseholdFollowupAssessment table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createTrainingParticipantTable()
    {
        try
        {
                String query="create table TrainingParticipant"
                +"(participantId VARCHAR(15) NOT NULL primary key," +
                "organizationCode VARCHAR(15)," +
                "traineeId VARCHAR(15) NOT NULL," +
                "participantName VARCHAR(200) NOT NULL," +
                "gender VARCHAR(6) NOT NULL," +
                "age numeric(3) NOT NULL," +
                "designation VARCHAR(100) NOT NULL," +
                "phone VARCHAR(20)," +
                "email VARCHAR(20)," +
                "dateModified DATE NOT NULL)";

                boolean executed=createNomisConfigurationFile("TrainingParticipant",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating TrainingParticipant table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createTrainingTable()
    {
        try
        {//
                String query="create table TRAINING"
                +"(RECORDId VARCHAR(15) NOT NULL primary key," +
                "trainingId VARCHAR(15) NOT NULL," +
                "participantId VARCHAR(15) NOT NULL," +
                "category VARCHAR(15) NOT NULL," +
                "designation VARCHAR(15) NOT NULL," +
                "trainingStatus VARCHAR(15) NOT NULL," +
                "partnerCode VARCHAR(15) NOT NULL," +
                "startDate DATE NOT NULL," +
                "endDate DATE NOT NULL,"+
                "DATEMODIFIED DATE NOT NULL)";
                boolean executed=createNomisConfigurationFile("Training",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating TRAINING table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createTrainingCategoryTable()
    {
        try
        {
                String query="create table TRAININGCATEGORY"
                +"(categoryId VARCHAR(15) NOT NULL primary key," +
                 "categoryName VARCHAR(200) NOT NULL)";
                boolean executed=createNomisConfigurationFile("TrainingCategoryTable",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating TRAININGCATEGORY table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createTrainingDesignationTable()
    {
        try
        {
                String query="create table TRAININGDESIGNATION"
                +"(designationId VARCHAR(15) NOT NULL primary key," +
                "designationName VARCHAR(200) NOT NULL," +
                "categoryId VARCHAR(15) NOT NULL)";
                boolean executed=createNomisConfigurationFile("TrainingDesignationTable",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating TRAININGDESIGNATION table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createTrainingStatusSetupTable()
    {
        try
        {
                String query="create table TRAININGSTATUSSETUP"
                +"(STATUSID VARCHAR(15) NOT NULL primary key," +
                "TRAININGSTATUSNAME VARCHAR(200) NOT NULL)";
                boolean executed=createNomisConfigurationFile("TrainingStatusSetupTable",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating TRAININGINFORMATIONSETUP table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createTrainingInformationSetupTable()
    {
        try
        {
                String query="create table TRAININGINFORMATIONSETUP"
                +"(RECORDID VARCHAR(15) NOT NULL primary key," +
                "TRAININGNAME VARCHAR(200) NOT NULL)";
                boolean executed=createNomisConfigurationFile("TrainingInformationSetupTable",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("Error creating TRAININGINFORMATIONSETUP table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean dropDhisDataExportTable()
    {
        try
        {
            String query="drop table DHISDATAEXPORT";
            util.executeSQLupdate(query);
        }
        catch(Exception ex)
        {
            System.err.println("Error droping DHISDATAEXPORT table -- "+ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean dropHouseholdUniqueServiceReportTable()
    {
        try
        {
            if(tableTruelyExists("HOUSEHOLDUNIQUESERVICEREPORT"))
            {
                String serviceTableQuery="drop table HOUSEHOLDUNIQUESERVICEREPORT";
                util.executeSQLupdate(serviceTableQuery);
            }
        }
        catch(Exception ex)
        {
            System.err.println("Error droping HOUSEHOLDUNIQUESERVICEREPORT table -- "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean dropServiceReportTable()
    {
        try
        {
            if(tableTruelyExists("SERVICEREPORT"))
            {
                String serviceTableQuery="drop table SERVICEREPORT";
                util.executeSQLupdate(serviceTableQuery);
            }
        }
        catch(Exception ex)
        {
            System.err.println("Error droping SERVICEREPORT table -- "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createServiceReportTable()
    {
        try
        {
            if(!tableTruelyExists("SERVICEREPORT"))
            {
                String serviceTableQuery="create table APP.SERVICEREPORT"
                +"(ID INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 0, increment BY 1) not null primary key," +
                "OVC_ID CHAR(25) not null,SERVICEDATE DATE default '0000-00-00' not null,DATE_ENTRY DATE default '0000-00-00' not null," +
                "SERVICE_ACCESSED1 VARCHAR(2000),SERVICE_ACCESSED2 VARCHAR(2000),SERVICE_ACCESSED3 VARCHAR(2000)," +
                "SERVICE_ACCESSED4 VARCHAR(2000),SERVICE_ACCESSED5 VARCHAR(2000),SERVICE_ACCESSED6 VARCHAR(2000)," +
                "SERVICE_ACCESSED7 VARCHAR(2000),SERVICESREFERRED VARCHAR(2000),WEIGHT DOUBLE default 0 not null," +
                "HEIGHT DOUBLE default 0 not null,SURVEY_NUMBER INTEGER default 0 not null,SERVICEPROVIDER VARCHAR(100)," +
                "NO_OF_SERVICES NUMERIC(3) default 0 not null)";
                util.executeSQLupdate(serviceTableQuery);
                updateServiceReport();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Error creating SERVICEREPORT table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createHouseholdUniqueServiceReportTable()
    {
        try
        {
            if(!tableTruelyExists("HOUSEHOLDUNIQUESERVICEREPORT"))
            {
                String serviceTableQuery="create table APP.HOUSEHOLDUNIQUESERVICEREPORT"
                +"(ID INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 0, increment BY 1) not null primary key," +
                "HHUNIQUEID VARCHAR(25) DEFAULT 'none'  NOT NULL, SERVICEDATE DATE, SERVICENO NUMERIC(3) DEFAULT 0  NOT NULL," +
                "ECONOMICSTRENGTHENING VARCHAR(1000), EDUCATION VARCHAR(1000), HEALTH VARCHAR(1000), NUTRITION VARCHAR(1000)," +
                "PROTECTION VARCHAR(1000), PSYCHOSOCIALSUPPORT VARCHAR(1000), SHELTERANDCARE VARCHAR(1000), VOLUNTEERNAME VARCHAR(50)," +
                "VOLUNTEERDESIGNATION VARCHAR(50), RECORDEDBY VARCHAR(100), DATEOFENTRY DATE, SERVICERECIPIENTTYPE VARCHAR(25)," +
                "CAREGIVERID VARCHAR(25), NUMBEROFSERVICES NUMERIC(3) DEFAULT 0)";
                util.executeSQLupdate(serviceTableQuery);
                addIndexToHouseholdUniqueServiceReportTable();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Error creating HOUSEHOLDUNIQUESERVICEREPORT table "+ex.getMessage());
            return false;
        }
        return true;
    }//CAREGIVERID VARCHAR(25), NUMBEROFSERVICES NUMERIC(3) DEFAULT 0 , PRIMARY KEY (ID));
    public boolean dropSpecificServicesReportTable()
    {
        try
        {
            if(tableTruelyExists("SPECIFICSERVICEREPORT"))
            {
                String serviceTableQuery="drop table APP.SPECIFICSERVICEREPORT";
                util.executeSQLupdate(serviceTableQuery);
            }
        }
        catch(Exception ex)
        {
            System.err.println("Error droping SPECIFICSERVICEREPORT table -- "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createSpecificServiceReportTable()
    {
        try
        {
            if(!tableTruelyExists("SPECIFICSERVICEREPORT"))
            {
                String serviceTableQuery="create table APP.SPECIFICSERVICEREPORT"
                +"(ID INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 0, increment BY 1) not null primary key," +
                "OVC_ID CHAR(25) not null,SERVICEDATE DATE default '0000-00-00' not null,DATE_ENTRY DATE default '0000-00-00' not null," +
                "PSYCHOSOCIALSUPPORT VARCHAR(2000),NUTRITIONALSERVICES VARCHAR(2000),HEALTHSERVICES VARCHAR(2000)," +
                "EDUCATIONALSERVICES VARCHAR(2000),PROTECTIONSERVICES VARCHAR(2000),SHELTERSERVICES VARCHAR(2000)," +
                "ECONOMICSERVICES VARCHAR(2000),SERVICESREFERRED VARCHAR(2000),WEIGHT DOUBLE default 0 not null," +
                "HEIGHT DOUBLE default 0 not null,SURVEY_NUMBER INTEGER default 0 not null,SERVICEPROVIDER VARCHAR(100)," +
                "NO_OF_SERVICES NUMERIC(3) default 0 not null)";
                util.executeSQLupdate(serviceTableQuery);
                updateSpecificServiceReport();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Error creating SPECIFICSERVICEREPORT table "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createDhisDataExportTable(boolean initial)
    {
        try
        {
            boolean executed=true;
            String query="create table DHISDATAEXPORT" +
            "(ID INTEGER generated always as identity (start with 0, increment by 1) not null primary key,"+
            "DHISINSTANCE VARCHAR(20),"+
            "STATE VARCHAR(200),"+
            "LGA VARCHAR(200),"+
            "ORGUNITID VARCHAR(25),"+
            "ORGUNITNAME VARCHAR(200),"+
            "DATAELEMENTID VARCHAR(25),"+
            "DATAELEMENTNAME VARCHAR(200),"+
            "CATEGORYOPTIONCOMBOID VARCHAR(25),"+
            "CATEORYCOMBOOPTIONNAME VARCHAR(100),"+
            "DATASET VARCHAR(100),"+
            "STARTDATE DATE,"+
            "VALUE NUMERIC(6) default 0"+
             ")";
            if(initial)
            executed=createNomisConfigurationFile("dhisDataExport",0,query);
            else
            util.executeSQLupdate(query);
        }
        catch(Exception ex)
        {
            System.err.println("error creating Table DHISDATAEXPORT "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createCustomDataElementTable()
    {
        try
        {
            String query="create table CustomDataElement" +
            "(customDeId varchar(15) not null primary key,"+
            "customDeName VARCHAR(200)  not null,"+
            "customDeDescription VARCHAR(1000),"+
            "categoryComboId VARCHAR(15) not null,"+
            "stdDeId VARCHAR(15)  not null,"+
            "stdDeDescription VARCHAR(1000)"+
             ")";
            boolean executed=createNomisConfigurationFile("CustomDataElement",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("error creating Table CustomDataElementTable "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createCategoryCombinationTable()
    {
        try
        {//PROGRAMAREA
            String query="create table CategoryCombination" +
            "(categoryComboId varchar(25) not null primary key,"+
            "categoryId VARCHAR(15) not null,"+
            "catComboName VARCHAR(200)  not null,"+
            "catComboDescription VARCHAR(1000),"+
            "programArea VARCHAR(200)  not null,"+
            "type VARCHAR(100)  not null,"+
            "query VARCHAR(1000)"+
            ")";
            boolean executed=createNomisConfigurationFile("CategoryCombination",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("error creating Table CategoryCombinationTable "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createCategoryTable()
    {
        try
        {
            String query="create table Category" +
            "(categoryId varchar(25) not null primary key,"+
            "categoryName VARCHAR(50)  not null,"+
            "categoryOption VARCHAR(200) not null"+
            ")";
            boolean executed=createNomisConfigurationFile("Category",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("error creating Table CategoryTable "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createCategoryOptionTable()
    {
        try
        {
            String query="create table CategoryOption" +
            "(categoryOptionId varchar(15) not null primary key,"+
            "categoryOptionName VARCHAR(200) not null,"+
             "categoryOptionDescription VARCHAR(1000),"+
            "disaggregation VARCHAR(50),"+
            "categoryDefinition VARCHAR(50) not null"+
             ")";
            boolean executed=createNomisConfigurationFile("CategoryOptionTable",0,query);
        }
        catch(Exception ex)
        {
            System.err.println("error creating Table CategoryOptionTable "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean createReportQueryTable()
    {
        try
        {
            String reportQueryTableQuery="create table ReportQuery" +
            "(queryId varchar(15) not null primary key,"+
            "queryName VARCHAR(200) not null,"+
            "QueryString VARCHAR(1000) not null,"+
            "queryCriteria VARCHAR(100),"+
            "QueryDescription VARCHAR(1000),"+
            "queryType VARCHAR(50)"+
            ")";
            boolean executed=createNomisConfigurationFile("reportQueryTable",0,reportQueryTableQuery);
        }
        catch(Exception ex)
        {
            System.err.println("error creating Table ReportQuery "+ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean addColumnsToNutritionAssessmentTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table NutritionAssessment add column bodyMassIndex double not null default 0";
            executed=createNomisConfigurationFile("bodyMassIndexAddedToNutritionAssessmentTable",0,query);
            query="alter table NutritionAssessment add column naRecordStatus numeric(1) not null default 0";
            executed=createNomisConfigurationFile("naRecordStatusAddedToNutritionAssessmentTable",0,query);
            
            query="alter table NutritionAssessment add column muacFacility varchar(15)";
            executed=createNomisConfigurationFile("muacFacilityAddedToNutritionAssessmentTable",0,query);
            query="alter table NutritionAssessment add column yellowMuacServices varchar(500)";
            executed=createNomisConfigurationFile("yellowMuacServicesAddedToNutritionAssessmentTable",0,query);
            
            util.executeSQLupdate("create index index_na_muac on NutritionAssessment(muac)");
            util.executeSQLupdate("create index index_na_naRecordStatus on NutritionAssessment(naRecordStatus)");
            util.executeSQLupdate("create index index_na_bodyMassIndex on NutritionAssessment(bodyMassIndex)");
            util.executeSQLupdate("create index index_na_bmi on NutritionAssessment(bmi)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean createNutritionAssessmentTable()
    {
        try
        {
            String nutritionAssessmentTableQuery="create table NUTRITIONASSESSMENT" +
            "(ID INTEGER generated always as identity(start with 0,increment by 1) not null primary key,"+
            "OVCID VARCHAR(25) not null,"+
            "DATEOFASSESSMENT DATE,"+
            "WEIGHT DOUBLE default 0 not null,"+
            "LASTWEIGHT DOUBLE default 0 not null,"+
            "HEIGHT DOUBLE default 0 not null,"+
            "DATEOFLASTWEIGHT DATE,"+
            "CHANGEINWEIGHT VARCHAR(15),"+
            "ASSESSMENTNO NUMERIC(3) not null,"+
            "OEDEMA VARCHAR(3),"+
            "MUAC VARCHAR(25),"+
            "BMI VARCHAR(15),"+
            "NUTRITIONALSTATUS VARCHAR(25),"+
            "FOODSECURITYANDDIETQ1 VARCHAR(25),"+
            "FOODSECURITYANDDIETQ2 VARCHAR(25),"+
            "FOODSECURITYANDDIETQ3 VARCHAR(25),"+
            "FOODSECURITYANDDIETQ4 VARCHAR(25),"+
            "FOODSECURITYANDDIETQ5 VARCHAR(25),"+
            "FOODSECURITYANDDIETQ6 VARCHAR(25),"+
            "FOODSECURITYANDDIETQ7 VARCHAR(25),"+
            "FOODSECURITYANDDIETQ8 VARCHAR(25),"+
            "FOODSECURITYANDDIETQ9 VARCHAR(25),"+
            "HYGIENEQ1 VARCHAR(25),"+
            "HYGIENEQ2 VARCHAR(25),"+
            "HYGIENEQ3 VARCHAR(25),"+
            "HYGIENEQ4 VARCHAR(25),"+
            "MEDICALEVALUATIONHIV VARCHAR(25),"+
            "MEDICALEVALUATIONDIARRHEA VARCHAR(25),"+
            "MEDICALEVALUATIONNAUSEA VARCHAR(25),"+
            "MEDICALEVALUATIONPAINFULCHEWING VARCHAR(25),"+
            "MEDICALEVALUATIONVOMITING VARCHAR(25),"+
            "MEDICALEVALUATIONPOORAPETITE VARCHAR(25),"+
            "MEDICALEVALUATIONMOUTHSOARS VARCHAR(25),"+
            "DATEMODIFIED DATE,"+
            "RECORDEDBY VARCHAR(50)"+
            ")";
            boolean executed=createNomisConfigurationFile("nutritionAssessmentTable",0,nutritionAssessmentTableQuery);

        }
        catch(Exception ex)
        {
            System.err.println("Table nutritionAssessmentTable already exist");
            return false;
        }
        return true;
    }
    public boolean createHouseholdEnrollmentTable()
    {
        try
        {
            String householdEnrollmentTableQuery="create table HOUSEHOLDENROLLMENT"+
            "(HHUNIQUEID VARCHAR(50) default ' ' not null primary key,"+
            "STATECODE VARCHAR(10)," +
            "LGACODE VARCHAR(10)," +
            "ORGCODE VARCHAR(25)," +
            "COMMUNITYCODE VARCHAR(25)," +
            "HHFIRSTNAME VARCHAR(25)," +
            "HHSURNAME VARCHAR(25) not null," +
            "HHGENDER VARCHAR(6)," +
            "HHAGE NUMERIC(3) default 0 not null," +
            "CURRENTAGE NUMERIC(3) default 0 not null," +
            "ADDRESS VARCHAR(1000)," +
            "PHONE VARCHAR(25)," +
            "MARITALSTATUS VARCHAR(20)," +
            "OCCUPATION VARCHAR(50)," +
            "NOOFCHILDRENINHH NUMERIC(3) default 0 not null," +
            "NOOFPEOPLEINHH NUMERIC(3) not null," +
            "ELIGIBLEFORENROLLMENT VARCHAR(25)," +
            "PARTNERCODE VARCHAR(25)," +
            "RECORDEDBY VARCHAR(100)," +
            "DATEOFASSESSMENT DATE," +
            "DATEOFENTRY DATE)";
            boolean executed=createNomisConfigurationFile("householdEnrollmentTable",0,householdEnrollmentTableQuery);
        }
        catch(Exception ex)
        {
            System.err.println("Table householdEnrollmentTable already exist");
            return false;
        }
        return true;
    }
    public boolean createHouseholdVulnerabilityAssessmentTable()
    {
        try
        {
             String householdVulnerabilityAssessmentTableQuery="create table HHVULNERABILITYASSESSMENT" +
            "(ID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1) not null primary key," +
            "HHUNIQUEID VARCHAR(50) not null," +
            "DATEOFASSESSMENT DATE," +
            "HHHEADSHIP NUMERIC," +
            "HEALTH NUMERIC," +
            "EDUCATIONLEVEL NUMERIC," +
            "SHELTERANDHOUSING NUMERIC," +
            "FOODSECURITYANDNUTRITION NUMERIC," +
            "MEANSOFLIVELIHOOD NUMERIC," +
            "HHINCOME NUMERIC," +
            "ASSESSMENTNO NUMERIC," +
            "VULNERABILITYSCORE NUMERIC," +
            "NAMEOFASSESSOR VARCHAR(50)," +
            "DESIGNATIONOFASSESSOR VARCHAR(50)," +
            "RECORDEDBY VARCHAR(100)," +
            "DATEOFENTRY DATE)";
            boolean executed=createNomisConfigurationFile("householdVulnerabilityAssessmentTable",0,householdVulnerabilityAssessmentTableQuery);

        }
        catch(Exception ex)
        {
            System.err.println("Table householdVulnerabilityAssessmentTable already exist");
            return false;
        }
        return true;
    }
    public boolean createCaregiverInfoTable()
    {
        try
        {
                String caregiverInfoTableQuery="CREATE TABLE CAREGIVERINFO"
                +"(CAREGIVERID VARCHAR(25) not null primary key,"
                +"HHUNIQUEID VARCHAR(25) not null,"
                +"CAREGIVERFIRSTNAME VARCHAR(50) not null,"
                +"CAREGIVERLASTNAME VARCHAR(50) not null,"
                +"CAREGIVERGENDER VARCHAR(6) not null,"
                +"CAREGIVERAGE NUMERIC(3) not null default 0,"
                +"CURRENTAGE NUMERIC(3) not null default 0,"
                +"CAREGIVERPHONE VARCHAR(25),"
                +"CAREGIVERADDRESS VARCHAR(1000),"
                +"CAREGIVEROCCUPATION VARCHAR(50),"
                +"CAREGIVERMARITALSTATUS VARCHAR(50),"
                +"DATEOFENROLLMENT DATE,"
                +"DATEMODIFIED DATE,"
                +"HOUSEHOLDHEAD VARCHAR(3))";
                boolean executed=createNomisConfigurationFile("caregiverInfoTable",0,caregiverInfoTableQuery);
                //caregiverMaritalStatus
        }
        catch(Exception ex)
        {
            System.err.println("Table CAREGIVERINFO already exist");
            return false;
        }
        return true;
    }
    public boolean createUserRightsTable()
    {
        try
        {
                String userRightsTableQuery="CREATE TABLE USERRIGHTS"
                +"(RIGHTID VARCHAR(11) NOT NULL, NAME VARCHAR(100) NOT NULL,"
                +"TYPE VARCHAR(25), PRIMARY KEY (RIGHTID))";               
                boolean executed=createNomisConfigurationFile("userRightsTable",0,userRightsTableQuery);
                if(executed)
                {
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('001', 'Data entry', 'dataEntry')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('002', 'Add user role', 'userrole')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('003', 'Add User', 'addUser')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('004', 'Generate reports', 'generateReport')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('005', 'Export database', 'dataExport')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('006', 'Import Data', 'dataImport')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('007', 'Add/Update School', 'schoolSetup')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('008', 'Setup sites', 'siteSetup')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('009', 'Add Vulnerability Status', 'addVulnerabilityStatus')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('010', 'Add/Update LGA', 'lgasetup')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('011', 'Import data elements', 'importdataelements')");
                    util.executeSQLupdate("INSERT INTO APP.USERRIGHTS (RIGHTID, NAME, TYPE) VALUES ('012', 'Access pmis data', 'accesspmisdata')");
                }
                
        }
        catch(Exception ex)
        {
            System.err.println("Table USERRIGHTS TABLE already exist");
            return false;
        }
        return true;
    }
    public boolean addClientEnrolledOnARTToHivStatusUpdateTable()
    {
        boolean executed=false;
        try
        {
            String query="alter table HivStatusUpdate add column CLIENTENROLLEDONART VARCHAR (4)";
            executed=createNomisConfigurationFile("clientOnARTAddedToHivStatusUpdateTable",0,query);
            util.executeSQLupdate("create index index_onART on HivStatusUpdate(CLIENTENROLLEDONART)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public void updateOrgRecordsTable()
    {     try
          {
            util.executeSQLupdate("alter table orgrecords drop column lga_code");
            util.executeSQLupdate("alter table orgrecords add column lga_code varchar(300)");
          }
          catch(Exception ex)
          {
              ex.printStackTrace();
          }
    }
    public boolean addLatitudeToOrgRecordsTable()
    {
        String query="alter table OrgRecords add column latitude DOUBLE not null default 0.0";
        boolean executed=createNomisConfigurationFile("latitudeAddedToOrgRecordsTable",0,query);
        return executed;
    }
    public boolean addLongitudeToOrgRecordsTable()
    {
        String query="alter table OrgRecords add column longitude DOUBLE not null default 0.0";
        boolean executed=createNomisConfigurationFile("longitudeAddedToOrgRecordsTable",0,query);
        return executed;
    }
    public boolean addFieldsToEnrollmentTable()
    {
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("currentAgeUnitAddedToEnrollmentTable",0,"alter table ENROLLMENT add column CURRENTAGEUNIT VARCHAR(5)");
            executed=createNomisConfigurationFile("enrolledInCareAddedToEnrollmentTable",0,"alter table ENROLLMENT add column ENROLLEDINCARE VARCHAR(4)");
            executed=createNomisConfigurationFile("enrolledOnARTAddedToEnrollmentTable",0,"alter table ENROLLMENT add column ENROLLEDONART VARCHAR(4)");
            executed=createNomisConfigurationFile("artFacilityAddedToEnrollmentTable",0,"alter table ENROLLMENT add column FACILITYID VARCHAR(12)");
            //createIndexesOnEnrollmentTable();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public void createIndexesOnEnrollmentTable()
    {
        try
        {
            util.executeSQLupdate("create index index_currentAgeUnit on ENROLLMENT(CURRENTAGEUNIT)");
            util.executeSQLupdate("create index index_vcincare on ENROLLMENT(ENROLLEDINCARE)");
            util.executeSQLupdate("create index index_vconart on ENROLLMENT(ENROLLEDONART)");
            util.executeSQLupdate("create index index_sch_enr on ENROLLMENT(SCHOOL_STATUS)");
            util.executeSQLupdate("create index index_hivstatus on ENROLLMENT(HIV_STATUS)");
            util.executeSQLupdate("create index index_birthcert on ENROLLMENT(BIRTH_CERTIFICATE)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public boolean addFieldsToServiceTable()
    {
        boolean executed=false;
        try
        {
            //System.err.println("Inside addFieldsToServiceTable() ");
            executed=createNomisConfigurationFile("hivStatusAddedToServiceTable",0,"alter table SERVICE add column HIVSTATUS VARCHAR(15)");
            executed=createNomisConfigurationFile("enrolledInCareAddedToServiceTable",0,"alter table SERVICE add column ENROLLEDINCARE VARCHAR(4)");
            executed=createNomisConfigurationFile("enrolledOnARTAddedToServiceTable",0,"alter table SERVICE add column ENROLLEDONART VARCHAR(4)");
            executed=createNomisConfigurationFile("artFacilityAddedToServiceTable",0,"alter table SERVICE add column FACILITYID VARCHAR(12)");
            executed=createNomisConfigurationFile("childAbusedAddedToServiceTable",0,"alter table SERVICE add column CHILDABUSED NUMERIC(1) NOT NULL DEFAULT 0");
            executed=createNomisConfigurationFile("childLinkedToGovtAddedToServiceTable",0,"alter table SERVICE add column CHILDLINKEDTOGOVT NUMERIC(1) NOT NULL DEFAULT 0");
            executed=createNomisConfigurationFile("childMissedSchoolAddedToServiceTable",0,"alter table SERVICE add column CHILDMISSEDSCHOOL VARCHAR(3)");
            executed=createNomisConfigurationFile("recordedByAddedToServiceTable",0,"alter table SERVICE add column RECORDEDBY VARCHAR(25)");
            executed=createNomisConfigurationFile("index_childabuse",0,"create index index_childabuse on SERVICE(CHILDABUSED)");
            executed=createNomisConfigurationFile("index_childlinkedtogovt",0,"create index index_childlinkedtogovt on SERVICE(CHILDLINKEDTOGOVT)");
            executed=createNomisConfigurationFile("idx_servicechildmissedsch",0,"create index idx_servicechildmissedsch on SERVICE(CHILDMISSEDSCHOOL)");
            executed=createNomisConfigurationFile("index_servhivstat",0,"create index index_servhivstat on SERVICE(HIVSTATUS)");
            executed=createNomisConfigurationFile("index_vcservcare",0,"create index index_vcservcare on SERVICE(ENROLLEDINCARE)");
            executed=createNomisConfigurationFile("index_vcservart",0,"create index index_vcservart on SERVICE(ENROLLEDONART)");
            
            //System.err.println("After execution in addFieldsToServiceTable() ");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addFieldsToHouseholdServiceTable()
    {
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("hivStatusAddedTohhServiceTable",0,"alter table HOUSEHOLDSERVICE add column HIVSTATUS VARCHAR(15)");
            executed=createNomisConfigurationFile("enrolledInCareAddedTohhServiceTable",0,"alter table HOUSEHOLDSERVICE add column ENROLLEDINCARE VARCHAR(4)");
            executed=createNomisConfigurationFile("enrolledOnARTAddedTohhServiceTable",0,"alter table HOUSEHOLDSERVICE add column ENROLLEDONART VARCHAR(4)");
            executed=createNomisConfigurationFile("artFacilityAddedTohhServiceTable",0,"alter table HOUSEHOLDSERVICE add column FACILITYID VARCHAR(12)");
            util.executeSQLupdate("create index index_hhservhivstat on HOUSEHOLDSERVICE(HIVSTATUS)");
            util.executeSQLupdate("create index index_hhservcare on HOUSEHOLDSERVICE(ENROLLEDINCARE)");
            util.executeSQLupdate("create index index_hhservart on HOUSEHOLDSERVICE(ENROLLEDONART)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addFieldsToCaregiverInfoTable()
    {
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("enrolledInCareAddedToCgInfoTable",0,"alter table CAREGIVERINFO add column ENROLLEDINCARE VARCHAR(4)");
            executed=createNomisConfigurationFile("enrolledOnARTAddedToCgInfoTable",0,"alter table CAREGIVERINFO add column ENROLLEDONART VARCHAR(4)");
            executed=createNomisConfigurationFile("artFacilityAddedToCgInfoTable",0,"alter table CAREGIVERINFO add column FACILITYID VARCHAR(12)");
            util.executeSQLupdate("create index index_cghivstat on CAREGIVERINFO(CGIVERHIVSTATUS)");
            util.executeSQLupdate("create index index_cgincare on CAREGIVERINFO(ENROLLEDINCARE)");
            util.executeSQLupdate("create index index_cgonart on CAREGIVERINFO(ENROLLEDONART)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addFieldsToHouseholdEnrollmentTable()
    {
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("hivStatusAddedTohheTable",0,"alter table HOUSEHOLDENROLLMENT add column HIVSTATUS VARCHAR(15)");
            executed=createNomisConfigurationFile("enrolledInCareAddedTohheTable",0,"alter table HOUSEHOLDENROLLMENT add column ENROLLEDINCARE VARCHAR(4)");
            executed=createNomisConfigurationFile("enrolledOnARTAddedTohheTable",0,"alter table HOUSEHOLDENROLLMENT add column ENROLLEDONART VARCHAR(4)");
            executed=createNomisConfigurationFile("artFacilityAddedTohheTable",0,"alter table HOUSEHOLDENROLLMENT add column FACILITYID VARCHAR(12)");
            util.executeSQLupdate("create index index_hhehivstat on HOUSEHOLDENROLLMENT(HIVSTATUS)");
            util.executeSQLupdate("create index index_hheincare on HOUSEHOLDENROLLMENT(ENROLLEDINCARE)");
            util.executeSQLupdate("create index index_hheonart on HOUSEHOLDENROLLMENT(ENROLLEDONART)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addFieldsToHouseholdFollowupAssessmentTable()
    {
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("hivStatusAddedTohhfaTable",0,"alter table HOUSEHOLDFOLLOWUPASSESSMENT add column HIVSTATUS VARCHAR(15)");
            executed=createNomisConfigurationFile("enrolledInCareAddedTohhfaTable",0,"alter table HOUSEHOLDFOLLOWUPASSESSMENT add column ENROLLEDINCARE VARCHAR(4)");
            executed=createNomisConfigurationFile("enrolledOnARTAddedTohhfaTable",0,"alter table HOUSEHOLDFOLLOWUPASSESSMENT add column ENROLLEDONART VARCHAR(4)");
            executed=createNomisConfigurationFile("artFacilityAddedTohhfaTable",0,"alter table HOUSEHOLDFOLLOWUPASSESSMENT add column FACILITYID VARCHAR(12)");
            util.executeSQLupdate("create index index_hhfahivstat on HOUSEHOLDFOLLOWUPASSESSMENT(HIVSTATUS)");
            util.executeSQLupdate("create index index_hhfaincare on HOUSEHOLDFOLLOWUPASSESSMENT(ENROLLEDINCARE)");
            util.executeSQLupdate("create index index_hhfaonart on HOUSEHOLDFOLLOWUPASSESSMENT(ENROLLEDONART)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    /*public boolean addCurrentAgeUnitFieldToEnrollmentTable()
    {
        String query="alter table ENROLLMENT add column CURRENTAGEUNIT VARCHAR(5)";
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("currentAgeUnitAddedToEnrollmentTable",0,query);
            util.executeSQLupdate("create index index_currentAgeUnit on ENROLLMENT(CURRENTAGEUNIT)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }*/
    public boolean addWithdrawnFromProgramFieldToEnrollmentTable()
    {
        String query="alter table ENROLLMENT add column WITHDRAWNFROMPROGRAM VARCHAR(4) NOT NULL DEFAULT 'No'";
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("WithdrawnFromProgramAddedToEnrollmentTable",0,query);
            util.executeSQLupdate("create index index_withdrawn on ENROLLMENT(WITHDRAWNFROMPROGRAM)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addLatitudeAndLongitudeFieldsToHouseholdEnrollmentTable()
    {
        String query="alter table HOUSEHOLDENROLLMENT add column LATITUDE DOUBLE NOT NULL DEFAULT 0.0";
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("LatitudeAddedToHHEnrollmentTable",0,query);
            query="alter table HOUSEHOLDENROLLMENT add column LONGITUDE DOUBLE NOT NULL DEFAULT 0.0";
            executed=createNomisConfigurationFile("LongitudeAddedToHHEnrollmentTable",0,query);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addWithdrawnFromProgramFieldToHouseholdEnrollmentTable()
    {
        String query="alter table HOUSEHOLDENROLLMENT add column WITHDRAWNFROMPROGRAM VARCHAR(4) NOT NULL DEFAULT 'No'";
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("WithdrawnFromProgramAddedToHHEnrollmentTable",0,query);
            util.executeSQLupdate("create index index_hhwithdrawn on HOUSEHOLDENROLLMENT(WITHDRAWNFROMPROGRAM)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addWithdrawnFromProgramFieldToCaregiverTable()
    {
        String query="alter table CAREGIVERINFO add column WITHDRAWNFROMPROGRAM VARCHAR(4) NOT NULL DEFAULT 'No'";
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("WithdrawnFromProgramAddedToCaregiverTable",0,query);
            util.executeSQLupdate("create index index_cgwithdrawn on CAREGIVERINFO(WITHDRAWNFROMPROGRAM)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return executed;
    }
    public boolean addCaregiverHivStatusFieldToCaregiverTable()
    {
        //String query="alter table CAREGIVERINFO add column CGIVERHIVSTATUS VARCHAR(25)";
        boolean executed=createNomisConfigurationFile("cgiverHivStatusAddedToCaregiverTable",0,"alter table CAREGIVERINFO add column CGIVERHIVSTATUS VARCHAR(25)");
        
        return executed;
    }
    public boolean addProgramAreaToCategoryCombinationTable()
    {
        String query="alter table CATEGORYCOMBINATION add column PROGRAMAREA VARCHAR(200)";
        boolean executed=createNomisConfigurationFile("programAreaAddedToCategoryCombinationTable",0,query);
        return executed;
    }
    public boolean addTypeToCategoryCombinationTable()
    {
        String query="alter table CATEGORYCOMBINATION add column TYPE VARCHAR(100)";
        boolean executed=createNomisConfigurationFile("typeAddedToCategoryCombinationTable",0,query);
        return executed;
    }
    public boolean addQueryToCategoryCombinationTable()
    {
        String query="alter table CATEGORYCOMBINATION add column QUERY VARCHAR(1000)";
        boolean executed=createNomisConfigurationFile("queryAddedToCategoryCombinationTable",0,query);
        return executed;
    }
    public boolean changeIdSizeForCategoryOptionTable()
    {
        String query="alter table CategoryOption alter column categoryOptionId VARCHAR(25)";
        boolean executed=createNomisConfigurationFile("categoryOptionIdSizeChanged",0,query);
        return executed;
    }
    public boolean changeIdSizeForCategoryCombinationTable()
    {
        String query="alter table CATEGORYCOMBINATION alter column CATEGORYCOMBOID VARCHAR(25)";
        boolean executed=createNomisConfigurationFile("categoryComboIdSizeChanged",0,query);
        return executed;
    }
    public boolean addFieldsToUsersTable()
    {
        boolean executed=false;
        if(tableExists("USERS"))
        {
            String query="alter table USERS add column CHANGEPWD VARCHAR(3) default 'no'";
            executed=createNomisConfigurationFile("changepwd",0,query);
            query="alter table USERS add column VIEWCLIENTDETAILS VARCHAR(3) default 'no'";
            executed=createNomisConfigurationFile("viewclientdetails",2,query);
        }
        return executed;
    }
    public boolean changeHivStatusFieldSizeInSurveyTable()
    {
         String query="alter table survey alter column hiv_status set data type varchar(20)";
        boolean executed=createNomisConfigurationFile("hiv_statusFieldSizedInSurveyTable",0,query);
        
        return executed;
    }
    public boolean changeLga_CodeFieldInCBOSETUPTable()
    {
         String query="alter table CBOSETUP alter column LGA_CODE NULL";
        boolean executed=createNomisConfigurationFile("lga_codeIncbosetup",0,query);
        return executed;
    }
    public boolean addCaregiverIdFieldToSurveyTable()
    {
        String query="alter table Survey add column CAREGIVERID VARCHAR(25)";
        boolean executed=createNomisConfigurationFile("caregiverIdAddedToSurveyTable",0,query);
        return executed;
    }
    public boolean addCaregiverHivStatusFieldToSurveyTable()
    {
        String query="alter table Survey add column CGIVERHIVSTATUS VARCHAR(25)";
        boolean executed=createNomisConfigurationFile("cgiverHivStatusAddedToSurveyTable",0,query);
        return executed;
    }
    public boolean addFieldsToSurveyTable()
    {
        boolean executed=false;
        try
        {
            executed=createNomisConfigurationFile("vcInCareAddedToSurveyTable",0,"alter table SURVEY add column VCENROLLEDINCARE VARCHAR(4)");
            executed=createNomisConfigurationFile("vcOnArtAddedToSurveyTable",0,"alter table SURVEY add column VCENROLLEDONART VARCHAR(4)");
            executed=createNomisConfigurationFile("vcFacilityIdAddedToSurveyTable",0,"alter table SURVEY add column VCFACILITYID VARCHAR(12)");
            executed=createNomisConfigurationFile("cgiverInCareAddedToSurveyTable",0,"alter table SURVEY add column CGIVERENROLLEDINCARE VARCHAR(4)");
            executed=createNomisConfigurationFile("cgiverOnArtAddedToSurveyTable",0,"alter table SURVEY add column CGIVERENROLLEDONART VARCHAR(4)");
            executed=createNomisConfigurationFile("cgiverFacilityIdAddedToSurveyTable",0,"alter table SURVEY add column CGIVERFACILITYID VARCHAR(12)");
        
            createNomisConfigurationFile("index_fuvcincare",0,"create index index_fuvcincare on SURVEY(VCENROLLEDINCARE)");
            createNomisConfigurationFile("index_fuvconart",0,"create index index_fuvconart on SURVEY(VCENROLLEDONART)");
            createNomisConfigurationFile("index_fuvcfacid",0,"create index index_fuvcfacid on SURVEY(VCFACILITYID)");
            createNomisConfigurationFile("index_fucgincare",0,"create index index_fucgincare on SURVEY(CGIVERENROLLEDINCARE)");
            createNomisConfigurationFile("index_fucgonart",0,"create index index_fucgonart on SURVEY(CGIVERENROLLEDONART)");
            createNomisConfigurationFile("index_fucgfacid",0,"create index index_fucgfacid on SURVEY(CGIVERFACILITYID)");
            
            createNomisConfigurationFile("idx_fuschstatus",0,"create index idx_fuschstatus on SURVEY(SCHOOL_STATUS)");
            createNomisConfigurationFile("idx_fuschname",0,"create index idx_fuschname on SURVEY(SCHOOL_NAME)");
            createNomisConfigurationFile("idx_fuvchivstatus",0,"create index idx_fuvchivstatus on SURVEY(HIV_STATUS)");
            createNomisConfigurationFile("idx_fucghivstatus",0,"create index idx_fucghivstatus on SURVEY(CGIVERHIVSTATUS)");
            createNomisConfigurationFile("idx_fubrtcert",0,"create index idx_fubrtcert on SURVEY(BIRTH_CERTIFICATE)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return executed;
    }
    public boolean addUpdatedClassFieldToSurveyTable()
    {
        String query="alter table Survey add column UPDATEDCLASS VARCHAR(25)";
        boolean executed=createNomisConfigurationFile("updatedclassAddedToSurveyTable",0,query);
        return executed;
    }
    public boolean changeStateFieldSizeInUsersTable()
    {
         String query="alter table Users alter column state set data type varchar(300)";
        boolean executed=createNomisConfigurationFile("stateFieldSizedInUsersTable",0,query);
        return executed;
    }

    public void addMaritalStatusToCaregiverInfoTable()
    {
        try
        {
            String addMaritalStatusInCaregiverInfoTableQuery="alter table CAREGIVERINFO add column CAREGIVERMARITALSTATUS varchar(25)";
            boolean executed=createNomisConfigurationFile("addMaritalStatusInCaregiverInfoTable",0,addMaritalStatusInCaregiverInfoTableQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addDateOfEnrollmentToCaregiverInfoTable()
    {
        try
        {
            String addDateOfEnrollmentInCaregiverInfoTableQuery="alter table CAREGIVERINFO add column DATEOFENROLLMENT DATE";
            boolean executed=createNomisConfigurationFile("addDateOfEnrollmentInCaregiverInfoTable",0,addDateOfEnrollmentInCaregiverInfoTableQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addHouseholdColumnToCaregiverTable()
    {
        try
        {
            String householdColumnToCaregiverQuery="alter table CAREGIVERINFO add column householdhead varchar(3) not null default '0'";
            boolean executed=createNomisConfigurationFile("householdColumnToCaregiver",0,householdColumnToCaregiverQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addIndexToCaregiverInfoTable()
    {
        try
        {
            util.executeSQLupdate("create index index_cgiverhhUniqueId on caregiverinfo(hhUniqueId)");
            util.executeSQLupdate("create index index_cgivermarital on caregiverinfo(caregivermaritalstatus)");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addIndexToHHVulnerabilityAssessmentTable()
    {
        try
        {
            util.executeSQLupdate("create index index_hhvahhUniqueId on HHVULNERABILITYASSESSMENT(hhUniqueId)");
            util.executeSQLupdate("create index index_hhvadateofassessment on HHVULNERABILITYASSESSMENT(dateofassessment)");
            util.executeSQLupdate("create index index_hhvahhheadship on HHVULNERABILITYASSESSMENT(hhheadship)");
            util.executeSQLupdate("create index index_hhvahealth on HHVULNERABILITYASSESSMENT(health)");
            util.executeSQLupdate("create index index_hhvaeducation on HHVULNERABILITYASSESSMENT(educationlevel)");
            util.executeSQLupdate("create index index_hhvahousing on HHVULNERABILITYASSESSMENT(shelterandhousing)");
            util.executeSQLupdate("create index index_hhvafood on HHVULNERABILITYASSESSMENT(foodsecurityandnutrition)");
            util.executeSQLupdate("create index index_hhvalivelihood on HHVULNERABILITYASSESSMENT(meansoflivelihood)");
            util.executeSQLupdate("create index index_hhvahhincome on HHVULNERABILITYASSESSMENT(hhincome)");
            util.executeSQLupdate("create index index_hhvanameofassessor on HHVULNERABILITYASSESSMENT(nameofassessor)");
            util.executeSQLupdate("create index index_hhvadesignationofassessor on HHVULNERABILITYASSESSMENT(designationofassessor)");
            util.executeSQLupdate("create index index_recordedby on HHVULNERABILITYASSESSMENT(recordedby)");
            util.executeSQLupdate("create index index_dateofentry on HHVULNERABILITYASSESSMENT(dateofentry)");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addIndexToServiceTable()
    {
        try
        {
            util.executeSQLupdate("create index index_servicedateofentry on service(date_entry)");
            util.executeSQLupdate("create index index_servicesurveyno on service(survey_number)");
            util.executeSQLupdate("create index index_servicesreferred on service(servicesreferred)");
            util.executeSQLupdate("create index index_servicesnumberofservices on service(numberofservices)");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addServiceRecipientTypeToHouseholdServiceTable()
    {
        try
        {
            String serviceRecipientTypeInHhsQuery="alter table HOUSEHOLDSERVICE add column ServiceRecipientType varchar(25)";
            boolean executed=createNomisConfigurationFile("serviceRecipientTypeInHhs",0,serviceRecipientTypeInHhsQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addIndexToHouseholdServiceTable()
    {
        try
        {
            util.executeSQLupdate("create index index_hhsUniqueId on householdservice(hhUniqueId)");
            util.executeSQLupdate("create index index_hhsservicedate on householdservice(servicedate)");
            util.executeSQLupdate("create index index_hhscgiverId on householdservice(caregiverid)");
            util.executeSQLupdate("create index index_hhseconstr on householdservice(economicstrengthening)");
            util.executeSQLupdate("create index index_hhseducation on householdservice(education)");
            util.executeSQLupdate("create index index_hhshealth on householdservice(health)");
            util.executeSQLupdate("create index index_hhsnutrition on householdservice(nutrition)");
            util.executeSQLupdate("create index index_hhsprotection on householdservice(protection)");
            util.executeSQLupdate("create index index_hhspsycho on householdservice(psychosocialsupport)");
            util.executeSQLupdate("create index index_hhsshelter on householdservice(shelterandcare)");
            util.executeSQLupdate("create index index_hhsserviceno on householdservice(serviceno)");
            util.executeSQLupdate("create index index_hhsdateofentry on householdservice(dateofentry)");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addIndexToHouseholdUniqueServiceReportTable()
    {
        try
        {
            util.executeSQLupdate("create index index_hhusrpUniqueId on HOUSEHOLDUNIQUESERVICEREPORT(hhUniqueId)");
            util.executeSQLupdate("create index index_hhusrpservicedate on HOUSEHOLDUNIQUESERVICEREPORT(servicedate)");
            util.executeSQLupdate("create index index_hhusrpcgiverId on HOUSEHOLDUNIQUESERVICEREPORT(caregiverid)");
            util.executeSQLupdate("create index index_hhusrpeconstr on HOUSEHOLDUNIQUESERVICEREPORT(economicstrengthening)");
            util.executeSQLupdate("create index index_hhusrpeducation on HOUSEHOLDUNIQUESERVICEREPORT(education)");
            util.executeSQLupdate("create index index_hhusrphealth on HOUSEHOLDUNIQUESERVICEREPORT(health)");
            util.executeSQLupdate("create index index_hhusrpnutrition on HOUSEHOLDUNIQUESERVICEREPORT(nutrition)");
            util.executeSQLupdate("create index index_hhusrpprotection on HOUSEHOLDUNIQUESERVICEREPORT(protection)");
            util.executeSQLupdate("create index index_hhusrppsycho on HOUSEHOLDUNIQUESERVICEREPORT(psychosocialsupport)");
            util.executeSQLupdate("create index index_hhusrpshelter on HOUSEHOLDUNIQUESERVICEREPORT(shelterandcare)");
            util.executeSQLupdate("create index index_hhusrpserviceno on HOUSEHOLDUNIQUESERVICEREPORT(serviceno)");
            util.executeSQLupdate("create index index_hhusrpdateofentry on HOUSEHOLDUNIQUESERVICEREPORT(dateofentry)");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }//
    public void addIndexToHouseholdFollowupTable()
    {
        try
        {
            util.executeSQLupdate("create index index_hhfUniqueId on HouseholdFollowupAssessment(hhUniqueId)");
            util.executeSQLupdate("create index index_hhfdateofassessment on HouseholdFollowupAssessment(dateofassessment)");
            util.executeSQLupdate("create index index_hhfdatemod on HouseholdFollowupAssessment(datemodified)");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addIndexToAssessmentTable()
    {
        try
        {
            util.executeSQLupdate("create index index_assessmentcsi_date on Assessment(csi_date)");
            util.executeSQLupdate("create index index_assessmentdateofentry on Assessment(dateofentry)");
            util.executeSQLupdate("create index index_assessmentsurvey_number on Assessment(survey_number)");
            util.executeSQLupdate("create index index_assessmenttotalscore on Assessment(totalcsiscore)");
            util.executeSQLupdate("create index index_assessmentcsi_factor1 on Assessment(csi_factor1)");
            util.executeSQLupdate("create index index_assessmentcsi_factor2 on Assessment(csi_factor2)");
            util.executeSQLupdate("create index index_assessmentcsi_factor3 on Assessment(csi_factor3)");
            util.executeSQLupdate("create index index_assessmentcsi_factor4 on Assessment(csi_factor4)");
            util.executeSQLupdate("create index index_assessmentcsi_factor5 on Assessment(csi_factor5)");
            util.executeSQLupdate("create index index_assessmentcsi_factor6 on Assessment(csi_factor6)");
            util.executeSQLupdate("create index index_assessmentcsi_factor7 on Assessment(csi_factor7)");
            util.executeSQLupdate("create index index_assessmentcsi_factor8 on Assessment(csi_factor8)");
            util.executeSQLupdate("create index index_assessmentcsi_factor9 on Assessment(csi_factor9)");
            util.executeSQLupdate("create index index_assessmentcsi_factor10 on Assessment(csi_factor10)");
            util.executeSQLupdate("create index index_assessmentcsi_factor11 on Assessment(csi_factor11)");
            util.executeSQLupdate("create index index_assessmentcsi_factor12 on Assessment(csi_factor12)");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    public void addIndexToNutritionalAssessmentTable()
    {
        try
        {
            util.executeSQLupdate("create index index_naovcid on NutritionAssessment(ovcid)");
            util.executeSQLupdate("create index index_nadateofassessment on NutritionAssessment(dateofassessment)");
            util.executeSQLupdate("create index index_naassessmentno on NutritionAssessment(assessmentno)");
            util.executeSQLupdate("create index index_nadatemodified on NutritionAssessment(datemodified)");
            util.executeSQLupdate("create index index_nadateoflastweight on NutritionAssessment(dateoflastweight)");
            util.executeSQLupdate("create index index_nanutritionalstatus on NutritionAssessment(nutritionalstatus)");
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addCaregiverIdToHouseholdServiceTable()
    {
        try
        {
            String caregiverIdInHhsQuery="alter table HOUSEHOLDSERVICE add column CAREGIVERID varchar(25)";
            boolean executed=createNomisConfigurationFile("caregiverIdInHhs",0,caregiverIdInHhsQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addHhUniqueIdToEnrollmentTable()
    {
        try
        {
            String hhUniqueIdQuery="alter table Enrollment add column hhUniqueId varchar(25)";
            boolean executed=createNomisConfigurationFile("hhUniqueId",0,hhUniqueIdQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void createHHGENDERFieldInHouseholdEnrollmentTable()
    {
        try
        {
            String hhGender_codeQuery="alter table householdEnrollment add column hhgender varchar(10)";
            boolean executed=createNomisConfigurationFile("hhgenderAddedToHHEnrollment",0,hhGender_codeQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        //columnExists("householdEnrollment");
    }
    public void setHHGENDERFieldInHouseholdEnrollmentTable()
    {
        try
        {
            String hhGender_codeQuery="alter table householdEnrollment alter column hhgender null";
            boolean executed=createNomisConfigurationFile("hhgender_null",0,hhGender_codeQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void setStateLgaCBOWardFieldsInEnrollmentTable()
    {
        try
        {
            String state_codeQuery="alter table enrollment alter column state_code null";
            String lga_codeQuery="alter table enrollment alter column lga_code null";
            String cbo_codeQuery="alter table enrollment alter column cbo_code null";
            String ward_codeQuery="alter table enrollment alter column ward_code null";
            
            boolean executed=createNomisConfigurationFile("state_code",0,state_codeQuery);
            executed=createNomisConfigurationFile("lga_code",0,lga_codeQuery);
            executed=createNomisConfigurationFile("cbo_code",0,cbo_codeQuery);
            executed=createNomisConfigurationFile("ward_code",0,ward_codeQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }//
    public void addTotalCsiScoreToAssessmentTable()
    {
        try
        {
            String totalCsiScoreQuery="alter table Assessment add column TOTALCSISCORE numeric(3) not null default 0";
            boolean executed=createNomisConfigurationFile("totalCsiScore",0,totalCsiScoreQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addNumberOfServicesToHhServiceTable()
    {
        try
        {
            String numberOfServicesQuery="alter table HOUSEHOLDSERVICE add column numberOfServices numeric(3) default 0";
            boolean executed=createNomisConfigurationFile("hhNumberOfServices",0,numberOfServicesQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addNumberOfServicesToServiceTable()
    {
        try
        {
            String numberOfServicesQuery="alter table Service add column numberOfServices numeric(3) default 0";
            boolean executed=createNomisConfigurationFile("numberOfServices",0,numberOfServicesQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addCaregiverIdToEnrollmentTable()
    {
        try
        {
            String caregiverIdQuery="alter table Enrollment add column caregiverId varchar(25)";
            boolean executed=createNomisConfigurationFile("caregiverId",0,caregiverIdQuery);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public void addTypeColumnToWithdrawalTable()
    {
        try
        {
            createNomisConfigurationFile("withdrwalType",0,"alter table withdrawal add column type varchar(25)");
            createNomisConfigurationFile("idx_dateOfWithdrawal",0,"create index idx_dateOfWithdrawal on withdrawal(date_Of_Withdrawal)");
            createNomisConfigurationFile("idx_reasonWithdrawal",0,"create index idx_reasonWithdrawal on withdrawal(Reason_Withdrawal)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void addDefaultUser()
    {
        try
        {
            util.executeSQLupdate("insert into users values('admin','admin','default','Default','Account','FCT')");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void addCurrentAgeColumn()
    {
        try
        {
        util.executeSQLupdate("alter table enrollment add column currentAge integer not null default 0");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void addTypeColumnToOvcReferral()
    {
        try
        {
        util.executeSQLupdate("alter table ovcreferral add column type varchar(15)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateCboSetup()
    {
        try
        {
            util.executeSQLupdate("alter table cbosetup alter column state_code set data type varchar(300)");
            util.executeSQLupdate("alter table cbosetup alter column lga_code set data type varchar(300)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateEnrollment()
    {
        try
        {
            util.executeSQLupdate("alter table enrollment alter column address set data type varchar(300)");
            util.executeSQLupdate("create index index_dateOfEnrollment on enrollment(date_enrollment)");           
            util.executeSQLupdate("create index index_age on enrollment(age)");
            util.executeSQLupdate("create index index_ageunit on enrollment(ageunit)");
            util.executeSQLupdate("create index index_hhUniqueId on enrollment(hhUniqueId)");
            util.executeSQLupdate("create index index_ovccurrentage on enrollment(currentage)");
            util.executeSQLupdate("create index index_caregiverid on enrollment(caregiverid)");
            util.executeSQLupdate("create index index_ovcgender on enrollment(gender)");
            util.executeSQLupdate("create index index_ovcothername on ENROLLMENT(OTHERNAMES)");
            util.executeSQLupdate("create index index_ovcsurname on ENROLLMENT(SURNAME)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateHouseholdEnrollment()
    {
        try
        {
            util.executeSQLupdate("create index index_statecode on HouseholdEnrollment(statecode)");
            util.executeSQLupdate("create index index_lgacode on HouseholdEnrollment(lgacode)");
            util.executeSQLupdate("create index index_orgcode on HouseholdEnrollment(orgcode)");
            util.executeSQLupdate("create index index_communitycode on HouseholdEnrollment(communitycode)");
            util.executeSQLupdate("create index index_currentage on HouseholdEnrollment(currentage)");
            util.executeSQLupdate("create index index_partnercode on HouseholdEnrollment(partnercode)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /*public boolean dbObjectExist(String objectName)
    {
        List list=util.createSqlQuery("SELECT t.tablename FROM sys.systables t WHERE t.tablename = '"+objectName+"'");
        if(list !=null && !list.isEmpty())
        return true;
        return false;
    }*/
    
    public void updateEnrollmentForCompatibility()
    {
        try
        {
        System.err.println("inside updateEnrollmentForCompatibility()");
        util.executeSQLupdate("alter table enrollment alter column BIRTH_CERTIFICATE set data type varchar(6)");
        util.executeSQLupdate("alter table enrollment alter column SCHOOL_STATUS set data type varchar(6)");
        util.executeSQLupdate("alter table enrollment alter column ORPHANAGE set data type varchar(6)");

        }
        catch(Exception ex)
        {
            System.err.println("Could not update enrollment, the specified columns may not exist");
        }
    }
    public void alterOrgRecordsTable()
    {
        try
        {
        util.executeSQLupdate("alter table orgrecords drop column lga_code");
        util.executeSQLupdate("alter table orgrecords add column lga_code varchar(300)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateService()
    {
        try
        {
            util.executeSQLupdate("create index service_ovcid on service(ovc_id)");
            util.executeSQLupdate("create index index_servicedate on service(servicedate)");
            util.executeSQLupdate("create index index_psychosocial on service(service_accessed1)");
            util.executeSQLupdate("create index index_nutrition on service(service_accessed2)");
            util.executeSQLupdate("create index index_health on service(service_accessed3)");
            util.executeSQLupdate("create index index_education on service(service_accessed4)");
            util.executeSQLupdate("create index index_protection on service(service_accessed5)");
            util.executeSQLupdate("create index index_shelter on service(service_accessed6)");
            util.executeSQLupdate("create index index_economicStrenghtening on service(service_accessed7)");
            util.executeSQLupdate("create index index_servicesreferred on service(SERVICESREFERRED)");
            util.executeSQLupdate("create index assessment_ovcid on assessment(ovc_id)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateServiceReport()
    {
        try
        {
            util.executeSQLupdate("create index index_sr_ovcid on servicereport(ovc_id)");
            util.executeSQLupdate("create index index_sr_servicedate on servicereport(servicedate)");
            util.executeSQLupdate("create index index_sr_psychosocial on servicereport(service_accessed1)");
            util.executeSQLupdate("create index index_sr_nutrition on servicereport(service_accessed2)");
            util.executeSQLupdate("create index index_sr_health on servicereport(service_accessed3)");
            util.executeSQLupdate("create index index_sr_education on servicereport(service_accessed4)");
            util.executeSQLupdate("create index index_sr_protection on servicereport(service_accessed5)");
            util.executeSQLupdate("create index index_sr_shelter on servicereport(service_accessed6)");
            util.executeSQLupdate("create index index_sr_economicStrenghtening on servicereport(service_accessed7)");
            util.executeSQLupdate("create index index_sr_servicesreferred on servicereport(SERVICESREFERRED)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateSpecificServiceReport()
    {
        try
        {
            util.executeSQLupdate("create index index_ssr_ovcid on specificservicereport(ovc_id)");
            util.executeSQLupdate("create index index_ssr_servicedate on specificservicereport(servicedate)");
            util.executeSQLupdate("create index index_ssr_psychosocial on specificservicereport(PSYCHOSOCIALSUPPORT)");
            util.executeSQLupdate("create index index_ssr_nutrition on specificservicereport(NUTRITIONALSERVICES)");
            util.executeSQLupdate("create index index_ssr_health on specificservicereport(HEALTHSERVICES)");
            util.executeSQLupdate("create index index_ssr_education on specificservicereport(EDUCATIONALSERVICES)");
            util.executeSQLupdate("create index index_ssr_protection on specificservicereport(PROTECTIONSERVICES)");
            util.executeSQLupdate("create index index_ssr_shelter on specificservicereport(SHELTERSERVICES)");
            util.executeSQLupdate("create index index_ssr_economicStrenghtening on specificservicereport(ECONOMICSERVICES)");
            util.executeSQLupdate("create index index_ssr_servicesreferred on specificservicereport(SERVICESREFERRED)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
 
 public void runDatabaseUpdateForVersion21()
 {
     AppUtility appUtils=new AppUtility();
     int version= appUtils.getVersion();
    
    //appUtil.moveAllImportFilesToDoneDirectory();
    
    addPartnerCodeToNomisUserTable();
    createProcessMonitorTable();
    createOptionsManagerTable();
    addEnrollmentIdsToBeneficiariesTable();
    createCaregiverExpenditureAndSchAttendanceTable();
    createCareAndSupportTable();
    //addCategoryToNutritionAssessmentTable();
    createGenderNormCohortAttendanceTable();
    createCarePlanAchievementTable();
    
    changeHivStatusFieldSizeInHouseholdEnrollmentTable();
    
    //addCurrentAgeUnitFieldToEnrollmentTable();
    createHivRiskAssessmentChecklistTable();
    //createDxCategoryCombinationTable();
    //createDxDataElementTable();
    //createDxOrganizationUnitTable();
    //createDhisInstanceTable();
    
    createUserGroupTable();
    //updateUserInfo();
    //createOrganizationUnitHierarchyTable();
    createReferralDirectoryTable();
    createSiteTransitionTable();
    createBirthRegistrationTable();
    createGraduationChecklistTable();
    createSpecificServiceReportTable();
    createCaregiverTbHivScreeningChecklistTable();
    createOvcTbHivScreeningChecklistTable();
    createDeletedRecordTable();
    
    createHivStatusUpdateTable();
    
    createDatabaseImportTrackerTable();
    
    createReportQueryManagerTable();
    createOrganizationUnitGroupAssignmentTable();
    changeOrgunitIdSizeInOrganizationUnitGroupAssignmentTable();
    createIndexesOnOrganizationUnitGroupAssignmentTable();
    createOrganizationUnitGroupTable();
    createDataValueTable();
    createIndicatorTable();
    createUmbrellaOrganizationTable();
    createHouseholdFollowupAssessmentTable();
    createTrainingParticipantTable();
    createTrainingTable();
    createTrainingCategoryTable();
    createTrainingDesignationTable();
    createTrainingStatusSetupTable();
    createDhisDataExportTable(true);
    createCustomDataElementTable();
    createCategoryCombinationTable();
    createCategoryTable();
    createCategoryOptionTable();
    createReportQueryTable();
    createTrainingInformationSetupTable();
    createNutritionAssessmentTable();
    
    
    //addFieldsToUsersTable();
    createUserRightsTable();
    //createUserRolesTable();
    createHouseholdEnrollmentTable();
    createHouseholdVulnerabilityAssessmentTable();
    createCaregiverInfoTable();
    
    
    changeHivStatusFieldSizeInSurveyTable();
    changeStateFieldSizeInUsersTable();
    addNewUniqueIdColumnToDeletedRecordTable();
    
    createHHGENDERFieldInHouseholdEnrollmentTable();
    setHHGENDERFieldInHouseholdEnrollmentTable();
    
    createIndexesOnSchoolTable();
    createIndexesOnOVCReferralTable();
    createIndexesOnSurveyTable();
    addWithdrawnFromProgramFieldToEnrollmentTable();
    addWithdrawnFromProgramFieldToHouseholdEnrollmentTable();
    addWithdrawnFromProgramFieldToCaregiverTable();
    changeLga_CodeFieldInCBOSETUPTable();
    addFieldsToHouseholdFollowupAssessmentTable();
    addFieldsToHouseholdEnrollmentTable();
    addFieldsToCaregiverInfoTable();
    addFieldsToHouseholdServiceTable();
    addFieldsToServiceTable();
    addFieldsToEnrollmentTable();
    addLatitudeAndLongitudeFieldsToHouseholdEnrollmentTable();
    addFieldsToSurveyTable();
    addRevisedFieldsToHouseholdFollowupTable();
    addRevisedFieldsToHheTable();
    
    addAutogeneratedToLgaTable();
    addVulnerabilityAssessmentFieldsToHheTable();
    addVulnerabilityAssessmentFieldsToHouseholdFollowupTable();
    addFieldsToUsersTable();
    
    addFieldsToNomisUserTable();
    addClientEnrolledOnARTToHivStatusUpdateTable();
    addColumnsToDatabaseImportTrackerTable();
    addColumnsToNutritionAssessmentTable();
    addIndexToNutritionalAssessmentTable();
    addIndexToHHVulnerabilityAssessmentTable();
    addIndexToServiceTable();
    addCaregiverHivStatusFieldToCaregiverTable();
    addCaregiverHivStatusFieldToSurveyTable();
    addLatitudeToOrgRecordsTable();
    addLongitudeToOrgRecordsTable();
    addCaregiverIdFieldToSurveyTable();
    addUpdatedClassFieldToSurveyTable();
    addMaritalStatusToCaregiverInfoTable();
    addDateOfEnrollmentToCaregiverInfoTable();
    addServiceRecipientTypeToHouseholdServiceTable();
    addCaregiverIdToHouseholdServiceTable();
    addIndexToHouseholdFollowupTable();
    addIndexToAssessmentTable();
    addTotalCsiScoreToAssessmentTable();
    setStateLgaCBOWardFieldsInEnrollmentTable();
    addNumberOfServicesToHhServiceTable();
    addNumberOfServicesToServiceTable();
    addHhUniqueIdToEnrollmentTable();
    addCaregiverIdToEnrollmentTable();
    
    updateEnrollment();
    updateHouseholdEnrollment();
    updateService();
    updateServiceReport();
    addIndexToHouseholdServiceTable();
    addIndexToCaregiverInfoTable();
    addHouseholdColumnToCaregiverTable();
    changeIdSizeForCategoryCombinationTable();
    addProgramAreaToCategoryCombinationTable();
    changeIdSizeForCategoryOptionTable();
    addTypeToCategoryCombinationTable();
    addQueryToCategoryCombinationTable();
    createIndexesOnEnrollmentTable();
    executeAdhocTasks();
    createSchAttendanceTrackerTable();
    //createOvcSchAttendanceTable();
    createOvcSchoolStatusTable();
    //addMarkedForDeleteColumnToTables();
    //modifyMarkedForDeleteColumns();
    createCustomIndicatorsReportTable();
    addFieldsToHivRiskAssessmentChecklistTable();
    addLastServiceDateToEnrollmentTable();
    //addPartnerCodeToNomisUserTable();
    createImportFileUploadTable();
    addTypeColumnToWithdrawalTable();
    createDatimReportTable();
    //addCurrentStatusCodeToEnrollmentTable();
    addFieldsToHivStatusUpdateTableTable();
    //appUtils.writeCurrentVersion();
 }
 public void executeAdhocTasks()
 {
     
     //if(tableExists("SCHOOLATTENDANCE"))
     //SchoolAttendanceManager.moveSchoolAttendanceRecordsFromCaregiverExpenditureRecord();
 }
 
 public String getTableIdFromTableName(String tableName)
 {
     String retTableId=null;
     Character tableId=null;
     String columnName=null;
     String query="select TABLEID from SYS.SYSTABLES WHERE TABLENAME='"+tableName+"'";
     List list=util.execSqlQuery(query);
     if(list !=null && !list.isEmpty())
     {
         for(int i=0; i<list.size(); i++)
         {
                      
             tableId=(Character)list.get(i);
             System.err.println("tableId.charValue() is "+tableId.charValue());
             System.err.println("tableId is "+tableId);
             query="select COLUMNNAME from SYS.SYSCOLUMNS WHERE COLUMNNAME='PARTNERCODE' and REFERENCEID='"+tableId+"'";
             List list2=util.execSqlQuery(query);
             if(list2 !=null && !list2.isEmpty())
             {
                 columnName=(String)list2.get(0);
                 System.err.println("columnName is "+columnName);
             }
         }
     }
     return retTableId;
 }
  /*public boolean checkTableId(String tableName)
    {
        tableName=tableName.toUpperCase();
        System.err.println("Inside checkTableId");
        boolean columnExists=false;
        String query="SELECT * FROM SYS.SYSTABLES WHERE UPPER(TABLENAME)='"+tableName+"'";
        List list=util.execSqlQuery(query);
        System.err.println(list);
        if(list !=null)
        {
            for(int i=0; i<list.size(); i++)
            {
                Object[] obj=(Object[])list.get(i);
                for(int j=0; i<obj.length; j++)
                {
                    System.err.println(obj[j]);
                }
            }
            
            columnExists=false;
        }
        return columnExists;
    }*/
    /*public boolean columnExists(String tableName)
    {
        tableName=tableName.toUpperCase();
        System.err.println("Inside columnExists");
        boolean columnExists=false;
        String query="SELECT TABLEID FROM SYS.SYSTABLES WHERE UPPER(TABLENAME)='"+tableName+"'";
        List list=util.execSqlQuery(query);
        System.err.println(list);
        if(list !=null)
        {
            for(int i=0; i<list.size(); i++)
            {
                System.err.println(list.get(i).toString());
            }
            System.err.println("Inside columnExists, list is not null");
            if(!list.isEmpty())
            {
                System.err.println("Inside columnExists, list is not empty");
                String tableId=list.get(0).toString();
                query="SELECT COLUMNNAME FROM SYS.SYSCOLUMNS WHERE REFERENCEID='"+tableId+"'";
                list=util.execSqlQuery(query);
                if(list !=null && !list.isEmpty())
                {
                    String columnName=list.get(0).toString();
                    System.err.println("Column name is "+columnName);
                    columnExists=true;
                }
                System.err.println("Table reference is "+tableId);
            }
            else
            columnExists=false;
        }
        return columnExists;
    }*/
    /*public void checkTable()
    {
        try
        {
            Connection dbConnection = DriverManager.getConnection("jdbc:derby:MyDb;create=true");
            DatabaseMetaData dbmd = dbConnection.getMetaData();
            
        }
        catch(Exception ex)
        {
            
        }
    }*/
}
