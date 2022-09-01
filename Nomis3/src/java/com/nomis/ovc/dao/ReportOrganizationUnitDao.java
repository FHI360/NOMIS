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
public interface ReportOrganizationUnitDao 
{
    public List getLevel4OuListFromHouseholdEnrollment() throws Exception;
    public List getLevel4OuPathsFromHouseholdEnrollment() throws Exception;
    public List getLevel2OuForReports() throws Exception;
    public List getLevel3OuForReports(String parentId) throws Exception;
    public List getLevel4OuForReports(String parentId) throws Exception;
    public OrganizationUnit getOrganizationUnit(String uid) throws Exception;
    public List getLevel4OuPathsFromHouseholdProfileByParentId(String parentId) throws Exception;
}
