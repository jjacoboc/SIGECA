/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.UsosBCDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class UsosBCService  extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla USOS.
     * @return List
     */
    public List obtenerUsosBC() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UsosBCDAO usosBCDao = new UsosBCDAO(tx);
            lista = usosBCDao.obtenerUsosBC();
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
     * codigoUsoBCBC o desc_uso contenga descripcionUsoBC.
     * @param codigoUsoBC
     * @param descripcionUsoBC
     * @return List
     */
    public List buscarUsosBC(String codigoUsoBC, String descripcionUsoBC) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UsosBCDAO usosBCDao = new UsosBCDAO(tx);
            lista = usosBCDao.buscarUsosBC(codigoUsoBC, descripcionUsoBC);
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
     * Retorna true si existe un registro con cod_usos igual a codigoUsoBC, de lo
     * contrario retorna false.
     * @param codigoUsoBC
     * @return boolean
     * @throws DAOException
     */
    public boolean existeUsoBC(String codigoUsoBC) throws DAOException
    {
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            UsosBCDAO usosBCDao = new UsosBCDAO(tx);
            existeRegistro = usosBCDao.existeUsoBC(codigoUsoBC);
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
     * Inserta un resgistro con los valores codigoUsoBC y descripcionUsoBC en la
     * tabla USOS.
     * @param codigoUsoBC
     * @param descripcionUsoBC
     * @return long
     */
    public long insertarUsoBC(String codigoUsoBC, String descripcionUsoBC) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UsosBCDAO usosBCDao = new UsosBCDAO(tx);
            result = usosBCDao.insertarUsoBC(codigoUsoBC, descripcionUsoBC);
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
     * Actualiza el campo desc_uso con el valor del campo descripcionUsoBC
     * del resgistro con el campo cod_usos = codigoUsoBC de la tabla USOS.
     * @param codigoUsoBC
     * @param descripcionUsoBC
     * @return long
     */
    public long actualizarUsoBC(String codigoUsoBC, String descripcionUsoBC) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UsosBCDAO usosBCDao = new UsosBCDAO(tx);
            result = usosBCDao.actualizarUsoBC(codigoUsoBC, descripcionUsoBC);
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
     * Elimina el registro con cod_uso igual a codigoUsoBC.
     * @param codigoUsoBC
     * @param descripcionUsoBC
     * @return long
     */
    public long eliminarUsoBC(String codigoUsoBC) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UsosBCDAO usosBCDao = new UsosBCDAO(tx);
            result = usosBCDao.eliminarUsoBC(codigoUsoBC);
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