/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.ClaPredioDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class ClaPredioService extends GenericService{

    public List getListClaPredio(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ClaPredioDAO claPredio = new ClaPredioDAO(tx);
            lista = claPredio.getListClaPredio();
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
