/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.persistence;

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
 * @author Jonatan Jacobo
 */
public class CargoDAO extends GenericDAO {

    /** Crea una nueva instancia de CargoDAO */
    public CargoDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla CARGO.
     * @return List
     * @throws DAOException
     */
    public List obtenerCargos() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap cargo = null;
        List listaCargos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_cargo,nombre_cargo FROM CARGO");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                cargo = new HashMap();
                cargo.put("id_cargo", rst.getString(1));
                cargo.put("nombre_cargo", rst.getString(2));
                listaCargos.add(cargo);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
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
            log.error("Ocurrió un error al obtener registros de CARGO", e);
            throw new DAOException(e);
        }
        return listaCargos;
    }
}
