/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TablaDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class NotariaDAO extends GenericDAO {

    /** Crea una nueva instancia de NotariaDAO */
    public NotariaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarNotaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codNotaria","TNOTARIA");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TNOTARIA(codNotaria,numFicha,nomNotaria,kardex,fecEscPublica) ");
            sql.append("VALUES(?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'))");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("nomNotaria"));
            pstm.setString(4,(String)map.get("kardex"));
            pstm.setString(5,(String)map.get("fecEscPublica"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            
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

    public long actualizarNotaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TNOTARIA SET nomNotaria=?,kardex=?,fecEscPublica=str_to_date(?,'%d/%m/%Y %h:%i:%S') ");
            sql.append("WHERE codNotaria=? AND numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("nomNotaria"));
            pstm.setString(2,(String)map.get("kardex"));
            pstm.setString(3,(String)map.get("fecEscPublica"));
            pstm.setString(4,(String)map.get("codNotaria"));
            pstm.setString(5,(String)map.get("numFicha"));

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

    public int eliminarNotaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TNOTARIA WHERE numFicha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm=null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public HashMap obtenerNotaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap notaria = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT rl.id_ficha,rl.id_notaria,rl.kardex,to_char(rl.fecha_escritura,'dd/MM/yyyy') ");
            sql.append("FROM registro_legal rl, fichas f ");
            sql.append("WHERE rl.id_ficha=f.id_ficha AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                notaria = new HashMap();
                notaria.put("id_ficha", rst.getString(1));
                notaria.put("nomNotaria", rst.getString(2));
                notaria.put("kardex", rst.getString(3));
                notaria.put("fecEscPublica", rst.getString(4));
            }

            pstm.close();
            pstm = null;
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
        return notaria;
    }
}
