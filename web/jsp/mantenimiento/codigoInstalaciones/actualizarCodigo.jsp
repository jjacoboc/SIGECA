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
        <title>Actualizar C&Oacute;DIGO INSTALACI&Oacute;N</title>
    </head>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" language="JavaScript">
        function guardarCodInstalacion()
        {
            f = document.forms[0];
            f.method.value = 'actualizarCodInstalacion';
            f.submit();
        }
        function eliminarCodInstalacion()
        {
            if (confirm("Desea eliminar el registro"))
            {
                f = document.forms[0];
                f.method.value = 'eliminarCodInstalacion';
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
        <html:form action="actualizarCodInstalacion-action" method="post" >
            <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td class="Titulo1">ACTUALIZAR C&Oacute;DIGO INSTALACI&Oacute;N</td>
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
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO INSTALACI&Oacute;N</td>
                                            <td>
                                                <%
                                                    String codigoInstalacion = (String)session.getAttribute("codigoInstalacion");
                                                    codigoInstalacion = codigoInstalacion==null? "": codigoInstalacion;
                                                %>
                                                <input type="text" class="casilla" name="codigoInstalacion" id="codigoInstalacion" value="<%=codigoInstalacion%>" size="2" maxlength="2" onkeypress="return valida(this,'int');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DESCRIPCI&Oacute; INSTALACI&Oacute;N</td>
                                            <td>
                                                <%
                                                    String descripcionInstalacion = (String)session.getAttribute("descripcionInstalacion");
                                                    descripcionInstalacion = descripcionInstalacion==null? "": descripcionInstalacion.trim();
                                                %>
                                                <input type="text" class="casilla" name="descripcionInstalacion" id="descripcionInstalacion" value="<%=descripcionInstalacion%>" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;UNIDAD</td>
                                            <td>
                                                <%
                                                    String unidad = (String)session.getAttribute("unidad");
                                                    unidad = unidad==null? "": unidad.trim();
                                                %>
                                                <input type="text" class="casilla" name="unidad" id="unidad" value="<%=unidad%>" size="30" maxlength="30" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;MATERIAL</td>
                                            <td>
                                                <%
                                                    String material = (String)session.getAttribute("material");
                                                    material = material==null? "": material.trim();
                                                %>
                                                <input type="text" class="casilla" name="material" id="material" value="<%=material%>" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'str');"/>
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
                                    <input class="buttoncenter" type="button" value="Guardar" name="bGuardar" onclick="JavaScript:guardarCodInstalacion();"/>
                                    <input class="buttoncenter" type="button" value="Eliminar" name="bEliminar" onclick="JavaScript:eliminarCodInstalacion();"/>
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