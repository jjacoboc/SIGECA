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
    HashMap map = request.getAttribute("construccionBean")!=null?(HashMap)request.getAttribute("construccionBean"):new HashMap();
    String ind = request.getAttribute("indListaConstruccion")!=null?(String)request.getAttribute("indListaConstruccion"):"";
    String codTipoFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
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
function agregarConstruccion(){
    f = document.forms[0];
    f.desMEP.value = f.codMEP.options[f.codMEP.selectedIndex].text;
    f.desECC.value = f.codECC.options[f.codECC.selectedIndex].text;
    f.desECS.value = f.codECS.options[f.codECS.selectedIndex].text;
    <%if(!codTipoFicha.equals(Properties.FichaCatastralRural)){%>
        f.desUCA.value = f.codUCA.options[f.codUCA.selectedIndex].text;
    <%}%>
    f.method.value = "agregarConstruccion";
    f.submit();
}
function editarConstruccion(){
    f = document.forms[0];
    f.desMEP.value = f.codMEP.options[f.codMEP.selectedIndex].text;
    f.desECC.value = f.codECC.options[f.codECC.selectedIndex].text;
    f.desECS.value = f.codECS.options[f.codECS.selectedIndex].text;
    <%if(!codTipoFicha.equals(Properties.FichaCatastralRural)){%>
        f.desUCA.value = f.codUCA.options[f.codUCA.selectedIndex].text;
    <%}%>
    f.method.value = "editarConstruccion";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    <%if(codTipoFicha.equals(Properties.FichaCatastralUrbanaIndividual)){%>
        f.method.value = "irFichaIndividual";
    <%}else if(codTipoFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){%>
        f.method.value = "irFichaBienComun";
    <%}else if(codTipoFicha.equals(Properties.FichaCatastralRural)){%>
        f.method.value = "irFichaRural";
    <%}%>
    f.submit();
}
</script>
<body>
<br>
<table width="550" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">DATOS DE LA CONSTRUCCI&Oacute;N</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaIndividual-action" method="post" styleId="fichaConstruccion">
            <input type="hidden" name="method">
            <input type="hidden" name="desMEP">
            <input type="hidden" name="desECC">
            <input type="hidden" name="desECS">
            <input type="hidden" name="desUCA">
            <input type="hidden" name="indListaConstruccion" value="<%=ind%>">
            <table width="520" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;PISO / SOTANO / MEZZANINE</td>
                    <td width="100">
                        <input type="text" class="casilla" name="numPiso" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("numPiso"))).trim()%>" size="2" maxlength="2"/>
                    </td>
                    <td width="160" class="etiqueta" height="24">FECHA DE CONSTRUCCI&Oacute;N</td>
                    <td>
                        <input type="text" class="casillaFecha" name="fecConstruccion" id="fecConstruccion" onblur="validaFecha(this.value,'fecConstruccion');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("fecConstruccion"))).trim()%>" maxlength="10"/>
                        &nbsp;<a href="JavaScript:showCal('fecConstruccion')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                <%if(!codTipoFicha.equals(Properties.FichaCatastralRural)){%>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;ESTRUCTURA</td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;MUROS Y COLUMNAS</td>
                    <td>
                        <input type="text" class="casilla" name="muro" onkeypress="return valida(this,'afn');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("muro"))).trim()%>" size="1" maxlength="1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </td>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;TECHOS</td>
                    <td>
                        <input type="text" class="casilla" name="techo" onkeypress="return valida(this,'afn');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("techo"))).trim()%>" size="1" maxlength="1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;ACABADOS</td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;PISOS</td>
                    <td>
                        <input type="text" class="casilla" name="pisos" onkeypress="return valida(this,'afn');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("pisos"))).trim()%>" size="1" maxlength="1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </td>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;PUERTAS Y VENTANAS</td>
                    <td>
                        <input type="text" class="casilla" name="puerta" onkeypress="return valida(this,'afn');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("puerta"))).trim()%>" size="1" maxlength="1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;REVESTIMIENTO</td>
                    <td>
                        <input type="text" class="casilla" name="revestimiento" onkeypress="return valida(this,'afn');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("revestimiento"))).trim()%>" size="1" maxlength="1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </td>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;BAÑOS</td>
                    <td>
                        <input type="text" class="casilla" name="bano" onkeypress="return valida(this,'afn');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("bano"))).trim()%>" size="1" maxlength="1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;INST. EL&Eacute;C. Y SANITARIAS</td>
                    <td colspan="3">
                        <input type="text" class="casilla" name="instalaciones" onkeypress="return valida(this,'afn');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("instalaciones"))).trim()%>" size="1" maxlength="1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&Aacute;REA CONSTRU&Iacute;DA (M2)</td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;DECLARADA</td>
                    <td>
                        <input type="text" class="casilla" name="areConDeclarada" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("areConDeclarada"))).trim()%>" size="2" maxlength="6"/>
                    </td>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;VERIFICADA</td>
                    <td>
                        <input type="text" class="casilla" name="areConVerificada" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("areConVerificada"))).trim()%>" size="2" maxlength="6"/>
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
                <%}else{%>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;&Aacute;REA CONSTRU&Iacute;DA (M2)</td>
                    <td colspan="3">
                        <input type="text" class="casilla" name="areConstruida" onkeypress="return valida(this,'dec');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("areConstruida"))).trim()%>" size="2" maxlength="6"/>
                    </td>
                </tr>
                <%}%>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("numPiso")).trim())){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarConstruccion()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarConstruccion()"/>
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