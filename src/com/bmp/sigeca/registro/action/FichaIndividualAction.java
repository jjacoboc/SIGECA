/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.action;

import com.bmp.sigeca.maestro.service.ECCService;
import com.bmp.sigeca.maestro.service.ECSService;
import com.bmp.sigeca.registro.service.FichaIndividualService;
import com.bmp.sigeca.maestro.service.InstalacionService;
import com.bmp.sigeca.maestro.service.MEPService;
import com.bmp.sigeca.maestro.service.UCAService;
import com.bmp.sigeca.maestro.service.UbigeoService;
import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.registro.service.ConstruccionService;
import com.bmp.sigeca.registro.service.DocumentoService;
import com.bmp.sigeca.registro.service.FichaService;
import com.bmp.sigeca.registro.service.LitiganteService;
import com.bmp.sigeca.registro.service.ObrComplementariaService;
import com.bmp.sigeca.registro.service.ViaService;
import com.bmp.sigeca.resource.Properties;
import java.util.ArrayList;
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
public class FichaIndividualAction extends DispatchAction {

    protected Logger logg;

    /**
     * Método que registra una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exceptionw
     * @return
     */
    public ActionForward guardarFicha(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo guardarFicha");

        String mensaje = null;
        try{
            HttpSession session = request.getSession();
            UsuarioBean usuarioBean = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):null;
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();
            map.put("cuc", ((String)map.get("cucA")).concat((String)map.get("cucB")));
            map.put("codEstado", Properties.EstadoRegistrado);
            map.put("activo", Properties.Activa);
            
            map.put("id_usuario", usuarioBean.getCodUsuario());
            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            mapFicha.putAll(setearValores(mapFicha,map));
            mapFicha.putAll(map);
            mapFicha.put("codTipFicha", Properties.FichaCatastralUrbanaIndividual);
            
            if(Properties.existeUbigeoDistrital(mapFicha)){
                FichaIndividualService fichaService = new FichaIndividualService();
                if(map.get("codUsoPreCatastral")!=null && !"".equals((String)map.get("codUsoPreCatastral"))){
                    if(map.get("nomNotaria")!=null && !"".equals((String)map.get("nomNotaria"))){
//                    if(fichaService.existeCUC(mapFicha)){
                        long result = fichaService.guardarFicha(mapFicha);
                        if(result==1){
                            mensaje = "Ficha registrada con éxito.";
                            mapFicha.put("flagOK", true);
                            mapFicha.put("flagActualizar", true);
                        }else{
                            mensaje = "Hubo un error al registrar la ficha./nComuníquese con el administrador de red para mayor información.";
                        }
//                    }else{
//                        mensaje = "El CUC ingresado no se encuentra dentro del rango asignado.";
//                        mapFicha.remove("flagActualizar");
//                    }
                    }else{
                        mensaje = "Debe seleccionar una Notaria.";
                    }
                }else{
                    mensaje = "Debe seleccionar un Uso de Predio Catastral.";
                }
            }else{
                mensaje = "Código de Referencia Catastral incorrecto - verificar ubigeo";
            }

            session.setAttribute("mapFicha", mapFicha);
            request.setAttribute("mensaje", mensaje);
            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaIndividual");
    }

