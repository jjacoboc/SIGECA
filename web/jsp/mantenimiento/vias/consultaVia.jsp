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
    List listaVia = session.getAttribute("listaVia")!=null?(List)session.getAttribute("listaVia"):new ArrayList();
    List listaTipoVia = session.getAttribute("listaTipoVia")!=null?(List)session.getAttribute("listaTipoVia"):new ArrayList();
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
            f.method.value = 'buscarVia';
            f.submit();
        }
        function nuevaVia(){
            f = document.forms[1];
            f.method.value = 'nuevaVia';
            f.submit();
        }
        function actualizarVia(hid_cod_via, hid_nom_via, hid_tip_via, hid_id_ubi_geo){
            f = document.forms[1];
            f.method.value = 'actualizarVia';
            f.hid_cod_via.value = hid_cod_via;
            f.hid_nom_via.value = hid_nom_via;
            f.hid_tip_via.value = hid_tip_via;
            f.hid_id_ubi_geo.value = hid_id_ubi_geo;
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
                    <html:form action="consultaVia-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="hid_cod_via"/>
                    <input type="hidden" name="hid_nom_via"/>
                    <input type="hidden" name="hid_tip_via"/>
                    <input type="hidden" name="hid_id_ubi_geo"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">C&Oacute;DIGO VIA&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="cod_via" id="cod_via" maxlength="6" onkeypress="return valida(this,'int');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">TIPO VIA&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <select class="clsComboDoc" name="tip_via">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTipoVia.size();i++){%>
                                        <%String codTipVia = ((TablasCodigosBean)listaTipoVia.get(i)).getCodigo().trim();%>
                                        <%String descripcion = ((TablasCodigosBean)listaTipoVia.get(i)).getDesc_codigo().trim();%>
                                        <option value="<%=codTipVia%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">NOMBRE VIA&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nom_via" id="nom_via" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'str');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">C&Oacute;DIGO UBIGEO&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="id_ubi_geo" id="id_ubi_geo" maxlength="6" onkeypress="return valida(this,'int');"/>
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
                                <display:table name="sessionScope.listaVia" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <%
                                    String cod_via = "";
                                    String nom_via = "";
                                    String tip_via = "";
                                    String desc_tip_via = "";
                                    String id_ubi_geo = "";
                                    if(listaVia!=null && !listaVia.isEmpty()){
                                        cod_via = StringUtil.nullAsEmptyString((String)(((HashMap)listaVia.get(indice)).get("cod_via")));
                                        nom_via = StringUtil.nullAsEmptyString((String)(((HashMap)listaVia.get(indice)).get("nom_via")));
                                        tip_via = StringUtil.nullAsEmptyString((String)(((HashMap)listaVia.get(indice)).get("tip_via")));
                                        desc_tip_via = StringUtil.nullAsEmptyString((String)(((HashMap)listaVia.get(indice)).get("desc_tip_via")));
                                        id_ubi_geo = StringUtil.nullAsEmptyString((String)(((HashMap)listaVia.get(indice)).get("id_ubi_geo")));
                                    }
                                    %>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:actualizarVia('<%=cod_via%>','<%=nom_via%>','<%=tip_via%>','<%=id_ubi_geo%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="cod_via" title="C&Oacute;DIGO DE VIA" sortable="true" style="text-align: center;width: 10%"/>
                                    <display:column property="desc_tip_via" title="TIPO VIA" sortable="true" style="text-align: center;width: 20%"/>
                                    <display:column property="nom_via" title="NOMBRE VIA" sortable="true" style="text-align: center;width: 55%"/>
                                    <display:column property="id_ubi_geo" title="C&Oacute;DIGO UBIGEO" sortable="true" style="text-align: center;width: 10%"/>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>
                    <p align="center">
                        <input class="buttoncenter" type="button" value="Nuevo" name="bNuevo" onclick="JavaScript:nuevaVia();"/>
                    </p>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../../footer.jsp" %>
</body>
</html:html>