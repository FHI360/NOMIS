/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.metadata.OrganizationUnit;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class ReportOrganizationUnitDaoImpl implements ReportOrganizationUnitDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    SubQueryGenerator sqg=new SubQueryGenerator();
    public List getLevel2OuForReports() throws Exception
    {
        System.err.println("Inside ReportOrganizationUnitDaoImpl.getLevel2OuForReports()");
        List level2OuList=new ArrayList();
        List ouPathList=getLevel4OuPathsFromHouseholdEnrollment();
        if(ouPathList !=null)
        {
            //System.err.println("Inside getLevel2OuForReports(), ouPathList size is "+ouPathList.size());
            OrganizationUnit ou=null;
            String oupath=null;
            //String pathSeperator="/";
            String uid=null;
            List ouIdList=new ArrayList();
            for(int i=0; i<ouPathList.size(); i++)
            {
                oupath=ouPathList.get(i).toString();
                //System.err.println("Inside getLevel2OuForReports(), oupath is "+oupath);
                if(oupath !=null)
                {
                    String[] ouIdArray=getOrgUnitIdsFromOuPath(oupath);
                    if(ouIdArray.length>1)
                    {
                        uid=ouIdArray[1];
                        if(!ouIdList.contains(uid))
                        {
                            ou=getOrganizationUnit(uid);
                            if(ou !=null)
                            level2OuList.add(ou);
                            ouIdList.add(uid);
                        }
                    }
                }
            }
        }
        if(level2OuList==null)
        level2OuList=new ArrayList();
        System.err.println("Inside getLevel2OuForReports(), level2OuList size is "+level2OuList.size());
        return level2OuList;
    }
    public List getLevel3OuForReports(String parentId) throws Exception
    {
       List level3OuList=new ArrayList(); 
       List ouPathList=getLevel4OuPathsFromHouseholdProfileByParentId(parentId);
       if(ouPathList !=null)
        {
            OrganizationUnit ou=null;
            String oupath=null;
            //String pathSeperator="\\";
            String uid=null;
            List ouIdList=new ArrayList();
            for(int i=0; i<ouPathList.size(); i++)
            {
                oupath=ouPathList.get(i).toString();
                if(oupath !=null)
                {
                    String[] ouIdArray=getOrgUnitIdsFromOuPath(oupath);
                    if(ouIdArray.length>2)
                    {
                        uid=ouIdArray[2];
                        if(!ouIdList.contains(uid))
                        {
                            ou=getOrganizationUnit(uid);
                            if(ou !=null)
                            level3OuList.add(ou);
                            ouIdList.add(uid);
                        }
                    }
                }
            }
        }
       if(level3OuList==null)
        level3OuList=new ArrayList();
       return level3OuList;
    }
    public List getLevel4OuForReports(String parentId) throws Exception
    {
        //System.err.println("Inside getLevel4OuForReports ");
        List level4OuList=new ArrayList();
        List ouPathList=getLevel4OuPathsFromHouseholdProfileByParentId(parentId);
        if(ouPathList !=null)
        {
            //System.err.println("Inside getLevel4OuForReports ouPathList is not null and size is "+ouPathList.size());
            OrganizationUnit ou=null;
            String oupath=null;
            String pathSeperator="\\";
            String uid=null;
            List ouIdList=new ArrayList();
            for(int i=0; i<ouPathList.size(); i++)
            {
                oupath=ouPathList.get(i).toString();
                if(oupath !=null)
                {
                    //System.err.println("Inside getLevel4OuForReports oupath is "+oupath);
                    String[] ouIdArray=getOrgUnitIdsFromOuPath(oupath);
                    if(ouIdArray.length>3)
                    {
                        uid=ouIdArray[3];
                        if(!ouIdList.contains(uid))
                        {
                            ou=getOrganizationUnit(uid);
                            if(ou !=null)
                            level4OuList.add(ou);
                            ouIdList.add(uid);
                        }
                    }
                }
            }
        }
        if(level4OuList==null)
        level4OuList=new ArrayList();
        //System.err.println("Inside getLevel4OuForReports level4OuList size is "+level4OuList.size());
        return level4OuList;
    }
    public String[] getOrgUnitIdsFromOuPath(String oupath)
    {
        while(oupath.indexOf("\\") !=-1)
        {
            oupath=oupath.replace("\\", "/");
        }
        String[] ouIdArray=oupath.split("/");
        return ouIdArray;
    }
    public OrganizationUnit getOrganizationUnit(String uid) throws Exception
    {
        DaoUtility util=new DaoUtility();
        OrganizationUnit ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(uid);
        return ou;
    }
    public List getLevel4OuListFromHouseholdEnrollment() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct hhe.organizationUnit from HouseholdEnrollment hhe").list();
            tx.commit();
            closeSession(session);
         }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list;
    }
    public List getLevel4OuPathsFromHouseholdEnrollment() throws Exception
    {
       List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("select distinct ou.ouPath "+SubQueryGenerator.getHheOrganizationUnitQuery()).list();
            tx.commit();
            closeSession(session);
         }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list; 
    }
    public List getLevel4OuPathsFromHouseholdProfileByParentId(String parentId) throws Exception
    {
       List list=null;
        try
        {
            String query="select distinct ou.ouPath "+SubQueryGenerator.getHheOrganizationUnitQuery()+" and ou.ouPath like '%"+parentId+"%'";
            //System.err.println("query in getLevel4OuPathsFromHouseholdProfileByParentId is "+query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
         }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list; 
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
