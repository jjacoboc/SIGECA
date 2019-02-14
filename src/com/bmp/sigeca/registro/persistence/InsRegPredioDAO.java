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
public class InsRegPredioDAO extends GenericDAO {

    /** Crea una nueva instancia de InsRegPredioDAO */
    public InsRegPredioDAO(TransactionContext context) {
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
            pk = (Long)tablaDAO.getNextPK("codInsRegPredio","TINS_REG_PREDIO");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TINS_REG_PREDIO(codInsRegPredio,numFicha,codTipParRegistral,numPartida,");
            sql.append("fojas,asiento,fecInsPredio,codDecFabrica,asiInsFabrica,fecInsFabrica,numInscripcion,fecInscripcion) ");
            sql.append("VALUES(?,?,?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,str_to_date(?,'%d/%m/%Y %h:%i:%S'))");

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
            sql.append("UPDATE TINS_REG_PREDIO SET codTipParRegistral=?,numPartida=?,fojas=?,asiento=?,fecInsPredio=str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("codDecFabrica=?,asiInsFabrica=?,fecInsFabrica=str_to_date(?,'%d/%m/%Y %h:%i:%S'),numInscripcion=?,fecInscripcion=str_to_date(?,'%d/%m/%Y %h:%i:%S') ");
            sql.append("WHERE codInsRegPredio=? AND numFicha=? ");

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

    public int eliminarInsRegPredio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TINS_REG_PREDIO WHERE numFicha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm=null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
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
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT s.id_ficha,s.tipo_partida,s.nro_partida,s.fojas,s.asiento,to_char(s.fecha_inscripcion,'dd/MM/yyyy'),");
            sql.append("s.cod_decla_fabrica,s.asiento_fabrica,to_char(s.fecha_fabrica,'dd/MM/yyyy') ");
            sql.append("FROM fichas f, sunarp s ");
            sql.append("WHERE f.id_ficha=s.id_ficha AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                inscripcion = new HashMap();
                inscripcion.put("id_ficha", rst.getString(1));
                inscripcion.put("codTipParRegistral", rst.getString(2));
                inscripcion.put("numPartida", rst.getString(3));
                inscripcion.put("fojas", rst.getString(4));
                inscripcion.put("asiento", rst.getString(5));
                inscripcion.put("fecInsPredio", rst.getString(6));
                inscripcion.put("codDecFabrica", rst.getString(7));
                inscripcion.put("asiInsFabrica", rst.getString(8));
                inscripcion.put("fecInsFabrica", rst.getString(9));
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