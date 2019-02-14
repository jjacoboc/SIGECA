/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.mantenimiento.service.NotariaService;
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
public class ConsultaNotariaAction extends DispatchAction {

    /**
     * Obtiene una lista con todos los registros de la tabla NOTARIA, luego
     * muestra la página consultaNotaria.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward buscarNotarias(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaNotarias = null;
        String codigoNotaria = "";
        String nombreNotaria = "";
        String codigoUbigeo = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            codigoNotaria = map.get("codigoNotaria").toString().trim();
            nombreNotaria = map.get("nombreNotaria").toString().trim();
            codigoUbigeo = map.get("codigoUbigeo").toString().trim();
            NotariaService notariaService = new NotariaService();
            listaNotarias = notariaService.buscarNotarias(codigoNotaria, nombreNotaria, codigoUbigeo);
            session.setAttribute("listaNotarias", listaNotarias);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Método que deriva a la página nuevaNotaria.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward nuevaNotaria (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        return actionMapping.findForward("nuevo");
    }

    /**
     * Obtiene los atributos actCodigoNotaria, actNombreNotaria y
     * actCodigoUbigeo del jsp, estos valores se guardan en sesión como
     * codigoNotaria, nombreNotaria y codigoUbigeo, luego muestra la página
     * actualizarNotaria.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarNotaria (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            session.setAttribute("codigoNotaria", (String)map.get("actCodigoNotaria"));
            session.setAttribute("nombreNotaria", (String)map.get("actNombreNotaria"));
            session.setAttribute("codigoUbigeo", (String)map.get("actCodigoUbigeo"));
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("actualizar");
    }
}
