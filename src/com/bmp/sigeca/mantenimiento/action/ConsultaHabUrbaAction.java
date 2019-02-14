/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.mantenimiento.service.HabilitacionUrbanaService;
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
public class ConsultaHabUrbaAction extends DispatchAction {

    public ActionForward buscarHabUrba(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaHabUrba = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            map.put("id_hab_urba", (String)map.get("id_ubi_geo")+(String)map.get("cod_hab_urba"));
            HabilitacionUrbanaService service = new HabilitacionUrbanaService();
            listaHabUrba = service.buscarHabilitacionesUrbanas(map);
            session.setAttribute("listaHabUrba", listaHabUrba);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consulta");
    }

    public ActionForward nuevaHabUrba (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        return actionMapping.findForward("nuevo");
    }

    public ActionForward actualizarHabUrba (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            session.setAttribute("grupo_urba", (String)map.get("hid_grupo_urba"));
            session.setAttribute("cod_hab_urba", (String)map.get("hid_cod_hab_urba"));
            session.setAttribute("nom_hab_urba", (String)map.get("hid_nom_hab_urba"));
            session.setAttribute("tip_hab_urba", (String)map.get("hid_tip_hab_urba"));
            session.setAttribute("id_ubi_geo", (String)map.get("hid_id_ubi_geo"));
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("actualizar");
    }

}
