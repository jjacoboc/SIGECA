<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.maestro.bean.UbigeoBean" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap mapCotitular = request.getAttribute("mapCotitular")!=null?(HashMap)request.getAttribute("mapCotitular"):new HashMap();
    String ind = request.getAttribute("indListaCotitulares")!=null?(String)request.getAttribute("indListaCotitulares"):"";
    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"): new HashMap();
    List listaTipTitular =  mapListas.get("listaTipTitular")!=null?(List) mapListas.get("listaTipTitular"):new ArrayList();
    List listaTipDocIdentidad =  mapListas.get("listaTipDocIdentidad")!=null?(List) mapListas.get("listaTipDocIdentidad"):new ArrayList();
    List listaConEspTitular =  mapListas.get("listaConEspTitular")!=null?(List) mapListas.get("listaConEspTitular"):new ArrayList();
    List listaForAdquisicion =  mapListas.get("listaForAdquisicion")!=null?(List) mapListas.get("listaForAdquisicion"):new ArrayList();
    List listaDepartamento =  mapListas.get("listaDepartamento")!=null?(List) mapListas.get("listaDepartamento"):new ArrayList();
    List listaProvincia =  mapListas.get("listaProvincia")!=null?(List) mapListas.get("listaProvincia"):new ArrayList();
    List listaDistrito =  mapListas.get("listaDistrito")!=null?(List) mapListas.get("listaDistrito"):new ArrayList();
    List listaTipVia =  mapListas.get("listaTipVia")!=null?(List) mapListas.get("listaTipVia"):new ArrayList();
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css"/>
<title>BMP Geom&aacute;tica S.A.</title>
</head>
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
    if(f.numRuc.value!=''){
        var valor = f.numRuc.value;
        if(valor.substr(0,1)!='1' && valor.substr(0,1)!='2'){
            alert('Formato de RUC incorrecto');
            return false;
        }
    }
    return true;
}
function agregarCotitular(){
    f = document.forms[0];
    if(!validar()){return;}
    f.desTipTitular.value = f.codTipTitular.options[f.codTipTitular.selectedIndex].text;
    f.desTipDocIdentidad.value = f.codTipDocIdentidad.options[f.codTipDocIdentidad.selectedIndex].text;
    f.method.value = "agregarCotitular";
    f.submit();
}
function editarCotitular(){
    f = document.forms[0];
    if(!validar()){return;}
    f.desTipTitular.value = f.codTipTitular.options[f.codTipTitular.selectedIndex].text;
    f.desTipDocIdentidad.value = f.codTipDocIdentidad.options[f.codTipDocIdentidad.selectedIndex].text;
    f.method.value = "editarCotitular";
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
function cancelar(){
    f = document.forms[0];
    f.method.value = "irFichaCotitularidad";
    f.submit();
}
</script>
<body onload="JavaScript:inicio();">
<br>
<html:form action="fichaCotitularidad-action" method="post">
<table width="980" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td class="Titulo1">IDENTIFICACI&Oacute;N DEL COTITULAR CATASTRAL</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <input type="hidden" name="method">
            <input type="hidden" name="desTipTitular">
            <input type="hidden" name="desTipDocIdentidad">
            <input type="hidden" name="indListaCotitulares" value="<%=ind%>">
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
                                    <input type="text" class="casilla" name="numCotitular" onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numCotitular")).trim()%>" size="1" maxlength="2"/>
                              </td>
                                <td width="155" class="etiqueta" height="24">TOTAL DE COTITULARES</td>
                                <td width="317">
                                    <input type="text" class="casilla" name="numTotCotitular" onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numTotCotitular")).trim()%>" size="1" maxlength="2"/>
                              </td>
                            </tr>
                            <tr>
                                <td width="156" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;PORCENTAJE DE COTITULAR</td>
                                <td width="320">
                                    <input type="text" class="casilla" name="porCotitular" onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("porCotitular")).trim()%>" size="1" maxlength="3"/>
                              </td>
                                <td width="155" class="etiqueta" height="24">C&Oacute;DIGO DEL CONTRIBUYENTE</td>
                                <td>
                                    <input type="text" class="casilla" name="codContribuyente" onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("codContribuyente")).trim()%>" size="9" maxlength="10"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="156" class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO DE TITULAR</td>
                                <td colspan="3">
                                    <%String codtiptitular = StringUtil.nullAsEmptyString((String)mapCotitular.get("codTipTitular")).trim();;%>
                                    <select class="clsComboDoc" name="codTipTitular" onchange="changeTipoTitular();" >
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
                                    <select class="clsComboLargo" name="codForAdqTitular" >
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
                                    <input type="text" class="casillaFecha" name="fecAdqTitular" id="fecAdqTitular" onblur="validaFecha(this.value,'fecAdqTitular');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("fecAdqTitular")).trim()%>" maxlength="10"/>
                                    &nbsp;<a href="JavaScript:showCal('fecAdqTitular')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;COND. ESP. DEL TITULAR</td>
                                <td>
                                    <%String codconesptitular = StringUtil.nullAsEmptyString((String)mapCotitular.get("codConEspTitular")).trim();%>
                                    <select class="clsComboLargo" name="codConEspTitular" >
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
                                    <input type="text" class="casillaFecha" name="numResExoneracion" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numResExoneracion")).trim()%>" size="9" maxlength="10"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;INICIO EXONERACION</td>
                                <td>
                                    <input type="text" class="casillaFecha" name="fecIniExoneracion" id="fecIniExoneracion" onblur="validaFecha(this.value,'fecIniExoneracion');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("fecIniExoneracion")).trim()%>" maxlength="10"/>
                                    &nbsp;<a href="JavaScript:showCal('fecIniExoCotitular')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                </td>
                                <td class="etiqueta" height="24">FIN EXONERACION</td>
                                <td>
                                    <input type="text" class="casillaFecha" name="fecVenExoneracion" id="fecVenExoneracion" onblur="validaFecha(this.value,'fecVenExoneracion');" onkeypress="return valida(this,'fec');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("fecVenExoneracion")).trim()%>" maxlength="10"/>
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
                                    <select class="clsComboMediano" name="codDepartamento" onchange="JavaScript:cargarProvincia()" >
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
                                    <select class="clsComboMediano" name="codProvincia" onchange="JavaScript:cargarDistrito()" >
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
                                    <select class="clsComboMediano" name="codDistrito" >
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
                                    <input type="text" class="casilla" name="telefonoTit" onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("telefono")).trim()%>" size="10" maxlength="12"/>
                                </td>
                                <td width="155" class="etiqueta" height="24">ANEXO</td>
                                <td>
                                    <input type="text" class="casilla" name="anexoTit" onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("anexo")).trim()%>" size="1" maxlength="4"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;FAX</td>
                                <td>
                                    <input type="text" class="casilla" name="faxTit" onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("fax")).trim()%>" size="10" maxlength="12"/>
                                </td>
                                <td class="etiqueta" height="24">CORREO ELECTR&Oacute;NICO</td>
                                <td>
                                    <input type="text" class="casillaLarga" name="correoTit" id="correoTit" onblur="validaEmail(this.value,'correoTit');" onkeypress="return valida(this,'email');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("corElectronico")).trim()%>" maxlength="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE V&Iacute;A</td>
                                <td>
                                    <input type="text" class="casilla" name="codViaTit" onkeypress="return valida(this,'int');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("codVia")).trim()%>" size="10" maxlength="6"/>
                                </td>
                                <td class="etiqueta" height="24">TIPO DE V&Iacute;A</td>
                                <td>
                                    <%String codtipvia = StringUtil.nullAsEmptyString((String)mapCotitular.get("codTipVia")).trim();%>
                                    <select class="clsComboDoc" name="codTipViaTit" >
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
                                    <input type="text" class="casillaLarga" name="nomViaTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("nomVia")).trim()%>" maxlength="50"/>
                                </td>
                                <td class="etiqueta" height="24">N&Uacute;MERO MUNICIPAL</td>
                                <td>
                                    <input type="text" class="casilla" name="numMunicipalTit" onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numMunicipal")).trim()%>" size="5" maxlength="6"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE DE EDIFICACI&Oacute;N</td>
                                <td>
                                    <input type="text" class="casillaLarga" name="nomEdificacionTit"  onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("nomEdificacion")).trim()%>" maxlength="50"/>
                                </td>
                                <td class="etiqueta" height="24">N&Uacute;MERO INTERIOR</td>
                                <td>
                                    <input type="text" class="casilla" name="numInteriorTit"  onkeypress="return valida(this,'int');" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("numInterior")).trim()%>" size="2" maxlength="5"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO HU</td>
                                <td>
                                    <input type="text" class="casilla" name="codHUTit"  onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("codHabUrbana")).trim()%>" size="1" maxlength="4"/>
                                </td>
                                <td class="etiqueta" height="24">NOMBRE DE LA HU</td>
                                <td>
                                    <input type="text" class="casillaLarga" name="nomHUTit"  onkeypress="return valida(this,'all');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("nomHabUrbana")).trim()%>" maxlength="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ZONA/SECTOR/ETAPA</td>
                                <td>
                                    <input type="text" class="casilla" name="sectorTit"  onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("sector")).trim()%>" size="20" maxlength="20"/>
                                </td>
                                <td class="etiqueta" height="24">MANZANA</td>
                                <td>
                                    <input type="text" class="casilla" name="manzanaTit"  onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("manzana")).trim()%>" size="2" maxlength="5"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;LOTE</td>
                                <td>
                                    <input type="text" class="casilla" name="loteTit"  onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("lote")).trim()%>" size="2" maxlength="5"/>
                                </td>
                                <td class="etiqueta" height="24">SUB-LOTE</td>
                                <td>
                                    <input type="text" class="casilla" name="subloteTit"  onkeypress="return valida(this,'afn');" onkeyup="this.value=this.value.toUpperCase();" value="<%=StringUtil.nullAsEmptyString((String)mapCotitular.get("sublote")).trim()%>" size="2" maxlength="5"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td colspan="4" height="24" align="center">
            <%if(!"".equals(StringUtil.nullAsEmptyString((String)mapCotitular.get("codTipDocumento")).trim())){%>
                <input class="buttoncenter" type="button" value="Actualizar" name="bEditar" onclick="JavaScript:editarCotitular()"/>
            <%}else{%>
                <input class="buttoncenter" type="button" value="Agregar" name="bAceptar" onclick="JavaScript:agregarCotitular()"/>
            <%}%>
            <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar()"/>
        </td>
    </tr>
    <tr>
        <td colspan="4">&nbsp;</td>
    </tr>
</table>
</html:form>
</body>
</html:html>