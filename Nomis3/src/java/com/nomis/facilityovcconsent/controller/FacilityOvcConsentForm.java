/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.facilityovcconsent.controller;

import com.nomis.ovc.business.Beneficiary;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import com.nomis.ovc.util.ValidationManager;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smomoh
 */
public class FacilityOvcConsentForm extends org.apache.struts.action.ActionForm {
    
    private String clientUniqueId;
    private String actionName;
    private int clientAge;
    private int clientAgeUnit;
    private String clientSex;
    private String artNoOfClient;
    private String dateEnrolledOnTreatment;
    private String clientFirstName;
    private String clientSurname;
    private String hivTreatmentFacilityId;
    private String nameOfPersonToShareContactWith;
    private int shareContactQuestion;
    private String caregiverFirstName;
    private String caregiverSurname;
    private String dateCaregiverSigned;
    private String facilityStaffFirstName;
    private String facilityStaffSurname;
    private String dateFacilityStaffSigned;
    
    private String cboId;
    private String level2OuId;
    private String level3OuId;
    private String organizationUnitId;

    public FacilityOvcConsentForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getArtNoOfClient() {
        return artNoOfClient;
    }

    public void setArtNoOfClient(String artNoOfClient) {
        this.artNoOfClient = artNoOfClient;
    }

    public String getCaregiverFirstName() {
        return caregiverFirstName;
    }

    public void setCaregiverFirstName(String caregiverFirstName) {
        this.caregiverFirstName = caregiverFirstName;
    }

    public String getCaregiverSurname() {
        return caregiverSurname;
    }

    public void setCaregiverSurname(String caregiverSurname) {
        this.caregiverSurname = caregiverSurname;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public int getClientAge() {
        return clientAge;
    }

    public void setClientAge(int clientAge) {
        this.clientAge = clientAge;
    }

    public int getClientAgeUnit() {
        return clientAgeUnit;
    }

    public void setClientAgeUnit(int clientAgeUnit) {
        this.clientAgeUnit = clientAgeUnit;
    }

    public String getClientSex() {
        return clientSex;
    }

    public void setClientSex(String clientSex) {
        this.clientSex = clientSex;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientUniqueId() {
        return clientUniqueId;
    }

    public void setClientUniqueId(String clientUniqueId) {
        this.clientUniqueId = clientUniqueId;
    }

    public String getDateEnrolledOnTreatment() {
        return dateEnrolledOnTreatment;
    }

    public void setDateEnrolledOnTreatment(String dateEnrolledOnTreatment) {
        this.dateEnrolledOnTreatment = dateEnrolledOnTreatment;
    }

    public String getDateCaregiverSigned() {
        return dateCaregiverSigned;
    }

    public void setDateCaregiverSigned(String dateCaregiverSigned) {
        this.dateCaregiverSigned = dateCaregiverSigned;
    }

    public String getDateFacilityStaffSigned() {
        return dateFacilityStaffSigned;
    }

    public void setDateFacilityStaffSigned(String dateFacilityStaffSigned) {
        this.dateFacilityStaffSigned = dateFacilityStaffSigned;
    }
    
    public String getFacilityStaffFirstName() {
        return facilityStaffFirstName;
    }

    public void setFacilityStaffFirstName(String facilityStaffFirstName) {
        this.facilityStaffFirstName = facilityStaffFirstName;
    }

    public String getFacilityStaffSurname() {
        return facilityStaffSurname;
    }

    public void setFacilityStaffSurname(String facilityStaffSurname) {
        this.facilityStaffSurname = facilityStaffSurname;
    }
    
    public String getHivTreatmentFacilityId() {
        return hivTreatmentFacilityId;
    }

    public void setHivTreatmentFacilityId(String hivTreatmentFacilityId) {
        this.hivTreatmentFacilityId = hivTreatmentFacilityId;
    }

    public String getLevel2OuId() {
        return level2OuId;
    }

    public void setLevel2OuId(String level2OuId) {
        this.level2OuId = level2OuId;
    }

    public String getLevel3OuId() {
        return level3OuId;
    }

    public void setLevel3OuId(String level3OuId) {
        this.level3OuId = level3OuId;
    }

    public String getNameOfPersonToShareContactWith() {
        return nameOfPersonToShareContactWith;
    }

    public void setNameOfPersonToShareContactWith(String nameOfPersonToShareContactWith) {
        this.nameOfPersonToShareContactWith = nameOfPersonToShareContactWith;
    }

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }

    public int getShareContactQuestion() {
        return shareContactQuestion;
    }

    public void setShareContactQuestion(int shareContactQuestion) {
        this.shareContactQuestion = shareContactQuestion;
    }
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        clientUniqueId=null;
        actionName=null;
        artNoOfClient=null;
        clientFirstName=null;
        clientSurname=null;
        hivTreatmentFacilityId=null;
        nameOfPersonToShareContactWith=null;
        shareContactQuestion=0;
        caregiverFirstName=null;
        caregiverSurname=null;
        dateCaregiverSigned=null;
        facilityStaffFirstName=null;
        facilityStaffSurname=null;
        dateFacilityStaffSigned=null;
        clientAge=0;
        clientAgeUnit=0;
        clientSex=null;
    }
    
    

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        ValidationManager vm=new ValidationManager();
        Date dateOfEnrollment=null;
        Date ovcOfferDate=null;
        if(getActionName()==null || getActionName().equalsIgnoreCase("level3OuList") || getActionName().equalsIgnoreCase("level4OuList") || getActionName().equalsIgnoreCase("clientDetails") || getActionName().equalsIgnoreCase("delete"))
        return errors;
        
