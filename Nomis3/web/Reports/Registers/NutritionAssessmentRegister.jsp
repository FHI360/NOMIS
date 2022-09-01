<%-- 
    Document   : NutritionAssessmentRegister
    Created on : Oct 10, 2020, 9:00:00 PM
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
        <title>Nutrition assessment register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <label><a href="exceldownload.do?rqparam=caregiverRegister">Download in excel</a> </label>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="nutritionAssessmentListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th> 
                                <th >CBO </th>
                                <th >Date of enrollment (yyyy-mm-dd)</th>
                                <th>HH Unique Id</th>
                                <th>OVC Id</th>
                                <th width="200">Child name </th>
                                <th>Age at assessment</th>
                                <th>Current age </th>
                                <th >Sex(M/F)</th>
                                <th >Date of assessment</th>
                                <th >Last Weight</th>
                                <th >Change in weight</th>
                                <th >Date of last weight</th>
                                <th >Oedema</th>
                                <th >MUAC</th>
                                <th >BMI</th>
                                <th >Nutrition status</th>
                                <th >In the last 30 days, was there ever no food to eat in the household?</th>
                                <th >In the last 30 days did any household member go to sleep hungry because there wasn’t any food?</th>
                                <th >In the last 30 days, did any household member go a whole day and night without eating anything because there was not enough food?</th>
                                
                                <th >How many times did the child eat yesterday?</th>
                                <th >Yesterday, did the child eat any vitamin A rich foods (for example: mango, carrots, papaya, red palm oil, zogali, ugu, cassava, liver, or kidney)?</th>
                                <th >Yesterday, did the child eat any Iron-rich foods (for example: liver, kidney, beans, groundnut, or dark green leaves such as spinach, zogali, ugu, cassava)?</th>
                                <th >Yesterday, did the child eat any protein foods (for example: meat, eggs, fish, beans, groundnut, milk, cheese, soya, or Tom Brown)?</th>
                                <th >Did the child receive any food or liquid besides breast milk in the last 24 hours?</th>
                                <th >Is the mother experiencing any difficulties with breastfeeding?</th>
                                
                                <th >Does the household have soap and water to wash dishes and utensils?</th>
                                <th >Does the household have soap or ash for hand washing?</th>
                                <th >Do you normally wash your hands with soap/ash before cooking/eating?</th>
                                <th >Do you normally wash your hands with soap/ash after the toilet?</th>
                                
                                <th >Facility if MUAC is red</th>
                                <th >Services if MUAC is yellow</th>
                                <th >Last modified date</th>
                                <th >Name of assessor</th>
                                
                </tr>           <logic:iterate name="nutritionAssessmentListForRegister" id="na">
                                   <tr style="background-color: ${na.rowColor}">
                                       <td>${na.serialNo}</td>
                                       <td>${na.ovc.hhe.level2Ou.name}</td>
                                       <td>${na.ovc.hhe.level3Ou.name}</td>
                                       <td>${na.ovc.hhe.level4Ou.name}</td>
                                       <td>${na.ovc.hhe.cbo.cboName}</td>
                                       <td>${na.ovc.dateOfEnrollment}</td>
                                       <td>${na.ovc.hhe.hhUniqueId}</td>
                                       <td>${na.ovcId}</td>
                                       
                                       <td>${na.ovc.firstName} ${na.ovc.surname}</td>
                                       <td>${na.ageAtAssessment} ${na.ageUnitAtAssessment}</td>
                                       <td>${na.ovc.currentAge}</td>
                                       <td>${na.ovc.sex}</td>
                                       <td>${na.dateOfAssessment}</td>
                                       
                                       <td>${na.lastWeight}</td>
                                       <td>${na.changeInWeight}</td>
                                       <td>${na.dateOfLastWeight}</td>
                                       <td>${na.oedema}</td>
                                       <td>${na.muac}</td>
                                       <td>${na.bmi}</td>
                                       
                                       <td>${na.nutritionalStatusAnswer}</td>
                                       <td>${na.foodSecurityAndDietQ1Answer}</td>
                                       <td>${na.foodSecurityAndDietQ2Answer}</td>
                                       <td>${na.foodSecurityAndDietQ3Answer}</td>
                                       <td>${na.foodSecurityAndDietQ4Answer}</td>
                                       <td>${na.foodSecurityAndDietQ5Answer}</td>
                                       <td>${na.foodSecurityAndDietQ6Answer}</td>
                                       <td>${na.foodSecurityAndDietQ7Answer}</td>
                                       <td>${na.foodSecurityAndDietQ8Answer}</td>
                                       <td>${na.foodSecurityAndDietQ9Answer}</td>
                                       
                                       <td>${na.hygieneQ1Answer}</td>
                                       <td>${na.hygieneQ2Answer}</td>
                                       <td>${na.hygieneQ3Answer}</td>
                                       <td>${na.hygieneQ4Answer}</td>
                                       <td>${na.muacFacility}</td>
                                       <td>${na.yellowMuacServices}</td>
                                       <td>${na.lastModifiedDate}</td>
                                       <td>${na.communityWorker.firstName} ${na.communityWorker.surname}</td>
                                       
                                    </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
