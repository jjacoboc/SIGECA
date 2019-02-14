/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.mantenimiento.service.TrabajadorService;
import com.bmp.sigeca.resource.Properties;
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
public class NuevoTrabajadorAction extends DispatchAction {

    public ActionForward insertarTrabajador(ActionMapping actionMapping,
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
                                    if(!service.existeTrabajadorByDni((String)map.get("dni"))) {
                                        long pk = service.getNextPK();
                                        map.put("id_trabajador", Properties.concatLeftCharacter("0", 3, Long.toString(pk)));
                                        result = service.insertarTrabajador(map);
                                        session.setAttribute("resultadoInsercion", result);

                                        listaTrabajadores = service.obtenerTrabajadores();
                                        session.setAttribute("listaTrabajadores", listaTrabajadores);
                                    }else{
                                        request.setAttribute("error", "Un trabajador con el dni ingresado ya se encuentra registrado.");
                                        return actionMapping.findForward("nuevo");
                                    }
                                }else{
                                    request.setAttribute("error", "Seleccione el estado del trabajador.");
                                    return actionMapping.findForward("nuevo");
                                }
                            }else{
                                request.setAttribute("error", "Seleccione el cargo del trabajador.");
                                return actionMapping.findForward("nuevo");
                            }
                        }else{
                            request.setAttribute("error", "Ingrese el apellido materno del trabajador.");
                            return actionMapping.findForward("nuevo");
                        }
                    }else{
                        request.setAttribute("error", "Ingrese el apellido paterno del trabajador.");
                        return actionMapping.findForward("nuevo");
                    }
                }else{
                    request.setAttribute("error", "Ingrese los nombres del trabajador.");
                    return actionMapping.findForward("nuevo");
                }
            }else{
                request.setAttribute("error", "Ingrese el dni del trabajador.");
                return actionMapping.findForward("nuevo");
            }

        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al intentar guardar el registro.");
            return actionMapping.findForward("nuevo");
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
