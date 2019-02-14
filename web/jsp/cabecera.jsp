<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.HashMap" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"): new HashMap();
    HashMap mapCabecera = mapFicha.get("mapCabecera")!=null?(HashMap)mapFicha.get("mapCabecera"): new HashMap();
    String tipoFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
    String estado = mapFicha.get("codEstado")!=null?(String)mapFicha.get("codEstado"):"";
    String action = session.getAttribute("accion")!=null?(String)session.getAttribute("accion"):"";
    String desactivo = "";
    if(estado.equals(Properties.EstadoRevisado) || action.equals(Properties.Accion_Reportes)){
        desactivo = "disabled";
    }else{
        desactivo = "";
    }
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<title>BMP Geomática S.A.</title>
</head>
<script type="text/javascript" language="JavaScript">
function autoTab(obj1,obj2){
    if(document.getElementById(obj1).maxLength==document.getElementById(obj1).value.length){
        document.getElementById(obj2).focus();
    }
    setCodRefCatastral();
}
function autoTabReverse(obj1,obj2){
    var xkey=event.keyCode;
    if(document.getElementById(obj1).value.length==0 && xkey==8){
        document.getElementById(obj2).focus();
        document.getElementById(obj2).value = document.getElementById(obj2).value
    }
    setCodRefCatastral();
}
function setCodRefCatastral(){
    <%if(tipoFicha.equals(Properties.FichaCatastralRural)){%>
    document.getElementById('codRefCatastral').value = document.getElementById('zona').value+document.getElementById('uniOrgCatRural').value+
                                         document.getElementById('uniCatastral').value;
    <%}else{%>
    document.getElementById('codRefCatastral').value = document.getElementById('dpto').value+document.getElementById('prov').value+
                                         document.getElementById('dist').value+document.getElementById('sector').value+
                                         document.getElementById('manzana').value+document.getElementById('lote').value+
                                         document.getElementById('edifica').value+document.getElementById('entrada').value+
                                         document.getElementById('piso').value+document.getElementById('unidad').value+
                                         document.getElementById('dc').value;
    <%}%>
    
}
function fillZeroLeft(obj){
    var tamañoMaximo = document.getElementById(obj).maxLength;
    var tamaño = document.getElementById(obj).value.length;
    if(tamaño>0 && tamañoMaximo>tamaño){
        var valor = document.getElementById(obj).value;
        while(tamañoMaximo>tamaño){
            valor = "0"+valor;
            tamaño = valor.length;
        }
        document.getElementById(obj).value = valor;
    }
}
</script>
<body>
    <input type="hidden" name="codRefCatastral" id="codRefCatastral" value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codRefCatastral"))%>"/>
    <input type="hidden" name="codRefCatastralRural" id="codRefCatastralRural" value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codRefCatastralRural"))%>"/>
    <br>
    <table width="950" align="center" cellPadding="0" cellSpacing="0" >
        <tr>
            <td align="center"><img src="<%=request.getContextPath()%>/imagenes/logoSBN.jpg" width="175" height="90" alt="Sistema Nacional Integrado de Información Catastral Predial"/></td>
            <td>
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="clsTabla2">
                    <tr>
                        <td class="etiquetanegra" colspan="4" height="30">
                            &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DATOS GENERALES:
                        </td>
                    </tr>
                    <tr>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO UNICO CATASTRAL  - CUC</td>
                        <td>
                            <input type="text" class="casilla" name="cucA" id="cucA" onkeypress="return valida(this,'int');" onkeydown="autoTab('cucA','cucB');" 
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("cucA"))%>" size="5" maxlength="8" <%=desactivo%>/>
                            <input type="text" class="casilla" name="cucB" id="cucB" onkeypress="return valida(this,'int');" onkeydown="autoTabReverse('cucB','cucA');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("cucB"))%>" size="1" maxlength="4" <%=desactivo%>/>
                        </td>
                        <td class="etiqueta">C&Oacute;DIGO HOJA CATASTRAL</td>
                        <td>
                            <input type="text" class="casilla" name="codHojCatastral" onkeypress="return valida(this,'int');" 
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codHojCatastral"))%>" size="8" maxlength="10" <%=desactivo%>/>
                        </td>
                    </tr>
                    <%if(tipoFicha.equals(Properties.FichaCatastralRural)){%>
                    <tr>
                        <td class="etiqueta"></td>
                        <td class="etiqueta" colspan="3">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td align="center" width="35" style="font-size:5pt">DPTO.</td>
                                    <td align="center" width="35" style="font-size:5pt">PROV.</td>
                                    <td align="center" width="35" style="font-size:5pt">DIST.</td>
                                    <td align="center" width="35" style="font-size:5pt">SECTOR</td>
                                    <td align="center" width="105" style="font-size:5pt">C&Oacute;DIGO CONTRIBUYENTE DE RENTAS</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE UBICACI&Oacute;N GEOGR&Aacute;FICA</td>
                        <td class="etiqueta" colspan="3">
                            <input type="text" class="casilla" name="dpto" id="dpto" onkeydown="autoTab('dpto','prov');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codDepartamento"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('dpto');"/>
                            <input type="text" class="casilla" name="prov" id="prov" onkeydown="autoTab('prov','dist');autoTabReverse('prov','dpto');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codProvincia"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('prov');"/>
                            <input type="text" class="casilla" name="dist" id="dist" onkeydown="autoTab('dist','sector');autoTabReverse('dist','prov');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codDistrito"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('dist');"/>
                            <input type="text" class="casilla" name="sector" id="sector" onkeydown="autoTab('sector','codConRenta');autoTabReverse('sector','dist');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("sector"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('sector');"/>
                            <input type="text" class="casilla" name="codConRenta" id="codConRenta" onkeydown="autoTabReverse('codConRenta','sector');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codConRenta"))%>" size="15" maxlength="12" <%=desactivo%>/>
                        </td>
                    </tr>
                    <tr>
                        <td class="etiqueta"></td>
                        <td class="etiqueta" colspan="3">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td align="center" width="35" style="font-size:5pt">ZONA</td>
                                    <td align="center" width="105" style="font-size:5pt">UNIDAD ORG&Aacute;NICA CATASTRAL RURAL</td>
                                    <td align="center" width="105" style="font-size:5pt">UNIDAD CATASTRAL</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE REFERENCIA CATASTRAL</td>
                        <td class="etiqueta" colspan="3">
                            <input type="text" class="casilla" name="zona" id="zona" onkeydown="autoTab('zona','uniOrgCatRural');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("zona"))%>" size="1" maxlength="2" <%=desactivo%>/>
                            <input type="text" class="casilla" name="uniOrgCatRural" id="uniOrgCatRural" onkeydown="autoTab('uniOrgCatRural','uniCatastral');autoTabReverse('uniOrgCatRural','zona');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("uniOrgCatRural"))%>" size="15" maxlength="12" <%=desactivo%>/>
                            <input type="text" class="casilla" name="uniCatastral" id="uniCatastral" onkeydown="autoTabReverse('uniCatastral','uniOrgCatRural');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("uniCatastral"))%>" size="15" maxlength="12" <%=desactivo%>/>
                        </td>
                    </tr>
                    <%}else{%>
                    <tr>
                        <td class="etiqueta"></td>
                        <td class="etiqueta" colspan="3">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td align="center" width="35" style="font-size:5pt">DPTO.</td>
                                    <td align="center" width="35" style="font-size:5pt">PROV.</td>
                                    <td align="center" width="35" style="font-size:5pt">DIST.</td>
                                    <td align="center" width="35" style="font-size:5pt">SECTOR</td>
                                    <td align="center" width="35" style="font-size:5pt">MANZANA</td>
                                    <td align="center" width="35" style="font-size:5pt">LOTE</td>
                                    <td align="center" width="35" style="font-size:5pt">EDIFICA</td>
                                    <td align="center" width="35" style="font-size:5pt">ENTRADA</td>
                                    <td align="center" width="35" style="font-size:5pt">PISO</td>
                                    <td align="center" width="35" style="font-size:5pt">UNIDAD</td>
                                    <td align="center" width="35" style="font-size:5pt">DC</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <%--
                        <%if(tipoFicha.equals(Properties.FichaCatastralBienesCulturales_MonumentoPrehispanico) || tipoFicha.equals(Properties.FichaCatastralBienesCulturales_MonumentoColonial)){%>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE REFERENCIA CATASTRAL / URBANA</td>
                        <%}else{%>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE REFERENCIA CATASTRAL</td>
                        <%}%>
                        --%>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE REFERENCIA CATASTRAL</td>
                        <td class="etiqueta" colspan="3">
                            <input type="text" class="casilla" name="dpto" id="dpto" onkeydown="autoTab('dpto','prov');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codDepartamento"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('dpto');"/>
                            <input type="text" class="casilla" name="prov" id="prov" onkeydown="autoTab('prov','dist');autoTabReverse('prov','dpto');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codProvincia"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('prov');"/>
                            <input type="text" class="casilla" name="dist" id="dist" onkeydown="autoTab('dist','sector');autoTabReverse('dist','prov');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codDistrito"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('dist');"/>
                            <input type="text" class="casilla" name="sector" id="sector" onkeydown="autoTab('sector','manzana');autoTabReverse('sector','dist');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("sector"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('sector');"/>
                            <input type="text" class="casilla" name="manzana" id="manzana" onkeydown="autoTab('manzana','lote');autoTabReverse('manzana','sector');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("manzana"))%>" size="1" maxlength="3" <%=desactivo%> onblur="fillZeroLeft('manzana');"/>
                            <input type="text" class="casilla" name="lote" id="lote" onkeydown="autoTab('lote','edifica');autoTabReverse('lote','manzana');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("lote"))%>" size="1" maxlength="3" <%=desactivo%> onblur="fillZeroLeft('lote');"/>
                            <input type="text" class="casilla" name="edifica" id="edifica" onkeydown="autoTab('edifica','entrada');autoTabReverse('edifica','lote');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("edifica"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('edifica');"/>
                            <input type="text" class="casilla" name="entrada" id="entrada" onkeydown="autoTab('entrada','piso');autoTabReverse('entrada','edifica');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("entrada"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('entrada');"/>
                            <input type="text" class="casilla" name="piso" id="piso" onkeydown="autoTab('piso','unidad');autoTabReverse('piso','entrada');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("piso"))%>" size="1" maxlength="2" <%=desactivo%> onblur="fillZeroLeft('piso');"/>
                            <input type="text" class="casilla" name="unidad" id="unidad" onkeydown="autoTab('unidad','dc');autoTabReverse('unidad','piso');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("unidad"))%>" size="1" maxlength="3" <%=desactivo%> onblur="fillZeroLeft('unidad');"/>
                            <input type="text" class="casilla" name="dc" id="dc" onkeydown="autoTabReverse('dc','unidad');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("dc"))%>" size="1" maxlength="1" <%=desactivo%> onblur="fillZeroLeft('dc');"/>
                        </td>
                    </tr>
                    <%--
                    <%if(tipoFicha.equals(Properties.FichaCatastralBienesCulturales_MonumentoPrehispanico) || tipoFicha.equals(Properties.FichaCatastralBienesCulturales_MonumentoColonial)){%>
                    <tr>
                        <td class="etiqueta"></td>
                        <td class="etiqueta" colspan="3">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td align="center" width="35" style="font-size:5pt">ZONA</td>
                                    <td align="center" width="105" style="font-size:5pt">COORDENADA ESTE</td>
                                    <td align="center" width="105" style="font-size:5pt">COORDENADA NORTE</td>
                                    <td align="center" width="105" style="font-size:5pt">UNIDAD CATASTRAL</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO DE REFERENCIA CATASTRAL / RURAL</td>
                        <td class="etiqueta" colspan="3">
                            <input type="text" class="casilla" name="zona" id="zona" onkeydown="autoTab('zona','coorEste');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("zona"))%>" size="1" maxlength="2" <%=desactivo%>/>
                            <input type="text" class="casilla" name="coorEste" id="coorEste" onkeydown="autoTab('coorEste','coorNorte');autoTabReverse('coorEste','zona');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("uniOrgCatRural"))%>" size="15" maxlength="12" <%=desactivo%>/>
                            <input type="text" class="casilla" name="coorNorte" id="coorNorte" onkeydown="autoTab('coorNorte','uniCatastral');autoTabReverse('coorNorte','coorEste');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("uniOrgCatRural"))%>" size="15" maxlength="12" <%=desactivo%>/>
                            <input type="text" class="casilla" name="uniCatastral" id="uniCatastral" onkeydown="autoTabReverse('uniCatastral','coorNorte');" onkeypress="return valida(this,'int');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("uniCatastral"))%>" size="15" maxlength="12" <%=desactivo%>/>
                        </td>
                    </tr>
                    <%}%>
                    --%>
                    <%}%>
                    <%if(tipoFicha.equals(Properties.FichaCatastralUrbanaIndividual)){%>
                    <tr>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO CONTRIBUYENTE DE RENTAS</td>
                        <td>
                            <input type="text" class="casilla" name="codConRenta" onkeypress="return valida(this,'afn');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codConRenta"))%>" size="8" maxlength="10" <%=desactivo%>/>
                        </td>
                        <td class="etiqueta">CODIGO PREDIAL DE RENTAS</td>
                        <td>
                            <input type="text" class="casilla" name="codPreRenta" onkeypress="return valida(this,'afn');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("codPreRenta"))%>" size="8" maxlength="10" <%=desactivo%>/>
                        </td>
                    </tr>
                    <tr>
                        <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;UNI. ACUM. A C&Oacute;DIGO PREDIAL DE RENTAS</td>
                        <td class="etiqueta" colspan="3">
                            <input type="text" class="casilla" name="uniAcuCodPreRenta" onkeypress="return valida(this,'afn');"
                            value="<%=StringUtil.nullAsEmptyString((String)mapCabecera.get("uniAcuCodPreRenta"))%>" size="8" maxlength="10" <%=desactivo%>/>
                        </td>
                    </tr>
                    <%}%>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</body>
</html:html>