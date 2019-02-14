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
public class NuevoUsoAction extends DispatchAction {

    /**
     * Inserta un nuevo registro en la tabla USOS, lo parámetros obtenidos del
     * jsp son codigoUso y descripcionUso, luego de la inserción obtiene todos
     * los registros de USOS y los guarda en sesión como listaUsos, finalmente
     * muestra la página consultaUsos.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward insertarUso(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaUsos = null;
        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("codigoUso") && !"".equals((String)map.get("codigoUso"))){
                if(!existeRegistro((String)map.get("codigoUso"))) {
                    UsosService usosService = new UsosService();
                    result = usosService.insertarUso((String)map.get("codigoUso"), (String)map.get("descripcionUso"));
                    session.setAttribute("resultadoInsercion", result);

                    listaUsos = usosService.obtenerUsos();
                    session.setAttribute("listaUsos", listaUsos);
                }else {
                    request.setAttribute("error", "El registro ya existe.");
                    return actionMapping.findForward("nuevo");
                }
            }else {
                request.setAttribute("error", "Ingrese el código de uso.");
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
