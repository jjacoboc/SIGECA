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
public class ActualizarTablaCodigoAction extends DispatchAction {

    /**
     * Actualiza un registro en la tabla TABLAS_CODIGO, los parámetros obtenidos del
     * jsp son codigo y descripcion, luego de actualizar el registro,
     * obtiene todos los registros de TABLAS_CODIGO y los guarda en sesión como
     * listaTablasCodigos, finalmente muestra la página consultaTablaCodigo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarTablaCodigo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaTablasCodigos = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            TablasCodigosService tablasCodigosService = new TablasCodigosService();
            if(map.containsKey("id") && !"".equals((String)map.get("id"))){
                if(map.containsKey("codigo") && !"".equals((String)map.get("codigo"))){
                        result = tablasCodigosService.actualizarTablasCodigos((String)map.get("id"), (String)map.get("codigo"), (String)map.get("descripcion"));
                        session.setAttribute("resultadoInsercion", result);
                        listaTablasCodigos = tablasCodigosService.obtenerTablasCodigos();
                        session.setAttribute("listaTablasCodigos", listaTablasCodigos);

                 }else{
                    request.setAttribute("error", "Ingrese un codigo.");
                    return actionMapping.findForward("nuevo");
                 }

             }else{
                request.setAttribute("error", "Ingrese un ID.");
                return actionMapping.findForward("nuevo");
             }

            
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar actualizar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Elimina un registro de la tabla TABLAS_CODIGOS, luego de quitar el registro
     * muestra la página consultaTablaCodigo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward eliminarTablaCodigo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaTablasCodigos = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            TablasCodigosService tablasCodigosService = new TablasCodigosService();
            
            result = tablasCodigosService.eliminarTablaCodigo((String)map.get("codigo") , (String)map.get("id") );
            session.setAttribute("resultadoEliminacion", result);
            listaTablasCodigos = tablasCodigosService.obtenerTablasCodigos();
            session.setAttribute("listaTablasCodigos", listaTablasCodigos);
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar eliminar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Método que deriva a la página consultaTablasCodigos.jsp.
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
