/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.seguridad.service;

import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.seguridad.persistence.UsuarioDAO;
import java.util.HashMap;

/**
 *
 * @author jjacobo
 */
public class UsuarioService extends GenericService{

    public long getNextPK(){
        TransactionContext tx = null;
        long pk = 0;
        try{
            tx = new TransactionContext();
            UsuarioDAO usuario = new UsuarioDAO(tx);
            pk = usuario.getNextPK();
            tx.close();
            tx = null;
        }catch(Exception e){
            if(tx != null) {
                try {
                    tx.close();
                    tx = null;
                }catch(Exception ignore){}
            }
        }
        return pk;
    }

    public UsuarioBean getUsuarioByPK(HashMap map){
        UsuarioBean usuarioBean = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UsuarioDAO usuario = new UsuarioDAO(tx);
            usuarioBean = new UsuarioBean();
            usuarioBean = usuario.getUsuarioByPK(map);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            if(tx != null) {
                try {                    
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return usuarioBean;
    }
    
    public int registrarUsuario(HashMap map){        
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UsuarioDAO usuario = new UsuarioDAO(tx);
            result = usuario.registrarUsuario(map);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            if(tx != null) {
                try {
                    tx.rollback();
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return result;
    }
    
    public int modificarUsuario(HashMap map){        
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UsuarioDAO usuario = new UsuarioDAO(tx);
            result = usuario.modificarUsuario(map);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            if(tx != null) {
                try {
                    tx.rollback();
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return result;
    }

    public boolean existeUsuarioByTrabajador(HashMap map){
        boolean result = false;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UsuarioDAO usuario = new UsuarioDAO(tx);
            result = usuario.existeUsuarioByTrabajador(map);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            if(tx != null) {
                try {
                    tx.rollback();
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return result;
    }
}
