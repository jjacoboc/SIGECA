<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>
<%@page import="org.apache.struts.action.*" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="displaytag.tld" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    List listaTrabajadores = session.getAttribute("listaTrabajadores")!=null?(List)session.getAttribute("listaTrabajadores"):new ArrayList();
    List listaCargo = session.getAttribute("listaCargo")!=null?(List)session.getAttribute("listaCargo"):new ArrayList();
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
            f.method.value = 'buscarTrabajador';
            f.submit();
        }
        function nuevoTrabajador(){
            f = document.forms[1];
            f.method.value = 'nuevoTrabajador';
            f.submit();
        }
        function actualizarVia(ind){
            f = document.forms[1];
            f.method.value = 'actualizarTrabajdor';
            f.indice.value = ind
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
                    <html:form action="consultaTrabajador-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="indice"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">DNI&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="dni" id="dni" value="" maxlength="8" onkeypress="return valida(this,'int');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">NOMBRES&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nombres" id="nombres" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">APELLIDO PATERNO&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="ape_paterno" id="ape_paterno" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">APELLIDO MATERNO&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="ape_materno" id="ape_materno" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">CARGO&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <select class="clsComboDoc" name="id_cargo">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaCargo.size();i++){%>
                                        <%String id_cargo = ((String)((HashMap)listaCargo.get(i)).get("id_cargo")).trim();%>
                                        <%String nombre_cargo = ((String)((HashMap)listaCargo.get(i)).get("nombre_cargo")).trim();%>
                                        <option value="<%=id_cargo%>">
                                            <%=nombre_cargo%>
                                        </option>
                                    <%}%>
                                </select>
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
                                <display:table name="sessionScope.listaTrabajadores" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:actualizarVia('<%=indice%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="Editar Trabajador" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="id_trabajador" title="C&Oacute;DIGO TRABAJADOR" sortable="true" style="text-align: center;width: 5%"/>
                                    <display:column property="dni" title="DNI" sortable="true" style="text-align: center;width: 10%"/>
                                    <display:column property="nombres" title="NOMBRES" sortable="true" style="text-align: center;width: 20%"/>
                                    <display:column property="ape_paterno" title="APELLIDO PATERNO" sortable="true" style="text-align: center;width: 20%"/>
                                    <display:column property="ape_materno" title="APELLIDO MATERNO" sortable="true" style="text-align: center;width: 20%"/>
                                    <display:column property="email" title="EMAIL" sortable="true" style="text-align: center;width: 10%"/>
                                    <display:column property="nombre_cargo" title="CARGO" sortable="true" style="text-align: center;width: 10%"/>
                                    <display:column property="nombre_estado" title="ESTADO" sortable="true" style="text-align: center;width: 10%"/>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <input class="buttoncenter" type="button" value="Nuevo" name="bNuevo" onclick="JavaScript:nuevoTrabajador();"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">&nbsp;</td>
                        </tr>
                    </table>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../../footer.jsp" %>
</body>
</html:html>