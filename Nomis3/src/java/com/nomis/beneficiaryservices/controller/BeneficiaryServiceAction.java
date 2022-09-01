/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.beneficiaryservices.controller;

import com.nomis.operationsManagement.AccessManager;
import com.nomis.operationsManagement.UserActivityManager;
import com.nomis.operationsManagement.UserPrivilegeManager;
import com.nomis.ovc.business.Service;
import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.AppManager;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
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
public class BeneficiaryServiceAction extends org.apache.struts.action.Action {

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
        BeneficiaryServiceForm bsform=(BeneficiaryServiceForm)form;
        HttpSession session=request.getSession();
        //session.setAttribute("mainHivStatus", HivPropertiesManager.getThreeMainHivStatus());
               
        String moduleName="Beneficiary service setup";
        DaoUtility util=new DaoUtility();
        AppManager appManager=new AppManager();
        String userName=appManager.getCurrentUserName(session);
        
        User user=appManager.getCurrentUser(session);
        if(AccessManager.isUserInRole(user,UserPrivilegeManager.getAdminSetupUtilityPriviledge().getPrivilegeId()))
        {
            setButtonState(session,"false","true");
        }
        else
        {
            setButtonState(session,"true","true");
            request.setAttribute("accessErrorMsg", AppConstant.DEFAULT_ACCESS_MSG);
            return mapping.findForward(SUCCESS);
        }
        loadService(session);
        String requiredAction=bsform.getActionName();
        String param=request.getParameter("p");
        String serviceId=request.getParameter("id");
        if(param !=null)
        requiredAction=param;
        System.err.println("requiredAction is "+requiredAction);
        if(requiredAction==null)
        {
            bsform.reset(mapping, request);
            
            return mapping.findForward(SUCCESS);
        }//getBeneficiaryServiceForm(BeneficiaryServiceForm form)
        else if(requiredAction.equalsIgnoreCase("details"))
        {
            bsform.reset(mapping, request);
            bsform.setServiceId(serviceId);
            Service service=util.getBeneficiaryServiceDaoInstance().getBeneficiaryService(serviceId);
            if(service !=null)
            {
                System.err.println("service is not null "+service.getServiceName());
                bsform.setBeneficiaryType(service.getBeneficiaryType());
                bsform.setDisplayStatus(service.getDisplayStatus());
                bsform.setDomain(service.getDomainId());
                bsform.setServiceCode(service.getServiceCode());
                bsform.setServiceId(service.getServiceId());
                bsform.setServiceName(service.getServiceName());
                setButtonState(session,"true","false");
            }
           //bsform=getBeneficiaryServiceForm(bsform); 
        }
        else if(requiredAction.equalsIgnoreCase("save"))
        {
            Service service=getService(bsform,userName);
            service.setServiceCode(getServiceCode(service.getDomainId()));
            util.getBeneficiaryServiceDaoInstance().saveBeneficiaryService(service);
            saveUserActivity(userName,moduleName,"Saved Beneficiary service record with name "+service.getServiceName());
            loadService(session);
        }
        else if(requiredAction.equalsIgnoreCase("update"))
        {
            Service service=getService(bsform,userName);
            service.setServiceId(bsform.getServiceId());
            util.getBeneficiaryServiceDaoInstance().updateBeneficiaryService(service);
            saveUserActivity(userName,moduleName,"Modified Beneficiary service record with name "+service.getServiceName());
            loadService(session);
        }
        else if(requiredAction.equalsIgnoreCase("delete"))
        {
            Service service=getService(bsform,userName);
            service.setServiceId(bsform.getServiceId());
            util.getBeneficiaryServiceDaoInstance().markForDelete(service);
            saveUserActivity(userName,moduleName,"Marked Beneficiary service record with name "+service.getServiceName()+" for delete");
            loadService(session);
        }
        return mapping.findForward(SUCCESS);
    }
    private void setButtonState(HttpSession session,String saveDisabled,String modifyDisabled)
    {
        if(AppUtility.isMetadataAccessEnabled())
        {
            session.setAttribute("bsfSaveDisabled", saveDisabled);
            session.setAttribute("bsfModifyDisabled", modifyDisabled);
        }
        else
        {
           session.setAttribute("bsfSaveDisabled", "true");
           session.setAttribute("bsfModifyDisabled", "true"); 
        }
    }
    private void loadService(HttpSession session) throws Exception
    {
        DaoUtility util=new DaoUtility();
        List list=util.getBeneficiaryServiceDaoInstance().getAllBeneficiaryServices();
        if(list ==null)
        list=new ArrayList();
        session.setAttribute("beneficiaryServiceList", list);
    }
    private Service getService(BeneficiaryServiceForm form,String userName) throws Exception
    {
        Service service=new Service();
        service.setBeneficiaryType(form.getBeneficiaryType());
        service.setDateCreated(DateManager.getCurrentDateInstance());
        service.setDomainId(form.getDomain());
        service.setLastModifiedDate(DateManager.getCurrentDateInstance());
        service.setRecordedBy(userName);
        service.setServiceName(form.getServiceName());
        service.setServiceCode(form.getServiceCode());
        service.setDisplayStatus(form.getDisplayStatus());
        return service;
    }
    private BeneficiaryServiceForm getBeneficiaryServiceForm(BeneficiaryServiceForm form) throws Exception
    {
        DaoUtility util=new DaoUtility();
        System.err.println("form.getServiceId() is "+form.getServiceId());
        Service service=util.getBeneficiaryServiceDaoInstance().getBeneficiaryService(form.getServiceId());
        if(service !=null)
        {
            System.err.println("service is not null "+service.getServiceName());
            form.setBeneficiaryType(service.getBeneficiaryType());
            form.setDisplayStatus(service.getDisplayStatus());
            form.setDomain(service.getDomainId());
            form.setServiceCode(service.getServiceCode());
            form.setServiceId(service.getServiceId());
            form.setServiceName(service.getServiceName());
        }
        return form;
    }
    private String getServiceCode(String domainId) throws Exception
    {
        String serviceCode=null;
        DaoUtility util=new DaoUtility();
        if(domainId !=null)
        {
            String domainCharacter=null;
            serviceCode="a";
            if(domainId.equalsIgnoreCase("HLT"))
            domainCharacter="h";
            else if(domainId.equalsIgnoreCase("SCH"))
            domainCharacter="e";
            else if(domainId.equalsIgnoreCase("STB"))
            domainCharacter="t";
            else if(domainId.equalsIgnoreCase("SFT"))
            domainCharacter="s";
                
            
            for(int i=1; i<1000; i++)
            {
                serviceCode="a"+i+domainCharacter;
                Service service=util.getBeneficiaryServiceDaoInstance().getBeneficiaryServiceByServiceCode(serviceCode);
                if(service==null)
                    break;
            }
        }
        return serviceCode;
    }
    private void saveUserActivity(String userName,String userAction,String description)
    {
        UserActivityManager uam=new UserActivityManager();
        uam.saveUserActivity(userName, userAction,description);
    }
}
