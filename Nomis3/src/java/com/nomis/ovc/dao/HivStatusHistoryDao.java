/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.HivStatusHistory;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface HivStatusHistoryDao 
{
    public void saveHivStatusHistory(HivStatusHistory hsh) throws Exception;
    public void updateHivStatusHistory(HivStatusHistory hsh) throws Exception;
    public void deleteHivStatusHistory(HivStatusHistory hsh) throws Exception;
    public HivStatusHistory getHivStatusHistory(int recordId) throws Exception;
    public HivStatusHistory getHivStatusHistory(String beneficiaryId, Date dateOfNewStatus) throws Exception;
    public List getHivStatusHistory(String beneficiaryId) throws Exception;
    public void changeBeneficiaryIdInHivStatusHistory(String oldOvcId, String newOvcId) throws Exception;
    public void deleteHivStatusRecordsOfChildrenAtRiskOfHivBeforeOctober2018() throws Exception;
}
