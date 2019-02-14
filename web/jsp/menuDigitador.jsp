<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.bmp.sigeca.resource.Properties" %>
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
    body { font: normal 62.5% verdana; }
    ul#primary-nav{
        width: 145px;
        margin: 0;
        padding: 0;
        background: #ccc; /* IE6 Bug */
        font-size: 100%;
    }
    ul#primary-nav ul {
        width: 170px;
        margin: 0;
        padding: 0;
        background: #ccc; /* IE6 Bug */
        font-size: 100%;
        text-align: left;
    }
    ul#primary-nav ul ul {
        width: 190px;
        margin: 0;
        padding: 0;
        background: #ccc; /* IE6 Bug */
        font-size: 100%;
        text-align: left;
    }
    ul#primary-nav {
        float: left;
        width: 740px;
    }
    ul#primary-nav:after {
        content: ".";
        display: block;
        height: 0;
        clear: both;
        visibility: hidden;
    }
    ul#primary-nav li {
        position: relative;
        list-style: none;
        float: left;
        width: 145px; /* Width of Menu Items */
    }
    ul#primary-nav li ul li{
        position: relative;
        list-style: none;
        float: left;
        width: 170px; /* Width of Sub Menu Items */
    }
    ul#primary-nav li ul li ul li{
        position: relative;
        list-style: none;
        float: left;
        width: 190px; /* Width of Sub Sub Menu Items */
    }
    ul#primary-nav li a,
    ul#primary-nav li li a {
        display: block;
        text-decoration: none;
        color: #777;
        padding: 5px;
        border: 1px solid #ccc;
    }
    /* Fix IE. Hide from IE Mac \*/
    * html ul#primary-nav li { float: left; height: 1%; }
    * html ul#primary-nav li a { height: 1%; }
    /* End */

    ul#primary-nav ul {
        position: absolute;
        display: none;
    }
    ul#primary-nav ul ul {
        left: 170px;
        top: 0;
    }
    ul#primary-nav ul ul ul {
        left: 170px;
        top: 0;
    }
    ul#primary-nav li ul li a { padding: 2px 5px; } /* Sub Menu Styles */

    ul#primary-nav li:hover ul ul,
    ul#primary-nav li:hover ul ul ul,
    ul#primary-nav li.over ul ul,
    ul#primary-nav li.over ul ul ul { display: none; } /* Hide sub-menus initially */

    ul#primary-nav li:hover ul,
    ul#primary-nav li li:hover ul,
    ul#primary-nav li li li:hover ul,
    ul#primary-nav li.over ul,
    ul#primary-nav li li.over ul,
    ul#primary-nav li li li.over ul { display: block; } /* The magic */

    ul#primary-nav li.menubar { background: transparent url(<%=request.getContextPath()%>/imagenes/arrow-down.png) right center no-repeat; }
    ul#primary-nav li li.menubar { background: transparent url(<%=request.getContextPath()%>/imagenes/arrow-right.png) right center no-repeat; }

    ul#primary-nav li:hover,
    ul#primary-nav li.over { background-color: #f9f9f9 !important; }

    ul#primary-nav li a:hover { color: #004d9a; }
</style>

