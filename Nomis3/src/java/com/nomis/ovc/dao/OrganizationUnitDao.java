/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.metadata.OrganizationUnit;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface OrganizationUnitDao 
{
    public void saveOrganizationUnit(OrganizationUnit ou) throws Exception;
    public void updateOrganizationUnit(OrganizationUnit ou) throws Exception;
    public void deleteOrganizationUnit(OrganizationUnit ou) throws Exception;
    public OrganizationUnit getOrganizationUnit(String uid) throws Exception;
    public OrganizationUnit getOrganizationUnitByName(String ouname) throws Exception;
    public List getAllOrganizationUnit() throws Exception;
    public List getOrganizationUnitsByOuLevel(int oulevel) throws Exception;
    public List getOrganizationUnityByParentId(String pid) throws Exception;
    public OrganizationUnit getOrganizationUnitByNameAndLevel(String ouname,int level) throws Exception;
    public OrganizationUnit createWard(OrganizationUnit parentOu,String wardName,String ouCode,String legacyId) throws Exception;
    public OrganizationUnit getOrganizationUnitByParentIdAndChildName(String pid,String ouname) throws Exception;
    public List getParentOuList(List ouList) throws Exception;
    public OrganizationUnit getOrganizationUnitByOuCodeAndOuLevel(String oucode,int ouLevel) throws Exception;
    public OrganizationUnit getOrganizationUnitByLegacyId(String legacyId) throws Exception;
    public List getOrganizationUnitByOuPath(String ouPath) throws Exception;
    public String generateOuCode(String parentId,String ouName,int ouLevel) throws Exception;
    public List getOrganizationUnitsByOuCodeAndOuLevel(String oucode,int ouLevel) throws Exception;
    public String generateRandomOuCode(int ouLevel) throws Exception;
    public List getDistinctOrganizationUnitsByOuLevel(int ouLevel) throws Exception;
    public OrganizationUnit createTemporaryLevel4OrganizationUnit(String level4OuId,String proposedName) throws Exception;
    public OrganizationUnit generateOuName(OrganizationUnit ou) throws Exception;
}
