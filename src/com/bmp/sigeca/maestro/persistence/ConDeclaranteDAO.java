/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.persistence;

import com.bmp.sigeca.maestro.bean.ConDeclaranteBean;
import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class ConDeclaranteDAO extends GenericDAO {

    /** Crea una nueva instancia de ConDeclaranteDAO */
    public ConDeclaranteDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public List getListConDeclarante() throws DAOException{
        ConDeclaranteBean conDeclaranteBean = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT codConDeclarante, descripcion ");
            sql.append("FROM TCON_DECLARANTE where 1=1 ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                conDeclaranteBean = new ConDeclaranteBean();
                conDeclaranteBean.setCodConDeclarante(rst.getString(1));
                conDeclaranteBean.setDescripcion(rst.getString(2));
                lista.add(conDeclaranteBean);
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
