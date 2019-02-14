/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class SysDireccionesDAO extends GenericDAO {

    /** Crea una nueva instancia de SysDireccionesDAO */
    public SysDireccionesDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long getPK() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        long result = 1;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT max(to_number(sys_dir,'9999999999')) as id FROM sys_direcciones");

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

    public int guardarSysDirecciones(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO SYS_DIRECCIONES(sys_dir,referencia,nro_interior,nro_muni,id_via,sub_lote_muni,lote_muni,mzna_muni,id_hab_urba,id_ubi_geo) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("sys_dir"));
            pstm.setString(2, (String) map.get("referencia"));
            pstm.setString(3, (String) map.get("nro_interior"));
            pstm.setString(4, (String) map.get("nro_muni"));
            pstm.setString(5, (String) map.get("id_via"));
            pstm.setString(6, (String) map.get("sub_lote_muni"));
            pstm.setString(7, (String) map.get("lote_muni"));
            pstm.setString(8, (String) map.get("mzna_muni"));
            pstm.setString(9, (String) map.get("id_hab_urba"));
            pstm.setString(10, (String) map.get("id_ubi_geo"));

            result = pstm.executeUpdate();
            pstm.close();
            pstm = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (Exception ignore) {
                }
            }
            throw new DAOException();
        }
        return result;
    }

    public int actualizarSysDirecciones(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SYS_DIRECCIONES SET referencia=?,nro_interior=?,nro_muni=?,id_via=?,sub_lote_muni=?,lote_muni=?,mzna_muni=?,id_hab_urba=?,id_ubi_geo=? ");
            sql.append("WHERE sys_dir=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("referencia"));
            pstm.setString(2, (String) map.get("nro_interior"));
            pstm.setString(3, (String) map.get("nro_muni"));
            pstm.setString(4, (String) map.get("id_via"));
            pstm.setString(5, (String) map.get("sub_lote_muni"));
            pstm.setString(6, (String) map.get("lote_muni"));
            pstm.setString(7, (String) map.get("mzna_muni"));
            pstm.setString(8, (String) map.get("id_hab_urba"));
            pstm.setString(9, (String) map.get("id_ubi_geo"));
            pstm.setString(10, (String) map.get("sys_dir"));

            result = pstm.executeUpdate();
            pstm.close();
            pstm = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (Exception ignore) {
                }
            }
            throw new DAOException();
        }
        return result;
    }

    public boolean existeSysDirecciones(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT sys_dir FROM SYS_DIRECCIONES WHERE sys_dir = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("sys_dir"));

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

}
