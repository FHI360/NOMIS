/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.CommunityBasedOrganization;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface CommunityBasedOrganizationDao 
{
    public void saveCommunityBasedOrganization(CommunityBasedOrganization cbo) throws Exception;
    public void updateCommunityBasedOrganization(CommunityBasedOrganization cbo) throws Exception;
    public void deleteCommunityBasedOrganization(CommunityBasedOrganization cbo) throws Exception;
    public CommunityBasedOrganization getCommunityBasedOrganization(String uniqueId) throws Exception;
    public List getAllCommunityBasedOrganization() throws Exception;
    public List getCommunityBasedOrganizationByLevel2OrgUnit(String level2OuId) throws Exception;
    public List getCommunityBasedOrganizationByAssignedLevel3OrgUnit(String level3OuId) throws Exception;
    public int updateCBOsWithWrongLevel2OuId(String wrongLevel2OuId,String correctLevel2OuId) throws Exception;
    public CommunityBasedOrganization getCommunityBasedOrganizationByName(String cboName) throws Exception;
    public CommunityBasedOrganization getCommunityBasedOrganizationByLevel2OuIdAndCboName(String level2OuId,String cboName) throws Exception;
    public CommunityBasedOrganization createCommunityBasedOrganization(String level2OuId,String cboCode,String cboName,String userName,String legacyId) throws Exception;
    public CommunityBasedOrganization getCommunityBasedOrganizationByLegacyId(String legacyId) throws Exception;
}
