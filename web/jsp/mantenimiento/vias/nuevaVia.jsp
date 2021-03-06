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
    List listaTipoVia = session.getAttribute("listaTipoVia")!=null?(List)session.getAttribute("listaTipoVia"):new ArrayList();
%>
<html:html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
        <title>BMP Geom&aacute;tica S.A.</title>
    </head>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" language="JavaScript">
        function guardarVia(){
            f = document.forms[0];
            f.method.value = 'insertarVia';
            f.submit();
        }
        function cancelar(){
            f = document.forms[0];
            f.method.value = 'cancelar';
            f.submit();
        }
    </script>
    <body>
        <html:form action="nuevaVia-action" method="post" >
            <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td class="Titulo1">NUEVA VIA</td>
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
                                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="clsTabla2">
                                        <tr>
                                            <td class="etiquetanegra" colspan="4" height="30">
                                                &nbsp;<img src="<%=request.getContextPath()%>/imagenes/vineta.gif" width="7" height="9" alt="vineta"/>DATOS:
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO VIA</td>
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
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO VIA</td>
                                            <td>
                                                <input type="text" class="casillaNro" name="cod_via" id="cod_via" value="" maxlength="6" onkeypress="return valida(this,'int');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE VIA</td>
                                            <td>
                                                <input type="text" class="casillaLarga" name="nom_via" id="nom_via" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO UBIGEO</td>
                                            <td>
                                                <input type="text" class="casillaNro" name="id_ubi_geo" id="id_ubi_geo" value="" maxlength="6" onkeypress="return valida(this,'int');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <p align="center">
                            <input class="buttoncenter" type="button" value="Guardar" name="bGuardar" onclick="JavaScript:guardarVia();"/>
                            <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar();"/>
                        </p>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>