/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.seguridad.action;

import com.bmp.sigeca.mantenimiento.service.UbigeoService;
import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.resource.Properties;
import com.bmp.sigeca.seguridad.service.UsuarioService;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Administrador
 */
public class LoguinAction extends DispatchAction {
    
    protected Logger logg;
    
    /**
     * Método que deriva a la página de registro de usuarios.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward obtenerCuenta(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en LoginAction metodo obtenerCuenta");
        System.out.println("en LoginAction metodo obtenerCuenta");
        
        request.getSession().removeAttribute("registroUsuario");
        return actionMapping.findForward("registroUsuario");
    }

    /**
     * Método que deriva a la página de loguin.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irLoguin(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en LoginAction metodo irLoguin");

        HttpSession session = request.getSession();

        return actionMapping.findForward("inicio");
    }

    /**
     * Método que deriva a la página de recuperación de clave de acceso.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward olvidoClave(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en LoginAction metodo olvidoClave");

        HttpSession session = request.getSession();

        return actionMapping.findForward("clave");
    }

    /**
     * Método que deriva a la página registro de fichas.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward ingreso(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en LoginAction metodo ingreso");

        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            UsuarioService usuarioservice = new UsuarioService();
            UsuarioBean bean = usuarioservice.getUsuarioByPK(map);

            ActionMessages error = new ActionMessages();

            if("".equals((String)map.get("loguin"))){
                error.add("loguin",new ActionMessage("error.loguin.required"));
                destino = "inicio";
            }
            if(error.isEmpty() && "".equals((String)map.get("clave"))){
                error.add("loguin",new ActionMessage("error.loguin.required"));
                destino = "inicio";
            }

            if(error.isEmpty()){
                if(bean!=null){
                    UbigeoService ubigeoService = new UbigeoService();
                    HashMap ubigeo = ubigeoService.obtenerUbigeoActivo();
                    if(ubigeo!=null){
                        destino = "busqueda";
                    }else{
                        com.bmp.sigeca.maestro.service.UbigeoService confUbigeo = new com.bmp.sigeca.maestro.service.UbigeoService();
                        List listaDepartamento = confUbigeo.getListDepartamento();
                        HashMap mapUbigeo = new HashMap();
                        mapUbigeo.put("listaDepartamento", listaDepartamento);
                        session.setAttribute("mapConfUbigeo", mapUbigeo);
                        destino = "setUbigeo";
                    }
                }else{
                    error.add("loguin",new ActionMessage("error.loguin.invalidate"));
                    destino="inicio";
                }
            }
            
            saveErrors(request,error);
            session.setAttribute("usuarioBean", bean);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
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

        List listaProvincia = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapConfUbigeo = session.getAttribute("mapConfUbigeo")!=null?(HashMap)session.getAttribute("mapConfUbigeo"):new HashMap();
            mapConfUbigeo.put("codDepartamento", (String)map.get("codDepartamento"));
            com.bmp.sigeca.maestro.service.UbigeoService ubigeo = new com.bmp.sigeca.maestro.service.UbigeoService();
            listaProvincia = ubigeo.getListProvincia(mapConfUbigeo);
            mapConfUbigeo.put("listaProvincia", listaProvincia);
            session.setAttribute("mapConfUbigeo", mapConfUbigeo);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("setUbigeo");
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

        List listaDistrito = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapConfUbigeo = session.getAttribute("mapConfUbigeo")!=null?(HashMap)session.getAttribute("mapConfUbigeo"):new HashMap();
            mapConfUbigeo.put("codDepartamento", (String)map.get("codDepartamento"));
            mapConfUbigeo.put("codProvincia", (String)map.get("codProvincia"));
            com.bmp.sigeca.maestro.service.UbigeoService ubigeo = new com.bmp.sigeca.maestro.service.UbigeoService();
            listaDistrito = ubigeo.getListDistrito(mapConfUbigeo);
            mapConfUbigeo.put("listaDistrito", listaDistrito);
            session.setAttribute("mapConfUbigeo", mapConfUbigeo);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("setUbigeo");
    }

     public ActionForward setearUbigeo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());

        String codDepartamento = null;
        String codProvincia = null;
        String codDistrito = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            UbigeoService ubigeoService = new UbigeoService();
            codDepartamento = (String)map.get("codDepartamento");
            codProvincia = (String)map.get("codProvincia");
            codDistrito = (String)map.get("codDistrito");
            HashMap ubigeo = ubigeoService.obtenerUbigeoById(codDepartamento+codProvincia+codDistrito);
            ubigeo.put("activo", "1");
            ubigeoService.actualizarUbigeo(ubigeo);
            session.setAttribute("mapConfUbigeo", ubigeo);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("menuAdministrador");
    }
}