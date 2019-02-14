<%-- 
    Document   : cambioClave
    Created on : 02/11/2009, 10:22:44 AM
    Author     : Administrador
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
    if(f.claveActual.value==''){
        alert("Ingrese su clave actual.");
        return;
    }
    if(f.clave.value==''){
        alert("Ingrese su nueva clave.");
        return;
    }
    if(f.confirm.value==''){
        alert("Confirme su nueva clave.");
        return;
    }
    if(f.confirm.value!=f.clave.value){
        alert("La confirmación de su nueva clave es incorrecta.");
        return;
    }
    f.method.value = "cambiarClave";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    f.method.value = "irBusqueda";
    f.submit();
}
</script>
<body>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <table width="450" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr>
            <td class="Titulo1">CAMBIO DE CONTRASEÑA</td>
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
                            <td width="210" class="etiqueta" height="24">&nbsp;&nbsp;CONTRASEÑA ACTUAL: </td>
                            <td>
                                <input type="password" name="claveActual" class="casillaDoc" onkeypress="return valida(this,'afn');" maxlength="11"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;NUEVA CONTRASEÑA: </td>
                            <td>
                                <input type="password" name="clave" class="casillaDoc" onkeypress="return valida(this,'afn');" maxlength="11"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;CONFIRMAR NUEVA CONTRASEÑA: </td>
                            <td>
                                <input type="password" name="confirm" class="casillaDoc" onkeypress="return valida(this,'afn');" maxlength="11"/>
                            </td>
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