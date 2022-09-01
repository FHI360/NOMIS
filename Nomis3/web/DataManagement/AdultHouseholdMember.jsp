<%-- 
    Document   : AdultHouseholdMember
    Created on : Dec 14, 2019, 1:11:43 PM
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
<title>Caregiver information management form</title>
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
$(function() {
        $("#dateOfEnrollment").datepicker();
        $("#dateOfBaselineHivStatus").datepicker();
        $("#dateEnrolledOnTreatment").datepicker();
    });
var beneficiaryType=0
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
function activateEnrolledOnTreatment(value)
{
    //if beneficiary is hiv positive
    if(value == "1") 
    {
        document.getElementById("enrolledOnTreatment").disabled = false;
        
        document.getElementById("dateOfBaselineHivStatus").disabled = false;
        if(document.getElementById("enrolledOnTreatment").value==1)
        {
            document.getElementById("dateEnrolledOnTreatment").disabled = false;
            document.getElementById("treatmentId").disabled = false;
        }
        else
        {
            document.getElementById("dateEnrolledOnTreatment").value = "";
            document.getElementById("dateEnrolledOnTreatment").disabled = true;
            document.getElementById("treatmentId").value = "";
            document.getElementById("treatmentId").disabled = true;
        }
    }
    else 
    {
       document.getElementById("enrolledOnTreatment").value=2
       document.getElementById("enrolledOnTreatment").disabled = true;
       document.getElementById("hivTreatmentFacilityId").value="select"
       document.getElementById("hivTreatmentFacilityId").disabled = true;
       document.getElementById("dateEnrolledOnTreatment").value = "";
       document.getElementById("dateOfBaselineHivStatus").value = "";
       document.getElementById("dateEnrolledOnTreatment").disabled = true;
       document.getElementById("treatmentId").value = "";
       document.getElementById("treatmentId").disabled = true;
       document.getElementById("dateOfBaselineHivStatus").disabled = false;
       if(value == "0" || value == "3" || value == "4") 
       {
           document.getElementById("dateOfBaselineHivStatus").disabled = true;
       }
    }
}
function activateReferralList(value) 
{
    if(value == "1") 
    {
        document.getElementById("hivTreatmentFacilityId").disabled = false;
        document.getElementById("treatmentId").disabled = false;
        document.getElementById("dateEnrolledOnTreatment").disabled = false;
    }
    else 
    {
        document.getElementById("hivTreatmentFacilityId").value="select"
        document.getElementById("hivTreatmentFacilityId").disabled = true;
        document.getElementById("dateEnrolledOnTreatment").disabled = true;
        document.getElementById("treatmentId").value = "";
        document.getElementById("treatmentId").disabled = true;
    }
}
/*function activateEnrolledOnTreatment(value)
{
    if(value == "1") 
    {
        document.getElementById("enrolledOnTreatment").disabled = false;
        document.getElementById("dateEnrolledOnTreatment").disabled = false;
        document.getElementById("dateOfBaselineHivStatus").disabled = false;
    }
    else 
    {
       document.getElementById("enrolledOnTreatment").value=2
       document.getElementById("enrolledOnTreatment").disabled = true;
       document.getElementById("hivTreatmentFacilityId").value="select"
       document.getElementById("hivTreatmentFacilityId").disabled = true;
       document.getElementById("dateEnrolledOnTreatment").value = "";
       document.getElementById("dateOfBaselineHivStatus").value = "";
       document.getElementById("dateEnrolledOnTreatment").disabled = true;
       document.getElementById("dateOfBaselineHivStatus").disabled = false;
       if(value == "0" || value == "3" || value == "4") 
       {
           document.getElementById("dateOfBaselineHivStatus").disabled = true;
       }
    }
}
function activateReferralList(value) 
{
    if(value == "1") 
    {
        document.getElementById("hivTreatmentFacilityId").disabled = false;
    }
    else 
    {
        document.getElementById("hivTreatmentFacilityId").value="select"
        document.getElementById("hivTreatmentFacilityId").disabled = true;
    }
}*/
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
    
    getValuesByAjaxApi('ajaxaction.do',req,'uniqueId')
    return true;
}
function submitForm(requiredAction,formId)
{
       setActionName(requiredAction)
       document.getElementById(formId).submit()
       return true
}

