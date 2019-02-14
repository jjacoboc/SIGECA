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
 * @author Jonatan Jacobo
 */
public class EstadoUsuarioDAO extends GenericDAO {

    /** Crea una nueva instancia de EstadoUsuarioDAO */
    public EstadoUsuarioDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con todos los registros de la tabla ESTADO_USU_TRA.
     * @return List
     * @throws DAOException
     */
    public List obtenerEstadosUsuarios() throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap estado = null;
        List listaEstados = new ArrayList();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_est_usu_tra,nombre_estado FROM ESTADO_USU_TRA");

            pstm = context.getConnection().prepareStatement(sql.toString());
            rst = pstm.executeQuery();

            while(rst.next())
            {
                estado = new HashMap();
                estado.put("id_est_usu_tra", rst.getString(1));
                estado.put("nombre_estado", rst.getString(2));
                listaEstados.add(estado);
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
            log.error("Ocurrió un error al obtener registros de ESTADO_USU_TRA", e);
            throw new DAOException(e);
        }
        return listaEstados;
    }
}