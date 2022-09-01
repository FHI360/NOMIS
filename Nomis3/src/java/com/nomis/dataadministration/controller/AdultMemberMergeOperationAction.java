/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.dataadministration.controller;

import com.nomis.operationsManagement.OrganizationUnitAttributesManager;
import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
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
public class AdultMemberMergeOperationAction extends org.apache.struts.action.Action {

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
            throws Exception {
        AdultMemberMergeOperationForm ahmo=(AdultMemberMergeOperationForm)form;
        HttpSession session=request.getSession();
        String requiredAction=ahmo.getActionName();
        OrganizationUnitAttributesManager ouaManager=new OrganizationUnitAttributesManager();
        AppManager appManager=new AppManager();
        User user=appManager.getCurrentUser(session);
        String userName=appManager.getCurrentUserName(session);
        String level2OuId=ahmo.getLevel2OuId();
        String level3OuId=ahmo.getLevel3OuId();
        String level4OuId=ahmo.getOrganizationUnitId();
        String cboId=ahmo.getCboId();
        ouaManager.getLevel2OrganizationUnitForReports(session);
        ouaManager.setOrganizationUnitAttributes(session, level3OuId,userName,cboId);
        ouaManager.setOrganizationUnitAttributesByOuId(request, level2OuId, level3OuId, level4OuId,cboId);
        ouaManager.setOrganizationUnitHierarchyAttributes(session);
        
        ReportParameterTemplate rpt=new ReportParameterTemplate();
        rpt.setLevel2OuId(level2OuId);
        rpt.setLevel3OuId(level3OuId);
        rpt.setLevel4OuId(level4OuId);
        rpt.setCboId(cboId);

        if(requiredAction==null)
        {
            ahmo.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("cboList"))
        {
            ouaManager.getLevel3OrganizationUnitForReports(session,level2OuId);
            ouaManager.getLevel4OrganizationUnitForReports(session,null);
            ouaManager.setCBOListByLevel2Ou(session, level2OuId);
            
            session.setAttribute("assignedLevel3OuList", new ArrayList());
            ouaManager.setLevel4OuList(session, null);
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level3OuList"))
        {
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("level4OuList"))
        {
            ouaManager.getLevel4OrganizationUnitForReports(session,level3OuId);
            
            return mapping.findForward(SUCCESS);
        }
        else if(requiredAction.equalsIgnoreCase("loadRecords"))
        {
           loadRecords(session,rpt);
           ahmo.setKeep(null);
           ahmo.setMerge(null);
        }
        else if(requiredAction.equalsIgnoreCase("mergeRecords"))
        {
            String uniqueIdToKeep=ahmo.getKeep();
            String[] uniqueIdsToMerge=ahmo.getMerge();
            DaoUtility util=new DaoUtility();             
            {
                if(uniqueIdToKeep !=null)
                {
                    if(uniqueIdsToMerge !=null)
                    {
                        for(int i=0; i<uniqueIdsToMerge.length; i++)
                        {
                            if(!uniqueIdsToMerge[i].equalsIgnoreCase(uniqueIdToKeep))
                            {
                                util.getAdultHouseholdMemberDaoInstance().mergeCaregivers(uniqueIdsToMerge[i], uniqueIdToKeep); 
                                //util.mergeHouseholds(uniqueIdsToMerge[i], uniqueIdToKeep);
                                System.err.println("Keep "+uniqueIdToKeep+", Merge "+uniqueIdsToMerge[i]);
                            }
                        }
                    }
                }
            }
            loadRecords(session,rpt);
            ahmo.setKeep(null);
           ahmo.setMerge(null);
        }
        return mapping.findForward(SUCCESS);
    }
    private void loadRecords(HttpSession session,ReportParameterTemplate rpt) throws Exception
    {
        List hheList=new ArrayList();
           DaoUtility util=new DaoUtility(); 
                      
           List list=util.getAdultHouseholdMemberDaoInstance().getAdultHouseholdMemberRecords(rpt);
           if(list !=null)
           {
               AdultHouseholdMember ahm=null;
               for(int i=0; i<list.size(); i++)
                {
                    ahm=(AdultHouseholdMember)list.get(i);
                    ahm.setSerialNo(i+1);
                    if(i%2>0)
                    ahm.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                    hheList.add(ahm);
                }
               session.setAttribute("ahmmergebtnDisabled", "false");
           }
           else
           {
               session.setAttribute("hhmergebtnDisabled", "true");     
           }
           session.setAttribute("ahmrecordsForMerge", hheList);
    }
}
