<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.registro.bean.InstalacionBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap map = request.getAttribute("obraBean")!=null?(HashMap)request.getAttribute("obraBean"):new HashMap();
    String ind = request.getAttribute("indListaObra")!=null?(String)request.getAttribute("indListaObra"):"";
    String codTipoFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaInstalacion = session.getAttribute("listaInstalacion")!=null?(List)session.getAttribute("listaInstalacion"):new ArrayList();
    List listaMEP = mapListas.get("listaMEP")!=null?(List)mapListas.get("listaMEP"):new ArrayList();
    List listaECC = mapListas.get("listaECC")!=null?(List)mapListas.get("listaECC"):new ArrayList();
    List listaECS = mapListas.get("listaECS")!=null?(List)mapListas.get("listaECS"):new ArrayList();
    List listaUCA = mapListas.get("listaUCA")!=null?(List)mapListas.get("listaUCA"):new ArrayList();
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
function agregarObra(){
    f = document.forms[0];
    f.desInstalacion.value = f.codInstalacion.options[f.codInstalacion.selectedIndex].text;
    f.desMEP.value = f.codMEP.options[f.codMEP.selectedIndex].text;
    f.desECC.value = f.codECC.options[f.codECC.selectedIndex].text;
    f.desECS.value = f.codECS.options[f.codECS.selectedIndex].text;
    f.desUCA.value = f.codUCA.options[f.codUCA.selectedIndex].text;
    f.method.value = "agregarObra";
    f.submit();
}
function editarObra(){
    f = document.forms[0];
    f.desInstalacion.value = f.codInstalacion.options[f.codInstalacion.selectedIndex].text;
    f.desMEP.value = f.codMEP.options[f.codMEP.selectedIndex].text;
    f.desECC.value = f.codECC.options[f.codECC.selectedIndex].text;
    f.desECS.value = f.codECS.options[f.codECS.selectedIndex].text;
    f.desUCA.value = f.codUCA.options[f.codUCA.selectedIndex].text;
    f.method.value = "editarObra";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    <%if(codTipoFicha.equals(Properties.FichaCatastralUrbanaIndividual)){%>
        f.method.value = "irFichaIndividual";
    <%}else if(codTipoFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){%>
        f.method.value = "irFichaBienComun";
    <%}%>
    f.submit();
}
</script>
<body>
<br>
<table width="550" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">DATOS DE LA OBRA COMPLEMENTARIA</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaIndividual-action" method="post" styleId="fichaObra">
            <input type="hidden" name="method">
            <input type="hidden" name="desInstalacion">
            <input type="hidden" name="desMEP">
            <input type="hidden" name="desECC">
            <input type="hidden" name="desECS">
            <input type="hidden" name="desUCA">
            <input type="hidden" name="indListaObra" value="<%=ind%>">
            <table width="520" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;C&Oacute;DIGO DE INSTALACI&Oacute;N</td>
                    <td colspan="3">
                        <%String codinstalacion = StringUtil.nullAsEmptyString(((String)map.get("codInstalacion"))).trim();%>
                        <select class="clsComboLargo" name="codInstalacion">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaInstalacion.size();i++){%>
                                <%String codInstalacion = ((InstalacionBean)listaInstalacion.get(i)).getCod_instalacion().trim();%>
                                <%String descripcion = ((InstalacionBean)listaInstalacion.get(i)).getDesc_instalacion().trim();%>
                                <%String material = ((InstalacionBean)listaInstalacion.get(i)).getMaterial().trim();%>
                                <%String unidad = ((InstalacionBean)listaInstalacion.get(i)).getUnidad().trim();%>
                                <option <%if(codInstalacion.equals(codinstalacion)){%> selected <%}%> value="<%=codInstalacion%>">
                                    <%=codInstalacion+" | "+descripcion+" | "+material%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;FECHA DE CONSTRUCCI&Oacute;N</td>
                    <td>
                        <input type="text" class="casillaFecha" name="fecConstruccion" id="fecConstruccion" onblur="validaFecha(this.value,'fecConstruccion');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("fecConstruccion"))).trim()%>" maxlength="10"/>
                        &nbsp;<a href="JavaScript:showCal('fecObra')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;MEP</td>
                    <td colspan="3">
                        <%String codmep = StringUtil.nullAsEmptyString(((String)map.get("codMEP"))).trim();%>
                        <select class="clsComboDoc" name="codMEP">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaMEP.size();i++){%>
                                <%String codMEP = ((TablasCodigosBean)listaMEP.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaMEP.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaMEP.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codMEP.equals(codmep)){%> selected <%}%> value="<%=codMEP%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;ECS</td>
                    <td colspan="3">
                        <%String codecs = StringUtil.nullAsEmptyString(((String)map.get("codECS"))).trim();%>
                        <select class="clsComboDoc" name="codECS">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaECS.size();i++){%>
                                <%String codECS = ((TablasCodigosBean)listaECS.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaECS.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaECS.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codECS.equals(codecs)){%> selected <%}%> value="<%=codECS%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;ECC</td>
                    <td colspan="3">
                        <%String codecc = StringUtil.nullAsEmptyString(((String)map.get("codECC"))).trim();%>
                        <select class="clsComboDoc" name="codECC">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaECC.size();i++){%>
                                <%String codECC = ((TablasCodigosBean)listaECC.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaECC.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaECC.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codECC.equals(codecc)){%> selected <%}%> value="<%=codECC%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;DIMENSIONES VERIFICADAS</td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;LARGO</td>
                    <td>
                        <input type="text" class="casilla" name="largo" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("largo"))).trim()%>" size="2" maxlength="6"/>
                    </td>
                    <td class="etiqueta" height="24">ANCHO</td>
                    <td>
                        <input type="text" class="casilla" name="ancho" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("ancho"))).trim()%>" size="2" maxlength="6"/>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;ALTO</td>
                    <td>
                        <input type="text" class="casilla" name="alto" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("alto"))).trim()%>" size="2" maxlength="6"/>
                    </td>
                    <td class="etiqueta" height="24">PRODUCTO TOTAL</td>
                    <td>
                        <input type="text" class="casilla" name="total" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("total"))).trim()%>" size="2" maxlength="6"/>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;UNIDAD DE MEDIDA</td>
                    <td colspan="3">
                        <input type="text" class="casilla" name="uniMedida" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("uniMedida"))).trim()%>" size="2" maxlength="6"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;UCA</td>
                    <td colspan="3">
                        <%String coduca = StringUtil.nullAsEmptyString(((String)map.get("codUCA"))).trim();%>
                        <select class="clsComboLargo" name="codUCA">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaUCA.size();i++){%>
                                <%String codUCA = ((TablasCodigosBean)listaUCA.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaUCA.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaUCA.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codUCA.equals(coduca)){%> selected <%}%> value="<%=codUCA%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("codInstalacion")).trim())){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarObra()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarObra()"/>
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