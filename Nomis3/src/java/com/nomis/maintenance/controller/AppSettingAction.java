/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.maintenance.controller;

import com.nomis.maintenance.OptionsAttributeManager;
import com.nomis.ovc.business.AppSetting;
import com.nomis.ovc.dao.AppSettingDao;
import com.nomis.ovc.dao.AppSettingDaoImpl;
import com.nomis.ovc.util.DateManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smomoh
 */
public class AppSettingAction extends org.apache.struts.action.Action {

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
        AppSettingForm asform=(AppSettingForm)form;
        String actionName=asform.getActionName();
        String curagedate=OptionsAttributeManager.CURAGEDATAE;
        String hhvaId=OptionsAttributeManager.HHVAID;
        String startDateId=OptionsAttributeManager.STARTDATEID;
        String endDateId=OptionsAttributeManager.ENDDATEID;
        String hracId=OptionsAttributeManager.HRACID;
        String dbImportAccessId=OptionsAttributeManager.DBIMPORTACCESSID;
        String displayClientRecordInHtml=OptionsAttributeManager.DISPLAYCLIENTRECORDINHTMLID;
        String downloadClientRecordInExcel=OptionsAttributeManager.DOWNLOADCLIENTRECORDINEXCELID;
        
        String optionName=asform.getName();
        String value=null;//asform.getCuragedate();
        
        String hhvaValue=asform.getHhvaversion();
        String hracValue=asform.getHracversion();
        String startDate=null;//asform.getStartDate();
        String endDate=null;//asform.getEndDate();
        String dbImportAccess=asform.getXmlDbImport();
        
        String displayInHtmlValue=asform.getDisplayClientRecordInHtml();
        String downloadInExcelValue=asform.getDownloadClientRecordInExcel();
        if(actionName==null)
        {
            asform=getDefaultForm(asform);
            return mapping.findForward(SUCCESS);
        }
        else if(actionName.equalsIgnoreCase("save"))
        {
            AppSettingDao opmdao=new AppSettingDaoImpl();
            AppSetting opm=new AppSetting();
            opm.setName(optionName);
            opm.setValue(DateManager.processMthDayYearToMysqlFormat(value));
            opm.setRecordId(curagedate);
            opm.setLastModifiedDate(DateManager.getCurrentDate());
            opmdao.saveAppSetting(opm);
            //hracversion
            opm=new AppSetting();
            opm.setName(optionName);
            opm.setValue(hhvaValue);
            opm.setRecordId(hhvaId);
            opm.setLastModifiedDate(DateManager.getCurrentDate());
            opmdao.saveAppSetting(opm);
            
                     
            opm=new AppSetting();
            opm.setName(optionName);
            opm.setValue(hracValue);
            opm.setRecordId(hracId);
            opm.setLastModifiedDate(DateManager.getCurrentDate());
            opmdao.saveAppSetting(opm);
            
            opm=new AppSetting();
            opm.setName(optionName);
            opm.setValue(dbImportAccess);
            opm.setRecordId(dbImportAccessId);
            opm.setLastModifiedDate(DateManager.getCurrentDate());
            opmdao.saveAppSetting(opm);
            
            opm=new AppSetting();
            opm.setName(optionName);
            opm.setValue(displayInHtmlValue);
            opm.setRecordId(displayClientRecordInHtml);
            opm.setLastModifiedDate(DateManager.getCurrentDate());
            opmdao.saveAppSetting(opm);
            
            opm=new AppSetting();
            opm.setName(optionName);
            opm.setValue(downloadInExcelValue);
            opm.setRecordId(downloadClientRecordInExcel);
            opm.setLastModifiedDate(DateManager.getCurrentDate());
            opmdao.saveAppSetting(opm);
            
            if(startDate !=null && startDate.indexOf("/") !=-1)
            {
                if(endDate !=null && endDate.indexOf("/") !=-1)
                {
                    opm=new AppSetting();
                    opm.setName(optionName);
                    //opm.setValue(DateManager.processMthDayYearToMysqlFormat(startDate));
                    opm.setRecordId(startDateId);
                    opm.setLastModifiedDate(DateManager.getCurrentDate());
                    opmdao.saveAppSetting(opm);
                    
                    opm=new AppSetting();
                    opm.setName(optionName);
                    //opm.setValue(DateManager.processMthDayYearToMysqlFormat(endDate));
                    opm.setRecordId(endDateId);
                    opm.setLastModifiedDate(DateManager.getCurrentDate());
                    opmdao.saveAppSetting(opm);
                }
            }
            
        }
        return mapping.findForward(SUCCESS);
    }
    private AppSettingForm getDefaultForm(AppSettingForm opf)
    {
        try
        {
        if(opf !=null)
        {
            AppSettingDao opmdao=new AppSettingDaoImpl();
            AppSetting opm=opmdao.getAppSetting("curagedate");
            /*if(opm !=null)
            opf.setCuragedate(DateManager.getMthDayYearFromMySqlDate(opm.getValue()));*/
            opm=opmdao.getAppSetting(OptionsAttributeManager.HHVAID);
            if(opm !=null)
            opf.setHhvaversion(opm.getValue());
            
            opm=opmdao.getAppSetting(OptionsAttributeManager.HRACID);
            if(opm !=null)
            opf.setHracversion(opm.getValue());
            
            /*opm=opmdao.getAppSetting(OptionsAttributeManager.STARTDATEID);
            if(opm !=null)
            opf.setStartDate(DateManager.getMthDayYearFromMySqlDate(opm.getValue()));
            
            opm=opmdao.getAppSetting(OptionsAttributeManager.ENDDATEID);
            if(opm !=null)
            opf.setEndDate(DateManager.getMthDayYearFromMySqlDate(opm.getValue()));*/
            opm=opmdao.getAppSetting(OptionsAttributeManager.DBIMPORTACCESSID);
            if(opm !=null)
            opf.setXmlDbImport(opm.getValue());
            
            opm=opmdao.getAppSetting(OptionsAttributeManager.DISPLAYCLIENTRECORDINHTMLID);
            if(opm !=null)
            opf.setDisplayClientRecordInHtml(opm.getValue());
            opm=opmdao.getAppSetting(OptionsAttributeManager.DOWNLOADCLIENTRECORDINEXCELID);
            if(opm !=null)
            opf.setDownloadClientRecordInExcel(opm.getValue());
        }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return opf;
    }
}
