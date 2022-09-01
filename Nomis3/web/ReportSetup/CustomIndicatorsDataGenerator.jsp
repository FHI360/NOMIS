<%-- 
    Document   : CustomIndicatorsDataGenerator
    Created on : Apr 29, 2020, 6:26:46 PM
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
<title>Custom indicators data generation</title>
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
                //alert(value)
                if(value==null || value=="0" || value==0)
                {
                    if(counter>0)
                    {
                        msg="Custom indicators data generation complete"
                        processMsg=" "
                        counter=0
                        
                    }
                    else
                    msg=" "
                    secs=0
                    showMsg("msgId",msg)
                    StopTheClock() 
                    return false;
                }
                else if(callerId=="checkCustomIndicatorsStatus")
                {
                    //if(secs==0)
                    secs=secCount
                    counter++
                    processMsg=value+" custom indicators tasks left to be processed"
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
    showMsg("msgId","Checking custom indicators tasks....")
    //processImportedFiles()
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
        msg="Procesing custom indicators, please wait..."//+secs+"--"+methodCallCount
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
function checkDataImportStatus()
{
   req="checkCustomIndicatorsStatus;"+"checkCustomIndicatorsStatus"
   //alert("req is "+req)
   getValuesFromDb('ajaxaction.do',req,'checkCustomIndicatorsStatus')
}			

function selectChkBoxes(chkname)
{
   var elements=document.getElementsByName(chkname)
    for(var i=0; i<elements.length; i++)
    {
        elements[i].checked=true
    }
}
function unselectChkBoxes(chkname)
{
   var elements=document.getElementsByName(chkname)
    for(var i=0; i<elements.length; i++)
    {
        elements[i].checked=false
    }
}			
function submitForm(requiredAction,formId)
{
       setActionName(requiredAction)
       document.getElementById(formId).submit()
       return true
}

function confirmAction(name)
{
     if(confirm("Are you sure you want to "+name+"?"))
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

<body onload="MM_preloadImages('images/About_down.jpg','images/Admin_down.jpg','images/Rapid_down.jpg','images/care_down.jpg','images/OVC_down.jpg');InitializeTimer()">

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
                        <div><jsp:include page="../Navigation/AnalyticsMenu.jsp"/></div>
                        <%--<jsp:include page="../Navigation/EnvironmentSetupLinkPage.jsp"/>--%>
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
              <div><jsp:include page="../Navigation/ReportLinkPage.jsp"/></div>
            </div></td>
          </tr> 
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="paramPage">
        <html:form action="/customindicatorsdatageneration" method="post">
                <html:hidden property="actionName" styleId="actionName"/>
               
        <span>
        <center>
            <html:errors/>
        <table class="paramPage">
            <tr><td colspan="3"><span id="msgId" style="font-size:medium; font-weight: bold; color: white"> </span></td></tr>
                                    <tr><td colspan="3"><label id="importStatusMsg" style="color: white"> </label></td></tr> 
            <tr><td style="color: white;" align="right">Partner </td><td colspan="2">
                        <html:select property="partnerCode">
                            <html:option value="select">select...</html:option>
                            <logic:present name="cidgPartnerList">
                                <logic:iterate name="cidgPartnerList" id="partner">
                                    <html:option value="${partner.partnerCode}">${partner.partnerName}</html:option>
                                </logic:iterate>
                            </logic:present>
                        </html:select></td>
                </tr>
                      
            <tr><td class="orglabel"> </td><td colspan="3" class="title" align="center">
                    <fieldset>
                        <legend class="fieldset"><label style="color:white">Select ${level2Ouh.name}</label></legend>
                        <div style="width:500px; min-height: 50px; max-height: 150px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td><!--level2OuListForReports-->
                          <table width="480" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="allLevel2OuList">
                                  <logic:iterate name="allLevel2OuList" id="level2Ou">
                                      <tr> <td style="width:30px"><html:multibox property='level2OuCodes' styleId="${level2Ou.uid}" value="${level2Ou.uid}" styleClass='smallfieldcellselect'/> </td>
                                      <td>${level2Ou.name} </td></tr>
                                  </logic:iterate>
                              </logic:present>
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset>
                </td>
            </tr>
            <tr><td> </td><td>
            <input type="button" value="Select all " onclick="selectChkBoxes('level2OuCodes')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('level2OuCodes')" />
                    </td></tr>
            
            <tr><td>&nbsp;</td>
                <td valign="top" colspan="3">
                      <fieldset>
                        <legend class="fieldset" style="color:white">Indicators </legend>
                        <div style="width:500px; height:150px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                            <tr>
                              <td>

                                  <fieldset>
                                    <table width="480" border="1" bordercolor="#D7E5F2" class="regsitertable">
                                       <logic:present name="customIndicatorList">
                                          <logic:iterate name="customIndicatorList" id="indicator">
                                              <tr><td><html:multibox property='indicators' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.merCode} </td> </tr>
                                          </logic:iterate>
                                        </logic:present>
                                      </table>
                                  </fieldset>
                                 </td>
                              </tr>
                    </table>
                        </div>
                      </fieldset>
                </td>
            </tr>
            <tr><td> </td><td>
            <input type="button" value="Select all " onclick="selectChkBoxes('indicators')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('indicators')" />
                    </td></tr>
            <tr><td class="orglabel" align="right"><label style="color:white">Period</label></td><td align="left" colspan="3">
                    <html:select property="startMth" styleId="startMth" style="width: 70px;">
                        <logic:present name="generatedMonths">
                            <logic:iterate id="months" name="generatedMonths">
                                <html:option value="${months}">${months}</html:option>
                            </logic:iterate>
                    </logic:present>
                    </html:select> <html:select property="startYear" styleId="startYear" style="width: 60px;">
                        <logic:present name="generatedYears">
                            <logic:iterate id="year" name="generatedYears" >
                                <html:option value="${year}">${year}</html:option>
                            </logic:iterate>
                        </logic:present>
                    </html:select> <label class="orglabel" style=" margin-left: 2px; margin-right: 2px"> to</label>
                    <html:select property="endMth" styleId="endMth" style="width: 70px;" >
                        <logic:present name="generatedMonths">
                            <logic:iterate id="month" name="generatedMonths">
                                <html:option value="${month}">${month}</html:option>
                            </logic:iterate>
                        </logic:present>
                    </html:select> <html:select property="endYear" style="width: 60px;" styleId="endYear" >
                        <logic:present name="generatedYears">
                            <logic:iterate id="year" name="generatedYears">
                                <html:option value="${year}">${year}</html:option>
                            </logic:iterate>
                        </logic:present>
                    </html:select> </td>
            </tr> <%--${cirbGenButtonDisabled} ${cirbGenButtonDisabled}--%>
            <tr><td colspan="3" align="center"><html:submit value="Generate report" onclick="return confirmAction('generateData')" style="width: 150px; margin-left: 50px;" disabled="${cirgSaveDisabled}" />
                <html:submit value="Delete data" onclick="return confirmAction('deleteData'); " style="width: 100px; margin-left: 10px" disabled="${cirgSaveDisabled}"/> </td></tr>
        </table>
        </center>
        </span>
   <!-- </div>-->

            </html:form>
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