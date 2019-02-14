/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.action;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.mantenimiento.service.UbigeoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ConsultaUbigeoAction extends DispatchAction {

    /**
     * Obtiene una lista con todos los registros de la tabla UBIGEO, luego
     * muestra la página consultaUbigeo.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward buscarUbigeo(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaUbigeos = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            UbigeoService ubigeoService = new UbigeoService();
            listaUbigeos = ubigeoService.buscarUbigeo((String)map.get("codigoUbigeo"), (String)map.get("nombreUbigeo"), (String)map.get("cucDesde"), (String)map.get("cucHasta"), (String)map.get("ultimoCuc"));
            session.setAttribute("listaUbigeos", listaUbigeos);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("consulta");
    }

    /**
     * Método que deriva a la página nuevoUbigeo.jsp.
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward nuevoUbigeo (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        com.bmp.sigeca.maestro.service.UbigeoService ubigeoService = new com.bmp.sigeca.maestro.service.UbigeoService();
        List listaDepartamento = ubigeoService.getListDepartamento();
        HttpSession session = request.getSession();
        session.setAttribute("listaDepartamento", listaDepartamento);
        session.setAttribute("listaProvincia", new ArrayList());
        session.setAttribute("listaDistrito", new ArrayList());
        return actionMapping.findForward("nuevo");
    }

    /**
     * Obtiene los atributos actCodigoUbigeo, actNombreUbigeo, actCucDesde,
     * actCucHasta y actUltimoCuc del jsp, estos valores se guardan en sesión
     * como codigoUbigeo, nombreUbigeo, cucDesde, cucHasta y ultimoCuc, luego
     * muestra la página actualizarUbigeo.jsp
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward actualizarUbigeo (ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            String codigoUbigeo = (String)map.get("actCodigoUbigeo");
            HashMap mapUbigeo = new HashMap();
            mapUbigeo.put("codDepartamento", (String)map.get("departamento"));
            mapUbigeo.put("codProvincia", (String)map.get("provincia"));
            mapUbigeo.put("codDistrito", (String)map.get("distrito"));

            session.setAttribute("codigoUbigeo", codigoUbigeo);
            session.setAttribute("nombreUbigeo", (String)map.get("actNombreUbigeo"));
            session.setAttribute("cucDesde", (String)map.get("actCucDesde"));
            session.setAttribute("cucHasta", (String)map.get("actCucHasta"));
            session.setAttribute("ultimoCuc", (String)map.get("actUltimoCuc"));
//            actualizarDescripcionesDepProDis(codigoUbigeo, request);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("actualizar");
    }

    private void actualizarDescripcionesDepProDis(String codigoUbigeo, HttpServletRequest request) throws DAOException
    {
        if(codigoUbigeo != null && codigoUbigeo.length() == 6)
        {
            String codigoDepartamento = codigoUbigeo.substring(0, 2);
            String codigoProvincia = codigoUbigeo.substring(2, 4);
            String codigoDistrito = codigoUbigeo.substring(4, 6);
            UbigeoService ubigeoService = new UbigeoService();
            request.setAttribute("descripcionDepartamento", ubigeoService.buscarDepartamento(codigoDepartamento));
            request.setAttribute("descripcionProvincia", ubigeoService.buscarProvincia(codigoProvincia, codigoDepartamento));
            request.setAttribute("descripcionDistrito", ubigeoService.buscarDistrito(codigoDistrito, codigoProvincia, codigoDepartamento));
        }
    }
}
