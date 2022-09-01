<%-- 
    Document   : NationalHouseholdServiceForm
    Created on : Mar 3, 2020, 9:23:36 PM
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
<title>Household service form</title>
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
        $("#serviceDate").datepicker();
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
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
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
    <td width="10" >&nbsp;</td>
      <td width="659" class="regsitertable">
        <html:form action="/nationalhouseholdservice" method="POST" styleId="serviceForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">Household service form </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset>
                        <legend class="fieldset">Personal Information  </legend>
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
                                        <td >Caregiver list </td>
                                        <td >
                                        <html:select style="width:150px;" property="beneficiaryId" styleId="beneficiaryId" onchange="setActionName('ahmDetails');forms[0].submit()">
                                                <html:option value="select">select...</html:option>
                                                <logic:present name="ahmList">
                                                    <logic:iterate name="ahmList" id="ahm">
                                                        <html:option value="${ahm.beneficiaryId}">${ahm.firstName} ${ahm.surname} (${ahm.beneficiaryTypeObject.name}) </html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>
                                            <td align="center"> 
                                                
                                                    
                                                    </td>
                                     </tr>
                                    
                                     <tr>
                                        <td align="right">Date of enrollment </td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" onchange="generateUniqueId()" disabled="true"/>
                                            </td>
                                            <td align="right">Sex </td>
                                        <td >
                                            <html:select property="sex" styleId="sex" onchange="generateUniqueId()" disabled="true">
                                                <html:option value="select">select...</html:option>
                                                <html:option value="M">Male</html:option>
                                                <html:option value="F">Female</html:option>
                                            </html:select>
                                            </td>
                                     </tr>
                                     <tr>
                                         
                                        <td align="right">Phone number</td>
                                        <td >
                                            <html:text property="phoneNumber" styleId="phoneNumber" disabled="true"/>
                                            </td>
                                        <td align="right">HIV status</td>
                                        <td>
                                            <html:select property="hivStatus" styleId="hivStatus" onchange="setEnrolledOnTreatmentStatus(this.value)" disabled="true">
                                                <logic:present name="mainHivStatus">
                                                    <logic:iterate name="mainHivStatus" id="hivStatus">
                                                        <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                    
                                            </html:select>
                                        </td>
                                     </tr>
                                     </table>
                        <%--<table width="100%" class="regsitertable">
                            
                        <tr><td width="15%" valign="top" class="right">Household S/No.</td>
                   
                            <td>
                                <html:text property="hhSerialNo"  styleClass="smallfieldcellinput" styleId="hhSerialNo"  onblur="submitForm('caregiverList','hhFormId')" onkeyup="constructUniqueId(this.value)"/> </td>
                                <td colspan="4"><label id="uniqueIdLabel"> ${hhServiceUniqueId}</label></td>
                        </tr>
                        
                        <tr>
                          <td valign="top" class="right">Caregiver name: </td>
                              <td>
                                  <html:select property="beneficiaryId" styleClass="fieldcellinput"  styleId="beneficiaryId" onchange="submitForm('caregiverDetails','hhFormId')">
                                      <html:option value=""> </html:option>
                                      <logic:present name="caregiverList">
                                      <logic:iterate name="caregiverList" id="cgiver">
                                          <html:option value="${cgiver.caregiverId}">${cgiver.caregiverFirstname} ${cgiver.caregiverLastName} </html:option>
                                      </logic:iterate>
                                      </logic:present>
                                  </html:select>  </td>
                              <td>Sex</td><td><html:text property="careiverGender" styleClass="fieldcellinput" styleId="careiverGender" readonly="true" style="width:70px;" /> </td>
                              <td>Age</td><td><html:text property="caregiverAge" styleClass="shortfieldcellinput" styleId="caregiverAge" readonly="true"  /></td>
                            </tr>
                            
                            <tr>
                          <td class="right">Current HIV status </td>
                              <td>
                                  <html:text property="currentHivStatus" styleId="currentHivStatus" styleClass="fieldcellinput" disabled="true"/>
                              </td>
                              <td> </td><td></td>
                              <td> </td><td> </td>
                            </tr>
                       
			</table>--%>
                      </fieldset>
                                        </td>
                                    </tr>
                                     
                                     <tr >
                  <td height="123" valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset">Services </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="762" height="17">
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">

			<tr>
                          <td width="20%">Date services provided: </td>
                          <td width="80%" colspan="2">
                              <html:text property="serviceDate" styleId="serviceDate" styleClass="smallfieldcellinput" onchange="setActionName('hhserviceDetails'); forms[0].submit()" readonly="true"/>&nbsp;(mm/dd/yyyy)
                          </td>

                        </tr>
                        </table>
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                              <td width="21%" bgcolor="#CCCCCC" class="right">Health </td>
                              <td colspan="2" bgcolor="#CCCCCC">&nbsp;</td>
                              
                          </tr>
                          <tr>
                              <td rowspan="12">&nbsp;</td>
                            <td width="4%"><html:multibox property="healthServices" value="Health education" styleClass="smallfieldcellselect" styleId="serviceAccessed33"/></td>
                            <td>Health education </td>
                          </tr>
                          <tr>
                            <td width="4%"><html:multibox property="healthServices" value="Treatmen_of_minor_illness" styleClass="smallfieldcellselect" styleId="insecticide"/></td>
                            <td>Treatment of minor illness </td>
                          </tr>
                          <tr>
                            <td width="4%"><html:multibox property="healthServices" value="Insecticides treated bed net" styleClass="smallfieldcellselect" styleId="insecticide"/></td>
                            <td>Insecticides treated bed nets </td>
                          </tr>
			   <tr>
                            <td width="4%"><html:multibox property="healthServices" value="Water treatment" styleClass="smallfieldcellselect" styleId="serviceAccessed32"/></td>
                            <td>Water treatment </td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Water-Sanitation and Hygiene (WASH)" styleClass="smallfieldcellselect" styleId="wash"/></td>
                            <td>Water, Sanitation and Hygiene (WASH) </td>
                          </tr>
                          <tr>

                              <td width="4%"><html:multibox property="healthServices" value="Community HIV services (HTC/PMTCT)" styleClass="smallfieldcellselect" styleId="communityhivservices"/></td>
                            <td>Community HIV services (HTS/PMTCT)</td>
                          </tr>
                          <tr>
                          <tr>
                              
                              <td width="4%"><html:multibox property="healthServices" value="HIV services referral (HTC/PMTCT)" styleClass="smallfieldcellselect" styleId="hivHealthreferral"/></td>
                            <td>HIV services referral (HTS/PMTCT/ART)</td>
                          </tr>                         
                          <tr>

                              <td width="4%"><html:multibox property="healthServices" value="HIV care and support" styleClass="smallfieldcellselect" styleId="accessforHIV"/></td>
                            <td>HIV care and support </td>
                          </tr>
                          <tr>

                              <td width="4%"><html:multibox property="healthServices" value="Community TB symptom screening" styleClass="smallfieldcellselect" styleId="communityTBsymptomscreening"/></td>
                            <td>Community TB symptom screening </td>
                          </tr>
                          <tr>

                              <td width="4%"><html:multibox property="healthServices" value="TB services referral (Diagnosis-DOTS)" styleClass="smallfieldcellselect" styleId="tbservicesreferral"/></td>
                            <td>TB services referral (Diagnosis, DOTS) </td>
                          </tr>
                          <tr>
                              
                              <td><html:multibox property="healthServices" value="Health referral" styleClass="smallfieldcellselect" styleId="healthreferral"/></td>
                            <td>Health referral</td>
                          </tr>
                          <%--<tr>
                              <td><input type="checkbox" name="chkHealthOther" class="smallfieldcellselect" id="growthOther"/>
                                  </td>
                            <td>Other (specify) <html:text property="healthOther" styleClass="fieldcellinput" styleId="serviceAccessed37"/></td>
                          </tr>--%>
                        </table>

                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Nutrition </td>
                            <td width="4%" colspan="2" bgcolor="#CCCCCC">&nbsp;</td>
                            
                          </tr>
                          <tr>
                            <td rowspan="11">&nbsp;</td>
                            <td width="4%"><html:multibox property="healthServices" value="Nutrition education and counselling" styleClass="smallfieldcellselect" styleId="serviceAccessed21"/></td>
                            <td width="75%">Nutrition education and counselling</td>
                          </tr>
                          <tr>
                            
                            <td><html:multibox property="healthServices" value="Vitamin A-Zinc and Iron suplement" styleClass="smallfieldcellselect" styleId="serviceAccessed22"/></td>
                            <td>Vitamin A, Zinc, Iron & supplement </td>
                          </tr>
                          <tr>
                            
                            <td><html:multibox property="healthServices" value="Food & nutritional supplements" styleClass="smallfieldcellselect" styleId="serviceAccessed22"/></td>
                            <td>Food and Nutritional supplements </td>
                          </tr>
                          
                          <tr>
                            <td><html:multibox property="healthServices" value="HHnutritionsssessmentCounsellingandsupport(NACS)services" styleClass="smallfieldcellselect" styleId="nacs"/></td>
                            <td>HH nutrition assessment, counselling and support (NACS) services</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Infant and young child feeding services" styleClass="smallfieldcellselect" styleId="infantFeeding"/></td>
                            <td>Support group (IYCF)</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Home garden" styleClass="smallfieldcellselect" styleId="homegarden"/></td>
                            <td>Home gardening support/training</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Food_preservation" styleClass="smallfieldcellselect" styleId="fooddemonstration"/></td>
                            <td>Food preservation and storage</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Food demonstration" styleClass="smallfieldcellselect" styleId="fooddemonstration"/></td>
                            <td>Food demonstration/preparation</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Household food security training" styleClass="smallfieldcellselect" styleId="trainingonhousehold"/></td>
                            <td>Household food security training</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Food and Nutrition training" styleClass="smallfieldcellselect" styleId="foodandnutritiontraining"/></td>
                            <td>Food and Nutrition training</td>
                          </tr>
                          <tr>
                              <td><html:multibox property="healthServices" value="Nutrition referral" styleClass="smallfieldcellselect" styleId="serviceAccessed36"/></td>
                            <td>Referral </td>
                          </tr>
                          </table>

                         <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Shelter and care</td>
                            <td width="4%" colspan="2" bgcolor="#CCCCCC"></td>
                            
                          </tr>
                          <tr>
                            <td rowspan="3">&nbsp;</td>
                            <td width="4%"><html:multibox property="safetyServices" value="Provision/repair of accommodation" styleClass="smallfieldcellselect" styleId="serviceAccessed61"/></td>
                            <td width="75%">Provision/repair of accommodation </td>
                          </tr>
                          <tr>
                              <td><html:multibox property="safetyServices" value="Clothing support" styleClass="smallfieldcellselect" styleId="serviceAccessed63"/></td>
                            <td>Clothing support </td>
                          </tr>
                          <tr>
                              <td><html:multibox property="safetyServices" value="Shelter referral" styleClass="smallfieldcellselect" styleId="serviceAccessed63"/></td>
                            <td>Referral </td>
                          </tr>                    
                        </table>




                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Education</td>
                            <td colspan="2" bgcolor="#CCCCCC"></td>
                            
                          </tr>
                          <tr>
                            <td rowspan="3">&nbsp;</td>
                            <td><html:multibox property="schoolServices" value="Education awareness and engagement" styleClass="smallfieldcellselect" styleId="holisticscholarship"/></td>
                            <td>Child education awareness and sensitization</td>
                          </tr>
                          <%--<tr>
                            <td><html:multibox property="educationalServices" value="Sensitization for child school enrolment/re-enrolment " styleClass="smallfieldcellselect" styleId="schoolenrolmentsensitization"/></td>
                            <td>Sensitization for child school enrolment/re-enrolment </td>
                          </tr>--%>
                          <tr>
                            <td width="4%"><html:multibox property="schoolServices" value="Education referral" styleClass="smallfieldcellselect" styleId="serviceAccessed41"/></td>
                            <td width="75%">Referral </td>
                          </tr>
                                                    
                        </table>

                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                        <tr>
                          <td width="21%" bgcolor="#CCCCCC" class="right">Psychosocial Support </td>
                          <td width="75%" colspan="2" bgcolor="#CCCCCC"> </td>
                        </tr>
                        <tr>
                          <td rowspan="4">&nbsp;</td>
                          <td width="4%"><html:multibox property="safetyServices" value="Counselling support" styleClass="smallfieldcellselect" styleId="serviceAccessed11"/></td>
                          <td width="75%">Counselling support </td>
                        </tr>
                        <tr>
                          
                          <td><html:multibox property="safetyServices" value="Parenting skills" styleClass="smallfieldcellselect" styleId="serviceAccessed12"/></td>
                          <td>Parenting skills </td>
                        </tr>
                        <tr>
                            <td><html:multibox property="safetyServices" value="Caregivers forum" styleClass="smallfieldcellselect" styleId="serviceAccessed13"/></td>
                          <td>Caregivers forum </td>
                        </tr>
                        <tr>
                            <td><html:multibox property="safetyServices" value="Psychosocial referral" styleClass="smallfieldcellselect" styleId="serviceAccessed13"/></td>
                          <td>Referral </td>
                        </tr>
                      </table>

                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Protection </td>
                            <td width="75%" colspan="2" bgcolor="#CCCCCC"></td>
                            
                          </tr>
                          <tr>
                            <td rowspan="5" >&nbsp;</td>
                            <td width="4%"><html:multibox property="safetyServices" value="Legal services" styleClass="smallfieldcellselect" styleId="serviceAccessed51"/></td>
                            <td width="75%">Access to legal services </td>
                          </tr>
                          <tr>
                              
                              <td><html:multibox property="safetyServices" value="Succession planning" styleClass="smallfieldcellselect" styleId="serviceAccessed53"/></td>
                            <td>Succession planning </td>
                          </tr>
                          <tr>
                            
                            <td><html:multibox property="safetyServices" value="Birth registration" styleClass="smallfieldcellselect" styleId="serviceAccessed52"/></td>
                            <td>Birth registration awareness</td>
                          </tr>
                          <tr>
                            
                            <td><html:multibox property="safetyServices" value="Awareness of gender issues" styleClass="smallfieldcellselect" styleId="serviceAccessed52"/></td>
                            <td>Awareness of gender issues/norms </td>
                          </tr>
                          <tr>
                            
                            <td><html:multibox property="safetyServices" value="Protection referral" styleClass="smallfieldcellselect" styleId="serviceAccessed52"/></td>
                            <td>Referral </td>
                          </tr>
                        </table>




                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="20%" bgcolor="#CCCCCC" class="right">Economic Strengthening </td>
                            <td width="75%" colspan="2" bgcolor="#CCCCCC"> </td>
                          </tr>
                          <tr>
                            <td rowspan="10"></td>
                            <td width="4%"><html:multibox property="stableServices" value="micro finance support" styleClass="smallfieldcellselect" styleId="microfinancesupport"/></td>
                            <td>Micro finance support(Grants, savings and loans) </td>
                          </tr>
                          <tr>
                                                       
                          </tr>
                          <tr>
                            <td width="4%"><html:multibox property="stableServices" value="Vocational/apprenticeship training" styleClass="smallfieldcellselect" styleId="Vocationalapprenticeshiptraining"/></td>
                            <td>Vocational/apprenticeship training </td>
                          </tr>

                          <tr>
                              <td width="4%"><html:multibox property="stableServices" value="Livelihood opportunity" styleClass="smallfieldcellselect" styleId="livelihoodopportunity"/></td>
                            <td>Livelihood support/opportunity</td>
                          </tr>
                          <tr>
                              <td width="4%"><html:multibox property="stableServices" value="Financial Education" styleClass="smallfieldcellselect" styleId="Financial Education"/></td>
                            <td>Financial Education</td>
                          </tr>
                          <tr>
                              <td width="4%"><html:multibox property="stableServices" value="Linkage to public sector scheme(s)" styleClass="smallfieldcellselect" styleId="serviceAccessed73"/></td>
                            <td>Linkage to public sector scheme(s)</td>
                          </tr>
                          <tr>
                              <td width="4%"><html:multibox property="stableServices" value="Linkage to cash transfer scheme" styleClass="smallfieldcellselect" styleId="serviceAccessed73"/></td>
                            <td>Linkage to cash transfer scheme</td>
                          </tr>
                          <tr>
                              <td width="4%"><html:multibox property="stableServices" value="Private sector linkage(s)" styleClass="smallfieldcellselect" styleId="Privatesectorlinkage(s)"/></td>
                            <td>Private sector linkage(s)</td>
                          </tr>
                          <tr><!--value="Savings and Internal Lending Community (SILC)"-->
                              <td width="4%"><html:multibox property="stableServices" value="SILC" styleClass="smallfieldcellselect" styleId="silc"/></td>
                            <td>Savings and Internal Lending Community (SILC)</td>
                          </tr>
                          <tr>
                              <td width="4%"><html:multibox property="stableServices" value="Economic strenghtening referral" styleClass="smallfieldcellselect" styleId="Economicstrenghteningreferral"/></td>
                            <td>Referral</td>
                          </tr>
                        </table>                        </td>
                      </tr>
                  </table>
                  </fieldset>






                  </td>
                </tr>
                                    <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${nhhsSaveDisabled}"/>
                                            <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${nhhsModifyDisabled}"/>
                                        <html:submit value="Delete" onclick="return confirmAction('delete')" disabled="${nhhsModifyDisabled}"/></td></tr>
                                    
                                    <%--<tr><td colspan="4" style="height: 400px;">&nbsp;</td></tr>--%> 
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
