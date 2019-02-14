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
public class FuncionamientoDAO extends GenericDAO {

    /** Crea una nueva instancia de FuncionamientoDAO */
    public FuncionamientoDAO(TransactionContext context) {
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
            pk = (Long)tablaDAO.getNextPK("codFuncionamiento","TFUNCIONAMIENTO");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TFUNCIONAMIENTO(codFuncionamiento,numFicha,numExpediente,numLicencia,areAutPreCatastral,areAutViaPublica,");
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
            sql.append("UPDATE TFUNCIONAMIENTO SET numExpediente=?,numLicencia=?,areAutPreCatastral=?,areAutViaPublica=?,");
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

    public int eliminarFuncionamiento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TFUNCIONAMIENTO WHERE numFicha = ? ");

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

    public HashMap obtenerFuncionamiento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap funcionamiento = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT fe.id_ficha,fe.nro_expediente,fe.nro_licencia,fe.predio_area_autor,fe.viap_area_autor,fe.bc_area_autor,");
            sql.append("fe.predio_area_verif,fe.viap_area_verif,fe.bc_area_verif,to_char(fe.fecha_expedicion,'dd/MM/yyyy'),");
            sql.append("to_char(fe.fecha_vencimiento,'dd/MM/yyyy'),to_char(fe.inicio_actividad,'dd/MM/yyyy'),fe.condicion_declarante,");
            sql.append("fe.estado_llenado,fe.mantenimiento,fe.nom_comercial,fe.documen_presentado ");
            sql.append("FROM fichas_economicas fe, fichas f ");
            sql.append("WHERE f.id_ficha=fe.id_ficha AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                funcionamiento = new HashMap();
                funcionamiento.put("id_fciha", rst.getString(1));
                funcionamiento.put("numExpediente", rst.getString(2));
                funcionamiento.put("numLicencia", rst.getString(3));
                funcionamiento.put("areAutPreCatastral", rst.getString(4));
                funcionamiento.put("areAutViaPublica", rst.getString(5));
                funcionamiento.put("areAutBienComun", rst.getString(6));
                funcionamiento.put("areVerPreCatastral", rst.getString(7));
                funcionamiento.put("areVerViaPublica", rst.getString(8));
                funcionamiento.put("areVerBienComun", rst.getString(9));
                funcionamiento.put("fecExpAutorizacion", rst.getString(10));
                funcionamiento.put("fecVenAutorizacion", rst.getString(11));
                funcionamiento.put("fecIniActividad", rst.getString(12));
                funcionamiento.put("codConDeclarante", rst.getString(13));
                funcionamiento.put("codEstLleFicha", rst.getString(14));
                funcionamiento.put("codMantenimiento", rst.getString(15));
                funcionamiento.put("nomComercial", rst.getString(16));
                funcionamiento.put("codDocPresentado", rst.getString(17));
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