function confirmAction(name)
{
     if(name=="save" || name=="generateForms")
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
            <td height="100" valign="top">
                <div style="float: left" id="my_menu2" class="sdmenu" >
              <div><jsp:include page="../Navigation/ReportLinkPage.jsp"/></div>
            </div></td>
          </tr>
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="regsitertable">
        <html:form action="/adulthouseholdmember" method="POST" styleId="ahmForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="beneficiaryId" styleId="beneficiaryId" />
                                <html:hidden property="hhName" styleId="hhName" />
                            <center>
                                <table> 
                                    <tr><td colspan="4" align="center">
                                            <logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present>
                                            
                                        </td></tr>
                                    <tr><td colspan="4" align="center" style="font-size: 14px; font-weight: bold; color: red">
                                            <logic:present name="ahmWithdrawnMessage">${ahmWithdrawnMessage}</logic:present>
                                        </td></tr>
                                    <tr><td colspan="4" align="center">Caregivers and other adult members of household </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Bio data</legend>
                                        <table cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td colspan="4" align="center"> 
                                                    <label id="genId" style="color:green"> </label>
                                                    <label id="errIdMsg" style="color:red; margin-left: 60px;"> </label>
                                                    </td>
                                                    
                                             </tr> 
                                            <tr>
                                                    <td align="right">HH Serial No. </td>
                                                    <td colspan="3"><html:text property="hhSerialNo" styleId="hhSerialNo" onkeyup="return generateUniqueId(1);" onblur="setActionName('ahmList');forms[0].submit()" /> 
                                                        &nbsp; <input type="button" name="search" value="Search by name" onclick="showSearchDiv()" /> 
                                                        &nbsp;<label id="hhId" style="color:green"><logic:present name="hhName">${hhName}</logic:present> </label> 
                                                    </td>              
                                            </tr><!--ahmList-->
                                            
                                             
                                     <tr>
                                         <td style="width: 150px;" align="right">HH Unique Id </td>
                                         <td><html:text property="hhUniqueId" styleId="hhUniqueId" onblur="setActionName('ahmList');forms[0].submit()" readonly="true" /> </td>
                                        <td align="right">HH members </td>
                                        <td>
                                            <html:select style="width:150px;" property="enrolledBeneficiaryId" styleId="enrolledBeneficiaryId" onchange="setActionName('ahmDetails');forms[0].submit()" >
                                                <html:option value="select">select...</html:option>
                                                <logic:present name="ahmList">
                                                    <logic:iterate name="ahmList" id="ahm">
                                                        <html:option value="${ahm.beneficiaryId}">${ahm.firstName} ${ahm.surname} </html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>
                                            
                                     </tr>
                                    <tr>
                                        <td align="right">Surname </td>
                                        <td style="width: 300px;">
                                            <html:text property="surname" styleId="surname" onblur="generateUniqueId()"/>
                                            </td>
                                            <td align="right" style="width: 150px;">First name </td>
                                        <td >
                                            <html:text property="firstName" styleId="firstName" onblur="generateUniqueId()" />
                                            </td>
                                     </tr>
                                     <tr>
                                        <td style="width: 200px;" align="right">Date of enrollment </td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" onchange="generateUniqueId()" readonly="true"/>
                                            </td>
                                            <td align="right">Sex</td>
                                        <td>
                                            <html:select property="sex" styleId="sex" onchange="generateUniqueId()">
                                                <html:option value="select">select...</html:option>
                                                <html:option value="M">Male</html:option>
                                                <html:option value="F">Female</html:option>
                                            </html:select>
                                            </td>
                                     </tr>
                                     <tr>
                                        <td align="right">Age </td>
                                        <td >
                                            <html:text property="ageAtBaseline" styleId="ageAtBaseline" />
                                                
                                            </td>
                                            <td align="right">Marital status</td>
                                        <td>
                                            <html:select property="maritalStatus" styleId="maritalStatus">
                                                <logic:present name="maritalStatusList">
                                                    <logic:iterate name="maritalStatusList" id="maritalStatus">
                                                        <html:option value="${maritalStatus.value}">${maritalStatus.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                                <%--<html:option value="0">select...</html:option>
                                                <html:option value="1">Single/Never married</html:option>
                                                <html:option value="2">Separated</html:option>
                                                <html:option value="3">Married</html:option>
                                                <html:option value="4">Widowed</html:option>
                                                <html:option value="5">Divorced</html:option>
                                                --%>
                                            </html:select>
                                        </td>
                                     </tr>
                                     <tr> 
                             <td align="right">HIV status</td>
                            <td>
                                <html:select property="baselineHivStatus" styleId="baselineHivStatus" onchange="activateEnrolledOnTreatment(this.value)" style="width: 147px;">
                                    <logic:present name="mainHivStatus">
                                        <logic:iterate name="mainHivStatus" id="hivStatus">
                                            <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                        </logic:iterate>
                                    </logic:present>
                                    
                                </html:select>
                            </td>
                            <td align="right">Date of HIV status </td>
                            <td>
                                <html:text property="dateOfBaselineHivStatus" styleId="dateOfBaselineHivStatus" style="width:100px;" disabled="${ahmUnkHivDisabled}"/>
                                  
                            </td>

                         </tr>
                         
                 <tr> 
                     <td width="300" align="right">Enrolled on treatment? </td>
                            <td>
                                <html:select property="enrolledOnTreatment" styleId="enrolledOnTreatment" style="width:148px;" onchange="activateReferralList(this.value)" disabled="${ahmHivDisabled}">
                                  <html:option value="0">N/A</html:option>
                                    <html:option value="2">No</html:option>
                                  <html:option value="1">Yes</html:option>
                                </html:select>
                            </td>
                             <td align="right">ART start date</td>
                            <td>
                                <html:text property="dateEnrolledOnTreatment" styleId="dateEnrolledOnTreatment" disabled="${ahmHivDisabled}"/>
                                    
                            </td>
                            

                         </tr>
                         <tr>
                <td align="right">Facility enrolled</td>
                <td colspan="3" > 
                    <html:select property="hivTreatmentFacilityId" styleId="hivTreatmentFacilityId"  style="width:500px;" disabled="${ahmHivDisabled}">
                          <html:option value="select">select...</html:option>
                          <html:option value="xxxxxxxxxxx">Default facility</html:option>
                          <logic:present name="ovcfacilityList">
                              <logic:iterate name="ovcfacilityList" id="facility">
                                  <html:option value="${facility.facilityId}">${facility.facilityName}</html:option>
                            </logic:iterate>
                          </logic:present>
                      </html:select>
                </td>
              </tr>
              <tr>
                <td align="right">Treatment/ART No.</td>
                <td colspan="3" > 
                    <html:text property="treatmentId" styleId="treatmentId"  style="width:150px;" disabled="${ahmHivDisabled}"/>
                          
                </td>
              </tr>
                                    <tr>
                                        
                                        <td align="right">Phone number</td>
                                        <td >
                                            <html:text property="phoneNumber" styleId="phoneNumber" style="width:150px;"/>
                                            </td>
                                        <td align="right">Occupation</td>
                                        <td>
                                            <html:select property="occupation" styleId="occupation">
                                                <html:option value="0">select...</html:option>
                                                <logic:present name="occupationList">
                                                    <logic:iterate name="occupationList" id="occupation">
                                                        <html:option value="${occupation.value}">${occupation.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                                <%--<html:option value="1">Formally employed</html:option>
                                                <html:option value="2">Informally employed</html:option>
                                                <html:option value="3">Self employed</html:option>
                                                <html:option value="4">Retired pensioner</html:option>
                                                <html:option value="5">Retired non-pensioner</html:option>
                                                <html:option value="6">Unemployed</html:option>--%>
                                            </html:select>
                                        </td>
                                     </tr>
                                     <tr>
                                                                                  
                                        <td align="right">Education</td>
                                        <td >
                                            <html:select property="educationLevel" styleId="educationLevel" style="width: 200px;">
                                                <html:option value="0">select...</html:option>
                                                <html:option value="1">No formal education</html:option>
                                                <html:option value="2">Some primary education</html:option>
                                                <html:option value="3">Completed primary education</html:option>
                                                <html:option value="4">Some secondary/high school</html:option>
                                                <html:option value="5">Completed secondary or high school</html:option>
                                                <html:option value="6">Completed tertiary education</html:option>
                                                <html:option value="7">Prefer not to answer</html:option>
                                            </html:select>
                                        </td>
                                        <td align="right">Beneficiary type</td>
                                        <td >
                                            <html:select property="beneficiaryType" styleId="beneficiaryTyped" style="width: 200px;">
                                                <html:option value="0">select...</html:option>
                                                <html:option value="1">Household head</html:option>
                                                <html:option value="2">Caregiver</html:option>
                                                <html:option value="4">Household head and Caregiver</html:option>
                                            </html:select>
                                        </td>
                                     </tr>
                                     
                                        </table>
                                            </fieldset>
                                        </td>
                                    </tr>
                                     
                                     
                                    <tr><td colspan="4" align="center">
                                            <html:submit value="Save" onclick="setActionName('save')" disabled="${ahmSaveDisabled}"/>
                                            <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${ahmModifyDisabled}"/>
                                        <html:submit value="Delete..." onclick="return confirmAction('delete')" disabled="${ahmModifyDisabled}"/></td></tr>
                                    <tr><td colspan="4" style="height: 200px;">&nbsp;</td></tr>
                                    
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

