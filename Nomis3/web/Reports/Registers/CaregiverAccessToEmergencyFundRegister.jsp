<%-- 
    Document   : CaregiverAccessToEmergencyFundRegister
    Created on : Oct 11, 2020, 8:35:03 PM
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
        <title>Caregiver access to emergency fund register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=householdServiceRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="caregiverAccessToEmergencyFundListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >State </th>
                                <th >LGA </th>
                                <th >Ward </th>
                                <th >CBO </th>
                                <th>HH Unique Id</th>
                                <th>Beneficiary Id</th>
                                <th width="100">Beneficiary name </th>
                                <th >Address </th>
                                <th >Sex(M/F)</th>
                                <th >Date of assessment (mm/dd/yyyy)</th>
                                <th >Did you make any unexpected expenditure in the past six (6) months?</th>
                                <th >Were you able to access money to pay for this unexpected expenditure?</th>
                                <th >How were you able to raise the money?</th>
                                <th >What are the household (HH) needs requiring routine and/or emergency cash to address?</th>
                                <th >Name of assessor</th>
                                <th >Last modified date</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="caregiverAccessToEmergencyFundListForRegister" id="caef">
                                   <tr style="background-color: ${service.rowColor}">
                                       <td>${caef.serialNo}</td>
                                       <td>${caef.adultHouseholdMember.hhe.level2Ou.name}</td>
                                       <td>${caef.adultHouseholdMember.hhe.level3Ou.name}</td>
                                       <td>${caef.adultHouseholdMember.hhe.level4Ou.name}</td>
                                       <td>${caef.adultHouseholdMember.hhe.cbo.cboName}</td>
                                       <td>${caef.adultHouseholdMember.hhUniqueId}</td>
                                       <td>${caef.beneficiaryId}</td>
                                       <td>${caef.adultHouseholdMember.firstName} ${caef.adultHouseholdMember.surname}</td>
                                       <td>${caef.adultHouseholdMember.hhe.address}</td>
                                       <td>${caef.adultHouseholdMember.sex}</td>
                                       <td>${caef.dateOfAssessment}</td>
                                       <td>${caef.unexpectedExpenditure}</td>
                                       <td>${caef.accessMoneyToPay}</td>
                                       <td>${caef.sourceOfMoney}</td>
                                       <td>${caef.urgentHhNeeds}</td>
                                       <td>${caef.communityWorker.firstName} ${caef.communityWorker.surname}</td>
                                       <td>${caef.lastModifiedDate}</td>
                                       <td>${caef.recordedBy}</td>
                                       
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
