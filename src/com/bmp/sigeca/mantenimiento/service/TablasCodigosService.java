/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.TablasCodigosDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class TablasCodigosService  extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla TABLAS_CODIGOS.
     * @return List
     */
    public List obtenerTablasCodigos() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            TablasCodigosDAO tablasCodigosDAO = new TablasCodigosDAO(tx);
            lista = tablasCodigosDAO.obtenerTablasCodigos();
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
     * Retorna una lista con los registros resultantes de la consulta de 
     * TABLAS_CODIGOS, la consulta busca valores igual a id o valores
     * iguales a codigo o valores que contengan descripcion
     * @param id
     * @param codigo
     * @param descripcion
     * @return List
     * @throws DAOException
     */
    public List buscarTablasCodigos(String id, String codigo, String descripcion) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            TablasCodigosDAO tablasCodigosDao = new TablasCodigosDAO(tx);
            lista = tablasCodigosDao.buscarTablasCodigos(id, codigo, descripcion);
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
     * Retorna true si existe un registro con id_tabla igual a id, de lo
     * contrario retorna false.
     * @param id
     * @return boolean
     * @throws DAOException
     */
    public boolean existeTablaCodigo(String codigo ,String id) throws DAOException
    {
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            TablasCodigosDAO tablasCodigosDao = new TablasCodigosDAO(tx);
            
            existeRegistro = tablasCodigosDao.existeTablaCodigo(codigo , id);
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
     * Inserta un resgistro con los valores id, codigo y descripcion
     * en la tabla TABLAS_CODIGOS.
     * @param id
     * @param codigo
     * @param descripcion
     * @return long
     * @throws DAOException
     */
    public long insertarTablaCodigo(String id, String codigo, String descripcion) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            TablasCodigosDAO tablasCodigosDao = new TablasCodigosDAO(tx);
            result = tablasCodigosDao.insertarTablaCodigo(id, codigo, descripcion);
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
     * Actualiza el campo codigo con el valor de codigo(parámetro) y desc_codigo
     * con el valor de descripcion del resgistro con el campo
     * id_tabla = id de la tabla TABLAS_CODIGOS.
     * @param id
     * @param codigo
     * @param descripcion
     * @return long
     * @throws DAOException
     */
    public long actualizarTablasCodigos(String id, String codigo, String descripcion) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            TablasCodigosDAO tablasCodigosDao = new TablasCodigosDAO(tx);
            result = tablasCodigosDao.actualizarTablaCodigo(id, codigo, descripcion);
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
     * Elimina el registro con id_tabla igual a id.
     * @param id
     * @return long
     * @throws DAOException
     */
    public long eliminarTablaCodigo(String codigo , String id) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            TablasCodigosDAO tablasCodigosDao = new TablasCodigosDAO(tx);
            result = tablasCodigosDao.eliminarTablaCodigo(codigo ,id);
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
