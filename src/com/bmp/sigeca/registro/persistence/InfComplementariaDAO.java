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
public class InfComplementariaDAO extends GenericDAO {

    /** Crea una nueva instancia de InfComplementariaDAO */
    public InfComplementariaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarInfComplementaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codInfComplementaria","TINF_COMPLEMENTARIA");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TINF_COMPLEMENTARIA(codInfComplementaria,numFicha,codConDeclarante,");
            sql.append("codEstLleFicha,numHabitantes,numFamilias,codMantenimiento) ");
            sql.append("VALUES(?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("codConDeclarante"));
            pstm.setString(4,(String)map.get("codEstLleFicha"));
            pstm.setString(5,(String)map.get("numHabitantes"));
            pstm.setString(6,(String)map.get("numFamilias"));
            pstm.setString(7,(String)map.get("codMantenimiento"));

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

    public long actualizarInfComplementaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TINF_COMPLEMENTARIA SET codConDeclarante=?,codEstLleFicha=?,numHabitantes=?,numFamilias=?,codMantenimiento=? ");
            sql.append("WHERE codInfComplementaria=? AND numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codConDeclarante"));
            pstm.setString(2,(String)map.get("codEstLleFicha"));
            pstm.setString(3,(String)map.get("numHabitantes"));
            pstm.setString(4,(String)map.get("numFamilias"));
            pstm.setString(5,(String)map.get("codMantenimiento"));
            pstm.setString(6,(String)map.get("codInfComplementaria"));
            pstm.setString(7,(String)map.get("numFicha"));

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

    public int eliminarInfComplementaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TINF_COMPLEMENTARIA WHERE numFicha = ? ");

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

    public HashMap obtenerInfComplementaria(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap informacion = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT fi.id_ficha,fi.condicion_declarante,fi.estado_llenado,fi.nro_habitantes,fi.nro_familias,fi.mantenimiento ");
            sql.append("FROM fichas_individuales fi, fichas f ");
            sql.append("WHERE fi.id_ficha=f.id_ficha AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                informacion = new HashMap();
                informacion.put("id_ficha", rst.getString(1));
                informacion.put("codConDeclarante", rst.getString(2));
                informacion.put("codEstLleFicha", rst.getString(3));
                informacion.put("numHabitantes", rst.getString(4));
                informacion.put("numFamilias", rst.getString(5));
                informacion.put("codMantenimiento", rst.getString(6));
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
        return informacion;
    }
}