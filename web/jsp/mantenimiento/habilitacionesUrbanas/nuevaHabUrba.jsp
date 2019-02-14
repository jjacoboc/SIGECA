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
    List listaTipoHabUrba = session.getAttribute("listaTipoHabUrba")!=null?(List)session.getAttribute("listaTipoHabUrba"):new ArrayList();
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
        function guardarHabUrba()
        {
            f = document.forms[0];
            f.method.value = 'insertarHabilitacionUrbana';
            f.submit();
        }
        function cancelar()
        {
            f = document.forms[0];
            f.method.value = 'cancelar';
            f.submit();
        }
    </script>
    <body>
        <html:form action="nuevaHabUrba-action" method="post" >
            <table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
                <tr>
                    <td class="Titulo1">NUEVA HABILITACI&Oacute;N URBANA</td>
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
                            if(error != null){
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
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;SECTOR</td>
                                            <td>
                                                <input type="text" class="casillaDatos" name="grupo_urba" id="grupo_urba" value="" maxlength="30" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;C&Oacute;DIGO HABILITACI&Oacute;N URBANA</td>
                                            <td>
                                                <input type="text" class="casillaNro" name="cod_hab_urba" id="cod_hab_urba" value="" maxlength="4" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'int');"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;TIPO HABILITACI&Oacute;N URBANA&nbsp;&nbsp;&nbsp;</td>
                                            <td>
                                                <select class="clsComboLargo" name="tip_hab_urba">
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
                                            <td class="etiqueta" height="24">&nbsp;&nbsp;&nbsp;NOMBRE HABILITACI&Oacute;N URBANA</td>
                                            <td>
                                                <input type="text" class="casillaLarga" name="nom_hab_urba" id="nom_hab_urba" value="" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return valida(this,'afn');"/>
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
                            <input class="buttoncenter" type="button" value="Guardar" name="bGuardar" onclick="JavaScript:guardarHabUrba();"/>
                            <input class="buttoncenter" type="button" value="Cancelar" name="bCancelar" onclick="JavaScript:cancelar();"/>
                        </p>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>