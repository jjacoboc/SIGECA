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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class IdeTitCatastralDAO extends GenericDAO {

    /** Crea una nueva instancia de IdeTitCatastralDAO */
    public IdeTitCatastralDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarIdeTitCatastral(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codIdentificacion","TIDE_TIT_CATASTRAL");

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TIDE_TIT_CATASTRAL(codIdentificacion,numFicha,codTipTitular,codEstCivil,codTipDocIdentidad,numDocIdentidad,");
            sql.append("nombre,apePaterno,apeMaterno,numRuc,razSocial,codPerJuridica,codConEspTitular,numResExoneracion,numBolPensionista,");
            sql.append("fecIniExoneracion,fecVenExoneracion,codDepartamento,codProvincia,codDistrito,telefono,anexo,fax,corElectronico,");
            sql.append("codVia,codTipVia,nomVia,numMunicipal,nomEdificacion,numInterior,codHabUrbana,nomHabUrbana,sector,manzana,lote,sublote,");
            sql.append("numCotitular,numTotCotitular,porCotitular,codContribuyente,codForAdqTitular,fecAdqTitular,nomComercial,codConConductor,nomPredio) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("codTipTitular"));
            pstm.setString(4,(String)map.get("codEstCivil"));
            pstm.setString(5,(String)map.get("codTipDocIdentidad"));
            pstm.setString(6,(String)map.get("numDocIdentidad"));
            pstm.setString(7,(String)map.get("nombre"));
            pstm.setString(8,(String)map.get("apePaterno"));
            pstm.setString(9,(String)map.get("apeMaterno"));
            pstm.setString(10,(String)map.get("numRuc"));
            pstm.setString(11,(String)map.get("razSocial"));
            pstm.setString(12,(String)map.get("codPerJuridica"));
            pstm.setString(13,(String)map.get("codConEspTitular"));
            pstm.setString(14,(String)map.get("numResExoneracion"));
            pstm.setString(15,(String)map.get("numBolPensionista"));
            pstm.setString(16,(String)map.get("fecIniExoneracion"));
            pstm.setString(17,(String)map.get("fecVenExoneracion"));
            pstm.setString(18,(String)map.get("codDepartamento"));
            pstm.setString(19,(String)map.get("codProvincia"));
            pstm.setString(20,(String)map.get("codDistrito"));
            pstm.setString(21,(String)map.get("telefono"));
            pstm.setString(22,(String)map.get("anexo"));
            pstm.setString(23,(String)map.get("fax"));
            pstm.setString(24,(String)map.get("corElectronico"));
            pstm.setString(25,(String)map.get("codVia"));
            pstm.setString(26,(String)map.get("codTipVia"));
            pstm.setString(27,(String)map.get("nomVia"));
            pstm.setString(28,(String)map.get("numMunicipal"));
            pstm.setString(29,(String)map.get("nomEdificacion"));
            pstm.setString(30,(String)map.get("numInterior"));
            pstm.setString(31,(String)map.get("codHabUrbana"));
            pstm.setString(32,(String)map.get("nomHabUrbana"));
            pstm.setString(33,(String)map.get("sector"));
            pstm.setString(34,(String)map.get("manzana"));
            pstm.setString(35,(String)map.get("lote"));
            pstm.setString(36,(String)map.get("sublote"));
            pstm.setString(37,(String)map.get("numCotitular"));
            pstm.setString(38,(String)map.get("numTotCotitular"));
            pstm.setString(39,(String)map.get("porCotitular"));
            pstm.setString(40,(String)map.get("codContribuyente"));
            pstm.setString(41,(String)map.get("codForAdqTitular"));
            pstm.setString(42,(String)map.get("fecAdqTitular"));
            pstm.setString(43,(String)map.get("nomComercial"));
            pstm.setString(44,(String)map.get("codConConductor"));
            pstm.setString(45,(String)map.get("nomPredio"));

            result = pstm.executeUpdate();
            if(result!=0) result=pk;

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

    public int actualizarIdeTitCatastral(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TIDE_TIT_CATASTRAL SET codTipTitular=?,codEstCivil=?,codTipDocIdentidad=?,numDocIdentidad=?,nombre=?,apePaterno=?,");
            sql.append("apeMaterno=?,numRuc=?,razSocial=?,codPerJuridica=?,codConEspTitular=?,numResExoneracion=?,numBolPensionista=?,");
            sql.append("fecIniExoneracion=str_to_date(?,'%d/%m/%Y %h:%i:%S'),fecVenExoneracion=str_to_date(?,'%d/%m/%Y %h:%i:%S'),codDepartamento=?,");
            sql.append("codProvincia=?,codDistrito=?,telefono=?,anexo=?,fax=?,corElectronico=?,codVia=?,codTipVia=?,nomVia=?,numMunicipal=?,");
            sql.append("nomEdificacion=?,numInterior=?,codHabUrbana=?,nomHabUrbana=?,sector=?,manzana=?,lote=?,sublote=?,numCotitular=?,");
            sql.append("numTotCotitular=?,porCotitular=?,codContribuyente=?,codForAdqTitular=?,fecAdqTitular=str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("nomComercial=?,codConConductor=?,nomPredio=? ");
            sql.append("WHERE codIdentificacion=? AND numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            
            pstm.setString(1,(String)map.get("codTipTitular"));
            pstm.setString(2,(String)map.get("codEstCivil"));
            pstm.setString(3,(String)map.get("codTipDocIdentidad"));
            pstm.setString(4,(String)map.get("numDocIdentidad"));
            pstm.setString(5,(String)map.get("nombre"));
            pstm.setString(6,(String)map.get("apePaterno"));
            pstm.setString(7,(String)map.get("apeMaterno"));
            pstm.setString(8,(String)map.get("numRuc"));
            pstm.setString(9,(String)map.get("razSocial"));
            pstm.setString(10,(String)map.get("codPerJuridica"));
            pstm.setString(11,(String)map.get("codConEspTitular"));
            pstm.setString(12,(String)map.get("numResExoneracion"));
            pstm.setString(13,(String)map.get("numBolPensionista"));
            pstm.setString(14,(String)map.get("fecIniExoneracion"));
            pstm.setString(15,(String)map.get("fecVenExoneracion"));
            pstm.setString(16,(String)map.get("codDepartamento"));
            pstm.setString(17,(String)map.get("codProvincia"));
            pstm.setString(18,(String)map.get("codDistrito"));
            pstm.setString(19,(String)map.get("telefono"));
            pstm.setString(20,(String)map.get("anexo"));
            pstm.setString(21,(String)map.get("fax"));
            pstm.setString(22,(String)map.get("corElectronico"));
            pstm.setString(23,(String)map.get("codVia"));
            pstm.setString(24,(String)map.get("codTipVia"));
            pstm.setString(25,(String)map.get("nomVia"));
            pstm.setString(26,(String)map.get("numMunicipal"));
            pstm.setString(27,(String)map.get("nomEdificacion"));
            pstm.setString(28,(String)map.get("numInterior"));
            pstm.setString(29,(String)map.get("codHabUrbana"));
            pstm.setString(30,(String)map.get("nomHabUrbana"));
            pstm.setString(31,(String)map.get("sector"));
            pstm.setString(32,(String)map.get("manzana"));
            pstm.setString(33,(String)map.get("lote"));
            pstm.setString(34,(String)map.get("sublote"));
            pstm.setString(35,(String)map.get("numCotitular"));
            pstm.setString(36,(String)map.get("numTotCotitular"));
            pstm.setString(37,(String)map.get("porCotitular"));
            pstm.setString(38,(String)map.get("codContribuyente"));
            pstm.setString(39,(String)map.get("codForAdqTitular"));
            pstm.setString(40,(String)map.get("fecAdqTitular"));
            pstm.setString(41,(String)map.get("nomComercial"));
            pstm.setString(42,(String)map.get("codConConductor"));
            pstm.setString(43,(String)map.get("nomPredio"));
            pstm.setString(44,(String)map.get("codIdentificacion"));
            pstm.setString(45,(String)map.get("numFicha"));

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

    public int eliminarIdeTitCatastral(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TIDE_TIT_CATASTRAL WHERE numFicha = ? ");

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

    public HashMap obtenerIdeTitCatastral(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT f.id_ficha,p.tip_persona,t.estado_civil,p.tip_doc,p.nro_doc,p.nombres,p.ape_paterno,p.ape_materno,p.tip_persona_juridica,et.nro_resolucion,");
            sql.append("et.nro_boleta_pension,to_char(et.fecha_inicio,'dd/MM/yyyy') as fecha_inicio,to_char(et.fecha_vencimiento,'dd/MM/yyyy') as fecha_vencimiento,");
            sql.append("dt.cod_departamento,dt.cod_provincia,dt.cod_distrito,dt.telefono,dt.anexo,dt.fax,dt.correo,dt.cod_via,dt.tip_via,dt.nom_via,dt.nro_muni,");
            sql.append("dt.nom_edificacion,dt.nro_interior,dt.cod_hab_urba,dt.nom_hab_urba,dt.sector,dt.manzana,dt.lote,dt.sublote,et.condicion ");
            sql.append("FROM personas p, fichas f, titulares t, exoneraciones_titular et, domicilio_titulares dt ");
            sql.append("WHERE p.id_persona=t.id_persona AND p.id_persona=et.id_persona AND f.id_ficha=t.id_ficha AND f.id_ficha=et.id_ficha ");
            sql.append("AND dt.id_ficha=f.id_ficha AND dt.id_persona=p.id_persona AND f.id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
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
//                titular.put("numFicha", rst.getString(36));
//                titular.put("numCotitular", rst.getString(37));
//                titular.put("numTotCotitular", rst.getString(38));
//                titular.put("porCotitular", rst.getString(39));
//                titular.put("codContribuyente", rst.getString(40));
//                titular.put("codForAdqTitular", rst.getString(41));
//                titular.put("fecAdqTitular", rst.getString(42));
//                titular.put("nomComercial", rst.getString(43));
//                titular.put("codConConductor", rst.getString(44));
//                titular.put("nomPredio", rst.getString(45));
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

    public List obtenerListaIdeTitCatastral(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap titular = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT f.id_ficha,p.tip_persona,t.estado_civil,p.tip_doc,p.nro_doc,p.nombres,p.ape_paterno,p.ape_materno,p.tip_persona_juridica,et.nro_resolucion,");
            sql.append("et.nro_boleta_pension,to_char(et.fecha_inicio,'dd/MM/yyyy') as fecha_inicio,to_char(et.fecha_vencimiento,'dd/MM/yyyy') as fecha_vencimiento,");
            sql.append("dt.cod_departamento,dt.cod_provincia,dt.cod_distrito,dt.telefono,dt.anexo,dt.fax,dt.correo,dt.cod_via,dt.tip_via,dt.nom_via,dt.nro_muni,");
            sql.append("dt.nom_edificacion,dt.nro_interior,dt.cod_hab_urba,dt.nom_hab_urba,dt.sector,dt.manzana,dt.lote,dt.sublote,et.condicion ");
            sql.append("FROM personas p, fichas f, titulares t, exoneraciones_titular et, domicilio_titulares dt ");
            sql.append("WHERE p.id_persona=t.id_persona AND p.id_persona=et.id_persona AND f.id_ficha=t.id_ficha AND f.id_ficha=et.id_ficha ");
            sql.append("AND dt.id_ficha=f.id_ficha AND dt.id_persona=p.id_persona AND f.id_ficha = ? ");
            sql.append("ORDER BY p.id_persona ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            if(rst.next()){
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
}