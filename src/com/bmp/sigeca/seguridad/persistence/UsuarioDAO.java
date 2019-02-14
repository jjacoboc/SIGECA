/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.seguridad.persistence;

import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author jjacobo
 */
public class UsuarioDAO extends GenericDAO{

    /** Crea una nueva instancia de DAOUsuario */
    public UsuarioDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long getNextPK() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        long result = 1;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT max(to_Number(id_usuario,'999')) as id FROM USUARIO");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            if(rst.next()){
                result = rst.getLong(1) + 1;
            }

            pstm.close();
            pstm = null;
            rst.close();
            rst = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public UsuarioBean getUsuarioByPK(HashMap map) throws DAOException{
        UsuarioBean usuarioBean = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT u.usuario,u.clave,to_char(u.fecha_creacion,'DD/MM/YYYY HH24:MI:SS'),p.id_perfil,p.nombre_perfil,c.id_cargo,");
            sql.append("c.nombre_cargo,t.nombres,t.ape_paterno,t.ape_materno,t.dni,t.email,u.id_usuario ");
            sql.append("FROM USUARIO u, PERFIL p, TRABAJADORES t, CARGO c, ESTADO_USU_TRA e WHERE u.id_perfil=p.id_perfil ");
            sql.append("AND u.id_est_usu_tra=e.id_est_usu_tra AND u.id_trabajador=t.id_trabajador AND c.id_cargo=t.id_cargo AND u.id_est_usu_tra='1' ");

            if(map.containsKey("loguin")){
                sql.append("AND u.usuario='").append((String)map.get("loguin")).append("' ");
            }
            if(map.containsKey("clave")){
                sql.append("AND u.clave='").append((String)map.get("clave")).append("' ");
            }
            
            pstm = context.getConnection().prepareStatement(sql.toString().trim());            
            rst = pstm.executeQuery();
            
            while(rst.next()){
                usuarioBean = new UsuarioBean();
                usuarioBean.setUsuario(rst.getString(1).trim());
                usuarioBean.setClave(rst.getString(2).trim());
                usuarioBean.setFecCreacion(rst.getString(3).trim());
                usuarioBean.setCodPefil(rst.getString(4).trim());
                usuarioBean.setDesPefil(rst.getString(5).trim());
                usuarioBean.setCodCargo(rst.getString(6).trim());
                usuarioBean.setDesCargo(rst.getString(7).trim());
                usuarioBean.setNombres(rst.getString(8).trim());
                usuarioBean.setApePaterno(rst.getString(9).trim());
                usuarioBean.setApeMaterno(rst.getString(10).trim());
                usuarioBean.setDni(rst.getString(11).trim());
                usuarioBean.setEmail(rst.getString(12).trim());
                usuarioBean.setNombreCompleto(usuarioBean.getNombres().trim()+" "+usuarioBean.getApePaterno().trim()+" "+usuarioBean.getApeMaterno().trim());
                usuarioBean.setCodUsuario(rst.getString(13).trim());
            }
            pstm.close();
            pstm=null;
            rst.close();
            rst=null;            
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
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
            throw new DAOException(ex);
        }
        return usuarioBean;
    }
    
    public int registrarUsuario(HashMap map) throws DAOException{
        int result = 0;
        PreparedStatement pstm = null;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO USUARIO(id_usuario,usuario,clave,fecha_creacion,fecha_cese,id_perfil,id_est_usu_tra,id_trabajador)");
            sql.append("VALUES(?,?,?,NOW(),to_date(?,'dd/MM/yyyy'),?,?,?)");
            
            pstm = context.getConnection().prepareStatement(sql.toString());
            
            pstm.setString(1, ((String)map.get("id_usuario")).trim());
            pstm.setString(2, ((String)map.get("usuario")).trim());
            pstm.setString(3, ((String)map.get("clave")).trim());
            pstm.setString(4, ((String)map.get("fecha_cese")).trim());
            pstm.setString(5, ((String)map.get("id_perfil")).trim());
            pstm.setString(6, ((String)map.get("id_est_usu_tra")).trim());
            pstm.setString(7, ((String)map.get("id_trabajador")).trim());
            
            result = pstm.executeUpdate();
            pstm.close();
            pstm=null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException(ex);
        }
        return result;
    }
    
    public int modificarUsuario(HashMap map) throws DAOException{
        int result = 0;
        PreparedStatement pstm = null;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            if(map.containsKey("clave") && map.get("clave")!=null && !"".equals((String)map.get("clave"))){
                sql.append("clave = '").append((String)map.get("clave")).append("',");
            }
            if(map.containsKey("id_perfil") && map.get("id_perfil")!=null && !"".equals((String)map.get("id_perfil"))){
                sql.append("id_perfil = '").append((String)map.get("id_perfil")).append("',");
            }
            if(map.containsKey("usuario") && map.get("usuario")!=null && !"".equals((String)map.get("usuario"))){
                sql.append("usuario = '").append((String)map.get("usuario")).append("',");
            }
            if(map.containsKey("fecha_cese") && map.get("fecha_cese")!=null && !"".equals((String)map.get("fecha_cese"))){
                sql.append("fecha_cese = to_date('").append((String)map.get("fecha_cese")).append("','dd/MM/yyyy'),");
            }
            if(map.containsKey("id_est_usu_tra") && map.get("id_est_usu_tra")!=null && !"".equals((String)map.get("id_est_usu_tra"))){
                sql.append("id_est_usu_tra = '").append((String)map.get("id_est_usu_tra")).append("',");
            }
            sql.append("fecha_modificacion = NOW() ");
            sql.append("WHERE id_usuario = ? ");
            
            System.out.println(sql.toString());
            
            pstm = context.getConnection().prepareStatement(sql.toString());
            
            pstm.setString(1, (String)map.get("id_usuario"));
            
            result = pstm.executeUpdate();
            
            pstm.close();
            pstm = null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException(ex);
        }
        return result;
    }

    /**
     * Retorna true si existe un registro con id_trabajador igual al ingresado, de lo
     * contrario retorna false.
     * @param idVia
     * @return boolean
     * @throws DAOException
     */
    public boolean existeUsuarioByTrabajador(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existeRegistro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_usuario FROM USUARIO WHERE id_trabajador = ?");
            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1, (String)map.get("id_trabajador"));
            rst = pstm.executeQuery();

            if(rst.next()){
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
            log.error("Ocurrió un error al buscar un registro en USUARIO", e);
            throw new DAOException(e);
        }
        return existeRegistro;
    }

}