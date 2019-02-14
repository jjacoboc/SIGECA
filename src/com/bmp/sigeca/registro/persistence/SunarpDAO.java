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
public class SunarpDAO extends GenericDAO {

    /** Crea una nueva instancia de SunarpDAO */
    public SunarpDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarSunarp(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO SUNARP(id_ficha,tipo_partida,nro_partida,fojas,asiento,fecha_inscripcion,cod_decla_fabrica,asiento_fabrica,fecha_fabrica) ");
            sql.append("VALUES(?,?,?,?,?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,?,to_date(?,'DD/MM/YYYY HH24:MI:SS'))");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("id_ficha"));
            pstm.setString(2,(String)map.get("codTipParRegistral"));
            pstm.setString(3,(String)map.get("numPartida"));
            pstm.setString(4,(String)map.get("fojas"));
            pstm.setString(5,(String)map.get("asiento"));
            pstm.setString(6,!"".equals((String)map.get("fecInsPredio"))?(String)map.get("fecInsPredio"):null);
            pstm.setString(7,(String)map.get("codDecFabrica"));
            pstm.setString(8,(String)map.get("asiInsFabrica"));
            pstm.setString(9,!"".equals((String)map.get("fecInsFabrica"))?(String)map.get("fecInsFabrica"):null);

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

    public long actualizarSunarp(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SUNARP SET tipo_partida=?,nro_partida=?,fojas=?,asiento=?,fecha_inscripcion=to_date(?,'DD/MM/YYYY HH24:MI:SS'),cod_decla_fabrica=?,asiento_fabrica=?,fecha_fabrica=to_date(?,'DD/MM/YYYY HH24:MI:SS') ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codTipParRegistral"));
            pstm.setString(2,(String)map.get("numPartida"));
            pstm.setString(3,(String)map.get("fojas"));
            pstm.setString(4,(String)map.get("asiento"));
            pstm.setString(5,!"".equals((String)map.get("fecInsPredio"))?(String)map.get("fecInsPredio"):null);
            pstm.setString(6,(String)map.get("codDecFabrica"));
            pstm.setString(7,(String)map.get("asiInsFabrica"));
            pstm.setString(8,!"".equals((String)map.get("fecInsFabrica"))?(String)map.get("fecInsFabrica"):null);
            pstm.setString(9,(String)map.get("id_ficha"));

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

    public boolean existeSunarp(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM SUNARP WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String)map.get("id_ficha"));

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
