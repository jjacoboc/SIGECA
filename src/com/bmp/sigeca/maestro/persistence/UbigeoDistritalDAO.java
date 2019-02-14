/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.persistence;

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
public class UbigeoDistritalDAO extends GenericDAO {

    /** Crea una nueva instancia de UbigeoDistritalDAO */
    public UbigeoDistritalDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public HashMap getUbigeoDistrital() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap mapa = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT id_ubi_geo, nom_ubi_geo, cuc_desde, cuc_hasta, ultimo_cuc ");
            sql.append("FROM UBIGEO WHERE 1=1 ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();

            mapa = new HashMap();
            while(rst.next()){
                mapa.put("id_ubi_geo",rst.getString(1));
                mapa.put("nom_ubi_geo",rst.getString(2));
                mapa.put("cuc_desde",rst.getString(3));
                mapa.put("cuc_hasta",rst.getString(4));
                mapa.put("ultimo_cuc",rst.getString(5));
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
            throw new DAOException(ex);
        }
        return mapa;
    }

    public boolean existeUbigeoDistrital(HashMap map) throws DAOException{
        boolean existe = false;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT id_ubi_geo ");
            sql.append("FROM UBIGEO WHERE id_ubi_geo = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1,(String)map.get("codDepartamento")+(String)map.get("codProvincia")+(String)map.get("codDistrito"));

            rst = pstm.executeQuery();

            if(rst.next()){
              existe = true;
            }

            pstm.close();
            pstm = null;
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
            throw new DAOException(ex);
        }
        return existe;
    }

}
