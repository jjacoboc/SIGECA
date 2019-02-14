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
public class NuevoCodInstalacionAction extends DispatchAction {

    /**
     * Inserta un nuevo registro en la tabla CODIGO_INSTALACION, lo parámetros
     * obtenidos del jsp son codigoInstalacion, descripcionInstalacion,
     * unidad y material, luego de la inserción obtiene todos los registros de
     * CODIGO_INSTALACION y los guarda en sesión como listaCodInstalaciones,
     * finalmente muestra la página consultaCodigo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward insertarCodInstalacion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaCodInstalaciones = null;
        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("codigoInstalacion") && !"".equals((String)map.get("codigoInstalacion"))){
                if(!existeRegistro((String)map.get("codigoInstalacion"))) {
                    CodInstalacionService codInstalacionService = new CodInstalacionService();
                    result = codInstalacionService.insertarCodInstalacion((String)map.get("codigoInstalacion"), (String)map.get("descripcionInstalacion"), (String)map.get("unidad"), (String)map.get("material"));
                    session.setAttribute("resultadoInsercion", result);

                    listaCodInstalaciones = codInstalacionService.obtenerCodInstalaciones();
                    session.setAttribute("listaCodInstalaciones", listaCodInstalaciones);
                }else {
                    request.setAttribute("error", "El registro ya existe.");
                    return actionMapping.findForward("nuevo");
                }
            }else {
                request.setAttribute("error", "Ingrese el código de la instalación.");
                return actionMapping.findForward("nuevo");
            }
        }
        catch(Exception e) {
            request.setAttribute("error", "Ocurrió un error al intentar guardar el registro.");
            return actionMapping.findForward("nuevo");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Retorna true si existe un registro con código igual a codigoInstalacion,
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
     * Método que deriva a la página consultaCodigo.jsp.
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
