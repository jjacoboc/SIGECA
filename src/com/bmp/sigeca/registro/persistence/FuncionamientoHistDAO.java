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
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class FuncionamientoHistDAO extends GenericDAO {

    /** Crea una nueva instancia de FuncionamientoHistDAO */
    public FuncionamientoHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarFuncionamiento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codFuncionamiento","TFUNCIONAMIENTO_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TFUNCIONAMIENTO_HIST(codFuncionamiento,numFicha,numExpediente,numLicencia,areAutPreCatastral,areAutViaPublica,");
            sql.append("areAutBienComun,areAutTotal,areVerPreCatastral,areVerViaPublica,areVerBienComun,areVerTotal,fecExpAutorizacion,");
            sql.append("fecVenAutorizacion,fecIniActividad) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,str_to_date(?,'%d/%m/%Y'),str_to_date(?,'%d/%m/%Y'),str_to_date(?,'%d/%m/%Y'))");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("numExpediente"));
            pstm.setString(4,(String)map.get("numLicencia"));
            pstm.setString(5,(String)map.get("areAutPreCatastral"));
            pstm.setString(6,(String)map.get("areAutViaPublica"));
            pstm.setString(7,(String)map.get("areAutBienComun"));
            pstm.setString(8,(String)map.get("areAutTotal"));
            pstm.setString(9,(String)map.get("areVerPreCatastral"));
            pstm.setString(10,(String)map.get("areVerViaPublica"));
            pstm.setString(11,(String)map.get("areVerBienComun"));
            pstm.setString(12,(String)map.get("areVerTotal"));
            pstm.setString(13,(String)map.get("fecExpAutorizacion"));
            pstm.setString(14,(String)map.get("fecVenAutorizacion"));
            pstm.setString(15,(String)map.get("fecIniActividad"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;

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

    public int actualizarFuncionamiento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TFUNCIONAMIENTO_HIST SET numExpediente=?,numLicencia=?,areAutPreCatastral=?,areAutViaPublica=?,");
            sql.append("areAutBienComun=?,areAutTotal=?,areVerPreCatastral=?,areVerViaPublica=?,areVerBienComun=?,areVerTotal=?,");
            sql.append("fecExpAutorizacion=str_to_date(?,'%d/%m/%Y'),fecVenAutorizacion=str_to_date(?,'%d/%m/%Y'),fecIniActividad=str_to_date(?,'%d/%m/%Y') ");
            sql.append("WHERE codFuncionamiento=? AND numFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("numExpediente"));
            pstm.setString(2,(String)map.get("numLicencia"));
            pstm.setString(3,(String)map.get("areAutPreCatastral"));
            pstm.setString(4,(String)map.get("areAutViaPublica"));
            pstm.setString(5,(String)map.get("areAutBienComun"));
            pstm.setString(6,(String)map.get("areAutTotal"));
            pstm.setString(7,(String)map.get("areVerPreCatastral"));
            pstm.setString(8,(String)map.get("areVerViaPublica"));
            pstm.setString(9,(String)map.get("areVerBienComun"));
            pstm.setString(10,(String)map.get("areVerTotal"));
            pstm.setString(11,(String)map.get("fecExpAutorizacion"));
            pstm.setString(12,(String)map.get("fecVenAutorizacion"));
            pstm.setString(13,(String)map.get("fecIniActividad"));
            pstm.setString(14,(String)map.get("codFuncionamiento"));
            pstm.setString(15,(String)map.get("numFicha"));

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

    public HashMap obtenerFuncionamiento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap funcionamiento = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codFuncionamiento,numFicha,numExpediente,numLicencia,areAutPreCatastral,areAutViaPublica,areAutBienComun,");
            sql.append("areAutTotal,areVerPreCatastral,areVerViaPublica,areVerBienComun,areVerTotal,date_format(fecExpAutorizacion,'%d/%m/%Y'),");
            sql.append("date_format(fecVenAutorizacion,'%d/%m/%Y'),date_format(fecIniActividad,'%d/%m/%Y') ");
            sql.append("FROM TFUNCIONAMIENTO_HIST ");
            sql.append("WHERE numFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                funcionamiento = new HashMap();
                funcionamiento.put("codFuncionamiento", rst.getString(1));
                funcionamiento.put("numFicha", rst.getString(2));
                funcionamiento.put("numExpediente", rst.getString(3));
                funcionamiento.put("numLicencia", rst.getString(4));
                funcionamiento.put("areAutPreCatastral", rst.getString(5));
                funcionamiento.put("areAutViaPublica", rst.getString(6));
                funcionamiento.put("areAutBienComun", rst.getString(7));
                funcionamiento.put("areAutTotal", rst.getString(8));
                funcionamiento.put("areVerPreCatastral", rst.getString(9));
                funcionamiento.put("areVerViaPublica", rst.getString(10));
                funcionamiento.put("areVerBienComun", rst.getString(11));
                funcionamiento.put("areVerTotal", rst.getString(12));
                funcionamiento.put("fecExpAutorizacion", rst.getString(13));
                funcionamiento.put("fecVenAutorizacion", rst.getString(14));
                funcionamiento.put("fecIniActividad", rst.getString(15));
            }

            pstm.close();
            pstm = null;
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
        return funcionamiento;
    }
}
