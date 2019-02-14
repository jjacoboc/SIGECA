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
    List listaUbigeos = (List)session.getAttribute("listaUbigeos");
    if(listaUbigeos == null)
    {
        listaUbigeos = new ArrayList();
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
            f.method.value = 'buscarUbigeo';
            f.submit();
        }
        function nuevoUbigeo()
        {
            f = document.forms[1];
            f.method.value = 'nuevoUbigeo';
            f.submit();
        }
        function actualizarUbigeo(actCodigoUbigeo, actNombreUbigeo, actCucDesde, actCucHasta, actUltimoCuc)
        {
            f = document.forms[1];
            f.method.value = 'actualizarUbigeo';
            f.actCodigoUbigeo.value = actCodigoUbigeo;
            f.actNombreUbigeo.value = actNombreUbigeo;
            f.actCucDesde.value = actCucDesde;
            f.actCucHasta.value = actCucHasta;
            f.actUltimoCuc.value = actUltimoCuc;
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
                    <html:form action="consultaUbigeo-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="actCodigoUbigeo"/>
                    <input type="hidden" name="actNombreUbigeo"/>
                    <input type="hidden" name="actCucDesde"/>
                    <input type="hidden" name="actCucHasta"/>
                    <input type="hidden" name="actUltimoCuc"/>
                    
                    <table width="720" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <display:table name="sessionScope.listaUbigeos" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <%
                                    String codigoUbigeo = "";
                                    String nombreUbigeo = "";
                                    String cucDesde = "";
                                    String cucHasta = "";
                                    String ultimoCuc = "";
                                    if(listaUbigeos!=null && !listaUbigeos.isEmpty())
                                    {
                                        codigoUbigeo = StringUtil.nullAsEmptyString((String)(((HashMap)listaUbigeos.get(indice)).get("codigoUbigeo")));
                                        nombreUbigeo = StringUtil.nullAsEmptyString((String)(((HashMap)listaUbigeos.get(indice)).get("nombreUbigeo")));
                                        cucDesde = StringUtil.nullAsEmptyString((String)(((HashMap)listaUbigeos.get(indice)).get("cucDesde")));
                                        cucHasta = StringUtil.nullAsEmptyString((String)(((HashMap)listaUbigeos.get(indice)).get("cucHasta")));
                                        ultimoCuc = StringUtil.nullAsEmptyString((String)(((HashMap)listaUbigeos.get(indice)).get("ultimoCuc")));
                                    }%>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:actualizarUbigeo('<%=codigoUbigeo%>','<%=nombreUbigeo%>','<%=cucDesde%>','<%=cucHasta%>','<%=ultimoCuc%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="codigoUbigeo" title="C&Oacute;DIGO UBIGEO" sortable="true" style="text-align: center;"/>
                                    <display:column property="nombreUbigeo" title="NOMBRE UBIGEO" sortable="true" style="text-align: center;"/>
                                    <display:column property="cucDesde" title="CUC DESDE" sortable="true" style="text-align: center;"/>
                                    <display:column property="cucHasta" title="CUC HASTA" sortable="true" style="text-align: center;"/>
                                    <display:column property="ultimoCuc" title="ULTIMO CUC" sortable="true" style="text-align: center;"/>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>

                    <p align="center">
                    <input class="buttoncenter" type="button" value="Nuevo" name="bNuevo" onclick="JavaScript:nuevoUbigeo();"/>
                    </p>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../../footer.jsp" %>
</body>
</html:html>