<%-- 
    Document   : login
    Created on : 17-oct-2008, 17:45:49
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@ page import="org.apache.struts.action.*" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<%
    String attemptsSession = session.getAttribute("attemptsSession")!=null?(String)session.getAttribute("attemptsSession"):"0";
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/keyboard.css" rel="stylesheet" type="text/css">
<title>BMP Geomática S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/keyboard.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function aceptar(){
    f = document.forms[0];
    f.method.value = "ingreso";
    f.submit();
}
function clave(){
    f = document.forms[0];
    f.method.value = "olvidoClave";
    f.submit();
}
function obtenerCuenta(){
    f = document.forms[0];
    f.method.value = "obtenerCuenta";
    f.submit();
}
function cargar(){
    f = document.forms[0];
    f.loguin.value="";
    f.clave.value="";
    f.loguin.focus();
}
function enviar(){
    var key=event.keyCode;
    if(key==13){
        f = document.forms[0];
        f.method.value = "ingreso";
        f.submit();
    }
}
</script>
<body onload="JavaScript:cargar()">
    <p>&nbsp;</p>
    <%@include file="header.jsp" %>
    <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td align="center" valign="middle">
                <html:form action="loguin-action" method="post">
                    <input type="hidden" name="method"/>
                    <table width="55%" align="center" cellPadding="0" cellSpacing="0" class="clsTabla3">
                        <tr>
                            <td colspan="2" bgcolor="#CCCCCC"><b>Inicio de Sesi&oacute;n</b></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="88" class="etiqueta"><b>&nbsp;USUARIO:</b></td>
                            <td width="163" class="etiqueta">
                                <html:text property="loguin" name="LoguinDynaForm" styleClass="keyboardInput" size="14" onkeypress="return valida(this,'afn');"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="etiqueta"><b>&nbsp;CONTRASE&Ntilde;A:</b></td>
                            <td class="etiqueta">
                                <html:password property="clave" name="LoguinDynaForm" styleClass="keyboardInput" size="14" onkeypress="return valida(this,'afn');" onkeydown="JavaScript:enviar()"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <%if(attemptsSession!=null && !"".equals(attemptsSession) && Integer.parseInt(attemptsSession)>3){%>
                            <tr>
                                <td colspan="2" align="center"><img src="Captcha.jpg"></td>
                            </tr>
                            <tr>
                                <td align="center" colspan="2" class="etiqueta">Ingrese el c&oacute;digo que se visualiza en la imagen</td>                            
                            </tr>
                            <tr>
                                <td align="center" colspan="2"><input type="text" name="captcha"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                        <%}%>
                        <tr>
                            <td colspan="2"><div align="center"><font color="red"><html:errors property="loguin"/></font></div><br></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input type="button" class="buttoncenter" value="Aceptar" onclick="JavaScript:aceptar()"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="center" colspan="2">
                                <a href="JavaScript:clave()"><u>¿Olvidó su contraseña?</u></a><br/><br/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
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