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
public class NotariaHistDAO extends GenericDAO {

    /** Crea una nueva instancia de NotariaHistDAO */
    public NotariaHistDAO(TransactionContext context) {
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
            pk = (Long)tablaDAO.getNextPK("codNotaria","TNOTARIA_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TNOTARIA_HIST(codNotaria,numFicha,nomNotaria,kardex,fecEscPublica,codFicha) ");
            sql.append("VALUES(?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("nomNotaria"));
            pstm.setString(4,(String)map.get("kardex"));
            pstm.setString(5,(String)map.get("fecEscPublica"));
            pstm.setString(6,(String)map.get("codFicha"));

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
            sql.append("UPDATE TNOTARIA_HIST SET nomNotaria=?,kardex=?,fecEscPublica=str_to_date(?,'%d/%m/%Y %h:%i:%S') ");
            sql.append("WHERE codNotaria=? AND numFicha=? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("nomNotaria"));
            pstm.setString(2,(String)map.get("kardex"));
            pstm.setString(3,(String)map.get("fecEscPublica"));
            pstm.setString(4,(String)map.get("codNotaria"));
            pstm.setString(5,(String)map.get("numFicha"));
            pstm.setString(6,(String)map.get("codFicha"));

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

    public HashMap obtenerNotaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap notaria = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codNotaria,numFicha,nomNotaria,kardex,date_format(fecEscPublica,'%d/%m/%Y') ");
            sql.append("FROM TNOTARIA_HIST ");
            sql.append("WHERE numFicha=? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));
            pstm.setString(2,(String)map.get("codFicha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                notaria = new HashMap();
                notaria.put("codNotaria", rst.getString(1));
                notaria.put("numFicha", rst.getString(2));
                notaria.put("nomNotaria", rst.getString(3));
                notaria.put("kardex", rst.getString(4));
                notaria.put("fecEscPublica", rst.getString(5));
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