    /**
     * Método que obtiene la lista de Fichas Catastrales.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward obtenerListaFicha(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo obtenerListaFicha");

        List lista = null;
        try{
            HttpSession session = request.getSession();
            
            FichaService fichaService = new FichaService();
            lista = fichaService.obtenerListaFicha(new HashMap());

            session.setAttribute("listaFicha", lista);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaIndividual");
    }

    /**
     * Método que setea los valores del request en mapas.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public HashMap setearValores(HashMap mapFicha,HashMap map) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo setearValores");

        HashMap mapCabecera = null;
        HashMap mapPredio = null;
        HashMap mapTitular = null;
        HashMap mapBienComun = null;
        HashMap mapNotaria = null;
        HashMap mapInscripcion = null;
        HashMap mapInformacion = null;
        
        try{
            mapFicha = mapFicha!=null?mapFicha:new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapFicha.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("cucA") && map.get("cucA")!=null && !"".equals((String)map.get("cucA"))
                    && map.containsKey("cucB") && map.get("cucB")!=null && !"".equals((String)map.get("cucB")))
                mapFicha.put("cuc", ((String)map.get("cucA")).concat((String)map.get("cucB")));
            if(map.containsKey("numFichLote") && map.get("numFichLote")!=null && !"".equals((String)map.get("numFichLote")))
                mapFicha.put("numFichLote", (String)map.get("numFichLote"));
            if(map.containsKey("numTotFichLote") && map.get("numTotFichLote")!=null && !"".equals((String)map.get("numTotFichLote")))
                mapFicha.put("numTotFichLote", (String)map.get("numTotFichLote"));
            if(map.containsKey("codEvaPreCatastral") && map.get("codEvaPreCatastral")!=null && !"".equals((String)map.get("codEvaPreCatastral")))
                mapFicha.put("codEvaPreCatastral", (String)map.get("codEvaPreCatastral"));
            if(map.containsKey("areTerInvLotColindante") && map.get("areTerInvLotColindante")!=null && !"".equals((String)map.get("areTerInvLotColindante")))
                mapFicha.put("areTerInvLotColindante", (String)map.get("areTerInvLotColindante"));
            if(map.containsKey("areTerInvJarAislamiento") && map.get("areTerInvJarAislamiento")!=null && !"".equals((String)map.get("areTerInvJarAislamiento")))
                mapFicha.put("areTerInvJarAislamiento", (String)map.get("areTerInvJarAislamiento"));
            if(map.containsKey("areTerInvArePublica") && map.get("areTerInvArePublica")!=null && !"".equals((String)map.get("areTerInvArePublica")))
                mapFicha.put("areTerInvArePublica", (String)map.get("areTerInvArePublica"));
            if(map.containsKey("areTerInvAreIntangible") && map.get("areTerInvAreIntangible")!=null && !"".equals((String)map.get("areTerInvAreIntangible")))
                mapFicha.put("areTerInvAreIntangible", (String)map.get("areTerInvAreIntangible"));
            if(map.containsKey("observacion") && map.get("observacion")!=null && !"".equals((String)map.get("observacion")))
                mapFicha.put("observacion", (String)map.get("observacion"));
            if(map.containsKey("dniDeclarante") && map.get("dniDeclarante")!=null && !"".equals((String)map.get("dniDeclarante")))
                mapFicha.put("dniDeclarante", (String)map.get("dniDeclarante"));
            if(map.containsKey("nomDeclarante") && map.get("nomDeclarante")!=null && !"".equals((String)map.get("nomDeclarante")))
                mapFicha.put("nomDeclarante", (String)map.get("nomDeclarante"));
            if(map.containsKey("apePatDeclarante") && map.get("apePatDeclarante")!=null && !"".equals((String)map.get("apePatDeclarante")))
                mapFicha.put("apePatDeclarante", (String)map.get("apePatDeclarante"));
            if(map.containsKey("apeMatDeclarante") && map.get("apeMatDeclarante")!=null && !"".equals((String)map.get("apeMatDeclarante")))
                mapFicha.put("apeMatDeclarante", (String)map.get("apeMatDeclarante"));
            if(map.containsKey("fecFirDeclarante") && map.get("fecFirDeclarante")!=null && !"".equals((String)map.get("fecFirDeclarante")))
                mapFicha.put("fecFirDeclarante", (String)map.get("fecFirDeclarante"));
            if(map.containsKey("idSupervisor") && map.get("idSupervisor")!=null && !"".equals((String)map.get("idSupervisor")))
                mapFicha.put("idSupervisor", (String)map.get("idSupervisor"));
            if(map.containsKey("dniSupervisor") && map.get("dniSupervisor")!=null && !"".equals((String)map.get("dniSupervisor")))
                mapFicha.put("dniSupervisor", (String)map.get("dniSupervisor"));
            if(map.containsKey("nomSupervisor") && map.get("nomSupervisor")!=null && !"".equals((String)map.get("nomSupervisor")))
                mapFicha.put("nomSupervisor", (String)map.get("nomSupervisor"));
            if(map.containsKey("apeSupervisor") && map.get("apeSupervisor")!=null && !"".equals((String)map.get("apeSupervisor")))
                mapFicha.put("apeSupervisor", (String)map.get("apeSupervisor"));
            if(map.containsKey("fecFirSupervisor") && map.get("fecFirSupervisor")!=null && !"".equals((String)map.get("fecFirSupervisor")))
                mapFicha.put("fecFirSupervisor", (String)map.get("fecFirSupervisor"));
            if(map.containsKey("idTecnico") && map.get("idTecnico")!=null && !"".equals((String)map.get("idTecnico")))
                mapFicha.put("idTecnico", (String)map.get("idTecnico"));
            if(map.containsKey("dniTecCatastral") && map.get("dniTecCatastral")!=null && !"".equals((String)map.get("dniTecCatastral")))
                mapFicha.put("dniTecCatastral", (String)map.get("dniTecCatastral"));
            if(map.containsKey("nomTecCatastral") && map.get("nomTecCatastral")!=null && !"".equals((String)map.get("nomTecCatastral")))
                mapFicha.put("nomTecCatastral", (String)map.get("nomTecCatastral"));
            if(map.containsKey("apeTecCatastral") && map.get("apeTecCatastral")!=null && !"".equals((String)map.get("apeTecCatastral")))
                mapFicha.put("apeTecCatastral", (String)map.get("apeTecCatastral"));
            if(map.containsKey("fecFirTecCatastral") && map.get("fecFirTecCatastral")!=null && !"".equals((String)map.get("fecFirTecCatastral")))
                mapFicha.put("fecFirTecCatastral", (String)map.get("fecFirTecCatastral"));
            if(map.containsKey("idVerificador") && map.get("idVerificador")!=null && !"".equals((String)map.get("idVerificador")))
                mapFicha.put("idVerificador", (String)map.get("idVerificador"));
            if(map.containsKey("dniVerCatastral") && map.get("dniVerCatastral")!=null && !"".equals((String)map.get("dniVerCatastral")))
                mapFicha.put("dniVerCatastral", (String)map.get("dniVerCatastral"));
            if(map.containsKey("numRegVerCatastral") && map.get("numRegVerCatastral")!=null && !"".equals((String)map.get("numRegVerCatastral")))
                mapFicha.put("numRegVerCatastral", (String)map.get("numRegVerCatastral"));
            if(map.containsKey("nomVerCatastral") && map.get("nomVerCatastral")!=null && !"".equals((String)map.get("nomVerCatastral")))
                mapFicha.put("nomVerCatastral", (String)map.get("nomVerCatastral"));
            if(map.containsKey("apeVerCatastral") && map.get("apeVerCatastral")!=null && !"".equals((String)map.get("apeVerCatastral")))
                mapFicha.put("apeVerCatastral", (String)map.get("apeVerCatastral"));
            if(map.containsKey("fecFirVerCatastral") && map.get("fecFirVerCatastral")!=null && !"".equals((String)map.get("fecFirVerCatastral")))
                mapFicha.put("fecFirVerCatastral", (String)map.get("fecFirVerCatastral"));

            mapCabecera = mapFicha.get("mapCabecera")!=null?(HashMap)mapFicha.get("mapCabecera"):new HashMap();
            if(map.containsKey("cucA") && map.get("cucA")!=null && !"".equals((String)map.get("cucA")))
                mapCabecera.put("cucA", ((String)map.get("cucA")));
            if(map.containsKey("cucB") && map.get("cucB")!=null && !"".equals((String)map.get("cucB")))
                mapCabecera.put("cucB", ((String)map.get("cucB")));
            if(map.containsKey("cucA") && map.get("cucA")!=null && !"".equals((String)map.get("cucA"))
                    && map.containsKey("cucB") && map.get("cucB")!=null && !"".equals((String)map.get("cucB")))
                mapCabecera.put("cuc", ((String)map.get("cucA")).concat((String)map.get("cucB")));
            if(map.containsKey("codHojCatastral") && map.get("codHojCatastral")!=null && !"".equals((String)map.get("codHojCatastral")))
                mapCabecera.put("codHojCatastral", (String)map.get("codHojCatastral"));
            if(map.containsKey("codRefCatastral") && map.get("codRefCatastral")!=null && !"".equals((String)map.get("codRefCatastral")))
                mapCabecera.put("codRefCatastral", (String)map.get("codRefCatastral"));
            if(map.containsKey("dpto") && map.get("dpto")!=null && !"".equals((String)map.get("dpto")))
                mapCabecera.put("codDepartamento", (String)map.get("dpto"));
            if(map.containsKey("prov") && map.get("prov")!=null && !"".equals((String)map.get("prov")))
                mapCabecera.put("codProvincia", (String)map.get("prov"));
            if(map.containsKey("dist") && map.get("dist")!=null && !"".equals((String)map.get("dist")))
                mapCabecera.put("codDistrito", (String)map.get("dist"));
            if(map.containsKey("sector") && map.get("sector")!=null && !"".equals((String)map.get("sector")))
                mapCabecera.put("sector", (String)map.get("sector"));
            if(map.containsKey("manzana") && map.get("manzana")!=null && !"".equals((String)map.get("manzana")))
                mapCabecera.put("manzana", (String)map.get("manzana"));
            if(map.containsKey("lote") && map.get("lote")!=null && !"".equals((String)map.get("lote")))
                mapCabecera.put("lote", (String)map.get("lote"));
            if(map.containsKey("edifica") && map.get("edifica")!=null && !"".equals((String)map.get("edifica")))
                mapCabecera.put("edifica", (String)map.get("edifica"));
            if(map.containsKey("piso") && map.get("piso")!=null && !"".equals((String)map.get("piso")))
                mapCabecera.put("piso", (String)map.get("piso"));
            if(map.containsKey("entrada") && map.get("entrada")!=null && !"".equals((String)map.get("entrada")))
                mapCabecera.put("entrada", (String)map.get("entrada"));
            if(map.containsKey("unidad") && map.get("unidad")!=null && !"".equals((String)map.get("unidad")))
                mapCabecera.put("unidad", (String)map.get("unidad"));
            if(map.containsKey("dc") && map.get("dc")!=null && !"".equals((String)map.get("dc")))
                mapCabecera.put("dc", (String)map.get("dc"));
            if(map.containsKey("codConRenta") && map.get("codConRenta")!=null && !"".equals((String)map.get("codConRenta")))
                mapCabecera.put("codConRenta", (String)map.get("codConRenta"));
            if(map.containsKey("codPreRenta") && map.get("codPreRenta")!=null && !"".equals((String)map.get("codPreRenta")))
                mapCabecera.put("codPreRenta", (String)map.get("codPreRenta"));
            if(map.containsKey("uniAcuCodPreRenta") && map.get("uniAcuCodPreRenta")!=null && !"".equals((String)map.get("uniAcuCodPreRenta")))
                mapCabecera.put("uniAcuCodPreRenta", (String)map.get("uniAcuCodPreRenta"));
            if(map.containsKey("zona") && map.get("zona")!=null && !"".equals((String)map.get("zona")))
                mapCabecera.put("zona", (String)map.get("zona"));
            if(map.containsKey("uniOrgCatRural") && map.get("uniOrgCatRural")!=null && !"".equals((String)map.get("uniOrgCatRural")))
                mapCabecera.put("uniOrgCatRural", (String)map.get("uniOrgCatRural"));
            if(map.containsKey("uniCatastral") && map.get("uniCatastral")!=null && !"".equals((String)map.get("uniCatastral")))
                mapCabecera.put("uniCatastral", (String)map.get("uniCatastral"));
            if(map.containsKey("coorEste") && map.get("coorEste")!=null && !"".equals((String)map.get("coorEste")))
                mapCabecera.put("coorEste", (String)map.get("coorEste"));
            if(map.containsKey("coorNorte") && map.get("coorNorte")!=null && !"".equals((String)map.get("coorNorte")))
                mapCabecera.put("coorNorte", (String)map.get("coorNorte"));
            /*
            String codRefCatastral = " ";
            codRefCatastral.concat((String)map.get("dpto")).concat((String)map.get("prov")).concat((String)map.get("dist"));
            codRefCatastral.concat((String)map.get("sector")).concat((String)map.get("manzana")).concat((String)map.get("lote"));
            codRefCatastral.concat((String)map.get("edifica")).concat((String)map.get("entrada")).concat((String)map.get("piso"));
            codRefCatastral.concat((String)map.get("unidad")).concat((String)map.get("dc"));
            mapCabecera.put("codRefCatastral", codRefCatastral.trim());
            */
            
