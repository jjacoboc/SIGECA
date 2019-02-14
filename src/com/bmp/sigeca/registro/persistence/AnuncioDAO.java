/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.resource.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class AnuncioDAO extends GenericDAO {

    /** Crea una nueva instancia de AnuncioDAO */
    public AnuncioDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarAnuncio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO AUTORIZACIONES_ANUNCIOS(id_ficha,cod_anuncio,nro_lados,area_autorizada,area_verificada,");
            sql.append("nro_expediente,nro_licencia,fecha_expedicion,fecha_vencimiento) ");
            sql.append("VALUES(?,?,?,?,?,?,?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),to_date(?,'DD/MM/YYYY HH24:MI:SS'))");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("id_ficha"));
            pstm.setString(2,(String)map.get("codTipAnuncio"));
            pstm.setInt(3,Integer.parseInt((String)map.get("numLados")));
            pstm.setDouble(4,Double.parseDouble((String)map.get("areAutorizada")));
            pstm.setDouble(5,Double.parseDouble((String)map.get("areVerificada")));
            pstm.setString(6,(String)map.get("numExpAnuncio"));
            pstm.setString(7,(String)map.get("numLicAnuncio"));
            pstm.setString(8,(String)map.get("fecExpedicion"));
            pstm.setString(9,(String)map.get("fecVencimiento"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public long actualizarAnuncio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE AUTORIZACIONES_ANUNCIOS SET cod_anuncio=?,nro_lados=?,area_autorizada=?,area_verificada=?,nro_expediente=?,");
            sql.append("nro_licencia=?,fecha_expedicion=to_date(?,'DD/MM/YYYY HH24:MI:SS'),fecha_vencimiento=to_date(?,'DD/MM/YYYY HH24:MI:SS') ");
            sql.append("WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codTipAnuncio"));
            pstm.setInt(2,Integer.parseInt((String)map.get("numLados")));
            pstm.setDouble(3,Double.parseDouble((String)map.get("areAutorizada")));
            pstm.setDouble(4,Double.parseDouble((String)map.get("areVerificada")));
            pstm.setString(5,(String)map.get("numExpAnuncio"));
            pstm.setString(6,(String)map.get("numLicAnuncio"));
            pstm.setString(7,(String)map.get("fecExpedicion"));
            pstm.setString(8,(String)map.get("fecVencimiento"));
            pstm.setString(9,(String)map.get("id_ficha"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                    pstm=null;
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public boolean existeAnuncio(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM AUTORIZACIONES_ANUNCIOS WHERE id_ficha = ? AND cod_anuncio = ?");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1,(String)map.get("id_ficha"));
            pstm.setString(2,(String)map.get("codTipAnuncio"));

            rst = pstm.executeQuery();

            if (rst.next()) {
                existe = true;
            }

            pstm.close();
            pstm = null;
            rst.close();
            rst = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (Exception ignore) {
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception ignore) {
                }
            }
            throw new DAOException(ex);
        }
        return existe;
    }

    public int eliminarAnuncio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM AUTORIZACIONES_ANUNCIOS WHERE id_ficha = ? AND cod_anuncio = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));
            pstm.setString(2,(String)map.get("codTipAnuncio"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm = null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public int eliminarAnuncioByFicha(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TINF_COMPLEMENTARIA WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm=null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public List obtenerListaAnuncio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap anuncio = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT DISTINCT a.id_ficha,a.cod_anuncio,tc.desc_codigo,a.nro_lados,a.area_autorizada,a.area_verificada,a.nro_expediente,a.nro_licencia,");
            sql.append("to_char(a.fecha_expedicion,'dd/MM/yyyy'),to_char(a.fecha_vencimiento,'dd/MM/yyyy') ");
            sql.append("FROM autorizaciones_anuncios a, fichas f, tablas_codigos tc ");
            sql.append("WHERE a.cod_anuncio=tc.codigo AND tc.id_tabla = '").append(Properties.TIPO_ANUNCIO).append("' AND a.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                anuncio = new HashMap();
                anuncio.put("id_ficha", rst.getString(1));
                anuncio.put("codTipAnuncio", rst.getString(2));
                anuncio.put("desTipAnuncio", rst.getString(3));
                anuncio.put("numLados", rst.getString(4));
                anuncio.put("areAutorizada", rst.getString(5));
                anuncio.put("areVerificada", rst.getString(6));
                anuncio.put("numExpAnuncio", rst.getString(7));
                anuncio.put("numLicAnuncio", rst.getString(8));
                anuncio.put("fecExpedicion", rst.getString(9));
                anuncio.put("fecVencimiento", rst.getString(10));
                lista.add(anuncio);
            }

            pstm.close();
            pstm=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                    pstm=null;
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return lista;
    }
}
