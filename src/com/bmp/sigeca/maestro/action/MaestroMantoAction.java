/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.action;

import com.bmp.sigeca.maestro.bean.CampoBean;
import com.bmp.sigeca.maestro.bean.DataBean;
import com.bmp.sigeca.maestro.bean.TablaBean;
import com.bmp.sigeca.maestro.service.CampoService;
import com.bmp.sigeca.maestro.service.TablaService;
import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.registro.service.FichaService;
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
public class MaestroMantoAction extends DispatchAction {

    protected Logger logg;

    /**
     * Método que registra una Ficha Catastral.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irRegistrar(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en MaestroMantoAction metodo irRegistrar");

        List listaTablas = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();

            TablaService tablaservice = new TablaService();
            listaTablas = tablaservice.getListaTablas();

            mapListas.put("listaTablas", listaTablas);

            session.setAttribute("mapListas", mapListas);
            session.removeAttribute("mantoMaestroBean");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("maestro");
    }


    /**
     * Método que genera la lista de datos de la tabla maestra seleccionada.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward generaLista(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaCampos = null;
        List listaData = null;
        String tabla = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();
            HashMap mantoMaestroBean = session.getAttribute("mantoMaestroBean")!=null?(HashMap)session.getAttribute("mantoMaestroBean"):new HashMap();

            mantoMaestroBean.put("codTabla",(String)map.get("codTabla"));
            mantoMaestroBean.put("descTabla",(String)map.get("descTabla"));
            mantoMaestroBean.put("data", "");

            List listaTablas = mapListas.get("listaTablas")!=null?(List)mapListas.get("listaTablas"):new ArrayList();

            for(int i=0;i<listaTablas.size();i++){
                if(((TablaBean)listaTablas.get(i)).getCodTabla().equals((String)mantoMaestroBean.get("codTabla"))){
                    tabla = ((TablaBean)listaTablas.get(i)).getNomFisico();
                    break;
                }
            }

            HashMap mapaTabla = new HashMap();
            mapaTabla.put("codTabla", mantoMaestroBean.get("codTabla"));

            CampoService camposervice = new CampoService();
            listaCampos = camposervice.getListaCampos(mapaTabla);

            StringBuffer sql = new StringBuffer();
            sql.append("SELECT ").append(((CampoBean)listaCampos.get(0)).getNomFisico()).append(", ");
            sql.append(((CampoBean)listaCampos.get(1)).getNomFisico());
            sql.append(" FROM ").append(tabla);

            listaData = camposervice.getDataMaestro(sql);
            
            mapListas.put("listaCampos", listaCampos);
            mapListas.put("listaData", listaData);

            session.setAttribute("mapListas", mapListas);
            session.setAttribute("mantoMaestroBean", mantoMaestroBean);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("maestro");
    }

    /**
     * Método que registra un nuevo dato a la tabla maestra seleccionada.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward registrarMaestro(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaTablas = null;
        List listaCampos = null;
        List listaData = null;
        String tabla = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();
            HashMap mantoMaestroBean = session.getAttribute("mantoMaestroBean")!=null?(HashMap)session.getAttribute("mantoMaestroBean"):new HashMap();

            mantoMaestroBean.put("codTabla",(String)map.get("codTabla"));
            mantoMaestroBean.put("descTabla",(String)map.get("descTabla"));
            mantoMaestroBean.put("data", "");

            listaTablas = mapListas.get("listaTablas")!=null?(List)mapListas.get("listaTablas"):new ArrayList();

            for(int i=0;i<listaTablas.size();i++){
                if(((TablaBean)listaTablas.get(i)).getCodTabla().equals((String)mantoMaestroBean.get("codTabla"))){
                    tabla = ((TablaBean)listaTablas.get(i)).getNomFisico();
                    break;
                }
            }

            listaCampos = mapListas.get("listaCampos")!=null?(List)mapListas.get("listaCampos"):new ArrayList();

            HashMap mapTabla = new HashMap();
            mapTabla.put("pk", ((CampoBean)listaCampos.get(0)).getNomFisico());
            mapTabla.put("tabla", tabla);

            TablaService tablaservice = new TablaService();
            int pk = tablaservice.getMaxPrimaryKey(mapTabla);

            String stringPK = Integer.toString(pk+1);
            if(stringPK.length()==1) stringPK = "0".concat(stringPK);

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ").append(tabla).append("(").append(((CampoBean)listaCampos.get(0)).getNomFisico()).append(", ");
            sql.append(((CampoBean)listaCampos.get(1)).getNomFisico()).append(") VALUES('").append(stringPK).append("','").append((String)map.get("data")).append("');");

            System.out.println(sql.toString());

            tablaservice.registrarMaestro(sql);

            sql = new StringBuffer();
            sql.append("SELECT ").append(((CampoBean)listaCampos.get(0)).getNomFisico()).append(", ");
            sql.append(((CampoBean)listaCampos.get(1)).getNomFisico());
            sql.append(" FROM ").append(tabla);

            System.out.println(sql.toString());
            
            CampoService camposervice = new CampoService();
            listaData = camposervice.getDataMaestro(sql);

            mapListas.put("listaData", listaData);

            session.setAttribute("mapListas", mapListas);
            session.setAttribute("mantoMaestroBean", mantoMaestroBean);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("maestro");
    }

    /**
     * Método que elimina un dato de la tabla maestra seleccionada.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward eliminarMaestro(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaCampos = null;
        List listaData = null;
        String tabla = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();
            HashMap mantoMaestroBean = session.getAttribute("mantoMaestroBean")!=null?(HashMap)session.getAttribute("mantoMaestroBean"):new HashMap();

            mantoMaestroBean.put("codTabla",(String)map.get("codTabla"));
            mantoMaestroBean.put("descTabla",(String)map.get("descTabla"));
            mantoMaestroBean.put("data", "");

            List listaTablas = mapListas.get("listaTablas")!=null?(List)mapListas.get("listaTablas"):new ArrayList();

            for(int i=0;i<listaTablas.size();i++){
                if(((TablaBean)listaTablas.get(i)).getCodTabla().equals((String)mantoMaestroBean.get("codTabla"))){
                    tabla = ((TablaBean)listaTablas.get(i)).getNomFisico();
                    break;
                }
            }

            listaCampos = mapListas.get("listaCampos")!=null?(List)mapListas.get("listaCampos"):new ArrayList();
            listaData = mapListas.get("listaData")!=null?(List)mapListas.get("listaData"):new ArrayList();

            DataBean data = (DataBean)listaData.get(Integer.parseInt((String)map.get("indice")));

            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM ").append(tabla).append(" WHERE ").append(((CampoBean)listaCampos.get(0)).getNomFisico()).append(" = '").append(data.getCodigo()).append("'");

            TablaService tablaservice = new TablaService();
            tablaservice.eliminarMaestro(sql);

            sql = new StringBuffer();
            sql.append("SELECT ").append(((CampoBean)listaCampos.get(0)).getNomFisico()).append(", ");
            sql.append(((CampoBean)listaCampos.get(1)).getNomFisico());
            sql.append(" FROM ").append(tabla);

            CampoService camposervice = new CampoService();
            listaData = camposervice.getDataMaestro(sql);

            mapListas.put("listaData", listaData);

            session.setAttribute("mapListas", mapListas);
            session.setAttribute("mantoMaestroBean", mantoMaestroBean);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("maestro");
    }

    /**
     * Método que elimina un dato de la tabla maestra seleccionada.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irEditarMaestro(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        String indice = "";
        List listaData = null;
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mantoMaestroBean = session.getAttribute("mantoMaestroBean")!=null?(HashMap)session.getAttribute("mantoMaestroBean"):new HashMap();

            indice = (String)map.get("indice");

            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();
            listaData = mapListas.get("listaData")!=null?(List)mapListas.get("listaData"):new ArrayList();
            DataBean data = (DataBean)listaData.get(Integer.parseInt(indice));

            mantoMaestroBean.put("codTabla",(String)map.get("codTabla"));
            mantoMaestroBean.put("descTabla",(String)map.get("descTabla"));
            mantoMaestroBean.put("data", data.getDescripcion());

            request.setAttribute("indice", indice);
            session.setAttribute("mantoMaestroBean", mantoMaestroBean);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("maestro");
    }

    /**
     * Método que edita un dato de la tabla maestra seleccionada.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward editarMaestro(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        List listaCampos = null;
        List listaData = null;
        String tabla = "";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            HashMap mapListas = session.getAttribute("mapListas")!=null?(HashMap)session.getAttribute("mapListas"):new HashMap();
            HashMap mantoMaestroBean = session.getAttribute("mantoMaestroBean")!=null?(HashMap)session.getAttribute("mantoMaestroBean"):new HashMap();

            mantoMaestroBean.put("codTabla",(String)map.get("codTabla"));
            mantoMaestroBean.put("descTabla",(String)map.get("descTabla"));
            mantoMaestroBean.put("data", "");

            List listaTablas = mapListas.get("listaTablas")!=null?(List)mapListas.get("listaTablas"):new ArrayList();

            for(int i=0;i<listaTablas.size();i++){
                if(((TablaBean)listaTablas.get(i)).getCodTabla().equals((String)mantoMaestroBean.get("codTabla"))){
                    tabla = ((TablaBean)listaTablas.get(i)).getNomFisico();
                    break;
                }
            }

            listaCampos = mapListas.get("listaCampos")!=null?(List)mapListas.get("listaCampos"):new ArrayList();
            listaData = mapListas.get("listaData")!=null?(List)mapListas.get("listaData"):new ArrayList();

            DataBean data = (DataBean)listaData.get(Integer.parseInt((String)map.get("indice")));

            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE ").append(tabla).append(" SET ").append(((CampoBean)listaCampos.get(1)).getNomFisico()).append("='").append((String)map.get("data")).append("' ");
            sql.append("WHERE ").append(((CampoBean)listaCampos.get(0)).getNomFisico()).append(" = '").append(data.getCodigo()).append("'");

            TablaService tablaservice = new TablaService();
            tablaservice.actualizarMaestro(sql);

            sql = new StringBuffer();
            sql.append("SELECT ").append(((CampoBean)listaCampos.get(0)).getNomFisico()).append(", ");
            sql.append(((CampoBean)listaCampos.get(1)).getNomFisico());
            sql.append(" FROM ").append(tabla);

            CampoService camposervice = new CampoService();
            listaData = camposervice.getDataMaestro(sql);

            mapListas.put("listaData", listaData);

            session.setAttribute("mapListas", mapListas);
            session.setAttribute("mantoMaestroBean", mantoMaestroBean);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward("maestro");
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
        logg.debug(" Log en InicioAction metodo irBusqueda");

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

                    if(usuario.getCodPefil().equals(Properties.Perfil_Administrador)){
                        destino = "menuAdministrador";
                    }else if(usuario.getCodPefil().equals(Properties.Perfil_Supervisor)){
                        destino = "menuSupervisor";
                    }
                }else{
                    destino = "noSession";
                }
            }else{
                destino = "noSession";
            }

            session.removeAttribute("mapFicha");
            session.removeAttribute("mapListas");
            session.removeAttribute("mantoMaestroBean");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }
}
