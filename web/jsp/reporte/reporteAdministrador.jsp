<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="com.bmp.sigeca.maestro.bean.TablasCodigosBean" %>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>

<%
    List listaEstados = session.getAttribute("listaEstados")!=null?(List)session.getAttribute("listaEstados"): new ArrayList();
%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <style type="text/css">
            body { font: normal 62.5% verdana; }
            ul#primary-nav{
                width: 185px;
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
                width: 185px; /* Width of Menu Items */
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
                left: 190px;
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

        <style type="text/css">
            .demo{
                clear:both;
                position:relative;
                margin:0 auto 0 auto;
                padding:1.5em 1.5em .75em;
                overflow:hidden;
                width: 750px;
            }

            .js .demo{visibility:hidden}

            .expand{clear:both; margin:0; padding-bottom:.75em}

            .collapse {
                margin-bottom:0;
                border:none;
                overflow:hidden;
            }
            .collapse p {
                margin:0 4px 1em  ;
            }
            #wrapper .expand a {
                display:block;
                padding:3px
            }
            #wrapper .expand a:link, #wrapper .expand a:visited {
                display:block;
                border-width:1px;
                border:none;
                background-image:url(../img/arrow-down.gif);
                background-repeat:no-repeat;
                background-position:98% 50%;
            }
            #wrapper .expand a:hover, #wrapper .expand a:active, #wrapper .expand a:focus {
                outline-color:#dedede;
            }
            #wrapper .expand.open a:link, #wrapper .expand.open a:visited {
                border-style:none;
            }
        </style>
        <!--[if IE]>
        <style type='text/css'>
        .demo a, .demo {position:relative; height:1%}
        .demo {margin-top:0}
        </style>
        <![endif]-->
        <!--[if !lt IE 6]><!-->
        <!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script> -->
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/accordion3.js"></script>
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
        <script type="text/javascript">
            <!--//--><![CDATA[//><!--
            $("html").addClass("js");
            $(function() {
                $("html").removeClass("js");
            });
            //--><!]]>
        </script>

    </head>
    <script type="text/javascript">
        function reporteAdministrador(value,numForm)
        {
            f = document.forms[numForm];
            f.method.value = value;
            f.submit();
        }
    </script>
</head>
<body id="accordion-h-div" class="jquery">
    <div id="wrapper">

        <div id="content">
            <!-- // -->
            <h2 align="center" class="heading">REPORTE ADMINISTRADOR</h2>
            <div id="outer">
                <div class="demo">
                    <h4 class="expand "><a style="display: block;" href="#" title="expand/collapse"  class="Titulo1">Primer Reporte Detallado </a></h4>
                    <div style="display: none;" class="collapse">
                        <html:form action="reporteAdministrador-action" method="post">
                            <p>
                                <input type="hidden" name="method"/>
                                <input type="hidden" name="accion"/>
                                <table width="95%" cellPadding="0" cellSpacing="10" align="center">
                                    <tr>
                                        <td class="etiqueta">Digitador:</td>
                                        <td><select name="digitador"></select></td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta">Fecha Inicio:</td>
                                        <td><input type="text" name="fecInicio"/></td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta">Fecha Fin:</td>
                                        <td><input type="text" name="fecFin"/></td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta">Estado:</td>
                                        <td>
                                            <select class="clsComboDoc" name="estado">
                                                <option value="">Seleccione</option>
                                                <%for(int i=0;i<listaEstados.size();i++){%>
                                                    <%String codEstado = ((TablasCodigosBean)listaEstados.get(i)).getCodigo();%>
                                                    <%String descripcion = ((TablasCodigosBean)listaEstados.get(i)).getDesc_codigo();%>
                                                    <option value="<%=codEstado%>">
                                                        <%=descripcion%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input class="casilla" type="button" value="Generar" name="bBuscar" onclick="JavaScript:reporteAdministrador('reportePorCodigo',0);"/></td>
                                        <td>&nbsp; </td>
                                    </tr>
                                </table>
                            </p>
                        </html:form>
                    </div>
                    <h4 class="expand "><a style="display: block;" href="#" title="expand/collapse"  class="Titulo1">Segundo Reporte Detallado </a></h4>
                    <div style="display: none;" class="collapse">
                        <html:form action="reporteAdministrador-action" method="post">
                            <p>
                                <input type="hidden" name="method">
                                <input type="hidden" name="accion">
                                <table width="95%"cellPadding="0" cellSpacing="10" align="center"  >

                                    <tr>
                                        <td class="etiqueta">Fecha Inicio :  </td>
                                        <td>   <input type="text" name="fecInicio"/></td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta">Fecha Fin: </td>
                                        <td>  <input type="text" name="fecFin"/></td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta">Estado: </td>
                                        <td>
                                            <select class="clsComboDoc" name="estado">
                                                <option value="">Seleccione</option>
                                                <%for(int i=0;i<listaEstados.size();i++){%>
                                                    <%String codEstado = ((TablasCodigosBean)listaEstados.get(i)).getCodigo();%>
                                                    <%String descripcion = ((TablasCodigosBean)listaEstados.get(i)).getDesc_codigo();%>
                                                    <option value="<%=codEstado%>">
                                                        <%=descripcion%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td> <input class="casilla" type="button" value="Generar" name="bBuscar" onclick="JavaScript:reporteAdministrador('reportePorCodigo',1);"/></td>
                                        <td>&nbsp; </td>
                                    </tr>
                                </table>
                            </p>
                        </html:form>
                    </div>

                    <h4 class="expand "><a style="display: block;" href="#"  title="expand/collapse"  class="Titulo1">Reporte General </a></h4>
                    <div style="display: none;" class="collapse">
                        <html:form action="reporteAdministrador-action" method="post">
                            <p>
                                <input type="hidden" name="method">
                                <input type="hidden" name="accion">
                                <table width="95%"cellPadding="0" cellSpacing="10" align="center"  >
                                    <tr >
                                        <td class="etiqueta">Digitador:  </td>
                                        <td>      <select name="digitador"></select></td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta">Fecha Inicio :  </td>
                                        <td>   <input type="text" name="fecInicio"/></td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta">Fecha Fin: </td>
                                        <td>  <input type="text" name="fecFin"/></td>
                                    </tr>
                                    <tr>
                                        <td class="etiqueta">Estado: </td>
                                        <td>
                                            <select class="clsComboDoc" name="estado">
                                                <option value="">Seleccione</option>
                                                <%for(int i=0;i<listaEstados.size();i++){%>
                                                    <%String codEstado = ((TablasCodigosBean)listaEstados.get(i)).getCodigo();%>
                                                    <%String descripcion = ((TablasCodigosBean)listaEstados.get(i)).getDesc_codigo();%>
                                                    <option value="<%=codEstado%>">
                                                        <%=descripcion%>
                                                    </option>
                                                <%}%>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> <input class="casilla" type="button" value="Generar" name="bBuscar" onclick="JavaScript:reporteAdministrador('reportePorCodigo',2);"/></td>
                                        <td>&nbsp; </td>
                                    </tr>
                                </table>
                            </p>
                        </html:form>
                    </div>
                </div>
            </div>
            <!-- end #outer -->
        </div>
    </div>

</body>
</html:html>