/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ajax;


import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.DataImportIndicator;
import com.nomis.ovc.business.EnvironmentInformationProvider;
import com.nomis.ovc.business.School;
import com.nomis.ovc.business.SiteSetup;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppUtility;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class HtmlContentProvider 
{
    public static String getAboutInformationIndicator()
    {
        //HTMLDocument htmlDoc=new HTMLDocument();
        EnvironmentInformationProvider environmentInformationManager=AppUtility.getEnvironmentInformationManager();
        if(environmentInformationManager==null)
        environmentInformationManager=new EnvironmentInformationProvider();
        
        String table="<table border='1' style='border-collapse: collapse'>";
        table+="<tr><td>"+environmentInformationManager.getCurrentTime().getTaskName()+"</td>"
                +"<td>"+environmentInformationManager.getCurrentTime().getProgressStatus()+"</td>"
                +"</tr>";
        
        table+="<tr><td>"+environmentInformationManager.getJavaVersion().getTaskName()+"</td>"
                +"<td>"+environmentInformationManager.getJavaVersion().getProgressStatus()+"</td>"
                +"</tr>";
        table+="<tr><td>"+environmentInformationManager.getTomcatVersion().getTaskName()+"</td>"
                +"<td>"+environmentInformationManager.getTomcatVersion().getProgressStatus()+"</td>"
                +"</tr>";
              
        table+="<tr><td>"+environmentInformationManager.getTomcatBaseDirectory().getTaskName()+"</td>"
                +"<td>"+environmentInformationManager.getTomcatBaseDirectory().getProgressStatus()+"</td>"
                +"</tr>";
        
        table+="<tr><td>"+environmentInformationManager.getServerName().getTaskName()+"</td>"
                +"<td>"+environmentInformationManager.getServerName().getProgressStatus()+"</td>"
                +"</tr>";
        table+="</table>";
         return table;
    }
    public static String getDataImportProgressIndicator()
    {
        //HTMLDocument htmlDoc=new HTMLDocument();
        DataImportIndicator dataImportIndicator=AppUtility.getDataImportIndicator();
        if(dataImportIndicator==null)
        dataImportIndicator=new DataImportIndicator();
        String importStatus="ongoing";
        //if(dataImportIndicator.getImportStartFlag()==dataImportIndicator.getImportEndFlag())
        if(AppUtility.xmlDataImportThreadCounter <1)
        importStatus="complete";
        String table="<table border='1' style='border-collapse: collapse'>";
        table+="<tr><td colspan='3'>File name</td>"
                
                +"<td>File size</td>"
                +"</tr>";
        table+="<tr>"
                +"<td colspan='3'>"+dataImportIndicator.getImportFileName()+"</td>"
                +"<td>"+dataImportIndicator.getImportFileSize()+"</td>"
                +"</tr>";
        table+="<tr><td>Task name</td>"
                +"<td>Total number of records</td>"
                +"<td>Number of records processed</td>"
                +"<td>Import status</td></tr>";
        table+="<tr><td>"+dataImportIndicator.getHouseholdEnrollmentIndicator().getTaskName()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdEnrollmentIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdEnrollmentIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdEnrollmentIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getAdultHouseholdMembersEnrollmentIndicator().getTaskName()+"</td>"
                +"<td>"+dataImportIndicator.getAdultHouseholdMembersEnrollmentIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getAdultHouseholdMembersEnrollmentIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getAdultHouseholdMembersEnrollmentIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getHouseholdAssessmentIndicator().getTaskName()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdAssessmentIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdAssessmentIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdAssessmentIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getRevisedHouseholdAssessmentIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getRevisedHouseholdAssessmentIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getRevisedHouseholdAssessmentIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getRevisedHouseholdAssessmentIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getChildEnrollmentIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getChildEnrollmentIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getChildEnrollmentIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getChildEnrollmentIndicator().getStatusMessage()+"</td></tr>";
                
        table+="<tr><td>"+dataImportIndicator.getChildServiceIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getChildServiceIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getChildServiceIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getChildServiceIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getHivRiskAssessmentIndicator().getTaskName()+"</td>"
                +"<td>"+dataImportIndicator.getHivRiskAssessmentIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getHivRiskAssessmentIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getHivRiskAssessmentIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getCareAndSupportIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getCareAndSupportIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getCareAndSupportIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getCareAndSupportIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getCaregiverAccessToEmergencyFundIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getCaregiverAccessToEmergencyFundIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getCaregiverAccessToEmergencyFundIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getCaregiverAccessToEmergencyFundIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getCareplanAchievementIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getCareplanAchievementIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getCareplanAchievementIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getCareplanAchievementIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getChildEducationalAssessmentIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getChildEducationalAssessmentIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getChildEducationalAssessmentIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getChildEducationalAssessmentIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getHouseholdCareplanIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getHouseholdCareplanIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdCareplanIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdCareplanIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getHouseholdReferralIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getHouseholdReferralIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdReferralIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getHouseholdReferralIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getNutritionAssessmentIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getNutritionAssessmentIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getNutritionAssessmentIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getNutritionAssessmentIndicator().getStatusMessage()+"</td></tr>";
        
        table+="<tr><td>"+dataImportIndicator.getStatusUpdateIndicatorIndicator().getTaskName()+"</td>"+
                "<td>"+dataImportIndicator.getStatusUpdateIndicatorIndicator().getTotalSize()+"</td>"
                +"<td>"+dataImportIndicator.getStatusUpdateIndicatorIndicator().getCurrentRecordSize()+"</td>"
                +"<td>"+dataImportIndicator.getStatusUpdateIndicatorIndicator().getStatusMessage()+"</td></tr>";
        table+="</table>"+importStatus;
         return table;
    }
   /* public String getChildrenSearchResult(String nameToSearch,ReportParameterTemplate rpt,String userName)
    {
        String returnValue=null;
        try
        {
            DaoUtility util=new DaoUtility();
            if(rpt.getLevel2OuId()==null || rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                SiteSetup setup=util.getSiteSetupDaoInstance().getSiteSetup(userName);
                if(setup !=null)
                {
                    rpt.setLevel2OuId(setup.getOrgUnitId());
                }
            }
            List list=util.getOvcDaoInstance().searchOvcByPartOfName(rpt,nameToSearch);
            if(list !=null && !list.isEmpty())
            {
                String html="<table border='1' style='border-collapse:collapse; background-color:white'>";
                html+="<tr><td>OVC Id</td><td style='width:250px'>Child name</td><td> </td></tr>";
                Ovc ovc=null;
                String hhdetails=null;
                String firstName=null;
                String surname=null;
                for(Object obj:list)
                {
                    ovc=(Ovc)obj;
                    hhdetails=getConcatenatedBeneficiaryDetails(ovc.getHhUniqueId(),ovc.getFirstName(),ovc.getSurname());
                    hhdetails=ovc.getOvcId()+"; "+firstName+" "+surname;
                    html+="<tr><td>"+ovc.getOvcId()+"</td><td>"+ovc.getFirstName()+" "+ovc.getSurname()+"</td><td><input type='button' name='detailsBtn' value='Load details' onclick=\"loadHouseholdDetails('"+hhdetails+"')\"/> </td></tr>";
                    returnValue=ovc.getFirstName()+" "+ovc.getSurname();
                    //System.err.println("list.get(0) is "+returnValue);
                }
                html+="</table>";
                returnValue=html;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return returnValue;
    }*/
    public String getHouseholdSearchResult(String nameToSearch,ReportParameterTemplate rpt,String userName)
    {
        String returnValue=null;
        try
        {
            DaoUtility util=new DaoUtility();
            if(rpt.getLevel2OuId()==null || rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                SiteSetup setup=util.getSiteSetupDaoInstance().getSiteSetup(userName);
                if(setup !=null)
                {
                    rpt.setLevel2OuId(setup.getLevel2OuId());
                }
            }
            List list=util.getAdultHouseholdMemberDaoInstance().searchHouseholdProfileByPartOfName(rpt,nameToSearch);
            if(list !=null && !list.isEmpty())
            {
                String html="<table border='1' style='border-collapse:collapse; background-color:white'>";
                html+="<tr><td>HH Unique Id</td><td style='width:250px'>Household head name</td><td> </td></tr>";
                AdultHouseholdMember ahm=null;
                String hhdetails=null;
                String firstName=null;
                String surname=null;
                for(Object obj:list)
                {
                    ahm=(AdultHouseholdMember)obj;
                    hhdetails=getConcatenatedBeneficiaryDetails(ahm.getHhUniqueId(),ahm.getFirstName(),ahm.getSurname());
                    System.err.println("hhdetails is "+hhdetails);
                    html+="<tr><td>"+ahm.getHhUniqueId()+"</td><td>"+ahm.getFirstName()+" "+ahm.getSurname()+"</td><td><input type='button' name='detailsBtn' value='Load details' onclick=\"loadHouseholdDetails('"+hhdetails+"')\"/> </td></tr>";
                    returnValue=ahm.getFirstName()+" "+ahm.getSurname();
                    //System.err.println("list.get(0) is "+returnValue);
                }
                html+="</table>";
                returnValue=html;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return returnValue;
    }
    public String getSchoolList(List list)
    {
        String returnValue=null;
        try
        {
            if(list !=null && !list.isEmpty())
            {
                School school=null;
                
                for(int i=0; i<list.size(); i++)
                {
                    school=(School)list.get(i);
                    if(i==0)
                    returnValue=school.getId()+";"+school.getSchoolName();
                    else
                    returnValue+=":"+school.getId()+";"+school.getSchoolName();
                }
                
                //System.err.println(returnValue);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return returnValue;
    }
    public String getSchoolListAsHtml(List list)
    {
        String returnValue=null;
        try
        {
            if(list !=null && !list.isEmpty())
            {
                String html="<html:select property='nearestSchoolId' styleId='nearestSchoolId' style='width:150px;'>";
                        html+="<html:option value='select'>select...</html:option>";
                School school=null;
                
                for(Object obj:list)
                {
                    school=(School)obj;
                    html+="<option value="+school.getId()+">"+school.getSchoolName()+"</option>";  
                }
                returnValue=html+"</select>";
                System.err.println(returnValue);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return returnValue;
    }
    private String getConcatenatedBeneficiaryDetails(String uniqueId,String firstName,String surname)
    {      
        if(firstName !=null)
        {
            firstName=firstName.replace("'", "");
            firstName=firstName.replace("\n", " ");
        }
        if(surname !=null)
        {
            surname=surname.replace("'", "");
            surname=surname.replace("\n", " ");
        }
        if(uniqueId !=null)
        {
            uniqueId=uniqueId.replace("'", "_");
        }
        String concatenatedBeneficiaryDetails=uniqueId+"; "+firstName+" "+surname;
        return concatenatedBeneficiaryDetails;
    }
}
