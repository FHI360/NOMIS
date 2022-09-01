<%-- 
    Document   : CareplanAchievementRegister
    Created on : Oct 11, 2020, 10:23:23 PM
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
        <title>Care plan achievement register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <%--<label><a href="exceldownload.do?rqparam=householdServiceRegister">Download in excel</a> </label>--%>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="careplanAchievementListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >State </th>
                                <th >LGA </th>
                                <th >Ward </th>
                                <th >CBO </th>
                                <th>HH Unique Id</th>
                                
                                <th width="100">Beneficiary name </th>
                                <th >Address </th>
                                <th >Sex(M/F)</th>
                                <th >Date of assessment (mm/dd/yyyy)</th>
                                <th >All children, adolescents, and caregivers in the household have known HIV status
                                    or a test is not required based on risk assessment</th>
                                <th >All HIV+ children, adolescents and caregiver in the household are linked to and have adhered to treatment for 12 months after initiation of ART?</th>
                                <th >All HIV+ children, adolescents and caregivers in the household have a viral load result documented in the last 12 months</th>
                                <th >All adolescents 10-17 years of age in the household have key knowledge about preventing HIV infection</th>
                                <th >No children < 5 years in the household are undernourished </th>
                                <th >All children in the household have birth certificates</th>
                                <th >There is a stable adult in the household who provides consistent care, attention and support to the children and adolescents</th>
                                <th >Children, adolescent or caregivers in the household reported experience(s) of violence in the last Six months </th>
                                <th >All children aged 6 years and above are enrolled in school </th>
                                <th >All children and adolescents enrolled in school have attended regularly and progressed in the last year </th>
                                <th >Caregiver is engaged in economic activities that helps meet the critical needs of the children in the household </th>
                                <th >Caregiver can identify an individual or group recognized as providing social and emotional support </th>
                                <th >Score</th>
                                <th >Baseline assessment score</th>
                                <th >Name of assessor</th>
                                <th >Last modified date</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="careplanAchievementListForRegister" id="cpa">
                                   <tr style="background-color: ${service.rowColor}">
                                       <td>${cpa.serialNo}</td>
                                       <td>${cpa.hhe.level2Ou.name}</td>
                                       <td>${cpa.hhe.level3Ou.name}</td>
                                       <td>${cpa.hhe.level4Ou.name}</td>
                                       <td>${cpa.hhe.cbo.cboName}</td>
                                       <td>${cpa.hhUniqueId}</td>
                                       
                                       <td>${cpa.hhe.prCaregiver.firstName} ${cpa.hhe.prCaregiver.surname}</td>
                                       <td>${cpa.hhe.address}</td>
                                       <td>${cpa.hhe.prCaregiver.sex}</td>
                                       <td>${cpa.dateOfAssessment}</td>
                                       
                                       <td>${cpa.childrenHivStatusknown}</td>
                                       <td>${cpa.hivPosAdolscentsLinked}</td>
                                       <td>${cpa.documentedViralLoadResult}</td>
                                       <td>${cpa.hivPreventionKnowledge}</td>
                                       
                                       <td>${cpa.childrenNotUndernourished}</td>
                                       <td>${cpa.allChildrenHaveBirthCert}</td>
                                       <td>${cpa.stableAdultInHousehold}</td>
                                       <td>${cpa.violenceIncidenceReport}</td>
                                       <td>${cpa.childrenEnrolledInSchool}</td>
                                       <td>${cpa.regularSchoolAttendance}</td>
                                       <td>${cpa.cgiversEconomicActivity}</td>
                                       <td>${cpa.emotionalSupportTeamIdentification}</td>
                                       <td>${cpa.score}</td>
                                       <td>${cpa.hhe.rhva.vulnerabilityScore}</td>
                                       <td>${cpa.communityWorker.firstName} ${cpa.communityWorker.surname}</td>
                                       <td>${cpa.lastModifiedDate}</td>
                                       <td>${cpa.recordedBy}</td>
                                       
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
