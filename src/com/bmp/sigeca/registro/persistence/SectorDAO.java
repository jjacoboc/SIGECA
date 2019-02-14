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
public class SectorDAO extends GenericDAO {

    /** Crea una nueva instancia de SectorDAO */
    public SectorDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarSector(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO SECTORES(id_sector,id_ubi_geo,cod_sector,sys_sector) ");
            sql.append("VALUES(?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_sector"));
            pstm.setString(2, (String) map.get("id_ubi_geo"));
            pstm.setString(3, (String) map.get("sector"));
            pstm.setString(4, (String) map.get("sector"));

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

    public int actualizarSector(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SECTORES SET cod_sector=?,sys_sector=? ");
            sql.append("WHERE id_sector=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("sector"));
            pstm.setString(2, (String) map.get("sector"));
            pstm.setString(3, (String) map.get("id_sector"));

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

    public boolean existeSector(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT id_sector FROM SECTORES WHERE id_sector = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_sector"));

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
