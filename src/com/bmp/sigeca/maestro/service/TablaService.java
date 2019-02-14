/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.TablaDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class TablaService extends GenericService{

    public List getListaTablas(){
        List result = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TablaDAO tabla = new TablaDAO(tx);
            result = tabla.getListaTablas();
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
        return result;
    }

    public int registrarMaestro(StringBuffer sql){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TablaDAO tabla = new TablaDAO(tx);
            result = tabla.registrarMaestro(sql);
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
        return result;
    }

    public int actualizarMaestro(StringBuffer sql){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TablaDAO tabla = new TablaDAO(tx);
            result = tabla.actualizarMaestro(sql);
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
        return result;
    }

    public int eliminarMaestro(StringBuffer sql){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TablaDAO tabla = new TablaDAO(tx);
            result = tabla.eliminarMaestro(sql);
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
        return result;
    }

    public int getMaxPrimaryKey(HashMap map){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TablaDAO tabla = new TablaDAO(tx);
            result = tabla.getMaxPrimaryKey(map);
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
        return result;
    }
}
