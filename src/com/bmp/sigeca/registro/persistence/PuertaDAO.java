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
public class PuertaDAO extends GenericDAO {

    /** Crea una nueva instancia de PuertaDAO */
    public PuertaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarPuerta(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO PUERTAS(id_puerta,cod_puerta,nro_muni,tip_puerta,id_lote,condicion_nro,id_via,nro_certificacion) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_puerta"));
            pstm.setString(2, (String) map.get("cod_puerta"));
            pstm.setString(3, (String) map.get("nro_muni"));
            pstm.setString(4, (String) map.get("tip_puerta"));
            pstm.setString(5, (String) map.get("id_lote"));
            pstm.setString(6, (String) map.get("condicion_nro"));
            pstm.setString(7, (String) map.get("id_via"));
            pstm.setString(8, (String) map.get("nro_certificacion"));

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

    public int actualizarPuerta(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PUERTAS SET cod_puerta=?,nro_muni=?,tip_puerta=?,id_lote=?,condicion_nro=?,id_via=?,nro_certificacion=? ");
            sql.append("WHERE id_puerta=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("cod_puerta"));
            pstm.setString(2, (String) map.get("nro_muni"));
            pstm.setString(3, (String) map.get("tip_puerta"));
            pstm.setString(4, (String) map.get("id_lote"));
            pstm.setString(5, (String) map.get("condicion_nro"));
            pstm.setString(6, (String) map.get("id_via"));
            pstm.setString(7, (String) map.get("nro_certificacion"));
            pstm.setString(8, (String) map.get("id_puerta"));

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

    public boolean existePuerta(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_puerta FROM PUERTAS WHERE id_puerta = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_puerta"));

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
