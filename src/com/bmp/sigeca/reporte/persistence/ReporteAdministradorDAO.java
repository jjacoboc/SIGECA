/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.reporte.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.reporte.bean.FichaReporteAdministradorBean;
import com.bmp.sigeca.reporte.bean.FichaReporteUsuarioBean;
import com.bmp.sigeca.reporte.bean.ReporteAdministradorBean;
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
public class ReporteAdministradorDAO extends GenericDAO{


     /** Crea una nueva instancia de DAOUsuario */
    public ReporteAdministradorDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public  List<FichaReporteAdministradorBean>  getLstReporte01Detallado(HashMap map) throws DAOException{

        List<FichaReporteAdministradorBean> lstFichaReporte = new ArrayList<FichaReporteAdministradorBean>();
        PreparedStatement pstm = null;
        ResultSet rst = null;

        PreparedStatement pstmHistorial = null;
        ResultSet rstHistorial = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT ");
            sql.append(" f.fecha_ingreso  ,  ");
            sql.append(" f.nro_ficha , ");
            sql.append(" t.desc_codigo ,  ");
            sql.append(" f.id_ficha ,  ");
            sql.append(" f.fecha_revision,  ");
            sql.append(" e.nombre_estado ");

            sql.append("FROM fichas f, tablas_codigos t  , estado e , usuario u ");

            sql.append("WHERE f.tip_ficha = t.codigo and t.id_tabla = 'FCH' and ");
            sql.append("  f.id_estado = e.id_estado and f.usuario_ingreso = u.id_usuario ");


             if(map.containsKey("digitador")&&map.get("digitador")!=null && !"".equals((String)map.get("digitador")) ){
                  sql.append("AND u.id_usuario ='").append((String)map.get("digitador")).append("' ");
             }

             if(map.containsKey("fecInicio")){
                  sql.append("AND to_timestamp(to_char(f.fecha_ingreso, 'yyyy/mm/dd'), 'yyyy/mm/dd') >= '").append((String)map.get("fecInicio")).append("' ");
             }

            if(map.containsKey("fecFin")){
                  sql.append("AND to_timestamp(to_char(f.fecha_ingreso, 'yyyy/mm/dd'), 'yyyy/mm/dd') <= '").append((String)map.get("fecFin")).append("' ");
             }


            if(map.containsKey("estado")&&map.get("estado")!=null && !"".equals((String)map.get("estado"))){
                sql.append("AND e.id_estado='").append((String)map.get("estado")).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();


            ReporteAdministradorBean r = new ReporteAdministradorBean();
            r.setDepartamento("" );
            r.setProvincia("" );
            r.setDistrito("" );
            r.setDigitador("");
            r.setFechaInicio("");
            r.setFechaFin("");
            r.setEstado("");


            while(rst.next()){

                FichaReporteAdministradorBean f = new FichaReporteAdministradorBean();
                f.setFechaDigitador(rst.getString(1));
                f.setNumeroFicha(rst.getString(2));
                f.setFichasInvolucradas(rst.getString(3));

                //saber si tiene historial o no
                 if(getHistorial(rst.getString(4)) > 0)
                    f.setHistorial("Si");
                else
                    f.setHistorial("No");

                //------------------------------

                if(map.containsKey("estado")&&map.get("estado")!=null && !"".equals((String)map.get("estado")) && "02".equals((String)map.get("estado"))){
                     f.setFechaRevision(rst.getString(5));
                }
                f.setEstado(rst.getString(6));

                f.setReporteBean(r);
                lstFichaReporte.add(f);



                pstmHistorial.close();
                pstmHistorial=null;
                rstHistorial.close();
                rstHistorial=null;
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



      public  List<FichaReporteAdministradorBean>  getLstReporte02Detallado(HashMap map) throws DAOException{

        List<FichaReporteAdministradorBean> lstFichaReporte = new ArrayList<FichaReporteAdministradorBean>();
        PreparedStatement pstm = null;
        ResultSet rst = null;

         PreparedStatement pstmHistorial = null;
        ResultSet rstHistorial = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT ");
            sql.append(" f.fecha_ingreso  ,  ");
            sql.append(" f.nro_ficha , ");
            sql.append(" t.desc_codigo ,  ");
            sql.append(" f.id_ficha ,  ");
            sql.append(" tr.nombres, tr.ape_paterno, tr.ape_materno ,  ");
            sql.append(" f.fecha_revision,  ");
            sql.append(" e.nombre_estado ");

            sql.append("FROM fichas f, tablas_codigos t  , estado e , usuario u , perfil p , trabajadores tr ");

            sql.append("WHERE f.tip_ficha = t.codigo and t.id_tabla = 'FCH' and ");
            sql.append("  f.id_estado = e.id_estado and f.usuario_ingreso = u.id_usuario and ");
            sql.append("  u.id_perfil =  p.id_perfil and p.nombre_perfil = 'Digitador' and ");
            sql.append("  tr.id_trabajador = u.id_usuario  ");

           
             if(map.containsKey("fecInicio")){
                  sql.append("AND to_timestamp(to_char(f.fecha_ingreso, 'yyyy/mm/dd'), 'yyyy/mm/dd') >= '").append((String)map.get("fecInicio")).append("' ");
             }

            if(map.containsKey("fecFin")){
                  sql.append("AND to_timestamp(to_char(f.fecha_ingreso, 'yyyy/mm/dd'), 'yyyy/mm/dd') <= '").append((String)map.get("fecFin")).append("' ");
             }


            if(map.containsKey("estado")&&map.get("estado")!=null && !"".equals((String)map.get("estado"))){
                sql.append("AND e.id_estado='").append((String)map.get("estado")).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();


            ReporteAdministradorBean r = new ReporteAdministradorBean();
            r.setDepartamento("" );
            r.setProvincia("" );
            r.setDistrito("" );
            r.setDigitador("");
            r.setFechaInicio("");
            r.setFechaFin("");
            r.setEstado("");


            while(rst.next()){

                FichaReporteAdministradorBean f = new FichaReporteAdministradorBean();
                f.setFechaDigitador(rst.getString(1));
                f.setNumeroFicha(rst.getString(2));
                f.setFichasInvolucradas(rst.getString(3));

                //saber si tiene historial o no
                
                if(getHistorial(rst.getString(4)) > 0)
                    f.setHistorial("Si");
                else
                    f.setHistorial("No");

                //------------------------------

                f.setDigitador(rst.getString(5) + " " + rst.getString(6) + " "  + rst.getString(7) );

                if(map.containsKey("estado")&&map.get("estado")!=null && !"".equals((String)map.get("estado")) && "02".equals((String)map.get("estado"))){
                     f.setFechaRevision(rst.getString(5));
                }

                f.setEstado(rst.getString(9));

                f.setReporteBean(r);
                lstFichaReporte.add(f);



                pstmHistorial.close();
                pstmHistorial=null;
                rstHistorial.close();
                rstHistorial=null;
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




      public  List<FichaReporteAdministradorBean>  getLstReporte01General(HashMap map) throws DAOException{

        List<FichaReporteAdministradorBean> lstFichaReporte = new ArrayList<FichaReporteAdministradorBean>();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
             StringBuffer sql = new StringBuffer("");
            sql.append("SELECT ");
            sql.append(" tr.nombres, tr.ape_paterno, tr.ape_materno ,  ");
            sql.append(" f.usuario_ingreso ,  ");
            sql.append(" f.fecha_actualizacion ,  ");
            sql.append(" f.fecha_ingreso ,   ");
            sql.append(" f.fecha_revision   ");


            sql.append("FROM fichas f,  estado e , usuario u , perfil p , trabajadores tr ");

            sql.append(" WHERE f.id_estado = e.id_estado and f.usuario_ingreso = u.id_usuario and ");
            sql.append("  u.id_perfil =  p.id_perfil and p.nombre_perfil = 'Digitador' and ");
            sql.append("  tr.id_trabajador = u.id_usuario  ");

             if(map.containsKey("digitador")&&map.get("digitador")!=null && !"".equals((String)map.get("digitador")) ){
                  sql.append("AND u.id_usuario ='").append((String)map.get("digitador")).append("' ");
             }

             if(map.containsKey("fecInicio")){
                  sql.append("AND to_timestamp(to_char(f.fecha_ingreso, 'yyyy/mm/dd'), 'yyyy/mm/dd') >= '").append((String)map.get("fecInicio")).append("' ");
             }

            if(map.containsKey("fecFin")){
                  sql.append("AND to_timestamp(to_char(f.fecha_ingreso, 'yyyy/mm/dd'), 'yyyy/mm/dd') <= '").append((String)map.get("fecFin")).append("' ");
             }


            if(map.containsKey("estado")&&map.get("estado")!=null && !"".equals((String)map.get("estado"))){
                sql.append("AND e.id_estado='").append((String)map.get("estado")).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();


            ReporteAdministradorBean r = new ReporteAdministradorBean();
            r.setDepartamento("" );
            r.setProvincia("" );
            r.setDistrito("" );
            r.setDigitador("");
            r.setFechaInicio("");
            r.setFechaFin("");
            r.setEstado("");


            while(rst.next()){

                FichaReporteAdministradorBean f = new FichaReporteAdministradorBean();
                f.setDigitador(rst.getString(1) + " " + rst.getString(2) + " "  + rst.getString(3) );

                String idDigitador = rst.getString(4);
                if(map.containsKey("estado")&&map.get("estado")!=null && !"".equals((String)map.get("estado"))){
                    if(((String)map.get("estado")).equals("01")) //por revisar
                    {
                         if(rst.getTimestamp(5) != null)
                         {
                             f.setTotalDomingo(getCountDayOfWeek(idDigitador,"0","fecha_actualizacion"));
                             f.setTotalLunes(getCountDayOfWeek(idDigitador,"1","fecha_actualizacion"));
                             f.setTotalMartes(getCountDayOfWeek(idDigitador,"2","fecha_actualizacion"));
                             f.setTotalMiercoles(getCountDayOfWeek(idDigitador,"3","fecha_actualizacion"));
                             f.setTotalJueves(getCountDayOfWeek(idDigitador,"4","fecha_actualizacion"));
                             f.setTotalViernes(getCountDayOfWeek(idDigitador,"5","fecha_actualizacion"));
                             f.setTotalSabado(getCountDayOfWeek(idDigitador,"6","fecha_actualizacion"));
                             f.calcularTotal();

                         } else if (rst.getTimestamp(6) != null)
                         {
                             f.setTotalDomingo(getCountDayOfWeek(idDigitador,"0","fecha_ingreso"));
                             f.setTotalLunes(getCountDayOfWeek(idDigitador,"1","fecha_ingreso"));
                             f.setTotalMartes(getCountDayOfWeek(idDigitador,"2","fecha_ingreso"));
                             f.setTotalMiercoles(getCountDayOfWeek(idDigitador,"3","fecha_ingreso"));
                             f.setTotalJueves(getCountDayOfWeek(idDigitador,"4","fecha_ingreso"));
                             f.setTotalViernes(getCountDayOfWeek(idDigitador,"5","fecha_ingreso"));
                             f.setTotalSabado(getCountDayOfWeek(idDigitador,"6","fecha_ingreso"));
                             f.calcularTotal();
                         }

                    }else if (rst.getTimestamp(7) != null){ //revisado

                                f.setTotalDomingo(getCountDayOfWeek(idDigitador,"0","fecha_revision"));
                                 f.setTotalLunes(getCountDayOfWeek(idDigitador,"1","fecha_revision"));
                                 f.setTotalMartes(getCountDayOfWeek(idDigitador,"2","fecha_revision"));
                                 f.setTotalMiercoles(getCountDayOfWeek(idDigitador,"3","fecha_revision"));
                                 f.setTotalJueves(getCountDayOfWeek(idDigitador,"4","fecha_revision"));
                                 f.setTotalViernes(getCountDayOfWeek(idDigitador,"5","fecha_revision"));
                                 f.setTotalSabado(getCountDayOfWeek(idDigitador,"6","fecha_revision"));
                                 f.calcularTotal();

                        }else{

                        }


                }else{

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


      public  Integer  getCountDayOfWeek(String idDigitador, String dayWeek, String tipoFecha) throws DAOException{
          Integer count = 0;

           PreparedStatement pstm = null;
            ResultSet rst = null;
            try {

                StringBuffer sql = new StringBuffer("");
                sql.append("select count(f.id_ficha) ");
                sql.append(" from fichas f   ");
                sql.append(" where date_part('dw',f.").append(tipoFecha).append(") = '").append(dayWeek).append("'");
                sql.append(" and f.usuario_ingreso ='").append(idDigitador).append("'");

                 pstm = context.getConnection().prepareStatement(sql.toString().trim());
                rst = pstm.executeQuery();

                 while(rst.next()){
                     count = rst.getInt(1);
                 }

            }catch (Exception ex) {
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

          return count;

      }

      public  Integer  getHistorial(String idFicha) throws DAOException{
          Integer count = 0;

           PreparedStatement pstm = null;
            ResultSet rst = null;
            try {

                StringBuffer sql = new StringBuffer("");
                sql.append("SELECT ");
                sql.append(" count(af.numero_ficha_nuevo) as total  ");
                sql.append(" from fichas f, anterior_ficha af   ");
                sql.append(" where f.id_ficha = af.numero_ficha_nuevo   ");
                sql.append(" and f.id_ficha = '").append(idFicha).append("'");

                 pstm = context.getConnection().prepareStatement(sql.toString().trim());
                rst = pstm.executeQuery();

                 while(rst.next()){
                     count = rst.getInt(1);
                 }

            }catch (Exception ex) {
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

          return count;

      }


}
