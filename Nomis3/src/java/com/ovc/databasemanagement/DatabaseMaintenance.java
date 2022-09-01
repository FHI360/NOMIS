/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ovc.databasemanagement;


import com.nomis.ovc.dao.DaoUtility;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class DatabaseMaintenance 
{
    DaoUtility util=new DaoUtility();
    public String defragmentTable(List tableNames,int sequence)
    {
        List lockedTableList=new ArrayList();
        String message="";
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
                System.err.println("tableName is "+tableName);
                if(tableName==null)
                continue;
                if(!tableIsLocked(tableName))
                {
                    sql="CALL SYSCS_UTIL.SYSCS_COMPRESS_TABLE('APP','"+tableName+"',"+sequence+")";
                    System.err.println("tableName after is "+sql);
                    util.executeSQLupdate(sql);
                }
                else
                {
                    lockedTableList.add(tableName);
                }
            }
        }
        catch(Exception ex)
        {
            return "<label style='color: red'>Exception: "+ex.getMessage()+"</label>";
        }
        if(lockedTableList !=null && !lockedTableList.isEmpty())
        {
          for(int i=0; i<lockedTableList.size(); i++)
          {
             message=message+lockedTableList.get(i).toString()+" "; 
          }
          message="Could not defragment tables "+message;
        }
        else
        message="Database defragmentation complete";
        return "<label style='color: green'>"+message+"</label>";
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
    public List getTableList()
    {
        List tableList=new ArrayList();
        DatabaseTable dbtable=null;
                       
        dbtable=new DatabaseTable();
        dbtable.setCode("hhe");
        dbtable.setName("Household Registration");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("ahm");
        dbtable.setName("Adult Household members");
        tableList.add(dbtable);
        
        /*dbtable=new DatabaseTable();
        dbtable.setCode("hhe");
        dbtable.setName("Household Registration");
        tableList.add(dbtable);*/
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hva");
        dbtable.setName("Household Vulnerability assessment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("rhva");
        dbtable.setName("Revised Household Vulnerability assessment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hhcp");
        dbtable.setName("Household care plan");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("vce");
        dbtable.setName("Child Enrolment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("vcs");
        dbtable.setName("Child Services");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hhs");
        dbtable.setName("Household Service");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("cir");
        dbtable.setName("Custom Indicators");
        tableList.add(dbtable);
        /*dbtable=new DatabaseTable();
        dbtable.setCode("gbve");
        dbtable.setName("GBV Enrollment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("gbvs");
        dbtable.setName("GBV service");
        tableList.add(dbtable);*/
        dbtable=new DatabaseTable();
        dbtable.setCode("casc");
        dbtable.setName("Care and support");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("caef");
        dbtable.setName("Caregiver access to emergency fund");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("bsu");
        dbtable.setName("OVC status update");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("cpac");
        dbtable.setName("Careplan achievement checklist");
        tableList.add(dbtable);
        dbtable=new DatabaseTable();
        dbtable.setCode("cep");
        dbtable.setName("Child educational performance");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hhr");
        dbtable.setName("Referral records");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hrac");
        dbtable.setName("HIV Risk assessment");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("nuta");
        dbtable.setName("Nutrition assessment records");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("esh");
        dbtable.setName("Enrollment status history");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("hsh");
        dbtable.setName("HIV status history");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("qst");
        dbtable.setName("Quarterly status table");
        tableList.add(dbtable);
        dbtable=new DatabaseTable();
        dbtable.setCode("qsrt");
        dbtable.setName("Quarterly service table");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("oru");
        dbtable.setName("Organization Unit table");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("ouh");
        dbtable.setName("Organization unit Hierachy");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("ref");
        dbtable.setName("Referral facilities");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("sch");
        dbtable.setName("School records");
        tableList.add(dbtable);
        
        dbtable=new DatabaseTable();
        dbtable.setCode("uat");
        dbtable.setName("User activities");
        tableList.add(dbtable);
        
        return tableList;
    }
    private String getRealTableName(String code)
    {
        String tableName=null;
        if(code.equalsIgnoreCase("vce"))
        tableName="CHILDENROLLMENT";
        else if(code.equalsIgnoreCase("vcs"))
        tableName="CHILDSERVICE";
        else if(code.equalsIgnoreCase("hhe"))
        tableName="HOUSEHOLDENROLLMENT";
        if(code.equalsIgnoreCase("hva"))
        tableName="HOUSEHOLDVULNERABILITYASSESSMENT";
        if(code.equalsIgnoreCase("rhva"))
        tableName="REVISEDHOUSEHOLDASSESSMENT";
        else if(code.equalsIgnoreCase("hhs"))
        tableName="HOUSEHOLDSERVICE";
        else if(code.equalsIgnoreCase("ahm"))
        tableName="ADULTHOUSEHOLDMEMBER";
        else if(code.equalsIgnoreCase("hrac"))
        tableName="HIVRISKASSESSMENT";
        //
        else if(code.equalsIgnoreCase("caef"))
        tableName="CAREGIVERACCESSTOEMERGENCYFUND";
        else if(code.equalsIgnoreCase("casc"))
        tableName="CAREANDSUPPORTCHECKLIST";
        else if(code.equalsIgnoreCase("bsu"))
        tableName="BENEFICIARYSTATUSUPDATE";
        
        else if(code.equalsIgnoreCase("cpac"))
        tableName="CAREPLANACHIEVEMENTCHECKLIST";
        else if(code.equalsIgnoreCase("cep"))
        tableName="CHILDEDUCATIONPERFORMANCEASSESSMENT";
        else if(code.equalsIgnoreCase("esh"))
        tableName="ENROLLMENTSTATUSHISTORY";
        else if(code.equalsIgnoreCase("hsh"))
        tableName="HIVSTATUSHISTORY";
        else if(code.equalsIgnoreCase("qst"))
        tableName="QUARTERLYSTATUSTRACKER";
        else if(code.equalsIgnoreCase("oru"))
        tableName="ORGANIZATIONUNIT";
        else if(code.equalsIgnoreCase("ouh"))
        tableName="ORGANIZATIONUNITHIERARCHY";
        else if(code.equalsIgnoreCase("cir"))
        tableName="CUSTOMINDICATORSREPORT";
        else if(code.equalsIgnoreCase("hhcp"))
        tableName="HOUSEHOLDCAREPLAN";
        else if(code.equalsIgnoreCase("hhr"))
        tableName="HOUSEHOLDREFERRAL";
        else if(code.equalsIgnoreCase("nuta"))
        tableName="NUTRITIONASSESSMENT";
        else if(code.equalsIgnoreCase("ref"))
        tableName="REFERRALDIRECTORY";
        else if(code.equalsIgnoreCase("sch"))
        tableName="SCHOOL";
        else if(code.equalsIgnoreCase("uat"))
        tableName="USERACTIVITYTRACKER";
        return tableName;
    }
    public boolean createNomisConfigurationFile(String tagName,int tagValue,String query)
    {
        PomisConfiguration nconfig=new PomisConfiguration();
        List tagNames=nconfig.getTagNames();
        List tagValues=nconfig.getTagValues();
        BackgroundProcessManager bpm=new BackgroundProcessManager();
        if(!bpm.backgroundProcessExecuted(tagName,tagValue))
        {
            try
            {
                util.executeSQLupdate(query);
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
        return false;
    }
    public boolean tableIsLocked(String tableName)
    {
        DaoUtility util=new DaoUtility();
        tableName=tableName.toUpperCase();
        boolean tableIsLocked=false;
        
        String query="SELECT * from SYSCS_DIAG.LOCK_TABLE where TABLENAME='"+tableName+"'";
        List list=util.execSqlQuery(query);
        if(list !=null && !list.isEmpty())
        {
            tableIsLocked=true;
            System.err.println("Table "+tableName+" is locked");
        }
        return tableIsLocked;
    }
}
