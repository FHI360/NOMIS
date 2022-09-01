<%-- 
    Document   : HouseholdReferralRegister
    Created on : Jun 14, 2021, 10:50:35 PM
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
        <title>Household referral register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=childServiceRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="ReferralServiceListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >State </th>
                                <th >LGA </th>
                                <th >Ward </th>
                                <th >CBO </th>
                                
                                <th >Address </th>
                                
                                <th >Date of enrollment (mm/dd/yyyy)</th>
                                <th >Date of referral</th>
                                <th>HH Unique Id</th>
                                <th>OVC Id</th>
                                <th width="200">Beneficiary name </th>
                                <th>Beneficiary type </th>
                                <th >Age at baseline</th>
                                <th >Age unit at baseline</th>
                                <th >Current age</th>
                                <th >Current age unit</th>
                                <th >Sex(M/F)</th>
                                <th >Current HIV status</th>
                                <th >Date of Current HIV status</th>
                                <th >Enrolled on treatment</th>
                                <th >Facility name</th>
                                <th >Health</th>
                                <th >Safe</th>
                                <th >Schooled</th>
                                <th >Stable</th>
                                <th >Referral complete?</th>
                                <td>Community volunteer</td>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="ReferralServiceListForRegister" id="service">
                                   <tr style="background-color: ${service.rowColor}">
                                       <td>${service.serialNo}</td>
                                       <td>${service.beneficiary.hhe.level2Ou.name}</td>
                                       <td>${service.beneficiary.hhe.level3Ou.name}</td>
                                       <td>${service.beneficiary.hhe.level4Ou.name}</td>
                                       <td>${service.beneficiary.hhe.cbo.cboName}</td>
                                       <td>${service.beneficiary.hhe.address}</td>
                                       
                                       <td>${service.beneficiary.dateOfEnrollment}</td>
                                       <td>${service.dateOfReferral}</td>
                                       <td>${service.beneficiary.hhUniqueId}</td>
                                       <td>${service.beneficiary.beneficiaryId}</td>
                                       <td>${service.beneficiary.firstName} ${service.beneficiary.surname}</td>
                                       <td>${service.beneficiary.beneficiaryTypeObject.name}</td>
                                       <td>${service.beneficiary.ageAtBaseline}</td>
                                       <td>${service.beneficiary.ageUnitAtBaseline}</td>
                                       <td>${service.beneficiary.currentAge}</td>
                                       <td>${service.beneficiary.currentAgeUnit}</td>
                                       <td>${service.beneficiary.sex}</td>
                                       
                                       <td>${service.beneficiary.currentHivStatusObject.name}</td>
                                       <td>${service.beneficiary.dateOfCurrentHivStatus}</td>
                                       <td>${service.beneficiary.enrolledOnTreatmentObject.code}</td>
                                       <td>${service.beneficiary.referralFacility.facilityName}</td>
                                       
                                       <td>${service.healthServiceNames}</td>
                                       <td>${service.safetyServiceNames}</td>
                                       <td>${service.schooledServiceNames}</td>
                                       <td>${service.stableServiceNames}</td>
                                       <td>${service.referralCompletedAnswer}</td>
                                       <td>${service.communityWorker.firstName} ${service.communityWorker.surname}</td>
                                       <td>${service.lastModifiedDate}</td>
                                       <td>${service.recordedBy}</td>
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
