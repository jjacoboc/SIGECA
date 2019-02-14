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
    HashMap map = request.getAttribute("litiganteBean")!=null?(HashMap)request.getAttribute("litiganteBean"):new HashMap();
    String ind = request.getAttribute("indListaLitigante")!=null?(String)request.getAttribute("indListaLitigante"):"";
    String codTipoFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipDocIdentidad = mapListas.get("listaTipDocIdentidad")!=null?(List)mapListas.get("listaTipDocIdentidad"):new ArrayList();
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css"/>
<title>BMP Geom&aacute;tica S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function agregarLitigante(){
    f = document.forms[0];
    f.desTipDocIdentidad.value = f.codTipDocIdentidad.options[f.codTipDocIdentidad.selectedIndex].text;
    f.method.value = "agregarLitigante";
    f.submit();
}
function editarLitigante(){
    f = document.forms[0];
    f.desTipDocIdentidad.value = f.codTipDocIdentidad.options[f.codTipDocIdentidad.selectedIndex].text;
    f.method.value = "editarLitigante";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    <%if(codTipoFicha.equals(Properties.FichaCatastralUrbanaIndividual)){%>
        f.method.value = "irFichaIndividual";
    <%}else if(codTipoFicha.equals(Properties.FichaCatastralRural)){%>
        f.method.value = "irFichaRural";
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
        <td class="Titulo1">IDENTIFICACI&Oacute;N DEL LITIGANTE</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <html:form action="fichaIndividual-action" method="post" styleId="fichaDocumento">
            <input type="hidden" name="method">
            <input type="hidden" name="desTipDocIdentidad">
            <input type="hidden" name="indListaLitigante" value="<%=ind%>">
            <table width="520" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td colspan="2">&nbsp;</td>
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
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;NOMBRES</td>
                    <td>
                        <input type="text" class="casillaLarga" name="nomLitigante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("nomLitigante"))).trim()%>"/>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;APELLIDO PATERNO</td>
                    <td>
                        <input type="text" class="casillaLarga" name="apePatLitigante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("apePatLitigante"))).trim()%>"/>
                    </td>
                </tr>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;APELLIDO MATERNO</td>
                    <td>
                        <input type="text" class="casillaLarga" name="apeMatLitigante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("apeMatLitigante"))).trim()%>"/>
                    </td>
                </tr>
                <%if(!codTipoFicha.equals(Properties.FichaCatastralRural)){%>
                <tr>
                    <td width="160" class="etiqueta" height="24">&nbsp;&nbsp;C&Oacute;DIGO DEL CONTRIBUYENTE</td>
                    <td>
                        <input type="text" class="casilla" name="codContribuyente" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString(((String)map.get("codContribuyente"))).trim()%>" maxlength="15"/>
                    </td>
                </tr>
                <%}%>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" height="24" align="center">
                        <%if(!"".equals(StringUtil.nullAsEmptyString((String)map.get("codTipDocIdentidad")).trim())){%>
                            <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarLitigante()"/>
                        <%}else{%>
                            <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarLitigante()"/>
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