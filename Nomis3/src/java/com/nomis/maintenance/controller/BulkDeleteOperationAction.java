/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance.controller;

import com.nomis.maintenance.DataCleanupScriptGenerator;
import com.nomis.exportimport.ZipHandler;
import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.ArrayList;
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
public class BulkDeleteOperationAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String PARAMPAGE = "paramPage";
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
        BulkDeleteOperationForm bdform=(BulkDeleteOperationForm)form;
        HttpSession session=request.getSession();
        DaoUtility util=new DaoUtility();
        //HouseholdEnrollmentDao hhedao=util.getHouseholdEnrollmentDaoInstance();
        //AdultHouseholdMemberDao ahmdao=util.getAdultHouseholdMemberDaoInstance();
        String level2OuId=bdform.getLevel2OuId();
        String level3OuId=bdform.getLevel3OuId();
        String level4OuId=bdform.getOrganizationUnitId();
        String beneficiaryType=bdform.getBeneficiaryType();
        String cboId=bdform.getCboId();
        String sortOrder=bdform.getSortOrder();
        String requiredAction=bdform.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
                
        if(AccessManager.isUserInDataEntryRole(user))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        if(requiredAction==null)
        {
            bdform.reset(mapping, request);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            ouaManager.setAssignedLevel3Ou(session, bdform.getCboId());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.setLevel4OuList(session, level3OuId);
            ouaManager.setCBOListByAssignedLevel3Ou(session, level3OuId);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equals("generateList"))
        {
            generateReport(level2OuId,level3OuId,level4OuId,cboId,"All",null,null,session,sortOrder,beneficiaryType);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("generateScript"))
        {
            String fileName="empty";
            
                if(beneficiaryType !=null)
                {
                    ReportParameterTemplate rpt=new ReportParameterTemplate();
                    rpt.setCboId(cboId);
                    rpt.setLevel2OuId(level2OuId);
                    rpt.setLevel3OuId(level3OuId);
                    rpt.setLevel4OuId(level4OuId);

                    rpt.setStartDate(null);
                    rpt.setEndDate(null);
                    DataCleanupScriptGenerator dcsg=new DataCleanupScriptGenerator();
                    
                    //The list of beneficiaries to be pulled is dependent on the beneficiary type that comes with the request
                    if(beneficiaryType.equalsIgnoreCase("household"))
                    {
                        fileName="HouseholdRecordsForDelete"+DateManager.getCurrentDate();
                        dcsg.createXmlScriptForHouseholdEnrollmentForDelete(null, rpt);
                    }
                    else if(beneficiaryType.equalsIgnoreCase("caregiver"))
                    {
                        fileName="AdultRecordsForDelete"+DateManager.getCurrentDate();
                        dcsg.createXmlScriptForAdultHouseholdMemberRecordsForDelete(null, rpt);
                    }
                    else if(beneficiaryType.equalsIgnoreCase("ovc"))
                    {
                        fileName="OvcRecordsForDelete"+DateManager.getCurrentDate();
                        dcsg.createXmlScriptForOvcRecordsForDelete(null, rpt);
                    }
                
                zipFiles(fileName);
            }
        }
        else if(requiredAction.equals("delete"))
        {
            String[] selectedRecords=bdform.getHouseholdsToDelete();
            if(selectedRecords !=null && selectedRecords.length>0)
            {
                String uniqueId=null;
                if(beneficiaryType !=null)
                {
                    //The list of beneficiaries to be pulled is dependent on the beneficiary type that comes with the request
                    if(beneficiaryType.equalsIgnoreCase("household"))
                    {
                        for(int i=0; i<selectedRecords.length; i++)
                        {
                            uniqueId=selectedRecords[i];
                            HouseholdEnrollment hhe=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollment(uniqueId);
                            if(hhe !=null)
                            {
                                util.getHouseholdEnrollmentDaoInstance().markedForDelete(hhe);
                            }
                        }
                    }
                    else if(beneficiaryType.equalsIgnoreCase("caregiver"))
                    {
                        AdultHouseholdMember ahm=null;
                        for(int i=0; i<selectedRecords.length; i++)
                        {
                            uniqueId=selectedRecords[i];
                            ahm=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMember(uniqueId);
                            if(ahm !=null)
                            {
                                //util.getHouseholdEnrollmentDaoInstance().deleteHouseholdMembers(uniqueId);
                                util.getAdultHouseholdMemberDaoInstance().markForDelete(ahm);
                            }
                        }
                    }
                    else if(beneficiaryType.equalsIgnoreCase("ovc"))
                    {
                        Ovc ovc=null;
                        for(int i=0; i<selectedRecords.length; i++)
                        {
                            uniqueId=selectedRecords[i];
                            ovc=util.getChildEnrollmentDaoInstance().getOvc(uniqueId);
                            if(ovc !=null)
                            {
                                //util.getHouseholdEnrollmentDaoInstance().deleteHouseholdMembers(uniqueId);
                                util.getChildEnrollmentDaoInstance().markForDelete(ovc);
                            }
                        }
                    }
                }
            }
        }
        return mapping.findForward(SUCCESS);
    }
    public void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        session.setAttribute("bdSaveDisabled", saveDisabled);
        session.setAttribute("bdModifyDisabled", modifyDisabled);
    }
    private void generateReport(String level2OuId,String level3OuId, String level4OuId,String cboId, String partnerCode,String startDate,String endDate,HttpSession session,String sortOrder,String beneficiaryType)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            ReportParameterTemplate rpt=new ReportParameterTemplate();
            rpt.setCboId(cboId);
            rpt.setLevel2OuId(level2OuId);
            rpt.setLevel3OuId(level3OuId);
            rpt.setLevel4OuId(level4OuId);
            
            rpt.setStartDate(startDate);
            rpt.setEndDate(endDate);
            
            session.removeAttribute("bdovcList");
            session.removeAttribute("bdadultList");
            session.removeAttribute("bdhheList");
            if(beneficiaryType !=null)
            {
                String color="#D7E5F2";
                //The list of beneficiaries to be pulled is dependent on the beneficiary type that comes with the request
                if(beneficiaryType.equalsIgnoreCase("ovc"))
                {
                    int count=0;
                    Ovc ovc=null;
                    List ovcList=new ArrayList();
                    List list=util.getChildEnrollmentDaoInstance().getOvcRecordsForExport(rpt,1);
                    if(list==null)
                    list=new ArrayList();
                    if(list==null)
                    list=new ArrayList();
                    for(Object obj:list)
                    {
                       count++;
                       ovc=(Ovc)obj;
                       ovc.setSerialNo(count);
                       if(count%2==0)
                       ovc.setRowColor(color);
                       ovcList.add(ovc);
                    }
                    session.setAttribute("bdovcList", ovcList);
                }
                else if(beneficiaryType.equalsIgnoreCase("caregiver"))
                {
                    int count=0;
                    AdultHouseholdMember ahm=null;
                    List cgiverList=new ArrayList();
                    List list=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMemberRecordsForExport(rpt,1);
                    if(list==null)
                    list=new ArrayList();
                    for(Object obj:list)
                    {
                       count++;
                       ahm=(AdultHouseholdMember)obj;
                       ahm.setSerialNo(count);
                       ahm.setNumberOfChildren(util.getChildEnrollmentDaoInstance().getNumberOfOvcPerCaregiver(ahm.getBeneficiaryId()));
                       if(count%2==0)
                       ahm.setRowColor(color);
                       cgiverList.add(ahm);
                    }
                    session.setAttribute("bdadultList",cgiverList);
                }
                else if(beneficiaryType.equalsIgnoreCase("household"))
                {
                    int count=0;
                    HouseholdEnrollment hhe=null;
                    List hheList=new ArrayList();
                    List list=util.getHouseholdEnrollmentDaoInstance().getHouseholdEnrollmentRecordsForExport(rpt, 1);
                    if(list==null)
                    list=new ArrayList();
                    if(list==null)
                    list=new ArrayList();
                    for(Object obj:list)
                    {
                       count++;
                       hhe=(HouseholdEnrollment)obj;
                       hhe.setSerialNo(count);
                       if(count%2==0)
                       hhe.setRowColor(color);
                       hheList.add(hhe);
                    }
                    session.setAttribute("bdhheList",hheList);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
private void zipFiles(String fileName)
{
    ZipHandler zipHandler=new ZipHandler();
    AppUtility appUtil=new AppUtility();
    List listOfFilesToBeZipped=appUtil.copyFilePathsIntoList(appUtil.getDeleteScriptsFilePath());
    zipHandler.zipFile(listOfFilesToBeZipped, appUtil.getDbExportZipDirectory(), fileName);
}
}
