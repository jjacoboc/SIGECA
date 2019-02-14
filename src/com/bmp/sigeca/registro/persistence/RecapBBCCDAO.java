/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.util.StringUtil;
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
public class RecapBBCCDAO extends GenericDAO {

    /** Crea una nueva instancia de RecapBBCCDAO */
    public RecapBBCCDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarRecapBBCC(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO RECAP_BBCC(id_ficha,edifica,entrada,piso,unidad,porcentaje,atc,acc,aoic) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("id_ficha"));
            pstm.setString(2,(String)map.get("edificacion"));
            pstm.setString(3,(String)map.get("entrada"));
            pstm.setString(4,(String)map.get("piso"));
            pstm.setString(5,(String)map.get("unidad"));
            pstm.setDouble(6,Double.parseDouble(StringUtil.emptyAsZero((String)map.get("porcentaje"))));
            pstm.setDouble(7,Double.parseDouble(StringUtil.emptyAsZero((String)map.get("atc"))));
            pstm.setDouble(8,Double.parseDouble(StringUtil.emptyAsZero((String)map.get("acc"))));
            pstm.setDouble(9,Double.parseDouble(StringUtil.emptyAsZero((String)map.get("aoic"))));

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

    public long actualizarRecapBBCC(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE RECAP_BBCC SET edifica=?,entrada=?,piso=?,unidad=?,porcentaje=?,atc=?,acc=?,aoic=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("edificacion"));
            pstm.setString(2,(String)map.get("entrada"));
            pstm.setString(3,(String)map.get("piso"));
            pstm.setString(4,(String)map.get("unidad"));
            pstm.setDouble(5,Double.parseDouble(StringUtil.emptyAsZero((String)map.get("porcentaje"))));
            pstm.setDouble(6,Double.parseDouble(StringUtil.emptyAsZero((String)map.get("atc"))));
            pstm.setDouble(7,Double.parseDouble(StringUtil.emptyAsZero((String)map.get("acc"))));
            pstm.setDouble(8,Double.parseDouble(StringUtil.emptyAsZero((String)map.get("aoic"))));
            pstm.setString(9,(String)map.get("id_ficha"));

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

    public List obtenerListaRecapBBCC(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap obra = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT r.id_ficha,r.edifica,r.entrada,r.piso,r.unidad,r.porcentaje,r.atc,r.acc,r.aoic ");
            sql.append("FROM RECAP_BBCC r ");
            sql.append("WHERE r.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                obra = new HashMap();
                obra.put("id_ficha", rst.getString(1));
                obra.put("edificacion", rst.getString(2));
                obra.put("entrada", rst.getString(3));
                obra.put("piso", rst.getString(4));
                obra.put("unidad", rst.getString(5));
                obra.put("porcentaje", rst.getString(6));
                obra.put("atc", rst.getString(7));
                obra.put("acc", rst.getString(8));
                obra.put("aoic", rst.getString(9));
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

    public boolean existeRecapBBCC(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM RECAP_BBCC WHERE id_ficha = ? ");

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
