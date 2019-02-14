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
public class UsosBCDAO extends GenericDAO {

    /** Crea una nueva instancia de UsosBCDAO */
    public UsosBCDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla USOSBC.
     * @return List
     * @throws DAOException
     */
    public List obtenerUsosBC() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap usoBC = null;
        List listaUsosBC = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_uso,desc_uso FROM USOS_BC");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                usoBC = new HashMap();
                usoBC.put("codigoUsoBC", rst.getString(1));
                usoBC.put("descripcionUsoBC", rst.getString(2));
                listaUsosBC.add(usoBC);
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
            log.error("Ocurrió un error al obtener registros de USO_BC", e);
            throw new DAOException(e);
        }
        return listaUsosBC;
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla USOSBC, la consulta busca registros donde cod_uso sea igual a
     * codigoUsoBC o desc_uso contenga descripcionUsoBC.
     * @param codigoUsoBC
     * @param descripcionUsoBC
     * @return List
     * @throws DAOException
     */
    public List buscarUsosBC(String codigoUsoBC, String descripcionUsoBC) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap usoBC = null;
        List listaUsosBC = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_uso, desc_uso FROM USOS_BC WHERE 1=1 ");
            if(codigoUsoBC!=null && !"".equals(codigoUsoBC)){
                sql.append("AND cod_uso = '").append(codigoUsoBC).append("' ");
            }
            if(descripcionUsoBC!=null && !"".equals(descripcionUsoBC)){
                sql.append("AND desc_uso LIKE '%").append(descripcionUsoBC).append("%'");
            }
            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                usoBC = new HashMap();
                usoBC.put("codigoUsoBC", rst.getString(1));
                usoBC.put("descripcionUsoBC", rst.getString(2));
                listaUsosBC.add(usoBC);
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
            log.error("Ocurrió un error al buscar registros en USO_BC", e);
            throw new DAOException(e);
        }
        return listaUsosBC;
    }


    /**
     * Retorna true si existe un registro con cod_uso igual a codigoUsoBC, de lo
     * contrario retorna false.
     * @param codigoUsoBC
     * @return boolean
     * @throws DAOException
     */
    public boolean existeUsoBC(String codigoUsoBC) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT cod_uso, desc_uso FROM USOS_BC WHERE cod_uso = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoUsoBC);
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
            log.error("Ocurrió un error al buscar un registro en USO_BC", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Inserta un resgistro con los valores codigoUsoBC y descripcionUsoBC en la
     * tabla USOS_BC.
     * @param codigoUsoBC
     * @param descripcionUsoBC
     * @return long
     * @throws DAOException
     */
    public long insertarUsoBC(String codigoUsoBC, String descripcionUsoBC) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO USOS_BC(cod_uso,desc_uso) ");
            sql.append("VALUES(?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, StringUtil.putLeftZeros(codigoUsoBC,6));
            pstm.setString(2, descripcionUsoBC);

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
            log.error("Ocurrió un error al insertar un registro en USO_BC", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Actualiza el campo desc_uso con el valor del campo descripcionUsoBC
     * del resgistro con el campo cod_uso = codigoUsoBC de la tabla USOS_BC.
     * @param codigoUsoBC
     * @param descripcionUsoBC
     * @return long
     * @throws DAOException
     */
    public long actualizarUsoBC(String codigoUsoBC, String descripcionUsoBC) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE USOS_BC SET desc_uso=? ");
            sql.append("WHERE cod_uso=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, descripcionUsoBC);
            pstm.setString(2, codigoUsoBC);

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
            log.error("Ocurrió un error al actualizar un registro de USO_BC", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Elimina el registro con cod_uso igual a codigoUsoBC.
     * @param codigoUsoBC
     * @param descripcionUsoBC
     * @return long
     * @throws DAOException
     */
    public long eliminarUsoBC(String codigoUsoBC) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM USOS_BC WHERE cod_uso = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoUsoBC);

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
            log.error("Ocurrió un error al eliminar un registro de USO_BC", e);
            throw new DAOException(e);
        }
        return result;
    }
}