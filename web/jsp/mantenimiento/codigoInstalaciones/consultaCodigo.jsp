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
    List listaCodInstalaciones = (List)session.getAttribute("listaCodInstalaciones");
    if(listaCodInstalaciones == null)
    {
        listaCodInstalaciones = new ArrayList();
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
            f.method.value = 'buscarCodInstalaciones';
            f.submit();
        }
        function nuevoCodInstalacion()
        {
            f = document.forms[1];
            f.method.value = 'nuevoCodInstalacion';
            f.submit();
        }
        function actualizarCodInstalacion(actCodigoInstalacion, actDescripcionInstalacion, actUnidad, actMaterial)
        {
            f = document.forms[1];
            f.method.value = 'actualizarCodInstalacion';
            f.actCodigoInstalacion.value = actCodigoInstalacion;
            f.actDescripcionInstalacion.value = actDescripcionInstalacion;
            f.actUnidad.value = actUnidad;
            f.actMaterial.value = actMaterial;
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
                    <html:form action="consultaCodInstalacion-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="actCodigoInstalacion"/>
                    <input type="hidden" name="actDescripcionInstalacion"/>
                    <input type="hidden" name="actUnidad"/>
                    <input type="hidden" name="actMaterial"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">C&Oacute;DIGO INSTALACI&Oacute;N&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="codigoInstalacion" id="codigoInstalacion" value="" size="2" maxlength="2" onkeypress="return valida(this,'int');" />
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">DESCRIPCI&Oacute;N INSTALACI&Oacute;N&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="descripcionInstalacion" id="descripcionInstalacion" value="" size="50" maxlength="50"  onkeyup="this.value=this.value.toUpperCase();"  />
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">UNIDAD&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="unidad" id="unidad" value="" size="30" maxlength="30" />
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">MATERIAL&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casilla" name="material" id="material" value="" size="50" maxlength="50" />
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
                                <display:table name="sessionScope.listaCodInstalaciones" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <%
                                    String codigoInstalacion = "";
                                    String descripcionInstalacion = "";
                                    String unidad = "";
                                    String material = "";
                                    if(listaCodInstalaciones!=null && !listaCodInstalaciones.isEmpty())
                                    {
                                        codigoInstalacion = StringUtil.nullAsEmptyString((String)(((HashMap)listaCodInstalaciones.get(indice)).get("codigoInstalacion")));
                                        descripcionInstalacion = StringUtil.nullAsEmptyString((String)(((HashMap)listaCodInstalaciones.get(indice)).get("descripcionInstalacion")));
                                        unidad = StringUtil.nullAsEmptyString((String)(((HashMap)listaCodInstalaciones.get(indice)).get("unidad")));
                                        material = StringUtil.nullAsEmptyString((String)(((HashMap)listaCodInstalaciones.get(indice)).get("material")));
                                    }%>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:actualizarCodInstalacion('<%=codigoInstalacion%>','<%=descripcionInstalacion%>','<%=unidad%>','<%=material%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="codigoInstalacion" title="C&Oacute;DIGO INSTALACI&Oacute;N" sortable="true" style="text-align: center;"/>
                                    <display:column property="descripcionInstalacion" title="DESCRIPCI&Oacute;N INSTALACI&Oacute;N" sortable="true" style="text-align: center;"/>
                                    <display:column property="unidad" title="UNIDAD" sortable="true" style="text-align: center;"/>
                                    <display:column property="material" title="MATERIAL" sortable="true" style="text-align: center;"/>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>

                    <p align="center">
                    <input class="buttoncenter" type="button" value="Nuevo" name="bNuevo" onclick="JavaScript:nuevoCodInstalacion();"/>
                    </p>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../../footer.jsp" %>
</body>
</html:html>