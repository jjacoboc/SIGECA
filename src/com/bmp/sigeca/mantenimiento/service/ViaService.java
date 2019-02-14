/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.ViaDAO;
import com.bmp.sigeca.registro.persistence.ViaHabUrbDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class ViaService extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla VIAS.
     * @return List
     */
    public List obtenerVias() throws DAOException{
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            ViaDAO via = new ViaDAO(tx);
            lista = via.obtenerVias();
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
     * Retorna una lista con todos los registros de la tabla VIAS.
     * @return List
     */
    public List obtenerViasByUbigeo(HashMap mapa) throws DAOException{
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            ViaDAO via = new ViaDAO(tx);
            lista = via.obtenerViasByUbigeo(mapa);
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
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla VIAS.
     * @param mapa
     * @return List
     */
    public List buscarVias(HashMap mapa) throws DAOException{
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            ViaDAO via = new ViaDAO(tx);
            lista = via.buscarVias(mapa);
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
     * Retorna true si existe un registro con id_via igual a idVia, de lo
     * contrario retorna false.
     * @param idVia
     * @return boolean
     * @throws DAOException
     */
    public boolean existeVia(String idVia) throws DAOException{
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            ViaDAO via = new ViaDAO(tx);
            existeRegistro = via.existeVia(idVia);
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

    /**
     * Retorna true si existe un registro con id_via igual a idVia, de lo
     * contrario retorna false.
     * @param idVia
     * @return boolean
     * @throws DAOException
     */
    public boolean existeViaHabUrb(HashMap map) throws DAOException{
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            ViaHabUrbDAO via = new ViaHabUrbDAO(tx);
            existeRegistro = via.existeVia(map);
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

    /**
     * Inserta un resgistro en la tabla VIAS.
     * @param mapa
     * @return long
     */
    public long insertarVia(HashMap mapa) throws DAOException{
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            ViaDAO via = new ViaDAO(tx);
            result = via.insertarVia(mapa);
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
     * Actualiza un resgistro de la tabla VIAS.
     * @param mapa
     * @return long
     */
    public long actualizarVia(HashMap mapa) throws DAOException{
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            ViaDAO via = new ViaDAO(tx);
            result = via.actualizarVia(mapa);
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
     * Elimina un registro de la tabla VIAS.
     * @param idVia
     * @return long
     */
    public long eliminarVia(String idVia) throws DAOException{
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            ViaDAO via = new ViaDAO(tx);
            result = via.eliminarVia(idVia);
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

}
