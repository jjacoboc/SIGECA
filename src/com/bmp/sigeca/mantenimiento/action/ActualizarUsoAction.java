/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.UsosService;
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
public class ActualizarUsoAction extends DispatchAction {

    private static Logger logger = Logger.getLogger(ActualizarUsoAction.class);
    protected Logger logg;

    /**
     * Actualiza un registro en la tabla USOS, el parámetro obtenido del
     * jsp es descripcionUso, luego de actualizar el registro, obtiene todos
     * los registros de USOS y los guarda en sesión como listaUsos, finalmente
     * muestra la página consultaUsos.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarUso(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUsos = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("codigoUso") && !"".equals((String)map.get("codigoUso"))){
                
                    UsosService usosService = new UsosService();
                    result = usosService.actualizarUso((String)map.get("codigoUso"), (String)map.get("descripcionUso"));
                    session.setAttribute("resultadoInsercion", result);
                    listaUsos = usosService.obtenerUsos();
                    session.setAttribute("listaUsos", listaUsos);
                
            }else {
                request.setAttribute("error", "Ingrese el código de uso.");
                return actionMapping.findForward("actualizar");
            }
        }catch(Exception e){
            logger.error("Ocurrió un error al actualizar un registro de USO", e);
            request.setAttribute("error", "Ocurrió un error al intentar actualizar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Elimina un registro de la tabla USOS, luego de quitar el registro
     * muestra la página consultaUsos.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward eliminarUso(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUsos = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            UsosService usosService = new UsosService();
            result = usosService.eliminarUso((String)map.get("codigoUso"));
            session.setAttribute("resultadoEliminacion", result);
            listaUsos = usosService.obtenerUsos();
            session.setAttribute("listaUsos", listaUsos);
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar eliminar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Retorna true si existe un registro con codigo igual a codigoUso, de lo
     * contrario retorna false.
     * @param codigoUso
     * @return boolean
     * @throws DAOException
     */
    boolean existeRegistro(String codigoUso) throws DAOException {
        UsosService usosService = new UsosService();
        return usosService.existeUso(codigoUso);
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
}
