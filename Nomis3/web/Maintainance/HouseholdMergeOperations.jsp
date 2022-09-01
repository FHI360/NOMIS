<%-- 
    Document   : BeneficiaryRecordsManagement
    Created on : May 19, 2021, 12:34:37 PM
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
<title>Household merge operations</title>
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
        
        
      <td width="659" class="regsitertable" colspan="3">
          <!--enrollmentstatusmanagement-->
        <html:form action="/householdmergeoperation" method="POST">
                <html:hidden property="actionName" styleId="actionName"/>
                <table>
                    <tr><td align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                    
                      <tr><td>
                        <table>
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
                        </table>
                                
                    
                    </td></tr>
                      <tr>
                          <td>
                              <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="hherecordsForMerge">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th>
                                <th >CBO </th>
                                <th >Date of enrollment (yyyy-mm-dd)</th>
                                <th>HH Unique Id</th>
                                <%--<th>Caregiver Id</th>--%>
                                <th width="200">Household head name </th>
                                <th width="200">Current age </th>
                                <th >Sex(M/F)</th>
                                <th >Address</th>
                                <th >Phone</th>
                                <th >Keep</th>
                                <th >Delete</th>
                                                                
                </tr>           <logic:iterate name="hherecordsForMerge" id="hhe">
                                   <tr style="background-color: ${hhe.rowColor}">
                                       <td>${hhe.serialNo}</td>
                                       <td>${hhe.level2Ou.name}</td>
                                       <td>${hhe.level3Ou.name}</td>
                                       <td>${hhe.level4Ou.name}</td>
                                       <td>${hhe.cbo.cboName}</td>
                                       <td>${hhe.dateOfAssessment}</td>
                                       <td>${hhe.hhUniqueId}</td>
                                       <%--<td> </td>--%>
                                       <td>${hhe.prCaregiver.firstName} ${hhe.prCaregiver.surname}</td>
                                       <td>${hhe.prCaregiver.currentAge}</td>
                                       <td>${hhe.prCaregiver.sex}</td>
                                       <%--<td>${hhe.prCaregiver.dateOfBirth}</td>
                                       <td>${prCaregiver.hhe.village}</td>--%>
                                       <td>${hhe.address}</td>
                                       <td>${hhe.prCaregiver.phoneNumber}</td>
                                       
                                       <td width="7%">
                                        <html:radio property="keep" styleId="keep_${hhe.hhUniqueId}" value="${hhe.hhUniqueId}" onchange="disableControl(${hhe.hhUniqueId})"/>Keep
                                    </td>
                                    <td width="8%"><html:checkbox property="merge" styleId="${hhe.hhUniqueId}" value="${hhe.hhUniqueId}" />delete</td>
                                                                               
                                    </tr>
                               </logic:iterate>
            </logic:present>
        </table>
                          </td>
                      </tr>
              </table>
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
    </td>
  </tr>
</table>
  </body>
</html>
