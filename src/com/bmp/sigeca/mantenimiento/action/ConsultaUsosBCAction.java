/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

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
public class ConsultaUsosBCAction extends DispatchAction {

    /**
     * Obtiene una lista con todos los registros de la tabla USOS_BC, luego
     * muestra la página consultaUsosBC.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward buscarUsosBC(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaUsosBC = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            UsosBCService usosBCService = new UsosBCService();
            listaUsosBC = usosBCService.buscarUsosBC((String)map.get("codigoUsoBC"), (String)map.get("descripcionUsoBC"));
            session.setAttribute("listaUsosBC", listaUsosBC);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Método que deriva a la página nuevoUsoBC.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward nuevoUsoBC (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        return actionMapping.findForward("nuevo");
    }

    /**
     * Obtiene los atributos actCodigoUsoBC y actDescripcionUsoBC del jsp, estos
     * valores se guardan en sesión como codigoUsoBC y descripcionUsoBC, luego
     * muestra la página actualizarUsoBC.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarUsoBC (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            session.setAttribute("codigoUsoBC", (String)map.get("actCodigoUsoBC"));
            session.setAttribute("descripcionUsoBC", (String)map.get("actDescripcionUsoBC"));
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("actualizar");
    }
}
