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
public class NuevoUsoBCAction extends DispatchAction {

    /**
     * Inserta un nuevo registro en la tabla USOS_BC, lo parámetros obtenidos del
     * jsp son codigoUsoBC y descripcionUsoBC, luego de la inserción obtiene todos
     * los registros de USOS_BC y los guarda en sesión como listaUsosBC, finalmente
     * muestra la página consultaUsosBC.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward insertarUsoBC(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUsosBC = null;
        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("codigoUsoBC") && !"".equals((String)map.get("codigoUsoBC"))){
                if(!existeRegistro((String)map.get("codigoUsoBC"))) {
                    UsosBCService usosBCService = new UsosBCService();
                    result = usosBCService.insertarUsoBC((String)map.get("codigoUsoBC"), (String)map.get("descripcionUsoBC"));
                    session.setAttribute("resultadoInsercion", result);

                    listaUsosBC = usosBCService.obtenerUsosBC();
                    session.setAttribute("listaUsosBC", listaUsosBC);
                }else {
                    request.setAttribute("error", "El registro ya existe.");
                    return actionMapping.findForward("nuevo");
                }
            }else {
                request.setAttribute("error", "Ingrese el código de uso bc.");
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
     * Método que deriva a la página consultaUsosBC.jsp.
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
