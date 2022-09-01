/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.FacilityOvcOffer;
import com.nomis.ovc.business.Ovc;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface FacilityOvcOfferDao 
{
    public void saveFacilityOvcOffer(FacilityOvcOffer foo) throws Exception;
    public void updateFacilityOvcOffer(FacilityOvcOffer foo) throws Exception;
    public void deleteFacilityOvcOffer(FacilityOvcOffer foo) throws Exception;
    public void markForDelete(FacilityOvcOffer foo) throws Exception;
    public FacilityOvcOffer getFacilityOvcOffer(String clientUniqueId) throws Exception;
    public int getNumberOfClientsOfferedOvcEnrollmentFromFacility(ReportParameterTemplate rpt,int startAge,int endAge,String startDate,String endDate,String sex) throws Exception;
    public FacilityOvcOffer getFacilityOvcOfferByTreatmentId(String treatmentId) throws Exception;
    public List getFacilityOvcOfferedRecordsForExport(ReportParameterTemplate rpt) throws Exception;
    public List getFacilityOvcOfferedRecords(ReportParameterTemplate rpt) throws Exception;
    public int updateChildEnrollmentRecordsForOVCOfferedClients() throws Exception;
    public Ovc getOvcFromFacilityOvcOfferInformation(Ovc ovc,FacilityOvcOffer foo) throws Exception;
}
