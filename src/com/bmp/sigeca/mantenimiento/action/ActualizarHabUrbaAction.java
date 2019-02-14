/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.HabilitacionUrbanaService;
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
 * @author Jonatan Jacobo
 */
public class ActualizarHabUrbaAction extends DispatchAction {

    public ActionForward actualizarHabUrba(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaHabUrba = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("cod_hab_urba") && !"".equals((String)map.get("cod_hab_urba"))){
                if(map.containsKey("tip_hab_urba") && !"".equals((String)map.get("tip_hab_urba"))){
                    if(map.containsKey("id_ubi_geo") && !"".equals((String)map.get("id_ubi_geo"))){
                        map.put("id_hab_urba", (String)map.get("id_ubi_geo")+(String)map.get("cod_hab_urba"));
                        
                            if(existeUbigeo((String)map.get("id_ubi_geo"))){
                                HabilitacionUrbanaService service = new HabilitacionUrbanaService();
                                result = service.actualizarHabilitacionUrbana(map);
                                session.setAttribute("resultadoInsercion", result);
                                listaHabUrba = service.obtenerHabilitacionesUrbanas();
                                session.setAttribute("listaHabUrba", listaHabUrba);
                            }
                            else{
                                request.setAttribute("error", "El código de ubigeo no existe.");
                                return actionMapping.findForward("actualizar");
                            }
                        
                    }else{
                        request.setAttribute("error", "Ingrese el ubigeo de la habilitación urbana.");
                        return actionMapping.findForward("actualizar");
                    }
                }else{
                    request.setAttribute("error", "Seleccione el tipo de la habilitación urbana.");
                    return actionMapping.findForward("actualizar");
                }
            }else{
                request.setAttribute("error", "Ingrese el código de la habilitación urbana.");
                return actionMapping.findForward("actualizar");
            }
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar actualizar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    public ActionForward eliminarHabUrba(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaHabUrba = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            map.put("id_hab_urba", (String)map.get("id_ubi_geo")+(String)map.get("cod_hab_urba"));
            HabilitacionUrbanaService service = new HabilitacionUrbanaService();
            result = service.eliminarHabilitacionUrbana((String)map.get("id_hab_urba"));
            session.setAttribute("resultadoEliminacion", result);
            listaHabUrba = service.obtenerHabilitacionesUrbanas();
            session.setAttribute("listaHabUrba", listaHabUrba);
        }catch(Exception e){
            request.setAttribute("error", "Ocurrió un error al intentar eliminar el registro.");
            return actionMapping.findForward("actualizar");
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Retorna true si existe un registro con código igual a idHabUrba,
     * de lo contrario retorna false.
     * @param idHabUrba
     * @return boolean
     * @throws DAOException
     */
    boolean existeRegistro(String idHabUrba) throws DAOException {
        HabilitacionUrbanaService service = new HabilitacionUrbanaService();
        return service.existeHabilitacionUrbana(idHabUrba);
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

    public ActionForward cancelar(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        return actionMapping.findForward("consulta");
    }

}
