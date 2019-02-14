/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.mantenimiento.service.TrabajadorService;
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
public class ActualizarTrabajadorAction extends DispatchAction {

    public ActionForward actualizarTrabajador(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaTrabajadores = null;
        HttpSession session = request.getSession();
        try {
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            if(map.containsKey("dni") && !"".equals((String)map.get("dni"))){
                if(map.containsKey("nombres") && !"".equals((String)map.get("nombres"))){
                    if(map.containsKey("ape_paterno") && !"".equals((String)map.get("ape_paterno"))){
                        if(map.containsKey("ape_materno") && !"".equals((String)map.get("ape_materno"))){
                            if(map.containsKey("id_cargo") && !"".equals((String)map.get("id_cargo"))){
                                if(map.containsKey("id_cargo") && !"".equals((String)map.get("id_cargo"))){
                                    TrabajadorService service = new TrabajadorService();
                                    if(service.existeTrabajadorByDni((String)map.get("dni"))) {
                                        result = service.actualizarTrabajador(map);
                                        session.setAttribute("resultadoInsercion", result);

                                        listaTrabajadores = service.obtenerTrabajadores();
                                        session.setAttribute("listaTrabajadores", listaTrabajadores);
                                    }else{
                                        request.setAttribute("error", "El trabajador con el dni ingresado no se encuentra registrado.");
                                        return actionMapping.findForward("actualizar");
                                    }
                                }else{
                                    request.setAttribute("error", "Seleccione el estado del trabajador.");
                                    return actionMapping.findForward("actualizar");
                                }
                            }else{
                                request.setAttribute("error", "Seleccione el cargo del trabajador.");
                                return actionMapping.findForward("actualizar");
                            }
                        }else{
                            request.setAttribute("error", "Ingrese el apellido materno del trabajador.");
                            return actionMapping.findForward("actualizar");
                        }
                    }else{
                        request.setAttribute("error", "Ingrese el apellido paterno del trabajador.");
                        return actionMapping.findForward("actualizar");
                    }
                }else{
                    request.setAttribute("error", "Ingrese los nombres del trabajador.");
                    return actionMapping.findForward("actualizar");
                }
            }else{
                request.setAttribute("error", "Ingrese el dni del trabajador.");
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

    public ActionForward eliminarTrabajador(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        long result = 0;
        List listaTrabajadores = null;
        HttpSession session = request.getSession();
        try{
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            TrabajadorService service = new TrabajadorService();
            if(service.existeTrabajadorByDni((String)map.get("dni"))) {
                result = service.eliminarTrabajador(map);
                session.setAttribute("resultadoEliminacion", result);

                listaTrabajadores = service.obtenerTrabajadores();
                session.setAttribute("listaTrabajadores", listaTrabajadores);
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

    /**
     * Método que deriva a la página consultaTrabajador.jsp.
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
