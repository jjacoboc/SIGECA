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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class FichasDAO extends GenericDAO {

    /** Crea una nueva instancia de FichasDAO */
    public FichasDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarFichas(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO FICHAS(id_ficha,tip_ficha,nro_ficha,id_lote,dc,nro_ficha_lote,activo,firma_declarante,declarante,");
            sql.append("fecha_levantamiento,supervisor,fecha_supervision,tecnico,fecha_tecnico,verificador,fecha_verificacion,id_uni_cat,");
            sql.append("fecha_ingreso,usuario_ingreso,id_estado) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,");
            sql.append("to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,current_timestamp,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String) map.get("codTipFicha"));
            pstm.setString(3, (String) map.get("numFicha"));
            pstm.setString(4, (String) map.get("id_lote"));
            pstm.setString(5, (String) map.get("dc"));
            pstm.setString(6, (String) map.get("numFichLote"));
            pstm.setInt(7, Integer.parseInt((String) map.get("activo")));
            pstm.setInt(8, Integer.parseInt((String) map.get("firma_declarante")));
            pstm.setString(9, (String) map.get("declarante"));
            pstm.setString(10, !"".equals((String)map.get("fecFirDeclarante"))?(String)map.get("fecFirDeclarante"):null);
            pstm.setString(11, (String) map.get("idSupervisor"));
            pstm.setString(12, !"".equals((String)map.get("fecFirSupervisor"))?(String)map.get("fecFirSupervisor"):null);
            pstm.setString(13, (String) map.get("idTecnico"));
            pstm.setString(14, !"".equals((String)map.get("fecFirTecCatastral"))?(String)map.get("fecFirTecCatastral"):null);
            pstm.setString(15, (String) map.get("idVerificador"));
            pstm.setString(16, !"".equals((String)map.get("fecFirVerCatastral"))?(String)map.get("fecFirVerCatastral"):null);
            pstm.setString(17, (String) map.get("id_uni_cat"));
            pstm.setString(18, (String) map.get("id_usuario"));
            pstm.setString(19, (String) map.get("codEstado"));

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

    public int actualizarFichas(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE FICHAS SET tip_ficha=?,nro_ficha=?,id_lote=?,dc=?,nro_ficha_lote=?,activo=?,firma_declarante=?,declarante=?,");
            sql.append("fecha_levantamiento=to_date(?,'DD/MM/YYYY HH24:MI:SS'),supervisor=?,fecha_supervision=to_date(?,'DD/MM/YYYY HH24:MI:SS'),");
            sql.append("tecnico=?,fecha_tecnico=to_date(?,'DD/MM/YYYY HH24:MI:SS'),verificador=?,fecha_verificacion=to_date(?,'DD/MM/YYYY HH24:MI:SS'),");
            sql.append("id_uni_cat=?,fecha_actualizacion=current_timestamp,usuario_actualizacion=?,id_estado=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("codTipFicha"));
            pstm.setString(2, (String) map.get("numFicha"));
            pstm.setString(3, (String) map.get("id_lote"));
            pstm.setString(4, (String) map.get("dc"));
            pstm.setString(5, (String) map.get("numFichLote"));
            pstm.setInt(6, Integer.parseInt((String) map.get("activo")));
            pstm.setInt(7, Integer.parseInt((String) map.get("firma_declarante")));
            pstm.setString(8, (String) map.get("declarante"));
            pstm.setString(9, !"".equals((String)map.get("fecFirDeclarante"))?(String)map.get("fecFirDeclarante"):null);
            pstm.setString(10, (String) map.get("idSupervisor"));
            pstm.setString(11, !"".equals((String)map.get("fecFirSupervisor"))?(String)map.get("fecFirSupervisor"):null);
            pstm.setString(12, (String) map.get("idTecnico"));
            pstm.setString(13, !"".equals((String)map.get("fecFirTecCatastral"))?(String)map.get("fecFirTecCatastral"):null);
            pstm.setString(14, (String) map.get("idVerificador"));
            pstm.setString(15, !"".equals((String)map.get("fecFirVerCatastral"))?(String)map.get("fecFirVerCatastral"):null);
            pstm.setString(16, (String) map.get("id_uni_cat"));
            pstm.setString(17, (String) map.get("id_usuario"));
            pstm.setString(18, (String) map.get("codEstado"));
            pstm.setString(19, (String) map.get("id_ficha"));

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

    public HashMap obtenerFicha(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ficha = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT f.nro_ficha,u.cuc,f.nro_ficha_lote,f.declarante,p.nro_doc,p.nombres,p.ape_materno,p.ape_paterno,");
            sql.append("to_char(f.fecha_levantamiento,'dd/MM/yyyy'),f.supervisor,to_char(f.fecha_supervision,'dd/MM/yyyy'),");
            sql.append("f.tecnico,to_char(f.fecha_tecnico,'dd/MM/yyyy'),f.verificador,to_char(f.fecha_verificacion,'dd/MM/yyyy'),");
            sql.append("f.tip_ficha,tc.desc_codigo,f.id_estado,f.id_ficha ");
            sql.append("FROM FICHAS f, PERSONAS p, TABLAS_CODIGOS tc, UNI_CAT u ");
            sql.append("WHERE f.declarante=p.id_persona AND f.tip_ficha=tc.codigo AND tc.id_tabla = 'FCH' AND u.id_uni_cat=f.id_uni_cat AND f.id_ficha = ? ");

//            if(map.containsKey("cuc") && map.get("cuc")!=null && !"".equals((String)map.get("cuc"))){
//                sql.append("AND u.cuc = '").append((String)map.get("cuc")).append("' ");
//            }
//            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha"))){
//                sql.append("AND f.nro_ficha = '").append(String.valueOf(Long.parseLong((String)map.get("numFicha")))).append("' ");
//            }
//            if(map.containsKey("dniTecCatastral") && map.get("dniTecCatastral")!=null && !"".equals((String)map.get("dniTecCatastral"))){
//                sql.append("AND p.nro_doc = '").append((String)map.get("dniTecCatastral")).append("' ");
//            }
//            if(map.containsKey("dniSupervisor") && map.get("dniSupervisor")!=null && !"".equals((String)map.get("dniSupervisor"))){
//                sql.append("AND p.nro_doc = '").append((String)map.get("dniSupervisor")).append("' ");
//            }
//            if(map.containsKey("codEstado") && map.get("codEstado")!=null && !"".equals((String)map.get("codEstado"))){
//                sql.append("AND f.id_estado = '").append((String)map.get("codEstado")).append("' ");
//            }

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String) map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                ficha = new HashMap();
                ficha.put("numFicha", rst.getString(1));
                ficha.put("cuc", rst.getString(2));
                ficha.put("numFichLote", rst.getString(3));
                ficha.put("declarante", rst.getString(4));
                ficha.put("dniDeclarante", rst.getString(5));
                ficha.put("nomDeclarante", rst.getString(6).trim());
                ficha.put("apeMatDeclarante", rst.getString(7).trim());
                ficha.put("apePatDeclarante", rst.getString(8).trim());
                ficha.put("fecFirDeclarante", rst.getString(9));
                ficha.put("idSupervisor", rst.getString(10));
                ficha.put("fecFirSupervisor", rst.getString(11));
                ficha.put("idTecnico", rst.getString(12));
                ficha.put("fecFirTecCatastral", rst.getString(13));
                ficha.put("idVerificador", rst.getString(14));
                ficha.put("fecFirVerCatastral", rst.getString(15));
                ficha.put("codTipFicha", rst.getString(16));
                ficha.put("desTipFicha", rst.getString(17));
                ficha.put("codEstado", rst.getString(18));
                ficha.put("id_ficha", rst.getString(19));
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

    public List buscarFichas(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ficha = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT DISTINCT f.nro_ficha,u.cuc,f.nro_ficha_lote,f.declarante,p.nro_doc,p.nombres,p.ape_materno,p.ape_paterno,");
            sql.append("to_char(f.fecha_levantamiento,'dd/MM/yyyy'),f.supervisor,to_char(f.fecha_supervision,'dd/MM/yyyy'),");
            sql.append("f.tecnico,to_char(f.fecha_tecnico,'dd/MM/yyyy'),f.verificador,to_char(f.fecha_verificacion,'dd/MM/yyyy'),");
            sql.append("f.tip_ficha,tc.desc_codigo,f.id_estado,f.id_ficha ");
            sql.append("FROM FICHAS f, PERSONAS p, TABLAS_CODIGOS tc, UNI_CAT u ");
            sql.append("WHERE f.declarante=p.id_persona AND f.tip_ficha=tc.codigo AND tc.id_tabla = 'FCH' AND u.id_uni_cat=f.id_uni_cat ");

//            sql.append("SELECT DISTINCT f.nro_ficha,u.cuc,f.nro_ficha_lote,f.tip_ficha,tc.desc_codigo,f.id_estado,f.id_ficha ");
//            sql.append("FROM FICHAS f, FICHAS_INDIVIDUALES fi, UNI_CAT u, TABLAS_CODIGOS tc ");
//            sql.append("WHERE f.id_ficha=fi.id_ficha AND u.id_uni_cat=f.id_uni_cat AND f.tip_ficha=tc.codigo AND tc.id_tabla = 'FCH' ");

            if(map.containsKey("cuc") && map.get("cuc")!=null && !"".equals((String)map.get("cuc"))){
                sql.append("AND u.cuc = '").append((String)map.get("cuc")).append("' ");
            }
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha"))){
                sql.append("AND f.nro_ficha = '").append((String)map.get("numFicha")).append("' ");
            }
            if(map.containsKey("dniTecCatastral") && map.get("dniTecCatastral")!=null && !"".equals((String)map.get("dniTecCatastral"))){
                sql.append("AND p.nro_doc = '").append((String)map.get("dniTecCatastral")).append("' ");
            }
            if(map.containsKey("dniSupervisor") && map.get("dniSupervisor")!=null && !"".equals((String)map.get("dniSupervisor"))){
                sql.append("AND p.nro_doc = '").append((String)map.get("dniSupervisor")).append("' ");
            }
            if(map.containsKey("codEstado") && map.get("codEstado")!=null && !"".equals((String)map.get("codEstado"))){
                sql.append("AND f.id_estado = '").append((String)map.get("codEstado")).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                ficha = new HashMap();
                ficha.put("numFicha", rst.getString(1));
                ficha.put("cuc", rst.getString(2));
                ficha.put("numFichLote", rst.getString(3));                
                ficha.put("declarante", rst.getString(4));
                ficha.put("dniDeclarante", rst.getString(5));
                ficha.put("nomDeclarante", rst.getString(6).trim());
                ficha.put("apeMatDeclarante", rst.getString(7).trim());
                ficha.put("apePatDeclarante", rst.getString(8).trim());
                ficha.put("fecFirDeclarante", rst.getString(9));
                ficha.put("idSupervisor", rst.getString(10));
                ficha.put("fecFirSupervisor", rst.getString(11));
                ficha.put("idTecnico", rst.getString(12));
                ficha.put("fecFirTecCatastral", rst.getString(13));
                ficha.put("idVerificador", rst.getString(14));
                ficha.put("fecFirVerCatastral", rst.getString(15));
                ficha.put("codTipFicha", rst.getString(16));
                ficha.put("desTipFicha", rst.getString(17));
                ficha.put("codEstado", rst.getString(18));
                ficha.put("id_ficha", rst.getString(19));
//                ficha.put("numFicha", rst.getString(1));
//                ficha.put("cuc", rst.getString(2));
//                ficha.put("numFichLote", rst.getString(3));
//                ficha.put("codTipFicha", rst.getString(4));
//                ficha.put("desTipFicha", rst.getString(5));
//                ficha.put("codEstado", rst.getString(6));
//                ficha.put("id_ficha", rst.getString(7));
                lista.add(ficha);
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

    public boolean existeFichas(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM FICHAS WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_ficha"));

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
