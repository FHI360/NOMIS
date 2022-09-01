<%-- 
    Document   : NationalChildEnrollmentForm
    Created on : Feb 9, 2020, 8:56:07 PM
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
<title>Child enrollment form</title>
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
        $("#dateOfEnrollment").datepicker();
        $("#dateOfBaselineHivStatus").datepicker();
        $("#dateEnrolledOnTreatment").datepicker();
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
function getSchoolList()
{
    var level2OuId=document.getElementById("level2OuId").value;
    var level3OuId=document.getElementById("level3OuId").value;
    req=level2OuId+";"+level3OuId;
    //alert(req)
    getValuesFromDb('ajaxaction.do',req,'schoolList')
}
function loadSchoolList(schstr)
{
    var schSelect=document.getElementById("nearestSchoolId");
    var schoolNameSelect=document.getElementById("schoolName");
    schArr=schstr.split(":")
    for(var i=0; i<schArr.length; i++)
    {
        idnameArr=schArr[i].split(";")
        schSelect.options[i+1]=new Option(idnameArr[1],idnameArr[0])
        schoolNameSelect.options[i+1]=new Option(idnameArr[1],idnameArr[0])
    }
}
function showSearchResult(value)
{
    document.getElementById("searchContent").innerHTML=value
    showComponent("pop")
}
function loadHouseholdDetails(values)
{
    if(values !=null && values.indexOf(";") !=-1)
    {
        details=values.split(";")
        document.getElementById("hhUniqueId").value=details[0]
        document.getElementById("hhId").innerHTML=details[1]
        document.getElementById("hhName").value=details[1]
        loadEnrolledHouseholdMembers()
        hideComponent('pop')
        
    }
}
function setSchoolStatus(value)
{
    if(value==1)
    {
        document.getElementById("schoolName").disabled=false
        document.getElementById("grade").disabled=false
    }
    else
    {
        document.getElementById("schoolName").disabled=true
        document.getElementById("grade").disabled=true
    }
}
function activateEnrolledOnTreatment(value)
{
    if(value == "1") 
    {
        document.getElementById("enrolledOnTreatment").disabled = false;
        document.getElementById("dateEnrolledOnTreatment").disabled = false;
        document.getElementById("dateOfBaselineHivStatus").disabled = false;
        document.getElementById("treatmentId").disabled = false;
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
    }
    else 
    {
        document.getElementById("hivTreatmentFacilityId").value="select"
        document.getElementById("hivTreatmentFacilityId").disabled = true;
    }
}
function loadEnrolledHouseholdMembers()
{
    setActionName('ahmOvcList');
    document.getElementById("formId").submit() 
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

function setBtnName(name)
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
            <td height="157" valign="top"><div style="float: left" id="my_menu2" class="sdmenu" >
              <div><jsp:include page="../Navigation/ReportLinkPage.jsp"/></div>
            </div></td>
          </tr>
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="regsitertable">
        <html:form action="/nationalchildenrollment" method="POST" styleId="formId">
                    <html:hidden property="actionName" styleId="actionName" />
                    <html:hidden property="hhName" styleId="hhName" />
                    
                    <html:hidden property="hiddenBeneficiaryId" styleId="hiddenBeneficiaryId" />
                <center>
                    <table>

                        <tr><td colspan="4" align="center">Child enrollment form</td></tr>
                          <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                        <tr><td colspan="4" align="center" style="color:red; font-size: 16px;"><html:errors/></td></tr>
                        <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                        <tr>
                            <td colspan="4">

                                <fieldset><legend>Child information</legend>
                            <table>
                                <tr>
                                        <td > </td>
                                        <td colspan="3">
                                            <input type="button" name="search" value="Search by name" onclick="showSearchDiv()" />
                                        </td> 

                                </tr>
                                <tr>
                                    <td>HH Serial No.</td>
                                        <td >
                                            <html:text property="hhSerialNo" styleId="hhSerialNo" onkeyup="return generateUniqueId(1);" onblur="setActionName('householdDetails');forms[0].submit()" style="width:30px;"/> 
                                            <html:text property="hhUniqueId" styleId="hhUniqueId" readonly="true" />
                                             &nbsp;<label id="hhId" style="color:white"> </label>
                                             &nbsp;<label id="hhName" style="color:green"><logic:present name="hhName">${hhName}</logic:present> </label>
                                        </td>
                                      <td align="right">Enrolled children </td>
                                        <td >
                                            <html:select style="width:165px;" property="enrolledChildId" styleId="enrolledChildId" onchange="setActionName('childDetails');forms[0].submit()" >
                                                <html:option value="select">select...</html:option>
                                                <logic:present name="listOfChildrenPerHousehold">
                                                    <logic:iterate name="listOfChildrenPerHousehold" id="child">
                                                        <html:option value="${child.ovcId}">${child.firstName} ${child.surname} (${child.ovcId}) </html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>  

                                </tr>
                                <tr>
                                    <td>Child Serial No. </td> 
                                        <td>
                                            <html:text property="childSerialNo" styleId="childSerialNo" onkeyup="return generateUniqueId(2)" style="width:30px;" onblur="setActionName('childDetailsBySerialNo');forms[0].submit()"/> 
                                            <html:text property="ovcId" styleId="ovcId" readonly="true" />
                                        </td> 
                                        
                                            <td align="right">Date of enrollment </td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" readonly="true"/> 
                                                
                                            </td>
                                            
                                     </tr> 
                               <%-- <tr>
                                    <td>Date of enrollment</td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" readonly="true"/> 
                                            
                                             
                                        </td>
                                        <td align="right">Child Unique Id </td>
                                        <td>
                                            <html:text property="ovcId" styleId="ovcId" style="width:160px;" onblur="setActionName('childDetails');forms[0].submit()" /> 
                                            
                                             &nbsp;<label id="hhId2" style="color:green"><logic:present name="hhName">${hhName}</logic:present> </label> 
                                        </td> 

                                </tr>--%> 
                                <tr>
                             <td >Child first name </td>
                            <td >
                                <html:text property="firstName" styleId="firstName" />
                                </td>
                            <td align="right">Surname </td>
                            <td >
                                <html:text property="surname" styleId="surname" style="width:160px;"/>
                                </td>

                         </tr>
                         <tr>
                           <td >Sex </td>
                            <td >
                                <html:select property="sex" styleId="sex" >
                                    <html:option value="select">select...</html:option>
                                    <html:option value="M">Male</html:option>
                                    <html:option value="F">Female</html:option>
                                </html:select>
                                </td> 
                            <td align="right">Age </td>
                            <td>
                                <html:select property="ageAtBaseline" styleId="ageAtBaseline">
                                      <html:option value="0">select...</html:option>
                                            <html:option value="1">1</html:option>
                                            <html:option value="2">2</html:option>
                                            <html:option value="3">3</html:option>
                                            <html:option value="4">4</html:option>
                                           <html:option value="5">5</html:option>
                                           <html:option value="6">6</html:option>
                                           <html:option value="7">7</html:option>
                                           <html:option value="8">8</html:option>
                                           <html:option value="9">9</html:option>
                                           <html:option value="10">10</html:option>
                                           <html:option value="11">11</html:option>
                                           <html:option value="12">12</html:option>
                                           <html:option value="13">13</html:option>
                                           <html:option value="14">14</html:option>
                                           <html:option value="15">15</html:option>
                                           <html:option value="16">16</html:option>
                                           <html:option value="17">17</html:option>
                                           
                                  </html:select>
                                  <html:select property="ageUnitAtBaseline" styleId="ageUnitAtBaseline">
                                      <html:option value="0">Age unit</html:option>
                                            <html:option value="1">Month</html:option>
                                            <html:option value="2">Year</html:option>
                                            
                                  </html:select>

                                </td>

                         </tr>
                         
                         <tr>
                  <td height="123" valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset">Vulnerability status </legend>
                        <div style="width:680px; height:120px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675" height="102">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="legacyVulnerabilityStatusList">
                                  <logic:iterate name="legacyVulnerabilityStatusList" id="subList">
                                      <tr><td><html:multibox property='vulnerabilityStatus' styleId="${subList[0].vulnerabilityStatusId}" value="${subList[0].vulnerabilityStatusId}" styleClass='smallfieldcellselect'/> </td><td>${subList[0].vulnerabilityStatusName} </td> 
                                          <td><html:multibox property='vulnerabilityStatus' styleId="${subList[1].vulnerabilityStatusId}" value="${subList[1].vulnerabilityStatusId}" styleClass='smallfieldcellselect'/> </td><td>${subList[1].vulnerabilityStatusName} </td> 
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr>                           
                         
                         <tr> 
                             <td>HIV status</td>
                            <td>
                                <html:select property="baselineHivStatus" styleId="baselineHivStatus" onchange="activateEnrolledOnTreatment(this.value)" >
                                    <logic:present name="mainHivStatus">
                                        <logic:iterate name="mainHivStatus" id="hivStatus">
                                            <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                        </logic:iterate>
                                    </logic:present>
                                    
                                </html:select>
                            </td>
                            <td align="right">Date of HIV status </td>
                            <td>
                                <html:text property="dateOfBaselineHivStatus" styleId="dateOfBaselineHivStatus" style="width:100px;" />
                                  
                            </td>

                         </tr>
                         <tr>
                 <tr> 
                     <td align="right">Child on treatment?</td>
                            <td>
                                <html:select property="enrolledOnTreatment" styleId="enrolledOnTreatment" style="width:100px;" onchange="activateReferralList(this.value)" disabled="${enrhivDisabled}">
                                  <html:option value="0">select...</html:option>
                                    <html:option value="2">No</html:option>
                                  <html:option value="1">Yes</html:option>
                                </html:select>
                            </td>
                             <td>Date enrolled on treatment</td>
                            <td>
                                <html:text property="dateEnrolledOnTreatment" styleId="dateEnrolledOnTreatment" />
                                    
                            </td>
                            

                         </tr>
                         <tr>
                <td >Facility enrolled</td>
                <td colspan="3" > 
                    <html:select property="hivTreatmentFacilityId" styleId="hivTreatmentFacilityId"  style="width:540px;" disabled="${enrhivDisabled}">
                          <html:option value="select">select...</html:option>
                          <html:option value="xxxxxxxxxxx">Deafult facility</html:option>
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
                    <html:text property="treatmentId" styleId="treatmentId"  style="width:150px;" disabled="${enrhivDisabled}"/>
                          
                </td>
                         <tr>
                            <td >Has birth certificate </td>
                            <td>
                                <html:select property="birthCertificate" styleId="birthCertificate">
                                    <html:option value="0">select...</html:option>
                                    <html:option value="1">Yes</html:option>
                                    <html:option value="2">No</html:option>
                                </html:select>
                            </td>
                            <td align="right">Child in school</td>
                            <td>
                                <html:select property="schoolStatus" styleId="schoolStatus" onchange="setSchoolStatus(this.value)" >
                                    <html:option value="0">select...</html:option>
                                    <html:option value="1">Yes</html:option>
                                    <html:option value="2">No</html:option>
                                    <html:option value="3">Not applicable</html:option>

                                </html:select>
                            </td>
                         </tr>
                        <tr>
                            
                             <td>Name of school</td>
                            <td>
                                <html:select property="schoolName" styleId="schoolName" style=" width:300px;" disabled="${schoolDisabled}">
                                    <html:option value="select">select...</html:option>
                                    <logic:present name="schoolListByLevel2Ou">
                                        <logic:iterate name="schoolListByLevel2Ou" id="school">
                                            <html:option value="${school.id}">${school.schoolName}</html:option>
                                        </logic:iterate>
                                    </logic:present>
                                </html:select>
                            </td>
                            <td align="right">Grade/Form</td>
                            <td>

                                <html:select property="grade" styleId="grade" disabled="${schoolDisabled}">
                                    <html:option value="0">select...</html:option>

                                    <logic:present name="schoolGradeList"> 
                                         <logic:iterate name="schoolGradeList" id="grade">
                                            <html:option value="${grade.id}">${grade.gradeName}</html:option>
                                         </logic:iterate>
                                    </logic:present>
                                </html:select>
                            </td>

                         </tr>
                        
                         <tr>
                             <td colspan="4">
                                 <fieldset><legend style="font-weight: bolder; font-size: 15px;">Caregiver information </legend>       
                         <table>   
                         <tr> 
                             <td align="right">Caregiver name</td>
                            <td>
                                <html:select property="caregiverId" styleId="caregiverId" style="width:145px;" onchange="setActionName('adultMemberDetails'); forms[0].submit()">
                                    <html:option value="0">select...</html:option>
                                    <logic:present name="listOfAdultMembersPerHousehold">
                                        <logic:iterate name="listOfAdultMembersPerHousehold" id="ahm">
                                            <html:option value="${ahm.beneficiaryId}">${ahm.firstName} ${ahm.surname} </html:option>
                                        </logic:iterate>
                                    </logic:present>
                                </html:select>
                            </td>
                            <td align="right">Relationship to child </td>
                            <td>
                                <html:select property="caregiverRelationship"  styleId="caregiverRelationship" style="width:100px;">
                                  <html:option value="0">select...</html:option>
                                  <html:option value="1">Father</html:option>
                                    <html:option value="2">Mother</html:option>
                                  <html:option value="3">Grand parent</html:option>
                                  <html:option value="4">Aunt/Uncle</html:option>
                                  <html:option value="5">Sister/Brother</html:option>
                                  <html:option value="6">Guardian</html:option>
                                  <html:option value="7">Social worker</html:option>
                                  <html:option value="8">Caregiver</html:option>
                                </html:select>
                            </td>
                         </tr>

                         <tr> 
                             <td align="right">Phone </td>
                            <td>
                                <html:text property="caregiverPhone" styleId="caregiverPhone" style="width:100px;" readonly="true"/>
                                  
                            </td>
                            <td align="right">Sex </td>
                            <td><html:text property="caregiverSex" styleId="caregiverSex" readonly="true" style="width:50px;"/> 
                                &nbsp;&nbsp;Age <html:text property="caregiverAge" styleId="caregiverAge" readonly="true" style="width:50px;"/></td>
                            

                         </tr>
                         <tr> 
                             <td align="right">Caregiver HIV status </td>
                            <td>
                                <html:text property="caregiverHivStatus" styleId="caregiverHivStatus" style="width:100px;" readonly="true"/> 
                            </td>
                            <td align="right">Date of Hiv status </td>
                            <td><html:text property="dateOfCaregiverHivStatus" styleId="dateOfCaregiverHivStatus" readonly="true"/> 
                                </td>
                         </tr>
                         <tr> 
                             <td align="right">Caregiver enrolled in care?</td>
                            <td>
                                <html:text property="caregiverEnrolledOnTreatment" styleId="caregiverEnrolledOnTreatment" style="width:100px;"/> 
                            </td>
                            <td align="right">Date caregiver enrolled </td>
                            <td><html:text property="dateCaregiverEnrolledOnTreatment" styleId="dateCaregiverEnrolledOnTreatment" readonly="true"/> 
                                </td>
                         </tr>
                         <tr> 
                             <td align="right">Facility enrolled </td>
                            <td colspan="3"><html:text property="facilityCaregiverEnrolled" styleId="facilityCaregiverEnrolled" readonly="true" style="width:250px;"/> 
                                </td>
                         </tr>
                         </table>
                                </fieldset>
                         </td></tr>
                   <tr>
                      <td height="172" valign="top" colspan="4"><fieldset>
                        <legend class="fieldset">Baseline / Initial child status index assessment </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <!--DWLayoutTable-->
                        <tr>
                          <td width="752" height="172">
                              <table width="100%" border="1" bordercolor="#D7E5F2" class="regsitertable">
                            <tr>
                              <td width="17%" rowspan="2"><strong>Domain</strong></td>
                              <td colspan="4"><strong>Score<br />
                                (Mark as appropriate)</strong></td>
                              <td width="21%" rowspan="2"><strong>Domain</strong></td>
                              <td colspan="4"><strong>Score<br />
(Mark as appropriate)</strong></td>
                              </tr>
                            <tr>
                                <td width="7%" align="center">Good<br /><div align="center">4</div></td>
                              <td width="7%" align="center">Fair<br /><div align="center">3</div></td>
                              <td width="7%" align="center">Bad<br /><div align="center">2</div></td>
                              <td width="7%" align="center">Very Bad<br /><div align="center">1</div> </td>

                              <td width="7%" align="center">Good<br /><div align="center">4</div></td>
                              <td width="7%" align="center">Fair<br /><div align="center">3</div></td>
                              <td width="7%" align="center">Bad<br /><div align="center">2</div></td>
                              <td width="7%" align="center">Very Bad<br /><div align="center">1</div> </td>
                            </tr>
                            <tr>
                              <td colspan="5" bgcolor="#F0F0F0"><strong>Food and nutrition </strong></td>
                              <td colspan="5" bgcolor="#F0F0F0"><strong>Health </strong></td>
                              </tr>
                            <tr>
                                <td>Food security </td>
                              <td align="center"><html:radio property="foodSecurity" styleId="csiFactor3_4" value="4" /></td><td align="center"><html:radio property="foodSecurity" styleId="csiFactor3_3" value="3" /></td><td align="center"><html:radio property="foodSecurity" styleId="csiFactor3_2" value="2" /></td><td align="center"><html:radio property="foodSecurity" styleId="csiFactor3_1" value="1" /></td>
                              <td>Wellness</td>
                              <td align="center"><html:radio property="wellness" styleId="csiFactor5_4" value="4" /></td><td align="center"><html:radio property="wellness" styleId="csiFactor5_3" value="3" /></td><td align="center"><html:radio property="wellness" styleId="csiFactor5_2" value="2" /></td><td align="center"><html:radio property="wellness" styleId="csiFactor5_1" value="1" /></td>
                             
                            </tr>
                            <tr>
                                <td>Nutrition and growth </td>
                              <td align="center"><html:radio property="nutritionAndGrowth" styleId="csiFactor4_4" value="4" /></td><td align="center"><html:radio property="nutritionAndGrowth" styleId="csiFactor4_3" value="3" /></td><td align="center"><html:radio property="nutritionAndGrowth" styleId="csiFactor4_2" value="2" /></td><td align="center"><html:radio property="nutritionAndGrowth" styleId="csiFactor4_1" value="1" /></td>
                              <td>Health care services </td>
                              <td align="center"><html:radio property="healthCareServices" styleId="csiFactor6_4" value="4" /></td><td align="center"><html:radio property="healthCareServices" styleId="csiFactor6_3" value="3" /></td><td align="center"><html:radio property="healthCareServices" styleId="csiFactor6_2" value="2" /></td><td align="center"><html:radio property="healthCareServices" styleId="csiFactor6_1" value="1" /></td>
                             
                            </tr>
                            <tr>
                              <td colspan="5" bgcolor="#F0F0F0"><strong>Shelter and care</strong></td>
                              <td colspan="5" bgcolor="#F0F0F0"><strong>Psychosocial</strong></td>
                              </tr>
                            <tr>
                              <td>Shelter</td>
                              <td align="center"><html:radio property="shelter" styleId="csiFactor11_4" value="4" /></td><td align="center"><html:radio property="shelter" styleId="csiFactor11_3" value="3" /></td><td align="center"><html:radio property="shelter" styleId="csiFactor11_2" value="2" /></td><td align="center"><html:radio property="shelter" styleId="csiFactor11_1" value="1" /></td>

                              <td>Emotional health </td>
                           <td align="center"><html:radio property="emotionalHealth" styleId="csiFactor1_4" value="4" /></td>
                            <td align="center"><html:radio property="emotionalHealth" styleId="csiFactor1_3" value="3" /></td>
                             <td align="center"><html:radio property="emotionalHealth" styleId="csiFactor1_2" value="2" /></td>
                            <td align="center"><html:radio property="emotionalHealth" styleId="csiFactor1_1" value="1" /></td>

                            </tr>
                            <tr>
                              <td>Care</td>
                              <td align="center"><html:radio property="care" styleId="csiFactor12_4" value="4" /></td><td align="center"><html:radio property="care" styleId="csiFactor12_3" value="3" /></td><td align="center"><html:radio property="care" styleId="csiFactor12_2" value="2" /></td><td align="center"><html:radio property="care" styleId="csiFactor12_1" value="1" /></td>
                              <td>Social behaviour </td>
                              <td align="center"><html:radio property="socialBehaviour" styleId="csiFactor2_4" value="4" /></td><td align="center"><html:radio property="socialBehaviour" styleId="csiFactor2_3" value="3" /></td><td align="center"><html:radio property="socialBehaviour" styleId="csiFactor2_2" value="2" /></td><td align="center"><html:radio property="socialBehaviour" styleId="csiFactor2_1" value="1" /></td>

                            </tr>
                            <tr>
                              <td colspan="5" bgcolor="#F0F0F0"><strong>Protection</strong></td>
                              <td colspan="5" bgcolor="#F0F0F0"><strong>Education and skills </strong></td>
                              </tr>
                            <tr>
                              <td>Abuse and exploitation </td>
                              <td align="center"><html:radio property="abuseAndExploitation" styleId="csiFactor9_4" value="4" /></td><td align="center"><html:radio property="abuseAndExploitation" styleId="csiFactor9_3" value="3" /></td><td align="center"><html:radio property="abuseAndExploitation" styleId="csiFactor9_2" value="2" /></td><td align="center"><html:radio property="abuseAndExploitation" styleId="csiFactor9_1" value="1" /></td>
                              <td>Development and performance</td>
                              <td align="center"><html:radio property="developmentAndPerformance" styleId="csiFactor7_4" value="4" /></td>
                              <td align="center"><html:radio property="developmentAndPerformance" styleId="csiFactor7_3" value="3" /></td>
                              <td align="center"><html:radio property="developmentAndPerformance" styleId="csiFactor7_2" value="2" /></td>
                              <td align="center"><html:radio property="developmentAndPerformance" styleId="csiFactor7_1" value="1" /></td>

                            </tr>
                            <tr>
                              <td>Legal protection </td>
                              <td align="center"><html:radio property="legalProtection" styleId="csiFactor10_4" value="4" /></td><td align="center"><html:radio property="legalProtection" styleId="csiFactor10_3" value="3" /></td><td align="center"><html:radio property="legalProtection" styleId="csiFactor10_2" value="2" /></td><td align="center"><html:radio property="legalProtection" styleId="csiFactor10_1" value="1" /></td>
                              <td>Education and work </td>
                              <td align="center"><html:radio property="educationAndWork" styleId="csiFactor8_4" value="4" /></td><td align="center"><html:radio property="educationAndWork" styleId="csiFactor8_3" value="3" /></td><td align="center"><html:radio property="educationAndWork" styleId="csiFactor8_2" value="2" /></td><td align="center"><html:radio property="educationAndWork" styleId="csiFactor8_1" value="1" /></td>
                            </tr>

                            
                          </table></td>
                        </tr>
                      </table></fieldset>                      </td>
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
               
                                       </table>
                               </fieldset>
                            </td>
                        </tr>


                        <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${cenSaveDisabled}"/>
                                <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${cenModifyDisabled}"/></td></tr>


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
