<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap via = request.getAttribute("viaBean")!=null?(HashMap)request.getAttribute("viaBean"):new HashMap();
    String ind = request.getAttribute("indListaVia")!=null?(String)request.getAttribute("indListaVia"):"";
    String codTipoFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaVias = mapListas.get("listaVias")!=null?(List)mapListas.get("listaVias"):new ArrayList();
    List listaTipPuerta = mapListas.get("listaTipPuerta")!=null?(List)mapListas.get("listaTipPuerta"):new ArrayList();
    List listaTipVia = mapListas.get("listaTipVia")!=null?(List)mapListas.get("listaTipVia"):new ArrayList();
    List listaConNumeracion = mapListas.get("listaConNumeracion")!=null?(List)mapListas.get("listaConNumeracion"):new ArrayList();
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css"/>
<title>BMP Geom&aacute;tica S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function agregarVia(){
    f = document.forms[0];
    //f.desTipVia.value = f.codTipVia.options[f.codTipVia.selectedIndex].text;
    var via = f.codVia.options[f.codVia.selectedIndex].text;
    via = via.substr(via.indexOf("-")+2,via.length);
    f.codTipVia.value = via.substr(0,via.indexOf(" "));
    f.nomVia.value = via.substr(via.indexOf(" ")+1,via.length);
    f.desTipPuerta.value = f.codTipPuerta.options[f.codTipPuerta.selectedIndex].text;
    f.desConNumeracion.value = f.codConNumeracion.options[f.codConNumeracion.selectedIndex].text;
    f.method.value = "agregarVia";
    f.submit();
}
function editarVia(){
    f = document.forms[0];
    //f.desTipVia.value = f.codTipVia.options[f.codTipVia.selectedIndex].text;
    var via = f.codVia.options[f.codVia.selectedIndex].text;
    via = via.substr(via.indexOf("-")+2,via.length);
    f.codTipVia.value = via.substr(0,via.indexOf(" "));
    f.nomVia.value = via.substr(via.indexOf(" ")+1,via.length);
    f.desTipPuerta.value = f.codTipPuerta.options[f.codTipPuerta.selectedIndex].text;
    f.desConNumeracion.value = f.codConNumeracion.options[f.codConNumeracion.selectedIndex].text;
    f.method.value = "editarVia";
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
<table width="650" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">DATOS DE LA VIA</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaIndividual-action" method="post" >
            <input type="hidden" name="method">
            <input type="hidden" name="codTipVia">
            <input type="hidden" name="desTipVia">
            <input type="hidden" name="nomVia">
            <input type="hidden" name="desTipPuerta">
            <input type="hidden" name="desConNumeracion">
            <input type="hidden" name="indListaVia" value="<%=ind%>">
            <table width="620" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%--
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;TIPO DE V&Iacute;A</td>
                    <td>
                        <%String codtipvia = StringUtil.nullAsEmptyString((String)via.get("codTipVia")).trim();%>
                        <select class="clsComboDoc" name="codTipVia">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaTipVia.size();i++){%>
                                <%String codTipVia = ((TablasCodigosBean)listaTipVia.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaTipVia.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipVia.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codTipVia.equals(codtipvia)){%> selected <%}%> value="<%=codTipVia%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                --%>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;V&Iacute;A</td>
                    <td>
                        <%String codvia = StringUtil.nullAsEmptyString((String)via.get("codVia")).trim();%>
                        <select class="clsComboLargo" name="codVia">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaVias.size();i++){%>
                                <%String codVia = ((String)((HashMap)listaVias.get(i)).get("cod_via")).trim();%>
                                <%String descripcion = ((String)((HashMap)listaVias.get(i)).get("cod_via")).trim()+" - "+((String)((HashMap)listaVias.get(i)).get("tip_via")).trim()+" "+((String)((HashMap)listaVias.get(i)).get("nom_via")).trim();%>
                                <option <%if(codVia.equals(codvia)){%> selected <%}%> value="<%=codVia%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <%--
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;C&Oacute;DIGO DE V&Iacute;A</td>
                    <td>
                        <input type="text" class="casilla" name="codVia" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)via.get("codVia"))%>" size="4" maxlength="6"/>
                    </td>
                </tr>                
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;NOMBRE DE V&Iacute;A</td>
                    <td>
                        <input type="text" class="casilla" name="nomVia" onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)via.get("nomVia"))%>" size="70" maxlength="50"/>
                    </td>
                </tr>
                --%>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;TIPO DE PUERTA</td>
                    <td>
                        <%String codtippuerta = StringUtil.nullAsEmptyString((String)via.get("codTipPuerta")).trim();%>
                        <select class="clsComboDoc" name="codTipPuerta">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaTipPuerta.size();i++){%>
                                <%String codTipPuerta = ((TablasCodigosBean)listaTipPuerta.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaTipPuerta.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipPuerta.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codTipPuerta.equals(codtippuerta)){%> selected <%}%> value="<%=codTipPuerta%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;N&Uacute;MERO MUNICIPAL</td>
                    <td>
                        <input type="text" class="casilla" name="numMunicipal" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)via.get("numMunicipal"))%>" size="5" maxlength="6"/>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;CONDICI&Oacute;N DE NUMERACI&Oacute;N</td>
                    <td>
                        <%String codconnumeracion = StringUtil.nullAsEmptyString((String)via.get("codConNumeracion")).trim();%>
                        <select class="clsComboLargo" name="codConNumeracion">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaConNumeracion.size();i++){%>
                                <%String codConNumeracion = ((TablasCodigosBean)listaConNumeracion.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaConNumeracion.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaConNumeracion.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codConNumeracion.equals(codconnumeracion)){%> selected <%}%> value="<%=codConNumeracion%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="etiqueta" height="24">&nbsp;&nbsp;N&Uacute;MERO DE CERTIFICADO DE NUMERACI&Oacute;N</td>
                    <td>
                        <input type="text" class="casilla" name="numCerNumeracion" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)via.get("numCerNumeracion"))%>" size="20" maxlength="18"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)via.get("codVia")))){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarVia()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarVia()"/>
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