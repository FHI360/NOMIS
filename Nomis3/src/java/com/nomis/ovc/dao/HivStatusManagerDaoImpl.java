/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;


import com.nomis.ovc.business.AdultHouseholdMember;
import com.nomis.ovc.business.HivStatusManager;
import com.nomis.ovc.business.Ovc;
import com.nomis.ovc.util.AppConstant;
import com.nomis.ovc.util.DateManager;
import com.nomis.reports.utils.ReportParameterTemplate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class HivStatusManagerDaoImpl implements HivStatusManagerDao
{
    Session session;
    Transaction tx;
    SessionFactory sessions;
    String markedForDeleteQuery=" and hsm.markedForDelete=0";
    public void deleteHivStatusRecordsOfChildrenAtRiskOfHivBeforeOctober2018() throws Exception
    {
        String query="delete from APP.HIVSTATUSMANAGER where newhivstatus="+AppConstant.HIV_TEST_REQUIRED_NUM+" and dateofnewstatus <'2018-10-01'";
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        session.createSQLQuery(query).executeUpdate();
        tx.commit();
        closeSession(session);
    }
    public void saveHivStatusManager(HivStatusManager hsm,boolean updateBeneficiaryHivStatus) throws Exception
    {
        try
        {
            if(hsm !=null && this.getHivStatusManager(hsm.getBeneficiaryId())==null)
            {
                hsm=getCleanedHivStatusManager(hsm);
                //System.err.println("hsm.getBeneficiaryId in saveHivStatusManager is "+hsm.getBeneficiaryId());
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.save(hsm);
                tx.commit();
                closeSession(session);
                if(updateBeneficiaryHivStatus)
                updateBeneficiaryCurrentHivStatus(hsm);
                saveHivStatusHistory(hsm);
            }
            else
            {
                updateHivStatusManager(hsm,updateBeneficiaryHivStatus);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void updateHivStatusManager(HivStatusManager hsm,boolean updateBeneficiaryHivStatus) throws Exception
    {
        try
        {
            System.err.println("Inside updateHivStatusManager ");
            if(hsm !=null)
            {
                System.err.println(" ID is "+hsm.getBeneficiaryId()+" "+hsm.getFirstName()+" hsm.getNewHivStatus() "+hsm.getNewHivStatus()+" "+hsm.getDateOfNewStatus());
                HivStatusManager hsm2=this.getHivStatusManager(hsm.getBeneficiaryId());
                if(hsm2 !=null)
                {
                    saveHivStatusHistory(hsm2);
                    System.err.println("hsm.getLastHivStatus() "+hsm.getLastHivStatus()+" hsm.getNewHivStatus() is "+hsm.getNewHivStatus());
                    hsm.setDateCreated(hsm2.getDateCreated());
                    if((hsm.getDateOfNewStatus().equals(hsm2.getDateOfNewStatus())) || hsm.getDateOfNewStatus().after(hsm2.getDateOfNewStatus()))
                    {
                        if(hsm.getNewHivStatus()==AppConstant.HIV_POSITIVE_NUM && hsm.getEnrolledOnTreatment()==0)
                        {
                            hsm.setEnrolledOnTreatment(hsm2.getEnrolledOnTreatment());
                            hsm.setFacilityId(hsm2.getFacilityId());
                        }
                        hsm=getCleanedHivStatusManager(hsm);
                        session = HibernateUtil.getSession();
                        tx = session.beginTransaction();
                        session.update(hsm);
                        tx.commit();
                        closeSession(session);
                        System.err.println(hsm.getNewHivStatus()+" HIV status updated ");
                        if(updateBeneficiaryHivStatus)
                        updateBeneficiaryCurrentHivStatus(hsm);
                        saveHivStatusHistory(hsm);
                    }
                }
                else
                {
                    saveHivStatusManager(hsm,updateBeneficiaryHivStatus);
                }
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void markForDelete(HivStatusManager hsm) throws Exception
    {
        try
        {
            //System.err.println("Inside updateHivStatusManager ");
            if(hsm !=null)
            {
                //System.err.println(" ID is "+hsm.getBeneficiaryId()+" "+hsm.getFirstName()+" hsm.getNewHivStatus() "+hsm.getNewHivStatus()+" "+hsm.getDateOfNewStatus());
                HivStatusManager hsm2=this.getHivStatusManager(hsm.getBeneficiaryId());
                if(hsm2 !=null)
                {
                    hsm2.setMarkedForDelete(AppConstant.MARKEDFORDELETE);
                    session = HibernateUtil.getSession();
                    tx = session.beginTransaction();
                    session.update(hsm2);
                    tx.commit();
                    closeSession(session);
                }
                
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteHivStatusManager(HivStatusManager hsm) throws Exception
    {
        try
        {
            if(hsm !=null && this.getHivStatusManager(hsm.getBeneficiaryId()) !=null)
            {
                session = HibernateUtil.getSession();
                tx = session.beginTransaction();
                session.delete(hsm);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
          closeSession(session);  
          ex.printStackTrace();
        }
    }
    public void deleteHivStatusManager(String beneficiarId) throws Exception
    {
        try
        {
            HivStatusManager hsm=getHivStatusManager(beneficiarId);
            if(hsm !=null)
            {
                deleteHivStatusManager(hsm);
            }
        }
        catch(Exception ex)
        {
          ex.printStackTrace();
        }
    }
    public  HivStatusManager getHivStatusManager(String beneficiaryId) throws Exception
    {
        HivStatusManager hsm=null;
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from HivStatusManager hsm where hsm.beneficiaryId=:id").setString("id", beneficiaryId).list();
        tx.commit();
        closeSession(session);
        if(list !=null && !list.isEmpty())
        {
            hsm=(HivStatusManager)list.get(0);
        }
        return hsm;
    }
    public List getHivRecordsForExport(ReportParameterTemplate rpt) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            mainList.addAll(getOvcHivRecordsForExport(rpt));
            mainList.addAll(getAdultHouseholdMemberHivRecordsForExport(rpt));
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getOvcHivRecordsForExport(ReportParameterTemplate rpt) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheOvcOrganizationUnitHivStatusManagerQuery()+additionalOrgUnitQuery+sqg.getHivStatusManagerLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate());
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[3]);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public List getAdultHouseholdMemberHivRecordsForExport(ReportParameterTemplate rpt) throws Exception
    {
        List mainList=new ArrayList();
        try
        {
            SubQueryGenerator sqg=new SubQueryGenerator();
            String additionalOrgUnitQuery="";
            if(rpt !=null && rpt.getLevel2OuId() !=null && rpt.getLevel2OuId().trim().length()>0 && !rpt.getLevel2OuId().equalsIgnoreCase("select") && !rpt.getLevel2OuId().equalsIgnoreCase("All"))
            {
                additionalOrgUnitQuery=sqg.getOrganizationUnitQuery(rpt);
            }
            String query=SubQueryGenerator.getHheAdultHouseholdMemberOrganizationUnitHivStatusManagerQuery()+additionalOrgUnitQuery+sqg.getHivStatusManagerLastModifiedDateQuery(rpt.getStartDate(),rpt.getEndDate());
            System.err.println(query);
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List list = session.createQuery(query).list();
            tx.commit();
            closeSession(session);
            if(list !=null)
            {
                for(Object obj:list)
                {
                    Object[] objArray=(Object[])obj;
                    mainList.add(objArray[3]);
                }
            }
        }
         catch (Exception ex)
         {
             closeSession(session);
            throw new Exception(ex);
         }
        return mainList;
    }
    public  List getAllHivStatusRecords() throws Exception
    {
        List list=null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from HivStatusManager hsm"+markedForDeleteQuery).list();
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
    private void updateBeneficiaryCurrentHivStatus(HivStatusManager hsm) throws Exception
    {
        if(hsm !=null)
        {
            System.err.println(" ID is "+hsm.getBeneficiaryId()+" "+hsm.getFirstName()+" hsm.getNewHivStatus() "+hsm.getNewHivStatus()+" "+hsm.getDateOfNewStatus());
            //EnrollmentStatusHistoryDao eshdao=new EnrollmentStatusHistoryDaoImpl();
            ChildEnrollmentDao dao=new ChildEnrollmentDaoImpl();
            Ovc ovc=dao.getOvc(hsm.getBeneficiaryId());
            if(ovc !=null)
            {
                System.err.println("Ovc is not null in hsm dao");
                if(hsm.getDateOfNewStatus()==null)
                hsm.setDateOfNewStatus(hsm.getDateOfLastHivStatus());
                if(hsm.getDateOfNewStatus() !=null)
                {
                    //System.err.println(" hsm.getDateOfNewStatus() "+hsm.getDateOfNewStatus()+" is not null");
                    if(ovc.getDateOfCurrentHivStatus() !=null)
                    {
                        //if(DateManager.compareDates(ovc.getDateOfCurrentHivStatus(), hsm.getDateOfNewStatus()))
                        if((ovc.getDateOfCurrentHivStatus().equals(hsm.getDateOfNewStatus())) || (ovc.getDateOfCurrentHivStatus().before(hsm.getDateOfNewStatus())))
                        {
                            ovc.setCurrentHivStatus(hsm.getNewHivStatus());
                            ovc.setDateOfCurrentHivStatus(hsm.getDateOfNewStatus());
                            if(ovc.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                            {
                                 ovc.setEnrolledOnTreatment(hsm.getEnrolledOnTreatment());
                                 ovc.setHivTreatmentFacilityId(hsm.getFacilityId());
                            }
                            //System.err.println("ovc.getDateOfCurrentHivStatus() is "+ovc.getDateOfCurrentHivStatus()+" "+ovc.getCurrentHivStatus());
                        }
                    }
                    else
                    {
                        ovc.setCurrentHivStatus(hsm.getNewHivStatus());
                        ovc.setDateOfCurrentHivStatus(hsm.getDateOfNewStatus());
                        //System.err.println("ovc.getDateOfCurrentHivStatus() 2 is "+ovc.getDateOfCurrentHivStatus()+" ovc.getCurrentHivStatus() is "+ovc.getCurrentHivStatus());
                    }
                    if(ovc.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                    {
                        ovc.setEnrolledOnTreatment(hsm.getEnrolledOnTreatment());
                        ovc.setHivTreatmentFacilityId(hsm.getFacilityId());
                    }
                    if((ovc.getDateOfCurrentHivStatus().equals(hsm.getDateOfNewStatus())))
                    dao.updateOvc(ovc, false, false);
                    //Update the HIV status for the quarter if the beneficiary has enrollment status record for that quarter
                    //eshdao.updateHivStatus(ovc.getOvcId(), ovc.getCurrentHivStatus(), ovc.getDateOfCurrentHivStatus(), ovc.getCurrentAge(),ovc.getCurrentAgeUnit());
                }
                
            }
            else
            {
                System.err.println("About to update ahm in hsm dao");
                AdultHouseholdMemberDao ahmdao=new AdultHouseholdMemberDaoImpl();
                AdultHouseholdMember ahm=ahmdao.getAdultHouseholdMember(hsm.getBeneficiaryId());
                if(ahm !=null)
                {
                    System.err.println("ahm is not null in hsm dao");
                    if(hsm.getDateOfNewStatus()==null)
                    hsm.setDateOfNewStatus(hsm.getDateOfLastHivStatus());
                    if(hsm.getDateOfNewStatus() !=null)
                    {
                        System.err.println("hsm.getDateOfNewStatus() is not null in hsm dao");
                        if(ahm.getDateOfCurrentHivStatus() !=null)
                        {
                            System.err.println("ahm.getDateOfCurrentHivStatus() is not null in hsm dao");
                            //if(DateManager.compareDates(ahm.getDateOfCurrentHivStatus(), hsm.getDateOfNewStatus()))
                            if((ahm.getDateOfCurrentHivStatus().equals(hsm.getDateOfNewStatus())) || (ahm.getDateOfCurrentHivStatus().before(hsm.getDateOfNewStatus())))
                            {
                                {
                                    ahm.setCurrentHivStatus(hsm.getNewHivStatus());
                                    ahm.setDateOfCurrentHivStatus(hsm.getDateOfNewStatus());
                                }
                            }
                        }
                        else
                        {
                            System.err.println("setting ahm.setCurrentHivStatus(hsm.getNewHivStatus()) in hsm dao");
                            ahm.setCurrentHivStatus(hsm.getNewHivStatus());
                            ahm.setDateOfCurrentHivStatus(hsm.getDateOfNewStatus());
                        }
                        if(ahm.getCurrentHivStatus()==AppConstant.HIV_POSITIVE_NUM)
                        {
                            System.err.println("setting ahm.setEnrolledOnTreatment(hsm.getEnrolledOnTreatment()); in hsm dao");
                            ahm.setEnrolledOnTreatment(hsm.getEnrolledOnTreatment());
                            ahm.setHivTreatmentFacilityId(hsm.getFacilityId());
                        }
                        if((ahm.getDateOfCurrentHivStatus().equals(hsm.getDateOfNewStatus())))
                        {
                            ahmdao.updateAdultHouseholdMember(ahm);
                            System.err.println("setting ahmdao.updateAdultHouseholdMember(ahm); in hsm dao");
                        }
                        
                        //Update the HIV status for the quarter if the beneficiary has enrollment status record for that quarter
                        //eshdao.updateHivStatus(ahm.getBeneficiaryId(), ahm.getCurrentHivStatus(), ahm.getDateOfCurrentHivStatus(), ahm.getCurrentAge(),"Y");
                    }
                    
                    //System.err.println("ahm.getEnrolledOnTreatment() is "+ahm.getEnrolledOnTreatment());
                }
            }
        }
    }
    public void changeBeneficiaryIdInHivStatusManager(String oldOvcId, String newOvcId) throws Exception
    {
        HivStatusManager hsm=this.getHivStatusManager(oldOvcId);
        if(hsm !=null)
        {
            /*HivStatusHistoryDao hshdao=new HivStatusHistoryDaoImpl();
            hsm.setBeneficiaryId(newOvcId);
            if(getHivStatusManager(hsm.getBeneficiaryId())==null)
            saveHivStatusManager(hsm, false);
            else
            updateHivStatusManager(hsm, false);
            
            hsm.setBeneficiaryId(oldOvcId);
            this.deleteHivStatusManager(hsm);
            hshdao.changeBeneficiaryIdInHivStatusHistory(oldOvcId, newOvcId);
            System.err.println("OVC Id in HivStatusManager changed from "+oldOvcId+" to "+newOvcId);*/
        }
    }
    public void saveHivStatusHistory(HivStatusManager hsm) throws Exception
    {
        if(hsm !=null)
        {
            /*HivStatusHistory hsh=new HivStatusHistory();
            hsh.setBeneficiaryId(hsm.getBeneficiaryId());
            hsh.setBeneficiaryTypeValue(hsm.getBeneficiaryTypeValue());
            hsh.setDateCreated(hsm.getDateCreated());
            hsh.setDateOfNewStatus(hsm.getDateOfNewStatus());
            hsh.setEnrolledOnTreatment(hsm.getEnrolledOnTreatment());
            hsh.setFacilityId(hsm.getFacilityId());
            hsh.setLastModifiedDate(hsm.getLastModifiedDate());
            hsh.setNewHivStatus(hsm.getNewHivStatus());
            hsh.setPointOfUpdateValue(hsm.getPointOfUpdateValue());
            hsh.setRecordedBy(hsm.getRecordedBy());
            HivStatusHistoryDao hshdao=new HivStatusHistoryDaoImpl();
            hshdao.saveHivStatusHistory(hsh);*/
        }
    }
    private HivStatusManager getCleanedHivStatusManager(HivStatusManager hsm)
    {
        if(hsm !=null)
        {
            if(hsm.getDateCreated()==null && hsm.getLastModifiedDate() !=null)
            hsm.setDateCreated(hsm.getLastModifiedDate());
            else if(hsm.getLastModifiedDate()==null && hsm.getDateCreated() !=null)
            hsm.setLastModifiedDate(hsm.getDateCreated());
            else if(hsm.getLastModifiedDate()==null && hsm.getDateCreated()==null)
            {
                hsm.setDateCreated(DateManager.getCurrentDateInstance());
                hsm.setLastModifiedDate(DateManager.getCurrentDateInstance());
            }
        }
        return hsm;
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
