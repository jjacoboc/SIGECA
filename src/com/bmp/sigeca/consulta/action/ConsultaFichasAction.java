/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.consulta.action;

import com.bmp.sigeca.consulta.service.FichaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
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
public class ConsultaFichasAction extends DispatchAction {
    Logger logger = Logger.getLogger(ConsultaFichasAction.class);
    public static final String CODIGO_UNICO_CATASTRAL = "01";
    public static final String CODIGO_REFERENCIA_CATASTRAL = "02";
    public static final String CODIGO_PREDIAL_RENTA = "03";
    public static final String DIRECCION = "05";
    public static final String UBICACION = "06";
    public static final String TITULAR = "07";
    public static final String DNI_RUC = "08";

    public ActionForward buscarFichas(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        DynaActionForm form = (DynaActionForm)actionForm;
        HashMap map = (HashMap)form.getMap();
        String filtroBusqueda = (String)map.get("filtroBusqueda");
        String estado = (String)map.get("estado");
        estado = StringUtils.isNotBlank(estado) && "Todos".equals(estado)?null:estado;
        logger.debug("buscarFichas: " + map);
        if(CODIGO_UNICO_CATASTRAL.equals(filtroBusqueda))
        {
            buscarPorCodigoUnicoCatastral(map, estado, request);
        }
        if(CODIGO_REFERENCIA_CATASTRAL.equals(filtroBusqueda))
        {
            buscarPorCodigoReferenciaCatastral(map, estado, request);
        }
        if(CODIGO_PREDIAL_RENTA.equals(filtroBusqueda))
        {
            buscarPorCodigoPredialRenta(map, estado, request);
        }
        if(DIRECCION.equals(filtroBusqueda))
        {
            buscarPorDireccion(map, estado, request);
        }
        if(UBICACION.equals(filtroBusqueda))
        {
            buscarPorUbicacion(map, estado, request);
        }
        if(TITULAR.equals(filtroBusqueda))
        {
            buscarPorTitular(map, estado, request);
        }
        if(DNI_RUC.equals(filtroBusqueda))
        {
            buscarPorDniRuc(map, estado, request);
        }
        FichaService fichaService = new FichaService();
        request.setAttribute("listaEstados", fichaService.obtenerEstados());
        request.setAttribute("listaTiposVia", fichaService.obtenerTiposVia());
        actualizarSeleccion(filtroBusqueda, (String)map.get("estado"), request);
        return actionMapping.findForward("consulta");
    }

    private void buscarPorCodigoUnicoCatastral(Map parametros, String estado, HttpServletRequest request)
    {
        FichaService fichaService = new FichaService();
        String codigoUnicoCatastral = (String)parametros.get("codigoUnicoCatastral");
        try {
            List listaFichas = fichaService.buscarPorCodigoUnicoCatastral(codigoUnicoCatastral, estado);
            request.setAttribute("listaFichas", listaFichas);
        } catch (Exception e) {
            logger.error("Ocurrió un error al consultar Fichas", e);
        }
    }

    private void buscarPorCodigoReferenciaCatastral(Map parametros, String estado, HttpServletRequest request)
    {
        FichaService fichaService = new FichaService();
        String codigoReferenciaCatastral = (String)parametros.get("codigoReferenciaCatastral");
        try {
            List listaFichas = fichaService.buscarPorCodigoReferenciaCatastral(codigoReferenciaCatastral, estado);
            request.setAttribute("listaFichas", listaFichas);
        } catch (Exception e) {
            logger.error("Ocurrió un error al consultar Fichas", e);
        }
    }

    private void buscarPorCodigoPredialRenta(Map parametros, String estado, HttpServletRequest request)
    {
        FichaService fichaService = new FichaService();
        String codigoPredialRenta = (String)parametros.get("codigoPredialRenta");
        try {
            List listaFichas = fichaService.buscarPorCodigoPredialRenta(codigoPredialRenta, estado);
            request.setAttribute("listaFichas", listaFichas);
        } catch (Exception e) {
            logger.error("Ocurrió un error al consultar Fichas", e);
        }
    }

    private void buscarPorDireccion(Map parametros, String estado, HttpServletRequest request)
    {
        FichaService fichaService = new FichaService();
        String tipoVia = (String)parametros.get("tipoVia");
        String nombreVia = (String)parametros.get("nombreVia");
        String numeroMunicipal = (String)parametros.get("numeroMunicipal");
        try {
            List listaFichas = fichaService.buscarPorDireccion(tipoVia, nombreVia, numeroMunicipal, estado);
            request.setAttribute("listaFichas", listaFichas);
        } catch (Exception e) {
            logger.error("Ocurrió un error al consultar Fichas", e);
        }
    }

    private void buscarPorUbicacion(Map parametros, String estado, HttpServletRequest request)
    {
        FichaService fichaService = new FichaService();
        String lote = (String)parametros.get("lote");
        String manzana = (String)parametros.get("manzana");
        try {
            List listaFichas = fichaService.buscarPorUbicacion(lote, manzana, estado);
            request.setAttribute("listaFichas", listaFichas);
        } catch (Exception e) {
            logger.error("Ocurrió un error al consultar Fichas", e);
        }
    }

    private void buscarPorTitular(Map parametros, String estado, HttpServletRequest request)
    {
        FichaService fichaService = new FichaService();
        String nombre = (String)parametros.get("nombre");
        String apellidoPaterno = (String)parametros.get("apellidoPaterno");
        String apellidoMaterno = (String)parametros.get("apellidoMaterno");
        try {
            if(StringUtils.isNotBlank(nombre) || StringUtils.isNotBlank(apellidoPaterno) || StringUtils.isNotBlank(apellidoMaterno))
            {
                List listaFichas = fichaService.buscarPorTitular(nombre, apellidoPaterno, apellidoMaterno, estado);
                request.setAttribute("listaFichas", listaFichas);
            }
        } catch (Exception e) {
            logger.error("Ocurrió un error al consultar Fichas", e);
        }
    }

    private void buscarPorDniRuc(Map parametros, String estado, HttpServletRequest request)
    {
        FichaService fichaService = new FichaService();
        String dniRuc = (String)parametros.get("dniRuc");
        try {
            List listaFichas = fichaService.buscarPorDniRuc(dniRuc, estado);
            request.setAttribute("listaFichas", listaFichas);
        } catch (Exception e) {
            logger.error("Ocurrió un error al consultar Fichas", e);
        }
    }

    private void actualizarSeleccion(String filtroBusqueda, String estado, HttpServletRequest request)
    {
        request.setAttribute("filtroBusquedaSelected", filtroBusqueda);
        request.setAttribute("estadoSelected", estado);
    }
}
