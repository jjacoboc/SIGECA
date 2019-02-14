<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap map = request.getAttribute("actividadBean")!=null?(HashMap)request.getAttribute("actividadBean"):new HashMap();
    String ind = request.getAttribute("indListaActividad")!=null?(String)request.getAttribute("indListaActividad"):"";
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css"/>
<title>BMP Geom&aacute;tica S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/cal.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/cal_conf.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function agregarActividad(){
    f = document.forms[0];
    f.method.value = "agregarActividad";
    f.submit();
}
function editarActividad(){
    f = document.forms[0];
    f.method.value = "editarActividad";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    f.method.value = "irFichaActividadEconomica";
    f.submit();
}
</script>
<body>
<br>
<table width="550" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">DATOS DE LA ACTIVIDAD</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaActividadEconomica-action" method="post">
            <input type="hidden" name="method">
            <input type="hidden" name="indListaActividad" value="<%=ind%>">
            <table width="520" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td width="170" class="etiqueta" height="24">&nbsp;&nbsp;C&Oacute;DIGO DE LA ACTIVIDAD</td>
                    <td>
                        <input type="text" class="casillaNro" name="codActividad" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("codActividad"))).trim()%>" maxlength="6"/>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;DESCRIPCI&Oacute;N DE LA ACTIVIDAD</td>
                    <td colspan="3">
                        <input type="text" class="casillaLarga" name="desActividad" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("desActividad"))).trim()%>" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("codActividad")).trim())){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarActividad()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarActividad()"/>
                        <%}%>
                        <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar()"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
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