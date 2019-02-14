/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.maestro.bean.TablasCodigosBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class TablasCodigosDAO extends GenericDAO {

    /** Crea una nueva instancia de FilEstilisticaDAO */
    public TablasCodigosDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public List getListaMaestro(String idTabla) throws DAOException{
        TablasCodigosBean tablasCodigos = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_tabla, codigo, desc_codigo ");
            sql.append("FROM TABLAS_CODIGOS WHERE id_tabla = '");
            sql.append(idTabla).append("' ");
            sql.append("ORDER BY codigo ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                tablasCodigos = new TablasCodigosBean();
                tablasCodigos.setId_tabla(rst.getString(1));
                tablasCodigos.setCodigo(rst.getString(2));
                tablasCodigos.setDesc_codigo(rst.getString(3));
                lista.add(tablasCodigos);
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
        return lista;
    }
}
