/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.resource;

import com.bmp.sigeca.maestro.bean.EstElementoBean;
import com.bmp.sigeca.maestro.service.AfeAntropoficaService;
import com.bmp.sigeca.maestro.service.AfeNaturalService;
import com.bmp.sigeca.maestro.service.CatInmuebleService;
import com.bmp.sigeca.maestro.service.ClaUsoActualService;
import com.bmp.sigeca.maestro.service.ConDeclaranteService;
import com.bmp.sigeca.maestro.service.ConEspTitularService;
import com.bmp.sigeca.maestro.service.ConInstalacionService;
import com.bmp.sigeca.maestro.service.ConTitularService;
import com.bmp.sigeca.maestro.service.EstCivilService;
import com.bmp.sigeca.maestro.service.EstLleFichaService;
import com.bmp.sigeca.maestro.service.FilCronologicaService;
import com.bmp.sigeca.maestro.service.FilEstilisticaService;
import com.bmp.sigeca.maestro.service.ForAdquisicionService;
import com.bmp.sigeca.maestro.service.IntConservacionService;
import com.bmp.sigeca.maestro.service.IntInmuebleService;
import com.bmp.sigeca.maestro.service.NotariaService;
import com.bmp.sigeca.maestro.service.PerJuridicaService;
import com.bmp.sigeca.maestro.service.RiegoService;
import com.bmp.sigeca.maestro.service.TablasCodigosService;
import com.bmp.sigeca.maestro.service.TipArqColonialService;
import com.bmp.sigeca.maestro.service.TipArquitecturaService;
import com.bmp.sigeca.maestro.service.TipDocIdentidadService;
import com.bmp.sigeca.maestro.service.TipMatConstructivoService;
import com.bmp.sigeca.maestro.service.TipParRegistralService;
import com.bmp.sigeca.maestro.service.TipTitularService;
import com.bmp.sigeca.maestro.service.UbigeoDistritalService;
import com.bmp.sigeca.maestro.service.UbigeoService;
import com.bmp.sigeca.maestro.service.UsoBCService;
import com.bmp.sigeca.maestro.service.UsoPreRuralService;
import com.bmp.sigeca.maestro.service.UsoService;
import com.bmp.sigeca.mantenimiento.service.HabilitacionUrbanaService;
import com.bmp.sigeca.mantenimiento.service.ViaService;
import com.bmp.sigeca.registro.service.TrabajadoresService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author jjacobo
 */
public class Properties {

    /* Ubigeo Municipal Catastral */
    public final static String Departamento = "15";
    public final static String Provincia = "01";
    public final static String Distrito = "13";

    /* Perfil de Usuario */
    public final static String Perfil_Administrador = "01";
    public final static String Perfil_Supervisor = "02";

    /* Acciones de menu */
    public final static String Accion_RegistrarFicha = "01";
    public final static String Accion_ActualizarPropietario = "02";
    public final static String Accion_RectificarArea = "03";
    public final static String Accion_FusionarFicha = "04";
    public final static String Accion_FraccionarFicha = "05";
    public final static String Accion_Reportes = "06";
    public final static String Accion_Mantenimiento = "07";

    /* Tipos de Ficha */
    public final static String FichaCatastralUrbanaIndividual = "01";
    public final static String FichaCatastralUrbanaCotitularidad = "02";
    public final static String FichaCatastralUrbanaActividadEconomica = "03";
    public final static String FichaCatastralUrbanaBienesComunes = "04";
    public final static String FichaCatastralRural = "05";
    public final static String FichaCatastralBienesCulturales_MonumentoPrehispanico = "06";
    public final static String FichaCatastralBienesCulturales_MonumentoColonial = "07";

    /* Estados de Ficha */
    public final static String EstadoRegistrado = "01";
    public final static String EstadoRevisado = "02";

    /* Ficha Catastral Activa o Desactiva */
    public final static String Activa = "1";
    public final static String Desactiva = "2";

    /* Tipos de Titular Catastral */
    public final static String TipoTitular_PersonaNatural = "1";
    public final static String TipoTitular_PersonaJuridica = "2";

    /* Tipos de Persona Juridica */
    public final static String TipoPersonaJuridica_Empresa = "01";
    public final static String TipoPersonaJuridica_Cooperativa = "02";
    public final static String TipoPersonaJuridica_Asociacion = "03";
    public final static String TipoPersonaJuridica_Fundacion = "04";
    public final static String TipoPersonaJuridica_Otros = "05";

