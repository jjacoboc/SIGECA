/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.mantenimiento.service.TablasCodigosService;
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
public class ConsultaTablasCodigosAction extends DispatchAction {

    /**
     * Obtiene una lista con todos los registros de la tabla TABLAS_CODIGOS, luego
     * muestra la página consultaTablasCodigos.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward buscarTablasCodigos(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTablasCodigos = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if("0".equals((String)map.get("id"))) map.put("id", "");
            TablasCodigosService tablasCodigosService = new TablasCodigosService();
            listaTablasCodigos = tablasCodigosService.buscarTablasCodigos((String)map.get("id"), (String)map.get("codigo"), (String)map.get("descripcion"));
            session.setAttribute("listaTablasCodigos", listaTablasCodigos);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Método que deriva a la página nuevaTablaCodigo.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward nuevaTablaCodigo (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        return actionMapping.findForward("nuevo");
    }

    /**
     * Obtiene los atributos actId, actCodigo y actDescripcion del jsp, estos
     * valores se guardan en sesión como id, codigo y descripcion, luego
     * muestra la página actualizarTablaCodigo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarTablaCodigo (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            session.setAttribute("id", (String)map.get("actId"));
            session.setAttribute("codigo", (String)map.get("actCodigo"));
            session.setAttribute("descripcion", (String)map.get("actDescripcion"));
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("actualizar");
    }
}
