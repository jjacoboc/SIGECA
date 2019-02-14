/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.mantenimiento.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class UbigeoDAO extends GenericDAO {

    /** Crea una nueva instancia de UbigeoDAO */
    public UbigeoDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla UBIGEO.
     * @return List
     * @throws DAOException
     */
    public List obtenerUbigeos() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ubigeo = null;
        List listaUbigeos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_ubi_geo,nom_ubi_geo,cuc_desde,cuc_hasta,ultimo_cuc,activo FROM UBIGEO ORDER BY id_ubi_geo");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                ubigeo = new HashMap();
                ubigeo.put("codigoUbigeo", rst.getString(1));
                ubigeo.put("nombreUbigeo", rst.getString(2));
                ubigeo.put("cucDesde", rst.getString(3));
                ubigeo.put("cucHasta", rst.getString(4));
                ubigeo.put("ultimoCuc", rst.getString(5));
                ubigeo.put("activo", rst.getString(6));
                listaUbigeos.add(ubigeo);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al obtener registros de UBIGEO", e);
            throw new DAOException(e);
        }
        return listaUbigeos;
    }

    /**
     * Retorna el registro activo de la tabla UBIGEO.
     * @return HashMap
     * @throws DAOException
     */
    public HashMap obtenerUbigeoActivo() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ubigeo = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_ubi_geo,nom_ubi_geo,cuc_desde,cuc_hasta,ultimo_cuc,activo FROM UBIGEO WHERE activo = '1'");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            if(rst.next()){
                ubigeo = new HashMap();
                ubigeo.put("codigoUbigeo", rst.getString(1));
                ubigeo.put("nombreUbigeo", rst.getString(2));
                ubigeo.put("cucDesde", rst.getString(3));
                ubigeo.put("cucHasta", rst.getString(4));
                ubigeo.put("ultimoCuc", rst.getString(5));
                ubigeo.put("activo", rst.getString(6));
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al obtener registros de UBIGEO", e);
            throw new DAOException(e);
        }
        return ubigeo;
    }

    /**
     * Retorna el registro de la tabla UBIGEO coincidente con el id dado.
     * @return HashMap
     * @throws DAOException
     */
    public HashMap obtenerUbigeoById(String id) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ubigeo = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_ubi_geo,nom_ubi_geo,cuc_desde,cuc_hasta,ultimo_cuc,activo FROM UBIGEO WHERE id_ubi_geo = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, id);
            rst = pstm.executeQuery();

            if(rst.next()){
                ubigeo = new HashMap();
                ubigeo.put("codigoUbigeo", rst.getString(1));
                ubigeo.put("nombreUbigeo", rst.getString(2));
                ubigeo.put("cucDesde", rst.getString(3));
                ubigeo.put("cucHasta", rst.getString(4));
                ubigeo.put("ultimoCuc", rst.getString(5));
                ubigeo.put("activo", rst.getString(6));
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al obtener registros de UBIGEO", e);
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
     * @throws DAOException
     */
    public List buscarUbigeos(String codigoUbigeo, String nombreUbigeo, String cucDesde, String cucHasta, String ultimoCuc) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ubigeo = null;
        List listaUbigeos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_ubi_geo, nom_ubi_geo, cuc_desde, cuc_hasta, ultimo_cuc FROM UBIGEO WHERE ");
            sql.append("id_ubi_geo = ? or nom_ubi_geo like ? or cuc_desde = ? or cuc_hasta = ? or ultimo_cuc = ?");


            if(nombreUbigeo != null && nombreUbigeo.length() != 0)
            {
                nombreUbigeo = "%" + nombreUbigeo + "%";
            }
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoUbigeo);
            pstm.setString(2, nombreUbigeo);
            pstm.setString(3, cucDesde);
            pstm.setString(4, cucHasta);
            pstm.setString(5, ultimoCuc);
            rst = pstm.executeQuery();

            while(rst.next())
            {
                ubigeo = new HashMap();
                ubigeo.put("codigoUbigeo", rst.getString(1));
                ubigeo.put("nombreUbigeo", rst.getString(2));
                ubigeo.put("cucDesde", rst.getString(3));
                ubigeo.put("cucHasta", rst.getString(4));
                ubigeo.put("ultimoCuc", rst.getString(5));
                listaUbigeos.add(ubigeo);
            }
            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al buscar registros en UBIGEO", e);
            throw new DAOException(e);
        }
        return listaUbigeos;
    }

    /**
     * Retorna true si existe un registro con id_notaria igual a codigoUbigeo,
     * de lo contrario retorna false.
     * @param codigoUbigeo
     * @return boolean
     * @throws DAOException
     */
    public boolean existeUbigeo(String codigoUbigeo) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_ubi_geo FROM UBIGEO WHERE id_ubi_geo = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoUbigeo);
            rst = pstm.executeQuery();

            if(rst.next())
            {
                existeRegistro = true;
            }
            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al buscar un registro en UBIGEO", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

    /**
     * Inserta un resgistro con los valores codigoUbigeo, nombreUbigeo, cucDesde
     * cucHasta y ultimoCuc en la tabla UBIGEO.
     * @param codigoUbigeo
     * @param nombreUbigeo
     * @param cucDesde
     * @param cucHasta
     * @param ultimoCuc
     * @return long
     * @throws DAOException
     */
    public long insertarUbigeo(String codigoUbigeo, String nombreUbigeo, String cucDesde, String cucHasta, String ultimoCuc) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO UBIGEO(id_ubi_geo, nom_ubi_geo, cuc_desde, cuc_hasta, ultimo_cuc) ");
            sql.append("VALUES(?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoUbigeo);
            pstm.setString(2, nombreUbigeo);
            pstm.setString(3, cucDesde);
            pstm.setString(4, cucHasta);
            pstm.setString(5, ultimoCuc);

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al insertar un registro en UBIGEO", e);
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
    public long actualizarUbigeo(HashMap ubigeo) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE UBIGEO SET nom_ubi_geo=?, cuc_desde=?, cuc_hasta=?, ultimo_cuc=? , activo=? ");
            sql.append("WHERE id_ubi_geo=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)ubigeo.get("nombreUbigeo"));
            pstm.setString(2, (String)ubigeo.get("cucDesde"));
            pstm.setString(3, (String)ubigeo.get("cucHasta"));
            pstm.setString(4, (String)ubigeo.get("ultimoCuc"));
            pstm.setString(5, (String)ubigeo.get("activo"));
            pstm.setString(6, (String)ubigeo.get("codigoUbigeo"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al actualizar un registro en UBIGEO", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Elimina el registro con id_ubi_geo igual a codigoUbigeo.
     * @param codigoUbigeo
     * @return long
     * @throws DAOException
     */
    public long eliminarUbigeo(String codigoUbigeo) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM UBIGEO WHERE id_ubi_geo = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoUbigeo);

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            pstm.close();
            pstm=null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al eliminar un registro en UBIGEO", e);
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * Retorna una lista con todos los Departamentos.
     * @return List
     * @throws DAOException
     */
    public List obtenerDepartamentos() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap departamento = null;
        List listaDepartamentos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT codigo,descripcion FROM DEPARTAMENTO");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                departamento = new HashMap();
                departamento.put("codigoDepartamento", rst.getString(1));
                departamento.put("descripcionDepartamento", rst.getString(2));
                listaDepartamentos.add(departamento);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al obtener registros de DEPARTAMENTO", e);
            throw new DAOException(e);
        }
        return listaDepartamentos;
    }

    /**
     * Retorna una lista con todos las Provincias del departamento codigoDepartamento.
     * @param codigoDepartamento
     * @return
     * @throws DAOException
     */
    public List obtenerProvincias(String codigoDepartamento) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap provincia = null;
        List listaProvincias = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT codigo, descripcion FROM PROVINCIA ");
            sql.append("WHERE codigo_departamento=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoDepartamento);
            rst = pstm.executeQuery();

            while(rst.next())
            {
                provincia = new HashMap();
                provincia.put("codigoProvincia", rst.getString(1));
                provincia.put("descripcionProvincia", rst.getString(2));
                listaProvincias.add(provincia);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al obtener registros de PROVINCIA", e);
            throw new DAOException(e);
        }
        return listaProvincias;
    }

    /**
     * Retorna una lista con todos los Distritos del departamento
     * codigoDepartamento y Pronvincia codigoProvincia
     * @param codigoDepartamento
     * @param codigoProvincia
     * @return List
     * @throws DAOException
     */
    public List obtenerDistritos(String codigoDepartamento, String codigoProvincia) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap distrito = null;
        List listaDistritos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT codigo, descripcion FROM DISTRITO ");
            sql.append("WHERE codigo_departamento=? and codigo_provincia=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoDepartamento);
            pstm.setString(2, codigoProvincia);
            rst = pstm.executeQuery();

            while(rst.next())
            {
                distrito = new HashMap();
                distrito.put("codigoDistrito", rst.getString(1));
                distrito.put("descripcionDistrito", rst.getString(2));
                listaDistritos.add(distrito);
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al obtener registros de DISTRITO", e);
            throw new DAOException(e);
        }
        return listaDistritos;
    }

    /**
     * Busca y retorna la descripción de un departamento.
     * @param codigo
     * @return String
     * @throws DAOException
     */
    public String buscarDepartamento(String codigo) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT codigo, descripcion FROM DEPARTAMENTO WHERE ");
            sql.append("codigo = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigo);
            rst = pstm.executeQuery();

            if(rst.next())
            {
                return rst.getString(2);
            }
            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al buscar registros en DEPARTAMENTO", e);
            throw new DAOException(e);
        }
        return "";
    }

    /**
     * Busca y retorna la descripción de una provincia.
     * @param codigoProvincia
     * @param codigoDepartamento
     * @return String
     * @throws DAOException
     */
    public String buscarProvincia(String codigoProvincia, String codigoDepartamento) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT codigo, descripcion FROM PROVINCIA WHERE ");
            sql.append("codigo = ? and codigo_departamento = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoProvincia);
            pstm.setString(2, codigoDepartamento);
            rst = pstm.executeQuery();

            if(rst.next())
            {
                return rst.getString(2);
            }
            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al buscar registros en PROVINCIA", e);
            throw new DAOException(e);
        }
        return "";
    }

    /**
     * Busca y retorna la descripción de un distrito.
     * @param codigoDistrito
     * @param codigoProvincia
     * @param codigoDepartamento
     * @return String
     * @throws DAOException
     */
    public String buscarDistrito(String codigoDistrito, String codigoProvincia, String codigoDepartamento) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT codigo, descripcion FROM DISTRITO WHERE ");
            sql.append("codigo = ? AND codigo_provincia = ? AND codigo_departamento = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigoDistrito);
            pstm.setString(2, codigoProvincia);
            pstm.setString(3, codigoDepartamento);
            rst = pstm.executeQuery();

            if(rst.next())
            {
                return rst.getString(2);
            }
            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception e) {
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            log.error("Ocurrió un error al buscar registros en DISTRITO", e);
            throw new DAOException(e);
        }
        return "";
    }
}