    /* Tipos de Documento de Identidad */
    public final static String TipoDoc_NoPresento = "01";
    public final static String TipoDoc_DNI = "02";
    public final static String TipoDoc_CarnetPoliciaNacional = "03";
    public final static String TipoDoc_CarnetFuerzasArmadas = "04";
    public final static String TipoDoc_PartidaNacimiento = "05";
    public final static String TipoDoc_Pasaporte = "06";
    public final static String TipoDoc_CarnetExtranjeria = "07";
    public final static String TipoDoc_Otros = "08";

    /* Cargos del Trabajador */
    public final static String Cargo_Tecnico = "01";
    public final static String Cargo_Supervisor = "02";
    public final static String Cargo_Verificador = "03";
    public final static String Cargo_Digitador = "04";

    /* Codigos Maestro Tablas TABLAS */
    public final static String TIPO_ANUNCIO = "ANU";
    public final static String AREAS = "ARE";
    public final static String CONDICION_CONDUCTOR = "CDC";
    public final static String CONDICION_DECLARANTE = "CDE";
    public final static String CLASIFICACION_PREDIO = "CDP";
    public final static String CONDICION_ESPECIAL_TITULAR_FORMALIZACION = "CEF";
    public final static String CONDICION_ESPECIAL_PREDIO = "CEP";
    public final static String CONDICION_NUMERACION_PUERTA = "CNP";
    public final static String CONDICION_TITULAR_FORMALIZACION = "CTF";
    public final static String CONDICION_TITULAR = "CTT";
    public final static String DOCUMENTOS_FORMALIZACION = "DCF";
    public final static String DECLARATORIA_FABRICA = "DFB";
    public final static String DOCUMENTOS_PRESENTADOS = "DFE";
    public final static String TIPO_DOCUMENTO = "DOC";
    public final static String ESTADO_CONSTRUCCION = "ECC";
    public final static String ESTADO_CONSERVACION = "ECS";
    public final static String ESTADO_CIVIL = "ECV";
    public final static String EVALUACION_PREDIO = "EPC";
    public final static String FORMA_ADQUISICION = "FAQ";
    public final static String TIPO_FICHA = "FCH";
    public final static String TIPO_HABILITACION_HURBANA = "HUR";
    public final static String VALOR_LOGICO = "LOG";
    public final static String ESTADO_LLENADO_FICHA = "LLE";
    public final static String MATERIAL_ESTRUCTURAL_PREDOMINANTE = "MEP";
    public final static String MANTENIMIENTO_FICHA_BIENES_COMUNES = "MFC";
    public final static String MANTENIMIENTO_FICHA_ECONOMICA = "MFE";
    public final static String MANTENIMIENTO_FICHA_INDIVIDUAL = "MFI";
    public final static String PREDIO_EN = "PEN";
    public final static String RUTAS = "RUT";
    public final static String TIPO_DOCUMENTO_CONSTRUCCION = "TDC";
    public final static String TIPO_EDIFICACION = "TED";
    public final static String TIPO_INTERIOR_PREDIO = "TIN";
    public final static String TIPO_INTERIOR_PREDIO2 = "TIF";
    public final static String TIPO_PARTIDA_REGISTRAL = "TPA";
    public final static String CARGO_TRABAJADOR_PROYECTO_CATASTRAL = "TPC";
    public final static String TIPO_PERSONA = "TPE";
    public final static String TIPO_PERSONA_JURIDICA = "TPJ";
    public final static String TIPO_PUERTA = "TPR";
    public final static String UBICACION_CONTRUCCION_ANTIRREGLAMENTARIA = "UCA";
    public final static String TIPO_VIA = "VIA";
    public final static String ESTADO_FICHA = "ECH";

    /* Número Zero=0; One=1 */
    public final static String ZERO = "0";
    public final static String ONE = "1";

    /* Opciones SI/NO */
    public final static String Si = "1";
    public final static String No = "2";

    /* Opciones ON/OFF */
    public final static String On = "on";
    public final static String Off = "off";

    /* Paginados en grillas */
    public final static String PaginadoReportes = "10";
    public final static String PaginadoMantenimientos = "5";
    public final static String PaginadoAdministracion = "5";
    public final static String PaginadoEvaluacion = "5";
    public final static String PaginadoEvaluacionSnip = "5";
    public final static String PaginadoMantFormulario = "5";

    /* Cantidad sugerida de expedientes para generear un lote */
    public final static String CantidadExpedientesLote = "5";
    
    public static String getParametros(){
        return "com.bmp.sigeca.resource.parametros";
    }
    
    public static String getDBProperties(){
        return "com.bmp.sigeca.resource.DBProperties";
    }
    
    public static String getMailProperties(){
        return "com.bmp.sigeca.resource.MailProperties";
    }

