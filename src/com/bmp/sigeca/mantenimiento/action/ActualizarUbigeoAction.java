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
public class ActualizarUbigeoAction extends DispatchAction {

    /**
     * Actualiza un registro en la tabla UBIGEO, los parámetros obtenidos del
     * jsp son nombreUbigeo, cucDesde, cucHasta y ultimoCuc luego de actualizar
     * el registro, obtiene todos los registros de UBIGEO y los guarda en
     * sesión como listaUbigeo, finalmente muestra la página consultaUbigeo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarUbigeo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUbigeos = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            UbigeoService ubigeoService = new UbigeoService();
            result = ubigeoService.actualizarUbigeo(map);
            session.setAttribute("resultadoInsercion", result);
            listaUbigeos = ubigeoService.obtenerUbigeos();
            session.setAttribute("listaUbigeos", listaUbigeos);
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar actualizar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Elimina un registro de la tabla UBIGEO, luego de quitar el registro
     * muestra la página consultaUbigeo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward eliminarUbigeo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUbigeos = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            UbigeoService ubigeoService = new UbigeoService();
            result = ubigeoService.eliminarUbigeo((String)map.get("codigoUbigeo"));
            session.setAttribute("resultadoEliminacion", result);
            listaUbigeos = ubigeoService.obtenerUbigeos();
            session.setAttribute("listaUbigeos", listaUbigeos);
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar eliminar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Método que deriva a la página consultaUbigeo.jsp.
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
}
