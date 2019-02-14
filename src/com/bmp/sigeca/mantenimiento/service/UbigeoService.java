/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.service;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.UbigeoDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class UbigeoService  extends GenericService{

    /**
     * Retorna una lista con todos los registros de la tabla UBIGEO.
     * @return List
     */
    public List obtenerUbigeos() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            lista = ubigeoDao.obtenerUbigeos();
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
     * Retorna el registro activo de la tabla UBIGEO.
     * @return HashMap
     */
    public HashMap obtenerUbigeoActivo() throws DAOException{
        TransactionContext tx = null;
        HashMap ubigeo = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            ubigeo = ubigeoDao.obtenerUbigeoActivo();
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
        return ubigeo;
    }

    /**
     * Retorna el registro de la tabla UBIGEO coincidente con el id dado.
     * @return HashMap
     */
    public HashMap obtenerUbigeoById(String id) throws DAOException{
        TransactionContext tx = null;
        HashMap ubigeo = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            ubigeo = ubigeoDao.obtenerUbigeoById(id);
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
        return ubigeo;
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla UBIGEO, la consulta busca registros donde id_ubi_geo sea igual a
     * codigoUbigeo o ...
     * @param codigoNotaria
     * @param nombreNotaria
     * @param codigoUbigeo
     * @return List
     */
    public List buscarUbigeo(String codigoUbigeo, String nombreUbigeo, String cucDesde, String cucHasta, String ultimoCuc) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            lista = ubigeoDao.buscarUbigeos(codigoUbigeo, nombreUbigeo, cucDesde, cucHasta, ultimoCuc);
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
     * Retorna true si existe un registro con id_notaria igual a codigoUbigeo,
     * de lo contrario retorna false.
     * @param codigoUbigeo
     * @return boolean
     * @throws DAOException
     */
    public boolean existeUbigeo(String codigoUbigeo) throws DAOException
    {
        TransactionContext tx = null;
        boolean existeRegistro = false;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            existeRegistro = ubigeoDao.existeUbigeo(codigoUbigeo);
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
     * Inserta un registro con los valores codigoUbigeo, nombreUbigeo, cucDesde,
     * cucHasta y ultimoCuc en la tabla UBIGEO.
     * @param codigoNotaria
     * @param nombreNotaria
     * @param codigoUbigeo
     * @return long
     */
    public long insertarUbigeo(String codigoUbigeo, String nombreUbigeo, String cucDesde, String cucHasta, String ultimoCuc) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            result = ubigeoDao.insertarUbigeo(codigoUbigeo, nombreUbigeo, cucDesde, cucHasta, ultimoCuc);
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
     * Actualiza el campo nom_ubi_geo, cuc_desde, cuc_hasta y ultimo_cuc
     * con el valor de nombreUbigeo, cucDesde, cucHasta y ultimoCuc del
     * resgistro con el campo id_ubi_geo = codigoUbigeo de la tabla UBIGEO.
     * @param codigoUbigeo
     * @param nombreUbigeo
     * @param cucDesde
     * @param cucHasta
     * @param ultimoCuc
     * @return long
     * @throws DAOException
     */
    public long actualizarUbigeo(HashMap ubigeo) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            result = ubigeoDao.actualizarUbigeo(ubigeo);
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
     * Elimina el registro con id_ubi_geo igual a codigoUbigeo.
     * @param codigoUbigeo
     * @return
     * @throws DAOException
     */
    public long eliminarUbigeo(String codigoUbigeo) throws DAOException
    {
        TransactionContext tx = null;
        long result = 0;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            result = ubigeoDao.eliminarUbigeo(codigoUbigeo);
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
     * Retorna una lista con todos los Departamentos.
     * @return List
     */
    public List obtenerDepartamentos() throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            lista = ubigeoDao.obtenerDepartamentos();
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
     * Retorna una lista con todos las Provincias del departamento codigoDepartamento.
     * @param codigoDepartamento
     * @return List
     * @throws DAOException
     */
    public List obtenerProvincias(String codigoDepartamento) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            lista = ubigeoDao.obtenerProvincias(codigoDepartamento);
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
     * Retorna una lista con todos los Distritos del departamento
     * codigoDepartamento y Pronvincia codigoProvincia
     * @param codigoDepartamento
     * @param codigoProvincia
     * @return List
     * @throws DAOException
     */
    public List obtenerDistritos(String codigoDepartamento, String codigoProvincia) throws DAOException
    {
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            lista = ubigeoDao.obtenerDistritos(codigoDepartamento, codigoProvincia);
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
     * Busca y retorna la descripción de un departamento.
     * @param codigoDepartamento
     * @return String
     * @throws DAOException
     */
    public String buscarDepartamento(String codigoDepartamento) throws DAOException
    {
        TransactionContext tx = null;
        String departamento = "";
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            departamento= ubigeoDao.buscarDepartamento(codigoDepartamento);
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
        return departamento;
    }

    /**
     * Busca y retorna la descripción de una provincia.
     * @param codigoProvincia
     * @param codigoDepartamento
     * @return String
     * @throws DAOException
     */
    public String buscarProvincia(String codigoProvincia, String codigoDepartamento) throws DAOException
    {
        TransactionContext tx = null;
        String provincia = "";
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            provincia = ubigeoDao.buscarProvincia(codigoProvincia, codigoDepartamento);
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
        return provincia;
    }

    /**
     * Busca y retorna la descripción de un distrito.
     * @param codigoDistrito
     * @param codigoProvincia
     * @param codigoDepartamento
     * @return String
     * @throws DAOException
     */
    public String buscarDistrito(String codigoDistrito, String codigoProvincia, String codigoDepartamento) throws DAOException
    {
        TransactionContext tx = null;
        String distrito = "";
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeoDao = new UbigeoDAO(tx);
            distrito = ubigeoDao.buscarDistrito(codigoDistrito, codigoProvincia, codigoDepartamento);
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
        return distrito;
    }
}