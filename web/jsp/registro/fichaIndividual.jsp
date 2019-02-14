<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.maestro.bean.ClaPredioBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.ConDeclaranteBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.ConEspPredioBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.ConEspTitularBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.ConNumeracionBean" %>
<%@page import="com.bmp.sigeca.registro.bean.ConstruccionBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.ConTitularBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.DecFabricaBean" %>
<%@page import="com.bmp.sigeca.registro.bean.DocumentoBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.EstCivilBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.EstLleFichaBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.EvaPreCatastralBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.ForAdquisicionBean" %>
<%@page import="com.bmp.sigeca.registro.bean.LitiganteBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.MantenimientoBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.NotariaBean" %>
<%@page import="com.bmp.sigeca.registro.bean.ObraBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.PerJuridicaBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.SubClaPredioBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TipDocIdentidadBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TipEdificacionBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TipInteriorBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TipParRegistralBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TipPuertaBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TipTitularBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TipViaBean" %>
<%@page import="com.bmp.sigeca.registro.bean.TrabajadorBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.UbigeoBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.UbiPreCatastralBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.UsoBean" %>
<%@page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@page import="com.bmp.sigeca.registro.bean.ViaBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap mapFichaIndividual = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"): new HashMap();
    HashMap mapPredio = mapFichaIndividual.get("mapPredio")!=null?(HashMap)mapFichaIndividual.get("mapPredio"): new HashMap();
    HashMap mapTitular = mapFichaIndividual.get("mapTitular")!=null?(HashMap)mapFichaIndividual.get("mapTitular"): new HashMap();
    HashMap mapBienComun = mapFichaIndividual.get("mapBienComun")!=null?(HashMap)mapFichaIndividual.get("mapBienComun"): new HashMap();
    HashMap mapNotaria = mapFichaIndividual.get("mapNotaria")!=null?(HashMap)mapFichaIndividual.get("mapNotaria"): new HashMap();
    HashMap mapInscripcion = mapFichaIndividual.get("mapInscripcion")!=null?(HashMap)mapFichaIndividual.get("mapInscripcion"): new HashMap();
    HashMap mapInformacion = mapFichaIndividual.get("mapInformacion")!=null?(HashMap)mapFichaIndividual.get("mapInformacion"): new HashMap();
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipInterior = mapListas.get("listaTipInterior")!=null?(List)mapListas.get("listaTipInterior"):new ArrayList();
    List listaTipEdificacion =  mapListas.get("listaTipEdificacion")!=null?(List) mapListas.get("listaTipEdificacion"):new ArrayList();
    List listaTipTitular =  mapListas.get("listaTipTitular")!=null?(List) mapListas.get("listaTipTitular"):new ArrayList();
    List listaEstCivil =  mapListas.get("listaEstCivil")!=null?(List) mapListas.get("listaEstCivil"):new ArrayList();
    List listaTipDocIdentidad =  mapListas.get("listaTipDocIdentidad")!=null?(List) mapListas.get("listaTipDocIdentidad"):new ArrayList();
    List listaPerJuridica =  mapListas.get("listaPerJuridica")!=null?(List) mapListas.get("listaPerJuridica"):new ArrayList();
    List listaConEspTitular =  mapListas.get("listaConEspTitular")!=null?(List) mapListas.get("listaConEspTitular"):new ArrayList();
    List listaDepartamento =  mapListas.get("listaDepartamento")!=null?(List) mapListas.get("listaDepartamento"):new ArrayList();
    List listaProvincia =  mapListas.get("listaProvincia")!=null?(List) mapListas.get("listaProvincia"):new ArrayList();
    List listaDistrito =  mapListas.get("listaDistrito")!=null?(List) mapListas.get("listaDistrito"):new ArrayList();
    List listaTipVia =  mapListas.get("listaTipVia")!=null?(List) mapListas.get("listaTipVia"):new ArrayList();
    List listaConTitular =  mapListas.get("listaConTitular")!=null?(List) mapListas.get("listaConTitular"):new ArrayList();
    List listaForAdquisicion =  mapListas.get("listaForAdquisicion")!=null?(List) mapListas.get("listaForAdquisicion"):new ArrayList();
    List listaConEspPredio =  mapListas.get("listaConEspPredio")!=null?(List) mapListas.get("listaConEspPredio"):new ArrayList();
    List listaClaPredio =  mapListas.get("listaClaPredio")!=null?(List) mapListas.get("listaClaPredio"):new ArrayList();
    List listaUbiPreCatastral =  mapListas.get("listaUbiPreCatastral")!=null?(List) mapListas.get("listaUbiPreCatastral"):new ArrayList();
    List listaTipParRegistral =  mapListas.get("listaTipParRegistral")!=null?(List) mapListas.get("listaTipParRegistral"):new ArrayList();
    List listaDecFabrica =  mapListas.get("listaDecFabrica")!=null?(List) mapListas.get("listaDecFabrica"):new ArrayList();
    List listaEvaPreCatastral =  mapListas.get("listaEvaPreCatastral")!=null?(List) mapListas.get("listaEvaPreCatastral"):new ArrayList();
    List listaConDeclarante =  mapListas.get("listaConDeclarante")!=null?(List) mapListas.get("listaConDeclarante"):new ArrayList();
    List listaEstLleFicha =  mapListas.get("listaEstLleFicha")!=null?(List) mapListas.get("listaEstLleFicha"):new ArrayList();
    List listaMantenimiento =  mapListas.get("listaMantenimiento")!=null?(List) mapListas.get("listaMantenimiento"):new ArrayList();
    List listaVerificador = mapListas.get("listaVerificador")!=null?(List)mapListas.get("listaVerificador"): new ArrayList();
    List listaSupervisor = mapListas.get("listaSupervisor")!=null?(List)mapListas.get("listaSupervisor"): new ArrayList();
    List listaTecnico = mapListas.get("listaTecnico")!=null?(List)mapListas.get("listaTecnico"): new ArrayList();
    List listaUso = mapListas.get("listaUso")!=null?(List)mapListas.get("listaUso"): new ArrayList();
    List listaNotaria = mapListas.get("listaNotaria")!=null?(List)mapListas.get("listaNotaria"): new ArrayList();
    List listaHabUrbanas = mapListas.get("listaHabUrbanas")!=null?(List)mapListas.get("listaHabUrbanas"): new ArrayList();
    List listaVia = mapFichaIndividual.get("listaVia")!=null?(List)mapFichaIndividual.get("listaVia"): new ArrayList();
    List listaConstruccion = mapFichaIndividual.get("listaConstruccion")!=null?(List)mapFichaIndividual.get("listaConstruccion"): new ArrayList();
    List listaObra = mapFichaIndividual.get("listaObra")!=null?(List)mapFichaIndividual.get("listaObra"): new ArrayList();
    List listaDocumento = mapFichaIndividual.get("listaDocumento")!=null?(List)mapFichaIndividual.get("listaDocumento"): new ArrayList();
    List listaLitigante = mapFichaIndividual.get("listaLitigante")!=null?(List)mapFichaIndividual.get("listaLitigante"): new ArrayList();
    String mensaje = request.getAttribute("mensaje")!=null?(String)request.getAttribute("mensaje"):"";
    String codEstado = mapFichaIndividual.get("codEstado")!=null?(String)mapFichaIndividual.get("codEstado"):"";
    String accion = session.getAttribute("accion")!=null?(String)session.getAttribute("accion"):"";
    String disabled = "";
    if(codEstado.equals(Properties.EstadoRevisado) || accion.equals(Properties.Accion_Reportes)) disabled="disabled";
    else disabled = "";
    String disabledActualizar = "";
    if("disabled".equals(disabled) && !accion.equals(Properties.Accion_ActualizarPropietario)) disabledActualizar="disabled";
    else disabledActualizar = "";
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
    if(f.fecIniExoneracion.value!='' && f.fecVenExoneracion.value!=''){
        if(!compararFechaMayor(f.fecIniExoneracion.value,f.fecVenExoneracion.value)){
            alert('La Fecha de Fin de Exoneración debe ser posterior a la Fecha de Inicio de Exoneración');
            return false;
        }
    }
    if(f.fecInicio.value!='' && f.fecVencimiento.value!=''){
        if(!compararFechaMayor(f.fecInicio.value,f.fecVencimiento.value)){
            alert('La Fecha de Vencimiento debe ser posterior a la Fecha de Inicio');
            return false;
        }
    }
    if(Number(f.codTipTitular.value)=='<%=Properties.TipoTitular_PersonaJuridica%>' && f.numRuc.value!=''){
        var valor = f.numRuc.value;
        if(valor.substr(0,1)!='1' && valor.substr(0,1)!='2'){
            alert('Formato de RUC incorrecto');
            return false;
        }
    }
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
function agregarDocumento(){
    f = document.forms[0];
    f.method.value = "irAgregarDocumento";
    f.submit();
}
function eliminarDocumento(ind){
    f = document.forms[0];
    f.method.value = "eliminarDocumento";
    f.indListaDocumento.value = ind;
    f.submit();
}
function editarDocumento(ind){
    f = document.forms[0];
    f.method.value = "irEditarDocumento";
    f.indListaDocumento.value = ind;
    f.submit();
}
function agregarLitigante(){
    f = document.forms[0];
    f.method.value = "irAgregarLitigante";
    f.submit();
}
function eliminarLitigante(ind){
    f = document.forms[0];
    f.method.value = "eliminarLitigante";
    f.indListaLitigante.value = ind;
    f.submit();
}
function editarLitigante(ind){
    f = document.forms[0];
    f.method.value = "irEditarLitigante";
    f.indListaLitigante.value = ind;
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
function changeMaxLength(){
    f = document.forms[0];
    if(Number(f.codTipDocIdentidad.value)=='<%=Properties.TipoDoc_DNI%>'){
        f.numDocIdentidad.maxLength='8';
    }else if(Number(f.codTipDocIdentidad.value)=='<%=Properties.TipoDoc_CarnetExtranjeria%>'){
        f.numDocIdentidad.maxLength='10';
    }else if(Number(f.codTipDocIdentidad.value)=='<%=Properties.TipoDoc_Pasaporte%>'){
        f.numDocIdentidad.maxLength='8';
    }else{
        f.numDocIdentidad.maxLength='11';
    }
}
function changeMaxLength2(){
    f = document.forms[0];
    if(Number(f.codTipDocIdentidad2.value)=='<%=Properties.TipoDoc_DNI%>'){
        f.numDocIdentidad2.maxLength='8';
    }else if(Number(f.codTipDocIdentidad2.value)=='<%=Properties.TipoDoc_CarnetExtranjeria%>'){
        f.numDocIdentidad2.maxLength='10';
    }else if(Number(f.codTipDocIdentidad2.value)=='<%=Properties.TipoDoc_Pasaporte%>'){
        f.numDocIdentidad2.maxLength='8';
    }else{
        f.numDocIdentidad2.maxLength='11';
    }
}
function changeTipoTitular(){
    f = document.forms[0];
    if(Number(f.codTipTitular.value)=='<%=Properties.TipoTitular_PersonaNatural%>'){
        document.getElementById('personanatural').style.display='';
        document.getElementById('personajuridica').style.display='none';
    }else if(Number(f.codTipTitular.value)=='<%=Properties.TipoTitular_PersonaJuridica%>'){
        document.getElementById('personanatural').style.display='none';
        document.getElementById('personajuridica').style.display='';
    }
}
function inicio(){
    f = document.forms[0];
    changeTipoTitular();
    changeMaxLength();
}
function showMessage(){
    <%if(codEstado.equals(Properties.EstadoRevisado)){%>
        document.all.disabled = true;
    <%}%>
    <%if(mensaje!=null && !"".equals(mensaje)){%>
        alert('<%=mensaje%>');
    <%}%>
}
</script>
<body onload="JavaScript:showMessage();inicio();">
<br>
<html:form action="fichaIndividual-action" method="post" styleId="fichaIndividual" >
<table width="980" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1" colspan="6">FICHA CATASTRAL URBANA INDIVIDUAL</td>
    </tr>
    <tr>
        <td colspan="6">&nbsp;</td>
    </tr>
    <tr>
        <td width="150" height="24">&nbsp;</td>
        <td width="100">N&Uacute;MERO DE FICHA</td>
        <td>
            <input type="text" class="casilla" name="numFicha" onkeypress="return valida(this,'int');"
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("numFicha"))%>" size="7" maxlength="7" <%=disabled%>/>
        </td>
        <td width="160">N&Uacute;MERO DE FICHAS POR LOTE</td>
        <td>
            <input type="text" class="casilla" name="numFichLote" onkeypress="return valida(this,'int');" 
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("numFichLote"))%>" size="1" maxlength="2" <%=disabled%>/>
            <input type="text" class="casilla" name="numTotFichLote" onkeypress="return valida(this,'int');" 
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("numTotFichLote"))%>" size="1" maxlength="2" <%=disabled%>/>
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
        <input type="hidden" name="indListaDocumento">
        <input type="hidden" name="indListaLitigante">
        <input type="hidden" name="nomHUPre">
        <input type="hidden" name="sectorPre">
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>UBICACI&Oacute;N DEL PREDIO CATASTRAL:
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
                                                <%if("disabled".equals(disabled)){%>
                                                    <td align="center"><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></td>
                                                    <td align="center"><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></td>
                                                <%}else{%>
                                                    <td align="center"><a href='JavaScript:editarVia(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                    <td align="center"><a href='JavaScript:eliminarVia(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <%}%>
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
                                <select class="clsComboDoc" name="codTipEdificacionPre"
                                <%=disabled%>>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("nomEdificacion")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DE INTERIOR</td>
                            <td>
                                <%String codtipinterior = StringUtil.nullAsEmptyString((String)mapPredio.get("codTipInterior")).trim();%>
                                <select class="clsComboDoc" name="codTipInteriorPre"
                                <%=disabled%>>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numInterior")).trim()%>" size="1" maxlength="4"
                                <%=disabled%>/>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("codHabUrbana")).trim()%>" size="1" maxlength="4"
                                <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">NOMBRE DE LA HU</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomHUPre" onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("nomHabUrbana")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ZONA/SECTOR/ETAPA</td>
                            <td>
                                <input type="text" class="casilla" name="sectorPre" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("sector")).trim()%>" size="20" maxlength="20"
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>IDENTIFICACI&Oacute;N DEL TITULAR CATASTRAL:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DE TITULAR</td>
                            <td colspan="3">
                                <%String codtiptitular = StringUtil.nullAsEmptyString((String)mapTitular.get("codTipTitular")).trim();%>
                                <select class="clsComboMediano" name="codTipTitular" onchange="changeTipoTitular();"
                                <%=disabledActualizar%>>
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
                                            <%String codtipdocidentidad = StringUtil.nullAsEmptyString((String)mapTitular.get("codTipDocIdentidad")).trim();%>
                                            <select class="clsComboLargo" name="codTipDocIdentidad" id="codTipDocIdentidad"
                                                <%=disabledActualizar%> onchange="changeMaxLength();">
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
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("numDocIdentidad")).trim()%>" size="9"
                                            <%=disabledActualizar%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ESTADO CIVIL</td>
                                        <td colspan="3">
                                            <%String codestcivil = StringUtil.nullAsEmptyString((String)mapTitular.get("codEstCivil")).trim();%>
                                            <select class="clsComboDoc" name="codEstCivil"
                                            <%=disabledActualizar%>>
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
                                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES</td>
                                        <td colspan="3">
                                            <input type="text" class="casillaLarga" name="nombre" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("nombre")).trim()%>" maxlength="50"
                                            <%=disabledActualizar%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                                        <td>
                                            <input type="text" class="casilla" name="apePaterno" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("apePaterno")).trim()%>" size="32" maxlength="30"
                                            <%=disabledActualizar%>/>
                                        </td>
                                        <td class="etiqueta" height="24">APELLIDO MATERNO</td>
                                        <td>
                                            <input type="text" class="casilla" name="apeMaterno" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("apeMaterno")).trim()%>" size="32" maxlength="30"
                                            <%=disabledActualizar%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DOC. IDENTIDAD</td>
                                        <td width="320">
                                            <%String codtipdocidentidad2 = StringUtil.nullAsEmptyString((String)mapTitular.get("codTipDocIdentidad2")).trim();%>
                                            <select class="clsComboLargo" name="codTipDocIdentidad2" id="codTipDocIdentidad2"
                                                <%=disabledActualizar%> onchange="changeMaxLength2();">
                                                <option value="">Seleccione</option>
                                                <%for(int i=0;i<listaTipDocIdentidad.size();i++){%>
                                                    <%String codTipDocIdentidad = ((TablasCodigosBean)listaTipDocIdentidad.get(i)).getCodigo().trim();%>
                                                    <%String descripcion = ((TablasCodigosBean)listaTipDocIdentidad.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaTipDocIdentidad.get(i)).getDesc_codigo().trim();%>
                                                    <option <%if(codtipdocidentidad2.equals(codTipDocIdentidad)){%> selected <%}%> value="<%=codTipDocIdentidad%>">
                                                        <%=descripcion%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </td>
                                        <td width="155" class="etiqueta" height="24">N&Uacute;MERO DE DOCUMENTO</td>
                                        <td>
                                            <input type="text" class="casilla" name="numDocIdentidad2" id="numDocIdentidad2" onkeypress="return valida(this,'int');"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("numDocIdentidad2")).trim()%>" size="9"
                                            <%=disabledActualizar%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES</td>
                                        <td colspan="3">
                                            <input type="text" class="casillaLarga" name="nombre2" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("nombre2")).trim()%>" maxlength="50"
                                            <%=disabledActualizar%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                                        <td>
                                            <input type="text" class="casilla" name="apePaterno2" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("apePaterno2")).trim()%>" size="32" maxlength="30"
                                            <%=disabledActualizar%>/>
                                        </td>
                                        <td class="etiqueta" height="24">APELLIDO MATERNO</td>
                                        <td>
                                            <input type="text" class="casilla" name="apeMaterno2" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("apeMaterno2")).trim()%>" size="32" maxlength="30"
                                            <%=disabledActualizar%>/>
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
                                        <td width="318">
                                            <input type="text" class="casilla" name="numRuc"onkeypress="return valida(this,'int');"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("numRuc")).trim()%>" size="9" maxlength="11"
                                            <%=disabledActualizar%>/>
                                      </td>
                                        <td width="157" class="etiqueta" height="24">RAZON SOCIAL</td>
                                        <td width="316">
                                            <input type="text" class="casillaLarga" name="razSocial" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("razSocial")).trim()%>" maxlength="50"
                                            <%=disabledActualizar%>/>
                                      </td>
                                    </tr>
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;PERSONA JUR&Iacute;DICA</td>
                                        <td colspan="3">
                                            <%String codperjuridica = StringUtil.nullAsEmptyString((String)mapTitular.get("codPerJuridica")).trim();%>
                                            <select class="clsComboDoc" name="codPerJuridica"
                                            <%=disabledActualizar%>>
                                                <option value="">Seleccione</option>
                                                <%for(int i=0;i<listaPerJuridica.size();i++){%>
                                                    <%String codPerJuridica = ((TablasCodigosBean)listaPerJuridica.get(i)).getCodigo().trim();%>
                                                    <%String descripcion = ((TablasCodigosBean)listaPerJuridica.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaPerJuridica.get(i)).getDesc_codigo().trim();%>
                                                    <option <%if(codperjuridica.equals(codPerJuridica)){%> selected <%}%> value="<%=codPerJuridica%>">
                                                        <%=descripcion%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;COND. ESP. DEL TITULAR</td>
                            <td colspan="3">
                                <%String codconesptitular = StringUtil.nullAsEmptyString((String)mapTitular.get("codConEspTitular")).trim();%>
                                <select class="clsComboLargo" name="codConEspTitular"
                                <%=disabledActualizar%>>
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
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;Nº RESOL. EXONERACION</td>
                            <td width="319">
                                <input type="text" class="casillaFecha" name="numResExoneracion" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("numResExoneracion")).trim()%>" size="9" maxlength="10"
                                <%=disabledActualizar%>/>
                          </td>
                            <td width="156" class="etiqueta" height="24">Nº BOL. PENSIONISTA</td>
                            <td width="318">
                                <input type="text" class="casillaFecha" name="numBolPensionista" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("numBolPensionista")).trim()%>" size="9" maxlength="10"
                                <%=disabledActualizar%>/>
                          </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;INICIO EXONERACION</td>
                            <td width="319">
                                <input type="text" class="casillaFecha" name="fecIniExoneracion" id="fecIniExoneracion" onblur="validaFecha(this.value,'fecIniExoneracion');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("fecIniExoneracion")).trim()%>" maxlength="10"
                                <%=disabledActualizar%>/>
                                <%if("disabled".equals(disabledActualizar)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecIniExoneracion')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                          </td>
                            <td width="156" class="etiqueta" height="24">FIN EXONERACION</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecVenExoneracion" id="fecVenExoneracion" onblur="validaFecha(this.value,'fecVenExoneracion');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("fecVenExoneracion")).trim()%>" maxlength="10"
                                <%=disabledActualizar%>/>
                                <%if("disabled".equals(disabledActualizar)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecVenExoneracion')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DOMICILIO FISCAL DEL TITULAR CATASTRAL:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DEPARTAMENTO</td>
                            <td colspan="3">
                                <%String coddepartamento = StringUtil.nullAsEmptyString((String)mapTitular.get("codDepartamento")).trim();%>
                                <select class="clsComboMediano" name="codDepartamento" onchange="JavaScript:cargarProvincia()"
                                <%=disabledActualizar%>>
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
                                <%String codprovincia = StringUtil.nullAsEmptyString((String)mapTitular.get("codProvincia")).trim();%>
                                <select class="clsComboMediano" name="codProvincia" onchange="JavaScript:cargarDistrito()"
                                <%=disabledActualizar%>>
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
                                <%String coddistrito = StringUtil.nullAsEmptyString((String)mapTitular.get("codDistrito")).trim();%>
                                <select class="clsComboMediano" name="codDistrito"
                                <%=disabledActualizar%>>
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
                                <input type="text" class="casilla" name="telefonoTit" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("telefono")).trim()%>" size="10" maxlength="12"
                                <%=disabledActualizar%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">ANEXO</td>
                            <td>
                                <input type="text" class="casilla" name="anexoTit" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("anexo")).trim()%>" size="1" maxlength="4"
                                <%=disabledActualizar%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FAX</td>
                            <td>
                                <input type="text" class="casilla" name="faxTit" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("fax")).trim()%>" size="10" maxlength="12"
                                <%=disabledActualizar%>/>
                            </td>
                            <td class="etiqueta" height="24">CORREO ELECTR&Oacute;NICO</td>
                            <td>
                                <input type="text" class="casillaLarga" name="correoTit" id="correoTit" onblur="validaEmail(this.value,'correoTit');" onkeypress="return valida(this,'email');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("corElectronico")).trim()%>" maxlength="50"
                                <%=disabledActualizar%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE V&Iacute;A</td>
                            <td>
                                <input type="text" class="casilla" name="codViaTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("codVia")).trim()%>" size="5" maxlength="6"
                                <%=disabledActualizar%>/>
                            </td>
                            <td class="etiqueta" height="24">TIPO DE V&Iacute;A</td>
                            <td>
                                <%String codtipvia = StringUtil.nullAsEmptyString((String)mapTitular.get("codTipVia")).trim();%>
                                <select class="clsComboDoc" name="codTipViaTit"
                                <%=disabledActualizar%>>
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
                                <input type="text" class="casillaLarga" name="nomViaTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("nomVia")).trim()%>" maxlength="50"
                                <%=disabledActualizar%>/>
                            </td>
                            <td class="etiqueta" height="24">N&Uacute;MERO MUNICIPAL</td>
                            <td>
                                <input type="text" class="casilla" name="numMunicipalTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("numMunicipal")).trim()%>" size="5" maxlength="6"
                                <%=disabledActualizar%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE DE EDIFICACI&Oacute;N</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomEdificacionTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("nomEdificacion")).trim()%>" maxlength="50"
                                <%=disabledActualizar%>/>
                            </td>
                            <td class="etiqueta" height="24">N&Uacute;MERO INTERIOR</td>
                            <td>
                                <input type="text" class="casilla" name="numInteriorTit" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("numInterior")).trim()%>" size="1" maxlength="4"
                                <%=disabledActualizar%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO HU</td>
                            <td>
                                <input type="text" class="casilla" name="codHUTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("codHabUrbana")).trim()%>" size="1" maxlength="4"
                                <%=disabledActualizar%>/>
                            </td>
                            <td class="etiqueta" height="24">NOMBRE DE LA HU</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomHUTit" onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("nomHabUrbana")).trim()%>" maxlength="50"
                                <%=disabledActualizar%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ZONA/SECTOR/ETAPA</td>
                            <td>
                                <input type="text" class="casilla" name="sectorTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("sector")).trim()%>" size="20" maxlength="20"
                                <%=disabledActualizar%>/>
                            </td>
                            <td class="etiqueta" height="24">MANZANA</td>
                            <td>
                                <input type="text" class="casilla" name="manzanaTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("manzana")).trim()%>" size="1" maxlength="3"
                                <%=disabledActualizar%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;LOTE</td>
                            <td>
                                <input type="text" class="casilla" name="loteTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("lote")).trim()%>" size="1" maxlength="3"
                                <%=disabledActualizar%>/>
                            </td>
                            <td class="etiqueta" height="24">SUB-LOTE</td>
                            <td>
                                <input type="text" class="casilla" name="subloteTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapTitular.get("sublote")).trim()%>" size="1" maxlength="4"
                                <%=disabledActualizar%>/>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>CARACTER&Iacute;STICAS DE LA TITULARIDAD:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CONDICI&Oacute;N DEL TITULAR</td>
                            <td width="320">
                                <%String codcontitular = StringUtil.nullAsEmptyString((String)mapPredio.get("codConTitular")).trim();%>
                                <select class="clsComboMediano" name="codConTitular"
                                <%=disabledActualizar%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaConTitular.size();i++){%>
                                        <%String codConTitular = ((TablasCodigosBean)listaConTitular.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaConTitular.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaConTitular.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codcontitular.equals(codConTitular)){%> selected <%}%> value="<%=codConTitular%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">FORMA DE ADQUISICI&Oacute;N</td>
                            <td>
                                <%String codforadquisicion = StringUtil.nullAsEmptyString((String)mapPredio.get("codForAdqPredio")).trim();%>
                                <select class="clsComboLargo" name="codForAdqPredio"
                                <%=disabledActualizar%>>
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
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA DE ADQUISICI&Oacute;N</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecAdquisicion" id="fecAdquisicion" onblur="validaFecha(this.value,'fecAdquisicion');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("fecAdquisicion")).trim()%>" maxlength="10"
                                <%=disabledActualizar%>/>
                                <%if("disabled".equals(disabledActualizar)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecAdquisicion')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                            <td class="etiqueta" height="24">COND. ESP. DEL PREDIO</td>
                            <td>
                                <%String codconesppredio = StringUtil.nullAsEmptyString((String)mapPredio.get("codConEspPredio")).trim();%>
                                <select class="clsComboLargo" name="codConEspPredio"
                                <%=disabledActualizar%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaConEspPredio.size();i++){%>
                                        <%String codConEspPredio = ((TablasCodigosBean)listaConEspPredio.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaConEspPredio.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaConEspPredio.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codconesppredio.equals(codConEspPredio)){%> selected <%}%> value="<%=codConEspPredio%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;Nº RESOL. EXONERACION</td>
                            <td>
                                <input type="text" class="casilla" name="numResExoPredio" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numResExoPredio")).trim()%>" size="20" maxlength="20"
                                <%=disabledActualizar%>/>
                            </td>
                            <td class="etiqueta" height="24">PORCENTAJE</td>
                            <td>
                                <input type="text" class="casilla" name="porcentaje" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("porcentaje")).trim()%>" size="3" maxlength="7"
                                <%=disabledActualizar%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA DE INICIO</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecInicio" id="fecInicio" onblur="validaFecha(this.value,'fecInicio');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("fecInicio")).trim()%>" maxlength="10"
                                <%=disabledActualizar%>/>
                                <%if("disabled".equals(disabledActualizar)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecInicio')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                            <td class="etiqueta" height="24">FECHA DE VENCIMIENTO</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecVencimiento" id="fecVencimiento" onblur="validaFecha(this.value,'fecVencimiento');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("fecVencimiento")).trim()%>" maxlength="10"
                                <%=disabledActualizar%>/>
                                <%if("disabled".equals(disabledActualizar)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecVencimiento')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DESCRIPCI&Oacute;N DEL PREDIO:
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
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;PREDIO CATASTRAL EN</td>
                            <td>
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
                            <td class="etiqueta" height="24">USO PREDIO CATASTRAL</td>
                            <td>
                                <%String codusoprecatastral = StringUtil.nullAsEmptyString((String)mapPredio.get("codUsoPreCatastral"));%>
                                <select class="clsComboLargo" name="codUsoPreCatastral"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaUso.size();i++){%>
                                        <%String codUsoPreCatastral = ((UsoBean)listaUso.get(i)).getCodUso().trim();%>
                                        <%String descripcion = ((UsoBean)listaUso.get(i)).getCodUso().trim()+" - "+((UsoBean)listaUso.get(i)).getDesUso().trim();%>
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
                            <td class="etiqueta" height="24">&Aacute;REA TERRENO DECLARADA</td>
                            <td>
                                <input type="text" class="casillaFecha" name="areTerDeclarada" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("areTerDeclarada")).trim()%>" maxlength="10"
                                <%=disabled%>/>&nbsp;(M2)
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;&Aacute;REA TERRENO VERIFICADA</td>
                            <td colspan="3">
                                <input type="text" class="casillaFecha" name="areTerVerificada" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("areTerVerificada")).trim()%>" maxlength="10"
                                <%if(codEstado.equals(Properties.EstadoRevisado) && !accion.equals(Properties.Accion_RectificarArea)){%> disabled <%}%>/>&nbsp;(M2)
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
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
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="medCamFrente" onkeypress="return valida(this,'decimales');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medCamFrente")).trim()%>" maxlength="20"
                                                <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="medTitFrente" onkeypress="return valida(this,'decimales');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medTitFrente")).trim()%>" maxlength="20"
                                                <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="colCamFrente" onkeypress="return valida(this,'all');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colCamFrente")).trim()%>" maxlength="20"
                                                onkeyup="this.value=this.value.toUpperCase();" <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="colTitFrente" onkeypress="return valida(this,'all');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colTitFrente")).trim()%>" maxlength="20"
                                                onkeyup="this.value=this.value.toUpperCase();" <%=disabled%>/>
                                            </td>
                                        </tr>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;DERECHA</b></td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="medCamDerecha" onkeypress="return valida(this,'decimales');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medCamDerecha")).trim()%>" maxlength="20"
                                                <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="medTitDerecha" onkeypress="return valida(this,'decimales');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medTitDerecha")).trim()%>" maxlength="20"
                                                <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="colCamDerecha" onkeypress="return valida(this,'all');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colCamDerecha")).trim()%>" maxlength="20"
                                                onkeyup="this.value=this.value.toUpperCase();" <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="colTitDerecha" onkeypress="return valida(this,'all');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colTitDerecha")).trim()%>" maxlength="20"
                                                onkeyup="this.value=this.value.toUpperCase();" <%=disabled%>/>
                                            </td>
                                        </tr>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;IZQUIERDA</b></td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="medCamIzquierda" onkeypress="return valida(this,'decimales');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medCamIzquierda")).trim()%>" maxlength="20"
                                                <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="medTitIzquierda" onkeypress="return valida(this,'decimales');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medTitIzquierda")).trim()%>" maxlength="20"
                                                <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="colCamIzquierda" onkeypress="return valida(this,'all');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colCamIzquierda")).trim()%>" maxlength="20"
                                                onkeyup="this.value=this.value.toUpperCase();" <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="colTitIzquierda" onkeypress="return valida(this,'all');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colTitIzquierda")).trim()%>" maxlength="20"
                                                onkeyup="this.value=this.value.toUpperCase();" <%=disabled%>/>
                                            </td>
                                        </tr>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;FONDO</b></td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="medCamFondo" onkeypress="return valida(this,'decimales');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medCamFondo")).trim()%>" maxlength="20"
                                                <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="medTitFondo" onkeypress="return valida(this,'decimales');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("medTitFondo")).trim()%>" maxlength="20"
                                                <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="colCamFondo" onkeypress="return valida(this,'all');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colCamFondo")).trim()%>" maxlength="20"
                                                onkeyup="this.value=this.value.toUpperCase();" <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaDatos" name="colTitFondo" onkeypress="return valida(this,'all');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("colTitFondo")).trim()%>" maxlength="20"
                                                onkeyup="this.value=this.value.toUpperCase();" <%=disabled%>/>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
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
                                <input type="checkbox" name="luz" class="casilla" <%if("on".equals(luz)){%> checked <%}%> <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">Nº SUMINISTRO LUZ</td>
                            <td>
                                <input type="text" class="casillaDoc" name="numSumLuz" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numSumLuz")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;AGUA</td>
                            <td width="320">
                                <%String agua = StringUtil.nullAsEmptyString((String)mapPredio.get("agua")).trim();%>
                                <input type="checkbox" name="agua" class="casilla" <%if("on".equals(agua)){%> checked <%}%> <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">Nº CONTRATO AGUA</td>
                            <td>
                                <input type="text" class="casillaDoc" name="numConAgua" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numConAgua")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TEL&Eacute;FONO</td>
                            <td width="320">
                                <%String telefono = StringUtil.nullAsEmptyString((String)mapPredio.get("telefono")).trim();%>
                                <input type="checkbox" name="telefono" class="casilla" <%if("on".equals(telefono)){%> checked <%}%> <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">Nº TEL&Eacute;FONO</td>
                            <td>
                                <input type="text" class="casillaDoc" name="numTelefono" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapPredio.get("numTelefono")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DESAGÜE</td>
                            <td colspan="3">
                                <%String desague = StringUtil.nullAsEmptyString((String)mapPredio.get("desague")).trim();%>
                                <input type="checkbox" class="casilla" name="desague" <%if("on".equals(desague)){%> checked <%}%> <%=disabled%>/>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>CONSTRUCCIONES:
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
                                                <%if("disabled".equals(disabled)){%>
                                                    <td align="center"><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></td>
                                                    <td align="center"><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></td>
                                                <%}else{%>
                                                    <td align="center"><a href='JavaScript:editarConstruccion(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                    <td align="center"><a href='JavaScript:eliminarConstruccion(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <%}%>
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
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarConstruccion()">Agregar Construcci&oacute;n</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td class="etiqueta" colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;PORCENTAJE DE BIEN COM&Uacute;N</td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <table cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TERRENO LEGAL</td>
                                        <td width="320">
                                            <input type="text" class="casilla" name="terLegal" onkeypress="return valida(this,'dec');" 
                                            value="<%=StringUtil.nullAsEmptyString((String)mapBienComun.get("porTerLegBienComun")).trim()%>" size="2" maxlength="5"
                                            <%=disabled%>/>
                                        </td>
                                        <td width="155" class="etiqueta" height="24">TERRENO F&Iacute;SICO</td>
                                        <td>
                                            <input type="text" class="casilla" name="terFisico" onkeypress="return valida(this,'dec');" 
                                            value="<%=StringUtil.nullAsEmptyString((String)mapBienComun.get("porTerFisBienComun")).trim()%>" size="2" maxlength="5"
                                            <%=disabled%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CONSTRUCCI&Oacute;N LEGAL</td>
                                        <td width="320">
                                            <input type="text" class="casilla" name="conLegal" onkeypress="return valida(this,'dec');" 
                                            value="<%=StringUtil.nullAsEmptyString((String)mapBienComun.get("porConLegBienComun")).trim()%>" size="2" maxlength="5"
                                            <%=disabled%>/>
                                        </td>
                                        <td width="155" class="etiqueta" height="24">CONSTRUCCI&Oacute;N F&Iacute;SICA</td>
                                        <td>
                                            <input type="text" class="casilla" name="conFisica" onkeypress="return valida(this,'dec');" 
                                            value="<%=StringUtil.nullAsEmptyString((String)mapBienComun.get("porConFisBienComun")).trim()%>" size="2" maxlength="5"
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
                                            <%String uniMedida = (String)mapObra.get("uniMedida");%>
                                            <tr class="normal">
                                                <%if("disabled".equals(disabled)){%>
                                                    <td align="center"><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></td>
                                                    <td align="center"><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></td>
                                                <%}else{%>
                                                    <td align="center"><a href='JavaScript:editarObra(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                    <td align="center"><a href='JavaScript:eliminarObra(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <%}%>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(desInstalacion)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(fecConstruccion)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(largo)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(ancho)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(alto)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(total)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(uniMedida)%></td>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DOCUMENTOS:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaDocumento!=null && !listaDocumento.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th>EDITAR</th>
                                        <th>ELIMINAR</th>
                                        <th>TIPO DE DOCUMENTO</th>
                                        <th>Nº DE DOCUMENTO</th>
                                        <th>FECHA</th>
                                        <th>AREA AUTORIZADA</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaDocumento.size();i++){%>
                                            <%HashMap mapDocumento = (HashMap)listaDocumento.get(i);%>
                                            <%String desTipDocumento = (String)mapDocumento.get("desTipDocumento");%>
                                            <%String numDocumento = (String)mapDocumento.get("numDocumento");%>
                                            <%String fecha = (String)mapDocumento.get("fecha");%>
                                            <%String areAutorizada = (String)mapDocumento.get("area");%>
                                            <tr class="normal">
                                                <%if("disabled".equals(disabled)){%>
                                                    <td align="center"><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></td>
                                                    <td align="center"><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></td>
                                                <%}else{%>
                                                    <td align="center"><a href='JavaScript:editarDocumento(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                    <td align="center"><a href='JavaScript:eliminarDocumento(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <%}%>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(desTipDocumento)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(numDocumento)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(fecha)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(areAutorizada)%></td>
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
                                        <th>TIPO DE DOCUMENTO</th>
                                        <th>Nº DE DOCUMENTO</th>
                                        <th>FECHA</th>
                                        <th>AREA AUTORIZADA</th>
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
                        <%if("disabled".equals(disabled)){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar Documento</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarDocumento();">Agregar Documento</a></td>
                            </tr>
                        <%}%>
                        
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;REGISTRO NOTARIAL DE LA ESCRITURA P&Uacute;BLICA</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE DE LA NOTARIA</td>
                            <td colspan="3">
                                <%String nomnotaria = StringUtil.nullAsEmptyString((String)mapNotaria.get("nomNotaria")).trim();%>
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
                                <input type="text" class="casilla" name="kardex" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapNotaria.get("kardex")).trim()%>" maxlength="20"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">FECHA ESCRITURA P&Uacute;BLICA</td>
                            <td >
                                <input type="text" class="casillaFecha" name="fecEscPublica" id="fecEscPublica" onblur="validaFecha(this.value,'fecEscPublica');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapNotaria.get("fecEscPublica")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecEscritura')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>INSCRIPCI&Oacute;N DEL PREDIO CATASTRAL EN EL REGISTRO DE PREDIOS:
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
                                <input type="text" class="casilla" name="numPartida" onkeypress="return valida(this,'int');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("numPartida")).trim()%>" maxlength="18"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FOJAS</td>
                            <td>
                                <input type="text" class="casilla" name="fojas" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("fojas")).trim()%>" maxlength="18"
                                <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">ASIENTO</td>
                            <td>
                                <input type="text" class="casilla" name="asiento" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("asiento")).trim()%>" maxlength="18"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA INSCRIPCI&Oacute;N PREDIO</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecInsPredio" id="fecInsPredio" onblur="validaFecha(this.value,'fecInsPredio');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("fecInsPredio")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecInsPredio')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
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
                                <input type="text" class="casilla" name="asiInsFabrica" onkeypress="return valida(this,'afn').trim();" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("asiInsFabrica"))%>" maxlength="18"
                                <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">FECHA INSCRIPCI&Oacute;N F&Aacute;BRICA</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecInsFabrica" id="fecInsFabrica" onblur="validaFecha(this.value,'fecInsFabrica');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInscripcion.get("fecInsFabrica")).trim()%>" maxlength="10"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecInsFabrica')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>EVALUACI&Oacute;N DEL PREDIO CATASTRAL:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;EVALUACI&Oacute;N DEL PREDIO</td>
                            <td colspan="3">
                                <%String codevaprecatastral = StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("codEvaPreCatastral")).trim();%>
                                <select class="clsComboLargo" name="codEvaPreCatastral"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaEvaPreCatastral.size();i++){%>
                                        <%String codEvaPreCatastral = ((TablasCodigosBean)listaEvaPreCatastral.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaEvaPreCatastral.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaEvaPreCatastral.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codevaprecatastral.equals(codEvaPreCatastral)){%> selected <%}%> value="<%=codEvaPreCatastral%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;&Aacute;REA DE TERRENO INVADIDA (M2)</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;EN LOTE COLINDANTE</td>
                            <td width="320">
                                <input type="text" class="casilla" name="areTerInvLotColindante" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("areTerInvLotColindante")).trim()%>" size="5" maxlength="7"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">EN &Aacute;REA P&Aacute;BLICA</td>
                            <td>
                                <input type="text" class="casilla" name="areTerInvArePublica" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("areTerInvArePublica")).trim()%>" size="5" maxlength="7"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;EN JARD&Iacute;N DE AISLAMIENTO</td>
                            <td width="320">
                                <input type="text" class="casilla" name="areTerInvJarAislamiento" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("areTerInvJarAislamiento")).trim()%>" size="5" maxlength="7"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">EN &Aacute;REA INTANGIBLE</td>
                            <td >
                                <input type="text" class="casilla" name="areTerInvAreIntangible" onkeypress="return valida(this,'dec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("areTerInvAreIntangible")).trim()%>" size="5" maxlength="7"
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
                            <td colspan="4" valign="top">
                                <%if(listaLitigante!=null && !listaLitigante.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th>EDITAR</th>
                                        <th>ELIMINAR</th>
                                        <th>TIPO DE DOCUMENTO</th>
                                        <th>Nº DE DOCUMENTO</th>
                                        <th>APELLIDOS Y NOMBRES DE LOS LITIGANTES</th>
                                        <th>C&Oacute;DIGO DEL CONTRIBUYENTE</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaLitigante.size();i++){%>
                                            <%HashMap mapLitigante = (HashMap)listaLitigante.get(i);%>
                                            <%String desTipDocumento = (String)mapLitigante.get("desTipDocIdentidad");%>
                                            <%String numDocumento = (String)mapLitigante.get("numDocumento");%>
                                            <%String nombreCompleto = (String)mapLitigante.get("nomCompleto");%>
                                            <%String codContribuyente = (String)mapLitigante.get("codContribuyente");%>
                                            <tr class="normal">
                                                <%if("disabled".equals(disabled)){%>
                                                    <td align="center"><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></td>
                                                <td align="center"><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></td>
                                                <%}else{%>
                                                    <td align="center"><a href='JavaScript:editarLitigante(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                    <td align="center"><a href='JavaScript:eliminarLitigante(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <%}%>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(desTipDocumento)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(numDocumento)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(nombreCompleto)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(codContribuyente)%></td>
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
                                        <th>TIPO DE DOCUMENTO</th>
                                        <th>Nº DE DOCUMENTO</th>
                                        <th>APELLIDOS Y NOMBRES DE LOS LITIGANTES</th>
                                        <th>C&Oacute;DIGO DEL CONTRIBUYENTE</th>
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
                        <%if("disabled".equals(disabled)){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar Litigante</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarLitigante();">Agregar Litigante</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;Nº DE HABITANTES</td>
                            <td width="320">
                                <input type="text" class="casilla" name="numHabitantes" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInformacion.get("numHabitantes")).trim()%>" size="1" maxlength="3"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">Nº DE FAMILIAS</td>
                            <td>
                                <input type="text" class="casilla" name="numFamilias"  onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapInformacion.get("numFamilias")).trim()%>" size="1" maxlength="3"
                                <%=disabled%>/>
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
                                <%=disabled%>><%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("observacion")).trim()%></textarea>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("dniDeclarante")).trim()%>" size="10" maxlength="8"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">NOMBRES</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("nomDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                            <td width="320">
                                <input type="text" class="casillaLarga" name="apePatDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("apePatDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">APELIDO MATERNO</td>
                            <td>
                                <input type="text" class="casillaLarga" name="apeMatDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("apeMatDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA</td>
                            <td colspan="3">
                                <input type="text" class="casillaFecha" name="fecFirDeclarante" id="fecFirDeclarante" onblur="validaFecha(this.value,'fecFirDeclarante');" onkeypress="return valida(this,'fec');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("fecFirDeclarante")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirDeclarante')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                <%String idsupervisor = StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("idSupervisor")).trim();%>
                                <select class="clsComboLargo" name="idSupervisor"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaSupervisor.size();i++){%>
                                        <%String idSupervisor = ((TrabajadorBean)listaSupervisor.get(i)).getId_trabajador();%>
                                        <%String nombres = ((TrabajadorBean)listaSupervisor.get(i)).getNombres();%>
                                        <%String apePaterno = ((TrabajadorBean)listaSupervisor.get(i)).getApe_paterno();%>
                                        <%String apeMaterno = ((TrabajadorBean)listaSupervisor.get(i)).getApe_materno();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("fecFirSupervisor")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirSupervisor')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;T&Eacute;CNICO CATASTRAL</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES Y APELLIDOS</td>
                            <td width="320">
                                <%String idtecnico = StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("idTecnico")).trim();%>
                                <select class="clsComboLargo" name="idTecnico"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTecnico.size();i++){%>
                                        <%String idTecnico = ((TrabajadorBean)listaTecnico.get(i)).getId_trabajador();%>
                                        <%String nombres = ((TrabajadorBean)listaTecnico.get(i)).getNombres();%>
                                        <%String apePaterno = ((TrabajadorBean)listaTecnico.get(i)).getApe_paterno();%>
                                        <%String apeMaterno = ((TrabajadorBean)listaTecnico.get(i)).getApe_materno();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("fecFirTecCatastral")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirTecCatastral')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                <%String idverificador = StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("idVerificador")).trim();%>
                                <select class="clsComboLargo" name="idVerificador"
                                <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaVerificador.size();i++){%>
                                        <%String idVerificador = ((TrabajadorBean)listaVerificador.get(i)).getId_trabajador();%>
                                        <%String nombres = ((TrabajadorBean)listaVerificador.get(i)).getNombres();%>
                                        <%String apePaterno = ((TrabajadorBean)listaVerificador.get(i)).getApe_paterno();%>
                                        <%String apeMaterno = ((TrabajadorBean)listaVerificador.get(i)).getApe_materno();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("fecFirVerCatastral")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirVerCatastral')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;Nº DE REGISTRO</td>
                            <td colspan="3">
                                <input type="text" class="casilla" name="numRegVerCatastral" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaIndividual.get("numRegVerCatastral")).trim()%>" size="10" maxlength="15"
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
    <%if(!codEstado.equals(Properties.EstadoRevisado) && !mapFichaIndividual.containsKey("flagOK")){%>
        <%if(mapFichaIndividual.containsKey("flagActualizar") && mapFichaIndividual.get("flagActualizar")!=null){%>
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