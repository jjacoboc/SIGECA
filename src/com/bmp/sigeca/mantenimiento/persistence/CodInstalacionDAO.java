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
public class CodInstalacionDAO extends GenericDAO {

    /** Crea una nueva instancia de CodInstalacionDAO */
    public CodInstalacionDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla CODIGO_INSTALACION.
     * @return List
     * @throws DAOException
     */
    public List obtenerCodInstalaciones() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap notaria = null;
        List listaCodInstalaciones = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_instalacion,desc_instalacion,unidad,material FROM CODIGOS_INSTALACIONES");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                notaria = new HashMap();
                notaria.put("codigoInstalacion", rst.getString(1));
                notaria.put("descripcionInstalacion", rst.getString(2));
                notaria.put("unidad", rst.getString(3));
                notaria.put("material", rst.getString(4));
                listaCodInstalaciones.add(notaria);
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
            log.error("Ocurrió un error al obtener registros de CODIGOS_INSTALACIONES", e);
            throw new DAOException(e);
        }
        return listaCodInstalaciones;
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla CODIGO_INSTALACION, la consulta busca registros donde
     * codigo_instalacion sea igual a codigo o desc_instalacion contenga
     * descripcion o unidad contenga el valor de unidad(argumento) o
     * material contenga el valor de material(argumento)
     * @param codigoInstalacion
     * @param descripcionInstalacion
     * @param unidad
     * @param material
     * @return List
     * @throws DAOException
     */
    public List buscarCodInstalaciones(String codigoInstalacion, String descripcionInstalacion, String unidad, String material) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap codInstalacion = null;
        List listaCodInstalaciones = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_instalacion, desc_instalacion, unidad, material FROM CODIGOS_INSTALACIONES WHERE 1=1 ");
            if(codigoInstalacion!=null && !"".equals(codigoInstalacion)){
                sql.append("AND cod_instalacion = '").append(StringUtil.putLeftZeros(codigoInstalacion.trim(),2)).append("' ");
            }
            if(descripcionInstalacion!=null && !"".equals(descripcionInstalacion)){
                sql.append("AND desc_instalacion LIKE '%").append(descripcionInstalacion).append("%' ");
            }
            if(unidad!=null && !"".equals(unidad)){
                sql.append("AND unidad LIKE '%").append(unidad).append("%' ");
            }
            if(material!=null && !"".equals(material)){
                sql.append("AND material LIKE '%").append(material).append("%' ");
            }
            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                codInstalacion = new HashMap();
                codInstalacion.put("codigoInstalacion", rst.getString(1));
                codInstalacion.put("descripcionInstalacion", rst.getString(2));
                codInstalacion.put("unidad", rst.getString(3));
                codInstalacion.put("material", rst.getString(4));
                listaCodInstalaciones.add(codInstalacion);
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
            log.error("Ocurrió un error al buscar registros en CODIGOS_INSTALACIONES", e);
            throw new DAOException(e);
        }
        return listaCodInstalaciones;
    }

    /**
     * Retorna true si existe un registro con cod_instalacion igual a codigoInstalacion, de lo
     * contrario retorna false.
     * @param codigoInstalacion
     * @return boolean
     * @throws DAOException
     */
    public boolean existeCodInstalacion(String codigoInstalacion) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_instalacion FROM CODIGOS_INSTALACIONES WHERE cod_instalacion = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, StringUtil.putLeftZeros(codigoInstalacion.trim(),2));
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
            log.error("Ocurrió un error al buscar un registro en CODIGOS_INSTALACIONES", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Inserta un resgistro con los valores codigoInstalacion, descripcionInstalacion,
     * unidad y material de la tabla CODIGO_INSTALACION.
     * @param codigoInstalacion
     * @param descripcionInstalacion
     * @param unidad
     * @param material
     * @return
     * @throws DAOException
     */
    public long insertarCodInstalacion(String codigoInstalacion, String descripcionInstalacion, String unidad, String material) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO CODIGOS_INSTALACIONES(cod_instalacion,desc_instalacion,unidad,material) ");
            sql.append("VALUES(?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, StringUtil.putLeftZeros(codigoInstalacion.trim(),2));
            pstm.setString(2, descripcionInstalacion);
            pstm.setString(3, unidad);
            pstm.setString(4, material);

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
            log.error("Ocurrió un error al insertar un registro en CODIGOS_INSTALACIONES", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Actualiza el campo desc_instalacion con el valor de descripcion,
     * unidad con el valor de unidad(parámetro) y material con el valor de
     * material(parámetro) del resgistro con el campo cod_instalacion = codigo
     * de la tabla CODIGO_INSTALACION.
     * @param codigo
     * @param descripcion
     * @param unidad
     * @param material
     * @return long
     * @throws DAOException
     */
    public long actualizarCodInstalacion(String codigoInstalacion, String descripcionInstalacion, String unidad, String material) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CODIGOS_INSTALACIONES SET desc_instalacion=?, unidad=?, material=? ");
            sql.append("WHERE cod_instalacion=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, descripcionInstalacion);
            pstm.setString(2, unidad);
            pstm.setString(3, material);
            pstm.setString(4, StringUtil.putLeftZeros(codigoInstalacion.trim(),2));

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
            log.error("Ocurrió un error al actualizar un registro en CODIGOS_INSTALACIONES", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Elimina el registro con cod_instalacion igual a codigoInstalacion.
     * @param codigoInstalacion
     * @return long
     * @throws DAOException
     */
    public long eliminarCodInstalacion(String codigoInstalacion) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM CODIGOS_INSTALACIONES WHERE cod_instalacion = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, StringUtil.putLeftZeros(codigoInstalacion.trim(),2));

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
            log.error("Ocurrió un error al eliminar un registro en CODIGOS_INSTALACIONES", e);
            throw new DAOException(e);
        }
        return result;
    }
}