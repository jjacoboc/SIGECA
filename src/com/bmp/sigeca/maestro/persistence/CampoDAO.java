/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.maestro.bean.CampoBean;
import com.bmp.sigeca.maestro.bean.DataBean;
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
public class CampoDAO extends GenericDAO{

    /** Crea una nueva instancia de CampoDAO */
    public CampoDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public List getListaCampos(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        CampoBean campo = null;
        try{
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT c.codTabla,c.codCampo,c.nomFisico,c.comentario FROM TCAMPO c, TTABLA t ");
            sql.append("WHERE c.codTabla=t.codTabla AND t.codTabla = ? ORDER BY c.nomFisico");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setLong(1, Long.parseLong((String)map.get("codTabla")));

            rst = pstm.executeQuery();
            lista = new ArrayList();

            while(rst.next()){
                campo = new CampoBean();
                campo.setCodTabla(rst.getString(1));
                campo.setCodCampo(rst.getString(2));
                campo.setNomFisico(rst.getString(3));
                campo.setComentario(rst.getString(4));
                lista.add(campo);
            }

            pstm.close();
            pstm = null;
            rst.close();
            rst = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return lista;
    }

    public List getDataMaestro(StringBuffer sql) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        DataBean data = null;
        try{
            pstm = context.getConnection().prepareStatement(sql.toString());

            rst = pstm.executeQuery();
            lista = new ArrayList();

            while(rst.next()){
                data = new DataBean();
                data.setCodigo(rst.getString(1));
                data.setDescripcion(rst.getString(2));
                lista.add(data);
            }
            
            pstm.close();
            pstm = null;
            rst.close();
            rst = null;
        }catch(Exception ex){
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
        return lista;
    }

}
