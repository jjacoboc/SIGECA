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
 * @author Administrador
 */
public class ViaHabUrbDAO extends GenericDAO {

    /** Crea una nueva instancia de ViaDAO */
    public ViaHabUrbDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarViaHabUrba(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO VIAS_HAB_URBA(id_via,id_hab_urba) ");
            sql.append("VALUES(?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_via"));
            pstm.setString(2, (String) map.get("id_hab_urba"));

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

    public boolean existeViaHabUrba(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_via FROM VIAS_HAB_URBA WHERE id_via = ? AND id_hab_urba = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_via"));
            pstm.setString(2, (String) map.get("id_hab_urba"));

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

    public boolean existeVia(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_via FROM VIAS_HAB_URBA WHERE id_via = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_via"));

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
