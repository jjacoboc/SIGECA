/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.UsosDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class UsosService  extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla USOS.
     * @return List
     */
    public List obtenerUsos() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UsosDAO usosDao = new UsosDAO(tx);
            lista = usosDao.obtenerUsos();
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
     * tabla USOS, la consulta busca registros donde cod_usos sea igual a
     * codigoUso o desc_uso contenga descripcionUso.
     * @param codigoUso
     * @param descripcionUso
     * @return List
     */
    public List buscarUsos(String codigoUso, String descripcionUso) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UsosDAO usosDao = new UsosDAO(tx);
            lista = usosDao.buscarUsos(codigoUso, descripcionUso);
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
     * Retorna true si existe un registro con cod_usos igual a codigoUso, de lo
     * contrario retorna false.
     * @param codigoUso
     * @return boolean
     * @throws DAOException
     */
    public boolean existeUso(String codigoUso) throws DAOException
    {
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            UsosDAO usosDao = new UsosDAO(tx);
            existeRegistro = usosDao.existeUso(codigoUso);
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
     * Inserta un resgistro con los valores codigoUso y descripcionUso en la
     * tabla USOS.
     * @param codigoUso
     * @param descripcionUso
     * @return long
     */
    public long insertarUso(String codigoUso, String descripcionUso) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UsosDAO usosDao = new UsosDAO(tx);
            result = usosDao.insertarUso(codigoUso, descripcionUso);
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
     * Actualiza el campo desc_uso con el valor del campo descripcionUso
     * del resgistro con el campo cod_usos = codigoUso de la tabla USOS.
     * @param codigoUso
     * @param descripcionUso
     * @return long
     */
    public long actualizarUso(String codigoUso, String descripcionUso) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UsosDAO usosDao = new UsosDAO(tx);
            result = usosDao.actualizarUso(codigoUso, descripcionUso);
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
     * Elimina el registro con cod_uso igual a codigoUso.
     * @param codigoUso
     * @param descripcionUso
     * @return long
     */
    public long eliminarUso(String codigoUso) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UsosDAO usosDao = new UsosDAO(tx);
            result = usosDao.eliminarUso(codigoUso);
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
