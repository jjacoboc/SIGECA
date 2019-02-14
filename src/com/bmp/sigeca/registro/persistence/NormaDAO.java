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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class NormaDAO extends GenericDAO {

    /** Crea una nueva instancia de NormaDAO */
    public NormaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarNorma(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codNorLegal","TNOR_LEGAL");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TNOR_LEGAL(codNorLegal,codPredio,numNorma,fecNorma,numPlano) ");
            sql.append("VALUES(?,?,?,str_to_date(?,'%d/%m/%Y'),?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("codPredio"));
            pstm.setString(3,(String)map.get("numNorma"));
            pstm.setString(4,(String)map.get("fecNorma"));
            pstm.setString(5,(String)map.get("numPlano"));

            result = pstm.executeUpdate();
            if(result!=0) result=pk;

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

    public long actualizarNorma(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TNOR_LEGAL SET numNorma=?,fecNorma=str_to_date(?,'%d/%m/%Y'),numPlano=? ");
            sql.append("WHERE codNorLegal=? && codPredio=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("numNorma"));
            pstm.setString(2,(String)map.get("fecNorma"));
            pstm.setString(3,(String)map.get("numPlano"));
            pstm.setString(4,(String)map.get("codNorLegal"));
            pstm.setString(5,(String)map.get("codPredio"));

            result = pstm.executeUpdate();

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
        return result;
    }

    public int eliminarNorma(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TNOR_LEGAL WHERE codNorLegal = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codNorLegal"));

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

    public int eliminarNormaByPredio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TNOR_LEGAL WHERE codPredio = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codPredio"));

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

    public List obtenerListaNorma(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap norma = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codNorLegal,codPredio,numNorma,date_format(fecNorma,'%d/%m/%Y'),numPlano ");
            sql.append("FROM TNOR_LEGAL ");
            sql.append("WHERE codPredio = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codPredio"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                norma = new HashMap();
                norma.put("codNorLegal", rst.getString(1));
                norma.put("codPredio", rst.getString(2));
                norma.put("numNorma", rst.getString(3));
                norma.put("fecNorma", rst.getString(4));
                norma.put("numPlano", rst.getString(5));
                lista.add(norma);
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