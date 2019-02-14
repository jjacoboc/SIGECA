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
public class RecapitulacionDAO extends GenericDAO {

    /** Crea una nueva instancia de RecapitulacionDAO */
    public RecapitulacionDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarRecapitulacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codRecapitulacion","TRECAPITULACION");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TRECAPITULACION(codRecapitulacion,numFicha,areTerInvLotColindante,areTerInvJarAislamiento,");
            sql.append("areTerInvArePublica,areTerInvAreIntangible) ");
            sql.append("VALUES(?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("areTerInvLotColindante"));
            pstm.setString(4,(String)map.get("areTerInvJarAislamiento"));
            pstm.setString(5,(String)map.get("areTerInvArePublica"));
            pstm.setString(6,(String)map.get("areTerInvAreIntangible"));

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

    public int actualizarRecapitulacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TRECAPITULACION SET areTerInvLotColindante=?,areTerInvJarAislamiento=?,areTerInvArePublica=?,areTerInvAreIntangible=? ");
            sql.append("WHERE codRecapitulacion=? AND numFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("areTerInvLotColindante"));
            pstm.setString(2,(String)map.get("areTerInvJarAislamiento"));
            pstm.setString(3,(String)map.get("areTerInvArePublica"));
            pstm.setString(4,(String)map.get("areTerInvAreIntangible"));
            pstm.setString(5,(String)map.get("codRecapitulacion"));
            pstm.setString(6,(String)map.get("numFicha"));

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

    public int eliminarRecapitulacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TRECAPITULACION WHERE numFicha = ? ");

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

    public HashMap obtenerRecapitulacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap recapitulacion = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT codRecapitulacion,numFicha,areTerInvLotColindante,areTerInvJarAislamiento,areTerInvArePublica,areTerInvAreIntangible ");
            sql.append("FROM TRECAPITULACION ");
            sql.append("WHERE numFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                recapitulacion = new HashMap();
                recapitulacion.put("codRecapitulacion", rst.getString(1));
                recapitulacion.put("numFicha", rst.getString(2));
                recapitulacion.put("areTerInvLotColindante", rst.getString(3));
                recapitulacion.put("areTerInvJarAislamiento", rst.getString(4));
                recapitulacion.put("areTerInvArePublica", rst.getString(5));
                recapitulacion.put("areTerInvAreIntangible", rst.getString(6));
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
        return recapitulacion;
    }
}
