<%-- 
    Document   : AdminUser
    Created on : Mar 19, 2021, 9:20:37 PM
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
<title>Administrator setup</title>
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
			
function submitForm(requiredAction,formId)
{
       setActionName(requiredAction)
       document.getElementById(formId).submit()
       return true
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
                        <div> </div>
                        
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
          
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="paramPage">
        <html:form action="/adminuser">
    <html:errors/>
    <html:hidden property="actionName" styleId="actionName" />
    <center><br/>
        <fieldset style="margin: 10px;">
<table  style=" font-size: 12px;">
    <tr><td colspan="4"> &nbsp;</td><td> </td></tr>
    
    <tr><td align="right">User name</td>
        <td align="left"> 
            <html:text property="username" styleId="username" styleClass="textField" style="width:200px;" maxlength="25"/>
                      <td align="right">User role</td>
        <td align="left"> 
            <html:select property="userrole" styleId="userrole" styleClass="textField" style="width:205px;"> 
                <html:option value="superuserxx">Super user</html:option>
            </html:select>
        </td>
    </tr>
    <tr></td><td align="right">Password</td>
        
            <td align="left"> <html:password property="password" styleId="password" styleClass="textField" maxlength="25" style="width:200px;"/> </td>

                     <td align="right">Confirm password</td><td> <html:password property="confirmPwd" styleId="confirmPwd" styleClass="textField" maxlength="25" style="width:200px;"/> </td>
    </tr>
    <tr><td align="right" >First name</td>
        <td align="left"> 
            <html:text property="firstname" styleId="firstname" styleClass="textField" style=" width:200px;" maxlength="25"/>

                      </td>
                      <td align="right">Surname</td>
                      <td align="left"> <html:text property="surname" styleId="surname" styleClass="textField" style="width:200px;" maxlength="25" /> </td>
    </tr>
    
    <tr>
        <td align="right">User group</td>
        <td align="left"> 
            <html:select property="usergroup" styleId="usergroup" styleClass="textField" style="width:205px;"> 
                <html:option value="superusergrpx">Super user group</html:option>
                
            </html:select>
        </td>
        <td align="right">Account status</td>
        <td align="left"> 
            <html:select property="accountStatus" styleId="accountStatus" styleClass="textField" style="width:205px;"> 
                <html:option value="enabled">Enabled</html:option>
            </html:select>
        </td>
    </tr>  
    
        
        <tr>
                  <td align="center" colspan="4">
                      <logic:present name="userList">
                          <div align="center" style="width:720px; max-height:200px; overflow:scroll">
                              <table width="700" border="1" bordercolor="#FFFFFF" style=" background-color: lightgray; border-collapse: collapse" class="regsitertable">
                          <tr><td>User name </td><td>User role </td><td>Date created </td><td>Last modified date</td><td>edit </td><td>delete</td>
                              </tr>
                              <logic:present name="userList">
                                  <logic:iterate name="userList" id="user">
                                      <tr><td>${user.username} </td><td>${user.userroleId} </td><td>${user.dateCreated} </td>
                                          <td>${user.lastModifiedDate} </td><td> </td>
                                      <td></td>
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                      </table>
                    </div>
                    </logic:present>
                  </td>
            </tr>
            <tr><td colspan="4">Environment information</td></tr>
            <tr>
                  <td align="center" colspan="4">
                      <logic:present name="userList">
                          <div align="center" style="width:720px; max-height:200px; overflow:scroll">
                              <table width="700" border="1" bordercolor="#FFFFFF" style=" background-color: lightgray; border-collapse: collapse" class="regsitertable">
                                  <%--<tr><td colspan="2">Update database connection information</td>
                                      <td colspan="2">
                                          <html:checkbox property="updateConnectionInfo" styleId="updateConnectionInfo"  value="1" />
                                      </td></tr>--%>
                                  <tr><td>Tomcat root directory </td><td colspan="3"><html:text property="tomcatRootLocation" styleId="tomcatRootLocation" styleClass="textField" style="width:400px;"/> </td></tr>
                                  <tr><td>Resource location </td><td colspan="3"><html:text property="resourceLocation" styleId="resourceLocation" styleClass="textField" style="width:400px;"/> </td></tr>
                                  <%--<tr><td>Database URL </td><td colspan="3"><html:text property="dbUrl" styleId="dbUrl" styleClass="textField" style="width:400px;"/> </td></tr>
                                  <tr><td>User name </td><td colspan="3"><html:text property="dbUsername" styleId="dbUsername" styleClass="textField" style="width:400px;" maxlength="25" /> </td></tr>    
                                  <tr><td>Password </td><td colspan="3"><html:password property="dbPassword" styleId="dbPassword" styleClass="textField" style="width:400px;" maxlength="25" /> </td></tr> --%>   
                      </table>
                    </div>
                    </logic:present>
                  </td>
            </tr>
<tr> <td colspan="3" align="center"><%--disabled="${userSaveDisabled}"--%>
    <html:submit style="width:75px; height: 30px;" styleId="save" value="Save"  onclick="return confirmAction('save');" disabled="${adminUserSaveDisabled}"/>
    

</td></tr>
</table>
    </fieldset>
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
  </body>
</html>