            mapPredio = mapFicha.get("mapPredio")!=null?(HashMap)mapFicha.get("mapPredio"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapPredio.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codTipEdificacionPre") && map.get("codTipEdificacionPre")!=null && !"".equals((String)map.get("codTipEdificacionPre")))
                mapPredio.put("codTipEdificacion", (String)map.get("codTipEdificacionPre"));
            if(map.containsKey("nomEdificacionPre") && map.get("nomEdificacionPre")!=null && !"".equals((String)map.get("nomEdificacionPre")))
                mapPredio.put("nomEdificacion", (String)map.get("nomEdificacionPre"));
            if(map.containsKey("codTipInteriorPre") && map.get("codTipInteriorPre")!=null && !"".equals((String)map.get("codTipInteriorPre")))
                mapPredio.put("codTipInterior", (String)map.get("codTipInteriorPre"));
            if(map.containsKey("numInteriorPre") && map.get("numInteriorPre")!=null && !"".equals((String)map.get("numInteriorPre")))
                mapPredio.put("numInterior", (String)map.get("numInteriorPre"));
            if(map.containsKey("codHUPre") && map.get("codHUPre")!=null && !"".equals((String)map.get("codHUPre")))
                mapPredio.put("codHabUrbana", (String)map.get("codHUPre"));
            if(map.containsKey("nomHUPre") && map.get("nomHUPre")!=null && !"".equals((String)map.get("nomHUPre")))
                mapPredio.put("nomHabUrbana", (String)map.get("nomHUPre"));
            if(map.containsKey("sectorPre") && map.get("sectorPre")!=null && !"".equals((String)map.get("sectorPre")))
                mapPredio.put("sector", (String)map.get("sectorPre"));
            if(map.containsKey("manzanaPre") && map.get("manzanaPre")!=null && !"".equals((String)map.get("manzanaPre")))
                mapPredio.put("manzana", (String)map.get("manzanaPre"));
            if(map.containsKey("lotePre") && map.get("lotePre")!=null && !"".equals((String)map.get("lotePre")))
                mapPredio.put("lote", (String)map.get("lotePre"));
            if(map.containsKey("sublotePre") && map.get("sublotePre")!=null && !"".equals((String)map.get("sublotePre")))
                mapPredio.put("sublote", (String)map.get("sublotePre"));
            if(map.containsKey("codClaPredio") && map.get("codClaPredio")!=null && !"".equals((String)map.get("codClaPredio")))
                mapPredio.put("codClaPredio", (String)map.get("codClaPredio"));
            if(map.containsKey("codSubClaPredio") && map.get("codSubClaPredio")!=null && !"".equals((String)map.get("codSubClaPredio")))
                mapPredio.put("codSubClaPredio", (String)map.get("codSubClaPredio"));
            if(map.containsKey("codUbiPreCatastral") && map.get("codUbiPreCatastral")!=null && !"".equals((String)map.get("codUbiPreCatastral")))
                mapPredio.put("codUbiPreCatastral", (String)map.get("codUbiPreCatastral"));
            if(map.containsKey("codUsoPreCatastral") && map.get("codUsoPreCatastral")!=null && !"".equals((String)map.get("codUsoPreCatastral")))
                mapPredio.put("codUsoPreCatastral", (String)map.get("codUsoPreCatastral"));
            if(map.containsKey("desUsoPreCatastral") && map.get("desUsoPreCatastral")!=null && !"".equals((String)map.get("desUsoPreCatastral")))
                mapPredio.put("desUsoPreCatastral", (String)map.get("desUsoPreCatastral"));
            if(map.containsKey("estructuracion") && map.get("estructuracion")!=null && !"".equals((String)map.get("estructuracion")))
                mapPredio.put("estructuracion", (String)map.get("estructuracion"));
            if(map.containsKey("zonificacion") && map.get("zonificacion")!=null && !"".equals((String)map.get("zonificacion")))
                mapPredio.put("zonificacion", (String)map.get("zonificacion"));
            if(map.containsKey("areTerTitulo") && map.get("areTerTitulo")!=null && !"".equals((String)map.get("areTerTitulo")))
                mapPredio.put("areTerTitulo", (String)map.get("areTerTitulo"));
            if(map.containsKey("areTerDeclarada") && map.get("areTerDeclarada")!=null && !"".equals((String)map.get("areTerDeclarada")))
                mapPredio.put("areTerDeclarada", (String)map.get("areTerDeclarada"));
            if(map.containsKey("areTerVerificada") && map.get("areTerVerificada")!=null && !"".equals((String)map.get("areTerVerificada")))
                mapPredio.put("areTerVerificada", (String)map.get("areTerVerificada"));
            if(map.containsKey("medCamFrente") && map.get("medCamFrente")!=null && !"".equals((String)map.get("medCamFrente")))
                mapPredio.put("medCamFrente", (String)map.get("medCamFrente"));
            if(map.containsKey("medTitFrente") && map.get("medTitFrente")!=null && !"".equals((String)map.get("medTitFrente")))
                mapPredio.put("medTitFrente", (String)map.get("medTitFrente"));
            if(map.containsKey("colCamFrente") && map.get("colCamFrente")!=null && !"".equals((String)map.get("colCamFrente")))
                mapPredio.put("colCamFrente", (String)map.get("colCamFrente"));
            if(map.containsKey("colTitFrente") && map.get("colTitFrente")!=null && !"".equals((String)map.get("colTitFrente")))
                mapPredio.put("colTitFrente", (String)map.get("colTitFrente"));
            if(map.containsKey("medCamDerecha") && map.get("medCamDerecha")!=null && !"".equals((String)map.get("medCamDerecha")))
                mapPredio.put("medCamDerecha", (String)map.get("medCamDerecha"));
            if(map.containsKey("medTitDerecha") && map.get("medTitDerecha")!=null && !"".equals((String)map.get("medTitDerecha")))
                mapPredio.put("medTitDerecha", (String)map.get("medTitDerecha"));
            if(map.containsKey("colCamDerecha") && map.get("colCamDerecha")!=null && !"".equals((String)map.get("colCamDerecha")))
                mapPredio.put("colCamDerecha", (String)map.get("colCamDerecha"));
            if(map.containsKey("colTitDerecha") && map.get("colTitDerecha")!=null && !"".equals((String)map.get("colTitDerecha")))
                mapPredio.put("colTitDerecha", (String)map.get("colTitDerecha"));
            if(map.containsKey("medCamIzquierda") && map.get("medCamIzquierda")!=null && !"".equals((String)map.get("medCamIzquierda")))
                mapPredio.put("medCamIzquierda", (String)map.get("medCamIzquierda"));
            if(map.containsKey("medTitIzquierda") && map.get("medTitIzquierda")!=null && !"".equals((String)map.get("medTitIzquierda")))
                mapPredio.put("medTitIzquierda", (String)map.get("medTitIzquierda"));
            if(map.containsKey("colCamIzquierda") && map.get("colCamIzquierda")!=null && !"".equals((String)map.get("colCamIzquierda")))
                mapPredio.put("colCamIzquierda", (String)map.get("colCamIzquierda"));
            if(map.containsKey("colTitIzquierda") && map.get("colTitIzquierda")!=null && !"".equals((String)map.get("colTitIzquierda")))
                mapPredio.put("colTitIzquierda", (String)map.get("colTitIzquierda"));
            if(map.containsKey("medCamFondo") && map.get("medCamFondo")!=null && !"".equals((String)map.get("medCamFondo")))
                mapPredio.put("medCamFondo", (String)map.get("medCamFondo"));
            if(map.containsKey("medTitFondo") && map.get("medTitFondo")!=null && !"".equals((String)map.get("medTitFondo")))
                mapPredio.put("medTitFondo", (String)map.get("medTitFondo"));
            if(map.containsKey("colCamFondo") && map.get("colCamFondo")!=null && !"".equals((String)map.get("colCamFondo")))
                mapPredio.put("colCamFondo", (String)map.get("colCamFondo"));
            if(map.containsKey("colTitFondo") && map.get("colTitFondo")!=null && !"".equals((String)map.get("colTitFondo")))
                mapPredio.put("colTitFondo", (String)map.get("colTitFondo"));
            if(map.containsKey("luz") && map.get("luz")!=null && !"".equals((String)map.get("luz")))
                mapPredio.put("luz", (String)map.get("luz"));
            if(map.containsKey("numSumLuz") && map.get("numSumLuz")!=null && !"".equals((String)map.get("numSumLuz")))
                mapPredio.put("numSumLuz", (String)map.get("numSumLuz"));
            if(map.containsKey("agua") && map.get("agua")!=null && !"".equals((String)map.get("agua")))
                mapPredio.put("agua", (String)map.get("agua"));
            if(map.containsKey("numConAgua") && map.get("numConAgua")!=null && !"".equals((String)map.get("numConAgua")))
                mapPredio.put("numConAgua", (String)map.get("numConAgua"));
            if(map.containsKey("telefono") && map.get("telefono")!=null && !"".equals((String)map.get("telefono")))
                mapPredio.put("telefono", (String)map.get("telefono"));
            if(map.containsKey("numTelefono") && map.get("numTelefono")!=null && !"".equals((String)map.get("numTelefono")))
                mapPredio.put("numTelefono", (String)map.get("numTelefono"));
            if(map.containsKey("desague") && map.get("desague")!=null && !"".equals((String)map.get("desague")))
                mapPredio.put("desague", (String)map.get("desague"));
            if(map.containsKey("codConTitular") && map.get("codConTitular")!=null && !"".equals((String)map.get("codConTitular")))
                mapPredio.put("codConTitular", (String)map.get("codConTitular"));
            if(map.containsKey("codForAdquisicion") && map.get("codForAdquisicion")!=null && !"".equals((String)map.get("codForAdquisicion")))
                mapPredio.put("codForAdquisicion", (String)map.get("codForAdquisicion"));
            if(map.containsKey("codForAdqPredio") && map.get("codForAdqPredio")!=null && !"".equals((String)map.get("codForAdqPredio")))
                mapPredio.put("codForAdqPredio", (String)map.get("codForAdqPredio"));
            if(map.containsKey("fecAdquisicion") && map.get("fecAdquisicion")!=null && !"".equals((String)map.get("fecAdquisicion")))
                mapPredio.put("fecAdquisicion", (String)map.get("fecAdquisicion"));
            if(map.containsKey("codConEspPredio") && map.get("codConEspPredio")!=null && !"".equals((String)map.get("codConEspPredio")))
                mapPredio.put("codConEspPredio", (String)map.get("codConEspPredio"));
            if(map.containsKey("numResExoPredio") && map.get("numResExoPredio")!=null && !"".equals((String)map.get("numResExoPredio")))
                mapPredio.put("numResExoPredio", (String)map.get("numResExoPredio"));
            if(map.containsKey("porcentaje") && map.get("porcentaje")!=null && !"".equals((String)map.get("porcentaje")))
                mapPredio.put("porcentaje", (String)map.get("porcentaje"));
            if(map.containsKey("fecInicio") && map.get("fecInicio")!=null && !"".equals((String)map.get("fecInicio")))
                mapPredio.put("fecInicio", (String)map.get("fecInicio"));
            if(map.containsKey("fecVencimiento") && map.get("fecVencimiento")!=null && !"".equals((String)map.get("fecVencimiento")))
                mapPredio.put("fecVencimiento", (String)map.get("fecVencimiento"));

            mapTitular = mapFicha.get("mapTitular")!=null?(HashMap)mapFicha.get("mapTitular"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapTitular.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codTipTitular") && map.get("codTipTitular")!=null && !"".equals((String)map.get("codTipTitular")))
                mapTitular.put("codTipTitular", (String)map.get("codTipTitular"));
            if(map.containsKey("codEstCivil") && map.get("codEstCivil")!=null && !"".equals((String)map.get("codEstCivil")))
                mapTitular.put("codEstCivil", (String)map.get("codEstCivil"));
            if(map.containsKey("codTipDocIdentidad") && map.get("codTipDocIdentidad")!=null && !"".equals((String)map.get("codTipDocIdentidad")))
                mapTitular.put("codTipDocIdentidad", (String)map.get("codTipDocIdentidad"));
            if(map.containsKey("numDocIdentidad") && map.get("numDocIdentidad")!=null && !"".equals((String)map.get("numDocIdentidad")))
                mapTitular.put("numDocIdentidad", (String)map.get("numDocIdentidad"));
            if(map.containsKey("nombre") && map.get("nombre")!=null && !"".equals((String)map.get("nombre")))
                mapTitular.put("nombre", (String)map.get("nombre"));
            if(map.containsKey("apePaterno") && map.get("apePaterno")!=null && !"".equals((String)map.get("apePaterno")))
                mapTitular.put("apePaterno", (String)map.get("apePaterno"));
            if(map.containsKey("apeMaterno") && map.get("apeMaterno")!=null && !"".equals((String)map.get("apeMaterno")))
                mapTitular.put("apeMaterno", (String)map.get("apeMaterno"));
            if(map.containsKey("codTipDocIdentidad2") && map.get("codTipDocIdentidad2")!=null && !"".equals((String)map.get("codTipDocIdentidad2")))
                mapTitular.put("codTipDocIdentidad2", (String)map.get("codTipDocIdentidad2"));
            if(map.containsKey("numDocIdentidad2") && map.get("numDocIdentidad2")!=null && !"".equals((String)map.get("numDocIdentidad2")))
                mapTitular.put("numDocIdentidad2", (String)map.get("numDocIdentidad2"));
            if(map.containsKey("nombre2") && map.get("nombre2")!=null && !"".equals((String)map.get("nombre2")))
                mapTitular.put("nombre2", (String)map.get("nombre2"));
            if(map.containsKey("apePaterno2") && map.get("apePaterno2")!=null && !"".equals((String)map.get("apePaterno2")))
                mapTitular.put("apePaterno2", (String)map.get("apePaterno2"));
            if(map.containsKey("apeMaterno2") && map.get("apeMaterno2")!=null && !"".equals((String)map.get("apeMaterno2")))
                mapTitular.put("apeMaterno2", (String)map.get("apeMaterno2"));
            if(map.containsKey("numRuc") && map.get("numRuc")!=null && !"".equals((String)map.get("numRuc")))
                mapTitular.put("numRuc", (String)map.get("numRuc"));
            if(map.containsKey("razSocial") && map.get("razSocial")!=null && !"".equals((String)map.get("razSocial")))
                mapTitular.put("razSocial", (String)map.get("razSocial"));
            if(map.containsKey("codPerJuridica") && map.get("codPerJuridica")!=null && !"".equals((String)map.get("codPerJuridica")))
                mapTitular.put("codPerJuridica", (String)map.get("codPerJuridica"));
            if(map.containsKey("codConEspTitular") && map.get("codConEspTitular")!=null && !"".equals((String)map.get("codConEspTitular")))
                mapTitular.put("codConEspTitular", (String)map.get("codConEspTitular"));
            if(map.containsKey("numResExoneracion") && map.get("numResExoneracion")!=null && !"".equals((String)map.get("numResExoneracion")))
                mapTitular.put("numResExoneracion", (String)map.get("numResExoneracion"));
            if(map.containsKey("numBolPensionista") && map.get("numBolPensionista")!=null && !"".equals((String)map.get("numBolPensionista")))
                mapTitular.put("numBolPensionista", (String)map.get("numBolPensionista"));
            if(map.containsKey("fecIniExoneracion") && map.get("fecIniExoneracion")!=null && !"".equals((String)map.get("fecIniExoneracion")))
                mapTitular.put("fecIniExoneracion", (String)map.get("fecIniExoneracion"));
            if(map.containsKey("fecVenExoneracion") && map.get("fecVenExoneracion")!=null && !"".equals((String)map.get("fecVenExoneracion")))
                mapTitular.put("fecVenExoneracion", (String)map.get("fecVenExoneracion"));
            if(map.containsKey("codDepartamento") && map.get("codDepartamento")!=null && !"".equals((String)map.get("codDepartamento")))
                mapTitular.put("codDepartamento", (String)map.get("codDepartamento"));
            if(map.containsKey("codProvincia") && map.get("codProvincia")!=null && !"".equals((String)map.get("codProvincia")))
                mapTitular.put("codProvincia", (String)map.get("codProvincia"));
            if(map.containsKey("codDistrito") && map.get("codDistrito")!=null && !"".equals((String)map.get("codDistrito")))
                mapTitular.put("codDistrito", (String)map.get("codDistrito"));
            if(map.containsKey("telefonoTit") && map.get("telefonoTit")!=null && !"".equals((String)map.get("telefonoTit")))
                mapTitular.put("telefono", (String)map.get("telefonoTit"));
            if(map.containsKey("anexoTit") && map.get("anexoTit")!=null && !"".equals((String)map.get("anexoTit")))
                mapTitular.put("anexo", (String)map.get("anexoTit"));
            if(map.containsKey("faxTit") && map.get("faxTit")!=null && !"".equals((String)map.get("faxTit")))
                mapTitular.put("fax", (String)map.get("faxTit"));
            if(map.containsKey("correoTit") && map.get("correoTit")!=null && !"".equals((String)map.get("correoTit")))
                mapTitular.put("corElectronico", (String)map.get("correoTit"));
            if(map.containsKey("codViaTit") && map.get("codViaTit")!=null && !"".equals((String)map.get("codViaTit")))
                mapTitular.put("codVia", (String)map.get("codViaTit"));
            if(map.containsKey("codTipViaTit") && map.get("codTipViaTit")!=null && !"".equals((String)map.get("codTipViaTit")))
                mapTitular.put("codTipVia", (String)map.get("codTipViaTit"));
            if(map.containsKey("nomViaTit") && map.get("nomViaTit")!=null && !"".equals((String)map.get("nomViaTit")))
                mapTitular.put("nomVia", (String)map.get("nomViaTit"));
            if(map.containsKey("numMunicipalTit") && map.get("numMunicipalTit")!=null && !"".equals((String)map.get("numMunicipalTit")))
                mapTitular.put("numMunicipal", (String)map.get("numMunicipalTit"));
            if(map.containsKey("nomEdificacionTit") && map.get("nomEdificacionTit")!=null && !"".equals((String)map.get("nomEdificacionTit")))
                mapTitular.put("nomEdificacion", (String)map.get("nomEdificacionTit"));
            if(map.containsKey("numInteriorTit") && map.get("numInteriorTit")!=null && !"".equals((String)map.get("numInteriorTit")))
                mapTitular.put("numInterior", (String)map.get("numInteriorTit"));
            if(map.containsKey("codHUTit") && map.get("codHUTit")!=null && !"".equals((String)map.get("codHUTit")))
                mapTitular.put("codHabUrbana", (String)map.get("codHUTit"));
            if(map.containsKey("nomHUTit") && map.get("nomHUTit")!=null && !"".equals((String)map.get("nomHUTit")))
                mapTitular.put("nomHabUrbana", (String)map.get("nomHUTit"));
            if(map.containsKey("sectorTit") && map.get("sectorTit")!=null && !"".equals((String)map.get("sectorTit")))
                mapTitular.put("sector", (String)map.get("sectorTit"));
            if(map.containsKey("manzanaTit") && map.get("manzanaTit")!=null && !"".equals((String)map.get("manzanaTit")))
                mapTitular.put("manzana", (String)map.get("manzanaTit"));
            if(map.containsKey("loteTit") && map.get("loteTit")!=null && !"".equals((String)map.get("loteTit")))
                mapTitular.put("lote", (String)map.get("loteTit"));
            if(map.containsKey("subloteTit") && map.get("subloteTit")!=null && !"".equals((String)map.get("subloteTit")))
                mapTitular.put("sublote", (String)map.get("subloteTit"));

            mapBienComun = mapFicha.get("mapBienComun")!=null?(HashMap)mapFicha.get("mapBienComun"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapBienComun.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("terLegal") && map.get("terLegal")!=null && !"".equals((String)map.get("terLegal")))
                mapBienComun.put("porTerLegBienComun", (String)map.get("terLegal"));
            if(map.containsKey("terFisico") && map.get("terFisico")!=null && !"".equals((String)map.get("terFisico")))
                mapBienComun.put("porTerFisBienComun", (String)map.get("terFisico"));
            if(map.containsKey("conLegal") && map.get("conLegal")!=null && !"".equals((String)map.get("conLegal")))
                mapBienComun.put("porConLegBienComun", (String)map.get("conLegal"));
            if(map.containsKey("conFisica") && map.get("conFisica")!=null && !"".equals((String)map.get("conFisica")))
                mapBienComun.put("porConFisBienComun", (String)map.get("conFisica"));

            mapNotaria = mapFicha.get("mapNotaria")!=null?(HashMap)mapFicha.get("mapNotaria"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapNotaria.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("nomNotaria") && map.get("nomNotaria")!=null && !"".equals((String)map.get("nomNotaria")))
                mapNotaria.put("nomNotaria", (String)map.get("nomNotaria"));
            if(map.containsKey("kardex") && map.get("kardex")!=null && !"".equals((String)map.get("kardex")))
                mapNotaria.put("kardex", (String)map.get("kardex"));
            if(map.containsKey("fecEscPublica") && map.get("fecEscPublica")!=null && !"".equals((String)map.get("fecEscPublica")))
                mapNotaria.put("fecEscPublica", (String)map.get("fecEscPublica"));

            mapInscripcion = mapFicha.get("mapInscripcion")!=null?(HashMap)mapFicha.get("mapInscripcion"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapInscripcion.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codTipParRegistral") && map.get("codTipParRegistral")!=null && !"".equals((String)map.get("codTipParRegistral")))
                mapInscripcion.put("codTipParRegistral", (String)map.get("codTipParRegistral"));
            if(map.containsKey("numPartida") && map.get("numPartida")!=null && !"".equals((String)map.get("numPartida")))
                mapInscripcion.put("numPartida", (String)map.get("numPartida"));
            if(map.containsKey("fojas") && map.get("fojas")!=null && !"".equals((String)map.get("fojas")))
                mapInscripcion.put("fojas", (String)map.get("fojas"));
            if(map.containsKey("asiento") && map.get("asiento")!=null && !"".equals((String)map.get("asiento")))
                mapInscripcion.put("asiento", (String)map.get("asiento"));
            if(map.containsKey("fecInsPredio") && map.get("fecInsPredio")!=null && !"".equals((String)map.get("fecInsPredio")))
                mapInscripcion.put("fecInsPredio", (String)map.get("fecInsPredio"));
            if(map.containsKey("codDecFabrica") && map.get("codDecFabrica")!=null && !"".equals((String)map.get("codDecFabrica")))
                mapInscripcion.put("codDecFabrica", (String)map.get("codDecFabrica"));
            if(map.containsKey("asiInsFabrica") && map.get("asiInsFabrica")!=null && !"".equals((String)map.get("asiInsFabrica")))
                mapInscripcion.put("asiInsFabrica", (String)map.get("asiInsFabrica"));
            if(map.containsKey("fecInsFabrica") && map.get("fecInsFabrica")!=null && !"".equals((String)map.get("fecInsFabrica")))
                mapInscripcion.put("fecInsFabrica", (String)map.get("fecInsFabrica"));

            mapInformacion = mapFicha.get("mapInformacion")!=null?(HashMap)mapFicha.get("mapInformacion"):new HashMap();
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha")))
                mapInformacion.put("numFicha", (String)map.get("numFicha"));
            if(map.containsKey("codConDeclarante") && map.get("codConDeclarante")!=null && !"".equals((String)map.get("codConDeclarante")))
                mapInformacion.put("codConDeclarante", (String)map.get("codConDeclarante"));
            if(map.containsKey("codEstLleFicha") && map.get("codEstLleFicha")!=null && !"".equals((String)map.get("codEstLleFicha")))
                mapInformacion.put("codEstLleFicha", (String)map.get("codEstLleFicha"));
            if(map.containsKey("numHabitantes") && map.get("numHabitantes")!=null && !"".equals((String)map.get("numHabitantes")))
                mapInformacion.put("numHabitantes", (String)map.get("numHabitantes"));
            if(map.containsKey("numFamilias") && map.get("numFamilias")!=null && !"".equals((String)map.get("numFamilias")))
                mapInformacion.put("numFamilias", (String)map.get("numFamilias"));
            if(map.containsKey("codMantenimiento") && map.get("codMantenimiento")!=null && !"".equals((String)map.get("codMantenimiento")))
                mapInformacion.put("codMantenimiento", (String)map.get("codMantenimiento"));

            mapFicha.put("mapCabecera", mapCabecera);
            mapFicha.put("mapPredio", mapPredio);
            mapFicha.put("mapTitular", mapTitular);
            mapFicha.put("mapBienComun", mapBienComun);
            mapFicha.put("mapNotaria", mapNotaria);
            mapFicha.put("mapInscripcion", mapInscripcion);
            mapFicha.put("mapInformacion", mapInformacion);
            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return mapFicha;
    }

    /**
     * Método que deriva a la página de Mantenimiento de Vía.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irAgregarVia");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);
            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("via");
    }

    /**
     * Método que agrega un vía a la lista de vías para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo agregarVia");

        List listaVia = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaVia") && mapFicha.get("listaVia")!=null ){
                listaVia = (List)mapFicha.get("listaVia");
            }else{
                listaVia = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }
            
            HashMap mapVia = new HashMap();
            mapVia.put("codVia", ((String)map.get("codVia")).trim());
            mapVia.put("codTipVia", ((String)map.get("codTipVia")).trim());
            mapVia.put("desTipVia", ((String)map.get("desTipVia")).trim());
            mapVia.put("nomVia", ((String)map.get("nomVia")).trim());
            mapVia.put("codTipPuerta", ((String)map.get("codTipPuerta")).trim());
            mapVia.put("desTipPuerta", ((String)map.get("desTipPuerta")).trim());
            mapVia.put("numMunicipal", ((String)map.get("numMunicipal")).trim());
            mapVia.put("codConNumeracion", ((String)map.get("codConNumeracion")).trim());
            mapVia.put("desConNumeracion", ((String)map.get("desConNumeracion")).trim());
            mapVia.put("numCerNumeracion", ((String)map.get("numCerNumeracion")).trim());

            listaVia.add(mapVia);

            mapFicha.put("listaVia", listaVia);
            session.setAttribute("mapFicha", mapFicha);
            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que elimina un vía a la lista de vías.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo eliminarVia");

        List listaVia = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaVia") && mapFicha.get("listaVia")!=null ){
                listaVia = (List)mapFicha.get("listaVia");
            }else{
                listaVia = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }

            int ind = Integer.parseInt((String)map.get("indListaVia"));
            HashMap mapVia = (HashMap)listaVia.get(ind);
            ViaService via = new ViaService();
            via.eliminarVia(mapVia);
            listaVia.remove(ind);

            mapFicha.put("listaVia", listaVia);
            session.setAttribute("mapFicha", mapFicha);
            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Mantenimiento de Vía.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irEditarVia");

        List listaVia = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);
            
            if(mapFicha.containsKey("listaVia") && mapFicha.get("listaVia")!=null ){
                listaVia = (List)mapFicha.get("listaVia");
            }else{
                listaVia = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaVia"));
            HashMap mapVia = (HashMap)listaVia.get(ind);

            request.setAttribute("viaBean", mapVia);
            request.setAttribute("indListaVia", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("via");
    }

    /**
     * Método que edita una vía de la lista de vías para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarVia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo editarVia");

        List listaVia = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaVia") && mapFicha.get("listaVia")!=null ){
                listaVia = (List)mapFicha.get("listaVia");
            }else{
                listaVia = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }

            int ind = Integer.parseInt((String)map.get("indListaVia"));
            
            HashMap mapVia = (HashMap)listaVia.get(ind);
            mapVia.put("codVia", ((String)map.get("codVia")).trim());
            mapVia.put("codTipVia", ((String)map.get("codTipVia")).trim());
            mapVia.put("desTipVia", ((String)map.get("desTipVia")).trim());
            mapVia.put("nomVia", ((String)map.get("nomVia")).trim());
            mapVia.put("codTipPuerta", ((String)map.get("codTipPuerta")).trim());
            mapVia.put("desTipPuerta", ((String)map.get("desTipPuerta")).trim());
            mapVia.put("numMunicipal", ((String)map.get("numMunicipal")).trim());
            mapVia.put("codConNumeracion", ((String)map.get("codConNumeracion")).trim());
            mapVia.put("desConNumeracion", ((String)map.get("desConNumeracion")).trim());
            mapVia.put("numCerNumeracion", ((String)map.get("numCerNumeracion")).trim());

            listaVia.set(ind, mapVia);

            mapFicha.put("listaVia", listaVia);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Mantenimiento de Construcción.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irAgregarConstruccion");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);
            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("construccion");
    }

    /**
     * Método que agrega una construcción a la lista de construcciones para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo agregarConstruccion");

        List listaConstruccion = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaConstruccion") && mapFicha.get("listaConstruccion")!=null ){
                listaConstruccion = (List)mapFicha.get("listaConstruccion");
            }else{
                listaConstruccion = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }else if(tipFicha.equals(Properties.FichaCatastralRural)){
                destino = "fichaRural";
            }

            HashMap construccion = new HashMap();
            construccion.put("numPiso", ((String)map.get("numPiso")).trim());
            construccion.put("fecConstruccion", ((String)map.get("fecConstruccion")).trim());
            construccion.put("codMEP", ((String)map.get("codMEP")).trim());
            construccion.put("desMEP", ((String)map.get("desMEP")).trim());
            construccion.put("codECC", ((String)map.get("codECC")).trim());
            construccion.put("desECC", ((String)map.get("desECC")).trim());
            construccion.put("codECS", ((String)map.get("codECS")).trim());
            construccion.put("desECS", ((String)map.get("desECS")).trim());
            construccion.put("muro", ((String)map.get("muro")).trim());
            construccion.put("techo", ((String)map.get("techo")).trim());
            construccion.put("pisos", ((String)map.get("pisos")).trim());
            construccion.put("puerta", ((String)map.get("puerta")).trim());
            construccion.put("revestimiento", ((String)map.get("revestimiento")).trim());
            construccion.put("bano", ((String)map.get("bano")).trim());
            construccion.put("instalaciones", ((String)map.get("instalaciones")).trim());
            construccion.put("areConDeclarada", ((String)map.get("areConDeclarada")).trim());
            construccion.put("areConVerificada", ((String)map.get("areConVerificada")).trim());
            construccion.put("codUCA", ((String)map.get("codUCA")).trim());
            construccion.put("desUCA", ((String)map.get("desUCA")).trim());
            construccion.put("areConstruida", ((String)map.get("areConstruida")).trim());

            listaConstruccion.add(construccion);

            mapFicha.put("listaConstruccion", listaConstruccion);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que elimina una construcción a la lista de construcciones.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo eliminarConstrucción");

        List listaConstruccion = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaConstruccion") && mapFicha.get("listaConstruccion")!=null ){
                listaConstruccion = (List)mapFicha.get("listaConstruccion");
            }else{
                listaConstruccion = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }else if(tipFicha.equals(Properties.FichaCatastralRural)){
                destino = "fichaRural";
            }

            int ind = Integer.parseInt((String)map.get("indListaConstruccion"));
            HashMap mapConstruccion = (HashMap)listaConstruccion.get(ind);
            ConstruccionService construccion = new ConstruccionService();
            construccion.eliminarConstruccion(mapConstruccion);
            listaConstruccion.remove(ind);

            mapFicha.put("listaConstruccion", listaConstruccion);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Mantenimiento de Construcción.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irEditarConstruccion");

        List listaConstruccion = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaConstruccion") && mapFicha.get("listaConstruccion")!=null ){
                listaConstruccion = (List)mapFicha.get("listaConstruccion");
            }else{
                listaConstruccion = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaConstruccion"));
            HashMap construccion = (HashMap)listaConstruccion.get(ind);

            request.setAttribute("construccionBean", construccion);
            request.setAttribute("indListaConstruccion", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("construccion");
    }

    /**
     * Método que edita una construccion de la lista de construcciones para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarConstruccion(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo editarConstruccion");

        List listaConstruccion = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaConstruccion") && mapFicha.get("listaConstruccion")!=null ){
                listaConstruccion = (List)mapFicha.get("listaConstruccion");
            }else{
                listaConstruccion = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }else if(tipFicha.equals(Properties.FichaCatastralRural)){
                destino = "fichaRural";
            }

            int ind = Integer.parseInt((String)map.get("indListaConstruccion"));

            HashMap construccion = (HashMap)listaConstruccion.get(ind);
            construccion.put("numPiso", ((String)map.get("numPiso")).trim());
            construccion.put("fecConstruccion", ((String)map.get("fecConstruccion")).trim());
            construccion.put("codMEP", ((String)map.get("codMEP")).trim());
            construccion.put("desMEP", ((String)map.get("desMEP")).trim());
            construccion.put("codECC", ((String)map.get("codECC")).trim());
            construccion.put("desECC", ((String)map.get("desECC")).trim());
            construccion.put("codECS", ((String)map.get("codECS")).trim());
            construccion.put("desECS", ((String)map.get("desECS")).trim());
            construccion.put("muro", ((String)map.get("muro")).trim());
            construccion.put("techo", ((String)map.get("techo")).trim());
            construccion.put("pisos", ((String)map.get("pisos")).trim());
            construccion.put("puerta", ((String)map.get("puerta")).trim());
            construccion.put("revestimiento", ((String)map.get("revestimiento")).trim());
            construccion.put("bano", ((String)map.get("bano")).trim());
            construccion.put("instalaciones", ((String)map.get("instalaciones")).trim());
            construccion.put("areConDeclarada", ((String)map.get("areConDeclarada")).trim());
            construccion.put("areConVerificada", ((String)map.get("areConVerificada")).trim());
            construccion.put("codUCA", ((String)map.get("codUCA")).trim());
            construccion.put("desUCA", ((String)map.get("desUCA")).trim());
            construccion.put("areConstruida", ((String)map.get("areConstruida")).trim());
            
            listaConstruccion.set(ind, construccion);

            mapFicha.put("listaConstruccion", listaConstruccion);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Mantenimiento de Obras Complementarias.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irAgregarObra");

        List listaInstalacion = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            InstalacionService instalacion = new InstalacionService();
            listaInstalacion = instalacion.getListInstalacion();

            session.setAttribute("listaInstalacion", listaInstalacion);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("obra");
    }

    /**
     * Método que agrega una obra complementaria a la lista de obras complementarias para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo agregarObra");

        List listaObra = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaObra") && mapFicha.get("listaObra")!=null ){
                listaObra = (List)mapFicha.get("listaObra");
            }else{
                listaObra = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }

            HashMap obra = new HashMap();
            obra.put("codInstalacion", ((String)map.get("codInstalacion")).trim());
            obra.put("desInstalacion", ((String)map.get("desInstalacion")).trim());
            obra.put("fecConstruccion", ((String)map.get("fecConstruccion")).trim());
            obra.put("codMEP", ((String)map.get("codMEP")).trim());
            obra.put("desMEP", ((String)map.get("desMEP")).trim());
            obra.put("codECC", ((String)map.get("codECC")).trim());
            obra.put("desECC", ((String)map.get("desECC")).trim());
            obra.put("codECS", ((String)map.get("codECS")).trim());
            obra.put("desECS", ((String)map.get("desECS")).trim());
            obra.put("largo", ((String)map.get("largo")).trim());
            obra.put("ancho", ((String)map.get("ancho")).trim());
            obra.put("alto", ((String)map.get("alto")).trim());
            obra.put("total", ((String)map.get("total")).trim());
            obra.put("uniMedida", ((String)map.get("uniMedida")).trim());
            obra.put("codUCA", ((String)map.get("codUCA")).trim());
            obra.put("desUCA", ((String)map.get("desUCA")).trim());

            listaObra.add(obra);

            mapFicha.put("listaObra", listaObra);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que elimina una obra a la lista de obras complementarias.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo eliminarObra");

        List listaObra = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaObra") && mapFicha.get("listaObra")!=null ){
                listaObra = (List)mapFicha.get("listaObra");
            }else{
                listaObra = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }

            int ind = Integer.parseInt((String)map.get("indListaObra"));
            HashMap mapObra = (HashMap)listaObra.get(ind);
            ObrComplementariaService obra = new ObrComplementariaService();
            obra.eliminarObraComplementaria(mapObra);
            listaObra.remove(ind);

            mapFicha.put("listaObra", listaObra);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Mantenimiento de Obras Complementarias.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irEditarObra");

        List listaInstalacion = null;
        List listaObra = null;
        List listaMEP = null;
        List listaECC = null;
        List listaECS = null;
        List listaUCA = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaObra") && mapFicha.get("listaObra")!=null ){
                listaObra = (List)mapFicha.get("listaObra");
            }else{
                listaObra = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaObra"));
            HashMap obra = (HashMap)listaObra.get(ind);

            InstalacionService instalacion = new InstalacionService();
            listaInstalacion = instalacion.getListInstalacion();

            MEPService mep = new MEPService();
            listaMEP = mep.getListMEP();

            ECCService ecc = new ECCService();
            listaECC = ecc.getListECC();

            ECSService ecs = new ECSService();
            listaECS = ecs.getListECS();

            UCAService uca = new UCAService();
            listaUCA = uca.getListUCA();

            session.setAttribute("listaMEP", listaMEP);
            session.setAttribute("listaECC", listaECC);
            session.setAttribute("listaECS", listaECS);
            session.setAttribute("listaUCA", listaUCA);

            request.setAttribute("obraBean", obra);
            request.setAttribute("indListaObra", Integer.toString(ind));
            session.setAttribute("listaInstalacion", listaInstalacion);
            session.setAttribute("listaMEP", listaMEP);
            session.setAttribute("listaECC", listaECC);
            session.setAttribute("listaECS", listaECS);
            session.setAttribute("listaUCA", listaUCA);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("obra");
    }

    /**
     * Método que edita una obra de la lista de obras complementarias para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarObra(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo editarObra");

        List listaObra = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaObra") && mapFicha.get("listaObra")!=null ){
                listaObra = (List)mapFicha.get("listaObra");
            }else{
                listaObra = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralUrbanaBienesComunes)){
                destino = "fichaBienComun";
            }

            int ind = Integer.parseInt((String)map.get("indListaObra"));

            HashMap obra = (HashMap)listaObra.get(ind);
            obra.put("codInstalacion", ((String)map.get("codInstalacion")).trim());
            obra.put("desInstalacion", ((String)map.get("desInstalacion")).trim());
            obra.put("fecConstruccion", ((String)map.get("fecConstruccion")).trim());
            obra.put("codMEP", ((String)map.get("codMEP")).trim());
            obra.put("desMEP", ((String)map.get("desMEP")).trim());
            obra.put("codECC", ((String)map.get("codECC")).trim());
            obra.put("desECC", ((String)map.get("desECC")).trim());
            obra.put("codECS", ((String)map.get("codECS")).trim());
            obra.put("desECS", ((String)map.get("desECS")).trim());
            obra.put("largo", ((String)map.get("largo")).trim());
            obra.put("ancho", ((String)map.get("ancho")).trim());
            obra.put("alto", ((String)map.get("alto")).trim());
            obra.put("total", ((String)map.get("total")).trim());
            obra.put("uniMedida", ((String)map.get("uniMedida")).trim());
            obra.put("codUCA", ((String)map.get("codUCA")).trim());
            obra.put("desUCA", ((String)map.get("desUCA")).trim());
            
            listaObra.set(ind, obra);

            mapFicha.put("listaObra", listaObra);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Mantenimiento de Documentos.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarDocumento(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irAgregarDocumento");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("documento");
    }

    /**
     * Método que agrega un documento a la lista de documentos para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarDocumento(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo agregarDocumento");

        List listaDocumento = null;
        String destino = "";

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaDocumento") && mapFicha.get("listaDocumento")!=null ){
                listaDocumento = (List)mapFicha.get("listaDocumento");
            }else{
                listaDocumento = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralRural)){
                destino = "fichaRural";
            }

            HashMap documento = new HashMap();
            documento.put("codTipDocumento", ((String)map.get("codTipDocumento")).trim());
            documento.put("desTipDocumento", ((String)map.get("desTipDocumento")).trim());
            documento.put("numDocumento", ((String)map.get("numDocumento")).trim());
            documento.put("fecha", ((String)map.get("fecha")).trim());
            documento.put("area", ((String)map.get("area")).trim());
            documento.put("nomDocumento", ((String)map.get("nomDocumento")).trim());
            documento.put("codForPresentacion", ((String)map.get("codForPresentacion")).trim());
            documento.put("desForPresentacion", ((String)map.get("desForPresentacion")).trim());

            listaDocumento.add(documento);

            mapFicha.put("listaDocumento", listaDocumento);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que elimina un documento a la lista de documentos.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarDocumento(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo eliminarDocumento");

        List listaDocumento = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaDocumento") && mapFicha.get("listaDocumento")!=null ){
                listaDocumento = (List)mapFicha.get("listaDocumento");
            }else{
                listaDocumento = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaDocumento"));
            HashMap mapDocumento = (HashMap)listaDocumento.get(ind);
            DocumentoService documento = new DocumentoService();
            documento.eliminarDocumento(mapDocumento);
            listaDocumento.remove(ind);

            mapFicha.put("listaDocumento", listaDocumento);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaIndividual");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Documentos.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarDocumento(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irEditarDocumento");

        List listaDocumento = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaDocumento") && mapFicha.get("listaDocumento")!=null ){
                listaDocumento = (List)mapFicha.get("listaDocumento");
            }else{
                listaDocumento = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaDocumento"));
            HashMap documento = (HashMap)listaDocumento.get(ind);

            request.setAttribute("documentoBean", documento);
            request.setAttribute("indListaDocumento", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("documento");
    }

    /**
     * Método que edita un documento de la lista de documentos para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarDocumento(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo editarDocumento");

        List listaDocumento = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaDocumento") && mapFicha.get("listaDocumento")!=null ){
                listaDocumento = (List)mapFicha.get("listaDocumento");
            }else{
                listaDocumento = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralRural)){
                destino = "fichaRural";
            }

            int ind = Integer.parseInt((String)map.get("indListaDocumento"));
            
            HashMap documento = (HashMap)listaDocumento.get(ind);
            documento.put("codTipDocumento", ((String)map.get("codTipDocumento")).trim());
            documento.put("desTipDocumento", ((String)map.get("desTipDocumento")).trim());
            documento.put("numDocumento", ((String)map.get("numDocumento")).trim());
            documento.put("fecha", ((String)map.get("fecha")).trim());
            documento.put("area", ((String)map.get("area")).trim());
            documento.put("nomDocumento", ((String)map.get("nomDocumento")).trim());
            documento.put("codForPresentacion", ((String)map.get("codForPresentacion")).trim());
            documento.put("desForPresentacion", ((String)map.get("desForPresentacion")).trim());
            
            listaDocumento.set(ind, documento);

            mapFicha.put("listaDocumento", listaDocumento);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Mantenimiento de Litigantes.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irAgregarLitigante(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irAgregarLitigante");

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("litigante");
    }

    /**
     * Método que agrega un documento a la lista de litigantes para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward agregarLitigante(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo agregarLitigante");

        List listaLitigante = null;
        String destino = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaLitigante") && mapFicha.get("listaLitigante")!=null ){
                listaLitigante = (List)mapFicha.get("listaLitigante");
            }else{
                listaLitigante = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralRural)){
                destino = "fichaRural";
            }else if(tipFicha.equals(Properties.FichaCatastralBienesCulturales_MonumentoColonial)){
                destino = "fichaCulturalColonial";
            }

            HashMap litigante = new HashMap();
            litigante.put("codTipDocIdentidad", ((String)map.get("codTipDocIdentidad")).trim());
            litigante.put("desTipDocIdentidad", ((String)map.get("desTipDocIdentidad")).trim());
            litigante.put("numDocumento", ((String)map.get("numDocumento")).trim());
            litigante.put("nomLitigante", ((String)map.get("nomLitigante")).trim());
            litigante.put("apePatLitigante", ((String)map.get("apePatLitigante")).trim());
            litigante.put("apeMatLitigante", ((String)map.get("apeMatLitigante")).trim());
            litigante.put("codContribuyente", ((String)map.get("codContribuyente")).trim());

            listaLitigante.add(litigante);

            mapFicha.put("listaLitigante", listaLitigante);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que elimina un litigante a la lista de litigantes.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarLitigante(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo eliminarLitigante");

        List listaLitigante = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaLitigante") && mapFicha.get("listaLitigante")!=null ){
                listaLitigante = (List)mapFicha.get("listaLitigante");
            }else{
                listaLitigante = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaLitigante"));
            HashMap mapLitigante = (HashMap)listaLitigante.get(ind);
            LitiganteService litigante = new LitiganteService();
            litigante.eliminarLitigante(mapLitigante);
            listaLitigante.remove(ind);

            mapFicha.put("listaLitigante", listaLitigante);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaIndividual");
    }

    /**
     * Método que deriva a la página de Mantenimiento de Litigantes.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarLitigante(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irEditarLitigante");

        List listaLitigante = null;

        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            if(mapFicha.containsKey("listaLitigante") && mapFicha.get("listaLitigante")!=null ){
                listaLitigante = (List)mapFicha.get("listaLitigante");
            }else{
                listaLitigante = new ArrayList();
            }

            int ind = Integer.parseInt((String)map.get("indListaLitigante"));
            HashMap litigante = (HashMap)listaLitigante.get(ind);

            request.setAttribute("litiganteBean", litigante);
            request.setAttribute("indListaLitigante", Integer.toString(ind));

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward("litigante");
    }

    /**
     * Método que edita un litigante de la lista de litigantes para mostrar en la ficha.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarLitigante(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo editarLitigante");

        List listaLitigante = null;
        String destino = "";
        
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            if(mapFicha.containsKey("listaLitigante") && mapFicha.get("listaLitigante")!=null ){
                listaLitigante = (List)mapFicha.get("listaLitigante");
            }else{
                listaLitigante = new ArrayList();
            }

            String tipFicha = session.getAttribute("codTipFicha")!=null?(String)session.getAttribute("codTipFicha"):"";
            if(tipFicha.equals(Properties.FichaCatastralUrbanaIndividual)){
                destino = "fichaIndividual";
            }else if(tipFicha.equals(Properties.FichaCatastralRural)){
                destino = "fichaRural";
            }else if(tipFicha.equals(Properties.FichaCatastralBienesCulturales_MonumentoColonial)){
                destino = "fichaCulturalColonial";
            }

            int ind = Integer.parseInt((String)map.get("indListaLitigante"));

            HashMap litigante = (HashMap)listaLitigante.get(ind);
            litigante.put("codTipDocIdentidad", ((String)map.get("codTipDocIdentidad")).trim());
            litigante.put("desTipDocIdentidad", ((String)map.get("desTipDocIdentidad")).trim());
            litigante.put("numDocumento", ((String)map.get("numDocumento")).trim());
            litigante.put("nomLitigante", ((String)map.get("nomLitigante")).trim());
            litigante.put("apePatLitigante", ((String)map.get("apePatLitigante")).trim());
            litigante.put("apeMatLitigante", ((String)map.get("apeMatLitigante")).trim());
            litigante.put("codContribuyente", ((String)map.get("codContribuyente")).trim());

            
            listaLitigante.set(ind, litigante);

            mapFicha.put("listaLitigante", listaLitigante);
            session.setAttribute("mapFicha", mapFicha);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Ficha Individual.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irFichaIndividual(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irFichaIndividual");

        try{

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaIndividual");
    }

    /**
     * Método que deriva a la página de Ficha de Bienes Comunes.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irFichaBienComun(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irFichaBienComun");

        try{

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaBienComun");
    }

    /**
     * Método que deriva a la página de Ficha Rural.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irFichaRural(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irFichaRural");

        try{

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaRural");
    }

    /**
     * Método que deriva a la página de Ficha Bien Cultural.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irFichaCulturalColonial(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irFichaCulturalColonial");

        try{

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaCulturalColonial");
    }

    /**
     * Método que carga la lista de provincias de un departamento dado.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarProvincia(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo cargarProvincia");

        List listaProvincia = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            HashMap mapUbigeo = new HashMap();
            mapUbigeo.put("codDepartamento", (String)map.get("codDepartamento"));
            UbigeoService ubigeo = new UbigeoService();
            listaProvincia = ubigeo.getListProvincia(mapUbigeo);

            mapListas.put("listaProvincia", listaProvincia);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaIndividual");
    }

    /**
     * Método que carga la lista de distritos de una provincia dada.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward cargarDistrito(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo cargarDistrito");

        List listaDistrito = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapFicha = session.getAttribute("mapFicha")!=null?(HashMap)session.getAttribute("mapFicha"):new HashMap();
            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();

            HashMap valores = setearValores(mapFicha,map);
            mapFicha.putAll(valores);
            session.setAttribute("mapFicha", mapFicha);

            HashMap mapUbigeo = new HashMap();
            mapUbigeo.put("codDepartamento", (String)map.get("codDepartamento"));
            mapUbigeo.put("codProvincia", (String)map.get("codProvincia"));
            UbigeoService ubigeo = new UbigeoService();
            listaDistrito = ubigeo.getListDistrito(mapUbigeo);

            mapListas.put("listaDistrito", listaDistrito);

            session.setAttribute("mapListas", mapListas);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("fichaIndividual");
    }

    /**
     * Método que deriva a la página de Bienvenida.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irBienvenida(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irBienvenida");

        try{
            HttpSession session = request.getSession();
            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("bienvenida");
    }

    /**
     * Método que deriva a la página de Búsqueda.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irBusqueda(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en FichaIndividualAction metodo irBusqueda");

        String destino = "";
        List lista = null;
        try{
            HttpSession session = request.getSession(false);
            UsuarioBean usuario = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):null;

            if(session!=null){
                if(usuario!=null){
                    FichaService fichaService = new FichaService();
                    lista = fichaService.buscarFichas(new HashMap());
                    if(lista!=null && !lista.isEmpty()){
                        for(int i=0;i<lista.size();i++){
                            String num = Properties.concatLeftCharacter("0", 7, (String)((HashMap)lista.get(i)).get("numFicha"));
                            ((HashMap)lista.get(i)).put("numFicha", num);
                        }
                    }
                    session.setAttribute("listaFicha", lista);
                    destino = "busqueda";
                }else{
                    destino = "noSession";
                }
            }else{
                destino = "noSession";
            }

            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }
}
