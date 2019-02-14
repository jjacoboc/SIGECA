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
public class FichaHistDAO extends GenericDAO {

    /** Crea una nueva instancia de FichaHistDAO */
    public FichaHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarFicha(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TFICHA_HIST (numFicha,numFichLote,numTotFichLote,observacion,codEvaPreCatastral,cuc,");
            sql.append("dniDeclarante,nomDeclarante,fecFirDeclarante,dniSupervisor,nomSupervisor,fecFirSupervisor,");
            sql.append("dniTecCatastral,nomTecCatastral,fecFirTecCatastral,dniVerCatastral,nomVerCatastral,fecFirVerCatastral,");
            sql.append("numRegVerCatastral,codTipFicha,areTerInvLotColindante,areTerInvArePublica,areTerInvJarAislamiento,");
            sql.append("areTerInvAreIntangible,apeDeclarante,apeSupervisor,apeTecCatastral,apeVerCatastral,codEstado,codCabecera) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,?,?,?,?,?,?,?,?,?,?) ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("numFicha"));
            pstm.setString(2,(String)map.get("numFichLote"));
            pstm.setString(3,(String)map.get("numTotFichLote"));
            pstm.setString(4,(String)map.get("observacion"));
            pstm.setString(5,(String)map.get("codEvaPreCatastral"));
            pstm.setString(6,(String)map.get("cuc"));
            pstm.setString(7,(String)map.get("dniDeclarante"));
            pstm.setString(8,(String)map.get("nomDeclarante"));
            pstm.setString(9,(String)map.get("fecFirDeclarante"));
            pstm.setString(10,(String)map.get("dniSupervisor"));
            pstm.setString(11,(String)map.get("nomSupervisor"));
            pstm.setString(12,(String)map.get("fecFirSupervisor"));
            pstm.setString(13,(String)map.get("dniTecCatastral"));
            pstm.setString(14,(String)map.get("nomTecCatastral"));
            pstm.setString(15,(String)map.get("fecFirTecCatastral"));
            pstm.setString(16,(String)map.get("dniVerCatastral"));
            pstm.setString(17,(String)map.get("nomVerCatastral"));
            pstm.setString(18,(String)map.get("fecFirVerCatastral"));
            pstm.setString(19,(String)map.get("numRegVerCatastral"));
            pstm.setString(20,(String)map.get("codTipFicha"));
            pstm.setString(21,(String)map.get("areTerInvLotColindante"));
            pstm.setString(22,(String)map.get("areTerInvArePublica"));
            pstm.setString(23,(String)map.get("areTerInvJarAislamiento"));
            pstm.setString(24,(String)map.get("areTerInvAreIntangible"));
            pstm.setString(25,(String)map.get("apeDeclarante"));
            pstm.setString(26,(String)map.get("apeSupervisor"));
            pstm.setString(27,(String)map.get("apeTecCatastral"));
            pstm.setString(28,(String)map.get("apeVerCatastral"));
            pstm.setString(29,(String)map.get("codEstado"));
            pstm.setString(30,(String)map.get("codCabecera"));
            
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

    public int actualizarFicha(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TFICHA_HIST SET numFichLote=?,numTotFichLote=?,observacion=?,codEvaPreCatastral=?,dniDeclarante=?,nomDeclarante=?,");
            sql.append("fecFirDeclarante=str_to_date(?,'%d/%m/%Y %h:%i:%S'),dniSupervisor=?,nomSupervisor=?,fecFirSupervisor=str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("dniTecCatastral=?,nomTecCatastral=?,fecFirTecCatastral=str_to_date(?,'%d/%m/%Y %h:%i:%S'),dniVerCatastral=?,nomVerCatastral=?,");
            sql.append("fecFirVerCatastral=str_to_date(?,'%d/%m/%Y %h:%i:%S'),numRegVerCatastral=?,codTipFicha=?,areTerInvLotColindante=?,areTerInvArePublica=?,");
            sql.append("areTerInvJarAislamiento=?,areTerInvAreIntangible=?,apeDeclarante=?,apeSupervisor=?,apeTecCatastral=?,apeVerCatastral=?,codEstado=? ");
            sql.append("WHERE numFicha=? AND cuc=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("numFichLote"));
            pstm.setString(2,(String)map.get("numTotFichLote"));
            pstm.setString(3,(String)map.get("observacion"));
            pstm.setString(4,(String)map.get("codEvaPreCatastral"));
            pstm.setString(5,(String)map.get("dniDeclarante"));
            pstm.setString(6,(String)map.get("nomDeclarante"));
            pstm.setString(7,(String)map.get("fecFirDeclarante"));
            pstm.setString(8,(String)map.get("dniSupervisor"));
            pstm.setString(9,(String)map.get("nomSupervisor"));
            pstm.setString(10,(String)map.get("fecFirSupervisor"));
            pstm.setString(11,(String)map.get("dniTecCatastral"));
            pstm.setString(12,(String)map.get("nomTecCatastral"));
            pstm.setString(13,(String)map.get("fecFirTecCatastral"));
            pstm.setString(14,(String)map.get("dniVerCatastral"));
            pstm.setString(15,(String)map.get("nomVerCatastral"));
            pstm.setString(16,(String)map.get("fecFirVerCatastral"));
            pstm.setString(17,(String)map.get("numRegVerCatastral"));
            pstm.setString(18,(String)map.get("codTipFicha"));
            pstm.setString(19,(String)map.get("areTerInvLotColindante"));
            pstm.setString(20,(String)map.get("areTerInvArePublica"));
            pstm.setString(21,(String)map.get("areTerInvJarAislamiento"));
            pstm.setString(22,(String)map.get("areTerInvAreIntangible"));
            pstm.setString(23,(String)map.get("apeDeclarante"));
            pstm.setString(24,(String)map.get("apeSupervisor"));
            pstm.setString(25,(String)map.get("apeTecCatastral"));
            pstm.setString(26,(String)map.get("apeVerCatastral"));
            pstm.setString(27,(String)map.get("codEstado"));
            pstm.setString(28,(String)map.get("numFicha"));
            pstm.setString(29,(String)map.get("cuc"));

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

    public HashMap obtenerFicha(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ficha = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT f.numFicha,f.cuc,f.numFichLote,f.numTotFichLote,f.observacion,f.codEvaPreCatastral,f.dniDeclarante,f.nomDeclarante,");
            sql.append("date_format(f.fecFirDeclarante,'%d/%m/%Y'),f.dniSupervisor,f.nomSupervisor,date_format(f.fecFirSupervisor,'%d/%m/%Y'),");
            sql.append("f.dniTecCatastral,f.nomTecCatastral,date_format(f.fecFirTecCatastral,'%d/%m/%Y'),f.dniVerCatastral,f.nomVerCatastral,");
            sql.append("date_format(f.fecFirVerCatastral,'%d/%m/%Y'),f.numRegVerCatastral,f.codTipFicha,f.areTerInvLotColindante,f.areTerInvArePublica,");
            sql.append("f.areTerInvJarAislamiento,f.areTerInvAreIntangible,f.apeDeclarante,f.apeSupervisor,f.apeTecCatastral,f.apeVerCatastral,");
            sql.append("tf.descripcion,f.codEstado,e.descripcion,f.codFicha,f.codCabecera ");
            sql.append("FROM TFICHA_HIST f, TTIP_FICHA tf, TESTADO e WHERE f.codTipFicha=tf.codTipFicha AND f.codEstado=e.codEstado ");

            if(map.containsKey("cuc") && map.get("cuc")!=null && !"".equals((String)map.get("cuc"))){
                sql.append("AND f.cuc = '").append((String)map.get("cuc")).append("' ");
            }
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha"))){
                sql.append("AND f.numFicha = '").append((String)map.get("numFicha")).append("' ");
            }
            if(map.containsKey("dniTecCatastral") && map.get("dniTecCatastral")!=null && !"".equals((String)map.get("dniTecCatastral"))){
                sql.append("AND f.dniTecCatastral = '").append((String)map.get("dniTecCatastral")).append("' ");
            }
            if(map.containsKey("dniSupervisor") && map.get("dniSupervisor")!=null && !"".equals((String)map.get("dniSupervisor"))){
                sql.append("AND f.dniSupervisor = '").append((String)map.get("dniSupervisor")).append("' ");
            }
            if(map.containsKey("codEstado") && map.get("codEstado")!=null && !"".equals((String)map.get("codEstado"))){
                sql.append("AND f.codEstado = '").append((String)map.get("codEstado")).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());

            rst = pstm.executeQuery();

            if(rst.next()){
                ficha = new HashMap();
                ficha.put("numFicha", rst.getString(1));
                ficha.put("cuc", rst.getString(2));
                ficha.put("numFichLote", rst.getString(3));
                ficha.put("numTotFichLote", rst.getString(4));
                ficha.put("observacion", rst.getString(5));
                ficha.put("codEvaPreCatastral", rst.getString(6));
                ficha.put("dniDeclarante", rst.getString(7));
                ficha.put("nomDeclarante", rst.getString(8));
                ficha.put("fecFirDeclarante", rst.getString(9));
                ficha.put("dniSupervisor", rst.getString(10));
                ficha.put("nomSupervisor", rst.getString(11));
                ficha.put("fecFirSupervisor", rst.getString(12));
                ficha.put("dniTecCatastral", rst.getString(13));
                ficha.put("nomTecCatastral", rst.getString(14));
                ficha.put("fecFirTecCatastral", rst.getString(15));
                ficha.put("dniVerCatastral", rst.getString(16));
                ficha.put("nomVerCatastral", rst.getString(17));
                ficha.put("fecFirVerCatastral", rst.getString(18));
                ficha.put("numRegVerCatastral", rst.getString(19));
                ficha.put("codTipFicha", rst.getString(20));
                ficha.put("areTerInvLotColindante", rst.getString(21));
                ficha.put("areTerInvArePublica", rst.getString(22));
                ficha.put("areTerInvJarAislamiento", rst.getString(23));
                ficha.put("areTerInvAreIntangible", rst.getString(24));
                ficha.put("apeDeclarante", rst.getString(25));
                ficha.put("apeSupervisor", rst.getString(26));
                ficha.put("apeTecCatastral", rst.getString(27));
                ficha.put("apeVerCatastral", rst.getString(28));
                ficha.put("desTipFicha", rst.getString(29));
                ficha.put("codEstado", rst.getString(30));
                ficha.put("desEstado", rst.getString(31));
                ficha.put("codFicha", rst.getString(32));
                ficha.put("codCabecera", rst.getString(33));
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
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT f.numFicha,f.cuc,f.numFichLote,f.numTotFichLote,f.observacion,f.codEvaPreCatastral,f.dniDeclarante,f.nomDeclarante,");
            sql.append("date_format(f.fecFirDeclarante,'%d/%m/%Y'),f.dniSupervisor,f.nomSupervisor,date_format(f.fecFirSupervisor,'%d/%m/%Y'),");
            sql.append("f.dniTecCatastral,f.nomTecCatastral,date_format(f.fecFirTecCatastral,'%d/%m/%Y'),f.dniVerCatastral,f.nomVerCatastral,");
            sql.append("date_format(f.fecFirVerCatastral,'%d/%m/%Y'),f.numRegVerCatastral,f.codTipFicha,f.areTerInvLotColindante,f.areTerInvArePublica,");
            sql.append("f.areTerInvJarAislamiento,f.areTerInvAreIntangible,f.apeDeclarante,f.apeSupervisor,f.apeTecCatastral,f.apeVerCatastral,");
            sql.append("tf.descripcion,f.codEstado,e.descripcion ");
            sql.append("FROM TFICHA_HIST f, TTIP_FICHA tf, TESTADO e, TCABECERA_HIST c ");
            sql.append("WHERE f.codTipFicha=tf.codTipFicha AND f.codEstado=e.codEstado AND f.cuc=c.cuc ");

            if(map.containsKey("codRefCatastral") && map.get("codRefCatastral")!=null && !"".equals((String)map.get("codRefCatastral"))){
                sql.append("AND c.codRefCatastral = '").append((String)map.get("codRefCatastral")).append("' ");
            }
            if(map.containsKey("codHojCatastral") && map.get("codHojCatastral")!=null && !"".equals((String)map.get("codHojCatastral"))){
                sql.append("AND c.codHojCatastral = '").append((String)map.get("codHojCatastral")).append("' ");
            }
            if(map.containsKey("cuc") && map.get("cuc")!=null && !"".equals((String)map.get("cuc"))){
                sql.append("AND f.cuc = '").append((String)map.get("cuc")).append("' ");
            }
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha"))){
                sql.append("AND f.numFicha = '").append((String)map.get("numFicha")).append("' ");
            }
            if(map.containsKey("dniTecCatastral") && map.get("dniTecCatastral")!=null && !"".equals((String)map.get("dniTecCatastral"))){
                sql.append("AND f.dniTecCatastral = '").append((String)map.get("dniTecCatastral")).append("' ");
            }
            if(map.containsKey("nomTecCatastral") && map.get("nomTecCatastral")!=null && !"".equals((String)map.get("nomTecCatastral"))){
                sql.append("AND (f.nomTecCatastral LIKE '%").append((String)map.get("nomTecCatastral")).append("%' ");
                sql.append("OR f.apeTecCatastral LIKE '%").append((String)map.get("nomTecCatastral")).append("%' ");
            }
            if(map.containsKey("codEstado") && map.get("codEstado")!=null && !"".equals((String)map.get("codEstado"))){
                sql.append("AND f.codEstado = '").append((String)map.get("codEstado")).append("' ");
            }

            pstm = context.getConnection().prepareStatement(sql.toString());

            rst = pstm.executeQuery();

            lista = new ArrayList();

            while(rst.next()){
                ficha = new HashMap();
                ficha.put("numFicha", rst.getString(1));
                ficha.put("cuc", rst.getString(2));
                ficha.put("numFichLote", rst.getString(3));
                ficha.put("numTotFichLote", rst.getString(4));
                ficha.put("observacion", rst.getString(5));
                ficha.put("codEvaPreCatastral", rst.getString(6));
                ficha.put("dniDeclarante", rst.getString(7));
                ficha.put("nomDeclarante", rst.getString(8));
                ficha.put("fecFirDeclarante", rst.getString(9));
                ficha.put("dniSupervisor", rst.getString(10));
                ficha.put("nomSupervisor", rst.getString(11));
                ficha.put("fecFirSupervisor", rst.getString(12));
                ficha.put("dniTecCatastral", rst.getString(13));
                ficha.put("nomTecCatastral", rst.getString(14));
                ficha.put("fecFirTecCatastral", rst.getString(15));
                ficha.put("dniVerCatastral", rst.getString(16));
                ficha.put("nomVerCatastral", rst.getString(17));
                ficha.put("fecFirVerCatastral", rst.getString(18));
                ficha.put("numRegVerCatastral", rst.getString(19));
                ficha.put("codTipFicha", rst.getString(20));
                ficha.put("areTerInvLotColindante", rst.getString(21));
                ficha.put("areTerInvArePublica", rst.getString(22));
                ficha.put("areTerInvJarAislamiento", rst.getString(23));
                ficha.put("areTerInvAreIntangible", rst.getString(24));
                ficha.put("apeDeclarante", rst.getString(25));
                ficha.put("apeSupervisor", rst.getString(26));
                ficha.put("apeTecCatastral", rst.getString(27));
                ficha.put("apeVerCatastral", rst.getString(28));
                ficha.put("desTipFicha", rst.getString(29));
                ficha.put("codEstado", rst.getString(30));
                ficha.put("desEstado", rst.getString(31));
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

    public String getLastCodFicha() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        String codFicha = "";
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT MAX(codFicha) FROM TFICHA_HIST WHERE 1=1 ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            if(rst.next()){
                codFicha = rst.getString(1);
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
        return codFicha;
    }

    public int eliminarFicha(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TFICHA_HIST WHERE 1=1 ");

            if(map.containsKey("cuc") && map.get("cuc")!=null && !"".equals((String)map.get("cuc"))){
                sql.append("AND cuc = '").append((String)map.get("cuc")).append("' ");
            }
            if(map.containsKey("numFicha") && map.get("numFicha")!=null && !"".equals((String)map.get("numFicha"))){
                sql.append("AND numFicha = '").append((String)map.get("numFicha")).append("' ");
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

    public String getLastNumFicha() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        String numFicha = "";
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT MAX(numFicha) FROM TFICHA_HIST WHERE 1=1 ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            if(rst.next()){
                numFicha = rst.getString(1);
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
        return numFicha;
    }
}