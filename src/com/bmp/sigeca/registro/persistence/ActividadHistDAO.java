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
public class ActividadHistDAO extends GenericDAO {

    /** Crea una nueva instancia de ActividadHistDAO */
    public ActividadHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarActividad(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codTActividad","TACTIVIDAD_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TACTIVIDAD_HIST(codTActividad,codFuncionamiento,codActividad,desActividad) ");
            sql.append("VALUES(?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("codFuncionamiento"));
            pstm.setString(3,(String)map.get("codActividad"));
            pstm.setString(4,(String)map.get("desActividad"));

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

    public long actualizarActividad(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TACTIVIDAD_HIST SET codActividad=?,desActividad=? ");
            sql.append("WHERE codTActividad=? AND codFuncionamiento=?");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codActividad"));
            pstm.setString(2,(String)map.get("desActividad"));
            pstm.setString(3,(String)map.get("codTActividad"));
            pstm.setString(4,(String)map.get("codFuncionamiento"));

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

    public int eliminarActividad(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TACTIVIDAD_HIST WHERE codTActividad = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codTActividad"));

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

    public List obtenerActividad(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap actividad = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codTActividad,codFuncionamiento,codActividad,desActividad ");
            sql.append("FROM TACTIVIDAD_HIST ");
            sql.append("WHERE codFuncionamiento=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codFuncionamiento"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                actividad = new HashMap();
                actividad.put("codTActividad", rst.getString(1));
                actividad.put("codFuncionamiento", rst.getString(2));
                actividad.put("codActividad", rst.getString(3));
                actividad.put("desActividad", rst.getString(4));
                lista.add(actividad);
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
