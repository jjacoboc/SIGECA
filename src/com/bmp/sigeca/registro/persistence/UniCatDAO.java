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
public class UniCatDAO extends GenericDAO {

    /** Crea una nueva instancia de UniCatDAO */
    public UniCatDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarUniCat(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO UNI_CAT(id_uni_cat,id_lote,id_edificacion,cod_entrada,cod_piso,cod_unidad,tip_interior,cuc,cuc_antecedente,");
            sql.append("cod_hoja_catastral,cod_cont_rentas,cod_pred_rentas,uni_acum_rentas,nro_interior) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_uni_cat"));
            pstm.setString(2, (String) map.get("id_lote"));
            pstm.setString(3, (String) map.get("id_edificacion"));
            pstm.setString(4, (String) map.get("entrada"));
            pstm.setString(5, (String) map.get("piso"));
            pstm.setString(6, (String) map.get("unidad"));
            pstm.setString(7, (String) map.get("codTipInteriorPre"));
            pstm.setString(8, (String) map.get("cuc"));
            pstm.setString(9, (String) map.get("cuc"));
            pstm.setString(10, (String) map.get("codHojCatastral"));
            pstm.setString(11, (String) map.get("codConRenta"));
            pstm.setString(12, (String) map.get("codPreRenta"));
            pstm.setString(13, (String) map.get("uniAcuCodPreRenta"));
            pstm.setString(14, (String) map.get("numInteriorPre"));

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

    public int actualizarUniCat(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE UNI_CAT SET id_lote=?,id_edificacion=?,cod_entrada=?,cod_piso=?,cod_unidad=?,tip_interior=?,cuc=?,cuc_antecedente=?,");
            sql.append("cod_hoja_catastral=?,cod_cont_rentas=?,cod_pred_rentas=?,uni_acum_rentas=?,nro_interior=? ");
            sql.append("WHERE id_uni_cat=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_lote"));
            pstm.setString(2, (String) map.get("id_edificacion"));
            pstm.setString(3, (String) map.get("entrada"));
            pstm.setString(4, (String) map.get("piso"));
            pstm.setString(5, (String) map.get("unidad"));
            pstm.setString(6, (String) map.get("codTipInteriorPre"));
            pstm.setString(7, (String) map.get("cuc"));
            pstm.setString(8, (String) map.get("cuc"));
            pstm.setString(9, (String) map.get("codHojCatastral"));
            pstm.setString(10, (String) map.get("codConRenta"));
            pstm.setString(11, (String) map.get("codConRenta"));
            pstm.setString(12, (String) map.get("uniAcuCodPreRenta"));
            pstm.setString(13, (String) map.get("numInteriorPre"));
            pstm.setString(14, (String) map.get("id_uni_cat"));

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

    public boolean existeUniCat(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_uni_cat FROM UNI_CAT WHERE id_uni_cat = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_uni_cat"));

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
