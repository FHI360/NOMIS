<%-- 
    Document   : SiteSetup
    Created on : Dec 1, 2019, 9:46:40 PM
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
<title>Site setup</title>
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
                        <div><jsp:include page="../Navigation/AdministrationLink.jsp"/></div>
                        <%--<jsp:include page="../Navigation/EnvironmentSetupLinkPage.jsp"/>--%>
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
        <html:form action="/sitesetup">
    <html:errors/>
    <html:hidden property="actionName" styleId="actionName" />
    <center><br/>
        <fieldset style="margin: 10px;">
<table  style=" font-size: 12px;">
    <tr><td colspan="3" align="center"> <label style="font-size: 14px; font-weight: bold; text-align: center">Site setup form</label></td><td> </td></tr>
    <tr><td><span style="margin-left:50px;">User</span></td><td> 
            <%--onchange="setActionName('stateList'); forms[0].submit()"--%>
            <html:select property="userName" styleId="userName" styleClass="textField" style="margin-left: 10px; width:300px;">
                <logic:present name="siteSetupUserList">
                    <logic:iterate id="user" name="siteSetupUserList">
                    <html:option value="${user.username}">${user.username}</html:option>
                </logic:iterate>
            </logic:present>
            </html:select></td><td>  </td>
    </tr>
    <tr><td><span style="margin-left:50px;">${ouhLevel2}</span></td><td> 
            
            <html:select property="level2OuId" styleId="level2OuId" styleClass="textField" style="margin-left: 10px; width:300px;" onchange="setActionName('cboList'); forms[0].submit()">
                <html:option value=""> </html:option> 
                <logic:present name="level2orgUnitList">
                    <logic:iterate id="ou" name="level2orgUnitList">
                        <html:option value="${ou.uid}">${ou.name}</html:option>
                    </logic:iterate>
                </logic:present>
            </html:select>
            
        </td><td>  </td>
    </tr>
    <tr><td><span style="margin-left:50px;"><jsp:include page="../includes/LocalOrganizationName.jsp" /></span></td><td> 
            
        <html:select property="cboId" styleId="cboId" style="margin-left: 10px; width:300px;" onchange="setActionName('level3OuList'); forms[0].submit()">
        <html:option value="select">select....</html:option>
        <logic:present name="siteSetupCboList">
            <logic:iterate name="siteSetupCboList" id="cbo">
                <html:option value="${cbo.uniqueId}">${cbo.cboName}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
            
        </td><td>  </td>
    </tr>
    <tr><td><span style="margin-left:50px;">${level3Ouh.name}</span></td><td> 
            
        <html:select property="level3OuId" styleId="level3OuId" onchange="setActionName('level4OuList'); forms[0].submit()" style="margin-left: 10px; width:300px;">
            <html:option value="select">select....</html:option>
            <logic:present name="siteSetupLevel3OuList">
                <logic:iterate name="siteSetupLevel3OuList" id="ou">
                    <html:option value="${ou.uid}">${ou.name}</html:option>
                </logic:iterate>
            </logic:present>
        </html:select>
            
        </td><td>  </td>
    </tr>
    <tr><td><span style="margin-left:50px;">${level4Ouh.name}</span></td><td> 
            
        <html:select property="level4OuId" styleId="level4OuId" style="margin-left: 10px; width:300px;">
            <html:option value="select">select....</html:option>
            <logic:present name="siteSetupLevel4OuList">
                <logic:iterate name="siteSetupLevel4OuList" id="ou">
                    <html:option value="${ou.uid}">${ou.name}</html:option>
                </logic:iterate>
            </logic:present>
        </html:select>
            
        </td><td>  </td>
    </tr>
    <tr><td><span style="margin-left:50px;">Partner</span></td>
        <td> 
            
            <html:select property="partner" styleId="partner" styleClass="textField" style="margin-left: 10px; width:300px;"  > <html:option value=""> </html:option>
                <logic:present name="partnerList">
                <logic:iterate name="partnerList" id="partner">
                    <html:option value="${partner.partnerCode}">${partner.partnerName} </html:option>
                </logic:iterate>
                </logic:present>
            </html:select>
            
        </td>
            <td>  </td>
    </tr>
    <tr>
      <td align="center" colspan="4">
          <logic:present name="siteSetupList">
              <div align="center" style="width:720px; max-height:200px; overflow:scroll">
                  <table width="700" border="1" bordercolor="#FFFFFF" style=" background-color: lightgray; border-collapse: collapse" class="regsitertable">
              <tr><td>Username </td><td>${level3Ouh.name}</td><td>CBO</td><td>${level3Ouh.name}</td><td>${level4Ouh.name}</td><td>Partner name </td><td>Date created </td><td>Last modified date</td><td>edit </td><td>delete</td>
                  </tr>
              <logic:iterate name="siteSetupList" id="setup">
                  <tr><td>${setup.userName} </td><td>${setup.level2Ou.name} </td><td>${setup.cbo.cboName} </td>
                      <td>${setup.level3Ou.name} </td><td>${setup.level4Ou.name} </td><td>${setup.partner.partnerName} </td><td>${setup.dateCreated} </td>
                      <td>${setup.lastModifiedDate} </td><td><a href="sitesetup.do?id=${setup.userName}&&p=ed">edit</a> </td>
                  <td><a href="sitesetup.do?id=${setup.userName}&&p=de" onclick="return confirmAction('delete')">delete</a> </td>
                  </tr>
              </logic:iterate>

          </table>
        </div>
        </logic:present>
      </td>
</tr>
<tr> <td colspan="3" align="center">
    <html:submit style="width:75px; height: 30px;" styleId="save" value="Save" disabled="${ssSaveDisabled}" onclick="return confirmAction('save'); forms[0].submit()"/>
    <html:submit style="width:75px; height: 30px;" styleId="modify" value="Modify..." disabled="${ssModifyDisabled}" onclick="return confirmAction('modify');forms[0].submit()" />
    <%--<html:submit style="width:75px; height: 30px;" styleId="deleteBtn" value="Delete..." disabled="${partnerModifyDisabled}" onclick="return confirmAction('delete');forms[0].submit()" />--%>

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
