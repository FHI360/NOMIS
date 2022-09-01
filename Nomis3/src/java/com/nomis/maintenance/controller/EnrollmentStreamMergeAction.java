/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance.controller;

import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.business.VulnerabilityStatus;
import com.nomis.ovc.dao.DaoUtility;
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
public class EnrollmentStreamMergeAction extends org.apache.struts.action.Action {

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
        EnrollmentStreamMergeForm esmf=(EnrollmentStreamMergeForm)form;
        HttpSession session=request.getSession();
        String requiredAction=esmf.getActionName();
        String stdEnrollmentStream=esmf.getStdEnrollmentStreamId();
        String[] nonStdEnrollmentStream=esmf.getNonStdEnrollmentStreamIds();
        setEnrollmentStreamList(session);
        if(requiredAction==null)
        {
            esmf.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("merge"))
        {
            if(nonStdEnrollmentStream !=null)
            {
                DaoUtility util=new DaoUtility();
                List list=null;
                Ovc ovc=null;
                VulnerabilityStatus vs=null;
                int updateSuccess=0;
                int updateSuccessCount=0;
                for(int i=0; i<nonStdEnrollmentStream.length; i++)
                {
                    list=util.getChildEnrollmentDaoInstance().getRecordsByVulnerabilityStatusId(nonStdEnrollmentStream[i]);
                    if(list !=null)
                    {
                        for(Object obj:list)
                        {
                           ovc=(Ovc)obj;
                           ovc=getOvcWithUpdatedEnrollmentStream(ovc,stdEnrollmentStream,nonStdEnrollmentStream);
                           updateSuccess=util.getChildEnrollmentDaoInstance().updateOvcOnly(ovc);
                           if(updateSuccess==1)
                           updateSuccessCount++;
                           System.err.println("updateSuccessCount is "+updateSuccessCount+" of "+list.size());
                           
                        }
                       if(updateSuccessCount==list.size())
                       {
                           vs=util.getVulnerabilityStatusDaoInstance().getVulnerabilityStatus(nonStdEnrollmentStream[i]);
                           if(vs !=null)
                           {
                              util.getVulnerabilityStatusDaoInstance().deleteVulnerabilityStatus(vs);
                              updateSuccessCount=0;
                           }
                       }
                    }
                }
            }
            setEnrollmentStreamList(session);
        }
        return mapping.findForward(SUCCESS);
    }
    private Ovc getOvcWithUpdatedEnrollmentStream(Ovc ovc,String stdEnrollmentStream,String[] nonStdEnrollmentStream)
    {
        if(ovc !=null)
        {
            String enrollmentStream=ovc.getVulnerabilityStatusCode();
            if(enrollmentStream !=null && nonStdEnrollmentStream !=null)
            {
                for(int i=0; i<nonStdEnrollmentStream.length; i++)
                {
                    enrollmentStream=enrollmentStream.replace(nonStdEnrollmentStream[i], stdEnrollmentStream);
                }
                ovc.setVulnerabilityStatusCode(enrollmentStream);
            }
            System.err.println("Modified Ovc enrollment stream is "+ovc.getVulnerabilityStatusCode());
        }
        return ovc;
    }
    private void setEnrollmentStreamList(HttpSession session)
    {
        try
        {
            DaoUtility util=new DaoUtility();
            util.getVulnerabilityStatusDaoInstance().resetLegacyVulnerabilityStatus();
            List stdEnrollmentStreamList=util.getVulnerabilityStatusDaoInstance().getStandardVulnerabilityStatus();
            if(stdEnrollmentStreamList==null)
            stdEnrollmentStreamList=new ArrayList();
            
            List nonStdEnrollmentStreamList=util.getVulnerabilityStatusDaoInstance().getNonStdVulnerabilityStatus();
            if(nonStdEnrollmentStreamList==null)
            nonStdEnrollmentStreamList=new ArrayList();
            
            session.setAttribute("standardEnrollmentStreamList", stdEnrollmentStreamList);
            session.setAttribute("nonStandardEnrollmentStreamList", nonStdEnrollmentStreamList);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
