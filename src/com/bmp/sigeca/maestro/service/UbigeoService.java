/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.UbigeoDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class UbigeoService extends GenericService{

    public List getListDepartamento(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeo = new UbigeoDAO(tx);
            lista = ubigeo.getListDepartamento();
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
        return lista;
    }

    public List getListProvincia(HashMap map){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeo = new UbigeoDAO(tx);
            lista = ubigeo.getListProvincia(map);
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
        return lista;
    }

    public List getListDistrito(HashMap map){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeo = new UbigeoDAO(tx);
            lista = ubigeo.getListDistrito(map);
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
        return lista;
    }

    public boolean existeUbigeo(HashMap map){
        boolean existe = false;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UbigeoDAO ubigeo = new UbigeoDAO(tx);
            existe = ubigeo.existeUbigeo(map);
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
        return existe;
    }
}
