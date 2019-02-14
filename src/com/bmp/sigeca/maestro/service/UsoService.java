/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.UsoDAO;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class UsoService extends GenericService{

    public List getListUsos(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UsoDAO uso = new UsoDAO(tx);
            lista = uso.getListUsos();
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
