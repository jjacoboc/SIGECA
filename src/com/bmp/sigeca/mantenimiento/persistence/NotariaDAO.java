/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
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
public class NotariaDAO extends GenericDAO {

    /** Crea una nueva instancia de NotariaDAO */
    public NotariaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla NOTARIA.
     * @return List
     * @throws DAOException
     */
    public List obtenerNotarias() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap notaria = null;
        List listaNotarias = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_notaria,nom_notaria,id_ubi_geo FROM NOTARIAS");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next()){
                notaria = new HashMap();
                notaria.put("codigoNotaria", rst.getString(1));
                notaria.put("nombreNotaria", rst.getString(2));
                notaria.put("codigoUbigeo", rst.getString(3));
                listaNotarias.add(notaria);
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
            log.error("Ocurrió un error al obtener registros de NOTARIA", e);
            throw new DAOException(e);
        }
        return listaNotarias;
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla NOTARIA, la consulta busca registros donde id_notaria sea igual a
     * codigoNotaria o nom_notaria contenga nombreNotaria o id_ubi_geo
     * sea igual a codigoUbigeo.
     * @param codigoNotaria
     * @param nombreNotaria
     * @param codigoUbigeo
     * @return List
     * @throws DAOException
     */
    public List buscarNotarias(String codigoNotaria, String nombreNotaria, String codigoUbigeo) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap notaria = null;
        List listaNotarias = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_notaria, nom_notaria, id_ubi_geo FROM NOTARIAS ");
            sql.append("WHERE 1 = 1 ");
            if(codigoNotaria!=null && !"".equals(codigoNotaria))
                sql.append("AND id_notaria = '").append(codigoNotaria).append("' ");
            if(nombreNotaria!=null && !"".equals(nombreNotaria))
                sql.append("AND nom_notaria LIKE '%").append(nombreNotaria).append("%' ");
            if(codigoUbigeo!=null && !"".equals(codigoUbigeo))
                sql.append("AND id_ubi_geo = '").append(codigoUbigeo).append("' ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next()){
                notaria = new HashMap();
                notaria.put("codigoNotaria", rst.getString(1));
                notaria.put("nombreNotaria", rst.getString(2));
                notaria.put("codigoUbigeo", rst.getString(3));
                listaNotarias.add(notaria);
            }
            log.debug("Registros encontrados: " + listaNotarias.size());

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
            log.error("Ocurrió un error al buscar registros en NOTARIA", e);
            throw new DAOException(e);
        }
        return listaNotarias;
    }

    /**
     * Retorna true si existe un registro con id_notaria igual a codigoNotaria, de lo
     * contrario retorna false.
     * @param codigoNotaria
     * @return boolean
     * @throws DAOException
     */
    public boolean existeNotaria(String codigoNotaria) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_notaria FROM NOTARIAS WHERE id_notaria = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoNotaria);
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
            log.error("Ocurrió un error al buscar un registro en NOTARIAS", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Retorna true si existe un registro con id_notaria igual a codigoNotaria, de lo
     * contrario retorna false.
     * @param codigoNotaria
     * @return boolean
     * @throws DAOException
     */
    public boolean existeNotariaEnRegistroLegal(String codigoNotaria) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_notaria FROM REGISTRO_LEGAL WHERE id_notaria = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoNotaria);
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
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Inserta un resgistro con los valores codigoNotaria, nombreNotaria y
     * codigoUbigeo en la tabla NOTARIA.
     * @param codigoNotaria
     * @param nombreNotaria
     * @param codigoUbigeo
     * @return long
     * @throws DAOException
     */
    public long insertarNotaria(String codigoNotaria, String nombreNotaria, String codigoUbigeo) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO NOTARIAS(id_notaria, nom_notaria, id_ubi_geo) ");
            sql.append("VALUES(?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, StringUtil.putLeftZeros(codigoNotaria,5));
            pstm.setString(2, nombreNotaria);
            pstm.setString(3, codigoUbigeo);

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
            log.error("Ocurrió un error al insertar un registro en NOTARIAS", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Actualiza el campo nom_notaria con el valor de nombreNotaria e id_ubi_geo
     * con el valor de codigoUbigeo del resgistro con el campo
     * id_notaria = codigoNotaria de la tabla NOTARIA.
     * @param codigoNotaria
     * @param nombreNotaria
     * @param codigoUbigeo
     * @return long
     * @throws DAOException
     */
    public long actualizarNotaria(String codigoNotaria, String nombreNotaria, String codigoUbigeo) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE NOTARIAS SET nom_notaria=?, id_ubi_geo=? ");
            sql.append("WHERE id_notaria=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, nombreNotaria);
            pstm.setString(2, codigoUbigeo);
            pstm.setString(3, codigoNotaria);

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
            log.error("Ocurrió un error al actualizar un registro en NOTARIAS", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Elimina el registro con id_notaria igual a codigoNotaria.
     * @param codigoNotaria
     * @return
     * @throws DAOException
     */
    public long eliminarNotaria(String codigoNotaria) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM NOTARIAS WHERE id_notaria = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoNotaria);

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
            log.error("Ocurrió un error al eliminar un registro en NOTARIAS", e);
            throw new DAOException(e);
        }
        return result;
    }
}