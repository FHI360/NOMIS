/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import com.nomis.ovc.business.ChildService;
import com.nomis.ovc.dao.ChildServiceDao;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.SubQueryGenerator;
import com.nomis.ovc.legacydatamanagement.utils.NomisConstant;
import com.nomis.ovc.util.AppConstant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class MonthlySummaryFormReport 
{
    private final String maleGender="M";
    private final String femaleGender="F";
    public final String FINER_AGE_DISAGGREGATION="finerAgeDisaggregation";
    String hviMaleGender="Male";
    String hviFemaleGender="Female";
    String hhQueryPart=null;
    String hhOvcAndServiceQueryPart=null;
    String hhOvcAndServiceByOvcIdQueryPart=null;
    String countQueryPart=null;
    String serviceCountQueryPart=null;
    String serviceCountHivQueryPart=null;
    String hheCaregiverHhsQuery=null;
    String hheServiceCountQuery=null;
    String ageQuery;
    private int[] ageSegregation=null;
    int[] householdAgeSegregation={0,17,18,59,60,200};
    
    DaoUtility util=new DaoUtility();
    ReportUtility rutil=new ReportUtility();
    ChildServiceDao serviceDao=util.getChildServiceDaoInstance();
    List maleGreaterThanThreeList=new ArrayList();
    List maleLessThanThreeList=new ArrayList();
    List femaleGreaterThanThreeList=new ArrayList();
    List femaleLessThanThreeList=new ArrayList();
    //OvcWithdrawalDao withdrawalDao=new OvcWithdrawalDaoImpl();
    SubQueryGenerator sqg=new SubQueryGenerator();
    public MonthlySummaryFormReport()
    {
        hhQueryPart=rutil.getHhOvcQueryPart();//.getHouseholdQueryPart()+"Ovc ovc where hhe.hhUniqueId=ovc.hhUniqueId and ";
        countQueryPart=rutil.getOvcCountQueryPart();//"select count(distinct ovc.ovcId)"+util.getHouseholdQueryPart()+"Ovc ovc where hhe.hhUniqueId=ovc.hhUniqueId and ";
        serviceCountQueryPart=rutil.getServiceCountQueryPart();//"select count(distinct ovc.ovcId)"+util.getHouseholdQueryPart()+"Ovc ovc, ChildService service where hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId and " ;
        hhOvcAndServiceQueryPart=rutil.getHhOvcAndServiceQueryPart();//util.getHouseholdQueryPart()+"Ovc ovc,ChildService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId) ";
        serviceCountHivQueryPart=rutil.getHouseholdHIVOvcServiceReportQueryPart("ChildService");
        hhOvcAndServiceByOvcIdQueryPart=rutil.getHhOvcAndServiceByOvcIdQueryPart();//"select distinct service.ovcId "+util.getHouseholdQueryPart()+"Ovc ovc,ChildService service where (hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=service.ovcId) ";
        hheCaregiverHhsQuery=rutil.getHheCaregiverHhsQuery();//"select count(distinct hhs.caregiverId) from HouseholdService hhs, HouseholdEnrollment hhe,Caregiver cgiver where (hhe.hhUniqueId=cgiver.hhUniqueId and cgiver.hhUniqueId=hhs.hhUniqueId)";
        hheServiceCountQuery=rutil.getHheServiceCountQuery();//"select count(distinct hhs.hhUniqueId) from HouseholdService hhs, HouseholdEnrollment hhe where (hhe.hhUniqueId=hhs.hhUniqueId)";
    }
    public List execReportQuery(String sql)
    {
        List list=new ArrayList();
        list=util.execReportQuery(sql);
        return list;
    }
    public int[] getNewAgeSegregation()
    {
        return rutil.getNewAgeSegregation();
    }
    public int[] getNewFinerAgeSegregation()
    {
        return rutil.getNewFinerAgeSegregation();
        /*int[] ageSegregation={0,0,1,4,5,9,10,14,15,17,18,24,25,100};
        return ageSegregation;*/
    }
    public int[] getAgeSegregation()
    {
        return rutil.getAgeSegregation();
        /*int[] ageSegregation={0,5,6,12,13,17};
        return ageSegregation;*/
    }
    public int[] getHouseholdAgeSegregation()
    {
        return householdAgeSegregation;
    }
    public int[] setHouseholdAgeSegregation(int[] ageSegregation)
    {
        householdAgeSegregation=ageSegregation;
        return householdAgeSegregation;
    }
    public int[] getSecondHouseholdAgeSegregation()
    {
        return rutil.getSecondHouseholdAgeSegregation();
        /*int[] ageSegregation={0,17,18,24,25,200};
        return ageSegregation;*/
    }
    public void setAgeSegregation(int[] ageSegregation)
    {
        this.ageSegregation=ageSegregation;
    }
    
    public List getNoOfCSOReportingChildServicesForTheMth(ReportParameterTemplate rpt) throws Exception
    {
        List reportList=new ArrayList();
        int noOfCBO=util.getHouseholdEnrollmentDaoInstance().getDistinctCommunityBasedOrganizationIdsWithServedBeneficiaries(rpt);
        reportList.add(noOfCBO);
        /*String additionalQueryCriteria=sqg.getAdditionalOrgUnitQuery(rpt);//=util.getOrgQueryCriteria(params);
         String malesql="select count(distinct hhe.cboId) "+SubQueryGenerator.getHheOvcOrganizationUnitServiceQuery()+" and ovc.ovcId is not null "+additionalQueryCriteria+" and service.serviceDate between '"+rpt.getStartDate()+"' and '"+rpt.getEndDate()+"'";
        maleList.add("No of <b>CSO</b> reporting VC services in the reporting period");
        List resultList=new ArrayList();
        resultList=execReportQuery(malesql);
        maleList.add(resultList.get(0));*/
        
        //System.err.println("No of CBO reporting is "+resultList.get(0));
        return reportList;
    }
    public List getNoOfLGAReportingChildServicesForTheMth(ReportParameterTemplate rpt) throws Exception
    {
        List reportList=new ArrayList();
        int noOfLevel3Ou=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel3OuIdsWithServedBeneficiaries(rpt);
        reportList.add(noOfLevel3Ou);
        return reportList;
        /*List maleList=new ArrayList();
        String additionalQueryCriteria=sqg.getAdditionalOrgUnitQuery(rpt);
        
        String malesql="select count(distinct hhe.lgaCode) "+hhOvcAndServiceQueryPart+" and ovc.ovcId is not null "+additionalQueryCriteria+" and service.serviceDate between '"+rpt.getStartDate()+"' and '"+rpt.getEndDate()+"'";

        maleList.add("No of <b>LGAs</b> reporting VC services in the reporting period");
        List resultList=new ArrayList();
        resultList=execReportQuery(malesql);
        maleList.add(resultList.get(0));

        return maleList;*/
    }
    public List getNoOfStateReportingChildServicesForTheMth(ReportParameterTemplate rpt) throws Exception
    {
        List reportList=new ArrayList();
        int noOfLevel2Ou=util.getHouseholdEnrollmentDaoInstance().getDistinctLevel3OuIdsWithServedBeneficiaries(rpt);
        reportList.add(noOfLevel2Ou);
        return reportList;
        /*List maleList=new ArrayList();
        String additionalQueryCriteria=sqg.getAdditionalOrgUnitQuery(rpt);
        
        String malesql="select count(distinct hhe.stateCode)"+hhOvcAndServiceQueryPart+" and ovc.ovcId is not null "+additionalQueryCriteria+" and   service.serviceDate between '"+rpt.getStartDate()+"' and '"+rpt.getEndDate()+"'";// or ovc.ovcId in (select ovc.ovcId from Ovc ovc where  ovc.dateEnrollment between '"+startDate+"' and '"+endDate+"' )";
        //System.err.println("State count query is "+malesql);
        maleList.add("No of <b>States</b> reporting VC services in the reporting period");
        List resultList=new ArrayList();
        resultList=execReportQuery(malesql);
        maleList.add(resultList.get(0));
        //System.err.println("No of State reporting is "+resultList.get(0));
        return maleList;*/
    }
    private String getAdditionalEnrollmentQuery(String additionalEnrollmentQuery)
    {
        return rutil.getAdditionalEnrollmentQuery(additionalEnrollmentQuery);
    }
    private String getAdditionalServiceQuery(String additionalServiceQuery)
    {
        return rutil.getAdditionalServiceQuery(additionalServiceQuery);
    }
    public List getOvcMthlySummaryData(ReportParameterTemplate rpt,String ageSegType) throws Exception
    {
        List mainList=new ArrayList();
        List list=new ArrayList();
        List list2=new ArrayList();
        List list3=new ArrayList();
        String additionalQueryCriteria=sqg.getAdditionalOrgUnitQuery(rpt);
        String startDate=rpt.getStartDate();
        String endDate=rpt.getEndDate();
        String enrollmentEndDateQuery=sqg.getEnrollmentEndDateQuery(endDate);
        if(ageSegType.equalsIgnoreCase("new"))
        ageSegregation=getNewFinerAgeSegregation();//getNewAgeSegregation();
        else
        ageSegregation=getAgeSegregation();
        list.add(getNoOfOvcNewlyEnrolled("No of <b>new VC</b> enrolled",additionalQueryCriteria,startDate,endDate));
        list.add(getNoOfOvcByEnrollmentStatus("No of <b>VC currently</b> enrolled",additionalQueryCriteria,startDate,endDate,AppConstant.ACTIVE_NUM));
        //list.add(getNoOfOvcByEnrollmentStatus("No of <b>VC ever</b> enrolled",additionalQueryCriteria,startDate,endDate,AppConstant.EVER_ENROLLED_NUM));
        list.add(getNoOfOvcServedForMSF("Number of OVC provided with atleast one service",additionalQueryCriteria,startDate,endDate,ageSegType));
        list.add(getNoOfOvcWithdrawn("Number of <b>VC Withdrawn</b> from the program",additionalQueryCriteria,startDate,endDate,AppConstant.EXITEDFROMPROGRAM_NUM));
        list.add(getNoOfOvcWithdrawn("<b>No of <b>VC known to have died",additionalQueryCriteria,startDate,endDate,AppConstant.DIED_NUM));
        
        list.add(getNoOfOvcWithdrawn("<b>No of <b>VC who have migrated</b>",additionalQueryCriteria,startDate,endDate,AppConstant.MIGRATED_NUM));
        list.add(getNoOfOvcWithdrawn("<b>No of <b>VC lost to followup</b>",additionalQueryCriteria,startDate,endDate,AppConstant.LOST_TO_FOLLOWUP_NUM));
        list.add(getNoOfOvcWithdrawn("Number of <b>VC graduated</b> from the program (18 and above)",additionalQueryCriteria,startDate,endDate,AppConstant.GRADUATED_NUM));
        
        list.add(getNoOfOvcServedByDomain("Number of <b>VC</b> accessing <b>Health care</b> services",additionalQueryCriteria,startDate,endDate,AppConstant.EVER_ENROLLED_NUM,AppConstant.SUBDOMAIN_HEALTH_NUM));
        list.add(getNoOfOvcServedByDomain("Number of <b>VC</b> accessing <b>Nutritional</b> services",additionalQueryCriteria,startDate,endDate,AppConstant.EVER_ENROLLED_NUM,AppConstant.SUBDOMAIN_HEALTH_NUM));
        list.add(getNoOfOvcServedByDomain("Number of <b>VC</b> accessing <b>Shelter</b> services",additionalQueryCriteria,startDate,endDate,AppConstant.EVER_ENROLLED_NUM,AppConstant.SUBDOMAIN_SHELTER_NUM));
        list.add(getNoOfOvcServedByDomain("Number of <b>VC</b> accessing <b>Education</b> services",additionalQueryCriteria,startDate,endDate,AppConstant.EVER_ENROLLED_NUM,AppConstant.SUBDOMAIN_EDUCATION_NUM));
        list.add(getNoOfOvcServedByDomain("Number of <b>VC</b> accessing <b>Psychosocial support</b> services",additionalQueryCriteria,startDate,endDate,AppConstant.EVER_ENROLLED_NUM,AppConstant.SUBDOMAIN_PSYCHOSOCIAL_NUM));
        list.add(getNoOfOvcServedByDomain("Number of <b>VC</b> accessing <b>Protection</b> services",additionalQueryCriteria,startDate,endDate,AppConstant.EVER_ENROLLED_NUM,AppConstant.SUBDOMAIN_PROTECTION_NUM));
        /*
        list.add(getNoOfOvcSupportedOnHIVAIDSServicesForMSF("Number of active VC beneficiaries receiving support to access HIV services",additionalQueryCriteria,startDate,endDate));
        //getNoOfOvcWithdrawn(String indicator,String additionalQueryCriteria,String startDate,String endDate)
        list.add(getNoOfOvcWithdrawn("Number of <b>VC Withdrawn</b> from the program",additionalQueryCriteria,startDate,endDate));
        list.add(getNoOfOvcOutOfProgram("<b>No of <b>VC known to have died",additionalQueryCriteria," ", "Known death"));
        list.add(getNoOfOvcOutOfProgram("<b>No of <b>VC who have migrated</b>",additionalQueryCriteria," ", "Migrated"));
        list.add(getNoOfOvcOutOfProgram("<b>No of <b>VC lost to followup</b>",additionalQueryCriteria," ", "Loss to follow-up"));
        list2.add(getNoOfOvcAbove18YearsWhoGraduated("Number of <b>VC graduated</b> from the program (18 and above)","All",additionalQueryCriteria," "," ",null));
        list.addAll(getOvcServedByNoOfServices("No of VC that received three or more services","No of VC that received less than three services",additionalQueryCriteria,startDate, endDate));

        list3.add(getNoOfOvcAccessingHealthCare("Number of <b>VC</b> accessing <b>Health care</b> services",additionalQueryCriteria,startDate,endDate));
        list3.add(getNoOfOvcAccessingNutritional("Number of <b>VC</b> accessing <b>Nutritional</b> services",additionalQueryCriteria,startDate,endDate));
        list3.add(getNoOfOvcAccessingShelter("Number of <b>VC</b> accessing <b>Shelter</b> services",additionalQueryCriteria,startDate,endDate));
        list3.add(getNoOfOvcAccessingEducational("Number of <b>VC</b> accessing <b>Education</b> services",additionalQueryCriteria,startDate,endDate));
        list3.add(getNoOfOvcAccessingPsychoSupport("Number of <b>VC</b> accessing <b>Psychosocial support</b> services",additionalQueryCriteria,startDate,endDate));
        list3.add(getNoOfOvcAccessingProtection("Number of <b>VC</b> accessing <b>Protection</b> services",additionalQueryCriteria,startDate,endDate));*/

        /*mainList.add(list);
        mainList.add(list2);
        mainList.add(list3);*/
        return list;
    }
    public List getOvcMthlyEconStrengtheningData(ReportParameterTemplate rpt, String ageSegType) throws Exception
    {
        List list=new ArrayList();
        List labelList=new ArrayList();
        List mainList=new ArrayList();
        String additionalQueryCriteria=sqg.getAdditionalOrgUnitQuery(rpt);
        String startDate=rpt.getStartDate();
        String endDate=rpt.getEndDate();
        String[] hhAgeDisaggregationLabel={" ","0-17","18-59",">60","Total(M)","0-17","18-59",">60","Total(F)","Grand Total"};
        for(int i=0; i<hhAgeDisaggregationLabel.length; i++)
        {
            labelList.add("<b>"+hhAgeDisaggregationLabel[i]+"</b>");
        }
        list.addAll(getNoOfOvcAccessingEconStrengthening("Number of <b>VC</b> accessing <b>Economic Strengthening</b> services",additionalQueryCriteria,startDate,endDate,ageSegType));
        //additionalQueryCriteria=util.getHVIOrgUnitQuery(hviParams);
        mainList.add(list);

        if(!ageSegType.equalsIgnoreCase("new"))
        {
            mainList.add(labelList);
            mainList.add(getNoOfCaregiversAndHouseholdHeadsProvidedWithAtleastOneService("Number of <b>caregivers/household heads</b> provided with at least one service",additionalQueryCriteria,startDate,endDate));
            mainList.add(getNoOfHouseholdsSupportedToAccessHIVCare("Number of caregivers/household heads supported to access HIV services",additionalQueryCriteria,startDate,endDate));
            mainList.add(getNoOfHouseholdHeadsReceivingEconomicStrenghtening("Number of caregivers/Household heads receiving Economic Strengthening",additionalQueryCriteria,startDate, endDate));
        }
        //if(ageSegType.equalsIgnoreCase("new"))
        mainList.add(getNoOfHouseholdsReceivingEconomicStrenghtening("Number of Households provided Economic Strengthening",additionalQueryCriteria,startDate, endDate,ageSegType));
        return mainList;
    }
    public String[] getHVIParams(String[] params)
    {
        String[] hviParams={params[0],params[1],params[2],null,null,null,params[7], params[8], params[9],params[10],params[18],params[19]};
        return hviParams;
    }
    public void setNoOfServices(String additionalQueryCriteria,String additionalServiceQuery,String ageQuery) throws Exception
    {
        maleLessThanThreeList.clear();
        maleGreaterThanThreeList.clear();
        femaleLessThanThreeList.clear();
        femaleGreaterThanThreeList.clear();
        System.err.println("setting number of services in Monthly summary form");
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        List uniqueOvcIdList=new ArrayList();
        int numberOfServices=0;
        if(!additionalServiceQuery.equals(" "))
            additionalServiceQuery=" and "+additionalServiceQuery;
        String maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);;
        String femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);;

        String malesql=hhOvcAndServiceByOvcIdQueryPart+maleGenderQuery+additionalQueryCriteria+additionalServiceQuery+ageQuery;
        String femalesql=hhOvcAndServiceByOvcIdQueryPart+femaleGenderQuery+additionalQueryCriteria+additionalServiceQuery+ageQuery;
        System.err.println("malesql in setNoOfServices is "+malesql);
        uniqueOvcIdList=execReportQuery(malesql);
        if(uniqueOvcIdList !=null && !uniqueOvcIdList.isEmpty())
        {
            String ovcId=null;
            ChildService service=null;

            for(Object s: uniqueOvcIdList)
            {
                ovcId=(String)s;
                maleList=serviceDao.getServicesByOvcIdAndAdditionalServiceQueryByNumberOfServices(ovcId, additionalServiceQuery);
                if(maleList !=null && !maleList.isEmpty())
                {
                    service=(ChildService)maleList.get(0);
                    numberOfServices=serviceDao.getNumberOfServicesPerServiceRecord(service);
                    if(numberOfServices >2)
                    maleGreaterThanThreeList.add(service.getOvcId());
                    else if(numberOfServices >0 && numberOfServices <3)
                    maleLessThanThreeList.add(service.getOvcId());
                }
              }
        }
        uniqueOvcIdList=execReportQuery(femalesql);
        if(uniqueOvcIdList !=null && !uniqueOvcIdList.isEmpty())
        {
            String ovcId=null;
            ChildService service=null;
            for(Object s: uniqueOvcIdList)
            {
                ovcId=(String)s;
                femaleList=serviceDao.getServicesByOvcIdAndAdditionalServiceQueryByNumberOfServices(ovcId, additionalServiceQuery);
                service=(ChildService)femaleList.get(0);
                numberOfServices=serviceDao.getNumberOfServicesPerServiceRecord(service);
                if(numberOfServices >2)
                femaleGreaterThanThreeList.add(service.getOvcId());
                else if(numberOfServices >0 && numberOfServices <3)
                femaleLessThanThreeList.add(service.getOvcId());
            }
        }
        System.err.println("Number of OVC Services generated");
    }
    public List getOvcServedByNoOfServices(String greaterThanThreeIndicator,String lessThanThreeIndicator,String additionalQueryCriteria,String startDate, String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List greaterThanThreeList=new ArrayList();
        List lessThanThreeList=new ArrayList();
        List maleMoreThanThreeServicesList=new ArrayList();
        List femaleMoreThanThreeServicesList=new ArrayList();
        List maleLessThanThreeServicesList=new ArrayList();
        List femaleLessThanThreeServicesList=new ArrayList();
        int maleLessThanThreeTotal=0;
        int femaleLessThanThreeTotal=0;
        int maleGreaterThanThreeTotal=0;
        int femaleGreaterThanThreeTotal=0;
        int count=0;
        String ageQuery=" ";
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            String additionalServiceQuery= sqg.getOvcServiceDateQuery(startDate, endDate);
            setNoOfServices(additionalQueryCriteria,additionalServiceQuery,ageQuery);

            maleGreaterThanThreeTotal+=maleGreaterThanThreeList.size();
            femaleGreaterThanThreeTotal+=femaleGreaterThanThreeList.size();
            maleLessThanThreeTotal+=maleLessThanThreeList.size();
            femaleLessThanThreeTotal+=femaleLessThanThreeList.size();

            maleMoreThanThreeServicesList.add(maleGreaterThanThreeList.size());
            femaleMoreThanThreeServicesList.add(femaleGreaterThanThreeList.size());
            maleLessThanThreeServicesList.add(maleLessThanThreeList.size());
            femaleLessThanThreeServicesList.add(femaleLessThanThreeList.size());
        }
        maleMoreThanThreeServicesList.add(maleGreaterThanThreeTotal);
        femaleMoreThanThreeServicesList.add(femaleGreaterThanThreeTotal);
        maleLessThanThreeServicesList.add(maleLessThanThreeTotal);
        femaleLessThanThreeServicesList.add(femaleLessThanThreeTotal);
        greaterThanThreeList.add(greaterThanThreeIndicator);
        //greaterThanThreeList.add("No of VC that received three or more services");
        greaterThanThreeList.addAll(maleMoreThanThreeServicesList);
        greaterThanThreeList.addAll(femaleMoreThanThreeServicesList);
        greaterThanThreeList.add((maleGreaterThanThreeTotal+femaleGreaterThanThreeTotal));

        lessThanThreeList.add(lessThanThreeIndicator);
        lessThanThreeList.addAll(maleLessThanThreeServicesList);
        lessThanThreeList.addAll(femaleLessThanThreeServicesList);
        lessThanThreeList.add(maleLessThanThreeTotal+femaleLessThanThreeTotal);
        mainlist.add(greaterThanThreeList);
        mainlist.add(lessThanThreeList);
        return mainlist;
    }
    public ReportTemplate getNoOfOvcNewlyEnrolled(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        //List mainlist=new ArrayList();
        //List maleList=new ArrayList();
        //List femaleList=new ArrayList();
        String maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
        String femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
        int maleTotal=0;
        int femaleTotal=0;
        int maleCount=0;
        int femaleCount=0;
        int startAge=0;
        int endAge=0;
        String ageQuery=" ";
        ReportTemplate rt=new ReportTemplate();
        rt.setIndicatorName(indicator);
        
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            startAge=ageSegregation[i];
            endAge=ageSegregation[i+1];
            ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String queryPart= additionalQueryCriteria+ageQuery+sqg.getOvcEnrollmentDateQuery(startDate, endDate);//" and ovc.dateOfEnrollment between '"+startDate+"' and '"+endDate+"' ";
            String malesql=maleGenderQuery+" "+queryPart;
            String femalesql=femaleGenderQuery+" "+queryPart;
            maleCount=util.getChildEnrollmentDaoInstance().getNoOfOvcByEnrollmentStatus(malesql, AppConstant.EVER_ENROLLED_NUM);
            femaleCount=util.getChildEnrollmentDaoInstance().getNoOfOvcByEnrollmentStatus(femalesql, AppConstant.EVER_ENROLLED_NUM);
            if(startAge==0 && endAge==5)
            {
                rt.setMale0to5(maleCount);
                rt.setFemale0to5(femaleCount);
            }
            else if(startAge==6 && endAge==12)
            {
                rt.setMale6to12(maleCount);
                rt.setFemale6to12(femaleCount);
            }
            else if(startAge==13 && endAge==17)
            {
                rt.setMale13to17(maleCount);
                rt.setFemale13to17(femaleCount);
            }
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
            
        }
        rt.setMaleTotal(maleTotal);
        rt.setFemaleTotal(femaleTotal);
        rt.setGrandTotal(maleTotal+femaleTotal);
        return rt;
    }
    public ReportTemplate getNoOfOvcByEnrollmentStatus(String indicator,String additionalQueryCriteria,String startDate,String endDate,int enrollmentStatus) throws Exception
    {
        String maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
        String femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
        int maleTotal=0;
        int femaleTotal=0;
        int maleCount=0;
        int femaleCount=0;
        int startAge=0;
        int endAge=0;
        String ageQuery=" ";
        ReportTemplate rt=new ReportTemplate();
        rt.setIndicatorName(indicator);
        
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            startAge=ageSegregation[i];
            endAge=ageSegregation[i+1];
            ageQuery=sqg.getOvcCurrentAgeQuery(startAge, endAge);
            String queryPart= additionalQueryCriteria+ageQuery+sqg.getOvcEnrollmentDateQuery(startDate, endDate);//" and ovc.dateOfEnrollment between '"+startDate+"' and '"+endDate+"' ";
            String malesql=maleGenderQuery+" "+queryPart;
            String femalesql=femaleGenderQuery+" "+queryPart;
            maleCount=util.getChildEnrollmentDaoInstance().getNoOfOvcByEnrollmentStatus(malesql, enrollmentStatus);
            femaleCount=util.getChildEnrollmentDaoInstance().getNoOfOvcByEnrollmentStatus(femalesql, enrollmentStatus);
            if(startAge==0 && endAge==5)
            {
                rt.setMale0to5(maleCount);
                rt.setFemale0to5(femaleCount);
            }
            else if(startAge==6 && endAge==12)
            {
                rt.setMale6to12(maleCount);
                rt.setFemale6to12(femaleCount);
            }
            else if(startAge==13 && endAge==17)
            {
                rt.setMale13to17(maleCount);
                rt.setFemale13to17(femaleCount);
            }
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
            
        }
        rt.setMaleTotal(maleTotal);
        rt.setFemaleTotal(femaleTotal);
        rt.setGrandTotal(maleTotal+femaleTotal);
        //mainlist.add(rt);
        
        return rt;
    }

    public ReportTemplate getNoOfOvcServedForMSF(String indicator,String additionalQueryCriteria,String startDate,String endDate,String ageSegType) throws Exception
    {
        ReportTemplate rt=new ReportTemplate();
        rt.setIndicatorName(indicator);        
        int startAge=0;
        int endAge=0;
        int maleCount=0;
        int femaleCount=0;
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            startAge=ageSegregation[i];
            endAge=ageSegregation[i+1];
            maleCount=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatus(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, startAge,endAge,maleGender);
            femaleCount=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatus(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, startAge,endAge,femaleGender);
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
            
            if(startAge==0 && endAge==5)
            {
                rt.setMale0to5(maleCount);
                rt.setFemale0to5(femaleCount);
            }
            else if(startAge==6 && endAge==12)
            {
                rt.setMale6to12(maleCount);
                rt.setFemale6to12(femaleCount);
            }
            else if(startAge==13 && endAge==17)
            {
                rt.setMale13to17(maleCount);
                rt.setFemale13to17(femaleCount);
            }
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
            
        }
        rt.setMaleTotal(maleTotal);
        rt.setFemaleTotal(femaleTotal);
        rt.setGrandTotal(maleTotal+femaleTotal);
        return rt;
    }
    public List getOvc_ServActive(String indicator,String additionalQueryCriteria,String startDate,String endDate,String ageSegType) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        int[] newAgeSegregation=ageSegregation;
        if(ageSegType !=null && ageSegType.equalsIgnoreCase(FINER_AGE_DISAGGREGATION))
        newAgeSegregation=getNewFinerAgeSegregation();
        String serviceCriteria=" ";
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        String maleGenderQuery="";
        String femaleGenderQuery="";
        String currentlyQuery=SubQueryGenerator.getOvcCurrentEnrollmentStatusQuery(AppConstant.CURRENTLY_ENROLLED_NUM);
        additionalQueryCriteria=additionalQueryCriteria+currentlyQuery;
        
        for(int i=0; i<newAgeSegregation.length; i+=2)
        {
            maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
            femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            serviceCriteria=" and service.serviceDate between '"+startDate+"' and '"+endDate+"' )";
            String malesql=serviceCountQueryPart+maleGenderQuery+ageQuery+" "+additionalQueryCriteria+serviceCriteria;
            String femalesql=serviceCountQueryPart+femaleGenderQuery+ageQuery+" "+additionalQueryCriteria+serviceCriteria;
            count=(Integer.parseInt(execReportQuery(malesql).get(0).toString()));
            maleList.add(count);
            maleTotal+=count;
            count=(Integer.parseInt(execReportQuery(femalesql).get(0).toString()));
            femaleList.add(count);
            femaleTotal+=count;
            System.err.println("malesql in getOvc_ServActive is "+malesql);
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        mainlist.add(maleList);
        mainlist.add(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfOvcServed(String indicator,String additionalQueryCriteria,String startDate,String endDate,String ageSegType) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        int[] newAgeSegregation=ageSegregation;
        if(ageSegType !=null && ageSegType.equalsIgnoreCase(FINER_AGE_DISAGGREGATION))
        newAgeSegregation=getNewFinerAgeSegregation();
        String serviceCriteria=" ";
        String maleGenderQuery="";
        String femaleGenderQuery="";
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        for(int i=0; i<newAgeSegregation.length; i+=2)
        {
            maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
            femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            serviceCriteria=" and service.serviceDate between '"+startDate+"' and '"+endDate+"' )";
            String malesql=serviceCountQueryPart+maleGenderQuery+ageQuery+" "+additionalQueryCriteria+serviceCriteria;
            String femalesql=serviceCountQueryPart+femaleGenderQuery+ageQuery+" "+additionalQueryCriteria+serviceCriteria;
            count=(Integer.parseInt(execReportQuery(malesql).get(0).toString()));
            maleList.add(count);
            maleTotal+=count;
            count=(Integer.parseInt(execReportQuery(femalesql).get(0).toString()));
            femaleList.add(count);
            femaleTotal+=count;
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        mainlist.add(maleList);
        mainlist.add(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfActiveBeneficiariesServed(String indicator,List ovcServedList,List caregiverServedList) throws Exception
    {
        List mainlist=new ArrayList();
        int maleTotal=0;
        int femaleTotal=0;
        int grandTotal=0;
        int ovcServed=0;
        int caregiversServed=0;
        //int totalServed=0;
        mainlist.add(indicator);
        for(int i=1; i<8; i++)
        {
            if(i==5)
            {
               ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
               caregiversServed=Integer.parseInt(caregiverServedList.get(1).toString());
               mainlist.add(ovcServed);
               maleTotal+=ovcServed;
               //maleTotal+=caregiversServed;
               continue;
            }
            else if(i==6)
            {
               ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
               caregiversServed=Integer.parseInt(caregiverServedList.get(2).toString());
               mainlist.add(ovcServed+caregiversServed);
               maleTotal+=ovcServed;
               maleTotal+=caregiversServed;
               continue;
            }
            else if(i==7)
            {
              caregiversServed=Integer.parseInt(caregiverServedList.get(3).toString());
              mainlist.add(caregiversServed);
              maleTotal+=caregiversServed;
              //mainlist.add(maleTotal);
              continue;
            }
            ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
            mainlist.add(ovcServed);
            maleTotal+=ovcServed;

        }
        for(int i=8; i<ovcServedList.size(); i++)
        {
            if(i==12)
            {
               ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
               caregiversServed=Integer.parseInt(caregiverServedList.get(5).toString());
               mainlist.add(ovcServed);
               femaleTotal+=ovcServed;
               //femaleTotal+=caregiversServed;
               continue;
            }
            else if(i==13)
            {
               ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
               caregiversServed=Integer.parseInt(caregiverServedList.get(6).toString());
               mainlist.add(ovcServed+caregiversServed);
               femaleTotal+=ovcServed;
               femaleTotal+=caregiversServed;
               continue;
            }
            else if(i==14)
            {
              caregiversServed=Integer.parseInt(caregiverServedList.get(7).toString());
              mainlist.add(caregiversServed);
              femaleTotal+=caregiversServed;
              //mainlist.add(femaleTotal);
              continue;
            }
            ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
            mainlist.add(ovcServed);
            femaleTotal+=ovcServed;
        }
        grandTotal=maleTotal+femaleTotal;
        //mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfActiveBeneficiariesServedForDatim2017(String indicator,List ovcServedList,List caregiverServedList) throws Exception
    {
        List mainlist=new ArrayList();
        int maleTotal=0;
        int femaleTotal=0;
        int grandTotal=0;
        int ovcServed=0;
        int caregiversServed=0;
        //int totalServed=0;
        mainlist.add(indicator);
        for(int i=1; i<8; i++)
        {
            if(i==5)
            {
               ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
               //caregiversServed=Integer.parseInt(caregiverServedList.get(1).toString());
               mainlist.add(ovcServed);
               maleTotal+=ovcServed;
               //maleTotal+=caregiversServed;
               continue;
            }
            else if(i==6)
            {
               ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
               //caregiversServed=Integer.parseInt(caregiverServedList.get(2).toString());
               mainlist.add(ovcServed+caregiversServed);
               maleTotal+=ovcServed;
               maleTotal+=caregiversServed;
               continue;
            }
            else if(i==7)
            {
              //caregiversServed=Integer.parseInt(caregiverServedList.get(3).toString());
              mainlist.add(caregiversServed);
              maleTotal+=caregiversServed;
              //mainlist.add(maleTotal);
              continue;
            }
            ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
            mainlist.add(ovcServed);
            maleTotal+=ovcServed;

        }
        for(int i=8; i<ovcServedList.size(); i++)
        {
            if(i==12)
            {
               ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
               //caregiversServed=Integer.parseInt(caregiverServedList.get(5).toString());
               mainlist.add(ovcServed);
               femaleTotal+=ovcServed;
               //femaleTotal+=caregiversServed;
               continue;
            }
            else if(i==13)
            {
               ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
               //caregiversServed=Integer.parseInt(caregiverServedList.get(6).toString());
               mainlist.add(ovcServed+caregiversServed);
               femaleTotal+=ovcServed;
               femaleTotal+=caregiversServed;
               continue;
            }
            else if(i==14)
            {
              //caregiversServed=Integer.parseInt(caregiverServedList.get(7).toString());
              mainlist.add(caregiversServed);
              femaleTotal+=caregiversServed;
              //mainlist.add(femaleTotal);
              continue;
            }
            ovcServed=Integer.parseInt(ovcServedList.get(i).toString());
            mainlist.add(ovcServed);
            femaleTotal+=ovcServed;
        }
        grandTotal=maleTotal+femaleTotal;
        //mainlist.add(grandTotal);
        return mainlist;
    }
        
    public List getNoOfActiveBeneficiariesSupportedToAccessHIVCare(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List ovcList=getNoOfOvcSupportedOnHIVAIDSServicesForMSF(indicator,additionalQueryCriteria,startDate, endDate);
        List cgiverList=getNoOfHouseholdsSupportedToAccessHIVCare(indicator,additionalQueryCriteria,startDate, endDate);
        int count=0;
        int maleOvcTotal=0;
        int femaleOvcTotal=0;
        int maleTotal=0;
        int femaleTotal=0;
        int cgiverMale0To17=Integer.parseInt(cgiverList.get(1).toString());
        int cgiverMale18To24=Integer.parseInt(cgiverList.get(2).toString());
        int cgiverMale25AndAbove=Integer.parseInt(cgiverList.get(3).toString());
        int cgiverMaleTotal=Integer.parseInt(cgiverList.get(4).toString());
        int cgiverFemale0To17=Integer.parseInt(cgiverList.get(5).toString());
        int cgiverFemale18To24=Integer.parseInt(cgiverList.get(6).toString());
        int cgiverFemale25AndAbove=Integer.parseInt(cgiverList.get(7).toString());
        int cgiverFemaleTotal=Integer.parseInt(cgiverList.get(8).toString());
        for(int i=0; i<ovcList.size(); i++)
        {
            if(i==6)
            {
                //System.err.println("cgiverMale18To24 is "+cgiverMale18To24);
                //System.err.println("cgiverMale25AndAbove is "+cgiverMale25AndAbove);
                count=Integer.parseInt(ovcList.get(i).toString());
                mainlist.add(count+cgiverMale18To24);
                mainlist.add(cgiverMale25AndAbove);
                maleTotal=count+cgiverMale18To24+cgiverMale25AndAbove;
                //mainlist.add(maleTotal);
                
            }
            else if(i==7)
            {
               maleOvcTotal=Integer.parseInt(ovcList.get(i).toString());
               //cgiverMaleTotal=Integer.parseInt(cgiverList.get(4).toString());
               maleTotal=maleOvcTotal+cgiverMaleTotal;
               mainlist.add(maleTotal);
            }
            else if(i==13)
            {
                count=Integer.parseInt(ovcList.get(i).toString());
                mainlist.add(count+cgiverFemale18To24);
                mainlist.add(cgiverFemale25AndAbove);
                femaleTotal=count+cgiverFemale18To24+cgiverFemale25AndAbove;
            }
            else if(i==14)
            {
               femaleOvcTotal=Integer.parseInt(ovcList.get(i).toString());
               femaleTotal=femaleOvcTotal+cgiverFemaleTotal;
               mainlist.add(femaleTotal);
            }
            else if(i==15)
            {
                mainlist.add(maleTotal+femaleTotal);
            }
            else
            {
                mainlist.add(ovcList.get(i));
            }
        }

        return mainlist;
    }
    public List getNoOfOvcSupportedOnHIVAIDSServicesForMSF(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        String maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);;
        String femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
        String serviceCriteria=" ";
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            
            serviceCriteria=sqg.getOvcServiceDateQuery(startDate, endDate);
            String malesql=serviceCountQueryPart+rutil.getHIVServicesReportQuery()+maleGenderQuery+ageQuery+" "+additionalQueryCriteria+serviceCriteria;
            String femalesql=serviceCountQueryPart+rutil.getHIVServicesReportQuery()+femaleGenderQuery+ageQuery+" "+additionalQueryCriteria+serviceCriteria;
            count=(Integer.parseInt(execReportQuery(malesql).get(0).toString()));
            maleList.add(count);
            maleTotal+=count;
            count=(Integer.parseInt(execReportQuery(femalesql).get(0).toString()));
            femaleList.add(count);
            femaleTotal+=count;
            System.err.println("malesql in getNoOfOvcSupportedOnHIVAIDSServicesForMSF is "+malesql+" Male: "+maleTotal+" Female: "+femaleTotal);
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        //mainlist.add("Number of active VC beneficiaries receiving support to access HIV services");
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public ReportTemplate getNoOfOvcWithdrawn(String indicator,String additionalQueryCriteria,String startDate,String endDate,int enrollmentStatus) throws Exception
    {
        String maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
        String femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
        int maleTotal=0;
        int femaleTotal=0;
        int maleCount=0;
        int femaleCount=0;
        int startAge=0;
        int endAge=0;
        String ageQuery=" ";
        ReportTemplate rt=new ReportTemplate();
        rt.setIndicatorName(indicator);
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            maleCount=util.getChildEnrollmentDaoInstance().getNoOfOvcByEnrollmentStatusAndHivStatus(additionalQueryCriteria, enrollmentStatus, 0, startDate,endDate, ageSegregation[i], ageSegregation[i+1], maleGender,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
            femaleCount=util.getChildEnrollmentDaoInstance().getNoOfOvcByEnrollmentStatusAndHivStatus(additionalQueryCriteria, enrollmentStatus, 0, startDate,endDate, ageSegregation[i], ageSegregation[i+1], femaleGender,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE);
            
            if(startAge==0 && endAge==5)
            {
                rt.setMale0to5(maleCount);
                rt.setFemale0to5(femaleCount);
            }
            else if(startAge==6 && endAge==12)
            {
                rt.setMale6to12(maleCount);
                rt.setFemale6to12(femaleCount);
            }
            else if(startAge==13 && endAge==17)
            {
                rt.setMale13to17(maleCount);
                rt.setFemale13to17(femaleCount);
            }
            maleTotal+=maleCount;
            femaleTotal+=femaleCount; 
        }
        rt.setMaleTotal(maleTotal);
        rt.setFemaleTotal(femaleTotal);
        rt.setGrandTotal(maleTotal+femaleTotal);
        return rt;
    }
    public List getNoOfOvcOutOfProgram(String indicator,String additionalQueryCriteria,String additionalServiceQuery,String reasonForWithdrawal) throws Exception
    {
        List mainlist=new ArrayList();
        additionalServiceQuery=getAdditionalServiceQuery(additionalServiceQuery);
        
        String ageQuery=" ";
        String queryPart=null;
        String withdrawalQuery="";
        if(reasonForWithdrawal !=null)
        withdrawalQuery=" and withdrawal.reasonWithdrawal like '%"+reasonForWithdrawal+"%'";
        int maleWithdrawn=0;
        int femaleWithdrawn=0;
        List maleWithdrawnList=null;
        List femaleWithdrawnList=null;
        String maleGenderQuery="";
        String femaleGenderQuery="";
        int maleCount=0;
        int femaleCount=0;
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
            List maleList=new ArrayList();
            List femaleList=new ArrayList();
            for(int i=0; i<ageSegregation.length; i+=2)
            {
                maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
                femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
                ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
                queryPart=additionalQueryCriteria+ageQuery;
                
                maleCount=util.getChildEnrollmentDaoInstance().getNoOfOvcByEnrollmentStatus(additionalServiceQuery+maleGenderQuery, AppConstant.EXITEDFROMPROGRAM_NUM);
                maleList.add(maleCount);
                maleTotal+=maleCount;
                
                femaleCount=maleCount=util.getChildEnrollmentDaoInstance().getNoOfOvcByEnrollmentStatus(additionalServiceQuery+femaleGenderQuery, AppConstant.EXITEDFROMPROGRAM_NUM);
                femaleList.add(femaleCount);
                femaleTotal+=femaleCount;
            }
            maleList.add(maleTotal);
            femaleList.add(femaleTotal);
            mainlist.add(indicator);
            mainlist.addAll(maleList);
            mainlist.addAll(femaleList);
            int grandTotal=maleTotal+femaleTotal;
            mainlist.add(grandTotal);
        System.err.println("Inside getNoOfOvcOutOfProgram");
        return mainlist;
    }
    public List getNoOfOvcAbove18YearsWhoGraduated(String indicator,String sex,String additionalQueryCriteria,String additionalEnrollmentQuery,String additionalServiceQuery,String ageQuery) throws Exception
    {
        List mainlist=new ArrayList();
        String maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
        String femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
        additionalEnrollmentQuery=getAdditionalEnrollmentQuery(additionalEnrollmentQuery);
        additionalServiceQuery=getAdditionalServiceQuery(additionalServiceQuery);
        //String dataElement="Number of <b>VC graduated</b> from the program ( > 18)";
        String maleSubSql=" from HouseholdEnrollment hhe, Ovc ovc,OvcWithdrawal withdrawal where hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=withdrawal.ovcId and "+maleGenderQuery;
        String femaleSubSql=" from HouseholdEnrollment hhe, Ovc ovc, OvcWithdrawal withdrawal where hhe.hhUniqueId=ovc.hhUniqueId and ovc.ovcId=withdrawal.ovcId and "+femaleGenderQuery;
        String serviceCriteria=" and withdrawal.reasonWithdrawal like '%ageabove17%'";
        String queryPart=additionalQueryCriteria+serviceCriteria;
        
        int maleCount=0;
        int femaleCount=0;
        int maleTotal=0;
        int femaleTotal=0;
        int mcount=0;
        int fcount=0;
        if(ageQuery==null)
        {
            List maleList=new ArrayList();
            List femaleList=new ArrayList();

            String malesql=maleSubSql+" "+queryPart;
            String femalesql=femaleSubSql+" "+queryPart;
            mcount=(execReportQuery(malesql).size());
            maleCount=mcount;//-maleWithdrawn;
            
            maleList.add(maleCount);
            maleTotal+=maleCount;
            maleList.add(maleTotal);
            fcount=(execReportQuery(femalesql).size());
            femaleCount=fcount;//-femaleWithdrawn;
            femaleList.add(femaleCount);
            femaleTotal+=femaleCount;
            
            femaleList.add(femaleTotal);
            mainlist.add(indicator);
            mainlist.addAll(maleList);
            mainlist.addAll(femaleList);
            /*System.err.println("maleTotal in getNoOfOvcAbove18Years is "+maleTotal);
            System.err.println("femaleTotal in getNoOfOvcAbove18Years is "+femaleCount);
            System.err.println("malesql in getNoOfOvcAbove18YearsWhoGraduated is "+malesql);
            System.err.println("femalesql in getNoOfOvcAbove18YearsWhoGraduated is "+femalesql);*/
        }
        int grandTotal=maleTotal+femaleTotal;
        
        mainlist.add(grandTotal);
        System.err.println("grandTotal in getNoOfOvcAbove18Years is "+grandTotal);
        //System.err.println("Finished executing getNoOfOvcAbove18Years");
        
        return mainlist;
    }
    public List getNoOfOvcAccessingPsychoSupport(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        String maleGenderQuery="";
        String femaleGenderQuery="";
        int j=0;
        int score=0;
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
            femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            String queryPart=serviceCountQueryPart+" (service.serviceAccessed1 is not null and  service.serviceAccessed1 !='' and service.serviceAccessed1 !=' ') and service.serviceDate between '"+startDate+"' and '"+endDate+"'";
            String malesql=queryPart+maleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            String femalesql=queryPart+femaleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            count=(Integer.parseInt(execReportQuery(malesql).get(0).toString()));
            maleList.add(count);
            maleTotal+=count;
            count=(Integer.parseInt(execReportQuery(femalesql).get(0).toString()));
            femaleList.add(count);
            femaleTotal+=count;
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        //mainlist.add("Number of <b>VC</b> accessing <b>Psychosocial support</b> services");
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public ReportTemplate getNoOfOvcServedByDomain(String indicatorName,String additionalQueryCriteria,String startDate,String endDate,int enrollmentStatus,int serviceDomain) throws Exception
    {
        OvcReportManager orm=new OvcReportManager();
        ReportTemplate mainReportTemplate=new ReportTemplate();
        ReportTemplate subReportTemplate=new ReportTemplate();
        Indicator indicator=new Indicator();
        mainReportTemplate.setIndicatorName(indicatorName);
        int startAge=0;
        int endAge=0;
        int maleCount=0;
        int femaleCount=0;
        int maleTotal=0;
        int femaleTotal=0;
        
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            startAge=ageSegregation[i];
            endAge=ageSegregation[i+1];
            subReportTemplate=orm.getNumberOfOvcServedBySubDomain(indicator,additionalQueryCriteria,startAge,endAge,startDate,endDate,enrollmentStatus,serviceDomain);
            maleCount=subReportTemplate.getMaleTotal();
            femaleCount=subReportTemplate.getFemaleTotal();
            if(startAge==0 && endAge==5)
            {
                mainReportTemplate.setMale0to5(maleCount);
                mainReportTemplate.setFemale0to5(femaleCount);
            }
            else if(startAge==6 && endAge==12)
            {
                mainReportTemplate.setMale6to12(maleCount);
                mainReportTemplate.setFemale6to12(femaleCount);
            }
            else if(startAge==13 && endAge==17)
            {
                mainReportTemplate.setMale13to17(maleCount);
                mainReportTemplate.setFemale13to17(femaleCount);
            }
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
            
        }
        mainReportTemplate.setMaleTotal(maleTotal);
        mainReportTemplate.setFemaleTotal(femaleTotal);
        mainReportTemplate.setGrandTotal(maleTotal+femaleTotal);
        return mainReportTemplate;
    }
    public ReportTemplate getNoOfOvcAccessingHealthCare(String indicatorName,String additionalQueryCriteria,String startDate,String endDate,int enrollmentStatus,int serviceDomainType) throws Exception
    {
        OvcReportManager orm=new OvcReportManager();
        ReportTemplate mainReportTemplate=new ReportTemplate();
        ReportTemplate subReportTemplate=new ReportTemplate();
        Indicator indicator=new Indicator();
        
        int startAge=0;
        int endAge=0;
        int maleCount=0;
        int femaleCount=0;
        int maleTotal=0;
        int femaleTotal=0;
        
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            startAge=ageSegregation[i];
            endAge=ageSegregation[i+1];
            subReportTemplate=orm.getNoOfOvcServedByEnrollmentStatusAndServiceDomain(indicator,additionalQueryCriteria,startAge,endAge,startDate,endDate,enrollmentStatus,serviceDomainType);
            maleCount=subReportTemplate.getMaleTotal();
            femaleCount=subReportTemplate.getFemaleTotal();
            if(startAge==0 && endAge==5)
            {
                mainReportTemplate.setMale0to5(maleCount);
                mainReportTemplate.setFemale0to5(femaleCount);
            }
            else if(startAge==6 && endAge==12)
            {
                mainReportTemplate.setMale6to12(maleCount);
                mainReportTemplate.setFemale6to12(femaleCount);
            }
            else if(startAge==13 && endAge==17)
            {
                mainReportTemplate.setMale13to17(maleCount);
                mainReportTemplate.setFemale13to17(femaleCount);
            }
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
            
        }
        mainReportTemplate.setMaleTotal(maleTotal);
        mainReportTemplate.setFemaleTotal(femaleTotal);
        mainReportTemplate.setGrandTotal(maleTotal+femaleTotal);
        return mainReportTemplate;
    }
    public List getNoOfHIVPositivOvcProvidedClinicalServicees(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        //String maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
        //String femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
        int startAge=0;
        int endAge=0;
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        int j=0; //   
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            startAge=ageSegregation[i];
            endAge=ageSegregation[i+1];
            count=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusAndHivStatusAndServiceType(additionalQueryCriteria,AppConstant.EVER_ENROLLED_NUM,AppConstant.HIV_POSITIVE_NUM,startDate, endDate, startAge,endAge,maleGender,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE,AppConstant.HEALTH_DOMAIN,null);
            maleList.add(count);
            maleTotal+=count;
            count=util.getChildServiceDaoInstance().getNumberOfOvcServedByEnrollmentStatusAndHivStatusAndServiceType(additionalQueryCriteria,AppConstant.EVER_ENROLLED_NUM,AppConstant.HIV_POSITIVE_NUM,startDate, endDate, startAge,endAge,femaleGender,AppConstant.ENROLLED_ON_TREATMENT_NOTAPPLICABLE,AppConstant.HEALTH_DOMAIN,null);
            femaleList.add(count);
            femaleTotal+=count;
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        //mainlist.add("Number of <b>VC</b> accessing <b>Health care</b> services");
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfOvcAccessingNutritional(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        String maleGenderQuery="";
        String femaleGenderQuery="";
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        int j=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
            femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            String queryPart=serviceCountQueryPart+"(service.serviceAccessed2 is not null and  service.serviceAccessed2 !='' and service.serviceAccessed2 !=' ') and service.serviceDate between '"+startDate+"' and '"+endDate+"'";
            String malesql=queryPart+maleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            String femalesql=queryPart+femaleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            count=(Integer.parseInt(execReportQuery(malesql).get(0).toString()));
            maleList.add(count);
            maleTotal+=count;
            count=(Integer.parseInt(execReportQuery(femalesql).get(0).toString()));
            femaleList.add(count);
            femaleTotal+=count;
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        //mainlist.add("Number of <b>VC</b> accessing <b>Nutritional</b> services");
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfOvcAccessingEducational(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        String maleGenderQuery="";
        String femaleGenderQuery="";
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
            femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            String queryPart=serviceCountQueryPart+"(service.serviceAccessed4 is not null and  service.serviceAccessed4 !='' and service.serviceAccessed4 !=' ') and service.serviceDate between '"+startDate+"' and '"+endDate+"'";
            String malesql=queryPart+maleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            String femalesql=queryPart+femaleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            count=(Integer.parseInt(execReportQuery(malesql).get(0).toString()));
            maleList.add(count);
            maleTotal+=count;
            count=(Integer.parseInt(execReportQuery(femalesql).get(0).toString()));
            femaleList.add(count);
            femaleTotal+=count;
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        //mainlist.add("Number of <b>VC</b> accessing <b>Education</b> services");
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfOvcAccessingShelter(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        String maleGenderQuery="";
        String femaleGenderQuery="";
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
            femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            String queryPart=serviceCountQueryPart+" (service.serviceAccessed6 is not null and  service.serviceAccessed6 !='' and service.serviceAccessed6 !=' ') and service.serviceDate between '"+startDate+"' and '"+endDate+"'";
            String malesql=queryPart+maleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            String femalesql=queryPart+femaleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            count=(Integer.parseInt(execReportQuery(malesql).get(0).toString()));
            maleList.add(count);
            maleTotal+=count;
            count=(Integer.parseInt(execReportQuery(femalesql).get(0).toString()));
            femaleList.add(count);
            femaleTotal+=count;
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        //mainlist.add("Number of <b>VC</b> accessing <b>Shelter</b> services");
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfOvcAccessingProtection(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        String ageQuery="";
        String maleGenderQuery="";
        String femaleGenderQuery="";
        int maleTotal=0;
        int femaleTotal=0;
        int count=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
            femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
            ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i],ageSegregation[i+1]);
            //ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
            String queryPart=serviceCountQueryPart+" (service.serviceAccessed5 is not null and  service.serviceAccessed5 !='' and service.serviceAccessed5 !=' ') and service.serviceDate between '"+startDate+"' and '"+endDate+"'";
            String malesql=queryPart+" and "+maleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            String femalesql=queryPart+" and "+femaleGenderQuery+ageQuery+" "+additionalQueryCriteria;
            count=(Integer.parseInt(execReportQuery(malesql).get(0).toString()));
            maleList.add(count);
            maleTotal+=count;
            count=(Integer.parseInt(execReportQuery(femalesql).get(0).toString()));
            femaleList.add(count);
            femaleTotal+=count;
        }
        maleList.add(maleTotal);
        femaleList.add(femaleTotal);
        mainlist.add(indicator);
        //mainlist.add("Number of <b>VC</b> accessing <b>Protection</b> services");
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        int grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfOvcAccessingEconStrengthening(String indicator,String additionalQueryCriteria,String startDate,String endDate,String ageSegType) throws Exception
    {
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        String maleGenderQuery="";
        String femaleGenderQuery="";
        int maleTotal=0,femaleTotal=0,grandTotal=0,maleCount=0,femaleCount=0;
        int k=4;
        String blankStyle="<label style='background-color:grey; width:50px'>&nbsp;</label>";
        if(ageSegType.equalsIgnoreCase("new"))
        {
            ageSegregation=getNewAgeSegregation();
            k=0;
            for(int i=k; i<ageSegregation.length; i+=2)
            {
                maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
                femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
                ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
                String queryPart=serviceCountQueryPart+"(service.serviceAccessed7 is not null and  service.serviceAccessed7 !='' and service.serviceAccessed7 !=' ') and service.serviceDate between '"+startDate+"' and '"+endDate+"'";
                String malesql=queryPart+maleGenderQuery+" and ovc.age between "+ ageSegregation[i]+" and "+ageSegregation[i+1]+" "+additionalQueryCriteria;
                String femalesql=queryPart+femaleGenderQuery+" and ovc.age between "+ ageSegregation[i]+" and "+ageSegregation[i+1]+" "+additionalQueryCriteria;
                maleCount=((Long)execReportQuery(malesql).get(0)).intValue();
                femaleCount=((Long)execReportQuery(femalesql).get(0)).intValue();
                maleList.add(maleCount);
                femaleList.add(femaleCount);
                maleTotal+=maleCount;
                femaleTotal+=femaleCount;
            }
            maleList.add(maleTotal);
            femaleList.add(femaleTotal);
        }
        else
        {
            for(int i=k; i<ageSegregation.length; i+=2)
            {
                maleGenderQuery=SubQueryGenerator.getOvcSexQuery(maleGender);
                femaleGenderQuery=SubQueryGenerator.getOvcSexQuery(femaleGender);
                ageQuery=sqg.getOvcCurrentAgeQuery(ageSegregation[i], ageSegregation[i+1]);
                String queryPart=serviceCountQueryPart+"(service.serviceAccessed7 is not null and  service.serviceAccessed7 !='' and service.serviceAccessed7 !=' ') and service.serviceDate between '"+startDate+"' and '"+endDate+"'";
                String malesql=queryPart+maleGenderQuery+" and ovc.age between "+ ageSegregation[i]+" and "+ageSegregation[i+1]+" "+additionalQueryCriteria;
                String femalesql=queryPart+femaleGenderQuery+" and ovc.age between "+ ageSegregation[i]+" and "+ageSegregation[i+1]+" "+additionalQueryCriteria;
                maleCount=((Long)execReportQuery(malesql).get(0)).intValue();
                femaleCount=((Long)execReportQuery(femalesql).get(0)).intValue();
                maleList.add(blankStyle);
                maleList.add(blankStyle);
                femaleList.add(blankStyle);
                femaleList.add(blankStyle);
                maleList.add(maleCount);
                femaleList.add(femaleCount);
                maleTotal+=maleCount;
                femaleTotal+=femaleCount;
            }
            maleList.add(maleTotal);
            femaleList.add(femaleTotal);
        }
        mainlist.add(indicator);
        //mainlist.add("Number of <b>VC</b> accessing <b>Economic Strengthening</b> services");
        //System.err.println("maleList size is "+maleList.size());
        //System.err.println("femaleList size is "+femaleList.size());
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        System.err.println("mainList size is "+mainlist.size());
        return mainlist;
    }
    public List getNoOfCaregiversTrained(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        //int[] ageSegregation=getHouseholdAgeSegregation();
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        
        int maleTotal=0,femaleTotal=0,grandTotal=0,maleCount=0,femaleCount=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            //String queryPart=hheCaregiverHhsQuery+" and hhs.economicStrengtheningServices like '%Vocational/apprenticeship training%' and hhs.serviceDate between '"+startDate+"' and '"+endDate+"' and ";
            maleCount=util.getHouseholdServiceDaoInstance().getNumberOfHouseholdsServedByServiceDomainAndSubType(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, ageSegregation[i], ageSegregation[i+1], maleGender, AppConstant.STABLE_DOMAIN, null);
            femaleCount=util.getHouseholdServiceDaoInstance().getNumberOfHouseholdsServedByServiceDomainAndSubType(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, ageSegregation[i], ageSegregation[i+1], femaleGender, AppConstant.STABLE_DOMAIN, null);
            
            maleList.add(maleCount);
            femaleList.add(femaleCount);
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
        }
        mainlist.add(indicator);
        //mainlist.add("Number of <b>caregivers/household heads</b> provided with at least one service");
        mainlist.addAll(maleList);
        mainlist.add(maleTotal);
        mainlist.addAll(femaleList);
        mainlist.add(femaleTotal);
        grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfCaregiversAndHouseholdHeadsProvidedWithAtleastOneService(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        int[] ageSegregation=getHouseholdAgeSegregation();
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        
        int maleTotal=0,femaleTotal=0,grandTotal=0,maleCount=0,femaleCount=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            maleCount=util.getHouseholdServiceDaoInstance().getNumberOfBeneficiariesServedByEnrollmentStatus(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, ageSegregation[i], ageSegregation[i+1], maleGender);
            femaleCount=util.getHouseholdServiceDaoInstance().getNumberOfBeneficiariesServedByEnrollmentStatus(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, ageSegregation[i], ageSegregation[i+1], femaleGender);
            
            maleList.add(maleCount);
            femaleList.add(femaleCount);
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
        }
        mainlist.add(indicator);
        //mainlist.add("Number of <b>caregivers/household heads</b> provided with at least one service");
        mainlist.addAll(maleList);
        mainlist.add(maleTotal);
        mainlist.addAll(femaleList);
        mainlist.add(femaleTotal);
        grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfHouseholdsSupportedToAccessHIVCare(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        int[] ageSegregation=getHouseholdAgeSegregation();
        
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();
        int maleTotal=0,femaleTotal=0,grandTotal=0,maleCount=0,femaleCount=0;

        for(int i=0; i<ageSegregation.length; i+=2)
        {
            //String queryPart=hheCaregiverHhsQuery+" and (hhs.healthServices like '%HIV services%' or hhs.healthServices like '%HIV care and support%')"+periodQuery+" and ";
            //String malesql=queryPart+util.getCaregiverGenderCriteria(hviMaleGender)+" and "+util.getCaregiverAgeCriteria(ageSegregation[i],ageSegregation[i+1])+" "+additionalQueryCriteria;
            //String femalesql=queryPart+util.getCaregiverGenderCriteria(hviFemaleGender)+" and "+util.getCaregiverAgeCriteria(ageSegregation[i],ageSegregation[i+1])+" "+additionalQueryCriteria;
            maleCount=util.getHouseholdServiceDaoInstance().getNumberOfHouseholdsServedByServiceDomainAndSubType(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, ageSegregation[i], ageSegregation[i+1], maleGender, AppConstant.HEALTH_DOMAIN, null);
            femaleCount=util.getHouseholdServiceDaoInstance().getNumberOfHouseholdsServedByServiceDomainAndSubType(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, ageSegregation[i], ageSegregation[i+1], femaleGender, AppConstant.HEALTH_DOMAIN, null);
            maleList.add(maleCount);
            femaleList.add(femaleCount);
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
        }
        mainlist.add(indicator);
        //mainlist.add("Number of caregivers/household heads supported to access HIV services");
        mainlist.addAll(maleList);
        mainlist.add(maleTotal);
        mainlist.addAll(femaleList);
        mainlist.add(femaleTotal);
        grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfHouseholdHeadsReceivingEconomicStrenghtening(String indicator,String additionalQueryCriteria,String startDate,String endDate) throws Exception
    {
        int[] ageSegregation=getHouseholdAgeSegregation();
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();

        int maleTotal=0,femaleTotal=0,grandTotal=0,maleCount=0,femaleCount=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            //String queryPart=hheCaregiverHhsQuery+" and (hhs.economicStrengtheningServices is not null and hhs.economicStrengtheningServices !='' and hhs.economicStrengtheningServices !=' ' and hhs.economicStrengtheningServices !='  ' ) and hhs.serviceDate between '"+startDate+"' and '"+endDate+"' and ";
            maleCount=maleCount=util.getHouseholdServiceDaoInstance().getNumberOfHouseholdsServedByServiceDomainAndSubType(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, ageSegregation[i], ageSegregation[i+1], maleGender, AppConstant.STABLE_DOMAIN, null);
            femaleCount=util.getHouseholdServiceDaoInstance().getNumberOfHouseholdsServedByServiceDomainAndSubType(additionalQueryCriteria, AppConstant.EVER_ENROLLED_NUM, startDate, endDate, ageSegregation[i], ageSegregation[i+1], femaleGender, AppConstant.STABLE_DOMAIN, null);
            maleList.add(maleCount);
            femaleList.add(femaleCount);
            maleTotal+=maleCount;
            femaleTotal+=femaleCount;
        }
        mainlist.add(indicator);
        //mainlist.add("Number of caregivers/Household heads receiving Economic Strengthening");
        mainlist.addAll(maleList);
        mainlist.add(maleTotal);
        mainlist.addAll(femaleList);
        mainlist.add(femaleTotal);
        grandTotal=maleTotal+femaleTotal;
        mainlist.add(grandTotal);
        return mainlist;
    }
    public List getNoOfHouseholdsReceivingEconomicStrenghtening(String indicator,String additionalQueryCriteria,String startDate,String endDate,String ageSegType) throws Exception
    {
        int[] ageSegregation=getHouseholdAgeSegregation();
        if(ageSegType.equalsIgnoreCase("new"))
        ageSegregation=getNewAgeSegregation();
        List mainlist=new ArrayList();
        List maleList=new ArrayList();
        List femaleList=new ArrayList();

        int maleTotal=0,femaleTotal=0,grandTotal=0,maleCount=0,femaleCount=0;
        for(int i=0; i<ageSegregation.length; i+=2)
        {
            maleList.add(maleCount);
            femaleList.add(femaleCount);
        }
        maleList.add(" ");
        femaleList.add(" ");
        String queryPart=hheServiceCountQuery+" and (hhs.economicStrengtheningServices is not null and hhs.economicStrengtheningServices !='' and hhs.economicStrengtheningServices !=' ' and hhs.economicStrengtheningServices !='  ' ) and hhs.serviceDate between '"+startDate+"' and '"+endDate+"'";
        String sql=queryPart+" "+additionalQueryCriteria;
        //System.err.println("sql in getNoOfHouseholdsReceivingEconomicStrenghtening is "+sql);
        grandTotal=((Long)execReportQuery(sql).get(0)).intValue();
        mainlist.add(indicator);
        //mainlist.add("Number of Households provided Economic Strengthening");
        mainlist.addAll(maleList);
        mainlist.addAll(femaleList);
        mainlist.add(grandTotal);
        return mainlist;
    }
}
