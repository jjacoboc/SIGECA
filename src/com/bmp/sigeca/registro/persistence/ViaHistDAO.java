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
 * @author Administrador
 */
public class ViaHistDAO extends GenericDAO {

    /** Crea una nueva instancia de ViaHistDAO */
    public ViaHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarVia(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TVIA_HIST(codVia,codPredio,codTipVia,nomVia,codTipPuerta,numMunicipal,codConNumeracion,numCerNumeracion,codFicha) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codVia"));
            pstm.setString(2,(String)map.get("codPredio"));
            pstm.setString(3,(String)map.get("codTipVia"));
            pstm.setString(4,(String)map.get("nomVia"));
            pstm.setString(5,(String)map.get("codTipPuerta"));
            pstm.setString(6,(String)map.get("numMunicipal"));
            pstm.setString(7,(String)map.get("codConNumeracion"));
            pstm.setString(8,(String)map.get("numCerNumeracion"));
            pstm.setString(9,(String)map.get("codFicha"));

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

    public int actualizarVia(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TVIA_HIST SET codVia=?,codTipVia=?,nomVia=?,codTipPuerta=?,numMunicipal=?,codConNumeracion=?,numCerNumeracion=? ");
            sql.append("WHERE codPredio=? AND codVia=? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codVia"));
            pstm.setString(2,(String)map.get("codTipVia"));
            pstm.setString(3,(String)map.get("nomVia"));
            pstm.setString(4,(String)map.get("codTipPuerta"));
            pstm.setString(5,(String)map.get("numMunicipal"));
            pstm.setString(6,(String)map.get("codConNumeracion"));
            pstm.setString(7,(String)map.get("numCerNumeracion"));
            pstm.setString(8,(String)map.get("codPredio"));
            pstm.setString(9,(String)map.get("codViaCondicional"));
            pstm.setString(10,(String)map.get("codFicha"));

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

    public int eliminarVia(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TVIA_HIST WHERE codVia = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codVia"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm = null;
        }catch(Exception ex){
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

    public List obtenerListaVia(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap via = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT v.codPredio,v.codVia,v.codTipVia,v.nomVia,v.codTipPuerta,v.numMunicipal,v.codConNumeracion,v.numCerNumeracion,");
            sql.append("tv.descripcion,tp.descripcion ");
            sql.append("FROM TVIA_HIST v, TTIP_VIA tv, TTIP_PUERTA tp ");
            sql.append("WHERE v.codTipVia=tv.codTipVia AND v.codTipPuerta=tp.codTipPuerta AND codPredio=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codPredio"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                via = new HashMap();
                via.put("codPredio", rst.getString(1));
                via.put("codVia", rst.getString(2));
                via.put("codTipVia", rst.getString(3));
                via.put("nomVia", rst.getString(4));
                via.put("codTipPuerta", rst.getString(5));
                via.put("numMunicipal", rst.getString(6));
                via.put("codConNumeracion", rst.getString(7));
                via.put("numCerNumeracion", rst.getString(8));
                via.put("desTipVia", rst.getString(9));
                via.put("desTipPuerta", rst.getString(10));
                lista.add(via);
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
}
