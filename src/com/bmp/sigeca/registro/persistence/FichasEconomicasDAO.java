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
public class FichasEconomicasDAO extends GenericDAO {

    /** Crea una nueva instancia de FichasEconomicasDAO */
    public FichasEconomicasDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarFichaActividadEconomica(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("INSERT INTO FICHAS_ECONOMICAS(id_ficha,nom_comercial,predio_area_autor,predio_area_verif,");
            sql.append("viap_area_autor,viap_area_verif,bc_area_autor,bc_area_verif,nro_expediente,nro_licencia,");
            sql.append("fecha_expedicion,fecha_vencimiento,inicio_actividad,condicion_declarante,estado_llenado,");
            sql.append("mantenimiento,documen_presentado,observaciones) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),to_date(?,'DD/MM/YYYY HH24:MI:SS'),");
            sql.append("to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String) map.get("nomComercial"));
            pstm.setDouble(3, (map.get("areAutPreCatastral")!=null && !"".equals((String)map.get("areAutPreCatastral")))?Double.parseDouble(((String)map.get("areAutPreCatastral")).trim()):0);
            pstm.setDouble(4, (map.get("areVerPreCatastral")!=null && !"".equals((String)map.get("areVerPreCatastral")))?Double.parseDouble(((String)map.get("areVerPreCatastral")).trim()):0);
            pstm.setDouble(5, (map.get("areAutViaPublica")!=null && !"".equals((String)map.get("areAutViaPublica")))?Double.parseDouble(((String)map.get("areAutViaPublica")).trim()):0);
            pstm.setDouble(6, (map.get("areVerViaPublica")!=null && !"".equals((String)map.get("areVerViaPublica")))?Double.parseDouble(((String)map.get("areVerViaPublica")).trim()):0);
            pstm.setDouble(7, (map.get("areAutBienComun")!=null && !"".equals((String)map.get("areAutBienComun")))?Double.parseDouble(((String)map.get("areAutBienComun")).trim()):0);
            pstm.setDouble(8, (map.get("areVerBienComun")!=null && !"".equals((String)map.get("areVerBienComun")))?Double.parseDouble(((String)map.get("areVerBienComun")).trim()):0);
            pstm.setString(9, (String) map.get("numExpediente"));
            pstm.setString(10, (String) map.get("numLicencia"));
            pstm.setString(11, (String) map.get("fecExpAutorizacion"));
            pstm.setString(12, (String) map.get("fecVenAutorizacion"));
            pstm.setString(13, (String) map.get("fecIniActividad"));
            pstm.setString(14, (String) map.get("codConDeclarante"));
            pstm.setString(15, (String) map.get("codEstLleFicha"));
            pstm.setString(16, (String) map.get("codMantenimiento"));
            pstm.setString(17, (String) map.get("codDocPresentado"));
            pstm.setString(18, (String) map.get("observacion"));

            result = pstm.executeUpdate();
            pstm.close();
            pstm = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (Exception ignore) {
                }
            }
            throw new DAOException();
        }
        return result;
    }

    public int actualizarFichaActividadEconomica(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("UPDATE FICHAS_ECONOMICAS SET nom_comercial=?,predio_area_autor=?,predio_area_verif=?,");
            sql.append("viap_area_autor=?,viap_area_verif=?,bc_area_autor=?,bc_area_verif=?,nro_expediente=?,nro_licencia=?,");
            sql.append("fecha_expedicion=to_date(?,'DD/MM/YYYY HH24:MI:SS'),fecha_vencimiento=to_date(?,'DD/MM/YYYY HH24:MI:SS'),");
            sql.append("inicio_actividad=to_date(?,'DD/MM/YYYY HH24:MI:SS'),condicion_declarante=?,estado_llenado=?,");
            sql.append("mantenimiento=?,documen_presentado=?,observaciones=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("nomComercial"));
            pstm.setDouble(2, (map.get("areAutPreCatastral")!=null && !"".equals((String)map.get("areAutPreCatastral")))?Double.parseDouble(((String)map.get("areAutPreCatastral")).trim()):0);
            pstm.setDouble(3, (map.get("areVerPreCatastral")!=null && !"".equals((String)map.get("areVerPreCatastral")))?Double.parseDouble(((String)map.get("areVerPreCatastral")).trim()):0);
            pstm.setDouble(4, (map.get("areAutViaPublica")!=null && !"".equals((String)map.get("areAutViaPublica")))?Double.parseDouble(((String)map.get("areAutViaPublica")).trim()):0);
            pstm.setDouble(5, (map.get("areVerViaPublica")!=null && !"".equals((String)map.get("areVerViaPublica")))?Double.parseDouble(((String)map.get("areVerViaPublica")).trim()):0);
            pstm.setDouble(6, (map.get("areAutBienComun")!=null && !"".equals((String)map.get("areAutBienComun")))?Double.parseDouble(((String)map.get("areAutBienComun")).trim()):0);
            pstm.setDouble(7, (map.get("areVerBienComun")!=null && !"".equals((String)map.get("areVerBienComun")))?Double.parseDouble(((String)map.get("areVerBienComun")).trim()):0);
            pstm.setString(8, (String) map.get("numExpediente"));
            pstm.setString(9, (String) map.get("numLicencia"));
            pstm.setString(10, (String) map.get("fecExpAutorizacion"));
            pstm.setString(11, (String) map.get("fecVenAutorizacion"));
            pstm.setString(12, (String) map.get("fecIniActividad"));
            pstm.setString(13, (String) map.get("codConDeclarante"));
            pstm.setString(14, (String) map.get("codEstLleFicha"));
            pstm.setString(15, (String) map.get("codMantenimiento"));
            pstm.setString(16, (String) map.get("codDocPresentado"));
            pstm.setString(17, (String) map.get("observacion"));
            pstm.setString(18, (String) map.get("id_ficha"));

            result = pstm.executeUpdate();
            pstm.close();
            pstm = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (Exception ignore) {
                }
            }
            throw new DAOException();
        }
        return result;
    }

    public HashMap obtenerFichaEconomica(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ficha = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT DISTINCT nom_comercial, observaciones ");
            sql.append("FROM FICHAS_ECONOMICAS ");
            sql.append("WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                ficha = new HashMap();
                ficha.put("nomComercial", rst.getString(1));
                ficha.put("observacion", rst.getString(2));
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
        return ficha;
    }

    public boolean existeFichaActividadEconomica(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM FICHAS_ECONOMICAS WHERE id_ficha = ? ");

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
