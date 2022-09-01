/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport.controller;

import com.nomis.exportimport.DataExportImportSummary;
import com.nomis.exportimport.XMLDataImportManager;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppUtility;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class ExportSummaryReportAction extends org.apache.struts.action.Action {

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
        ExportSummaryReportForm esrf=(ExportSummaryReportForm)form;
        AppUtility appUtil=new AppUtility();
        String fileName=request.getParameter("fileName");
        System.err.println("File name is "+fileName);
        appUtil.getImportFilePath();
        File file=new File(appUtil.getImportFilePath());
        if(file !=null)
        {
            String[] importFiles=file.list();
            if(importFiles !=null)
            {
                List fileList=new ArrayList();
                for(int i=0; i<importFiles.length; i++)
                {
                    fileList.add(importFiles[i]);
                    //System.err.println("Import file name is "+importFiles[i]);
                }
                request.setAttribute("listOfFileInimportFolder", fileList);
            }
        }
        if(fileName !=null && fileName.trim().length()>0)
        {
            System.err.println("id is "+fileName);
            XMLDataImportManager xdim=new XMLDataImportManager();
            if(fileName.indexOf(".zip") !=-1)
            {
                String importFilePath=appUtil.getImportFilePath()+appUtil.getFileSeperator()+fileName;
                String fileNameWithoutExtension=fileName.substring(0, fileName.indexOf(".zip")); 
                String destinationDirectory=appUtil.getZipExtractsFilePath()+appUtil.getFileSeperator()+fileNameWithoutExtension;
                xdim.unzipFile(importFilePath,destinationDirectory);
                List mainList=new ArrayList();
                List list=xdim.readDataExportSummaryFromXML(destinationDirectory);
                if(list !=null && !list.isEmpty())
                {
                    int i=0;
                    System.err.println("Data export summary list size is "+list.size());
                    DataExportImportSummary deis=null;
                    for(Object obj:list)
                    {
                        deis=(DataExportImportSummary)obj;
                        deis.setSerialNo(i+1);
                        if(i%2>0)
                        deis.setRowColor(AppConstant.SECONDREPORTROWCOLOUR);
                        mainList.add(deis);
                        i++;
                        //System.err.println("Data export xml Tag name is "+deis.getTagName()+" and value is "+deis.getTagValue());
                    }
                    request.setAttribute("dataExportImportSummaryList", mainList);
                }
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
