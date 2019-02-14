/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.TipParRegistralDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class TipParRegistralService extends GenericService{

    public List getListTipParRegistral(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TipParRegistralDAO tipParRegistral = new TipParRegistralDAO(tx);
            lista = tipParRegistral.getListTipParRegistral();
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
}
