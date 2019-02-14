/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.TipArqColonialDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class TipArqColonialService extends GenericService{

    public List getListTipArqColonial(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TipArqColonialDAO tipArqColonial = new TipArqColonialDAO(tx);
            lista = tipArqColonial.getListTipArqColonial();
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
