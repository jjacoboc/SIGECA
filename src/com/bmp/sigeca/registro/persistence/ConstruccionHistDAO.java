/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TablaDAO;
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
public class ConstruccionHistDAO extends GenericDAO {

    /** Crea una nueva instancia de ConstruccionHistDAO */
    public ConstruccionHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarConstruccion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codConstruccion","TCONSTRUCCION_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TCONSTRUCCION_HIST(codConstruccion,numPiso,fecConstruccion,codMEP,codECS,codECC,");
            sql.append("murColumnas,techos,pisos,pueVentanas,revestimientos,banos,insEleSanitarias,areConDeclarada,");
            sql.append("areConVerificada,codUCA,areTerreno,areDeclarada,codConIns,areConstruida,codBienComun,codFicha) ");
            sql.append("VALUES(?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,Long.toString(pk));
            pstm.setString(2,(String)map.get("numPiso"));
            pstm.setString(3,(String)map.get("fecConstruccion"));
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
            pstm.setString(14, StringUtil.emptyAsZero((String)map.get("areConDeclarada")));
            pstm.setString(15, StringUtil.emptyAsZero((String)map.get("areConVerificada")));
            pstm.setString(16, StringUtil.emptyStringAsNull((String)map.get("codUCA")));
            pstm.setString(17, StringUtil.emptyAsZero((String)map.get("areTerreno")));
            pstm.setString(18, StringUtil.emptyAsZero((String)map.get("areDeclarada")));
            pstm.setString(19, StringUtil.emptyStringAsNull((String)map.get("codConIns")));
            pstm.setString(20, StringUtil.emptyAsZero((String)map.get("areConstruida")));
            pstm.setString(21,(String)map.get("codBienComun"));
            pstm.setString(22,(String)map.get("codFicha"));

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

    public long actualizarConstruccion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TCONSTRUCCION_HIST SET numPiso=?,fecConstruccion=str_to_date(?,'%d/%m/%Y %h:%i:%S'),codMEP=?,codECS=?,codECC=?,");
            sql.append("murColumnas=?,techos=?,pisos=?,pueVentanas=?,revestimientos=?,banos=?,insEleSanitarias=?,areConDeclarada=?,");
            sql.append("areConVerificada=?,codUCA=?,areTerreno=?,areDeclarada=?,codConIns=?,areConstruida=? ");
            sql.append("WHERE codConstruccion=? AND codBienComun=? AND codFicha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("numPiso"));
            pstm.setString(2,(String)map.get("fecConstruccion"));
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
            pstm.setString(13, StringUtil.emptyAsZero((String)map.get("areConDeclarada")));
            pstm.setString(14, StringUtil.emptyAsZero((String)map.get("areConVerificada")));
            pstm.setString(15, StringUtil.emptyStringAsNull((String)map.get("codUCA")));
            pstm.setString(16, StringUtil.emptyAsZero((String)map.get("areTerreno")));
            pstm.setString(17, StringUtil.emptyAsZero((String)map.get("areDeclarada")));
            pstm.setString(18, StringUtil.emptyStringAsNull((String)map.get("codConIns")));
            pstm.setString(19, StringUtil.emptyAsZero((String)map.get("areConstruida")));
            pstm.setString(20,(String)map.get("codConstruccion"));
            pstm.setString(21,(String)map.get("codBienComun"));
            pstm.setString(22,(String)map.get("codFicha"));

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

    public int eliminarConstruccion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TCONSTRUCCION_HIST WHERE codConstruccion = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codConstruccion"));

            result = pstm.executeUpdate();

            pstm.close();
            pstm = null;
        }catch(Exception ex){
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


    public List obtenerConstruccion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap construccion = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT c.codBienComun,c.numPiso,date_format(c.fecConstruccion,'%d/%m/%Y'),c.codMEP,c.codECS,c.codECC,c.murColumnas,");
            sql.append("c.techos,c.pisos,c.pueVentanas,c.revestimientos,c.banos,c.insEleSanitarias,c.areConDeclarada,c.areConVerificada,c.codUCA,");
            sql.append("c.areTerreno,c.areDeclarada,c.codConIns,c.areConstruida,c.codConstruccion,mep.descripcion,ecs.descripcion,ecc.descripcion,");
            sql.append("c.codFicha ");
            sql.append("FROM TCONSTRUCCION_HIST c, TMAT_EST_PREDOMINANTE mep, TEST_CONSERVACION ecs, TEST_CONSTRUCCION ecc ");
            sql.append("WHERE c.codMEP=mep.codMEP AND c.codECS=ecs.codECS AND c.codECC=ecc.codECC AND codBienComun=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("codBienComun"));

            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                construccion = new HashMap();
                construccion.put("codBienComun", rst.getString(1));
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
                construccion.put("areTerreno", rst.getString(17));
                construccion.put("areDeclarada", rst.getString(18));
                construccion.put("codConIns", rst.getString(19));
                construccion.put("areConstruida", rst.getString(20));
                construccion.put("codConstruccion", rst.getString(21));
                construccion.put("desMEP", rst.getString(22));
                construccion.put("desECS", rst.getString(23));
                construccion.put("desECC", rst.getString(24));
                construccion.put("codFicha", rst.getString(25));
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
