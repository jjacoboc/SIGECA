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
 * @author Administrador
 */
public class ConstruccionDAO extends GenericDAO {

    /** Crea una nueva instancia de ConstruccionDAO */
    public ConstruccionDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long getPK() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        long result = 1;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT max(id_construccion) as id FROM CONSTRUCCIONES");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            if(rst.next()){
                result = rst.getLong(1) + 1;
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
            throw new DAOException();
        }
        return result;
    }

    public long guardarConstruccion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO CONSTRUCCIONES(id_ficha,nro_piso,fecha,mep,ecs,ecc,estru_muro_col,estru_techo,acaba_piso,");
            sql.append("acaba_puerta_ven,acaba_revest,acaba_bano,inst_elect_sanita,area_declarada,area_verificada,uca,nro_registro,id_construccion) ");
            sql.append("VALUES(?,?,to_date(?,'dd/MM/yyyy'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String)map.get("id_ficha"));
            pstm.setString(2, (String)map.get("numPiso"));
            pstm.setString(3, !"".equals((String)map.get("fecConstruccion"))?(String)map.get("fecConstruccion"):null);
            pstm.setString(4, StringUtil.emptyStringAsNull((String)map.get("codMEP")));
            pstm.setString(5, StringUtil.emptyStringAsNull((String)map.get("codECS")));
            pstm.setString(6, StringUtil.emptyStringAsNull((String)map.get("codECC")));
            pstm.setString(7, StringUtil.emptyAsZero((String)map.get("muro")));
            pstm.setString(8, StringUtil.emptyAsZero((String)map.get("techo")));
            pstm.setString(9, StringUtil.emptyAsZero((String)map.get("pisos")));
            pstm.setString(10, StringUtil.emptyAsZero((String)map.get("puerta")));
            pstm.setString(11, StringUtil.emptyAsZero((String)map.get("revestimiento")));
            pstm.setString(12, StringUtil.emptyAsZero((String)map.get("bano")));
            pstm.setString(13, StringUtil.emptyAsZero((String)map.get("instalaciones")));
            pstm.setDouble(14, (map.get("areConDeclarada")!=null && !"".equals((String)map.get("areConDeclarada")))?Double.parseDouble(((String)map.get("areConDeclarada")).trim()):0);
            pstm.setDouble(15, (map.get("areConVerificada")!=null && !"".equals((String)map.get("areConVerificada")))?Double.parseDouble(((String) map.get("areConVerificada")).trim()):0);
            pstm.setString(16, StringUtil.emptyStringAsNull((String)map.get("codUCA")));
            pstm.setInt(17, (map.get("nro_registro")!=null && !"".equals((String)map.get("nro_registro")))?Integer.parseInt(((String)map.get("nro_registro")).trim()):0);
            pstm.setLong(18, ((Long)map.get("id_construccion")).longValue());

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

    public long actualizarConstruccion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CONSTRUCCIONES SET nro_piso=?,fecha=to_date(?,'dd/MM/yyyy'),mep=?,ecs=?,ecc=?,estru_muro_col=?,estru_techo=?,acaba_piso=?,");
            sql.append("acaba_puerta_ven=?,acaba_revest=?,acaba_bano=?,inst_elect_sanita=?,area_declarada=?,area_verificada=?,uca=?,nro_registro=? ");
            sql.append("WHERE id_ficha=? AND id_construccion=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String)map.get("numPiso"));
            pstm.setString(2, !"".equals((String)map.get("fecConstruccion"))?(String)map.get("fecConstruccion"):null);
            pstm.setString(3, StringUtil.emptyStringAsNull((String)map.get("codMEP")));
            pstm.setString(4, StringUtil.emptyStringAsNull((String)map.get("codECS")));
            pstm.setString(5, StringUtil.emptyStringAsNull((String)map.get("codECC")));
            pstm.setString(6, StringUtil.emptyAsZero((String)map.get("muro")));
            pstm.setString(7, StringUtil.emptyAsZero((String)map.get("techo")));
            pstm.setString(8, StringUtil.emptyAsZero((String)map.get("pisos")));
            pstm.setString(9, StringUtil.emptyAsZero((String)map.get("puerta")));
            pstm.setString(10, StringUtil.emptyAsZero((String)map.get("revestimiento")));
            pstm.setString(11, StringUtil.emptyAsZero((String)map.get("bano")));
            pstm.setString(12, StringUtil.emptyAsZero((String)map.get("instalaciones")));
            pstm.setDouble(13, (map.get("areConDeclarada")!=null && !"".equals((String)map.get("areConDeclarada")))?Double.parseDouble(((String)map.get("areConDeclarada")).trim()):0);
            pstm.setDouble(14, (map.get("areConVerificada")!=null && !"".equals((String)map.get("areConVerificada")))?Double.parseDouble(((String) map.get("areConVerificada")).trim()):0);
            pstm.setString(15, StringUtil.emptyStringAsNull((String)map.get("codUCA")));
            pstm.setInt(16, (map.get("nro_registro")!=null && !"".equals((String)map.get("nro_registro")))?Integer.parseInt(((String)map.get("nro_registro")).trim()):0);
            pstm.setString(17,(String)map.get("id_ficha"));
            pstm.setLong(18, ((Long)map.get("id_construccion")).longValue());

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

    public boolean existeConstruccion(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM CONSTRUCCIONES WHERE id_ficha = ? AND id_construccion=? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String)map.get("id_ficha"));
            pstm.setLong(2, ((Long)map.get("id_construccion")).longValue());

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

    public List obtenerConstruccion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap construccion = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT DISTINCT c.id_construccion,c.nro_piso,to_char(c.fecha,'dd/MM/yyyy'),c.mep,c.ecs,c.ecc,c.estru_muro_col,");
            sql.append("c.estru_techo,c.acaba_piso,c.acaba_puerta_ven,c.acaba_revest,c.acaba_bano,c.inst_elect_sanita,c.area_declarada,");
            sql.append("c.area_verificada,c.uca,c.id_ficha ");
            sql.append("FROM construcciones c, fichas f ");
            sql.append("WHERE c.id_ficha=f.id_ficha AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                construccion = new HashMap();
                construccion.put("id_construccion", rst.getString(1));
                construccion.put("numPiso", rst.getString(2));
                construccion.put("fecConstruccion", rst.getString(3));
                construccion.put("codMEP", rst.getString(4));
                construccion.put("codECS", rst.getString(5));
                construccion.put("codECC", rst.getString(6));
                construccion.put("muro", rst.getString(7));
                construccion.put("techo", rst.getString(8));
                construccion.put("pisos", rst.getString(9));
                construccion.put("puerta", rst.getString(10));
                construccion.put("revestimiento", rst.getString(11));
                construccion.put("bano", rst.getString(12));
                construccion.put("instalaciones", rst.getString(13));
                construccion.put("areConDeclarada", rst.getString(14));
                construccion.put("areConVerificada", rst.getString(15));
                construccion.put("codUCA", rst.getString(16));
                construccion.put("id_ficha", rst.getString(17));
                lista.add(construccion);
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