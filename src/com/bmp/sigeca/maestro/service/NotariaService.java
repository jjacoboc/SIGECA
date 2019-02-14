/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.NotariaDAO;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class NotariaService extends GenericService{

    public List getListNotarias(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            NotariaDAO notaria = new NotariaDAO(tx);
            lista = notaria.getListNotarias();
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
