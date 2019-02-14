<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.List" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@page import="org.apache.struts.action.*" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="displaytag.tld" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    List listaFichas = (List)request.getAttribute("listaFichas");
    if(listaFichas == null){
        listaFichas = new ArrayList();
    }
    List listaEstados = (List)request.getAttribute("listaEstados");
    if(listaEstados == null){
        listaEstados = new ArrayList();
    }
    List listaTiposVia = (List)request.getAttribute("listaTiposVia");
    if(listaTiposVia == null){
        listaTiposVia = new ArrayList();
    }
    String filtroBusqueda = (String)request.getAttribute("filtroBusquedaSelected");
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
        function buscar()
        {
            f = document.forms[1];
            f.method.value = 'buscarFichas';
            f.submit();
        }
        function actualizarInputs()
        {
            ocultarInputsTipoBusqueda();
            f = document.forms[1];
            document.getElementById(f.filtroBusqueda.value).style.display='inline';
        }

        function ocultarInputsTipoBusqueda()
        {
            var inputsFiltroBusqueda = document.getElementById('inputsFiltroBusqueda');
            if(inputsFiltroBusqueda.hasChildNodes())
            {
                var children = inputsFiltroBusqueda.childNodes;
                for(var i=0; i<children.length;i++)
                {
                    var child = children[i];
                    if(child.nodeType == 1)
                    {
                        child.style.display='none';
                    }
                }
            }

        }

        function mostrarDetalle(id)
        {
            document.getElementById("iconoMas" + id).style.display='none';
            document.getElementById("iconoMenos" + id).style.display='inline';
            document.getElementById("detalle" + id).style.display='inline';
        }

        function ocultarDetalle(id)
        {
            document.getElementById("iconoMas" + id).style.display='inline';
            document.getElementById("iconoMenos" + id).style.display='none';
            document.getElementById("detalle" + id).style.display='none';
        }
    </script>

    <body>
        <p>&nbsp;</p>
        <%@include file="../header.jsp" %>
        <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr bgcolor="#004d9a">
                <td align="center"><%@include file="../menu.jsp" %></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td valign="middle">
                    <html:form action="consultaFichasCatastrales-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="actCodigoUso"/>
                    <input type="hidden" name="actDescripcionUso"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="260" align="right">B&Uacute;SQUEDA POR&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <select name="filtroBusqueda" onchange="JavaScript:actualizarInputs();">
                                    <%
                                        String filtro01 = "01".equals(filtroBusqueda)?"selected":"";
                                        String filtro02 = "02".equals(filtroBusqueda)?"selected":"";
                                        String filtro03 = "03".equals(filtroBusqueda)?"selected":"";
                                        String filtro04 = "04".equals(filtroBusqueda)?"selected":"";
                                        String filtro05 = "05".equals(filtroBusqueda)?"selected":"";
                                        String filtro06 = "06".equals(filtroBusqueda)?"selected":"";
                                        String filtro07 = "07".equals(filtroBusqueda)?"selected":"";
                                        String filtro08 = "08".equals(filtroBusqueda)?"selected":"";
                                    %>
                                    <option value="01" <%=filtro01%>>C&oacute;digo &Uacute;nico Catastral</option>
                                    <option value="02" <%=filtro02%>>C&oacute;digo Referencia Catastral</option>
                                    <option value="03" <%=filtro03%>>C&oacute;digo Predial de Renta</option>
                                    <option value="04" <%=filtro04%>>C&oacute;digo Contribuyente de Rentas</option>
                                    <option value="05" <%=filtro05%>>Direcci&oacute;n</option>
                                    <option value="06" <%=filtro06%>>Ubicaci&oacute;n</option>
                                    <option value="07" <%=filtro07%>>Titular</option>
                                    <option value="08" <%=filtro08%>>DNI/RUC</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div id="inputsFiltroBusqueda">
                                    <%
                                        String displayId01 = "01".equals(filtroBusqueda)?"inline":"none";
                                        String displayId02 = "02".equals(filtroBusqueda)?"inline":"none";
                                        String displayId03 = "03".equals(filtroBusqueda)?"inline":"none";
                                        String displayId04 = "04".equals(filtroBusqueda)?"inline":"none";
                                        String displayId05 = "05".equals(filtroBusqueda)?"inline":"none";
                                        String displayId06 = "06".equals(filtroBusqueda)?"inline":"none";
                                        String displayId07 = "07".equals(filtroBusqueda)?"inline":"none";
                                        String displayId08 = "08".equals(filtroBusqueda)?"inline":"none";
                                        if(filtroBusqueda == null || "".equals(filtroBusqueda))
                                        {
                                            displayId01 = "inline";
                                        }
                                    %>
                                    <div id="01" style="display: <%=displayId01%>">
                                        <input type="text" class="casilla" name="codigoUnicoCatastral" id="codigoUnicoCatastral" value="" size="12" maxlength="12" />
                                    </div>
                                    <div id="02" style="display: <%=displayId02%>;">
                                        <input type="text" class="casilla" name="codigoReferenciaCatastral" id="codigoReferenciaCatastral" value="" size="21" maxlength="21" />
                                    </div>
                                    <div id="03" style="display: <%=displayId03%>;">
                                        <input type="text" class="casilla" name="codigoPredialRenta" id="codigoPredialRenta" value="" size="15" maxlength="15" />
                                    </div>
                                    <div id="04" style="display: <%=displayId04%>;">
                                        <input type="text" class="casilla" name="codigoContribuyenteRenta" id="codigoContribuyenteRenta" value="" size="50" maxlength="50" />
                                    </div>
                                    <div id="05" style="display: <%=displayId05%>;">
                                        <table>
                                            <tr>
                                                <td>
                                                    TIPO V&Iacute;A
                                                </td>
                                                <td>
                                                    <select name="tipoVia">
                                                    <%
                                                    for (Iterator it = listaTiposVia.iterator(); it.hasNext();) {
                                                        Map columnas = (Map)it.next();
                                                        String tipoVia = (String)columnas.get("tip_via");
                                                    %>
                                                    <option value="<%=tipoVia%>"><%=tipoVia%></option>
                                                    <%}%>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    NOMBRE V&Iacute;A
                                                </td>
                                                <td>
                                                    <input type="text" class="casilla" name="nombreVia" id="nombreVia" value="" size="50" maxlength="100" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    N&Uacute;MERO MUNICIPAL
                                                </td>
                                                <td>
                                                    <input type="text" class="casilla" name="numeroMunicipal" id="numeroMunicipal" value="" size="6" maxlength="6" />
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="06" style="display: <%=displayId06%>;">
                                        <table>
                                            <tr>
                                                <td>
                                                    MANZANA
                                                </td>
                                                <td>
                                                    <input type="text" class="casilla" name="manzana" id="manzana" value="" size="3" maxlength="3" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    LOTE
                                                </td>
                                                <td>
                                                    <input type="text" class="casilla" name="lote" id="lote" value="" size="3" maxlength="3" />
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="07" style="display: <%=displayId07%>;">
                                        <table>
                                            <tr>
                                                <td>
                                                    APELLIDO PATERNO
                                                </td>
                                                <td>
                                                    <input type="text" class="casilla" name="apellidoPaterno" id="apellidoPaterno" value="" size="50" maxlength="50" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    APELLIDO MATERNO
                                                </td>
                                                <td>
                                                    <input type="text" class="casilla" name="apellidoMaterno" id="apellidoMaterno" value="" size="50" maxlength="50" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    NOMBRE
                                                </td>
                                                <td>
                                                    <input type="text" class="casilla" name="nombre" id="nombre" value="" size="50" maxlength="50" />
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="08" style="display: <%=displayId08%>;">
                                        <input type="text" class="casilla" name="dniRuc" id="dniRuc" value="" size="11" maxlength="11" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">ESTADO DE LA FICHA&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <select name="estado">
                                    <%
                                        String estadoSelected = (String)request.getAttribute("estadoSelected");
                                        String estadoTodos = "".equals(estadoSelected)||estadoSelected == null?"selected":"";
                                    %>
                                    <option value="Todos" <%=estadoTodos%>>Todos</option>
                                    <%
                                    for (Iterator it = listaEstados.iterator(); it.hasNext();) {
                                        Map columnas = (Map)it.next();
                                        String idEstado = (String)columnas.get("id_estado");
                                        String nombreEstado = (String)columnas.get("nombre_estado");
                                        String isSelected = "" ;
                                        if(idEstado != null && idEstado.equals(estadoSelected))
                                        {
                                            isSelected = "selected";
                                        }
                                    %>
                                    <option value="<%=idEstado%>" <%=isSelected%>><%=nombreEstado%></option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table width="730" align="center" cellPadding="0" cellSpacing="0">
                        <tr>
                            <td align="left">&nbsp;<input class="buttoncenter" type="button" value="Buscar" name="bBuscar" onclick="JavaScript:buscar();"/></td>
                        </tr>
                    </table>
                    <%
                    if(listaFichas != null && !listaFichas.isEmpty())
                    {
                        %>
                        <table width="720" class="displaytag" align="center">
                        <thead>
                            <tr>
                                <th>VER</th>
                                <%if("01".equals(filtroBusqueda)){%>
                                    <th>C&Oacute;DIGO &Uacute;NICO CATASTRAL</th>
                                <%}%>
                                <%if("02".equals(filtroBusqueda)){%>
                                    <th>C&Oacute;DIGO REFERENCIA CATASTRAL</th>
                                <%}%>
                                <%if("03".equals(filtroBusqueda)){%>
                                    <th>C&Oacute;DIGO PREDIAL DE RENTA</th>
                                <%}%>
                                <%if("05".equals(filtroBusqueda)){%>
                                    <th>TIPO V&Iacute;A</th>
                                    <th>NOMBRE V&Iacute;A</th>
                                    <th>N&Uacute;MERO MUNICIPAL</th>
                                <%}%>
                                <%if("06".equals(filtroBusqueda)){%>
                                    <th>MANZANA</th>
                                    <th>LOTE</th>
                                <%}%>
                                <%if("07".equals(filtroBusqueda)){%>
                                    <th>APELLIDO PATERNO</th>
                                    <th>APELLIDO MATERNO</th>
                                    <th>NOMBRE</th>
                                <%}%>
                                <%if("08".equals(filtroBusqueda)){%>
                                    <th>DNI/RUC</th>
                                <%}%>
                            </tr>
                        </thead>
                            <tbody>
                            <%
                            boolean imparFicha = true;
                            String estiloFilaFicha = "odd";
                            for (Iterator it = listaFichas.iterator(); it.hasNext();) {
                                if(imparFicha){
                                    estiloFilaFicha = "odd";
                                    imparFicha=false;
                                }else{
                                    estiloFilaFicha = "even";
                                    imparFicha=true;
                                }
                                Map contenido = (Map)it.next();
                                if(contenido!=null){
                                    Map columnas = (Map)contenido.get("columnas");
                                    if(columnas!=null){
                                        String id = (String)columnas.get("id_ficha")==null?"":((String)columnas.get("id_ficha")).trim();
                                    %>
                                    <tr class="<%=estiloFilaFicha%>">
                                            <td style="text-align:center">
                                                <div id="iconoMas<%=id%>" style="display:inline">
                                                    <a href="JavaScript:mostrarDetalle('<%=id%>')">+</a>
                                                </div>
                                                <div id="iconoMenos<%=id%>" style="display:none">
                                                    <a href="JavaScript:ocultarDetalle('<%=id%>')">-</a>
                                                </div>
                                            </td>
                                            <%if("01".equals(filtroBusqueda)){%>
                                                <td style="text-align:center">
                                                    <%=columnas.get("cuc")%>
                                                </td>
                                            <%}%>
                                            <%if("02".equals(filtroBusqueda)){%>
                                                <td style="text-align:center">
                                                    <%=columnas.get("codigoreferenciacatastral")%>
                                                </td>
                                            <%}%>
                                            <%if("03".equals(filtroBusqueda)){%>
                                                <td style="text-align:center">
                                                    <%=columnas.get("cod_pred_rentas")%>
                                                </td>
                                            <%}%>
                                            <%if("05".equals(filtroBusqueda)){%>
                                                <td style="text-align:center">
                                                    <%=columnas.get("tip_via")%>
                                                </td>
                                                <td style="text-align:center">
                                                    <%=columnas.get("nom_via")%>
                                                </td>
                                                <td style="text-align:center">
                                                    <%=columnas.get("nro_muni")%>
                                                </td>
                                            <%}%>
                                            <%if("06".equals(filtroBusqueda)){%>
                                                <td style="text-align:center">
                                                    <%=columnas.get("cod_mzna")%>
                                                </td>
                                                <td style="text-align:center">
                                                    <%=columnas.get("cod_lote")%>
                                                </td>
                                            <%}%>
                                            <%if("07".equals(filtroBusqueda)){%>
                                                <td style="text-align:center">
                                                    <%=columnas.get("ape_paterno")%>
                                                </td>
                                                <td style="text-align:center">
                                                    <%=columnas.get("ape_materno")%>
                                                </td>
                                                <td style="text-align:center">
                                                    <%=columnas.get("nombres")%>
                                                </td>
                                            <%}%>
                                            <%if("08".equals(filtroBusqueda)){%>
                                                <td style="text-align:center">
                                                    <%=columnas.get("nro_doc")%>
                                                </td>
                                            <%}%>
                                        </tr>

                                        <tr>
                                            <%if("05".equals(filtroBusqueda) || "07".equals(filtroBusqueda)){%>
                                            <td colspan="4">
                                            <%}else if("06".equals(filtroBusqueda)){%>
                                            <td colspan="3">
                                            <%}else{%>
                                            <td colspan="2">
                                            <%}%>
                                                <div id="detalle<%=id%>" style="display:none">
                                                    <table width="90%" align="right">
                                                        <%
                                                        List listaFichasIndividuales = (List)contenido.get("fichasIndividuales");
                                                        if(listaFichasIndividuales != null && !listaFichasIndividuales.isEmpty())
                                                        {
                                                        %>
                                                            <tr>
                                                                <td colspan="3">
                                                                    <table width="100%" align="right">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>TIPO DE FICHA</th>
                                                                                <th>ID FICHA</th>
                                                                                <th>NRO FAMILIAS</th>
                                                                                <th>C&Oacute;DIGO USO</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <%
                                                                            boolean impar = true;
                                                                            String estiloFila = "odd";
                                                                            for (Iterator itFichaIndividual = listaFichasIndividuales.iterator(); itFichaIndividual.hasNext();) {
                                                                                Map columnasFichaIndividuales = (Map)itFichaIndividual.next();
                                                                                if(impar){
                                                                                    estiloFila = "odd";
                                                                                    impar=false;
                                                                                }else{
                                                                                    estiloFila = "even";
                                                                                    impar=true;
                                                                                }
                                                                            %>
                                                                            <tr class="<%=estiloFila%>">
                                                                                <td style="text-align: center">Ficha Individual</td>
                                                                                <td style="text-align: center"><%=columnasFichaIndividuales.get("id_ficha")%></td>
                                                                                <td style="text-align: center"><%=columnasFichaIndividuales.get("nro_familias")%></td>
                                                                                <td style="text-align: center"><%=columnasFichaIndividuales.get("cod_uso")%></td>
                                                                            </tr>
                                                                            <%}%>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        <%
                                                        }
                                                        %>

                                                        <%
                                                        List listaFichasEconomicas = (List)contenido.get("fichasEconomicas");
                                                        if(listaFichasEconomicas != null && !listaFichasEconomicas.isEmpty())
                                                        {
                                                        %>
                                                            <tr>
                                                                <td colspan="3">
                                                                    <table width="100%" align="right">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>TIPO DE FICHA</th>
                                                                                <th>ID FICHA</th>
                                                                                <th>NOMBRE COMERCIAL</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <%
                                                                            boolean impar = true;
                                                                            String estiloFila = "odd";
                                                                            for (Iterator itFichaEconomica = listaFichasEconomicas.iterator(); itFichaEconomica.hasNext();) {
                                                                                Map columnasFichaIndividuales = (Map)itFichaEconomica.next();
                                                                                if(impar){
                                                                                    estiloFila = "odd";
                                                                                    impar=false;
                                                                                }else{
                                                                                    estiloFila = "even";
                                                                                    impar=true;
                                                                                }
                                                                            %>
                                                                            <tr class="<%=estiloFila%>">
                                                                                <td style="text-align: center">Ficha Económica</td>
                                                                                <td style="text-align: center"><%=columnasFichaIndividuales.get("id_ficha")%></td>
                                                                                <td style="text-align: center"><%=columnasFichaIndividuales.get("nom_comercial")%></td>
                                                                            </tr>
                                                                            <%}%>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        <%
                                                        }
                                                        %>

                                                        <%
                                                        List listaFichasCotitularidades = (List)contenido.get("fichasCotitularidades");
                                                        if(listaFichasCotitularidades != null && !listaFichasCotitularidades.isEmpty())
                                                        {
                                                        %>
                                                            <tr>
                                                                <td colspan="3">
                                                                    <table width="100%" align="right">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>TIPO DE FICHA</th>
                                                                                <th>ID FICHA</th>
                                                                                <th>CONDICI&Oacute;N DECLARANTE</th>
                                                                                <th>ESTADO LLENADO</th>
                                                                                <th>OBSERVACIONES</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <%
                                                                            boolean impar = true;
                                                                            String estiloFila = "odd";
                                                                            for (Iterator itFichaCotitularidades = listaFichasCotitularidades.iterator(); itFichaCotitularidades.hasNext();) {
                                                                                Map columnasFichaCotitularidades = (Map)itFichaCotitularidades.next();
                                                                                if(impar){
                                                                                    estiloFila = "odd";
                                                                                    impar=false;
                                                                                }else{
                                                                                    estiloFila = "even";
                                                                                    impar=true;
                                                                                }
                                                                            %>
                                                                            <tr class="<%=estiloFila%>">
                                                                                <td style="text-align: center">Ficha Cotitularidad</td>
                                                                                <td style="text-align: center"><%=columnasFichaCotitularidades.get("id_ficha")%></td>
                                                                                <td style="text-align: center"><%=columnasFichaCotitularidades.get("condicion_declarante")%></td>
                                                                                <td style="text-align: center"><%=columnasFichaCotitularidades.get("estado_llenado")%></td>
                                                                                <td style="text-align: center"><%=columnasFichaCotitularidades.get("observaciones")%></td>
                                                                            </tr>
                                                                            <%}%>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        <%
                                                        }
                                                        %>

                                                        <%
                                                        List listaFichasBienesComunes = (List)contenido.get("fichasBienesComunes");
                                                        if(listaFichasBienesComunes != null && !listaFichasBienesComunes.isEmpty())
                                                        {
                                                        %>
                                                            <tr>
                                                                <td colspan="3">
                                                                    <table width="100%" align="right">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>TIPO DE FICHA</th>
                                                                                <th>ID FICHA</th>
                                                                                <th>C&Oacute;IGO USO</th>
                                                                                <th>CLASIFICACI&Oacute;N</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <%
                                                                            boolean impar = true;
                                                                            String estiloFila = "odd";
                                                                            for (Iterator itFichaBienesComunes = listaFichasBienesComunes.iterator(); itFichaBienesComunes.hasNext();) {
                                                                                Map columnasFichaBienesComunes = (Map)itFichaBienesComunes.next();
                                                                                if(impar){
                                                                                    estiloFila = "odd";
                                                                                    impar=false;
                                                                                }else{
                                                                                    estiloFila = "even";
                                                                                    impar=true;
                                                                                }
                                                                            %>
                                                                            <tr class="<%=estiloFila%>">
                                                                                <td style="text-align: center">Ficha Bienes Comunes</td>
                                                                                <td style="text-align: center"><%=columnasFichaBienesComunes.get("id_ficha")%></td>
                                                                                <td style="text-align: center"><%=columnasFichaBienesComunes.get("cod_uso")%></td>
                                                                                <td style="text-align: center"><%=columnasFichaBienesComunes.get("clasificacion")%></td>
                                                                            </tr>
                                                                            <%}%>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        <%
                                                        }
                                                        %>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>


                                        <%
                                    }
                                }
                            }
                            %>
                            </tbody>
                        </table>
                        <%
                    }
                    else
                    {%>
                        <table width="720"align="center">
                            <tbody>
                                <tr>
                                    <td>
                                        <span class="pagebanner">Ningún registro encontrado.</span>
                                        <span class="pagelinks"></span>
                                    </td>
                                </tr>
                                <tr><td><br/></td></tr>
                            </tbody>
                        </table>
                    <%
                    }
                    %>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../footer.jsp" %>
</body>
</html:html>