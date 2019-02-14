/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.TipArquitecturaDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class TipArquitecturaService extends GenericService{

    public List getListTipArquitectura(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TipArquitecturaDAO tipArquitectura = new TipArquitecturaDAO(tx);
            lista = tipArquitectura.getListTipArquitectura();
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
