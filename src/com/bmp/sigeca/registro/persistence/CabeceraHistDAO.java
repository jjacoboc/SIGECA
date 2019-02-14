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
 * @author Administrador
 */
public class CabeceraHistDAO extends GenericDAO {

    /** Crea una nueva instancia de CabeceraHistDAO */
    public CabeceraHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarCabecera(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TCABECERA_HIST(cuc,codHojCatastral,codRefCatastral,codDepartamento,codProvincia,codDistrito,");
            sql.append("sector,manzana,lote,edifica,entrada,piso,unidad,dc,codConRentas,codPreRentas,uniAcuCodPreRentas) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TCABECERA_HIST SET codHojCatastral=?,codRefCatastral=?,codDepartamento=?,codProvincia=?,codDistrito=?,");
            sql.append("sector=?,manzana=?,lote=?,edifica=?,entrada=?,piso=?,unidad=?,dc=?,codConRentas=?,codPreRentas=?,uniAcuCodPreRentas=? ");
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
            pstm.setString(17,(String)map.get("cuc"));

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

    public HashMap obtenerCabeceraByCUC(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap cabecera = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codHojCatastral,codRefCatastral,codDepartamento,codProvincia,codDistrito,sector,");
            sql.append("manzana,lote,edifica,entrada,piso,unidad,dc,codConRentas,codPreRentas,uniAcuCodPreRentas,cuc ");
            sql.append("FROM TCABECERA_HIST ");
            sql.append("WHERE cuc = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("cuc"));

            rst = pstm.executeQuery();

            if(rst.next()){
                cabecera = new HashMap();
                cabecera.put("codHojCatastral", rst.getString(1));
                cabecera.put("codRefCatastral", rst.getString(2));
                cabecera.put("codDepartamento", rst.getString(3));
                cabecera.put("codProvincia", rst.getString(4));
                cabecera.put("codDistrito", rst.getString(5));
                cabecera.put("sector", rst.getString(6));
                cabecera.put("manzana", rst.getString(7));
                cabecera.put("lote", rst.getString(8));
                cabecera.put("edifica", rst.getString(9));
                cabecera.put("entrada", rst.getString(10));
                cabecera.put("piso", rst.getString(11));
                cabecera.put("unidad", rst.getString(12));
                cabecera.put("dc", rst.getString(13));
                cabecera.put("codConRenta", rst.getString(14));
                cabecera.put("codPreRenta", rst.getString(15));
                cabecera.put("uniAcuCodPreRenta", rst.getString(16));
                cabecera.put("cuc", rst.getString(17));
                cabecera.put("cucA", rst.getString(17).trim().substring(0,8));
                cabecera.put("cucB", rst.getString(17).substring(8,rst.getString(17).length()));
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
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codHojCatastral,codRefCatastral,codDepartamento,codProvincia,codDistrito,sector,");
            sql.append("manzana,lote,edifica,entrada,piso,unidad,dc,codConRentas,codPreRentas,uniAcuCodPreRentas,cuc ");
            sql.append("FROM TCABECERA_HIST WHERE 1=1 ");

            if(map.containsKey("cuc") && map.get("cuc")!=null && !"".equals((String)map.get("cuc"))){
                sql.append("AND cuc = '").append((String)map.get("cuc")).append("' ");
            }
            if(map.containsKey("codRefCatastral") && map.get("codRefCatastral")!=null && !"".equals((String)map.get("codRefCatastral"))){
                sql.append("AND codRefCatastral = '").append((String)map.get("codRefCatastral")).append("' ");
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
                cabecera.put("dpto", rst.getString(3));
                cabecera.put("prov", rst.getString(4));
                cabecera.put("dist", rst.getString(5));
                cabecera.put("sector", rst.getString(6));
                cabecera.put("manzana", rst.getString(7));
                cabecera.put("lote", rst.getString(8));
                cabecera.put("edifica", rst.getString(9));
                cabecera.put("entrada", rst.getString(10));
                cabecera.put("piso", rst.getString(11));
                cabecera.put("unidad", rst.getString(12));
                cabecera.put("dc", rst.getString(13));
                cabecera.put("codConRenta", rst.getString(14));
                cabecera.put("codPreRenta", rst.getString(15));
                cabecera.put("uniAcuCodPreRenta", rst.getString(16));
                cabecera.put("cuc", rst.getString(17));
                cabecera.put("cucA", rst.getString(17).substring(0,9));
                cabecera.put("cucB", rst.getString(17).substring(10,rst.getString(17).length()));
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
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT cuc FROM TCABECERA_HIST where cuc = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1,(String)map.get("cuc"));

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

    public String getLastCodCabecera() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        String codCabecera = "";
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT MAX(codCabecera) FROM TCABECERA_HIST WHERE 1=1 ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            if(rst.next()){
                codCabecera = rst.getString(1);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
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
        return codCabecera;
    }
}
