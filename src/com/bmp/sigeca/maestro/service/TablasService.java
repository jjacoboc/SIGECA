/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.TablasDAO;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class TablasService extends GenericService{

    public List getListaTablas(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TablasDAO tablas = new TablasDAO(tx);
            lista = tablas.getListaTablas();
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
