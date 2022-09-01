<%-- 
    Document   : HIVRiskAssessmentRegister
    Created on : Feb 24, 2019, 9:31:32 AM
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
        <title>HIV Risk assessment register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="hraForRegister">
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
                                
                                <th >Does the caregiver know the HIV status of the child?</th>
                                <th >Has the child/adolescent been tested for HIV in the last three (3) months?</th>
                                <th >Is the child/adolescent's biological mother HIV positive?</th>
                                <th >Is the child/adolescent's biological mother having a long-standing sickness (frequent hospital visits/admissions, frequent use of medicines)?</th>
                                <th >Does the child/adolescent have a sibling that is HIV positive?</th>
                                <th >Does the child/adolescent have a sibling that is having a long-standing sickness (frequent hospital visits/admissions, frequent use of medicines)? </th>
                                <th >In the last three months, has this child/adolescent been sick (e.g. frequent hospital visits/admissions, frequent use of medicines)?</th>
                                <th >In the last three months, has this child/adolescent had more than 2 of following: Frequent Cough, longstanding Fever, long-standing Diarrhoea, Loss of weight//poor weight gain, longstanding/ frequent skin rash?</th>
                                <th >Has the child ever received blood transfusion?</th>
                                <th >Has the child Had any of the following in the last 3 months: circumcision, ear piercing, scarification, injection/drip outside the hospital?</th>
                                <th >Has the child/adolescent ever been sexually assaulted (any form)? </th>
                                <%--<th >Secondary caregiver </th>--%>
                                <th >Has the child/adolescent ever been pregnant (female children)?</th>
                                <%--<th >Is the adolescent child out of school?</th>                         
                                <th >Do you have a boyfriend/girlfriend/Sexual partner?</th>
                                
                                <th >Have you and your boyfriend/girlfriend/sexual partner engaged in any sexual activity before?</th>
                                <th >Do your private parts sometimes itch, burn, smell or look strange (genital discharge)?</th>
                                <th >Does the child abuse drugs or alcohol?</th>--%>
                                <th >Child at risk</th>
                                <th >Child referred for HIV test?</th>
                                <th >Last modified</th>
                                <th >Recorded by</th>
                </tr>
                               <logic:iterate name="hraForRegister" id="hra">
                                   <tr style="background-color: ${hra.rowColor}">
                                       <td>${hra.serialNo}</td>
                                       <td>${hra.ovc.hhe.level2Ou.name}</td>
                                       <td>${hra.ovc.hhe.level3Ou.name}</td>
                                       <td>${hra.ovc.hhe.level4Ou.name}</td>
                                       <td>${hra.ovc.hhe.cbo.cboName}</td>
                                       
                                       <td>${hra.ovc.hhe.address}</td>
                                       <td>${hra.ovc.hhUniqueId}</td>
                                       <td>${hra.ovcId}</td>
                                       <td>${hra.dateOfAssessment}</td>
                                       <td>${hra.ovc.firstName} ${hra.ovc.surname}</td>
                                       <td>${hra.ovc.currentAge}</td>
                                       <td>${hra.ovc.sex}</td>
                                       
                                       <td>${hra.hivStatusAnswer.name}</td>
                                       <td>${hra.childTestedAnswer.name}</td>
                                       <td>${hra.hivParentAnswer.name}</td>
                                       <td>${hra.motherSicknessAnswer.name}</td>
                                       <td>${hra.hivSibblingAnswer.name}</td>
                                       <td>${hra.sibblingSicknessAnswer.name}</td>
                                       <td>${hra.childSickAnswer.name}</td>
                                       
                                       <td>${hra.childHasMoreThanTwoIllnessAnswer.name}</td>
                                       <td>${hra.bloodTransfusionAnswer.name}</td>
                                       <td>${hra.childCircumcisedOrEarPiercedAnswer.name}</td>
                                       <td>${hra.sexualAbuseAnswer.name}</td>
                                       <%--<td>${hra.hhe.secCaregiver.fullName}</td>--%>
                                       <td>${hra.childEverPregnantAnswer.name}</td>
                                       <%--<td>${hra.outOfSchoolAnswer.name}</td>
                                       <td>${hra.boyFriendGirlFriendAnswer.name}</td>
                                       
                                       <td>${hra.sexualActivityAnswer.name}</td>
                                       <td>${hra.genitalDischargeAnswer.name}</td>
                                       <td>${hra.drugsOrAlcoholAnswer.name}</td>--%>
                                       <td>${hra.childAtRiskAnswer.name}</td>
                                       <td>${hra.childReferredForHIVTestAnswer.name}</td>
                                       <td>${hra.lastModifiedDate}</td>
                                       <td>${hra.recordedBy}</td>
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
