<%-- 
    Document   : About
    Created on : Oct 5, 2021, 9:29:11 PM
    Author     : smomoh
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%--<logic:notPresent name="USER">
    <logic:forward name="login" />
</logic:notPresent>--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>     
<script type="text/javascript" src="js/odm.js"></script>
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
<script type="text/javascript" src="js/Enrollmentjsfile.js"></script>
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
<script language="javascript">
function loadAboutInfo()
{
    //alert("Inside load startup process")
    getContextPath();
}
function loadEnvironmentInfo()
{
    var uid="environmentInfo"
   req=uid+";"+uid
   getValuesByAjaxApi('ajaxaction.do',req,'environmentInfo')
}

function stateChanged()
{
	if (xmlhttp.readyState==4)
	{
		var value=xmlhttp.responseText;
                //alert(callerId+" "+value)
                if(value==" " || value=="" || value==";" || value==null)
                return false;
            
            //if(callerId=="checkProcessedImportFiles")
            document.getElementById("environmentInfo").innerHTML=value
            
	}
        else
        {
            //alert("error "+xmlhttp.responseText)
        }
}
</script>
</head>

<body onload="MM_preloadImages('images/About_down.jpg','images/Admin_down.jpg','images/Rapid_down.jpg','images/care_down.jpg','images/OVC_down.jpg');loadEnvironmentInfo()">
    <br/><br/>
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


      <jsp:include page="includes/Pagetabs.jsp" />

      <tr>
        <td height="30"> </td>
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
        <td height="17"> </td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td> </td>
          <td></td>
          <td><jsp:include page="Navigation/Logout.jsp" /></td>
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
    <td width="931" height="300" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <!--DWLayoutTable-->
      <tr>
          <td > </td>
        <td width="679" rowspan="4" >
            <table align="center" style="border:black solid 0px; border-collapse: collapse">
                <tr><td align="center"><span id="environmentInfo"> </span></td><td> </td></tr>
          </table>
            <%--<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
          <!--DWLayoutTable-->
          <tr>
            <td width="231" height="28" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <!--DWLayoutTable-->
              <tr>
                <td width="231" height="28"> </td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td height="85" valign="top"><div style="float: left" id="my_menu" class="sdmenu">
                    <div> </div>
            </div></td>
          </tr>
          <tr>
            <td height="30" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <!--DWLayoutTable-->
              <tr>
                <td width="231" height="30"> </td>
                    </tr>
            </table></td>
          </tr>
          <tr>
            <td height="400" valign="top"><div style="float: left" id="my_menu2" class="sdmenu"> 
                    <div> </div>
            </div></td>
          </tr>


      </table>--%>
        
        </td>
          
      </tr>
              
      <tr>
        <td height="300">&nbsp;</td>
        <td>&nbsp;</td>
        <td rowspan="2" valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr><td><label id="msg"> </label></td></tr>
            <tr><td><label id="contmsg"> </label></td></tr>
            <tr><td><label id="importStatusMsg"> </label></td></tr>
          <!--DWLayoutTable-->
          <tr>
            <td width="679" height="301">
                <div align="center">
                    
                </div></td>
        </tr>
            </table></td>
    </tr>
      <tr>
        <td >&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
    </table>    </td>
    <td width="18">&nbsp;</td>
  </tr>
          
          
  <tr>
    <td height="25" colspan="2" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
      <!--DWLayoutTable-->
      <tr>
        <td width="949" height="25" class="copyright"><jsp:include page="includes/Version.jsp"/></td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
