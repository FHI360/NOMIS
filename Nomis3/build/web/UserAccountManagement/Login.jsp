<%-- 
    Document   : Login
    Created on : Nov 24, 2019, 10:04:30 AM
    Author     : smomoh
--%>
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>NOMIS - National OVC Management Information System </title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/bg.jpg);
	background-repeat: repeat-x;
}
-->
</style>
<script type="text/javascript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
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
<script type="text/javascript" src="js/Enrollmentjsfile.js"></script>
 <script language="javascript">
function setActionName(val)
{
    document.getElementById("actionName").value=val
}
 </script>
</head>

<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="445" border="0" align="center" cellpadding="0" cellspacing="0" class="boarder">
  <!--DWLayoutTable-->
  <tr>
    <td width="500" height="214">
  <html:form action="/login" method="post">
      <html:hidden property="actionName" styleClass="txtbox" styleId="actionName" />
      <table width="100%" border="0">
        <tr>
          <td colspan="2"><div align="center"><img src="images/logo.jpg" width="268" height="92" /></div></td>
        </tr>
        <tr>
          <td width="44%" class="formnames">Username:</td>
          <td width="56%"><html:text property="userName" styleClass="txtbox" styleId="userName" /></td>
        </tr>
        <tr>
          <td class="formnames">Password:</td>
          <td><html:password property="password" styleClass="txtbox" styleId="textfield2" /></td>
        </tr>
        
        <tr>
            <td><!--<input type="button" value="Test" onclick="getDbPath()"/>--></td>
          <td><html:image src="images/login.jpg" alt="Click to submit form" onclick="setActionName('login')"/></td>
        </tr>
        <tr>
            <td colspan="2"><label style="color:red;"><html:errors/> <logic:present name="failedLogin"> ${failedLogin}</logic:present></label> </td>
        </tr>
        <tr>
            <td colspan="2" align="right" style="font-size: 15px; font-weight: bold; color: green"><jsp:include page="../includes/VersionNumber.jsp"/></td>

        </tr>
            
      </table>
        </html:form>    </td>
  </tr>
</table>
</body>
</html>
