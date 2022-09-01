<%-- 
    Document   : EducationalPerformanceAssessmentRegister
    Created on : Jun 17, 2021, 11:35:48 PM
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
        <title>Educational performance assessment register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="cepaListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >State </th>
                                <th >LGA </th>
                                <th >Ward </th>
                                <th >CBO </th>
                                
                                <th >Address </th>
                                <th>HH Unique Id</th>
                                <th>OVC Id</th>
                                <th >Date of assessment</th>
                                <th width="200">Child name </th>
                                <th width="200">Current age </th>
                                <th >Sex(M/F)</th>
                                
                                <th >Child has injuries or marks that cannot be explained by the child</th>
                                <th >Child is socially withdrawn among peers</th>
                                <th >Child shows signs of fatigue and constant tiredness</th>
                                <th >Child attends school regularly- not missing more than 5 days in the last one month- 4 weeks</th>
                                <th >Child shows steady improvement in his/her class work</th>
                                <th >Child resumes early at school</th>
                                <th >Child performed very well in the last Examination</th>
                                <th >Child progressed in school to a higher class in the new school year</th>
                                <th >Did child/children miss vocational training for more than 5 days in the last one month (4 weeks) of full vocational training session?</th>
                                <th >Child resumes early at training center</th>
                                <th >Child shows steady improvement in his/her work </th>
                                
                                <th >Class Teacher comment</th>
                                <th >Class Teacher name</th>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="cepaListForRegister" id="cepa">
                                   <tr style="background-color: ${cepa.rowColor}">
                                       <td>${cepa.serialNo}</td>
                                       <td>${cepa.ovc.hhe.level2Ou.name}</td>
                                       <td>${cepa.ovc.hhe.level3Ou.name}</td>
                                       <td>${cepa.ovc.hhe.level4Ou.name}</td>
                                       <td>${cepa.ovc.hhe.cbo.cboName}</td>
                                       
                                       <td>${cepa.ovc.hhe.address}</td>
                                       <td>${cepa.ovc.hhUniqueId}</td>
                                       <td>${cepa.ovcId}</td>
                                       <td>${cepa.dateOfAssessment}</td>
                                       <td>${cepa.ovc.firstName} ${cepa.ovc.surname}</td>
                                       <td>${cepa.ovc.currentAge}</td>
                                       <td>${cepa.ovc.sex}</td>
                                       
                                       <td>${cepa.childHasInjuriesOrMarksAnswer}</td>
                                       <td>${cepa.childIsSociallyWithdrawnAnswer}</td>
                                       <td>${cepa.signsOfFatigueAndTirednessAnswer}</td>
                                       <td>${cepa.regularSchoolAttendanceAnswer}</td>
                                       <td>${cepa.steadyImprovementInClassWorkAnswer}</td>
                                       <td>${cepa.earlyResumptionInSchoolAnswer}</td>
                                       <td>${cepa.goodPerformanceInLastExamAnswer}</td>
                                       <td>${cepa.childProgressedInSchoolAnswer}</td>
                                       <td>${cepa.childMissVocTrainingAnswer}</td>
                                       <td>${cepa.earlyResumptionInTrainingCenterAnswer}</td>
                                       <td>${cepa.steadyImprovementInVocWorkAnswer}</td>
                                       <td>${cepa.classTeacherComment}</td>
                                       <td>${cepa.classTeacher.fullName}</td>
                                       <td>${cepa.lastModifiedDate}</td>
                                       <td>${cepa.recordedBy}</td>
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
