/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.TablasCodigosService;
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
public class NuevaTablaCodigoAction extends DispatchAction {

    /**
     * Inserta un nuevo registro en la tabla TABLAS_CODIGOS, los parámetros
     * obtenidos del jsp son id, codigo y descripcion, luego de la inserción
     * obtiene todos los registros de TABLAS_CODIGOS y los guarda en sesión
     * como listaTablasCodigos, finalmente muestra la página
     * consultaTablasCodigos.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward insertarTablaCodigo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaTablasCodigos = null;
        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();


            if(map.containsKey("id") && !"".equals((String)map.get("id"))){

                if(map.containsKey("codigo") && !"".equals((String)map.get("codigo"))){

                        if(!existeRegistro( (String)map.get("codigo") , (String)map.get("id") )) {
                            TablasCodigosService tablasCodigosService = new TablasCodigosService();
                            result = tablasCodigosService.insertarTablaCodigo((String)map.get("id"), (String)map.get("codigo"), (String)map.get("descripcion"));
                            session.setAttribute("resultadoInsercion", result);

                            listaTablasCodigos = tablasCodigosService.obtenerTablasCodigos();
                            session.setAttribute("listaTablasCodigos", listaTablasCodigos);
                        }
                        else {
                            request.setAttribute("error", "El registro ya existe.");
                            return actionMapping.findForward("nuevo");
                        }


                 }else{
                    request.setAttribute("error", "Ingrese un codigo.");
                    return actionMapping.findForward("nuevo");
                 }

             }else{
                request.setAttribute("error", "Ingrese un ID.");
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
     * Retorna true si existe un registro con id_tabla igual a id, de lo
     * contrario retorna false.
     * @param codigoUso
     * @return boolean
     * @throws DAOException
     */
    boolean existeRegistro(String codigo , String id) throws DAOException {
        TablasCodigosService tablasCodigosService = new TablasCodigosService();
        return tablasCodigosService.existeTablaCodigo(codigo , id);
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
