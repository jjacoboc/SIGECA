<%--
    Document   : consultaUsos
    Created on : 13/05/2010, 05:55:09 PM
    Author     : Ricardo Avila
--%>

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

<%
    List listaUsosBC = (List)session.getAttribute("listaUsosBC");
    if(listaUsosBC == null)
    {
        listaUsosBC = new ArrayList();
    }
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
            f.method.value = 'buscarUsosBC';
            f.submit();
        }
        function nuevoUsoBC()
        {
            f = document.forms[1];
            f.method.value = 'nuevoUsoBC';
            f.submit();
        }
        function actualizarUsoBC(actCodigoUsoBC, actDescripcionUsoBC)
        {
            f = document.forms[1];
            f.method.value = 'actualizarUsoBC';
            f.actCodigoUsoBC.value = actCodigoUsoBC;
            f.actDescripcionUsoBC.value = actDescripcionUsoBC;
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
                    <html:form action="consultaUsosBC-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="actCodigoUsoBC"/>
                    <input type="hidden" name="actDescripcionUsoBC"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">C&Oacute;DIGO USO BC&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="codigoUsoBC" id="codigoUsoBC" size="6" maxlength="6" onkeypress="return valida(this,'int');" />
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">DESCRIPCI&Oacute;N USO BC&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="descripcionUsoBC" id="descripcionUsoBC" maxlength="250" onkeyup="this.value=this.value.toUpperCase()" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
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
                                <display:table name="sessionScope.listaUsosBC" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <%
                                    String codigoUsoBC = "";
                                    String descripcionUsoBC = "";
                                    if(listaUsosBC!=null && !listaUsosBC.isEmpty())
                                    {
                                        codigoUsoBC = StringUtil.nullAsEmptyString((String)(((HashMap)listaUsosBC.get(indice)).get("codigoUsoBC")));
                                        descripcionUsoBC = StringUtil.nullAsEmptyString((String)(((HashMap)listaUsosBC.get(indice)).get("descripcionUsoBC")));
                                    }%>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:actualizarUsoBC('<%=codigoUsoBC%>','<%=descripcionUsoBC%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="codigoUsoBC" title="C&Oacute;DIGO USO" sortable="true" style="text-align: center;"/>
                                    <display:column property="descripcionUsoBC" title="DESCRIPCI&Oacute;N USO" sortable="true" style="text-align: center;"/>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>

                    <p align="center">
                    <input class="buttoncenter" type="button" value="Nuevo" name="bNuevo" onclick="JavaScript:nuevoUsoBC();"/>
                    </p>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../../footer.jsp" %>
</body>
</html:html>