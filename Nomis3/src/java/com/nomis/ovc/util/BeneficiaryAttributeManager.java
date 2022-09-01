/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;


import com.nomis.ovc.business.ReferralFacility;
import com.nomis.ovc.business.School;
import com.nomis.ovc.business.SchoolGrade;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class BeneficiaryAttributeManager implements Serializable
{
    public BirthCertificate getBirthCertificate(int value)
    {
        return BirthCertificateManager.getBirthCertificate(value);
    }
    public ReferralFacility getReferralFacility(String facilityId) 
    {
        //ReferralFacilityManager rfm=new ReferralFacilityManager();
        return ReferralFacilityManager.getReferralFacilityById(facilityId);
    }
    public SchoolStatus getSchoolStatus(int code)
    {
        return SchoolStatusManager.getSchoolStatus(code);
    }
    public School getSchool(String schoolId)
    {
        SchoolManager sm=new SchoolManager();
        return sm.getSchool(schoolId);
    }
    public SchoolGrade getSchoolGrade(String gradeId)
    {
        SchoolManager sm=new SchoolManager();
        return sm.getSchoolGrade(gradeId);
    }
    public CaregiverRelationship getCaregiverRelationship(int relationshipId)
    {
        CaregiverRelationshipManager crm=new CaregiverRelationshipManager();
        return crm.getCaregiverRelationship(relationshipId);
    }
    public SourceOfInformation getSourceOfInformation(int value)
    {
        SourceOfInformationManager sim=new SourceOfInformationManager();
        return sim.getSourceOfInformation(value);
    }
    public Disability getDisabilityInformation(int value)
    {
        DisabilityManager dm=new DisabilityManager();
        return dm.getDisability(value);
    }
    
}
