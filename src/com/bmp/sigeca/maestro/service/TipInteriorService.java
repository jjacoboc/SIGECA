/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.TipInteriorDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class TipInteriorService extends GenericService{

    public List getListTipInterior(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TipInteriorDAO tipInterior = new TipInteriorDAO(tx);
            lista = tipInterior.getListTipInterior();
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
