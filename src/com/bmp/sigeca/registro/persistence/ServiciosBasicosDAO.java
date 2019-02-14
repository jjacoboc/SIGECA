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
public class ServiciosBasicosDAO extends GenericDAO {

    /** Crea una nueva instancia de ServiciosBasicosDAO */
    public ServiciosBasicosDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarServiciosBasicos(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO SERVICIOS_BASICOS(id_ficha,luz,agua,telefono,desague,nro_sum_luz,nro_contrato_agua,nro_telefono) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setInt(2, (map.get("luz")!=null && "on".equals(map.get("luz").toString()))?1:0);
            pstm.setInt(3, (map.get("agua")!=null && "on".equals(map.get("agua").toString()))?1:0);
            pstm.setInt(4, (map.get("telefono")!=null && "on".equals(map.get("telefono").toString()))?1:0);
            pstm.setInt(5, (map.get("desague")!=null && "on".equals(map.get("desague").toString()))?1:0);
            pstm.setString(6, (String) map.get("numSumLuz"));
            pstm.setString(7, (String) map.get("numConAgua"));
            pstm.setString(8, (String) map.get("numTelefono"));

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

    public int actualizarServiciosBasicos(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SERVICIOS_BASICOS SET luz=?,agua=?,telefono=?,desague=?,nro_sum_luz=?,nro_contrato_agua=?,nro_telefono=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setInt(1, (map.get("luz")!=null && "on".equals(map.get("luz").toString()))?1:0);
            pstm.setInt(2, (map.get("agua")!=null && "on".equals(map.get("agua").toString()))?1:0);
            pstm.setInt(3, (map.get("telefono")!=null && "on".equals(map.get("telefono").toString()))?1:0);
            pstm.setInt(4, (map.get("desague")!=null && "on".equals(map.get("desague").toString()))?1:0);
            pstm.setString(5, (String) map.get("numSumLuz"));
            pstm.setString(6, (String) map.get("numConAgua"));
            pstm.setString(7, (String) map.get("numTelefono"));
            pstm.setString(8, (String) map.get("id_ficha"));

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

    public boolean existeServiciosBasicos(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM SERVICIOS_BASICOS WHERE id_ficha = ? ");

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
