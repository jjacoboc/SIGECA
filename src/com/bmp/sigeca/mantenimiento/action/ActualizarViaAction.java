/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.HabilitacionUrbanaService;
import com.bmp.sigeca.mantenimiento.service.UbigeoService;
import com.bmp.sigeca.mantenimiento.service.ViaService;
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
 * @author Jonatan Jacobo
 */
public class ActualizarViaAction extends DispatchAction {

    public ActionForward actualizarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaVia = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("tip_via") && !"".equals((String)map.get("tip_via"))){
                if(map.containsKey("cod_via") && !"".equals((String)map.get("cod_via"))){
                    if(map.containsKey("id_ubi_geo") && !"".equals((String)map.get("id_ubi_geo"))){
                        String id_via = map.get("id_ubi_geo").toString().trim()+map.get("cod_via").toString().trim();
                        map.put("id_via", id_via );
                        if(existeRegistro((String)map.get("id_via"))) {
                            if(existeUbigeo(map.get("id_ubi_geo").toString().trim())){
                                ViaService service = new ViaService();
                                result = service.actualizarVia(map);
                                session.setAttribute("resultadoInsercion", result);
                                listaVia = service.obtenerVias();
                                session.setAttribute("listaVia", listaVia);
      
                            }else{
                                request.setAttribute("error", "El código de ubigeo no existe.");
                                return actionMapping.findForward("actualizar");
                            }
                        }else{
                            request.setAttribute("error", "El código de vía que actualizará no existe.");
                            return actionMapping.findForward("actualizar");
                        }
                    }else{
                        request.setAttribute("error", "Ingrese el ubigeo de la vía.");
                        return actionMapping.findForward("actualizar");
                    }
                }else{
                    request.setAttribute("error", "Ingrese el código de la vía.");
                    return actionMapping.findForward("actualizar");
                }
            }else{
                request.setAttribute("error", "Seleccione el tipo de vía.");
                return actionMapping.findForward("actualizar");
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al intentar actualizar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    public ActionForward eliminarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaVia = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            map.put("id_via", map.get("id_ubi_geo").toString().trim()+map.get("cod_via").toString().trim());
            ViaService service = new ViaService();
            if(!service.existeViaHabUrb(map)){
                result = service.eliminarVia(map.get("id_via").toString().trim());
                session.setAttribute("resultadoEliminacion", result);
                listaVia = service.obtenerVias();
                session.setAttribute("listaVia", listaVia);
            }else{
                request.setAttribute("error", "La via no puede ser eliminada porque está siendo utilizada en una ficha registrada.");
                return actionMapping.findForward("actualizar");
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al intentar eliminar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    public ActionForward cancelar(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        return actionMapping.findForward("consulta");
    }

    /**
     * Retorna true si existe un registro con código igual a idHabUrba,
     * de lo contrario retorna false.
     * @param idHabUrba
     * @return boolean
     * @throws DAOException
     */
    boolean existeRegistro(String idVia) throws DAOException {
        ViaService service = new ViaService();
        return service.existeVia(idVia);
    }

    /**
     * Retorna true si existe un registro con código igual a idUbigeo,
     * de lo contrario retorna false.
     * @param idUbigeo
     * @return boolean
     * @throws DAOException
     */
    boolean existeUbigeo(String idUbigeo) throws DAOException {
        UbigeoService ubigeoService = new UbigeoService();
        return ubigeoService.existeUbigeo(idUbigeo);
    }

}
