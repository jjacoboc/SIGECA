<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<title>BMP Geomática S.A.</title>
</head>
<body>
    <p>&nbsp;</p>
    <%@include file="header.jsp" %>    
    <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr bgcolor="#004d9a">
            <td align="center"><%@include file="menu.jsp" %></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td align="center" valign="middle">
                <html:form action="loguin-action" method="post">
                    <input type="hidden" name="method" value=""/>
                    <table width="55%" align="center" cellPadding="0" cellSpacing="0" class="clsTabla3">
                        <tr>
                            <td colspan="2" bgcolor="#CCCCCC"><b>Bienvenido(a) al sistema</b></td>
                        </tr>
                        <tr>
                            <td colspan="2" height="30">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2" height="30">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2"><b><%=StringUtil.nullAsEmptyString(usuarioBean.getNombreCompleto())%></b></td>
                        </tr>
                        <tr>
                            <td colspan="2" height="30">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2" height="30">&nbsp;</td>
                        </tr>
                    </table>
                </html:form>
            </td>
        </tr>
        <tr>
            <td height="15" class="etiqueta"></td>
        </tr>
    </table>
    <%@include file="footer.jsp" %>
</body>
</html:html>