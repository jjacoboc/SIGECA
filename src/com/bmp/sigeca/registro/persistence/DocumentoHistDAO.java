/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TablaDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.resource.Properties;
import com.bmp.sigeca.util.StringUtil;
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
public class DocumentoHistDAO extends GenericDAO {

    /** Crea una nueva instancia de DocumentoHistDAO */
    public DocumentoHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarDocumento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codDocumento","TDOCUMENTO_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TDOCUMENTO_HIST(codDocumento,codFicha,codTipDocumento,");
            sql.append("numDocumento,fecha,areAutorizada,codNotaria,codForPresentacion,nomDocumento) ");
            sql.append("VALUES(?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("codFicha"));
            pstm.setString(3, StringUtil.emptyStringAsNull((String)map.get("codTipDocumento")));
            pstm.setString(4,(String)map.get("numDocumento"));
            pstm.setString(5,(String)map.get("fecha"));
            pstm.setString(6,(String)map.get("areAutorizada"));
            pstm.setString(7,(String)map.get("codNotaria"));
            pstm.setString(8, StringUtil.emptyStringAsNull((String)map.get("codForPresentacion")));
            pstm.setString(9,(String)map.get("nomDocumento"));

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

    public long actualizarDocumento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TDOCUMENTO_HIST SET codTipDocumento=?,numDocumento=?,fecha=str_to_date(?,'%d/%m/%Y %h:%i:%S'),areAutorizada=?,");
            sql.append("codForPresentacion=?,nomDocumento=? ");
            sql.append("WHERE codDocumento=? AND codNotaria=? AND codFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, StringUtil.emptyStringAsNull((String)map.get("codTipDocumento")));
            pstm.setString(2,(String)map.get("numDocumento"));
            pstm.setString(3,(String)map.get("fecha"));
            pstm.setString(4,(String)map.get("areAutorizada"));
            pstm.setString(5, StringUtil.emptyStringAsNull((String)map.get("codForPresentacion")));
            pstm.setString(6,(String)map.get("nomDocumento"));
            pstm.setString(7,(String)map.get("codDocumento"));
            pstm.setString(8,(String)map.get("codNotaria"));
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

    public int eliminarDocumento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TDOCUMENTO_HIST WHERE codDocumento = ? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codDocumento"));
            pstm.setString(2,(String)map.get("codFicha"));

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

    public List obtenerDocumento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap documento = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            String codTipFicha = (String)map.get("codTipFicha");
            if(codTipFicha.equals(Properties.FichaCatastralRural)){
                sql.append("SELECT d.codDocumento,d.codNotaria,d.codFicha,d.codTipDocumento,d.numDocumento,date_format(d.fecha,'%d/%m/%Y'),");
                sql.append("d.areAutorizada,d.codForPresentacion,d.nomDocumento,tf.descripcion ");
                sql.append("FROM TDOCUMENTO_HIST d, TFOR_PRESENTACION tf ");
                sql.append("WHERE d.codForPresentacion=tf.codForPresentacion AND codNotaria=? AND codFicha=? ");
            }else{
                sql.append("SELECT d.codDocumento,d.codNotaria,d.codFicha,d.codTipDocumento,d.numDocumento,date_format(d.fecha,'%d/%m/%Y'),");
                sql.append("d.areAutorizada,d.codForPresentacion,d.nomDocumento,td.descripcion ");
                sql.append("FROM TDOCUMENTO_HIST d, TTIP_DOCUMENTO td ");
                sql.append("WHERE d.codTipDocumento=td.codTipDocumento AND codNotaria=? AND codFicha=? ");
            }
            

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codNotaria"));
            pstm.setString(2,(String)map.get("codFicha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                documento = new HashMap();
                documento.put("codDocumento", rst.getString(1));
                documento.put("codNotaria", rst.getString(2));
                documento.put("codFicha", rst.getString(3));
                documento.put("codTipDocumento", rst.getString(4));
                documento.put("numDocumento", rst.getString(5));
                documento.put("fecha", rst.getString(6));
                documento.put("area", rst.getString(7));
                documento.put("codForPresentacion", rst.getString(8));
                documento.put("nomDocumento", rst.getString(9));
                if(codTipFicha.equals(Properties.FichaCatastralRural)){
                    documento.put("desForPresentacion", rst.getString(10));
                }else{
                    documento.put("desTipDocumento", rst.getString(10));
                }
                lista.add(documento);
            }

            pstm.close();
            pstm=null;
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
        return lista;
    }
}