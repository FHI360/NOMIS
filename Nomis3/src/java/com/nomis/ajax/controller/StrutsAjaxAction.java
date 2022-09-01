/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ajax.controller;

import com.nomis.exportimport.DataImportImplementer;
import com.nomis.exportimport.XMLDataImportManager;
import com.nomis.maintenance.DataCleanupManager;
import com.nomis.ovc.business.SiteSetup;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.DatabaseUtilities;
import com.nomis.ovc.util.UniqueIdManager;
import com.nomis.ajax.HtmlContentProvider;
import com.nomis.operationsManagement.EnvironmentInformationManager;
import com.nomis.ovc.util.AppConstant;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class StrutsAjaxAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
        HttpSession session=request.getSession();
        String reqParam=request.getParameter("qparam");
        String id=request.getParameter("id");
        //System.err.println("reqParam is "+reqParam);
        //System.err.println("id is "+id);
        String returnValue=null;
        
        PrintWriter out=response.getWriter();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        String uniqueId=null;
        if(reqParam==null)
        {
            
        }
        else
        {
            DaoUtility util=new DaoUtility();
            String searchKeyWard="";
            int beneficiaryType=0;
            if(id !=null)
            {
                if(id.equalsIgnoreCase("daysPerMth"))
                {
                    int mth=Integer.parseInt(reqParam);
                    /*if(reqParam !=null && reqParam.indexOf(";") !=-1)
                    {
                        String[] reqParamArr=reqParam.split(";");
                        //if(reqParamArr.length>0)
                        mth=Integer.parseInt(reqParamArr[0]);
                    }*/
                    
                    List list=DateManager.generateDays(mth);
                    String concatenatedMonths=null;
                    if(list !=null && !list.isEmpty())
                    {
                        for(int i=0; i<list.size(); i++)
                        {
                            if(i==0)
                            concatenatedMonths=list.get(i).toString();
                            else
                            concatenatedMonths+=";"+list.get(i).toString();
                        }
                    }
                    returnValue=concatenatedMonths;
                    System.err.println("Inside generate days per month in StrutsAjaxAction");
                }
                else if(id.equalsIgnoreCase("upgrade"))
                {
                    DatabaseUtilities dbUtils=new DatabaseUtilities();
                    
                    //dbUtils.runUpgrade();
                    System.err.println("Upgrade run in StrutsAjaxAction");
                }
                else if(id.equalsIgnoreCase("updateExistingRecords"))
                {
                    String message=""+DataCleanupManager.updateBeneficiaryARTInformationFromCareAndSupport();
                    System.err.println(" "+message);
                    DatabaseUtilities dbUtils=new DatabaseUtilities();
                    dbUtils.revertHivUnknownDueToRiskAssessmentToBaselineHivStatusInChildEnrollment();
                    message=""+DataCleanupManager.resetCurrentHivStatusForPositiveBaselineStatus();
                    util.getChildServiceDaoInstance().getAndUpdateWashRecords();
                    DataCleanupManager.updateNutritionStatusWithAgeHeightAndWeight();
                    DataCleanupManager.correctOrganizationUnitsInDataButNotInOuMetadata();
                    DataCleanupManager.updateMainEnrollmentStreams();
                    DataCleanupManager.updateChildEnrollmentRecordsForOVCOfferedClients();
                    int count=util.getCareAndSupportChecklistDaoInstance().removeRecordsOfNonPositiveBeneficiaries();
                    System.err.println(count+" records whose HIV status changed from positive were deleted");
                    System.err.println("updateExistingRecords run in StrutsAjaxAction "+message);
                    
                }//hsuTrackingDate
                else if(id.equalsIgnoreCase("search"))
                {
                    
                    ReportParameterTemplate rpt=new ReportParameterTemplate();
                    String level2OuId="All";
                    String level3OuId="All";
                    String level4OuId="All";
                    SiteSetup setup=util.getSiteSetupDaoInstance().getSiteSetup(userName);
                    if(setup !=null)
                    {
                        level2OuId=setup.getLevel2OuId();
                    }
                    if(reqParam !=null && reqParam.trim().length()>0)
                    {
                        //level2OuId=reqParam;
                        if(reqParam.indexOf(":") !=-1)
                        {
                            String[] reqParamArray=reqParam.split(":");
                            if(reqParamArray.length>1)
                            {
                                searchKeyWard=reqParamArray[0];
                                level3OuId=reqParamArray[1];
                                level4OuId=reqParamArray[2];
                                if(searchKeyWard !=null && searchKeyWard.indexOf("=") !=-1)
                                {
                                    String[] keyWordParams=searchKeyWard.split("=");
                                    if(keyWordParams !=null && keyWordParams.length>1)
                                    {
                                        beneficiaryType=Integer.parseInt(keyWordParams[0]);
                                        searchKeyWard=keyWordParams[1];
                                    }
                                }
                            }
                        }
                    }
                    rpt.setLevel2OuId(level2OuId);
                    rpt.setLevel3OuId(level3OuId);
                    rpt.setLevel4OuId(level4OuId);
                    HtmlContentProvider hcp=new HtmlContentProvider();
                    if(beneficiaryType>0 && beneficiaryType==3)
                    {
                        //search for children
                        //returnValue=hcp.getChildrenSearchResult(searchKeyWard,rpt,userName);
                    }
                    else
                    returnValue=hcp.getHouseholdSearchResult(searchKeyWard,rpt,userName);
                    
                }
                else if(id.equals("processImportFiles"))
                {
                    //XMLDataImportManager xdim=new XMLDataImportManager();
                    //List mainList=xdim.importData(userName,3,3,4,1,1,1,true);
                    DataImportImplementer dim=new DataImportImplementer();
                    dim.importData(userName,3,3,4,1,1,1,true);
                }
                else if(id.equals("checkEnrollmentStatusUpdate"))
                {
                    if(AppUtility.enrollmentStatusUpdateThreadCounter<0)
                    AppUtility.enrollmentStatusUpdateThreadCounter=0;
                    returnValue=AppUtility.enrollmentStatusUpdateThreadCounter+"";
                    System.err.println("returnValue is "+returnValue);
                }
                else if(id.equals("checkCustomIndicatorsStatus"))
                {
                    if(AppUtility.customIndicatorsThreadCounter<0)
                    AppUtility.customIndicatorsThreadCounter=0;
                    returnValue=AppUtility.customIndicatorsThreadCounter+"";
                    //System.err.println("returnValue is "+returnValue);
                }
                else if(id.equals("checkImportStatus"))
                {
                    if(AppUtility.getCurrentImportFileName()==null)
                    {
                        List uploadedFileList=util.getDataImportUploadManagerDaoInstance().getAllDataImportFileUploadManager(0);
                        if(uploadedFileList !=null && !uploadedFileList.isEmpty())
                        {
                            AppUtility.setCurrentImportFileName("About to process "+uploadedFileList.size()+" uploaded data files...");
                        }
                    }
                    //System.err.println("AppUtility.getCurrentImportFileName() is "+AppUtility.getCurrentImportFileName());
                    if(AppUtility.getCurrentImportFileName()!=null)
                    //returnValue="Processing "+AppUtility.getCurrentImportFileName()+": "+AppUtility.getCurrentImportRecordName();
                    returnValue=HtmlContentProvider.getDataImportProgressIndicator();
                    //System.err.println("returnValue is "+returnValue);
                }
                else if(id.equalsIgnoreCase("environmentInfo"))
                {
                    EnvironmentInformationManager.loadEnvironmentInfo(request);
                    returnValue=HtmlContentProvider.getAboutInformationIndicator();
                }
                else if(id.equalsIgnoreCase("generateOuCode"))
                {
                    //System.err.println("reqParam is "+reqParam);
                    String[] idparams=reqParam.split(";");
                    if(idparams !=null && idparams.length>2)
                    {
                        String ouName=idparams[0];
                        String parentId=idparams[1];
                        int ouLevel=Integer.parseInt(idparams[2]);
                        String ouCode=util.getOrganizationUnitDaoInstance().generateOuCode(parentId, ouName, ouLevel);
                        returnValue=ouCode;
                    }
                }
                else if(id.equalsIgnoreCase("uniqueId"))
                {
                    try
                    {
                    UniqueIdManager uig=new UniqueIdManager();
                    String[] idparams=reqParam.split(";");
                    if(idparams !=null && idparams.length>3)
                    {
                        int serialNumber=Integer.parseInt(idparams[3]);
                        uniqueId=uig.generateUniqueIdByOrganizationUnitShortCodes(idparams[0], idparams[1], idparams[2], serialNumber);
                        returnValue=uniqueId;
                        System.err.println("hhUniqueId is "+uniqueId);
                    }
                    else if(idparams !=null && idparams.length==2)
                    {
                        int serialNumber=Integer.parseInt(idparams[1]);
                        if(serialNumber>0)
                        uniqueId=idparams[0]+"/"+uig.getPaddedSerialNumber(serialNumber);
                        returnValue=uniqueId;
                        System.err.println("ovcId is "+uniqueId);
                    }
                    }
                    catch(NumberFormatException ex)
                    {
                        returnValue=ex.getMessage();
                    }
                    catch(Exception ex)
                    {
                        returnValue=ex.getMessage();
                    }
                }
                else if(id.equalsIgnoreCase("ovcuniqueId"))
                {
                    UniqueIdManager uig=new UniqueIdManager();
                    String[] idparams=reqParam.split(";");
                    if(idparams !=null && idparams.length>4)
                    {
                        uniqueId=uig.generateOvcUniqueId(idparams[0], idparams[1], idparams[2], idparams[3], idparams[4],idparams[5]);
                        returnValue=uniqueId;
                        System.err.println("ovchhUniqueId is "+uniqueId);
                    }
                }   
                else if(id.equalsIgnoreCase("schoolList"))
                {
                    ReportParameterTemplate rpt=new ReportParameterTemplate();
                    String[] ouparams=reqParam.split(";");
                    if(ouparams !=null && ouparams.length>0)
                    {
                        if(ouparams[0] !=null && ouparams[0].trim().length()>0)
                        {
                            String level2OuId=ouparams[0].trim();
                            rpt.setLevel2OuId(level2OuId);
                            if(ouparams.length>1 && ouparams[1] !=null && ouparams[1].trim().length()>0)
                            {
                                String level3OuId=ouparams[1].trim();
                                rpt.setLevel3OuId(level3OuId);
                            }
                        }
                    }
                    List schoolList=util.getSchoolDaoInstance().getSchoolsByOrgUnit(rpt);
                    if(schoolList !=null)
                    //System.err.println("schoolList.size() is "+schoolList.size());
                    request.setAttribute("schoolList", schoolList);
                    HtmlContentProvider htmlProvider=new HtmlContentProvider();
                    String htmlSch=htmlProvider.getSchoolList(schoolList);
                    returnValue=htmlSch;
                }
            }
            
        }
        out.print(returnValue);
        out.flush();
        return null;
    }
    private void loadEnvironmentInfo(HttpServletRequest request)
    {
        
    }
}
