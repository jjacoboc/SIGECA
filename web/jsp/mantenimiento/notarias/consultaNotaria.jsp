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
    List listaNotarias = session.getAttribute("listaNotarias")!=null?(List)session.getAttribute("listaNotarias"):new ArrayList();
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
        function buscar(){
            f = document.forms[1];
            f.method.value = 'buscarNotarias';
            f.submit();
        }
        function nuevaNotaria(){
            f = document.forms[1];
            f.method.value = 'nuevaNotaria';
            f.submit();
        }
        function actualizarNotaria(actCodigoNotaria, actNombreNotaria, actCodigoUbigeo){
            f = document.forms[1];
            f.method.value = 'actualizarNotaria';
            f.actCodigoNotaria.value = actCodigoNotaria;
            f.actNombreNotaria.value = actNombreNotaria;
            f.actCodigoUbigeo.value = actCodigoUbigeo;
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
                    <html:form action="consultaNotaria-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="actCodigoNotaria"/>
                    <input type="hidden" name="actNombreNotaria"/>
                    <input type="hidden" name="actCodigoUbigeo"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">C&Oacute;DIGO NOTAR&Iacute;A&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="codigoNotaria" id="codigoNotaria" value="" maxlength="5" onkeypress="return valida(this,'int');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">NOMBRE NOTAR&Iacute;A&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nombreNotaria" id="nombreNotaria" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">C&Oacute;DIGO UBIGEO&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="codigoUbigeo" id="codigoUbigeo" value="" maxlength="6" onkeypress="return valida(this,'int');"/>
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
                                <display:table name="sessionScope.listaNotarias" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if(indiceFila != null) indice = (indiceFila.intValue()) - 1;
                                    %>
                                    <%
                                        String codigoNotaria = "";
                                        String nombreNotaria = "";
                                        String codigoUbigeo = "";
                                        if(listaNotarias!=null && !listaNotarias.isEmpty()){
                                            codigoNotaria = StringUtil.nullAsEmptyString((String)(((HashMap)listaNotarias.get(indice)).get("codigoNotaria")));
                                            nombreNotaria = StringUtil.nullAsEmptyString((String)(((HashMap)listaNotarias.get(indice)).get("nombreNotaria")));
                                            codigoUbigeo = StringUtil.nullAsEmptyString((String)(((HashMap)listaNotarias.get(indice)).get("codigoUbigeo")));
                                        }
                                    %>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:actualizarNotaria('<%=codigoNotaria%>','<%=nombreNotaria%>','<%=codigoUbigeo%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="codigoNotaria" title="C&Oacute;DIGO NOTAR&Iacute;A" sortable="true" style="text-align: center;"/>
                                    <display:column property="nombreNotaria" title="NOMBRE NOTAR&Iacute;A" sortable="true" style="text-align: center;"/>
                                    <display:column property="codigoUbigeo" title="C&Oacute;DIGO UBIGEO" sortable="true" style="text-align: center;"/>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>
                    <p align="center">
                        <input class="buttoncenter" type="button" value="Nuevo" name="bNuevo" onclick="JavaScript:nuevaNotaria();"/>
                    </p>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../../footer.jsp" %>
</body>
</html:html>