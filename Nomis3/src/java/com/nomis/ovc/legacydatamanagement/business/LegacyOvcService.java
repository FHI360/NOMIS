/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.legacydatamanagement.business;


import java.io.Serializable;

/**
 *
 * @author HP USER
 */
public class LegacyOvcService implements Serializable {

    public LegacyOvcService() {

    }

    private int id;
    private String ovcId;
    private String servicedate;
    private String dateOfEntry;
    private String providerName;
    private String serviceAccessed1;
    private String serviceAccessed2;
    private String serviceAccessed3;
    private String serviceAccessed4;
    private String serviceAccessed5;
    private String serviceAccessed6;
    private String serviceAccessed7;
    private String servicesReferred;
    private int surveyNumber;
    private int numberOfServices;
    private double weight;
    private double height;
    private String currentHivStatus;
    private String pointOfService="service";
    private String enrolledInCare="No";
    private String enrolledOnART="No";
    private String facilityId;
    private int markedForDelete;
    private String recordedBy;
    private String childMissedSchool;
    private int childAbused;
    private int childLinkedToGovt;
    private String childAbusedAnswer;
    private String childLinkedToGovtAnswer;
    
    private String psychosocialServicesByName;
    private String healthServicesByName;
    private String nutritionalServicesByName;
    private String educationalServicesByName;
    private String protectionServicesByName;
    private String shelterAndCareServicesByName;
    private String economicStrenghteningServicesByName;
    boolean removeStartAndEndWildCharacters=true;

public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}

public String getOvcId() {
return ovcId;
}
public void setOvcId(String ovcId) {
this.ovcId = ovcId;
}

   public String getServicedate() {
        return servicedate;
    }

    public void setServicedate(String servicedate) {
        this.servicedate = servicedate;
    }

    
    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getServicesReferred() {
        return servicesReferred;
    }

    public void setServicesReferred(String servicesReferred) {
        this.servicesReferred = servicesReferred;
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

public String getServiceAccessed1() {
return serviceAccessed1;
}
public void setserviceAccessed1(String serviceAccessed1) {
this.serviceAccessed1 = serviceAccessed1;
}

public String getServiceAccessed2() {
return serviceAccessed2;
}
public void setserviceAccessed2(String serviceAccessed2) {
this.serviceAccessed2 = serviceAccessed2;
}

public String getServiceAccessed3() {
return serviceAccessed3;
}
public void setserviceAccessed3(String serviceAccessed3) {
this.serviceAccessed3 = serviceAccessed3;
}

public String getServiceAccessed4() {
return serviceAccessed4;
}
public void setserviceAccessed4(String serviceAccessed4) {
this.serviceAccessed4 = serviceAccessed4;
}

public String getServiceAccessed5() {
return serviceAccessed5;
}
public void setserviceAccessed5(String serviceAccessed5) {
this.serviceAccessed5 = serviceAccessed5;
}

public String getServiceAccessed6() {
return serviceAccessed6;
}
public void setserviceAccessed6(String serviceAccessed6) {
this.serviceAccessed6 = serviceAccessed6;
}

public String getServiceAccessed7() {
return serviceAccessed7;
}
public void setserviceAccessed7(String serviceAccessed7) {
this.serviceAccessed7 = serviceAccessed7;
}

public int getSurveyNumber() {
    return surveyNumber;
}

public void setSurveyNumber(int surveyNumber) {
    this.surveyNumber = surveyNumber;
}

public int getNumberOfServices() {
    return numberOfServices;
}

public void setNumberOfServices(int numberOfServices) {
    this.numberOfServices = numberOfServices;
}

    public String getCurrentHivStatus() {
        return currentHivStatus;
    }

    public void setCurrentHivStatus(String currentHivStatus) {
        this.currentHivStatus = currentHivStatus;
    }

    public String getEnrolledInCare() {
        return enrolledInCare;
    }

    public void setEnrolledInCare(String enrolledInCare) {
        this.enrolledInCare = enrolledInCare;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getPointOfService() {
        return pointOfService;
    }

    public void setPointOfService(String pointOfService) {
        this.pointOfService = pointOfService;
    }

    public String getEnrolledOnART() {
        return enrolledOnART;
    }

    public void setEnrolledOnART(String enrolledOnART) {
        this.enrolledOnART = enrolledOnART;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getChildMissedSchool() {
        return childMissedSchool;
    }

    public void setChildMissedSchool(String childMissedSchool) {
        this.childMissedSchool = childMissedSchool;
    }

    public int getChildAbused() {
        return childAbused;
    }

    public void setChildAbused(int childAbused) {
        this.childAbused = childAbused;
    }

    public int getChildLinkedToGovt() {
        return childLinkedToGovt;
    }

    public void setChildLinkedToGovt(int childLinkedToGovt) {
        this.childLinkedToGovt = childLinkedToGovt;
    }

    public String getChildAbusedAnswer() 
    {
        if(getChildAbused()==0)
        childAbusedAnswer="N/A";
        else if(getChildAbused()==1)
        childAbusedAnswer="No";
        else if(getChildAbused()==2)
        childAbusedAnswer="Yes";
        return childAbusedAnswer;
    }

    public String getEconomicStrenghteningServicesByName() 
    {
        //economicStrenghteningServicesByName=OvcServiceManager.getConcatenatedServiceNames(getServiceAccessed7(),removeStartAndEndWildCharacters);
        return economicStrenghteningServicesByName;
    }

    public String getEducationalServicesByName() 
    {
        //educationalServicesByName=OvcServiceManager.getConcatenatedServiceNames(getServiceAccessed4(),removeStartAndEndWildCharacters);
        return educationalServicesByName;
    }

    public String getHealthServicesByName() 
    {
        //healthServicesByName=OvcServiceManager.getConcatenatedServiceNames(getServiceAccessed3(),removeStartAndEndWildCharacters);
        return healthServicesByName;
    }

    public String getNutritionalServicesByName() 
    {
        //nutritionalServicesByName=OvcServiceManager.getConcatenatedServiceNames(getServiceAccessed2(),removeStartAndEndWildCharacters);
        return nutritionalServicesByName;
    }

    public String getProtectionServicesByName() 
    {
        //protectionServicesByName=OvcServiceManager.getConcatenatedServiceNames(getServiceAccessed5(),removeStartAndEndWildCharacters);
        return protectionServicesByName;
    }

    public String getPsychosocialServicesByName() 
    {
        //psychosocialServicesByName=OvcServiceManager.getConcatenatedServiceNames(getServiceAccessed1(),removeStartAndEndWildCharacters);
        return psychosocialServicesByName;
    }

    public String getShelterAndCareServicesByName() 
    {
        //shelterAndCareServicesByName=OvcServiceManager.getConcatenatedServiceNames(getServiceAccessed6(),removeStartAndEndWildCharacters);
        return shelterAndCareServicesByName;
    }

    public String getChildLinkedToGovtAnswer() 
    {
        if(getChildLinkedToGovt()==0)
        childLinkedToGovtAnswer="N/A";
        else if(getChildLinkedToGovt()==1)
        childLinkedToGovtAnswer="No";
        else if(getChildLinkedToGovt()==2)
        childLinkedToGovtAnswer="Yes";
        return childLinkedToGovtAnswer;
    }

}
