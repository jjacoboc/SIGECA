/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.UbigeoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class NuevoUbigeoAction extends DispatchAction {

    /**
     * Inserta un nuevo registro en la tabla UBIGEO, lo parámetros obtenidos del
     * jsp son codigoUbigeo, nombreUbigeo, cucDesde, cucHasta, ultimoCuc,
     * luego de la inserción obtiene todos los registros de UBIGEO y los guarda
     * en sesión como listaUbigeos, finalmente muestra la página consultaUbigeo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward insertarUbigeo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUbigeos = null;
        List listaDepartamentos = null;
        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(!existeRegistro((String)map.get("codigoUbigeo"))) {
                UbigeoService ubigeoService = new UbigeoService();
                result = ubigeoService.insertarUbigeo((String)map.get("codigoUbigeo"), (String)map.get("nombreUbigeo"), (String)map.get("cucDesde"), (String)map.get("cucHasta"), (String)map.get("ultimoCuc"));
                session.setAttribute("resultadoInsercion", result);

                listaUbigeos = ubigeoService.obtenerUbigeos();
                session.setAttribute("listaUbigeos", listaUbigeos);

//                listaDepartamentos = ubigeoService.obtenerDepartamentos();
//                session.setAttribute("listaDepartamentos", listaDepartamentos);
            }
            else {
                request.setAttribute("error", "El registro ya existe.");
                return actionMapping.findForward("nuevo");
            }
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al intentar guardar el registro.");
            return actionMapping.findForward("nuevo");
        }
        return actionMapping.findForward("consulta");
    }

    public ActionForward obtenerProvincias(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaProvincias = null;
        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            UbigeoService ubigeoService = new UbigeoService();
            String codigoDepartamento = (String)map.get("departamento");
            actualizarSeleccionDepartamento(codigoDepartamento, session);

            listaProvincias = ubigeoService.obtenerProvincias(codigoDepartamento);
            String codigoProvincia = (String)((Map)listaProvincias.get(0)).get("codigoProvincia");
            if(codigoProvincia != null)
            {
                actualizarSeleccionProvincia(codigoProvincia, session);
                session.setAttribute("listaProvincias", listaProvincias);
                actualizarListaySeleccionDistritos(codigoDepartamento, codigoProvincia, session);
            }
        }
        catch(Exception e) {
            request.setAttribute("error", "Ocurrió un error al datos de Provincias.");
            return actionMapping.findForward("nuevo");
        }
        return actionMapping.findForward("nuevo");
    }

    public ActionForward obtenerDistritos(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            actualizarListaySeleccionDistritos((String)map.get("departamento"), (String)map.get("provincia"), session);
            actualizarSeleccionProvincia((String)map.get("provincia"), session);
        }
        catch(Exception e) {
            request.setAttribute("error", "Ocurrió un error al datos de Distritos.");
            return actionMapping.findForward("nuevo");
        }
        return actionMapping.findForward("nuevo");
    }

    private void actualizarListaySeleccionDistritos(String codigoDepartamento, String codigoProvincia, HttpSession session) throws DAOException
    {
        UbigeoService ubigeoService = new UbigeoService();
        List listaDistritos = ubigeoService.obtenerDistritos(codigoDepartamento, codigoProvincia);
        String codigoDistrito = (String)((Map)listaDistritos.get(0)).get("codigoDistrito");
        if(codigoDistrito != null)
        {
            session.setAttribute("listaDistritos", listaDistritos);
            actualizarSeleccionDistrito(codigoDistrito, session);
        }
    }

    private void actualizarSeleccionDistrito(String codigoDistrito, HttpSession session)
    {
        session.setAttribute("distritoSelected", codigoDistrito);
    }

    private void actualizarSeleccionProvincia(String codigoProvincia, HttpSession session)
    {
        session.setAttribute("provinciaSelected", codigoProvincia);
    }

    private void actualizarSeleccionDepartamento(String codigoDepartamento, HttpSession session)
    {
        session.setAttribute("departamentoSelected", codigoDepartamento);
    }

    public ActionForward actualizarCodigo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            session.setAttribute("distritoSelected", (String)map.get("distrito"));
        }
        catch(Exception e) {
            request.setAttribute("error", "Ocurrió un error al actualizar el código de Ubigeo");
            return actionMapping.findForward("nuevo");
        }
        return actionMapping.findForward("nuevo");
    }

    /**
     * Retorna true si existe un registro con codigo igual a codigoUbigeo, de lo
     * contrario retorna false.
     * @param codigoUbigeo
     * @return boolean
     * @throws DAOException
     */
    boolean existeRegistro(String codigoUbigeo) throws DAOException {
        UbigeoService ubigeoService = new UbigeoService();
        return ubigeoService.existeUbigeo(codigoUbigeo);
    }
    /**
     * Método que deriva a la página consultaUsos.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward cancelar(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {
        return actionMapping.findForward("consulta");
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

        List listaProvincia = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapUbigeo = new HashMap();
            mapUbigeo.put("codDepartamento", (String)map.get("departamento"));
            com.bmp.sigeca.maestro.service.UbigeoService ubigeo = new com.bmp.sigeca.maestro.service.UbigeoService();
            listaProvincia = ubigeo.getListProvincia(mapUbigeo);

            session.setAttribute("listaProvincia", listaProvincia);
            session.setAttribute("departamento", (String)map.get("departamento"));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("nuevo");
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

        List listaDistrito = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapUbigeo = new HashMap();
            mapUbigeo.put("codDepartamento", (String)map.get("departamento"));
            mapUbigeo.put("codProvincia", (String)map.get("provincia"));
            com.bmp.sigeca.maestro.service.UbigeoService ubigeo = new com.bmp.sigeca.maestro.service.UbigeoService();
            listaDistrito = ubigeo.getListDistrito(mapUbigeo);

            session.setAttribute("listaDistrito", listaDistrito);
            session.setAttribute("departamento", (String)map.get("departamento"));
            session.setAttribute("provincia", (String)map.get("provincia"));
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("nuevo");
    }
}
