/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.maestro.bean.TablaBean;
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
public class TablaDAO extends GenericDAO{

    /** Crea una nueva instancia de TablaDAO */
    public TablaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public List getListaTablas() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        TablaBean tabla = null;
        List lista = null;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codTabla,nomFisico,descripcion,comentario FROM TTABLA ");
            sql.append("ORDER BY descripcion ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            rst = pstm.executeQuery();
            lista = new ArrayList();
            while(rst.next()){
                tabla = new TablaBean();
                tabla.setCodTabla(rst.getString(1));
                tabla.setNomFisico(rst.getString(2));
                tabla.setDescripcion(rst.getString(3));
                tabla.setComentario(rst.getString(4));
                lista.add(tabla);
            }
            
            pstm.close();
            pstm = null;
            rst.close();
            rst = null;
        }catch(Exception ex){
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

    public int registrarMaestro(StringBuffer sql) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            pstm = context.getConnection().prepareStatement(sql.toString());

            result = pstm.executeUpdate();

            pstm.close();
            pstm = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    public int actualizarMaestro(StringBuffer sql) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            pstm = context.getConnection().prepareStatement(sql.toString());

            result = pstm.executeUpdate();

            pstm.close();
            pstm = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    public int eliminarMaestro(StringBuffer sql) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            pstm = context.getConnection().prepareStatement(sql.toString());

            result = pstm.executeUpdate();

            pstm.close();
            pstm = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    public int getMaxPrimaryKey(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT ").append("MAX(").append((String)map.get("pk")).append(") ").append(" FROM ").append((String)map.get("tabla"));

            System.out.println(sql.toString());
            pstm = context.getConnection().prepareStatement(sql.toString());

            rst = pstm.executeQuery();
            if(rst.next()){
                result = Integer.parseInt(rst.getString(1));
            }

            pstm.close();
            pstm = null;
            rst.close();
            rst = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return result;
    }
}
