/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.action;

import com.bmp.sigeca.maestro.service.ECCService;
import com.bmp.sigeca.maestro.service.ECSService;
import com.bmp.sigeca.registro.service.FichaBienComunService;
import com.bmp.sigeca.maestro.service.InstalacionService;
import com.bmp.sigeca.maestro.service.MEPService;
import com.bmp.sigeca.maestro.service.UCAService;
import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.registro.service.ConstruccionService;
import com.bmp.sigeca.registro.service.FichaService;
import com.bmp.sigeca.registro.service.ObrComplementariaService;
import com.bmp.sigeca.registro.service.ViaService;
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
public class FichaBienComunAction extends DispatchAction {

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
        logg.debug(" Log en FichaBienComunAction metodo guardarFicha");

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
            mapFicha.put("codTipFicha", Properties.FichaCatastralUrbanaBienesComunes);

            if(Properties.existeUbigeoDistrital(mapFicha)){
                FichaBienComunService fichaService = new FichaBienComunService();
                if(map.get("codUsoPreCatastral")!=null && !"".equals((String)map.get("codUsoPreCatastral"))){
                    if(map.get("nomNotaria")!=null && !"".equals((String)map.get("nomNotaria"))){
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
                        mensaje = "El CUC ingresado no se encuentra dentro del rango asignado.";
                        mapFicha.remove("flagActualizar");
                    }*/
                    }else{
                        mensaje = "Debe seleccionar una Notaria.";
                    }
                }else{
                    mensaje = "Debe seleccionar un Uso de Predio Catastral.";
                }
            }else{
                mensaje = "Código de Referencia Catastral incorrecto - verificar ubigeo";
            }

            session.setAttribute("mapFicha", mapFicha);
            request.setAttribute("mensaje", mensaje);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que actualiza una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
