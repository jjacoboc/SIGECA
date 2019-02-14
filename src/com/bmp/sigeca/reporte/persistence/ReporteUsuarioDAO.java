/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.reporte.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.reporte.bean.FichaReporteUsuarioBean;
import com.bmp.sigeca.reporte.bean.ReporteUsuarioBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Ricardo Avila
 */
public class ReporteUsuarioDAO extends GenericDAO{


     /** Crea una nueva instancia de DAOUsuario */
    public ReporteUsuarioDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public  List<FichaReporteUsuarioBean>  getLstReportePorUbicacionPredio(HashMap map) throws DAOException{

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT pp.APE_PATERNO, pp.APE_MATERNO, pp.NOMBRES,    ");
            sql.append(" v.TIP_VIA, v.NOM_VIA, p.NRO_MUNI,  ");
            sql.append(" h.TIP_HAB_URBA , h.NOM_HAB_URBA , h.GRUPO_URBA , m.COD_MZNA , l.COD_LOTE , l.SUB_LOTE_DIST , ");
            sql.append(" u.cuc ,   ");
            sql.append(" ub.id_ubi_geo, s.cod_sector, m.cod_mzna , l.cod_lote, e.cod_edificacion, u.cod_entrada, u.cod_piso , u.cod_unidad, f.dc, ");
            sql.append(" td.desc_codigo, ");
            sql.append(" us.desc_uso, ");
            sql.append(" fi.AREA_TITULO  ");

            sql.append("FROM ficha f,  uni_cat u, persona pp, sectores s, hab_urba h, vias v, manzanas m , lotes l , vias_hab_urba vh , ");
            sql.append(" puertas p, ficha_individual fi,  titulares t , ubigeo ub , edificaciones e , usos us, tablas_codigos td ");

            sql.append("WHERE f.id_ficha = t.id_ficha and t.id_persona = pp.id_persona and ");
            sql.append(" fi.id_ficha = f.id_ficha and ");
            sql.append("  f.id_uni_cat = u.id_uni_cat and ");
            sql.append(" f.id_lote = l.id_lotes and l.id_mzna = m.id_mzna and  ");
            sql.append(" l.id_lotes = p.id_lote and p.id_via = v.id_via and v.id_via = vh.id_via and  ");
            sql.append(" vh.id_hab_urba = h.id_hab_urba and m.id_sector = s.id_sector and  ");
            sql.append(" s.id_ubi_geo = ub.id_ubi_geo and e.id_lote = l.id_lotes and ");
            sql.append(" fi.cod_uso = us.cod_usos and fi.clasificacion = td.codigo  ");


            if(map.containsKey("tipoUbicacion")){

                if(map.get("tipoUbicacion").equals("0"))
                {
                    if(map.containsKey("urbanizacion")){
                     sql.append("AND  h.nom_hab_urba = '").append((String)map.get("urbanizacion")).append("' ");
                    }

                   if(map.containsKey("manzana")){
                      sql.append("AND  m.cod_mzna = '").append((String)map.get("manzana")).append("' ");
                    }

                    if(map.containsKey("lote")){
                       sql.append("AND l.cod_lote ='").append((String)map.get("loteCalle")).append("' ");
                    }

                }else{

                    if(map.containsKey("tipoVia")){
                     sql.append("AND v.tip_via ='").append((String)map.get("urbaVia")).append("' ");
                    }

                   if(map.containsKey("nombreVia")){
                      sql.append("AND v.nom_via ='").append((String)map.get("manzVia")).append("' ");
                    }

                    if(map.containsKey("numCalle")){
                       sql.append("AND p.nro_muni = '").append((String)map.get("numCalle")).append("' ");
                    }
                }

            }

            if(map.containsKey("clasificacionPredio")){
                if(map.get("clasificacionPredio").equals("04"))
                {
                   if(map.containsKey("otrosPredio")){
                        sql.append("AND fi.clasificacion ='").append((String)map.get("otrosPredio")).append("' ");
                   }
                }else{
                    sql.append("AND fi.clasificacion ='").append((String)map.get("clasificacionPredio")).append("' ");
                }
                  
             }

             if(map.containsKey("codUso")){
                  sql.append("AND fi.cod_uso ='").append((String)map.get("codUso")).append("' ");
             }


            if(map.containsKey("activo")){
                sql.append("AND f.activo=").append((String)map.get("activo")).append(" ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();


            ReporteUsuarioBean r = new ReporteUsuarioBean();
            r.setDepartamento("" );
            r.setProvincia("" );
            r.setDistrito("" );


            while(rst.next()){

                FichaReporteUsuarioBean f = new FichaReporteUsuarioBean();

                f.setTitularCatastral(rst.getString(1) + " " + rst.getString(2) + " " + rst.getString(3));
                
                  //validando los valores para ubicacion del predio
                StringBuffer ubicacion = new StringBuffer("");

                String tipoVia = rst.getString(4);
                String nombreVia = rst.getString(5);
                String numMuni = rst.getString(6);

                String tipoUrba = rst.getString(7);
                String nombreUrba = rst.getString(8);
                String grupoUrba = rst.getString(9);
                String codManz = rst.getString(10);
                String codLote = rst.getString(11);
                String subLote = rst.getString(12);

                if(numMuni.equals(""))
                {
                    if(nombreVia.equals("") && tipoVia.equals(""))
                    {
                        ubicacion.append(tipoUrba).append(" ").append(nombreUrba).append(" ").append(grupoUrba).append(" ").append(codManz).append(" ").append(codLote).append(" ").append(subLote);
                    }else{
                         ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(codManz).append(" ").append(codLote);
                    }
                }else{
                    ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(numMuni);
                }
                
                f.setCuc(rst.getString(13));
                f.setCrc(rst.getString(14) + rst.getString(15)  + rst.getString(16) + rst.getString(17) + rst.getString(18)  + rst.getString(19) +rst.getString(20) + rst.getString(21)  + rst.getString(22));
                f.setClasificacionPredio(rst.getString(23));
                f.setUsoPredioCatastral(rst.getString(24));
                f.setAreaLote(rst.getDouble(25));
               
                
                f.setReporteBean(r);
                lstFichaReporte.add(f);

            }

            pstm.close();
            pstm=null;
            rst.close();
            rst=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            throw new DAOException(ex);
        }
        return lstFichaReporte;
    }



      public  List<FichaReporteUsuarioBean>  getLstReportePorCodigo(HashMap map) throws DAOException{

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT u.cuc ,   ");
            sql.append(" ub.id_ubi_geo, s.cod_sector, m.cod_mzna , l.cod_lote, e.cod_edificacion, u.cod_entrada, u.cod_piso , u.cod_unidad, f.dc, ");
            sql.append(" fi.AREA_TITULO,   ");
            sql.append(" pp.APE_PATERNO, pp.APE_MATERNO, pp.NOMBRES,  ");
            sql.append(" pp.nro_doc, ");
            sql.append(" v.TIP_VIA, v.NOM_VIA, p.NRO_MUNI, ");
            sql.append(" h.TIP_HAB_URBA , h.NOM_HAB_URBA , h.GRUPO_URBA , m.COD_MZNA , l.COD_LOTE , l.SUB_LOTE_DIST  ");

            sql.append("FROM ficha f,  uni_cat u, persona pp, sectores s, hab_urba h, vias v, manzanas m , lotes l , vias_hab_urba vh , ");
            sql.append(" puertas p, ficha_individual fi,  titulares t , ubigeo ub , edificaciones e");

            sql.append("WHERE f.id_ficha = t.id_ficha and t.id_persona = pp.id_persona and ");
            sql.append(" fi.id_ficha = f.id_ficha and ");
            sql.append("  f.id_uni_cat = u.id_uni_cat and ");
            sql.append(" f.id_lote = l.id_lotes and l.id_mzna = m.id_mzna and  ");
            sql.append(" l.id_lotes = p.id_lote and p.id_via = v.id_via and v.id_via = vh.id_via and  ");
            sql.append(" vh.id_hab_urba = h.id_hab_urba and m.id_sector = s.id_sector and  ");
            sql.append(" s.id_ubi_geo = ub.id_ubi_geo and e.id_lote = l.id_lotes  ");


            if(map.containsKey("tipoCodigo")){
                if(map.get("tipoCodigo").equals("crc"))
                {
                    String codigo = (String)map.get("codDocumento");

                   sql.append("AND ub.id_ubi_geo ='").append(codigo.substring(0, 6)).append("' ");
                   sql.append("AND s.cod_sector ='").append(codigo.substring(6, 8)).append("' ");
                   sql.append("AND  m.cod_mzna ='").append(codigo.substring(8, 11)).append("' ");
                   sql.append("AND  l.cod_lote ='").append(codigo.substring(11, 14)).append("' ");
                   sql.append("AND   e.cod_edificacion ='").append(codigo.substring(14, 16)).append("' ");
                   sql.append("AND u.cod_entrada ='").append(codigo.substring(16, 18)).append("' ");
                   sql.append("AND u.cod_piso  ='").append(codigo.substring(18, 20)).append("' ");
                   sql.append("AND u.cod_unidad  ='").append(codigo.substring(20, 23)).append("' ");
                   sql.append("AND f.dc  ='").append(codigo.substring(23, 24)).append("' ");

                }else  if(map.get("tipoCodigo").equals("cuc"))
                {
                     sql.append("AND u.cuc ='").append((String)map.get("codDocumento")).append("' ");
                }else if(map.get("tipoCodigo").equals("cpr"))
                {
                      sql.append("AND u.cod_pred_rentas ='").append((String)map.get("codDocumento")).append("' ");
                }

            }

            if(map.containsKey("activo")){
                sql.append("AND f.activo=").append((String)map.get("activo")).append(" ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();


            ReporteUsuarioBean r = new ReporteUsuarioBean();
            r.setDepartamento("" );
            r.setProvincia("" );
            r.setDistrito("" );


            while(rst.next()){

                FichaReporteUsuarioBean f = new FichaReporteUsuarioBean();
                
                f.setCuc(rst.getString(1));
                f.setCrc(rst.getString(2) + rst.getString(3)  + rst.getString(4) + rst.getString(5) + rst.getString(6)  + rst.getString(7) +rst.getString(8) + rst.getString(9)  + rst.getString(10));
                f.setAreaLote(rst.getDouble(11));
                f.setTitularCatastral(rst.getString(12) + " " + rst.getString(13) + " " + rst.getString(14));
                f.setNroDocumento(rst.getString(15));

                 //validando los valores para ubicacion del predio
                StringBuffer ubicacion = new StringBuffer("");

                String tipoVia = rst.getString(16);
                String nombreVia = rst.getString(17);
                String numMuni = rst.getString(18);

                String tipoUrba = rst.getString(19);
                String nombreUrba = rst.getString(20);
                String grupoUrba = rst.getString(21);
                String codManz = rst.getString(22);
                String codLote = rst.getString(23);
                String subLote = rst.getString(24);

                if(numMuni.equals(""))
                {
                    if(nombreVia.equals("") && tipoVia.equals(""))
                    {
                        ubicacion.append(tipoUrba).append(" ").append(nombreUrba).append(" ").append(grupoUrba).append(" ").append(codManz).append(" ").append(codLote).append(" ").append(subLote);
                    }else{
                         ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(codManz).append(" ").append(codLote);
                    }
                }else{
                    ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(numMuni);
                }

                f.setReporteBean(r);
                lstFichaReporte.add(f);

            }

            pstm.close();
            pstm=null;
            rst.close();
            rst=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            throw new DAOException(ex);
        }
        return lstFichaReporte;
    }




      public  List<FichaReporteUsuarioBean>  getLstReportePorDocumento(HashMap map) throws DAOException{

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT pp.APE_PATERNO, pp.APE_MATERNO, pp.NOMBRES,   ");
            sql.append(" v.TIP_VIA, v.NOM_VIA, p.NRO_MUNI, ");
            sql.append(" h.TIP_HAB_URBA , h.NOM_HAB_URBA , h.GRUPO_URBA , m.COD_MZNA , l.COD_LOTE , l.SUB_LOTE_DIST , ");
            sql.append(" u.cuc ,  ");
            sql.append(" ub.id_ubi_geo, s.cod_sector, m.cod_mzna , l.cod_lote, e.cod_edificacion, u.cod_entrada, u.cod_piso , u.cod_unidad, f.dc, ");
            sql.append(" fi.AREA_TITULO  ");

            sql.append("FROM ficha f,  uni_cat u, persona pp, sectores s, hab_urba h, vias v, manzanas m , lotes l , vias_hab_urba vh , ");
            sql.append(" puertas p, ficha_individual fi,  titulares t , ubigeo ub , edificaciones e");

            sql.append("WHERE f.id_ficha = t.id_ficha and t.id_persona = pp.id_persona and ");
            sql.append(" fi.id_ficha = f.id_ficha and ");
            sql.append("  f.id_uni_cat = u.id_uni_cat and ");
            sql.append(" f.id_lote = l.id_lotes and l.id_mzna = m.id_mzna and  ");
            sql.append(" l.id_lotes = p.id_lote and p.id_via = v.id_via and v.id_via = vh.id_via and  ");
            sql.append(" vh.id_hab_urba = h.id_hab_urba and m.id_sector = s.id_sector and  ");
            sql.append(" s.id_ubi_geo = ub.id_ubi_geo and e.id_lote = l.id_lotes  ");


            if(map.containsKey("tipoDocumento")){
                sql.append("AND pp.tip_persona ='").append((String)map.get("tipoDocumento")).append("' ");
            }
             if(map.containsKey("numDocumento")){
                sql.append("AND pp.nro_doc ='").append((String)map.get("numDocumento")).append("' ");
            }
            if(map.containsKey("activo")){
                sql.append("AND f.activo=").append((String)map.get("activo")).append(" ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();


            ReporteUsuarioBean r = new ReporteUsuarioBean();
            r.setDepartamento("" );
            r.setProvincia("" );
            r.setDistrito("" );


            while(rst.next()){

                FichaReporteUsuarioBean f = new FichaReporteUsuarioBean();
                f.setTitularCatastral(rst.getString(1) + " " + rst.getString(2) + " " + rst.getString(3));
               
              
                 //validando los valores para ubicacion del predio
                StringBuffer ubicacion = new StringBuffer("");

                String tipoVia = rst.getString(4);
                String nombreVia = rst.getString(5);
                String numMuni = rst.getString(6);

                String tipoUrba = rst.getString(7);
                String nombreUrba = rst.getString(8);
                String grupoUrba = rst.getString(9);
                String codManz = rst.getString(10);
                String codLote = rst.getString(11);
                String subLote = rst.getString(12);

                if(numMuni.equals(""))
                {
                    if(nombreVia.equals("") && tipoVia.equals(""))
                    {
                        ubicacion.append(tipoUrba).append(" ").append(nombreUrba).append(" ").append(grupoUrba).append(" ").append(codManz).append(" ").append(codLote).append(" ").append(subLote);
                    }else{
                         ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(codManz).append(" ").append(codLote);
                    }
                }else{
                    ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(numMuni);
                }
                
                
                f.setCuc(rst.getString(13));
                f.setCrc(rst.getString(14) + rst.getString(15)  + rst.getString(16) + rst.getString(17) + rst.getString(18)  + rst.getString(19) +rst.getString(20) + rst.getString(21)  + rst.getString(22));
                f.setAreaLote(rst.getDouble(23));


                f.setReporteBean(r);
                lstFichaReporte.add(f);

            }

            pstm.close();
            pstm=null;
            rst.close();
            rst=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            throw new DAOException(ex);
        }
        return lstFichaReporte;
    }


     public  List<FichaReporteUsuarioBean>  getLstReportePorTitularesArea(HashMap map) throws DAOException{

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT pp.APE_PATERNO, pp.APE_MATERNO, pp.NOMBRES, pp.nro_doc ,u.cuc ,  ");
            sql.append(" ub.id_ubi_geo, s.cod_sector, m.cod_mzna , l.cod_lote, e.cod_edificacion, u.cod_entrada, u.cod_piso , u.cod_unidad, f.dc, ");
            sql.append(" v.TIP_VIA, v.NOM_VIA, p.NRO_MUNI,  ");
            sql.append(" h.TIP_HAB_URBA , h.NOM_HAB_URBA , h.GRUPO_URBA , m.COD_MZNA , l.COD_LOTE , l.SUB_LOTE_DIST ,  ");
            sql.append(" fi.AREA_TITULO  ");

            sql.append("FROM ficha f,  uni_cat u, persona pp, sectores s, hab_urba h, vias v, manzanas m , lotes l , vias_hab_urba vh , ");
            sql.append("puertas p, ficha_individual fi,  titulares t , ubigeo ub , edificaciones e ");

            sql.append("WHERE f.id_ficha = t.id_ficha and t.id_persona = pp.id_persona and ");
            sql.append(" fi.id_ficha = f.id_ficha and ");
            sql.append("  f.id_uni_cat = u.id_uni_cat and ");
            sql.append(" f.id_lote = l.id_lotes and l.id_mzna = m.id_mzna and  ");
            sql.append(" l.id_lotes = p.id_lote and p.id_via = v.id_via and v.id_via = vh.id_via and  ");
            sql.append(" vh.id_hab_urba = h.id_hab_urba and m.id_sector = s.id_sector and  ");
            sql.append(" s.id_ubi_geo = ub.id_ubi_geo and e.id_lote = l.id_lotes  ");


            if(map.containsKey("apePaterno")){
                sql.append("AND pp.ape_paterno ='").append((String)map.get("apePaterno")).append("' ");
            }
             if(map.containsKey("apeMaterno")){
                sql.append("AND pp.ape_materno ='").append((String)map.get("apeMaterno")).append("' ");
            }
             if(map.containsKey("nombre")){
                sql.append("AND pp.nombres ='").append((String)map.get("nombre")).append("' ");
            }
             if(map.containsKey("area")){
                sql.append("AND fi.area_titulo ='").append((String)map.get("secareaor")).append("' ");
            }
              if(map.containsKey("activo")){
                sql.append("AND f.activo=").append((String)map.get("activo")).append(" ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();


            ReporteUsuarioBean r = new ReporteUsuarioBean();
            r.setDepartamento("" );
            r.setProvincia("" );
            r.setDistrito("" );


            while(rst.next()){

                FichaReporteUsuarioBean f = new FichaReporteUsuarioBean();
                f.setTitularCatastral(rst.getString(1) + " " + rst.getString(2) + " " + rst.getString(3));
                 f.setNroDocumento(rst.getString(4));
                f.setCuc(rst.getString(5));
               
                f.setCrc(rst.getString(6) + rst.getString(7)  + rst.getString(8) + rst.getString(9) + rst.getString(10)  + rst.getString(11) +rst.getString(12) + rst.getString(13)  + rst.getString(14));

                 //validando los valores para ubicacion del predio
                StringBuffer ubicacion = new StringBuffer("");

                String tipoVia = rst.getString(15);
                String nombreVia = rst.getString(16);
                String numMuni = rst.getString(17);

                String tipoUrba = rst.getString(18);
                String nombreUrba = rst.getString(19);
                String grupoUrba = rst.getString(20);
                String codManz = rst.getString(21);
                String codLote = rst.getString(22);
                String subLote = rst.getString(23);

                if(numMuni.equals(""))
                {
                    if(nombreVia.equals("") && tipoVia.equals(""))
                    {
                        ubicacion.append(tipoUrba).append(" ").append(nombreUrba).append(" ").append(grupoUrba).append(" ").append(codManz).append(" ").append(codLote).append(" ").append(subLote);
                    }else{
                         ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(codManz).append(" ").append(codLote);
                    }
                }else{
                    ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(numMuni);
                }


                f.setAreaLote(rst.getDouble(24));

              
                f.setReporteBean(r);
                lstFichaReporte.add(f);

            }
            
            pstm.close();
            pstm=null;
            rst.close();
            rst=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            throw new DAOException(ex);
        }
        return lstFichaReporte;
    }


      public  List<FichaReporteUsuarioBean>  getLstReportePorUbicacion(HashMap map) throws DAOException{

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT u.cuc, pp.APE_PATERNO, pp.APE_MATERNO, pp.NOMBRES, pp.nro_doc , v.TIP_VIA, v.NOM_VIA, p.NRO_MUNI, ");
            sql.append(" h.TIP_HAB_URBA , h.NOM_HAB_URBA , h.GRUPO_URBA , m.COD_MZNA , l.COD_LOTE , l.SUB_LOTE_DIST , fi.AREA_TITULO , c.AREA_VERIFICADA ");
            sql.append("FROM ficha f, uni_cat u, persona pp, hab_urba h, vias v, manzanas m , lotes l , vias_hab_urba vh , ");
            sql.append("puertas p, ficha_individual fi, construcciones c , titulares t  ");
            sql.append("WHERE f.id_ficha = t.id_ficha AND t.id_persona = pp.id_persona AND fi.id_ficha = f.id_ficha AND ");
            sql.append("f.id_lote = l.id_lotes AND l.id_mzna = m.id_mzna AND  f.id_uni_cat = u.id_uni_cat AND ");
             sql.append("l.id_lotes = p.id_lote AND p.id_via = v.id_via AND v.id_via = vh.id_via AND vh.id_hab_urba = h.id_hab_urba AND c.id_ficha = f.id_ficha ");

            if(map.containsKey("sector")){
                sql.append("AND m.id_sector ='").append((String)map.get("sector")).append("' ");
            }

            if(map.containsKey("tipoUbicacion")){

                if(map.get("tipoUbicacion").equals("0"))
                {
                    if(map.containsKey("urbanizacion")){
                     sql.append("AND  h.nom_hab_urba = '").append((String)map.get("urbanizacion")).append("' ");
                    }

                   if(map.containsKey("manzana")){
                      sql.append("AND  m.cod_mzna = '").append((String)map.get("manzana")).append("' ");
                    }

                    if(map.containsKey("lote")){
                       sql.append("AND l.cod_lote ='").append((String)map.get("loteCalle")).append("' ");
                    }

                }else{

                    if(map.containsKey("tipoVia")){
                     sql.append("AND v.tip_via ='").append((String)map.get("urbaVia")).append("' ");
                    }

                   if(map.containsKey("nombreVia")){
                      sql.append("AND v.nom_via ='").append((String)map.get("manzVia")).append("' ");
                    }

                    if(map.containsKey("numCalle")){
                       sql.append("AND p.nro_muni = '").append((String)map.get("numCalle")).append("' ");
                    }
                }

            }


              if(map.containsKey("activo")){
                sql.append("AND f.activo=").append((String)map.get("activo")).append(" ");
            }


            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();

            ReporteUsuarioBean r = new ReporteUsuarioBean();
            r.setDepartamento("" );
            r.setProvincia("" );
            r.setDistrito("" );


            while(rst.next()){

                FichaReporteUsuarioBean f = new FichaReporteUsuarioBean();
                f.setCuc(rst.getString(1));
                f.setTitularCatastral(rst.getString(2) + " " + rst.getString(3) + " " + rst.getString(4));
                f.setNroDocumento(rst.getString(5));

                //validando los valores para ubicacion del predio
                StringBuffer ubicacion = new StringBuffer("");

                String tipoVia = rst.getString(6);
                String nombreVia = rst.getString(7);
                String numMuni = rst.getString(8);

                String tipoUrba = rst.getString(9);
                String nombreUrba = rst.getString(10);
                String grupoUrba = rst.getString(11);
                String codManz = rst.getString(12);
                String codLote = rst.getString(13);
                String subLote = rst.getString(14);

                if(numMuni.equals(""))
                {
                    if(nombreVia.equals("") && tipoVia.equals(""))
                    {
                        ubicacion.append(tipoUrba).append(" ").append(nombreUrba).append(" ").append(grupoUrba).append(" ").append(codManz).append(" ").append(codLote).append(" ").append(subLote);
                    }else{
                         ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(codManz).append(" ").append(codLote);
                    }
                }else{
                    ubicacion.append(tipoVia).append(" ").append(nombreVia).append(" ").append(numMuni);
                }

                f.setUbicacionPredio(ubicacion.toString());
                f.setAreaLote(rst.getDouble(15));
                f.setAreaConstruida(rst.getDouble(16));

                f.setReporteBean(r);
                
                lstFichaReporte.add(f);

            }

            pstm.close();
            pstm=null;
            rst.close();
            rst=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            throw new DAOException(ex);
        }
        return lstFichaReporte;
    }
      
    

}
