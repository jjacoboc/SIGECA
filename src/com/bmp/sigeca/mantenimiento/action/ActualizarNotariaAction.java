/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.NotariaService;
import com.bmp.sigeca.mantenimiento.service.UbigeoService;
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
public class ActualizarNotariaAction extends DispatchAction {

    /**
     * Actualiza un registro en la tabla NOTARIA, los parámetros obtenidos del
     * jsp son nombreNotaria, codigoUbigeo, luego de actualizar el registro,
     * obtiene todos los registros de NOTARIA y los guarda en sesión como
     * listaNotaria, finalmente muestra la página consultaNotaria.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarNotaria(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaNotarias = null;
        String codigoNotaria = "";
        String nombreNotaria = "";
        String codigoUbigeo = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("codigoNotaria") && !"".equals((String)map.get("codigoNotaria"))){
                if(map.containsKey("codigoUbigeo") && !"".equals((String)map.get("codigoUbigeo"))){
                    codigoNotaria = map.get("codigoNotaria").toString().trim();
                    nombreNotaria = map.get("nombreNotaria").toString().trim();
                    codigoUbigeo = map.get("codigoUbigeo").toString().trim();
                    
                        if(existeUbigeo(codigoUbigeo)){
                            NotariaService notariaService = new NotariaService();
                            result = notariaService.actualizarNotaria(codigoNotaria, nombreNotaria, codigoUbigeo);
                            listaNotarias = notariaService.obtenerNotarias();
                            session.setAttribute("listaNotarias", listaNotarias);
                        }else{
                            request.setAttribute("error", "El código de ubigeo no existe.");
                            return actionMapping.findForward("actualizar");
                        }
                    
                }else{
                    request.setAttribute("error", "Ingrese el ubigeo de la notaría.");
                    return actionMapping.findForward("actualizar");
                }
            }else{
                request.setAttribute("error", "Ingrese el código de la notaría.");
                return actionMapping.findForward("actualizar");
            }
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar actualizar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Elimina un registro de la tabla NOTARIA, luego de quitar el registro
     * muestra la página consultaNotaria.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward eliminarNotaria(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaNotarias = null;
        String codigoNotaria = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            codigoNotaria = map.get("codigoNotaria").toString().trim();

            NotariaService notariaService = new NotariaService();
            if(!notariaService.existeNotariaEnRegistroLegal(codigoNotaria)){
                result = notariaService.eliminarNotaria(codigoNotaria);
                listaNotarias = notariaService.obtenerNotarias();
                session.setAttribute("listaNotarias", listaNotarias);
            }else{
                request.setAttribute("error", "No se puede eliminar la notaria seleccionada porque está siendo utilizado en una ficha catastral.");
                return actionMapping.findForward("actualizar");
            }
            
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar eliminar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Retorna true si existe un registro con código igual a codigoNotaria,
     * de lo contrario retorna false.
     * @param codigoNotaria
     * @return boolean
     * @throws DAOException
     */
    boolean existeRegistro(String codigoNotaria) throws DAOException {
        NotariaService notariaService = new NotariaService();
        return notariaService.existeNotaria(codigoNotaria);
    }

    /**
     * Retorna true si existe un registro con código igual a codigoUbigeo,
     * de lo contrario retorna false.
     * @param codigoUbigeo
     * @return boolean
     * @throws DAOException
     */
    boolean existeUbigeo(String codigoUbigeo) throws DAOException {
        UbigeoService ubigeoService = new UbigeoService();
        return ubigeoService.existeUbigeo(codigoUbigeo);
    }

    /**
     * Método que deriva a la página consultaNotaria.jsp.
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