        Beneficiary beneficiary=vm.getBeneficiaryByTreatmentId(this.getArtNoOfClient());
        
        if(this.getOrganizationUnitId()==null || getOrganizationUnitId().equalsIgnoreCase("select"))
        errors.add("organizationUnitId", new ActionMessage("errors.organizationUnitId.required"));
        else if(this.getArtNoOfClient()==null || this.getArtNoOfClient().trim().length()==0)
        errors.add("artNoOfClient", new ActionMessage("errors.treatmentId.required"));
        else if(this.getDateEnrolledOnTreatment()==null || !ValidationManager.isValidDate(getDateEnrolledOnTreatment()))
        errors.add("dateEnrolledOnTreatment", new ActionMessage("errors.dateEnrolledOnTreatment.required"));
        
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateEnrolledOnTreatment()))
        errors.add("dateEnrolledOnTreatment", new ActionMessage("errors.dateEnrolledOnTreatment.postdated"));
        
        else if(!vm.isTreatmentIdAvailable(this.getArtNoOfClient(), this.getClientUniqueId()))
        errors.add("artNoOfClient", new ActionMessage("errors.treatmentId.notAvailable"));
        
        else if(beneficiary !=null)
        {
            dateOfEnrollment=beneficiary.getDateOfEnrollment();
            ovcOfferDate=DateManager.getDateInstance(DateManager.processMthDayYearToMysqlFormat(this.getDateEnrolledOnTreatment()));
            if(!vm.isOvcOfferDateBeforeDateOfEnrollment(ovcOfferDate, dateOfEnrollment))
            errors.add("dateEnrolledOnTreatment", new ActionMessage("errors.dateOfEnrollmentForOvcOffer.invalid"));
        }
        
        else if(this.getClientFirstName()==null || this.getClientFirstName().trim().length()==0)
        errors.add("clientFirstName", new ActionMessage("errors.clientFirstName.required"));
        else if(this.getClientSurname()==null || this.getClientSurname().trim().length()==0)
        errors.add("clientSurnameName", new ActionMessage("errors.clientSurnameName.required"));
        else if(this.getClientAge()==0 || this.getClientAgeUnit()==0)
        errors.add("clientAge", new ActionMessage("errors.ageAtBaseline.required"));
        else if(this.getClientAgeUnit()==0)
        errors.add("clientAgeUnit", new ActionMessage("errors.ageUnitAtBaseline.required"));
        else if(this.getClientAge()>11 && this.getClientAgeUnit()==AppConstant.AGEUNIT_MONTH_NUM)
        errors.add("clientAgeUnit", new ActionMessage("errors.ageUnitAtBaseline.mismatch"));
        else if(this.getClientSex()==null || this.getClientSex().equalsIgnoreCase("select"))
        errors.add("clientSex", new ActionMessage("errors.sex.required"));
        
        else if(this.getCaregiverFirstName()==null || this.getCaregiverFirstName().trim().length()<3)
        errors.add("caregiverFirstName", new ActionMessage("errors.caregiverFirstName.required"));
        else if(this.getCaregiverSurname()==null || this.getCaregiverSurname().trim().length()<3)
        errors.add("caregiverSurname", new ActionMessage("errors.caregiverSurname.required"));
        else if(this.getCaregiverSurname()==null || this.getCaregiverSurname().trim().length()<3)
        errors.add("caregiverSurname", new ActionMessage("errors.caregiverSurname.required"));
        
        else if(this.getDateCaregiverSigned()==null || !ValidationManager.isValidDate(getDateCaregiverSigned()))
        errors.add("dateCaregiverSigned", new ActionMessage("errors.dateCaregiverSigned.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateCaregiverSigned()))
        errors.add("dateCaregiverSigned", new ActionMessage("errors.dateCaregiverSigned.postdated"));
        if(DateManager.getDateInstanceFromMthDayYearFormat(this.getDateEnrolledOnTreatment()).after(DateManager.getDateInstanceFromMthDayYearFormat(this.getDateCaregiverSigned())))
        errors.add("dateCaregiverSigned", new ActionMessage("errors.dateCaregiverSigned.afterDateEnrolledOnTreatment"));
        
        else if(this.getDateFacilityStaffSigned()==null || !ValidationManager.isValidDate(getDateFacilityStaffSigned()))
        errors.add("dateFacilityStaffSigned", new ActionMessage("errors.dateFacilityStaffSigned.required"));
        else if(!ValidationManager.dateBeforeOrEqualToCurrentDate(getDateFacilityStaffSigned()))
        errors.add("dateFacilityStaffSigned", new ActionMessage("errors.dateFacilityStaffSigned.postdated"));
        if(DateManager.getDateInstanceFromMthDayYearFormat(this.getDateEnrolledOnTreatment()).after(DateManager.getDateInstanceFromMthDayYearFormat(this.getDateFacilityStaffSigned())))
        errors.add("dateFacilityStaffSigned", new ActionMessage("errors.dateFacilityStaffSigned.afterDateEnrolledOnTreatment"));
        
        else if(this.getCboId()==null || this.getCboId().equalsIgnoreCase("select"))
        errors.add("cboId", new ActionMessage("errors.cboId.required"));
        else if(this.getFacilityStaffFirstName()==null || this.getFacilityStaffFirstName().trim().length()==0)
        errors.add("facilityStaffFirstName", new ActionMessage("errors.facilityStaffFirstName.required"));
        else if(this.getFacilityStaffSurname()==null || this.getFacilityStaffSurname().trim().length()==0)
        errors.add("facilityStaffSurname", new ActionMessage("errors.facilityStaffSurname.required"));
        else if(this.getHivTreatmentFacilityId()==null || this.getHivTreatmentFacilityId().equalsIgnoreCase("select"))
        errors.add("hivTreatmentFacilityId", new ActionMessage("errors.hivTreatmentFacilityId.required"));
        else if(this.getNameOfPersonToShareContactWith()==null || this.getNameOfPersonToShareContactWith().trim().length()==0)
        errors.add("nameOfPersonToShareContactWith", new ActionMessage("errors.nameOfPersonToShareContactWith.required"));
        else if(this.getShareContactQuestion()==0)
        errors.add("shareContactQuestion", new ActionMessage("errors.shareContactQuestion.required"));
        return errors;
    }
}
