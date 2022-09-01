<%-- 
    Document   : HouseholdEnrollmentRegister
    Created on : May 31, 2020, 11:38:29 PM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Household enrollment register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=caregiverRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="hheListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th>
                                <th >CBO </th>
                                <th >Date of enrollment (yyyy-mm-dd)</th>
                                <th>HH Unique Id</th>
                                <%--<th>Caregiver Id</th>--%>
                                <th width="200">Caregiver name </th>
                                <th width="200">Current age </th>
                                <th >Sex(M/F)</th>
                                <%--<th >Date of birth</th>
                                <th >Village</th>--%> 
                                <th >Address</th>
                                <th >Phone</th>
                                <th >Occupation</th>
                                <th >Education level</th>
                                <th >Current enrollment status</th>
                                <th >Date of current status</th>
                                <th >Baseline HIV status</th>
                                <th >Current HIV status</th>
                                <th >Date of Current HIV status</th>
                                <th >Enrolled on treatment</th>
                                
                                <th >Facility enrolled</th>
                                <th >Treatment ID</th>
                                <th >Viral load</th>
                                <th >Name of volunteer</th>
                                <th >Date created</th>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                               
                                <th >All children, adolescents, and caregiver(s) in the household have known HIV status or a test is not required based on risk assessment</th>
                                <th >All HIV+ children, adolescents and caregiver in the household are linked to and have adhered to treatment for 12 months after initiation of ART</th>
                                <th >All HIV+ children, adolescents and caregiver(s) in the household have a viral load result documented in the last 12 months</th>
                                <th >All adolescents 10-17 years of age in the household have key knowledge about preventing HIV infection</th>
                                <th >No children < 5 years in the household are undernourished</th>
                                <th >All children in the household have birth certificates</th>
                                <th >There is a stable adult in the household who provides consistent care, attention and support to the children and adolescents?</th>
                                <th >Children, adolescent or caregivers in the household reported experience(s) of violence in the last 6 month</th>
                                
                                <th >All children aged 6 years and above are enrolled in school</th>
                                <th >All children and adolescent enrolled in school have attended regularly and progressed in the last year</th>
                                <th >Caregiver is engaged in economic activities that helps meet the critical needs of the children in the household</th>
                                <th >Caregiver can identify an individual or group recognized as providing social and emotional support</th>
                                <th>Total assessment score</th>
                </tr>           <logic:iterate name="hheListForRegister" id="hhe">
                                   <tr style="background-color: ${hhe.rowColor}">
                                       <td>${hhe.serialNo}</td>
                                       <td>${hhe.level2Ou.name}</td>
                                       <td>${hhe.level3Ou.name}</td>
                                       <td>${hhe.level4Ou.name}</td>
                                       <td>${hhe.cbo.cboName}</td>
                                       <td>${hhe.dateOfAssessment}</td>
                                       <td>${hhe.hhUniqueId}</td>
                                       <%--<td> </td>--%>
                                       <td>${hhe.prCaregiver.firstName} ${prCaregiver.surname}</td>
                                       <td>${hhe.prCaregiver.currentAge}</td>
                                       <td>${hhe.prCaregiver.sex}</td>
                                       <%--<td>${hhe.prCaregiver.dateOfBirth}</td>
                                       <td>${prCaregiver.hhe.village}</td>--%>
                                       <td>${hhe.address}</td>
                                       <td>${hhe.prCaregiver.phoneNumber}</td>
                                       <td>${hhe.prCaregiver.occupation}</td>
                                       <td>${hhe.prCaregiver.educationLevel}</td>
                                       <td>${hhe.currentEnrollmentStatusObj.statusName}</td>
                                       <td>${hhe.dateOfCurrentStatus}</td>
                                       <td>${hhe.prCaregiver.baselineHivStatusObject.name}</td>
                                       <td>${hhe.prCaregiver.currentHivStatusObject.name}</td>
                                       <td>${hhe.prCaregiver.dateOfCurrentHivStatus}</td>
                                       <td>${hhe.prCaregiver.enrolledOnTreatmentObject.code}</td>
                                       
                                       <td>${hhe.prCaregiver.referralFacility.facilityName}</td>
                                       <td>${hhe.prCaregiver.treatmentId}</td>
                                       <td>${hhe.prCaregiver.viralLoad}</td>
                                       <td>${hhe.communityWorker.firstName} ${hhe.communityWorker.surname}</td>
                                       <td>${hhe.dateCreated}</td>
                                       <td>${hhe.lastModifiedDate}</td>
                                       <td>${hhe.recordedBy}</td>
                                       
                                       <td>${hhe.rhva.hivStatusKnownName}</td>
                                       <td>${hhe.rhva.hivPositiveLinkedName}</td>
                                       <td>${hhe.rhva.hasViralLoadResultName}</td>
                                       <td>${hhe.rhva.hivPreventionKnowledgeName}</td>
                                       <td>${hhe.rhva.childUndernourishedName}</td>
                                       <td>${hhe.rhva.childrenHasBirthCertificateName}</td>
                                       <td>${hhe.rhva.stableAdultName}</td>
                                       <td>${hhe.rhva.violenceExperienceReportedName}</td>
                                       <td>${hhe.rhva.childrenEnrolledInSchoolName}</td>
                                       <td>${hhe.rhva.regularSchoolAttendanceName}</td>
                                       <td>${hhe.rhva.cgEngagedInEconomicActivitiesName}</td>
                                       <td>${hhe.rhva.socialEmotionalSupportName}</td>
                                        <td>${hhe.rhva.vulnerabilityScore}</td>
                                    </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
