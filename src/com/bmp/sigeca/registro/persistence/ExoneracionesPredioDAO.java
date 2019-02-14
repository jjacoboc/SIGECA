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
public class ExoneracionesPredioDAO extends GenericDAO {

    /** Crea una nueva instancia de ExoneracionesPredioDAO */
    public ExoneracionesPredioDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarExoneracionesPredio(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO EXONERACIONES_PREDIO(id_ficha,fecha_vencimiento,fecha_inicio,porcentaje,nro_resolucion,condicion) ");
            sql.append("VALUES(?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, !"".equals((String)map.get("fecVencimiento"))?(String)map.get("fecVencimiento"):null);
            pstm.setString(3, !"".equals((String)map.get("fecInicio"))?(String)map.get("fecInicio"):null);
            pstm.setDouble(4, (map.get("porcentaje")!=null && !"".equals((String)map.get("porcentaje")))?Double.parseDouble((String) map.get("porcentaje")):0);
            pstm.setString(5, (String) map.get("numResExoPredio"));
            pstm.setString(6, (String) map.get("codConEspPredio"));

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

    public int actualizarExoneracionesPredio(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE EXONERACIONES_PREDIO SET fecha_vencimiento=to_date(?,'DD/MM/YYYY HH24:MI:SS'),fecha_inicio=to_date(?,'DD/MM/YYYY HH24:MI:SS'),");
            sql.append("porcentaje=?,nro_resolucion=?,condicion=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, !"".equals((String)map.get("fecVencimiento"))?(String)map.get("fecVencimiento"):null);
            pstm.setString(2, !"".equals((String)map.get("fecInicio"))?(String)map.get("fecInicio"):null);
            pstm.setDouble(3,(map.get("porcentaje")!=null && !"".equals((String)map.get("porcentaje")))?Double.parseDouble((String) map.get("porcentaje")):0);
            pstm.setString(4, (String) map.get("numResExoPredio"));
            pstm.setString(5, (String) map.get("codConEspPredio"));
            pstm.setString(6, (String) map.get("id_ficha"));

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

    public boolean existeExoneracionesPredio(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT id_ficha FROM EXONERACIONES_PREDIO WHERE id_ficha = ? ");

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
