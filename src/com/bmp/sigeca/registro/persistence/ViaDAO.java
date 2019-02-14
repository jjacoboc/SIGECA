/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.resource.Properties;
import com.bmp.sigeca.util.StringUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class ViaDAO extends GenericDAO {

    /** Crea una nueva instancia de ViaDAO */
    public ViaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarVia(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO VIAS(id_via,nom_via,tip_via,cod_via,id_ubi_geo,id_sys_via) ");
            sql.append("VALUES(?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_via"));
            pstm.setString(2, (String) map.get("nomVia"));
            pstm.setString(3, (String) map.get("codTipVia"));
            pstm.setString(4, (String) map.get("codVia"));
            pstm.setString(5, (String) map.get("id_ubi_geo"));
            pstm.setString(6, StringUtil.nullAsEmptyString((String) map.get("id_sys_via")));

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

    public int actualizarVia(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE VIAS SET nom_via=?,tip_via=?,cod_via=?,id_ubi_geo=?,id_sys_via=? ");
            sql.append("WHERE id_via=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("nomVia"));
            pstm.setString(2, (String) map.get("codTipVia"));
            pstm.setString(3, (String) map.get("codVia"));
            pstm.setString(4, (String) map.get("id_ubi_geo"));
            pstm.setString(5, StringUtil.nullAsEmptyString((String) map.get("id_sys_via")));
            pstm.setString(6, (String) map.get("id_via"));

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

    public boolean existeVia(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_via FROM VIAS WHERE id_via = ? ");

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

    public int eliminarVia(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TVIA WHERE codVia = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String) map.get("codVia"));

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

    public int eliminarViaByPredio(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TVIA WHERE codPredio = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String) map.get("codPredio"));

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

    public List obtenerListaVia(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap via = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT f.id_ficha,v.cod_via,v.tip_via,v.nom_via,p.tip_puerta,p.nro_muni,p.condicion_nro,p.nro_certificacion,");
            sql.append("tc.desc_codigo ");
            sql.append("FROM puertas p, vias v, fichas f, uni_cat u, lotes l, tablas_codigos tc ");
            sql.append("WHERE p.id_via=v.id_via AND p.id_lote=l.id_lote AND l.id_lote=u.id_lote AND u.id_uni_cat=f.id_uni_cat ");
            sql.append("AND v.tip_via=tc.codigo AND tc.id_tabla = '").append(Properties.TIPO_VIA).append("' ");
            sql.append("AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String) map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while (rst.next()) {
                via = new HashMap();
                via.put("id_ficha", rst.getString(1));
                via.put("codVia", rst.getString(2));
                via.put("codTipVia", rst.getString(3));
                via.put("nomVia", rst.getString(4));
                via.put("codTipPuerta", rst.getString(5));
                via.put("numMunicipal", rst.getString(6));
                via.put("codConNumeracion", rst.getString(7));
                via.put("numCerNumeracion", rst.getString(8));
                via.put("desTipVia", rst.getString(9));
                //via.put("desTipPuerta", rst.getString(10));
                lista.add(via);
            }

            pstm.close();
            pstm = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                    pstm = null;
                } catch (Exception ignore) {
                }
            }
            throw new DAOException();
        }
        return lista;
    }
}
