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
public class DocumentoDAO extends GenericDAO {

    /** Crea una nueva instancia de DocumentoDAO */
    public DocumentoDAO(TransactionContext context) {
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
            pk = (Long)tablaDAO.getNextPK("codDocumento","TDOCUMENTO");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TDOCUMENTO(codDocumento,numFicha,codTipDocumento,");
            sql.append("numDocumento,fecha,areAutorizada,codNotaria,codForPresentacion,nomDocumento) ");
            sql.append("VALUES(?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3, StringUtil.emptyStringAsNull((String)map.get("codTipDocumento")));
            pstm.setString(4,(String)map.get("numDocumento"));
            pstm.setString(5,(String)map.get("fecha"));
            pstm.setString(6,(String)map.get("area"));
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
            sql.append("UPDATE TDOCUMENTO SET codTipDocumento=?,numDocumento=?,fecha=str_to_date(?,'%d/%m/%Y %h:%i:%S'),areAutorizada=?,");
            sql.append("codForPresentacion=?,nomDocumento=? ");
            sql.append("WHERE codDocumento=? AND codNotaria=? AND numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, StringUtil.emptyStringAsNull((String)map.get("codTipDocumento")));
            pstm.setString(2,(String)map.get("numDocumento"));
            pstm.setString(3,(String)map.get("fecha"));
            pstm.setString(4,(String)map.get("area"));
            pstm.setString(5, StringUtil.emptyStringAsNull((String)map.get("codForPresentacion")));
            pstm.setString(6,(String)map.get("nomDocumento"));
            pstm.setString(7,(String)map.get("codDocumento"));
            pstm.setString(8,(String)map.get("codNotaria"));
            pstm.setString(9,(String)map.get("numFicha"));

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
            sql.append("DELETE FROM TDOCUMENTO WHERE codDocumento = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codDocumento"));

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

    public int eliminarDocumentoByNotaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TDOCUMENTO WHERE codNotaria = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codNotaria"));

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
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT d.id_ficha,d.tip_doc,d.nro_doc,to_char(d.fecha_doc,'dd/MM/yyyy'),d.area_autorizada,tc.desc_codigo ");
            sql.append("FROM documentos_adjuntos d, fichas f, tablas_codigos tc ");
            sql.append("WHERE d.id_ficha=f.id_ficha AND tc.codigo=d.tip_doc AND tc.id_tabla = 'TDC' AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                documento = new HashMap();
                documento.put("id_ficha", rst.getString(1));
                documento.put("codTipDocumento", rst.getString(2));
                documento.put("numDocumento", rst.getString(3));
                documento.put("fecha", rst.getString(4));
                documento.put("area", rst.getString(5));
                documento.put("desTipDocumento", rst.getString(6));
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