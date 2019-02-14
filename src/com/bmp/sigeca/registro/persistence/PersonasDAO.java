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
public class PersonasDAO extends GenericDAO {

    /** Crea una nueva instancia de PersonasDAO */
    public PersonasDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarPersona(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO PERSONAS(id_persona,nro_doc,tip_doc,tip_persona,nombres,ape_materno,ape_paterno,tip_persona_juridica) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_persona"));
            pstm.setString(2, (String) map.get("nro_doc"));
            pstm.setString(3, (String) map.get("tip_doc"));
            pstm.setString(4, (String) map.get("tip_persona"));
            pstm.setString(5, (String) map.get("nombres"));
            pstm.setString(6, (String) map.get("ape_materno"));
            pstm.setString(7, (String) map.get("ape_paterno"));
            pstm.setString(8, (String) map.get("tip_persona_juridica"));

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

    public int actualizarPersona(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PERSONAS SET nro_doc=?,tip_doc=?,tip_persona=?,nombres=?,ape_materno=?,ape_paterno=?,tip_persona_juridica=? ");
            sql.append("WHERE id_persona=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            
            pstm.setString(1, (String) map.get("nro_doc"));
            pstm.setString(2, (String) map.get("tip_doc"));
            pstm.setString(3, (String) map.get("tip_persona"));
            pstm.setString(4, (String) map.get("nombres"));
            pstm.setString(5, (String) map.get("ape_materno"));
            pstm.setString(6, (String) map.get("ape_paterno"));
            pstm.setString(7, (String) map.get("tip_persona_juridica"));
            pstm.setString(8, (String) map.get("id_persona"));

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

    public HashMap obtenerPersona(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_persona,nro_doc,tip_doc,tip_persona,nombres,ape_materno,ape_paterno,tip_persona_juridica ");
            sql.append("FROM personas ");
            sql.append("WHERE id_persona = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_persona"));

            rst = pstm.executeQuery();

            if(rst.next()){
                titular = new HashMap();
                titular.put("id_persona", rst.getString(1));
                titular.put("numDocIdentidad", rst.getString(2));
                titular.put("codTipDocIdentidad", rst.getString(3));
                titular.put("codTipTitular", rst.getString(4));
                titular.put("nombre", rst.getString(5));
                titular.put("apePaterno", rst.getString(6));
                titular.put("apeMaterno", rst.getString(7));
                titular.put("numRuc", rst.getString(2));
                titular.put("razSocial", rst.getString(5));
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
        return titular;
    }

    public boolean existePersona(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_persona FROM PERSONAS WHERE id_persona = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_persona"));

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
