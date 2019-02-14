/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.CodInstalacionDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class CodInstalacionService  extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla CODIGO_INSTALACION.
     * @return List
     */
    public List obtenerCodInstalaciones() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            CodInstalacionDAO codInstalacionDAO = new CodInstalacionDAO(tx);
            lista = codInstalacionDAO.obtenerCodInstalaciones();
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
     * tabla CODIGO_INSTALACION, la consulta busca registros donde
     * codigo_instalacion sea igual a codigo o desc_instalacion contenga
     * descripcion o unidad contenga el valor de unidad(argumento) o
     * material contenga el valor de material(argumento)
     * @param codigo
     * @param descripcion
     * @param unidad
     * @param material
     * @return List
     * @throws DAOException
     */
    public List buscarCodInstalacion(String codigo, String descripcion, String unidad, String material) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            CodInstalacionDAO codInstalacionDao = new CodInstalacionDAO(tx);
            lista = codInstalacionDao.buscarCodInstalaciones(codigo, descripcion, unidad, material);
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
     * Retorna true si existe un registro con cod_instalacion igual a codigo, de lo
     * contrario retorna false.
     * @param codigoUso
     * @return boolean
     * @throws DAOException
     */
    public boolean existeCodInstalacion(String codigo) throws DAOException
    {
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            CodInstalacionDAO codInstalacionDao = new CodInstalacionDAO(tx);
            
            existeRegistro = codInstalacionDao.existeCodInstalacion(codigo);
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
     * Inserta un resgistro con los valores codigoInstalacion, descripcionInstalacion,
     * unidad y material en la tabla CODIGO_INSTALACION.
     * @param codigoInstalacion
     * @param descripcionInstalacion
     * @param unidad
     * @param material
     * @return long
     * @throws DAOException
     */
    public long insertarCodInstalacion(String codigoInstalacion, String descripcionInstalacion, String unidad, String material) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            CodInstalacionDAO codInstalacionDao = new CodInstalacionDAO(tx);
            result = codInstalacionDao.insertarCodInstalacion(codigoInstalacion, descripcionInstalacion, unidad, material);
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
     * Actualiza el campo desc_instalacion con el valor de descripcionInstalacion,
     * unidad con el valor de unidad(parámetro) y material con el valor de
     * material(parámetro) del resgistro con el campo
     * cod_instalacion = codigoInstalacion de la tabla CODIGO_INSTALACION.
     * @param codigoInstalacion
     * @param descripcionInstalacion
     * @param unidad
     * @param material
     * @return long
     * @throws DAOException
     */
    public long actualizarCodInstalacion(String codigoInstalacion, String descripcionInstalacion, String unidad, String material) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            CodInstalacionDAO codInstalacionDao = new CodInstalacionDAO(tx);
            result = codInstalacionDao.actualizarCodInstalacion(codigoInstalacion, descripcionInstalacion, unidad, material);
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
     * Elimina el registro con cod_instalacion igual a codigoInstalacion.
     * @param codigoNotaria
     * @return long
     * @throws DAOException
     */
    public long eliminarCodInstalacion(String codigoInstalacion) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            CodInstalacionDAO codInstalacionDao = new CodInstalacionDAO(tx);
            result = codInstalacionDao.eliminarCodInstalacion(codigoInstalacion);
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
