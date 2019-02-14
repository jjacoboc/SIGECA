/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.maestro.persistence.UbigeoDistritalDAO;
import java.util.HashMap;

/**
 *
 * @author Jonatan Jacobo
 */
public class UbigeoDistritalService {

    public HashMap getUbigeoDistrital(){
        HashMap mapa = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UbigeoDistritalDAO ubigeo = new UbigeoDistritalDAO(tx);
            mapa = ubigeo.getUbigeoDistrital();
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
        return mapa;
    }

    public boolean existeUbigeoDistrital(HashMap map){
        boolean existe = false;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UbigeoDistritalDAO ubigeo = new UbigeoDistritalDAO(tx);
            existe = ubigeo.existeUbigeoDistrital(map);
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
