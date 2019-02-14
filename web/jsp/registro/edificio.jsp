<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap map = request.getAttribute("edificioBean")!=null?(HashMap)request.getAttribute("edificioBean"):new HashMap();
    String ind = request.getAttribute("indListaRecEdificio")!=null?(String)request.getAttribute("indListaRecEdificio"):"";
    String codTipoFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
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
function agregarEdificio(){
    f = document.forms[0];
    f.method.value = "agregarEdificio";
    f.submit();
}
function editarEdificio(){
    f = document.forms[0];
    f.method.value = "editarEdificio";
    f.submit();
}
function cancelar(){
    f = document.forms[0];

    <%if(codTipoFicha.equals(Properties.FichaCatastralUrbanaActividadEconomica)){%>
        f.method.value = "irFichaActividadEconomica";
    <%}else if(codTipoFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){%>
        f.method.value = "irFichaBienComun";
    <%}%>
    f.submit();
}
</script>
<body>
<br>
<table width="580" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">RECAPITULACI&Oacute;N DE EDIFICIOS</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaBienComun-action" method="post">
            <input type="hidden" name="method">
            <input type="hidden" name="indListaRecEdificio" value="<%=ind%>">
            <table width="550" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td width="180" class="etiqueta" height="24">&nbsp;&nbsp;EDIFICIO</td>
                    <td colspan="3">
                        <input type="text" class="casilla" name="edificio" onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("edificio"))).trim()%>" size="2" maxlength="2"/>
                    </td>
                </tr>
                <tr>
                    <td width="180" class="etiqueta" height="24">&nbsp;&nbsp;PORCENTAJE</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="porcentaje" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("porcentaje"))).trim()%>" maxlength="6"/>
                    </td>
                    <td width="160" class="etiqueta" height="24">ATC (m2)</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="atc" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("atc"))).trim()%>" maxlength="10"/>
                    </td>
                </tr>
                <tr>
                    <td width="180" class="etiqueta" height="24">&nbsp;&nbsp;ACC (m2)</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="acc" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("acc"))).trim()%>" maxlength="10"/>
                    </td>
                    <td width="160" class="etiqueta" height="24">AOIC (m2)</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="aoic" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("aoic"))).trim()%>" maxlength="10"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("edificio")).trim())){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarEdificio()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarEdificio()"/>
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