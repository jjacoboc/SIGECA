<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasBean" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@page import="org.apache.struts.action.*" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="displaytag.tld" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    List listaTablas = session.getAttribute("listaTablas")!=null?(List)session.getAttribute("listaTablas"):new ArrayList();
%>

<html:html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
        <title>Actualizar Notar&iacute;a</title>
    </head>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" language="JavaScript">
        function guardarTablaCodigo()
        {
            f = document.forms[0];
            f.method.value = 'actualizarTablaCodigo';
            f.submit();
        }
        function eliminarTablaCodigo()
        {
            if (confirm("Desea eliminar el registro"))
            {
                f = document.forms[0];
                f.method.value = 'eliminarTablaCodigo';
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
        <html:form action="actualizarTablaCodigo-action" method="post" >
            <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td class="Titulo1">ACTUALIZAR TABLA C&Oacute;DIGO</td>
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
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ID</td>
                                            <td>
                                                <%
                                                    String id = (String)session.getAttribute("id");
                                                    id = id==null? "": id;
                                                %>
                                                <select class="clsComboLargo" name="id2" disabled>
                                                    <option value="">Seleccione</option>
                                                    <%for(int i=0;i<listaTablas.size();i++){%>
                                                        <%String codigo = ((TablasBean)listaTablas.get(i)).getId_tabla().trim();%>
                                                        <%String descripcion = ((TablasBean)listaTablas.get(i)).getDesc_tabla().trim();%>
                                                        <option <%if(id.equals(codigo)){%>selected<%}%> value="<%=codigo%>">
                                                            <%=codigo+" - "+descripcion%>
                                                        </option>
                                                    <%}%>
                                                </select>
                                                <input type="hidden" name="id" value="<%=id%>"/>
                                            </td>
                                        </tr>
                                        <%--
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;ID</td>
                                            <td>
                                                <%
                                                    String id = (String)session.getAttribute("id");
                                                    id = id==null? "": id;
                                                %>
                                                <input type="text" class="casillaNro" name="id" id="id" value="<%=id%>" maxlength="3" readOnly/>
                                            </td>
                                        </tr>
                                        --%>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO</td>
                                            <td>
                                                <%
                                                    String codigo = (String)session.getAttribute("codigo");
                                                    codigo = codigo==null? "": codigo.trim();
                                                %>
                                                <input type="text" class="casillaNro" name="codigo" id="codigo" value="<%=codigo%>" maxlength="6" onkeyup="this.value=this.value.toUpperCase();" readonly/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;DESCRIPCI&Oacute;N</td>
                                            <td>
                                                <%
                                                    String descripcion = (String)session.getAttribute("descripcion");
                                                    descripcion = descripcion==null? "": descripcion.trim();
                                                %>
                                                <input type="text" class="casillaLarga" name="descripcion" id="descripcion" value="<%=descripcion%>" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
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
                                    <input class="buttoncenter" type="button" value="Guardar" name="bGuardar" onclick="JavaScript:guardarTablaCodigo();"/>
                                    <input class="buttoncenter" type="button" value="Eliminar" name="bEliminar" onclick="JavaScript:eliminarTablaCodigo();"/>
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