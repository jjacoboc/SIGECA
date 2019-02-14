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
public class FichasBienesComunesDAO extends GenericDAO {

    /** Crea una nueva instancia de FichasBienesComunesDAO */
    public FichasBienesComunesDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarFichaBienComun(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO FICHAS_BIENES_COMUNES(id_ficha,en_colindante,en_jardin_aislamiento,en_area_publica,contenido_en,en_area_intangible,estado_llenado,");
            sql.append("area_verificada,area_declarada,area_titulo,mantenimiento,observaciones,condicion_declarante,cod_uso,clasificacion) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setDouble(2, (map.get("areTerInvLotColindante")!=null && !"".equals((String)map.get("areTerInvLotColindante")))?Double.parseDouble((String) map.get("areTerInvLotColindante")):0);
            pstm.setDouble(3, (map.get("areTerInvJarAislamiento")!=null && !"".equals((String)map.get("areTerInvJarAislamiento")))?Double.parseDouble((String) map.get("areTerInvJarAislamiento")):0);
            pstm.setDouble(4, (map.get("areTerInvArePublica")!=null && !"".equals((String)map.get("areTerInvArePublica")))?Double.parseDouble((String) map.get("areTerInvArePublica")):0);
            pstm.setString(5, (String) map.get("codUbiPreCatastral"));
            pstm.setDouble(6, (map.get("areTerInvAreIntangible")!=null && !"".equals((String)map.get("areTerInvAreIntangible")))?Double.parseDouble((String) map.get("areTerInvAreIntangible")):0);
            pstm.setString(7, (String) map.get("codEstLleFicha"));
            pstm.setDouble(8, (map.get("areTerVerificada")!=null && !"".equals((String)map.get("areTerVerificada")))?Double.parseDouble((String) map.get("areTerVerificada")):0);
            pstm.setDouble(9, (map.get("areTerDeclarada")!=null && !"".equals((String)map.get("areTerDeclarada")))?Double.parseDouble((String) map.get("areTerDeclarada")):0);
            pstm.setDouble(10, (map.get("areTerTitulo")!=null && !"".equals((String)map.get("areTerTitulo")))?Double.parseDouble((String) map.get("areTerTitulo")):0);
            pstm.setString(11, (String) map.get("codMantenimiento"));
            pstm.setString(12, (String) map.get("observacion"));
            pstm.setString(13, (String) map.get("codConDeclarante"));
            pstm.setString(14, (String) map.get("codUsoPreCatastral"));
            pstm.setString(15, (String) map.get("codClaPredio"));

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

    public int actualizarFichaBienComun(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE FICHAS_BIENES_COMUNES SET en_colindante=?,en_jardin_aislamiento=?,en_area_publica=?,contenido_en=?,en_area_intangible=?,estado_llenado=?,");
            sql.append("area_verificada=?,area_declarada=?,area_titulo=?,mantenimiento=?,observaciones=?,condicion_declarante=?,cod_uso=?,clasificacion=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setDouble(1, (map.get("areTerInvLotColindante")!=null && !"".equals((String)map.get("areTerInvLotColindante")))?Double.parseDouble((String) map.get("areTerInvLotColindante")):0);
            pstm.setDouble(2, (map.get("areTerInvJarAislamiento")!=null && !"".equals((String)map.get("areTerInvJarAislamiento")))?Double.parseDouble((String) map.get("areTerInvJarAislamiento")):0);
            pstm.setDouble(3, (map.get("areTerInvArePublica")!=null && !"".equals((String)map.get("areTerInvArePublica")))?Double.parseDouble((String) map.get("areTerInvArePublica")):0);
            pstm.setString(4, (String) map.get("codUbiPreCatastral"));
            pstm.setDouble(5, (map.get("areTerInvAreIntangible")!=null && !"".equals((String)map.get("areTerInvAreIntangible")))?Double.parseDouble((String) map.get("areTerInvAreIntangible")):0);
            pstm.setString(6, (String) map.get("codEstLleFicha"));
            pstm.setDouble(7, (map.get("areTerVerificada")!=null && !"".equals((String)map.get("areTerVerificada")))?Double.parseDouble((String) map.get("areTerVerificada")):0);
            pstm.setDouble(8, (map.get("areTerDeclarada")!=null && !"".equals((String)map.get("areTerDeclarada")))?Double.parseDouble((String) map.get("areTerDeclarada")):0);
            pstm.setDouble(9, (map.get("areTerTitulo")!=null && !"".equals((String)map.get("areTerTitulo")))?Double.parseDouble((String) map.get("areTerTitulo")):0);
            pstm.setString(10, (String) map.get("codMantenimiento"));
            pstm.setString(11, (String) map.get("observacion"));
            pstm.setString(12, (String) map.get("codConDeclarante"));
            pstm.setString(13, (String) map.get("codUsoPreCatastral"));
            pstm.setString(14, (String) map.get("codClaPredio"));
            pstm.setString(15, (String) map.get("id_ficha"));

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

    public boolean existeFichaBienComun(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM FICHAS_BIENES_COMUNES WHERE id_ficha = ? ");

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
