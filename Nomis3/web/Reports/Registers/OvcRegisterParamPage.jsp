<%-- 
    Document   : EnrollmentRegisterParamPage
    Created on : May 31, 2020, 1:20:08 PM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
        <link href="css/general/stylefile.css" rel="stylesheet" type="text/css" />
        <link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="js/Enrollmentjsfile.js"></script>
        <link href="images/kidmap2.css" rel="stylesheet" type="text/css" />
        <title>Enrollment register</title>
        <style type="text/css">
<!--
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #333333;
	font-weight: bold;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #000000;
}
a:hover {
	text-decoration: underline;
	color: #0075B6;
}
a:active {
	text-decoration: none;
	color: #000000;
}
.title
{
  color: #FFFFFF;
  font-weight: bold;
}
.orglabel
{
    color: #FFFFFF;
}

-->
</style>
        <script language="javascript">
            $(function() {
    $("#startDate").datepicker();
     $("#endDate").datepicker();
        //$("#dateOfBirth").datepicker();
    });
            function setActionName(val)
            {
                //alert(val)
                document.getElementById("actionName").value=val
            }

        </script>
    </head>
    <body style="background-color: #F9FBFD">
        <br/><br/>
        <center>
            <html:form action="/ovcregister" method="POST">
                <html:hidden property="actionName" styleId="actionName"/>
                <table>
                    <tr><td align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                    
                      <tr><td>
                        <table>
                            <tr>
                                <td colspan="3">
                <jsp:include page="/includes/ReportOrganizationUnitParameter.jsp" />
                </td></tr>
                            <tr><td ><label>Register</label></td>
                                <td colspan="2">
                                    <html:select property="registerType" styleId="registerType" style="width: 568px;">
                                <html:option value="HouseholdRegister">Household register</html:option>
                                <html:option value="caregiverRegister">Caregiver register</html:option>
                                <html:option value="childRegister">Child register</html:option>
                                <html:option value="ovcServiceRegister">Child Service register</html:option>
                                <html:option value="householdServiceRegister">Caregiver Service register</html:option>
                                <html:option value="hivRiskAssessmentRegister">HIV Risk assessment register</html:option>
                                <html:option value="careAndSupportRegister">Care and Support register</html:option>
                                <html:option value="careAndSupportExcelTemplate">Care and Support Excel report</html:option>
                                <html:option value="nutritionAssessment">Nutrition assessment register</html:option>
                                <html:option value="caregiverAccessToEmergencyFund">Caregiver access to emergency fund register</html:option>
                                <html:option value="careplanAchievementChecklist">Careplan achievement register</html:option>
                                <html:option value="householdCarePlanRegister">Household care plan register</html:option>
                                <html:option value="referralRegister">Referral register</html:option>
                                <html:option value="educationalAssessmentRegister">Child educational assessment register</html:option>
                                <html:option value="monthlySummaryForm">Monthly Summary form</html:option>
                                <html:option value="facilityOvcOfferRegister">Facility OVC Offer register</html:option>
                                <%--<html:option value="gbvServiceRegister">GBV Service register</html:option>
                                
                                <html:option value="graduationBenchmark">Graduation Benchmark register</html:option>
                                <html:option value="trainingRegister">Training register</html:option>--%>
                            </html:select>
                                </td>
                        </tr>
                            <tr><td colspan="3" align="center">
                                    <html:submit value="Generate report" onclick="setActionName('register')" style="width: 150px; margin-left: 50px;" disabled="${regSaveDisabled}" />
                                </td>
                            </tr>
                        </table>
                    </td></tr>
              </table>
            </html:form>
        </center>
    </body>
</html>
