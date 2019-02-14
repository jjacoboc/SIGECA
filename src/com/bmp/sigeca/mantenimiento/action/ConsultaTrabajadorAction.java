/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.mantenimiento.service.CargoService;
import com.bmp.sigeca.mantenimiento.service.EstadoUsuarioService;
import com.bmp.sigeca.mantenimiento.service.TrabajadorService;
import java.util.ArrayList;
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
public class ConsultaTrabajadorAction extends DispatchAction {

    public ActionForward buscarTrabajador(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTrabajadores = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            TrabajadorService service = new TrabajadorService();
            listaTrabajadores = service.buscarTrabajadores(map);

            session.setAttribute("listaTrabajadores", listaTrabajadores);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consulta");
    }

    public ActionForward nuevoTrabajador (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaCargo = null;
        List listaEstado = null;
        try{
            HttpSession session = request.getSession();

            CargoService cargo = new CargoService();
            listaCargo = cargo.obtenerCargos();

            EstadoUsuarioService estado = new EstadoUsuarioService();
            listaEstado = estado.obtenerEstadosUsuarios();

            session.setAttribute("listaCargo", listaCargo);
            session.setAttribute("listaEstado", listaEstado);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("nuevo");
    }

    public ActionForward actualizarTrabajdor (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        HashMap mapTrabajador = null;
        List listaEstado = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            List listaTrabajadores = session.getAttribute("listaTrabajadores")!=null?(List)session.getAttribute("listaTrabajadores"):new ArrayList();
            if(listaTrabajadores!=null && !listaTrabajadores.isEmpty()){
                int ind = Integer.parseInt(map.get("indice")!=null?(String)map.get("indice"):"-1");
                if(ind!=-1){
                    mapTrabajador = (HashMap)listaTrabajadores.get(ind);
                }
            }

            EstadoUsuarioService estado = new EstadoUsuarioService();
            listaEstado = estado.obtenerEstadosUsuarios();
            
            session.setAttribute("listaEstado", listaEstado);
            session.setAttribute("mapTrabajador", mapTrabajador);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("actualizar");
    }
}
