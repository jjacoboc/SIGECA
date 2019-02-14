<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.UbigeoBean" %>
<%@page import="com.bmp.sigeca.registro.bean.TrabajadorBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap mapFichaCotitular = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"): new HashMap();
    HashMap mapCotitular = mapFichaCotitular.get("mapCotitular")!=null?(HashMap)mapFichaCotitular.get("mapCotitular"): new HashMap();
    HashMap mapInformacion = mapFichaCotitular.get("mapInformacion")!=null?(HashMap)mapFichaCotitular.get("mapInformacion"): new HashMap();
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipTitular =  mapListas.get("listaTipTitular")!=null?(List) mapListas.get("listaTipTitular"):new ArrayList();
    List listaTipDocIdentidad =  mapListas.get("listaTipDocIdentidad")!=null?(List) mapListas.get("listaTipDocIdentidad"):new ArrayList();
    List listaConEspTitular =  mapListas.get("listaConEspTitular")!=null?(List) mapListas.get("listaConEspTitular"):new ArrayList();
    List listaForAdquisicion =  mapListas.get("listaForAdquisicion")!=null?(List) mapListas.get("listaForAdquisicion"):new ArrayList();
    List listaDepartamento =  mapListas.get("listaDepartamento")!=null?(List) mapListas.get("listaDepartamento"):new ArrayList();
    List listaProvincia =  mapListas.get("listaProvincia")!=null?(List) mapListas.get("listaProvincia"):new ArrayList();
    List listaDistrito =  mapListas.get("listaDistrito")!=null?(List) mapListas.get("listaDistrito"):new ArrayList();
    List listaTipVia =  mapListas.get("listaTipVia")!=null?(List) mapListas.get("listaTipVia"):new ArrayList();
    List listaConDeclarante =  mapListas.get("listaConDeclarante")!=null?(List) mapListas.get("listaConDeclarante"):new ArrayList();
    List listaEstLleFicha =  mapListas.get("listaEstLleFicha")!=null?(List) mapListas.get("listaEstLleFicha"):new ArrayList();
    List listaVerificador = mapListas.get("listaVerificador")!=null?(List)mapListas.get("listaVerificador"): new ArrayList();
    List listaSupervisor = mapListas.get("listaSupervisor")!=null?(List)mapListas.get("listaSupervisor"): new ArrayList();
    List listaTecnico = mapListas.get("listaTecnico")!=null?(List)mapListas.get("listaTecnico"): new ArrayList();
    List listaCotitulares = mapFichaCotitular.get("listaCotitulares")!=null?(List)mapFichaCotitular.get("listaCotitulares"): new ArrayList();
    String mensaje = request.getAttribute("mensaje")!=null?(String)request.getAttribute("mensaje"):"";
    String codEstado = mapFichaCotitular.get("codEstado")!=null?(String)mapFichaCotitular.get("codEstado"):"";
    String accion = session.getAttribute("accion")!=null?(String)session.getAttribute("accion"):"";
    String disabled = "";
    if(codEstado.equals(Properties.EstadoRevisado)) disabled="disabled";
    else disabled = "";
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
function validar(){
    f = document.forms[0];
    if(f.dniDeclarante.value==''){
        alert('Ingrese el DNI del declarante');
        return false;
    }
    if(f.nomDeclarante.value==''){
        alert('Ingrese los nombres del declarante');
        return false;
    }
    if(f.apePatDeclarante.value=='' || f.apeMatDeclarante.value==''){
        alert('Ingrese los apellidos completos del declarante');
        return false;
    }
    if(f.fecFirDeclarante.value==''){
        alert('Ingrese la fecha de firma del declarante');
        return false;
    }
    return true;
}
function guardarFicha(){
    f = document.forms[0];
    if(!validar()){return;}
    f.method.value = "guardarFicha";
    f.submit();
}
function actualizarFicha(){
    f = document.forms[0];
    if(!validar()){return;}
    f.method.value = "actualizarFicha";
    f.submit();
}
function cerrarFicha(){
    f = document.forms[0];
    if(!validar()){return;}
    f.method.value = "cerrarFicha";
    f.submit();
}
function agregarCotitular(){
    f = document.forms[0];
    f.method.value = "irAgregarCotitular";
    f.submit();
}
function eliminarCotitular(ind){
    f = document.forms[0];
    f.method.value = "eliminarCotitular";
    f.indListaCotitulares.value = ind;
    f.submit();
}
function editarCotitular(ind){
    f = document.forms[0];
    f.method.value = "irEditarCotitular";
    f.indListaCotitulares.value = ind;
    f.submit();
}

