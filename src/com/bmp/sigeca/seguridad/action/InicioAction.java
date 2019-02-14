/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.seguridad.action;

import com.bmp.sigeca.registro.service.FichaActividadEconomicaService;
import com.bmp.sigeca.registro.service.FichaBienComunService;
import com.bmp.sigeca.registro.service.FichaCotitularidadService;
import com.bmp.sigeca.registro.service.FichaIndividualService;
import com.bmp.sigeca.maestro.service.UbigeoService;
import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.registro.service.FichaService;
import com.bmp.sigeca.resource.Properties;
import com.bmp.sigeca.util.StringUtil;
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
 * @author Administrador
 */
public class InicioAction extends DispatchAction {

    protected Logger logg;
   
    /**
     * Método que obtiene la lista de Fichas Catastrales.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward buscarFichas(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en InicioAction metodo obtenerListaFicha");

        List lista = null;
        HashMap mapBusqueda = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            UsuarioBean usuario = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):null;
            if(usuario!=null){
                if(usuario.getCodPefil().equals(Properties.Perfil_Administrador)){
                    destino = "menuAdministrador";
                }else if(usuario.getCodPefil().equals(Properties.Perfil_Supervisor)){
                    destino = "menuSupervisor";
                }
            }else{
                destino = "noSession";
            }

            mapBusqueda = new HashMap();
            mapBusqueda.put("cuc", StringUtil.nullAsEmptyString((String)map.get("cuc")));
            mapBusqueda.put("numFicha", StringUtil.nullAsEmptyString(Properties.concatLeftCharacter("0", 7, (String)map.get("numFicha"))));
            mapBusqueda.put("codHojCatastral", StringUtil.nullAsEmptyString((String)map.get("codHojCatastral")));
            mapBusqueda.put("codRefCatastral", StringUtil.nullAsEmptyString((String)map.get("codRefCatastral")));
            mapBusqueda.put("codEstado", StringUtil.nullAsEmptyString((String)map.get("codEstado")));
            mapBusqueda.put("dniTecCatastral", StringUtil.nullAsEmptyString((String)map.get("dniTecCatastral")));
            mapBusqueda.put("nomTecCatastral", StringUtil.nullAsEmptyString((String)map.get("nomTecCatastral")));

            FichaService fichaService = new FichaService();
            lista = fichaService.buscarFichas(mapBusqueda);

            if(lista!=null && !lista.isEmpty()){
                for(int i=0;i<lista.size();i++){
                    String num = Properties.concatLeftCharacter("0", 7, (String)((HashMap)lista.get(i)).get("numFicha"));
                    ((HashMap)lista.get(i)).put("numFicha", num);
                }
            }

            session.setAttribute("listaFicha", lista);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que obtiene una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward obtenerFichaIndividual(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en InicioAction metodo obtenerFichaIndividual");

        HashMap mapFicha = new HashMap();
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            List listaFicha = session.getAttribute("listaFicha")!=null?(List)session.getAttribute("listaFicha"):new ArrayList();
            int i = Integer.parseInt((String)map.get("indListaFicha"));

            if(listaFicha!=null && !listaFicha.isEmpty()){
                FichaIndividualService fichaService = new FichaIndividualService();
                mapFicha = fichaService.obtenerFicha((HashMap)listaFicha.get(i));
                mapFicha.put("flagActualizar", "true");
                session.removeAttribute("mapListas");
                Properties.cargarListasFichaIndividual(request,response);
                mapFicha.put("numFicha", Properties.concatLeftCharacter("0", 7, (String)mapFicha.get("numFicha")));

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

                HashMap mapUbigeo = new HashMap();
                com.bmp.sigeca.mantenimiento.service.UbigeoService ubigeoService = new com.bmp.sigeca.mantenimiento.service.UbigeoService();
                mapUbigeo = ubigeoService.obtenerUbigeoActivo();

                mapUbigeo.put("id_ubi_geo", (String)mapUbigeo.get("codigoUbigeo"));

                mapFicha.put("mapUbigeo", mapUbigeo);
                session.setAttribute("codTipFicha", Properties.FichaCatastralUrbanaIndividual);
                session.setAttribute("accion", (String)map.get("accion"));
                destino = "fichaIndividual";
            }else{
                destino = "busqueda";
            }
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que obtiene una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward obtenerFichaCotitularidad(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en InicioAction metodo obtenerFichaCotitularidad");

        HashMap mapFicha = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            List listaFicha = session.getAttribute("listaFicha")!=null?(List)session.getAttribute("listaFicha"):new ArrayList();
            int i = Integer.parseInt((String)map.get("indListaFicha"));

            if(listaFicha!=null && !listaFicha.isEmpty()){
                FichaCotitularidadService fichaService = new FichaCotitularidadService();
                mapFicha = fichaService.obtenerFicha((HashMap)listaFicha.get(i));
                mapFicha.put("flagActualizar", "true");
                session.removeAttribute("mapListas");
                Properties.cargarListasFichaCotitular(request,response);
                mapFicha.put("numFicha", Properties.concatLeftCharacter("0", 7, (String)mapFicha.get("numFicha")));

                HashMap mapCotitular = (HashMap)mapFicha.get("mapCotitular");

                if(mapCotitular!=null && !mapCotitular.isEmpty()){
                    HashMap mapUbigeo = new HashMap();
                    mapUbigeo.put("codDepartamento", (String)mapCotitular.get("codDepartamento"));
                    UbigeoService ubigeo = new UbigeoService();
                    List listaProvincia = ubigeo.getListProvincia(mapUbigeo);

                    mapUbigeo.put("codProvincia", (String)mapCotitular.get("codProvincia"));
                    List listaDistrito = ubigeo.getListDistrito(mapUbigeo);

                    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();
                    mapListas.put("listaProvincia", listaProvincia);
                    mapListas.put("listaDistrito", listaDistrito);
                    session.setAttribute("mapListas", mapListas);
                }

                HashMap mapUbigeo = new HashMap();
                com.bmp.sigeca.mantenimiento.service.UbigeoService ubigeoService = new com.bmp.sigeca.mantenimiento.service.UbigeoService();
                mapUbigeo = ubigeoService.obtenerUbigeoActivo();

                mapUbigeo.put("id_ubi_geo", (String)mapUbigeo.get("codigoUbigeo"));

                mapFicha.put("mapUbigeo", mapUbigeo);
                session.setAttribute("codTipFicha", Properties.FichaCatastralUrbanaCotitularidad);
                session.setAttribute("accion", (String)map.get("accion"));
                destino = "fichaCotitularidad";
            }else{
                destino = "busqueda";
            }
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que obtiene una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward obtenerFichaActividadEconomica(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en InicioAction metodo obtenerFichaActividadEconomica");

        HashMap mapFicha = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            List listaFicha = session.getAttribute("listaFicha")!=null?(List)session.getAttribute("listaFicha"):new ArrayList();
            int i = Integer.parseInt((String)map.get("indListaFicha"));

            if(listaFicha!=null && !listaFicha.isEmpty()){
                FichaActividadEconomicaService fichaService = new FichaActividadEconomicaService();
                mapFicha = fichaService.obtenerFicha((HashMap)listaFicha.get(i));
                mapFicha.put("flagActualizar", "true");
                session.removeAttribute("mapListas");
                Properties.cargarListasFichaActividadEconomica(request,response);
                mapFicha.put("numFicha", Properties.concatLeftCharacter("0", 7, (String)mapFicha.get("numFicha")));

                HashMap mapConductor = (HashMap)mapFicha.get("mapConductor");

                if(mapConductor!=null && !mapConductor.isEmpty()){
                    HashMap mapUbigeo = new HashMap();
                    mapUbigeo.put("codDepartamento", (String)mapConductor.get("codDepartamento"));
                    UbigeoService ubigeo = new UbigeoService();
                    List listaProvincia = ubigeo.getListProvincia(mapUbigeo);

                    mapUbigeo.put("codProvincia", (String)mapConductor.get("codProvincia"));
                    List listaDistrito = ubigeo.getListDistrito(mapUbigeo);

                    HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();
                    mapListas.put("listaProvincia", listaProvincia);
                    mapListas.put("listaDistrito", listaDistrito);
                    session.setAttribute("mapListas", mapListas);
                }

                HashMap mapUbigeo = new HashMap();
                com.bmp.sigeca.mantenimiento.service.UbigeoService ubigeoService = new com.bmp.sigeca.mantenimiento.service.UbigeoService();
                mapUbigeo = ubigeoService.obtenerUbigeoActivo();

                mapUbigeo.put("id_ubi_geo", (String)mapUbigeo.get("codigoUbigeo"));

                mapFicha.put("mapUbigeo", mapUbigeo);
                session.setAttribute("codTipFicha", Properties.FichaCatastralUrbanaActividadEconomica);
                session.setAttribute("accion", (String)map.get("accion"));
                destino = "fichaActividadEconomica";
            }else{
                destino = "busqueda";
            }
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que obtiene una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward obtenerFichaBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en InicioAction metodo obtenerFichaBienComun");

        HashMap mapFicha = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            List listaFicha = session.getAttribute("listaFicha")!=null?(List)session.getAttribute("listaFicha"):new ArrayList();
            int i = Integer.parseInt((String)map.get("indListaFicha"));

            if(listaFicha!=null && !listaFicha.isEmpty()){
                FichaBienComunService fichaService = new FichaBienComunService();
                mapFicha = fichaService.obtenerFicha((HashMap)listaFicha.get(i));
                mapFicha.put("flagActualizar", "true");
                session.removeAttribute("mapListas");
                Properties.cargarListasFichaBienComun(request,response);
                mapFicha.put("numFicha", Properties.concatLeftCharacter("0", 7, (String)mapFicha.get("numFicha")));

                HashMap mapUbigeo = new HashMap();
                com.bmp.sigeca.mantenimiento.service.UbigeoService ubigeoService = new com.bmp.sigeca.mantenimiento.service.UbigeoService();
                mapUbigeo = ubigeoService.obtenerUbigeoActivo();

                mapUbigeo.put("id_ubi_geo", (String)mapUbigeo.get("codigoUbigeo"));

                mapFicha.put("mapUbigeo", mapUbigeo);
                session.setAttribute("codTipFicha", Properties.FichaCatastralUrbanaBienesComunes);
                session.setAttribute("accion", (String)map.get("accion"));
                destino = "fichaBienComun";
            }else{
                destino = "busqueda";
            }
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
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
        HashMap mapBusqueda = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            mapBusqueda = new HashMap();
            mapBusqueda.put("cuc", StringUtil.nullAsEmptyString((String)map.get("cuc")));
            mapBusqueda.put("numFicha", StringUtil.nullAsEmptyString((String)map.get("numFicha")));
            mapBusqueda.put("codHojCatastral", StringUtil.nullAsEmptyString((String)map.get("codHojCatastral")));
            mapBusqueda.put("codRefCatastral", StringUtil.nullAsEmptyString((String)map.get("codRefCatastral")));
            mapBusqueda.put("codEstado", StringUtil.nullAsEmptyString((String)map.get("codEstado")));
            mapBusqueda.put("dniTecCatastral", StringUtil.nullAsEmptyString((String)map.get("dniTecCatastral")));
            mapBusqueda.put("nomTecCatastral", StringUtil.nullAsEmptyString((String)map.get("nomTecCatastral")));

            FichaService fichaService = new FichaService();
            lista = fichaService.reporteFichas(mapBusqueda);

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
        HashMap mapBusqueda = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            mapBusqueda = new HashMap();
            mapBusqueda.put("cuc", StringUtil.nullAsEmptyString((String)map.get("cuc")));
            mapBusqueda.put("numFicha", StringUtil.nullAsEmptyString((String)map.get("numFicha")));
            mapBusqueda.put("dniTitular", StringUtil.nullAsEmptyString((String)map.get("dniTitular")));
            mapBusqueda.put("nomTitular", StringUtil.nullAsEmptyString((String)map.get("nomTitular")));

            FichaService fichaService = new FichaService();
            lista = fichaService.reporteFichas(mapBusqueda);

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
        logg.debug(" Log en InicioAction metodo irBusqueda");

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
}