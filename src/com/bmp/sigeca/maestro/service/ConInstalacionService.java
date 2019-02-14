/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.ConInstalacionDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class ConInstalacionService extends GenericService{

    public List getListConInstalacion(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ConInstalacionDAO conInstalacion = new ConInstalacionDAO(tx);
            lista = conInstalacion.getListConInstalacion();
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
