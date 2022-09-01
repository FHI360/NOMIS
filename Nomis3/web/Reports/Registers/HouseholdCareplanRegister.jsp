<%-- 
    Document   : HouseholdCareplanRegister
    Created on : May 8, 2021, 12:57:46 AM
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
        <title>Household care plan register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <%--<label><a href="exceldownload.do?rqparam=caregiverRegister">Download in excel</a> </label>--%>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="householdCareplanListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th>
                                <th >CBO </th>
                                <th >Date of enrollment (yyyy-mm-dd)</th>
                                <th>HH Unique Id</th>
                                <th>Beneficiary Id</th>
                                <th width="200">Beneficiary name </th>
                                <th width="200">Beneficiary type </th>
                                <th >Current age </th>
                                <th >Sex(M/F)</th>
                                <th >Address</th>
                                <th >Phone</th>
                                
                                
                                <th >Identified Health issues</th>
                                <th >Household health goals</th>
                                <th >Priority action for resolving health issues</th>
                                <th >Health services to be provided</th>
                                <th >Time frame</th>
                                <th >follow up status and date</th>
                                
                                <th >Identified Safe issues</th>
                                <th >Household Safe goals</th>
                                <th >Priority action for resolving Safe issues</th>
                                <th >Safe services to be provided</th>
                                <th >Time frame</th>
                                <th >follow up status and date</th>
                                
                                <th >Identified Schooled issues</th>
                                <th >Household Schooled goals</th>
                                <th >Priority action for resolving Schooled issues</th>
                                <th >Schooled services to be provided</th>
                                <th >Time frame</th>
                                <th >follow up status and date</th>
                                
                                <th >Identified Stable issues</th>
                                <th >Household Stable goals</th>
                                <th >Priority action for resolving Stable issues</th>
                                <th >Stable services to be provided</th>
                                <th >Time frame</th>
                                <th >follow up status and date</th>
                                
                                <th >Name of volunteer</th>
                                <th >Recorded by</th>
                                <th >Last modified date</th>
                                
                </tr>           <logic:iterate name="householdCareplanListForRegister" id="hcp">
                                   <tr style="background-color: ${prCaregiver.rowColor}">
                                       <td>${hcp.serialNo}</td>
                                       <td>${hcp.hhe.level2Ou.name}</td>
                                       <td>${hcp.hhe.level3Ou.name}</td>
                                       <td>${hcp.hhe.level4Ou.name}</td>
                                       <td>${hcp.hhe.cbo.cboName}</td>
                                       <td>${hcp.careplanDate}</td>
                                       <td>${hcp.hhe.hhUniqueId}</td>
                                       <td>${hcp.beneficiaryId}</td>
                                       <td>${hcp.beneficiary.firstName} ${hcp.beneficiary.surname} </td>
                                       <td>${hcp.beneficiary.beneficiaryTypeName} </td>
                                       <td>${hcp.beneficiary.currentAge}</td>
                                       <td>${hcp.beneficiary.sex}</td>
                                       <td>${hcp.hhe.address}</td>
                                       <td>${hcp.beneficiary.phoneNumber}</td>
                                       
                                       <td>${hcp.identifiedHealthIssues}</td>
                                       <td>${hcp.householdHealthGoals}</td>
                                       <td>${hcp.priorityHealthGoals}</td>
                                       <td>${hcp.healthServicesToBeProvided}</td>
                                       <td>${hcp.timeFrameForHealthServices}</td>
                                       <td>${hcp.followupForHealthServices}</td>
                                       
                                       <td>${hcp.identifiedSafetyIssues}</td>
                                       <td>${hcp.householdSafetyGoals}</td>
                                       <td>${hcp.prioritySafetyGoals}</td>
                                       <td>${hcp.safetyServicesToBeProvided}</td>
                                       <td>${hcp.timeFrameForSafetyServices}</td>
                                       <td>${hcp.followupForSafetyServices}</td>
                                       
                                       <td>${hcp.identifiedSchooledIssues}</td>
                                       <td>${hcp.householdSchooledGoals}</td>
                                       <td>${hcp.prioritySchooledGoals}</td>
                                       <td>${hcp.schooledServicesToBeProvided}</td>
                                       <td>${hcp.timeFrameForSchooledServices}</td>
                                       <td>${hcp.followupForSchooledServices}</td>
                                       
                                       <td>${hcp.identifiedStableIssues}</td>
                                       <td>${hcp.householdStableGoals}</td>
                                       <td>${hcp.priorityStableGoals}</td>
                                       <td>${hcp.stableServicesToBeProvided}</td>
                                       <td>${hcp.timeFrameForStableServices}</td>
                                       <td>${hcp.followupForStableServices}</td>
                                       
                                       <td>${hcp.communityWorker.fullName}</td>
                                       <td>${hcp.recordedBy}</td>
                                       <td>${hcp.lastModifiedDate}</td> 
                                    </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
