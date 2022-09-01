/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.fhi.nomis.logs.NomisLogManager;
import com.nomis.ovc.business.HouseholdEnrollment;
import com.nomis.ovc.metadata.OrganizationUnit;
import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DateManager;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class OrganizationUnitDaoImpl implements OrganizationUnitDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    public List getParentOuList(List ouList) throws Exception
    {
        List parentOuList=new ArrayList();
        try
        {
            DaoUtility util=new DaoUtility();
            OrganizationUnit parentOu=null;
            OrganizationUnit ou=null;
            if(ouList !=null)
            {
                List uniqueParentOuIdList=new ArrayList();
                String ouId=null;
                for(Object obj:ouList)
                {
                    ouId=(String)obj;
                    ou=util.getOrganizationUnitDaoInstance().getOrganizationUnit(ouId);
                    if(ou !=null)
                    {
                        parentOu=util.getOrganizationUnitDaoInstance().getOrganizationUnit(ou.getParentId());
                        if(parentOu !=null && !uniqueParentOuIdList.contains(parentOu.getUid()))
                        {
                            parentOuList.add(parentOu);
                            uniqueParentOuIdList.add(parentOu.getUid());
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            NomisLogManager.logStackTrace(ex);
        }
        return parentOuList;
    }
    public void saveOrganizationUnit(OrganizationUnit ou) throws Exception
    {
        try
        {
            if(ou !=null && this.getOrganizationUnitByName(ou.getName()) ==null)
            {
                OrganizationUnit existingOu=null;
                if(ou.getOucode() !=null)
                {
                    //All National, State, LGA (Levels 1,2 and 3) codes must be unique at their levels. Others below are only required to be unique at parent org unit level
                    if(ou.getOulevel()<4)
                    existingOu=getOrganizationUnitByOuCodeAndOuLevel(ou.getOucode(),ou.getOulevel());
                    else
                    existingOu=getOrganizationUnitByOuCodeAndParentId(ou.getOucode(),ou.getParentId());
                }
                if(existingOu==null)
                {
                    if(ou.getUid()==null || ou.getUid().trim().length() !=11)
                    {
                        String uid=getUniqueRecordId();
                        ou.setUid(uid);
                    }
                    if(ou.getOulevel()==1)
                    ou.setParentId(ou.getUid());
                    ou.setOuPath(getOuPath(ou));
                    if(ou.getDateCreated()==null)
                    ou.setDateCreated(DateManager.getCurrentDateInstance());
                    if(ou.getLastModifiedDate()==null)
                    ou.setLastModifiedDate(DateManager.getCurrentDateInstance());
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.save(ou);
                    tx.commit();
                    closeSession(session);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
    }
    private void updateOrganizationUnitOnly(OrganizationUnit ou) throws Exception
    {
        try
        {
            if(ou !=null && this.getOrganizationUnit(ou.getUid()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.update(ou);
                tx.commit();
                closeSession(session);
                //System.err.println(ou.getName()+" updated in updateOrganizationUnitOnly(OrganizationUnit ou");
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
    }
    public void updateOrganizationUnit(OrganizationUnit ou) throws Exception
    {
        try
        {
            if(ou !=null && this.getOrganizationUnit(ou.getUid()) !=null)
            {
                if(ou.getUid() !=null && ou.getOuPath() !=null && ou.getName() !=null)
                {
                    //parent Id is not required for level 1 org unit
                    if(ou.getOulevel()>1 && ou.getParentId() ==null )
                    return;
                    else
                    {
                        ou.setOuPath(getOuPath(ou));
                        OrganizationUnit ou2=getOrganizationUnitByName(ou.getName());
                        if(ou2 !=null)
                        {
                            if(ou.getUid() !=null && ou.getUid().equalsIgnoreCase(ou2.getUid()))
                            {
                                if(ou.getOulevel()==1 && ou.getParentId()==null)
                                ou.setParentId(ou.getUid());
                                session = HibernateUtil.getSession();
                                tx = session.beginTransaction();
                                session.update(ou);
                                tx.commit();
                                closeSession(session);
                                updateChildrenPath(ou);
                            }
                        }
                        else
                        {
                            if(getOrganizationUnit(ou.getUid()) !=null)
                            {
                                if(ou.getOulevel()==1 && ou.getParentId()==null)
                                ou.setParentId(ou.getUid());
                                session = HibernateUtil.getSession();
                                tx = session.beginTransaction();
                                session.update(ou);
                                tx.commit();
                                closeSession(session);
                                updateChildrenPath(ou);
                            }
                        }
                    }
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
    }
    public void updateChildrenPath(OrganizationUnit ou) throws Exception
    {
        List list=this.getOrganizationUnityByParentId(ou.getUid());
        //System.err.println("Inside updateChildrenPath(OrganizationUnit ou)");
        if(list !=null && !list.isEmpty())
        {
            //System.err.println("list.size() is "+list.size());
            for(Object obj:list)
            {
                OrganizationUnit ou2=(OrganizationUnit)obj;
                ou2.setParentId(ou.getUid());
                ou2.setOuPath(this.getOuPath(ou2));
                //ou2.setOuPath(ou.getOuPath()+"\\"+ou2.getUid());
                updateOrganizationUnitOnly(ou2);
            }
        }
    }
    public void deleteOrganizationUnit(OrganizationUnit ou) throws Exception
    {
        try
        {
            if(ou !=null)
            {
                OrganizationUnit ou2=getOrganizationUnit(ou.getUid());
                if(ou2 !=null)
                {
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.delete(ou);
                    tx.commit();
                    closeSession(session);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
    }
    public String getOuPath(OrganizationUnit ou) throws Exception
    {
        String oupath=null;
        //String fileSeperator="/";
        if(ou !=null)
        {
            if(ou.getOulevel()==1)
            oupath=ou.getUid();
            else
            {
               OrganizationUnit parent=getOrganizationUnit(ou.getParentId());
               //System.err.println("parent is "+parent);
               if(parent !=null && parent.getOuPath() !=null)
               {
                    oupath=parent.getOuPath()+"/"+ou.getUid();
                    System.err.println("parent.getOuPath() is "+parent.getOuPath());
               }
            }
            //System.err.println("oupath is "+oupath);
        }
        return oupath;
    }
    /*public String getOuPath(OrganizationUnit ou) throws Exception
    {
        String oupath=null;
        if(ou !=null)
        {
            //AppUtility appUtil=new AppUtility();
            OrganizationUnit parent=null;
            OrganizationUnit ou2=null;
            String fileSeperator="/";//appUtil.getFileSeperator();
            String parentId=ou.getParentId();
            String path=ou.getUid();
            //System.err.println("Uid is "+ou.getUid());
            //System.err.println("parentId is "+parentId);
            ou2=ou;
            while(!ou2.getUid().equalsIgnoreCase(parentId))
            {
                path=parentId+fileSeperator+path;
                System.err.println("path is "+path);
                parent=getOrganizationUnit(parentId);
                if(parent !=null)
                {
                    ou2=parent;
                    parentId=ou2.getParentId();
                    //path=ou2.getUid()+fileSeperator+path;
                    
                }
            }
            oupath=path;
        }
        return oupath;
    }*/
    public OrganizationUnit getOrganizationUnit(String uid) throws Exception
    {
        OrganizationUnit ou=null;
        try
        {
            if(uid !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from OrganizationUnit ou where ou.uid=:id").setString("id", uid).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    ou=(OrganizationUnit)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ou;
    }
    public OrganizationUnit getOrganizationUnitByOuCodeAndOuLevel(String oucode,int ouLevel) throws Exception
    {
        OrganizationUnit ou=null;
        try
        {
            if(oucode !=null)
            {
                oucode=oucode.toUpperCase();
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from OrganizationUnit ou where ou.oucode=:cd and ou.oulevel=:oul").setString("cd", oucode).setInteger("oul", ouLevel).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    ou=(OrganizationUnit)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ou;
    }
    public List getDistinctOrganizationUnitsByOuLevel(int ouLevel) throws Exception
    {
        List list=null;
        try
        {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("select distinct ou.uid from OrganizationUnit ou where ou.oulevel=:oul").setInteger("oul", ouLevel).list();
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
    public List getOrganizationUnitsByOuCodeAndOuLevel(String oucode,int ouLevel) throws Exception
    {
        List list=null;
        try
        {
            if(oucode !=null)
            {
                oucode=oucode.toUpperCase();
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                list = session.createQuery("from OrganizationUnit ou where ou.oucode=:cd and ou.oulevel=:oul").setString("cd", oucode).setInteger("oul", ouLevel).list();
                tx.commit();
                closeSession(session);
                
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return list;
    }
    public OrganizationUnit getOrganizationUnitByOuCodeAndParentId(String oucode,String parentId) throws Exception
    {
        OrganizationUnit ou=null;
        try
        {
            if(oucode !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from OrganizationUnit ou where ou.oucode=:cd and ou.parentId=:pid").setString("cd", oucode).setString("pid", parentId).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    ou=(OrganizationUnit)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ou;
    }
    public OrganizationUnit getOrganizationUnitByName(String ouname) throws Exception
    {
        OrganizationUnit ou=null;
        try
        {
            if(ouname !=null)
            {
                ouname=ouname.trim().toUpperCase();
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from OrganizationUnit ou where UPPER(ou.name)=:oun").setString("oun", ouname).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    ou=(OrganizationUnit)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ou;
    }
    public OrganizationUnit getOrganizationUnitByNameAndLevel(String ouname,int level) throws Exception
    {
        OrganizationUnit ou=null;
        try
        {
            if(ouname !=null)
            {
                ouname=ouname.trim().toUpperCase();
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                List list = session.createQuery("from OrganizationUnit ou where UPPER(ou.name)=:oun and ou.oulevel=:lv").setString("oun", ouname).setInteger("lv", level).list();
                tx.commit();
                closeSession(session);
                if(list !=null && !list.isEmpty())
                {
                    ou=(OrganizationUnit)list.get(0);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ou;
    }
    public List getAllOrganizationUnit() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from OrganizationUnit ou order by ou.oulevel, ou.name").list();
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
    public List getOrganizationUnitsByOuLevel(int oulevel) throws Exception
    {
       List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from OrganizationUnit ou where ou.oulevel=:lv order by ou.name").setInteger("lv", oulevel).list();
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
    public OrganizationUnit getOrganizationUnitByParentIdAndChildName(String pid,String ouname) throws Exception
    {
       OrganizationUnit ou=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from OrganizationUnit ou where ou.parentId=:pid and ou.name=:oun").setString("pid", pid).setString("oun", ouname).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ou=(OrganizationUnit)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ou;
    }
    public List getOrganizationUnityByParentId(String pid) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from OrganizationUnit ou where ou.parentId=:pid").setString("pid", pid).list();
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
    public List getOrganizationUnitByOuPath(String ouPath) throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from OrganizationUnit ou where ou.ouPath like '%"+ouPath+"%' order by ou.oulevel, ou.name").list();
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
    public OrganizationUnit getOrganizationUnitByLegacyId(String legacyId) throws Exception
    {
       OrganizationUnit ou=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from OrganizationUnit ou where ou.legacyId=:lid").setString("lid", legacyId).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                ou=(OrganizationUnit)list.get(0);
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return ou;
    }
public OrganizationUnit createTemporaryLevel4OrganizationUnit(String level4OuId,String proposedName) throws Exception
{
    int count=0;
    int ouLevel=0;
    int created=0;
    HouseholdEnrollment hhe=null;
    String enrollmentId=null;
    String level2OuCode=null;
    String level3OuCode=null;
    OrganizationUnit level2Ou=null;
    OrganizationUnit level3Ou=null;
    OrganizationUnit level4Ou=null;
    List hheList=null;
    String ouCode=null;
    try
    {
        OrganizationUnit ou=getOrganizationUnit(level4OuId);
        if(ou==null)
        {
            HouseholdEnrollmentDao dao=new HouseholdEnrollmentDaoImpl();
            count++;
            //System.err.println("Ward Id does not exist");
            hheList=dao.getHouseholdEnrollmentByOrgUnit(level4OuId);
            if(hheList !=null && !hheList.isEmpty())
            {
                hhe=(HouseholdEnrollment)hheList.get(0);
                enrollmentId=hhe.getEnrollmentId();
                //System.err.println("Household Id is "+hhe.getHhUniqueId());
                if(enrollmentId !=null && enrollmentId.indexOf("/") !=-1)
                {
                    String[] enrollmentIdArray=enrollmentId.split("/");
                    if(enrollmentIdArray !=null && enrollmentIdArray.length>2)
                    {
                        level2OuCode=enrollmentIdArray[0];
                        level3OuCode=enrollmentIdArray[1];
                        level2Ou=getOrganizationUnitByOuCodeAndOuLevel(level2OuCode, 2);
                        List ouList=getOrganizationUnitsByOuCodeAndOuLevel(level3OuCode, 3);
                        if(ouList !=null && level2Ou !=null)
                        {
                            for(Object ouObj:ouList)
                            {
                                level3Ou=(OrganizationUnit)ouObj;
                                if(proposedName==null || proposedName.trim().length()==0)
                                proposedName=level3Ou.getName()+" unknown community";
                                //chech to be sure the two organization units are in the same hirachy, i.e level2 is the parent of level3
                                if(level3Ou.getParentId().equalsIgnoreCase(level2Ou.getUid()))
                                {
                                    ouLevel=level3Ou.getOulevel()+1;
                                    level4Ou=new OrganizationUnit();
                                    level4Ou.setUid(level4OuId);
                                    level4Ou.setParentId(level3Ou.getUid());
                                    level4Ou.setOuPath(level3Ou.getOuPath()+"/"+level4OuId);
                                    level4Ou.setName(proposedName);
                                    level4Ou=generateOuName(level4Ou);
                                    level4Ou.setOulevel(ouLevel);
                                    ouCode=generateRandomOuCode(ouLevel);//generateOuCode(level3Ou.getUid(), level4Ou.getName(), ouLevel);
                                    level4Ou.setOucode(ouCode);
                                    saveOrganizationUnit(level4Ou); 
                                    created=1;
                                    //System.err.println("level4Ou name is "+level4Ou.getName()+" OuCode is "+level4Ou.getOucode()+" ouLevel is "+level4Ou.getOulevel()+" OuPath is "+level4Ou.getOuPath());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return level4Ou;
}
public OrganizationUnit generateOuName(OrganizationUnit ou) throws Exception
{
    try
    {
        DaoUtility util=new DaoUtility();
        if(ou !=null)
        {
            AppUtility appUtil=new AppUtility();
            OrganizationUnit ouByName=null;
            String randomName=null;
            String ouName=ou.getName();
            String randomCode=null;
            for(int i=0; i<1000; i++)
            {
                if(i==0)
                {
                    ouByName=util.getOrganizationUnitDaoInstance().getOrganizationUnitByName(ouName);
                }
                else
                {
                    randomCode=appUtil.generateUniqueId(3);
                    randomName=ouName+"_"+randomCode+i;
                    ouByName=util.getOrganizationUnitDaoInstance().getOrganizationUnitByName(randomName);
                }
                if(ouByName !=null)
                {
                    continue;
                }
                else
                {
                    if(i==0)
                    ou.setName(ouName);
                    else if(randomName !=null)
                    ou.setName(randomName);
                    else
                    ou.setName(ouName+i);
                    break;
                }
            }
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return ou;
}
    public OrganizationUnit createWard(OrganizationUnit parentOu,String wardName,String ouCode,String legacyId) throws Exception
    {
        OrganizationUnit ward=null;
        if(parentOu !=null && wardName !=null)
        {
            //System.err.println("Parent name is "+parentOu.getName()+" and ward name "+wardName);
                ward=getOrganizationUnitByParentIdAndChildName(parentOu.getUid(),wardName);
                if(ward==null)
                {
                    ward=new OrganizationUnit();
                    ward.setUid(getUniqueRecordId());
                    ward.setDateCreated(DateManager.getCurrentDateInstance());
                    ward.setLastModifiedDate(DateManager.getCurrentDateInstance());
                    ward.setName(wardName);
                    ward.setOuPath(getOuPath(ward)); 
                    ward.setOucode(ouCode);
                    //ward.setOuPath(parentOu.getOuPath()+"\\"+ward.getUid());
                    ward.setOulevel(parentOu.getOulevel()+1);
                    ward.setParentId(parentOu.getUid());
                    ward.setLegacyId(legacyId);
                    saveOrganizationUnit(ward);
                    //System.err.println("ward with name "+ward.getName()+" created");
                }
            
        }
        return ward;
    }
    public String getUniqueRecordId()
    {
        AppUtility appUtil=new AppUtility();
        String uniqueId=appUtil.generateUniqueId(11);
        try
        {
            if(this.getOrganizationUnit(uniqueId) !=null)
            getUniqueRecordId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return uniqueId;
    }
    public String generateRandomOuCode(int ouLevel) throws Exception
    {
        String ouCode=null;
        AppUtility appUtil=new AppUtility();
        if(ouLevel<4)
        {
            ouCode=appUtil.generateUniqueId(3);
            
        }
        else
        {
            ouCode=appUtil.generateUniqueId(5);
        }
        OrganizationUnit ou=this.getOrganizationUnitByOuCodeAndOuLevel(ouCode, ouLevel);
        if(ou !=null)
        generateRandomOuCode(ouLevel);
        if(ouCode !=null)
        ouCode=ouCode.toUpperCase();
        System.err.println("ouCode is "+ouCode);
        return ouCode;
    }
    public String generateOuCode(String parentId,String ouName,int ouLevel) throws Exception
    {
        AppUtility appUtil=new AppUtility();
        String ouCode=null;
        if(ouName !=null)
        {
            //String ouName=ou.getName();
            if(ouName !=null && ouName.length()>3)
            {
                //remove spaces from ouName
                ouName=ouName.replaceAll(" ", "");
                ouCode=ouName.substring(0, 3);
                //System.err.println("ouCode 1 is "+ouCode);
                OrganizationUnit ou2=getOrganizationUnitByOuCodeAndOuLevel(ouCode, ouLevel);
                if(ou2 !=null)
                {
                    //System.err.println("ou2.getName() is "+ou2.getName());
                    OrganizationUnit parentOu=getOrganizationUnit(parentId);
                    String parentName=null;
                    if(parentOu !=null)
                    {
                        parentName=parentOu.getName();
                        //remove spaces from parentName
                        parentName=parentName.replaceAll(" ", "");
                    }
                    int j=0; //j is the offset of the loop below. it is incremented if the ouCode is not available
                    while(ou2 !=null)
                    {
                        for(int i=j; i<ouName.length(); i++)
                        {
                            if(ouName.length()>(i+3))
                            {
                                //take the next three letters as ouCode
                                ouCode=ouName.substring(i, i+3);
                                //System.err.println("ouCode 2 is "+ouCode);
                                ou2=this.getOrganizationUnitByOuCodeAndOuLevel(ouCode, ouLevel);
                                if(ou2==null)
                                break;
                                //if(ou2 !=null)
                                //j++;
                            }
                        }
                        //if all the codes generated in the loop above are still not available, execute second iteration with parentName
                        if(ou2 !=null)
                        {
                            String singleLetterOfParentName=null;
                            j=0;
                            for(int k=0; k<parentName.length(); k++)
                            {
                                //Assign single letter of parent name to code, then proceed to add the next two letters of ouName
                                singleLetterOfParentName=parentName.substring(k, k+1);
                                //System.err.println("singleLetterOfParentName is "+singleLetterOfParentName);
                                for(int i=0; i<ouName.length(); i++)
                                {
                                    if(ouName.length()>(i+3))
                                    {
                                        //take the next two letters of ouName and add to singleLetterOfParentName to create the ouCode
                                        ouCode=singleLetterOfParentName+ouName.substring(i, i+2);
                                        //System.err.println("ouCode 3 is "+ouCode);
                                        ou2=this.getOrganizationUnitByOuCodeAndOuLevel(ouCode, ouLevel);
                                        if(ou2 ==null)
                                        break;
                                        
                                    }
                                }
                                if(ou2 ==null)
                                break;
                                else if(k==parentName.length()-1)
                                {
                                    ouCode=appUtil.generateUniqueId(5);
                                    ou2=null;
                                }
                            }
                         }
                    }
                }
            }
        }
        if(ouCode !=null)
        ouCode=ouCode.toUpperCase();
        System.err.println("ouCode is "+ouCode);
        return ouCode;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
