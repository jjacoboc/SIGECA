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
public class DomicilioTitularDAO extends GenericDAO {

    /** Crea una nueva instancia de DomicilioTitularDAO */
    public DomicilioTitularDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarDomicilioTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO DOMICILIO_TITULARES(id_ficha,id_persona,cod_departamento,cod_provincia,cod_distrito,telefono,anexo,fax,correo,");
            sql.append("cod_via,tip_via,nom_via,nro_muni,nom_edificacion,nro_interior,cod_hab_urba,nom_hab_urba,sector,manzana,lote,sublote) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));
            pstm.setString(3, (String) map.get("codDepartamento"));
            pstm.setString(4, (String) map.get("codProvincia"));
            pstm.setString(5, (String) map.get("codDistrito"));
            pstm.setString(6, (String) map.get("telefonoTit"));
            pstm.setString(7, (String) map.get("anexoTit"));
            pstm.setString(8, (String) map.get("faxTit"));
            pstm.setString(9, (String) map.get("correoTit"));
            pstm.setString(10, (String) map.get("codViaTit"));
            pstm.setString(11, (String) map.get("codTipViaTit"));
            pstm.setString(12, (String) map.get("nomViaTit"));
            pstm.setString(13, (String) map.get("numMunicipalTit"));
            pstm.setString(14, (String) map.get("nomEdificacionTit"));
            pstm.setString(15, (String) map.get("numInteriorTit"));
            pstm.setString(16, (String) map.get("codHUTit"));
            pstm.setString(17, (String) map.get("nomHUTit"));
            pstm.setString(18, (String) map.get("sectorTit"));
            pstm.setString(19, (String) map.get("manzanaTit"));
            pstm.setString(20, (String) map.get("loteTit"));
            pstm.setString(21, (String) map.get("subloteTit"));

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

    public int actualizarDomicilioTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE DOMICILIO_TITULARES SET cod_departamento=?,cod_provincia=?,cod_distrito=?,telefono=?,anexo=?,fax=?,correo=?,cod_via=?,");
            sql.append("tip_via=?,nom_via=?,nro_muni=?,nom_edificacion=?,nro_interior=?,cod_hab_urba=?,nom_hab_urba=?,sector=?,manzana=?,lote=?,sublote=? ");
            sql.append("WHERE id_ficha=? AND id_persona=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("codDepartamento"));
            pstm.setString(2, (String) map.get("codProvincia"));
            pstm.setString(3, (String) map.get("codDistrito"));
            pstm.setString(4, (String) map.get("telefonoTit"));
            pstm.setString(5, (String) map.get("anexoTit"));
            pstm.setString(6, (String) map.get("faxTit"));
            pstm.setString(7, (String) map.get("correoTit"));
            pstm.setString(8, (String) map.get("codViaTit"));
            pstm.setString(9, (String) map.get("codTipViaTit"));
            pstm.setString(10, (String) map.get("nomViaTit"));
            pstm.setString(11, (String) map.get("numMunicipalTit"));
            pstm.setString(12, (String) map.get("nomEdificacionTit"));
            pstm.setString(13, (String) map.get("numInteriorTit"));
            pstm.setString(14, (String) map.get("codHUTit"));
            pstm.setString(15, (String) map.get("nomHUTit"));
            pstm.setString(16, (String) map.get("sectorTit"));
            pstm.setString(17, (String) map.get("manzanaTit"));
            pstm.setString(18, (String) map.get("loteTit"));
            pstm.setString(19, (String) map.get("subloteTit"));
            pstm.setString(20, (String) map.get("id_ficha"));
            pstm.setString(21, (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));

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

    public HashMap obtenerDomicilioTitular(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT dt.id_ficha,dt.cod_departamento,dt.cod_provincia,dt.cod_distrito,dt.telefono,dt.anexo,dt.fax,dt.correo,dt.cod_via,dt.tip_via,");
            sql.append("dt.nom_via,dt.nro_muni,dt.nom_edificacion,dt.nro_interior,dt.cod_hab_urba,dt.nom_hab_urba,dt.sector,dt.manzana,dt.lote,dt.sublote,dt.id_persona ");
            sql.append("FROM fichas f, domicilio_titulares dt ");
            sql.append("WHERE dt.id_ficha=f.id_ficha AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                titular = new HashMap();
                titular.put("id_ficha", rst.getString(1));
                titular.put("codDepartamento", rst.getString(2));
                titular.put("codProvincia", rst.getString(3));
                titular.put("codDistrito", rst.getString(4));
                titular.put("telefono", rst.getString(5));
                titular.put("anexo", rst.getString(6));
                titular.put("fax", rst.getString(7));
                titular.put("corElectronico", rst.getString(8));
                titular.put("codVia", rst.getString(9));
                titular.put("codTipVia", rst.getString(10));
                titular.put("nomVia", rst.getString(11));
                titular.put("numMunicipal", rst.getString(12));
                titular.put("nomEdificacion", rst.getString(13));
                titular.put("numInterior", rst.getString(14));
                titular.put("codHabUrbana", rst.getString(15));
                titular.put("nomHabUrbana", rst.getString(16));
                titular.put("sector", rst.getString(17));
                titular.put("manzana", rst.getString(18));
                titular.put("lote", rst.getString(19));
                titular.put("sublote", rst.getString(20));
                titular.put("id_persona", rst.getString(21));
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
        return titular;
    }

    public List obtenerListaDomicilioTitular(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT dt.id_ficha,dt.cod_departamento,dt.cod_provincia,dt.cod_distrito,dt.telefono,dt.anexo,dt.fax,dt.correo,dt.cod_via,dt.tip_via,");
            sql.append("dt.nom_via,dt.nro_muni,dt.nom_edificacion,dt.nro_interior,dt.cod_hab_urba,dt.nom_hab_urba,dt.sector,dt.manzana,dt.lote,dt.sublote,dt.id_persona ");
            sql.append("FROM fichas f, domicilio_titulares dt ");
            sql.append("WHERE dt.id_ficha=f.id_ficha AND f.id_ficha = ? ");
            sql.append("ORDER BY dt.id_persona ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            if(rst.next()){
                titular = new HashMap();
                titular.put("id_ficha", rst.getString(1));
                titular.put("codDepartamento", rst.getString(2));
                titular.put("codProvincia", rst.getString(3));
                titular.put("codDistrito", rst.getString(4));
                titular.put("telefono", rst.getString(5));
                titular.put("anexo", rst.getString(6));
                titular.put("fax", rst.getString(7));
                titular.put("corElectronico", rst.getString(8));
                titular.put("codVia", rst.getString(9));
                titular.put("codTipVia", rst.getString(10));
                titular.put("nomVia", rst.getString(11));
                titular.put("numMunicipal", rst.getString(12));
                titular.put("nomEdificacion", rst.getString(13));
                titular.put("numInterior", rst.getString(14));
                titular.put("codHabUrbana", rst.getString(15));
                titular.put("nomHabUrbana", rst.getString(16));
                titular.put("sector", rst.getString(17));
                titular.put("manzana", rst.getString(18));
                titular.put("lote", rst.getString(19));
                titular.put("sublote", rst.getString(20));
                titular.put("id_persona", rst.getString(21));
                lista.add("titular");
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

    public boolean existeDomicilioTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha, id_persona FROM DOMICILIO_TITULARES WHERE id_ficha = ? AND id_persona = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));

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
