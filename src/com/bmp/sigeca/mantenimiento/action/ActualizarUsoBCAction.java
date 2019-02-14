/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.UsosBCService;
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
public class ActualizarUsoBCAction extends DispatchAction {

    /**
     * Actualiza un registro en la tabla USOS_BC, el parámetro obtenido del
     * jsp es descripcionUsoBC, luego de actualizar el registro, obtiene todos
     * los registros de USOS_BC y los guarda en sesión como listaUsosBC, finalmente
     * muestra la página consultaUsosBC.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarUsoBC(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUsosBC = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("codigoUsoBC") && !"".equals((String)map.get("codigoUsoBC"))){
                
                    UsosBCService usosBCService = new UsosBCService();
                    result = usosBCService.actualizarUsoBC((String)map.get("codigoUsoBC"), (String)map.get("descripcionUsoBC"));
                    session.setAttribute("resultadoInsercion", result);
                    listaUsosBC = usosBCService.obtenerUsosBC();
                    session.setAttribute("listaUsosBC", listaUsosBC);
                
            }else {
                request.setAttribute("error", "Ingrese el código de uso bc.");
                return actionMapping.findForward("actualizar");
            }
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar actualizar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Elimina un registro de la tabla USOS_BC, luego de quitar el registro
     * muestra la página consultaUsosBC.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward eliminarUsoBC(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUsosBC = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            UsosBCService usosBCService = new UsosBCService();
            result = usosBCService.eliminarUsoBC((String)map.get("codigoUsoBC"));
            session.setAttribute("resultadoEliminacion", result);
            listaUsosBC = usosBCService.obtenerUsosBC();
            session.setAttribute("listaUsosBC", listaUsosBC);
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar eliminar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Retorna true si existe un registro con codigo igual a codigoUsoBC, de lo
     * contrario retorna false.
     * @param codigoUsoBC
     * @return boolean
     * @throws DAOException
     */
    boolean existeRegistro(String codigoUsoBC) throws DAOException {
        UsosBCService usosBCService = new UsosBCService();
        return usosBCService.existeUsoBC(codigoUsoBC);
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
