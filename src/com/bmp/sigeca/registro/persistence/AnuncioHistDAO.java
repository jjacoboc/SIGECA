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
public class AnuncioHistDAO extends GenericDAO {

    /** Crea una nueva instancia de AnuncioHistDAO */
    public AnuncioHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarAnuncio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codAnuncio","TANUNCIO_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TANUNCIO_HIST(codAnuncio,numFicha,codTipAnuncio,desTipAnuncio,numLados,areAutorizada,areVerificada,");
            sql.append("numExpAnuncio,numLicAnuncio,fecExpedicion,fecVencimiento) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,str_to_date(?,'%d/%m/%Y'),str_to_date(?,'%d/%m/%Y'))");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("codTipAnuncio"));
            pstm.setString(4,(String)map.get("desTipAnuncio"));
            pstm.setString(5,(String)map.get("numLados"));
            pstm.setString(6,(String)map.get("areAutorizada"));
            pstm.setString(7,(String)map.get("areVerificada"));
            pstm.setString(8,(String)map.get("numExpAnuncio"));
            pstm.setString(9,(String)map.get("numLicAnuncio"));
            pstm.setString(10,(String)map.get("fecExpedicion"));
            pstm.setString(11,(String)map.get("fecVencimiento"));

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

    public long actualizarAnuncio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TANUNCIO_HIST SET codTipAnuncio=?,desTipAnuncio=?,numLados=?,areAutorizada=?,areVerificada=?,");
            sql.append("numExpAnuncio=?,numLicAnuncio=?,fecExpedicion=str_to_date(?,'%d/%m/%Y'),fecVencimiento=str_to_date(?,'%d/%m/%Y') ");
            sql.append("WHERE codAnuncio=? && numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codTipAnuncio"));
            pstm.setString(2,(String)map.get("desTipAnuncio"));
            pstm.setString(3,(String)map.get("numLados"));
            pstm.setString(4,(String)map.get("areAutorizada"));
            pstm.setString(5,(String)map.get("areVerificada"));
            pstm.setString(6,(String)map.get("numExpAnuncio"));
            pstm.setString(7,(String)map.get("numLicAnuncio"));
            pstm.setString(8,(String)map.get("fecExpedicion"));
            pstm.setString(9,(String)map.get("fecVencimiento"));
            pstm.setString(10,(String)map.get("codAnuncio"));
            pstm.setString(11,(String)map.get("numFicha"));

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

    public int eliminarAnuncio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TANUNCIO_HIST WHERE codAnuncio = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codAnuncio"));

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

    public List obtenerListaAnuncio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap anuncio = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codAnuncio,numFicha,codTipAnuncio,desTipAnuncio,numLados,areAutorizada,areVerificada,");
            sql.append("numExpAnuncio,numLicAnuncio,date_format(fecExpedicion,'%d/%m/%Y'),date_format(fecVencimiento,'%d/%m/%Y') ");
            sql.append("FROM TANUNCIO_HIST ");
            sql.append("WHERE numFicha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                anuncio = new HashMap();
                anuncio.put("codAnuncio", rst.getString(1));
                anuncio.put("numFicha", rst.getString(2));
                anuncio.put("codTipAnuncio", rst.getString(3));
                anuncio.put("desTipAnuncio", rst.getString(4));
                anuncio.put("numLados", rst.getString(5));
                anuncio.put("areAutorizada", rst.getString(6));
                anuncio.put("areVerificada", rst.getString(7));
                anuncio.put("numExpAnuncio", rst.getString(8));
                anuncio.put("numLicAnuncio", rst.getString(9));
                anuncio.put("fecExpedicion", rst.getString(10));
                anuncio.put("fecVencimiento", rst.getString(11));
                lista.add(anuncio);
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
