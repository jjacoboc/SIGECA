<%@ page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.struts.action.*" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.bmp.sigeca.maestro.bean.UbigeoBean" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
    HashMap map = session.getAttribute("mapConfUbigeo")!=null?(HashMap)session.getAttribute("mapConfUbigeo"): new HashMap();
    List listaDepartamento =  map.get("listaDepartamento")!=null?(List) map.get("listaDepartamento"):new ArrayList();
    List listaProvincia =  map.get("listaProvincia")!=null?(List) map.get("listaProvincia"):new ArrayList();
    List listaDistrito =  map.get("listaDistrito")!=null?(List) map.get("listaDistrito"):new ArrayList();
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/keyboard.css" rel="stylesheet" type="text/css">
<title>BMP Geomática S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function aceptar(){
    f = document.forms[0];
    if(f.codDepartamento.value==''){
        alert('Seleccione el departamento de la ubicación geográfica.');
        return;
    }
    if(f.codProvincia.value==''){
        alert('Seleccione la provincia de la ubicación geográfica.');
        return;
    }
    if(f.codDistrito.value==''){
        alert('Seleccione el distrito de la ubicación geográfica.');
        return;
    }
    if(!confirm('Se registrará permanentemente el ubigeo seleccionado. Desea Continuar?')){
        return;
    }
    f.method.value = "setearUbigeo";
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
</script>
<body>
    <p>&nbsp;</p>
    <%@include file="header.jsp" %>
    <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td align="center" valign="middle">
                <html:form action="loguin-action" method="post">
                    <input type="hidden" name="method"/>
                    <table width="55%" align="center" cellPadding="0" cellSpacing="0" class="clsTabla3">
                        <tr>
                            <td colspan="2" bgcolor="#CCCCCC"><b>Ubicaci&oacute;n Geogr&aacute;fica</b></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="88" class="etiqueta"><b>&nbsp;DEPARTAMENTO</b></td>
                            <td width="163" class="etiqueta">
                                <%String coddepartamento = StringUtil.nullAsEmptyString((String)map.get("codDepartamento"));%>
                                <select class="clsComboMediano" name="codDepartamento" onchange="JavaScript:cargarProvincia()">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaDepartamento.size();i++){%>
                                        <%String codDepartamento = ((UbigeoBean)listaDepartamento.get(i)).getCodDepartamento();%>
                                        <%String descripcion = ((UbigeoBean)listaDepartamento.get(i)).getDescripcion();%>
                                        <option <%if(coddepartamento.equals(codDepartamento)){%> selected <%}%> value="<%=codDepartamento%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="etiqueta"><b>&nbsp;PROVINCIA:</b></td>
                            <td class="etiqueta">
                                <%String codprovincia = StringUtil.nullAsEmptyString((String)map.get("codProvincia"));%>
                                <select class="clsComboMediano" name="codProvincia" onchange="JavaScript:cargarDistrito()">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaProvincia.size();i++){%>
                                        <%String codProvincia = ((UbigeoBean)listaProvincia.get(i)).getCodProvincia();%>
                                        <%String descripcion = ((UbigeoBean)listaProvincia.get(i)).getDescripcion();%>
                                        <option <%if(codprovincia.equals(codProvincia)){%> selected <%}%> value="<%=codProvincia%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="etiqueta"><b>&nbsp;DISTRITO:</b></td>
                            <td class="etiqueta">
                                <%String coddistrito = StringUtil.nullAsEmptyString((String)map.get("codDistrito"));%>
                                <select class="clsComboMediano" name="codDistrito">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaDistrito.size();i++){%>
                                        <%String codDistrito = ((UbigeoBean)listaDistrito.get(i)).getCodDistrito();%>
                                        <%String descripcion = ((UbigeoBean)listaDistrito.get(i)).getDescripcion();%>
                                        <option <%if(coddistrito.equals(codDistrito)){%> selected <%}%> value="<%=codDistrito%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input type="button" class="buttoncenter" value="Aceptar" onclick="JavaScript:aceptar()"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                    </table>
                </html:form>
            </td>
        </tr>
        <tr>
            <td height="15" class="etiqueta"></td>
        </tr>
    </table>
    <%@include file="footer.jsp" %>
</body>
</html:html>