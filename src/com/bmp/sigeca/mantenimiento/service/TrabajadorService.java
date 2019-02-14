/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.TrabajadorDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class TrabajadorService extends GenericService{

    public long getNextPK() throws DAOException{
        TransactionContext tx = null;
        long pk = 0;
        try{
            tx = new TransactionContext();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO(tx);
            pk = trabajadorDAO.getNextPK();
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
        return pk;
    }

    /**
     * Retorna una lista con los registros encontrados de la tabla TRABAJADORES.
     * @return List
     */
    public List buscarTrabajadores(HashMap mapa) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO(tx);
            lista = trabajadorDAO.buscarTrabajadores(mapa);
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

    /**
     * Retorna un registro de la tabla TRABAJADORES.
     * @return HashMap
     */
    public HashMap obtenerTrabajadorByPK(HashMap map) throws DAOException{
        TransactionContext tx = null;
        HashMap mapa = null;
        try{
            tx = new TransactionContext();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO(tx);
            mapa = trabajadorDAO.obtenerTrabajadorByPK(map);
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
        return mapa;
    }

    /**
     * Retorna una lista con todos los registros de la tabla TRABAJADORES.
     * @return List
     */
    public List obtenerTrabajadores() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO(tx);
            lista = trabajadorDAO.obtenerTrabajadores();
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

    /**
     * Inserta un resgistro en la tabla TRABAJADORES.
     * @param mapa
     * @return long
     */
    public long insertarTrabajador(HashMap mapa) throws DAOException{
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO(tx);
            result = trabajadorDAO.insertarTrabajador(mapa);
            tx.close();
            tx = null;
        }
        catch(Exception e)
        {
            if(tx != null) {
                try {
                    tx.close();
                    tx = null;
                }catch(Exception ignore){}
            }
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Actualiza un resgistro de la tabla TRABAJADORES.
     * @param mapa
     * @return long
     */
    public long actualizarTrabajador(HashMap mapa) throws DAOException{
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO(tx);
            result = trabajadorDAO.actualizarTrabajador(mapa);
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
        return result;
    }

    /**
     * Elimina un registro de la tabla TRABAJADORES.
     * @param mapa
     * @return
     * @throws DAOException
     */
    public long eliminarTrabajador(HashMap mapa) throws DAOException{
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO(tx);
            result = trabajadorDAO.eliminarTrabajador(mapa);
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
        return result;
    }

    /**
     * Retorna true si existe un registro con dni igual al ingresado, de lo
     * contrario retorna false.
     * @param idVia
     * @return boolean
     * @throws DAOException
     */
    public boolean existeTrabajadorByDni(String dni) throws DAOException{
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO(tx);
            existeRegistro = trabajadorDAO.existeTrabajadorByDni(dni);
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
        return existeRegistro;
    }
}
