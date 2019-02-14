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
public class CotRuralHistDAO extends GenericDAO {

    /** Crea una nueva instancia de CotRuralHistDAO */
    public CotRuralHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarCotRural(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codCotRural","TCOT_RURAL_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TCOT_RURAL_HIST(codCotRural,numFicha,apePaterno,apeMaterno,nombre,codTipDocIdentidad,numDocumento,codEstCivil) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("apePaterno"));
            pstm.setString(4,(String)map.get("apeMaterno"));
            pstm.setString(5,(String)map.get("nombre"));
            pstm.setString(6,(String)map.get("codTipDocIdentidad"));
            pstm.setString(7,(String)map.get("numDocumento"));
            pstm.setString(8,(String)map.get("codEstCivil"));

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

    public long actualizarCotRural(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TCOT_RURAL_HIST SET apePaterno=?,apeMaterno=?,nombre=?,codTipDocIdentidad=?,numDocumento=?,codEstCivil=? ");
            sql.append("WHERE codCotRural=? && numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("apePaterno"));
            pstm.setString(2,(String)map.get("apeMaterno"));
            pstm.setString(3,(String)map.get("nombre"));
            pstm.setString(4,(String)map.get("codTipDocIdentidad"));
            pstm.setString(5,(String)map.get("numDocumento"));
            pstm.setString(6,(String)map.get("codEstCivil"));
            pstm.setString(7,(String)map.get("codCotRural"));
            pstm.setString(8,(String)map.get("numFicha"));

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

    public int eliminarCotRural(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TCOT_RURAL_HIST WHERE codCotRural = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codCotRural"));

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

    public List obtenerListaCotRural(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap cotitular = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT c.codCotRural,c.numFicha,c.apePaterno,c.apeMaterno,c.nombre,c.codTipDocIdentidad,c.numDocumento,c.codEstCivil,");
            sql.append("td.descripcion,ec.descripcion ");
            sql.append("FROM TCOT_RURAL_HIST c, TTIP_DOC_IDENTIDAD td, TEST_CIVIL ec ");
            sql.append("WHERE c.codTipDocIdentidad=td.codTipDocIdentidad AND c.codEstCivil=ec.codEstCivil AND c.numFicha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                cotitular = new HashMap();
                cotitular.put("codCotRural", rst.getString(1));
                cotitular.put("numFicha", rst.getString(2));
                cotitular.put("apePaterno", rst.getString(3));
                cotitular.put("apeMaterno", rst.getString(4));
                cotitular.put("nombre", rst.getString(5));
                cotitular.put("codTipDocIdentidad", rst.getString(6));
                cotitular.put("numDocumento", rst.getString(7));
                cotitular.put("codEstCivil", rst.getString(8));
                cotitular.put("desTipDocIdentidad", rst.getString(9));
                cotitular.put("desEstCivil", rst.getString(10));
                lista.add(cotitular);
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
