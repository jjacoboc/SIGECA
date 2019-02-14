/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.util.StringUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class InstalacionDAO extends GenericDAO {

    /** Crea una nueva instancia de InstalacionDAO */
    public InstalacionDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarInstalacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO INSTALACIONES(id_ficha,cod_instalacion,fecha,mep,ecs,ecc,dimension_largo,dimension_ancho,dimension_alto,producto_total,uca,nro_registro) ");
            sql.append("VALUES(?,?,to_date(?,'dd/MM/yyyy'),?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String)map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codInstalacion"));
            pstm.setString(3, !"".equals((String)map.get("fecConstruccion"))?(String)map.get("fecConstruccion"):null);
            pstm.setString(4, StringUtil.emptyStringAsNull((String)map.get("codMEP")));
            pstm.setString(5, StringUtil.emptyStringAsNull((String)map.get("codECS")));
            pstm.setString(6, StringUtil.emptyStringAsNull((String)map.get("codECC")));
            pstm.setDouble(7, (map.get("largo")!=null && !"".equals((String)map.get("largo")))?Double.parseDouble(((String)map.get("largo")).trim()):0);
            pstm.setDouble(8, (map.get("ancho")!=null && !"".equals((String)map.get("ancho")))?Double.parseDouble(((String)map.get("ancho")).trim()):0);
            pstm.setDouble(9, (map.get("alto")!=null && !"".equals((String)map.get("alto")))?Double.parseDouble(((String)map.get("alto")).trim()):0);
            pstm.setDouble(10, (map.get("total")!=null && !"".equals((String)map.get("total")))?Double.parseDouble(((String)map.get("total")).trim()):0);
            pstm.setString(11, StringUtil.emptyStringAsNull((String)map.get("codUCA")));
            pstm.setInt(12, (map.get("nro_registro")!=null && !"".equals(((String)map.get("nro_registro")).trim()))?Integer.parseInt(((String)map.get("nro_registro")).trim()):0);

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

    public long actualizarInstalacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE INSTALACIONES SET fecha=to_date(?,'dd/MM/yyyy'),mep=?,ecs=?,ecc=?,dimension_largo=?,dimension_ancho=?,dimension_alto=?,producto_total=?,uca=?,nro_registro=? ");
            sql.append("WHERE id_ficha=? AND cod_instalacion=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, !"".equals((String)map.get("fecConstruccion"))?(String)map.get("fecConstruccion"):null);
            pstm.setString(2, StringUtil.emptyStringAsNull((String)map.get("codMEP")));
            pstm.setString(3, StringUtil.emptyStringAsNull((String)map.get("codECS")));
            pstm.setString(4, StringUtil.emptyStringAsNull((String)map.get("codECC")));
            pstm.setDouble(5, (map.get("largo")!=null && !"".equals((String)map.get("largo")))?Double.parseDouble(((String)map.get("largo")).trim()):0);
            pstm.setDouble(6, (map.get("ancho")!=null && !"".equals((String)map.get("ancho")))?Double.parseDouble(((String)map.get("ancho")).trim()):0);
            pstm.setDouble(7, (map.get("alto")!=null && !"".equals((String)map.get("alto")))?Double.parseDouble(((String)map.get("alto")).trim()):0);
            pstm.setDouble(8, (map.get("total")!=null && !"".equals((String)map.get("total")))?Double.parseDouble(((String)map.get("total")).trim()):0);
            pstm.setString(9, StringUtil.emptyStringAsNull((String)map.get("codUCA")));
            pstm.setInt(10, (map.get("nro_registro")!=null && !"".equals(((String)map.get("nro_registro")).trim()))?Integer.parseInt(((String)map.get("nro_registro")).trim()):0);
            pstm.setString(11, (String)map.get("id_ficha"));
            pstm.setString(12, (String)map.get("codInstalacion"));

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

    public List obtenerListaInstalacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap obra = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT i.id_ficha,i.cod_instalacion,ci.desc_instalacion,ci.material,to_char(i.fecha,'dd/MM/yyyy'),");
            sql.append("i.mep,i.ecs,i.ecc,i.dimension_largo,i.dimension_ancho,i.dimension_alto,i.producto_total,i.uca ");
            sql.append("FROM INSTALACIONES i, CODIGOS_INSTALACIONES ci ");
            sql.append("WHERE i.cod_instalacion=ci.cod_instalacion AND i.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                obra = new HashMap();
                obra.put("id_ficha", rst.getString(1));
                obra.put("codInstalacion", rst.getString(2));
                obra.put("desInstalacion", rst.getString(3)+" | "+rst.getString(4));
                obra.put("fecConstruccion", rst.getString(5));
                obra.put("codMEP", rst.getString(6));
                obra.put("codECS", rst.getString(7));
                obra.put("codECC", rst.getString(8));
                obra.put("largo", rst.getString(9));
                obra.put("ancho", rst.getString(10));
                obra.put("alto", rst.getString(11));
                obra.put("total", rst.getString(12));
                obra.put("codUCA", rst.getString(13));
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

    public boolean existeInstalacion(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM INSTALACIONES WHERE id_ficha = ? AND cod_instalacion=? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String)map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codInstalacion"));

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
