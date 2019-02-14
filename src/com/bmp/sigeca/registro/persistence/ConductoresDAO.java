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
public class ConductoresDAO extends GenericDAO {

    /** Crea una nueva instancia de ConductoresDAO */
    public ConductoresDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarConductor(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO CONDUCTORES(id_ficha,id_persona,fax,telefono,anexo,correo_elect,condicion_conductor) ");
            sql.append("VALUES(?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("id_ficha"));
            pstm.setString(2,(String)map.get("id_persona"));
            pstm.setString(3,(String)map.get("fax"));
            pstm.setString(4,(String)map.get("telefono"));
            pstm.setString(5,(String)map.get("anexo"));
            pstm.setString(6,(String)map.get("correo_elect"));
            pstm.setString(7,(String)map.get("condicion_conductor"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public long actualizarConductor(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CONDUCTORES SET id_persona=?,fax=?,telefono=?,anexo=?,correo_elect=?,condicion_conductor=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("id_persona"));
            pstm.setString(2,(String)map.get("fax"));
            pstm.setString(3,(String)map.get("telefono"));
            pstm.setString(4,(String)map.get("anexo"));
            pstm.setString(5,(String)map.get("correo_elect"));
            pstm.setString(6,(String)map.get("condicion_conductor"));
            pstm.setString(7,(String)map.get("id_ficha"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public HashMap obtenerConductor(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT c.id_ficha,c.id_persona,c.fax,c.telefono,c.anexo,c.correo_elect,c.condicion_conductor ");
            sql.append("FROM fichas f, conductores c ");
            sql.append("WHERE c.id_ficha=f.id_ficha AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                titular = new HashMap();
                titular.put("id_ficha", rst.getString(1));
                titular.put("id_persona", rst.getString(2));
                titular.put("fax", rst.getString(3));
                titular.put("telefono", rst.getString(4));
                titular.put("anexo", rst.getString(5));
                titular.put("corElectronico", rst.getString(6));
                titular.put("codConConductor", rst.getString(7));
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

    public boolean existeConductor(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM CONDUCTORES WHERE id_ficha = ? ");

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
