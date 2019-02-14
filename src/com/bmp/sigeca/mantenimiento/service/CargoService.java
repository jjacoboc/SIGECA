/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.CargoDAO;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class CargoService extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla CARGO.
     * @return List
     */
    public List obtenerCargos() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            CargoDAO cargoDAO = new CargoDAO(tx);
            lista = cargoDAO.obtenerCargos();
            tx.close();
            tx = null;
        }catch(Exception e){
            if(tx != null) {
                try {
                    tx.close();
                    tx = null;
                }catch(Exception ignore){}
            }
            throw new DAOException(e);
        }
        return lista;
    }

}
