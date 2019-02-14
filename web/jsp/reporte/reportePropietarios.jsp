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
    List listaFicha = session.getAttribute("listaFicha")!=null?(List)session.getAttribute("listaFicha"):new ArrayList();
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
    f = document.forms[0];
    f.method.value = 'reportePropietarios';
    f.submit();
}
function obtenerFicha(ind,tipo){
    f = document.forms[0];
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
function cancelar(){
    f = document.forms[0];
    f.method.value = 'irBusqueda';
    f.submit();
}
</script>
<body>
    <p>&nbsp;</p>
    <%@include file="../header.jsp" %>
    <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr>
            <td valign="middle">
                <html:form action="inicio-action" method="post">
                    <input type="hidden" name="method" value=""/>
                    <input type="hidden" name="indListaFicha" value=""/>
                    <input type="hidden" name="accion" value="<%=Properties.Accion_Reportes%>"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2" class="Titulo1">REPORTE DE PROPIETARIOS Y LOTES CATASTRALES</td>
                        </tr>
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
                        <tr>
                            <td height="24" align="right">DNI O RUC DEL TITULAR DEL PREDIO CATASTRAL&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="dniTitular" onkeypress="return valida(this,'int');" size="10" maxlength="8"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">NOMBRES O RAZ&Oacute;N SOCIAL DEL TITULAR DEL PREDIO CATASTRAL&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomTitular" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');" maxlength="50"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                    </table>
                    <br>
                    <table width="730" align="center" cellPadding="0" cellSpacing="0">
                        <tr>
                            <td align="left">
                                &nbsp;<input class="buttoncenter" type="button" value="Buscar" name="bBuscar" onclick="JavaScript:buscar();"/>
                                &nbsp;<input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar();"/>
                            </td>
                        </tr>
                    </table>
                    <table width="720" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <display:table name="sessionScope.listaFicha" defaultsort="1" export="true" pagesize="15" class="displaytag" id="row">
                                    <display:setProperty name="decorator.media.pdf" value="org.displaytag.sample.decorators.ItextTotalWrapper" />
                                    <display:setProperty name="export.pdf.filename" value="export.pdf" />
                                    <display:setProperty name="export.pdf" value="true" />
                                    <display:setProperty name="decorator.media.excel" value="org.displaytag.sample.decorators.HssfTotalWrapper" />
                                    <display:setProperty name="export.xls.filename" value="export.xls" />
                                    <display:setProperty name="export.xls" value="true" />
                                    <display:setProperty name="export.csv.filename" value="export.csv" />
                                    <display:setProperty name="export.csv" value="true" />
                                    <display:setProperty name="export.xml" value="false" />
                                    <display:setProperty name="export.rtf" value="false" />
                                    <display:caption media="excel pdf">REPORTE DE PROPIETARIOS</display:caption>
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <%String tipFicha = StringUtil.nullAsEmptyString((String)(((HashMap)listaFicha.get(indice)).get("codTipFicha")));%>
                                    <%String codTipTitular = StringUtil.nullAsEmptyString((String)(((HashMap)listaFicha.get(indice)).get("codTipTitular")));%>
                                    <%String nomTitular = StringUtil.nullAsEmptyString((String)(((HashMap)listaFicha.get(indice)).get("nomTitular")));%>
                                    <%String apePatTitular = StringUtil.nullAsEmptyString((String)(((HashMap)listaFicha.get(indice)).get("apePatTitular")));%>
                                    <%String apeMatTitular = StringUtil.nullAsEmptyString((String)(((HashMap)listaFicha.get(indice)).get("apeMatTitular")));%>
                                    <%String nombreTitular = apePatTitular.concat(" ").concat(apeMatTitular).concat(", ").concat(nomTitular);%>
                                    <display:column title="VER" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:obtenerFicha('<%=indice%>','<%=tipFicha%>')"><img src="<%=request.getContextPath()%>/imagenes/magnifier.png" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="cuc" title="CUC" sortable="true" style="text-align: center;"/>
                                    <display:column property="desTipFicha" title="TIPO DE FICHA" sortable="true"/>
                                    <display:column property="desEstado" title="ESTADO" sortable="true"/>
                                    <%if(codTipTitular.equals(Properties.TipoTitular_PersonaNatural)){%>
                                        <display:column property="numDocIdeTitular" title="DNI" sortable="true" style="text-align: center;"/>
                                        <display:column title="APELLIDOS Y NOMBRES" sortable="true"><%=nombreTitular%></display:column>
                                    <%}else{%>
                                        <display:column property="numRucTitular" title="RUC" sortable="true" style="text-align: center;"/>
                                        <display:column property="razSocTitular" title="RAZ&Oacute;N SOCIAL" sortable="true"/>
                                    <%}%>
                                    <display:setProperty name="basic.empty.showtable" value="true" />
                                </display:table>
                            </td>
                        </tr>
                    </table>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../footer.jsp" %>
</body>
</html:html>
