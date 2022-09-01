<%-- 
    Document   : ChildServiceRegister
    Created on : Oct 11, 2018, 1:46:57 PM
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
        <title>Child service register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=childServiceRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="ovcServiceListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >State </th>
                                <th >LGA </th>
                                <th >Ward </th>
                                <th >CBO </th>
                                
                                <th >Address </th>
                                
                                <th >Date of enrollment (mm/dd/yyyy)</th>
                                <th >Date of service</th>
                                <th>HH Unique Id</th>
                                <th>OVC Id</th>
                                <th>Enrollment setting</th>
                                <th width="200">Child name </th>
                                <th >Age at baseline</th>
                                <th >Age unit at baseline</th>
                                <th >Current age</th>
                                <th >Current age unit</th>
                                <th >Sex(M/F)</th>
                                <th >Current enrollment status</th>
                                <th >Date of current status</th>
                                <th >Main enrollment stream </th>
                                <th >Current HIV status</th>
                                <th >Date of current HIV status</th>
                                <th >Enrolled on treatment</th>
                                <th >Facility name</th>
                                <th >Health</th>
                                <th >Referral</th>
                                <th >Safe</th>
                                <th >Schooled</th>
                                <th >Stable</th>
                                <td>Community volunteer</td>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="ovcServiceListForRegister" id="service">
                                   <tr style="background-color: ${service.rowColor}">
                                       <td>${service.serialNo}</td>
                                       <td>${service.ovc.hhe.level2Ou.name}</td>
                                       <td>${service.ovc.hhe.level3Ou.name}</td>
                                       <td>${service.ovc.hhe.level4Ou.name}</td>
                                       <td>${service.ovc.hhe.cbo.cboName}</td>
                                       <td>${service.ovc.hhe.address}</td>
                                       
                                       <td>${service.ovc.dateOfEnrollment}</td>
                                       <td>${service.serviceDate}</td>
                                       <td>${service.ovc.hhUniqueId}</td>
                                       <td>${service.ovc.ovcId}</td>
                                       <td>${service.ovc.enrollmentSettingName}</td>
                                       <td>${service.ovc.firstName} ${service.ovc.surname}</td>
                                       <td>${service.ovc.ageAtBaseline}</td>
                                       <td>${service.ovc.ageUnitAtBaseline}</td>
                                       <td>${service.ovc.currentAge}</td>
                                       <td>${service.ovc.currentAgeUnit}</td>
                                       <td>${service.ovc.sex}</td>
                                       <td>${service.ovc.currentEnrollmentStatusObj.statusName}</td>
                                       <td>${service.ovc.dateOfCurrentEnrollmentStatus}</td>
                                       <td>${service.ovc.mainVulnerabilityStatusName}</td>
                                       <td>${service.ovc.currentHivStatusObject.name}</td>
                                       <td>${service.ovc.dateOfCurrentHivStatus}</td>
                                       <td>${service.ovc.enrolledOnTreatmentObject.code}</td>
                                       <td>${service.ovc.referralFacility.facilityName}</td>
                                       
                                       <td>${service.healthServiceNames}</td>
                                       <td>${service.referralServiceNames}</td>
                                       <td>${service.safetyServiceNames}</td>
                                       <td>${service.schooledServiceNames}</td>
                                       <td>${service.stableServiceNames}</td>
                                       <td>${service.communityWorker.firstName} ${service.communityWorker.surname}</td>
                                       <td>${service.lastModifiedDate}</td>
                                       <td>${service.recordedBy}</td>
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
