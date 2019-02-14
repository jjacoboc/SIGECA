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
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class DocumentoAdjuntoDAO extends GenericDAO {

    /** Crea una nueva instancia de DocumentoAdjuntoDAO */
    public DocumentoAdjuntoDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarDocumentoAdjunto(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO DOCUMENTOS_ADJUNTOS(id_ficha,tip_doc,nro_doc,fecha_doc,area_autorizada) ");
            sql.append("VALUES(?,?,?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String)map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codTipDocumento"));
            pstm.setString(3, (String)map.get("numDocumento"));
            pstm.setString(4, !"".equals((String)map.get("fecha"))?(String)map.get("fecha"):null);
            pstm.setDouble(5, map.get("area")!=null?Double.parseDouble((String) map.get("area")):0);

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

    public long actualizarDocumentoAdjunto(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE DOCUMENTOS_ADJUNTOS SET fecha_doc=to_date(?,'DD/MM/YYYY HH24:MI:SS'),area_autorizada=? ");
            sql.append("WHERE id_ficha = ? AND tip_doc=? AND nro_doc=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, !"".equals((String)map.get("fecha"))?(String)map.get("fecha"):null);
            pstm.setDouble(2, map.get("area")!=null?Double.parseDouble((String) map.get("area")):0);
            pstm.setString(3, (String)map.get("id_ficha"));
            pstm.setString(4, (String)map.get("codTipDocumento"));
            pstm.setString(5, (String)map.get("numDocumento"));

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

    public boolean existeDocumentoAdjunto(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM DOCUMENTOS_ADJUNTOS WHERE id_ficha = ? AND tip_doc=? AND nro_doc=? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String)map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codTipDocumento"));
            pstm.setString(3, (String)map.get("numDocumento"));

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
