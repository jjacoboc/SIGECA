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
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class MonumentoDAO extends GenericDAO {

    /** Crea una nueva instancia de MonumentoDAO */
    public MonumentoDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarMonumento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codMonumento","TMONUMENTO");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TMONUMENTO(codMonumento,numFicha,codTipArqColonial,usoActual,usoOriginal,numPisos,fecConstruccion,areTerrTitulo,");
            sql.append("areConstruida,areLibre,portada,balcon,pilastra,moldura,cornisa,ventana,balaustrada,reja,otros,desFachada,desInterior,");
            sql.append("estCimiento,estPiso,estTecho,estPilastra,estRevestimiento,estBalcon,estPuerta,estVentana,estReja,estMuro,estOtros,");
            sql.append("codFilEstilistica,codIntInmueble,resHistorica) ");
            sql.append("VALUES(?,?,?,?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numFicha"));
            pstm.setString(3,(String)map.get("codTipArqColonial"));
            pstm.setString(4,(String)map.get("usoActual"));
            pstm.setString(5,(String)map.get("usoOriginal"));
            pstm.setString(6,(String)map.get("numPisos"));
            pstm.setString(7,(String)map.get("fecConstruccion"));
            pstm.setString(8,(String)map.get("areTerrTitulo"));
            pstm.setString(9,(String)map.get("areConstruida"));
            pstm.setString(10,(String)map.get("areLibre"));
            pstm.setString(11,(String)map.get("portada"));
            pstm.setString(12,(String)map.get("balcon"));
            pstm.setString(13,(String)map.get("pilastra"));
            pstm.setString(14,(String)map.get("moldura"));
            pstm.setString(15,(String)map.get("cornisa"));
            pstm.setString(16,(String)map.get("ventana"));
            pstm.setString(17,(String)map.get("balaustrada"));
            pstm.setString(18,(String)map.get("reja"));
            pstm.setString(19,(String)map.get("otros"));
            pstm.setString(20,(String)map.get("desFachada"));
            pstm.setString(21,(String)map.get("desInterior"));
            pstm.setString(22,(String)map.get("estCimiento"));
            pstm.setString(23,(String)map.get("estPiso"));
            pstm.setString(24,(String)map.get("estTecho"));
            pstm.setString(25,(String)map.get("estPilastra"));
            pstm.setString(26,(String)map.get("estRevestimiento"));
            pstm.setString(27,(String)map.get("estBalcon"));
            pstm.setString(28,(String)map.get("estPuerta"));
            pstm.setString(29,(String)map.get("estVentana"));
            pstm.setString(30,(String)map.get("estReja"));
            pstm.setString(31,(String)map.get("estMuro"));
            pstm.setString(32,(String)map.get("estOtros"));
            pstm.setString(33,(String)map.get("codFilEstilistica"));
            pstm.setString(34,(String)map.get("codIntInmueble"));
            pstm.setString(35,(String)map.get("resHistorica"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;

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

    public long actualizarMonumento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TMONUMENTO SET codTipArqColonial=?,usoActual=?,usoOriginal=?,numPisos=?,fecConstruccion=str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("areTerrTitulo=?,areConstruida=?,areLibre=?,portada=?,balcon=?,pilastra=?,moldura=?,cornisa=?,ventana=?,balaustrada=?,reja=?,");
            sql.append("otros=?,desFachada=?,desInterior=?,estCimiento=?,estPiso=?,estTecho=?,estPilastra=?,estRevestimiento=?,estBalcon=?,estPuerta=?,");
            sql.append("estVentana=?,estReja=?,estMuro=?,estOtros=?,codFilEstilistica=?,codIntInmueble=?,resHistorica=? ");
            sql.append("WHERE codMonumento=? AND numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("codTipArqColonial"));
            pstm.setString(2,(String)map.get("usoActual"));
            pstm.setString(3,(String)map.get("usoOriginal"));
            pstm.setString(4,(String)map.get("numPisos"));
            pstm.setString(5,(String)map.get("fecConstruccion"));
            pstm.setString(6,(String)map.get("areTerrTitulo"));
            pstm.setString(7,(String)map.get("areConstruida"));
            pstm.setString(8,(String)map.get("areLibre"));
            pstm.setString(9,(String)map.get("portada"));
            pstm.setString(10,(String)map.get("balcon"));
            pstm.setString(11,(String)map.get("pilastra"));
            pstm.setString(12,(String)map.get("moldura"));
            pstm.setString(13,(String)map.get("cornisa"));
            pstm.setString(14,(String)map.get("ventana"));
            pstm.setString(15,(String)map.get("balaustrada"));
            pstm.setString(16,(String)map.get("reja"));
            pstm.setString(17,(String)map.get("otros"));
            pstm.setString(18,(String)map.get("desFachada"));
            pstm.setString(19,(String)map.get("desInterior"));
            pstm.setString(20,(String)map.get("estCimiento"));
            pstm.setString(21,(String)map.get("estPiso"));
            pstm.setString(22,(String)map.get("estTecho"));
            pstm.setString(23,(String)map.get("estPilastra"));
            pstm.setString(24,(String)map.get("estRevestimiento"));
            pstm.setString(25,(String)map.get("estBalcon"));
            pstm.setString(26,(String)map.get("estPuerta"));
            pstm.setString(27,(String)map.get("estVentana"));
            pstm.setString(28,(String)map.get("estReja"));
            pstm.setString(29,(String)map.get("estMuro"));
            pstm.setString(30,(String)map.get("estOtros"));
            pstm.setString(31,(String)map.get("codFilEstilistica"));
            pstm.setString(32,(String)map.get("codIntInmueble"));
            pstm.setString(33,(String)map.get("resHistorica"));
            pstm.setString(34,(String)map.get("codMonumento"));
            pstm.setString(35,(String)map.get("numFicha"));

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

    public int eliminarMonumento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TMONUMENTO WHERE numFicha = ? ");

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

    public HashMap obtenerMonumento(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap monumento = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codMonumento,numFicha,codTipArqColonial,usoActual,usoOriginal,numPisos,date_format(fecConstruccion,'%d/%m/%Y'),");
            sql.append("areTerrTitulo,areConstruida,areLibre,portada,balcon,pilastra,moldura,cornisa,ventana,balaustrada,reja,otros,desFachada,");
            sql.append("desInterior,estCimiento,estPiso,estTecho,estPilastra,estRevestimiento,estBalcon,estPuerta,estVentana,estReja,estMuro,");
            sql.append("estOtros,codFilEstilistica,codIntInmueble,resHistorica) ");
            sql.append("FROM TMONUMENTO ");
            sql.append("WHERE numFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                monumento = new HashMap();
                monumento.put("codMonumento", rst.getString(1));
                monumento.put("numFicha", rst.getString(2));
                monumento.put("codTipArqColonial", rst.getString(3));
                monumento.put("usoActual", rst.getString(4));
                monumento.put("usoOriginal", rst.getString(5));
                monumento.put("numPisos", rst.getString(6));
                monumento.put("fecConstruccion", rst.getString(7));
                monumento.put("areTerrTitulo", rst.getString(8));
                monumento.put("areConstruida", rst.getString(9));
                monumento.put("areLibre", rst.getString(10));
                monumento.put("portada", rst.getString(11));
                monumento.put("balcon", rst.getString(12));
                monumento.put("pilastra", rst.getString(13));
                monumento.put("moldura", rst.getString(14));
                monumento.put("cornisa", rst.getString(15));
                monumento.put("ventana", rst.getString(16));
                monumento.put("balaustrada", rst.getString(17));
                monumento.put("reja", rst.getString(18));
                monumento.put("otros", rst.getString(19));
                monumento.put("desFachada", rst.getString(20));
                monumento.put("desInterior", rst.getString(21));
                monumento.put("estCimiento", rst.getString(22));
                monumento.put("estPiso", rst.getString(23));
                monumento.put("estTecho", rst.getString(24));
                monumento.put("estPilastra", rst.getString(25));
                monumento.put("estRevestimiento", rst.getString(26));
                monumento.put("estBalcon", rst.getString(27));
                monumento.put("estPuerta", rst.getString(28));
                monumento.put("estVentana", rst.getString(29));
                monumento.put("estReja", rst.getString(30));
                monumento.put("estMuro", rst.getString(31));
                monumento.put("estOtros", rst.getString(32));
                monumento.put("codFilEstilistica", rst.getString(33));
                monumento.put("codIntInmueble", rst.getString(34));
                monumento.put("resHistorica", rst.getString(35));
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
            throw new DAOException();
        }
        return monumento;
    }
}
