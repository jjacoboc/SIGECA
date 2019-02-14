/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.HabilitacionUrbanaDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class HabilitacionUrbanaService extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla HAB_URBA.
     * @return List
     */
    public List obtenerHabilitacionesUrbanas() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            HabilitacionUrbanaDAO habilitacionUrbana = new HabilitacionUrbanaDAO(tx);
            lista = habilitacionUrbana.obtenerHabilitacionesUrbanas();
            tx.close();
            tx = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
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
    public List obtenerHabilitacionesUrbanasByUbigeo(HashMap mapa) throws DAOException{
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            HabilitacionUrbanaDAO habilitacionUrbana = new HabilitacionUrbanaDAO(tx);
            lista = habilitacionUrbana.obtenerHabilitacionesUrbanasByUbigeo(mapa);
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
     * tabla HAB_URBA.
     * @param mapa
     * @return List
     */
    public List buscarHabilitacionesUrbanas(HashMap mapa) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            HabilitacionUrbanaDAO habilitacionUrbana = new HabilitacionUrbanaDAO(tx);
            lista = habilitacionUrbana.buscarHabilitacionesUrbanas(mapa);
            tx.close();
            tx = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
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
     * Retorna true si existe un registro con id_hab_urba igual a idHabUrba, de lo
     * contrario retorna false.
     * @param idHabUrba
     * @return boolean
     * @throws DAOException
     */
    public boolean existeHabilitacionUrbana(String idHabUrba) throws DAOException
    {
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            HabilitacionUrbanaDAO habilitacionUrbana = new HabilitacionUrbanaDAO(tx);
            existeRegistro = habilitacionUrbana.existeHabilitacionUrbana(idHabUrba);
            tx.close();
            tx = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
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
     * Inserta un resgistro en la tabla HAB_URBA.
     * @param mapa
     * @return long
     */
    public long insertarHabilitacionUrbana(HashMap mapa) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            HabilitacionUrbanaDAO habilitacionUrbana = new HabilitacionUrbanaDAO(tx);
            result = habilitacionUrbana.insertarHabilitacionUrbana(mapa);
            tx.close();
            tx = null;
        }
        catch(Exception e)
        {
            e.getMessage();
            e.printStackTrace();
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
     * Actualiza un resgistro de la tabla HAB_URBA.
     * @param mapa
     * @return long
     */
    public long actualizarHabilitacionUrbana(HashMap mapa) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            HabilitacionUrbanaDAO habilitacionUrbana = new HabilitacionUrbanaDAO(tx);
            result = habilitacionUrbana.actualizarHabilitacionUrbana(mapa);
            tx.close();
            tx = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
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
     * Elimina un registro de la tabla HAB_URBA.
     * @param idHabUrba
     * @return long
     */
    public long eliminarHabilitacionUrbana(String idHabUrba) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            HabilitacionUrbanaDAO habilitacionUrbana = new HabilitacionUrbanaDAO(tx);
            result = habilitacionUrbana.eliminarHabilitacionUrbana(idHabUrba);
            tx.close();
            tx = null;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
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
