<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.UbigeoBean" %>
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
    HashMap mapFichaActividad = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"): new HashMap();
    HashMap mapConductor = mapFichaActividad.get("mapConductor")!=null?(HashMap)mapFichaActividad.get("mapConductor"): new HashMap();
    HashMap mapFuncionamiento = mapFichaActividad.get("mapFuncionamiento")!=null?(HashMap)mapFichaActividad.get("mapFuncionamiento"): new HashMap();
    HashMap mapInformacion = mapFichaActividad.get("mapInformacion")!=null?(HashMap)mapFichaActividad.get("mapInformacion"): new HashMap();
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipTitular =  mapListas.get("listaTipTitular")!=null?(List) mapListas.get("listaTipTitular"):new ArrayList();
    List listaTipDocIdentidad =  mapListas.get("listaTipDocIdentidad")!=null?(List) mapListas.get("listaTipDocIdentidad"):new ArrayList();
    List listaDepartamento =  mapListas.get("listaDepartamento")!=null?(List) mapListas.get("listaDepartamento"):new ArrayList();
    List listaProvincia =  mapListas.get("listaProvincia")!=null?(List) mapListas.get("listaProvincia"):new ArrayList();
    List listaDistrito =  mapListas.get("listaDistrito")!=null?(List) mapListas.get("listaDistrito"):new ArrayList();
    List listaTipVia =  mapListas.get("listaTipVia")!=null?(List) mapListas.get("listaTipVia"):new ArrayList();
    List listaConDeclarante =  mapListas.get("listaConDeclarante")!=null?(List) mapListas.get("listaConDeclarante"):new ArrayList();
    List listaEstLleFicha =  mapListas.get("listaEstLleFicha")!=null?(List) mapListas.get("listaEstLleFicha"):new ArrayList();
    List listaMantenimiento =  mapListas.get("listaMantenimiento")!=null?(List) mapListas.get("listaMantenimiento"):new ArrayList();
    List listaDocPresentados =  mapListas.get("listaDocPresentados")!=null?(List) mapListas.get("listaDocPresentados"):new ArrayList();
    List listaConConductor =  mapListas.get("listaConConductor")!=null?(List) mapListas.get("listaConConductor"):new ArrayList();
    List listaVerificador = mapListas.get("listaVerificador")!=null?(List)mapListas.get("listaVerificador"): new ArrayList();
    List listaSupervisor = mapListas.get("listaSupervisor")!=null?(List)mapListas.get("listaSupervisor"): new ArrayList();
    List listaTecnico = mapListas.get("listaTecnico")!=null?(List)mapListas.get("listaTecnico"): new ArrayList();
    List listaAnuncio = mapFichaActividad.get("listaAnuncio")!=null?(List)mapFichaActividad.get("listaAnuncio"): new ArrayList();
    List listaActividad = mapFichaActividad.get("listaActividad")!=null?(List)mapFichaActividad.get("listaActividad"): new ArrayList();
    String mensaje = request.getAttribute("mensaje")!=null?(String)request.getAttribute("mensaje"):"";
    String codEstado = mapFichaActividad.get("codEstado")!=null?(String)mapFichaActividad.get("codEstado"):"";
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
    if(f.fecExpAutorizacion.value!='' && f.fecVenAutorizacion.value!=''){
        if(!compararFechaMayor(f.fecExpAutorizacion.value,f.fecVenAutorizacion.value)){
            alert('La Fecha de Vencimiento de Autorización debe ser posterior a la Fecha de Inicio de Autorización');
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
function agregarAnuncio(){
    f = document.forms[0];
    f.method.value = "irAgregarAnuncio";
    f.submit();
}
function eliminarAnuncio(ind){
    f = document.forms[0];
    f.method.value = "eliminarAnuncio";
    f.indListaAnuncio.value = ind;
    f.submit();
}
function editarAnuncio(ind){
    f = document.forms[0];
    f.method.value = "irEditarAnuncio";
    f.indListaAnuncio.value = ind;
    f.submit();
}
function agregarActividad(){
    f = document.forms[0];
    f.method.value = "irAgregarActividad";
    f.submit();
}
function eliminarActividad(ind){
    f = document.forms[0];
    f.method.value = "eliminarActividad";
    f.indListaActividad.value = ind;
    f.submit();
}
function editarActividad(ind){
    f = document.forms[0];
    f.method.value = "irEditarActividad";
    f.indListaActividad.value = ind;
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
<html:form action="fichaActividadEconomica-action" method="post" styleId="fichaActividadEconomica" >
<table width="980" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1" colspan="6">FICHA CATASTRAL URBANA ACTIVIDAD ECON&Oacute;MICA</td>
    </tr>
    <tr>
        <td colspan="6">&nbsp;</td>
    </tr>
    <tr>
        <td width="150" height="24">&nbsp;</td>
        <td width="100">N&Uacute;MERO DE FICHA</td>
        <td>
            <input type="text" class="casilla" name="numFicha" onkeypress="return valida(this,'int').trim();"
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("numFicha"))%>" size="7" maxlength="7" <%=disabled%>/>
        </td>
        <td width="160">N&Uacute;MERO DE FICHAS POR LOTE</td>
        <td>
            <input type="text" class="casilla" name="numFichLote" onkeypress="return valida(this,'int').trim();"
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("numFichLote"))%>" size="1" maxlength="2" <%=disabled%>/>
            <input type="text" class="casilla" name="numTotFichLote" onkeypress="return valida(this,'int').trim();"
            value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("numTotFichLote"))%>" size="1" maxlength="2" <%=disabled%>/>
        </td>
        <td width="150">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="6">
        <%@include file="../cabecera.jsp" %>
        <br>
        <input type="hidden" name="method">
        <input type="hidden" name="indListaAnuncio">
        <input type="hidden" name="indListaActividad">
        <table width="950" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="etiquetanegra" colspan="4" height="30">
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>IDENTIFICACI&Oacute;N DEL CONDUCTOR:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DE CONDUCTOR</td>
                            <td width="320">
                                <%String codtiptitular = StringUtil.nullAsEmptyString((String)mapConductor.get("codTipTitular")).trim();%>
                                <select class="clsComboMediano" name="codTipTitular"  onchange="changeTipoTitular();" <%=disabled%>>
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
                            <td width="155" class="etiqueta" height="24">NOMBRE COMERCIAL</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomComercial" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("nomComercial")).trim()%>" maxlength="50" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr id="personanatural" >
                            <td colspan="4">
                                <table width="100%" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DOC. IDENTIDAD</td>
                                        <td width="320">
                                            <%String codtipdocidentidad = StringUtil.nullAsEmptyString((String)mapConductor.get("codTipDocIdentidad")).trim();%>
                                            <select class="clsComboLargo" name="codTipDocIdentidad" <%=disabled%> onchange="changeMaxLength();">
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
                                            <input type="text" class="casilla" name="numDocIdentidad" onkeypress="return valida(this,'int');"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("numDocIdentidad")).trim()%>" size="9" maxlength="11" <%=disabled%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES</td>
                                        <td colspan="3">
                                            <input type="text" class="casillaLarga" name="nombre" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("nombre")).trim()%>" maxlength="50" <%=disabled%>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                                        <td>
                                            <input type="text" class="casilla" name="apePaterno" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("apePaterno")).trim()%>" size="32" maxlength="30" <%=disabled%>/>
                                        </td>
                                        <td class="etiqueta" height="24">APELLIDO MATERNO</td>
                                        <td>
                                            <input type="text" class="casilla" name="apeMaterno" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("apeMaterno")).trim()%>" size="32" maxlength="30" <%=disabled%>/>
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
                                            value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("numRuc")).trim()%>" size="9" maxlength="11" <%=disabled%>/>
                                        </td>
                                        <td width="155" class="etiqueta" height="24">RAZON SOCIAL</td>
                                        <td>
                                            <input type="text" class="casillaLarga" name="razSocial" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                            value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("razSocial")).trim()%>" maxlength="50" <%=disabled%>/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CONDICI&Oacute;N DEL CONDUCTOR</td>
                            <td colspan="3">
                                <%String codconconductor = StringUtil.nullAsEmptyString((String)mapConductor.get("codConConductor")).trim();%>
                                <select class="clsComboMediano" name="codConConductor" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaConConductor.size();i++){%>
                                        <%String codConConductor = ((TablasCodigosBean)listaConConductor.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaConConductor.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaConConductor.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(codconconductor.equals(codConConductor)){%> selected <%}%> value="<%=codConConductor%>">
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DOMICILIO FISCAL DEL CONDUCTOR DE LA ACTIVIDAD:
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DEPARTAMENTO</td>
                            <td colspan="3">
                                <%String coddepartamento = StringUtil.nullAsEmptyString((String)mapConductor.get("codDepartamento")).trim();%>
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
                                <%String codprovincia = StringUtil.nullAsEmptyString((String)mapConductor.get("codProvincia")).trim();%>
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
                                <%String coddistrito = StringUtil.nullAsEmptyString((String)mapConductor.get("codDistrito")).trim();%>
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
                                <input type="text" class="casilla" name="telefonoTit" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("telefono")).trim()%>" size="10" maxlength="12" <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">ANEXO</td>
                            <td>
                                <input type="text" class="casilla" name="anexoTit" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("anexo")).trim()%>" size="1" maxlength="4" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FAX</td>
                            <td>
                                <input type="text" class="casilla" name="faxTit" onkeypress="return valida(this,'int');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("fax")).trim()%>" size="10" maxlength="12" <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">CORREO ELECTR&Oacute;NICO</td>
                            <td>
                                <input type="text" class="casillaLarga" name="correoTit" id="correoTit" onblur="validaEmail(this.value,'correoTit');" onkeypress="return valida(this,'email');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("corElectronico")).trim()%>" maxlength="50" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE V&Iacute;A</td>
                            <td>
                                <input type="text" class="casilla" name="codViaTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("codVia")).trim()%>" size="6" maxlength="6" <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">TIPO DE V&Iacute;A</td>
                            <td>
                                <%String codtipvia = StringUtil.nullAsEmptyString((String)mapConductor.get("codTipVia")).trim();%>
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
                                <input type="text" class="casillaLarga" name="nomViaTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("nomVia")).trim()%>" maxlength="50" <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">N&Uacute;MERO MUNICIPAL</td>
                            <td>
                                <input type="text" class="casilla" name="numMunicipalTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("numMunicipal")).trim()%>" size="5" maxlength="5" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE DE EDIFICACI&Oacute;N</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomEdificacionTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("nomEdificacion")).trim()%>" maxlength="50" <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">N&Uacute;MERO INTERIOR</td>
                            <td>
                                <input type="text" class="casillaNro" name="numInteriorTit" onkeypress="return valida(this,'int');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("numInterior")).trim()%>" maxlength="6" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO HU</td>
                            <td>
                                <input type="text" class="casillaNro" name="codHUTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("codHabUrbana")).trim()%>" maxlength="4" <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">NOMBRE DE LA HU</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomHUTit" onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("nomHabUrbana")).trim()%>" maxlength="50" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ZONA/SECTOR/ETAPA</td>
                            <td>
                                <input type="text" class="casillaNro" name="sectorTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("sector")).trim()%>" size="20" maxlength="20" <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">MANZANA</td>
                            <td>
                                <input type="text" class="casillaNro" name="manzanaTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("manzana")).trim()%>" maxlength="3" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;LOTE</td>
                            <td>
                                <input type="text" class="casillaNro" name="loteTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("lote")).trim()%>" maxlength="5" <%=disabled%>/>
                            </td>
                            <td class="etiqueta" height="24">SUB-LOTE</td>
                            <td>
                                <input type="text" class="casillaNro" name="subloteTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapConductor.get("sublote")).trim()%>" maxlength="5" <%=disabled%>/>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>AUTORIZACI&Oacute;N MUNICIPAL DE FUNCIONAMIENTO:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaActividad!=null && !listaActividad.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th width="5%">EDITAR</th>
                                        <th width="5%">ELIMINAR</th>
                                        <th width="20%">C&Oacute;DIGO DE LA ACTIVIDAD</th>
                                        <th width="70%">DESCRIPCI&Oacute;N DE LA ACTIVIDAD</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaActividad.size();i++){%>
                                            <%HashMap mapActividad = (HashMap)listaActividad.get(i);%>
                                            <%String codActividad = (String)mapActividad.get("codActividad");%>
                                            <%String desActividad = (String)mapActividad.get("desActividad");%>
                                            <tr class="normal">
                                                <td align="center"><a href='JavaScript:editarActividad(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                <td align="center"><a href='JavaScript:eliminarActividad(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(codActividad)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(desActividad)%></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                <%}else{%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th width="5%">EDITAR</th>
                                        <th width="5%">ELIMINAR</th>
                                        <th width="20%">C&Oacute;DIGO DE LA ACTIVIDAD</th>
                                        <th width="70%">DESCRIPCI&Oacute;N DE LA ACTIVIDAD</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="normal">
                                            <td align="center" colspan="4">No se encontraron registros.</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <%}%>
                            </td>
                        </tr>
                        <%if("disabled".equals(disabled)){%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar Actividad</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarActividad();">Agregar Actividad</a></td>
                            </tr>
                        <%}%>
                        <tr>
                            <td class="etiqueta" colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;N&Uacute;MERO DE EXPEDIENTE</td>
                            <td width="320">
                                <input type="text" class="casillaFecha" name="numExpediente" onkeypress="return valida(this,'afn');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("numExpediente")).trim()%>" maxlength="10" <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">N&Uacute;MERO DE LICENCIA</td>
                            <td>
                                <input type="text" class="casillaFecha" name="numLicencia" onkeypress="return valida(this,'afn');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("numLicencia")).trim()%>" maxlength="10" <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;&Aacute;REA DE LA ACTIVIDAD ECON&Oacute;MICA</td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                               <table class="clsTablaDatosMediano" align="center">
                                    <thead>
                                        <tr class="principal">
                                            <th>UBICACI&Oacute;N</th>
                                            <th>PREDIO CATASTRAL</th>
                                            <th>V&Iacute;A P&Uacute;BLICA</th>
                                            <th>BIEN COM&Uacute;N</th>
                                            <!--
                                            <th>TOTAL</th>
                                            -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;&Aacute;REA AUTORIZADA</b></td>
                                            <td align="center">
                                                <input type="text" class="casillaInvisible" name="areAutPreCatastral" onkeypress="return valida(this,'dec');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("areAutPreCatastral")).trim()%>" maxlength="10" <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaInvisible" name="areAutViaPublica" onkeypress="return valida(this,'dec');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("areAutViaPublica")).trim()%>" maxlength="10" <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaInvisible" name="areAutBienComun" onkeypress="return valida(this,'dec');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("areAutBienComun")).trim()%>" maxlength="10" <%=disabled%>/>
                                            </td>
                                            <%--
                                            <td align="center">
                                                <input type="text" class="casillaInvisible" name="areAutTotal" onkeypress="return valida(this,'dec');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("areAutTotal"))%>" maxlength="10" <%=disabled%>/>
                                            </td>
                                            --%>
                                        </tr>
                                        <tr class="normal">
                                            <td class="principal"><b>&nbsp;&nbsp;&nbsp;&Aacute;REA VERIFICADA</b></td>
                                            <td align="center">
                                                <input type="text" class="casillaInvisible" name="areVerPreCatastral" onkeypress="return valida(this,'dec');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("areVerPreCatastral")).trim()%>" maxlength="10" <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaInvisible" name="areVerViaPublica" onkeypress="return valida(this,'dec');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("areVerViaPublica")).trim()%>" maxlength="10" <%=disabled%>/>
                                            </td>
                                            <td align="center">
                                                <input type="text" class="casillaInvisible" name="areVerBienComun" onkeypress="return valida(this,'dec');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("areVerBienComun")).trim()%>" maxlength="10" <%=disabled%>/>
                                            </td>
                                            <%--
                                            <td align="center">
                                                <input type="text" class="casillaInvisible" name="areVerTotal" onkeypress="return valida(this,'dec');"
                                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("areVerTotal"))%>" maxlength="10" <%=disabled%>/>
                                            </td>
                                            --%>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="etiquetanegra" height="24">&nbsp;&nbsp;&nbsp;VIGENCIA DE AUTORIZACI&Oacute;N</td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA DE EXPEDICI&Oacute;N</td>
                            <td width="320">
                                <input type="text" class="casillaFecha" name="fecExpAutorizacion" onblur="validaFecha(this.value,'fecExpAutorizacion');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("fecExpAutorizacion")).trim()%>" maxlength="10" <%=disabled%>/>
                                &nbsp;<a href="JavaScript:showCal('fecExpAutorizacion')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                            </td>
                            <td width="155" class="etiqueta" height="24">FECHA DE VENCIMIENTO</td>
                            <td>
                                <input type="text" class="casillaFecha" name="fecVenAutorizacion" onblur="validaFecha(this.value,'fecVenAutorizacion');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("fecVenAutorizacion")).trim()%>" maxlength="10" <%=disabled%>/>
                                &nbsp;<a href="JavaScript:showCal('fecVenAutorizacion')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;INICIO DE ACTIVIDAD</td>
                            <td colspan="3">
                                <input type="text" class="casillaFecha" name="fecIniActividad" onblur="validaFecha(this.value,'fecIniActividad');" onkeypress="return valida(this,'fec');" 
                                value="<%=StringUtil.nullAsEmptyString((String)mapFuncionamiento.get("fecIniActividad")).trim()%>" maxlength="10" <%=disabled%>/>
                                &nbsp;<a href="JavaScript:showCal('fecIniActividad')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>AUTORIZACI&Oacute;N DE ANUNCIO:
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" valign="top">
                                <%if(listaAnuncio!=null && !listaAnuncio.isEmpty()){%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th rowspan="2" scope="col" width="5%">EDITAR</th>
                                        <th rowspan="2" scope="col" width="5%">ELIMINAR</th>
                                        <th rowspan="2" scope="col" width="10%">C&Oacute;DIGO TIPO DE ANUNCIO</th>
                                        <th rowspan="2" scope="col" width="25%">DESCRIPCI&Oacute;N TIPO DE ANUNCIO</th>
                                        <th rowspan="2" scope="col" width="5%">Nº DE LADOS</th>
                                        <th rowspan="2" scope="col" width="5%">AREA AUTORIZADA DEL ANUNCIO (m2)</th>
                                        <th rowspan="2" scope="col" width="5%">AREA VERIFICADA DEL ANUNCIO (m2)</th>
                                        <th rowspan="2" scope="col" width="10%">Nº EXPEDIENTE</th>
                                        <th rowspan="2" scope="col" width="10%">Nº LICENCIA</th>
                                        <th colspan="2" scope="col">VIGENCIA DE AUTORIZACI&Oacute;N</th>
                                    </tr>
                                    <tr class="principal">
                                        <th rowspan="2" class="secundario" width="10%">FECHA DE EXPEDICI&Oacute;N</th>
                                        <th rowspan="2" class="secundario" width="10%">FECHA DE VENCIMIENTO</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(int i=0;i<listaAnuncio.size();i++){%>
                                            <%HashMap mapAnuncio = (HashMap)listaAnuncio.get(i);%>
                                            <%String codTipAnuncio = (String)mapAnuncio.get("codTipAnuncio");%>
                                            <%String desTipAnuncio = (String)mapAnuncio.get("desTipAnuncio");%>
                                            <%String numLados = (String)mapAnuncio.get("numLados");%>
                                            <%String areAutorizada = (String)mapAnuncio.get("areAutorizada");%>
                                            <%String areVerificada = (String)mapAnuncio.get("areVerificada");%>
                                            <%String numExpAnuncio = (String)mapAnuncio.get("numExpAnuncio");%>
                                            <%String numLicAnuncio = (String)mapAnuncio.get("numLicAnuncio");%>
                                            <%String fecExpedicion = (String)mapAnuncio.get("fecExpedicion");%>
                                            <%String fecVencimiento = (String)mapAnuncio.get("fecVencimiento");%>
                                            <tr class="normal">
                                                <td align="center"><a href='JavaScript:editarAnuncio(<%=i%>)'><img src="imagenes/editar.gif" alt="editar" border="0" align="center"/></a></td>
                                                <td align="center"><a href='JavaScript:eliminarAnuncio(<%=i%>)'><img src="imagenes/eliminar.gif" alt="eliminar" border="0" align="center"/></a></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(codTipAnuncio)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(desTipAnuncio)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(numLados)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(areAutorizada)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(areVerificada)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(numExpAnuncio)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(numLicAnuncio)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(fecExpedicion)%></td>
                                                <td align="center"><%=StringUtil.nullAsEmptyString(fecVencimiento)%></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                <%}else{%>
                                <table class="clsTablaDatos" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr class="principal">
                                        <th rowspan="2" scope="col" width="5%">EDITAR</th>
                                        <th rowspan="2" scope="col" width="5%">ELIMINAR</th>
                                        <th rowspan="2" scope="col" width="10%">C&Oacute;DIGO TIPO DE ANUNCIO</th>
                                        <th rowspan="2" scope="col" width="25%">DESCRIPCI&Oacute;N TIPO DE ANUNCIO</th>
                                        <th rowspan="2" scope="col" width="5%">Nº DE LADOS</th>
                                        <th rowspan="2" scope="col" width="5%">AREA AUTORIZADA DEL ANUNCIO (m2)</th>
                                        <th rowspan="2" scope="col" width="5%">AREA VERIFICADA DEL ANUNCIO (m2)</th>
                                        <th rowspan="2" scope="col" width="10%">Nº EXPEDIENTE</th>
                                        <th rowspan="2" scope="col" width="10%">Nº LICENCIA</th>
                                        <th colspan="2" scope="col">VIGENCIA DE AUTORIZACI&Oacute;N</th>
                                    </tr>
                                    <tr class="principal">
                                        <th rowspan="2" class="secundario" width="10%">FECHA DE EXPEDICI&Oacute;N</th>
                                        <th rowspan="2" class="secundario" width="10%">FECHA DE VENCIMIENTO</th>
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
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;Agregar Autorizaci&oacute;n de Anuncio</td>
                            </tr>
                        <%}else{%>
                            <tr>
                                <td class="etiqueta" colspan="4">&nbsp;&nbsp;&nbsp;<a href="JavaScript:agregarAnuncio();">Agregar Autorizaci&oacute;n de Anuncio</a></td>
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
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DOC. PRESENTADOS</td>
                            <td width="320">
                                <%String coddocpresentado = StringUtil.nullAsEmptyString((String)mapInformacion.get("codDocPresentado")).trim();%>
                                <select class="clsComboMediano" name="codDocPresentado" <%=disabled%>>
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaDocPresentados.size();i++){%>
                                        <%String codDocPresentado = ((TablasCodigosBean)listaDocPresentados.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaDocPresentados.get(i)).getCodigo().trim()+" - "+((TablasCodigosBean)listaDocPresentados.get(i)).getDesc_codigo().trim();%>
                                        <option <%if(coddocpresentado.equals(codDocPresentado)){%> selected <%}%> value="<%=codDocPresentado%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                            <td width="155" class="etiqueta" height="24">MANTENIMIENTO</td>
                            <td>
                                <%String codmantenimiento = StringUtil.nullAsEmptyString((String)mapInformacion.get("codMantenimiento")).trim();%>
                                <select class="clsComboLargo" name="codMantenimiento" <%=disabled%>>
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
                                <textarea name="observacion" rows="4" cols="20"  onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" class="casillaExtraLarga" <%=disabled%>><%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("observacion")).trim()%></textarea>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("dniDeclarante")).trim()%>" size="10" maxlength="8"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">NOMBRES</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nomDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("nomDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                            <td width="320">
                                <input type="text" class="casillaLarga" name="apePatDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("apePatDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                            <td width="155" class="etiqueta" height="24">APELIDO MATERNO</td>
                            <td>
                                <input type="text" class="casillaLarga" name="apeMatDeclarante" onkeypress="return valida(this,'str');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("apeMatDeclarante")).trim()%>" maxlength="50"
                                <%=disabled%>/>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FECHA</td>
                            <td colspan="3">
                                <input type="text" class="casillaFecha" name="fecFirDeclarante" id="fecFirDeclarante" onblur="validaFecha(this.value,'fecFirDeclarante');" onkeypress="return valida(this,'fec');"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("fecFirDeclarante")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirDecActividadEconomica')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                <%String idsupervisor = StringUtil.nullAsEmptyString((String)mapFichaActividad.get("idSupervisor")).trim();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("fecFirSupervisor")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirSupActividadEconomica')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                <%String idtecnico = StringUtil.nullAsEmptyString((String)mapFichaActividad.get("idTecnico")).trim();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("fecFirTecCatastral")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirTecCatActividadEconomica')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
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
                                <%String idverificador = StringUtil.nullAsEmptyString((String)mapFichaActividad.get("idVerificador")).trim();%>
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
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("fecFirVerCatastral")).trim()%>"
                                <%=disabled%>/>
                                <%if("disabled".equals(disabled)){%>
                                    &nbsp;<img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/>
                                <%}else{%>
                                    &nbsp;<a href="JavaScript:showCal('fecFirVerCatActividadEconomica')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td width="155" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;Nº DE REGISTRO</td>
                            <td colspan="3">
                                <input type="text" class="casilla" name="numRegVerCatastral" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();"
                                value="<%=StringUtil.nullAsEmptyString((String)mapFichaActividad.get("numRegVerCatastral")).trim()%>" size="10" maxlength="15"
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
    <%if(!codEstado.equals(Properties.EstadoRevisado) && !mapFichaActividad.containsKey("flagOK")){%>
        <%if(mapFichaActividad.containsKey("flagActualizar") && mapFichaActividad.get("flagActualizar")!=null){%>
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