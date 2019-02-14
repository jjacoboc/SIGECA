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
public class TablasCodigosDAO extends GenericDAO {

    /** Crea una nueva instancia de NotariaDAO */
    public TablasCodigosDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla TABLAS_CODIGOS.
     * @return List
     * @throws DAOException
     */
    public List obtenerTablasCodigos() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap tablasCodigos = null;
        List listaTablasCodigos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_tabla,codigo,desc_codigo FROM TABLAS_CODIGOS");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                tablasCodigos = new HashMap();
                tablasCodigos.put("id", rst.getString(1));
                tablasCodigos.put("codigo", rst.getString(2));
                tablasCodigos.put("descripcion", rst.getString(3));
                listaTablasCodigos.add(tablasCodigos);
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
            log.error("Ocurrió un error al obtener registros de TABLAS_CODIGOS", e);
            throw new DAOException(e);
        }
        return listaTablasCodigos;
    }

    public HashMap obtenerTablasCodigosById(String id) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap tablasCodigos = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_tabla,codigo,desc_codigo FROM TABLAS_CODIGOS WHERE id_tabla = ?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, id);
            rst = pstm.executeQuery();

            while(rst.next())
            {
                tablasCodigos = new HashMap();
                tablasCodigos.put("id", rst.getString(1));
                tablasCodigos.put("codigo", rst.getString(2));
                tablasCodigos.put("descripcion", rst.getString(3));
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
            log.error("Ocurrió un error al obtener registros de TABLAS_CODIGOS", e);
            throw new DAOException(e);
        }
        return tablasCodigos;
    }

    public HashMap obtenerTablaCodigo(String id, String codigo) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap tablasCodigos = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_tabla,codigo,desc_codigo FROM TABLAS_CODIGOS WHERE id_tabla = ? AND codigo = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, id);
            pstm.setString(2, codigo);
            rst = pstm.executeQuery();

            while(rst.next())
            {
                tablasCodigos = new HashMap();
                tablasCodigos.put("id", rst.getString(1));
                tablasCodigos.put("codigo", rst.getString(2));
                tablasCodigos.put("descripcion", rst.getString(3));
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
            log.error("Ocurrió un error al obtener registros de TABLAS_CODIGOS", e);
            throw new DAOException(e);
        }
        return tablasCodigos;
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta a la
     * tabla TABLAS_CODIGOS, la consulta busca registros donde id_tabla sea
     * igual a id o codigo sea igual a codigo(parámetro) o desc_codigo contenga
     * descripcion.
     * @param id
     * @param codigo
     * @param descripcion
     * @return List
     * @throws DAOException
     */
    public List buscarTablasCodigos(String id, String codigo, String descripcion) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap tablasCodigos = null;
        List listaTablasCodigos = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_tabla, codigo, desc_codigo FROM TABLAS_CODIGOS WHERE 1=1 ");
            if(id!=null && !"".equals(id))
                sql.append("AND id_tabla = '").append(id).append("' ");
            if(codigo!=null && !"".equals(codigo))
                sql.append("AND codigo = '").append(codigo).append("' ");
            if(descripcion!=null && !"".equals(descripcion))
                sql.append("AND desc_codigo like '%").append(descripcion).append("%' ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                tablasCodigos = new HashMap();
                tablasCodigos.put("id", rst.getString(1));
                tablasCodigos.put("codigo", rst.getString(2));
                tablasCodigos.put("descripcion", rst.getString(3));
                listaTablasCodigos.add(tablasCodigos);
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
            log.error("Ocurrió un error al buscar registros en TABLAS_CODIGOS", e);
            throw new DAOException(e);
        }
        return listaTablasCodigos;
    }

    /**
     * Retorna true si existe un registro con id_tabla igual a id, de lo
     * contrario retorna false.
     * @param id
     * @return boolean
     * @throws DAOException
     */
    public boolean existeTablaCodigo(String codigo , String id) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_tabla FROM TABLAS_CODIGOS WHERE codigo = ? and  id_tabla = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigo );
            pstm.setString(2, id);
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
            log.error("Ocurrió un error al buscar un registro en TABLAS_CODIGOS", e);
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
    public long insertarTablaCodigo(String id, String codigo, String descripcion) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TABLAS_CODIGOS(id_tabla,codigo,desc_codigo) ");
            sql.append("VALUES(?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, id);
            pstm.setString(2, codigo);
            pstm.setString(3, descripcion);

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
            log.error("Ocurrió un error al insertar un registro en TABLAS_CODIGOS", e);
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
    public long actualizarTablaCodigo(String id, String codigo, String descripcion) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TABLAS_CODIGOS SET  desc_codigo=? ");
            sql.append("WHERE codigo = ? and id_tabla=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, descripcion);
            pstm.setString(2, codigo);
            pstm.setString(3, id);

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
            log.error("Ocurrió un error al actualizar un registro en TABLAS_CODIGOS", e);
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
    public long eliminarTablaCodigo(String codigo , String id) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TABLAS_CODIGOS WHERE codigo = ? and  id_tabla = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, codigo);
            pstm.setString(2, id);

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
            log.error("Ocurrió un error al eliminar un registro en TABLAS_CODIGOS", e);
            throw new DAOException(e);
        }
        return result;
    }
}