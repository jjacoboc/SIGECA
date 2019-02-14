/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.seguridad.action;

import com.bmp.sigeca.maestro.service.PerfilService;
import com.bmp.sigeca.maestro.service.TablaService;
import com.bmp.sigeca.maestro.service.TablasService;
import com.bmp.sigeca.maestro.service.UbigeoService;
import com.bmp.sigeca.mantenimiento.service.CargoService;
import com.bmp.sigeca.mantenimiento.service.EstadoUsuarioService;
import com.bmp.sigeca.mantenimiento.service.HabilitacionUrbanaService;
import com.bmp.sigeca.mantenimiento.service.NotariaService;
import com.bmp.sigeca.mantenimiento.service.TablasCodigosService;
import com.bmp.sigeca.mantenimiento.service.TrabajadorService;
import com.bmp.sigeca.mantenimiento.service.UsosBCService;
import com.bmp.sigeca.mantenimiento.service.UsosService;
import com.bmp.sigeca.mantenimiento.service.ViaService;
import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.registro.service.FichaActividadEconomicaService;
import com.bmp.sigeca.registro.service.FichaBienComunService;
import com.bmp.sigeca.registro.service.FichaCotitularidadService;
import com.bmp.sigeca.registro.service.FichaIndividualService;
import com.bmp.sigeca.registro.service.FichaService;
import com.bmp.sigeca.reporte.service.ReporteAdministradorService;
import com.bmp.sigeca.reporte.service.ReporteUsuarioService;
import com.bmp.sigeca.resource.Properties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Jonatan Jacobo
 */
public class MenuAction extends DispatchAction {

    protected Logger logg;

