<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.bmp.sigeca.registro.bean.UsuarioBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    UsuarioBean usuario = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):new UsuarioBean();
%>
<html:html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body bgcolor="#004d9a">
    <%if(Properties.Perfil_Administrador.equals(usuario.getCodPefil())){%>
        <%@include file="menuAdministrador.jsp" %>
    <%}else{%>
        <%@include file="menuDigitador.jsp" %>
    <%}%>
</body>
</html:html>