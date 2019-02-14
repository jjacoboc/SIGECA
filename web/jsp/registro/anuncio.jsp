<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap map = request.getAttribute("anuncioBean")!=null?(HashMap)request.getAttribute("anuncioBean"):new HashMap();
    String ind = request.getAttribute("indListaAnuncio")!=null?(String)request.getAttribute("indListaAnuncio"):"";
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipAnuncio =  mapListas.get("listaTipAnuncio")!=null?(List) mapListas.get("listaTipAnuncio"):new ArrayList();
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
function agregarAnuncio(){
    f = document.forms[0];
    f.desTipAnuncio.value = f.codTipAnuncio.options[f.codTipAnuncio.selectedIndex].text;
    f.method.value = "agregarAnuncio";
    f.submit();
}
function editarAnuncio(){
    f = document.forms[0];
    f.desTipAnuncio.value = f.codTipAnuncio.options[f.codTipAnuncio.selectedIndex].text;
    f.method.value = "editarAnuncio";
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
<table width="580" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">AUTORIZACI&Oacute;N DE ANUNCIO</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaActividadEconomica-action" method="post" styleId="fichaAnuncio">
            <input type="hidden" name="method">
            <input type="hidden" name="desTipAnuncio">
            <input type="hidden" name="indListaAnuncio" value="<%=ind%>">
            <table width="550" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td width="190" class="etiqueta" height="24">&nbsp;&nbsp;C&Oacute;DIGO DEL TIPO DE ANUNCIO</td>
                    <td colspan="3">
                        <%String codtipanuncio = StringUtil.nullAsEmptyString((String)map.get("codTipAnuncio")).trim();%>
                        <select class="clsComboLargo" name="codTipAnuncio">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaTipAnuncio.size();i++){%>
                                <%String codTipAnuncio = ((TablasCodigosBean)listaTipAnuncio.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaTipAnuncio.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipAnuncio.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codtipanuncio.equals(codTipAnuncio)){%> selected <%}%> value="<%=codTipAnuncio%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <!--
                <tr>
                    <td width="190" class="etiqueta" height="24">&nbsp;&nbsp;DESCRIPCI&Oacute;N DEL TIPO DE ANUNCIO</td>
                    <td colspan="3">
                        <input type="text" class="casillaLarga" name="desTipAnuncio" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("desTipAnuncio"))).trim()%>" maxlength="50"/>                        
                    </td>
                </tr>
                -->
                <tr>
                    <td width="190" class="etiqueta" height="24">&nbsp;&nbsp;Nº DE LADOS</td>
                    <td colspan="3">
                        <input type="text" class="casilla" name="numLados" onkeypress="return valida(this,'int');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("numLados"))).trim()%>" size="2" maxlength="2"/>
                    </td>
                </tr>
                <tr>
                    <td width="190" class="etiqueta" height="24">&nbsp;&nbsp;AREA AUTORIZADA</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="areAutorizada" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("areAutorizada"))).trim()%>" maxlength="10"/>
                    </td>
                    <td width="150" class="etiqueta" height="24">AREA VERIFICADA</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="areVerificada" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("areVerificada"))).trim()%>" maxlength="10"/>
                    </td>
                </tr>
                <tr>
                    <td width="190" class="etiqueta" height="24">&nbsp;&nbsp;Nº DE EXPEDIENTE</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="numExpAnuncio" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("numExpAnuncio"))).trim()%>" maxlength="10"/>
                    </td>
                    <td width="150" class="etiqueta" height="24">Nº DE LICENCIA</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="numLicAnuncio" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("numLicAnuncio"))).trim()%>" maxlength="10"/>
                    </td>
                </tr>
                <tr>
                    <td width="190" class="etiqueta" height="24">&nbsp;&nbsp;FECHA DE EXPEDICI&Oacute;N</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="fecExpedicion" id="fecExpedicion" onblur="validaFecha(this.value,'fecExpedicion');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("fecExpedicion"))).trim()%>" maxlength="10"/>
                        &nbsp;<a href="JavaScript:showCal('fecExpAnuncio')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                    </td>
                    <td width="150" class="etiqueta" height="24">FECHA DE VENCIMIENTO</td>
                    <td width="100">
                        <input type="text" class="casillaFecha" name="fecVencimiento" id="fecVencimiento" onblur="validaFecha(this.value,'fecVencimiento');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("fecVencimiento"))).trim()%>" maxlength="10"/>
                        &nbsp;<a href="JavaScript:showCal('fecVenAnuncio')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("codTipAnuncio")).trim())){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarAnuncio()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarAnuncio()"/>
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