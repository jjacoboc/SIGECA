/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.resource.Properties;
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
public class CabeceraDAO extends GenericDAO {

    /** Crea una nueva instancia de CabeceraDAO */
    public CabeceraDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }


    public int guardarCabecera(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO UNI_CAT(id_uni_cat,id_lote,id_edificacion,cod_entrada,cod_piso,cod_unidad,tip_interior,");
            sql.append("cuc,cuc_antecedente,cod_hoja_catastral,cod_pred_rentas,nro_interior,uni_acum_rentas) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("cuc"));
            pstm.setString(2,(String)map.get("codHojCatastral"));
            pstm.setString(3,(String)map.get("codRefCatastral"));
            pstm.setString(4,(String)map.get("codDepartamento"));
            pstm.setString(5,(String)map.get("codProvincia"));
            pstm.setString(6,(String)map.get("codDistrito"));
            pstm.setString(7,(String)map.get("sector"));
            pstm.setString(8,(String)map.get("manzana"));
            pstm.setString(9,(String)map.get("lote"));
            pstm.setString(10,(String)map.get("edifica"));
            pstm.setString(11,(String)map.get("entrada"));
            pstm.setString(12,(String)map.get("piso"));
            pstm.setString(13,(String)map.get("unidad"));
            pstm.setString(14,(String)map.get("dc"));
            pstm.setString(15,(String)map.get("codConRenta"));
            pstm.setString(16,(String)map.get("codPreRenta"));
            pstm.setString(17,(String)map.get("uniAcuCodPreRenta"));
            pstm.setString(18,(String)map.get("zona"));
            pstm.setString(19,(String)map.get("uniOrgCatRural"));
            pstm.setString(20,(String)map.get("uniCatastral"));
            pstm.setString(21,(String)map.get("coorEste"));
            pstm.setString(22,(String)map.get("coorNorte"));

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

    public int actualizarCabecera(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TCABECERA SET codHojCatastral=?,codRefCatastral=?,codDepartamento=?,codProvincia=?,codDistrito=?,");
            sql.append("sector=?,manzana=?,lote=?,edifica=?,entrada=?,piso=?,unidad=?,dc=?,codConRentas=?,codPreRentas=?,uniAcuCodPreRentas=?,");
            sql.append("zona=?,uniOrgCatRural=?,uniCatastral=?,coorEste=?,coorNorte=? ");
            sql.append("WHERE cuc=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codHojCatastral"));
            pstm.setString(2,(String)map.get("codRefCatastral"));
            pstm.setString(3,(String)map.get("codDepartamento"));
            pstm.setString(4,(String)map.get("codProvincia"));
            pstm.setString(5,(String)map.get("codDistrito"));
            pstm.setString(6,(String)map.get("sector"));
            pstm.setString(7,(String)map.get("manzana"));
            pstm.setString(8,(String)map.get("lote"));
            pstm.setString(9,(String)map.get("edifica"));
            pstm.setString(10,(String)map.get("entrada"));
            pstm.setString(11,(String)map.get("piso"));
            pstm.setString(12,(String)map.get("unidad"));
            pstm.setString(13,(String)map.get("dc"));
            pstm.setString(14,(String)map.get("codConRenta"));
            pstm.setString(15,(String)map.get("codPreRenta"));
            pstm.setString(16,(String)map.get("uniAcuCodPreRenta"));
            pstm.setString(17,(String)map.get("zona"));
            pstm.setString(18,(String)map.get("uniOrgCatRural"));
            pstm.setString(19,(String)map.get("uniCatastral"));
            pstm.setString(20,(String)map.get("coorEste"));
            pstm.setString(21,(String)map.get("coorNorte"));
            pstm.setString(22,(String)map.get("cuc"));

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

    public int eliminarCabecera(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM UNI_CAT WHERE 1=1 ");

            if(map.containsKey("cuc") && map.get("cuc")!=null && !"".equals((String)map.get("cuc"))){
                sql.append("AND cuc = '").append((String)map.get("cuc")).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());
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

    public HashMap obtenerCabeceraByCUC(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap cabecera = null;
        try {
            StringBuilder sql = new StringBuilder();
            if(map!=null && map.containsKey("codTipFicha") && Properties.FichaCatastralUrbanaIndividual.equals((String)map.get("codTipFicha"))){
                sql.append("SELECT DISTINCT u.cod_hoja_catastral, s.id_ubi_geo, s.cod_sector, m.cod_mzna, l.cod_lote, e.cod_edificacion, u.cod_entrada, u.cod_piso, u.cod_unidad, f.dc, ");
                sql.append("t.cod_contribuyente, u.cod_pred_rentas, u.uni_acum_rentas,  u.cuc ");
                sql.append("FROM SECTORES s, MANZANAS m, LOTES l, EDIFICACIONES e, UNI_CAT u, FICHAS f, TITULARES t ");
                sql.append("WHERE s.id_sector=m.id_sector AND m.id_mzna=l.id_mzna AND l.id_lote=u.id_lote AND u.id_uni_cat=f.id_uni_cat AND f.id_ficha=t.id_ficha ");
                sql.append("AND u.id_edificacion=e.id_edificacion AND s.id_ubi_geo = '").append((String)map.get("id_ubi_geo")).append("' AND u.cuc = ? ");
            }else{
                sql.append("SELECT DISTINCT u.cod_hoja_catastral, s.id_ubi_geo, s.cod_sector, m.cod_mzna, l.cod_lote, e.cod_edificacion, u.cod_entrada, u.cod_piso, u.cod_unidad, f.dc, ");
                sql.append("u.cod_pred_rentas, u.uni_acum_rentas,  u.cuc ");
                sql.append("FROM SECTORES s, MANZANAS m, LOTES l, EDIFICACIONES e, UNI_CAT u, FICHAS f ");
                sql.append("WHERE s.id_sector=m.id_sector AND m.id_mzna=l.id_mzna AND l.id_lote=u.id_lote AND u.id_uni_cat=f.id_uni_cat ");
                sql.append("AND u.id_edificacion=e.id_edificacion AND s.id_ubi_geo = '").append((String)map.get("id_ubi_geo")).append("' AND u.cuc = ? ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("cuc"));

            rst = pstm.executeQuery();

            if(rst.next()){
                cabecera = new HashMap();
                cabecera.put("codHojCatastral", rst.getString(1));
                cabecera.put("codRefCatastral", rst.getString(2));
                cabecera.put("dpto", rst.getString(2).substring(0, 2));
                cabecera.put("prov", rst.getString(2).substring(2, 4));
                cabecera.put("dist", rst.getString(2).substring(4, 6));
                cabecera.put("sector", rst.getString(3));
                cabecera.put("manzana", rst.getString(4));
                cabecera.put("lote", rst.getString(5));
                cabecera.put("edifica", rst.getString(6));
                cabecera.put("entrada", rst.getString(7));
                cabecera.put("piso", rst.getString(8));
                cabecera.put("unidad", rst.getString(9));
                cabecera.put("dc", rst.getString(10));
                if(map!=null && map.containsKey("codTipFicha") && Properties.FichaCatastralUrbanaIndividual.equals((String)map.get("codTipFicha"))){
                    cabecera.put("codConRenta", rst.getString(11));
                    cabecera.put("codPreRenta", rst.getString(12));
                    cabecera.put("uniAcuCodPreRenta", rst.getString(13));
                    cabecera.put("cuc", rst.getString(14));
                    cabecera.put("cucA", rst.getString(14).substring(0,7));
                    cabecera.put("cucB", rst.getString(14).substring(8,rst.getString(14).length()));
                }else{
                    cabecera.put("codPreRenta", rst.getString(11));
                    cabecera.put("uniAcuCodPreRenta", rst.getString(12));
                    cabecera.put("cuc", rst.getString(13));
                    cabecera.put("cucA", rst.getString(13).substring(0,7));
                    cabecera.put("cucB", rst.getString(13).substring(8,rst.getString(13).length()));
                }
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
        return cabecera;
    }

    public HashMap obtenerCabeceraById(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap cabecera = null;
        try {
            StringBuilder sql = new StringBuilder();
            if(map!=null && map.containsKey("codTipFicha") && Properties.FichaCatastralUrbanaIndividual.equals((String)map.get("codTipFicha"))){
                sql.append("SELECT DISTINCT u.cod_hoja_catastral,s.id_ubi_geo,s.cod_sector, m.cod_mzna, l.cod_lote, e.cod_edificacion, u.cod_entrada, u.cod_piso, u.cod_unidad, f.dc, ");
                sql.append("t.cod_contribuyente, u.cod_pred_rentas, u.uni_acum_rentas,  u.cuc ");
                sql.append("FROM SECTORES s, MANZANAS m, LOTES l, EDIFICACIONES e, UNI_CAT u, FICHAS f, TITULARES t ");
                sql.append("WHERE s.id_sector=m.id_sector AND m.id_mzna=l.id_mzna AND l.id_lote=u.id_lote AND u.id_uni_cat=f.id_uni_cat AND f.id_ficha=t.id_ficha AND u.id_edificacion=e.id_edificacion ");
                sql.append("AND f.id_ficha = ? ");
            }else{
                sql.append("SELECT DISTINCT u.cod_hoja_catastral,s.id_ubi_geo,s.cod_sector, m.cod_mzna, l.cod_lote, e.cod_edificacion, u.cod_entrada, u.cod_piso, u.cod_unidad, f.dc, ");
                sql.append("u.cod_pred_rentas, u.uni_acum_rentas,  u.cuc ");
                sql.append("FROM SECTORES s, MANZANAS m, LOTES l, EDIFICACIONES e, UNI_CAT u, FICHAS f ");
                sql.append("WHERE s.id_sector=m.id_sector AND m.id_mzna=l.id_mzna AND l.id_lote=u.id_lote AND u.id_uni_cat=f.id_uni_cat AND u.id_edificacion=e.id_edificacion ");
                sql.append("AND f.id_ficha = ? ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                cabecera = new HashMap();
                cabecera.put("codHojCatastral", rst.getString(1));
                cabecera.put("codRefCatastral", rst.getString(2));
                cabecera.put("codDepartamento", rst.getString(2).substring(0, 2));
                cabecera.put("codProvincia", rst.getString(2).substring(2, 4));
                cabecera.put("codDistrito", rst.getString(2).substring(4, 6));
                cabecera.put("sector", rst.getString(3));
                cabecera.put("manzana", rst.getString(4));
                cabecera.put("lote", rst.getString(5));
                cabecera.put("edifica", rst.getString(6));
                cabecera.put("entrada", rst.getString(7));
                cabecera.put("piso", rst.getString(8));
                cabecera.put("unidad", rst.getString(9));
                cabecera.put("dc", rst.getString(10));
                if(map!=null && map.containsKey("codTipFicha") && Properties.FichaCatastralUrbanaIndividual.equals((String)map.get("codTipFicha"))){
                    cabecera.put("codConRenta", rst.getString(11));
                    cabecera.put("codPreRenta", rst.getString(12));
                    cabecera.put("uniAcuCodPreRenta", rst.getString(13));
                    cabecera.put("cuc", rst.getString(14));
                    cabecera.put("cucA", rst.getString(14).substring(0,7));
                    cabecera.put("cucB", rst.getString(14).substring(8,rst.getString(14).length()));
                }else{
                    cabecera.put("codPreRenta", rst.getString(11));
                    cabecera.put("uniAcuCodPreRenta", rst.getString(12));
                    cabecera.put("cuc", rst.getString(13));
                    cabecera.put("cucA", rst.getString(13).substring(0,7));
                    cabecera.put("cucB", rst.getString(13).substring(8,rst.getString(13).length()));
                }
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
        return cabecera;
    }

    public List obtenerListaCabecera(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap cabecera = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            if(map!=null && map.containsKey("codTipFicha") && Properties.FichaCatastralUrbanaIndividual.equals((String)map.get("codTipFicha"))){
                sql.append("SELECT DISTINCT u.cod_hoja_catastral,s.id_ubi_geo,s.cod_sector, m.cod_mzna, l.cod_lote, e.cod_edificacion, u.cod_entrada, ");
                sql.append("u.cod_piso, u.cod_unidad, f.dc, t.cod_contribuyente, u.cod_pred_rentas, u.uni_acum_rentas,  u.cuc ");
                sql.append("FROM SECTORES s, MANZANAS m, LOTES l, EDIFICACIONES e, UNI_CAT u, FICHAS f, TITULARES t ");
                sql.append("WHERE s.id_sector=m.id_sector AND m.id_mzna=l.id_mzna AND l.id_lote=u.id_lote AND u.id_uni_cat=f.id_uni_cat AND f.id_ficha=t.id_ficha ");
                sql.append("AND u.id_edificacion=e.id_edificacion AND s.id_ubi_geo = '").append((String)map.get("id_ubi_geo")).append("' ");
            }else{
                sql.append("SELECT DISTINCT u.cod_hoja_catastral,s.id_ubi_geo,s.cod_sector, m.cod_mzna, l.cod_lote, e.cod_edificacion, u.cod_entrada, ");
                sql.append("u.cod_piso, u.cod_unidad, f.dc, u.cod_pred_rentas, u.uni_acum_rentas,  u.cuc ");
                sql.append("FROM SECTORES s, MANZANAS m, LOTES l, EDIFICACIONES e, UNI_CAT u, FICHAS f ");
                sql.append("WHERE s.id_sector=m.id_sector AND m.id_mzna=l.id_mzna AND l.id_lote=u.id_lote AND u.id_uni_cat=f.id_uni_cat ");
                sql.append("AND u.id_edificacion=e.id_edificacion AND s.id_ubi_geo = '").append((String)map.get("id_ubi_geo")).append("' ");
            }
            

            if(map.containsKey("cuc") && map.get("cuc")!=null && !"".equals((String)map.get("cuc"))){
                sql.append("AND cuc = '").append((String)map.get("cuc")).append("' ");
            }
            if(map.containsKey("codRefCatastral") && map.get("codRefCatastral")!=null && !"".equals((String)map.get("codRefCatastral"))){
                sql.append("AND codRefCatastral LIKE '").append((String)map.get("codRefCatastral")).append("%' ");
            }
            if(map.containsKey("codHojCatastral") && map.get("codHojCatastral")!=null && !"".equals((String)map.get("codHojCatastral"))){
                sql.append("AND codHojCatastral = '").append((String)map.get("codHojCatastral")).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                cabecera = new HashMap();
                cabecera.put("codHojCatastral", rst.getString(1));
                cabecera.put("codRefCatastral", rst.getString(2));
                cabecera.put("dpto", rst.getString(2).substring(0, 2));
                cabecera.put("prov", rst.getString(2).substring(2, 4));
                cabecera.put("dist", rst.getString(2).substring(4, 6));
                cabecera.put("sector", rst.getString(3));
                cabecera.put("manzana", rst.getString(4));
                cabecera.put("lote", rst.getString(5));
                cabecera.put("edifica", rst.getString(6));
                cabecera.put("entrada", rst.getString(7));
                cabecera.put("piso", rst.getString(8));
                cabecera.put("unidad", rst.getString(9));
                cabecera.put("dc", rst.getString(10));
                if(map!=null && map.containsKey("codTipFicha") && Properties.FichaCatastralUrbanaIndividual.equals((String)map.get("codTipFicha"))){
                    cabecera.put("codConRenta", rst.getString(11));
                    cabecera.put("codPreRenta", rst.getString(12));
                    cabecera.put("uniAcuCodPreRenta", rst.getString(13));
                    cabecera.put("cuc", rst.getString(14));
                    cabecera.put("cucA", rst.getString(14).substring(0,9));
                    cabecera.put("cucB", rst.getString(14).substring(10,rst.getString(14).length()));
                }else{
                    cabecera.put("codPreRenta", rst.getString(11));
                    cabecera.put("uniAcuCodPreRenta", rst.getString(12));
                    cabecera.put("cuc", rst.getString(13));
                    cabecera.put("cucA", rst.getString(13).substring(0,9));
                    cabecera.put("cucB", rst.getString(13).substring(10,rst.getString(13).length()));
                }
                lista.add(cabecera);
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

    public boolean existeCUC(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ubi_geo FROM UBIGEO WHERE id_ubi_geo = ? AND cuc_desde <= ? AND cuc_hasta >= ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1,(String)map.get("codDepartamento")+(String)map.get("codProvincia")+(String)map.get("codDistrito"));
            pstm.setString(2,(String)map.get("cucA")+(String)map.get("cucB"));
            pstm.setString(3,(String)map.get("cucA")+(String)map.get("cucB"));
            
            rst = pstm.executeQuery();

            if(rst.next()){
                existe = true;
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
            throw new DAOException(ex);
        }
        return existe;
    }
}