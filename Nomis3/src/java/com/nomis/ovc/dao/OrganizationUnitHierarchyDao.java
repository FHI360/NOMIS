/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.metadata.OrganizationUnitHierarchy;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface OrganizationUnitHierarchyDao 
{
    public void saveOrganizationUnitHierarchy(OrganizationUnitHierarchy ouh) throws Exception;
    public void updateOrganizationUnitHierarchy(OrganizationUnitHierarchy ouh) throws Exception;
    public void deleteOrganizationUnitHierarchy(OrganizationUnitHierarchy ouh) throws Exception;
    public OrganizationUnitHierarchy getOrganizationUnitHierarchy(String uid) throws Exception;
    public int getMaximumOuLevel() throws Exception;
    public List getAllOrganizationUnitHierarchyRecords() throws Exception;
    public OrganizationUnitHierarchy getOrganizationUnitHierarchyByName(String hierarchyName) throws Exception;
    public OrganizationUnitHierarchy getOrganizationUnitHierarchyByLevel(int oulevel) throws Exception;
}
