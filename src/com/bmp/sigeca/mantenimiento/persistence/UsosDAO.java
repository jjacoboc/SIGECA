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
public class UsosDAO extends GenericDAO {

    /** Crea una nueva instancia de UsosDAO */
    public UsosDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla USOS.
     * @return List
     * @throws DAOException
     */
    public List obtenerUsos() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap uso = null;
        List listaUsos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_uso,desc_uso FROM USOS");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                uso = new HashMap();
                uso.put("codigoUso", rst.getString(1));
                uso.put("descripcionUso", rst.getString(2));
                listaUsos.add(uso);
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
            log.error("Ocurrió un error al obtener registros de USO", e);
            throw new DAOException(e);
        }
        return listaUsos;
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla USOS, la consulta busca registros donde cod_usos sea igual a
     * codigoUso o desc_uso contenga descripcionUso.
     * @param codigoUso
     * @param descripcionUso
     * @return List
     * @throws DAOException
     */
    public List buscarUsos(String codigoUso, String descripcionUso) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap uso = null;
        List listaUsos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_uso, desc_uso FROM USOS WHERE 1=1 ");
            if(codigoUso!=null && !"".equals(codigoUso)){
                sql.append("AND cod_uso = '").append(codigoUso).append("' ");
            }
            if(descripcionUso!=null && !"".equals(descripcionUso)){
                sql.append("AND desc_uso LIKE '%").append(descripcionUso).append("%'");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                uso = new HashMap();
                uso.put("codigoUso", rst.getString(1));
                uso.put("descripcionUso", rst.getString(2));
                listaUsos.add(uso);
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
            log.error("Ocurrió un error al buscar registros en USO", e);
            throw new DAOException(e);
        }
        return listaUsos;
    }

    /**
     * Retorna true si existe un registro con cod_usos igual a codigoUso, de lo
     * contrario retorna false.
     * @param codigoUso
     * @return boolean
     * @throws DAOException
     */
    public boolean existeUso(String codigoUso) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_uso, desc_uso FROM USOS WHERE cod_uso = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoUso);
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
            log.error("Ocurrió un error al buscar un registro en USO", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Inserta un resgistro con los valores codigoUso y descripcionUso en la
     * tabla USOS.
     * @param codigoUso
     * @param descripcionUso
     * @return long
     * @throws DAOException
     */
    public long insertarUso(String codigoUso, String descripcionUso) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO USOS(cod_uso,desc_uso) ");
            sql.append("VALUES(?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, StringUtil.putLeftZeros(codigoUso,6));
            pstm.setString(2, descripcionUso);

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
            log.error("Ocurrió un error al insertar un registro en USO", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Actualiza el campo desc_uso con el valor del campo descripcionUso
     * del resgistro con el campo cod_usos = codigoUso de la tabla USOS.
     * @param codigoUso
     * @param descripcionUso
     * @return long
     * @throws DAOException
     */
    public long actualizarUso(String codigoUso, String descripcionUso) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USOS SET desc_uso=? ");
            sql.append("WHERE cod_uso=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, descripcionUso);
            pstm.setString(2, codigoUso);

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
            log.error("Ocurrió un error al actualizar un registro de USO", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Elimina el registro con cod_uso igual a codigoUso.
     * @param codigoUso
     * @param descripcionUso
     * @return long
     * @throws DAOException
     */
    public long eliminarUso(String codigoUso) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM USOS WHERE cod_uso = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoUso);

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
            log.error("Ocurrió un error al eliminar un registro de USO", e);
            throw new DAOException(e);
        }
        return result;
    }
}