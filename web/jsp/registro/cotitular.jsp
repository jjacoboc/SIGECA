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
    HashMap map = request.getAttribute("cotitularBean")!=null?(HashMap)request.getAttribute("cotitularBean"):new HashMap();
    String ind = request.getAttribute("indListaCotRural")!=null?(String)request.getAttribute("indListaCotRural"):"";
    String codTipoFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipDocIdentidad = mapListas.get("listaTipDocIdentidad")!=null?(List)mapListas.get("listaTipDocIdentidad"):new ArrayList();
    List listaEstCivil = mapListas.get("listaEstCivil")!=null?(List)mapListas.get("listaEstCivil"):new ArrayList();
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css"/>
<title>BMP Geom&aacute;tica S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function agregarCotitular(){
    f = document.forms[0];
    f.desTipDocIdentidad.value = f.codTipDocIdentidad.options[f.codTipDocIdentidad.selectedIndex].text;
    f.desEstCivil.value = f.codEstCivil.options[f.codEstCivil.selectedIndex].text;
    f.method.value = "agregarCotRural";
    f.submit();
}
function editarCotitular(){
    f = document.forms[0];
    f.desTipDocIdentidad.value = f.codTipDocIdentidad.options[f.codTipDocIdentidad.selectedIndex].text;
    f.desEstCivil.value = f.codEstCivil.options[f.codEstCivil.selectedIndex].text;
    f.method.value = "editarCotRural";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    <%if(codTipoFicha.equals(Properties.FichaCatastralUrbanaIndividual)){%>
        f.method.value = "irFichaIndividual";
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
        <td class="Titulo1">IDENTIFICACI&Oacute;N DEL COTITULAR CATASTRAL</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaRural-action" method="post">
            <input type="hidden" name="method">
            <input type="hidden" name="desTipDocIdentidad">
            <input type="hidden" name="desEstCivil">
            <input type="hidden" name="indListaCotRural" value="<%=ind%>">
            <table width="520" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;APELLIDO PATERNO</td>
                    <td>
                        <input type="text" class="casillaLarga" name="apePaterno" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("apePaterno"))).trim()%>" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;APELLIDO MATERNO</td>
                    <td>
                        <input type="text" class="casillaLarga" name="apeMaterno" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("apeMaterno"))).trim()%>" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;NOMBRES</td>
                    <td>
                        <input type="text" class="casillaLarga" name="nombre" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("nombre"))).trim()%>" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;ESTADO CIVIL</td>
                    <td colspan="3">
                        <%String codestcivil = StringUtil.nullAsEmptyString((String)map.get("codEstCivil")).trim();%>
                        <select class="clsComboDoc" name="codEstCivil">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaEstCivil.size();i++){%>
                                <%String codEstCivil = ((TablasCodigosBean)listaEstCivil.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaEstCivil.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaEstCivil.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codestcivil.equals(codEstCivil)){%> selected <%}%> value="<%=codEstCivil%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;TIPO DOC. IDENTIDAD</td>
                    <td>
                        <%String codtipdocidentidad = StringUtil.nullAsEmptyString(((String)map.get("codTipDocIdentidad"))).trim();%>
                        <select class="clsComboLargo" name="codTipDocIdentidad">
                            <option value="">Seleccione</option>
                            <%for(int i=0;i<listaTipDocIdentidad.size();i++){%>
                                <%String codTipDocIdentidad = ((TablasCodigosBean)listaTipDocIdentidad.get(i)).getCodigo().trim();%>
                                <%String descripcion = ((TablasCodigosBean)listaTipDocIdentidad.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipDocIdentidad.get(i)).getDesc_codigo().trim();%>
                                <option <%if(codTipDocIdentidad.equals(codtipdocidentidad)){%> selected <%}%> value="<%=codTipDocIdentidad%>">
                                    <%=descripcion%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;N&Uacute;MERO DE DOCUMENTO</td>
                    <td>
                        <input type="text" class="casillaDoc" name="numDocumento" onkeypress="return valida(this,'afn');" value="<%=StringUtil.nullAsEmptyString(((String)map.get("numDocumento"))).trim()%>" maxlength="11"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("codTipDocIdentidad")).trim())){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarCotitular()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarCotitular()"/>
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