<%-- 
    Document   : CareAndSupportRegister
    Created on : Jul 5, 2020, 8:10:38 PM
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
        <title>Care and Support register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=householdServiceRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="careAndSupportListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >State </th>
                                <th >LGA </th>
                                <th >Ward </th>
                                <th >CBO </th>
                                <th>HH Unique Id</th>
                                <th>Beneficiary Id</th>
                                <th width="200">Beneficiary name </th>
                                <th >Address </th>
                                <th >Sex(M/F)</th>
                                <th >Age</th>
                                <th >Date of assessment (mm/dd/yyyy)</th>
                                <th >Has the child been coughing for up to two weeks or more?</th>
                                <th >Has the child been losing weight recently or is not growing properly?</th>
                                <th >Has the child been having fever for a long period of time?</th>
                                <th >Has the child been having night sweats?</th>
                                <th >Does the child have swellings on the neck?</th>
                                <th >Is the beneficiary currently on ART?</th>
                                <th >HIV Treatment facility</th>
                                <th >Has the beneficiary picked up his/her medication?</th>
                                <th >Date of last drug pickup</th>
                                <th >Number of days of refill</th>
                                <th >Has the beneficiary missed his/her ARVs more than two doses in a month in the last 3 months?</th>
                                <th>Reasons people skip ARVs</th>
                                <th>Has beneficiary carried out viral load test in the last one year?</th>
                                <th>Date of sample collection</th>
                                <th>Do you know the viral load test result?</th>
                                <th>What was the result?</th>
                                <th>Why was the viral load not done?</th>
                                <th>Has beneficiary received transportation support to access ARVs in the last six months?</th>
                                <th>How many months?</th>
                                <th>Has beneficiary experienced sores/rash/pain/discharge/bleeding from the vagina or penis in the last six months?</th>
                                <th>Next clinical appointment date</th>
                                <th >Name of volunteer</th>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="careAndSupportListForRegister" id="cas">
                                   <tr style="background-color: ${service.rowColor}">
                                       <td>${cas.serialNo}</td>
                                       <td>${cas.beneficiary.hhe.level2Ou.name}</td>
                                       <td>${cas.beneficiary.hhe.level3Ou.name}</td>
                                       <td>${cas.beneficiary.hhe.level4Ou.name}</td>
                                       <td>${cas.beneficiary.hhe.cbo.cboName}</td>
                                       <td>${cas.beneficiary.hhUniqueId}</td>
                                       <td>${cas.beneficiaryId}</td>
                                       <td>${cas.beneficiary.firstName} ${cas.beneficiary.surname}</td>
                                       <td>${cas.beneficiary.hhe.address}</td>
                                       <td>${cas.beneficiary.sex}</td>
                                       <td>${cas.beneficiary.currentAge}</td>
                                       <td>${cas.dateOfAssessment}</td>
                                       <td>${cas.coughSymptomOptionName}</td>
                                       <td>${cas.childLossinWeightOptionName}</td>
                                       <td>${cas.childHasFeverOptionName}</td>
                                       <td>${cas.childHasNightSweatOptionName}</td>
                                       <td>${cas.childHasSwellingOptionName}</td>
                                       <td>${cas.enrolledOnTreatmentOptionName}</td>
                                       <td>${cas.facilityName}</td>
                                       <td>${cas.pickedUpMedicationOptionName}</td>
                                       
                                       <td>${cas.dateOfLastDrugPickup}</td>
                                       <td>${cas.numberOfDaysOfRefill}</td>
                                       
                                       <td>${cas.missedARVsRecentlyOptionName}</td>
                                       <td>${cas.reasonsPeopleSkipARV}</td>
                                       <td>${cas.viralLoadTestDoneOptionName}</td>
                                       <td>${cas.dateOfViralLoadSampleCollection}</td>
                                       <td>${cas.viralLoadResultKnownOptionName}</td>
                                       <td>${cas.viralLoadResult}</td>
                                       <td>${cas.reasonViralLoadNotDone}</td>
                                       <td>${cas.receivedTransportationSupportOptionName}</td>
                                       <td>${cas.monthsOfTransportationSupportOptionName}</td>
                                       <td>${cas.soresRashPainExperienceOptionName}</td>
                                       <td>${cas.dateOfNextAppointment}</td>
                                       <td>${cas.volunteerName}</td>
                                       <td>${cas.lastModifiedDate}</td>
                                       <td>${cas.recordedBy}</td>
                                       
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
