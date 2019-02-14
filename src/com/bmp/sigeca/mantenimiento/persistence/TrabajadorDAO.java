/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class TrabajadorDAO extends GenericDAO {

    /** Crea una nueva instancia de TrabajadorDAO */
    public TrabajadorDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long getNextPK() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        long result = 1;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT max(to_Number(id_trabajador,'999')) as id FROM TRABAJADORES");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            if(rst.next()){
                result = rst.getLong(1) + 1;
            }

            pstm.close();
            pstm = null;
            rst.close();
            rst = null;
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

    /**
     * Retorna un registro de la tabla TRABAJADORES.
     * @return HashMap
     * @throws DAOException
     */
    public HashMap obtenerTrabajadorByPK(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap trabajador = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT t.id_trabajador,t.nombres,t.ape_paterno,t.ape_materno,t.dni,to_char(t.fecha,'dd/MM/yyyy'),");
            sql.append("t.email,t.id_cargo,t.id_est_usu_tra,c.nombre_cargo,e.nombre_estado ");
            sql.append("FROM TRABAJADORES t, CARGO c, ESTADO_USU_TRA e ");
            sql.append("WHERE t.id_cargo=c.id_cargo AND t.id_est_usu_tra=e.id_est_usu_tra ");
            sql.append("AND t.id_trabajador = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)map.get("id_trabajador"));

            rst = pstm.executeQuery();

            if(rst.next()){
                trabajador = new HashMap();
                trabajador.put("id_trabajador", rst.getString(1));
                trabajador.put("nombres", rst.getString(2));
                trabajador.put("ape_paterno", rst.getString(3));
                trabajador.put("ape_materno", rst.getString(4));
                trabajador.put("dni", rst.getString(5));
                trabajador.put("fecha", rst.getString(6));
                trabajador.put("email", rst.getString(7));
                trabajador.put("id_cargo", rst.getString(8));
                trabajador.put("id_est_usu_tra", rst.getString(9));
                trabajador.put("nombre_cargo", rst.getString(10));
                trabajador.put("nombre_estado", rst.getString(11));
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
            log.error("Ocurrió un error al obtener registros de TRABAJADORES", e);
            throw new DAOException(e);
        }
        return trabajador;
    }

    /**
     * Retorna una lista con todos los registros de la tabla TRABAJADORES.
     * @return List
     * @throws DAOException
     */
    public List obtenerTrabajadores() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap trabajador = null;
        List listaTrabajadores = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT t.id_trabajador,t.nombres,t.ape_paterno,t.ape_materno,t.dni,to_char(t.fecha,'dd/MM/yyyy'),");
            sql.append("t.email,t.id_cargo,t.id_est_usu_tra,c.nombre_cargo,e.nombre_estado ");
            sql.append("FROM TRABAJADORES t, CARGO c, ESTADO_USU_TRA e ");
            sql.append("WHERE t.id_cargo=c.id_cargo AND t.id_est_usu_tra=e.id_est_usu_tra ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                trabajador = new HashMap();
                trabajador.put("id_trabajador", rst.getString(1));
                trabajador.put("nombres", rst.getString(2));
                trabajador.put("ape_paterno", rst.getString(3));
                trabajador.put("ape_materno", rst.getString(4));
                trabajador.put("dni", rst.getString(5));
                trabajador.put("fecha", rst.getString(6));
                trabajador.put("email", rst.getString(7));
                trabajador.put("id_cargo", rst.getString(8));
                trabajador.put("id_est_usu_tra", rst.getString(9));
                trabajador.put("nombre_cargo", rst.getString(10));
                trabajador.put("nombre_estado", rst.getString(11));
                listaTrabajadores.add(trabajador);
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
            log.error("Ocurrió un error al obtener registros de TRABAJADORES", e);
            throw new DAOException(e);
        }
        return listaTrabajadores;
    }

    /**
     * Retorna una lista con todos los registros encontrados de la tabla TRABAJADORES.
     * @return List
     * @throws DAOException
     */
    public List buscarTrabajadores(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap trabajador = null;
        List listaTrabajadores = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT t.id_trabajador,t.nombres,t.ape_paterno,t.ape_materno,t.dni,to_char(t.fecha,'dd/MM/yyyy'),");
            sql.append("t.email,t.id_cargo,t.id_est_usu_tra,c.nombre_cargo,e.nombre_estado ");
            sql.append("FROM TRABAJADORES t, CARGO c, ESTADO_USU_TRA e ");
            sql.append("WHERE t.id_cargo=c.id_cargo AND t.id_est_usu_tra=e.id_est_usu_tra ");

            if(mapa!=null && mapa.containsKey("dni") && !"".equals((String)mapa.get("dni"))){
                sql.append("AND t.dni = '").append(((String)mapa.get("dni")).trim()).append("' ");
            }
            if(mapa!=null && mapa.containsKey("nombres") && !"".equals((String)mapa.get("nombres"))){
                sql.append("AND t.nombres LIKE '%").append(((String)mapa.get("nombres")).trim()).append("%' ");
            }
            if(mapa!=null && mapa.containsKey("ape_paterno") && !"".equals((String)mapa.get("ape_paterno"))){
                sql.append("AND t.ape_paterno LIKE '%").append(((String)mapa.get("ape_paterno")).trim()).append("%' ");
            }
            if(mapa!=null && mapa.containsKey("ape_materno") && !"".equals((String)mapa.get("ape_materno"))){
                sql.append("AND t.ape_materno LIKE '%").append(((String)mapa.get("ape_materno")).trim()).append("%' ");
            }
            if(mapa!=null && mapa.containsKey("id_cargo") && !"".equals((String)mapa.get("id_cargo"))){
                sql.append("AND t.id_cargo = '").append(((String)mapa.get("id_cargo")).trim()).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                trabajador = new HashMap();
                trabajador.put("id_trabajador", rst.getString(1));
                trabajador.put("nombres", rst.getString(2));
                trabajador.put("ape_paterno", rst.getString(3));
                trabajador.put("ape_materno", rst.getString(4));
                trabajador.put("dni", rst.getString(5));
                trabajador.put("fecha", rst.getString(6));
                trabajador.put("email", rst.getString(7));
                trabajador.put("id_cargo", rst.getString(8));
                trabajador.put("id_est_usu_tra", rst.getString(9));
                trabajador.put("nombre_cargo", rst.getString(10));
                trabajador.put("nombre_estado", rst.getString(11));
                listaTrabajadores.add(trabajador);
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
            log.error("Ocurrió un error al obtener registros de TRABAJADORES", e);
            throw new DAOException(e);
        }
        return listaTrabajadores;
    }

    /**
     * Inserta un resgistro en la tabla TRABAJADORES.
     * @param mapa
     * @return long
     * @throws DAOException
     */
    public long insertarTrabajador(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("INSERT INTO TRABAJADORES(id_trabajador, nombres, ape_paterno, ape_materno, dni, fecha, email, id_cargo, id_est_usu_tra) ");
            sql.append("VALUES(?,?,?,?,?,NOW(),?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)mapa.get("id_trabajador"));
            pstm.setString(2, (String)mapa.get("nombres"));
            pstm.setString(3, (String)mapa.get("ape_paterno"));
            pstm.setString(4, (String)mapa.get("ape_materno"));
            pstm.setString(5, (String)mapa.get("dni"));
            pstm.setString(6, (String)mapa.get("email"));
            pstm.setString(7, (String)mapa.get("id_cargo"));
            pstm.setString(8, (String)mapa.get("id_est_usu_tra"));

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
            log.error("Ocurrió un error al insertar un registro en TRABAJADORES", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Actualiza un resgistro de la tabla TRABAJADORES.
     * @param mapa
     * @return long
     * @throws DAOException
     */
    public long actualizarTrabajador(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TRABAJADORES SET nombres=?, ape_paterno=?, ape_materno=?, dni=?, ");
            sql.append("email=?, id_cargo=?, id_est_usu_tra=? ");
            sql.append("WHERE id_trabajador=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)mapa.get("nombres"));
            pstm.setString(2, (String)mapa.get("ape_paterno"));
            pstm.setString(3, (String)mapa.get("ape_materno"));
            pstm.setString(4, (String)mapa.get("dni"));
            pstm.setString(5, (String)mapa.get("email"));
            pstm.setString(6, (String)mapa.get("id_cargo"));
            pstm.setString(7, (String)mapa.get("id_est_usu_tra"));
            pstm.setString(8, (String)mapa.get("id_trabajador"));

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
            log.error("Ocurrió un error al actualizar un registro en TRABAJADORES", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Retorna true si existe un registro con dni igual al ingresado, de lo
     * contrario retorna false.
     * @param idVia
     * @return boolean
     * @throws DAOException
     */
    public boolean existeTrabajadorByDni(String dni) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_trabajador FROM TRABAJADORES WHERE dni = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, dni);
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
            log.error("Ocurrió un error al buscar un registro en TRABAJADORES", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Elimina un registro de la tabla TRABAJADORES.
     * @param mapa
     * @return
     * @throws DAOException
     */
    public long eliminarTrabajador(HashMap mapa) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TRABAJADORES WHERE id_trabajador = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)mapa.get("id_trabajador"));

            result = pstm.executeUpdate();
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al eliminar un registro en TRABAJADORES", e);
            throw new DAOException(e);
        }
        return result;
    }

}
