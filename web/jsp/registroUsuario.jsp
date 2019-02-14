<%-- 
    Document   : ingreso
    Created on : 17-oct-2008, 18:03:55
    Author     : admin
--%>

<%@ page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@ page import="org.apache.struts.action.*" %>
<%@ page import="com.bmp.sigeca.maestro.bean.PerfilBean" %>
<%@ page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@ page import="com.bmp.sigeca.resource.Properties" %>
<%@ page import="com.bmp.sigeca.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    List listaTrabajadores = session.getAttribute("listaTrabajadores")!=null?(List)session.getAttribute("listaTrabajadores"):new ArrayList();
    List listaPerfil = session.getAttribute("listaPerfil")!=null?(List)session.getAttribute("listaPerfil"):new ArrayList();
    List listaEstado = session.getAttribute("listaEstado")!=null?(List)session.getAttribute("listaEstado"):new ArrayList();
    UsuarioBean bing = session.getAttribute("registroUsuario")!=null?(UsuarioBean)session.getAttribute("registroUsuario"):new UsuarioBean();
%>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
<title>BMP Geomática S.A.</title>
</head>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/cal.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/cal_conf.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script type="text/javascript" language="JavaScript">
function aceptar(){
    f = document.forms[0];
    f.method.value = "registrarUsuario";
    f.submit();
}
function cancelar(){
    f = document.forms[0];
    f.method.value = "irBusqueda";
    f.submit();
}
</script>
<body>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <table width="600" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
        <tr>
            <td class="Titulo1">REGISTRO DE USUARIO</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td align="center">
                <html:form action="registroUsuario-action" method="post" styleId="registroUsuario">
                    <input type="hidden" name="method" value=""/>
                    <div align="center"><font color="red"><ol><html:errors property="ingreso"/></ol></font></div>
                    <table width="570" align="center" cellpadding="0" cellspacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;TRABAJADOR:</td>
                            <td>
                                <%String idtrabajador = StringUtil.nullAsEmptyString(bing.getCodPefil());%>
                                <select name="id_trabajador" class="clsComboLargo">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTrabajadores.size();i++){%>
                                        <%String id_trabajador = ((String)((HashMap)listaTrabajadores.get(i)).get("id_trabajador")).trim();%>
                                        <%String nombres = ((String)((HashMap)listaTrabajadores.get(i)).get("nombres")).trim();%>
                                        <%String ape_paterno = ((String)((HashMap)listaTrabajadores.get(i)).get("ape_paterno")).trim();%>
                                        <%String ape_materno = ((String)((HashMap)listaTrabajadores.get(i)).get("ape_materno")).trim();%>
                                        <option <%if(id_trabajador.equals(idtrabajador)){%> selected <%}%> value="<%=id_trabajador%>">
                                            <%=nombres+" "+ape_paterno+" "+ape_materno%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;PERFIL:</td>
                            <td>
                                <%String codperfil = StringUtil.nullAsEmptyString(bing.getCodPefil());%>
                                <select name="id_perfil" class="clsComboDoc">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaPerfil.size();i++){%>
                                        <%String codPerfil = ((PerfilBean)listaPerfil.get(i)).getCodPerfil();%>
                                        <%String descripcion = ((PerfilBean)listaPerfil.get(i)).getDescripcion();%>
                                        <option <%if(codPerfil.equals(codperfil)){%> selected <%}%> value="<%=codPerfil%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;FECHA DE CESE:</td>
                            <td>
                                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td width="70">
                                            <input type="text" class="casillaFecha" name="fecha_cese" id="fecha_cese" onblur="validaFecha(this.value,'fecha_cese');" onkeypress="return valida(this,'fec');" value="" maxlength="10"/>
                                        </td>
                                        <td valign="middle" align="left">
                                            <a href="JavaScript:showCal('fecha_cese')"><img src="<%=request.getContextPath()%>/imagenes/calendar.png" alt="calendario" align="bottom" border="0"/></a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;ESTADO</td>
                            <td>
                                <select class="clsComboDoc" name="id_est_usu_tra">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaEstado.size();i++){%>
                                        <%String id_est_usu_tra = ((String)((HashMap)listaEstado.get(i)).get("id_est_usu_tra")).trim();%>
                                        <%String nombre_estado = ((String)((HashMap)listaEstado.get(i)).get("nombre_estado")).trim();%>
                                        <option value="<%=id_est_usu_tra%>">
                                            <%=nombre_estado%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;</td>
                            <td align="left"><img src="Captcha.jpg"></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="etiqueta" height="24">&nbsp;&nbsp;Ingrese el c&oacute;digo que se visualiza en la imagen</td>
                            <td align="left"><input type="text" name="captcha" maxlength="5"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="center" colspan="2">
                                <input type="button" class="buttoncenter" value="Aceptar" onclick="JavaScript:aceptar()"/>
                                &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" class="buttoncenter" value="Cancelar" onclick="JavaScript:cancelar()" />
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
            <td>&nbsp;</td>
        </tr>
    </table>
</body>
</html:html>