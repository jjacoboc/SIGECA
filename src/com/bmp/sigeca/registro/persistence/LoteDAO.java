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
public class LoteDAO extends GenericDAO {

    /** Crea una nueva instancia de LoteDAO */
    public LoteDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarLote(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO LOTES(id_lote,id_mzna,cod_lote,id_hab_urba,mzna_dist,lote_dist,sub_lote_dist,estructuracion,zonificacion,sys_lote,cuc) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_lote"));
            pstm.setString(2, (String) map.get("id_mzna"));
            pstm.setString(3, (String) map.get("lote"));
            pstm.setString(4, (String) map.get("id_hab_urba"));
            pstm.setString(5, (String) map.get("manzanaPre"));
            pstm.setString(6, (String) map.get("lotePre"));
            pstm.setString(7, (String) map.get("sublotePre"));
            pstm.setString(8, (String) map.get("estructuracion"));
            pstm.setString(9, (String) map.get("zonificacion"));
            pstm.setString(10, null);
            pstm.setString(11, (String) map.get("cucA"));

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

    public int actualizarLote(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE LOTES SET id_mzna=?,cod_lote=?,id_hab_urba=?,mzna_dist=?,lote_dist=?,sub_lote_dist=?,estructuracion=?,zonificacion=?,sys_lote=?,cuc=? ");
            sql.append("WHERE id_lote=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_mzna"));
            pstm.setString(2, (String) map.get("lote"));
            pstm.setString(3, (String) map.get("id_hab_urba"));
            pstm.setString(4, (String) map.get("manzanaPre"));
            pstm.setString(5, (String) map.get("lotePre"));
            pstm.setString(6, (String) map.get("sublotePre"));
            pstm.setString(7, (String) map.get("estructuracion"));
            pstm.setString(8, (String) map.get("zonificacion"));
            pstm.setString(9, null);
            pstm.setString(10, (String) map.get("cucA"));
            pstm.setString(11, (String) map.get("id_lote"));

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

    public boolean existeLote(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_lote FROM LOTES WHERE id_lote = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_lote"));

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
