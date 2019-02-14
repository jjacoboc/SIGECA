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
public class HabilitacionUrbanaDAO extends GenericDAO {

    /** Crea una nueva instancia de HabilitacionUrbanaDAO */
    public HabilitacionUrbanaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla HAB_URBA.
     * @return List
     * @throws DAOException
     */
    public List obtenerHabilitacionesUrbanas() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap habUrba = null;
        List listaHabUrba = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT h.id_hab_urba, h.grupo_urba, h.nom_hab_urba, h.tip_hab_urba, h.cod_hab_urba, h.id_ubi_geo, t.desc_codigo FROM HAB_URBA h, TABLAS_CODIGOS t ");
            sql.append("WHERE h.tip_hab_urba = t.codigo AND t.id_tabla = '").append(Properties.TIPO_HABILITACION_HURBANA).append("' ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                habUrba = new HashMap();
                habUrba.put("id_hab_urba", rst.getString(1));
                habUrba.put("grupo_urba", rst.getString(2));
                habUrba.put("nom_hab_urba", rst.getString(3));
                habUrba.put("tip_hab_urba", rst.getString(4));
                habUrba.put("cod_hab_urba", rst.getString(5));
                habUrba.put("id_ubi_geo", rst.getString(6));
                habUrba.put("desc_tip_hab_urba", rst.getString(7));
                listaHabUrba.add(habUrba);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
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
            log.error("Ocurrió un error al obtener registros de HAB_URBA", e);
            throw new DAOException(e);
        }
        return listaHabUrba;
    }

    /**
     * Retorna una lista con todos los registros de la tabla HAB_URBA.
     * @return List
     * @throws DAOException
     */
    public List obtenerHabilitacionesUrbanasByUbigeo(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap habUrba = null;
        List listaHabUrba = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT h.id_hab_urba, h.grupo_urba, h.nom_hab_urba, h.tip_hab_urba, h.cod_hab_urba, h.id_ubi_geo, t.desc_codigo FROM HAB_URBA h, TABLAS_CODIGOS t ");
            sql.append("WHERE h.tip_hab_urba = t.codigo AND t.id_tabla = '").append(Properties.TIPO_HABILITACION_HURBANA).append("' ");
            sql.append("AND h.id_ubi_geo = '").append((String)mapa.get("id_ubi_geo")).append("' ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                habUrba = new HashMap();
                habUrba.put("id_hab_urba", rst.getString(1));
                habUrba.put("grupo_urba", rst.getString(2));
                habUrba.put("nom_hab_urba", rst.getString(3));
                habUrba.put("tip_hab_urba", rst.getString(4));
                habUrba.put("cod_hab_urba", rst.getString(5));
                habUrba.put("id_ubi_geo", rst.getString(6));
                habUrba.put("desc_tip_hab_urba", rst.getString(7));
                listaHabUrba.add(habUrba);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
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
            log.error("Ocurrió un error al obtener registros de HAB_URBA", e);
            throw new DAOException(e);
        }
        return listaHabUrba;
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla HAB_URBA.
     * @param mapa
     * @return List
     * @throws DAOException
     */
    public List buscarHabilitacionesUrbanas(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap habUrba = null;
        List listaHabUrba = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT h.id_hab_urba, h.grupo_urba, h.nom_hab_urba, h.tip_hab_urba, h.cod_hab_urba, h.id_ubi_geo, t.desc_codigo FROM HAB_URBA h, TABLAS_CODIGOS t ");
            sql.append("WHERE h.tip_hab_urba = t.codigo AND t.id_tabla = '").append(Properties.TIPO_HABILITACION_HURBANA).append("' ");
            if(mapa.get("cod_hab_urba")!=null && !"".equals(mapa.get("cod_hab_urba").toString()))
                sql.append("AND h.cod_hab_urba = '").append(mapa.get("cod_hab_urba").toString()).append("' ");
            if(mapa.get("nom_hab_urba")!=null && !"".equals(mapa.get("nom_hab_urba").toString()))
                sql.append("AND h.nom_hab_urba LIKE '%").append(mapa.get("nom_hab_urba").toString()).append("%' ");
            if(mapa.get("id_ubi_geo")!=null && !"".equals(mapa.get("id_ubi_geo").toString()))
                sql.append("AND h.id_ubi_geo = '").append(mapa.get("id_ubi_geo").toString()).append("' ");
            if(mapa.get("tip_hab_urba")!=null && !"".equals(mapa.get("tip_hab_urba").toString()))
                sql.append("AND h.tip_hab_urba = '").append(mapa.get("tip_hab_urba").toString()).append("' ");
            if(mapa.get("grupo_urba")!=null && !"".equals(mapa.get("grupo_urba").toString()))
                sql.append("AND h.grupo_urba LIKE '%").append(mapa.get("grupo_urba").toString()).append("%' ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next()){
                habUrba = new HashMap();
                habUrba.put("id_hab_urba", rst.getString(1));
                habUrba.put("grupo_urba", rst.getString(2));
                habUrba.put("nom_hab_urba", rst.getString(3));
                habUrba.put("tip_hab_urba", rst.getString(4));
                habUrba.put("cod_hab_urba", rst.getString(5));
                habUrba.put("id_ubi_geo", rst.getString(6));
                habUrba.put("desc_tip_hab_urba", rst.getString(7));
                listaHabUrba.add(habUrba);
            }
            log.debug("Registros encontrados: " + listaHabUrba.size());

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
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
            log.error("Ocurrió un error al buscar registros en HAB_URBA", e);
            throw new DAOException(e);
        }
        return listaHabUrba;
    }

    /**
     * Retorna true si existe un registro con id_hab_urba igual a idHabUrba, de lo
     * contrario retorna false.
     * @param idHabUrba
     * @return boolean
     * @throws DAOException
     */
    public boolean existeHabilitacionUrbana(String idHabUrba) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_hab_urba FROM HAB_URBA WHERE id_hab_urba = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, idHabUrba);
            rst = pstm.executeQuery();

            if(rst.next())
            {
                existeRegistro = true;
            }
            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
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
            log.error("Ocurrió un error al buscar un registro en HAB_URBA", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Inserta un resgistro en la tabla HAB_URBA.
     * @param mapa
     * @return long
     * @throws DAOException
     */
    public long insertarHabilitacionUrbana(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("INSERT INTO HAB_URBA(id_hab_urba, grupo_urba, nom_hab_urba, tip_hab_urba, cod_hab_urba, id_ubi_geo) ");
            sql.append("VALUES(?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)mapa.get("id_hab_urba"));
            pstm.setString(2, (String)mapa.get("grupo_urba"));
            pstm.setString(3, (String)mapa.get("nom_hab_urba"));
            pstm.setString(4, (String)mapa.get("tip_hab_urba"));
            pstm.setString(5, StringUtil.putLeftZeros(((String)mapa.get("cod_hab_urba")).trim(),4));
            pstm.setString(6, (String)mapa.get("id_ubi_geo"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al insertar un registro en HAB_URBA", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Actualiza un resgistro de la tabla HAB_URBA.
     * @param mapa
     * @return long
     * @throws DAOException
     */
    public long actualizarHabilitacionUrbana(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE HAB_URBA SET grupo_urba=?, nom_hab_urba=?, tip_hab_urba=?, cod_hab_urba=?, id_ubi_geo=? ");
            sql.append("WHERE id_hab_urba=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)mapa.get("grupo_urba"));
            pstm.setString(2, (String)mapa.get("nom_hab_urba"));
            pstm.setString(3, (String)mapa.get("tip_hab_urba"));
            pstm.setString(4, StringUtil.putLeftZeros(((String)mapa.get("cod_hab_urba")).trim(),4));
            pstm.setString(5, (String)mapa.get("id_ubi_geo"));
            pstm.setString(6, (String)mapa.get("id_hab_urba"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al actualizar un registro en HAB_URBA", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Elimina un registro de la tabla HAB_URBA.
     * @param idHabUrba
     * @return long
     * @throws DAOException
     */
    public long eliminarHabilitacionUrbana(String idHabUrba) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM HAB_URBA WHERE id_hab_urba = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, idHabUrba);

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al eliminar un registro en HAB_URBA", e);
            throw new DAOException(e);
        }
        return result;
    }
}