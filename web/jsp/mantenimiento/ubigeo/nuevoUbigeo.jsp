<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="com.bmp.sigeca.maestro.bean.UbigeoBean" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@page import="org.apache.struts.action.*" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="displaytag.tld" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
    String nombreUbigeo = "";
    List listaDepartamento =  session.getAttribute("listaDepartamento")!=null?(List) session.getAttribute("listaDepartamento"):new ArrayList();
    List listaProvincia =  session.getAttribute("listaProvincia")!=null?(List) session.getAttribute("listaProvincia"):new ArrayList();
    List listaDistrito =  session.getAttribute("listaDistrito")!=null?(List) session.getAttribute("listaDistrito"):new ArrayList();
    String departamento = session.getAttribute("departamento")!=null?(String)session.getAttribute("departamento"):"";
    String provincia = session.getAttribute("provincia")!=null?(String)session.getAttribute("provincia"):"";
    String distrito = session.getAttribute("distrito")!=null?(String)session.getAttribute("distrito"):"";
%>
<html:html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
        <title>Nuevo Ubigeo</title>
    </head>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" language="JavaScript">
        function guardarUbigeo()
        {
            f = document.forms[0];
            f.method.value = 'insertarUbigeo';
            f.submit();
        }
        function obtenerProvincias()
        {
            f = document.forms[0];
            f.method.value = 'obtenerProvincias';
            f.submit();
        }
        function obtenerDistritos()
        {
            f = document.forms[0];
            f.method.value = 'obtenerDistritos';
            f.submit();
        }
        function actualizarCodigo()
        {
            f = document.forms[0];
            f.method.value = 'actualizarCodigo';
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
        function setUbigeo(){
            f = document.forms[0];
            f.codigoUbigeo.value = f.departamento.value+f.provincia.value+f.codigoDistrito.value
        }
        function cancelar()
        {
            f = document.forms[0];
            f.method.value = 'cancelar';
            f.submit();
        }
    </script>
    <body>
        <html:form action="nuevoUbigeo-action" method="post" >
            <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td class="Titulo1">NUEVO UBIGEO</td>
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
                                            <td colspan="2">
                                                <hr/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DEPARTAMENTO</td>
                                            <td>
                                                <select class="clsComboMediano" name="departamento" onchange="JavaScript:cargarProvincia();">
                                                    <option value="">Seleccione</option>
                                                    <%for(int i=0;i<listaDepartamento.size();i++){%>
                                                        <%String codDepartamento = ((UbigeoBean)listaDepartamento.get(i)).getCodDepartamento().trim();%>
                                                        <%String descripcion = ((UbigeoBean)listaDepartamento.get(i)).getDescripcion().trim();%>
                                                        <option <%if(departamento.equals(codDepartamento)){%>selected<%}%> value="<%=codDepartamento%>">
                                                            <%=descripcion%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;PROVINCIA</td>
                                            <td>
                                                <select class="clsComboMediano" name="provincia" onchange="JavaScript:cargarDistrito();">
                                                    <option value="">Seleccione</option>
                                                    <%for(int i=0;i<listaProvincia.size();i++){%>
                                                        <%String codProvincia = ((UbigeoBean)listaProvincia.get(i)).getCodProvincia().trim();%>
                                                        <%String descripcion = ((UbigeoBean)listaProvincia.get(i)).getDescripcion().trim();%>
                                                        <option <%if(provincia.equals(codProvincia)){%>selected<%}%> value="<%=codProvincia%>">
                                                            <%=descripcion%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                        </tr>
                                        <%--
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DISTRITO</td>
                                            <td>
                                                <select class="clsComboMediano" name="distrito" onchange="JavaScript:setUbigeo();">
                                                    <option value="">Seleccione</option>
                                                    <%for(int i=0;i<listaDistrito.size();i++){%>
                                                        <%String codDistrito = ((UbigeoBean)listaDistrito.get(i)).getCodDistrito().trim();%>
                                                        <%String descripcion = ((UbigeoBean)listaDistrito.get(i)).getDescripcion().trim();%>
                                                        <option <%if(distrito.equals(codDistrito)){%>selected<%}%> value="<%=codDistrito%>">
                                                            <%=descripcion%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                        </tr>
                                        --%>
                                        <tr>
                                            <td colspan="2">
                                                <hr/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO UBIGEO</td>
                                            <td>
                                                <input type="text" class="casilla" name="codigoDistrito" id="codigoDistrito" size="2" maxlength="2" onblur="JavaScript:setUbigeo();"/>
                                                <input type="text" class="casilla" name="codigoUbigeo" id="codigoUbigeo" size="6" maxlength="6" readOnly/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE UBIGEO</td>
                                            <td>
                                                <input type="text" class="casilla" name="nombreUbigeo" id="nombreUbigeo" value="<%=nombreUbigeo%>" size="50" maxlength="100" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CUC DESDE</td>
                                            <td>
                                                <input type="text" class="casilla" name="cucDesde" id="cucDesde" value="" size="8" maxlength="8" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CUC HASTA</td>
                                            <td>
                                                <input type="text" class="casilla" name="cucHasta" id="cucHasta" value="" size="8" maxlength="8" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;&Uacute;LTIMO CUC</td>
                                            <td>
                                                <input type="text" class="casilla" name="ultimoCuc" id="ultimoCuc" value="" size="8" maxlength="8" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center">
                                    <input class="buttoncenter" type="button" value="Guardar" name="bGuardar" onclick="JavaScript:guardarUbigeo();"/>
                                    <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar();"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>