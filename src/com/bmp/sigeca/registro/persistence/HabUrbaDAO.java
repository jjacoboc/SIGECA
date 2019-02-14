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
public class HabUrbaDAO extends GenericDAO {

    /** Crea una nueva instancia de HabUrbaDAO */
    public HabUrbaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarHabilitacionUrbana(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO HAB_URBA(id_hab_urba,grupo_urba,nom_hab_urba,tip_hab_urba,cod_hab_urba,id_ubi_geo) ");
            sql.append("VALUES(?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_hab_urba"));
            pstm.setString(2, (String) map.get("grupo_urba"));
            pstm.setString(3, (String) map.get("nom_hab_urba"));
            pstm.setString(4, (String) map.get("tip_hab_urba"));
            pstm.setString(5, (String) map.get("cod_hab_urba"));
            pstm.setString(6, (String) map.get("id_ubi_geo"));

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

    public int actualizarHabilitacionUrbana(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE HAB_URBA SET grupo_urba=?,nom_hab_urba=?,tip_hab_urba=?,cod_hab_urba=?,id_ubi_geo=? ");
            sql.append("WHERE id_hab_urba=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("grupo_urba"));
            pstm.setString(2, (String) map.get("nom_hab_urb"));
            pstm.setString(3, (String) map.get("tip_hab_urba"));
            pstm.setString(4, (String) map.get("cod_hab_urba"));
            pstm.setString(5, (String) map.get("id_ubi_geo"));
            pstm.setString(6, (String) map.get("id_hab_urba"));

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

    public boolean existeHabilitacionUrbana(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_hab_urba FROM HAB_URBA WHERE id_hab_urba = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_hab_urba"));

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
