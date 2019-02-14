<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="com.bmp.sigeca.maestro.bean.NotariaBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.UbigeoBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.UsoBCBean" %>
<%@page import="com.bmp.sigeca.registro.bean.TrabajadorBean" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap mapFichaBienComun = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"): new HashMap();
    HashMap mapPredio = mapFichaBienComun.get("mapPredio")!=null?(HashMap)mapFichaBienComun.get("mapPredio"): new HashMap();
    HashMap mapNotaria = mapFichaBienComun.get("mapNotaria")!=null?(HashMap)mapFichaBienComun.get("mapNotaria"): new HashMap();
    HashMap mapRecapitulacion = mapFichaBienComun.get("mapRecapitulacion")!=null?(HashMap)mapFichaBienComun.get("mapRecapitulacion"): new HashMap();
    HashMap mapInscripcion = mapFichaBienComun.get("mapInscripcion")!=null?(HashMap)mapFichaBienComun.get("mapInscripcion"): new HashMap();
    HashMap mapInformacion = mapFichaBienComun.get("mapInformacion")!=null?(HashMap)mapFichaBienComun.get("mapInformacion"): new HashMap();
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipInterior =  mapListas.get("listaTipInterior")!=null?(List) mapListas.get("listaTipInterior"):new ArrayList();
    List listaTipEdificacion =  mapListas.get("listaTipEdificacion")!=null?(List) mapListas.get("listaTipEdificacion"):new ArrayList();
    List listaClaPredio =  mapListas.get("listaClaPredio")!=null?(List) mapListas.get("listaClaPredio"):new ArrayList();
    List listaUbiPreCatastral =  mapListas.get("listaUbiPreCatastral")!=null?(List) mapListas.get("listaUbiPreCatastral"):new ArrayList();
    List listaTipParRegistral =  mapListas.get("listaTipParRegistral")!=null?(List) mapListas.get("listaTipParRegistral"):new ArrayList();
    List listaDecFabrica =  mapListas.get("listaDecFabrica")!=null?(List) mapListas.get("listaDecFabrica"):new ArrayList();
    List listaConDeclarante =  mapListas.get("listaConDeclarante")!=null?(List) mapListas.get("listaConDeclarante"):new ArrayList();
    List listaEstLleFicha =  mapListas.get("listaEstLleFicha")!=null?(List) mapListas.get("listaEstLleFicha"):new ArrayList();
    List listaMantenimiento =  mapListas.get("listaMantenimiento")!=null?(List) mapListas.get("listaMantenimiento"):new ArrayList();
    List listaVerificador = mapListas.get("listaVerificador")!=null?(List)mapListas.get("listaVerificador"): new ArrayList();
    List listaSupervisor = mapListas.get("listaSupervisor")!=null?(List)mapListas.get("listaSupervisor"): new ArrayList();
    List listaTecnico = mapListas.get("listaTecnico")!=null?(List)mapListas.get("listaTecnico"): new ArrayList();
    List listaUsoBC = mapListas.get("listaUsoBC")!=null?(List)mapListas.get("listaUsoBC"): new ArrayList();
    List listaNotaria = mapListas.get("listaNotaria")!=null?(List)mapListas.get("listaNotaria"): new ArrayList();
    List listaHabUrbanas = mapListas.get("listaHabUrbanas")!=null?(List)mapListas.get("listaHabUrbanas"): new ArrayList();
    List listaVia = mapFichaBienComun.get("listaVia")!=null?(List)mapFichaBienComun.get("listaVia"): new ArrayList();
    List listaConstruccion = mapFichaBienComun.get("listaConstruccion")!=null?(List)mapFichaBienComun.get("listaConstruccion"): new ArrayList();
    List listaObra = mapFichaBienComun.get("listaObra")!=null?(List)mapFichaBienComun.get("listaObra"): new ArrayList();
    List listaRecEdificio = mapFichaBienComun.get("listaRecEdificio")!=null?(List)mapFichaBienComun.get("listaRecEdificio"): new ArrayList();
    List listaRecBienComun = mapFichaBienComun.get("listaRecBienComun")!=null?(List)mapFichaBienComun.get("listaRecBienComun"): new ArrayList();
    String mensaje = request.getAttribute("mensaje")!=null?(String)request.getAttribute("mensaje"):"";
    String codEstado = mapFichaBienComun.get("codEstado")!=null?(String)mapFichaBienComun.get("codEstado"):"";
    String accion = session.getAttribute("accion")!=null?(String)session.getAttribute("accion"):"";
    String disabled = "";
    if(codEstado.equals(Properties.EstadoRevisado)) disabled="disabled";
    else disabled = "";