//    public ActionForward actualizarFicha(ActionMapping actionMapping,
//        ActionForm actionForm,
//        HttpServletRequest request,
//        HttpServletResponse response) throws java.lang.Exception {
//
//        logg = Logger.getLogger(this.getClass());
//        logg.debug(" Log en FichaBienComunAction metodo actualizarFicha");
//
//        String mensaje = null;
//        try{
//            HttpSession session = request.getSession();
//            DynaActionForm form = (DynaActionForm)actionForm;
//            HashMap map = (HashMap)form.getMap();
//
//            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
//
//            mapFicha.putAll(setearValores(mapFicha,map));
//            mapFicha.put("codTipFicha", Properties.FichaCatastralUrbanaBienesComunes);
//
//            if(Properties.existeUbigeoDistrital(mapFicha)){
//                FichaBienComunService fichaService = new FichaBienComunService();
//                if(fichaService.existeCUC(mapFicha)){
//                    mapFicha.put("codEstado", Properties.EstadoActualizado);
//                    fichaService.actualizarFicha(mapFicha);
//                    mensaje = "Ficha actualizada con éxito.";
//                    mapFicha.put("flagActualizar", "true");
//                }else{
//                    mensaje = "Hubo un error al actualizar la ficha catastral.";
//                }
//            }else{
//                mensaje = "Código de Referencia Catastral incorrecto - verificar ubigeo";
//            }
//
//            session.setAttribute("mapFicha", mapFicha);
//            request.setAttribute("mensaje", mensaje);
//
//        }catch(Exception e){
//            e.getMessage();
//            e.printStackTrace();
//        }
//        return actionMapping.findForward("fichaBienComun");
//    }

    
    /**
     * Método que deriva a la página de Mantenimiento de Vía.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irAgregarVia");

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

        return actionMapping.findForward("via");
    }

    /**
     * Método que agrega un vía a la lista de vías para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo agregarVia");

        List listaVia = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaVia") && mapFicha.get("listaVia")!=null ){
                listaVia = (List)mapFicha.get("listaVia");
            }else{
                listaVia = new ArrayList();
            }

            HashMap mapVia = new HashMap();
            mapVia.put("codVia", ((String)map.get("codVia")).trim());
            mapVia.put("codTipVia", ((String)map.get("codTipVia")).trim());
            mapVia.put("desTipVia", ((String)map.get("desTipVia")).trim());
            mapVia.put("nomVia", ((String)map.get("nomVia")).trim());
            mapVia.put("codTipPuerta", ((String)map.get("codTipPuerta")).trim());
            mapVia.put("desTipPuerta", ((String)map.get("desTipPuerta")).trim());
            mapVia.put("numMunicipal", ((String)map.get("numMunicipal")).trim());
            mapVia.put("codConNumeracion", ((String)map.get("codConNumeracion")).trim());
            mapVia.put("desConNumeracion", ((String)map.get("desConNumeracion")).trim());
            mapVia.put("numCerNumeracion", ((String)map.get("numCerNumeracion")).trim());

            listaVia.add(mapVia);

            mapFicha.put("listaVia", listaVia);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que elimina un vía a la lista de vías.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo eliminarVia");

        List listaVia = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaVia") && mapFicha.get("listaVia")!=null ){
                listaVia = (List)mapFicha.get("listaVia");
            }else{
                listaVia = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaVia"));
            HashMap mapVia = (HashMap)listaVia.get(ind);
            ViaService via = new ViaService();
            via.eliminarVia(mapVia);
            listaVia.remove(ind);

            mapFicha.put("listaVia", listaVia);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Vía.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irEditarVia");

        List listaVia = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaVia") && mapFicha.get("listaVia")!=null ){
                listaVia = (List)mapFicha.get("listaVia");
            }else{
                listaVia = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaVia"));
            HashMap mapVia = (HashMap)listaVia.get(ind);

            request.setAttribute("viaBean", mapVia);
            request.setAttribute("indListaVia", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("via");
    }

    /**
     * Método que edita una vía de la lista de vías para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo editarVia");

        List listaVia = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaVia") && mapFicha.get("listaVia")!=null ){
                listaVia = (List)mapFicha.get("listaVia");
            }else{
                listaVia = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaVia"));

            HashMap mapVia = (HashMap)listaVia.get(ind);
            mapVia.put("codVia", ((String)map.get("codVia")).trim());
            mapVia.put("codTipVia", ((String)map.get("codTipVia")).trim());
            mapVia.put("desTipVia", ((String)map.get("desTipVia")).trim());
            mapVia.put("nomVia", ((String)map.get("nomVia")).trim());
            mapVia.put("codTipPuerta", ((String)map.get("codTipPuerta")).trim());
            mapVia.put("desTipPuerta", ((String)map.get("desTipPuerta")).trim());
            mapVia.put("numMunicipal", ((String)map.get("numMunicipal")).trim());
            mapVia.put("codConNumeracion", ((String)map.get("codConNumeracion")).trim());
            mapVia.put("desConNumeracion", ((String)map.get("desConNumeracion")).trim());
            mapVia.put("numCerNumeracion", ((String)map.get("numCerNumeracion")).trim());

            listaVia.set(ind, mapVia);

            mapFicha.put("listaVia", listaVia);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Construcción.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irAgregarConstruccion");

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

        return actionMapping.findForward("construccion");
    }

    /**
     * Método que agrega una construcción a la lista de construcciones para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo agregarConstruccion");

        List listaConstruccion = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaConstruccion") && mapFicha.get("listaConstruccion")!=null ){
                listaConstruccion = (List)mapFicha.get("listaConstruccion");
            }else{
                listaConstruccion = new ArrayList();
            }

            HashMap construccion = new HashMap();
            construccion.put("numPiso", ((String)map.get("numPiso")).trim());
            construccion.put("fecConstruccion", ((String)map.get("fecConstruccion")).trim());
            construccion.put("codMEP", ((String)map.get("codMEP")).trim());
            construccion.put("desMEP", ((String)map.get("desMEP")).trim());
            construccion.put("codECC", ((String)map.get("codECC")).trim());
            construccion.put("desECC", ((String)map.get("desECC")).trim());
            construccion.put("codECS", ((String)map.get("codECS")).trim());
            construccion.put("desECS", ((String)map.get("desECS")).trim());
            construccion.put("muro", ((String)map.get("muro")).trim());
            construccion.put("techo", ((String)map.get("techo")).trim());
            construccion.put("pisos", ((String)map.get("pisos")).trim());
            construccion.put("puerta", ((String)map.get("puerta")).trim());
            construccion.put("revestimiento", ((String)map.get("revestimiento")).trim());
            construccion.put("bano", ((String)map.get("bano")).trim());
            construccion.put("instalaciones", ((String)map.get("instalaciones")).trim());
            construccion.put("areConDeclarada", ((String)map.get("areConDeclarada")).trim());
            construccion.put("areConVerificada", ((String)map.get("areConVerificada")).trim());
            construccion.put("codUCA", ((String)map.get("codUCA")).trim());
            construccion.put("desUCA", ((String)map.get("desUCA")).trim());

            listaConstruccion.add(construccion);

            mapFicha.put("listaConstruccion", listaConstruccion);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que elimina una construcción a la lista de construcciones.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo eliminarConstrucción");

        List listaConstruccion = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaConstruccion") && mapFicha.get("listaConstruccion")!=null ){
                listaConstruccion = (List)mapFicha.get("listaConstruccion");
            }else{
                listaConstruccion = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaConstruccion"));
            HashMap mapConstruccion = (HashMap)listaConstruccion.get(ind);
            ConstruccionService construccion = new ConstruccionService();
            construccion.eliminarConstruccion(mapConstruccion);
            listaConstruccion.remove(ind);

            mapFicha.put("listaConstruccion", listaConstruccion);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Construcción.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irEditarConstruccion");

        List listaConstruccion = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaConstruccion") && mapFicha.get("listaConstruccion")!=null ){
                listaConstruccion = (List)mapFicha.get("listaConstruccion");
            }else{
                listaConstruccion = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaConstruccion"));
            HashMap construccion = (HashMap)listaConstruccion.get(ind);

            request.setAttribute("construccionBean", construccion);
            request.setAttribute("indListaConstruccion", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("construccion");
    }

    /**
     * Método que edita una construccion de la lista de construcciones para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo editarConstruccion");

        List listaConstruccion = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaConstruccion") && mapFicha.get("listaConstruccion")!=null ){
                listaConstruccion = (List)mapFicha.get("listaConstruccion");
            }else{
                listaConstruccion = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaConstruccion"));

            HashMap construccion = (HashMap)listaConstruccion.get(ind);
            construccion.put("numPiso", ((String)map.get("numPiso")).trim());
            construccion.put("fecConstruccion", ((String)map.get("fecConstruccion")).trim());
            construccion.put("codMEP", ((String)map.get("codMEP")).trim());
            construccion.put("desMEP", ((String)map.get("desMEP")).trim());
            construccion.put("codECC", ((String)map.get("codECC")).trim());
            construccion.put("desECC", ((String)map.get("desECC")).trim());
            construccion.put("codECS", ((String)map.get("codECS")).trim());
            construccion.put("desECS", ((String)map.get("desECS")).trim());
            construccion.put("muro", ((String)map.get("muro")).trim());
            construccion.put("techo", ((String)map.get("techo")).trim());
            construccion.put("pisos", ((String)map.get("pisos")).trim());
            construccion.put("puerta", ((String)map.get("puerta")).trim());
            construccion.put("revestimiento", ((String)map.get("revestimiento")).trim());
            construccion.put("bano", ((String)map.get("bano")).trim());
            construccion.put("instalaciones", ((String)map.get("instalaciones")).trim());
            construccion.put("areConDeclarada", ((String)map.get("areConDeclarada")).trim());
            construccion.put("areConVerificada", ((String)map.get("areConVerificada")).trim());
            construccion.put("codUCA", ((String)map.get("codUCA")).trim());
            construccion.put("desUCA", ((String)map.get("desUCA")).trim());


            listaConstruccion.set(ind, construccion);

            mapFicha.put("listaConstruccion", listaConstruccion);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Obras Complementarias.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irAgregarObra");

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

        return actionMapping.findForward("obra");
    }

    /**
     * Método que agrega una obra complementaria a la lista de obras complementarias para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo agregarObra");

        List listaObra = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaObra") && mapFicha.get("listaObra")!=null ){
                listaObra = (List)mapFicha.get("listaObra");
            }else{
                listaObra = new ArrayList();
            }

            HashMap obra = new HashMap();
            obra.put("codInstalacion", ((String)map.get("codInstalacion")).trim());
            obra.put("desInstalacion", ((String)map.get("desInstalacion")).trim());
            obra.put("fecConstruccion", ((String)map.get("fecConstruccion")).trim());
            obra.put("codMEP", ((String)map.get("codMEP")).trim());
            obra.put("desMEP", ((String)map.get("desMEP")).trim());
            obra.put("codECC", ((String)map.get("codECC")).trim());
            obra.put("desECC", ((String)map.get("desECC")).trim());
            obra.put("codECS", ((String)map.get("codECS")).trim());
            obra.put("desECS", ((String)map.get("desECS")).trim());
            obra.put("largo", ((String)map.get("largo")).trim());
            obra.put("ancho", ((String)map.get("ancho")).trim());
            obra.put("alto", ((String)map.get("alto")).trim());
            obra.put("total", ((String)map.get("total")).trim());
            obra.put("uniMedida", ((String)map.get("uniMedida")).trim());
            obra.put("codUCA", ((String)map.get("codUCA")).trim());
            obra.put("desUCA", ((String)map.get("desUCA")).trim());

            listaObra.add(obra);

            mapFicha.put("listaObra", listaObra);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que elimina una obra a la lista de obras complementarias.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo eliminarObra");

        List listaObra = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaObra") && mapFicha.get("listaObra")!=null ){
                listaObra = (List)mapFicha.get("listaObra");
            }else{
                listaObra = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaObra"));
            HashMap mapObra = (HashMap)listaObra.get(ind);
            ObrComplementariaService obra = new ObrComplementariaService();
            obra.eliminarObraComplementaria(mapObra);
            listaObra.remove(ind);

            mapFicha.put("listaObra", listaObra);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Obras Complementarias.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irEditarObra");

        List listaInstalacion = null;
        List listaObra = null;
        List listaMEP = null;
        List listaECC = null;
        List listaECS = null;
        List listaUCA = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaObra") && mapFicha.get("listaObra")!=null ){
                listaObra = (List)mapFicha.get("listaObra");
            }else{
                listaObra = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaObra"));
            HashMap obra = (HashMap)listaObra.get(ind);

            InstalacionService instalacion = new InstalacionService();
            listaInstalacion = instalacion.getListInstalacion();

            MEPService mep = new MEPService();
            listaMEP = mep.getListMEP();

            ECCService ecc = new ECCService();
            listaECC = ecc.getListECC();

            ECSService ecs = new ECSService();
            listaECS = ecs.getListECS();

            UCAService uca = new UCAService();
            listaUCA = uca.getListUCA();

            session.setAttribute("listaMEP", listaMEP);
            session.setAttribute("listaECC", listaECC);
            session.setAttribute("listaECS", listaECS);
            session.setAttribute("listaUCA", listaUCA);

            request.setAttribute("obraBean", obra);
            request.setAttribute("indListaObra", Integer.toString(ind));
            session.setAttribute("listaInstalacion", listaInstalacion);
            session.setAttribute("listaMEP", listaMEP);
            session.setAttribute("listaECC", listaECC);
            session.setAttribute("listaECS", listaECS);
            session.setAttribute("listaUCA", listaUCA);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("obra");
    }

    /**
     * Método que edita una obra de la lista de obras complementarias para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo editarObra");

        List listaObra = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaObra") && mapFicha.get("listaObra")!=null ){
                listaObra = (List)mapFicha.get("listaObra");
            }else{
                listaObra = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaObra"));

            HashMap obra = (HashMap)listaObra.get(ind);
            obra.put("codInstalacion", ((String)map.get("codInstalacion")).trim());
            obra.put("desInstalacion", ((String)map.get("desInstalacion")).trim());
            obra.put("fecConstruccion", ((String)map.get("fecConstruccion")).trim());
            obra.put("codMEP", ((String)map.get("codMEP")).trim());
            obra.put("desMEP", ((String)map.get("desMEP")).trim());
            obra.put("codECC", ((String)map.get("codECC")).trim());
            obra.put("desECC", ((String)map.get("desECC")).trim());
            obra.put("codECS", ((String)map.get("codECS")).trim());
            obra.put("desECS", ((String)map.get("desECS")).trim());
            obra.put("largo", ((String)map.get("largo")).trim());
            obra.put("ancho", ((String)map.get("ancho")).trim());
            obra.put("alto", ((String)map.get("alto")).trim());
            obra.put("total", ((String)map.get("total")).trim());
            obra.put("uniMedida", ((String)map.get("uniMedida")).trim());
            obra.put("codUCA", ((String)map.get("codUCA")).trim());
            obra.put("desUCA", ((String)map.get("desUCA")).trim());

            listaObra.set(ind, obra);

            mapFicha.put("listaObra", listaObra);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Recapitulación de Edificios.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarEdificio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irAgregarEdificio");

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

        return actionMapping.findForward("edificio");
    }

    /**
     * Método que agrega un edificio a la lista de recapitulacion de edificios para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarEdificio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo agregarEdificio");

        List listaRecEdificio = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaRecEdificio") && mapFicha.get("listaRecEdificio")!=null ){
                listaRecEdificio = (List)mapFicha.get("listaRecEdificio");
            }else{
                listaRecEdificio = new ArrayList();
            }

            HashMap edificio = new HashMap();
            edificio.put("edificio", ((String)map.get("edificio")).trim());
            edificio.put("porcentaje", ((String)map.get("porcentaje")).trim());
            edificio.put("atc", ((String)map.get("atc")).trim());
            edificio.put("acc", ((String)map.get("acc")).trim());
            edificio.put("aoic", ((String)map.get("aoic")).trim());

            listaRecEdificio.add(edificio);

            mapFicha.put("listaRecEdificio", listaRecEdificio);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que elimina un edificio a la lista de recapitulacion de edificios.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarEdificio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo eliminarEdificio");

        List listaRecEdificio = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaRecEdificio") && mapFicha.get("listaRecEdificio")!=null ){
                listaRecEdificio = (List)mapFicha.get("listaRecEdificio");
            }else{
                listaRecEdificio = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaRecEdificio"));
//            HashMap mapEdificio = (HashMap)listaRecEdificio.get(ind);
//            RecEdificioService recEdificio = new RecEdificioService();
//            recEdificio.eliminarRecEdificio(mapEdificio);
            listaRecEdificio.remove(ind);

            mapFicha.put("listaRecEdificio", listaRecEdificio);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Edificios.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarEdificio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irEditarEdificio");

        List listaRecEdificio = null;
        
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaRecEdificio") && mapFicha.get("listaRecEdificio")!=null ){
                listaRecEdificio = (List)mapFicha.get("listaRecEdificio");
            }else{
                listaRecEdificio = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaRecEdificio"));
            HashMap edificio = (HashMap)listaRecEdificio.get(ind);

            request.setAttribute("edificioBean", edificio);
            request.setAttribute("indListaRecEdificio", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("edificio");
    }

    /**
     * Método que edita un edificio de la lista de recapitulacion de edificios para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarEdificio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo editarEdificio");

        List listaRecEdificio = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaRecEdificio") && mapFicha.get("listaRecEdificio")!=null ){
                listaRecEdificio = (List)mapFicha.get("listaRecEdificio");
            }else{
                listaRecEdificio = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaRecEdificio"));

            HashMap edificio = new HashMap();
            edificio.put("edificio", ((String)map.get("edificio")).trim());
            edificio.put("porcentaje", ((String)map.get("porcentaje")).trim());
            edificio.put("atc", ((String)map.get("atc")).trim());
            edificio.put("acc", ((String)map.get("acc")).trim());
            edificio.put("aoic", ((String)map.get("aoic")).trim());

            listaRecEdificio.set(ind, edificio);

            mapFicha.put("listaRecEdificio", listaRecEdificio);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Recapitulación de Bienes Comunes.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irAgregarBienComun");

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

        return actionMapping.findForward("bienComun");
    }

    /**
     * Método que agrega un bien común a la lista de recapitulacion de bienes comunes para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo agregarBienComun");

        List listaRecBienComun = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaRecBienComun") && mapFicha.get("listaRecBienComun")!=null ){
                listaRecBienComun = (List)mapFicha.get("listaRecBienComun");
            }else{
                listaRecBienComun = new ArrayList();
            }

            HashMap bienComun = new HashMap();
            bienComun.put("numero", ((String)map.get("numero")).trim());
            bienComun.put("edificacion", ((String)map.get("edificacion")).trim());
            bienComun.put("entrada", ((String)map.get("entrada")).trim());
            bienComun.put("piso", ((String)map.get("piso")).trim());
            bienComun.put("unidad", ((String)map.get("unidad")).trim());
            bienComun.put("porcentaje", ((String)map.get("porcentaje")).trim());
            bienComun.put("atc", ((String)map.get("atc")).trim());
            bienComun.put("acc", ((String)map.get("acc")).trim());
            bienComun.put("aoic", ((String)map.get("aoic")).trim());

            listaRecBienComun.add(bienComun);

            mapFicha.put("listaRecBienComun", listaRecBienComun);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que elimina un bien común a la lista de recapitulacion de bienes comunes.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo eliminarBienComun");

        List listaRecBienComun = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaRecBienComun") && mapFicha.get("listaRecBienComun")!=null ){
                listaRecBienComun = (List)mapFicha.get("listaRecBienComun");
            }else{
                listaRecBienComun = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaRecBienComun"));
//            HashMap mapRecBienComun = (HashMap)listaRecBienComun.get(ind);
//            RecBienComunService recBienComun = new RecBienComunService();
//            recBienComun.eliminarRecBienComun(mapRecBienComun);
            listaRecBienComun.remove(ind);

            mapFicha.put("listaRecBienComun", listaRecBienComun);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Bienes Comunes.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irEditarBienComun");

        List listaRecBienComun = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaRecBienComun") && mapFicha.get("listaRecBienComun")!=null ){
                listaRecBienComun = (List)mapFicha.get("listaRecBienComun");
            }else{
                listaRecBienComun = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaRecBienComun"));
            HashMap bienComun = (HashMap)listaRecBienComun.get(ind);

            request.setAttribute("bienComunBean", bienComun);
            request.setAttribute("indListaRecBienComun", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("bienComun");
    }

    /**
     * Método que edita un bien común de la lista de recapitulacion de bienes comunes para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo editarBienComun");

        List listaRecBienComun = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaRecBienComun") && mapFicha.get("listaRecBienComun")!=null ){
                listaRecBienComun = (List)mapFicha.get("listaRecBienComun");
            }else{
                listaRecBienComun = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaRecBienComun"));

            HashMap bienComun = new HashMap();
            bienComun.put("numero", ((String)map.get("numero")).trim());
            bienComun.put("edificacion", ((String)map.get("edificacion")).trim());
            bienComun.put("entrada", ((String)map.get("entrada")).trim());
            bienComun.put("piso", ((String)map.get("piso")).trim());
            bienComun.put("unidad", ((String)map.get("unidad")).trim());
            bienComun.put("porcentaje", ((String)map.get("porcentaje")).trim());
            bienComun.put("atc", ((String)map.get("atc")).trim());
            bienComun.put("acc", ((String)map.get("acc")).trim());
            bienComun.put("aoic", ((String)map.get("aoic")).trim());

            listaRecBienComun.set(ind, bienComun);

            mapFicha.put("listaRecBienComun", listaRecBienComun);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
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
        logg.debug(" Log en FichaBienComunAction metodo setearValores");

        HashMap mapCabecera = null;
        HashMap mapPredio = null;
        HashMap mapNotaria = null;
        HashMap mapBienComun = null;
        HashMap mapRecapitulacion = null;
        HashMap mapInscripcion = null;
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

            mapPredio = mapFicha.get("mapPredio")!=null?(HashMap)mapFicha.get("mapPredio"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapPredio.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codTipEdificacionPre") && map.get("codTipEdificacionPre")!=null && !"".equals((String)map.get("codTipEdificacionPre")))
                mapPredio.put("codTipEdificacion", (String)map.get("codTipEdificacionPre"));
            if(map.containsKey("nomEdificacionPre") && map.get("nomEdificacionPre")!=null && !"".equals((String)map.get("nomEdificacionPre")))
                mapPredio.put("nomEdificacion", (String)map.get("nomEdificacionPre"));
            if(map.containsKey("codTipInteriorPre") && map.get("codTipInteriorPre")!=null && !"".equals((String)map.get("codTipInteriorPre")))
                mapPredio.put("codTipInterior", (String)map.get("codTipInteriorPre"));
            if(map.containsKey("numInteriorPre") && map.get("numInteriorPre")!=null && !"".equals((String)map.get("numInteriorPre")))
                mapPredio.put("numInterior", (String)map.get("numInteriorPre"));
            if(map.containsKey("codHUPre") && map.get("codHUPre")!=null && !"".equals((String)map.get("codHUPre")))
                mapPredio.put("codHabUrbana", (String)map.get("codHUPre"));
            if(map.containsKey("nomHUPre") && map.get("nomHUPre")!=null && !"".equals((String)map.get("nomHUPre")))
                mapPredio.put("nomHabUrbana", (String)map.get("nomHUPre"));
            if(map.containsKey("sectorPre") && map.get("sectorPre")!=null && !"".equals((String)map.get("sectorPre")))
                mapPredio.put("sector", (String)map.get("sectorPre"));
            if(map.containsKey("manzanaPre") && map.get("manzanaPre")!=null && !"".equals((String)map.get("manzanaPre")))
                mapPredio.put("manzana", (String)map.get("manzanaPre"));
            if(map.containsKey("lotePre") && map.get("lotePre")!=null && !"".equals((String)map.get("lotePre")))
                mapPredio.put("lote", (String)map.get("lotePre"));
            if(map.containsKey("sublotePre") && map.get("sublotePre")!=null && !"".equals((String)map.get("sublotePre")))
                mapPredio.put("sublote", (String)map.get("sublotePre"));
            if(map.containsKey("codClaPredio") && map.get("codClaPredio")!=null && !"".equals((String)map.get("codClaPredio")))
                mapPredio.put("codClaPredio", (String)map.get("codClaPredio"));
            if(map.containsKey("codSubClaPredio") && map.get("codSubClaPredio")!=null && !"".equals((String)map.get("codSubClaPredio")))
                mapPredio.put("codSubClaPredio", (String)map.get("codSubClaPredio"));
            if(map.containsKey("codUbiPreCatastral") && map.get("codUbiPreCatastral")!=null && !"".equals((String)map.get("codUbiPreCatastral")))
                mapPredio.put("codUbiPreCatastral", (String)map.get("codUbiPreCatastral"));
            if(map.containsKey("codUsoPreCatastral") && map.get("codUsoPreCatastral")!=null && !"".equals((String)map.get("codUsoPreCatastral")))
                mapPredio.put("codUsoPreCatastral", (String)map.get("codUsoPreCatastral"));
            if(map.containsKey("desUsoPreCatastral") && map.get("desUsoPreCatastral")!=null && !"".equals((String)map.get("desUsoPreCatastral")))
                mapPredio.put("desUsoPreCatastral", (String)map.get("desUsoPreCatastral"));
            if(map.containsKey("estructuracion") && map.get("estructuracion")!=null && !"".equals((String)map.get("estructuracion")))
                mapPredio.put("estructuracion", (String)map.get("estructuracion"));
            if(map.containsKey("zonificacion") && map.get("zonificacion")!=null && !"".equals((String)map.get("zonificacion")))
                mapPredio.put("zonificacion", (String)map.get("zonificacion"));
            if(map.containsKey("areTerTitulo") && map.get("areTerTitulo")!=null && !"".equals((String)map.get("areTerTitulo")))
                mapPredio.put("areTerTitulo", (String)map.get("areTerTitulo"));
            if(map.containsKey("areTerVerificada") && map.get("areTerVerificada")!=null && !"".equals((String)map.get("areTerVerificada")))
                mapPredio.put("areTerVerificada", (String)map.get("areTerVerificada"));
            if(map.containsKey("medCamFrente") && map.get("medCamFrente")!=null && !"".equals((String)map.get("medCamFrente")))
                mapPredio.put("medCamFrente", (String)map.get("medCamFrente"));
            if(map.containsKey("medTitFrente") && map.get("medTitFrente")!=null && !"".equals((String)map.get("medTitFrente")))
                mapPredio.put("medTitFrente", (String)map.get("medTitFrente"));
            if(map.containsKey("colCamFrente") && map.get("colCamFrente")!=null && !"".equals((String)map.get("colCamFrente")))
                mapPredio.put("colCamFrente", (String)map.get("colCamFrente"));
            if(map.containsKey("colTitFrente") && map.get("colTitFrente")!=null && !"".equals((String)map.get("colTitFrente")))
                mapPredio.put("colTitFrente", (String)map.get("colTitFrente"));
            if(map.containsKey("medCamDerecha") && map.get("medCamDerecha")!=null && !"".equals((String)map.get("medCamDerecha")))
                mapPredio.put("medCamDerecha", (String)map.get("medCamDerecha"));
            if(map.containsKey("medTitDerecha") && map.get("medTitDerecha")!=null && !"".equals((String)map.get("medTitDerecha")))
                mapPredio.put("medTitDerecha", (String)map.get("medTitDerecha"));
            if(map.containsKey("colCamDerecha") && map.get("colCamDerecha")!=null && !"".equals((String)map.get("colCamDerecha")))
                mapPredio.put("colCamDerecha", (String)map.get("colCamDerecha"));
            if(map.containsKey("colTitDerecha") && map.get("colTitDerecha")!=null && !"".equals((String)map.get("colTitDerecha")))
                mapPredio.put("colTitDerecha", (String)map.get("colTitDerecha"));
            if(map.containsKey("medCamIzquierda") && map.get("medCamIzquierda")!=null && !"".equals((String)map.get("medCamIzquierda")))
                mapPredio.put("medCamIzquierda", (String)map.get("medCamIzquierda"));
            if(map.containsKey("medTitIzquierda") && map.get("medTitIzquierda")!=null && !"".equals((String)map.get("medTitIzquierda")))
                mapPredio.put("medTitIzquierda", (String)map.get("medTitIzquierda"));
            if(map.containsKey("colCamIzquierda") && map.get("colCamIzquierda")!=null && !"".equals((String)map.get("colCamIzquierda")))
                mapPredio.put("colCamIzquierda", (String)map.get("colCamIzquierda"));
            if(map.containsKey("colTitIzquierda") && map.get("colTitIzquierda")!=null && !"".equals((String)map.get("colTitIzquierda")))
                mapPredio.put("colTitIzquierda", (String)map.get("colTitIzquierda"));
            if(map.containsKey("medCamFondo") && map.get("medCamFondo")!=null && !"".equals((String)map.get("medCamFondo")))
                mapPredio.put("medCamFondo", (String)map.get("medCamFondo"));
            if(map.containsKey("medTitFondo") && map.get("medTitFondo")!=null && !"".equals((String)map.get("medTitFondo")))
                mapPredio.put("medTitFondo", (String)map.get("medTitFondo"));
            if(map.containsKey("colCamFondo") && map.get("colCamFondo")!=null && !"".equals((String)map.get("colCamFondo")))
                mapPredio.put("colCamFondo", (String)map.get("colCamFondo"));
            if(map.containsKey("colTitFondo") && map.get("colTitFondo")!=null && !"".equals((String)map.get("colTitFondo")))
                mapPredio.put("colTitFondo", (String)map.get("colTitFondo"));
            if(map.containsKey("luz") && map.get("luz")!=null && !"".equals((String)map.get("luz")))
                mapPredio.put("luz", (String)map.get("luz"));
            if(map.containsKey("numSumLuz") && map.get("numSumLuz")!=null && !"".equals((String)map.get("numSumLuz")))
                mapPredio.put("numSumLuz", (String)map.get("numSumLuz"));
            if(map.containsKey("agua") && map.get("agua")!=null && !"".equals((String)map.get("agua")))
                mapPredio.put("agua", (String)map.get("agua"));
            if(map.containsKey("numConAgua") && map.get("numConAgua")!=null && !"".equals((String)map.get("numConAgua")))
                mapPredio.put("numConAgua", (String)map.get("numConAgua"));
            if(map.containsKey("telefono") && map.get("telefono")!=null && !"".equals((String)map.get("telefono")))
                mapPredio.put("telefono", (String)map.get("telefono"));
            if(map.containsKey("numTelefono") && map.get("numTelefono")!=null && !"".equals((String)map.get("numTelefono")))
                mapPredio.put("numTelefono", (String)map.get("numTelefono"));
            if(map.containsKey("desague") && map.get("desague")!=null && !"".equals((String)map.get("desague")))
                mapPredio.put("desague", (String)map.get("desague"));
            if(map.containsKey("codConTitular") && map.get("codConTitular")!=null && !"".equals((String)map.get("codConTitular")))
                mapPredio.put("codConTitular", (String)map.get("codConTitular"));
            if(map.containsKey("codForAdquisicion") && map.get("codForAdquisicion")!=null && !"".equals((String)map.get("codForAdquisicion")))
                mapPredio.put("codForAdquisicion", (String)map.get("codForAdquisicion"));
            if(map.containsKey("codForAdqPredio") && map.get("codForAdqPredio")!=null && !"".equals((String)map.get("codForAdqPredio")))
                mapPredio.put("codForAdqPredio", (String)map.get("codForAdqPredio"));
            if(map.containsKey("codConEspPredio") && map.get("codConEspPredio")!=null && !"".equals((String)map.get("codConEspPredio")))
                mapPredio.put("codConEspPredio", (String)map.get("codConEspPredio"));
            if(map.containsKey("numResExoPredio") && map.get("numResExoPredio")!=null && !"".equals((String)map.get("numResExoPredio")))
                mapPredio.put("numResExoPredio", (String)map.get("numResExoPredio"));
            if(map.containsKey("porcentaje") && map.get("porcentaje")!=null && !"".equals((String)map.get("porcentaje")))
                mapPredio.put("porcentaje", (String)map.get("porcentaje"));
            if(map.containsKey("fecInicio") && map.get("fecInicio")!=null && !"".equals((String)map.get("fecInicio")))
                mapPredio.put("fecInicio", (String)map.get("fecInicio"));
            if(map.containsKey("fecVencimiento") && map.get("fecVencimiento")!=null && !"".equals((String)map.get("fecVencimiento")))
                mapPredio.put("fecVencimiento", (String)map.get("fecVencimiento"));

            mapNotaria = mapFicha.get("mapNotaria")!=null?(HashMap)mapFicha.get("mapNotaria"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapNotaria.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("nomNotaria") && map.get("nomNotaria")!=null && !"".equals((String)map.get("nomNotaria")))
                mapNotaria.put("nomNotaria", (String)map.get("nomNotaria"));
            if(map.containsKey("kardex") && map.get("kardex")!=null && !"".equals((String)map.get("kardex")))
                mapNotaria.put("kardex", (String)map.get("kardex"));
            if(map.containsKey("fecEscPublica") && map.get("fecEscPublica")!=null && !"".equals((String)map.get("fecEscPublica")))
                mapNotaria.put("fecEscPublica", (String)map.get("fecEscPublica"));

            mapBienComun = mapFicha.get("mapBienComun")!=null?(HashMap)mapFicha.get("mapBienComun"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapBienComun.put("numFicha", (String)map.get("numFicha"));

            mapRecapitulacion = mapFicha.get("mapRecapitulacion")!=null?(HashMap)mapFicha.get("mapRecapitulacion"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapRecapitulacion.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("areTerInvLotColindante") && map.get("areTerInvLotColindante")!=null && !"".equals((String)map.get("areTerInvLotColindante")))
                mapRecapitulacion.put("areTerInvLotColindante", (String)map.get("areTerInvLotColindante"));
            if(map.containsKey("areTerInvJarAislamiento") && map.get("areTerInvJarAislamiento")!=null && !"".equals((String)map.get("areTerInvJarAislamiento")))
                mapRecapitulacion.put("areTerInvJarAislamiento", (String)map.get("areTerInvJarAislamiento"));
            if(map.containsKey("areTerInvArePublica") && map.get("areTerInvArePublica")!=null && !"".equals((String)map.get("areTerInvArePublica")))
                mapRecapitulacion.put("areTerInvArePublica", (String)map.get("areTerInvArePublica"));
            if(map.containsKey("areTerInvAreIntangible") && map.get("areTerInvAreIntangible")!=null && !"".equals((String)map.get("areTerInvAreIntangible")))
                mapRecapitulacion.put("areTerInvAreIntangible", (String)map.get("areTerInvAreIntangible"));

            mapInscripcion = mapFicha.get("mapInscripcion")!=null?(HashMap)mapFicha.get("mapInscripcion"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapInscripcion.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codTipParRegistral") && map.get("codTipParRegistral")!=null && !"".equals((String)map.get("codTipParRegistral")))
                mapInscripcion.put("codTipParRegistral", (String)map.get("codTipParRegistral"));
            if(map.containsKey("numPartida") && map.get("numPartida")!=null && !"".equals((String)map.get("numPartida")))
                mapInscripcion.put("numPartida", (String)map.get("numPartida"));
            if(map.containsKey("fojas") && map.get("fojas")!=null && !"".equals((String)map.get("fojas")))
                mapInscripcion.put("fojas", (String)map.get("fojas"));
            if(map.containsKey("asiento") && map.get("asiento")!=null && !"".equals((String)map.get("asiento")))
                mapInscripcion.put("asiento", (String)map.get("asiento"));
            if(map.containsKey("fecInsPredio") && map.get("fecInsPredio")!=null && !"".equals((String)map.get("fecInsPredio")))
                mapInscripcion.put("fecInsPredio", (String)map.get("fecInsPredio"));
            if(map.containsKey("codDecFabrica") && map.get("codDecFabrica")!=null && !"".equals((String)map.get("codDecFabrica")))
                mapInscripcion.put("codDecFabrica", (String)map.get("codDecFabrica"));
            if(map.containsKey("asiInsFabrica") && map.get("asiInsFabrica")!=null && !"".equals((String)map.get("asiInsFabrica")))
                mapInscripcion.put("asiInsFabrica", (String)map.get("asiInsFabrica"));
            if(map.containsKey("fecInsFabrica") && map.get("fecInsFabrica")!=null && !"".equals((String)map.get("fecInsFabrica")))
                mapInscripcion.put("fecInsFabrica", (String)map.get("fecInsFabrica"));

            mapInformacion = mapFicha.get("mapInformacion")!=null?(HashMap)mapFicha.get("mapInformacion"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapInformacion.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codConDeclarante") && map.get("codConDeclarante")!=null && !"".equals((String)map.get("codConDeclarante")))
                mapInformacion.put("codConDeclarante", (String)map.get("codConDeclarante"));
            if(map.containsKey("codEstLleFicha") && map.get("codEstLleFicha")!=null && !"".equals((String)map.get("codEstLleFicha")))
                mapInformacion.put("codEstLleFicha", (String)map.get("codEstLleFicha"));
            if(map.containsKey("codMantenimiento") && map.get("codMantenimiento")!=null && !"".equals((String)map.get("codMantenimiento")))
                mapInformacion.put("codMantenimiento", (String)map.get("codMantenimiento"));

            mapFicha.put("mapCabecera", mapCabecera);
            mapFicha.put("mapPredio", mapPredio);
            mapFicha.put("mapNotaria", mapNotaria);
            mapFicha.put("mapBienComun", mapBienComun);
            mapFicha.put("mapRecapitulacion", mapRecapitulacion);
            mapFicha.put("mapInscripcion", mapInscripcion);
            mapFicha.put("mapInformacion", mapInformacion);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return mapFicha;
    }

    /**
     * Método que deriva a la página de Bienvenida.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irBienvenida(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irBienvenida");

        try{
            HttpSession session = request.getSession();
            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("bienvenida");
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
    public ActionForward irFichaBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaBienComunAction metodo irFichaBienComun");

        try{

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
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
        logg.debug(" Log en FichaBienComunAction metodo irFichaActividadEconomica");

        try{

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
        logg.debug(" Log en FichaBienComunAction metodo irBusqueda");

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
