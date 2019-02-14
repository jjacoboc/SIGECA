<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@page import="com.bmp.sigeca.util.StringUtil" %>

<%
    UsuarioBean usuarioBean = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):null;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>
</head>
<body>
<table width="750" align="center" cellPadding="0" cellSpacing="0" class="clsTabla2">
    <tr>
        <td width="170" align="center">
            <img src="<%=request.getContextPath()%>/imagenes/logoSBN.jpg" width="155" height="82" alt="Sistema Nacional Integrado de Información Catastral Predial"/>
        </td>
        <td height="82">
            <table width="100%" cellPadding="0" cellSpacing="0" >
                <tr height="60">
                    <td align="center" height="30"><span style="color:#004d9a;font-size:25px"><b>SISTEMA DE GESTIÓN CATASTRAL - SIGECA</b></span></td>
                </tr>
                <tr bgcolor="#004d9a" height="3">
                    <td></td>
                </tr>
            </table>
            <table width="100%" cellPadding="0" cellSpacing="0" height="32" border="0">
                <tr>
                    <td bgcolor="#004d9a" width="2">&nbsp;</td>
                    <%if(usuarioBean!=null){%>
                        <%String usuario = StringUtil.nullAsEmptyString(usuarioBean.getNombreCompleto());%>
                        <%String perfil = StringUtil.nullAsEmptyString(usuarioBean.getDesPefil());%>
                        <td bgcolor="#CCCCCC" align="center" width="20">
                            <img src="<%=request.getContextPath()%>/imagenes/icon_user.gif" width="14" height="16" alt="Usuario"/>
                        </td>
                        <td bgcolor="#CCCCCC" align="left" width="260">
                            <span style="color:#004d9a;font:bold;"><%=usuario%></span>
                        </td>
                        <td bgcolor="#CCCCCC" align="center" width="20">
                            <img src="<%=request.getContextPath()%>/imagenes/vcard.png" width="14" height="16" alt="Perfil de Usuario"/>
                        </td>
                        <td bgcolor="#CCCCCC" align="left" width="200">
                            <span style="color:#004d9a;font:bold;"><%=perfil%></span>
                        </td>
                    <%}else{%>
                        <td bgcolor="#CCCCCC" align="center" colspan="2">&nbsp;</td>
                    <%}%>
                    <td bgcolor="#CCCCCC" align="right">
                        <!--<span style="color:#004d9a;font:bold">Ayuda</span>&nbsp;-->
                    </td>
                    <td bgcolor="#CCCCCC" align="right" width="20">
                        <%--<img src="<%=request.getContextPath()%>/imagenes/Help.gif" width="16" height="16" alt="Ayuda"/>&nbsp;--%>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
