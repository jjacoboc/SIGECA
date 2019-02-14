/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.registro.persistence.AnuncioDAO;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class AnuncioService extends GenericService{

    public int eliminarAnuncio(HashMap map){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            AnuncioDAO anuncio = new AnuncioDAO(tx);
            result = anuncio.eliminarAnuncio(map);
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
