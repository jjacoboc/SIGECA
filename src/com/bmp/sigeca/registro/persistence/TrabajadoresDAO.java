/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.registro.bean.TrabajadorBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class TrabajadoresDAO extends GenericDAO {

    /** Crea una nueva instancia de TrabajadoresDAO */
    public TrabajadoresDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public List getListTrabajadoresPorCargo(HashMap map) throws DAOException{
        TrabajadorBean trabajadorBean = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_trabajador,nombres,ape_paterno,ape_materno,dni,email ");
            sql.append("FROM TRABAJADORES where id_cargo = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_cargo"));
            
            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                trabajadorBean = new TrabajadorBean();
                trabajadorBean.setId_trabajador(rst.getString(1));
                trabajadorBean.setNombres(rst.getString(2));
                trabajadorBean.setApe_paterno(rst.getString(3));
                trabajadorBean.setApe_materno(rst.getString(4));
                trabajadorBean.setDni(rst.getString(5));
                trabajadorBean.setEmail(rst.getString(6));
                lista.add(trabajadorBean);
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

    public TrabajadorBean getListTrabajadorPorId(String id) throws DAOException{
        TrabajadorBean trabajadorBean = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_trabajador,nombres,ape_paterno,ape_materno,dni,email ");
            sql.append("FROM TRABAJADORES where id_trabajador = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, id);

            rst = pstm.executeQuery();

            while(rst.next()){
                trabajadorBean = new TrabajadorBean();
                trabajadorBean.setId_trabajador(rst.getString(1));
                trabajadorBean.setNombres(rst.getString(2));
                trabajadorBean.setApe_paterno(rst.getString(3));
                trabajadorBean.setApe_materno(rst.getString(4));
                trabajadorBean.setDni(rst.getString(5));
                trabajadorBean.setEmail(rst.getString(6));
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
        return trabajadorBean;
    }
}
