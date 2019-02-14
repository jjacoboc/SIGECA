/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.persistence;

import com.bmp.sigeca.maestro.bean.UbigeoBean;
import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
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
public class UbigeoDAO extends GenericDAO {

    /** Crea una nueva instancia de UbigeoDAO */
    public UbigeoDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public List getListDepartamento() throws DAOException{
        UbigeoBean ubigeoBean = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT codDepartamento, codProvincia, codDistrito, descripcion ");
            sql.append("FROM UBIGEOS where 1=1 AND codProvincia = '00' AND codDistrito = '00' ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                ubigeoBean = new UbigeoBean();
                ubigeoBean.setCodDepartamento(rst.getString(1));
                ubigeoBean.setCodProvincia(rst.getString(2));
                ubigeoBean.setCodDistrito(rst.getString(3));
                ubigeoBean.setDescripcion(rst.getString(4));
                lista.add(ubigeoBean);
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
        return lista;
    }

    public List getListProvincia(HashMap map) throws DAOException{
        UbigeoBean ubigeoBean = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT codDepartamento, codProvincia, codDistrito, descripcion ");
            sql.append("FROM UBIGEOS where codDepartamento = ? AND codProvincia <> '00' AND codDistrito = '00' ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1,(String)map.get("codDepartamento"));
            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                ubigeoBean = new UbigeoBean();
                ubigeoBean.setCodDepartamento(rst.getString(1));
                ubigeoBean.setCodProvincia(rst.getString(2));
                ubigeoBean.setCodDistrito(rst.getString(3));
                ubigeoBean.setDescripcion(rst.getString(4));
                lista.add(ubigeoBean);
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
        return lista;
    }

    public List getListDistrito(HashMap map) throws DAOException{
        UbigeoBean ubigeoBean = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT codDepartamento, codProvincia, codDistrito, descripcion ");
            sql.append("FROM UBIGEOS where codDepartamento = ? AND codProvincia = ? AND codDistrito <> '00' ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1,(String)map.get("codDepartamento"));
            pstm.setString(2,(String)map.get("codProvincia"));
            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                ubigeoBean = new UbigeoBean();
                ubigeoBean.setCodDepartamento(rst.getString(1));
                ubigeoBean.setCodProvincia(rst.getString(2));
                ubigeoBean.setCodDistrito(rst.getString(3));
                ubigeoBean.setDescripcion(rst.getString(4));
                lista.add(ubigeoBean);
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
        return lista;
    }

    public boolean existeUbigeo(HashMap map) throws DAOException{
        boolean existe = false;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT codDepartamento, codProvincia, codDistrito ");
            sql.append("FROM UBIGEOS WHERE codDepartamento = ? AND codProvincia = ? AND codDistrito = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1,(String)map.get("codDepartamento"));
            pstm.setString(2,(String)map.get("codProvincia"));
            pstm.setString(3,(String)map.get("codDistrito"));

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