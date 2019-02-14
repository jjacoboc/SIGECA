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
public class ObrComplementariaDAO extends GenericDAO {

    /** Crea una nueva instancia de ObrComplementariaDAO */
    public ObrComplementariaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarObraComplementaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codObrComplementaria","TOBR_COMPLEMENTARIA");

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TOBR_COMPLEMENTARIA(codObrComplementaria,numFicha,codInstalacion,desInstalacion,fecConstruccion,");
            sql.append("codMEP,codECS,codECC,largo,ancho,alto,proTotal,uniMedida,codUCA) ");
            sql.append("VALUES(?,?,?,?,str_to_date(?,'%d/%m/%Y'),?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("codInstalacion"));
            pstm.setString(4,(String)map.get("desInstalacion"));
            pstm.setString(5,(String)map.get("fecConstruccion"));
            pstm.setString(6,(String)map.get("codMEP"));
            pstm.setString(7,(String)map.get("codECS"));
            pstm.setString(8,(String)map.get("codECC"));
            pstm.setString(9,(String)map.get("largo"));
            pstm.setString(10,(String)map.get("ancho"));
            pstm.setString(11,(String)map.get("alto"));
            pstm.setString(12,(String)map.get("total"));
            pstm.setString(13,(String)map.get("uniMedida"));
            pstm.setString(14,(String)map.get("codUCA"));

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

    public long actualizarObraComplementaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TOBR_COMPLEMENTARIA SET codInstalacion=?,desInstalacion=?,fecConstruccion=str_to_date(?,'%d/%m/%Y'),");
            sql.append("codMEP=?,codECS=?,codECC=?,largo=?,ancho=?,alto=?,proTotal=?,uniMedida=?,codUCA=? ");
            sql.append("WHERE codObrComplementaria=? && numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            
            pstm.setString(1,(String)map.get("codInstalacion"));
            pstm.setString(2,(String)map.get("desInstalacion"));
            pstm.setString(3,(String)map.get("fecConstruccion"));
            pstm.setString(4,(String)map.get("codMEP"));
            pstm.setString(5,(String)map.get("codECS"));
            pstm.setString(6,(String)map.get("codECC"));
            pstm.setString(7,(String)map.get("largo"));
            pstm.setString(8,(String)map.get("ancho"));
            pstm.setString(9,(String)map.get("alto"));
            pstm.setString(10,(String)map.get("total"));
            pstm.setString(11,(String)map.get("uniMedida"));
            pstm.setString(12,(String)map.get("codUCA"));
            pstm.setString(13,(String)map.get("codObrComplementaria"));
            pstm.setString(14,(String)map.get("numFicha"));

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

    public int eliminarObraComplementaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TOBR_COMPLEMENTARIA WHERE codObrComplementaria = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codObrComplementaria"));

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

    public int eliminarObraComplementariaByFicha(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TOBR_COMPLEMENTARIA WHERE numFicha = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

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

    public List obtenerListaObraComplementaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap obra = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codObrComplementaria,numFicha,codInstalacion,desInstalacion,date_format(fecConstruccion,'%d/%m/%Y'),");
            sql.append("codMEP,codECS,codECC,largo,ancho,alto,proTotal,uniMedida,codUCA ");
            sql.append("FROM TOBR_COMPLEMENTARIA ");
            sql.append("WHERE numFicha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                obra = new HashMap();
                obra.put("codObrComplementaria", rst.getString(1));
                obra.put("numFicha", rst.getString(2));
                obra.put("codInstalacion", rst.getString(3));
                obra.put("desInstalacion", rst.getString(4));
                obra.put("fecConstruccion", rst.getString(5));
                obra.put("codMEP", rst.getString(6));
                obra.put("codECS", rst.getString(7));
                obra.put("codECC", rst.getString(8));
                obra.put("largo", rst.getString(9));
                obra.put("ancho", rst.getString(10));
                obra.put("alto", rst.getString(11));
                obra.put("total", rst.getString(12));
                obra.put("uniMedida", rst.getString(13));
                obra.put("codUCA", rst.getString(14));
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
}