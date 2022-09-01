<%-- 
    Document   : XMLDataImport
    Created on : Apr 7, 2020, 9:13:59 PM
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
<title>XML data import</title>
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
-->
</style>
<!--<link href="sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />-->
<link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<link href="kidmap-default.css" rel="stylesheet" type="text/css" />
<link href="images/kidmap2.css" rel="stylesheet" type="text/css" />
<link href="images/untitled.css" rel="stylesheet" type="text/css" />
<link href="images/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
<script language="javascript">
var param=""
var callerId=""
var t=0
var k=0
var j=0;
var secs=0
var secCount=4
var methodCallCount=secCount-1
var processMsg=null
var msgSecs=0
var counter=0
var timerID = null
var timerRunning = false
var delay = 4000
var totalCount=0;
var timeSpentInSecs=0;
var timeSpentInMins=0;
var msg=" "
var dbIndicator=" "
function getValuesFromDb(url,str,id)
{
    callerId=id;
    xmlhttp=GetXmlHttpObject();
    if (xmlhttp==null)
    {
        alert ("Browser does not support HTTP Request");
        return;
    }
    url=url+"?qparam="+str;
    url=url+"&id="+id;
    url=url+"&sid="+Math.random();
    //alert(url+"---"+str)
    xmlhttp.onreadystatechange=stateChanged;
    xmlhttp.open("GET",url,true);
    xmlhttp.send(null);
}
function GetXmlHttpObject()
{
    if (window.XMLHttpRequest)
    {
        return new XMLHttpRequest();
    }
    if (window.ActiveXObject)
    {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
    return null;
}
function stateChanged()
{
	if (xmlhttp.readyState==4)
	{
		var value=xmlhttp.responseText;
                table=null
                progressFlag="ongoing";
                if(value !=null && value.indexOf("</table>") !=-1)
                {
                    table=value.substring(0,value.lastIndexOf("</table>")+1)
                    progressFlag=value.substring(value.lastIndexOf("</table>"))
                    
                }
                //alert("value1 is "+value)
                //alert("progressFlag is "+progressFlag)
                //value=value.trim()
                if(value==null || value=='null' || value=="" || value==" " || value=="-" || value==";" || progressFlag=="complete" || value.lastIndexOf("</table>complete") !=-1)
                {
                    //document.write("value2 is "+value)
                    //alert("value2 is"+value)
                    if(counter>0)
                    {
                        msg="Data import complete"
                        counter=0
                        
                    }
                    else
                    msg=" "
                    secs=0
                    showMsg("msgId",msg)
                    StopTheClock() 
                    return false;
                }
                else if(callerId=="checkImportStatus")
                {
                    //if(secs==0)
                    secs=secCount
                    counter++
                    processMsg=value
                    //alert("processMsg is"+processMsg+"*")
                    showMsg("msgId",value)
                    StartTheTimer()
                    //methodCallCount--
                } 
                
	}
        else
        {
            //alert("error "+xmlhttp.responseText)
        }
}
function InitializeTimer()
{
    showMsg("msgId","Checking for import....")
    processImportedFiles()
    checkDataImportStatus()
    //secs = secCount
    StopTheClock()
    StartTheTimer()
}
function StopTheClock()
{
    if(timerRunning)
        clearTimeout(timerID)
    timerRunning = false
}
function StartTheTimer()
{
    if(secs>0)
    {
        
        //alert("methodCallCount in StartTheTimer() "+methodCallCount)
        if(methodCallCount==0)
        {
            methodCallCount=secCount-1
            checkDataImportStatus()
        }
        methodCallCount--
        //msg="Database import in progress, please wait..."
        self.status = secs
        secs = secs - 1
        if(secs%2==0)
        msg=" "
        else
        //msg=processMsg
        msg="Database import in progress, please wait..."//+secs+"--"+methodCallCount
        timeSpentInSecs=timeSpentInSecs+delay
        timeSpentInMins=(timeSpentInSecs/60000)
        showMsg("msgId",msg)
        timerRunning = true
        timerID = self.setTimeout("StartTheTimer()", delay)
        //if(secs ==1)
        //secs=3
    }
    else
    {
        StopTheClock()
    }
}
function showMsg(id,msg)
{
    //alert(msg)
    document.getElementById(id).innerHTML=msg
    document.getElementById("importStatusMsg").innerHTML=processMsg
    //document.getElementById("timeSpentMsg").innerHTML=timeSpentInMins
}
function resetGlobalVariables()
{
    j=0
    k=0
    t=0
    secs=0
    timerID = null
    timerRunning = false
    delay = 4000
}
function processImportedFiles()
{
   req="processImportFiles;"+"processImportFiles"
   //alert("req is "+req)
   getValuesFromDb('ajaxaction.do',req,'processImportFiles')
}
function checkDataImportStatus()
{
   req="checkImportStatus;"+"checkImportStatus"
   //alert("req is "+req)
   getValuesFromDb('ajaxaction.do',req,'checkImportStatus')
}
function setActionName(val)
{
    document.getElementById("actionName").value=val
}//

function confirmAction(value)
{
    if(confirm("Are you sure you want to "+value+" this record?"))
    {
        setActionName(value)
        return true
    }
    else
    return false
}
        </script>

</head>

<body onload="MM_preloadImages('images/About_down.jpg','images/Admin_down.jpg','images/Rapid_down.jpg','images/care_down.jpg','images/OVC_down.jpg'); InitializeTimer()">

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

<jsp:include page="../includes/Pagetabs.jsp" />

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
          <td><jsp:include page="../Navigation/Logout.jsp" /></td>
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
        
        <td width="231" rowspan="2" valign="top"  bgcolor="#038233">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
          <!--DWLayoutTable-->
          <tr>
            <td width="231" height="28" valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <!--DWLayoutTable-->
              <tr>
                <td width="231" height="28"><img src="images/dataentry.jpg" width="231" height="28" /></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td height="85" valign="top">
                <div style="float: left" id="my_menu" class="sdmenu">
                    <div>
                        <div><jsp:include page="../Navigation/ExportImportLink.jsp"/></div>
                        
                    </div>
              </div>

            </td>
          </tr>
          <tr>
            <td height="30" valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <!--DWLayoutTable-->
              <tr>
                <td width="180" height="30"><img src="images/reports.jpg" width="231" height="30" /></td>
                    </tr>
            </table></td>
          </tr>
          <tr>
            <td height="100" valign="top">
                <div style="float: left" id="my_menu2" class="sdmenu" >
              <div><jsp:include page="../Navigation/ExportImportReportLink.jsp"/></div>
            </div></td>
          </tr>
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="regsitertable">
            <html:form enctype="multipart/form-data" action="/xmldataimport" method="POST" styleId="formId">
                                <html:hidden property="actionName" styleId="actionName" />
                                                                
                            <center>
                                <table>
                                    <tr><td colspan="4"><span id="msgId" style="font-size:medium; font-weight: bold; color: green"> </span></td></tr>
                                    <tr><td colspan="4"><label id="importStatusMsg" style="color:blue"> </label></td></tr> 
                                    <%--<tr><td style="font-size: 15px; font-weight: bold; color: blue" align="center"><logic:present name="dbImportMsg">${dbImportMsg}</logic:present> </td></tr>--%>
                                    <tr><td colspan="4" align="center">&nbsp; </td></tr>
                                    <tr><td colspan="4" align="center">XML Data import </td></tr>
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    
                                    <tr><td align="right">Choose file to upload </td><td><html:file property="uploadedFile" /></td><td> </td></tr>
                                    
                                    <tr><td align="right">Partner </td><td colspan="2">
                                            <html:select property="partnerCode">
                                                <html:option value="select">select...</html:option>
                                                <logic:present name="userAssignedPartners">
                                                    <logic:iterate name="userAssignedPartners" id="partner">
                                                        <html:option value="${partner.partnerCode}">${partner.partnerName}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select></td>
                                    </tr>
                                    <tr>
                                        <td align="right">Household assessment and enrollment</td>
                                          
                                         <td colspan="3">   
                                             <html:select property="hheOption" styleId="hheOption">
                                                 <html:option value="1">Accept updates only </html:option>
                                                 <html:option value="2">Accept new records only </html:option>
                                                 <html:option value="3">Accept both new records and updates </html:option>
                                                 <html:option value="4">Do not process</html:option>
                                             </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Child enrollment</td>
                                          
                                         <td colspan="3">   
                                             <html:select property="ovcOption" styleId="ovcOption">
                                                 <html:option value="1">Accept updates only </html:option>
                                                 <html:option value="2">Accept new records only </html:option>
                                                 <html:option value="3">Accept both new records and updates </html:option>
                                                 <html:option value="4">Do not process</html:option>
                                             </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Household service</td>
                                          
                                         <td colspan="3">   
                                             <html:select property="householdServiceOption" styleId="householdServiceOption">
                                                 <html:option value="3">Accept both new records and updates </html:option>
                                                 <html:option value="1">Accept updates only </html:option>
                                                 <html:option value="2">Accept new records only </html:option>
                                                 <html:option value="4">Do not process</html:option>
                                             </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Child service</td>
                                          
                                         <td colspan="3">   
                                             <html:select property="childServiceOption" styleId="childServiceOption">
                                                 
                                                 <html:option value="1">Accept updates only </html:option>
                                                 <html:option value="2">Accept new records only </html:option>
                                                 <html:option value="3">Accept both new records and updates </html:option>
                                                 <html:option value="4">Do not process</html:option>
                                             </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">HIV Risk assessment</td>
                                          
                                         <td colspan="3">   
                                             <html:select property="riskAssessmentOption" styleId="riskAssessmentOption">
                                                 <html:option value="3">Accept both new records and updates </html:option>
                                                 <html:option value="1">Accept updates only </html:option>
                                                 <html:option value="2">Accept new records only </html:option>
                                                 <html:option value="4">Do not process</html:option>
                                             </html:select>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td align="right">Enrollment status</td>
                                          
                                         <td colspan="3">   
                                             <html:select property="quarterlyEnrollmentStatusOption" styleId="quarterlyEnrollmentStatusOption">
                                                 <html:option value="1">update Quarterly Enrollment Status </html:option>
                                                 <html:option value="2">Do not update Quarterly Enrollment Status</html:option>
                                                 
                                             </html:select>
                                        </td>
                                    </tr>
                                    <tr><td colspan="3" align="center">
                                            <html:submit value="Upload" styleId="uploadBtn" style="width:75px; height:20px; " disabled="${xmlDataImportButtonDisabled}" onclick="setActionName('upload')"/></td></tr>
                                </table>
                            </center>
                            </html:form>
 </td>
   
      
      </tr>
    
</table>


          </td>
      
    <td width="18">&nbsp;</td>
  </tr>

  <tr>
    <td height="25" colspan="2" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
      <!--DWLayoutTable-->
      <tr>
        <td width="945" height="25" class="copyright"><jsp:include page="../includes/Version.jsp"/></td>
        </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
