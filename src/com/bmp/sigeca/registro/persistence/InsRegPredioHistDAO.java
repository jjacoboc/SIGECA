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
public class InsRegPredioHistDAO extends GenericDAO {

    /** Crea una nueva instancia de InsRegPredioHistDAO */
    public InsRegPredioHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarInsRegPredio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codInsRegPredio","TINS_REG_PREDIO_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TINS_REG_PREDIO_HIST(codInsRegPredio,numFicha,codTipParRegistral,numPartida,fojas,");
            sql.append("asiento,fecInsPredio,codDecFabrica,asiInsFabrica,fecInsFabrica,numInscripcion,fecInscripcion,codFicha) ");
            sql.append("VALUES(?,?,?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,");
            sql.append("str_to_date(?,'%d/%m/%Y %h:%i:%S'),?) ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("codTipParRegistral"));
            pstm.setString(4,(String)map.get("numPartida"));
            pstm.setString(5,(String)map.get("fojas"));
            pstm.setString(6,(String)map.get("asiento"));
            pstm.setString(7,(String)map.get("fecInsPredio"));
            pstm.setString(8,(String)map.get("codDecFabrica"));
            pstm.setString(9,(String)map.get("asiInsFabrica"));
            pstm.setString(10,(String)map.get("fecInsFabrica"));
            pstm.setString(11,(String)map.get("numInscripcion"));
            pstm.setString(12,(String)map.get("fecInscripcion"));
            pstm.setString(13,(String)map.get("codFicha"));

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

    public int actualizarInsRegPredio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TINS_REG_PREDIO_HIST SET codTipParRegistral=?,numPartida=?,fojas=?,asiento=?,fecInsPredio=str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("codDecFabrica=?,asiInsFabrica=?,fecInsFabrica=str_to_date(?,'%d/%m/%Y %h:%i:%S'),numInscripcion=?,fecInscripcion=str_to_date(?,'%d/%m/%Y %h:%i:%S') ");
            sql.append("WHERE codInsRegPredio=? AND numFicha=? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codTipParRegistral"));
            pstm.setString(2,(String)map.get("numPartida"));
            pstm.setString(3,(String)map.get("fojas"));
            pstm.setString(4,(String)map.get("asiento"));
            pstm.setString(5,(String)map.get("fecInsPredio"));
            pstm.setString(6,(String)map.get("codDecFabrica"));
            pstm.setString(7,(String)map.get("asiInsFabrica"));
            pstm.setString(8,(String)map.get("fecInsFabrica"));
            pstm.setString(9,(String)map.get("numInscripcion"));
            pstm.setString(10,(String)map.get("fecInscripcion"));
            pstm.setString(11,(String)map.get("codInsRegPredio"));
            pstm.setString(12,(String)map.get("numFicha"));
            pstm.setString(13,(String)map.get("codFicha"));

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

    public HashMap obtenerInsRegPredio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap inscripcion = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codInsRegPredio,numFicha,codTipParRegistral,numPartida,fojas,asiento,date_format(fecInsPredio,'%d/%m/%Y'),");
            sql.append("codDecFabrica,asiInsFabrica,date_format(fecInsFabrica,'%d/%m/%Y'),numInscripcion,date_format(fecInscripcion,'%d/%m/%Y') ");
            sql.append("FROM TINS_REG_PREDIO_HIST ");
            sql.append("WHERE numFicha=? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));
            pstm.setString(2,(String)map.get("codFicha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                inscripcion = new HashMap();
                inscripcion.put("codInsRegPredio", rst.getString(1));
                inscripcion.put("numFicha", rst.getString(2));
                inscripcion.put("codTipParRegistral", rst.getString(3));
                inscripcion.put("numPartida", rst.getString(4));
                inscripcion.put("fojas", rst.getString(5));
                inscripcion.put("asiento", rst.getString(6));
                inscripcion.put("fecInsPredio", rst.getString(7));
                inscripcion.put("codDecFabrica", rst.getString(8));
                inscripcion.put("asiInsFabrica", rst.getString(9));
                inscripcion.put("fecInsFabrica", rst.getString(10));
                inscripcion.put("numInscripcion", rst.getString(11));
                inscripcion.put("fecInscripcion", rst.getString(12));
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
        return inscripcion;
    }
}