/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.dataupload.controller;

import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.ExcelReader;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author smomoh
 */
public class DataUploadAction extends org.apache.struts.action.Action {

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
        DataUploadForm duf=(DataUploadForm)form;
        
        String requiredAction=duf.getActionName();
        String uploadType=duf.getUploadType();
        FormFile uploadedFile=duf.getUploadedFile();
        HttpSession session=request.getSession();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        
        if(requiredAction==null)
        {
            duf.reset(mapping, request);
        }
        else if(requiredAction.equalsIgnoreCase("upload"))
        {
            if(uploadType !=null && uploadedFile !=null)
            {
                DaoUtility util=new DaoUtility();
                ExcelReader er=new ExcelReader();
                InputStream is=uploadedFile.getInputStream();
                if(is !=null)
                {
                    if(uploadType.equalsIgnoreCase("organizationUnit"))
                    {
                        List ouList=er.uploadOrganizationUnitsFromExcel(is);
                        if(ouList !=null && !ouList.isEmpty())
                        {
                            for(Object obj:ouList)
                            {
                                OrganizationUnit ou=(OrganizationUnit)obj;
                                util.getOrganizationUnitDaoInstance().saveOrganizationUnit(ou);
                            }
                        }
                    }
                }
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
