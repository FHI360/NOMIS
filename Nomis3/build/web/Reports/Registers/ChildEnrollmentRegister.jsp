<%-- 
    Document   : ChildEnrollmentRegister
    Created on : Oct 10, 2018, 7:19:01 AM
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
        <title>Enrollment register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=childRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="ovcListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th> 
                                <th >CBO </th>
                                <th >Date of enrollment (yyyy-mm-dd)</th>
                                <th>HH Unique Id</th>
                                <th>OVC Id</th>
                                <%--<logic:present name="viewDetails">
                                </logic:present>--%>
                                <th>Enrollment setting</th>
                                <th width="200">Child name </th>
                                <th >Address</th>
                                <th >Phone</th>
                                
                                <th width="200">Current age </th>
                                <th >Sex(M/F)</th>
                                <th >Date of birth</th>
                                
                                <th >Baseline HIV status</th>
                                <th >Current HIV status</th>
                                <th >Date of Current HIV status</th>
                                <th >Enrolled on treatment?</th>
                                <th >Facility enrolled</th>
                                <th >Facility ID</th>
                                <th >Treatment ID</th>
                                <th >Viral load</th>
                                <th >Main enrollment stream </th>
                                <th >Other enrollment stream </th>
                                <th >Child has birth certificate at baseline?</th>
                                <th >Child has birth certificate currently</th>
                                <th >Current enrollment status</th>
                                <th >Date of current status</th>
                                <th >Child in school</th>
                                <th >Name of school</th>
                                <th >Form/Grade</th>
                                <th >Primary caregiver </th>
                                <%--<th >Secondary caregiver </th>--%>
                                <th >relationship to child</th>
                                <th >Baseline Weight</th>                         
                                <th >Baseline Height</th>
                                <th >Current Weight</th>                         
                                <th >Current Height</th>
                                <th >Source of information</th>
                                <th>Child has case plan?</th>
                                <th>Date case plan developed</th>
                                <th >Volunteer/Service provider</th>
                                <%--<th >Date created</th>--%>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                                <th >Child history</th> 
                </tr>
                               <logic:iterate name="ovcListForRegister" id="ovc">
                                   <tr style="background-color: ${ovc.rowColor}">
                                       <td>${ovc.serialNo}</td>
                                       <td>${ovc.hhe.level2Ou.name}</td>
                                       <td>${ovc.hhe.level3Ou.name}</td>
                                       <td>${ovc.hhe.level4Ou.name}</td>
                                       <td>${ovc.hhe.cbo.cboName}</td>
                                       <td>${ovc.dateOfEnrollment}</td>
                                       <td>${ovc.hhUniqueId}</td>
                                       <td>${ovc.ovcId}</td>
                                       <td>${ovc.enrollmentSettingName}</td>
                                       <%--<logic:present name="viewDetails">
                                           </logic:present>--%>
                                       <td>${ovc.firstName} ${ovc.surname}</td>
                                       <td>${ovc.hhe.address}</td>
                                       <td>${ovc.phoneNumber}</td>
                                       
                                       <td>${ovc.currentAge} ${ovc.currentAgeUnitName}</td>
                                       <td>${ovc.sex}</td>
                                       <td>${ovc.dateOfBirth}</td>
                                       
                                       <td>${ovc.baselineHivStatusObject.name}</td>
                                       <td>${ovc.currentHivStatusObject.name}</td>
                                       <td>${ovc.dateOfCurrentHivStatus}</td>
                                       <td>${ovc.enrolledOnTreatmentObject.code}</td>
                                       <td>${ovc.referralFacility.facilityName}</td>
                                       <td>${ovc.hivTreatmentFacilityId}</td>
                                       <td>${ovc.treatmentId}</td>
                                       <td>${ovc.viralLoad}</td>
                                       <td>${ovc.mainVulnerabilityStatusName}</td>
                                       <td>${ovc.selectedVulnerabilityNames}</td>
                                       <td>${ovc.baselineBirthCertificateObject.name}</td>
                                       <td>${ovc.currentBirthCertificateObject.name}</td>
                                       <td>${ovc.currentEnrollmentStatusObj.statusName}</td>
                                       <td>${ovc.dateOfCurrentEnrollmentStatus}</td>
                                       <td>${ovc.schoolStatusObj.code}</td>
                                       <td>${ovc.schoolObj.schoolName}</td>
                                       <td>${ovc.schoolGradeObj.gradeName}</td>
                                       <td>${ovc.primaryCaregiver.fullName}</td>
                                       <%--<td>${ovc.hhe.secCaregiver.fullName}</td>--%>
                                       <td>${ovc.caregiverRelationshipObj.name}</td>
                                       <td>${ovc.weight}</td>
                                       <td>${ovc.height}</td>
                                       <td>${ovc.currentNutritionStatus.weight}</td>
                                       <td>${ovc.currentNutritionStatus.height}</td>
                                      <td>${ovc.sourceOfInformationObj.name}</td>
                                       <td>${ovc.casePlanAnswer}</td>
                                       <td>${ovc.dateCasePlanDeveloped}</td>
                                       <td>${ovc.communityWorker.firstName} ${ovc.communityWorker.surname}</td>
                                       <%--<td>${ovc.dateCreated}</td>--%>
                                       <td>${ovc.lastModifiedDate}</td>
                                       <td>${ovc.recordedBy}</td>
                                       <td><a href="householdenrollmentregister.do?id=childHistory:${ovc.ovcId}" target="_blank">Open Child file</a></td>
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>

