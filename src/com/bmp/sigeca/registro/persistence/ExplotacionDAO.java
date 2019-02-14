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
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class ExplotacionDAO extends GenericDAO {

    /** Crea una nueva instancia de ExplotacionDAO */
    public ExplotacionDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarExplotacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codExplotacion","TEXPLOTACION");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TEXPLOTACION(codExplotacion,numFicha,desTemporal,porTemporal,desPermanente,porPermanente,");
            sql.append("desForestal,porForestal,desNatural,porNatural,desDescanso,porDescanso,canVacuno,canOvino,canCaprino,");
            sql.append("canCamelido,canAve,canOtros,codTipRiego) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, Long.toString(pk));
            pstm.setString(2, (String)map.get("numFicha"));
            pstm.setString(3, (String)map.get("desTemporal"));
            pstm.setString(4, StringUtil.emptyAsZero((String)map.get("porTemporal")));
            pstm.setString(5, (String)map.get("desPermanente"));
            pstm.setString(6, StringUtil.emptyAsZero((String)map.get("porPermanente")));
            pstm.setString(7, (String)map.get("desForestal"));
            pstm.setString(8, StringUtil.emptyAsZero((String)map.get("porForestal")));
            pstm.setString(9, (String)map.get("desNatural"));
            pstm.setString(10, StringUtil.emptyAsZero((String)map.get("porNatural")));
            pstm.setString(11, (String)map.get("desDescanso"));
            pstm.setString(12, StringUtil.emptyAsZero((String)map.get("porDescanso")));
            pstm.setString(13, StringUtil.emptyAsZero((String)map.get("canVacuno")));
            pstm.setString(14, StringUtil.emptyAsZero((String)map.get("canOvino")));
            pstm.setString(15, StringUtil.emptyAsZero((String)map.get("canCaprino")));
            pstm.setString(16, StringUtil.emptyAsZero((String)map.get("canCamelido")));
            pstm.setString(17, StringUtil.emptyAsZero((String)map.get("canAve")));
            pstm.setString(18, StringUtil.emptyAsZero((String)map.get("canOtros")));
            pstm.setString(19, (String)map.get("codTipRiego"));

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

    public int actualizarExplotacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TEXPLOTACION SET desTemporal=?,porTemporal=?,desPermanente=?,porPermanente=?,desForestal=?,porForestal=?,");
            sql.append("desNatural=?,porNatural=?,desDescanso=?,porDescanso=?,canVacuno=?,canOvino=?,canCaprino=?,canCamelido=?,canAve=?,");
            sql.append("canOtros=?,codTipRiego=? ");
            sql.append("WHERE codExplotacion=? AND numFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String)map.get("desTemporal"));
            pstm.setString(2, StringUtil.emptyAsZero((String)map.get("porTemporal")));
            pstm.setString(3, (String)map.get("desPermanente"));
            pstm.setString(4, StringUtil.emptyAsZero((String)map.get("porPermanente")));
            pstm.setString(5, (String)map.get("desForestal"));
            pstm.setString(6, StringUtil.emptyAsZero((String)map.get("porForestal")));
            pstm.setString(7, (String)map.get("desNatural"));
            pstm.setString(8, StringUtil.emptyAsZero((String)map.get("porNatural")));
            pstm.setString(9, (String)map.get("desDescanso"));
            pstm.setString(10, StringUtil.emptyAsZero((String)map.get("porDescanso")));
            pstm.setString(11, StringUtil.emptyAsZero((String)map.get("canVacuno")));
            pstm.setString(12, StringUtil.emptyAsZero((String)map.get("canOvino")));
            pstm.setString(13, StringUtil.emptyAsZero((String)map.get("canCaprino")));
            pstm.setString(14, StringUtil.emptyAsZero((String)map.get("canCamelido")));
            pstm.setString(15, StringUtil.emptyAsZero((String)map.get("canAve")));
            pstm.setString(16, StringUtil.emptyAsZero((String)map.get("canOtros")));
            pstm.setString(17, (String)map.get("codTipRiego"));
            pstm.setString(18, (String)map.get("codExplotacion"));
            pstm.setString(19, (String)map.get("numFicha"));

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

    public int eliminarExplotacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        int result = 0;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM TEXPLOTACION WHERE numFicha = ? ");

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

    public HashMap obtenerExplotacion(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap explotacion = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codExplotacion,numFicha,desTemporal,porTemporal,desPermanente,porPermanente,desForestal,porForestal,");
            sql.append("desNatural,porNatural,desDescanso,porDescanso,canVacuno,canOvino,canCaprino,canCamelido,canAve,canOtros,codTipRiego ");
            sql.append("FROM TEXPLOTACION ");
            sql.append("WHERE numFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                explotacion = new HashMap();
                explotacion.put("codExplotacion", rst.getString(1));
                explotacion.put("numFicha", rst.getString(2));
                explotacion.put("desTemporal", rst.getString(3));
                explotacion.put("porTemporal", rst.getString(4));
                explotacion.put("desPermanente", rst.getString(5));
                explotacion.put("porPermanente", rst.getString(6));
                explotacion.put("desForestal", rst.getString(7));
                explotacion.put("porForestal", rst.getString(8));
                explotacion.put("desNatural", rst.getString(9));
                explotacion.put("porNatural", rst.getString(10));
                explotacion.put("desDescanso", rst.getString(11));
                explotacion.put("porDescanso", rst.getString(12));
                explotacion.put("canVacuno", rst.getString(13));
                explotacion.put("canOvino", rst.getString(14));
                explotacion.put("canCaprino", rst.getString(15));
                explotacion.put("canCamelido", rst.getString(16));
                explotacion.put("canAve", rst.getString(17));
                explotacion.put("canOtros", rst.getString(18));
                explotacion.put("codTipRiego", rst.getString(19));
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
        return explotacion;
    }
}
