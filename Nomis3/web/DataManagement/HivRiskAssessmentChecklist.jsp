<%-- 
    Document   : HivRiskAssessmentChecklist
    Created on : Dec 25, 2019, 7:13:13 PM
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
<title>HIV Risk assessment checklist</title>
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
function setAnswerToHivStatusQuestion(value)
{
    if(value==3 || value==4)
    {
        document.getElementById("hivStatusQuestion").value=2
    }
    else
    {
        document.getElementById("hivStatusQuestion").value=1
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
        <html:form action="/hivriskassessment" method="POST" styleId="ahmForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="recordId" styleId="recordId" />
                                
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">HIV Risk assessment form </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red; font-size: 14px; font-weight: bold;"><html:errors/></td></tr>
                                    <tr><td style="font-size: 14px; font-weight: bold; color: red" colspan="4"><logic:present name="hraWithdrawnMessage">${hraWithdrawnMessage}</logic:present></td></tr>
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
                                        
                                        <td align="right">Baseline HIV status</td>
                                        <td>
                                            <html:select property="baselineHivStatus" styleId="baselineHivStatus" disabled="true">
                                                <logic:present name="mainHivStatus">
                                                    <logic:iterate name="mainHivStatus" id="hivStatus">
                                                        <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                        </td>
                                        <td align="right">Current HIV status</td>
                                        <td> 
                                            <html:select property="currentHivStatus" styleId="currentHivStatus" disabled="true">
                                                <logic:present name="allHivStatus">
                                                    <logic:iterate name="allHivStatus" id="hivStatus">
                                                        <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>                                                                          
                                            </html:select>
                                        </td>
                                        
                                     </tr>
                                     
                                     <tr>
                                        
                                        <td align="right">Age at assessment</td>
                                        <td>
                                            <html:text property="ageAtAssessment" styleId="ageAtAssessment" disabled="true"/>
                                        </td>
                                        <td align="right">Current age</td>
                                        <td> 
                                            <html:text property="currentAge" styleId="currentAge" disabled="true"/>
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
                      <td width="762" height="17">
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">

			<tr>
                          <td width="20%">Date of assessment: </td>
                          <td width="80%" colspan="2">
                              <html:text property="dateOfAssessment" styleId="dateOfAssessment" styleClass="smallfieldcellinput" onchange="setActionName('assessmentDetails'); forms[0].submit()" readonly="true"/>&nbsp;(mm/dd/yyyy)
                          </td>

                        </tr>
                        </table>
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                                <td colspan="4">
                                    <fieldset><legend>HIV status disclosure</legend>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                                        <tr>
                                          <td class="right" colspan="2">Do you know the HIV status of your child/ward?</td>
                                          <td >
                                              <html:select styleClass="fieldcellinput" property="hivStatusQuestion" style="width:82px;" styleId="hivStatusQuestion" onchange="enableSingleControl('hivStatus',this.value)">
                                                 <html:option value="2">No</html:option>
                                                 <html:option value="1">Yes</html:option>
                                              </html:select> </td>
                                          <td><html:select property="hivStatusAtRiskAssessment" styleId="hivStatusAtRiskAssessment" onchange="setAnswerToHivStatusQuestion(this.value)"> 
                                                    <html:option value="0">select...</html:option>
                                                    <logic:present name="riskAssessmentCurrentHivStatus">
                                                    <logic:iterate name="riskAssessmentCurrentHivStatus" id="hivStatus">
                                                        <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                                
                                              </html:select>
                                              </td>
                                      </tr>
                                    </table>
                                        </fieldset>
                                </td>
                            </tr>
                          <tr>
                              <td class="right" colspan="2">Has the child/adolescent been tested for HIV in the last three (3) months?</td>
                              <td > <%--onchange="setActionName('childTestedQuestion'); forms[0].submit()"--%>
                                  <html:select styleClass="fieldcellinput" property="childTestedQuestion" style="width:82px;" styleId="childTestedQuestion"  disabled="${hracchildtesteddisabled}">
                                      <html:option value="0">select...</html:option>
                                      <html:option value="3">N/A</html:option>
                                      <html:option value="1">Yes</html:option>
                                      <html:option value="2">No</html:option>
                                      
                                  </html:select> </td><td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Is the child/adolescent's biological mother HIV positive? 
                                        <!--onchange="setControlsForHivParentQuestion(this.value)" -->
                                </td>
                              <td><!---->
                                  <html:select styleClass="fieldcellinput" property="hivParentQuestion" style="width:82px;" styleId="hivParentQuestion" onchange="setActionName('hivParentQuestion'); forms[0].submit()" disabled="${hrachivParentdisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                      
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Is the child/adolescent's biological mother having a long-standing sickness (frequent hospital visits/admissions, frequent use of medicines)?</td><td >
                                  <html:select styleClass="fieldcellinput" property="motherSicknessQuestion" style="width:82px;" styleId="motherSicknessQuestion" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td><td></td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Does the child/adolescent have a sibling that is HIV positive?</td><td >
                                  <html:select styleClass="fieldcellinput" property="hivSibblingQuestion" style="width:82px;" styleId="hivSibblingQuestion" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Does the child/adolescent have a sibling that is having a long-standing sickness (frequent hospital visits/admissions, frequent use of medicines)?</td><td >
                                  <html:select styleClass="fieldcellinput" property="sibblingSicknessQuestion" style="width:82px;" styleId="sibblingSicknessQuestion" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">In the last three months, has this child/adolescent been sick (e.g. frequent hospital visits/admissions, frequent use of medicines)?</td><td >
                                  <html:select styleClass="fieldcellinput" property="childSickQuestion" style="width:82px;" styleId="childSickQuestion" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">In the last three months, has this child/adolescent had more than 2 of the following: Frequent Cough, longstanding Fever, long-standing Diarrhoea, Loss of weight//poor weight gain, longstanding/ frequent skin rash?</td><td >
                                  <html:select styleClass="fieldcellinput" property="childHasMoreThanTwoIllnessQuestion" style="width:82px;" styleId="childHasMoreThanTwoIllnessQuestion" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Has the child ever received blood transfusion?</td><td >
                                  <html:select styleClass="fieldcellinput" property="bloodTransfusionQuestion" style="width:82px;" styleId="bloodTransfusionQuestion" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Has the child Had any of the following in the last 3 months: circumcision, ear piercing, scarification, injection/drip outside the hospital?</td><td >
                                  <html:select styleClass="fieldcellinput" property="childCircumcisedOrEarPierced" style="width:82px;" styleId="childCircumcisedOrEarPierced" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          
                          <tr>
                              <td class="right" colspan="2">Has the child/adolescent ever been sexually assaulted (any form)? </td><td >
                                  <html:select styleClass="fieldcellinput" property="sexualAbuseQuestion" style="width:82px;" styleId="sexualAbuseQuestion" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select>                                         
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Has the child/adolescent ever been pregnant (female children)? </td><td >
                                  <html:select styleClass="fieldcellinput" property="childEverPregnantQuestion" style="width:82px;" styleId="childEverPregnantQuestion" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select>                                         
                          </tr>
                          
                          
                          <tr>
                              <td class="right" align="center" colspan="3"><label style="font-weight:bold; font-size: 14px;">Section B [ To be completed for 15-17 years only in addition to section A] </label></td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Have you ever had sex in the past? (anal, vaginal  or oral)? </td><td >
                                  <html:select styleClass="fieldcellinput" property="sexualActivityQuestion" style="width:82px;" styleId="sexualActivityQuestion" disabled="${hraadolfiledsdisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                              </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Have you experienced painful urination, lower abdominal pain, vaginal or penile discharge in the past?</td><td >
                                  <html:select styleClass="fieldcellinput" property="genitalDischargeQuestion" style="width:82px;" styleId="genitalDischargeQuestion" disabled="${hraadolfiledsdisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>                                        
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Have you ever injected drugs, shared needles or other sharp objects in the past? </td><td >
                                  <html:select styleClass="fieldcellinput" property="drugInjectionQuestion" style="width:82px;" styleId="drugInjectionQuestion" disabled="${hraadolfiledsdisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>                                        
                          </tr>
                          
                         
                          <tr>
                              <td class="right" colspan="2">Is the child at risk? </td><td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="childAtRiskQuestion" style="width:82px;" styleId="childAtRiskQuestion" disabled="${hracchildatriskdisabled}">
                                      <html:option value="0">select...</html:option>
                                      <html:option value="2">No</html:option>
                                      <html:option value="1">Yes</html:option>
                                      
                                  </html:select>
                                   </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Is the child referred for HIV test? </td><td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="childReferredForHIVTest" style="width:82px;" styleId="childReferredForHIVTest" disabled="${hracchildatriskdisabled}">
                                      <html:option value="0">select...</html:option>
                                      <html:option value="2">No</html:option>
                                      <html:option value="1">Yes</html:option>
                                      
                                  </html:select>
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
