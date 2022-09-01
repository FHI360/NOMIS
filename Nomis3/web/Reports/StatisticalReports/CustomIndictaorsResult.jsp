<%-- 
    Document   : CustomIndictaorsResult
    Created on : Aug 11, 2020, 8:56:34 PM
    Author     : smomoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">-->
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>OVC Custom indicators report</title>
<style>
.verticaltext
{
writing-mode: tb-rl;
filter: flipV flipH;
}
.fcell
{
	border-right: solid black 2px;
        width: 50px;
}
.tdLine
{
	border-right: solid black 3px;
}
tr
{
	height:30px;
}
td{
	padding-left: 5px;
	padding-right: 5px;
	border-left: 2px solid black;
	border-right: dashed black 2px;
	border-bottom: 1px solid black;
	font-size: 14px;
	color: black;
        /*width: 30px;*/
}
th {
	padding-left: 5px;
	padding-right: 5px;
	/*border-left: 1px solid black;
	border-right: dashed black 2px;
	border-bottom: 1px solid black;*/
	font-size: 15px;
	color: black;
        width: 30px;
}
table {
	border-collapse: collapse;
	margin: 10px;
}
.borderdraw
{
    border-style:solid;
    height:0;
    line-height:0;
    width:0;
}
.orglabel
{
    border:none;
    font-family: 'Courier New', Courier, monospace;
    font-size:12pt;
}
</style>

</head>
<body style="background-color:#2A3F55" >
<center>
    <table border="0" width="90%" style="border:none; background-color:#FFFFFF">
        <tr><td style="border:none;" >
<table width="90%"  border="0" style="border:none;">
  <tr>
      <td style="border:none;"><img src="images/CoatOfArm.jpg"  /></td>
      <td style="border:none; font-family:'Courier New', Courier, monospace; font-size:14pt" align="center" colspan="2">
          <label style="margin-left:100px"><b> OVC CUSTOM INDICATORS REPORT </b></label>
      </td><td colspan="5"> </td>
  </tr>
  
  <tr>
      <th style="border:none;" colspan="8" align="center">
          <jsp:include page="/includes/ReportHeading.jsp" />
      </th>
  </tr>
 
  </table>
            </td></tr>
        <tr><td style="border:none;" align="center">
<table border="0" cellpadding="0" cellspacing="0" style="border:1px black solid; margin-bottom:70px">
      <tr style=" background-color: lightgrey">
    <td width="10%" rowspan="3" class="tdLine" style="font-size:16px; width: 170px;"><div align="center">Community VC Services Data Elements</div></td>
    <td width="4%" colspan="10" class="tdLine" style="font-size:16px"><div align="center">Male</div></td>
    <td width="2%" colspan="10" class="tdLine" style="font-size:16px"><div align="center">Female</div></td>
    <td width="2%" rowspan="3" class="tdLine" style="font-size:16px"><div align="center">Total</div></td>
  </tr>
  <tr style=" background-color: lightgrey">
    <td width="1%" rowspan="2"><div align="center"> &lt;1</div></td>
    <td width="1%" rowspan="2"><div align="center">1-4</div></td>
    <td width="2%" rowspan="2" class="tdLine"><div align="center">5-9</div></td>
    <td width="2%" rowspan="2" class="tdLine"><div align="center">10-14</div></td>
    <td width="2%" rowspan="2" class="tdLine"><div align="center">15-17</div></td>
    <td width="2%" rowspan="2" class="tdLine"><div align="center">18+ children</div></td>
    <td width="2%" rowspan="2" class="tdLine"><div align="center">18-19 adult</div></td>
    <td width="2%" rowspan="2" class="tdLine"><div align="center">20-24</div></td>
    <td width="2%" rowspan="2" class="tdLine"><div align="center">25+</div></td>
    <td width="2%" rowspan="2" class="tdLine"><div align="center">Male total</div></td>
    
  </tr>
  <tr style=" background-color: lightgrey">
    <td width="1%" ><div align="center"> &lt;1</div></td>
    <td width="1%" ><div align="center">1-4</div></td>
    <td width="2%" class="tdLine"><div align="center">5-9</div></td>
    <td width="2%" class="tdLine"><div align="center">10-14</div></td>
    <td width="2%" class="tdLine"><div align="center">15-17</div></td>
    <td width="2%" class="tdLine"><div align="center">18+ children</div></td>
    <td width="2%" class="tdLine"><div align="center">18-19 adult</div></td>
    <td width="2%" class="tdLine"><div align="center">20-24</div></td>
    <td width="2%" class="tdLine"><div align="center">25+</div></td>
    <td width="2%" class="tdLine"><div align="center">Female Total</div></td>
  </tr>
  <logic:present name="mthlySummaryReportTemplateList">
      <logic:iterate name="mthlySummaryReportTemplateList" id="rt">
      <tr ><%--<a href="indicatorreferenceaction.do?id=${rt.mainDataElementId}" target="_blank"> --%>
              <td width="15%" style=" background-color: azure" ><div>${rt.mainDataElementName}</div></td>
        <td width="1%" ><div align="center"> ${rt.ovc_servMaleLessThan1}</div></td>
        <td width="1%" ><div align="center">${rt.ovc_servMale1To4}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servMale5To9}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servMale10To14}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servMale15To17}</div></td>
        
        <td width="2%" class="tdLine"><div align="center">${rt.childrenMale18AndAbove} </div></td>
        
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servMale18To19}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servMale20To24}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servMale25AndAbove}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.maleTotal}</div></td>
        <td width="1%" ><div align="center"> ${rt.ovc_servFemaleLessThan1}</div></td>
        <td width="1%" ><div align="center">${rt.ovc_servFemale1To4}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servFemale5To9}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servFemale10To14}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servFemale15To17}</div></td>
        
        <td width="2%" class="tdLine"><div align="center">${rt.childrenFemale18AndAbove} </div></td> 
        
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servFemale18To19}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servFemale20To24}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.ovc_servFemale25AndAbove}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.femaleTotal}</div></td>
        <td width="2%" class="tdLine"><div align="center">${rt.grandTotal}</div></td>
      </tr>
    </logic:iterate>
</logic:present>
</table>
            </td></tr>
<tr><td style="border:none;"> </td></tr>
  <tr><td style="border:none;" align="center"> Completed by: Name_______________________ Designation:________________Sign/Date:________________</td></tr>
        <tr><td style="border:none;"> </td></tr>
    </table>
</center>
</body>
</html>

