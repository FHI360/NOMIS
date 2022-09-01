<%-- 
    Document   : NationalChildServiceForm
    Created on : Feb 10, 2020, 11:09:24 AM
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
<title>Child service form</title>
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
      <td width="659" class="paramPage">
        <html:form action="/nationalchildService" method="POST" styleId="serviceForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <%--<html:hidden property="beneficiaryId" styleId="beneficiaryId" />--%>
                                <html:hidden property="hhName" styleId="hhName" />
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">Vulnerable Child service form </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Child information</legend>
                                        <table>
                                            <tr>
                                                <td>Beneficiary type</td>
                                                <td colspan="3">
                                                    <html:select property="beneficiaryType" styleId="beneficiaryType" >
                                                        <html:option value="3">Child</html:option>
                                                        <html:option value="2">Household</html:option>
                                                    </html:select>

                                                </td>   
                                            </tr>
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
                                        <html:select style="width:150px;" property="ovcId" styleId="ovcId" onchange="setActionName('childDetails');forms[0].submit()">
                                                <html:option value="select">select...</html:option>
                                                <logic:present name="serviceOvcPerHouseholdList">
                                                    <logic:iterate name="serviceOvcPerHouseholdList" id="ovc">
                                                        <html:option value="${ovc.ovcId}">${ovc.firstName} ${ovc.surname} (${ovc.ovcId})</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>
                                            <td align="right">Date of enrollment </td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" onchange="generateUniqueId()" disabled="true"/>
                                            </td>
                                            <%--<td align="center"> 
                                                <label id="genId" style="color:green"><logic:present name="hracOvc">${hracOvc.ovcId}</logic:present> </label>
                                                    
                                                    </td>--%>
                                     </tr>
                                    
                                    <tr>
                                         <td align="right">Sex </td>
                                        <td >
                                            <html:select property="sex" styleId="sex" onchange="generateUniqueId()" disabled="true">
                                                <html:option value="select">select...</html:option>
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
                                        <td colspan="3">
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
                                            </fieldset>
                                        </td>
                                    </tr>
                                     
                                     <tr >
                  <td height="123" valign="top" colspan="4"><fieldset>
                        <legend class="fieldset">Services </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="762" height="17">
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">

			<tr>
                          <td width="20%">Date services provided: </td>
                          <td width="80%" colspan="2">
                              <html:text property="serviceDate" styleId="serviceDate" styleClass="smallfieldcellinput" disabled="${servicedatedisabled}" onchange="setActionName('serviceDetails'); forms[0].submit()" readonly="true"/> &nbsp;(mm/dd/yyyy)
                          </td>

                        </tr>
                        </table>
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                              <td width="21%" bgcolor="#CCCCCC" class="right">Health: </td>
                              <td bgcolor="#CCCCCC" colspan="2"></td>
                            
                          </tr>
                          <tr>
                            <td rowspan="16">&nbsp;</td>
                              <td><html:multibox property="healthServices" value="Health education" styleClass="smallfieldcellselect" styleId="serviceAccessed35"/></td>
                            <td>Health education</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Treatment of minor illnesses" styleClass="smallfieldcellselect" styleId="treatmentofminorillnesses"/></td>
                            <td>Treatment of minor illnesses</td>
                          </tr>
			
                          <tr>
                              <td><html:multibox property="healthServices" value="De-worming" styleClass="smallfieldcellselect" styleId="serviceAccessed38"/></td>
                              <td>Deworming </td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Insecticides treated bed net" styleClass="smallfieldcellselect" styleId="insecticide"/></td>
                            <td>Insecticides treated bed nets </td>
                          </tr>  
                          <tr>
                            <td><html:multibox property="healthServices" value="Water treatment" styleClass="smallfieldcellselect" styleId="serviceAccessed32"/></td>
                            <td>Water treatment </td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Wash" styleClass="smallfieldcellselect" styleId="wash"/></td>
                            <td>Water, Sanitation and Hygiene (WASH)</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Community HIV services (HTC/PMTCT)" styleClass="smallfieldcellselect" styleId="communityhivservices"/></td>
                            <td>Community HIV services ? HTC/PMTCT</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="HIV services referral (HTC/EID)" styleClass="smallfieldcellselect" styleId="healthreferralHCTEID"/></td>
                            <td>HIV services referral ? HTC/EID</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="HIV services referral-ART" styleClass="smallfieldcellselect" styleId="healthreferralART"/></td>
                            <td>HIV services referral ? ART</td>
                          </tr>
                          <tr>
                           <td><html:multibox property="healthServices" value="Access for HIV care" styleClass="smallfieldcellselect" styleId="accessforHIV"/></td>
                            <td>HIV care and support</td>
                          </tr>
                          <tr>
                           <td width="4%"><html:multibox property="healthServices" value="Community TB symptom screening" styleClass="smallfieldcellselect" styleId="communityTBsymptomscreening"/></td>
                            <td>TB symptom screening </td>
                          </tr>
                          <tr>
                              <td width="4%"><html:multibox property="healthServices" value="TB services referral (Diagnosis-DOTS)" styleClass="smallfieldcellselect" styleId="tbservicesreferral"/></td>
                            <td>TB services referral (Diagnosis, DOTS) </td>
                          </tr>
                          <tr><%-- --%>
                              <td width="4%"><html:multibox property="healthServices" value="Adolescent HIV prevention and sexual reproductive health services" styleClass="smallfieldcellselect" styleId="hivprev" disabled="${hivprevdisabled}"/></td>
                            <td>Adolescent HIV prevention and sexual reproductive health services </td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="Health referral" styleClass="smallfieldcellselect" styleId="healthreferral"/></td>
                            <td>Health referral</td>
                          </tr>
                          
                          <tr>
                              <td><input type="checkbox" name="chkHealthOther" class="smallfieldcellselect" id="growthOther"/>
                                  </td>
                            <td>Other (specify) <html:text property="healthOther" styleClass="fieldcellinput" styleId="serviceAccessed37"/></td>
                          </tr>
                          
                        </table>

                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Nutrition: </td>
                            <td bgcolor="#CCCCCC" colspan="2"> </td>
                            
                          </tr>
                          <tr>
                            <td rowspan="8">&nbsp;</td>
                            <td><html:multibox property="nutritionalServices" value="Nutrition education and counselling" styleClass="smallfieldcellselect" styleId="serviceAccessed21"/></td>
                            <td>Nutrition education</td>
                          </tr>
                          <tr>
                            
                            <td><html:multibox property="nutritionalServices" value="Food & nutritional supplements" styleClass="smallfieldcellselect" styleId="serviceAccessed22"/></td>
                            <td>Food and Nutritional supplements </td>
                          </tr>
                          <tr>
                           <tr>
                            <td><html:multibox property="healthServices" value="Vitamin A-Zinc and Iron suplement" styleClass="smallfieldcellselect" styleId="serviceAccessed36"/></td>
                            <td>Vitamin A, Zinc and Iron suplement</td>
                          </tr>
                            <td><html:multibox property="nutritionalServices" value="NACS" styleClass="smallfieldcellselect" styleId="nacs"/></td>
                            <td>Nutrition assessment, counselling and support (NACS)</td>
                          </tr>
                          <tr>
                              <td><html:multibox property="nutritionalServices" value="Growth monitoring" styleClass="smallfieldcellselect" styleId="serviceAccessed23" onclick="monitorGrowth()"/></td>
                              <td>Growth monitoring   <label style="margin-left:20px;">Weight(kg) <html:text property="currentWeight" styleClass="shortfieldcellinput" styleId="currentWeight" disabled="${weightDisabled}" /></label>
                                  <label style="margin-left:20px;">Height(cm) </label><html:text property="currentHeight"  styleClass="shortfieldcellinput" styleId="currentHeight" disabled="${heightDisabled}" /></td>
                          </tr>
                          <%--<tr>
                            <td><html:multibox property="nutritionalServices" value="Water-Sanitation and Hygiene (WASH)" styleClass="smallfieldcellselect" styleId="wash"/></td>
                            <td>Water, Sanitation and Hygiene (WASH) </td>
                          </tr>--%>
                          <tr>
                            <td><html:multibox property="nutritionalServices" value="Nutrition referral" styleClass="smallfieldcellselect" styleId="trainingonhousehold"/></td>
                            <td>Nutrition referral ? malnutrition</td>
                          </tr>
                          <tr>
                              <td><input type="checkbox" name="chkNutritionOther" class="smallfieldcellselect" id="nutritionOther"/></td>
                            <td>Others (specify)  <html:text property="nutritionOther" styleClass="fieldcellinput" styleId="serviceAccessed24"/></td>
                          </tr>
                        </table>

                         <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Shelter and care: </td>
                            <td bgcolor="#CCCCCC" colspan="2"></td>
                          </tr>
                          <tr>
                            <td rowspan="6">&nbsp;</td>
                            <td width="4%"><html:multibox property="shelterServices" value="Re-integration into Family" styleClass="smallfieldcellselect" styleId="serviceAccessed62"/></td>
                            <td>Re-integration into family </td>
                          </tr>
                          <tr>
                              <td><html:multibox property="shelterServices" value="Provision/repair of accommodation" styleClass="smallfieldcellselect" styleId="repareofaccommodation"/></td>
                            <td>Provision/repair of accommodation </td>
                          </tr>
                          <tr>
                              <td><html:multibox property="shelterServices" value="Foster parenting" styleClass="smallfieldcellselect" styleId="fosterparent"/></td>
                            <td>Foster parenting </td>
                          </tr>
                          <tr>
                              <td><html:multibox property="shelterServices" value="Clothing support" styleClass="smallfieldcellselect" styleId="serviceAccessed63"/></td>
                            <td>Clothing support </td>
                          </tr>
                          <tr>
                              <td><html:multibox property="shelterServices" value="Referral for shelter and care services" styleClass="smallfieldcellselect" styleId="serviceAccessed63"/></td>
                            <td>Referral </td>
                          </tr>
                          <tr>
                              <td>
                                  <input type="checkbox" name="chkShelterOther" class="smallfieldcellselect" id="shelterOther"/>
                                  </td>
                            <td>Other (specify) <html:text property="shelterOther" styleClass="fieldcellinput" styleId="serviceAccessed65"/></td>
                          </tr>
                        </table>




                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Education: </td>
                            <td bgcolor="#CCCCCC" colspan="2"></td>
                          </tr>
                          <tr>
                            <td rowspan="10">&nbsp;</td>
                            <td><html:multibox property="educationalServices" value="Advocacy for school enrolment" styleClass="smallfieldcellselect" styleId="holisticscholarship"/></td>
                            <td>School enrolment/re-enrolment </td>
                          </tr>
                          <tr>
                            
                            <td width="4%"><html:multibox property="educationalServices" value="Advocacy for waiver of school fees" styleClass="smallfieldcellselect" styleId="serviceAccessed41"/></td>
                            <td width="75%">Waiver of school fees </td>
                          </tr>
                          <tr>
                            <td><html:multibox property="educationalServices" value="Provision of school materials" styleClass="smallfieldcellselect" styleId="serviceAccessed42"/></td>
                            <td>Provision of school materials/uniform</td>
                          </tr>
                          <tr>
                              <td><html:multibox property="educationalServices" value="School visit" styleClass="smallfieldcellselect" styleId="serviceAccessed44"/></td>
                            <td>School visit </td>
                          </tr>
                          <tr>
                              <td><html:multibox property="educationalServices" value="School performance assessment" styleClass="smallfieldcellselect" styleId="serviceAccessed45"/></td>
                            <td>School performance assessment </td>
                          </tr>
                          <tr>
                          <tr>
                              <td><html:multibox property="educationalServices" value="Holistic scholarship" styleClass="smallfieldcellselect" styleId="holisticscholarship"/></td>
                            <td>Holistic scholarship </td>
                          </tr>
                          <tr> 
                              <td> </td>
                            <td>Did the child miss school for more than 5 days in the last 1 month?
                            
                                <html:select property="childMissedSchool" styleClass="smallfieldcellselect" styleId="childMissedSchool" disabled="${childMissSchoolDisabled}">
                                    <html:option value="N/A">N/A</html:option>
                                    <html:option value="No">No</html:option>
                                    <html:option value="Yes">Yes</html:option>
                                </html:select>
                                </td>
                            
                          </tr>
                          <tr>
                              <td><html:multibox property="educationalServices" value="Referral for educational services" styleClass="smallfieldcellselect" styleId="educationalReferral"/></td>
                            <td>Referral</td>
                          </tr>
                          <tr>
                              <td><input type="checkbox" name="chkEducationOther" class="smallfieldcellselect" id="educationOther"/></td>
                            <td>Other (specify) <html:text property="educationOther" styleClass="fieldcellinput" styleId="serviceAccessed46"/></td>
                          </tr>
                        </table>

                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                        <tr>
                          <td width="21%" bgcolor="#CCCCCC" class="right">Psychosocial Support: </td>
                          <td bgcolor="#CCCCCC" colspan="2"></td>
                        </tr>
                        <tr>
                          <td rowspan="4">&nbsp;</td>
                          <td width="4%"><html:multibox property="psychoServices" value="Counselling support" styleClass="smallfieldcellselect" styleId="serviceAccessed11"/></td>
                          <td width="75%">Counselling support </td>
                        </tr>
                        <tr>
                          
                          <td><html:multibox property="psychoServices" value="Recreational activity" styleClass="smallfieldcellselect" styleId="serviceAccessed12"/></td>
                          <td>Recreational activity (e.g kids club) </td>
                        </tr>
                        <tr>
                            <td><html:multibox property="psychoServices" value="Life skill support" styleClass="smallfieldcellselect" styleId="serviceAccessed13"/></td>
                          <td>Life skill support </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" name="chkPsychosocialOther" class="smallfieldcellselect" id="psychosocialOther"/>
                            </td>
                          <td>Others (specify) <html:text property="psychosocialOther" styleClass="fieldcellinput" styleId="serviceAccessed14"/></td>
                        </tr>
                      </table>

                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Child protection: </td>
                            <td bgcolor="#CCCCCC" colspan="2"></td>
                          </tr>
                          <tr>
                            <td rowspan="7">&nbsp;</td>
                            <td width="4%"><html:multibox property="protectionServices" value="Legal services" styleClass="smallfieldcellselect" styleId="serviceAccessed51"/></td>
                            <td width="75%">Legal services </td>
                          </tr>
                          <tr>
                              
                              <td><html:multibox property="protectionServices" value="Succession planning" styleClass="smallfieldcellselect" styleId="serviceAccessed53"/></td>
                            <td>Succession planning </td>
                          </tr>
                          <tr>
                            
                            <td><html:multibox property="protectionServices" value="Birth registration" styleClass="smallfieldcellselect" styleId="serviceAccessed52"/></td>
                            <td>Birth registration </td>
                          </tr>
                          <tr>  
                              <td></td>
                            <td >Did Child experience any form of abuse, exploitation or neglect in the last 1 month?
                            
                                <html:select property="childAbused" styleClass="smallfieldcellselect" styleId="childAbused">
                                    <html:option value="0">N/A</html:option>
                                    <html:option value="1">No</html:option>
                                    <html:option value="2">Yes</html:option>
                                </html:select>
                                </td>
                            
                          </tr>
                          <tr>                            
                            <td></td>
                            <td> If yes, was the child Linked to Government for post-violence services? 
                            <html:select property="childLinkedToGovt" styleClass="smallfieldcellselect" styleId="childLinkedToGovt">
                                    <html:option value="0">N/A</html:option>
                                    <html:option value="1">No</html:option>
                                    <html:option value="2">Yes</html:option>
                                </html:select>
                            </td>
                          </tr>
                          <tr>
                            <td><html:multibox property="protectionServices" value="Referral for protection services" styleClass="smallfieldcellselect" styleId="protectionReferral"/></td>
                            <td>Referral </td>
                          </tr>
                          <tr>
                              <td>
                                  <input type="checkbox" name="chkProtectionOther" class="smallfieldcellselect" id="protectionOther"/>
                                  </td>
                            <td>Other (specify) <html:text property="protectionOther" styleClass="fieldcellinput" styleId="serviceAccessed54"/></td>
                          </tr>
                        </table>




                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr>
                            <td width="21%" bgcolor="#CCCCCC" class="right">Economic Strengthening: </td>
                            <td bgcolor="#CCCCCC" colspan="2"></td>
                          </tr>
                          <tr>
                            <td rowspan="6">&nbsp;</td>
                            <td width="4%"><html:multibox property="economicServices" value="Financial Education" styleClass="smallfieldcellselect" styleId="serviceAccessed71"/></td>
                            <td width="75%">Financial Education </td>
                          </tr>
                          <tr>
                            
                            <td><html:multibox property="economicServices" value="Micro-finance (savings and loans)" styleClass="smallfieldcellselect" styleId="microcreditsupport"/></td>
                            <td>Micro-finance (savings and loans) </td>
                          </tr>
                          <tr>
                            <td><html:multibox property="economicServices" value="Vocational/apprenticeship training" styleClass="smallfieldcellselect" styleId="Vocationalapprenticeshiptraining"/></td>
                            <td>Vocational/apprenticeship training </td>
                          </tr>

                          <tr>
                              <td><html:multibox property="economicServices" value="Livelihood opportunity" styleClass="smallfieldcellselect" styleId="serviceAccessed73"/></td>
                            <td>Livelihood opportunity</td>
                          </tr>
                          <tr>
                              <td><html:multibox property="economicServices" value="Business grant" styleClass="smallfieldcellselect" styleId="businessgrant"/></td>
                            <td>Business grant</td>
                          </tr>
                          <tr>
                              <td>
                                  <input type="checkbox" name="chkEconomicOther" class="smallfieldcellselect" id="economicOther"/>
                                  </td>
                            <td>Other (specify) <html:text property="economicOther" styleClass="fieldcellinput" styleId="serviceAccessed75"/></td>
                          </tr>




                        </table>                        </td>
                      </tr>
                  </table>
                  </fieldset>






                  </td>
                </tr>
                                    <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${serviceSaveDisabled}"/>
                                            <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${serviceModifyDisabled}"/>
                                        <html:submit value="Delete" onclick="return confirmAction('delete')" disabled="${serviceModifyDisabled}"/></td></tr>
                                    
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