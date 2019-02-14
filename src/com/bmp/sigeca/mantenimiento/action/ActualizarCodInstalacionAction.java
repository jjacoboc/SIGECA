/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.CodInstalacionService;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Administrador
 */
public class ActualizarCodInstalacionAction extends DispatchAction {

    /**
     * Actualiza un registro en la tabla CODIGO_INSTALACION, los par�metros obtenidos del
     * jsp son descripcionInstalacion, unidad y material, luego de actualizar el registro,
     * obtiene todos los registros de CODIGO_INSTALACION y los guarda en sesi�n como
     * listaCodInstalaciones, finalmente muestra la p�gina consultaCodigo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarCodInstalacion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaCodInstalaciones = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("codigoInstalacion") && !"".equals((String)map.get("codigoInstalacion"))){
                
                    CodInstalacionService codInstalacionService = new CodInstalacionService();
                    result = codInstalacionService.actualizarCodInstalacion((String)map.get("codigoInstalacion"), (String)map.get("descripcionInstalacion"), (String)map.get("unidad"), (String)map.get("material"));
                    session.setAttribute("resultadoInsercion", result);
                    listaCodInstalaciones = codInstalacionService.obtenerCodInstalaciones();
                    session.setAttribute("listaCodInstalaciones", listaCodInstalaciones);
                
            }else {
                request.setAttribute("error", "Ingrese el c�digo de la instalaci�n.");
                return actionMapping.findForward("actualizar");
            }
        }catch(Exception e){
            request.setAttribute("error", "Ocurri� un error al intentar actualizar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Elimina un registro de la tabla CODIGO_INSTALACION, luego de quitar el registro
     * muestra la p�gina codigo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward eliminarCodInstalacion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaCodInstalaciones = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            CodInstalacionService codInstalacionService = new CodInstalacionService();
            result = codInstalacionService.eliminarCodInstalacion((String)map.get("codigoInstalacion"));
            session.setAttribute("resultadoEliminacion", result);
            listaCodInstalaciones = codInstalacionService.obtenerCodInstalaciones();
            session.setAttribute("listaCodInstalaciones", listaCodInstalaciones);
        }catch(Exception e){
            request.setAttribute("error", "Ocurri� un error al intentar eliminar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Retorna true si existe un registro con c�digo igual a codigoInstalacion,
     * de lo contrario retorna false.
     * @param codigoInstalacion
     * @return boolean
     * @throws DAOException
     */
    boolean existeRegistro(String codigoInstalacion) throws DAOException {
        CodInstalacionService codInstalacionService = new CodInstalacionService();
        return codInstalacionService.existeCodInstalacion(codigoInstalacion);
    }

    /**
     * M�todo que deriva a la p�gina consultaCodigo.jsp.
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
