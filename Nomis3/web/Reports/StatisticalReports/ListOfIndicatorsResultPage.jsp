<%-- 
    Document   : ListOfIndicatorsResultPage
    Created on : Nov 4, 2018, 7:50:30 AM
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
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>List of indicators</title>
        <link href="kidmap.css" rel="stylesheet" type="text/css" />

        <style type="text/css">
            <!--
            #Layer1 {
                position:absolute;
                width:200px;
                height:115px;
                z-index:1;
            }
            -->
        </style>
        <style>
.verticaltext
{
	writing-mode: tb-rl;
	filter: flipV flipH;
	border-right: solid black 1px;
}
.tdLine
{
	border-right: solid black 2px;
}
tr
{
	height:20px;
}
td {
	padding-left: 11px;
	padding-right: 11px;
	border-left: 1px solid black;
	border-bottom: 1px solid black;
	font-size: 11px;
	color: black;
}
th {
	padding-left: 11px;
	padding-right: 11px;
	/*border-left: 1px solid black;
	border-bottom: 1px solid black;
        border-top: 1px solid black;*/
	font-size: 11px;
	color: black;
}
table {
	border-collapse: collapse;
	margin: 10px;
}
.lab
{
    font-family:  arial, sans-serif;
    font-size: 14px;
    /*font-weight: bold;*/
    letter-spacing: 3px;
}
.orgNames
{
    font-family:  arial, sans-serif;
    font-size: 12px;
    border: none; text-align: left; width: 40px;
}
.orgNamestd
{
    border: none; text-align: left; width: 40px;
}
</style>
    </head>

        <body >

            <html:errors/>


            <center>
                
            <div align="center" style=" width: 800px; height: 1000px; background-color: white;"><br/>
                <a href="listofindicators.do">Back</a>
                <label class="lab">List of indicators</label><br/>

                <table border=0 style=" width: 800px;">
                    
                    <tr>
                        <td align="left" border="0" style=" border: none;">
                            <jsp:include page="/includes/ReportHeading.jsp" />
                        </td>
                        </tr>
                        <tr>
                        <td align="center" border="0" style=" border: none;">
                            <logic:present name="ageLabel">
                                Age: ${ageLabel}
                            </logic:present>
                        </td>
                        </tr>
                        <tr>
                            <td style=" border: none;">
                        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid; margin-bottom:40px">
                            <tr align="left" bgcolor="#D7E5F2">
                                <th>SNo</th>
                                <th>Indicator Name</th>
                                <th >Male</th>
                                <th >Female</th>
                                <th >Total</th>
                              </tr>

                            <logic:present name="listOfIndicatorsResult">
                             <logic:iterate name="listOfIndicatorsResult" id="rt">
                                <tr align="left" bgcolor="${rt.rowColor}">
                                    <td width="5%">${rt.serialNo}</td>
                                    <td width="59%">
                                        ${rt.indicatorName}
                                    </td>
                                    <%--<td width="7%">
                                        ${rt.maleTotal}
                                    </td>
                                    <td width="7%">
                                        ${rt.femaleTotal}
                                    </td>
                                    <td width="7%">
                                        ${rt.grandTotal} 
                                    </td>--%>
                                    <td width="7%">
                                        <a href="listofindicators.do?reqParam=${rt.indicatorId}:M:summstatList" target="_blank">${rt.maleTotal}</a>
                                    </td>
                                    <td width="7%">
                                        <a href="listofindicators.do?reqParam=${rt.indicatorId}:F:summstatList" target="_blank">${rt.femaleTotal}</a>
                                    </td>
                                    <td width="7%">
                                        <a href="listofindicators.do?reqParam=${rt.indicatorId}:BG:summstatList" target="_blank">${rt.grandTotal} </a>
                                    </td>
                                  </tr> 
                                </logic:iterate>
                            </logic:present>
                           <logic:present name="orgAssessmentList">
                           <logic:iterate name="orgAssessmentList" id="assessmentList">
                                <tr align="left" bgcolor="${rt.rowColor}">
                                    <td width="5%">${rt.serialNo}</td>
                                    <td width="65%" colspan="3">
                                        ${assessmentList[0]}
                                    </td>

                                    <td width="10%">
                                        <a href="summstatreport.do?reqParam=${assessmentList[0]}:${assessmentList[2]}:Both gender:summstatList" target="_blank">${assessmentList[1]} </a>
                                        
                                    </td>
                                    <td width="20%">
                                        <a href="summstatreport.do?reqParam=${assessmentList[0]}:${assessmentList[2]}:Both gender:dowloadInExcel" target="_blank">Download</a>
                                    </td>
                                </tr>
                                </logic:iterate>
                           </logic:present>
                            </table>
                            </td></tr>
                    </table>
            </div>

            </center>
        </body>
</html>

