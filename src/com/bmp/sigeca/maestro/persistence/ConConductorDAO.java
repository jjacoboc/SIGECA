/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.persistence;

import com.bmp.sigeca.maestro.bean.ConConductorBean;
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
public class ConConductorDAO extends GenericDAO {

    /** Crea una nueva instancia de ConConductorDAO */
    public ConConductorDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public List getListConConductor() throws DAOException{
        ConConductorBean conConductorBean = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List lista = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("SELECT codConConductor, descripcion ");
            sql.append("FROM TCON_CONDUCTOR where 1=1 ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            rst = pstm.executeQuery();

            lista = new ArrayList();
            while(rst.next()){
                conConductorBean = new ConConductorBean();
                conConductorBean.setCodConConductor(rst.getString(1));
                conConductorBean.setDescripcion(rst.getString(2));
                lista.add(conConductorBean);
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
