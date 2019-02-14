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
public class LinderosDAO extends GenericDAO {

    /** Crea una nueva instancia de LinderosDAO */
    public LinderosDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarLinderos(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO LINDEROS(id_ficha,frente_campo,frente_titulo,frente_colinda_campo,frente_colinda_titulo,der_campo,der_titulo,der_colinda_campo,der_colinda_titulo,");
            sql.append("izq_campo,izq_titulo,izq_colinda_campo,izq_colinda_titulo,fondo_campo,fondo_titulo,fondo_colinda_campo,fondo_colinda_titulo) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String) map.get("medCamFrente"));
            pstm.setString(3, (String) map.get("medTitFrente"));
            pstm.setString(4, (String) map.get("colCamFrente"));
            pstm.setString(5, (String) map.get("colTitFrente"));
            pstm.setString(6, (String) map.get("medCamDerecha"));
            pstm.setString(7, (String) map.get("medTitDerecha"));
            pstm.setString(8, (String) map.get("colCamDerecha"));
            pstm.setString(9, (String) map.get("colTitDerecha"));
            pstm.setString(10, (String) map.get("medCamIzquierda"));
            pstm.setString(11, (String) map.get("medTitIzquierda"));
            pstm.setString(12, (String) map.get("colCamIzquierda"));
            pstm.setString(13, (String) map.get("colTitIzquierda"));
            pstm.setString(14, (String) map.get("medCamFondo"));
            pstm.setString(15, (String) map.get("medTitFondo"));
            pstm.setString(16, (String) map.get("colCamFondo"));
            pstm.setString(17, (String) map.get("colTitFondo"));

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

    public int actualizarLinderos(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE LINDEROS SET frente_campo=?,frente_titulo=?,frente_colinda_campo=?,frente_colinda_titulo=?,der_campo=?,der_titulo=?,der_colinda_campo=?,der_colinda_titulo=?,");
            sql.append("izq_campo=?,izq_titulo=?,izq_colinda_campo=?,izq_colinda_titulo=?,fondo_campo=?,fondo_titulo=?,fondo_colinda_campo=?,fondo_colinda_titulo=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("medCamFrente"));
            pstm.setString(2, (String) map.get("medTitFrente"));
            pstm.setString(3, (String) map.get("colCamFrente"));
            pstm.setString(4, (String) map.get("colTitFrente"));
            pstm.setString(5, (String) map.get("medCamDerecha"));
            pstm.setString(6, (String) map.get("medTitDerecha"));
            pstm.setString(7, (String) map.get("colCamDerecha"));
            pstm.setString(8, (String) map.get("colTitDerecha"));
            pstm.setString(9, (String) map.get("medCamIzquierda"));
            pstm.setString(10, (String) map.get("medTitIzquierda"));
            pstm.setString(11, (String) map.get("colCamIzquierda"));
            pstm.setString(12, (String) map.get("colTitIzquierda"));
            pstm.setString(13, (String) map.get("medCamFondo"));
            pstm.setString(14, (String) map.get("medTitFondo"));
            pstm.setString(15, (String) map.get("colCamFondo"));
            pstm.setString(16, (String) map.get("colTitFondo"));
            pstm.setString(17, (String) map.get("id_ficha"));

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

    public boolean existeLinderos(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM LINDEROS WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String)map.get("id_ficha"));

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
