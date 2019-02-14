<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@page import="org.apache.struts.action.*" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="displaytag.tld" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
        <title>Actualizar Ubigeo</title>
    </head>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" language="JavaScript">
        function guardarUbigeo()
        {
            f = document.forms[0];
            f.method.value = 'actualizarUbigeo';
            f.submit();
        }
        function eliminarUbigeo()
        {
            if (confirm("Desea eliminar el registro"))
            {
                f = document.forms[0];
                f.method.value = 'eliminarUbigeo';
                f.submit();
            }
        }
        function cancelar()
        {
            f = document.forms[0];
            f.method.value = 'cancelar';
            f.submit();
        }
    </script>
    <body>
        <html:form action="actualizarUbigeo-action" method="post" >
            <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td class="Titulo1">ACTUALIZAR UBIGEO</td>
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
                                    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="clsTabla2">
                                        <tr>
                                            <td class="etiquetanegra" colspan="4" height="30">
                                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DATOS:
                                            </td>
                                        </tr>
                                        <%--
                                        <tr>
                                            <td colspan="2">
                                                <hr/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DEPARTAMENTO</td>
                                            <td>
                                                <%
                                                    String descripcionDepartamento = (String)request.getAttribute("descripcionDepartamento")==null?"":(String)request.getAttribute("descripcionDepartamento");
                                                %>
                                                <input type="text" class="casilla" name="descripcionDepartamento" id="descripcionDepartamento" value="<%=descripcionDepartamento%>" size="50" maxlength="50" readOnly/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;PROVINCIA</td>
                                            <td>
                                                <%
                                                    String descripcionProvincia = (String)request.getAttribute("descripcionProvincia")==null?"":(String)request.getAttribute("descripcionProvincia");
                                                %>
                                                <input type="text" class="casilla" name="descripcionProvincia" id="descripcionProvincia" value="<%=descripcionProvincia%>" size="50" maxlength="50" readOnly/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DISTRITO</td>
                                            <td>
                                                <%
                                                    String descripcionDistrito = (String)request.getAttribute("descripcionDistrito")==null?"":(String)request.getAttribute("descripcionDistrito");
                                                %>
                                                <input type="text" class="casilla" name="descripcionDistrito" id="descripcionDistrito" value="<%=descripcionDistrito%>" size="50" maxlength="50" readOnly/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <hr/>
                                            </td>
                                        </tr>
                                        --%>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO UBIGEO</td>
                                            <td>
                                                <%
                                                    String codigoUbigeo = (String)session.getAttribute("codigoUbigeo");
                                                    codigoUbigeo = codigoUbigeo==null? "": codigoUbigeo;
                                                %>
                                                <input type="text" class="casilla" name="codigoUbigeo" id="codigoUbigeo" value="<%=codigoUbigeo%>" size="6" maxlength="6" readOnly/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE UBIGEO</td>
                                            <td>
                                                <%
                                                    String nombreUbigeo = (String)session.getAttribute("nombreUbigeo");
                                                    nombreUbigeo = nombreUbigeo==null? "": nombreUbigeo.trim();
                                                %>
                                                <input type="text" class="casilla" name="nombreUbigeo" id="nombreUbigeo" value="<%=nombreUbigeo%>" size="50" maxlength="100" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CUC DESDE</td>
                                            <td>
                                                <%
                                                    String cucDesde = (String)session.getAttribute("cucDesde");
                                                    cucDesde = cucDesde==null? "": cucDesde;
                                                %>
                                                <input type="text" class="casilla" name="cucDesde" id="cucDesde" value="<%=cucDesde%>" size="8" maxlength="8" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;CUC HASTA</td>
                                            <td>
                                                <%
                                                    String cucHasta = (String)session.getAttribute("cucHasta");
                                                    cucHasta = cucHasta==null? "": cucHasta;
                                                %>
                                                <input type="text" class="casilla" name="cucHasta" id="cucHasta" value="<%=cucHasta%>" size="8" maxlength="8" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;&Uacute;LTIMO CUC</td>
                                            <td>
                                                <%
                                                    String ultimoCuc = (String)session.getAttribute("ultimoCuc");
                                                    ultimoCuc = ultimoCuc==null? "": ultimoCuc;
                                                %>
                                                <input type="text" class="casilla" name="ultimoCuc" id="ultimoCuc" value="<%=ultimoCuc%>" size="8" maxlength="8" />
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
                                    <input class="buttoncenter" type="button" value="Eliminar" name="bEliminar" onclick="JavaScript:eliminarUbigeo();"/>
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