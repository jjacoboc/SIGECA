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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class RecapEdificioDAO extends GenericDAO {

    /** Crea una nueva instancia de RecapEdificioDAO */
    public RecapEdificioDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarRecapEdificio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO RECAP_EDIFICIO(id_ficha,edificio,total_porcentaje,total_atc,total_acc,total_aoic) ");
            sql.append("VALUES(?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("id_ficha"));
            pstm.setString(2,(String)map.get("edificio"));
            pstm.setDouble(3, Double.parseDouble((String)map.get("porcentaje")));
            pstm.setDouble(4, Double.parseDouble((String)map.get("atc")));
            pstm.setDouble(5, Double.parseDouble((String)map.get("acc")));
            pstm.setDouble(6, Double.parseDouble((String)map.get("aoic")));

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

    public long actualizarRecapEdificio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE RECAP_EDIFICIO SET edificio=?,total_porcentaje=?,total_atc=?,total_acc=?,total_aoic=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("edificio"));
            pstm.setDouble(2,Double.parseDouble((String)map.get("porcentaje")));
            pstm.setDouble(3,Double.parseDouble((String)map.get("atc")));
            pstm.setDouble(4,Double.parseDouble((String)map.get("acc")));
            pstm.setDouble(5,Double.parseDouble((String)map.get("aoic")));
            pstm.setString(6,(String)map.get("id_ficha"));

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

    public List obtenerListaRecapEdificio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap obra = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT r.id_ficha,r.edificio,r.total_porcentaje,r.total_atc,r.total_acc,r.total_aoic ");
            sql.append("FROM RECAP_EDIFICIO r ");
            sql.append("WHERE r.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                obra = new HashMap();
                obra.put("id_ficha", rst.getString(1));
                obra.put("edificio", rst.getString(2));
                obra.put("porcentaje", rst.getString(3));
                obra.put("atc", rst.getString(4));
                obra.put("acc", rst.getString(5));
                obra.put("aoic", rst.getString(6));
                lista.add(obra);
            }

            pstm.close();
            pstm=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                    pstm=null;
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return lista;
    }

    public boolean existeRecapEdificio(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM RECAP_EDIFICIO WHERE id_ficha = ? ");

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
