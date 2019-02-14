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
    List listaTablasCodigos = session.getAttribute("listaTablasCodigos")!=null?(List)session.getAttribute("listaTablasCodigos"):new ArrayList();
    List listaTablas = session.getAttribute("listaTablas")!=null?(List)session.getAttribute("listaTablas"):new ArrayList();
%>

<html:html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
        <title>BMP Geomática S.A.</title>
    </head>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" language="JavaScript">
        function buscar()
        {
            f = document.forms[1];
            f.method.value = 'buscarTablasCodigos';
            f.submit();
        }
        function nuevaTablaCodigo()
        {
            f = document.forms[1];
            f.method.value = 'nuevaTablaCodigo';
            f.submit();
        }
        function actualizarTablaCodigo(actId, actCodigo, actDescripcion)
        {
            f = document.forms[1];
            f.method.value = 'actualizarTablaCodigo';
            f.actId.value = actId;
            f.actCodigo.value = actCodigo;
            f.actDescripcion.value = actDescripcion;
            f.submit();
        }
    </script>

    <body>
        <p>&nbsp;</p>
        <%@include file="../../header.jsp" %>
        <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
            <tr bgcolor="#004d9a">
                <td align="center"><%@include file="../../menu.jsp" %></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td valign="middle">
                    <html:form action="consultaTablasCodigos-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="actId"/>
                    <input type="hidden" name="actCodigo"/>
                    <input type="hidden" name="actDescripcion"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">ID&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <select class="clsComboLargo" name="id">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTablas.size();i++){%>
                                        <%String codigo = ((TablasBean)listaTablas.get(i)).getId_tabla().trim();%>
                                        <%String descripcion = ((TablasBean)listaTablas.get(i)).getDesc_tabla().trim();%>
                                        <option value="<%=codigo%>">
                                            <%=codigo+" - "+descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <%--
                        <tr>
                            <td height="24" width="360" align="right">ID&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="id" id="id" maxlength="3" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        --%>
                        <tr>
                            <td height="24" align="right">C&Oacute;DIGO&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="codigo" id="codigo" maxlength="6" />
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">DESCRIPCI&Oacute;N&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="descripcion" id="descripcion" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" />
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table width="730" align="center" cellPadding="0" cellSpacing="0">
                        <tr>
                            <td align="left">&nbsp;<input class="buttoncenter" type="button" value="Buscar" name="bBuscar" onclick="JavaScript:buscar();"/></td>
                        </tr>
                    </table>
                    <table width="720" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <display:table name="sessionScope.listaTablasCodigos" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <%
                                    String id = "";
                                    String codigo = "";
                                    String descripcion = "";
                                    if(listaTablasCodigos!=null && !listaTablasCodigos.isEmpty())
                                    {
                                        id = StringUtil.nullAsEmptyString((String)(((HashMap)listaTablasCodigos.get(indice)).get("id")));
                                        codigo = StringUtil.nullAsEmptyString((String)(((HashMap)listaTablasCodigos.get(indice)).get("codigo")));
                                        descripcion = StringUtil.nullAsEmptyString((String)(((HashMap)listaTablasCodigos.get(indice)).get("descripcion")));
                                    }%>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:actualizarTablaCodigo('<%=id%>','<%=codigo%>','<%=descripcion%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="id" title="ID" sortable="true" style="text-align: center;"/>
                                    <display:column property="codigo" title="C&Oacute;DIGO" sortable="true" style="text-align: center;"/>
                                    <display:column property="descripcion" title="DESCRIPCI&Oacute;N" sortable="true" style="text-align: center;"/>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>

                    <p align="center">
                    <input class="buttoncenter" type="button" value="Nuevo" name="bNuevo" onclick="JavaScript:nuevaTablaCodigo();"/>
                    </p>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../../footer.jsp" %>
</body>
</html:html>