</head>
<script type="text/javascript">
loadTopMenu=function IEHoverPseudo() {
    var navItems = document.getElementById("primary-nav").getElementsByTagName("li");
    for (var i=0; i<navItems.length; i++) {
        if(navItems[i].className == "menubar") {
            navItems[i].onmouseover=function() { this.className += " over"; }
            navItems[i].onmouseout=function() { this.className = "menubar"; }
        }
    }
}
function metodo(ficha,acc){
    f = document.forms[0];
    f.method.value = ficha;
    f.accion.value = acc;
    f.submit();
}
window.onload=loadTopMenu;
</script>
<body bgcolor="#004d9a">
<html:form action="menu-action" method="post" >
<input type="hidden" name="method">
<input type="hidden" name="accion">
<div id="topMenu">
<ul id="primary-nav" class="menuList">
    <li class="menubar">
        <a href="#" title="Usuario" style="width: 144px">Usuario</a>
        <ul>
            <%--
            <li>
                <a href="JavaScript:metodo('registrarUsuario','<%=Properties.Accion_RegistrarFicha%>')" title="Registrar Nuevo Usuario">Registrar Nuevo Usuario</a>
            </li>
            --%>
            <li class="last">
                <a href="JavaScript:metodo('cambiarClave','<%=Properties.Accion_RegistrarFicha%>')" title="Cambiar clave">Cambiar clave</a>
            </li>
	</ul>
    </li>
    <li class="menubar">
        <a href="#" title="Ficha Catastral" style="width: 144px">Ficha Catastral</a>
        <ul>
            <li class="menubar">
                <a href="#" title="Registro Normal de Fichas Catastrales">Registrar Fichas Catastrales</a>
                <ul>
                    <li><a href="JavaScript:metodo('cargarFichaIndividual','<%=Properties.Accion_RegistrarFicha%>')" title="Ficha Urbana Individual">Ficha Urbana Individual</a></li>
                    <li><a href="JavaScript:metodo('cargarFichaCotitularidad','<%=Properties.Accion_RegistrarFicha%>')" title="Ficha Urbana Cotitularidad">Ficha Urbana Cotitularidad</a></li>
                    <li><a href="JavaScript:metodo('cargarFichaBienComun','<%=Properties.Accion_RegistrarFicha%>')" title="Ficha Urbana Bienes Comunes">Ficha Urbana Bienes Comunes</a></li>
                    <li class="last"><a href="JavaScript:metodo('cargarFichaActividadEconomica','<%=Properties.Accion_RegistrarFicha%>')" title="Ficha Urbana Actividad Económica">Ficha Urbana Actividad Económica</a></li>
                    <%--
                    <li><a href="JavaScript:metodo('cargarFichaRural','<%=Properties.Accion_RegistrarFicha%>')" title="Ficha Rural">Ficha Rural</a></li>
                    <li class="menubar">
                        <a href="#" title="Ficha Bienes Culturales">Ficha Bienes Culturales</a>
                        <ul>
                            <li><a href="JavaScript:metodo('cargarFichaCulturalPrehispanico','<%=Properties.Accion_RegistrarFicha%>')" title="Monumento Arqueológico Prehispánico">Monumento Arqueológico Prehispánico</a></li>
                            <li class="last"><a href="JavaScript:metodo('cargarFichaCulturalColonial','<%=Properties.Accion_RegistrarFicha%>')" title="Monumento Histórico Colonial / Republicano">Monumento Histórico Colonial / Republicano</a></li>
                        </ul>
                    </li>
                    --%>
                </ul>
            </li>
            <li>
                <a href="JavaScript:metodo('irBusqueda','')" title="Búsqueda de Fichas Catastrales">Buscar Fichas Catastrales</a>
            </li>
            <%--
            <li class="menubar">
                <a href="JavaScript:metodo('consultarFichasCatastrales','')" title="Consulta Para Fichas Catastrales">Consultar Fichas Catastrales</a>
            </li>

            <li>
                <a href="#" title="Actualizar Fichas Catastrales">Actualizar Fichas Catastrales</a>
                <ul>
                    <li><a href="JavaScript:metodo('actualizarPropietario','<%=Properties.Accion_ActualizarPropietario%>')" title="Actualización de Propietarios">Actualización de Propietarios</a></li>
                    <li class="last"><a href="JavaScript:metodo('actualizarPropietario','<%=Properties.Accion_RectificarArea%>')" title="Rectificación de Área">Rectificación de Área</a></li>
                </ul>
            </li>
            <li>
                <a href="JavaScript:metodo('fusionarLote','<%=Properties.Accion_FusionarFicha%>')" title="Fusionar Fichas Catastrales">Fusionar Fichas Catastrales</a>
            </li>
            <li class="last">
                <a href="JavaScript:metodo('fraccionarLote','<%=Properties.Accion_FraccionarFicha%>')" title="Fraccionar Fichas Catastrales">Fraccionar Fichas Catastrales</a>
            </li>
            --%>
        </ul>
    </li>
    <%--
    <li class="menubar">
        <a href="#" title="Datos" style="width: 144px">Reportes</a>
        <ul>
            <li>
                <a href="JavaScript:metodo('reporteAdministrador','<%=Properties.Accion_Reportes%>')" title="Reporte Administrador">Reporte Administrador</a>
            </li>
            <li>
                <a href="JavaScript:metodo('reporteUsuario','<%=Properties.Accion_Reportes%>')" title="Reporte Usuario">Reporte Usuario</a>
            </li>
	</ul>
    </li>
    <li class="menubar">
        <a href="#" title="Mantenimiento" style="width: 144px">Mantenimiento</a>
        <ul>
            <li><a href="JavaScript:metodo('mantenimientoUsos','<%=Properties.Accion_Mantenimiento%>')" title="Usos">Usos</a></li>
            <li class="last"><a href="JavaScript:metodo('mantenimientoUsosBC','<%=Properties.Accion_Mantenimiento%>')" title="Usos BC">Usos BC</a></li>
            <li class="last"><a href="JavaScript:metodo('mantenimientoNotaria','<%=Properties.Accion_Mantenimiento%>')" title="Notarias">Notarias</a></li>
            <li class="last"><a href="JavaScript:metodo('mantenimientoVia','<%=Properties.Accion_Mantenimiento%>')" title="Vias">Vias</a></li>
            <li class="last"><a href="JavaScript:metodo('mantenimientoHabUrba','<%=Properties.Accion_Mantenimiento%>')" title="Habilitaciones Urbanas">Habilitaciones Urbanas</a></li>
            <li class="last"><a href="JavaScript:metodo('mantenimientoCodigoInstalaciones','<%=Properties.Accion_Mantenimiento%>')" title="Codigo Instalaciones">Codigo Instalaciones</a></li>
            <li class="last"><a href="JavaScript:metodo('mantenimientoUbiego','<%=Properties.Accion_Mantenimiento%>')" title="Ubigeo">Ubigeo</a></li>
            <li class="last"><a href="JavaScript:metodo('mantenimientoTablaCodigos','<%=Properties.Accion_Mantenimiento%>')" title="Tabla Codigos">Tabla Codigos</a></li>
	</ul>
    </li>
    
    <li class="menubar">
        <a href="#" title="Datos" style="width: 144px">Reportes</a>
        <ul>
            <li><a href="JavaScript:metodo('reporteLotes','<%=Properties.Accion_Reportes%>')" title="Reporte de Lotes y Unidades Catastrales">Lotes y Unidades Catastrales</a></li>
            <li class="last"><a href="JavaScript:metodo('reportePropietarios','<%=Properties.Accion_Reportes%>')" title="Reporte de Propietarios de Predios">Propietarios</a></li>
	</ul>
    </li>

    <li class="menubar">
	<a href="#" title="Permisos" style="width: 134px">Configuración</a>
        <ul>
            <li><a href="JavaScript:metodo('irRegistrar','<%=Properties.Accion_Reportes%>')" title="Mantenimiento de Tablas Maestras">Mantenimiento de Maestros</a></li>
	</ul>
    </li>
    --%>
    <li><a href="JavaScript:metodo('cerrarSesion')" title="Cerrar Sesión" style="width: 144px">Cerrar Sesión</a></li>
</ul>
</div>
<br><br>
</html:form>
</body>
</html:html>