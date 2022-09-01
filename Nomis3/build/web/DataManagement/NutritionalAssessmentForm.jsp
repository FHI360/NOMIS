<%-- 
    Document   : NutritionalAssessmentForm
    Created on : Dec 30, 2019, 7:16:23 PM
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
<title>Nutrition assessment form</title>
<link href="images/untitled.css" rel="stylesheet" type="text/css" />
<link href="images/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
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
.whiteLabel
{
    color: white;
    font-size: 14;
}
-->
</style>
<link href="sdmenu/sdmenu.css" rel="stylesheet" type="text/css" /> 
<link href="css/general/stylefile.css" rel="stylesheet" type="text/css"/>
<link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
        
<script type="text/javascript" src="js/odm.js"></script>
<script language="javascript">
var beneficiaryType=0
var defaultDate="01/01/1900"
$(function() {
        $("#dateOfAssessment").datepicker();
        $("#dateOfLastWeight").datepicker();
    });

function stateChanged()
{
	if (xmlhttp.readyState==4)
	{
            var val=xmlhttp.responseText;
            if(callerId=="search")
            {
                showSearchResult(val)
            }
            else if(callerId=="schoolList")
            {
                loadSchoolList(val)
            }
           else
           {
               //alert(beneficiaryType)
              
                if(beneficiaryType==1)
                {
                    document.getElementById("hhId").innerHTML=val
                    document.getElementById("hhUniqueId").value=val
                }
                
                else if(beneficiaryType==2)
                document.getElementById("ovcId").value=val
               
              <%--else
              {
                document.getElementById("hhUniqueId").value=val
              }--%>
           }
	}
        else
        {
            //alert("error "+xmlhttp.responseText)
        }
}
function setChangeInWeight()
{
    var weight=document.getElementById("weight").value
    var lastWeight=document.getElementById("lastWeight").value
    if((parseInt(weight) < parseInt(lastWeight)))
    {
        document.getElementById("changeInWeight").value=3
    }
    else if((parseInt(weight) == parseInt(lastWeight)))
    {
        document.getElementById("changeInWeight").value=2
    }
    else if((parseInt(weight) > parseInt(lastWeight)))
    {
        document.getElementById("changeInWeight").value=1
    }
    else
    {
        document.getElementById("changeInWeight").value=0
    }
}
function calculateBMI()
{
    var bmi=0
    var height=(parseInt(document.getElementById("height").value)/100);
    var weight=parseInt(document.getElementById("weight").value)
    if(height>0)
    {
        bmi=(weight/(height*height))
        document.getElementById("bmi").value=bmi
    }
}
function activateBMIAndNutritionalStatus()
{
    var age=document.getElementById("ovcAge").value
    var ageUnit=document.getElementById("ageUnit").value
    if((ageUnit=="Year" && parseInt(age) >4))
    {
        enableControl("bmi")
        enableControl("nutritionalStatus")
    }
    else
    {
        disableControl("bmi")
        disableControl("nutritionalStatus")
    }
}
function activateOedemaAndMUAC()
{
    var age=document.getElementById("ovcAge").value
    var ageUnit=document.getElementById("ageUnit").value
    if(((ageUnit=="month" || ageUnit=="Month") && parseInt(age) >5)|| (ageUnit=="Year" && parseInt(age) <5))
    {
        enableControl("oedma")
        enableControl("muac")
    }
    else
    {
        disableControl("oedema")
        disableControl("muac")
    }
}
function activateFoodSecurityAndDietQ4ToQ7()
{
    var age=document.getElementById("ovcAge").value
    var ageUnit=document.getElementById("ageUnit").value
    if(((ageUnit=="month" || ageUnit=="Month") && parseInt(age) >5)|| (ageUnit=="Year"))
    {
        enableControl("foodSecurityAndDietQ4")
        enableControl("foodSecurityAndDietQ5")
        enableControl("foodSecurityAndDietQ6")
        enableControl("foodSecurityAndDietQ7")
    }
    else
    {
        disableControl("foodSecurityAndDietQ4")
        disableControl("foodSecurityAndDietQ5")
        disableControl("foodSecurityAndDietQ6")
        disableControl("foodSecurityAndDietQ7")
    }
}
function activateFoodSecurityAndDietQ8AndQ9()
{
    var age=document.getElementById("ovcAge").value
    var ageUnit=document.getElementById("ageUnit").value
    if(((ageUnit=="month" || ageUnit=="Month") && parseInt(age) <6))
    {
        enableControl("foodSecurityAndDietQ8")
        enableControl("foodSecurityAndDietQ9")
    }
    else
    {
        disableControl("foodSecurityAndDietQ8")
        disableControl("foodSecurityAndDietQ9")
    }
}
function showSearchResult(value)
{
    document.getElementById("searchContent").innerHTML=value
    showComponent("pop")
}
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
function generateUniqueId(val)
{
    level2OuId=document.getElementById("level2OuId").value;
    level3OuId=document.getElementById("level3OuId").value;
    cboUniqueId=document.getElementById("cboId").value;
    var req=""
    if(val==1)
    {
        serialNumber=document.getElementById("hhSerialNo").value;
        req=level2OuId+";"+level3OuId+";"+cboUniqueId+";"+serialNumber
        beneficiaryType=1
    }
    else if(val==2)
    {
        serialNumber=document.getElementById("childSerialNo").value;
        hhUniqueId=document.getElementById("hhUniqueId").value;
        req=hhUniqueId+";"+serialNumber
        beneficiaryType=2
    }
    
    //req=level2OuId+";"+level3OuId+";"+cboUniqueId+";"+serialNumber
    getValuesByAjaxApi('ajaxaction.do',req,'uniqueId')
    return true;
}
function submitForm(requiredAction,formId)
{
       setActionName(requiredAction)
       document.getElementById(formId).submit()
       return true
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
                        <div><jsp:include page="../Navigation/DataEntryLinkPage.jsp"/></div>
                        
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
            <td height="157" valign="top"><div style="float: left" id="my_menu2" class="sdmenu" >
              <div><jsp:include page="../Navigation/ReportLinkPage.jsp"/></div>
            </div></td>
          </tr>
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="regsitertable">
        <html:form action="/nutritionalassessment" method="POST" styleId="dataForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="recordId" styleId="recordId" />
                                
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">Nutrition assessment form </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Child information</legend>
                                        <table>
                                             
                                            <tr>
                                                    <td align="center" >HH Serial No </td>
                                                    
                                                    <td colspan="3">
                                                        <html:text property="hhSerialNo" styleId="hhSerialNo" onkeyup="return generateUniqueId(1);" onblur="setActionName('householdDetails');forms[0].submit()" style="width:30px;"/> 
                                                        <html:text property="hhUniqueId" styleId="hhUniqueId" /> 
                                                        &nbsp;<input type="button" name="search" value="Search by name" onclick="showSearchDiv()" /> 
                                                        &nbsp;<label id="hhId" style="color:green"><logic:present name="hhName">${hhName}</logic:present> </label> 
                                                    </td>              
                                            </tr><!--ahmList-->
                                            
                                             <tr>
                                        <td >Children list </td>
                                        <td >
                                            <html:select style="width:150px;" property="ovcId" styleId="ovcId" onchange="setActionName('childDetails');forms[0].submit()" >
                                                <html:option value="0">select...</html:option>
                                                <logic:present name="hracOvcPerHouseholdList">
                                                    <logic:iterate name="hracOvcPerHouseholdList" id="ovc">
                                                        <html:option value="${ovc.ovcId}">${ovc.firstName} ${ovc.surname} </html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>
                                            <td align="right">Date of enrollment </td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" onchange="generateUniqueId()" disabled="true"/>
                                            </td>
                                     </tr>
                                    
                                     
                                     <tr>
                                         <td align="right">Sex </td>
                                        <td >
                                            <html:select property="sex" styleId="sex" onchange="generateUniqueId()" disabled="true">
                                                <html:option value="0">select...</html:option>
                                                <html:option value="M">Male</html:option>
                                                <html:option value="F">Female</html:option>
                                            </html:select>
                                            </td>
                                        <td align="right">Phone number</td>
                                        <td >
                                            <html:text property="phoneNumber" styleId="phoneNumber" disabled="true"/>
                                            </td>
                                     </tr>
                                    
                                    <tr>
                                        
                                        <td align="right">HIV status</td>
                                        <td>
                                            <html:select property="hivStatus" styleId="hivStatus" disabled="true" >
                                                <logic:present name="hivStatusForRiskAssessment">
                                                    <logic:iterate name="hivStatusForRiskAssessment" id="hivStatus">
                                                        <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                                
                                            </html:select>
                                        </td>
                                        <td align="right"> </td>
                                        <td>
                                            
                                        </td>
                                        
                                     </tr>
                                     </table>
                                            </fieldset>
                                        </td>
                                    </tr>
                                     
                                     <tr>
                  <td valign="top" colspan="4"><fieldset>
                        <legend class="fieldset">Assessment checklist </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                          <td width="762" height="130">

                              <fieldset><legend>Anthropometric</legend>
                              <table width="100%" border="1" bordercolor="#D7E5F2" class="regsitertable">
                            <tr>
                            <td valign="top" class="right" >Date of assessment </td>
                            <td colspan="3"><html:text property="dateOfAssessment" styleId="dateOfAssessment" styleClass="smallfieldcellinput" onchange="setActionName('assessmentDetails'); forms[0].submit()" /> &nbsp;(mm/dd/yyyy)
                                  </td>

                                
                            </tr>
                           <tr>
                            <td valign="top" class="right" >Weight(Kg): </td>
                            <td><html:text property="weight" styleClass="shortfieldcellinput" styleId="weight" style="margin-right:28px;" onblur="calculateBMI();setChangeInWeight()"/>
                                Height(cm): <html:text property="height" styleClass="shortfieldcellinput" styleId="height" onblur="calculateBMI()" /></td>

                                <td align="right">Last Weight</td>
                                <td ><html:text styleClass="smallfieldcellinput" property="lastWeight" styleId="lastWeight" onblur="setChangeInWeight()"/></td>
                            </tr>
                           <tr>
                            <td valign="top" class="right" >Change in weight</td>
                              <td><html:select property="changeInWeight" styleClass="fieldcellinput" styleId="changeInWeight">
                                    <html:option value="0">select...</html:option><html:option value="1">Gain</html:option><html:option value="2">Stationary</html:option>
                                    <html:option value="3">Loss</html:option>
                                  </html:select></td>
                              <td align="right">Date of Last weight </td><td ><html:text styleClass="fieldcellinput" property="dateOfLastWeight"  styleId="dateOfLastWeight" readonly="true"/> </td>

                                
                            </tr>
                            <tr>
                            <td valign="top" class="right" >Oedema </td>
                            <td><html:select styleClass="fieldcellinput" property="oedema"  styleId="oedema" disabled="${disableOedemaAndMUAC}" >
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select>
                                  </td>
                                  <td align="right">Muac (6mths to 4 yrs)</td><td><html:select styleClass="fieldcellinput" property="muac" styleId="muac" onchange="activateFacilityList(this.value)" disabled="${disableOedemaAndMUAC}" >
                                      <html:option value="0"> </html:option><html:option value="1">Red</html:option><html:option value="2">Yellow</html:option>
                                      <html:option value="3">Green</html:option>
                                  </html:select> </td>

                              
                            </tr>
                            <tr><%--${disableBMIAndNutritionalStatus}calculateBMI()--%>
                            <td valign="top" class="right" >BMI (Above 4 yrs)</td>
                            <td><html:text styleClass="fieldcellinput" property="bmi" styleId="bmi" disabled="true" />

                                  </td>
                                  <td align="right">Nutritional Status </td><td ><html:select styleClass="fieldcellinput" property="nutritionalStatus" styleId="nutritionalStatus" disabled="${disableBMIAndNutritionalStatus}" >
                                      <html:option value="0"> </html:option><html:option value="1">Obesse</html:option><html:option value="2">Over Weight</html:option>
                                      <html:option value="3">Normal</html:option>
                                      <html:option value="4">Mild malnutrition</html:option><html:option value="5">Moderate malnutrition</html:option>
                                      <html:option value="6">Severe malnutrition</html:option>
                                  </html:select> </td>

                              
                            </tr>

                              </table>
                              </fieldset>
                          </td>
                      </tr>
                  
                    <tr>
                      <td width="762" height="17">
                         
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                              <td class="right" colspan="2">In the last 30 days, was there ever no food to eat in the household? </td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ1" style="width:82px;" styleId="foodSecurityAndDietQ1" >
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="5">Rarely</html:option>
                                      <html:option value="6">Sometimes</html:option>
                                      <html:option value="7">Often</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">In the last 30 days did any household member go to sleep hungry because there wasn't any food?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ2" style="width:82px;" styleId="foodSecurityAndDietQ2" >
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="5">Rarely</html:option>
                                      <html:option value="6">Sometimes</html:option>
                                      <html:option value="7">Often</html:option>
                                  </html:select> </td>
                          </tr>
			<tr>
                              <td class="right" colspan="2">In the last 30 days, did any household member go a whole day and night without eating anything because there was not enough food?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ3" style="width:82px;" styleId="foodSecurityAndDietQ3" >
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="5">Rarely</html:option>
                                      <html:option value="6">Sometimes</html:option>
                                      <html:option value="7">Often</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Did the child receive any food or liquid besides breast milk in the last 24 hours?</td><td >
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ8" style="width:82px;" styleId="foodSecurityAndDietQ8" disabled="${disableFoodSecurityAndDietQ8AndQ9}">
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Is the mother experiencing any difficulties with breastfeeding?</td><td >
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ9" style="width:82px;" styleId="foodSecurityAndDietQ9" disabled="${disableFoodSecurityAndDietQ8AndQ9}">
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">How many times did the child eat yesterday?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ4" style="width:82px;" styleId="foodSecurityAndDietQ4" disabled="${disableFoodSecurityAndDietQ4ToQ7}" >
                                      <html:option value=""> </html:option><html:option value="0">0</html:option>
                                      <html:option value="1">1</html:option>
                                      <html:option value="2">2</html:option>
                                      <html:option value="3">3</html:option>
                                      <html:option value="4">4 or more</html:option>
                                    
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Yesterday, did the child eat any vitamin A rich foods (for example: mango, carrots, papaya, red palm oil, zogali, ugu, cassava, liver, or kidney)?</td>
                              <td>
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ5" style="width:82px;" styleId="foodSecurityAndDietQ5" disabled="${disableFoodSecurityAndDietQ4ToQ7}">
                                      <html:option value=""> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          
                          <tr>
                              <td class="right" colspan="2">Yesterday, did the child eat any Iron-rich foods (for example: liver, kidney, beans, groundnut, or dark green leaves such as spinach, zogali, ugu, cassava)?</td><td >
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ6" style="width:82px;" styleId="foodSecurityAndDietQ6" disabled="${disableFoodSecurityAndDietQ4ToQ7}">
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Yesterday, did the child eat any protein foods (for example: meat, eggs, fish, beans, groundnut, milk, cheese, soya, or Tom Brown)?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="foodSecurityAndDietQ7" style="width:82px;" styleId="foodSecurityAndDietQ7" disabled="${disableFoodSecurityAndDietQ4ToQ7}">
                                      <html:option value=""> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          
                          <tr>
                              <td class="right" colspan="2">Does the household have soap and water to wash dishes and utensils?</td><td >
                                  <html:select  property="hygieneQ1" styleClass="fieldcellinput" style="width:82px;" styleId="hygieneQ1" >
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>

                          <tr>
                              <td class="right" colspan="2">Does the household have soap or ash for hand washing?</td><td >
                                  <html:select styleClass="fieldcellinput" property="hygieneQ2" style="width:82px;" styleId="hygieneQ2" >
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Do you normally wash your hands with soap/ash before cooking/eating?</td><td >
                                  <html:select styleClass="fieldcellinput" property="hygieneQ3" style="width:82px;" styleId="hygieneQ3" >
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>

                          <tr>
                              <td class="right" colspan="2">Do you normally wash your hands with soap/ash after the toilet?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="hygieneQ4" style="width:82px;" styleId="hygieneQ4" >
                                      <html:option value="0"> </html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> 
                              </td>
                          </tr>
                          <tr>
                              <td colspan="3" ><b>If MUAC is red, refer to facility</b></td>
                            
                          </tr>
                          <tr>
                            <td colspan="3"> Facility <html:select property="muacFacility" styleClass="fieldcellinput" styleId="muacFacility" style="width:510px;" disabled="true">
                                      <html:option value="select">Select</html:option>
                                      <html:optionsCollection property="referralDirectoryList" label="facilityName" value="facilityId"/>
                                  </html:select></td>
                            
                          </tr>
                          <tr>
                              <td colspan="3" ><b>If MUAC is yellow, provide...</b></td>
                            
                          </tr>
                          <tr>
                              <td align="bottom" colspan="3">Nutrition education<html:multibox property="yellowMuacServices" styleId="nut_edu" style="border-align: bottom;" value="nut_edu"/> 
                            
                                Nutrition counselling <html:multibox property="yellowMuacServices" styleId="nut_counsel" style="border-align: bottom"  value="nut_counsel"/>
                            Nutrition support <html:multibox property="yellowMuacServices" styleId="nut_support" value="nut_support"/>
                            
                                HIV Risk assessment <html:multibox property="yellowMuacServices" styleId="hivRiskAss" value="hivRiskAss"/>
                              </td> 
                          </tr>
                        
                        </table>
                      </td>
                      </tr>
                  </table>
                  </fieldset>

                  </td>
                </tr>
                <tr>
                  <td>Completed by </td>
                <td > 
                    <html:select property="volunteerName" styleId="volunteerName" style="width:200px;" >
                          <%--<html:option value="select">select...</html:option>--%>
                          <html:option value="xxxxxxxxxxx">None</html:option>
                          <logic:present name="enumeratorList">
                              <logic:iterate name="enumeratorList" id="er">
                                <html:option value="${er.communityWorkerId}">${er.firstName} ${er.surname}</html:option>
                              </logic:iterate>

                          </logic:present>
                      </html:select>
                </td>

                <td  align="right"> </td>
                <td > 

                </td>
              </tr>
                                       <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${hracSaveDisabled}"/>
                                            <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${hracModifyDisabled}"/>
                                           <html:submit value="Delete" onclick="return confirmAction('delete')" disabled="${hracModifyDisabled}"/></td></tr>
                                    
                                    
                                </table>
                            </center>
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
</table>
  </body>
</html>
