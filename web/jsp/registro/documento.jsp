<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap map = request.getAttribute("documentoBean")!=null?(HashMap)request.getAttribute("documentoBean"):new HashMap();
    String ind = request.getAttribute("indListaDocumento")!=null?(String)request.getAttribute("indListaDocumento"):"";
    String codTipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipDocumento = mapListas.get("listaTipDocumento")!=null?(List)mapListas.get("listaTipDocumento"):new ArrayList();
    List listaForPresentacion = mapListas.get("listaForPresentacion")!=null?(List)mapListas.get("listaForPresentacion"):new ArrayList();
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
function agregarDocumento(){
    f = document.forms[0];
    <%if(!codTipFicha.equals(Properties.FichaCatastralRural)){%>
        f.desTipDocumento.value = f.codTipDocumento.options[f.codTipDocumento.selectedIndex].text;
    <%}else{%>
        f.desForPresentacion.value = f.codForPresentacion.options[f.codForPresentacion.selectedIndex].text;
    <%}%>
    f.method.value = "agregarDocumento";
    f.submit();
}
function editarDocumento(){
    f = document.forms[0];
    <%if(!codTipFicha.equals(Properties.FichaCatastralRural)){%>
        f.desTipDocumento.value = f.codTipDocumento.options[f.codTipDocumento.selectedIndex].text;
    <%}else{%>
        f.desForPresentacion.value = f.codForPresentacion.options[f.codForPresentacion.selectedIndex].text;
    <%}%>
    f.method.value = "editarDocumento";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    <%if(codTipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){%>
        f.method.value = "irFichaIndividual";
    <%}else if(codTipFicha.equals(Properties.FichaCatastralRural)){%>
        f.method.value = "irFichaRural";
    <%}%>
    f.submit();
}
</script>
<body>
<br>
<table width="550" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">DATOS DEL DOCUMENTO</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaIndividual-action" method="post" styleId="fichaDocumento">
            <input type="hidden" name="method">
            <input type="hidden" name="desTipDocumento">
            <input type="hidden" name="desForPresentacion">
            <input type="hidden" name="indListaDocumento" value="<%=ind%>">
            <table width="520" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%if(!codTipFicha.equals(Properties.FichaCatastralRural)){%>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;TIPO DE DOCUMENTO</td>
                    <td colspan="3">
                        <%String codtipdocumento = StringUtil.nullAsEmptyString(((String)map.get("codTipDocumento"))).trim();%>
                        <select class="clsComboLargo" name="codTipDocumento">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaTipDocumento.size();i++){%>
                                <%String codTipDocumento = ((TablasCodigosBean)listaTipDocumento.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaTipDocumento.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipDocumento.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codTipDocumento.equals(codtipdocumento)){%> selected <%}%> value="<%=codTipDocumento%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;Nº DE DOCUMENTO</td>
                    <td>
                        <input type="text" class="casilla" name="numDocumento" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("numDocumento"))).trim()%>" maxlength="20"/>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;FECHA</td>
                    <td>
                        <input type="text" class="casillaFecha" name="fecha" id="fecha" onblur="validaFecha(this.value,'fecha');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("fecha"))).trim()%>" maxlength="10"/>
                        &nbsp;<a href="JavaScript:showCal('fecDocumento')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;AREA AUTORIZADA</td>
                    <td colspan="3">
                        <input type="text" class="casillaFecha" name="area" onkeypress="return valida(this,'dec');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("area"))).trim()%>" maxlength="7"/>
                    </td>
                </tr>
                <%}else{%>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;DOCUMENTO</td>
                    <td colspan="3">
                        <input type="text" class="casillaLarga" name="nomDocumento" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("nomDocumento"))).trim()%>"/>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;FORMA DE PRESENTACI&Oacute;N</td>
                    <td colspan="3">
                        <%String codforpresentacion = StringUtil.nullAsEmptyString(((String)map.get("codForPresentacion"))).trim();%>
                        <select class="clsComboMediano" name="codForPresentacion">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaForPresentacion.size();i++){%>
                                <%String codForPresentacion = ((TablasCodigosBean)listaForPresentacion.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaForPresentacion.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaForPresentacion.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codforpresentacion.equals(codForPresentacion)){%> selected <%}%> value="<%=codForPresentacion%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <%}%>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" height="24" align="center">
                        <%if(codTipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){%>
                            <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("codTipDocumento")).trim())){%>
                                <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarDocumento()"/>
                            <%}else{%>
                                <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarDocumento()"/>
                            <%}%>
                        <%}else if(codTipFicha.equals(Properties.FichaCatastralRural)){%>
                            <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("nomDocumento")).trim())){%>
                                <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarDocumento()"/>
                            <%}else{%>
                                <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarDocumento()"/>
                            <%}%>
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