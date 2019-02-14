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
 * @author Jonatan Jacobo
 */
public class TitularesDAO extends GenericDAO {

    /** Crea una nueva instancia de TitularesDAO */
    public TitularesDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("INSERT INTO TITULARES(id_ficha,id_persona,telefono,anexo,fax,correo_elect,fecha_adquisicion,forma_adquisicion,");
            sql.append("cod_contribuyente,nro_titular,estado_civil,sys_dir,porcentaje_cotitular,condic_de_titular) ");
            sql.append("VALUES(?,?,?,?,?,?,to_date(?,'DD/MM/YYYY HH24:MI:SS'),?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));
            pstm.setString(3, (String) map.get("telefonoTit"));
            pstm.setString(4, (String) map.get("anexoTit"));
            pstm.setString(5, (String) map.get("faxTit"));
            pstm.setString(6, (String) map.get("correoTit"));
            pstm.setString(7, !"".equals((String)map.get("fecAdquisicion"))?(String)map.get("fecAdquisicion"):null);
            pstm.setString(8, (String) map.get("codForAdqPredio"));
            pstm.setString(9, (String) map.get("codContribuyente"));
            pstm.setString(10, map.get("numCotitular")!=null?(String) map.get("numCotitular"):"");
            pstm.setString(11, (String) map.get("codEstCivil"));
            pstm.setString(12, (String) map.get("sys_dir"));
            pstm.setDouble(13, map.get("porCotitular")!=null?Double.parseDouble((String) map.get("porCotitular")):0);
            pstm.setString(14, (String) map.get("codConTitular"));

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

    public int actualizarTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("UPDATE TITULARES SET telefono=?,anexo=?,fax=?,correo_elect=?,fecha_adquisicion=to_date(?,'DD/MM/YYYY HH24:MI:SS'),forma_adquisicion=?,");
            sql.append("cod_contribuyente=?,nro_titular=?,estado_civil=?,sys_dir=?,porcentaje_cotitular=?,condic_de_titular=? ");
            sql.append("WHERE id_ficha=? AND id_persona=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("telefonoTit"));
            pstm.setString(2, (String) map.get("anexoTit"));
            pstm.setString(3, (String) map.get("faxTit"));
            pstm.setString(4, (String) map.get("correoTit"));
            pstm.setString(5, !"".equals((String)map.get("fecAdquisicion"))?(String)map.get("fecAdquisicion"):null);
            pstm.setString(6, (String) map.get("codForAdqPredio"));
            pstm.setString(7, (String) map.get("codContribuyente"));
            pstm.setString(8, map.get("numCotitular")!=null?(String) map.get("numCotitular"):"");
            pstm.setString(9, (String) map.get("codEstCivil"));
            pstm.setString(10, (String) map.get("sys_dir"));
            pstm.setDouble(11, map.get("porCotitular")!=null?Double.parseDouble((String) map.get("porCotitular")):0);
            pstm.setString(12, (String) map.get("codConTitular"));
            pstm.setString(13, (String) map.get("id_ficha"));
            pstm.setString(14, (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));

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

    public HashMap obtenerCotitular(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT t.id_ficha,p.nro_doc,p.tip_doc,p.nombres,p.ape_paterno,p.ape_materno ");
            sql.append("FROM FICHAS f, TITULARES t, PERSONAS p ");
            sql.append("WHERE t.id_ficha=f.id_ficha AND p.id_persona=t.id_persona AND t.estado_civil is null AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                titular = new HashMap();
                titular.put("id_ficha", rst.getString(1));
                titular.put("numDocIdentidad2", rst.getString(2));
                titular.put("codTipDocIdentidad2", rst.getString(3));
                titular.put("nombre2", rst.getString(4));
                titular.put("apePaterno2", rst.getString(5));
                titular.put("apeMaterno2", rst.getString(6));
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

    public List obtenerListaTitular(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT t.id_ficha,t.telefono,t.anexo,t.fax,t.correo_elect,to_char(t.fecha_adquisicion,'dd/MM/yyyy'),t.forma_adquisicion,");
            sql.append("t.cod_contribuyente,t.nro_titular,t.estado_civil,t.sys_dir,t.porcentaje_cotitular,t.condic_de_titular ");
            sql.append("FROM FICHAS f, TITULARES t ");
            sql.append("WHERE t.id_ficha=f.id_ficha AND f.id_ficha = ? ");
            sql.append("ORDER BY t.id_persona ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            if(rst.next()){
                titular = new HashMap();
                titular.put("id_ficha", rst.getString(1));
                titular.put("telefono", rst.getString(2));
                titular.put("anexo", rst.getString(3));
                titular.put("fax", rst.getString(4));
                titular.put("corElectronico", rst.getString(5));
                titular.put("fecAdqTitular", rst.getString(6));
                titular.put("codForAdqTitular", rst.getString(7));
                titular.put("codContribuyente", rst.getString(8));
                titular.put("numCotitular", rst.getString(9));
                titular.put("codEstCivil", rst.getString(10));
                titular.put("sys_dir", rst.getString(11));
                titular.put("porCotitular", rst.getString(12));
                titular.put("conTitular", rst.getString(13));
                titular.put("fecAdquisicion", rst.getString(6));
                titular.put("codForAdqPredio", rst.getString(7));
                lista.add(titular);
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

    public List obtenerListaCotitular(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT f.id_ficha,p.tip_persona,t.estado_civil,p.tip_doc,p.nro_doc,p.nombres,p.ape_paterno,p.ape_materno,p.tip_persona_juridica,et.nro_resolucion,");
            sql.append("et.nro_boleta_pension,to_char(et.fecha_inicio,'dd/MM/yyyy') as fecha_inicio,to_char(et.fecha_vencimiento,'dd/MM/yyyy') as fecha_vencimiento,");
            sql.append("dt.cod_departamento,dt.cod_provincia,dt.cod_distrito,dt.telefono,dt.anexo,dt.fax,dt.correo,dt.cod_via,dt.tip_via,dt.nom_via,dt.nro_muni,");
            sql.append("dt.nom_edificacion,dt.nro_interior,dt.cod_hab_urba,dt.nom_hab_urba,dt.sector,dt.manzana,dt.lote,dt.sublote,et.condicion,");

            sql.append("t.telefono,t.anexo,t.fax,t.correo_elect,to_char(t.fecha_adquisicion,'dd/MM/yyyy'),t.forma_adquisicion,");
            sql.append("t.cod_contribuyente,t.nro_titular,t.estado_civil,t.sys_dir,t.porcentaje_cotitular,t.condic_de_titular,");

            sql.append("dt.cod_departamento,dt.cod_provincia,dt.cod_distrito,dt.telefono,dt.anexo,dt.fax,dt.correo,dt.cod_via,dt.tip_via,");
            sql.append("dt.nom_via,dt.nro_muni,dt.nom_edificacion,dt.nro_interior,dt.cod_hab_urba,dt.nom_hab_urba,dt.sector,dt.manzana,dt.lote,dt.sublote,dt.id_persona,tc.desc_codigo ");

            sql.append("FROM personas p, fichas f, titulares t, exoneraciones_titular et, domicilio_titulares dt, tablas_codigos tc ");
            sql.append("WHERE p.id_persona=t.id_persona AND p.id_persona=et.id_persona AND f.id_ficha=t.id_ficha AND f.id_ficha=et.id_ficha ");
            sql.append("AND dt.id_ficha=f.id_ficha AND dt.id_persona=p.id_persona AND p.tip_persona=tc.codigo AND tc.id_tabla='");
            sql.append(Properties.TIPO_PERSONA).append("' AND f.id_ficha = ? ");
            sql.append("ORDER BY p.id_persona ");




            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                titular = new HashMap();
                titular.put("id_ficha", rst.getString(1));
                titular.put("codTipTitular", rst.getString(2));
                titular.put("codEstCivil", rst.getString(3));
                titular.put("codTipDocIdentidad", rst.getString(4));
                titular.put("numDocIdentidad", rst.getString(5));
                titular.put("nombre", rst.getString(6));
                titular.put("apePaterno", rst.getString(7));
                titular.put("apeMaterno", rst.getString(8));
                titular.put("numRuc", rst.getString(5));
                titular.put("razSocial", rst.getString(6));
                titular.put("codPerJuridica", rst.getString(9));
                titular.put("numResExoneracion", rst.getString(10));
                titular.put("numBolPensionista", rst.getString(11));
                titular.put("fecIniExoneracion", rst.getString(12));
                titular.put("fecVenExoneracion", rst.getString(13));
                titular.put("codDepartamento", rst.getString(14));
                titular.put("codProvincia", rst.getString(15));
                titular.put("codDistrito", rst.getString(16));
                titular.put("telefono", rst.getString(17));
                titular.put("anexo", rst.getString(18));
                titular.put("fax", rst.getString(19));
                titular.put("corElectronico", rst.getString(20));
                titular.put("codVia", rst.getString(21));
                titular.put("codTipVia", rst.getString(22));
                titular.put("nomVia", rst.getString(23));
                titular.put("numMunicipal", rst.getString(24));
                titular.put("nomEdificacion", rst.getString(25));
                titular.put("numInterior", rst.getString(26));
                titular.put("codHabUrbana", rst.getString(27));
                titular.put("nomHabUrbana", rst.getString(28));
                titular.put("sector", rst.getString(29));
                titular.put("manzana", rst.getString(30));
                titular.put("lote", rst.getString(31));
                titular.put("sublote", rst.getString(32));
                titular.put("codConEspTitular", rst.getString(33));
                titular.put("telefono", rst.getString(34));
                titular.put("anexo", rst.getString(35));
                titular.put("fax", rst.getString(36));
                titular.put("corElectronico", rst.getString(37));
                titular.put("fecAdqTitular", rst.getString(38));
                titular.put("codForAdqTitular", rst.getString(39));
                titular.put("codContribuyente", rst.getString(40));
                titular.put("numCotitular", rst.getString(41));
                titular.put("codEstCivil", rst.getString(42));
                titular.put("sys_dir", rst.getString(43));
                titular.put("porCotitular", rst.getString(44));
                titular.put("conTitular", rst.getString(45));
                titular.put("codDepartamento", rst.getString(46));
                titular.put("codProvincia", rst.getString(47));
                titular.put("codDistrito", rst.getString(48));
                titular.put("telefono", rst.getString(49));
                titular.put("anexo", rst.getString(50));
                titular.put("fax", rst.getString(51));
                titular.put("corElectronico", rst.getString(52));
                titular.put("codVia", rst.getString(53));
                titular.put("codTipVia", rst.getString(54));
                titular.put("nomVia", rst.getString(55));
                titular.put("numMunicipal", rst.getString(56));
                titular.put("nomEdificacion", rst.getString(57));
                titular.put("numInterior", rst.getString(58));
                titular.put("codHabUrbana", rst.getString(59));
                titular.put("nomHabUrbana", rst.getString(60));
                titular.put("sector", rst.getString(61));
                titular.put("manzana", rst.getString(62));
                titular.put("lote", rst.getString(63));
                titular.put("sublote", rst.getString(64));
                titular.put("id_persona", rst.getString(65));
                titular.put("desTipTitular", rst.getString(66));
                lista.add(titular);
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

    public boolean existeTitular(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha,id_persona FROM TITULARES WHERE id_ficha = ? AND id_persona = ? ");

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
