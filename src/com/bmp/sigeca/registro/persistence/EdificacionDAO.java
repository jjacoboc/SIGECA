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
public class EdificacionDAO extends GenericDAO {

    /** Crea una nueva instancia de EdificacionDAO */
    public EdificacionDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarEdificacion(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO EDIFICACIONES(id_edificacion,id_lote,cod_edificacion,tip_edificacion,nom_edificacion,clasificacion) ");
            sql.append("VALUES(?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_edificacion"));
            pstm.setString(2, (String) map.get("id_lote"));
            pstm.setString(3, (String) map.get("edifica"));
            pstm.setString(4, (String) map.get("codTipEdificacionPre"));
            pstm.setString(5, (String) map.get("nomEdificacionPre"));
            pstm.setString(6, (String) map.get("clasificacion"));

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

    public int actualizarEdificacion(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE EDIFICACIONES SET id_lote=?,cod_edificacion=?,tip_edificacion=?,nom_edificacion=?,clasificacion=? ");
            sql.append("WHERE id_edificacion=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_lote"));
            pstm.setString(2, (String) map.get("edifica"));
            pstm.setString(3, (String) map.get("codTipEdificacionPre"));
            pstm.setString(4, (String) map.get("nomEdificacionPre"));
            pstm.setString(5, (String) map.get("clasificacion"));
            pstm.setString(6, (String) map.get("id_edificacion"));

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

    public boolean existeEdificacion(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT id_edificacion FROM EDIFICACIONES WHERE id_edificacion = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_edificacion"));

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
