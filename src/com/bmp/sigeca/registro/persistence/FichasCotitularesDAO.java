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
public class FichasCotitularesDAO extends GenericDAO {

    /** Crea una nueva instancia de FichasCotitularesDAO */
    public FichasCotitularesDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarFichaCotitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("INSERT INTO FICHAS_COTITULARIDADES(id_ficha,condicion_declarante,estado_llenado,observaciones) ");
            sql.append("VALUES(?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String) map.get("codConDeclarante"));
            pstm.setString(3, (String) map.get("codEstLleFicha"));
            pstm.setString(4, (String) map.get("observacion"));

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

    public int actualizarFichaCotitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("UPDATE FICHAS_COTITULARIDADES SET condicion_declarante=?,estado_llenado=?,observaciones=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("codConDeclarante"));
            pstm.setString(2, (String) map.get("codEstLleFicha"));
            pstm.setString(3, (String) map.get("observacion"));
            pstm.setString(4, (String) map.get("id_ficha"));

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

    public HashMap obtenerFichaCotitular(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ficha = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT DISTINCT condicion_declarante, estado_llenado, observaciones ");
            sql.append("FROM FICHAS_COTITULARIDADES ");
            sql.append("WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                ficha = new HashMap();
                ficha.put("codConDeclarante", rst.getString(1));
                ficha.put("codEstLleFicha", rst.getString(2));
                ficha.put("observacion", rst.getString(3));
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return ficha;
    }

    public boolean existeFichaCotitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM FICHAS_COTITULARIDADES WHERE id_ficha = ? ");

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
