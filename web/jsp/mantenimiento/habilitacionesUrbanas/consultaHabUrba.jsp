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
    List listaHabUrba = session.getAttribute("listaHabUrba")!=null?(List)session.getAttribute("listaHabUrba"):new ArrayList();
    List listaTipoHabUrba = session.getAttribute("listaTipoHabUrba")!=null?(List)session.getAttribute("listaTipoHabUrba"):new ArrayList();
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
            f.method.value = 'buscarHabUrba';
            f.submit();
        }
        function nuevaHabUrba(){
            f = document.forms[1];
            f.method.value = 'nuevaHabUrba';
            f.submit();
        }
        function actualizarHabUrba(hid_cod_hab_urba, hid_nom_hab_urba, hid_grupo_urba, hid_id_ubi_geo, hid_tip_hab_urba){
            f = document.forms[1];
            f.method.value = 'actualizarHabUrba';
            f.hid_cod_hab_urba.value = hid_cod_hab_urba;
            f.hid_nom_hab_urba.value = hid_nom_hab_urba;
            f.hid_grupo_urba.value = hid_grupo_urba;
            f.hid_id_ubi_geo.value = hid_id_ubi_geo;
            f.hid_tip_hab_urba.value = hid_tip_hab_urba;
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
                    <html:form action="consultaHabUrba-action" method="post">
                    <input type="hidden" name="method"/>
                    <input type="hidden" name="hid_cod_hab_urba"/>
                    <input type="hidden" name="hid_nom_hab_urba"/>
                    <input type="hidden" name="hid_grupo_urba"/>
                    <input type="hidden" name="hid_id_ubi_geo"/>
                    <input type="hidden" name="hid_tip_hab_urba"/>
                    <table width="720" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">SECTOR&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaMediana" name="grupo_urba" id="grupo_urba" value="" maxlength="30" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">C&Oacute;DIGO HABILITACI&Oacute;N URBANA&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="cod_hab_urba" id="cod_hab_urba" value="" maxlength="4" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'int');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" width="360" align="right">TIPO HABILITACI&Oacute;N URBANA&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <select class="clsComboMediano" name="tip_hab_urba">
                                    <option value="">Seleccione</option>
                                    <%for(int i=0;i<listaTipoHabUrba.size();i++){%>
                                        <%String codTipHabUrba = ((TablasCodigosBean)listaTipoHabUrba.get(i)).getCodigo();%>
                                        <%String descripcion = ((TablasCodigosBean)listaTipoHabUrba.get(i)).getDesc_codigo();%>
                                        <option value="<%=codTipHabUrba%>">
                                            <%=descripcion%>
                                        </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">NOMBRE HABILITACI&Oacute;N URBANA&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaLarga" name="nom_hab_urba" id="nom_hab_urba" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'str');"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="24" align="right">C&Oacute;DIGO UBIGEO&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <input type="text" class="casillaNro" name="id_ubi_geo" id="id_ubi_geo" value="" maxlength="6" onkeypress="return valida(this,'int');"/>
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
                                <display:table name="sessionScope.listaHabUrba" defaultsort="1" export="false" pagesize="10" class="displaytag" id="row">
                                    <%
                                        Integer indiceFila = (Integer)pageContext.getAttribute("row_rowNum");
                                        int indice = 0;
                                        if (indiceFila!=null) indice=(indiceFila.intValue())-1;
                                    %>
                                    <%
                                    String cod_hab_urba = "";
                                    String nom_hab_urba = "";
                                    String tip_hab_urba = "";
                                    String grupo_urba = "";
                                    String id_ubi_geo = "";
                                    if(listaHabUrba!=null && !listaHabUrba.isEmpty()){
                                        cod_hab_urba = StringUtil.nullAsEmptyString((String)(((HashMap)listaHabUrba.get(indice)).get("cod_hab_urba")));
                                        nom_hab_urba = StringUtil.nullAsEmptyString((String)(((HashMap)listaHabUrba.get(indice)).get("nom_hab_urba")));
                                        tip_hab_urba = StringUtil.nullAsEmptyString((String)(((HashMap)listaHabUrba.get(indice)).get("tip_hab_urba")));
                                        grupo_urba = StringUtil.nullAsEmptyString((String)(((HashMap)listaHabUrba.get(indice)).get("grupo_urba")));
                                        id_ubi_geo = StringUtil.nullAsEmptyString((String)(((HashMap)listaHabUrba.get(indice)).get("id_ubi_geo")));
                                    }
                                    %>
                                    <display:column title="EDITAR" media="html" style="text-align: center;width: 5%">
                                        <a href="JavaScript:actualizarHabUrba('<%=cod_hab_urba%>','<%=nom_hab_urba%>','<%=grupo_urba%>','<%=id_ubi_geo%>','<%=tip_hab_urba%>')"><img src="<%=request.getContextPath()%>/imagenes/editar.gif" alt="ver ficha" border="0" align="center"/></a>
                                    </display:column>
                                    <display:column property="grupo_urba" title="SECTOR" sortable="true" style="text-align: center;"/>
                                    <display:column property="cod_hab_urba" title="C&Oacute;DIGO HABILITACI&Oacute;N URBANA" sortable="true" style="text-align: center;"/>
                                    <display:column property="desc_tip_hab_urba" title="TIPO HABILITACI&Oacute;N URBANA" sortable="true" style="text-align: center;"/>
                                    <display:column property="nom_hab_urba" title="NOMBRE HABILITACI&Oacute;N URBANA" sortable="true" style="text-align: center;"/>
                                    <display:column property="id_ubi_geo" title="C&Oacute;DIGO UBIGEO" sortable="true" style="text-align: center;"/>
                                    <display:setProperty name="basic.empty.showtable" value="true"/>
                                </display:table>
                            </td>
                        </tr>
                    </table>
                    <p align="center">
                    <input class="buttoncenter" type="button" value="Nuevo" name="bNuevo" onclick="JavaScript:nuevaHabUrba();"/>
                    </p>
                </html:form>
            </td>
        </tr>
    </table>
    <%@include file="../../footer.jsp" %>
</body>
</html:html>