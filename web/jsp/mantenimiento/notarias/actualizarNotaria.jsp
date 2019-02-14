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
        <title>Actualizar Notar&iacute;a</title>
    </head>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" language="JavaScript">
        function guardarNotaria(){
            f = document.forms[0];
            f.method.value = 'actualizarNotaria';
            f.submit();
        }
        function eliminarNotaria(){
            if (confirm("Desea eliminar el registro"))
            {
                f = document.forms[0];
                f.method.value = 'eliminarNotaria';
                f.submit();
            }
        }
        function cancelar(){
            f = document.forms[0];
            f.method.value = 'cancelar';
            f.submit();
        }
    </script>
    <body>
        <html:form action="actualizarNotaria-action" method="post" >
            <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td class="Titulo1">ACTUALIZAR NOTAR&Iacute;A</td>
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
                            if(error != null){
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
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO NOTAR&Iacute;A</td>
                                            <td>
                                                <%
                                                    String codigoNotaria = (String)session.getAttribute("codigoNotaria");
                                                    codigoNotaria = codigoNotaria==null? "": codigoNotaria;
                                                %>
                                                <input type="text" class="casillaNro" name="codigoNotaria" id="codigoNotaria" value="<%=codigoNotaria%>" maxlength="5" onkeypress="return valida(this,'int');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE NOTAR&Iacute;A</td>
                                            <td>
                                                <%
                                                    String nombreNotaria = (String)session.getAttribute("nombreNotaria");
                                                    nombreNotaria = nombreNotaria==null? "": nombreNotaria.trim();
                                                %>
                                                <input type="text" class="casillaLarga" name="nombreNotaria" id="nombreNotaria" value="<%=nombreNotaria%>" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO UBIGEO</td>
                                            <td>
                                                <%
                                                    String codigoUbigeo = (String)session.getAttribute("codigoUbigeo");
                                                    codigoUbigeo = codigoUbigeo==null? "": codigoUbigeo;
                                                %>
                                                <input type="text" class="casillaNro" name="codigoUbigeo" id="codigoUbigeo" value="<%=codigoUbigeo%>" maxlength="6" onkeypress="return valida(this,'int');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <p align="center">
                            <input class="buttoncenter" type="button" value="Guardar" name="bGuardar" onclick="JavaScript:guardarNotaria();"/>
                            <input class="buttoncenter" type="button" value="Eliminar" name="bEliminar" onclick="JavaScript:eliminarNotaria();"/>
                            <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar();"/>
                        </p>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>