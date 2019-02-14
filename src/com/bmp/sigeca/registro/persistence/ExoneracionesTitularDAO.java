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
public class ExoneracionesTitularDAO extends GenericDAO {

    /** Crea una nueva instancia de ExoneracionesTitularDAO */
    public ExoneracionesTitularDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarExoneracionesTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO EXONERACIONES_TITULAR(id_ficha,id_persona,fecha_vencimiento,fecha_inicio,nro_boleta_pension,nro_resolucion,condicion) ");
            sql.append("VALUES(?,?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));
            pstm.setString(3, !"".equals((String)map.get("fecVenExoneracion"))?(String)map.get("fecVenExoneracion"):null);
            pstm.setString(4, !"".equals((String)map.get("fecIniExoneracion"))?(String)map.get("fecIniExoneracion"):null);
            pstm.setString(5, (String) map.get("numBolPensionista"));
            pstm.setString(6, (String) map.get("numResExoneracion"));
            pstm.setString(7, (String) map.get("codConEspTitular"));

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

    public int actualizarExoneracionesTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE EXONERACIONES_TITULAR SET fecha_vencimiento=to_date(?,'DD/MM/YYYY HH24:MI:SS'),fecha_inicio=to_date(?,'DD/MM/YYYY HH24:MI:SS'),");
            sql.append("nro_boleta_pension=?,nro_resolucion=?,condicion=? ");
            sql.append("WHERE id_ficha=? AND id_persona=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, !"".equals((String)map.get("fecVenExoneracion"))?(String)map.get("fecVenExoneracion"):null);
            pstm.setString(2, !"".equals((String)map.get("fecIniExoneracion"))?(String)map.get("fecIniExoneracion"):null);
            pstm.setString(3, (String) map.get("numBolPensionista"));
            pstm.setString(4, (String) map.get("numResExoneracion"));
            pstm.setString(5, (String) map.get("codConEspTitular"));
            pstm.setString(6, (String) map.get("id_ficha"));
            pstm.setString(7, (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));

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

    public boolean existeExoneracionesTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha, id_persona FROM EXONERACIONES_TITULAR WHERE id_ficha = ? AND id_persona = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String)map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));

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
