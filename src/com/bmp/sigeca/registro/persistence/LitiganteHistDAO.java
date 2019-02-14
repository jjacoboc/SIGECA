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
public class LitiganteHistDAO extends GenericDAO {

    /** Crea una nueva instancia de LitiganteHistDAO */
    public LitiganteHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarLitigante(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codLitigante","TLITIGANTE_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TLITIGANTE_HIST(codLitigante,codInfComplementaria,numDocumento,nomCompleto,codContribuyente,");
            sql.append("codTipDocIdentidad,codFicha) ");
            sql.append("VALUES(?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("codInfComplementaria"));
            pstm.setString(3,(String)map.get("numDocumento"));
            pstm.setString(4,(String)map.get("nomCompleto"));
            pstm.setString(5,(String)map.get("codContribuyente"));
            pstm.setString(6,(String)map.get("codTipDocumento"));
            pstm.setString(7,(String)map.get("codFicha"));

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

    public long actualizarLitigante(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TLITIGANTE_HIST SET numDocumento=?,nomCompleto=?,codContribuyente=?,codTipDocIdentidad=? ");
            sql.append("WHERE codLitigante=? AND codInfComplementaria=? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("numDocumento"));
            pstm.setString(2,(String)map.get("nomCompleto"));
            pstm.setString(3,(String)map.get("codContribuyente"));
            pstm.setString(4,(String)map.get("codTipDocumento"));
            pstm.setString(5,(String)map.get("codLitigante"));
            pstm.setString(6,(String)map.get("codInfComplementaria"));
            pstm.setString(7,(String)map.get("codFicha"));

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

    public int eliminarLitigante(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TLITIGANTE_HIST WHERE codLitigante = ? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codLitigante"));
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

    public List obtenerLitigante(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap litigante = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT l.codLitigante,l.codInfComplementaria,l.numDocumento,l.nomCompleto,l.codContribuyente,l.codTipDocIdentidad,");
            sql.append("td.descripcion ");
            sql.append("FROM TLITIGANTE_HIST l, TTIP_DOC_IDENTIDAD td ");
            sql.append("WHERE l.codTipDocIdentidad=td.codTipDocIdentidad AND l.codInfComplementaria=? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codInfComplementaria"));
            pstm.setString(2,(String)map.get("codFicha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                litigante = new HashMap();
                litigante.put("codLitigante", rst.getString(1));
                litigante.put("codInfComplementaria", rst.getString(2));
                litigante.put("numDocumento", rst.getString(3));
                litigante.put("nomCompleto", rst.getString(4));
                litigante.put("codContribuyente", rst.getString(5));
                litigante.put("codTipDocumento", rst.getString(6));
                litigante.put("desTipDocumento", rst.getString(7));
                lista.add(litigante);
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