function cancelar(){
    f = document.forms[0];
    f.method.value = "irBusqueda";
    f.submit();
}
function showMessage(){
    <%if(mensaje!=null && !"".equals(mensaje)){%>
        alert('<%=mensaje%>');
    <%}%>
}
</script>
<body onload="JavaScript:showMessage();">
<br>
<html:form action="fichaCotitularidad-action" method="post" styleId="fichaCotitularidad" >
<table width="980" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1" colspan="6">FICHA CATASTRAL URBANA COTITULARIDAD</td>
    </tr>
    <tr>
        <td colspan="6">&nbsp;</td>
    </tr>
    <tr>
        <td width="150" height="24">&nbsp;</td>
        <td width="100">N&Uacute;MERO DE FICHA</td>
        <td><input type="text" class="casilla" name="numFicha" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("numFicha")).trim()%>" size="7" maxlength="7"/></td>
        <td width="160">N&Uacute;MERO DE FICHAS POR LOTE</td>
        <td>
            <input type="text" class="casilla" name="numFichLote" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("numFichLote")).trim()%>" size="1" maxlength="2"/>
            <input type="text" class="casilla" name="numTotFichLote" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("numTotFichLote")).trim()%>" size="1" maxlength="2"/>
        </td>
        <td width="150">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="6">
        <%@include file="../cabecera.jsp" %>
        <br>
        <input type="hidden" name="method">
        <input type="hidden" name="indListaCotitulares">
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DOCUMENTOS:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaCotitulares!=null && !listaCotitulares.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th>EDITAR</th>
                                        <th>ELIMINAR</th>
                                        <th>TIPO DE TITULAR</th>
                                        <th>TIPO DE DOCUMENTO</th>
                                        <th>NRO. DE DOCUMENTO</th>
                                        <th>NOMBRES Y APELLIDOS O RAZÓN SOCIAL</th>
                                        <th>TELÉFONO</th>
                                        <th>CORREO ELECTRÓNICO</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaCotitulares.size();i++){%>
                                            <%HashMap mapCotitulares= (HashMap)listaCotitulares.get(i);%>
                                            <%String codTipTitular = ((String)mapCotitulares.get("codTipTitular")).trim();%>
                                            <%String desTipTitular = (String)mapCotitulares.get("desTipTitular");%>
                                            <%String desTipDocIdentidad = (String)mapCotitulares.get("desTipDocIdentidad");%>
                                            <%String numDocIdentidad = (String)mapCotitulares.get("numDocIdentidad");%>
                                            <%String nombre = (String)mapCotitulares.get("nombre");%>
                                            <%String apePaterno = (String)mapCotitulares.get("apePaterno");%>
                                            <%String apeMaterno = (String)mapCotitulares.get("apeMaterno");%>
                                            <%String nomCompleto = nombre+" "+apePaterno+" "+apeMaterno;%>
                                            <%String numRuc = (String)mapCotitulares.get("numRuc");%>
                                            <%String razSocial = (String)mapCotitulares.get("razSocial");%>
                                            <%String telefonoTit = (String)mapCotitulares.get("telefono");%>
                                            <%String correoTit = (String)mapCotitulares.get("corElectronico");%>
                                            <tr class="normal">
                                                <%if("disabled".equals(disabled)){%>
                                                    <td align="center"><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></td>
                                                    <td align="center"><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></td>
                                                <%}else{%>
                                                    <td align="center"><a href='JavaScript:editarCotitular(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                    <td align="center"><a href='JavaScript:eliminarCotitular(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <%}%>
                                                    <td align="center"><%=StringUtil.nullAsEmptyString(desTipTitular)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(desTipDocIdentidad)%></td>
                                                <%if(codTipTitular.equals(Properties.TipoTitular_PersonaNatural)){%>
                                                    <td align="center"><%=StringUtil.nullAsEmptyString(numDocIdentidad)%></td>
                                                    <td align="center"><%=StringUtil.nullAsEmptyString(nomCompleto)%></td>
                                                <%}else{%>
                                                    <td align="center"><%=StringUtil.nullAsEmptyString(numRuc)%></td>
                                                    <td align="center"><%=StringUtil.nullAsEmptyString(razSocial)%></td>
                                                <%}%>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(telefonoTit)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(correoTit)%></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                <%}else{%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th>EDITAR</th>
                                        <th>ELIMINAR</th>
                                        <th>TIPO DE TITULAR</th>
                                        <th>TIPO DE DOCUMENTO</th>
                                        <th>NOMBRES Y APELLIDOS O RAZÓN SOCIAL</th>
                                        <th>TELÉFONO</th>
                                        <th>CORREO ELECTRÓNICO</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="normal">
                                            <td align="center" colspan="6">No se encontraron registros.</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <%}%>
                            </td>
                        </tr>
                        <%if(listaCotitulares.size()>=7){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:alert('Solo puede agregar hasta 7 cotitulares.');">Agregar Cotitular</a></td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarCotitular();">Agregar Cotitular</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <br/>
        <%--
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DATOS DEL COTITULAR CATASTRAL:
                            </td>
                        </tr>
                        <tr>
                            <td width="156" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;N&Uacute;MERO DE COTITULAR</td>
                            <td width="320" >
                                <input type="text" class="casilla" name="numCotitular" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numCotitular")).trim()%>" size="1" maxlength="2"/>
                          </td>
                            <td width="155" class="etiqueta" height="24">TOTAL DE COTITULARES</td>
                            <td width="317">
                                <input type="text" class="casilla" name="numTotCotitular" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numTotCotitular")).trim()%>" size="1" maxlength="2"/>
                          </td>
                        </tr>
                        <tr>
                            <td width="156" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;PORCENTAJE DE COTITULAR</td>
                            <td width="320">
                                <input type="text" class="casilla" name="porCotitular" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("porCotitular")).trim()%>" size="1" maxlength="3"/>
                          </td>
                            <td width="155" class="etiqueta" height="24">C&Oacute;DIGO DEL CONTRIBUYENTE</td>
                            <td>
                                <input type="text" class="casilla" name="codContribuyente" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("codContribuyente")).trim()%>" size="9" maxlength="10"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="156" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DE TITULAR</td>
                            <td colspan="3">
                                <%String codtiptitular = StringUtil.nullAsEmptyString((String)mapCotitular.get("codTipTitular")).trim();;%>
                                <select class="clsComboDoc" name="codTipTitular" onchange="changeTipoTitular();" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTipTitular.size();i++){%>
                                        <%String codTipTitular = ((TablasCodigosBean)listaTipTitular.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaTipTitular.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipTitular.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codtiptitular.equals(codTipTitular)){%> selected <%}%> value="<%=codTipTitular%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr id="personanatural" >
                            <td colspan="4">
                                <table width="100%" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DOC. IDENTIDAD</td>
                                        <td width="320">
                                            <%String codtipdocidentidad = StringUtil.nullAsEmptyString((String)mapCotitular.get("codTipDocIdentidad")).trim();%>
                                            <select class="clsComboLargo" name="codTipDocIdentidad" id="codTipDocIdentidad" onchange="changeMaxLength();">
                                                <option value="">Seleccione</option>
                                                <%for(int i=0;i<listaTipDocIdentidad.size();i++){%>
                                                    <%String codTipDocIdentidad = ((TablasCodigosBean)listaTipDocIdentidad.get(i)).getCodigo().trim();%>
                                                    <%String descripcion = ((TablasCodigosBean)listaTipDocIdentidad.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipDocIdentidad.get(i)).getDesc_codigo().trim();%>
                                                    <option <%if(codtipdocidentidad.equals(codTipDocIdentidad)){%> selected <%}%> value="<%=codTipDocIdentidad%>">
                                                        <%=descripcion%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </td>
                                        <td width="155" class="etiqueta" height="24">N&Uacute;MERO DE DOCUMENTO</td>
                                        <td>
                                            <input type="text" class="casilla" name="numDocIdentidad" id="numDocIdentidad" onkeypress="return valida(this,'int');"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numDocIdentidad")).trim()%>" size="9"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES</td>
                                        <td colspan="3">
                                            <input type="text" class="casillaLarga" name="nombre" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("nombre")).trim()%>" maxlength="50"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                                        <td width="320">
                                            <input type="text" class="casilla" name="apePaterno" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("apePaterno")).trim()%>" size="32" maxlength="30"/>
                                        </td>
                                        <td width="155" class="etiqueta" height="24">APELLIDO MATERNO</td>
                                        <td>
                                            <input type="text" class="casilla" name="apeMaterno" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("apeMaterno")).trim()%>" size="32" maxlength="30"/>
                                        </td>
                                    </tr>
                                </table>
                          </td>
                        </tr>
                        <tr id="personajuridica">
                            <td colspan="4">
                                <table width="100%" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;N&Uacute;MERO DE RUC</td>
                                        <td width="320">
                                            <input type="text" class="casilla" name="numRuc"onkeypress="return valida(this,'int');"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numRuc")).trim()%>" size="9" maxlength="11"/>
                                      </td>
                                        <td width="155" class="etiqueta" height="24">RAZON SOCIAL</td>
                                        <td>
                                            <input type="text" class="casillaLarga" name="razSocial" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("razSocial")).trim()%>" maxlength="50"/>
                                        </td>
                                    </tr>
                                </table>
                          </td>
                        </tr>
                        <tr>
                            <td width="156" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FORMA DE ADQUISICI&Oacute;N</td>
                            <td width="320" >
                                <%String codforadquisicion = StringUtil.nullAsEmptyString((String)mapCotitular.get("codForAdqTitular")).trim();%>
                                <select class="clsComboLargo" name="codForAdqTitular" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaForAdquisicion.size();i++){%>
                                        <%String codForAdquisicion = ((TablasCodigosBean)listaForAdquisicion.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaForAdquisicion.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaForAdquisicion.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codforadquisicion.equals(codForAdquisicion)){%> selected <%}%> value="<%=codForAdquisicion%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                          </td>
                            <td width="155" class="etiqueta" height="24">FECHA DE ADQUISICI&Oacute;N</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecAdqTitular" <%=disabled%> id="fecAdqTitular" onblur="validaFecha(this.value,'fecAdqTitular');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("fecAdqTitular")).trim()%>" maxlength="10"/>
                                &nbsp;<a href="JavaScript:showCal('fecAdqTitular')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;COND. ESP. DEL TITULAR</td>
                            <td>
                                <%String codconesptitular = StringUtil.nullAsEmptyString((String)mapCotitular.get("codConEspTitular")).trim();%>
                                <select class="clsComboLargo" name="codConEspTitular" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaConEspTitular.size();i++){%>
                                        <%String codConEspTitular = ((TablasCodigosBean)listaConEspTitular.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaConEspTitular.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaConEspTitular.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codconesptitular.equals(codConEspTitular)){%> selected <%}%> value="<%=codConEspTitular%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td class="etiqueta" height="24">Nº RESOL. EXONERACION</td>
                            <td>
                                <input type="text" class="casillaFecha" name="numResExoneracion" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numResExoneracion")).trim()%>" size="9" maxlength="10"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;INICIO EXONERACION</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecIniExoneracion" id="fecIniExoneracion" <%=disabled%> onblur="validaFecha(this.value,'fecIniExoneracion');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("fecIniExoneracion")).trim()%>" maxlength="10"/>
                                &nbsp;<a href="JavaScript:showCal('fecIniExoCotitular')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                            </td>
                            <td class="etiqueta" height="24">FIN EXONERACION</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecVenExoneracion" id="fecVenExoneracion" <%=disabled%> onblur="validaFecha(this.value,'fecVenExoneracion');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("fecVenExoneracion")).trim()%>" maxlength="10"/>
                                &nbsp;<a href="JavaScript:showCal('fecVenExoCotitular')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <br>
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DOMICILIO FISCAL DEL COTITULAR CATASTRAL:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DEPARTAMENTO</td>
                            <td colspan="3">
                                <%String coddepartamento = StringUtil.nullAsEmptyString((String)mapCotitular.get("codDepartamento")).trim();%>
                                <select class="clsComboMediano" name="codDepartamento" onchange="JavaScript:cargarProvincia()" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaDepartamento.size();i++){%>
                                        <%String codDepartamento = ((UbigeoBean)listaDepartamento.get(i)).getCodDepartamento().trim();%>
                                        <%String descripcion = ((UbigeoBean)listaDepartamento.get(i)).getDescripcion().trim();%>
                                        <option <%if(coddepartamento.equals(codDepartamento)){%> selected <%}%> value="<%=codDepartamento%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;PROVINCIA</td>
                            <td colspan="3">
                                <%String codprovincia = StringUtil.nullAsEmptyString((String)mapCotitular.get("codProvincia")).trim();%>
                                <select class="clsComboMediano" name="codProvincia" onchange="JavaScript:cargarDistrito()" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaProvincia.size();i++){%>
                                        <%String codProvincia = ((UbigeoBean)listaProvincia.get(i)).getCodProvincia().trim();%>
                                        <%String descripcion = ((UbigeoBean)listaProvincia.get(i)).getDescripcion().trim();%>
                                        <option <%if(codprovincia.equals(codProvincia)){%> selected <%}%> value="<%=codProvincia%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DISTRITO</td>
                            <td colspan="3">
                                <%String coddistrito = StringUtil.nullAsEmptyString((String)mapCotitular.get("codDistrito")).trim();%>
                                <select class="clsComboMediano" name="codDistrito" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaDistrito.size();i++){%>
                                        <%String codDistrito = ((UbigeoBean)listaDistrito.get(i)).getCodDistrito().trim();%>
                                        <%String descripcion = ((UbigeoBean)listaDistrito.get(i)).getDescripcion().trim();%>
                                        <option <%if(coddistrito.equals(codDistrito)){%> selected <%}%> value="<%=codDistrito%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TEL&Eacute;FONO</td>
                            <td width="320">
                                <input type="text" class="casilla" name="telefonoTit" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("telefono")).trim()%>" size="10" maxlength="12"/>
                            </td>
                            <td width="155" class="etiqueta" height="24">ANEXO</td>
                            <td>
                                <input type="text" class="casilla" name="anexoTit" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("anexo")).trim()%>" size="1" maxlength="4"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FAX</td>
                            <td>
                                <input type="text" class="casilla" name="faxTit" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("fax")).trim()%>" size="10" maxlength="12"/>
                            </td>
                            <td class="etiqueta" height="24">CORREO ELECTR&Oacute;NICO</td>
                            <td>
                                <input type="text" class="casillaLarga" name="correoTit" <%=disabled%> id="correoTit" onblur="validaEmail(this.value,'correoTit');" onkeypress="return valida(this,'email');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("corElectronico")).trim()%>" maxlength="50"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE V&Iacute;A</td>
                            <td>
                                <input type="text" class="casilla" name="codViaTit" <%=disabled%> onkeypress="return valida(this,'int');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("codVia")).trim()%>" size="10" maxlength="6"/>
                            </td>
                            <td class="etiqueta" height="24">TIPO DE V&Iacute;A</td>
                            <td>
                                <%String codtipvia = StringUtil.nullAsEmptyString((String)mapCotitular.get("codTipVia")).trim();%>
                                <select class="clsComboDoc" name="codTipViaTit" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTipVia.size();i++){%>
                                        <%String codTipVia = ((TablasCodigosBean)listaTipVia.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaTipVia.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipVia.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codtipvia.equals(codTipVia)){%> selected <%}%> value="<%=codTipVia%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE DE V&Iacute;A</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomViaTit" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("nomVia")).trim()%>" maxlength="50"/>
                            </td>
                            <td class="etiqueta" height="24">N&Uacute;MERO MUNICIPAL</td>
                            <td>
                                <input type="text" class="casilla" name="numMunicipalTit" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numMunicipal")).trim()%>" size="5" maxlength="6"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE DE EDIFICACI&Oacute;N</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomEdificacionTit" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("nomEdificacion")).trim()%>" maxlength="50"/>
                            </td>
                            <td class="etiqueta" height="24">N&Uacute;MERO INTERIOR</td>
                            <td>
                                <input type="text" class="casilla" name="numInteriorTit" <%=disabled%> onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numInterior")).trim()%>" size="2" maxlength="5"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO HU</td>
                            <td>
                                <input type="text" class="casilla" name="codHUTit" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("codHabUrbana")).trim()%>" size="1" maxlength="4"/>
                            </td>
                            <td class="etiqueta" height="24">NOMBRE DE LA HU</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomHUTit" <%=disabled%> onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("nomHabUrbana")).trim()%>" maxlength="50"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ZONA/SECTOR/ETAPA</td>
                            <td>
                                <input type="text" class="casilla" name="sectorTit" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("sector")).trim()%>" size="20" maxlength="20"/>
                            </td>
                            <td class="etiqueta" height="24">MANZANA</td>
                            <td>
                                <input type="text" class="casilla" name="manzanaTit" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("manzana")).trim()%>" size="2" maxlength="5"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;LOTE</td>
                            <td>
                                <input type="text" class="casilla" name="loteTit" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("lote")).trim()%>" size="2" maxlength="5"/>
                            </td>
                            <td class="etiqueta" height="24">SUB-LOTE</td>
                            <td>
                                <input type="text" class="casilla" name="subloteTit" <%=disabled%> onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("sublote")).trim()%>" size="2" maxlength="5"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <br>
        --%>
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>INFORMACI&Oacute;N COMPLEMENTARIA:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CONDICI&Oacute;N DE DECLARANTE</td>
                            <td width="320">
                                <%String codcondeclarante = StringUtil.nullAsEmptyString((String)mapInformacion.get("codConDeclarante")).trim();%>
                                <select class="clsComboMediano" name="codConDeclarante" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaConDeclarante.size();i++){%>
                                        <%String codConDeclarante = ((TablasCodigosBean)listaConDeclarante.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaConDeclarante.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaConDeclarante.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codcondeclarante.equals(codConDeclarante)){%> selected <%}%> value="<%=codConDeclarante%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">ESTADO DE LA FICHA</td>
                            <td>
                                <%String codestlleficha = StringUtil.nullAsEmptyString((String)mapInformacion.get("codEstLleFicha")).trim();%>
                                <select class="clsComboLargo" name="codEstLleFicha" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaEstLleFicha.size();i++){%>
                                        <%String codEstLleFicha = ((TablasCodigosBean)listaEstLleFicha.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaEstLleFicha.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaEstLleFicha.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codestlleficha.equals(codEstLleFicha)){%> selected <%}%> value="<%=codEstLleFicha%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <br>
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>OBSERVACIONES:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;OBSERVACIONES</td>
                            <td colspan="3">
                                <textarea name="observacion" rows="4" cols="20" <%=disabled%> onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" class="casillaExtraLarga"><%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("observacion")).trim()%></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <br>
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>FIRMAS:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;DECLARANTE</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DNI</td>
                            <td width="320">
                                <input type="text" class="casilla" name="dniDeclarante" onkeypress="return valida(this,'int');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("dniDeclarante")).trim()%>" size="10" maxlength="8"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">NOMBRES</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("nomDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                            <td width="320">
                                <input type="text" class="casillaLarga" name="apePatDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("apePatDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">APELIDO MATERNO</td>
                            <td>
                                <input type="text" class="casillaLarga" name="apeMatDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("apeMatDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA</td>
                            <td colspan="3">
                                <input type="text" class="casillaFecha" name="fecFirDeclarante" id="fecFirDeclarante" onblur="validaFecha(this.value,'fecFirDeclarante');" onkeypress="return valida(this,'fec');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("fecFirDeclarante")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirDecCotitularidad')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;SUPERVISOR</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES Y APELLIDOS</td>
                            <td width="320">
                                <%String idsupervisor = StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("idSupervisor")).trim();%>
                                <select class="clsComboLargo" name="idSupervisor"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaSupervisor.size();i++){%>
                                        <%String idSupervisor = ((TrabajadorBean)listaSupervisor.get(i)).getId_trabajador().trim();%>
                                        <%String nombres = ((TrabajadorBean)listaSupervisor.get(i)).getNombres().trim();%>
                                        <%String apePaterno = ((TrabajadorBean)listaSupervisor.get(i)).getApe_paterno().trim();%>
                                        <%String apeMaterno = ((TrabajadorBean)listaSupervisor.get(i)).getApe_materno().trim();%>
                                        <%String descripcion = nombres+" "+apePaterno+" "+apeMaterno;%>
                                        <option <%if(idsupervisor.equals(idSupervisor)){%> selected <%}%> value="<%=idSupervisor%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">FECHA</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecFirSupervisor" id="fecFirSupervisor" onblur="validaFecha(this.value,'fecFirSupervisor');" onkeypress="return valida(this,'fec');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("fecFirSupervisor")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirSupCotitularidad')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;T&Eacute;CNICO CATASTRAL</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES Y APELLIDOS</td>
                            <td width="320">
                                <%String idtecnico = StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("idTecnico")).trim();%>
                                <select class="clsComboLargo" name="idTecnico"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTecnico.size();i++){%>
                                        <%String idTecnico = ((TrabajadorBean)listaTecnico.get(i)).getId_trabajador().trim();%>
                                        <%String nombres = ((TrabajadorBean)listaTecnico.get(i)).getNombres().trim();%>
                                        <%String apePaterno = ((TrabajadorBean)listaTecnico.get(i)).getApe_paterno().trim();%>
                                        <%String apeMaterno = ((TrabajadorBean)listaTecnico.get(i)).getApe_materno().trim();%>
                                        <%String descripcion = nombres+" "+apePaterno+" "+apeMaterno;%>
                                        <option <%if(idtecnico.equals(idTecnico)){%> selected <%}%> value="<%=idTecnico%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">FECHA</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecFirTecCatastral" id="fecFirTecCatastral" onblur="validaFecha(this.value,'fecFirTecCatastral');" onkeypress="return valida(this,'fec');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("fecFirTecCatastral")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirTecCatCotitularidad')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;VERIFICADOR CATASTRAL</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES Y APELLIDOS</td>
                            <td width="320">
                                <%String idverificador = StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("idVerificador")).trim();%>
                                <select class="clsComboLargo" name="idVerificador"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaVerificador.size();i++){%>
                                        <%String idVerificador = ((TrabajadorBean)listaVerificador.get(i)).getId_trabajador().trim();%>
                                        <%String nombres = ((TrabajadorBean)listaVerificador.get(i)).getNombres().trim();%>
                                        <%String apePaterno = ((TrabajadorBean)listaVerificador.get(i)).getApe_paterno().trim();%>
                                        <%String apeMaterno = ((TrabajadorBean)listaVerificador.get(i)).getApe_materno().trim();%>
                                        <%String descripcion = nombres+" "+apePaterno+" "+apeMaterno;%>
                                        <option <%if(idverificador.equals(idVerificador)){%> selected <%}%> value="<%=idVerificador%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">FECHA</td>
                            <td colspan="3">
                                <input type="text" class="casillaFecha" name="fecFirVerCatastral" id="fecFirVerCatastral" onblur="validaFecha(this.value,'fecFirVerCatastral');" onkeypress="return valida(this,'fec');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("fecFirVerCatastral")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirVerCatCotitularidad')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;Nº DE REGISTRO</td>
                            <td colspan="3">
                                <input type="text" class="casilla" name="numRegVerCatastral" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaCotitular.get("numRegVerCatastral")).trim()%>" size="10" maxlength="15"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <br>
        </td>
    </tr>
</table>
<p align="center">
    <%if(!codEstado.equals(Properties.EstadoRevisado) && !mapFichaCotitular.containsKey("flagOK")){%>
        <%if(mapFichaCotitular.containsKey("flagActualizar") && mapFichaCotitular.get("flagActualizar")!=null){%>
            <input class="buttoncenter" type="button" value="Actualizar Ficha" name="bActualizar" onclick="JavaScript:guardarFicha();"/>&nbsp;&nbsp;&nbsp;
        <%}else{%>
            <input class="buttoncenter" type="button" value="Guardar Ficha" name="bGuardar" onclick="JavaScript:guardarFicha();"/>&nbsp;&nbsp;&nbsp;
        <%}%>
        <%if(!accion.equals(Properties.Accion_ActualizarPropietario) && !accion.equals(Properties.Accion_RectificarArea)){%>
            <%--<input class="buttoncenter" type="button" value="Cerrar Ficha" name="bCerrar" onclick="JavaScript:cerrarFicha();"/>&nbsp;&nbsp;&nbsp;--%>
        <%}%>
    <%}%>
    <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar();"/>
</p>
</html:form>
<br>
</body>
</html:html>