    /**
     * Método que deriva a la página de la ficha catastral individual.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarFichaIndividual(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarFichaIndividual");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("codTipFicha", Properties.FichaCatastralUrbanaIndividual);
            session.setAttribute("accion", (String)map.get("accion"));
            cargarDatosBasicos(request, response);

            session.removeAttribute("mapListas");
            Properties.cargarListasFichaIndividual(request,response);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fichaIndividual");
    }

    /**
     * Método que deriva a la página de la ficha catastral cotitularidad.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarFichaCotitularidad(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarFichaCotitularidad");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("codTipFicha", Properties.FichaCatastralUrbanaCotitularidad);
            session.setAttribute("accion", (String)map.get("accion"));
            cargarDatosBasicos(request, response);

            session.removeAttribute("mapListas");
            Properties.cargarListasFichaCotitular(request,response);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fichaCotitularidad");
    }

    /**
     * Método que deriva a la página de la ficha catastral actividad económica.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarFichaActividadEconomica(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarFichaActividadEconomica");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("codTipFicha", Properties.FichaCatastralUrbanaActividadEconomica);
            session.setAttribute("accion", (String)map.get("accion"));
            cargarDatosBasicos(request, response);

            session.removeAttribute("mapListas");
            Properties.cargarListasFichaActividadEconomica(request,response);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que deriva a la página de la ficha catastral bienes comunes.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarFichaBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarFichaBienComun");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("codTipFicha", Properties.FichaCatastralUrbanaBienesComunes);
            session.setAttribute("accion", (String)map.get("accion"));
            cargarDatosBasicos(request, response);

            session.removeAttribute("mapListas");
            Properties.cargarListasFichaBienComun(request,response);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de la ficha catastral rural.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarFichaRural(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarFichaRural");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("codTipFicha", Properties.FichaCatastralRural);
            session.setAttribute("accion", (String)map.get("accion"));
            cargarDatosBasicos(request, response);

            session.removeAttribute("mapListas");
            Properties.cargarListasFichaRural(request,response);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fichaRural");
    }

    /**
     * Método que deriva a la página de la ficha catastral bienes culturales.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarFichaCulturalPrehispanico(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarFichaCulturalPrehispanico");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("codTipFicha", Properties.FichaCatastralBienesCulturales_MonumentoPrehispanico);
            session.setAttribute("accion", (String)map.get("accion"));
            cargarDatosBasicos(request, response);

            session.removeAttribute("mapListas");
            Properties.cargarListasFichaCultural(request,response);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fichaCulturalPrehispanico");
    }

    /**
     * Método que deriva a la página de la ficha catastral bienes culturales.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarFichaCulturalColonial(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarFichaCulturalColonial");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("codTipFicha", Properties.FichaCatastralBienesCulturales_MonumentoColonial);
            session.setAttribute("accion", (String)map.get("accion"));
            cargarDatosBasicos(request, response);

            session.removeAttribute("mapListas");
            Properties.cargarListasFichaCultural(request,response);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fichaCulturalColonial");
    }

    public void cargarDatosBasicos(HttpServletRequest request,HttpServletResponse response){

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarDatosBasicos");

        String numFicha = "";
        String numFichaHist = "";
        String codigoUbigeo = "";
        HashMap mapFicha = null;
        HashMap mapCabecera = null;
        HashMap mapUbigeo = null;
        try{
            HttpSession session = request.getSession();

//            FichaService ficha = new FichaService();
//            numFicha = StringUtil.emptyAsZero(StringUtil.nullAsEmptyString(ficha.getLastNumFicha()));
//
//            FichaHistService fichaHist = new FichaHistService();
//            numFichaHist = StringUtil.emptyAsZero(StringUtil.nullAsEmptyString(fichaHist.getLastNumFicha()));
//
//            if(numFichaHist.compareTo(numFicha)>0){
//                numFicha = numFichaHist;
//            }
//
//            numFicha = Long.toString(Long.parseLong(numFicha)+1);
//            numFicha = Properties.concatLeftCharacter("0", 7, numFicha);
//
            mapFicha = new HashMap();
//            mapFicha.put("numFicha", numFicha);

            com.bmp.sigeca.mantenimiento.service.UbigeoService ubigeoService = new com.bmp.sigeca.mantenimiento.service.UbigeoService();
            mapUbigeo = ubigeoService.obtenerUbigeoActivo();

            codigoUbigeo = (String)mapUbigeo.get("codigoUbigeo");
            mapUbigeo.put("id_ubi_geo", codigoUbigeo);
            mapCabecera = new HashMap();
            mapCabecera.put("codDepartamento", codigoUbigeo.substring(0, 2));
            mapCabecera.put("codProvincia", codigoUbigeo.substring(2, 4));
            mapCabecera.put("codDistrito", codigoUbigeo.substring(4, 6));

            HashMap mapTitular = new HashMap();
            mapTitular.put("codTipTitular", Properties.TipoTitular_PersonaNatural);

            HashMap mapCotitular = new HashMap();
            mapCotitular.put("codTipTitular", Properties.TipoTitular_PersonaNatural);

            HashMap mapConductor = new HashMap();
            mapConductor.put("codTipTitular", Properties.TipoTitular_PersonaNatural);

            mapFicha.put("mapCabecera", mapCabecera);
            mapFicha.put("mapUbigeo", mapUbigeo);
            mapFicha.put("mapTitular", mapTitular);
            mapFicha.put("mapCotitular", mapCotitular);
            mapFicha.put("mapConductor", mapConductor);

            session.setAttribute("mapFicha",mapFicha);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Método que deriva a la página de actualización de propietarios.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward actualizarPropietario(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo actualizarPropietario");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("accion", (String)map.get("accion"));
            session.removeAttribute("mapListas");
            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("actualizarFicha");
    }

    /**
     * Método que deriva a la página de Ficha a rectificar o actualizar.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarFichaPorActualizar(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cargarFichaPorActualizar");

        HashMap mapFicha = null;
        HashMap mapFichaHistorial = null;
        String tipo = "";
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            FichaService fichaService = new FichaService();
            mapFicha = fichaService.obtenerFicha(map);

            tipo = (String)mapFicha.get("codTipFicha");

            session.removeAttribute("mapListas");
            if(tipo.equals(Properties.FichaCatastralUrbanaIndividual)){
                FichaIndividualService ficha = new FichaIndividualService();
                mapFicha = ficha.obtenerFicha(mapFicha);
                Properties.cargarListasFichaIndividual(request,response);
                destino = "fichaIndividual";
            }else if(tipo.equals(Properties.FichaCatastralUrbanaCotitularidad)){
                FichaCotitularidadService ficha = new FichaCotitularidadService();
                mapFicha = ficha.obtenerFicha(mapFicha);
                Properties.cargarListasFichaCotitular(request,response);
                destino = "fichaCotitularidad";
            }else if(tipo.equals(Properties.FichaCatastralUrbanaActividadEconomica)){
                FichaActividadEconomicaService ficha = new FichaActividadEconomicaService();
                mapFicha = ficha.obtenerFicha(mapFicha);
                Properties.cargarListasFichaActividadEconomica(request,response);
                destino = "fichaActividadEconomica";
            }else if(tipo.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                FichaBienComunService ficha = new FichaBienComunService();
                mapFicha = ficha.obtenerFicha(mapFicha);
                Properties.cargarListasFichaBienComun(request,response);
                destino = "fichaBienComun";
            }
            mapFicha.put("flagActualizar", "true");

            HashMap mapTitular = (HashMap)mapFicha.get("mapTitular");

            if(mapTitular!=null && !mapTitular.isEmpty()){
                HashMap mapUbigeo = new HashMap();
                mapUbigeo.put("codDepartamento", (String)mapTitular.get("codDepartamento"));
                UbigeoService ubigeo = new UbigeoService();
                List listaProvincia = ubigeo.getListProvincia(mapUbigeo);

                mapUbigeo.put("codProvincia", (String)mapTitular.get("codProvincia"));
                List listaDistrito = ubigeo.getListDistrito(mapUbigeo);

                HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();
                mapListas.put("listaProvincia", listaProvincia);
                mapListas.put("listaDistrito", listaDistrito);
                session.setAttribute("mapListas", mapListas);
            }

            mapFichaHistorial = (HashMap)mapFicha.clone();

            session.setAttribute("mapFicha", mapFicha);
            session.setAttribute("mapFichaHistorial", mapFichaHistorial);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de fusción de fichas catastrales.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward añadirTextField(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo añadirTextField");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            List lista = session.getAttribute("listaTextField")!=null?(List)session.getAttribute("listaTextField"):new ArrayList();
            String cuc[] = ((String)map.get("cuc")).split(";");
            if(lista!=null && !lista.isEmpty()){
                for(int i=0;i<cuc.length;i++){
                    lista.set(i, cuc[i]);
                }
            }
            if(lista.size()>0)  lista.add(lista.size(), "");
            else  lista.add("");

            session.setAttribute("listaTextField", lista);
            session.setAttribute("accion", (String)map.get("accion"));
            session.removeAttribute("mapListas");
            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fusionarLote");
    }

    /**
     * Método que deriva a la página de fusción de fichas catastrales.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward quitarTextField(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo quitarTextField");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            List lista = session.getAttribute("listaTextField")!=null?(List)session.getAttribute("listaTextField"):new ArrayList();
            String cuc[] = ((String)map.get("cuc")).split(";");
            for(int i=0;i<cuc.length;i++){
                lista.set(i, cuc[i]);
            }
            lista.remove(lista.size()-1);

            session.setAttribute("listaTextField", lista);
            session.setAttribute("accion", (String)map.get("accion"));
            session.removeAttribute("mapListas");
            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("fusionarLote");
    }

    /**
     * Método que deriva a la página de Reporte de Lostes y Unidades Catastrales.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward reporteLotes(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo reporteLotes");

        List lista = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            FichaService fichaService = new FichaService();
            lista = fichaService.reporteFichas(new HashMap());

            session.setAttribute("listaFicha", lista);
            session.setAttribute("accion", (String)map.get("accion"));
            session.removeAttribute("mapListas");
            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("reporteLotes");
    }

    /**
     * Método que deriva a la página de Reporte de Lostes y Unidades Catastrales.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward reportePropietarios(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo reportePropietarios");

        List lista = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            FichaService fichaService = new FichaService();
            lista = fichaService.reporteFichas(new HashMap());

            session.setAttribute("listaFicha", lista);
            session.setAttribute("accion", (String)map.get("accion"));
            session.removeAttribute("mapListas");
            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("reportePropietarios");
    }

    /**
     * Método que deriva a la página de Registro de Usuarios.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward registrarUsuario(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo registrarUsuario");

        List listaTrabajadores = null;
        List listaPerfil = null;
        List listaEstado = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            PerfilService perfil = new PerfilService();
            listaPerfil = perfil.getListPerfil();

            TrabajadorService service = new TrabajadorService();
            listaTrabajadores = service.obtenerTrabajadores();

            EstadoUsuarioService estado = new EstadoUsuarioService();
            listaEstado = estado.obtenerEstadosUsuarios();

            session.setAttribute("listaEstado", listaEstado);
            session.setAttribute("listaTrabajadores", listaTrabajadores);
            session.setAttribute("listaPerfil", listaPerfil);
            session.setAttribute("accion", (String)map.get("accion"));
            session.removeAttribute("mapListas");
            session.removeAttribute("mapFicha");
            session.removeAttribute("registroUsuario");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("registroUsuario");
    }

    /**
     * Método que deriva a la página de cambio de clave.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cambiarClave(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo cambiarClave");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            session.setAttribute("accion", (String)map.get("accion"));
            session.removeAttribute("mapListas");
            session.removeAttribute("mapFicha");
            session.removeAttribute("registroUsuario");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("cambiarClave");
    }

    /**
     * Método que registra una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irRegistrar(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo irRegistrar");

        List listaTablas = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();

            TablaService tablaservice = new TablaService();
            listaTablas = tablaservice.getListaTablas();

            mapListas.put("listaTablas", listaTablas);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("maestro");
    }

    /**
     * Método que deriva a la página de Búsqueda.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irBusqueda(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MenuAction metodo irBusqueda");

        String destino = "";
        List lista = null;
        try{
            HttpSession session = request.getSession(false);
            UsuarioBean usuario = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):null;

            if(session!=null){
                if(usuario!=null){
                    FichaService fichaService = new FichaService();
                    lista = fichaService.buscarFichas(new HashMap());
                    if(lista!=null && !lista.isEmpty()){
                        for(int i=0;i<lista.size();i++){
                            String num = Properties.concatLeftCharacter("0", 7, (String)((HashMap)lista.get(i)).get("numFicha"));
                            ((HashMap)lista.get(i)).put("numFicha", num);
                        }
                    }
                    session.setAttribute("listaFicha", lista);

                    destino = "busqueda";
                }else{
                    destino = "noSession";
                }
            }else{
                destino = "noSession";
            }

            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    public ActionForward cerrarSesion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        try{
            HttpSession session = request.getSession();
            session.invalidate();
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("inicio");
    }

    /**
     * Obtiene todos los registros de la tabla USOS y los guarda en session
     * como listaUsos, luego muestra la página ConsultaUsos.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoUsos (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaUsos = null;
        try
        {
            HttpSession session = request.getSession();
            UsosService usosService = new UsosService();
            listaUsos = usosService.obtenerUsos();
            session.setAttribute("listaUsos", listaUsos);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("consultaUsos");
    }

    /**
     * Obtiene todos los registros de la tabla USOS_BC y los guarda en session
     * como listaUsosBC, luego muestra la página ConsultaUsosBC.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoUsosBC (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaUsosBC = null;
        try
        {
            HttpSession session = request.getSession();
            UsosBCService usosBCService = new UsosBCService();
            listaUsosBC = usosBCService.obtenerUsosBC();
            session.setAttribute("listaUsosBC", listaUsosBC);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consultaUsosBC");
    }

    /**
     * Obtiene todos los registros de la tabla NOTARIA y los guarda en session
     * como listaNotarias, luego muestra la página ConsultaNotaria.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoNotaria (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaNotarias = null;
        try
        {
            HttpSession session = request.getSession();
            NotariaService notariaService = new NotariaService();
            listaNotarias = notariaService.obtenerNotarias();
            session.setAttribute("listaNotarias", listaNotarias);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consultaNotaria");
    }

    /**
     * Obtiene todos los registros de la tabla UBIGEO y los guarda en session
     * como listaUbigeos, luego muestra la página ConsultaUbigeo.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoUbiego (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaUbigeos = null;
        try
        {
            HttpSession session = request.getSession();
            com.bmp.sigeca.mantenimiento.service.UbigeoService ubigeoService = new com.bmp.sigeca.mantenimiento.service.UbigeoService();
            listaUbigeos = ubigeoService.obtenerUbigeos();
            session.setAttribute("listaUbigeos", listaUbigeos);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consultaUbigeo");
    }

    /**
     * Obtiene todos los registros de la tabla CODIGO_INSTALACION y los guarda
     * en session como listaCodInstalaciones, luego muestra la página
     * ConsultaCodigo.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoCodigoInstalaciones (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaCodInstalaciones = null;
        try
        {
            HttpSession session = request.getSession();
            com.bmp.sigeca.mantenimiento.service.CodInstalacionService codInstalacionService = new com.bmp.sigeca.mantenimiento.service.CodInstalacionService();
            listaCodInstalaciones = codInstalacionService.obtenerCodInstalaciones();
            session.setAttribute("listaCodInstalaciones", listaCodInstalaciones);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consultaCodInstalacion");
    }

    /**
     * Obtiene todos los registros de la tabla TABLAS_CODIGOS y los guarda
     * en session como listaTCodigos, luego muestra la página
     * ConsultaCodigo.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoTablaCodigos (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTablas = null;
        List listaTablasCodigos = null;
        try
        {
            HttpSession session = request.getSession();
            TablasService tablasService = new TablasService();
            listaTablas = tablasService.getListaTablas();
            TablasCodigosService tablasCodigosService = new TablasCodigosService();
            listaTablasCodigos = tablasCodigosService.obtenerTablasCodigos();
            session.setAttribute("listaTablasCodigos", listaTablasCodigos);
            session.setAttribute("listaTablas", listaTablas);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consultaTablasCodigos");
    }

    /**
     * Obtiene todos los registros de la tabla HAB_URBA y los guarda en session
     * como listaHabUrba, luego muestra la página ConsultaHabUrba.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoHabUrba (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaHabUrba = null;
        List listaTipoHabUrba = null;
        try
        {
            HttpSession session = request.getSession();
            HabilitacionUrbanaService service = new HabilitacionUrbanaService();
            listaHabUrba = service.obtenerHabilitacionesUrbanas();
            com.bmp.sigeca.maestro.service.TablasCodigosService tablasCodigos = new com.bmp.sigeca.maestro.service.TablasCodigosService();
            listaTipoHabUrba = tablasCodigos.getListaMaestro(Properties.TIPO_HABILITACION_HURBANA);
            session.setAttribute("listaHabUrba", listaHabUrba);
            session.setAttribute("listaTipoHabUrba", listaTipoHabUrba);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consultaHabUrba");
    }

    /**
     * Obtiene todos los registros de la tabla VIAS y los guarda en session
     * como listaVia, luego muestra la página ConsultaVia.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoVia (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaVia = null;
        List listaTipoVia = null;
        try
        {
            HttpSession session = request.getSession();
            ViaService service = new ViaService();
            listaVia = service.obtenerVias();
            com.bmp.sigeca.maestro.service.TablasCodigosService tablasCodigos = new com.bmp.sigeca.maestro.service.TablasCodigosService();
            listaTipoVia = tablasCodigos.getListaMaestro(Properties.TIPO_VIA);
            session.setAttribute("listaVia", listaVia);
            session.setAttribute("listaTipoVia", listaTipoVia);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consultaVia");
    }

    /**
     * Obtiene todos los registros de la tabla TRABAJADORES y los guarda en session
     * como listaTrabajadores, luego muestra la página consultaTrabajador.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward mantenimientoTrabajador (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTrabajadores = null;
        List listaCargo = null;
        try
        {
            HttpSession session = request.getSession();
            TrabajadorService service = new TrabajadorService();
            listaTrabajadores = service.obtenerTrabajadores();

            CargoService cargo = new CargoService();
            listaCargo = cargo.obtenerCargos();

            session.setAttribute("listaCargo", listaCargo);
            session.setAttribute("listaTrabajadores", listaTrabajadores);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consultaTrabajador");
    }

    /**
     * Método que deriva a la página de la consulta fichas catastrales.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward consultarFichasCatastrales (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        try
        {
            com.bmp.sigeca.consulta.service.FichaService fichaService = new com.bmp.sigeca.consulta.service.FichaService();
            request.setAttribute("listaEstados", fichaService.obtenerEstados());
            request.setAttribute("listaTiposVia", fichaService.obtenerTiposVia());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("consultaFichasCatastrales");
    }


	/**
     * Método que deriva a la página de reporte usuario.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward   reporteUsuario (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

            logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en Reporte Usuario");
        //llenar los combos para la pantalla de reporte usuario

        ReporteUsuarioService reporteUsuarioService = new ReporteUsuarioService();
        try{

            //combo de sectores

            //combo de estados

            //combo de tipo de codigo

            //combo de tipo de documento

            //combo de clasificacion predio

            //combo de otros



        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }


        return actionMapping.findForward("reporteUsuario");
    }

	   /**
     * Método que deriva a la página de reporte administrador.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward   reporteAdministrador (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en Reporte Administrador");

        List listaEstados = null;

        ReporteAdministradorService reporteAdministradorService = new ReporteAdministradorService();
        try{
            HttpSession session = request.getSession();
            com.bmp.sigeca.maestro.service.TablasCodigosService tablasCodigos = new com.bmp.sigeca.maestro.service.TablasCodigosService();
            listaEstados = tablasCodigos.getListaMaestro(Properties.ESTADO_FICHA);
            session.setAttribute("listaEstados", listaEstados);
            //combo de digitador


            //combo de estados



        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("reporteAdministrador");
    }
}

