/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.resource.Properties;
import com.bmp.sigeca.util.StringUtil;
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
public class ViaDAO extends GenericDAO {

    /** Crea una nueva instancia de ViaDAO */
    public ViaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla VIAS.
     * @return List
     * @throws DAOException
     */
    public List obtenerVias() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap via = null;
        List listaVias = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT v.id_via, v.nom_via, v.tip_via, v.cod_via, v.id_ubi_geo, v.id_sys_via, t.desc_codigo ");
            sql.append("FROM VIAS v, TABLAS_CODIGOS t ");
            sql.append("WHERE v.tip_via = t.codigo AND t.id_tabla = '").append(Properties.TIPO_VIA).append("' ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next()){
                via = new HashMap();
                via.put("id_via", rst.getString(1));
                via.put("nom_via", rst.getString(2));
                via.put("tip_via", rst.getString(3));
                via.put("cod_via", rst.getString(4));
                via.put("id_ubi_geo", rst.getString(5));
                via.put("id_sys_via", rst.getString(6));
                via.put("desc_tip_via", rst.getString(7));
                listaVias.add(via);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
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
            log.error("Ocurrió un error al obtener registros de VIAS", e);
            throw new DAOException(e);
        }
        return listaVias;
    }

    /**
     * Retorna una lista con todos los registros de la tabla VIAS.
     * @return List
     * @throws DAOException
     */
    public List obtenerViasByUbigeo(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap via = null;
        List listaVias = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT v.id_via, v.nom_via, v.tip_via, v.cod_via, v.id_ubi_geo, v.id_sys_via, t.desc_codigo ");
            sql.append("FROM VIAS v, TABLAS_CODIGOS t ");
            sql.append("WHERE v.tip_via = t.codigo AND t.id_tabla = '").append(Properties.TIPO_VIA).append("' ");
            sql.append("AND v.id_ubi_geo = '").append((String)mapa.get("id_ubi_geo")).append("' ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next()){
                via = new HashMap();
                via.put("id_via", rst.getString(1));
                via.put("nom_via", rst.getString(2));
                via.put("tip_via", rst.getString(3));
                via.put("cod_via", rst.getString(4));
                via.put("id_ubi_geo", rst.getString(5));
                via.put("id_sys_via", rst.getString(6));
                via.put("desc_tip_via", rst.getString(7));
                listaVias.add(via);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
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
            log.error("Ocurrió un error al obtener registros de VIAS", e);
            throw new DAOException(e);
        }
        return listaVias;
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla VIAS.
     * @param mapa
     * @return List
     * @throws DAOException
     */
    public List buscarVias(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap via = null;
        List listaVias = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT v.id_via, v.nom_via, v.tip_via, v.cod_via, v.id_ubi_geo, v.id_sys_via, t.desc_codigo FROM VIAS v, TABLAS_CODIGOS t ");
            sql.append("WHERE v.tip_via = t.codigo AND t.id_tabla = '").append(Properties.TIPO_VIA).append("' ");
            if(mapa.get("cod_via")!=null && !"".equals(mapa.get("cod_via").toString()))
                sql.append("AND v.cod_via = '").append(mapa.get("cod_via").toString()).append("' ");
            if(mapa.get("tip_via")!=null && !"".equals(mapa.get("tip_via").toString()))
                sql.append("AND v.tip_via = '").append(mapa.get("tip_via").toString()).append("' ");
            if(mapa.get("nom_via")!=null && !"".equals(mapa.get("nom_via").toString()))
                sql.append("AND v.nom_via LIKE '%").append(mapa.get("nom_via").toString()).append("%' ");
            if(mapa.get("id_ubi_geo")!=null && !"".equals(mapa.get("id_ubi_geo").toString()))
                sql.append("AND v.id_ubi_geo = '").append(mapa.get("id_ubi_geo").toString()).append("' ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next()){
                via = new HashMap();
                via.put("id_via", rst.getString(1));
                via.put("nom_via", rst.getString(2));
                via.put("tip_via", rst.getString(3));
                via.put("cod_via", rst.getString(4));
                via.put("id_ubi_geo", rst.getString(5));
                via.put("id_sys_via", rst.getString(6));
                via.put("desc_tip_via", rst.getString(7));
                listaVias.add(via);
            }
            log.debug("Registros encontrados: " + listaVias.size());

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
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
            log.error("Ocurrió un error al buscar registros en VIAS", e);
            throw new DAOException(e);
        }
        return listaVias;
    }

    /**
     * Retorna true si existe un registro con id_via igual a idVia, de lo
     * contrario retorna false.
     * @param idVia
     * @return boolean
     * @throws DAOException
     */
    public boolean existeVia(String idVia) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_via FROM VIAS WHERE id_via = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, idVia);
            rst = pstm.executeQuery();

            if(rst.next()){
                existeRegistro = true;
            }
            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
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
            log.error("Ocurrió un error al buscar un registro en VIAS", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Inserta un resgistro en la tabla VIAS.
     * @param mapa
     * @return long
     * @throws DAOException
     */
    public long insertarVia(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("INSERT INTO VIAS(id_via, nom_via, tip_via, cod_via, id_ubi_geo, id_sys_via) ");
            sql.append("VALUES(?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)mapa.get("id_via"));
            pstm.setString(2, (String)mapa.get("nom_via"));
            pstm.setString(3, (String)mapa.get("tip_via"));
            pstm.setString(4, StringUtil.putLeftZeros(((String)mapa.get("cod_via")).trim(),6));
            pstm.setString(5, (String)mapa.get("id_ubi_geo"));
            pstm.setString(6, (String)mapa.get("id_sys_via"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al insertar un registro en VIAS", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Actualiza un resgistro de la tabla VIAS.
     * @param mapa
     * @return long
     * @throws DAOException
     */
    public long actualizarVia(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE VIAS SET nom_via=?, tip_via=?, cod_via=?, id_ubi_geo=?, id_sys_via=? ");
            sql.append("WHERE id_via=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)mapa.get("nom_via"));
            pstm.setString(2, (String)mapa.get("tip_via"));
            pstm.setString(3, StringUtil.putLeftZeros(((String)mapa.get("cod_via")).trim(),6));
            pstm.setString(4, (String)mapa.get("id_ubi_geo"));
            pstm.setString(5, (String)mapa.get("id_sys_via"));
            pstm.setString(6, (String)mapa.get("id_via"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al actualizar un registro en VIAS", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Elimina un registro de la tabla VIAS.
     * @param idVia
     * @return
     * @throws DAOException
     */
    public long eliminarVia(String idVia) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM VIAS WHERE id_via = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, idVia);

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al eliminar un registro en VIAS", e);
            throw new DAOException(e);
        }
        return result;
    }

}
