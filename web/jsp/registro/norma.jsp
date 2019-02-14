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
    HashMap map = request.getAttribute("normaLegalBean")!=null?(HashMap)request.getAttribute("normaLegalBean"):new HashMap();
    String ind = request.getAttribute("indListaNormaLegal")!=null?(String)request.getAttribute("indListaNormaLegal"):"";
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
function agregarNorma(){
    f = document.forms[0];
    f.method.value = "agregarNorma";
    f.submit();
}
function editarNorma(){
    f = document.forms[0];
    f.method.value = "editarNorma";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    <%if(codTipoFicha.equals(Properties.FichaCatastralBienesCulturales_MonumentoPrehispanico)){%>
        f.method.value = "irFichaCulturalPrehispanico";
    <%}else if(codTipoFicha.equals(Properties.FichaCatastralBienesCulturales_MonumentoColonial)){%>
        f.method.value = "irFichaCulturalColonial";
    <%}%>
    f.submit();
}
</script>
<body>
<br>
<table width="550" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">DATOS DE LA NORMA LEGAL</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaCulturalPrehispanico-action" method="post">
            <input type="hidden" name="method">
            <input type="hidden" name="indListaNormaLegal" value="<%=ind%>">
            <table width="520" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;N&Uacute;MERO DE NORMA</td>
                    <td>
                        <input type="text" class="casilla" name="numNorma" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("numNorma"))).trim()%>" maxlength="15"/>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;FECHA DE NORMA</td>
                    <td colspan="3">
                        <input type="text" class="casillaFecha" name="fecNorma" id="fecNorma" onblur="validaFecha(this.value,'fecNorma');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("fecNorma"))).trim()%>" maxlength="10"/>
                        &nbsp;<a href="JavaScript:showCal('fecNorma')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;N&Uacute;MERO DE PLANO</td>
                    <td colspan="3">
                        <input type="text" class="casilla" name="numPlano" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("numPlano"))).trim()%>" maxlength="15"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("numNorma")).trim())){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarNorma()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarNorma()"/>
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