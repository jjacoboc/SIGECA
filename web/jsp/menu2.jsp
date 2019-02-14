<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
    a {text-decoration: none;}
    a:link {color: #080;}
    a:visited {color: #790;}
    a:active {color: red;}
    a:hover {text-decoration: underline;}
    .divlink a {
    font-size: 12px;
    display: block;
    width: 142px;
    background: url(<%=request.getContextPath()%>/imagenes/navbg.gif) repeat;
    }
    .divlink a:hover {
    width: 142px;
    font-size: 12px;
    background: url(<%=request.getContextPath()%>/imagenes/navhoover.gif) repeat;
    }
    ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    #nav a {
        font-weight: bold;
        color: black;
    }
    #nav a {
        font-size: 12px;
        text-decoration: none;
    }
    #nav li li a {
        font-size: 10px;
        display: block;
        font-weight: bold;
        color: black;
        padding: 0.2em 10px;
    }
    #nav li li a:hover {
        font-size: 10px;
        width: 180px;
        background: url(<%=request.getContextPath()%>/imagenes/navhoover.gif) repeat;
    }
    #navChild a {
        font-weight: bold;
        color: black;
    }
    #navChild a {
        font-size: 12px;
        text-decoration: none;
    }
    #navChild li li a {
        font-size: 10px;
        display: block;
        font-weight: bold;
        color: black;
        padding: 0.2em 10px;
    }
    #navChild li li a:hover {
        font-size: 10px;
        width: 180px;
        background: url(<%=request.getContextPath()%>/imagenes/navhoover.gif) repeat;
    }
    li {
        font-size: 12px;
        float: left;
        position: relative;
        width: 142px;
        text-align: left;
        cursor: default;
        background-color: white;
        border: 1px solid #004d9a;
    }
    li#first {border-left-width: 1em;}
    li#last {border-right-width: 1em;}
    li ul {
        width: 200px;
        font-size: 12px;
        display: none;
        position: absolute;
        top: 100%;
        left: 0;
        font-weight: normal;
        background-color: white;
        border-right: solid 1px #666666;
        border-left: solid 1px #666666;
        border-bottom: solid 1px #666666;
    }
    li>ul {
        width: 200px;
        top: auto;
        left: auto;
    }
    li li {
        width: 200px;
        display: block;
        float: none;
        background-color: transparent;
        border: 0;
    }
    li:hover ul, li.over ul {
        width: 200px;
        display: block;
    }
</style>
</head>
<script type="text/javascript">
loadTopMenu = function(){
    if (document.all&&document.getElementById){
        menunavParent = document.getElementById("nav");
        for (x=0; x < menunavParent.childNodes.length; x++){
            menunode = menunavParent.childNodes[x];
            if (menunode.nodeName=="LI"){
                menunode.onmouseover=function(){
                    this.className+=" over";
                }
                menunode.onmouseout=function(){
                    this.className=this.className.replace(" over", "");
                }
            }
        }
        menunavChild = document.getElementById("navChild");
        for (x=0; x < menunavChild.childNodes.length; x++){
            menunode = menunavChild.childNodes[x];
            if (menunode.nodeName=="LI"){
                menunode.onmouseover=function(){
                    this.className+=" over";
                }
                menunode.onmouseout=function(){
                    this.className=this.className.replace(" over", "");
                }
            }
        }
    }
}

function metodo(ficha){
    f = document.forms[0];
    f.method.value = ficha;
    f.submit();
}
window.onload=loadTopMenu;
</script>
<body bgcolor="#004d9a">
<html:form action="menu-action" method="post" >
<input type="hidden" name="method">
<div>
<ul id="nav">
    <li id="first">
		<div class="divlink"><a href="#">&nbsp;Inicio</a></div>
	</li>
    <li>
		<div class="divlink"><a href="#">&nbsp;Registro Catastral</a></div>
		<ul>
			<li><a href="JavaScript:metodo('cargarFichaIndividual')">Ficha Urbana Individual</a></li>
			<li><a href="JavaScript:metodo('cargarFichaCotitularidad')">Ficha Urbana Cotitularidad</a></li>
			<li><a href="JavaScript:metodo('cargarFichaBienComun')">Ficha Urbana Bienes Comunes</a></li>
			<li><a href="JavaScript:metodo('cargarFichaActividadEconomica')">Ficha Urbana Actividad Económica</a></li>
			<li><a href="JavaScript:metodo('cargarFichaRural')">Ficha Rural</a></li>
            <li>
                <a href="#">Ficha Bienes Culturales</a>
                <ul id="navChild">
                    <li><a href="JavaScript:metodo('cargarFichaCulturalPrehispanico')">Monumento Arqueológico Prehispánico</a></li>
                    <li><a href="JavaScript:metodo('cargarFichaCulturalColonial')">Monumento Histórico Colonial / Republicano</a></li>
                </ul>
            </li>
		</ul>
	</li>
    <li>
		<div class="divlink"><a href="#">&nbsp;Datos</a></div>
		<ul>
			<li><a href="#">Exportar Datos</a></li>
			<li><a href="#">Importar Datos</a></li>
		</ul>
	</li>
    <li>
		<div class="divlink"><a href="#">&nbsp;Permisos</a></div>
	</li>
	<li id="last">
		<div class="divlink"><a href="JavaScript:metodo('cerrarSesion')">&nbsp;Cerrar Sesión</a></div>
	</li>
</ul>
</div>
</html:form>
</body>
</html:html>