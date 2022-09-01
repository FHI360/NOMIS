<%-- 
    Document   : MonthlySummaryForm
    Created on : Jun 3, 2021, 6:20:17 AM
    Author     : smomoh
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Monthly Summary form</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
        font-size: 12px;
	background-image: url(images/bg.jpg);
	background-repeat: repeat-x;
}
-->
</style>
<script type="text/javascript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<link href="images/untitled.css" rel="stylesheet" type="text/css" />
<link href="images/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: underline;
}
a:active {
	text-decoration: none;
}
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
	padding-left: 11px;
	padding-right: 11px;
	border-left: 2px solid black;
	border-right: dashed black 2px;
	border-bottom: 1px solid black;
	font-size: 14px;
	color: black;
        /*width: 30px;*/
}
th {
	padding-left: 11px;
	padding-right: 11px;
	border-left: 1px solid black;
	border-right: dashed black 2px;
	border-bottom: 1px solid black;
	font-size: 11px;
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
-->
</style>
<!--<link href="sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />-->
<link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>

<script language="javascript">
function generateUniqueId()
{
    return true;
}
function disableControl(id)
{
    alert("Inside disableControl "+id)
    document.getElementById(id).disabled=true;
}
function confirmAction(name)
{
     if(name=="save")
     {
            setActionName(name)
            return true
     }
     if(confirm("Are you sure you want to "+name+" this record?"))
     {
            setActionName(name)
            return true
     }
       return false
}
function setActionName(val)
{
    document.getElementById("actionName").value=val
}
</script>
<link href="kidmap-default.css" rel="stylesheet" type="text/css" />
<link href="images/kidmap2.css" rel="stylesheet" type="text/css" />
<link href="images/untitled.css" rel="stylesheet" type="text/css" />
<link href="images/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
</head>

<body onload="MM_preloadImages('images/About_down.jpg','images/Admin_down.jpg','images/Rapid_down.jpg','images/care_down.jpg','images/OVC_down.jpg');">

<table width="949" border="0" align="center" cellpadding="0" cellspacing="0" class="boarder">
  <!--DWLayoutTable-->
  <tr>
    <td height="117" colspan="2" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <!--DWLayoutTable-->
      <tr>
        <td width="7" height="2"></td>
          <td width="271"></td>
          <td width="137"></td>
          <td width="95"></td>
          <td width="8"></td>
          <td width="95"></td>
          <td width="8"></td>
          <td width="95"></td>
          <td width="8"></td>
          <td width="95"></td>
          <td width="8"></td>
          <td width="95"></td>
          <td width="23"></td>
        </tr>

<%--<jsp:include page="/includes/Pagetabs.jsp" />--%>

      <tr>
        <td height="30"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>&nbsp;</td>
        <td></td>
        <td></td>
        <td></td>
        </tr>

      <tr>
        <td height="17"></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><jsp:include page="/Navigation/Logout.jsp" /></td>
          <td></td>
        </tr>
      <tr>
        <td height="3" colspan="13" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
          <!--DWLayoutTable-->
          <tr>
            <td width="945" height="2"></td>
            </tr>
          <!--DWLayoutTable-->
          <tr>
            <td height="1"></td>
              </tr>
        </table></td>
        </tr>

    </table></td>
  </tr>
  
  
  
  <tr>
    <td width="931" height="251" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <!--DWLayoutTable-->
      <tr>
        
        
      <td width="659" class="regsitertable" colspan="3">
          <!--enrollmentstatusmanagement-->
        <%--<html:form action="/monthlysummaryform" method="POST">
                <html:hidden property="actionName" styleId="actionName"/>--%>
                <table>
                    <tr><td align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                    
                      <tr><td>
                        <%--<table>
                            <tr><td colspan="3">
                <jsp:include page="/includes/OrganizationUnitHeader.jsp" />
                </td></tr>
                            <tr><td ><label>Register</label></td>
                                <td colspan="2">
                                    
                                </td>
                        </tr>
                            <tr><td colspan="3" align="center">
                                    <html:submit value="Load records" onclick="setActionName('loadRecords')" style="width: 160px; margin-left: 120px;" />
                                    <html:submit value="Merge selected records" onclick="setActionName('mergeRecords')" style="width: 160px; margin-left: 150px;" disabled="hhmergebtnDisabled"/>
                                </td>
                            </tr>
                        </table>--%>
                                
                    
                    </td></tr>
                      
                      
                      
                      
                      <tr><td style="border:none;" align="center">
<table border="0" cellpadding="0" cellspacing="0" style="border:1px black solid; margin-bottom:70px">
    <logic:present name="nationalMthlySummaryList">
        <logic:iterate id="countOfStates" name="nationalMthlySummaryList">
        <tr><td class="tdLine" colspan="9" >${countOfStates[0]} </td><td>${countOfStates[1]} </td></tr>
        <tr><td class="tdLine" colspan="9" >${countOfStates[2]} </td><td>${countOfStates[3]} </td></tr>
        <tr><td class="tdLine" colspan="9" >${countOfStates[4]} </td><td>${countOfStates[5]} </td></tr>
        </logic:iterate>
    </logic:present>
        <logic:present name="stateMthlySummaryList">
    <logic:iterate id="countOfLGA" name="stateMthlySummaryList">
        <tr><td class="tdLine" colspan="9" align="center" ><b>State level Data element</b> </td><td><b>Total</b> </td></tr>
    <tr><td class="tdLine" colspan="9" >${countOfLGA[0]} </td><td>${countOfLGA[1]} </td></tr>
    <tr><td class="tdLine" colspan="9" >${countOfLGA[2]} </td><td>${countOfLGA[3]} </td></tr>
    </logic:iterate>
</logic:present>
 <logic:present name="lgaMthlySummaryList">
    <logic:iterate id="countOfCSO" name="lgaMthlySummaryList">
        <tr><td class="tdLine" colspan="9" align="center" ><b>LGA level Data element</b> </td><td><b>Total</b> </td></tr>
    <tr><td class="tdLine" colspan="9" >${countOfCSO[0]} </td><td>${countOfCSO[1]} </td></tr>
    </logic:iterate>
 </logic:present>
  <tr >
    <th width="10%" rowspan="3" class="tdLine" style="font-size:16px; width: 170px;"><div align="center">Community VC Services Data Elements</div></th>
    <th width="4%" colspan="4" class="tdLine" style="font-size:16px"><div align="center">Male</div></th>
    <th width="2%" colspan="4" class="tdLine" style="font-size:16px"><div align="center">Female</div></th>
    <th width="2%" rowspan="3" class="tdLine" style="font-size:16px"><div align="center">Total</div></th>
  </tr>
  <tr >
    <th width="2%" rowspan="2"><div align="center">0-5 years</div></th>
    <th width="2%" rowspan="2"><div align="center">6-12 years</div></th>
    <th width="3%" rowspan="2" class="tdLine"><div align="center">13-17 years</div></th>
    <th width="2%" rowspan="2" class="tdLine"><div align="center">Male total</div></th>
    <!--<th width="3%" rowspan="2"><div align="center">Pregnant</div></th>-->
  </tr>
  <tr >
  	<th width="2%"><div align="center">0-5 years</div></th>
    <th width="2%"><div align="center">6-12 years</div></th>
    <th width="3%" class="tdLine"><div align="center">13-17 years</div></th>
    <th width="2%" class="tdLine"><div align="center">Female Total</div></th>
  </tr>

  <logic:present name="mthSummaryReportTemplateList">
      <logic:iterate name="mthSummaryReportTemplateList" id="rt" >
            <tr><td class="tdLine">${rt.indicatorName}</td><td >${rt.male0to5} </td><td>${rt.male6to12}</td><td>${rt.male13to17} </td><td class="tdLine">${rt.maleTotal}</td><td class="tdLine">${rt.female0to5} </td><td>${rt.female6to12} </td><td>${rt.female13to17} </td><td class="tdLine">${rt.femaleTotal} </td><td class="tdLine">${rt.grandTotal}</td> </tr>
       </logic:iterate>
       <%--<logic:iterate id="monthlySummCount" collection="${mthSummarySessionObj[0]}">
            <tr><td class="tdLine">${monthlySummCount[0]} </td><td>${monthlySummCount[1]}</td><td>${monthlySummCount[2]} </td><td class="tdLine">${monthlySummCount[3]}</td><td class="tdLine">${monthlySummCount[4]} </td><td>${monthlySummCount[5]} </td><td>${monthlySummCount[6]} </td><td class="tdLine">${monthlySummCount[7]} </td><td class="tdLine">${monthlySummCount[8]}</td><td>${monthlySummCount[9]}</td> </tr>
       </logic:iterate>
   <logic:iterate id="monthlySummCount" collection="${mthSummarySessionObj[1]}">
            <tr><td class="tdLine">${monthlySummCount[0]} </td><td style="background-color:#808080"> </td><td style="background-color:#808080"> </td><td class="tdLine" style="background-color:#808080"> </td><td class="tdLine">${monthlySummCount[2]} </td><td style="background-color:#808080"> </td><td style="background-color:#808080"> </td><td class="tdLine" style="background-color:#808080"> </td><td class="tdLine">${monthlySummCount[4]}</td><td>${monthlySummCount[2]+monthlySummCount[4]}</td> </tr>
   </logic:iterate>
   <logic:iterate id="monthlySummCount" collection="${mthSummarySessionObj[2]}">
            <tr><td class="tdLine">${monthlySummCount[0]} </td><td>${monthlySummCount[1]}</td><td>${monthlySummCount[2]} </td><td class="tdLine">${monthlySummCount[3]}</td><td class="tdLine">${monthlySummCount[4]} </td><td>${monthlySummCount[5]} </td><td>${monthlySummCount[6]} </td><td class="tdLine">${monthlySummCount[7]} </td><td class="tdLine">${monthlySummCount[8]}</td><td>${monthlySummCount[9]}</td> </tr>
       </logic:iterate>
   </logic:present>
  <logic:present name="econonomicStrenghtList">
      <logic:iterate id="econonomicStrenghtList" collection="${econStrengthList}">
          <tr><td class="tdLine">${econonomicStrenghtList[0]} </td><td>${econonomicStrenghtList[1]}</td><td>${econonomicStrenghtList[2]} </td><td class="tdLine">${econonomicStrenghtList[3]}</td><td class="tdLine">${econonomicStrenghtList[4]}</td><td>${econonomicStrenghtList[5]} </td><td>${econonomicStrenghtList[6]} </td><td class="tdLine">${econonomicStrenghtList[7]} </td><td class="tdLine">${econonomicStrenghtList[8]}</td><td>${econonomicStrenghtList[9]}</td> </tr>  
      </logic:iterate>--%>
  </logic:present>    
  
</table>
            </td></tr>
       <tr><td style="border:none;"> </td></tr>
  <tr><td style="border:none;" align="center"> Completed by: Name_______________________ Designation:________________Sign/Date:________________</td></tr>
        <tr><td style="border:none;"> </td></tr>               
                      
              </table>
            <%--</html:form>--%>
         </td>
      
    <td width="18">&nbsp;</td>
  </tr>

  <tr>
    <td height="25" colspan="2" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
      <!--DWLayoutTable-->
      <tr>
        <td width="945" height="25" class="copyright"><jsp:include page="/includes/Version.jsp"/></td>
        </tr>
    </table></td>
  </tr>
</table>
    </td>
  </tr>
</table>
  </body>
</html>
