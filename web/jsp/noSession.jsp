<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@ page import="org.apache.struts.action.*" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css"/>
<title>BMP Geom&aacute;tica S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function go(){
    f = document.forms[0];
    f.method.value = "irLoguin";
    f.submit();
}
</script>
<body>
<p>&nbsp;</p>
    <%@include file="header.jsp" %>
    <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr>
            <td height="15" class="etiqueta"><br><br><br><br></td>
        </tr>
        <tr>
            <td align="center" valign="middle">
                <html:form action="loguin-action" method="post">
                <input type="hidden" name="method"/>
                <table width="55%" align="center" cellPadding="0" cellSpacing="0" class="clsTabla3">
                    <tr>
                        <td>
                        <div align="center">
                            <p><strong><font color="005E97" size="2" face="Verdana, Arial, Helvetica, sans-serif">Sesión Expirada</font></strong></p>
                            <p style="margin-bottom: 0; margin-top:0">
                            <span style="vertical-align: super; letter-spacing: 2px; font-weight:700">
                            <font face="Verdana, Arial, Helvetica, sans-serif" color="#006699" size=1>
                                Su sesión ha expirado debido al tiempo de inactividad.
                            </font>
                            </span>
                            </p>
                            <p style="margin-bottom: 0; margin-top:0">
                            <span style="vertical-align: super; letter-spacing: 2px; font-weight:700">
                            <font face="Verdana, Arial, Helvetica, sans-serif" color="#006699" size=1>
                                <a href="JavaScript:go();">REGRESAR</a>
                            </font>
                            </span>
                            </p>
                        </div>
                        </td>
                    </tr>
                </table>
                </html:form>
            </td>
        </tr>
        <tr>
            <td height="15" class="etiqueta"><br><br><br><br></td>
        </tr>
    </table>
    <%@include file="footer.jsp" %>
</body>
</html:html>