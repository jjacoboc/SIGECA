/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TablaDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.util.StringUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class BienComunDAO extends GenericDAO {

    /** Crea una nueva instancia de BienComunDAO */
    public BienComunDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarBienComun(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codBienComun","TBIENCOMUN");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TBIENCOMUN(codBienComun,numFicha,porTerLegBienComun,porTerFisBienComun,porConLegBienComun,porConFisBienComun) ");
            sql.append("VALUES(?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3, StringUtil.emptyAsZero((String)map.get("porTerLegBienComun")));
            pstm.setString(4, StringUtil.emptyAsZero((String)map.get("porTerFisBienComun")));
            pstm.setString(5, StringUtil.emptyAsZero((String)map.get("porConLegBienComun")));
            pstm.setString(6, StringUtil.emptyAsZero((String)map.get("porConFisBienComun")));

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

    public int actualizarBienComun(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TBIENCOMUN SET porTerLegBienComun=?,porTerFisBienComun=?,porConLegBienComun=?,porConFisBienComun=? ");
            sql.append("WHERE codBienComun=? AND numFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, StringUtil.emptyAsZero((String)map.get("porTerLegBienComun")));
            pstm.setString(2, StringUtil.emptyAsZero((String)map.get("porTerFisBienComun")));
            pstm.setString(3, StringUtil.emptyAsZero((String)map.get("porConLegBienComun")));
            pstm.setString(4, StringUtil.emptyAsZero((String)map.get("porConFisBienComun")));
            pstm.setString(5,(String)map.get("codBienComun"));
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

    public int eliminarBienComun(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TBIENCOMUN WHERE numFicha = ? ");

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

    public HashMap obtenerBienComun(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap bien = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT f.id_ficha,fi.porc_bc_terr_legal,fi.porc_bc_const_legal,fi.porc_bc_terr_fisico,fi.porc_bc_const_fisico ");
            sql.append("FROM fichas_individuales fi, fichas f ");
            sql.append("WHERE f.id_ficha=fi.id_ficha AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                bien = new HashMap();
                bien.put("id_ficha", rst.getString(1));
                bien.put("porTerLegBienComun", rst.getString(2));
                bien.put("porConLegBienComun", rst.getString(3));
                bien.put("porTerFisBienComun", rst.getString(4));
                bien.put("porConFisBienComun", rst.getString(5));
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
        return bien;
    }
}
