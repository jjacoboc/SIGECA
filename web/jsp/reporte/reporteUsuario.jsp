<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.bmp.sigeca.resource.Properties" %>
<%@page import="org.apache.struts.action.*" %>
<%@page import="org.apache.struts.Globals" %>
<%@taglib uri="struts-bean.tld" prefix="bean" %>
<%@taglib uri="struts-html.tld" prefix="html" %>
<%@taglib uri="struts-logic.tld" prefix="logic" %>


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
        function reporteUsuario(value,numForm)
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
            <h2 align="center" class="heading">REPORTE USUARIO</h2>
            <div id="outer">
                <div class="demo">


                    <h4 class="expand open "><a style="display: block;" href="#"
                                                title="expand/collapse"  class="Titulo1">Por Ubicacion </a></h4>
                    <div style="display: block;" class="collapse">
                        <p>
                            <html:form action="reporteUsuario-action" method="post">
                                <input type="hidden" name="method"/>
                                <input type="hidden" name="accion"/>
                            <table width="95%"cellPadding="0" cellSpacing="10" align="center"  >
                                <tr >
                                    <td class="etiqueta">Sector :  </td>
                                    <td> <select  name="sector" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td colspan="2">

                                        <input type="hidden" name="tipoUbicacion"><br />
                                        <h4 class="expand"><a style="display: block;" href="#"
                                                              title="expand/collapse"  class="etiquetanegra">Ubicacion por numero </a></h4>
                                        <div style="display: none;" class="collapse">
                                            <p>

                                            <table width="100%" border="0">
                                                <tr>
                                                    <td>&nbsp;    </td>
                                                    <td class="etiqueta"> Urbanizacion : </td>
                                                    <td >    <input type="text" name="urbanizacion" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;    </td>
                                                    <td class="etiqueta"> Manzana :  </td>
                                                    <td> <input type="text" name="manzana" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;    </td>
                                                    <td class="etiqueta">Lote :   </td>
                                                    <td>  <input type="text" name="lote" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp; </td>
                                                    <td>&nbsp; </td>
                                                    <td>&nbsp; </td>
                                                </tr>
                                            </table>

                                            </p>
                                        </div>
                                        <h4 class="expand"><a style="display: block;" href="#"
                                                              title="expand/collapse"  class="etiquetanegra">Ubicacion por via </a></h4>
                                        <div style="display: none;" class="collapse">
                                            <p>
                                            <table width="100%" border="0">
                                                <tr>
                                                    <td>&nbsp;    </td>
                                                    <td class="etiqueta">  Tipo Via :   </td>
                                                    <td>     <input type="text" name="tipoVia" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;   </td>
                                                    <td class="etiqueta">   Nombre Via :  </td>
                                                    <td> <input type="text" name="nombreVia" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;     </td>
                                                    <td class="etiqueta"> Numero Calle:  </td>
                                                    <td>  <input type="text" name="numCalle" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp; </td>
                                                    <td>&nbsp; </td>
                                                    <td>&nbsp; </td>
                                                </tr>
                                            </table>
                                            </p>
                                        </div>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Estado de la ficha: </td>
                                    <td>  <select  name="activo" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td><input class="casilla" type="button" value="Generar" name="bBuscar" onclick="JavaScript:reporteUsuario('reportePorUbicacion',0);" /></td>
                                    <td>&nbsp; </td>
                                </tr>
                            </table>

                        </html:form>	</p>
                    </div>
                    <h4 class="expand "><a style="display: block;" href="#"
                                           title="expand/collapse"  class="Titulo1">Por Titulares / Area </a></h4>
                    <div style="display: none;" class="collapse">
                        <html:form action="reporteUsuario-action" method="post">
                            <input type="hidden" name="method"/>
                            <input type="hidden" name="accion"/>
                            <p>
                            <table width="95%"cellPadding="0" cellSpacing="10" align="center"  >
                                <tr >
                                    <td class="etiqueta">Apellido Paterno :  </td>
                                    <td>    <input type="text" name="apePaterno"/></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Apellido Materno :  </td>
                                    <td>    <input type="text" name="apeMaterno"/></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Nombres:  </td>
                                    <td>    <input type="text" name="nombre"/></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Area:  </td>
                                    <td>    <input type="text" name="area"/></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Estado de la ficha: </td>
                                    <td>  <select  name="activo" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td> <input class="casilla" type="button" value="Generar" name="bBuscar" onclick="JavaScript:reporteUsuario('reportePorTitulares',1);"/></td>
                                    <td>&nbsp; </td>
                                </tr>
                            </table>



                            </p></html:form>
                        </div>
                        <h4 class="expand "><a style="display: block;" href="#"
                                               title="expand/collapse"  class="Titulo1">Por Codigo </a></h4>
                        <div style="display: none;" class="collapse">
                        <html:form action="reporteUsuario-action" method="post">
                            <input type="hidden" name="method"/>
                            <input type="hidden" name="accion"/>
                            <p>


                            <table width="95%"cellPadding="0" cellSpacing="10" align="center"  >
                                <tr >
                                    <td class="etiqueta">Tipo Codigo:  </td>
                                    <td>    <select  name="tipoCodigo"></select></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Codigo :  </td>
                                    <td>    <input type="text" name="codDocumento"/></td>
                                </tr>

                                <td class="etiqueta">Estado de la ficha: </td>
                                <td>  <select  name="activo" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td> <input class="casilla" type="button" value="Generar" name="bBuscar" onclick="JavaScript:reporteUsuario('reportePorCodigo',2);"/></td>
                                    <td>&nbsp; </td>
                                </tr>
                            </table>

                            </p>    </html:form>
                        </div>
                        <h4 class="expand "><a style="display: block;" href="#"
                                               title="expand/collapse"  class="Titulo1">Por Documento </a></h4>
                        <div style="display: none;" class="collapse">
                        <html:form action="reporteUsuario-action" method="post">
                            <input type="hidden" name="method"/>
                            <input type="hidden" name="accion"/>
                            <p>

                            <table width="95%"cellPadding="0" cellSpacing="10" align="center"  >
                                <tr >
                                    <td class="etiqueta">Tipo Documento:  </td>
                                    <td>    <select  name="tipoDocumento"></select></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Numero :  </td>
                                    <td>    <input type="text" name="numDocumento"/></td>
                                </tr>

                                <td class="etiqueta">Estado de la ficha: </td>
                                <td>  <select  name="activo" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td>   <input class="casilla" type="button" value="Generar" name="bBuscar" onclick="JavaScript:reporteUsuario('reportePorDocumento',3);"/></td>
                                    <td>&nbsp; </td>
                                </tr>
                            </table></p>

                        </html:form>
                    </div>
                    <h4 class="expand "><a style="display: block;" href="#"
                                           title="expand/collapse"  class="Titulo1">Por Ubicacion y Descripcion Predio </a></h4>
                    <div style="display: none;" class="collapse">
                        <html:form action="reporteUsuario-action" method="post">

                            <input type="hidden" name="method"/>
                            <input type="hidden" name="accion"/>
                            <p>


                            <table width="95%"cellPadding="0" cellSpacing="10" align="center"  >
                                <tr >
                                    <td class="etiqueta">Sector :  </td>
                                    <td> <select  name="sector" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td colspan="2">

                                        <input type="hidden" name="tipoUbicacion"><br />
                                        <h4 class="expand"><a style="display: block;" href="#"
                                                              title="expand/collapse"  class="etiquetanegra">Ubicacion por numero </a></h4>
                                        <div style="display: none;" class="collapse">
                                            <p>

                                            <table width="100%" border="0">
                                                <tr>
                                                    <td>&nbsp;    </td>
                                                    <td class="etiqueta"> Urbanizacion : </td>
                                                    <td >    <input type="text" name="urbanizacion" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;    </td>
                                                    <td class="etiqueta"> Manzana :  </td>
                                                    <td> <input type="text" name="manzana" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;    </td>
                                                    <td class="etiqueta">Lote :   </td>
                                                    <td>  <input type="text" name="lote" class="casilla"></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp; </td>
                                                    <td>&nbsp; </td>
                                                    <td>&nbsp; </td>
                                                </tr>
                                            </table>
                                            </p>
                                        </div>
                                        <h4 class="expand"><a style="display: block;" href="#"
                                                              title="expand/collapse"  class="etiquetanegra">Ubicacion por via </a></h4>
                                        <div style="display: none;" class="collapse">
                                            <p>
                                            <table width="100%" border="0">
                                                <tr>
                                                    <td>&nbsp;    </td>
                                                    <td class="etiqueta">  Tipo Via :   </td>
                                                    <td>     <input type="text" name="tipoVia" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;   </td>
                                                    <td class="etiqueta">   Nombre Via :  </td>
                                                    <td> <input type="text" name="nombreVia" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;     </td>
                                                    <td class="etiqueta"> Numero Calle:  </td>
                                                    <td>  <input type="text" name="numCalle" class="casilla"/></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp; </td>
                                                    <td>&nbsp; </td>
                                                    <td>&nbsp; </td>
                                                </tr>
                                            </table>
                                            </p>
                                        </div>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Clasificacion del Predio: </td>
                                    <td>  <select  name="clasificacionPredio" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Otros: </td>
                                    <td> <select  name="otrosPredio" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Codigo de Uso: </td>
                                    <td>  <input type="text" name="codUso" class="casilla"/></td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">Estado de la ficha: </td>
                                    <td>  <select  name="activo" class="casilla"></select></td>
                                </tr>
                                <tr>
                                    <td> <input class="casilla" type="button" value="Generar" name="bBuscar" onclick="JavaScript:reporteUsuario('reportePorUbicacionPredio',4);"/></td>
                                    <td>&nbsp; </td>
                                </tr>
                            </table>

                            </p>
                        </html:form>       </div>

                </div>

            </div>
            <!-- end #outer -->
        </div>
    </div>

</body>
</html:html>