%>
<html:html>
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
    var habUrba = f.codHUPre.options[f.codHUPre.selectedIndex].text;
    habUrba = habUrba.substr(habUrba.indexOf("-")+2,habUrba.length);
    habUrba = habUrba.substr(habUrba.indexOf(" ")+1,habUrba.length);
    f.nomHUPre.value = habUrba.substr(0,habUrba.indexOf("-")-1);
    f.sectorPre.value = habUrba.substr(habUrba.indexOf("-")+2, habUrba.length);
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
function agregarVia(){
    f = document.forms[0];
    f.method.value = "irAgregarVia";
    f.submit();
}
function eliminarVia(ind){
    f = document.forms[0];
    f.method.value = "eliminarVia";
    f.indListaVia.value = ind;
    f.submit();
}
function editarVia(ind){
    f = document.forms[0];
    f.method.value = "irEditarVia";
    f.indListaVia.value = ind;
    f.submit();
}
function agregarConstruccion(){
    f = document.forms[0];
    f.method.value = "irAgregarConstruccion";
    f.submit();
}
function eliminarConstruccion(ind){
    f = document.forms[0];
    f.method.value = "eliminarConstruccion";
    f.indListaConstruccion.value = ind;
    f.submit();
}
function editarConstruccion(ind){
    f = document.forms[0];
    f.method.value = "irEditarConstruccion";
    f.indListaConstruccion.value = ind;
    f.submit();
}
function agregarObra(){
    f = document.forms[0];
    f.method.value = "irAgregarObra";
    f.submit();
}
function eliminarObra(ind){
    f = document.forms[0];
    f.method.value = "eliminarObra";
    f.indListaObra.value = ind;
    f.submit();
}
function editarObra(ind){
    f = document.forms[0];
    f.method.value = "irEditarObra";
    f.indListaObra.value = ind;
    f.submit();
}
function agregarEdificio(){
    f = document.forms[0];
    f.method.value = "irAgregarEdificio";
    f.submit();
}
function eliminarEdificio(ind){
    f = document.forms[0];
    f.method.value = "eliminarEdificio";
    f.indListaRecEdificio.value = ind;
    f.submit();
}
function editarEdificio(ind){
    f = document.forms[0];
    f.method.value = "irEditarEdificio";
    f.indListaRecEdificio.value = ind;
    f.submit();
}
function agregarBienComun(){
    f = document.forms[0];
    f.method.value = "irAgregarBienComun";
    f.submit();
}
function eliminarBienComun(ind){
    f = document.forms[0];
    f.method.value = "eliminarBienComun";
    f.indListaRecBienComun.value = ind;
    f.submit();
}
function editarBienComun(ind){
    f = document.forms[0];
    f.method.value = "irEditarBienComun";
    f.indListaRecBienComun.value = ind;
    f.submit();
}
function cargarProvincia(){
    f = document.forms[0];
    f.method.value = "cargarProvincia";
    f.submit();
}
function cargarDistrito(){
    f = document.forms[0];
    f.method.value = "cargarDistrito";
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
<html:form action="fichaBienComun-action" method="post" styleId="fichaBienComun" >
<table width="980" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1" colspan="6">FICHA CATASTRAL URBANA BIENES COMUNES</td>
    </tr>
    <tr>
        <td colspan="6">&nbsp;</td>
    </tr>
    <tr>
        <td width="150" height="24">&nbsp;</td>
        <td width="100">N&Uacute;MERO DE FICHA</td>
        <td>
            <input type="text" class="casilla" name="numFicha" onkeypress="return valida(this,'int');"
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("numFicha")).trim()%>" size="7" maxlength="7" <%=disabled%>/>
        </td>
        <td width="160">N&Uacute;MERO DE FICHAS POR LOTE</td>
        <td>
            <input type="text" class="casilla" name="numFichLote" onkeypress="return valida(this,'int');" 
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("numFichLote")).trim()%>" size="1" maxlength="2" <%=disabled%>/>
            <input type="text" class="casilla" name="numTotFichLote" onkeypress="return valida(this,'int');" 
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("numTotFichLote")).trim()%>" size="1" maxlength="2" <%=disabled%>/>
        </td>
        <td width="150">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="6">
        <%@include file="../cabecera.jsp" %>
        <br>
        <input type="hidden" name="method">
        <input type="hidden" name="indListaVia">
        <input type="hidden" name="indListaConstruccion">
        <input type="hidden" name="indListaObra">
        <input type="hidden" name="indListaRecEdificio">
        <input type="hidden" name="indListaRecBienComun">
        <input type="hidden" name="nomHUPre">
        <input type="hidden" name="sectorPre">
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>UBICACI&Oacute;N DEL BIEN COM&Uacute;N:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaVia!=null && !listaVia.isEmpty()){%>
                                    <table width="95%" class="clsTablaDatos">
                                        <thead>
                                        <tr class="principal">
                                            <th>EDITAR</th>
                                            <th>ELIMINAR</th>
                                            <th>C&Oacute;DIGO DE VIA </th>
                                            <th>TIPO DE V&Iacute;A </th>
                                            <th>NOMBRE V&Iacute;A</th>
                                            <th>TIPO PUERTA</th>
                                            <th>Nº MUNICIPAL</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%for(int i=0;i<listaVia.size();i++){%>
                                            <%HashMap mapVia = (HashMap)listaVia.get(i);%>
                                            <%String codVia = (String)mapVia.get("codVia");%>
                                            <%String codTipVia = (String)mapVia.get("codTipVia");%>
                                            <%String nomVia = (String)mapVia.get("nomVia");%>
                                            <%String desTipPuerta = (String)mapVia.get("desTipPuerta");%>
                                            <%String numMunicipal = (String)mapVia.get("numMunicipal");%>
                                            <tr class="normal">
                                                <td align="center"><a href='JavaScript:editarVia(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                <td align="center"><a href='JavaScript:eliminarVia(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(codVia)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(codTipVia)%></td>
                                                <td><%=StringUtil.nullAsEmptyString(nomVia)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(desTipPuerta)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(numMunicipal)%></td>
                                            </tr>
                                        <%}%>
                                        </tbody>
                                    </table>
                                <%}else{%>
                                    <table width="95%" class="clsTablaDatos">
                                        <thead>
                                        <tr class="principal">
                                            <th width="5%">EDITAR</th>
                                            <th width="5%">ELIMINAR</th>
                                            <th width="12%">C&Oacute;DIGO DE VIA </th>
                                            <th width="15%">TIPO DE V&Iacute;A </th>
                                            <th width="35%">NOMBRE V&Iacute;A</th>
                                            <th width="15%">TIPO PUERTA</th>
                                            <th width="13%">Nº MUNICIPAL</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="normal">
                                                <td align="center" colspan="7">No se encontraron registros.</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                <%}%>
                            </td>
                        </tr>
                        <%if("disabled".equals(disabled)){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar V&iacute;a</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarVia();">Agregar V&iacute;a</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td class="etiqueta" colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DE EDIFICACI&Oacute;N</td>
                            <td width="320">
                                <%String codtipedificacion = StringUtil.nullAsEmptyString((String)mapPredio.get("codTipEdificacion")).trim();%>
                                <select class="clsComboDoc" name="codTipEdificacionPre" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTipEdificacion.size();i++){%>
                                        <%String codTipEdificacion = ((TablasCodigosBean)listaTipEdificacion.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaTipEdificacion.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipEdificacion.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codtipedificacion.equals(codTipEdificacion)){%> selected <%}%> value="<%=codTipEdificacion%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">NOMBRE DE LA EDIFICACI&Oacute;N</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomEdificacionPre" onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("nomEdificacion")).trim()%>" maxlength="50" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DE INTERIOR</td>
                            <td>
                                <%String codtipinterior = StringUtil.nullAsEmptyString((String)mapPredio.get("codTipInterior")).trim();%>
                                <select class="clsComboDoc" name="codTipInteriorPre" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTipInterior.size();i++){%>
                                        <%String codTipInterior = ((TablasCodigosBean)listaTipInterior.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaTipInterior.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipInterior.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codtipinterior.equals(codTipInterior)){%> selected <%}%> value="<%=codTipInterior%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td class="etiqueta" height="24">N&Uacute;MERO DE INTERIOR</td>
                            <td>
                                <input type="text" class="casilla" name="numInteriorPre" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numInterior")).trim()%>" size="1" maxlength="4" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;HABILITACI&Oacute;N URBANA</td>
                            <td colspan="3">
                                <%String codhaburbana = StringUtil.nullAsEmptyString((String)mapPredio.get("codHabUrbana")).trim();%>
                                <select class="clsComboExtraLargo" name="codHUPre">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaHabUrbanas.size();i++){%>
                                        <%String codHabUrbana = ((String)((HashMap)listaHabUrbanas.get(i)).get("cod_hab_urba")).trim();%>
                                        <%String descripcion = ((String)((HashMap)listaHabUrbanas.get(i)).get("cod_hab_urba")).trim()+" - "+
                                                                ((String)((HashMap)listaHabUrbanas.get(i)).get("tip_hab_urba")).trim()+" "+
                                                                ((String)((HashMap)listaHabUrbanas.get(i)).get("nom_hab_urba")).trim()+" - "+
                                                                ((String)((HashMap)listaHabUrbanas.get(i)).get("grupo_urba")).trim();%>
                                        <option <%if(codhaburbana.equals(codHabUrbana)){%> selected <%}%> value="<%=codHabUrbana%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <%--
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO HU</td>
                            <td>
                                <input type="text" class="casilla" name="codHUPre" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("codHabUrbana")).trim()%>" size="1" maxlength="4" <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">NOMBRE DE LA HU</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomHUPre" onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("nomHabUrbana")).trim()%>" maxlength="50" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ZONA/SECTOR/ETAPA</td>
                            <td>
                                <input type="text" class="casilla" name="sectorPre" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("sector")).trim()%>" size="1" maxlength="2"
                                <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">MANZANA</td>
                            <td>
                                <input type="text" class="casilla" name="manzanaPre" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("manzana")).trim()%>" size="1" maxlength="3"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        --%>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;MANZANA</td>
                            <td colspan="3">
                                <input type="text" class="casilla" name="manzanaPre" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("manzana")).trim()%>" size="1" maxlength="3"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;LOTE</td>
                            <td>
                                <input type="text" class="casilla" name="lotePre" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("lote")).trim()%>" size="1" maxlength="3"
                                <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">SUB-LOTE</td>
                            <td>
                                <input type="text" class="casilla" name="sublotePre" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("sublote")).trim()%>" size="1" maxlength="4"
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
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DESCRIPCI&Oacute;N DEL BIEN COM&Uacute;N:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CLASIFICACI&Oacute;N DEL PREDIO</td>
                            <td colspan="3">
                                <%String codclapredio = StringUtil.nullAsEmptyString((String)mapPredio.get("codClaPredio")).trim();%>
                                <select class="clsComboMediano" name="codClaPredio"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaClaPredio.size();i++){%>
                                        <%String codClaPredio = ((TablasCodigosBean)listaClaPredio.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaClaPredio.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaClaPredio.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codclapredio.equals(codClaPredio)){%> selected <%}%> value="<%=codClaPredio%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;PREDIO CATASTRAL EN</td>
                            <td width="320">
                                <%String codubiprecatastral = StringUtil.nullAsEmptyString((String)mapPredio.get("codUbiPreCatastral")).trim();%>
                                <select class="clsComboMediano" name="codUbiPreCatastral"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaUbiPreCatastral.size();i++){%>
                                        <%String codUbiPreCatastral = ((TablasCodigosBean)listaUbiPreCatastral.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaUbiPreCatastral.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaUbiPreCatastral.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codubiprecatastral.equals(codUbiPreCatastral)){%> selected <%}%> value="<%=codUbiPreCatastral%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">USO PREDIO CATASTRAL</td>
                            <td>
                                <%String codusoprecatastral = StringUtil.nullAsEmptyString((String)mapPredio.get("codUsoPreCatastral")).trim();%>
                                <select class="clsComboLargo" name="codUsoPreCatastral"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaUsoBC.size();i++){%>
                                        <%String codUsoPreCatastral = ((UsoBCBean)listaUsoBC.get(i)).getCodUso().trim();%>
                                        <%String descripcion = ((UsoBCBean)listaUsoBC.get(i)).getCodUso().trim()+" - "+((UsoBCBean)listaUsoBC.get(i)).getDesUso().trim();%>
                                        <option <%if(codusoprecatastral.equals(codUsoPreCatastral)){%> selected <%}%> value="<%=codUsoPreCatastral%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ESTRUCTURACI&Oacute;N</td>
                            <td>
                                <input type="text" class="casillaLarga" name="estructuracion" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("estructuracion")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">ZONIFICACI&Oacute;N</td>
                            <td>
                                <input type="text" class="casillaLarga" name="zonificacion" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("zonificacion")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;&Aacute;REA TERRENO T&Iacute;TULO</td>
                            <td>
                                <input type="text" class="casillaFecha" name="areTerTitulo" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("areTerTitulo")).trim()%>" maxlength="10"
                                <%=disabled%>/>&nbsp;(M2)
                            </td>
                            <td class="etiqueta" height="24">&Aacute;REA TERRENO VERIFICADA</td>
                            <td>
                                <input type="text" class="casillaFecha" name="areTerVerificada" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("areTerVerificada")).trim()%>" maxlength="10"
                                <%=disabled%>/>&nbsp;(M2)
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                               <table class="clsTablaDatosMediano" align="center">
                                    <thead>
                                        <tr class="principal">
                                            <th>LINDEROS DE LOTE(ML)</th>
                                            <th>MEDIDA EN CAMPO</th>
                                            <th>MEDIDA SEG&Uacute;N T&Iacute;TULO</th>
                                            <th>COLINDANCIAS EN CAMPO</th>
                                            <th>COLINDANCIAS SEG&Uacute;N T&Iacute;TULO</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;FRENTE</b></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="medCamFrente" onkeypress="return valida(this,'decimales');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medCamFrente")).trim()%>" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="medTitFrente" onkeypress="return valida(this,'decimales');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medTitFrente")).trim()%>" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="colCamFrente" onkeypress="return valida(this,'all');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colCamFrente")).trim()%>" onkeyup="this.value=this.value.toUpperCase();" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="colTitFrente" onkeypress="return valida(this,'all');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colTitFrente")).trim()%>" onkeyup="this.value=this.value.toUpperCase();" maxlength="20"/></td>
                                        </tr>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;DERECHA</b></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="medCamDerecha" onkeypress="return valida(this,'decimales');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medCamDerecha")).trim()%>" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="medTitDerecha" onkeypress="return valida(this,'decimales');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medTitDerecha")).trim()%>" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="colCamDerecha" onkeypress="return valida(this,'all');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colCamDerecha")).trim()%>" onkeyup="this.value=this.value.toUpperCase();" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="colTitDerecha" onkeypress="return valida(this,'all');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colTitDerecha")).trim()%>" onkeyup="this.value=this.value.toUpperCase();" maxlength="20"/></td>
                                        </tr>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;IZQUIERDA</b></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="medCamIzquierda" onkeypress="return valida(this,'decimales');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medCamIzquierda")).trim()%>" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="medTitIzquierda" onkeypress="return valida(this,'decimales');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medTitIzquierda")).trim()%>" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="colCamIzquierda" onkeypress="return valida(this,'all');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colCamIzquierda")).trim()%>" onkeyup="this.value=this.value.toUpperCase();" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="colTitIzquierda" onkeypress="return valida(this,'all');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colTitIzquierda")).trim()%>" onkeyup="this.value=this.value.toUpperCase();" maxlength="20"/></td>
                                        </tr>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;FONDO</b></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="medCamFondo" onkeypress="return valida(this,'decimales');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medCamFondo")).trim()%>" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="medTitFondo" onkeypress="return valida(this,'decimales');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medTitFondo")).trim()%>" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="colCamFondo" onkeypress="return valida(this,'all');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colCamFondo")).trim()%>" onkeyup="this.value=this.value.toUpperCase();" maxlength="20"/></td>
                                            <td align="center"><input type="text" class="casillaDatos" name="colTitFondo" onkeypress="return valida(this,'all');" value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colTitFondo")).trim()%>" onkeyup="this.value=this.value.toUpperCase();" maxlength="20"/></td>
                                        </tr>
                                    </tbody>
                                </table>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>SERVICIOS B&Aacute;SICOS:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;LUZ</td>
                            <td width="320">
                                <%String luz = StringUtil.nullAsEmptyString((String)mapPredio.get("luz")).trim();%>
                                <input type="checkbox" name="luz" class="casilla" <%if("on".equals(luz)){%> checked <%}%>
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">Nº SUMINISTRO LUZ</td>
                            <td>
                                <input type="text" class="casillaDoc" name="numSumLuz" maxlength="10" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numSumLuz")).trim()%>"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;AGUA</td>
                            <td width="320">
                                <%String agua = StringUtil.nullAsEmptyString((String)mapPredio.get("agua")).trim();%>
                                <input type="checkbox" name="agua" class="casilla" <%if("on".equals(agua)){%> checked <%}%>
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">Nº CONTRATO AGUA</td>
                            <td>
                                <input type="text" class="casillaDoc" name="numConAgua" maxlength="10" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numConAgua")).trim()%>"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TEL&Eacute;FONO</td>
                            <td width="320">
                                <%String telefono = StringUtil.nullAsEmptyString((String)mapPredio.get("telefono")).trim();%>
                                <input type="checkbox" name="telefono" class="casilla" <%if("on".equals(telefono)){%> checked <%}%>
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">Nº TEL&Eacute;FONO</td>
                            <td>
                                <input type="text" class="casillaDoc" name="numTelefono" maxlength="10" onkeypress="return valida(this,'int');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numTelefono")).trim()%>"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DESAGÜE</td>
                            <td colspan="3">
                                <%String desague = StringUtil.nullAsEmptyString((String)mapPredio.get("desague")).trim();%>
                                <input type="checkbox" class="casilla" name="desague" <%if("on".equals(desague)){%> checked <%}%>
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
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>CONSTRUCCIONES COMUNES (Llenar una fila por cada piso, s&oacute;tano o mezzanine):
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaConstruccion!=null && !listaConstruccion.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th rowspan="3" scope="col">EDITAR</th>
                                        <th rowspan="3" scope="col">ELIMINAR</th>
                                        <th rowspan="3" scope="col">N&ordm; PISO SOTANO MEZZANINE</th>
                                        <th rowspan="3" scope="col">FECHA DE CONSTRUCCI&Oacute;N</th>
                                        <th colspan="7" scope="col">CATEGOR&Iacute;AS</th>
                                        <th colspan="2" scope="col">AREA CONSTRU&Iacute;DA (M2)</th>
                                    </tr>
                                    <tr class="principal">
                                        <th colspan="2">ESTRUCTURA</th>
                                        <th colspan="4">ACABADOS</th>
                                        <th rowspan="2" class="secundario">INSTALACIONES EL&Eacute;CTRICAS Y SANITARIAS</th>
                                        <th rowspan="2" class="secundario">DECLARADA</th>
                                        <th rowspan="2" class="secundario">VERIFICADA</th>
                                    </tr>
                                    <tr class="principal">
                                        <th class="secundario">MUROS Y COLUMNAS</th>
                                        <th class="secundario">TECHOS</th>
                                        <th class="secundario">PISOS</th>
                                        <th class="secundario">PUERTAS Y VENTANAS</th>
                                        <th class="secundario">REVEST.</th>
                                        <th class="secundario">BA&Ntilde;OS</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaConstruccion.size();i++){%>
                                            <%HashMap mapConstruccion = (HashMap)listaConstruccion.get(i);%>
                                            <%String numPiso = (String)mapConstruccion.get("numPiso");%>
                                            <%String fecConstruccion = (String)mapConstruccion.get("fecConstruccion");%>
                                            <%String muro = (String)mapConstruccion.get("muro");%>
                                            <%String techo = (String)mapConstruccion.get("techo");%>
                                            <%String piso = (String)mapConstruccion.get("pisos");%>
                                            <%String puerta = (String)mapConstruccion.get("puerta");%>
                                            <%String revestimiento = (String)mapConstruccion.get("revestimiento");%>
                                            <%String bano = (String)mapConstruccion.get("bano");%>
                                            <%String instalaciones = (String)mapConstruccion.get("instalaciones");%>
                                            <%String areConDeclarada = (String)mapConstruccion.get("areConDeclarada");%>
                                            <%String areConVerificada = (String)mapConstruccion.get("areConVerificada");%>
                                            <tr class="normal">
                                                <td align="center"><a href='JavaScript:editarConstruccion(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                <td align="center"><a href='JavaScript:eliminarConstruccion(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(numPiso)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(fecConstruccion)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(muro)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(techo)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(piso)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(puerta)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(revestimiento)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(bano)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(instalaciones)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(areConDeclarada)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(areConVerificada)%></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                <%}else{%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th rowspan="3" scope="col">EDITAR</th>
                                        <th rowspan="3" scope="col">ELIMINAR</th>
                                        <th rowspan="3" scope="col">N&ordm; PISO SOTANO MEZZANINE</th>
                                        <th rowspan="3" scope="col">FECHA DE CONSTRUCCI&Oacute;N</th>
                                        <th colspan="7" scope="col">CATEGOR&Iacute;AS</th>
                                        <th colspan="2" scope="col">AREA CONSTRU&Iacute;DA (M2)</th>
                                    </tr>
                                    <tr class="principal">
                                        <th colspan="2">ESTRUCTURA</th>
                                        <th colspan="4">ACABADOS</th>
                                        <th rowspan="2" class="secundario">INSTALACIONES EL&Eacute;CTRICAS Y SANITARIAS</th>
                                        <th rowspan="2" class="secundario">DECLARADA</th>
                                        <th rowspan="2" class="secundario">VERIFICADA</th>
                                    </tr>
                                    <tr class="principal">
                                        <th class="secundario">MUROS Y COLUMNAS</th>
                                        <th class="secundario">TECHOS</th>
                                        <th class="secundario">PISOS</th>
                                        <th class="secundario">PUERTAS Y VENTANAS</th>
                                        <th class="secundario">REVEST.</th>
                                        <th class="secundario">BA&Ntilde;OS</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="normal">
                                            <td align="center" colspan="13">No se encontraron registros.</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <%}%>
                            </td>
                        </tr>
                        <%if("disabled".equals(disabled)){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar Construcci&oacute;n</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarConstruccion();">Agregar Construcci&oacute;n</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td class="etiqueta" colspan="4">&nbsp;</td>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>OBRAS COMPLEMENTARIAS / OTRAS INSTALACIONES:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaObra!=null && !listaObra.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th rowspan="2" scope="col">EDITAR</th>
                                        <th rowspan="2" scope="col">ELIMINAR</th>
                                        <th rowspan="2" scope="col">DESCRIPCIÓN</th>
                                        <th rowspan="2" scope="col">FECHA DE CONSTRUCCION</th>
                                        <th colspan="3" scope="col">DIMENSIONES VERIFICADAS</th>
                                        <th rowspan="2" scope="col">PRODUCTO TOTAL</th>
                                        <th rowspan="2" scope="col">UNIDAD DE MEDIDA</th>
                                    </tr>
                                    <tr class="principal">
                                        <th rowspan="2" class="secundario">LARGO</th>
                                        <th rowspan="2" class="secundario">ANCHO</th>
                                        <th rowspan="2" class="secundario">ALTO</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaObra.size();i++){%>
                                            <%HashMap mapObra = (HashMap)listaObra.get(i);%>
                                            <%String desInstalacion = (String)mapObra.get("desInstalacion");%>
                                            <%String fecConstruccion = (String)mapObra.get("fecConstruccion");%>
                                            <%String largo = (String)mapObra.get("largo");%>
                                            <%String ancho = (String)mapObra.get("ancho");%>
                                            <%String alto = (String)mapObra.get("alto");%>
                                            <%String total = (String)mapObra.get("total");%>
                                            <%String unidad = (String)mapObra.get("uniMedida");%>
                                            <tr class="normal">
                                                <td align="center"><a href='JavaScript:editarObra(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                <td align="center"><a href='JavaScript:eliminarObra(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(desInstalacion)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(fecConstruccion)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(largo)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(ancho)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(alto)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(total)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(unidad)%></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                <%}else{%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th rowspan="2" scope="col">EDITAR</th>
                                        <th rowspan="2" scope="col">ELIMINAR</th>
                                        <th rowspan="2" scope="col">DESCRIPCIÓN</th>
                                        <th rowspan="2" scope="col">FECHA DE CONSTRUCCION</th>
                                        <th colspan="3" scope="col">DIMENSIONES VERIFICADAS</th>
                                        <th rowspan="2" scope="col">PRODUCTO TOTAL</th>
                                        <th rowspan="2" scope="col">UNIDAD DE MEDIDA</th>
                                    </tr>
                                    <tr class="principal">
                                        <th rowspan="2" class="secundario">LARGO</th>
                                        <th rowspan="2" class="secundario">ANCHO</th>
                                        <th rowspan="2" class="secundario">ALTO</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="normal">
                                            <td align="center" colspan="9">No se encontraron registros.</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <%}%>
                            </td>
                        </tr>
                        <%if("disabled".equals(disabled)){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar Obra Complementaria</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarObra();">Agregar Obra Complementaria</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td colspan="4">&nbsp;</td>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>RECAPITULACI&Oacute;N DE EDIFICIOS:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaRecEdificio!=null && !listaRecEdificio.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th>EDITAR</th>
                                        <th>ELIMINAR</th>
                                        <th>EDIFICIO</th>
                                        <th>PORCENTAJE (%)</th>
                                        <th>ATC (M2)</th>
                                        <th>ACC (M2)</th>
                                        <th>AOIC (M2)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaRecEdificio.size();i++){%>
                                            <%HashMap mapEdificio = (HashMap)listaRecEdificio.get(i);%>
                                            <%String edificio = (String)mapEdificio.get("edificio");%>
                                            <%String porcentaje = (String)mapEdificio.get("porcentaje");%>
                                            <%String atc = (String)mapEdificio.get("atc");%>
                                            <%String acc = (String)mapEdificio.get("acc");%>
                                            <%String aoic = (String)mapEdificio.get("aoic");%>
                                            <tr class="normal">
                                                <td align="center"><a href='JavaScript:editarEdificio(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                <td align="center"><a href='JavaScript:eliminarEdificio(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(edificio)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(porcentaje)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(atc)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(acc)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(aoic)%></td>
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
                                        <th>EDIFICIO</th>
                                        <th>PORCENTAJE (%)</th>
                                        <th>ATC (M2)</th>
                                        <th>ACC (M2)</th>
                                        <th>AOIC (M2)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="normal">
                                            <td align="center" colspan="7">No se encontraron registros.</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <%}%>
                            </td>
                        </tr>
                        <%if("disabled".equals(disabled)){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar Edificio</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarEdificio();">Agregar Edificio</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td class="etiqueta" colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;&Aacute;REA DE TERRENO INVADIDA EN M2</td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <table cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;LOTE COLINDANTE</td>
                                        <td width="320">
                                            <input type="text" class="casillaFecha" name="areTerInvLotColindante" onkeypress="return valida(this,'dec');" 
                                            value="<%=StringUtil.nullAsEmptyString((String)mapRecapitulacion.get("areTerInvLotColindante")).trim()%>" maxlength="10"
                                            <%=disabled%>/>
                                        </td>
                                        <td width="155" class="etiqueta" height="24">JARD&Iacute;N AISLAMIENTO</td>
                                        <td>
                                            <input type="text" class="casillaFecha" name="areTerInvJarAislamiento" onkeypress="return valida(this,'dec');" 
                                            value="<%=StringUtil.nullAsEmptyString((String)mapRecapitulacion.get("areTerInvJarAislamiento")).trim()%>" maxlength="10"
                                            <%=disabled%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;&Aacute;REA P&Uacute;BLICA</td>
                                        <td width="320">
                                            <input type="text" class="casillaFecha" name="areTerInvArePublica" onkeypress="return valida(this,'dec');" 
                                            value="<%=StringUtil.nullAsEmptyString((String)mapRecapitulacion.get("areTerInvArePublica")).trim()%>" maxlength="10"
                                            <%=disabled%>/>
                                        </td>
                                        <td width="155" class="etiqueta" height="24">&Aacute;REA INTANGIBLE</td>
                                        <td>
                                            <input type="text" class="casillaFecha" name="areTerInvAreIntangible" onkeypress="return valida(this,'dec');" 
                                            value="<%=StringUtil.nullAsEmptyString((String)mapRecapitulacion.get("areTerInvAreIntangible")).trim()%>" maxlength="10"
                                            <%=disabled%>/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">&nbsp;</td>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>RECAPITULACI&Oacute;N DE BIENES COMUNES:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaRecBienComun!=null && !listaRecBienComun.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th>EDITAR</th>
                                        <th>ELIMINAR</th>
                                        <th>N&Uacute;MERO</th>
                                        <th>EDIFICACI&Oacute;N</th>
                                        <th>ENTRADA</th>
                                        <th>PISO</th>
                                        <th>UNIDAD</th>
                                        <th>PORCENTAJE (%)</th>
                                        <th>ATC (M2)</th>
                                        <th>ACC (M2)</th>
                                        <th>AOIC (M2)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaRecBienComun.size();i++){%>
                                            <%HashMap mapRecBienComun = (HashMap)listaRecBienComun.get(i);%>
                                            <%String numero = (String)mapRecBienComun.get("numero");%>
                                            <%String edificacion = (String)mapRecBienComun.get("edificacion");%>
                                            <%String entrada = (String)mapRecBienComun.get("entrada");%>
                                            <%String piso = (String)mapRecBienComun.get("piso");%>
                                            <%String unidad = (String)mapRecBienComun.get("unidad");%>
                                            <%String porcentaje = (String)mapRecBienComun.get("porcentaje");%>
                                            <%String atc = (String)mapRecBienComun.get("atc");%>
                                            <%String acc = (String)mapRecBienComun.get("acc");%>
                                            <%String aoic = (String)mapRecBienComun.get("aoic");%>
                                            <tr class="normal">
                                                <td align="center"><a href='JavaScript:editarBienComun(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                <td align="center"><a href='JavaScript:eliminarBienComun(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(numero)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(edificacion)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(entrada)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(piso)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(unidad)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(porcentaje)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(atc)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(acc)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(aoic)%></td>
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
                                        <th>N&Uacute;MERO</th>
                                        <th>EDIFICACI&Oacute;N</th>
                                        <th>ENTRADA</th>
                                        <th>PISO</th>
                                        <th>UNIDAD</th>
                                        <th>PORCENTAJE (%)</th>
                                        <th>ATC (M2)</th>
                                        <th>ACC (M2)</th>
                                        <th>AOIC (M2)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="normal">
                                            <td align="center" colspan="11">No se encontraron registros.</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <%}%>
                            </td>
                        </tr>
                        <%if("disabled".equals(disabled)){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar Bien Com&uacute;n</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarBienComun();">Agregar Bien Com&uacute;n</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td colspan="4">&nbsp;</td>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DOCUMENTOS Y DATOS REGISTRALES:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;REGISTRO NOTARIAL DE LA ESCRITURA P&Uacute;BLICA</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE DE LA NOTARIA</td>
                            <td colspan="3">
                                <%String nomnotaria = StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("nomNotaria")).trim();%>
                                <select class="clsComboLargo" name="nomNotaria"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaNotaria.size();i++){%>
                                        <%String nomNotaria = ((NotariaBean)listaNotaria.get(i)).getIdNotaria().trim();%>
                                        <%String descripcion = ((NotariaBean)listaNotaria.get(i)).getIdNotaria().trim()+" - "+((NotariaBean)listaNotaria.get(i)).getNomNotaria().trim();%>
                                        <option <%if(nomnotaria.equals(nomNotaria)){%> selected <%}%> value="<%=nomNotaria%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;KARDEX</td>
                            <td width="320">
                                <input type="text" class="casillaDoc" name="kardex" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapNotaria.get("kardex")).trim()%>" maxlength="15"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">FECHA ESCRITURA P&Uacute;BLICA</td>
                            <td >
                                <input type="text" class="casillaFecha" name="fecEscPublica" id="fecEscPublica" onblur="validaFecha(this.value,'fecEscPublica');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapNotaria.get("fecEscPublica")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                                &nbsp;<a href="JavaScript:showCal('fecEscritura')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">&nbsp;</td>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>INSCRIPCI&Oacute;N EN REGISTRO DE PREDIOS:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO PARTIDA REGISTRAL</td>
                            <td width="320">
                                <%String codtipparregistral = StringUtil.nullAsEmptyString((String)mapInscripcion.get("codTipParRegistral")).trim();%>
                                <select class="clsComboMediano" name="codTipParRegistral"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTipParRegistral.size();i++){%>
                                        <%String codTipParRegistral = ((TablasCodigosBean)listaTipParRegistral.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaTipParRegistral.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipParRegistral.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codtipparregistral.equals(codTipParRegistral)){%> selected <%}%> value="<%=codTipParRegistral%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">N&Uacute;MERO</td>
                            <td>
                                <input type="text" class="casillaDoc" name="numPartida" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("numPartida")).trim()%>" maxlength="15"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FOJAS</td>
                            <td>
                                <input type="text" class="casillaDoc" name="fojas" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("fojas")).trim()%>" maxlength="15"
                                <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">ASIENTO</td>
                            <td>
                                <input type="text" class="casillaDoc" name="asiento" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("asiento")).trim()%>" maxlength="15"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA INSCRIPCI&Oacute;N PREDIO</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecInsPredio" id="fecInsPredio" onblur="validaFecha(this.value,'fecInsPredio');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("fecInsPredio")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                                &nbsp;<a href="JavaScript:showCal('fecInsPredio')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                            </td>
                            <td class="etiqueta" height="24">DECLARATORIA DE F&Aacute;BRICA</td>
                            <td>
                                <%String coddecfabrica = StringUtil.nullAsEmptyString((String)mapInscripcion.get("codDecFabrica")).trim();%>
                                <select class="clsComboMediano" name="codDecFabrica"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaDecFabrica.size();i++){%>
                                        <%String codDecFabrica = ((TablasCodigosBean)listaDecFabrica.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaDecFabrica.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaDecFabrica.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(coddecfabrica.equals(codDecFabrica)){%> selected <%}%> value="<%=codDecFabrica%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;AS. INS. DE F&Aacute;BRICA</td>
                            <td>
                                <input type="text" class="casillaDoc" name="asiInsFabrica" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("asiInsFabrica")).trim()%>" maxlength="15"
                                <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">FECHA INSCRIPCI&Oacute;N F&Aacute;BRICA</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecInsFabrica" id="fecInsFabrica" onblur="validaFecha(this.value,'fecInsFabrica');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("fecInsFabrica")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                                &nbsp;<a href="JavaScript:showCal('fecInsFabrica')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>INFORMACI&Oacute;N COMPLEMENTARIA:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CONDICI&Oacute;N DE DECLARANTE</td>
                            <td width="320">
                                <%String codcondeclarante = StringUtil.nullAsEmptyString((String)mapInformacion.get("codConDeclarante")).trim();%>
                                <select class="clsComboMediano" name="codConDeclarante"
                                <%=disabled%>>
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
                                <select class="clsComboLargo" name="codEstLleFicha"
                                <%=disabled%>>
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
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;MANTENIMIENTO</td>
                            <td colspan="3">
                                <%String codmantenimiento = StringUtil.nullAsEmptyString((String)mapInformacion.get("codMantenimiento")).trim();%>
                                <select class="clsComboLargo" name="codMantenimiento"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaMantenimiento.size();i++){%>
                                        <%String codMantenimiento = ((TablasCodigosBean)listaMantenimiento.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaMantenimiento.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaMantenimiento.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codmantenimiento.equals(codMantenimiento)){%> selected <%}%> value="<%=codMantenimiento%>">
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
                                <textarea name="observacion" rows="4" cols="20"  onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" class="casillaExtraLarga"
                                <%=disabled%>><%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("observacion")).trim()%></textarea>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("dniDeclarante")).trim()%>" size="10" maxlength="8"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">NOMBRES</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("nomDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                            <td width="320">
                                <input type="text" class="casillaLarga" name="apePatDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("apePatDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">APELIDO MATERNO</td>
                            <td>
                                <input type="text" class="casillaLarga" name="apeMatDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("apeMatDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA</td>
                            <td colspan="3">
                                <input type="text" class="casillaFecha" name="fecFirDeclarante" id="fecFirDeclarante" onblur="validaFecha(this.value,'fecFirDeclarante');" onkeypress="return valida(this,'fec');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("fecFirDeclarante")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirDecBienComun')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                <%String idsupervisor = StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("idSupervisor")).trim();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("fecFirSupervisor")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirSupBienComun')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                <%String idtecnico = StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("idTecnico")).trim();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("fecFirTecCatastral")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirTecCatBienComun')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                <%String idverificador = StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("idVerificador")).trim();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("fecFirVerCatastral")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirVerCatBienComun')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;Nº DE REGISTRO</td>
                            <td colspan="3">
                                <input type="text" class="casilla" name="numRegVerCatastral" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaBienComun.get("numRegVerCatastral")).trim()%>" size="10" maxlength="15"
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
    <%if(!codEstado.equals(Properties.EstadoRevisado) && !mapFichaBienComun.containsKey("flagOK")){%>
        <%if(mapFichaBienComun.containsKey("flagActualizar") && mapFichaBienComun.get("flagActualizar")!=null){%>
            <input class="buttoncenter" type="button" value="Actualizar Ficha" name="bActualizar" onclick="JavaScript:guardarFicha();"/>&nbsp;&nbsp;&nbsp;
        <%}else{%>
            <input class="buttoncenter" type="button" value="Guardar Ficha" name="bGuardar" onclick="JavaScript:guardarFicha();"/>&nbsp;&nbsp;&nbsp;
        <%}%>
    <%}%>
    <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar();"/>
</p>
</html:form>
<br>
</body>
</html:html>