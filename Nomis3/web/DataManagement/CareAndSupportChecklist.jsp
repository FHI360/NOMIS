<%-- 
    Document   : CareAndSupportChecklist
    Created on : Jan 18, 2020, 1:17:38 PM
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
<title>Care and support checklist</title>
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
        $("#dateEnrolledOnTreatment").datepicker();
        $("#dateOfViralLoadResult").datepicker();
        $("#dateOfAssessment").datepicker();
        $("#dateOfViralLoadSampleCollection").datepicker();
        $("#dateOfNextAppointment").datepicker();
        $("#dateOfLastPickup").datepicker();
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
                document.getElementById("beneficiaryId").value=val
               
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
function activateReferralList(value) 
{
    //if beneficiary on ART
    if(value == "1") 
    {
       document.getElementById("facilityId").disabled = false;
       document.getElementById("pickedUpMedication").disabled = false;
       document.getElementById("missedARVsRecently").disabled = false;
       document.getElementById("dateEnrolledOnTreatment").disabled = false;
       
       //document.getElementById("dateEnrolledOnTreatment").disabled = false; 
    }
    else 
    {
        document.getElementById("facilityId").value="select"
        document.getElementById("facilityId").disabled = true;
        document.getElementById("pickedUpMedication").disabled = true;
        document.getElementById("missedARVsRecently").disabled = true;
        document.getElementById("dateEnrolledOnTreatment").value = "";
        document.getElementById("dateEnrolledOnTreatment").disabled = true;
        //disableARVSkippingOptions(value);
        //document.getElementById("dateEnrolledOnTreatment").disabled = true;
        //document.getElementById("treatmentId").value = "";
        //document.getElementById("treatmentId").disabled = true; 
    }
    disableARVSkippingOptions(value);
    enableDisableViralLoadRelatedFields(value)
    enableDisableViralTestDoneQuestionField(value)
    enableDisableTransportationSupportQuestionField(value)
}
function disableARVSkippingOptions(value)
{
    if(value==1)
    {
        disableChkBoxes("reasonsPeopleSkipARV",value)
        /*document.getElementById("drugsideeffect").disabled=false
        document.getElementById("stigma").disabled=false
        document.getElementById("dfdailylife").disabled=false
        document.getElementById("hopelessness").disabled=false
        document.getElementById("feelingwell").disabled=false
        document.getElementById("lackoffood").disabled=false
        document.getElementById("relbelief").disabled=false
        document.getElementById("lackofmotivation").disabled=false
        document.getElementById("dateEnrolledOnART").disabled=false*/
    }
    else
    {
        unselectChkBoxes("reasonsPeopleSkipARV") 
        disableChkBoxes("reasonsPeopleSkipARV",value)
        /*document.getElementById("drugsideeffect").disabled=true
        document.getElementById("stigma").disabled=true
        document.getElementById("dfdailylife").disabled=true
        document.getElementById("hopelessness").disabled=true
        document.getElementById("feelingwell").disabled=true
        document.getElementById("lackoffood").disabled=true
        document.getElementById("relbelief").disabled=true
        document.getElementById("lackofmotivation").disabled=true
        document.getElementById("dateEnrolledOnART").disabled=true*/        
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
function disableChkBoxes(chkname,disabled)
{
   var elements=document.getElementsByName(chkname)
    for(var i=0; i<elements.length; i++)
    {
        if(disabled==1)
        elements[i].disabled=false
        else
        elements[i].disabled=true
    }
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
function enableDisableViralLoadRelatedFields(val)
{
    if(val==1)
    {
        document.getElementById("dateOfViralLoadSampleCollection").disabled=false
        document.getElementById("viralLoadResultKnown").disabled=false
        document.getElementById("viralLoadResult").disabled=false
        document.getElementById("reasonViralLoadNotDone").disabled=false
    }
    else
    {
        document.getElementById("dateOfViralLoadSampleCollection").disabled=true
        document.getElementById("viralLoadResultKnown").disabled=true
        document.getElementById("viralLoadResult").disabled=true
        document.getElementById("reasonViralLoadNotDone").disabled=true
    }
}
function enableDisableViralTestDoneQuestionField(value)
{
    if(value==1)
    {
        document.getElementById("viralLoadTestDone").disabled=false
    }
    else
    {
        document.getElementById("viralLoadTestDone").disabled=true
    }
}
function enableDisableTransportationSupportQuestionField(value)
{
    if(value==1)
    {
        document.getElementById("receivedTransportationSupport").disabled=false
    }
    else
    {
        document.getElementById("receivedTransportationSupport").disabled=true
    }
}
function enableDisableViralLoadResultField(val)
{
    if(val==1)
    {
        document.getElementById("viralLoadResult").disabled=false
        document.getElementById("reasonViralLoadNotDone").disabled=true
    }
    else
    {
        document.getElementById("viralLoadResult").disabled=true
        document.getElementById("reasonViralLoadNotDone").disabled=false
    }
}
function enableDisableTransportationSupportField(val)
{
    if(val==1)
    {
        document.getElementById("monthsOfTransportationSupport").disabled=false 
    }
    else
    {
        document.getElementById("monthsOfTransportationSupport").disabled=true
    }
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
        <html:form action="/careandsupport" method="POST" styleId="ahmForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="recordId" styleId="recordId" />
                                
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">Care and support checklist </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    <tr><td style="font-size: 14px; font-weight: bold; color: red" colspan="4"><logic:present name="casWithdrawnMessage">${casWithdrawnMessage}</logic:present></td></tr>
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Beneficiary information</legend>
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
                                                 <%----%>
                                        <td >Beneficiary list </td>
                                        <td>
                                                     <html:select style="min-width:250px; max-width:350px;" property="beneficiaryId" styleId="beneficiaryId" onchange="setActionName('beneficiaryDetails');forms[0].submit()">
                                                <html:option value="select">select...</html:option>
                                                <logic:present name="cascBeneficiaryList">
                                                    <logic:iterate name="cascBeneficiaryList" id="beneficiary">
                                                        <html:option value="${beneficiary.beneficiaryId}">${beneficiary.firstName} ${beneficiary.surname} (${beneficiary.beneficiaryId}) (${beneficiary.beneficiaryTypeObject.name})</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                       </td>
                                        <%--<td >
                                            <html:select style="width:150px;" property="beneficiaryId" styleId="beneficiaryId" onchange="setActionName('childDetails');forms[0].submit()" >
                                                <html:option value="0">select...</html:option>
                                                <logic:present name="cascOvcPerHouseholdList">
                                                    <logic:iterate name="cascOvcPerHouseholdList" id="ovc">
                                                        <html:option value="${ovc.beneficiaryId}">${ovc.firstName} ${ovc.surname} </html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                       </td>--%>
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
                                                                          
                                     </table>
                                            </fieldset>
                                        </td>
                                    </tr>
                                     
                                     <tr>
                  <td valign="top" colspan="4"><fieldset>
                        <legend class="fieldset">Care and support checklist </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="762" height="17">
                          
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <tr><td colspan="4" align="center"><label style="font-weight:bolder">SECTION A</label></td></tr>
                         <tr>
                          <td width="20%">Date of assessment: </td>
                          <td width="80%" colspan="3">
                              <html:text property="dateOfAssessment" styleId="dateOfAssessment" styleClass="smallfieldcellinput" onchange="setActionName('assessmentDetails'); forms[0].submit()" readonly="true"/>&nbsp;(mm/dd/yyyy)
                          </td>

                        </tr> 
                         <tr>
                              <td class="right" colspan="2">Has the child been coughing for up to two weeks or more?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="coughSymptom" style="width:82px;" styleId="coughSymptom" disabled="${childSpecificQuestionDisabled}">
                                      <html:option value="0">select...</html:option>
                                      <html:option value="1">Yes</html:option>
                                      <html:option value="2">No</html:option>
                                      
                                  </html:select> </td><td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Has the child been losing weight recently or is not growing properly?
                                </td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="childLossinWeight" style="width:82px;" styleId="childLossinWeight" disabled="${childSpecificQuestionDisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                      
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Has the child been having fever for a long period of time?</td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="childHasFever" style="width:82px;" styleId="childHasFever" disabled="${childSpecificQuestionDisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Has the child been having night sweats?</td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="childHasNightSweat" style="width:82px;" styleId="childHasNightSweat" disabled="${childSpecificQuestionDisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Does the child have swellings on the neck?</td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="childHasSwelling" style="width:82px;" styleId="childHasSwelling" disabled="${childSpecificQuestionDisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr><td colspan="4"align="center"><label style="font-weight:bolder">SECTION B</label></td></tr>
                          <tr>
                              <td class="right" colspan="2">Is the beneficiary currently on ART?</td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="enrolledOnTreatment" style="width:82px;" styleId="enrolledOnTreatment" onchange="activateReferralList(this.value)">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                              
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Date enrolled on treatment</td>
                              <td colspan="2">
                                  <html:text property="dateEnrolledOnTreatment" styleClass="fieldcellinput" styleId="dateEnrolledOnTreatment" style="width:81px;" readonly="true" disabled="${casDateEnrolledOnTreatmentDisabled}"/> 
                              </td>
                              <td> </td>
                          </tr>
                          <tr><%--Which health facility is beneficiary currently receiving ART? --%> 
                            <td colspan="2">Which health facility is beneficiary currently receiving ART?</td>
                            <td colspan="2" > 
                                <html:select property="facilityId" styleId="facilityId" styleClass="fieldcellinput"  style="width:350px;" disabled="${casHealthFacilityDisabled}">
                                      <html:option value="select">select...</html:option>
                                      <logic:present name="ovcfacilityList">
                                          <logic:iterate name="ovcfacilityList" id="facility">
                                              <html:option value="${facility.facilityId}">${facility.facilityName}</html:option>
                                        </logic:iterate>
                                      </logic:present>
                                  </html:select>
                            </td>
                                
                            </tr>
                          <tr>
                              <td class="right" colspan="2">Has the beneficiary picked up his/her medication?</td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="pickedUpMedication" style="width:82px;" styleId="pickedUpMedication" disabled="${casOnTreatmentQuestionsDisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Date of last pickup</td>
                              <td colspan="2">
                                  <html:text styleClass="fieldcellinput" property="dateOfLastPickup" style="width:82px;" styleId="dateOfLastPickup" readonly="true"/>
                              </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Number of days of refill</td>
                              <td colspan="2">
                                  <html:text styleClass="fieldcellinput" property="numberOfDaysOfRefill" style="width:82px;" styleId="numberOfDaysOfRefill" />
                              </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Has the beneficiary missed his/her ARVs more than two doses in a month in the last 3 months?</td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="missedARVsRecently" style="width:82px;" styleId="missedARVsRecently" onchange="disableARVSkippingOptions(this.value)" disabled="${casOnTreatmentQuestionsDisabled}">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                  <td height="123" valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset">Reasons people skip ARVs </legend>
                        <div style="width:720px; height:120px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="710" height="102">
                          <table width="700" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              
                              <tr><td>Drug side effect </td> <td><html:multibox property='reasonsPeopleSkipARV' styleId="drugsideeffect" styleClass='smallfieldcellselect' value="Drug side effect" disabled="true"/> </td>
                                      <td>Stigma </td> <td><html:multibox property='reasonsPeopleSkipARV' styleId="stigma" styleClass='smallfieldcellselect' value="Stigma" disabled="true"/> </td>
                                  </tr>
                                  <tr>
                                      <td>Unceasing demand for daily life </td> <td><html:multibox property='reasonsPeopleSkipARV' styleId="dfdailylife" styleClass='smallfieldcellselect' value="Demand for daily life" disabled="true"/> </td>
                                      <td>Feeling of hopelessness </td> <td><html:multibox property='reasonsPeopleSkipARV' styleId="hopelessness" styleClass='smallfieldcellselect' value="Feeling of hopelessness" disabled="true"/> </td>
                                  </tr>
                                  <tr>
                                      <td>Feeling well </td> <td><html:multibox property='reasonsPeopleSkipARV' styleId="feelingwell" styleClass='smallfieldcellselect' value="Feeling well" disabled="true"/> </td>
                                      <td>Lack of food </td> <td><html:multibox property='reasonsPeopleSkipARV' styleId="lackoffood" styleClass='smallfieldcellselect' value="Lack of food" disabled="true"/> </td>
                                  </tr>
                                  <tr>
                                      <td>Religious belief </td> <td><html:multibox property='reasonsPeopleSkipARV' styleId="relbelief" styleClass='smallfieldcellselect' value="Religious belief" disabled="true"/> </td>
                                      <td>Lack of motivation </td> <td><html:multibox property='reasonsPeopleSkipARV' styleId="lackofmotivation" styleClass='smallfieldcellselect' value="Lack of motivation" disabled="true"/> </td>
                                  </tr>
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr>
                          <tr>
                              <td class="right" colspan="2">Has beneficiary carried out viral load test in the last one year?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="viralLoadTestDone" style="width:82px;" styleId="viralLoadTestDone" disabled="${casOnTreatmentQuestionsDisabled}" onchange="enableDisableViralLoadRelatedFields(this.value)">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                                <td>When was the viral load sample collected? <html:text property="dateOfViralLoadSampleCollection" styleId="dateOfViralLoadSampleCollection" styleClass="fieldcellinput" style="width:82px;" readonly="true" /> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Do you know the viral load test result?</td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="viralLoadResultKnown" style="width:82px;" styleId="viralLoadResultKnown" disabled="${casOnTreatmentQuestionsDisabled}" onchange="enableDisableViralLoadResultField(this.value)">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">What was the result? </td>
                              <td>
                                  <html:text styleClass="fieldcellinput" property="viralLoadResult" style="width:82px;" styleId="viralLoadResult" disabled="${casOnTreatmentQuestionsDisabled}"/>
                                       </td>
                              <td class="right">Date of VL result 
                                  <html:text styleClass="fieldcellinput" property="dateOfViralLoadResult" style="width:82px;" styleId="dateOfViralLoadResult" disabled="${casOnTreatmentQuestionsDisabled}"/>
                                       </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Why was the viral load not done? </td>
                              <td colspan="2">
                                  <html:text styleClass="fieldcellinput" property="reasonViralLoadNotDone" style="width:82px;" styleId="reasonViralLoadNotDone" disabled="${casOnTreatmentQuestionsDisabled}"/>
                                       </td>
                          </tr>
                          
                          <tr>
                              <td class="right" colspan="2">Has beneficiary received transportation support to access ARVs in the last six months?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="receivedTransportationSupport" style="width:82px;" styleId="receivedTransportationSupport" disabled="${casOnTreatmentQuestionsDisabled}" onchange="enableDisableTransportationSupportField(this.value)">
                                      <html:option value="0">select...</html:option><html:option value="3">N/A</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td>
                              <td> 
                                  How many months?<html:select styleClass="fieldcellinput" property="monthsOfTransportationSupport" style="width:82px;" styleId="monthsOfTransportationSupport" disabled="${casOnTreatmentQuestionsDisabled}">
                                      <html:option value="0">select...</html:option><html:option value="0">N/A</html:option>
                                      <html:option value="1">1</html:option><html:option value="2">2</html:option>
                                      <html:option value="3">3</html:option><html:option value="4">4</html:option>
                                      <html:option value="5">5</html:option><html:option value="6">6</html:option>
                                      <html:option value="7">7</html:option><html:option value="8">8</html:option>
                                      <html:option value="9">9</html:option><html:option value="10">10</html:option>
                                      <html:option value="11">11</html:option><html:option value="12">12</html:option>
                                  </html:select>
                              </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Has beneficiary experienced sores/rash/pain/discharge/bleeding from the vagina or penis in the last six months?</td><td >
                                  <html:select styleClass="fieldcellinput" property="soresRashPainExperience" style="width:82px;" styleId="soresRashPainExperience">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          
                          <tr>
                              <td class="right" colspan="2">Next clinical appointment date</td>
                              <td colspan="2">
                                  <html:text styleClass="fieldcellinput" property="dateOfNextAppointment" style="width:82px;" styleId="dateOfNextAppointment" readonly="true"/>
                                       
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
                <td colspan="3"> 
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
             </tr>
                                       <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${cascSaveDisabled}"/>
                                            <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${cascModifyDisabled}"/>
                                           <html:submit value="Delete" onclick="return confirmAction('delete')" disabled="${cascModifyDisabled}"/></td>
                                       </tr>
                                    
                                    
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
