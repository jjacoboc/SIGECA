/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.action;

import com.bmp.sigeca.registro.service.FichaActividadEconomicaService;
import com.bmp.sigeca.maestro.service.UbigeoService;
import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.registro.service.ActividadService;
import com.bmp.sigeca.registro.service.AnuncioService;
import com.bmp.sigeca.registro.service.FichaService;
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
 * @author Administrador
 */
public class FichaActividadEconomicaAction extends DispatchAction {

    protected Logger logg;

    /**
     * Método que registra una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward guardarFicha(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo guardarFicha");

        String mensaje = null;
        try{
            HttpSession session = request.getSession();
            UsuarioBean usuarioBean = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):null;
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            map.put("cuc", ((String)map.get("cucA")).concat((String)map.get("cucB")));
            map.put("codEstado", Properties.EstadoRegistrado);
            map.put("activo", Properties.Activa);

            map.put("id_usuario", usuarioBean.getCodUsuario());
            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            mapFicha.putAll(setearValores(mapFicha,map));
            mapFicha.putAll(map);
            mapFicha.put("codTipFicha", Properties.FichaCatastralUrbanaActividadEconomica);

            if(Properties.existeUbigeoDistrital(mapFicha)){
                FichaActividadEconomicaService fichaService = new FichaActividadEconomicaService();
                //if(!fichaService.existeCUC(mapFicha)){
                    long result = fichaService.guardarFicha(mapFicha);
                    if(result==1){
                        mensaje = "Ficha registrada con éxito.";
                        mapFicha.put("flagOK", true);
                        mapFicha.put("flagActualizar", true);
                    }else{
                        mensaje = "Hubo un error al registrar la ficha./nComuníquese con el administrador de red para mayor información.";
                    }
                /*}else{
                    mensaje = "El CUC ingresado ya existe.";
                    mapFicha.remove("flagActualizar");
                }*/
            }else{
                mensaje = "Código de Referencia Catastral incorrecto - verificar ubigeo";
            }

            session.setAttribute("mapFicha", mapFicha);
            request.setAttribute("mensaje", mensaje);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que carga la lista de provincias de un departamento dado.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarProvincia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo cargarProvincia");

        List listaProvincia = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            HashMap mapUbigeo = new HashMap();
            mapUbigeo.put("codDepartamento", (String)map.get("codDepartamento"));
            UbigeoService ubigeo = new UbigeoService();
            listaProvincia = ubigeo.getListProvincia(mapUbigeo);

            mapListas.put("listaProvincia", listaProvincia);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que carga la lista de distritos de una provincia dada.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarDistrito(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo cargarDistrito");

        List listaDistrito = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            HashMap mapUbigeo = new HashMap();
            mapUbigeo.put("codDepartamento", (String)map.get("codDepartamento"));
            mapUbigeo.put("codProvincia", (String)map.get("codProvincia"));
            UbigeoService ubigeo = new UbigeoService();
            listaDistrito = ubigeo.getListDistrito(mapUbigeo);

            mapListas.put("listaDistrito", listaDistrito);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Autorización de Anuncios.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarAnuncio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo irAgregarAnuncio");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("anuncio");
    }

    /**
     * Método que agrega un anuncio a la lista de autorizaciones de anuncios para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarAnuncio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo agregarAnuncio");

        List listaAnuncio = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaAnuncio") && mapFicha.get("listaAnuncio")!=null ){
                listaAnuncio = (List)mapFicha.get("listaAnuncio");
            }else{
                listaAnuncio = new ArrayList();
            }

            HashMap anuncio = new HashMap();
            anuncio.put("codTipAnuncio", ((String)map.get("codTipAnuncio")).trim());
            anuncio.put("desTipAnuncio", ((String)map.get("desTipAnuncio")).trim());
            anuncio.put("numLados", ((String)map.get("numLados")).trim());
            anuncio.put("areAutorizada", ((String)map.get("areAutorizada")).trim());
            anuncio.put("areVerificada", ((String)map.get("areVerificada")).trim());
            anuncio.put("numExpAnuncio", ((String)map.get("numExpAnuncio")).trim());
            anuncio.put("numLicAnuncio", ((String)map.get("numLicAnuncio")).trim());
            anuncio.put("fecExpedicion", ((String)map.get("fecExpedicion")).trim());
            anuncio.put("fecVencimiento", ((String)map.get("fecVencimiento")).trim());

            listaAnuncio.add(anuncio);

            mapFicha.put("listaAnuncio", listaAnuncio);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que elimina un anuncio de la lista de autorizaciones de anuncio.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarAnuncio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo eliminarAnuncio");

        List listaAnuncio = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaAnuncio") && mapFicha.get("listaAnuncio")!=null ){
                listaAnuncio = (List)mapFicha.get("listaAnuncio");
            }else{
                listaAnuncio = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaAnuncio"));
            HashMap mapAnuncio = (HashMap)listaAnuncio.get(ind);
            AnuncioService anuncio = new AnuncioService();
            anuncio.eliminarAnuncio(mapAnuncio);
            listaAnuncio.remove(ind);

            mapFicha.put("listaAnuncio", listaAnuncio);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Autorización de Anuncios.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarAnuncio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo irEditarAnuncio");

        List listaAnuncio = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaAnuncio") && mapFicha.get("listaAnuncio")!=null ){
                listaAnuncio = (List)mapFicha.get("listaAnuncio");
            }else{
                listaAnuncio = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaAnuncio"));
            HashMap anuncio = (HashMap)listaAnuncio.get(ind);

            request.setAttribute("anuncioBean", anuncio);
            request.setAttribute("indListaAnuncio", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("anuncio");
    }

    /**
     * Método que edita un anuncio de la lista de autorizaciones de anuncio para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarAnuncio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo editarAnuncio");

        List listaAnuncio = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaAnuncio") && mapFicha.get("listaAnuncio")!=null ){
                listaAnuncio = (List)mapFicha.get("listaAnuncio");
            }else{
                listaAnuncio = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaAnuncio"));

            HashMap anuncio = (HashMap)listaAnuncio.get(ind);
            anuncio.put("codTipAnuncio", ((String)map.get("codTipAnuncio")).trim());
            anuncio.put("desTipAnuncio", ((String)map.get("desTipAnuncio")).trim());
            anuncio.put("numLados", ((String)map.get("numLados")).trim());
            anuncio.put("areAutorizada", ((String)map.get("areAutorizada")).trim());
            anuncio.put("areVerificada", ((String)map.get("areVerificada")).trim());
            anuncio.put("numExpAnuncio", ((String)map.get("numExpAnuncio")).trim());
            anuncio.put("numLicAnuncio", ((String)map.get("numLicAnuncio")).trim());
            anuncio.put("fecExpedicion", ((String)map.get("fecExpedicion")).trim());
            anuncio.put("fecVencimiento", ((String)map.get("fecVencimiento")).trim());


            listaAnuncio.set(ind, anuncio);

            mapFicha.put("listaAnuncio", listaAnuncio);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Actividades.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarActividad(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo irAgregarActividad");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("actividad");
    }

    /**
     * Método que agrega una actividad a la lista de actividades para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarActividad(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo agregarActividad");

        List listaActividad = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaActividad") && mapFicha.get("listaActividad")!=null ){
                listaActividad = (List)mapFicha.get("listaActividad");
            }else{
                listaActividad = new ArrayList();
            }

            HashMap actividad = new HashMap();
            actividad.put("codActividad", ((String)map.get("codActividad")).trim());
            actividad.put("desActividad", ((String)map.get("desActividad")).trim());

            listaActividad.add(actividad);

            mapFicha.put("listaActividad", listaActividad);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que elimina una actividad de la lista de actividades.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarActividad(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo eliminarActividad");

        List listaActividad = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaActividad") && mapFicha.get("listaActividad")!=null ){
                listaActividad = (List)mapFicha.get("listaActividad");
            }else{
                listaActividad = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaActividad"));
            HashMap mapActividad = (HashMap)listaActividad.get(ind);
            ActividadService actividad = new ActividadService();
            actividad.eliminarActividad(mapActividad);
            listaActividad.remove(ind);

            mapFicha.put("listaActividad", listaActividad);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Actividades.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarActividad(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo irEditarActividad");

        List listaActividad = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaActividad") && mapFicha.get("listaActividad")!=null ){
                listaActividad = (List)mapFicha.get("listaActividad");
            }else{
                listaActividad = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaActividad"));
            HashMap actividad = (HashMap)listaActividad.get(ind);

            request.setAttribute("actividadBean", actividad);
            request.setAttribute("indListaActividad", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("actividad");
    }

    /**
     * Método que edita una actividad de la lista de actividades para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarActividad(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo editarActividad");

        List listaActividad = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaActividad") && mapFicha.get("listaActividad")!=null ){
                listaActividad = (List)mapFicha.get("listaActividad");
            }else{
                listaActividad = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaActividad"));

            HashMap actividad = (HashMap)listaActividad.get(ind);
            actividad.put("codActividad", ((String)map.get("codActividad")).trim());
            actividad.put("desActividad", ((String)map.get("desActividad")).trim());


            listaActividad.set(ind, actividad);

            mapFicha.put("listaActividad", listaActividad);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
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
        logg.debug(" Log en FichaActividadEconomicaAction metodo irBusqueda");

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

    /**
     * Método que deriva a la página de Búsqueda.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irFichaActividadEconomica(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo irFichaActividadEconomica");

        try{

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaActividadEconomica");
    }

    /**
     * Método que setea los valores del request en mapas.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public HashMap setearValores(HashMap mapFicha,HashMap map) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaActividadEconomicaAction metodo setearValores");

        HashMap mapCabecera = null;
        HashMap mapConductor = null;
        HashMap mapFuncionamiento = null;
        HashMap mapInformacion = null;

        try{
            mapFicha = mapFicha!=null?mapFicha:new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapFicha.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("cucA") && map.get("cucA")!=null && !"".equals((String)map.get("cucA"))
                    && map.containsKey("cucB") && map.get("cucB")!=null && !"".equals((String)map.get("cucB")))
                mapFicha.put("cuc", ((String)map.get("cucA")).concat((String)map.get("cucB")));
            if(map.containsKey("numFichLote") && map.get("numFichLote")!=null && !"".equals((String)map.get("numFichLote")))
                mapFicha.put("numFichLote", (String)map.get("numFichLote"));
            if(map.containsKey("numTotFichLote") && map.get("numTotFichLote")!=null && !"".equals((String)map.get("numTotFichLote")))
                mapFicha.put("numTotFichLote", (String)map.get("numTotFichLote"));
            if(map.containsKey("codEvaPreCatastral") && map.get("codEvaPreCatastral")!=null && !"".equals((String)map.get("codEvaPreCatastral")))
                mapFicha.put("codEvaPreCatastral", (String)map.get("codEvaPreCatastral"));
            if(map.containsKey("areTerInvLotColindante") && map.get("areTerInvLotColindante")!=null && !"".equals((String)map.get("areTerInvLotColindante")))
                mapFicha.put("areTerInvLotColindante", (String)map.get("areTerInvLotColindante"));
            if(map.containsKey("areTerInvJarAislamiento") && map.get("areTerInvJarAislamiento")!=null && !"".equals((String)map.get("areTerInvJarAislamiento")))
                mapFicha.put("areTerInvJarAislamiento", (String)map.get("areTerInvJarAislamiento"));
            if(map.containsKey("areTerInvArePublica") && map.get("areTerInvArePublica")!=null && !"".equals((String)map.get("areTerInvArePublica")))
                mapFicha.put("areTerInvArePublica", (String)map.get("areTerInvArePublica"));
            if(map.containsKey("areTerInvAreIntangible") && map.get("areTerInvAreIntangible")!=null && !"".equals((String)map.get("areTerInvAreIntangible")))
                mapFicha.put("areTerInvAreIntangible", (String)map.get("areTerInvAreIntangible"));
            if(map.containsKey("observacion") && map.get("observacion")!=null && !"".equals((String)map.get("observacion")))
                mapFicha.put("observacion", (String)map.get("observacion"));
            if(map.containsKey("dniDeclarante") && map.get("dniDeclarante")!=null && !"".equals((String)map.get("dniDeclarante")))
                mapFicha.put("dniDeclarante", (String)map.get("dniDeclarante"));
            if(map.containsKey("nomDeclarante") && map.get("nomDeclarante")!=null && !"".equals((String)map.get("nomDeclarante")))
                mapFicha.put("nomDeclarante", (String)map.get("nomDeclarante"));
            if(map.containsKey("apeDeclarante") && map.get("apeDeclarante")!=null && !"".equals((String)map.get("apeDeclarante")))
                mapFicha.put("apeDeclarante", (String)map.get("apeDeclarante"));
            if(map.containsKey("fecFirDeclarante") && map.get("fecFirDeclarante")!=null && !"".equals((String)map.get("fecFirDeclarante")))
                mapFicha.put("fecFirDeclarante", (String)map.get("fecFirDeclarante"));
            if(map.containsKey("dniSupervisor") && map.get("dniSupervisor")!=null && !"".equals((String)map.get("dniSupervisor")))
                mapFicha.put("dniSupervisor", (String)map.get("dniSupervisor"));
            if(map.containsKey("nomSupervisor") && map.get("nomSupervisor")!=null && !"".equals((String)map.get("nomSupervisor")))
                mapFicha.put("nomSupervisor", (String)map.get("nomSupervisor"));
            if(map.containsKey("apeSupervisor") && map.get("apeSupervisor")!=null && !"".equals((String)map.get("apeSupervisor")))
                mapFicha.put("apeSupervisor", (String)map.get("apeSupervisor"));
            if(map.containsKey("fecFirSupervisor") && map.get("fecFirSupervisor")!=null && !"".equals((String)map.get("fecFirSupervisor")))
                mapFicha.put("fecFirSupervisor", (String)map.get("fecFirSupervisor"));
            if(map.containsKey("dniTecCatastral") && map.get("dniTecCatastral")!=null && !"".equals((String)map.get("dniTecCatastral")))
                mapFicha.put("dniTecCatastral", (String)map.get("dniTecCatastral"));
            if(map.containsKey("nomTecCatastral") && map.get("nomTecCatastral")!=null && !"".equals((String)map.get("nomTecCatastral")))
                mapFicha.put("nomTecCatastral", (String)map.get("nomTecCatastral"));
            if(map.containsKey("apeTecCatastral") && map.get("apeTecCatastral")!=null && !"".equals((String)map.get("apeTecCatastral")))
                mapFicha.put("apeTecCatastral", (String)map.get("apeTecCatastral"));
            if(map.containsKey("fecFirTecCatastral") && map.get("fecFirTecCatastral")!=null && !"".equals((String)map.get("fecFirTecCatastral")))
                mapFicha.put("fecFirTecCatastral", (String)map.get("fecFirTecCatastral"));
            if(map.containsKey("dniVerCatastral") && map.get("dniVerCatastral")!=null && !"".equals((String)map.get("dniVerCatastral")))
                mapFicha.put("dniVerCatastral", (String)map.get("dniVerCatastral"));
            if(map.containsKey("numRegVerCatastral") && map.get("numRegVerCatastral")!=null && !"".equals((String)map.get("numRegVerCatastral")))
                mapFicha.put("numRegVerCatastral", (String)map.get("numRegVerCatastral"));
            if(map.containsKey("nomVerCatastral") && map.get("nomVerCatastral")!=null && !"".equals((String)map.get("nomVerCatastral")))
                mapFicha.put("nomVerCatastral", (String)map.get("nomVerCatastral"));
            if(map.containsKey("apeVerCatastral") && map.get("apeVerCatastral")!=null && !"".equals((String)map.get("apeVerCatastral")))
                mapFicha.put("apeVerCatastral", (String)map.get("apeVerCatastral"));
            if(map.containsKey("fecFirVerCatastral") && map.get("fecFirVerCatastral")!=null && !"".equals((String)map.get("fecFirVerCatastral")))
                mapFicha.put("fecFirVerCatastral", (String)map.get("fecFirVerCatastral"));

            mapCabecera = mapFicha.get("mapCabecera")!=null?(HashMap)mapFicha.get("mapCabecera"):new HashMap();
            if(map.containsKey("cucA") && map.get("cucA")!=null && !"".equals((String)map.get("cucA")))
                mapCabecera.put("cucA", ((String)map.get("cucA")));
            if(map.containsKey("cucB") && map.get("cucB")!=null && !"".equals((String)map.get("cucB")))
                mapCabecera.put("cucB", ((String)map.get("cucB")));
            if(map.containsKey("cucA") && map.get("cucA")!=null && !"".equals((String)map.get("cucA"))
                    && map.containsKey("cucB") && map.get("cucB")!=null && !"".equals((String)map.get("cucB")))
                mapCabecera.put("cuc", ((String)map.get("cucA")).concat((String)map.get("cucB")));
            if(map.containsKey("codHojCatastral") && map.get("codHojCatastral")!=null && !"".equals((String)map.get("codHojCatastral")))
                mapCabecera.put("codHojCatastral", (String)map.get("codHojCatastral"));
            if(map.containsKey("codRefCatastral") && map.get("codRefCatastral")!=null && !"".equals((String)map.get("codRefCatastral")))
                mapCabecera.put("codRefCatastral", (String)map.get("codRefCatastral"));
            if(map.containsKey("dpto") && map.get("dpto")!=null && !"".equals((String)map.get("dpto")))
                mapCabecera.put("codDepartamento", (String)map.get("dpto"));
            if(map.containsKey("prov") && map.get("prov")!=null && !"".equals((String)map.get("prov")))
                mapCabecera.put("codProvincia", (String)map.get("prov"));
            if(map.containsKey("dist") && map.get("dist")!=null && !"".equals((String)map.get("dist")))
                mapCabecera.put("codDistrito", (String)map.get("dist"));
            if(map.containsKey("sector") && map.get("sector")!=null && !"".equals((String)map.get("sector")))
                mapCabecera.put("sector", (String)map.get("sector"));
            if(map.containsKey("manzana") && map.get("manzana")!=null && !"".equals((String)map.get("manzana")))
                mapCabecera.put("manzana", (String)map.get("manzana"));
            if(map.containsKey("lote") && map.get("lote")!=null && !"".equals((String)map.get("lote")))
                mapCabecera.put("lote", (String)map.get("lote"));
            if(map.containsKey("edifica") && map.get("edifica")!=null && !"".equals((String)map.get("edifica")))
                mapCabecera.put("edifica", (String)map.get("edifica"));
            if(map.containsKey("piso") && map.get("piso")!=null && !"".equals((String)map.get("piso")))
                mapCabecera.put("piso", (String)map.get("piso"));
            if(map.containsKey("entrada") && map.get("entrada")!=null && !"".equals((String)map.get("entrada")))
                mapCabecera.put("entrada", (String)map.get("entrada"));
            if(map.containsKey("unidad") && map.get("unidad")!=null && !"".equals((String)map.get("unidad")))
                mapCabecera.put("unidad", (String)map.get("unidad"));
            if(map.containsKey("dc") && map.get("dc")!=null && !"".equals((String)map.get("dc")))
                mapCabecera.put("dc", (String)map.get("dc"));
            if(map.containsKey("codConRenta") && map.get("codConRenta")!=null && !"".equals((String)map.get("codConRenta")))
                mapCabecera.put("codConRenta", (String)map.get("codConRenta"));
            if(map.containsKey("codPreRenta") && map.get("codPreRenta")!=null && !"".equals((String)map.get("codPreRenta")))
                mapCabecera.put("codPreRenta", (String)map.get("codPreRenta"));
            if(map.containsKey("uniAcuCodPreRenta") && map.get("uniAcuCodPreRenta")!=null && !"".equals((String)map.get("uniAcuCodPreRenta")))
                mapCabecera.put("uniAcuCodPreRenta", (String)map.get("uniAcuCodPreRenta"));
            if(map.containsKey("zona") && map.get("zona")!=null && !"".equals((String)map.get("zona")))
                mapCabecera.put("zona", (String)map.get("zona"));
            if(map.containsKey("uniOrgCatRural") && map.get("uniOrgCatRural")!=null && !"".equals((String)map.get("uniOrgCatRural")))
                mapCabecera.put("uniOrgCatRural", (String)map.get("uniOrgCatRural"));
            if(map.containsKey("uniCatastral") && map.get("uniCatastral")!=null && !"".equals((String)map.get("uniCatastral")))
                mapCabecera.put("uniCatastral", (String)map.get("uniCatastral"));
            if(map.containsKey("coorEste") && map.get("coorEste")!=null && !"".equals((String)map.get("coorEste")))
                mapCabecera.put("coorEste", (String)map.get("coorEste"));
            if(map.containsKey("coorNorte") && map.get("coorNorte")!=null && !"".equals((String)map.get("coorNorte")))
                mapCabecera.put("coorNorte", (String)map.get("coorNorte"));
            String codRefCatastral = "";
            codRefCatastral.concat((String)map.get("dpto")).concat((String)map.get("prov")).concat((String)map.get("dist"));
            codRefCatastral.concat((String)map.get("sector")).concat((String)map.get("manzana")).concat((String)map.get("lote"));
            codRefCatastral.concat((String)map.get("edifica")).concat((String)map.get("entrada")).concat((String)map.get("piso"));
            codRefCatastral.concat((String)map.get("unidad")).concat((String)map.get("dc"));
            mapCabecera.put("codRefCatastral", codRefCatastral);


            mapConductor = mapFicha.get("mapConductor")!=null?(HashMap)mapFicha.get("mapConductor"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapConductor.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codTipTitular") && map.get("codTipTitular")!=null && !"".equals((String)map.get("codTipTitular")))
                mapConductor.put("codTipTitular", (String)map.get("codTipTitular"));
            if(map.containsKey("nomComercial") && map.get("nomComercial")!=null && !"".equals((String)map.get("nomComercial")))
                mapConductor.put("nomComercial", (String)map.get("nomComercial"));
            if(map.containsKey("codTipDocIdentidad") && map.get("codTipDocIdentidad")!=null && !"".equals((String)map.get("codTipDocIdentidad")))
                mapConductor.put("codTipDocIdentidad", (String)map.get("codTipDocIdentidad"));
            if(map.containsKey("numDocIdentidad") && map.get("numDocIdentidad")!=null && !"".equals((String)map.get("numDocIdentidad")))
                mapConductor.put("numDocIdentidad", (String)map.get("numDocIdentidad"));
            if(map.containsKey("nombre") && map.get("nombre")!=null && !"".equals((String)map.get("nombre")))
                mapConductor.put("nombre", (String)map.get("nombre"));
            if(map.containsKey("apePaterno") && map.get("apePaterno")!=null && !"".equals((String)map.get("apePaterno")))
                mapConductor.put("apePaterno", (String)map.get("apePaterno"));
            if(map.containsKey("apeMaterno") && map.get("apeMaterno")!=null && !"".equals((String)map.get("apeMaterno")))
                mapConductor.put("apeMaterno", (String)map.get("apeMaterno"));
            if(map.containsKey("numRuc") && map.get("numRuc")!=null && !"".equals((String)map.get("numRuc")))
                mapConductor.put("numRuc", (String)map.get("numRuc"));
            if(map.containsKey("razSocial") && map.get("razSocial")!=null && !"".equals((String)map.get("razSocial")))
                mapConductor.put("razSocial", (String)map.get("razSocial"));
            if(map.containsKey("codConConductor") && map.get("codConConductor")!=null && !"".equals((String)map.get("codConConductor")))
                mapConductor.put("codConConductor", (String)map.get("codConConductor"));
            if(map.containsKey("codDepartamento") && map.get("codDepartamento")!=null && !"".equals((String)map.get("codDepartamento")))
                mapConductor.put("codDepartamento", (String)map.get("codDepartamento"));
            if(map.containsKey("codProvincia") && map.get("codProvincia")!=null && !"".equals((String)map.get("codProvincia")))
                mapConductor.put("codProvincia", (String)map.get("codProvincia"));
            if(map.containsKey("codDistrito") && map.get("codDistrito")!=null && !"".equals((String)map.get("codDistrito")))
                mapConductor.put("codDistrito", (String)map.get("codDistrito"));
            if(map.containsKey("telefonoTit") && map.get("telefonoTit")!=null && !"".equals((String)map.get("telefonoTit")))
                mapConductor.put("telefono", (String)map.get("telefonoTit"));
            if(map.containsKey("anexoTit") && map.get("anexoTit")!=null && !"".equals((String)map.get("anexoTit")))
                mapConductor.put("anexo", (String)map.get("anexoTit"));
            if(map.containsKey("faxTit") && map.get("faxTit")!=null && !"".equals((String)map.get("faxTit")))
                mapConductor.put("fax", (String)map.get("faxTit"));
            if(map.containsKey("correoTit") && map.get("correoTit")!=null && !"".equals((String)map.get("correoTit")))
                mapConductor.put("corElectronico", (String)map.get("correoTit"));
            if(map.containsKey("codViaTit") && map.get("codViaTit")!=null && !"".equals((String)map.get("codViaTit")))
                mapConductor.put("codVia", (String)map.get("codViaTit"));
            if(map.containsKey("codTipViaTit") && map.get("codTipViaTit")!=null && !"".equals((String)map.get("codTipViaTit")))
                mapConductor.put("codTipVia", (String)map.get("codTipViaTit"));
            if(map.containsKey("nomViaTit") && map.get("nomViaTit")!=null && !"".equals((String)map.get("nomViaTit")))
                mapConductor.put("nomVia", (String)map.get("nomViaTit"));
            if(map.containsKey("numMunicipalTit") && map.get("numMunicipalTit")!=null && !"".equals((String)map.get("numMunicipalTit")))
                mapConductor.put("numMunicipal", (String)map.get("numMunicipalTit"));
            if(map.containsKey("nomEdificacionTit") && map.get("nomEdificacionTit")!=null && !"".equals((String)map.get("nomEdificacionTit")))
                mapConductor.put("nomEdificacion", (String)map.get("nomEdificacionTit"));
            if(map.containsKey("numInteriorTit") && map.get("numInteriorTit")!=null && !"".equals((String)map.get("numInteriorTit")))
                mapConductor.put("numInterior", (String)map.get("numInteriorTit"));
            if(map.containsKey("codHUTit") && map.get("codHUTit")!=null && !"".equals((String)map.get("codHUTit")))
                mapConductor.put("codHabUrbana", (String)map.get("codHUTit"));
            if(map.containsKey("nomHUTit") && map.get("nomHUTit")!=null && !"".equals((String)map.get("nomHUTit")))
                mapConductor.put("nomHabUrbana", (String)map.get("nomHUTit"));
            if(map.containsKey("sectorTit") && map.get("sectorTit")!=null && !"".equals((String)map.get("sectorTit")))
                mapConductor.put("sector", (String)map.get("sectorTit"));
            if(map.containsKey("manzanaTit") && map.get("manzanaTit")!=null && !"".equals((String)map.get("manzanaTit")))
                mapConductor.put("manzana", (String)map.get("manzanaTit"));
            if(map.containsKey("loteTit") && map.get("loteTit")!=null && !"".equals((String)map.get("loteTit")))
                mapConductor.put("lote", (String)map.get("loteTit"));
            if(map.containsKey("subloteTit") && map.get("subloteTit")!=null && !"".equals((String)map.get("subloteTit")))
                mapConductor.put("sublote", (String)map.get("subloteTit"));

            mapFuncionamiento = mapFicha.get("mapFuncionamiento")!=null?(HashMap)mapFicha.get("mapFuncionamiento"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapFuncionamiento.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("numExpediente") && map.get("numExpediente")!=null && !"".equals((String)map.get("numExpediente")))
                mapFuncionamiento.put("numExpediente", (String)map.get("numExpediente"));
            if(map.containsKey("numLicencia") && map.get("numLicencia")!=null && !"".equals((String)map.get("numLicencia")))
                mapFuncionamiento.put("numLicencia", (String)map.get("numLicencia"));
            if(map.containsKey("areAutPreCatastral") && map.get("areAutPreCatastral")!=null && !"".equals((String)map.get("areAutPreCatastral")))
                mapFuncionamiento.put("areAutPreCatastral", (String)map.get("areAutPreCatastral"));
            if(map.containsKey("areAutViaPublica") && map.get("areAutViaPublica")!=null && !"".equals((String)map.get("areAutViaPublica")))
                mapFuncionamiento.put("areAutViaPublica", (String)map.get("areAutViaPublica"));
            if(map.containsKey("areAutBienComun") && map.get("areAutBienComun")!=null && !"".equals((String)map.get("areAutBienComun")))
                mapFuncionamiento.put("areAutBienComun", (String)map.get("areAutBienComun"));
            if(map.containsKey("areAutTotal") && map.get("areAutTotal")!=null && !"".equals((String)map.get("areAutTotal")))
                mapFuncionamiento.put("areAutTotal", (String)map.get("areAutTotal"));
            if(map.containsKey("areVerPreCatastral") && map.get("areVerPreCatastral")!=null && !"".equals((String)map.get("areVerPreCatastral")))
                mapFuncionamiento.put("areVerPreCatastral", (String)map.get("areVerPreCatastral"));
            if(map.containsKey("areVerViaPublica") && map.get("areVerViaPublica")!=null && !"".equals((String)map.get("areVerViaPublica")))
                mapFuncionamiento.put("areVerViaPublica", (String)map.get("areVerViaPublica"));
            if(map.containsKey("areVerBienComun") && map.get("areVerBienComun")!=null && !"".equals((String)map.get("areVerBienComun")))
                mapFuncionamiento.put("areVerBienComun", (String)map.get("areVerBienComun"));
            if(map.containsKey("areVerTotal") && map.get("areVerTotal")!=null && !"".equals((String)map.get("areVerTotal")))
                mapFuncionamiento.put("areVerTotal", (String)map.get("areVerTotal"));
            if(map.containsKey("fecExpAutorizacion") && map.get("fecExpAutorizacion")!=null && !"".equals((String)map.get("fecExpAutorizacion")))
                mapFuncionamiento.put("fecExpAutorizacion", (String)map.get("fecExpAutorizacion"));
            if(map.containsKey("fecVenAutorizacion") && map.get("fecVenAutorizacion")!=null && !"".equals((String)map.get("fecVenAutorizacion")))
                mapFuncionamiento.put("fecVenAutorizacion", (String)map.get("fecVenAutorizacion"));
            if(map.containsKey("fecIniActividad") && map.get("fecIniActividad")!=null && !"".equals((String)map.get("fecIniActividad")))
                mapFuncionamiento.put("fecIniActividad", (String)map.get("fecIniActividad"));

            mapInformacion = mapFicha.get("mapInformacion")!=null?(HashMap)mapFicha.get("mapInformacion"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapInformacion.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codConDeclarante") && map.get("codConDeclarante")!=null && !"".equals((String)map.get("codConDeclarante")))
                mapInformacion.put("codConDeclarante", (String)map.get("codConDeclarante"));
            if(map.containsKey("codEstLleFicha") && map.get("codEstLleFicha")!=null && !"".equals((String)map.get("codEstLleFicha")))
                mapInformacion.put("codEstLleFicha", (String)map.get("codEstLleFicha"));
            if(map.containsKey("codDocPresentado") && map.get("codDocPresentado")!=null && !"".equals((String)map.get("codDocPresentado")))
                mapInformacion.put("codDocPresentado", (String)map.get("codDocPresentado"));
            if(map.containsKey("codMantenimiento") && map.get("codMantenimiento")!=null && !"".equals((String)map.get("codMantenimiento")))
                mapInformacion.put("codMantenimiento", (String)map.get("codMantenimiento"));

            mapFicha.put("mapCabecera", mapCabecera);
            mapFicha.put("mapConductor", mapConductor);
            mapFicha.put("mapFuncionamiento", mapFuncionamiento);
            mapFicha.put("mapInformacion", mapInformacion);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return mapFicha;
    }
}
