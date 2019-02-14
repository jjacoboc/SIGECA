<%-- 
    Document   : clave
    Created on : 26-oct-2009, 9:13:17
    Author     : JJ
--%>

<%@ page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@ page import="org.apache.struts.action.*" %>
<%@ page import="com.bmp.sigeca.maestro.bean.PerfilBean" %>
<%@ page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@ page import="com.bmp.sigeca.resource.Properties" %>
<%@ page import="com.bmp.sigeca.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
<title>BMP Geomática S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function aceptar(){
    f = document.forms[0];
    if(f.dni.value==''){
        alert("Ingrese su usuario.");
        return;
    }
    if(f.captcha.value==''){
        alert("Ingrese el código de la imagen.");
        return;
    }
    alert("Su contraseña será enviada a su correo electrónico registrado.");
    f.method.value = "enviarClave";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    f.method.value = "irInicio";
    f.submit();
}
</script>
<body>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <table width="450" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr>
            <td class="Titulo1">ENVÍO DE CONTRASEÑA</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td align="center">
                <html:form action="registroUsuario-action" method="post">
                    <input type="hidden" name="method" value=""/>
                    <div align="center"><font color="red"><ol><html:errors property="clave"/></ol></font></div>
                    <table width="420" align="center" cellpadding="0" cellspacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="100" class="etiqueta" height="24">&nbsp;&nbsp;USUARIO</td>
                            <td>
                                <input type="text" name="dni" class="casillaDoc" onkeypress="return valida(this,'int');" maxlength="11"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2" height="24" align="center"><img align="center" src="Captcha.jpg"></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;Ingrese el c&oacute;digo que se visualiza en la imagen</td>
                            <td align="left"><input type="text" name="captcha" maxlength="5"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="center" colspan="2">
                                <input type="button" class="buttoncenter" value="Aceptar" onclick="JavaScript:aceptar()"/>
                                &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" class="buttoncenter" value="Cancelar" onclick="JavaScript:cancelar()" />
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
            <td>&nbsp;</td>
        </tr>
    </table>
</body>
</html:html>