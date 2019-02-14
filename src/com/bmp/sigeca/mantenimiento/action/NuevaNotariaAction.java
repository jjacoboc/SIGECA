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
public class NuevaNotariaAction extends DispatchAction {

    /**
     * Inserta un nuevo registro en la tabla NOTARIA, lo parámetros obtenidos
     * del jsp son codigoNotaria, nombreNotaria y codigoUbigeo, luego de la
     * inserción obtiene todos los registros de NOTARIA y los guarda en sesión
     * como listaNotarias, finalmente muestra la página consultaNotaria.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward insertarNotaria(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaNotarias = null;
        String codigoNotaria = "";
        String nombreNotaria = "";
        String codigoUbigeo = "";
        try {
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("codigoNotaria") && !"".equals((String)map.get("codigoNotaria"))){
                if(map.containsKey("codigoUbigeo") && !"".equals((String)map.get("codigoUbigeo"))){
                    codigoNotaria = map.get("codigoNotaria").toString().trim();
                    nombreNotaria = map.get("nombreNotaria").toString().trim();
                    codigoUbigeo = map.get("codigoUbigeo").toString().trim();
                    if(!existeRegistro(codigoNotaria)) {
                        if(existeUbigeo(codigoUbigeo)){
                            NotariaService notariaService = new NotariaService();
                            result = notariaService.insertarNotaria(codigoNotaria, nombreNotaria, codigoUbigeo);

                            listaNotarias = notariaService.obtenerNotarias();
                            session.setAttribute("listaNotarias", listaNotarias);
                        }else{
                            request.setAttribute("error", "El código de ubigeo no existe.");
                            return actionMapping.findForward("nuevo");
                        }
                    }else {
                        request.setAttribute("error", "El registro ya existe.");
                        return actionMapping.findForward("nuevo");
                    }
                }else{
                    request.setAttribute("error", "Ingrese el ubigeo de la notaría.");
                    return actionMapping.findForward("nuevo");
                }
            }else{
                request.setAttribute("error", "Ingrese el código de la notaría.");
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
