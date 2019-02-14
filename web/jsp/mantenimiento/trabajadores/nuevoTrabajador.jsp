<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@page import="org.apache.struts.action.*" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="displaytag.tld" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    List listaCargo = session.getAttribute("listaCargo")!=null?(List)session.getAttribute("listaCargo"):new ArrayList();
    List listaEstado = session.getAttribute("listaEstado")!=null?(List)session.getAttribute("listaEstado"):new ArrayList();
%>
<html:html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
        <title>BMP Geom&aacute;tica S.A.</title>
    </head>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" language="JavaScript">
        function guardarTrabajador(){
            f = document.forms[0];
            f.method.value = 'insertarTrabajador';
            f.submit();
        }
        function cancelar(){
            f = document.forms[0];
            f.method.value = 'cancelar';
            f.submit();
        }
    </script>
    <body>
        <html:form action="nuevoTrabajador-action" method="post" >
            <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td class="Titulo1">NUEVO TRABAJADOR</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>
                        <br>
                        <input type="hidden" name="method">
                        <table width="90%" align="center" cellPadding="0" cellSpacing="0">
                            <%
                            String error = (String)request.getAttribute("error");
                            if(error != null)
                            {
                            %>
                            <tr>
                                <td align="center">
                                    <span class="pagebanner2"><%=error%></span>
                                </td>
                            </tr>
                            <%}%>
                            <tr>
                                <td>
                                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="clsTabla2">
                                        <tr>
                                            <td class="etiquetanegra" colspan="4" height="30">
                                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DATOS:
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DNI</td>
                                            <td>
                                                <input type="text" class="casillaNro" name="dni" id="dni" value="" maxlength="8" onkeypress="return valida(this,'int');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRES</td>
                                            <td>
                                                <input type="text" class="casillaLarga" name="nombres" id="nombres" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO PATERNO</td>
                                            <td>
                                                <input type="text" class="casillaLarga" name="ape_paterno" id="ape_paterno" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;APELLIDO MATERNO</td>
                                            <td>
                                                <input type="text" class="casillaLarga" name="ape_materno" id="ape_materno" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CORREO ELECTR&Oacute;NICO</td>
                                            <td>
                                                <input type="text" class="casillaLarga" name="email" id="email" value="" maxlength="50" onkeypress="return valida(this,'all');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CARGO</td>
                                            <td>
                                                <select class="clsComboDoc" name="id_cargo">
                                                    <option value="">Seleccione</option>
                                                    <%for(int i=0;i<listaCargo.size();i++){%>
                                                        <%String id_cargo = ((String)((HashMap)listaCargo.get(i)).get("id_cargo")).trim();%>
                                                        <%String nombre_cargo = ((String)((HashMap)listaCargo.get(i)).get("nombre_cargo")).trim();%>
                                                        <option value="<%=id_cargo%>">
                                                            <%=nombre_cargo%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ESTADO</td>
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
                    <td align="center">
                        <input class="buttoncenter" type="button" value="Guardar" name="bGuardar" onclick="JavaScript:guardarTrabajador();"/>
                        <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar();"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>