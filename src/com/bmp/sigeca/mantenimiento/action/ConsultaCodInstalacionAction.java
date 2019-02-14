/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

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
public class ConsultaCodInstalacionAction extends DispatchAction {

    /**
     * Obtiene una lista con todos los registros de la tabla CODIGO_INSTALACION, luego
     * muestra la página consultaNotaria.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward buscarCodInstalaciones(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaCodInstalaciones = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            CodInstalacionService codInstalacionService = new CodInstalacionService();
            listaCodInstalaciones = codInstalacionService.buscarCodInstalacion((String)map.get("codigoInstalacion"), (String)map.get("descripcionInstalacion"), (String)map.get("unidad"), (String)map.get("material"));
            session.setAttribute("listaCodInstalaciones", listaCodInstalaciones);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Método que deriva a la página nuevoCodigo.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward nuevoCodInstalacion (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        return actionMapping.findForward("nuevo");
    }

    /**
     * Obtiene los atributos actCodigoInstalacion, actDescripcionInstalacion,
     * actUnidad y actMaterial del jsp, estos valores se guardan en sesión como
     * codigoInstalacion, descripcionInstalacion, unidad, material, luego
     * muestra la página actualizarCodigo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarCodInstalacion (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            session.setAttribute("codigoInstalacion", (String)map.get("actCodigoInstalacion"));
            session.setAttribute("descripcionInstalacion", (String)map.get("actDescripcionInstalacion"));
            session.setAttribute("unidad", (String)map.get("actUnidad"));
            session.setAttribute("material", (String)map.get("actMaterial"));
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("actualizar");
    }
}
