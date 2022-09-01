<%-- 
    Document   : HouseholdServiceRegister
    Created on : Dec 14, 2018, 6:38:18 AM
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
        <title>Household service register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=householdServiceRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="householdServiceListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >State </th>
                                <th >LGA </th>
                                <th >Ward </th>
                                <th >CBO </th>
                                <th >Address </th>
                                <th >Date of enrollment (mm/dd/yyyy)</th>
                                <th >Age at baseline</th>
                                <th >Current age</th>
                                <th >Date of service</th>
                                <th>HH Unique Id</th>
                                <th>Beneficiary Id</th>
                                <th width="200">Beneficiary name </th>
                                <th >Sex(M/F)</th>
                                <th >Current enrollment status</th>
                                <th >Date of current status</th>
                                <th >Current HIV status</th>
                                <th >Date of HIV status</th>
                                <th >Health</th>
                                <th >Referral</th>
                                <th >Safe</th>
                                <th >Stable</th>
                                <th >Schooled</th>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="householdServiceListForRegister" id="service">
                                   <tr style="background-color: ${service.rowColor}">
                                       <td>${service.serialNo}</td>
                                       <td>${service.ahm.hhe.level2Ou.name}</td>
                                       <td>${service.ahm.hhe.level3Ou.name}</td>
                                       <td>${service.ahm.hhe.level4Ou.name}</td>
                                       <td>${service.ahm.hhe.cbo.cboName}</td>
                                       <td>${service.ahm.hhe.address}</td>
                                       <td>${service.ahm.dateOfEnrollment}</td>
                                       <td>${service.ahm.ageAtBaseline}</td>
                                       <td>${service.ahm.currentAge}</td>
                                       <td>${service.serviceDate}</td>
                                       <td>${service.ahm.hhUniqueId}</td>
                                       <td>${service.ahm.beneficiaryId}</td>
                                       <td>${service.ahm.firstName} ${service.ahm.surname}</td>
                                       <td>${service.ahm.sex}</td>
                                       <td>${service.ahm.currentEnrollmentStatusObj.statusName}</td>
                                       <td>${service.ahm.dateOfCurrentEnrollmentStatus}</td>
                                       
                                       <td>${service.ahm.currentHivStatusObject.name}</td>
                                       <td>${service.ahm.dateOfCurrentHivStatus}</td>                                     
                                       <td>${service.healthServiceNames}</td>
                                       <td>${service.referralServiceNames}</td>
                                       <td>${service.safetyServiceNames}</td>
                                       <td>${service.stableServiceNames}</td>
                                       <td>${service.schoolServiceNames}</td>
                                       <td>${service.lastModifiedDate}</td>
                                       <td>${service.recordedBy}</td>
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>