    public static List getListaEstadoElementos(){
        List lista = new ArrayList();
        EstElementoBean estElemento = new EstElementoBean();
        estElemento.setCodEstElemento("B");
        estElemento.setDescripcion("BUENO");
        lista.add(estElemento);
        estElemento = new EstElementoBean();
        estElemento.setCodEstElemento("R");
        estElemento.setDescripcion("REGULAR");
        lista.add(estElemento);
        estElemento = new EstElementoBean();
        estElemento.setCodEstElemento("M");
        estElemento.setDescripcion("MALO");
        lista.add(estElemento);
        return lista;
    }

    public static String concatLeftCharacter(String character, int finalSize, String string){
        String newString = null;
        try{
            if(string!=null && !"".equals(string)){
                newString = string;
                while(newString.length()<finalSize){
                    newString = character.concat(newString);
                }
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return newString;
    }


    public static void cargarListasFichaIndividual(HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTipInterior = null;
        List listaTipEdificacion = null;
        List listaTipTitular = null;
        List listaEstCivil = null;
        List listaTipDocIdentidad = null;
        List listaPerJuridica = null;
        List listaConEspTitular = null;
        List listaDepartamento = null;
        List listaTipVia = null;
        List listaConTitular = null;
        List listaForAdquisicion = null;
        List listaConEspPredio = null;
        List listaClaPredio = null;
        List listaSubClaPredio = null;
        List listaUbiPreCatastral = null;
        List listaTipParRegistral = null;
        List listaDecFabrica = null;
        List listaEvaPreCatastral = null;
        List listaConDeclarante = null;
        List listaEstLleFicha = null;
        List listaMantenimiento = null;
        List listaTipPuerta = null;
        List listaConNumeracion = null;
        List listaMEP = null;
        List listaECC = null;
        List listaECS = null;
        List listaUCA = null;
        List listaTipDocumento = null;
        List listaSupervisor = null;
        List listaVerificador = null;
        List listaTecnico = null;
        List listaUso = null;
        List listaNotaria = null;
        List listaVias = null;
        List listaHabUrbanas = null;
        HashMap mapListas = new HashMap();
        try{
            HttpSession session = request.getSession();

            TablasCodigosService tablasCodigos = new TablasCodigosService();
            listaTipInterior = tablasCodigos.getListaMaestro(TIPO_INTERIOR_PREDIO);
            listaTipEdificacion = tablasCodigos.getListaMaestro(TIPO_EDIFICACION);
            listaTipTitular = tablasCodigos.getListaMaestro(TIPO_PERSONA);
            listaEstCivil = tablasCodigos.getListaMaestro(ESTADO_CIVIL);
            listaTipDocIdentidad = tablasCodigos.getListaMaestro(TIPO_DOCUMENTO);
            listaPerJuridica = tablasCodigos.getListaMaestro(TIPO_PERSONA_JURIDICA);
            listaConEspTitular = tablasCodigos.getListaMaestro(CONDICION_ESPECIAL_TITULAR_FORMALIZACION);
            listaTipVia = tablasCodigos.getListaMaestro(TIPO_VIA);
            listaConTitular = tablasCodigos.getListaMaestro(CONDICION_TITULAR);
            listaForAdquisicion = tablasCodigos.getListaMaestro(FORMA_ADQUISICION);
            listaConEspPredio = tablasCodigos.getListaMaestro(CONDICION_ESPECIAL_PREDIO);
            listaClaPredio = tablasCodigos.getListaMaestro(CLASIFICACION_PREDIO);
            listaUbiPreCatastral = tablasCodigos.getListaMaestro(PREDIO_EN);
            listaTipParRegistral = tablasCodigos.getListaMaestro(TIPO_PARTIDA_REGISTRAL);
            listaDecFabrica = tablasCodigos.getListaMaestro(DECLARATORIA_FABRICA);
            listaEvaPreCatastral = tablasCodigos.getListaMaestro(EVALUACION_PREDIO);
            listaConDeclarante = tablasCodigos.getListaMaestro(CONDICION_DECLARANTE);
            listaEstLleFicha = tablasCodigos.getListaMaestro(ESTADO_LLENADO_FICHA);
            listaMantenimiento = tablasCodigos.getListaMaestro(MANTENIMIENTO_FICHA_INDIVIDUAL);
            listaTipPuerta = tablasCodigos.getListaMaestro(TIPO_PUERTA);
            listaConNumeracion = tablasCodigos.getListaMaestro(CONDICION_NUMERACION_PUERTA);
            listaMEP = tablasCodigos.getListaMaestro(MATERIAL_ESTRUCTURAL_PREDOMINANTE);
            listaECC = tablasCodigos.getListaMaestro(ESTADO_CONSTRUCCION);
            listaECS = tablasCodigos.getListaMaestro(ESTADO_CONSERVACION);
            listaUCA = tablasCodigos.getListaMaestro(UBICACION_CONTRUCCION_ANTIRREGLAMENTARIA);
            listaTipDocumento = tablasCodigos.getListaMaestro(TIPO_DOCUMENTO_CONSTRUCCION);

            HashMap criteria = new HashMap();
            criteria.put("id_cargo", Cargo_Verificador);
            TrabajadoresService trabajadores = new TrabajadoresService();
            listaVerificador = trabajadores.getListTrabajadoresPorCargo(criteria);
            criteria.put("id_cargo", Cargo_Supervisor);
            listaSupervisor = trabajadores.getListTrabajadoresPorCargo(criteria);
            criteria.put("id_cargo", Cargo_Tecnico);
            listaTecnico = trabajadores.getListTrabajadoresPorCargo(criteria);

            UbigeoService ubigeo = new UbigeoService();
            listaDepartamento = ubigeo.getListDepartamento();

            UsoService uso = new UsoService();
            listaUso = uso.getListUsos();

            NotariaService notaria = new NotariaService();
            listaNotaria = notaria.getListNotarias();

            HashMap mapFicha = (HashMap)session.getAttribute("mapFicha");
            HashMap mapUbigeo = (HashMap)mapFicha.get("mapUbigeo");
            ViaService via = new ViaService();
            listaVias = via.obtenerViasByUbigeo(mapUbigeo);

            HabilitacionUrbanaService HabUrbaService = new HabilitacionUrbanaService();
            listaHabUrbanas = HabUrbaService.obtenerHabilitacionesUrbanasByUbigeo(mapUbigeo);
            
            mapListas.put("listaTipInterior", listaTipInterior);
            mapListas.put("listaTipEdificacion", listaTipEdificacion);
            mapListas.put("listaTipTitular", listaTipTitular);
            mapListas.put("listaEstCivil", listaEstCivil);
            mapListas.put("listaTipDocIdentidad", listaTipDocIdentidad);
            mapListas.put("listaPerJuridica", listaPerJuridica);
            mapListas.put("listaConEspTitular", listaConEspTitular);
            mapListas.put("listaDepartamento", listaDepartamento);
            mapListas.put("listaTipVia", listaTipVia);
            mapListas.put("listaConTitular", listaConTitular);
            mapListas.put("listaForAdquisicion", listaForAdquisicion);
            mapListas.put("listaConEspPredio", listaConEspPredio);
            mapListas.put("listaClaPredio", listaClaPredio);
            mapListas.put("listaSubClaPredio", listaSubClaPredio);
            mapListas.put("listaUbiPreCatastral", listaUbiPreCatastral);
            mapListas.put("listaTipParRegistral", listaTipParRegistral);
            mapListas.put("listaDecFabrica", listaDecFabrica);
            mapListas.put("listaEvaPreCatastral", listaEvaPreCatastral);
            mapListas.put("listaConDeclarante", listaConDeclarante);
            mapListas.put("listaEstLleFicha", listaEstLleFicha);
            mapListas.put("listaMantenimiento", listaMantenimiento);
            mapListas.put("listaTipPuerta", listaTipPuerta);
            mapListas.put("listaConNumeracion", listaConNumeracion);
            mapListas.put("listaMEP", listaMEP);
            mapListas.put("listaECC", listaECC);
            mapListas.put("listaECS", listaECS);
            mapListas.put("listaUCA", listaUCA);
            mapListas.put("listaTipDocumento", listaTipDocumento);
            mapListas.put("listaVerificador", listaVerificador);
            mapListas.put("listaSupervisor", listaSupervisor);
            mapListas.put("listaTecnico", listaTecnico);
            mapListas.put("listaUso", listaUso);
            mapListas.put("listaNotaria", listaNotaria);
            mapListas.put("listaVias", listaVias);
            mapListas.put("listaHabUrbanas", listaHabUrbanas);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void cargarListasFichaCotitular(HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTipTitular = null;
        List listaTipDocIdentidad = null;
        List listaConEspTitular = null;
        List listaDepartamento = null;
        List listaForAdquisicion = null;
        List listaConDeclarante = null;
        List listaEstLleFicha = null;
        List listaTipVia = null;
        List listaSupervisor = null;
        List listaVerificador = null;
        List listaTecnico = null;
        HashMap mapListas = new HashMap();
        try{
            HttpSession session = request.getSession();

            TablasCodigosService tablasCodigos = new TablasCodigosService();
            listaTipTitular = tablasCodigos.getListaMaestro(TIPO_PERSONA);
            listaTipDocIdentidad = tablasCodigos.getListaMaestro(TIPO_DOCUMENTO);
            listaConEspTitular = tablasCodigos.getListaMaestro(CONDICION_ESPECIAL_TITULAR_FORMALIZACION);
            listaForAdquisicion = tablasCodigos.getListaMaestro(FORMA_ADQUISICION);
            listaConDeclarante = tablasCodigos.getListaMaestro(CONDICION_DECLARANTE);
            listaEstLleFicha = tablasCodigos.getListaMaestro(ESTADO_LLENADO_FICHA);
            listaTipVia = tablasCodigos.getListaMaestro(TIPO_VIA);

            UbigeoService ubigeo = new UbigeoService();
            listaDepartamento = ubigeo.getListDepartamento();

            HashMap criteria = new HashMap();
            criteria.put("id_cargo", Cargo_Verificador);
            TrabajadoresService trabajadores = new TrabajadoresService();
            listaVerificador = trabajadores.getListTrabajadoresPorCargo(criteria);
            criteria.put("id_cargo", Cargo_Supervisor);
            listaSupervisor = trabajadores.getListTrabajadoresPorCargo(criteria);
            criteria.put("id_cargo", Cargo_Tecnico);
            listaTecnico = trabajadores.getListTrabajadoresPorCargo(criteria);

            mapListas.put("listaTipTitular", listaTipTitular);
            mapListas.put("listaTipDocIdentidad", listaTipDocIdentidad);
            mapListas.put("listaConEspTitular", listaConEspTitular);
            mapListas.put("listaDepartamento", listaDepartamento);
            mapListas.put("listaTipVia", listaTipVia);
            mapListas.put("listaForAdquisicion", listaForAdquisicion);
            mapListas.put("listaConDeclarante", listaConDeclarante);
            mapListas.put("listaEstLleFicha", listaEstLleFicha);
            mapListas.put("listaVerificador", listaVerificador);
            mapListas.put("listaSupervisor", listaSupervisor);
            mapListas.put("listaTecnico", listaTecnico);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void cargarListasFichaBienComun(HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTipInterior = null;
        List listaTipEdificacion = null;
        List listaClaPredio = null;
        List listaSubClaPredio = null;
        List listaUbiPreCatastral = null;
        List listaTipParRegistral = null;
        List listaDecFabrica = null;
        List listaConDeclarante = null;
        List listaEstLleFicha = null;
        List listaMantenimiento = null;
        List listaSupervisor = null;
        List listaVerificador = null;
        List listaTecnico = null;
        List listaUsoBC = null;
        List listaTipVia = null;
        List listaTipPuerta = null;
        List listaConNumeracion = null;
        List listaMEP = null;
        List listaECC = null;
        List listaECS = null;
        List listaUCA = null;
        List listaNotaria = null;
        List listaVias = null;
        List listaHabUrbanas = null;
        HashMap mapListas = new HashMap();
        try{
            HttpSession session = request.getSession();

            TablasCodigosService tablasCodigos = new TablasCodigosService();
            listaTipInterior = tablasCodigos.getListaMaestro(TIPO_INTERIOR_PREDIO);
            listaTipEdificacion = tablasCodigos.getListaMaestro(TIPO_EDIFICACION);
            listaClaPredio = tablasCodigos.getListaMaestro(CLASIFICACION_PREDIO);
            listaUbiPreCatastral = tablasCodigos.getListaMaestro(PREDIO_EN);
            listaTipParRegistral = tablasCodigos.getListaMaestro(TIPO_PARTIDA_REGISTRAL);
            listaDecFabrica = tablasCodigos.getListaMaestro(DECLARATORIA_FABRICA);
            listaConDeclarante = tablasCodigos.getListaMaestro(CONDICION_DECLARANTE);
            listaEstLleFicha = tablasCodigos.getListaMaestro(ESTADO_LLENADO_FICHA);
            listaMantenimiento = tablasCodigos.getListaMaestro(MANTENIMIENTO_FICHA_INDIVIDUAL);
            listaTipVia = tablasCodigos.getListaMaestro(TIPO_VIA);
            listaTipPuerta = tablasCodigos.getListaMaestro(TIPO_PUERTA);
            listaConNumeracion = tablasCodigos.getListaMaestro(CONDICION_NUMERACION_PUERTA);
            listaMEP = tablasCodigos.getListaMaestro(MATERIAL_ESTRUCTURAL_PREDOMINANTE);
            listaECC = tablasCodigos.getListaMaestro(ESTADO_CONSTRUCCION);
            listaECS = tablasCodigos.getListaMaestro(ESTADO_CONSERVACION);
            listaUCA = tablasCodigos.getListaMaestro(UBICACION_CONTRUCCION_ANTIRREGLAMENTARIA);

            HashMap criteria = new HashMap();
            criteria.put("id_cargo", Cargo_Verificador);
            TrabajadoresService trabajadores = new TrabajadoresService();
            listaVerificador = trabajadores.getListTrabajadoresPorCargo(criteria);
            criteria.put("id_cargo", Cargo_Supervisor);
            listaSupervisor = trabajadores.getListTrabajadoresPorCargo(criteria);
            criteria.put("id_cargo", Cargo_Tecnico);
            listaTecnico = trabajadores.getListTrabajadoresPorCargo(criteria);

            UsoBCService uso = new UsoBCService();
            listaUsoBC = uso.getListUsosBC();

            NotariaService notaria = new NotariaService();
            listaNotaria = notaria.getListNotarias();

            HashMap mapFicha = (HashMap)session.getAttribute("mapFicha");
            HashMap mapUbigeo = (HashMap)mapFicha.get("mapUbigeo");
            ViaService via = new ViaService();
            listaVias = via.obtenerViasByUbigeo(mapUbigeo);

            HabilitacionUrbanaService HabUrbaService = new HabilitacionUrbanaService();
            listaHabUrbanas = HabUrbaService.obtenerHabilitacionesUrbanasByUbigeo(mapUbigeo);

            mapListas.put("listaTipInterior", listaTipInterior);
            mapListas.put("listaTipEdificacion", listaTipEdificacion);
            mapListas.put("listaClaPredio", listaClaPredio);
            mapListas.put("listaSubClaPredio", listaSubClaPredio);
            mapListas.put("listaUbiPreCatastral", listaUbiPreCatastral);
            mapListas.put("listaTipParRegistral", listaTipParRegistral);
            mapListas.put("listaDecFabrica", listaDecFabrica);
            mapListas.put("listaConDeclarante", listaConDeclarante);
            mapListas.put("listaEstLleFicha", listaEstLleFicha);
            mapListas.put("listaMantenimiento", listaMantenimiento);
            mapListas.put("listaVerificador", listaVerificador);
            mapListas.put("listaSupervisor", listaSupervisor);
            mapListas.put("listaTecnico", listaTecnico);
            mapListas.put("listaUsoBC", listaUsoBC);
            mapListas.put("listaTipVia", listaTipVia);
            mapListas.put("listaTipPuerta", listaTipPuerta);
            mapListas.put("listaConNumeracion", listaConNumeracion);
            mapListas.put("listaMEP", listaMEP);
            mapListas.put("listaECC", listaECC);
            mapListas.put("listaECS", listaECS);
            mapListas.put("listaUCA", listaUCA);
            mapListas.put("listaNotaria", listaNotaria);
            mapListas.put("listaVias", listaVias);
            mapListas.put("listaHabUrbanas", listaHabUrbanas);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void cargarListasFichaActividadEconomica(HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTipTitular = null;
        List listaTipInterior = null;
        List listaTipDocIdentidad = null;
        List listaDepartamento = null;
        List listaTipVia = null;
        List listaConDeclarante = null;
        List listaEstLleFicha = null;
        List listaMantenimiento = null;
        List listaDocPresentados = null;
        List listaConConductor = null;
        List listaSupervisor = null;
        List listaVerificador = null;
        List listaTecnico = null;
        List listaTipAnuncio = null;
        HashMap mapListas = new HashMap();
        try{
            HttpSession session = request.getSession();

            TablasCodigosService tablasCodigos = new TablasCodigosService();
            listaTipTitular = tablasCodigos.getListaMaestro(TIPO_PERSONA);
            listaTipInterior = tablasCodigos.getListaMaestro(TIPO_INTERIOR_PREDIO);
            listaTipDocIdentidad = tablasCodigos.getListaMaestro(TIPO_DOCUMENTO);
            listaTipVia = tablasCodigos.getListaMaestro(TIPO_VIA);
            listaConDeclarante = tablasCodigos.getListaMaestro(CONDICION_DECLARANTE);
            listaEstLleFicha = tablasCodigos.getListaMaestro(ESTADO_LLENADO_FICHA);
            listaMantenimiento = tablasCodigos.getListaMaestro(MANTENIMIENTO_FICHA_INDIVIDUAL);
            listaDocPresentados = tablasCodigos.getListaMaestro(DOCUMENTOS_PRESENTADOS);
            listaConConductor = tablasCodigos.getListaMaestro(CONDICION_CONDUCTOR);
            listaTipAnuncio = tablasCodigos.getListaMaestro(TIPO_ANUNCIO);

            HashMap criteria = new HashMap();
            criteria.put("id_cargo", Cargo_Verificador);
            TrabajadoresService trabajadores = new TrabajadoresService();
            listaVerificador = trabajadores.getListTrabajadoresPorCargo(criteria);
            criteria.put("id_cargo", Cargo_Supervisor);
            listaSupervisor = trabajadores.getListTrabajadoresPorCargo(criteria);
            criteria.put("id_cargo", Cargo_Tecnico);
            listaTecnico = trabajadores.getListTrabajadoresPorCargo(criteria);

            UbigeoService ubigeo = new UbigeoService();
            listaDepartamento = ubigeo.getListDepartamento();

            mapListas.put("listaTipTitular", listaTipTitular);
            mapListas.put("listaTipInterior", listaTipInterior);
            mapListas.put("listaTipDocIdentidad", listaTipDocIdentidad);
            mapListas.put("listaDepartamento", listaDepartamento);
            mapListas.put("listaTipVia", listaTipVia);
            mapListas.put("listaConDeclarante", listaConDeclarante);
            mapListas.put("listaEstLleFicha", listaEstLleFicha);
            mapListas.put("listaMantenimiento", listaMantenimiento);
            mapListas.put("listaDocPresentados", listaDocPresentados);
            mapListas.put("listaConConductor", listaConConductor);
            mapListas.put("listaVerificador", listaVerificador);
            mapListas.put("listaSupervisor", listaSupervisor);
            mapListas.put("listaTecnico", listaTecnico);
            mapListas.put("listaTipAnuncio", listaTipAnuncio);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void cargarListasFichaRural(HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTipTitular = null;
        List listaEstCivil = null;
        List listaTipDocIdentidad = null;
        List listaPerJuridica = null;
        List listaConEspTitular = null;
        List listaConTitular = null;
        List listaTipParRegistral = null;
        List listaForAdquisicion = null;
        List listaUsoPreRural = null;
        List listaClaUsoActual = null;
        List listaConInstalacion = null;
        List listaDepartamento = null;
        List listaRiego = null;
        HashMap mapListas = new HashMap();
        try{
            HttpSession session = request.getSession();

            TipTitularService tipTitular = new TipTitularService();
            listaTipTitular = tipTitular.getListTipTitular();

            EstCivilService estCivil = new EstCivilService();
            listaEstCivil = estCivil.getListEstCivil();

            TipDocIdentidadService tipDocIdentidad = new TipDocIdentidadService();
            listaTipDocIdentidad = tipDocIdentidad.getListTipDocIdentidad();

            PerJuridicaService perJuridica = new PerJuridicaService();
            listaPerJuridica = perJuridica.getListPerJuridica();

            ConEspTitularService conEspTitular = new ConEspTitularService();
            listaConEspTitular = conEspTitular.getListConEspTitular();

            UbigeoService ubigeo = new UbigeoService();
            listaDepartamento = ubigeo.getListDepartamento();

            ConTitularService conTitular = new ConTitularService();
            listaConTitular = conTitular.getListConTitular();

            ForAdquisicionService forAdquisicion = new ForAdquisicionService();
            listaForAdquisicion = forAdquisicion.getListForAdquisicion();

            TipParRegistralService tipParRegistral = new TipParRegistralService();
            listaTipParRegistral = tipParRegistral.getListTipParRegistral();

            UsoPreRuralService usoPreRural = new UsoPreRuralService();
            listaUsoPreRural = usoPreRural.getListUsoPreRural();

            ClaUsoActualService claUsoActual = new ClaUsoActualService();
            listaClaUsoActual = claUsoActual.getListClaUsoActual();

            ConInstalacionService conInstalacion = new ConInstalacionService();
            listaConInstalacion = conInstalacion.getListConInstalacion();

            RiegoService riego = new RiegoService();
            listaRiego = riego.getListRiego();

            mapListas.put("listaTipTitular", listaTipTitular);
            mapListas.put("listaEstCivil", listaEstCivil);
            mapListas.put("listaTipDocIdentidad", listaTipDocIdentidad);
            mapListas.put("listaPerJuridica", listaPerJuridica);
            mapListas.put("listaConEspTitular", listaConEspTitular);
            mapListas.put("listaDepartamento", listaDepartamento);
            mapListas.put("listaConTitular", listaConTitular);
            mapListas.put("listaForAdquisicion", listaForAdquisicion);
            mapListas.put("listaTipParRegistral", listaTipParRegistral);
            mapListas.put("listaUsoPreRural", listaUsoPreRural);
            mapListas.put("listaClaUsoActual", listaClaUsoActual);
            mapListas.put("listaConInstalacion", listaConInstalacion);
            mapListas.put("listaRiego", listaRiego);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void cargarListasFichaCultural(HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaCatInmueble = null;
        List listaFilCronologica = null;
        List listaTipParRegistral = null;
        List listaTipArquitectura = null;
        List listaTipMatConstructivo = null;
        List listaAfeNatural = null;
        List listaAfeAntropica = null;
        List listaIntConservacion = null;
        List listaTipTitular = null;
        List listaEstCivil = null;
        List listaTipDocIdentidad = null;
        List listaPerJuridica = null;
        List listaConEspTitular = null;
        List listaConDeclarante = null;
        List listaEstLleFicha = null;
        List listaTipArqColonial = null;
        List listaFilEstilistica = null;
        List listaIntInmueble = null;
        List listaEstElemento = null;
        HashMap mapListas = new HashMap();
        try{
            HttpSession session = request.getSession();

            CatInmuebleService catInmueble = new CatInmuebleService();
            listaCatInmueble = catInmueble.getListCatInmueble();

            FilCronologicaService filCronologica = new FilCronologicaService();
            listaFilCronologica = filCronologica.getListFilCronologica();

            TipParRegistralService tipParRegistral = new TipParRegistralService();
            listaTipParRegistral = tipParRegistral.getListTipParRegistral();

            TipArquitecturaService tipArquitectura = new TipArquitecturaService();
            listaTipArquitectura = tipArquitectura.getListTipArquitectura();

            TipMatConstructivoService tipMatConstructivo = new TipMatConstructivoService();
            listaTipMatConstructivo = tipMatConstructivo.getListTipMatConstructivo();

            AfeNaturalService afeNatural = new AfeNaturalService();
            listaAfeNatural = afeNatural.getListAfeNatural();

            AfeAntropoficaService afeAntropica = new AfeAntropoficaService();
            listaAfeAntropica = afeAntropica.getListAfeAntropica();

            IntConservacionService intConservacion = new IntConservacionService();
            listaIntConservacion = intConservacion.getListIntConservacion();

            TipTitularService tipTitular = new TipTitularService();
            listaTipTitular = tipTitular.getListTipTitular();

            EstCivilService estCivil = new EstCivilService();
            listaEstCivil = estCivil.getListEstCivil();

            TipDocIdentidadService tipDocIdentidad = new TipDocIdentidadService();
            listaTipDocIdentidad = tipDocIdentidad.getListTipDocIdentidad();

            PerJuridicaService perJuridica = new PerJuridicaService();
            listaPerJuridica = perJuridica.getListPerJuridica();

            ConEspTitularService conEspTitular = new ConEspTitularService();
            listaConEspTitular = conEspTitular.getListConEspTitular();

            ConDeclaranteService conDeclarante = new ConDeclaranteService();
            listaConDeclarante = conDeclarante.getListConDeclarante();

            EstLleFichaService estLleFicha = new EstLleFichaService();
            listaEstLleFicha = estLleFicha.getListEstLleFicha();

            TipArqColonialService tipArqColonial = new TipArqColonialService();
            listaTipArqColonial = tipArqColonial.getListTipArqColonial();

            FilEstilisticaService filEstilistica = new FilEstilisticaService();
            listaFilEstilistica = filEstilistica.getListFilEstilistica();

            IntInmuebleService intInmueble = new IntInmuebleService();
            listaIntInmueble = intInmueble.getListIntInmueble();

            listaEstElemento = getListaEstadoElementos();

            mapListas.put("listaCatInmueble", listaCatInmueble);
            mapListas.put("listaFilCronologica", listaFilCronologica);
            mapListas.put("listaTipParRegistral", listaTipParRegistral);
            mapListas.put("listaTipArquitectura", listaTipArquitectura);
            mapListas.put("listaTipMatConstructivo", listaTipMatConstructivo);
            mapListas.put("listaAfeNatural", listaAfeNatural);
            mapListas.put("listaAfeAntropica", listaAfeAntropica);
            mapListas.put("listaIntConservacion", listaIntConservacion);
            mapListas.put("listaTipTitular", listaTipTitular);
            mapListas.put("listaEstCivil", listaEstCivil);
            mapListas.put("listaTipDocIdentidad", listaTipDocIdentidad);
            mapListas.put("listaPerJuridica", listaPerJuridica);
            mapListas.put("listaConEspTitular", listaConEspTitular);
            mapListas.put("listaConDeclarante", listaConDeclarante);
            mapListas.put("listaEstLleFicha", listaEstLleFicha);
            mapListas.put("listaTipArqColonial", listaTipArqColonial);
            mapListas.put("listaFilEstilistica", listaFilEstilistica);
            mapListas.put("listaIntInmueble", listaIntInmueble);
            mapListas.put("listaEstElemento", listaEstElemento);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static boolean existeUbigeoDistrital(HashMap mapFicha){

        boolean existe = false;
        HashMap mapCabecera = null;
        try{
            mapCabecera = (HashMap)mapFicha.get("mapCabecera");
            if(mapCabecera!=null && !mapCabecera.isEmpty()){
                UbigeoDistritalService ubigeo = new UbigeoDistritalService();
                existe = ubigeo.existeUbigeoDistrital(mapCabecera);
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return existe;
    }
}