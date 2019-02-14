/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.EstadoUsuarioDAO;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class EstadoUsuarioService extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla ESTADO_USU_TRA.
     * @return List
     */
    public List obtenerEstadosUsuarios() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            EstadoUsuarioDAO estadoUsuarioDAO = new EstadoUsuarioDAO(tx);
            lista = estadoUsuarioDAO.obtenerEstadosUsuarios();
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
