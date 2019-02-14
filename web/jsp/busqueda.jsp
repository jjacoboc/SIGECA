<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.maestro.bean.EstadoBean" %>
<%@page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>
<%@taglib uri="displaytag.tld" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
    List listaFicha = session.getAttribute("listaFicha")!=null?(List)session.getAttribute("listaFicha"):null;
    List listaEstado = session.getAttribute("listaEstado")!=null?(List)session.getAttribute("listaEstado"):new ArrayList();
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
<title>BMP Geomática S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function buscar(){
    f = document.forms[1];
    f.method.value = 'buscarFichas';
    f.submit();
}
function obtenerFicha(ind,tipo){
    f = document.forms[1];
    if(tipo=='<%=Properties.FichaCatastralUrbanaIndividual%>'){
        f.method.value = 'obtenerFichaIndividual';
    }else if(tipo=='<%=Properties.FichaCatastralUrbanaCotitularidad%>'){
        f.method.value = 'obtenerFichaCotitularidad';
    }else if(tipo=='<%=Properties.FichaCatastralUrbanaActividadEconomica%>'){
        f.method.value = 'obtenerFichaActividadEconomica';
    }else if(tipo=='<%=Properties.FichaCatastralUrbanaBienesComunes%>'){
        f.method.value = 'obtenerFichaBienComun';
    }else if(tipo=='<%=Properties.FichaCatastralRural%>'){
        f.method.value = 'obtenerFichaRural';
    }else if(tipo=='<%=Properties.FichaCatastralBienesCulturales_MonumentoPrehispanico%>'){
        f.method.value = 'obtenerFichaCulturalPrehispanico';
    }else if(tipo=='<%=Properties.FichaCatastralBienesCulturales_MonumentoColonial%>'){
        f.method.value = 'obtenerFichaCulturalColonial';
    }
    
    f.indListaFicha.value = ind;
    f.submit();
}
</script>
<body>
    <p>&nbsp;</p>
    <%@include file="header.jsp" %>
    <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr bgcolor="#004d9a">
            <td align="center"><%@include file="menu.jsp" %></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td valign="middle">
                <html:form action="inicio-action" method="post">
                    <input type="hidden" name="method" value=""/>
                    <input type="hidden" name="indListaFicha" value=""/>
                    <input type="hidden" name="accion" value="<%=Properties.Accion_RegistrarFicha%>"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">C&Oacute;DIGO &Uacute;NICO CATASTRAL  - CUC&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="cuc" onkeypress="return valida(this,'int');" size="10" maxlength="12"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">N&Uacute;MERO DE FICHA &nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="numFicha" onkeypress="return valida(this,'int');" size="10" maxlength="10"/>
                            </td>
                        </tr>
                        <%--
                        <tr>
                            <td height="24" align="right">DNI DEL T&Eacute;CNICO CATASTRAL&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="dniTecCatastral" onkeypress="return valida(this,'int');" size="10" maxlength="8"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">NOMBRES O APELLIDOS DEL T&Eacute;CNICO CATASTRAL&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomTecCatastral" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');" maxlength="50"/>
                            </td>
                        </tr>
                        --%>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                    </table>
                    <br>
                    <table width="730" align="center" cellPadding="0" cellSpacing="0">
                        <tr>
                            <td align="left">&nbsp;<input class="buttoncenter" type="button" value="Buscar" name="bBuscar" onclick="JavaScript:buscar();"/></td>
                        </tr>
                    </table>
                    <table width="720" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <display:table name="sessionScope.listaFicha" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <%String tipFicha = "";%>
                                    <%String nomTecCatastral = "";%>
                                    <%String apeTecCatastral = "";%>
                                    <%String nombre = "";%>
                                    <%if(listaFicha!=null && !listaFicha.isEmpty()){%>
                                        <%tipFicha = StringUtil.nullAsEmptyString((String)(((HashMap)listaFicha.get(indice)).get("codTipFicha")));%>
                                        <%nomTecCatastral = StringUtil.nullAsEmptyString((String)(((HashMap)listaFicha.get(indice)).get("nomTecCatastral")));%>
                                        <%apeTecCatastral = StringUtil.nullAsEmptyString((String)(((HashMap)listaFicha.get(indice)).get("apeTecCatastral")));%>
                                        <%nombre = nomTecCatastral.concat(" ").concat(apeTecCatastral);%>
                                    <%}%>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:obtenerFicha('<%=indice%>','<%=tipFicha%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="cuc" title="CUC" sortable="true" style="text-align: center;"/>
                                    <display:column property="numFicha" title="N&Uacute;MERO DE FICHA" sortable="true"/>
                                    <display:column property="desTipFicha" title="TIPO DE FICHA" sortable="true"/>
                                    <display:column property="dniTecCatastral" title="DNI" sortable="true"/>
                                    <display:column title="APELLIDOS Y NOMBRES" sortable="true"><%=nombre%></display:column>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="footer.jsp" %>
</body>
</html:html>