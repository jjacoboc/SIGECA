/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.registro.persistence.DomicilioTitularDAO;
import java.util.HashMap;

/**
 *
 * @author Jonatan Jacobo
 */
public class DomicilioTitularService extends GenericService{

    public int guardarDomicilioTitular(HashMap map){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            DomicilioTitularDAO domicilioTitular = new DomicilioTitularDAO(tx);
            result = domicilioTitular.guardarDomicilioTitular(map);
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

    public int actualizarDomicilioTitular(HashMap map){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            DomicilioTitularDAO domicilioTitular = new DomicilioTitularDAO(tx);
            result = domicilioTitular.actualizarDomicilioTitular(map);
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

    public boolean existeDomicilioTitular(HashMap map){
        boolean result = false;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            DomicilioTitularDAO domicilioTitular = new DomicilioTitularDAO(tx);
            result = domicilioTitular.existeDomicilioTitular(map);
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
