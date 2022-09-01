<%-- 
    Document   : CaregiverEnrollmentRegister
    Created on : Oct 11, 2018, 9:32:05 AM
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
        <title>Caregiver enrollment register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=caregiverRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="cgiverListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th>
                                <th >CBO </th>
                                <th >Date of enrollment (yyyy-mm-dd)</th>
                                <th>HH Unique Id</th>
                                <th>Caregiver Id</th>
                                <th width="200">Caregiver name </th>
                                <th width="200">Current age </th>
                                <th >Sex(M/F)</th>
                                <th >Date of birth</th>
                                <%--<th >Village</th>--%> 
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
                                <th >Facility ID</th>
                                <th >Treatment ID</th>
                                <th >Viral load</th>
                                <th >Date created</th>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="cgiverListForRegister" id="cgiver">
                                   <tr style="background-color: ${cgiver.rowColor}">
                                       <td>${cgiver.serialNo}</td>
                                       <td>${cgiver.hhe.level2Ou.name}</td>
                                       <td>${cgiver.hhe.level3Ou.name}</td>
                                       <td>${cgiver.hhe.level4Ou.name}</td>
                                       <td>${cgiver.hhe.cbo.cboName}</td>
                                       <td>${cgiver.dateOfEnrollment}</td>
                                       <td>${cgiver.hhUniqueId}</td>
                                       <td>${cgiver.beneficiaryId}</td>
                                       <td>${cgiver.firstName} ${cgiver.surname}</td>
                                       <td>${cgiver.currentAge}</td>
                                       <td>${cgiver.sex}</td>
                                       <td>${cgiver.dateOfBirth}</td>
                                       <%--<td>${cgiver.hhe.village}</td>--%>
                                       <td>${cgiver.hhe.address}</td>
                                       <td>${cgiver.phoneNumber}</td>
                                       <td>${cgiver.occupation}</td>
                                       <td>${cgiver.educationLevel}</td>
                                       <td>${cgiver.currentEnrollmentStatusObj.statusName}</td>
                                       <td>${cgiver.dateOfCurrentEnrollmentStatus}</td>
                                       <td>${cgiver.baselineHivStatusObject.name}</td>
                                       <td>${cgiver.currentHivStatusObject.name}</td>
                                       <td>${cgiver.dateOfCurrentHivStatus}</td>
                                       <td>${cgiver.enrolledOnTreatmentObject.code}</td>
                                       <td>${cgiver.referralFacility.facilityName}</td>
                                       <td>${cgiver.hivTreatmentFacilityId}</td>
                                       <td>${cgiver.treatmentId}</td>
                                       <td>${cgiver.viralLoad}</td>
                                       <td>${cgiver.dateCreated}</td>
                                       <td>${cgiver.lastModifiedDate}</td>
                                       <td>${cgiver.recordedBy}</td> 
                                    </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
