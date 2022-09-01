<%-- 
    Document   : DatimReportParamPage
    Created on : Apr 13, 2020, 7:25:06 PM
    Author     : smomoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DATIM Report</title>
        <link href="css/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
        <link href="css/general/stylefile.css" rel="stylesheet" type="text/css" />
        <link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="js/Enrollmentjsfile.js"></script>
        <link href="images/kidmap2.css" rel="stylesheet" type="text/css" />
        <style type="text/css">

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
.selectBoxtWidth
{
    width: 160px;
}


</style>
        <script language="javascript">
$(function() {
    $("#startDate").datepicker();
     $("#endDate").datepicker();
    });
function enableDateFields()
{
    if(document.getElementById("chkReportType").checked==true)
    {
        enableComponents("sumstat_month")
        enableComponents("sumstat_year")
        document.getElementById("reportType").value="on"
    }
    else
    {
        disableComponents("sumstat_month")
        disableComponents("sumstat_year")
        document.getElementById("reportType").value="off"
    }
}
function enableComponents(id)
{
    document.getElementById(id).disabled=false
}
function disableComponents(id)
{
    document.getElementById(id).disabled=true
}
function setActionName(val)
{
    document.getElementById("actionName").value=val
}
        </script>
    </head>
    <body style="background-color: #f1f1f1">
        <br/><br/>
        <center>
            <html:form action="/datimreport" method="post">
                <html:hidden property="actionName" styleId="actionName"/>
                                
        <span>
        <center>
            <table><tr><td> 
            <html:errors/>
            <table style="margin-left: 343px">
                <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                <tr><td colspan="4" align="center" >DATIM report</td></tr>
                <tr><td colspan="4" class="title" align="center"> <logic:present name="datimpdfmsg">
                        ${datimpdfmsg}
                    </logic:present></td></tr>
            </table>
        <jsp:include page="/includes/DatimReportOrganizationUnitParameter.jsp" />
               
<table>
                          
            <tr><td align="right">Type</td>
                <td colspan="3">
                    <html:select property="reportType" styleId="reportType" style="width: 290px; margin-left:10px;" >
                        <html:option value="datim2017">Datim report form</html:option>
                        <%--<html:option value="writedatim2017ToPDF">Write Datim report to PDF</html:option>--%>
                        <html:option value="datimExcelDownload">Download Datim report in Excel</html:option>
                        <html:option value="datimExcelFlatFileDownload">Download Datim flat file in Excel</html:option>
                    </html:select> 
                </td>
            </tr>
           
 <tr><td colspan="4" align="center"><html:submit property="reportBtn" value="Generate report" onclick="setActionName('report')" style="width: 120px;" disabled="${datimSaveDisabled}"/>
    
        </table>
                
             </td></tr></table>   
        </center>
        </span>
   <!-- </div>-->

            </html:form>
        </center>
        
    </body>
</html>

