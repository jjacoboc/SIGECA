/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.UsoBCDAO;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class UsoBCService extends GenericService{

    public List getListUsosBC(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UsoBCDAO uso = new UsoBCDAO(tx);
            lista = uso.getListUsosBC();
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
