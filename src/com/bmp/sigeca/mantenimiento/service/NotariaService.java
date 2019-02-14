/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.NotariaDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class NotariaService  extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla NOTARIA.
     * @return List
     */
    public List obtenerNotarias() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            NotariaDAO notariaDao = new NotariaDAO(tx);
            lista = notariaDao.obtenerNotarias();
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
     * tabla NOTARIA, la consulta busca registros donde id_notaria sea igual a
     * codigoNotaria o nom_notaria contenga nombreNotaria o id_ubi_geo
     * sea igual a codigoUbigeo.
     * @param codigoNotaria
     * @param nombreNotaria
     * @param codigoUbigeo
     * @return List
     */
    public List buscarNotarias(String codigoNotaria, String nombreNotaria, String codigoUbigeo) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            NotariaDAO notariaDao = new NotariaDAO(tx);
            lista = notariaDao.buscarNotarias(codigoNotaria, nombreNotaria, codigoUbigeo);
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
     * Retorna true si existe un registro con id_notaria igual a codigoNotaria, de lo
     * contrario retorna false.
     * @param codigoUso
     * @return boolean
     * @throws DAOException
     */
    public boolean existeNotaria(String codigoNotaria) throws DAOException
    {
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            NotariaDAO notariaDao = new NotariaDAO(tx);
            existeRegistro = notariaDao.existeNotaria(codigoNotaria);
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
     * Retorna true si existe un registro con id_notaria igual a codigoNotaria, de lo
     * contrario retorna false.
     * @param codigoUso
     * @return boolean
     * @throws DAOException
     */
    public boolean existeNotariaEnRegistroLegal(String codigoNotaria) throws DAOException{
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            NotariaDAO notariaDao = new NotariaDAO(tx);
            existeRegistro = notariaDao.existeNotariaEnRegistroLegal(codigoNotaria);
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
     * Inserta un resgistro con los valores codigoNotaria, nombreNotaria y
     * codigoUbigeo en la tabla NOTARIA.
     * @param codigoNotaria
     * @param nombreNotaria
     * @param codigoUbigeo
     * @return long
     */
    public long insertarNotaria(String codigoNotaria, String nombreNotaria, String codigoUbigeo) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            NotariaDAO notariaDao = new NotariaDAO(tx);
            result = notariaDao.insertarNotaria(codigoNotaria, nombreNotaria, codigoUbigeo);
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
     * Actualiza el campo nom_notaria con el valor de nombreNotaria e id_ubi_geo
     * con el valor de codigoUbigeo del resgistro con el campo
     * id_notaria = codigoNotaria de la tabla NOTARIA.
     * @param codigoNotaria
     * @param nombreNotaria
     * @param codigoUbigeo
     * @return long
     */
    public long actualizarNotaria(String codigoNotaria, String nombreNotaria, String codigoUbigeo) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            NotariaDAO notariaDao = new NotariaDAO(tx);
            result = notariaDao.actualizarNotaria(codigoNotaria, nombreNotaria, codigoUbigeo);
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
     * Elimina el registro con id_notaria igual a codigoNotaria.
     * @param codigoNotaria
     * @return long
     */
    public long eliminarNotaria(String codigoNotaria) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            NotariaDAO notariaDao = new NotariaDAO(tx);
            result = notariaDao.eliminarNotaria(codigoNotaria);
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
