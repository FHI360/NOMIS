/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.UserPrivilege;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class UserPrivilegeManager 
{
    public static List getUserPrivileges()
    {
        List PrivilegeList=new ArrayList();
        PrivilegeList.add(getBeneficiaryRecordsUpdatePriviledge());
        PrivilegeList.add(getOrganizationUnitUpdatePriviledge());
        PrivilegeList.add(getUserSetupPriviledge());
        PrivilegeList.add(getUserRoleSetupPriviledge());
        PrivilegeList.add(getBulkDeleteRecordsPriviledge());
        
        PrivilegeList.add(getHouseholdRecordsMergePriviledge());
        PrivilegeList.add(getOrganizationUnitsMergePriviledge());
        PrivilegeList.add(getSiteTransferPriviledge());
        PrivilegeList.add(getPartnerChangeOperationPriviledge());
        PrivilegeList.add(getDataExportPriviledge());
        PrivilegeList.add(getMetaDataExportPriviledge());
        PrivilegeList.add(getDataImportPriviledge());
        PrivilegeList.add(getMetaDataImportPriviledge());
        PrivilegeList.add(getDatabaseDefragmentationUtilityPriviledge());
        PrivilegeList.add(getHealthFacilityUpdatePriviledge());
        PrivilegeList.add(getAdhocOperationsUtilityPriviledge());
        PrivilegeList.add(getAdminSetupUtilityPriviledge());
        
        //PrivilegeList.add(getUserPrivileges("datimreport","Access Datim report","datimreport"));
        return PrivilegeList;
    }
    public static UserPrivilege getBeneficiaryRecordsUpdatePriviledge()
    {
        UserPrivilege up=getUserPrivileges("dataentryxx","Add/Update beneficiary records","dataentry");
        return up;
    }
    public static UserPrivilege getOrganizationUnitUpdatePriviledge()
    {
        UserPrivilege up=getUserPrivileges("orguntsetup","Add/Update organization unit","orguntsetup");
        return up;
    }
    public static UserPrivilege getUserSetupPriviledge()
    {
        UserPrivilege up=getUserPrivileges("createusers","Add/Update user","usersetup");
        return up;
    }
    public static UserPrivilege getUserRoleSetupPriviledge()
    {
        UserPrivilege up=getUserPrivileges("adduserrole","Add/Update user role","userrolesetup");
        return up;
    }
    public static UserPrivilege getBulkDeleteRecordsPriviledge()
    {
        UserPrivilege up=getUserPrivileges("bdelete","Bulk delete records","bulkdelete");
        return up;
    }
    public static UserPrivilege getHouseholdRecordsMergePriviledge()
    {
        UserPrivilege up=getUserPrivileges("hhmerge","Merge Households","hhmerge");
        return up;
    }
    public static UserPrivilege getOrganizationUnitsMergePriviledge()
    {
        UserPrivilege up=getUserPrivileges("mergeou","Merge Organization units","mergeou");
        return up;
    }
    public static UserPrivilege getSiteTransferPriviledge()
    {
        UserPrivilege up=getUserPrivileges("siteTrans","Transition sites","sitetransfer");
        return up;
    }
    public static UserPrivilege getPartnerChangeOperationPriviledge()
    {
        UserPrivilege up=getUserPrivileges("chptner","Change Partner","partnerchange");
        return up;
    }
    public static UserPrivilege getDataExportPriviledge()
    {
        UserPrivilege up=getUserPrivileges("dataexportx","Export data","dataexport");
        return up;
    }
    public static UserPrivilege getMetaDataExportPriviledge()
    {
        UserPrivilege up=getUserPrivileges("mdataexport","Export meta data","metadataexport");
        return up;
    }
    public static UserPrivilege getDataImportPriviledge()
    {
        UserPrivilege up=getUserPrivileges("dataimportx","Import data","dataimport");
        return up;
    }
    public static UserPrivilege getMetaDataImportPriviledge()
    {
        UserPrivilege up=getUserPrivileges("mdataimport","Import meta data","metadataimport");
        return up;
    }
    public static UserPrivilege getDatabaseDefragmentationUtilityPriviledge()
    {
        UserPrivilege up=getUserPrivileges("defragmentx","Run Database defragmentation utility","dbdefragment");
        return up;
    }
    public static UserPrivilege getHealthFacilityUpdatePriviledge()
    {
        UserPrivilege up=getUserPrivileges("addfacility","Add/Update Referral facility","referralfacility");
        return up;
    }
    public static UserPrivilege getAdhocOperationsUtilityPriviledge()
    {
        UserPrivilege up=getUserPrivileges("adhocoperat","Execute Adhoc operations utility","adhocoperations");
        return up;
    }
    public static UserPrivilege getAdminSetupUtilityPriviledge()
    {
        UserPrivilege up=getUserPrivileges("adminsetupx","Setup Sites, Schools, Community workers and CBOs","adminsetuputility");
        return up;
    }
    public static UserPrivilege getSuperUserPriviledge()
    {
        UserPrivilege up=getUserPrivileges("101","Super user","dataentry");
        return up;
    }
    public static UserPrivilege getUserPrivileges(String PrivilegeId,String name,String type)
    {
        UserPrivilege up=new UserPrivilege();
        up.setPrivilegeId(PrivilegeId);
        up.setPrivilegeName(name);
        up.setPrivilegeType(type);
        return up;
    }
}
