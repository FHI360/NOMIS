<%-- 
    Document   : SearchDiv
    Created on : Dec 10, 2018, 10:54:06 PM
    Author     : smomoh
--%>
<div id="pop" class="search" style="border: 5px blue solid; min-width: 452px;max-height: 400px; overflow: scroll">
    <table><tr><td style="width:450px;"><label id="title" style="color:#FFFFFF; width:198px;">Browse</label></td><td><img name="popClose" src="images/close.jpg" style="width:10px; height:10px;" onClick="hideComponent('pop')"/></td></tr>
        <tr><td colspan="2" align="left"><span><input type="text" name="selectedName" style="width:440px;" style="margin-top:0px;" onkeyup="searchHousehold(this.value)"/></span></td></tr>
        <tr><td colspan="2"><span id="searchContent" style="height: 500px;"> </span></td></tr>
    </table>
  </div>
